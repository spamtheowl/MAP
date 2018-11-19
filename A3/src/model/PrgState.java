package model;
import stack.*;
import stmt.IStmt;
import tuple.*;

import java.io.BufferedReader;

import dictionary.*;
import list.*;
public class PrgState
{
	private MyIStack<IStmt> exeStack;
	private MyIDictionary<String, Integer> symTable;
	private MyIList<Integer> out;
	private MyIDictionary<Integer, MyTuple<String, BufferedReader>> fileTable;
	//private int
	public PrgState(MyIStack<IStmt> stk, MyIDictionary<String, Integer> symtbl, MyIList<Integer> ot, MyIDictionary<Integer, MyTuple<String, BufferedReader>> fT, IStmt prg)
	{
		exeStack = stk;
		symTable = symtbl;
		out = ot;
		fileTable = fT;
		exeStack.push(prg);
	}
	public String toString()
	{
		return "Execution stack: " + exeStack.toString() + "\n" + "Symbol Table: " + symTable.toString() + "\n" + "Output: " + out.toString() + "\n" + "File Table:\n" + fileTable.toString() + "\n";
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
}
