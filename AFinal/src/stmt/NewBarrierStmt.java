package stmt;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import dictionary.MyIDictionary;
import exp.*;
import model.MyStmtException;
import tuple.MyITuple;
import tuple.MyTuple;

public class NewBarrierStmt implements IStmt
{
	String var;
	Exp exp;
	static int unq;

	public NewBarrierStmt(String v, Exp e)
	{
		var = v;
		exp = e;
		unq = 1;
	}

	public String toString()
	{
		return "newBarrier(" + var + ", " + exp.toString() + ")";
	}
	@Override
	public PrgState execute(PrgState state) throws MyStmtException, FileNotFoundException, IOException
	{
		ArrayList<Integer>list = new ArrayList<Integer>();
		MyIDictionary<Integer, Integer> h = state.getHeap();
		MyIDictionary<String, Integer> symTbl = state.getSymTable();
		MyIDictionary<Integer, MyITuple<Integer, ArrayList<Integer>>> barTable = state.getBarTable();
		int number = exp.eval(symTbl, h);
		MyITuple<Integer, ArrayList<Integer>> tup = new MyTuple<Integer, ArrayList<Integer>>(number, list);
		synchronized(this)
		{
			//System.out.println("here?");
			barTable.add(unq, tup);
			//System.out.println("here?");
			if (symTbl.isDefined(var))
			{
				symTbl.update(var, unq); //??
			}
			else
			{
				symTbl.add(var, unq);
			}
			unq++;
		}
		return null;
	}

}
