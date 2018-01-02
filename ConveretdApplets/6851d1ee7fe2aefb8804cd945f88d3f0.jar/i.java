// 
// Decompiled by Procyon v0.5.30
// 

public class i implements e
{
    private static final int Bka = 0;
    private static final int Cka = 1;
    private static final int Dka = 2;
    private static final int Eka = 3;
    private static final int Fka = 4;
    private double[] Gka;
    private int Hka;
    private int Ika;
    private double cja;
    private double Jka;
    private boolean Kka;
    private f Lka;
    
    public i() {
        this.Lka = new f();
        this.Kka = false;
        this.Ika = 1;
    }
    
    public i(final double n, final double n2, final int ika) {
        this.Lka = new f();
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
    
    public int[] a(final double[] array) {
        return this.Lka.b(array);
    }
    
    private void fa() {
        if (this.Jka == this.cja || this.Ika < 2) {
            if (this.Gka == null || this.Gka.length < this.Ika) {
                this.Gka = new double[this.Ika];
            }
            this.Gka[0] = this.cja + (this.Jka - this.cja) / 2.0;
            if (this.Jka - this.cja > 2.0) {
                this.Gka[0] = Math.round(this.Gka[0]);
            }
            this.Hka = 1;
            this.Kka = true;
            return;
        }
        this.Hka = this.Ika;
        if (this.Gka == null || this.Gka.length < this.Ika) {
            this.Gka = new double[this.Ika];
        }
        final double n = this.Lka._(this.cja, this.Jka);
        this.Lka._(this.cja);
        if ((int)n <= this.Hka) {
            this.Hka = (int)n + 1;
            if (this.Hka > this.Ika) {
                this.Hka = this.Ika;
            }
            for (int i = 0; i < this.Hka; ++i) {
                this.Gka[i] = this.Lka._();
                if (this.Gka[i] > this.Jka) {
                    break;
                }
                this.Hka = i + 1;
                this.Lka.s(1);
            }
            this.Kka = true;
            return;
        }
        final double ceil = Math.ceil(n / this.Ika);
        int n2;
        int n3;
        if (ceil >= this.Lka.a() || this.Jka - this.cja > 2.0) {
            n2 = (int)Math.ceil(ceil / this.Lka.a());
            n3 = 3;
        }
        else if (ceil >= 3600000.0) {
            n2 = (int)Math.ceil(ceil / 3600000.0);
            n3 = 2;
        }
        else if (ceil >= 60000.0) {
            n2 = (int)Math.ceil(ceil / 60000.0);
            n3 = 1;
        }
        else if (ceil >= 1000.0) {
            n2 = (int)Math.ceil(ceil / 1000.0);
            n3 = 0;
        }
        else {
            n2 = (int)Math.ceil(ceil);
            n3 = 4;
        }
        this.Lka._(this.cja);
        this.Lka.s(1);
        switch (n3) {
            case 3: {
                this.Lka.ga();
                break;
            }
            case 2: {
                this.Lka.aa();
                break;
            }
            case 1: {
                if (n2 >= 5) {
                    this.Lka.ca();
                    while (n2 % 5 > 0) {
                        ++n2;
                    }
                    break;
                }
                this.Lka.ba();
                break;
            }
            case 0: {
                this.Lka.da();
                if (n2 >= 5) {
                    this.Lka.ea();
                    while (n2 % 5 > 0) {
                        ++n2;
                    }
                    break;
                }
                break;
            }
        }
        this.Hka = 0;
        for (int j = 0; j < this.Ika; ++j) {
            this.Gka[j] = this.Lka._();
            if (this.Gka[j] > this.Jka) {
                break;
            }
            this.Hka = j + 1;
            switch (n3) {
                case 3: {
                    this.Lka.t(n2);
                    break;
                }
                case 2: {
                    this.Lka.p(n2);
                    break;
                }
                case 1: {
                    this.Lka.l(n2);
                    break;
                }
                case 0: {
                    this.Lka.k(n2);
                    break;
                }
                case 4: {
                    this.Lka.s(n2);
                    break;
                }
            }
        }
        this.Kka = true;
    }
}
