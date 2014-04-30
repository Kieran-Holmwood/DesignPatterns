package commandExampleUsage;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import Actors.Actor;
import static org.lwjgl.opengl.GL11.*;

public class Gameloop {

	/** time at last frame */
	long lastFrame;

	/** frames per second */
	int fps;
	/** last fps time */
	long lastFPS;

	private GamePadInputHandler gamepad;

	public void start()
	{
		setupDisplay();
		lastFPS = getTime();
	}


	private void setupDisplay()
	{
		try{
			Display.setDisplayMode(new DisplayMode(800,600));
			Display.create();
		}
		catch(LWJGLException e)
		{
			e.printStackTrace();
			System.exit(0);
		}
		//setup display environment values
		initGL();
		//places Actor a at the center of the screen
		Actor a = new Actor((Display.getWidth()/2),(Display.getHeight()/2),0.5f,0.5f); //fix this line up
		setupController();

		while(!Display.isCloseRequested())
		{
			int delta = getDelta();
			update(delta);
			//updateScene
			
			
			//actors update
			//this will later be updated to update all the actors
			//currently only 1 actor created though and moved to update
			if(gamepad.alive())
			{
			// read inputs
			gamepad.handleInput(a,delta);
			}else
			{
				setupController();
			}
			a.update();
			
			//draw
			Display.update();
			Display.sync(120);
		}

		Display.destroy();
	}

	//TODO: find out a way to make this work so the controller is reinitialised on plugging back in.
	private void setupController() {
			gamepad = new GamePadInputHandler(new ObtainGamePads().getController());
	}

	
	private void update(int delta) {
		

		while (Keyboard.next()) {
			if (Keyboard.getEventKeyState()) {
				if (Keyboard.getEventKey() == Keyboard.KEY_F12) {
					setDisplayMode(Display.getWidth(), Display.getHeight(), !Display.isFullscreen());
				}
				else if (Keyboard.getEventKey() == Keyboard.KEY_ESCAPE) {
					setDisplayMode(800, 600, false);
				}
			}
		}
		updateFPS(); // update FPS Counter
	}
	
	private void setDisplayMode(int width, int height, boolean fullscreen) {
		// TODO: clean / make easier to read.
		if ((Display.getDisplayMode().getWidth() == width) && 
				(Display.getDisplayMode().getHeight() == height) && 
				(Display.isFullscreen() == fullscreen)) 
		{
			return;
		}
		try 
		{
			DisplayMode targetDisplayMode = null;

			if (fullscreen)
			{
				DisplayMode[] modes = Display.getAvailableDisplayModes();
				int freq = 0;

				for (int i=0;i<modes.length;i++) 
				{
					DisplayMode current = modes[i];

					if ((current.getWidth() == width) && (current.getHeight() == height))
					{
						if ((targetDisplayMode == null) || (current.getFrequency() >= freq)) 
						{
							if ((targetDisplayMode == null) || (current.getBitsPerPixel() > targetDisplayMode.getBitsPerPixel())) {
								targetDisplayMode = current;
								freq = targetDisplayMode.getFrequency();
							}
						}
						if ((current.getBitsPerPixel() == Display.getDesktopDisplayMode().getBitsPerPixel()) &&
								(current.getFrequency() == Display.getDesktopDisplayMode().getFrequency())) 
						{
							targetDisplayMode = current;
							break;
						}
					}
				}
			} 
			else 
			{
				targetDisplayMode = new DisplayMode(width,height);
			}

			if (targetDisplayMode == null) 
			{
				System.out.println("Failed to find value mode: "+width+"x"+height+" fs="+fullscreen);
				return;
			}

			Display.setDisplayMode(targetDisplayMode);
			Display.setFullscreen(fullscreen);

		} catch (LWJGLException e) {
			System.out.println("Unable to setup mode "+width+"x"+height+" fullscreen="+fullscreen + e);
		}
	}

	public long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

	private int getDelta() {
		long time = getTime();
		int delta = (int) (time - lastFrame);
		lastFrame = time;

		return delta;
	}

	public void updateFPS() {
		if (getTime() - lastFPS > 1000) {
			Display.setTitle("FPS: " + fps);
			fps = 0;
			lastFPS += 1000;
		}
		fps++;
	}

	private void initGL()
	{
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, 800, 0, 600, 1, -1);
		glMatrixMode(GL_MODELVIEW);
	}
}
