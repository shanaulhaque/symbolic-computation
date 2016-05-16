package semat2.parse;

import java.util.StringTokenizer;

public class ParseSum extends Parser implements Runnable  
{
	String input="",out[];
	Thread t;
	int cnt=0;
	private static String [] params; /*Contains the parameter X, lower bound and upper bound*/
	private static String [] error;
	
	ParseSum(String in1,int cnt)
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
	
     
	String [] parsecot()
	{		
		error=new String[1];
		/*Taking the parameters (parameter,lower bound,upper bound*/
		String parameters;
		try{
			parameters=input.substring(input.indexOf("[")+1,input.lastIndexOf("]"));
		}
		catch(Exception e)
		{
			error[0]="Lower limit not in the format x=1";   // Error Code 003
			return error;
		}
		
		/*Taking the inputs*/
		String parstring;
		try{
			parstring=input.substring(input.indexOf("(")+1,input.lastIndexOf(")"));
		}
		catch(Exception e)
		{
			error[0]="Left or Right Paranthesis Missing";    // Error Code 002
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
		
		/*Preparing the output array*/
		String [] output=new String[10];
		int outIndex=0;
		
		/*par holds the parameter*/
		char par=params[0].charAt(0);
		
		/*Getting Upper bound*/
		int lowl=0;
		try{
			lowl=Integer.parseInt(params[1]);
		}
		catch(Exception e)
		{
			error[0]="Numeric Value Should be Entered";       // Error Code 001
			return error;
		}
		int uppl=0;
		
		/*Getting Upper bound*/
		try{
			uppl=Integer.parseInt(params[2]);
		}
		catch(Exception e)
		{
			error[0]="Numeric Value Should be Entered";       // Error Code 001
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
		
		/*Starts evaluating each token*/
		for(int i=0;i<args.length;i++)
		{
			/*Checks if the length is one*/
			if(args[i].length()==1)
			{	
				char []c=args[i].toCharArray();
				/*Checks if the input token is a number or not*/
				if(c[0] >= '0' && c[0] <='9')
				{
					output[outIndex]=sumIfNumber(args[i],uppl,lowl);
					outIndex++;
				}
				/*Checks if the input token is the parameter or not*/
				else if(c[0]==par)
				{
					output[outIndex]=sumIfParameter(args[i],uppl,lowl);
					outIndex++;
				}
				/*Assumes it is a character constant*/
				else
				{
					output[outIndex]=sumIfConstant(args[i],uppl,lowl);
					outIndex++;
				}//end of if else statement
			} 
			/*Executes if the length is more than one*/
			else if(args[i].length()> 1)
			{
				/*Checks if the input token contains - symbol*/
				if(args[i].indexOf("-")!=-1)
				{
					String [] _Temp=sumIfContainsMinus(args[i],uppl,lowl);
					if((_Temp.length==1)&&(_Temp[0].equals("Error")))
					{
						error[0]="Invalid Input";
						return error;
					}
					for(int z=0;z<_Temp.length;z++)
					{
						output[outIndex]=_Temp[z];
						outIndex++;
					}
				}
				
				/*Checks if the input token contains * symbol*/
				else if(args[i].indexOf("*")!=-1)
				{
					output[outIndex]=sumIfContainsMultiply(args[i],uppl,lowl);
					outIndex++;
				}
				
				/*Checks if the input token contains ^ symbol*/
				else if(args[i].indexOf("^")!=-1)
				{
					output[outIndex]=sumIfContainsPower(args[i],uppl,lowl);
					outIndex++;
				}
				
				/*Checks if all previous conditions fail*/
				else
				{
					int ss=0;
					String s1="";
					try
					{
						ss=Integer.parseInt(args[i]);						
						ss=(uppl-lowl+1)*ss;					
						output[outIndex]=ss+"";
						outIndex++;
					}
					catch(Exception e)
					{
						s1=args[i];
						output[outIndex]=(uppl-lowl+1)+""+s1;
						outIndex++;
					}
				}
			}
		}
		String [] finalOutput=new String[outIndex];
		for(int i=0;i<outIndex;i++)
		{
			finalOutput[i]=output[i];
		}
		return finalOutput;
	}
	
	/*Returns true if the input string is a number*/
	private static boolean isNumber(String str)
	{
		try{
			Integer.parseInt(str);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	/*Return the Sum if the String is a numeric constant*/
	private static String sumIfNumber(String input,int upperbound,int lowerbound)
	{
		int sum=0;
		try
		{
			sum=Integer.parseInt(input);
			sum=sum*(upperbound-lowerbound+1);
		}
		catch(Exception e)
		{
			System.out.println("Error");
		}
		return sum+"";
	}
	
	/*Return the Sum if the String is the parameter*/
	private static String sumIfParameter(String input,int upperbound,int lowerbound)
	{
		int sum=0;
		for(int i=lowerbound;i<=upperbound;i++)
		{
			sum=sum+i;
		}
		return sum+"";
	}
	
	/*Return the Sum if the String is a constant*/
	private static String sumIfConstant(String input,int upperbound,int lowerbound)
	{
		return (upperbound-lowerbound+1)+""+input;
	}
	
	/*Return the Sum if the String contains a power '^' symbol*/
	private static String sumIfContainsPower(String input,int upperbound,int lowerbound)
	{
		String [] tokens=getTokens(input,"^");
		String TempOut="";
		int sum=0;
		if(tokens[0].equalsIgnoreCase(params[0]))
		{
			for(int i=lowerbound;i<=upperbound;i++)
			{
				sum=sum+(int)Math.pow(i,Integer.parseInt(tokens[1]));
			}
			TempOut=sum+"";
		}
		return TempOut;
	}
	
	/*Return the Sum if the String contains a multiplication '*' symbol*/
	private static String sumIfContainsMultiply(String input,int upperbound,int lowerbound)
	{
		String [] tokens=getTokens(input,"*");
		String TempOut="";
		int sum=0;
		if(tokens[0].equalsIgnoreCase(params[0]))
		{
			for(int k=lowerbound;k<=upperbound;k++)
			{
				sum=sum+k*(Integer.parseInt(tokens[1]));
			}
			TempOut=sum+"";
		}
		return TempOut;
	}
	
	/*Return the Sum if the String contains a minus '-' symbol*/
	private static String[] sumIfContainsMinus(String input,int upperbound,int lowerbound)
	{
		String [] tokens=getTokens(input,"-");
		String TempOut[]=new String[10];
		int TempOutIndex=0;
		if(tokens.length!=1 && input.indexOf('-')!=0)
		{
			for(int k=0;k<tokens.length;k++)
			{
				if(tokens[k].equalsIgnoreCase(params[0]))
				{
					int sum_m=0;
					for(int l=lowerbound;l<=upperbound;l++)
					{
						sum_m=sum_m+l;
					}
					if(k!=0)
					{
						sum_m= -1*sum_m;
					}
					TempOut[TempOutIndex]=sum_m+"";
					TempOutIndex++;
				}
				else if(isNumber(tokens[k]))
				{
					int sum_m=0;
					int temp=Integer.parseInt(tokens[k]);
					for(int l=lowerbound;l<=upperbound;l++)
					{
						sum_m=sum_m+temp;
					}
					if(k!=0)
					{
						sum_m= -1*sum_m;
					}
					TempOut[TempOutIndex]=sum_m+"";
					TempOutIndex++;
				}
				else if(tokens[k].indexOf('*')!=-1)
				{
					TempOut[TempOutIndex]=sumIfContainsMultiply(tokens[k],upperbound,lowerbound);
					TempOutIndex++;
				}
				else if(tokens[k].indexOf('^')!=-1)
				{
					TempOut[TempOutIndex]=sumIfContainsPower(tokens[k],upperbound,lowerbound);
					TempOutIndex++;
				}
				else
				{
					String sts=(upperbound-lowerbound+1)+""+tokens[k];
					if(k!=0)
					{
						sts= "-"+sts;
					}
					TempOut[TempOutIndex]=sts;
					TempOutIndex++;
				}
			}
		}
		else if(tokens.length!=1 && input.indexOf('-')==0)
		{
			for(int k=0;k<tokens.length;k++)
			{
				if(tokens[k].equalsIgnoreCase(params[0]))
				{
					int sum_m=0;
					for(int l=lowerbound;l<=upperbound;l++)
					{
						sum_m=sum_m+l;
					}
					sum_m= -1*sum_m;
					TempOut[TempOutIndex]=sum_m+"";
					TempOutIndex++;
				}
				else if(isNumber(tokens[k]))
				{
					int sum_m=0;
					int temp=Integer.parseInt(tokens[k]);
					for(int l=lowerbound;l<=upperbound;l++)
					{
						sum_m=sum_m+temp;
					}
					sum_m= -1*sum_m;
					TempOut[TempOutIndex]=sum_m+"";
					TempOutIndex++;
				}
				else if(tokens[k].indexOf('*')!=-1)
				{
					TempOut[TempOutIndex]=sumIfContainsMultiply(tokens[k],upperbound,lowerbound);
					TempOutIndex++;
				}
				else if(tokens[k].indexOf('^')!=-1)
				{
					TempOut[TempOutIndex]=sumIfContainsPower(tokens[k],upperbound,lowerbound);
					TempOutIndex++;
				}
				else
				{
					String sts=(upperbound-lowerbound+1)+""+tokens[k];
					sts= "-"+sts;
					TempOut[TempOutIndex]=sts;
					TempOutIndex++;
				}
			}
		}
		else if(tokens.length==1)
		{
			
			String TempArg=tokens[0];
			int ss=0;
			if(input.indexOf('-')!=0)
			{
				String [] _Temp=new String[1];
				_Temp[0]="Error";
				return _Temp;
			}
			else if(TempArg.length()==1);
			{
				if(TempArg.equalsIgnoreCase(params[0]))
				{
					for(int i=lowerbound;i<=upperbound;i++)
					{
						ss=ss+i;
					}
					ss=-1*ss;
					TempOut[TempOutIndex]=ss+"";
					TempOutIndex++;
				}
				else
				{
					try
					{
						ss=Integer.parseInt(TempArg);						
						ss=(upperbound-lowerbound+1)*ss;					
						TempOut[TempOutIndex]=ss+"";
						TempOutIndex++;
					}
					catch(Exception e)
					{
						String [] TempTokens=getTokens(input,"-");
						TempTokens[0]="-"+(upperbound-lowerbound+1)+TempTokens[0];
						TempOut[TempOutIndex]=TempTokens[0];
						TempOutIndex++;
					}
				}
			}
		}
		int d=0;
		String temp="";
		for(int i=0;i<TempOutIndex;i++)
		{
			try{
				d=d+Integer.parseInt(TempOut[i]);
			}
			catch(Exception e)
			{
				if(TempOut[i].indexOf('-')!=-1)
				{
					temp+=TempOut[i];
				}
				else
				{
					temp+=(TempOut[i]);
				}
			}
		}
		//System.out.println(d);
		//System.out.println(temp);
		String [] _Temp=new String[2];
		_Temp[0]=d+"";
		_Temp[1]=temp;
		return _Temp;
	}
	
	/*Tokenize the inputs and returns array of String*/
	private static String [] getTokens(String input,String constraint)
	{
		StringTokenizer tokenize=new StringTokenizer(input,constraint);
		String [] TempOut=new String[tokenize.countTokens()];
		for(int i=0;i<TempOut.length;i++)
		{
			TempOut[i]=tokenize.nextToken();
		}
		return TempOut;
	}
}