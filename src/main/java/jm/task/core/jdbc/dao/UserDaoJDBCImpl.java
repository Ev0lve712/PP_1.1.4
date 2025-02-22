package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    String CREATE_TABLES = "CREATE TABLE users (id INT AUTO_INCREMENT PRIMARY KEY, name varchar(255), lastName varchar(255) , age int)";
    String DROP_TABLES = "DROP TABLE IF EXISTS users";
    String GET_TABLES = "SELECT * FROM users";
    String CLEAN_TABLES = "TRUNCATE TABLE users";
    String DELETE_USER = "DELETE FROM users WHERE id = ?";
    String SAVE_USER = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";


    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try(Statement statement = Util.getConnection().createStatement();) {
            statement.executeUpdate(CREATE_TABLES);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try(Statement statement = Util.getConnection().createStatement();) {
            statement.executeUpdate(DROP_TABLES);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try(Statement statement = Util.getConnection().createStatement();
        PreparedStatement preparedStatement = Util.getConnection().prepareStatement(SAVE_USER)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try(Statement statement = Util.getConnection().createStatement();
            PreparedStatement preparedStatement = Util.getConnection().prepareStatement(DELETE_USER)) {
            preparedStatement.setLong(1, id);
            int suc_del = preparedStatement.executeUpdate();
            if (suc_del == 0) {
                System.out.println("User with id " + id + " was not found");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try(Statement statement = Util.getConnection().createStatement();) {
            ResultSet rs = statement.executeQuery(GET_TABLES);
            while(rs.next()) {
                String name = rs.getString("name");
                String lastName = rs.getString("lastName");
                byte age = rs.getByte("age");
                User user = new User(name, lastName, age);
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public void cleanUsersTable() {
        try(Statement statement = Util.getConnection().createStatement();) {
            statement.executeUpdate(CLEAN_TABLES);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
