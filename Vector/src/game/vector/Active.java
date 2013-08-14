package game.vector;

import Updates;

import java.awt.Graphics2D;
import java.awt.Point;

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
		if (!collidesWithWalls)
		{
			x += xSpeed;
			y += ySpeed;
		}
		else
		{
			boolean moveXAllowed = true;
			boolean moveYAllowed = true;
			
			for (int i = 0; i < Vector.d.updateList.size(); i ++)
			{
				if (Vector.d.updateList.get(i) instanceof Wall)
				{
					Wall wall = (Wall) Vector.d.updateList.get(i);
					if (wall.doesCollideWithCircle(new Point(x + xSpeed, y + ySpeed), radius))
					{
						if (wall.doesCollideWithCircle(new Point(x, y + ySpeed), radius))
						{
							moveYAllowed = false;
						}
						if (wall.doesCollideWithCircle(new Point(x + xSpeed, y), radius))
						{
							moveXAllowed = false;
						}
						
					}
				}
				if (Vector.d.updateList.get(i) instanceof Pushable)
				{
					Pushable p = (Pushable) Vector.d.updateList.get(i);
					if (HelperClass.pythagoras(new Point(x + xSpeed, y + ySpeed), new Point(p.x,p.y)) < radius + p.radius)
					{
						if (HelperClass.pythagoras(new Point(x, y + ySpeed), new Point(p.x,p.y)) < radius + p.radius)
						{
							moveXAllowed = false;
							p.push(0,ySpeed);
						}
						if (HelperClass.pythagoras(new Point(x + xSpeed, y), new Point(p.x,p.y)) < radius + p.radius)
						{
							moveYAllowed = false;
							p.push(xSpeed,0);
						}

					}
				}
			}
			if (moveXAllowed)
			{
				x += xSpeed;
				Vector.d.viewPort.x = x;
			}
			if (moveYAllowed)
			{
				y += ySpeed;
				Vector.d.viewPort.y = y;
			}
		}
		draw(g);
	}
	
	protected abstract void draw(Graphics2D g);
}
