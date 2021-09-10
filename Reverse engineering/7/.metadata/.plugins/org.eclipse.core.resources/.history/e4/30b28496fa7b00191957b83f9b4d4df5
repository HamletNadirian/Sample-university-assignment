/*    */ package org.eclipse.jdt.internal.jarinjarloader;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.net.URL;
/*    */ import java.net.URLConnection;
/*    */ import java.net.URLStreamHandler;
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
/*    */ 
/*    */ 
/*    */ public class RsrcURLStreamHandler
/*    */   extends URLStreamHandler
/*    */ {
/*    */   private ClassLoader classLoader;
/*    */   
/* 37 */   public RsrcURLStreamHandler(ClassLoader classLoader) { this.classLoader = classLoader; }
/*    */ 
/*    */ 
/*    */   
/* 41 */   protected URLConnection openConnection(URL u) throws IOException { return new RsrcURLConnection(u, this.classLoader); }
/*    */ 
/*    */   
/*    */   protected void parseURL(URL url, String spec, int start, int limit) {
/*    */     String file;
/* 46 */     if (spec.startsWith("rsrc:")) {
/* 47 */       file = spec.substring(5);
/* 48 */     } else if (url.getFile().equals("./")) {
/* 49 */       file = spec;
/* 50 */     } else if (url.getFile().endsWith("/")) {
/* 51 */       file = String.valueOf(url.getFile()) + spec;
/* 52 */     } else if ("#runtime".equals(spec)) {
/* 53 */       file = url.getFile();
/*    */     } else {
/* 55 */       file = spec;
/* 56 */     }  setURL(url, "rsrc", "", -1, null, null, file, null, null);
/*    */   }
/*    */ }


/* Location:              C:\Revers\7\Lab07Rev\lab7rev.jar!\org\eclipse\jdt\internal\jarinjarloader\RsrcURLStreamHandler.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.0.1
 */