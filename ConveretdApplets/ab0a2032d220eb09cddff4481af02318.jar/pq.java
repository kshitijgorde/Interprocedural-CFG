// 
// Decompiled by Procyon v0.5.30
// 

public class pq implements oq
{
    private double[] Ra;
    private int Sa;
    private int Ta;
    private double Ua;
    private double Va;
    private boolean Wa;
    
    public pq() {
        this.Wa = false;
        this.Ta = 1;
    }
    
    public pq(final double n, final double n2, final int ta) {
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
        final double n = (this.Va - this.Ua) / this.Ta;
        final double pow = Math.pow(10.0, Math.floor(Math.log(n) / Math.log(10.0)));
        final double pow2 = Math.pow(10.0, Math.ceil(Math.log(n) / Math.log(10.0)));
        double n2 = pow;
        final int[] array = { 1, 2, 5 };
        for (int n3 = 0; n3 < array.length && n2 < n; ++n3) {
            n2 = array[n3] * pow;
            if (n2 * 1.1 >= n) {
                break;
            }
        }
        if (n2 < n && n2 * 1.1 < n) {
            n2 = pow2;
        }
        final double n4 = n2;
        if (this.Ra == null || this.Ra.length < this.Ta) {
            this.Ra = new double[this.Ta];
        }
        long n5 = (long)Math.ceil(this.Ua / n4);
        for (int i = 0; i < this.Ta; ++i, ++n5) {
            this.Ra[i] = n4 * n5;
            if (this.Ra[i] > this.Va) {
                break;
            }
            this.Sa = i + 1;
        }
        this.Wa = true;
    }
}
