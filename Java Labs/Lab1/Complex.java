package pack3;
public class Complex
{
	private int real;
	private int imag;
	public Complex(int Creal,int Cimag)
	{
		real=Creal;
		imag=Cimag;
	}
	public void setReal(int Creal)
	{
		real=Creal;
	}
	public void setImag(int Cimag)
	{
		imag=Cimag;
	}
	public int getReal()
	{
		return real;
	}
	public int getImag()
	{
		return imag;
	}
	public static Complex add(Complex c1,Complex c2)
	{
		return new Complex(c1.getReal()+c2.getReal(),c1.getImag()+c2.getImag());
	}
	public static Complex sub(Complex c1,Complex c2)
	{
		return new Complex(c1.getReal()-c2.getReal(),c1.getImag()-c2.getImag());
	}
	public void print()
	{
		if(imag>0)
		System.out.println(real+"+i"+imag);
	else
		System.out.println(real+"-i"+(imag*-1));
	}
	public static void main(String [] arg)
	{
		
		Complex c1=new Complex(1,2);
		Complex c2=new Complex(3,4);
		Complex c3;
		Complex c4;
		c3=Complex.add(c1,c2);
		c4=Complex.sub(c1,c2);
		c3.print();
		c4.print();
	}
}