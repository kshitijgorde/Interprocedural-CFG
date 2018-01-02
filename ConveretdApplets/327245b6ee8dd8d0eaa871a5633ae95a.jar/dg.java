import java.util.Vector;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class dg extends ba
{
    int a;
    int b;
    String c;
    int d;
    int e;
    float[][] f;
    float[][] g;
    int[][] h;
    Hashtable i;
    Vector j;
    
    dg(final int a) {
        this.i = null;
        this.j = null;
        this.a = a;
    }
    
    void a(final w w) {
        final boolean l = c.l;
        super.a(w);
        w.c();
        this.c = w.m();
        final int d = w.d();
        final float n = (float)Math.pow(2.0, d) - 1.0f;
        float intBitsToFloat = 0.0f;
        float intBitsToFloat2 = 0.0f;
        float n2 = 0.0f;
        float n3 = 0.0f;
        Label_0114: {
            if (this.a == 2) {
                final int a = w.a(31);
                final int a2 = w.a(31);
                intBitsToFloat = Float.intBitsToFloat(a);
                intBitsToFloat2 = Float.intBitsToFloat(a2);
                n2 = intBitsToFloat * 2.0f / n;
                n3 = intBitsToFloat2 * 2.0f / n;
                if (!l) {
                    break Label_0114;
                }
            }
            n2 = 1.0f / n;
            n3 = 1.0f / n;
        }
        this.d = w.a(31);
        this.e = w.a(31);
        this.f = new float[this.d][2];
        this.g = new float[this.e][2];
        this.h = new int[this.e][2];
        this.i = new Hashtable(this.d, 1.0f);
        this.j = new Vector();
        int n4 = 0;
        int n5;
        while (true) {
            while (true) {
                Label_0253: {
                    if (!l) {
                        break Label_0253;
                    }
                    this.f[n4][0] = w.a(d) * n2 - intBitsToFloat;
                    this.f[n4][1] = w.a(d) * n3 - intBitsToFloat2;
                    ++n4;
                }
                if (n4 < this.d) {
                    continue;
                }
                break;
            }
            n5 = 0;
            if (l) {
                continue;
            }
            break;
        }
        while (true) {
            Label_0348: {
                if (!l) {
                    break Label_0348;
                }
                this.h[n5][0] = w.a(d);
                this.h[n5][1] = w.a(d);
                this.g[n5][0] = w.a(d) * n2 - intBitsToFloat;
                this.g[n5][1] = w.a(d) * n3 - intBitsToFloat2;
                ++n5;
            }
            if (n5 >= this.e) {
                return;
            }
            continue;
        }
    }
    
    void a(final a2 a2) {
        final boolean l = c.l;
        int n = 0;
    Label_0548:
        while (true) {
            Label_0539: {
                if (!l) {
                    break Label_0539;
                }
                final ba ba;
                if ((ba = a2.i[n]).a.equals(this.c)) {
                    final a9 a3 = (a9)ba;
                    a3.f = this;
                    this.b = a3.b;
                    int n2 = 0;
                    super.b = new float[this.b][6];
                    int n3 = 0;
                    while (true) {
                        Label_0524: {
                            if (!l) {
                                break Label_0524;
                            }
                            final c9 c9 = a3.e[n3];
                            final Integer n4 = new Integer(c9.d.d);
                            Label_0233: {
                                final Integer n5;
                                if ((n5 = this.i.get(n4)) == null) {
                                    super.b[n3][0] = this.f[n2][0];
                                    super.b[n3][3] = this.f[n2][1];
                                    this.i.put(n4, new Integer(n2));
                                    ++n2;
                                    if (!l) {
                                        break Label_0233;
                                    }
                                }
                                super.b[n3][0] = this.f[n5][0];
                                super.b[n3][3] = this.f[n5][1];
                            }
                            final Integer n6 = new Integer(c9.e.d);
                            Label_0377: {
                                final Integer n7;
                                if ((n7 = this.i.get(n6)) == null) {
                                    super.b[n3][1] = this.f[n2][0];
                                    super.b[n3][4] = this.f[n2][1];
                                    this.i.put(n6, new Integer(n2));
                                    ++n2;
                                    if (!l) {
                                        break Label_0377;
                                    }
                                }
                                super.b[n3][1] = this.f[n7][0];
                                super.b[n3][4] = this.f[n7][1];
                            }
                            final Integer n8 = new Integer(c9.f.d);
                            Label_0521: {
                                final Integer n9;
                                if ((n9 = this.i.get(n8)) == null) {
                                    super.b[n3][2] = this.f[n2][0];
                                    super.b[n3][5] = this.f[n2][1];
                                    this.i.put(n8, new Integer(n2));
                                    ++n2;
                                    if (!l) {
                                        break Label_0521;
                                    }
                                }
                                super.b[n3][2] = this.f[n9][0];
                                super.b[n3][5] = this.f[n9][1];
                            }
                            ++n3;
                        }
                        if (n3 >= this.b) {
                            break Label_0548;
                        }
                        continue;
                    }
                }
                else {
                    ++n;
                }
            }
            if (n < a2.i.length) {
                continue;
            }
            break;
        }
        int n10 = 0;
        while (true) {
            Label_0627: {
                if (!l) {
                    break Label_0627;
                }
                super.b[this.h[n10][0]][this.h[n10][1]] = this.g[n10][0];
                super.b[this.h[n10][0]][this.h[n10][1] + 3] = this.g[n10][1];
                ++n10;
            }
            if (n10 >= this.e) {
                return;
            }
            continue;
        }
    }
    
    void b() {
        super.b();
        super.b = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i.clear();
        this.i = null;
    }
}
