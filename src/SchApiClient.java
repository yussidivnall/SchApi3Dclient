import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;
import javax.microedition.lcdui.*;

public class SchApiClient extends MIDlet {
	String TITLE="SchApi v0.00000001";
	
	Display display;
	MainMenu mainMenu;
	public SchApiClient() {

	}

	protected void startApp() throws MIDletStateChangeException {
		display = Display.getDisplay(this);
		mainMenu = new MainMenu("main",display);
		display.setCurrent(mainMenu);
	}
	protected void destroyApp(boolean arg0) throws MIDletStateChangeException {
		SchConfig.finish();
	}

	protected void pauseApp() {
		// TODO Auto-generated method stub
	}
}
