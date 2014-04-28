package Client;

import Command.*;
import Momento.Caretaker;
import Momento.Origonator;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ObjectCommanded obj = new ObjectCommanded();
		
		Command add = new behaviourAddCommand(obj);
		Command minus = new behaviourMinusCommand(obj);
		invoker i = new invoker();
		
		Caretaker caretaker = new Caretaker(new Origonator());

		
		i.storeAndExectute(add);
		caretaker.storeState(obj);
		i.storeAndExectute(minus);
		i.storeAndExectute(add);
		i.storeAndExectute(add);
		caretaker.storeState(obj);
		i.storeAndExectute(add);
		obj = caretaker.restoreState(0);
		System.out.print("restored state: ");
		obj.printNumber();
		i.getCommands();
	}

}
