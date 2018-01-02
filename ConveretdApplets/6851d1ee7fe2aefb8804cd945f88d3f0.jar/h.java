// 
// Decompiled by Procyon v0.5.30
// 

public class h implements e
{
    private static final int Mka = 0;
    private static final int MONTH = 1;
    private static final int YEAR = 2;
    private double[] Gka;
    private int Hka;
    private int Ika;
    private double cja;
    private double Jka;
    private boolean Kka;
    r Nka;
    
    public h() {
        this.Nka = new r();
        this.Kka = false;
        this.Ika = 1;
    }
    
    public h(final double n, final double n2, final int ika) {
        this.Nka = new r();
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
        if (this.Gka == null || this.Gka.length < this.Ika) {
            this.Gka = new double[this.Ika];
        }
        this.Nka._(this.Jka);
        final int o = this.Nka.o();
        final int p = this.Nka.p();
        this.Nka._(this.cja);
        final int n = o - this.Nka.o();
        final int n2 = p - this.Nka.p();
        int n3 = (int)Math.ceil(Math.abs(this.Nka.a(this.cja, this.Jka)) / this.Ika);
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
        this.Nka._(this.cja);
        this.Hka = 0;
        for (int i = 0; i < this.Ika; ++i) {
            if (n4 == 2) {
                this.Nka.ha();
            }
            else if (n4 == 1) {
                this.Nka.ia();
            }
            this.Gka[i] = this.Nka._();
            if (this.Gka[i] > this.Jka) {
                break;
            }
            ++this.Hka;
            this.Nka._(this.Nka._() + n3);
        }
        this.Kka = true;
    }
}
