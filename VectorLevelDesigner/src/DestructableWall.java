

import java.awt.Color;
import java.awt.Point;

public class DestructableWall extends Wall implements Updates 
{
	int health = 6;
	public DestructableWall(Point _start, Point _end) 
	{
		super(_start, _end);
		wallColor = new Color(200,50,255);
	}
}
