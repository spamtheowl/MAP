package exp;

import dictionary.*;
import model.*;

public class DiffExp extends Exp
{
	Exp exp1;
	Exp exp2;
	public DiffExp(Exp exp1, Exp exp2)
	{
		this.exp1 = exp1;
		this.exp2 = exp2;
	}
	public int eval(MyIDictionary<String, Integer> tbl, MyIDictionary<Integer, Integer> h) throws MyStmtException
	{
		if(exp1.eval(tbl, h) != exp2.eval(tbl, h))
		{
			return 1;
		}
		return 0;
	}
	public String toString()
	{
		return exp1.toString() + " != " + exp2.toString();
	}
}
