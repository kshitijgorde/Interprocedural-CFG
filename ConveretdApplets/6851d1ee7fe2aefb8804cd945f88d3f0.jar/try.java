// 
// Decompiled by Procyon v0.5.30
// 

public class try extends throws
{
    private double[] ola;
    private int[] pla;
    private int qla;
    var rla;
    
    public try() {
        this.ola = null;
        this.pla = null;
        this.qla = 1;
        this.rla = new var();
    }
    
    public try(final double n, final double n2, final double n3, final double n4) {
        super(n, n2, n3, n4);
        this.ola = null;
        this.pla = null;
        this.qla = 1;
        (this.rla = new var()).b(n, n2);
        this.rla._(n3, n4);
    }
    
    public double b(final double n) {
        if (!super.Wka) {
            this.o();
        }
        if (this.ola == null || this.pla == null || this.ola.length != this.pla.length || super.hka > super.ika || super.Uka > super.Vka) {
            throw new RuntimeException("Invalid map parameters! xmin=" + super.hka + " xmax=" + super.ika + " ymin=" + super.Uka + " ymax=" + super.Vka + " xValues.length=" + ((this.ola == null) ? "null" : ("" + this.ola.length)) + " yValues.length=" + ((this.pla == null) ? "null" : ("" + this.pla.length)));
        }
        if (n < this.ola[0]) {
            if (this.ola.length == 1) {
                return super.Uka;
            }
            return this.rla.b(n);
        }
        else if (n > this.ola[this.ola.length - 1]) {
            if (this.ola.length == 1) {
                return super.Vka;
            }
            return this.rla.b(n);
        }
        else {
            final int _ = _(this.ola, n);
            if (_ >= 0) {
                return this.pla[_];
            }
            final int n2 = -1 - _;
            final int n3 = this.pla[n2 - 1];
            final int n4 = this.pla[n2];
            final double n5 = this.ola[n2 - 1];
            return Math.round(n3 + (n4 - n3) * ((n - n5) / (this.ola[n2] - n5)));
        }
    }
    
    public double a(final double n) {
        if (!super.Wka) {
            this.o();
        }
        if (this.ola == null || this.pla == null || this.ola.length != this.pla.length || super.hka > super.ika || super.Uka > super.Vka) {
            throw new RuntimeException("Invalid map parameters! xmin=" + super.hka + " xmax=" + super.ika + " ymin=" + super.Uka + " ymax=" + super.Vka + " xValues.length=" + ((this.ola == null) ? "null" : ("" + this.ola.length)) + " yValues.length=" + ((this.pla == null) ? "null" : ("" + this.pla.length)));
        }
        if (n < this.pla[0]) {
            if (this.pla.length == 1) {
                return this.ola[0];
            }
            return this.rla.a(n);
        }
        else if (n > this.pla[this.pla.length - 1]) {
            if (this.pla.length == 1) {
                return this.ola[this.ola.length - 1];
            }
            return this.rla.a(n);
        }
        else {
            final int b = b(this.pla, (int)n);
            if (b >= 0) {
                return this.ola[b];
            }
            final int n2 = -1 - b;
            final double n3 = this.ola[n2 - 1];
            final double n4 = this.ola[n2];
            final int n5 = this.pla[n2 - 1];
            return n3 + (n4 - n3) * ((n - n5) / (this.pla[n2] - n5));
        }
    }
    
    public void _(final double[] ola) {
        this.ola = ola;
        super.Wka = false;
    }
    
    protected void o() {
        this.qla = (int)(super.Vka - super.Uka);
        if (this.ola != null) {
            this.pla = new int[this.ola.length];
            if (this.pla.length == 1) {
                this.pla[0] = (int)((super.Uka + super.Vka) * 0.5);
            }
            else {
                final var var = new var(0.0, this.pla.length - 1, super.Uka, super.Vka);
                var.g(true);
                for (int i = 0; i < this.pla.length; ++i) {
                    this.pla[i] = (int)var.b(i);
                    if (i > 0 && this.pla[i] - this.pla[i - 1] < this.qla) {
                        this.qla = this.pla[i] - this.pla[i - 1];
                    }
                }
            }
            if (this.ola.length > 0) {
                this.rla.b(this.ola[0], this.ola[this.ola.length - 1]);
                this.rla._(this.pla[0], this.pla[this.pla.length - 1]);
            }
        }
        super.Wka = true;
    }
    
    public int r() {
        if (!super.Wka) {
            this.o();
        }
        return this.qla;
    }
    
    public int da() {
        if (this.ola == null) {
            return 0;
        }
        return this.ola.length;
    }
    
    public double[] X() {
        return this.ola;
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
    
    public static int _(final double[] array, final double n) {
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
