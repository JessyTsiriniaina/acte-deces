package actedeces.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseManager {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/acte_deces"; 
    private static final String DB_USER = "ghost"; 
    private static final String DB_PASSWORD = "Ghost@mysql1";
    
    public DatabaseManager() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Erreur de chargement du driver JDBC MySQL : " + e.getMessage());
        }
    }
    
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

}
