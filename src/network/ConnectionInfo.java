package network;

import java.net.InetAddress;
import java.net.InetSocketAddress;

/**
 * A lot of times we will need to know information about the server/client. This is where we set it up to avoid cluttering the Client.java with static variables and methods
 *
 * Thread: n/a
 */
public class ConnectionInfo {


    //Server detials
    private static InetSocketAddress serverSocketAddress;


    //Client details
    private static InetSocketAddress clientSocketAddress;


    public static InetSocketAddress getServerSocketAddress() {
        return serverSocketAddress;
    }

    public static void setServerSocketAddress(InetSocketAddress serverSocketAddress) {
        ConnectionInfo.serverSocketAddress = serverSocketAddress;
    }

    public static InetSocketAddress getClientSocketAddress() {
        return clientSocketAddress;
    }

    public static void setClientSocketAddress(InetSocketAddress clientSocketAddress) {
        ConnectionInfo.clientSocketAddress = clientSocketAddress;
    }
}
