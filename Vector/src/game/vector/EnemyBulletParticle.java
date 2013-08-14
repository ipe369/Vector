package game.vector;

import Updates;

import java.awt.Color;
import java.awt.Graphics2D;

public class EnemyBulletParticle extends Particle implements Updates 
{
	public EnemyBulletParticle(int _x, int _y, int _xSpeed, int _ySpeed) 
	{
		super(_x, _y, _xSpeed, _ySpeed, 30);
	}

	@Override
	protected void draw(Graphics2D g) 
	{
		if (lifeTime <= 0)
		{
			Vector.d.updateList.remove(this);
			return;
		}
		g.setColor(new Color(200,0,200,(lifeTime/maxLifeTime)*165 + 90));
		g.fillOval(x-3,y-3,6,6);
		lifeTime --;
		
	}
}
