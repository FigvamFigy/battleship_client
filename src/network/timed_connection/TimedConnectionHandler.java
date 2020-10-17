package network.timed_connection;

import network.Client;

import java.net.InetSocketAddress;
import java.util.ArrayList;

/**
 * This class is a handler that is used in Client.java to handle the connection (which in this case would only be the server) closures if a response was not recieved
 * from the "CLIENT_TO_SERVER_CONNECTION_CHECK" tag. If the server has not responded after a certain amount of time, arrayListAddressesToClose will be filled
 * with the connections needed to close. Then, the client will remove its keys and close the server connection
 *
 */
public class TimedConnectionHandler {


    private static ArrayList<TimedConnection> arrayListTimedConnection;
    private static ArrayList<InetSocketAddress> arrayListAddressesToClose;

    public TimedConnectionHandler() {
        arrayListTimedConnection = new ArrayList<>();
        arrayListAddressesToClose = new ArrayList<>();
    }


    public void addTimedConnection(InetSocketAddress address){
        TimedConnection timedConnection = new TimedConnection(address);
        arrayListTimedConnection.add(timedConnection);
    }


    public static void addConnectionToClose(InetSocketAddress address){
        arrayListAddressesToClose.add(address);
    }


    public void closeTimedConnection(InetSocketAddress address){
        arrayListAddressesToClose.remove(address);
        arrayListTimedConnection.remove(address);
    }

    public static void continueTimedConnection(InetSocketAddress address){
        for(TimedConnection connection: arrayListTimedConnection){
            if(connection.getAddress().equals(address)){
                connection.resetTimedConnection();
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
