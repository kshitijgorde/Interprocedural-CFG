// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class w extends ao
{
    float[] fc;
    float[] fb;
    float fa;
    float fg;
    double[] ff;
    double[] fd;
    private boolean fh;
    private char[] fe;
    
    public w() {
        this.fh = false;
        this.fe = new char[] { 'p', 'a', 'n', 'o', 'F', 'l', 'a', 't', '\0' };
    }
    
    public void if() {
        super.if();
        this.ff = null;
        this.fd = null;
        this.fc = null;
        this.fb = null;
        this.fe = null;
    }
    
    public void a(final ac b, final float ca, final ae long1, final f cc, final m cb) {
        super.b = b;
        super.cA = ca;
        super.cD = 1.0f / super.cA;
        super.long = long1;
        super.cC = cc;
        super.cB = cb;
        super.goto = cb.h;
        this.ff = new double[4];
        this.fd = new double[4];
        super.int = 1;
        super.case = this.fe;
    }
    
    public void int(final a2 a2) {
        super.int(a2);
        a2 a3 = a2.if;
        this.fh = true;
        while (a3 != null) {
            if (a3.a.toLowerCase().compareTo("rectangle") == 0) {
                this.fh = false;
                this.fc = new float[4];
                this.fb = new float[4];
                a2 a4 = a3.if;
                int n = 0;
                while (a4 != null) {
                    if (a4.a.toLowerCase().compareTo("vertex") == 0) {
                        for (int i = 0; i < a4.do; ++i) {
                            if (a4.try[i].toLowerCase().compareTo("pan") == 0) {
                                this.fc[n] = new Float(a4.new[i]) * super.cA;
                            }
                            else if (a4.try[i].toLowerCase().compareTo("tilt") == 0) {
                                this.fb[n] = new Float(a4.new[i]) * super.cA;
                            }
                        }
                        if (++n == 4) {
                            break;
                        }
                    }
                    a4 = a4.for;
                }
                if (n != 4) {
                    this.fh = true;
                }
            }
            a3 = a3.for;
        }
    }
    
    protected void r() {
        if (this.fh) {
            return;
        }
        final aq[] array = new aq[4];
        this.fa = (this.fc[0] + this.fc[1] + this.fc[2] + this.fc[3]) / 4.0f;
        this.fg = (this.fb[0] + this.fb[1] + this.fb[2] + this.fb[3]) / 4.0f;
        for (int i = 0; i < 4; ++i) {
            final float n = (float)Math.sin(this.fb[i]);
            final float n2 = (float)(Math.cos(this.fc[i]) * Math.cos(this.fb[i]));
            final float n3 = (float)(Math.sin(this.fc[i]) * Math.cos(this.fb[i]));
            final float n4 = (float)(Math.cos(this.fa) * Math.cos(this.fg));
            final float n5 = (float)(Math.cos(this.fa) * n2 + Math.sin(this.fa) * n3);
            final float n6 = 1.0f / (float)(Math.cos(this.fg) * n5 + Math.sin(this.fg) * n);
            this.ff[i] = (-Math.cos(this.fa) * n3 + Math.sin(this.fa) * n2) * n6;
            this.fd[i] = -(Math.cos(this.fg) * n - Math.sin(this.fg) * n5) * n6;
            final double sqrt = Math.sqrt(this.ff[i] * this.ff[i] + this.fd[i] * this.fd[i] + 1.0);
            array[i] = new aq();
            array[i].byte = (float)sqrt * (float)Math.sin(this.fb[i]);
            array[i].try = (float)sqrt * (float)(Math.cos(this.fc[i]) * Math.cos(this.fb[i]));
            array[i].if = (float)sqrt * (float)(Math.sin(this.fc[i]) * Math.cos(this.fb[i]));
        }
        super.ej = (int)(17.0f * super.b.ar / 100.0f);
        super.eh = (int)(17.0f * super.b.ar / 100.0f);
        (super.d9 = new aq[1][])[0] = new aq[super.ej * super.eh];
        (super.ef = new aq[1][])[0] = new aq[super.ej * super.eh];
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
                super.d9[0][n15] = new aq();
                super.ef[0][n15] = new aq();
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
            if (super.dX != null && super.d9 != null && !this.fh && !super.c) {
                super.dX.for();
                super.b.W.a(super.d9[0], super.ej, super.eh, super.dX, super.cC, b);
            }
            this.o();
        }
    }
}
