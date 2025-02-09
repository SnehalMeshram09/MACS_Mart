# E-Commerce-Management-System-with-Database
A simple Java Console based Mini Project with a MYSQL database

---

### Instructions


# macs_mart Database Export

This repository contains the exported SQL file for the **macs_mart** database. You can download the `.sql` file to restore the database on your local MySQL server.

## How to Download the `.sql` File

To download the `macs_mart.sql` file from this repository, follow these steps:

1. Go to the GitHub Repository:

   - Navigate to the repository where the `macs_mart.sql` file is stored.  
     [Link to the repository](https://github.com/chaitali-kulkarni1/E-Commerce-Management-System-with-Database)

2. Find the `.sql` file:
   - Locate the `macs_mart.sql` file in the repository's file list. The file should be visible in the root directory or in a folder, depending on where you uploaded it.

3. Download the `.sql` file:
   - Click on the `macs_mart.sql` file name to open it.
   - On the file preview page, you will see a "Download" button (usually found at the top right of the file preview).
   - Click **Download** to save the `.sql` file to your local machine.
     
## How to Import the Database into MySQL

Once you've downloaded the `.sql` file, you can import it into your MySQL database using one of the following methods:

### Method 1: Using MySQL Workbench

1. **Open MySQL Workbench** and connect to your MySQL server.
2. **Create a New Database**:
   - Click on **File > New Query Tab**.
   - Run the following SQL command to create a new database:
     ```markdown
     ```sql
     CREATE DATABASE macs_mart;
     ```
3. **Import the SQL File**:
   - Click on **Server > Data Import** in the menu.
   - Select **Import from Self-Contained File** and browse to the location where you downloaded `macs_mart.sql`.
   - Choose the newly created `macs_mart` database as the target.
   - Click **Start Import**.

### Method 2: Using Command Line (`mysqldump`)

1. Open a terminal or Command Prompt on your computer.
2. Run the following command to import the `.sql` file into your MySQL server:
   ```markdown
   ```bash
   mysql -u username -p macs_mart < /path/to/macs_mart.sql
   ```
   - Replace `username` with your MySQL username (e.g., `root`).
   - Replace `/path/to/macs_mart.sql` with the actual path to the `.sql` file you downloaded.

---

## Notes

- Ensure you have **MySQL** installed on your system before importing the database.
- If you're using **XAMPP**, **WAMP**, or a similar local server stack, make sure MySQL is running.
- If you encounter any issues during import, check for any errors that may appear in MySQL Workbench or your command line and refer to the error messages for more information.

For any questions or issues, please feel free to open an issue in this repository.



---
