package cs455.overlay.wireformats;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;


public class OVERLAY_NODE_SENDS_REGISTRATION implements Event{
	
	// raw message to be sent: 
	private byte messageType;
	private byte IPaddressLen;
	private byte[] IPaddress;
	private int PortNum;
	byte[] marshalledBytes;
		
		
	public byte getMessageType() {
		return messageType;
	}


	public void setMessageType(byte messageType) {
		this.messageType = messageType;
	}


	public byte getIPaddressLen() {
		return IPaddressLen;
	}


	public void setIPaddressLen(byte iPaddressLen) {
		IPaddressLen = iPaddressLen;
	}


	public byte[] getIPaddress() {
		return IPaddress;
	}


	public void setIPaddress(byte[] iPaddress) {
		IPaddress = iPaddress;
	}


	public int getPortNum() {
		return PortNum;
	}


	public void setPortNum(int portNum) {
		PortNum = portNum;
	}

		
		// marshall data V2: 
		/*
		public OVERLAY_NODE_SENDS_REGISTRATION(byte msgType, byte IPlen, byte[] IPaddr, int port) throws IOException {
			this.messageType = msgType;
			this.IPaddressLen = IPlen;
			this.IPaddress = IPaddr;
			this.PortNum = port;
			
			byte[] marshalledBytes = null;
			ByteArrayOutputStream baOutputStream = new ByteArrayOutputStream();
			DataOutputStream dout = new DataOutputStream(new BufferedOutputStream(baOutputStream));
			
			dout.writeByte(messageType);
			dout.writeByte(IPaddressLen);
			
			dout.write(IPaddress);
			
			dout.writeInt(PortNum);
			
			dout.flush();
			marshalledBytes = baOutputStream.toByteArray();
			
			baOutputStream.close();
			dout.close();
			this.marshalledBytes = marshalledBytes;
			//return marshalledBytes;
			
		}
		*/
		
		
		// unmarshall data V2: 
		/*
		public void getData(byte[] mBytes) throws IOException {
			ByteArrayInputStream baInputStream = new ByteArrayInputStream(mBytes);
			DataInputStream din = new DataInputStream(new BufferedInputStream(baInputStream));
			
			messageType = din.readByte();
			
			IPaddressLen = din.readByte();
			
			int ipAddrLen = (int) IPaddressLen;
			IPaddress = new byte[ipAddrLen];
			din.readFully(IPaddress);
			
			PortNum = din.readInt();
			
			baInputStream.close();
			din.close();
		}
		*/
		
		
		
		
		// unmarshall data V1: 
	public OVERLAY_NODE_SENDS_REGISTRATION(byte[] marshalledBytes) throws IOException {
			if(marshalledBytes != null) {
				ByteArrayInputStream baInputStream = new ByteArrayInputStream(marshalledBytes);
				DataInputStream din = new DataInputStream(new BufferedInputStream(baInputStream));
			
				messageType = din.readByte();
			
				IPaddressLen = din.readByte();
			
				int ipAddrLen = (int) IPaddressLen;
				IPaddress = new byte[ipAddrLen];
				din.readFully(IPaddress);
			
				PortNum = din.readInt();
			
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
		dout.writeByte(IPaddressLen);
		
		dout.write(IPaddress);
		
		dout.writeInt(PortNum);
		
		dout.flush();
		marshalledBytes = baOutputStream.toByteArray();
		
		baOutputStream.close();
		dout.close();
		return marshalledBytes;
	}
		
		
		// getBytes V2
		/*
		public byte[] getBytes() {
			return marshalledBytes;
		}
		*/
		// get the message type: 
	public byte getType() {
		//byte msgType = getMessageType();
		return messageType;
		
	}
		
		// should this be static?
		/*
		public void setData(byte msgType, byte IPlen, byte[] IPaddr, int port) {
			this.messageType = msgType;
			this.IPaddressLen = IPlen;
			this.IPaddress = IPaddr;
			this.PortNum = port;
			
		}*/


		

}



