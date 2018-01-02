// 
// Decompiled by Procyon v0.5.30
// 

public class rq implements oq
{
    private static final int Ma = 0;
    private static final int Na = 1;
    private static final int Oa = 2;
    private static final int Pa = 3;
    private static final int Qa = 4;
    private double[] Ra;
    private int Sa;
    private int Ta;
    private double Ua;
    private double Va;
    private boolean Wa;
    Yo Xa;
    
    public rq() {
        this.Xa = new Yo();
        this.Wa = false;
        this.Ta = 1;
    }
    
    public rq(final double n, final double n2, final int ta) {
        this.Xa = new Yo();
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
    
    public int[] _(final double[] array) {
        return this.Xa.a(array);
    }
    
    private void j() {
        if (this.Va == this.Ua || this.Ta < 2) {
            if (this.Ra == null || this.Ra.length < this.Ta) {
                this.Ra = new double[this.Ta];
            }
            this.Ra[0] = this.Ua + (this.Va - this.Ua) / 2.0;
            if (this.Va - this.Ua > 2.0) {
                this.Ra[0] = Math.round(this.Ra[0]);
            }
            this.Sa = 1;
            this.Wa = true;
            return;
        }
        this.Sa = this.Ta;
        if (this.Ra == null || this.Ra.length < this.Ta) {
            this.Ra = new double[this.Ta];
        }
        final double n = this.Xa.a(this.Ua, this.Va);
        this.Xa._(this.Ua);
        if ((int)n <= this.Sa) {
            this.Sa = (int)n + 1;
            if (this.Sa > this.Ta) {
                this.Sa = this.Ta;
            }
            for (int i = 0; i < this.Sa; ++i) {
                this.Ra[i] = this.Xa.m();
                if (this.Ra[i] > this.Va) {
                    break;
                }
                this.Sa = i + 1;
                this.Xa.a(1);
            }
            this.Wa = true;
            return;
        }
        final double ceil = Math.ceil(n / this.Ta);
        int n2;
        int n3;
        if (ceil >= this.Xa.n() || this.Va - this.Ua > 2.0) {
            n2 = (int)Math.ceil(ceil / this.Xa.n());
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
        this.Xa._(this.Ua);
        this.Xa.a(1);
        switch (n3) {
            case 3: {
                this.Xa.k();
                break;
            }
            case 2: {
                this.Xa.l();
                break;
            }
            case 1: {
                if (n2 >= 5) {
                    this.Xa.m();
                    while (n2 % 5 > 0) {
                        ++n2;
                    }
                    break;
                }
                this.Xa.n();
                break;
            }
            case 0: {
                this.Xa.c();
                if (n2 >= 5) {
                    this.Xa.d();
                    while (n2 % 5 > 0) {
                        ++n2;
                    }
                    break;
                }
                break;
            }
        }
        this.Sa = 0;
        for (int j = 0; j < this.Ta; ++j) {
            this.Ra[j] = this.Xa.m();
            if (this.Ra[j] > this.Va) {
                break;
            }
            this.Sa = j + 1;
            switch (n3) {
                case 3: {
                    this.Xa.b(n2);
                    break;
                }
                case 2: {
                    this.Xa.h(n2);
                    break;
                }
                case 1: {
                    this.Xa.i(n2);
                    break;
                }
                case 0: {
                    this.Xa.j(n2);
                    break;
                }
                case 4: {
                    this.Xa.a(n2);
                    break;
                }
            }
        }
        this.Wa = true;
    }
}
