package stmt;

import model.MyStmtException;
import tuple.MyITuple;
import tuple.MyTuple;

import java.io.BufferedReader;

import exp.*;
public class closeRFile implements IStmt
{
	Exp exp_file_id;
	public closeRFile(Exp e)
	{
		exp_file_id = e;
	}
	@Override
	public PrgState execute(PrgState state) throws MyStmtException
	{
		//state.getExeStack().pop();
		try
		{
			int val = exp_file_id.eval(state.getSymTable(), state.getHeap());//what to eval
			MyTuple<String, BufferedReader> entry = state.getFileTable().lookup(val);
			entry.getSecond().close();
			state.getFileTable().delete(val, entry);
		}
		catch(Exception e)
		{
			throw new MyStmtException("Something went wrong!");
		}
		//return state;
		return null;
	}
	
	public String toString()
	{
		return "closeRFile(" + exp_file_id.toString() + ")";
	}

}
