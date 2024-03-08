package persistance;

import entity.Powers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    public Powers getPowerById(int powerId) {
        Powers powers = null;
        Database database = Database.getInstance();
        Connection connection = null;
        String sql = "SELECT * FROM Powers WHERE powerID = ?";

        try {
            database.connect();
            connection = database.getConnection();
            PreparedStatement selectStatement = connection.prepareStatement(sql);
            selectStatement.setInt(1, powerId);
            ResultSet results = selectStatement.executeQuery();

            if (results.next()) {
                powers = createPowersFromResults(results);
            }
        } catch (Exception e) {
            logger.error("Error in getPowerById: ", e);
        } finally {
            database.disconnect();
        }
        return powers;
    }


    public void addPower(Powers powers) {
        Database database = Database.getInstance();
        Connection connection = null;
        String sql = "INSERT INTO Powers (description) VALUES (?)";

        try {
            database.connect();
            connection = database.getConnection();
            PreparedStatement insertStatement = connection.prepareStatement(sql);
            insertStatement.setString(1, powers.getDescription());
            insertStatement.executeUpdate();
        } catch (Exception e) {
            logger.error("Error in addPower: ", e);
        } finally {
            database.disconnect();
        }
    }

    public void updatePower(Powers powers) {
        Database database = Database.getInstance();
        Connection connection = null;
        String sql = "UPDATE Powers SET description = ? WHERE powerID = ?";

        try {
            database.connect();
            connection = database.getConnection();
            PreparedStatement updateStatement = connection.prepareStatement(sql);
            updateStatement.setString(1, powers.getDescription());
            updateStatement.setInt(2, powers.getPowerID());
            updateStatement.executeUpdate();
        } catch (Exception e) {
            logger.error("Error in updatePower: ", e);
        } finally {
            database.disconnect();
        }
    }

    public void deletePower(Powers powers) {
        Database database = Database.getInstance();
        Connection connection = null;
        String sql = "DELETE FROM Powers WHERE powerID = ?";

        try {
            database.connect();
            connection = database.getConnection();
            PreparedStatement deleteStatement = connection.prepareStatement(sql);
            deleteStatement.setInt(1, powers.getPowerID());
            deleteStatement.executeUpdate();
        } catch (Exception e) {
            logger.error("Error in deletePower: ", e);
        } finally {
            database.disconnect();
        }
    }

    private Powers createPowersFromResults(ResultSet results) {
        Powers powers = new Powers();
        try {
            powers.setPowerID(results.getInt("powerID"));
            powers.setDescription(results.getString("description"));
            // Set other properties if needed
        } catch (SQLException e) {
            logger.error("Error creating Powers from results: ", e);
        }
        return powers;
    }
}
