// 
// Decompiled by Procyon v0.5.30
// 

public class Yo
{
    private static final int MHb = 86400000;
    private int NHb;
    private int OHb;
    private boolean PHb;
    private ap time;
    private Xo Za;
    
    static int _(final double n) {
        if (n < 0.0) {
            throw new IllegalArgumentException("value must be >= 0");
        }
        return (int)Math.round((n - (int)n) * 8.64E7);
    }
    
    static double g(final int n) {
        return n / 8.64E7;
    }
    
    public synchronized void b(final int[] array) {
        if (array == null || array.length != 2) {
            this.NHb = 32400000;
            this.OHb = 64800000;
            return;
        }
        this.NHb = array[0];
        this.OHb = array[1];
    }
    
    public synchronized int[] a(final double[] array) {
        this.NHb = 32400000;
        this.OHb = 64800000;
        if (array == null || array.length == 0) {
            return new int[] { this.NHb, this.OHb };
        }
        this.NHb = _(array[0]);
        this.OHb = this.NHb;
        for (int i = 1; i < array.length; ++i) {
            this.NHb = Math.min(this.NHb, _(array[i]));
            this.OHb = Math.max(this.OHb, _(array[i]));
        }
        final ap ap = new ap(this.NHb);
        final int m = ap.M();
        int o = ap.O();
        if (o < 30) {
            o = 0;
        }
        else if (o <= 59) {
            o = 30;
        }
        ap.a(m, o, 0, 0);
        this.NHb = (int)ap.m();
        ap._(this.OHb);
        if (ap.P() == 0 && ap.N() > 0) {
            ap.j(1);
        }
        ap.n();
        ap.M();
        final int o2 = ap.O();
        if (o2 < 30) {
            ap._a(30);
        }
        else if (o2 > 30) {
            ap.l();
        }
        ap.a(ap.M(), ap.O(), 0, 0);
        this.OHb = (int)ap.m();
        if (this.NHb + 3600000 >= this.OHb) {
            this.OHb = this.NHb + 3600000 + 1800000;
        }
        if (this.OHb >= 86400000) {
            this.OHb = 86399999;
        }
        return new int[] { this.NHb, this.OHb };
    }
    
    synchronized double n() {
        return this.OHb - this.NHb;
    }
    
    public Yo() {
        this.NHb = 32400000;
        this.OHb = 64800000;
        this.PHb = false;
        this.Za = new Xo();
        this.time = new ap(this.NHb);
    }
    
    public Yo(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        this(n, n2, n3, n4, n5, n6, 0);
    }
    
    public Yo(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        this.NHb = 32400000;
        this.OHb = 64800000;
        this.PHb = false;
        this.Za = new Xo(n, n2, n3);
        this.PHb = ((n != 0 || n2 != 0 || n3 != 0) && this.Za.m() < Double.MIN_VALUE);
        final long n8 = n4 * 60 * 60 * 1000 + n5 * 60 * 1000 + n6 * 1000 + n7;
        if (n8 < 0L) {
            this.time.a(0, 0, 0, 0);
        }
        else if (n8 >= 86400000L) {
            this.time.a(23, 59, 59, 999);
        }
        else {
            this.time = new ap(n4, n5, n6, n7);
        }
    }
    
    public void _(final double n) {
        final double n2 = (int)n;
        this.Za._(n2);
        this.PHb = (n2 < 0.0 || (n2 > 0.0 && this.Za.m() < Double.MIN_VALUE));
        this.time._(_(n));
    }
    
    public void set(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        this._(n, n2, n3, n4, n5, n6, 0);
    }
    
    public void _(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        this.Za.set(n, n2, n3);
        this.time.a(n4, n5, n6, n7);
        this.PHb = ((n != 0 || n2 != 0 || n3 != 0) && this.Za.m() < Double.MIN_VALUE);
    }
    
    public void set(final int n, final int n2, final int n3) {
        this.a(n, n2, n3, 0);
    }
    
    public void a(final int n, final int n2, final int n3, final int n4) {
        this.Za._(0.0);
        this.time.a(n, n2, n3, n4);
    }
    
    public void c(final String s) {
        this.Za.c(s);
    }
    
    public double m() {
        if (this.PHb) {
            return 0.0;
        }
        return this.Za.m() + g((int)this.time.m());
    }
    
    public synchronized String toString() {
        String string;
        if (this.Za.m() < 1.0) {
            string = "";
        }
        else {
            string = this.Za.toString();
        }
        if (Math.round(this.time.m()) == this.NHb) {
            return string;
        }
        return string + "\n" + this.time.toString();
    }
    
