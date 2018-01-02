// 
// Decompiled by Procyon v0.5.30
// 

package loader;

import java.awt.Graphics;
import java.applet.Applet;
import java.net.URLConnection;
import java.io.Serializable;
import java.io.FileInputStream;
import java.net.URL;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import java.awt.Font;
import java.awt.Color;

public class Loader extends a implements Runnable
{
    private Object m;
    private boolean n;
    private Thread o;
    private String[] p;
    private int[] q;
    private String r;
    private Class s;
    private a t;
    private boolean u;
    private boolean v;
    private Object w;
    private boolean x;
    private int y;
    private int z;
    private boolean A;
    private int B;
    private long C;
    private Color D;
    private Object E;
    private boolean F;
    private boolean G;
    private int H;
    private Color I;
    private int J;
    private int K;
    private Color L;
    private String M;
    private int N;
    private int O;
    private Font P;
    private String Q;
    private long R;
    private int S;
    private int T;
    private int U;
    private Color V;
    private String W;
    private int X;
    private int Y;
    private Font Z;
    private String ab;
    private int bb;
    private int cb;
    private int db;
    private int eb;
    private Color fb;
    private Color gb;
    private Color hb;
    private long ib;
    private long jb;
    private int kb;
    private int lb;
    private Color mb;
    private String nb;
    private int ob;
    private int pb;
    private Font qb;
    private String rb;
    private boolean sb;
    private int tb;
    private Hashtable ub;
    private static String[] vb;
    
    private void a(final String[] p3, final int[] q, final String r) {
        synchronized (this.m) {
            if (this.n) {
                // monitorexit(this.m)
                return;
            }
            this.n = true;
            this.p = p3;
            this.q = q;
            this.r = r;
            this.s = null;
            this.t = null;
            (this.o = new Thread(this, Loader.vb[4] + p3 + Loader.vb[3] + r)).start();
        }
        // monitorexit(this.m)
    }
    
    private byte[] a(final InputStream inputStream, final int n) throws IOException {
        if (inputStream == null) {
            throw new IOException(Loader.vb[2]);
        }
        final BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        byte[] array = new byte[0];
        int n2 = 0;
        while (true) {
            if (n2 >= array.length) {
                final byte[] array2 = new byte[n2 + 50000];
                System.arraycopy(array, 0, array2, 0, n2);
                array = array2;
            }
            int n3 = array.length - n2;
            if (n3 >= 10000) {
                n3 = 10000;
            }
            final int read = bufferedInputStream.read(array, n2, n3);
            if (read < 0) {
                break;
            }
            n2 += read;
            this.a(n2 + n);
            Thread.yield();
        }
        final byte[] array3 = new byte[n2];
        System.arraycopy(array, 0, array3, 0, n2);
        Thread.yield();
        return array3;
    }
    
    private void a() {
        final Class s;
        synchronized (this.m) {
            if (this.s == null) {
                // monitorexit(this.m)
                return;
            }
            s = this.s;
        }
        // monitorexit(this.m)
        a t;
        try {
            t = s.newInstance();
        }
        catch (IllegalAccessException ex) {
            if (loader.a.c()) {
                this.a(Loader.vb[17], ex);
                this.d();
            }
            else {
                this.a(Loader.vb[18], ex);
                this.e();
            }
            return;
        }
        catch (InstantiationException ex2) {
            if (loader.a.c()) {
                this.a(Loader.vb[16], ex2);
                this.d();
            }
            else {
                this.a(Loader.vb[19], ex2);
                this.e();
            }
            return;
        }
        synchronized (this.m) {
            this.t = t;
        }
        // monitorexit(this.m)
    }
    
    public a getTargetApplet() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        loader/Loader.m:Ljava/lang/Object;
        //     4: astore_1       
        //     5: aload_1        
        //     6: monitorenter   
        //     7: aload_0        
        //     8: getfield        loader/Loader.t:Lloader/a;
        //    11: astore_3       
        //    12: jsr             25
        //    15: aload_3        
        //    16: areturn        
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
    
    private static boolean d() {
        return System.getProperty(Loader.vb[14]).startsWith(Loader.vb[15]);
    }
    
    public void run() {
        try {
            if (this == null) {
                throw null;
            }
            final f f = new f(this, null);
            final long currentTimeMillis = System.currentTimeMillis();
            final String[] p;
            final int[] q;
            synchronized (this.m) {
                p = this.p;
                q = this.q;
            }
            // monitorexit(this.m)
            if (p != null) {
                final Serializable s = this.getCodeBase();
                int n = 0;
                this.b(0);
                this.a(n);
                for (int i = 0; i < p.length; ++i) {
                    final String s2 = p[i];
                    int n2 = n;
                    int n3 = 1;
                    for (int j = i; j < p.length; ++j) {
                        int n4 = -1;
                        if (q != null && j < q.length) {
                            n4 = q[j];
                        }
                        if (n4 == -1) {
                            if (i > 0) {
                                n4 = n / i;
                            }
                            else {
                                n4 = 1;
                            }
                        }
                        if (j == i) {
                            n3 = n4;
                        }
                        n2 += n4;
                    }
                    this.b(n2);
                    Label_0506: {
                        if (s2 != null) {
                            try {
                                if (s != null) {
                                    final URL url = new URL((URL)s, s2);
                                    if (!d()) {
                                        try {
                                            final URL url2 = new URL(Loader.vb[10] + url + Loader.vb[11]);
                                            if (this == null) {
                                                throw null;
                                            }
                                            final g g = new g(this, null, url2);
                                            int n5 = 0;
                                            Label_0321: {
                                                break Label_0321;
                                                do {
                                                    Thread.yield();
                                                    Thread.sleep(100L);
                                                    this.a(n + n5);
                                                    n5 += 2000;
                                                } while (!loader.g.a(g));
                                            }
                                            this.a(n + n3);
                                            break Label_0506;
                                        }
                                        catch (Throwable t2) {}
                                    }
                                    final URLConnection openConnection = url.openConnection();
                                    openConnection.setUseCaches(true);
                                    final int contentLength = openConnection.getContentLength();
                                    if (contentLength != n3) {
                                        final int n6 = n2 + (contentLength - n3);
                                        n3 = contentLength;
                                        this.b(n6);
                                    }
                                    final InputStream inputStream = openConnection.getInputStream();
                                    if (inputStream != null) {
                                        n3 = this.a(inputStream, n).length;
                                        break Label_0506;
                                    }
                                }
                            }
                            catch (Throwable t3) {}
                            try {
                                final ClassLoader classLoader = this.getClass().getClassLoader();
                                if (classLoader != null) {
                                    final InputStream resourceAsStream = classLoader.getResourceAsStream(s2);
                                    if (resourceAsStream != null) {
                                        n3 = this.a(resourceAsStream, n).length;
                                        break Label_0506;
                                    }
                                }
                            }
                            catch (Throwable t4) {}
                            try {
                                final FileInputStream fileInputStream = new FileInputStream(s2);
                                if (fileInputStream != null) {
                                    n3 = this.a(fileInputStream, n).length;
                                }
                            }
                            catch (Throwable t5) {}
                        }
                    }
                    n += n3;
                    this.a(n);
                }
            }
            Serializable s;
            synchronized (this.m) {
                s = this.r;
            }
            // monitorexit(this.m)
            if (s != null) {
                Class<?> forName;
                try {
                    this.getClass();
                    forName = Class.forName((String)s);
                }
                catch (ClassNotFoundException ex) {
                    forName = null;
                    if (loader.a.c()) {
                        this.a(Loader.vb[8], ex);
                        this.d();
                    }
                    else {
                        this.a(Loader.vb[9], ex);
                        this.e();
                    }
                }
                synchronized (this.m) {
                    this.s = forName;
                }
                // monitorexit(this.m)
                this.a();
            }
            final long n7 = System.currentTimeMillis() - currentTimeMillis;
            long n8 = 0L;
            final long l = this.l();
            if (l >= 0L && l > n7) {
                n8 += l - n7;
            }
            final long m = this.m();
            if (m > 0L) {
                n8 += m;
            }
            if (n8 > 0L) {
                this.a(true);
                long n9 = n8;
                while (true) {
                    long n10 = (n8 - n9) * 100L / n8;
                    if (n10 < 0L) {
                        n10 = 0L;
                    }
                    else if (n10 > 100L) {
                        n10 = 100L;
                    }
                    this.c((int)n10);
                    Thread.yield();
                    try {
                        Thread.sleep(100L);
                    }
                    catch (InterruptedException ex2) {
                        break;
                    }
                    if (n9 <= 0L) {
                        break;
                    }
                    n9 -= 100L;
                }
            }
            loader.f.a(f);
            synchronized (this.m) {
                this.o = null;
                this.n = false;
            }
            // monitorexit(this.m)
            this.j();
        }
        catch (Throwable t) {
            if (loader.a.c()) {
                this.a(Loader.vb[12], t);
                this.d();
            }
            else {
                this.a(Loader.vb[13], t);
                this.e();
            }
        }
    }
    
