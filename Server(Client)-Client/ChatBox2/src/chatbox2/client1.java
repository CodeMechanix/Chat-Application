package chatbox2;

import java.io.*;
import java.net.*;

class client1 {

    public static void main(String argv[]) throws Exception {
        String sentence;
        String modifiedSentence;
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

        Socket clientSocket = new Socket("localhost", 6789);

        DataInputStream inFromServer = new DataInputStream(clientSocket.getInputStream());
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

        InputStreamReader inputStreamReader = new InputStreamReader(inFromServer);
        BufferedReader bufferReader = new BufferedReader(inputStreamReader);

        while (true) {

            System.out.print("Client 01: ");
            sentence = inFromUser.readLine();
            outToServer.writeBytes(sentence + '\n');
            modifiedSentence = inFromServer.readLine();
            System.out.println("Client 02: " + modifiedSentence);

        }
    }
}
