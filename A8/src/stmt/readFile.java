package stmt;

import model.MyStmtException;
import tuple.MyITuple;
import dictionary.MyDictionary;
import dictionary.MyIDictionary;
import exp.*;
import java.io.*;

public class readFile implements IStmt
{
	Exp exp_file_id;
	String var_name;
	
	public readFile (Exp exp, String var_name)
	{
		this.exp_file_id = exp;
		this.var_name = var_name;
	}
	
	public PrgState execute(PrgState state) throws MyStmtException, IOException
	{
		MyIDictionary<String, Integer> symTable = state.getSymTable();
		int file_id = this.exp_file_id.eval(symTable, state.getHeap());
		MyITuple<String, BufferedReader> fileTable = state.getFileTable().lookup(file_id);
		if (fileTable == null)
		{
			throw new MyStmtException("File not opened");
		}
		int anotherVal;
		String line = fileTable.getSecond().readLine();
		if (line == null)
		{
			anotherVal = 0;
		}
		else
		{
			anotherVal = Integer.parseInt(line);
		}
		if (symTable.isDefined(var_name))
		{
			symTable.update(var_name, anotherVal);
		}
		else
		{
			symTable.add(var_name, anotherVal);
		}
		//return state;
		return null;
	}
	
	public String toString()
	{
		return "readFile(" + exp_file_id + ", \"" + var_name + "\")";
	}
}
