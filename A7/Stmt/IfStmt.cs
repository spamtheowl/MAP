using A7.Dictionary;
using A7.Expr;
using A7.Model;
using A7.Stack;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace A7.Stmt
{
    class IfStmt: IStmt
    {
        Exp exp;
        IStmt thenS;
        IStmt elseS;
        public IfStmt(Exp e, IStmt t, IStmt el)
        {
            exp = e;
            thenS = t;
            elseS = el;
        }
        public override String ToString()
        {
            return "IF (" + exp.ToString() + ") THEN (" + thenS.ToString() + ") ELSE (" + elseS.ToString() + ")";
        }
        public PrgState Execute(PrgState state)
        {
            MyIStack<IStmt> stk = state.GetExeStack();
            MyIDictionary<String, int> symtbl = state.GetSymTable();
            if (exp.Eval(symtbl) != 0)
            {
                stk.Push(thenS);
            }
            else
            {
                stk.Push(elseS);
            }
            return state;
        }
    }
}
