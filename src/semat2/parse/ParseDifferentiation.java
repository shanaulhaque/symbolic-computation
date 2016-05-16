package semat2.parse;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.lsmp.djep.djep.DJep;
import org.nfunk.jep.Node;
import org.nfunk.jep.ParseException;

public class ParseDifferentiation extends Parser implements Runnable 
{
	 String in="",out="";
	 Thread t;
	 int cnt=0;
	 
	ParseDifferentiation(String in1,int cnt)
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
		out=valueDifferentiation();
	}
		String valueDifferentiation()
		{
			String s2="";
		s2 = in.substring(in.indexOf('[')+1,in.indexOf(']'));
		if(s2.equals("")) 
		{
			return "dx value is missing";
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
		if(st.contains("isin"))
		{
			st=st.replaceFirst("isin","asin");
		}
		if(st.contains("icos"))
		{
			st=st.replaceFirst("icos","acos");
		}
		if(st.contains("itan"))
		{
			st=st.replaceFirst("itan","atan");
		}
		if(st.contains("hsin"))
		{
			st=st.replaceFirst("hsin","sinh");
		}
		if(st.contains("hcos"))
		{
			st=st.replaceFirst("hcos","cosh");
		}
		if(st.contains("htan"))
		{
			st=st.replaceFirst("htan","tanh");
		}
		try 
		{
			return ParseDifferentiation.evaluate(st,s2);
		}
		catch (Exception e1) 
		{
			e1.printStackTrace();
		}
		return in;
	}
	private static String evaluate(String input1,String input2)
	{
		DJep j=new DJep();
		ByteArrayOutputStream b=new ByteArrayOutputStream();
        j.addStandardConstants();
        j.addStandardFunctions();
        j.addComplex();
        j.setAllowUndeclared(true);
        j.setAllowAssignment(true);
        j.setImplicitMul(true);   
        j.addStandardDiffRules();
        try 
        {
        	Node n1 = j.parse(input1);
        	Node n2 = j.differentiate(n1,input2);
        	Node n3 = j.simplify(n2);
        	PrintStream p=new PrintStream(b);
        	j.print(n3,p);
        }
        catch(ParseException e)
        {
            e.printStackTrace();
        }
        return b.toString();
	}
}
