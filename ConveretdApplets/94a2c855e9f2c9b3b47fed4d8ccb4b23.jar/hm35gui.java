import java.io.DataInputStream;
import java.awt.Event;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.CropImageFilter;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class hm35gui extends hm35player
{
    public static byte mq;
    public static String mp;
    public static String mo;
    public int mn;
    public int mm;
    public int ml;
    public int[] mj;
    public Image mi;
    public Image mh;
    public Image mg;
    public Image[][] mf;
    public Image[][] me;
    public Graphics md;
    public Graphics mc;
    public Graphics mb;
    public Color ma;
    public int l9;
    public int l8;
    public int l7;
    public hm35player l6;
    public hm35player l5;
    public int[] l4;
    public boolean[] l3;
    public boolean l2;
    public boolean l1;
    public boolean l0;
    public boolean l_;
    public boolean lz;
    public boolean ly;
    public boolean lx;
    public boolean lw;
    public boolean lv;
    public boolean lu;
    public boolean lt;
    public boolean ls;
    public boolean lr;
    public boolean lq;
    public boolean lp;
    public boolean lo;
    public boolean ln;
    public boolean lm;
    public int ll;
    public int lk;
    public int lj;
    public int li;
    public int lh;
    public static int lg;
    public static int lf;
    public int le;
    public int ld;
    public int lc;
    public int lb;
    public int la;
    public int k9;
    public int k8;
    public int k7;
    public boolean k6;
    public int k5;
    public int k4;
    public int k3;
    public int k2;
    public int k1;
    
    static {
        hm35gui.mq = 32;
        hm35gui.mp = "\u4947\u3846\u6137,\u0007³\u0000\u0000\u0080\u0000\u0080\u8080\u0000\u8000\u0080\u0080\u8080\u8080\uc080\uc0c0\u00ff\u0000\u00ff\uffff\u0000\uff00\u00ff\u00ff\uffff\uffff\u2cff\u0000\u0000,\u0007\u0400\u103c\u8001\u9d2a\udb38\u374c\u95e7\u4a25\ub921\u639d\ua139\u35d7\ue976\uc12a\u2870\ub3df\ub5d9\u0f5f\u1f7a\ucbd5\u8c24\u6ced\ud147\ue92a\u112a\uc969\u53dc\u232a\u90f9\u6c3d\u16b5\u0001;";
        hm35gui.mo = "\u4947\u3846\u6137´\f³\u0000\u0000\u0080\u0000\u0080\u8080\u0000\u8000\u0080\u0080\u8080\u8080\uc080\uc0c0\u00ff\u0000\u00ff\uffff\u0000\uff00\u00ff\u00ff\uffff\uffff\u2cff\u0000\u0000´\f\u0400\u10ff\u49c9\ubdab\u6013\ubb8d\u00df\u8577\u4562\ue776\uaea7\u5aec\u5fb6\uc208\u0234\uf83c\udd2b\u09b9\u401c\u2519\u3c04\u8708\u51b5\u254c\u263a\uce47\u7461\u4c27\u4865\u1e53\uf1ee\u79e2\uc033\u116d\u107c\ua503\uf1aa\ua50c\u51f3\u9651\u6fb6\ucd4a\ua356\ufc58\udf76\u6adc\u3f39\u3839\u0708\u7b60\u840f\u4657\u8558\u8b14\u503f\u9225\u5774\u941a\u576f\u5831\u5620\u9e75\u9b52\u5c43\u5c58\ua319\ua524\ua781\u8583\u87aa\u3689\u793f\u4625\u4685\u9684\u3b79\ub136\u73a1\u68ba\u2932\uc227\ube93\u4ebf\u78c3\ua6c0\ua587\u7e3b\u367e\u7965\uad88\uce65\ub1b0\uad45\ub51c\ub743\ubbb3\uc7b9\u4495\uc53a\u9ebd\ua0e9\ucc94\u9d9f\uf1bd\u8736\u825d\uaacf\ud6a8\uced3\u19fb\ud36e\u2d1c\u5792\u9785\u38b8\uce24\u9930\ud0b4\u3cdd\u5d65\uf5de\u0692\ue28a\u6c0e\ub43c\u62b9\u89a5\ub913\u0661\u2557\u09d6\u22e2\u0b8e\u6d87\u2220\ua633\u540e\u304b\ub608\u6848\u5014\u36a6\uf382\u4934\ub972\ue061\u1613\u7f0e\u91b2\ub2f3\u04e7\u3085\ud267\uc3ac\uc6a8\u58a5\u9e33\u450a\u64a2\u142a\u520f\u228f\ua0b5\u2b86\ua5b7\u8830\u42ee\ucff8\ud9aa\u68b3\uaad3\ue93d\u0221\u3b00";
        hm35gui.lg = 50;
        hm35gui.lf = 4;
    }
    
    public hm35gui() {
        this.mn = 1;
        this.mm = 12;
        this.ml = 14;
        this.mj = new int[] { 2, 4, 5, 3, 6, 7, 8, 9, 4, 5, 3, 12, 13, 12, 14, 12, 13, 0, 1, 11 };
        this.ma = Color.lightGray;
        this.l9 = 99;
        this.ln = true;
        this.lm = true;
        this.lj = 99;
        this.k8 = 6060;
        this.k6 = false;
        this.k4 = 99;
    }
    
    private final Image ds(int n, int n2, final boolean b) {
        if (n > 9999) {
            n = 9999;
        }
        if (n2 > n) {
            n2 %= n;
        }
        final int[] array = new int[4];
        for (int i = 0, n3 = 1000; i < 4; ++i, n3 /= 10) {
            array[i] = n2 / n3;
            n2 -= array[i] * n3;
        }
        if (this.mh == null) {
            this.mh = this.createImage(2 * this.ml, this.ml);
            this.mc = this.mh.getGraphics();
        }
        this.mc.setColor(this.ma);
        this.mc.fill3DRect(0, 0, 28 * this.mn, this.ml, true);
        final int n4 = this.mn + 1;
        final int n5 = 4 * this.mn;
        final int n6 = 7 * this.mn;
        final int n7 = (this.ml - n6) / 2;
        this.mc.drawImage(this.mf[b][array[0]], n4, n7, n5, n6, this);
        final int n8;
        this.mc.drawImage(this.mf[b][array[1]], n8 = n4 + (n5 + this.mn), n7, n5, n6, this);
        final int n9;
        this.mc.drawImage(this.mf[b][10], n9 = n8 + (n5 + this.mn), n7, n5, n6, this);
        final int n10;
        this.mc.drawImage(this.mf[b][array[2]], n10 = n9 + (n5 + this.mn), n7, n5, n6, this);
        this.mc.drawImage(this.mf[b][array[3]], n10 + (n5 + this.mn), n7, n5, n6, this);
        return this.mh;
    }
    
    private final Image dt(final boolean b, final int n, int n2, final int n3, int n4, final boolean b2) {
        hm35gui.lg = this.mn * 50;
        hm35gui.lf = this.mn * 4;
        if (!b) {
            n2 = n4;
        }
        if (n2 > n) {
            n2 = n;
        }
        if (n4 > n2) {
            n4 = n2;
        }
        n2 = ((n == 0) ? 0 : (hm35gui.lg * n2 / n - 1));
        n4 = ((n3 == 0) ? 0 : (hm35gui.lg * n4 / n3 - 1));
        if (this.mg == null) {
            this.mg = this.createImage(4 * this.ml, this.ml);
            this.mb = this.mg.getGraphics();
        }
        final int n5 = (4 * this.ml - hm35gui.lg) / 2;
        final int n6 = (this.ml - hm35gui.lf) / 2;
        this.mb.setColor(this.ma);
        this.mb.fill3DRect(0, 0, 4 * this.ml, this.ml, true);
        if (b) {
            this.mb.setColor(this.ma);
        }
        else {
            this.mb.setColor(Color.gray);
            n4 = n2 + 1;
        }
        this.mb.fillRect(n5 + 1, n6, n2++, hm35gui.lf);
        this.mb.setColor(Color.white);
        this.mb.fillRect(n5 + n2, n6, hm35gui.lg - n2 - 1, hm35gui.lf - 1);
        this.mb.setColor(b2 ? Color.gray : Color.black);
        this.mb.drawRect(n5, n6, hm35gui.lg - 1, hm35gui.lf - 1);
        n4 += n5;
        final int n7 = b ? (this.mn * 3) : this.mn;
        final int n8 = this.mn * 8;
        final int n9 = n4 - n7 / 2;
        final int n10 = (this.ml - n8) / 2;
        if (b) {
            this.mb.setColor(this.lo ? this.ma : Color.gray);
            this.mb.fill3DRect(n9 + 1, n10, n7, n8, true);
            this.mb.setColor(b2 ? Color.gray : Color.black);
            this.mb.drawRect(n9, n10 - 1, n7 + 1, n8 + 1);
        }
        else {
            this.mb.fillRoundRect(n9, n10, this.mn, n8, this.mn, this.mn);
        }
        return this.mg;
    }
    
    private final void du() {
        final byte[] array = new byte[hm35gui.mo.length() * 2];
        for (int i = hm35gui.mo.length() - 1; i >= 0; --i) {
            final char char1 = hm35gui.mo.charAt(i);
            array[i * 2] = (byte)(char1 & '\u00ff');
            array[i * 2 + 1] = (byte)(char1 >> 8 & '\u00ff');
        }
        final Image image = super.createImage(-1, array);
        this.me = new Image[2][15];
        for (int j = 0; j < 15; ++j) {
            final Image image2 = this.createImage(new FilteredImageSource(image.getSource(), new CropImageFilter(j * 12, 0, 12, 12)));
            this.me[0][j] = image2;
            try {
                final int[] array2 = new int[144];
                if (new PixelGrabber(image2, 0, 0, 12, 12, array2, 0, 12).grabPixels()) {
                    super.sleep(20L);
                    for (int k = 0; k < 144; ++k) {
                        if (array2[k] == -1) {
                            array2[k] = -4144960;
                        }
                        if (array2[k] == -16777216) {
                            array2[k] = -8355712;
                        }
                    }
                    this.me[1][j] = this.createImage(new MemoryImageSource(12, 12, array2, 0, 12));
                }
            }
            catch (Exception ex) {}
        }
        final byte[] array3 = new byte[hm35gui.mp.length() * 2];
        for (int l = hm35gui.mp.length() - 1; l >= 0; --l) {
            final char char2 = hm35gui.mp.charAt(l);
            array3[l * 2] = (byte)(char2 & '\u00ff');
            array3[l * 2 + 1] = (byte)(char2 >> 8 & '\u00ff');
        }
        final Image image3 = super.createImage(-1, array3);
        this.mf = new Image[2][11];
        for (int n = 0; n < 11; ++n) {
            this.mf[0][n] = this.createImage(new FilteredImageSource(image3.getSource(), new CropImageFilter(n * 4, 0, 4, 7)));
            try {
                final int[] array4 = new int[28];
                if (new PixelGrabber(this.mf[0][n], 0, 0, 4, 7, array4, 0, 4).grabPixels()) {
                    super.sleep(20L);
                    for (int n2 = 0; n2 < 28; ++n2) {
                        if (array4[n2] == -1) {
                            array4[n2] = -4144960;
                        }
                        if (array4[n2] == -16777216) {
                            array4[n2] = -8355712;
                        }
                    }
                    this.mf[1][n] = this.createImage(new MemoryImageSource(4, 7, array4, 0, 4));
                }
            }
            catch (Exception ex2) {}
        }
    }
    
    private final boolean dv(final int k1) {
        boolean b = false;
        if (this.k1 != k1) {
            final int k2 = k1 % (this.k8 % 100);
            final int la = k1 / (this.k8 / 100 % 100);
            if (this.k9 != k2 || this.la != la) {
                this.la = la;
                this.k9 = k2;
                b = true;
            }
            this.k1 = k1;
        }
        return b;
    }
    
    private final int dw(int n, final int n2) {
        int lc = this.lc;
        final int n3 = 50 * this.mn;
        final int n4 = this.k7 + 2 * this.ml - n3 / 2;
        if (n2 > 2 && n2 < this.ml) {
            n -= n4;
            if (n < 1) {
                n = 1;
            }
            if (n > n3) {
                n = n3;
            }
            lc = this.lb * n / n3;
        }
        return lc;
    }
    
    private final int dx() {
        final int[] l4 = this.l4;
        int n = 0;
        if (this.lw) {
            l4[n++] = 19;
        }
        if (this.l6 != null) {
            if (this.ly) {
                if (this.ln) {
                    l4[n++] = 18;
                    if (this.l2) {
                        l4[n++] = 0;
                        if (this.lr) {
                            l4[n++] = 2;
                        }
                        l4[n++] = 1;
                        l4[n++] = 3;
                    }
                    if (this.l1) {
                        l4[n++] = 5;
                        l4[n++] = 4;
                    }
                    this.li = n;
                    for (int i = 0; i < this.ll; ++i) {
                        l4[n++] = 6;
                    }
                    this.lh = n;
                }
                else {
                    l4[n++] = 17;
                }
            }
            else if (this.lz) {
                l4[n++] = (this.l3[1] ? 1 : 3);
            }
        }
        if (this.l5 != null) {
            if (this.ln) {
                final boolean b = this.l3[10];
                if (this.l_) {
                    if (this.lq) {
                        l4[n++] = 18;
                        this.lm = this.ln;
                    }
                    else {
                        l4[n++] = (b ? 16 : 15);
                    }
                    boolean b4;
                    boolean b3;
                    boolean b2 = b3 = (b4 = false);
                    if (this.lm) {
                        l4[n++] = 9;
                        if (l4[n - 1] == 9 && !this.l3[9]) {
                            b3 = true;
                        }
                        if (this.lx) {
                            l4[n++] = 8;
                        }
                        else if (this.ls) {
                            l4[n++] = 8;
                        }
                        if (l4[n - 1] == 8 && !this.l3[8]) {
                            b2 = true;
                        }
                        l4[n++] = 10;
                        if (l4[n - 1] == 10 && !this.l3[10]) {
                            b4 = true;
                        }
                        if (this.lu && b3 && b2 && b4) {
                            this.k6 = true;
                        }
                        else {
                            this.k6 = false;
                        }
                        if (this.lu) {
                            l4[n++] = -1;
                            l4[n++] = -1;
                            l4[n++] = -1;
                            l4[n++] = -1;
                        }
                        if (this.lt) {
                            l4[n++] = -2;
                            l4[n++] = -2;
                        }
                        if (this.lv && this.lq) {
                            l4[n++] = (super.d4 ? 13 : 14);
                        }
                    }
                }
                else if (this.l0) {
                    if (this.lq) {
                        l4[n++] = (b ? 10 : (this.lx ? 8 : 9));
                    }
                    else {
                        l4[n++] = (b ? 12 : 11);
                    }
                    if (this.lv && this.lq) {
                        l4[n++] = (super.d4 ? 13 : 14);
                    }
                }
            }
            else if (this.lq) {
                l4[n++] = 17;
            }
        }
        return n;
    }
    
    private final void dy() {
        boolean b = false;
        if (this.l9 < this.lk) {
            b = true;
        }
        this.l9 = 99;
        if (b) {
            this.repaint();
        }
    }
    
    public final void an(final int lb, final int ld, final int k8) {
        this.lb = lb;
        this.ld = ld;
        this.k8 = k8;
    }
    
    public final void ai(int ld) {
        if (this.k2 != ld) {
            if (ld > this.ld) {
                ld = this.ld;
            }
            if (ld < 0) {
                ld = 0;
            }
            final int n = ld;
            this.le = n;
            this.k2 = n;
            this.repaint();
        }
    }
    
    public final void aj(final int n, final int n2) {
        if (this.lo) {
            return;
        }
        boolean b = false;
        if (this.k3 != n) {
            this.lc = n;
            this.k3 = n;
            b = true;
        }
        if (b | this.dv(n2)) {
            this.repaint();
        }
    }
    
    public final void select(final hm35player hm35player, final int n) {
        if (hm35player == this.l6) {
            if (n < this.ll) {
                this.lj = n + this.li;
            }
            this.repaint();
        }
    }
    
    public final void setSensitive(final hm35player hm35player, final int n, final int n2) {
        if (this.l5 == null && this.l6 == null) {
            return;
        }
        if (hm35player == this.l5 || hm35player == this.l6) {
            boolean b = false;
            int n3 = 1;
            for (byte b2 = 0; b2 < hm35gui.mq; ++b2) {
                if ((n & n3) == n3) {
                    this.l3[b2] = ((n2 & n3) == n3);
                    b = true;
                }
                n3 *= 2;
            }
            if (this.ll != 0) {
                this.l3[7] = this.l3[6];
            }
            if ((n & 0x80) != 0x0) {
                super.d4 = ((n2 & 0x80) == 0x0);
                b = true;
            }
            if (b) {
                this.lk = this.dx();
                this.repaint();
            }
        }
    }
    
    public final void setGUI(final hm35player hm35player, final int n, final int ll) {
        final boolean b = false;
        this.l_ = b;
        this.l0 = b;
        final boolean b2 = false;
        this.ly = b2;
        this.lz = b2;
        final boolean b3 = false;
        this.lw = b3;
        this.lv = b3;
        final int n2 = n & 0x2F800;
        final int n3 = n & 0x7FF;
        this.l0 = ((n2 & 0x800) != 0x0);
        this.l_ = ((n2 & 0x1000) != 0x0);
        this.ly = ((n2 & 0x2000) != 0x0);
        this.lz = ((n2 & 0x4000) != 0x0);
        this.lv = ((n2 & 0x8000) != 0x0);
        this.lw = ((n2 & 0x20000) != 0x0);
        if (this.l3 == null) {
            this.l3 = new boolean[hm35gui.mq];
            for (byte b4 = 0; b4 < hm35gui.mq; ++b4) {
                this.l3[b4] = true;
            }
        }
        this.l4 = new int[hm35gui.mq];
        if (n3 == 0 && ll == 0) {
            if (hm35player == this.l5) {
                this.l5 = null;
            }
            if (hm35player == this.l6) {
                this.l6 = null;
            }
            if (this.l5 == null && this.l6 == null && !this.lw) {
                this.reshape(0, 0, 0, 0);
                return;
            }
        }
        if ((this.l0 || this.l_) && (n3 & 0xC) != 0x0) {
            this.l5 = hm35player;
            this.lq = ((n3 & 0x8) != 0x0);
            this.ls = ((n3 & 0x10) != 0x0);
            this.lx = ((n3 & 0x100) != 0x0);
            this.lu = ((n3 & 0x200) != 0x0);
            this.lt = ((n3 & 0x400) != 0x0);
            this.lv &= ((n3 & 0x80) != 0x0);
            this.l0 = (this.lq || (n3 & 0x4) != 0x0);
        }
        if (this.lq) {
            final boolean b5 = false;
            this.ly = b5;
            this.lz = b5;
        }
        if ((this.lz || this.ly) && ((n3 & 0x3) != 0x0 || ll > 0)) {
            this.l6 = hm35player;
            this.l2 = ((n3 & 0x1) != 0x0);
            this.l1 = ((n3 & 0x2) != 0x0);
            this.lr = ((n3 & 0x20) != 0x0);
            this.ll = ll;
        }
        final int l8 = this.l8;
        final int l9 = this.l7;
        this.lk = this.dx();
        this.reshape(l8, l9, this.lk * this.ml, this.ml);
    }
    
    public final boolean mouseEnter(final Event event, final int n, final int n2) {
        this.k5 = super.getCursorType();
        super.setCursor(0);
        this.lp = true;
        final int n3 = n / this.ml;
        final int n4 = this.l4[n3];
        if (n4 == -2 || n4 == -1) {
            return true;
        }
        if (this.k4 == n3 && this.l3[this.l4[n3]]) {
            this.l9 = this.k4;
            this.k4 = 99;
            this.repaint();
            final int n5 = this.l4[this.l9];
            if (n5 == 5) {
                this.l6.zoom(true, 1);
            }
            else if (n5 == 4) {
                this.l6.zoom(true, -1);
            }
        }
        return true;
    }
    
    public final boolean mouseExit(final Event event, final int n, final int n2) {
        super.setCursor(this.k5);
        this.lp = false;
        if (this.l9 != 99) {
            this.k4 = this.l9;
            final int n3 = this.l4[this.l9];
            if ((n3 == 5 || n3 == 4) && this.l3[n3]) {
                this.l6.zoom(true, 0);
            }
            this.dy();
        }
        return true;
    }
    
    public final boolean mouseUp(final Event event, final int n, final int n2) {
        if (!this.lp) {
            if (this.k4 != 99) {
                this.k4 = 99;
            }
            return true;
        }
        if (this.l9 == 99) {
            return true;
        }
        final int n3 = n / this.ml;
        final int n4 = this.l4[n3];
        this.lo = false;
        if (n4 == -2) {
            return true;
        }
        if (n4 == -1) {
            if (this.lu && this.lx && !this.k6) {
                this.lc = this.dw(n, n2);
                this.l5.a(this.lc, 1);
            }
            return true;
        }
        final hm35player l5 = this.l5;
        final hm35player l6 = this.l6;
        if (n3 == this.l9) {
            switch (this.l4[this.l9]) {
                case 0: {
                    l6.play(-1);
                    break;
                }
                case 1: {
                    l6.pause();
                    break;
                }
                case 2: {
                    l6.play(0);
                    break;
                }
                case 3: {
                    l6.play(1);
                    break;
                }
                case 4: {
                    l6.zoom(false, -1);
                    break;
                }
                case 5: {
                    l6.zoom(false, 1);
                    break;
                }
                case 6:
                case 7: {
                    l6.position(n3 - this.li);
                    break;
                }
                case 8: {
                    l5.pause();
                    break;
                }
                case 9: {
                    l5.play(0);
                    break;
                }
                case 10: {
                    l5.play(1);
                    break;
                }
                case 11: {
                    if (this.lx) {
                        l5.pause();
                        break;
                    }
                    l5.stop();
                    break;
                }
                case 12: {
                    l5.play(1);
                    break;
                }
                case 13:
                case 14: {
                    l5.b();
                    break;
                }
                case 15:
                case 16: {
                    this.lm = !this.lm;
                    final int l7 = this.l8;
                    final int l8 = this.l7;
                    this.lk = this.dx();
                    this.reshape(l7, l8, this.lk * this.ml, this.ml);
                    break;
                }
                case 17:
                case 18: {
                    this.ln = !this.ln;
                    final int l9 = this.l8;
                    final int l10 = this.l7;
                    this.lk = this.dx();
                    this.reshape(l9, l10, this.lk * this.ml, this.ml);
                    break;
                }
                case 19: {
                    super.temporalTrigger((byte)31, 0);
                    break;
                }
            }
        }
        this.dy();
        return true;
    }
    
    public final boolean mouseDrag(final Event event, final int n, final int n2) {
        if (this.l4[n / this.ml] == -1) {
            if (this.lu && this.lx && !this.k6) {
                final int dw = this.dw(n, n2);
                if (this.k3 != dw) {
                    this.lc = dw;
                    this.l5.a(this.lc, 0);
                    this.repaint();
                    this.k3 = dw;
                }
            }
            return true;
        }
        if (!this.lp) {
            return true;
        }
        final int n3 = n / this.ml;
        if (this.l9 == 99) {
            if (this.k4 == n3) {
                this.l9 = this.k4;
                this.k4 = 99;
                this.repaint();
                final int n4 = this.l4[this.l9];
                if (this.l3[n4]) {
                    if (n4 == 5) {
                        this.l6.zoom(true, 1);
                    }
                    else if (n4 == 4) {
                        this.l6.zoom(true, -1);
                    }
                }
            }
        }
        else if (n3 != this.l9) {
            this.k4 = this.l9;
            final int n5 = this.l4[this.l9];
            if (n5 > 0 && this.l3[n5] && (n5 == 5 || n5 == 4)) {
                this.l6.zoom(true, 0);
            }
            this.dy();
        }
        return true;
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        final int n3 = this.l4[n / this.ml];
        if (n3 == -2) {
            return true;
        }
        if (n3 == -1) {
            if (this.lu && this.lx && !this.k6) {
                this.lo = true;
                this.l9 = n / this.ml;
                this.lc = this.dw(n, n2);
                this.l5.a(this.lc, 0);
            }
            return true;
        }
        if (this.l3[n3]) {
            this.l9 = n / this.ml;
            this.repaint();
            if (n3 == 4) {
                this.l6.zoom(true, -1);
            }
            else if (n3 == 5) {
                this.l6.zoom(true, 1);
            }
        }
        return true;
    }
    
    public final void paint(final Graphics graphics) {
        if (this.me == null || this.lk == 0) {
            return;
        }
        if (this.mi == null) {
            this.mi = this.createImage(hm35gui.mq * this.ml, this.ml);
            this.md = this.mi.getGraphics();
        }
        this.md.setColor(this.ma);
        for (int i = 0; i < this.lk; ++i) {
            int n = this.l4[i];
            int k7 = i * this.ml;
            int mn = this.mn;
            if (n == -1 && this.lu) {
                this.k7 = k7;
                i += 3;
                this.md.drawImage(this.dt(this.lx, this.ld, this.le, this.lb, this.lc, this.k6), k7, 0, this.ma, this);
            }
            if (n == -2 && this.lt) {
                ++i;
                this.md.drawImage(this.ds(this.k8, this.la * 100 + this.k9, this.k6), k7, 0, this.ma, this);
            }
            if (n > -1) {
                if (this.l9 == i) {
                    this.md.fill3DRect(k7, 0, this.ml, this.ml, false);
                    ++k7;
                    mn += 2;
                }
                else {
                    this.md.fill3DRect(k7, 0, this.ml, this.ml, true);
                }
                if (this.l6 != null && this.ln && i >= this.li && i < this.lh) {
                    if (i == this.lj) {
                        this.md.fill3DRect(k7, 0, this.ml, this.ml, false);
                        ++k7;
                        mn += 2;
                    }
                    else {
                        this.md.fill3DRect(k7, 0, this.ml, this.ml, true);
                        ++n;
                    }
                }
                this.md.drawImage(this.me[!this.l3[n]][this.mj[n]], k7 + this.mn, mn, this.mm, this.mm, this.ma, this);
            }
        }
        graphics.drawImage(this.mi, 0, 0, this.ma, this);
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final int consumeFrame(final byte b, final int n, final byte b2, final short n2, final int n3, final int n4, final DataInputStream dataInputStream) {
        return -1;
    }
    
    public final int init(final hm35master hm35master) {
        super.init(hm35master);
        this.du();
        this.mn = super.ah("GUI", 1);
        this.mm *= this.mn;
        this.ml *= this.mn;
        this.l8 = 0;
        this.l7 = super.masterObject.ii.size().height;
        this.l7 -= ((this.l7 <= 27) ? this.l7 : this.ml);
        this.setBackground(this.ma);
        return 0;
    }
}
