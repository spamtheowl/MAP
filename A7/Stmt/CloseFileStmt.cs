using A7.Expr;
using A7.Model;
using A7.Pair;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace A7.Stmt
{
    class CloseFileStmt: IStmt
    {
        Exp exp_file_id;
        public CloseFileStmt(Exp e)
        {
            exp_file_id = e;
        }
        public PrgState Execute(PrgState state)
        {
            try
            {
                int val = exp_file_id.Eval(state.GetSymTable());//what to eval
                MyPair<String, StreamReader> entry = state.GetFileTable().Lookup(val);
                entry.GetSecond().Close();
                state.GetFileTable().Delete(val, entry);
            }
            catch (Exception e)
            {
                throw new MyException("Something went wrong!");
            }
            return state;
        }
        public override String ToString()
        {
            return "closeRFile(" + exp_file_id.ToString() + ")";
        }
    }
}
