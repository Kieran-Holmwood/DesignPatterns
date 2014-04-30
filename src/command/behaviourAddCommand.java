package command;

public class behaviourAddCommand implements Command{

	private ObjectCommanded o;
	public behaviourAddCommand(ObjectCommanded o)
	{
		this.o = o;
	}
	public void execute() {
		// TODO Auto-generated method stub
		o.behaviourAdd();
	}

}
