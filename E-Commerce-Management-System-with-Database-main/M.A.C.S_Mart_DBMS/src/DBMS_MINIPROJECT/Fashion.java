package DBMS_MINIPROJECT;
import java.util.*;
import java.sql.*;
//class Fashion extends Mart { 
//	 Scanner scanner = new Scanner(System.in);  //FItem fitem = new FItem(); 
//	 Item it = new Item(); 
//	 public void searchItems() {
//	 // Implement search functionality 
//	 it.accept(); 
//	 System.out.print("Enter the item to search: "); 
//	 String searchItem = scanner.next(); // use next() instead of nextLine()   
//	 // Call the search method and display the result 
//	 boolean found = searchItem(it.itemlist, searchItem); 
//	 if (found) { 
//	 System.out.println(searchItem + " is found in the list.");  } else { 
//	 System.out.println(searchItem + " is not found in the list.");  } 
//	 } 
//	 public static boolean searchItem(ArrayList<String> items, String searchItem) {  for (String item : items) { 
//	 if (item.equals(searchItem)) { 
//	 return true; 
//	 } 
//	 } 
//	 return false; 
//	 } 
//	 void display_menu(ShoppingCart cart) { 
//	 int ch; 
//	 do { 
//	 System.out.println("****Fashion Menu****"); 
//	 System.out.println("1. Men's wear"); 
//	 System.out.println("2. Women's Wear"); 
//	 System.out.println("3. Kid's Section"); 
//	 System.out.println("4. Search for Specific clothing ");
//	 System.out.println("5. Display clothing chart");  System.out.println("6. View Cart"); 
//	 System.out.println("7. Go Back to Main Menu");  System.out.println("Enter your choice: ");  ch = scanner.nextInt(); 
//	 switch (ch) { 
//	 case 1: 
//	 menswear(cart); 
//	 break; 
//	 case 2: 
//	 womenswear(cart); 
//	 break; 
//	 case 3: 
//	 kidswear(cart); 
//	 break; 
//	 case 4: 
//	 searchItems(); 
//	 break; 
//	 case 5: 
//	 displaychart(); 
//	 break; 
//	  
//	 case 6: 
//	 cart.display_menu(); 
//	 break; 
//	  
//	 case 7: 
//	 break; 
//	 default:
//	 System.out.println("Invalid choice. Please enter a number between 1 and 5.");  break; 
//	 } 
//	 } while (ch != 7); 
//	 } 
//	  
//	 public void menswear(ShoppingCart cart) { 
//	 int ch; 
//	 do { 
//	 System.out.println("1. Formal wear"); 
//	 System.out.println("2. Tshirts and jeans "); 
//	 System.out.println("3. Indian wear "); 
//	 //System.out.println("Sports wear "); can add this one also, was not sue what to add  in this 
//	 System.out.println("4. Go Back to Main Menu "); 
//	 System.out.print("Enter your choice: "); 
//	 ch = scanner.nextInt(); 
//	 switch (ch) { 
//	 case 1: 
//	 displayformalwear(cart); 
//	 break; 
//	 case 2: 
//	 displaytandj(cart); 
//	 break; 
//	 case 3: 
//	 displayIndianswear(cart); 
//	 break; 
//	 case 4: 
//	 System.out.println("Going back to main menu..."); 
//	 return;
//	 default: 
//	 System.out.println("Invalid choice. Please enter a number between 1 and 4.");  break; 
//	 } 
//	 } while (ch != 4); 
//	 } 
//	 public void displayformalwear(ShoppingCart cart) { 
//	 int choice; 
//	 do { 
//	 System.out.println("1. Enter to display formal wear 2. Enter to go back to main  menu"); 
//	 choice = scanner.nextInt(); 
//	  
//	 switch (choice) { 
//	 case 1: 
//	 int choice0; 
//	 do { 
//	 System.out.println("1. Shirts"); 
//	 System.out.println("2. Trousers"); 
//	 System.out.println("3. Blazers"); 
//	 System.out.println("4. Ties"); 
//	 System.out.println("5. Go back to main menu"); 
//	 System.out.println("Enter your choice: "); 
//	 choice0 = scanner.nextInt(); 
//	 switch (choice0) { 
//	 case 1: 
//	 System.out.println("Enter the color: "); 
//	 String color = scanner.next(); 
//	 System.out.println("Enter the shirt size (for ex. S, M, XL): ");  String size = scanner.next();
//	 System.out.println("Enter the quantity: "); 
//	 int q = scanner.nextInt(); 
//	 // Add shirt to cart 
//	 cart.quantity.add(q); 
//	 cart.cartlist.add("Shirts"); 
//	 System.out.println("Shirt - Color: " + color + ", Size: " + size);  break; 
//	 case 2: 
//	 System.out.println("Enter the color: "); 
//	 String color1 = scanner.next(); 
//	 System.out.println("Enter the Trouser size (in inches): ");  int size1 = scanner.nextInt(); 
//	 System.out.println("Enter the quantity: "); 
//	 int q1 = scanner.nextInt(); 
//	 // Add trousers to cart 
//	 cart.quantity.add(q1); 
//	 cart.cartlist.add("Trousers"); 
//	 System.out.println("Trousers - Color: " + color1 + ", Size: " + size1 + ",  Quantity: " + q1); 
//	 break; 
//	 case 3: 
//	 System.out.println("Enter the color: "); 
//	 String color2 = scanner.next(); 
//	 System.out.println("Enter the Blazer size (for ex. S, M, XL): ");  String size2 = scanner.next(); 
//	 System.out.println("Enter the quantity: "); 
//	 int q2 = scanner.nextInt(); 
//	 // Add blazer to cart 
//	 cart.quantity.add(q2); 
//	 cart.cartlist.add("Blazers");
//	 System.out.println("Blazer - Color: " + color2 + ", Size: " + size2 + ",  Quantity: " + q2); 
//	 break; 
//	 case 4: 
//	 System.out.println("Enter the color: "); 
//	 String color3 = scanner.next(); 
//	 System.out.println("Enter the Tie length (in inches): ");  int length = scanner.nextInt(); 
//	 System.out.println("Enter the quantity: "); 
//	 int q3 = scanner.nextInt(); 
//	 // Add tie to cart 
//	 cart.quantity.add(q3); 
//	 cart.cartlist.add("Ties"); 
//	 System.out.println("Tie - Color: " + color3 + ", Length: " + length + " inches,  Quantity: " + q3); 
//	 break; 
//	 case 5: 
//	 break; 
//	 default: 
//	 System.out.println("Invalid choice. Please enter a number between 1 and  5."); 
//	 break; 
//	 } 
//	 } while (choice0 != 5); 
//	 break; 
//	 case 2: 
//	 break; 
//	 default: 
//	 System.out.println("Invalid choice. Please enter 1 or 2."); 
//	 break;
//	 } 
//	 } while (choice != 2); 
//	 } 
//	 public void displaytandj(ShoppingCart cart) { 
//	 int option; 
//	 do { 
//	 System.out.println("Enter what to buy: "); 
//	 System.out.println("1) Tshirt"); 
//	 System.out.println("2) Jeans"); 
//	 System.out.println("3) Go back to main menu"); 
//	 option = scanner.nextInt(); 
//	 switch (option) { 
//	 case 1: 
//	 System.out.println("Enter the color: "); 
//	 String color = scanner.next(); 
//	 System.out.println("Enter the Tshirt size (for ex. S, M, XL): ");  String size = scanner.next(); 
//	 System.out.println("Enter the quantity: "); 
//	 int quantity = scanner.nextInt(); 
//	 // Add Tshirt to cart 
//	 cart.quantity.add(quantity); 
//	 cart.cartlist.add("Tshirt"); 
//	 System.out.println("Tshirt - Color: " + color + ", Size: " + size + ", Quantity: " +  quantity); 
//	 break; 
//	 case 2: 
//	 System.out.println("Enter the color: "); 
//	 String color2 = scanner.next(); 
//	 System.out.println("Enter the jeans size (in inches): "); 
//	 int size2 = scanner.nextInt();
//	 System.out.println("Enter the quantity: "); 
//	 int quantity2 = scanner.nextInt(); 
//	 // Add Jeans to cart 
//	 cart.quantity.add(quantity2); 
//	 cart.cartlist.add("Jeans"); 
//	 System.out.println("Jeans - Color: " + color2 + ", Size: " + size2 + " inches, Quantity: "  + quantity2); 
//	 break; 
//	 case 3: 
//	 System.out.println("Going back to Main menu..."); 
//	 break; 
//	 default: 
//	 System.out.println("Invalid option. Please enter 1, 2, or 3."); 
//	 break; 
//	 } 
//	 } while (option != 3); 
//	} 
//	public void displayIndianswear(ShoppingCart cart) { 
//	 int option; 
//	 do { 
//	 System.out.println("1. Kurtas"); 
//	 System.out.println("2. Sherwani"); 
//	 System.out.println("3. Pajamas and churidars"); 
//	 System.out.println("4. Go back to main menu"); 
//	 System.out.print("Enter your choice: "); 
//	 option = scanner.nextInt(); 
//	 switch (option) { 
//	 case 1: 
//	 System.out.println("Enter the color: "); 
//	 String color = scanner.next();
//	 System.out.println("Enter the Kurta size (for ex. S, M, XL): "); 
//	 String size = scanner.next(); 
//	 System.out.println("Enter the quantity: "); 
//	 int quantity = scanner.nextInt(); 
//	 // Add Kurta to cart 
//	 cart.quantity.add(quantity); 
//	 cart.cartlist.add("Kurta"); 
//	 System.out.println("Kurta - Color: " + color + ", Size: " + size + ", Quantity: " +  quantity); 
//	 break; 
//	 case 2: 
//	 System.out.println("Enter the color: "); 
//	 String color2 = scanner.next(); 
//	 System.out.println("Enter the Sherwani size (for ex. S, M, XL): ");  String size2 = scanner.next(); 
//	 System.out.println("Enter the quantity: "); 
//	 int quantity2 = scanner.nextInt(); 
//	 // Add Sherwani to cart 
//	 cart.quantity.add(quantity2); 
//	 cart.cartlist.add("Sherwani"); 
//	 System.out.println("Sherwani - Color: " + color2 + ", Size: " + size2 + ", Quantity: " +  quantity2); 
//	 break; 
//	 case 3: 
//	 System.out.println("Enter the color: "); 
//	 String color3 = scanner.next(); 
//	 System.out.println("Enter the Pyjama size (in inches): "); 
//	 int size3 = scanner.nextInt(); 
//	 System.out.println("Enter the quantity: "); 
//	 int quantity3 = scanner.nextInt();
//	 // Add Pyjama to cart 
//	 cart.quantity.add(quantity3); 
//	 cart.cartlist.add("Pajama and churidars"); 
//	 System.out.println("Pyjama - Color: " + color3 + ", Size: " + size3 + " inches, Quantity:  " + quantity3); 
//	 break; 
//	 case 4: 
//	 System.out.println("Going back to Main menu..."); 
//	 break; 
//	 default: 
//	 System.out.println("Invalid choice. Please enter a number between 1 and 4.");  break; 
//	 } 
//	 } while (option != 4); 
//	} 
//	 public void womenswear(ShoppingCart cart) { 
//	 int option2; 
//	 do { 
//	 System.out.println("****In Women's Category****"); 
//	 System.out.println("1) Tops and Jeans"); 
//	 System.out.println("2) Kurtis/Salwars"); 
//	 System.out.println("3) Sarees"); 
//	 // System.out.println("Sports wear "); 
//	 System.out.println("4) Dresses"); 
//	 System.out.println("5) Go back to Main menu"); 
//	 System.out.println("Enter your choice: "); 
//	 option2 = scanner.nextInt(); 
//	 switch (option2) { 
//	 case 1: 
//	 displaytops(cart);
//	 break; 
//	 case 2: 
//	 displaykurtis(cart); 
//	 break; 
//	 case 3: 
//	 displaysarees(cart); 
//	 break; 
//	 case 4: 
//	 displaydresses(cart); 
//	 break; 
//	 case 5: 
//	 System.out.println("Going back to Main menu...."); 
//	 break; 
//	 default: 
//	 System.out.println("Invalid choice. Please enter a number between 1 and 5.");  break; 
//	 } 
//	 } while (option2 != 5); 
//	 } 
//	  
//	public void displaytops(ShoppingCart cart) { 
//	 int choice2; 
//	 do { 
//	 System.out.println("Enter what do you want to buy: "); 
//	 System.out.println("1) Tops"); 
//	 System.out.println("2) Jeans"); 
//	 System.out.println("3) Go back to Main menu"); 
//	 choice2 = scanner.nextInt(); 
//	 switch (choice2) {
//	 case 1: 
//	 System.out.println("--------------------------------");  
//	 System.out.println("| Different Types of Tops |"); 
//	 System.out.println("| Patterns: |"); 
//	 System.out.println("-----------------------------"); 
//	 System.out.println("| |"); 
//	 System.out.println("| 1. Crop Top |"); 
//	 System.out.println("| 2. Tank Top |"); 
//	 System.out.println("| 3. Off-Shoulder Top |"); 
//	 System.out.println("| 4. Peplum Top |"); 
//	 System.out.println("| 5. Wrap Top |"); 
//	 System.out.println("--------------------------------"); 
//	 System.out.println("Enter the pattern of top: "); 
//	 String pattern = scanner.next(); 
//	 System.out.println("Enter the color: "); 
//	 String color = scanner.next(); 
//	 System.out.println("Enter the Top size (for ex. S, M, XL): "); 
//	 String size = scanner.next(); 
//	 System.out.println("Enter the quantity: "); 
//	 int quantity = scanner.nextInt(); 
//	 // Add top to cart 
//	 cart.quantity.add(quantity); 
//	 cart.cartlist.add("Tops"); 
//	 System.out.println("Top - Pattern: " + pattern + ", Color: " + color + ", Size: " + size +  ", Quantity: " + quantity); 
//	 break; 
//	 case 2: 
//	 System.out.println("Enter the color: "); 
//	 String color2 = scanner.next(); 
//	 System.out.println("Enter the Jeans size (in inches): ");
//	 int size2 = scanner.nextInt(); 
//	 System.out.println("Enter the quantity: "); 
//	 int quantity2 = scanner.nextInt(); 
//	 // Add jeans to cart 
//	 cart.quantity.add(quantity2); 
//	 cart.cartlist.add("Jeans"); 
//	 System.out.println("Jeans - Color: " + color2 + ", Size: " + size2 + " inches, Quantity: "  + quantity2); 
//	 break; 
//	 case 3: 
//	 System.out.println("Going back to main menu..."); 
//	 break; 
//	 default: 
//	 System.out.println("Invalid option. Please enter 1, 2, or 3."); 
//	 break; 
//	 } 
//	 } while (choice2 != 3); 
//	} 
//	  
//	 public void displaykurtis(ShoppingCart cart) { 
//	 int option3 = 0; 
//	 do { 
//	 System.out.println("1. Enter to display Kurtis 2. Enter to go back to main menu");  option3 = scanner.nextInt(); 
//	 switch(option3) 
//	 { 
//	 case 1: 
//	 System.out.println("Enter the color: "); 
//	 String color4 = scanner.next(); 
//	 System.out.println("Enter the Kurti size(for ex. S,M,XL): ");
//	 String size4 = scanner.next(); 
//	  
//	 System.out.println("Enter the quantity: "); 
//	 int q4 = scanner.nextInt(); 
//	 cart.quantity.add(q4); 
//	 cart.cartlist.add("Kurtis"); 
//	 System.out.println("Kurti: Color"+color4+"Size: "+size4); 
//	 break; 
//	  
//	  
//	 case 2: 
//	 break; 
//	 } 
//	 }while (option3 != 2); 
//	 } 
//	 public void displaysarees(ShoppingCart cart) { 
//	 int option4; 
//	 do { 
//	 System.out.println("1. Enter to display Sarees 2. Enter to go back to main menu");  option4 = scanner.nextInt(); 
//	 switch (option4) { 
//	 case 1: 
//	 System.out.println("---------------------------"); 
//	 System.out.println("| Saree Choices |"); 
//	 System.out.println("-----------------------------"); 
//	 System.out.println("| 1. Silk Saree |"); 
//	 System.out.println("| 2. Cotton Saree |"); 
//	 System.out.println("| 3. Georgette Saree |"); 
//	 System.out.println("| 4. Chiffon Saree |");
//	 System.out.println("| 5. Banarasi Saree |"); 
//	 System.out.println("-----------------------------"); 
//	 System.out.println("Enter the pattern of saree: "); 
//	 String pattern = scanner.next(); 
//	 System.out.println("Enter the color: "); 
//	 String color = scanner.next(); 
//	 System.out.println("Enter the quantity: "); 
//	 int quantity = scanner.nextInt(); 
//	 // Add saree to cart 
//	 cart.quantity.add(quantity); 
//	 cart.cartlist.add("Saree"); 
//	 System.out.println("Saree - Pattern: " + pattern + ", Color: " + color + ", Quantity: " +  quantity); 
//	 break; 
//	 case 2: 
//	 break; 
//	 default: 
//	 System.out.println("Invalid choice. Please enter 1 or 2."); 
//	 break; 
//	 } 
//	 } while (option4 != 2); 
//	} 
//	public void displaydresses(ShoppingCart cart) { 
//	 int option5; 
//	 do { 
//	 System.out.println("1. Enter to display dresses 2. Enter to go back to main menu");  option5 = scanner.nextInt(); 
//	 switch (option5) { 
//	 case 1: 
//	 System.out.println("---------------------------");
//	 System.out.println("| Dress Choices |"); 
//	 System.out.println("----------------------------"); 
//	 System.out.println("| 1. Maxi Dress |"); 
//	 System.out.println("| 2. Midi Dress |"); 
//	 System.out.println("| 3. Shift Dress |"); 
//	 System.out.println("| 4. A-Line Dress |"); 
//	 System.out.println("| 5. Wrap Dress |"); 
//	 System.out.println("-----------------------------"); 
//	 System.out.println("Enter the dress pattern: "); 
//	 String pattern = scanner.next(); 
//	 System.out.println("Enter the color: "); 
//	 String color = scanner.next(); 
//	 System.out.println("Enter the dress size (for ex. S, M, XL): "); 
//	 String size = scanner.next(); 
//	 System.out.println("Enter the quantity: "); 
//	 int quantity = scanner.nextInt(); 
//	 // Add dress to cart 
//	 cart.quantity.add(quantity); 
//	 cart.cartlist.add("Dress"); 
//	 System.out.println ("Dress - Pattern: " + pattern + ", Color: " + color + ", Size: " + size  + ", Quantity: " + quantity); 
//	 break; 
//	 case 2: 
//	 System.out.println("Going back to Main menu...."); 
//	 break; 
//	 default: 
//	 System.out.println("Invalid choice. Please enter 1 or 2."); 
//	 break; 
//	 } 
//	 } while (option5 != 2);
//	} 
//	 public void kidswear(ShoppingCart cart) { 
//	 int option6=0; 
//	 do { 
//	 System.out.println("1. Baby clothes"); 
//	 System.out.println("2. Girls Clothing"); 
//	 System.out.println("3. Boy's Clothing"); 
//	 System.out.println("4. Go back to main menu"); 
//	 System.out.print("Enter your choice: "); 
//	 option6 = scanner.nextInt(); 
//	 switch (option6) { 
//	 case 1: 
//	 displaybabywear(cart); 
//	 break; 
//	 case 2: 
//	 displaygirlsclothes(cart); 
//	 break; 
//	 case 3: 
//	 displayboysclothing(cart); 
//	 break; 
//	 case 4: 
//	 break; 
//	 default: 
//	 System.out.println("Invalid choice. Please enter a number between 1 and 4.");  break; 
//	 } 
//	 } while (option6 != 4); 
//	 } 
//	public void displaybabywear(ShoppingCart cart) {
//	 int option7; 
//	 do { 
//	 System.out.println("1. Enter to display babywear 2. Enter to go back to main menu");  option7 = scanner.nextInt(); 
//	  
//	 switch (option7) { 
//	 case 1: 
//	 System.out.println("------------------------------"); 
//	 System.out.println("| Baby Wear Choices |"); 
//	 System.out.println("-----------------------------"); 
//	 System.out.println("| 1. Onesie |"); 
//	 System.out.println("| 2. Romper |"); 
//	 System.out.println("| 3. Jumpsuit |"); 
//	 System.out.println("| 4. Baby Gown |"); 
//	 System.out.println("| 5. Baby T-Shirt |"); 
//	 System.out.println("--------------------------------"); 
//	 System.out.println("Enter your desired choice: "); 
//	 String babypattern = scanner.next(); 
//	 System.out.println("Enter the color: "); 
//	 String color4 = scanner.next(); 
//	 System.out.println("Enter the dress size (for ex. S, M, XL): ");  String size4 = scanner.next(); 
//	 System.out.println("Enter the quantity: "); 
//	 int q4 = scanner.nextInt(); 
//	 // Add babywear to cart 
//	 cart.quantity.add(q4); 
//	 cart.cartlist.add("Babywear"); 
//	 System.out.println("Babywear - Pattern: " + babypattern + ", Color: " + color4 + ",  Size: " + size4 + ", Quantity: " + q4); 
//	 break;
//	 case 2: 
//	 break; 
//	 } 
//	 } while (option7 != 2); 
//	} 
//	public void displaygirlsclothes(ShoppingCart cart) { 
//	 int option8; 
//	 do { 
//	 System.out.println("1. Enter to display girl's clothes 2. Enter to go back to main menu");  option8 = scanner.nextInt(); 
//	  
//	 switch (option8) { 
//	 case 1: 
//	 System.out.println("----------------------------"); 
//	 System.out.println("| Little Girl's Clothing |"); 
//	 System.out.println("-----------------------------"); 
//	 System.out.println("| 1. Dress |"); 
//	 System.out.println("| 2. Skirt |"); 
//	 System.out.println("| 3. T-shirt |"); 
//	 System.out.println("--------------------------------"); 
//	 System.out.println("Enter your desired choice: "); 
//	 String girlpattern = scanner.next(); 
//	 System.out.println("Enter the color: "); 
//	 String color4 = scanner.next(); 
//	 System.out.println("Enter the dress size (for ex. S, M, XL): "); 
//	 String size4 = scanner.next(); 
//	 System.out.println("Enter the quantity: "); 
//	 int q4 = scanner.nextInt(); 
//	 // Add girl's clothes to cart
//	 cart.quantity.add(q4); 
//	 cart.cartlist.add("girlswear"); 
//	 System.out.println("Girl's Clothes - Pattern: " + girlpattern + ", Color: " + color4 + ",  Size: " + size4 + ", Quantity: " + q4); 
//	 break; 
//	 case 2: 
//	 break; 
//	 } 
//	 } while (option8 != 2); 
//	} 
//	public void displayboysclothing(ShoppingCart cart) { 
//	 int option9; 
//	 do { 
//	 System.out.println("1. Enter to display boy's clothes 2. Enter to go back to main menu");  option9 = scanner.nextInt(); 
//	  
//	 switch (option9) { 
//	 case 1: 
//	 System.out.println("------------------------------"); 
//	 System.out.println("| Little Boy's Clothing |"); 
//	 System.out.println("-----------------------------"); 
//	 System.out.println("| 1. T-shirt |"); 
//	 System.out.println("| 2. Shirt |"); 
//	 System.out.println("| 3. Shorts |"); 
//	 System.out.println("-----------------------------"); 
//	 System.out.println("Enter your desired choice: "); 
//	 String Boypattern = scanner.next(); 
//	 System.out.println("Enter the color: "); 
//	 String color4 = scanner.next(); 
//	 System.out.println("Enter the dress size (for ex. S, M, XL): ");
//	 String size4 = scanner.next(); 
//	 System.out.println("Enter the quantity: "); 
//	 int q4 = scanner.nextInt(); 
//	 // Add boy's clothing to cart 
//	 cart.quantity.add(q4); 
//	 cart.cartlist.add("Boyswear"); 
//	 System.out.println("Boy's Clothing - Pattern: " + Boypattern + ", Color: " + color4 + ",  Size: " + size4 + ", Quantity: " + q4); 
//	 break; 
//	 case 2: 
//	 break; 
//	 } 
//	 } while (option9 != 2); 
//	} 
//	 public void displaychart() 
//	 { 
//	 int ch3=0; 
//	 do{ 
//	 System.out.println("1. Men's clothing chart 2. Women's Clothing chart 3. Go back to  Main menu"); 
//	 System.out.println("Enter your choice: "); 
//	 ch3=scanner.nextInt(); 
//	  
//	 switch(ch3) 
//	 { 
//	  
//	  
//	 case 1:
//	 System.out.println("------------------------------------");  System.out.println("| Men's Clothing Size Chart |");  System.out.println("------------------------------------");  System.out.println("| Shirt Sizes Trouser Sizes |");  System.out.println("| S: 36-38\" S: 28-30\" |");  System.out.println("| M: 39-41\" M: 32-34\" |");  System.out.println("| L: 42-44\" L: 36-38\" |");  System.out.println("| XL: 45-48\" XL: 40-42\" |");  System.out.println("| XXL: 49-52\" XXL: 44-46\" |");  System.out.println("| |");  System.out.println("| Blazer Sizes Tie Length |");  System.out.println("| S: 36-38\" Regular: 58\" |");  System.out.println("| M: 40-42\" Long: 62\" |");  System.out.println("| L: 44-46\" |");  System.out.println("| XL: 48-50\" |");  System.out.println("| XXL: 52-54\" |");  System.out.println("------------------------------------");   
//	 break; 
//	  
//	  
//	  
//	 case 2: 
//	 System.out.println("------------------------------------");  System.out.println("| Women's Clothing Size Chart |");  System.out.println("------------------------------------");  System.out.println("| Tops & Jeans Kurtis/Salwars |");  System.out.println("| Tops: S-XL Kurtis: S-XL |");  System.out.println("| Jeans: 26-32\" Salwars: S-XL |");
//	 System.out.println("| |");  System.out.println("| Sarees Dresses |");  System.out.println("| Free Size S-XL |");  System.out.println("| |");  System.out.println("| Baby Clothes Girls Clothing |");  System.out.println("| 0-24 Months 2T-16 Years |");  System.out.println("| |");  System.out.println("| Boy's Clothing |");  System.out.println("| 2T-20 Years |");  System.out.println("------------------------------------");   
//	  
//	 break; 
//	  
//	 case 3: 
//	 break; 
//	  
//	 default: 
//	 System.out.println("Please, Enter a valid choice.");  } 
//	 } 
//	 while(ch3!=3); 
//	  
//	  
//	  
//	 } 
//	} 
//	 /*public void fsearchItems() { 
//	 System.out.print("Enter the item to search: ");
//	 String fsearchItem = scanner.next(); 
//	 // Call the search method on the fitem instance 
//	 boolean found = fitem.fsearchItem(fitem.fitemlist, fsearchItem);  if (found) { 
//	 System.out.println(fsearchItem + " is found in the list.");  } else { 
//	 System.out.println(fsearchItem + " is not found in the list.");  } 
//	 } 
//	}*/ 
//	/*class FItem { 
//	 ArrayList<String> fitemlist = new ArrayList<>(); 
//	 public boolean fsearchItem(ArrayList<String> fitems, String fsearchItem) {  for (String fitem : fitems) { 
//	 if (fitem.equals(fsearchItem)) { 
//	 return true; 
//	 } 
//	 } 
//	 return false; 
//	 } 
//	}*/ 



