using A7.Dictionary;
using A7.Expr;
using A7.List;
using A7.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace A7.Stmt
{
    class PrintStmt: IStmt
    {
        Exp exp;
        public PrintStmt(Exp exp)
        {
            this.exp = exp;
        }
        public PrgState Execute(PrgState state)
        {
            MyIList<int> ot = state.GetOut();
            MyIDictionary<String, int> symtbl = state.GetSymTable();
            int val = exp.Eval(symtbl);
            ot.Add(val);
            return state;
        }

        public override string ToString()
        {
                return "print (" + exp.ToString() + ")";
        }
    }
}
