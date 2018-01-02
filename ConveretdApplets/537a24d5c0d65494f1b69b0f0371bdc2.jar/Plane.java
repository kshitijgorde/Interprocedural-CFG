import java.awt.Graphics;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class Plane
{
    Medium m;
    Trackers t;
    int[] ox;
    int[] oy;
    int[] oz;
    int n;
    int[] c;
    int[] oc;
    float[] hsb;
    boolean glass;
    int gr;
    int disline;
    boolean road;
    int master;
    int wx;
    int wz;
    int wy;
    float deltaf;
    float projf;
    int av;
    int bfase;
    boolean nocol;
    int chip;
    float ctmag;
    int cxz;
    int cxy;
    int czy;
    int[] cox;
    int[] coz;
    int[] coy;
    int dx;
    int dy;
    int dz;
    int vx;
    int vy;
    int vz;
    int embos;
    int typ;
    int pa;
    int pb;
    int flx;
    
    public void loadprojf() {
        this.projf = 1.0f;
        int n = 0;
        do {
            int n2 = 0;
            do {
                if (n2 != n) {
                    this.projf *= (float)(Math.sqrt((this.ox[n] - this.ox[n2]) * (this.ox[n] - this.ox[n2]) + (this.oz[n] - this.oz[n2]) * (this.oz[n] - this.oz[n2])) / 100.0);
                }
            } while (++n2 < 3);
        } while (++n < 3);
        this.projf /= 3.0f;
    }
    
    public int ys(final int n, int cz) {
        if (cz < this.m.cz) {
            cz = this.m.cz;
        }
        return (cz - this.m.focus_point) * (this.m.cy - n) / cz + n;
    }
    
    public Plane(final Medium m, final Trackers t, final int[] array, final int[] array2, final int[] array3, final int n, final int[] array4, final boolean glass, final int gr, final int wx, final int wy, final int wz, final int disline, final int bfase, final boolean road) {
        this.c = new int[3];
        this.oc = new int[3];
        this.hsb = new float[3];
        this.glass = false;
        this.gr = 0;
        this.disline = 7;
        this.road = false;
        this.master = 0;
        this.wx = 0;
        this.wz = 0;
        this.wy = 0;
        this.deltaf = 1.0f;
        this.projf = 1.0f;
        this.av = 0;
        this.bfase = 0;
        this.nocol = false;
        this.chip = 0;
        this.ctmag = 0.0f;
        this.cxz = 0;
        this.cxy = 0;
        this.czy = 0;
        this.cox = new int[3];
        this.coz = new int[3];
        this.coy = new int[3];
        this.dx = 0;
        this.dy = 0;
        this.dz = 0;
        this.vx = 0;
        this.vy = 0;
        this.vz = 0;
        this.embos = 0;
        this.typ = 0;
        this.pa = 0;
        this.pb = 0;
        this.flx = 0;
        this.m = m;
        this.t = t;
        this.n = n;
        this.ox = new int[this.n];
        this.oz = new int[this.n];
        this.oy = new int[this.n];
        for (int i = 0; i < this.n; ++i) {
            this.ox[i] = array[i];
            this.oy[i] = array3[i];
            this.oz[i] = array2[i];
        }
        final int abs = Math.abs(this.ox[2] - this.ox[1]);
        final int abs2 = Math.abs(this.oy[2] - this.oy[1]);
        final int abs3 = Math.abs(this.oz[2] - this.oz[1]);
        if (abs2 <= abs && abs2 <= abs3) {
            this.typ = 2;
        }
        if (abs <= abs2 && abs <= abs3) {
            this.typ = 1;
        }
        if (abs3 <= abs && abs3 <= abs2) {
            this.typ = 3;
        }
        int n2 = 0;
        do {
            this.oc[n2] = array4[n2];
        } while (++n2 < 3);
        if (array4[0] == array4[1] && array4[1] == array4[2]) {
            this.nocol = true;
        }
        if (!glass) {
            int n3 = 0;
            do {
                this.c[n3] = (int)(array4[n3] + array4[n3] * (this.m.snap[n3] / 100.0f));
                if (this.c[n3] > 255) {
                    this.c[n3] = 255;
                }
                if (this.c[n3] < 0) {
                    this.c[n3] = 0;
                }
            } while (++n3 < 3);
        }
        if (glass) {
            int n4 = 0;
            do {
                this.c[n4] = (this.m.csky[n4] * this.m.fade[0] * 2 + this.m.cfade[n4] * 3000) / (this.m.fade[0] * 2 + 3000);
            } while (++n4 < 3);
        }
        this.disline = disline;
        this.bfase = bfase;
        this.glass = glass;
        Color.RGBtoHSB(this.c[0], this.c[1], this.c[2], this.hsb);
        if (!this.nocol && !this.glass) {
            if (this.bfase > 20 && this.hsb[1] > 0.2) {
                this.hsb[1] = 0.2f;
            }
            if (this.bfase > 30) {
                if (this.hsb[2] < 0.5) {
                    this.hsb[2] = 0.5f;
                }
                if (this.hsb[1] > 0.1) {
                    this.hsb[1] = 0.1f;
                }
            }
            if (this.bfase > 40) {
                this.hsb[1] = 0.05f;
            }
            if (this.bfase > 50) {
                if (this.hsb[2] > 0.8) {
                    this.hsb[2] = 0.8f;
                }
                this.hsb[0] = 0.075f;
                this.hsb[1] = 0.05f;
            }
            if (this.bfase > 60) {
                this.hsb[0] = 0.05f;
            }
        }
        this.road = road;
        this.gr = gr;
        this.wx = wx;
        this.wy = wy;
        this.wz = wz;
        int n5 = 0;
        do {
            int n6 = 0;
            do {
                if (n6 != n5) {
                    this.deltaf *= (float)(Math.sqrt((this.ox[n6] - this.ox[n5]) * (this.ox[n6] - this.ox[n5]) + (this.oy[n6] - this.oy[n5]) * (this.oy[n6] - this.oy[n5]) + (this.oz[n6] - this.oz[n5]) * (this.oz[n6] - this.oz[n5])) / 100.0);
                }
            } while (++n6 < 3);
        } while (++n5 < 3);
        this.deltaf /= 3.0f;
    }
    
    public void d(final Graphics graphics, final int n, final int n2, final int n3, final int cxz, final int cxy, final int czy, final int n4, final int n5, boolean b) {
        final int[] array = new int[this.n];
        final int[] array2 = new int[this.n];
        final int[] array3 = new int[this.n];
        if (this.embos == 0) {
            for (int i = 0; i < this.n; ++i) {
                array[i] = this.ox[i] + n;
                array3[i] = this.oy[i] + n2;
                array2[i] = this.oz[i] + n3;
            }
        }
        else {
            if (this.embos <= 11 && this.m.random() > 0.5 && !this.glass) {
                for (int j = 0; j < this.n; ++j) {
                    array[j] = (int)(this.ox[j] + n + (15.0f - this.m.random() * 30.0f));
                    array3[j] = (int)(this.oy[j] + n2 + (15.0f - this.m.random() * 30.0f));
                    array2[j] = (int)(this.oz[j] + n3 + (15.0f - this.m.random() * 30.0f));
                }
                this.rot(array, array3, n, n2, cxy, this.n);
                this.rot(array3, array2, n2, n3, czy, this.n);
                this.rot(array, array2, n, n3, cxz, this.n);
                this.rot(array, array2, this.m.cx, this.m.cz, this.m.xz, this.n);
                this.rot(array3, array2, this.m.cy, this.m.cz, this.m.zy, this.n);
                final int[] array4 = new int[this.n];
                final int[] array5 = new int[this.n];
                for (int k = 0; k < this.n; ++k) {
                    array4[k] = this.xs(array[k], array2[k]);
                    array5[k] = this.ys(array3[k], array2[k]);
                }
                graphics.setColor(new Color(230, 230, 230));
                graphics.fillPolygon(array4, array5, this.n);
            }
            float n6 = 1.0f;
            if (this.embos <= 4) {
                n6 = 1.0f + this.m.random() / 5.0f;
            }
            if (this.embos > 4 && this.embos <= 7) {
                n6 = 1.0f + this.m.random() / 4.0f;
            }
            if (this.embos > 7 && this.embos <= 9) {
                n6 = 1.0f + this.m.random() / 3.0f;
                if (this.hsb[2] > 0.7) {
                    this.hsb[2] = 0.7f;
                }
            }
            if (this.embos > 9 && this.embos <= 10) {
                n6 = 1.0f + this.m.random() / 2.0f;
                if (this.hsb[2] > 0.6) {
                    this.hsb[2] = 0.6f;
                }
            }
            if (this.embos > 10 && this.embos <= 12) {
                n6 = 1.0f + this.m.random() / 1.0f;
                if (this.hsb[2] > 0.5) {
                    this.hsb[2] = 0.5f;
                }
            }
            if (this.embos == 12) {
                this.chip = 1;
                this.ctmag = 2.0f;
                this.bfase = -7;
            }
            if (this.embos == 13) {
                this.hsb[1] = 0.2f;
                this.hsb[2] = 0.4f;
            }
            if (this.embos == 16) {
                this.pa = (int)(this.m.random() * this.n);
                this.pb = (int)(this.m.random() * this.n);
                while (this.pa == this.pb) {
                    this.pb = (int)(this.m.random() * this.n);
                }
            }
            if (this.embos >= 16) {
                int n7 = 1;
                int n8 = 1;
                int l;
                for (l = Math.abs(czy); l > 270; l -= 360) {}
                if (Math.abs(l) > 90) {
                    n7 = -1;
                }
                int abs;
                for (abs = Math.abs(cxy); abs > 270; abs -= 360) {}
                if (Math.abs(abs) > 90) {
                    n8 = -1;
                }
                final int[] array6 = new int[3];
                final int[] array7 = new int[3];
                array[0] = this.ox[this.pa] + n;
                array3[0] = this.oy[this.pa] + n2;
                array2[0] = this.oz[this.pa] + n3;
                array[1] = this.ox[this.pb] + n;
                array3[1] = this.oy[this.pb] + n2;
                array2[1] = this.oz[this.pb] + n3;
                while (Math.abs(array[0] - array[1]) > 100) {
                    if (array[1] > array[0]) {
                        final int[] array8 = array;
                        final int n9 = 1;
                        array8[n9] -= 30;
                    }
                    else {
                        final int[] array9 = array;
                        final int n10 = 1;
                        array9[n10] += 30;
                    }
                }
                while (Math.abs(array2[0] - array2[1]) > 100) {
                    if (array2[1] > array2[0]) {
                        final int[] array10 = array2;
                        final int n11 = 1;
                        array10[n11] -= 30;
                    }
                    else {
                        final int[] array11 = array2;
                        final int n12 = 1;
                        array11[n12] += 30;
                    }
                }
                final int n13 = (int)(Math.abs(array[0] - array[1]) / 3 * (0.5 - this.m.random()));
                final int n14 = (int)(Math.abs(array2[0] - array2[1]) / 3 * (0.5 - this.m.random()));
                array[2] = (array[0] + array[1]) / 2 + n13;
                array2[2] = (array2[0] + array2[1]) / 2 + n14;
                final int n15 = (int)((Math.abs(array[0] - array[1]) + Math.abs(array2[0] - array2[1])) / 1.5 * (this.m.random() / 2.0f + 0.5));
                array3[2] = (array3[0] + array3[1]) / 2 - n7 * n8 * n15;
                this.rot(array, array3, n, n2, cxy, 3);
                this.rot(array3, array2, n2, n3, czy, 3);
                this.rot(array, array2, n, n3, cxz, 3);
                this.rot(array, array2, this.m.cx, this.m.cz, this.m.xz, 3);
                this.rot(array3, array2, this.m.cy, this.m.cz, this.m.zy, 3);
                int n16 = 0;
                do {
                    array6[n16] = this.xs(array[n16], array2[n16]);
                    array7[n16] = this.ys(array3[n16], array2[n16]);
                } while (++n16 < 3);
                int n17 = (int)(255.0f + 255.0f * (this.m.snap[0] / 400.0f));
                if (n17 > 255) {
                    n17 = 255;
                }
                if (n17 < 0) {
                    n17 = 0;
                }
                int n18 = (int)(169.0f + 169.0f * (this.m.snap[1] / 300.0f));
                if (n18 > 255) {
                    n18 = 255;
                }
                if (n18 < 0) {
                    n18 = 0;
                }
                int n19 = (int)(89.0f + 89.0f * (this.m.snap[2] / 200.0f));
                if (n19 > 255) {
                    n19 = 255;
                }
                if (n19 < 0) {
                    n19 = 0;
                }
                graphics.setColor(new Color(n17, n18, n19));
                graphics.fillPolygon(array6, array7, 3);
                array[0] = this.ox[this.pa] + n;
                array3[0] = this.oy[this.pa] + n2;
                array2[0] = this.oz[this.pa] + n3;
                array[1] = this.ox[this.pb] + n;
                array3[1] = this.oy[this.pb] + n2;
                array2[1] = this.oz[this.pb] + n3;
                while (Math.abs(array[0] - array[1]) > 100) {
                    if (array[1] > array[0]) {
                        final int[] array12 = array;
                        final int n20 = 1;
                        array12[n20] -= 30;
                    }
                    else {
                        final int[] array13 = array;
                        final int n21 = 1;
                        array13[n21] += 30;
                    }
                }
                while (Math.abs(array2[0] - array2[1]) > 100) {
                    if (array2[1] > array2[0]) {
                        final int[] array14 = array2;
                        final int n22 = 1;
                        array14[n22] -= 30;
                    }
                    else {
                        final int[] array15 = array2;
                        final int n23 = 1;
                        array15[n23] += 30;
                    }
                }
                array[2] = (array[0] + array[1]) / 2 + n13;
                array2[2] = (array2[0] + array2[1]) / 2 + n14;
                array3[2] = (array3[0] + array3[1]) / 2 - n7 * n8 * (int)(n15 * 0.8);
                this.rot(array, array3, n, n2, cxy, 3);
                this.rot(array3, array2, n2, n3, czy, 3);
                this.rot(array, array2, n, n3, cxz, 3);
                this.rot(array, array2, this.m.cx, this.m.cz, this.m.xz, 3);
                this.rot(array3, array2, this.m.cy, this.m.cz, this.m.zy, 3);
                int n24 = 0;
                do {
                    array6[n24] = this.xs(array[n24], array2[n24]);
                    array7[n24] = this.ys(array3[n24], array2[n24]);
                } while (++n24 < 3);
                int n25 = (int)(255.0f + 255.0f * (this.m.snap[0] / 400.0f));
                if (n25 > 255) {
                    n25 = 255;
                }
                if (n25 < 0) {
                    n25 = 0;
                }
                int n26 = (int)(207.0f + 207.0f * (this.m.snap[1] / 300.0f));
                if (n26 > 255) {
                    n26 = 255;
                }
                if (n26 < 0) {
                    n26 = 0;
                }
                int n27 = (int)(136.0f + 136.0f * (this.m.snap[2] / 200.0f));
                if (n27 > 255) {
                    n27 = 255;
                }
                if (n27 < 0) {
                    n27 = 0;
                }
                graphics.setColor(new Color(n25, n26, n27));
                graphics.fillPolygon(array6, array7, 3);
            }
            for (int n28 = 0; n28 < this.n; ++n28) {
                if (this.typ == 1) {
                    array[n28] = (int)(this.ox[n28] * n6 + n);
                }
                else {
                    array[n28] = this.ox[n28] + n;
                }
                if (this.typ == 2) {
                    array3[n28] = (int)(this.oy[n28] * n6 + n2);
                }
                else {
                    array3[n28] = this.oy[n28] + n2;
                }
                if (this.typ == 3) {
                    array2[n28] = (int)(this.oz[n28] * n6 + n3);
                }
                else {
                    array2[n28] = this.oz[n28] + n3;
                }
            }
            if (this.embos != 70) {
                ++this.embos;
            }
            else {
                this.embos = 16;
            }
        }
        if (this.wz != 0) {
            this.rot(array3, array2, this.wy + n2, this.wz + n3, n5, this.n);
        }
        if (this.wx != 0) {
            this.rot(array, array2, this.wx + n, this.wz + n3, n4, this.n);
        }
        if (this.chip == 1 && (this.m.random() > 0.6 || this.bfase == 0)) {
            this.chip = 0;
            if (this.bfase == 0 && this.nocol) {
                this.bfase = 1;
            }
        }
        if (this.chip != 0) {
            if (this.chip == 1) {
                this.cxz = cxz;
                this.cxy = cxy;
                this.czy = czy;
                final int n29 = (int)(this.m.random() * this.n);
                this.cox[0] = this.ox[n29];
                this.coz[0] = this.oz[n29];
                this.coy[0] = this.oy[n29];
                if (this.ctmag > 3.0f) {
                    this.ctmag = 3.0f;
                }
                if (this.ctmag < -3.0f) {
                    this.ctmag = -3.0f;
                }
                this.cox[1] = (int)(this.cox[0] + this.ctmag * (10.0f - this.m.random() * 20.0f));
                this.cox[2] = (int)(this.cox[0] + this.ctmag * (10.0f - this.m.random() * 20.0f));
                this.coy[1] = (int)(this.coy[0] + this.ctmag * (10.0f - this.m.random() * 20.0f));
                this.coy[2] = (int)(this.coy[0] + this.ctmag * (10.0f - this.m.random() * 20.0f));
                this.coz[1] = (int)(this.coz[0] + this.ctmag * (10.0f - this.m.random() * 20.0f));
                this.coz[2] = (int)(this.coz[0] + this.ctmag * (10.0f - this.m.random() * 20.0f));
                this.dx = 0;
                this.dy = 0;
                this.dz = 0;
                if (this.bfase != -7) {
                    this.vx = (int)(this.ctmag * (30.0f - this.m.random() * 60.0f));
                    this.vz = (int)(this.ctmag * (30.0f - this.m.random() * 60.0f));
                    this.vy = (int)(this.ctmag * (30.0f - this.m.random() * 60.0f));
                }
                else {
                    this.vx = (int)(this.ctmag * (10.0f - this.m.random() * 20.0f));
                    this.vz = (int)(this.ctmag * (10.0f - this.m.random() * 20.0f));
                    this.vy = (int)(this.ctmag * (10.0f - this.m.random() * 20.0f));
                }
                this.chip = 2;
            }
            final int[] array16 = new int[3];
            final int[] array17 = new int[3];
            final int[] array18 = new int[3];
            int n30 = 0;
            do {
                array16[n30] = this.cox[n30] + n;
                array18[n30] = this.coy[n30] + n2;
                array17[n30] = this.coz[n30] + n3;
            } while (++n30 < 3);
            this.rot(array16, array18, n, n2, this.cxy, 3);
            this.rot(array18, array17, n2, n3, this.czy, 3);
            this.rot(array16, array17, n, n3, this.cxz, 3);
            int n31 = 0;
            do {
                final int[] array19 = array16;
                final int n32 = n31;
                array19[n32] += this.dx;
                final int[] array20 = array18;
                final int n33 = n31;
                array20[n33] += this.dy;
                final int[] array21 = array17;
                final int n34 = n31;
                array21[n34] += this.dz;
            } while (++n31 < 3);
            this.dx += this.vx;
            this.dz += this.vz;
            this.dy += this.vy;
            this.vy += 7;
            if (array18[0] > this.m.ground) {
                this.chip = 19;
            }
            this.rot(array16, array17, this.m.cx, this.m.cz, this.m.xz, 3);
            this.rot(array18, array17, this.m.cy, this.m.cz, this.m.zy, 3);
            final int[] array22 = new int[3];
            final int[] array23 = new int[3];
            int n35 = 0;
            do {
                array22[n35] = this.xs(array16[n35], array17[n35]);
                array23[n35] = this.ys(array18[n35], array17[n35]);
                if (array23[n35] < 45 && this.m.flex != 0) {
                    this.m.flex = 0;
                }
            } while (++n35 < 3);
            final int n36 = (int)(this.m.random() * 3.0f);
            if (this.bfase != -7) {
                if (n36 == 0) {
                    graphics.setColor(new Color(this.c[0], this.c[1], this.c[2]).darker());
                }
                if (n36 == 1) {
                    graphics.setColor(new Color(this.c[0], this.c[1], this.c[2]));
                }
                if (n36 == 2) {
                    graphics.setColor(new Color(this.c[0], this.c[1], this.c[2]).brighter());
                }
            }
            else {
                graphics.setColor(Color.getHSBColor(this.hsb[0], this.hsb[1], this.hsb[2]));
            }
            graphics.fillPolygon(array22, array23, 3);
            ++this.chip;
            if (this.chip == 20) {
                this.chip = 0;
            }
        }
        this.rot(array, array3, n, n2, cxy, this.n);
        this.rot(array3, array2, n2, n3, czy, this.n);
        this.rot(array, array2, n, n3, cxz, this.n);
        if (cxy != 0 || czy != 0 || cxz != 0) {
            this.projf = 1.0f;
            int n37 = 0;
            do {
                int n38 = 0;
                do {
                    if (n38 != n37) {
                        this.projf *= (float)(Math.sqrt((array[n37] - array[n38]) * (array[n37] - array[n38]) + (array2[n37] - array2[n38]) * (array2[n37] - array2[n38])) / 100.0);
                    }
                } while (++n38 < 3);
            } while (++n37 < 3);
            this.projf /= 3.0f;
        }
        this.rot(array, array2, this.m.cx, this.m.cz, this.m.xz, this.n);
        boolean b2 = false;
        final int[] array24 = new int[this.n];
        final int[] array25 = new int[this.n];
        int n39 = 500;
        for (int n40 = 0; n40 < this.n; ++n40) {
            array24[n40] = this.xs(array[n40], array2[n40]);
            array25[n40] = this.ys(array3[n40], array2[n40]);
        }
        int n41 = 0;
        int n42 = 1;
        for (int n43 = 0; n43 < this.n; ++n43) {
            for (int n44 = 0; n44 < this.n; ++n44) {
                if (n43 != n44 && Math.abs(array24[n43] - array24[n44]) - Math.abs(array25[n43] - array25[n44]) < n39) {
                    n42 = n43;
                    n41 = n44;
                    n39 = Math.abs(array24[n43] - array24[n44]) - Math.abs(array25[n43] - array25[n44]);
                }
            }
        }
        if (array25[n41] < array25[n42]) {
            final int n45 = n41;
            n41 = n42;
            n42 = n45;
        }
        if (this.spy(array[n41], array2[n41]) > this.spy(array[n42], array2[n42])) {
            b2 = true;
            int n46 = 0;
            for (int n47 = 0; n47 < this.n; ++n47) {
                if (array2[n47] < 50 && array3[n47] > this.m.cy) {
                    b2 = false;
                }
                else if (array3[n47] == array3[0]) {
                    ++n46;
                }
            }
            if (n46 == this.n && array3[0] > this.m.cy) {
                b2 = false;
            }
        }
        this.rot(array3, array2, this.m.cy, this.m.cz, this.m.zy, this.n);
        int n48 = 1;
        final int[] array26 = new int[this.n];
        final int[] array27 = new int[this.n];
        int n49 = 0;
        int n50 = 0;
        int n51 = 0;
        int n52 = 0;
        int n53 = 0;
        for (int n54 = 0; n54 < this.n; ++n54) {
            array26[n54] = this.xs(array[n54], array2[n54]);
            array27[n54] = this.ys(array3[n54], array2[n54]);
            if (array27[n54] < 0 || array2[n54] < 10) {
                ++n49;
            }
            if (array27[n54] > this.m.h || array2[n54] < 10) {
                ++n50;
            }
            if (array26[n54] < 0 || array2[n54] < 10) {
                ++n51;
            }
            if (array26[n54] > this.m.w || array2[n54] < 10) {
                ++n52;
            }
            if (array27[n54] < 45 && this.m.flex != 0) {
                this.m.flex = 0;
            }
            if (array2[n54] < 10) {
                ++n53;
            }
        }
        if (n51 == this.n || n49 == this.n || n50 == this.n || n52 == this.n) {
            n48 = 0;
        }
        if (this.m.trk && (n51 != 0 || n49 != 0 || n50 != 0 || n52 != 0)) {
            n48 = 0;
        }
        if (n53 != 0) {
            b = true;
        }
        if (n48 != 0) {
            int n55 = 0;
            int n56 = 0;
            int n57 = 0;
            int n58 = 0;
            int n59 = 0;
            int n60 = 0;
            for (int n61 = 0; n61 < this.n; ++n61) {
                int n62 = 0;
                int n63 = 0;
                int n64 = 0;
                int n65 = 0;
                int n66 = 0;
                int n67 = 0;
                for (int n68 = 0; n68 < this.n; ++n68) {
                    if (array3[n61] >= array3[n68]) {
                        ++n62;
                    }
                    if (array3[n61] <= array3[n68]) {
                        ++n63;
                    }
                    if (array[n61] >= array[n68]) {
                        ++n64;
                    }
                    if (array[n61] <= array[n68]) {
                        ++n65;
                    }
                    if (array2[n61] >= array2[n68]) {
                        ++n66;
                    }
                    if (array2[n61] <= array2[n68]) {
                        ++n67;
                    }
                }
                if (n62 == this.n) {
                    n55 = array3[n61];
                }
                if (n63 == this.n) {
                    n56 = array3[n61];
                }
                if (n64 == this.n) {
                    n57 = array[n61];
                }
                if (n65 == this.n) {
                    n58 = array[n61];
                }
                if (n66 == this.n) {
                    n59 = array2[n61];
                }
                if (n67 == this.n) {
                    n60 = array2[n61];
                }
            }
            final int n69 = (n55 + n56) / 2;
            final int n70 = (n57 + n58) / 2;
            final int n71 = (n59 + n60) / 2;
            this.av = (int)Math.sqrt((this.m.cy - n69) * (this.m.cy - n69) + (this.m.cx - n70) * (this.m.cx - n70) + n71 * n71 + Math.abs(this.gr * this.gr * this.gr));
            if (!this.m.trk && (this.av > this.m.fade[this.disline] || this.av == 0)) {
                n48 = 0;
            }
            if (this.gr > 0 && this.av > 3500) {
                n48 = 0;
            }
            if (this.av > 3000 && this.m.adv <= 900) {
                b = true;
            }
            if (this.flx != 0 && this.m.random() > 0.3) {
                n48 = 0;
            }
        }
        if (n48 != 0) {
            float n72 = (float)(this.projf / this.deltaf + 0.3);
            if (b) {
                boolean b3 = false;
                if (n72 > 1.0f) {
                    if (n72 >= 1.27) {
                        b3 = true;
                    }
                    n72 = 1.0f;
                }
                if (b3) {
                    n72 *= 0.89;
                }
                else {
                    n72 *= 0.86;
                }
                if (n72 < 0.37) {
                    n72 = 0.37f;
                }
                if (this.gr == -9) {
                    n72 = 0.7f;
                }
                if (this.gr != -7 && b2) {
                    n72 = 0.32f;
                }
                if (this.gr == -8) {
                    n72 = 1.0f;
                }
            }
            else {
                if (n72 > 1.0f) {
                    n72 = 1.0f;
                }
                if (n72 < 0.6 || b2) {
                    n72 = 0.6f;
                }
            }
            Color color;
            if (!this.m.trk) {
                color = Color.getHSBColor(this.hsb[0], this.hsb[1], this.hsb[2] * n72);
            }
            else {
                final float[] array28 = new float[3];
                Color.RGBtoHSB(this.oc[0], this.oc[1], this.oc[2], array28);
                color = Color.getHSBColor(0.105f, 0.95f, array28[2] * n72 + 0.1f);
            }
            int red = color.getRed();
            int green = color.getGreen();
            int blue = color.getBlue();
            if (!this.m.trk) {
                int n73 = 0;
                do {
                    if (this.av > this.m.fade[n73]) {
                        red = (red * 3 + this.m.cfade[0]) / 4;
                        green = (green * 3 + this.m.cfade[1]) / 4;
                        blue = (blue * 3 + this.m.cfade[2]) / 4;
                    }
                } while (++n73 < 8);
            }
            graphics.setColor(new Color(red, green, blue));
            graphics.fillPolygon(array26, array27, this.n);
            if (!b) {
                if (this.flx == 0) {
                    graphics.setColor(new Color(0, 0, 0));
                    graphics.drawPolygon(array26, array27, this.n);
                }
                else {
                    if (this.flx == 2) {
                        graphics.setColor(new Color(0, 0, 0));
                        graphics.drawPolygon(array26, array27, this.n);
                    }
                    if (this.flx == 1) {
                        final boolean b4 = false;
                        int n74 = (int)(223.0f + 223.0f * (this.m.snap[1] / 100.0f));
                        if (n74 > 255) {
                            n74 = 255;
                        }
                        if (n74 < 0) {
                            n74 = 0;
                        }
                        int n75 = (int)(255.0f + 255.0f * (this.m.snap[2] / 100.0f));
                        if (n75 > 255) {
                            n75 = 255;
                        }
                        if (n75 < 0) {
                            n75 = 0;
                        }
                        graphics.setColor(new Color((int)(b4 ? 1 : 0), n74, n75));
                        graphics.drawPolygon(array26, array27, this.n);
                        this.flx = 2;
                    }
                    if (this.flx == 3) {
                        final boolean b5 = false;
                        int n76 = (int)(255.0f + 255.0f * (this.m.snap[1] / 100.0f));
                        if (n76 > 255) {
                            n76 = 255;
                        }
                        if (n76 < 0) {
                            n76 = 0;
                        }
                        int n77 = (int)(223.0f + 223.0f * (this.m.snap[2] / 100.0f));
                        if (n77 > 255) {
                            n77 = 255;
                        }
                        if (n77 < 0) {
                            n77 = 0;
                        }
                        graphics.setColor(new Color((int)(b5 ? 1 : 0), n76, n77));
                        graphics.drawPolygon(array26, array27, this.n);
                        this.flx = 2;
                    }
                }
            }
            else if (this.av <= 3000 && !this.m.trk && this.road && this.m.fade[0] > 4000) {
                red -= 10;
                if (red < 0) {
                    red = 0;
                }
                green -= 10;
                if (green < 0) {
                    green = 0;
                }
                blue -= 10;
                if (blue < 0) {
                    blue = 0;
                }
                graphics.setColor(new Color(red, green, blue));
                graphics.drawPolygon(array26, array27, this.n);
            }
            if (!this.glass && this.gr == -10 && !this.m.trk) {
                int n78 = this.c[0];
                int n79 = this.c[1];
                int n80 = this.c[2];
                int n81 = 0;
                do {
                    if (this.av > this.m.fade[n81]) {
                        n78 = (n78 * 3 + this.m.cfade[0]) / 4;
                        n79 = (n79 * 3 + this.m.cfade[1]) / 4;
                        n80 = (n80 * 3 + this.m.cfade[2]) / 4;
                    }
                } while (++n81 < 8);
                graphics.setColor(new Color(n78, n79, n80));
                graphics.drawPolygon(array26, array27, this.n);
            }
        }
    }
    
    public void rot(final int[] array, final int[] array2, final int n, final int n2, final int n3, final int n4) {
        if (n3 != 0) {
            for (int i = 0; i < n4; ++i) {
                final int n5 = array[i];
                final int n6 = array2[i];
                array[i] = n + (int)((n5 - n) * this.m.cos(n3) - (n6 - n2) * this.m.sin(n3));
                array2[i] = n2 + (int)((n5 - n) * this.m.sin(n3) + (n6 - n2) * this.m.cos(n3));
            }
        }
    }
    
    public int xs(final int n, int cz) {
        if (cz < this.m.cz) {
            cz = this.m.cz;
        }
        return (cz - this.m.focus_point) * (this.m.cx - n) / cz + n;
    }
    
    public void s(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        if (this.gr <= 0) {
            final int[] array = new int[this.n];
            final int[] array2 = new int[this.n];
            final int[] array3 = new int[this.n];
            for (int i = 0; i < this.n; ++i) {
                array[i] = this.ox[i] + n;
                array3[i] = this.oy[i] + n2;
                array2[i] = this.oz[i] + n3;
            }
            this.rot(array, array3, n, n2, n5, this.n);
            this.rot(array3, array2, n2, n3, n6, this.n);
            this.rot(array, array2, n, n3, n4, this.n);
            int n8 = (int)(this.m.cgrnd[0] / 1.5);
            int n9 = (int)(this.m.cgrnd[1] / 1.5);
            int n10 = (int)(this.m.cgrnd[2] / 1.5);
            for (int j = 0; j < this.n; ++j) {
                array3[j] = this.m.ground;
            }
            if (n7 == 0) {
                for (int k = this.t.nt - 1; k >= 0; --k) {
                    int n11 = 0;
                    for (int l = 0; l < this.n; ++l) {
                        if (Math.abs(this.t.zy[k]) != 90 && Math.abs(this.t.xy[k]) != 90 && Math.abs(array[l] - (this.t.x[k] - this.m.x)) < this.t.radx[k] && Math.abs(array2[l] - (this.t.z[k] - this.m.z)) < this.t.radz[k]) {
                            ++n11;
                        }
                    }
                    if (n11 > this.n / 2) {
                        for (int n12 = 0; n12 < this.n; ++n12) {
                            array3[n12] = this.t.y[k] - this.m.y;
                            if (this.t.zy[k] != 0) {
                                final int[] array4 = array3;
                                final int n13 = n12;
                                array4[n13] += (int)((array2[n12] - (this.t.z[k] - this.m.z - this.t.radz[k])) * this.m.sin(this.t.zy[k]) / this.m.sin(90 - this.t.zy[k]) - this.t.radz[k] * this.m.sin(this.t.zy[k]) / this.m.sin(90 - this.t.zy[k]));
                            }
                            if (this.t.xy[k] != 0) {
                                final int[] array5 = array3;
                                final int n14 = n12;
                                array5[n14] += (int)((array[n12] - (this.t.x[k] - this.m.x - this.t.radx[k])) * this.m.sin(this.t.xy[k]) / this.m.sin(90 - this.t.xy[k]) - this.t.radx[k] * this.m.sin(this.t.xy[k]) / this.m.sin(90 - this.t.xy[k]));
                            }
                        }
                        n8 = (int)(this.t.c[k][0] / 1.5);
                        n9 = (int)(this.t.c[k][1] / 1.5);
                        n10 = (int)(this.t.c[k][2] / 1.5);
                        break;
                    }
                }
            }
            int n15 = 1;
            final int[] array6 = new int[this.n];
            final int[] array7 = new int[this.n];
            if (n7 == 2) {
                n8 = 76;
                n9 = 98;
                n10 = 142;
            }
            else {
                for (int n16 = 0; n16 < this.m.nsp; ++n16) {
                    for (int n17 = 0; n17 < this.n; ++n17) {
                        if (Math.abs(array[n17] - this.m.spx[n16]) < this.m.sprad[n16] && Math.abs(array2[n17] - this.m.spz[n16]) < this.m.sprad[n16]) {
                            n15 = 0;
                        }
                    }
                }
            }
            if (n15 != 0) {
                this.rot(array, array2, this.m.cx, this.m.cz, this.m.xz, this.n);
                this.rot(array3, array2, this.m.cy, this.m.cz, this.m.zy, this.n);
                int n18 = 0;
                int n19 = 0;
                int n20 = 0;
                int n21 = 0;
                for (int n22 = 0; n22 < this.n; ++n22) {
                    array6[n22] = this.xs(array[n22], array2[n22]);
                    array7[n22] = this.ys(array3[n22], array2[n22]);
                    if (array7[n22] < 0 || array2[n22] < 10) {
                        ++n18;
                    }
                    if (array7[n22] > this.m.h || array2[n22] < 10) {
                        ++n19;
                    }
                    if (array6[n22] < 0 || array2[n22] < 10) {
                        ++n20;
                    }
                    if (array6[n22] > this.m.w || array2[n22] < 10) {
                        ++n21;
                    }
                }
                if (n20 == this.n || n18 == this.n || n19 == this.n || n21 == this.n) {
                    n15 = 0;
                }
            }
            if (n15 != 0) {
                int n23 = 0;
                do {
                    if (this.av > this.m.fade[n23]) {
                        n8 = (n8 * 3 + this.m.cfade[0]) / 4;
                        n9 = (n9 * 3 + this.m.cfade[1]) / 4;
                        n10 = (n10 * 3 + this.m.cfade[2]) / 4;
                    }
                } while (++n23 < 8);
                graphics.setColor(new Color(n8, n9, n10));
                graphics.fillPolygon(array6, array7, this.n);
            }
        }
    }
    
    public int spy(final int n, final int n2) {
        return (int)Math.sqrt((n - this.m.cx) * (n - this.m.cx) + n2 * n2);
    }
}
