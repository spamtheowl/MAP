package exp;
import stack.*;
import stmt.IStmt;
import tuple.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import dictionary.*;
import list.*;
import model.MyStmtException;
public class PrgState
{
	private int id = 1;
	private MyIStack<IStmt> exeStack;
	private MyIDictionary<String, Integer> symTable;
	private MyIList<Integer> out;
	private MyIDictionary<Integer, MyTuple<String, BufferedReader>> fileTable;
	private MyIDictionary<Integer, Integer> heap;
	private MyIDictionary<Integer, MyITuple<Integer, ArrayList<Integer>>> barTable;
	public PrgState(MyIStack<IStmt> stk, MyIDictionary<String, Integer> symtbl, MyIList<Integer> ot, MyIDictionary<Integer, MyTuple<String, BufferedReader>> fT, MyIDictionary<Integer, Integer> h, MyIDictionary<Integer, MyITuple<Integer, ArrayList<Integer>>>  b, IStmt prg, int i)
	{
		this.id = i + 1;
		exeStack = stk;
		symTable = symtbl;
		out = ot;
		fileTable = fT;
		heap = h;
		barTable = b;
		exeStack.push(prg);
	}
	public int getId()
	{
		return this.id;
	}
	public String toString()
	{
		return "Id: " + this.id + "\n\nExecution stack: " + exeStack.toString() + "\n" + "Symbol Table: " + symTable.toString() + "\n" + "Output: " +
	out.toString() + "\n" + "File Table:\n" + fileTable.toString() + "\n" + "Heap:" + heap.toString() + "Barrier Table: " + barTable.toString() + "\n";
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
	public MyIDictionary<Integer, MyITuple<Integer, ArrayList<Integer>>> getBarTable()
	{
		return this.barTable;
	}
	public boolean isNotCompleted()
	{
		if (this.exeStack.isEmpty()==true)
			return false;
		return true;
	}
	public PrgState oneStep() throws MyStmtException, FileNotFoundException, IOException
	{
		if (exeStack.isEmpty()) throw new MyStmtException("Stack is empty!");
		IStmt crtStmt = exeStack.pop();
		return crtStmt.execute(this);
	}
}
