import com.sun.security.ntlm.Server;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class DataServer {

    //Function to check file is exist and readable
    public static boolean checkFileExists(File file){
        return file.exists()  && file.canRead();
    }

    public static void main(String args[]) throws Exception{

        boolean fileExistsFlag;

        //Server socket obeject
        ServerSocket serverSocket = new ServerSocket(888);

        //Listening for connection
        Socket socket = serverSocket.accept();
        System.out.println("Connection established!");

        //Sending data to client
        PrintStream printStream = new PrintStream(socket.getOutputStream());

        //Reading data from client
        BufferedReader clientReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        //Reading data from keyboard
        BufferedReader keyboardReader = new BufferedReader(new InputStreamReader(System.in));

        //File object
        File fileObj = new File("src/data.csv");

        fileExistsFlag =checkFileExists(fileObj);

        //Getting file data
        Scanner fileReader = new Scanner(fileObj);

        String dataToSend;
        String closeTransferMsg = "CLOSEING_CONNECTION";
        while (fileReader.hasNextLine()){
            dataToSend = fileReader.nextLine();
            printStream.println(dataToSend);
        }
        printStream.println(closeTransferMsg);

        while (true){
            String messageIn,messageOut;
            while ((messageIn = clientReader.readLine())!= null ){
                while (fileReader.hasNextLine()){
                    messageOut = fileReader.nextLine();
                    printStream.println(messageOut);
                }
                printStream.println("CLOSEING_CONNECTION");
            }
            System.out.println("Data Transfered Successfully");


            //Releasing the resources
            fileReader.close();
            printStream.close();
            clientReader.close();
            keyboardReader.close();
            serverSocket.close();
            socket.close();
            System.exit(0);
        }
    }
}
