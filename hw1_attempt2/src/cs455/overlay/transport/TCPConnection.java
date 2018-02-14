package cs455.overlay.transport;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import cs455.overlay.node.Node;
import cs455.overlay.wireformats.EventFactory;

// Should have constructor TCPConnection(clientSocket) in order to create TCPSenderThread (on the socket) and 
// TCPReceiverThread (also on the socket)
// constructor should also take event factory so you can call ef.CreateEvent to return type Event (which is essentially which message to deal with)
public class TCPConnection {
	final Socket clientSock;
	final byte[] data;
	Node nodeType;

	
	public TCPConnection(Socket clientSock, byte[] data, Node nodeType) throws IOException {
		this.clientSock = clientSock;
		this.data = data;
		this.nodeType = nodeType;
		
		if(data != null) {
			TCPSender sender = new TCPSender(this.clientSock);
			sender.sendData(data);
			// create sender thread?: 
			
		}
		else {
			// when you receive a message, use Node.onEvent to get it?
			TCPReceiver receiver = new TCPReceiver(clientSock, nodeType);
			Thread t = new Thread(receiver);
			t.start();
			
		}
	}

}


