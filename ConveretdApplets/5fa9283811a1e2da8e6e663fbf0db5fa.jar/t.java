import java.awt.Graphics;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class t extends k
{
    public y p;
    public int p;
    public double p;
    public z p;
    public int d;
    public y[] p;
    
    public final void d() {
        for (int i = 0; i < this.d; ++i) {
            this.p[i].g();
        }
    }
    
    public final void p(final y y, final y p4, final int n, final int n2) {
        y.g = true;
        y.p = p4;
        y.e = n;
        y.d = y.p[n2].p[0].p;
    }
    
    public final void p(final boolean b) {
        this.p.p(-10.0, -10.0, 720.0);
        if (b) {
            this.p[0] = new y(this.p, 0, this.p + 100.0, 100, 66, 66, 66, null, -1, 6, super.p.p[0], super.p);
        }
        else {
            this.p[0] = new y(this.p, 0, this.p + 100.0, 66, 66, 66, 66, null, -1, 6, super.p.p[0], super.p);
        }
        final y p = this.p[0];
        final v v = p.p[0];
        final v v2 = p.p[1];
        final v v3 = p.p[2];
        final v v4 = p.p[3];
        if (b) {
            v3.d(2.0, 2.0, this.p[0].c - 24.0, 16.0, "checkers.gif", true, Color.white, true);
        }
        else {
            v3.d(2.0, 2.0, 37.0, 16.0, "hole.gif", true, Color.white, true);
        }
        if (super.p.h) {
            v.d(1.0, 1.0, 1.0, 1.0, super.p.i, true, Color.white, true);
            v.p[0].d = super.p.g;
            v.p[0].p(super.p.h, super.p.j);
            v2.d(3.6, 6.2, 12.8, 7.6, "4d.gif", true, Color.white, true);
            v4.p(3.0, 8.0, 14.0, 4.0, "exit.gif", true, Color.white, true);
            v2.d(9.8, 9.95, 0.4, 0.1, "close.gif", true, Color.white, true);
            v2.d(9.775, 10.2, 0.45, 0.05, "bumping.gif", true, Color.white, true);
        }
        else {
            v.d(3.6, 6.2, 12.8, 7.6, "4d.gif", true, Color.white, true);
            v2.d(1.0, 1.0, 1.0, 1.0, super.p.i, true, Color.white, true);
            v2.p[0].d = super.p.g;
            v2.p[0].p(super.p.h, super.p.j);
            v4.p(3.0, 8.0, 14.0, 4.0, "exit.gif", true, Color.white, true);
            v.d(9.8, 9.95, 0.4, 0.1, "close.gif", true, Color.white, true);
            v.d(9.775, 10.2, 0.45, 0.05, "bumping.gif", true, Color.white, true);
        }
        super.p.p = p;
        this.p[1] = null;
        this.p[2] = new y(null, 2, this.p, -1, 100, -1, -1, this.p[0], 3, 5, super.p.p[2], super.p);
        this.p[3] = null;
        this.p[4] = new y(null, 4, this.p, -1, -1, -1, 100, this.p[0], 5, 5, super.p.p[4], super.p);
        this.p[5] = null;
        this.p[6] = null;
        this.p[7] = new y(null, 7, this.p + 80.0, -1, 100, -1, -1, this.p[2], 3, 5, super.p.p[7], super.p);
        this.p[8] = new y(null, 8, this.p + 80.0, -1, -1, -1, 100, this.p[4], 5, 5, super.p.p[8], super.p);
        (this.p[9] = new y(null, 9, this.p + 30.0, -1, -1, -1, 50, this.p[7], 3, 4, super.p.p[9], super.p)).p(this.p[8]);
        this.p[10] = null;
        this.p[11] = null;
        this.p.n(this.p[4].p);
        final z p2 = this.p;
        p2.d -= 10.0;
        (this.p[12] = new y(this.p, 12, 10.0, 10.0, 10.0, true, true, this.p[4], Color.green, super.p)).p(8.0, 8.0, 1.0, 1.0);
        this.p[12].a(this.p[12].p[0].p[2].p, 0.7853981633974483);
        this.p[12].p(this.p[12].p[0].p[2].p, 0.7853981633974483);
        this.p[13] = null;
        this.p[14] = null;
        this.p[15] = null;
        this.p[16] = null;
        this.p[17] = null;
        this.p[18] = null;
        this.p[19] = null;
        this.p[20] = null;
        this.p[21] = null;
        this.p = new z(this.p[0].p);
        final z p3 = this.p;
        p3.a += 60.0;
        final z p4 = this.p;
        p4.d -= 6.0;
        final z p5 = this.p;
        p5.p -= 5.0;
        this.p[22] = new y(this.p, 22, 10.0, 0.25, 10.0, false, false, this.p[0], Color.white, super.p);
        this.p[22].p[4].d(1.0, 1.0, 5.0, 5.0, "chessboard.gif", true, Color.white, false);
        final z p6 = this.p;
        p6.a -= 2.5;
        final z p7 = this.p;
        p7.d -= 4.0;
        final z p8 = this.p;
        p8.p += 2.5;
        this.p[23] = new y(this.p, 23, 5.0, 0.25, 5.0, false, false, this.p[0], Color.lightGray, super.p);
        final z p9 = this.p;
        p9.a -= 2.25;
        final z p10 = this.p;
        p10.d += 0.25;
        final z p11 = this.p;
        p11.p += 2.25;
        this.p[24] = new y(this.p, 24, 0.5, 3.75, 0.5, false, false, this.p[0], Color.lightGray, super.p);
        this.p.n(this.p[0].p);
        final z p12 = this.p;
        p12.a += 55.0;
        final z p13 = this.p;
        p13.p -= 7.0;
        final z p14 = this.p;
        p14.d -= 10.0;
        this.p[25] = new y(this.p, 25, 0.25, 6.0, 4.0, false, false, this.p[0], Color.orange, super.p);
        this.p[25].p[3].d(0.1, 0.1, 0.4, 0.25, "charret.gif", false, Color.white, true);
        this.p[25].p[3].d(3.5, 0.1, 0.4, 0.25, "charret.gif", false, Color.white, true);
        this.p[25].p[3].d(0.5, 3.5, 3.0, 2.0, "charret.gif", false, Color.white, true);
        final z p15 = this.p;
        p15.p += 2.5;
        final z p16 = this.p;
        --p16.a;
        this.p[26] = new y(this.p, 26, 0.25, 3.0, 2.0, false, false, this.p[0], Color.yellow, super.p);
        final z p17 = this.p;
        p17.d += 3.0;
        final z p18 = this.p;
        p18.p -= 2.25;
        final z p19 = this.p;
        ++p19.a;
        this.p[27] = new y(this.p, 27, 3.0, 0.25, 4.0, false, false, this.p[0], Color.red, super.p);
        this.p.n(this.p[22].p);
        final z p20 = this.p;
        p20.p -= 3.0;
        final z p21 = this.p;
        p21.d += 0.125;
        this.p[28] = new y(this.p, 28, 1.8, 0.5, 1.4, false, false, this.p[0], Color.red, super.p);
        this.p[28].p[0].p = Color.yellow;
        this.p[28].p[3].p = Color.yellow;
        this.p[28].p[5].p = Color.yellow;
        this.p[28].d(this.p[28].p[0].p[0].p, 0.7853981633974483);
        this.p[28].p[4].d(0.2, 0.2, 1.0, 0.1972, "guide.gif", false, Color.white, true);
        this.p[28].p[4].d(0.8, 1.5, 0.2, 0.04, "ok.gif", false, Color.white, true);
        this.p.n(this.p[22].p[4].p[0].p);
        final z p22 = this.p;
        p22.p -= 2.9;
        final z p23 = this.p;
        p23.a -= 2.35;
        (this.p[29] = new y(this.p, 29, 0.3, 3.0, false, this.p[0], Color.cyan, super.p)).d(this.p[29].p, 0.5235987755982988);
    }
    
    public final void p(final y y, final y y2) {
        this.p.n(y2.p);
        if (super.p.k) {
            final z p2 = this.p;
            p2.d -= 40.0;
            final z p3 = this.p;
            p3.p -= 20.0;
            final z p4 = this.p;
            p4.a -= 400.0;
        }
        else {
            final z p5 = this.p;
            p5.p -= 200.0;
            final z p6 = this.p;
            p6.d -= 40.0;
            final z p7 = this.p;
            p7.a += 20.0;
        }
        (this.p[17] = new y(this.p, 17, 40.0, 105.0, 40.0, true, false, y2, Color.lightGray, super.p)).p(6.0, 4.0, 4.0, 8.0);
        this.p[17].p[4].d(10.0, 10.0, 20.0, 20.0, "charret.gif", false, Color.yellow, false);
        super.p.d = this.p[17];
        final z p8 = this.p;
        p8.a += 20.0;
        this.p[18] = new y(this.p, 18, 30.0, 3.0, false, y2, Color.green, super.p);
        final z p9 = this.p;
        p9.a -= 100.0;
        this.p[19] = new y(this.p, 19, 30.0, 3.0, false, y2, Color.green, super.p);
        final z p10 = this.p;
        p10.p += 60.0;
        final z p11 = this.p;
        p11.a += 60.0;
        this.p[20] = new y(this.p, 20, 30.0, 2.0, false, y2, Color.green, super.p);
        this.p.n(this.p[17].p[0].p[2].p);
        this.p.p -= 60.0;
        this.p.d -= 0.1;
        this.p.a -= 100.0;
        this.p[31] = new y(this.p, 31, 'h', 160.0, 160.0, y2, Color.red, super.p);
        this.p[31].p[0].d(5.0, 5.0, 150.0, 150.0, "ground.gif", false, Color.green, false);
        this.p.n(this.p[31].p[0].p[2].p);
        final z p12 = this.p;
        p12.p += 30.0;
        final z p13 = this.p;
        p13.a += 80.0;
        (this.p[35] = new y(this.p, 35, 13.0, 13.0, 13.0, false, false, y2, Color.red, super.p)).a(this.p[35].p[0].p[2].p, 0.7853981633974483);
        this.p[35].p(this.p[35].p[0].p[2].p, 0.7853981633974483);
        this.p[35].p[0].d(0.0, 0.0, 13.0, 13.0, "cubedeco.gif", false, Color.white, false);
        this.p[35].p[1].d(0.0, 0.0, 13.0, 13.0, "cubedeco.gif", false, Color.white, false);
        this.p[35].p[2].d(0.0, 0.0, 13.0, 13.0, "cubedeco.gif", false, Color.white, false);
        this.p[35].p[3].d(0.0, 0.0, 13.0, 13.0, "cubedeco.gif", false, Color.white, false);
        this.p[35].p[4].d(0.0, 0.0, 13.0, 13.0, "cubedeco.gif", false, Color.white, false);
        this.p[35].p[5].d(0.0, 0.0, 13.0, 13.0, "cubedeco.gif", false, Color.white, false);
        this.p.n(this.p[17].p[0].p[2].p);
        final z p14 = this.p;
        p14.d += 33.0;
        final z p15 = this.p;
        p15.a -= 40.0;
        this.p[32] = new y(this.p, 32, 'h', 40.0, 40.0, y2, Color.yellow, super.p);
        this.p[32].p[0].d(0.0, 0.0, 40.0, 40.0, "charret.gif", false, Color.yellow, false);
        this.p.n(this.p[17].p[0].p[2].p);
        final z p16 = this.p;
        p16.d += 70.0;
        final z p17 = this.p;
        p17.a -= 40.0;
        this.p[33] = new y(this.p, 33, 'h', 40.0, 40.0, y2, Color.yellow, super.p);
        this.p[33].p[0].d(0.0, 0.0, 40.0, 40.0, "charret.gif", false, Color.yellow, false);
        this.p.n(this.p[32].p);
        final z p18 = this.p;
        p18.d += 5.0;
        final z p19 = this.p;
        p19.a += 10.0;
        this.p[34] = new y(this.p, 34, 's', 15.0, 4.0, y2, Color.white, super.p);
        this.p[34].p[0].d(0.0, 0.0, 15.0, 4.0, "ameubler.gif", false, Color.white, true);
        this.p[34].a(this.p[34].p[0].p[2].p, -1.5707963267948966);
    }
    
    public final void p(final c c) {
        this.d(this.p[0].p, 3.141592653589793);
        this.a(super.p.d, 3.141592653589793);
        this.a(75.0);
        this.p(super.p.v);
        this.p(super.p.n);
        this.p = this.p[super.p.a];
        super.p.p = this;
    }
    
    public final void p() {
        this.d = 43;
        this.p[30] = null;
        this.p[31] = null;
        this.p[32] = null;
        this.p[33] = null;
        this.p[34] = null;
        this.p[35] = null;
        this.p(true);
        this.p.n(this.p[2].p);
        final z p = this.p;
        p.p += 12.0;
        final z p2 = this.p;
        p2.d += 10.0;
        final z p3 = this.p;
        p3.a -= 3.75;
        (this.p[11] = new y(this.p, 11, 7.5, 1.0, true, this.p[2], Color.green, super.p)).p(1.45, 1.45, 0.7, 0.7);
        this.p[11].a(this.p[11].p[0].p[2].a, 3.141592653589793);
        this.p[11].d(this.p[11].p[0].p[2].a, 1.0471975511965976);
        this.p[1] = new y(null, 1, 60.0, -1, -1, -1, -1, this.p[0], 4, 5, super.p.p[1], super.p);
        this.p.n(this.p[1].p);
        final z p4 = this.p;
        p4.d += 30.0;
        final z p5 = this.p;
        p5.a -= 5.0;
        (this.p[3] = new y(this.p, 3, 3.0, 24.49, false, this.p[1], Color.white, super.p)).a(this.p[3].p[0].p[2].a, 3.141592653589793);
        final z p6 = this.p;
        p6.p += 5.0;
        final z p7 = this.p;
        p7.a += 11.0;
        (this.p[5] = new y(this.p, 5, 3.0, 24.0, false, this.p[1], Color.white, super.p)).a(this.p[5].p[0].p[2].a, 3.141592653589793);
        this.p[5].d(this.p[5].p[0].p[2].a, 0.1);
        final z p8 = this.p;
        p8.p -= 12.0;
        (this.p[6] = new y(this.p, 6, 3.0, 24.0, false, this.p[1], Color.white, super.p)).a(this.p[6].p[0].p[2].a, 3.141592653589793);
        this.p[6].d(this.p[6].p[0].p[2].a, 0.2);
        final z p9 = this.p;
        p9.p += 6.0;
        final z p10 = this.p;
        p10.a -= 5.0;
        (this.p[10] = new y(this.p, 10, 3.0, 24.0, false, this.p[1], Color.white, super.p)).a(this.p[10].p[0].p[2].a, 3.141592653589793);
        this.p[10].d(this.p[10].p[0].p[2].a, 0.3);
        this.p.n(this.p[0].p);
        final z p11 = this.p;
        p11.d -= 10.0;
        final z p12 = this.p;
        p12.a -= 27.5;
        this.p[13] = new y(this.p, 13, 3.0, 5.0, false, this.p[0], Color.white, super.p);
        this.p.n(this.p[0].p);
        final z p13 = this.p;
        p13.d -= 10.0;
        final z p14 = this.p;
        p14.p -= 5.0;
        final z p15 = this.p;
        p15.a -= 23.5;
        (this.p[14] = new y(this.p, 14, 3.0, 4.0, false, this.p[0], Color.white, super.p)).d(this.p[14].p[0].p[2].a, 0.1);
        this.p.n(this.p[0].p);
        final z p16 = this.p;
        p16.d -= 10.0;
        final z p17 = this.p;
        p17.p += 3.0;
        final z p18 = this.p;
        p18.a -= 20.0;
        (this.p[15] = new y(this.p, 15, 5.0, 1.0, false, this.p[0], Color.white, super.p)).d(this.p[15].p[0].p[2].a, 0.35);
        this.p(this.p[16] = new y(null, 16, this.p, -1, -1, -1, 100, this.p[9], 5, 5, super.p.p[10], super.p), this.p[9], 30, 1);
        this.p[21] = new y(null, 21, this.p + 50.0, -1, -1, -1, 100, this.p[16], 5, 5, super.p.p[11], super.p);
        this.p[36] = new y(null, 36, this.p + 100.0, -1, -1, -1, -1, this.p[21], 5, 4, super.p.p[12], super.p);
        (this.p[37] = new y(null, 37, 40.0, this.p[36], Color.red, super.p)).p(0.0, 0.0, -this.p - 100.0);
        this.p[37].a = true;
        this.p[37].n = true;
        super.p.a = this.p[37];
        super.p.n = this.p[36];
        this.p.n(this.p[36].p);
        final z p19 = this.p;
        p19.p -= 5.0;
        final z p20 = this.p;
        p20.d += 3.0;
        this.p[30] = new y(this.p, 30, 'f', 12.0, 6.0, this.p[36], Color.red, super.p);
        this.p[30].p[0].d(1.0, 1.0, 10.0, 4.0, "bravo.gif", false, Color.white, true);
        this.p[38] = new y(null, 38, this.p, -1, 100, -1, -1, this.p[0], 2, 5, super.p.p[13], super.p);
        this.p[39] = new y(null, 39, this.p, -1, -1, 100, -1, this.p[38], 3, 5, super.p.p[14], super.p);
        this.p[40] = new y(null, 40, this.p, -1, -1, -1, 100, this.p[39], 4, 5, super.p.p[15], super.p);
        this.p[41] = new y(null, 41, this.p, 100, -1, -1, -1, this.p[40], 5, 5, super.p.p[16], super.p);
        this.p[42] = new y(null, 42, 10.0 * this.p, -1, -1, -1, -1, this.p[41], 2, 5, super.p.p[17], super.p);
        this.p(this.p[36], this.p[37]);
        this.p(super.p);
    }
    
    public final void g() {
    }
    
    public final void a() {
        for (int i = 0; i < this.d; ++i) {
            if (this.p[i] != null) {
                this.p[i].h = false;
            }
        }
    }
    
    public final void p(final z z) {
        z.p();
        for (int i = 0; i < this.d; ++i) {
            if (this.p[i] != null) {
                z.d(this.p[i].p);
            }
        }
        z.p(this.d);
    }
    
    public final void a(final z z, final double n) {
        for (int i = 0; i < this.d; ++i) {
            if (this.p[i] != null) {
                this.p[i].a(z, n);
            }
        }
    }
    
    public final void d(final z z, final double n) {
        for (int i = 0; i < this.d; ++i) {
            if (this.p[i] != null) {
                this.p[i].d(z, n);
            }
        }
    }
    
    public final void p(final z z, final double n) {
        for (int i = 0; i < this.d; ++i) {
            if (this.p[i] != null) {
                this.p[i].p(z, n);
            }
        }
    }
    
    public final void v() {
        for (int i = 0; i < this.d; ++i) {
            if (this.p[i] != null) {
                this.p[i].e();
            }
        }
    }
    
    public final void a(final double n) {
        for (int i = 0; i < this.d; ++i) {
            if (this.p[i] != null) {
                this.p[i].p(n);
            }
        }
    }
    
    public final void d(final double n) {
        this.a(n);
        super.p.i();
    }
    
    public final void p(final double n, final double n2, final double n3) {
        for (int i = 0; i < this.d; ++i) {
            if (this.p[i] != null) {
                this.p[i].p(n, n2, n3);
            }
        }
        super.p.i();
    }
    
    public final void n() {
        super.p.p.l();
        for (int i = 0; i < this.d; ++i) {
            if (this.p[i] != null) {
                this.p[i].a();
            }
        }
    }
    
    public final boolean p() {
        boolean b = true;
        for (int i = 0; i < this.p.i; ++i) {
            if (this.p.p[i].d(super.p.p)) {
                b = false;
                break;
            }
        }
        return b;
    }
    
    public final y p(final boolean b) {
        y y = null;
    Label_0150:
        while (true) {
            for (int i = 0; i < this.p.i; ++i) {
                if (this.p.p[i].d(b)) {
                    y = this.p.p[i];
                    return y;
                }
            }
            if (super.p.a != this.p) {
                for (int j = 0; j < this.p.i; ++j) {
                    for (int k = 0; k < this.p.p[j].i; ++k) {
                        if (this.p.p[j].p[k].d(b)) {
                            y = this.p.p[j].p[k];
                            continue Label_0150;
                        }
                    }
                }
                return y;
            }
            break;
        }
        return y;
    }
    
    public final boolean a() {
        if (this.p.n()) {
            return true;
        }
        final boolean b = this.p(true) != null;
        final y p = this.p(false);
        if (p != null) {
            if (this.p == super.p.a && p == super.p.n) {
                super.p.e();
            }
            if (this.p == super.p.n && p == super.p.a) {
                super.p.h();
            }
            if (p == super.p.n) {
                super.p.j = true;
            }
            this.p = p;
        }
        return this.p.n || b;
    }
    
    public final void i() {
        for (int i = 0; i < this.d; ++i) {
            if (this.p[i] != null) {
                this.p[i].f();
            }
        }
    }
    
    public final boolean d() {
        this.i();
        if (this.p[0].e && !super.p.l) {
            final boolean a = this.a();
            this.p.d(true);
            return a && this.p();
        }
        this.n();
        return true;
    }
    
    public final void e() {
        final Graphics p = super.p.p;
        final h p2 = super.p.p;
        if (super.p.i && this.p[0].e && !super.p.l) {
            this.p.d(false);
        }
        else {
            p2.i();
            p2.p();
            p2.n();
        }
    }
    
    public final void f() {
        for (int i = 0; i < this.d; ++i) {
            if (this.p[i] != null) {
                this.p[i].c();
            }
        }
    }
    
    public final void c() {
        for (int i = 0; i < this.d; ++i) {
            if (this.p[i] != null) {
                this.p[i].l();
            }
        }
    }
    
    public final void l() {
        for (int i = 0; i < this.d; ++i) {
            if (this.p[i] != null) {
                this.p[i].d();
            }
        }
    }
    
    public final void b() {
        for (int i = 0; i < this.d; ++i) {
            if (this.p[i] != null) {
                this.p[i].n();
            }
        }
    }
    
    public final void p(double n) {
        if (Math.abs(super.p.a) < 150.0) {
            if (n < 0.0) {
                n = -10.0;
            }
            else {
                n = 10.0;
            }
        }
        final c p = super.p;
        p.a += n;
        this.d(n);
        final z p2 = super.p.p;
        p2.a += n;
        final z d = super.p.d;
        d.a += n;
        super.p.b();
    }
    
    public t(final c p) {
        this.p = null;
        this.p = 70;
        this.p = 50.0;
        this.p = new z();
        this.p = new y[this.p];
        super.p = p;
        super.p.p = true;
        if (super.p.k) {
            this.p();
        }
        else {
            this.g();
        }
        super.p.p = false;
    }
}
