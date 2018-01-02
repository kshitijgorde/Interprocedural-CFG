import java.util.Enumeration;
import java.util.Vector;
import java.util.StringTokenizer;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class cg extends be implements ch, ay
{
    public ax a;
    public boolean b;
    public ce c;
    public boolean d;
    public boolean e;
    public int f;
    public int g;
    public static boolean h;
    
    public cg(final ax a) {
        this.d = false;
        this.e = false;
        this.f = 0;
        this.g = 0;
        this.b = false;
        this.a = a;
        this.c = a.c();
        this.g = a.aj().d("PUSH_UPDATE_MODE");
    }
    
    public boolean a(final du du, final int n, final bb bb) {
        final String a = du.a().a("UN");
        if (a != null) {
            try {
                final int a2 = ck.a(a);
                if (this.e && a2 != this.f + 1) {
                    this.a.a((Exception)null, "received update number out of order: " + a2 + "!=" + (this.f + 1));
                    return false;
                }
                this.f = a2;
                this.e = true;
            }
            catch (NumberFormatException ex) {
                this.e = false;
            }
        }
        return super.a(du, n, bb);
    }
    
    public long b() {
        return this.a.ac();
    }
    
    public long d() {
        return this.a.ad();
    }
    
    public boolean a(final bg bg, final int n, final bb bb, final du du) {
        if (this.b) {
            final ci j = this.a.j();
            ++j.i;
            if (du.g) {
                final ci i = this.a.j();
                ++i.j;
                final ci k = this.a.j();
                k.m += du.e;
                final ci l = this.a.j();
                l.n += du.d;
            }
            else {
                final ci m = this.a.j();
                ++m.k;
                final ci j2 = this.a.j();
                j2.l += du.d;
            }
            return this.a(bg);
        }
        this.b = true;
        return this.a(bg, du);
    }
    
    public boolean a(final bg bg) {
        if (!this.d) {
            return false;
        }
        if (this.a.c() != this.c) {
            return false;
        }
        if (ay.a.l()) {
            ak.a.j(this.a.as() + " handleUpdateObject " + bg + " with " + bg.a() + " lines");
        }
        cg.h = true;
        bg ae = null;
        if (bg.a() > 0) {
            int n;
            for (int i = 0; i < bg.a(); i += n) {
                boolean b = true;
                try {
                    final int intValue = bg.a(i, 1).b((String)null);
                    if (intValue < 0) {
                        n = 1;
                        try {
                            Integer.parseInt(bg.a(i, 2).toString());
                        }
                        catch (NumberFormatException ex2) {
                            final byte a = bg.a(i, 2).a();
                            if (a != 65 && a != 66 && a != 67) {
                                b = false;
                            }
                        }
                    }
                    else {
                        n = bg.a(i, 3).b((String)null) + 1;
                    }
                    if (b) {
                        if (ay.a.l()) {
                            ay.a.j(this.a.as() + " found error code " + intValue);
                        }
                        final String string = bg.a(i).a(0).toString();
                        if (ay.a.l()) {
                            ay.a.j(this.a.as() + " found update key(s) " + string);
                        }
                        final StringTokenizer stringTokenizer = new StringTokenizer(string, ",");
                        while (stringTokenizer.hasMoreTokens()) {
                            final String nextToken = stringTokenizer.nextToken();
                            final cw b2 = this.a.b(nextToken);
                            if (b2 != null) {
                                if (intValue < 0) {
                                    if (ay.a.l()) {
                                        ay.a.j(this.a.as() + " try to update diff-line " + bg.a(i));
                                    }
                                    b2.a(bg.a(i));
                                    ae = b2.ae();
                                }
                                else if (intValue == 0) {
                                    final bg bg2 = new bg();
                                    for (int j = 0; j < n; ++j) {
                                        bg2.a(bg.a(i + j));
                                    }
                                    bg2.a(new bi(b2.k(), b2.v()), 0, 0);
                                    switch (this.g) {
                                        case 0:
                                        case 1: {
                                            b2.a(bg2, bg2.b(), null);
                                            continue;
                                        }
                                        case 2: {
                                            if (ae != null) {
                                                bg2.a(ae.a(0, 2), 0, 2);
                                            }
                                            if (ae != null && ae.toString().equals(bg2.toString())) {
                                                if (ay.a.l()) {
                                                    ay.a.j(this.a.as() + " OK update matches with comparison");
                                                    continue;
                                                }
                                                continue;
                                            }
                                            else {
                                                final int a2 = ae.a();
                                                final int a3 = bg2.a();
                                                if (a2 == a3) {
                                                    for (int k = 0; k < a3; ++k) {
                                                        final int a4 = ae.a(k).a();
                                                        final int a5 = bg2.a(k).a();
                                                        if (a4 != a5) {
                                                            if (ay.a.l()) {
                                                                ay.a.j(this.a.as() + " update NOT OK; Differences in tokenNr in line " + k + ": oldTokenNr=" + a4 + ", newTokenNr=" + a5);
                                                            }
                                                        }
                                                        else {
                                                            for (int l = 0; l < ae.a(k).a(); ++l) {
                                                                if (!ae.a(k, l).toString().equals(bg2.a(k, l).toString()) && ay.a.l()) {
                                                                    ay.a.j(this.a.as() + " update NOT OK; Differences in line " + k + ", " + ae.a(k, l).toString() + ", pos " + l + ", " + bg2.a(k, l).toString());
                                                                }
                                                            }
                                                        }
                                                    }
                                                    continue;
                                                }
                                                if (ay.a.l()) {
                                                    ay.a.j(this.a.as() + " update NOT OK; LineNr after update: " + a2 + "; LineNr of comparison: " + a3);
                                                    continue;
                                                }
                                                continue;
                                            }
                                            break;
                                        }
                                    }
                                }
                                else {
                                    if (!ay.a.g()) {
                                        continue;
                                    }
                                    ay.a.d(this.a.as() + " got mdg_error [" + intValue + "] for key [" + nextToken + "] (" + b2.l() + ") in SessionResponseHandler");
                                }
                            }
                            else {
                                if (!ay.a.i()) {
                                    continue;
                                }
                                ay.a.g(this.a.as() + " can't find object for key " + nextToken + " in SessionResponseHandler");
                            }
                        }
                    }
                    else if (ay.a.g()) {
                        ay.a.d(this.a.as() + " skipping diff-line with unknown command in SessionResponseHandler");
                    }
                }
                catch (Exception ex) {
                    this.a.a(ex, "internal error in handleUpdateObject", this.c);
                    return false;
                }
            }
        }
        else if (ay.a.i()) {
            ay.a.g(this.a.as() + " SessionResponseHandler: received empty update information.");
        }
        return true;
    }
    
    public boolean a(final bg bg, final du du) {
        if (ay.a.i()) {
            ay.a.g(this.a.as() + " SessionResponseHandler received session-object " + bg);
        }
        final a3 a3 = new a3(this.a, this.a.aj().g("URI_SESSION"), 0, new bn(new a3[] { null }, 0), false);
        a3.a(bg, du.b().length, du.a().i());
        if (this.a.c() != this.c || this.a.d() == 2) {
            if (ay.a.g()) {
                ay.a.d(this.a.as() + " couldn't assign session-object because of session-status " + this.a.c().b());
            }
            if (a3.f()) {
                this.a.a(a3.e("PUSH_CLIENT_ID"), a3.e("PUSH_PREFIX"));
            }
            return false;
        }
        this.c = this.a.a(a3, this);
        if (this.c != null && this.c.a() == 2) {
            this.d = true;
        }
        return this.d;
    }
    
    public void a(final Exception ex, final int n, final String s) {
        if (this.a.c() != this.c) {
            return;
        }
        if (ay.a.i()) {
            ay.a.g(this.a.as() + " handleCSVError with " + ex + " at msgNr " + n + ((s != null) ? (" with " + s) : ""));
        }
        this.a.a(ex, ex.toString(), this.c, this);
    }
    
    public x a(final String s) {
        final cj cj = new cj(this.a.aj().g("URI_SESSION") + s);
        final String a = this.a.aj().a("AUTH_ID_NAME");
        final String a2 = this.a.aj().a("AUTH_ID_VALUE");
        if (a != null && a2 != null) {
            cj.a(a, a2);
        }
        final String a3 = this.a.aj().a("PUSH_CLIENT_NAME");
        if (a3 != null && a3.length() != 0) {
            cj.a("PUSH_CLIENT_NAME", a3);
        }
        final String n = this.a.n();
        if (n != null) {
            cj.a("LAST_PUSH_CLIENT_ID", n);
        }
        final String o = this.a.o();
        if (o != null) {
            cj.a("FIRST_PUSH_CLIENT_ID", o);
        }
        final String q = this.a.q();
        if (q != null) {
            cj.a("RECONNECT_REASON", q);
        }
        final int u = this.a.u();
        if (u > 0) {
            cj.a("PUSH_CLIENT_STATUS_TIMEOUT", u + this.a.aj().d("PUSH_ESTABLISH_TIMEOUT") + "");
        }
        cj.a("PUSH_CASCADE", this.a.t() + "");
        this.a.b(cj);
        final long currentTimeMillis = System.currentTimeMillis();
        cj.a("SESSION_INFO", az.ah() + "." + this.a.v() + "." + (int)this.a.aj().d("MAX_PUSH_SESSIONS") + "_" + ((this.a.w() > 0L) ? (currentTimeMillis - this.a.w()) : 0L) + "." + this.a.y() + "." + this.a.z() + "_" + currentTimeMillis + "." + (currentTimeMillis - this.a.x()));
        final String aa = this.a.aa();
        if (aa != null) {
            cj.a("SESSION_INFO_MSG", aa);
        }
        synchronized (ax.x) {
            if (ax.x.size() > 0) {
                final Vector<String> vector = new Vector<String>();
                final StringBuffer sb = new StringBuffer();
                final Enumeration<String> keys = ax.x.keys();
                while (keys.hasMoreElements()) {
                    final String s2 = keys.nextElement();
                    sb.append(s2 + ",");
                    vector.addElement(s2);
                }
                final String string = sb.toString();
                cj.a("BYE_PUSH_CLIENT_ID", string.substring(0, string.length() - 1));
                this.a.a(vector);
            }
        }
        cj.a("NR_CONNECTS", this.a.r() + "");
        cj.a("NR_CONNECT_TRIES", this.a.s() + "");
        cj.a("JMDG_VERSION", "2.1.12");
        cj.a("PUSH_SERVER_DELAY", this.a.ab() + "");
        cj.a("PUSH_UPDATE_MODE", this.a.aj().a("PUSH_UPDATE_MODE"));
        cj.a("PUSH_IDLE_TIMEOUT", this.a.aj().a("PUSH_IDLE_TIMEOUT"));
        return cj;
    }
    
    public boolean e() {
        return cg.h;
    }
    
    public boolean c() {
        return false;
    }
    
    public final void b(final Exception ex, final int n, final String s) {
        if (ex == null) {
            return;
        }
        if (ex instanceof am) {
            if (ex instanceof ct) {
                final ct ct = (ct)ex;
                this.a.ak.addElement(new aw(ct.getMessage(), ex, (ct.c() == 403) ? 9 : 7));
            }
            else {
                this.a.ak.addElement(new aw("Network Error", ex, 8));
            }
        }
        else if (ex instanceof cu && ((cu)ex).c() == 1) {
            this.a.ak.addElement(new aw("Timeout", ex, 6));
        }
        else {
            this.a.ak.addElement(new aw(ex.getClass().getName(), ex, 0));
        }
    }
    
    static {
        cg.h = false;
    }
}
