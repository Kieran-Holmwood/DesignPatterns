package Command;

import java.util.ArrayList;
import java.util.List;

public class invoker {
	private List<Command> history = new ArrayList<Command>();
	
	public void storeAndExectute(Command c)
	{
		history.add(c);
		c.execute();
	}

	public void getCommands() {
		// TODO Auto-generated method stub
		System.out.println(history.toString());
	}
}
