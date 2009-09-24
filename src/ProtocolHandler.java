import java.io.*;
public class ProtocolHandler {
	static ConnectMenu myConnectMenu;
	
	public static void connect(ConnectMenu cmenu){
		myConnectMenu = cmenu;
		ConnectionManager.connect();
	}
	public static void connectionCompleted(){
		myConnectMenu.connectionCompleted();
		dispatch("GETALL");
	}
	public static void NewData(byte data[]){
		System.out.println("New data!!");
		System.out.println(new String(data));
	}
	public static void dispatch(String command){
		ConnectionManager.Dispatch(command);
	}
	
	
}
