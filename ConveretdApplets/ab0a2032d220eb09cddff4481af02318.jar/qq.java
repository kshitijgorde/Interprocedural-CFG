// 
// Decompiled by Procyon v0.5.30
// 

public class qq implements oq
{
    private static final int Ya = 0;
    private static final int MONTH = 1;
    private static final int YEAR = 2;
    private double[] Ra;
    private int Sa;
    private int Ta;
    private double Ua;
    private double Va;
    private boolean Wa;
    Xo Za;
    
    public qq() {
        this.Za = new Xo();
        this.Wa = false;
        this.Ta = 1;
    }
    
    public qq(final double n, final double n2, final int ta) {
        this.Za = new Xo();
        if (n2 >= n) {
            this.Ua = n;
            this.Va = n2;
        }
        else {
            this.Ua = n2;
            this.Va = n;
        }
        this.Ta = ta;
        if (this.Ta <= 0) {
            this.Ta = 1;
        }
        this.Wa = false;
    }
    
    public void _(final double n, final double n2) {
        if (n2 >= n) {
            this.Ua = n;
            this.Va = n2;
        }
        else {
            this.Ua = n2;
            this.Va = n;
        }
        this.Wa = false;
    }
    
    public void _(final int ta) {
        this.Ta = ta;
        if (this.Ta <= 0) {
            this.Ta = 1;
        }
        this.Wa = false;
    }
    
    public int k() {
        if (!this.Wa) {
            this.j();
        }
        return this.Sa;
    }
    
    public double _(final int n) {
        if (!this.Wa) {
            this.j();
        }
        if (n < 0 || n >= this.Sa) {
            return 0.0;
        }
        return this.Ra[n];
    }
    
    public double c() {
        return this.Ua;
    }
    
    public double d() {
        return this.Va;
    }
    
    private void j() {
        if (this.Va == this.Ua || this.Ta < 2) {
            if (this.Ra == null || this.Ra.length < this.Ta) {
                this.Ra = new double[this.Ta];
            }
            this.Ra[0] = this.Ua + (this.Va - this.Ua) / 2.0;
            this.Sa = 1;
            this.Wa = true;
            return;
        }
        this.Sa = this.Ta;
        if (this.Ra == null || this.Ra.length < this.Ta) {
            this.Ra = new double[this.Ta];
        }
        this.Za._(this.Va);
        final int l = this.Za.l();
        final int m = this.Za.m();
        this.Za._(this.Ua);
        final int n = l - this.Za.l();
        final int n2 = m - this.Za.m();
        int n3 = (int)Math.ceil(Math.abs(this.Za._(this.Ua, this.Va)) / this.Ta);
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
        this.Za._(this.Ua);
        this.Sa = 0;
        for (int i = 0; i < this.Ta; ++i) {
            if (n4 == 2) {
                this.Za.e();
            }
            else if (n4 == 1) {
                this.Za.f();
            }
            this.Ra[i] = this.Za.m();
            if (this.Ra[i] > this.Va) {
                break;
            }
            ++this.Sa;
            this.Za._(this.Za.m() + n3);
        }
        this.Wa = true;
    }
}
