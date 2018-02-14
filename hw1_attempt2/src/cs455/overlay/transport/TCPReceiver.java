package cs455.overlay.transport;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

import cs455.overlay.node.Node;
import cs455.overlay.wireformats.EventFactory;

public class TCPReceiver implements Runnable {
	
	private Socket socket; 
	private DataInputStream din;
	private Node nodeType;
	EventFactory efSingleton;
	
	public TCPReceiver(Socket socket, Node nodeType) throws IOException {
		this.socket = socket; 
		din = new DataInputStream(socket.getInputStream());
		this.efSingleton = EventFactory.getInstance();
		this.nodeType = nodeType;
	}
	
	public void run() {
		
		int dataLength; 
		while(socket != null) {
			try {
				dataLength = din.readInt();
				
				byte[] data = new byte[dataLength];
				din.readFully(data, 0,  dataLength);
				
				nodeType.onEvent(efSingleton.createEvent(data));
								
			} catch (SocketException se) {
				System.out.println(se.getMessage());
				break;
			} catch (IOException ioe) {
				System.out.println(ioe.getMessage());
				break;
			}
		}
	}

}
