package game.vector;

import Updates;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class Pushable implements Updates 
{
	public int x;
	public int y;
	public int radius = 8;
	public Pushable(int _x, int _y) 
	{
		x = _x;
		y = _y;
	}

	@Override
	public void update(Graphics2D g) 
	{
		g.setStroke(new BasicStroke(3));
		g.setColor(new Color(50,255,0,100));
		g.drawOval(x - radius, y - radius, radius*2, radius*2);
		g.setStroke(new BasicStroke(1));
		g.setColor(new Color(100,255,50));
		g.drawOval(x - radius, y - radius, radius*2, radius*2);
	}
	

	public void push(int _x, int _y)
	{
		boolean moveXAllowed = true;
		boolean moveYAllowed = true;
		for (int i = 0; i < Vector.d.updateList.size(); i ++)
		{
			if (Vector.d.updateList.get(i) instanceof Wall)
			{
				Wall wall = (Wall) Vector.d.updateList.get(i);
				if (wall.doesCollideWithCircle(new Point(x + _x, y + _y), radius))
				{
					if (wall.doesCollideWithCircle(new Point(x, y + _y), radius))
					{
						moveYAllowed = false;
					}
					if (wall.doesCollideWithCircle(new Point(x + _x, y), radius))
					{
						moveXAllowed = false;
					}
					
				}
			}
			if (Vector.d.updateList.get(i) instanceof Pushable)
			{
				Pushable p = (Pushable) Vector.d.updateList.get(i);
				if (p != this)
				{
					if (HelperClass.pythagoras(new Point(x + _x, y + _y), new Point(p.x,p.y)) < radius + p.radius)
					{
						if (HelperClass.pythagoras(new Point(x, y + _y), new Point(p.x,p.y)) < radius + p.radius)
						{
							moveYAllowed = false;
						}
						if (HelperClass.pythagoras(new Point(x + _x, y), new Point(p.x,p.y)) < radius + p.radius)
						{
							moveXAllowed = false;
						}
						p.push(_x, _y);
					}
				}
			}
		}
		if (moveXAllowed)
		{
			x += _x/2;
		}
		if (moveYAllowed)
		{
			y += _y/2;
		}
	}
}
