abstract class Geoshape
{
protected float length;
protected float width;
Geoshape(int x,int y)
{
	length=x;
	width=y;
}
abstract public float getArea();
public static float sumAreas(Geoshape g1,Geoshape g2,Geoshape g3)
{
	 return g1.getArea()+g2.getArea()+g3.getArea();
}	
}