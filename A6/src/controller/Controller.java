package controller;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import dictionary.*;

import stack.*;
import stmt.*;
import tuple.MyITuple;
import tuple.MyTuple;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import exp.PrgState;
import model.*;
import repository.*;

public class Controller
{
	private IRepository rep;
	ExecutorService executor;
	public Controller(IRepository rep)
	{
		this.rep = rep;
	}
	
	public Map<Integer, Integer> conservativeGarbageCollector(Collection<Integer> symTableVals, Map<Integer, Integer> heap)
	{
		return heap.entrySet().stream().filter(e->symTableVals.contains(e.getKey())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}
	
	public void closeAllFiles(PrgState prg) throws MyStmtException
	{
		MyIDictionary<Integer, MyTuple<String, BufferedReader>> fT = prg.getFileTable();
		for (Integer entry: fT.keySet())
		{
			fT.delete(entry, fT.lookup(entry));
		}
	}
	public ArrayList<PrgState> removeCompletedPrg(ArrayList<PrgState> inPrgList)
	{
		return (ArrayList<PrgState>)inPrgList.stream()
				.filter(p->p.isNotCompleted())
				.collect(Collectors.toList());
	}
	
	void oneStepForAllPrg(List<PrgState> prgList) throws InterruptedException
	{
		prgList.forEach(prg->{
			try
			{
				rep.logPrgStateExec(prg);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		});
		List<Callable<PrgState>> callList = prgList.stream().map((PrgState p) -> (Callable<PrgState>)(()-> { return p.oneStep(); }))
				.collect(Collectors.toList());
		List<PrgState> newPrgList = executor.invokeAll(callList).stream()
				.map(future->{
					try
					{
						return future.get();
					}
					catch(Exception e)
					{
						System.out.println(e.getMessage());
						return null;
					}
				})
				.filter(p->p!=null).collect(Collectors.toList());
		prgList.addAll(newPrgList);
		prgList.forEach(prg->{
			try
			{
				rep.logPrgStateExec(prg);
			}
			catch (IOException e)
			{
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		});
		rep.setPrgList(prgList);
	}
	public void allStep() throws InterruptedException, MyStmtException
	{
		executor = Executors.newFixedThreadPool(2);
		//List<PrgState> prgList = removeCompletedPrg(rep.getPrgList());
		List<PrgState> prgList = rep.getPrgList();
		while(prgList.size() > 0)
		{
			prgList.forEach(prg->prg.getHeap().setContent(conservativeGarbageCollector(prg.getSymTable().getContent().values(), prg.getHeap().getContent())));
			oneStepForAllPrg(prgList);
			prgList = removeCompletedPrg(rep.getPrgList());
		}
		executor.shutdownNow();
		prgList.forEach(prg->{
			try
			{
				closeAllFiles(prg);
			}
			catch (MyStmtException e)
			{
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		});
		rep.setPrgList(prgList);
	}
}
