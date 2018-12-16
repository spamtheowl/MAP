using A7.Dictionary;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace A7.Expr
{
    class ConstExp: Exp
    {
        int number;
        public ConstExp(int no)
        {
            this.number = no;
        }
        public override int Eval(MyIDictionary<String, int> tbl)
        {
            return number;
        }
        public override String ToString()
        {
            return "" + number;
        }
    }
}
