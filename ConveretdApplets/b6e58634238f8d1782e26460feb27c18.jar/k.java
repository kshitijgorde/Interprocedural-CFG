import java.awt.SystemColor;
import java.text.ParseException;
import java.awt.Component;
import java.util.Properties;
import java.awt.Font;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class k
{
    public String a;
    public Color b;
    public Color c;
    public Color d;
    public Color e;
    public Color f;
    public boolean g;
    public boolean h;
    public boolean i;
    public String j;
    public String k;
    public int l;
    public long m;
    public int n;
    public Font o;
    public int p;
    public Color q;
    public long r;
    public Font s;
    public Font t;
    public Font u;
    public d v;
    public boolean w;
    public int x;
    public int y;
    public boolean z;
    public boolean aa;
    public boolean ab;
    public boolean ac;
    public boolean ad;
    public int ae;
    public int af;
    public Integer ag;
    
    public void a(final Properties properties) {
        this.c = this.a(properties, "BackgroundColor", this.c);
        this.d = this.a(properties, "FlyoverBackgroundColor", this.d);
        this.e = this.a(properties, "FlyoverHeaderColor", this.e);
        this.b = this.b;
        this.f = this.a(properties, "GridColor", this.f);
        this.g = this.a(properties, "BrandingOn", this.g);
        this.l = this.a(properties, "GridThickness", this.l);
        final double a = this.a(properties, "FlyoverWait", Double.NaN);
        if (a != Double.NaN) {
            this.m = Math.round(1000.0 * a);
        }
        this.n = this.a(properties, "WarningBannerHeight", this.n);
        this.ad = this.a(properties, "UseLeftClickMenus", this.ad);
        this.q = this.q;
        this.h = this.a(properties, "ShowConnectionStatus", this.h);
        this.i = this.a(properties, "ShowConnectionInformation", this.i);
    }
    
    private Color a(final Properties properties, final String s, final Color color) {
        final String property = properties.getProperty(s);
        if (property == null) {
            return color;
        }
        try {
            return this.v.d().f(property);
        }
        catch (ParseException ex) {
            this.v.c().a((Component)this.v.s, 4, new Object[] { property, s, ex.getMessage() });
            return color;
        }
    }
    
    private boolean a(final Properties properties, final String s, final boolean b) {
        final String property = properties.getProperty(s);
        if (property == null) {
            return b;
        }
        try {
            return this.v.d().e(property);
        }
        catch (Exception ex) {
            this.v.c().a((Component)this.v.s, 4, new Object[] { property, s, ex.getMessage() });
            return b;
        }
    }
    
    private int a(final Properties properties, final String s, final int n) {
        final String property = properties.getProperty(s);
        if (property == null) {
            return n;
        }
        try {
            final int intValue = this.v.d().c(property);
            if (intValue >= 0) {
                return intValue;
            }
            this.v.c().a((Component)this.v.s, 4, new Object[] { property, s, "negative" });
        }
        catch (Exception ex) {
            this.v.c().a((Component)this.v.s, 4, new Object[] { property, s, ex.getMessage() });
        }
        return n;
    }
    
    private double a(final Properties properties, final String s, final double n) {
        final String property = properties.getProperty(s);
        if (property == null) {
            return n;
        }
        try {
            final double doubleValue = this.v.d().d(property);
            if (doubleValue >= 0.0) {
                return doubleValue;
            }
            this.v.c().a((Component)this.v.s, 4, new Object[] { property, s, "negative" });
        }
        catch (ParseException ex) {
            this.v.c().a((Component)this.v.s, 4, new Object[] { property, s, ex.getMessage() });
        }
        return n;
    }
    
    public final Font a() {
        Font s;
        if ((s = this.s) == null) {
            s = new Font(this.o.getName(), this.o.getStyle() + 1, this.o.getSize() + 2);
        }
        return s;
    }
    
    public final Font b() {
        Font t;
        if ((t = this.t) == null) {
            t = new Font("Dialog", 0, 12);
        }
        return t;
    }
    
    public final Font c() {
        Font u;
        if ((u = this.u) == null) {
            final Font b = this.b();
            u = new Font(b.getName(), b.getStyle() + 1, b.getSize() + 1);
        }
        return u;
    }
    
    public k(final d v, final Properties properties) {
        this.a = "";
        this.b = SystemColor.window;
        this.c = SystemColor.window;
        this.d = new Color(255, 255, 204);
        this.e = new Color(102, 51, 0);
        this.f = SystemColor.windowText;
        this.g = true;
        this.h = true;
        this.i = true;
        this.j = "Last update: $UpdateTime";
        this.k = "$Status";
        this.l = 1;
        this.m = 400L;
        this.n = 17;
        this.o = new Font("SansSerif", 0, 9);
        this.p = this.o.getSize() + 2 * this.l;
        this.q = Color.red;
        this.r = 2000L;
        this.w = true;
        this.x = 30;
        this.z = false;
        this.aa = false;
        this.ab = false;
        this.ac = false;
        this.ad = false;
        this.ae = 1;
        this.af = 1;
        this.ag = null;
        this.v = v;
        this.w = this.a(properties, "ShowColorBar", this.w);
        this.x = this.a(properties, "RowLabelWidth", this.x);
    }
    
    public final int a(final int n) {
        Integer n2;
        if ((n2 = this.d()) == null) {
            this.a(this.b(n));
            n2 = this.d();
        }
        return n2;
    }
    
    public final Integer d() {
        return this.ag;
    }
    
    public final Integer b(final int n) {
        return new Integer((int)Math.max(1.0, Math.sqrt(n)));
    }
    
    public final int a(final s s) {
        int max = 1;
        for (int i = 0; i < s.f(); ++i) {
            max = Math.max(max, s.b(i).g());
        }
        return max;
    }
    
    public final int b(final s s) {
        int n = 1;
        for (int i = 0; i < s.f(); ++i) {
            final s b = s.b(i);
            if (b.f.d() != null && !b.c().ac) {
                int n2 = b.g();
                for (int j = 1; j < b.q(); ++j) {
                    if (b.a(j - 1, j)) {
                        n2 += b.g();
                    }
                    else {
                        n = Math.max(n, n2);
                        n2 = b.g();
                    }
                }
                n = Math.max(n, n2);
            }
            else {
                n = Math.max(n, b.g() * b.q());
            }
        }
        return n;
    }
    
    public int a(final s s, final int n) {
        return Math.min(this.b(s), Math.max(this.a(s), n));
    }
    
    public void a(final Integer ag) {
        this.ag = ag;
    }
    
    public void e() {
        this.c = null;
        this.s = null;
        this.ag = null;
        this.v = null;
    }
}
