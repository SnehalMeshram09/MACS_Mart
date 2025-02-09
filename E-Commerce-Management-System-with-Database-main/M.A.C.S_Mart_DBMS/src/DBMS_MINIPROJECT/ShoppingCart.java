package DBMS_MINIPROJECT;
import java.util.*;
import java.sql.*;
import java.sql.Date;
class ShoppingCart{ 
	
	public static void displayCart(Connection conn,int customerID) {
	    // SQL query to retrieve cart items for a given customer
	    String query = "SELECT p.name, p.price, p.description, ci.quantity " +
	                   "FROM cart c " +
	                   "JOIN cart_item ci ON c.cart_id = ci.cart_id " +
	                   "JOIN product p ON ci.product_id = p.product_id " +
	                   "WHERE c.customer_id = ?";
	    // Optional: filter by customer_id
//	    String getProductIDQuery = "SELECT product_id FROM product WHERE name = ?";
//	    PreparedStatement getProductIDStmt = conn.prepareStatement(getProductIDQuery);
//	    getProductIDStmt.setString(1, productName);
//	    ResultSet rs = getProductIDStmt.executeQuery();
//	    int productId = rs.getInt("product_id");
//	    
	    try (PreparedStatement stmt = conn.prepareStatement(query)) {
	        // Optional: If you want to filter by a specific customer_id, set it like this:
	        stmt.setInt(1, customerID);
	    	
	    	//stmt.setInt(1, 1);

	        // Execute the query and get the result set
	        try (ResultSet rs = stmt.executeQuery()) {
	            // Print the header for the table
	            System.out.printf("%-15s %-10s %-20s %-10s%n", "Product Name", "Price", "Description", "Quantity");
	            System.out.println("---------------------------------------------------------------");

	            // Process the result set and print each row
	            while (rs.next()) {
	                String productName = rs.getString("name");
	                double price = rs.getDouble("price");
	                String description = rs.getString("description");
	                int quantity = rs.getInt("quantity");

	                // Print product details in a formatted table
	                System.out.printf("%-15s %-9.2f %-20s %-10d%n",
	                                  productName, price, description, quantity);
	            }
	        } catch (SQLException e) {
	            // Handle exceptions related to the ResultSet or query execution
	            System.err.println("Error processing cart data: " + e.getMessage());
	        }
	    } catch (SQLException e) {
	        // Handle exceptions related to the PreparedStatement or database connection
	        System.err.println("Error displaying cart data: " + e.getMessage());
	    }
	}



	 void display_menu(Connection conn,Scanner scan,int customerID) { 
	 int choice=0; 
	  

	 System.out.println("1.Add to Cart"); 
	 System.out.println("2.Remove from cart"); 
	 System.out.println("3.Display Cart"); 
	 System.out.println("4.Display bill"); 
	 System.out.println("Enter your choice:"); 
	 choice=scan.nextInt();
	 scan.nextLine();
	 switch(choice) 
	 { 
	  
	 case 1: 
	 break; 
	 case 2: 
		 removeFromCart(conn,scan,customerID); 
	 break; 
	  
	 case 3: 
	 displayCart(conn,customerID); 
	 break; 
	  
	 case 4: 
	 calculateBill(customerID,conn,scan);
	 break; 
	  
	 default: 
	 System.out.println("Please Enter a valid choice"); 
	  
	 } 
	  
	  
	 } 
	 public static void removeFromCart(Connection conn, Scanner scan, int CustomerID) {
		    System.out.print("Enter the Product Name to remove from cart: ");
		    String productName = scan.nextLine();

		    // Query to get the cart_id for the customer
		    String getCartIdQuery = "SELECT cart_id FROM cart WHERE Customer_ID = ?";
		    // Query to get the product_id based on the product name
		    String getProductIdQuery = "SELECT product_id FROM product WHERE name = ?";
		    // Query to delete the item from the cart_item table based on cart_id and product_id
		    String deleteQuery = "DELETE FROM cart_item WHERE cart_id = ? AND product_id = ?";

		    try (
		        PreparedStatement getCartIdStmt = conn.prepareStatement(getCartIdQuery);
		        PreparedStatement getProductIdStmt = conn.prepareStatement(getProductIdQuery);
		        PreparedStatement deleteStmt = conn.prepareStatement(deleteQuery)
		    ) {
		        // Get the cart_id for the customer
		        getCartIdStmt.setInt(1, CustomerID);
		        ResultSet cartResult = getCartIdStmt.executeQuery();
		        
		        if (cartResult.next()) {
		            int cartId = cartResult.getInt("cart_id");

		            // Get the product_id for the given product name
		            getProductIdStmt.setString(1, productName);
		            ResultSet productResult = getProductIdStmt.executeQuery();

		            if (productResult.next()) {
		                int productId = productResult.getInt("product_id");

		                // Now, delete the item from cart_item where cart_id and product_id match
		                deleteStmt.setInt(1, cartId);
		                deleteStmt.setInt(2, productId);
		                
		                int rowsAffected = deleteStmt.executeUpdate();
		                
		                if (rowsAffected > 0) {
		                    System.out.println("Product '" + productName + "' was successfully removed from the cart.");
		                } else {
		                    System.out.println("Product '" + productName + "' was not found in the cart.");
		                }
		            } else {
		                System.out.println("Product '" + productName + "' does not exist.");
		            }
		        } else {
		            System.out.println("No cart found for the customer with ID: " + CustomerID);
		        }

		    } catch (SQLException e) {
		        System.err.println("Error removing product from cart: " + e.getMessage());
		    }
		}


	

