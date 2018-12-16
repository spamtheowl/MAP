using A7.Model;
using A7.Rep;
using A7.Stack;
using A7.Stmt;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace A7.Ctrl
{
    class Controller
    {
        private IRepository rep;
        public Controller(IRepository rep)
        {
            this.rep = rep;
        }

        public PrgState OneStep(PrgState state)
        {
            MyIStack<IStmt> stk = state.GetExeStack();
            if (stk.IsEmpty()) throw new MyException("Stack is empty");
            IStmt crtStmt = stk.Pop();
            return crtStmt.Execute(state);
        }
        public void AllStep()
        {
            PrgState prg = rep.GetCrtPrg();
            try
            {
                while (true)
                {
                    OneStep(prg);
                    Console.WriteLine(OneStep(prg).ToString());
                    rep.LogPrgStateExec();
                }
            }
            catch (MyException e)
            {
                throw e;
            }
        }
    }
}
