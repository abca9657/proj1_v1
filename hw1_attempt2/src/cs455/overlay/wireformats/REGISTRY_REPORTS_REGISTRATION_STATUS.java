package cs455.overlay.wireformats;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class REGISTRY_REPORTS_REGISTRATION_STATUS implements Event {
	
	// raw message to be sent: 
	private byte messageType;
	private int sucStatus;
	private byte infoStringLen;
	private byte[] infoString;
	byte[] marshalledBytes;
	
	
	public byte getMessageType() {
		return messageType;
	}


	public void setMessageType(byte messageType) {
		this.messageType = messageType;
	}


	public int getSucStatus() {
		return sucStatus;
	}


	public void setSucStatus(int sucStatus) {
		this.sucStatus = sucStatus;
	}


	public byte getInfoStringLen() {
		return infoStringLen;
	}


	public void setInfoStringLen(byte infoStringLen) {
		this.infoStringLen = infoStringLen;
	}


	public byte[] getInfoString() {
		return infoString;
	}


	public void setInfoString(byte[] infoString) {
		this.infoString = infoString;
	}


	
	
	// unmarshall data: 
	public REGISTRY_REPORTS_REGISTRATION_STATUS(byte[] marshalledBytes) throws IOException {
		if(marshalledBytes != null) {
			ByteArrayInputStream baInputStream = new ByteArrayInputStream(marshalledBytes);
			DataInputStream din = new DataInputStream(new BufferedInputStream(baInputStream));
		
			messageType = din.readByte();
		
			sucStatus = din.readInt();
			infoStringLen = din.readByte();
			
			// remember that this returns bytes, still need to put the string together
			int strLen = (int) infoStringLen;
			infoString = new byte[strLen];
			din.readFully(infoString);
				
			baInputStream.close();
			din.close();
		}
			
	}
		
	
// marshall data V1: 
	public byte[] getBytes() throws IOException {
		byte[] marshalledBytes = null;
		ByteArrayOutputStream baOutputStream = new ByteArrayOutputStream();
		DataOutputStream dout = new DataOutputStream(new BufferedOutputStream(baOutputStream));
	
		dout.writeByte(messageType);
		dout.writeInt(sucStatus);
		dout.writeByte(infoStringLen);
		
		dout.write(infoString);
		
		dout.flush();
		marshalledBytes = baOutputStream.toByteArray();
	
		baOutputStream.close();
		dout.close();
		return marshalledBytes;
	}


	@Override
	public byte getType() {
		// TODO Auto-generated method stub
		return messageType;
	}

}
