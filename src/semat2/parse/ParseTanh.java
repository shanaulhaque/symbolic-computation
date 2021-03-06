package semat2.parse;

public class ParseTanh extends Parser implements Runnable  
{
	String in="",out="";
	Thread t;
	int cnt=0;
	
	ParseTanh(String in1,int cnt)
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
		out=parsetanh();
	}
	
     
	String parsetanh()
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
			e.printStackTrace();
		}
		try 
		{
			double d=Double.parseDouble(s);
			return ParseTanh.evaluate(d)+"";
		}
		catch (Exception e1) 
		{
			
		}
		return "tanh("+s+")";
	}
	private static double evaluate(double d)
	{
		return Math.tanh(d);
	}
}
