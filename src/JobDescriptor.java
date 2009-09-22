import java.util.Vector;


public class JobDescriptor {
	String name;
	String Description;
	String status;
	int ID;
	int x,y,z;
	Vector Parents;
	Vector Children;
	
	JobDescriptor(){};
	public void SetJobName(String n){}
	public void SetJobID(int i){}
	public void SetStatus(String s){};
	public void SetJobDescription(String desc){}
	public void AddParent(JobDescriptor prnt){}
	public void AddChild(JobDescriptor chld){}
	
}
