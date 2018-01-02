// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.util.tunnel3;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.SocketException;
import java.io.OutputStream;
import java.io.InputStream;
import java.net.Socket;

public class ap extends Socket
{
    protected as a;
    protected InputStream b;
    protected OutputStream c;
    private boolean d;
    
    public final InputStream getInputStream() {
        return this.b;
    }
    
    public final OutputStream getOutputStream() {
        return this.c;
    }
    
    public final void setSoLinger(final boolean b, final int n) throws SocketException {
    }
    
    public final void setSoTimeout(final int n) throws SocketException {
    }
    
    public final void setTcpNoDelay(final boolean b) throws SocketException {
    }
    
    public final void close() throws IOException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/diginet/digichat/util/tunnel3/ap.b:Ljava/io/InputStream;
        //     4: invokevirtual   java/io/InputStream.close:()V
        //     7: jsr             19
        //    10: goto            67
        //    13: astore_1       
        //    14: jsr             19
        //    17: aload_1        
        //    18: athrow         
        //    19: astore_2       
        //    20: aload_0        
        //    21: getfield        com/diginet/digichat/util/tunnel3/ap.c:Ljava/io/OutputStream;
        //    24: invokevirtual   java/io/OutputStream.close:()V
        //    27: jsr             39
        //    30: goto            65
        //    33: astore_3       
        //    34: jsr             39
        //    37: aload_3        
        //    38: athrow         
        //    39: astore          4
        //    41: aload_0        
        //    42: getfield        com/diginet/digichat/util/tunnel3/ap.d:Z
        //    45: ifeq            56
        //    48: getstatic       java/lang/System.out:Ljava/io/PrintStream;
        //    51: ldc             "about to closeconnection"
        //    53: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
        //    56: aload_0        
        //    57: getfield        com/diginet/digichat/util/tunnel3/ap.a:Lcom/diginet/digichat/util/tunnel3/as;
        //    60: invokevirtual   com/diginet/digichat/util/tunnel3/as.b:()V
        //    63: ret             4
        //    65: ret             2
        //    67: return         
        //    Exceptions:
        //  throws java.io.IOException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  0      7      13     19     Any
        //  20     27     33     39     Any
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
    
    public ap(final String s) throws IOException {
        if (this == null) {
            throw null;
        }
        this.b = new InputStream() {
            private byte[] a;
            private int b;
            
            public final int available() {
                return (this.a == null) ? 0 : (this.a.length - 1 - this.b);
            }
            
            public final int read() throws IOException {
                while (true) {
                    if (this.a != null) {
                        if (this.b < this.a.length - 1) {
                            return this.a[this.b++] & 0xFF;
                        }
                        if (this.a[this.b] != 0) {
                            return -1;
                        }
                    }
                    this.a = ap.this.a.a().a();
                    this.b = 0;
                }
            }
        };
        if (this == null) {
            throw null;
        }
        this.c = new OutputStream() {
            private boolean a = false;
            private ByteArrayOutputStream b = new ByteArrayOutputStream();
            
            private final void a(final boolean b) throws IOException {
                if (this.b.size() > 0 || b) {
                    this.b.write(b ? -1 : 0);
                    ap.this.a.a(new ca(this.b.toByteArray()));
                    this.b.reset();
                }
            }
            
            public final synchronized void close() throws IOException {
                if (!this.a) {
                    this.a(true);
                    this.a = true;
                }
            }
            
            public final synchronized void flush() throws IOException {
                if (!this.a) {
                    this.a(false);
                }
                else if (ap.this.d) {
                    System.out.println("error: tried to flush a closed stream");
                    Thread.dumpStack();
                }
            }
            
            public final synchronized void write(final int n) throws IOException {
                if (!this.a) {
                    this.b.write(n);
                }
                else if (ap.this.d) {
                    System.out.println("error: tried to write to a closed stream");
                    Thread.dumpStack();
                }
            }
            
            public final synchronized void write(final byte[] array) throws IOException {
                if (!this.a) {
                    this.b.write(array, 0, array.length);
                }
                else if (ap.this.d) {
                    System.out.println("error: tried to write to a closed stream");
                    Thread.dumpStack();
                }
            }
            
            public final synchronized void write(final byte[] array, final int n, final int n2) throws IOException {
                if (!this.a) {
                    this.b.write(array, n, n2);
                }
                else if (ap.this.d) {
                    System.out.println("error: tried to write to a closed stream");
                    Thread.dumpStack();
                }
            }
        };
        this.d = false;
        final String string = new Double(Math.random()).toString();
        this.a = new as(s, Long.parseLong(string.substring(string.indexOf(46) + 1)));
    }
}
