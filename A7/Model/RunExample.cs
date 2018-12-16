using A7.Ctrl;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace A7.Model
{
    class RunExample: Command
    {
        private Controller ctrl;
        String key, desc;
        public RunExample(String key, String desc, Controller c): base(key, desc)
        {
            this.ctrl = c;
        }
        public override void Execute()
        {
            ctrl.AllStep();
        }
}
}
