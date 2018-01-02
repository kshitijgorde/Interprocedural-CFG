// 
// Decompiled by Procyon v0.5.30
// 

public class u extends o
{
    private double[] pra;
    private int[] qra;
    private int rra;
    catch sra;
    
    public u() {
        this.pra = null;
        this.qra = null;
        this.rra = 1;
        this.sra = new catch();
    }
    
    public u(final double n, final double n2, final double n3, final double n4) {
        super(n, n2, n3, n4);
        this.pra = null;
        this.qra = null;
        this.rra = 1;
        (this.sra = new catch()).a(n, n2);
        this.sra.b(n3, n4);
    }
    
    public double b(final double n) {
        if (!super.bra) {
            this.U();
        }
        if (this.pra == null || this.qra == null || this.pra.length != this.qra.length || super.gra > super.fra || super.era > super.dra) {
            throw new RuntimeException("Invalid map parameters! xmin=" + super.gra + " xmax=" + super.fra + " ymin=" + super.era + " ymax=" + super.dra + " xValues.length=" + ((this.pra == null) ? "null" : ("" + this.pra.length)) + " yValues.length=" + ((this.qra == null) ? "null" : ("" + this.qra.length)));
        }
        if (n < this.pra[0]) {
            if (this.pra.length == 1) {
                return super.era;
            }
            return this.sra.b(n);
        }
        else if (n > this.pra[this.pra.length - 1]) {
            if (this.pra.length == 1) {
                return super.dra;
            }
            return this.sra.b(n);
        }
        else {
            final int b = b(this.pra, n);
            if (b >= 0) {
                return this.qra[b];
            }
            final int n2 = -1 - b;
            final int n3 = this.qra[n2 - 1];
            final int n4 = this.qra[n2];
            final double n5 = this.pra[n2 - 1];
            return Math.round(n3 + (n4 - n3) * ((n - n5) / (this.pra[n2] - n5)));
        }
    }
    
    public double _(final double n) {
        if (!super.bra) {
            this.U();
        }
        if (this.pra == null || this.qra == null || this.pra.length != this.qra.length || super.gra > super.fra || super.era > super.dra) {
            throw new RuntimeException("Invalid map parameters! xmin=" + super.gra + " xmax=" + super.fra + " ymin=" + super.era + " ymax=" + super.dra + " xValues.length=" + ((this.pra == null) ? "null" : ("" + this.pra.length)) + " yValues.length=" + ((this.qra == null) ? "null" : ("" + this.qra.length)));
        }
        if (n < this.qra[0]) {
            if (this.qra.length == 1) {
                return this.pra[0];
            }
            return this.sra._(n);
        }
        else if (n > this.qra[this.qra.length - 1]) {
            if (this.qra.length == 1) {
                return this.pra[this.pra.length - 1];
            }
            return this.sra._(n);
        }
        else {
            final int b = b(this.qra, (int)n);
            if (b >= 0) {
                return this.pra[b];
            }
            final int n2 = -1 - b;
            final double n3 = this.pra[n2 - 1];
            final double n4 = this.pra[n2];
            final int n5 = this.qra[n2 - 1];
            return n3 + (n4 - n3) * ((n - n5) / (this.qra[n2] - n5));
        }
    }
    
    public void b(final double[] pra) {
        this.pra = pra;
        super.bra = false;
    }
    
    protected void U() {
        this.rra = (int)(super.dra - super.era);
        if (this.pra != null) {
            this.qra = new int[this.pra.length];
            if (this.qra.length == 1) {
                this.qra[0] = (int)((super.era + super.dra) * 0.5);
            }
            else {
                final catch catch1 = new catch(0.0, this.qra.length - 1, super.era, super.dra);
                catch1.b(true);
                for (int i = 0; i < this.qra.length; ++i) {
                    this.qra[i] = (int)catch1.b(i);
                    if (i > 0 && this.qra[i] - this.qra[i - 1] < this.rra) {
                        this.rra = this.qra[i] - this.qra[i - 1];
                    }
                }
            }
            if (this.pra.length > 0) {
                this.sra.a(this.pra[0], this.pra[this.pra.length - 1]);
                this.sra.b(this.qra[0], this.qra[this.qra.length - 1]);
            }
        }
        super.bra = true;
    }
    
    public int a() {
        if (!super.bra) {
            this.U();
        }
        return this.rra;
    }
    
    public int W() {
        if (this.pra == null) {
            return 0;
        }
        return this.pra.length;
    }
    
    public double[] X() {
        return this.pra;
    }
    
    public static int b(final int[] array, final int n) {
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
    
    public static int b(final double[] array, final double n) {
        int i = 0;
        int n2 = array.length - 1;
        while (i <= n2) {
            final int n3 = (i + n2) / 2;
            final double n4 = array[n3];
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
}
