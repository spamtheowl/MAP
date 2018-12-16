using A7.Dictionary;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace A7.Expr
{
    abstract class Exp
    {
        public abstract int Eval(MyIDictionary<String, int> tbl);
        public abstract override String ToString();
    }
}
