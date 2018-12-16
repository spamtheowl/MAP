using A7.Dictionary;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace A7.Model
{
    class TextMenu
    {
        private MyIDictionary<String, Command> commands;
        public TextMenu()
        {
            commands = new MyDictionary<String, Command>();
        }
        public void addCommand(Command c)
        {
            commands.Add(c.GetKey(), c);
        }
        public void PrintMenu()
        {
            foreach (Command com in commands.Values())
            {
                String line = "    " + com.GetKey() + ": " + com.GetDescription();
                Console.WriteLine(line);
            }
        }
        public void show()
        {
            while (true)
            {
                PrintMenu();
                Console.Write("Input the option: ");
                String key = Console.ReadLine();
                Command com = commands.Lookup(key);
                if (com == null)
                {
                    Console.WriteLine("Invalid Option");
                    continue;
                }
                com.Execute();
            }
        }
    }
}
