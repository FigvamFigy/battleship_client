package network.timed_connection;

import network.Client;

import java.net.InetSocketAddress;
import java.util.ArrayList;

/**
 * This class is a handler that is used in Client.java to handle the connection (which in this case would only be the server) closures if a response was not recieved
 * from the "CLIENT_TO_SERVER_CONNECTION_CHECK" tag. If the server has not responded after a certain amount of time, arrayListAddressesToClose will be filled
 * with the connections needed to close. Then, the client will remove its keys and close the server connection
 *
 * Thread: clientThread
 */
public class TimedConnectionHandler {


    private volatile static ArrayList<TimedConnection> arrayListTimedConnection;
    private volatile static ArrayList<InetSocketAddress> arrayListAddressesToClose;

    public TimedConnectionHandler() {
        arrayListTimedConnection = new ArrayList<>();
        arrayListAddressesToClose = new ArrayList<>();
    }


    public void addTimedConnection(InetSocketAddress address){
        TimedConnection timedConnection = new TimedConnection(address);
        arrayListTimedConnection.add(timedConnection);
        timedConnection.startTimedConnection();
    }


    public static void addConnectionToClose(InetSocketAddress address){
        arrayListAddressesToClose.add(address);
    }


    public void closeTimedConnection(InetSocketAddress address){
        arrayListAddressesToClose.remove(address);
        arrayListTimedConnection.remove(address);
    }

    public synchronized static void continueTimedConnection(InetSocketAddress address){
        for(TimedConnection connection: arrayListTimedConnection){
            if(connection.getAddress().toString().equals(address.toString())){
                connection.resetTimedConnection();
                System.out.println("Held connection for: " + connection.getAddress());
            }

        }

    }


    public boolean hasConnectionsToClose(){
        if(arrayListAddressesToClose.isEmpty()){
            return false;
        }
        return true;
    }





}
