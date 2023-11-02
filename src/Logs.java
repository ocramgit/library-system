import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class Logs {

    File file;
    FileWriter fileWriter;

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

}
