package cs455.overlay.wireformats;

import java.io.IOException;

public interface Event {
	
	public void messageType(byte msgType);
	
	// Marshall bytes: 
	public byte[] getBytes () throws IOException;
	
	//Get message type: (and unmarshall bytes?)
	public byte getType() ;

}
