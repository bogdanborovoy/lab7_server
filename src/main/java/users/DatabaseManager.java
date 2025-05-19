package users;

import helpers.CollectionManager;
import models.*;

import java.sql.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.TreeSet;

public class DatabaseManager {
    private Connection connection;
    public DatabaseManager() {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/studs", "s465267", "xUaQIKx4AivXYHbx");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public HashMap<String, String> retrievePasswords() {
        HashMap<String, String> users = new HashMap<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                users.put(resultSet.getString("username"), resultSet.getString("password"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            return users;
        }
    }
    public void addPassword(String username, String password) {
        try {
            PreparedStatement ps = connection.prepareStatement("insert into users values (?, ?)");
            ps.setString(1, username);
            ps.setString(2, password);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public HashMap<String, TreeSet<SpaceMarine>> getSpaceMarines() {
        HashMap<String, TreeSet<SpaceMarine>> spaceMarines = new HashMap<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM spacemarines");
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                Integer x = resultSet.getInt("x");
                int y = resultSet.getInt("y");
                Coordinates coordinates = new Coordinates(x, y);
                ZonedDateTime creation_date = ZonedDateTime.ofInstant(resultSet.getTimestamp("creation_date").toInstant(), ZoneId.systemDefault());
                double health = resultSet.getDouble("health");
                int heartCount = resultSet.getInt("heart_count");
                AstartesCategory astartesCategory = AstartesCategory.valueOf(resultSet.getString("category").toUpperCase());
                MeleeWeapon meleeWeapon = MeleeWeapon.valueOf(resultSet.getString("melee_weapon").toUpperCase());
                String chapterName = resultSet.getString("chapter_name");
                Integer marinesCount = resultSet.getInt("marines_count");
                Chapter chapter = new Chapter(chapterName, marinesCount);
                String username = resultSet.getString("username");
                SpaceMarine spaceMarine = new SpaceMarine(id, name, coordinates, creation_date, health, heartCount,  astartesCategory, meleeWeapon, chapter);
                if (!spaceMarines.containsKey(username)) {
                    spaceMarines.put(username, new TreeSet<>(new CollectionManager.IDComparator()));
                    spaceMarines.get(username).add(spaceMarine);
                }
                else {
                    spaceMarines.get(username).add(spaceMarine);
                }
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        finally {
            return spaceMarines;
        }
    }
}

//    public boolean authenticate(String username, String password) {
//        boolean result = false;
//        try {
//            String query = "SELECT * from users where username = ?";
//            PreparedStatement ps = connection.prepareStatement(query);
//            ps.setString(1, username);
//            ResultSet rs = ps.executeQuery();
//            if(rs.next()) {
//                if (rs.getString("password").equals(password)) {
//                    result = true;
//                }
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return result;
//    }
//}
