package DBMS_MINIPROJECT;

import java.util.Scanner;
import java.sql.*;

public class Main {

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/MACS_MART";
    static final String USER = "root";
    static final String PASS = "root";
    static int customerID = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Connection conn = null;
        PreparedStatement pstmt = null;

        ShoppingCart cart = new ShoppingCart();
        Mart mart = new Mart();

        System.out.println("**********WELCOME TO MACS MART***********");

        try {
            // Load the JDBC driver
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            boolean success = false;
            boolean isSignedUp = false;

            // Main Menu: Login or Sign Up
            while (!success) {
                System.out.println("1. Login");
                System.out.println("2. Sign Up");
                System.out.print("Choose an option: ");
                int choice = sc.nextInt();
                sc.nextLine(); // Consume newline

                if (choice == 1) {
                    // Login
                    System.out.print("Enter Customer ID to login: ");
                    customerID = sc.nextInt();
                    sc.nextLine(); // Consume newline
                    success = loginCustomer(conn, customerID);
                    if (!success) {
                        System.out.println("Invalid Customer ID. Please try again or sign up.");
                    }
                } else if (choice == 2) {
                    // Sign Up
                    success = insertCustomer(conn, sc);
                    isSignedUp = true;
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
            }

            // If login or signup was successful, proceed to the main menu
            if (success) {
                do {
                    mart.display_menu(cart, conn, sc, customerID);
                } while (isSignedUp);
            }

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            // Close resources if necessary
            try {
                if (conn != null) conn.close();
                sc.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public static boolean insertCustomer(Connection conn, Scanner scan) throws SQLException {
        boolean success = false;
        String query = "INSERT INTO Customer(customer_id, first_name, last_name, email, phone_number) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(query);

        try {
            System.out.print("Enter Customer ID: ");
            customerID = scan.nextInt();
            pstmt.setInt(1, customerID);
            scan.nextLine(); // Consume newline

            System.out.print("Enter First Name: ");
            pstmt.setString(2, scan.nextLine());

            System.out.print("Enter Last Name: ");
            pstmt.setString(3, scan.nextLine());

            System.out.print("Enter Email: ");
            pstmt.setString(4, scan.nextLine());

            System.out.print("Enter Phone Number: ");
            pstmt.setString(5, scan.nextLine());

            pstmt.executeUpdate();
            success = true;
            System.out.println("Customer added successfully!");

        } catch (SQLException e) {
            if (e.getMessage().contains("chk_phone_number")) {
                System.out.println("Error. Try Again!\nTip: The phone number should be of 10 digits.");
                insertCustomer(conn, scan);
            } else if (e.getMessage().contains("chk_email")) {
                System.out.println("Error. Try Again!\nTip: The email should contain an '@' character.");
                insertCustomer(conn, scan);
            } else {
                System.err.println("SQL error: " + e.getMessage());
            }
        } finally {
            pstmt.close();
            return success;
        }
    }

    public static boolean loginCustomer(Connection conn, int customerId) throws SQLException {
        String query = "SELECT customer_id FROM Customer WHERE customer_id = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);

        try {
            pstmt.setInt(1, customerId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                System.out.println("Login successful. Welcome back!");
                return true;
            } else {
                System.out.println("Customer ID not found. Please sign up first.");
                return false;
            }
        } finally {
            pstmt.close();
        }
    }
}
