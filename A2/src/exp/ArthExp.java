package exp;
import dictionary.*;
import model.MyStmtException;
public class ArthExp extends Exp
{
	Exp e1;
	Exp e2;
	char op;
	public ArthExp(char c, Exp e1, Exp e2)
	{
		this.op = c;
		this.e1 = e1;
		this.e2 = e2;
	}
	public int eval (MyIDictionary<String, Integer> tbl) throws MyStmtException
	{
		switch(op)
		{
		case ('+'):
			return e1.eval(tbl) + e2.eval(tbl);
		case ('-'):
			return e1.eval(tbl) - e2.eval(tbl);
		case ('*'):
			return e1.eval(tbl) * e2.eval(tbl);
		case ('/'):
			if(e2.eval(tbl) == 0)
			{
				throw new MyStmtException("Division with zero!");
			}
			else
			{
				return e1.eval(tbl) / e2.eval(tbl);
			}
		
		default: //exception@division w/ 0
			if(e2.eval(tbl) == 0)
			{
				throw new MyStmtException("Division with zero!");
			}
			else
			{
				return e1.eval(tbl) % e2.eval(tbl);
			}
		}
	}
	@Override
	public String toString()
	{
		return e1.toString() + op + e2.toString() + " = " + "eval(??)";
	}
}
