package view;
import model.*;
import repository.*;
import stack.*;
import stmt.*;
import controller.*;
import dictionary.*;
import exp.*;
import list.*;
public class View
{
	
	public static void main(String[] args) throws MyStmtException
	{
		MyIStack<IStmt> stk = new MyStack<IStmt>();
		MyIDictionary<String, Integer> symtbl = new MyDictionary<String, Integer> ();
		MyIList<Integer> ot = new MyList<Integer>() ;
		
		//v = 2; print(v)
		//IStmt ex = new CompStmt(new AssignStmt("v", new ConstExp(2)), new PrintStmt(new VarExp("v")));
		
		//a = 2 + 1; b = a/3; print(b)
		IStmt ex = new CompStmt (new CompStmt(new AssignStmt("a", new ArthExp('+', new ConstExp(2), new ConstExp(1))), new AssignStmt("b", new ArthExp('/', new VarExp("a"), new ConstExp(0)))), new PrintStmt(new VarExp("b")));
		
		//a=2-2;(If a Then v=2 Else v=3);Print(v)
		//IStmt ex = new CompStmt(new AssignStmt("a", new ArthExp('-',new ConstExp(2), new ConstExp(2))), new CompStmt(new IfStmt(new VarExp("a"),new AssignStmt("v",new ConstExp(2)), new AssignStmt("v", new ConstExp(3))), new PrintStmt(new VarExp("v"))));
		
		PrgState prg = new PrgState(stk, symtbl, ot, ex);
		Repository rep = new Repository();
		rep.add(prg);
		Controller ctrl = new Controller(rep);
		//ctrl.oneStep(prg);
		ctrl.allStep();
	}

}
