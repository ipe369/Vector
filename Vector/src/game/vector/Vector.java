package game.vector;

public class Vector 
{
	static Vector v;
	public static int frameDelayInMillis = 10;
	public static Data d;
	public Vector() 
	{
		v = this;
		d = new Data();

		start();
	}
	public static void main(String[] args) 
	{
		new Vector();
	}
	
	public void start()
	{
		Vector.d.updateList.clear();
		d.currentLevel = new Level1();

		while(true)
		{
			try
			{
				Thread.sleep(frameDelayInMillis);
				d.update();
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}

}
