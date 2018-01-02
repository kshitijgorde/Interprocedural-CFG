// 
// Decompiled by Procyon v0.5.30
// 

public class extends implements do
{
    private double[] zqa;
    private int Aqa;
    private int Bqa;
    private double Tpa;
    private double Cqa;
    private boolean Dqa;
    
    public extends() {
        this.Dqa = false;
        this.Bqa = 1;
    }
    
    public extends(final double n, final double n2, final int bqa) {
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
        final double n = (this.Cqa - this.Tpa) / this.Bqa;
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
        if (this.zqa == null || this.zqa.length < this.Bqa) {
            this.zqa = new double[this.Bqa];
        }
        long n5 = (long)Math.ceil(this.Tpa / n4);
        for (int i = 0; i < this.Bqa; ++i, ++n5) {
            this.zqa[i] = n4 * n5;
            if (this.zqa[i] > this.Cqa) {
                break;
            }
            this.Aqa = i + 1;
        }
        this.Dqa = true;
    }
}
