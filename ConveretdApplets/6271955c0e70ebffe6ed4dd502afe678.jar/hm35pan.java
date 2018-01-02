import java.awt.Event;
import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.MediaTracker;
import java.io.DataInputStream;
import java.awt.Point;
import java.util.Hashtable;
import java.awt.image.ColorModel;
import java.util.Enumeration;
import java.util.Vector;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageConsumer;
import java.awt.image.ImageProducer;

// 
// Decompiled by Procyon v0.5.30
// 

public class hm35pan extends hm35player implements ImageProducer, ImageConsumer
{
    public Image hp;
    public Image ho;
    public double hn;
    public long hm;
    public boolean hl;
    public boolean hk;
    public long hj;
    public long hi;
    public long hh;
    public long b8;
    public long b7;
    public int hg;
    public int hf;
    public int he;
    public boolean hd;
    public double hc;
    public double hb;
    public double ha;
    public long g9;
    public int g8;
    public int g7;
    public int g6;
    public boolean g5;
    public double g4;
    public double g3;
    public double g2;
    public double g1;
    public double g0;
    public double g_;
    public double gz;
    public double gy;
    public double gx;
    public double gw;
    public double gv;
    public double gu;
    public double gt;
    public double gs;
    public double gr;
    public double gq;
    public double gp;
    public boolean go;
    public int cr;
    public int gn;
    public int cw;
    public int cv;
    public int gm;
    public Object cs;
    public int gl;
    public int gk;
    public Image gj;
    public Graphics gi;
    public int ck;
    public boolean b2;
    public boolean df;
    public boolean gh;
    public int[][] gg;
    public int gf;
    public boolean ge;
    public int b6;
    public int b5;
    public int b4;
    public int b3;
    public boolean b0;
    public int cu;
    public int ct;
    public int cq;
    public boolean cp;
    public boolean co;
    public boolean gd;
    public boolean gc;
    public long gb;
    public double ga;
    public double f9;
    public long f8;
    public boolean f7;
    public double f6;
    public double f5;
    public ImageConsumer f4;
    public int[] b_;
    public int[] f3;
    public int[] f2;
    public int[] f1;
    public int[] f0;
    public int[] f_;
    public int[] fz;
    public int[] fy;
    public int[] fx;
    public double fw;
    public double fv;
    public double fu;
    public double ft;
    public double fs;
    public double fr;
    public double fq;
    public int fp;
    public double fo;
    public double fn;
    public double fm;
    public double fl;
    public double fk;
    public double[] fj;
    public double[] fi;
    public int fh;
    public int fg;
    public int ff;
    public int fe;
    public int fc;
    public int fb;
    public int fa;
    public int e9;
    public int e8;
    public int e7;
    public int e6;
    public int e5;
    public int e4;
    public int e3;
    public int e2;
    public int e1;
    public int e0;
    public int e_;
    public int ez;
    public int ey;
    public int ex;
    public int ew;
    public int ev;
    public int eu;
    public double et;
    public double es;
    public double er;
    public double eq;
    public double ep;
    public double eo;
    public double en;
    public double em;
    public double el;
    public double ek;
    public double ej;
    public double ei;
    public double eh;
    public double eg;
    public double ef;
    public double ee;
    public double ed;
    public ImageProducer ec;
    public int eb;
    public int[][] ea;
    public int d9;
    public int d8;
    public int d7;
    
    public hm35pan() {
        this.hl = false;
        this.hh = 1L;
        this.g5 = true;
        this.g2 = 60.0;
        this.g1 = 0.2;
        this.g_ = 1.0;
        this.gz = 0.2;
        this.gx = 1.3;
        this.gw = 1.0;
        this.gv = 1.0;
        this.gu = 1.0;
        this.gt = 20.0;
        this.gs = 70.0;
        this.gr = 10.0;
        this.gq = 10.0;
        this.gp = 10.0;
        this.ck = -1;
        this.b2 = true;
        this.df = true;
        this.gh = false;
        this.gg = new int[4][];
        this.ge = false;
        this.b4 = 1;
        this.b3 = 1;
        this.b0 = false;
        this.cq = -1;
        this.cp = false;
        this.co = false;
        this.gd = false;
        this.gc = false;
        this.fw = 60.0;
        this.fv = 60.0;
        this.fu = 60.0;
        this.ft = 60.00001;
        this.fr = 1.0E-5;
        this.fo = 360.0;
    }
    
    public final boolean mediaControl(final Object[] array) {
        if (!super.mediaControl(array)) {
            return false;
        }
        final Vector ag = super.ag();
        if (ag != null) {
            double fw = this.fw;
            double fs = this.fs;
            double n = this.fu;
            final Enumeration<String[]> elements = ag.elements();
            while (elements.hasMoreElements()) {
                final String[] array2 = elements.nextElement();
                if (array2 != null) {
                    try {
                        final Double n2 = new Double(array2[1]);
                        if (array2[0].equalsIgnoreCase("NewAz")) {
                            fw = n2;
                        }
                        if (array2[0].equalsIgnoreCase("NewEl")) {
                            fs = -n2;
                        }
                        if (array2[0].equalsIgnoreCase("NewFOV")) {
                            n = n2;
                        }
                    }
                    catch (NumberFormatException ex) {}
                    this.at(n);
                    this.fw = fw;
                    this.fs = fs;
                    this.ay();
                    this.h();
                    this.hd = true;
                    this.pause();
                }
            }
        }
        return true;
    }
    
    public final int zoom(final boolean b, final int n) {
        this.setSensitive(this, 11, 9);
        if (b && n != 0) {
            final double n2 = 0.0;
            this.hc = n2;
            this.hb = n2;
            this.hd = true;
            this.g9 = System.currentTimeMillis();
            this.f6 = 0.0;
            this.f5 = 0.0;
            if (n > 0) {
                if (this.gx != 1.0) {
                    this.ha = 1.0 / this.gx;
                }
            }
            else {
                if (n >= 0) {
                    return -1;
                }
                this.ha = this.gx;
            }
            return 0;
        }
        if (this.hm - this.g9 < 0L) {
            this.gd = true;
            return 0;
        }
        this.hd = false;
        this.ha = 1.0;
        return 0;
    }
    
    public final int pause() {
        if (this.hg == 2) {
            this.hg = 0;
        }
        this.hd = !this.hd;
        int n = 0;
        if (this.hd) {
            this.g9 = System.currentTimeMillis();
            this.f6 = 0.0;
            this.f5 = 0.0;
            if (this.hb == this.gz) {
                n = 3;
            }
            else if (this.hb == -this.gz) {
                n = 10;
            }
        }
        else {
            n = 9;
        }
        this.setSensitive(this, 11, n);
        return 0;
    }
    
    public final int play(final int n) {
        int n2 = 0;
        this.hc = 0.0;
        if (n == -1) {
            this.hb = -this.gz;
            this.hc = -this.gy;
            this.ha = 1.0;
            n2 = 10;
        }
        else if (n == 0) {
            final double g4 = this.g4;
            final double g5 = this.g2;
            final double g6 = this.g3;
            this.at(g5);
            this.fw = g4;
            this.fs = g6;
            this.ay();
            this.hc = this.g0;
            this.hb = this.g1;
            this.ha = 1.0 / this.g_;
            this.h();
            n2 = 9;
        }
        else if (n == 1) {
            this.hb = this.gz;
            this.hc = this.gy;
            this.ha = 1.0;
            n2 = 3;
        }
        this.setSensitive(this, 11, n2);
        this.hd = (this.hb != 0.0 || this.hc != 0.0 || this.ha != 1.0);
        if (this.hd) {
            this.g9 = System.currentTimeMillis();
            this.f6 = 0.0;
            this.f5 = 0.0;
        }
        return 0;
    }
    
