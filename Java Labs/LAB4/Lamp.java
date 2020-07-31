import java.applet.Applet;
import java.awt.*;
public class Lamp extends Applet{
public void paint(Graphics g){  //reference for child of graphics.
	Color yellow=new Color(Color.YELLOW.getRed(),Color.YELLOW.getGreen(),Color.YELLOW.getBlue());
	Color black=new Color(Color.BLACK.getRed(),Color.BLACK.getGreen(),Color.BLACK.getBlue());
	g.setColor(yellow);
	int Width=getWidth();
	int Height=getHeight();
	int firstOvalY=2;
	int firstOvalWidth=200;
	int firstOvalHeight=40;
	int firstLineX=(Width/2)-firstOvalWidth/2;
	int secondLineX=(Width/2)-(firstOvalWidth/2)+firstOvalWidth;
	int EndOfLine1X=(Width/2)-(firstOvalWidth/2)-20;
	int EndOfLine1Y=150;
	int EndOfLine2X=(Width/2)+(firstOvalWidth/2)+20;
	int EndOfLine2Y=150;
	int ArcWidth=firstOvalWidth+40;
	int ArcHeight=80;
	int Line3X=(Width/2)-10;
	int Line3Y=EndOfLine1Y-40+ArcHeight;
	int Line4X=(Width/2)+10;
	int Line4Y=EndOfLine1Y-40+ArcHeight;
	g.setColor(black);
	g.drawOval((Width/2)-firstOvalWidth/2,2,firstOvalWidth,firstOvalHeight);
	g.setColor(yellow);
g.fillOval((Width/2)-(firstOvalWidth/2)+1,3,firstOvalWidth-1,firstOvalHeight-1);
g.setColor(black);
g.drawLine(firstLineX,firstOvalY+firstOvalHeight/2,EndOfLine1X,EndOfLine1Y);
g.drawLine(secondLineX,firstOvalY+firstOvalHeight/2,EndOfLine2X,EndOfLine2Y);
g.drawArc(EndOfLine1X,EndOfLine1Y-40,ArcWidth,ArcHeight,180,180);
g.drawLine(Line3X,Line3Y,Line3X-10,Line3Y+90);
g.drawLine(Line4X,Line4Y,Line4X+10,Line4Y+90);
g.drawRect((Width/2)-firstOvalWidth/2,Line4Y+90,180+Line4X-Line3X,20);

g.drawOval(EndOfLine1X+35,firstOvalHeight+25,30,50);
g.drawOval((Width/2)-30,firstOvalHeight+10,60,120);
g.drawOval(EndOfLine2X-65,firstOvalHeight+25,30,50);
g.setColor(yellow);
g.fillOval(EndOfLine1X+36,firstOvalHeight+27,28,48);
g.fillOval((Width/2)-29,firstOvalHeight+12,58,118);
g.fillOval(EndOfLine2X-64,firstOvalHeight+26,28,48);
}
}