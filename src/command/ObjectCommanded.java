package command;

public class ObjectCommanded {
	private int i = 0;
	
	public ObjectCommanded()
	{
		
	}

	public void behaviourAdd()
	{
		i++;
		printNumber();
	}
	
	public void behviourMinus()
	{
		i--;
		printNumber();
		
	}
	public void printNumber()
	{
		System.out.println("Your number is: "+i);
	}
	
	public ObjectCommanded clone()
	{
		return new ObjectCommanded(i);
	}
	
	private ObjectCommanded(int i)
	{
		this.i = i;
	}
	
	
}
