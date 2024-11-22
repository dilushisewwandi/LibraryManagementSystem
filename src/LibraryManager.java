import java.util.Scanner;

public class LibraryManager {
    //Creates an instance of the Library class, which stores users and will later handle books and other functionalities.
    private Library library = new Library();
    //Creates a Scanner object to read user input from the console.
    private Scanner scanner = new Scanner(System.in);
    //A variable to keep track of the currently logged-in user. If null, no user is logged in.
    private User currentUser = null;

    //The entry point to the library system's main menu.
    public void start(){
        //Placeholder for loading existing data (e.g., users, books).
        loadData();
        //A do-while loop to continuously display the menu until the user chooses option 10 (Exit).
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
            //Reads the user's menu choice. scanner.nextLine() consumes any leftover newline characters to avoid input issues.
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            //Handles menu options via a switch statement. Each case corresponds to a menu action.
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

    private void login(){
        //Prompts the user to enter their email and reads the input.
        System.out.println("Enter your email: ");
        String email = scanner.nextLine();
        System.out.println("Enter your phone number: ");
        String phone = scanner.nextLine();

        //Loops through the list of users. If a user's email and phone match the input, sets currentUser and logs them in.
        for (User user : library.getUsers()){
            if (user.getEmail().equals(email) && user.getPhone().equals(phone)){
                currentUser = user;
                System.out.println("Login successful. Welcome, " + currentUser.getName() + "!");
                return;
            }
        }
        System.out.println("Login failed. Please check your credentials.");
    }

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

    private void viewBooks(){

    }

    private void issueBook(){

    }

    private void returnBook(){

    }

    private void viewTransactions(){

    }

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

    private void logout(){
        //Sets currentUser to null, indicating no one is logged in.
        currentUser = null;
        System.out.println("You have been logged out. ");
    }

    private void loadData(){
        // Preload an admin user with a predefined email and phone number
        User adminUser = new User(library.generateUserID(), "admin", "admin@library.com", "1234567890");
        library.addUser(adminUser);
        System.out.println("Admin user loaded: admin (Email:admin@library.com, Phone:1234567890)");
    }

    private void saveData(){

    }

    //Creates an instance of LibraryManager and calls the start() method to run the application.
    public static void main(String[] args){
        LibraryManager manager = new LibraryManager();
        manager.start();
    }
}
