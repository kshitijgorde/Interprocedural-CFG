// 
// Decompiled by Procyon v0.5.30
// 

package ji.net;

import java.util.StringTokenizer;
import ji.zip.a4;
import ji.v1event.a9;
import java.net.URLConnection;
import java.io.FileNotFoundException;
import ji.util.bh;
import ji.util.e;
import java.util.zip.CRC32;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.File;
import java.net.MalformedURLException;
import java.io.IOException;
import ji.v1event.a3;
import ji.v1event.ao;
import ji.io.ac;
import ji.v1event.a6;
import java.net.URL;
import ji.util.d;
import ji.io.h;
import ji.util.i;
import ji.v1event.af;
import ji.awt.bb;
import java.util.Vector;
import java.util.Hashtable;
import ji.v1event.a2;
import java.io.InputStream;
import ji.awt.c;
import ji.io.q;
import ji.v1event.ak;

public class a0 implements ak
{
    private q a;
    private static a1 b;
    private static c c;
    private static c d;
    private c e;
    private a0 f;
    private boolean g;
    private static boolean h;
    private boolean i;
    private static boolean j;
    private String k;
    private long l;
    private boolean m;
    private boolean n;
    private a7 o;
    private String p;
    private String q;
    private String r;
    private boolean s;
    private boolean t;
    private boolean u;
    private boolean v;
    private static int w;
    private static boolean x;
    private static boolean y;
    private int z;
    private InputStream aa;
    private String ab;
    private a2 ac;
    private boolean ad;
    private boolean ae;
    private boolean af;
    private boolean ag;
    private long ah;
    private Object ai;
    private long aj;
    private long ak;
    private long al;
    private long am;
    private boolean an;
    private boolean ao;
    private String ap;
    private String aq;
    static Hashtable ar;
    static Hashtable as;
    static Object at;
    private long au;
    public Vector av;
    public bb aw;
    public boolean ax;
    private static boolean ay;
    private static String az;
    private static String a0;
    static af a1;
    int a2;
    static double a3;
    static double a4;
    
    public a0(final Object o, final String s) {
        this(o, s, false);
    }
    
    public a0(final Object o, final String ab, final boolean ae) {
        this.a = null;
        this.e = null;
        this.f = null;
        this.g = false;
        this.i = true;
        this.k = "";
        this.l = 0L;
        this.m = false;
        this.n = true;
        this.o = null;
        this.p = "N/A";
        this.q = "N/A";
        this.r = null;
        this.s = false;
        this.t = false;
        this.u = false;
        this.v = false;
        this.z = 0;
        this.aa = null;
        this.ab = null;
        this.ac = null;
        this.ad = false;
        this.ae = false;
        this.af = false;
        this.ag = false;
        this.ai = null;
        this.aj = 0L;
        this.ak = 0L;
        this.al = 0L;
        this.am = 0L;
        this.an = false;
        this.ao = false;
        this.ap = "";
        this.aq = "";
        this.au = 0L;
        this.av = new Vector();
        this.aw = null;
        this.ax = false;
        this.a2 = 15;
        this.ab = ab;
        this.ae = ae;
        if (this.a == null) {
            this.a = ji.io.q.a(o, ab);
        }
        if (!ji.util.i.c(272)) {
            if (ji.net.a0.c == null) {
                ji.net.a0.c = new c("jiNetCacheKeys");
            }
            if (ji.net.a0.d == null) {
                ji.net.a0.d = new c("jiNetCacheExtendedData");
            }
        }
        else if (ji.net.a0.b == null) {
            ji.net.a0.b = new a1();
        }
        if (this.ac == null) {
            (this.ac = new a2("jiNetCache", ab)).b(this);
        }
    }
    
    public final a0 a(final Object o, final String s, final String s2) {
        synchronized (this) {
            if (this.f == null) {
                this.f = new a0(o, s);
            }
        }
        return this.f;
    }
    
    public final void a(final boolean ao) {
        this.ao = ao;
    }
    
    public final void a() {
        try {
            if (this.ac != null) {
                this.ac.a(this);
                this.ac.g();
                this.ac = null;
            }
        }
        finally {
            if (this.f != null) {
                this.f.a();
                this.f = null;
            }
        }
    }
    
    public static final void a(final String s) {
        try {
            if (i.c(83)) {
                ji.io.h.d(s, "Releasing static resources net cache");
            }
            if (ji.net.a0.c != null) {
                ji.net.a0.c.c();
                ji.net.a0.c = null;
            }
            if (ji.net.a0.d != null) {
                b();
                ji.net.a0.c = null;
            }
            if (ji.net.a0.b != null) {
                ji.net.a0.b.a();
                ji.net.a0.b = null;
            }
        }
        catch (Exception ex) {}
    }
    
    public static final void b() {
        if (ji.net.a0.d != null) {
            ji.net.a0.d.c();
        }
    }
    
    public final void b(final boolean y) {
        ji.net.a0.y = y;
    }
    
    public final void c(final boolean v) {
        this.v = v;
    }
    
    public final String a(String a, final String s, final Object o, final af af) throws Exception {
        this.u = false;
        this.g = false;
        this.k = "";
        this.ak = 0L;
        this.al = 0L;
        this.am = 0L;
        this.ap = "";
        this.an = true;
        this.p = ji.util.d.bh(a);
        if (this.p != null && (this.p.toLowerCase().equals("gzip") || this.p.toLowerCase().equals("gz") || this.p.toLowerCase().equals("zip") || this.p.toLowerCase().equals("zipp") || this.p.toLowerCase().equals("zippped"))) {
            a = this.a(a, false, o, af, null, a, false, false);
        }
        if (this.af) {
            this.d(a);
        }
        else if (ji.util.d.dr()) {
            ji.io.h.d(this.ab, "not calculating checksum");
        }
        this.o = null;
        this.q = this.p;
        return a;
    }
    
    private static final long b(String cp, final String s) {
        cp = ji.util.d.cp(cp);
        if (!i.c(272)) {
            return ji.util.d.cq(cp);
        }
        if (ji.net.a0.b == null) {
            ji.net.a0.b = new a1();
        }
        return ji.net.a0.b.a(cp, s);
    }
    
    public final boolean c() {
        return ji.net.a0.ay;
    }
    
    private static final String u() {
        String s = v();
        if (i.c(272)) {
            if (s == null) {
                s = "n";
            }
            else {
                s = "n".concat(String.valueOf(String.valueOf(s)));
            }
        }
        return s;
    }
    
    private static final String v() {
        if (ji.net.a0.ay) {
            return q.b();
        }
        return null;
    }
    
