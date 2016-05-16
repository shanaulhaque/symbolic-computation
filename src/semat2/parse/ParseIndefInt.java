package semat2.parse;

import java.util.StringTokenizer;

public class ParseIndefInt extends Parser implements Runnable 
{
	 String in="",out[]=new String[100];
	 Thread t;
	 int cnt=0;
	 
	 ParseIndefInt(String in1,int cnt)
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
		String [] value()
		{
			int i=0;
		int j=0;
		String st="",st1="";
		try {
			i = in.indexOf("(");
			j = in.lastIndexOf(")");
			st=in.substring(i+1,j);
		} catch (Exception e) {
			return new String[]{"Paranthesis Missing"};
		}
		st1=in.substring(j+1);
		if(st1.equals(""))
		{
			return new String[]{"dx value is missing"};
		}
		StringTokenizer stt=new StringTokenizer(st,"+");
		String [] tokens=new String[stt.countTokens()];
		i=0;
		String [] output=new String[stt.countTokens()];
		int l=0;
		while(stt.hasMoreTokens())
		{
			tokens[i]=stt.nextToken();
			i++;
		}
		if(st1.length()==2)
		{
			st1=st1.substring(1);
			for(int k=0;k<tokens.length;k++)
			{
				if(tokens[k].contains(st1))
				{
					if((tokens[k]).length()==1)
					{
						output[l]=st1+"^2/2";
						l++;
					}
					else if((tokens[k]).contains("^")&&(tokens[k].indexOf(st1)==0))
					{
						String s1=tokens[k].substring(tokens[k].indexOf("^")-1,1);
						String s2=tokens[k].substring(tokens[k].indexOf("^")+1);
						try
						{
							int temp=Integer.parseInt(s2);
							temp++;
							s2=temp+"";
							output[l]=s1+"^"+s2+"/"+s2+"";
							l++;
						}
						catch (Exception e) 
						{
							s2=s2+"+1";
							output[l]=s1+"^("+s2+")/("+s2+")";
							l++;
						}
					}
					else if((tokens[k]).contains("^")&&(tokens[k].indexOf(st1)==tokens[k].indexOf("^")+1)&&(tokens[k].contains("2.71")))
					{
						output[l]=tokens[k]+"";
						l++;
					}
					else if((tokens[k]).contains("^")&&(tokens[k].indexOf(st1)==tokens[k].indexOf("^")+1))
					{
						String s1=tokens[k].substring(tokens[k].indexOf("^")+1);
						String s2=tokens[k].substring(tokens[k].indexOf("^")-1,1);
						output[l]=s2+"^("+s1+")/ln("+s2+")";
						l++;
					}
					else if((tokens[k]).contains("/")&&(tokens[k].indexOf(st1)==tokens[k].indexOf("/")+1))
					{
						String s1=tokens[k].substring(tokens[k].indexOf("/")+1);
						String s2=tokens[k].substring(tokens[k].indexOf("/")-1,1);
						if(!s2.equals("1"))
						{
							output[l]=s2+"ln("+s1+")";
						}
						else output[l]="ln("+s1+")";
						l++;
					}
					else if((tokens[k].contains("hcosec("+st1+")"))&&(tokens[k].contains("hcot("+st1+")")))
					{
						output[l]="-cosech("+st1+")";
						l++;
					}
					else if((tokens[k].contains("hsec("+st1+")"))&&(tokens[k].contains("htan("+st1+")")))
					{
						output[l]="-sech("+st1+")";
						l++;
					}
					else if((tokens[k].contains("cosec("+st1+")"))&&(tokens[k].contains("cot("+st1+")")))
					{
						output[l]="-cosec("+st1+")";
						l++;
					}
					else if((tokens[k].contains("sec("+st1+")"))&&(tokens[k].contains("tan("+st1+")")))
					{
						output[l]="-sec("+st1+")";
						l++;
					}
					else if((tokens[k].startsWith("hsin"))&&(tokens[k].contains(st1)))
					{
						output[l]="cosh("+st1+")";
						l++;
					}
					else if((tokens[k].startsWith("hcos"))&&(tokens[k].contains(st1)))
					{
						output[l]="sinh("+st1+")";
						l++;
					}
					else if((tokens[k].startsWith("sin"))&&(tokens[k].contains(st1)))
					{
						output[l]="-cos("+st1+")";
						l++;
					}
					else if((tokens[k].startsWith("cos"))&&(tokens[k].contains(st1)))
					{
						output[l]="sin("+st1+")";
						l++;
					}
				}
				else
				{
					output[l]=st1+"*("+tokens[k]+")";
					l++;
				}
			}
		}
		return output;
	}
}
