// 
// Decompiled by Procyon v0.5.30
// 

public class a4 implements a0
{
    public String a;
    public a5 b;
    public String c;
    public String d;
    public int e;
    public long f;
    public int g;
    public String h;
    public int i;
    public bg j;
    public int k;
    public am l;
    public long m;
    public String n;
    private int o;
    private String p;
    public l q;
    
    public static final String a(final int n) {
        if (n == 0) {
            return "Ok";
        }
        if (n > 0) {
            return "Error:MdgError (" + n + ")";
        }
        if (n == -2) {
            return "Error:NetException";
        }
        if (n == -3) {
            return "Error:NoDescriptor";
        }
        if (n == -1) {
            return "Error:Unintialized";
        }
        if (n == -4) {
            return "Error:InvalidCSV";
        }
        if (n == -5) {
            return "InitError";
        }
        if (n == -6) {
            return "ClientTimeout";
        }
        if (n == -7) {
            return "HttpError";
        }
        if (n == -8) {
            return "SubscriptionLimitExceeded";
        }
        return "Error:Unknown (" + n + ")";
    }
    
    public a4() {
        this.h = "0.0.0";
        this.o = 0;
        this.p = null;
        this.q = new l(-1);
    }
    
    public a4(final az az, final String a, final boolean b) {
        this.h = "0.0.0";
        this.o = 0;
        this.p = null;
        this.q = new l(-1);
        this.a = a;
        this.b = new a5(a);
        this.k = az.aj().j("SIZE_FACTOR");
        this.c = this.b.a();
        if (b) {
            a(this.b, az);
        }
        this.a(az);
        this.d = this.b.toString();
        final ca ca = (ca)az.e("STATS_MODULE");
        if (ca != null) {
            final l l = ca.c.get(this.c);
            if (l != null) {
                l.a();
            }
        }
    }
    
    public static void a(final a4 a4, final a4 a5) {
        a4.a = a5.a;
        a4.b = a5.b;
        a4.c = a5.c;
        a4.d = a5.d;
        a4.e = a5.e;
        a4.f = a5.f;
        a4.h = a5.h;
        a4.i = a5.i;
        a4.j = a5.j;
        a4.q = a5.q;
        a4.l = a5.l;
        a4.g = a5.g;
        a4.m = a5.m;
        a4.n = a5.n;
    }
    
    public static void a(final a5 a5, final az az) {
        final String a6 = az.aj().a("URI_PREFIX");
        if (a6 != null) {
            a5.f(a6);
        }
    }
    
    public boolean a(final bg j, final int e, String n) {
        if (this.l != null) {
            if (a0.a.g()) {
                a0.a.b("skipping init because of netException", this.l);
            }
            return false;
        }
        synchronized (this.q) {
            this.j = j;
            this.e = e;
            if (this.n == null) {
                if (n == null) {
                    n = "ISO-8859-1";
                    if (a0.a.j()) {
                        a0.a.h("changing charset from null to [ISO-8859-1]");
                    }
                }
                else if (eh.a() && n != null && n.equalsIgnoreCase("UTF-8")) {
                    if (a0.a.j()) {
                        a0.a.h("changing charset from [" + n + "] to [" + "UTF8" + "]");
                    }
                    n = "UTF8";
                }
                if (eh.a() && n.equalsIgnoreCase("ISO-8859-1") && eh.b().equalsIgnoreCase("Cp1252")) {
                    if (a0.a.j()) {
                        a0.a.h("changing charset from [" + n + "] to [" + "Cp1252" + "]");
                    }
                    n = "Cp1252";
                }
                this.n = n;
            }
            try {
                this.g = j.a() - 1;
                final bh a = j.a(0);
                final String a2 = a.a(0).a(this.n);
                if (a2 != null && a2.indexOf("/") > 0) {
                    this.c = a2;
                }
                final int intValue = a.a(1).b(this.n);
                if (intValue < 0 && a0.a.g()) {
                    a0.a.d("Received incorrect StatusCode " + intValue + " from object [" + a.toString() + "]");
                }
                this.q.a(intValue);
                this.f = new Long(a.a(2).a(this.n)) * 1000L;
                this.i = a.a(5).b(this.n);
                this.h = a.a(4).a(this.n);
            }
            catch (Exception ex) {
                this.q.a(-4);
                if (a0.a.g()) {
                    a0.a.b("invalid csv for " + this.i(), ex);
                }
                if (a0.a.k()) {
                    a0.a.i("got CSVObject: " + this.y());
                }
            }
            this.m = System.currentTimeMillis();
        }
        return true;
    }
    
