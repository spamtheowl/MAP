using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace A7.Model
{
    public class MyException: Exception
    {
        public MyException(String str)
        {
            Console.WriteLine(str);
        }
    }
}
