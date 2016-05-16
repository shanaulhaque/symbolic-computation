package expression;

public class Node
{
	Node lchild,rchild;
	boolean is_Operand,visited;
	int priority;
	double data=0;
	String operator="";
	public Node()
	{
		lchild=null;
		rchild=null;	
		data=0;
		priority=0;
		is_Operand=true;	
		visited=false;	
	}
	public Node(Node l,Node r, String ddata,boolean operand)
	{
		if(operand)
			data=Double.parseDouble(ddata);
		else
		{
			operator=ddata;	
			char s=ddata.charAt(0);
			switch (s)
			{
				case '+':
					priority=2;
					break;
				case '-':	
					priority=1;
					break;
				case '/':	
					priority=4;
					break;
				case '*':	
					priority=3;
					break;
				case '^':	
					priority=5;
					break;
				default:		
					priority=0;
			}
		}
		lchild=l;
		rchild=r;
		this.is_Operand=operand;
		visited=false;	
	}

	public Node getLeft()
	{
		return lchild;
	}
	public Node getRight()
	{
		return rchild;
	}

	public void setLeft(Node node)
	{
		lchild=node;
	}
	public void setRight(Node node)
	{
		rchild=node;
	}

	public double getData()
	{
		return data;
	}

	public String getOperator()
	{
		return operator;
	}

	public void  setData(double o)
	{
		data=o;
	}
	public int getPrec()
	{
		return priority;
	}

	public void  setPrec(int i)
	{
		priority=i;
	}
	
	public boolean	isOperand()
	{
		return is_Operand;
	}
	public boolean isOperator()
	{
		return (!is_Operand);
	}
	public void setOperand(boolean b)
	{
		is_Operand=b;
	}

	public boolean isVisited()
	{
		return visited;
	}
	public void setVisited(boolean b)
	{
		visited=b;
	}
}
