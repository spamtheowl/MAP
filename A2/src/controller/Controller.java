package controller;
import stack.*;
import stmt.IStmt;
import model.*;
import repository.*;

public class Controller
{
	private IRepository rep;
	public Controller(IRepository rep)
	{
		this.rep = rep;
	}
	
	public PrgState oneStep(PrgState state) throws MyStmtException
	{
		MyIStack<IStmt> stk = state.getExeStack();
		if (stk.isEmpty()) throw new MyStmtException("Stack is empty");
		IStmt crtStmt = stk.pop();
		return crtStmt.execute(state);
	}
	
	public void allStep()
	{
		PrgState prg = rep.getCrtPrg();
		try
		{
			while(true)
			{
				//oneStep(prg);
				System.out.println(oneStep(prg).toString());
			}
		}
		catch(MyStmtException e) {}
	}
}
