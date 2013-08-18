package game.vector;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FileLoader 
{
	public static void LoadLevel(ArrayList<Updates> l, String path)
	{
		Trigger[] t = new Trigger[9999];
		try
		{
			BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path))));
			String current;
			String[] infos;
			while((current = r.readLine()) != null)
			{
				infos = current.split("/");
				if (infos[0].compareTo("Wall") == 0)
				{
					String[] startString = infos[1].split(",");
					String[] endString = infos[2].split(",");
					Point start = new Point(Integer.parseInt(startString[0]),Integer.parseInt(startString[1]));
					Point end = new Point(Integer.parseInt(endString[0]),Integer.parseInt(endString[1]));
					l.add(new Wall(start,end));
					continue;
				}
				else if (infos[0].compareTo("DestructableWall") == 0)
				{
					String[] startString = infos[1].split(",");
					String[] endString = infos[2].split(",");
					Point start = new Point(Integer.parseInt(startString[0]),Integer.parseInt(startString[1]));
					Point end = new Point(Integer.parseInt(endString[0]),Integer.parseInt(endString[1]));
					l.add(new DestructableWall(start,end));
					continue;
				}
				else if (infos[0].compareTo("Door") == 0)
				{
					String[] startString = infos[1].split(",");
					String[] endString = infos[2].split(",");
					Point start = new Point(Integer.parseInt(startString[0]),Integer.parseInt(startString[1]));
					Point end = new Point(Integer.parseInt(endString[0]),Integer.parseInt(endString[1]));
					Door d;
					l.add(d = new Door(start,end));
					d.triggerID = Integer.parseInt(infos[3]);
					continue;
				}
				else if (infos[0].compareTo("Switch") == 0)
				{
					String[] positionString = infos[1].split(",");
					Point position = new Point(Integer.parseInt(positionString[0]),Integer.parseInt(positionString[1]));
					Switch s;
					l.add(s = new Switch(position.x,position.y, Integer.parseInt(infos[2])));
					t[Integer.parseInt(infos[2])] = s;
					continue;
				}
				else if (infos[0].compareTo("PressurePlate") == 0)
				{
					String[] positionString = infos[1].split(",");
					Point position = new Point(Integer.parseInt(positionString[0]),Integer.parseInt(positionString[1]));
					PressurePlate s;
					l.add(s = new PressurePlate(position.x,position.y, Integer.parseInt(infos[2])));
					t[Integer.parseInt(infos[2])] = s;
					continue;
				}
				else if (infos[0].compareTo("Player") == 0)
				{
					String[] positionString = infos[1].split(",");
					Point position = new Point(Integer.parseInt(positionString[0]),Integer.parseInt(positionString[1]));
					Player p;
					l.add(p = new Player(position.x,position.y));
					Vector.d.addKeyListener(p);
					Vector.d.currentLevel.player = p;
					continue;
				}
				else if (infos[0].compareTo("LevelEnd") == 0)
				{
					String[] positionString = infos[1].split(",");
					Point position = new Point(Integer.parseInt(positionString[0]),Integer.parseInt(positionString[1]));
					l.add(new LevelEnd(position.x,position.y));
					continue;
				}
				else if (infos[0].compareTo("Pushable") == 0)
				{
					String[] positionString = infos[1].split(",");
					Point position = new Point(Integer.parseInt(positionString[0]),Integer.parseInt(positionString[1]));
					l.add(new Pushable(position.x,position.y));
					continue;
				}
				else if (infos[0].compareTo("Fighter") == 0)
				{
					String[] positionString = infos[1].split(",");
					Point position = new Point(Integer.parseInt(positionString[0]),Integer.parseInt(positionString[1]));
					l.add(new Fighter(position.x,position.y));
					continue;
				}
				else if (infos[0].compareTo("BurstTurret") == 0)
				{
					String[] positionString = infos[1].split(",");
					Point position = new Point(Integer.parseInt(positionString[0]),Integer.parseInt(positionString[1]));
					l.add(new BurstTurret(position.x,position.y));
					continue;
				}
			}
			for (int i = 0; i < l.size(); i ++)
			{
				if (l.get(i) instanceof Door)
				{
					Door d = (Door) l.get(i);
					d.trigger = t[d.triggerID];
				}
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
