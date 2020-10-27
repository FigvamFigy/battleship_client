package network;

import graphicLogic.MainLogic;
import javafx.application.Platform;
import network.protocol_classes.DataDecider;
import network.timed_connection.TimedConnectionHandler;
import thread.DataProcessingThread;
import util.EnumScene;

import java.io.IOException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
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

    //TimedConnectionHandler
    private TimedConnectionHandler timedConnectionHandler;

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

            //TimedConnectionHandler
            timedConnectionHandler = new TimedConnectionHandler();




            connect(new InetSocketAddress(serverAddress,serverPort));
            //connect(InetSocketAddress.createUnresolved(serverAddress,serverPort));


            if(clientSocket.isConnected()){//If there is a correct connection made, it will continue
                System.out.println("Successfully Connected");


                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        MainLogic.createPlayers();
                        MainLogic.showScene(EnumScene.sceneGameBoard);
//
//                        MainLogic.createClientPlayer();
//                        MainLogic.showScene(EnumScene.sceneGameBoard);
//                        MainLogic.updateBoard();

                    }
                });


                //Set the connection info
                ConnectionInfo.setClientSocketAddress((InetSocketAddress) clientSocket.getLocalAddress());
                ConnectionInfo.setServerSocketAddress((InetSocketAddress)clientSocket.getRemoteAddress());

                DataProcessingThread dataProcessingThread = new DataProcessingThread();
                dataProcessingThread.start();

                //Set timed connection
                timedConnectionHandler.addTimedConnection((InetSocketAddress)clientSocket.getRemoteAddress());


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
                Thread.sleep(1000);

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

                if(timedConnectionHandler.hasConnectionsToClose()){
                    closeConnections();
                }
            }

        }
        catch (Exception exception){
            exception.printStackTrace();
        }
    }


    private void sendData(){
        try{

            System.out.println("DATA SENT: " + OutgoingDataQueue.peakData());
            ByteBuffer byteBuffer = ByteBuffer.wrap(OutgoingDataQueue.getData().getParsedData().getBytes());
            clientSocket.write(byteBuffer);

        }
        catch (IOException ioException){

        }
        catch (Exception exception){
            exception.printStackTrace();
        }
    }

    private void readData(){
        try{
            ByteBuffer byteBuffer = ByteBuffer.allocate(2048);
            clientSocket.read(byteBuffer);

            String result = new String(byteBuffer.array()).trim();

            if(!result.equals("")){//If its not empty
                System.out.println("READ DATA: " + result);
                IncomingDataQueue.addToQueue(result);
            }


        }
        catch (IOException ioException){

        }
        catch (Exception exception){
            exception.printStackTrace();
        }

    }

    private void closeConnections(){
        try{
            System.out.println("Closed connection to: " + clientSocket.getRemoteAddress());

            DataDecider.shouldRun = false;
            selector = null;
            timedConnectionHandler.closeTimedConnection((InetSocketAddress)clientSocket.getRemoteAddress());
            clientSocket.close();
            isClientRunning = false;
        }
        catch (Exception exception){
            exception.printStackTrace();
        }


    }





}
