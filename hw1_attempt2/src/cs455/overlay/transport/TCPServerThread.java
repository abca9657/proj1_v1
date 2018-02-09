package cs455.overlay.transport;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import cs455.overlay.node.RegEntry;


// This is where you will have your serverSocket
// needs constructor to take ef (event factory)
// and create connection needs to take ef as well
public class TCPServerThread implements Runnable {
	int localPortNum ;
	@Override
	public void run() {

		// Create ServerSocket for listening and get information for registry: 
		try(ServerSocket servSock = new ServerSocket(0)) {
			int localPortNum = servSock.getLocalPort();
			InetAddress addr = servSock.getInetAddress();
			byte[] localAddr = addr.getAddress();
			byte localAddrLen = (byte) localAddr.length;
			
			RegEntry message = new RegEntry(OVERLAY_NODE_SENDS_REGISTRATION, localAddrLen, localAddr, localPortNum);	
			// pack this data and send to register with message type
			// need to create a (thread?) with ServerSocket.accept()
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}

