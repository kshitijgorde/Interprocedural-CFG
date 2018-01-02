// 
// Decompiled by Procyon v0.5.30
// 

public class else
{
    private static final int Hqa = 86400000;
    private int Iqa;
    private int Jqa;
    private boolean Kqa;
    private for time;
    private throw Gqa;
    
    static int a(final double n) {
        if (n < 0.0) {
            throw new IllegalArgumentException("value must be >= 0");
        }
        return (int)Math.round((n - (int)n) * 8.64E7);
    }
    
    static double a(final int n) {
        return n / 8.64E7;
    }
    
    public synchronized void _(final int[] array) {
        if (array == null || array.length != 2) {
            this.Iqa = 32400000;
            this.Jqa = 64800000;
            return;
        }
        this.Iqa = array[0];
        this.Jqa = array[1];
    }
    
    public synchronized int[] a(final double[] array) {
        this.Iqa = 32400000;
        this.Jqa = 64800000;
        if (array == null || array.length == 0) {
            return new int[] { this.Iqa, this.Jqa };
        }
        this.Iqa = a(array[0]);
        this.Jqa = this.Iqa;
        for (int i = 1; i < array.length; ++i) {
            this.Iqa = Math.min(this.Iqa, a(array[i]));
            this.Jqa = Math.max(this.Jqa, a(array[i]));
        }
        final for for1 = new for(this.Iqa);
        final int m = for1.M();
        int o = for1.O();
        if (o < 30) {
            o = 0;
        }
        else if (o <= 59) {
            o = 30;
        }
        for1._(m, o, 0, 0);
        this.Iqa = (int)for1.l();
        for1._(this.Jqa);
        if (for1.P() == 0 && for1.N() > 0) {
            for1.z(1);
        }
        for1.K();
        for1.M();
        final int o2 = for1.O();
        if (o2 < 30) {
            for1.C(30);
        }
        else if (o2 > 30) {
            for1.J();
        }
        for1._(for1.M(), for1.O(), 0, 0);
        this.Jqa = (int)for1.l();
        if (this.Iqa + 3600000 >= this.Jqa) {
            this.Jqa = this.Iqa + 3600000 + 1800000;
        }
        if (this.Jqa >= 86400000) {
            this.Jqa = 86399999;
        }
        return new int[] { this.Iqa, this.Jqa };
    }
    
    synchronized double c() {
        return this.Jqa - this.Iqa;
    }
    
    public else() {
        this.Iqa = 32400000;
        this.Jqa = 64800000;
        this.Kqa = false;
        this.Gqa = new throw();
        this.time = new for(this.Iqa);
    }
    
    public else(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        this(n, n2, n3, n4, n5, n6, 0);
    }
    
    public else(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        this.Iqa = 32400000;
        this.Jqa = 64800000;
        this.Kqa = false;
        this.Gqa = new throw(n, n2, n3);
        this.Kqa = ((n != 0 || n2 != 0 || n3 != 0) && this.Gqa.l() < Double.MIN_VALUE);
        final long n8 = n4 * 60 * 60 * 1000 + n5 * 60 * 1000 + n6 * 1000 + n7;
        if (n8 < 0L) {
            this.time = new for(0, 0, 0, 0);
        }
        else if (n8 >= 86400000L) {
            this.time = new for(23, 59, 59, 999);
        }
        else {
            this.time = new for(n4, n5, n6, n7);
        }
    }
    
    public synchronized void _(final double n) {
        final double n2 = (int)n;
        this.Gqa._(n2);
        this.Kqa = (n2 < 0.0 || (n2 > 0.0 && this.Gqa.l() < Double.MIN_VALUE));
        this.time._(a(n));
    }
    
    public synchronized void set(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        this.b(n, n2, n3, n4, n5, n6, 0);
    }
    
    public synchronized void b(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        this.Gqa.set(n, n2, n3);
        this.time._(n4, n5, n6, n7);
        this.Kqa = ((n != 0 || n2 != 0 || n3 != 0) && this.Gqa.l() < Double.MIN_VALUE);
    }
    
    public synchronized void set(final int n, final int n2, final int n3) {
        this._(n, n2, n3, 0);
    }
    
    public synchronized void _(final int n, final int n2, final int n3, final int n4) {
        this.Gqa._(0.0);
        this.time._(n, n2, n3, n4);
    }
    
    public void x(final String s) {
        this.Gqa.x(s);
    }
    
    public synchronized double l() {
        if (this.Kqa) {
            return 0.0;
        }
        return this.Gqa.l() + a((int)this.time.l());
    }
    
