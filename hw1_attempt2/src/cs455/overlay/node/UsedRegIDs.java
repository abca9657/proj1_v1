package cs455.overlay.node;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


// This is the list of Used IDs for each Messaging Node: 
// It should only be called once in order to properly keep track of all IDs
public class UsedRegIDs {
	private static List<Integer> regIDList;
	private static List<Integer> unusedList;
	private int keepTrack = 128;
	private int newID;
	private static int isRepeatID;
	
	public UsedRegIDs() {
		regIDList = new ArrayList<Integer>();
		unusedList = new ArrayList<Integer>();
		System.out.println("making brand new regIDList..");
		// create a list of unused IDs
		for(int i=0; i<128; i++) {
			unusedList.add(i);
		}
		
	}
	
	public void addID(int idNum) {
		newID = idNum;
		regIDList.add(newID);
	}
	
	public List<Integer> getList() {
		return regIDList;
	}
	
	public static int checkForRepeatID(int newID) {
		isRepeatID = newID;
		int chk = regIDList.indexOf(isRepeatID);
		return chk;
	}
	
	public int generateNewID () {
		Random tryRegID = new Random();
		if(keepTrack < 0) {
			System.out.println("Error, max IDs chosen");
			return -1;
		}
		int regIDindex = tryRegID.nextInt(keepTrack);
		newID = unusedList.get(regIDindex);
		
		// remove this id from unused list: 
		unusedList.remove(regIDindex);
		keepTrack--;
		return newID;
				
	}

}
