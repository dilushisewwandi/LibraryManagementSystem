import java.time.LocalDate;

public class Transaction {
    private int transactionID;
    private int bookID;
    private int userID;
    private LocalDate issueDate;
    private LocalDate returnDate;
    private boolean isReturned;

    //Getters and setters
    public int getTransactionID() {
        return transactionID;
    }
    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }
    public int getBookID() {
        return bookID;
    }
    public void setBookID(int bookID) {
        this.bookID = bookID;
    }
    public int getUserID() {
        return userID;
    }
    public void setUserID(int userID) {
        this.userID = userID;
    }
    public LocalDate getIssueDate() {
        return issueDate;
    }
    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }
    public LocalDate getReturnDate() {
        return returnDate;
    }
    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
    public boolean isReturned() {
        return isReturned;
    }
    public void setReturned(boolean isReturned) {
        this.isReturned = isReturned;
    }
    
    
    //Constructor to initialize a new transaction with the provided attributes
    public Transaction(int transactionID, int bookID, int userID){
        this.transactionID = transactionID;
        this.bookID = bookID;
        this.userID = userID;
        this.issueDate = LocalDate.now();
        this.returnDate = null;
        this.isReturned = false;
    }
        
   
    
}
