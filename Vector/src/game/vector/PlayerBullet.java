package game.vector;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class PlayerBullet extends Bullet implements Updates 
{
	private Point prevPoint;
	public PlayerBullet(int _x, int _y, int _xSpeed, int _ySpeed) 
	{
		super(_x, _y, _xSpeed, _ySpeed);
		prevPoint = new Point(_x,_y);
	}

	@Override
	public void draw2(Graphics2D g) 
	{
		g.setColor(Color.yellow);
		g.fillOval(x - 4, y - 4, 8, 8);
		g.setColor(Color.yellow.darker());
		g.fillOval(x - 4, y - 4, 8, 8);
		
	}

}
