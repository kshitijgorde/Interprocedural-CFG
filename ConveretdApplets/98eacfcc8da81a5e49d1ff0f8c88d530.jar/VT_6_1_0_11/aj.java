// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.io.DataOutputStream;
import java.net.SocketException;
import java.net.NoRouteToHostException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.io.InterruptedIOException;
import java.net.Socket;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Vector;
import java.net.InetAddress;

public final class aj
{
    private static final Object e;
    private Object f;
    private int g;
    int a;
    boolean b;
    private String h;
    private String i;
    private int j;
    private InetAddress k;
    private int l;
    private String m;
    private int n;
    private static String o;
    private static V p;
    private static Vector q;
    private static Vector r;
    private static Vector s;
    private cE t;
    private static cE u;
    private bC v;
    i c;
    private i w;
    private boolean x;
    private boolean y;
    private int z;
    private int A;
    private static boolean B;
    private static boolean C;
    private static boolean D;
    private static boolean E;
    private static boolean F;
    static boolean d;
    private static int G;
    private int H;
    private cU[] I;
    private static Vector J;
    private Vector K;
    private static boolean L;
    private boolean M;
    private volatile bd N;
    private volatile bd O;
    private volatile bd P;
    private boolean Q;
    
    public aj(final String s, final int n) {
        this.f = null;
        this.m = null;
        this.t = null;
        this.v = null;
        this.c = new i();
        this.w = new i();
        this.x = false;
        this.y = true;
        this.z = -1;
        this.I = new cU[0];
        this.N = null;
        this.O = null;
        this.P = null;
        this.Q = true;
        this.a(0, s, n, null, -1);
    }
    
    public aj(final String s, final String s2, final int n) {
        this(s, s2, n, null, -1);
    }
    
    private aj(String lowerCase, final String s, final int n, final InetAddress inetAddress, final int n2) {
        this.f = null;
        this.m = null;
        this.t = null;
        this.v = null;
        this.c = new i();
        this.w = new i();
        this.x = false;
        this.y = true;
        this.z = -1;
        this.I = new cU[0];
        this.N = null;
        this.O = null;
        this.P = null;
        this.Q = true;
        if (!(lowerCase = lowerCase.trim().toLowerCase()).equals("http")) {
            throw new a("Unsupported protocol '" + lowerCase + "'");
        }
        if (lowerCase.equals("http")) {
            this.a(0, s, n, null, -1);
            return;
        }
        if (lowerCase.equals("https")) {
            this.a(1, s, n, null, -1);
            return;
        }
        if (lowerCase.equals("shttp")) {
            this.a(2, s, n, null, -1);
            return;
        }
        if (lowerCase.equals("http-ng")) {
            this.a(3, s, n, null, -1);
        }
    }
    
    public aj(final bf bf) {
        this(bf.a(), bf.b(), bf.c());
    }
    
    private void a(final int g, final String s, int j, final InetAddress k, int i) {
        this.g = g;
        this.i = s.trim().toLowerCase();
        this.j = j;
        this.k = k;
        this.l = i;
        if (this.j == -1) {
            this.j = bf.a(this.b());
        }
        this.a((String)null, 0);
        this.t = null;
        this.H = 0;
        this.K = (Vector)aj.J.clone();
        this.M = aj.L;
        if (aj.E) {
            final cU[] array = { new cU("Connection", "close") };
            cU[] a = new cU[j = ((array == null) ? 0 : array.length)];
            i = 0;
            int n = 0;
            while (i < j) {
                if (array[i] != null && !array[i].a().trim().equalsIgnoreCase("Content-length")) {
                    a[n++] = array[i];
                }
                ++i;
            }
            if (n < j) {
                a = bz.a(a, n);
            }
            synchronized (this.I) {
                this.I = a;
            }
        }
    }
    
    public final cM a(final String s) {
        return this.a("GET", c(s), null, null, null);
    }
    
    public final cM b(final String s) {
        return this.a(s, null, null);
    }
    
    public final cM a(final String s, final byte[] array) {
        return this.a(s, array, null);
    }
    
