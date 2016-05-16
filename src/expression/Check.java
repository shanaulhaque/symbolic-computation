package expression;

import java.util.StringTokenizer;

public class Check 
{
	public static boolean check(String s)
	{
		StringTokenizer st=new StringTokenizer(s,"^+()-*/");
		while(st.hasMoreTokens())
		{
			try
			{
				Double.parseDouble(st.nextToken());
			}
			catch(Exception e)
			{
				return true;
			}
		}
		return false;
	}
}
