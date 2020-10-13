package thread;

import network.Client;
import util.Constants;

import java.net.InetAddress;

/**
 * This thread is called from the JavaFX to start the client
 *
 * Thread: JavaFX Thread
 */
public class ClientThread implements Runnable{


    private Thread thread;

    private String serverIP;
    private int serverPort;

    public ClientThread(String serverIP, int serverPort) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
    }



    @Override
    public void run() {
        try{
            Client client = new Client();
            client.startClient(serverIP,serverPort);

        }
        catch (Exception exception){
            exception.printStackTrace();
        }
    }


    public void start(){
        if(thread == null){
            thread = new Thread(this, Constants.CLIENT_THREAD_NAME);
            thread.start();
        }
    }
}
