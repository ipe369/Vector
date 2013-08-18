package game.vector;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class BurstTurret extends Enemy implements Updates 
{
	private int reloadTimer = 80;
	private boolean drawHitDamage;
	public BurstTurret(int _x, int _y) 
	{
		super(_x, _y);
		radius = 16;
		health = 6;
	}

	@Override
	protected void draw(Graphics2D g) 
	{
		xSpeed = 0;
		ySpeed = 0;
		//glow
		//1st glow
		drawSelf(g,drawHitDamage);
		drawHitDamage = false;
		
		
		
		reloadTimer --;
		if (reloadTimer <= 0)
		{
			reloadTimer = 80;
			Vector.d.updateList.add(new EnemyBullet(x - radius,y,-7,0));
			Vector.d.updateList.add(new EnemyBullet(x + radius,y,7,0));
			Vector.d.updateList.add(new EnemyBullet(x,y - radius,0,-7));
			Vector.d.updateList.add(new EnemyBullet(x,y + radius,0,7));
		}
		
		if (checkHitBullet())
		{
			health --;
			drawHitDamage = true;
		}
		if (health <= 0)
		{
			Vector.d.updateList.remove(this);
			for (int i = 0; i < 4; i ++)
			{
				Vector.d.updateList.add(new Explosion(x + (int) (Math.random() * 16 - 8),y + (int) (Math.random() * 16 - 8) * 2,16));
			}
			return;
		}
	}
	
	private void drawSelf(Graphics2D g, boolean drawHit)
	{
		Color color;
		if (drawHit)
		{
			color = new Color(200,230,255);
		}
		else
		{
			color = new Color(80,150,255);
		}
		g.setColor(new Color(color.getRed(),color.getGreen(),color.getBlue(),80));
		g.setStroke(new BasicStroke(16,BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER));
		g.drawLine(x - 16, y, x + 16, y);
		g.drawLine(x, y - 16, x, y + 16);
		g.setStroke(new BasicStroke(10,BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER));
		g.drawLine(x - 24, y, x + 24, y);
		g.drawLine(x, y - 24, x, y + 24);
		g.drawOval(x - 9, y - 9, 18, 18);

		//2nd glow
		g.setColor(new Color(color.getRed(),color.getGreen(),color.getBlue(),140));
		g.setStroke(new BasicStroke(14,BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER));
		g.drawLine(x - 16, y, x + 16, y);
		g.drawLine(x, y - 16, x, y + 16);
		g.setStroke(new BasicStroke(8,BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER));
		g.drawLine(x - 24, y, x + 24, y);
		g.drawLine(x, y - 24, x, y + 24);
		g.drawOval(x - 9, y - 9, 18, 18);
		
		//draw enemy
		g.setColor(new Color(color.getRed(),color.getGreen(),color.getBlue()));
		g.setStroke(new BasicStroke(12,BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER));
		g.drawLine(x - 16, y, x + 16, y);
		g.drawLine(x, y - 16, x, y + 16);
		g.setStroke(new BasicStroke(6,BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER));
		g.drawLine(x - 24, y, x + 24, y);
		g.drawLine(x, y - 24, x, y + 24);
		g.fillOval(x - 12, y - 12, 24, 24);
		
		//inner circle bit
		//glow
		g.setColor(new Color(color.getRed(),color.getGreen(),color.getBlue(),80));
		g.setStroke(new BasicStroke(4));
		g.drawOval(x - 8, y - 8, 16,16);
		g.setColor(new Color(100,255,200,150));
		g.setStroke(new BasicStroke(2));
		g.drawOval(x - 8, y - 8, 16,16);
		g.setColor(new Color(100,255,200));
		g.fillOval(x - 8, y - 8, 16,16);
	}

}
