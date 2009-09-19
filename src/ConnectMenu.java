import javax.microedition.lcdui.*;
public class ConnectMenu extends Form implements CommandListener{
	Display display;
	Displayable parent;
	
	Command ok;
	Command cancel;
	public ConnectMenu(String title,Display d,Displayable p) {
		super(title);
		display=d;
		parent = p;
		ok = new Command("OK",Command.OK,1);
		cancel = new Command("Cancel",Command.CANCEL,1);
		
		this.addCommand(ok);
		this.addCommand(cancel);
		setCommandListener(this);
	}
	public void commandAction(Command cmd, Displayable disp) {
		if(cmd.getLabel()=="OK"){
			ConnectThread connectThread = new ConnectThread();
			connectThread.start();
		}
		if (cmd.getLabel()=="Cancel"){
			System.out.println("Canceled connect");
			display.setCurrent(parent);
		}
		
	}

}
