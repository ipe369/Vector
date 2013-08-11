package game.vector;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Fighter extends Enemy implements Updates 
{
	public int speed = 3;
	public int degrees = 0;
	public Fighter(int _x, int _y) 
	{
		super(_x, _y);
		radius = 8;
	}

	@Override
	protected void draw(Graphics2D g) 
	{
		if (checkHitBullet())
		{
			Vector.d.updateList.remove(this);
			Vector.d.updateList.add(new Explosion(x,y,16));
			return;
		}
		
		boolean canSee = canSeePlayer();
		System.out.println(canSee);
		if (canSee)
		{
			degrees = (int) Math.toDegrees(Math.atan2(Vector.d.currentLevel.player.y - y, Vector.d.currentLevel.player.x - x));
			xSpeed = (int) (Math.cos(Math.toRadians(degrees)) * speed);
			ySpeed = (int) (Math.sin(Math.toRadians(degrees)) * speed);
		}
		else
		{
			xSpeed = 0;
			ySpeed = 0;
		}
		Color orangeColor;
		orangeColor = Color.ORANGE;
		g.setColor(new Color(orangeColor.getRed(), orangeColor.getGreen(), orangeColor.getBlue(), 50));
		g.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		g.drawLine((int) (Math.cos(Math.toRadians(degrees)) * 8) + x, (int) (Math.sin(Math.toRadians(degrees)) * 8) + y, (int) (Math.cos(Math.toRadians(degrees + 120))*8) + x, (int) (Math.sin(Math.toRadians(degrees + 120))*8) + y);
		g.drawLine((int) (Math.cos(Math.toRadians(degrees + 120))*8) + x, (int) (Math.sin(Math.toRadians(degrees + 120))*8) + y, x,y);
		g.drawLine(x, y,(int) (Math.cos(Math.toRadians(degrees + 240))*8) + x, (int) (Math.sin(Math.toRadians(degrees + 240))*8) + y);
		g.drawLine((int) (Math.cos(Math.toRadians(degrees + 240))*8) + x, (int) (Math.sin(Math.toRadians(degrees + 240))*8) + y,(int) (Math.cos(Math.toRadians(degrees)) * 8) + x, (int) (Math.sin(Math.toRadians(degrees)) * 8) + y);
		g.setColor(orangeColor.brighter());
		g.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		g.drawLine((int) (Math.cos(Math.toRadians(degrees)) * 8) + x, (int) (Math.sin(Math.toRadians(degrees)) * 8) + y, (int) (Math.cos(Math.toRadians(degrees + 120))*8) + x, (int) (Math.sin(Math.toRadians(degrees + 120))*8) + y);
		g.drawLine((int) (Math.cos(Math.toRadians(degrees + 120))*8) + x, (int) (Math.sin(Math.toRadians(degrees + 120))*8) + y, x,y);
		g.drawLine(x, y,(int) (Math.cos(Math.toRadians(degrees + 240))*8) + x, (int) (Math.sin(Math.toRadians(degrees + 240))*8) + y);
		g.drawLine((int) (Math.cos(Math.toRadians(degrees + 240))*8) + x, (int) (Math.sin(Math.toRadians(degrees + 240))*8) + y,(int) (Math.cos(Math.toRadians(degrees)) * 8) + x, (int) (Math.sin(Math.toRadians(degrees)) * 8) + y);
		degrees++;
	}

}
