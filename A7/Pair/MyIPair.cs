using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace A7.Pair
{
    interface MyIPair<F, S>
    {
        String ToString();
        F GetFirst();
        S GetSecond();
    }
}
