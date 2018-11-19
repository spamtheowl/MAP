package model;
import stack.*;
import stmt.IStmt;
import dictionary.*;
import list.*;
public class PrgState
{
	private MyIStack<IStmt> exeStack;
	private MyIDictionary<String, Integer> symTable;
	private MyIList<Integer> out;
	
	public PrgState(MyIStack<IStmt> stk, MyIDictionary<String, Integer> symtbl, MyIList<Integer> ot, IStmt prg)
	{
		exeStack = stk;
		symTable = symtbl;
		out = ot;
		exeStack.push(prg);
	}
	public String toString()
	{
		return "Execution stack: " + exeStack.toString() + "\n" + "Symbol Table: " + symTable.toString() + "\n" + "Output: " + out.toString() + "\n";
	}
	public MyIStack<IStmt> getExeStack()
	{
		return this.exeStack;
	}
	public void setExeStack(MyIStack<IStmt> stk)
	{
		this.exeStack = stk;
	}
	public MyIDictionary<String, Integer> getSymTable()
	{
		return this.symTable;
	}
	public void setSymTable(MyIDictionary<String, Integer> symtbl)
	{
		this.symTable = symtbl;
	}
	public MyIList<Integer> getOut()
	{
		return this.out;
	}
	public void setOut(MyIList<Integer> ot)
	{
		this.out = ot;
	}
}
