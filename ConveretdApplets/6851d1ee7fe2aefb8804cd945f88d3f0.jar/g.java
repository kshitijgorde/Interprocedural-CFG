// 
// Decompiled by Procyon v0.5.30
// 

public class g implements e
{
    private double[] Gka;
    private int Hka;
    private int Ika;
    private double cja;
    private double Jka;
    private boolean Kka;
    
    public g() {
        this.Kka = false;
        this.Ika = 1;
    }
    
    public g(final double n, final double n2, final int ika) {
        if (n2 >= n) {
            this.cja = n;
            this.Jka = n2;
        }
        else {
            this.cja = n2;
            this.Jka = n;
        }
        this.Ika = ika;
        if (this.Ika <= 0) {
            this.Ika = 1;
        }
        this.Kka = false;
    }
    
    public void j(final double n, final double n2) {
        if (n2 >= n) {
            this.cja = n;
            this.Jka = n2;
        }
        else {
            this.cja = n2;
            this.Jka = n;
        }
        this.Kka = false;
    }
    
    public void r(final int ika) {
        this.Ika = ika;
        if (this.Ika <= 0) {
            this.Ika = 1;
        }
        this.Kka = false;
    }
    
    public int ia() {
        if (!this.Kka) {
            this.fa();
        }
        return this.Hka;
    }
    
    public double a(final int n) {
        if (!this.Kka) {
            this.fa();
        }
        if (n < 0 || n >= this.Hka) {
            return 0.0;
        }
        return this.Gka[n];
    }
    
    public double b() {
        return this.cja;
    }
    
    public double j() {
        return this.Jka;
    }
    
    private void fa() {
        if (this.Jka == this.cja || this.Ika < 2) {
            if (this.Gka == null || this.Gka.length < this.Ika) {
                this.Gka = new double[this.Ika];
            }
            this.Gka[0] = this.cja + (this.Jka - this.cja) / 2.0;
            this.Hka = 1;
            this.Kka = true;
            return;
        }
        this.Hka = this.Ika;
        final double n = (this.Jka - this.cja) / this.Ika;
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
        if (this.Gka == null || this.Gka.length < this.Ika) {
            this.Gka = new double[this.Ika];
        }
        long n5 = (long)Math.ceil(this.cja / n4);
        for (int i = 0; i < this.Ika; ++i, ++n5) {
            this.Gka[i] = n4 * n5;
            if (this.Gka[i] > this.Jka) {
                break;
            }
            this.Hka = i + 1;
        }
        this.Kka = true;
    }
}
