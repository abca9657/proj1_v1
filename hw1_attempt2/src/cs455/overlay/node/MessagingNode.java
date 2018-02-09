package cs455.overlay.node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class MessagingNode {
	private final int regPortNum;

	public static void main(String[] args) throws InterruptedException {
		// parse input arguments from user: 
		if (args.length != 2) {
			System.err.println("Usage: java cs455.overlay.node.MessagingNode <RegistryHost> <RegistryPort>");
			System.exit(1);
		}
		String hostName = args[0];
		int regPortNum = Integer.parseInt(args[1]);
		
		
		
		 
		
		
		
		/*
		//establish socket to talk to registry: 
		try(Socket sock = new Socket(hostName, regPortNum)) {
			BufferedReader reader = 
					new BufferedReader(new InputStreamReader(sock.getInputStream()));
			PrintWriter writer = new PrintWriter(sock.getOutputStream());
			
			new Thread(new ReadMessages(reader)).start();
			
			// Write messages to the registry to be echoed back
			for(int i=0; i<20; i++) {
				// this prints to the socket
				writer.println(i);
				writer.flush();
				Thread.sleep(100);
			}
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		

	}
	
	private static class ReadMessages implements Runnable {
		private final BufferedReader reader;

		public ReadMessages(BufferedReader reader) {
			this.reader = reader;
		}

		@Override
		public void run() {
			
			// Loop over reader and print it to the standard output: 
			String s;
			try {
				while((s = reader.readLine())!= null) {
					System.out.println(s);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	public MessagingNode(int regPortNum) {
		this.regPortNum = regPortNum;
	}
	
	/*
	private void run() {
		try(ServerSocket servSock = new ServerSocket(0)) {
			// loop forever in order to listen for incoming connections: 
			while(true) {
				// Accept all connection requests
				Socket clientSock = servSock.accept();
				
				// Create a new handler object to handle the request: 
				MessageHandler messageHandler = new MessageHandler(clientSock);	// NEED TO ADD MESSAGE HANDLER
				
				// Create a new thread to handle the request, MessageHandler will have to implement Runnable: 
				Thread t = new Thread(messageHandler);
				// Run the new thread
				t.start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	*/

}
