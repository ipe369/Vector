package game.vector;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class BurstTurret extends Enemy implements Updates 
{
	private int reloadTimer = 80;
	public BurstTurret(int _x, int _y) 
	{
		super(_x, _y);
		radius = 16;
	}

	@Override
	protected void draw(Graphics2D g) 
	{
		xSpeed = 0;
		ySpeed = 0;
		//glow
		//1st glow
		g.setColor(new Color(80,150,255,80));
		g.setStroke(new BasicStroke(16,BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER));
		g.drawLine(x - 16, y, x + 16, y);
		g.drawLine(x, y - 16, x, y + 16);
		g.setStroke(new BasicStroke(10,BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER));
		g.drawLine(x - 24, y, x + 24, y);
		g.drawLine(x, y - 24, x, y + 24);
		g.drawOval(x - 9, y - 9, 18, 18);
		//2nd glow
		g.setColor(new Color(80,150,255,140));
		g.setStroke(new BasicStroke(14,BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER));
		g.drawLine(x - 16, y, x + 16, y);
		g.drawLine(x, y - 16, x, y + 16);
		g.setStroke(new BasicStroke(8,BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER));
		g.drawLine(x - 24, y, x + 24, y);
		g.drawLine(x, y - 24, x, y + 24);
		g.drawOval(x - 9, y - 9, 18, 18);
		
		//draw enemy
		g.setColor(new Color(80,150,255));
		g.setStroke(new BasicStroke(12,BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER));
		g.drawLine(x - 16, y, x + 16, y);
		g.drawLine(x, y - 16, x, y + 16);
		g.setStroke(new BasicStroke(6,BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER));
		g.drawLine(x - 24, y, x + 24, y);
		g.drawLine(x, y - 24, x, y + 24);
		g.fillOval(x - 12, y - 12, 24, 24);
		
		reloadTimer --;
		if (reloadTimer <= 0)
		{
			reloadTimer = 80;
			Vector.d.updateList.add(new EnemyBullet(x,y,-7,0));
			Vector.d.updateList.add(new EnemyBullet(x,y,7,0));
			Vector.d.updateList.add(new EnemyBullet(x,y,0,-7));
			Vector.d.updateList.add(new EnemyBullet(x,y,0,7));
		}
	}

}
