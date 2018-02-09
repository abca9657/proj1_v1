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
		byte messageType;
		byte IPaddressLen;
		byte[] IPaddress;
		int PortNum;
		
		
		// unmarshall data: 
			public OVERLAY_NODE_SENDS_REGISTRATION(byte[] marshalledBytes) throws IOException {
				
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
			
		
		// marshall data: 
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
		
		
		// get the message type: 
		public byte getType() {
			byte msgType = messageType;
			return msgType;
			
		}


		@Override
		public void messageType(byte msgType) {
			// TODO Auto-generated method stub
			
		}

}



