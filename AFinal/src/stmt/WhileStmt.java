package stmt;

import exp.*;
import model.*;
import dictionary.*;
import stack.*;

public class WhileStmt implements IStmt
{
	Exp exp;
	IStmt s;
	public WhileStmt(Exp exp, IStmt s)
	{
		this.exp = exp;
		this.s = s;
	}
	@Override
	public PrgState execute(PrgState state) throws MyStmtException
	{
		MyIStack<IStmt> stk = state.getExeStack();
		MyIDictionary<String, Integer> symTbl = state.getSymTable();
		MyIDictionary<Integer, Integer> heap = state.getHeap();
		int cond = exp.eval(symTbl, heap);
		if (cond == 0)
		{
			//stk.pop();
		}
		else
		{
			stk.push(new WhileStmt(exp, s));
			stk.push(s);
		}
		//return state;
		return null;
	}
	public String toString()
	{
		return "(while(" + this.exp.toString() + ")" + s.toString() + ")";
	}
}
