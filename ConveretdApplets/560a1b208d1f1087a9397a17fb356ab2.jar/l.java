import java.awt.Point;
import java.awt.Dimension;

// 
// Decompiled by Procyon v0.5.30
// 

class l extends k
{
    protected boolean p;
    
    protected boolean b(final Dimension dimension) {
        this.p = k.a(dimension.width);
        if (this.p) {
            return super.b(dimension);
        }
        final int n = 4096;
        super.t = n;
        super.u = n;
        final int n2 = dimension.width - 1 << 12;
        super.r = n2;
        super.s = n2;
        return true;
    }
    
    l(final h h, final g g) {
        super(h, g);
        this.p = false;
        this.b(h.c);
    }
    
    boolean c() {
        return true;
    }
    
    void a(final b b, final boolean b2) {
        final int a = q.a;
        if (this.p) {
            super.a(b, b2);
            return;
        }
        int n = b.y * super.g.c.width + b.x;
        if (!super.h.d() && !super.h.c() && !b2) {
            int n2 = 0;
        Label_0103_Outer:
            while (true) {
                Label_0136: {
                    if (a == 0) {
                        break Label_0136;
                    }
                    final int n3 = n;
                    int n4 = 0;
                    while (true) {
                        while (true) {
                            Label_0106: {
                                if (a == 0) {
                                    break Label_0106;
                                }
                                super.g.b[n3] = super.h.b[0];
                                ++n4;
                            }
                            if (n4 < b.width) {
                                continue Label_0103_Outer;
                            }
                            break;
                        }
                        n += super.g.c.width;
                        if (a != 0) {
                            continue;
                        }
                        break;
                    }
                    ++n2;
                }
                if (n2 >= b.height) {
                    return;
                }
                continue;
            }
        }
        else {
            final int n5 = (b2 && super.h.d()) ? (super.h.c.width * super.h.c.height / 2) : 0;
            int n6 = 0;
        Label_0250_Outer:
            while (true) {
                Label_0441: {
                    if (a == 0) {
                        break Label_0441;
                    }
                    int x = b.a.x;
                    int y = b.a.y;
                    int n7 = n;
                    int n8 = 0;
                    while (true) {
                        while (true) {
                            Label_0335: {
                                if (a == 0) {
                                    break Label_0335;
                                }
                                int n9 = 0;
                                Label_0285: {
                                    Label_0258: {
                                        if ((y & Integer.MAX_VALUE) <= super.s && (x & Integer.MAX_VALUE) <= super.r) {
                                            break Label_0258;
                                        }
                                        n9 = 0;
                                        if (a == 0) {
                                            break Label_0285;
                                        }
                                    }
                                    n9 = n5 + (y >>> 12) * super.h.c.width + (x >>> 12);
                                }
                                super.g.b[n7] = super.h.b[n9];
                                ++n7;
                                x += b.c.x;
                                y += b.c.y;
                                ++n8;
                            }
                            if (n8 < b.width) {
                                continue Label_0250_Outer;
                            }
                            break;
                        }
                        final Point a2 = b.a;
                        a2.x += b.b.x;
                        final Point a3 = b.a;
                        a3.y += b.b.y;
                        final Point c = b.c;
                        c.x += b.d.x;
                        final Point c2 = b.c;
                        c2.y += b.d.y;
                        n += super.g.c.width;
                        if (a != 0) {
                            continue;
                        }
                        break;
                    }
                    ++n6;
                }
                if (n6 >= b.height) {
                    return;
                }
                continue;
            }
        }
    }
    
