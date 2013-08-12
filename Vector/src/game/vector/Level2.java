package game.vector;

import java.awt.Point;

public class Level2 extends Level 
{
	public Level2() 
	{
		super(new Point(128,180));
		walls.add(new Wall(new Point(64,64), new Point(64,300)));
		walls.add(new Wall(new Point(64,300), new Point(192,300)));
		walls.add(new Wall(new Point(192,300), new Point(192,360)));
		walls.add(new Wall(new Point(192,360), new Point(448,360)));
		walls.add(new Wall(new Point(448,360), new Point(448,300)));
		walls.add(new Wall(new Point(448,300), new Point(576, 300)));
		walls.add(new Wall(new Point(576,300), new Point(576,64)));
		walls.add(new Wall(new Point(576,64), new Point(448,64)));
		walls.add(new Wall(new Point(448,64), new Point(448,180)));
		walls.add(new Wall(new Point(448,180), new Point(192,180)));
		walls.add(new Wall(new Point(192,180), new Point(192,64)));
		walls.add(new Wall(new Point(192,64), new Point(64,64)));
		enemies.add(new Fighter(512, 140));
		levelEnd = new LevelEnd(512,240);
		
		nextLevel  = Level2.class;
		loadLevel();
	}
}