	     public static void calculateBill(int customerId, Connection conn,Scanner scan) {
	         
	         int paymentChoice;
	         // Step 1: Select Payment Method
	         System.out.println("Choose Payment Method:");
	         System.out.println("1. Credit Card");
	         System.out.println("2. Debit Card");
	         System.out.println("3. Cash");
	         System.out.println("4. Online Banking");
	         System.out.print("Enter choice (1-4): ");
	         
	         paymentChoice = scan.nextInt();
	         scan.nextLine();
	         String paymentMethod;
	         
	         switch (paymentChoice) {
	             case 1 :paymentMethod = "Credit Card"; break;
	             case 2 :paymentMethod = "Debit Card"; break;
	             case 3 :paymentMethod = "Cash"; break;
	             case 4 :paymentMethod = "Online Banking"; break;
	             default : {
	                 System.out.println("Invalid choice. Defaulting to Cash.");
	                 paymentMethod = "Cash";
	             }
	         }

	         try {
	             // Step 2: Retrieve the cart_id for the given customer
	             String getCartIdQuery = "SELECT cart_id FROM cart WHERE customer_id = ?";
	             PreparedStatement getCartIdStmt = conn.prepareStatement(getCartIdQuery);
	             getCartIdStmt.setInt(1, customerId);

	             
	             ResultSet cartResult = getCartIdStmt.executeQuery();
	             if (!cartResult.next()) {
	                 System.out.println("No cart found for the given customer.");
	                 return;
	             }

	             int cartId = cartResult.getInt("cart_id");

	             
	             // Step 3: Retrieve cart items with product details and calculate total
	             String getItemsQuery = " SELECT ci.product_id, p.name, p.description, ci.quantity, p.price, p.stock FROM cart_item ci JOIN product p ON ci.product_id = p.product_id WHERE ci.cart_id = ?";
	             PreparedStatement getItemsStmt = conn.prepareStatement(getItemsQuery);
	             getItemsStmt.setInt(1, cartId);

	             ResultSet itemsResult = getItemsStmt.executeQuery();

	          
	             double totalAmount = 0;
	             System.out.println("-------------------------------------------------------------------------------------------------");
	             System.out.printf("%-15s %-20s %-30s %-10s %-10s %-10s%n", "Product ID", "Product Name", "Description", "Quantity", "Price", "Amount");
	             System.out.println("-------------------------------------------------------------------------------------------------");

	             while (itemsResult.next()) {
	                 int productId = itemsResult.getInt("product_id");
	                 String productName = itemsResult.getString("name");
	                 String description = itemsResult.getString("description");
	                 int quantity = itemsResult.getInt("quantity");
	                 double price = itemsResult.getDouble("price");
	                 int stock = itemsResult.getInt("stock");
	                 
	                 // Calculate the amount for each product and add to the total amount
	                 double amount = quantity * price;
	                 totalAmount += amount;

	                 // Print each product's details in a row format
	                 System.out.printf("%-15d %-20s %-30s %-10d %-10.2f %-10.2f%n", productId, productName, description, quantity, price, amount);

	                 // Update stock in products table
	                 int newStock = stock - quantity;
	                 if (newStock < 0) {
	                     System.out.printf("Warning: Insufficient stock for product %s (ID: %d).%n", productName, productId);
	                 } else {
	                     String updateStockQuery = "UPDATE product SET stock = ? WHERE product_id = ?";
	                     PreparedStatement updateStockStmt = conn.prepareStatement(updateStockQuery);
	                     updateStockStmt.setInt(1, newStock);
	                     updateStockStmt.setInt(2, productId);
	                     updateStockStmt.executeUpdate();
	                 }
	             }

	             System.out.println("-------------------------------------------------------------------------------------------------");
	             System.out.printf("%-65s %-10.2f%n", "Total Bill Amount:", totalAmount);

	          // Step 4: Insert payment information into payment table with payment_date as NOW()
	             String insertPaymentQuery = "INSERT INTO payment (cart_id, payment_method, payment_status, payment_date) VALUES (?, ?, ?, NOW())";
	             PreparedStatement insertPaymentStmt = conn.prepareStatement(insertPaymentQuery);
	             insertPaymentStmt.setInt(1, cartId);
	             insertPaymentStmt.setString(2, paymentMethod);
	             insertPaymentStmt.setString(3, "Paid");
	             insertPaymentStmt.executeUpdate();

	             // Step 5: Confirm Payment Success
	             System.out.println("Payment successful! Bill saved to payment table with status 'Paid'.");

	         } catch (SQLException se) {
	             se.getMessage();
	         } catch (Exception e)
	         {
	        	 e.getMessage();
	         }
	         
	     }
	 }


	 
	 

