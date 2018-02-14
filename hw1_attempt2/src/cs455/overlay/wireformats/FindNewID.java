package cs455.overlay.wireformats;

import java.util.Random;

import cs455.overlay.node.RegisterEntrys;
import cs455.overlay.node.UsedRegIDs;

public class FindNewID {
	
	UsedRegIDs usedIDs;
	RegisterEntrys registry;
	
	public FindNewID (UsedRegIDs usedIDs, RegisterEntrys registry) {
		
		this.usedIDs = usedIDs;
		this.registry = registry;
		
		// pick a random number for the ID: 
		Random tryRegID = new Random();
		int regID = tryRegID.nextInt(128);
		int tmpRegID = regID;
		// check to see if the ID is used: 
		if(usedIDs.checkForRepeatID(regID) == -1) { 
			//there is no current ID for this node
			// Add ID to node and to ID list: 
			usedIDs.addID(regID);
		} else {
			// this ID is a duplicate:
			while(usedIDs.checkForRepeatID(regID) != -1 && regID < 128) {
				regID++;
			}
			if(usedIDs.checkForRepeatID(regID) == -1) { 
				// found an ID value:
				usedIDs.addID(regID);
			} else if(regID == 128) {
				while(usedIDs.checkForRepeatID(tmpRegID) != -1 && tmpRegID >= 0) {
					tmpRegID--;
				}
			}
			
		}
	}
	
	
	
}


