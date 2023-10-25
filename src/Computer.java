import java.util.ArrayList;
import java.util.Scanner;

public class Computer {

    Scanner sc;

    public Computer() {
        sc = new Scanner(System.in);
    }

    public void getBookList(ArrayList<Book> books) {
        int countBooks = 1;

        for (Book booksList : books) {
            System.out.println(countBooks++ + ": " + booksList.getBookName());
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
        books.add(new Book(sc.next()));
        System.out.println("Book added!");
    }

    public void getMembership(Person user) {
        if(!user.isMember) {
            user.isMember = true;
            System.out.println("You're now member at this Library.");
        } else {
            System.out.println("You already are a member.");
        }
    }

    public void checkMyBooks(Person person) {
        int count = 1;

        for (Book books : person.getRequestedBooks()) {
            System.out.println(count++ + " " + books.getBookName());
        }
    }
}
