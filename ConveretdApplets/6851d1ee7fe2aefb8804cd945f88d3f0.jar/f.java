// 
// Decompiled by Procyon v0.5.30
// 

public class f
{
    private static final int Oka = 86400000;
    private int Pka;
    private int Qka;
    private boolean Rka;
    private j time;
    private r Nka;
    
    static int _(final double n) {
        if (n < 0.0) {
            throw new IllegalArgumentException("value must be >= 0");
        }
        return (int)Math.round((n - (int)n) * 8.64E7);
    }
    
    static double b(final int n) {
        return n / 8.64E7;
    }
    
    public synchronized void a(final int[] array) {
        if (array == null || array.length != 2) {
            this.Pka = 32400000;
            this.Qka = 64800000;
            return;
        }
        this.Pka = array[0];
        this.Qka = array[1];
    }
    
    public synchronized int[] b(final double[] array) {
        this.Pka = 32400000;
        this.Qka = 64800000;
        if (array == null || array.length == 0) {
            return new int[] { this.Pka, this.Qka };
        }
        this.Pka = _(array[0]);
        this.Qka = this.Pka;
        for (int i = 1; i < array.length; ++i) {
            this.Pka = Math.min(this.Pka, _(array[i]));
            this.Qka = Math.max(this.Qka, _(array[i]));
        }
        final j j = new j(this.Pka);
        final int c = j.c();
        int e = j.e();
        if (e < 30) {
            e = 0;
        }
        else if (e <= 59) {
            e = 30;
        }
        j.a(c, e, 0, 0);
        this.Pka = (int)j._();
        j._(this.Qka);
        if (j.f() == 0 && j.d() > 0) {
            j.k(1);
        }
        j.ba();
        j.c();
        final int e2 = j.e();
        if (e2 < 30) {
            j.q(30);
        }
        else if (e2 > 30) {
            j.aa();
        }
        j.a(j.c(), j.e(), 0, 0);
        this.Qka = (int)j._();
        if (this.Pka + 3600000 >= this.Qka) {
            this.Qka = this.Pka + 3600000 + 1800000;
        }
        if (this.Qka >= 86400000) {
            this.Qka = 86399999;
        }
        return new int[] { this.Pka, this.Qka };
    }
    
    synchronized double a() {
        return this.Qka - this.Pka;
    }
    
    public f() {
        this.Pka = 32400000;
        this.Qka = 64800000;
        this.Rka = false;
        this.Nka = new r();
        this.time = new j(this.Pka);
    }
    
    public f(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        this(n, n2, n3, n4, n5, n6, 0);
    }
    
    public f(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        this.Pka = 32400000;
        this.Qka = 64800000;
        this.Rka = false;
        this.Nka = new r(n, n2, n3);
        this.Rka = ((n != 0 || n2 != 0 || n3 != 0) && this.Nka._() < Double.MIN_VALUE);
        final long n8 = n4 * 60 * 60 * 1000 + n5 * 60 * 1000 + n6 * 1000 + n7;
        if (n8 < 0L) {
            this.time = new j(0, 0, 0, 0);
        }
        else if (n8 >= 86400000L) {
            this.time = new j(23, 59, 59, 999);
        }
        else {
            this.time = new j(n4, n5, n6, n7);
        }
    }
    
    public synchronized void _(final double n) {
        final double n2 = (int)n;
        this.Nka._(n2);
        this.Rka = (n2 < 0.0 || (n2 > 0.0 && this.Nka._() < Double.MIN_VALUE));
        this.time._(_(n));
    }
    
    public synchronized void set(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        this.b(n, n2, n3, n4, n5, n6, 0);
    }
    
    public synchronized void b(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        this.Nka.set(n, n2, n3);
        this.time.a(n4, n5, n6, n7);
        this.Rka = ((n != 0 || n2 != 0 || n3 != 0) && this.Nka._() < Double.MIN_VALUE);
    }
    
    public synchronized void set(final int n, final int n2, final int n3) {
        this.a(n, n2, n3, 0);
    }
    
    public synchronized void a(final int n, final int n2, final int n3, final int n4) {
        this.Nka._(0.0);
        this.time.a(n, n2, n3, n4);
    }
    
    public void x(final String s) {
        this.Nka.x(s);
    }
    
    public synchronized double _() {
        if (this.Rka) {
            return 0.0;
        }
        return this.Nka._() + b((int)this.time._());
    }
    
    public synchronized String toString() {
        String string;
        if (this.Nka._() < 1.0) {
            string = "";
        }
        else {
            string = this.Nka.toString();
        }
        if (Math.round(this.time._()) == this.Pka) {
            return string;
        }
        return string + "\n" + this.time.toString();
    }
    
