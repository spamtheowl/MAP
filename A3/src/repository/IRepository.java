package repository;
import java.io.IOException;

import model.*;
public interface IRepository
{
	void add(PrgState prg);
	PrgState getCrtPrg();
	void logPrgStateExec() throws IOException;
}
