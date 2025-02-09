package DBMS_MINIPROJECT;
import java.util.*;
import java.sql.*;


class Electricals extends Mart { 
	
	  
	 void display_menu(ShoppingCart cart,Connection conn,Scanner scan,int customerID) { 
	 int ch=0; 
	 do { 
	 System.out.println("Electricals Menu:"); 
	 System.out.println("1. Browse Electronics");
	 System.out.println("2. Search for Specific Items");  
	 System.out.println("3. View Cart"); 
	 System.out.println("4. Go Back to Main Menu");  
	 System.out.print("Enter your choice: "); 
	 ch = scan.nextInt(); 
	 scan.nextLine();
	 switch (ch) { 
	 case 1: 
		 
	 browseElectronics(cart,conn,scan,customerID); 
	 break; 
	 case 2: 
		 System.out.println("Enter Product Name to search:");
		 String ProductName=scan.nextLine();
		 try {
	 searchItem(conn,ProductName); 
		 }
		 catch(SQLException e)
		 {
			 System.out.println(e.getMessage());
		 }
	 break; 
	 case 3: 
	 cart.display_menu(conn,scan,customerID); 
	 break; 
	 case 4: 
	 System.out.println("Going back to main menu...");  return; 
	 default: 
	 System.out.println("Invalid choice. Please try again.");  } 
	 } while (ch != 4); 
	 } 
	 public void browseElectronics(ShoppingCart cart,Connection conn,Scanner scan,int customerID) {  
		 int choice; 
	 do { 
	 System.out.println("Browse Electronics:");
  
	 System.out.println("1. Electrical appliances"); 
	 System.out.println("2. Electronic devices");
	 System.out.println("3. Go Back to Electronics Menu");  
	 System.out.print("Enter your choice: "); 
	 choice = scan.nextInt(); 
	 switch (choice) { 
	 case 1: 
	 display_Electrical_appliances(cart,conn,scan,customerID); 
	 break; 
	 case 2: 
	 display_Electronic_devices(cart,conn,scan,customerID); 
	 break; 
	 case 3: 
		 System.out.println("Going back to Electronics menu...");  return;
	 
	 
	 default: 
	 System.out.println("Invalid choice. Please try again.");  } 
	 } while (choice != 3); 
	 } 

	 
	
	 
	 
//	 private void insertCartItem(Connection conn, String productName, int quantity, int customerID) throws SQLException {
//		    // Query to get the product ID based on product name
//		    String getProductIDQuery = "SELECT product_id FROM product WHERE name = ?";
//		    // Query to get the cart ID based on customer ID
//		    String getCartIDQuery = "SELECT cart_id FROM cart WHERE Customer_ID = ?";
//		    // Query to insert a new record into cart_item
//		    String insertCartItemQuery = "INSERT INTO cart_item (cart_id, product_id, quantity) VALUES (?, ?, ?)";
//
//		    try (PreparedStatement getProductIDStmt = conn.prepareStatement(getProductIDQuery);
//		         PreparedStatement getCartIDStmt = conn.prepareStatement(getCartIDQuery);
//		         PreparedStatement insertCartItemStmt = conn.prepareStatement(insertCartItemQuery)) {
//		        
//		        // Get the product ID
//		        getProductIDStmt.setString(1, productName);
//		        ResultSet productRs = getProductIDStmt.executeQuery();
//		        
//		        if (productRs.next()) {
//		            int productId = productRs.getInt("product_id");
//
//		            // Get the cart ID
//		            getCartIDStmt.setInt(1, customerID);
//		            ResultSet cartRs = getCartIDStmt.executeQuery();
//		            
//		            if (cartRs.next()) {
//		                int cartId = cartRs.getInt("cart_id");
//
//		                // Insert into cart_item table
//		                insertCartItemStmt.setInt(1, cartId);
//		                insertCartItemStmt.setInt(2, productId);
//		                insertCartItemStmt.setInt(3, quantity);
//		                insertCartItemStmt.executeUpdate();
//		                
//		                System.out.println("Item added to cart successfully.");
//		            } else {
//		                System.out.println("Cart not found for customer ID: " + customerID);
//		            }
//		        } else {
//		            System.out.println("Product not found in inventory: " + productName);
//		        }
//		    }
//		}

