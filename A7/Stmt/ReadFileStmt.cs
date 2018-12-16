using A7.Dictionary;
using A7.Pair;
using A7.Expr;
using A7.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;

namespace A7.Stmt
{
    class ReadFileStmt: IStmt
    {
        Exp exp_file_id;
        String var_name;
        public ReadFileStmt(Exp exp, String var_name)
        {
            this.exp_file_id = exp;
            this.var_name = var_name;
        }
        public PrgState Execute(PrgState state)
        {
            MyIDictionary<String, int> symTable = state.GetSymTable();
            int file_id = this.exp_file_id.Eval(symTable);
            MyIPair<String, StreamReader> fileTable = state.GetFileTable().Lookup(file_id);
            if (fileTable == null)
            {
                throw new MyException("File not opened");
            }
            int anotherVal;
            String line = fileTable.GetSecond().ReadLine();
            if (line == null)
            {
                anotherVal = 0;
            }
            else
            {
                anotherVal = System.Convert.ToInt32(line);
            }
            if (symTable.IsDefinedU(var_name))
            {
                symTable.Update(var_name, anotherVal);
            }
            else
            {
                symTable.Add(var_name, anotherVal);
            }
            return state;
        }
        public override String ToString()
        {
            return "readFile(" + exp_file_id + ", \"" + var_name + "\")";
        }
    }
}
