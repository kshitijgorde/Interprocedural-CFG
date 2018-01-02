// 
// Decompiled by Procyon v0.5.30
// 

public class g extends a
{
    al ag;
    al ac;
    float Q;
    int an;
    aa J;
    boolean ae;
    int aa;
    int[] aq;
    float[] ap;
    int[] Z;
    int[] M;
    an Y;
    aa W;
    int[] L;
    boolean ah;
    int T;
    int X;
    int U;
    int O;
    int K;
    int ao;
    int ad;
    int N;
    int[] P;
    int[] V;
    int[] ab;
    int al;
    int am;
    int ai;
    int ak;
    int af;
    int S;
    int aj;
    int R;
    
    public g() {
        this.Q = 0.0f;
        this.an = 0;
        this.J = null;
        this.ae = false;
        this.aa = 0;
        this.Y = null;
        this.W = null;
        this.L = null;
        this.ah = false;
        this.T = 0;
    }
    
    public void a(final l int1, final float e, final aa byte1, final t g, final v v) {
        super.int = int1;
        super.E = e;
        super.H = 1.0f / super.E;
        super.byte = byte1;
        super.G = g;
        this.P = new int[512];
        this.V = new int[512];
        this.ab = new int[512];
        for (int i = 256; i < 512; ++i) {
            this.P[i] = -65536;
            this.V[i] = 65280;
            this.ab[i] = 255;
        }
    }
    
    public void if(final n n) {
        this.ag = new al();
        this.ac = new al();
        float n2 = 0.0f;
        float n3 = 0.0f;
        for (int i = 0; i < n.do; ++i) {
            if (n.try[i].toLowerCase().compareTo("pan") == 0) {
                n2 = new Float(n.new[i]) * super.E;
            }
            else if (n.try[i].toLowerCase().compareTo("tilt") == 0) {
                n3 = new Float(n.new[i]) * super.E;
            }
            else if (n.try[i].toLowerCase().compareTo("visible") == 0) {
                if (n.new[i].compareTo("false") == 0) {
                    super.if = false;
                }
            }
            else if (n.try[i].toLowerCase().compareTo("dazzlecolor") == 0) {
                this.ah = true;
                final int a = l.a(n.new[i]);
                this.K = (a >> 16 & 0xFF);
                this.ao = (a >> 8 & 0xFF);
                this.ad = (a & 0xFF);
            }
            else if (n.try[i].toLowerCase().compareTo("flarecolor") == 0) {
                this.T |= 0x1;
                final int a2 = l.a(n.new[i]);
                this.X = (a2 >> 16 & 0xFF);
                this.U = (a2 >> 8 & 0xFF);
                this.O = (a2 & 0xFF);
            }
            else if (n.try[i].toLowerCase().compareTo("size") == 0) {
                this.Q = new Float(n.new[i]);
                this.T |= 0x2;
            }
            else if (n.try[i].toLowerCase().compareTo("file") == 0) {
                this.J = super.int.n.a(n.new[i], super.byte, false, false, false);
                this.T |= 0x4;
            }
        }
        this.ag.byte = (float)Math.sin(n3);
        this.ag.try = (float)(Math.cos(n2) * Math.cos(n3));
        this.ag.if = (float)(Math.sin(n2) * Math.cos(n3));
        this.ac.byte = this.ag.byte;
        this.ac.try = this.ag.try;
        this.ac.if = this.ag.if;
    }
    
    void a(final float[][] array) {
        super.int.a(array, this.ac, this.ag);
    }
    
