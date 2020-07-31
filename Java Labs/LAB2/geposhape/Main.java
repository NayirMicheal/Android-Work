class Main
{
	public static void main(String[] args)
	{
		Geoshape g_c=new Circle(3);
		Geoshape g_s=new Square(5);
		Geoshape g_t=new Triangle(6,8);
		Geoshape g_s2=new Square(2);
		System.out.println("Sum of Areas is "+Geoshape.sumAreas(g_c,g_s,g_t));
		System.out.println("Another Sum of Areas is "+Geoshape.sumAreas(g_c,g_s,g_s2));
	}
}