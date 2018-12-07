package view;

import java.io.BufferedReader;
import java.io.IOException;

import command.*;
import controller.*;
import dictionary.*;
import exp.*;
import list.*;
import model.*;
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
		MyIDictionary<Integer, Integer> heap = new MyDictionary<Integer, Integer>();
		PrgState prg = new PrgState(stk, symtbl, ot, fT, heap, ex, 0);
		return prg;
	}
	
	public static void main(String[] args) throws IOException, MyStmtException, InterruptedException
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
		
		/*openRFile(var_f,"test.in"); readFile(var_f,var_c);print(var_c);
        (if var_c then readFile(var_f,var_c);print(var_c) else print(0)); closeRFile(var_f)*/
		IStmt ex4 = new CompStmt(new openRFile("var_f", "test.in"), new CompStmt(new readFile(new VarExp("var_f"), "var_c"),
				new CompStmt(new PrintStmt(new VarExp("var_c")), new CompStmt(new IfStmt(new VarExp("var_c"),
						new CompStmt(new readFile(new VarExp("var_f"), "var_c"), new PrintStmt(new VarExp("var_c"))),
						new PrintStmt(new ConstExp(0))), new closeRFile(new VarExp("var_f"))))));
		PrgState prg4 = createPrg(ex4);
		IRepository rep4 = new Repository(prg4, "log4.txt");
		Controller ctrl4 = new Controller(rep4);
		
		/* openRFile(var_f,"test.in"); readFile(var_f,var_c); print(var_c); (if var_c then readFile(var_f,var_c); print(var_c)
		 * else print(0)); closeRFile(var_f) closeRFile(var_f)*/
		
		IStmt ex5 = new CompStmt(new openRFile("var_f", "test.in"), new CompStmt(new readFile(new VarExp("var_f"), "var_c"),
				new CompStmt(new PrintStmt(new VarExp("var_c")), new CompStmt(new IfStmt(new VarExp("var_c"),
						new CompStmt(new readFile(new VarExp("var_f"), "var_c"), new PrintStmt(new VarExp("var_c"))),
						new PrintStmt(new ConstExp(0))), new CompStmt (new closeRFile(new VarExp("var_f")), new closeRFile(new VarExp("var_f")))))));
		PrgState prg5 = createPrg(ex5);
		IRepository rep5 = new Repository(prg5, "log5.txt");
		Controller ctrl5 = new Controller(rep5);
		
		/* openRFile(var_f,"test.in"); openRFile(var_f,"test.in"); readFile(var_f,var_c); print(var_c);
		 * (if var_c then readFile(var_f,var_c); print(var_c) else print(0));
		 * closeRFile(var_f)*/
		
		IStmt ex6 = new CompStmt(new CompStmt(new openRFile("var_f", "test.in"), new openRFile("var_f", "test.in")), new CompStmt(new readFile(new VarExp("var_f"), "var_c"),
				new CompStmt(new PrintStmt(new VarExp("var_c")), new CompStmt(new IfStmt(new VarExp("var_c"),
						new CompStmt(new readFile(new VarExp("var_f"), "var_c"), new PrintStmt(new VarExp("var_c"))),
						new PrintStmt(new ConstExp(0))), new CompStmt (new closeRFile(new VarExp("var_f")), new closeRFile(new VarExp("var_f")))))));
		PrgState prg6 = createPrg(ex6);
		IRepository rep6 = new Repository(prg6, "log6.txt");
		Controller ctrl6 = new Controller(rep6);
		
		//v=10;new(v,20);new(a,22);print(v)
		
		IStmt ex7 = new CompStmt(new AssignStmt("v", new ConstExp(10)), new CompStmt(new NouveauStmt("v", new ConstExp(20)), 
				new CompStmt(new NouveauStmt("a", new ConstExp(22)), new PrintStmt(new VarExp("v")))));
		PrgState prg7 = createPrg(ex7);
		IRepository rep7 = new Repository(prg7, "log7.txt");
		Controller ctrl7 = new Controller (rep7);
		
		//Example: v=10;new(v,20);new(a,22);print(100+rH(v));print(100+rH(a))
		//At the end of execution: Heap={1->20, 2->22}, SymTable={v->1, a->2} and Out={120,122}
		IStmt ex8 = new CompStmt(new AssignStmt("v", new ConstExp(10)), new CompStmt(new NouveauStmt("v", new ConstExp(20)), 
				new CompStmt(new NouveauStmt("a", new ConstExp(22)), new CompStmt(new PrintStmt(new ArthExp('+', new ConstExp(100), 
						new ReadHeapExp("v"))), new PrintStmt(new ArthExp('+', new ConstExp(100), new ReadHeapExp("a")))))));
		PrgState prg8 = createPrg(ex8);
		IRepository rep8 = new Repository(prg8, "log8.txt");
		Controller ctrl8 = new Controller (rep8);
		
		//v=10;new(v,20);new(a,22);wH(a,30);print(a);print(rH(a))
		//At the end of execution: Heap={1->20, 2->30}, SymTable={v->1, a->2} and Out={2,30}
		IStmt ex9 = new CompStmt(new AssignStmt("v", new ConstExp(10)), new CompStmt(new NouveauStmt("v", new ConstExp(20)), 
				new CompStmt(new NouveauStmt("a", new ConstExp(22)), new CompStmt(new WriteHeapStmt("a", new ConstExp(30)), 
						new CompStmt(new PrintStmt(new VarExp("a")), new PrintStmt(new ReadHeapExp("a")))))));
		PrgState prg9 = createPrg(ex9);
		IRepository rep9 = new Repository(prg9, "log9.txt");
		Controller ctrl9 = new Controller (rep9);
		
		//v=10;new(v,20);new(a,22);wH(a,30);print(a);print(rH(a));a=0
		//At the end of execution: Heap={1->20}, SymTable={v->1, a->0} and Out={2,30}
		IStmt ex10 = new CompStmt(new AssignStmt("v", new ConstExp(10)), new CompStmt(new NouveauStmt("v", new ConstExp(20)), 
				new CompStmt(new NouveauStmt("a", new ConstExp(22)), new CompStmt(new WriteHeapStmt("a", new ConstExp(30)), 
						new CompStmt(new PrintStmt(new VarExp("a")), new CompStmt(new PrintStmt(new ReadHeapExp("a")), 
								new AssignStmt("a", new ConstExp(0))))))));
		PrgState prg10 = createPrg(ex10);
		IRepository rep10 = new Repository(prg10, "log10.txt");
		Controller ctrl10 = new Controller (rep10);
		
		//v=6; (while (v-4) print(v);v=v-1);print(v)
		
		IStmt ex11 = new CompStmt(new AssignStmt("v", new ConstExp(6)), new CompStmt(new WhileStmt(new ArthExp('-', new VarExp("v"),
				new ConstExp(4)), new CompStmt(new PrintStmt(new VarExp("v")), new AssignStmt("v", new ArthExp('-', new VarExp("v"), 
						new ConstExp(1))))),new PrintStmt(new VarExp("v"))));
		PrgState prg11 = createPrg(ex11);
		IRepository rep11 = new Repository(prg11, "log11.txt");
		Controller ctrl11 = new Controller (rep11);
		
		//v=10;new(v,20);new(a,22);wH(a,30);print(a);print(rH(a));a=0;rH(a)
		IStmt ex12 = new CompStmt(new AssignStmt("v", new ConstExp(10)), new CompStmt(new NouveauStmt("v", new ConstExp(20)), 
				new CompStmt(new NouveauStmt("a", new ConstExp(22)), new CompStmt(new WriteHeapStmt("a", new ConstExp(30)), 
						new CompStmt(new PrintStmt(new VarExp("a")), new CompStmt(new PrintStmt(new ReadHeapExp("a")),
								new CompStmt(new AssignStmt("a", new ConstExp(0)), new PrintStmt(new ReadHeapExp("a")))))))));
		PrgState prg12 = createPrg(ex12);
		IRepository rep12 = new Repository(prg12, "log12.txt");
		Controller ctrl12 = new Controller (rep12);
		
		//10 + (2<6) evaluates to 11
		IStmt ex13 = new PrintStmt(new ArthExp('+', new ConstExp(10), new LessThanExp(new ConstExp(2), new ConstExp(6))));
		PrgState prg13 = createPrg(ex13);
		IRepository rep13 = new Repository(prg13, "log13.txt");
		Controller ctrl13 = new Controller (rep13);
		
		//(10+2)<6 evaluates to 0
		IStmt ex14 = new PrintStmt(new LessThanExp(new ArthExp('+', new ConstExp(10), new ConstExp(2)), new ConstExp(6)));
		PrgState prg14 = createPrg(ex14);
		IRepository rep14 = new Repository(prg14, "log14.txt");
		Controller ctrl14 = new Controller (rep14);
		
		//openRFile(var_f,"test.in");print(a)
		IStmt ex15 = new CompStmt(new openRFile("var_f", "test.in"), new PrintStmt(new ReadHeapExp("a")));
		PrgState prg15 = createPrg(ex15);
		IRepository rep15 = new Repository(prg15, "log15.txt");
		Controller ctrl15 = new Controller (rep15);
		
		//v=10;new(a,22);
		//fork(wH(a,30);v=32;print(v);print(rH(a)));
		//print(v);print(rH(a))
		//At the end:
		//Id=1
		//SymTable_1={v->10,a->1}
		//Id=10
		//SymTable_10={v->32,a->1}
		//Heap={1->30}
		//Out={10,30,32,30}
		IStmt ex16 = new CompStmt(new AssignStmt("v", new ConstExp(10)), new CompStmt(new NouveauStmt("a", new ConstExp(22)), 
				new CompStmt(new ForkStmt(new CompStmt(new AssignStmt("v", new ConstExp(32)), 
						new CompStmt(new WriteHeapStmt("a", new ConstExp(30)), 
								new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new ReadHeapExp("a")))))), 
						new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new ReadHeapExp("a"))))));
		PrgState prg16 = createPrg(ex16);
		IRepository rep16 = new Repository(prg16, "log16.txt");
		Controller ctrl16 = new Controller (rep16);
		
		
		//v=10;new(a,22);
		//fork(wH(a,30);v=32;print(v);print(rH(a)));
		//fork(wH(a,3);v=31;print(v);print(rH(a)));
		//print(v);print(rH(a))
		IStmt ex17 = new CompStmt(new CompStmt(new AssignStmt("v", new ConstExp(10)), new NouveauStmt("a", new ConstExp(22))), 
				new CompStmt(new ForkStmt(new CompStmt(new AssignStmt("v", new ConstExp(32)), 
						new CompStmt(new WriteHeapStmt("a", new ConstExp(30)), 
								new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new ReadHeapExp("a")))))), 
						new CompStmt(new ForkStmt(new CompStmt(new AssignStmt("v", new ConstExp(3)), 
						new CompStmt(new WriteHeapStmt("a", new ConstExp(31)), 
								new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new ReadHeapExp("a")))))),
								new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new ReadHeapExp("a"))))));
		PrgState prg17 = createPrg(ex17);
		IRepository rep17 = new Repository(prg17, "log17.txt");
		Controller ctrl17 = new Controller (rep17);
		
		//v=10;new(a,22);
		//fork(wH(a,30);v=32;fork(wH(a,3);v=31;print(v);print(rH(a));)print(v);print(rH(a)));
		//print(v);print(rH(a))
		
		IStmt ex18 = new CompStmt(new CompStmt(new AssignStmt("v", new ConstExp(10)), new NouveauStmt("a", new ConstExp(22))),
				new CompStmt(new ForkStmt(new CompStmt(new WriteHeapStmt("a", new ConstExp(3)), 
						new CompStmt(new ForkStmt(new CompStmt(new CompStmt(new WriteHeapStmt("a", new ConstExp(3)), 
								new AssignStmt("v", new ConstExp(31))), 
								new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new ReadHeapExp("a"))))), 
								new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new ReadHeapExp("a")))))), 
								new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new ReadHeapExp("a")))));
		PrgState prg18 = createPrg(ex18);
		IRepository rep18 = new Repository(prg18, "log18.txt");
		Controller ctrl18 = new Controller (rep18);
		
		TextMenu menu = new TextMenu();
		menu.addCommand(new ExitCommand("0", "exit"));
		menu.addCommand(new RunExample("1", ex1.toString(), ctrl1));
		menu.addCommand(new RunExample("2", ex2.toString(), ctrl2));
		menu.addCommand(new RunExample("3", ex3.toString(), ctrl3));
		menu.addCommand(new RunExample("4", ex4.toString(), ctrl4));
		menu.addCommand(new RunExample("5", ex5.toString(), ctrl5));
		menu.addCommand(new RunExample("6", ex6.toString(), ctrl6));
		menu.addCommand(new RunExample("7", ex7.toString(), ctrl7));
		menu.addCommand(new RunExample("8", ex8.toString(), ctrl8));
		menu.addCommand(new RunExample("9", ex9.toString(), ctrl9));
		menu.addCommand(new RunExample("10", ex10.toString(), ctrl10));
		menu.addCommand(new RunExample("11", ex11.toString(), ctrl11));
		menu.addCommand(new RunExample("12", ex12.toString(), ctrl12));
		menu.addCommand(new RunExample("13", ex13.toString(), ctrl13));
		menu.addCommand(new RunExample("14", ex14.toString(), ctrl14));
		menu.addCommand(new RunExample("15", ex15.toString(), ctrl15));
		menu.addCommand(new RunExample("16", ex16.toString(), ctrl16));
		menu.addCommand(new RunExample("17", ex17.toString(), ctrl17));
		menu.addCommand(new RunExample("18", ex18.toString(), ctrl18));
		menu.show();
	}
}
