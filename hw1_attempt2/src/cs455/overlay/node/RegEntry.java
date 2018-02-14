package cs455.overlay.node;

public class RegEntry {
	// Each Registration Entry with an ID number (entry number), IP address, and Port Number
	byte[] ipAddr;
	int portNum;
	int nodeID;
	
	/*
	// setters and getters: 
	public byte[] getIpAddr() {
		return ipAddr;
	}

	public void setIpAddr(byte[] ipAddr) {
		this.ipAddr = ipAddr;
	}

	public int getPortNum() {
		return portNum;
	}

	public void setPortNum(int portNum) {
		this.portNum = portNum;
	}

	public int getNodeID() {
		return nodeID;
	}

	public void setNodeID(int nodeID) {
		this.nodeID = nodeID;
	}
*/
	
	
	public RegEntry(byte[] ipAddr, int portNum, int nodeID) {
		this.ipAddr = ipAddr;
		this.portNum = portNum;
		this.nodeID = nodeID;
	}

}
