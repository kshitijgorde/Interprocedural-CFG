import java.applet.AppletContext;
import java.applet.Applet;
import java.awt.Component;
import java.util.Date;
import java.util.Properties;
import java.net.URL;

// 
// Decompiled by Procyon v0.5.30
// 

public final class d
{
    public static final String a;
    public static final String b;
    public static final String c;
    public static final String d;
    public static final String e;
    public static final String f;
    public Class g;
    public Class h;
    public e i;
    public f j;
    public g k;
    public i l;
    public n m;
    public j n;
    public k o;
    public l p;
    public boolean q;
    public URL r;
    public b s;
    public Properties t;
    
    public static final String a() {
        return "(tm)";
    }
    
    public static final String b() {
        String property;
        if ((property = System.getProperty("java.version")) == null) {
            property = "(unknown)";
        }
        return property;
    }
    
    public d(final URL r, final b s, final Properties t) {
        final String s2 = "-----";
        this.s = s;
        this.r = r;
        this.t = t;
        final e i = this.i;
        e.a.println(s2 + " " + d.d);
        final e j = this.i;
        e.a.println(s2 + " " + "© 2002-2005 SS&C Technologies, Inc.");
        final e k = this.i;
        e.a.println(s2 + " " + new Date());
        final boolean n = this.n();
        this.q = n;
        if (n) {
            this.i = this.c();
            this.j = this.d();
            this.k = this.e();
            this.l = this.f();
            this.n = this.h();
            this.o = this.i();
            this.p = this.j();
            this.m = this.g();
            if (this.g == null) {
                try {
                    this.g = Class.forName("com.NeoVision.WebHeatmaps.Client.WhClientAppletBeanInfo");
                }
                catch (ClassNotFoundException ex) {}
            }
            if (this.h == null) {
                try {
                    this.h = Class.forName("com.NeoVision.WebHeatmaps.Client.WhClientApplet$COMClassObject");
                }
                catch (ClassNotFoundException ex2) {}
            }
        }
    }
    
    public final e c() {
        if (this.i == null) {
            (this.i = new e(this)).a(this.k());
        }
        return this.i;
    }
    
    public final f d() {
        if (this.j == null) {
            (this.j = new f(this)).a(this.k());
        }
        return this.j;
    }
    
    public final g e() {
        if (this.k == null) {
            (this.k = new g(this)).a(this.k());
        }
        return this.k;
    }
    
    public final i f() {
        if (this.l == null) {
            this.l = new i(this);
        }
        return this.l;
    }
    
    public final n g() {
        if (this.m == null) {
            this.m = new n(this);
        }
        return this.m;
    }
    
    public final j h() {
        if (this.n == null) {
            (this.n = new j(this)).a(this.k());
        }
        return this.n;
    }
    
    public final k i() {
        if (this.o == null) {
            (this.o = new k(this, this.k())).a(this.k());
        }
        return this.o;
    }
    
    public final l j() {
        if (this.p == null) {
            (this.p = new l(this)).a(this.k());
        }
        return this.p;
    }
    
    private boolean n() {
        boolean b = false;
        final String b2 = b();
        final int digit;
        if (b2.length() >= 3 && Character.isDigit(b2.charAt(0)) && (digit = Character.digit(b2.charAt(0), 10)) >= 1 && Character.isDigit(b2.charAt(2))) {
            b = (Character.digit(b2.charAt(2), 10) >= 1);
        }
        if (!b) {
            this.c().a((Component)this.s, 14, new Object[] { b2 });
            b = false;
        }
        return b;
    }
    
    public final Properties k() {
        if (this.t == null) {
            this.t = c.a;
        }
        return this.t;
    }
    
    public final void a(final URL url, final String s) {
        final AppletContext appletContext;
        if (this.s instanceof Applet && (appletContext = ((Applet)this.s).getAppletContext()) != null) {
            if (s != null) {
                appletContext.showDocument(url, s);
            }
            else {
                appletContext.showDocument(url, "_blank");
            }
        }
    }
    
    public final void l() {
        this.c().a();
        this.g().a();
        this.s.Destroy();
    }
    
    public final void m() {
        if (this.n != null) {
            this.n.a();
            this.n = null;
        }
        if (this.p != null) {
            this.p.i();
            this.p = null;
        }
        this.r = null;
        if (this.i != null) {
            this.i.b();
            this.i = null;
        }
        if (this.m != null) {
            this.m.b();
            this.m = null;
        }
        if (this.k != null) {
            this.k.b();
            this.k = null;
        }
        if (this.o != null) {
            this.o.e();
            this.o = null;
        }
        this.s = null;
        if (this.t != null) {
            this.t.clear();
            this.t = null;
        }
        if (this.l != null) {
            this.l.a();
            this.l = null;
        }
        if (this.j != null) {
            this.j.d();
            this.j = null;
        }
    }
    
    static {
        a = "webHeatmap" + a();
        b = "webHeatmaps" + a();
        c = d.b + " User Client";
        d = d.c + " " + "3.1.6.12_nocpslim" + (("release".length() > 0) ? " release" : "") + " (" + "2009.08.14 04:29:09 PM" + ")";
        e = d.c + " " + "3.1.6.12_nocpslim" + (("release".length() > 0) ? " release" : "") + " (" + "2009.08.14 04:29:09 PM" + ") " + "© 2002-2005 SS&C Technologies, Inc.";
        f = System.getProperty("line.separator");
    }
}
