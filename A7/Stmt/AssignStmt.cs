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
    class AssignStmt : IStmt
    {
        String id;
        Exp exp;
        public AssignStmt(String id, Exp exp)
        {
            this.id = id;
            this.exp = exp;
        }
        public override String ToString()
        {
            return id + "=" + exp.ToString();
        }
        public PrgState Execute(PrgState state)
        {
            MyIStack<IStmt> stk = state.GetExeStack();
            MyIDictionary<String, int> symTbl = state.GetSymTable();
            int val = exp.Eval(symTbl);
            if (symTbl.IsDefinedU(id))
            {
                symTbl.Update(id, val);
            }
            else
            {
                symTbl.Add(id, val);
            }
            return state;
        }
    }
}
