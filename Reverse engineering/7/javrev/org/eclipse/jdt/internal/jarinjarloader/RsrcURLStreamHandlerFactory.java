/*    */ package org.eclipse.jdt.internal.jarinjarloader;
/*    */ 
/*    */ import java.net.URLStreamHandler;
/*    */ import java.net.URLStreamHandlerFactory;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RsrcURLStreamHandlerFactory
/*    */   implements URLStreamHandlerFactory
/*    */ {
/*    */   private ClassLoader classLoader;
/*    */   private URLStreamHandlerFactory chainFac;
/*    */   
/* 34 */   public RsrcURLStreamHandlerFactory(ClassLoader cl) { this.classLoader = cl; }
/*    */ 
/*    */   
/*    */   public URLStreamHandler createURLStreamHandler(String protocol) {
/* 38 */     if ("rsrc".equals(protocol))
/* 39 */       return new RsrcURLStreamHandler(this.classLoader); 
/* 40 */     if (this.chainFac != null)
/* 41 */       return this.chainFac.createURLStreamHandler(protocol); 
/* 42 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 54 */   public void setURLStreamHandlerFactory(URLStreamHandlerFactory fac) { this.chainFac = fac; }
/*    */ }


/* Location:              C:\Revers\7\Lab07Rev\lab7rev.jar!\org\eclipse\jdt\internal\jarinjarloader\RsrcURLStreamHandlerFactory.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.0.1
 */