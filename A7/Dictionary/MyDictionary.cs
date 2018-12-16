using A7.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace A7.Dictionary
{
    public class MyDictionary<T, U>: MyIDictionary<T, U> 
    {
        Dictionary<T, U> dictionary;
        public MyDictionary()
        {
            dictionary = new Dictionary<T, U>();
        }
        public void Add(T v, U w)
        {
            dictionary.Add(v, w);
        }
        public void Delete(T v, U w)
        {
            dictionary.Remove(v);
        }
        public Boolean IsDefinedU(T v)
        {
            foreach(T key in dictionary.Keys)
            {
                if (key.Equals(v))
                {
                    return true;
                }
            }
            return false;
        }
        public void Update(T v, U w)
        {
            dictionary.Remove(v);
            dictionary.Add(v, w);
        }
        public U Lookup(T v) //throws MyStmtException
        {
            if (IsDefinedU(v))
            {
                return dictionary[v];
            }
            else
            {
                throw new MyException("Undefined variable");
            }
        }
        public Boolean IsDefinedV(U w)
        {
            foreach (U val in dictionary.Values)
            {
                if (w.Equals(val))
                {
                    return true;
                }
            }
            return false;
        }
        public ICollection<U> Values()
        {
            return dictionary.Values;
        }
        public Boolean IsEmpty()
        {
            return dictionary.Count == 0;
        }

        public override String ToString()
        {
            String result = "\n";
            foreach (T key in dictionary.Keys)
            {
                result += key.ToString() + "=>" + dictionary[key].ToString() + "\n";
            }
            return result;
        }
    }
}
