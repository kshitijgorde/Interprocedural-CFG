import java.awt.FontMetrics;
import java.awt.Event;
import java.awt.image.PixelGrabber;
import java.awt.image.ImageProducer;
import java.awt.image.ColorModel;
import java.awt.image.DirectColorModel;
import java.awt.Container;
import java.awt.Label;
import java.awt.LayoutManager;
import java.io.DataInputStream;
import java.io.InputStream;
import java.awt.Component;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.net.MalformedURLException;
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

public class TmapCube extends Applet implements Runnable, ImageObserver
{
    private int a;
    Frame b;
    boolean c;
    final String d = "D\n[hGqZI}\u0002C\u001bImM%9BqAf\u0013\u000b,Ur\r\u0005eLc";
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
    anfy cq;
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
    int do;
    int dp;
    int dq;
    int[] dr;
    int ds;
    Lware dt;
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
    
    private final void a() {
        while (true) {
            this.showStatus(c("A\u0015E#V%\bNiMs\u001f\u000bsUrTJjD|\u0010JrC+\u0019Di\u0002f\bN`Kq\t\u000bhKk\u001f\u000bmL%2\u007fIn$"));
            try {
                Thread.sleep(500L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void b() {
        for (int i = 0; i < 6; ++i) {
            final double n = this.cl[this.E[i * 4]];
            final double n2 = this.cl[this.E[i * 4 + 1]];
            final double n3 = this.cl[this.E[i * 4 + 2]];
            final double n4 = this.cl[this.E[i * 4] + 1];
            final double n5 = this.cl[this.E[i * 4 + 1] + 1];
            final double n6 = this.cl[this.E[i * 4 + 2] + 1];
            final double n7 = this.cl[this.E[i * 4] + 2];
            final double n8 = this.cl[this.E[i * 4 + 1] + 2];
            final double n9 = this.cl[this.E[i * 4 + 2] + 2];
            final double n10 = (n5 - n4) * (n9 - n7) - (n8 - n7) * (n6 - n4);
            final double n11 = (n8 - n7) * (n3 - n) - (n2 - n) * (n9 - n7);
            final double n12 = (n2 - n) * (n6 - n4) - (n3 - n) * (n5 - n4);
            final double sqrt = Math.sqrt(n10 * n10 + n11 * n11 + n12 * n12);
            this.cc[i * 3] = n10 / sqrt;
            this.cc[i * 3 + 1] = n11 / sqrt;
            this.cc[i * 3 + 2] = n12 / sqrt;
        }
        for (int j = 0; j < 8; ++j) {
            int n13 = 0;
            double n14 = 0.0;
            double n15 = 0.0;
            double n16 = 0.0;
            for (int k = 0; k < 6; ++k) {
                for (int l = 0; l < 4; ++l) {
                    if (this.E[k * 4 + l] == j * 3) {
                        n14 += this.cc[k * 3];
                        n15 += this.cc[k * 3 + 1];
                        n16 += this.cc[k * 3 + 2];
                        ++n13;
                    }
                }
            }
            if (n13 > 0) {
                n14 /= n13;
                n15 /= n13;
                n16 /= n13;
            }
            final double sqrt2 = Math.sqrt(n14 * n14 + n15 * n15 + n16 * n16);
            this.cb[j * 3] = n14 / sqrt2;
            this.cb[j * 3 + 1] = n15 / sqrt2;
            this.cb[j * 3 + 2] = n16 / sqrt2;
        }
    }
    
    public int c() {
        int n = 0;
        if (this.ba >= 1) {
            n = 28213;
        }
        if (this.ba >= 3) {
            n = 14000;
        }
        if (this.ba >= 5) {
            n = 9300;
        }
        if (this.ba >= 7) {
            n = 6900;
        }
        if (this.ba >= 9) {
            n = 5450;
        }
        if (this.ba >= 10) {
            n = 5425;
        }
        if (this.ba >= 12) {
            n = 4480;
        }
        if (this.ba >= 14) {
            n = 3805;
        }
        if (this.ba >= 16) {
            n = 3300;
        }
        if (this.ba >= 18) {
            n = 2905;
        }
        if (this.ba >= 20) {
            n = 2590;
        }
        if (this.ba >= 22) {
            n = 2330;
        }
        if (this.ba >= 24) {
            n = 2110;
        }
        if (this.ba >= 26) {
            n = 1930;
        }
        if (this.ba >= 28) {
            n = 1772;
        }
        if (this.ba >= 30) {
            n = 1639;
        }
        if (this.ba >= 32) {
            n = 1521;
        }
        if (this.ba >= 34) {
            n = 1416;
        }
        if (this.ba >= 36) {
            n = 1324;
        }
        if (this.ba >= 38) {
            n = 1242;
        }
        if (this.ba >= 40) {
            n = 1169;
        }
        if (this.ba >= 42) {
            n = 1101;
        }
        if (this.ba >= 44) {
            n = 1040;
        }
        if (this.ba >= 46) {
            n = 985;
        }
        if (this.ba >= 48) {
            n = 934;
        }
        if (this.ba >= 50) {
            n = 887;
        }
        if (this.ba >= 52) {
            n = 843;
        }
        if (this.ba >= 54) {
            n = 803;
        }
        if (this.ba >= 56) {
            n = 766;
        }
        if (this.ba >= 58) {
            n = 731;
        }
        if (this.ba >= 60) {
            n = 699;
        }
        if (this.ba >= 62) {
            n = 668;
        }
        if (this.ba >= 64) {
            n = 640;
        }
        if (this.ba >= 66) {
            n = 613;
        }
        if (this.ba >= 68) {
            n = 588;
        }
        if (this.ba >= 70) {
            n = 564;
        }
        if (this.ba >= 72) {
            n = 542;
        }
        if (this.ba >= 74) {
            n = 521;
        }
        if (this.ba >= 76) {
            n = 501;
        }
        if (this.ba >= 78) {
            n = 482;
        }
        if (this.ba >= 80) {
            n = 464;
        }
        if (this.ba >= 82) {
            n = 447;
        }
        if (this.ba >= 84) {
            n = 431;
        }
        if (this.ba >= 86) {
            n = 415;
        }
        if (this.ba >= 88) {
            n = 401;
        }
        if (this.ba >= 90) {
            n = 387;
        }
        if (this.ba >= 92) {
            n = 373;
        }
        if (this.ba >= 94) {
            n = 360;
        }
        if (this.ba >= 96) {
            n = 348;
        }
        if (this.ba >= 98) {
            n = 336;
        }
        if (this.ba >= 100) {
            n = 325;
        }
        if (this.ba >= 102) {
            n = 314;
        }
        if (this.ba >= 104) {
            n = 303;
        }
        if (this.ba >= 106) {
            n = 293;
        }
        if (this.ba >= 108) {
            n = 283;
        }
        if (this.ba >= 110) {
            n = 274;
        }
        if (this.ba >= 112) {
            n = 265;
        }
        if (this.ba >= 114) {
            n = 256;
        }
        if (this.ba >= 116) {
            n = 248;
        }
        if (this.ba >= 118) {
            n = 239;
        }
        if (this.ba >= 120) {
            n = 232;
        }
        if (this.ba >= 122) {
            n = 224;
        }
        if (this.ba >= 124) {
            n = 217;
        }
        if (this.ba >= 126) {
            n = 210;
        }
        if (this.ba >= 128) {
            n = 203;
        }
        if (this.ba >= 130) {
            n = 197;
        }
        if (this.ba >= 132) {
            n = 190;
        }
        if (this.ba >= 134) {
            n = 184;
        }
        if (this.ba >= 136) {
            n = 178;
        }
        if (this.ba >= 138) {
            n = 172;
        }
        if (this.ba >= 140) {
            n = 166;
        }
        if (this.ba >= 142) {
            n = 161;
        }
        if (this.ba >= 144) {
            n = 156;
        }
        if (this.ba >= 146) {
            n = 150;
        }
        if (this.ba >= 148) {
            n = 145;
        }
        if (this.ba >= 150) {
            n = 141;
        }
        if (this.ba >= 152) {
            n = 136;
        }
        if (this.ba >= 154) {
            n = 131;
        }
        if (this.ba >= 156) {
            n = 127;
        }
        if (this.ba >= 158) {
            n = 122;
        }
        if (this.ba >= 160) {
            n = 118;
        }
        if (this.ba >= 162) {
            n = 114;
        }
        if (this.ba >= 164) {
            n = 110;
        }
        if (this.ba >= 166) {
            n = 106;
        }
        if (this.ba >= 168) {
            n = 102;
        }
        if (this.ba >= 170) {
            n = 98;
        }
        if (this.ba >= 172) {
            n = 94;
        }
        if (this.ba >= 174) {
            n = 91;
        }
        if (this.ba >= 176) {
            n = 87;
        }
        if (this.ba >= 178) {
            n = 84;
        }
        if (this.ba >= 180) {
            n = 80;
        }
        if (this.ba >= 182) {
            n = 77;
        }
        if (this.ba >= 184) {
            n = 74;
        }
        if (this.ba >= 186) {
            n = 71;
        }
        if (this.ba >= 188) {
            n = 68;
        }
        if (this.ba >= 190) {
            n = 65;
        }
        if (this.ba >= 192) {
            n = 62;
        }
        if (this.ba >= 194) {
            n = 59;
        }
        if (this.ba >= 196) {
            n = 56;
        }
        if (this.ba >= 198) {
            n = 53;
        }
        if (this.ba >= 200) {
            n = 51;
        }
        if (this.ba >= 202) {
            n = 48;
        }
        if (this.ba >= 204) {
            n = 45;
        }
        if (this.ba >= 206) {
            n = 43;
        }
        if (this.ba >= 208) {
            n = 40;
        }
        if (this.ba >= 210) {
            n = 38;
        }
        if (this.ba >= 212) {
            n = 36;
        }
        if (this.ba >= 214) {
            n = 33;
        }
        if (this.ba >= 216) {
            n = 31;
        }
        if (this.ba >= 218) {
            n = 29;
        }
        if (this.ba >= 220) {
            n = 26;
        }
        if (this.ba >= 222) {
            n = 24;
        }
        if (this.ba >= 224) {
            n = 22;
        }
        if (this.ba >= 226) {
            n = 20;
        }
        if (this.ba >= 228) {
            n = 18;
        }
        if (this.ba >= 230) {
            n = 16;
        }
        if (this.ba >= 232) {
            n = 14;
        }
        if (this.ba >= 234) {
            n = 12;
        }
        if (this.ba >= 236) {
            n = 10;
        }
        if (this.ba >= 238) {
            n = 9;
        }
        if (this.ba >= 240) {
            n = 7;
        }
        if (this.ba >= 242) {
            n = 5;
        }
        if (this.ba >= 244) {
            n = 3;
        }
        if (this.ba >= 246) {
            n = 1;
        }
        if (this.ba >= 248) {
            n = 0;
        }
        if (this.ba >= 250) {
            n = -2;
        }
        if (this.ba >= 252) {
            n = -3;
        }
        if (this.ba >= 254) {
            n = -5;
        }
        if (this.ba >= 256) {
            n = -6;
        }
        if (this.ba >= 258) {
            n = -8;
        }
        if (this.ba >= 260) {
            n = -9;
        }
        if (this.ba >= 262) {
            n = -11;
        }
        if (this.ba >= 264) {
            n = -12;
        }
        if (this.ba >= 266) {
            n = -14;
        }
        if (this.ba >= 268) {
            n = -15;
        }
        if (this.ba >= 270) {
            n = -17;
        }
        if (this.ba >= 272) {
            n = -18;
        }
        if (this.ba >= 274) {
            n = -19;
        }
        if (this.ba >= 276) {
            n = -21;
        }
        if (this.ba >= 278) {
            n = -22;
        }
        if (this.ba >= 280) {
            n = -23;
        }
        if (this.ba >= 282) {
            n = -24;
        }
        if (this.ba >= 284) {
            n = -26;
        }
        if (this.ba >= 286) {
            n = -27;
        }
        if (this.ba >= 288) {
            n = -28;
        }
        if (this.ba >= 290) {
            n = -29;
        }
        if (this.ba >= 292) {
            n = -30;
        }
        if (this.ba >= 294) {
            n = -31;
        }
        if (this.ba >= 296) {
            n = -33;
        }
        if (this.ba >= 298) {
            n = -34;
        }
        if (this.ba >= 300) {
            n = -35;
        }
        return n;
    }
    
    private final void d() {
        int n = 0;
        int n2 = 0;
        this.P = 0;
        for (int i = 0; i < 24; i += 4) {
            final int n3 = this.E[n++];
            final int n4 = this.E[n++];
            final int n5 = this.E[n++];
            final int n6 = this.E[n++];
            if ((this.cp[n4] - this.cp[n3]) * (this.cp[n5 + 1] - this.cp[n3 + 1]) - (this.cp[n5] - this.cp[n3]) * (this.cp[n4 + 1] - this.cp[n3 + 1]) <= 0) {
                ++this.P;
                this.dz[n2++] = i;
            }
        }
    }
    
    public synchronized boolean e() {
        this.prepareImage(this.ci, this);
        if (this.bw) {
            for (int i = 0; i < 3; ++i) {
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
            return;
        }
        this.o();
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
    
    Image a(final String s) {
        try {
            return this.b(s);
        }
        catch (NoSuchMethodError noSuchMethodError) {
            return this.b(s);
        }
    }
    
    synchronized Image b(final String s) {
        URL url = null;
        Image image = null;
        try {
            url = new URL(this.getDocumentBase(), s);
        }
        catch (MalformedURLException ex) {}
        Label_0172: {
            try {
                try {
                    final InputStream resourceAsStream = this.getClass().getResourceAsStream(url.toString());
                    if (resourceAsStream == null) {
                        break Label_0172;
                    }
                    final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
                    final byte[] array = new byte[512];
                    boolean b = false;
                    byte[] byteArray;
                    try {
                        while (!b) {
                            final int read = resourceAsStream.read(array, 0, 512);
                            if (read != -1) {
                                byteArrayOutputStream.write(array, 0, read);
                                byteArrayOutputStream.flush();
                            }
                            else {
                                b = true;
                            }
                        }
                        byteArray = byteArrayOutputStream.toByteArray();
                        byteArrayOutputStream.close();
                        resourceAsStream.close();
                    }
                    catch (IOException ex2) {
                        byteArray = null;
                    }
                    System.gc();
                    if (byteArray != null) {
                        image = this.getToolkit().createImage(byteArray);
                        this.prepareImage(image, this);
                    }
                }
                catch (NoSuchMethodError noSuchMethodError) {}
            }
            catch (SecurityException ex3) {}
        }
        if (image == null) {
            int i = 0;
            while (i < 5) {
                try {
                    if (i % 2 == 0) {
                        image = Toolkit.getDefaultToolkit().getImage(url);
                    }
                    else {
                        image = this.getImage(url);
                    }
                    ++i;
                    final MediaTracker mediaTracker = new MediaTracker(this);
                    this.notifyAll();
                    Thread.currentThread();
                    Thread.yield();
                    try {
                        mediaTracker.addImage(image, 0);
                        mediaTracker.waitForID(0);
                    }
                    catch (InterruptedException ex4) {
                        image = null;
                    }
                    if (mediaTracker.isErrorID(0)) {
                        image = null;
                    }
                    else {
                        i = 6;
                    }
                }
                catch (NullPointerException ex5) {
                    System.gc();
                }
            }
        }
        if (image == null) {
            for (int j = 0; j < 25; ++j) {
                this.showStatus(c("L\u0017JcG%") + s + c("%\u0014Dp\u0002c\u0015^jF$"));
                try {
                    Thread.currentThread();
                    Thread.sleep(250L);
                }
                catch (InterruptedException ex6) {}
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
                catch (InterruptedException ex7) {}
            }
        }
        return image;
    }
    
    public void a(final String s, final int n) {
        try {
            this.b(s, n);
        }
        catch (NoSuchMethodError noSuchMethodError) {
            this.b(s, n);
        }
    }
    
    public void b(final String s, final int n) {
        try {
            final URL url = new URL(this.getDocumentBase(), s);
            try {
                final DataInputStream dataInputStream = new DataInputStream(url.openStream());
                if (dataInputStream == null) {
                    return;
                }
                final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
                final byte[] array = new byte[512];
                int n2 = 0;
                boolean b = false;
                try {
                    while (!b) {
                        final int read = dataInputStream.read(array, 0, 512);
                        if (read == -1) {
                            b = true;
                        }
                        else {
                            byteArrayOutputStream.write(array, 0, read);
                            byteArrayOutputStream.flush();
                            n2 += read;
                        }
                    }
                    final byte[] byteArray = byteArrayOutputStream.toByteArray();
                    byteArrayOutputStream.close();
                    dataInputStream.close();
                    System.gc();
                    if (n == 0) {
                        for (int i = 0; i < n2; ++i) {
                            final byte b2 = byteArray[i];
                            if (b2 == 13 || b2 == 10) {
                                byteArray[i] = 32;
                            }
                        }
                        try {
                            this.cI = new String(byteArray);
                            return;
                        }
                        catch (NoSuchMethodError noSuchMethodError) {
                            this.cI = new String(byteArray, 0);
                            return;
                        }
                    }
                    int n3 = 1;
                    for (int j = 0; j < n2; ++j) {
                        if (byteArray[j] == 10) {
                            ++n3;
                        }
                    }
                    this.bC = new String[n3 - 1];
                    final int[] array2 = new int[n3 + 1];
                    final int[] array3 = new int[n3 + 1];
                    array2[0] = 0;
                    int n4 = 0;
                    int n5 = 0;
                    for (int k = 0; k < n2; ++k) {
                        final byte b3 = byteArray[k];
                        if (b3 == 10) {
                            array2[n4 + 1] = k + 1;
                            if (n5 == 13) {
                                array3[n4] = k - array2[n4] - 1;
                            }
                            else {
                                array3[n4] = k - array2[n4];
                            }
                            ++n4;
                        }
                        n5 = b3;
                    }
                    array3[n4] = n2 - array2[n4 + 1] - 1;
                    try {
                        for (int l = 0; l < n3 - 1; ++l) {
                            try {
                                this.bC[l] = new String(byteArray, array2[l], array3[l]);
                            }
                            catch (NoSuchMethodError noSuchMethodError2) {
                                this.bC[l] = new String(byteArray, 0, array2[l], array3[l]);
                            }
                        }
                    }
                    catch (StringIndexOutOfBoundsException ex) {
                        this.bC = null;
                    }
                }
                catch (IOException ex2) {}
            }
            catch (IOException ex3) {}
        }
        catch (MalformedURLException ex4) {}
    }
    
    public void a(final Graphics graphics) {
        graphics.setFont(this.Q);
        if (this.a == 0) {
            this.dx = this.dy;
        }
        else {
            this.bv += this.dn;
            this.dx = this.dy - (int)Math.abs(this.a * Math.sin(this.bv / 90.0 * 3.141592653589793));
        }
        if (this.cO != 0) {
            for (int i = 0; i < this.dp; ++i) {
                final int n = this.cT[this.cN + i];
                graphics.copyArea(i, n, 1, this.cQ, 0, this.ct - n);
            }
            if (this.dc) {
                graphics.setColor(this.cM);
                graphics.drawString(this.cI, this.dv + 1, this.ct + this.cJ + 1);
            }
            graphics.setColor(this.cY);
            graphics.drawString(this.cI, this.dv, this.ct + this.cJ);
            for (int j = 0; j < this.dp; ++j) {
                graphics.copyArea(j, this.ct, 1, this.cR, 0, this.cU[this.cN + j]);
            }
            this.cN -= this.cS;
            if (this.cN < 0) {
                this.cN += 360;
            }
        }
        else {
            if (this.dc) {
                graphics.setColor(this.cM);
                graphics.drawString(this.cI, this.dv + 1, this.dx + 1);
            }
            graphics.setColor(this.cY);
            graphics.drawString(this.cI, this.dv, this.dx);
        }
        this.dv -= this.do;
        if (this.dv < -this.cK) {
            this.dv = this.dp;
        }
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if (image == this.ci) {
            if (n == 16) {
                this.cg = true;
            }
            return true;
        }
        return true;
    }
    
    public void init() {
        this.R = false;
        this.setLayout(null);
        this.addNotify();
        this.dk = this.getToolkit();
        this.cX = this.getParameter(c("v\u000eJpWv\u0017Xc"));
        final String parameter = this.getParameter(c("f\bN`Kq\t"));
        if (parameter != null) {
            if (!parameter.startsWith(c("D\n[hGqZI}\u0002C\u001bImM%9BqAf\u0013\u000b,Ur\r\u0005eLc"))) {
                this.a();
            }
        }
        else {
            this.a();
        }
        String s;
        try {
            s = this.getDocumentBase().getProtocol();
        }
        catch (SecurityException ex) {
            s = c("c\u0013Ga");
        }
        String s2;
        try {
            s2 = this.getDocumentBase().getHost();
        }
        catch (SecurityException ex2) {
            s2 = "";
        }
        if (s.equalsIgnoreCase(c("c\u0013Ga")) || s2.length() == 0 || s2.equalsIgnoreCase(c("i\u0015HeNm\u0015Xp")) || s2.equals(c("4H\u001c*\u0012+J\u00055"))) {
            this.cB = true;
        }
        else {
            if (s2.startsWith(c("r\r\\*"))) {
                s2 = s2.substring(4);
            }
            final String parameter2 = this.getParameter(c("w\u001fLgMa\u001f"));
            if (parameter2 != null && !parameter2.equals("NO") && parameter2.length() > 10) {
                int n = 1;
                try {
                    for (int i = 0; i < parameter2.length(); ++i) {
                        if (parameter2.charAt(i) == '+') {
                            ++n;
                        }
                    }
                }
                catch (StringIndexOutOfBoundsException ex3) {}
                final int[] array = new int[n];
                if (n == 1) {
                    array[0] = parameter2.length();
                }
                else {
                    int n2 = 0;
                    try {
                        for (int j = 0; j < parameter2.length(); ++j) {
                            if (parameter2.charAt(j) == '+') {
                                array[n2] = j;
                                ++n2;
                            }
                        }
                    }
                    catch (StringIndexOutOfBoundsException ex4) {}
                    array[n2] = parameter2.length();
                }
                final String[] array2 = new String[n];
                int n3 = 0;
                for (int k = 0; k < n; ++k) {
                    try {
                        array2[k] = parameter2.substring(n3, array[k]);
                    }
                    catch (StringIndexOutOfBoundsException ex5) {}
                    n3 = array[k] + 1;
                }
                for (int l = 0; l < n; ++l) {
                    final int n4 = array2[l].length() - 8;
                    final byte[] array3 = new byte[n4];
                    final byte[] array4 = new byte[8];
                    array2[l].getBytes(0, n4, array3, 0);
                    array2[l].getBytes(n4, n4 + 8, array4, 0);
                    int n5 = n4 % 7;
                    final int n6 = n4 % 3;
                    for (int n7 = 0; n7 < n4; ++n7) {
                        final byte b = array3[n7];
                        if (b >= 48 && b <= 57) {
                            array3[n7] = this.a(b, n5, 48, 57);
                        }
                        else if (b >= 65 && b <= 90) {
                            array3[n7] = this.a(b, n5, 65, 90);
                        }
                        else if (b >= 97 && b <= 122) {
                            array3[n7] = this.a(b, n5, 97, 122);
                        }
                        else if (b == 45) {
                            array3[n7] = 46;
                        }
                        else if (b == 46) {
                            array3[n7] = 45;
                        }
                        if ((n5 += n6) > 7) {
                            n5 = 1;
                        }
                    }
                    byte b2 = 0;
                    byte b3 = 0;
                    for (int n8 = 0; n8 < 4; ++n8) {
                        final byte[] array5 = array4;
                        final int n9 = n8;
                        array5[n9] -= 52;
                    }
                    for (int n10 = 4; n10 < 8; ++n10) {
                        final byte[] array6 = array4;
                        final int n11 = n10;
                        array6[n11] -= 55;
                    }
                    for (int n12 = 0; n12 < n4; n12 += 2) {
                        b2 += array3[n12];
                    }
                    for (int n13 = 1; n13 < n4; n13 += 2) {
                        b3 += array3[n13];
                    }
                    String s3 = String.valueOf(b2);
                    String s4 = String.valueOf(b3);
                    while (s3.length() < 4) {
                        s3 = "0" + s3;
                    }
                    while (s4.length() < 4) {
                        s4 = "0" + s4;
                    }
                    final byte[] array7 = new byte[8];
                    s3.getBytes(0, 4, array7, 0);
                    s4.getBytes(0, 4, array7, 4);
                    if (new String(array7, 0).equals(new String(array4, 0))) {
                        final String s5 = new String(array3, 0);
                        String substring;
                        if (s5.startsWith(c("r\r\\*"))) {
                            substring = s5.substring(4);
                        }
                        else {
                            substring = s5;
                        }
                        if (s2.equalsIgnoreCase(substring)) {
                            this.cB = true;
                        }
                    }
                }
            }
        }
        final String parameter3 = this.getParameter(c("w\u001fLhKk\u0011"));
        if (parameter3 != null && !parameter3.equalsIgnoreCase("NO")) {
            try {
                this.bD = new URL(this.getDocumentBase(), parameter3);
            }
            catch (MalformedURLException ex6) {
                this.bD = null;
            }
        }
        if (this.getParameter(c("w\u001fLjGr\u001cYeO`")).equalsIgnoreCase(c("\\?x"))) {
            this.bZ = true;
        }
        Container container;
        for (container = this.getParent(); !(container instanceof Frame); container = ((Frame)container).getParent()) {}
        (this.b = (Frame)container).setCursor(3);
        final String parameter4 = this.getParameter(c("j\fNvKh\u001d"));
        if (parameter4 != null && !parameter4.equalsIgnoreCase("NO")) {
            this.ci = this.a(parameter4);
            if (this.ci != null) {
                String parameter5 = this.getParameter(c("j\fNvKh\u001ds"));
                if (parameter5 == null) {
                    parameter5 = "0";
                }
                this.cj = Integer.valueOf(parameter5);
                String parameter6 = this.getParameter(c("j\fNvKh\u001dr"));
                if (parameter6 == null) {
                    parameter6 = "0";
                }
                this.ck = Integer.valueOf(parameter6);
            }
        }
        this.bQ = this.getParameter(c("H\u0013EW{K9"));
        if (this.bQ == null) {
            this.bQ = "10";
        }
        this.bF = Integer.valueOf(this.bQ);
        this.bQ = null;
        this.bQ = this.getParameter(c("i\u0013LlVh\u0013E"));
        if (this.bQ == null) {
            this.bQ = "10";
        }
        this.by = Integer.valueOf(this.bQ);
        this.bQ = null;
        this.bQ = this.getParameter(c("i\u0013LlVh\u001bS"));
        if (this.bQ == null) {
            this.bQ = c("0J\u001b");
        }
        this.bx = Integer.valueOf(this.bQ);
        this.bQ = null;
        this.bQ = this.getParameter(c("i\u0013LlVu\u0015\\aP"));
        if (this.bQ == null) {
            this.bQ = c("4J\u001b");
        }
        this.bz = Double.valueOf(this.bQ) / 100.0;
        this.bn = this.getParameter(c("l\u0017JcG4"));
        this.bo = this.getParameter(c("l\u0017JcG7"));
        this.bp = this.getParameter(c("l\u0017JcG6"));
        this.bq = this.getParameter(c("l\u0017JcG1"));
        this.br = this.getParameter(c("l\u0017JcG0"));
        this.bs = this.getParameter(c("l\u0017JcG3"));
        this.bG = this.getParameter(c("l\u0014_aPd\u0019_mT`"));
        if (this.bG.equalsIgnoreCase(c("\\?x"))) {
            this.bu = true;
        }
        else {
            this.bu = false;
        }
        this.bG = null;
        this.bG = this.getParameter(c("i\u0013LlVv\u0015^vA`\u001e"));
        if (this.bG.equalsIgnoreCase(c("\\?x"))) {
            this.bB = true;
        }
        else {
            this.bB = false;
        }
        this.bH = this.getParameter(c("w\u001fX"));
        if (this.bH == null) {
            this.bH = "1";
        }
        this.bM = this.getParameter(c("w\u0015_|"));
        if (this.bM == null) {
            this.bM = "4";
        }
        this.bN = this.getParameter(c("w\u0015_}"));
        if (this.bN == null) {
            this.bN = "6";
        }
        this.bO = this.getParameter(c("w\u0015_~"));
        if (this.bO == null) {
            this.bO = "8";
        }
        this.bP = this.getParameter(c("g\u001bHoP"));
        if (this.bP == null) {
            this.bP = "64";
        }
        this.bR = this.getParameter(c("g\u001bHoE"));
        if (this.bR == null) {
            this.bR = "96";
        }
        this.bS = this.getParameter(c("g\u001bHo@"));
        if (this.bS == null) {
            this.bS = c("4L\u001b");
        }
        this.bT = this.getParameter(c("f\u0016NePg\u001bHo"));
        if (this.bT.equalsIgnoreCase(c("\\?x"))) {
            this.s = true;
        }
        else {
            this.s = false;
        }
        this.bI = this.getParameter(c("h\u001fF`Gi\u001bR"));
        this.bJ = this.getParameter(c("u\bBkPl\u000eR"));
        this.bL = this.getParameter(c("u\bBkPl\u000eRH"));
        this.bE = Integer.valueOf(this.bI);
        this.cn = Integer.valueOf(this.bJ);
        this.co = Integer.valueOf(this.bL);
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
        this.cC = Integer.valueOf(this.bH);
        this.cE = Float.valueOf(this.bM) / 100.0f;
        this.cF = Float.valueOf(this.bN) / 100.0f;
        this.cG = Float.valueOf(this.bO) / 100.0f;
        this.bU = 0.2f;
        this.k = Integer.valueOf(this.bP);
        this.j = Integer.valueOf(this.bR);
        this.h = Integer.valueOf(this.bS);
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
        for (int n14 = 0; n14 < this.dm; ++n14) {
            this.B[n14] = this.i;
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
        for (int n15 = 0; n15 < 1024; ++n15) {
            this.cV[n15] = (float)Math.sin(n15 * 2.0 * 3.141592653589793 / 1024.0);
        }
        for (int n16 = 0; n16 < 256; ++n16) {
            this.cV[n16 + 1024] = this.cV[n16];
        }
        if (this.bB) {
            this.v = new int[1280];
            this.w = new int[1280];
            this.x = new int[1280];
            for (int n17 = 0; n17 < 256; ++n17) {
                this.v[n17] = 0;
                this.w[n17] = 0;
                this.x[n17] = 0;
            }
            for (int n18 = 256; n18 < 512; ++n18) {
                this.v[n18] = n18 - 256 << 16;
                this.w[n18] = n18 - 256 << 8;
                this.x[n18] = n18 - 256;
            }
            for (int n19 = 512; n19 < 1280; ++n19) {
                this.v[n19] = 16711680;
                this.w[n19] = 65280;
                this.x[n19] = 255;
            }
            this.cc = new double[18];
            this.cb = new double[24];
            this.cD = new double[24];
            this.u = new int[6];
            this.cs = new int[this.ba * 6];
            this.bA = new int[65536];
            double n20 = -0.39269908169872414;
            for (int n21 = 0; n21 < 256; ++n21) {
                double n22 = -0.39269908169872414;
                final double n23 = Math.sin(n20) * 220.0;
                for (int n24 = 0; n24 < 256; ++n24) {
                    int n25 = (int)((Math.sin(n22) * 220.0 + n23) * this.bz);
                    if (n25 > this.bx) {
                        n25 = this.bx;
                    }
                    if (n25 < this.by) {
                        n25 = this.by;
                    }
                    this.bA[n21 * 256 + n24] = n25;
                    n22 += 0.02454369260617026;
                }
                n20 += 0.02454369260617026;
            }
            this.b();
        }
        if (this.s) {
            this.t = new int[this.dm];
            for (int n26 = 0; n26 < this.dm; ++n26) {
                this.t[n26] = this.i;
            }
        }
        try {
            this.g();
        }
        catch (NoSuchMethodError noSuchMethodError) {
            this.g();
        }
        this.n();
        this.cf = this.createImage(this.cu, this.ct + this.cQ);
        this.ce = this.cf.getGraphics();
        if (!this.cB) {
            (this.dt = new Lware(this.getAppletContext(), new Label(c("D\u0014M}\u0002Q\u0017Jtap\u0018N$Cu\nGaV%\u0018R$dd\u0018Bk\u0002F\u0013^gAlZ\u001a=\u001b3U\u0012<\f")))).setTitle(c("Q\u0017Jt\u0002F\u000fIa\u0002D\n[hGqZI}\u0002C\u001bImM%9BqAf\u0013"));
            this.dt.hide();
        }
    }
    
    void g() {
        this.cr = new MemoryImageSource(this.ds, this.ba, new DirectColorModel(24, 16711680, 65280, 255), this.B, 0, this.ds);
        String s;
        try {
            s = System.getProperty(c("o\u001b]e\fs\u001fYwKj\u0014"));
        }
        catch (SecurityException ex) {
            s = c("p\u0014@");
        }
        if (!s.startsWith(c("4T\u001b"))) {
            try {
                this.cr.setAnimated(true);
                this.cr.setFullBufferUpdates(true);
                this.bg = this.createImage(this.cr);
                this.cr.newPixels();
                this.bw = true;
            }
            catch (NoSuchMethodError noSuchMethodError) {
                this.bw = false;
            }
        }
        if (!this.bw) {
            this.cr = null;
            this.cq = new anfy(this.ds, this.ba, new DirectColorModel(24, 16711680, 65280, 255), this.B, 0, this.ds);
            this.bg = this.createImage(this.cq);
        }
    }
    
    private final void a(int n, int n2, int n3, int n4, int n5, int n6, int n7, int n8) {
        final int n9 = 0;
        final int dj = this.dj;
        final int[] ch = this.cH;
        if (n2 == n6) {
            return;
        }
        if (n6 < n2) {
            n2 -= n6;
            int n10 = n9 + (n6 + n6 + n6 + dj);
            final int n11 = (n - n5 << 16) / n2;
            final int n12 = (n3 - n7) / n2;
            final int n13 = (n4 - n8) / n2;
            n5 <<= 16;
            do {
                ch[n10++] = n5 >> 16;
                ch[n10++] = (n7 & 0xFF00);
                ch[n10++] = (n8 & 0xFF00);
                n5 += n11;
                n7 += n12;
                n8 += n13;
            } while (--n2 > 0);
            return;
        }
        n6 -= n2;
        int n14 = n9 + (n2 + n2 + n2);
        final int n15 = (n5 - n << 16) / n6;
        final int n16 = (n7 - n3) / n6;
        final int n17 = (n8 - n4) / n6;
        n <<= 16;
        do {
            ch[n14++] = n >> 16;
            ch[n14++] = (n3 & 0xFF00);
            ch[n14++] = (n4 & 0xFF00);
            n += n15;
            n3 += n16;
            n4 += n17;
        } while (--n6 > 0);
    }
    
    private final boolean h() {
        this.bV = new MediaTracker(this);
        this.showStatus(c("I\u0015J`Kk\u001d\u000bmOd\u001dNw\f+T"));
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
        this.bG = null;
        this.bG = this.getParameter(c("g\u001bHoKh\u001bLa"));
        if (!this.bG.equalsIgnoreCase("NO")) {
            final Image a = this.a(this.bG);
            if (a == null) {
                this.showStatus(c("@\bYkP%\u0016DeFl\u0014L$@d\u0019@cPj\u000fE`\u0002l\u0017JcG"));
            }
            else if (a.getWidth(this) == this.ds && a.getHeight(this) == this.ba) {
                final PixelGrabber pixelGrabber = new PixelGrabber(a, 0, 0, this.ds, this.ba, this.t, 0, this.ds);
                try {
                    pixelGrabber.grabPixels();
                }
                catch (InterruptedException ex) {}
            }
        }
        if (this.bh.getWidth(this) == 256) {
            final PixelGrabber pixelGrabber2 = new PixelGrabber(this.bh, 0, 0, 256, 256, this.dd, 0, 256);
            try {
                pixelGrabber2.grabPixels();
            }
            catch (InterruptedException ex2) {}
            final PixelGrabber pixelGrabber3 = new PixelGrabber(this.bi, 0, 0, 256, 256, this.de, 0, 256);
            try {
                pixelGrabber3.grabPixels();
            }
            catch (InterruptedException ex3) {}
            final PixelGrabber pixelGrabber4 = new PixelGrabber(this.bj, 0, 0, 256, 256, this.df, 0, 256);
            try {
                pixelGrabber4.grabPixels();
            }
            catch (InterruptedException ex4) {}
            final PixelGrabber pixelGrabber5 = new PixelGrabber(this.bk, 0, 0, 256, 256, this.dg, 0, 256);
            try {
                pixelGrabber5.grabPixels();
            }
            catch (InterruptedException ex5) {}
            final PixelGrabber pixelGrabber6 = new PixelGrabber(this.bl, 0, 0, 256, 256, this.dh, 0, 256);
            try {
                pixelGrabber6.grabPixels();
            }
            catch (InterruptedException ex6) {}
            final PixelGrabber pixelGrabber7 = new PixelGrabber(this.bm, 0, 0, 256, 256, this.di, 0, 256);
            try {
                pixelGrabber7.grabPixels();
            }
            catch (InterruptedException ex7) {}
        }
        else {
            final int[] array = new int[16384];
            final PixelGrabber pixelGrabber8 = new PixelGrabber(this.bh, 0, 0, 128, 128, array, 0, 128);
            try {
                pixelGrabber8.grabPixels();
            }
            catch (InterruptedException ex8) {}
            this.a(array, this.dd);
            final PixelGrabber pixelGrabber9 = new PixelGrabber(this.bi, 0, 0, 128, 128, array, 0, 128);
            try {
                pixelGrabber9.grabPixels();
            }
            catch (InterruptedException ex9) {}
            this.a(array, this.de);
            final PixelGrabber pixelGrabber10 = new PixelGrabber(this.bj, 0, 0, 128, 128, array, 0, 128);
            try {
                pixelGrabber10.grabPixels();
            }
            catch (InterruptedException ex10) {}
            this.a(array, this.df);
            final PixelGrabber pixelGrabber11 = new PixelGrabber(this.bk, 0, 0, 128, 128, array, 0, 128);
            try {
                pixelGrabber11.grabPixels();
            }
            catch (InterruptedException ex11) {}
            this.a(array, this.dg);
            final PixelGrabber pixelGrabber12 = new PixelGrabber(this.bl, 0, 0, 128, 128, array, 0, 128);
            try {
                pixelGrabber12.grabPixels();
            }
            catch (InterruptedException ex12) {}
            this.a(array, this.dh);
            final PixelGrabber pixelGrabber13 = new PixelGrabber(this.bm, 0, 0, 128, 128, array, 0, 128);
            try {
                pixelGrabber13.grabPixels();
            }
            catch (InterruptedException ex13) {}
            this.a(array, this.di);
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
    
    private final void a(int n, final int[] array) {
        final int n2 = this.cp[this.E[n]];
        final int n3 = this.cp[this.E[n++] + 1];
        final int n4 = this.cp[this.E[n]];
        final int n5 = this.cp[this.E[n++] + 1];
        final int n6 = this.cp[this.E[n]];
        final int n7 = this.cp[this.E[n++] + 1];
        final int n8 = this.cp[this.E[n]];
        final int n9 = this.cp[this.E[n++] + 1];
        int n10;
        int n11;
        if (n3 > n5) {
            n10 = n5;
            n11 = n3;
        }
        else {
            n10 = n3;
            n11 = n5;
        }
        if (n10 > n7) {
            n10 = n7;
        }
        else if (n11 < n7) {
            n11 = n7;
        }
        if (n10 > n9) {
            n10 = n9;
        }
        else if (n11 < n9) {
            n11 = n9;
        }
        int n12 = n11 - n10;
        if (n12 <= 0) {
            return;
        }
        this.a(n2, n3, 65280, 65280, n4, n5, 65280, 0);
        this.a(n4, n5, 65280, 0, n6, n7, 0, 0);
        this.a(n6, n7, 0, 0, n8, n9, 0, 65280);
        this.a(n8, n9, 0, 65280, n2, n3, 65280, 65280);
        final int ds = this.ds;
        final int[] ch = this.cH;
        final int[] b = this.B;
        int n13 = (n10 - 1) * ds;
        int n14 = n10 + n10 + n10;
        int n15 = n14 + this.dj;
        do {
            n13 += ds;
            int n16 = ch[n14++];
            int n17 = ch[n15++] - n16;
            int n18 = ch[n14++];
            final int n19 = ch[n15++];
            int n20 = ch[n14++];
            final int n21 = ch[n15++];
            if (n17 > 0) {
                final int n22 = (n19 - n18) / n17;
                final int n23 = (n21 - n20) / n17;
                do {
                    b[n13 + n16++] = array[(n20 & 0xFF00) + (n18 >> 8)];
                    n18 += n22;
                    n20 += n23;
                } while (--n17 > 0);
            }
        } while (--n12 > 0);
    }
    
    private final void a(int n, final short[] array, final short[] array2, final short[] array3, final int n2) {
        final int n3 = 128 - (int)(this.cD[this.E[n] + 2] * 220.0);
        final int n4 = (int)(this.cD[this.E[n]] * 128.0) + 127 << 8;
        final int n5 = (int)(this.cD[this.E[n] + 1] * 128.0) + 127 << 8;
        final int n6 = this.cp[this.E[n]];
        final int n7 = this.cp[this.E[n++] + 1];
        final int n8 = 128 - (int)(this.cD[this.E[n] + 2] * 220.0);
        final int n9 = (int)(this.cD[this.E[n]] * 128.0) + 127 << 8;
        final int n10 = (int)(this.cD[this.E[n] + 1] * 128.0) + 127 << 8;
        final int n11 = this.cp[this.E[n]];
        final int n12 = this.cp[this.E[n++] + 1];
        final int n13 = 128 - (int)(this.cD[this.E[n] + 2] * 220.0);
        final int n14 = (int)(this.cD[this.E[n]] * 128.0) + 127 << 8;
        final int n15 = (int)(this.cD[this.E[n] + 1] * 128.0) + 127 << 8;
        final int n16 = this.cp[this.E[n]];
        final int n17 = this.cp[this.E[n++] + 1];
        final int n18 = 128 - (int)(this.cD[this.E[n] + 2] * 220.0);
        final int n19 = (int)(this.cD[this.E[n]] * 128.0) + 127 << 8;
        final int n20 = (int)(this.cD[this.E[n] + 1] * 128.0) + 127 << 8;
        final int n21 = this.cp[this.E[n]];
        final int n22 = this.cp[this.E[n++] + 1];
        if (n3 < 0) {}
        if (n8 < 0) {}
        if (n13 < 0) {}
        if (n18 < 0) {}
        int n23;
        int n24;
        if (n7 > n12) {
            n23 = n12;
            n24 = n7;
        }
        else {
            n23 = n7;
            n24 = n12;
        }
        if (n23 > n17) {
            n23 = n17;
        }
        else if (n24 < n17) {
            n24 = n17;
        }
        if (n23 > n22) {
            n23 = n22;
        }
        else if (n24 < n22) {
            n24 = n22;
        }
        int n25 = n24 - n23;
        if (n25 <= 0) {
            return;
        }
        this.a(n6, n7, 65280, 65280, n11, n12, 65280, 0);
        this.a(n11, n12, 65280, 0, n16, n17, 0, 0);
        this.a(n16, n17, 0, 0, n21, n22, 0, 65280);
        this.a(n21, n22, 0, 65280, n6, n7, 65280, 65280);
        this.b(n6, n7, n4, n5, n11, n12, n9, n10);
        this.b(n11, n12, n9, n10, n16, n17, n14, n15);
        this.b(n16, n17, n14, n15, n21, n22, n19, n20);
        this.b(n21, n22, n19, n20, n6, n7, n4, n5);
        final int[] ch = this.cH;
        final int[] cs = this.cs;
        final int[] b = this.B;
        final int[] v = this.v;
        final int[] w = this.w;
        final int[] x = this.x;
        final int[] ba = this.bA;
        final int ds = this.ds;
        int n26 = (n23 - 1) * ds;
        int n27 = n23 + n23 + n23;
        int n28 = n27 + this.dj;
        do {
            n26 += ds;
            int n29 = ch[n27++];
            int n30 = ch[n28++] - n29;
            int n31 = cs[n27];
            int n32 = ch[n27++];
            final int n33 = cs[n28];
            final int n34 = ch[n28++];
            int n35 = cs[n27];
            int n36 = ch[n27++];
            final int n37 = cs[n28];
            final int n38 = ch[n28++];
            if (n30 > 0) {
                final int n39 = (n34 - n32) / n30;
                final int n40 = (n38 - n36) / n30;
                final int n41 = (n33 - n31) / n30;
                final int n42 = (n37 - n35) / n30;
                do {
                    final int n43 = (n36 & 0xFF00) + (n32 >> 8);
                    final int n44 = (n35 & 0xFF00) + (n31 >> 8);
                    b[n26 + n29++] = (v[array[n43] + ba[n44]] | w[array2[n43] + ba[n44]] | x[array3[n43] + ba[n44]]);
                    n32 += n39;
                    n36 += n40;
                    n31 += n41;
                    n35 += n42;
                } while (--n30 > 0);
            }
        } while (--n25 > 0);
    }
    
    public final synchronized boolean mouseDown(final Event event, final int n, final int n2) {
        if (!this.cB) {
            this.dt.show();
            this.dt.toFront();
            this.dt.requestFocus();
        }
        else if (this.bD != null) {
            if (this.bZ) {
                this.getAppletContext().showDocument(this.bD, this.getParameter(c("w\u001fLbPd\u0017NjCh\u001f")));
            }
            else {
                this.getAppletContext().showDocument(this.bD);
            }
        }
        return true;
    }
    
    public final boolean mouseEnter(final Event event, final int n, final int n2) {
        this.showStatus(this.cX);
        return true;
    }
    
    public final boolean mouseExit(final Event event, final int n, final int n2) {
        this.showStatus("");
        return true;
    }
    
    public synchronized boolean mouseMove(final Event event, final int n, final int n2) {
        if (this.bu) {
            this.z = System.currentTimeMillis();
            this.ca = true;
            this.e = (this.ct / 2 - n2) * this.bU / this.ct * 2.0f;
            this.f = (this.cu / 2 - n) * -this.bU / this.cu * 2.0f;
            this.g = 0.0f;
        }
        return true;
    }
    
    public void paint(final Graphics graphics) {
        if (!this.R) {
            if (this.C) {
                switch (this.bt) {
                    case 1: {
                        this.ce.drawImage(this.bh, 0, 0, this.cu, this.ct, this);
                        break;
                    }
                    case 2: {
                        this.ce.drawImage(this.bi, 0, 0, this.cu, this.ct, this);
                        break;
                    }
                    case 3: {
                        this.ce.drawImage(this.bj, 0, 0, this.cu, this.ct, this);
                        break;
                    }
                    case 4: {
                        this.ce.drawImage(this.bk, 0, 0, this.cu, this.ct, this);
                        break;
                    }
                    case 5: {
                        this.ce.drawImage(this.bl, 0, 0, this.cu, this.ct, this);
                        break;
                    }
                    case 6: {
                        this.ce.drawImage(this.bm, 0, 0, this.cu, this.ct, this);
                        break;
                    }
                }
            }
            else if (this.cC == 1) {
                this.ce.drawImage(this.bg, 0, 0, this);
            }
            else {
                this.j();
                this.ce.drawImage(this.bg, 0, 0, this.cu, this.ct, this);
            }
            this.ce.setColor(Color.black);
            this.ce.drawString(c("R\u001bBp"), this.cu / 2 - 16 + 1, this.ct / 2 - 4 - 6 + 1);
            this.ce.drawString(c("I\u0015J`Kk\u001d"), this.cu / 2 - 20 + 1, this.ct / 2 + 2 + 1);
            this.ce.setColor(Color.white);
            this.ce.drawString(c("R\u001bBp"), this.cu / 2 - 16, this.ct / 2 - 4 - 6);
            this.ce.drawString(c("I\u0015J`Kk\u001d"), this.cu / 2 - 20, this.ct / 2 + 2);
            graphics.drawImage(this.cf, 0, 0, this);
            return;
        }
        if (this.bg != null) {
            if (this.cC == 1) {
                this.ce.drawImage(this.bg, 0, 0, this);
            }
            else {
                this.j();
                this.ce.drawImage(this.bg, 0, 0, this.cu, this.ct, this);
            }
            if (this.ci != null) {
                this.i();
            }
            if (this.db) {
                this.b(this.ce);
            }
            graphics.drawImage(this.cf, 0, 0, this);
        }
    }
    
    private final void b(int n, int n2, int n3, int n4, int n5, int n6, int n7, int n8) {
        final int n9 = 0;
        final int[] cs = this.cs;
        final int dj = this.dj;
        if (n2 == n6) {
            return;
        }
        if (n6 < n2) {
            n2 -= n6;
            int n10 = n9 + (n6 + n6 + n6 + dj);
            final int n11 = (n - n5 << 16) / n2;
            final int n12 = (n3 - n7) / n2;
            final int n13 = (n4 - n8) / n2;
            n5 <<= 16;
            do {
                cs[n10++] = n5 >> 16;
                cs[n10++] = (n7 & 0xFF00);
                cs[n10++] = (n8 & 0xFF00);
                n5 += n11;
                n7 += n12;
                n8 += n13;
            } while (--n2 > 0);
            return;
        }
        n6 -= n2;
        int n14 = n9 + (n2 + n2 + n2);
        final int n15 = (n5 - n << 16) / n6;
        final int n16 = (n7 - n3) / n6;
        final int n17 = (n8 - n4) / n6;
        n <<= 16;
        do {
            cs[n14++] = n >> 16;
            cs[n14++] = (n3 & 0xFF00);
            cs[n14++] = (n4 & 0xFF00);
            n += n15;
            n3 += n16;
            n4 += n17;
        } while (--n6 > 0);
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
    
    public synchronized void a(final Image image) {
        int checkImage = 0;
        this.prepareImage(image, this.cu, this.ct, this);
        this.notifyAll();
        while ((checkImage & 0xF0) == 0x0) {
            Thread.yield();
            checkImage = this.checkImage(image, this.cu, this.ct, this);
        }
    }
    
    public synchronized void j() {
        int checkImage = 0;
        this.prepareImage(this.bg, this.cu, this.ct, this);
        this.notifyAll();
        while ((checkImage & 0xF0) == 0x0) {
            Thread.yield();
            checkImage = this.checkImage(this.bg, this.cu, this.ct, this);
        }
    }
    
    public final void k() {
        try {
            if (this.bw) {
                this.cr.newPixels();
                return;
            }
            this.cq.startProduction(this.cq.getConsumer());
        }
        catch (NoSuchMethodError noSuchMethodError) {}
    }
    
    private final void a(final int[] array, final int[] array2) {
        for (int n = 0, i = 0; i < 65536; i += 256, n += 128) {
            this.du = 0;
            while (this.du < 256) {
                array2[i + this.du] = array[n + (this.du >> 1)];
                ++this.du;
            }
            final int n2 = i;
            i += 256;
            this.du = 0;
            while (this.du < 256) {
                array2[i + this.du] = array2[n2 + this.du];
                ++this.du;
            }
        }
    }
    
    private final void l() {
        int n = 8;
        int n2 = 0;
        int n3 = 0;
        final float n4 = this.ch[0];
        final float n5 = this.ch[1];
        final float n6 = this.ch[2];
        final float n7 = this.ch[3];
        final float n8 = this.ch[4];
        final float n9 = this.ch[5];
        final float n10 = this.ch[6];
        final float n11 = this.ch[7];
        final float n12 = this.ch[8];
        do {
            final float n13 = (float)this.cb[n2++];
            final float n14 = (float)this.cb[n2++];
            final float n15 = (float)this.cb[n2++];
            final float n16 = n13 * n4 + n14 * n7 + n15 * n10;
            final float n17 = n13 * n5 + n14 * n8 + n15 * n11;
            final float n18 = n13 * n6 + n14 * n9 + n15 * n12;
            this.cD[n3++] = n16;
            this.cD[n3++] = n17;
            this.cD[n3++] = n18;
        } while (--n > 0);
    }
    
    private final void m() {
        int n = 8;
        int n2 = 0;
        int n3 = 0;
        final float n4 = (float)Math.sin(this.e);
        final float n5 = (float)Math.cos(this.e);
        final float n6 = (float)Math.sin(this.f);
        final float n7 = (float)Math.cos(this.f);
        final float n8 = (float)Math.sin(this.g);
        final float n9 = (float)Math.cos(this.g);
        final float n10 = n4 * n8;
        final float n11 = n4 * n9;
        this.G = n7 * n9 + n10 * n6;
        this.H = n11 * n6 - n8 * n7;
        this.I = n6 * n5;
        this.J = n8 * n5;
        this.K = n5 * n9;
        this.L = -n4;
        this.M = n10 * n7 - n6 * n9;
        this.N = n6 * n8 + n11 * n7;
        this.O = n5 * n7;
        final float n12 = this.ch[0] * this.G + this.ch[1] * this.J + this.ch[2] * this.M;
        final float n13 = this.ch[0] * this.H + this.ch[1] * this.K + this.ch[2] * this.N;
        final float n14 = this.ch[0] * this.I + this.ch[1] * this.L + this.ch[2] * this.O;
        final float n15 = this.ch[3] * this.G + this.ch[4] * this.J + this.ch[5] * this.M;
        final float n16 = this.ch[3] * this.H + this.ch[4] * this.K + this.ch[5] * this.N;
        final float n17 = this.ch[3] * this.I + this.ch[4] * this.L + this.ch[5] * this.O;
        final float n18 = this.ch[6] * this.G + this.ch[7] * this.J + this.ch[8] * this.M;
        final float n19 = this.ch[6] * this.H + this.ch[7] * this.K + this.ch[8] * this.N;
        final float n20 = this.ch[6] * this.I + this.ch[7] * this.L + this.ch[8] * this.O;
        this.ch[0] = n12;
        this.ch[1] = n13;
        this.ch[2] = n14;
        this.ch[3] = n15;
        this.ch[4] = n16;
        this.ch[5] = n17;
        this.ch[6] = n18;
        this.ch[7] = n19;
        this.ch[8] = n20;
        do {
            final float n21 = this.cl[n2++];
            final float n22 = this.cl[n2++];
            final float n23 = this.cl[n2++];
            final float n24 = n21 * n12 + n22 * n15 + n23 * n18;
            final float n25 = n21 * n13 + n22 * n16 + n23 * n19;
            final int n26 = (int)(n21 * n14 + n22 * n17 + n23 * n20) + this.dB;
            this.cp[n3++] = (int)(n24 * (256.0 / n26) + this.bc);
            this.cp[n3++] = (int)(n25 * (256.0 / n26) + this.bb);
            this.cp[n3++] = n26;
        } while (--n > 0);
    }
    
    public void run() {
        this.dl.setPriority(this.co);
        if (!this.R) {
            if (!(this.R = this.h())) {
                this.showStatus(c("@\bYkP%\u0016DeFl\u0014L$Kh\u001bLaQ$Z"));
                this.stop();
                return;
            }
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
        this.dl.setPriority(this.cn);
        this.showStatus("");
        System.gc();
        this.y = System.currentTimeMillis();
        final Graphics graphics = this.getGraphics();
        if (this.ci != null && !this.c) {
            this.c = this.e();
        }
        if (this.bD != null) {
            this.b.setCursor(12);
        }
        else {
            this.b.setCursor(0);
        }
        while (this.dl != null) {
            if (this.s) {
                try {
                    System.arraycopy(this.t, 0, this.B, 0, this.dm);
                }
                catch (ArrayIndexOutOfBoundsException ex) {
                    this.stop();
                }
                catch (ArrayStoreException ex2) {
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
            if (this.ci != null) {
                this.i();
            }
            if (this.db) {
                this.b(this.ce);
            }
            graphics.drawImage(this.cf, 0, 0, this);
            this.q();
        }
    }
    
    byte a(final int n, final int n2, final int n3, final int n4) {
        final int n5 = n - n2;
        if (n5 >= n3) {
            return (byte)n5;
        }
        return (byte)(n4 - (n3 - n5 - 1));
    }
    
    public void n() {
        this.db = false;
        final String parameter = this.getParameter(c("q\u001fSpQf\bDhN"));
        if (parameter != null && !parameter.equalsIgnoreCase("NO")) {
            String s = this.getParameter(c("q\u001fSpV|\nN"));
            if (s == null) {
                s = c("m\u0015YmXj\u0014_eN");
            }
            if (s.equals(c("m\u0015YmXj\u0014_eN"))) {
                this.cL = 0;
            }
            else if (s.equals(c("s\u001fYpKf\u001bG"))) {
                this.cL = 1;
            }
            else if (s.equals(c("\u007f\u0015DiKk\u001d"))) {
                this.cL = 2;
            }
            else if (s.equals(c("l\u0014]~Mj\u0017BjE"))) {
                this.cL = 3;
            }
            if (this.cL == 0) {
                this.a(parameter, 0);
                if (this.cI != null) {
                    this.db = true;
                }
            }
            else {
                this.a(parameter, 1);
                if (this.bC != null) {
                    this.db = true;
                }
            }
        }
        if (this.db) {
            String parameter2 = this.getParameter(c("q\u001fSpQu\u001fN`"));
            if (parameter2 == null) {
                parameter2 = "0";
            }
            this.do = Integer.valueOf(parameter2);
            String s2 = this.getParameter(c("q\u001fSpDj\u0014_"));
            if (s2 == null) {
                s2 = c("D\bBeN");
            }
            int n = 0;
            if (this.getParameter(c("q\u001fSp@j\u0016O")).equalsIgnoreCase(c("\\?x"))) {
                ++n;
            }
            String parameter3 = this.getParameter(c("q\u001fSpKq\u001bGmA"));
            if (parameter3 == null) {
                parameter3 = "NO";
            }
            if (parameter3.equalsIgnoreCase(c("\\?x"))) {
                n += 2;
            }
            String parameter4 = this.getParameter(c("q\u001fSpQl\u0000N"));
            if (parameter4 == null) {
                parameter4 = "12";
            }
            this.Q = new Font(s2, n, Integer.valueOf(parameter4));
            if (this.getParameter(c("q\u001fSpQm\u001bOkU")).equalsIgnoreCase(c("\\?x"))) {
                this.dc = true;
            }
            else {
                this.dc = false;
            }
            this.cY = new Color(Integer.valueOf(this.getParameter(c("Q\u001fSpaj\u0016y"))), Integer.valueOf(this.getParameter(c("Q\u001fSpaj\u0016l"))), Integer.valueOf(this.getParameter(c("Q\u001fSpaj\u0016i"))));
            this.cM = new Color(Integer.valueOf(this.getParameter(c("Q\u001fSpqF\u0015GV"))), Integer.valueOf(this.getParameter(c("Q\u001fSpqF\u0015GC"))), Integer.valueOf(this.getParameter(c("Q\u001fSpqF\u0015GF"))));
            this.dp = this.size().width;
            this.dq = this.size().height;
            if (this.cL == 0) {
                String parameter5 = this.getParameter(c("q\u001fSpMc\u001cXaV"));
                if (parameter5 == null) {
                    parameter5 = "0";
                }
                this.dy = Integer.valueOf(parameter5);
                if (this.dy < 0) {
                    this.dy = 0;
                }
                String parameter6 = this.getParameter(c("Q\u001fSphp\u0017[EOu"));
                if (parameter6 == null) {
                    parameter6 = "0";
                }
                this.a = Integer.valueOf(parameter6);
                String parameter7 = this.getParameter(c("Q\u001fSphp\u0017[WRa"));
                if (parameter7 == null) {
                    parameter7 = "0";
                }
                this.dn = Integer.valueOf(parameter7);
                String parameter8 = this.getParameter(c("Q\u001fSpql\u0014NEOu"));
                if (parameter8 == null) {
                    parameter8 = "0";
                }
                this.cO = Integer.valueOf(parameter8);
                String parameter9 = this.getParameter(c("Q\u001fSpql\u0014NWRa"));
                if (parameter9 == null) {
                    parameter9 = "0";
                }
                this.cS = Integer.valueOf(parameter9);
                String parameter10 = this.getParameter(c("Q\u001fSpql\u0014NELb\u0016N"));
                if (parameter10 == null) {
                    parameter10 = "0";
                }
                this.cP = Integer.valueOf(parameter10);
                final FontMetrics fontMetrics = this.getGraphics().getFontMetrics(this.Q);
                this.cK = fontMetrics.stringWidth(this.cI);
                this.cJ = fontMetrics.getHeight();
                this.A = fontMetrics.getMaxDescent();
                this.dv = this.dp;
                if (this.dy < this.cJ - this.A) {
                    this.dy = this.cJ - this.A;
                }
                else if (this.dy > this.dq - this.A) {
                    this.dy = this.dq - this.A;
                }
                if (this.cO != 0) {
                    this.cT = new int[this.dp + 360];
                    this.cU = new int[this.dp + 360];
                    for (int i = 0; i < this.dp + 360; ++i) {
                        this.cT[i] = (int)(this.cO * Math.sin(this.cP * i * 3.141592653589793 / 180.0)) - this.cJ - this.A + this.dy;
                        this.cU[i] = this.cT[i] - this.ct;
                    }
                    this.cN = 360;
                    this.cQ = this.cJ + this.A + 1;
                    this.cR = this.cQ - 1;
                }
            }
            else {
                if (this.cL == 1) {
                    String parameter11 = this.getParameter(c("q\u001fSpTv\nJgG"));
                    if (parameter11 == null) {
                        parameter11 = "10";
                    }
                    final int intValue = Integer.valueOf(parameter11);
                    final FontMetrics fontMetrics2 = this.getGraphics().getFontMetrics(this.Q);
                    this.S = fontMetrics2.getHeight() + intValue;
                    this.dr = new int[this.bC.length];
                    this.bf = 0;
                    while (this.bf < this.bC.length) {
                        this.dr[this.bf] = (this.dp - fontMetrics2.stringWidth(this.bC[this.bf])) / 2;
                        ++this.bf;
                    }
                    this.bY = -this.S;
                    return;
                }
                if (this.cL >= 2) {
                    String parameter12 = this.getParameter(c("q\u001fSpOl\u0014MkLq"));
                    if (parameter12 == null) {
                        parameter12 = "2";
                    }
                    this.da = Integer.valueOf(parameter12);
                    String parameter13 = this.getParameter(c("q\u001fSpOd\u0002MkLq"));
                    if (parameter13 == null) {
                        parameter13 = "72";
                    }
                    this.cZ = Integer.valueOf(parameter13);
                    this.cd = this.cZ - this.da;
                    this.Q = null;
                    this.dE = new Font[this.cd];
                    int da = this.da;
                    this.bf = 0;
                    while (this.bf < this.cd) {
                        this.dE[this.bf] = new Font(s2, n, da++);
                        ++this.bf;
                    }
                    this.be = this.dp / 2.0f;
                    this.bd = this.dq / 2.0f;
                    if (this.cL == 3) {
                        this.dD = this.cd - 1;
                        return;
                    }
                    this.dD = 0;
                }
            }
        }
    }
    
    public void b(final Graphics graphics) {
        switch (this.cL) {
            case 0: {
                this.a(graphics);
            }
            case 1: {
                this.c(graphics);
            }
            default: {
                this.d(graphics);
            }
        }
    }
    
    public void start() {
        if (this.dl == null) {
            (this.dl = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.dl != null && this.dl.isAlive()) {
            this.dl.stop();
        }
        this.dl = null;
    }
    
    private final void o() {
        int i = this.P;
        do {
            --i;
            final int n = this.dz[i];
            switch (n >> 2) {
                default: {
                    continue;
                }
                case 5: {
                    this.a(n, this.di);
                    continue;
                }
                case 4: {
                    this.a(n, this.dh);
                    continue;
                }
                case 3: {
                    this.a(n, this.dg);
                    continue;
                }
                case 2: {
                    this.a(n, this.df);
                    continue;
                }
                case 1: {
                    this.a(n, this.de);
                    continue;
                }
                case 0: {
                    this.a(n, this.dd);
                    continue;
                }
            }
        } while (i > 0);
    }
    
    private final void p() {
        int i = this.P;
        do {
            --i;
            final int n = this.dz[i];
            switch (n >> 2) {
                default: {
                    continue;
                }
                case 5: {
                    this.a(n, this.cA, this.Z, this.q, this.u[i]);
                    continue;
                }
                case 4: {
                    this.a(n, this.cz, this.Y, this.p, this.u[i]);
                    continue;
                }
                case 3: {
                    this.a(n, this.cy, this.X, this.o, this.u[i]);
                    continue;
                }
                case 2: {
                    this.a(n, this.cx, this.W, this.n, this.u[i]);
                    continue;
                }
                case 1: {
                    this.a(n, this.cw, this.V, this.m, this.u[i]);
                    continue;
                }
                case 0: {
                    this.a(n, this.cv, this.U, this.l, this.u[i]);
                    continue;
                }
            }
        } while (i > 0);
    }
    
    public TmapCube() {
        this.bw = false;
        this.R = false;
        this.cg = false;
        this.c = false;
        this.cB = false;
        this.bZ = false;
        this.C = false;
        this.ch = new float[] { 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f };
        this.cl = new float[] { -64.0f, -64.0f, -64.0f, 64.0f, -64.0f, -64.0f, 64.0f, 64.0f, -64.0f, -64.0f, 64.0f, -64.0f, -64.0f, -64.0f, 64.0f, 64.0f, -64.0f, 64.0f, 64.0f, 64.0f, 64.0f, -64.0f, 64.0f, 64.0f };
        this.E = new int[] { 9, 6, 3, 0, 12, 15, 18, 21, 3, 6, 18, 15, 12, 21, 9, 0, 0, 3, 15, 12, 9, 21, 18, 6 };
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void c(final Graphics graphics) {
        graphics.setFont(this.Q);
        this.bY += this.do;
        if (this.bY > this.dq + this.bC.length * this.S) {
            this.bY = -this.S;
        }
        if (this.dc) {
            for (int i = 0; i < this.bC.length; ++i) {
                final String s = this.bC[i];
                final int n = this.dr[i];
                final int n2 = this.dq - this.bY + i * this.S;
                graphics.setColor(this.cM);
                graphics.drawString(s, n + 1, n2 + 1);
                graphics.setColor(this.cY);
                graphics.drawString(s, n, n2);
            }
            return;
        }
        graphics.setColor(this.cY);
        for (int j = 0; j < this.bC.length; ++j) {
            graphics.drawString(this.bC[j], this.dr[j], this.dq - this.bY + j * this.S);
        }
    }
    
    public synchronized void q() {
        Thread.yield();
        this.dk.sync();
        final long n = 10L - (System.currentTimeMillis() - this.y);
        if (n > 0L) {
            try {
                Thread.sleep(n);
            }
            catch (InterruptedException ex) {}
        }
        else {
            try {
                Thread.sleep(1L);
            }
            catch (InterruptedException ex2) {}
        }
        this.y = System.currentTimeMillis();
        try {
            Thread.sleep(this.bF);
        }
        catch (InterruptedException ex3) {}
    }
    
    public void d(final Graphics graphics) {
        final String s = this.bC[this.dC];
        graphics.setFont(this.dE[this.dD]);
        final FontMetrics fontMetrics = graphics.getFontMetrics(this.dE[this.dD]);
        final int n = (int)(this.be - fontMetrics.stringWidth(s) / 2.0f);
        final int n2 = (int)(this.bd + fontMetrics.getHeight() / 4.0f);
        if (this.dc) {
            graphics.setColor(this.cM);
            graphics.drawString(s, n + 1, n2 + 1);
        }
        graphics.setColor(this.cY);
        graphics.drawString(s, n, n2);
        if (this.cL == 3) {
            this.dD -= this.do;
            if (this.dD <= 1) {
                this.dD = this.cd - 1;
                ++this.dC;
                if (this.dC >= this.bC.length) {
                    this.dC = 0;
                }
            }
        }
        else {
            this.dD += this.do;
            if (this.dD >= this.cd) {
                this.dD = 0;
                ++this.dC;
                if (this.dC >= this.bC.length) {
                    this.dC = 0;
                }
            }
        }
    }
    
    private static String c(final String s) {
        final char[] charArray = s.toCharArray();
        final int length = charArray.length;
        int n = 0;
    Label_0010:
        while (true) {
            while (true) {
                int n2 = 0;
                char c = '\u0005';
                char[] array2;
                char[] array = array2 = charArray;
                int n4;
                int n3 = n4 = n;
                while (true) {
                    array[n3] = (char)(c ^ array2[n4]);
                    Label_0047: {
                        Label_0039: {
                            Label_0029: {
                            Label_0021:
                                while (true) {
                                    ++n;
                                    ++n2;
                                    if (length == n) {
                                        break Label_0010;
                                    }
                                    switch (n2) {
                                        case 5: {
                                            continue Label_0010;
                                        }
                                        case 1: {
                                            break Label_0021;
                                        }
                                        case 2: {
                                            break Label_0029;
                                        }
                                        case 3: {
                                            break Label_0039;
                                        }
                                        case 4: {
                                            break Label_0047;
                                        }
                                        default: {
                                            continue;
                                        }
                                    }
                                }
                                c = 'z';
                                array = (array2 = charArray);
                                n3 = (n4 = n);
                                continue;
                            }
                            c = '+';
                            array = (array2 = charArray);
                            n3 = (n4 = n);
                            continue;
                        }
                        c = '\u0004';
                        array = (array2 = charArray);
                        n3 = (n4 = n);
                        continue;
                    }
                    c = '\"';
                    array = (array2 = charArray);
                    n3 = (n4 = n);
                }
            }
            break;
        }
        return new String(charArray);
    }
}
