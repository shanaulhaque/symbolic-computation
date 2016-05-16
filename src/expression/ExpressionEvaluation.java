package expression;

import java.util.*;

public class ExpressionEvaluation
{
	private String elements[];
	private int numt=0;
	private int global=0;	
	private Node tree;
	private Stack<Double> stack;
	
	public ExpressionEvaluation(String s)
	{
		StringTokenizer tokenizer=new StringTokenizer(s,"^+()-*/",true);
		elements=new String[tokenizer.countTokens()];
		numt=tokenizer.countTokens();		
		int i=0;
		while (tokenizer.hasMoreTokens())
		{
			elements[i]=tokenizer.nextToken();
			i++;
		}
		stack=new Stack<Double>();
	}
	
	private boolean isOperator(String c)
	{
		if(c.equals("+")||c.equals("^")||c.equals("/")||c.equals("*")||c.equals("-"))
			return true;
		return false	;
	}

	private boolean isOperand(String c)
	{
		try{
			Double.parseDouble(c);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	public double getValue()throws Exception
	{
		tree=getTree();
		postOrder(tree);
		return stack.pop();
	}
		
	public Node getTree()
	{
		Node node[],nd;
		node=new Node[numt-global];
		int i=0;
		while (global<numt)
		{
			if (isOperand(elements[global]))
			{
				nd=new Node(null,null,elements[global],true);
				node[i]=nd;
				i++;
				global++;
			}
			else if(isOperator(elements[global]))
			{
				nd=new Node(null,null,elements[global],false);
				node[i]=nd;								
				i++;
				global++;
			}
			else if (elements[global].equals("("))
			{
				global++;
				node[i]=getTree();				
				i++;
			}
			else if (elements[global].equals(")"))
			{
				global++;
				break;
			}
		}
		while (true)
		{
			int priority=0;
			int index=0;
			for(int j=0;j<i;j++)
			{
				if ((!node[j].isOperand()&&(!node[j].isVisited()))&&node[j].getPrec()>priority)
				{
					index=j;
					priority=node[j].getPrec();
				}
			}
			if (index>0)
			{
				node[index].setLeft(node[index-1]);
				node[index].setRight(node[index+1]);
				node[index].setVisited(true);
				node[index-1]=node[index];
				for(int j=index;j<i-2;j++)
					node[j]=node[j+2];
				i-=2;
			}		
			if(i==0||i==1)break;
		}		
		return node[0];
	}
		
	public void postOrder(Node nd)
	{
		if(nd.getLeft()!=null)
			postOrder(nd.getLeft());
		if(nd.getRight()!=null)	
			postOrder(nd.getRight());

		if (nd.isOperand())
		{
			stack.push(nd.getData());
		}
		else
		{				
			double d1=stack.pop();
			double d2=stack.pop();			
			if(nd.getOperator().equals("+"))
				d1=d1+d2;
			else if(nd.getOperator().equals("-"))
				d1=d2-d1;
			else if(nd.getOperator().equals("*"))
				d1=d1*d2;
			else if(nd.getOperator().equals("/"))
				d1=d2/d1;
			else if(nd.getOperator().equals("^"))
				d1=Math.pow(d2,d1);
			stack.push(d1);
		}
	}
}
