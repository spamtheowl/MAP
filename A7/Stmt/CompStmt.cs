using A7.Model;
using A7.Stack;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace A7.Stmt
{
    class CompStmt: IStmt
    {
        IStmt first;
        IStmt snd;

        public CompStmt(IStmt f, IStmt s)
        {
            this.first = f;
            this.snd = s;
        }
        public PrgState Execute(PrgState state)
        {
            MyIStack<IStmt> stk = state.GetExeStack();
            stk.Push(snd);
            stk.Push(first);
            return state;
        }
        public override String ToString()
        {
            return "(" + first.ToString() + "; " + snd.ToString() + ")";
        }
    }
}
