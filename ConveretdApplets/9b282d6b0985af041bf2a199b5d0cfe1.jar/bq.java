import java.awt.Dimension;
import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.Toolkit;
import java.awt.Image;
import java.io.IOException;
import java.io.DataInputStream;
import java.net.URL;
import java.io.InputStream;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class bq extends bp
{
    protected Vector a;
    protected InputStream b;
    protected w c;
    protected URL d;
    boolean e;
    protected boolean f;
    protected boolean g;
    protected Object h;
    
    void a(final g g) {
        this.a.addElement(g);
    }
    
    bq(final URL d) {
        this.a = new Vector();
        this.e = (System.getProperty("os.name").startsWith("Win") && System.getProperty("java.vendor").startsWith("Netscape") && System.getProperty("java.version").compareTo("1.1") >= 0);
        this.f = false;
        this.g = false;
        this.h = new Object();
        this.d = d;
    }
    
    void a(final InputStream b) {
        this.b = b;
    }
    
    protected int a(final byte[] array, final int n) throws RuntimeException {
        for (int i = array.length - 2; i > n; --i) {
            if (array[i] == -1) {
                if (array[i + 1] == -39) {
                    return i;
                }
                if (array[i + 1] == -38) {
                    return i;
                }
            }
        }
        throw new RuntimeException();
    }
    
    void a(final w c) {
        this.c = c;
        this.start();
    }
    
    protected byte[] a(final DataInputStream dataInputStream) throws IOException {
        byte[] b = new byte[6];
        dataInputStream.readFully(b, 0, 6);
        int n2;
        for (int n = 2; b[n + 1] != -64; n += 2 + n2) {
            if (b[n + 1] == -38) {
                return b;
            }
            n2 = (b[n + 2] << 8 & 0xFF00) + (b[n + 3] & 0xFF);
            b = this.b(b, n + 4 + (n2 + 2));
            dataInputStream.readFully(b, n + 4, n2 + 2);
        }
        return b;
    }
    
    protected void a(final w w, final int n) throws IOException, InterruptedException, Exception {
        final byte[] a = this.a(new DataInputStream(this.b));
        int length = a.length;
        final byte[] b = this.b(a, n);
        while (!this.f) {
            Thread.yield();
            final int min = Math.min(Math.max(1024, this.b.available()), b.length - length);
            if (min == 0) {
                return;
            }
            int i;
            int n2;
            for (i = 0; i < min; i += n2) {
                if (this.e) {
                    n2 = this.b.read(b, length + i, b.length - length - i);
                }
                else {
                    n2 = this.b.read(b, length + i, min - i);
                }
                if (n2 == -1) {
                    throw new IOException();
                }
            }
            length += i;
            Thread.yield();
            try {
                final int a2 = this.a(b, length - i);
                if (b[a2 + 1] != -38) {
                    this.a(w, new bt(this.d).a(b, 0, a2 + 1));
                    this.a(new f(2));
                    return;
                }
                b[a2 + 1] = -39;
                this.a(w, new bt(this.d).a(b, 0, a2 + 1));
                this.a(new f(1));
                b[a2 + 1] = -38;
            }
            catch (RuntimeException ex) {}
        }
        throw new Exception("exit");
    }
    
    protected void a(final w w, final Image image) throws InterruptedException {
        w.a(Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), new c(this))));
        final Dimension c = w.c();
        w.a(new float[] { c.width / 2.0f - 0.5f, c.height / 2.0f - 0.5f });
    }
    
    protected void a(final f f) {
        for (int i = 0; i < this.a.size(); ++i) {
            ((g)this.a.elementAt(i)).a(f);
        }
        try {
            Thread.sleep(0L);
        }
        catch (InterruptedException ex) {}
    }
    
    public void run() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        bq.f:Z
        //     4: iconst_1       
        //     5: if_icmpne       18
        //     8: new             Ljava/lang/Exception;
        //    11: dup            
        //    12: ldc             "exit"
        //    14: invokespecial   java/lang/Exception.<init>:(Ljava/lang/String;)V
        //    17: athrow         
        //    18: aload_0        
        //    19: invokevirtual   bq.a:()V
        //    22: goto            70
        //    25: astore_1       
        //    26: aload_1        
        //    27: athrow         
        //    28: astore_1       
        //    29: aload_1        
        //    30: invokevirtual   java/lang/Throwable.getMessage:()Ljava/lang/String;
        //    33: ldc             "exit"
        //    35: if_acmpne       54
        //    38: aload_0        
        //    39: new             Lf;
        //    42: dup            
        //    43: iconst_4       
        //    44: invokespecial   f.<init>:(I)V
        //    47: invokevirtual   bq.a:(Lf;)V
        //    50: jsr             80
        //    53: return         
        //    54: aload_1        
        //    55: invokevirtual   java/lang/Throwable.printStackTrace:()V
        //    58: aload_0        
        //    59: new             Lf;
        //    62: dup            
        //    63: aload_1        
        //    64: invokespecial   f.<init>:(Ljava/lang/Throwable;)V
        //    67: invokevirtual   bq.a:(Lf;)V
        //    70: jsr             80
        //    73: return         
        //    74: astore_2       
        //    75: jsr             80
        //    78: aload_2        
        //    79: athrow         
        //    80: astore_3       
        //    81: aload_0        
        //    82: getfield        bq.a:Ljava/util/Vector;
        //    85: invokevirtual   java/util/Vector.removeAllElements:()V
        //    88: aload_0        
        //    89: getfield        bq.h:Ljava/lang/Object;
        //    92: dup            
        //    93: astore          4
        //    95: monitorenter   
        //    96: aload_0        
        //    97: iconst_1       
        //    98: putfield        bq.g:Z
        //   101: aload_0        
        //   102: iconst_0       
        //   103: putfield        bq.f:Z
        //   106: jsr             116
        //   109: goto            123
        //   112: aload           4
        //   114: monitorexit    
        //   115: athrow         
        //   116: astore          5
        //   118: aload           4
        //   120: monitorexit    
        //   121: ret             5
        //   123: ret             3
        //   125: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                   
        //  -----  -----  -----  -----  -----------------------
        //  0      22     25     28     Ljava/lang/ThreadDeath;
        //  0      22     28     70     Ljava/lang/Throwable;
        //  0      70     74     80     Any
        //  96     106    112    116    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:657)
        //     at java.util.ArrayList.get(ArrayList.java:433)
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:3035)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2445)
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
    
    protected void a() throws IOException, RuntimeException, InterruptedException, Exception {
        this.c.a("DefViewpoint", new float[] { 0.0f, 0.0f, 0.0f, 0.1f });
        this.a(this.c, 0);
    }
    
    protected byte[] b(final byte[] array, final int n) {
        final byte[] array2 = new byte[n];
        System.arraycopy(array, 0, array2, 0, Math.min(array.length, n));
        return array2;
    }
    
    void a(final int n) throws InterruptedException, IllegalThreadStateException {
        if (Thread.currentThread() == this) {
            throw new IllegalThreadStateException();
        }
        synchronized (this.h) {
            if (this.g) {
                // monitorexit(this.h)
                return;
            }
            this.f = true;
        }
        // monitorexit(this.h)
        final long currentTimeMillis = System.currentTimeMillis();
        while (this.f && System.currentTimeMillis() - currentTimeMillis < n) {
            Thread.sleep(10L);
        }
    }
}