    private final synchronized boolean aq(final ImageProducer ec) {
        this.ec = ec;
        this.eb = 0;
        ec.startProduction(this);
        try {
            while (this.eb == 0) {
                this.wait();
            }
        }
        catch (InterruptedException ex) {
            return false;
        }
        return this.eb > 0;
    }
    
    public final synchronized void imageComplete(final int n) {
        switch (n) {
            case 2:
            case 3: {
                this.eb = 1;
                break;
            }
            default: {
                this.eb = -1;
                break;
            }
        }
        this.ec.removeConsumer(this);
        this.notifyAll();
    }
    
    public final void setPixels(final int n, final int n2, final int n3, final int n4, final ColorModel colorModel, final int[] array, final int n5, final int n6) {
        for (int i = 0; i < n4; ++i) {
            for (int n7 = n5 + i * n6, n8 = n + (n2 + i) * this.d8, j = 0; j < n3; ++j, ++n7, ++n8) {
                this.ea[n8 >> 19][n8 & 0x7FFFF] = (array[n7] | 0xFF000000);
            }
        }
    }
    
    public final void setPixels(final int n, final int n2, final int n3, final int n4, final ColorModel colorModel, final byte[] array, final int n5, final int n6) {
        for (int i = 0; i < n4; ++i) {
            for (int n7 = n5 + i * n6, n8 = n + (n2 + i) * this.d8, j = 0; j < n3; ++j, ++n7, ++n8) {
                this.ea[n8 >> 19][n8 & 0x7FFFF] = ((colorModel.getAlpha(array[n7] & 0xFF) & 0xFF) << 24 | (colorModel.getRed(array[n7] & 0xFF) & 0xFF) << 16 | (colorModel.getGreen(array[n7] & 0xFF) & 0xFF) << 8 | (colorModel.getBlue(array[n7] & 0xFF) & 0xFF));
            }
        }
    }
    
    public final void setHints(final int n) {
    }
    
    public final void setColorModel(final ColorModel colorModel) {
    }
    
    public final void setProperties(final Hashtable hashtable) {
    }
    
    public final void setDimensions(final int d8, final int d9) {
        this.d8 = d8;
        this.d7 = d9;
        this.d9 = d8 * d9;
        final int n = this.d9 >> 19;
        this.ea = new int[n + 1][];
        int i;
        for (i = 0; i < n; ++i) {
            this.ea[i] = new int[524288];
        }
        this.ea[i] = new int[this.d9 & 0x7FFFF];
    }
    
    private final Point ar(final int n, final int n2) {
        final double n3 = this.fs * 0.017453292519943295;
        final double n4 = this.fl * 0.017453292519943295;
        final double n5 = this.fw * 0.017453292519943295;
        final double n6 = this.fu * 0.017453292519943295;
        final double n7 = this.d8 / (this.fo * 0.017453292519943295);
        final double n8 = this.d7 * 0.5 - this.fk * n7;
        final double tan = Math.tan(n6 / 2.0);
        final double n9 = 2.0 * tan / this.b4;
        final double n10 = n7 * n9;
        final double n11 = n8 * (n7 * Math.tan(n3 - n4)) / n7 - 0.5 * n10 * this.b3;
        double n12 = n / n7 - n5;
        if (n5 > 3.141592653589793) {
            n12 += 6.283185307179586;
        }
        if (!this.gc) {
            n12 = ((n12 < -1.5) ? -1.5 : ((n12 > 1.5) ? 1.5 : n12));
        }
        return new Point((int)(((this.gc ? n12 : Math.tan(n12)) + tan) / n9), (int)(((n2 - n8) / (this.gc ? 1.0 : Math.cos(n12)) - n11) / n10));
    }
    
    private final Point as(final int n, final int n2) {
        final double n3 = this.fl * 0.017453292519943295;
        final double n4 = this.fs * 0.017453292519943295;
        final double n5 = this.fw * 0.017453292519943295;
        final double n6 = this.fu * 0.017453292519943295;
        final double n7 = this.d8 / 6.283185307179586 * 360.0 / this.fo;
        final double tan = Math.tan(n6 / 2.0);
        final double n8 = 2.0 * tan / this.b4;
        final double n9 = n7 * n8;
        final double n10 = this.d7 * 0.5 - this.fk * n7;
        final double n11 = n7 * Math.tan(n4 - n3);
        final double n12 = -tan + n * n8;
        final double n13 = this.gc ? n12 : Math.atan(n12);
        double n14 = n13 + n5;
        if (n14 < 0.0) {
            n14 += 6.283185307179586;
        }
        int n15 = (int)(n14 * n7);
        if (n15 > this.d8) {
            n15 -= this.d8;
        }
        return new Point(n15, (int)(n10 + (n9 * n2 + (n10 * n11 / n7 - 0.5 * n9 * this.b3)) * (this.gc ? 1.0 : Math.cos(n13))));
    }
    
    private final void at(final double fu) {
        final int fp = this.fp;
        this.fu = fu;
        this.fp = 0;
        if (this.fu <= this.gt) {
            this.fu = this.gt;
            this.ha = 1.0;
            this.fp |= 0x10;
        }
        else if (this.fu >= this.gs) {
            this.fu = this.gs;
            this.ha = 1.0;
            this.fp |= 0x20;
        }
        else {
            this.fp |= 0x30;
        }
        if (this.fp != fp) {
            this.setSensitive(this, 48, this.fp);
        }
        this.fq = 114.59155902616465 * Math.atan(this.b3 * Math.tan(this.fu * 0.008726646259971648) / this.b4);
    }
    
    private final void au(final double n) {
        this.fs -= n;
        if (this.fs < -89.0) {
            this.fs = -89.0;
        }
        if (this.fs > 89.0) {
            this.fs = 89.0;
        }
    }
    
    private final void av(final double n) {
        this.fw += n;
        if (this.fw < 0.0) {
            this.fw += 360.0;
        }
        if (this.fw >= 360.0) {
            this.fw -= 360.0;
        }
    }
    
    private final double aw(final double n, final double n2, final double n3) {
        boolean b = false;
        boolean b2 = false;
        double n4 = n3 * Math.tan((this.fs + -this.fl) * 0.017453292519943295);
        final double n5 = n - this.fk * n3;
        final double n6 = n * 2.0 - n5;
        if (n2 - n5 * n4 / n3 > n5) {
            n4 = (n2 - n5) * n3 / n5;
            b = true;
            this.hc = -this.hc;
            if (this.hg == 1) {
                this.go = true;
            }
            if (this.go) {
                this.hc = 0.0;
            }
            else if (this.hb == 0.0) {
                this.setSensitive(this, 11, 10);
            }
            if (n2 + n5 * n4 / n3 > n6) {
                n4 = n - n5;
                b2 = true;
                this.hc = 0.0;
            }
        }
        else if (n2 + n5 * n4 / n3 > n6) {
            n4 = (n6 - n2) * n3 / n5;
            if (this.hg == 1) {
                this.go = true;
            }
            b = true;
            this.hc = -this.hc;
            if (this.go) {
                this.hc = 0.0;
            }
            else if (this.hb == 0.0) {
                this.setSensitive(this, 11, 3);
            }
            if (n2 - n5 * n4 / n3 > n5) {
                n4 = n - n5;
                b2 = true;
                this.hc = 0.0;
            }
        }
        if (b2) {
            n4 = 0.0;
            b = true;
            this.at(114.59155902616465 * Math.atan2(this.b4, this.b3 * Math.tan(Math.atan2(n3, n))));
        }
        if (b) {
            this.fs = Math.atan2(n4, n3) / 0.017453292519943295 - -this.fl;
        }
        return n4;
    }
    
