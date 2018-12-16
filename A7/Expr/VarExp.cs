using A7.Dictionary;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace A7.Expr
{
    class VarExp: Exp
    {
        String id;
        public VarExp(String id)
        {
            this.id = id;
        }
        public override int Eval(MyIDictionary<String, int> tbl)
        {
            return tbl.Lookup(this.id);
        }
        public override String ToString()
        {
            return id;
        }
    }
}
