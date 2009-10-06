import java.io.*;
import org.kxml2.*;
import org.kxml2.io.*;
import org.xmlpull.v1.*;


class Parser{
	public static void RecieveInputStream(InputStream in){
			listenerThread listener = new listenerThread(in);
			listener.start();
	}

/*		
	public static void Parse_XML(InputStream in) throws IOException{
		System.out.println("Parsing XML!!!");
		try{
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			factory.setValidating(true);
			XmlPullParser XML_Parser = factory.newPullParser();
			XML_Parser.setInput(in, null);
			while(in.available()>0 ){
				//XML_Parser.next();
				in.read();
			}
		}catch (XmlPullParserException xmle) {
			xmle.printStackTrace();
		}
	}*/
}

class listenerThread extends Thread{
	InputStream input;
	listenerThread(InputStream in){
		input=in;
	}
	public void run(){
		try{
			DataInputStream dis = new DataInputStream(input);
			while (!SchConfig.done){
				while(input.available()==0){} // hold until input avail;
				byte b[] = new byte[dis.available()];
				dis.readFully(b);
				if(new String(b).startsWith("<?xml"))parseXML_root(dis); 
						
			}
			input.close();
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
	}
	
	
	public void parseXML_root(DataInputStream dis) throws IOException{
		while(dis.available()>0){
			byte b[] = new byte[dis.available()];
			dis.readFully(b);
			System.out.println("root: "+new String(b));
			if(new String(b).trim().startsWith("<map"))parseXML_map(dis);
		}
	}
	public void parseXML_map(DataInputStream dis) throws IOException{
		while(dis.available()>0){
			byte b[] = new byte[dis.available()];
			dis.readFully(b);
			System.out.println("map: "+new String(b));
			if(new String(b).trim().startsWith("</map")) break;
			if(new String(b).trim().startsWith("<job")) parseXML_job(dis,new String(b));
		}
	}
	public void parseXML_job(DataInputStream dis,String header) throws IOException{
		while(dis.available()>0){
			System.out.println("Job: "+ header);
			byte b[] = new byte[dis.available()];
			dis.readFully(b);
			if(new String(b).startsWith("</job")) break;
			System.out.println("Job: "+ new String(b));
		}
	}

}