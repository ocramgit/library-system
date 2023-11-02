import java.util.Random;

public class Job {

    public void work(Person person) throws InterruptedException {
        int count = 7;
        int salary = getSalary();
        while(count != 0) {
            Thread.sleep(500);
            System.out.println("\u001b[31;1mYour shift ends in " + count--+"\u001b[0m");
        }
        person.getBankAccount().setBalance(person.getBankAccount().getBalance() + salary);
        System.out.println("\n\u001b[32;1mYou won "+salary+"$\u001b[0m\n");
    }

    public int getSalary() {
        Random random = new Random();
        return random.nextInt((50 - 8) + 1) + 8;
    }
}
