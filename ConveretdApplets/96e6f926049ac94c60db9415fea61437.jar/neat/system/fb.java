// 
// Decompiled by Procyon v0.5.30
// 

package neat.system;

import neat.r;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.io.ByteArrayInputStream;
import java.util.zip.ZipException;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URLConnection;
import java.io.InputStream;
import java.io.FileInputStream;
import neat.lb;
import neat.h;
import neat.ob;
import java.net.URL;
import neat.kb;

public class fb implements cb, Runnable
{
    public static final kb a;
    public static final kb b;
    public static final kb c;
    private static f d;
    private volatile boolean e;
    private volatile boolean f;
    private volatile boolean g;
    private volatile boolean h;
    private kb i;
    private boolean j;
    private boolean k;
    private kb l;
    private URL m;
    private kb n;
    private ob o;
    private Object p;
    private int q;
    private byte[] r;
    private h s;
    private byte[] t;
    private byte[] u;
    private byte[] v;
    private static /* synthetic */ Class w;
    private static String[] z;
    
    public void a(final kb kb, final boolean b, final boolean j, final boolean k, final kb kb2, final URL m, final kb kb3) {
        synchronized (this.p) {
            if (this.e) {
                throw new RuntimeException(fb.z[1]);
            }
            this.e = true;
            this.q = 0;
        }
        // monitorexit(this.p)
        this.i = kb.b();
        this.j = j;
        this.k = k;
        if (kb2 != null) {
            this.l = kb2.b();
        }
        if (m != null) {
            this.m = m;
        }
        if (kb3 != null) {
            this.n = kb3.b();
        }
        if (b) {
            try {
                this.e();
                synchronized (this.p) {
                    this.f = true;
                }
                // monitorexit(this.p)
            }
            catch (Throwable t) {
                synchronized (this.p) {
                    this.g = true;
                }
                // monitorexit(this.p)
            }
        }
        else {
            final lb a = lb.a();
            a.c(fb.z[0]);
            a.a(this.i);
            final kb b2 = a.b();
            final ob a2 = ob.a(b2);
            b2.f();
            a2.a(this);
        }
    }
    
    public void a(final kb kb, final qb qb, final boolean k) {
        synchronized (this.p) {
            if (this.e) {
                throw new RuntimeException(fb.z[1]);
            }
            this.e = true;
            this.q = 0;
        }
        // monitorexit(this.p)
        this.j = this.j;
        this.k = k;
        try {
            this.a(kb, qb);
            synchronized (this.p) {
                this.f = true;
            }
            // monitorexit(this.p)
        }
        catch (Throwable t) {
            synchronized (this.p) {
                this.g = true;
            }
            // monitorexit(this.p)
        }
    }
    
    public boolean a() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        neat/system/fb.p:Ljava/lang/Object;
        //     4: astore_1       
        //     5: aload_1        
        //     6: monitorenter   
        //     7: aload_0        
        //     8: getfield        neat/system/fb.e:Z
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
    
    public boolean b() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        neat/system/fb.p:Ljava/lang/Object;
        //     4: astore_1       
        //     5: aload_1        
        //     6: monitorenter   
        //     7: aload_0        
        //     8: getfield        neat/system/fb.f:Z
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
    
    public int c() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        neat/system/fb.p:Ljava/lang/Object;
        //     4: astore_1       
        //     5: aload_1        
        //     6: monitorenter   
        //     7: aload_0        
        //     8: invokevirtual   neat/system/fb.a:()Z
        //    11: ifne            21
        //    14: iconst_0       
        //    15: istore_3       
        //    16: jsr             54
        //    19: iload_3        
        //    20: ireturn        
        //    21: aload_0        
        //    22: invokevirtual   neat/system/fb.b:()Z
        //    25: ifeq            36
        //    28: bipush          100
        //    30: istore_3       
        //    31: jsr             54
        //    34: iload_3        
        //    35: ireturn        
        //    36: aload_0        
        //    37: getfield        neat/system/fb.q:I
        //    40: istore_3       
        //    41: jsr             54
        //    44: iload_3        
        //    45: ireturn        
        //    46: aload_1        
        //    47: monitorexit    
        //    48: goto            59
        //    51: aload_1        
        //    52: monitorexit    
        //    53: athrow         
        //    54: astore_2       
        //    55: aload_1        
        //    56: monitorexit    
        //    57: ret             2
        //    59: nop            
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  7      51     51     54     Any
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
    
    public boolean d() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        neat/system/fb.p:Ljava/lang/Object;
        //     4: astore_1       
        //     5: aload_1        
        //     6: monitorenter   
        //     7: aload_0        
        //     8: getfield        neat/system/fb.g:Z
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
    
