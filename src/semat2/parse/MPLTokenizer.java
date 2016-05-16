package semat2.parse;
import java.util.StringTokenizer;

public class MPLTokenizer 
{
	String input;
	public MPLTokenizer(String input)
	{
		this.input=input;
	}
	public String[] prepareParse()
	{
		char []st1=input.toCharArray();
		String _str="";
		for(int i=0;i<st1.length;i++)
		{
			char ch=st1[i];
			if(ch=='(')
			{
				try
				{
					do
					{
						_str+=ch;
						i++;
						ch=st1[i];
					}while(ch!=')');
					_str+=')';
				}
				catch(Exception e)
				{
					return new String[]{"Paranthesis Missing"};
				}
			}
			else if(ch=='+')
			{
				_str+='@';
			}
			else
			{
				_str+=ch;
			}
		}
		StringTokenizer st=new StringTokenizer(_str,"@");
		String finalInput[]=new String[st.countTokens()];
		for(int i=0;i<finalInput.length;i++)
		{
			finalInput[i]=st.nextToken();
		}
		return finalInput;
	}
	public String [] tokenize()
	{
		String []finalInput=this.prepareParse();
		String tempOut="";
		for(int i=0;i<finalInput.length;i++)
		{
			if(this.countLPar(finalInput[i])!=this.countRPar(finalInput[i]))
			{
				String s1="";
				s1=finalInput[i];
				i++;
				while(this.countLPar(finalInput[i])==this.countRPar(finalInput[i]))
				{
					s1+="+"+finalInput[i];
					i++;
				}
				s1+="+"+finalInput[i];
				tempOut+=s1+"#";
			}
			else tempOut+=finalInput[i]+"#";
		}
		StringTokenizer st=new StringTokenizer(tempOut,"#");
		String []finalString=new String[st.countTokens()];
		for(int i=0;i<finalString.length;i++)
		{
			finalString[i]=st.nextToken();
		}
		return finalString;
	}
	int countLPar(String in)
	{
		int i=0;
		char []inp=in.toCharArray();
		for(int j=0;j<inp.length;j++)
		{
			if(inp[j]=='(')
			{
				i++;
			}
		}
		return i;
	}
	int countRPar(String in)
	{
		int i=0;
		char []inp=in.toCharArray();
		for(int j=0;j<inp.length;j++)
		{
			if(inp[j]==')')
			{
				i++;
			}
		}
		return i;
	}
}