    public boolean a(final am l) {
        if (this.l != null) {
            if (a0.a.g()) {
                a0.a.b("skipping init(NetException) because of netException", this.l);
            }
            return false;
        }
        synchronized (this.q) {
            this.q.a(-2);
            this.l = l;
        }
        if (l instanceof ct) {
            final ct ct = (ct)l;
            this.o = ct.c();
            this.p = ct.getMessage();
            this.q.a(-7);
        }
        else {
            final Throwable a = l.a();
            if (a != null && a instanceof cu) {
                final cu cu = (cu)a;
                synchronized (this.q) {
                    switch (cu.c()) {
                        case 1: {
                            this.q.a(-6);
                        }
                        case 3: {}
                    }
                }
            }
        }
        this.m = System.currentTimeMillis();
        return true;
    }
    
    public void a(final az az) {
        b(this.b, az);
    }
    
    public static void b(final a5 a5, final az az) {
        if (az.k) {
            return;
        }
        final at aj = az.aj();
        if (!a5.c("VERSION")) {
            a5.a("VERSION", aj.a("DEFAULT_VERSION"));
        }
        if (!a5.c("BLOCKSIZE") && a5.c().indexOf("_list") > -1 && aj.a("DEFAULT_BLOCKSIZE") != null) {
            a5.a("BLOCKSIZE", aj.a("DEFAULT_BLOCKSIZE"));
        }
        if (!a5.c("LANG")) {
            a5.a("LANG", aj.a("DEFAULT_LANG"));
        }
        if (!a5.c("ID_ENCODING") && aj.a("ID_ENCODING") != null && !aj.a("ID_ENCODING").equals("none")) {
            a5.a("ID_ENCODING", aj.a("ID_ENCODING"));
        }
        final String a6 = aj.a("AUTH_ID_VALUE");
        final String a7 = aj.a("AUTH_ID_NAME");
        if (a6 != null && a7 != null) {
            a5.a(a7, a6);
        }
        else if (a0.a.g()) {
            a0.a.d(az.as() + " either AUTH_ID_NAME or AUTH_ID_VALUE is null");
        }
        az.b(a5);
    }
    
    public a5 h() {
        return this.b;
    }
    
    public final String i() {
        return this.a;
    }
    
    public a5 j() {
        return new a5(this.b);
    }
    
    public final String k() {
        return this.c;
    }
    
    public final String l() {
        return this.d;
    }
    
    public final long m() {
        return this.f;
    }
    
    public String n() {
        return this.h;
    }
    
    public int o() {
        return this.e;
    }
    
    public int p() {
        return this.e * this.k;
    }
    
    public final int q() {
        return this.i;
    }
    
    public final int r() {
        synchronized (this.q) {
            return this.q.b();
        }
    }
    
    public final String s() {
        return a(this.q.b());
    }
    
    public final String t() {
        String s = this.s() + "(" + this.q.b() + ")";
        final int b = this.q.b();
        if (b <= 0) {
            if (this.l != null) {
                s = s + " " + this.l.getMessage();
            }
            return s;
        }
        String a = "no message";
        try {
            a = this.a(0, 0);
        }
        catch (Exception ex) {}
        return s + ":" + b + ":" + a;
    }
    
    public final am u() {
        return this.l;
    }
    
    public final String v() {
        return this.n;
    }
    
    public final String a(final int n, final int n2) {
        return this.j.a(n + 1, n2).a(this.n);
    }
    
    public final int b(final int n) {
        return this.j.a(n + 1).a();
    }
    
    public final int w() {
        return this.g;
    }
    
    public String toString() {
        return this.i();
    }
    
    public String x() {
        return this.l() + ";" + this.r() + ";" + this.m() + ";" + this.n() + ";" + this.o();
    }
    
    public String y() {
        if (this.j == null) {
            return "No data.";
        }
        return this.j.toString();
    }
    
    public boolean f() {
        return this.r() == 0;
    }
    
    public boolean z() {
        return this.c.indexOf("_list") > 0;
    }
    
    public final long aa() {
        return (this.f != 0L) ? (this.m() - System.currentTimeMillis()) : 0L;
    }
}