    private void e() throws a {
        kb kb;
        if (this.n != null) {
            final lb a = lb.a();
            a.a(this.n);
            a.a(this.i);
            kb = a.b();
        }
        else {
            kb = this.i.b();
        }
        Label_0350: {
            try {
                final ClassLoader classLoader = this.getClass().getClassLoader();
                if (classLoader != null) {
                    final InputStream resourceAsStream = classLoader.getResourceAsStream(kb.toString());
                    if (resourceAsStream != null) {
                        this.a(resourceAsStream, 0);
                        break Label_0350;
                    }
                }
            }
            catch (a a2) {
                throw new RuntimeException(fb.z[5] + this.i.toString() + fb.z[6] + a2.toString());
            }
            catch (Throwable t) {}
            try {
                if (this.m != null) {
                    final URL url = new URL(this.m, this.i.toString());
                    final URLConnection openConnection = url.openConnection();
                    openConnection.setUseCaches(true);
                    final int contentLength = openConnection.getContentLength();
                    final InputStream openStream = url.openStream();
                    if (openStream != null) {
                        this.a(openStream, contentLength);
                        break Label_0350;
                    }
                }
            }
            catch (a a3) {
                throw new RuntimeException(fb.z[5] + this.i.toString() + fb.z[6] + a3.toString());
            }
            catch (Throwable t2) {}
            try {
                final FileInputStream fileInputStream = new FileInputStream(kb.toString());
                if (fileInputStream != null) {
                    this.a(fileInputStream, 0);
                }
            }
            catch (a a4) {
                throw new RuntimeException(fb.z[5] + this.i.toString() + fb.z[6] + a4.toString());
            }
            catch (Throwable t3) {}
        }
        kb.f();
        synchronized (this.p) {
            this.q = 100;
        }
        // monitorexit(this.p)
        if (this.o != null) {
            Thread.yield();
        }
    }
    
    private void a(final InputStream inputStream, final int n) throws IOException {
        if (inputStream == null) {
            throw new IOException(fb.z[7]);
        }
        final BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        int n2 = 0;
        while (true) {
            if (n2 >= this.t.length) {
                final byte[] t = new byte[n2 + 100000];
                System.arraycopy(this.t, 0, t, 0, n2);
                this.t = t;
            }
            int n3 = this.t.length - n2;
            if (n3 >= 10000) {
                n3 = 10000;
            }
            final int read = bufferedInputStream.read(this.t, n2, n3);
            if (read < 0) {
                break;
            }
            n2 += read;
            int q;
            if (n > 0) {
                q = n2 * 90 / n;
            }
            else {
                q = n2 * 90 / 1000000;
            }
            synchronized (this.p) {
                this.q = q;
            }
            // monitorexit(this.p)
            if (this.o == null) {
                continue;
            }
            Thread.yield();
        }
        synchronized (this.p) {
            this.q = 90;
        }
        // monitorexit(this.p)
        if (this.o != null) {
            Thread.yield();
        }
        if (!this.j) {
            final qb a = qb.a(this.t, 0, n2);
            synchronized (this.p) {
                this.s.a(this.i.b(), a);
                this.q = 100;
            }
            // monitorexit(this.p)
            return;
        }
        try {
            if (this.a(null, this.t, n2)) {
                synchronized (this.p) {
                    this.q = 100;
                }
                // monitorexit(this.p)
                return;
            }
        }
        catch (ZipException ex) {}
        synchronized (this.p) {
            this.q = 100;
        }
        // monitorexit(this.p)
    }
    
