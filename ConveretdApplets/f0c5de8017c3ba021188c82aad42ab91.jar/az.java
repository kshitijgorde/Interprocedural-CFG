import java.util.Enumeration;
import java.util.Locale;
import java.util.Date;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class az implements a0
{
    public at a;
    public Hashtable b;
    public Hashtable c;
    public b d;
    public Date e;
    public bm f;
    public a7 g;
    public a1 h;
    private cb i;
    public Hashtable j;
    public boolean k;
    private int l;
    public String m;
    public static Hashtable n;
    public long o;
    private boolean p;
    private Hashtable q;
    private Integer r;
    public x s;
    private x t;
    public Long u;
    public static int v;
    private Object w;
    
    public static aw b(final Exception ex, final String s) {
        if (a0.a.f()) {
            a0.a.a(s, ex);
        }
        if (ex instanceof aw) {
            return (aw)ex;
        }
        return new aw(s, ex);
    }
    
    public static int ah() {
        return az.n.size();
    }
    
    public az() {
        this.b = new Hashtable();
        this.c = new Hashtable();
        this.h = new a1();
        this.j = new Hashtable();
        this.k = false;
        this.l = 0;
        this.o = 0L;
        this.p = false;
        this.u = new Long(Long.MIN_VALUE);
        this.w = new Object();
    }
    
    public az(final at a, final x t, final b d) throws aw {
        this.b = new Hashtable();
        this.c = new Hashtable();
        this.h = new a1();
        this.j = new Hashtable();
        this.k = false;
        this.l = 0;
        this.o = 0L;
        this.p = false;
        this.u = new Long(Long.MIN_VALUE);
        this.w = new Object();
        this.a = a;
        this.t = t;
        this.d = d;
        this.j.put("JMDG-Version", "2.1.12");
        if (t != null) {
            a2 a2 = null;
            if (!a.c("NO_XID_REQUEST")) {
                a2 = a2.a(this, t);
                if (a0.a.h()) {
                    a0.a.f(this.as() + " init got XidObject " + a2);
                }
                final int r = a2.r();
                if (r != 0) {
                    int n;
                    if (r < 0) {
                        if (r == -6) {
                            n = 1;
                        }
                        else if (r == -7) {
                            n = 2;
                        }
                        else {
                            final am u = a2.u();
                            if (u != null && (u instanceof dl || u instanceof dn || u instanceof al || u instanceof dp)) {
                                n = 3;
                            }
                            else {
                                n = 4;
                            }
                        }
                    }
                    else if (r == 20000) {
                        n = 5;
                    }
                    else {
                        n = 4;
                    }
                    throw new aw("Error in getting server config: " + a2.t(), a2.u(), n);
                }
            }
            this.a(a2);
        }
        else {
            final String a3 = a.a("AUTH_ID_NAME");
            if (a3 == null || a3.length() == 0) {}
            final String a4 = a.a("AUTH_ID_VALUE");
            if (a4 == null || a4.length() == 0) {}
            final String a5 = a.a("AUTH_ID_EXPIRES");
            Long n2 = null;
            if (a5 != null) {
                try {
                    n2 = new Long(a5);
                }
                catch (Exception ex) {
                    if (a0.a.k()) {
                        a0.a.g("cannot create authIdExpires value by " + a5, ex);
                    }
                }
            }
            this.a(a4, n2);
            if (this.d == null) {
                a.c("XID_AUTO_REFRESH", "false");
            }
            this.a((a2)null);
        }
        az.n.put(this.a(), this);
        long n3 = this.ak();
        if (n3 <= System.currentTimeMillis()) {
            n3 = System.currentTimeMillis();
        }
        boolean booleanValue = true;
        final Boolean c = a.c("XID_AUTO_REFRESH");
        if (c != null) {
            booleanValue = c;
        }
        if (booleanValue) {
            this.at().a(new cc(this), n3 + 5000L);
        }
        if ("true".equals(this.aj().a("CACHE_ADJUST_EXPIRY_TIME"))) {
            new cd(this).produce();
        }
        if (a0.a.i()) {
            a0.a.g(this.as() + " autofrefresh is " + (booleanValue ? "on" : "off"));
        }
    }
    
    private String a() {
        return this.getClass().getName() + "(" + this.an() + ")";
    }
    
    public void a(final a2 a2) throws aw {
        if (a2 != null) {
            this.a.a(a2);
        }
        this.g = new a7(this);
        this.m = "" + az.v++;
        if (a0.a.i() || !this.a.c("SUPPRESS_VERSION")) {
            a0.a.g("created MDGSession;" + this.a.a("AUTH_ID_NAME") + "=" + this.a.a("AUTH_ID_VALUE") + " for " + b8.e + " ID=" + this.an() + " JV=" + this.c() + " BINARY=" + b8.a());
        }
        if (a2 != null && a2.r() == 0) {
            final long longValue = a2.d("SERVER_TIME");
            final long n = this.a.j("DELTA_TIME_CRITICAL");
            final long n2 = System.currentTimeMillis() / 1000L - longValue;
            if (Math.abs(n2) > n && a0.a.g()) {
                a0.a.d(this.as() + " detected a time difference of " + n2 + "seconds which is greater than " + n + "seconds  as specified by parameter DELTA_TIME_CRITICAL " + "Servertime:" + new Date(longValue * 1000L) + " " + "Localtime:" + new Date(System.currentTimeMillis()));
            }
        }
        else if (!this.a.c("NO_XID_REQUEST")) {
            throw new aw("No Config Object - please check settings for 'NO_XID_REQUEST' property", null, 11);
        }
        if (a0.a.i()) {
            a0.a.g(this.as() + " config: " + this.a.p("  "));
        }
        this.a(this.a);
        this.g.a();
        this.g.a(this.a);
        final Boolean c;
        if ((c = this.a.c("DONT_TOUCH_REQUESTS")) != null) {
            this.k = c;
        }
        final String g = this.a.g("DEFAULT_CACHE_MODE");
        if (g != null) {
            if (g.equals("READTHROUGH_CACHE_REQUEST")) {
                this.l = 1;
            }
            else if (g.equals("BYPASS_CACHE_REQUEST")) {
                this.l = 2;
            }
        }
        try {
            this.a((x)this.a.b("DEFAULT_URL_PARAMS"));
        }
        catch (Exception ex) {
            if (a0.a.i()) {
                a0.a.e(this.as() + " no default URL params available", ex);
            }
        }
        this.e = new Date();
    }
    
    private void a(final at at) {
        try {
            final x x = (x)at.b("HTTP_REQUEST_HEADER");
            if (x != null) {
                final Enumeration d = x.d();
                while (d.hasMoreElements()) {
                    final String s = d.nextElement();
                    if (s.toLowerCase(Locale.ENGLISH).indexOf("user-agent") == -1 && s.toLowerCase(Locale.ENGLISH).indexOf("host") == -1) {
                        this.j.put(s, x.a(s));
                    }
                }
            }
            this.j.put("Accept-Encoding", at.a("CONTENT_ENCODING"));
        }
        catch (Exception ex) {
            if (a0.a.g()) {
                a0.a.d(this.as() + " could not convert general HTTP headers.");
            }
        }
    }
    
    public final br e(final String s) {
        if (this.g == null) {
            return null;
        }
        return this.g.a(s);
    }
    
    public final a2 ai() {
        return a2.a(this, this.av());
    }
    
    public a1 i() {
        return this.h;
    }
    
    public final at aj() {
        return this.a;
    }
    
    public final String a(final String s, final Long n) {
        if (a0.a.l()) {
            a0.a.j(this.as() + " setAuthId " + a0.a.a(s, n));
        }
        final String a = this.a.a("AUTH_ID_VALUE");
        this.a.c("AUTH_ID_VALUE", s);
        if (n != null) {
            this.a(n);
        }
        if (a0.a.i()) {
            a0.a.g(this.as() + " set AuthIdValue to " + s + " with exp. " + n);
        }
        ++this.o;
        return a;
    }
    
    public final void a(final long n) {
        this.a.c("AUTH_ID_EXPIRES", "" + n / 1000L);
    }
    
    public final long ak() {
        final Long e = this.a.e("AUTH_ID_EXPIRES");
        if (e != null) {
            return e * 1000L;
        }
        return 0L;
    }
    
    public void b() {
        if (a0.a.i()) {
            a0.a.g(this.as() + " try to close MDGSession");
        }
        try {
            this.g.b();
            if (this.i != null) {
                this.i.destroy();
            }
            az.n.remove(this.a());
            bj.i();
            cq.b();
            cs.b();
            b8.b();
        }
        catch (Exception ex) {
            if (a0.a.g()) {
                a0.a.b(this.as() + " an Exception occured while closing the MDGSession", ex);
            }
        }
        this.p = true;
    }
    
    public boolean al() {
        return this.p;
    }
    
    public final bm am() throws Exception {
        synchronized (this.w) {
            if (this.f != null && this.f.a() > System.currentTimeMillis()) {
                return this.f;
            }
            if (this.f != null && a0.a.h()) {
                a0.a.f(this.as() + " creating new FormatContainer because it expires at " + this.f.a());
            }
            else if (this.f == null && a0.a.h()) {
                a0.a.f(this.as() + " creating initial FormatCointainer");
            }
            return this.f = new bm(this, this.f);
        }
    }
    
    public final String an() {
        return this.m;
    }
    
    public final bc ao() {
        return ((bu)this.g.a("REQUESTHANDLER_MODULE")).b();
    }
    
    public final int ap() {
        return this.l;
    }
    
    public final j aq() {
        return ((b9)this.e("NON_BLOCKING_MODULE")).b;
    }
    
    public String ar() {
        String s = this.a.a("POST_URI") + this.a.a("AUTH_ID_NAME") + "=" + this.a.a("AUTH_ID_VALUE");
        if (this.s != null) {
            s = s + "&" + this.s.toString();
        }
        return s;
    }
    
    private String c() {
        final String s = "java.version";
        String property = "";
        try {
            final SecurityManager securityManager = System.getSecurityManager();
            if (securityManager != null) {
                securityManager.checkPropertyAccess(s);
            }
            property = System.getProperty(s);
        }
        catch (Throwable t) {}
        return property;
    }
    
    public final String as() {
        return "[" + this.an() + "]";
    }
    
    public String toString() {
        return "MDGSession@" + this.hashCode() + this.as() + ":" + (this.al() ? "closed" : "ready");
    }
    
    public final cb at() {
        if (this.i == null) {
            this.i = new cb(100L, 5, 10);
        }
        return this.i;
    }
    
    public int a(final a4 a4) {
        if (this.q == null) {
            this.q = new Hashtable();
            try {
                final x x = (x)this.a.b("REQUEST_TIMEOUT_EXCEPTIONS");
                if (x != null) {
                    final Enumeration d = x.d();
                    while (d.hasMoreElements()) {
                        try {
                            String s = d.nextElement();
                            final Integer n = new Integer(x.a(s));
                            if (s.startsWith("/")) {
                                s = s.substring(1);
                            }
                            final int index = s.indexOf(".csv");
                            if (index > 0) {
                                s = s.substring(0, index);
                            }
                            final int index2 = s.indexOf("?");
                            if (index2 > 0) {
                                s = s.substring(0, index2);
                            }
                            if (a0.a.j()) {
                                a0.a.h(this.as() + "registering timeout " + n + " for " + s);
                            }
                            this.q.put(s, n);
                        }
                        catch (Exception ex) {
                            if (!a0.a.g()) {
                                continue;
                            }
                            a0.a.b("cannot convert timeout value", ex);
                        }
                    }
                }
            }
            catch (Exception ex2) {
                if (a0.a.i()) {
                    a0.a.e("cannot access REQUEST_TIMEOUT_EXCEPTIONS property : " + this.a.a("REQUEST_TIMEOUT_EXCEPTIONS"), ex2);
                }
            }
        }
        final String k = a4.k();
        final Integer n2 = this.q.get(k);
        if (n2 != null) {
            return n2;
        }
        if (this.r == null) {
            this.r = this.a.d("REQUEST_TIMEOUT");
            this.q.put(k, this.r);
        }
        return this.r;
    }
    
    public final void a(final x s) {
        if (a0.a.i()) {
            a0.a.g(this.as() + " user set [" + s + "] as default URL params");
        }
        this.s = s;
    }
    
    public x au() {
        return this.s;
    }
    
    public final void b(final x x) {
        final x au = this.au();
        if (au != null) {
            final Enumeration d = au.d();
            while (d.hasMoreElements()) {
                final String s = d.nextElement();
                if (!x.c(s)) {
                    final String a = au.a(s);
                    if (a == null) {
                        continue;
                    }
                    if (a0.a.k()) {
                        a0.a.i(this.as() + " manipulateRequest:setting default parameter [" + s + "]=[" + a + "]");
                    }
                    x.a(s, a);
                }
            }
        }
    }
    
    public final x av() {
        return this.t;
    }
    
    public final long aw() {
        synchronized (this.u) {
            return this.u;
        }
    }
    
    static {
        az.n = new Hashtable();
        az.v = 0;
    }
}
