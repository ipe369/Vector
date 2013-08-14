package game.vector;

import java.awt.Point;

public class Level5 extends Level 
{

	public Level5() 
	{
		super(new Point(132,185));
		
		Door d;
		Trigger t;
		
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
		walls.add(d = new Door(new Point(256,220), new Point(256,144)));
		pushables.add(new Pushable(150,100));
		triggers.add(t = new PressurePlate(160,182));
		
		messages.add(new TutorialMessage("You're on your own from now on!",256,197, 85, Vector.d.getGraphics()));
		messages.add(new TutorialMessage("You can press R to reset the level!",256,197, 240, Vector.d.getGraphics()));
		
		levelEnd = new LevelEnd(512,182);
		
		d.trigger = t;
		
		nextLevel  = Level6.class;
		loadLevel();
	}

}
