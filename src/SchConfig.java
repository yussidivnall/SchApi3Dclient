public class SchConfig {
	public static boolean done = false;
	public static boolean connection = false;
	
	public static void connected(){
		connection=true;
		ProtocolHandler.connectionCompleted();
	}
	
	public static void finish(){
		done=true;
	}
}
