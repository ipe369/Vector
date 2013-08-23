package game.vector;

import java.awt.Point;

public class Level6 extends Level 
{

	public Level6() 
	{
		super(new Point(132,190));
		Trigger t1;
		Trigger t2;
		Trigger t3;
		Door d1a;
		Door d1b;
		Door d2a;
		Door d2b;
		Door d3a;
		Door d3b;
		
		triggers.add(t1 = new PressurePlate(275, 190,0));
		triggers.add(t2 = new PressurePlate(425, 190,1));
		triggers.add(t3 = new PressurePlate(575, 190,2));
		
		walls.add(new Wall(new Point(100,170), new Point(100,210)));
		walls.add(new Wall(new Point(100,170), new Point(250,170)));
		
		walls.add(d1a = new Door(new Point(250,170), new Point(300,170)));
		walls.add(d1b = new Door(new Point(250,210), new Point(300,210)));
		
		walls.add(new Wall(new Point(300,170), new Point(400,170)));
		
		walls.add(d2a = new Door(new Point(400,170), new Point(450,170)));
		walls.add(d2b = new Door(new Point(400,210), new Point(450,210)));
		
		walls.add(new Wall(new Point(450,170), new Point(550,170)));
		
		walls.add(d3a = new Door(new Point(550,170), new Point(600,170)));
		walls.add(d3b = new Door(new Point(550,210), new Point(600,210)));
		
		walls.add(new Wall(new Point(600,170), new Point(700,170)));
		
		walls.add(new Wall(new Point(700,170), new Point(850,170)));
		walls.add(new Wall(new Point(100,210), new Point(250,210)));
		walls.add(new Wall(new Point(300,210), new Point(400,210)));
		walls.add(new Wall(new Point(450,210), new Point(550,210)));
		walls.add(new Wall(new Point(600,210), new Point(700,210)));
		walls.add(new Wall(new Point(700,210), new Point(850,210)));
		
		walls.add(new Wall(new Point(850,170), new Point(850,210)));
		
		enemies.add(new Fighter(275,150));
		enemies.add(new Fighter(275,230));
		enemies.add(new Fighter(425,150));
		enemies.add(new Fighter(425,230));
		enemies.add(new Fighter(575,150));
		enemies.add(new Fighter(575,230));
		
		
		d1a.trigger = t1;
		d1b.trigger = t1;
		d2a.trigger = t2;
		d2b.trigger = t2;
		d3a.trigger = t3;
		d3b.trigger = t3;
		
		levelEnd = new LevelEnd(827,190);
		
		nextLevel  = Level7.class;
		loadLevel();
	}

}