    private void g() {
        synchronized (this.w) {
            this.i();
            if (!this.u) {
                this.u = true;
                if (this.t != null) {
                    this.t.a((Applet)this);
                    this.t.init();
                    this.t.start();
                }
            }
        }
        // monitorexit(this.w)
    }
    
    private void h() {
        final a targetApplet = this.getTargetApplet();
        synchronized (this.w) {
            this.v = true;
            if (this.u) {
                this.u = false;
                if (targetApplet != null) {
                    targetApplet.stop();
                    targetApplet.destroy();
                }
            }
        }
        // monitorexit(this.w)
    }
    
    private void i() {
        synchronized (this.w) {
            this.v = false;
        }
        // monitorexit(this.w)
    }
    
    private void j() {
        final a targetApplet = this.getTargetApplet();
        synchronized (this.w) {
            if (this.v) {
                // monitorexit(this.w)
                return;
            }
            final String parameter = this.getParameter(Loader.vb[22]);
            if (parameter != null) {
                String parameter2 = this.getParameter(Loader.vb[21]);
                if (parameter2 == null) {
                    parameter2 = Loader.vb[23];
                }
                try {
                    this.getAppletContext().showDocument(new URL(this.getDocumentBase(), parameter), parameter2);
                    // monitorexit(this.w)
                    return;
                }
                catch (Throwable t) {
                    if (loader.a.c()) {
                        this.a(Loader.vb[24], t);
                        this.d();
                    }
                    else {
                        this.a(Loader.vb[20], t);
                        this.e();
                    }
                }
            }
            if (targetApplet != null) {
                this.g();
            }
        }
        // monitorexit(this.w)
    }
    
    private void a(final Graphics graphics, final boolean b) {
        final a targetApplet = this.getTargetApplet();
        synchronized (this.w) {
            if (this.v) {
                // monitorexit(this.w)
                return;
            }
            if (targetApplet != null && this.u) {
                if (b) {
                    targetApplet.paint(graphics);
                }
                else {
                    targetApplet.update(graphics);
                }
            }
            else {
                this.b(graphics, b);
            }
        }
        // monitorexit(this.w)
    }
    
    private void k() {
        synchronized (this.E) {
            this.x = false;
            for (int i = 0; i >= 0; ++i) {
                switch (i) {
                    case 0: {
                        this.I = new Color(this.getParameter(Loader.vb[28], i, 0));
                        break;
                    }
                    default: {
                        i = -2;
                        break;
                    }
                }
            }
            String parameter = null;
            for (int j = 0; j >= 0; ++j) {
                switch (j) {
                    case 0: {
                        this.J = this.getParameter(Loader.vb[26], j, -1);
                        break;
                    }
                    case 1: {
                        this.K = this.getParameter(Loader.vb[26], j, -1);
                        break;
                    }
                    case 2: {
                        this.L = new Color(this.getParameter(Loader.vb[26], j, 16777215));
                        break;
                    }
                    case 3: {
                        this.M = this.getParameter(Loader.vb[26], j, null);
                        break;
                    }
                    case 4: {
                        parameter = this.getParameter(Loader.vb[26], j, null);
                        break;
                    }
                    case 5: {
                        this.O = this.getParameter(Loader.vb[26], j, -1);
                        break;
                    }
                    case 6: {
                        this.Q = this.getParameter(Loader.vb[26], j, null, true);
                        break;
                    }
                    default: {
                        j = -2;
                        break;
                    }
                }
            }
            if (this.M == null) {
                try {
                    this.M = this.getGraphics().getFont().getName();
                }
                catch (Throwable t) {}
            }
            if (this.M != null && this.O > 0) {
                this.N = 0;
                if (parameter != null) {
                    if (parameter.indexOf("b") >= 0) {
                        this.N = 1;
                    }
                    else if (parameter.indexOf("i") >= 0) {
                        this.N = 2;
                    }
                }
                this.P = new Font(this.M, this.N, this.O);
            }
            else {
                this.P = null;
            }
            String parameter2 = null;
            for (int k = 0; k >= 0; ++k) {
                switch (k) {
                    case 0: {
                        this.S = this.getParameter(Loader.vb[29], k, 0);
                        break;
                    }
                    case 1: {
                        this.T = this.getParameter(Loader.vb[29], k, -1);
                        break;
                    }
                    case 2: {
                        this.U = this.getParameter(Loader.vb[29], k, -1);
                        break;
                    }
                    case 3: {
                        this.V = new Color(this.getParameter(Loader.vb[29], k, 16777215));
                        break;
                    }
                    case 4: {
                        this.W = this.getParameter(Loader.vb[29], k, null);
                        break;
                    }
                    case 5: {
                        parameter2 = this.getParameter(Loader.vb[29], k, null);
                        break;
                    }
                    case 6: {
                        this.Y = this.getParameter(Loader.vb[29], k, -1);
                        break;
                    }
                    case 7: {
                        this.ab = this.getParameter(Loader.vb[29], k, null, true);
                        break;
                    }
                    default: {
                        k = -2;
                        break;
                    }
                }
            }
            if (this.W == null) {
                try {
                    this.W = this.getGraphics().getFont().getName();
                }
                catch (Throwable t2) {}
            }
            if (this.W != null && this.Y > 0) {
                this.X = 0;
                if (parameter2 != null) {
                    if (parameter2.indexOf("b") >= 0) {
                        this.X = 1;
                    }
                    else if (parameter2.indexOf("i") >= 0) {
                        this.X = 2;
                    }
                }
                this.Z = new Font(this.W, this.X, this.Y);
            }
            else {
                this.Z = null;
            }
            for (int l = 0; l >= 0; ++l) {
                switch (l) {
                    case 0: {
                        this.bb = this.getParameter(Loader.vb[27], l, -1);
                        break;
                    }
                    case 1: {
                        this.cb = this.getParameter(Loader.vb[27], l, -1);
                        break;
                    }
                    case 2: {
                        this.db = this.getParameter(Loader.vb[27], l, -1);
                        break;
                    }
                    case 3: {
                        this.eb = this.getParameter(Loader.vb[27], l, -1);
                        break;
                    }
                    case 4: {
                        this.fb = new Color(this.getParameter(Loader.vb[27], l, 65280));
                        break;
                    }
                    case 5: {
                        this.gb = new Color(this.getParameter(Loader.vb[27], l, 255));
                        break;
                    }
                    case 6: {
                        this.hb = new Color(this.getParameter(Loader.vb[27], l, 16711680));
                        break;
                    }
                    default: {
                        l = -2;
                        break;
                    }
                }
            }
            if (this.bb >= 0) {
                this.bb += this.tb;
            }
            if (this.cb >= 0) {
                this.cb += this.tb;
            }
            if (this.db > 0) {
                this.db -= this.tb * 2;
            }
            if (this.eb > 0) {
                this.eb -= this.tb * 2;
            }
            String parameter3 = null;
            for (int n = 0; n >= 0; ++n) {
                switch (n) {
                    case 0: {
                        this.ib = this.getParameter(Loader.vb[25], n, -1);
                        break;
                    }
                    case 1: {
                        this.jb = this.getParameter(Loader.vb[25], n, -1);
                        break;
                    }
                    case 2: {
                        this.sb = this.getParameter(Loader.vb[25], n, false);
                        break;
                    }
                    case 3: {
                        this.kb = this.getParameter(Loader.vb[25], n, -1);
                        break;
                    }
                    case 4: {
                        this.lb = this.getParameter(Loader.vb[25], n, -1);
                        break;
                    }
                    case 5: {
                        this.mb = new Color(this.getParameter(Loader.vb[25], n, 16777215));
                        break;
                    }
                    case 6: {
                        this.nb = this.getParameter(Loader.vb[25], n, null);
                        break;
                    }
                    case 7: {
                        parameter3 = this.getParameter(Loader.vb[25], n, null);
                        break;
                    }
                    case 8: {
                        this.pb = this.getParameter(Loader.vb[25], n, -1);
                        break;
                    }
                    case 9: {
                        this.rb = this.getParameter(Loader.vb[25], n, null, true);
                        break;
                    }
                    default: {
                        n = -2;
                        break;
                    }
                }
            }
            if (this.nb == null) {
                try {
                    this.nb = this.getGraphics().getFont().getName();
                }
                catch (Throwable t3) {}
            }
            if (this.nb != null && this.pb > 0) {
                this.ob = 0;
                if (parameter3 != null) {
                    if (parameter3.indexOf("b") >= 0) {
                        this.ob = 1;
                    }
                    else if (parameter3.indexOf("i") >= 0) {
                        this.ob = 2;
                    }
                }
                this.qb = new Font(this.nb, this.ob, this.pb);
            }
            else {
                this.qb = null;
            }
            this.x = true;
            this.y = 0;
            this.z = 0;
            this.A = false;
            this.C = System.currentTimeMillis();
            this.R = System.currentTimeMillis();
        }
        // monitorexit(this.E)
        this.b(true);
    }
    
