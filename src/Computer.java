import java.io.IOException;
import java.util.*;

public class Computer {

    private Scanner sc;

    public Computer() {
        sc = new Scanner(System.in);
    }

    public void getBookList(ArrayList<Book> books) {
        int countBooks = 1;

        System.out.println();
        if (!books.isEmpty()) {
            for (Book booksList : books) {
                System.out.println("\u001b[32;1m" + countBooks++ + ": " + booksList.getBookName() + " from " + booksList.getAuthor() + "\u001b[0m");
            }
            System.out.println();
        } else {
            System.out.println("\n\u001b[31;1mThis library don't have any books.\u001b[0m\n");
        }
    }

    public void getMembershipStatus(Person person) {
        if (person.isMember()) {
            System.out.println("\n\u001b[32;1mYou're a library member.\u001b[0m\n");
        } else {
            System.out.println("\n\u001b[31;1mYou're not a library member.\u001b[0m\n");
        }
    }

    public void registerOnSystemANewUser(ArrayList<Person> users) {
        System.out.println("\n\u001b[32;1mName of person:\u001b[0m ");
        String name = sc.nextLine();
        System.out.println("\u001b[32;1mUsername:\u001b[0m ");
        String username = sc.nextLine();
        System.out.println("\u001b[32;1mPassword:\u001b[0m ");
        String password = sc.nextLine();
        users.add(new Person(name, username, password));
        System.out.println("\u001b[32;1mAdd him as a library member?\n1 - YES\n2 - NO\u001b[0m\n");
        if (sc.nextInt() == 1) {
            for (Person user : users) {
                if (user.getName().equals(name)) {
                    user.setMember(true);
                    System.out.println("\n\u001b[32;1mAdded on membership club!\u001b[0m\n");
                }
            }
        }
        System.out.println("\n\u001b[32;1mMember added with success!\u001b[0m\n");
    }

    public void registerUser(ArrayList<Person> users) {

        boolean userExists = false;

        System.out.print("\n\u001b[32;1mName of person: \u001b[0m ");
        String name = sc.next();
        System.out.print("\u001b[32;1mUsername: \u001b[0m ");
        String username = sc.next();
        System.out.print("\u001b[32;1mPassword: \u001b[0m ");
        String password = sc.next();

        for (Person user : users) {
            if (user.getUsername().equals(username)) {
                userExists = true;
                System.out.println("\n\u001b[31;1mUsername already taken!\u001b[0m\n");
            }
        }

        if (username.equalsIgnoreCase("admin")) {
            System.out.println("\n\u001b[31;1mYou can't create this privileged user.\u001b[0m\n");
            userExists = true;
        }

        if (!userExists) {
            users.add(new Person(name, username, password));
            System.out.println("\n\u001b[32;1mMember added with success!\u001b[0m\n");
        }
    }

    public void workOnline(Person user) throws InterruptedException {
        Job job = new Job();
        job.work(user);
    }

    public void getUsersList(ArrayList<Person> users) {

        int countUsers = 1;

        System.out.println();

        if (!users.isEmpty()) {

            for (Person user : users) {
                System.out.println("\u001b[32;1m" + countUsers++ + " "
                        + user.getName()
                        + " MEMBER: " + user.isMember() + "\u001b[0m");

                if (!user.getInventoryOfBooks().isEmpty()) {
                    System.out.print("\u001b[32;1mBooks: \u001b[0m");
                    for (int i = 0; i < user.getInventoryOfBooks().size(); i++) {

                        System.out.println(user.getInventoryOfBooks().get(i).getBookName() + " ");
                    }
                }
            }

            System.out.println();

        } else {
            System.out.println("\n\u001b[31;1mThis library don't have any users.\u001b[0m");
        }
    }

    public void removeUserFromSystem(ArrayList<Person> users) {
        getUsersList(users);

        if (users.isEmpty()) {
            return;
        }

        System.out.print("\n\u001b[32;1mPlease enter option: \u001b[0m\n");

        int indexToRemove;

        while (true) {
            try {
                indexToRemove = Integer.parseInt(sc.next());
                if (indexToRemove > 0 && indexToRemove <= users.size()) {
                    break;
                } else {
                    System.out.println("\u001b[31mInvalid index. Please enter a valid index.\u001b[0m");
                }
            } catch (NumberFormatException e) {
                System.out.println("\u001b[31mInvalid input. Please enter a valid number.\u001b[0m");
            }
        }

        users.remove(indexToRemove - 1);
        System.out.println("\n\u001b[32;1mUser removed successfully.\u001b[0m\n");
    }


