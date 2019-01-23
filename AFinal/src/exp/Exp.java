package exp;
import dictionary.*;
import model.MyStmtException;

public abstract class Exp
{
	public abstract int eval(MyIDictionary<String, Integer> tbl, MyIDictionary<Integer, Integer> h) throws MyStmtException;
	public abstract String toString();
}
