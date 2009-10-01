import java.io.*;
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
	XmlPullParserFactory factory; 
	
	
	listenerThread(InputStream in){
		input=in;
	}
	public void run(){
		try{
			while (!SchConfig.done){
				while(input.available()==0){} // hold until input avail;
				//Parser.Parse_XML(input);
				//ParseXML(input);
				//System.out.println("Input:"+input.read());
			}
			input.close();
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
	}

}