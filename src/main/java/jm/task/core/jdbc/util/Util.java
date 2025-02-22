package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соединения с БД
    public static Connection getConnection() throws SQLException {
        final String url = "jdbc:mysql://localhost:3306/test_main";
        final String login = "root";
        final String password = "";

        Connection con = null;
        con = DriverManager.getConnection(url, login, password);
        return con;
    }


}
