import java.util.ArrayList;
import java.util.List;

public class Library {
    //Lists to store all Book objects, Transaction objects, and registered users.
    private List<Book> books = new ArrayList<>();
    private List<Transaction> transactions = new ArrayList<>();
    private List<User> users = new ArrayList<>();

    //A counter to generate unique user IDs and book IDs.
    private int userIDCounter = 1;
    private int bookIDCounter = 1;

    //Generates a new unique user ID by returning the current value and incrementing it for the next user.
    public int generateUserID(){
        return userIDCounter++;
    }

    //Generates a new unique book ID by returning the current value and incrementing it for the next book.
    public int generateBookID(){
        return bookIDCounter++;
    }

    //Adds a user to the users list.
    public void addUser(User user){
        users.add(user);
    }

    //Adds a book to the books list.
    public void addBook(Book book){
        books.add(book);
    }

    //Adds a transaction to the transactions list.
    public void addTransaction(Transaction transaction){
        transactions.add(transaction);
    }

    //Getters for lists 
    public List<User> getUsers(){
        return users;
    }
    public List<Book> getBooks() {
        return books;
    }
    public List<Transaction> getTransactions() {
        return transactions;
    }

    
}
