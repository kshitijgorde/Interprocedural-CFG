// 
// Decompiled by Procyon v0.5.30
// 

package neat.system.graphics;

import neat.kb;
import neat.r;
import neat.i;
import neat.ob;
import neat.system.cb;

public abstract class e implements cb, Runnable
{
    private static e a;
    private static Object b;
    private boolean c;
    private boolean d;
    private ob e;
    private Object f;
    private i g;
    private i h;
    public static boolean i;
    private static String[] z;
    
    void a(final d d) {
        if (d.a() == null) {
            return;
        }
        synchronized (this.f) {
            if (this.g.i() < 3) {
                this.h.d(d);
                this.g.a(d);
            }
            this.f.notify();
        }
        // monitorexit(this.f)
    }
    
    void b(final d d) {
        if (d.a() == null) {
            return;
        }
        synchronized (this.f) {
            if (this.h.i() < 3) {
                this.g.d(d);
                this.h.a(d);
            }
            this.f.notify();
        }
        // monitorexit(this.f)
    }
    
    public static final synchronized void a() {
        synchronized (e.b) {
            if (e.a != null && e.a.e.f()) {
                // monitorexit(e.b)
                return;
            }
        }
        // monitorexit(e.b)
        boolean b = false;
        try {
            if (System.getProperty(e.z[0]).equals(e.z[1])) {
                b = true;
            }
        }
        catch (Throwable t) {
            b = false;
        }
        e a = null;
        Label_0117: {
            if (!b) {
                try {
                    a = g.a();
                }
                catch (Throwable t2) {
                    a = null;
                }
                if (a != null) {
                    break Label_0117;
                }
            }
            try {
                a = f.a();
            }
            catch (Throwable t3) {
                a = null;
            }
        }
        synchronized (e.b) {
            e.a = a;
        }
        // monitorexit(e.b)
    }
    
    public static final synchronized e b() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: getstatic       neat/system/graphics/e.b:Ljava/lang/Object;
        //     3: astore_0       
        //     4: aload_0        
        //     5: monitorenter   
        //     6: getstatic       neat/system/graphics/e.a:Lneat/system/graphics/e;
        //     9: astore_2       
        //    10: jsr             23
        //    13: aload_2        
        //    14: areturn        
        //    15: aload_0        
        //    16: monitorexit    
        //    17: goto            28
        //    20: aload_0        
        //    21: monitorexit    
        //    22: athrow         
        //    23: astore_1       
        //    24: aload_0        
        //    25: monitorexit    
        //    26: ret             1
        //    28: nop            
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  6      20     20     23     Any
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
    
    protected abstract h c();
    
    protected abstract void c(final d p0);
    
    protected abstract void d(final d p0);
    
    public void run() {
        final i k = neat.i.k();
        final i i = neat.i.k();
        while (true) {
            synchronized (this.f) {
                if (this.d) {
                    // monitorexit(this.f)
                    break;
                }
                if (this.g.e() && this.h.e()) {
                    try {
                        this.f.wait();
                    }
                    catch (InterruptedException ex) {}
                }
                final r f = this.g.f();
                while (f.a()) {
                    k.a(((d)f.b()).c());
                }
                f.f();
                final r f2 = this.h.f();
                while (f2.a()) {
                    i.a(((d)f2.b()).c());
                }
                f2.f();
                this.g.c();
                this.h.c();
            }
            // monitorexit(this.f)
            final r f3 = k.f();
            while (f3.a()) {
                this.c((d)f3.b());
            }
            f3.f();
            final r f4 = i.f();
            while (f4.a()) {
                this.d((d)f4.b());
            }
            f4.f();
            k.j();
            i.j();
            Thread.yield();
            try {
                Thread.sleep(100L);
            }
            catch (InterruptedException ex2) {}
        }
        k.f();
        i.f();
        synchronized (this.f) {
            if (!this.d) {
                // monitorexit(this.f)
                return;
            }
        }
        // monitorexit(this.f)
        super.f();
    }
    
    protected boolean d() {
        synchronized (this.f) {
            if (this.c) {
                this.d = true;
                this.f.notify();
                // monitorexit(this.f)
                return false;
            }
        }
        // monitorexit(this.f)
        return true;
    }
    
    public void g() {
        this.g = neat.i.k();
        this.h = neat.i.k();
        synchronized (this.f) {
            this.c = false;
            this.d = false;
        }
        // monitorexit(this.f)
        final kb a = kb.a(neat.system.graphics.e.z[2]);
        this.e = ob.a(a);
        a.f();
        this.e.a(this);
        synchronized (this.f) {
            this.c = true;
        }
        // monitorexit(this.f)
    }
    
    public void h() {
        this.e.f();
        this.e = null;
        this.g.f();
        this.g = null;
        this.h.f();
        this.h = null;
        synchronized (this.f) {
            if (neat.system.graphics.e.a == this) {
                neat.system.graphics.e.a = null;
            }
        }
        // monitorexit(this.f)
    }
    
    public abstract void f();
    
    public e() {
        this.e = null;
        this.f = new Object();
        this.g = null;
        this.h = null;
    }
    
    static {
        final String[] z = new String[3];
        final int n = 0;
        final char[] charArray = "\u001c>\\&x\u001e(".toCharArray();
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
                            c2 = 's';
                            break;
                        }
                        case 1: {
                            c2 = 'M';
                            break;
                        }
                        case 2: {
                            c2 = 'r';
                            break;
                        }
                        case 3: {
                            c2 = 'H';
                            break;
                        }
                        default: {
                            c2 = '\u0019';
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
        final char[] charArray2 = "$$\u001c,v\u0004>R\u0006M".toCharArray();
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
                            c4 = 's';
                            break;
                        }
                        case 1: {
                            c4 = 'M';
                            break;
                        }
                        case 2: {
                            c4 = 'r';
                            break;
                        }
                        case 3: {
                            c4 = 'H';
                            break;
                        }
                        default: {
                            c4 = '\u0019';
                            break;
                        }
                    }
                    charArray2[length2] = (char)(c3 ^ c4);
                    ++n8;
                } while (n6 == 0);
            }
            if (n6 > n8) {
                continue;
            }
            break;
        }
        z[n5] = new String(charArray2).intern();
        final int n9 = 2;
        final char[] charArray3 = "\u0000\"\u0007&}S.\u001d&m\u0001\"\u001e".toCharArray();
        int length3;
        int n11;
        final int n10 = n11 = (length3 = charArray3.length);
        int n12 = 0;
        while (true) {
            Label_0330: {
                if (n10 > 1) {
                    break Label_0330;
                }
                length3 = (n11 = n12);
                do {
                    final char c5 = charArray3[n11];
                    char c6 = '\0';
                    switch (n12 % 5) {
                        case 0: {
                            c6 = 's';
                            break;
                        }
                        case 1: {
                            c6 = 'M';
                            break;
                        }
                        case 2: {
                            c6 = 'r';
                            break;
                        }
                        case 3: {
                            c6 = 'H';
                            break;
                        }
                        default: {
                            c6 = '\u0019';
                            break;
                        }
                    }
                    charArray3[length3] = (char)(c5 ^ c6);
                    ++n12;
                } while (n10 == 0);
            }
            if (n10 <= n12) {
                z[n9] = new String(charArray3).intern();
                e.z = z;
                e.a = null;
                e.b = new Object();
                return;
            }
            continue;
        }
    }
}
