package stmt;

import exp.PrgState;
import stack.*;

public class CompStmt implements IStmt
{
	IStmt first;
	IStmt snd;
	
	public CompStmt(IStmt f, IStmt s)
	{
		this.first = f;
		this.snd = s;
	}
	@Override
	public PrgState execute(PrgState state)
	{
		MyIStack<IStmt> stk = state.getExeStack();
		stk.push(snd);
		stk.push(first);
		return state;
	}
	
	public String toString()
	{
		return "(" + first.toString() + "; " + snd.toString() + ")";
	}
}
