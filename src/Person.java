import java.util.ArrayList;

public class Person {
    private String name;
    boolean isMember;
    private String username;
    private String password;
    private ArrayList<Book> requestedBooks;

    public Person(String name) {
        this.name = name;
    }

    public Person(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
        requestedBooks = new ArrayList<>();
    }

    public ArrayList<Book> getInventoryOfBooks() {
        return requestedBooks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isMember() {
        return isMember;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setMember(boolean member) {
        isMember = member;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
