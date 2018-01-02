// 
// Decompiled by Procyon v0.5.30
// 

package neat.system;

import java.io.OutputStream;
import java.io.PrintStream;
import neat.i;

public class f extends e
{
    private static Object e;
    private static i f;
    private static i g;
    public static int h;
    private static f i;
    private int j;
    private String k;
    private Object[] l;
    private String[] m;
    private c n;
    private PrintStream o;
    private int p;
    private Object[] q;
    private int r;
    private int s;
    private static Class t;
    private static String[] z;
    
    public Object a() {
        Object a;
        synchronized (this) {
            if (this.r == 0) {
                a = super.a();
            }
            else {
                final Object[] q = this.q;
                final int r = this.r - 1;
                this.r = r;
                a = q[r];
                this.q[this.r] = null;
            }
            ++this.p;
        }
        if (a instanceof cb) {
            ((cb)a).g();
        }
        return a;
    }
    
    public void a(final Object o) {
        if (o instanceof cb) {
            ((cb)o).h();
        }
        synchronized (this) {
            --this.p;
            if (this.p < 0) {
                throw new RuntimeException(neat.system.f.z[1] + o);
            }
            if (this.r >= this.q.length) {
                final Object[] q = new Object[this.q.length + this.s];
                System.arraycopy(this.q, 0, q, 0, this.r);
                this.q = q;
            }
            this.q[this.r++] = o;
        }
    }
    
