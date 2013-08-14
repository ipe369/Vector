package game.vector;

import java.io.IOException;

public class TimedLevelReset implements Runnable
{
	public int timeInMillis;
	public TimedLevelReset(int _timeInMillis) 
	{
		timeInMillis = _timeInMillis;
		new Thread(this).start();
	}
	@Override
	public void run() 
	{
		while(timeInMillis > 0)
		{
			try
			{
				Thread.sleep(Vector.frameDelayInMillis);
				timeInMillis -= Vector.frameDelayInMillis;
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		
		try 
		{
			Vector.d.removeKeyListener(Vector.d.currentLevel.player);
			Vector.d.currentLevel.player = null;
			Vector.d.currentLevel = Vector.d.currentLevel.getClass().newInstance();
		} 
		catch (InstantiationException | IllegalAccessException e) 
		{
			e.printStackTrace();
		}
	}

}
