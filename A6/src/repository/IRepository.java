package repository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import exp.PrgState;
import model.*;
public interface IRepository
{
	void add(PrgState prg);
	ArrayList<PrgState> getPrgList();
	void setPrgList(List<PrgState> prgList);
	//PrgState getCrtPrg();
	void logPrgStateExec(PrgState prg) throws IOException;
}
