package network.protocol_classes;

import network.OutgoingDataQueue;
import network.timed_connection.TimedConnectionHandler;
import util.EnumDataPurposeTag;
import util.parser.DataWriteParser;

import java.net.InetSocketAddress;

/**
 *
 * thread: dataProcessing
 */
public class ClientToServerConnectionCheckProtocolClass implements IProtocolClass{



    private InetSocketAddress senderIP;
    private InetSocketAddress data;

    public ClientToServerConnectionCheckProtocolClass(InetSocketAddress senderIP, InetSocketAddress data) {
        this.senderIP = senderIP;
        this.data = data;

    }

    @Override
    public void execute(){
        TimedConnectionHandler.continueTimedConnection(senderIP);

    }



}
