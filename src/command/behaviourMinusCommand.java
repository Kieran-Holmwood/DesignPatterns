package command;

public class behaviourMinusCommand implements Command{

	private ObjectCommanded o;
	public behaviourMinusCommand(ObjectCommanded o)
	{
		this.o = o;
	}
	public void execute() {
		// TODO Auto-generated method stub
		o.behviourMinus();
	}

}
