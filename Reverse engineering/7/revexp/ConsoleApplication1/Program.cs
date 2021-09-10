using System;

namespace Lab8Reverse
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("Enter pwd:");
            uint upwd = uint.Parse(Console.ReadLine());
            Pwd pwd = new Pwd();
            //Console.WriteLine("Key:" + pwd.getPwd());
            if (upwd == pwd.getPwd()) //685
                Console.WriteLine("Right");
            else
                Console.WriteLine("Incorrect");
        }
    }
    public class Pwd
    {
        uint b = 2, c = 3, d = 4, e = 5, f = 6;
        private string a = "Nadirian Hamlet Ovikov";
        private static uint res = 0;
        public uint getPwd() { return res; }
        public Pwd()
        {
            int i;
            for (i = 0; i < a.Length; i++)
            {
                res += b * d + (uint)Math.Sqrt((uint)a[i]) + c + e + f;
            }

        }
    }
}