    private void a(final int y) {
        synchronized (this.E) {
            this.y = y;
            if (this.y >= this.z) {
                this.z = this.y;
            }
        }
        // monitorexit(this.E)
        this.b(false);
    }
    
    private void b(final int z) {
        synchronized (this.E) {
            this.z = z;
        }
        // monitorexit(this.E)
        this.b(false);
    }
    
    private void a(final boolean a) {
        synchronized (this.E) {
            if (this.A == a) {
                // monitorexit(this.E)
                return;
            }
            this.A = a;
            this.B = 0;
        }
        // monitorexit(this.E)
        this.b(true);
    }
    
    private void c(final int b) {
        synchronized (this.E) {
            this.B = b;
        }
        // monitorexit(this.E)
        this.b(false);
    }
    
    private long l() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        loader/Loader.E:Ljava/lang/Object;
        //     4: astore_1       
        //     5: aload_1        
        //     6: monitorenter   
        //     7: aload_0        
        //     8: getfield        loader/Loader.ib:J
        //    11: lstore_3       
        //    12: jsr             25
        //    15: lload_3        
        //    16: lreturn        
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
    
    private long m() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        loader/Loader.E:Ljava/lang/Object;
        //     4: astore_1       
        //     5: aload_1        
        //     6: monitorenter   
        //     7: aload_0        
        //     8: getfield        loader/Loader.jb:J
        //    11: lstore_3       
        //    12: jsr             25
        //    15: lload_3        
        //    16: lreturn        
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
    
    private Color a(final Color color) {
        final long n = System.currentTimeMillis() - this.C;
        final int n2 = 1000;
        final int n3 = (int)(n % n2);
        int n4;
        if (n3 < n2 / 2) {
            n4 = n2 / 2 - n3;
        }
        else {
            n4 = n3 - n2 / 2;
        }
        final int n5 = 50 * n4 / (n2 / 2) + 50;
        final int n6 = color.getRed() * n5 / 100;
        final int n7 = color.getGreen() * n5 / 100;
        final int n8 = color.getBlue() * n5 / 100;
        if (this.D.getRed() != n6 || this.D.getGreen() != n7 || this.D.getBlue() != n8) {
            this.D = new Color(n6, n7, n8);
        }
        return this.D;
    }
    
    private void b(final boolean b) {
        synchronized (this.E) {
            if (b && !this.G) {}
            this.G |= b;
            int h = this.H;
            if (this.A) {
                if (this.sb) {
                    h = this.db * this.B / 100;
                }
            }
            else if (this.z == 0) {
                h = 0;
            }
            else {
                h = this.db * this.y / this.z;
                if (h > this.db) {
                    h = this.db;
                }
                if (h < this.H) {
                    h = this.H;
                }
            }
            if (h != this.H) {
                this.H = h;
            }
            if (true && !this.F) {
                this.F = true;
                this.repaint();
            }
        }
        // monitorexit(this.E)
    }
    
    private void b(final Graphics graphics, final boolean b) {
        if (this.b()) {
            if (b) {
                this.b(graphics);
            }
            else {
                this.a(graphics);
            }
            return;
        }
        synchronized (this.E) {
            if (!this.x) {
                // monitorexit(this.E)
                return;
            }
            final boolean b2 = b | this.G;
            this.G = false;
            if (b2) {
                this.F = true;
            }
            if (!this.F) {
                // monitorexit(this.E)
                return;
            }
            this.F = false;
            final int h = this.H;
            final int width = this.getSize().width;
            final int height = this.getSize().height;
            if (b2) {
                graphics.setColor(this.I);
                graphics.fillRect(0, 0, width, height);
            }
            if ((!this.A || this.sb) && this.bb >= 0 && this.cb >= 0 && this.db > 0 && this.eb > 0) {
                graphics.setColor(this.fb);
                graphics.drawRect(this.bb - this.tb, this.cb - this.tb, this.db + this.tb, this.eb + this.tb);
                graphics.setColor(this.gb);
                graphics.fillRect(this.bb, this.cb, this.H, this.eb);
                graphics.setColor(this.hb);
                graphics.fillRect(this.bb + this.H, this.cb, this.db - this.H, this.eb);
            }
            if (this.A) {
                if (this.rb != null && this.kb >= 0 && this.lb >= 0) {
                    if (this.qb != null) {
                        graphics.setFont(this.qb);
                    }
                    graphics.setColor(this.a(this.mb));
                    graphics.drawString(this.rb, this.kb, this.lb);
                }
            }
            else {
                if (this.Q != null && this.J >= 0 && this.K >= 0) {
                    if (this.P != null) {
                        graphics.setFont(this.P);
                    }
                    graphics.setColor(this.a(this.L));
                    graphics.drawString(this.Q, this.J, this.K);
                }
                if ((int)(System.currentTimeMillis() - this.R) / 1000 > this.S && this.ab != null && this.T >= 0 && this.U >= 0) {
                    if (this.Z != null) {
                        graphics.setFont(this.Z);
                    }
                    graphics.setColor(this.V);
                    graphics.drawString(this.ab, this.T, this.U);
                }
            }
        }
        // monitorexit(this.E)
    }
    
