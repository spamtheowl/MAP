package exp;
import stack.*;
import stmt.IStmt;
import tuple.*;

import java.io.BufferedReader;
import java.util.HashMap;

import dictionary.*;
import list.*;
public class PrgState
{
	private MyIStack<IStmt> exeStack;
	private MyIDictionary<String, Integer> symTable;
	private MyIList<Integer> out;
	private MyIDictionary<Integer, MyTuple<String, BufferedReader>> fileTable;
	private MyIDictionary<Integer, Integer> heap;
	public PrgState(MyIStack<IStmt> stk, MyIDictionary<String, Integer> symtbl, MyIList<Integer> ot, MyIDictionary<Integer, MyTuple<String, BufferedReader>> fT, MyIDictionary<Integer, Integer> h, IStmt prg)
	{
		exeStack = stk;
		symTable = symtbl;
		out = ot;
		fileTable = fT;
		heap = h;
		exeStack.push(prg);
	}
	public String toString()
	{
		return "Execution stack: " + exeStack.toString() + "\n" + "Symbol Table: " + symTable.toString() + "\n" + "Output: " + out.toString() + "\n" + "File Table:\n" + fileTable.toString() + "\n" + "Heap:\n" + heap.toString() + "\n";
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
	public MyIDictionary<Integer, MyTuple<String, BufferedReader>> getFileTable()
	{
		return this.fileTable;
	}
	public MyIDictionary<Integer, Integer> getHeap()
	{
		return this.heap;
	}
}
