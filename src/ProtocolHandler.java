import java.io.*;
import javax.microedition.io.*;

public class ProtocolHandler {
	static int RECIVING_XML=1;
	static int state=0;
	
	static StreamConnection buff;
	
	static ConnectMenu myConnectMenu;
	
	
	public static void connect(ConnectMenu cmenu){
		myConnectMenu = cmenu;
		ConnectionManager.connect();
	}
	public static void connectionCompleted(){
		myConnectMenu.connectionCompleted();
	}
	public static void dispatch(String command){
		ConnectionManager.Dispatch(command);
	}
}
