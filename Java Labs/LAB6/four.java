import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
public class four extends Applet{
	int x=50,y=200;
	public void init()
	{
		this.addMouseMotionListener(new MouseMotionListener(){
		public void mouseMoved(MouseEvent e)
		{}
		public void mouseDragged(MouseEvent e)
		{
			x=e.getX()-25;
			y=e.getY()-25;
			repaint();
		}		
		});
	}
public void paint(Graphics g)
{ 
	g.setColor(Color.RED);
	g.fillOval(x,y,50,50);
}
	
}