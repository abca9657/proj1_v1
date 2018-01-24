package cs455.overlay.node;

public class Registry {

	public static void main(String[] args) {
		
		// create portnum for registry: 
		if(args.length != 1) {
			System.err.println("Usage: java cs455.overlay.node.Registry <portnumber>");
			System.exit(1);
		}
		int portNum = Integer.parseInt(args[0]);

	}

}