    public String getParameterDefault(final String s) {
        if (s == null) {
            return null;
        }
        String parameter = super.getParameter(s);
        if (parameter == null) {
            parameter = this.ub.get(s);
        }
        if (parameter == null) {
            return null;
        }
        if (parameter.length() == 0) {
            return null;
        }
        return parameter;
    }
    
    public String getParameter(final String s, final String s2) {
        final String parameter = this.getParameter(s);
        if (parameter == null) {
            return s2;
        }
        return parameter;
    }
    
    public String getParameter(final String s, final int n, final String s2, final boolean b) {
        final String parameter = this.getParameter(s);
        if (parameter == null) {
            return s2;
        }
        int n2 = 0;
        int n3 = -1;
        for (int i = 0; i <= n; ++i) {
            if (n3 >= parameter.length()) {
                return s2;
            }
            n2 = n3 + 1;
            n3 = parameter.indexOf(",", n2);
            if (n3 < 0) {
                n3 = parameter.length();
            }
        }
        final String substring = parameter.substring(n2, b ? parameter.length() : n3);
        if (substring.length() == 0) {
            return null;
        }
        return substring;
    }
    
    public String getParameter(final String s, final int n, final String s2) {
        return this.getParameter(s, n, s2, false);
    }
    
    public int getParameter(final String s, final int n, final int n2) {
        final String parameter = this.getParameter(s, n, null);
        if (parameter == null) {
            return n2;
        }
        if (parameter.length() > 1 && parameter.charAt(0) == '#') {
            final int n3 = Integer.MAX_VALUE;
            final int n4 = n3 >> 4;
            int n5 = 0;
            int n6 = 1;
            while (true) {
                final char char1 = parameter.charAt(n6);
                char c;
                if (char1 >= '0' && char1 <= '9') {
                    c = (char)(char1 - '0');
                }
                else if (char1 >= 'a' && char1 <= 'f') {
                    c = (char)(char1 - 'a' + '\n');
                }
                else {
                    if (char1 < 'A' || char1 > 'F') {
                        return n2;
                    }
                    c = (char)(char1 - 'A' + '\n');
                }
                if (n5 > n4) {
                    return n2;
                }
                final int n7 = n5 << 4;
                if (n7 > n3 - c) {
                    return n2;
                }
                n5 = n7 + c;
                if (++n6 >= parameter.length()) {
                    return n5;
                }
            }
        }
        else {
            try {
                return Integer.parseInt(parameter);
            }
            catch (Throwable t) {
                return n2;
            }
        }
    }
    
    public boolean getParameter(final String s, final int n, final boolean b) {
        final String parameter = this.getParameter(s, n, null);
        if (parameter == null) {
            return b;
        }
        return parameter.equals(Loader.vb[1]) || (!parameter.equals(Loader.vb[0]) && b);
    }
    
    public int getParameterNumber(final String s) {
        if (this.getParameter(s) == null) {
            return 0;
        }
        int n;
        for (n = 0; this.getParameter(s, n, null) != null; ++n) {}
        return n;
    }
    
    public String getParameter(final String s) {
        return this.getParameterDefault(s);
    }
    
    public final void start() {
        this.i();
        this.k();
        this.repaint();
        final int parameterNumber = this.getParameterNumber(Loader.vb[6]);
        String[] array;
        if (parameterNumber == 0) {
            array = null;
        }
        else {
            array = new String[parameterNumber];
            for (int i = 0; i < array.length; ++i) {
                array[i] = this.getParameter(Loader.vb[6], i, null);
            }
        }
        final int parameterNumber2 = this.getParameterNumber(Loader.vb[7]);
        int[] array2;
        if (parameterNumber2 == 0) {
            array2 = null;
        }
        else {
            array2 = new int[parameterNumber2];
            for (int j = 0; j < array2.length; ++j) {
                array2[j] = this.getParameter(Loader.vb[7], j, 1) * 1000;
            }
        }
        this.a(array, array2, this.getParameter(Loader.vb[5]));
    }
    
    public final void stop() {
        this.h();
    }
    
    public final void update(final Graphics graphics) {
        if (graphics == null) {
            return;
        }
        if (this.b()) {
            this.a(graphics);
            return;
        }
        this.a(graphics, false);
    }
    
    public final void paint(final Graphics graphics) {
        if (graphics == null) {
            return;
        }
        if (this.b()) {
            this.b(graphics);
            return;
        }
        this.a(graphics, true);
    }
    
    public final void paintAll(final Graphics graphics) {
        this.paint(graphics);
    }
    
    static void a(final Loader loader, final boolean b) {
        loader.b(b);
    }
    
    public Loader() {
        this.m = new Object();
        this.n = false;
        this.o = null;
        this.p = null;
        this.q = null;
        this.r = null;
        this.s = null;
        this.t = null;
        this.u = false;
        this.v = false;
        this.w = new Object();
        this.x = false;
        this.y = 0;
        this.z = 0;
        this.A = false;
        this.D = Color.black;
        this.E = new Object();
        this.F = false;
        this.G = true;
        this.H = 0;
        this.tb = 1;
        this.ub = new Hashtable();
    }
    
