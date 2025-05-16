package chatapp;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    public static Connection connect() throws Exception {
        String url = "jdbc:mysql://localhost:3306/myappdb";
        String user = "root"; // Change to your MySQL username
        String password = "Tebalelo123@"; // Change to your MySQL password
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, user, password);
    }
}



