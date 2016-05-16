package semat2.parse;

public class ParseFactorial 
{
	public static double parse(double x)
	{
		if(x==0)
		{
			return 1.0;
		}
		return x*parse(x-1);
	}
}
