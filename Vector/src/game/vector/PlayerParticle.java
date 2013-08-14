package game.vector;

import Updates;

import java.awt.Color;
import java.awt.Graphics2D;

public class PlayerParticle extends Particle implements Updates 
{
	public int size;
	public PlayerParticle(int _x, int _y, int _xSpeed, int _ySpeed, int _size) 
	{
		super(_x, _y, _xSpeed, _ySpeed, 400);
		size = _size;
	}
	
	@Override
	protected void draw(Graphics2D g) 
	{
		if (lifeTime <= 0)
		{
			Vector.d.updateList.remove(this);
			return;
		}
		g.setColor(new Color(255,0,0));
		g.fillOval(x-size,y-size,size*2, size*2);
		lifeTime --;
		
	}
}
