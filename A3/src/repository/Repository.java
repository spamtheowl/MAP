package repository;

import java.io.*;
import java.util.ArrayList;

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
	@Override
	public PrgState getCrtPrg()
	{
		return prgs.get(prgs.size() - 1);
	}
	
	public void logPrgStateExec() throws IOException
	{
		logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
		try
		{
			logFile.write(prgs.get(0).toString());
		}
		catch (Exception e)
		{
			System.out.println("Error with file");
		}
		logFile.close(); //!!!very important line
	}

}
