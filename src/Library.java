import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Library {

    private Logs logs;
    private String libraryName;
    private boolean userIsInTheLibrary = true;
    private ArrayList<Book> books;
    private ArrayList<Person> users;
    private Computer computer;
    private double membershipPrice = 25;
    private BankAccount libraryBankAccount;
    private Scanner sc;

    public Library(String libraryName) {
        this.libraryName = libraryName;
        books = new ArrayList<>();
        users = new ArrayList<>();
        computer = new Computer();
        logs = new Logs();

        libraryBankAccount = new BankAccount(this.libraryName);

        sc = new Scanner(System.in);
    }

    public void enter() throws IOException, InterruptedException {
        while (userIsInTheLibrary) {
            System.out.println("\u001b[41;1mWelcome to the " + getLibraryName() + " library!\u001b[0m");
            System.out.println("\u001b[37;1m1 - USE COMPUTER \u001b[0m");
            System.out.println("\u001b[37;1m2 - EXIT \u001b[0m");
            System.out.print("\u001b[31;1mPlease select a option: \u001b[0m");

            switch (sc.next()) {
                case "1":
                    useComputer();
                    break;
                case "2":
                    userIsInTheLibrary = false;
                    break;
                default:
                    System.out.println("\u001b[31;1mInvalid option!\u001b[0m");
                    break;
            }
        }
    }

    public void useComputer() throws IOException, InterruptedException {

        boolean usingComputer = true;

        while (usingComputer) {
            System.out.println();
            System.out.println("\u001b[41;1mCOMPUTER: \u001b[0m");
            System.out.println("\u001b[37;1m1 - LOGIN \u001b[0m");
            System.out.println("\u001b[37;1m2 - REGISTER NEW USER \u001b[0m");
            System.out.println("\u001b[37;1m3 - EXIT COMPUTER \u001b[0m");
            System.out.print("\u001b[31;1mPlease select a option: \u001b[0m");

            String passwordSystem = "admin";
            String usernameAdmin = "admin";
            switch (sc.next()) {
                case "1":
                    System.out.print("\u001b[32;1mUSERNAME:\u001b[0m ");
                    String userInput = sc.next();
                    System.out.print("\u001b[32;1mPASSWORD:\u001b[0m ");
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
                        System.out.println();
                        System.out.println("\u001b[41;1mWelcome, Admin!\u001b[0m");
                        loginAsAdmin();
                        break;
                    }

                    if (userFound) {
                        if (passwordFound) {
                            System.out.println();
                            System.out.println("\n\u001b[32;1mAccess granted! Welcome, " + user.getName() + "!\u001b[0m");
                            loginAsUser(user);
                        } else {
                            System.out.println("\u001b[41;1mWrong password.\u001b[0m");
                        }
                    } else {
                        System.out.println("\u001b[41;1mUser not found.\u001b[0m");
                    }
                    break;
                case "2":
                    computer.registerUser(users);
                    break;
                case "3":
                    usingComputer = false;
                    break;
                default:
                    System.out.println("\u001b[31;1mInvalid option.\u001b[0m");
                    break;
            }
        }
    }

    public void loginAsAdmin() throws IOException {

        boolean loggedIn = true;

        while (loggedIn) {
            System.out.println();
            System.out.println("\u001b[41;1mADMIN:\u001b[0m");
            System.out.println("\u001b[37;1m1 - GET LIBRARY STOCK\u001b[0m");
            System.out.println("\u001b[37;1m2 - GET LIST OF BOOKS\u001b[0m");
            System.out.println("\u001b[37;1m3 - REGISTER BOOK\u001b[0m");
            System.out.println("\u001b[37;1m4 - REGISTER NEW USER ON SYSTEM\u001b[0m");
            System.out.println("\u001b[37;1m5 - REMOVE BOOK\u001b[0m");
            System.out.println("\u001b[37;1m6 - REMOVE USER FROM SYSTEM\u001b[0m");
            System.out.println("\u001b[37;1m7 - GET USERS LIST\u001b[0m");
            System.out.println("\u001b[37;1m8 - CHECK LIBRARY BALANCE\u001b[0m");
            System.out.println("\u001b[37;1m9 - SET MEMBERSHIP PRICE\u001b[0m");
            System.out.println("\u001b[37;1m10 - CHECK LAST LOG\u001b[0m");
            System.out.println("\u001b[37;1m11 - EXIT FROM ADMIN SESSION\u001b[0m");
            System.out.print("\u001b[31;1mPlease select a option: \u001b[0m");

            switch (sc.next().replaceAll("[^0-9]", "")) {
                case "1":
                    getStock();
                    logs.writeOnLog("Admin checked stock at " + LocalDate.now() + " | " + LocalTime.now() + "\n");
                    break;
                case "2":
                    computer.getBookList(books);
                    logs.writeOnLog("Admin check book list at " + LocalDate.now() + " | " + LocalTime.now() + "\n");
                    break;
                case "3":
                    computer.addBook(books);
                    logs.writeOnLog("Admin added a book at " + LocalDate.now() + " | " + LocalTime.now() + "\n");
                    break;
                case "4":
                    computer.registerOnSystemANewUser(users);
                    logs.writeOnLog("Admin registered a new system user at "+ LocalDate.now() + " | " + LocalTime.now() + "\n");
                    break;
                case "5":
                    computer.removeBook(books);
                    logs.writeOnLog("Admin removed a book at "+ LocalDate.now() + " | " + LocalTime.now() + "\n");
                    break;
                case "6":
                    computer.removeUserFromSystem(users);
                    logs.writeOnLog("Admin removed a user from system at "+ LocalDate.now() + " | " + LocalTime.now() + "\n");
                    break;
                case "7":
                    computer.getUsersList(users);
                    logs.writeOnLog("Admin checked users list at "+ LocalDate.now() + " | " + LocalTime.now() + "\n");
                    break;
                case "8":
                    computer.getLibraryBalance(this);
                    logs.writeOnLog("Admin checked library balance at "+ LocalDate.now() + " | " + LocalTime.now() + "\n");
                    break;
                case "9":
                    computer.setMembershipPrice(this);
                    logs.writeOnLog("Admin setted a new membership price at "+ LocalDate.now() + " | " + LocalTime.now() + "\n");
                    break;
                case "10":
                    logs.writeOnLog("Admin checked logs at "+LocalDate.now() + " | " + LocalTime.now() + "\n");
                    logs.readLastLog();
                    break;
                case "11":
                    loggedIn = false;
                    logs.writeOnLog("Admin logged out at "+ LocalDate.now() + " | " + LocalTime.now() + "\n");
                    System.out.println("\u001b[31;1mLogged out.\u001b[0m");
                    break;
                default:
                    System.out.println("\u001b[31;1mInvalid option.\u001b[0m");
                    break;
            }
        }
    }

    public void loginAsUser(Person user) throws IOException, InterruptedException {

        boolean loggedIn = true;

        while (loggedIn) {
            System.out.println();
            System.out.println("\u001b[42;1mUSER:\u001b[0m");
            System.out.println("\u001b[37;1m1 - BUY MEMBERSHIP\u001b[0m");
            System.out.println("\u001b[37;1m2 - CHECK MY BOOKS\u001b[0m");
            System.out.println("\u001b[37;1m3 - REQUEST A BOOK\u001b[0m");
            System.out.println("\u001b[37;1m4 - RETURN A BOOK\u001b[0m");
            System.out.println("\u001b[37;1m5 - CHECK MY MEMBER STATUS\u001b[0m");
            System.out.println("\u001b[37;1m6 - WORK ONLINE\u001b[0m");
            System.out.println("\u001b[37;1m7 - ONLINE BANK\u001b[0m");
            System.out.println("\u001b[37;1m8 - CHANGE PASSWORD\u001b[0m");
            System.out.println("\u001b[37;1m9 - EXIT FROM USER SESSION\u001b[0m");

            switch (sc.next().replaceAll("[^0-9]", "")) {
                case "1":
                    computer.getMembership(this, user, membershipPrice);
                    logs.writeOnLog(user.getName() + " bought membership at "+ LocalDate.now() + " | " + LocalTime.now() + "\n");
                    break;
                case "2":
                    computer.checkMyBooks(user);
                    logs.writeOnLog(user.getName() + " checked books list at "+ LocalDate.now() + " | " + LocalTime.now() + "\n");
                    break;
                case "3":
                    computer.requestBook(user, books);
                    logs.writeOnLog(user.getName() + " requested a book at "+ LocalDate.now() + " | " + LocalTime.now() + "\n");
                    break;
                case "4":
                    computer.returnBook(user, books);
                    logs.writeOnLog(user.getName() + " returned a book at "+ LocalDate.now() + " | " + LocalTime.now() + "\n");
                    break;
                case "5":
                    computer.getMembershipStatus(user);
                    logs.writeOnLog(user.getName() + " checked membership status at "+ LocalDate.now() + " | " + LocalTime.now() + "\n");
                    break;
                case "6":
                    computer.workOnline(user);
                    logs.writeOnLog(user.getName() + " worked online at "+ LocalDate.now() + " | " + LocalTime.now() + "\n");
                    break;
                case "7":
                    computer.bankOnline(user);
                    logs.writeOnLog(user.getName() + " entered on Bank Online at "+ LocalDate.now() + " | " + LocalTime.now() + "\n");
                    break;
                case "8":
                    computer.changePassword(user);
                    logs.writeOnLog(user.getName() + " changed password at "+ LocalDate.now() + " | " + LocalTime.now() + "\n");
                    break;
                case "9":
                    loggedIn = false;
                    logs.writeOnLog(user.getName() + " logged out at "+ LocalDate.now() + " | " + LocalTime.now() + "\n");
                    System.out.println("\u001b[31;1mLogged out.\u001b[0m");
                    break;
                default:
                    System.out.println("\u001b[31;1mInvalid option.\u001b[0m");
                    break;
            }
        }
    }

    public String getLibraryName() {
        return libraryName;
    }

    public void getStock() {
        System.out.println("\n\u001b[31;1mSTOCK: "+ "\u001b[37;1m" + books.size()+ "\u001b[0m\n");
    }

    public BankAccount getLibraryBankAccount() {
        return libraryBankAccount;
    }

    public void setMembershipPrice(double membershipPrice) {
        this.membershipPrice = membershipPrice;
    }
}
