// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.io.OutputStream;
import java.io.DataInputStream;
import java.io.IOException;

public final class ec extends Thread implements az, bo
{
    public long a;
    cv a;
    private bw a;
    cj a;
    private String a;
    private int a;
    private String b;
    private dm a;
    private boolean a;
    private IOException a;
    private int b;
    private DataInputStream a;
    private boolean b;
    
    public ec(final String a, final int a2, final String b, final dm a3) {
        this.a = System.currentTimeMillis();
        this.a = null;
        this.b = 0;
        this.b = false;
        this.a = a;
        this.a = a2;
        this.b = b;
        this.a = a3;
        this.start();
    }
    
    public final void run() {
        try {
            this.a = new cv(this, this.a, this.a, 32768, this.a.getParameter("milestones") != null);
            this.a.a.a(this.b);
            do {
                final cv a2;
                final fj a;
                (a = (a2 = this.a).a).a(1);
                final byte b = a.a[a.a];
                final fj fj = a;
                ++fj.a;
                a.c = a.a;
                final byte b2;
                if ((b2 = b) == 111) {
                    a2.a.a();
                    final bw bw = new bw(a2.a.a(), a2.a);
                    a2.a.addElement(bw);
                    a2.a.a(bw);
                }
                else if (b2 == 102) {
                    a2.a.a();
                    a2.a.b();
                }
                else if (b2 == 99) {
                    final bw a3 = a2.a(a2.a.a());
                    a2.a.a();
                    a2.a.removeElement(a3);
                }
                else if (b2 == 100) {
                    a2.a(a2.a.a());
                    final az a4 = a2.a;
                    final DataInputStream a5 = a2.a;
                    final fj a6;
                    (a6 = a2.a).a(2);
                    final int a7;
                    if ((a7 = y.a.a(a6.a, a6.a)) < 0) {
                        throw new IOException("illegal packet length");
                    }
                    final fj fj2 = a6;
                    fj2.a += 2;
                    a6.c = a6.a + a7;
                    a6.a(a7);
                    a4.a(a5);
                    if (!a2.a.a()) {
                        throw new IOException("Unexpected packet length");
                    }
                    continue;
                }
                else {
                    if (b2 != 101) {
                        throw new IOException("Illegal connection proxy command: " + b2);
                    }
                    a2.a(a2.a.a());
                    final az a8 = a2.a;
                    final DataInputStream a9 = a2.a;
                    final fj a10;
                    (a10 = a2.a).a(4);
                    final int b3;
                    if ((b3 = y.a.b(a10.a, a10.a)) < 0) {
                        throw new IOException("illegal packet length");
                    }
                    final fj fj3 = a10;
                    fj3.a += 4;
                    a10.c = a10.a + b3;
                    a10.a(b3);
                    a8.a(a9);
                    if (!a2.a.a()) {
                        throw new IOException("Unexpected packet length");
                    }
                    continue;
                }
            } while (this.a == null);
            throw this.a;
        }
        catch (IOException ex) {
            this.a.b(ex);
            this.a.a.a(this, (this.a != null) ? -1 : -2, null);
            if (this.a != null) {
                this.a.a();
            }
        }
    }
    