class Fashion extends Mart { 
	
	  
	 void display_menu(ShoppingCart cart,Connection conn,Scanner scan,int customerID) { 
	 int ch=0; 
	 do { 
	 System.out.println("Fashion Menu:"); 
	 System.out.println("1. Browse Fashion");
	 System.out.println("2. Search for Specific Items");  
	 System.out.println("3. View Cart"); 
	 System.out.println("4. Go Back to Main Menu");  
	 System.out.print("Enter your choice: "); 
	 ch = scan.nextInt(); 
	 scan.nextLine();
	 switch (ch) { 
	 case 1: 
		 
	 browseFashion(cart,conn,scan,customerID); 
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
	 public void browseFashion(ShoppingCart cart,Connection conn,Scanner scan,int customerID) {  
		 int choice; 
	 do { 
	 System.out.println("Browse Fashion:"); 
	 System.out.println("1. Men's wear"); 
	 System.out.println("2. Women's Wear"); 
	 System.out.println("3. Kid's Section");  
	 System.out.println("4. Go Back to Fashion Menu");  System.out.print("Enter your choice: "); 
	 choice = scan.nextInt(); 
	 switch (choice) { 
	 case 1: 
	 display_MensWear(cart,conn,scan,customerID); 
	 break; 
	 case 2: 
	 display_WomensWear(cart,conn,scan,customerID); 
	 break; 
	 case 3: 
	 display_KidsSection(cart,conn,scan,customerID); 
	 break; 
	 case 4: 
	 System.out.println("Going back to Fashion menu...");  return; 
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


	 public static void displayMensWear(Connection conn) {
		    displayCategory(conn, "Mens_Wear");
		}

		public static void displayWomensWear(Connection conn) {
		    displayCategory(conn, "Womens_Wear");
		}

		public static void displayKidsSection(Connection conn) {
		    displayCategory(conn, "Kids_Section");
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

		void display_MensWear(ShoppingCart cart, Connection conn, Scanner scanner,int customerID) {
		    int choice;
		    do {
//		        ;
		    	displayMensWear(conn);
		        System.out.println("10. Go back to previous menu");
		        System.out.print("Enter your choice: ");
		        
		        choice = scanner.nextInt();
		        scanner.nextLine(); // Consume newline

		        String productName = null;
		        switch (choice) {
		        case 1: productName = "Shirts"; break;
		        case 2: productName = "Trousers"; break;
		        case 3: productName = "Blazers"; break;
		        case 4: productName = "Ties"; break;
		        case 5: productName = "Tshirt"; break;
		        case 6: productName = "Jeans"; break;
		        case 7: productName = "Kurta"; break;
		        case 8: productName = "Sherwani"; break;
		        case 9: productName = "Pajama and churidars"; break;
		        case 10: return; // Return to previous menu
		        default:
		            System.out.println("Invalid choice. Please enter a number between 1 and 10.");
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

		    } while (choice != 10);
		}

		void display_WomensWear(ShoppingCart cart, Connection conn, Scanner scanner,int customerID) {
		    int choice;
		    do {
//		    	 
		    	displayWomensWear(conn);
		    	System.out.println("5. Go back to previous menu");
		        System.out.print("Enter your choice: ");
		        
		        choice = scanner.nextInt();
		        scanner.nextLine(); // Consume newline

		        String productName = null;
		        switch (choice) {
		        case 1: productName = "Tops"; break;                
		        case 2: productName = "Kurtis"; break;              
		        case 3: productName = "Saree"; break;               
		        case 4: productName = "Dress"; break;               
		        case 5: return; // Return to previous menu
		        default:
		            System.out.println("Invalid choice. Please enter a number between 1 and 5.");
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

		    } while (choice != 5);
		}

	 
		void display_KidsSection(ShoppingCart cart, Connection conn, Scanner scanner,int customerID) {
		    int choice;
		    do {

//		    	 

		    	
		    	displayKidsSection(conn);
		    	System.out.println("4. Go back to previous menu"); 
		    	
		        System.out.print("Enter your choice: ");
		        
		        choice = scanner.nextInt();
		        scanner.nextLine(); // Consume newline

		        String productName = null;
		        switch (choice) {
		        case 1: productName = "Babywear"; break;      
		        case 2: productName = "Girlswear"; break;    
		        case 3: productName = "Boyswear"; break;     
		        case 4: return; // Return to previous menu
		        default:
		            System.out.println("Invalid choice. Please enter a number between 1 and 4.");
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

		    } while (choice != 4);
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
	

