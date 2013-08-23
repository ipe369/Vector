package game.vector;

import java.awt.Point;

public class Level1 extends Level 
{
	public Level1() 
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
		messages.add(new TutorialMessage("Hey! Use the WASD keys to move around, and the G and T keys to zoom the camera.",256,197, 85, Vector.d.getGraphics()));
		messages.add(new TutorialMessage("Reach the green pulsing circle to advance to the next level!",256,197, 240, Vector.d.getGraphics()));
		levelEnd = new LevelEnd(512,182);

		nextLevel = Level2.class;
		loadLevel();
	}

}
