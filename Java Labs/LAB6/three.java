import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
public class three extends Applet implements Runnable{
	Thread th;
	volatile int x;
	volatile int y;
	boolean goRightBall1=true;
	boolean goDownBall1=true;
	Button start,pause;
	boolean firsttime=true;
	public void init()
	{
		th= new Thread(this);
		x=getWidth()/2;
		y=getHeight()/2;
		start = new Button("start");
		pause =new Button ("pause");
		start.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ev)
			{
			if(firsttime)
			{
				th.start();	
				firsttime=false;	
			}
			else
			{
				th.resume();
			}
			}
		});
		pause.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ev)
			{
			th.suspend();
			}
		});
		add(start);
		add(pause);
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