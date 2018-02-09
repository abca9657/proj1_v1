package cs455.overlay.wireformats;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Random;

import cs455.overlay.node.Node;
import cs455.overlay.node.RegisterEntrys;
import cs455.overlay.node.UsedRegIDs;

public class EventFactory {
	
	/*
	byte messageType;
	byte[] marshalledBytes;
	UsedRegIDs usedIDs;
	RegisterEntrys registry;
	*/
	Node nodeType;
	//Event messageObj;
	//, UsedRegIDs usedIDs, RegisterEntrys registry
	public EventFactory(Node nodey) throws IOException {
		nodeType = nodey;
		//marshalledBytes = bytes;
		//this.usedIDs = usedIDs;
		//this.registry = registry;
	}
	
	private Event CreateEvent(byte[] marshalledBytes) throws IOException {
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
		
	}
		
	/*	
		
			
			
		}*/
		
			
			
			
		
		
		//return messageObj;
		
	//}

}
