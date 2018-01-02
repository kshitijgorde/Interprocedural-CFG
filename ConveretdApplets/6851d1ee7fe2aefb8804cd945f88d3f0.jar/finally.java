import java.awt.Graphics;
import java.awt.Font;
import java.text.DecimalFormat;
import java.awt.Rectangle;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class finally
{
    protected int Xma;
    protected int Gma;
    public static final int yma = 0;
    public static final int zma = 1;
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    protected throws Hma;
    protected e Oma;
    public goto Pma;
    protected double Ima;
    protected double Jma;
    protected boolean Yma;
    protected boolean Kma;
    protected int Rma;
    protected Color xa;
    protected Rectangle bounds;
    protected int Ika;
    protected boolean Sma;
    protected boolean Qma;
    protected int Lma;
    protected int Zma;
    public static final int _na = 0;
    public static final int ana = 1;
    public static final int bna = 2;
    public static final int cna = 0;
    public static final int dna = 1;
    public static final int ena = 2;
    protected int fna;
    protected int Mma;
    protected int gna;
    protected int Nma;
    protected DecimalFormat hna;
    protected DecimalFormat ina;
    protected r jna;
    protected f kna;
    public Color Uma;
    public int Vma;
    public int Wma;
    public boolean Tma;
    private boolean lna;
    
    public finally() {
        this.Xma = -1;
        this.Ima = 0.0;
        this.Jma = 1.0;
        this.Kma = true;
        this.hna = new DecimalFormat("#,##0.00");
        this.ina = new DecimalFormat("#,##0.00");
        this.jna = new r();
        this.kna = new f();
        this.Uma = Color.lightGray;
        this.Tma = true;
        this.lna = false;
        this.Lma = 0;
        this.Zma = 0;
        this.Oma = new g();
        this.Hma = new var();
        this.Pma = new goto();
        this.xa = Color.black;
        this.Rma = 3;
        this.Yma = true;
        this.Pma.setFont(new Font("SansSerif", 0, 9));
        this.bounds = null;
        this.Ika = 9;
        this.Sma = true;
        this.Qma = true;
        this.gna = 2;
        this.fna = 2;
        this.Nma = 0;
        this.Mma = 0;
    }
    
    public void m(final boolean lna) {
        this.lna = lna;
    }
    
    public boolean _(final boolean b) {
        return this.lna;
    }
    
    public int G() {
        return this.Gma;
    }
    
    public void B(final int fna) {
        this.fna = fna;
    }
    
    public void C(final int gna) {
        this.gna = gna;
    }
    
    public int H() {
        return this.Lma;
    }
    
    public void D(final int lma) {
        if (this.Lma == lma) {
            return;
        }
        if (lma == 0) {
            this.Lma = lma;
            this.Oma = new g();
        }
        else if (lma == 1) {
            this.Lma = lma;
            this.Oma = new h();
        }
        else if (lma == 2) {
            this.Lma = lma;
            this.Oma = new i();
        }
    }
    
    public void E(final int zma) {
        if (this.Zma == zma) {
            return;
        }
        if (zma == 0) {
            this.Zma = zma;
            this.Hma = new var();
        }
        else if (zma == 1) {
            this.Zma = zma;
            this.Hma = new d();
        }
        else if (zma == 2) {
            this.Zma = zma;
            this.Hma = new try();
        }
    }
    
    public int I() {
        return this.Zma;
    }
    
    public void r(final int ika) {
        if (ika > 0) {
            this.Ika = ika;
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
    
    public void z(final String s) {
        this.jna.x(s);
        this.kna.x(s);
    }
    
    public void A(final String s) {
        if (this.Lma == 0) {
            this.hna.applyPattern(s);
        }
        else if (this.Lma == 1) {
            this.jna.x(s);
        }
        else if (this.Lma == 2) {
            this.kna.x(s);
        }
    }
    
    public String e(final double n) {
        String s = "ERROR";
        if (this.Lma == 1) {
            s = this.jna.f(n);
        }
        else if (this.Lma == 0) {
            s = this.ina.format(n);
        }
        else if (this.Lma == 2) {
            this.kna._(n);
            s = this.kna.toString();
        }
        return s;
    }
    
    public String g(final double n) {
        String s = "ERROR";
        if (this.Lma == 1) {
            s = this.jna.f(n);
        }
        else if (this.Lma == 0) {
            s = this.hna.format(n);
        }
        else if (this.Lma == 2) {
            this.kna._(n);
            s = this.kna.toString();
        }
        return s;
    }
    
    public String _(final double n) {
        if (this.lna) {
            return this.g(n) + "%";
        }
        return this.g(n);
    }
    
    public throws b() {
        return this.Hma;
    }
    
    public double i() {
        return this.Ima;
    }
    
    public double S() {
        return this.Jma;
    }
    
    public void j(double n, double n2) {
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
        this.Ima = Math.min(n, n2);
        this.Jma = Math.max(n, n2);
        if (this.Kma && this.Lma == 0) {
            this.t();
        }
    }
    
    public void setColor(final Color xa) {
        this.xa = xa;
    }
    
    public abstract boolean a(final Graphics p0);
    
    public void F(final int rma) {
        this.Rma = rma;
    }
    
    public boolean Q() {
        return this.Yma;
    }
    
    public void n(final boolean yma) {
        this.Yma = yma;
    }
    
    protected double g() {
        final int n = (this.Nma == 0) ? this.gna : 0;
        double pow;
        if (this.Zma == 1) {
            pow = Math.pow(10.0, d.l(this.Ima) - Math.abs(d.l(this.Jma) - d.l(this.Ima)) * n / 100.0);
        }
        else {
            pow = this.Ima - Math.abs(this.Jma - this.Ima) * n / 100.0;
        }
        return pow;
    }
    
    protected double h() {
        return this.Jma + Math.abs(this.Jma - this.Ima) * ((this.Mma == 0) ? this.fna : 0) / 100.0;
    }
    
    protected void t() {
        final double n = (this.Jma - this.Ima) / this.Ika;
        int xma;
        if (n >= 1.0) {
            xma = 0;
        }
        else {
            xma = (int)Math.ceil(Math.abs(d.l(n)));
        }
        final int n2 = (int)Math.ceil(d.l(Math.max(Math.abs(this.Ima), Math.abs(this.Jma))));
        if (this.Xma > 0 && this.Xma > xma) {
            xma = this.Xma;
        }
        this.hna.setMaximumFractionDigits(xma);
        this.hna.setMinimumFractionDigits(xma);
        this.hna.setMaximumIntegerDigits(n2 + 1);
        this.hna.setMinimumIntegerDigits(1);
        this.hna.setGroupingSize(3);
        this.hna.setGroupingUsed(true);
        this.ina.setMaximumFractionDigits(xma + 2);
        this.ina.setMinimumFractionDigits(xma);
        this.ina.setMaximumIntegerDigits(n2 + 1);
        this.ina.setMinimumIntegerDigits(1);
        this.ina.setGroupingSize(3);
        this.ina.setGroupingUsed(true);
    }
    
    public void c(final boolean kma) {
        this.Kma = kma;
    }
    
    public void G(final int xma) {
        this.Xma = xma;
    }
    
    public int J() {
        return this.Xma;
    }
    
    public void d(final boolean sma) {
        this.Sma = sma;
    }
    
    public void e(final boolean qma) {
        this.Qma = qma;
    }
    
    public void H(final int mma) {
        this.Mma = mma;
    }
    
    public void I(final int nma) {
        this.Nma = nma;
    }
    
    public int K() {
        return this.Mma;
    }
    
    public int L() {
        return this.Nma;
    }
}
