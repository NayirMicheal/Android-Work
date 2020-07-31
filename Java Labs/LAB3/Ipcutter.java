class Ipcutter
{
	public static void main(String [] args)
	{
		int firstOcuur=0;
		int secondOcuur=1;
		do
		{	secondOcuur=args[0].indexOf(".",firstOcuur);
			if(secondOcuur==-1)
				break;
			String fiSuffix=args[0].substring(firstOcuur,secondOcuur);
				System.out.println(fiSuffix);
			firstOcuur=secondOcuur+1;
		}while(secondOcuur!=-1);
		String last=args[0].substring(firstOcuur);
		System.out.println(last);
		
	}
}