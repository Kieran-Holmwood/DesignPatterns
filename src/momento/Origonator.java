package momento;

import command.ObjectCommanded;


public class Origonator{
	private ObjectCommanded state;
	
	
	public void set(ObjectCommanded o)
	{
		state = o;
	}
	
	public Memento saveToMemento()
	{
		System.out.println("Originator: Saving to Memento.");
		return new Memento(state);
	}
	
	public ObjectCommanded restoreFromMemento(Memento m)
	{
		return m.getSavedState();
	}
	
	public class Memento {
		private final ObjectCommanded state;
		
		public Memento(ObjectCommanded o)
		{
			//this needs to changed for a "clone"
			state = o.clone();
		}
		
		public ObjectCommanded getSavedState()
		{
			return state;
		}
	}
	
	
}
