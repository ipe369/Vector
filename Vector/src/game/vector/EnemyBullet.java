package game.vector;

import Updates;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class EnemyBullet extends Bullet implements Updates 
{
	public EnemyBullet(int _x, int _y, int _xSpeed, int _ySpeed) 
	{
		super(_x, _y, _xSpeed, _ySpeed);
		lifeTime = 100;
	}

	@Override
	public void draw2(Graphics2D g) 
	{
		g.setColor(new Color(255,50,130, 80));
		g.setStroke(new BasicStroke(4));
		g.drawOval(x - 4, y - 4, 8,8);
		g.setColor(new Color(255,100,200, 150));
		g.setStroke(new BasicStroke(2));
		g.drawOval(x - 4, y - 4, 8,8);
		g.setColor(new Color(255,100,200).brighter());
		g.fillOval(x-4,y-4,8,8);
	}
	
	@Override
	public void onDead(Graphics2D g)
	{
		Vector.d.updateList.remove(this);
		for (int i = 0; i < 4; i ++)
		{
			Vector.d.updateList.add(new EnemyBulletParticle(x,y,(int) (Math.random() * 8 - 4), (int) (Math.random() * 8 - 4) * 2));
		}
		dead = true;
		return;
	}

}
