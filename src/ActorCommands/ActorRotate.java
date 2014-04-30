package ActorCommands;

import Actors.Actor;

public class ActorRotate implements ActorCommand {

	private Actor a;
	private float rotation;
	public ActorRotate(Actor a,float rotation)
	{
		this.a =a;
		this.rotation =rotation;
	}
	public void execute() {
		a.rotate(rotation);
	}
}
