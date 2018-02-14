package cs455.overlay.node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import cs455.overlay.transport.TCPConnection;
import cs455.overlay.transport.TCPServerThread;
import cs455.overlay.wireformats.Event;
import cs455.overlay.wireformats.EventFactory;
import cs455.overlay.wireformats.OVERLAY_NODE_SENDS_REGISTRATION;
import cs455.overlay.wireformats.REGISTRY_REPORTS_REGISTRATION_STATUS;

public class MessagingNode implements Node {
	private final int regPortNum;
	private final String hostName;

	public static void main(String[] args) throws InterruptedException, IOException {
		// parse input arguments from user: 
		if (args.length != 2) {
			System.err.println("Usage: java cs455.overlay.node.MessagingNode <RegistryHost> <RegistryPort>");
			System.exit(1);
		}
		String hostName = args[0];
		int regPortNum = Integer.parseInt(args[1]);
		System.out.println("parsed input");
		
		MessagingNode msgNode = new MessagingNode(regPortNum, hostName);
		System.out.println("MessagingNode object called");
		//Node nodeObj = (Node) efObj;
		
	} // end of mainn
	


	public MessagingNode(int regPortNum, String hostName) throws IOException {
		//System.out.println("reached MessagingNode");
		this.regPortNum = regPortNum;
		this.hostName = hostName;
		//System.out.println("before eventFactory");
		EventFactory efObj = EventFactory.getInstance();
		//System.out.println("after eventFactory");
		TCPServerThread newTCPServerThread = new TCPServerThread(0, this);
		Thread startTCPServSock = new Thread(newTCPServerThread);
		startTCPServSock.start();
		
		//print Messaging node init
		System.out.println("messaging node initialized");
		// Marshall message into byte array (V1):
		OVERLAY_NODE_SENDS_REGISTRATION msgObj = new OVERLAY_NODE_SENDS_REGISTRATION(null);
		msgObj.setMessageType(newTCPServerThread.getMsgType());
		msgObj.setIPaddressLen(newTCPServerThread.getLocalAddrLen());
		msgObj.setIPaddress(newTCPServerThread.getLocalAddr());
		msgObj.setPortNum(newTCPServerThread.getLocalPortNum());
		
		// Marshall message into byte array (V2):
		/*
		OVERLAY_NODE_SENDS_REGISTRATION newRegistrationMsg = new OVERLAY_NODE_SENDS_REGISTRATION(newTCPServerThread.getMsgType(), newTCPServerThread.getLocalAddrLen(),
				newTCPServerThread.getLocalAddr(), newTCPServerThread.getLocalPortNum());
		*/
		//sending message
		Socket socket = new Socket(hostName, regPortNum);
		TCPConnection sendRegistryInfo = new TCPConnection(socket, msgObj.getBytes(), this);
	}



	@Override
	public void onEvent(Event msgType) throws IOException {
		byte[] dataBytes = msgType.getBytes();
		if(msgType.getType() == 3) {
			REGISTRY_REPORTS_REGISTRATION_STATUS newStatus = new REGISTRY_REPORTS_REGISTRATION_STATUS(dataBytes);
			//System.out.println("received status?");
			String identifier = new String(newStatus.getInfoString());		
			System.out.println(identifier);
		}
		
	}
	
	

}
