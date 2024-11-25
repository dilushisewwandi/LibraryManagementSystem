public class Book {
    private int bookID;
    private String title;
    private String author;
    private String genre;
    private int publishedYear;
    private boolean isAvailable;
    private Integer issuedTo;
    private Integer returnFrom;

    //Getters and setters
    public int getBookID() {
        return bookID;
    }
    public void setBookID(int bookID) {
        this.bookID = bookID;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public int getPublishedYear() {
        return publishedYear;
    }
    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }
    public boolean isAvailable() {
        return isAvailable;
    }
    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
    public Integer getIssuedTo() {
        return issuedTo;
    }
    public void setIssuedTo(Integer issuedTo) {
        this.issuedTo = issuedTo;
    }
    public Integer getReturnFrom() {
        return returnFrom;
    }
    public void setReturnFrom(Integer returnFrom) {
        this.returnFrom = returnFrom;
    }


    //Constructor to initialize a new book with the provided attributes
    public Book(int bookID, String title, String author, String genre, int publishedYear, boolean isAvailable) {
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publishedYear = publishedYear;
        this.isAvailable = true;
        this.issuedTo = null;// Initially, no one has issued the book
        this.returnFrom = null;//Initially, no one has returned a book
    }
    
    //Overrides the default toString() method to provide a readable string representation of a book
    @Override
    public String toString() {
        return "Book ID: " + bookID + ", Title: " + title + ", Author: " + author +", Available: " + (isAvailable ? "Yes" : "No");//ternary operator            
    }
 
}