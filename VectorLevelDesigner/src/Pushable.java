import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;


public class Pushable implements Updates 
{
	public int x;
	public int y;
	public int radius = 8;
	public Pushable(int _x, int _y) 
	{
		x = _x;
		y = _y;
	}

	@Override
	public void update(Graphics2D g) 
	{
		g.setStroke(new BasicStroke(3));
		g.setColor(new Color(50,255,0,100));
		g.drawOval(x - radius, y - radius, radius*2, radius*2);
		g.setStroke(new BasicStroke(1));
		g.setColor(new Color(100,255,50));
		g.drawOval(x - radius, y - radius, radius*2, radius*2);
	}
	

	public void push(int _x, int _y)
	{
	}
}