    private cM a(final String s, byte[] array, final cU[] array2) {
        if (array == null) {
            array = new byte[0];
        }
        return this.a("POST", c(s), null, array, null);
    }
    
    public final void a() {
        for (q q = (q)this.w.b(); q != null; q = (q)this.w.c()) {
            q.d = true;
        }
        for (bC bc = (bC)this.c.b(); bc != null; bc = (bC)this.c.c()) {
            bc.d();
        }
    }
    
    public final String b() {
        switch (this.g) {
            case 0: {
                return "http";
            }
            case 1: {
                return "https";
            }
            case 2: {
                return "shttp";
            }
            case 3: {
                return "http-ng";
            }
            default: {
                throw new Error("HTTPClient Internal Error: invalid protocol " + this.g);
            }
        }
    }
    
    public final String c() {
        return this.i;
    }
    
    public final int d() {
        return this.j;
    }
    
    public final String e() {
        return this.m;
    }
    
    public final int f() {
        return this.n;
    }
    
    public final boolean a(final bf bf) {
        if (!bf.a().equals(this.b()) || !bf.b().equalsIgnoreCase(this.i)) {
            return false;
        }
        int n;
        if ((n = bf.c()) == -1) {
            n = bf.a(bf.a());
        }
        return n == this.j;
    }
    
    public final void a(final int h) {
        this.H = h;
    }
    
    public final void a(final Object f) {
        if (f == null) {
            throw new IllegalArgumentException("Context must be non-null");
        }
        if (this.f != null) {
            throw new IllegalStateException("Context already set");
        }
        this.f = f;
    }
    
    public final Object g() {
        if (this.f != null) {
            return this.f;
        }
        return aj.e;
    }
    
    public static Object h() {
        return aj.e;
    }
    
    public final synchronized void a(final String s, final int n) {
        if (s == null || s.trim().length() == 0) {
            this.m = null;
        }
        else {
            this.m = s.trim().toLowerCase();
            if (n <= 0) {
                this.n = 80;
            }
            else {
                this.n = n;
            }
        }
        switch (this.g) {
            case 0:
            case 1: {
                if (aj.C) {
                    this.a = 65536;
                    this.b = true;
                    this.h = "HTTP/1.0";
                    break;
                }
                this.a = 65537;
                this.b = false;
                this.h = "HTTP/1.1";
                break;
            }
            case 3: {
                this.a = -1;
                this.b = false;
                this.h = "";
                break;
            }
            case 2: {
                this.a = -1;
                this.b = false;
                this.h = "Secure-HTTP/1.3";
                break;
            }
            default: {
                throw new Error("HTTPClient Internal Error: invalid protocol " + this.g);
            }
        }
        this.y = true;
        this.x = false;
        this.v = null;
        this.N = null;
        this.O = null;
        this.P = null;
    }
    
    private static String c(String substring) {
        if (substring == null) {
            return "";
        }
        final int index;
        if ((index = substring.indexOf(35)) != -1) {
            substring = substring.substring(0, index);
        }
        return substring.trim();
    }
    
    private cM a(String s, final String s2, final cU[] array, final byte[] array2, final cK ck) {
        s = (String)new q(this, s, s2, this.a(array), array2, null, this.M);
        this.w.a(s);
        try {
            final cM cm = new cM(this.j(), this.H, (q)s);
            this.a((q)s, cm, null, true);
            return cm;
        }
        finally {
            this.w.b(s);
        }
    }
    