    public synchronized long _(double n, double n2) {
        int n3 = 1;
        if (n2 < n) {
            n3 = -1;
            final double n4 = n2;
            n2 = n;
            n = n4;
        }
        final long n5 = (long)n2 - (long)n;
        final double n6 = _(n);
        final double n7 = _(n2);
        double n8 = 0.0;
        if (n5 > 0L) {
            n8 = (n5 - 1L) * this.a();
        }
        double n9;
        if (n5 != 0L) {
            n9 = n8 + (this.Qka - n6 + (n7 - this.Pka));
        }
        else {
            n9 = n8 + (n7 - n6);
        }
        return Math.round(n9 * n3);
    }
    
    public synchronized long l() {
        return (long)this.Nka._() * 24L * 60L * 60L + this.time.ha();
    }
    
    public int q() {
        return this.time.ha();
    }
    
    public synchronized void t(final int n) {
        this.Nka._(this.Nka._() + n);
    }
    
    public synchronized void s(int n) {
        final int n2 = (int)this.time._();
        if (n2 + n > this.Qka) {
            n -= this.Qka - n2;
            this.Nka._(this.Nka._() + 1.0);
            this.time._(this.Pka);
        }
        final int n3 = (int)this.a();
        final int n4 = n / n3;
        final int n5 = n % n3;
        this.Nka._(this.Nka._() + n4);
        this.time._(this.time._() + n5);
    }
    
    public synchronized void k(final int n) {
        this.s(n * 1000);
    }
    
    public synchronized void l(final int n) {
        this.k(n * 60);
    }
    
    public synchronized void p(final int n) {
        this.k(n * 60 * 60);
    }
    
    public synchronized void ga() {
        if (Math.round(this.time._()) != this.Pka) {
            this.Nka._(this.Nka._() + 1.0);
            this.time._(this.Pka);
        }
    }
    
    public synchronized void aa() {
        this.time.a(this.time.c(), this.time.e(), this.time.f(), 0);
        if (this.time.e() != 0 || this.time.f() != 0) {
            final int n = this.Qka / 3600000;
            final int n2 = this.Pka / 3600000;
            if (this.time.c() < n) {
                this.time.set(this.time.c() + 1, 0, 0);
            }
            else {
                this.Nka._(this.Nka._() + 1.0);
                this.time.set(n2, 0, 0);
                if (this.time._() < this.Pka) {
                    this.time.set(this.time.c() + 1, 0, 0);
                }
            }
        }
    }
    
    public synchronized void ca() {
        this.ba();
        while (this.time.e() % 5 != 0) {
            this.l(1);
        }
    }
    
    public synchronized void u(final int n) {
        final int n2 = (int)this.Nka._();
        if (n >= 60) {
            this.q(n / 60);
            return;
        }
        this.da();
        if (n == 0) {
            return;
        }
        while (this.time.f() % n != 0) {
            this.k((this.time.f() / n + 1) * n - this.time.f());
            if (n2 != (int)this.Nka._()) {
                this.Nka._(n2);
                this.time._(this.Qka);
                break;
            }
        }
    }
    
    public synchronized void q(final int n) {
        final int n2 = (int)this.Nka._();
        this.ba();
        if (n == 0) {
            return;
        }
        while (this.time.e() % n != 0) {
            this.l((this.time.e() / n + 1) * n - this.time.e());
            if (n2 != (int)this.Nka._()) {
                this.Nka._(n2);
                this.time._(this.Qka);
                break;
            }
        }
    }
    
    public synchronized void ea() {
        this.da();
        while (this.time.f() % 5 != 0) {
            this.k(1);
        }
    }
    
    public synchronized void da() {
        this.time.a(this.time.c(), this.time.e(), this.time.f(), 0);
    }
    
    public synchronized void ba() {
        this.time.a(this.time.c(), this.time.e(), this.time.f(), 0);
        if (this.time.f() != 0) {
            if (this.time.e() < 59) {
                this.time.a(this.time.c(), this.time.e() + 1, 0, 0);
                if (this.time._() > this.Qka) {
                    this.Nka._(this.Nka._() + 1.0);
                    this.time._(this.Pka);
                    if (this.time.f() != 0) {
                        if (this.time.e() < 59) {
                            this.time.a(this.time.c(), this.time.e() + 1, 0, 0);
                        }
                        else {
                            this.time.a(this.time.c() + 1, 0, 0, 0);
                        }
                    }
                }
            }
            else if (this.time.c() < 23) {
                this.time.a(this.time.c() + 1, 0, 0, 0);
                if (this.time._() > this.Qka) {
                    this.Nka._(this.Nka._() + 1.0);
                    this.time._(this.Pka);
                    if (this.time.f() != 0) {
                        if (this.time.e() < 59) {
                            this.time.a(this.time.c(), this.time.e() + 1, 0, 0);
                        }
                        else {
                            this.time.a(this.time.c() + 1, 0, 0, 0);
                        }
                    }
                }
            }
            else {
                this.Nka._(this.Nka._() + 1.0);
                this.time._(this.Pka);
                if (this.time.f() != 0) {
                    if (this.time.e() < 59) {
                        this.time.a(this.time.c(), this.time.e() + 1, 0, 0);
                    }
                    else {
                        this.time.a(this.time.c() + 1, 0, 0, 0);
                    }
                }
            }
        }
    }
}
