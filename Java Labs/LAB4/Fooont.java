import java.applet.Applet;
import java.awt.*;
public class Fooont extends Applet{
public void paint(Graphics g){
	int i=20;
String[] Fonts =GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
for(String f:Fonts)
{
	Font font=new Font(f,Font.BOLD,20);
	g.setFont(font);
	g.drawString(f, 10, i);
	i+=20;
}
}
}