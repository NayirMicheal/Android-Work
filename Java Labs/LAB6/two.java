import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
public class two extends Applet{
 int x=50,y=200;

	public void init()
	{
		
		this.addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent e)
			{
				switch(e.getKeyCode())
				{
					case KeyEvent.VK_UP :
						y--;
						break;
					case KeyEvent.VK_DOWN :
						y++;
						break;
					case KeyEvent.VK_RIGHT :
						x++;
						break;
					case KeyEvent.VK_LEFT :
						x--;
						break;
				}
				repaint();
			}
			public void keyReleased(KeyEvent e)
			{
					
			}
			public void keyTyped(KeyEvent e)
			{
					
			}
		});
	}
public void paint(Graphics g)
{ 
	g.drawString("JAVA", x, y);
}
	
}