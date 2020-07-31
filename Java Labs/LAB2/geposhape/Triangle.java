class Triangle extends Geoshape{
	Triangle(int a,int b)
	{
		super(a,b);
	}
	public float getArea()
	{
		return .5F*length*width;
	}
}