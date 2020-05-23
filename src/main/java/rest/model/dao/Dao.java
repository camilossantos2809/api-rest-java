package rest.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

abstract public class Dao {
    static Connection conn = null;

    public static Connection getConnection() {
        String url = "jdbc:sqlite:database.db";
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
