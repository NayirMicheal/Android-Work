class Square extends Geoshape{
	Square(int a)
	{
		super(a,a);
	}
	public float getArea()
	{
		return length*width;
	}
}