    public void addBook(ArrayList<Book> books) {
        System.out.println("\n\u001b[32;1mBook name: \u001b[0m");
        String bookNameFix = sc.nextLine();
        String bookName = bookNameFix;
        System.out.println("\u001b[32;1mAuthor: \u001b[0m");
        String author = sc.nextLine();
        books.add(new Book(bookName, author));
        System.out.println("\u001b[32;1mBook added!\u001b[0m\n");
    }

    public void getMembership(Library library, Person user, double membershipPrice) {
        if (!user.isMember()) {
            if (user.getBankAccount().getBalance() > 25) {
                user.setMember(true);
                user.getBankAccount().setBalance(user.getBankAccount().getBalance() - membershipPrice);
                library.getLibraryBankAccount().setBalance(library.getLibraryBankAccount().getBalance() + membershipPrice);
                System.out.println("\n\u001b[32;1mYou're now a member of this library.\u001b[0m");
                System.out.println("\u001b[32;1mWe debited 25$ from your bank account.\u001b[0m\n");
            } else {
                System.out.println("\n\u001b[31;1mYou don't have money to buy membership.\u001b[0m\n");
            }
        } else {
            System.out.println("\n\u001b[31;1mYou already are a member.\u001b[0m\n");
        }
    }

    public void checkMyBooks(Person user) {
        int count = 1;

        System.out.println("\u001b[32;1mYour books: \u001b[0m");

        if (!user.getInventoryOfBooks().isEmpty()) {
            for (Book books : user.getInventoryOfBooks()) {
                System.out.println("\u001b[37;1m" + count++ + " - " + books.getBookName() + "\u001b[0m");
            }
        } else {
            System.out.println("\n\u001b[31;1mYou don't have any book.\u001b[0m\n");
        }
    }

    public void requestBook(Person user, ArrayList<Book> books) {
        if (user.isMember()) {

            if (!books.isEmpty()) {
                getBookList(books);
                System.out.println("\n\u001b[31;1mWhat book you want to request?\u001b[0m\n");
                System.out.print("\u001b[31;1mPlease select a option: \u001b[0m\n");
                int inputUser = sc.nextInt();
                if (inputUser >= 1 && inputUser <= books.size()) {
                    Book requestedBook = books.get(inputUser - 1);

                    String timeToReturn = getRequestBookTime();
                    System.out.println("\n\u001b[32;1mYou need to return the book until \u001b[31;1m" + timeToReturn + "\u001b[0m");

                    user.getInventoryOfBooks().add(requestedBook);
                    books.remove(requestedBook);
                    System.out.println("\n\u001b[32;1mYou now have the " + requestedBook.getBookName() + " book!\u001b[0m\n");
                } else {
                    System.out.println("\n\u001b[31;1mBook not found.\u001b[0m\n");
                }
            } else {
                System.out.println("\n\u001b[31;1mThis library don't have any book.\u001b[0m\n");
            }
        } else {
            System.out.println("\n\u001b[31;1mYou can't request a book because you're not a library member.\u001b[0m\n");
        }
    }

    public void returnBook(Person user, ArrayList<Book> books) {
        if (!user.getInventoryOfBooks().isEmpty()) {
            checkMyBooks(user);

            System.out.println("\n\u001b[31;1mWhat book you want to return?\u001b[0m ");
            System.out.print("\u001b[31;1mPlease select a option: \u001b[0m\n");
            int inputUser = sc.nextInt();

            if (inputUser >= 1 && inputUser <= user.getInventoryOfBooks().size()) {
                Book returnBook = user.getInventoryOfBooks().get(inputUser - 1);

                books.add(returnBook);
                user.getInventoryOfBooks().remove(returnBook);
                System.out.println("\n\u001b[32;1mYou returned the " + returnBook.getBookName() + " book!\u001b[0m\n");
            } else {
                System.out.println("\n\u001b[31;1mYou don't have this book.\u001b[0m\n");
            }
        } else {
            System.out.println("\n\u001b[31;1mYou don't have any book.\u001b[0m\n");
        }
    }

    public void changePassword(Person user) {
        System.out.print("\u001b[32;1mWhat is your old password?\u001b[0m ");
        String oldPassword = sc.next();
        if (oldPassword.equals(user.getPassword())) {
            System.out.print("\u001b[32;1mNew password:\u001b[0m ");
            user.setPassword(sc.next());
            System.out.println("\n\u001b[32;1mPassword changed!\u001b[0m\n");
        } else {
            System.out.println("\n\u001b[31;1mInvalid password.\u001b[0m\n");
        }
    }

