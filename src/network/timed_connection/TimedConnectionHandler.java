package network.timed_connection;

import network.Client;

import java.net.InetSocketAddress;
import java.util.ArrayList;

public class TimedConnectionHandler {

    private Client client;

    private static ArrayList<ConnectionMessageSender> arrayListConnectionMessageSender;

    private static ArrayList<InetSocketAddress> arrayListAddressesToClose;

    public TimedConnectionHandler(Client client) {
        this.client = client;

        arrayListConnectionMessageSender = new ArrayList<>();
        arrayListAddressesToClose = new ArrayList<>();
    }


    public void addTimedConnection(InetSocketAddress address){



    }


    public static void addConnectionToClose(InetSocketAddress address){
        arrayListAddressesToClose.add(address);
    }


    public void closeTimedConnection(InetSocketAddress address){
        arrayListAddressesToClose.remove(address);
        arrayListConnectionMessageSender.remove(address);
    }

    public static void continueTimedConnection(InetSocketAddress address){

    }





}
