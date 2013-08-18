package game.vector;

import java.awt.Graphics2D;
import java.awt.Point;

public abstract class Bullet extends Active implements Updates 
{
	public int lifeTime = 45;
	public boolean dead;
	public Bullet(int _x, int _y, int _xSpeed, int _ySpeed) 
	{
		x = _x;
		y = _y;
		xSpeed = _xSpeed;
		ySpeed = _ySpeed;
	}

	@Override
	protected final void draw(Graphics2D g)
	{
		if (!dead)
		{
			lifeTime --;
			if (lifeTime <= 0)
			{
				onDead(g);
			}
			for (int i = 0; i < Vector.d.updateList.size(); i ++)
			{
				if (Vector.d.updateList.get(i) instanceof Wall)
				{
					Wall wall = (Wall) Vector.d.updateList.get(i);
					if (wall.doesCollideWithCircle(new Point(x,y), 8))
					{
						wall.hitByBullet(this);
						onDead(g);
					}
				}
			}
			draw2(g);
		}
	}
	
	public void onDead(Graphics2D g)
	{
		Vector.d.updateList.remove(this);
		for (int i = 0; i < 4; i ++)
		{
			Vector.d.updateList.add(new Particle(x,y,(int) (Math.random() * 8 - 4), (int) (Math.random() * 8 - 4) * 2, 40));
		}
		dead = true;
		return;
	}
	public abstract void draw2(Graphics2D g);

}
