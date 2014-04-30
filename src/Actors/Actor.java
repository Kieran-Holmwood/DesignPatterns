package Actors;

import org.lwjgl.opengl.GL11;

public class Actor{
	
	private float currentX,currentY,currentRotation,currentR, currentG;
	private final float maxR = 1.0f;
	private final float minR = -1.0f;
	private final float maxG = 1.0f;
	private final float minG = -1.0f;
	private final float maxX = 800;
	private final float minX = 0;
	private final float maxY = 600;
	private final float minY = 0;
	public Actor(float x, float y,float r,float g)
	{
		currentX = x;
		currentY = y;
		currentR = r;
		currentG = g;
	}
	
	public void update() {
		// TODO Auto-generated method stub
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		
		GL11.glPushMatrix();
			
			GL11.glTranslatef(currentX, currentY, 0);
			GL11.glRotatef(currentRotation, 0f, 0f, 1f);
			GL11.glTranslatef(-currentX, -currentY, 0);
			
			GL11.glBegin(GL11.GL_QUADS);				
				GL11.glColor3f(currentR+0.5f, currentG+0.5f, 1.0f);
				GL11.glVertex2f(currentX - 50, currentY - 50);
				
				GL11.glColor3f(currentR+0.5f, currentG-0.5f, 1.0f);
				GL11.glVertex2f(currentX + 50, currentY - 50);
				
				GL11.glColor3f(currentR-0.5f, currentG-0.5f, 1.0f);
				GL11.glVertex2f(currentX + 50, currentY + 50);
				
				GL11.glColor3f(currentR-0.5f, currentG+0.5f, 1.0f);
				GL11.glVertex2f(currentX - 50, currentY + 50);
			GL11.glEnd();
			
		GL11.glPopMatrix();
	}

	public void move(float x,float y)
	{
		currentX+= x;
		currentY+= y;
		//keep actor inbounds
		if (currentX < minY) currentX = minX;
		if (currentX > maxX) currentX = maxX;
		if (currentY < minY) currentY = minY;
		if (currentY > maxY) currentY = maxY;
	}
	public void rotate(float r)
	{
		currentRotation+= r;
	}
	
	public void changecolour(float r, float g)
	{
		currentR +=r;
		currentG +=g;
		
		//keep colour inbounds
		if (currentR>maxR) currentR = maxR;
		if (currentR<minR) currentR = minR;
		if (currentG>maxG) currentG = maxR;
		if (currentG<minG) currentG = minR;
	}
}
