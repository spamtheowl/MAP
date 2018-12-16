using A7.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace A7.Stmt
{
    interface IStmt
    {
        String ToString();
        PrgState Execute(PrgState state);
    }
}
