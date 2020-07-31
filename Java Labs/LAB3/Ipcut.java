class Ipcut
{
	public static void main(String [] args)
	{
		String[] suffix=args[0].split("\\.");
		for(String str:suffix)
			System.out.println(str);
	}
}