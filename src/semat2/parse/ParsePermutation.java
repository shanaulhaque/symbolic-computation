package semat2.parse;

import java.util.StringTokenizer;

public class ParsePermutation extends Parser implements Runnable  
{
	String in="",out="";
	Thread t;
	int cnt=0;
	
	ParsePermutation(String in1,int cnt)
	{
		this.cnt=cnt;
		in=in1;
		t=new Thread(this);
        t.start();
        try {
			t.join();
		} catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void run()
	{
		out=parsepermutation();
	}
	
     
	String parsepermutation()
	{		
		int n=0,r=0;
		String st=in.substring(in.indexOf('[')+1,in.indexOf(']'));
		StringTokenizer s=new StringTokenizer(st,",");
		try
		{
			n=Integer.parseInt(s.nextToken());
			r=Integer.parseInt(s.nextToken());
		}
		catch(Exception e)
		{
			return "Value of n or r should be numeric";
		}
		if(n >= r)
		{
			return evaluate(n,r)+"";
		}
		else
		{
			return "n should be greater than or equal to r";
		}
	}
	private static double evaluate(double n,double r)
	{
		double d1=ParseFactorial.parse(n);
		double d2=ParseFactorial.parse(n-r);
		return d1/d2;
	}
}
