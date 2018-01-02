// 
// Decompiled by Procyon v0.5.30
// 

package loader;

import java.net.URL;

class g implements Runnable
{
    private Object a;
    private volatile URL b;
    private final Loader c;
    private static String z;
    
    private boolean a() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        loader/g.a:Ljava/lang/Object;
        //     4: astore_1       
        //     5: aload_1        
        //     6: monitorenter   
        //     7: aload_0        
        //     8: getfield        loader/g.b:Ljava/net/URL;
        //    11: ifnull          18
        //    14: iconst_0       
        //    15: goto            19
        //    18: iconst_1       
        //    19: istore_3       
        //    20: jsr             33
        //    23: iload_3        
        //    24: ireturn        
        //    25: aload_1        
        //    26: monitorexit    
        //    27: goto            38
        //    30: aload_1        
        //    31: monitorexit    
        //    32: athrow         
        //    33: astore_2       
        //    34: aload_1        
        //    35: monitorexit    
        //    36: ret             2
        //    38: nop            
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  7      30     30     33     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2162)
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
    
    public void run() {
        URL b = null;
        synchronized (this.a) {
            if (this.b == null) {
                // monitorexit(this.a)
                return;
            }
            b = this.b;
        }
        // monitorexit(this.a)
        try {
            b.getContent();
        }
        catch (Throwable t) {}
        synchronized (this.a) {
            this.b = null;
        }
        // monitorexit(this.a)
    }
    
    static boolean a(final g g) {
        return g.a();
    }
    
    private g(final Loader c, final URL b) {
        this.c = c;
        this.a = new Object();
        this.b = null;
        this.b = b;
        new Thread(this, g.z + b.toString()).start();
    }
    
    g(final Loader loader, final e e, final URL url) {
        this(loader, url);
    }
    
    static {
        final char[] charArray = "\u00186\u007f(.\"\u0005Wm0w".toCharArray();
        int length;
        int n2;
        final int n = n2 = (length = charArray.length);
        int n3 = 0;
        while (true) {
            Label_0094: {
                if (n > 1) {
                    break Label_0094;
                }
                length = (n2 = n3);
                do {
                    final char c = charArray[n2];
                    char c2 = '\0';
                    switch (n3 % 5) {
                        case 0: {
                            c2 = 'M';
                            break;
                        }
                        case 1: {
                            c2 = 'd';
                            break;
                        }
                        case 2: {
                            c2 = '3';
                            break;
                        }
                        case 3: {
                            c2 = '\b';
                            break;
                        }
                        default: {
                            c2 = 'B';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                g.z = new String(charArray).intern();
                return;
            }
            continue;
        }
    }
}
