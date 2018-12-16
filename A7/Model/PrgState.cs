using A7.Dictionary;
using A7.List;
using A7.Pair;
using A7.Stack;
using A7.Stmt;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace A7.Model
{
    class PrgState
    {
        private MyIStack<IStmt> exeStack;
        private MyIDictionary<String, int> symTable;
        private MyIList<int> outList;
        private MyIDictionary<int, MyPair<String, StreamReader>> fileTable;
        public PrgState(MyIStack<IStmt> stk, MyIDictionary<String, int> symtbl, MyIList<int> ot, MyIDictionary<int, MyPair<String, StreamReader>> fT, IStmt prg)
        {
            exeStack = stk;
            symTable = symtbl;
            outList = ot;
            fileTable = fT;
            exeStack.Push(prg);
        }
        public override String ToString()
        {
            return "Execution stack: " + exeStack.ToString() + Environment.NewLine + "Symbol Table: " + symTable.ToString() + Environment.NewLine + "Output: " + outList.ToString() + Environment.NewLine + "File Table:"+ Environment.NewLine + fileTable.ToString() + Environment.NewLine;
        }
        public MyIStack<IStmt> GetExeStack()
        {
            return this.exeStack;
        }
        public void SetExeStack(MyIStack<IStmt> stk)
        {
            this.exeStack = stk;
        }
        public MyIDictionary<String, int> GetSymTable()
        {
            return this.symTable;
        }
        public void SetSymTable(MyIDictionary<String, int> symtbl)
        {
            this.symTable = symtbl;
        }
        public MyIList<int> GetOut()
        {
            return this.outList;
        }
        public void SetOut(MyIList<int> ot)
        {
            this.outList = ot;
        }
        public MyIDictionary<int, MyPair<String, StreamReader>> GetFileTable()
        {
            return this.fileTable;
        }
    }
}
