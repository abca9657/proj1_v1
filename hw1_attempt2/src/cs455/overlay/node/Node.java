package cs455.overlay.node;

import java.io.IOException;
import cs455.overlay.wireformats.Event;


// this is kind of the protocol, it should have the onEvent(Event) method?
public interface Node {
	public void onEvent(Event msg) throws IOException;
	

}