    public synchronized String toString() {
        String string;
        if (this.Gqa.l() < 1.0) {
            string = "";
        }
        else {
            string = this.Gqa.toString();
        }
        if (Math.round(this.time.l()) == this.Iqa) {
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
        final double n6 = a(n);
        final double n7 = a(n2);
        double n8 = 0.0;
        if (n5 > 0L) {
            n8 = (n5 - 1L) * this.c();
        }
        double n9;
        if (n5 != 0L) {
            n9 = n8 + (this.Jqa - n6 + (n7 - this.Iqa));
        }
        else {
            n9 = n8 + (n7 - n6);
        }
        return Math.round(n9 * n3);
    }
    
    public synchronized long m() {
        return (long)this.Gqa.l() * 24L * 60L * 60L + this.time.Q();
    }
    
    public synchronized void F(final int n) {
        this.Gqa._(this.Gqa.l() + n);
    }
    
    public synchronized void E(int n) {
        final int n2 = (int)this.time.l();
        if (n2 + n > this.Jqa) {
            n -= this.Jqa - n2;
            this.Gqa._(this.Gqa.l() + 1.0);
            this.time._(this.Iqa);
        }
        final int n3 = (int)this.c();
        final int n4 = n / n3;
        final int n5 = n % n3;
        this.Gqa._(this.Gqa.l() + n4);
        this.time._(this.time.l() + n5);
    }
    
    public synchronized void z(final int n) {
        this.E(n * 1000);
    }
    
    public synchronized void A(final int n) {
        this.z(n * 60);
    }
    
    public synchronized void B(final int n) {
        this.z(n * 60 * 60);
    }
    
    public synchronized void P() {
        if (Math.round(this.time.l()) != this.Iqa) {
            this.Gqa._(this.Gqa.l() + 1.0);
            this.time._(this.Iqa);
        }
    }
    
    public synchronized void J() {
        this.time._(this.time.M(), this.time.O(), this.time.P(), 0);
        if (this.time.O() != 0 || this.time.P() != 0) {
            final int n = this.Jqa / 3600000;
            final int n2 = this.Iqa / 3600000;
            if (this.time.M() < n) {
                this.time.set(this.time.M() + 1, 0, 0);
            }
            else {
                this.Gqa._(this.Gqa.l() + 1.0);
                this.time.set(n2, 0, 0);
                if (this.time.l() < this.Iqa) {
                    this.time.set(this.time.M() + 1, 0, 0);
                }
            }
        }
    }
    
    public synchronized void L() {
        this.K();
        while (this.time.O() % 5 != 0) {
            this.A(1);
        }
    }
    
    public synchronized void G(final int n) {
        final int n2 = (int)this.Gqa.l();
        if (n >= 60) {
            this.C(n / 60);
            return;
        }
        this.M();
        if (n == 0) {
            return;
        }
        while (this.time.P() % n != 0) {
            this.z((this.time.P() / n + 1) * n - this.time.P());
            if (n2 != (int)this.Gqa.l()) {
                this.Gqa._(n2);
                this.time._(this.Jqa);
                break;
            }
        }
    }
    
    public synchronized void C(final int n) {
        final int n2 = (int)this.Gqa.l();
        this.K();
        if (n == 0) {
            return;
        }
        while (this.time.O() % n != 0) {
            this.A((this.time.O() / n + 1) * n - this.time.O());
            if (n2 != (int)this.Gqa.l()) {
                this.Gqa._(n2);
                this.time._(this.Jqa);
                break;
            }
        }
    }
    
    public synchronized void N() {
        this.M();
        while (this.time.P() % 5 != 0) {
            this.z(1);
        }
    }
    
    public synchronized void M() {
        this.time._(this.time.M(), this.time.O(), this.time.P(), 0);
    }
    
    public synchronized void K() {
        this.time._(this.time.M(), this.time.O(), this.time.P(), 0);
        if (this.time.P() != 0) {
            if (this.time.O() < 59) {
                this.time._(this.time.M(), this.time.O() + 1, 0, 0);
                if (this.time.l() > this.Jqa) {
                    this.Gqa._(this.Gqa.l() + 1.0);
                    this.time._(this.Iqa);
                    if (this.time.P() != 0) {
                        if (this.time.O() < 59) {
                            this.time._(this.time.M(), this.time.O() + 1, 0, 0);
                        }
                        else {
                            this.time._(this.time.M() + 1, 0, 0, 0);
                        }
                    }
                }
            }
            else if (this.time.M() < 23) {
                this.time._(this.time.M() + 1, 0, 0, 0);
                if (this.time.l() > this.Jqa) {
                    this.Gqa._(this.Gqa.l() + 1.0);
                    this.time._(this.Iqa);
                    if (this.time.P() != 0) {
                        if (this.time.O() < 59) {
                            this.time._(this.time.M(), this.time.O() + 1, 0, 0);
                        }
                        else {
                            this.time._(this.time.M() + 1, 0, 0, 0);
                        }
                    }
                }
            }
            else {
                this.Gqa._(this.Gqa.l() + 1.0);
                this.time._(this.Iqa);
                if (this.time.P() != 0) {
                    if (this.time.O() < 59) {
                        this.time._(this.time.M(), this.time.O() + 1, 0, 0);
                    }
                    else {
                        this.time._(this.time.M() + 1, 0, 0, 0);
                    }
                }
            }
        }
    }
}
