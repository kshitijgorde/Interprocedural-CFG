import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class Madness
{
    Medium m;
    Record rpd;
    xtGraphics xt;
    int cn;
    int im;
    int mxz;
    int cxz;
    float[][] acelf;
    int[][] swits;
    int[] handb;
    float[] airs;
    int[] airc;
    float[] drag;
    int[] turn;
    float[] grip;
    float[] bounce;
    float[] simag;
    float[] moment;
    float[] comprad;
    int[] push;
    int[] revpush;
    int[] lift;
    int[] revlift;
    int[] powerloss;
    int[] flipy;
    int[] msquash;
    int[] clrad;
    int[] maxmag;
    float[] dammult;
    int[] outdam;
    boolean[] dominate;
    boolean[] caught;
    int pzy;
    int pxy;
    float speed;
    float forca;
    float[] scy;
    float[] scz;
    float[] scx;
    boolean mtouch;
    boolean wtouch;
    int cntouch;
    boolean capsized;
    int txz;
    int fxz;
    int pmlt;
    int nmlt;
    int dcnt;
    int skid;
    boolean pushed;
    boolean gtouch;
    boolean pl;
    boolean pr;
    boolean pd;
    boolean pu;
    int loop;
    float ucomp;
    float dcomp;
    float lcomp;
    float rcomp;
    int lxz;
    int travxy;
    int travzy;
    int travxz;
    int trcnt;
    int capcnt;
    int srfcnt;
    boolean rtab;
    boolean ftab;
    boolean btab;
    boolean surfer;
    float powerup;
    int xtpower;
    float tilt;
    int squash;
    int nbsq;
    int hitmag;
    int cntdest;
    boolean dest;
    boolean newcar;
    int pan;
    int pcleared;
    int clear;
    int nlaps;
    int focus;
    float power;
    int missedcp;
    int lastcolido;
    int point;
    boolean nofocus;
    boolean colidim;
    
    public int py(final int n, final int n2, final int n3, final int n4) {
        return (n - n2) * (n - n2) + (n3 - n4) * (n3 - n4);
    }
    
    public void regy(final int n, float n2, final ContO contO) {
        n2 *= this.dammult[this.cn];
        if (n2 > 100.0f) {
            this.rpd.recy(n, n2, this.mtouch, this.im);
            n2 -= 100.0f;
            int n3 = 0;
            int n4 = 0;
            int i = contO.zy;
            int j = contO.xy;
            while (i < 360) {
                i += 360;
            }
            while (i > 360) {
                i -= 360;
            }
            if (i < 210 && i > 150) {
                n3 = -1;
            }
            if (i > 330 || i < 30) {
                n3 = 1;
            }
            while (j < 360) {
                j += 360;
            }
            while (j > 360) {
                j -= 360;
            }
            if (j < 210 && j > 150) {
                n4 = -1;
            }
            if (j > 330 || j < 30) {
                n4 = 1;
            }
            if (this.im == 0 || this.colidim) {
                this.xt.crash(n2, n4 * n3);
            }
            if (n4 * n3 == 0 || this.mtouch) {
                for (int k = 0; k < contO.npl; ++k) {
                    float ctmag = 0.0f;
                    for (int l = 0; l < contO.p[k].n; ++l) {
                        if (contO.p[k].wz == 0 && this.py(contO.keyx[n], contO.p[k].ox[l], contO.keyz[n], contO.p[k].oz[l]) < this.clrad[this.cn]) {
                            ctmag = n2 / 20.0f * this.m.random();
                            final int[] oz = contO.p[k].oz;
                            final int n5 = l;
                            oz[n5] += (int)(ctmag * this.m.sin(i));
                            final int[] ox = contO.p[k].ox;
                            final int n6 = l;
                            ox[n6] -= (int)(ctmag * this.m.sin(j));
                            this.hitmag += (int)Math.abs(ctmag);
                        }
                    }
                    if (ctmag != 0.0f) {
                        if (Math.abs(ctmag) >= 1.0f) {
                            contO.p[k].chip = 1;
                            contO.p[k].ctmag = ctmag;
                        }
                        if (!contO.p[k].nocol && !contO.p[k].glass) {
                            if (contO.p[k].bfase > 20 && contO.p[k].hsb[1] > 0.2) {
                                contO.p[k].hsb[1] = 0.2f;
                            }
                            if (contO.p[k].bfase > 30) {
                                if (contO.p[k].hsb[2] < 0.5) {
                                    contO.p[k].hsb[2] = 0.5f;
                                }
                                if (contO.p[k].hsb[1] > 0.1) {
                                    contO.p[k].hsb[1] = 0.1f;
                                }
                            }
                            if (contO.p[k].bfase > 40) {
                                contO.p[k].hsb[1] = 0.05f;
                            }
                            if (contO.p[k].bfase > 50) {
                                if (contO.p[k].hsb[2] > 0.8) {
                                    contO.p[k].hsb[2] = 0.8f;
                                }
                                contO.p[k].hsb[0] = 0.075f;
                                contO.p[k].hsb[1] = 0.05f;
                            }
                            if (contO.p[k].bfase > 60) {
                                contO.p[k].hsb[0] = 0.05f;
                            }
                            final Plane plane = contO.p[k];
                            plane.bfase += (int)ctmag;
                            new Color(contO.p[k].c[0], contO.p[k].c[1], contO.p[k].c[2]);
                            final Color hsbColor = Color.getHSBColor(contO.p[k].hsb[0], contO.p[k].hsb[1], contO.p[k].hsb[2]);
                            contO.p[k].c[0] = hsbColor.getRed();
                            contO.p[k].c[1] = hsbColor.getGreen();
                            contO.p[k].c[2] = hsbColor.getBlue();
                        }
                        if (contO.p[k].glass) {
                            final Plane plane2 = contO.p[k];
                            plane2.gr -= (int)Math.abs(ctmag * 1.5);
                        }
                    }
                }
            }
            if (n4 * n3 == -1) {
                if (this.nbsq > 0) {
                    int n7 = 0;
                    int n8 = 1;
                    for (int n9 = 0; n9 < contO.npl; ++n9) {
                        float ctmag2 = 0.0f;
                        for (int n10 = 0; n10 < contO.p[n9].n; ++n10) {
                            if (contO.p[n9].wz == 0) {
                                ctmag2 = n2 / 15.0f * this.m.random();
                                if ((Math.abs(contO.p[n9].oy[n10] - this.flipy[this.cn] - this.squash) < this.msquash[this.cn] * 3 || contO.p[n9].oy[n10] < this.flipy[this.cn] + this.squash) && this.squash < this.msquash[this.cn]) {
                                    final int[] oy = contO.p[n9].oy;
                                    final int n11 = n10;
                                    oy[n11] += (int)ctmag2;
                                    n7 += (int)ctmag2;
                                    ++n8;
                                    this.hitmag += (int)Math.abs(ctmag2);
                                }
                            }
                        }
                        if (contO.p[n9].glass) {
                            final Plane plane3 = contO.p[n9];
                            plane3.gr -= 5;
                        }
                        else if (ctmag2 != 0.0f) {
                            final Plane plane4 = contO.p[n9];
                            plane4.bfase += (int)ctmag2;
                        }
                        if (Math.abs(ctmag2) >= 1.0f) {
                            contO.p[n9].chip = 1;
                            contO.p[n9].ctmag = ctmag2;
                        }
                    }
                    this.squash += n7 / n8;
                    this.nbsq = 0;
                }
                else {
                    ++this.nbsq;
                }
            }
        }
    }
    
    public Madness(final Medium m, final Record rpd, final xtGraphics xt, final int im) {
        this.cn = 0;
        this.im = 0;
        this.mxz = 0;
        this.cxz = 0;
        this.acelf = new float[][] { { 11.0f, 5.0f, 3.0f }, { 8.0f, 7.0f, 5.0f }, { 10.0f, 5.0f, 3.5f }, { 11.0f, 6.0f, 3.5f }, { 10.0f, 5.0f, 3.5f }, { 12.0f, 6.0f, 3.0f }, { 7.0f, 9.0f, 4.0f }, { 9.0f, 5.0f, 3.0f }, { 11.0f, 7.0f, 4.0f }, { 12.0f, 6.0f, 3.5f } };
        this.swits = new int[][] { { 50, 180, 280 }, { 50, 200, 310 }, { 60, 180, 275 }, { 70, 190, 295 }, { 70, 170, 275 }, { 60, 200, 290 }, { 60, 170, 280 }, { 50, 160, 270 }, { 80, 200, 300 }, { 70, 210, 290 } };
        this.handb = new int[] { 7, 10, 7, 15, 12, 8, 9, 10, 7, 7, 7 };
        this.airs = new float[] { 1.0f, 1.2f, 0.95f, 1.0f, 1.5f, 1.0f, 0.9f, 0.8f, 1.3f, 1.0f };
        this.airc = new int[] { 70, 30, 40, 40, 30, 50, 40, 10, 100, 60 };
        this.drag = new float[] { 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f };
        this.turn = new int[] { 6, 9, 5, 7, 6, 7, 5, 4, 7, 6 };
        this.grip = new float[] { 20.0f, 27.0f, 18.0f, 22.0f, 15.0f, 20.0f, 25.0f, 25.0f, 25.0f, 27.0f };
        this.bounce = new float[] { 1.2f, 1.05f, 1.3f, 1.15f, 1.3f, 1.2f, 1.15f, 0.8f, 1.1f, 1.15f };
        this.simag = new float[] { 0.9f, 0.85f, 1.05f, 0.9f, 0.85f, 0.9f, 1.05f, 1.1f, 0.9f, 1.15f };
        this.moment = new float[] { 1.2f, 0.75f, 1.4f, 1.0f, 0.85f, 1.25f, 1.4f, 2.0f, 1.5f, 2.0f };
        this.comprad = new float[] { 0.5f, 0.4f, 0.8f, 0.5f, 0.5f, 0.5f, 0.5f, 1.5f, 0.5f, 0.8f };
        this.push = new int[] { 2, 2, 3, 3, 2, 2, 2, 4, 2, 2 };
        this.revpush = new int[] { 2, 3, 2, 2, 2, 2, 2, 1, 2, 1 };
        this.lift = new int[] { 0, 30, 0, 20, 0, 30, 0, 0, 30, 0 };
        this.revlift = new int[] { 0, 0, 15, 0, 0, 0, 0, 0, 0, 32 };
        this.powerloss = new int[] { 2500000, 2500000, 3500000, 2500000, 2500000, 2500000, 3200000, 4500000, 3000000, 5500000 };
        this.flipy = new int[] { -50, -26, -90, -41, -55, -53, -54, -85, -60, -127 };
        this.msquash = new int[] { 7, 3, 7, 2, 3, 3, 6, 10, 3, 8 };
        this.clrad = new int[] { 3300, 1500, 4700, 3000, 1700, 2100, 3500, 7000, 4000, 4000 };
        this.maxmag = new int[] { 3500, 1700, 7500, 5000, 3000, 4100, 6000, 9000, 4400, 9500 };
        this.dammult = new float[] { 1.0f, 2.028f, 0.9375f, 1.1791f, 1.0f, 0.9066f, 1.0f, 0.6969f, 0.8266f, 0.7667f };
        this.outdam = new int[] { 77, 35, 80, 67, 55, 75, 81, 100, 75, 90 };
        this.dominate = new boolean[5];
        this.caught = new boolean[5];
        this.pzy = 0;
        this.pxy = 0;
        this.speed = 0.0f;
        this.forca = 0.0f;
        this.scy = new float[4];
        this.scz = new float[4];
        this.scx = new float[4];
        this.mtouch = false;
        this.wtouch = false;
        this.cntouch = 0;
        this.capsized = false;
        this.txz = 0;
        this.fxz = 0;
        this.pmlt = 1;
        this.nmlt = 1;
        this.dcnt = 0;
        this.skid = 0;
        this.pushed = false;
        this.gtouch = false;
        this.pl = false;
        this.pr = false;
        this.pd = false;
        this.pu = false;
        this.loop = 0;
        this.ucomp = 0.0f;
        this.dcomp = 0.0f;
        this.lcomp = 0.0f;
        this.rcomp = 0.0f;
        this.lxz = 0;
        this.travxy = 0;
        this.travzy = 0;
        this.travxz = 0;
        this.trcnt = 0;
        this.capcnt = 0;
        this.srfcnt = 0;
        this.rtab = false;
        this.ftab = false;
        this.btab = false;
        this.surfer = false;
        this.powerup = 0.0f;
        this.xtpower = 0;
        this.tilt = 0.0f;
        this.squash = 0;
        this.nbsq = 0;
        this.hitmag = 0;
        this.cntdest = 0;
        this.dest = false;
        this.newcar = false;
        this.pan = 0;
        this.pcleared = 0;
        this.clear = 0;
        this.nlaps = 0;
        this.focus = -1;
        this.power = 75.0f;
        this.missedcp = 0;
        this.lastcolido = 0;
        this.point = 0;
        this.nofocus = false;
        this.colidim = false;
        this.m = m;
        this.rpd = rpd;
        this.xt = xt;
        this.im = im;
    }
    
    public int rpy(final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        return (int)((n - n2) * (n - n2) + (n3 - n4) * (n3 - n4) + (n5 - n6) * (n5 - n6));
    }
    
    public void regz(final int n, float n2, final ContO contO) {
        n2 *= this.dammult[this.cn];
        if (Math.abs(n2) > 100.0f) {
            this.rpd.recz(n, n2, this.im);
            if (n2 > 100.0f) {
                n2 -= 100.0f;
            }
            if (n2 < -100.0f) {
                n2 += 100.0f;
            }
            if (this.im == 0 || this.colidim) {
                this.xt.crash(n2, 0);
            }
            for (int i = 0; i < contO.npl; ++i) {
                float ctmag = 0.0f;
                for (int j = 0; j < contO.p[i].n; ++j) {
                    if (contO.p[i].wz == 0 && this.py(contO.keyx[n], contO.p[i].ox[j], contO.keyz[n], contO.p[i].oz[j]) < this.clrad[this.cn]) {
                        ctmag = n2 / 20.0f * this.m.random();
                        final int[] oz = contO.p[i].oz;
                        final int n3 = j;
                        oz[n3] += (int)(ctmag * this.m.cos(contO.xz) * this.m.cos(contO.zy));
                        final int[] ox = contO.p[i].ox;
                        final int n4 = j;
                        ox[n4] += (int)(ctmag * this.m.sin(contO.xz) * this.m.cos(contO.xy));
                        this.hitmag += (int)Math.abs(ctmag);
                    }
                }
                if (ctmag != 0.0f) {
                    if (Math.abs(ctmag) >= 1.0f) {
                        contO.p[i].chip = 1;
                        contO.p[i].ctmag = ctmag;
                    }
                    if (!contO.p[i].nocol && !contO.p[i].glass) {
                        if (contO.p[i].bfase > 20 && contO.p[i].hsb[1] > 0.2) {
                            contO.p[i].hsb[1] = 0.2f;
                        }
                        if (contO.p[i].bfase > 30) {
                            if (contO.p[i].hsb[2] < 0.5) {
                                contO.p[i].hsb[2] = 0.5f;
                            }
                            if (contO.p[i].hsb[1] > 0.1) {
                                contO.p[i].hsb[1] = 0.1f;
                            }
                        }
                        if (contO.p[i].bfase > 40) {
                            contO.p[i].hsb[1] = 0.05f;
                        }
                        if (contO.p[i].bfase > 50) {
                            if (contO.p[i].hsb[2] > 0.8) {
                                contO.p[i].hsb[2] = 0.8f;
                            }
                            contO.p[i].hsb[0] = 0.075f;
                            contO.p[i].hsb[1] = 0.05f;
                        }
                        if (contO.p[i].bfase > 60) {
                            contO.p[i].hsb[0] = 0.05f;
                        }
                        final Plane plane = contO.p[i];
                        plane.bfase += (int)Math.abs(ctmag);
                        new Color(contO.p[i].c[0], contO.p[i].c[1], contO.p[i].c[2]);
                        final Color hsbColor = Color.getHSBColor(contO.p[i].hsb[0], contO.p[i].hsb[1], contO.p[i].hsb[2]);
                        contO.p[i].c[0] = hsbColor.getRed();
                        contO.p[i].c[1] = hsbColor.getGreen();
                        contO.p[i].c[2] = hsbColor.getBlue();
                    }
                    if (contO.p[i].glass) {
                        final Plane plane2 = contO.p[i];
                        plane2.gr -= (int)Math.abs(ctmag * 1.5);
                    }
                }
            }
        }
    }
    
    public void rot(final float[] array, final float[] array2, final int n, final int n2, final int n3, final int n4) {
        if (n3 != 0) {
            for (int i = 0; i < n4; ++i) {
                final float n5 = array[i];
                final float n6 = array2[i];
                array[i] = n + ((n5 - n) * this.m.cos(n3) - (n6 - n2) * this.m.sin(n3));
                array2[i] = n2 + ((n5 - n) * this.m.sin(n3) + (n6 - n2) * this.m.cos(n3));
            }
        }
    }
    
    public void colide(final ContO contO, final Madness madness, final ContO contO2) {
        final float[] array = new float[4];
        final float[] array2 = new float[4];
        final float[] array3 = new float[4];
        final float[] array4 = new float[4];
        final float[] array5 = new float[4];
        final float[] array6 = new float[4];
        int n = 0;
        do {
            array[n] = contO.x + contO.keyx[n];
            if (this.capsized) {
                array2[n] = contO.y + this.flipy[this.cn] + this.squash;
            }
            else {
                array2[n] = contO.y + contO.grat;
            }
            array3[n] = contO.z + contO.keyz[n];
            array4[n] = contO2.x + contO2.keyx[n];
            if (this.capsized) {
                array5[n] = contO2.y + madness.flipy[madness.cn] + madness.squash;
            }
            else {
                array5[n] = contO2.y + contO2.grat;
            }
            array6[n] = contO2.z + contO2.keyz[n];
        } while (++n < 4);
        this.rot(array, array2, contO.x, contO.y, contO.xy, 4);
        this.rot(array2, array3, contO.y, contO.z, contO.zy, 4);
        this.rot(array, array3, contO.x, contO.z, contO.xz, 4);
        this.rot(array4, array5, contO2.x, contO2.y, contO2.xy, 4);
        this.rot(array5, array6, contO2.y, contO2.z, contO2.zy, 4);
        this.rot(array4, array6, contO2.x, contO2.z, contO2.xz, 4);
        if (this.rpy(contO.x, contO2.x, contO.y, contO2.y, contO.z, contO2.z) < (contO.maxR * contO.maxR + contO2.maxR * contO2.maxR) * 1.5) {
            if (!this.caught[madness.im] && (this.speed != 0.0f || madness.speed != 0.0f)) {
                if (Math.abs(this.power * this.speed * this.moment[this.cn]) != Math.abs(madness.power * madness.speed * madness.moment[madness.cn])) {
                    if (Math.abs(this.power * this.speed * this.moment[this.cn]) > Math.abs(madness.power * madness.speed * madness.moment[madness.cn])) {
                        this.dominate[madness.im] = true;
                    }
                    else {
                        this.dominate[madness.im] = false;
                    }
                }
                else if (this.moment[this.cn] > madness.moment[madness.cn]) {
                    this.dominate[madness.im] = true;
                }
                else {
                    this.dominate[madness.im] = false;
                }
                this.caught[madness.im] = true;
            }
        }
        else if (this.caught[madness.im]) {
            this.caught[madness.im] = false;
        }
        if (this.dominate[madness.im]) {
            final int n2 = (int)(((this.scz[0] - madness.scz[0] + this.scz[1] - madness.scz[1] + this.scz[2] - madness.scz[2] + this.scz[3] - madness.scz[3]) * (this.scz[0] - madness.scz[0] + this.scz[1] - madness.scz[1] + this.scz[2] - madness.scz[2] + this.scz[3] - madness.scz[3]) + (this.scx[0] - madness.scx[0] + this.scx[1] - madness.scx[1] + this.scx[2] - madness.scx[2] + this.scx[3] - madness.scx[3]) * (this.scx[0] - madness.scx[0] + this.scx[1] - madness.scx[1] + this.scx[2] - madness.scx[2] + this.scx[3] - madness.scx[3])) / 16.0f);
            int n3 = 0;
            do {
                int n4 = 0;
                do {
                    if (this.rpy(array[n3], array4[n4], array2[n3], array5[n4], array3[n3], array6[n4]) < (n2 + 7000) * (this.comprad[madness.cn] + this.comprad[this.cn])) {
                        if (Math.abs(this.scx[n3] * this.moment[this.cn]) > Math.abs(madness.scx[n4] * madness.moment[madness.cn])) {
                            float n5 = madness.scx[n4] * this.revpush[this.cn];
                            if (n5 > 300.0f) {
                                n5 = 300.0f;
                            }
                            if (n5 < -300.0f) {
                                n5 = -300.0f;
                            }
                            float n6 = this.scx[n3] * this.push[this.cn];
                            if (n6 > 300.0f) {
                                n6 = 300.0f;
                            }
                            if (n6 < -300.0f) {
                                n6 = -300.0f;
                            }
                            final float[] scx = madness.scx;
                            final int n7 = n4;
                            scx[n7] += n6;
                            if (this.im == 0) {
                                madness.colidim = true;
                            }
                            madness.regx(n4, n6 * this.moment[this.cn], contO2);
                            if (madness.colidim) {
                                madness.colidim = false;
                            }
                            final float[] scx2 = this.scx;
                            final int n8 = n3;
                            scx2[n8] -= n5;
                            this.regx(n3, -n5 * madness.moment[this.cn], contO);
                            final float[] scy = this.scy;
                            final int n9 = n3;
                            scy[n9] -= this.revlift[this.cn];
                            if (this.im == 0) {
                                madness.colidim = true;
                            }
                            madness.regy(n4, this.revlift[this.cn] * 7, contO2);
                            if (madness.colidim) {
                                madness.colidim = false;
                            }
                        }
                        if (Math.abs(this.scz[n3] * this.moment[this.cn]) > Math.abs(madness.scz[n4] * madness.moment[madness.cn])) {
                            float n10 = madness.scz[n4] * this.revpush[this.cn];
                            if (n10 > 300.0f) {
                                n10 = 300.0f;
                            }
                            if (n10 < -300.0f) {
                                n10 = -300.0f;
                            }
                            float n11 = this.scz[n3] * this.push[this.cn];
                            if (n11 > 300.0f) {
                                n11 = 300.0f;
                            }
                            if (n11 < -300.0f) {
                                n11 = -300.0f;
                            }
                            final float[] scz = madness.scz;
                            final int n12 = n4;
                            scz[n12] += n11;
                            if (this.im == 0) {
                                madness.colidim = true;
                            }
                            madness.regz(n4, n11 * this.moment[this.cn], contO2);
                            if (madness.colidim) {
                                madness.colidim = false;
                            }
                            final float[] scz2 = this.scz;
                            final int n13 = n3;
                            scz2[n13] -= n10;
                            this.regz(n3, -n10 * madness.moment[this.cn], contO);
                            final float[] scy2 = this.scy;
                            final int n14 = n3;
                            scy2[n14] -= this.revlift[this.cn];
                            if (this.im == 0) {
                                madness.colidim = true;
                            }
                            madness.regy(n4, this.revlift[this.cn] * 7, contO2);
                            if (madness.colidim) {
                                madness.colidim = false;
                            }
                        }
                        if (this.im == 0) {
                            madness.lastcolido = 70;
                        }
                        if (madness.im == 0) {
                            this.lastcolido = 70;
                        }
                        final float[] scy3 = madness.scy;
                        final int n15 = n4;
                        scy3[n15] -= this.lift[this.cn];
                    }
                } while (++n4 < 4);
            } while (++n3 < 4);
        }
    }
    
    public void distruct(final ContO contO) {
        for (int i = 0; i < contO.npl; ++i) {
            if (contO.p[i].wz == 0) {
                contO.p[i].embos = 1;
            }
        }
    }
    
    public void reseto(final int cn, final ContO contO, final CheckPoints checkPoints) {
        this.cn = cn;
        int n = 0;
        do {
            this.dominate[n] = false;
            this.caught[n] = false;
        } while (++n < 5);
        if (this.cn == 7 && this.im == 0) {
            if (checkPoints.stage == 10) {
                this.moment[this.cn] = 1.7f;
            }
            else {
                this.moment[this.cn] = 2.0f;
            }
        }
        this.mxz = 0;
        this.cxz = 0;
        this.pzy = 0;
        this.pxy = 0;
        this.speed = 0.0f;
        int n2 = 0;
        do {
            this.scy[n2] = 0.0f;
            this.scx[n2] = 0.0f;
            this.scz[n2] = 0.0f;
        } while (++n2 < 4);
        this.forca = ((float)Math.sqrt(contO.keyz[0] * contO.keyz[0] + contO.keyx[0] * contO.keyx[0]) + (float)Math.sqrt(contO.keyz[1] * contO.keyz[1] + contO.keyx[1] * contO.keyx[1]) + (float)Math.sqrt(contO.keyz[2] * contO.keyz[2] + contO.keyx[2] * contO.keyx[2]) + (float)Math.sqrt(contO.keyz[3] * contO.keyz[3] + contO.keyx[3] * contO.keyx[3])) / 10000.0f * (float)(this.bounce[this.cn] - 0.3);
        this.mtouch = false;
        this.wtouch = false;
        this.txz = 0;
        this.fxz = 0;
        this.pmlt = 1;
        this.nmlt = 1;
        this.dcnt = 0;
        this.skid = 0;
        this.pushed = false;
        this.gtouch = false;
        this.pl = false;
        this.pr = false;
        this.pd = false;
        this.pu = false;
        this.loop = 0;
        this.ucomp = 0.0f;
        this.dcomp = 0.0f;
        this.lcomp = 0.0f;
        this.rcomp = 0.0f;
        this.lxz = 0;
        this.travxy = 0;
        this.travzy = 0;
        this.travxz = 0;
        this.rtab = false;
        this.ftab = false;
        this.btab = false;
        this.powerup = 0.0f;
        this.xtpower = 0;
        this.trcnt = 0;
        this.capcnt = 0;
        this.tilt = 0.0f;
        this.pan = 0;
        this.pcleared = checkPoints.pcs;
        this.clear = 0;
        this.nlaps = 0;
        this.focus = -1;
        this.missedcp = 0;
        this.nofocus = false;
        this.power = 98.0f;
        this.lastcolido = 0;
        checkPoints.dested[this.im] = 0;
        this.squash = 0;
        this.nbsq = 0;
        this.hitmag = 0;
        this.cntdest = 0;
        this.dest = false;
        this.newcar = false;
    }
    
    public void regx(final int n, float n2, final ContO contO) {
        n2 *= this.dammult[this.cn];
        if (Math.abs(n2) > 100.0f) {
            this.rpd.recx(n, n2, this.im);
            if (n2 > 100.0f) {
                n2 -= 100.0f;
            }
            if (n2 < -100.0f) {
                n2 += 100.0f;
            }
            if (this.im == 0 || this.colidim) {
                this.xt.crash(n2, 0);
            }
            for (int i = 0; i < contO.npl; ++i) {
                float ctmag = 0.0f;
                for (int j = 0; j < contO.p[i].n; ++j) {
                    if (contO.p[i].wz == 0 && this.py(contO.keyx[n], contO.p[i].ox[j], contO.keyz[n], contO.p[i].oz[j]) < this.clrad[this.cn]) {
                        ctmag = n2 / 20.0f * this.m.random();
                        final int[] oz = contO.p[i].oz;
                        final int n3 = j;
                        oz[n3] -= (int)(ctmag * this.m.sin(contO.xz) * this.m.cos(contO.zy));
                        final int[] ox = contO.p[i].ox;
                        final int n4 = j;
                        ox[n4] += (int)(ctmag * this.m.cos(contO.xz) * this.m.cos(contO.xy));
                        this.hitmag += (int)Math.abs(ctmag);
                    }
                }
                if (ctmag != 0.0f) {
                    if (Math.abs(ctmag) >= 1.0f) {
                        contO.p[i].chip = 1;
                        contO.p[i].ctmag = ctmag;
                    }
                    if (!contO.p[i].nocol && !contO.p[i].glass) {
                        if (contO.p[i].bfase > 20 && contO.p[i].hsb[1] > 0.2) {
                            contO.p[i].hsb[1] = 0.2f;
                        }
                        if (contO.p[i].bfase > 30) {
                            if (contO.p[i].hsb[2] < 0.5) {
                                contO.p[i].hsb[2] = 0.5f;
                            }
                            if (contO.p[i].hsb[1] > 0.1) {
                                contO.p[i].hsb[1] = 0.1f;
                            }
                        }
                        if (contO.p[i].bfase > 40) {
                            contO.p[i].hsb[1] = 0.05f;
                        }
                        if (contO.p[i].bfase > 50) {
                            if (contO.p[i].hsb[2] > 0.8) {
                                contO.p[i].hsb[2] = 0.8f;
                            }
                            contO.p[i].hsb[0] = 0.075f;
                            contO.p[i].hsb[1] = 0.05f;
                        }
                        if (contO.p[i].bfase > 60) {
                            contO.p[i].hsb[0] = 0.05f;
                        }
                        final Plane plane = contO.p[i];
                        plane.bfase += (int)Math.abs(ctmag);
                        new Color(contO.p[i].c[0], contO.p[i].c[1], contO.p[i].c[2]);
                        final Color hsbColor = Color.getHSBColor(contO.p[i].hsb[0], contO.p[i].hsb[1], contO.p[i].hsb[2]);
                        contO.p[i].c[0] = hsbColor.getRed();
                        contO.p[i].c[1] = hsbColor.getGreen();
                        contO.p[i].c[2] = hsbColor.getBlue();
                    }
                    if (contO.p[i].glass) {
                        final Plane plane2 = contO.p[i];
                        plane2.gr -= (int)Math.abs(ctmag * 1.5);
                    }
                }
            }
        }
    }
    
    public void drive(final Control control, final ContO contO, final Trackers trackers, final CheckPoints checkPoints) {
        int n = 1;
        int n2 = 1;
        boolean zyinv = false;
        boolean b = false;
        boolean b2 = false;
        this.capsized = false;
        int i;
        for (i = Math.abs(this.pzy); i > 270; i -= 360) {}
        if (Math.abs(i) > 90) {
            zyinv = true;
        }
        int n3 = 0;
        int j;
        for (j = Math.abs(this.pxy); j > 270; j -= 360) {}
        if (Math.abs(j) > 90) {
            n3 = 1;
            n2 = -1;
        }
        int grat = contO.grat;
        if (zyinv) {
            if (n3 != 0) {
                n3 = 0;
                b = true;
            }
            else {
                n3 = 1;
                this.capsized = true;
            }
            n = -1;
        }
        else if (n3 != 0) {
            this.capsized = true;
        }
        if (this.capsized) {
            grat = this.flipy[this.cn] + this.squash;
        }
        control.zyinv = zyinv;
        float n4 = 0.0f;
        float n5 = 0.0f;
        float n6 = 0.0f;
        if (this.mtouch) {
            this.loop = 0;
        }
        if (this.wtouch) {
            if (this.loop == 2 || this.loop == -1) {
                this.loop = -1;
                if (control.left) {
                    this.pl = true;
                }
                if (control.right) {
                    this.pr = true;
                }
                if (control.up) {
                    this.pu = true;
                }
                if (control.down) {
                    this.pd = true;
                }
            }
            this.ucomp = 0.0f;
            this.dcomp = 0.0f;
            this.lcomp = 0.0f;
            this.rcomp = 0.0f;
        }
        if (control.handb) {
            if (!this.pushed) {
                if (!this.wtouch) {
                    if (this.loop == 0) {
                        this.loop = 1;
                    }
                }
                else if (this.gtouch) {
                    this.pushed = true;
                }
            }
        }
        else {
            this.pushed = false;
        }
        if (this.loop == 1) {
            final float n7 = (this.scy[0] + this.scy[1] + this.scy[2] + this.scy[3]) / 4.0f;
            int n8 = 0;
            do {
                this.scy[n8] = n7;
            } while (++n8 < 4);
            this.loop = 2;
        }
        if (!this.dest) {
            if (this.loop == 2) {
                if (control.up) {
                    if (this.ucomp == 0.0f) {
                        this.ucomp = 10.0f + (this.scy[0] + 50.0f) / 20.0f;
                        if (this.ucomp < 5.0f) {
                            this.ucomp = 5.0f;
                        }
                        if (this.ucomp > 10.0f) {
                            this.ucomp = 10.0f;
                        }
                        this.ucomp *= this.airs[this.cn];
                    }
                    if (this.ucomp < 20.0f) {
                        this.ucomp += (float)(0.5 * this.airs[this.cn]);
                    }
                    n4 = -this.airc[this.cn] * this.m.sin(contO.xz) * n2;
                    n5 = this.airc[this.cn] * this.m.cos(contO.xz) * n2;
                }
                else if (this.ucomp != 0.0f && this.ucomp > -2.0f) {
                    this.ucomp -= (float)(0.5 * this.airs[this.cn]);
                }
                if (control.down) {
                    if (this.dcomp == 0.0f) {
                        this.dcomp = 10.0f + (this.scy[0] + 50.0f) / 20.0f;
                        if (this.dcomp < 5.0f) {
                            this.dcomp = 5.0f;
                        }
                        if (this.dcomp > 10.0f) {
                            this.dcomp = 10.0f;
                        }
                        this.dcomp *= this.airs[this.cn];
                    }
                    if (this.dcomp < 20.0f) {
                        this.dcomp += (float)(0.5 * this.airs[this.cn]);
                    }
                    n6 = -this.airc[this.cn];
                }
                else if (this.dcomp != 0.0f && this.ucomp > -2.0f) {
                    this.dcomp -= (float)(0.5 * this.airs[this.cn]);
                }
                if (control.left) {
                    if (this.lcomp == 0.0f) {
                        this.lcomp = 5.0f;
                    }
                    if (this.lcomp < 20.0f) {
                        this.lcomp += 2.0f * this.airs[this.cn];
                    }
                    n4 = -this.airc[this.cn] * this.m.cos(contO.xz) * n;
                    n5 = -this.airc[this.cn] * this.m.sin(contO.xz) * n;
                }
                else if (this.lcomp > 0.0f) {
                    this.lcomp -= 2.0f * this.airs[this.cn];
                }
                if (control.right) {
                    if (this.rcomp == 0.0f) {
                        this.rcomp = 5.0f;
                    }
                    if (this.rcomp < 20.0f) {
                        this.rcomp += 2.0f * this.airs[this.cn];
                    }
                    n4 = this.airc[this.cn] * this.m.cos(contO.xz) * n;
                    n5 = this.airc[this.cn] * this.m.sin(contO.xz) * n;
                }
                else if (this.rcomp > 0.0f) {
                    this.rcomp -= 2.0f * this.airs[this.cn];
                }
                this.pzy += (int)((this.dcomp - this.ucomp) * this.m.cos(this.pxy));
                if (zyinv) {
                    contO.xz += (int)((this.dcomp - this.ucomp) * this.m.sin(this.pxy));
                }
                else {
                    contO.xz -= (int)((this.dcomp - this.ucomp) * this.m.sin(this.pxy));
                }
                this.pxy += (int)(this.rcomp - this.lcomp);
            }
            else {
                float power = this.power;
                if (power < 40.0f) {
                    power = 40.0f;
                }
                if (this.im == 0 && this.power != 98.0f) {
                    if (checkPoints.stage != 6 && checkPoints.stage != 8) {
                        power *= 0.76;
                    }
                    else if (checkPoints.stage != 6) {
                        power *= 0.9;
                    }
                }
                if (control.down) {
                    if (this.speed > 0.0f) {
                        this.speed -= this.handb[this.cn] / 2;
                    }
                    else {
                        int n9 = 0;
                        int n10 = 0;
                        do {
                            if (this.speed <= -(this.swits[this.cn][n10] / 2 + power * this.swits[this.cn][n10] / 196.0f)) {
                                ++n9;
                            }
                        } while (++n10 < 2);
                        if (n9 != 2) {
                            this.speed -= this.acelf[this.cn][n9] / 2.0f + power * this.acelf[this.cn][n9] / 196.0f;
                        }
                        else {
                            this.speed = -(this.swits[this.cn][1] / 2 + power * this.swits[this.cn][1] / 196.0f);
                        }
                    }
                }
                if (control.up) {
                    if (this.speed < 0.0f) {
                        this.speed += this.handb[this.cn];
                    }
                    else {
                        int n11 = 0;
                        int n12 = 0;
                        do {
                            if (this.speed >= this.swits[this.cn][n12] / 2 + power * this.swits[this.cn][n12] / 196.0f) {
                                ++n11;
                            }
                        } while (++n12 < 3);
                        if (n11 != 3) {
                            this.speed += this.acelf[this.cn][n11] / 2.0f + power * this.acelf[this.cn][n11] / 196.0f;
                        }
                        else {
                            this.speed = this.swits[this.cn][2] / 2 + power * this.swits[this.cn][2] / 196.0f;
                        }
                    }
                }
                if (control.handb && Math.abs(this.speed) > this.handb[this.cn]) {
                    if (this.speed < 0.0f) {
                        this.speed += this.handb[this.cn];
                    }
                    else {
                        this.speed -= this.handb[this.cn];
                    }
                }
                if (this.loop == -1 && contO.y < 100) {
                    if (control.left) {
                        if (!this.pl) {
                            if (this.lcomp == 0.0f) {
                                this.lcomp = 5.0f * this.airs[this.cn];
                            }
                            if (this.lcomp < 20.0f) {
                                this.lcomp += 2.0f * this.airs[this.cn];
                            }
                        }
                    }
                    else {
                        if (this.lcomp > 0.0f) {
                            this.lcomp -= 2.0f * this.airs[this.cn];
                        }
                        this.pl = false;
                    }
                    if (control.right) {
                        if (!this.pr) {
                            if (this.rcomp == 0.0f) {
                                this.rcomp = 5.0f * this.airs[this.cn];
                            }
                            if (this.rcomp < 20.0f) {
                                this.rcomp += 2.0f * this.airs[this.cn];
                            }
                        }
                    }
                    else {
                        if (this.rcomp > 0.0f) {
                            this.rcomp -= 2.0f * this.airs[this.cn];
                        }
                        this.pr = false;
                    }
                    if (control.up) {
                        if (!this.pu) {
                            if (this.ucomp == 0.0f) {
                                this.ucomp = 5.0f * this.airs[this.cn];
                            }
                            if (this.ucomp < 20.0f) {
                                this.ucomp += 2.0f * this.airs[this.cn];
                            }
                        }
                    }
                    else {
                        if (this.ucomp > 0.0f) {
                            this.ucomp -= 2.0f * this.airs[this.cn];
                        }
                        this.pu = false;
                    }
                    if (control.down) {
                        if (!this.pd) {
                            if (this.dcomp == 0.0f) {
                                this.dcomp = 5.0f * this.airs[this.cn];
                            }
                            if (this.dcomp < 20.0f) {
                                this.dcomp += 2.0f * this.airs[this.cn];
                            }
                        }
                    }
                    else {
                        if (this.dcomp > 0.0f) {
                            this.dcomp -= 2.0f * this.airs[this.cn];
                        }
                        this.pd = false;
                    }
                    this.pzy += (int)((this.dcomp - this.ucomp) * this.m.cos(this.pxy));
                    if (zyinv) {
                        contO.xz += (int)((this.dcomp - this.ucomp) * this.m.sin(this.pxy));
                    }
                    else {
                        contO.xz -= (int)((this.dcomp - this.ucomp) * this.m.sin(this.pxy));
                    }
                    this.pxy += (int)(this.rcomp - this.lcomp);
                }
            }
        }
        float n13 = 20.0f * this.speed / (154.0f * this.simag[this.cn]);
        if (n13 > 20.0f) {
            n13 = 20.0f;
        }
        contO.wzy -= (int)n13;
        if (contO.wzy < -45) {
            contO.wzy += 45;
        }
        if (contO.wzy > 45) {
            contO.wzy -= 45;
        }
        if (control.right) {
            contO.wxz -= this.turn[this.cn];
            if (contO.wxz < -36) {
                contO.wxz = -36;
            }
        }
        if (control.left) {
            contO.wxz += this.turn[this.cn];
            if (contO.wxz > 36) {
                contO.wxz = 36;
            }
        }
        if (contO.wxz != 0 && !control.left && !control.right) {
            if (Math.abs(this.speed) < 10.0f) {
                if (Math.abs(contO.wxz) == 1) {
                    contO.wxz = 0;
                }
                if (contO.wxz > 0) {
                    --contO.wxz;
                }
                if (contO.wxz < 0) {
                    ++contO.wxz;
                }
            }
            else {
                if (Math.abs(contO.wxz) < this.turn[this.cn] * 2) {
                    contO.wxz = 0;
                }
                if (contO.wxz > 0) {
                    contO.wxz -= this.turn[this.cn] * 2;
                }
                if (contO.wxz < 0) {
                    contO.wxz += this.turn[this.cn] * 2;
                }
            }
        }
        int n14 = (int)(3600.0f / (this.speed * this.speed));
        if (n14 < 5) {
            n14 = 5;
        }
        if (this.speed < 0.0f) {
            n14 = -n14;
        }
        if (this.wtouch) {
            if (!this.capsized) {
                if (!control.handb) {
                    this.fxz = contO.wxz / (n14 * 3);
                }
                else {
                    this.fxz = contO.wxz / n14;
                }
                contO.xz += contO.wxz / n14;
            }
            this.wtouch = false;
            this.gtouch = false;
        }
        else {
            contO.xz += this.fxz;
        }
        if (this.speed > 30.0f || this.speed < -100.0f) {
            while (Math.abs(this.mxz - this.cxz) > 180) {
                if (this.cxz > this.mxz) {
                    this.cxz -= 360;
                }
                else {
                    if (this.cxz >= this.mxz) {
                        continue;
                    }
                    this.cxz += 360;
                }
            }
            if (Math.abs(this.mxz - this.cxz) < 30) {
                this.cxz += (int)((this.mxz - this.cxz) / 4.0f);
            }
            else {
                if (this.cxz > this.mxz) {
                    this.cxz -= 10;
                }
                if (this.cxz < this.mxz) {
                    this.cxz += 10;
                }
            }
        }
        final float[] array = new float[4];
        final float[] array2 = new float[4];
        final float[] array3 = new float[4];
        int n15 = 0;
        do {
            array[n15] = contO.keyx[n15] + contO.x;
            array3[n15] = grat + contO.y;
            array2[n15] = contO.z + contO.keyz[n15];
            final float[] scy = this.scy;
            final int n16 = n15;
            scy[n16] += 7.0f;
        } while (++n15 < 4);
        this.rot(array, array3, contO.x, contO.y, this.pxy, 4);
        this.rot(array3, array2, contO.y, contO.z, this.pzy, 4);
        this.rot(array, array2, contO.x, contO.z, contO.xz, 4);
        boolean b3 = false;
        final int n17 = (int)((this.scx[0] + this.scx[1] + this.scx[2] + this.scx[3]) / 4.0f);
        final int n18 = (int)((this.scz[0] + this.scz[1] + this.scz[2] + this.scz[3]) / 4.0f);
        int n19 = 0;
        do {
            if (this.scx[n19] - n17 > 200.0f) {
                this.scx[n19] = 200 + n17;
            }
            if (this.scx[n19] - n17 < -200.0f) {
                this.scx[n19] = n17 - 200;
            }
            if (this.scz[n19] - n18 > 200.0f) {
                this.scz[n19] = 200 + n18;
            }
            if (this.scz[n19] - n18 < -200.0f) {
                this.scz[n19] = n18 - 200;
            }
        } while (++n19 < 4);
        int n20 = 0;
        do {
            final float[] array4 = array3;
            final int n21 = n20;
            array4[n21] += this.scy[n20];
            final float[] array5 = array;
            final int n22 = n20;
            array5[n22] += (this.scx[0] + this.scx[1] + this.scx[2] + this.scx[3]) / 4.0f;
            final float[] array6 = array2;
            final int n23 = n20;
            array6[n23] += (this.scz[0] + this.scz[1] + this.scz[2] + this.scz[3]) / 4.0f;
        } while (++n20 < 4);
        int n24 = 1;
        for (int k = 0; k < trackers.nt; ++k) {
            if (Math.abs(trackers.zy[k]) != 90 && Math.abs(trackers.xy[k]) != 90 && Math.abs(contO.x - trackers.x[k]) < trackers.radx[k] && Math.abs(contO.z - trackers.z[k]) < trackers.radz[k]) {
                n24 = trackers.skd[k];
            }
        }
        if (this.mtouch) {
            float n25 = this.grip[this.cn] - Math.abs(this.txz - contO.xz) * this.speed / 250.0f;
            if (control.handb) {
                n25 -= Math.abs(this.txz - contO.xz) * 4;
            }
            if (n25 < this.grip[this.cn]) {
                if (this.skid != 2) {
                    this.skid = 1;
                }
                this.speed -= this.speed / 100.0f;
            }
            else if (this.skid == 1) {
                this.skid = 2;
            }
            if (n24 == 1) {
                n25 *= 0.75;
            }
            if (n24 == 2) {
                n25 *= 0.55;
            }
            int n26 = -(int)(this.speed * this.m.sin(contO.xz) * this.m.cos(this.pzy));
            int n27 = (int)(this.speed * this.m.cos(contO.xz) * this.m.cos(this.pzy));
            int n28 = -(int)(this.speed * this.m.sin(this.pzy));
            if (this.capsized || this.dest || checkPoints.haltall) {
                n26 = 0;
                n27 = 0;
                n28 = 0;
                n25 = this.grip[this.cn] / 5.0f;
                if (this.speed > 0.0f) {
                    this.speed -= 2.0f;
                }
                else {
                    this.speed += 2.0f;
                }
            }
            if (Math.abs(this.speed) > this.drag[this.cn]) {
                if (this.speed > 0.0f) {
                    this.speed -= this.drag[this.cn];
                }
                else {
                    this.speed += this.drag[this.cn];
                }
            }
            else {
                this.speed = 0.0f;
            }
            if (n25 < 1.0f) {
                n25 = 1.0f;
            }
            float n29 = 0.0f;
            float n30 = 0.0f;
            int n31 = 0;
            do {
                if (Math.abs(this.scx[n31] - n26) > n25) {
                    if (this.scx[n31] < n26) {
                        final float[] scx = this.scx;
                        final int n32 = n31;
                        scx[n32] += n25;
                    }
                    else {
                        final float[] scx2 = this.scx;
                        final int n33 = n31;
                        scx2[n33] -= n25;
                    }
                }
                else {
                    this.scx[n31] = n26;
                }
                if (Math.abs(this.scz[n31] - n27) > n25) {
                    if (this.scz[n31] < n27) {
                        final float[] scz = this.scz;
                        final int n34 = n31;
                        scz[n34] += n25;
                    }
                    else {
                        final float[] scz2 = this.scz;
                        final int n35 = n31;
                        scz2[n35] -= n25;
                    }
                }
                else {
                    this.scz[n31] = n27;
                }
                if (Math.abs(this.scy[n31] - n28) > n25) {
                    if (this.scy[n31] < n28) {
                        final float[] scy2 = this.scy;
                        final int n36 = n31;
                        scy2[n36] += n25;
                    }
                    else {
                        final float[] scy3 = this.scy;
                        final int n37 = n31;
                        scy3[n37] -= n25;
                    }
                }
                else {
                    this.scy[n31] = n28;
                }
                if (n25 < this.grip[this.cn]) {
                    if (this.txz != contO.xz) {
                        ++this.dcnt;
                    }
                    else if (this.dcnt != 0) {
                        this.dcnt = 0;
                    }
                    if (this.dcnt > 40.0f * n25 / this.grip[this.cn] || this.capsized) {
                        float n38 = 1.0f;
                        if (n24 != 0) {
                            n38 = 1.2f;
                        }
                        if (this.m.random() > 0.75) {
                            contO.dust(n31, array[n31], array3[n31], array2[n31], this.scx[n31], this.scz[n31], n38 * this.simag[this.cn], true, (int)this.tilt);
                            if (this.im == 0 && !this.capsized) {
                                this.xt.skid(n24, (float)Math.sqrt(this.scx[n31] * this.scx[n31] + this.scz[n31] * this.scz[n31]));
                            }
                        }
                    }
                    else {
                        if (n24 == 1 && this.m.random() > 0.85) {
                            contO.dust(n31, array[n31], array3[n31], array2[n31], this.scx[n31], this.scz[n31], 1.1f * this.simag[this.cn], false, (int)this.tilt);
                        }
                        if ((n24 == 2 || n24 == 3) && this.m.random() > 0.7) {
                            contO.dust(n31, array[n31], array3[n31], array2[n31], this.scx[n31], this.scz[n31], 1.15f * this.simag[this.cn], false, (int)this.tilt);
                        }
                    }
                }
                else if (this.dcnt != 0) {
                    this.dcnt -= 2;
                    if (this.dcnt < 0) {
                        this.dcnt = 0;
                    }
                }
                if (n24 == 3) {
                    this.scy[(int)(this.m.random() * 4.0f)] = (float)(-100.0f * this.m.random() * (this.speed / this.swits[this.cn][2]) * (this.bounce[this.cn] - 0.3));
                }
                n29 += this.scx[n31];
                n30 += this.scz[n31];
            } while (++n31 < 4);
            this.txz = contO.xz;
            int n39;
            if (n29 > 0.0f) {
                n39 = -1;
            }
            else {
                n39 = 1;
            }
            this.mxz = (int)(Math.acos(n30 / Math.sqrt(n29 * n29 + n30 * n30)) / 0.017453292519943295 * n39);
            if (this.skid == 2) {
                if (!this.capsized) {
                    n29 /= 4.0f;
                    n30 /= 4.0f;
                    if (b) {
                        this.speed = -((float)Math.sqrt(n29 * n29 + n30 * n30) * this.m.cos(this.mxz - contO.xz));
                    }
                    else {
                        this.speed = (float)Math.sqrt(n29 * n29 + n30 * n30) * this.m.cos(this.mxz - contO.xz);
                    }
                }
                this.skid = 0;
            }
            if (this.capsized && n29 == 0.0f && n30 == 0.0f) {
                n24 = 0;
            }
            this.mtouch = false;
            b3 = true;
        }
        else if (this.skid != 2) {
            this.skid = 2;
        }
        int n40 = 0;
        final boolean[] array7 = new boolean[4];
        int n41 = 0;
        do {
            if (array3[n41] > 245.0f) {
                ++n40;
                this.wtouch = true;
                this.gtouch = true;
                if (!b3 && this.scy[n41] != 7.0f) {
                    float n42 = this.scy[n41] / 333.33f;
                    if (n42 > 0.3) {
                        n42 = 0.3f;
                    }
                    float n43;
                    if (n24 == 0) {
                        n43 = (float)(n42 + 1.1);
                    }
                    else {
                        n43 = (float)(n42 + 1.2);
                    }
                    contO.dust(n41, array[n41], array3[n41], array2[n41], this.scx[n41], this.scz[n41], n43 * this.simag[this.cn], true, 0);
                }
                array3[n41] = 250.0f;
                int n44 = 0;
                do {
                    if (n41 != n44 && array3[n44] <= 245.0f) {
                        final float[] array8 = array3;
                        final int n45 = n44;
                        array8[n45] -= array3[n41] - 250.0f;
                    }
                } while (++n44 < 4);
                float n46 = (Math.abs(this.m.sin(this.pxy)) + Math.abs(this.m.sin(this.pzy))) / 3.0f;
                if (n46 > 0.4) {
                    n46 = 0.4f;
                }
                float n47 = n46 + this.bounce[this.cn];
                if (n47 < 1.1) {
                    n47 = 1.1f;
                }
                this.regy(n41, Math.abs(this.scy[n41] * n47), contO);
                if (this.scy[n41] > 0.0f) {
                    final float[] scy4 = this.scy;
                    final int n48 = n41;
                    scy4[n48] -= Math.abs(this.scy[n41] * n47);
                }
            }
            array7[n41] = false;
        } while (++n41 < 4);
        int n49 = 0;
        for (int l = 0; l < trackers.nt; ++l) {
            int n50 = 0;
            int n51 = 0;
            int n52 = 0;
            do {
                if (!array7[n52] && array[n52] > trackers.x[l] - trackers.radx[l] && array[n52] < trackers.x[l] + trackers.radx[l] && array2[n52] > trackers.z[l] - trackers.radz[l] && array2[n52] < trackers.z[l] + trackers.radz[l] && array3[n52] > trackers.y[l] - trackers.rady[l] && array3[n52] < trackers.y[l] + trackers.rady[l]) {
                    if (trackers.xy[l] == 0 && trackers.zy[l] == 0 && trackers.y[l] != 250 && array3[n52] > trackers.y[l] - 5) {
                        ++n51;
                        this.wtouch = true;
                        this.gtouch = true;
                        if (!b3 && this.scy[n52] != 7.0f) {
                            float n53 = this.scy[n52] / 333.33f;
                            if (n53 > 0.3) {
                                n53 = 0.3f;
                            }
                            float n54;
                            if (n24 == 0) {
                                n54 = (float)(n53 + 1.1);
                            }
                            else {
                                n54 = (float)(n53 + 1.2);
                            }
                            contO.dust(n52, array[n52], array3[n52], array2[n52], this.scx[n52], this.scz[n52], n54 * this.simag[this.cn], true, 0);
                        }
                        array3[n52] = trackers.y[l];
                        int n55 = 0;
                        do {
                            if (n52 != n55 && array3[n55] <= trackers.y[l] - 5) {
                                final float[] array9 = array3;
                                final int n56 = n55;
                                array9[n56] -= array3[n52] - trackers.y[l];
                            }
                        } while (++n55 < 4);
                        float n57 = (Math.abs(this.m.sin(this.pxy)) + Math.abs(this.m.sin(this.pzy))) / 3.0f;
                        if (n57 > 0.4) {
                            n57 = 0.4f;
                        }
                        float n58 = n57 + this.bounce[this.cn];
                        if (n58 < 1.1) {
                            n58 = 1.1f;
                        }
                        this.regy(n52, Math.abs(this.scy[n52] * n58), contO);
                        if (this.scy[n52] > 0.0f) {
                            final float[] scy5 = this.scy;
                            final int n59 = n52;
                            scy5[n59] -= Math.abs(this.scy[n52] * n58);
                        }
                        array7[n52] = true;
                    }
                    if (trackers.zy[l] == -90 && array2[n52] < trackers.z[l] + trackers.radz[l] && this.scz[n52] < 0.0f) {
                        array2[n52] = trackers.z[l] + trackers.radz[l];
                        int n60 = 0;
                        do {
                            if (n52 != n60 && array2[n60] >= trackers.z[l] + trackers.radz[l]) {
                                final float[] array10 = array2;
                                final int n61 = n60;
                                array10[n61] -= array2[n52] - (trackers.z[l] + trackers.radz[l]);
                            }
                        } while (++n60 < 4);
                        float n62 = (Math.abs(this.m.cos(this.pxy)) + Math.abs(this.m.cos(this.pzy))) / 4.0f;
                        if (n62 > 0.3) {
                            n62 = 0.3f;
                        }
                        if (b3) {
                            n62 = 0.0f;
                        }
                        float n63 = (float)(n62 + (this.bounce[this.cn] - 0.2));
                        if (n63 < 1.1) {
                            n63 = 1.1f;
                        }
                        this.regz(n52, Math.abs(this.scz[n52] * n63 * trackers.dam[l]), contO);
                        final float[] scz3 = this.scz;
                        final int n64 = n52;
                        scz3[n64] += Math.abs(this.scz[n52] * n63);
                        this.skid = 2;
                        b2 = true;
                        array7[n52] = true;
                        control.wall = l;
                    }
                    if (trackers.zy[l] == 90 && array2[n52] > trackers.z[l] - trackers.radz[l] && this.scz[n52] > 0.0f) {
                        array2[n52] = trackers.z[l] - trackers.radz[l];
                        int n65 = 0;
                        do {
                            if (n52 != n65 && array2[n65] <= trackers.z[l] - trackers.radz[l]) {
                                final float[] array11 = array2;
                                final int n66 = n65;
                                array11[n66] -= array2[n52] - (trackers.z[l] - trackers.radz[l]);
                            }
                        } while (++n65 < 4);
                        float n67 = (Math.abs(this.m.cos(this.pxy)) + Math.abs(this.m.cos(this.pzy))) / 4.0f;
                        if (n67 > 0.3) {
                            n67 = 0.3f;
                        }
                        if (b3) {
                            n67 = 0.0f;
                        }
                        float n68 = (float)(n67 + (this.bounce[this.cn] - 0.2));
                        if (n68 < 1.1) {
                            n68 = 1.1f;
                        }
                        this.regz(n52, -Math.abs(this.scz[n52] * n68 * trackers.dam[l]), contO);
                        final float[] scz4 = this.scz;
                        final int n69 = n52;
                        scz4[n69] -= Math.abs(this.scz[n52] * n68);
                        this.skid = 2;
                        b2 = true;
                        array7[n52] = true;
                        control.wall = l;
                    }
                    if (trackers.xy[l] == -90 && array[n52] < trackers.x[l] + trackers.radx[l] && this.scx[n52] < 0.0f) {
                        array[n52] = trackers.x[l] + trackers.radx[l];
                        int n70 = 0;
                        do {
                            if (n52 != n70 && array[n70] >= trackers.x[l] + trackers.radx[l]) {
                                final float[] array12 = array;
                                final int n71 = n70;
                                array12[n71] -= array[n52] - (trackers.x[l] + trackers.radx[l]);
                            }
                        } while (++n70 < 4);
                        float n72 = (Math.abs(this.m.cos(this.pxy)) + Math.abs(this.m.cos(this.pzy))) / 4.0f;
                        if (n72 > 0.3) {
                            n72 = 0.3f;
                        }
                        if (b3) {
                            n72 = 0.0f;
                        }
                        float n73 = (float)(n72 + (this.bounce[this.cn] - 0.2));
                        if (n73 < 1.1) {
                            n73 = 1.1f;
                        }
                        this.regx(n52, Math.abs(this.scx[n52] * n73 * trackers.dam[l]), contO);
                        final float[] scx3 = this.scx;
                        final int n74 = n52;
                        scx3[n74] += Math.abs(this.scx[n52] * n73);
                        this.skid = 2;
                        b2 = true;
                        array7[n52] = true;
                        control.wall = l;
                    }
                    if (trackers.xy[l] == 90 && array[n52] > trackers.x[l] - trackers.radx[l] && this.scx[n52] > 0.0f) {
                        array[n52] = trackers.x[l] - trackers.radx[l];
                        int n75 = 0;
                        do {
                            if (n52 != n75 && array[n75] <= trackers.x[l] - trackers.radx[l]) {
                                final float[] array13 = array;
                                final int n76 = n75;
                                array13[n76] -= array[n52] - (trackers.x[l] - trackers.radx[l]);
                            }
                        } while (++n75 < 4);
                        float n77 = (Math.abs(this.m.cos(this.pxy)) + Math.abs(this.m.cos(this.pzy))) / 4.0f;
                        if (n77 > 0.3) {
                            n77 = 0.3f;
                        }
                        if (b3) {
                            n77 = 0.0f;
                        }
                        float n78 = (float)(n77 + (this.bounce[this.cn] - 0.2));
                        if (n78 < 1.1) {
                            n78 = 1.1f;
                        }
                        this.regx(n52, -Math.abs(this.scx[n52] * n78 * trackers.dam[l]), contO);
                        final float[] scx4 = this.scx;
                        final int n79 = n52;
                        scx4[n79] -= Math.abs(this.scx[n52] * n78);
                        this.skid = 2;
                        b2 = true;
                        array7[n52] = true;
                        control.wall = l;
                    }
                    if (trackers.zy[l] != 0 && trackers.zy[l] != 90 && trackers.zy[l] != -90) {
                        final int n80 = 90 + trackers.zy[l];
                        float n81 = 1.0f + (50 - Math.abs(trackers.zy[l])) / 30.0f;
                        if (n81 < 1.0f) {
                            n81 = 1.0f;
                        }
                        final float n82 = trackers.y[l] + ((array3[n52] - trackers.y[l]) * this.m.cos(n80) - (array2[n52] - trackers.z[l]) * this.m.sin(n80));
                        float n83 = trackers.z[l] + ((array3[n52] - trackers.y[l]) * this.m.sin(n80) + (array2[n52] - trackers.z[l]) * this.m.cos(n80));
                        if (n83 > trackers.z[l] && n83 < trackers.z[l] + 200) {
                            final float[] scy6 = this.scy;
                            final int n84 = n52;
                            scy6[n84] -= (n83 - trackers.z[l]) / n81;
                            n83 = trackers.z[l];
                        }
                        if (n83 > trackers.z[l] - 30) {
                            if (trackers.skd[l] == 2) {
                                ++n50;
                            }
                            else {
                                ++n49;
                            }
                            this.wtouch = true;
                            this.gtouch = false;
                            if (!b3 && n24 != 0) {
                                contO.dust(n52, array[n52], array3[n52], array2[n52], this.scx[n52], this.scz[n52], 1.4f * this.simag[this.cn], true, 0);
                            }
                        }
                        array3[n52] = trackers.y[l] + ((n82 - trackers.y[l]) * this.m.cos(-n80) - (n83 - trackers.z[l]) * this.m.sin(-n80));
                        array2[n52] = trackers.z[l] + ((n82 - trackers.y[l]) * this.m.sin(-n80) + (n83 - trackers.z[l]) * this.m.cos(-n80));
                        array7[n52] = true;
                    }
                    if (trackers.xy[l] == 0 || trackers.xy[l] == 90 || trackers.xy[l] == -90) {
                        continue;
                    }
                    final int n85 = 90 + trackers.xy[l];
                    float n86 = 1.0f + (50 - Math.abs(trackers.xy[l])) / 30.0f;
                    if (n86 < 1.0f) {
                        n86 = 1.0f;
                    }
                    final float n87 = trackers.y[l] + ((array3[n52] - trackers.y[l]) * this.m.cos(n85) - (array[n52] - trackers.x[l]) * this.m.sin(n85));
                    float n88 = trackers.x[l] + ((array3[n52] - trackers.y[l]) * this.m.sin(n85) + (array[n52] - trackers.x[l]) * this.m.cos(n85));
                    if (n88 > trackers.x[l] && n88 < trackers.x[l] + 200) {
                        final float[] scy7 = this.scy;
                        final int n89 = n52;
                        scy7[n89] -= (n88 - trackers.x[l]) / n86;
                        n88 = trackers.x[l];
                    }
                    if (n88 > trackers.x[l] - 30) {
                        if (trackers.skd[l] == 2) {
                            ++n50;
                        }
                        else {
                            ++n49;
                        }
                        this.wtouch = true;
                        this.gtouch = false;
                        if (!b3 && n24 != 0) {
                            contO.dust(n52, array[n52], array3[n52], array2[n52], this.scx[n52], this.scz[n52], 1.4f * this.simag[this.cn], true, 0);
                        }
                    }
                    array3[n52] = trackers.y[l] + ((n87 - trackers.y[l]) * this.m.cos(-n85) - (n88 - trackers.x[l]) * this.m.sin(-n85));
                    array[n52] = trackers.x[l] + ((n87 - trackers.y[l]) * this.m.sin(-n85) + (n88 - trackers.x[l]) * this.m.cos(-n85));
                    array7[n52] = true;
                }
            } while (++n52 < 4);
            if (n50 == 4) {
                this.mtouch = true;
            }
            if (n51 == 4) {
                n40 = 4;
            }
        }
        if (n49 == 4) {
            this.mtouch = true;
        }
        int n90 = 0;
        int n91 = 0;
        int n92 = 0;
        int n93 = 0;
        if (this.scy[2] != this.scy[0]) {
            int n94;
            if (this.scy[2] < this.scy[0]) {
                n94 = -1;
            }
            else {
                n94 = 1;
            }
            final double n95 = Math.sqrt((array2[0] - array2[2]) * (array2[0] - array2[2]) + (array3[0] - array3[2]) * (array3[0] - array3[2]) + (array[0] - array[2]) * (array[0] - array[2])) / (Math.abs(contO.keyz[0]) + Math.abs(contO.keyz[2]));
            if (n95 >= 0.9998) {
                n90 = n94;
            }
            else {
                n90 = (int)(Math.acos(n95) / 0.017453292519943295 * n94);
            }
        }
        if (this.scy[3] != this.scy[1]) {
            int n96;
            if (this.scy[3] < this.scy[1]) {
                n96 = -1;
            }
            else {
                n96 = 1;
            }
            final double n97 = Math.sqrt((array2[1] - array2[3]) * (array2[1] - array2[3]) + (array3[1] - array3[3]) * (array3[1] - array3[3]) + (array[1] - array[3]) * (array[1] - array[3])) / (Math.abs(contO.keyz[1]) + Math.abs(contO.keyz[3]));
            if (n97 >= 0.9998) {
                n91 = n96;
            }
            else {
                n91 = (int)(Math.acos(n97) / 0.017453292519943295 * n96);
            }
        }
        if (this.scy[1] != this.scy[0]) {
            int n98;
            if (this.scy[1] < this.scy[0]) {
                n98 = -1;
            }
            else {
                n98 = 1;
            }
            final double n99 = Math.sqrt((array2[0] - array2[1]) * (array2[0] - array2[1]) + (array3[0] - array3[1]) * (array3[0] - array3[1]) + (array[0] - array[1]) * (array[0] - array[1])) / (Math.abs(contO.keyx[0]) + Math.abs(contO.keyx[1]));
            if (n99 >= 0.9998) {
                n92 = n98;
            }
            else {
                n92 = (int)(Math.acos(n99) / 0.017453292519943295 * n98);
            }
        }
        if (this.scy[3] != this.scy[2]) {
            int n100;
            if (this.scy[3] < this.scy[2]) {
                n100 = -1;
            }
            else {
                n100 = 1;
            }
            final double n101 = Math.sqrt((array2[2] - array2[3]) * (array2[2] - array2[3]) + (array3[2] - array3[3]) * (array3[2] - array3[3]) + (array[2] - array[3]) * (array[2] - array[3])) / (Math.abs(contO.keyx[2]) + Math.abs(contO.keyx[3]));
            if (n101 >= 0.9998) {
                n93 = n100;
            }
            else {
                n93 = (int)(Math.acos(n101) / 0.017453292519943295 * n100);
            }
        }
        if (b2) {
            int abs;
            for (abs = Math.abs(contO.xz + 45); abs > 180; abs -= 360) {}
            if (Math.abs(abs) > 90) {
                this.pmlt = 1;
            }
            else {
                this.pmlt = -1;
            }
            int abs2;
            for (abs2 = Math.abs(contO.xz - 45); abs2 > 180; abs2 -= 360) {}
            if (Math.abs(abs2) > 90) {
                this.nmlt = 1;
            }
            else {
                this.nmlt = -1;
            }
        }
        contO.xz += (int)(this.forca * (this.scz[0] * this.nmlt - this.scz[1] * this.pmlt + this.scz[2] * this.pmlt - this.scz[3] * this.nmlt + this.scx[0] * this.pmlt + this.scx[1] * this.nmlt - this.scx[2] * this.nmlt - this.scx[3] * this.pmlt));
        if (Math.abs(n91) > Math.abs(n90)) {
            n90 = n91;
        }
        if (Math.abs(n93) > Math.abs(n92)) {
            n92 = n93;
        }
        if (!zyinv) {
            this.pzy += n90;
        }
        else {
            this.pzy -= n90;
        }
        if (n3 == 0) {
            this.pxy += n92;
        }
        else {
            this.pxy -= n92;
        }
        if (n40 == 4) {
            int n102 = 0;
            while (this.pzy < 360) {
                this.pzy += 360;
                contO.zy += 360;
            }
            while (this.pzy > 360) {
                this.pzy -= 360;
                contO.zy -= 360;
            }
            if (this.pzy < 190 && this.pzy > 170) {
                this.pzy = 180;
                contO.zy = 180;
                ++n102;
            }
            if (this.pzy > 350 || this.pzy < 10) {
                this.pzy = 0;
                contO.zy = 0;
                ++n102;
            }
            while (this.pxy < 360) {
                this.pxy += 360;
                contO.xy += 360;
            }
            while (this.pxy > 360) {
                this.pxy -= 360;
                contO.xy -= 360;
            }
            if (this.pxy < 190 && this.pxy > 170) {
                this.pxy = 180;
                contO.xy = 180;
                ++n102;
            }
            if (this.pxy > 350 || this.pxy < 10) {
                this.pxy = 0;
                contO.xy = 0;
                ++n102;
            }
            if (n102 == 2) {
                this.mtouch = true;
            }
        }
        if (!this.mtouch && this.wtouch) {
            if (this.cntouch == 10) {
                this.mtouch = true;
            }
            else {
                ++this.cntouch;
            }
        }
        else {
            this.cntouch = 0;
        }
        contO.y = (int)((array3[0] + array3[1] + array3[2] + array3[3]) / 4.0f - grat * this.m.cos(this.pzy) * this.m.cos(this.pxy) + n6);
        int n103;
        if (zyinv) {
            n103 = -1;
        }
        else {
            n103 = 1;
        }
        contO.x = (int)((array[0] - contO.keyx[0] * this.m.cos(contO.xz) + n103 * contO.keyz[0] * this.m.sin(contO.xz) + array[1] - contO.keyx[1] * this.m.cos(contO.xz) + n103 * contO.keyz[1] * this.m.sin(contO.xz) + array[2] - contO.keyx[2] * this.m.cos(contO.xz) + n103 * contO.keyz[2] * this.m.sin(contO.xz) + array[3] - contO.keyx[3] * this.m.cos(contO.xz) + n103 * contO.keyz[3] * this.m.sin(contO.xz)) / 4.0f + grat * this.m.sin(this.pxy) * this.m.cos(contO.xz) - grat * this.m.sin(this.pzy) * this.m.sin(contO.xz) + n4);
        contO.z = (int)((array2[0] - n103 * contO.keyz[0] * this.m.cos(contO.xz) - contO.keyx[0] * this.m.sin(contO.xz) + array2[1] - n103 * contO.keyz[1] * this.m.cos(contO.xz) - contO.keyx[1] * this.m.sin(contO.xz) + array2[2] - n103 * contO.keyz[2] * this.m.cos(contO.xz) - contO.keyx[2] * this.m.sin(contO.xz) + array2[3] - n103 * contO.keyz[3] * this.m.cos(contO.xz) - contO.keyx[3] * this.m.sin(contO.xz)) / 4.0f + grat * this.m.sin(this.pxy) * this.m.sin(contO.xz) - grat * this.m.sin(this.pzy) * this.m.cos(contO.xz) + n5);
        if (Math.abs(this.speed) > 10.0f || !this.mtouch) {
            if (Math.abs(this.pxy - contO.xy) >= 4) {
                if (this.pxy > contO.xy) {
                    contO.xy += 2 + (this.pxy - contO.xy) / 2;
                }
                else {
                    contO.xy -= 2 + (contO.xy - this.pxy) / 2;
                }
            }
            else {
                contO.xy = this.pxy;
            }
            if (Math.abs(this.pzy - contO.zy) >= 4) {
                if (this.pzy > contO.zy) {
                    contO.zy += 2 + (this.pzy - contO.zy) / 2;
                }
                else {
                    contO.zy -= 2 + (contO.zy - this.pzy) / 2;
                }
            }
            else {
                contO.zy = this.pzy;
            }
        }
        if (this.wtouch && !this.capsized) {
            final float n104 = (float)(this.speed / this.swits[this.cn][2] * 14.0f * (this.bounce[this.cn] - 0.4));
            if (control.left && this.tilt < n104 && this.tilt >= 0.0f) {
                this.tilt += 0.4;
            }
            else if (control.right && this.tilt > -n104 && this.tilt <= 0.0f) {
                this.tilt -= 0.4;
            }
            else if (Math.abs(this.tilt) > 3.0 * (this.bounce[this.cn] - 0.4)) {
                if (this.tilt > 0.0f) {
                    this.tilt -= (float)(3.0 * (this.bounce[this.cn] - 0.3));
                }
                else {
                    this.tilt += (float)(3.0 * (this.bounce[this.cn] - 0.3));
                }
            }
            else {
                this.tilt = 0.0f;
            }
            contO.xy += (int)this.tilt;
            if (this.gtouch) {
                contO.y -= (int)(this.tilt / 1.5);
            }
        }
        else if (this.tilt != 0.0f) {
            this.tilt = 0.0f;
        }
        if (this.wtouch && n24 == 2) {
            contO.zy += (int)((this.m.random() * 6.0f * this.speed / this.swits[this.cn][2] - 3.0f * this.speed / this.swits[this.cn][2]) * (this.bounce[this.cn] - 0.3));
            contO.xy += (int)((this.m.random() * 6.0f * this.speed / this.swits[this.cn][2] - 3.0f * this.speed / this.swits[this.cn][2]) * (this.bounce[this.cn] - 0.3));
        }
        if (this.wtouch && n24 == 1) {
            contO.zy += (int)((this.m.random() * 4.0f * this.speed / this.swits[this.cn][2] - 2.0f * this.speed / this.swits[this.cn][2]) * (this.bounce[this.cn] - 0.3));
            contO.xy += (int)((this.m.random() * 4.0f * this.speed / this.swits[this.cn][2] - 2.0f * this.speed / this.swits[this.cn][2]) * (this.bounce[this.cn] - 0.3));
        }
        if (this.hitmag > this.maxmag[this.cn] && !this.dest) {
            this.distruct(contO);
            if (this.cntdest == 7) {
                this.dest = true;
            }
            else {
                ++this.cntdest;
            }
            if (this.cntdest == 1) {
                this.rpd.dest[this.im] = 300;
            }
        }
        if (contO.dist == 0) {
            for (int n105 = 0; n105 < contO.npl; ++n105) {
                if (contO.p[n105].chip != 0) {
                    contO.p[n105].chip = 0;
                }
                if (contO.p[n105].embos != 0) {
                    contO.p[n105].embos = 13;
                }
            }
        }
        int focus = 0;
        int n106 = 0;
        int n107 = 0;
        int n108;
        if (this.nofocus) {
            n108 = 1;
        }
        else {
            n108 = 7;
        }
        for (int n109 = 0; n109 < checkPoints.n; ++n109) {
            if (checkPoints.typ[n109] > 0) {
                ++n107;
                if (checkPoints.typ[n109] == 1) {
                    if (this.clear == n107 + this.nlaps * checkPoints.nsp) {
                        n108 = 1;
                    }
                    if (Math.abs(contO.z - checkPoints.z[n109]) < 60.0f + Math.abs(this.scz[0] + this.scz[1] + this.scz[2] + this.scz[3]) / 4.0f && Math.abs(contO.x - checkPoints.x[n109]) < 700 && Math.abs(contO.y - checkPoints.y[n109]) < 800 && this.clear == n107 + this.nlaps * checkPoints.nsp - 1) {
                        this.clear = n107 + this.nlaps * checkPoints.nsp;
                        this.pcleared = n109;
                        this.focus = -1;
                    }
                }
                if (checkPoints.typ[n109] == 2) {
                    if (this.clear == n107 + this.nlaps * checkPoints.nsp) {
                        n108 = 1;
                    }
                    if (Math.abs(contO.x - checkPoints.x[n109]) < 60.0f + Math.abs(this.scx[0] + this.scx[1] + this.scx[2] + this.scx[3]) / 4.0f && Math.abs(contO.z - checkPoints.z[n109]) < 700 && Math.abs(contO.y - checkPoints.y[n109]) < 800 && this.clear == n107 + this.nlaps * checkPoints.nsp - 1) {
                        this.clear = n107 + this.nlaps * checkPoints.nsp;
                        this.pcleared = n109;
                        this.focus = -1;
                    }
                }
            }
            if (this.py(contO.x / 100, checkPoints.x[n109] / 100, contO.z / 100, checkPoints.z[n109] / 100) * n108 < n106 || n106 == 0) {
                focus = n109;
                n106 = this.py(contO.x / 100, checkPoints.x[n109] / 100, contO.z / 100, checkPoints.z[n109] / 100) * n108;
            }
        }
        if (this.clear == n107 + this.nlaps * checkPoints.nsp) {
            ++this.nlaps;
        }
        if (this.focus == -1) {
            if (this.im == 0) {
                focus += 2;
            }
            else {
                ++focus;
            }
            if (!this.nofocus) {
                int n110;
                for (n110 = this.pcleared + 1; checkPoints.typ[n110] <= 0; n110 = 0) {
                    if (++n110 == checkPoints.n) {}
                }
                if (focus > n110 && (this.clear != this.nlaps * checkPoints.nsp || focus < this.pcleared)) {
                    focus = n110;
                    this.focus = focus;
                }
            }
            if (focus >= checkPoints.n) {
                focus -= checkPoints.n;
            }
            if (checkPoints.typ[focus] == -3) {
                focus = 0;
            }
            if (this.im == 0) {
                if (this.missedcp != -1) {
                    this.missedcp = -1;
                }
            }
            else if (this.missedcp != 0) {
                this.missedcp = 0;
            }
        }
        else {
            focus = this.focus;
            if (this.im == 0) {
                if (this.missedcp == 0 && this.mtouch && Math.sqrt(this.py(contO.x / 10, checkPoints.x[this.focus] / 10, contO.z / 10, checkPoints.z[this.focus] / 10)) > 800.0) {
                    this.missedcp = 1;
                }
                if (this.missedcp == -2 && Math.sqrt(this.py(contO.x / 10, checkPoints.x[this.focus] / 10, contO.z / 10, checkPoints.z[this.focus] / 10)) < 400.0) {
                    this.missedcp = 0;
                }
                if (this.missedcp != 0 && this.mtouch && Math.sqrt(this.py(contO.x / 10, checkPoints.x[this.focus] / 10, contO.z / 10, checkPoints.z[this.focus] / 10)) < 250.0) {
                    this.missedcp = 68;
                }
            }
            else {
                this.missedcp = 1;
            }
            if (this.nofocus) {
                this.focus = -1;
                this.missedcp = 0;
            }
        }
        if (this.nofocus) {
            this.nofocus = false;
        }
        this.point = focus;
        for (int n111 = 0; n111 < checkPoints.fn; ++n111) {
            if (!checkPoints.roted[n111]) {
                if (Math.abs(contO.z - checkPoints.fz[n111]) < 200 && this.py(contO.x / 100, checkPoints.fx[n111] / 100, contO.y / 100, checkPoints.fy[n111] / 100) < 30) {
                    if (contO.dist == 0) {
                        contO.fcnt = 8;
                    }
                    else {
                        if (this.im == 0 && !contO.fix && !this.xt.mutes) {
                            this.xt.carfixed.play();
                        }
                        contO.fix = true;
                    }
                    this.rpd.fix[this.im] = 300;
                }
            }
            else if (Math.abs(contO.x - checkPoints.fx[n111]) < 200 && this.py(contO.z / 100, checkPoints.fz[n111] / 100, contO.y / 100, checkPoints.fy[n111] / 100) < 30) {
                if (contO.dist == 0) {
                    contO.fcnt = 8;
                }
                else {
                    if (this.im == 0 && !contO.fix && !this.xt.mutes) {
                        this.xt.carfixed.play();
                    }
                    contO.fix = true;
                }
                this.rpd.fix[this.im] = 300;
            }
        }
        if (contO.fcnt == 7 || contO.fcnt == 8) {
            this.squash = 0;
            this.nbsq = 0;
            this.hitmag = 0;
            this.cntdest = 0;
            this.dest = false;
            this.newcar = true;
        }
        if (!this.mtouch) {
            if (this.trcnt != 1) {
                this.trcnt = 1;
                this.lxz = contO.xz;
            }
            if (this.loop == 2 || this.loop == -1) {
                this.travxy += (int)(this.rcomp - this.lcomp);
                if (Math.abs(this.travxy) > 135) {
                    this.rtab = true;
                }
                this.travzy += (int)(this.ucomp - this.dcomp);
                if (this.travzy > 135) {
                    this.ftab = true;
                }
                if (this.travzy < -135) {
                    this.btab = true;
                }
            }
            if (this.lxz != contO.xz) {
                this.travxz += this.lxz - contO.xz;
                this.lxz = contO.xz;
            }
            if (this.srfcnt < 10) {
                if (control.wall != -1) {
                    this.surfer = true;
                }
                ++this.srfcnt;
            }
        }
        else if (!this.dest) {
            if (!this.capsized) {
                if (this.capcnt != 0) {
                    this.capcnt = 0;
                }
                if (this.gtouch && this.trcnt != 0) {
                    if (this.trcnt == 9) {
                        this.powerup = 0.0f;
                        if (Math.abs(this.travxy) > 90) {
                            this.powerup += Math.abs(this.travxy) / 24.0f;
                        }
                        else if (this.rtab) {
                            this.powerup += 30.0f;
                        }
                        if (Math.abs(this.travzy) > 90) {
                            this.powerup += Math.abs(this.travzy) / 18.0f;
                        }
                        else {
                            if (this.ftab) {
                                this.powerup += 40.0f;
                            }
                            if (this.btab) {
                                this.powerup += 40.0f;
                            }
                        }
                        if (Math.abs(this.travxz) > 90) {
                            this.powerup += Math.abs(this.travxz) / 18.0f;
                        }
                        if (this.surfer) {
                            this.powerup += 30.0f;
                        }
                        this.power += this.powerup;
                        if (this.im == 0 && (int)this.powerup > this.rpd.powered && this.rpd.wasted == 0 && this.powerup > 60.0f) {
                            this.rpd.cotchinow(0);
                            if (this.rpd.hcaught) {
                                this.rpd.whenwasted = 225;
                                this.rpd.powered = (int)this.powerup;
                            }
                        }
                        if (this.power > 98.0f) {
                            this.power = 98.0f;
                            if (this.powerup > 150.0f) {
                                this.xtpower = 200;
                            }
                            else {
                                this.xtpower = 100;
                            }
                        }
                    }
                    if (this.trcnt == 10) {
                        this.travxy = 0;
                        this.travzy = 0;
                        this.travxz = 0;
                        this.ftab = false;
                        this.rtab = false;
                        this.btab = false;
                        this.trcnt = 0;
                        this.srfcnt = 0;
                        this.surfer = false;
                    }
                    else {
                        ++this.trcnt;
                    }
                }
            }
            else {
                if (this.trcnt != 0) {
                    this.travxy = 0;
                    this.travzy = 0;
                    this.travxz = 0;
                    this.ftab = false;
                    this.rtab = false;
                    this.btab = false;
                    this.trcnt = 0;
                    this.srfcnt = 0;
                    this.surfer = false;
                }
                if (this.capcnt == 0) {
                    int n112 = 0;
                    int n113 = 0;
                    do {
                        if (Math.abs(this.scz[n113]) < 70.0f && Math.abs(this.scx[n113]) < 70.0f) {
                            ++n112;
                        }
                    } while (++n113 < 4);
                    if (n112 == 4) {
                        this.capcnt = 1;
                    }
                }
                else {
                    ++this.capcnt;
                    if (this.capcnt == 30) {
                        this.speed = 0.0f;
                        contO.y += this.flipy[this.cn];
                        this.pxy += 180;
                        contO.xy += 180;
                        this.capcnt = 0;
                    }
                }
            }
            if (this.trcnt == 0) {
                if (this.xtpower == 0) {
                    if (this.power > 0.0f) {
                        this.power -= this.power * this.power * this.power / this.powerloss[this.cn];
                    }
                    else {
                        this.power = 0.0f;
                    }
                }
                else {
                    --this.xtpower;
                }
            }
        }
        if (this.im == 0) {
            if (control.wall != -1) {
                control.wall = -1;
            }
        }
        else if (this.lastcolido != 0 && !this.dest) {
            --this.lastcolido;
        }
        if (this.dest) {
            if (checkPoints.dested[this.im] == 0) {
                if (this.lastcolido == 0) {
                    checkPoints.dested[this.im] = 1;
                }
                else {
                    checkPoints.dested[this.im] = 2;
                }
            }
        }
        else if (checkPoints.dested[this.im] != 0) {
            checkPoints.dested[this.im] = 0;
        }
    }
}
