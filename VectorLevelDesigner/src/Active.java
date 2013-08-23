


import java.awt.Graphics2D;

public abstract class Active implements Updates
{
	public int radius = 16;
	public int x;
	public int y;
	public int xSpeed;
	public int ySpeed;
	boolean collidesWithWalls;
	public Active() 
	{
	}
	
	@Override
	public synchronized final void update(Graphics2D g)
	{
		draw(g);
	}
	
	protected abstract void draw(Graphics2D g);
}
