package game.vector;

import Updates;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public abstract class Enemy extends Active implements Updates 
{
	public int health = 1;
	
	public Enemy(int _x, int _y) 
	{
		x = _x;
		y = _y;
		collidesWithWalls = true;
	}

	@Override
	protected abstract void draw(Graphics2D g);
	
	public boolean canSeePlayer()
	{
		for (int i = 0; i < Vector.d.updateList.size(); i ++)
		{
			if (Vector.d.updateList.get(i) instanceof Wall)
			{
				if (Vector.d.updateList.get(i) instanceof Door)
				{
					if ((boolean) ((Door) Vector.d.updateList.get(i)).open)
					{
						continue;
					}
				}
				Wall wall = (Wall) Vector.d.updateList.get(i);
				if (new Line2D.Float((Point2D) wall.start,(Point2D) wall.end).intersectsLine(new Line2D.Float(x,y,Vector.d.currentLevel.player.x,Vector.d.currentLevel.player.y)))
				{
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean checkHitBullet()
	{
		for (int i = 0; i < Vector.d.updateList.size(); i ++)
		{
			if (Vector.d.updateList.get(i) instanceof PlayerBullet)
			{
				PlayerBullet b = (PlayerBullet) Vector.d.updateList.get(i);
				if (HelperClass.pythagoras(new Point(b.x,b.y), new Point(x,y)) < 4 + radius)
				{
					Vector.d.updateList.remove(b);
					return true;
				}
			}
		}
		return false;
	}

}