	 private void insertCartItem(Connection conn, String productName, int quantity, int customerID) throws SQLException {
		    // Query to get the product ID based on product name
		    String getProductIDQuery = "SELECT product_id FROM product WHERE name = ?";
		    // Query to get the cart ID based on customer ID
		    String getCartIDQuery = "SELECT cart_id FROM cart WHERE Customer_ID = ?";
		    // Query to check if the product is already in the cart_item table for this cart
		    String checkCartItemQuery = "SELECT quantity FROM cart_item WHERE cart_id = ? AND product_id = ?";
		    // Query to insert a new record into cart_item
		    String insertCartItemQuery = "INSERT INTO cart_item (cart_id, product_id, quantity) VALUES (?, ?, ?)";
		    // Query to update the quantity in cart_item if the item is already in the cart
		    String updateCartItemQuery = "UPDATE cart_item SET quantity = quantity + ? WHERE cart_id = ? AND product_id = ?";

		    try (PreparedStatement getProductIDStmt = conn.prepareStatement(getProductIDQuery);
		         PreparedStatement getCartIDStmt = conn.prepareStatement(getCartIDQuery);
		         PreparedStatement checkCartItemStmt = conn.prepareStatement(checkCartItemQuery);
		         PreparedStatement insertCartItemStmt = conn.prepareStatement(insertCartItemQuery);
		         PreparedStatement updateCartItemStmt = conn.prepareStatement(updateCartItemQuery)) {

		        // Get the product ID
		        getProductIDStmt.setString(1, productName);
		        ResultSet productRs = getProductIDStmt.executeQuery();

		        if (productRs.next()) {
		            int productId = productRs.getInt("product_id");

		            // Get the cart ID
		            getCartIDStmt.setInt(1, customerID);
		            ResultSet cartRs = getCartIDStmt.executeQuery();

		            if (cartRs.next()) {
		                int cartId = cartRs.getInt("cart_id");

		                // Check if the item is already in the cart
		                checkCartItemStmt.setInt(1, cartId);
		                checkCartItemStmt.setInt(2, productId);
		                ResultSet cartItemRs = checkCartItemStmt.executeQuery();

		                if (cartItemRs.next()) {
		                    // If the item is already in the cart, update the quantity
		                    updateCartItemStmt.setInt(1, quantity); // Add the new quantity
		                    updateCartItemStmt.setInt(2, cartId);
		                    updateCartItemStmt.setInt(3, productId);
		                    updateCartItemStmt.executeUpdate();
		                    System.out.println("Item quantity updated in cart successfully.");
		                } else {
		                    // If the item is not in the cart, insert a new row
		                    insertCartItemStmt.setInt(1, cartId);
		                    insertCartItemStmt.setInt(2, productId);
		                    insertCartItemStmt.setInt(3, quantity);
		                    insertCartItemStmt.executeUpdate();
		                    System.out.println("Item added to cart successfully.");
		                }
		            } else {
		                System.out.println("Cart not found for customer ID: " + customerID);
		            }
		        } else {
		            System.out.println("Product not found in inventory: " + productName);
		        }
		    }
		}


	 public static void displayElectrical_appliances(Connection conn) {
		    displayCategory(conn, "Electrical_appliances");
		}

		public static void displayElectronic_devices(Connection conn) {
		    displayCategory(conn, "Electronic_devices");
		}

		

		private static void displayCategory(Connection conn, String category) {
		    // Use a parameterized query to prevent SQL injection and retrieve products based on category name
		    String query = "SELECT name, Description, price, stock FROM "+category;
		    
		    try (PreparedStatement pstmt = conn.prepareStatement(query)) {
		        // Set the category name as a parameter in the query
//		        pstmt.setString(1, category);
		        ResultSet rs = pstmt.executeQuery();

		        System.out.println("Products in category: " + category);
		        System.out.println("------------------------------------------------------------------------------------------");

		        boolean hasProducts = false;
		        System.out.printf("%-10s %-20s %-20s %-10s %-10s\n", "Choice ID", "Product", "Description", "Price", "Stock");
		        System.out.println("------------------------------------------------------------------------------------------");

		        int choiceId = 1;  // Start with Choice ID 1
		        while (rs.next()) {
		            String productName = rs.getString("name");
		            String description = rs.getString("Description");
		            double price = rs.getDouble("price");
		            int stock = rs.getInt("stock");

		            System.out.printf("%-10d %-20s %-20s %-10.2f %-10d\n", choiceId, productName, description, price, stock);
		            choiceId++;  // Increment choice ID for the next row
		            hasProducts = true;
		        }

		        if (!hasProducts) {
		            System.out.println("No products found in this category.");
		        }

		    } catch (SQLException se) {
		        System.err.println("Error retrieving products in category " + category + ": " + se.getMessage());
		    } catch (Exception e) {
		        System.err.println("Unexpected error: " + e.getMessage());
		    }
		}

