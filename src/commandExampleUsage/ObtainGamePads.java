package commandExampleUsage;

import net.java.games.input.Controller;
import net.java.games.input.Component;
import net.java.games.input.ControllerEnvironment;

public class ObtainGamePads {

	Controller controller;

	public ObtainGamePads()
	{
		controller = null;
		ControllerEnvironment ce = ControllerEnvironment.getDefaultEnvironment();
		for (Controller c : ce.getControllers()) {
			if (c.getType() == Controller.Type.GAMEPAD) {
				controller = c;
			}
		}
	}

	public Controller getController()
	{
		return controller;
	}

	public void listControllers()
	{
		if(controller != null)
		{
			for (Controller c : ControllerEnvironment.getDefaultEnvironment().getControllers()) {
				System.out.println(c.getName());
			}
		}
		else { System.out.println("controller was null");}
	}

	public void listComponants()
	{
		if(controller != null)
		{
			for (Component c : controller.getComponents()) {
				System.out.println(c.getName());
			}
		}else { System.out.println("controller was null");}
	}
	
	public Component[] getComponants()
	{
		return controller.getComponents();
	}

}
