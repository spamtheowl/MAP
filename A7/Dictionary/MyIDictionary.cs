using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace A7.Dictionary
{
    interface MyIDictionary<T, U>
    {
        void Add(T v, U w);
        void Delete(T v, U w);
        Boolean IsDefinedU(T v);
        void Update(T v, U w);
        U Lookup(T v); //throws MyStmtException
        Boolean IsDefinedV(U w);
        ICollection<U> Values();
        Boolean IsEmpty();
    }
}
