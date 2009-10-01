import java.io.*;
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
	public static void Dispatch(String cmd){
		cThread.dispatch(cmd);
	}
}
//---------------------------- |Threads
class connectThread extends Thread{
	SecureConnection connection;
	String command = "";
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
			SchConfig.connected();
			
			
			dispatch("GETALL");
			
			Parser.RecieveInputStream(connection.openInputStream());
			OutputStream out = connection.openOutputStream();
			
			while (!SchConfig.done){
				while(command==""){} // Hold until command comes
				command = command+"\n";
				out.write(command.getBytes());
				command="";
			}
			out.close();
			connection.close();			
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
	}
}
