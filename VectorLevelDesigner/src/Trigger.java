


import java.awt.Graphics2D;

public abstract class Trigger implements Updates 
{
	public final int ID;
	public int radius = 8;
	public int x;
	public int y;
	public boolean on;
	public Trigger(int _x, int _y, int _ID)
	{
		x = _x;
		y = _y;
		ID = _ID;
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
		return on;
	}
}