    public String getRequestBookTime() {
        Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        calendar.set(Calendar.MONTH, Calendar.NOVEMBER);

        return calendar.get(Calendar.DAY_OF_MONTH) + 15 + "/" + calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.YEAR) + ".";
    }

    public void getLibraryBalance(Library library) {
        System.out.println("\n\u001b[32;1mLibrary balance: " + "\u001b[0m" + library.getLibraryBankAccount().getBalance() + "$\n");
    }

    public void removeBook(ArrayList<Book> books) {
        getBookList(books);

        if (!books.isEmpty()) {

            System.out.println("\n\u001b[31;1mWhat book you want to remove?\u001b[0m");
            System.out.print("\u001b[31;1mPlease select a option: \u001b[0m\n");

            int userInput = sc.nextInt();

            if (books.size() >= userInput && books.get(userInput - 1) != null) {
                books.remove(userInput - 1);
                System.out.println();
                System.out.println("\n\u001b[31;1mBook removed.\u001b[0m\n");
                System.out.println();
            } else {
                System.out.println();
                System.out.println("\n\u001b[31;1mBook not found.\u001b[0m\n");
                System.out.println();
            }
        } else {
            System.out.println("\n\u001b[31;1mThis library don't have any book.\u001b[0m\n");
        }
    }

    public void setMembershipPrice(Library library) {
        System.out.println("\n\u001b[31;1mSet a price! \u001b[37;1mDefault: 25\u001b[0m");
        System.out.print("\u001b[37;1mNew price: \u001b[0m");
        double price = sc.nextDouble();
        System.out.println("\n\u001b[32;1mNew price setted.\u001b[0m\n");
        library.setMembershipPrice(price);
    }

    public void bankOnline(Person user) {
        boolean loggedInBank = false;

        if (user.getBankAccount().getPin() == null) {
            System.out.println("\n\u001b[32;1mChoose a pin with 4 digits.\u001b[0m");
            System.out.print("\u001b[32;1mPin: \u001b[0m");
            String pin = sc.next();
            if (pin.length() == 4) {
                user.getBankAccount().setPin(pin);
                System.out.println("\u001b[32;1mPin set.\u001b[0m\n");
            } else {
                System.out.println("\n\u001b[31;1mYou need add a pin with 4 digits.\u001b[0m\n");
            }
        } else {
            System.out.println("\u001b[32;1mEnter bank pin:\u001b[0m");
            System.out.print("\u001b[32;1mPin: \u001b[0m");
            String pin = sc.next();
            if (user.getBankAccount().getPin().equals(pin)) {
                System.out.println("\u001b[32;1mWelcome, " + user.getName() + "!\u001b[0m\n");
                loggedInBank = true;
            } else {
                System.out.println("\u001b[31;1mWrong pin.\u001b[0m\n");
            }
        }

        while (loggedInBank) {
            System.out.println("\u001b[37;1m1 - CHECK BALANCE\u001b[0m");
            System.out.println("\u001b[37;1m2 - CHANGE PIN\u001b[0m");
            System.out.println("\u001b[37;1m3 - LOGOUT FROM BANK\u001b[0m");

            switch (sc.next().replaceAll("[^0-9]", "")) {
                case "1":
                    System.out.println("\u001b[32;1mBalance: \u001b[0m" + user.getBankAccount().getBalance());
                    break;
                case "2":
                    System.out.println("\u001b[32;1mYour old pin:\u001b[0m");
                    String oldPin = sc.next();
                    if (user.getBankAccount().getPin().equals(oldPin)) {
                        System.out.print("\u001b[32;1mNew pin: \u001b[0m");
                        String newPin = sc.next();
                        if (newPin.length() == 4) {
                            user.getBankAccount().setPin(newPin);
                            System.out.println("\u001b[32;1mPin set.\u001b[0m\n");
                        } else {
                            System.out.println("\n\u001b[31;1mThe pin length is invalid.\u001b[0m\n");
                        }
                    } else {
                        System.out.println("\n\u001b[31;1mWrong pin.\u001b[0m\n");
                    }
                    break;
                case "3":
                    System.out.println("\n\u001b[31;1mLogged out.\u001b[0m\n");
                    loggedInBank = false;
                    break;
                default:
                    System.out.println("\u001b[31;1mInvalid option.\u001b[0m");
                    break;
            }
        }
    }
}
