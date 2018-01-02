// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class j extends a2
{
    private char[] fi;
    int[] fd;
    int[] fh;
    int[] fk;
    boolean[] fb;
    int[][] fj;
    int[][] fg;
    int[] ff;
    boolean fc;
    private int fe;
    private long fa;
    
    public j() {
        this.fi = new char[] { 's', 'f', 'x', '\0' };
        this.fk = null;
        this.fb = null;
        this.fj = null;
        this.fg = null;
        this.ff = null;
        this.fc = true;
        this.fe = 200;
        this.fa = 0L;
    }
    
    public void a(final an b, final float ca, final aq long1, final h cc, final t cb) {
        try {
            super.b = b;
            super.cA = ca;
            super.cD = 1.0f / super.cA;
            super.long = long1;
            super.cC = cc;
            super.cB = cb;
            super.goto = cb.h;
            super.int = 1;
            super.case = this.fi;
            this.fd = new int[768];
            this.fh = new int[768];
            for (int i = 0; i < 768; ++i) {
                int n = i / 3;
                if (n > 255) {
                    n = 255;
                }
                int n2 = n * 32 / 30;
                if (n2 > 255) {
                    n2 = 255;
                }
                this.fd[i] = (n * 25 / 30 | n << 8 | n2 << 16 | 0xFF000000);
                this.fh[i] = (n * 25 / 30 * this.fe >> 8 | ((n << 8) * this.fe >> 8 & 0xFF00) | ((n2 << 16) * this.fe >> 8 & 0xFF0000) | 0xFF000000);
            }
            this.fk = new int[2];
            this.fb = new boolean[2];
            this.fj = new int[5][];
            for (int j = 0; j < 5; ++j) {
                this.fj[j] = new int[1024];
                int n3 = (int)(Math.random() * 32.0);
                for (int k = 0; k < 1024; ++k) {
                    this.fj[j][k] = 0;
                }
                int n4 = 0;
                for (int l = 0; l < 32; ++l) {
                    this.fj[j][n4 + n3] = 1;
                    n3 = (n3 + (int)(Math.random() * 3.0) - 1 & 0x1F);
                    n4 += 32;
                }
            }
            this.fg = new int[10][];
            for (int n5 = 0; n5 < 10; ++n5) {
                int n6 = 16;
                int n7 = 16;
                this.fg[n5] = new int[1024];
                for (int n8 = 0; n8 < 1024; ++n8) {
                    this.fg[n5][n8] = 0;
                }
                for (int n9 = 0; n9 < 128; ++n9) {
                    n6 = (n6 + (int)(Math.random() * 3.0) - 1 & 0x1F);
                    n7 = (n7 + (int)(Math.random() * 3.0) - 1 & 0x1F);
                    this.fg[n5][n6 + n7 * 32] = 1;
                }
            }
        }
        catch (OutOfMemoryError outOfMemoryError) {
            System.out.println("Not enough memory to play Sepia effect.");
            super.goto = false;
        }
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
            else if (bh.try[i].toLowerCase().compareTo("play") == 0 && bh.new[i].compareTo("false") == 0) {
                super.ek = false;
            }
        }
        this.a();
        super.byte = true;
        super.goto = true;
        super.else = true;
    }
    
    public boolean a(final long n) {
        this.l();
        return super.for && super.byte;
    }
    
    boolean byte(final boolean b) {
        return super.byte = true;
    }
    
    void new(final long n) {
        super.byte = true;
    }
    
    public void new(final boolean b) {
        final long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis < this.fa + 100L) {
            try {
                Thread.sleep(this.fa + 100L - currentTimeMillis);
            }
            catch (Exception ex) {}
        }
        this.fa = System.currentTimeMillis();
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
                int n2 = n + m * ef.s;
                final int n3 = ef.s - l;
                if (this.fc) {
                    for (int i = 0; i < p; ++i) {
                        for (int j = 0; j < l; ++j) {
                            final int n4 = super.cC.eF.x[n2];
                            super.cC.eF.x[n2++] = this.fh[(n4 & 0xFF) + ((n4 & 0xFF00) >> 8) + ((n4 & 0xFF0000) >> 16)];
                        }
                        n2 += n3;
                    }
                }
                else {
                    for (int k = 0; k < p; ++k) {
                        for (int n5 = 0; n5 < l; ++n5) {
                            final int n6 = super.cC.eF.x[n2];
                            super.cC.eF.x[n2++] = this.fd[(n6 & 0xFF) + ((n6 & 0xFF00) >> 8) + ((n6 & 0xFF0000) >> 16)];
                        }
                        n2 += n3;
                    }
                }
                this.fc = !this.fc;
                for (int n7 = 0; n7 < 2; ++n7) {
                    if (!this.fb[n7]) {
                        if (Math.random() > 0.3) {
                            this.fb[n7] = true;
                            this.fk[n7] = (int)(Math.random() * l);
                        }
                    }
                    else if (Math.random() < 0.1) {
                        this.fb[n7] = false;
                    }
                    if (this.fb[n7]) {
                        final int[] fk = this.fk;
                        final int n8 = n7;
                        fk[n8] += (int)(Math.random() * 10.0) - 5;
                        if (this.fk[n7] > 0 && this.fk[n7] < l - 1) {
                            int n9 = this.fk[n7] + n + m * super.cC.eF.s;
                            for (int n10 = 0; n10 < p; ++n10) {
                                super.cC.eF.x[n9] = -8094616;
                                n9 += super.cC.eF.s;
                            }
                        }
                    }
                }
                for (int n11 = 0; n11 < 5; ++n11) {
                    if (Math.random() > 0.95) {
                        final int n12 = (int)(Math.random() * (l - 32));
                        int n13 = n + (m + (int)(Math.random() * (p - 32))) * super.cC.eF.s;
                        int n14 = 0;
                        for (int n15 = 0; n15 < 32; ++n15) {
                            for (int n16 = 0; n16 < 32; ++n16) {
                                if (this.fj[n11][n14 + n16] != 0) {
                                    super.cC.eF.x[n13 + n12 + n16] = -8094616;
                                }
                            }
                            n14 += 32;
                            n13 += super.cC.eF.s;
                        }
                    }
                }
                for (int n17 = 0; n17 < 10; ++n17) {
                    if (Math.random() > 0.9) {
                        final int n18 = (int)(Math.random() * (l - 32));
                        int n19 = n + (m + (int)(Math.random() * (p - 32))) * super.cC.eF.s;
                        int n20 = 0;
                        for (int n21 = 0; n21 < 32; ++n21) {
                            for (int n22 = 0; n22 < 32; ++n22) {
                                if (this.fg[n17][n20 + n22] != 0) {
                                    super.cC.eF.x[n19 + n18 + n22] = -8094616;
                                }
                            }
                            n20 += 32;
                            n19 += super.cC.eF.s;
                        }
                    }
                }
            }
            catch (Exception ex2) {}
        }
    }
    
    public void if() {
    }
}
