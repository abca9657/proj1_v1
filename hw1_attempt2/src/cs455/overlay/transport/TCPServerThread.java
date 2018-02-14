package cs455.overlay.transport;

import java.io.IOException;

import cs455.overlay.wireformats.Event;
import cs455.overlay.wireformats.EventFactory;
import cs455.overlay.wireformats.OVERLAY_NODE_SENDS_REGISTRATION;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import cs455.overlay.node.Node;
import cs455.overlay.node.RegEntry;


// This is where you will have your serverSocket
// needs constructor to take ef (event factory)
// and create connection needs to take ef as well
public class TCPServerThread implements Runnable {
	byte msgType;
	byte localAddrLen;
	byte[] localAddr;
	int localPortNum;
	ServerSocket servSock;
	OVERLAY_NODE_SENDS_REGISTRATION newRegObj;
	//EventFactory efObj;
	Node nodeType;

	public byte getMsgType() {
		return msgType;
	}


	public void setMsgType(byte msgType) {
		this.msgType = msgType;
	}


	public byte getLocalAddrLen() {
		return localAddrLen;
	}


	public void setLocalAddrLen(byte localAddrLen) {
		this.localAddrLen = localAddrLen;
	}


	public byte[] getLocalAddr() {
		return localAddr;
	}


	public void setLocalAddr(byte[] localAddr) {
		this.localAddr = localAddr;
	}


	public int getLocalPortNum() {
		return localPortNum;
	}


	public void setLocalPortNum(int localPortNum) {
		this.localPortNum = localPortNum;
	}



	public TCPServerThread (int portNumber, Node nodeType) {
		System.out.println("I'm in the TCPServerThread!");
		try {
			this.servSock = new ServerSocket(portNumber);
			System.out.println("serverSocket created in TCPServerThread");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// obtain local port number:
		int localPortNum = servSock.getLocalPort();
		
		// obtain local address of this serversocket: 
		InetAddress addr = servSock.getInetAddress();
		byte[] lclAddr = addr.getAddress();
		byte localAddrLen = (byte) lclAddr.length;
		byte msgType = 2;

		this.msgType = msgType;
		this.localAddrLen = localAddrLen;
		this.localAddr = lclAddr;
		this.localPortNum = localPortNum;
		this.nodeType = nodeType;

		//RegEntry message = new RegEntry(OVERLAY_NODE_SENDS_REGISTRATION, localAddrLen, localAddr, localPortNum);	
		// pack this data and send to register with message type

	}


	@Override
	public void run() {

		// Create ServerSocket for listening and get information for registry: 
		/*
		try(ServerSocket servSock = new ServerSocket(0)) {
			int localPortNum = servSock.getLocalPort();
			InetAddress addr = servSock.getInetAddress();
			byte[] localAddr = addr.getAddress();
			byte localAddrLen = (byte) localAddr.length;
			byte msgType = 2;

			this.msgType = msgType;
			this.localAddrLen = localAddrLen;
			this.localAddr = localAddr;
			this.localPortNum = localPortNum;

			//RegEntry message = new RegEntry(OVERLAY_NODE_SENDS_REGISTRATION, localAddrLen, localAddr, localPortNum);	
			// pack this data and send to register with message type


			OVERLAY_NODE_SENDS_REGISTRATION newRegistrationMsg = new OVERLAY_NODE_SENDS_REGISTRATION(msgType, localAddrLen, localAddr, localPortNum);
			// send registry info 
			Socket socket = new Socket();
			//EventFactory efSingleton;
			TCPConnection sendRegistryInfo = new TCPConnection(socket, newRegistrationMsg.getBytes(), nodeType);



			// need to create a (thread?) with ServerSocket.accept()
		 */
		while(true) {
			// accept all connection requests and create a new socket connection to handle request
			Socket clientSock = null;
			try {
				clientSock = servSock.accept();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			//create a new thread to handle request
			System.out.println("accepted connection, clientSock created. Start TCPConnection");
			try {
				TCPConnection receiver = new TCPConnection(clientSock, null, nodeType);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		/*} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}




}

