package game.vector;

public class Level7 extends Level 
{
	public Level7()
	{
		nextLevel  = Level7.class;
		Vector.d.updateList.clear();
		FileLoader.LoadLevel(Vector.d.updateList, "Level7", this);
	}
}
