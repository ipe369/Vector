package game.vector;

import Updates;

import java.awt.Graphics2D;

public abstract class Trigger implements Updates 
{
	public int radius = 8;
	public int x;
	public int y;
	public boolean on;
	public Trigger(int _x, int _y)
	{
		x = _x;
		y = _y;
	}

	@Override
	public abstract void update(Graphics2D g);
	public abstract void activate();
	public void toggle()
	{
		on = !on;
	}
	public void turnOn()
	{		
		on = true;
	}
	public void turnOff()
	{
		on = false;
	}
	
	public boolean getState()
	{
		System.out.println(on);
		return on;
	}
}
