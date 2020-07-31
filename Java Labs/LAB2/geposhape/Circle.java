class Circle extends Geoshape{
	Circle(int a)
	{
		super(a,a);
	}
	public float getArea()
	{
		return 3.14F*length*width;
	}
}