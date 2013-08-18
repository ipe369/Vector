package game.vector;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Switch extends Trigger implements Updates 
{
	public Switch(int _x, int _y, int _ID) 
	{
		super(_x,_y,_ID);
		radius = 8;
	}

	@Override 
	public void activate()
	{
		toggle();
	}
	
	@Override
	public void update(Graphics2D g) 
	{
		//System.out.println(on);
		Color color;
		if (getState())
		{
			color = new Color(100,255,0);
		}
		else
		{
			color = new Color(150,0,0);
		}
		//glow
		g.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 100));
		g.setStroke(new BasicStroke(3));
		g.drawOval(x - radius, y - radius, radius*2, radius*2);
		//draw
		g.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue()));
		g.fillOval(x - radius, y - radius, radius*2, radius*2);
	}
	
	@Override
	public boolean getState()
	{
		return on;
	}

}
