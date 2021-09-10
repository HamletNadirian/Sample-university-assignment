/*    */ package rev07hc;
/*    */ 
/*    */ public class PWD {
/*  4 */   int b = 2; int c = 3; int d = 4; int e = 5; int f = 6;
/*  5 */   private static String a = "Nadirian Hamlet Ovikov";
/*  6 */   private static int res = 0;
/*  7 */   public int getPwd() { return res; }
/*    */ 
/*    */   
/*    */   public PWD() {
/* 11 */     for (int i = 0; i < a.length(); i++)
/* 12 */       res = (int)(res + (this.b * this.d + this.c + this.e + this.f) + Math.sqrt(a.charAt(i))); 
/*    */   }
/*    */ }