    public final void a(final URL url, final Object o, final String s) {
        this.g = false;
        try {
            if (ji.net.a0.c != null) {
                final long b = b(url.toString(), s);
                this.a.d(this.a.a(ji.io.q.a(), false, b, u()));
                if (!ji.util.i.c(272)) {
                    ji.net.a0.c.a("".concat(String.valueOf(String.valueOf(b))));
                    ji.net.a0.d.a("".concat(String.valueOf(String.valueOf(b))));
                }
                else if (ji.net.a0.b != null) {
                    ji.net.a0.b.b(url.toString(), s);
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public static final void a(final String s, final String s2) {
        final q a = q.a((Object)null, s2);
        if (a != null) {
            if (i.c(195)) {
                ji.io.h.d(s2, "EXCACHE: Purged :".concat(String.valueOf(String.valueOf(s))));
            }
            a.d(s);
            if (!i.c(272)) {
                final String f = a.f(s);
                if (ji.net.a0.c.d(f) != null) {
                    ji.net.a0.c.a(f);
                    ji.net.a0.d.a(f);
                }
            }
            else if (ji.net.a0.b != null) {
                ji.net.a0.b.b(s, s2);
            }
        }
    }
    
    public final void a(final boolean b, final af af, final String s) {
        if (!ji.util.i.c(272)) {
            this.b(b, af, s);
        }
        else {
            this.c(b, af, s);
        }
    }
    
    private void b(final boolean b, final af af, final String s) {
        this.g = false;
        try {
            if (ji.util.i.c(83)) {
                ji.io.h.d(s, "net remove files");
            }
            if (ji.net.a0.c != null && !b) {
                final int b2 = ji.net.a0.c.b();
                final int n = b2 / 20;
                int n2;
                if ((n2 = n) > 0) {
                    this.a(new a6(this, 9, String.valueOf(String.valueOf(this.a(1213))).concat("..."), false));
                }
                int i = ji.net.a0.c.b();
                int n3 = 0;
                final Vector vector = new Vector<Long>(i);
                while (i > 0) {
                    final long a = ji.util.d.a("".concat(String.valueOf(String.valueOf(ji.net.a0.c.c(n3)))), -1L);
                    if (((String)ji.net.a0.c.b(n3)).equals(s)) {
                        vector.addElement(new Long(a));
                        this.a(s, a);
                        if (n > 0 && --n2 == 0) {
                            n2 = n;
                            this.a(new a6(this, 4, "".concat(String.valueOf(String.valueOf(100 * i / b2)))));
                        }
                    }
                    ++n3;
                    --i;
                }
                while (vector.size() > 0) {
                    final Long n4 = vector.elementAt(0);
                    vector.removeElementAt(0);
                    ji.net.a0.c.a("".concat(String.valueOf(String.valueOf((long)n4))));
                    ji.net.a0.d.a("".concat(String.valueOf(String.valueOf((long)n4))));
                }
                if (n > 0) {
                    this.a(new a6(this, 4, "0"));
                    this.a(new a6(this, 13, this.a(1213), true));
                }
            }
        }
        catch (Exception ex) {}
    }
    
    private void c(final boolean b, final af af, final String s) {
        this.g = false;
        try {
            if (ji.util.i.c(83)) {
                ji.io.h.d(s, "net remove files");
            }
            if (ji.net.a0.b != null && !b) {
                final long[] b2 = ji.net.a0.b.b(s);
                if (b2 != null) {
                    final int length = b2.length;
                    final int n = length / 20;
                    int n2;
                    if ((n2 = n) > 0) {
                        this.a(new a6(this, 9, String.valueOf(String.valueOf(this.a(1213))).concat("..."), false));
                    }
                    for (int i = 0; i < b2.length; ++i) {
                        if (ji.net.a0.b.a(b2[i], s)) {
                            this.a(s, b2[i]);
                        }
                        if (n > 0 && --n2 == 0) {
                            n2 = n;
                            this.a(new a6(this, 4, "".concat(String.valueOf(String.valueOf(100 * (b2.length - i) / length)))));
                        }
                    }
                    if (n > 0) {
                        this.a(new a6(this, 4, "0"));
                        this.a(new a6(this, 13, this.a(1213), true));
                    }
                }
            }
            if (ji.util.i.c(83)) {
                ji.io.h.d(s, "net finish remove files");
            }
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
        }
    }
    
    private void a(final String s, final long n) {
        final String a = this.a.a(ji.io.q.a(), false, n, u());
        if (ji.io.ac.d(a, s)) {
            if (ji.util.i.c(83)) {
                ji.io.h.d(s, "attempt remove ".concat(String.valueOf(String.valueOf(a))));
            }
            this.a.d(a);
            if (ji.util.d.c2()) {
                final String a2 = this.a.a(n);
                if (ji.util.i.c(83)) {
                    ji.io.h.d(s, "attempt remove ".concat(String.valueOf(String.valueOf(a2))));
                }
                this.a.d(a2);
            }
            if (ji.util.i.c(293)) {
                final String a3 = this.a.a("ps", n);
                if (ji.util.i.c(83)) {
                    ji.io.h.d(s, "attempt remove ".concat(String.valueOf(String.valueOf(a3))));
                }
                this.a.d(a3);
            }
        }
        else {
            final String a4 = this.a.a(ji.io.q.a(), true, n, u());
            if (ji.io.ac.d(a4, s)) {
                if (ji.util.i.c(83)) {
                    ji.io.h.d(s, "attempt remove ".concat(String.valueOf(String.valueOf(a4))));
                }
                this.a.d(a4);
            }
        }
    }
    
    public final void b(final String s) {
        this.g = false;
    }
    
    public final void d() {
        try {
            this.t = true;
            if ((ji.util.d.dr() || ji.util.d.ak) && ji.net.a0.h) {
                ji.io.h.d(this.ab, "> Net: Abort retrieval1...");
            }
            this.g = true;
        }
        finally {
            if (this.f != null) {
                this.f.d();
            }
        }
    }
    
    public final void e() {
        try {
            this.t = false;
        }
        finally {
            if (this.f != null) {
                this.f.e();
            }
        }
    }
    
    public final void f() {
    }
    
    public final String g() {
        return this.k;
    }
    
    public final void d(final boolean b) {
    }
    
    public final void e(final boolean u) {
        this.u = u;
    }
    
    public final boolean h() {
        return this.u;
    }
    
    public final String i() {
        if (this.o != null) {
            return this.o.a();
        }
        return "";
    }
    
    public final String a(final String s, final Object o, final String s2) throws Exception {
        if (ji.util.d.bj(s)) {
            final a7 b = this.b(s, o, s2);
            if (b != null) {
                return b.a();
            }
        }
        return "";
    }
    
    public final a7 b(final String s, final Object o, final String s2) throws Exception {
        final long b = b(s.toString(), s2);
        final String a = this.a.a(ji.io.q.a(), false, b, u());
        if (!ji.util.i.c(272)) {
            if (ji.net.a0.d != null) {
                this.o = (a7)ji.net.a0.d.d("".concat(String.valueOf(String.valueOf(b))));
                if (ji.io.ac.d(a, s2)) {
                    if (this.o != null) {
                        return this.o;
                    }
                }
                else {
                    this.o = null;
                    ji.net.a0.d.a("".concat(String.valueOf(String.valueOf(b))));
                }
            }
        }
        else if (ji.net.a0.b != null) {
            this.o = ji.net.a0.b.a(s.toString());
            if (ji.io.ac.d(a, s2)) {
                if (this.o != null) {
                    return this.o;
                }
            }
            else {
                this.o = null;
                ji.net.a0.b.a(s.toString(), (a7)null);
            }
        }
        if (ji.net.a0.ay) {
            ac ac = null;
            try {
                ac = new ac(a, false, false, 0, o, s2);
                if (ac.s()) {
                    (this.o = new a7(s2)).b(ac);
                    if (!ji.util.i.c(272)) {
                        if (ji.net.a0.d != null) {
                            ji.net.a0.d.a("".concat(String.valueOf(String.valueOf(b))), this.o);
                        }
                    }
                    else if (ji.net.a0.b != null) {
                        ji.net.a0.b.a(s.toString(), this.o);
                    }
                    this.p = this.o.i;
                    this.q = this.o.j;
                    this.ap = this.o.k;
                    this.ah = this.o.h;
                }
            }
            catch (Exception ex) {}
            finally {
                if (ac != null) {
                    ac.a(o);
                }
            }
        }
        return this.o;
    }
    
    public final String j() {
        return this.p;
    }
    
    public final String k() {
        return this.q;
    }
    
    public final String l() {
        return this.ap;
    }
    
    public final String m() {
        return this.aq;
    }
    
    public final String a(final URL url, final boolean b, final boolean b2, final String s, final Object o, final af af, final ao ao) throws Exception {
        return this.a(url, b, b2, s, null, null, false, o, false, af, false, true, ao, null, false, false);
    }
    
    public final String a(final URL url, final Object o, final ao ao) throws Exception {
        return this.a(url, true, true, null, null, null, false, o, false, null, false, false, ao, null, true, false);
    }
    
    public final String b(final URL url, final Object o, final ao ao) throws Exception {
        return this.a(url, true, true, null, null, null, false, o, false, null, true, false, ao, null, true, false);
    }
    
    public final String b(final URL url, final boolean b, final boolean b2, final String s, final Object o, final af af, final ao ao) throws Exception {
        return this.a(url, b, b2, s, null, null, false, o, false, af, false, false, ao, null, false, false);
    }
    
    public final String a(final URL url, final boolean b, final boolean b2, final String s, final String s2, final String s3, final boolean b3, final Object o, final af af, final ao ao) throws Exception {
        return this.a(url, b, b2, s, s2, s3, b3, o, af, false, ao, null, false);
    }
    
    public final String a(final URL url, final boolean b, final boolean b2, final String s, final String s2, final String s3, final boolean b3, final Object o, final af af, final boolean b4, final ao ao, final String s4, final boolean b5) throws Exception {
        return this.a(url, b, b2, s, s2, s3, b3, o, false, af, b4, false, ao, s4, false, b5);
    }
    
    public final String a(final URL url, final boolean b, final boolean b2, final String s, final Object o, final boolean b3, final af af, final ao ao) throws Exception {
        return this.a(url, b, b2, s, null, null, false, o, b3, af, false, false, ao, null, false, false);
    }
    
    private URL c(URL url, final Object o, final ao ao) {
        if (ao != null) {
            final a3 a3 = new a3(o, 44, -1, "".concat(String.valueOf(String.valueOf(url))), -1);
            a3.a(true);
            final String b = ao.b(a3);
            if (!ji.util.d.by(b)) {
                if (b.toLowerCase().startsWith("url:")) {
                    final String bc = ji.util.d.bc(b.substring("url:".length()));
                    if (ji.util.d.dr() || ji.util.i.c(6)) {
                        ji.io.h.d(this.ab, String.valueOf(String.valueOf(new StringBuffer("> Net(").append(this.a((Object)url)).append("): Javascript EventHandler has supplied an alternative URL..."))));
                        ji.io.h.d(this.ab, String.valueOf(String.valueOf(new StringBuffer("> Net(").append(this.a((Object)url)).append("): Old = ").append(url))));
                        ji.io.h.d(this.ab, String.valueOf(String.valueOf(new StringBuffer("> Net(").append(this.a((Object)url)).append("): New = ").append(bc))));
                    }
                    try {
                        url = new URL(bc);
                        if (ji.util.d.dr() || ji.util.i.c(6)) {
                            ji.io.h.d(this.ab, String.valueOf(String.valueOf(new StringBuffer("> Net(").append(this.a((Object)url)).append("):  Url now = ").append(url))));
                        }
                    }
                    catch (Exception ex) {
                        this.a(ex);
                    }
                }
                else {
                    ji.io.h.d(this.ab, String.valueOf(String.valueOf(new StringBuffer("> Net(").append(this.a((Object)url)).append("): Javascript EventHandler unexpectantly returned: ").append(b).append(" (in response to JavaScript event ").append(44).append(", ").append(a3.l()).append(")"))));
                }
            }
            return url;
        }
        return url;
    }
    
    public final a7 a(final URL url, final URL[] array, final boolean b, final boolean b2, final String s, final String az, final String a0, final boolean b3, final Object o, final af af, final boolean b4, final ao ao, final boolean b5) throws Exception {
        a7 b6 = null;
        final Vector vector = new Vector<URL>();
        try {
            final StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                final URL url2 = array[i];
                synchronized (a0.at) {
                    if (!a0.as.containsKey("".concat(String.valueOf(String.valueOf(url2))))) {
                        final URL c = this.c(url2, o, ao);
                        if (this.a(c) == null) {
                            vector.addElement(c);
                            sb.append(String.valueOf(String.valueOf(c.toString())).concat("\n"));
                            a0.as.put("".concat(String.valueOf(String.valueOf(c))), Thread.currentThread());
                        }
                    }
                }
                // monitorexit(a0.at)
            }
            if (vector.size() > 0) {
                this.ap = "";
                this.an = false;
                if (az != null) {
                    a0.az = az;
                }
                if (a0 != null) {
                    a0.a0 = a0;
                }
                final String a2 = this.a(url, sb.toString(), b, b2, s, az, a0, b3, o, false, b4, null, false, af, true, b5);
                b6 = this.b(url.toString(), o, this.ab);
                try {
                    Label_1596: {
                        if (a2 != null) {
                            final String r = this.r;
                            if (r != null) {
                                if (r.toLowerCase().indexOf("multipart/related") > -1 && r.toLowerCase().indexOf("boundary=") > -1) {
                                    final int n = r.indexOf("boundary=") + "boundary=".length();
                                    int n2 = r.indexOf(";", n);
                                    if (n2 == -1) {
                                        n2 = r.length();
                                    }
                                    final String concat = "--".concat(String.valueOf(String.valueOf(r.substring(n, n2))));
                                    final ac ac = new ac(a2, false, false, 0, o, this.ab);
                                    try {
                                        String s2 = null;
                                        int n3 = 0;
                                        boolean b7 = false;
                                        boolean b8 = ji.util.d.dl;
                                        String s3 = null;
                                        String s4 = null;
                                        String s5 = null;
                                        long n4 = 0L;
                                        long r2 = 0L;
                                        long r3 = 0L;
                                        long long1 = -1L;
                                        long a3 = 0L;
                                        URL url3 = null;
                                        ac.a(0L);
                                        ac.r();
                                        String m;
                                        while ((m = ac.m()) != null) {
                                            if (m.startsWith(concat)) {
                                                if (s2 != null) {
                                                    final long n5 = r3 - 2;
                                                    if (n5 > r2) {
                                                        final ac ac2 = new ac(s2, true, false, 0, o, this.ab, b8);
                                                        try {
                                                            final long n6 = n5;
                                                            final long n7 = r2;
                                                            long n8 = n6 - n7;
                                                            if (long1 != -1 && long1 != n8) {
                                                                if (Math.abs(long1 - n8) != 1) {
                                                                    if (ji.util.d.dr()) {
                                                                        ji.io.h.d(this.ab, String.valueOf(String.valueOf(new StringBuffer("> Net(").append(a3).append("): There was a problem retreiving multipart data, length not correct, server specified ").append(long1).append(", we retreived ").append(n8))));
                                                                    }
                                                                    throw new IOException("Invalid length for multipart part");
                                                                }
                                                                n8 = long1;
                                                            }
                                                            int j = (int)n8;
                                                            ac.a(n7);
                                                            final int min = Math.min(51200, j);
                                                            final byte[] array2 = new byte[min];
                                                            int n9 = ac.a(array2);
                                                            if (n9 > 0) {
                                                                while (j > 0) {
                                                                    j -= n9;
                                                                    ac2.b(array2, 0, n9);
                                                                    if (j <= 0) {
                                                                        break;
                                                                    }
                                                                    n9 = ac.a(array2, 0, Math.min(min, j));
                                                                    if (n9 < 0) {
                                                                        break;
                                                                    }
                                                                }
                                                            }
                                                            ac.a(n6);
                                                        }
                                                        finally {
                                                            if (ac2 != null) {
                                                                ac2.a(o);
                                                            }
                                                        }
                                                    }
                                                    boolean b9 = false;
                                                    if (n3 != 0) {
                                                        b9 = true;
                                                    }
                                                    else if (this.p != null && (this.p.toLowerCase().equals("gzip") || this.p.toLowerCase().equals("gz") || this.p.toLowerCase().equals("zip") || this.p.toLowerCase().equals("zipp") || this.p.toLowerCase().equals("zippped") || this.p.toLowerCase().indexOf("zip") >= 0 || this.p.toLowerCase().indexOf("gzip") >= 0)) {
                                                        b9 = true;
                                                    }
                                                    if (b9) {
                                                        s2 = this.a(s2, b8, o, null, this.e, url3.toString(), this.g(b4), b7, null);
                                                    }
                                                    if (s2 != null) {
                                                        boolean b10 = b8;
                                                        if (b7) {
                                                            b10 = true;
                                                        }
                                                        if (!this.g(b4)) {
                                                            this.a(url3, s2, s3, s4, s5, null, n4, b10, o);
                                                        }
                                                    }
                                                    else if (ji.util.d.dr()) {
                                                        ji.io.h.d(this.ab, String.valueOf(String.valueOf(new StringBuffer("> Net(").append(a3).append("): There was a problem unzipping data"))));
                                                    }
                                                    n3 = 0;
                                                    b7 = false;
                                                    b8 = ji.util.d.dl;
                                                    s3 = null;
                                                    s4 = null;
                                                    s5 = null;
                                                    n4 = 0L;
                                                    long1 = -1L;
                                                    a3 = 0L;
                                                    ac.r();
                                                    s2 = null;
                                                }
                                                final Hashtable<Object, String> hashtable = new Hashtable<Object, String>();
                                                String k;
                                                while ((k = ac.m()) != null && k.length() > 0) {
                                                    a(k, hashtable);
                                                }
                                                if (k != null) {
                                                    final String s6 = hashtable.get("content-location");
                                                    if (s6 != null) {
                                                        try {
                                                            url3 = new URL(s6);
                                                        }
                                                        catch (MalformedURLException ex2) {
                                                            continue;
                                                        }
                                                        a3 = this.a((Object)url3);
                                                        final String s7 = hashtable.get("content-length");
                                                        if (!ji.util.d.by(s7)) {
                                                            try {
                                                                long1 = Long.parseLong(s7);
                                                            }
                                                            catch (NumberFormatException ex3) {
                                                                long1 = -1L;
                                                                if (ji.util.d.dr()) {
                                                                    ji.io.h.d(this.ab, String.valueOf(String.valueOf(new StringBuffer("> Net(").append(a3).append("): Multipart part content length invalid: ").append(s7))));
                                                                }
                                                            }
                                                        }
                                                        final String s8 = hashtable.get("content-encoding");
                                                        boolean b11 = false;
                                                        if (b8) {
                                                            b11 = true;
                                                        }
                                                        final long b12 = b(url3.toString(), this.ab);
                                                        if (!ji.util.d.by(s8)) {
                                                            if (s8.toLowerCase().indexOf("gzip") > -1) {
                                                                if (ji.util.d.dr()) {
                                                                    ji.io.h.d(this.ab, String.valueOf(String.valueOf(new StringBuffer("> Net(").append(a3).append("): Data is gzipped"))));
                                                                }
                                                                n3 = 1;
                                                            }
                                                            if (s8.toLowerCase().indexOf("v1enc") > -1) {
                                                                if (ji.util.d.dr()) {
                                                                    ji.io.h.d(this.ab, String.valueOf(String.valueOf(new StringBuffer("> Net(").append(a3).append("): Data is encrypted"))));
                                                                }
                                                                if (n3 != 0) {
                                                                    if (s8.toLowerCase().indexOf("v1enc") < s8.toLowerCase().indexOf("gzip")) {
                                                                        b7 = true;
                                                                    }
                                                                }
                                                                else {
                                                                    b8 = true;
                                                                }
                                                            }
                                                        }
                                                        if (b8) {
                                                            b11 = true;
                                                        }
                                                        s2 = this.a.a(ji.io.q.a(), b11, b12, u());
                                                        r2 = ac.r();
                                                    }
                                                }
                                            }
                                            r3 = ac.r();
                                        }
                                        break Label_1596;
                                    }
                                    catch (Exception ex) {
                                        if (ji.util.i.c(137)) {
                                            ex.printStackTrace();
                                        }
                                        break Label_1596;
                                    }
                                    finally {
                                        if (ac != null) {
                                            ac.a(o);
                                        }
                                    }
                                }
                                b6 = null;
                            }
                            else {
                                b6 = null;
                            }
                        }
                        else {
                            b6 = null;
                        }
                    }
                }
                finally {
                    if (a2 != null) {
                        this.a(url, o, this.ab);
                    }
                }
            }
        }
        finally {
            if (vector != null) {
                for (int l = 0; l < vector.size(); ++l) {
                    final URL url4 = vector.elementAt(l);
                    synchronized (a0.at) {
                        a0.as.remove("".concat(String.valueOf(String.valueOf(url4))));
                    }
                    // monitorexit(a0.at)
                }
                vector.removeAllElements();
            }
            if (!a0.x && this.f(az)) {
                if ((!ji.util.d.by(az) || !ji.util.d.by(a0)) && ji.util.i.c(94)) {
                    if (b3 && !this.ao) {
                        this.y();
                    }
                }
                else {
                    this.z();
                }
            }
        }
        return b6;
    }
    
    private static final void a(final String s, final Hashtable hashtable) {
        try {
            final String s2 = ":";
            final int index = s.indexOf(s2);
            if (index > -1) {
                final String substring = s.substring(0, index);
                int n;
                for (n = index + s2.length(); n < s.length() && Character.isWhitespace(s.charAt(n)); ++n) {}
                hashtable.put(substring.toLowerCase(), s.substring(n, s.length()));
            }
        }
        catch (Exception ex) {}
    }
    
    public final boolean n() {
        return this.an;
    }
    
    public final String o() {
        return ji.net.a0.a0;
    }
    
    private final String a(URL c, final boolean b, final boolean b2, final String s, final String az, final String a0, final boolean b3, final Object o, final boolean b4, final af af, final boolean b5, final boolean b6, final ao ao, final String s2, final boolean b7, final boolean b8) throws Exception {
        String s3 = null;
        try {
            synchronized (a0.at) {
                a0.as.put("".concat(String.valueOf(String.valueOf(c))), Thread.currentThread());
            }
            // monitorexit(a0.at)
            this.ap = "";
            this.an = false;
            if (az != null) {
                a0.az = az;
            }
            if (a0 != null) {
                a0.a0 = a0;
            }
            c = this.c((URL)c, o, ao);
            int eo = ji.util.d.eo();
            if (ji.util.d.dr()) {
                ji.io.h.d(this.ab, String.valueOf(String.valueOf(new StringBuffer("> Net(").append(this.a(c)).append("): ram: ").append(this.g(b5)))));
            }
            if (!this.g(b5)) {
                if (c != null && ji.util.d.d1()) {
                    final String n = ji.util.d.n(((URL)c).toString().toLowerCase(), this.ab);
                    if (n != null && ji.io.ac.d(n, this.ab)) {
                        if (ji.util.d.dr()) {
                            ji.io.h.d(this.ab, String.valueOf(String.valueOf(new StringBuffer("> Net(").append(this.a(c)).append("): Retrieved from local file: ").append(c))));
                        }
                        this.u = false;
                        return this.a(n, s, o, af);
                    }
                }
                s3 = this.a((URL)c, b, b2, s, o, b4, false, af);
                this.an = (s3 != null);
            }
            Label_1421: {
                Label_1315: {
                    if (s3 == null && !b6) {
                        if (ji.util.d.dr()) {
                            ji.io.h.d(this.ab, String.valueOf(String.valueOf(new StringBuffer("> Net(").append(this.a(c)).append("): File not in cache: ").append(c))));
                        }
                        int d = ji.util.i.d(3);
                        if (d <= 0) {
                            d = 1;
                        }
                        int n2 = 0;
                        while (eo > 0 && !this.t) {
                            this.u = false;
                            try {
                                s3 = this.a((URL)c, null, b, b2, s, az, a0, b3, o, b4, this.g(b5), s2, b7, af, false, b8);
                                if (b7 && a0.x) {
                                    break;
                                }
                                if (n2 != 0 && (ji.util.d.dr() || ji.util.i.c(6))) {
                                    ji.io.h.d(this.ab, String.valueOf(String.valueOf(new StringBuffer("> Net(").append(this.a(c)).append("): New URL OK, continuing..."))));
                                }
                                eo = 0;
                                break;
                            }
                            catch (Exception ex) {
                                ji.util.d.a(ex);
                                if (ex.toString().toLowerCase().indexOf(this.a(290).toLowerCase()) >= 0 && eo > 1) {
                                    --eo;
                                }
                                else {
                                    eo = 0;
                                    if (ao == null) {
                                        throw ex;
                                    }
                                }
                                if (eo != 0 || ao == null) {
                                    continue;
                                }
                                if (d == 0) {
                                    if (ji.util.d.dr() || ji.util.i.c(6)) {
                                        ji.io.h.d(this.ab, String.valueOf(String.valueOf(new StringBuffer("> Net(").append(this.a(c)).append("): Tried all JavaScript alternatives, giving up..."))));
                                    }
                                    throw ex;
                                }
                                if (ji.util.d.dr() || ji.util.i.c(6)) {
                                    ji.io.h.d(this.ab, String.valueOf(String.valueOf(new StringBuffer("> Net(").append(this.a(c)).append("): Error#1 ").append(ex.toString()).append(" when using ").append(c).append("..."))));
                                    ji.io.h.d(this.ab, String.valueOf(String.valueOf(new StringBuffer("> Net(").append(this.a(c)).append("): Checking if there are JavaScript alternatives..."))));
                                }
                                String b9 = null;
                                a3 a2 = null;
                                if (!this.u && !this.g && !this.t) {
                                    a2 = new a3(o, 45, -1, "".concat(String.valueOf(String.valueOf(c))), -1);
                                    a2.a(true);
                                    b9 = ao.b(a2);
                                }
                                if (ji.util.d.by(b9)) {
                                    if (ji.util.d.dr() || ji.util.i.c(6)) {
                                        ji.io.h.d(this.ab, String.valueOf(String.valueOf(new StringBuffer("> Net(").append(this.a(c)).append("): No alternative supplied, so throwing error: ").append(ex))));
                                    }
                                    throw ex;
                                }
                                if (b9.toLowerCase().startsWith("url:")) {
                                    final String bc = ji.util.d.bc(b9.substring("url:".length()));
                                    if (ji.util.d.dr() || ji.util.i.c(6)) {
                                        ji.io.h.d(this.ab, String.valueOf(String.valueOf(new StringBuffer("> Net(").append(this.a(c)).append("): Javascript EventHandler has supplied an alternative URL..."))));
                                        ji.io.h.d(this.ab, String.valueOf(String.valueOf(new StringBuffer("> Net(").append(this.a(c)).append("): Old = ").append(c))));
                                        ji.io.h.d(this.ab, String.valueOf(String.valueOf(new StringBuffer("> Net(").append(this.a(c)).append("): New = ").append(bc))));
                                    }
                                    try {
                                        c = new URL(bc);
                                        if (ji.util.d.dr() || ji.util.i.c(6)) {
                                            ji.io.h.d(this.ab, String.valueOf(String.valueOf(new StringBuffer("> Net(").append(this.a(c)).append("):  Url now = ").append(c))));
                                        }
                                        n2 = 1;
                                        eo = d;
                                        --d;
                                        continue;
                                    }
                                    catch (Exception ex2) {
                                        ji.io.h.d(this.ab, "Error:");
                                        this.a(ex2);
                                        throw ex;
                                    }
                                }
                                if (a2 != null) {
                                    ji.io.h.d(this.ab, String.valueOf(String.valueOf(new StringBuffer("> Net(").append(this.a(c)).append("): Javascript EventHandler unexpectantly returned: ").append(b9).append(" (in response to JavaScript event ").append(45).append(", ").append(a2.l()).append(")"))));
                                    throw ex;
                                }
                                continue;
                            }
                            break Label_1315;
                        }
                        break Label_1421;
                    }
                }
                if (!b6 && (!b7 || !a0.x) && !this.g(b5)) {
                    if (ji.util.d.dr()) {
                        ji.io.h.d(this.ab, String.valueOf(String.valueOf(new StringBuffer("> Net(").append(this.a(c)).append("): File is in cache: ").append(s3).append(" URL: ").append(c))));
                    }
                    new ac(o, this.ab).a(o, s3);
                }
            }
            if (!b6 && (!b7 || !a0.x) && !this.ag) {
                if (this.af) {
                    this.d(s3);
                }
                else if (ji.util.d.dr()) {
                    ji.io.h.d(this.ab, "not calculating checksum");
                }
            }
        }
        finally {
            synchronized (a0.at) {
                a0.as.remove("".concat(String.valueOf(String.valueOf(c))));
            }
            // monitorexit(a0.at)
            if ((!b7 || !a0.x) && this.f(az)) {
                if ((!ji.util.d.by(az) || !ji.util.d.by(a0)) && ji.util.i.c(94)) {
                    if (b3 && !this.ao) {
                        this.y();
                    }
                }
                else {
                    this.z();
                }
            }
        }
        return s3;
    }
    
    private void d(final String s) {
        if (s == null) {
            if (ji.util.d.dr()) {
                ji.io.h.d(this.ab, "invalid file passed to calculate checksum");
            }
            return;
        }
        if (ji.util.d.dr()) {
            ji.io.h.d(this.ab, "calculating checksum on ".concat(String.valueOf(String.valueOf(s))));
        }
        try {
            final FileInputStream fileInputStream = new FileInputStream(new File(s));
            final BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            final byte[] array = new byte[10000];
            final CRC32 crc32 = new CRC32();
            int read;
            while ((read = bufferedInputStream.read(array)) != -1) {
                crc32.update(array, 0, read);
            }
            fileInputStream.close();
            bufferedInputStream.close();
            this.ah = crc32.getValue();
            this.ag = true;
        }
        catch (Exception ex) {
            if (ji.util.d.dr()) {
                ji.io.h.d(this.ab, "error calculating checksum: ".concat(String.valueOf(String.valueOf(ex.getMessage()))));
            }
        }
    }
    
    private String a(final int n) {
        return ji.util.d.b(n, this.ab);
    }
    
    private final void a(final URL url, final af a1, final String s) {
        try {
            boolean b = false;
            synchronized (ji.net.a0.at) {
                b = (ji.net.a0.ar.get("".concat(String.valueOf(String.valueOf(url)))) != null);
                if (!b) {
                    b = (ji.net.a0.as.get("".concat(String.valueOf(String.valueOf(url)))) != null);
                }
            }
            // monitorexit(a0.at)
            ji.net.a0.a1 = a1;
            this.a(new a6(this, 9, s, this.ao));
            while (b && !this.t && !this.g) {
                ji.util.d.b(50, 561, this.ab);
                synchronized (ji.net.a0.ar) {
                    final Object at = ji.net.a0.at;
                    // monitorenter(at)
                    try {
                        b = (ji.net.a0.ar.get("".concat(String.valueOf(String.valueOf(url)))) != null);
                        if (!b) {
                            b = (ji.net.a0.as.get("".concat(String.valueOf(String.valueOf(url)))) != null);
                        }
                    }
                    // monitorexit(at)
                    finally {}
                    // monitorexit(a0.ar)
                    continue;
                }
                break;
            }
        }
        finally {
            ji.net.a0.a1 = null;
        }
    }
    
    private final String a(final URL url, final String s, final boolean b, final boolean b2, final String s2, final String s3, final String s4, final boolean b3, final Object o, final boolean b4, final boolean b5, final String s5, final boolean b6, final af af, final boolean b7, final boolean b8) throws Exception {
        String a = null;
        boolean b9 = false;
        this.ak = System.currentTimeMillis();
        this.am = 0L;
        this.l = 0L;
        try {
            if (ji.net.a0.x && b6) {
                return null;
            }
            boolean b10 = false;
            if (b7) {
                synchronized (ji.net.a0.at) {
                    b10 = (ji.net.a0.ar.get("".concat(String.valueOf(String.valueOf(url)))) != null);
                    if (!b10) {
                        b10 = (ji.net.a0.as.get("".concat(String.valueOf(String.valueOf(url)))) != null);
                    }
                }
                // monitorexit(a0.at)
            }
            if (b10) {
                this.a(url, af, s4);
                if (this.t || this.g) {
                    return null;
                }
                final String a2 = this.a(url, b, b2, s2, o, b4, af);
                if (a2 != null) {
                    return a2;
                }
            }
            if (ji.net.a0.x && !this.ae) {
                while (ji.net.a0.x && !this.t && !this.g) {
                    ji.util.d.b(50, 56, this.ab);
                }
                if (this.t || this.g) {
                    return null;
                }
            }
            if (!b6) {
                ji.net.a0.x = true;
            }
            synchronized (ji.net.a0.ar) {
                ji.net.a0.ar.put("".concat(String.valueOf(String.valueOf(url))), Thread.currentThread());
            }
            // monitorexit(a0.ar)
            ++ji.net.a0.w;
            b9 = true;
            if (this.v && ji.util.d.ds()) {
                ji.io.h.d(this.ab, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(System.currentTimeMillis()))).append("> Prefetch: Retrieving: ").append(url))));
            }
            this.m = false;
            this.l = System.currentTimeMillis();
            if (ji.util.d.be()) {
                this.z = ji.util.e.be();
                this.s = false;
            }
            a = this.a(url, s, b, b2, s2, s3, s4, b3, o, b4, this.g(b5), s5, b6, b8);
        }
        finally {
            synchronized (ji.net.a0.ar) {
                ji.net.a0.ar.remove("".concat(String.valueOf(String.valueOf(url))));
            }
            // monitorexit(a0.ar)
            this.m = true;
            if (!b6) {
                ji.net.a0.x = false;
            }
            if (b9) {
                --ji.net.a0.w;
            }
        }
        if (a != null && ji.util.d.dv()) {
            ji.io.h.e(this.ab, String.valueOf(String.valueOf(new StringBuffer("Retrieved from the network in ").append(ji.util.d.e(this.al)).append(" : ").append(url))));
        }
        return a;
    }
    
    public final long p() {
        return this.am;
    }
    
    public final long q() {
        return this.ak;
    }
    
    public final long r() {
        return this.al;
    }
    
    public final String a(final URL url, final boolean b, final boolean b2, final String s, final Object o, final boolean b3) throws Exception {
        final long b4 = b(url.toString(), this.ab);
        final String a = this.a.a(ji.io.q.a(), b3, b4, u());
        if (this.a.e(a)) {
            return a;
        }
        if (!b3) {
            final String a2 = this.a.a(ji.io.q.a(), true, b4, u());
            if (this.a.e(a2)) {
                return a2;
            }
        }
        if (!ji.util.i.c(272)) {
            if (ji.net.a0.c.d("".concat(String.valueOf(String.valueOf(b4)))) != null) {
                ji.net.a0.c.a("".concat(String.valueOf(String.valueOf(b4))));
                ji.net.a0.d.a("".concat(String.valueOf(String.valueOf(b4))));
            }
        }
        else if (ji.net.a0.b != null) {
            ji.net.a0.b.b(url.toString(), this.ab);
        }
        return null;
    }
    
    public final String a(final URL url, final boolean b, final boolean b2, final String s, final Object o, final boolean b3, final af af) throws Exception {
        return this.a(url, b, b2, s, o, b3, true, af);
    }
    
    private final String a(final URL url, final boolean b, final boolean b2, final String s, final Object o, final boolean b3, final boolean b4, final af af) throws Exception {
        String a = null;
        try {
            if (ji.net.a0.as != null && url != null && b4) {
                int n = 0;
                synchronized (ji.net.a0.at) {
                    n = ((ji.net.a0.as.get("".concat(String.valueOf(String.valueOf(url)))) != null) ? 1 : 0);
                }
                // monitorexit(a0.at)
                if (n != 0) {
                    this.a(url, af, null);
                }
            }
            if (b2) {
                a = this.a(url, b, b2, s, o, b3);
                this.an = (a != null);
                if (this.an) {
                    try {
                        this.o = this.b(url.toString(), o, this.ab);
                        if (this.o != null) {
                            this.ap = this.o.k;
                            this.ah = this.o.h;
                            this.p = this.o.i;
                            this.q = this.o.j;
                        }
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
            try {
                if (a != null) {
                    try {
                        if (!this.a.e(a)) {
                            this.a(url, o, this.ab);
                            a = null;
                        }
                    }
                    catch (Exception ex2) {
                        ji.util.d.b(ex2);
                        this.a(url, o, this.ab);
                        a = null;
                    }
                }
            }
            catch (Exception ex3) {}
            if (a != null) {
                if (this.v && ji.util.d.ds()) {
                    ji.io.h.d(this.ab, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(System.currentTimeMillis()))).append("> Prefetch: Already cached: ").append(url))));
                }
                return a;
            }
        }
        finally {
            this.m = true;
        }
        return null;
    }
    
    protected void s() {
        try {
            if (ji.util.d.be() && !this.s && (System.currentTimeMillis() - this.l > this.z || ji.util.e.bd())) {
                if (ji.util.d.dr()) {
                    ji.io.h.d(this.ab, "> Net: Retrieve message displayed.");
                }
                this.x();
                this.s = true;
            }
        }
        catch (Exception ex) {}
    }
    
    private final String e(final String s) {
        return String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(ji.util.d.i(s, this.ab)))).append("source_").append(ji.util.d.b(ji.util.d.h(s, this.ab), ".", "_")).append(".log")));
    }
    
    private final boolean f(final String s) {
        return s == null || !s.toLowerCase().equals("noconmsg");
    }
    
    private final String a(final URL url, final String s, final boolean b, final boolean b2, final String s2, final String s3, final String s4, final boolean b3, final Object o, boolean b4, final boolean b5, final String s5, final boolean b6, final boolean b7) throws Exception {
        final boolean dr = ji.util.d.dr();
        long n = 0L;
        final long currentTimeMillis = System.currentTimeMillis();
        int n2 = 0;
        String s6 = null;
        this.ax = false;
        final long a = this.a((Object)url);
        final long b8 = b(url.toString(), this.ab);
        bh bh = null;
        try {
            this.ab();
            this.a(new a6(this, 24));
            ji.net.a0.h = true;
            this.g = false;
            if ((!ji.util.d.by(s3) || !ji.util.d.by(s4)) && !ji.util.d.by(s3) && ji.util.i.c(94) && this.aa()) {
                ji.net.a0.j = true;
                if (this.f(s3)) {
                    this.a(new a6(this, 9, s3, this.ao));
                }
            }
            URLConnection urlConnection = null;
            long n3 = 0L;
            long currentTimeMillis2 = 0L;
            String contentType = null;
            final int n4 = 2048;
            final int n5 = 102400;
            int contentLength = 0;
            int n6 = 0;
            int n7 = 0;
            final int en = ji.util.d.en();
            boolean b9 = true;
            long n8 = 0L;
            boolean b10 = false;
            ac ac = null;
            ac ac2 = null;
            boolean dl = ji.util.d.dl;
            boolean b11 = false;
            if (s6 == null) {
                boolean b12 = false;
                try {
                    if (url.toString().toLowerCase().indexOf("https") >= 0) {
                        b12 = true;
                    }
                }
                catch (Exception ex5) {}
                this.l = System.currentTimeMillis();
                this.z = ji.util.e.bf();
                final long currentTimeMillis3 = System.currentTimeMillis();
                do {
                    if (ji.util.d.be() && !ji.util.d.bd()) {
                        this.s();
                    }
                    try {
                        if (ji.util.d.dr()) {
                            ji.io.h.d(this.ab, String.valueOf(String.valueOf(new StringBuffer("> Net(").append(a).append("): Connecting to: ").append(url).append("/").append(b2))));
                        }
                        if (this.f(s3)) {
                            if (s2 != null) {
                                this.i(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.a(262)))).append(" ").append(s2).append("..."))));
                            }
                            else {
                                this.i(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.a(262)))).append(" ").append(url).append("..."))));
                            }
                        }
                        this.b(0);
                        if (!this.t && (!b6 || !ji.net.a0.x)) {
                            boolean b13 = true;
                            if (ji.util.d.au(this.ab) && ji.util.d.at(this.ab)) {
                                b13 = false;
                            }
                            if (s == null) {
                                if (ji.util.e.j() || !b13) {
                                    urlConnection = ji.util.d.a(url, false, this.ab);
                                }
                                else {
                                    urlConnection = ji.util.d.a(url, b2, this.ab);
                                }
                            }
                            else {
                                bh = new bh(url, this.ab, true, s.getBytes().length, o, false);
                                bh.a(ji.net.a0.a1, s.getBytes());
                                urlConnection = bh.a();
                            }
                        }
                    }
                    catch (bg bg) {
                        ji.net.a0.h = false;
                        if (ji.util.d.cy()) {
                            ji.io.h.d(this.ab, String.valueOf(String.valueOf(new StringBuffer("jiUserLoggedOutException: #2: ").append(url).append("\n").append(bi.a(urlConnection, this.ab)))));
                        }
                        this.a(urlConnection);
                        throw bg;
                    }
                    catch (Exception ex) {
                        ji.util.d.b(ex);
                        ji.net.a0.h = false;
                        if (ji.util.d.cy()) {
                            ji.io.h.d(this.ab, String.valueOf(String.valueOf(new StringBuffer("FileNotFoundException: #2: ").append(url).append("\n").append(bi.a(urlConnection, this.ab)))));
                        }
                        this.a(urlConnection);
                        throw new FileNotFoundException(String.valueOf(String.valueOf(new StringBuffer("#2 ").append(this.a(260)).append("\n ").append(ex.toString()).append("\n(").append(url).append(")").append("\n").append(bi.a(urlConnection, this.ab)))));
                    }
                    this.ap = "";
                    if (!this.t && (!b6 || !ji.net.a0.x)) {
                        if (this.f(s3)) {
                            if (s2 != null) {
                                this.i(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.a(262)))).append(" ").append(s2).append("..."))));
                            }
                            else {
                                this.i(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.a(262)))).append(" ").append(url).append("..."))));
                            }
                        }
                        this.b(0);
                        if (ji.util.d.dr()) {
                            ji.io.h.d(this.ab, String.valueOf(String.valueOf(new StringBuffer("> Net(").append(a).append("): Connection: retrieving content length..."))));
                        }
                        contentLength = urlConnection.getContentLength();
                        if (ji.util.d.dr()) {
                            ji.io.h.d(this.ab, String.valueOf(String.valueOf(new StringBuffer("> Net(").append(a).append("): Connection: Length = ").append(contentLength).append("..."))));
                        }
                        if (ji.util.d.dr()) {
                            ji.io.h.d(this.ab, String.valueOf(String.valueOf(new StringBuffer("> Net(").append(a).append("): Connection: retrieving content type..."))));
                        }
                        try {
                            contentType = urlConnection.getContentType();
                        }
                        catch (Exception ex2) {
                            ji.util.d.b(ex2);
                        }
                        this.ap = "";
                        int n9 = 1;
                        String ap = urlConnection.getHeaderField("X-Custom-Param".concat(String.valueOf(String.valueOf(n9))));
                        if (ji.util.d.dr()) {
                            ji.io.h.d(this.ab, String.valueOf(String.valueOf(new StringBuffer("> Net(X-Custom-Param").append(n9).append("): Custom1: ").append(ap))));
                        }
                        if (!ji.util.d.b()) {
                            if (!ji.util.d.by(ap)) {
                                ji.util.d.a9(true);
                            }
                        }
                        else {
                            ji.util.d.a9(false);
                        }
                        while (!ji.util.d.by(ap)) {
                            if (n9 > 1) {
                                this.ap = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.ap))).append(", ").append(ap)));
                            }
                            else {
                                this.ap = ap;
                            }
                            ++n9;
                            ap = urlConnection.getHeaderField("X-Custom-Param".concat(String.valueOf(String.valueOf(n9))));
                            if (ji.util.d.dr()) {
                                ji.io.h.d(this.ab, String.valueOf(String.valueOf(new StringBuffer("> Net(X-Custom-Param").append(n9).append("): Custom1A: ").append(ap))));
                            }
                        }
                        this.aq = urlConnection.getHeaderField("X-Server-Version");
                        if (ji.util.d.dr()) {
                            ji.io.h.d(this.ab, "> Net(X-Server-Version): ".concat(String.valueOf(String.valueOf(this.aq))));
                        }
                        if (ji.util.d.dr()) {
                            ji.io.h.d(this.ab, String.valueOf(String.valueOf(new StringBuffer("> Net(").append(a).append("): Custom parameters2: ").append(this.ap))));
                        }
                        final String headerField = urlConnection.getHeaderField("Content-Encoding");
                        if (!ji.util.d.by(headerField)) {
                            if (headerField.toLowerCase().indexOf("gzip") > -1) {
                                if (ji.util.d.dr()) {
                                    ji.io.h.d(this.ab, String.valueOf(String.valueOf(new StringBuffer("> Net(").append(a).append("): Data is gzipped"))));
                                }
                                b10 = true;
                            }
                            if (headerField.toLowerCase().indexOf("v1enc") > -1) {
                                dl = true;
                                if (ji.util.d.dr()) {
                                    ji.io.h.d(this.ab, String.valueOf(String.valueOf(new StringBuffer("> Net(").append(a).append("): Data is encrypted"))));
                                }
                                if (b10 && headerField.toLowerCase().indexOf("v1enc") < headerField.toLowerCase().indexOf("gzip")) {
                                    b11 = true;
                                }
                            }
                        }
                    }
                    else if (ji.util.d.dr()) {
                        ji.io.h.d(this.ab, String.valueOf(String.valueOf(new StringBuffer("> Net(").append(a).append("): File read aborted: ").append(this.t).append("/").append(b6).append("/").append(ji.net.a0.x))));
                    }
                    if (!this.t && (!b6 || !ji.net.a0.x) && contentLength <= 0) {
                        if (ji.util.d.dr()) {
                            ji.io.h.d(this.ab, String.valueOf(String.valueOf(new StringBuffer("> Net(").append(a).append("): Connection params: ").append(n2).append("/").append(contentType).append("/").append(contentLength))));
                        }
                        if (url.toString().toLowerCase().indexOf("file:") >= 0) {
                            ji.net.a0.h = false;
                            final URLConnection urlConnection2 = null;
                            if (ji.util.d.cy()) {
                                ji.io.h.d(this.ab, String.valueOf(String.valueOf(new StringBuffer("FileNotFoundException: #3: ").append(url).append("\n").append(bi.a(urlConnection2, this.ab)))));
                            }
                            this.a(urlConnection2);
                            throw new FileNotFoundException(String.valueOf(String.valueOf(new StringBuffer("#3 ").append(this.a(260)).append("\n").append(this.a(286)).append(": ").append(url).append("\n(").append(url).append(")").append("\n").append(bi.a(null, this.ab)))));
                        }
                        if (contentLength == -1 && ji.util.i.c(274)) {
                            b9 = false;
                        }
                        else if (n2 > 2 || b12) {
                            b9 = false;
                        }
                        else if (contentType != null) {
                            if (contentType.toLowerCase().indexOf("multipart/related") >= 0 || contentType.toLowerCase().indexOf("image") >= 0 || contentType.toLowerCase().indexOf("html") >= 0 || contentType.toLowerCase().indexOf("text") >= 0) {
                                b9 = false;
                            }
                        }
                        else {
                            b9 = false;
                        }
                        Label_2419: {
                            if (!b9) {
                                break Label_2419;
                            }
                            if (n2 >= 6) {
                                ji.net.a0.h = false;
                                if (ji.util.d.cy()) {
                                    ji.io.h.d(this.ab, String.valueOf(String.valueOf(new StringBuffer("FileNotFoundException: #3: ").append(url).append("\n").append(bi.a(urlConnection, this.ab)))));
                                }
                                this.a(urlConnection);
                                throw new FileNotFoundException(String.valueOf(String.valueOf(new StringBuffer("#31 ").append(this.a(260)).append("\n").append(this.a(286)).append(": ").append(url).append("\n(").append(url).append(")").append("\n").append(bi.a(null, this.ab)))));
                            }
                            ++n2;
                            urlConnection = null;
                            if (this.f(s3)) {
                                if (s2 != null) {
                                    this.i(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.a(262)))).append(" ").append(s2).append("..."))));
                                }
                                else {
                                    this.i(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.a(262)))).append(" ").append(url).append("..."))));
                                }
                            }
                            if (this.t || (b6 && ji.net.a0.x)) {
                                break Label_2419;
                            }
                            if (ji.util.d.ak) {
                                ji.io.h.d(this.ab, "NET Sleep1...");
                            }
                            ji.util.d.b(300, 58, this.ab);
                            if (ji.util.d.ak) {
                                ji.io.h.d(this.ab, "NET Sleep1a...");
                                break Label_2419;
                            }
                            break Label_2419;
                        }
                        goto Label_2423;
                    }
                } while (contentLength <= 0 && ji.net.a0.h && b9 && !this.t && (!b6 || !ji.net.a0.x));
                int n10 = 15;
                if (this.f(s3)) {
                    if (s2 != null) {
                        this.i(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.a(262)))).append(" ").append(s2).append("..."))));
                    }
                    else {
                        this.i(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.a(262)))).append(" ").append(url).append("..."))));
                    }
                }
                this.b(0);
                try {
                    if (this.f(s3)) {
                        if (s2 != null) {
                            this.i(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.a(262)))).append(" ").append(s2).append("..."))));
                        }
                        else {
                            this.i(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.a(262)))).append(" ").append(url).append("..."))));
                        }
                    }
                    this.b(0);
                    String s7 = "tif";
                    if (b9) {
                        s7 = ji.util.d.bh(url.toString());
                    }
                    else if (contentType != null) {
                        int n11 = contentType.indexOf("/");
                        if (n11 < 0) {
                            n11 = contentType.indexOf("\\");
                        }
                        if (n11 >= 0) {
                            s7 = contentType.substring(n11 + 1);
                        }
                    }
                    this.p = null;
                    this.q = "N/A";
                    if (contentType != null) {
                        this.r = contentType;
                        this.p = this.g(contentType);
                        this.q = this.h(contentType);
                    }
                    if (this.p != null) {
                        final String bc = ji.util.d.bc(this.p.toLowerCase());
                        if (bc.equals("tif")) {
                            s7 = "tif";
                        }
                        else if (bc.equals("tiff")) {
                            s7 = "tif";
                        }
                        else if (bc.equals("bmp")) {
                            s7 = "bmp";
                        }
                        else if (bc.equals("jpeg")) {
                            s7 = "jpg";
                        }
                        else if (bc.equals("text")) {
                            s7 = "txt";
                        }
                        else if (bc.equals("txt")) {
                            s7 = "txt";
                        }
                        else if (bc.equals("doc")) {
                            s7 = "doc";
                        }
                        else if (bc.equals("xls")) {
                            s7 = "xls";
                        }
                        else if (bc.equals("pdf")) {
                            s7 = "pdf";
                        }
                        else if (bc.equals("htm")) {
                            s7 = "htm";
                        }
                        else if (bc.equals("html")) {
                            s7 = "htm";
                        }
                        else if (bc.equals("jp2")) {
                            s7 = "jp2";
                        }
                        else if (bc.equals("jpeg2")) {
                            s7 = "jp2";
                        }
                        else if (bc.equals("jpeg2000")) {
                            s7 = "jp2";
                        }
                        else if (bc.equals("jj2")) {
                            s7 = "jp2";
                        }
                    }
                    String s8 = ji.util.d.d(s7, false);
                    boolean b14 = false;
                    if (s8 != null && (s8.equals("gzip") || s8.equals("gz"))) {
                        this.p = "gzip";
                        this.q = this.p;
                    }
                    if (s8 == null) {
                        b14 = true;
                    }
                    else if (s8.length() == 0) {
                        b14 = true;
                    }
                    if (b14) {
                        if (contentType != null) {
                            int n12 = contentType.indexOf("/");
                            if (n12 < 0) {
                                n12 = contentType.indexOf("\\");
                            }
                            if (n12 >= 0) {
                                s8 = contentType.substring(n12 + 1);
                            }
                        }
                        if (s8 != null && s8.toLowerCase().equals("filenet")) {
                            s8 = "001";
                        }
                        if (s8 == null) {
                            s8 = "tmp";
                        }
                    }
                    n10 = 16;
                    this.k = String.valueOf(String.valueOf(new StringBuffer("").append(contentType).append(" - ").append(s8).append("/").append(contentLength)));
                    if (ji.util.d.dr()) {
                        ji.io.h.d(this.ab, String.valueOf(String.valueOf(new StringBuffer("> Net(").append(a).append("): Connection state: ").append(b9).append("/").append(this.k))));
                    }
                    n3 = (currentTimeMillis2 = System.currentTimeMillis());
                    long n13 = System.currentTimeMillis();
                    this.aa = urlConnection.getInputStream();
                    if (dl) {
                        b4 = true;
                    }
                    if (this.g(b5)) {
                        s6 = ji.util.d.ee();
                    }
                    else {
                        if (!b11) {
                            s6 = this.a.a(ji.io.q.a(), b4, b8, u());
                        }
                        else {
                            s6 = this.a.a(ji.io.q.a(), b8, u());
                        }
                        ac = new ac(s6, true, false, 0, o, this.ab, dl);
                        if (ji.util.i.c(95)) {
                            ac2 = new ac(this.e(s6), true, false, 0, o, this.ab, dl);
                        }
                    }
                    n10 = 17;
                    int n14 = n4;
                    byte[] array = new byte[n14];
                    int read = 0;
                    if (b9) {
                        n6 = contentLength;
                    }
                    long n15 = System.currentTimeMillis();
                    final String a2 = this.a(259);
                    String value = "";
                    if (b9) {
                        value = String.valueOf(String.valueOf(new StringBuffer(" ").append(this.a(266)).append(" ").append(ji.util.d.b((long)contentLength, this.ab))));
                    }
                    int n16 = 0;
                    this.i(String.valueOf(String.valueOf(a2)).concat("..."));
                    if (ji.util.d.dr()) {
                        ji.io.h.d(this.ab, String.valueOf(String.valueOf(new StringBuffer("> Net(").append(a).append("): Reading..."))));
                    }
                    int n17;
                    if (url.toString().toLowerCase().indexOf(".v1") >= 0) {
                        n17 = ji.util.d.bb();
                        if (n17 > 0) {
                            ji.io.h.d(this.ab, String.valueOf(String.valueOf(new StringBuffer("> Net(").append(a).append("): Applying simulated delay to resource file of ").append(n17).append(" milliseconds"))));
                        }
                    }
                    else {
                        n17 = ji.util.d.bc();
                        if (n17 > 0) {
                            ji.io.h.d(this.ab, String.valueOf(String.valueOf(new StringBuffer("> Net(").append(a).append("): Applying simulated delay to image file of ").append(n17).append(" milliseconds"))));
                        }
                    }
                    if (ji.net.a0.y) {
                        this.x();
                        this.s = true;
                    }
                    int n19;
                    final int n18 = n19 = contentLength / 100;
                    CRC32 crc32 = null;
                    if (this.af) {
                        crc32 = new CRC32();
                    }
                    n10 = 18;
                    boolean b15 = false;
                    if ((!ji.util.d.by(s3) || !ji.util.d.by(s4)) && !ji.util.d.by(s4) && ji.util.i.c(94) && this.aa()) {
                        b15 = true;
                    }
                    boolean b16 = false;
                    if (ji.util.d.ag == 0) {
                        b16 = true;
                    }
                    n10 = 19;
                    while (read >= 0 && (n7 < contentLength || !b9) && !this.g && !this.t && (!b6 || !ji.net.a0.x)) {
                        if (b15 && !ji.net.a0.j) {
                            ji.net.a0.j = true;
                            this.a(new a6(this, 9, s4, this.ao));
                        }
                        n10 = 20;
                        if (n17 > 0) {
                            ji.io.h.d(this.ab, String.valueOf(String.valueOf(new StringBuffer("> Net(").append(a).append("): Simulated delay ").append(n17).append(" milliseconds"))));
                            if (ji.util.d.ak) {
                                ji.io.h.d(this.ab, "NET Sleep2...");
                            }
                            ji.util.d.b(n17, 59, this.ab);
                            if (ji.util.d.ak) {
                                ji.io.h.d(this.ab, "NET Sleep2a...");
                            }
                        }
                        if (ji.util.d.be() && !ji.util.d.bd()) {
                            this.s();
                        }
                        if (n14 > n6 && b9) {
                            n14 = n6;
                        }
                        if (ji.util.e.b()) {
                            ji.util.d.b(100, 60, this.ab);
                        }
                        if (dr) {
                            for (int i = 0; i < array.length; ++i) {
                                array[i] = -4;
                            }
                        }
                        n10 = 21;
                        if (!this.t && (!b6 || !ji.net.a0.x)) {
                            read = this.aa.read(array, 0, n14);
                        }
                        final long abs = Math.abs(n15 - System.currentTimeMillis());
                        final long abs2 = Math.abs(n13 - System.currentTimeMillis());
                        n8 += abs;
                        if (read > 0) {
                            n10 = 22;
                            try {
                                ji.net.a0.a4 += read;
                                this.am += read;
                                if (abs2 > 0) {
                                    ji.net.a0.a3 += abs2;
                                    n13 = System.currentTimeMillis();
                                }
                            }
                            catch (Exception ex6) {}
                            if (dr) {
                                try {
                                    for (int j = 0; j < read; ++j) {
                                        n += array[j];
                                    }
                                }
                                catch (Exception ex3) {
                                    this.a(ex3);
                                }
                            }
                            if (!this.t && (!b6 || !ji.net.a0.x)) {
                                if (this.g(b5)) {
                                    n10 = 23;
                                    if (b10) {
                                        s6 = String.valueOf(String.valueOf(s6)).concat(String.valueOf(String.valueOf(new String(array, 0, read, "ISO-8859-1"))));
                                    }
                                    else if (s5 != null) {
                                        s6 = String.valueOf(String.valueOf(s6)).concat(String.valueOf(String.valueOf(new String(array, 0, read, s5))));
                                    }
                                    else {
                                        s6 = String.valueOf(String.valueOf(s6)).concat(String.valueOf(String.valueOf(new String(array, 0, read))));
                                    }
                                }
                                else {
                                    n10 = 24;
                                    ac.b(array, 0, read);
                                    if (ac2 != null) {
                                        ac2.b(array, 0, read);
                                    }
                                }
                            }
                            n10 = 25;
                            if (this.af) {
                                crc32.update(array, 0, read);
                            }
                            n10 = 26;
                            n7 += read;
                            n6 -= read;
                            n19 -= read;
                            if (dr) {
                                ji.io.h.d(this.ab, String.valueOf(String.valueOf(new StringBuffer("> Net(").append(a).append("): Read ").append(read).append(" bytes (total ").append(n7).append(")"))));
                            }
                            if (abs < 500 && read >= n14) {
                                ++n16;
                            }
                            else {
                                n16 = 0;
                            }
                            if (!this.ad && n8 > 500) {
                                this.w();
                            }
                            n10 = 27;
                            if (n16 > 3) {
                                n14 = Math.min(n5, n14 * 2);
                            }
                            else {
                                n14 = Math.max(n14 / 2, n4);
                            }
                            if (array.length < n14) {
                                array = new byte[n14];
                            }
                            n10 = 28;
                            if (contentLength > 0) {
                                n10 = 29;
                                if (n19 <= 0) {
                                    if (ji.util.i.c(97)) {
                                        if (b16) {
                                            final long abs3 = Math.abs(n13 - System.currentTimeMillis());
                                            try {
                                                if (abs3 > 0) {
                                                    ji.net.a0.a3 += abs3;
                                                    n13 = System.currentTimeMillis();
                                                }
                                                if (ji.net.a0.a3 > 0) {
                                                    ji.util.d.c((double)Math.round(1000.0 * ji.net.a0.a4 / ji.net.a0.a3));
                                                }
                                            }
                                            catch (Exception ex7) {}
                                        }
                                        if (ji.util.d.ag > 0) {
                                            this.i(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(a2))).append(" ").append(ji.util.d.b((long)n7, this.ab)).append(value).append(" (").append(100 * n7 / contentLength).append("%) ...(").append(ji.util.d.c(contentLength - n7, this.ab)).append(")..."))));
                                        }
                                        else {
                                            this.i(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(a2))).append(" ").append(ji.util.d.b((long)n7, this.ab)).append(value).append(" (").append(100 * n7 / contentLength).append("%) ..."))));
                                        }
                                    }
                                    else {
                                        this.i(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(a2))).append(" ").append(ji.util.d.b((long)n7, this.ab)).append(value).append(" (").append(100 * n7 / contentLength).append("%) ..."))));
                                    }
                                    this.b(100 * n7 / contentLength);
                                    n19 = n18;
                                }
                            }
                            else {
                                this.i(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(a2))).append(" ").append(ji.util.d.b((long)n7, this.ab)).append(value).append(" ..."))));
                            }
                            n10 = 30;
                        }
                        else {
                            n10 = 31;
                            if (dr) {
                                ji.io.h.d(this.ab, "Ooh! numRead is ".concat(String.valueOf(String.valueOf(read))));
                            }
                            if (Math.abs(n15 - System.currentTimeMillis()) >= en) {
                                throw new Exception(this.a(290));
                            }
                        }
                        n15 = System.currentTimeMillis();
                        if ((this.t || (b6 && ji.net.a0.x)) && ji.util.d.ak) {
                            ji.io.h.d(this.ab, "NET ABORTED1...");
                        }
                    }
                    n10 = 32;
                    if (this.af) {
                        if (ji.util.d.dr()) {
                            ji.io.h.d(this.ab, "calculate checksum is on, calculating value...");
                        }
                        this.ah = crc32.getValue();
                        this.ag = true;
                        if (ji.util.d.dr()) {
                            ji.io.h.d(this.ab, "checksum found value: ".concat(String.valueOf(String.valueOf(this.ah))));
                        }
                    }
                    final long abs4 = Math.abs(n13 - System.currentTimeMillis());
                    try {
                        if (abs4 > 0) {
                            ji.net.a0.a3 += abs4;
                            System.currentTimeMillis();
                        }
                        if (ji.net.a0.a3 > 0) {
                            ji.util.d.c((double)Math.round(1000.0 * ji.net.a0.a4 / ji.net.a0.a3));
                        }
                        ji.util.d.b((long)(int)this.am);
                    }
                    catch (Exception ex8) {}
                    n10 = 33;
                    if (dr) {
                        ji.io.h.d(this.ab, String.valueOf(String.valueOf(new StringBuffer("> Net(").append(a).append("): Read complete (").append(n7).append(" bytes)"))));
                    }
                    if (n7 <= 0 && !b7) {
                        if (dr) {
                            ji.io.h.d(this.ab, "Argh! num-bytes-so-far is ".concat(String.valueOf(String.valueOf(n7))));
                        }
                        ji.net.a0.h = false;
                        if (ji.util.d.cy()) {
                            ji.io.h.d(this.ab, String.valueOf(String.valueOf(new StringBuffer("FileNotFoundException: #4: ").append(url).append("\n").append(bi.a(urlConnection, this.ab)))));
                        }
                        this.a(urlConnection);
                        if (!b6) {
                            throw new FileNotFoundException(String.valueOf(String.valueOf(new StringBuffer("#4 ").append(this.a(260)).append("\n").append(this.a(286)).append(": ").append(url).append("\n(").append(url).append(")").append("\n").append(bi.a(urlConnection, this.ab)))));
                        }
                    }
                    n10 = 34;
                }
                catch (Exception ex4) {
                    if ((this.t || (b6 && ji.net.a0.x)) && ji.util.d.ak) {
                        ji.io.h.d(this.ab, "NET ABORTED2...");
                    }
                    if (dr) {
                        ji.io.h.d(this.ab, String.valueOf(String.valueOf(new StringBuffer("> Net(").append(a).append("): Error#2: step = ").append(n10))));
                        this.a(ex4);
                    }
                    else if (!this.t && (!b6 || !ji.net.a0.x)) {
                        ji.util.d.b(ex4);
                    }
                    try {
                        if (this.aa != null) {
                            this.aa.close();
                            this.aa = null;
                        }
                        if (ac != null) {
                            ac.a(o);
                            ac = null;
                        }
                        if (ac2 != null) {
                            ac2.a(o);
                            ac2 = null;
                        }
                    }
                    catch (Exception ex9) {}
                    if (!this.g(b5)) {
                        this.a(url, o, this.ab);
                    }
                    s6 = null;
                    if (contentLength < 0) {
                        if (ji.util.d.cy()) {
                            ji.io.h.d(this.ab, String.valueOf(String.valueOf(new StringBuffer("FileNotFoundException: #5: ").append(url).append("\n").append(bi.a(urlConnection, this.ab)))));
                        }
                        this.a(urlConnection);
                        throw new FileNotFoundException(String.valueOf(String.valueOf(new StringBuffer("#5 ").append(this.a(260)).append("\n").append(this.a(286)).append(": ").append(url).append("\n").append("\n").append(bi.a(urlConnection, this.ab)))));
                    }
                    this.a(urlConnection);
                    throw ex4;
                }
                finally {
                    if (this.aa != null) {
                        this.aa.close();
                        this.aa = null;
                    }
                    if (ac != null) {
                        ac.a(o);
                    }
                    if (ac2 != null) {
                        ac2.a(o);
                    }
                    this.b(0);
                    ji.net.a0.h = false;
                    if ((!ji.util.d.by(s3) || !ji.util.d.by(s4)) && ji.util.i.c(94)) {
                        if (this.s && b3 && !this.ao) {
                            this.z();
                        }
                        this.s = false;
                    }
                    else if (this.s) {
                        this.z();
                        this.s = false;
                    }
                }
                if (!this.g && !this.t && (!b6 || !ji.net.a0.x)) {
                    final long currentTimeMillis4 = System.currentTimeMillis();
                    this.al = System.currentTimeMillis() - this.ak;
                    boolean b17 = false;
                    if (b10) {
                        b17 = true;
                    }
                    else if (this.p != null && (this.p.toLowerCase().equals("gzip") || this.p.toLowerCase().equals("gz") || this.p.toLowerCase().equals("zip") || this.p.toLowerCase().equals("zipp") || this.p.toLowerCase().equals("zippped") || this.p.toLowerCase().indexOf("zip") >= 0 || this.p.toLowerCase().indexOf("gzip") >= 0)) {
                        b17 = true;
                    }
                    if (b17) {
                        final String s9 = s6;
                        boolean b18 = dl;
                        if (b11) {
                            b18 = false;
                        }
                        s6 = this.a(s9, b18, o, null, this.e, url.toString(), this.g(b5), b11, s5);
                    }
                    if (b11) {
                        b4 = true;
                    }
                    if (contentLength == -1 && this.am > 0) {
                        contentLength = (int)this.am;
                    }
                    this.o = this.a(currentTimeMillis3, n3, currentTimeMillis2, currentTimeMillis4, contentLength, this.al, this.am);
                    if (!this.g(b5)) {
                        this.a(url, s6, this.p, this.q, this.ap, this.o, this.ah, b4, o);
                    }
                    if (this.f(s3)) {
                        this.i("");
                    }
                }
                else {
                    if (ji.util.d.ak) {
                        ji.io.h.d(this.ab, "NET ABORTED3...");
                    }
                    this.u = true;
                    this.a(url, o, this.ab);
                    s6 = null;
                }
            }
        }
        finally {
            try {
                ji.net.a0.h = false;
                if (this.f(s3)) {
                    this.i("");
                }
                if (dr) {
                    ji.io.h.d(this.ab, "Network read checksum: ".concat(String.valueOf(String.valueOf(n))));
                    ji.util.e.a(s6, o, "Cached", this.ab);
                }
            }
            catch (Exception ex10) {}
            if (ji.util.d.dr() || ji.util.i.c(89)) {
                ji.io.h.d(this.ab, String.valueOf(String.valueOf(new StringBuffer("File load completed in ").append(System.currentTimeMillis() - currentTimeMillis).append(" milliseconds (").append(url).append(")"))));
            }
            this.ab();
            this.a(new a6(this, 25));
            this.ax = false;
            if (bh != null) {
                bh.e();
            }
        }
        if (dr && s6 != null) {
            if (s6.startsWith("V!RF")) {
                ji.io.h.d(this.ab, String.valueOf(String.valueOf(new StringBuffer("> Net(").append(a).append("): Read finalized: (RAM FILE)"))));
            }
            else {
                ji.io.h.d(this.ab, String.valueOf(String.valueOf(new StringBuffer("> Net(").append(a).append("): Read finalized: ").append(s6))));
            }
        }
        if (this.u) {
            if (dr) {
                ji.io.h.d(this.ab, String.valueOf(String.valueOf(new StringBuffer("> Net(").append(a).append("): Read ABORTED"))));
            }
            return null;
        }
        return s6;
    }
    
    private boolean g(final boolean b) {
        return b;
    }
    
    private void a(final URLConnection urlConnection) throws jiServerException {
        if (urlConnection != null) {
            String headerField = null;
            try {
                headerField = urlConnection.getHeaderField("X-Error");
            }
            catch (Exception ex) {
                if (ji.util.d.cy()) {
                    ji.io.h.d(this.ab, "Couldn't retrieve any additional header fields from error response: ".concat(String.valueOf(String.valueOf(ex.getStackTrace()))));
                }
            }
            if (!ji.util.d.by(headerField)) {
                throw new jiServerException(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.a(1197)))).append("\n ").append(headerField))));
            }
            if (ji.util.d.cy()) {
                ji.io.h.d(this.ab, "Couldn't retrieve an error message from error response");
            }
        }
    }
    
    private String g(final String s) {
        String s2 = null;
        final int index = s.indexOf("/");
        if (index >= 0) {
            final int n = index + 1;
            final int index2 = s.indexOf(";", n);
            if (index2 > n + 1) {
                s2 = s.substring(n, index2).trim();
            }
            else {
                s2 = s.substring(n).trim();
            }
        }
        return s2;
    }
    
    private String h(final String s) {
        String substring = s;
        final int index = s.indexOf("/");
        if (index >= 0) {
            final int index2 = s.indexOf(";", index + 1);
            if (index2 > index + 2) {
                substring = s.substring(0, index2);
            }
        }
        return substring;
    }
    
    private final a7 a(final long a, final long b, final long c, final long d, final long e, final long f, final long g) {
        final a7 a2 = new a7(this.ab);
        a2.a = a;
        a2.b = b;
        a2.c = c;
        a2.d = d;
        a2.e = e;
        a2.f = f;
        a2.g = g;
        return a2;
    }
    
    private final void i(final String s) {
        this.a(new a6(this, 1, s));
    }
    
    private final void b(final int n) {
        this.a(new a6(this, 4, "".concat(String.valueOf(String.valueOf(n)))));
    }
    
    private final void w() {
        this.ad = true;
        this.a(new a6(this, 18, ""));
    }
    
    private final void x() {
        if (!ji.net.a0.j && this.aa()) {
            ji.net.a0.j = true;
            this.a(new a6(this, 9, ""));
        }
    }
    
    private final void y() {
        this.l = -1L;
        if (ji.net.a0.j && ji.util.d.d() && this.aa()) {
            this.a(new a6(this, 13, ""));
            ji.net.a0.j = false;
        }
    }
    
    private final void z() {
        this.l = -1L;
        if (ji.net.a0.j && ji.util.d.d() && this.aa()) {
            this.a(new a6(this, 10, ""));
            ji.net.a0.j = false;
        }
    }
    
    public final void a(final af af) {
        if (this.e != null && this.e.a(af)) {
            this.e.b(af);
        }
    }
    
    public final void b(final af af) {
        if (this.e == null) {
            this.e = new c("jiNetCache1", 2);
        }
        if (!this.e.a(af)) {
            this.e.c(af);
        }
    }
    
    private final boolean aa() {
        return ji.net.a0.a1 != null || (this.e != null && this.e.b() > 0);
    }
    
    public final void a(final a9 a9) {
        if (a9.d() instanceof a6) {
            this.b((a6)a9.d());
        }
    }
    
    protected final void a(final a6 a6) {
        this.ac.a(new a9(this, a6, true));
    }
    
    protected final void b(final a6 a6) {
        synchronized (this.av) {
            boolean b = true;
            if (!this.ax) {
                switch (a6.d()) {
                    case 24: {
                        this.au = System.currentTimeMillis();
                        b = false;
                        break;
                    }
                    case 25: {
                        this.au = 0L;
                        b = false;
                        this.ab();
                        break;
                    }
                    case 1:
                    case 4: {
                        if (this.au > 0 && System.currentTimeMillis() - this.au < ji.util.i.d(9)) {
                            b = false;
                            break;
                        }
                        break;
                    }
                    case 9: {
                        if (this.au > 0 && System.currentTimeMillis() - this.au < ji.util.i.d(9)) {
                            b = false;
                            ji.net.a0.j = false;
                            break;
                        }
                        break;
                    }
                }
            }
            if (b && (this.au > 0 || this.ax)) {
                while (this.av.size() > 0) {
                    final a6 a7 = this.av.elementAt(0);
                    this.av.removeElementAt(0);
                    this.c(a7);
                }
                this.c(a6);
            }
            else {
                if (this.aw == null) {
                    (this.aw = new bb(this.ab, new ab9(this))).start();
                }
                this.av.addElement(a6);
            }
        }
        // monitorexit(this.av)
    }
    
    private final void ab() {
        try {
            if (this.av != null) {
                while (this.av.size() > 0) {
                    this.av.removeElementAt(0);
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public final void c(final a6 a6) {
        try {
            if (a6.d() == 24) {
                return;
            }
            if (a6.d() == 25) {
                return;
            }
            if (ji.net.a0.a1 != null) {
                ji.net.a0.a1.a(a6);
            }
        }
        catch (Exception ex) {
            this.a(ex);
        }
        try {
            if (this.e != null) {
                final c e = this.e;
                for (int b = e.b(), i = 0; i < b; ++i) {
                    if (e.b(i) != null) {
                        ((af)e.b(i)).a(a6);
                    }
                }
            }
        }
        catch (Exception ex2) {
            ji.util.d.a(ex2);
        }
    }
    
    private final long a(final Object ai) {
        if (ai == null) {
            return 0L;
        }
        if (this.ai != null && this.ai.equals(ai)) {
            return this.aj;
        }
        this.ai = ai;
        long aj = 0L;
        final byte[] bytes = ai.toString().getBytes();
        for (int i = 0; i < bytes.length; ++i) {
            aj += (bytes[i] & 0xFF);
        }
        return this.aj = aj;
    }
    
    private final void a(final ac ac, final String s, final String i, final String j, final String k, final a7 a7, final long h, final Object o) {
        if (a7 != null) {
            try {
                a7.i = i;
                a7.j = j;
                a7.k = k;
                a7.h = h;
                if (ji.net.a0.ay) {
                    a7.a(ac);
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    private final void a(final URL url, final String s, final String s2, final String s3, final String s4, final a7 a7, final long n, final boolean b, final Object o) {
        try {
            if (url != null) {
                final long b2 = b(url.toString(), this.ab);
                final String a8 = this.a.a(ji.io.q.a(), b, b2, u());
                final ac ac = new ac(a8, true, false, 0, false, o, true, this.ab, false, false, true);
                try {
                    this.a(ac, a8, s2, s3, s4, a7, n, o);
                }
                finally {
                    ac.a(o);
                }
                if (!ji.util.i.c(272)) {
                    ji.net.a0.d.a("".concat(String.valueOf(String.valueOf(b2))), a7);
                    if (ji.net.a0.c.d("".concat(String.valueOf(String.valueOf(b2)))) == null) {
                        ji.net.a0.c.a("".concat(String.valueOf(String.valueOf(b2))), this.ab);
                    }
                }
                else {
                    ji.net.a0.b.a(url.toString(), a7);
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public final String a(final URL url) {
        try {
            final String a = this.a.a(ji.io.q.a(), false, b(url.toString(), this.ab), u());
            if (this.a.e(a)) {
                return a;
            }
        }
        catch (Exception ex) {}
        return null;
    }
    
    private final String a(String substring, final boolean b, final Object o, final af af, final c c, final String s, final boolean b2, final boolean b3, final String s2) throws Exception {
        if (this.g(b2)) {
            final String n = this.a.n();
            substring = substring.substring("V!RF".length());
            final ac ac = new ac(n, true, false, 0, o, this.ab, false);
            ac.b(substring.getBytes("ISO-8859-1"));
            ac.a(o);
            final String a = this.a(n, b, o, af, c, s, this.g(b2), b3);
            ji.io.ac.c(n, this.ab);
            final byte[] array = new byte[4096];
            final ac ac2 = new ac(a, true, false, 0, o, this.ab, false);
            String s3 = "";
            int a2;
            while ((a2 = ac2.a(array)) > -1) {
                if (s2 != null) {
                    s3 = String.valueOf(String.valueOf(s3)).concat(String.valueOf(String.valueOf(new String(array, 0, a2, s2))));
                }
                else {
                    s3 = String.valueOf(String.valueOf(s3)).concat(String.valueOf(String.valueOf(new String(array, 0, a2))));
                }
            }
            ac2.a(o);
            ji.io.ac.c(a, this.ab);
            return "V!RF".concat(String.valueOf(String.valueOf(s3)));
        }
        return this.a(substring, b, o, af, c, s, this.g(b2), b3);
    }
    
    private final String a(String a, final boolean b, final Object o, final af af, final c c, String externalForm, final boolean b2, final boolean b3) throws Exception {
        String s;
        boolean a2;
        try {
            final long currentTimeMillis = System.currentTimeMillis();
            if (ji.util.d.dr()) {
                ji.io.h.d(this.ab, "> Net: Attempting to unzip: ".concat(String.valueOf(String.valueOf(a))));
            }
            s = ji.zip.a4.a(a, b, o, this.ab, af, c, false, b3);
            a2 = ji.zip.a4.a();
            if (!a2) {
                String s2;
                boolean b4;
                if (ji.util.d.bj(externalForm)) {
                    int n = externalForm.lastIndexOf("/");
                    if (n < 0) {
                        n = externalForm.lastIndexOf("\\");
                    }
                    s2 = externalForm.substring(0, n);
                    b4 = true;
                }
                else {
                    externalForm = new File(externalForm).toURL().toExternalForm();
                    int n2 = externalForm.lastIndexOf("/");
                    if (n2 < 0) {
                        n2 = externalForm.lastIndexOf("\\");
                    }
                    s2 = externalForm.substring(0, n2);
                    b4 = true;
                }
                s = ji.zip.a4.a(a, o, this.ab, true, s2, b4, ji.net.a0.ay);
                if (s != null) {
                    final String[] b5 = ji.zip.a4.b();
                    final String i = ji.util.d.i(externalForm, this.ab);
                    for (int j = 0; j < b5.length; ++j) {
                        if (b5[j] != null) {
                            final int index = b5[j].indexOf("<:>");
                            if (index >= 0) {
                                final String substring = b5[j].substring(0, index);
                                final String substring2 = b5[j].substring(index + 3);
                                String s3;
                                if (i.endsWith("/") || i.endsWith("\\")) {
                                    s3 = String.valueOf(String.valueOf(i)).concat(String.valueOf(String.valueOf(substring)));
                                }
                                else {
                                    s3 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(i))).append("/").append(substring)));
                                }
                                final long b6 = b(s3, this.ab);
                                int n3 = 0;
                                if (!ji.util.i.c(272)) {
                                    n3 = ((ji.net.a0.d.d("".concat(String.valueOf(String.valueOf(b6)))) == null) ? 1 : 0);
                                }
                                else if (ji.net.a0.b == null) {
                                    ji.net.a0.b = new a1();
                                    n3 = 1;
                                }
                                else if (ji.net.a0.b.a(s3) == null) {
                                    n3 = 1;
                                }
                                if (n3 != 0) {
                                    this.p = ji.util.d.bh(a);
                                    this.q = this.p;
                                    final long currentTimeMillis2 = System.currentTimeMillis();
                                    this.o = this.a(currentTimeMillis, currentTimeMillis2, currentTimeMillis, currentTimeMillis2, ji.io.ac.a(substring2, this.ab), 0L, 0L);
                                    this.a(new URL(s3), substring2, this.p, this.q, this.ap, this.o, 0L, b, o);
                                }
                            }
                        }
                    }
                }
            }
            if (ji.util.d.dr()) {
                if (!s.equals(a)) {
                    ji.io.h.d(this.ab, "> Net: Unzipped successfully");
                }
                else {
                    ji.io.h.d(this.ab, "> Net: Unzip failed!");
                }
            }
        }
        catch (Exception ex) {
            this.a(ex);
            return a;
        }
        if (a2 && !this.g(b2) && !s.equals(a)) {
            ji.io.ac.c(a, this.ab);
            if (b3) {
                a = this.a.a(ji.io.q.a(), b3, b(externalForm.toString(), this.ab), u());
            }
            ji.io.ac.a(s, a, this.ab);
            s = a;
        }
        return s;
    }
    
    public void f(final boolean af) {
        if (ji.util.d.dr()) {
            ji.io.h.d(this.ab, "turning checksum calc: ".concat(String.valueOf(String.valueOf(af))));
        }
        if (af) {
            this.ag = false;
            this.ah = 0L;
        }
        this.af = af;
    }
    
    public long t() {
        if (!this.ag) {
            if (ji.util.d.dr()) {
                ji.io.h.d(this.ab, "checksum requested, but was never calculated");
            }
        }
        else if (ji.util.d.dr()) {
            ji.io.h.d(this.ab, "checksum requested, returning ".concat(String.valueOf(String.valueOf(this.ah))));
        }
        return this.ah;
    }
    
    public static final c c(final String s) {
        c c = null;
        try {
            if (!ji.util.d.by(s)) {
                final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
                if (stringTokenizer.countTokens() > 0) {
                    c = new c("jiDocHandlerCustomParams", stringTokenizer.countTokens());
                    while (stringTokenizer.hasMoreTokens()) {
                        final String bc = ji.util.d.bc(stringTokenizer.nextToken());
                        try {
                            final int index = bc.indexOf("=");
                            if (index <= 0) {
                                continue;
                            }
                            c.a(ji.util.d.bc(bc.substring(0, index)).toLowerCase(), ji.util.d.bc(bc.substring(index + 1)));
                        }
                        catch (Exception ex) {}
                    }
                }
            }
        }
        catch (Exception ex2) {}
        return c;
    }
    
    public static final boolean a(final String s, final c c, final String s2) {
        boolean b = false;
        try {
            if (c != null) {
                final String s3 = (String)c.d(s2);
                if (ji.util.d.dr()) {
                    ji.io.h.d(s, "> Net: Custom: ".concat(String.valueOf(String.valueOf(s3))));
                }
                if (!ji.util.d.by(s3)) {
                    final String lowerCase = s3.toLowerCase();
                    if (lowerCase.equals("1") || lowerCase.equals("true") || lowerCase.equals("yes")) {
                        b = true;
                    }
                }
            }
        }
        catch (Exception ex) {}
        return b;
    }
    
    public static final int a(final String s, final c c) {
        int c2 = -1;
        try {
            if (c != null) {
                final String s2 = (String)c.d("pixeldepth");
                if (ji.util.d.dr()) {
                    ji.io.h.d(s, "> Net: Custom3: ".concat(String.valueOf(String.valueOf(s2))));
                }
                if (!ji.util.d.by(s2)) {
                    c2 = ji.util.d.c(s2, -1);
                }
            }
        }
        catch (Exception ex) {}
        return c2;
    }
    
    private final void a(final Exception ex) {
        final String message = ex.getMessage();
        if (message != null && message.toLowerCase().indexOf("ok") >= 0) {
            return;
        }
        ex.printStackTrace();
    }
    
    static {
        ji.net.a0.b = null;
        ji.net.a0.c = null;
        ji.net.a0.d = null;
        ji.net.a0.h = false;
        ji.net.a0.j = false;
        ji.net.a0.w = 0;
        ji.net.a0.x = false;
        ji.net.a0.y = false;
        ji.net.a0.ar = new Hashtable();
        ji.net.a0.as = new Hashtable();
        ji.net.a0.at = new Object();
        ji.net.a0.ay = false;
        ji.net.a0.az = null;
        ji.net.a0.a0 = null;
        ji.net.a0.a1 = null;
        ji.net.a0.a3 = 0.0;
        ji.net.a0.a4 = 0.0;
    }
    
    class ab9 implements Runnable
    {
        a0 a;
        
        public ab9(final a0 a) {
            this.a = null;
            this.a = a;
        }
        
        public void run() {
            ji.util.d.b(ji.util.i.d(9), 30932, ji.net.a0.this.ab);
            synchronized (ji.net.a0.this.av) {
                while (this.a.av.size() > 0) {
                    final a6 a6 = this.a.av.elementAt(0);
                    this.a.av.removeElementAt(0);
                    this.a.c(a6);
                }
                this.a.ax = true;
                this.a();
            }
            // monitorexit(this.b.av)
        }
        
        public void a() {
            try {
                this.a.aw = null;
                this.a = null;
            }
            catch (Exception ex) {}
        }
    }
}
