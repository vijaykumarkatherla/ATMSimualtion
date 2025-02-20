package com.atmsimulation;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Database {
    private Connection connection;

    // Constructor to establish a database connection
    public Database() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm", "root", "Bunny@123");
        } catch (Exception e) {
            System.out.println("Database Connection Failed: " + e.getMessage());
        }
    }

    // Method to save transactions in the database
    public void saveTransaction(String accountNumber, String detail) {
        String query = "INSERT INTO transactions (account_number, detail) VALUES (?, ?)"; // Fixed column name

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, accountNumber);
            pstmt.setString(2, detail);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error Saving Transaction: " + e.getMessage());
        }
    }
}



