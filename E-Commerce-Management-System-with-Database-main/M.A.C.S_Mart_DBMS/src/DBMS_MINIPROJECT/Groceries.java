package DBMS_MINIPROJECT;
import java.util.*;
import java.sql.*;
import java.sql.Date;
class Groceries extends Mart { 
	
	  
	 void display_menu(ShoppingCart cart,Connection conn,Scanner scan,int customerID) { 
	 int ch=0; 
	 do { 
	 System.out.println("Groceries Menu:"); 
	 System.out.println("1. Browse Groceries");
	 System.out.println("2. Search for Specific Items");  
	 System.out.println("3. View Cart"); 
	 System.out.println("4. Go Back to Main Menu");  
	 System.out.print("Enter your choice: "); 
	 ch = scan.nextInt(); 
	 scan.nextLine();
	 switch (ch) { 
	 case 1: 
		 
	 browseGroceries(cart,conn,scan,customerID); 
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
	 public void browseGroceries(ShoppingCart cart,Connection conn,Scanner scan,int customerID) {  
		 int choice; 
	 do { 
	 System.out.println("Browse Groceries:"); 
	 System.out.println("1. Fruits"); 
	 System.out.println("2. Vegetables");
	 System.out.println("3. Dairy Products"); 
	 System.out.println("4. Go Back to Groceries Menu");  System.out.print("Enter your choice: "); 
	 choice = scan.nextInt(); 
	 switch (choice) { 
	 case 1: 
	 display_Fruits(cart,conn,scan,customerID); 
	 break; 
	 case 2: 
	 display_Vegetables(cart,conn,scan,customerID); 
	 break; 
	 case 3: 
	 display_DairyProducts(cart,conn,scan,customerID); 
	 break; 
	 case 4: 
	 System.out.println("Going back to groceries menu...");  return; 
	 default: 
	 System.out.println("Invalid choice. Please try again.");  } 
	 } while (choice != 4); 
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


	 public static void displayFruits(Connection conn) {
		    displayCategory(conn, "Fruits");
		}

		public static void displayVegetables(Connection conn) {
		    displayCategory(conn, "Vegetables");
		}

		public static void displayDairyProducts(Connection conn) {
		    displayCategory(conn, "Dairy_Products");
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

		void display_Fruits(ShoppingCart cart, Connection conn, Scanner scanner,int customerID) {
		    int choice;
		    do {
//		        System.out.println("Menu of Fruits:");
//		        System.out.println("1. Apple");
//		        System.out.println("2. Banana");
//		        System.out.println("3. Orange");
//		        System.out.println("4. Mango");
//		        System.out.println("5. Pineapple");
//		        System.out.println("6. Go back to previous menu");
		    	displayFruits(conn);
		        System.out.println("6. Go back to previous men");
		        System.out.print("Enter your choice: ");
		        
		        choice = scanner.nextInt();
		        scanner.nextLine(); // Consume newline

		        String productName = null;
		        switch (choice) {
		            case 1: productName = "Apple"; break;
		            case 2: productName = "Banana"; break;
		            case 3: productName = "Orange"; break;
		            case 4: productName = "Mango"; break;
		            case 5: productName = "Pineapple"; break;
		            case 6: return; // Return to previous menu
		            default:
		                System.out.println("Invalid choice. Please enter a number between 1 and 6.");
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

		    } while (choice != 6);
		}

		void display_Vegetables(ShoppingCart cart, Connection conn, Scanner scanner,int customerID) {
		    int choice;
		    do {
//		    	 System.out.println("Menu of Vegetables:"); 
//		    	 System.out.println("1. Spinach"); 
//		    	 System.out.println("2. Carrots"); 
//		    	 System.out.println("3. Potatoes"); 
//		    	 System.out.println("4. Cauliflower"); 
//		    	 System.out.println("5. Cabbage"); 
//		    	 System.out.println("6. Go back to previous menu"); 
		    	
		    	displayVegetables(conn);
		    	System.out.println("6. Go back to previous menu");
		        System.out.print("Enter your choice: ");
		        
		        choice = scanner.nextInt();
		        scanner.nextLine(); // Consume newline

		        String productName = null;
		        switch (choice) {
		            case 1: productName = "Spinach"; break;
		            case 2: productName = "Carrots"; break;
		            case 3: productName = "Potatoes"; break;
		            case 4: productName = "Cauliflower"; break;
		            case 5: productName = "Cabbage"; break;
		            case 6: return; // Return to previous menu
		            default:
		                System.out.println("Invalid choice. Please enter a number between 1 and 6.");
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

		    } while (choice != 6);
		}

	 
		void display_DairyProducts(ShoppingCart cart, Connection conn, Scanner scanner,int customerID) {
		    int choice;
		    do {

//		    	 System.out.println("Menu of dairyproducts:"); 
//		    	 System.out.println("1. Milk"); 
//		    	 System.out.println("2. Cheese"); 
//		    	 System.out.println("3. Yoghurt"); 
//		    	 System.out.println("4. Butter"); 
//		    	 System.out.println("5. Eggs"); 

		    	
		    	displayDairyProducts(conn);
		    	System.out.println("6. Go back to previous menu"); 
		    	
		        System.out.print("Enter your choice: ");
		        
		        choice = scanner.nextInt();
		        scanner.nextLine(); // Consume newline

		        String productName = null;
		        switch (choice) {
		            case 1: productName = "Milk"; break;
		            case 2: productName = "Cheese"; break;
		            case 3: productName = "Yoghurt"; break;
		            case 4: productName = "Butter"; break;
		            case 5: productName = "Eggs"; break;
		            case 6: return; // Return to previous menu
		            default:
		                System.out.println("Invalid choice. Please enter a number between 1 and 6.");
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
	
