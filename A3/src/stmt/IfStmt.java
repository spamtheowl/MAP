package stmt;
import stack.*;
import dictionary.*;
import exp.Exp;
import model.MyStmtException;
import model.PrgState;

public class IfStmt implements IStmt
{
	Exp exp;
	IStmt thenS;
	IStmt elseS;
	public IfStmt (Exp e, IStmt t, IStmt el)
	{
		exp = e;
		thenS = t;
		elseS = el;
	}
	public String toString()
	{
		return "IF (" + exp.toString() + ") THEN (" + thenS.toString() + ") ELSE (" + elseS.toString() + ")";
	}
	public PrgState execute(PrgState state) throws MyStmtException
	{
		MyIStack<IStmt> stk = state.getExeStack();
		MyIDictionary<String, Integer> symtbl = state.getSymTable();
		if (exp.eval(symtbl) != 0)
		{
			stk.push(thenS);
		}
		else
		{
			stk.push(elseS);
		}
		return state;
	}
}
