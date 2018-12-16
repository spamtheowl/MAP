using A7.Ctrl;
using A7.Dictionary;
using A7.Expr;
using A7.List;
using A7.Model;
using A7.Pair;
using A7.Rep;
using A7.Stack;
using A7.Stmt;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace A7
{
    class Program
    {
        private static PrgState createPrg(IStmt ex)
        {
            MyIStack<IStmt> stk = new MyStack<IStmt>();
            MyIDictionary<String, int> symtbl = new MyDictionary<String, int>();
            MyIList<int> ot = new MyList<int>();
            MyIDictionary<int, MyPair<String, StreamReader>> fT = new MyDictionary<int, MyPair<String, StreamReader>>();
            PrgState prg = new PrgState(stk, symtbl, ot, fT, ex);
            return prg;
        }
        static void Main(string[] args)
        {
            IStmt ex1 = new CompStmt(new AssignStmt("v", new ConstExp(2)), new PrintStmt(new VarExp("v"))); ;
            PrgState prg1 = createPrg(ex1);
            IRepository rep1 = new Repository(prg1, "log1.txt");
            Controller ctrl1 = new Controller(rep1);

            IStmt ex2 = new CompStmt(new CompStmt(new AssignStmt("a", new ArithExp('+', new ConstExp(2), new ConstExp(1))), new AssignStmt("b", new ArithExp('/', new VarExp("a"), new ConstExp(0)))), new PrintStmt(new VarExp("b")));
            PrgState prg2 = createPrg(ex2);
            IRepository rep2 = new Repository(prg2, "log2.txt");
            Controller ctrl2 = new Controller(rep2);

            IStmt ex3 = new CompStmt(new AssignStmt("a", new ArithExp('-', new ConstExp(2), new ConstExp(2))), new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ConstExp(2)), new AssignStmt("v", new ConstExp(3))), new PrintStmt(new VarExp("v"))));
            PrgState prg3 = createPrg(ex3);
            IRepository rep3 = new Repository(prg3, "log3.txt");
            Controller ctrl3 = new Controller(rep3);

            /*openRFile(var_f,"test.in");
            readFile(var_f,var_c);print(var_c);
            (if var_c then readFile(var_f,var_c);print(var_c)
            else print(0));
            closeRFile(var_f)*/
            IStmt ex4 = new CompStmt(new OpenFileStmt("var_f", "test.in"), new CompStmt(new ReadFileStmt(new VarExp("var_f"), "var_c"),
                    new CompStmt(new PrintStmt(new VarExp("var_c")), new CompStmt(new IfStmt(new VarExp("var_c"),
                            new CompStmt(new ReadFileStmt(new VarExp("var_f"), "var_c"), new PrintStmt(new VarExp("var_c"))),
                            new PrintStmt(new ConstExp(0))), new CloseFileStmt(new VarExp("var_f"))))));
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

            IStmt ex5 = new CompStmt(new OpenFileStmt("var_f", "test.in"), new CompStmt(new ReadFileStmt(new VarExp("var_f"), "var_c"),
                    new CompStmt(new PrintStmt(new VarExp("var_c")), new CompStmt(new IfStmt(new VarExp("var_c"),
                            new CompStmt(new ReadFileStmt(new VarExp("var_f"), "var_c"), new PrintStmt(new VarExp("var_c"))),
                            new PrintStmt(new ConstExp(0))), new CompStmt(new CloseFileStmt(new VarExp("var_f")), new CloseFileStmt(new VarExp("var_f")))))));
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

            IStmt ex6 = new CompStmt(new CompStmt(new OpenFileStmt("var_f", "test.in"), new OpenFileStmt("var_f", "test.in")), new CompStmt(new ReadFileStmt(new VarExp("var_f"), "var_c"),
                    new CompStmt(new PrintStmt(new VarExp("var_c")), new CompStmt(new IfStmt(new VarExp("var_c"),
                            new CompStmt(new ReadFileStmt(new VarExp("var_f"), "var_c"), new PrintStmt(new VarExp("var_c"))),
                            new PrintStmt(new ConstExp(0))), new CompStmt(new CloseFileStmt(new VarExp("var_f")), new CloseFileStmt(new VarExp("var_f")))))));
            PrgState prg6 = createPrg(ex6);
            IRepository rep6 = new Repository(prg6, "log6.txt");
            Controller ctrl6 = new Controller(rep6);

            TextMenu menu = new TextMenu();
            menu.addCommand(new ExitCommand("0", "exit"));
            menu.addCommand(new RunExample("1", ex1.ToString(), ctrl1));
            menu.addCommand(new RunExample("2", ex2.ToString(), ctrl2));
            menu.addCommand(new RunExample("3", ex3.ToString(), ctrl3));
            menu.addCommand(new RunExample("4", ex4.ToString(), ctrl4));
            menu.addCommand(new RunExample("5", ex5.ToString(), ctrl5));
            menu.addCommand(new RunExample("6", ex6.ToString(), ctrl6));
            menu.show();
        }
    }
}
