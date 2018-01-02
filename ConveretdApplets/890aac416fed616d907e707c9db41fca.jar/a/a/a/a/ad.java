// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class ad extends a2
{
    private char[] fu;
    private int[] fn;
    private int[] fv;
    private int[] fy;
    private int[] fw;
    private int[] fo;
    private int[] fs;
    int ft;
    int fp;
    long fq;
    boolean fm;
    private boolean fr;
    private boolean fx;
    
    public ad() {
        this.fu = new char[] { 's', 'f', 'x', '\0' };
        this.ft = 0;
        this.fp = 255;
        this.fq = 0L;
        this.fm = false;
        this.fr = true;
        this.fx = true;
    }
    
    public void a(final an b, final float ca, final aq long1, final h cc, final t cb) {
        super.b = b;
        super.cA = ca;
        super.cD = 1.0f / super.cA;
        super.long = long1;
        super.cC = cc;
        super.cB = cb;
        super.goto = cb.h;
        super.int = 1;
        super.case = this.fu;
        this.fn = new int[256];
        this.fv = new int[256];
        this.fy = new int[256];
        this.fw = new int[256];
        this.fo = new int[256];
        this.fs = new int[256];
    }
    
    public void int(final bh bh) {
        for (int i = 0; i < bh.do; ++i) {
            if (bh.try[i].toLowerCase().compareTo("id") == 0) {
                super.f = (String.valueOf(bh.new[i]) + "\u0000").toCharArray();
            }
            else if (bh.try[i].toLowerCase().compareTo("layer") == 0) {
                super.d = new Integer(bh.new[i]);
            }
            else if (bh.try[i].toLowerCase().compareTo("visible") == 0) {
                if (bh.new[i].compareTo("false") == 0) {
                    super.for = false;
                }
            }
            else if (bh.try[i].toLowerCase().compareTo("play") == 0) {
                if (bh.new[i].compareTo("false") == 0) {
                    super.ek = false;
                }
            }
            else if (bh.try[i].toLowerCase().compareTo("egaliser") == 0) {
                if (bh.new[i].compareTo("false") == 0) {
                    this.fr = false;
                }
            }
            else if (bh.try[i].toLowerCase().compareTo("expension") == 0 && bh.new[i].compareTo("false") == 0) {
                this.fx = false;
            }
        }
        this.a();
        super.byte = true;
        super.goto = true;
        super.else = true;
    }
    
    public boolean a(final long n) {
        this.l();
        return super.for && super.byte && this.fm;
    }
    
    boolean byte(final boolean b) {
        return super.byte = true;
    }
    
    void new(final long n) {
        super.byte = true;
    }
    
    public void new(final boolean b) {
        if (super.goto && super.byte && super.for) {
            try {
                final ap ef = super.cC.eF;
                if (ef.s < -ef.n || ef.r < -ef.m || ef.n > ef.s || ef.m > ef.r) {
                    return;
                }
                int l = ef.l;
                int p = ef.p;
                int n = ef.n;
                int m = ef.m;
                if (n < 0) {
                    l += n;
                    n = 0;
                }
                if (m < 0) {
                    p += m;
                    m = 0;
                }
                if (n + l > ef.s) {
                    l = ef.s - n;
                }
                if (m + p > ef.r) {
                    p = ef.r - m;
                }
                final int n2 = n + m * ef.s;
                final int n3 = ef.s - l;
                final int n4 = n2;
                if (this.fr) {
                    this.if(n4, n3, l, p);
                }
                if (this.fx) {
                    this.a(n2, n3, l, p);
                }
            }
            catch (Exception ex) {}
        }
    }
    
    private void a(int n, final int n2, final int n3, final int n4) {
        int n5 = 0;
        int n6 = 0;
        int n7 = 0;
        int n8 = 16711680;
        int n9 = 65280;
        int n10 = 255;
        int n11 = n;
        for (int i = 0; i < n4; ++i) {
            for (int j = 0; j < n3; ++j) {
                final int n12 = super.cC.eF.x[n11];
                final int n13 = n12 & 0xFF0000;
                n5 = ((n5 > n13) ? n5 : n13);
                n8 = ((n8 < n13) ? n8 : n13);
                final int n14 = n12 & 0xFF00;
                n6 = ((n6 > n14) ? n6 : n14);
                n9 = ((n9 < n14) ? n9 : n14);
                final int n15 = n12 & 0xFF;
                n7 = ((n7 > n15) ? n7 : n15);
                n10 = ((n10 < n15) ? n10 : n15);
                ++n11;
            }
            n11 += n2;
        }
        final int n16 = n5 >> 16;
        final int n17 = n8 >> 16;
        final int n18 = n6 >> 8;
        final int n19 = n9 >> 8;
        final int n20 = (n16 > n18) ? n16 : n18;
        int n21 = (n20 > n7) ? n20 : n7;
        final int n22 = (n17 < n19) ? n17 : n19;
        int n23 = (n22 < n10) ? n22 : n10;
        if (n21 == n23) {
            if (n21 == 0) {
                ++n21;
            }
            else {
                --n23;
            }
        }
        final double n24 = 255.0 / (n21 - n23);
        for (int k = n23; k <= n21; ++k) {
            this.fy[k] = (int)((k - n23) * n24);
            if (this.fy[k] < 0) {
                this.fy[k] = 0;
            }
            if (this.fy[k] > 255) {
                this.fy[k] = 255;
            }
            this.fv[k] = this.fy[k] << 8;
            this.fn[k] = this.fy[k] << 16;
        }
        for (int l = 0; l < n4; ++l) {
            for (int n25 = 0; n25 < n3; ++n25) {
                final int n26 = super.cC.eF.x[n];
                super.cC.eF.x[n++] = (0xFF000000 | this.fn[(n26 & 0xFF0000) >> 16] | this.fv[(n26 & 0xFF00) >> 8] | this.fy[n26 & 0xFF]);
            }
            n += n2;
        }
    }
    
    private void if(int n, final int n2, final int n3, final int n4) {
        int n5 = n;
        for (int i = 0; i < 256; ++i) {
            this.fw[i] = 0;
            this.fo[i] = 0;
            this.fs[i] = 0;
        }
        for (int j = 0; j < n4; ++j) {
            for (int k = 0; k < n3; ++k) {
                final int n6 = super.cC.eF.x[n5];
                final int[] fw = this.fw;
                final int n7 = (n6 & 0xFF0000) >> 16;
                ++fw[n7];
                final int[] fo = this.fo;
                final int n8 = (n6 & 0xFF00) >> 8;
                ++fo[n8];
                final int[] fs = this.fs;
                final int n9 = n6 & 0xFF;
                ++fs[n9];
                ++n5;
            }
            n5 += n2;
        }
        for (int l = 0; l < 256; ++l) {
            this.fw[l] = (this.fw[l] + this.fo[l] + this.fs[l]) / 3;
        }
        final int n10 = n4 * n3;
        int n11 = 0;
        int n12 = n10 >> 8;
        int n13 = 0;
        for (int n14 = 0; n14 < 256; ++n14) {
            n11 += this.fw[n14];
            this.fw[n14] = n13;
            while (n11 > n12) {
                ++n13;
                n12 += n10 >> 8;
            }
        }
        for (int n15 = 0; n15 < n4; ++n15) {
            for (int n16 = 0; n16 < n3; ++n16) {
                final int n17 = super.cC.eF.x[n];
                super.cC.eF.x[n++] = (0xFF000000 | this.fw[(n17 & 0xFF0000) >> 16] << 16 | this.fw[(n17 & 0xFF00) >> 8] << 8 | this.fw[n17 & 0xFF]);
            }
            n += n2;
        }
    }
    
    public void if() {
    }
}
