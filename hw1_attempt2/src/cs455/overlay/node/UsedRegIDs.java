package cs455.overlay.node;

import java.util.ArrayList;
import java.util.List;


// This is the list of Used IDs for each Messaging Node: 
// It should only be called once in order to properly keep track of all IDs
public class UsedRegIDs {
	private static List<Integer> regIDList;
	private int newID;
	private static int isRepeatID;
	
	public UsedRegIDs() {
		regIDList = new ArrayList<Integer>();
		
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

}
