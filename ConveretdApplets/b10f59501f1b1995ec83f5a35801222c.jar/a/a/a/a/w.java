// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class w extends ao
{
    float[] fb;
    float[] fa;
    float e9;
    float ff;
    double[] fe;
    double[] fc;
    private boolean fg;
    private char[] fd;
    
    public w() {
        this.fg = false;
        this.fd = new char[] { 'p', 'a', 'n', 'o', 'F', 'l', 'a', 't', '\0' };
    }
    
    public void if() {
        super.if();
        this.fe = null;
        this.fc = null;
        this.fb = null;
        this.fa = null;
        this.fd = null;
    }
    
    public void a(final ac b, final float cz, final ae long1, final f cb, final m ca) {
        super.b = b;
        super.cz = cz;
        super.cC = 1.0f / super.cz;
        super.long = long1;
        super.cB = cb;
        super.cA = ca;
        super.goto = ca.h;
        this.fe = new double[4];
        this.fc = new double[4];
        super.int = 1;
        super.case = this.fd;
    }
    
    public void int(final a2 a2) {
        super.int(a2);
        a2 a3 = a2.if;
        this.fg = true;
        while (a3 != null) {
            if (a3.a.toLowerCase().compareTo("rectangle") == 0) {
                this.fg = false;
                this.fb = new float[4];
                this.fa = new float[4];
                a2 a4 = a3.if;
                int n = 0;
                while (a4 != null) {
                    if (a4.a.toLowerCase().compareTo("vertex") == 0) {
                        for (int i = 0; i < a4.do; ++i) {
                            if (a4.try[i].toLowerCase().compareTo("pan") == 0) {
                                this.fb[n] = new Float(a4.new[i]) * super.cz;
                            }
                            else if (a4.try[i].toLowerCase().compareTo("tilt") == 0) {
                                this.fa[n] = new Float(a4.new[i]) * super.cz;
                            }
                        }
                        if (++n == 4) {
                            break;
                        }
                    }
                    a4 = a4.for;
                }
                if (n != 4) {
                    this.fg = true;
                }
            }
            a3 = a3.for;
        }
    }
    
    protected void t() {
        if (this.fg) {
            return;
        }
        final aq[] array = new aq[4];
        this.e9 = (this.fb[0] + this.fb[1] + this.fb[2] + this.fb[3]) / 4.0f;
        this.ff = (this.fa[0] + this.fa[1] + this.fa[2] + this.fa[3]) / 4.0f;
        for (int i = 0; i < 4; ++i) {
            final float n = (float)Math.sin(this.fa[i]);
            final float n2 = (float)(Math.cos(this.fb[i]) * Math.cos(this.fa[i]));
            final float n3 = (float)(Math.sin(this.fb[i]) * Math.cos(this.fa[i]));
            final float n4 = (float)(Math.cos(this.e9) * Math.cos(this.ff));
            final float n5 = (float)(Math.cos(this.e9) * n2 + Math.sin(this.e9) * n3);
            final float n6 = 1.0f / (float)(Math.cos(this.ff) * n5 + Math.sin(this.ff) * n);
            this.fe[i] = (-Math.cos(this.e9) * n3 + Math.sin(this.e9) * n2) * n6;
            this.fc[i] = -(Math.cos(this.ff) * n - Math.sin(this.ff) * n5) * n6;
            final double sqrt = Math.sqrt(this.fe[i] * this.fe[i] + this.fc[i] * this.fc[i] + 1.0);
            array[i] = new aq();
            array[i].byte = (float)sqrt * (float)Math.sin(this.fa[i]);
            array[i].try = (float)sqrt * (float)(Math.cos(this.fb[i]) * Math.cos(this.fa[i]));
            array[i].if = (float)sqrt * (float)(Math.sin(this.fb[i]) * Math.cos(this.fa[i]));
        }
        super.ei = (int)(17.0f * super.b.ap / 100.0f);
        super.eg = (int)(17.0f * super.b.ap / 100.0f);
        (super.d8 = new aq[1][])[0] = new aq[super.ei * super.eg];
        (super.ee = new aq[1][])[0] = new aq[super.ei * super.eg];
        final float n7 = (super.dW.j - 1) / (super.ei - 1);
        final float n8 = (super.dW.u - 1) / (super.eg - 1);
        for (int j = 0; j < super.eg; ++j) {
            final float n9 = array[0].try + j * (-array[0].try + array[3].try) / (super.eg - 1.0f);
            final float n10 = array[1].try + j * (-array[1].try + array[2].try) / (super.eg - 1.0f);
            final float n11 = array[0].if + j * (-array[0].if + array[3].if) / (super.eg - 1.0f);
            final float n12 = array[1].if + j * (-array[1].if + array[2].if) / (super.eg - 1.0f);
            final float n13 = array[0].byte + j * (-array[0].byte + array[3].byte) / (super.eg - 1.0f);
            final float n14 = array[1].byte + j * (-array[1].byte + array[2].byte) / (super.eg - 1.0f);
            for (int k = 0; k < super.ei; ++k) {
                final int n15 = k + j * super.ei;
                super.d8[0][n15] = new aq();
                super.ee[0][n15] = new aq();
                super.d8[0][n15].a = j * n8;
                super.d8[0][n15].do = k * n7;
                super.d8[0][n15].if = n11 + k * (n12 - n11) / (super.ei - 1.0f);
                super.d8[0][n15].try = n9 + k * (n10 - n9) / (super.ei - 1.0f);
                super.d8[0][n15].byte = n13 + k * (n14 - n13) / (super.ei - 1.0f);
                super.ee[0][n15].do = super.d8[0][n15].do;
                super.ee[0][n15].a = super.d8[0][n15].a;
                super.ee[0][n15].try = super.d8[0][n15].try;
                super.ee[0][n15].if = super.d8[0][n15].if;
                super.ee[0][n15].byte = super.d8[0][n15].byte;
            }
        }
    }
    
    public void new(final boolean b) {
        if (super.byte && super.for) {
            if (super.dW != null && super.d8 != null && !this.fg && !super.c) {
                super.dW.for();
                super.b.V.a(super.d8[0], super.ei, super.eg, super.dW, super.cB, b);
            }
            this.q();
        }
    }
}
