import java.awt.Color;
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
		}
		if (e.getSource() == d.door)
		{
			d.mode = Mode.ADD_DOOR;
		}
		if (e.getSource() == d.enemy)
		{
			d.mode = Mode.ADD_ENEMY;
		}
		if (e.getSource() == d.trigger)
		{
			d.mode = Mode.ADD_TRIGGER;
		}
		if (e.getSource() == d.player)
		{
			d.mode = Mode.ADD_PLAYER;
		}
		if (e.getSource() == d.exit)
		{
			d.mode = Mode.ADD_LEVEL_END;
		}
		if (e.getSource() == d.pushable)
		{
			d.mode = Mode.ADD_PUSHABLE;
		}
		if (e.getSource() == d.breakableWall)
		{
			d.mode = Mode.ADD_BREAKABLE_WALL;
		}
		if (e.getSource() == d.save)
		{
			new ExportDialogue();
		}
		
	}

	@Override
	public void mouseDragged(MouseEvent e) 
	{
	}

	@Override
	public void mouseMoved(MouseEvent e) 
	{
		Point actualMousePos = d.calculateNewPointWithZoom(new Point(e.getX() + d.viewPort.x - 320, e.getY() + d.viewPort.y - 240));
		Point snappedMousePos = null;
		for (int i = 0; i < d.updateList.size(); i ++)
		{
			if (d.updateList.get(i) instanceof Wall)
			{
				Wall a = (Wall) d.updateList.get(i);
				if (HelperClass.pythagoras(actualMousePos, a.start) <= 8)
				{
					snappedMousePos = a.start;
					break;
				}
				if (HelperClass.pythagoras(actualMousePos, a.end) <= 8)
				{
					snappedMousePos = a.end;
					break;
				}
			}
		}
		
		if (snappedMousePos == null)
		{
			snappedMousePos = actualMousePos;
		}
		if (d.creatingWall)
		{
			Wall w = (Wall) d.currentlyModifying;
			if (!d.shiftModifier)
			{
				w.end = snappedMousePos;
			}
			else
			{
				if (Math.abs(snappedMousePos.x - w.start.x) >  Math.abs(snappedMousePos.y - w.start.y))
				{
					w.end = new Point(snappedMousePos.x,w.start.y);
				}
				else
				{
					w.end = new Point(w.start.x,snappedMousePos.y);
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
		Point actualMousePos = d.calculateNewPointWithZoom(new Point(e.getX() + d.viewPort.x - 320, e.getY() + d.viewPort.y - 240));
		if (e.getButton() == 1)
		{
			if (d.mode == Mode.ADD_WALL ||
				d.mode == Mode.ADD_DOOR ||
				d.mode == Mode.ADD_BREAKABLE_WALL)
			{

				Point snappedMousePos = null;
				for (int i = 0; i < d.updateList.size(); i ++)
				{
					if (d.updateList.get(i) instanceof Wall)
					{
						Wall a = (Wall) d.updateList.get(i);
						if (HelperClass.pythagoras(actualMousePos, a.start) <= 8)
						{
							snappedMousePos = a.start;
							break;
						}
						if (HelperClass.pythagoras(actualMousePos, a.end) <= 8)
						{
							snappedMousePos = a.end;
						}
					}
				}
				
				if (snappedMousePos == null)
				{
					snappedMousePos = actualMousePos;
				}
				
				if (!d.creatingWall)
				{
					d.creatingWall = true;
					System.out.println("Added wall");

					
					if (d.mode == Mode.ADD_DOOR)
					{
						d.updateList.add(d.currentlyModifying = new Door(snappedMousePos,snappedMousePos));
					}
					else if (d.mode == Mode.ADD_WALL)
					{
						d.updateList.add(d.currentlyModifying = new Wall(snappedMousePos,snappedMousePos));
					}
					else
					{
						d.updateList.add(d.currentlyModifying = new DestructableWall(snappedMousePos,snappedMousePos));
					}
				}
				else
				{
					Wall w = (Wall) d.currentlyModifying;
					if (!d.shiftModifier)
					{
						w.end = snappedMousePos;
					}
					else
					{
						if (Math.abs(snappedMousePos.x - w.start.x) >  Math.abs(snappedMousePos.y - w.start.y))
						{
							w.end = new Point(snappedMousePos.x,w.start.y);
						}
						else
						{
							w.end = new Point(w.start.x,snappedMousePos.y);
						}
						
					}
					d.currentlyModifying = null;
					d.creatingWall = false;
				}
			}
			if (d.mode == Mode.ADD_TRIGGER)
			{
				new AddTriggerDialogue(new Point(e.getX() + d.viewPort.x - 320, e.getY() + d.viewPort.y - 240));
			}
			if (d.mode == Mode.ADD_ENEMY)
			{
				new AddEnemyDialogue(new Point(e.getX() + d.viewPort.x - 320, e.getY() + d.viewPort.y - 240));
			}
			if (d.mode == Mode.ADD_PLAYER)
			{
				d.updateList.add(new Player(actualMousePos.x,actualMousePos.y));
			}
			if (d.mode == Mode.ADD_LEVEL_END)
			{
				d.updateList.add(new LevelEnd(actualMousePos.x,actualMousePos.y));
			}
			if (d.mode == Mode.ADD_PUSHABLE)
			{
				d.updateList.add(new Pushable(actualMousePos.x, actualMousePos.y));
			}
			if (d.mode == Mode.NONE)
			{
				for (int i = 0; i < d.updateList.size(); i ++)
				{
					if (d.updateList.get(i) instanceof Wall)
					{
						Wall w = (Wall) d.updateList.get(i);
						//Check if wall is selected
						if (w.doesCollideWithCircle(d.calculateNewPointWithZoom(new Point(e.getX() + d.viewPort.x - 320, e.getY() + d.viewPort.y - 240)), 8))
						{
							if (d.currentlySelected != null)
							{
								if (d.currentlySelected instanceof DestructableWall)
								{
									Wall q = (Wall) d.currentlySelected;
									q.wallColor = new Color(200,50,255);
								}
								else if (d.currentlySelected instanceof Door)
								{
									Wall q = (Wall) d.currentlySelected;
									q.wallColor = Color.WHITE;
								}
								else
								{
									Wall q = (Wall) d.currentlySelected;
									q.wallColor = Color.CYAN;
								}
									
								d.currentlySelected = null;
							}
							d.currentlySelected = w;
							w.wallColor = Color.YELLOW;
							return;
						}
					}
					if (d.updateList.get(i) instanceof Active)
					{
						Active a = (Active) d.updateList.get(i);
						if (HelperClass.pythagoras(new Point(a.x,a.y), actualMousePos) <= a.radius)
						{
							if (d.currentlySelected != null)
							{
								if (d.currentlySelected instanceof DestructableWall)
								{
									Wall q = (Wall) d.currentlySelected;
									q.wallColor = new Color(200,50,255);
								}
								else if (d.currentlySelected instanceof Door)
								{
									Wall q = (Wall) d.currentlySelected;
									q.wallColor = Color.WHITE;
								}
								else
								{
									Wall q = (Wall) d.currentlySelected;
									q.wallColor = Color.CYAN;
								}
									
								d.currentlySelected = null;
							}
							d.currentlySelected = a;
							return;
						}
					}
					if (d.updateList.get(i) instanceof Trigger)
					{
						Trigger a = (Trigger) d.updateList.get(i);
						if (HelperClass.pythagoras(new Point(a.x,a.y), actualMousePos) <= a.radius)
						{
							if (d.currentlySelected != null)
							{
								if (d.currentlySelected instanceof DestructableWall)
								{
									Wall q = (Wall) d.currentlySelected;
									q.wallColor = new Color(200,50,255);
								}
								else if (d.currentlySelected instanceof Door)
								{
									Wall q = (Wall) d.currentlySelected;
									q.wallColor = Color.WHITE;
								}
								else
								{
									Wall q = (Wall) d.currentlySelected;
									q.wallColor = Color.CYAN;
								}
									
								d.currentlySelected = null;
							}
							d.currentlySelected = a;
							return;
						}
					}
				}
				if (d.currentlySelected != null)
				{
					if (d.currentlySelected instanceof DestructableWall)
					{
						Wall q = (Wall) d.currentlySelected;
						q.wallColor = new Color(200,50,255);
					}
					else if (d.currentlySelected instanceof Door)
					{
						Wall q = (Wall) d.currentlySelected;
						q.wallColor = Color.WHITE;
					}
					else
					{
						Wall q = (Wall) d.currentlySelected;
						q.wallColor = Color.CYAN;
					}
						
					d.currentlySelected = null;
				}
			}
		}
		else if (e.getButton() == MouseEvent.BUTTON3)
		{
			if (d.currentlySelected instanceof Door)
			{
				Door w = (Door) d.currentlySelected;
				for (int i = 0; i < d.updateList.size(); i ++)
				{
					if (d.updateList.get(i) instanceof Trigger)
					{
						Trigger a = (Trigger) d.updateList.get(i);
						if (HelperClass.pythagoras(new Point(a.x,a.y), actualMousePos) <= a.radius)
						{
							w.triggerID = a.ID;
						}
					}
				}
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
		else if (e.getKeyCode() == KeyEvent.VK_G)
		{
			d.zooming = -0.03f;
		}
		else if (e.getKeyCode() == KeyEvent.VK_T)
		{
			d.zooming = 0.03f;
		}
		else if (e.getKeyCode() == KeyEvent.VK_W)
		{
			d.cameraYSpeed = -1;
		}
		else if (e.getKeyCode() == KeyEvent.VK_A)
		{
			d.cameraXSpeed = -1;
		}
		else if (e.getKeyCode() == KeyEvent.VK_S)
		{
			d.cameraYSpeed = 1;
		}
		else if (e.getKeyCode() == KeyEvent.VK_D)
		{
			d.cameraXSpeed = 1;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{
		if (e.getKeyCode() == KeyEvent.VK_T ||
			e.getKeyCode() == KeyEvent.VK_G)
		{
			d.zooming = 0;
		}
		if (e.getKeyCode() == KeyEvent.VK_SHIFT)
		{
			d.shiftModifier = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
		{
			d.mode = Mode.NONE;
		}
		if (e.getKeyCode() == KeyEvent.VK_DELETE)
		{
			if (d.currentlySelected != null)
			{
				d.updateList.remove(d.currentlySelected);
				d.currentlySelected = null;
			}
		}
		else if (e.getKeyCode() == KeyEvent.VK_W ||
				 e.getKeyCode() == KeyEvent.VK_S)
		{
			d.cameraYSpeed = 0;
		}
		else if (e.getKeyCode() == KeyEvent.VK_A ||
				 e.getKeyCode() == KeyEvent.VK_D)
		{
			d.cameraXSpeed = 0;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) 
	{
	}


}
