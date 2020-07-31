import java.applet.Applet;
import java.awt.*;
import java.util.Date;
public class Ball extends Applet implements Runnable{
	Thread th;
	volatile int x;
	volatile int y;
	boolean goRightBall1=true;
	boolean goDownBall1=true;
	public void init()
	{
		x=getWidth()/2;
		y=getHeight()/2;
		th= new Thread(this);
		th.start();
	}
	public void paint(Graphics g)
{ 		
	g.setColor(Color.RED);
	g.fillOval(x,y,50,50);
}
public void run()
{
	while(true)
	{	
		if(goRightBall1)
		{
			if((x+50)<getWidth())
				x++;
			else
			goRightBall1=false;	
		}
		else
		{
			if(x>0)
				x--;
			else
			goRightBall1=true;		
		}
	
	if(goDownBall1)
		{
			if((y+50)<getHeight())
				y++;
			else
			goDownBall1=false;	
		}
		else
		{
			if(y>0)
				y--;
			else
			goDownBall1=true;		
		}
		
		repaint();
		try{
		th.sleep(10);
		}
			catch(InterruptedException ex)
	{
		ex.printStackTrace();
	}
	}
}
}