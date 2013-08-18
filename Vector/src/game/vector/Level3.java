package game.vector;

import java.awt.Point;

public class Level3 extends Level 
{

	public Level3() 
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
		
		triggers.add(t = new Switch(220,182,0));
		
		messages.add(new TutorialMessage("Use E to trigger switches.",256,197, 85, Vector.d.getGraphics()));
		messages.add(new TutorialMessage("Switches can trigger lots of different things!",256,197, 240, Vector.d.getGraphics()));
		
		levelEnd = new LevelEnd(512,182);
		
		d.trigger = t;
		
		nextLevel  = Level4.class;
		loadLevel();
	}

}
