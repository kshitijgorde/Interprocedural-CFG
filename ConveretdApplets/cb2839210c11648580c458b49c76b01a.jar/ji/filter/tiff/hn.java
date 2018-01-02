// 
// Decompiled by Procyon v0.5.30
// 

package ji.filter.tiff;

public class hn
{
    public int a;
    public int b;
    public int c;
    public ho[] d;
    public int e;
    public int f;
    public long g;
    public short[] h;
    public boolean i;
    public int j;
    public int k;
    public boolean l;
    public int m;
    public int n;
    public int o;
    public int p;
    public int q;
    public int r;
    public int s;
    public int t;
    public int u;
    public int v;
    public int[] w;
    public int x;
    public int y;
    public int z;
    public int aa;
    public int ab;
    public int ac;
    public int ad;
    public int ae;
    public int af;
    public byte[][] ag;
    public byte[][] ah;
    public byte[][] ai;
    public int aj;
    public int ak;
    public byte[] al;
    public int am;
    public hl[] an;
    public int[] ao;
    public String ap;
    
    public hn() {
        this.d = null;
        this.e = 0;
        this.f = 0;
        this.g = 0L;
        this.h = null;
        this.i = false;
        this.j = 0;
        this.k = 0;
        this.l = false;
        this.m = 0;
        this.n = 0;
        this.o = 0;
        this.p = 1;
        this.q = 1;
        this.r = 0;
        this.s = 0;
        this.t = 0;
        this.u = 0;
        this.v = 1;
        this.w = null;
        this.x = 1;
        this.y = 0;
        this.z = 0;
        this.aa = 0;
        this.ab = 0;
        this.ac = 0;
        this.ad = 0;
        this.ae = 0;
        this.af = 0;
        this.ag = null;
        this.ah = null;
        this.ai = null;
        this.aj = 0;
        this.ak = 0;
        this.al = null;
        this.am = 0;
        this.an = null;
        this.ao = null;
        this.ap = null;
        (this.an = new hl[1])[0] = new hl();
    }
    
    public hn a() {
        final hn hn = new hn();
        hn.a = this.a;
        hn.b = this.b;
        hn.c = this.c;
        hn.e = this.e;
        hn.f = this.f;
        hn.g = this.g;
        hn.j = this.j;
        hn.m = this.m;
        hn.n = this.n;
        hn.o = this.o;
        hn.p = this.p;
        hn.q = this.q;
        hn.k = this.k;
        hn.l = this.l;
        hn.r = this.r;
        hn.s = this.s;
        hn.t = this.t;
        hn.u = this.u;
        hn.v = this.v;
        hn.y = this.y;
        hn.am = this.am;
        hn.z = this.z;
        hn.aa = this.aa;
        hn.ab = this.ab;
        hn.ac = this.ac;
        hn.ad = this.ad;
        hn.ae = this.ae;
        hn.af = this.af;
        hn.aj = this.aj;
        hn.ak = this.ak;
        hn.ap = this.ap;
        if (this.ag != null) {
            hn.ag = new byte[this.ag.length][this.ag[0].length];
            for (int i = 0; i < this.ag.length; ++i) {
                for (int j = 0; j < this.ag[i].length; ++j) {
                    hn.ag[i][j] = this.ag[i][j];
                }
            }
        }
        if (this.ah != null) {
            hn.ah = new byte[this.ah.length][this.ah[0].length];
            for (int k = 0; k < this.ah.length; ++k) {
                for (int l = 0; l < this.ah[k].length; ++l) {
                    hn.ah[k][l] = this.ah[k][l];
                }
            }
        }
        if (this.ai != null) {
            hn.ai = new byte[this.ai.length][this.ai[0].length];
            for (int n = 0; n < this.ai.length; ++n) {
                for (int n2 = 0; n2 < this.ai[n].length; ++n2) {
                    hn.ai[n][n2] = this.ai[n][n2];
                }
            }
        }
        hn.an = new hl[this.an.length];
        for (int n3 = 0; n3 < this.an.length; ++n3) {
            hn.an[n3] = new hl();
            hn.an[n3].a = this.an[n3].a;
            hn.an[n3].b = this.an[n3].b;
            hn.an[n3].c = this.an[n3].c;
        }
        if (this.d != null) {
            hn.d = new ho[this.d.length];
            for (int n4 = 0; n4 < this.d.length; ++n4) {
                hn.d[n4] = new ho();
                hn.d[n4].a = this.d[n4].a;
                hn.d[n4].b = this.d[n4].b;
            }
        }
        if (this.h != null) {
            hn.h = new short[this.h.length];
            for (int n5 = 0; n5 < this.h.length; ++n5) {
                hn.h[n5] = this.h[n5];
            }
        }
        if (this.w != null) {
            hn.w = new int[this.w.length];
            for (int n6 = 0; n6 < this.w.length; ++n6) {
                hn.w[n6] = this.w[n6];
            }
        }
        if (this.ao != null) {
            hn.ao = new int[this.ao.length];
            for (int n7 = 0; n7 < this.ao.length; ++n7) {
                hn.ao[n7] = this.ao[n7];
            }
        }
        hn.i = this.i;
        hn.al = this.al;
        return hn;
    }
}
