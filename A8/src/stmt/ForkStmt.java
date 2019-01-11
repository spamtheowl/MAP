package stmt;

import java.io.FileNotFoundException;
import java.io.IOException;

import dictionary.MyIDictionary;
import exp.*;
import model.*;
import stack.*;

public class ForkStmt implements IStmt
{
	IStmt state;
	public ForkStmt(IStmt state)
	{
		this.state = state;
	}
	@Override
	public PrgState execute(PrgState state) throws MyStmtException, FileNotFoundException, IOException
	{
		MyIStack<IStmt> stk = state.getExeStack();
		MyIDictionary<String, Integer> symTable = state.getSymTable();
		//IStmt newStmt = stk.pop();
		//stk.pop();
		int k = state.getId();
		MyIStack<IStmt> newStack = new MyStack<IStmt>();
		MyIDictionary<String, Integer> newSymTable = symTable.clone();
		PrgState newPrg = new PrgState(newStack, newSymTable, state.getOut(), state.getFileTable(), state.getHeap(), this.state, k);
		
		return newPrg;
	}
	public String toString()
	{
		return "fork(" + this.state + ")";
	}
}
