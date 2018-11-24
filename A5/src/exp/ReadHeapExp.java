package exp;

import dictionary.*;
import model.*;
public class ReadHeapExp extends Exp
{
	String varName;
	public ReadHeapExp(String vN)
	{
		this.varName = vN;
	}
	@Override
	public int eval(MyIDictionary<String, Integer> tbl, MyIDictionary<Integer, Integer> h) throws MyStmtException
	{
		int v = tbl.lookup(varName);
		int content = h.lookup(v);
		return content;
	}

	@Override
	public String toString()
	{
		return "read(" + varName + ")";
	}

}
