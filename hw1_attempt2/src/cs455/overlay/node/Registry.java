package cs455.overlay.node;


import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
import cs455.overlay.transport.TCPConnection;
import cs455.overlay.transport.TCPServerThread;
import cs455.overlay.wireformats.Event;
import cs455.overlay.wireformats.EventFactory;
import cs455.overlay.wireformats.OVERLAY_NODE_SENDS_REGISTRATION;
import cs455.overlay.wireformats.REGISTRY_REPORTS_REGISTRATION_STATUS;


public class Registry implements Node, Runnable{

	private int portNumber;
	private RegisterEntrys registryEntry;  // this is the actual registry
	//private List<Integer> usedRegisterIDs;
	private UsedRegIDs usedRegisterIDs;

	public static void main(String[] args) throws IOException {

		// create portnum for registry:
		if (args.length != 1) {
			System.err.println("Usage: java cs455.overlay.node.Registry <portnumber>");
			System.exit(1);
		}
		int portNum = Integer.parseInt(args[0]);
		//EventFactory ef = new EventFactory(this);
		
		Registry reg = new Registry(portNum);
		

	} // end of main
	
	

	// this is type Node
	public Registry(int portNumber) throws IOException {
		this.portNumber = portNumber;
		EventFactory efObj = EventFactory.getInstance();
		
		TCPServerThread newThread = new TCPServerThread(portNumber, this);
		Thread startNewThread = new Thread(newThread);
		startNewThread.start();
				
		
		// Initialize the registry: 
		registryEntry = new RegisterEntrys();
		// you will add each messaging node to this list
		
		// Keep track of IDs: 
		//UsedRegIDs usedIDs = new UsedRegIDs();
		usedRegisterIDs = new UsedRegIDs();
		
		
		
	}


	
	public void onEvent(Event msgType) throws IOException {
		// TODO Auto-generated method stub
		byte[] dataBytes = msgType.getBytes();
		if(msgType.getType() == 2) {
			// register node
			OVERLAY_NODE_SENDS_REGISTRATION newReg = new OVERLAY_NODE_SENDS_REGISTRATION(dataBytes);
			
			byte[] regIPaddr = newReg.getIPaddress();
			int regPortNum = newReg.getPortNum();
			
			
			System.out.println("regPortNumber: " + regPortNum);
			//InetAddress passMe = InetAddress.getByAddress(regIPaddr);
			InetAddress printThis = InetAddress.getByAddress(regIPaddr);
			String prntHostAddr = printThis.getHostAddress();
			System.out.println("regIPAddress: " + prntHostAddr);
			
			
			// check to see if it is already registered: 
			int isRegistered = registryEntry.checkForDuplicateIP(regIPaddr);
			//System.out.println("message sent, registered?=" + isRegistered);
			
			// if the node is not registered, register it
			if(isRegistered == -1) {
				// generage new ID: 
				int newID = usedRegisterIDs.generateNewID();
				if(newID == -1){
					System.out.println("no more IDs to use");
					return;
				}
				RegEntry newRegEntry = new RegEntry(regIPaddr, regPortNum, newID);
				registryEntry.addEntry(newRegEntry);
				int regSize = registryEntry.sizeOf();
				// success! Now send a message back with the assigned ID
				String sucMsg = "Registration request successful. The number of messaging nodes currently constituting the overlay is " + regSize;
				byte[] sucMsgStatus = sucMsg.getBytes();
				byte sucMsgStatusLen = (byte) sucMsgStatus.length;
				REGISTRY_REPORTS_REGISTRATION_STATUS sndStatus = new REGISTRY_REPORTS_REGISTRATION_STATUS(null);
				sndStatus.setMessageType((byte) 3);
				sndStatus.setSucStatus(newID);
				sndStatus.setInfoStringLen(sucMsgStatusLen);
				sndStatus.setInfoString(sucMsgStatus);
				InetAddress passMe = InetAddress.getByAddress(regIPaddr);
				
				
				Socket socket = new Socket(passMe , regPortNum);
				TCPConnection sendSuccessMsg = new TCPConnection(socket, sndStatus.getBytes(), this);
				
				
				
				//System.out.println("registration successful");
			} else {
				// send a message back with -1 instead of assigned node ID
			}
			
		} //else if(msgType == 4) {
			// deregister node
		//}
		
	}



	@Override
	public void run() {
		// put your scanner for commands here:
		Scanner sc = new Scanner(System.in);
		while(true) {
			// get user input
			
		}
		
	}

	/*
	 * This method creates a socket listener on the portNumber and waits for 
	 * connection requests, and accepts all requests and creates a new thread to 
	 * handle all new connections 
	 */


}
