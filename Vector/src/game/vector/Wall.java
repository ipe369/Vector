package game.vector;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Line2D;


//I am aware colour is spelled with a U, however to keep it similar to the Color class
//I spell it the american way.
public class Wall implements Updates
{
	Point start;
	Point end;
	public boolean solid = true;
	public Color wallColor = Color.CYAN;
	public Wall(Point _start, Point _end) 
	{
		start = _start;
		end = _end;
	}
	
	@Override
	public void update(Graphics2D g)
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
	
	public boolean doesCollideWithCircle(Point circle, int radius)
	{
		if (!solid)
		{
			return false;
		}
		double dist = Line2D.ptSegDist(start.x, start.y, end.x, end.y, circle.x, circle.y);
		if (radius > dist)
			return true;
		else
			return false;
	}
	
	public void hitByBullet(Bullet bullet)
	{
	}
}
