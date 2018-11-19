package stmt;

import model.MyStmtException;
import model.PrgState;

public interface IStmt
{
	String toString();
	PrgState execute(PrgState state) throws MyStmtException;
}
