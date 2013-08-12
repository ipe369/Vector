package game.vector;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;


public class PressurePlate extends Trigger implements Updates 
{

	public PressurePlate(int _x, int _y) 
	{
		super(_x, _y);
		radius = 16;
	}

	@Override
	public void update(Graphics2D g) 
	{
		Color color;
		if (getState())
		{
			color = new Color(100,255,0);
		}
		else
		{
			color = new Color(150,0,0);
		}
		g.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 100));
		g.setStroke(new BasicStroke(3));
		g.drawRect(x - radius, y - radius, radius*2, radius*2);
		//draw
		g.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue()));
		g.fillRect(x - radius, y - radius, radius*2, radius*2);
		if (isBeingPressed())
			turnOn();
		else
			turnOff();
	}

	@Override
	public void activate() 
	{
		turnOn();
	}
	
	public boolean isBeingPressed()
	{
		for (int i = 0; i < Vector.d.updateList.size(); i ++)
		{
			if (Vector.d.updateList.get(i) instanceof Player)
			{
				Player p = (Player) Vector.d.updateList.get(i);
				if (HelperClass.pythagoras(new Point(x,y), new Point(p.x,p.y)) < radius + p.radius)
				{
					return true;
				}
			}
			if (Vector.d.updateList.get(i) instanceof Enemy)
			{
				Enemy p = (Enemy) Vector.d.updateList.get(i);
				if (HelperClass.pythagoras(new Point(x,y), new Point(p.x,p.y)) < radius + p.radius)
				{
					return true;
				}
			}
		}
		return false;
	}

}
