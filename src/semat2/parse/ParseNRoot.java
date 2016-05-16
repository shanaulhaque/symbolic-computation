package semat2.parse;

public class ParseNRoot extends Parser implements Runnable  
{
	String in="",out="";
	Thread t;
	int cnt=0;
	
	ParseNRoot(String in1,int cnt)
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
		out=parsenroot();
	}
	
     
	String parsenroot()
	{		
		double n;
		String s2="";
		try
		{
			s2 = in.substring(in.indexOf('[')+1,in.indexOf(']'));
		} 
		catch (Exception e2) 
		{
			e2.printStackTrace();
			return "Nth root Value missing";
		}
		try{
			n=Double.parseDouble(s2);
		}
		catch(Exception e)
		{
			return "Nth root Value must be numeric";
		}
		int i=0;
		int j=0;
		try {
			i = in.indexOf("(");
			j = in.lastIndexOf(")");
		} catch (Exception e) {
			return "Paranthesis Missing";
		}
		String st=in.substring(i+1,j);
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
			return ParseNRoot.evaluate(d,n)+"";
		}
		catch (Exception e1) 
		{
			
		}
		return "nroot["+s2+"]("+s+")";
	}
	private static double evaluate(double e,double n)
	{
		return Math.pow(e,1/n);
	}
}
