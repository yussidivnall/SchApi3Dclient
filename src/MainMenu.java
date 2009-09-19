import javax.microedition.lcdui.*;
public class MainMenu extends Form implements CommandListener{
Command ok;
Command cancel;
ChoiceGroup options;

Display display;

	public MainMenu(String title,Display d) {
		super(title);
		display = d;
		ok = new Command("OK",Command.OK,1);
		cancel = new Command("Cancel",Command.CANCEL,1);
		this.addCommand(ok);
		this.addCommand(cancel);
		
		options = new ChoiceGroup("Options:",ChoiceGroup.EXCLUSIVE);
		options.append("Connect", null);
		options.append("Configure",null);
		
		this.append(options);
		
		setCommandListener(this);
	}

	public void commandAction(Command cmd, Displayable disp) {
		// TODO Auto-generated method stub
		System.out.println("command action!"+cmd.getLabel());
		if(cmd.getLabel()=="OK"){
			System.out.println(options.getSelectedIndex());
			if(options.getSelectedIndex()==0){ // connect
				ConnectMenu connetMenu = new ConnectMenu("Connect",display,this);
				display.setCurrent(connetMenu);
			}
			else if (options.getSelectedIndex()==1){ // configure
				
			}
		}
		else if(cmd.getLabel()=="Cancel"){
			
		}
	}

}
