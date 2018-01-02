// 
// Decompiled by Procyon v0.5.30
// 

public class o
{
    private final int a;
    private d b;
    private int[] c;
    private int[] d;
    private int[] e;
    private int[] f;
    private int[] g;
    private float[] h;
    private float[] i;
    private e[] j;
    
    public o(final d b, final int[] g) {
        this.a = 81;
        this.c = new int[81];
        this.d = new int[81];
        this.e = new int[81];
        this.f = new int[81];
        this.h = new float[81];
        this.i = new float[81];
        this.j = new e[81];
        this.b = b;
        this.g = g;
        int n = 0;
        while (true) {
            Label_0112: {
                if (!p.dJ) {
                    break Label_0112;
                }
                this.e[n] = 0;
                this.j[n] = b.a("_t", "0", 0, 0, true);
                ++n;
            }
            if (n >= 81) {
                return;
            }
            continue;
        }
    }
    
    public void a(final int n, final int n2, final long n3) {
        final boolean dj = p.dJ;
        int n4 = 0;
        while (true) {
        Label_0131:
            while (true) {
                Label_0124: {
                    if (!dj) {
                        break Label_0124;
                    }
                    if (this.e[n4] == 0) {
                        this.j[n4].a(n3, this.g);
                        this.j[n4].a(n, n2);
                        this.c[n4] = n;
                        this.d[n4] = n2;
                        this.h[n4] = 0.0f;
                        this.i[n4] = 1.0f;
                        this.e[n4] = 100;
                        this.f[n4] = this.b.a("s", this.j[n4], 75000);
                        if (!dj) {
                            break Label_0131;
                        }
                    }
                    ++n4;
                }
                if (n4 < 81) {
                    continue;
                }
                break;
            }
            if (!dj) {
                return;
            }
            continue;
        }
    }
    
    public void a() {
        final boolean dj = p.dJ;
        int n = 0;
        while (true) {
            Label_0136: {
                if (!dj) {
                    break Label_0136;
                }
                Label_0133: {
                    if (this.e[n] != 0) {
                        final int[] e = this.e;
                        final int n2 = n;
                        --e[n2];
                        if (this.e[n] == 0) {
                            this.b.i(this.f[n]);
                            if (!dj) {
                                break Label_0133;
                            }
                        }
                        final int[] d = this.d;
                        final int n3 = n;
                        --d[n3];
                        this.j[n].a((int)(this.c[n] + Math.sin(this.h[n]) * this.i[n]), this.d[n]);
                        final float[] i = this.i;
                        final int n4 = n;
                        i[n4] += 0.2;
                        final float[] h = this.h;
                        final int n5 = n;
                        h[n5] += 0.05f;
                    }
                }
                ++n;
            }
            if (n >= 81) {
                return;
            }
            continue;
        }
    }
}
