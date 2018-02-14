package cs455.overlay.wireformats;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Random;

import cs455.overlay.node.Node;
import cs455.overlay.node.RegisterEntrys;
import cs455.overlay.node.UsedRegIDs;

public class EventFactory{
	
	
	/*
	 * Create Singleton instance of EventFactory FOR EACH NODE
	 * should contain the node's instance (registry or msgingNode)
	 * when a message is received, the EventFactory will call onEvent through
	 * this object reference
	 * obj.onEvent(event)? where obj is type node.. how to connect Node to EF??
	 * 
	 */
	
	// Singleton, how to do this? 
	
	private static final EventFactory efObj = new EventFactory();
	private EventFactory() { }
	public static EventFactory getInstance () {
			return efObj;
	}
	public Event createEvent(byte[] rawMsg) throws IOException {
		ByteArrayInputStream baInputStream = new ByteArrayInputStream(rawMsg);
		DataInputStream din = new DataInputStream(new BufferedInputStream(baInputStream));
		
		byte messageType = din.readByte();
		
		baInputStream.close();
		din.close();
		Event retEvent = null;
		
		if(messageType == 2) {
			retEvent = new OVERLAY_NODE_SENDS_REGISTRATION(rawMsg);
		} else if(messageType == 3) {
			retEvent = new REGISTRY_REPORTS_REGISTRATION_STATUS(rawMsg);
		}
		
		return retEvent;
	}
	
	
	
	
	
	
	
	/*
	byte messageType;
	byte[] marshalledBytes;
	UsedRegIDs usedIDs;
	RegisterEntrys registry;
	*/
	//Node nodeType;
	
	
	/*
	public EventFactory(Node node) throws IOException {
		this.nodeType = node;
		//marshalledBytes = bytes;
		//this.usedIDs = usedIDs;
		//this.registry = registry;
	}
	*/
	/*
	public byte MsgReceiver(byte[] marshalledBytes) throws IOException {
		
		ByteArrayInputStream baInputStream = new ByteArrayInputStream(marshalledBytes);
		DataInputStream din = new DataInputStream(new BufferedInputStream(baInputStream));
		
		byte messageType = din.readByte();
		
		baInputStream.close();
		din.close();
		return messageType;
		
	}
	*/
	
	// Another message receiver example: 
	/*
	public void MsgReceiver(byte[] marshalledBytes) throws IOException {
		ByteArrayInputStream baInputStream = new ByteArrayInputStream(marshalledBytes);
		DataInputStream din = new DataInputStream(new BufferedInputStream(baInputStream));
		
		byte messageType = din.readByte();
		
		baInputStream.close();
		din.close();
		
		nodeType.onEvent(messageType);
	}
	*/
	
	
	
	
	
	
	/*private Event CreateEvent(byte[] marshalledBytes) throws IOException {
		// Extract Message type (first byte)
		ByteArrayInputStream baInputStream = new ByteArrayInputStream(marshalledBytes);
		DataInputStream din = new DataInputStream(new BufferedInputStream(baInputStream));
		
		int messageType = din.readByte();
		
		baInputStream.close();
		din.close();
		
		//onEvent should define these?
		switch(messageType){
		case 2: //OVERLAY_NODE_SENDS_REGISTRATION
			OVERLAY_NODE_SENDS_REGISTRATION newReg = new OVERLAY_NODE_SENDS_REGISTRATION(marshalledBytes);
			// newReg should be an Event which gets passed to onEvent in respective Node			
			nodeType.onEvent(newReg);
			
			break;
			
		case 3: //REGISTRY_REPORTS_REGISTRATION_STATUS
			
			// send status of registration to messaging node

			//nodex.onEvent(newReg);
		//return messageType;
	}
		
	}*/
		

}
