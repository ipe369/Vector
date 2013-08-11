package game.vector;

import java.awt.Color;
import java.awt.Graphics2D;

public class Particle extends Active implements Updates 
{
	public int lifeTime = 10;
	private final int maxLifeTime;
	public Particle(int _x, int _y, int _xSpeed, int _ySpeed, int _lifeTime) 
	{
		x = _x;
		y = _y;
		xSpeed = _xSpeed;
		ySpeed = _ySpeed;
		lifeTime = _lifeTime;
		maxLifeTime = lifeTime;
	}

	@Override
	protected void draw(Graphics2D g) 
	{
		if (lifeTime <= 0)
		{
			Vector.d.updateList.remove(this);
			return;
		}
		g.setColor(new Color(200,200,0,(lifeTime/maxLifeTime)*165 + 90));
		g.fillOval(x-3,y-3,6,6);
		lifeTime --;
		
	}

}
