package stmt;

import java.io.FileNotFoundException;
import java.io.IOException;

import model.MyStmtException;
import model.PrgState;

public interface IStmt
{
	String toString();
	PrgState execute(PrgState state) throws MyStmtException, FileNotFoundException, IOException;
}