		void display_Electrical_appliances(ShoppingCart cart, Connection conn, Scanner scanner,int customerID) {
		    int choice;
		    do {
//		        
		    	displayElectrical_appliances(conn);
		        System.out.println("9. Go back to previous menu");
		        System.out.print("Enter your choice: ");
		     ;
		        choice = scanner.nextInt();
		        scanner.nextLine(); // Consume newline

		        String productName = null;
		        switch (choice) {
		        case 1: 
		            productName = "Refrigerator"; 
		            break;
		        case 2: 
		            productName = "Washing machine"; 
		            break;
		        case 3: 
		            productName = "oven"; 
		            break;
		        case 4: 
		            productName = "Vacuum cleaner"; 
		            break;
		        case 5: 
		            productName = "Toaster"; 
		            break;
		        case 6: 
		            productName = "Mixer Grinder"; 
		            break;
		        case 7: 
		            productName = "Blender"; 
		            break;
		        case 8: 
		            productName = "TV"; 
		            break;
		        case 9:
		        	return;
		        default:
	                System.out.println("Invalid choice. Please enter a number between 1 and 9.");
	                continue;
		    }

		    
		            
		        

		        System.out.print("Enter the quantity: ");
		        int quantity = scanner.nextInt();

		        
		        // Insert item into cart_item table
		        try {
		            insertCartItem(conn,productName, quantity,customerID);
		            System.out.println(productName + " added to cart.");
		        } catch (SQLException e) {
		            System.err.println("Error adding item to cart: " + e.getMessage());
		        }

		    } while (choice != 9);
		}

		void display_Electronic_devices(ShoppingCart cart, Connection conn, Scanner scanner,int customerID) {
		    int choice;
		    do {
//		    	 
		    	
		    	displayElectronic_devices(conn);
		    	System.out.println("9. Go back to previous menu");
		        System.out.print("Enter your choice: ");
		        
		        choice = scanner.nextInt();
		        scanner.nextLine(); // Consume newline

		        String productName = null;
		        switch (choice) {
		        case 1: 
		            productName = "Smartphone"; 
		            break;
		        case 2: 
		            productName = "Earphone"; 
		            break;
		        case 3: 
		            productName = "Headphones"; 
		            break;
		        case 4: 
		            productName = "iPad"; 
		            break;
		        case 5: 
		            productName = "Bluetooth speaker"; 
		            break;
		        case 6: 
		            productName = "Laptop"; 
		            break;
		        case 7: 
		            productName = "Smart watch"; 
		            break;
		        case 8: 
		            productName = "Monitor"; 
		            break;
		        case 9: 
		            System.out.println("Going back to previous menu...");
		            return; // Exit the method to go back to the previous menu
		        default:
		            System.out.println("Invalid choice. Please enter a number between 1 and 9.");
		            return; // Exit the method on invalid input
		    }


		        System.out.print("Enter the quantity: ");
		        int quantity = scanner.nextInt();

		        
		        // Insert item into cart_item table
		        try {
		            insertCartItem(conn,productName, quantity,customerID);
		            System.out.println(productName + " added to cart.");
		        } catch (SQLException e) {
		            System.err.println("Error adding item to cart: " + e.getMessage());
		        }

		    } while (choice != 6);
		}

	 
		
		public static void searchItem(Connection conn, String productName) throws SQLException {
		    // SQL query to get the product details based on the product name
		    String query = "SELECT * FROM product WHERE name = ?";

		    // Using try-with-resources to ensure resources are closed
		    try (PreparedStatement stmt = conn.prepareStatement(query)) {
		        // Set the product name in the query
		        stmt.setString(1, productName);

		        // Execute the query and get the result set
		        ResultSet rs = stmt.executeQuery();

		        System.out.println("--------------------------------------------------------------------------");

		        boolean hasProducts = false;
		        System.out.printf("%-20s %-30s %-10s %-10s\n", "Product", "Description", "Price", "Stock");
		        System.out.println("--------------------------------------------------------------------------");

		        // Check if a product was found and print details
		        while (rs.next()) {
		            String name = rs.getString("name");
		            String description = rs.getString("Description");
		            double price = rs.getDouble("price");
		            int stock = rs.getInt("stock");

		            // Correct format specifiers for displaying the information
		            System.out.printf("%-20s %-30s %-10.2f %-10d\n", name, description, price, stock);
		            hasProducts = true;
		        }
		        
		        if (!hasProducts) {
		            System.out.println("No products found with the name: " + productName);
		        }

		    } catch (SQLException e) {
		        System.err.println("Error retrieving product: " + e.getMessage());
		    }
		}

	} 
	

