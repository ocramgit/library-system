import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Logs {

    File file;
    FileWriter fileWriter;

    public Logs() throws IOException {
       file = new File("logs.txt");
       fileWriter = new FileWriter(file);
    }

    public void writeOnLog(String whatYouWantWrite) throws IOException {
        fileWriter.write(whatYouWantWrite);
        fileWriter.flush();
        System.out.println("Writed on log.");
    }

}