    private final void ax(final double n) {
        if (this.fu > n) {
            this.at(n);
            this.fw = 0.5 * n;
            return;
        }
        if (n >= 360.0) {
            return;
        }
        if (this.fu == 180.0) {
            return;
        }
        if (this.b4 / this.b3 / (this.d8 / this.d7) > 0.99 && this.fu > n / 2.5) {
            this.at(n / 2.5);
        }
        if (!this.gc) {
            this.go = false;
            boolean b = false;
            boolean b2 = false;
            while (this.fw + this.fu / 2.0 > n || this.fw - this.fu / 2.0 < 0.0) {
                b = true;
                if (this.hg == 1) {
                    this.go = true;
                }
                if (this.hg == 0) {
                    this.go = false;
                }
                if (this.fw + this.fu / 2.0 > n) {
                    b2 = true;
                    this.fw = n - this.fu / 2.0;
                }
                if (this.fw - this.fu / 2.0 < 0.0) {
                    b2 = false;
                    this.fw = this.fu / 2.0;
                }
                if (this.fw + this.fu / 2.0 > n || this.fw - this.fu / 2.0 < 0.0) {
                    --this.fu;
                }
            }
            if (b) {
                this.hb = -this.hb;
                if (this.go) {
                    this.hb = 0.0;
                    return;
                }
                if (b2) {
                    this.setSensitive(this, 11, 3);
                    return;
                }
                this.setSensitive(this, 11, 10);
            }
        }
        else {
            double n2 = this.a5(0, 0).x;
            double n3 = this.a5((int)(this.b4 / 2.0), 0).x;
            double n4 = this.a5(this.b4, 0).x;
            if (n3 < n2) {
                this.hb = Math.abs(this.hb);
                if (this.hg == 1) {
                    this.go = true;
                }
                if (this.hg == 0) {
                    this.go = false;
                }
                if (this.go) {
                    this.hb = 0.0;
                }
                else {
                    this.setSensitive(this, 11, 3);
                }
                while (n3 < n2) {
                    ++this.fw;
                    n2 = this.a5(0, 0).x;
                    n3 = this.a5((int)(this.b4 / 2.0), 0).x;
                }
                return;
            }
            if (n4 < n3) {
                this.hb = -Math.abs(this.hb);
                if (this.hg == 1) {
                    this.go = true;
                }
                if (this.hg == 0) {
                    this.go = false;
                }
                if (this.go) {
                    this.hb = 0.0;
                }
                else {
                    this.setSensitive(this, 11, 10);
                }
                while (n4 < n3) {
                    --this.fw;
                    n3 = this.a5((int)(this.b4 / 2.0), 0).x;
                    n4 = this.a5(this.b4, 0).x;
                }
            }
        }
    }
    
    private final void ay() {
        this.ax(this.fo);
        final double n = this.d8 / 6.283185307179586 * 360.0 / this.fo;
        final double n2 = n * (2.0 * Math.tan(this.fu * 0.008726646259971648) / this.b4);
        if (!this.gc) {
            this.aw(this.d7 * 0.5 - 2.0, 0.5 * n2 * this.b3, n);
            return;
        }
        this.aw(this.d7 * 0.5, 0.5 * n2 * this.b3, n);
    }
    
    private final int az(int n) {
        n = ((n > this.d9) ? (this.d9 - 1) : ((n < 0) ? 0 : n));
        return this.ea[n >> 19][n & 0x7FFFF];
    }
    
    private final void a_() {
        try {
            this.en = (this.d8 - 1) / 6.283185307179586 * 360.0 / this.fo;
            this.ej = 0.017453292519943295 * (this.fw - this.fv);
            int n = (int)(this.ej * this.en);
            if (n < 0) {
                n += this.d8;
            }
            final int n2 = n % this.d8;
            int b4 = this.b4;
            int b5 = this.b4;
            int b6 = this.b4;
            int b7 = this.b4;
            if (this.hl) {
                for (int i = 0; i < this.b4; ++i) {
                    if (this.f_[i] % this.d8 == this.d8 - 1) {
                        if (b6 == this.b4) {
                            b6 = i;
                        }
                        b7 = i;
                    }
                }
                for (int j = 0; j < this.b4; ++j) {
                    int n3 = 0;
                    final int n4 = 0;
                    if (j > b6) {
                        final int n5 = n4 - this.d8;
                    }
                    if (j >= b7) {
                        n3 -= this.d8;
                    }
                    int n6 = j;
                    this.fb = 0;
                    while (this.fb < this.b3) {
                        this.e5 = this.f_[n6] + n2 - n3;
                        this.b_[n6] = this.az(this.e5);
                        n6 += this.b4;
                        ++this.fb;
                    }
                }
            }
            else {
                for (int k = 0; k < this.b4; ++k) {
                    if (this.f_[k] % this.d8 == this.d8 - 1) {
                        if (b6 == this.b4) {
                            b6 = k;
                        }
                        b7 = k;
                    }
                    if ((this.f_[k] + n2) % this.d8 == this.d8 - 1 && (this.fz[k] + n2) % this.d8 == 0) {
                        if (b4 == this.b4) {
                            b4 = k;
                        }
                        b5 = k;
                    }
                }
                for (int l = 0; l < this.b4; ++l) {
                    int d8;
                    int n7;
                    if (l > b5) {
                        d8 = this.d8;
                        n7 = this.d8;
                    }
                    else if (l >= b4 && l <= b5) {
                        d8 = 0;
                        n7 = this.d8;
                    }
                    else {
                        d8 = 0;
                        n7 = 0;
                    }
                    if (l > b6) {
                        n7 -= this.d8;
                    }
                    if (l >= b7) {
                        d8 -= this.d8;
                    }
                    if (b5 <= b7) {
                        d8 -= this.d8;
                        n7 -= this.d8;
                    }
                    int n8 = l;
                    this.fb = 0;
                    while (this.fb < this.b3) {
                        this.e5 = this.f_[n8] + n2 - d8;
                        this.e4 = this.fz[n8] + n2 - n7;
                        this.e3 = this.fy[n8] + n2 - d8;
                        this.e2 = this.fx[n8] + n2 - n7;
                        this.e9 = this.az(this.e5);
                        this.e8 = this.az(this.e4);
                        this.e7 = this.az(this.e3);
                        this.e6 = this.az(this.e2);
                        this.e1 = this.f3[n8];
                        this.e0 = this.f2[n8];
                        this.e_ = this.f1[n8];
                        this.ez = this.f0[n8];
                        this.ey = ((this.e9 & 0xFF0000) * this.e1 + (this.e8 & 0xFF0000) * this.e0 + (this.e7 & 0xFF0000) * this.e_ + (this.e6 & 0xFF0000) * this.ez >> 8 & 0xFF0000);
                        this.ex = ((this.e9 & 0xFF00) * this.e1 + (this.e8 & 0xFF00) * this.e0 + (this.e7 & 0xFF00) * this.e_ + (this.e6 & 0xFF00) * this.ez >> 8 & 0xFF00);
                        this.ew = ((this.e9 & 0xFF) * this.e1 + (this.e8 & 0xFF) * this.e0 + (this.e7 & 0xFF) * this.e_ + (this.e6 & 0xFF) * this.ez >> 8 & 0xFF);
                        this.b_[n8] = (this.ey | this.ex | this.ew | 0xFF000000);
                        n8 += this.b4;
                        ++this.fb;
                    }
                }
            }
            if (this.f4 != null) {
                this.f4.setPixels(0, 0, this.b4, this.b3, ColorModel.getRGBdefault(), this.b_, 0, this.b4);
                this.f4.imageComplete(2);
            }
        }
        catch (Error error) {}
        catch (Exception ex) {}
    }
    
