package semat2.parse;

import java.util.StringTokenizer;

import expression.Check;
import expression.ExpressionEvaluation;

public class ParseDefiniteIntegral extends Parser implements Runnable 
{
	 String in="",out[]=new String[100];
	 Thread t;
	 int cnt=0;
	 
	ParseDefiniteIntegral(String in1,int cnt)
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
	
	//synchronized 
	public void run()
	{
		out=valueDefinite();
	}
		String [] valueDefinite()
		{
		 String low="",expr="",up="",dx="";
		try
		{
			expr=in.substring(in.indexOf('('));
		}
		catch(Exception e)
		{
			return new String[]{"Paranthesis Missing"};
		}	
		String temp=in.substring(in.indexOf('[')+1,in.indexOf(']'));
		StringTokenizer st=new StringTokenizer(temp,",");
		try
		{
			low=st.nextToken();
			up=st.nextToken();
			dx=in.substring(in.lastIndexOf(')')+2);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new String[]{"Upper Limit or Lower Limit should not left blank"};
		}
		try
		{
			Integer.parseInt(low);
			Integer.parseInt(up);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new String[]{"Limits should be numeric"};
		}
		ParseIndefInt ob=new ParseIndefInt(expr,2);
		String [] tempResult=ob.out;
		String [] finResult=new String[tempResult.length];
		int fin=0;
		for(int i=0;i<tempResult.length;i++)
		{
			String lowRes=tempResult[i].replaceAll(dx,low);
			String upRes=tempResult[i].replaceAll(dx,up);
			if(!Check.check(lowRes)&&!Check.check(upRes))
			{
				try {
					ExpressionEvaluation e=new ExpressionEvaluation(lowRes);
					double d1=e.getValue();
					e=new ExpressionEvaluation(upRes);
					double d2=e.getValue();
					finResult[fin]=(d2-d1)+"";
					fin++;
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
			else if(tempResult[i].contains("^"))
			{
				String st1=tempResult[i].replace(dx,up);
				String st2=tempResult[i].replace(dx,low);
				finResult[fin]="("+st1+")-("+st2+")";
				fin++;
			}
			else
			{
				int a=Integer.parseInt(up);
				int b=Integer.parseInt(low);
				tempResult[i]=tempResult[i].replace(dx,(a-b)+"");
				finResult[fin]=tempResult[i]+"";
				fin++;
			}
		}
		return finResult;
	}
}
