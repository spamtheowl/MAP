using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace A7.Model
{
    abstract class Command
    {
        private String key, description;
        public Command(String key, String description)
        {
            this.key = key;
            this.description = description;
        }
        public abstract void Execute();
        public String GetKey()
        {
            return key;
        }
        public String GetDescription()
        {
            return description;
        }
        public void SetKey(String key)
        {
            this.key = key;
        }
        public void SetDesc(String d)
        {
            this.description = d;
        }
    }
}
