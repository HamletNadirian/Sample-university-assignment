/*    */ package org.eclipse.jdt.internal.jarinjarloader;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.net.MalformedURLException;
/*    */ import java.net.URL;
/*    */ import java.net.URLConnection;
/*    */ import java.net.URLDecoder;
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
/*    */ public class RsrcURLConnection
/*    */   extends URLConnection
/*    */ {
/*    */   private ClassLoader classLoader;
/*    */   
/*    */   public RsrcURLConnection(URL url, ClassLoader classLoader) {
/* 38 */     super(url);
/* 39 */     this.classLoader = classLoader;
/*    */   }
/*    */ 
/*    */   
/*    */   public void connect() throws IOException {}
/*    */   
/*    */   public InputStream getInputStream() throws IOException {
/* 46 */     String file = URLDecoder.decode(this.url.getFile(), "UTF-8");
/* 47 */     InputStream result = this.classLoader.getResourceAsStream(file);
/* 48 */     if (result == null) {
/* 49 */       throw new MalformedURLException("Could not open InputStream for URL '" + this.url + "'");
/*    */     }
/* 51 */     return result;
/*    */   }
/*    */ }


/* Location:              C:\Revers\7\Lab07Rev\lab7rev.jar!\org\eclipse\jdt\internal\jarinjarloader\RsrcURLConnection.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.0.1
 */