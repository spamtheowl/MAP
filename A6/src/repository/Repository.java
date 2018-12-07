package repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import exp.PrgState;
import model.*;

public class Repository implements IRepository
{
	int i = 0;
	private ArrayList<PrgState> prgs;
	String logFilePath;
	PrintWriter logFile;
	public Repository(PrgState prg, String lFP)
	{
		prgs = new ArrayList<PrgState>();
		prgs.add(prg);
		logFilePath = lFP;
	}
	public void add(PrgState prg)
	{
		prgs.add(prg);
	}
	
	public void logPrgStateExec(PrgState prg) throws IOException
	{
		logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
		try
		{
			logFile.write(prg.toString());
		}
		catch (Exception e)
		{
			System.out.println("Error with file");
		}
		logFile.close(); //!!!very important line
	}
	@Override
	public ArrayList<PrgState> getPrgList()
	{
		return this.prgs;
	}
	@Override
	public void setPrgList(List<PrgState> p)
	{
		this.prgs = (ArrayList<PrgState>) p;
	}

}
