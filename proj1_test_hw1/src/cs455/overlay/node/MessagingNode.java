package cs455.overlay.node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MessagingNode {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		if (args.length != 2) {
			System.err.println("Usage: java cs455.overlay.node.MessagingNode <RegistryHost> <RegistryPort>");
			System.exit(1);
		}
		String hostName = args[0];
		int regPortNum = Integer.parseInt(args[1]);
		
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
		}
		

	}
	
	private static class ReadMessages implements Runnable {
		private final BufferedReader reader;

		public ReadMessages(BufferedReader reader) {
			this.reader = reader;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
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

}
