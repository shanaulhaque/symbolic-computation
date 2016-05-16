package semat2.parse;

import java.util.StringTokenizer;

import expression.Check;
import expression.ExpressionEvaluation;

public class ParseProduct extends Parser implements Runnable  
{
	String input="",out="";
	Thread t;
	int cnt=0;
	private static String [] params; /*Contains the parameter X, lower bound and upper bound*/
	private static String error,finalOutput;
	
	ParseProduct(String in1,int cnt)
	{
		this.cnt=cnt;
		input=in1;
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
		out=parsecot();
	}
	
     
	String parsecot()
	{		
		error="";
		finalOutput="";
		/*Taking the parameters (parameter,lower bound,upper bound*/
		String parameters;
		try{
			parameters=input.substring(input.indexOf("[")+1,input.lastIndexOf("]"));
		}
		catch(Exception e)
		{
			error="Lower limit not in the format x=1";
			return error;
		}
		
		/*Taking the inputs*/
		String parstring="";
		try{
			parstring=input.substring(input.indexOf("(")+1,input.lastIndexOf(")"));
		}
		catch(Exception e)
		{
			error="Left or Right Paranthesis Missing";
			return error;
		}
		
		/*Splitting the parameters into array of String*/
		StringTokenizer st=new StringTokenizer(parameters,",");
		params=new String[st.countTokens()];
		for(int i=0;i<params.length;i++)
		{
			params[i]=st.nextToken();
		}
		
		/*Splitting the inputs into array of String*/
		st=new StringTokenizer(parstring,"+");
		String [] args=new String[st.countTokens()];
		for(int i=0;i<args.length;i++)
		{
			args[i]=st.nextToken();
		}
	
		/*Getting Upper bound*/
		int lowl=0;
		try{
			lowl=Integer.parseInt(params[1]);
		}
		catch(Exception e)
		{
			error="Numeric Value Should be Entered";
			return error;
		}
		int uppl=0;
		
		/*Getting Upper bound*/
		try{
			uppl=Integer.parseInt(params[2]);
		}
		catch(Exception e)
		{
			error="Numeric Value Should be Entered";
			return error;
		}
		
		/*Swapping Bounds if Lower bound is greater than Upper bound*/
		if(lowl > uppl)
		{
			int temp;
			temp=lowl;
			lowl=uppl;
			uppl=temp;
		}
		if(parstring.contains(params[0]))
		{
			double val=1;
			String temp=parstring.replace(params[0],lowl+"");
			if(!Check.check(temp))
			{
				for(int i=lowl;i<=uppl;i++)
				{
					temp=parstring.replace(params[0],i+"");
					ExpressionEvaluation e=new ExpressionEvaluation(temp);
					try 
					{
						double d=e.getValue();
						val=val*d;
					}
					catch (Exception e1)
					{
						e1.printStackTrace();
					}
				}
				finalOutput=val+"";
			}
			else
			{
				finalOutput="("+parstring+")^("+uppl+")";
			}
		}
		else finalOutput="("+parstring+")^("+uppl+")";
		return finalOutput;
	}
}