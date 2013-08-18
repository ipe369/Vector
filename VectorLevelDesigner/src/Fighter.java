

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Fighter extends Enemy implements Updates 
{
	public int speed = 3;
	public int degrees = 0;
	public Fighter(int _x, int _y) 
	{
		super(_x, _y);
		radius = 8;
	}

	@Override
	protected void draw(Graphics2D g) 
	{

		Color orangeColor;
		orangeColor = Color.ORANGE;
		g.setColor(new Color(orangeColor.getRed(), orangeColor.getGreen(), orangeColor.getBlue(), 80));
		g.setStroke(new BasicStroke(4, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		g.drawLine((int) (Math.cos(Math.toRadians(degrees)) * 8) + x, (int) (Math.sin(Math.toRadians(degrees)) * 8) + y, (int) (Math.cos(Math.toRadians(degrees + 120))*8) + x, (int) (Math.sin(Math.toRadians(degrees + 120))*8) + y);
		g.drawLine((int) (Math.cos(Math.toRadians(degrees + 120))*8) + x, (int) (Math.sin(Math.toRadians(degrees + 120))*8) + y, x,y);
		g.drawLine(x, y,(int) (Math.cos(Math.toRadians(degrees + 240))*8) + x, (int) (Math.sin(Math.toRadians(degrees + 240))*8) + y);
		g.drawLine((int) (Math.cos(Math.toRadians(degrees + 240))*8) + x, (int) (Math.sin(Math.toRadians(degrees + 240))*8) + y,(int) (Math.cos(Math.toRadians(degrees)) * 8) + x, (int) (Math.sin(Math.toRadians(degrees)) * 8) + y);
		g.setColor(orangeColor.brighter());
		g.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		g.drawLine((int) (Math.cos(Math.toRadians(degrees)) * 8) + x, (int) (Math.sin(Math.toRadians(degrees)) * 8) + y, (int) (Math.cos(Math.toRadians(degrees + 120))*8) + x, (int) (Math.sin(Math.toRadians(degrees + 120))*8) + y);
		g.drawLine((int) (Math.cos(Math.toRadians(degrees + 120))*8) + x, (int) (Math.sin(Math.toRadians(degrees + 120))*8) + y, x,y);
		g.drawLine(x, y,(int) (Math.cos(Math.toRadians(degrees + 240))*8) + x, (int) (Math.sin(Math.toRadians(degrees + 240))*8) + y);
		g.drawLine((int) (Math.cos(Math.toRadians(degrees + 240))*8) + x, (int) (Math.sin(Math.toRadians(degrees + 240))*8) + y,(int) (Math.cos(Math.toRadians(degrees)) * 8) + x, (int) (Math.sin(Math.toRadians(degrees)) * 8) + y);
		degrees++;
	}

}
