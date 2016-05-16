package semat2.parse;

public class ParseCoseci extends Parser implements Runnable  
{
	String in="",out="";
	Thread t;
	int cnt=0;
	
	ParseCoseci(String in1,int cnt)
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
		out=parsecoseci();
	}
	
     String parsecoseci()
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
			return ParseCoseci.evaluate(d)+"";
		}
		catch (Exception e1) 
		{
			
		}
		return "icosec("+s+")";
	}
	private static double evaluate(double d)
	{
		d=1.0/d;
		d=Math.asin(d);
		d=(d*180)/Math.PI;
		d=Math.round(d);
		return d;
	}
}
