import java.io.*;
import java.util.*;
import javax.microedition.io.*;
public class ConnectionManager {
	public static boolean connected = false;
	
	static SecureConnection connection;
	static SecurityInfo info;
	
	static ConnectMenu myConnectMenu; // used for a call back to inform menu of completion
	public static void setConnection(SecureConnection c){
		connection=c;
	}
	public static void setConnectMenu(ConnectMenu c){
		myConnectMenu = c;
	}
	
	public static void connect(){
		connectThread ct = new connectThread(connection);
		ct.start();
		
	}
	
	
	public static void setConnected(boolean c){
		connected=c;
		if(myConnectMenu != null){myConnectMenu.connectionCompleted();}
		System.out.println("ConnactionManager informed:"+connected);
	}
	public static boolean isConnected(){
		return connected;
	}
}

class connectThread extends Thread{
	SecureConnection connection;
	connectThread(SecureConnection c){
		connection=c;
	}
	public void run(){
		try{
			connection = (SecureConnection)Connector.open("ssl://localhost:999");
			System.out.println("Finaly, connected(Thread)!");
			ConnectionManager.setConnected(true);
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
	}
}