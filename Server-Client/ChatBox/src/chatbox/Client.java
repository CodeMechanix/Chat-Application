package chatbox;

import java.io.*;
import java.net.*;

class Client {

    public static void main(String argv[]) throws Exception {
        String sentence;
        String modifiedSentence;
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

        Socket clientSocket = new Socket("localhost", 6789);

        DataInputStream inFromServer = new DataInputStream(clientSocket.getInputStream());
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

        InputStreamReader inputStreamReader = new InputStreamReader(inFromServer);
        BufferedReader bufferReader = new BufferedReader(inputStreamReader);
        int i = 0;
        String name = null;
        while (true) {
            if (i <= 1) {
                System.out.print("Client: ");
                sentence = inFromUser.readLine();
                name = sentence;
                outToServer.writeBytes(sentence + '\n');
                modifiedSentence = inFromServer.readLine();
                System.out.println("From Server : " + modifiedSentence);
                i++;
            } else if (i == 2) {
                System.out.print(name + " : ");
                sentence = inFromUser.readLine();
                outToServer.writeBytes(sentence + '\n');
                modifiedSentence = inFromServer.readLine();
                System.out.println("From Server : " + modifiedSentence);
            } else {
                System.out.print(name + " : ");
                sentence = inFromUser.readLine();
                outToServer.writeBytes(sentence + '\n');
                modifiedSentence = inFromServer.readLine();
                System.out.println("From Server : " + modifiedSentence);
            }
        }
    }
}
