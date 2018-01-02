// 
// Decompiled by Procyon v0.5.30
// 

package ji.io;

import ji.sec.au;
import ji.res.o;
import ji.v1event.af;
import ji.sec.f;
import ji.util.e;
import java.util.Enumeration;
import ji.sec.u;
import ji.sec.az;
import java.io.OutputStream;
import java.io.File;
import ji.annotate.b8;
import ji.util.i;
import ji.res.s;
import ji.util.d;
import ji.sec.aw;
import java.util.Hashtable;

public class q
{
    private String a;
    private String b;
    private final String c = "x";
    private final String d = "xe";
    private String e;
    private static boolean f;
    private boolean g;
    private boolean h;
    private String i;
    private String j;
    public boolean k;
    public boolean l;
    public static boolean m;
    private String n;
    private static int o;
    private static String p;
    private static int q;
    char r;
    private String s;
    private static Hashtable t;
    private static String u;
    private aw v;
    
    public static q a(Object w, final String s) {
        if (s == null) {
            return null;
        }
        synchronized (ji.io.q.t) {
            if (d.aq()) {
                ji.io.q.t.remove(s);
            }
            if (w == null) {
                w = d.w(s);
            }
            if (ji.io.q.t.get(s) == null) {
                final q q = new q(w, s);
                ji.io.q.t.put(s, q);
                // monitorexit(q.t)
                return q;
            }
            // monitorexit(q.t)
            return ji.io.q.t.get(s);
        }
    }
    
    protected q(final Object o, final String s) {
        this.a = null;
        this.b = null;
        this.e = null;
        this.g = true;
        this.h = false;
        this.i = null;
        this.j = null;
        this.k = false;
        this.l = false;
        this.n = null;
        this.r = '\0';
        this.s = null;
        this.v = null;
        this.s = s;
        if (ji.util.d.ei()) {
            this.a = ji.res.s.c("viewone .NET");
        }
        else {
            this.a = ji.res.s.c("viewone");
        }
        this.b = "cache";
        this.e = "";
        if (w()) {
            ji.io.h.d(s, "CacheTestInit1 : ".concat(String.valueOf(String.valueOf(this.e))));
        }
        try {
            this.h = this.a(o);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            this.h = false;
        }
        if (this.h) {
            try {
                if (!ji.util.d.by(this.i) && this.g(s)) {
                    this.x();
                }
            }
            catch (Exception ex2) {
                c(s, "CacheB = ".concat(String.valueOf(String.valueOf(this.i))));
            }
        }
        if (!this.h) {
            v();
            ji.util.d.br(false);
        }
        if (ji.util.d.dr() || ji.io.q.f) {
            ji.io.h.d(s, "Cache = ".concat(String.valueOf(String.valueOf(this.i))));
        }
        c(s, "CacheB = ".concat(String.valueOf(String.valueOf(ji.util.d.dn()))));
        c(s, "CacheC = ".concat(String.valueOf(String.valueOf(ji.util.d.dx()))));
        ji.util.d.o(false);
    }
    
    private boolean g(final String s) {
        return ji.util.i.c(231);
    }
    
    private static final boolean t() {
        return i.c(69);
    }
    
    private static final boolean u() {
        return i.c(106);
    }
    
    private static void v() {
        i.a(52);
    }
    
    private static boolean w() {
        return i.c(83) || ji.io.q.f;
    }
    
    private boolean h(final String s) {
        return ac.d(s) || b8.a(s);
    }
    
