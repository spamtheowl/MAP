using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace A7.Pair
{
    class MyPair<F, S>: MyIPair<F, S>
    {
        KeyValuePair<F, S> pair;
        public MyPair(F f, S s)
        {
            this.pair = new KeyValuePair<F, S>();
        }
        public F GetFirst()
        {
            return pair.Key;
        }
        public S GetSecond()
        {
            return pair.Value;
        }
        public override String ToString()
        {
            return pair.Key.ToString();
        }
    }
}
