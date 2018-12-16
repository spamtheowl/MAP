using A7.Dictionary;
using A7.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace A7.Expr
{
    class ArithExp: Exp
    {
        Exp e1;
        Exp e2;
        char op;
        public ArithExp(char c, Exp e1, Exp e2)
        {
            this.op = c;
            this.e1 = e1;
            this.e2 = e2;
        }
        public override int Eval(MyIDictionary<String, int> tbl)
        {
            switch (op)
            {
                case ('+'):
                    return e1.Eval(tbl) + e2.Eval(tbl);
                case ('-'):
                    return e1.Eval(tbl) - e2.Eval(tbl);
                case ('*'):
                    return e1.Eval(tbl) * e2.Eval(tbl);
                case ('/'):
                    if (e2.Eval(tbl) == 0)
                    {
                        throw new MyException("Division with zero!");
                    }
                    else
                    {
                        return e1.Eval(tbl) / e2.Eval(tbl);
                    }
                default:
                    if (e2.Eval(tbl) == 0)
                    {
                        throw new MyException("Division with zero!");
                    }
                    else
                    {
                        return e1.Eval(tbl) % e2.Eval(tbl);
                    }
            }
        }
    public override String ToString()
{
    return e1.ToString() + op + e2.ToString();
}
    }
}
