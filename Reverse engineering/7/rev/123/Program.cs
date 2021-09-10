using System;

namespace Lab8Reverse
{
	// Token: 0x02000002 RID: 2
	internal class Program
	{
		// Token: 0x06000001 RID: 1 RVA: 0x00002050 File Offset: 0x00000250
		private static void Main(string[] args)
		{
            

            //Console.WriteLine("Enter pwd:");
			//uint upwd = uint.Parse(Console.ReadLine());
			Pwd pwd = new Pwd();
            Console.WriteLine(pwd.getPwd());
            /*if (upwd == pwd.getPwd())
			{
				Console.WriteLine("Right");
			}*/
		}
	}
}