    private cU[] a(final cU[] p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: ifnull          9
        //     4: aload_1        
        //     5: arraylength    
        //     6: goto            10
        //     9: iconst_0       
        //    10: istore_2       
        //    11: aload_0        
        //    12: getfield        VT_6_1_0_11/aj.I:[LVT_6_1_0_11/cU;
        //    15: dup            
        //    16: astore          5
        //    18: monitorenter   
        //    19: aload_0        
        //    20: getfield        VT_6_1_0_11/aj.I:[LVT_6_1_0_11/cU;
        //    23: ifnull          34
        //    26: aload_0        
        //    27: getfield        VT_6_1_0_11/aj.I:[LVT_6_1_0_11/cU;
        //    30: arraylength    
        //    31: goto            35
        //    34: iconst_0       
        //    35: istore_3       
        //    36: iload_2        
        //    37: iload_3        
        //    38: iadd           
        //    39: anewarray       LVT_6_1_0_11/cU;
        //    42: astore          4
        //    44: aload_0        
        //    45: getfield        VT_6_1_0_11/aj.I:[LVT_6_1_0_11/cU;
        //    48: iconst_0       
        //    49: aload           4
        //    51: iconst_0       
        //    52: iload_3        
        //    53: invokestatic    java/lang/System.arraycopy:(Ljava/lang/Object;ILjava/lang/Object;II)V
        //    56: aload           5
        //    58: monitorexit    
        //    59: goto            68
        //    62: astore_3       
        //    63: aload           5
        //    65: monitorexit    
        //    66: aload_3        
        //    67: athrow         
        //    68: iload_3        
        //    69: istore_3       
        //    70: iconst_0       
        //    71: istore          5
        //    73: iload           5
        //    75: iload_2        
        //    76: if_icmpge       165
        //    79: aload_1        
        //    80: iload           5
        //    82: aaload         
        //    83: ifnull          159
        //    86: aload_1        
        //    87: iload           5
        //    89: aaload         
        //    90: invokevirtual   VT_6_1_0_11/cU.a:()Ljava/lang/String;
        //    93: invokevirtual   java/lang/String.trim:()Ljava/lang/String;
        //    96: dup            
        //    97: astore          6
        //    99: ldc             "Content-length"
        //   101: invokevirtual   java/lang/String.equalsIgnoreCase:(Ljava/lang/String;)Z
        //   104: ifne            159
        //   107: iconst_0       
        //   108: istore          7
        //   110: iload           7
        //   112: iload_3        
        //   113: if_icmpge       141
        //   116: aload           4
        //   118: iload           7
        //   120: aaload         
        //   121: invokevirtual   VT_6_1_0_11/cU.a:()Ljava/lang/String;
        //   124: invokevirtual   java/lang/String.trim:()Ljava/lang/String;
        //   127: aload           6
        //   129: invokevirtual   java/lang/String.equalsIgnoreCase:(Ljava/lang/String;)Z
        //   132: ifne            141
        //   135: iinc            7, 1
        //   138: goto            110
        //   141: aload           4
        //   143: iload           7
        //   145: aload_1        
        //   146: iload           5
        //   148: aaload         
        //   149: aastore        
        //   150: iload           7
        //   152: iload_3        
        //   153: if_icmpne       159
        //   156: iinc            3, 1
        //   159: iinc            5, 1
        //   162: goto            73
        //   165: iload_3        
        //   166: aload           4
        //   168: arraylength    
        //   169: if_icmpge       180
        //   172: aload           4
        //   174: iload_3        
        //   175: invokestatic    VT_6_1_0_11/bz.a:([LVT_6_1_0_11/cU;I)[LVT_6_1_0_11/cU;
        //   178: astore          4
        //   180: aload           4
        //   182: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  19     59     62     68     Any
        //  62     66     62     68     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:2985)
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
    
    private bk[] j() {
        synchronized (this.K) {
            final bk[] array = new bk[this.K.size()];
            for (int i = 0; i < this.K.size(); ++i) {
                final Class<bk> clazz = this.K.elementAt(i);
                try {
                    array[i] = clazz.newInstance();
                }
                catch (Exception ex) {
                    throw new Error("HTTPClient Internal Error: could not create instance of " + clazz.getName() + " -\n" + ex);
                }
            }
            return array;
        }
    }
    
    final void a(q q, cM cm, bd bd, boolean b) {
    Label_0301:
        while (true) {
            final bd[] array = { bd };
            final bk[] d = cm.d();
            if (b) {
                for (int i = 0; i < d.length; ++i) {
                    final int a;
                    switch (a = d[i].a(q)) {
                        case 0: {
                            break;
                        }
                        case 1: {
                            i = -1;
                            break;
                        }
                        case 2: {
                            break Label_0301;
                        }
                        case 3:
                        case 4: {
                            if (array[0] == null) {
                                throw new Error("HTTPClient Internal Error: no response returned by module " + d[i].getClass().getName());
                            }
                            cm.a(q, array[0]);
                            if (q.f() != null) {
                                q.f().a(q);
                            }
                            if (q.e) {
                                return;
                            }
                            if (a == 3) {
                                cm.e();
                                return;
                            }
                            cm.a(array[0]);
                            return;
                        }
                        case 5: {
                            if (q.e) {
                                return;
                            }
                            final aj a2 = q.a();
                            final q q2 = q;
                            final cM cm2 = cm;
                            final bd bd2 = array[0];
                            b = true;
                            bd = bd2;
                            cm = cm2;
                            q = q2;
                            this = a2;
                            continue Label_0301;
                        }
                        case 6: {
                            if (q.e) {
                                return;
                            }
                            final aj a3 = q.a();
                            final q q3 = q;
                            final cM cm3 = cm;
                            final bd bd3 = array[0];
                            b = false;
                            bd = bd3;
                            cm = cm3;
                            q = q3;
                            this = a3;
                            continue Label_0301;
                        }
                        default: {
                            throw new Error("HTTPClient Internal Error: invalid status " + a + " returned by module " + d[i].getClass().getName());
                        }
                    }
                }
                break;
            }
            break;
        }
        if (q.e) {
            return;
        }
        if (q.f() != null && q.f().b() == -1) {
            if (!this.b || this.a < 65537 || aj.B) {
                q.f().a(q, null, cm.g());
                cm.a(q, q.f());
            }
            else {
                cU[] d2;
                int n;
                for (d2 = q.d(), n = 0; n < d2.length && !d2[n].a().equalsIgnoreCase("Transfer-Encoding"); ++n) {}
                if (n == d2.length) {
                    final cU[] a4;
                    (a4 = bz.a(d2, n + 1))[n] = new cU("Transfer-Encoding", "chunked");
                    q.a(a4);
                }
                else {
                    final String b2;
                    if (!bz.b(b2 = d2[n].b(), "chunked")) {
                        d2[n] = new cU("Transfer-Encoding", b2 + ", chunked");
                    }
                }
                cm.a(q, this.a(q, cm.g()));
            }
        }
        else {
            cm.a(q, this.a(q, cm.g()));
        }
        if (q.d) {
            throw new IOException("Request aborted by user");
        }
    }
    
    final bd a(final q q, final int soTimeout) {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(600);
        bd a = null;
        if (this.N != null) {
            try {
                new StringBuffer().append("Conn:  Early-stalling Request: ").append(q.b()).append(" ").append(q.c()).toString();
                synchronized (this.N) {
                    try {
                        this.N.c();
                    }
                    catch (IOException ex3) {}
                    this.N = null;
                }
            }
            catch (NullPointerException ex4) {}
        }
        final String[] a2 = this.a(q, byteArrayOutputStream);
        boolean b;
        try {
            b = ((this.a >= 65537 && !bz.b(a2[0], "close")) || (this.a == 65536 && bz.b(a2[0], "keep-alive")));
        }
        catch (dh dh) {
            throw new IOException(dh.toString());
        }
        synchronized (this) {
            if (this.O != null) {
                if (this.v != null || this.y) {
                    new StringBuffer().append("Conn:  Stalling Request: ").append(q.b()).append(" ").append(q.c()).toString();
                    try {
                        this.O.c();
                        if (this.y) {
                            final bd o = this.O;
                            try {
                                String s;
                                if (this.a >= 65537 || ((((this.m == null || this.g == 1) && (s = o.a("Connection")) != null) || (this.m != null && this.g != 1 && (s = o.a("Proxy-Connection")) != null)) && bz.b(s, "keep-alive"))) {
                                    this.x = true;
                                    this.y = false;
                                }
                                else if (o.a() < 400) {
                                    this.y = false;
                                }
                                final String a3;
                                final Q a4;
                                if (this.x && this.a == 65536 && (a3 = o.a("Keep-Alive")) != null && (a4 = bz.a(bz.a(a3), "max")) != null && a4.b() != null) {
                                    this.z = Integer.parseInt(a4.b());
                                    this.A = this.z;
                                    new StringBuffer().append("Conn:  Max Keep-Alive requests: ").append(this.z).toString();
                                }
                            }
                            catch (dh dh3) {}
                            catch (NumberFormatException ex5) {}
                            catch (ClassCastException ex6) {}
                        }
                    }
                    catch (IOException ex7) {}
                }
                this.O = null;
            }
            if ((q.b().equals("POST") || q.c) && this.P != null && this.v != null) {
                new StringBuffer().append("Conn:  Stalling Request: ").append(q.b()).append(" ").append(q.c()).toString();
                try {
                    this.P.c();
                }
                catch (IOException ex8) {}
            }
            if (!this.Q) {
                try {
                    this.wait();
                }
                catch (InterruptedException ex) {
                    throw new IOException(ex.toString());
                }
            }
            if (q.d) {
                throw new IOException("Request aborted by user");
            }
            int n = 3;
            while (n-- > 0) {
                try {
                    Socket socket;
                    if (this.v == null || (socket = this.v.c()) == null) {
                        socket = this.b(soTimeout);
                        if (this.g == 1) {
                            if (this.m != null) {
                                final Socket[] array = { socket };
                                if ((a = this.a(array, q, soTimeout)) != null) {
                                    a.m = true;
                                    return a;
                                }
                                socket = array[0];
                            }
                            socket.setSoTimeout(soTimeout);
                        }
                        this.v = new bC(this.g, socket, this);
                        this.c.a(this.v);
                        this.A = this.z;
                    }
                    if (q.d) {
                        throw new IOException("Request aborted by user");
                    }
                    aF.a(1, "Conn:  Sending Request: ", byteArrayOutputStream);
                    OutputStream outputStream = socket.getOutputStream();
                    if (aj.F) {
                        outputStream = new cu(this, outputStream);
                    }
                    byteArrayOutputStream.writeTo(outputStream);
                    try {
                        if (this.b && this.a >= 65537 && bz.b(a2[1], "100-continue")) {
                            (a = new bd(q, this.m != null && this.g != 1, this.v)).b = 60;
                            if (a.d() != 100) {
                                a.b = 0;
                                break;
                            }
                        }
                    }
                    catch (dh dh2) {
                        throw new IOException(dh2.toString());
                    }
                    catch (InterruptedIOException ex9) {}
                    finally {
                        if (a != null) {
                            a.b = 0;
                        }
                    }
                    if (q.e() != null && q.e().length > 0) {
                        if (q.a > 0L) {
                            final long n2 = q.a / 100L;
                            final long n3 = q.a / n2;
                            for (int n4 = 0; n4 < n2 && this.v.a((cq)null) == 0; ++n4) {
                                try {
                                    Thread.sleep(n3);
                                }
                                catch (InterruptedException ex10) {}
                            }
                            if (this.v.a((cq)null) == 0) {
                                outputStream.write(q.e());
                            }
                            else {
                                b = false;
                            }
                        }
                        else {
                            outputStream.write(q.e());
                        }
                    }
                    if (q.f() != null) {
                        q.f().a(q, outputStream, 0);
                    }
                    else {
                        outputStream.flush();
                    }
                    if (a == null) {
                        a = new bd(q, this.m != null && this.g != 1, this.v);
                    }
                }
                catch (IOException ex2) {
                    aF.a(1, "Conn:  ", ex2);
                    this.a(ex2, true);
                    if (n == 0 || ex2 instanceof UnknownHostException || ex2 instanceof ConnectException || ex2 instanceof NoRouteToHostException || ex2 instanceof InterruptedIOException || q.d) {
                        throw ex2;
                    }
                    continue;
                }
                break;
            }
            this.P = a;
            if ((!this.y && !this.x) || !b || (this.z != -1 && this.A-- == 0)) {
                this.v.b(a);
                this.v = null;
            }
            else {
                this.v.a();
            }
            if (this.z != -1) {
                new StringBuffer().append("Conn:  Number of requests left: ").append(this.A).toString();
            }
            if (!this.b) {
                (this.N = a).a(q);
            }
            if (this.y || !cN.a(q.b()) || q.c || aj.D) {
                this.O = a;
            }
            if (q.f() != null) {
                this.Q = false;
            }
            else {
                this.Q = true;
                this.notify();
            }
        }
        return a;
    }
    
    private Socket b(int i) {
        Socket socket = null;
        String s;
        int n;
        if (this.m != null) {
            s = this.m;
            n = this.n;
        }
        else {
            s = this.i;
            n = this.j;
        }
        new StringBuffer().append("Conn:  Creating Socket: ").append(s).append(":").append(n).toString();
        if (i == 0) {
            if (this.t != null) {
                socket = this.t.a(s, n);
            }
            else {
                final InetAddress[] allByName = InetAddress.getAllByName(s);
                i = 0;
                while (i < allByName.length) {
                    try {
                        if (this.k == null) {
                            socket = new Socket(allByName[i], n);
                        }
                        else {
                            socket = new Socket(allByName[i], n, this.k, this.l);
                        }
                    }
                    catch (SocketException ex) {
                        if (i == allByName.length - 1) {
                            throw ex;
                        }
                        ++i;
                        continue;
                    }
                    break;
                }
            }
        }
        else {
            final bB bb;
            (bb = new bB(this, s, n, this.t)).start();
            try {
                bb.join(i);
            }
            catch (InterruptedException ex2) {}
            if (bb.a != null) {
                throw bb.a;
            }
            if ((socket = bb.b) == null) {
                bb.c = true;
                if ((socket = bb.b) == null) {
                    throw new InterruptedIOException("Connection establishment timed out");
                }
            }
        }
        return socket;
    }
    
    private bd a(final Socket[] array, q q, final int n) {
        final Vector vector = new Vector<cU>();
        for (int i = 0; i < q.d().length; ++i) {
            final String a;
            if ((a = q.d()[i].a()).equalsIgnoreCase("User-Agent") || a.equalsIgnoreCase("Proxy-Authorization")) {
                vector.addElement(q.d()[i]);
            }
        }
        final cU[] array2 = new cU[vector.size()];
        vector.copyInto(array2);
        final q q2;
        (q2 = new q(this, "CONNECT", this.i + ":" + this.j, array2, null, null, q.g())).e = true;
        q = (q)new ByteArrayOutputStream(600);
        final cM cm = new cM(this.j(), n, q2);
        bd bd = null;
        while (true) {
            this.a(q2, cm, bd, true);
            ((ByteArrayOutputStream)q).reset();
            this.a(q2, (ByteArrayOutputStream)q);
            aF.a(1, "Conn:  Sending SSL-Tunneling Subrequest: ", (ByteArrayOutputStream)q);
            ((ByteArrayOutputStream)q).writeTo(array[0].getOutputStream());
            if ((bd = new bd(q2, array[0].getInputStream())).a() == 200) {
                return null;
            }
            try {
                bd.e();
            }
            catch (IOException ex) {}
            try {
                array[0].close();
            }
            catch (IOException ex2) {}
            cm.a(q2, bd);
            if (!cm.e()) {
                return bd;
            }
            array[0] = this.b(n);
        }
    }
    
    private String[] a(final q q, final ByteArrayOutputStream byteArrayOutputStream) {
        final DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        final String[] array = { "", "" };
        final cU[] d = q.d();
        int n = -1;
        int n2 = -1;
        int n3 = -1;
        int n4 = -1;
        int n5 = -1;
        int n6 = -1;
        int n7 = -1;
        int n8 = -1;
        int n9 = -1;
        int n10 = -1;
        for (int i = 0; i < d.length; ++i) {
            final String lowerCase;
            if ((lowerCase = d[i].a().trim().toLowerCase()).equals("host")) {
                n = i;
            }
            else if (lowerCase.equals("content-type")) {
                n2 = i;
            }
            else if (lowerCase.equals("user-agent")) {
                n3 = i;
            }
            else if (lowerCase.equals("connection")) {
                n4 = i;
            }
            else if (lowerCase.equals("proxy-connection")) {
                n5 = i;
            }
            else if (lowerCase.equals("keep-alive")) {
                n6 = i;
            }
            else if (lowerCase.equals("expect")) {
                n7 = i;
            }
            else if (lowerCase.equals("te")) {
                n8 = i;
            }
            else if (lowerCase.equals("transfer-encoding")) {
                n9 = i;
            }
            else if (lowerCase.equals("upgrade")) {
                n10 = i;
            }
        }
        final String c = bz.c(q.c());
        if (this.m != null && this.g != 1 && !c.equals("*")) {
            dataOutputStream.writeBytes(q.b() + " http://" + this.i + ":" + this.j + c + " " + this.h + "\r\n");
        }
        else {
            dataOutputStream.writeBytes(q.b() + " " + c + " " + this.h + "\r\n");
        }
        final String s = (n >= 0) ? d[n].b().trim() : this.i;
        if (this.j != bf.a(this.b())) {
            dataOutputStream.writeBytes("Host: " + s + ":" + this.j + "\r\n");
        }
        else {
            dataOutputStream.writeBytes("Host: " + s + "\r\n");
        }
        String s2 = null;
        if (!this.b || this.a < 65537 || n4 != -1) {
            if (n4 == -1) {
                s2 = "Keep-Alive";
                array[0] = "Keep-Alive";
            }
            else {
                array[0] = d[n4].b().trim();
                s2 = array[0];
            }
            try {
                if (n6 != -1 && bz.b(array[0], "keep-alive")) {
                    dataOutputStream.writeBytes("Keep-Alive: " + d[n6].b().trim() + "\r\n");
                }
            }
            catch (dh dh) {
                throw new IOException(dh.toString());
            }
        }
        if (this.m != null && this.g != 1 && (!this.b || this.a < 65537) && s2 != null) {
            dataOutputStream.writeBytes("Proxy-Connection: ");
            dataOutputStream.writeBytes(s2);
            dataOutputStream.writeBytes("\r\n");
            s2 = null;
        }
        Label_0774: {
            if (s2 != null) {
                try {
                    if (!bz.b(s2, "TE")) {
                        s2 += ", TE";
                    }
                    break Label_0774;
                }
                catch (dh dh2) {
                    throw new IOException(dh2.toString());
                }
            }
            s2 = "TE";
        }
        if (n10 != -1) {
            s2 += ", Upgrade";
        }
        if (s2 != null) {
            dataOutputStream.writeBytes("Connection: ");
            dataOutputStream.writeBytes(s2);
            dataOutputStream.writeBytes("\r\n");
        }
        if (n8 != -1) {
            dataOutputStream.writeBytes("TE: ");
            Vector a;
            try {
                a = bz.a(d[n8].b());
            }
            catch (dh dh3) {
                throw new IOException(dh3.toString());
            }
            if (!a.contains(new Q("trailers"))) {
                dataOutputStream.writeBytes("trailers, ");
            }
            dataOutputStream.writeBytes(d[n8].b().trim() + "\r\n");
        }
        else {
            dataOutputStream.writeBytes("TE: trailers\r\n");
        }
        if (n3 != -1) {
            dataOutputStream.writeBytes("User-Agent: " + d[n3].b().trim() + " " + "RPT-HTTPClient/0.3-3" + "\r\n");
        }
        else {
            dataOutputStream.writeBytes("User-Agent: RPT-HTTPClient/0.3-3\r\n");
        }
        for (int j = 0; j < d.length; ++j) {
            if (j != n2 && j != n3 && j != n4 && j != n5 && j != n6 && j != n7 && j != n8 && j != n) {
                dataOutputStream.writeBytes(d[j].a().trim() + ": " + d[j].b().trim() + "\r\n");
            }
        }
        if (q.e() != null || q.f() != null) {
            dataOutputStream.writeBytes("Content-type: ");
            if (n2 != -1) {
                dataOutputStream.writeBytes(d[n2].b().trim());
            }
            else {
                dataOutputStream.writeBytes("application/octet-stream");
            }
            dataOutputStream.writeBytes("\r\n");
            if (q.e() != null) {
                dataOutputStream.writeBytes("Content-length: " + q.e().length + "\r\n");
            }
            else if (q.f().b() != -1 && n9 == -1) {
                dataOutputStream.writeBytes("Content-length: " + q.f().b() + "\r\n");
            }
            if (n7 != -1) {
                array[1] = d[n7].b().trim();
                dataOutputStream.writeBytes("Expect: " + array[1] + "\r\n");
            }
        }
        else if (n7 != -1) {
            Vector a2;
            try {
                a2 = bz.a(d[n7].b());
            }
            catch (dh dh4) {
                throw new IOException(dh4.toString());
            }
            while (a2.removeElement(new Q("100-continue"))) {}
            if (!a2.isEmpty()) {
                array[1] = bz.a(a2);
                dataOutputStream.writeBytes("Expect: " + array[1] + "\r\n");
            }
        }
        dataOutputStream.writeBytes("\r\n");
        return array;
    }
    
    final boolean a(final bd bd) {
        final String substring;
        final int index = (substring = bd.c().substring(5)).indexOf(46);
        this.a = (Integer.parseInt(substring.substring(0, index)) << 16 | Integer.parseInt(substring.substring(index + 1)));
        this.b = true;
        final int a = bd.a();
        if (this.m != null && this.g != 1 && bd.a("Via") == null && a != 407 && a != 502 && a != 504) {
            this.a = 65536;
        }
        final StringBuffer append = new StringBuffer().append("Conn:  Protocol Version established: ");
        final int a2 = this.a;
        append.append("HTTP/" + (a2 >>> 16) + "." + (a2 & 0xFFFF)).toString();
        if (this.a == 65536 && (bd.a() == 400 || bd.a() == 500)) {
            if (this.v != null) {
                this.v.b(bd);
            }
            this.v = null;
            this.h = "HTTP/1.0";
            return false;
        }
        return true;
    }
    
    final synchronized void i() {
        this.Q = true;
        this.notify();
    }
    
    final synchronized void a(final IOException ex, final boolean b) {
        if (this.v != null) {
            this.v.a(ex, b);
        }
        this.N = null;
        this.O = null;
        this.P = null;
    }
    
    public final String toString() {
        return this.b() + "://" + this.i + ((this.j != bf.a(this.b())) ? (":" + this.j) : "");
    }
    
    static InetAddress a(final aj aj) {
        return aj.k;
    }
    
    static int b(final aj aj) {
        return aj.l;
    }
    
    static {
        e = new Object();
        aj.o = null;
        aj.p = new V();
        aj.q = new Vector();
        aj.r = new Vector();
        aj.s = new Vector();
        aj.u = null;
        aj.B = false;
        aj.C = false;
        aj.D = false;
        aj.E = false;
        aj.F = false;
        aj.d = false;
        aj.G = 0;
        aj.L = true;
        (aj.J = new Vector()).addElement(new cc().getClass());
        aj.J.addElement(new bi().getClass());
        aj.J.addElement(new aI().getClass());
        try {
            aj.D = Boolean.getBoolean("HTTPClient.disable_pipelining");
        }
        catch (Exception ex) {}
        try {
            aj.E = Boolean.getBoolean("HTTPClient.disableKeepAlives");
        }
        catch (Exception ex2) {}
        try {
            aj.C = Boolean.getBoolean("HTTPClient.forceHTTP_1.0");
        }
        catch (Exception ex3) {}
        try {
            aj.B = Boolean.getBoolean("HTTPClient.dontChunkRequests");
        }
        catch (Exception ex4) {}
        try {
            if (System.getProperty("os.name").indexOf("Windows") >= 0 && System.getProperty("java.version").startsWith("1.1")) {
                aj.F = true;
            }
        }
        catch (Exception ex5) {}
        try {
            aj.d = Boolean.getBoolean("HTTPClient.deferStreamed");
        }
        catch (Exception ex6) {}
    }
}
