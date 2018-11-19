package repository;

import java.util.ArrayList;

import model.*;

public class Repository implements IRepository
{
	int i = 0;
	private ArrayList<PrgState> prgs;
	public Repository()
	{
		prgs = new ArrayList<PrgState>();
	}
	public void add(PrgState prg)
	{
		prgs.add(prg);
	}
	@Override
	public PrgState getCrtPrg()
	{
		return prgs.get(i);
	}

}
