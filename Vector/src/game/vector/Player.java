package game.vector;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player extends Active implements Updates, KeyListener
{
	public int speed = 4;
	private boolean shooting;
	private Direction Direction;
	private int reloadTimer = 0;
	public boolean dead = false;
	
	public Player(int _x, int _y) 
	{
		x = _x;
		y = _y;
		collidesWithWalls = true;
	}


	
	protected void draw(Graphics2D g) 
	{
		Vector.d.viewPort.x = x;
		Vector.d.viewPort.y = y;

		if (dead)
		{
			System.out.println("Player is dead!");
			new TimedLevelReset(1500);
			spawnDeathParticles(20);
			return;
		}
		if (checkHitBullet())
		{
			dead = true;
		}

		g.setColor(Color.RED);
		g.fillOval(x - radius, y - radius, radius*2, radius*2);
		g.setColor(new Color(255,0,0,80));
		g.setStroke(new BasicStroke(3));
		g.drawOval(x - radius, y - radius, radius*2, radius*2);
		g.setColor(new Color(255,0,0,130));
		g.setStroke(new BasicStroke(1));
		g.drawOval(x - radius, y - radius, radius*2, radius*2);
		if (shooting)
		{
			if (reloadTimer != 0)
			{
				reloadTimer--;
			}
			else
			{
				int bulletXSpeed = 0;
				int bulletYSpeed = 0;
				switch (Direction)
				{
					case LEFT: bulletXSpeed = -12; break;
					case UP: bulletYSpeed = -12; break;
					case RIGHT: bulletXSpeed = 12; break;
					case DOWN: bulletYSpeed = 12; break;
				}
				
				Vector.d.updateList.add(new PlayerBullet(x,y,bulletXSpeed,bulletYSpeed));
				reloadTimer = 30;
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		if (e.getKeyCode() == KeyEvent.VK_R && !dead)
		{
			new TimedLevelReset(1000);
			spawnDeathParticles(20);
			dead = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_E)
		{
			for (int i = 0; i < Vector.d.updateList.size(); i ++)
			{
				if (Vector.d.updateList.get(i) instanceof Switch)
				{

					Switch t = (Switch) Vector.d.updateList.get(i);
					int dis = HelperClass.pythagoras(new Point(x,y), new Point(t.x,t.y));
					if (dis < radius + t.radius)
					{
						t.activate();
					}
				}
			}
		}
		
		if (e.getKeyCode() == KeyEvent.VK_A)
		{
			xSpeed = -speed;
		}
		if (e.getKeyCode() == KeyEvent.VK_W)
		{
			ySpeed = -speed;
		}
		if (e.getKeyCode() == KeyEvent.VK_D)
		{
			xSpeed = speed;
		}
		if (e.getKeyCode() == KeyEvent.VK_S)
		{
			ySpeed = speed;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			shooting = true;
			Direction = game.vector.Direction.LEFT;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			shooting = true;
			Direction = game.vector.Direction.UP;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			shooting = true;
			Direction = game.vector.Direction.RIGHT;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			shooting = true;
			Direction = game.vector.Direction.DOWN;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{

		
		if (e.getKeyCode() == KeyEvent.VK_A && !(xSpeed > 0))
		{
			xSpeed = 0;
		}
		if (e.getKeyCode() == KeyEvent.VK_W && !(ySpeed > 0))
		{
			ySpeed = 0;
		}
		if (e.getKeyCode() == KeyEvent.VK_D && !(xSpeed < 0))
		{
			xSpeed = 0;
		}
		if (e.getKeyCode() == KeyEvent.VK_S && !(ySpeed < 0))
		{
			ySpeed = 0;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT && Direction == game.vector.Direction.LEFT)
		{
			shooting = false;
			Direction = game.vector.Direction.LEFT;
			reloadTimer = 0;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP && Direction == game.vector.Direction.UP)
		{
			shooting = false;
			Direction = game.vector.Direction.UP;
			reloadTimer = 0;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT && Direction == game.vector.Direction.RIGHT)
		{
			shooting = false;
			Direction = game.vector.Direction.RIGHT;
			reloadTimer = 0;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN && Direction == game.vector.Direction.DOWN)
		{	
			shooting = false;
			Direction = game.vector.Direction.DOWN;
			reloadTimer = 0;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) 
	{
	}
	
	private boolean checkHitBullet()
	{
		for (int i = 0; i < Vector.d.updateList.size(); i ++)
		{
			if (Vector.d.updateList.get(i) instanceof EnemyBullet)
			{
				EnemyBullet b = (EnemyBullet) Vector.d.updateList.get(i);
				if (HelperClass.pythagoras(new Point(b.x,b.y), new Point(x,y)) < 4 + radius)
				{
					Vector.d.updateList.remove(b);
					return true;
				}
			}
			if (Vector.d.updateList.get(i) instanceof Fighter)
			{
				Fighter b = (Fighter) Vector.d.updateList.get(i);
				if (HelperClass.pythagoras(new Point(b.x,b.y), new Point(x,y)) < b.radius + radius)
				{
					Vector.d.updateList.remove(b);
					return true;
				}
			}
		}
		return false;
	}
	
	public void spawnDeathParticles(int number)
	{
		Vector.d.updateList.remove(this);
		for (int i = 0; i < number; i ++)
		{
			Vector.d.updateList.add(new PlayerParticle(x,y,(int) (Math.random() * 8 - 4), (int) (Math.random() * 8 - 4) * 2, (int) (Math.random() * 10)));
		}
		dead = true;
		return;
	}

}
