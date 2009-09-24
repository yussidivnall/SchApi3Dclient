import javax.microedition.lcdui.*;
public class MapCanvas extends Canvas implements CommandListener{
	Command refreshCmd;
	MapCanvas(){
		refreshCmd = new Command("Refresh",Command.ITEM,1);
		this.addCommand(refreshCmd);
		setCommandListener(this);
	}
	
	protected void paint(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	public void commandAction(Command cmd, Displayable disp) {
		if(cmd.getLabel()=="Refresh"){
			ProtocolHandler.dispatch("GETALL");
		}
		
	}

}
