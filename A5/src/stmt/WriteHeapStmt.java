package stmt;

import dictionary.*;
import exp.*;
import model.*;

public class WriteHeapStmt implements IStmt
{
	String varName; //heap address
	Exp exp;
	public WriteHeapStmt(String vN, Exp e)
	{
		this.varName = vN;
		this.exp = e;
	}
	public PrgState execute(PrgState state) throws MyStmtException
	{
		MyIDictionary<String, Integer> symTbl = state.getSymTable();
		MyIDictionary<Integer, Integer> heap = state.getHeap();
		int address = symTbl.lookup(varName);
		int v = exp.eval(symTbl, heap);
		if (heap.lookup(address) != null)
		{
			heap.update(address, v);
		}
		else
		{
			throw new MyStmtException("Invalid address!");
		}
		return state;
	}
	public String toString()
	{
		return "write(" + varName +", " + exp.toString() + ")";
	}
}
