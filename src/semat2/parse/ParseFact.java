package semat2.parse;

import expression.Check;
import expression.ExpressionEvaluation;

public class ParseFact extends Parser implements Runnable  
{
	String in="",out="";
	Thread t;
	int cnt=0;
	
	ParseFact(String in1,int cnt)
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
		out=parsefactorial();
	}
	
     
	String parsefactorial()
	{		
		int i=0;
		try {
			i = in.indexOf("!");
		}
		catch (Exception e2)
		{
			e2.printStackTrace();
			return "Paranthesis Missing";
		}
		String st=in.substring(0,i);
		String _st="";
		try
		{
			_st=in.substring(i+1);
		}
		catch(Exception e)
		{
			_st="";
		}
		Parser p=new Parser(st);
		String s="";
		try{
			s=p.parse();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		try 
		{
			double d=Double.parseDouble(s);
			d=ParseFact.evaluate(d);
			if(!_st.equals(""))
			{
				String temp=d+_st;
				if(!Check.check(temp))
				{
					ExpressionEvaluation e=new ExpressionEvaluation(temp);
					d=e.getValue();
					return d+"";
				}
				return temp;
			}
			return d+"";
		}
		catch (Exception e1) 
		{
			
		}
		return in;
	}
	private static double evaluate(double d)
	{
		return ParseFactorial.parse(d);
	}
}