    public boolean a(final long n) {
        if (this.ae) {
            boolean a = false;
            if (this.W != null && this.Y != null && this.W.byte && !this.Y.for) {
                this.W.a(this.Y);
                super.for = this.Y.for;
                if (super.for) {
                    this.L = new int[256];
                    super.do = true;
                }
            }
            if (super.for && this.Y != null && this.Y.for) {
                a = this.Y.a(n);
            }
            final boolean do1 = super.do;
            super.do = false;
            return (a & super.if) || do1;
        }
        if (this.T == 7 && this.J != null && this.J.void) {
            n do2 = null;
            if (this.J.j != 0) {
                this.J = this.J.int[0];
            }
            final ae ae = new ae();
            try {
                q.a(ae, this.J.k, this.J.if);
                do2 = ae.do;
            }
            catch (Exception ex) {}
            if (do2 == null) {
                this.ae = true;
                return false;
            }
            for (int i = 0; i < do2.do; ++i) {
                if (do2.try[i].toLowerCase().compareTo("xmlns") == 0) {
                    if (do2.new[i].toLowerCase().compareTo("http://www.immervision.com/lensflare") != 0) {
                        return false;
                    }
                }
                else if (do2.try[i].toLowerCase().compareTo("image") == 0) {
                    this.Y = new an();
                    this.W = super.int.n.a(do2.new[i], this.J, false, true, false);
                }
            }
            for (n n2 = do2.if; n2 != null; n2 = n2.for) {
                if (n2.a.toLowerCase().compareTo("flare") == 0) {
                    this.new();
                    for (int j = 0; j < n2.do; ++j) {
                        if (n2.try[j].toLowerCase().compareTo("map") == 0) {
                            this.M[this.aa] = new Integer(n2.new[j]);
                        }
                        else if (n2.try[j].toLowerCase().compareTo("color") == 0) {
                            this.aq[this.aa] = l.a(n2.new[j]);
                        }
                        else if (n2.try[j].toLowerCase().compareTo("position") == 0) {
                            this.Z[this.aa] = (int)(new Float(n2.new[j]) * 655.35);
                        }
                        else if (n2.try[j].toLowerCase().compareTo("size") == 0) {
                            this.ap[this.aa] = new Float(n2.new[j]);
                        }
                    }
                    ++this.aa;
                }
            }
            this.ae = true;
        }
        else if (this.T != 7) {
            this.ae = true;
            super.for = true;
        }
        return false;
    }
    
    public void a(final boolean b) {
        if (!super.for || !super.if) {
            return;
        }
        final int[] c = super.G.a5.c;
        this.N = super.G.a5.long;
        if (super.G.a1 < super.G.aS) {
            this.af = super.G.a1 >> 1;
            this.S = super.G.aS >> 1;
            this.aj = (int)(super.G.aS * 0.1);
            this.R = this.S + this.aj;
        }
        else {
            this.af = super.G.aS >> 1;
            this.S = super.G.a1 >> 1;
            this.aj = (int)(super.G.aS * 0.1);
            this.R = this.S + this.aj;
        }
        final int n = 0;
        final int n2 = 0;
        final int n3 = 0;
        if (this.ah || this.T == 7) {
            final float n4 = super.G.bc * this.ag.try + super.G.aI * this.ag.if;
            final float n5 = super.G.bi * n4 + super.G.bB * this.ag.byte;
            if (n5 < 0.01f) {
                return;
            }
            final float n6 = super.G.aZ / n5;
            final float n7 = (-super.G.bc * this.ag.if + super.G.aI * this.ag.try) * n6;
            final float n8 = -(super.G.bi * this.ag.byte - super.G.bB * n4) * n6;
            this.al = ((super.G.a5.goto + super.G.a5.d > super.G.a5.long) ? super.G.a5.long : (super.G.a5.goto + super.G.a5.d));
            this.am = ((super.G.a5.goto > 0) ? super.G.a5.goto : 0);
            this.ai = ((super.G.a5.else + super.G.a5.void > super.G.a5.e) ? super.G.a5.e : (super.G.a5.else + super.G.a5.void));
            this.ak = ((super.G.a5.else > 0) ? super.G.a5.else : 0);
            final int n9 = (int)Math.sqrt(n7 * n7 + n8 * n8);
            if (this.T == 7 && n9 < this.S) {
                for (int i = 0; i < this.aa; ++i) {
                    final int n10 = (int)((super.G.a1 >> 1) + n7 - ((int)(n7 * this.Z[i]) >> 16));
                    final int n11 = (int)((super.G.aS >> 1) + n8 - ((int)(n8 * this.Z[i]) >> 16));
                    final int n12 = (int)(65535.0f * (this.Y.long * 10000 / (this.Q * this.ap[i] * (super.G.a1 + super.G.aS) / 2.0f)));
                    final int n13 = this.M[i] * this.Y.long * this.Y.long;
                    for (int j = 0; j < 256; ++j) {
                        this.L[j] = ((this.O * j * (this.aq[i] >> 16) & 0xFF0000) | (this.U * j * ((this.aq[i] & 0xFF00) >> 8) >> 8 & 0xFF00) | (this.X * j * (this.aq[i] & 0xFF) >> 16 & 0xFF));
                    }
                    this.a(n10, n11, n12, n13, c);
                }
            }
            else if (this.T == 7 && n9 < this.R) {
                final float n14 = (this.R - n9) / this.aj;
                for (int k = 0; k < this.aa; ++k) {
                    final int n15 = (int)((super.G.a1 >> 1) + n7 - ((int)(n7 * this.Z[k]) >> 16));
                    final int n16 = (int)((super.G.aS >> 1) + n8 - ((int)(n8 * this.Z[k]) >> 16));
                    final int n17 = (int)(65535.0f * (this.Y.long * 10000 / (this.Q * this.ap[k] * (super.G.a1 + super.G.aS) / 2.0f)));
                    final int n18 = this.M[k] * this.Y.long * this.Y.long;
                    for (int l = 0; l < 256; ++l) {
                        this.L[l] = (((int)(this.O * l * (this.aq[k] >> 16) * n14) & 0xFF0000) | ((int)(this.U * l * ((this.aq[k] & 0xFF00) >> 8) * n14) >> 8 & 0xFF00) | ((int)(this.X * l * (this.aq[k] & 0xFF) * n14) >> 16 & 0xFF));
                    }
                    this.a(n15, n16, n17, n18, c);
                }
            }
            if (this.ah && n9 < this.af && Math.abs(n7) < this.af && Math.abs(n8) < this.af) {
                int n19 = n + (this.K * (this.af - n9) / this.af & 0xFF);
                if (n19 > 255) {
                    n19 = 255;
                }
                int n20 = n2 + (this.ao * (this.af - n9) / this.af & 0xFF);
                if (n20 > 255) {
                    n20 = 255;
                }
                int n21 = n3 + (this.ad * (this.af - n9) / this.af & 0xFF);
                if (n21 > 255) {
                    n21 = 255;
                }
                this.a(n21 << 16, n20 << 8, n19, c);
            }
        }
    }
    
