package stmt;
import tuple.*;
import java.io.*;

import dictionary.MyIDictionary;
import exp.PrgState;
import model.*;

public class openRFile implements IStmt
{
	private String var_file_id;
	private String filename;
	static int ran;
	
	public openRFile(String vfi, String filename)
	{
		this.var_file_id = vfi;
		this.filename = filename;
		ran = 1;
	}
	@Override
	public PrgState execute(PrgState state) throws MyStmtException, FileNotFoundException
	{
		MyIDictionary<Integer, MyTuple<String, BufferedReader>> fileTable = state.getFileTable();
		for (MyITuple<String, BufferedReader> ff: fileTable.values())
		{
			System.out.println("here?");
			if(ff.getFirst().equals(this.filename))
			{
				throw new MyStmtException("File already used");
			}
		}
		
		MyIDictionary<String, Integer> symTable = state.getSymTable();
		BufferedReader bufferedReader = new BufferedReader(new FileReader(this.filename));
		int unq = ran++;
		MyTuple<String, BufferedReader> tup = new MyTuple<String, BufferedReader>(this.filename, bufferedReader);
		fileTable.add(unq, tup);
		System.out.println("here??");
		symTable.add(var_file_id, unq);
		return state;
	}
	
	public String toString()
	{
		return "openRFile(" + var_file_id + ", \"" + filename + "\")";
	}
}