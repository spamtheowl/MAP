package stmt;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import dictionary.MyIDictionary;
import exp.PrgState;
import model.MyStmtException;
import stack.MyIStack;
import tuple.MyITuple;

public class AwaitStmt implements IStmt
{
	String var;

	public AwaitStmt(String v)
	{
		var = v;
	}

	public String toString()
	{
		return "await("+ var +")";
	}
	@Override
	public PrgState execute(PrgState state) throws MyStmtException, FileNotFoundException, IOException
	{
		/*- foundIndex=lookup(SymTable,var). If var is not in SymTable print an error message and terminate the execution.
		- if foundIndex is not an index in the BarrierTable then print an error message and terminate the execution
else
- retrieve the entry for that foundIndex, as BarrierTable[foundIndex]==
(N1,List1)
- compute the length of that list List1 as NL=length(L1)
- if (N1>NL) then
if(the identifier of the current PrgState is in L1) then
- push back await(var) on the ExeStack
else
- add the id of the current PrgState to L1
- push back await(var) on the ExeStack
else do nothing
Note that the lookup and the update of the BarrierTable must be an atomic operation, that means they cannot be interrupted by the execution of the other
PrgStates. Therefore you must use the lock mechanisms of the host language Java over the BarrierTable in order to read and write the values of the
BarrierTable entrances .
		 */
		MyIDictionary<String, Integer> symTbl = state.getSymTable();
		MyIStack<IStmt> stk = state.getExeStack();
		MyIDictionary<Integer, MyITuple<Integer, ArrayList<Integer>>> barTable = state.getBarTable();
		try
		{
			int foundIndex = symTbl.lookup(var);
			if (foundIndex < 0)
			{
				throw new MyStmtException("Var not found in Symbol Table");
			}
			else
			{
				synchronized(this)
				{
					MyITuple<Integer, ArrayList<Integer>> entry = barTable.lookup(foundIndex);
					int n1 = entry.getFirst();
					int nl = entry.getSecond().size();
					if (n1 - 1 > nl)
					{
						if(entry.getSecond().contains(state.getId()) == true)
						{
							stk.push(new AwaitStmt(var));
						}
						else
						{
							entry.getSecond().add(state.getId());
							stk.push(new AwaitStmt(var));
						}
					}
				}
			}
		}
		catch(MyStmtException e)
		{
			System.out.println(e.getMessage());
		}
		return null;
	}
}
