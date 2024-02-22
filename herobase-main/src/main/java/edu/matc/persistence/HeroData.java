package edu.matc.persistence;

import edu.matc.entity.Hero;

import java.sql.*;
import java.util.*;

/**
 * Access heroes in the hero table.
 *
 * @author chstudebaker
 */
public class HeroData {

    public List<Hero> getAllHeroes() {
        List<Hero> heroes = new ArrayList<Hero>();
        Database database = Database.getInstance();
        Connection connection = null;
        String sql = "SELECT * FROM hero";

        try {
            database.connect();
            connection = database.getConnection();
            Statement selectStatement = connection.createStatement();
            ResultSet results = selectStatement.executeQuery(sql);
            while (results.next()) {
                Hero hero = createHeroFromResults(results);
                heroes.add(hero);
            }
            database.disconnect();
        } catch (SQLException e) {
            System.out.println("SearchUser.getAllHeroes()...SQL Exception: " + e);
        } catch (Exception e) {
            System.out.println("SearchUser.getAllHeroes()...Exception: " + e);
        }
        return heroes;
    }

    public List<Hero> getHeroesByCriteria(String criteria) {
        List<Hero> heroes = new ArrayList<>();
        Database database = Database.getInstance();
        Connection connection = null;

        // Modify the SQL query based on the provided criteria
        String sql = "SELECT * FROM hero WHERE code_name='" + criteria + "'";

        try {
            database.connect();
            connection = database.getConnection();
            Statement selectStatement = connection.createStatement();
            ResultSet results = selectStatement.executeQuery(sql);
            while (results.next()) {
                Hero hero = createHeroFromResults(results);
                heroes.add(hero);
            }
            database.disconnect();
        } catch (SQLException e) {
            System.out.println("UserData.getHeroesByCriteria()...SQL Exception: " + e);
        } catch (Exception e) {
            System.out.println("UserData.getHeroesByCriteria()...Exception: " + e);
        }
        return heroes;
    }

    private Hero createHeroFromResults(ResultSet results) throws SQLException {
        Hero hero = new Hero();
        hero.setCodeName(results.getString("code_name"));
        hero.setSecretIdentity(results.getString("secret_identity"));
        hero.setAlignment(results.getString("alignment"));
        hero.setPowerTags(results.getString("power_tags"));
        hero.setBio(results.getString("bio"));

        return hero;
    }

}