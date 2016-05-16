package semat2.parse;

import java.util.StringTokenizer;
import expression.Check;
import expression.ExpressionEvaluation;

public class Nparser 
{

	String input;
	String str;
	public Nparser(String str)
	{
		this.str=str;
	}
	public String[] prepareParse()
	{
			input=str;
	
		MPLTokenizer mplt=new MPLTokenizer(input);
		String []finalString=mplt.tokenize();
		return finalString;
	}
	
	public String parse()throws Exception
	{
		String[] in;
		in=this.prepareParse();
		String [] TempOut=new String[50];
		int tempIndex=0;	

		for(int i=0;i<in.length;i++)
		{
			if(this.isValidChar(in[i]))
			{
				TempOut[tempIndex]=in[i];
				tempIndex++;
			}
			else if(!Check.check(in[i]))
			{
				ExpressionEvaluation e=new ExpressionEvaluation(in[i]);
				double d=e.getValue();
				TempOut[tempIndex]=d+"";
				tempIndex++;
			}
			else if(in[i].startsWith("sum"))
			{
				ParseSum x=new ParseSum(in[i],1);
				String [] temp=x.out;
				for(int j=0;j<temp.length;j++)
				{
					TempOut[tempIndex]=temp[j];
					tempIndex++;
				}
			}
			else if(in[i].startsWith("permu"))
			{
				ParsePermutation x=new ParsePermutation(in[i],2);
				TempOut[tempIndex]=x.out;
				tempIndex++;
			}
			else if(in[i].startsWith("combi"))
			{
				ParseCombination x=new ParseCombination(in[i],2);
				TempOut[tempIndex]=x.out;
				tempIndex++;
			}
			else if(in[i].startsWith("sin"))
			{
				
				ParseSin x=new ParseSin(in[i],2);
				TempOut[tempIndex]=x.out;
				tempIndex++;
			
				System.out.println("Sin evaluation string finally in parser-->"+TempOut[tempIndex-1]);  
			}
			else if(in[i].startsWith("cosec"))
			{
				ParseCosec x=new ParseCosec(in[i],2);
				TempOut[tempIndex]=x.out;
				tempIndex++;
			
			}
			else if(in[i].startsWith("cos"))
			{
				ParseCos ob=new ParseCos(in[i],1);
				TempOut[tempIndex]=ob.out;
				tempIndex++;
			}
			else if(in[i].startsWith("tan"))
			{
				ParseTan ob=new ParseTan(in[i],2);
				TempOut[tempIndex]=ob.out;
				tempIndex++;
			}
			else if(in[i].startsWith("sec"))
			{
				ParseSec ob=new ParseSec(in[i],2);
				TempOut[tempIndex]=ob.out;
				tempIndex++;
			}
			else if(in[i].startsWith("cot"))
			{
				ParseCot ob=new ParseCot(in[i],2);
				TempOut[tempIndex]=ob.out;
				tempIndex++;
			}
			else if(in[i].startsWith("log1"))
			{
				ParseLog ob=new ParseLog(in[i],2);
				TempOut[tempIndex]=ob.out;
				tempIndex++;
			}
			else if(in[i].startsWith("logb"))
			{
				ParseLogb ob=new ParseLogb(in[i],2);
				TempOut[tempIndex]=ob.out;
				tempIndex++;
			}
			else if(in[i].startsWith("ln"))
			{
				ParseLn ob=new ParseLn(in[i],2);
				TempOut[tempIndex]=ob.out;
				tempIndex++;
		    }
			else if(in[i].startsWith("hsin"))
			{
				ParseSinh ob=new ParseSinh(in[i],2);
				TempOut[tempIndex]=ob.out;
				tempIndex++;
			}
			else if(in[i].startsWith("htan"))
			{

				ParseTanh ob=new ParseTanh(in[i],2);
				TempOut[tempIndex]=ob.out;
				tempIndex++;
			}
			else if(in[i].startsWith("hcosec"))
			{
				ParseCosech ob=new ParseCosech(in[i],2);
				TempOut[tempIndex]=ob.out;
				tempIndex++;
			}
			else if(in[i].startsWith("hcos"))
			{

				ParseCosh ob=new ParseCosh(in[i],2);
				TempOut[tempIndex]=ob.out;
				tempIndex++;
			}
			else if(in[i].startsWith("hsec"))
			{

				ParseSech ob=new ParseSech(in[i],2);
				TempOut[tempIndex]=ob.out;
				tempIndex++;
			}
			else if(in[i].startsWith("hcot"))
			{
				ParseCoth ob=new ParseCoth(in[i],2);
				TempOut[tempIndex]=ob.out;
				tempIndex++;
			}
			else if(in[i].startsWith("sroot"))
			{
				ParseSRoot ob=new ParseSRoot(in[i],1);
				TempOut[tempIndex]=ob.out;
				tempIndex++;
			}
			else if(in[i].startsWith("nroot"))
			{
				ParseNRoot ob=new ParseNRoot(in[i],1);
				TempOut[tempIndex]=ob.out;
				tempIndex++;
			}
			else if(in[i].startsWith("isin"))
			{
				ParseSini ob=new ParseSini(in[i],2);
				TempOut[tempIndex]=ob.out;
				tempIndex++;
			}
			else if(in[i].startsWith("icosec"))
			{
				ParseCoseci ob=new ParseCoseci(in[i],2);
				TempOut[tempIndex]=ob.out;
				tempIndex++;
			}
			else if(in[i].startsWith("icos"))
			{

				ParseCosi ob=new ParseCosi(in[i],2);
				TempOut[tempIndex]=ob.out;
				tempIndex++;
			}
			else if(in[i].startsWith("itan"))
			{
				ParseTani ob=new ParseTani(in[i],2);
				TempOut[tempIndex]=ob.out;
				tempIndex++;
			}
			else if(in[i].startsWith("isec"))
			{
				ParseSeci ob=new ParseSeci(in[i],2);
				TempOut[tempIndex]=ob.out;
				tempIndex++;
			}
			else if(in[i].startsWith("icot"))
			{
				ParseCoti ob=new ParseCoti(in[i],2);
				TempOut[tempIndex]=ob.out;
				tempIndex++;
			}
			else if(in[i].startsWith("deriv"))
			{
				ParseDifferentiation ob=new ParseDifferentiation(in[i],2);
				TempOut[tempIndex]=ob.out;
				tempIndex++;
			}
			else if(in[i].startsWith("prod"))
			{
				ParseProduct ob=new ParseProduct(in[i],2);
				TempOut[tempIndex]=ob.out;
				tempIndex++;
			}
			else if(in[i].contains("!"))
			{
				  ParseFact ob=new ParseFact(in[i],2);
				  TempOut[tempIndex]=ob.out;
				  tempIndex++;
			}
			else if(in[i].startsWith("indefint"))
			{
				ParseIndefInt ob=new ParseIndefInt(in[i],2);
				String [] temp=ob.out;
				for(int j=0;j<temp.length;j++)
				{
					TempOut[tempIndex]=temp[j];
					tempIndex++;
				}
				TempOut[tempIndex]="K";
				tempIndex++;
			}
			else if(in[i].startsWith("defint"))
			{
				ParseDefiniteIntegral ob=new ParseDefiniteIntegral(in[i],2);
				String [] temp=ob.out;
				for(int j=0;j<temp.length;j++)
				{
					TempOut[tempIndex]=temp[j];
					tempIndex++;
				}
			}
			else
			{
				TempOut[tempIndex]=in[i];
				tempIndex++;
			}
		}
		int num_count=0,const_count=0;
		for(int i=0;i<tempIndex;i++)
		{
			try
			{
				Double.parseDouble(TempOut[i]);
				num_count++;
			}
			catch(Exception e)
			{
				const_count++;
			}
		}
		String [] num_arr=new String[num_count];
		String [] const_arr=new String[const_count];
		num_count=0;const_count=0;
		for(int i=0;i<tempIndex;i++)
		{
			try
			{
				Double.parseDouble(TempOut[i]);
				num_arr[num_count]=TempOut[i];
				num_count++;
			}
			catch(Exception e)
			{
				const_arr[const_count]=TempOut[i];
				const_count++;
			}
		}
		String const_final="";
		double d=0.0;		
		for(int i=0;i<num_arr.length;i++)
		{
			double dt=Double.parseDouble(num_arr[i]);
			d+=dt;
		}
		if(const_arr.length==1)
		{
			const_final=const_arr[0];
		}
		else if(const_arr.length > 1)
		{
			int i;
			for(i=0;i<const_arr.length-1;i++)
			{
				const_final+=const_arr[i]+"+";
			}
			const_final+=const_arr[i];
		}
		String final_result="";
		if(const_arr.length==0)
		{
			final_result=d+"";
		}
		else if(num_arr.length==0)
		{
			final_result=const_final;
		}
		else
		{
			final_result=d+"+"+const_final;
		}
		StringTokenizer st=new StringTokenizer(final_result,"-");
		String [] fin=new String[st.countTokens()];
		for(int i=0;i<fin.length;i++)
		{
			fin[i]=st.nextToken();
		}
		for(int i=0;i<fin.length;i++)
		{
			if(fin[i].charAt(fin[i].length()-1)=='+')
			{
				fin[i]=fin[i].substring(0,fin[i].length()-1);
			}
		}
		String finOut="";
		int i=0;
		for(i=0;i<fin.length-1;i++)
		{
			finOut+=fin[i]+"-";
		}
		finOut+=fin[i];
		return finOut;
	}
	
	private boolean isValidChar(String arg)
	{
		char ch[]=arg.toCharArray();
		if(ch.length==1)
		{
			char key=ch[0];
			if((key >= 48 && key <=57)||(key >= 65 && key <=90)||(key==61)||(key==42)||(key>=45 && key<=47)||(key >= 97 && key <=122))
			{
				return true;
			}
		}
		return false;
	}
}