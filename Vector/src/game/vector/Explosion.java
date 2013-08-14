package game.vector;

import Updates;

import java.awt.Color;
import java.awt.Graphics2D;

public class Explosion implements Updates 
{
	private int timePassed;
	public int size = 16;
	public int x;
	public int y;
	public Explosion(int _x, int _y, int _size) 
	{
		size = _size;
		x = _x;
		y = _y;
	}

	@Override
	public void update(Graphics2D g) 
	{
		g.setColor(Color.ORANGE);
		g.fillOval(x - size, y - size, size*2, size*2);
		g.setColor(Color.BLACK);
		g.fillOval(x-timePassed, y-timePassed, timePassed*2, timePassed*2);
		timePassed ++;
		if (timePassed > size)
			Vector.d.updateList.remove(this);
	}

}
