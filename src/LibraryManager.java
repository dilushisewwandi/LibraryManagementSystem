import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Scanner;

public class LibraryManager {
    //Creates an instance of the Library class, which stores users and will later handle books and other functionalities.
    private Library library = new Library();
    //Creates a Scanner object to read user input from the console.
    private Scanner scanner = new Scanner(System.in);
    //A variable to keep track of the currently logged-in user. If null, no user is logged in.
    private User currentUser = null;

    public void start(){
        loadData();
        int choice;
        do{
            System.out.println("\nLibrary Management System");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. View All Books");
            System.out.println("4. Issue Book");
            System.out.println("5. Return Book");
            System.out.println("6. View Transactions");
            System.out.println("7. Add Book (Admin)");
            System.out.println("8. Remove Book (Admin)");
            System.out.println("9. Logout");
            System.out.println("10. Exit");
            System.out.print("Enter your choice (1-10): ");
            choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    registerUser();
                    break;
                case 3: 
                    viewBooks();
                    break;
                case 4: 
                    issueBook();
                    break;
                case 5 : 
                    returnBook();
                    break;
                case 6 :
                    viewTransactions();
                    break;
                case 7 : 
                    addBook();
                    break;
                case 8 : 
                    removeBook();
                    break;
                case 9 : 
                    logout();
                    break;
                case 10 : 
                    System.out.println("Exiting...");
                    saveData();
                    break;
                default : 
                    System.out.println("Invalid choice. Please try again.");
            }
        }while (choice !=10);
    }

    //User login
    private void login(){
        System.out.println("Enter your email: ");
        String email = scanner.nextLine();
        System.out.println("Enter your phone number: ");
        String phone = scanner.nextLine();

        for (User user : library.getUsers()){
            if (user.getEmail().equals(email) && user.getPhone().equals(phone)){
                currentUser = user;
                System.out.println("Login successful. Welcome, " + currentUser.getName() + "!");
                return;
            }
        }
        System.out.println("Login failed. Please check your credentials.");
    }

    //User registration
    private void registerUser(){
        System.out.println("Enter your name");
        String name = scanner.nextLine();
        System.out.println("Enter your email");
        String email = scanner.nextLine();
        System.out.println("Enter your phone number");
        String phone = scanner.nextLine();

        //Calls the generateUserID() method from the Library class to assign a unique ID.
        int userID = library.generateUserID();
        //Creates a new User object and adds it to the library's user list.
        User newUser = new User(userID, name, email, phone);
        library.addUser(newUser);

        System.out.println("Registration successful! Your user ID is: " + newUser.getUserID());
    }

    //View all books
    private void viewBooks(){
        if (library.getBooks().isEmpty()) {
            System.out.println("No books are in the library.");
            return;
        }
        System.out.println("Book list: ");
        for(Book book : library.getBooks()){
            System.out.println(book);
        }
    }

    //Issue book
    private void issueBook(){
        if (currentUser ==null) {
            System.out.println("You must login to issue a book");
            return;
        }
        System.out.println("Enter the book ID: ");
        int bookID = scanner.nextInt();
        scanner.nextLine();

        //Find the book by bookID
        Book bookToIssue = null;
        for(Book book : library.getBooks()){
            if (book.getBookID() == bookID) {
                bookToIssue = book;
                break;
            }
        }

        if (bookToIssue == null) {
            System.out.println("Book is not found of ID: " + bookID);
            return;
        }

        if (!bookToIssue.isAvailable()) {
            System.out.println("The book is already issued.");
            return;
        }

        bookToIssue.setAvailable(false);
        bookToIssue.setIssuedTo(currentUser.getUserID());

        //Create a transaction
        int transactionID = library.getTransactions().size() + 1;
        Transaction transaction = new Transaction(transactionID, bookID, currentUser.getUserID());
        library.addTransaction(transaction);

        System.out.println("Book issued successfully: " + bookToIssue.getTitle());
    }

    //Return book
    private void returnBook(){
        if (currentUser == null) {
            System.out.println("You must login to return a book");
            return;
        }
        System.out.println("Enter the book id to return: ");
        int bookID = scanner.nextInt();
        scanner.nextLine();

        //Find book by book ID
        Book bookToReturn = null;
        for(Book book : library.getBooks()){
            if (book.getBookID() == bookID) {
                bookToReturn = book;
                break;
            }
        }

        if (bookToReturn == null) {
            System.out.println("Book is not found of ID: " + bookID);
            return;
        }

        if (bookToReturn.isAvailable()) {
            System.out.println("This book is already returned. ");
            return;
        }

        //Find the corresponding transaction
        Transaction relatedTransaction = null;
        for(Transaction transaction : library.getTransactions()){
            if (transaction.getBookID() == bookID && transaction.getUserID() == currentUser.getUserID() && !transaction.isReturned()) {
                relatedTransaction = transaction;
                break;
            }
        }

        if (relatedTransaction == null) {
            System.out.println("No transactions found for this book.");
            return;
        }

        bookToReturn.setAvailable(true);
        bookToReturn.setIssuedTo(null);

        relatedTransaction.setReturned(true);
        relatedTransaction.setReturnDate(LocalDate.now());

        System.out.println("Successfully returned the book: " + bookToReturn.getTitle());
        // bookToReturn.setReturnFrom(currentUser.getUserID());

        // //Create a transaction
        // int transactionID = library.getTransactions().size() + 1;
        // Transaction transaction = new Transaction(transactionID, bookID, currentUser.getUserID());
        // library.addTransaction(transaction);

        // System.out.println("Book is returned successfully: " + bookToReturn.getTitle());
    }

    //View transactions
    private void viewTransactions(){
        if (library.getTransactions().isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }
        System.out.println("Transaction History: ");
        for(Transaction transaction : library.getTransactions()){
            System.out.println("Transaction ID: " + transaction.getTransactionID() + ", Book ID: " + transaction.getBookID() + ", User ID: " + transaction.getUserID() + ", Issue Date: " + transaction.getIssueDate() +
             ", Return Date: " + (transaction.getReturnDate() != null? transaction.getReturnDate() : "Not returned") + ", Status: " + (transaction.isReturned() ? "Returned" : "Issued"));
        }
    }

    //Add a book
    private void addBook(){
        if(currentUser == null){
            System.out.println("You must log in as an admin to add books");
            return;
        }

        //Admin check
       if (!currentUser.getName().equalsIgnoreCase("admin")) {
        System.out.println("Only admin can add books");
        return;
       }

       System.out.println("Enter the book title: ");
       String title = scanner.nextLine();

       System.out.println("Enter the author name: ");
       String author = scanner.nextLine();

       System.out.println("Enter the book genre: ");
       String genre = scanner.nextLine();

       int bookID = library.generateBookID();
       Book newBook = new Book(bookID, title, author, genre, bookID, true);
       library.addBook(newBook);

       System.out.println("Book is added successfully! Book ID: " + bookID);
    }

    //Remove a book
    private void removeBook(){
        if (currentUser==null) {
            System.out.println("You must login as an admin to remove books");
            return;
        }

        //Admin Check
        if (!currentUser.getName().equalsIgnoreCase("admin")) {
            System.out.println("Only admin can remove books");
            return;
        }

        System.out.println("Enter the book id: ");
        int bookID = scanner.nextInt();
        scanner.nextLine();

        //if the book is exists
        Book bookToRemove = null;
        for(Book book : library.getBooks()){
            if (book.getBookID() == bookID) {
                bookToRemove = book;
                break;
            }
        }

        if (bookToRemove !=null) {
            library.getBooks().remove(bookToRemove);
            System.out.println("Book removed successfully: " + bookToRemove.getTitle());
        }else{
            System.out.println("Book is not found related with ID " + bookID);
        }

    }

    //User logout
    private void logout(){
        //Sets currentUser to null, indicating no one is logged in.
        currentUser = null;
        System.out.println("You have been logged out. ");
    }

    //Load data
    private void loadData(){
        loadUsers();
        loadBooks();
        loadTransactions();

        // Preload an admin user with a predefined email and phone number
        User adminUser = new User(library.generateUserID(), "admin", "admin@library.com", "1234567890");
        library.addUser(adminUser);
        System.out.println("Admin user loaded: admin (Email:admin@library.com, Phone:1234567890)");
    }

    //Load users
    private void loadUsers(){
        try(BufferedReader reader = new BufferedReader(new FileReader("users.txt"))){
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int userID = Integer.parseInt(parts[0]);
                String name = parts[1];
                String email = parts[2];
                String phone = parts[3];
                library.addUser(new User(userID,name,email,phone));
            }
        }catch(IOException e){
            System.out.println("Error loading users: " + e.getMessage());
        }
    }

    //Load books
    private void loadBooks(){
        try(BufferedReader reader = new BufferedReader(new FileReader("books.txt"))) {
            String line;
            while ((line = reader.readLine()) !=null) {
                String[] parts = line.split(",");
                int bookID = Integer.parseInt(parts[0]);
                String title = parts[1];
                String author = parts[2];
                String genre = parts[3];
                int publishedYear = Integer.parseInt(parts[4]);
                boolean isAvailable = Boolean.parseBoolean(parts[5]);
                library.addBook(new Book(bookID, title, author, genre, publishedYear, isAvailable));
            }
        } catch (IOException e) {
            System.out.println("Error loading books: " + e.getMessage());
        }
    }

    //Load transactions
    private void loadTransactions(){
        try(BufferedReader reader = new BufferedReader(new FileReader("transactions.txt"))){
            String line;
            while ((line = reader.readLine()) !=null) {
                String[] parts = line.split(",");
                int transactionID = Integer.parseInt(parts[0]);
                int bookID = Integer.parseInt(parts[1]);
                int userID = Integer.parseInt(parts[2]);
                library.addTransaction(new Transaction(transactionID, bookID, userID));
            }
        } catch (IOException e) {
            System.out.println("Error loading transactions: " + e.getMessage());
        }
    }

    //Save user, book, and transaction data
    private void saveData(){
        saveUsers();
        saveBooks();
        saveTransactions();
    }

    //Save users
    private void saveUsers(){
        try (PrintWriter writer = new PrintWriter(new FileWriter("users.txt"))) {
            for(User user : library.getUsers()){
                writer.println(user.getUserID() + "," + user.getName() + "," + user.getEmail() + "," + user.getPhone());
            }            
        } catch (IOException e) {
            System.out.println("Error saving users: " + e.getMessage());
        }
    }

    //Save books
    private void saveBooks(){
        try (PrintWriter writer = new PrintWriter(new FileWriter("books.txt"))) {
            for(Book book : library.getBooks()){
                writer.println(book.getBookID() + "," + book.getTitle() + "," + book.getAuthor() + "," + book.getGenre() + "," + book.getPublishedYear() + "," + book.isAvailable());
            }
        } catch (IOException e) {
            System.out.println("Error saving books: " + e.getMessage());
        }
    }

    //Save transactions
    private void saveTransactions(){
        try (PrintWriter writer = new PrintWriter(new FileWriter("transactions.txt"))){
            for(Transaction transaction : library.getTransactions()){
                writer.println(transaction.getTransactionID() + "," + transaction.getBookID() + "," + transaction.getUserID());
            }
        } catch (IOException e) {
            System.out.println("Error saving transactions: " + e.getMessage());
        }
    }

    //Main method
    public static void main(String[] args){
        LibraryManager manager = new LibraryManager();
        manager.start();
    }
}