    private final void h() {
        try {
            final int b4 = this.b4;
            final int b5 = this.b3;
            final int d8 = this.d8;
            this.en = (d8 - 1) / 6.283185307179586 * 360.0 / this.fo;
            this.em = Math.tan(this.fu * 0.008726646259971648);
            this.el = 2.0 * this.em / b4;
            this.ek = this.en * this.el;
            this.ej = 0.017453292519943295 * this.fw;
            this.ei = this.d7 * 0.5 - this.fk * this.en;
            this.eh = this.en * Math.tan((this.fs + -this.fl) * 0.017453292519943295);
            this.eg = 0.5 * this.ek * b5;
            final double n = this.eg - this.eh;
            final double n2 = this.eg + this.eh;
            this.ef = -this.em;
            this.ev = 0;
            this.eu = b4 / 2 + 1;
            while (this.ev < b4) {
                this.ee = (this.gc ? this.ef : Math.atan(this.ef));
                this.ed = (this.ee + this.ej) * this.en;
                if (this.ed < 0.0) {
                    this.ed += d8;
                }
                if (this.ed >= d8) {
                    this.ed -= d8;
                }
                this.fj[this.ev] = this.ed;
                if (this.ev < this.eu) {
                    this.fi[this.ev] = Math.cos(this.ee);
                }
                this.ef += this.el;
                ++this.ev;
            }
            this.ef = -this.em;
            final int[] b_ = this.b_;
            this.er = this.ei * this.eh / this.en - 0.5 * this.ek * b5;
            if (this.gc) {
                this.fb = 0;
                while (this.fb < b5) {
                    this.et = this.ek * this.fb + this.er;
                    this.fh = Math.max(0, b4 * this.fb);
                    this.fc = (int)(this.ei + this.et) * d8;
                    this.ff = 0;
                    while (this.ff < b4) {
                        this.fa = (int)(this.fj[this.ff] + this.fc);
                        if (this.fa < 0 || this.fa >= this.d9) {
                            b_[this.fh] = -16777216;
                        }
                        else {
                            b_[this.fh] = this.az(this.fa);
                        }
                        ++this.fh;
                        ++this.ff;
                    }
                    ++this.fb;
                }
            }
            else {
                if (this.hl) {
                    this.fb = 0;
                    while (this.fb < b5) {
                        this.et = this.ek * this.fb + this.er;
                        this.fh = b4 * this.fb;
                        this.fg = this.fh + b4 - 1;
                        this.ff = 0;
                        this.fe = b4 - 1;
                        while (this.ff < this.eu) {
                            this.es = this.ei + this.et * this.fi[this.ff];
                            this.fc = (int)this.es * this.d8;
                            this.eq = this.es - (int)this.es;
                            this.e5 = (int)this.fj[this.ff] + this.fc;
                            if (this.e5 < 0 || this.e5 >= this.d9) {
                                b_[this.fh] = (b_[this.fg] = -16777216);
                                this.f_[this.fh] = (this.f_[this.fg] = 0);
                            }
                            else {
                                this.f_[this.fh] = this.e5;
                                this.b_[this.fh] = this.az(this.e5);
                            }
                            this.e5 = (int)this.fj[this.fe] + this.fc;
                            if (this.e5 < 0 || this.e5 >= this.d9) {
                                this.b_[this.fh] = (this.b_[this.fg] = -16777216);
                                this.f_[this.fh] = (this.f_[this.fg] = 0);
                            }
                            else {
                                this.f_[this.fg] = this.e5;
                                this.b_[this.fg] = this.az(this.e5);
                            }
                            ++this.fh;
                            --this.fg;
                            ++this.ff;
                            --this.fe;
                        }
                        ++this.fb;
                    }
                }
                else {
                    this.fb = 0;
                    while (this.fb < b5) {
                        this.et = this.ek * this.fb + this.er;
                        this.fh = b4 * this.fb;
                        this.fg = this.fh + b4 - 1;
                        this.ff = 0;
                        this.fe = b4 - 1;
                        while (this.ff < this.eu) {
                            this.es = this.ei + this.et * this.fi[this.ff];
                            this.fc = (int)this.es * this.d8;
                            this.eq = this.es - (int)this.es;
                            this.eo = this.fj[this.ff] + this.fc;
                            this.e5 = (int)this.fj[this.ff] + this.fc;
                            this.e4 = this.e5 + 1;
                            if (this.fj[this.ff] >= this.d8 - 1) {
                                this.e4 -= this.d8;
                            }
                            this.e3 = this.e5 + this.d8;
                            this.e2 = this.e5 + this.d8 + 1;
                            if (this.fj[this.ff] >= this.d8 - 1) {
                                this.e2 -= this.d8;
                            }
                            if (this.e4 >= this.d9) {
                                this.e4 = this.e5;
                            }
                            if (this.e3 >= this.d9) {
                                this.e3 = this.e5;
                            }
                            if (this.e2 >= this.d9) {
                                this.e2 = this.e5;
                            }
                            if (this.e5 < 0 || this.e5 >= this.d9) {
                                b_[this.fh] = (b_[this.fg] = -16777216);
                            }
                            else {
                                this.ep = this.eo - this.e5;
                                this.e9 = this.az(this.e5);
                                this.e8 = this.az(this.e4);
                                this.e7 = this.az(this.e3);
                                this.e6 = this.az(this.e2);
                                this.e1 = (int)(256.0 * (1.0 - this.ep) * (1.0 - this.eq));
                                this.e0 = (int)(256.0 * (0.0 + this.ep) * (1.0 - this.eq));
                                this.e_ = (int)(256.0 * (1.0 - this.ep) * (0.0 + this.eq));
                                this.ez = (int)(256.0 * (0.0 + this.ep) * (0.0 + this.eq));
                                this.f_[this.fh] = this.e5;
                                this.fz[this.fh] = this.e4;
                                this.fy[this.fh] = this.e3;
                                this.fx[this.fh] = this.e2;
                                this.f3[this.fh] = this.e1;
                                this.f2[this.fh] = this.e0;
                                this.f1[this.fh] = this.e_;
                                this.f0[this.fh] = this.ez;
                                this.ey = ((this.e9 & 0xFF0000) * this.e1 + (this.e8 & 0xFF0000) * this.e0 + (this.e7 & 0xFF0000) * this.e_ + (this.e6 & 0xFF0000) * this.ez >> 8 & 0xFF0000);
                                this.ex = ((this.e9 & 0xFF00) * this.e1 + (this.e8 & 0xFF00) * this.e0 + (this.e7 & 0xFF00) * this.e_ + (this.e6 & 0xFF00) * this.ez >> 8 & 0xFF00);
                                this.ew = ((this.e9 & 0xFF) * this.e1 + (this.e8 & 0xFF) * this.e0 + (this.e7 & 0xFF) * this.e_ + (this.e6 & 0xFF) * this.ez >> 8 & 0xFF);
                                this.b_[this.fh] = (this.ey | this.ex | this.ew | 0xFF000000);
                            }
                            this.eo = this.fj[this.fe] + this.fc;
                            this.e5 = (int)this.fj[this.fe] + this.fc;
                            this.e4 = this.e5 + 1;
                            if (this.fj[this.fe] >= this.d8 - 1) {
                                this.e4 -= this.d8;
                            }
                            this.e3 = this.e5 + this.d8;
                            this.e2 = this.e5 + this.d8 + 1;
                            if (this.fj[this.fe] >= this.d8 - 1) {
                                this.e2 -= this.d8;
                            }
                            if (this.e4 >= this.d9) {
                                this.e4 = this.e5;
                            }
                            if (this.e3 >= this.d9) {
                                this.e3 = this.e5;
                            }
                            if (this.e2 >= this.d9) {
                                this.e2 = this.e5;
                            }
                            if (this.e5 < 0 || this.e5 >= this.d9) {
                                b_[this.fh] = (b_[this.fg] = -16777216);
                            }
                            else {
                                this.ep = this.eo - this.e5;
                                this.e9 = this.az(this.e5);
                                this.e8 = this.az(this.e4);
                                this.e7 = this.az(this.e3);
                                this.e6 = this.az(this.e2);
                                this.e1 = (int)(256.0 * (1.0 - this.ep) * (1.0 - this.eq));
                                this.e0 = (int)(256.0 * (0.0 + this.ep) * (1.0 - this.eq));
                                this.e_ = (int)(256.0 * (1.0 - this.ep) * (0.0 + this.eq));
                                this.ez = (int)(256.0 * (0.0 + this.ep) * (0.0 + this.eq));
                                this.f_[this.fg] = this.e5;
                                this.fz[this.fg] = this.e4;
                                this.fy[this.fg] = this.e3;
                                this.fx[this.fg] = this.e2;
                                this.f3[this.fg] = this.e1;
                                this.f2[this.fg] = this.e0;
                                this.f1[this.fg] = this.e_;
                                this.f0[this.fg] = this.ez;
                                this.ey = ((this.e9 & 0xFF0000) * this.e1 + (this.e8 & 0xFF0000) * this.e0 + (this.e7 & 0xFF0000) * this.e_ + (this.e6 & 0xFF0000) * this.ez >> 8 & 0xFF0000);
                                this.ex = ((this.e9 & 0xFF00) * this.e1 + (this.e8 & 0xFF00) * this.e0 + (this.e7 & 0xFF00) * this.e_ + (this.e6 & 0xFF00) * this.ez >> 8 & 0xFF00);
                                this.ew = ((this.e9 & 0xFF) * this.e1 + (this.e8 & 0xFF) * this.e0 + (this.e7 & 0xFF) * this.e_ + (this.e6 & 0xFF) * this.ez >> 8 & 0xFF);
                                this.b_[this.fg] = (this.ey | this.ex | this.ew | 0xFF000000);
                            }
                            ++this.fh;
                            --this.fg;
                            ++this.ff;
                            --this.fe;
                        }
                        ++this.fb;
                    }
                }
                this.fv = this.fw;
            }
            if (this.f4 != null) {
                this.f4.setPixels(0, 0, this.b4, this.b3, ColorModel.getRGBdefault(), this.b_, 0, this.b4);
                this.f4.imageComplete(2);
            }
            this.fr = this.fs;
            this.ft = this.fu;
        }
        catch (Error error) {
            error.printStackTrace();
        }
        catch (Exception ex) {}
    }
    
