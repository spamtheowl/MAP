using A7.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace A7.Rep
{
    interface IRepository
    {
        void Add(PrgState prg);
        PrgState GetCrtPrg();
        void LogPrgStateExec();
    }
}
