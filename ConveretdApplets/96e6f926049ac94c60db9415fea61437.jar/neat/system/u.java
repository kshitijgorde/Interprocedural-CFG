// 
// Decompiled by Procyon v0.5.30
// 

package neat.system;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import neat.pb;

public class u implements x, cb
{
    private static f b;
    private pb c;
    private x d;
    private boolean e;
    private boolean f;
    private transient Object g;
    private static /* synthetic */ Class h;
    private static String z;
    
    protected void a(final boolean f) {
        this.f = f;
    }
    
    public void a(final g g) {
        if (!this.f) {
            g.a();
            if (this.e) {
                g.b();
            }
            else {
                this.c.a(g);
            }
        }
        else {
            g.a();
            boolean b;
            synchronized (this.g) {
                if (this.e) {
                    b = true;
                }
                else {
                    this.c.a(g);
                    b = false;
                }
            }
            // monitorexit(this.g)
            if (b) {
                g.b();
            }
        }
    }
    
    public boolean a() {
        final g b = this.b();
        if (b == null) {
            return false;
        }
        this.b(b);
        return true;
    }
    
    protected g b() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        neat/system/u.f:Z
        //     4: ifne            18
        //     7: aload_0        
        //     8: getfield        neat/system/u.c:Lneat/pb;
        //    11: invokevirtual   neat/pb.a:()Ljava/lang/Object;
        //    14: checkcast       Lneat/system/g;
        //    17: areturn        
        //    18: aload_0        
        //    19: getfield        neat/system/u.g:Ljava/lang/Object;
        //    22: astore_1       
        //    23: aload_1        
        //    24: monitorenter   
        //    25: aload_0        
        //    26: getfield        neat/system/u.c:Lneat/pb;
        //    29: invokevirtual   neat/pb.a:()Ljava/lang/Object;
        //    32: checkcast       Lneat/system/g;
        //    35: astore_3       
        //    36: jsr             49
        //    39: aload_3        
        //    40: areturn        
        //    41: aload_1        
        //    42: monitorexit    
        //    43: goto            54
        //    46: aload_1        
        //    47: monitorexit    
        //    48: athrow         
        //    49: astore_2       
        //    50: aload_1        
        //    51: monitorexit    
        //    52: ret             2
        //    54: nop            
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  25     46     46     49     Any
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
    
    protected void b(final g g) {
        this.d.receiveEvent(g);
        g.b();
    }
    
    public void a(final x x) {
        if (!this.f) {
            this.d = x;
        }
        else {
            synchronized (this.g) {
                this.d = x;
            }
            // monitorexit(this.g)
        }
    }
    
    public void receiveEvent(final g g) {
        this.a(g);
    }
    
    public static u c() {
        return (u)u.b.a();
    }
    
    public static u b(final boolean b) {
        final u u = (u)neat.system.u.b.a();
        u.a(b);
        return u;
    }
    
    public void f() {
        u.b.a(this);
    }
    
    public void g() {
        this.c = pb.d();
        this.e = false;
        this.f = false;
    }
    
    public void h() {
        this.c.f();
        this.c = null;
        this.d = null;
    }
    
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
        if (!this.f) {
            objectOutputStream.defaultWriteObject();
        }
        else {
            synchronized (this.g) {
                objectOutputStream.defaultWriteObject();
            }
            // monitorexit(this.g)
        }
    }
    
    private void readObject(final ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.g = new Object();
    }
    
    static /* synthetic */ Class a(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public u() {
        this.c = null;
        this.d = null;
        this.e = false;
        this.f = false;
        this.g = new Object();
    }
    
    static {
        final char[] charArray = "\u0000K;=c\u001dW)=(\u0003\u0000/".toCharArray();
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
                            c2 = 'n';
                            break;
                        }
                        case 1: {
                            c2 = '.';
                            break;
                        }
                        case 2: {
                            c2 = 'Z';
                            break;
                        }
                        case 3: {
                            c2 = 'I';
                            break;
                        }
                        default: {
                            c2 = 'M';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                u.z = new String(charArray).intern();
                u.b = new f((u.h != null) ? u.h : (u.h = a(u.z)));
                return;
            }
            continue;
        }
    }
}
