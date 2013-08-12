package game.vector;

import java.awt.Point;

public class Level4 extends Level
{

	public Level4() 
	{
		super(new Point(132,185));
		walls.add(new Wall(new Point(64,64), new Point(64,300)));
		walls.add(new Wall(new Point(64,300), new Point(192,300)));
		walls.add(new Wall(new Point(192,300), new Point(192,220)));
		walls.add(new Wall(new Point(192,220), new Point(448,220)));
		walls.add(new Wall(new Point(448,220), new Point(448,300)));
		walls.add(new Wall(new Point(448,300), new Point(576, 300)));
		walls.add(new Wall(new Point(576,300), new Point(576,64)));
		walls.add(new Wall(new Point(576,64), new Point(448,64)));
		walls.add(new Wall(new Point(448,64), new Point(448,144)));
		walls.add(new Wall(new Point(448,144), new Point(192,144)));
		walls.add(new Wall(new Point(192,144), new Point(192,64)));
		walls.add(new Wall(new Point(192,64), new Point(64,64)));
		walls.add(new DestructableWall(new Point(256,220), new Point(256,144)));
		enemies.add(new Fighter(320,182));
		messages.add(new TutorialMessage("Careful, this guy is dangerous!",256,197, 85, Vector.d.getGraphics()));
		messages.add(new TutorialMessage("He can't see you yet though. Try and shoot him as quickly as possible!",256,197, 240, Vector.d.getGraphics()));
		levelEnd = new LevelEnd(512,182);
		
		nextLevel  = Level4.class;
		loadLevel();
	}

}
