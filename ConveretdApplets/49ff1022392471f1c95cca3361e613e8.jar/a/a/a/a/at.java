// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class at extends e
{
    aq c9;
    aq dc;
    float dw;
    int c0;
    ae dg;
    boolean dj;
    int du;
    int[] cY;
    float[] dr;
    int[] c3;
    int[] c6;
    ad c2;
    ae c4;
    int[] di;
    boolean dn;
    int c8;
    int c1;
    int dA;
    int dv;
    int dt;
    int ds;
    int dl;
    int c7;
    int[] de;
    int[] dk;
    int[] dm;
    int db;
    int dq;
    int da;
    int dp;
    private static char[] cZ;
    private static char[] dd;
    private static char[] df;
    private char[] dz;
    int dh;
    int dy;
    int c5;
    int dx;
    
    static {
        at.cZ = new char[] { 'd', 'a', 'z', 'z', 'l', 'e', 'c', 'o', 'l', 'o', 'r', '\0' };
        at.dd = new char[] { 'f', 'l', 'a', 'r', 'e', 'c', 'o', 'l', 'o', 'r', '\0' };
        at.df = new char[] { 's', 'i', 'z', 'e', '\0' };
    }
    
    public at() {
        this.dw = 0.0f;
        this.c0 = 0;
        this.dg = null;
        this.dj = false;
        this.du = 0;
        this.c2 = null;
        this.c4 = null;
        this.di = null;
        this.dn = false;
        this.c8 = 0;
        this.dz = new char[] { 'l', 'i', 'g', 'h', 't', '\0' };
    }
    
    public void a(final ac void1, final float cj, final ae goto1, final f cl, final m m) {
        super.void = void1;
        super.cj = cj;
        super.cm = 1.0f / super.cj;
        super.goto = goto1;
        super.cl = cl;
        super.for = 3;
        super.byte = this.dz;
        this.de = new int[512];
        this.dk = new int[512];
        this.dm = new int[512];
        for (int i = 256; i < 512; ++i) {
            this.de[i] = -65536;
            this.dk[i] = 65280;
            this.dm[i] = 255;
        }
    }
    
    public void int(final a2 a2) {
        this.c9 = new aq();
        this.dc = new aq();
        float n = 0.0f;
        float n2 = 0.0f;
        for (int i = 0; i < a2.do; ++i) {
            if (a2.try[i].toLowerCase().compareTo("id") == 0) {
                super.e = (String.valueOf(a2.new[i]) + "\u0000").toCharArray();
            }
            else if (a2.try[i].toLowerCase().compareTo("pan") == 0) {
                n = new Float(a2.new[i]) * super.cj;
            }
            else if (a2.try[i].toLowerCase().compareTo("tilt") == 0) {
                n2 = new Float(a2.new[i]) * super.cj;
            }
            else if (a2.try[i].toLowerCase().compareTo("visible") == 0) {
                if (a2.new[i].compareTo("false") == 0) {
                    super.do = false;
                }
            }
            else if (a2.try[i].toLowerCase().compareTo("dazzlecolor") == 0) {
                this.dn = true;
                final int a3 = ac.a(a2.new[i]);
                this.dt = (a3 >> 16 & 0xFF);
                this.ds = (a3 >> 8 & 0xFF);
                this.dl = (a3 & 0xFF);
            }
            else if (a2.try[i].toLowerCase().compareTo("flarecolor") == 0) {
                this.c8 |= 0x1;
                final int a4 = ac.a(a2.new[i]);
                this.c1 = (a4 >> 16 & 0xFF);
                this.dA = (a4 >> 8 & 0xFF);
                this.dv = (a4 & 0xFF);
            }
            else if (a2.try[i].toLowerCase().compareTo("size") == 0) {
                this.dw = new Float(a2.new[i]);
                this.c8 |= 0x2;
            }
            else if (a2.try[i].toLowerCase().compareTo("file") == 0) {
                this.dg = super.void.w.a(a2.new[i], super.goto, false, false, false);
                this.c8 |= 0x4;
            }
        }
        this.a();
        this.c9.byte = (float)Math.sin(n2);
        this.c9.try = (float)(Math.cos(n) * Math.cos(n2));
        this.c9.if = (float)(Math.sin(n) * Math.cos(n2));
        this.dc.byte = this.c9.byte;
        this.dc.try = this.c9.try;
        this.dc.if = this.c9.if;
    }
    
    void a(final float[][] array) {
        super.void.a(array, this.dc, this.c9);
    }
    
    public boolean a(final long n) {
        if (this.dj) {
            boolean a = false;
            if (this.c4 != null && this.c2 != null && this.c4.case && !this.c2.try) {
                this.c4.a(this.c2);
                super.try = this.c2.try;
                if (super.try) {
                    this.di = new int[256];
                    super.if = true;
                }
            }
            if (super.try && this.c2 != null && this.c2.try) {
                a = this.c2.a(n);
            }
            final boolean if1 = super.if;
            super.if = false;
            return (a & super.do) || if1;
        }
        if (this.c8 == 7 && this.dg != null && this.dg.b) {
            a2 do1 = null;
            if (this.dg.j != 0) {
                this.dg = this.dg.new[0];
            }
            final bf bf = new bf();
            try {
                y.a(bf, this.dg.k);
                do1 = bf.do;
            }
            catch (Exception ex) {}
            if (do1 == null) {
                this.dj = true;
                return false;
            }
            for (int i = 0; i < do1.do; ++i) {
                if (do1.try[i].toLowerCase().compareTo("xmlns") == 0) {
                    if (do1.new[i].toLowerCase().compareTo("http://www.immervision.com/lensflare") != 0) {
                        return false;
                    }
                }
                else if (do1.try[i].toLowerCase().compareTo("image") == 0) {
                    this.c2 = new ad();
                    this.c4 = super.void.w.a(do1.new[i], this.dg, false, true, false);
                }
            }
            for (a2 a2 = do1.if; a2 != null; a2 = a2.for) {
                if (a2.a.toLowerCase().compareTo("flare") == 0) {
                    this.m();
                    for (int j = 0; j < a2.do; ++j) {
                        if (a2.try[j].toLowerCase().compareTo("map") == 0) {
                            this.c6[this.du] = new Integer(a2.new[j]);
                        }
                        else if (a2.try[j].toLowerCase().compareTo("color") == 0) {
                            this.cY[this.du] = ac.a(a2.new[j]);
                        }
                        else if (a2.try[j].toLowerCase().compareTo("position") == 0) {
                            this.c3[this.du] = (int)(new Float(a2.new[j]) * 655.35);
                        }
                        else if (a2.try[j].toLowerCase().compareTo("size") == 0) {
                            this.dr[this.du] = new Float(a2.new[j]);
                        }
                    }
                    ++this.du;
                }
            }
            this.dj = true;
        }
        else if (this.c8 != 7) {
            this.dj = true;
            super.try = true;
        }
        return false;
    }
    
    public void new(final boolean b) {
        if (!super.try || !super.do) {
            return;
        }
        final int[] p = super.cl.em.p;
        this.c7 = super.cl.em.i;
        if (super.cl.et < super.cl.ey) {
            this.dh = super.cl.et >> 1;
            this.dy = super.cl.ey >> 1;
            this.c5 = (int)(super.cl.ey * 0.1);
            this.dx = this.dy + this.c5;
        }
        else {
            this.dh = super.cl.ey >> 1;
            this.dy = super.cl.et >> 1;
            this.c5 = (int)(super.cl.ey * 0.1);
            this.dx = this.dy + this.c5;
        }
        final int n = 0;
        final int n2 = 0;
        final int n3 = 0;
        if (this.dn || this.c8 == 7) {
            final float n4 = super.cl.eC * this.c9.try + super.cl.eq * this.c9.if;
            final float n5 = super.cl.eA * n4 + super.cl.eG * this.c9.byte;
            if (n5 < 0.01f) {
                return;
            }
            final float n6 = super.cl.ep / n5;
            final float n7 = (-super.cl.eC * this.c9.if + super.cl.eq * this.c9.try) * n6;
            final float n8 = -(super.cl.eA * this.c9.byte - super.cl.eG * n4) * n6;
            this.db = ((super.cl.em.h + super.cl.em.s > super.cl.em.i) ? super.cl.em.i : (super.cl.em.h + super.cl.em.s));
            this.dq = ((super.cl.em.h > 0) ? super.cl.em.h : 0);
            this.da = ((super.cl.em.g + super.cl.em.j > super.cl.em.t) ? super.cl.em.t : (super.cl.em.g + super.cl.em.j));
            this.dp = ((super.cl.em.g > 0) ? super.cl.em.g : 0);
            final int n9 = (int)Math.sqrt(n7 * n7 + n8 * n8);
            if (this.c8 == 7 && n9 < this.dy) {
                for (int i = 0; i < this.du; ++i) {
                    final int n10 = (int)((super.cl.et >> 1) + n7 - ((int)(n7 * this.c3[i]) >> 16));
                    final int n11 = (int)((super.cl.ey >> 1) + n8 - ((int)(n8 * this.c3[i]) >> 16));
                    final int n12 = (int)(65535.0f * (this.c2.i * 10000 / (this.dw * this.dr[i] * (super.cl.et + super.cl.ey) / 2.0f)));
                    final int n13 = this.c6[i] * this.c2.i * this.c2.i;
                    for (int j = 0; j < 256; ++j) {
                        this.di[j] = ((this.dv * j * (this.cY[i] >> 16) & 0xFF0000) | (this.dA * j * ((this.cY[i] & 0xFF00) >> 8) >> 8 & 0xFF00) | (this.c1 * j * (this.cY[i] & 0xFF) >> 16 & 0xFF));
                    }
                    this.a(n10, n11, n12, n13, p);
                }
            }
            else if (this.c8 == 7 && n9 < this.dx) {
                final float n14 = (this.dx - n9) / this.c5;
                for (int k = 0; k < this.du; ++k) {
                    final int n15 = (int)((super.cl.et >> 1) + n7 - ((int)(n7 * this.c3[k]) >> 16));
                    final int n16 = (int)((super.cl.ey >> 1) + n8 - ((int)(n8 * this.c3[k]) >> 16));
                    final int n17 = (int)(65535.0f * (this.c2.i * 10000 / (this.dw * this.dr[k] * (super.cl.et + super.cl.ey) / 2.0f)));
                    final int n18 = this.c6[k] * this.c2.i * this.c2.i;
                    for (int l = 0; l < 256; ++l) {
                        this.di[l] = (((int)(this.dv * l * (this.cY[k] >> 16) * n14) & 0xFF0000) | ((int)(this.dA * l * ((this.cY[k] & 0xFF00) >> 8) * n14) >> 8 & 0xFF00) | ((int)(this.c1 * l * (this.cY[k] & 0xFF) * n14) >> 16 & 0xFF));
                    }
                    this.a(n15, n16, n17, n18, p);
                }
            }
            if (this.dn && n9 < this.dh && Math.abs(n7) < this.dh && Math.abs(n8) < this.dh) {
                int n19 = n + (this.dt * (this.dh - n9) / this.dh & 0xFF);
                if (n19 > 255) {
                    n19 = 255;
                }
                int n20 = n2 + (this.ds * (this.dh - n9) / this.dh & 0xFF);
                if (n20 > 255) {
                    n20 = 255;
                }
                int n21 = n3 + (this.dl * (this.dh - n9) / this.dh & 0xFF);
                if (n21 > 255) {
                    n21 = 255;
                }
                this.a(n21 << 16, n20 << 8, n19, p);
            }
        }
    }
    
    private void a(int n, int n2, final int n3, final int n4, final int[] array) {
        n += super.cl.em.h;
        n2 += super.cl.em.g;
        final int n5 = this.c2.i >> 1;
        final int n6 = (n5 << 16) / n3;
        final int n7;
        if ((n7 = ((n - n6 < this.dq) ? this.dq : (n - n6))) > super.cl.et) {
            return;
        }
        final int n8;
        if ((n8 = ((n2 - n6 < this.dp) ? this.dp : (n2 - n6))) > super.cl.ey) {
            return;
        }
        final int n9;
        if ((n9 = ((n + n6 > this.db) ? this.db : (n + n6))) < this.dq) {
            return;
        }
        final int n10;
        if ((n10 = ((n2 + n6 > this.da) ? this.da : (n2 + n6))) < this.dp) {
            return;
        }
        int n11 = n8 * this.c7;
        int n12 = (n8 - n2) * n3 + (n5 << 16);
        final int n13 = (n7 - n) * n3 + (n5 << 16);
        for (int i = n8; i < n10; ++i) {
            int n14 = n13;
            for (int j = n7; j < n9; ++j) {
                final int n15 = array[j + n11];
                final int n16 = this.di[this.c2.p[n4 + (n14 >> 16) + (n12 >> 16) * this.c2.i] & 0xFF];
                final int n17;
                final int n18;
                final int n19;
                array[j + n11] = ((((n17 = (n15 & 0xFF) + (n16 & 0xFF)) > 255) ? 255 : n17) | (((n18 = (n15 & 0xFF00) + (n16 & 0xFF00)) > 65280) ? 65280 : n18) | (((n19 = (n15 & 0xFF0000) + (n16 & 0xFF0000)) > 16711680) ? 16711680 : n19) | 0xFF000000);
                n14 += n3;
            }
            n11 += this.c7;
            n12 += n3;
        }
    }
    
    void a(final int n, final int n2, final int n3, final int[] array) {
        int n4 = this.dp * this.c7 + this.dq;
        for (int i = this.dp; i < this.da; ++i) {
            int n5 = n4;
            for (int j = this.dq; j < this.db; ++j) {
                final int n6 = array[n5];
                final int n7;
                final int n8;
                final int n9;
                array[n5] = ((((n7 = (n6 & 0xFF) + n3) > 255) ? 255 : n7) | (((n8 = (n6 & 0xFF00) + n2) > 65280) ? 65280 : n8) | (((n9 = (n6 & 0xFF0000) + n) > 16711680) ? 16711680 : n9) | 0xFF000000);
                ++n5;
            }
            n4 += this.c7;
        }
    }
    
    private void m() {
        if (this.c6 == null || this.c6.length == this.du) {
            final int[] cy = new int[this.du + 10];
            final float[] dr = new float[this.du + 10];
            final int[] c3 = new int[this.du + 10];
            final int[] c4 = new int[this.du + 10];
            for (int i = 0; i < this.du; ++i) {
                cy[i] = this.cY[i];
                dr[i] = this.dr[i];
                c3[i] = this.c3[i];
                c4[i] = this.c6[i];
            }
            this.cY = cy;
            this.dr = dr;
            this.c3 = c3;
            this.c6 = c4;
        }
    }
    
    public void a(final char[] array, final a3 a3) {
        if (g.if(array, ac.K) == 0) {
            super.do = al.a(a3);
            super.if = true;
        }
        else if (g.if(array, at.cZ) == 0) {
            if (a3.char == 2) {
                if ((a3.case & 0xFFFFFFL) == 0x0L) {
                    this.dn = false;
                }
                else {
                    this.dn = true;
                    this.dt = ((int)(a3.case >> 16) & 0xFF);
                    this.ds = ((int)(a3.case >> 8) & 0xFF);
                    this.dl = ((int)a3.case & 0xFF);
                }
            }
        }
        else if (g.if(array, at.dd) == 0) {
            if (a3.char == 2) {
                if ((a3.case & 0xFFFFFFL) == 0x0L) {
                    this.c8 = 0;
                }
                else {
                    this.c8 = 7;
                    this.c1 = ((int)(a3.case >> 16) & 0xFF);
                    this.dA = ((int)(a3.case >> 8) & 0xFF);
                    this.dv = ((int)a3.case & 0xFF);
                }
            }
        }
        else if (g.if(array, ac.G) == 0) {
            if (a3.char == 2 || a3.char == 3) {
                super.cn = (float)((a3.char == 2) ? a3.case : a3.else);
            }
        }
        else if (g.if(array, ac.try) == 0 && (a3.char == 2 || a3.char == 3)) {
            super.cg = (float)((a3.char == 2) ? a3.case : a3.else);
        }
    }
    
    public a3 a(final char[] array) {
        if (g.if(array, ac.K) == 0) {
            super.int.char = 1;
            super.int.long = super.do;
            return super.int;
        }
        if (g.if(array, at.cZ) == 0) {
            super.int.char = 2;
            super.int.case = (this.dn ? (0xFF000000 | this.dt << 16 | this.ds << 8 | this.dl) : 0);
            return super.int;
        }
        if (g.if(array, at.dd) == 0) {
            super.int.char = 2;
            super.int.case = ((this.c8 == 7) ? (0xFF000000 | this.c1 << 16 | this.dA << 8 | this.dv) : 0);
            return super.int;
        }
        if (g.if(array, at.df) == 0) {
            super.int.char = 3;
            super.int.else = this.dw;
            return super.int;
        }
        return ac.a(super.int);
    }
    
    public void if() {
        if (this.c2 != null) {
            this.c2.if();
        }
    }
}
