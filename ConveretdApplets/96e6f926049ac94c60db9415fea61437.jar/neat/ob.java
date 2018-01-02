// 
// Decompiled by Procyon v0.5.30
// 

package neat;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectOutputStream;
import neat.system.f;
import neat.system.cb;

public class ob implements cb, Runnable
{
    private static f a;
    public static final kb b;
    public static final kb c;
    public static int d;
    private Object e;
    private kb f;
    private int g;
    private Runnable h;
    private Thread i;
    private boolean j;
    private static /* synthetic */ Class k;
    private static String[] z;
    
    public void a() {
        synchronized (this.e) {
            this.j = true;
        }
        // monitorexit(this.e)
    }
    
    public void a(final Runnable h) {
        synchronized (this.e) {
            if (this.g == 5 || this.g == 6) {
                throw new RuntimeException(ob.z[1]);
            }
            if (this.g == 0 || this.g == 1) {
                // monitorexit(this.e)
                return;
            }
            final int g = this.g;
            this.g = 0;
            this.h = h;
            if (g == 4) {
                this.i = new Thread(this, this.f.toString());
                if (this.j) {
                    this.i.setDaemon(true);
                }
                this.i.start();
            }
            this.e.notify();
        }
        // monitorexit(this.e)
    }
    
    public boolean b() {
        return this.e() == 1;
    }
    
    public boolean c() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        neat/ob.e:Ljava/lang/Object;
        //     4: astore_1       
        //     5: aload_1        
        //     6: monitorenter   
        //     7: aload_0        
        //     8: getfield        neat/ob.g:I
        //    11: iconst_1       
        //    12: if_icmpeq       22
        //    15: iconst_0       
        //    16: istore_3       
        //    17: jsr             51
        //    20: iload_3        
        //    21: ireturn        
        //    22: aload_0        
        //    23: getfield        neat/ob.i:Ljava/lang/Thread;
        //    26: invokestatic    java/lang/Thread.currentThread:()Ljava/lang/Thread;
        //    29: if_acmpeq       36
        //    32: iconst_0       
        //    33: goto            37
        //    36: iconst_1       
        //    37: istore_3       
        //    38: jsr             51
        //    41: iload_3        
        //    42: ireturn        
        //    43: aload_1        
        //    44: monitorexit    
        //    45: goto            56
        //    48: aload_1        
        //    49: monitorexit    
        //    50: athrow         
        //    51: astore_2       
        //    52: aload_1        
        //    53: monitorexit    
        //    54: ret             2
        //    56: nop            
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  7      48     48     51     Any
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
    
    public static void a(final long n) {
        Thread.yield();
        try {
            Thread.sleep(n);
        }
        catch (InterruptedException ex) {}
    }
    
    public void d() {
        synchronized (this.e) {
            if (this.g == 5 || this.g == 6) {
                throw new RuntimeException(ob.z[2]);
            }
            if (this.g != 0 && this.g != 1) {
                // monitorexit(this.e)
                return;
            }
            this.g = 2;
            this.h = null;
            this.e.notify();
        }
        // monitorexit(this.e)
    }
    
    private boolean a(final int p0, final int p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        neat/ob.e:Ljava/lang/Object;
        //     4: astore_3       
        //     5: aload_3        
        //     6: monitorenter   
        //     7: aload_0        
        //     8: getfield        neat/ob.g:I
        //    11: iload_1        
        //    12: if_icmpeq       24
        //    15: iconst_0       
        //    16: istore          5
        //    18: jsr             46
        //    21: iload           5
        //    23: ireturn        
        //    24: aload_0        
        //    25: iload_2        
        //    26: putfield        neat/ob.g:I
        //    29: iconst_1       
        //    30: istore          5
        //    32: jsr             46
        //    35: iload           5
        //    37: ireturn        
        //    38: aload_3        
        //    39: monitorexit    
        //    40: goto            52
        //    43: aload_3        
        //    44: monitorexit    
        //    45: athrow         
        //    46: astore          4
        //    48: aload_3        
        //    49: monitorexit    
        //    50: ret             4
        //    52: nop            
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  7      43     43     46     Any
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
    
    private int e() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        neat/ob.e:Ljava/lang/Object;
        //     4: astore_1       
        //     5: aload_1        
        //     6: monitorenter   
        //     7: aload_0        
        //     8: getfield        neat/ob.g:I
        //    11: istore_3       
        //    12: jsr             25
        //    15: iload_3        
        //    16: ireturn        
        //    17: aload_1        
        //    18: monitorexit    
        //    19: goto            30
        //    22: aload_1        
        //    23: monitorexit    
        //    24: athrow         
        //    25: astore_2       
        //    26: aload_1        
        //    27: monitorexit    
        //    28: ret             2
        //    30: nop            
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  7      22     22     25     Any
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
    
    public boolean f() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        neat/ob.e:Ljava/lang/Object;
        //     4: astore_1       
        //     5: aload_1        
        //     6: monitorenter   
        //     7: aload_0        
        //     8: getfield        neat/ob.g:I
        //    11: ifne            21
        //    14: iconst_1       
        //    15: istore_3       
        //    16: jsr             57
        //    19: iload_3        
        //    20: ireturn        
        //    21: aload_0        
        //    22: getfield        neat/ob.g:I
        //    25: iconst_1       
        //    26: if_icmpeq       36
        //    29: iconst_0       
        //    30: istore_3       
        //    31: jsr             57
        //    34: iload_3        
        //    35: ireturn        
        //    36: aload_0        
        //    37: getfield        neat/ob.i:Ljava/lang/Thread;
        //    40: invokevirtual   java/lang/Thread.isAlive:()Z
        //    43: istore_3       
        //    44: jsr             57
        //    47: iload_3        
        //    48: ireturn        
        //    49: aload_1        
        //    50: monitorexit    
        //    51: goto            62
        //    54: aload_1        
        //    55: monitorexit    
        //    56: athrow         
        //    57: astore_2       
        //    58: aload_1        
        //    59: monitorexit    
        //    60: ret             2
        //    62: nop            
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  7      54     54     57     Any
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
        while (true) {
            Thread.yield();
            final int e = this.e();
            switch (e) {
                case 0: {
                    if (!this.a(e, 1)) {
                        continue;
                    }
                    this.h.run();
                    if (!this.a(1, 3)) {
                        continue;
                    }
                    continue;
                }
                case 2: {
                    this.a(e, 3);
                    continue;
                }
                case 3:
                case 5: {
                    synchronized (this.e) {
                        if (this.g == 3 || this.g == 5) {
                            if (this.g == 3) {
                                this.g = 4;
                                // monitorexit(this.e)
                                return;
                            }
                            if (this.g == 5) {
                                this.g = 6;
                                ob.a.a(this);
                                // monitorexit(this.e)
                                return;
                            }
                        }
                        // monitorexit(this.e)
                    }
                    continue;
                }
                default: {
                    throw new RuntimeException(ob.z[3] + e);
                }
            }
        }
    }
    