    private int a(final f p0, final Class p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: getstatic       neat/system/f.e:Ljava/lang/Object;
        //     3: astore_3       
        //     4: aload_3        
        //     5: monitorenter   
        //     6: getstatic       neat/system/f.f:Lneat/i;
        //     9: ifnonnull       107
        //    12: aload_2        
        //    13: getstatic       neat/system/f.t:Ljava/lang/Class;
        //    16: ifnull          25
        //    19: getstatic       neat/system/f.t:Ljava/lang/Class;
        //    22: goto            37
        //    25: getstatic       neat/system/f.z:[Ljava/lang/String;
        //    28: iconst_0       
        //    29: aaload         
        //    30: invokestatic    neat/system/f.a:(Ljava/lang/String;)Ljava/lang/Class;
        //    33: dup            
        //    34: putstatic       neat/system/f.t:Ljava/lang/Class;
        //    37: if_acmpne       53
        //    40: aload_1        
        //    41: putstatic       neat/system/f.i:Lneat/system/f;
        //    44: iconst_m1      
        //    45: istore          5
        //    47: jsr             140
        //    50: iload           5
        //    52: ireturn        
        //    53: invokestatic    neat/i.k:()Lneat/i;
        //    56: putstatic       neat/system/f.f:Lneat/i;
        //    59: invokestatic    neat/i.k:()Lneat/i;
        //    62: putstatic       neat/system/f.g:Lneat/i;
        //    65: getstatic       neat/system/f.i:Lneat/system/f;
        //    68: ifnull          107
        //    71: aload_0        
        //    72: getstatic       neat/system/f.i:Lneat/system/f;
        //    75: getstatic       neat/system/f.t:Ljava/lang/Class;
        //    78: ifnull          87
        //    81: getstatic       neat/system/f.t:Ljava/lang/Class;
        //    84: goto            99
        //    87: getstatic       neat/system/f.z:[Ljava/lang/String;
        //    90: iconst_0       
        //    91: aaload         
        //    92: invokestatic    neat/system/f.a:(Ljava/lang/String;)Ljava/lang/Class;
        //    95: dup            
        //    96: putstatic       neat/system/f.t:Ljava/lang/Class;
        //    99: invokespecial   neat/system/f.a:(Lneat/system/f;Ljava/lang/Class;)I
        //   102: pop            
        //   103: aconst_null    
        //   104: putstatic       neat/system/f.i:Lneat/system/f;
        //   107: getstatic       neat/system/f.f:Lneat/i;
        //   110: invokevirtual   neat/i.i:()I
        //   113: istore          6
        //   115: getstatic       neat/system/f.f:Lneat/i;
        //   118: aload_1        
        //   119: invokevirtual   neat/i.a:(Ljava/lang/Object;)V
        //   122: iload           6
        //   124: istore          5
        //   126: jsr             140
        //   129: iload           5
        //   131: ireturn        
        //   132: aload_3        
        //   133: monitorexit    
        //   134: goto            146
        //   137: aload_3        
        //   138: monitorexit    
        //   139: athrow         
        //   140: astore          4
        //   142: aload_3        
        //   143: monitorexit    
        //   144: ret             4
        //   146: nop            
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  6      137    137    140    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static int a() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: getstatic       neat/system/f.e:Ljava/lang/Object;
        //     3: astore_0       
        //     4: aload_0        
        //     5: monitorenter   
        //     6: iconst_0       
        //     7: istore_3       
        //     8: getstatic       neat/system/f.f:Lneat/i;
        //    11: invokevirtual   neat/i.f:()Lneat/r;
        //    14: astore          4
        //    16: goto            39
        //    19: aload           4
        //    21: invokeinterface neat/r.b:()Ljava/lang/Object;
        //    26: checkcast       Lneat/system/f;
        //    29: astore          5
        //    31: iload_3        
        //    32: aload           5
        //    34: getfield        neat/system/f.p:I
        //    37: iadd           
        //    38: istore_3       
        //    39: aload           4
        //    41: invokeinterface neat/r.a:()Z
        //    46: ifne            19
        //    49: aload           4
        //    51: invokeinterface neat/r.f:()V
        //    56: iload_3        
        //    57: putstatic       neat/system/f.h:I
        //    60: iload_3        
        //    61: istore_2       
        //    62: jsr             75
        //    65: iload_2        
        //    66: ireturn        
        //    67: aload_0        
        //    68: monitorexit    
        //    69: goto            80
        //    72: aload_0        
        //    73: monitorexit    
        //    74: athrow         
        //    75: astore_1       
        //    76: aload_0        
        //    77: monitorexit    
        //    78: ret             1
        //    80: nop            
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  6      72     72     75     Any
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
    
    static Class a(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public f(final Class clazz) {
        this(clazz, 10);
    }
    
    public f(final Class clazz, final int s) {
        super(clazz);
        this.k = clazz.getName();
        this.j = this.a(this, clazz);
        this.q = new Object[0];
        this.l = new Object[0];
        this.m = new String[0];
        if (this == null) {
            throw null;
        }
        this.n = new c(this);
        this.o = new PrintStream(this.n);
        this.s = s;
    }
    
    static {
        final String[] z = new String[2];
        final int n = 0;
        final char[] charArray = "\u0018o.\u0016N\u001f".toCharArray();
        int length;
        int n3;
        final int n2 = n3 = (length = charArray.length);
        int n4 = 0;
        while (true) {
            Label_0098: {
                if (n2 > 1) {
                    break Label_0098;
                }
                length = (n3 = n4);
                do {
                    final char c = charArray[n3];
                    char c2 = '\0';
                    switch (n4 % 5) {
                        case 0: {
                            c2 = 'v';
                            break;
                        }
                        case 1: {
                            c2 = '\n';
                            break;
                        }
                        case 2: {
                            c2 = 'O';
                            break;
                        }
                        case 3: {
                            c2 = 'b';
                            break;
                        }
                        default: {
                            c2 = '`';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n4;
                } while (n2 == 0);
            }
            if (n2 > n4) {
                continue;
            }
            break;
        }
        z[n] = new String(charArray).intern();
        final int n5 = 1;
        final char[] charArray2 = "\"b&\u0011@\u0019h%\u0007\u0003\u0002*&\u0011@\u0017f=\u0007\u0001\u0012so\u0006\u0005\u001ao;\u0007\u0004Ve=B\u0014\u001eo=\u0007@\u0017x*B\r\u0019x*B\u0004\u0013f*\u0016\t\u0018mo\u0016\b\u0017do\f\u0005\u00010".toCharArray();
        int length2;
        int n7;
        final int n6 = n7 = (length2 = charArray2.length);
        int n8 = 0;
        while (true) {
            Label_0214: {
                if (n6 > 1) {
                    break Label_0214;
                }
                length2 = (n7 = n8);
                do {
                    final char c3 = charArray2[n7];
                    char c4 = '\0';
                    switch (n8 % 5) {
                        case 0: {
                            c4 = 'v';
                            break;
                        }
                        case 1: {
                            c4 = '\n';
                            break;
                        }
                        case 2: {
                            c4 = 'O';
                            break;
                        }
                        case 3: {
                            c4 = 'b';
                            break;
                        }
                        default: {
                            c4 = '`';
                            break;
                        }
                    }
                    charArray2[length2] = (char)(c3 ^ c4);
                    ++n8;
                } while (n6 == 0);
            }
            if (n6 <= n8) {
                z[n5] = new String(charArray2).intern();
                neat.system.f.z = z;
                neat.system.f.e = new Object();
                neat.system.f.f = null;
                neat.system.f.g = null;
                neat.system.f.h = 0;
                neat.system.f.i = null;
                return;
            }
            continue;
        }
    }
}
