using A7.Model;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace A7.Rep
{
    class Repository: IRepository
    {
        int i = 0;
        private LinkedList<PrgState> prgs;
        String logFilePath;
        public Repository(PrgState prg, String lFP)
        {
            prgs = new LinkedList<PrgState>();
            prgs.AddLast(prg);
            logFilePath = lFP;
        }
        public void Add(PrgState prg)
        {
            prgs.AddLast(prg);
        }
        public PrgState GetCrtPrg()
        {
            return prgs.Last.Value;
        }

        public void LogPrgStateExec()
        {
            try
            {
                File.AppendAllText(logFilePath, prgs.Last.Value.ToString());
            }
            catch (Exception e)
            {
                Console.WriteLine("Error with file");
            }
        }
    }
}
