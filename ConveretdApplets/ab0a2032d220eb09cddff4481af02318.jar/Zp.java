import java.awt.Graphics;
import java.awt.Font;
import java.text.DecimalFormat;
import java.awt.Rectangle;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class Zp
{
    protected int r;
    protected int s;
    public static final int t = 0;
    public static final int u = 1;
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    protected Np v;
    protected oq w;
    public Tp x;
    protected double y;
    protected double z;
    protected boolean A;
    protected boolean B;
    protected int C;
    protected Color Ba;
    protected Rectangle bounds;
    protected int Ta;
    protected boolean D;
    protected boolean E;
    protected int F;
    protected int G;
    public static final int H = 0;
    public static final int I = 1;
    public static final int J = 2;
    public static final int K = 0;
    public static final int L = 1;
    public static final int M = 2;
    protected int N;
    protected int O;
    protected int P;
    protected int Q;
    protected DecimalFormat R;
    protected DecimalFormat S;
    protected Xo T;
    protected Yo U;
    public Color V;
    public int W;
    public int X;
    public boolean Y;
    private boolean Z;
    
    public Zp() {
        this.r = -1;
        this.y = 0.0;
        this.z = 1.0;
        this.B = true;
        this.R = new DecimalFormat("#,##0.00");
        this.S = new DecimalFormat("#,##0.00");
        this.T = new Xo();
        this.U = new Yo();
        this.V = Color.lightGray;
        this.Y = true;
        this.Z = false;
        this.F = 0;
        this.G = 0;
        this.w = new pq();
        this.v = new Mp();
        this.x = new Tp();
        this.Ba = Color.black;
        this.C = 3;
        this.A = true;
        this.x.setFont(new Font("SansSerif", 0, 9));
        this.bounds = null;
        this.Ta = 9;
        this.D = true;
        this.E = true;
        this.P = 2;
        this.N = 2;
        this.Q = 0;
        this.O = 0;
    }
    
    public void j(final boolean z) {
        this.Z = z;
    }
    
    public boolean _(final boolean b) {
        return this.Z;
    }
    
    public int v() {
        return this.s;
    }
    
    public void n(final int n) {
        this.N = n;
    }
    
    public void c(final int p) {
        this.P = p;
    }
    
    public int w() {
        return this.F;
    }
    
    public void d(final int f) {
        if (this.F == f) {
            return;
        }
        if (f == 0) {
            this.F = f;
            this.w = new pq();
        }
        else if (f == 1) {
            this.F = f;
            this.w = new qq();
        }
        else if (f == 2) {
            this.F = f;
            this.w = new rq();
        }
    }
    
    public void e(final int g) {
        if (this.G == g) {
            return;
        }
        if (g == 0) {
            this.G = g;
            this.v = new Mp();
        }
        else if (g == 1) {
            this.G = g;
            this.v = new lq();
        }
        else if (g == 2) {
            this.G = g;
            this.v = new jq();
        }
    }
    
    public int x() {
        return this.G;
    }
    
    public void _(final int ta) {
        if (ta > 0) {
            this.Ta = ta;
        }
    }
    
    public void setBounds(final Rectangle bounds) {
        if (bounds == null) {
            this.bounds = new Rectangle(0, 0, 0, 0);
        }
        else {
            this.bounds = bounds;
            final Rectangle bounds2 = this.bounds;
            --bounds2.width;
            final Rectangle bounds3 = this.bounds;
            --bounds3.height;
        }
    }
    
    public void n(final String s) {
        this.T.c(s);
        this.U.c(s);
    }
    
    public void d(final String s) {
        if (this.F == 0) {
            this.R.applyPattern(s);
        }
        else if (this.F == 1) {
            this.T.c(s);
        }
        else if (this.F == 2) {
            this.U.c(s);
        }
    }
    
    public String a(final double n) {
        String s = "ERROR";
        if (this.F == 1) {
            s = this.T.b(n);
        }
        else if (this.F == 0) {
            s = this.S.format(n);
        }
        else if (this.F == 2) {
            this.U._(n);
            s = this.U.toString();
        }
        return s;
    }
    
    public String d(final double n) {
        String s = "ERROR";
        if (this.F == 1) {
            s = this.T.b(n);
        }
        else if (this.F == 0) {
            s = this.R.format(n);
        }
        else if (this.F == 2) {
            this.U._(n);
            s = this.U.toString();
        }
        return s;
    }
    
    public String e(final double n) {
        if (this.Z) {
            return this.d(n) + "%";
        }
        return this.d(n);
    }
    
    public Np a() {
        return this.v;
    }
    
    public double i() {
        return this.y;
    }
    
    public double j() {
        return this.z;
    }
    
    public void _(double n, double n2) {
        if (Math.abs(n2 - n) < 1.0E-6) {
            if (n == 0.0 || n == -0.0) {
                n = 0.0;
                n2 = 1.0;
            }
            else {
                if (n > 0.1) {
                    n -= 0.1;
                }
                else {
                    n -= 0.01;
                }
                n2 += 0.1;
            }
        }
        this.y = Math.min(n, n2);
        this.z = Math.max(n, n2);
        if (this.B && this.F == 0) {
            this.Q();
        }
    }
    
    public void setColor(final Color ba) {
        this.Ba = ba;
    }
    
    public abstract boolean a(final Graphics p0);
    
    public void f(final int c) {
        this.C = c;
    }
    
    public boolean _() {
        return this.A;
    }
    
    public void k(final boolean a) {
        this.A = a;
    }
    
    protected double k() {
        final int n = (this.Q == 0) ? this.P : 0;
        double pow;
        if (this.G == 1) {
            pow = Math.pow(10.0, lq.a(this.y) - Math.abs(lq.a(this.z) - lq.a(this.y)) * n / 100.0);
        }
        else {
            pow = this.y - Math.abs(this.z - this.y) * n / 100.0;
        }
        return pow;
    }
    
    protected double H() {
        return this.z + Math.abs(this.z - this.y) * ((this.O == 0) ? this.N : 0) / 100.0;
    }
    
    protected void Q() {
        final double n = (this.z - this.y) / this.Ta;
        int r;
        if (n >= 1.0) {
            r = 0;
        }
        else {
            r = (int)Math.ceil(Math.abs(lq.a(n)));
        }
        final int n2 = (int)Math.ceil(lq.a(Math.max(Math.abs(this.y), Math.abs(this.z))));
        if (this.r > 0 && this.r > r) {
            r = this.r;
        }
        this.R.setMaximumFractionDigits(r);
        this.R.setMinimumFractionDigits(r);
        this.R.setMaximumIntegerDigits(n2 + 1);
        this.R.setMinimumIntegerDigits(1);
        this.R.setGroupingSize(3);
        this.R.setGroupingUsed(true);
        this.S.setMaximumFractionDigits(r + 2);
        this.S.setMinimumFractionDigits(r);
        this.S.setMaximumIntegerDigits(n2 + 1);
        this.S.setMinimumIntegerDigits(1);
        this.S.setGroupingSize(3);
        this.S.setGroupingUsed(true);
    }
    
    public void l(final boolean b) {
        this.B = b;
    }
    
    public void g(final int r) {
        this.r = r;
    }
    
    public int y() {
        return this.r;
    }
    
    public void m(final boolean d) {
        this.D = d;
    }
    
    public void n(final boolean e) {
        this.E = e;
    }
    
    public void A(final int o) {
        this.O = o;
    }
    
    public void B(final int q) {
        this.Q = q;
    }
}
