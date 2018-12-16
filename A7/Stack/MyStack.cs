using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace A7.Stack
{
    class MyStack<T>: MyIStack<T>
    {
        Stack<T> stack;
        public MyStack()
        {
            this.stack = new Stack<T>();
        }
        public T Pop()
        {
            return stack.Pop();
        }
        public void Push(T e)
        {
            stack.Push(e);
        }

        public Boolean IsEmpty()
        {
            return stack.Count() == 0;
        }
        public override String ToString()
        {
            String result = "\n";
            foreach (T el in stack)
            {
                result += el.ToString() + Environment.NewLine;
            }
            return result;
        }
    }
}
