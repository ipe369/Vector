package game.vector;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player extends Active implements Updates, KeyListener
{
	public int speed = 4;
	private boolean shooting;
	private ShootingDirection shootingDirection;
	private int reloadTimer = 30;
	
	public Player(int _x, int _y) 
	{
		x = _x;
		y = _y;
		collidesWithWalls = true;
	}


	
	protected void draw(Graphics2D g) 
	{
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
				switch (shootingDirection)
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
		if (e.getKeyChar() == 'a')
		{
			xSpeed = -speed;
		}
		if (e.getKeyChar() == 'w')
		{
			ySpeed = -speed;
		}
		if (e.getKeyChar() == 'd')
		{
			xSpeed = speed;
		}
		if (e.getKeyChar() == 's')
		{
			ySpeed = speed;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			shooting = true;
			shootingDirection = ShootingDirection.LEFT;
			reloadTimer = 0;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			shooting = true;
			shootingDirection = ShootingDirection.UP;
			reloadTimer = 0;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			shooting = true;
			shootingDirection = ShootingDirection.RIGHT;
			reloadTimer = 0;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			shooting = true;
			shootingDirection = ShootingDirection.DOWN;
			reloadTimer = 0;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{
		if (e.getKeyChar() == 'a')
		{
			if (!(xSpeed > 0))
				xSpeed = 0;
		}
		if (e.getKeyChar() == 'w')
		{
			if (!(ySpeed > 0))
				ySpeed = 0;
		}
		if (e.getKeyChar() == 'd')
		{
			if (!(xSpeed < 0))
				xSpeed = 0;
		}
		if (e.getKeyChar() == 's')
		{
			if (!(ySpeed < 0))
				ySpeed = 0;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			if (shootingDirection == ShootingDirection.LEFT)
			{
				shooting = false;
				shootingDirection = ShootingDirection.LEFT;
				reloadTimer = 0;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			if (shootingDirection == ShootingDirection.UP)
			{
				shooting = false;
				shootingDirection = ShootingDirection.UP;
				reloadTimer = 0;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			if (shootingDirection == ShootingDirection.RIGHT)
			{
				shooting = false;
				shootingDirection = ShootingDirection.RIGHT;
				reloadTimer = 0;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			if (shootingDirection == ShootingDirection.DOWN)
			{
				shooting = false;
				shootingDirection = ShootingDirection.DOWN;
				reloadTimer = 0;
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) 
	{
	}

}
