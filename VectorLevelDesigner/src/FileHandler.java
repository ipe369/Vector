import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class FileHandler 
{
	public static Data d = VLD.d;
	
	public static void export(String name)
	{
		try 
		{
			System.out.println(name);
			PrintWriter w = new PrintWriter(new FileWriter(name));
			for (int i = 0; i < d.updateList.size(); i ++)
			{
				if (d.updateList.get(i) instanceof Wall)
				{
					Wall x = (Wall) d.updateList.get(i);
					if (x instanceof Door)
					{
						Door d = (Door) x;
						w.println("Door/" + x.start.x + "," + x.start.y + "/" +  + x.end.x + "," + x.end.y + "/" + d.triggerID);
					}
					else if (x instanceof DestructableWall)
					{
						w.println("DestructableWall/" + x.start.x + "," + x.start.y + "/" +  + x.end.x + "," + x.end.y);
					}
					else
					{
						w.println("Wall/" + x.start.x + "," + x.start.y + "/" +  + x.end.x + "," + x.end.y);
					}
				}
				else if (d.updateList.get(i) instanceof Player)
				{
					Player p = (Player) d.updateList.get(i);
					w.println("Player/" + p.x + ","+ p.y);
				}
				else if (d.updateList.get(i) instanceof Enemy)
				{
					Enemy e = (Enemy) d.updateList.get(i);
					if (e instanceof Fighter)
					{
						w.println("Fighter/" + e.x + "," + e.y);
					}
					else if (e instanceof BurstTurret)
					{
						w.println("BurstTurret/" + e.x + "," + e.y + "/" + ((BurstTurret) e).offsetTiming);
					}
				}
				else if (d.updateList.get(i) instanceof Trigger)
				{
					Trigger t = (Trigger) d.updateList.get(i);
					if (t instanceof Switch)
					{
						w.println("Switch/" + t.x + "," + t.y + "/" + t.ID);
					}
					else if (t instanceof PressurePlate)
					{
						w.println("PressurePlate/" + t.x + "," + t.y + "/" + t.ID);
					}
				}
				else if (d.updateList.get(i) instanceof LevelEnd)
				{
					LevelEnd l = (LevelEnd) d.updateList.get(i);
					w.println("LevelEnd/" + l.x + "," + l.y);
				}
				else if (d.updateList.get(i) instanceof Pushable)
				{
					Pushable p = (Pushable) d.updateList.get(i);
					w.println("Pushable/" + p.x + "," + p.y);
				}
			}
			w.flush();
			w.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}