    public final synchronized void a(final DataInputStream a) {
        this.a = true;
        this.a = a;
        this.a.a.a(this, 0, null);
        while (this.a) {
            try {
                this.wait();
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public final void a(final bw a) {
        this.a = a;
        this.a = new cj(new ee(a.a));
    }
    
    public final void b() {
        this.a.a.a(this, (this.a != null) ? -1 : -2, null);
    }
    
    public final void a() {
        this.a.a.a(this, -1, null);
    }
    
    public final void a(final int p0, final Object p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: iload_1        
        //     1: iflt            200
        //     4: aload_0        
        //     5: getfield        y/ec.a:Ly/dm;
        //     8: aload_0        
        //     9: getfield        y/ec.a:Ljava/io/DataInputStream;
        //    12: aload_0        
        //    13: getfield        y/ec.b:I
        //    16: invokevirtual   y/dm.a:(Ljava/io/DataInputStream;I)V
        //    19: aload_0        
        //    20: dup            
        //    21: getfield        y/ec.b:I
        //    24: iconst_1       
        //    25: iadd           
        //    26: putfield        y/ec.b:I
        //    29: aload_0        
        //    30: getfield        y/ec.a:Ly/cv;
        //    33: aload_0        
        //    34: getfield        y/ec.a:Ly/bw;
        //    37: astore_2       
        //    38: aconst_null    
        //    39: astore_1       
        //    40: getfield        y/cv.a:Ly/fj;
        //    43: aload_2        
        //    44: getfield        y/bw.a:I
        //    47: istore_2       
        //    48: dup            
        //    49: astore_1       
        //    50: invokevirtual   y/fj.a:()Z
        //    53: ifne            66
        //    56: new             Ljava/io/IOException;
        //    59: dup            
        //    60: ldc             "Unexepected packet length"
        //    62: invokespecial   java/io/IOException.<init>:(Ljava/lang/String;)V
        //    65: athrow         
        //    66: aload_1        
        //    67: getfield        y/fj.b:I
        //    70: aload_1        
        //    71: getfield        y/fj.a:I
        //    74: isub           
        //    75: bipush          7
        //    77: if_icmplt       166
        //    80: aload_1        
        //    81: getfield        y/fj.a:[B
        //    84: aload_1        
        //    85: getfield        y/fj.a:I
        //    88: baload         
        //    89: bipush          100
        //    91: if_icmpne       166
        //    94: aload_1        
        //    95: getfield        y/fj.a:[B
        //    98: aload_1        
        //    99: getfield        y/fj.a:I
        //   102: iconst_1       
        //   103: iadd           
        //   104: invokestatic    y/a.b:([BI)I
        //   107: iload_2        
        //   108: if_icmpne       166
        //   111: aload_1        
        //   112: getfield        y/fj.a:[B
        //   115: aload_1        
        //   116: getfield        y/fj.a:I
        //   119: iconst_5       
        //   120: iadd           
        //   121: invokestatic    y/a.a:([BI)I
        //   124: istore_2       
        //   125: aload_1        
        //   126: getfield        y/fj.b:I
        //   129: aload_1        
        //   130: getfield        y/fj.a:I
        //   133: isub           
        //   134: iload_2        
        //   135: bipush          7
        //   137: iadd           
        //   138: if_icmplt       166
        //   141: aload_1        
        //   142: dup            
        //   143: getfield        y/fj.a:I
        //   146: bipush          7
        //   148: iadd           
        //   149: putfield        y/fj.a:I
        //   152: aload_1        
        //   153: aload_1        
        //   154: getfield        y/fj.a:I
        //   157: iload_2        
        //   158: iadd           
        //   159: putfield        y/fj.c:I
        //   162: iconst_1       
        //   163: goto            167
        //   166: iconst_0       
        //   167: ifne            4
        //   170: goto            179
        //   173: astore_1       
        //   174: aload_0        
        //   175: aload_1        
        //   176: putfield        y/ec.a:Ljava/io/IOException;
        //   179: aload_0        
        //   180: dup            
        //   181: astore_1       
        //   182: monitorenter   
        //   183: aload_0        
        //   184: iconst_0       
        //   185: putfield        y/ec.a:Z
        //   188: aload_0        
        //   189: invokevirtual   java/lang/Object.notify:()V
        //   192: aload_1        
        //   193: monitorexit    
        //   194: return         
        //   195: astore_0       
        //   196: aload_1        
        //   197: monitorexit    
        //   198: aload_0        
        //   199: athrow         
        //   200: aload_0        
        //   201: getfield        y/ec.b:Z
        //   204: ifne            244
        //   207: aload_0        
        //   208: getfield        y/ec.a:Ly/cv;
        //   211: ifnull          221
        //   214: aload_0        
        //   215: getfield        y/ec.a:Ly/cv;
        //   218: invokevirtual   y/cv.a:()V
        //   221: aload_0        
        //   222: getfield        y/ec.a:Ly/dm;
        //   225: aload_0        
        //   226: iload_1        
        //   227: iconst_m1      
        //   228: if_icmpne       235
        //   231: iconst_1       
        //   232: goto            236
        //   235: iconst_0       
        //   236: invokevirtual   y/dm.a:(Ly/ec;Z)V
        //   239: aload_0        
        //   240: iconst_1       
        //   241: putfield        y/ec.b:Z
        //   244: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  4      170    173    179    Ljava/io/IOException;
        //  183    194    195    200    Any
        //  195    198    195    200    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.languages.java.ast.NameVariables.generateNameForVariable(NameVariables.java:264)
        //     at com.strobel.decompiler.languages.java.ast.NameVariables.assignNamesToVariables(NameVariables.java:188)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:276)
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
    
    public final void c() {
        this.a = System.currentTimeMillis();
        try {
            final bw a = this.a;
            a.a.a(a.a);
        }
        catch (IOException ex) {
            this.a.a();
        }
    }
}