    private boolean a(final kb kb, final byte[] array, final int n) throws ZipException, IOException {
        final h e = neat.h.e();
        final h e2 = neat.h.e();
        final ZipInputStream zipInputStream = new ZipInputStream(new ByteArrayInputStream(array, 0, n));
        boolean b = true;
        Label_0335: {
            break Label_0335;
            ZipEntry nextEntry = null;
            do {
                kb kb2;
                if (kb != null) {
                    final lb a = lb.a();
                    a.a(kb);
                    a.c(nextEntry.getName());
                    kb2 = a.b();
                }
                else {
                    kb2 = kb.a(nextEntry.getName());
                }
                int n2 = 0;
                while (true) {
                    if (n2 >= this.v.length) {
                        final byte[] v = new byte[n2 + n2 / 2 + 100000];
                        System.arraycopy(this.v, 0, v, 0, n2);
                        this.v = v;
                    }
                    final int read = zipInputStream.read(this.v, n2, this.v.length - n2);
                    if (read < 0) {
                        break;
                    }
                    n2 += read;
                }
                final qb a2 = qb.a(this.v, 0, n2);
                if (kb2.c(fb.a)) {
                    e.a(kb2, a2);
                }
                else if (kb2.c(fb.b)) {
                    e2.a(kb2, a2);
                }
                else if (kb2.c(fb.c) && this.k) {
                    e2.a(kb2, a2);
                }
                else {
                    synchronized (this.p) {
                        this.s.a(kb2, a2);
                    }
                    // monitorexit(this.p)
                }
                if (this.o != null) {
                    Thread.yield();
                }
                zipInputStream.closeEntry();
                b = false;
                nextEntry = zipInputStream.getNextEntry();
            } while (nextEntry != null);
        }
        if (!b) {
            final r a3 = e2.a();
            while (a3.a()) {
                final kb kb3 = (kb)a3.b();
                final qb qb = (qb)e2.g(kb3);
                try {
                    this.a(kb3, qb);
                }
                catch (ZipException ex) {}
                catch (IOException ex2) {}
            }
            a3.f();
            int n3 = 1;
            while (!e.k()) {
                if (n3 == 0) {
                    final r a4 = e.a();
                    throw new RuntimeException(fb.z[2] + kb + fb.z[3] + e.j() + fb.z[4] + (a4.a() ? a4.b() : null));
                }
                n3 = 0;
                final r a5 = e.a();
            Label_0634_Outer:
                while (a5.a()) {
                    final kb kb4 = (kb)a5.b();
                    qb qb2 = (qb)e.g(kb4);
                    final byte[] b2 = qb2.b();
                    final int a6 = qb2.a();
                    int i = 0;
                    kb b3 = null;
                    kb c = null;
                    final lb a7 = lb.a();
                    while (true) {
                        while (i < a6) {
                            final byte b4 = b2[i++];
                            if (b4 == 0) {
                                Label_1262: {
                                    if (a7.j() != 0) {
                                        b3 = a7.b();
                                        byte[] r = this.r;
                                        int a8 = 0;
                                        synchronized (this.p) {
                                            final qb qb3 = (qb)this.s.g(b3);
                                            if (qb3 != null) {
                                                a8 = qb3.a();
                                                if (r.length < a8) {
                                                    r = new byte[a8 + 10000];
                                                }
                                                System.arraycopy(qb3.b(), 0, r, 0, a8);
                                                qb3.c();
                                            }
                                        }
                                        // monitorexit(this.p)
                                        if (a8 > 0) {
                                            this.r = r;
                                            final int e3 = kb4.e(fb.a);
                                            if (e3 >= 0) {
                                                c = kb4.c(0, e3);
                                                if (c.d() != 0) {
                                                    int n4 = 0;
                                                    while (true) {
                                                        while (i < a6) {
                                                            final int n5 = b2[i++] & 0xFF;
                                                            if (n5 != 0) {
                                                                int n6;
                                                                int n7;
                                                                if (n5 == 1) {
                                                                    if (i + 2 >= a6) {
                                                                        break;
                                                                    }
                                                                    n6 = ((b2[i++] & 0xFF) << 16) + ((b2[i++] & 0xFF) << 8) + (b2[i++] & 0xFF);
                                                                    if (n6 < 0 || n6 >= a8) {
                                                                        break;
                                                                    }
                                                                    if (i + 2 >= a6) {
                                                                        break;
                                                                    }
                                                                    n7 = ((b2[i++] & 0xFF) << 16) + ((b2[i++] & 0xFF) << 8) + (b2[i++] & 0xFF);
                                                                    if (n7 <= 0 || n6 + n7 > a8 || i + n7 > a6) {
                                                                        break;
                                                                    }
                                                                }
                                                                else {
                                                                    int n8 = n5;
                                                                    if (n8 >= 254) {
                                                                        n8 -= 254;
                                                                    }
                                                                    n6 = n4 + n8;
                                                                    if (n6 < 0 || n6 >= a8) {
                                                                        break;
                                                                    }
                                                                    if (i >= a6) {
                                                                        break;
                                                                    }
                                                                    n7 = (b2[i++] & 0xFF);
                                                                    if (n7 <= 0 || n6 + n7 > a8 || i + n7 > a6) {
                                                                        break;
                                                                    }
                                                                }
                                                                System.arraycopy(b2, i, r, n6, n7);
                                                                i += n7;
                                                                n4 = n6 + n7;
                                                                continue Label_0634_Outer;
                                                            }
                                                            final qb a9 = qb.a(r, 0, a8);
                                                            synchronized (this.p) {
                                                                final qb qb4 = (qb)this.s.a(c.b(), a9);
                                                                if (qb4 != null) {
                                                                    qb4.f();
                                                                }
                                                            }
                                                            // monitorexit(this.p)
                                                            qb2.c();
                                                            e.c(kb4);
                                                            n3 = 1;
                                                            qb2 = null;
                                                            break Label_1262;
                                                        }
                                                        continue;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                if (qb2 != null) {
                                    qb2.c();
                                }
                                if (b3 != null) {
                                    b3.f();
                                }
                                if (c != null) {
                                    c.f();
                                    continue Label_0634_Outer;
                                }
                                continue Label_0634_Outer;
                            }
                            else {
                                a7.a((char)b4);
                            }
                        }
                        continue;
                    }
                }
                a5.f();
            }
        }
        e.c();
        e.f();
        e2.c();
        e2.f();
        return !b;
    }
    
    private void a(final kb kb, final qb qb) throws ZipException, IOException {
        int c = kb.c(47);
        final int c2 = kb.c(92);
        if (c2 >= 0 && c2 > c) {
            c = c2;
        }
        final int c3 = kb.c(58);
        if (c3 >= 0 && c3 > c) {
            c = c3;
        }
        kb kb2 = null;
        if (c > 0) {
            if (c + 1 < kb.d()) {
                kb2 = kb.c(0, c + 1);
            }
            else {
                kb2 = kb.d(0);
            }
        }
        this.a(kb2, qb.b(), qb.a());
        qb.c();
        if (kb2 != null) {
            kb2.f();
        }
    }
    
    public h f() {
        final h s;
        synchronized (this.p) {
            s = this.s;
            this.s = neat.h.e();
        }
        // monitorexit(this.p)
        return s;
    }
    
    private void i() {
        synchronized (this.p) {
            this.s.c();
        }
        // monitorexit(this.p)
    }
    
    public void run() {
        try {
            this.e();
            synchronized (this.p) {
                this.f = true;
            }
            // monitorexit(this.p)
        }
        catch (Throwable t) {
            synchronized (this.p) {
                this.g = true;
            }
            // monitorexit(this.p)
        }
        synchronized (this.p) {
            if (!this.h) {
                // monitorexit(this.p)
                return;
            }
        }
        // monitorexit(this.p)
        this.f();
    }
    
    public static fb j() {
        return (fb)fb.d.a();
    }
    
    public void f() {
        synchronized (this.p) {
            if (this.e && !this.g && !this.f) {
                this.h = true;
                // monitorexit(this.p)
                return;
            }
        }
        // monitorexit(this.p)
        fb.d.a(this);
    }
    
    public void g() {
        this.e = false;
        this.f = false;
        this.g = false;
        this.h = false;
        this.j = false;
        this.s = neat.h.e();
    }
    
    public void h() {
        if (this.o != null) {
            this.o.f();
            this.o = null;
        }
        this.i();
        this.s.f();
        this.s = null;
        if (this.i != null) {
            this.i.f();
            this.i = null;
        }
        if (this.l != null) {
            this.l.f();
            this.l = null;
        }
        this.m = null;
        if (this.n != null) {
            this.n.f();
            this.n = null;
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
    
    public fb() {
        this.i = null;
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = null;
        this.p = new Object();
        this.r = new byte[0];
        this.s = null;
        this.t = new byte[500000];
        this.u = new byte[500000];
        this.v = new byte[500000];
    }
    
    static {
        fb.z = new String[] { z(z("WyB\u0018lUq\u0003\u0018dOw\u0003")), z(z("xwM[q\u001beW\u001dwO6B\u001bdRx\u0003]")), z(z("xwM[q\u001bfQ\u0013f^eP\\aRpE\\w^eL\twXs\u0002\\w^eL\twXss\u000e`]\u007f[F")), z(z("\u00176V\u0012uIy@\u0019vHsG\\aRpE\\vRlFF")), z(z("\u00176E\u0015wHb\u0019")), z(z("~dQ\u0013w\u001b\u007fM\\w^eL\twXs\u0003\u0010jZrJ\u0012b\u00176E\u0015i^,")), z(z("\u00176F\u000ewTd\u0019")), z(z("rxS\tq\u001beW\u000e`Z{\u0003\u0015v\u001bxV\u0010i\u001b7")), z(z("UsB\b+HoP\b`V8E\u001e")) };
        a = kb.a(z(z("\u0015rJ\u001ac")));
        b = kb.a(z(z("\u0015|B\u000e")));
        c = kb.a(z(z("\u0015dB\u0016")));
        fb.d = new f((fb.w != null) ? fb.w : (fb.w = a(fb.z[8])));
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
        charArray[n] ^= '\u0005';
        return charArray;
    }
    
    private static String z(final char[] array) {
        int length;
        int n2;
        final int n = n2 = (length = array.length);
        int n3 = 0;
        while (true) {
            Label_0085: {
                if (n > 1) {
                    break Label_0085;
                }
                length = (n2 = n3);
                do {
                    final char c = array[n2];
                    char c2 = '\0';
                    switch (n3 % 5) {
                        case 0: {
                            c2 = ';';
                            break;
                        }
                        case 1: {
                            c2 = '\u0016';
                            break;
                        }
                        case 2: {
                            c2 = '#';
                            break;
                        }
                        case 3: {
                            c2 = '|';
                            break;
                        }
                        default: {
                            c2 = '\u0005';
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
