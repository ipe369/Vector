import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public class Data extends Canvas
{
	boolean shiftModifier = false;
	
	boolean creatingWall;
	Updates currentlyModifying;
	
	public ArrayList<Updates> updateList = new ArrayList<Updates>();
	public Point viewPort = new Point(0,0);
	public float zoom = 1;
	public Mode mode = Mode.NONE;
	
	JFrame frame = new JFrame("Vectorify");
	
	JMenuBar menuBar = new JMenuBar();
	
	JMenu file = new JMenu("File");
	JMenuItem newLevel = new JMenuItem("New");
	JMenuItem open = new JMenuItem("Open");
	JMenuItem save = new JMenuItem("Save");
	
	JMenu add = new JMenu("Add");
	JMenuItem wall = new JMenuItem("Add Wall");
	JMenuItem enemy = new JMenuItem("Add Enemy");
	JMenuItem exit = new JMenuItem("Add Level End");
	
	BufferStrategy strat;
	public Data()
	{
		Listener l = new Listener(this);
		addMouseListener(l);
		addMouseMotionListener(l);
		addKeyListener(l);
		wall.addActionListener(l);
		
		file.add(newLevel);
		file.add(open);
		file.add(save);
		
		add.add(wall);
		add.add(enemy);
		add.add(exit);
		
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
		
		strat.show();
		g.dispose();
	}



}
