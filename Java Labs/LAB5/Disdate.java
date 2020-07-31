import java.applet.Applet;
import java.awt.*;
import java.util.Date;
public class Disdate extends Applet implements Runnable{
	Thread th;
	public void init()
	{
		th= new Thread(this);
		th.start();
	}
public void paint(Graphics g)
{ 
	Date d=new Date();
	g.drawString(d.toString(),getWidth()/2,getHeight()/2);

}
public void run()
{while(true){
	try{
	repaint();
	th.sleep(1000);	
	}
	catch(InterruptedException ex)
	{
		ex.printStackTrace();
	}
	}
	
}
}