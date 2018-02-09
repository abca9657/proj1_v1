package cs455.overlay.node;

import java.awt.Event;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import cs455.overlay.wireformats.EventFactory;

public class Registry implements Node {

	private final int portNumber;

	public static void main(String[] args) {

		// create portnum for registry:
		if (args.length != 1) {
			System.err.println("Usage: java cs455.overlay.node.Registry <portnumber>");
			System.exit(1);
		}
		int portNum = Integer.parseInt(args[0]);
		Registry regLocation = new Registry(portNum); // this is type node
		regLocation.run(); // this needs a TCPServerThread with constructor that takes (ef)?
		
		

		
		
		// Initialize the registry: 
		RegisterEntrys registry = new RegisterEntrys();
		// you will add each messaging node to this list
		
		// Keep track of IDs: 
		//UsedRegIDs usedIDs = new UsedRegIDs();
		List<Integer> usedRegisterIDs = new ArrayList<Integer>();
		
		
		

	}
	
	
	private void onEvent(Event eventAbby){
		
		if(eventAbby == OVerlay_NODE_Send_REg){
			//DO stuff
			//Call method to do stuff
			eventAbby.Dostuffwith(registry, other crap, )
		}
		
	}

	// this is type Node
	public Registry(int portNumber) {
		this.portNumber = portNumber;
		
		
		try {
			EventFactory ef = new EventFactory(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/*
	 * This method creates a socket listener on the portNumber and waits for 
	 * connection requests, and accepts all requests and creates a new thread to 
	 * handle all new connections 
	 */
	private void run() {
		
		// Create a listener for connection requests
		try (ServerSocket servSock = new ServerSocket(this.portNumber)) {
			
			// loop forever
			while (true) {
				
				// Accept all connection requests and create a new socket connection to 
				//  handle request
				Socket clientSock = servSock.accept();
				
				//use TCPConnection for this:
				
				// Create a new handler object to handle the requests in a new thread
				MessageHandler messageHandler = new MessageHandler(clientSock);
				
				// Create a new thread to handle request
				Thread t = new Thread(messageHandler);
				
				// run the new thread
				t.start();
			}

		} catch (IOException e) {
			System.out.println("Error in server socket initialization");
		}
	}

	private class MessageHandler implements Runnable {
		private final Socket clientSock;

		MessageHandler(Socket clientSock) {
			this.clientSock = clientSock;
			
		}

		@Override
		public void run() {
			try{
				BufferedReader reader = 
						new BufferedReader(new InputStreamReader(clientSock.getInputStream()));
				
				PrintWriter writer = new PrintWriter(clientSock.getOutputStream());
				
				String s;
				// Echo what the server reads
				while((s = reader.readLine())!= null) {
					writer.println(s);
					writer.flush();
					System.out.println(s);
				}
			} catch(IOException e) {
				
			}

		}

	}

}
