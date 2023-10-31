import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Library {

    private Logs logs = new Logs();
    private String libraryName;
    private boolean userIsInTheLibrary = true;
    private ArrayList<Book> books;
    private ArrayList<Person> users;
    private Computer computer;
    private double membershipPrice = 25;
    private BankAccount libraryBankAccount;
    private Scanner sc;

    public Library(String libraryName) throws IOException {
        this.libraryName = libraryName;
        books = new ArrayList<>();
        users = new ArrayList<>();
        computer = new Computer();

        libraryBankAccount = new BankAccount(this.libraryName, 0);

        sc = new Scanner(System.in);
    }

    public void open() throws IOException {
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

    public void useComputer() throws IOException {

        boolean usingComputer = true;

        while (usingComputer) {
            System.out.println("COMPUTER: ");
            System.out.println("1 - LOGIN");
            System.out.println("2 - REGISTER NEW USER");
            System.out.println("3 - EXIT COMPUTER");

            String passwordSystem = "admin";
            String usernameAdmin = "admin";
            switch (sc.next()) {
                case "1":
                    System.out.println("Username: ");
                    String userInput = sc.next();
                    System.out.println("Password: ");
                    String passwordUserInput = sc.next();

                    boolean userFound = false;
                    boolean passwordFound = false;

                    Person user = null;

                    for (Person person : users) {
                        if (person.getUsername().equals(userInput)) {
                            userFound = true;
                            user = person;

                            if (person.getPassword().equals(passwordUserInput)) {
                                passwordFound = true;
                            }
                            break;
                        }
                    }

                    if (userInput.equals(usernameAdmin) && passwordUserInput.equals(passwordSystem)) {
                        System.out.println("Welcome, Admin!");
                        loginAsAdmin();
                        break;
                    }

                    if (userFound) {
                        if (passwordFound) {
                            System.out.println("Access granted! Welcome, " + user.getName() + "!");
                            loginAsUser(user);
                        } else {
                            System.out.println("Wrong password.");
                        }
                    } else {
                        System.out.println("User not found.");
                    }
                    break;
                case "2":
                    computer.registerUser(users);
                    break;
                case "3":
                    usingComputer = false;
                    break;
            }
        }
    }

    public void loginAsAdmin() throws IOException {

        boolean loggedIn = true;

        while (loggedIn) {

            System.out.println("ADMIN: ");
            System.out.println("1 - GET LIBRARY STOCK");
            System.out.println("2 - GET LIST OF BOOKS");
            System.out.println("3 - REGISTER BOOK");
            System.out.println("4 - REGISTER NEW USER ON SYSTEM");
            System.out.println("5 - REMOVE BOOK");
            System.out.println("6 - REMOVE USER FROM SYSTEM");
            System.out.println("7 - GET USERS LIST");
            System.out.println("8 - CHECK LIBRARY BALANCE");
            System.out.println("9 - SET MEMBERSHIP PRICE");
            System.out.println("10 - CHECK USER INFO");
            System.out.println("11 - EXIT FROM ADMIN SESSION");

            switch (sc.next()) {
                case "1":
                    getStock();
                    logs.writeOnLog("Admin checked stock at " + LocalTime.now() + "\n");
                    break;
                case "2":
                    computer.getBookList(books);
                    logs.writeOnLog("Admin check book list at " + LocalTime.now() + "\n");
                    break;
                case "3":
                    computer.addBook(books);
                    logs.writeOnLog("Admin added a book at " + LocalTime.now() + "\n");
                    break;
                case "4":
                    computer.registerOnSystemANewUser(users);
                    logs.writeOnLog("Admin registered a new system user at "+LocalTime.now() + "\n");
                    break;
                case "5":
                    computer.removeBook(books);
                    logs.writeOnLog("Admin removed a book at "+LocalTime.now() + "\n");
                    break;
                case "6":
                    computer.removeUserFromSystem(users);
                    logs.writeOnLog("Admin removed a user from system at "+LocalTime.now() + "\n");
                    break;
                case "7":
                    computer.getUsersList(users);
                    logs.writeOnLog("Admin checked users list at "+LocalTime.now() + "\n");
                    break;
                case "8":
                    computer.getLibraryBalance(this);
                    logs.writeOnLog("Admin checked library balance at "+LocalTime.now() + "\n");
                    break;
                case "9":
                    computer.setMembershipPrice(this);
                    logs.writeOnLog("Admin setted a new membership price at "+LocalTime.now() + "\n");
                    break;
                case "11":
                    loggedIn = false;
                    logs.writeOnLog("Admin logged out at "+LocalTime.now() + "\n");
                    System.out.println("Logged out.");
                    break;
            }
        }
    }

    public void loginAsUser(Person user) throws IOException {

        boolean loggedIn = true;

        while (loggedIn) {

            System.out.println("USER: ");
            System.out.println("1 - BUY MEMBERSHIP");
            System.out.println("2 - CHECK MY BOOKS");
            System.out.println("3 - REQUEST A BOOK");
            System.out.println("4 - RETURN A BOOK");
            System.out.println("5 - CHECK MY MEMBER STATUS");
            System.out.println("6 - CHANGE PASSWORD");
            System.out.println("7 - EXIT FROM USER SESSION");

            switch (sc.next()) {
                case "1":
                    computer.getMembership(this, user, membershipPrice);
                    logs.writeOnLog(user.getName() + " bought membership at "+LocalTime.now() + "\n");
                    break;
                case "2":
                    computer.checkMyBooks(user);
                    logs.writeOnLog(user.getName() + " checked books list at "+LocalTime.now() + "\n");
                    break;
                case "3":
                    computer.requestBook(user, books);
                    logs.writeOnLog(user.getName() + " requested a book at "+LocalTime.now() + "\n");
                    break;
                case "4":
                    computer.returnBook(user, books);
                    logs.writeOnLog(user.getName() + " returned a book at "+LocalTime.now() + "\n");
                    break;
                case "5":
                    computer.getMembershipStatus(user);
                    logs.writeOnLog(user.getName() + " checked membership status at "+LocalTime.now() + "\n");
                    break;
                case "6":
                    computer.changePassword(user);
                    logs.writeOnLog(user.getName() + " changed password at "+LocalTime.now() + "\n");
                    break;
                case "7":
                    loggedIn = false;
                    logs.writeOnLog(user.getName() + " logged out at "+LocalTime.now() + "\n");
                    System.out.println("Logged out.");
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

    public BankAccount getLibraryBankAccount() {
        return libraryBankAccount;
    }

    public void setMembershipPrice(double membershipPrice) {
        this.membershipPrice = membershipPrice;
    }
}