    private void a(int n, int n2, final int n3, final int n4, final int[] array) {
        n += super.G.a5.goto;
        n2 += super.G.a5.else;
        final int n5 = this.Y.long >> 1;
        final int n6 = (n5 << 16) / n3;
        final int n7;
        if ((n7 = ((n - n6 < this.am) ? this.am : (n - n6))) > super.G.a1) {
            return;
        }
        final int n8;
        if ((n8 = ((n2 - n6 < this.ak) ? this.ak : (n2 - n6))) > super.G.aS) {
            return;
        }
        final int n9;
        if ((n9 = ((n + n6 > this.al) ? this.al : (n + n6))) < this.am) {
            return;
        }
        final int n10;
        if ((n10 = ((n2 + n6 > this.ai) ? this.ai : (n2 + n6))) < this.ak) {
            return;
        }
        int n11 = n8 * this.N;
        int n12 = (n8 - n2) * n3 + (n5 << 16);
        final int n13 = (n7 - n) * n3 + (n5 << 16);
        for (int i = n8; i < n10; ++i) {
            int n14 = n13;
            for (int j = n7; j < n9; ++j) {
                final int n15 = array[j + n11];
                final int n16 = this.L[this.Y.c[n4 + (n14 >> 16) + (n12 >> 16) * this.Y.long] & 0xFF];
                final int n17;
                final int n18;
                final int n19;
                array[j + n11] = ((((n17 = (n15 & 0xFF) + (n16 & 0xFF)) > 255) ? 255 : n17) | (((n18 = (n15 & 0xFF00) + (n16 & 0xFF00)) > 65280) ? 65280 : n18) | (((n19 = (n15 & 0xFF0000) + (n16 & 0xFF0000)) > 16711680) ? 16711680 : n19) | 0xFF000000);
                n14 += n3;
            }
            n11 += this.N;
            n12 += n3;
        }
    }
    
    void a(final int n, final int n2, final int n3, final int[] array) {
        int n4 = this.ak * this.N + this.am;
        for (int i = this.ak; i < this.ai; ++i) {
            int n5 = n4;
            for (int j = this.am; j < this.al; ++j) {
                final int n6 = array[n5];
                final int n7;
                final int n8;
                final int n9;
                array[n5] = ((((n7 = (n6 & 0xFF) + n3) > 255) ? 255 : n7) | (((n8 = (n6 & 0xFF00) + n2) > 65280) ? 65280 : n8) | (((n9 = (n6 & 0xFF0000) + n) > 16711680) ? 16711680 : n9) | 0xFF000000);
                ++n5;
            }
            n4 += this.N;
        }
    }
    
    private void new() {
        if (this.M == null || this.M.length == this.aa) {
            final int[] aq = new int[this.aa + 10];
            final float[] ap = new float[this.aa + 10];
            final int[] z = new int[this.aa + 10];
            final int[] m = new int[this.aa + 10];
            for (int i = 0; i < this.aa; ++i) {
                aq[i] = this.aq[i];
                ap[i] = this.ap[i];
                z[i] = this.Z[i];
                m[i] = this.M[i];
            }
            this.aq = aq;
            this.ap = ap;
            this.Z = z;
            this.M = m;
        }
    }
    
    public void a() {
        if (this.Y != null) {
            this.Y.a();
        }
    }
}
