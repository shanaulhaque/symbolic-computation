package semat2.parse;


public class ParseLogb extends Parser implements Runnable  
{
	String in="",out="";
	Thread t;
	int cnt=0;
	
	ParseLogb(String in1,int cnt)
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
		out=parselogb();
	}
	
     
	String parselogb()
	{	
		double b;
		String s2="";
		try {
			s2 = in.substring(in.indexOf('[')+1,in.indexOf(']'));
		} 
		catch (Exception e2) {
			e2.printStackTrace();
			return "Base Value Missing";
		}
		System.out.println(s2);
		try{
			b=Double.parseDouble(s2);
		}
		catch(Exception e)
		{
			return "Base value must be numeric";
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
			return ParseLogb.evaluate(d,b)+"";
		}
		catch (Exception e1) 
		{
			
		}
		return "log["+s2+"]("+s+")";
	}
	private static double evaluate(double val,double b)
	{
		return Math.log10(val)/Math.log10(b);
	}
}
