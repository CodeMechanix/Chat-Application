package chatbox;
import java.io.*;
import java.net.*;
public class Server
{
    public static void main(String argv[]) throws Exception
    {
        int workerThreadCount = 0;
        int id = 1;
        ServerSocket serverSocket = new ServerSocket(6789);
        while(true)
        {
            Socket connectionSocket = serverSocket.accept();
            WorkerThread wt = new WorkerThread(connectionSocket,id);
            Thread t = new Thread(wt);
            t.start();
            workerThreadCount++;
            System.out.println("Client [" + id + "] is now connected. No. of worker threads = " + workerThreadCount);
            id++;
        }
		
    } 
}
class WorkerThread implements Runnable
{
    private Socket connectionSocket;
    private int id;
    public WorkerThread(Socket ConnectionSocket, int id) 
    {
        this.connectionSocket=ConnectionSocket;
        this.id=id;
    }
    public void run()
    {
        String clientSentence;
        String capitalizedSentence;
        int i = 0;
        while(true)
        {
            try
            {
                DataInputStream inFromClient = new DataInputStream(connectionSocket.getInputStream());
                DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
                                
                InputStreamReader inputStreamReader = new InputStreamReader(inFromClient);
                BufferedReader bufferReader = new BufferedReader(inputStreamReader);    
                
                clientSentence = bufferReader.readLine();
                if (i==0){
                    i++;
                    outToClient.writeBytes("Enter Your Name: " + '\n');
                }
                else if(i==1){
                    i++;
                    outToClient.writeBytes("Thank You "+clientSentence + '\n');
                }else if(i >= 2){
                
                capitalizedSentence = clientSentence.toUpperCase();
                
                outToClient.writeBytes(capitalizedSentence + '\n');
                }
                
            }
            catch(Exception e)
            {
                
            }
        }
    }
}