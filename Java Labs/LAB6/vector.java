import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
public class vector extends Applet{
int x1,y1,x2,y2;
Vector<LineVec> v; 
boolean reachDrag=false;
boolean draw=false;
	public void init()
	{
		v=new Vector(10,1);
	this.addMouseListener(new MouseListener()
		{
		
	public void	mousePressed(MouseEvent e){
		x1=e.getX();
		y1=e.getY();
		draw=true;
		
	}
	public void mouseReleased(MouseEvent e){
		if(reachDrag){
		LineVec L=new LineVec(x1,y1,x2,y2);
		v.add(L);
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
	for(int i=0;i<v.capacity();i++){
		try{
		g.drawLine(v.elementAt(i).getX1(),v.elementAt(i).getY1(),v.elementAt(i).getX2(),v.elementAt(i).getY2());
			}
		catch(ArrayIndexOutOfBoundsException e)
		{
			
		}		
	}
}
	
}
class LineVec{
   int x1,y1,x2,y2;
	LineVec(int xx1,int yy1,int xx2,int yy2)
	{
		x1=xx1;
		y1=yy1;
		x2=xx2;
		y2=yy2;
	}
	public int getX1()
	{
		return x1;
	}
	public int getY1()
	{
		return y1;
	}
	public int getX2()
	{
		return x2;
	}
	public int getY2()
	{
		return y2;
	}
}