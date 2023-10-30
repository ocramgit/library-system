import java.util.*;

public class Computer {

    Scanner sc;

    public Computer() {
        sc = new Scanner(System.in);
    }

    public void getBookList(ArrayList<Book> books) {
        int countBooks = 1;

        for (Book booksList : books) {
            System.out.println(countBooks++ + ": " + booksList.getBookName() + " from " + booksList.getAuthor());
        }
    }

    public void getMembershipStatus(Person person) {
        if(person.isMember) {
            System.out.println("You're a library member.");
        } else {
            System.out.println("You're not a library member.");
        }
    }

    public void registerOnSystemANewUser(ArrayList<Person> users) {
        System.out.println("Name of person: ");
        String name = sc.nextLine();
        System.out.println("Username: ");
        String username = sc.nextLine();
        System.out.println("Password: ");
        String password = sc.nextLine();
        users.add(new Person(name, username, password));
        System.out.println("Add him as a library member?\n1 - YES\n2 - NO");
        if (sc.nextInt() == 1) {
            for (Person user : users) {
                if (user.getName().equals(name)) {
                    user.setMember(true);
                    System.out.println("Added on membership club!");
                }
            }
        }
        System.out.println("Member added with success!");
    }

    public void registerUser(ArrayList<Person> users) {
        System.out.println("Name of person: ");
        String name = sc.next();
        System.out.println("Username: ");
        String username = sc.next();
        System.out.println("Password: ");
        String password = sc.next();
        users.add(new Person(name, username, password));
        System.out.println("Member added with success!");
    }

    public void getUsersList(ArrayList<Person> users) {

        int countUsers = 1;

        for (Person user : users) {
            System.out.println(countUsers++ + ": " + user.getName() + "| Username: "+ user.getUsername() + " MEMBER: " + user.isMember);
        }
    }

    public void removeUserFromSystem(ArrayList<Person> users) {
        getUsersList(users);

        System.out.println("What user you want to remove?");
        users.remove(sc.nextInt() - 1);
        System.out.println("User removed.");
    }

    public void addBook(ArrayList<Book> books) {
        System.out.println("Book name: ");
        String bookName = sc.nextLine();
        System.out.println("Author: ");
        String author = sc.nextLine();
        books.add(new Book(bookName, author));
        System.out.println("Book added!");
    }

    public void getMembership(Library library, Person user, double membershipPrice) {
        if(!user.isMember) {
            if(user.getBankAccount().getBalance() > 25) {
                user.isMember = true;
                user.getBankAccount().setBalance(user.getBankAccount().getBalance()-membershipPrice);
                library.getLibraryBankAccount().setBalance(library.getLibraryBankAccount().getBalance()+membershipPrice);
                System.out.println("You're now a member of this library.");
                System.out.println("We debited 25$ from your bank account.");
            } else {
                System.out.println("You don't have money to buy membership.");
            }
        } else {
            System.out.println("You already are a member.");
        }
    }

    public void checkMyBooks(Person user) {
        int count = 1;

        for (Book books : user.getInventoryOfBooks()) {
            System.out.println(count++ + " " + books.getBookName());
        }
    }

    public void requestBook(Person user, ArrayList<Book> books) {

        getBookList(books);

        System.out.println("What book you want to request?");
        int inputUser = sc.nextInt();
        Book requestedBook = books.get(inputUser-1);

        if(user.isMember()) {
            System.out.println("You need to return the book until "+getRequestBookTime());

            user.getInventoryOfBooks().add(requestedBook);
            books.remove(requestedBook);
            System.out.println("You now have the " + requestedBook.getBookName() + " book!");
        } else {
            System.out.println("You can't request a book because you're not a library member.");
        }
    }

    public void returnBook(Person user, ArrayList<Book> books) {
        checkMyBooks(user);

        System.out.println("What book you want to return?");
        int inputUser = sc.nextInt();
        Book returnBook = user.getInventoryOfBooks().get(inputUser-1);

        books.add(returnBook);
        user.getInventoryOfBooks().remove(returnBook);
        System.out.println("You returned the " + returnBook.getBookName() + " book!");
    }

    public void changePassword(Person user) {
        System.out.println("What is your old password?");
        String oldPassword = sc.nextLine();
        if(oldPassword.equals(user.getPassword())) {
            System.out.print("New password: ");
            user.setPassword(sc.nextLine());
            System.out.println("Password changed!");
        } else {
            System.out.println("Invalid password.");
        }
    }

    public String getRequestBookTime() {
        Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        calendar.set(Calendar.MONTH, Calendar.NOVEMBER);

        return calendar.get(Calendar.DAY_OF_MONTH)+5 + "/" + calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.YEAR) + ".";
    }

    public void getLibraryBalance(Library library) {
        System.out.println("Library balance: " + library.getLibraryBankAccount().getBalance()+"$");
    }
 }
