package stmt;

import exp.Exp;
import list.*;
import dictionary.*;
import model.*;

public class PrintStmt implements IStmt
{
	Exp exp;
	public PrintStmt(Exp exp)
	{
		this.exp = exp;
	}
	public PrgState execute(PrgState state) throws MyStmtException
	{
		MyIList<Integer> ot = state.getOut();
		MyIDictionary<String, Integer> symtbl = state.getSymTable();
		int val = exp.eval(symtbl);
		ot.add(val);
		return state;
	}
	public String toString()
	{
		return "print (" + exp.toString() + ")";
	}
}
