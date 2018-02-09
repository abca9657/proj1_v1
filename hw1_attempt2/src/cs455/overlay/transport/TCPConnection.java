package cs455.overlay.transport;

import java.net.Socket;

// Should have constructor TCPConnection(clientSocket) in order to create TCPSenderThread (on the socket) and 
// TCPReceiverThread (also on the socket)
// constructor should also take event factory so you can call ef.CreateEvent to return type Event (which is essentially which message to deal with)
public class TCPConnection {
	Socket clientSock;
	
	
	
	public TCPConnection(Socket clientSock) {
		this.clientSock = clientSock;
		
	}

}
