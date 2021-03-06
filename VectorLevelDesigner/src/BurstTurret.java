

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class BurstTurret extends Enemy implements Updates 
{
	private int reloadTimer = 80;
	public int offsetTiming = 0;
	public BurstTurret(int _x, int _y) 
	{
		super(_x, _y);
		radius = 16;
		health = 6;
	}
	public BurstTurret(int _x, int _y, int _offsetTiming) 
	{
		super(_x, _y);
		radius = 16;
		health = 6;
		offsetTiming = _offsetTiming;
	}

	@Override
	protected void draw(Graphics2D g) 
	{
		xSpeed = 0;
		ySpeed = 0;
		drawSelf(g,false);
	}
	
	private void drawSelf(Graphics2D g, boolean drawHit)
	{
		Color color;
		if (drawHit)
		{
			color = new Color(200,230,255);
		}
		else
		{
			color = new Color(80,150,255);
		}
		g.setColor(new Color(color.getRed(),color.getGreen(),color.getBlue(),80));
		g.setStroke(new BasicStroke(16,BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER));
		g.drawLine(x - 16, y, x + 16, y);
		g.drawLine(x, y - 16, x, y + 16);
		g.setStroke(new BasicStroke(10,BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER));
		g.drawLine(x - 24, y, x + 24, y);
		g.drawLine(x, y - 24, x, y + 24);
		g.drawOval(x - 9, y - 9, 18, 18);

		//2nd glow
		g.setColor(new Color(color.getRed(),color.getGreen(),color.getBlue(),140));
		g.setStroke(new BasicStroke(14,BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER));
		g.drawLine(x - 16, y, x + 16, y);
		g.drawLine(x, y - 16, x, y + 16);
		g.setStroke(new BasicStroke(8,BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER));
		g.drawLine(x - 24, y, x + 24, y);
		g.drawLine(x, y - 24, x, y + 24);
		g.drawOval(x - 9, y - 9, 18, 18);
		
		//draw enemy
		g.setColor(new Color(color.getRed(),color.getGreen(),color.getBlue()));
		g.setStroke(new BasicStroke(12,BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER));
		g.drawLine(x - 16, y, x + 16, y);
		g.drawLine(x, y - 16, x, y + 16);
		g.setStroke(new BasicStroke(6,BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER));
		g.drawLine(x - 24, y, x + 24, y);
		g.drawLine(x, y - 24, x, y + 24);
		g.fillOval(x - 12, y - 12, 24, 24);
		
		//inner circle bit
		//glow
		g.setColor(new Color(color.getRed(),color.getGreen(),color.getBlue(),80));
		g.setStroke(new BasicStroke(4));
		g.drawOval(x - 8, y - 8, 16,16);
		g.setColor(new Color(100,255,200,150));
		g.setStroke(new BasicStroke(2));
		g.drawOval(x - 8, y - 8, 16,16);
		g.setColor(new Color(100,255,200));
		g.fillOval(x - 8, y - 8, 16,16);
	}

}
