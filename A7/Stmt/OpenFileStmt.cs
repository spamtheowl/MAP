using A7.Dictionary;
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
    class OpenFileStmt: IStmt
    {
        private String var_file_id;
        private String filename;
        static int ran;
        public OpenFileStmt(String vfi, String filename)
        {
            this.var_file_id = vfi;
            this.filename = filename;
            ran = 1;
        }
        public PrgState Execute(PrgState state)
        {
            MyIDictionary<int, MyPair<String, StreamReader>> fileTable = state.GetFileTable();
            foreach (MyIPair<String, StreamReader> ff in fileTable.Values())
            {
                if (ff.GetFirst().Equals(this.filename))
                {
                    throw new MyException("File already used");
                }
            }
            MyIDictionary<String, int> symTable = state.GetSymTable();
            StreamReader streamReader = new StreamReader(this.filename);
            int unq = ran++;
            MyPair<String, StreamReader> tup = new MyPair<String, StreamReader>(this.filename, streamReader);
            fileTable.Add(unq, tup);
            symTable.Add(var_file_id, unq);
            return state;
        }
        public override String ToString()
        {
            return "openRFile(" + var_file_id + ", \"" + filename + "\")";
        }
    }
}
