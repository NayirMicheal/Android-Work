import java.applet.Applet;
import java.awt.*;
public class Foont extends Applet{
public void paint(Graphics g){
	int i=20;
	int j=20;
String[] Fonts =Toolkit.getDefaultToolkit().getFontList();
for(String f:Fonts)
{
	Font font=new Font(f,Font.BOLD,12);
	g.setFont(font);
	g.drawString(f, j, i);
	i+=20;
}
}
}