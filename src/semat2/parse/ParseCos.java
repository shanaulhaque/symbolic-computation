package semat2.parse;

public class ParseCos extends Parser implements Runnable 
{
	 String in="",out="";
	 Thread t;
	 int cnt=0;
	 
	ParseCos(String in1,int cnt)
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
		out=value();
	}
		String value()
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
			System.out.println("RParser exception in cos");
			
		}
		try 
		{
			double d=Double.parseDouble(s);
			return ParseCos.evaluate(d)+"";
		}
		catch (Exception e1) 
		{
			
		}
		return "cos("+s+")";
	}
	private static double evaluate(double d)
	{
		d=(d*Math.PI)/180;
		return Math.cos(d);
	}
}
