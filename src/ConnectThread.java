//Credit : http://java.sun.com/javame/reference/apis/jsr118/javax/microedition/io/SecureConnection.html

import java.io.IOException;

import javax.microedition.io.*;
import java.io.*;

public class ConnectThread extends Thread {
	SecureConnection connection;
	SecurityInfo info;
	
	ConnectThread(){
		
	}
	public void run(){
		System.out.println("Thread running");

		try{
			connection = (SecureConnection)Connector.open("ssl://localhost:999");
			info = connection.getSecurityInfo();
			//connection.setSocketOption(SocketConnection.LINGER, 10);
			
			InputStream is = connection.openInputStream();
			OutputStream os = connection.openOutputStream();
			os.write("GETALL".getBytes());
			
			int r=0;
			while(r != -1){
				r = is.read();
				
				System.out.print((char)r);
			}
			is.close();
			os.close();
			connection.close();
			System.out.println("Connection closed!");
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
	}
}
