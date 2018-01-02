import java.awt.LayoutManager;
import java.awt.image.PixelGrabber;
import java.awt.image.ImageProducer;
import java.awt.image.ColorModel;
import java.awt.image.DirectColorModel;
import java.awt.Component;
import java.net.MalformedURLException;
import java.awt.Event;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.image.MemoryImageSource;
import java.awt.MediaTracker;
import java.net.URL;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Frame;
import java.awt.image.ImageObserver;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class TF_Cube extends Applet implements Runnable, ImageObserver
{
    private int a;
    Frame b;
    boolean c;
    float e;
    float f;
    float g;
    int h;
    int i;
    int j;
    int k;
    short[] l;
    short[] m;
    short[] n;
    short[] o;
    short[] p;
    short[] q;
    int r;
    boolean s;
    int[] t;
    int[] u;
    int[] v;
    int[] w;
    int[] x;
    long y;
    long z;
    int A;
    int[] B;
    boolean C;
    int D;
    final int[] E;
    static final int F = 6;
    float G;
    float H;
    float I;
    float J;
    float K;
    float L;
    float M;
    float N;
    float O;
    int P;
    Font Q;
    boolean R;
    int S;
    private Graphics T;
    short[] U;
    short[] V;
    short[] W;
    short[] X;
    short[] Y;
    short[] Z;
    int ba;
    int bb;
    int bc;
    float bd;
    float be;
    int bf;
    private Image bg;
    private Image bh;
    private Image bi;
    private Image bj;
    private Image bk;
    private Image bl;
    private Image bm;
    String bn;
    String bo;
    String bp;
    String bq;
    String br;
    String bs;
    int bt;
    boolean bu;
    int bv;
    boolean bw;
    int bx;
    int by;
    double bz;
    int[] bA;
    boolean bB;
    String[] bC;
    URL bD;
    int bE;
    int bF;
    String bG;
    String bH;
    String bI;
    String bJ;
    String bK;
    String bL;
    String bM;
    String bN;
    String bO;
    String bP;
    String bQ;
    String bR;
    String bS;
    String bT;
    float bU;
    MediaTracker bV;
    static final int bW = 1024;
    static final float bX = 1024.0f;
    int bY;
    boolean bZ;
    boolean ca;
    double[] cb;
    double[] cc;
    int cd;
    private Graphics ce;
    private Image cf;
    boolean cg;
    float[] ch;
    private Image ci;
    int cj;
    int ck;
    final float[] cl;
    static final int cm = 8;
    int cn;
    int co;
    int[] cp;
    TF_Cube2 cq;
    MemoryImageSource cr;
    int[] cs;
    int ct;
    int cu;
    short[] cv;
    short[] cw;
    short[] cx;
    short[] cy;
    short[] cz;
    short[] cA;
    boolean cB;
    int cC;
    double[] cD;
    float cE;
    float cF;
    float cG;
    int[] cH;
    String cI;
    int cJ;
    int cK;
    int cL;
    Color cM;
    int cN;
    int cO;
    int cP;
    int cQ;
    int cR;
    int cS;
    int[] cT;
    int[] cU;
    float[] cV;
    int[] cW;
    String cX;
    Color cY;
    int cZ;
    int da;
    boolean db;
    boolean dc;
    int[] dd;
    int[] de;
    int[] df;
    int[] dg;
    int[] dh;
    int[] di;
    int dj;
    Toolkit dk;
    Thread dl;
    int dm;
    int dn;
    int _flddo;
    int dp;
    int dq;
    int[] dr;
    int ds;
    int du;
    int dv;
    int dw;
    int dx;
    int dy;
    int[] dz;
    static final int dA = 256;
    int dB;
    int dC;
    int dD;
    Font[] dE;
    
    public final boolean mouseEnter(final Event event, final int i1, final int j1) {
        this.showStatus("Programmed by Tarek Fouda 'tarek@fouda.de'");
        this.z = System.currentTimeMillis();
        return true;
    }
    
    public void stop() {
        if (this.dl != null && this.dl.isAlive()) {
            this.dl.stop();
        }
        this.dl = null;
    }
    
    public int c() {
        char c1 = '\0';
        if (this.ba >= 1) {
            c1 = '\u6e35';
        }
        if (this.ba >= 3) {
            c1 = '\u36b0';
        }
        if (this.ba >= 5) {
            c1 = '\u2454';
        }
        if (this.ba >= 7) {
            c1 = '\u1af4';
        }
        if (this.ba >= 9) {
            c1 = '\u154a';
        }
        if (this.ba >= 10) {
            c1 = '\u1531';
        }
        if (this.ba >= 12) {
            c1 = '\u1180';
        }
        if (this.ba >= 14) {
            c1 = '\u0edd';
        }
        if (this.ba >= 16) {
            c1 = '\u0ce4';
        }
        if (this.ba >= 18) {
            c1 = '\u0b59';
        }
        if (this.ba >= 20) {
            c1 = '\u0a1e';
        }
        if (this.ba >= 22) {
            c1 = '\u091a';
        }
        if (this.ba >= 24) {
            c1 = '\u083e';
        }
        if (this.ba >= 26) {
            c1 = '\u078a';
        }
        if (this.ba >= 28) {
            c1 = '\u06ec';
        }
        if (this.ba >= 30) {
            c1 = '\u0667';
        }
        if (this.ba >= 32) {
            c1 = '\u05f1';
        }
        if (this.ba >= 34) {
            c1 = '\u0588';
        }
        if (this.ba >= 36) {
            c1 = '\u052c';
        }
        if (this.ba >= 38) {
            c1 = '\u04da';
        }
        if (this.ba >= 40) {
            c1 = '\u0491';
        }
        if (this.ba >= 42) {
            c1 = '\u044d';
        }
        if (this.ba >= 44) {
            c1 = '\u0410';
        }
        if (this.ba >= 46) {
            c1 = '\u03d9';
        }
        if (this.ba >= 48) {
            c1 = '\u03a6';
        }
        if (this.ba >= 50) {
            c1 = '\u0377';
        }
        if (this.ba >= 52) {
            c1 = '\u034b';
        }
        if (this.ba >= 54) {
            c1 = '\u0323';
        }
        if (this.ba >= 56) {
            c1 = '\u02fe';
        }
        if (this.ba >= 58) {
            c1 = '\u02db';
        }
        if (this.ba >= 60) {
            c1 = '\u02bb';
        }
        if (this.ba >= 62) {
            c1 = '\u029c';
        }
        if (this.ba >= 64) {
            c1 = '\u0280';
        }
        if (this.ba >= 66) {
            c1 = '\u0265';
        }
        if (this.ba >= 68) {
            c1 = '\u024c';
        }
        if (this.ba >= 70) {
            c1 = '\u0234';
        }
        if (this.ba >= 72) {
            c1 = '\u021e';
        }
        if (this.ba >= 74) {
            c1 = '\u0209';
        }
        if (this.ba >= 76) {
            c1 = '\u01f5';
        }
        if (this.ba >= 78) {
            c1 = '\u01e2';
        }
        if (this.ba >= 80) {
            c1 = '\u01d0';
        }
        if (this.ba >= 82) {
            c1 = '\u01bf';
        }
        if (this.ba >= 84) {
            c1 = '\u01af';
        }
        if (this.ba >= 86) {
            c1 = '\u019f';
        }
        if (this.ba >= 88) {
            c1 = '\u0191';
        }
        if (this.ba >= 90) {
            c1 = '\u0183';
        }
        if (this.ba >= 92) {
            c1 = '\u0175';
        }
        if (this.ba >= 94) {
            c1 = '\u0168';
        }
        if (this.ba >= 96) {
            c1 = '\u015c';
        }
        if (this.ba >= 98) {
            c1 = '\u0150';
        }
        if (this.ba >= 100) {
            c1 = '\u0145';
        }
        if (this.ba >= 102) {
            c1 = '\u013a';
        }
        if (this.ba >= 104) {
            c1 = '\u012f';
        }
        if (this.ba >= 106) {
            c1 = '\u0125';
        }
        if (this.ba >= 108) {
            c1 = '\u011b';
        }
        if (this.ba >= 110) {
            c1 = '\u0112';
        }
        if (this.ba >= 112) {
            c1 = '\u0109';
        }
        if (this.ba >= 114) {
            c1 = '\u0100';
        }
        if (this.ba >= 116) {
            c1 = '\u00f8';
        }
        if (this.ba >= 118) {
            c1 = '\u00ef';
        }
        if (this.ba >= 120) {
            c1 = '\u00e8';
        }
        if (this.ba >= 122) {
            c1 = '\u00e0';
        }
        if (this.ba >= 124) {
            c1 = '\u00d9';
        }
        if (this.ba >= 126) {
            c1 = '\u00d2';
        }
        if (this.ba >= 128) {
            c1 = '\u00cb';
        }
        if (this.ba >= 130) {
            c1 = '\u00c5';
        }
        if (this.ba >= 132) {
            c1 = '¾';
        }
        if (this.ba >= 134) {
            c1 = '¸';
        }
        if (this.ba >= 136) {
            c1 = '²';
        }
        if (this.ba >= 138) {
            c1 = '¬';
        }
        if (this.ba >= 140) {
            c1 = '¦';
        }
        if (this.ba >= 142) {
            c1 = '¡';
        }
        if (this.ba >= 144) {
            c1 = '\u009c';
        }
        if (this.ba >= 146) {
            c1 = '\u0096';
        }
        if (this.ba >= 148) {
            c1 = '\u0091';
        }
        if (this.ba >= 150) {
            c1 = '\u008d';
        }
        if (this.ba >= 152) {
            c1 = '\u0088';
        }
        if (this.ba >= 154) {
            c1 = '\u0083';
        }
        if (this.ba >= 156) {
            c1 = '\u007f';
        }
        if (this.ba >= 158) {
            c1 = 'z';
        }
        if (this.ba >= 160) {
            c1 = 'v';
        }
        if (this.ba >= 162) {
            c1 = 'r';
        }
        if (this.ba >= 164) {
            c1 = 'n';
        }
        if (this.ba >= 166) {
            c1 = 'j';
        }
        if (this.ba >= 168) {
            c1 = 'f';
        }
        if (this.ba >= 170) {
            c1 = 'b';
        }
        if (this.ba >= 172) {
            c1 = '^';
        }
        if (this.ba >= 174) {
            c1 = '[';
        }
        if (this.ba >= 176) {
            c1 = 'W';
        }
        if (this.ba >= 178) {
            c1 = 'T';
        }
        if (this.ba >= 180) {
            c1 = 'P';
        }
        if (this.ba >= 182) {
            c1 = 'M';
        }
        if (this.ba >= 184) {
            c1 = 'J';
        }
        if (this.ba >= 186) {
            c1 = 'G';
        }
        if (this.ba >= 188) {
            c1 = 'D';
        }
        if (this.ba >= 190) {
            c1 = 'A';
        }
        if (this.ba >= 192) {
            c1 = '>';
        }
        if (this.ba >= 194) {
            c1 = ';';
        }
        if (this.ba >= 196) {
            c1 = '8';
        }
        if (this.ba >= 198) {
            c1 = '5';
        }
        if (this.ba >= 200) {
            c1 = '3';
        }
        if (this.ba >= 202) {
            c1 = '0';
        }
        if (this.ba >= 204) {
            c1 = '-';
        }
        if (this.ba >= 206) {
            c1 = '+';
        }
        if (this.ba >= 208) {
            c1 = '(';
        }
        if (this.ba >= 210) {
            c1 = '&';
        }
        if (this.ba >= 212) {
            c1 = '$';
        }
        if (this.ba >= 214) {
            c1 = '!';
        }
        if (this.ba >= 216) {
            c1 = '\u001f';
        }
        if (this.ba >= 218) {
            c1 = '\u001d';
        }
        if (this.ba >= 220) {
            c1 = '\u001a';
        }
        if (this.ba >= 222) {
            c1 = '\u0018';
        }
        if (this.ba >= 224) {
            c1 = '\u0016';
        }
        if (this.ba >= 226) {
            c1 = '\u0014';
        }
        if (this.ba >= 228) {
            c1 = '\u0012';
        }
        if (this.ba >= 230) {
            c1 = '\u0010';
        }
        if (this.ba >= 232) {
            c1 = '\u000e';
        }
        if (this.ba >= 234) {
            c1 = '\f';
        }
        if (this.ba >= 236) {
            c1 = '\n';
        }
        if (this.ba >= 238) {
            c1 = '\t';
        }
        if (this.ba >= 240) {
            c1 = '\u0007';
        }
        if (this.ba >= 242) {
            c1 = '\u0005';
        }
        if (this.ba >= 244) {
            c1 = '\u0003';
        }
        if (this.ba >= 246) {
            c1 = '\u0001';
        }
        if (this.ba >= 248) {
            c1 = '\0';
        }
        if (this.ba >= 250) {
            c1 = '\ufffe';
        }
        if (this.ba >= 252) {
            c1 = '\ufffd';
        }
        if (this.ba >= 254) {
            c1 = '\ufffb';
        }
        if (this.ba >= 256) {
            c1 = '\ufffa';
        }
        if (this.ba >= 258) {
            c1 = '\ufff8';
        }
        if (this.ba >= 260) {
            c1 = '\ufff7';
        }
        if (this.ba >= 262) {
            c1 = '\ufff5';
        }
        if (this.ba >= 264) {
            c1 = '\ufff4';
        }
        if (this.ba >= 266) {
            c1 = '\ufff2';
        }
        if (this.ba >= 268) {
            c1 = '\ufff1';
        }
        if (this.ba >= 270) {
            c1 = '\uffef';
        }
        if (this.ba >= 272) {
            c1 = '\uffee';
        }
        if (this.ba >= 274) {
            c1 = '\uffed';
        }
        if (this.ba >= 276) {
            c1 = '\uffeb';
        }
        if (this.ba >= 278) {
            c1 = '\uffea';
        }
        if (this.ba >= 280) {
            c1 = '\uffe9';
        }
        if (this.ba >= 282) {
            c1 = '\uffe8';
        }
        if (this.ba >= 284) {
            c1 = '\uffe6';
        }
        if (this.ba >= 286) {
            c1 = '\uffe5';
        }
        if (this.ba >= 288) {
            c1 = '\uffe4';
        }
        if (this.ba >= 290) {
            c1 = '\uffe3';
        }
        if (this.ba >= 292) {
            c1 = '\uffe2';
        }
        if (this.ba >= 294) {
            c1 = '\uffe1';
        }
        if (this.ba >= 296) {
            c1 = '\uffdf';
        }
        if (this.ba >= 298) {
            c1 = '\uffde';
        }
        if (this.ba >= 300) {
            c1 = '\uffdd';
        }
        return c1;
    }
    
    private static String c(final String s1) {
        final char[] ac = s1.toCharArray();
        final int i1 = ac.length;
        int j1 = 0;
    Label_0010:
        while (true) {
            while (true) {
                int k1 = 0;
                final char[] array = ac;
                final int n = j1;
                array[n] ^= '\u0005';
                while (true) {
                    ++j1;
                    ++k1;
                    if (i1 == j1) {
                        break Label_0010;
                    }
                    switch (k1) {
                        case 1: {
                            final char[] array2 = ac;
                            final int n2 = j1;
                            array2[n2] ^= 'z';
                            continue;
                        }
                        case 2: {
                            final char[] array3 = ac;
                            final int n3 = j1;
                            array3[n3] ^= '+';
                            continue;
                        }
                        case 3: {
                            final char[] array4 = ac;
                            final int n4 = j1;
                            array4[n4] ^= '\u0004';
                            continue;
                        }
                        case 4: {
                            final char[] array5 = ac;
                            final int n5 = j1;
                            array5[n5] ^= '\"';
                            continue;
                        }
                        case 5: {
                            continue Label_0010;
                        }
                    }
                }
            }
            break;
        }
        return new String(ac);
    }
    
    public void b() {
        for (int i1 = 0; i1 < 6; ++i1) {
            final double d1 = this.cl[this.E[i1 * 4]];
            final double d2 = this.cl[this.E[i1 * 4 + 1]];
            final double d3 = this.cl[this.E[i1 * 4 + 2]];
            final double d4 = this.cl[this.E[i1 * 4] + 1];
            final double d5 = this.cl[this.E[i1 * 4 + 1] + 1];
            final double d6 = this.cl[this.E[i1 * 4 + 2] + 1];
            final double d7 = this.cl[this.E[i1 * 4] + 2];
            final double d8 = this.cl[this.E[i1 * 4 + 1] + 2];
            final double d9 = this.cl[this.E[i1 * 4 + 2] + 2];
            final double d10 = (d5 - d4) * (d9 - d7) - (d8 - d7) * (d6 - d4);
            final double d11 = (d8 - d7) * (d3 - d1) - (d2 - d1) * (d9 - d7);
            final double d12 = (d2 - d1) * (d6 - d4) - (d3 - d1) * (d5 - d4);
            final double d13 = Math.sqrt(d10 * d10 + d11 * d11 + d12 * d12);
            this.cc[i1 * 3] = d10 / d13;
            this.cc[i1 * 3 + 1] = d11 / d13;
            this.cc[i1 * 3 + 2] = d12 / d13;
        }
        for (int j1 = 0; j1 < 8; ++j1) {
            int i2 = 0;
            double d14 = 0.0;
            double d15 = 0.0;
            double d16 = 0.0;
            for (int k1 = 0; k1 < 6; ++k1) {
                for (int l1 = 0; l1 < 4; ++l1) {
                    if (this.E[k1 * 4 + l1] == j1 * 3) {
                        d14 += this.cc[k1 * 3];
                        d15 += this.cc[k1 * 3 + 1];
                        d16 += this.cc[k1 * 3 + 2];
                        ++i2;
                    }
                }
            }
            if (i2 > 0) {
                d14 /= i2;
                d15 /= i2;
                d16 /= i2;
            }
            final double d17 = Math.sqrt(d14 * d14 + d15 * d15 + d16 * d16);
            this.cb[j1 * 3] = d14 / d17;
            this.cb[j1 * 3 + 1] = d15 / d17;
            this.cb[j1 * 3 + 2] = d16 / d17;
        }
    }
    
    synchronized Image b(final String s1) {
        URL url = null;
        Image image = null;
        try {
            url = new URL(this.getDocumentBase(), s1);
        }
        catch (MalformedURLException ex) {}
        if (image == null) {
            int i1 = 0;
            while (i1 < 5) {
                try {
                    if (i1 % 2 == 0) {
                        image = Toolkit.getDefaultToolkit().getImage(url);
                    }
                    else {
                        image = this.getImage(url);
                    }
                    ++i1;
                    final MediaTracker mediatracker = new MediaTracker(this);
                    this.notifyAll();
                    Thread.currentThread();
                    Thread.yield();
                    try {
                        mediatracker.addImage(image, 0);
                        mediatracker.waitForID(0);
                    }
                    catch (InterruptedException _ex) {
                        image = null;
                    }
                    if (mediatracker.isErrorID(0)) {
                        image = null;
                    }
                    else {
                        i1 = 6;
                    }
                }
                catch (NullPointerException _ex2) {
                    System.gc();
                }
            }
        }
        if (image == null) {
            for (int j1 = 0; j1 < 25; ++j1) {
                try {
                    Thread.currentThread();
                    Thread.sleep(250L);
                }
                catch (InterruptedException ex2) {}
            }
        }
        else {
            while (image.getWidth(this) < 0) {
                this.notifyAll();
                Thread.currentThread();
                Thread.yield();
                try {
                    Thread.currentThread();
                    Thread.sleep(100L);
                }
                catch (InterruptedException ex3) {}
            }
        }
        return image;
    }
    
    void g() {
        this.cr = new MemoryImageSource(this.ds, this.ba, new DirectColorModel(24, 16711680, 65280, 255), this.B, 0, this.ds);
        try {
            this.cr.setAnimated(true);
            this.cr.setFullBufferUpdates(true);
            this.bg = this.createImage(this.cr);
            this.cr.newPixels();
            this.bw = true;
        }
        catch (NoSuchMethodError _ex) {
            this.bw = false;
        }
        if (!this.bw) {
            this.cr = null;
            this.cq = new TF_Cube2(this.ds, this.ba, new DirectColorModel(24, 16711680, 65280, 255), this.B, 0, this.ds);
            this.bg = this.createImage(this.cq);
        }
    }
    
    private final void b(int i1, int j1, int k1, int l1, int i2, int j2, int k2, int l2) {
        int k3 = 0;
        final int[] ai = this.cs;
        final int l3 = this.dj;
        if (j1 == j2) {
            return;
        }
        if (j2 < j1) {
            j1 -= j2;
            k3 += j2 + j2 + j2 + l3;
            final int i3 = (i1 - i2 << 16) / j1;
            final int k4 = (k1 - k2) / j1;
            final int i4 = (l1 - l2) / j1;
            i2 <<= 16;
            do {
                ai[k3++] = i2 >> 16;
                ai[k3++] = (k2 & 0xFF00);
                ai[k3++] = (l2 & 0xFF00);
                i2 += i3;
                k2 += k4;
                l2 += i4;
            } while (--j1 > 0);
            return;
        }
        j2 -= j1;
        k3 += j1 + j1 + j1;
        final int j3 = (i2 - i1 << 16) / j2;
        final int l4 = (k2 - k1) / j2;
        final int j4 = (l2 - l1) / j2;
        i1 <<= 16;
        do {
            ai[k3++] = i1 >> 16;
            ai[k3++] = (k1 & 0xFF00);
            ai[k3++] = (l1 & 0xFF00);
            i1 += j3;
            k1 += l4;
            l1 += j4;
        } while (--j2 > 0);
    }
    
    private final void m() {
        int i1 = 8;
        final float f4 = 0.0f;
        final float f5 = 0.0f;
        final float f6 = 0.0f;
        int k1 = 0;
        int l1 = 0;
        final float f7 = (float)Math.sin(this.e);
        final float f8 = (float)Math.cos(this.e);
        final float f9 = (float)Math.sin(this.f);
        final float f10 = (float)Math.cos(this.f);
        final float f11 = (float)Math.sin(this.g);
        final float f12 = (float)Math.cos(this.g);
        final float f13 = f7 * f11;
        final float f14 = f7 * f12;
        this.G = f10 * f12 + f13 * f9;
        this.H = f14 * f9 - f11 * f10;
        this.I = f9 * f8;
        this.J = f11 * f8;
        this.K = f8 * f12;
        this.L = -f7;
        this.M = f13 * f10 - f9 * f12;
        this.N = f9 * f11 + f14 * f10;
        this.O = f8 * f10;
        final float f15 = this.ch[0] * this.G + this.ch[1] * this.J + this.ch[2] * this.M;
        final float f16 = this.ch[0] * this.H + this.ch[1] * this.K + this.ch[2] * this.N;
        final float f17 = this.ch[0] * this.I + this.ch[1] * this.L + this.ch[2] * this.O;
        final float f18 = this.ch[3] * this.G + this.ch[4] * this.J + this.ch[5] * this.M;
        final float f19 = this.ch[3] * this.H + this.ch[4] * this.K + this.ch[5] * this.N;
        final float f20 = this.ch[3] * this.I + this.ch[4] * this.L + this.ch[5] * this.O;
        final float f21 = this.ch[6] * this.G + this.ch[7] * this.J + this.ch[8] * this.M;
        final float f22 = this.ch[6] * this.H + this.ch[7] * this.K + this.ch[8] * this.N;
        final float f23 = this.ch[6] * this.I + this.ch[7] * this.L + this.ch[8] * this.O;
        this.ch[0] = f15;
        this.ch[1] = f16;
        this.ch[2] = f17;
        this.ch[3] = f18;
        this.ch[4] = f19;
        this.ch[5] = f20;
        this.ch[6] = f21;
        this.ch[7] = f22;
        this.ch[8] = f23;
        do {
            final float f24 = this.cl[k1++];
            final float f25 = this.cl[k1++];
            final float f26 = this.cl[k1++];
            final float f27 = f24 * f15 + f25 * f18 + f26 * f21;
            final float f28 = f24 * f16 + f25 * f19 + f26 * f22;
            final float f29 = f24 * f17 + f25 * f20 + f26 * f23;
            final int j1 = (int)f29 + this.dB;
            this.cp[l1++] = (int)(f27 * (256.0 / j1) + this.bc);
            this.cp[l1++] = (int)(f28 * (256.0 / j1) + this.bb);
            this.cp[l1++] = j1;
        } while (--i1 > 0);
    }
    
    public boolean imageUpdate(final Image image, final int i1, final int j1, final int k1, final int l1, final int i2) {
        if (image == this.ci) {
            if (i1 == 16) {
                this.cg = true;
            }
            return true;
        }
        return true;
    }
    
    private final void a() {
        while (true) {
            this.showStatus("Programmed by Tarek Fouda 'tarek@fouda.de'");
            try {
                Thread.sleep(500L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    Image a(final String s1) {
        try {
            return this.b(s1);
        }
        catch (NoSuchMethodError _ex) {
            return this.b(s1);
        }
    }
    
    private final void a(int i1, int j1, int k1, int l1, int i2, int j2, int k2, int l2) {
        int k3 = 0;
        final int l3 = this.dj;
        final int[] ai = this.cH;
        if (j1 == j2) {
            return;
        }
        if (j2 < j1) {
            j1 -= j2;
            k3 += j2 + j2 + j2 + l3;
            final int i3 = (i1 - i2 << 16) / j1;
            final int k4 = (k1 - k2) / j1;
            final int i4 = (l1 - l2) / j1;
            i2 <<= 16;
            do {
                ai[k3++] = i2 >> 16;
                ai[k3++] = (k2 & 0xFF00);
                ai[k3++] = (l2 & 0xFF00);
                i2 += i3;
                k2 += k4;
                l2 += i4;
            } while (--j1 > 0);
            return;
        }
        j2 -= j1;
        k3 += j1 + j1 + j1;
        final int j3 = (i2 - i1 << 16) / j2;
        final int l4 = (k2 - k1) / j2;
        final int j4 = (l2 - l1) / j2;
        i1 <<= 16;
        do {
            ai[k3++] = i1 >> 16;
            ai[k3++] = (k1 & 0xFF00);
            ai[k3++] = (l1 & 0xFF00);
            i1 += j3;
            k1 += l4;
            l1 += j4;
        } while (--j2 > 0);
    }
    
    private final void a(int i1, final int[] ai) {
        final boolean flag = false;
        final boolean flag2 = false;
        final boolean flag3 = false;
        final boolean flag4 = false;
        final int l3 = this.cp[this.E[i1]];
        final int j4 = this.cp[this.E[i1++] + 1];
        final int k4 = this.cp[this.E[i1]];
        final int l4 = this.cp[this.E[i1++] + 1];
        final int i2 = this.cp[this.E[i1]];
        final int j5 = this.cp[this.E[i1++] + 1];
        final int k5 = this.cp[this.E[i1]];
        final int l5 = this.cp[this.E[i1++] + 1];
        int k6;
        int l6;
        if (j4 > l4) {
            k6 = l4;
            l6 = j4;
        }
        else {
            k6 = j4;
            l6 = l4;
        }
        if (k6 > j5) {
            k6 = j5;
        }
        else if (l6 < j5) {
            l6 = j5;
        }
        if (k6 > l5) {
            k6 = l5;
        }
        else if (l6 < l5) {
            l6 = l5;
        }
        l6 -= k6;
        if (l6 <= 0) {
            return;
        }
        this.a(l3, j4, 65280, 65280, k4, l4, 65280, 0);
        this.a(k4, l4, 65280, 0, i2, j5, 0, 0);
        this.a(i2, j5, 0, 0, k5, l5, 0, 65280);
        this.a(k5, l5, 0, 65280, l3, j4, 65280, 65280);
        final int k7 = this.ds;
        final int[] ai2 = this.cH;
        final int[] ai3 = this.B;
        int j6 = (k6 - 1) * k7;
        int i3 = k6 + k6 + k6;
        int j7 = i3 + this.dj;
        do {
            j6 += k7;
            int i4 = ai2[i3++];
            int i5 = ai2[j7++] - i4;
            int k8 = ai2[i3++];
            final int i6 = ai2[j7++];
            int l7 = ai2[i3++];
            final int j8 = ai2[j7++];
            if (i5 > 0) {
                final int j9 = (i6 - k8) / i5;
                final int k9 = (j8 - l7) / i5;
                do {
                    ai3[j6 + i4++] = ai[(l7 & 0xFF00) + (k8 >> 8)];
                    k8 += j9;
                    l7 += k9;
                } while (--i5 > 0);
            }
        } while (--l6 > 0);
    }
    
    private final void a(int i1, final short[] aword0, final short[] aword1, final short[] aword2, final int j1) {
        final boolean flag = false;
        final boolean flag2 = false;
        final boolean flag3 = false;
        final boolean flag4 = false;
        int l9 = 128 - (int)(this.cD[this.E[i1] + 2] * 220.0);
        final int j2 = (int)(this.cD[this.E[i1]] * 128.0) + 127 << 8;
        final int k6 = (int)(this.cD[this.E[i1] + 1] * 128.0) + 127 << 8;
        final int k7 = this.cp[this.E[i1]];
        final int i2 = this.cp[this.E[i1++] + 1];
        int i3 = 128 - (int)(this.cD[this.E[i1] + 2] * 220.0);
        final int j3 = (int)(this.cD[this.E[i1]] * 128.0) + 127 << 8;
        final int k8 = (int)(this.cD[this.E[i1] + 1] * 128.0) + 127 << 8;
        final int l10 = this.cp[this.E[i1]];
        final int i4 = this.cp[this.E[i1++] + 1];
        int j4 = 128 - (int)(this.cD[this.E[i1] + 2] * 220.0);
        final int j5 = (int)(this.cD[this.E[i1]] * 128.0) + 127 << 8;
        final int k9 = (int)(this.cD[this.E[i1] + 1] * 128.0) + 127 << 8;
        final int l11 = this.cp[this.E[i1]];
        final int i5 = this.cp[this.E[i1++] + 1];
        int k10 = 128 - (int)(this.cD[this.E[i1] + 2] * 220.0);
        final int j6 = (int)(this.cD[this.E[i1]] * 128.0) + 127 << 8;
        final int k11 = (int)(this.cD[this.E[i1] + 1] * 128.0) + 127 << 8;
        final int l12 = this.cp[this.E[i1]];
        final int i6 = this.cp[this.E[i1++] + 1];
        if (l9 < 0) {
            l9 = 0;
        }
        if (i3 < 0) {
            i3 = 0;
        }
        if (j4 < 0) {
            j4 = 0;
        }
        if (k10 < 0) {
            k10 = 0;
        }
        int l13;
        int i7;
        if (i2 > i4) {
            l13 = i4;
            i7 = i2;
        }
        else {
            l13 = i2;
            i7 = i4;
        }
        if (l13 > i5) {
            l13 = i5;
        }
        else if (i7 < i5) {
            i7 = i5;
        }
        if (l13 > i6) {
            l13 = i6;
        }
        else if (i7 < i6) {
            i7 = i6;
        }
        i7 -= l13;
        if (i7 <= 0) {
            return;
        }
        this.a(k7, i2, 65280, 65280, l10, i4, 65280, 0);
        this.a(l10, i4, 65280, 0, l11, i5, 0, 0);
        this.a(l11, i5, 0, 0, l12, i6, 0, 65280);
        this.a(l12, i6, 0, 65280, k7, i2, 65280, 65280);
        this.b(k7, i2, j2, k6, l10, i4, j3, k8);
        this.b(l10, i4, j3, k8, l11, i5, j5, k9);
        this.b(l11, i5, j5, k9, l12, i6, j6, k11);
        this.b(l12, i6, j6, k11, k7, i2, j2, k6);
        final int[] ai = this.cH;
        final int[] ai2 = this.cs;
        final int[] ai3 = this.B;
        final int[] ai4 = this.v;
        final int[] ai5 = this.w;
        final int[] ai6 = this.x;
        final int[] ai7 = this.bA;
        final int j7 = this.ds;
        int k12 = (l13 - 1) * j7;
        int l14 = l13 + l13 + l13;
        int i8 = l14 + this.dj;
        do {
            k12 += j7;
            int l15 = ai[l14++];
            int j8 = ai[i8++] - l15;
            int l16 = ai2[l14];
            int l17 = ai[l14++];
            final int j9 = ai2[i8];
            final int j10 = ai[i8++];
            int i9 = ai2[l14];
            int i10 = ai[l14++];
            final int k13 = ai2[i8];
            final int k14 = ai[i8++];
            if (j8 > 0) {
                final int k15 = (j10 - l17) / j8;
                final int l18 = (k14 - i10) / j8;
                final int i11 = (j9 - l16) / j8;
                final int j11 = (k13 - i9) / j8;
                do {
                    final int k16 = (i10 & 0xFF00) + (l17 >> 8);
                    final int l19 = (i9 & 0xFF00) + (l16 >> 8);
                    ai3[k12 + l15++] = (ai4[aword0[k16] + ai7[l19]] | ai5[aword1[k16] + ai7[l19]] | ai6[aword2[k16] + ai7[l19]]);
                    l17 += k15;
                    i10 += l18;
                    l16 += i11;
                    i9 += j11;
                } while (--j8 > 0);
            }
        } while (--i7 > 0);
    }
    
    public synchronized void a(final Image image) {
        int i1 = 0;
        this.prepareImage(image, this.cu, this.ct, this);
        this.notifyAll();
        while ((i1 & 0xF0) == 0x0) {
            Thread.yield();
            i1 = this.checkImage(image, this.cu, this.ct, this);
        }
    }
    
    private final void a(final int[] ai, final int[] ai1) {
        for (int i1 = 0, j1 = 0; j1 < 65536; j1 += 256, i1 += 128) {
            this.du = 0;
            while (this.du < 256) {
                ai1[j1 + this.du] = ai[i1 + (this.du >> 1)];
                ++this.du;
            }
            final int k1 = j1;
            j1 += 256;
            this.du = 0;
            while (this.du < 256) {
                ai1[j1 + this.du] = ai1[k1 + this.du];
                ++this.du;
            }
        }
    }
    
    private final void f() {
        if (this.ca) {
            if (System.currentTimeMillis() - this.z > 10000L) {
                this.ca = false;
            }
        }
        else {
            this.e = this.cE;
            this.f = this.cF;
            this.g = this.cG;
        }
        this.m();
        this.d();
        if (this.bB) {
            this.l();
            this.p();
        }
    }
    
    private final boolean h() {
        this.bV = new MediaTracker(this);
        this.bh = this.a(this.bn);
        if (this.bh == null) {
            return false;
        }
        this.C = true;
        this.a(this.bh);
        this.bt = 1;
        this.repaint();
        this.bi = this.a(this.bo);
        if (this.bi == null) {
            return false;
        }
        this.a(this.bi);
        this.bt = 2;
        this.repaint();
        this.bj = this.a(this.bp);
        if (this.bj == null) {
            return false;
        }
        this.a(this.bj);
        this.bt = 3;
        this.repaint();
        this.bk = this.a(this.bq);
        if (this.bk == null) {
            return false;
        }
        this.a(this.bk);
        this.bt = 4;
        this.repaint();
        this.bl = this.a(this.br);
        if (this.bl == null) {
            return false;
        }
        this.a(this.bl);
        this.bt = 5;
        this.repaint();
        this.bm = this.a(this.bs);
        if (this.bm == null) {
            return false;
        }
        this.a(this.bm);
        this.bt = 6;
        this.repaint();
        if (this.bh.getWidth(this) == 256) {
            final PixelGrabber pixelgrabber = new PixelGrabber(this.bh, 0, 0, 256, 256, this.dd, 0, 256);
            try {
                pixelgrabber.grabPixels();
            }
            catch (InterruptedException ex) {}
            final PixelGrabber pixelgrabber2 = new PixelGrabber(this.bi, 0, 0, 256, 256, this.de, 0, 256);
            try {
                pixelgrabber2.grabPixels();
            }
            catch (InterruptedException ex2) {}
            final PixelGrabber pixelgrabber3 = new PixelGrabber(this.bj, 0, 0, 256, 256, this.df, 0, 256);
            try {
                pixelgrabber3.grabPixels();
            }
            catch (InterruptedException ex3) {}
            final PixelGrabber pixelgrabber4 = new PixelGrabber(this.bk, 0, 0, 256, 256, this.dg, 0, 256);
            try {
                pixelgrabber4.grabPixels();
            }
            catch (InterruptedException ex4) {}
            final PixelGrabber pixelgrabber5 = new PixelGrabber(this.bl, 0, 0, 256, 256, this.dh, 0, 256);
            try {
                pixelgrabber5.grabPixels();
            }
            catch (InterruptedException ex5) {}
            final PixelGrabber pixelgrabber6 = new PixelGrabber(this.bm, 0, 0, 256, 256, this.di, 0, 256);
            try {
                pixelgrabber6.grabPixels();
            }
            catch (InterruptedException ex6) {}
        }
        else {
            final int[] ai = new int[16384];
            PixelGrabber pixelgrabber7 = new PixelGrabber(this.bh, 0, 0, 128, 128, ai, 0, 128);
            try {
                pixelgrabber7.grabPixels();
            }
            catch (InterruptedException ex7) {}
            pixelgrabber7 = null;
            this.a(ai, this.dd);
            PixelGrabber pixelgrabber8 = new PixelGrabber(this.bi, 0, 0, 128, 128, ai, 0, 128);
            try {
                pixelgrabber8.grabPixels();
            }
            catch (InterruptedException ex8) {}
            pixelgrabber8 = null;
            this.a(ai, this.de);
            PixelGrabber pixelgrabber9 = new PixelGrabber(this.bj, 0, 0, 128, 128, ai, 0, 128);
            try {
                pixelgrabber9.grabPixels();
            }
            catch (InterruptedException ex9) {}
            pixelgrabber9 = null;
            this.a(ai, this.df);
            PixelGrabber pixelgrabber10 = new PixelGrabber(this.bk, 0, 0, 128, 128, ai, 0, 128);
            try {
                pixelgrabber10.grabPixels();
            }
            catch (InterruptedException ex10) {}
            pixelgrabber10 = null;
            this.a(ai, this.dg);
            PixelGrabber pixelgrabber11 = new PixelGrabber(this.bl, 0, 0, 128, 128, ai, 0, 128);
            try {
                pixelgrabber11.grabPixels();
            }
            catch (InterruptedException ex11) {}
            pixelgrabber11 = null;
            this.a(ai, this.dh);
            PixelGrabber pixelgrabber12 = new PixelGrabber(this.bm, 0, 0, 128, 128, ai, 0, 128);
            try {
                pixelgrabber12.grabPixels();
            }
            catch (InterruptedException ex12) {}
            pixelgrabber12 = null;
            this.a(ai, this.di);
        }
        this.bi.flush();
        this.bi = null;
        this.bj.flush();
        this.bj = null;
        this.bk.flush();
        this.bk = null;
        this.bl.flush();
        this.bl = null;
        this.bm.flush();
        this.bm = null;
        return true;
    }
    
    byte a(final int i1, final int j1, final int k1, final int l1) {
        final int i2 = i1 - j1;
        if (i2 >= k1) {
            return (byte)i2;
        }
        final int j2 = k1 - i2 - 1;
        return (byte)(l1 - j2);
    }
    
    public void start() {
        if (this.dl == null) {
            (this.dl = new Thread(this)).start();
        }
    }
    
    public final void update(final Graphics g1) {
        this.paint(g1);
    }
    
    private final void l() {
        int i1 = 8;
        final float f4 = 0.0f;
        final float f5 = 0.0f;
        final float f6 = 0.0f;
        int j1 = 0;
        int k1 = 0;
        final float f7 = this.ch[0];
        final float f8 = this.ch[1];
        final float f9 = this.ch[2];
        final float f10 = this.ch[3];
        final float f11 = this.ch[4];
        final float f12 = this.ch[5];
        final float f13 = this.ch[6];
        final float f14 = this.ch[7];
        final float f15 = this.ch[8];
        do {
            final float f16 = (float)this.cb[j1++];
            final float f17 = (float)this.cb[j1++];
            final float f18 = (float)this.cb[j1++];
            final float f19 = f16 * f7 + f17 * f10 + f18 * f13;
            final float f20 = f16 * f8 + f17 * f11 + f18 * f14;
            final float f21 = f16 * f9 + f17 * f12 + f18 * f15;
            this.cD[k1++] = f19;
            this.cD[k1++] = f20;
            this.cD[k1++] = f21;
        } while (--i1 > 0);
    }
    
    public final void k() {
        try {
            if (this.bw) {
                this.cr.newPixels();
                return;
            }
            this.cq.startProduction(this.cq.getConsumer());
        }
        catch (NoSuchMethodError _ex) {}
    }
    
    public final boolean mouseExit(final Event event, final int i1, final int j1) {
        this.z = 0L;
        return true;
    }
    
    public void paint(final Graphics g1) {
        if (!this.R) {
            if (this.cC == 1) {
                this.ce.drawImage(this.bg, 0, 0, this);
            }
            else {
                this.j();
                this.ce.drawImage(this.bg, 0, 0, this.cu, this.ct, this);
            }
            this.ce.setColor(Color.black);
            this.ce.fillRect(0, 0, this.size().width, this.size().height);
            this.ce.setColor(Color.white);
            this.ce.drawString("Please wait ...", this.size().width / 2 - 30, this.size().height / 2 + 3);
            g1.drawImage(this.cf, 0, 0, this);
            return;
        }
        g1.drawImage(this.cf, 0, 0, this);
    }
    
    public Color Farbe(String str) {
        int i = str.indexOf(44);
        final int R = Integer.parseInt(str.substring(0, i));
        str = str.substring(i + 1);
        i = str.indexOf(44);
        final int G = Integer.parseInt(str.substring(0, i));
        final int B = Integer.parseInt(str.substring(i + 1));
        return new Color(R, G, B);
    }
    
    private final void d() {
        int k2 = 0;
        int l2 = 0;
        this.P = 0;
        for (int j2 = 0; j2 < 24; j2 += 4) {
            final int i1 = this.E[k2++];
            final int j3 = this.E[k2++];
            final int k3 = this.E[k2++];
            final int l3 = this.E[k2++];
            final int i2 = (this.cp[j3] - this.cp[i1]) * (this.cp[k3 + 1] - this.cp[i1 + 1]) - (this.cp[k3] - this.cp[i1]) * (this.cp[j3 + 1] - this.cp[i1 + 1]);
            if (i2 <= 0) {
                ++this.P;
                this.dz[l2++] = j2;
            }
        }
    }
    
    public synchronized boolean e() {
        this.prepareImage(this.ci, this);
        if (this.bw) {
            for (int i1 = 0; i1 < 3; ++i1) {
                this.notifyAll();
                Thread.yield();
                try {
                    Thread.sleep(100L);
                }
                catch (InterruptedException ex) {}
            }
            return this.cg;
        }
        return false;
    }
    
    public synchronized void i() {
        if (this.c) {
            this.notifyAll();
            while (!this.cg) {
                Thread.yield();
                try {
                    Thread.sleep(8L);
                }
                catch (InterruptedException ex) {}
            }
            this.cg = false;
        }
        this.ce.drawImage(this.ci, this.cj, this.ck, this);
    }
    
    public TF_Cube() {
        this.E = new int[] { 9, 6, 3, 0, 12, 15, 18, 21, 3, 6, 18, 15, 12, 21, 9, 0, 0, 3, 15, 12, 9, 21, 18, 6 };
        this.ch = new float[] { 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f };
        this.cl = new float[] { -64.0f, -64.0f, -64.0f, 64.0f, -64.0f, -64.0f, 64.0f, 64.0f, -64.0f, -64.0f, 64.0f, -64.0f, -64.0f, -64.0f, 64.0f, 64.0f, -64.0f, 64.0f, 64.0f, 64.0f, 64.0f, -64.0f, 64.0f, 64.0f };
        this.bw = false;
        this.R = false;
        this.cg = false;
        this.c = false;
        this.cB = false;
        this.bZ = false;
        this.C = false;
    }
    
    public void destroy() {
        if (this.ci != null) {
            this.ci.flush();
        }
        this.ci = null;
        if (this.cf != null) {
            this.cf.flush();
        }
        this.cf = null;
        if (this.ce != null) {
            this.ce.dispose();
        }
        this.ce = null;
        System.gc();
    }
    
    public synchronized void j() {
        int i1 = 0;
        this.prepareImage(this.bg, this.cu, this.ct, this);
        this.notifyAll();
        while ((i1 & 0xF0) == 0x0) {
            Thread.yield();
            i1 = this.checkImage(this.bg, this.cu, this.ct, this);
        }
    }
    
    private final void p() {
        int i1 = this.P;
        do {
            --i1;
            final int j1 = this.dz[i1];
            final int k1 = j1 >> 2;
            switch (k1) {
                default: {
                    continue;
                }
                case 5: {
                    this.a(j1, this.cA, this.Z, this.q, this.u[i1]);
                    continue;
                }
                case 4: {
                    this.a(j1, this.cz, this.Y, this.p, this.u[i1]);
                    continue;
                }
                case 3: {
                    this.a(j1, this.cy, this.X, this.o, this.u[i1]);
                    continue;
                }
                case 2: {
                    this.a(j1, this.cx, this.W, this.n, this.u[i1]);
                    continue;
                }
                case 1: {
                    this.a(j1, this.cw, this.V, this.m, this.u[i1]);
                    continue;
                }
                case 0: {
                    this.a(j1, this.cv, this.U, this.l, this.u[i1]);
                    continue;
                }
            }
        } while (i1 > 0);
    }
    
    public final synchronized boolean mouseDown(final Event event, final int i1, final int j1) {
        return true;
    }
    
    public void run() {
        if (!this.R) {
            this.R = this.h();
            if (this.bB) {
                this.cv = new short[65536];
                this.U = new short[65536];
                this.l = new short[65536];
                this.bf = 0;
                while (this.bf < 65536) {
                    this.cv[this.bf] = (short)(this.dd[this.bf] >> 16 & 0xFF);
                    this.U[this.bf] = (short)(this.dd[this.bf] >> 8 & 0xFF);
                    this.l[this.bf] = (short)(this.dd[this.bf] & 0xFF);
                    ++this.bf;
                }
                this.dd = null;
                this.cw = new short[65536];
                this.V = new short[65536];
                this.m = new short[65536];
                this.bf = 0;
                while (this.bf < 65536) {
                    this.cw[this.bf] = (short)(this.de[this.bf] >> 16 & 0xFF);
                    this.V[this.bf] = (short)(this.de[this.bf] >> 8 & 0xFF);
                    this.m[this.bf] = (short)(this.de[this.bf] & 0xFF);
                    ++this.bf;
                }
                this.de = null;
                this.cx = new short[65536];
                this.W = new short[65536];
                this.n = new short[65536];
                this.bf = 0;
                while (this.bf < 65536) {
                    this.cx[this.bf] = (short)(this.df[this.bf] >> 16 & 0xFF);
                    this.W[this.bf] = (short)(this.df[this.bf] >> 8 & 0xFF);
                    this.n[this.bf] = (short)(this.df[this.bf] & 0xFF);
                    ++this.bf;
                }
                this.df = null;
                this.cy = new short[65536];
                this.X = new short[65536];
                this.o = new short[65536];
                this.bf = 0;
                while (this.bf < 65536) {
                    this.cy[this.bf] = (short)(this.dg[this.bf] >> 16 & 0xFF);
                    this.X[this.bf] = (short)(this.dg[this.bf] >> 8 & 0xFF);
                    this.o[this.bf] = (short)(this.dg[this.bf] & 0xFF);
                    ++this.bf;
                }
                this.dg = null;
                this.cz = new short[65536];
                this.Y = new short[65536];
                this.p = new short[65536];
                this.bf = 0;
                while (this.bf < 65536) {
                    this.cz[this.bf] = (short)(this.dh[this.bf] >> 16 & 0xFF);
                    this.Y[this.bf] = (short)(this.dh[this.bf] >> 8 & 0xFF);
                    this.p[this.bf] = (short)(this.dh[this.bf] & 0xFF);
                    ++this.bf;
                }
                this.dh = null;
                this.cA = new short[65536];
                this.Z = new short[65536];
                this.q = new short[65536];
                this.bf = 0;
                while (this.bf < 65536) {
                    this.cA[this.bf] = (short)(this.di[this.bf] >> 16 & 0xFF);
                    this.Z[this.bf] = (short)(this.di[this.bf] >> 8 & 0xFF);
                    this.q[this.bf] = (short)(this.di[this.bf] & 0xFF);
                    ++this.bf;
                }
                this.di = null;
            }
        }
        this.showStatus("Programmed by Tarek Fouda 'tarek@fouda.de'");
        System.gc();
        this.y = System.currentTimeMillis();
        final Graphics g1 = this.getGraphics();
        if (this.ci != null && !this.c) {
            this.c = this.e();
        }
        while (this.dl != null) {
            if (this.s) {
                try {
                    System.arraycopy(this.t, 0, this.B, 0, this.dm);
                }
                catch (ArrayIndexOutOfBoundsException _ex) {
                    this.stop();
                }
                catch (ArrayStoreException _ex2) {
                    this.stop();
                }
            }
            this.f();
            if (++this.r == this.bE) {
                System.gc();
                this.r = 0;
            }
            try {
                this.k();
            }
            catch (NoSuchMethodError noSuchMethodError) {}
            if (this.cC == 1) {
                this.ce.drawImage(this.bg, 0, 0, this);
            }
            else {
                this.j();
                this.ce.drawImage(this.bg, 0, 0, this.cu, this.ct, this);
            }
            g1.drawImage(this.cf, 0, 0, this);
        }
    }
    
    public void init() {
        this.R = false;
        this.setLayout(null);
        this.addNotify();
        this.dk = this.getToolkit();
        this.by = 150;
        this.bx = 800;
        this.bz = 0.8;
        this.bn = this.getParameter(c("l\u0017JcG4"));
        this.bo = this.getParameter(c("l\u0017JcG7"));
        this.bp = this.getParameter(c("l\u0017JcG6"));
        this.bq = this.getParameter(c("l\u0017JcG1"));
        this.br = this.getParameter(c("l\u0017JcG0"));
        this.bs = this.getParameter(c("l\u0017JcG3"));
        this.bu = true;
        this.bB = true;
        this.bH = "1";
        this.bM = "2";
        this.bN = "2";
        this.bO = "1";
        this.bP = "0";
        this.bR = "0";
        this.bS = "0";
        this.s = true;
        this.bE = 0;
        this.cn = 0;
        this.co = 0;
        if (this.bE < 0) {
            this.bE = 0;
        }
        if (this.cn > 10) {
            this.cn = 10;
        }
        else if (this.cn < 1) {
            this.cn = 1;
        }
        if (this.co > 10) {
            this.co = 10;
        }
        else if (this.co < 1) {
            this.co = 1;
        }
        this.cC = 0;
        this.cE = Float.valueOf(this.bM) / 100.0f;
        this.cF = Float.valueOf(this.bN) / 100.0f;
        this.cG = Float.valueOf(this.bO) / 100.0f;
        this.bU = 0.2f;
        Color background;
        try {
            background = this.Farbe(this.getParameter("Background"));
        }
        catch (Exception ex) {
            background = new Color(0);
        }
        this.k = background.getRed();
        this.j = background.getGreen();
        this.h = background.getBlue();
        this.i = (0xFF000000 | this.k << 16 | this.j << 8 | this.h);
        if (this.cC > 8) {
            this.cC = 8;
        }
        else if (this.cC < 1) {
            this.cC = 1;
        }
        this.ds = this.size().width / this.cC;
        this.ba = this.size().height / this.cC;
        this.cu = this.ds * this.cC;
        this.ct = this.ba * this.cC;
        this.bb = this.ba / 2;
        this.bc = this.ds / 2;
        this.dj = this.ba * 3;
        this.dm = this.ds * this.ba;
        this.D = this.c();
        this.dB = this.D + 256;
        this.cW = new int[this.dm];
        this.B = new int[this.dm];
        for (int i4 = 0; i4 < this.dm; ++i4) {
            this.B[i4] = this.i;
        }
        this.dd = new int[65536];
        this.de = new int[65536];
        this.df = new int[65536];
        this.dg = new int[65536];
        this.dh = new int[65536];
        this.di = new int[65536];
        this.cV = new float[1280];
        this.cp = new int[24];
        this.dz = new int[6];
        this.cH = new int[this.ba * 6];
        for (int j2 = 0; j2 < 1024; ++j2) {
            this.cV[j2] = (float)Math.sin(j2 * 2.0 * 3.141592653589793 / 1024.0);
        }
        for (int k2 = 0; k2 < 256; ++k2) {
            this.cV[k2 + 1024] = this.cV[k2];
        }
        if (this.bB) {
            this.v = new int[1280];
            this.w = new int[1280];
            this.x = new int[1280];
            for (int l2 = 0; l2 < 256; ++l2) {
                this.v[l2] = 0;
                this.w[l2] = 0;
                this.x[l2] = 0;
            }
            for (int i5 = 256; i5 < 512; ++i5) {
                this.v[i5] = i5 - 256 << 16;
                this.w[i5] = i5 - 256 << 8;
                this.x[i5] = i5 - 256;
            }
            for (int j3 = 512; j3 < 1280; ++j3) {
                this.v[j3] = 16711680;
                this.w[j3] = 65280;
                this.x[j3] = 255;
            }
            this.cc = new double[18];
            this.cb = new double[24];
            this.cD = new double[24];
            this.u = new int[6];
            this.cs = new int[this.ba * 6];
            this.bA = new int[65536];
            double d1 = -0.39269908169872414;
            for (int k3 = 0; k3 < 256; ++k3) {
                double d2 = -0.39269908169872414;
                final double d3 = Math.sin(d1) * 220.0;
                for (int l3 = 0; l3 < 256; ++l3) {
                    int i6 = (int)((Math.sin(d2) * 220.0 + d3) * this.bz);
                    if (i6 > this.bx) {
                        i6 = this.bx;
                    }
                    if (i6 < this.by) {
                        i6 = this.by;
                    }
                    this.bA[k3 * 256 + l3] = i6;
                    d2 += 0.02454369260617026;
                }
                d1 += 0.02454369260617026;
            }
            this.b();
        }
        if (this.s) {
            this.t = new int[this.dm];
            for (int j4 = 0; j4 < this.dm; ++j4) {
                this.t[j4] = this.i;
            }
        }
        try {
            this.g();
        }
        catch (NoSuchMethodError _ex) {
            this.g();
        }
        this.cf = this.createImage(this.cu, this.ct + this.cQ);
        this.ce = this.cf.getGraphics();
        if (!this.cB) {}
    }
    
    public synchronized boolean mouseMove(final Event event, final int i1, final int j1) {
        if (this.bu) {
            this.z = System.currentTimeMillis();
            this.ca = true;
            this.e = (this.ct / 2 - j1) * this.bU / this.ct * 2.0f;
            this.f = (this.cu / 2 - i1) * -this.bU / this.cu * 2.0f;
            this.g = 0.0f;
        }
        return true;
    }
}