    void a(final c c, final boolean b) {
        final int a = q.a;
        if (this.p) {
            super.a(c, b);
            return;
        }
        int n = c.y * super.g.c.width + c.x;
        int n2 = 0;
        Label_0084: {
            if (!super.q) {
                n2 = n + c.width - 1;
                if (a == 0) {
                    break Label_0084;
                }
            }
            n2 = n + (c.height - 1) * super.g.c.width;
        }
        int n3 = 0;
        final boolean b2 = false;
        int n4 = 0;
        boolean b3 = false;
        Label_0160: {
            if (super.h.d()) {
                n3 = super.h.c.width * super.h.c.height / 2;
                if (a == 0) {
                    break Label_0160;
                }
            }
            if (!super.h.c()) {
                n4 = (b ? 0 : 1);
                b3 = b;
            }
        }
        final int n5 = b ? n3 : b2;
        final int n6 = b ? b2 : n3;
        final int n7 = super.q ? c.x : c.y;
        final int n8 = n7 + (super.q ? c.width : c.height);
        final int n9 = super.q ? super.g.c.width : 1;
        final int n10 = super.q ? 1 : super.g.c.width;
        int n11 = n7;
        int n12;
        int n13;
        int n14;
        int n15 = 0;
        int n16 = 0;
        int q = 0;
        int n17 = 0;
        int n18;
        int n19;
        Point a2;
        Point a3;
        Point e;
        Point e2;
        Point c2;
        Point c3;
        Point g;
        Point g2;
        int n20 = 0;
        Label_0421_Outer:Label_0628_Outer:
        while (true) {
            Label_0951: {
                if (a == 0) {
                    break Label_0951;
                }
                n12 = c.a.x;
                n13 = c.a.y;
                n14 = n;
                Label_0361: {
                    if (!super.q) {
                        n15 = n.a(super.o[n11] - c.x, 0, c.width);
                        if (a == 0) {
                            break Label_0361;
                        }
                    }
                    n15 = n.a(super.n[n11] - c.y, 0, c.height);
                }
                while (true) {
                    Label_0541: {
                        Label_0534: {
                            if (n4 != 0) {
                                n16 = 0;
                                while (true) {
                                    Label_0403: {
                                        if (a == 0) {
                                            break Label_0403;
                                        }
                                        super.g.b[n14] = super.h.b[0];
                                        ++n16;
                                        n14 += n9;
                                    }
                                    if (n16 >= n15) {
                                        break Label_0541;
                                    }
                                    continue Label_0421_Outer;
                                }
                            }
                            else {
                                n16 = 0;
                                if (a == 0) {
                                    break Label_0534;
                                }
                            }
                            Label_0480: {
                                if (q > super.s || (n12 & Integer.MAX_VALUE) > super.r) {
                                    n17 = 0;
                                    if (a == 0) {
                                        break Label_0480;
                                    }
                                }
                                n17 = n5 + (n13 >>> 12) * super.h.c.width + (n12 >>> 12);
                            }
                            super.g.b[n14] = super.h.b[n17];
                            n14 += n9;
                            n12 += c.c.x;
                            n13 += c.c.y;
                            ++n16;
                        }
                        if (n16 < n15) {
                            continue Label_0628_Outer;
                        }
                    }
                    n12 = c.e.x;
                    n13 = c.e.y;
                    n14 = n2;
                    q = (super.q ? 1 : 0);
                    if (a != 0) {
                        continue Label_0628_Outer;
                    }
                    break;
                }
                n18 = ((q == 0) ? (c.width - 1) : (c.height - 1));
                while (true) {
                    Label_0642: {
                        if (!b3) {
                            break Label_0642;
                        }
                        n19 = n18;
                        while (true) {
                            Label_0635: {
                                if (a == 0) {
                                    break Label_0635;
                                }
                                super.g.b[n14] = super.h.b[0];
                                --n19;
                                n14 -= n9;
                            }
                            if (n19 >= n15) {
                                continue Label_0628_Outer;
                            }
                            break;
                        }
                    }
                    n19 = n18;
                    if (a != 0) {
                        if (a != 0) {
                            continue;
                        }
                    }
                    break;
                }
                while (true) {
                    Label_0718: {
                        Label_0691: {
                            if (n19 < n15) {
                                a2 = c.a;
                                a2.x += c.b.x;
                                a3 = c.a;
                                a3.y += c.b.y;
                                e = c.e;
                                e.x += c.f.x;
                                e2 = c.e;
                                e2.y += c.f.y;
                                c2 = c.c;
                                c2.x += c.d.x;
                                c3 = c.c;
                                c3.y += c.d.y;
                                g = c.g;
                                g.x += c.h.x;
                                g2 = c.g;
                                g2.y += c.h.y;
                                n += n10;
                                n2 += n10;
                                if (a == 0) {
                                    break;
                                }
                            }
                            else if ((n13 & Integer.MAX_VALUE) <= super.s && (n12 & Integer.MAX_VALUE) <= super.r) {
                                break Label_0691;
                            }
                            n20 = 0;
                            if (a == 0) {
                                break Label_0718;
                            }
                        }
                        n20 = n6 + (n13 >>> 12) * super.h.c.width + (n12 >>> 12);
                    }
                    super.g.b[n14] = super.h.b[n20];
                    n14 -= n9;
                    n12 -= c.g.x;
                    n13 -= c.g.y;
                    --n19;
                }
                ++n11;
            }
            if (n11 >= n8) {
                return;
            }
            continue;
        }
    }
}
