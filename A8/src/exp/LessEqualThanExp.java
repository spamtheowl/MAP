package exp;

import dictionary.MyIDictionary;
import model.MyStmtException;

public class LessEqualThanExp extends Exp
{
	Exp exp1;
	Exp exp2;
	public LessEqualThanExp(Exp e1, Exp e2)
	{
		exp1 = e1;
		exp2 = e2;
	}
	@Override
	public int eval(MyIDictionary<String, Integer> tbl, MyIDictionary<Integer, Integer> h) throws MyStmtException
	{
		if(exp1.eval(tbl, h) <= exp2.eval(tbl, h))
		{
			return 1;
		}
		return 0;
	}

	@Override
	public String toString()
	{
		return exp1.toString() + " <= " + exp2.toString();
	}

}
