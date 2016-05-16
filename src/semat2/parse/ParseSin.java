package semat2.parse;

public class ParseSin extends Parser implements Runnable 
{
	 String in="",out="";
	 Thread t;
	 int cnt=0;
	 
	ParseSin(String in1,int cnt)
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
		 int j=0;;
		 try {
			i = in.indexOf("(");
			j = in.lastIndexOf(")");
		    }
		    catch (Exception e2)
		    {
			e2.printStackTrace();
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
			System.out.println("RParser exception in sin");
			}
		try 
		{
			double d=Double.parseDouble(s);
			return ParseSin.evaluate(d)+"";
		}
		catch (Exception e1) 
		{
			
		}
		return "sin("+s+")";
		
	}
	private static double evaluate(double d)
	{
		d=(d*Math.PI)/180;
		return Math.sin(d);
	}
}
