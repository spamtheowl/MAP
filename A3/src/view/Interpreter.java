package view;

import java.io.BufferedReader;
import java.io.IOException;

import controller.*;
import dictionary.*;
import exp.*;
import list.*;
import model.*;
import mvc.*;
import stmt.*;
import tuple.MyTuple;
import repository.*;
import stack.*;

public class Interpreter
{
	private static PrgState createPrg(IStmt ex) {
		MyIStack<IStmt> stk = new MyStack<IStmt>();
		MyIDictionary<String, Integer> symtbl = new MyDictionary<String, Integer> ();
		MyIList<Integer> ot = new MyList<Integer>() ;
		MyIDictionary<Integer, MyTuple<String, BufferedReader>> fT = new MyDictionary<Integer, MyTuple<String, BufferedReader>>();
		PrgState prg = new PrgState(stk, symtbl, ot, fT, ex);
		return prg;
	}
	
	public static void main(String[] args) throws IOException, MyStmtException
	{
		
		
		IStmt ex1 = new CompStmt(new AssignStmt("v", new ConstExp(2)), new PrintStmt(new VarExp("v")));;
		PrgState prg1 = createPrg(ex1);
		IRepository rep1 = new Repository(prg1, "log1.txt");
		Controller ctrl1 = new Controller(rep1);
		
		IStmt ex2 = new CompStmt (new CompStmt(new AssignStmt("a", new ArthExp('+', new ConstExp(2), new ConstExp(1))), new AssignStmt("b", new ArthExp('/', new VarExp("a"), new ConstExp(0)))), new PrintStmt(new VarExp("b")));
		PrgState prg2 = createPrg(ex2);
		IRepository rep2 = new Repository(prg2, "log2.txt");
		Controller ctrl2 = new Controller(rep2);
		
		IStmt ex3 = new CompStmt(new AssignStmt("a", new ArthExp('-',new ConstExp(2), new ConstExp(2))), new CompStmt(new IfStmt(new VarExp("a"),new AssignStmt("v",new ConstExp(2)), new AssignStmt("v", new ConstExp(3))), new PrintStmt(new VarExp("v"))));
		PrgState prg3 = createPrg(ex3);
		IRepository rep3 = new Repository(prg3, "log3.txt");
		Controller ctrl3 = new Controller(rep3);
		
		/*openRFile(var_f,"test.in");
        readFile(var_f,var_c);print(var_c);
        (if var_c then readFile(var_f,var_c);print(var_c)
        else print(0));
        closeRFile(var_f)*/
		IStmt ex4 = new CompStmt(new openRFile("var_f", "test.in"), new CompStmt(new readFile(new VarExp("var_f"), "var_c"),
				new CompStmt(new PrintStmt(new VarExp("var_c")), new CompStmt(new IfStmt(new VarExp("var_c"),
						new CompStmt(new readFile(new VarExp("var_f"), "var_c"), new PrintStmt(new VarExp("var_c"))),
						new PrintStmt(new ConstExp(0))), new closeRFile(new VarExp("var_f"))))));
		PrgState prg4 = createPrg(ex4);
		IRepository rep4 = new Repository(prg4, "log4.txt");
		Controller ctrl4 = new Controller(rep4);
		
		/*
		 compst:
		 	openRFile(var_f,"test.in");
        	compst
        		readFile(var_f,var_c);
        		compst
        			print(var_c);
        			compstmt
        				(if var_c then 
        					compst
        						readFile(var_f,var_c);
        						print(var_c)
        				else print(0));
        closeRFile(var_f)
        closeRFile(var_f)*/
		
		IStmt ex5 = new CompStmt(new openRFile("var_f", "test.in"), new CompStmt(new readFile(new VarExp("var_f"), "var_c"),
				new CompStmt(new PrintStmt(new VarExp("var_c")), new CompStmt(new IfStmt(new VarExp("var_c"),
						new CompStmt(new readFile(new VarExp("var_f"), "var_c"), new PrintStmt(new VarExp("var_c"))),
						new PrintStmt(new ConstExp(0))), new CompStmt (new closeRFile(new VarExp("var_f")), new closeRFile(new VarExp("var_f")))))));
		PrgState prg5 = createPrg(ex5);
		IRepository rep5 = new Repository(prg5, "log5.txt");
		Controller ctrl5 = new Controller(rep5);
		
		/*
		 compst:
		 	compst
		 		openRFile(var_f,"test.in");
		 		openRFile(var_f,"test.in");
       		compst
       			readFile(var_f,var_c);
       			compst
       				print(var_c);
       				compstmt
       					(if var_c then 
       						compst
       							readFile(var_f,var_c);
       							print(var_c)
       					else print(0));
       		closeRFile(var_f)*/
		
		IStmt ex6 = new CompStmt(new CompStmt(new openRFile("var_f", "test.in"), new openRFile("var_f", "test.in")), new CompStmt(new readFile(new VarExp("var_f"), "var_c"),
				new CompStmt(new PrintStmt(new VarExp("var_c")), new CompStmt(new IfStmt(new VarExp("var_c"),
						new CompStmt(new readFile(new VarExp("var_f"), "var_c"), new PrintStmt(new VarExp("var_c"))),
						new PrintStmt(new ConstExp(0))), new CompStmt (new closeRFile(new VarExp("var_f")), new closeRFile(new VarExp("var_f")))))));
		PrgState prg6 = createPrg(ex6);
		IRepository rep6 = new Repository(prg6, "log6.txt");
		Controller ctrl6 = new Controller(rep6);
		
		TextMenu menu = new TextMenu();
		menu.addCommand(new ExitCommand("0", "exit"));
		menu.addCommand(new RunExample("1", ex1.toString(), ctrl1));
		menu.addCommand(new RunExample("2", ex2.toString(), ctrl2));
		menu.addCommand(new RunExample("3", ex3.toString(), ctrl3));
		menu.addCommand(new RunExample("4", ex4.toString(), ctrl4));
		menu.addCommand(new RunExample("5", ex5.toString(), ctrl5));
		menu.addCommand(new RunExample("6", ex6.toString(), ctrl6));
		menu.show();
	}
}
