package commandExampleUsage;

import java.util.ArrayList;

import ActorCommands.ActorColour;
import ActorCommands.ActorMove;
import ActorCommands.ActorRotate;
import Actors.Actor;
import net.java.games.input.Component;
import net.java.games.input.Component.Identifier.*;
import net.java.games.input.Controller;
//import net.java.games.input.Event;
//import net.java.games.input.EventQueue;

public class GamePadInputHandler {
	
	private Controller c;
	
	public GamePadInputHandler(Controller c)
	{
		this.c = c;
		//buildController(); not used currently
	}
	
	/* TODO: build a model of the controller mapping all the buttons
	 * 
	 * private void buildController()
	{
		Component[] sourceAxes = c.getComponents();
		
		for (Component comp: sourceAxes)
		{
			if(comp.getIdentifier() instanceof Button)
			{
				buttons.add(comp);
			}
			if(comp.getIdentifier().equals(Axis.POV))
			{
				pov.add(comp);
			}
			else{
				axes.add(comp);
			}
		}
	}*/
	
	
	/*to fix the cube floats around without user input probably related to the deadzone
	* very mess needs rebuidling with future models in mind
	* TODO: once build the model of the controller rebuild this to use the event queue for buttons
	* and to read continuous for the thumb sticks.*/
	
	public void handleInput(Actor a,int delta)
	{
		float rotate = 0;
		float x= 0, y = 0, r= 0, g = 0;
		for(Component comp: c.getComponents()){
			
			float f = comp.getDeadZone();
			float num = comp.getPollData();
			if(comp.getIdentifier().getName().equals("x") && (num+f) != 0)
			{
				x += num * delta;
			}
			if( comp.getIdentifier().getName().equals("y")&& (num+f) != 0)
			{
				y -= num * delta;
			}
			if(comp.getIdentifier().getName().equals("rx")&& (num+f) != 0)
			{
				rotate += num * delta;
			}
			if(comp.getIdentifier().getName().equals("1"))
			{
				if(num != 0)
				{
					r += num/(delta*delta); g-= num/(delta*delta);
				}
			}
			if(comp.getIdentifier().getName().equals("2"))
			{
				if(num != 0)
				{
					r -= num/(delta*delta); g+= num/(delta*delta);
				}
			}
		}
		
		/*EventQueue queue = c.getEventQueue();
		Event event = new Event();
		while(queue.getNextEvent(event))
		{
			Component comp = event.getComponent();
			if(comp.getIdentifier().getName().equals("1"))
			{
				r += 0.01f; g-= 0.01f;
			}
			if(comp.getIdentifier().getName().equals("2"))
			{
				r -= 0.01f; g+= 0.01f;
			}
		}*/
		new ActorMove(a,x,y).execute();
		new ActorRotate(a,rotate).execute();
		new ActorColour(a,r,g).execute();
	}
	
	//this needs work but currently does ok for now
	public boolean alive() {
		if(c.poll())
		{
		return c.poll();
		}
		return false;
	}

}
