import java.applet.Applet;
import java.awt.*;
import java.util.Date;
public class Textt extends Applet implements Runnable{
	Thread th;
	volatile int x=1;

	public void init()
	{
		th= new Thread(this);
		th.start();
	}
public void paint(Graphics g)
{ 
	Font f=new Font(g.getFont().getFontName(),Font.BOLD,20);
	g.setFont(f);
	g.setColor(Color.RED);
	g.drawString("Hello Java",x,getHeight()/2);

}
public void run()
{
	while(true)
	{
		if(x<getWidth())
			x+=5;
		else 
			x=1;
		repaint();
		try{
		th.sleep(50);
		}
			catch(InterruptedException ex)
	{
		ex.printStackTrace();
	}
	}
}
}