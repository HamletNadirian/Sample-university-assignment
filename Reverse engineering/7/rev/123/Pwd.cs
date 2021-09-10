using System;

namespace Lab8Reverse
{
	// Token: 0x02000003 RID: 3
	public class Pwd
	{
		// Token: 0x06000003 RID: 3 RVA: 0x000020A0 File Offset: 0x000002A0
		public uint getPwd()
		{
			return Pwd.res;
		}

		// Token: 0x06000004 RID: 4 RVA: 0x000020B8 File Offset: 0x000002B8
		public Pwd()
		{
			for (int i = 0; i < this.a.Length; i++)
			{
				Pwd.res += this.b * this.d + (uint)Math.Sqrt(this.a[i]) + this.c + this.e + this.f;
			}
			Console.WriteLine("Key:" + Pwd.res);
		}

		// Token: 0x04000001 RID: 1
		private uint b = 2u;

		// Token: 0x04000002 RID: 2
		private uint c = 3u;

		// Token: 0x04000003 RID: 3
		private uint d = 4u;

		// Token: 0x04000004 RID: 4
		private uint e = 5u;

		// Token: 0x04000005 RID: 5
		private uint f = 6u;

		// Token: 0x04000006 RID: 6
		private string a = "Nadirian Hamlet Ovikov";

		// Token: 0x04000007 RID: 7
		private static uint res = 0u;
	}
}
