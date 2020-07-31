import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
public class five extends Applet{
int x_start,y_start,x_end,y_end;
boolean draw=false;
	public void init()
	{
	this.addMouseListener(new MouseListener()
	{
		
	public void	mousePressed(MouseEvent e){
		x_start=e.getX();
		y_start=e.getY();
		draw=true;
	}
	public void mouseReleased(MouseEvent e){}
	public void mouseClicked(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	});
	this.addMouseMotionListener(new MouseMotionListener(){
	public void mouseMoved(MouseEvent e)
		{}
	public void mouseDragged(MouseEvent e)
		{
			x_end=e.getX();
			y_end=e.getY();
			repaint();
		}		
		});
	}
public void paint(Graphics g)
{ 
	if(draw)
	g.drawLine(x_start,y_start,x_end,y_end);
}
	
}