    public final void requestTopDownLeftRightResend(final ImageConsumer imageConsumer) {
    }
    
    public final void startProduction(final ImageConsumer imageConsumer) {
        this.addConsumer(imageConsumer);
    }
    
    public final synchronized void removeConsumer(final ImageConsumer imageConsumer) {
        if (imageConsumer == this.f4) {
            this.f4 = null;
        }
    }
    
    public final synchronized boolean isConsumer(final ImageConsumer imageConsumer) {
        return imageConsumer == this.f4;
    }
    
    private final void a0(final ImageConsumer imageConsumer) {
        if (this.isConsumer(imageConsumer)) {
            imageConsumer.setDimensions(this.b4, this.b3);
            imageConsumer.setColorModel(ColorModel.getRGBdefault());
            imageConsumer.setHints(14);
        }
    }
    
    public final synchronized void addConsumer(final ImageConsumer f4) {
        if (this.f4 == null) {
            this.f4 = f4;
        }
        try {
            this.a0(f4);
            this.h();
        }
        catch (Exception ex) {
            if (this.isConsumer(f4)) {
                f4.imageComplete(1);
            }
        }
    }
    
    private final void a1() {
        this.fo = this.fm - this.fn;
        if (this.fo <= 0.0) {
            this.fo += 360.0;
        }
        this.fj = new double[this.b4 + 1];
        this.fi = new double[this.b4 / 2 + 1];
        this.b_ = new int[this.b4 * this.b3];
        this.f_ = new int[this.b4 * this.b3];
        this.fz = new int[this.b4 * this.b3];
        this.fy = new int[this.b4 * this.b3];
        this.fx = new int[this.b4 * this.b3];
        this.f3 = new int[this.b4 * this.b3];
        this.f2 = new int[this.b4 * this.b3];
        this.f1 = new int[this.b4 * this.b3];
        this.f0 = new int[this.b4 * this.b3];
    }
    
