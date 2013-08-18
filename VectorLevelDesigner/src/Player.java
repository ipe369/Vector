

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Player extends Active implements Updates
{
	public int speed = 4;
	public boolean dead = false;
	
	public Player(int _x, int _y) 
	{
		x = _x;
		y = _y;
		collidesWithWalls = true;
	}


	
	protected void draw(Graphics2D g) 
	{

		g.setColor(Color.RED);
		g.fillOval(x - radius, y - radius, radius*2, radius*2);
		g.setColor(new Color(255,0,0,80));
		g.setStroke(new BasicStroke(3));
		g.drawOval(x - radius, y - radius, radius*2, radius*2);
		g.setColor(new Color(255,0,0,130));
		g.setStroke(new BasicStroke(1));
		g.drawOval(x - radius, y - radius, radius*2, radius*2);

	}
}
