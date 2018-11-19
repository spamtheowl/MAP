package controller;
import stack.*;
import stmt.IStmt;

import java.io.FileNotFoundException;
import java.io.IOException;

import model.*;
import repository.*;

public class Controller
{
	private IRepository rep;
	public Controller(IRepository rep)
	{
		this.rep = rep;
	}
	
	public PrgState oneStep(PrgState state) throws MyStmtException, FileNotFoundException, IOException
	{
		MyIStack<IStmt> stk = state.getExeStack();
		if (stk.isEmpty()) throw new MyStmtException("Stack is empty");
		IStmt crtStmt = stk.pop();
		return crtStmt.execute(state);
	}
	
	public void allStep() throws IOException
	{
		PrgState prg = rep.getCrtPrg();
		try
		{
			while(true)
			{
				oneStep(prg);
				//System.out.println(oneStep(prg).toString());
				rep.logPrgStateExec();
			}
		}
		catch(MyStmtException e) {}
	}
}
