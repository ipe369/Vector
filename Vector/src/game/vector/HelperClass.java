package game.vector;

import java.awt.Point;

public abstract class HelperClass 
{
	public static int pythagoras(Point p1, Point p2)
	{
		return (int)  Math.sqrt(((p1.x - p2.x)*(p1.x - p2.x)) + ((p1.y - p2.y)*(p1.y - p2.y)));
	}
}
