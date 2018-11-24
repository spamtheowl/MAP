package controller;
import java.util.*;
import java.util.stream.Collectors;

import dictionary.*;

import stack.*;
import stmt.*;
import tuple.MyITuple;
import tuple.MyTuple;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import exp.PrgState;
import model.*;
import repository.*;

public class Controller
{
	private IRepository rep;
	public Controller(IRepository rep)
	{
		this.rep = rep;
	}
	
	public Map<Integer, Integer> conservativeGarbageCollector(Collection<Integer> symTableVals, Map<Integer, Integer> heap)
	{
		return heap.entrySet().stream().filter(e->symTableVals.contains(e.getKey())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}
	
	public void closeAllFiles(PrgState state) throws MyStmtException
	{
		MyIDictionary<Integer, MyTuple<String, BufferedReader>> fT = state.getFileTable();
		for (Integer entry: fT.keySet())
		{
			fT.delete(entry, fT.lookup(entry));
		}
	}
	
	public PrgState oneStep(PrgState state) throws MyStmtException, FileNotFoundException, IOException
	{
		MyIStack<IStmt> stk = state.getExeStack();
		if (stk.isEmpty()) throw new MyStmtException("Stack is empty");
		IStmt crtStmt = stk.pop();
		return crtStmt.execute(state);
	}
	
	public void allStep() throws IOException, MyStmtException
	{
		PrgState prg = rep.getCrtPrg();
		try
		{
			while(true)
			{
				oneStep(prg);
				//System.out.println(oneStep(prg).toString());
				prg.getHeap().setContent(conservativeGarbageCollector(prg.getSymTable().getContent().values(), prg.getHeap().getContent()));
				rep.logPrgStateExec();
			}
		}
		catch(MyStmtException e)
		{
			System.out.println(e.getMessage());
		}
		finally
		{
			closeAllFiles(prg);
			rep.logPrgStateExec();
		}
	}
}