    public synchronized long a(double n, double n2) {
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
            n8 = (n5 - 1L) * this.n();
        }
        double n9;
        if (n5 != 0L) {
            n9 = n8 + (this.OHb - n6 + (n7 - this.NHb));
        }
        else {
            n9 = n8 + (n7 - n6);
        }
        return Math.round(n9 * n3);
    }
    
    public long j() {
        return (long)this.Za.m() * 24L * 60L * 60L + this.time.Q();
    }
    
    public void b(final int n) {
        this.Za._(this.Za.m() + n);
    }
    
    public synchronized void a(int n) {
        final int n2 = (int)this.time.m();
        if (n2 + n > this.OHb) {
            n -= this.OHb - n2;
            this.Za._(this.Za.m() + 1.0);
            this.time._(this.NHb);
        }
        final int n3 = (int)this.n();
        final int n4 = n / n3;
        final int n5 = n % n3;
        this.Za._(this.Za.m() + n4);
        this.time._(this.time.m() + n5);
    }
    
    public void j(final int n) {
        this.a(n * 1000);
    }
    
    public void i(final int n) {
        this.j(n * 60);
    }
    
    public void h(final int n) {
        this.j(n * 60 * 60);
    }
    
    public synchronized void k() {
        if (Math.round(this.time.m()) != this.NHb) {
            this.Za._(this.Za.m() + 1.0);
            this.time._(this.NHb);
        }
    }
    
    public synchronized void l() {
        this.time.a(this.time.M(), this.time.O(), this.time.P(), 0);
        if (this.time.O() != 0 || this.time.P() != 0) {
            final int n = this.OHb / 3600000;
            final int n2 = this.NHb / 3600000;
            if (this.time.M() < n) {
                this.time.set(this.time.M() + 1, 0, 0);
            }
            else {
                this.Za._(this.Za.m() + 1.0);
                this.time.set(n2, 0, 0);
                if (this.time.m() < this.NHb) {
                    this.time.set(this.time.M() + 1, 0, 0);
                }
            }
        }
    }
    
    public void m() {
        this.n();
        while (this.time.O() % 5 != 0) {
            this.i(1);
        }
    }
    
    public synchronized void aa(final int n) {
        final int n2 = (int)this.Za.m();
        if (n == 0) {
            return;
        }
        if (n >= 60) {
            this._a(n / 60);
            return;
        }
        this.c();
        while (this.time.P() % n != 0) {
            this.j((this.time.P() / n + 1) * n - this.time.P());
            if (n2 != (int)this.Za.m()) {
                this.Za._(n2);
                this.time._(this.OHb);
                break;
            }
        }
    }
    
    public synchronized void _a(final int n) {
        final int n2 = (int)this.Za.m();
        if (n == 0) {
            return;
        }
        this.n();
        while (this.time.O() % n != 0) {
            this.i((this.time.O() / n + 1) * n - this.time.O());
            if (n2 != (int)this.Za.m()) {
                this.Za._(n2);
                this.time._(this.OHb);
                break;
            }
        }
    }
    
    public void d() {
        this.c();
        while (this.time.P() % 5 != 0) {
            this.j(1);
        }
    }
    
    public synchronized void c() {
        this.time.a(this.time.M(), this.time.O(), this.time.P(), 0);
    }
    
    public synchronized void n() {
        this.time.a(this.time.M(), this.time.O(), this.time.P(), 0);
        if (this.time.P() != 0) {
            if (this.time.O() < 59) {
                this.time.a(this.time.M(), this.time.O() + 1, 0, 0);
                if (this.time.m() > this.OHb) {
                    this.Za._(this.Za.m() + 1.0);
                    this.time._(this.NHb);
                    if (this.time.P() != 0) {
                        if (this.time.O() < 59) {
                            this.time.a(this.time.M(), this.time.O() + 1, 0, 0);
                        }
                        else {
                            this.time.a(this.time.M() + 1, 0, 0, 0);
                        }
                    }
                }
            }
            else if (this.time.M() < 23) {
                this.time.a(this.time.M() + 1, 0, 0, 0);
                if (this.time.m() > this.OHb) {
                    this.Za._(this.Za.m() + 1.0);
                    this.time._(this.NHb);
                    if (this.time.P() != 0) {
                        if (this.time.O() < 59) {
                            this.time.a(this.time.M(), this.time.O() + 1, 0, 0);
                        }
                        else {
                            this.time.a(this.time.M() + 1, 0, 0, 0);
                        }
                    }
                }
            }
            else {
                this.Za._(this.Za.m() + 1.0);
                this.time._(this.NHb);
                if (this.time.P() != 0) {
                    if (this.time.O() < 59) {
                        this.time.a(this.time.M(), this.time.O() + 1, 0, 0);
                    }
                    else {
                        this.time.a(this.time.M() + 1, 0, 0, 0);
                    }
                }
            }
        }
    }
}
