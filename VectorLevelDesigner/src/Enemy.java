import java.awt.Graphics2D;


public abstract class Enemy extends Active implements Updates 
{
	public int health = 1;
	
	public Enemy(int _x, int _y) 
	{
		x = _x;
		y = _y;
		collidesWithWalls = true;
	}

	@Override
	protected abstract void draw(Graphics2D g);



}
