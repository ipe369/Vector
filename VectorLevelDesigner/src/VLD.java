
public class VLD 
{
	public static Data d;
	public VLD() 
	{
		d = new Data();
		go();
	}
	
	public static void main(String[] args)
	{
		new VLD();
	}
	
	private void go()
	{
		while(true)
		{
			try
			{
				Thread.sleep(10);
				d.draw();
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
}