    private final byte[] o(final DataInputStream dataInputStream) {
        try {
            dataInputStream.readInt();
            final byte[] array = new byte[dataInputStream.readInt()];
            dataInputStream.readFully(array);
            return array;
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public final boolean endOfData(final byte b) {
        return this.b0 = true;
    }
    
    public final int consumeFrame(final byte b, final int n, final byte b2, final short n2, final int n3, final int n4, final DataInputStream dataInputStream) {
        if (this.b0) {
            return 0;
        }
        try {
            switch (b2) {
                case 0: {
                    final Image image = super.createImage(super.mediaSubType, this.o(dataInputStream));
                    final MediaTracker mediaTracker = new MediaTracker(this);
                    mediaTracker.addImage(image, 0);
                    mediaTracker.waitForID(0);
                    this.hp = image;
                    break;
                }
                case 1: {
                    final byte byte1 = dataInputStream.readByte();
                    if ((dataInputStream.readByte() & 0x1) == 0x1) {
                        this.gc = true;
                    }
                    if (this.gc) {
                        final double n5 = 180.0;
                        this.gs = n5;
                        this.g2 = n5;
                        this.gt = 0.67 * this.gs;
                    }
                    this.fn = dataInputStream.readUnsignedShort() / 100.0;
                    this.fm = dataInputStream.readUnsignedShort() / 100.0;
                    final double n6 = dataInputStream.readShort() / 100.0;
                    final double n7 = dataInputStream.readShort() / 100.0;
                    this.fl = dataInputStream.readShort() / 100.0;
                    if (this.fl < -99.0 || this.gc) {
                        this.fl = 0.0;
                    }
                    this.fk = Math.tan(-this.fl * 0.017453292519943295);
                    dataInputStream.readFully(new byte[dataInputStream.readUnsignedShort()]);
                    if (byte1 == 0 && this.fn == 0.0) {}
                    break;
                }
                case 2: {
                    short short2;
                    for (int i = n3; i > 0; i -= short2) {
                        final short short1 = dataInputStream.readShort();
                        short2 = dataInputStream.readShort();
                        switch (short1) {
                            case 0: {
                                this.gr = dataInputStream.readShort() / 1000.0;
                                this.gq = dataInputStream.readShort() / 1000.0;
                                this.gp = dataInputStream.readShort() / 1000.0;
                                dataInputStream.readFully(new byte[short2 - 10]);
                                break;
                            }
                            case 1: {
                                this.gt = dataInputStream.readShort() / 100.0;
                                this.gs = dataInputStream.readShort() / 100.0;
                                dataInputStream.readFully(new byte[short2 - 8]);
                                break;
                            }
                            case 2: {
                                dataInputStream.readShort();
                                dataInputStream.readShort();
                                this.gx = dataInputStream.readShort() / 1000.0;
                                dataInputStream.readFully(new byte[short2 - 10]);
                                break;
                            }
                            case 3: {
                                this.gw = dataInputStream.readShort() / 100.0;
                                this.gv = dataInputStream.readShort() / 100.0;
                                this.gu = dataInputStream.readShort() / 100.0;
                                dataInputStream.readFully(new byte[short2 - 10]);
                                break;
                            }
                            case 4: {
                                this.g1 = dataInputStream.readShort() / 1000.0;
                                this.gz = Math.abs(this.g1);
                                this.g0 = dataInputStream.readShort() / 1000.0;
                                this.gy = Math.abs(this.g0);
                                this.g_ = dataInputStream.readShort() / 1000.0;
                                dataInputStream.readFully(new byte[short2 - 10]);
                                break;
                            }
                            case 5: {
                                this.g4 = dataInputStream.readShort() / 100.0;
                                this.g3 = dataInputStream.readShort() / 100.0;
                                this.g2 = dataInputStream.readShort() / 100.0;
                                dataInputStream.readFully(new byte[short2 - 10]);
                                break;
                            }
                            default: {
                                dataInputStream.readFully(new byte[short2 - 4]);
                                break;
                            }
                        }
                    }
                    break;
                }
                default: {
                    dataInputStream.readFully(new byte[n3]);
                    break;
                }
            }
        }
        catch (Exception ex) {
            return -1;
        }
        return n3;
    }
    
    private final void a2() {
        this.ge = (this.ck != -1);
        if (this.cr != this.ck) {
            this.cr = this.ck;
            this.cs = null;
            this.repaint();
        }
    }
    
    private final synchronized void m(final int n, final int[][] array) {
        final Vector spatialVector = this.getSpatialVector(n, 0);
        final Vector spatialVector2 = this.getSpatialVector(n, 1);
        final int size = spatialVector.size();
        if (array[0] == null || array[0].length < 2 * size) {
            for (int i = 0; i < 4; ++i) {
                array[i] = new int[2 * size];
            }
        }
        this.gf = 0;
        Point point = null;
        Point point2 = null;
        final boolean b = false;
        this.gk = (b ? 1 : 0);
        this.gl = (b ? 1 : 0);
        for (int j = 0; j < size + 1; ++j) {
            final int intValue = spatialVector.elementAt(j % size);
            final int intValue2 = spatialVector2.elementAt(j % size);
            if (j < size) {
                this.gl += intValue;
                this.gk += intValue2;
            }
            final Point ar = this.ar(intValue * this.d8 / 9999, intValue2 * this.d7 / 9999);
            if (ar != null && point != null) {
                array[0][this.gf] = point.x;
                array[1][this.gf] = point.y;
                array[2][this.gf] = ar.x;
                array[3][this.gf] = ar.y;
                ++this.gf;
            }
            point = ar;
            final Point ar2 = this.ar((intValue - 10000) * this.d8 / 9999, intValue2 * this.d7 / 9999);
            if (ar2 != null && point2 != null) {
                array[0][this.gf] = point2.x;
                array[1][this.gf] = point2.y;
                array[2][this.gf] = ar2.x;
                array[3][this.gf] = ar2.y;
                ++this.gf;
            }
            point2 = ar2;
        }
        this.gl /= size;
        this.gk /= size;
        final Point ar3 = this.ar(this.gl * this.d8 / 9999, this.gk * this.d7 / 9999);
        this.gl = ar3.x;
        this.gk = ar3.y;
    }
    
    private final int n(final byte b, final int n, final int n2, final int n3, final int n4) {
        int n5 = n;
        int n6 = n2;
        int n7 = n3;
        int n8 = n4;
        int n9 = 100000000;
        int ck = -1;
        if (super.masterObject != null && super.masterObject.ik != null) {
            if (b == 4 || b == 5 || b == 12 || b == 100 || b == 101 || b == 127) {
                if (this.d8 == 0 || this.d7 == 0) {
                    if (this.b4 == 0 || this.b3 == 0) {
                        return 0;
                    }
                    this.d8 = this.b4;
                    this.d7 = this.b3;
                }
                if (b == 101) {
                    n5 = 9999 * (n - this.b6) / this.d8;
                    n6 = 9999 * (n2 - this.b5) / this.d7;
                    n7 = 9999 * (n3 - this.b6) / this.d8;
                    n8 = 9999 * (n4 - this.b5) / this.d7;
                }
                else if (b != 5) {
                    n5 = 9999 * (n - this.b6 + this.gn) / this.d8;
                    n6 = 9999 * (n2 - this.b5) / this.d7;
                }
            }
            final Vector<Integer> vector = new Vector<Integer>();
            final Vector<Integer> vector2 = new Vector<Integer>();
            vector.addElement(new Integer(n5));
            vector2.addElement(new Integer(n6));
            vector.addElement(new Integer(n7));
            vector2.addElement(new Integer(n6));
            vector.addElement(new Integer(n7));
            vector2.addElement(new Integer(n8));
            vector.addElement(new Integer(n5));
            vector2.addElement(new Integer(n8));
            int n10;
            if (b == 101) {
                n10 = 4;
            }
            else {
                n10 = 2;
            }
            Vector vector3 = this.handleMediaEvent(this, super.trackID, b, 0, n10, vector, vector2);
            if (this.ck == -1 && this.fo == 360.0 && vector3.size() == 0 && (b == 127 || b == 100)) {
                this.gn = this.d8 - this.gn;
                vector.setElementAt(new Integer(n5 + this.gn / this.d8 * 9999), 0);
                vector3 = this.handleMediaEvent(this, super.trackID, b, 0, n10, vector, vector2);
            }
            int n11 = -1;
            for (int i = 0; i < vector3.size(); ++i) {
                final int intValue = vector3.elementAt(i);
                final int actionType = this.getActionType(intValue);
                final int actionSubType = this.getActionSubType(intValue);
                switch (actionType) {
                    case 6: {
                        final Vector spatialVector = this.getSpatialVector(intValue, 0);
                        final Vector spatialVector2 = this.getSpatialVector(intValue, 1);
                        int n12 = 100000000;
                        int n13 = 100000000;
                        int n14 = -1;
                        int n15 = -1;
                        for (int j = 0; j < spatialVector.size(); ++j) {
                            final int intValue2 = spatialVector.elementAt(j);
                            if (intValue2 < n12) {
                                n12 = intValue2;
                            }
                            if (intValue2 > n14) {
                                n14 = intValue2;
                            }
                            final int intValue3 = spatialVector2.elementAt(j);
                            if (intValue3 < n13) {
                                n13 = intValue3;
                            }
                            if (intValue3 > n15) {
                                n15 = intValue3;
                            }
                        }
                        final int n16 = (int)Math.sqrt((n14 - n12) * (n14 - n12)) + (int)Math.sqrt((n15 - n13) * (n15 - n13)) + Math.abs(n12 + (n14 - n12) / 2 - n5) + Math.abs(n13 + (n15 - n13) / 2 - n6);
                        if (n16 >= n9) {
                            continue;
                        }
                        n9 = n16;
                        ck = intValue;
                        if (actionSubType == 2) {
                            n11 = intValue;
                        }
                        continue;
                    }
                    case 0: {
                        if (this.getActionDataLength(intValue) > 1 && this.hd) {
                            this.pause();
                            break;
                        }
                        break;
                    }
                }
                if ((b == 12 && this.getRangeIndexForAction(intValue) == this.getRangeIndexForAction(this.ck)) || b != 12) {
                    this.handleAction(intValue);
                }
            }
            if (b == 127) {
                if (!this.b2 && n9 != 100000000) {
                    this.ck = ck;
                    if (this.ck != this.cr) {
                        this.cs = null;
                    }
                    this.m(ck, this.gg);
                    if (n11 != -1) {
                        this.cs = this.getDisplayRecord(n11);
                    }
                }
                else {
                    this.ck = -1;
                    this.cs = null;
                }
            }
            return 1;
        }
        this.ck = -1;
        return 0;
    }
    
    public final int init(final hm35master hm35master) {
        super.init(hm35master);
        this.df = super.getBoolParam("ShowALink", this.df);
        this.gh = super.getBoolParam("ShowATrackSwitch", this.gh);
        this.gc = super.getBoolParam("FlatPan", this.gc);
        this.hl = super.getBoolParam("FasterProjection", this.hl);
        super.startThread(null);
        return 1;
    }
    
    public final boolean a3(final double n) {
        this.ga = 620.0 / (this.b4 + this.b3);
        this.f9 = this.fu / 60.0;
        this.f8 = System.currentTimeMillis();
        this.f7 = false;
        if (this.hd) {
            if (this.hg == 2) {
                this.hg = 0;
                this.hd = false;
            }
            if (this.hb != 0.0 || this.ha != 1.0 || this.hc != 0.0) {
                this.f7 = true;
                if (this.f5 < 10.0 || this.hg == 0) {
                    this.f6 += n;
                    ++this.f5;
                }
                final double n2 = this.f6 / this.f5;
                this.av(this.hb * this.fu * n2);
                this.au(this.hc * this.fq * n2);
                if (this.ha != 1.0) {
                    this.at(this.fu * Math.pow(this.ha, n2));
                }
                this.ay();
                if (this.hk) {
                    this.a4();
                }
                this.ge = (this.ck != -1);
                this.cr = this.ck;
                if (this.fr != this.fs || this.ft != this.fu || this.gc || this.hi / this.hh < 80L) {
                    this.h();
                    this.hi += this.b8 + this.b7 + (System.currentTimeMillis() - this.f8);
                    ++this.hh;
                }
                else {
                    this.a_();
                }
            }
        }
        else {
            this.ga *= 2.0;
            if (this.g8 != 0) {
                this.av(this.gw * this.ga * this.f9 * this.g8 * 0.5);
                this.g8 = 0;
                this.f7 = true;
            }
            if (this.g7 != 0) {
                final double n3 = this.gu * this.g7 * 0.01;
                this.at(this.fu * ((n3 >= 0.0) ? (1.0 + n3) : (1.0 / (1.0 - n3))));
                this.g7 = 0;
                this.f7 = true;
            }
            if (this.g6 != 0) {
                this.au(this.gv * this.ga * this.f9 * this.g6 * -0.25);
                this.g6 = 0;
                this.f7 = true;
            }
            if (this.f7) {
                this.ay();
                if (this.hk) {
                    this.a4();
                }
                this.ge = (this.ck != -1);
                this.cr = this.ck;
                this.h();
            }
        }
        final long n4 = System.currentTimeMillis() - this.f8;
        if (this.hj > 1L) {
            super.sleep(Math.max(25L, n4 * 3L / 7L));
        }
        else {
            super.sleep(Math.max(25L, n4 / 9L));
        }
        if (this.hg != 1) {
            this.cp &= !this.f7;
        }
        return this.f7;
    }
    
    public final void destroy() {
        this.stop();
        super.destroyThread();
        final Image image = null;
        this.ho = image;
        this.hp = image;
        this.ea = null;
        System.gc();
    }
    
    public final void stop() {
        super.setThreadState((byte)1);
    }
    
    public final void start() {
        super.setThreadState((byte)2);
    }
    
    public final void runRun() {
        try {
            this.gb = System.currentTimeMillis();
            if (this.g9 > this.hm) {
                this.hm = this.g9;
            }
            final double n = 0.001 * (this.gb - this.hm);
            this.hn = ((n < 0.0) ? 0.0 : ((n > 5.0) ? 5.0 : n));
            final double n2 = (this.gb - this.g9) * 0.001;
            this.hm = this.gb;
            super.setThreadState((byte)((this.g5 || !this.a3(this.hn)) ? 1 : 2));
            if (this.gd && this.gb - this.g9 > 0L) {
                this.zoom(this.gd = false, 0);
            }
        }
        catch (Exception ex) {}
    }
    
    public final void runInit() {
        try {
            this.hk = super.masterObject.hx;
            this.hj = super.masterObject.hw;
            while (!this.b0) {
                super.sleep(100L);
                if (super.getThreadState() == 0) {
                    return;
                }
            }
            this.n((byte)21, 0, 0, 0, 0);
            this.b4 = super.masterObject.ii.size().width;
            this.b3 = super.masterObject.ii.size().height;
            this.b6 = 0;
            this.b5 = 0;
            try {
                this.aq(this.hp.getSource());
                this.a1();
                this.ho = this.createImage(this);
                if (this.gc) {
                    final double gt = this.gt;
                    this.gt = 1.0;
                    this.fu = 180.0;
                    this.ay();
                    final double n = this.fu / 180.0;
                    this.gs *= n;
                    this.gt = gt * n;
                    this.g4 = this.g4;
                    this.g2 *= this.fu / 180.0;
                    final double g4 = this.g4;
                    this.at(this.g2);
                    this.fw = g4;
                    this.fs = -90.0;
                    this.ay();
                    this.g3 *= this.fs / -90.0;
                    this.g1 /= 5.0 * n;
                    this.gz = this.g1;
                    final double n2 = this.g0 * (20.0 * (this.fs / -90.0) / this.fq);
                    this.g0 = n2;
                    this.gy = n2;
                    this.g_ = this.g_;
                }
                if (Math.abs(this.g1) < 0.005000000000000001 && Math.abs(this.g0) < 0.005000000000000001) {
                    if (this.b4 / this.b3 / (this.d8 / this.d7) < 1.0) {
                        this.gz = 0.1;
                        this.gy = 0.0;
                    }
                    else {
                        this.gz = 0.0;
                        this.gy = 0.05;
                    }
                }
                this.play(0);
            }
            catch (Exception ex) {
                return;
            }
            if (this.ho != null && this.g5) {
                int fp = this.fp;
                this.g5 = false;
                if (this.gz != 0.0 || this.gy != 0.0 || this.gx != 1.0) {
                    this.setGUI(this, 0x1 | ((this.gt == this.gs || this.gx == 1.0) ? 0 : 2), 0);
                    if (this.g1 == 0.0 && this.g0 == 0.0 && this.g_ == 1.0) {
                        this.play(0);
                        fp |= 0x9;
                    }
                    else if (this.g1 > 0.0 || this.g0 > 0.0 || this.g_ > 1.0) {
                        fp |= 0x3;
                    }
                    else {
                        fp |= 0xA;
                    }
                }
                else {
                    this.setGUI(this, 2, 0);
                }
                this.setSensitive(this, 59, fp);
                super.masterObject.bg(this);
                this.hm = System.currentTimeMillis();
            }
            this.hb = this.g1;
            if (this.hb != 0.0) {
                this.hd = true;
                this.g9 = System.currentTimeMillis();
            }
            this.repaint();
        }
        catch (Exception ex2) {}
    }
    
    public final void runPause() {
        super.runPause();
        this.runRun();
    }
    
    public final void update(final Graphics graphics) {
        if (this.ho != null && !this.g5) {
            if (this.gi == null) {
                this.gj = this.createImage(this.b4, this.b3);
                this.gi = this.gj.getGraphics();
            }
            final long currentTimeMillis = System.currentTimeMillis();
            this.gi.drawImage(this.ho, 0, 0, this);
            final int cursor = ((this.hg == 0 && this.ge) || (this.ge && this.cp && Math.abs(this.cw - this.cu) + Math.abs(this.cv - this.ct) < 7)) ? 12 : 0;
            if (super.getCursorType() != cursor) {
                super.setCursor(cursor);
            }
            if (this.ge) {
                if (this.df) {
                    this.gi.setColor(Color.red);
                    for (int i = 0; i < this.gf; ++i) {
                        this.gi.drawLine(this.gg[0][i], this.gg[1][i], this.gg[2][i], this.gg[3][i]);
                    }
                }
                super.aa(this.gi, this.cs, this.cw, this.cv);
            }
            this.b7 = System.currentTimeMillis() - currentTimeMillis;
            graphics.drawImage(this.gj, 0, 0, null);
        }
    }
    
    public final void paint(final Graphics graphics) {
        final long currentTimeMillis = System.currentTimeMillis();
        this.update(graphics);
        this.b8 = System.currentTimeMillis() - currentTimeMillis;
    }
    
    private final synchronized void a4() {
        if (this.b2 || this.cw < 0 || this.cv < 0 || this.cw >= this.b4 || this.cv >= this.b3) {
            this.ge = false;
            this.ck = -1;
            return;
        }
        final Point a5 = this.a5(this.cw, this.cv);
        this.n((byte)100, a5.x, a5.y, 0, 0);
        this.n((byte)127, a5.x, a5.y, 0, 0);
    }
    
    private final Point a5(final int n, final int n2) {
        final Point as = this.as(n, n2);
        if (this.fo != 360.0 || this.ck == -1) {
            this.gn = 0;
        }
        else if (as.x - this.gm < -this.d8 / 2) {
            this.gn += this.d8;
        }
        else if (as.x - this.gm > this.d8 / 2) {
            this.gn -= this.d8;
        }
        this.gm = as.x;
        return as;
    }
    
    public final boolean mouseMove(final Event event, final int cw, final int cv) {
        final boolean b = this.cs != null && (cw != this.cw || cv != this.cv);
        this.b2 = false;
        this.cw = cw;
        this.cv = cv;
        if (!this.g5) {
            if (super.masterObject.hx) {
                this.a4();
            }
            if (b && this.cr == this.ck) {
                super.redrawToolTip(cw, cv);
            }
            else {
                this.a2();
            }
        }
        return true;
    }
    
    public final boolean mouseDrag(final Event event, final int n, final int n2) {
        this.cw = n;
        this.cv = n2;
        if (this.g5) {
            return true;
        }
        if (this.hg == 1) {
            final int n3 = n - this.hf;
            final int n4 = n2 - this.he;
            if (this.hd) {
                final double n5 = 2 * n3 / this.b4;
                final double n6 = 5.0E-4 * n5 * Math.abs(n5);
                final double n7 = 2 * n4 / this.b3;
                final double n8 = 5.0E-4 * n7 * Math.abs(n7);
                final double n9 = this.gw * 2000.0 * n6 * 0.5;
                final double n10 = -this.gr;
                final double gr = this.gr;
                this.hb = ((n9 < n10) ? n10 : ((n9 > gr) ? gr : n9));
                if (event.controlDown() || event.metaDown()) {
                    final double n11 = this.gu * n8 * 0.01 * 3600.0;
                    final double n12 = (n11 >= 0.0) ? (1.0 + n11) : (1.0 / (1.0 - n11));
                    final double n13 = 1.0 / this.gp;
                    final double gp = this.gp;
                    this.ha = ((n12 < n13) ? n13 : ((n12 > gp) ? gp : n12));
                    this.hc = 0.0;
                }
                else {
                    this.ha = 1.0;
                    final double n14 = this.gv * 2000.0 * n8 * -0.25;
                    final double n15 = -this.gq;
                    final double gq = this.gq;
                    this.hc = ((n14 < n15) ? n15 : ((n14 > gq) ? gq : n14));
                    if ((this.hc < 0.0 && this.hc > 0.05) || (this.hc > 0.0 && this.hc < 0.05)) {
                        this.hc = 0.0;
                    }
                }
            }
            else {
                this.g8 += n3;
                if (event.controlDown() || event.metaDown()) {
                    this.g7 += n4;
                }
                else {
                    this.g6 += n4;
                }
                this.hf = n;
                this.he = n2;
            }
            this.setSensitive(this, 11, 9);
        }
        return true;
    }
    
    public final boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.g5) {
            return true;
        }
        this.hg = (this.hd ? 2 : 0);
        if (this.co && !event.shiftDown() && this.cq != -1 && Math.abs(n - this.cu) + Math.abs(n2 - this.ct) < 7 && this.cp) {
            this.cq = -1;
            this.cp = false;
            final Point a5 = this.a5(n, n2);
            this.n((byte)12, a5.x, a5.y, 0, 0);
        }
        return true;
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        this.b2 = false;
        if (this.g5) {
            return true;
        }
        this.cu = n;
        this.ct = n2;
        this.cq = this.ck;
        this.cp = true;
        this.co = (!event.controlDown() && !event.metaDown());
        this.hg = 1;
        this.setSensitive(this, 11, 9);
        this.repaint(0L);
        this.f6 = 0.0;
        this.f5 = 0.0;
        this.hd = !event.shiftDown();
        this.g9 = System.currentTimeMillis();
        if (this.ge) {
            this.g9 += 250L;
        }
        if (this.hd) {
            this.hf = this.b4 / 2;
            this.he = this.b3 / 2;
            this.mouseDrag(event, n, n2);
        }
        else {
            this.hf = n;
            this.he = n2;
        }
        return true;
    }
    
    public final boolean mouseExit(final Event event, final int n, final int n2) {
        this.b2 = true;
        if (!this.g5) {
            this.n((byte)5, 0, 0, 0, 0);
            this.ck = -1;
            this.a2();
        }
        return true;
    }
    
    public final boolean mouseEnter(final Event event, final int n, final int n2) {
        final Point a5 = this.a5(n, n2);
        this.n((byte)4, a5.x, a5.y, 0, 0);
        return this.mouseMove(event, n, n2);
    }
}
