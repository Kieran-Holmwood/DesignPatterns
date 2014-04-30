package ActorCommands;

import Actors.Actor;

public class ActorColour implements ActorCommand {

	private Actor a;
	private float r,g;
	public ActorColour(Actor a,float r, float g)
	{
		this.a =a;
		this.r =r;
		this.g =g;
	}
	public void execute() {
		a.changecolour(r, g);
	}
}
