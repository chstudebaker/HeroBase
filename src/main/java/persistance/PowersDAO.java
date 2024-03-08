package persistance;

import entity.Powers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PowersDAO {

    private static final Logger logger = LogManager.getLogger(PowersDAO.class);

    public List<Powers> getAllPowers() {
        List<Powers> powersList = new ArrayList<>();
        Database database = Database.getInstance();
        Connection connection = null;
        String sql = "SELECT * FROM Powers";

        try {
            database.connect();
            connection = database.getConnection();
            PreparedStatement selectStatement = connection.prepareStatement(sql);
            ResultSet results = selectStatement.executeQuery();
            while (results.next()) {
                Powers powers = createPowersFromResults(results);
                powersList.add(powers);
            }
        } catch (Exception e) {
            logger.error("Error in getAllPowers: ", e);
        } finally {
            database.disconnect();
        }
        return powersList;
    }

    private Powers createPowersFromResults(ResultSet results) {
        Powers powers = new Powers();
        try {
            powers.setPowerID(results.getInt("powerID"));
            powers.setDescription(results.getString("description"));
            // Set other properties if needed
        } catch (Exception e) {
            logger.error("Error creating Powers from results: ", e);
        }
        return powers;
    }
}
