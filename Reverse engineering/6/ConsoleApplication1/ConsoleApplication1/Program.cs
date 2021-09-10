using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;


using System.IO;
namespace ReverseProgramming
{
    class Program
    {
        static void Main(string[] args)
        {
            if (args.Length != 3)
            {
                Console.WriteLine("Usage: RP.exe DecryptedExe.exe startbytenumber size");
                return;
            }
            byte[] exe = File.ReadAllBytes(args[0]);
            ushort key = 0x1515;
            int start = int.Parse(args[1]);
            int stop = int.Parse(args[2]);

            for (int i = start, k = 0; k < stop; i += 2, k++) // к увеличиваем на 2 т.к. изменяем слова. 
            {
                ushort res = (ushort)((exe[i + 1] << 8) | exe[i]);
                res -= key;
                exe[i + 1] = (byte)(res >> 8);
                exe[i] = (byte)res;

            }
            File.WriteAllBytes("Encrypt.exe", exe);
        }
    }
}