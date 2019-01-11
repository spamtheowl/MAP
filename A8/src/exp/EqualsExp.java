package exp;

import dictionary.MyIDictionary;
import model.MyStmtException;

public class EqualsExp extends Exp
{
	Exp exp1;
	Exp exp2;
	public EqualsExp(Exp exp1, Exp exp2)
	{
		this.exp1 = exp1;
		this.exp2 = exp2;
	}
	@Override
	public int eval(MyIDictionary<String, Integer> tbl, MyIDictionary<Integer, Integer> h) throws MyStmtException
	{
		if(exp1.eval(tbl, h) == exp1.eval(tbl, h))
		{
			return 1;
		}
		return 0;
	}

	@Override
	public String toString()
	{
		return exp1.toString() + " == " + exp2.toString();
	}

}
