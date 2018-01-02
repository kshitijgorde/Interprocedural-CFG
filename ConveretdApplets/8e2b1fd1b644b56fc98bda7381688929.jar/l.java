import java.awt.Graphics;
import java.awt.Font;
import java.text.DecimalFormat;
import java.awt.Rectangle;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class l
{
    protected int Jra;
    protected int Kra;
    public static final int Lra = 0;
    public static final int Mra = 1;
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    protected o Nra;
    protected do Ora;
    public g t;
    protected double Pra;
    protected double Qra;
    protected boolean Rra;
    protected boolean Sra;
    protected int Tra;
    protected Color T;
    protected Rectangle bounds;
    protected int Bqa;
    protected boolean Ura;
    protected boolean Vra;
    protected int Wra;
    protected int Xra;
    public static final int Yra = 0;
    public static final int Zra = 1;
    public static final int _sa = 2;
    public static final int asa = 0;
    public static final int bsa = 1;
    public static final int csa = 2;
    protected int dsa;
    protected int esa;
    protected int fsa;
    protected int gsa;
    protected DecimalFormat hsa;
    protected DecimalFormat isa;
    protected throw jsa;
    protected else ksa;
    public Color lsa;
    public int msa;
    public int nsa;
    public boolean osa;
    private boolean psa;
    
    public l() {
        this.Jra = -1;
        this.Pra = 0.0;
        this.Qra = 1.0;
        this.Sra = true;
        this.hsa = new DecimalFormat("#,##0.00");
        this.isa = new DecimalFormat("#,##0.00");
        this.jsa = new throw();
        this.ksa = new else();
        this.lsa = Color.lightGray;
        this.osa = true;
        this.psa = false;
        this.Wra = 0;
        this.Xra = 0;
        this.Ora = new extends();
        this.Nra = new catch();
        this.t = new g();
        this.T = Color.black;
        this.Tra = 3;
        this.Rra = true;
        this.t.setFont(new Font("SansSerif", 0, 9));
        this.bounds = null;
        this.Bqa = 9;
        this.Ura = true;
        this.Vra = true;
        this.fsa = 2;
        this.dsa = 2;
        this.gsa = 0;
        this.esa = 0;
    }
    
    public void c(final boolean psa) {
        this.psa = psa;
    }
    
    public boolean b(final boolean b) {
        return this.psa;
    }
    
    public int aa() {
        return this.Kra;
    }
    
    public void l(final int dsa) {
        this.dsa = dsa;
    }
    
    public void k(final int fsa) {
        this.fsa = fsa;
    }
    
    public int ba() {
        return this.Wra;
    }
    
    public void u(final int wra) {
        if (this.Wra == wra) {
            return;
        }
        if (wra == 0) {
            this.Wra = wra;
            this.Ora = new extends();
        }
        else if (wra == 1) {
            this.Wra = wra;
            this.Ora = new final();
        }
        else if (wra == 2) {
            this.Wra = wra;
            this.Ora = new finally();
        }
    }
    
    public void h(final int xra) {
        if (this.Xra == xra) {
            return;
        }
        if (xra == 0) {
            this.Xra = xra;
            this.Nra = new catch();
        }
        else if (xra == 1) {
            this.Xra = xra;
            this.Nra = new abstract();
        }
        else if (xra == 2) {
            this.Xra = xra;
            this.Nra = new u();
        }
    }
    
    public int ca() {
        return this.Xra;
    }
    
    public void D(final int bqa) {
        if (bqa > 0) {
            this.Bqa = bqa;
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
    
    public void k(final String s) {
        this.jsa.x(s);
        this.ksa.x(s);
    }
    
    public void y(final String s) {
        if (this.Wra == 0) {
            this.hsa.applyPattern(s);
        }
        else if (this.Wra == 1) {
            this.jsa.x(s);
        }
        else if (this.Wra == 2) {
            this.ksa.x(s);
        }
    }
    
    public String a(final double n) {
        String s = "ERROR";
        if (this.Wra == 1) {
            s = this.jsa.g(n);
        }
        else if (this.Wra == 0) {
            s = this.isa.format(n);
        }
        else if (this.Wra == 2) {
            this.ksa._(n);
            s = this.ksa.toString();
        }
        return s;
    }
    
    public String h(final double n) {
        String s = "ERROR";
        if (this.Wra == 1) {
            s = this.jsa.g(n);
        }
        else if (this.Wra == 0) {
            s = this.hsa.format(n);
        }
        else if (this.Wra == 2) {
            this.ksa._(n);
            s = this.ksa.toString();
        }
        return s;
    }
    
    public String i(final double n) {
        if (this.psa) {
            return this.h(n) + "%";
        }
        return this.h(n);
    }
    
    public o _() {
        return this.Nra;
    }
    
    public double h() {
        return this.Pra;
    }
    
    public double i() {
        return this.Qra;
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
        this.Pra = Math.min(n, n2);
        this.Qra = Math.max(n, n2);
        if (this.Sra && this.Wra == 0) {
            this.Y();
        }
    }
    
    public void setColor(final Color t) {
        this.T = t;
    }
    
    public abstract boolean a(final Graphics p0);
    
    public void M(final int tra) {
        this.Tra = tra;
    }
    
    public boolean h() {
        return this.Rra;
    }
    
    public void i(final boolean rra) {
        this.Rra = rra;
    }
    
    protected double j() {
        final int n = (this.gsa == 0) ? this.fsa : 0;
        double pow;
        if (this.Xra == 1) {
            pow = Math.pow(10.0, abstract.n(this.Pra) - Math.abs(abstract.n(this.Qra) - abstract.n(this.Pra)) * n / 100.0);
        }
        else {
            pow = this.Pra - Math.abs(this.Qra - this.Pra) * n / 100.0;
        }
        return pow;
    }
    
    protected double _a() {
        return this.Qra + Math.abs(this.Qra - this.Pra) * ((this.esa == 0) ? this.dsa : 0) / 100.0;
    }
    
    protected void Y() {
        final double n = (this.Qra - this.Pra) / this.Bqa;
        int jra;
        if (n >= 1.0) {
            jra = 0;
        }
        else {
            jra = (int)Math.ceil(Math.abs(abstract.n(n)));
        }
        final int n2 = (int)Math.ceil(abstract.n(Math.max(Math.abs(this.Pra), Math.abs(this.Qra))));
        if (this.Jra > 0 && this.Jra > jra) {
            jra = this.Jra;
        }
        this.hsa.setMaximumFractionDigits(jra);
        this.hsa.setMinimumFractionDigits(jra);
        this.hsa.setMaximumIntegerDigits(n2 + 1);
        this.hsa.setMinimumIntegerDigits(1);
        this.hsa.setGroupingSize(3);
        this.hsa.setGroupingUsed(true);
        this.isa.setMaximumFractionDigits(jra + 2);
        this.isa.setMinimumFractionDigits(jra);
        this.isa.setMaximumIntegerDigits(n2 + 1);
        this.isa.setMinimumIntegerDigits(1);
        this.isa.setGroupingSize(3);
        this.isa.setGroupingUsed(true);
    }
    
    public void G(final boolean sra) {
        this.Sra = sra;
    }
    
    public void m(final int jra) {
        this.Jra = jra;
    }
    
    public int da() {
        return this.Jra;
    }
    
    public void H(final boolean ura) {
        this.Ura = ura;
    }
    
    public void I(final boolean vra) {
        this.Vra = vra;
    }
    
    public void N(final int esa) {
        this.esa = esa;
    }
    
    public void O(final int gsa) {
        this.gsa = gsa;
    }
}
