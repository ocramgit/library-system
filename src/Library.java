import java.util.ArrayList;
import java.util.Scanner;

public class Library {

    private String libraryName;
    boolean userIsInTheLibrary = true;
    ArrayList<Book> books;
    ArrayList<Person> users;
    Scanner sc;
    private final String passwordSystem = "admin";

    public Library(String libraryName) {
        setLibraryName(libraryName);
        books = new ArrayList<>();
        users = new ArrayList<>();

        sc = new Scanner(System.in);
    }

    public void open() {
        while (userIsInTheLibrary) {
            System.out.println("Welcome to the " + getLibraryName());
            System.out.println("1 - USE COMPUTER");
            System.out.println("2 - EXIT");

            switch (sc.next()) {
                case "1":
                    useComputer();
                    break;
                case "2":
                    userIsInTheLibrary = false;
                    break;
                default:
                    System.out.println("Invalid option!");
                    break;
            }
        }
    }

    public void useComputer() {
        System.out.println("COMPUTER: ");
        System.out.println("1 - LOGIN AS ADMIN");
        System.out.println("2 - LOGIN AS USER");
        System.out.println("3 - REGISTER NEW USER");
        System.out.println("4 - EXIT COMPUTER");

        switch (sc.next()) {
            case "1":
                System.out.print("Password: ");
                String userInput = sc.next();

                if (userInput.equals(passwordSystem)) {
                    loginAsAdmin();
                } else {
                    System.out.println("Wrong password!");
                }
                break;
            case "2":
                break;
            case "3":
                break;
            case "4":
                open();
                break;
        }
    }

    public void loginAsAdmin() {

        System.out.println("ADMIN: ");
        System.out.println("1 - GET LIBRARY STOCK");
        System.out.println("2 - GET LIST OF BOOKS");
        System.out.println("3 - REGISTER BOOK");
        System.out.println("4 - REGISTER NEW USER ON SYSTEM");
        System.out.println("5 - REMOVE USER FROM SYSTEM");
        System.out.println("6 - GET USERS LIST");
        System.out.println("7 - EXIT FROM COMPUTER");

        switch (sc.next()) {
            case "1":
                System.out.println("STOCK: " + books.size());
                loginAsAdmin();
                break;
            case "2":
                int countBooks = 1;

                for (Book booksList : books) {
                    System.out.println(countBooks++ + ": " + booksList.getBookName());
                }
                loginAsAdmin();
                break;
            case "3":
                System.out.println("Book name: ");
                books.add(new Book(sc.next()));
                System.out.println("Book added!");
                loginAsAdmin();
                break;
            case "4":
                System.out.println("Name of person: ");
                String name = sc.nextLine();
                System.out.println("Username: ");
                sc.next();
                String username = sc.nextLine();
                System.out.println("Password: ");
                sc.next();
                String password = sc.nextLine();
                users.add(new Person(name, username, password));
                System.out.println("Add him as a library member?\n1 - YES\n2 - NO");
                if (sc.nextInt() == 1) {
                    for (int i = 0; i < users.size(); i++) {
                        if (users.get(i).getName().equals(name)) {
                            users.get(i).setMember(true);
                            System.out.println("Added on membership club!");
                        }
                    }
                }
                System.out.println("Member added with success!");
            loginAsAdmin();
            break;
            case "6":

                int countUsers = 0;

                for (int i = 0; i < users.size(); i++) {
                    System.out.println(countUsers++ + ": " + users.get(i).getName() + " | MEMBER: " + users.get(i).isMember);
                }
        }
    }

    public String getPassword() {
        return passwordSystem;
    }

    public String getLibraryName() {
        return libraryName;
    }

    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }
}
