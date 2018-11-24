package exp;
import dictionary.*;
import model.MyStmtException;
public class VarExp extends Exp
{
	String id;
	//more stuff to do
	public VarExp(String id)
	{
		this.id = id;
	}
	public int eval(MyIDictionary<String, Integer> tbl, MyIDictionary<Integer, Integer> h) throws MyStmtException
	{
		return tbl.lookup(this.id);
	}
	public String toString()
	{
		return id;
	}
}
