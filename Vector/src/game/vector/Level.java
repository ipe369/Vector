package game.vector;

import java.awt.Point;
import java.util.ArrayList;

public class Level 
{
	public ArrayList<Wall> walls = new ArrayList<Wall>();
	public ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	public ArrayList<Trigger> triggers = new ArrayList<Trigger>();
	public ArrayList<TutorialMessage> messages = new ArrayList<TutorialMessage>();
	public ArrayList<Pushable> pushables = new ArrayList<Pushable>();
	public Player player = null;
	LevelEnd levelEnd;
	Class<? extends Level> nextLevel;
	
	public Level(Point playerPos) 
	{
		player = new Player(playerPos.x, playerPos.y);
	}
	public Level() 
	{
	}
	
	public final boolean isLevelEmptyOfEnemies()
	{
		return enemies.isEmpty();
	}

	public void loadLevel()
	{
		if (Vector.d.updateList != null && !Vector.d.updateList.isEmpty())
		{
			Vector.d.updateList.clear();
		}
		if (player != null)
		{
			Vector.d.addKeyListener(player);

			for (int i = 0; i < walls.size(); i ++)
			{
				Vector.d.updateList.add(walls.get(i));
			}
			for (int i = 0; i < triggers.size(); i ++)
			{
				Vector.d.updateList.add(triggers.get(i));
			}
			for (int i = 0; i < enemies.size(); i ++)
			{
				Vector.d.updateList.add(enemies.get(i));
			}
			for (int i = 0; i < messages.size(); i ++)
			{
				Vector.d.updateList.add(messages.get(i));
			}
			for (int i = 0; i < pushables.size(); i ++)
			{
				Vector.d.updateList.add(pushables.get(i));
			}

			Vector.d.updateList.add(player);
		}
		else
		{
			System.out.println("Please make sure each level contains a player.");
			return;
		}
		if (levelEnd != null)
		{
			Vector.d.updateList.add(levelEnd);
		}
		else
		{
			System.out.println("Please make sure each level contains an exit.");
		}
	}
}
