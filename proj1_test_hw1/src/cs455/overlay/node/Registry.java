package cs455.overlay.node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Registry {

	private final int portNumber;

	public static void main(String[] args) {

		// create portnum for registry:
		if (args.length != 1) {
			System.err.println("Usage: java cs455.overlay.node.Registry <portnumber>");
			System.exit(1);
		}
		int portNum = Integer.parseInt(args[0]);

		new Registry(portNum).run();

	}

	public Registry(int portNumber) {
		this.portNumber = portNumber;
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
