package semat2.parse;

public class ParseCoth extends Parser implements Runnable  
{
	String in="",out="";
	Thread t;
	int cnt=0;
	
	ParseCoth(String in1,int cnt)
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
		out=parsecoth();
	}
	
     
	String parsecoth()
	{		
		int i=0;
		int j=0;
		try {
			i = in.indexOf("(");
			j = in.lastIndexOf(")");
		} catch (Exception e) {
			return "Paranthesis Missing";
		}
		String st=in.substring(i+1,j);
		Nparser p=new Nparser(st);
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
			return ParseCoth.evaluate(d)+"";
		}
		catch (Exception e1) 
		{
			
		}
		return "coth("+s+")";
	}
	private static double evaluate(double d)
	{
		return 1/Math.tanh(d);
	}
}
