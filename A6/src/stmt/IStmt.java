package stmt;

import java.io.FileNotFoundException;
import java.io.IOException;

import exp.PrgState;
import model.MyStmtException;

public interface IStmt
{
	String toString();
	PrgState execute(PrgState state) throws MyStmtException, FileNotFoundException, IOException;
}