    private void x() {
        c(this.s, "Pro: CacheCLC");
        try {
            final File file = new File(this.i);
            c(this.s, "Pro: cachePathA: ".concat(String.valueOf(String.valueOf(this.i))));
            final File file2 = new File(file.getAbsolutePath());
            c(this.s, "Pro: cachePathB: ".concat(String.valueOf(String.valueOf(file2.getAbsolutePath()))));
            this.j = file2.getParent();
            if (this.j == null && file2.exists()) {
                c(this.s, "Pro: cachePathC: exists");
                this.j = ji.util.d.i(this.i, this.s);
            }
            c(this.s, "Pro: cacheRoot: ".concat(String.valueOf(String.valueOf(this.j))));
            boolean b = false;
            boolean b2 = false;
            if (ji.util.d.b()) {
                b = true;
                this.j();
            }
            else if (b(this.i, this.s)) {
                c(this.s, "Pro: CacheCLC: #1");
                if (d(this.i, this.s)) {
                    if (w()) {
                        ji.io.h.d(this.s, "Pro: CacheCLC: #1a");
                    }
                    b = false;
                }
                else {
                    if (w()) {
                        ji.io.h.d(this.s, "Pro: CacheCLC: #1b");
                    }
                    b = true;
                    b2 = true;
                }
            }
            else {
                if (w()) {
                    ji.io.h.d(this.s, "Pro: CacheCLC: #2");
                }
                b2 = true;
            }
            if (b) {
                if (w()) {
                    ji.io.h.d(this.s, "Pro: CacheCLC: #3");
                }
                if (this.i.endsWith("/") || this.i.endsWith("\\")) {
                    this.i = this.i.substring(0, this.i.length() - 1);
                }
                if (ji.util.d.b()) {
                    this.i(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.i))).append(File.separator).append(ji.util.d.c1(this.s)).append("-").append(this.s).append(".").append("tmp"))));
                }
                else {
                    this.i(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.i))).append(File.separator).append(ji.util.d.c1(this.s)).append(".").append("tmp"))));
                }
            }
            try {
                ac.e(this.i, this.s);
            }
            catch (Exception ex2) {}
            if (b2) {
                this.j("Pro: CacheCLC: #4");
                this.k(this.i);
                this.v = a(this.i, this.s, ji.util.d.c1(this.s));
            }
            else {
                this.v = a(this.i, this.s);
            }
            c(this.s, "CacheCLC: #5 = ".concat(String.valueOf(String.valueOf(this.i))));
        }
        catch (Exception ex) {
            ex.printStackTrace();
            c(this.s, "CacheCLC: #5a = ".concat(String.valueOf(String.valueOf(this.i))));
        }
    }
    
    private void i(final String i) {
        this.i = i;
    }
    
    private void j(final String s) {
        if (w()) {
            ji.io.h.d(this.s, s);
        }
    }
    
    private static void c(final String s, final String s2) {
        if (w()) {
            h.d(s, s2);
        }
    }
    
    public static final String a() {
        if ("tmp" == null) {
            return "tmp";
        }
        return "tmp";
    }
    
    public static final String b() {
        return "ex";
    }
    
    public static final void c() {
    }
    
    public static final String d() {
        return ji.io.q.u;
    }
    
    public static final void e() {
        ji.io.q.u = null;
    }
    
    public final String f() {
        return this.i;
    }
    
    public final String g() {
        return "xe";
    }
    
    private final boolean y() {
        return true;
    }
    
    public final String h() {
        return this.i;
    }
    
    public final String i() {
        return this.j;
    }
    
    private final void k(final String s) {
        if (!this.h || !ji.util.d.dn()) {
            return;
        }
        if (this.h && s != null) {
            try {
                this.a(s, ac.h(s, this.s));
            }
            catch (Exception ex) {}
        }
    }
    
    public final void j() {
        this.k(this.i);
    }
    
    public static final aw a(final String s, final String s2, final String s3) throws Exception {
        if (w()) {
            h.d(s2, "Pro: CacheCLC: ".concat(String.valueOf(String.valueOf(s))));
        }
        final aw aw = new aw(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s))).append(File.separator).append(".lock"))), s2);
        aw.write(s3.getBytes("UTF8"));
        aw.flush();
        return aw;
    }
    
    public static final void a(final String s, final String s2, final OutputStream outputStream) {
        if (w()) {
            h.d(s2, "Pro: CacheCLC3: ".concat(String.valueOf(String.valueOf(s))));
        }
        synchronized (ji.io.q.t) {
            try {
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    }
                    catch (Exception ex) {}
                }
                try {
                    if (!a(s, s2, true) && s != null && ac.f(s, s2) && s.endsWith("tmp")) {
                        ac.c(s, s2);
                    }
                }
                catch (Exception ex2) {}
            }
            finally {
                ji.io.q.t.remove(s2);
            }
        }
        // monitorexit(q.t)
    }
    
    public static final aw a(final String s, final String s2) throws Exception {
        if (w()) {
            h.d(s2, "Pro: CacheCLC2: ".concat(String.valueOf(String.valueOf(s))));
        }
        return new aw(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s))).append(File.separator).append(".lock"))), true, s2);
    }
    
    private static boolean d(final String s, final String s2) throws Exception {
        final az az = new az(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s))).append(File.separator).append(".lock"))), s2);
        final byte[] array = new byte[20];
        final int read = az.read(array);
        if (read > -1) {
            final String s3 = new String(array, 0, read, "UTF8");
            final String c1 = d.c1(s2);
            if (c1 != null && c1.equals(s3)) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean b(final String s, final String s2) throws Exception {
        return a(s, s2, false);
    }
    
    public static boolean a(final String s, final String s2, final boolean b) throws Exception {
        final String value = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s))).append(File.separator).append(".lock")));
        synchronized (ji.io.q.t) {
            final Enumeration<String> keys = (Enumeration<String>)ji.io.q.t.keys();
            while (keys.hasMoreElements()) {
                final String s3 = keys.nextElement();
                if (b && s3 != null && s3.equals(s2)) {
                    continue;
                }
                final q q = ji.io.q.t.get(s3);
                if (q != null && new File(q.h()).equals(new File(s))) {
                    // monitorexit(q.t)
                    return true;
                }
            }
            final u u = new u(value, s2, false);
            if (u.c() && !u.k()) {
                // monitorexit(q.t)
                return true;
            }
            // monitorexit(q.t)
            return false;
        }
    }
    
    private final void a(final String s, final String[] array) {
        this.j("Emptying cache ".concat(String.valueOf(String.valueOf(s))));
        if (array != null) {
            for (int i = 0; i < array.length; ++i) {
                try {
                    if (array[i].startsWith("x") || array[i].startsWith("xe") || array[i].endsWith("tmp")) {
                        final String b = b(s, array[i], this.s);
                        if (this.g(this.s)) {
                            if (ac.f(b, this.s)) {
                                try {
                                    if (!b(b, this.s)) {
                                        final String[] h = ac.h(b, this.s);
                                        if (h != null) {
                                            this.j("Emptying cache subdir ".concat(String.valueOf(String.valueOf(b))));
                                            this.a(b, h);
                                        }
                                        this.l(b);
                                    }
                                }
                                catch (Exception ex) {
                                    ji.util.d.a(ex);
                                }
                            }
                            else if (!b(s, this.s)) {
                                this.l(b);
                            }
                        }
                        else {
                            this.l(b);
                        }
                    }
                }
                catch (Exception ex2) {
                    ji.util.d.a(ex2);
                }
            }
        }
    }
    
    private final void l(final String s) throws Exception {
        if (t()) {
            if (!this.h(s)) {
                ac.a(s, this.s, false, false);
            }
        }
        else {
            ac.a(s, this.s, false, false);
        }
    }
    
    private final boolean a(final Object o) {
        boolean b = false;
        boolean b2 = false;
        this.n = null;
        boolean b3 = true;
        if (w()) {
            ji.io.h.d(this.s, "CacheTest2 : ".concat(String.valueOf(String.valueOf(b3))));
        }
        if (b3) {
            if (ji.util.e.u(this.s) && !ji.util.d.az(this.s) && u()) {
                try {
                    if (w()) {
                        ji.io.h.d(this.s, "CacheTestWin1a");
                    }
                    this.i(ji.util.d.c(o, this.s));
                    if (w()) {
                        ji.io.h.d(this.s, "CacheTestWin1b ".concat(String.valueOf(String.valueOf(this.i))));
                    }
                    if (this.i == null) {
                        if (w()) {
                            ji.io.h.d(this.s, "CacheTestWin1");
                        }
                        String e = ji.res.s.e();
                        if (ji.util.d.by(e)) {
                            e = "viewone";
                        }
                        this.i(ji.util.d.a(this.s, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(e))).append("/").append("cache")))));
                        if (w()) {
                            ji.io.h.d(this.s, "CacheTestWin2 ".concat(String.valueOf(String.valueOf(this.i))));
                        }
                        if (this.i != null) {
                            try {
                                ac.e(ji.util.d.b(this.s), this.s);
                                ac.e(this.i, this.s);
                            }
                            catch (Exception ex) {
                                ji.util.d.a(ex);
                            }
                            final String a = this.a(true, "tst");
                            final ac ac = new ac(a, true, false, 0, o, this.s);
                            try {
                                ac.a(o);
                                ji.io.ac.a(a, this.s, false, false);
                            }
                            catch (Exception ex2) {
                                ji.util.d.a(ex2);
                            }
                            b2 = true;
                        }
                    }
                }
                catch (Exception ex3) {
                    this.i(null);
                    if (w()) {
                        ji.io.h.d(this.s, "CacheTestWin3 ".concat(String.valueOf(String.valueOf(ex3))));
                    }
                }
            }
            if (!b2) {
                b3 = false;
                SecurityManager securityManager = null;
                if (ji.util.d.dn() && ji.util.d.eg()) {
                    securityManager = System.getSecurityManager();
                }
                try {
                    if (w()) {
                        ji.io.h.d(this.s, "CacheTest3 : ".concat(String.valueOf(String.valueOf(securityManager))));
                    }
                    if (securityManager != null) {
                        Object a2 = null;
                        try {
                            a2 = ji.sec.f.a("user.home", this.s);
                        }
                        catch (Exception ex4) {
                            ji.util.d.a(ex4);
                        }
                        if (w()) {
                            ji.io.h.d(this.s, "CacheTest4 : ".concat(String.valueOf(String.valueOf(a2))));
                        }
                        if (a2 == null) {
                            try {
                                securityManager.checkPropertyAccess("user.home");
                            }
                            catch (Exception ex5) {
                                if (w()) {
                                    ji.io.h.d(this.s, "CacheTest5 : ".concat(String.valueOf(String.valueOf(ex5))));
                                }
                                b3 = true;
                            }
                        }
                        else {
                            b3 = true;
                        }
                    }
                    else {
                        b3 = true;
                    }
                }
                catch (Exception ex6) {
                    if (w()) {
                        ji.io.h.d(this.s, "CacheTest6 : ".concat(String.valueOf(String.valueOf(ex6))));
                    }
                    ji.util.d.b(ex6);
                    this.n = ex6.getMessage();
                    ji.util.d.br(false);
                }
                if (w()) {
                    ji.io.h.d(this.s, "CacheTest7 : ".concat(String.valueOf(String.valueOf(ji.util.d.dx()))));
                }
                if (!ji.util.d.dx()) {
                    this.i("");
                    return true;
                }
                if (w()) {
                    ji.io.h.d(this.s, "CacheTest8 : ".concat(String.valueOf(String.valueOf(b3))));
                }
                if (b3 && ji.util.d.dx()) {
                    b3 = false;
                    try {
                        this.i(ji.util.d.c(o, this.s));
                        if (w()) {
                            ji.io.h.d(this.s, "Cache1 : ".concat(String.valueOf(String.valueOf(this.i))));
                        }
                        Label_0960: {
                            if (this.i == null) {
                                if (ji.util.d.eg()) {
                                    try {
                                        this.i(ji.sec.f.a("user.home", this.s));
                                        break Label_0960;
                                    }
                                    catch (Exception ex7) {
                                        ex7.printStackTrace();
                                        if (w()) {
                                            ji.io.h.d(this.s, "CacheTest8A : ".concat(String.valueOf(String.valueOf(ex7))));
                                        }
                                        if (!this.l) {
                                            this.l = true;
                                            if (!ji.util.d.q) {
                                                boolean b4 = false;
                                                ji.util.d.q = true;
                                                if (!ji.util.d.ei()) {
                                                    if (ex7 instanceof SecurityException) {
                                                        b4 = true;
                                                    }
                                                    String s;
                                                    if (!b4) {
                                                        s = "There appears to be a problem with your Java installation.\nPlease re-install Java by visiting http://www.java.com\n(You are probably using a version of Java with known problems and that have since been resolved with later versions of Java)";
                                                    }
                                                    else {
                                                        s = "SECERR: The viewer cannot run if the security certificate has been cancelled or denied.\nPlease try again and accept the security certficate.";
                                                    }
                                                    ji.util.e.o(false);
                                                    ji.io.h.d(this.s, s);
                                                    if (ji.util.d.ei()) {
                                                        ji.util.d.a(ji.res.s.c("ViewONE .NET"), ji.res.s.c(s), (af)null, this.s);
                                                    }
                                                    else {
                                                        ji.util.d.a(ji.res.s.c("ViewONE"), ji.res.s.c(s), (af)null, this.s);
                                                    }
                                                }
                                            }
                                        }
                                        return false;
                                    }
                                }
                                try {
                                    this.i(ji.sec.f.a("user.dir", this.s));
                                }
                                catch (Exception ex8) {
                                    if (w()) {
                                        ji.io.h.d(this.s, "CacheTest9 : ".concat(String.valueOf(String.valueOf(ex8))));
                                    }
                                    this.i(null);
                                }
                                if (this.i == null) {
                                    this.i(ji.sec.f.a("user.home", this.s));
                                }
                            }
                        }
                        if (this.i == null) {
                            this.i("");
                        }
                        if (w()) {
                            ji.io.h.d(this.s, "CacheTest10 : ".concat(String.valueOf(String.valueOf(this.i))));
                        }
                        if (this.i != null) {
                            this.i(this.m(this.i));
                        }
                        if (w()) {
                            ji.io.h.d(this.s, "CacheTest10A : ".concat(String.valueOf(String.valueOf(this.i))));
                        }
                        if (this.i != null) {
                            try {
                                if (!ac.d(this.i, this.s)) {
                                    ac.e(this.i, this.s);
                                }
                            }
                            catch (Exception ex9) {
                                if (w()) {
                                    ji.io.h.d(this.s, "CacheTest10B : ".concat(String.valueOf(String.valueOf(ex9))));
                                }
                            }
                            String e2 = null;
                            try {
                                final String concat = String.valueOf(String.valueOf(ji.util.d.bu(this.s))).concat(String.valueOf(String.valueOf(b(this.a, this.b, this.s))));
                                c(this.s, "CacheTest10C : ".concat(String.valueOf(String.valueOf(concat))));
                                boolean b5 = true;
                                if (!ji.util.d.by(this.i) && !ji.util.d.by(concat)) {
                                    if (this.i.toLowerCase().endsWith(concat.toLowerCase())) {
                                        b5 = false;
                                    }
                                    this.j("CacheTest10F : ".concat(String.valueOf(String.valueOf(b5))));
                                }
                                if (b5) {
                                    e2 = b(this.i, this.a, this.s);
                                    this.e = e2;
                                    if (w()) {
                                        ji.io.h.d(this.s, "CacheTest2 : ".concat(String.valueOf(String.valueOf(e2))));
                                    }
                                    if (!ac.d(e2, this.s)) {
                                        ac.e(e2, this.s);
                                    }
                                    if (ji.util.d.cg(this.s)) {
                                        e2 = b(e2, this.b, this.s);
                                    }
                                    else {
                                        e2 = String.valueOf(String.valueOf(e2)).concat(String.valueOf(String.valueOf(this.b)));
                                    }
                                    if (w()) {
                                        ji.io.h.d(this.s, "CacheTest3 : ".concat(String.valueOf(String.valueOf(e2))));
                                    }
                                    if (!ac.d(e2, this.s)) {
                                        ac.e(e2, this.s);
                                    }
                                    this.i(e2);
                                }
                                b3 = true;
                                if (w()) {
                                    ji.io.h.d(this.s, "CacheTest11");
                                }
                            }
                            catch (Exception ex10) {
                                ji.util.d.b(ex10);
                                this.n = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(ex10.getMessage()))).append(" (").append(e2).append(")")));
                            }
                        }
                        else {
                            this.n = "System property \"user.home\" is undefined";
                        }
                    }
                    catch (Exception ex11) {
                        ji.util.d.b(ex11);
                        ji.util.d.br(false);
                        return true;
                    }
                }
                if (!b3) {
                    ji.util.d.br(false);
                }
                if (b3 && !ji.util.d.az(this.s)) {
                    if (w()) {
                        ji.io.h.d(this.s, "CacheTest12");
                    }
                    String s2 = this.a(true, "tstc");
                    if (w()) {
                        ji.io.h.d(this.s, "CacheTest4 : ".concat(String.valueOf(String.valueOf(s2))));
                    }
                    ac ac2 = null;
                    Label_2128: {
                        if (!ji.util.d.az(this.s)) {
                            try {
                                if (w()) {
                                    ji.io.h.d(this.s, "CacheTest13");
                                }
                                ac2 = new ac(s2, true, false, 0, o, this.s);
                                try {
                                    ac2.a(o);
                                }
                                catch (Exception ex12) {
                                    ji.util.d.a(ex12);
                                }
                            }
                            catch (Exception ex17) {
                                if (w()) {
                                    ji.io.h.d(this.s, "CacheTest14");
                                }
                                int n = 1;
                                final String i = this.i;
                                if (ji.util.e.u(this.s)) {
                                    try {
                                        if (w()) {
                                            ji.io.h.d(this.s, "CacheTest15");
                                        }
                                        final o o2 = new o(this.s);
                                        this.i(o2.b("viewone/cache"));
                                        if (w()) {
                                            ji.io.h.d(this.s, "CacheTest15a ".concat(String.valueOf(String.valueOf(this.i))));
                                        }
                                        if (this.i != null) {
                                            try {
                                                ac.e(o2.b(), this.s);
                                                ac.e(this.i, this.s);
                                            }
                                            catch (Exception ex13) {
                                                ji.util.d.a(ex13);
                                            }
                                            s2 = this.n();
                                            ac2 = new ac(s2, true, false, 0, o, this.s);
                                            try {
                                                ac2.a(o);
                                            }
                                            catch (Exception ex14) {
                                                ji.util.d.a(ex14);
                                            }
                                            n = 0;
                                        }
                                        if (n != 0) {
                                            this.i("c:/viewONE/cache");
                                            try {
                                                ac.e("c:/viewONE", this.s);
                                                ac.e(this.i, this.s);
                                            }
                                            catch (Exception ex15) {
                                                ji.util.d.a(ex15);
                                            }
                                            s2 = this.n();
                                            ac2 = new ac(s2, true, false, 0, o, this.s);
                                            try {
                                                ac2.a(o);
                                            }
                                            catch (Exception ex16) {
                                                ji.util.d.a(ex16);
                                            }
                                            n = 0;
                                        }
                                    }
                                    catch (Exception ex30) {
                                        if (w()) {
                                            ji.io.h.d(this.s, "CacheTest16");
                                        }
                                        final String b6 = ji.util.d.b(i, "\\", "/");
                                        String c1 = ji.util.d.c1();
                                        if (ji.util.d.by(c1)) {
                                            c1 = "<Empty>";
                                        }
                                        ji.util.e.o(false);
                                        ji.io.q.u = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(b6))).append("' or '").append(this.i).append(" originally defined as '").append(c1).append("'")));
                                        if (w()) {
                                            ji.io.h.d(this.s, "CacheTest16b");
                                        }
                                        n = 1;
                                    }
                                }
                                if (n != 0) {
                                    ji.util.d.b(ex17);
                                    try {
                                        ac2.a(o);
                                    }
                                    catch (Exception ex18) {
                                        ji.util.d.a(ex18);
                                    }
                                    try {
                                        if (securityManager != null) {
                                            securityManager.checkWrite(s2);
                                            break Label_2128;
                                        }
                                        this.n = ex17.getMessage();
                                    }
                                    catch (Exception ex19) {
                                        ji.util.d.b(ex19);
                                        this.n = ex19.getMessage();
                                    }
                                }
                            }
                        }
                    }
                    if (w()) {
                        ji.io.h.d(this.s, "CacheTest17:".concat(String.valueOf(String.valueOf(s2))));
                    }
                    Label_2286: {
                        if (!ji.util.d.az(this.s)) {
                            try {
                                try {
                                    ac2.a(o);
                                }
                                catch (Exception ex20) {
                                    ji.util.d.a(ex20);
                                }
                                ac2 = new ac(s2, false, false, 0, o, this.s);
                                try {
                                    ac2.a(o);
                                }
                                catch (Exception ex21) {
                                    ji.util.d.a(ex21);
                                }
                            }
                            catch (Exception ex22) {
                                if (w()) {
                                    ji.io.h.d(this.s, "CacheTest17a");
                                }
                                ji.util.d.b(ex22);
                                try {
                                    b = true;
                                    if (securityManager != null) {
                                        securityManager.checkRead(s2);
                                        break Label_2286;
                                    }
                                    this.n = ex22.getMessage();
                                }
                                catch (Exception ex23) {
                                    ji.util.d.b(ex23);
                                    this.n = ex22.getMessage();
                                }
                            }
                        }
                    }
                    Label_2301: {
                        if (!w()) {
                            break Label_2301;
                        }
                        ji.io.h.d(this.s, "CacheTest18");
                        try {
                            try {
                                if (ac2 != null) {
                                    ac2.a(o);
                                }
                            }
                            catch (Exception ex24) {
                                ji.util.d.a(ex24);
                            }
                            if (!ji.util.d.az(this.s)) {
                                ac.a(s2, this.s, false, false);
                            }
                        }
                        catch (Exception ex25) {
                            ji.util.d.b(ex25);
                            try {
                                if (ac2 != null) {
                                    ac2.a(o);
                                }
                            }
                            catch (Exception ex26) {
                                ji.util.d.a(ex26);
                            }
                            try {
                                if (securityManager != null) {
                                    securityManager.checkDelete(s2);
                                    break Label_2301;
                                }
                                this.n = ex25.getMessage();
                            }
                            catch (Exception ex27) {
                                ji.util.d.b(ex27);
                                this.n = ex27.getMessage();
                            }
                        }
                    }
                    if (w()) {
                        ji.io.h.d(this.s, "CacheTest19");
                    }
                    b3 = !b;
                    if (ac2 != null) {
                        try {
                            ac2.a(o);
                        }
                        catch (Exception ex28) {
                            ji.util.d.a(ex28);
                        }
                        try {
                            ac.a(s2, this.s, false, false);
                        }
                        catch (Exception ex29) {
                            ji.util.d.a(ex29);
                        }
                    }
                }
            }
        }
        if (w()) {
            ji.io.h.d(this.s, "CacheTest20");
        }
        if (!ji.util.i.c(69)) {
            this.y();
        }
        if (w()) {
            ji.io.h.d(this.s, "CacheTest21:".concat(String.valueOf(String.valueOf(this.i))));
        }
        ji.util.d.a(this.i, o, this.s);
        if (w()) {
            ji.io.h.d(this.s, "CacheTest22:".concat(String.valueOf(String.valueOf(ji.util.d.c(o, this.s)))));
        }
        return b3;
    }
    
    final String k() {
        if (this.g(this.s) && !ji.util.d.by(this.j)) {
            return String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.j))).append("/lf.").append("v1")));
        }
        if (!ji.util.d.by(this.e)) {
            return String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.e))).append("/lf.").append("v1")));
        }
        return String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.i))).append("/../lf.").append("v1")));
    }
    
    private final String z() {
        if (this.g(this.s) && !ji.util.d.by(this.j)) {
            return String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.j))).append("/p.").append("v1")));
        }
        if (!ji.util.d.by(this.e)) {
            return String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.e))).append("/p.").append("v1")));
        }
        return String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.i))).append("/../p.").append("v1")));
    }
    
    public final String l() {
        if (this.g(this.s) && !ji.util.d.by(this.j)) {
            return String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.j))).append("/lb.").append("v1")));
        }
        if (!ji.util.d.by(this.e)) {
            return String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.e))).append("/lb.").append("v1")));
        }
        return String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.i))).append("/../lb.").append("v1")));
    }
    
    public final void a(final String s) throws Exception {
        if (this.i != null && ji.util.d.dx()) {
            final au au = new au(this.z(), "rw", this.s, this.g);
            au.write(s.getBytes());
            au.e();
        }
    }
    
    public final String m() throws Exception {
        String s = null;
        if (this.i != null && ji.util.d.dn()) {
            try {
                if (new u(this.z(), this.s).c()) {
                    final au au = new au(this.z(), "r", this.s, this.g);
                    final byte[] array = new byte[(int)au.d()];
                    au.a(array);
                    s = new String(array);
                    au.e();
                }
            }
            catch (Exception ex) {
                ji.util.d.a(ex);
            }
        }
        return s;
    }
    
    public final String n() {
        return this.a(true);
    }
    
    public final String a(final boolean b) {
        return this.a(b, null);
    }
    
    public final String a(final boolean b, final String s) {
        String s2;
        for (s2 = this.a(b, false, 0L, s); ac.d(s2, this.s); s2 = this.a(b, false, 0L, s)) {}
        return s2;
    }
    
    public final String b(final String s) {
        String s2;
        for (s2 = this.a(false, false, 0L, s); ac.d(s2, this.s); s2 = this.a(false, false, 0L, s)) {}
        return s2;
    }
    
    public final String o() {
        return this.b(null);
    }
    
    public final String a(final long n) {
        if (n != 0) {
            return this.a(false, false, n, null);
        }
        return this.o();
    }
    
    public final String p() {
        String s;
        for (s = this.ab(); ac.d(s, this.s); s = this.ab()) {}
        return s;
    }
    
    public final String c(final String s) {
        return this.a(s, false);
    }
    
    public final String a(final String s, final boolean b) {
        return this.a(s, b, 0L, null);
    }
    
    public final String a(final String s, final boolean b, final long n, final String s2) {
        String s3 = this.m(this.b(s, b, n, s2));
        if (n == 0) {
            while (ac.d(s3, this.s)) {
                s3 = this.m(this.b(s, b, n, s2));
            }
        }
        return s3;
    }
    
    public final String a(final String s, final long n, final String s2) {
        String s3 = this.m(this.a(s, false, false, n, s2));
        if (n == 0) {
            while (ac.d(s3, this.s)) {
                s3 = this.m(this.a(s, false, false, n, s2));
            }
        }
        return s3;
    }
    
    public final byte[] a(final String s, final Object o) {
        byte[] array = null;
        try {
            if (this.e(s)) {
                final ac ac = new ac(s, false, false, 0, o, this.s);
                array = new byte[(int)ac.w()];
                ac.a(array);
                ac.a(o);
            }
            else {
                this.d(s);
            }
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
        }
        return array;
    }
    
    public final int[] b(final String s, final Object o) {
        int[] array = null;
        try {
            if (this.e(s)) {
                final ac ac = new ac(s, false, false, 0, o, this.s);
                array = new int[(int)ac.w()];
                ac.a(array);
                ac.a(o);
            }
            else {
                this.d(s);
            }
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
        }
        return array;
    }
    
    public final boolean d(final String s) {
        return this.b(s, false);
    }
    
    public final boolean b(final String s, final boolean b) {
        final boolean b2 = false;
        if (!this.h) {
            return b2;
        }
        if (s == null) {
            return false;
        }
        boolean b3;
        try {
            if (!b) {
                c(this.s, "Attempting to remove ".concat(String.valueOf(String.valueOf(s))));
                if (ac.d(s, this.s)) {
                    c(this.s, "Removing ".concat(String.valueOf(String.valueOf(s))));
                    c(this.s, String.valueOf(String.valueOf(new StringBuffer("Removing ").append(s).append(" result: ").append(ac.a(s, this.s, false, false)))));
                }
                else {
                    c(this.s, "Not removing file, doesn't exist");
                }
            }
            b3 = true;
        }
        catch (Exception ex) {
            ji.util.d.b(ex);
            b3 = false;
        }
        return b3;
    }
    
    public long c(final String s, final Object o) {
        long w = 0L;
        ac ac = null;
        try {
            if (ji.util.d.dx()) {
                ac = new ac(s, false, false, 0, o, this.s);
                w = ac.w();
            }
        }
        catch (Exception ex) {
            ji.util.d.b(ex);
            w = 0L;
        }
        finally {
            if (ac != null) {
                try {
                    ac.a(o);
                }
                catch (Exception ex2) {
                    ji.util.d.a(ex2);
                }
            }
        }
        return w;
    }
    
    public boolean e(final String s) {
        boolean d;
        try {
            d = ac.d(s, this.s);
        }
        catch (Exception ex) {
            ji.util.d.b(ex);
            d = false;
        }
        return d;
    }
    
    private final String a(final boolean b, final boolean b2, final long n, final String s) {
        return this.a("tmp", b, b2, n, s);
    }
    
    public String a(final String s, final long n) {
        return this.a(String.valueOf(String.valueOf(new StringBuffer("tmp.").append(s))), false, false, n, null);
    }
    
    private static synchronized int aa() {
        return ji.io.q.o++;
    }
    
    private final String a(final String s, final boolean b, final boolean b2, final long n, final String s2) {
        String s3 = null;
        try {
            String s4;
            String s5;
            if (n != 0) {
                s4 = "".concat(String.valueOf(String.valueOf(n)));
                s5 = "";
            }
            else {
                s4 = Integer.toHexString(aa());
                s5 = Integer.toHexString((int)(System.currentTimeMillis() % 256));
                if (s4.length() < 3) {
                    s4 = String.valueOf(String.valueOf(ji.io.q.p.substring(0, 3 - s4.length()))).concat(String.valueOf(String.valueOf(s4)));
                }
                if (s5.length() < 3) {
                    s5 = String.valueOf(String.valueOf(ji.io.q.p.substring(0, 3 - s5.length()))).concat(String.valueOf(String.valueOf(s5)));
                }
            }
            if (s2 != null) {
                s4 = String.valueOf(String.valueOf(s4)).concat(String.valueOf(String.valueOf(s2)));
            }
            if ((ji.util.d.c2() || b2) && b) {
                s3 = String.valueOf(String.valueOf(new StringBuffer("xe").append(s5).append(s4).append(".").append(s)));
            }
            else {
                s3 = String.valueOf(String.valueOf(new StringBuffer("x").append(s5).append(s4).append(".").append(s)));
            }
            if (this.i != null) {
                s3 = b(this.i, s3, this.s);
            }
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
        }
        return this.m(s3);
    }
    
    private final String ab() {
        String s = null;
        try {
            final String b = b(this.i, "v1debug", this.s);
            s = String.valueOf(String.valueOf(new StringBuffer("debug").append(ji.io.q.q++).append(".txt")));
            try {
                if (!ac.d(b, this.s)) {
                    ac.e(b, this.s);
                }
                s = b(b, s, this.s);
            }
            catch (Exception ex2) {
                if (this.j != null) {
                    s = b(this.j, s, this.s);
                }
            }
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
        }
        return this.m(s);
    }
    
    private final String b(final String s, final boolean b, final long n, final String s2) {
        String s3;
        String s4;
        if (n != 0) {
            s3 = "".concat(String.valueOf(String.valueOf(n)));
            s4 = "";
        }
        else {
            s3 = Integer.toHexString(aa());
            s4 = Integer.toHexString((int)(System.currentTimeMillis() % 256));
            if (s3.length() < 3) {
                s3 = String.valueOf(String.valueOf(ji.io.q.p.substring(0, 3 - s3.length()))).concat(String.valueOf(String.valueOf(s3)));
            }
            if (s4.length() < 3) {
                s4 = String.valueOf(String.valueOf(ji.io.q.p.substring(0, 3 - s4.length()))).concat(String.valueOf(String.valueOf(s4)));
            }
        }
        if (s2 != null) {
            s3 = String.valueOf(String.valueOf(s3)).concat(String.valueOf(String.valueOf(s2)));
        }
        String s5;
        if (ji.util.d.c2() || b) {
            s5 = String.valueOf(String.valueOf(new StringBuffer("xe").append(s4).append(s3).append(".").append(s)));
        }
        else {
            s5 = String.valueOf(String.valueOf(new StringBuffer("x").append(s4).append(s3).append(".").append(s)));
        }
        if (this.i != null) {
            s5 = b(this.i, s5, this.s);
        }
        return this.m(s5);
    }
    
    public String f(final String s) {
        final String k = ji.util.d.k(s, this.s);
        return ji.util.d.b(ji.util.d.b(ji.util.d.b(k.substring(0, k.length() - 1), "xe", ""), "x", ""), "ex", "");
    }
    
    private final String m(final String s) {
        String s3;
        final String s2 = s3 = ji.util.d.b(ji.util.d.b(s, "http://", ""), "https://", "");
        if (this.r == '\0') {
            this.r = ji.util.d.bu(this.s).toCharArray()[0];
        }
        boolean b = true;
        final int length = s2.length();
        for (int i = 0; i < length; ++i) {
            if (s2.charAt(i) == '*' || s2.charAt(i) == '?' || s2.charAt(i) == '\"' || s2.charAt(i) == '<' || s2.charAt(i) == '>') {
                b = false;
                break;
            }
        }
        if (!b) {
            s3 = "";
            for (int j = 0; j < length; ++j) {
                if (s2.charAt(j) != '*' && s2.charAt(j) != '?' && s2.charAt(j) != '\"' && s2.charAt(j) != '<' && s2.charAt(j) != '>') {
                    s3 = String.valueOf(String.valueOf(s3)).concat(String.valueOf(String.valueOf(s2.charAt(j))));
                }
            }
        }
        return s3;
    }
    
    public static final String b(final String s, final String s2, final String s3) {
        if (s != null) {
            String s4;
            try {
                final String bu = d.bu(s3);
                if (bu != null) {
                    final char char1 = bu.charAt(bu.length() - 1);
                    if (s.length() > 0) {
                        if (s.charAt(s.length() - 1) != char1) {
                            s4 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s))).append(char1).append(s2)));
                        }
                        else {
                            s4 = String.valueOf(String.valueOf(s)).concat(String.valueOf(String.valueOf(s2)));
                        }
                    }
                    else {
                        s4 = String.valueOf(String.valueOf(s)).concat(String.valueOf(String.valueOf(s2)));
                    }
                }
                else {
                    s4 = String.valueOf(String.valueOf(s)).concat(String.valueOf(String.valueOf(s2)));
                }
            }
            catch (Exception ex) {
                d.b(ex);
                s4 = String.valueOf(String.valueOf(s)).concat(String.valueOf(String.valueOf(s2)));
            }
            return s4;
        }
        return s2;
    }
    
    public static void q() {
        if (ji.io.q.t != null) {
            ji.io.q.t.clear();
        }
    }
    
    public void r() {
        if (!ji.util.i.c(231)) {
            return;
        }
        a(this.i, this.s, this.v);
    }
    
    public boolean s() {
        return this.g;
    }
    
    static {
        ji.io.q.f = false;
        ji.io.q.m = true;
        ji.io.q.o = 1;
        ji.io.q.p = "0000";
        ji.io.q.q = 1;
        ji.io.q.t = new Hashtable();
        ji.io.q.u = null;
    }
}
