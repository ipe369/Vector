package game.vector;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class LevelEnd implements Updates 
{
	protected float frame = 1;
	protected final int maxFrame = 16;
	public int x;
	public int y;
	public LevelEnd(int _x, int _y)
	{
		x = _x;
		y = _y;
	}
	
	@Override
	public void update(Graphics2D g) 
	{
		g.setColor(new Color(0,255,0,50));
		g.setStroke(new BasicStroke(4));
		g.drawOval(x - 16, y - 16, 32, 32);
		g.setColor(new Color(0,255,0,90));
		g.setStroke(new BasicStroke(2));
		g.drawOval(x - 16, y - 16, 32, 32);
		
		g.setColor(Color.GREEN.darker());
		g.fillOval(x - 16, y - 16, 32, 32);
		
		
		g.setColor(new Color(255,255,0,120));
		g.setStroke(new BasicStroke(7));
		g.drawOval(x - (int) frame,y - (int) frame,(int) frame*2,(int) frame*2);
		g.setColor(new Color(255,255,0));
		g.setStroke(new BasicStroke(4));
		g.drawOval(x - (int) frame,y - (int) frame,(int) frame*2,(int) frame*2);
		frame += 0.2;
		if (frame >= maxFrame-2)
		{
			frame = 1;
		}
		if (isCollidingWithPlayer())
		{
			Vector.d.updateList.clear();
			Vector.d.levelNumber ++;
			try 
			{
				Vector.d.currentLevel = Vector.d.currentLevel.nextLevel.newInstance();
			} 
			catch (InstantiationException | IllegalAccessException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	public boolean isCollidingWithPlayer()
	{
		if (Vector.d.currentLevel.player != null)
		{
			if (HelperClass.pythagoras(new Point(x,y), new Point(Vector.d.currentLevel.player.x,Vector.d.currentLevel.player.y)) < 32)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}

}
