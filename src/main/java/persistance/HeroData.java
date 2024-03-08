package persistance;

import entity.Hero;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Access heroes in the hero table.
 */
public class HeroData {

    public List<Hero> getAllHeroes() {
        List<Hero> heroes = new ArrayList<>();
        Database database = Database.getInstance();
        Connection connection = null;
        String sql = "SELECT * FROM hero";

        try {
            database.connect();
            connection = database.getConnection();
            PreparedStatement selectStatement = connection.prepareStatement(sql);
            ResultSet results = selectStatement.executeQuery();
            while (results.next()) {
                Hero hero = createHeroFromResults(results);
                heroes.add(hero);
            }
        } catch (SQLException e) {
            System.out.println("HeroData.getAllHeroes()...SQL Exception: " + e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            database.disconnect();
        }
        return heroes;
    }

    public List<Hero> searchHeroes(String searchCriteria, String searchTerm) {
        List<Hero> heroes = new ArrayList<>();
        Database database = Database.getInstance();
        Connection connection = null;

        // Build the SQL query with a prepared statement
        String sql = "SELECT * FROM hero WHERE " + searchCriteria + " LIKE ?";
        System.out.println(sql);

        try {
            database.connect();
            connection = database.getConnection();
            PreparedStatement selectStatement = connection.prepareStatement(sql);
            selectStatement.setString(1, "%" + searchTerm + "%");
            ResultSet results = selectStatement.executeQuery();
            while (results.next()) {
                Hero hero = createHeroFromResults(results);
                heroes.add(hero);
                System.out.println(results);
            }
        } catch (Exception e) {
            System.out.println("HeroData.searchHeroes()...SQL Exception: " + e);
        } finally {
            database.disconnect();
        }
        return heroes;
    }

    private Hero createHeroFromResults(ResultSet results) throws SQLException {
        Hero hero = new Hero();
        hero.setCodeName(results.getString("codeName"));
        hero.setPowers(results.getString("powers"));
        hero.setAlignment(results.getString("alignment"));
        hero.setRealName(results.getString("realName"));
        hero.setBio(results.getString("bio"));

        return hero;
    }
}

