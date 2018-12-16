using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace A7.List
{
    class MyList<T>: MyIList<T>
    {
        LinkedList<T> list;
        public MyList()
        {
            list = new LinkedList<T>();
        }
        public void Add(T e)
        {
            list.AddLast(e);
        }
        public override String ToString()
        {
            String result = "\n";

            for (int i = 0; i < list.Count; i++)
            {
                result += list.ElementAt<T>(i).ToString() + "\n";
            }
            return result;
        }
    }
}
