# LibraryManagementSystem

## Table of Contents
- [Description](#description)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Contact](#contact)

## Description
The Library Management System is a comprehensive Java-based console application built to streamline the management of library operations. 
It offers functionalities to handle user registrations, maintain a robust inventory of books, and manage transactions efficiently. 
The system is designed with features that cater to the needs of both general users (borrowers) and administrators (library staff), making 
it a practical solution for small to medium-sized libraries.

This system eliminates the complexity of manual library management by leveraging digital records and an intuitive menu-driven interface. 
Users can register themselves, log in securely, browse available books, borrow or return books, and view their transaction history. 
Administrators, on the other hand, have exclusive rights to manage the book inventory by adding or removing books, ensuring the library 
catalog is up-to-date and accurate.

The system uses a file-based storage mechanism, with all data about users, books, and transactions being stored persistently in text files. 
This approach makes the system simple to set up and operate, without the need for external database dependencies. Additionally, it comes with 
a preloaded administrator account, enabling easy access for library staff to start managing the library immediately after deployment.

## Features

### 1. User Management
- **User Registration**:
  - Register new users by providing name, email, and phone number.
  - Automatically assigns a unique User ID upon successful registration.
  
- **User Login**:
  - Secure login using email and phone number to prevent unauthorized access.

- **User Logout**:
  - Allows users to securely end their session.

---

### 2. Book Inventory Management
- **View Books**:
  - Displays a list of all books in the library with details like:
    - Title
    - Author
    - Genre
    - Availability status.

- **Add Books (Admin Only)**:
  - Administrators can add new books to the library by providing:
    - Title
    - Author
    - Genre.
  - Automatically assigns a unique Book ID.

- **Remove Books (Admin Only)**:
  - Administrators can remove books from the library by providing the Book ID.
  - Ensures the catalog remains accurate and up-to-date.

---

### 3. Book Transactions
- **Issue Books**:
  - Borrow books by providing the Book ID (user must be logged in).
  - Automatically updates the book's availability and transaction history.

- **Return Books**:
  - Return borrowed books by providing the Book ID (user must be logged in).
  - Updates the book's availability and logs the return in transaction records.

- **View Transactions**:
  - Displays a complete history of transactions, including:
    - Transaction ID
    - Book ID
    - User ID
    - Issue Date
    - Return Date (if applicable)
    - Status (Issued or Returned).

---

### 4. Data Persistence
- **Data Loading**:
  - Automatically loads data from text files at startup:
    - `users.txt`: Stores user details.
    - `books.txt`: Stores book details.
    - `transactions.txt`: Stores transaction history.

- **Data Saving**:
  - Saves updated data back to text files before exiting to ensure data consistency across sessions.

---

### 5. Administrative Privileges
- **Preloaded Admin Account**:
  - Comes with a default administrator account for easy access:
    - Email: `admin@library.com`
    - Phone: `1234567890`.

- **Exclusive Features for Admin**:
  - Only the admin can:
    - Add books to the library.
    - Remove books from the catalog.

---

### 6. Menu-Driven Interface
- User-friendly, text-based interface with a menu to navigate between functionalities:
  - Register
  - Login
  - Borrow or return books
  - View available books and transactions
  - Admin-only options like adding and removing books.

---

### 7. Error Handling and Validation
- Validates all input data to ensure:
  - Book IDs exist.
  - Users are logged in before performing restricted actions.
  - Prevents duplicate issuing of books already borrowed.

- Displays meaningful error messages for invalid inputs or operations.

---

### 8. Lightweight and Portable
- Uses plain text files for data storage, eliminating the need for external databases.
- Fully portable and can run on any platform with Java installed (Windows, macOS, Linux).


## Technologies Used

### 1. **Programming Language**
- **Java**:
  - Used as the core programming language to implement the application logic.
  - Provides robust object-oriented features and supports file handling for persistent data storage.

### 2. **Development Environment**
- **Java Development Kit (JDK)**:
  - Minimum Version: JDK 8.
  - Required to compile and run the Java application.
  
- **Integrated Development Environment (IDE)** (Optional):
  - Recommended IDEs:
    - IntelliJ IDEA
    - Eclipse
    - NetBeans
  - Alternatively, you can use any text editor and a terminal/command prompt.

### 3. **Storage Mechanism**
- **File-Based Data Storage**:
  - `users.txt`: Stores user details (e.g., userID, name, email, phone).
  - `books.txt`: Stores book information (e.g., bookID, title, author, genre, availability).
  - `transactions.txt`: Stores transaction history (e.g., transactionID, bookID, userID, issue/return dates).

### 4. **Libraries and APIs**
- **Java Standard Library**:
  - `java.util.Scanner`: Handles user input from the console.
  - `java.io.*`: Manages file operations for reading and writing data.
  - `java.time.LocalDate`: Tracks transaction dates (issue/return).

### 5. **Platform Compatibility**
- Cross-Platform Support:
  - Runs seamlessly on **Windows**, **macOS**, and **Linux** operating systems.
  - Requires only the Java Runtime Environment (JRE) for execution.

### 6. **Version Control**
- **Git**:
  - Version control system for tracking code changes and collaboration.
  - Use GitHub or similar platforms to host and manage the project repository.

### 7. **Text-Based User Interface**
- Console-based application:
  - Simple, text-based interface for user interactions.
  - Menu-driven navigation for ease of use.

## Contact
For questions, please contact:
- **Name**: Dilushika Sewwandi
- **Email**: dilushi928@outlook.com



