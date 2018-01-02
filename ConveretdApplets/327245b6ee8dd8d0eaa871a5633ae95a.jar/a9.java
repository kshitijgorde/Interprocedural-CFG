import java.util.Enumeration;
import java.util.Vector;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class a9 extends ba
{
    a2 a;
    int b;
    int[] c;
    Hashtable d;
    c9[] e;
    ba f;
    int g;
    int h;
    int[] i;
    int[][] j;
    int k;
    final int l = 16;
    char[][] m;
    l[] n;
    int p;
    Vector[] q;
    c9 r;
    int s;
    
    void a(final char[] array, final l l) {
        if (this.p >= 16) {
            return;
        }
        this.m[this.p] = array;
        this.n[this.p] = l;
        ++this.p;
    }
    
    char[] a(final l l) {
        int n = 0;
        while (true) {
            Label_0028: {
                if (!c.l) {
                    break Label_0028;
                }
                if (this.n[n] == l) {
                    return this.m[n];
                }
                ++n;
            }
            if (n >= this.p) {
                return null;
            }
            continue;
        }
    }
    
    a9() {
        this.d = new Hashtable(100, 0.5f);
        this.m = new char[16][];
        this.n = new l[16];
        this.p = 0;
    }
    
    void a(final w w) {
        w.c();
        super.a = w.m();
        this.b = w.a(31);
        this.c = new int[this.b];
        this.e = new c9[this.b];
    }
    
    int a(final int n) {
        final Integer n2 = this.d.get(new Integer(n));
        if (n2 == null) {
            return -1;
        }
        return n2;
    }
    
    void a(final int n, final int[] array, final int n2) {
        int n3 = 0;
        int n4 = 0;
        while (true) {
            Label_0061: {
                if (!c.l) {
                    break Label_0061;
                }
                if (array[n4] == n) {
                    this.c[n3] = n4;
                    this.d.put(new Integer(n4), new Integer(n3));
                    ++n3;
                }
                ++n4;
            }
            if (n4 >= n2) {
                return;
            }
            continue;
        }
    }
    
    void a(final a2 a) {
        this.a = a;
        int n = 0;
        while (true) {
            Label_0033: {
                if (!c.l) {
                    break Label_0033;
                }
                this.e[n] = a.g[this.c[n]];
                ++n;
            }
            if (n >= this.b) {
                return;
            }
            continue;
        }
    }
    
    void a() {
        int n = 0;
        while (true) {
            Label_0021: {
                if (!c.l) {
                    break Label_0021;
                }
                this.e[n].b = this;
                ++n;
            }
            if (n >= this.b) {
                return;
            }
            continue;
        }
    }
    
    int b(int n) {
        final boolean l = c.l;
        this.h = n * 3;
        this.q = new Vector[3 * this.b];
        this.i = new int[3 * this.b * 2];
        int n2 = 0;
        int n3;
        while (true) {
            while (true) {
                Label_0076: {
                    if (!l) {
                        break Label_0076;
                    }
                    n3 = 0;
                    while (true) {
                        Label_0068: {
                            if (!l) {
                                break Label_0068;
                            }
                            this.e[n2].c[n3] = -1;
                            ++n3;
                        }
                        if (n3 < 3) {
                            continue;
                        }
                        break;
                    }
                    ++n2;
                }
                if (n2 < this.b) {
                    continue;
                }
                break;
            }
            this.g = 0;
            n3 = 0;
            if (l) {
                continue;
            }
            break;
        }
        int n6;
        while (true) {
            while (true) {
                Label_0399: {
                    if (!l) {
                        break Label_0399;
                    }
                    this.r = this.e[n3];
                    final c8[] array = { this.r.d, this.r.e, this.r.f };
                    final da[] array2 = { this.r.h, this.r.i, this.r.j };
                    int n4 = 0;
                    while (true) {
                        Label_0390: {
                            if (!l) {
                                break Label_0390;
                            }
                            if (this.r.c[n4] == -1) {
                                final int n5 = this.g * 2;
                                this.q[this.g] = new Vector();
                                final c8 c8 = array[n4];
                                final da da = array2[(n4 + 1) % 3];
                                final da da2 = array2[(n4 + 2) % 3];
                                this.q[this.g].addElement(this.r);
                                this.r.c[n4] = this.g;
                                this.s = 0;
                                if (!da.e) {
                                    this.a(da, c8, this.r);
                                }
                                if (this.s != c8.f && !da2.e) {
                                    this.a(da2, c8, this.r);
                                }
                                this.i[n5] = c8.d * 3;
                                this.i[n5 + 1] = n * 3;
                                ++n;
                                ++this.g;
                            }
                            ++n4;
                        }
                        if (n4 < 3) {
                            continue;
                        }
                        break;
                    }
                    ++n3;
                }
                if (n3 < this.b) {
                    continue;
                }
                break;
            }
            this.j = new int[this.g][];
            n6 = 0;
            if (l) {
                continue;
            }
            break;
        }
        while (true) {
            while (true) {
                Label_0538: {
                    if (!l) {
                        break Label_0538;
                    }
                    final a9 a9 = this;
                    final Vector vector = a9.q[n6];
                    this.j[n6] = new int[vector.size() + 1];
                    final int[] array3 = this.j[n6];
                    array3[0] = vector.size() + 1;
                    int n7 = 1;
                    final Enumeration<c9> elements = vector.elements();
                    while (true) {
                        Label_0512: {
                            if (!l) {
                                break Label_0512;
                            }
                            array3[n7++] = elements.nextElement().a;
                        }
                        if (elements.hasMoreElements()) {
                            continue;
                        }
                        break;
                    }
                    vector.removeAllElements();
                    this.q[n6] = null;
                    ++n6;
                }
                if (n6 < this.g) {
                    continue;
                }
                break;
            }
            this.q = null;
            final int n8 = 3 * this.b * 2;
            final a9 a9 = this;
            if (l) {
                continue;
            }
            break;
        }
        final int n9 = this.g * 2;
        final int[] i = new int[n9];
        int n10 = 0;
        while (true) {
            while (true) {
                Label_0604: {
                    if (!l) {
                        break Label_0604;
                    }
                    i[n10] = this.i[n10];
                    ++n10;
                }
                if (n10 < n9) {
                    continue;
                }
                break;
            }
            this.i = null;
            this.i = i;
            if (!l) {
                return this.g;
            }
            continue;
        }
    }
    
    private void a(final da da, final c8 c8, final c9 c9) {
        final boolean l = c.l;
        ++this.s;
        c9 c10 = null;
        da da2 = null;
        int n = 0;
        c9 c12 = null;
        c9 r = null;
    Label_0047_Outer:
        while (true) {
            while (true) {
                Label_0065: {
                    if (n >= da.d) {
                        break Label_0065;
                    }
                    final c9 c11;
                    c10 = (c11 = this.a.g[da.c[n]]);
                    if (c12 == r) {
                        ++n;
                        continue Label_0047_Outer;
                    }
                }
                c12 = c10;
                r = this.r;
                if (!l) {
                    break;
                }
                continue;
            }
        }
        if (c12 == r || c10 == null) {
            return;
        }
        final c8[] array = { c10.d, c10.e, c10.f };
        final da[] array2 = { c10.h, c10.i, c10.j };
        int n2 = 0;
        while (true) {
        Label_0239:
            while (true) {
                Label_0233: {
                    if (!l) {
                        break Label_0233;
                    }
                    if (array[n2] != c8) {
                        ++n2;
                        break Label_0233;
                    }
                    if (array2[(n2 + 1) % 3] == da) {
                        da2 = array2[(n2 + 2) % 3];
                    }
                    else {
                        da2 = array2[(n2 + 1) % 3];
                    }
                    if (c10.b == this.r.b) {
                        c10.c[n2] = this.g;
                    }
                    break Label_0239;
                }
                if (n2 < 3) {
                    continue;
                }
                break;
            }
            if (!l) {
                if (!da2.e) {
                    this.a(da2, c8, c10);
                }
                this.q[this.g].addElement(c10);
                return;
            }
            continue;
        }
    }
    
    void b() {
        final boolean l = c.l;
        super.b();
        this.a = null;
        int n = 0;
        int n2;
        while (true) {
            while (true) {
                Label_0029: {
                    if (!l) {
                        break Label_0029;
                    }
                    this.e[n] = null;
                    ++n;
                }
                if (n < this.b) {
                    continue;
                }
                break;
            }
            this.c = null;
            this.f = null;
            this.i = null;
            n2 = 0;
            if (l) {
                continue;
            }
            break;
        }
        while (true) {
            while (true) {
                Label_0072: {
                    if (!l) {
                        break Label_0072;
                    }
                    this.j[n2] = null;
                    ++n2;
                }
                if (n2 < this.g) {
                    continue;
                }
                break;
            }
            this.j = null;
            this.q = null;
            this.r = null;
            if (!l) {
                return;
            }
            continue;
        }
    }
}
