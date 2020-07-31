import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
public class six extends Applet{
int [] x_start=new int[10];
int [] y_start=new int[10];
int [] x_end=new int[10];
int [] y_end =new int[10];
int x1,y1,x2,y2;
int index=0;
boolean reachDrag=false;
boolean draw=false;
	public void init()
	{
	this.addMouseListener(new MouseListener()
		{
		
	public void	mousePressed(MouseEvent e){
		x1=e.getX();
		y1=e.getY();
		draw=true;
		
	}
	public void mouseReleased(MouseEvent e){
		if(reachDrag){
		x_start[index]=x1;
		y_start[index]=y1;
		x_end[index]=x2;
		y_end[index]=y2;
		if(index<10)
		index++;
		reachDrag=false;
		}
		}
	public void mouseClicked(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	});
	this.addMouseMotionListener(new MouseMotionListener(){
	public void mouseMoved(MouseEvent e)
		{}
	public void mouseDragged(MouseEvent e)
		{
			x2=e.getX();
			y2=e.getY();
			repaint();
			reachDrag=true;
			
		}		
		});
	}
public void paint(Graphics g)
{ 	
if(draw)
	g.drawLine(x1,y1,x2,y2);
	if(index==10)
		index--; 
	for(int i=0;i<index;i++)
		g.drawLine(x_start[i],y_start[i],x_end[i],y_end[i]);
	
}
	
}