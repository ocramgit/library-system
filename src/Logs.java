import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Logs {

    private File file;
    private FileWriter fileWriter;

    public Logs() {
        file = new File("logs.txt");
    }

    public void writeOnLog(String whatYouWantWrite) throws IOException {
        fileWriter = new FileWriter(file,true);
        fileWriter.write(whatYouWantWrite);
        fileWriter.flush();
        fileWriter.close();
    }

    public void readLastLog() throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(file.getAbsolutePath()));
        String line = in.readLine();

        System.out.println("\u001b[41;1mLogs:\u001b[0m");

            while (line != null) {
                System.out.println(line);
                line = in.readLine();
            }

        System.out.println("\u001b[41;1mAll logs listed.\u001b[0m");

        in.close();
    }

    public void getUserLogs() throws IOException {

        BufferedReader in = new BufferedReader(new FileReader(file.getAbsolutePath()));

        Scanner sc = new Scanner(System.in);

        System.out.print("\n\u001b[31;1mWrite a username:\u001b[0m ");
        String username = sc.nextLine();
        writeOnLog("Admin searched log of " + username.toLowerCase() + " at "+ LocalDate.now() + " | " + LocalTime.now() + "\n");

        String line;
        boolean userFound = false;

        while ((line = in.readLine()) != null) {
            if (line.contains(username)) {
                System.out.println(line);
                userFound = true;
            }
        }

        in.close();

        if (!userFound) {
            System.out.println("\n\u001b[31;1mUser not found.\u001b[0m");
        }
    }
}
