class calc
{
public static void main(String[] args)
{
	if(args.length!=3)
		System.out.println("Missing parameter");	
else{	
float num1=Float.parseFloat(args[0]);
float num2=Float.parseFloat(args[2]);

switch(args[1])
{
case "+":
System.out.println("a+b="+(num1+num2));
break;
case "-":
System.out.println("a-b="+(num1-num2));
break;	
case "x":
System.out.println("a*b="+(num1*num2));
break;
case "/":
if(num2!=0)
System.out.println("a/b="+(num1/num2));
else
System.out.println("Illegal division by zero");	
break;
default:
System.out.println("Illegal operation");
}
}
}
}