import java.applet.Applet;
import java.awt.*;
import java.net.URL;
public class Dimage extends Applet{
public void paint(Graphics g){
URL url=getCodeBase();
Image img=getImage(url,"pic.jpg");
g.drawImage(img,0,0,getWidth(),getHeight(),this);
}
}