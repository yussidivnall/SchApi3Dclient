import java.io.*;
import java.util.*;
import javax.microedition.io.*;
public class ConnectionManager {
	public static boolean connected = false;
	
	static SecureConnection connection;
	static SecurityInfo info;
	
	static ProtocolHandler myProtocolHandler;
	static connectThread cThread;
	
	public static void connect(ProtocolHandler p){
		myProtocolHandler = p;
		connect();
	}
	
	
	public static void connect(){
		cThread = new connectThread(connection);
		cThread.start();
	}
	
	
	public static void setConnected(boolean c){
		connected=c;
		ProtocolHandler.connectionCompleted();
		System.out.println("ConnactionManager informed:"+connected);
		
		try{
		//	InputStream is = connection.openInputStream();
		}catch(Exception e){e.printStackTrace();}
			//listenerThread lt = new listenerThread(connection);
		//lt.start();
	}
	public static boolean isConnected(){
		return connected;
	}
	
	public static void NewData(byte dat[]){
		ProtocolHandler.NewData(dat);
	}
	public static void Dispatch(String cmd){
		cThread.dispatch(cmd);
	}
}
//---------------------------- |Threads
class connectThread extends Thread{
	SecureConnection connection;
	String command = "";
	boolean done = false;
	connectThread(SecureConnection c){
		connection=c;
	}
	public void dispatch(String cmd){
		command = cmd;
	}
	
	public void run(){
		try{
			connection = (SecureConnection)Connector.open("ssl://localhost:999");
			connection.setSocketOption(SocketConnection.LINGER, 6);
			System.out.println("Finaly, connected(Thread)!");
			ConnectionManager.setConnected(true);
			DataInputStream dis = connection.openDataInputStream();
			OutputStream os = connection.openOutputStream();
			while(!done){
				System.out.println("Available:"+dis.available());
				while(dis.available() == 0 && command ==""){} // wait for data
					if(dis.available()>0){
						byte b[]=new byte[dis.available()];
						dis.readFully(b);
						ConnectionManager.NewData(b);
					}else{
						command=command+"\n";
						os.write(command.getBytes());
						command="";
					}
			}
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
	}
}



class retriveThread extends Thread{
	SecureConnection connection;
	InputStream inputstream;
	String command;
	retriveThread(SecureConnection sc,InputStream is,String cmd){
		connection = sc;inputstream = is; command = cmd;
	}
	public void run(){
		try{
			connection.openOutputStream().write(command.getBytes());
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
	}
}

