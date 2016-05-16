package semat2.parse;

import java.util.Scanner;

public class TextMath {

	static String inp;
	private static Scanner in;
	public static void main(String[] args) {
		in = new Scanner(System.in);
		inp=in.next();
		Parser p=new Parser(inp);
		try
		{
			String str=p.parse();
			System.out.println(str);
		}
		catch(Exception E)
		{
			System.out.println("Error");
		}
	}

}
