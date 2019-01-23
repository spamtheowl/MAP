package stmt;

import java.io.FileNotFoundException;
import java.io.IOException;

import exp.*;
import model.MyStmtException;
import stack.MyIStack;

public class SwitchStmt implements IStmt
{
	Exp exp;
	Exp exp1;
	Exp exp2;
	IStmt stmt1;
	IStmt stmt2;
	IStmt stmt3;
	public SwitchStmt (Exp e, Exp e1, IStmt s1, Exp e2,  IStmt s2, IStmt s3)
	{
		exp = e;
		exp1 = e1;
		stmt1 = s1;
		exp2 = e2;
		stmt2 = s2;
		stmt3 = s3;
	}
	public String toString()
	{
		//switch(exp) (case exp1: stmt1) (case exp2: stmt2) (default: stmt3)
		return "switch(" + exp.toString() + ") (case " + exp1.toString() + ": " + stmt1.toString() +
				") (case" + exp2.toString() + ": " + stmt2.toString() + ") (default: " + stmt3.toString() + ")";
	}
	@Override
	public PrgState execute(PrgState state) throws MyStmtException, FileNotFoundException, IOException
	{
		MyIStack<IStmt> stk = state.getExeStack();
		Exp cond1 = new EqualsExp(exp, exp1);
		Exp cond2 = new EqualsExp(exp, exp2);
		IStmt elseS = new IfStmt(cond2, stmt2, stmt3);
		IStmt ifS = new IfStmt(cond1, stmt1, elseS);
		stk.push(ifS);
		return null;
	}
}
