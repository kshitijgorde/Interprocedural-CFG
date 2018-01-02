// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class af extends a2
{
    float[] fB;
    float[] fA;
    float fz;
    float fF;
    double[] fE;
    double[] fC;
    private boolean fG;
    private char[] fD;
    
    public af() {
        this.fG = false;
        this.fD = new char[] { 'p', 'a', 'n', 'o', 'F', 'l', 'a', 't', '\0' };
    }
    
    public void if() {
        super.if();
        this.fE = null;
        this.fC = null;
        this.fB = null;
        this.fA = null;
        this.fD = null;
    }
    
    public void a(final an b, final float ca, final aq long1, final h cc, final t cb) {
        super.b = b;
        super.cA = ca;
        super.cD = 1.0f / super.cA;
        super.long = long1;
        super.cC = cc;
        super.cB = cb;
        super.goto = cb.h;
        this.fE = new double[4];
        this.fC = new double[4];
        super.int = 1;
        super.case = this.fD;
    }
    
    public void int(final bh bh) {
        super.int(bh);
        bh bh2 = bh.if;
        this.fG = true;
        while (bh2 != null) {
            if (bh2.a.toLowerCase().compareTo("rectangle") == 0) {
                this.fG = false;
                this.fB = new float[4];
                this.fA = new float[4];
                bh bh3 = bh2.if;
                int n = 0;
                while (bh3 != null) {
                    if (bh3.a.toLowerCase().compareTo("vertex") == 0) {
                        for (int i = 0; i < bh3.do; ++i) {
                            if (bh3.try[i].toLowerCase().compareTo("pan") == 0) {
                                this.fB[n] = new Float(bh3.new[i]) * super.cA;
                            }
                            else if (bh3.try[i].toLowerCase().compareTo("tilt") == 0) {
                                this.fA[n] = new Float(bh3.new[i]) * super.cA;
                            }
                        }
                        if (++n == 4) {
                            break;
                        }
                    }
                    bh3 = bh3.for;
                }
                if (n != 4) {
                    this.fG = true;
                }
            }
            bh2 = bh2.for;
        }
    }
    
    protected void r() {
        if (this.fG) {
            return;
        }
        final a4[] array = new a4[4];
        this.fz = (this.fB[0] + this.fB[1] + this.fB[2] + this.fB[3]) / 4.0f;
        this.fF = (this.fA[0] + this.fA[1] + this.fA[2] + this.fA[3]) / 4.0f;
        for (int i = 0; i < 4; ++i) {
            final float n = (float)Math.sin(this.fA[i]);
            final float n2 = (float)(Math.cos(this.fB[i]) * Math.cos(this.fA[i]));
            final float n3 = (float)(Math.sin(this.fB[i]) * Math.cos(this.fA[i]));
            final float n4 = (float)(Math.cos(this.fz) * Math.cos(this.fF));
            final float n5 = (float)(Math.cos(this.fz) * n2 + Math.sin(this.fz) * n3);
            final float n6 = 1.0f / (float)(Math.cos(this.fF) * n5 + Math.sin(this.fF) * n);
            this.fE[i] = (-Math.cos(this.fz) * n3 + Math.sin(this.fz) * n2) * n6;
            this.fC[i] = -(Math.cos(this.fF) * n - Math.sin(this.fF) * n5) * n6;
            final double sqrt = Math.sqrt(this.fE[i] * this.fE[i] + this.fC[i] * this.fC[i] + 1.0);
            array[i] = new a4();
            array[i].byte = (float)sqrt * (float)Math.sin(this.fA[i]);
            array[i].try = (float)sqrt * (float)(Math.cos(this.fB[i]) * Math.cos(this.fA[i]));
            array[i].if = (float)sqrt * (float)(Math.sin(this.fB[i]) * Math.cos(this.fA[i]));
        }
        super.ej = (int)(17.0f * super.b.ar / 100.0f);
        super.eh = (int)(17.0f * super.b.ar / 100.0f);
        (super.d9 = new a4[1][])[0] = new a4[super.ej * super.eh];
        (super.ef = new a4[1][])[0] = new a4[super.ej * super.eh];
        final float n7 = (super.dX.s - 1) / (super.ej - 1);
        final float n8 = (super.dX.r - 1) / (super.eh - 1);
        for (int j = 0; j < super.eh; ++j) {
            final float n9 = array[0].try + j * (-array[0].try + array[3].try) / (super.eh - 1.0f);
            final float n10 = array[1].try + j * (-array[1].try + array[2].try) / (super.eh - 1.0f);
            final float n11 = array[0].if + j * (-array[0].if + array[3].if) / (super.eh - 1.0f);
            final float n12 = array[1].if + j * (-array[1].if + array[2].if) / (super.eh - 1.0f);
            final float n13 = array[0].byte + j * (-array[0].byte + array[3].byte) / (super.eh - 1.0f);
            final float n14 = array[1].byte + j * (-array[1].byte + array[2].byte) / (super.eh - 1.0f);
            for (int k = 0; k < super.ej; ++k) {
                final int n15 = k + j * super.ej;
                super.d9[0][n15] = new a4();
                super.ef[0][n15] = new a4();
                super.d9[0][n15].a = j * n8;
                super.d9[0][n15].do = k * n7;
                super.d9[0][n15].if = n11 + k * (n12 - n11) / (super.ej - 1.0f);
                super.d9[0][n15].try = n9 + k * (n10 - n9) / (super.ej - 1.0f);
                super.d9[0][n15].byte = n13 + k * (n14 - n13) / (super.ej - 1.0f);
                super.ef[0][n15].do = super.d9[0][n15].do;
                super.ef[0][n15].a = super.d9[0][n15].a;
                super.ef[0][n15].try = super.d9[0][n15].try;
                super.ef[0][n15].if = super.d9[0][n15].if;
                super.ef[0][n15].byte = super.d9[0][n15].byte;
            }
        }
    }
    
    public void new(final boolean b) {
        if (super.byte && super.for) {
            if (super.dX != null && super.d9 != null && !this.fG && !super.c) {
                super.dX.for();
                super.b.W.a(super.d9[0], super.ej, super.eh, super.dX, super.cC, b);
            }
            this.o();
        }
    }
}
