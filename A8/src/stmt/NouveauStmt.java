package stmt;

import dictionary.*;
import exp.*;
import model.*;

public class NouveauStmt implements IStmt
{
	String varName;
	Exp exp;
	static int freeLocation = 1;
	public NouveauStmt(String vN, Exp e)
	{
		this.varName = vN;
		this.exp = e;
	}
	@Override
	public PrgState execute(PrgState state) throws MyStmtException
	{
		MyIDictionary<String, Integer> symTable = state.getSymTable();
		int v = exp.eval(symTable, state.getHeap());
		if (symTable.isDefined(varName))
		{
			symTable.update(varName, freeLocation);
		}
		else
		{
			symTable.add(varName, freeLocation);
		}
		state.getHeap().add(freeLocation, v);
		freeLocation++;
		//return state;
		return null;
	}
	
	public String toString()
	{
		return "new(" + this.varName + ", " + this.exp.toString() + ")";
	}
}
