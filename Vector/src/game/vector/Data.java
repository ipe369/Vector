package game.vector;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Data extends Canvas implements KeyListener
{
	public int levelNumber = 1;
	Level currentLevel;
	public Point viewPort = new Point(0,0);
	Player player;
	public float zoom = 1;
	private float zooming = 0;
	private JFrame frame;
	private BufferStrategy strat;
	public ArrayList<Updates> updateList = new ArrayList<Updates>();
	
	public Data() 
	{
		frame = new JFrame("Vector");
		frame.setSize(640,480);
		frame.setResizable(false);
		frame.add(this);
		addKeyListener(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		createBufferStrategy(3);
		strat = getBufferStrategy();
		
	}
	
	public synchronized void update()
	{
		zoom += zooming;
		if (zoom < 0.25)
			zoom = 0.25f;
		else if (zoom > 4)
			zoom = 4;
		draw();
	}
	
	protected synchronized void draw()
	{

		Graphics2D g = (Graphics2D) strat.getDrawGraphics();
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 640, 480);
		g.translate(320, 240);
		g.scale(zoom, zoom);
		g.translate(-viewPort.x, -viewPort.y);

		for (int i = 0; i < updateList.size(); i ++)
		{
			//This check is in case the thread restarts the level
			if (updateList.size() > 0)
				updateList.get(i).update(g);
		}

		strat.show();
		g.dispose();
	}
	
	@Override
	public void keyPressed(KeyEvent e) 
	{
		if (e.getKeyCode() == KeyEvent.VK_T)
		{
			zooming = 0.02f;
		}
		else if (e.getKeyCode() == KeyEvent.VK_G)
		{
			zooming = -0.02f;
		}
		
	}
	@Override
	public void keyReleased(KeyEvent e) 
	{
		if (e.getKeyCode() == KeyEvent.VK_G ||
			e.getKeyCode() == KeyEvent.VK_T)
		{
			zooming = 0;
		}
	}
	@Override
	public void keyTyped(KeyEvent e) 
	{
	}

}
