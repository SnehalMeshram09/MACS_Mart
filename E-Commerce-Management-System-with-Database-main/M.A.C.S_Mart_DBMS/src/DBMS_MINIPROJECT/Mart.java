package DBMS_MINIPROJECT;

import java.sql.*;
import java.sql.Date;
import java.util.*;
class Mart {


void display_menu(ShoppingCart cart,Connection conn, Scanner scan,int customerID) {
int choice;
do {
System.out.println("Welcome to M.A.C.S. MART! \n Menu:");
System.out.println("1. Groceries");
System.out.println("2. Fashion");
System.out.println("3. Appliances and Electronics");
System.out.println("4. Stationary ");
System.out.println("5. Exit");
System.out.print("Enter your choice: ");
choice = scan.nextInt();
switch (choice) {
case 1:

Groceries grocery = new Groceries();
grocery.display_menu(cart,conn,scan,customerID);
break;
case 2:
//Fashion fashion = new Fashion();
//fashion.display_menu(cart);
break;
case 3:
Electricals electricals = new Electricals();
electricals.display_menu(cart,conn,scan,customerID);
break;
case 4:
Stationary stationary = new Stationary();
stationary.display_menu(cart,conn,scan,customerID);
break;
case 5:
System.out.println("Exiting..");
System.out.println("Thank you for Shopping! Visit Again!");
break;
default:
System.out.println("Invalid choice. Please try again.");
}
} while (choice != 5);

//try {
//	if (conn != null) conn.close();
//	//scan.close();
//} 
//catch (SQLException se) {
//	se.printStackTrace();
//}
}
}