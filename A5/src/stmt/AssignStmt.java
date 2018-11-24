package stmt;
import dictionary.*;
import exp.Exp;
import exp.PrgState;
import model.MyStmtException;
import stack.*;

public class AssignStmt implements IStmt
{
	String id;
	Exp exp;
	
	public AssignStmt (String id, Exp exp)
	{
		this.id = id;
		this.exp = exp;
	}
	public String toString()
	{
		return id + "=" + exp.toString();
	}
	public PrgState execute(PrgState state) throws MyStmtException
	{
		MyIStack<IStmt> stk = state.getExeStack();
		MyIDictionary<String, Integer> symTbl = state.getSymTable();
		int val = exp.eval(symTbl, state.getHeap());
		if (symTbl.isDefined(id))
		{
			symTbl.update(id, val);
		}
		else
		{
			symTbl.add(id, val);
		}
		return state;
	}
}