    static {
        final String[] vb = new String[30];
        final int n = 0;
        final char[] charArray = "IVx6\u001a".toCharArray();
        int length;
        int n3;
        final int n2 = n3 = (length = charArray.length);
        int n4 = 0;
        while (true) {
            Label_0102: {
                if (n2 > 1) {
                    break Label_0102;
                }
                length = (n3 = n4);
                do {
                    final char c = charArray[n3];
                    char c2 = '\0';
                    switch (n4 % 5) {
                        case 0: {
                            c2 = '/';
                            break;
                        }
                        case 1: {
                            c2 = '7';
                            break;
                        }
                        case 2: {
                            c2 = '\u0014';
                            break;
                        }
                        case 3: {
                            c2 = 'E';
                            break;
                        }
                        default: {
                            c2 = '\u007f';
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
        vb[n] = new String(charArray).intern();
        final int n5 = 1;
        final char[] charArray2 = "[Ea ".toCharArray();
        int length2;
        int n7;
        final int n6 = n7 = (length2 = charArray2.length);
        int n8 = 0;
        while (true) {
            Label_0222: {
                if (n6 > 1) {
                    break Label_0222;
                }
                length2 = (n7 = n8);
                do {
                    final char c3 = charArray2[n7];
                    char c4 = '\0';
                    switch (n8 % 5) {
                        case 0: {
                            c4 = '/';
                            break;
                        }
                        case 1: {
                            c4 = '7';
                            break;
                        }
                        case 2: {
                            c4 = '\u0014';
                            break;
                        }
                        case 3: {
                            c4 = 'E';
                            break;
                        }
                        default: {
                            c4 = '\u007f';
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
        vb[n5] = new String(charArray2).intern();
        final int n9 = 2;
        final char[] charArray3 = "fYd0\u000b\u000fD`7\u001aNZ4,\f\u000fYa)\u0013\u000f\u0016".toCharArray();
        int length3;
        int n11;
        final int n10 = n11 = (length3 = charArray3.length);
        int n12 = 0;
        while (true) {
            Label_0338: {
                if (n10 > 1) {
                    break Label_0338;
                }
                length3 = (n11 = n12);
                do {
                    final char c5 = charArray3[n11];
                    char c6 = '\0';
                    switch (n12 % 5) {
                        case 0: {
                            c6 = '/';
                            break;
                        }
                        case 1: {
                            c6 = '7';
                            break;
                        }
                        case 2: {
                            c6 = '\u0014';
                            break;
                        }
                        case 3: {
                            c6 = 'E';
                            break;
                        }
                        default: {
                            c6 = '\u007f';
                            break;
                        }
                    }
                    charArray3[length3] = (char)(c5 ^ c6);
                    ++n12;
                } while (n10 == 0);
            }
            if (n10 > n12) {
                continue;
            }
            break;
        }
        vb[n9] = new String(charArray3).intern();
        final int n13 = 3;
        final char[] charArray4 = "\u0003\u0017w)\u001e\\D.".toCharArray();
        int length4;
        int n15;
        final int n14 = n15 = (length4 = charArray4.length);
        int n16 = 0;
        while (true) {
            Label_0454: {
                if (n14 > 1) {
                    break Label_0454;
                }
                length4 = (n15 = n16);
                do {
                    final char c7 = charArray4[n15];
                    char c8 = '\0';
                    switch (n16 % 5) {
                        case 0: {
                            c8 = '/';
                            break;
                        }
                        case 1: {
                            c8 = '7';
                            break;
                        }
                        case 2: {
                            c8 = '\u0014';
                            break;
                        }
                        case 3: {
                            c8 = 'E';
                            break;
                        }
                        default: {
                            c8 = '\u007f';
                            break;
                        }
                    }
                    charArray4[length4] = (char)(c7 ^ c8);
                    ++n16;
                } while (n14 == 0);
            }
            if (n14 > n16) {
                continue;
            }
            break;
        }
        vb[n13] = new String(charArray4).intern();
        final int n17 = 4;
        final char[] charArray5 = "cXu!\u0016AP4#\u0016CR.".toCharArray();
        int length5;
        int n19;
        final int n18 = n19 = (length5 = charArray5.length);
        int n20 = 0;
        while (true) {
            Label_0570: {
                if (n18 > 1) {
                    break Label_0570;
                }
                length5 = (n19 = n20);
                do {
                    final char c9 = charArray5[n19];
                    char c10 = '\0';
                    switch (n20 % 5) {
                        case 0: {
                            c10 = '/';
                            break;
                        }
                        case 1: {
                            c10 = '7';
                            break;
                        }
                        case 2: {
                            c10 = '\u0014';
                            break;
                        }
                        case 3: {
                            c10 = 'E';
                            break;
                        }
                        default: {
                            c10 = '\u007f';
                            break;
                        }
                    }
                    charArray5[length5] = (char)(c9 ^ c10);
                    ++n20;
                } while (n18 == 0);
            }
            if (n18 > n20) {
                continue;
            }
            break;
        }
        vb[n17] = new String(charArray5).intern();
        final int n21 = 5;
        final char[] charArray6 = "[Vf\"\u001a[\u0019w)\u001e\\D".toCharArray();
        int length6;
        int n23;
        final int n22 = n23 = (length6 = charArray6.length);
        int n24 = 0;
        while (true) {
            Label_0686: {
                if (n22 > 1) {
                    break Label_0686;
                }
                length6 = (n23 = n24);
                do {
                    final char c11 = charArray6[n23];
                    char c12 = '\0';
                    switch (n24 % 5) {
                        case 0: {
                            c12 = '/';
                            break;
                        }
                        case 1: {
                            c12 = '7';
                            break;
                        }
                        case 2: {
                            c12 = '\u0014';
                            break;
                        }
                        case 3: {
                            c12 = 'E';
                            break;
                        }
                        default: {
                            c12 = '\u007f';
                            break;
                        }
                    }
                    charArray6[length6] = (char)(c11 ^ c12);
                    ++n24;
                } while (n22 == 0);
            }
            if (n22 > n24) {
                continue;
            }
            break;
        }
        vb[n21] = new String(charArray6).intern();
        final int n25 = 6;
        final char[] charArray7 = "[Vf\"\u001a[\u0019r,\u0013JD".toCharArray();
        int length7;
        int n27;
        final int n26 = n27 = (length7 = charArray7.length);
        int n28 = 0;
        while (true) {
            Label_0806: {
                if (n26 > 1) {
                    break Label_0806;
                }
                length7 = (n27 = n28);
                do {
                    final char c13 = charArray7[n27];
                    char c14 = '\0';
                    switch (n28 % 5) {
                        case 0: {
                            c14 = '/';
                            break;
                        }
                        case 1: {
                            c14 = '7';
                            break;
                        }
                        case 2: {
                            c14 = '\u0014';
                            break;
                        }
                        case 3: {
                            c14 = 'E';
                            break;
                        }
                        default: {
                            c14 = '\u007f';
                            break;
                        }
                    }
                    charArray7[length7] = (char)(c13 ^ c14);
                    ++n28;
                } while (n26 == 0);
            }
            if (n26 > n28) {
                continue;
            }
            break;
        }
        vb[n25] = new String(charArray7).intern();
        final int n29 = 7;
        final char[] charArray8 = "[Vf\"\u001a[\u0019g,\u0005JD".toCharArray();
        int length8;
        int n31;
        final int n30 = n31 = (length8 = charArray8.length);
        int n32 = 0;
        while (true) {
            Label_0926: {
                if (n30 > 1) {
                    break Label_0926;
                }
                length8 = (n31 = n32);
                do {
                    final char c15 = charArray8[n31];
                    char c16 = '\0';
                    switch (n32 % 5) {
                        case 0: {
                            c16 = '/';
                            break;
                        }
                        case 1: {
                            c16 = '7';
                            break;
                        }
                        case 2: {
                            c16 = '\u0014';
                            break;
                        }
                        case 3: {
                            c16 = 'E';
                            break;
                        }
                        default: {
                            c16 = '\u007f';
                            break;
                        }
                    }
                    charArray8[length8] = (char)(c15 ^ c16);
                    ++n32;
                } while (n30 == 0);
            }
            if (n30 > n32) {
                continue;
            }
            break;
        }
        vb[n29] = new String(charArray8).intern();
        final int n33 = 8;
        final char[] charArray9 = "l[u6\f\u000fRf7\u0010]".toCharArray();
        int length9;
        int n35;
        final int n34 = n35 = (length9 = charArray9.length);
        int n36 = 0;
        while (true) {
            Label_1046: {
                if (n34 > 1) {
                    break Label_1046;
                }
                length9 = (n35 = n36);
                do {
                    final char c17 = charArray9[n35];
                    char c18 = '\0';
                    switch (n36 % 5) {
                        case 0: {
                            c18 = '/';
                            break;
                        }
                        case 1: {
                            c18 = '7';
                            break;
                        }
                        case 2: {
                            c18 = '\u0014';
                            break;
                        }
                        case 3: {
                            c18 = 'E';
                            break;
                        }
                        default: {
                            c18 = '\u007f';
                            break;
                        }
                    }
                    charArray9[length9] = (char)(c17 ^ c18);
                    ++n36;
                } while (n34 == 0);
            }
            if (n34 > n36) {
                continue;
            }
            break;
        }
        vb[n33] = new String(charArray9).intern();
        final int n37 = 9;
        final char[] charArray10 = "l[u6\f\u000f\u00184\u00132\u000fRf7\u0010]".toCharArray();
        int length10;
        int n39;
        final int n38 = n39 = (length10 = charArray10.length);
        int n40 = 0;
        while (true) {
            Label_1166: {
                if (n38 > 1) {
                    break Label_1166;
                }
                length10 = (n39 = n40);
                do {
                    final char c19 = charArray10[n39];
                    char c20 = '\0';
                    switch (n40 % 5) {
                        case 0: {
                            c20 = '/';
                            break;
                        }
                        case 1: {
                            c20 = '7';
                            break;
                        }
                        case 2: {
                            c20 = '\u0014';
                            break;
                        }
                        case 3: {
                            c20 = 'E';
                            break;
                        }
                        default: {
                            c20 = '\u007f';
                            break;
                        }
                    }
                    charArray10[length10] = (char)(c19 ^ c20);
                    ++n40;
                } while (n38 == 0);
            }
            if (n38 > n40) {
                continue;
            }
            break;
        }
        vb[n37] = new String(charArray10).intern();
        final int n41 = 10;
        final char[] charArray11 = "EVf\u007f".toCharArray();
        int length11;
        int n43;
        final int n42 = n43 = (length11 = charArray11.length);
        int n44 = 0;
        while (true) {
            Label_1286: {
                if (n42 > 1) {
                    break Label_1286;
                }
                length11 = (n43 = n44);
                do {
                    final char c21 = charArray11[n43];
                    char c22 = '\0';
                    switch (n44 % 5) {
                        case 0: {
                            c22 = '/';
                            break;
                        }
                        case 1: {
                            c22 = '7';
                            break;
                        }
                        case 2: {
                            c22 = '\u0014';
                            break;
                        }
                        case 3: {
                            c22 = 'E';
                            break;
                        }
                        default: {
                            c22 = '\u007f';
                            break;
                        }
                    }
                    charArray11[length11] = (char)(c21 ^ c22);
                    ++n44;
                } while (n42 == 0);
            }
            if (n42 > n44) {
                continue;
            }
            break;
        }
        vb[n41] = new String(charArray11).intern();
        final int n45 = 11;
        final char[] charArray12 = "\u000e\u0018".toCharArray();
        int length12;
        int n47;
        final int n46 = n47 = (length12 = charArray12.length);
        int n48 = 0;
        while (true) {
            Label_1406: {
                if (n46 > 1) {
                    break Label_1406;
                }
                length12 = (n47 = n48);
                do {
                    final char c23 = charArray12[n47];
                    char c24 = '\0';
                    switch (n48 % 5) {
                        case 0: {
                            c24 = '/';
                            break;
                        }
                        case 1: {
                            c24 = '7';
                            break;
                        }
                        case 2: {
                            c24 = '\u0014';
                            break;
                        }
                        case 3: {
                            c24 = 'E';
                            break;
                        }
                        default: {
                            c24 = '\u007f';
                            break;
                        }
                    }
                    charArray12[length12] = (char)(c23 ^ c24);
                    ++n48;
                } while (n46 == 0);
            }
            if (n46 > n48) {
                continue;
            }
            break;
        }
        vb[n45] = new String(charArray12).intern();
        final int n49 = 12;
        final char[] charArray13 = "cXu!\u0016AP4 \r]Xf".toCharArray();
        int length13;
        int n51;
        final int n50 = n51 = (length13 = charArray13.length);
        int n52 = 0;
        while (true) {
            Label_1526: {
                if (n50 > 1) {
                    break Label_1526;
                }
                length13 = (n51 = n52);
                do {
                    final char c25 = charArray13[n51];
                    char c26 = '\0';
                    switch (n52 % 5) {
                        case 0: {
                            c26 = '/';
                            break;
                        }
                        case 1: {
                            c26 = '7';
                            break;
                        }
                        case 2: {
                            c26 = '\u0014';
                            break;
                        }
                        case 3: {
                            c26 = 'E';
                            break;
                        }
                        default: {
                            c26 = '\u007f';
                            break;
                        }
                    }
                    charArray13[length13] = (char)(c25 ^ c26);
                    ++n52;
                } while (n50 == 0);
            }
            if (n50 > n52) {
                continue;
            }
            break;
        }
        vb[n49] = new String(charArray13).intern();
        final int n53 = 13;
        final char[] charArray14 = "cXu!\u0016AP4j_yz4 \r]Xf".toCharArray();
        int length14;
        int n55;
        final int n54 = n55 = (length14 = charArray14.length);
        int n56 = 0;
        while (true) {
            Label_1646: {
                if (n54 > 1) {
                    break Label_1646;
                }
                length14 = (n55 = n56);
                do {
                    final char c27 = charArray14[n55];
                    char c28 = '\0';
                    switch (n56 % 5) {
                        case 0: {
                            c28 = '/';
                            break;
                        }
                        case 1: {
                            c28 = '7';
                            break;
                        }
                        case 2: {
                            c28 = '\u0014';
                            break;
                        }
                        case 3: {
                            c28 = 'E';
                            break;
                        }
                        default: {
                            c28 = '\u007f';
                            break;
                        }
                    }
                    charArray14[length14] = (char)(c27 ^ c28);
                    ++n56;
                } while (n54 == 0);
            }
            if (n54 > n56) {
                continue;
            }
            break;
        }
        vb[n53] = new String(charArray14).intern();
        final int n57 = 14;
        final char[] charArray15 = "EVb$QYRf6\u0016@Y".toCharArray();
        int length15;
        int n59;
        final int n58 = n59 = (length15 = charArray15.length);
        int n60 = 0;
        while (true) {
            Label_1766: {
                if (n58 > 1) {
                    break Label_1766;
                }
                length15 = (n59 = n60);
                do {
                    final char c29 = charArray15[n59];
                    char c30 = '\0';
                    switch (n60 % 5) {
                        case 0: {
                            c30 = '/';
                            break;
                        }
                        case 1: {
                            c30 = '7';
                            break;
                        }
                        case 2: {
                            c30 = '\u0014';
                            break;
                        }
                        case 3: {
                            c30 = 'E';
                            break;
                        }
                        default: {
                            c30 = '\u007f';
                            break;
                        }
                    }
                    charArray15[length15] = (char)(c29 ^ c30);
                    ++n60;
                } while (n58 == 0);
            }
            if (n58 > n60) {
                continue;
            }
            break;
        }
        vb[n57] = new String(charArray15).intern();
        final int n61 = 15;
        final char[] charArray16 = "\u001e\u0019%".toCharArray();
        int length16;
        int n63;
        final int n62 = n63 = (length16 = charArray16.length);
        int n64 = 0;
        while (true) {
            Label_1886: {
                if (n62 > 1) {
                    break Label_1886;
                }
                length16 = (n63 = n64);
                do {
                    final char c31 = charArray16[n63];
                    char c32 = '\0';
                    switch (n64 % 5) {
                        case 0: {
                            c32 = '/';
                            break;
                        }
                        case 1: {
                            c32 = '7';
                            break;
                        }
                        case 2: {
                            c32 = '\u0014';
                            break;
                        }
                        case 3: {
                            c32 = 'E';
                            break;
                        }
                        default: {
                            c32 = '\u007f';
                            break;
                        }
                    }
                    charArray16[length16] = (char)(c31 ^ c32);
                    ++n64;
                } while (n62 == 0);
            }
            if (n62 > n64) {
                continue;
            }
            break;
        }
        vb[n61] = new String(charArray16).intern();
        final int n65 = 16;
        final char[] charArray17 = "nGd)\u001a[\u0017g1\u001e]C4 \r]Xf".toCharArray();
        int length17;
        int n67;
        final int n66 = n67 = (length17 = charArray17.length);
        int n68 = 0;
        while (true) {
            Label_2006: {
                if (n66 > 1) {
                    break Label_2006;
                }
                length17 = (n67 = n68);
                do {
                    final char c33 = charArray17[n67];
                    char c34 = '\0';
                    switch (n68 % 5) {
                        case 0: {
                            c34 = '/';
                            break;
                        }
                        case 1: {
                            c34 = '7';
                            break;
                        }
                        case 2: {
                            c34 = '\u0014';
                            break;
                        }
                        case 3: {
                            c34 = 'E';
                            break;
                        }
                        default: {
                            c34 = '\u007f';
                            break;
                        }
                    }
                    charArray17[length17] = (char)(c33 ^ c34);
                    ++n68;
                } while (n66 == 0);
            }
            if (n66 > n68) {
                continue;
            }
            break;
        }
        vb[n65] = new String(charArray17).intern();
        final int n69 = 17;
        final char[] charArray18 = "nGd)\u001a[\u0017u&\u001cJDge\u001a]E{7".toCharArray();
        int length18;
        int n71;
        final int n70 = n71 = (length18 = charArray18.length);
        int n72 = 0;
        while (true) {
            Label_2126: {
                if (n70 > 1) {
                    break Label_2126;
                }
                length18 = (n71 = n72);
                do {
                    final char c35 = charArray18[n71];
                    char c36 = '\0';
                    switch (n72 % 5) {
                        case 0: {
                            c36 = '/';
                            break;
                        }
                        case 1: {
                            c36 = '7';
                            break;
                        }
                        case 2: {
                            c36 = '\u0014';
                            break;
                        }
                        case 3: {
                            c36 = 'E';
                            break;
                        }
                        default: {
                            c36 = '\u007f';
                            break;
                        }
                    }
                    charArray18[length18] = (char)(c35 ^ c36);
                    ++n72;
                } while (n70 == 0);
            }
            if (n70 > n72) {
                continue;
            }
            break;
        }
        vb[n69] = new String(charArray18).intern();
        final int n73 = 18;
        final char[] charArray19 = "nGd)\u001a[\u0017u&\u001cJDgeP\u000faYe\u001a]E{7".toCharArray();
        int length19;
        int n75;
        final int n74 = n75 = (length19 = charArray19.length);
        int n76 = 0;
        while (true) {
            Label_2246: {
                if (n74 > 1) {
                    break Label_2246;
                }
                length19 = (n75 = n76);
                do {
                    final char c37 = charArray19[n75];
                    char c38 = '\0';
                    switch (n76 % 5) {
                        case 0: {
                            c38 = '/';
                            break;
                        }
                        case 1: {
                            c38 = '7';
                            break;
                        }
                        case 2: {
                            c38 = '\u0014';
                            break;
                        }
                        case 3: {
                            c38 = 'E';
                            break;
                        }
                        default: {
                            c38 = '\u007f';
                            break;
                        }
                    }
                    charArray19[length19] = (char)(c37 ^ c38);
                    ++n76;
                } while (n74 == 0);
            }
            if (n74 > n76) {
                continue;
            }
            break;
        }
        vb[n73] = new String(charArray19).intern();
        final int n77 = 19;
        final char[] charArray20 = "nGd)\u001a[\u0017g1\u001e]C4j_yz4 \r]Xf".toCharArray();
        int length20;
        int n79;
        final int n78 = n79 = (length20 = charArray20.length);
        int n80 = 0;
        while (true) {
            Label_2366: {
                if (n78 > 1) {
                    break Label_2366;
                }
                length20 = (n79 = n80);
                do {
                    final char c39 = charArray20[n79];
                    char c40 = '\0';
                    switch (n80 % 5) {
                        case 0: {
                            c40 = '/';
                            break;
                        }
                        case 1: {
                            c40 = '7';
                            break;
                        }
                        case 2: {
                            c40 = '\u0014';
                            break;
                        }
                        case 3: {
                            c40 = 'E';
                            break;
                        }
                        default: {
                            c40 = '\u007f';
                            break;
                        }
                    }
                    charArray20[length20] = (char)(c39 ^ c40);
                    ++n80;
                } while (n78 == 0);
            }
            if (n78 > n80) {
                continue;
            }
            break;
        }
        vb[n77] = new String(charArray20).intern();
        final int n81 = 20;
        final char[] charArray21 = "}Rp,\rJT`eP\u000faYe\u001a]E{7".toCharArray();
        int length21;
        int n83;
        final int n82 = n83 = (length21 = charArray21.length);
        int n84 = 0;
        while (true) {
            Label_2486: {
                if (n82 > 1) {
                    break Label_2486;
                }
                length21 = (n83 = n84);
                do {
                    final char c41 = charArray21[n83];
                    char c42 = '\0';
                    switch (n84 % 5) {
                        case 0: {
                            c42 = '/';
                            break;
                        }
                        case 1: {
                            c42 = '7';
                            break;
                        }
                        case 2: {
                            c42 = '\u0014';
                            break;
                        }
                        case 3: {
                            c42 = 'E';
                            break;
                        }
                        default: {
                            c42 = '\u007f';
                            break;
                        }
                    }
                    charArray21[length21] = (char)(c41 ^ c42);
                    ++n84;
                } while (n82 == 0);
            }
            if (n82 > n84) {
                continue;
            }
            break;
        }
        vb[n81] = new String(charArray21).intern();
        final int n85 = 21;
        final char[] charArray22 = "]Rp,\rJT`k\u000bNEs \u000b".toCharArray();
        int length22;
        int n87;
        final int n86 = n87 = (length22 = charArray22.length);
        int n88 = 0;
        while (true) {
            Label_2606: {
                if (n86 > 1) {
                    break Label_2606;
                }
                length22 = (n87 = n88);
                do {
                    final char c43 = charArray22[n87];
                    char c44 = '\0';
                    switch (n88 % 5) {
                        case 0: {
                            c44 = '/';
                            break;
                        }
                        case 1: {
                            c44 = '7';
                            break;
                        }
                        case 2: {
                            c44 = '\u0014';
                            break;
                        }
                        case 3: {
                            c44 = 'E';
                            break;
                        }
                        default: {
                            c44 = '\u007f';
                            break;
                        }
                    }
                    charArray22[length22] = (char)(c43 ^ c44);
                    ++n88;
                } while (n86 == 0);
            }
            if (n86 > n88) {
                continue;
            }
            break;
        }
        vb[n85] = new String(charArray22).intern();
        final int n89 = 22;
        final char[] charArray23 = "]Rp,\rJT`k\u000fNPq".toCharArray();
        int length23;
        int n91;
        final int n90 = n91 = (length23 = charArray23.length);
        int n92 = 0;
        while (true) {
            Label_2726: {
                if (n90 > 1) {
                    break Label_2726;
                }
                length23 = (n91 = n92);
                do {
                    final char c45 = charArray23[n91];
                    char c46 = '\0';
                    switch (n92 % 5) {
                        case 0: {
                            c46 = '/';
                            break;
                        }
                        case 1: {
                            c46 = '7';
                            break;
                        }
                        case 2: {
                            c46 = '\u0014';
                            break;
                        }
                        case 3: {
                            c46 = 'E';
                            break;
                        }
                        default: {
                            c46 = '\u007f';
                            break;
                        }
                    }
                    charArray23[length23] = (char)(c45 ^ c46);
                    ++n92;
                } while (n90 == 0);
            }
            if (n90 > n92) {
                continue;
            }
            break;
        }
        vb[n89] = new String(charArray23).intern();
        final int n93 = 23;
        final char[] charArray24 = "pUx$\u0011D".toCharArray();
        int length24;
        int n95;
        final int n94 = n95 = (length24 = charArray24.length);
        int n96 = 0;
        while (true) {
            Label_2846: {
                if (n94 > 1) {
                    break Label_2846;
                }
                length24 = (n95 = n96);
                do {
                    final char c47 = charArray24[n95];
                    char c48 = '\0';
                    switch (n96 % 5) {
                        case 0: {
                            c48 = '/';
                            break;
                        }
                        case 1: {
                            c48 = '7';
                            break;
                        }
                        case 2: {
                            c48 = '\u0014';
                            break;
                        }
                        case 3: {
                            c48 = 'E';
                            break;
                        }
                        default: {
                            c48 = '\u007f';
                            break;
                        }
                    }
                    charArray24[length24] = (char)(c47 ^ c48);
                    ++n96;
                } while (n94 == 0);
            }
            if (n94 > n96) {
                continue;
            }
            break;
        }
        vb[n93] = new String(charArray24).intern();
        final int n97 = 24;
        final char[] charArray25 = "}Rp,\rJT`e\u001a]E{7".toCharArray();
        int length25;
        int n99;
        final int n98 = n99 = (length25 = charArray25.length);
        int n100 = 0;
        while (true) {
            Label_2966: {
                if (n98 > 1) {
                    break Label_2966;
                }
                length25 = (n99 = n100);
                do {
                    final char c49 = charArray25[n99];
                    char c50 = '\0';
                    switch (n100 % 5) {
                        case 0: {
                            c50 = '/';
                            break;
                        }
                        case 1: {
                            c50 = '7';
                            break;
                        }
                        case 2: {
                            c50 = '\u0014';
                            break;
                        }
                        case 3: {
                            c50 = 'E';
                            break;
                        }
                        default: {
                            c50 = '\u007f';
                            break;
                        }
                    }
                    charArray25[length25] = (char)(c49 ^ c50);
                    ++n100;
                } while (n98 == 0);
            }
            if (n98 > n100) {
                continue;
            }
            break;
        }
        vb[n97] = new String(charArray25).intern();
        final int n101 = 25;
        final char[] charArray26 = "\\_{2Q_Va6\u001a".toCharArray();
        int length26;
        int n103;
        final int n102 = n103 = (length26 = charArray26.length);
        int n104 = 0;
        while (true) {
            Label_3086: {
                if (n102 > 1) {
                    break Label_3086;
                }
                length26 = (n103 = n104);
                do {
                    final char c51 = charArray26[n103];
                    char c52 = '\0';
                    switch (n104 % 5) {
                        case 0: {
                            c52 = '/';
                            break;
                        }
                        case 1: {
                            c52 = '7';
                            break;
                        }
                        case 2: {
                            c52 = '\u0014';
                            break;
                        }
                        case 3: {
                            c52 = 'E';
                            break;
                        }
                        default: {
                            c52 = '\u007f';
                            break;
                        }
                    }
                    charArray26[length26] = (char)(c51 ^ c52);
                    ++n104;
                } while (n102 == 0);
            }
            if (n102 > n104) {
                continue;
            }
            break;
        }
        vb[n101] = new String(charArray26).intern();
        final int n105 = 26;
        final char[] charArray27 = "\\_{2Q[Rl1".toCharArray();
        int length27;
        int n107;
        final int n106 = n107 = (length27 = charArray27.length);
        int n108 = 0;
        while (true) {
            Label_3206: {
                if (n106 > 1) {
                    break Label_3206;
                }
                length27 = (n107 = n108);
                do {
                    final char c53 = charArray27[n107];
                    char c54 = '\0';
                    switch (n108 % 5) {
                        case 0: {
                            c54 = '/';
                            break;
                        }
                        case 1: {
                            c54 = '7';
                            break;
                        }
                        case 2: {
                            c54 = '\u0014';
                            break;
                        }
                        case 3: {
                            c54 = 'E';
                            break;
                        }
                        default: {
                            c54 = '\u007f';
                            break;
                        }
                    }
                    charArray27[length27] = (char)(c53 ^ c54);
                    ++n108;
                } while (n106 == 0);
            }
            if (n106 > n108) {
                continue;
            }
            break;
        }
        vb[n105] = new String(charArray27).intern();
        final int n109 = 27;
        final char[] charArray28 = "\\_{2QMVf".toCharArray();
        int length28;
        int n111;
        final int n110 = n111 = (length28 = charArray28.length);
        int n112 = 0;
        while (true) {
            Label_3326: {
                if (n110 > 1) {
                    break Label_3326;
                }
                length28 = (n111 = n112);
                do {
                    final char c55 = charArray28[n111];
                    char c56 = '\0';
                    switch (n112 % 5) {
                        case 0: {
                            c56 = '/';
                            break;
                        }
                        case 1: {
                            c56 = '7';
                            break;
                        }
                        case 2: {
                            c56 = '\u0014';
                            break;
                        }
                        case 3: {
                            c56 = 'E';
                            break;
                        }
                        default: {
                            c56 = '\u007f';
                            break;
                        }
                    }
                    charArray28[length28] = (char)(c55 ^ c56);
                    ++n112;
                } while (n110 == 0);
            }
            if (n110 > n112) {
                continue;
            }
            break;
        }
        vb[n109] = new String(charArray28).intern();
        final int n113 = 28;
        final char[] charArray29 = "\\_{2QMP".toCharArray();
        int length29;
        int n115;
        final int n114 = n115 = (length29 = charArray29.length);
        int n116 = 0;
        while (true) {
            Label_3446: {
                if (n114 > 1) {
                    break Label_3446;
                }
                length29 = (n115 = n116);
                do {
                    final char c57 = charArray29[n115];
                    char c58 = '\0';
                    switch (n116 % 5) {
                        case 0: {
                            c58 = '/';
                            break;
                        }
                        case 1: {
                            c58 = '7';
                            break;
                        }
                        case 2: {
                            c58 = '\u0014';
                            break;
                        }
                        case 3: {
                            c58 = 'E';
                            break;
                        }
                        default: {
                            c58 = '\u007f';
                            break;
                        }
                    }
                    charArray29[length29] = (char)(c57 ^ c58);
                    ++n116;
                } while (n114 == 0);
            }
            if (n114 > n116) {
                continue;
            }
            break;
        }
        vb[n113] = new String(charArray29).intern();
        final int n117 = 29;
        final char[] charArray30 = "\\_{2Q[Rl1;J[u<".toCharArray();
        int length30;
        int n119;
        final int n118 = n119 = (length30 = charArray30.length);
        int n120 = 0;
        while (true) {
            Label_3566: {
                if (n118 > 1) {
                    break Label_3566;
                }
                length30 = (n119 = n120);
                do {
                    final char c59 = charArray30[n119];
                    char c60 = '\0';
                    switch (n120 % 5) {
                        case 0: {
                            c60 = '/';
                            break;
                        }
                        case 1: {
                            c60 = '7';
                            break;
                        }
                        case 2: {
                            c60 = '\u0014';
                            break;
                        }
                        case 3: {
                            c60 = 'E';
                            break;
                        }
                        default: {
                            c60 = '\u007f';
                            break;
                        }
                    }
                    charArray30[length30] = (char)(c59 ^ c60);
                    ++n120;
                } while (n118 == 0);
            }
            if (n118 <= n120) {
                vb[n117] = new String(charArray30).intern();
                Loader.vb = vb;
                return;
            }
            continue;
        }
    }
}
