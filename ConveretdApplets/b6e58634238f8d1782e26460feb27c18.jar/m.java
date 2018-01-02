import java.text.ParseException;
import java.awt.Component;
import java.util.Properties;
import java.awt.Color;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class m
{
    public d a;
    public m b;
    public Vector c;
    public Color d;
    public Double e;
    public Double f;
    public Integer g;
    public Integer h;
    public Color i;
    public Color j;
    public Color k;
    public Integer l;
    public Boolean m;
    public Double n;
    public Double o;
    public Double p;
    public ai q;
    
    public m(final d a) {
        this.c = new Vector();
        this.a = a;
    }
    
    public m(final m b) {
        this.c = new Vector();
        this.a = b.a;
        this.b = b;
        b.c.addElement(this);
    }
    
    public void a(final Properties properties) {
        final String property = properties.getProperty("UndefinedColor");
        if (property == null) {
            return;
        }
        try {
            this.a(this.a.d().f(property));
        }
        catch (ParseException ex) {
            this.a.c().a((Component)this.a.s, 4, new Object[] { property, "UndefinedColor", ex.getMessage() });
        }
    }
    
    public void a(final Color d) {
        this.d = d;
        this.q();
    }
    
    public Color a() {
        if (this.d != null) {
            return this.d;
        }
        if (this.b != null) {
            return this.b.a();
        }
        return this.a.i().c;
    }
    
    public void a(final Double e) {
        this.e = e;
        this.q();
    }
    
    public Double b() {
        if (this.e != null) {
            return this.e;
        }
        if (this.b != null) {
            return this.b.b();
        }
        return null;
    }
    
    public void b(final Double f) {
        this.f = f;
        this.q();
    }
    
    public Double c() {
        if (this.f != null) {
            return this.f;
        }
        if (this.b != null) {
            return this.b.c();
        }
        return null;
    }
    
    public void a(final Integer g) {
        this.g = g;
        this.q();
    }
    
    public int d() {
        if (this.g != null) {
            return this.g;
        }
        if (this.b != null) {
            return this.b.d();
        }
        return 0;
    }
    
    public void b(final Integer h) {
        this.h = h;
        this.q();
    }
    
    public int e() {
        if (this.h != null) {
            return this.h;
        }
        if (this.b != null) {
            return this.b.e();
        }
        return this.d();
    }
    
    public void b(final Color i) {
        this.i = i;
        this.q();
    }
    
    public Color f() {
        if (this.i != null) {
            return this.i;
        }
        if (this.b != null) {
            return this.b.f();
        }
        return null;
    }
    
    public void c(final Color j) {
        this.j = j;
        this.q();
    }
    
    public Color g() {
        if (this.j != null) {
            return this.j;
        }
        if (this.b != null) {
            return this.b.g();
        }
        return null;
    }
    
    public void d(final Color k) {
        this.k = k;
        this.q();
    }
    
    public Color h() {
        if (this.k != null) {
            return this.k;
        }
        if (this.b != null) {
            return this.b.h();
        }
        return null;
    }
    
    public void c(final Integer l) {
        this.l = l;
        this.q();
    }
    
    public int i() {
        if (this.l != null) {
            return this.l;
        }
        if (this.b != null) {
            return this.b.i();
        }
        return 0;
    }
    
    public void a(final Boolean m) {
        this.m = m;
        this.q();
    }
    
    public boolean j() {
        if (this.m != null) {
            return this.m;
        }
        return this.b != null && this.b.j();
    }
    
    public void c(final Double n) {
        this.n = n;
        this.q();
    }
    
    public Double k() {
        if (this.n != null) {
            return this.n;
        }
        if (this.b != null) {
            return this.b.k();
        }
        return null;
    }
    
    public void d(final Double o) {
        this.o = o;
        this.q();
    }
    
    public Double l() {
        if (this.o != null) {
            return this.o;
        }
        if (this.b != null) {
            return this.b.l();
        }
        return null;
    }
    
    public void e(final Double p) {
        this.p = p;
        this.q();
    }
    
    public Double m() {
        if (this.p != null) {
            return this.p;
        }
        if (this.b != null) {
            return this.b.m();
        }
        return null;
    }
    
    public Color a(final Number n) {
        final Object o = this.o();
        final int a;
        Color a2;
        if (n == null || (a = this.a(n.doubleValue())) < 0 || this.i() <= 0 || this.b() == null || this.c() == null || ai.a(o).length <= 0) {
            a2 = this.a();
        }
        else {
            final Object object = ai.a(o)[a];
            final double n2 = (object.c == object.d) ? 0.0 : ((n.doubleValue() - object.c) / (object.d - object.c));
            a2 = new Color((int)this.a(object.g.getRed(), object.f.getRed(), n2), (int)this.a(object.g.getGreen(), object.f.getGreen(), n2), (int)this.a(object.g.getBlue(), object.f.getBlue(), n2));
        }
        return a2;
    }
    
    private double a(final double n, final double n2, final double n3) {
        double n4;
        if (n < n2) {
            n4 = n2 - n3 * (n2 - n);
        }
        else {
            n4 = n2 + n3 * (n - n2);
        }
        if (n4 > 255.0) {
            n4 = n;
        }
        else if (n4 < 0.0) {
            n4 = n2;
        }
        return n4;
    }
    
    public final Double[] a(final int n) {
        final Object o = this.o();
        Double[] array;
        if (ai.a(o).length <= 0 || this.i() <= 0 || this.b() == null || this.c() == null || n < 0 || n + 1 > ai.a(o).length) {
            array = new Double[2];
        }
        else {
            final Object object = ai.a(o)[n];
            array = new Double[] { new Double(object.c), new Double(object.d) };
        }
        return array;
    }
    
    public final Color b(final int n) {
        final Object o = this.o();
        Color color;
        if (ai.a(o).length <= 0 || this.i() <= 0 || this.b() == null || this.c() == null || n < 0 || n + 1 > ai.a(o).length) {
            color = this.a();
        }
        else {
            color = ai.a(o)[n].e;
        }
        return color;
    }
    
    public int a(final int n, final int n2) {
        return n * this.i() / n2;
    }
    
    public int b(final int n, final int n2) {
        return n2 * n / this.i();
    }
    
    public int c(final int n, final int n2) {
        return n2 / this.i();
    }
    
    public void n() {
        if (this.c != null) {
            for (int i = 0; i < this.c.size(); ++i) {
                ((m)this.c.elementAt(i)).n();
            }
            this.c.removeAllElements();
            this.c = null;
        }
        this.g = null;
        this.h = null;
        this.m = null;
        this.i = null;
        this.n = null;
        this.f = null;
        this.j = null;
        this.o = null;
        this.e = null;
        this.k = null;
        this.p = null;
        this.l = null;
        this.d = null;
        this.b = null;
        this.a = null;
    }
    
    public ai o() {
        Object p;
        if ((p = this.p()) == null) {
            if (this.i() <= 0 || this.b() == null || this.c() == null || this.c() <= this.b()) {
                p = new Object(0, 0, 0) {
                    public aj[] a;
                    public int b;
                    public int c;
                    
                    {
                        this.a = new aj[n];
                        this.b = b;
                        this.c = c;
                    }
                    
                    public double a() {
                        return this.a[0].c;
                    }
                    
                    public double b() {
                        return this.a[this.a.length - 1].d;
                    }
                    
                    public String toString() {
                        final StringBuffer sb = new StringBuffer();
                        sb.append("Buckets{");
                        sb.append("leftLength=" + this.b);
                        sb.append(",");
                        sb.append("rightLength=" + this.c);
                        sb.append(",");
                        sb.append("buckets={");
                        for (int i = 0; i < this.a.length; ++i) {
                            sb.append("" + this.a[i]);
                            if (i + 1 < this.a.length) {
                                sb.append(",");
                            }
                        }
                        sb.append("}");
                        sb.append("}");
                        return sb.toString();
                    }
                    
                    public static /* synthetic */ aj[] a(final ai object) {
                        return object.a;
                    }
                    
                    public static /* synthetic */ int a(final ai object, final int b) {
                        return object.b = b;
                    }
                    
                    public static /* synthetic */ int b(final ai object, final int c) {
                        return object.c = c;
                    }
                    
                    public static /* synthetic */ int b(final ai object) {
                        return object.b;
                    }
                    
                    public static /* synthetic */ int c(final ai object) {
                        return object.c;
                    }
                    
                    public static /* synthetic */ int d(final ai object) {
                        return ++object.b;
                    }
                    
                    public static /* synthetic */ int e(final ai object) {
                        return --object.c;
                    }
                    
                    public static /* synthetic */ int f(final ai object) {
                        return ++object.c;
                    }
                    
                    public static /* synthetic */ int g(final ai object) {
                        return --object.b;
                    }
                };
            }
            else {
                p = new Object(this.i(), 0, 0) {
                    public aj[] a = new aj[n];
                    public int b = b;
                    public int c = c;
                    
                    public double a() {
                        return this.a[0].c;
                    }
                    
                    public double b() {
                        return this.a[this.a.length - 1].d;
                    }
                    
                    public String toString() {
                        final StringBuffer sb = new StringBuffer();
                        sb.append("Buckets{");
                        sb.append("leftLength=" + this.b);
                        sb.append(",");
                        sb.append("rightLength=" + this.c);
                        sb.append(",");
                        sb.append("buckets={");
                        for (int i = 0; i < this.a.length; ++i) {
                            sb.append("" + this.a[i]);
                            if (i + 1 < this.a.length) {
                                sb.append(",");
                            }
                        }
                        sb.append("}");
                        sb.append("}");
                        return sb.toString();
                    }
                    
                    public static /* synthetic */ aj[] a(final ai object) {
                        return object.a;
                    }
                    
                    public static /* synthetic */ int a(final ai object, final int b) {
                        return object.b = b;
                    }
                    
                    public static /* synthetic */ int b(final ai object, final int c) {
                        return object.c = c;
                    }
                    
                    public static /* synthetic */ int b(final ai object) {
                        return object.b;
                    }
                    
                    public static /* synthetic */ int c(final ai object) {
                        return object.c;
                    }
                    
                    public static /* synthetic */ int d(final ai object) {
                        return ++object.b;
                    }
                    
                    public static /* synthetic */ int e(final ai object) {
                        return --object.c;
                    }
                    
                    public static /* synthetic */ int f(final ai object) {
                        return ++object.c;
                    }
                    
                    public static /* synthetic */ int g(final ai object) {
                        return --object.b;
                    }
                };
                double n = 0.0;
                double n2 = 0.0;
                switch (this.e()) {
                    default: {
                        n = -Math.max(Math.abs(this.b()), Math.abs(this.c()));
                        n2 = Math.max(Math.abs(this.b()), Math.abs(this.c()));
                        break;
                    }
                    case 1: {
                        n = this.b();
                        n2 = this.c();
                        break;
                    }
                    case 2: {
                        n = ((this.k() != null && this.m() != null && this.k() <= this.m()) ? this.k() : ((double)this.b()));
                        n2 = ((this.k() != null && this.m() != null && this.k() <= this.m()) ? this.m() : ((double)this.c()));
                        break;
                    }
                }
                if (this.j() && this.l() != null) {
                    n = Math.min(n, this.l());
                    n2 = Math.max(n2, this.l());
                }
                double min;
                if (!this.j() || this.l() == null) {
                    min = n + (n2 - n) / 2.0;
                    if (ai.a(p).length % 2 != 0) {
                        ai.a(p, (ai.a(p).length - 1) / 2);
                        ai.b(p, ai.b(p));
                    }
                    else {
                        ai.b(p, ai.a(p).length / 2);
                        ai.a(p, ai.a(p).length - ai.a(p).length / 2 - 1);
                    }
                }
                else {
                    min = Math.min(n2, Math.max(n, this.l()));
                    double n3;
                    if (n == n2) {
                        n3 = 0.0;
                    }
                    else {
                        n3 = (min - n) / (n2 - n);
                    }
                    ai.a(p, Math.max(0, (int)Math.floor(n3 * ai.a(p).length) - 1));
                    ai.b(p, Math.max(0, ai.a(p).length - ai.b(p) - 1));
                }
                if (ai.a(p).length >= 3) {
                    if (ai.b(p) == 0 && ai.c(p) > 1 && n < min) {
                        ai.d(p);
                        ai.e(p);
                    }
                    if (ai.c(p) == 0 && ai.b(p) > 1 && n2 > min) {
                        ai.f(p);
                        ai.g(p);
                    }
                }
                int n4 = 0;
                final Color f = this.f();
                for (int i = 0; i < ai.b(p); ++i, ++n4) {
                    double n5;
                    double n6;
                    if (!this.j() || this.l() == null) {
                        if (ai.a(p).length % 2 != 0) {
                            n5 = n + i * (n2 - n) / ai.a(p).length;
                            n6 = n5 + 1.0 * (n2 - n) / ai.a(p).length;
                        }
                        else {
                            final double n7 = (min - n - (n2 - n) / ai.a(p).length / 2.0) / ai.b(p);
                            n5 = n + i * n7;
                            n6 = n5 + 1.0 * n7;
                        }
                    }
                    else {
                        n5 = n + i * (min - n) / ai.b(p);
                        n6 = n5 + 1.0 * (min - n) / ai.b(p);
                    }
                    final double n8 = n5 + (n6 - n5) / 2.0;
                    final double n9 = (n5 == n6) ? 0.0 : ((n8 - n) / (min - n));
                    final Color color = new Color((int)this.a(this.g().getRed(), f.getRed(), n9), (int)this.a(this.g().getGreen(), f.getGreen(), n9), (int)this.a(this.g().getBlue(), f.getBlue(), n9));
                    final double n10 = n5;
                    final double n11 = (n5 == n6) ? 0.0 : ((n10 - n) / (min - n));
                    final Color color2 = new Color((int)this.a(this.g().getRed(), f.getRed(), n11), (int)this.a(this.g().getGreen(), f.getGreen(), n11), (int)this.a(this.g().getBlue(), f.getBlue(), n11));
                    final double n12 = n6;
                    final double n13 = (n5 == n6) ? 0.0 : ((n12 - n) / (min - n));
                    ai.a(p)[n4] = new Object(n4, n5, n6, color, color2, new Color((int)this.a(this.g().getRed(), f.getRed(), n13), (int)this.a(this.g().getGreen(), f.getGreen(), n13), (int)this.a(this.g().getBlue(), f.getBlue(), n13))) {
                        public static final double a;
                        public int b;
                        public double c;
                        public double d;
                        public Color e;
                        public Color f;
                        public Color g;
                        
                        public static boolean a(final double n, final double n2) {
                            return Math.abs(n - n2) < aj.a;
                        }
                        
                        {
                            this.b = b;
                            this.c = c;
                            this.d = d;
                            this.e = e;
                            this.f = f;
                            this.g = g;
                        }
                        
                        public String toString() {
                            final StringBuffer sb = new StringBuffer();
                            sb.append("Bucket{");
                            sb.append("index=" + this.b);
                            sb.append(",");
                            sb.append("lowerValue=" + this.c);
                            sb.append(",");
                            sb.append("upperValue=" + this.d);
                            sb.append(",");
                            sb.append("bucketColor=" + this.e);
                            sb.append(",");
                            sb.append("lowerColor=" + this.f);
                            sb.append(",");
                            sb.append("upperColor=" + this.g);
                            sb.append("}");
                            return sb.toString();
                        }
                        
                        static {
                            a = Math.pow(10.0, -5.0);
                        }
                    };
                }
                this.g();
                double n14;
                double n15;
                Color g;
                Color g2;
                if (!this.j() || this.l() == null) {
                    n14 = min - 1.0 * (n2 - n) / ai.a(p).length / 2.0;
                    n15 = min + 1.0 * (n2 - n) / ai.a(p).length / 2.0;
                    final double n16 = n14;
                    final double n17 = (n14 == n15) ? 0.0 : ((n16 - n) / (min - n));
                    g = new Color((int)this.a(this.g().getRed(), this.f().getRed(), n17), (int)this.a(this.g().getGreen(), this.f().getGreen(), n17), (int)this.a(this.g().getBlue(), this.f().getBlue(), n17));
                    final double n18 = n15;
                    final double n19 = (n14 == n15) ? 0.0 : ((n18 - min) / (n2 - min));
                    g2 = new Color((int)this.a(this.h().getRed(), this.g().getRed(), n19), (int)this.a(this.h().getGreen(), this.g().getGreen(), n19), (int)this.a(this.h().getBlue(), this.g().getBlue(), n19));
                }
                else {
                    n14 = min;
                    n15 = min;
                    g = this.g();
                    g2 = this.g();
                }
                ai.a(p)[n4] = new Object(n4, n14, n15, this.g(), g, g2) {
                    public static final double a;
                    public int b;
                    public double c;
                    public double d;
                    public Color e;
                    public Color f;
                    public Color g;
                    
                    public static boolean a(final double n, final double n2) {
                        return Math.abs(n - n2) < aj.a;
                    }
                    
                    {
                        this.b = b;
                        this.c = c;
                        this.d = d;
                        this.e = e;
                        this.f = f;
                        this.g = g;
                    }
                    
                    public String toString() {
                        final StringBuffer sb = new StringBuffer();
                        sb.append("Bucket{");
                        sb.append("index=" + this.b);
                        sb.append(",");
                        sb.append("lowerValue=" + this.c);
                        sb.append(",");
                        sb.append("upperValue=" + this.d);
                        sb.append(",");
                        sb.append("bucketColor=" + this.e);
                        sb.append(",");
                        sb.append("lowerColor=" + this.f);
                        sb.append(",");
                        sb.append("upperColor=" + this.g);
                        sb.append("}");
                        return sb.toString();
                    }
                    
                    static {
                        a = Math.pow(10.0, -5.0);
                    }
                };
                final Color h = this.h();
                for (int n20 = ai.a(p).length - 1, j = ai.c(p) - 1; j >= 0; --j, --n20) {
                    double n21;
                    double n22;
                    if (!this.j() || this.l() == null) {
                        if (ai.a(p).length % 2 != 0) {
                            n21 = n2 - (ai.a(p).length - 1 - n20) * (n2 - n) / ai.a(p).length;
                            n22 = n21 - 1.0 * (n2 - n) / ai.a(p).length;
                        }
                        else {
                            final double n23 = (n2 - min - (n2 - n) / ai.a(p).length / 2.0) / ai.c(p);
                            n21 = n2 - (ai.a(p).length - 1 - n20) * n23;
                            n22 = n21 - 1.0 * n23;
                        }
                    }
                    else {
                        n21 = n2 - (ai.c(p) - 1 - j) * (n2 - min) / ai.c(p);
                        n22 = n21 - 1.0 * (n2 - min) / ai.c(p);
                    }
                    final double n24 = n21 - (n21 - n22) / 2.0;
                    final double n25 = (n22 == n21) ? 0.0 : ((n24 - min) / (n2 - min));
                    final Color color3 = new Color((int)this.a(h.getRed(), this.g().getRed(), n25), (int)this.a(h.getGreen(), this.g().getGreen(), n25), (int)this.a(h.getBlue(), this.g().getBlue(), n25));
                    final double n26 = n22;
                    final double n27 = (n22 == n21) ? 0.0 : ((n26 - min) / (n2 - min));
                    final Color color4 = new Color((int)this.a(h.getRed(), this.g().getRed(), n27), (int)this.a(h.getGreen(), this.g().getGreen(), n27), (int)this.a(h.getBlue(), this.g().getBlue(), n27));
                    final double n28 = n21;
                    final double n29 = (n22 == n21) ? 0.0 : ((n28 - min) / (n2 - min));
                    ai.a(p)[n20] = new Object(n20, n22, n21, color3, color4, new Color((int)this.a(h.getRed(), this.g().getRed(), n29), (int)this.a(h.getGreen(), this.g().getGreen(), n29), (int)this.a(h.getBlue(), this.g().getBlue(), n29))) {
                        public static final double a;
                        public int b = b;
                        public double c = c;
                        public double d = d;
                        public Color e = e;
                        public Color f = f;
                        public Color g = g;
                        
                        public static boolean a(final double n, final double n2) {
                            return Math.abs(n - n2) < aj.a;
                        }
                        
                        public String toString() {
                            final StringBuffer sb = new StringBuffer();
                            sb.append("Bucket{");
                            sb.append("index=" + this.b);
                            sb.append(",");
                            sb.append("lowerValue=" + this.c);
                            sb.append(",");
                            sb.append("upperValue=" + this.d);
                            sb.append(",");
                            sb.append("bucketColor=" + this.e);
                            sb.append(",");
                            sb.append("lowerColor=" + this.f);
                            sb.append(",");
                            sb.append("upperColor=" + this.g);
                            sb.append("}");
                            return sb.toString();
                        }
                        
                        static {
                            a = Math.pow(10.0, -5.0);
                        }
                    };
                }
            }
            this.a(p);
        }
        return p;
    }
    
    public int a(final double n) {
        int b = -1;
        final Object o = this.o();
        if (this.i() <= 0 || this.b() == null || this.c() == null || this.c() <= this.b() || ai.a(o).length <= 0) {
            b = -1;
        }
        else if (aj.a(o.a(), o.b())) {
            b = 0;
        }
        else if (n <= o.a()) {
            b = 0;
        }
        else if (n >= o.b()) {
            b = ai.a(o).length - 1;
        }
        else {
            double n2 = 0.0;
            double n3 = 0.0;
            int n4 = 0;
            int n5 = 0;
            if (!this.j() || this.l() == null) {
                n2 = n - o.a();
                n3 = o.b() - o.a();
                n4 = ai.a(o).length;
            }
            else if (aj.a(this.l(), n)) {
                b = ai.b(o);
            }
            else if (n < this.l()) {
                n2 = n - o.a();
                n3 = this.l() - o.a();
                n4 = ai.b(o);
            }
            else {
                n2 = o.b() - n;
                n3 = o.b() - this.l();
                n4 = ai.c(o);
                n5 = ai.b(o) + 1;
            }
            if (b == -1) {
                for (b = (int)Math.floor(n2 / n3 * n4) + n5; n < ai.a(o)[b].c && b > 0; --b) {}
                while (n > ai.a(o)[b].d && b + 1 < ai.a(o).length) {
                    ++b;
                }
            }
        }
        return b;
    }
    
    public ai p() {
        if (this.b != null) {
            return this.b.p();
        }
        return this.q;
    }
    
    public void a(final ai q) {
        if (this.b != null) {
            this.b.a(q);
        }
        else {
            this.q = q;
        }
    }
    
    public void q() {
        this.a((ai)null);
    }
}