    public static ob g() {
        return a((kb)null);
    }
    
    public static ob a(final kb kb) {
        final ob ob = (ob)neat.ob.a.a();
        final lb a = lb.a();
        a.a(neat.ob.b);
        if (kb != null) {
            a.a(kb);
        }
        a.a(neat.ob.c);
        synchronized (neat.ob.b) {
            a.d(neat.ob.d++);
        }
        // monitorexit(ob.b)
        ob.f = a.b();
        return ob;
    }
    
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
        throw new NotSerializableException(((ob.k != null) ? ob.k : (ob.k = a(ob.z[0]))).getName());
    }
    
    private void readObject(final ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        throw new NotSerializableException(((ob.k != null) ? ob.k : (ob.k = a(ob.z[0]))).getName());
    }
    
    public void f() {
        synchronized (this.e) {
            if (this.g != 4) {
                this.g = 5;
                // monitorexit(this.e)
                return;
            }
            this.g = 6;
            ob.a.a(this);
        }
        // monitorexit(this.e)
    }
    
    public void g() {
        synchronized (this.e) {
            if (this.g == 6) {
                this.g = 4;
            }
            else {
                this.g = 3;
            }
            this.j = false;
        }
        // monitorexit(this.e)
    }
    
    public void h() {
        this.h = null;
        if (this.f != null) {
            this.f.f();
            this.f = null;
        }
    }
    
    static /* synthetic */ Class a(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public ob() {
        this.e = new Object();
        this.f = null;
        this.g = 6;
        this.h = null;
        this.i = null;
    }
    
    static {
        ob.z = new String[] { z(z("\u001bu\u001d\u000b^\u001ar")), z(z("6q\u0012X\u0004Uc\b\u001e\u0002\u00010\u001d\u0019\u0004\u0010b\\\u001b\u0015\u0019u\b\u001aPT")), z(z("6q\u0012X\u0004Uc\b\u0010\u0000Uq\u001a\u000b\u0015\u00070\u0018\u001a\u001c\u0010d\u0019_Q")), z(z("6q\u0012X\u0004Uc\u000b\u0016\u0004\u0016x\\\u0016\u001eUd\u0014\u0016\u0003Uc\b\u001e\u0004\u0010*")) };
        ob.a = new f((ob.k != null) ? ob.k : (ob.k = a(ob.z[0])));
        b = kb.a(z(z("!x\u000e\u001a\u0011\u00110\f\u0010\u001f\u0019*\\")));
        c = kb.a(z(z("Y0\u001f\u0011\u0004O")));
        ob.d = 0;
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        int i;
        do {
            i = charArray.length;
            if (i < 2) {
                continue;
            }
            return charArray;
        } while (i == 0);
        final int n = 0;
        charArray[n] ^= 'p';
        return charArray;
    }
    
    private static String z(final char[] array) {
        int length;
        int n2;
        final int n = n2 = (length = array.length);
        int n3 = 0;
        while (true) {
            Label_0086: {
                if (n > 1) {
                    break Label_0086;
                }
                length = (n2 = n3);
                do {
                    final char c = array[n2];
                    char c2 = '\0';
                    switch (n3 % 5) {
                        case 0: {
                            c2 = 'u';
                            break;
                        }
                        case 1: {
                            c2 = '\u0010';
                            break;
                        }
                        case 2: {
                            c2 = '|';
                            break;
                        }
                        case 3: {
                            c2 = '\u007f';
                            break;
                        }
                        default: {
                            c2 = 'p';
                            break;
                        }
                    }
                    array[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                return new String(array).intern();
            }
            continue;
        }
    }
}
