package rest.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

abstract public class Dao {
    static Connection conn = null;

    private static Connection getConnection(String url) {
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static Connection getConnection(boolean test) {
        String url = "jdbc:sqlite::memory:";
        return getConnection(url);
    }

    public static Connection getConnection() {
        String url = "jdbc:sqlite:database.db";
        return getConnection(url);
    }
}
