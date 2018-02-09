package cs455.overlay.node;

public class RegEntry {
	// Each Registration Entry with an ID number (entry number), IP address, and Port Number
	byte[] ipAddr;
	int portNum;
	int nodeID;
	
	public RegEntry(byte[] ipAddr, int portNum, int nodeID) {
		this.ipAddr = ipAddr;
		this.portNum = portNum;
		this.nodeID = nodeID;
	}

}
