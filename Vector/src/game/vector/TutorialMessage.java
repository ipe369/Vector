package game.vector;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.ArrayList;

public class TutorialMessage implements Updates 
{
	public String message;
	public int width;
	private ArrayList<String> lines = new ArrayList<String>();
	public Font f;
	public int x;
	public int y;
	public Color color = Color.WHITE;
	
	public TutorialMessage(String _message, int _width, int _x, int _y, Graphics g) 
	{
		x = _x;
		y = _y;
		message = _message;
		width = _width;
		try
		{
	        Font fontRaw = Font.createFont(Font.TRUETYPE_FONT,getClass().getResource("tempesta.ttf").openStream());
	        Font fontBase = fontRaw.deriveFont(14f);
	        f = new TempestaFont(fontBase);
		}
		catch (IOException | FontFormatException e)
		{
			e.printStackTrace();
		}
		calculateWordWrap(g);
	}
	
	protected void calculateWordWrap(Graphics g)
	{
		String[] words = message.split(" ");
		String currentLine = new String();
		for (int i = 0; i < words.length; i ++)
		{
			if (g.getFontMetrics(f).stringWidth(words[i]) + g.getFontMetrics(f).stringWidth(currentLine) < width)
			{
				currentLine += words[i] + " ";
			}
			else
			{
				lines.add(currentLine);
				currentLine = words[i] + " ";
			}
		}
		lines.add(currentLine);
	}

	@Override
	public void update(Graphics2D g) 
	{
		g.setColor(color);
		g.setFont(f);
		for (int i = 0; i < lines.size(); i ++)
		{
			g.drawString(lines.get(i), x, y + (g.getFontMetrics(f).getHeight()+3)*i);
		}
	}

}
