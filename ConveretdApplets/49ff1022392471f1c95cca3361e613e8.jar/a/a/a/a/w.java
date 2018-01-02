// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class w extends ao
{
    float[] e0;
    float[] eT;
    float eS;
    float eU;
    double[] eZ;
    double[] eY;
    int eR;
    aq[] eW;
    private boolean eV;
    private char[] eX;
    
    public w() {
        this.eV = false;
        this.eX = new char[] { 'p', 'a', 'n', 'o', 'F', 'l', 'a', 't', '\0' };
    }
    
    public void a(final ac void1, final float cj, final ae goto1, final f cl, final m ck) {
        super.void = void1;
        super.cj = cj;
        super.cm = 1.0f / super.cj;
        super.goto = goto1;
        super.cl = cl;
        super.ck = ck;
        super.else = ck.l;
        this.eZ = new double[4];
        this.eY = new double[4];
        super.for = 1;
        super.byte = this.eX;
    }
    
    public void int(final a2 a2) {
        super.int(a2);
        a2 a3 = a2.if;
        this.eV = true;
        while (a3 != null) {
            if (a3.a.toLowerCase().compareTo("rectangle") == 0) {
                this.eV = false;
                this.e0 = new float[4];
                this.eT = new float[4];
                a2 a4 = a3.if;
                int n = 0;
                while (a4 != null) {
                    if (a4.a.toLowerCase().compareTo("vertex") == 0) {
                        for (int i = 0; i < a4.do; ++i) {
                            if (a4.try[i].toLowerCase().compareTo("pan") == 0) {
                                this.e0[n] = new Float(a4.new[i]) * super.cj;
                            }
                            else if (a4.try[i].toLowerCase().compareTo("tilt") == 0) {
                                this.eT[n] = new Float(a4.new[i]) * super.cj;
                            }
                        }
                        if (++n == 4) {
                            break;
                        }
                    }
                    a4 = a4.for;
                }
                if (n != 4) {
                    this.eV = true;
                }
            }
            a3 = a3.for;
        }
    }
    
    protected void r() {
        if (this.eV) {
            return;
        }
        final aq[] array = new aq[4];
        this.eS = (this.e0[0] + this.e0[1] + this.e0[2] + this.e0[3]) / 4.0f;
        this.eU = (this.eT[0] + this.eT[1] + this.eT[2] + this.eT[3]) / 4.0f;
        for (int i = 0; i < 4; ++i) {
            final float n = (float)Math.sin(this.eT[i]);
            final float n2 = (float)(Math.cos(this.e0[i]) * Math.cos(this.eT[i]));
            final float n3 = (float)(Math.sin(this.e0[i]) * Math.cos(this.eT[i]));
            final float n4 = (float)(Math.cos(this.eS) * Math.cos(this.eU));
            final float n5 = (float)(Math.cos(this.eS) * n2 + Math.sin(this.eS) * n3);
            final float n6 = 1.0f / (float)(Math.cos(this.eU) * n5 + Math.sin(this.eU) * n);
            this.eZ[i] = (-Math.cos(this.eS) * n3 + Math.sin(this.eS) * n2) * n6;
            this.eY[i] = -(Math.cos(this.eU) * n - Math.sin(this.eU) * n5) * n6;
            final double sqrt = Math.sqrt(this.eZ[i] * this.eZ[i] + this.eY[i] * this.eY[i] + 1.0);
            array[i] = new aq();
            array[i].byte = (float)sqrt * (float)Math.sin(this.eT[i]);
            array[i].try = (float)sqrt * (float)(Math.cos(this.e0[i]) * Math.cos(this.eT[i]));
            array[i].if = (float)sqrt * (float)(Math.sin(this.e0[i]) * Math.cos(this.eT[i]));
        }
        super.d0 = (int)(17.0f * super.void.ai / 100.0f);
        super.dY = (int)(17.0f * super.void.ai / 100.0f);
        (super.dQ = new aq[1][])[0] = new aq[super.d0 * super.dY];
        (super.dW = new aq[1][])[0] = new aq[super.d0 * super.dY];
        final float n7 = (super.dE.i - 1) / (super.d0 - 1);
        final float n8 = (super.dE.t - 1) / (super.dY - 1);
        for (int j = 0; j < super.dY; ++j) {
            final float n9 = array[0].try + j * (-array[0].try + array[3].try) / (super.dY - 1.0f);
            final float n10 = array[1].try + j * (-array[1].try + array[2].try) / (super.dY - 1.0f);
            final float n11 = array[0].if + j * (-array[0].if + array[3].if) / (super.dY - 1.0f);
            final float n12 = array[1].if + j * (-array[1].if + array[2].if) / (super.dY - 1.0f);
            final float n13 = array[0].byte + j * (-array[0].byte + array[3].byte) / (super.dY - 1.0f);
            final float n14 = array[1].byte + j * (-array[1].byte + array[2].byte) / (super.dY - 1.0f);
            for (int k = 0; k < super.d0; ++k) {
                final int n15 = k + j * super.d0;
                super.dQ[0][n15] = new aq();
                super.dW[0][n15] = new aq();
                super.dQ[0][n15].a = j * n8;
                super.dQ[0][n15].do = k * n7;
                super.dQ[0][n15].if = n11 + k * (n12 - n11) / (super.d0 - 1.0f);
                super.dQ[0][n15].try = n9 + k * (n10 - n9) / (super.d0 - 1.0f);
                super.dQ[0][n15].byte = n13 + k * (n14 - n13) / (super.d0 - 1.0f);
                super.dW[0][n15].do = super.dQ[0][n15].do;
                super.dW[0][n15].a = super.dQ[0][n15].a;
                super.dW[0][n15].try = super.dQ[0][n15].try;
                super.dW[0][n15].if = super.dQ[0][n15].if;
                super.dW[0][n15].byte = super.dQ[0][n15].byte;
            }
        }
        this.eR = super.d0 * 2 + (super.dY - 2) * 2;
        this.eW = new aq[this.eR];
        int n16 = 0;
        for (int l = 0; l < super.dQ.length; ++l) {
            if (l < super.d0) {
                this.eW[n16++] = super.dQ[0][l];
            }
        }
        for (int n17 = 0; n17 < super.dQ.length; ++n17) {
            if (n17 >= super.d0 && n17 < super.dQ.length - super.d0 && (n17 + 1) % super.d0 == 0) {
                this.eW[n16++] = super.dQ[0][n17];
            }
        }
        for (int n18 = super.dQ.length - 1; n18 >= 0; --n18) {
            if (n18 >= super.dQ.length - super.d0) {
                this.eW[n16++] = super.dQ[0][n18];
            }
        }
        for (int n19 = super.dQ.length - 1; n19 >= 0; --n19) {
            if (n19 >= super.d0 && n19 < super.dQ.length - super.d0 && n19 % super.d0 == 0) {
                this.eW[n16++] = super.dQ[0][n19];
            }
        }
    }
    
    public void new(final boolean b) {
        if (super.try && super.do) {
            if (super.dE != null && super.dQ != null && !this.eV && !super.b) {
                super.dE.for();
                super.void.P.a(super.dQ[0], super.d0, super.dY, super.dE, super.cl, b);
            }
            this.o();
        }
    }
    
    protected boolean do(final int n, final int n2) {
        if (this.eV) {
            return false;
        }
        int n3 = 0;
        int n4 = this.eR - 1;
        boolean b = false;
        while (n3 < this.eR && !super.dQ[0][n3].new) {
            ++n3;
        }
        if (n3 == this.eR) {
            return false;
        }
        while (n4 > n3 + 2 && !super.dQ[0][n4].new) {
            --n4;
        }
        if (n4 < n3 + 2) {
            return false;
        }
        int i = n3;
        int n5 = n4;
        while (i < n4 + 1) {
            while (i < n4 && !this.eW[i].new) {
                ++i;
            }
            if (((this.eW[i].for <= n2 && n2 < this.eW[n5].for) || (this.eW[n5].for <= n2 && n2 < this.eW[i].for)) && n < (this.eW[n5].int - this.eW[i].int) * (n2 - this.eW[i].for) / (this.eW[n5].for - this.eW[i].for) + this.eW[i].int) {
                b = !b;
            }
            n5 = i++;
        }
        return b;
    }
}
