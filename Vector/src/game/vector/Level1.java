package game.vector;

import java.awt.Point;

public class Level1 extends Level 
{
	public Level1() 
	{
		Vector.d.currentLevel = this;
		FileLoader.LoadLevel(Vector.d.updateList, "Level1.txt");
		nextLevel = Level2.class;
	}

}
