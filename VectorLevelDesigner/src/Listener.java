import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public class Listener implements KeyListener, MouseListener, ActionListener,MouseMotionListener 
{
	Data d;
	public Listener(Data _d) 
	{
		d = _d;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == d.wall)
		{
			d.mode = Mode.ADD_WALL;
			System.out.println("Adding walls");
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) 
	{
	}

	@Override
	public void mouseMoved(MouseEvent e) 
	{
		if (d.creatingWall)
		{
			Wall w = (Wall) d.currentlyModifying;
			if (!d.shiftModifier)
			{
				w.end.x = e.getX() - d.viewPort.x - 320;
				w.end.y = e.getY() - d.viewPort.y - 240;
			}
			else
			{
				if (Math.abs((e.getX() - d.viewPort.x - 320) - w.start.x) >  Math.abs(e.getY() - d.viewPort.y - 240 - w.start.y))
				{
					w.end.x = e.getX() - d.viewPort.x - 320;
					w.end.y = w.start.y;
				}
				else
				{
					w.end.x = w.start.x;
					w.end.y = e.getY() - d.viewPort.y - 240;
				}
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) 
	{
	}

	@Override
	public void mouseEntered(MouseEvent e) 
	{
	}

	@Override
	public void mouseExited(MouseEvent e) 
	{
	}

	@Override
	public void mousePressed(MouseEvent e) 
	{
	}

	@Override
	public void mouseReleased(MouseEvent e) 
	{
		if (d.mode == Mode.ADD_WALL)
		{
			if (!d.creatingWall)
			{
				d.creatingWall = true;
				System.out.println("Added wall");
				d.updateList.add(d.currentlyModifying = new Wall(new Point(e.getX() - d.viewPort.x - 320,e.getY() - d.viewPort.y - 240), new Point(e.getX() - d.viewPort.x - 320, e.getY() - d.viewPort.y - 240)));
			}
			else
			{
				Wall w = (Wall) d.currentlyModifying;
				if (!d.shiftModifier)
				{
					w.end.x = e.getX() - d.viewPort.x - 320;
					w.end.y = e.getY() - d.viewPort.y - 240;
				}
				else
				{
					if (Math.abs((e.getX() - d.viewPort.x - 320) - w.start.x) >  Math.abs(e.getY() - d.viewPort.y - 240 - w.start.y))
					{
						w.end.x = e.getX() - d.viewPort.x - 320;
						w.end.y = w.start.y;
					}
					else
					{
						w.end.x = w.start.x;
						w.end.y = e.getY() - d.viewPort.y - 240;
					}
				}
				d.currentlyModifying = null;
				d.creatingWall = false;
			}
		}
	}

	
	
	
	@Override
	public void keyPressed(KeyEvent e) 
	{
		if (e.getKeyCode() == KeyEvent.VK_SHIFT)
		{
			d.shiftModifier = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{
		if (e.getKeyCode() == KeyEvent.VK_SHIFT)
		{
			d.shiftModifier = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) 
	{
	}


}
