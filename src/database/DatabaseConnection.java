package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private Connection conn = null;
    private static class LazyHolder{
        static final DatabaseConnection INSTANCE = new DatabaseConnection();
    }
    private DatabaseConnection(){
        try{
            String url = "jdbc:mysql://localhost:3306/menu_db";
            String username = "root";
            String password = "pass";
            conn =  DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public static DatabaseConnection getInstance(){
        return LazyHolder.INSTANCE;
    }

    public Connection getConnection(){
        return conn;
    }
}
