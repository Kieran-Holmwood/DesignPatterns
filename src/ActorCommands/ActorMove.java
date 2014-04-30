package ActorCommands;

import Actors.Actor;

public class ActorMove implements ActorCommand {

	private Actor a;
	private float x,y;
	public ActorMove(Actor a,float x, float y)
	{
		this.a =a;
		this.x =x;
		this.y =y;
	}
	public void execute() {
		a.move(x, y);
	}

}
