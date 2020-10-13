package network;

import java.net.ConnectException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Set;


/**
 * This class is responsible for holding and taking care of the connection made with the server. This class not handle the data except for reading it and sending it from
 * IncomingData.java and OutgoingData.java
 *
 * Thread: clientThread
 */

public class Client {

    private boolean isClientRunning;

    //Client Stuff
    private SocketChannel clientSocket;
    private Selector selector;

    //Outgoing/incoming data
    private OutgoingDataQueue outgoingDataQueue;
    private IncomingDataQueue incomingDataQueue;

    public Client() {

        this.isClientRunning = false;


    }


    /**
     * This method is called from the ClientThread.java.
     * This method creates instances of classes that need it and attempts to connect with the server
     *
     * @param serverAddress the address of the server
     * @param serverPort the port of the server
     */
    public void startClient(String serverAddress, int serverPort){
        try{
            System.out.println("--Starting Client--");

            selector = Selector.open();


            //Outgoing/incoming data initialization
            this.outgoingDataQueue = new OutgoingDataQueue();
            this.incomingDataQueue = new IncomingDataQueue();

            connect(new InetSocketAddress(serverAddress,serverPort));
            //connect(InetSocketAddress.createUnresolved(serverAddress,serverPort));


            if(clientSocket.isConnected()){//If there is a correct connection made, it will continue
                System.out.println("Successfully Connected");

                //Set the connection info
                ConnectionInfo.setClientSocketAddress((InetSocketAddress) clientSocket.getLocalAddress());
                ConnectionInfo.setServerSocketAddress((InetSocketAddress)clientSocket.getRemoteAddress());


                isClientRunning = true;
                clientLoop();
            }

        }
        catch (Exception exception){
            exception.printStackTrace();
        }


    }

    /**
     * This method will actually try to connect to the server
     *
     * @param clientSocketAddress contains both the ip and the port
     */
    private void connect(InetSocketAddress clientSocketAddress){
        try{
            System.out.println("Connecting to Server");

            clientSocket = SocketChannel.open();

            clientSocket.connect(clientSocketAddress);
            clientSocket.configureBlocking(false);
            clientSocket.finishConnect();

            clientSocket.register(selector, SelectionKey.OP_WRITE | SelectionKey.OP_READ | SelectionKey.OP_CONNECT);


            System.out.println("Target IP: " + clientSocketAddress.toString());
            System.out.println("Is connected to server: " + clientSocket.isConnected());
            System.out.println("Client IP: " + clientSocket.getLocalAddress());
        }
        catch (ConnectException connectException){
            System.out.println("Connection failed. Connection refused: connect");
        }
        catch (Exception exception){
            exception.printStackTrace();
        }


    }


    /**
     * This is the main loop that the client goes through two main part:reading data from the server and writing data to the server
     *
     */
    private void clientLoop(){
        try{
            System.out.println("--Entering Client Loop--");

            while (isClientRunning) {
                selector.select();

                Set<SelectionKey> selectionKeySet = selector.selectedKeys();

                for (SelectionKey key : selectionKeySet) {

                    if (key.isWritable() && OutgoingDataQueue.hasData()) {
                        sendData();
                    }

                    if (key.isReadable()) {
                        readData();
                    }

                }
            }

        }
        catch (Exception exception){
            exception.printStackTrace();
        }
    }


    private void sendData(){

    }

    private void readData(){

    }


}
