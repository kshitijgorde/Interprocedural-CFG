// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.util.tunnel3;

import java.io.IOException;
import java.net.SocketException;
import java.io.OutputStream;
import java.io.InputStream;
import java.net.Socket;

public class b3 extends Socket
{
    protected b6 a;
    protected InputStream b;
    protected OutputStream c;
    private boolean d;
    
    public InputStream getInputStream() {
        return this.b;
    }
    
    public OutputStream getOutputStream() {
        return this.c;
    }
    
    public void setSoLinger(final boolean b, final int n) throws SocketException {
    }
    
    public void setSoTimeout(final int n) throws SocketException {
    }
    
    public void setTcpNoDelay(final boolean b) throws SocketException {
    }
    
    public void close() throws IOException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/diginet/digichat/util/tunnel3/b3.b:Ljava/io/InputStream;
        //     4: invokevirtual   java/io/InputStream.close:()V
        //     7: jsr             19
        //    10: goto            67
        //    13: astore_1       
        //    14: jsr             19
        //    17: aload_1        
        //    18: athrow         
        //    19: astore_2       
        //    20: aload_0        
        //    21: getfield        com/diginet/digichat/util/tunnel3/b3.c:Ljava/io/OutputStream;
        //    24: invokevirtual   java/io/OutputStream.close:()V
        //    27: jsr             39
        //    30: goto            65
        //    33: astore_3       
        //    34: jsr             39
        //    37: aload_3        
        //    38: athrow         
        //    39: astore          4
        //    41: aload_0        
        //    42: getfield        com/diginet/digichat/util/tunnel3/b3.d:Z
        //    45: ifeq            56
        //    48: getstatic       java/lang/System.out:Ljava/io/PrintStream;
        //    51: ldc             "about to closeconnection"
        //    53: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
        //    56: aload_0        
        //    57: getfield        com/diginet/digichat/util/tunnel3/b3.a:Lcom/diginet/digichat/util/tunnel3/b6;
        //    60: invokevirtual   com/diginet/digichat/util/tunnel3/b6.b:()V
        //    63: ret             4
        //    65: ret             2
        //    67: return         
        //    Exceptions:
        //  throws java.io.IOException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  0      13     13     19     Any
        //  20     33     33     39     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Inconsistent stack size at #0197 (coming from #0195).
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2181)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    static boolean a(final b3 b3) {
        return b3.d;
    }
    
    public b3(final String s) throws IOException {
        this.b = new b4(this);
        this.c = new b5(this);
        this.d = false;
        final String string = new Double(Math.random()).toString();
        this.a = new b6(s, Long.parseLong(string.substring(string.indexOf(46) + 1)));
    }
}
