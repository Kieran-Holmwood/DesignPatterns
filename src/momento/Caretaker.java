package momento;

import java.util.ArrayList;
import java.util.List;

import command.ObjectCommanded;

public class Caretaker {
	private List<Origonator.Memento> savedStates = new ArrayList<Origonator.Memento>();
    private Origonator o;
	
	public Caretaker(Origonator o)
	{
		this.o = o;
	}
	
	public void storeState(ObjectCommanded c)
	{
		o.set(c);
		savedStates.add(o.saveToMemento());
	}

	public ObjectCommanded restoreState(int i)
	{
		return o.restoreFromMemento(savedStates.get(i));
	}
}
