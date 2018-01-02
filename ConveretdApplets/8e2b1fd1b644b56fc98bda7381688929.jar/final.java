// 
// Decompiled by Procyon v0.5.30
// 

public class final implements do
{
    private static final int Fqa = 0;
    private static final int MONTH = 1;
    private static final int YEAR = 2;
    private double[] zqa;
    private int Aqa;
    private int Bqa;
    private double Tpa;
    private double Cqa;
    private boolean Dqa;
    throw Gqa;
    
    public final() {
        this.Gqa = new throw();
        this.Dqa = false;
        this.Bqa = 1;
    }
    
    public final(final double n, final double n2, final int bqa) {
        this.Gqa = new throw();
        if (n2 >= n) {
            this.Tpa = n;
            this.Cqa = n2;
        }
        else {
            this.Tpa = n2;
            this.Cqa = n;
        }
        this.Bqa = bqa;
        if (this.Bqa <= 0) {
            this.Bqa = 1;
        }
        this.Dqa = false;
    }
    
    public void _(final double n, final double n2) {
        if (n2 >= n) {
            this.Tpa = n;
            this.Cqa = n2;
        }
        else {
            this.Tpa = n2;
            this.Cqa = n;
        }
        this.Dqa = false;
    }
    
    public void D(final int bqa) {
        this.Bqa = bqa;
        if (this.Bqa <= 0) {
            this.Bqa = 1;
        }
        this.Dqa = false;
    }
    
    public int R() {
        if (!this.Dqa) {
            this.O();
        }
        return this.Aqa;
    }
    
    public double _(final int n) {
        if (!this.Dqa) {
            this.O();
        }
        if (n < 0 || n >= this.Aqa) {
            return 0.0;
        }
        return this.zqa[n];
    }
    
    public double d() {
        return this.Tpa;
    }
    
    public double e() {
        return this.Cqa;
    }
    
    private void O() {
        if (this.Cqa == this.Tpa || this.Bqa < 2) {
            if (this.zqa == null || this.zqa.length < this.Bqa) {
                this.zqa = new double[this.Bqa];
            }
            this.zqa[0] = this.Tpa + (this.Cqa - this.Tpa) / 2.0;
            this.Aqa = 1;
            this.Dqa = true;
            return;
        }
        this.Aqa = this.Bqa;
        if (this.zqa == null || this.zqa.length < this.Bqa) {
            this.zqa = new double[this.Bqa];
        }
        this.Gqa._(this.Cqa);
        final int s = this.Gqa.S();
        final int t = this.Gqa.T();
        this.Gqa._(this.Tpa);
        final int n = s - this.Gqa.S();
        final int n2 = t - this.Gqa.T();
        int n3 = (int)Math.ceil(Math.abs(this.Gqa._(this.Tpa, this.Cqa)) / this.Bqa);
        if (n3 < 1) {
            n3 = 1;
        }
        int n4;
        if (n3 > 182 && n != 0) {
            n4 = 2;
        }
        else if ((n3 >= 10 && n2 != 0) || n2 > 2) {
            n4 = 1;
        }
        else {
            n4 = 0;
        }
        this.Gqa._(this.Tpa);
        this.Aqa = 0;
        for (int i = 0; i < this.Bqa; ++i) {
            if (n4 == 2) {
                this.Gqa.Q();
            }
            else if (n4 == 1) {
                this.Gqa.R();
            }
            this.zqa[i] = this.Gqa.l();
            if (this.zqa[i] > this.Cqa) {
                break;
            }
            ++this.Aqa;
            this.Gqa._(this.Gqa.l() + n3);
        }
        this.Dqa = true;
    }
}
