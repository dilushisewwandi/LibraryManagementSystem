public class User {
    private int userID;
    private String name;
    private String email;
    private String phone;
    
    //Getters and setters
    public int getUserID() {
        return userID;
    }
    public void setUserID(int userID) {
        this.userID = userID;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
   
    //Constructor to initialize a new user with the provided attributes
    public User(int userID, String name, String email, String phone){
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    //Overrides the default toString() method to provide a readable string representation of a user
    @Override
    public String toString(){
        return "User ID: "+ userID + ", Name: " + name + ",Email: " + email + ",Phone: " + phone;
    }
    
}
