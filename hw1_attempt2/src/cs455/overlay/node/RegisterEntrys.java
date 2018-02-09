package cs455.overlay.node;

import java.util.ArrayList;

// This is the Registry which keeps track of the IP addresses and port numbers
// it should only have one object associated with it

public class RegisterEntrys {
	private ArrayList<RegEntry> regEntrys;
	private RegEntry addEntry;
	
	public RegisterEntrys() {
		regEntrys = new ArrayList<RegEntry>();
	}
	
	public void addEntry(RegEntry addToList) {
		addEntry = addToList;
		regEntrys.add(addEntry);
		
	}
	
	public ArrayList<RegEntry> getList() {
		return regEntrys;
	}

}
