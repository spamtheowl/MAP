package exp;
import dictionary.*;

public class ConstExp extends Exp
{
	int number;
	public ConstExp(int no)
	{
		this.number = no;
	}
	public int eval (MyIDictionary<String, Integer> tbl, MyIDictionary<Integer, Integer> h)
	{
		return number;
	}
	public String toString()
	{
		return "" + number;
	}
}
