package game.vector;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class Door extends Wall implements Updates 
{
	Trigger trigger;
	public boolean open;
	public Door(Point start, Point end) 
	{
		super(start, end);
		wallColor = Color.white;
	}

	@Override
	public void update(Graphics2D g) 
	{
		if (trigger != null)
		{
			open = trigger.getState();
			//System.out.println(open);
		}
		else
		{
			open = false;
		}
		solid = !open;
		draw(g);
	}
	
	private void draw(Graphics2D g)
	{
		if (!open)
		{
			Color currentColor = wallColor;
			currentColor = new Color(currentColor.getRed(), currentColor.getBlue(), currentColor.getGreen(), 60);
			g.setColor(currentColor);
			g.setStroke(new BasicStroke(4, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
			g.drawLine(start.x, start.y, end.x, end.y);
			currentColor = wallColor;
			currentColor = new Color(currentColor.getRed(), currentColor.getBlue(), currentColor.getGreen(), 80);
			g.setColor(currentColor);
			g.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
			g.drawLine(start.x, start.y, end.x, end.y);
			currentColor = wallColor;
			currentColor = new Color(currentColor.getRed(), currentColor.getBlue(), currentColor.getGreen(), 255);
			g.setColor(currentColor);
			g.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
			g.drawLine(start.x, start.y, end.x, end.y);
		}
	}

}
