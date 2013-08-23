import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public class Data extends Canvas
{
	public boolean dragging = false;
	
	private static final long serialVersionUID = 1L;

	public static int currentTriggerID = 0;
	
	public int cameraXSpeed;
	public int cameraYSpeed;
	
	boolean shiftModifier = false;
	
	boolean creatingWall;
	Updates currentlyModifying;
	Updates currentlySelected;
	
	public ArrayList<Updates> updateList = new ArrayList<Updates>();
	public Point viewPort = new Point(0,0);
	public float zoom = 1.01f;
	public float zooming = 0;
	public Mode mode = Mode.NONE;
	
	JFrame frame = new JFrame("Vectorify");
	
	JMenuBar menuBar = new JMenuBar();
	
	JMenu file = new JMenu("File");
	JMenuItem newLevel = new JMenuItem("New");
	JMenuItem open = new JMenuItem("Open");
	JMenuItem save = new JMenuItem("Save");
	
	JMenu add = new JMenu("Add");
	JMenuItem player = new JMenuItem("Add Player");
	JMenuItem wall = new JMenuItem("Add Wall");
	JMenuItem enemy = new JMenuItem("Add Enemy");
	JMenuItem exit = new JMenuItem("Add Level End");
	JMenuItem door = new JMenuItem("Add Door");
	JMenuItem breakableWall = new JMenuItem("Add Breakable Wall");
	JMenuItem trigger = new JMenuItem("Add Trigger");
	JMenuItem pushable = new JMenuItem("Add Pushable");
	
	BufferStrategy strat;
	public Data()
	{

		Listener l = new Listener(this);
		addMouseListener(l);
		addMouseMotionListener(l);
		addKeyListener(l);
		wall.addActionListener(l);
		enemy.addActionListener(l);
		door.addActionListener(l);
		player.addActionListener(l);
		breakableWall.addActionListener(l);
		exit.addActionListener(l);
		trigger.addActionListener(l);
		pushable.addActionListener(l);
		save.addActionListener(l);
		
		file.add(newLevel);
		file.add(open);
		file.add(save);
		
		add.add(player);
		add.add(wall);
		add.add(enemy);
		add.add(exit);
		add.add(door);
		add.add(breakableWall);
		add.add(trigger);
		add.add(pushable);
		
		menuBar.add(file);
		menuBar.add(add);
		
		frame.setResizable(false);
		frame.setSize(640,480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.getContentPane().add(menuBar,BorderLayout.NORTH);
		frame.add(this);
		
		createBufferStrategy(3);
		strat = getBufferStrategy();
	}
	
	public void draw()
	{
		viewPort.x += cameraXSpeed;
		viewPort.y += cameraYSpeed;
		zoom += zooming;
		Graphics2D g = (Graphics2D) strat.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 640, 480);
		g.translate(320, 240);
		g.scale(zoom, zoom);
		g.translate(-viewPort.x, -viewPort.y);
		
		for (int i = 0; i < updateList.size(); i ++)
		{
			updateList.get(i).update(g);
		}
		if (currentlySelected != null)
		{
			if (currentlySelected instanceof Active)
			{
				Active a = (Active) currentlySelected;
				g.setColor(new Color(200,200,0,150));
				g.fillOval((int) (a.x - a.radius*zoom), (int) (a.y - a.radius*zoom), (int) (a.radius*zoom*2), (int) (a.radius*zoom*2));
			}
			else if (currentlySelected instanceof Trigger)
			{
				Trigger a = (Trigger) currentlySelected;
				g.setColor(new Color(200,200,0,150));
				g.fillOval((int) (a.x - a.radius*zoom), (int) (a.y - a.radius*zoom), (int) (a.radius*zoom*2), (int) (a.radius*zoom*2));
			}
		}
		
		strat.show();
		g.dispose();
	}
	
	
	public Point calculateNewPointWithZoom(Point p)
	{
		//p2 and centreScreen are created so that modifying them does not modify the original p object.
		Point p2 = new Point(p.x,p.y);
		Point centreScreen = new Point(viewPort.x,viewPort.y);
		int xDisFromCentre = p.x - centreScreen.x;
		int yDisFromCentre = p.y - centreScreen.y;
		int newXDis = (int) (xDisFromCentre / zoom);
		int newYDis = (int) (yDisFromCentre / zoom);
		p2.x = centreScreen.x + newXDis;
		p2.y = centreScreen.y + newYDis;
		return p2;
	}
	public Point reverseCalculateNewPointWithZoom(Point p)
	{
		//p2 and centreScreen are created so that modifying them does not modify the original p object.
		Point p2 = new Point(p.x,p.y);
		Point centreScreen = new Point(viewPort.x,viewPort.y);
		int xDisFromCentre = p.x - centreScreen.x;
		int yDisFromCentre = p.y - centreScreen.y;
		int newXDis = 0;
		int newYDis = 0;
		if (xDisFromCentre > 0)
		{
			newXDis = (int) (xDisFromCentre * zoom + 1);
		}
		else
		{
			newXDis = (int) (xDisFromCentre * zoom - 1);
		}
		if (yDisFromCentre > 0)
		{
			newYDis = (int) (yDisFromCentre * zoom + 1);
		}
		else
		{
			newYDis = (int) (yDisFromCentre * zoom - 1);
		}
		p2.x = centreScreen.x + newXDis;
		p2.y = centreScreen.y + newYDis;
		return p2;
	}


}
