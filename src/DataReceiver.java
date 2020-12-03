import java.io.*;
import java.net.*;

public class DataReceiver {
    public static void main(String[] args) throws Exception {

        //Creating socket
        Socket socket = new Socket("localhost",888);

        //Getting server output
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

        //Getting ser
        BufferedReader senderReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        BufferedReader keyboardReader = new BufferedReader( new InputStreamReader(System.in));

        String filePath = createFile();

        FileWriter writer = new FileWriter(filePath);

        String messageSent,messageGot;
        messageSent = "getting information ";
        while(!(messageSent).equals("CLOSEING_CONNECTION")){
            dataOutputStream.writeBytes(messageSent+"\n");
            messageSent = messageGot = senderReader.readLine();
            System.out.println(messageGot);
            writer.write(messageGot+"\n");
        }
        writer.close();
        dataOutputStream.close();
        senderReader.close();
        keyboardReader.close();
        socket.close();

    }

    public static String createFile() throws IOException {
        String filePath = "src/receivedData.csv";
        File newFile = new File(filePath);
        if(newFile.createNewFile()){

        }else{
            System.out.println("File already exists.");
        }
        return filePath;
    }
}
