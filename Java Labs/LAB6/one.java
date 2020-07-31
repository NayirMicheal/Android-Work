import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
public class one extends Applet{
int count;
Button b1,b2;
	public void init()
	{
	b1 = new Button("Increase");
	b2 =new Button ("Decrease");
		b1.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ev)
			{
			count++ ;
			repaint() ;
			}
		});
	b2.addActionListener(new MyButtonListener());
	add(b1);
	add(b2);
	}
public void paint(Graphics g)
{ 
	g.drawString("Click Count is:" + count, 50, 200);
}
class MyButtonListener implements ActionListener
	{
	public void actionPerformed(ActionEvent ev)
		{
	count-- ;
	repaint() ;
		}
	}	
}