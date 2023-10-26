import java.util.ArrayList;
import java.util.Scanner;

public class Library {

    private String libraryName;
    boolean userIsInTheLibrary = true;
    ArrayList<Book> books;
    ArrayList<Person> users;
    Computer computer;
    Scanner sc;
    private final String passwordSystem = "admin";

    public Library(String libraryName) {
        this.libraryName = libraryName;
        books = new ArrayList<>();
        users = new ArrayList<>();
        computer = new Computer();

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

        boolean usingComputer = true;

        while (usingComputer) {
            System.out.println("COMPUTER: ");
            System.out.println("1 - LOGIN AS ADMIN");
            System.out.println("2 - LOGIN AS USER");
            System.out.println("3 - REGISTER NEW USER");
            System.out.println("4 - EXIT COMPUTER");

            switch (sc.next()) {
                case "1":
                    System.out.print("Password: ");
                    String adminInput = sc.next();

                    if (adminInput.equals(passwordSystem)) {
                        loginAsAdmin();
                    } else {
                        System.out.println("Wrong password!");
                    }
                    break;
                case "2":
                    System.out.println("Username: ");
                    String userInput = sc.next();
                    System.out.println("Password: ");
                    String passwordUserInput = sc.next();

                    for (Person person : users) {
                        if(person.getUsername().equals(userInput) && person.getPassword().equals(passwordUserInput)) {
                            System.out.println("Access granted! Welcome, " + person.getName());
                            loginAsUser(person);
                            break;
                        } else {
                            System.out.println("Wrong credentials!");
                        }
                    }
                    break;
                case "3":
                    computer.registerUser(users);
                    break;
                case "4":
                    usingComputer = false;
                    break;
            }
        }
    }

    public void loginAsAdmin() {

        boolean loggedIn = true;

        while (loggedIn) {

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
                    getStock();
                    break;
                case "2":
                    computer.getBookList(books);
                    break;
                case "3":
                    computer.addBook(books);
                    break;
                case "4":
                    computer.registerOnSystemANewUser(users);
                    break;
                case "5":
                    computer.removeUserFromSystem(users);
                    break;
                case "6":
                    computer.getUsersList(users);
                    break;
                case "7":
                    loggedIn = false;
                    break;
            }
        }
    }

    public void loginAsUser(Person user) {

        boolean loggedIn = true;

        while (loggedIn) {

            System.out.println("ADMIN: ");
            System.out.println("1 - GET MEMBERSHIP");
            System.out.println("2 - CHECK MY BOOKS");
            System.out.println("3 - REQUEST A BOOK");
            System.out.println("4 - RETURN A BOOK");
            System.out.println("5 - CHECK MY MEMBER STATUS");
            System.out.println("5 - CHANGE PASSWORD");
            System.out.println("6 - EXIT FROM COMPUTER");

            switch (sc.next()) {
                case "1":
                    computer.getMembership(user);
                    break;
                case "2":
                    computer.checkMyBooks(user);
                    break;
                case "3":
                    computer.requestBook(user, books);
                    break;
                case "4":
                    computer.returnBook(user, books);
                    break;
                case "5":
                    computer.changePassword(user);
                    break;
                case "6":
                    loggedIn = false;
                    break;
            }
        }
    }

    public String getLibraryName() {
        return libraryName;
    }

    public void getStock() {
        System.out.println("STOCK: "+ books.size());
    }

}
