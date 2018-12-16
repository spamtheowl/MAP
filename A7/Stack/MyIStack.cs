using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace A7.Stack
{
    public interface MyIStack<T>
    {
        T Pop();
        void Push(T v);
        Boolean IsEmpty();
    }
}
