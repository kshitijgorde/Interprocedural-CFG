// 
// Decompiled by Procyon v0.5.30
// 

public class jq extends Np
{
    private double[] tb;
    private int[] _;
    private int a;
    Mp b;
    
    public jq() {
        this.tb = null;
        this._ = null;
        this.a = 1;
        this.b = new Mp();
    }
    
    public jq(final double n, final double n2, final double n3, final double n4) {
        super(n, n2, n3, n4);
        this.tb = null;
        this._ = null;
        this.a = 1;
        (this.b = new Mp()).a(n, n2);
        this.b.b(n3, n4);
    }
    
    public double b(final double n) {
        if (!super.ob) {
            this.g();
        }
        if (this.tb == null || this._ == null || this.tb.length != this._.length || super.kb > super.lb || super.mb > super.nb) {
            throw new RuntimeException("Invalid map parameters! xmin=" + super.kb + " xmax=" + super.lb + " ymin=" + super.mb + " ymax=" + super.nb + " xValues.length=" + ((this.tb == null) ? "null" : ("" + this.tb.length)) + " yValues.length=" + ((this._ == null) ? "null" : ("" + this._.length)));
        }
        if (n < this.tb[0]) {
            if (this.tb.length == 1) {
                return super.mb;
            }
            return this.b.b(n);
        }
        else if (n > this.tb[this.tb.length - 1]) {
            if (this.tb.length == 1) {
                return super.nb;
            }
            return this.b.b(n);
        }
        else {
            final int a = a(this.tb, n);
            if (a >= 0) {
                return this._[a];
            }
            final int n2 = -1 - a;
            final int n3 = this._[n2 - 1];
            final int n4 = this._[n2];
            final double n5 = this.tb[n2 - 1];
            return Math.round(n3 + (n4 - n3) * ((n - n5) / (this.tb[n2] - n5)));
        }
    }
    
    public double e(final double n) {
        if (!super.ob) {
            this.g();
        }
        if (this.tb == null || this._ == null || this.tb.length != this._.length || super.kb > super.lb || super.mb > super.nb) {
            throw new RuntimeException("Invalid map parameters! xmin=" + super.kb + " xmax=" + super.lb + " ymin=" + super.mb + " ymax=" + super.nb + " xValues.length=" + ((this.tb == null) ? "null" : ("" + this.tb.length)) + " yValues.length=" + ((this._ == null) ? "null" : ("" + this._.length)));
        }
        if (n < this._[0]) {
            if (this._.length == 1) {
                return this.tb[0];
            }
            return this.b.e(n);
        }
        else if (n > this._[this._.length - 1]) {
            if (this._.length == 1) {
                return this.tb[this.tb.length - 1];
            }
            return this.b.e(n);
        }
        else {
            final int a = a(this._, (int)n);
            if (a >= 0) {
                return this.tb[a];
            }
            final int n2 = -1 - a;
            final double n3 = this.tb[n2 - 1];
            final double n4 = this.tb[n2];
            final int n5 = this._[n2 - 1];
            return n3 + (n4 - n3) * ((n - n5) / (this._[n2] - n5));
        }
    }
    
    public void _(final double[] tb) {
        this.tb = tb;
        super.ob = false;
    }
    
    protected void g() {
        this.a = (int)(super.nb - super.mb);
        if (this.tb != null) {
            this._ = new int[this.tb.length];
            if (this._.length == 1) {
                this._[0] = (int)((super.mb + super.nb) * 0.5);
            }
            else {
                final Mp mp = new Mp(0.0, this._.length - 1, super.mb, super.nb);
                mp.b(true);
                for (int i = 0; i < this._.length; ++i) {
                    this._[i] = (int)mp.b(i);
                    if (i > 0 && this._[i] - this._[i - 1] < this.a) {
                        this.a = this._[i] - this._[i - 1];
                    }
                }
            }
            if (this.tb.length > 0) {
                this.b.a(this.tb[0], this.tb[this.tb.length - 1]);
                this.b.b(this._[0], this._[this._.length - 1]);
            }
        }
        super.ob = true;
    }
    
    public int a() {
        if (!super.ob) {
            this.g();
        }
        return this.a;
    }
    
    public int d() {
        if (this.tb == null) {
            return 0;
        }
        return this.tb.length;
    }
    
    public double[] b() {
        return this.tb;
    }
    
    public static int a(final int[] array, final int n) {
        int i = 0;
        int n2 = array.length - 1;
        while (i <= n2) {
            final int n3 = (i + n2) / 2;
            final int n4 = array[n3];
            if (n4 < n) {
                i = n3 + 1;
            }
            else {
                if (n4 <= n) {
                    return n3;
                }
                n2 = n3 - 1;
            }
        }
        return -(i + 1);
    }
    
    public static int a(final double[] array, final double n) {
        int i = 0;
        int n2 = array.length - 1;
        while (i <= n2) {
            final int n3 = (i + n2) / 2;
            final double n4 = array[n3];
            int n5;
            if (n4 < n) {
                n5 = -1;
            }
            else if (n4 > n) {
                n5 = 1;
            }
            else {
                final long doubleToLongBits = Double.doubleToLongBits(n4);
                final long doubleToLongBits2 = Double.doubleToLongBits(n);
                if (doubleToLongBits == doubleToLongBits2) {
                    n5 = 0;
                }
                else if (doubleToLongBits > doubleToLongBits2) {
                    n5 = 1;
                }
                else {
                    n5 = -1;
                }
            }
            if (n5 < 0) {
                i = n3 + 1;
            }
            else {
                if (n5 <= 0) {
                    return n3;
                }
                n2 = n3 - 1;
            }
        }
        return -(i + 1);
    }
}
