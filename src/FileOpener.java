import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class FileOpener {

    public static void main(String[] args) throws Exception {
        File fileObj = new File("src/data.csv");

        System.out.println(fileObj.exists()+" "+fileObj.canRead());

        Scanner fileReader = new Scanner(fileObj);
        while (fileReader.hasNextLine()){
            System.out.println(fileReader.nextLine());
        }
        fileReader.close();
    }
}
