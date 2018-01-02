import java.awt.FontMetrics;
import java.awt.Event;
import java.awt.image.ImageProducer;
import java.awt.image.ColorModel;
import java.awt.Container;
import java.awt.Label;
import java.awt.LayoutManager;
import java.io.DataInputStream;
import java.io.InputStream;
import java.awt.Component;
import java.awt.MediaTracker;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.net.MalformedURLException;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.image.MemoryImageSource;
import java.awt.Graphics;
import java.net.URL;
import java.awt.Image;
import java.awt.Font;
import java.awt.image.IndexColorModel;
import java.awt.Frame;
import java.awt.image.ImageObserver;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class galaxy extends Applet implements Runnable, ImageObserver
{
    float a;
    float b;
    private int c;
    Frame d;
    boolean e;
    final String f = "Ul+b[`<9w\u001eUr?|[u<\u001doMwy{&IckuoPr";
    int g;
    int h;
    int i;
    int j;
    byte[] k;
    int l;
    int[] m;
    byte[] n;
    IndexColorModel o;
    IndexColorModel[] p;
    long q;
    int r;
    float s;
    float t;
    float u;
    float v;
    float w;
    int x;
    float y;
    float z;
    float A;
    float B;
    float C;
    float D;
    float E;
    float F;
    float G;
    Font H;
    float[] I;
    int J;
    int K;
    int L;
    byte[] M;
    int N;
    int O;
    int P;
    float Q;
    float R;
    int S;
    private Image T;
    int U;
    boolean V;
    String[] W;
    URL X;
    int Y;
    int Z;
    static final int ba = 1024;
    static final float bb = 1024.0f;
    int bc;
    boolean bd;
    int be;
    private Graphics bf;
    private Image bg;
    boolean bh;
    private Image bi;
    int bj;
    int bk;
    int bl;
    byte bm;
    float[] bn;
    float[] bo;
    float[] bp;
    float[] bq;
    float[] br;
    float[] bs;
    float[] bt;
    float[] bu;
    float[] bv;
    float[] bw;
    static final int bx = 4800;
    static final int by = 9600;
    int bz;
    int[] bA;
    anfy bB;
    MemoryImageSource bC;
    anfy[] bD;
    int bE;
    int bF;
    int bG;
    byte[] bH;
    boolean bI;
    int bJ;
    int bK;
    int bL;
    int bM;
    byte[] bN;
    int[] bO;
    int[] bP;
    int[] bQ;
    int[] bR;
    int[] bS;
    int[] bT;
    int[] bU;
    int[] bV;
    int[] bW;
    int[] bX;
    int[] bY;
    int[] bZ;
    int[] ca;
    int[] cb;
    int cc;
    int cd;
    String ce;
    int cf;
    int cg;
    int ch;
    Color ci;
    int cj;
    int ck;
    int cl;
    int cm;
    int cn;
    int co;
    int[] cp;
    int[] cq;
    int[] cr;
    String cs;
    float ct;
    float cu;
    Color cv;
    int cw;
    int cx;
    boolean cy;
    boolean cz;
    Toolkit cA;
    Thread cB;
    float[] cC;
    int cD;
    float[] cE;
    int cF;
    int cG;
    int cH;
    int cI;
    int[] cJ;
    int cK;
    Lware cL;
    int cM;
    int cN;
    int cO;
    int cP;
    float cQ;
    int cR;
    int cS;
    int cT;
    Font[] cU;
    float cV;
    
    private final void a() {
        while (true) {
            this.showStatus(c("Ps5)J4n>cQby{yIc2:`Xmv:x_:\u007f4c\u001ewn>jW`o{bWzy{gP4T\u000fCr5"));
            try {
                Thread.sleep(500L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public synchronized boolean b() {
        this.prepareImage(this.bi, this);
        if (this.V) {
            for (int i = 0; i < 3; ++i) {
                this.notifyAll();
                Thread.yield();
                try {
                    Thread.sleep(100L);
                }
                catch (InterruptedException ex) {}
            }
            return this.bh;
        }
        return false;
    }
    
    public void destroy() {
        if (this.bi != null) {
            this.bi.flush();
        }
        this.bi = null;
        if (this.bg != null) {
            this.bg.flush();
        }
        this.bg = null;
        if (this.bf != null) {
            this.bf.dispose();
        }
        this.bf = null;
        System.gc();
    }
    
    private final void c() {
        if (this.a != this.ct + 1.0f) {
            final float a = this.a;
            this.a = a + 1.0f;
            final float n = a;
            final float ct = this.ct;
            final float[] cc = this.cC;
            final float[] i = this.I;
            final float[] bn = this.bn;
            for (int j = 0; j < 14400; ++j) {
                final float n2 = cc[j];
                final float n3 = i[j];
                bn[j] = n * (n2 - n3) / ct + n3;
            }
        }
    }
    
    private final void d() {
        if (this.b != this.cu + 1.0f) {
            final float b = this.b;
            this.b = b + 1.0f;
            this.cR = (int)(b * (this.cQ - this.cV) / this.cu + this.cV);
        }
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
                this.showStatus(c("]q:i[4") + s + c("4r4z\u001ers.`Z5"));
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
    
    public galaxy() {
        this.V = false;
        this.bh = false;
        this.e = false;
        this.bI = false;
        this.bd = false;
        this.bQ = new int[] { 4, 306, 0, 0, 3, 0, 0, -3, 5, -700, -400, 0, 50, 0, 1, 0, 0, 200, 1, 200, 0, 0, 1, 0, 200, 1, 200, 2, 13 };
        this.bR = new int[] { 4, 346, 0, 0, 3, 0, 0, -6, 5, -700, -580, 0, 30, 0, 4, 4, 0, 20, 1, 100, 0, 4, 5, 0, 40, 1, 100, 5, -580, -2000, 0, 250, 1, 250, 5, -2000, -580, 0, 50, 0, 5, 4, 0, 50, 1, 50, 2, 13 };
        this.bU = new int[] { 4, 306, 0, 0, 3, -1, 1, -3, 5, -700, -290, 0, 30, 0, 3, 2, 0, 50, 1, 200, 5, -290, -130, 0, 30, 1, 30, 5, -130, -290, 0, 30, 1, 30, 2, 18 };
        this.bV = new int[] { 4, 346, 0, 0, 3, 0, 0, -3, 5, -700, -310, 0, 30, 0, 3, 0, 0, 50, 1, 200, 5, -310, -30, 0, 200, 1, 200, 5, -30, -310, 0, 200, 1, 200, 2, 18 };
        this.bW = new int[] { 4, 0, 0, 0, 3, 0, 0, -6, 5, -700, -310, 0, 30, 0, 4, 4, 0, 50, 1, 200, 5, -310, -30, 0, 200, 1, 200, 5, -30, -310, 0, 200, 1, 200, 2, 18 };
        this.bX = new int[] { 4, 256, 0, 0, 3, 0, 0, -6, 5, -700, -280, 0, 30, 0, 4, 4, 0, 50, 1, 200, 5, -280, -30, 0, 200, 1, 200, 5, -30, -280, 0, 200, 1, 200, 2, 18 };
        this.bY = new int[] { 4, 346, 0, 0, 3, 0, 0, -3, 5, -700, -310, 0, 30, 0, 3, 1, 0, 50, 1, 200, 5, -310, -30, 0, 200, 1, 200, 5, -30, -310, 0, 200, 1, 200, 2, 18 };
        this.bZ = new int[] { 4, 346, 0, 0, 3, 2, 1, -6, 5, -700, -480, 0, 30, 0, 4, 1, 0, 100, 1, 100, 0, 1, 2, 0, 100, 1, 150, 0, 2, 4, 0, 100, 1, 150, 2, 13 };
        this.ca = new int[] { 4, 346, 0, 0, 3, 1, 3, -4, 5, -700, -580, 0, 30, 0, 8, 8, 0, 20, 1, 100, 0, 8, 7, 0, 40, 1, 100, 5, -580, -700, 0, 250, 1, 250, 5, -700, -580, 0, 50, 0, 7, 8, 0, 50, 1, 50, 2, 13 };
        this.cb = new int[] { 4, 306, 0, 0, 3, 3, 4, 6, 5, -700, -400, 0, 50, 0, 6, 8, 0, 100, 1, 300, 0, 8, 6, 0, 100, 1, 300, 2, 13 };
        this.bS = new int[] { 4, 306, 0, 0, 3, 0, 0, -3, 5, -700, -400, 0, 50, 0, 1, 0, 0, 200, 1, 200, 0, 0, 1, 0, 200, 1, 200, 0, 1, 4, 0, 20, 5, -400, -200, 0, 20, 1, 20, 4, 346, 0, 0, 3, 0, 0, -6, 0, 4, 4, 0, 20, 5, -200, -700, 0, 20, 1, 20, 5, -700, -580, 0, 30, 0, 4, 4, 0, 20, 1, 100, 0, 4, 5, 0, 40, 1, 100, 5, -580, -2000, 0, 250, 1, 250, 5, -2000, -580, 0, 50, 0, 5, 4, 0, 50, 1, 50, 0, 4, 4, 0, 20, 5, -580, -200, 0, 20, 1, 20, 4, 306, 0, 0, 3, -1, 1, -3, 0, 4, 3, 0, 20, 5, -200, -700, 0, 20, 1, 20, 5, -700, -290, 0, 30, 0, 3, 2, 0, 50, 1, 200, 5, -290, -130, 0, 30, 1, 30, 5, -130, -290, 0, 30, 1, 30, 0, 2, 4, 0, 20, 5, -290, -200, 0, 20, 1, 20, 4, 346, 0, 0, 3, 0, 0, -3, 0, 4, 3, 0, 20, 5, -200, -700, 0, 20, 1, 20, 5, -700, -310, 0, 30, 0, 3, 0, 0, 50, 1, 200, 5, -310, -30, 0, 200, 1, 200, 5, -30, -310, 0, 200, 1, 200, 0, 0, 4, 0, 20, 5, -310, -200, 0, 20, 1, 20, 4, 0, 0, 0, 3, 0, 0, -6, 0, 4, 4, 0, 20, 5, -200, -700, 0, 20, 1, 20, 5, -700, -310, 0, 30, 0, 4, 4, 0, 50, 1, 200, 5, -310, -30, 0, 200, 1, 200, 5, -30, -310, 0, 200, 1, 200, 0, 4, 4, 0, 20, 5, -310, -200, 0, 20, 1, 20, 4, 256, 0, 0, 3, 0, 0, -6, 0, 4, 4, 0, 20, 5, -200, -700, 0, 20, 1, 20, 5, -700, -280, 0, 30, 0, 4, 4, 0, 50, 1, 200, 5, -280, -30, 0, 200, 1, 200, 5, -30, -280, 0, 200, 1, 200, 0, 4, 4, 0, 20, 5, -280, -200, 0, 20, 1, 20, 4, 346, 0, 0, 3, 0, 0, -3, 0, 4, 3, 0, 20, 5, -200, -700, 0, 20, 1, 20, 5, -700, -310, 0, 30, 0, 3, 1, 0, 50, 1, 200, 5, -310, -30, 0, 200, 1, 200, 5, -30, -310, 0, 200, 1, 200, 0, 1, 4, 0, 20, 5, -310, -200, 0, 20, 1, 20, 4, 346, 0, 0, 3, 2, 1, -6, 0, 4, 4, 0, 20, 5, -200, -700, 0, 20, 1, 20, 5, -700, -480, 0, 30, 0, 4, 1, 0, 100, 1, 100, 0, 1, 2, 0, 100, 1, 150, 0, 2, 4, 0, 100, 1, 150, 0, 4, 4, 0, 20, 5, -480, -200, 0, 20, 1, 20, 4, 346, 0, 0, 3, 1, 3, -4, 0, 4, 8, 0, 20, 5, -200, -700, 0, 20, 1, 20, 5, -700, -580, 0, 30, 0, 8, 8, 0, 20, 1, 100, 0, 8, 7, 0, 40, 1, 100, 5, -580, -2000, 0, 250, 1, 250, 5, -2000, -580, 0, 50, 0, 7, 8, 0, 50, 1, 50, 0, 8, 4, 0, 20, 5, -580, -200, 0, 20, 1, 20, 4, 306, 0, 0, 3, 3, 4, 6, 0, 4, 6, 0, 20, 5, -200, -700, 0, 20, 1, 20, 5, -700, -400, 0, 50, 0, 6, 8, 0, 100, 1, 300, 0, 8, 6, 0, 100, 1, 300, 0, 6, 4, 0, 20, 5, -400, -200, 0, 20, 1, 20, 4, 306, 0, 0, 3, 0, 0, -3, 0, 4, 1, 0, 20, 5, -200, -700, 0, 20, 1, 20, 2, 8 };
        this.bT = new int[] { 4, 306, 0, 0, 3, 0, 0, -3, 6, 1, 5, -700, -400, 0, 50, 0, 1, 0, 0, 200, 1, 200, 0, 0, 1, 0, 200, 1, 200, 0, 1, 4, 0, 20, 5, -400, -200, 0, 20, 1, 20, 4, 346, 0, 0, 6, 0, 3, 0, 0, -6, 0, 4, 4, 0, 20, 5, -200, -700, 0, 20, 1, 20, 5, -700, -580, 0, 30, 0, 4, 4, 0, 20, 1, 100, 0, 4, 5, 0, 40, 1, 100, 5, -580, -2000, 0, 250, 1, 250, 5, -2000, -580, 0, 50, 0, 5, 4, 0, 50, 1, 50, 0, 4, 4, 0, 20, 5, -580, -200, 0, 20, 1, 20, 4, 306, 0, 0, 6, 2, 3, -1, 1, -3, 0, 4, 3, 0, 20, 5, -200, -700, 0, 20, 1, 20, 5, -700, -290, 0, 30, 0, 3, 2, 0, 50, 1, 200, 5, -290, -130, 0, 30, 1, 30, 5, -130, -290, 0, 30, 1, 30, 0, 2, 4, 0, 20, 5, -290, -200, 0, 20, 1, 20, 4, 346, 0, 0, 6, 3, 3, 0, 0, -3, 0, 4, 3, 0, 20, 5, -200, -700, 0, 20, 1, 20, 5, -700, -310, 0, 30, 0, 3, 0, 0, 50, 1, 200, 5, -310, -30, 0, 200, 1, 200, 5, -30, -310, 0, 200, 1, 200, 0, 0, 4, 0, 20, 5, -310, -200, 0, 20, 1, 20, 4, 0, 0, 0, 6, 4, 3, 0, 0, -6, 0, 4, 4, 0, 20, 5, -200, -700, 0, 20, 1, 20, 5, -700, -310, 0, 30, 0, 4, 4, 0, 50, 1, 200, 5, -310, -30, 0, 200, 1, 200, 5, -30, -310, 0, 200, 1, 200, 0, 4, 4, 0, 20, 5, -310, -200, 0, 20, 1, 20, 4, 256, 0, 0, 6, 0, 3, 0, 0, -6, 0, 4, 4, 0, 20, 5, -200, -700, 0, 20, 1, 20, 5, -700, -280, 0, 30, 0, 4, 4, 0, 50, 1, 200, 5, -280, -30, 0, 200, 1, 200, 5, -30, -280, 0, 200, 1, 200, 0, 4, 4, 0, 20, 5, -280, -200, 0, 20, 1, 20, 4, 346, 0, 0, 6, 0, 3, 0, 0, -3, 0, 4, 3, 0, 20, 5, -200, -700, 0, 20, 1, 20, 5, -700, -310, 0, 30, 0, 3, 1, 0, 50, 1, 200, 5, -310, -30, 0, 200, 1, 200, 5, -30, -310, 0, 200, 1, 200, 0, 1, 4, 0, 20, 5, -310, -200, 0, 20, 1, 20, 4, 346, 0, 0, 6, 1, 3, 2, 1, -6, 0, 4, 4, 0, 20, 5, -200, -700, 0, 20, 1, 20, 5, -700, -480, 0, 30, 0, 4, 1, 0, 100, 1, 100, 0, 1, 2, 0, 100, 1, 150, 0, 2, 4, 0, 100, 1, 150, 0, 4, 4, 0, 20, 5, -480, -200, 0, 20, 1, 20, 4, 346, 0, 0, 6, 2, 3, 1, 3, -4, 0, 4, 8, 0, 20, 5, -200, -700, 0, 20, 1, 20, 5, -700, -580, 0, 30, 0, 8, 8, 0, 20, 1, 100, 0, 8, 7, 0, 40, 1, 100, 5, -580, -2000, 0, 250, 1, 250, 5, -2000, -580, 0, 50, 0, 7, 8, 0, 50, 1, 50, 0, 8, 4, 0, 20, 5, -580, -200, 0, 20, 1, 20, 4, 306, 0, 0, 6, 3, 3, 3, 4, 6, 0, 4, 6, 0, 20, 5, -200, -700, 0, 20, 1, 20, 5, -700, -400, 0, 50, 0, 6, 8, 0, 100, 1, 300, 0, 8, 6, 0, 100, 1, 300, 0, 6, 4, 0, 20, 5, -400, -200, 0, 20, 1, 20, 4, 306, 0, 0, 6, 1, 3, 0, 0, -3, 0, 4, 1, 0, 20, 5, -200, -700, 0, 20, 1, 20, 2, 8 };
        this.bl = 5;
    }
    
    public void e() {
        this.p = new IndexColorModel[6];
        this.O = this.N / 2;
        this.P = this.cK / 2;
        this.cE = new float[1280];
        this.bA = new int[9600];
        this.S = 0;
        while (this.S < 1024) {
            this.cE[this.S] = (float)Math.sin(this.S * 2.0 * 3.141592653589793 / 1024.0);
            ++this.S;
        }
        this.S = 0;
        while (this.S < 256) {
            this.cE[this.S + 1024] = this.cE[this.S];
            ++this.S;
        }
        this.n = new byte[this.cD];
        this.m = new int[this.cD];
        this.t = 0.0f;
        this.u = 1.0f;
        if (this.bl < 3) {
            this.bH = new byte[256];
            this.M = new byte[256];
            this.k = new byte[256];
            int n = 0;
            this.S = 0;
            while (this.S < 256) {
                this.bH[this.S] = (byte)n;
                this.M[this.S] = (byte)n;
                this.k[this.S] = (byte)n;
                ++n;
                ++this.S;
            }
            this.p[0] = new IndexColorModel(8, 256, this.bH, this.M, this.k);
            this.bE = 255;
            this.K = 255;
            this.j = 255;
            this.S = 255;
            while (this.S >= 0) {
                this.bH[this.S] = (byte)this.bE;
                this.M[this.S] = (byte)this.K;
                this.k[this.S] = (byte)this.j;
                this.bE -= 4;
                this.K -= 2;
                --this.j;
                if (this.bE < 0) {
                    this.bE = 0;
                }
                if (this.K < 0) {
                    this.K = 0;
                }
                if (this.j < 0) {
                    this.j = 0;
                }
                --this.S;
            }
            this.p[1] = new IndexColorModel(8, 256, this.bH, this.M, this.k);
            this.p[2] = new IndexColorModel(8, 256, this.bH, this.M, this.k);
            this.p[3] = new IndexColorModel(8, 256, this.bH, this.M, this.k);
            this.p[4] = new IndexColorModel(8, 256, this.bH, this.M, this.k);
            this.p[5] = new IndexColorModel(8, 256, this.bH, this.M, this.k);
        }
        else if (this.bl >= 3) {
            this.bH = new byte[128];
            this.M = new byte[128];
            this.k = new byte[128];
            int n2 = 0;
            this.S = 0;
            while (this.S < 128) {
                this.bH[this.S] = (byte)n2;
                this.M[this.S] = (byte)n2;
                this.k[this.S] = (byte)n2;
                n2 += 2;
                ++this.S;
            }
            this.p[0] = new IndexColorModel(7, 128, this.bH, this.M, this.k);
            this.bE = 255;
            this.K = 255;
            this.j = 255;
            this.S = 127;
            while (this.S >= 0) {
                this.bH[this.S] = (byte)(this.bE & 0xFF);
                this.M[this.S] = (byte)(this.K & 0xFF);
                this.k[this.S] = (byte)(this.j & 0xFF);
                this.bE -= 8;
                this.K -= 4;
                this.j -= 2;
                if (this.bE < 0) {
                    this.bE = 0;
                }
                if (this.K < 0) {
                    this.K = 0;
                }
                if (this.j < 0) {
                    this.j = 0;
                }
                --this.S;
            }
            this.p[1] = new IndexColorModel(7, 128, this.bH, this.M, this.k);
            this.bE = 255;
            this.S = 0;
            while (this.S < 128) {
                this.bH[this.S] = (byte)(255.0 * Math.sin(this.S * 3.141592653589793 / 128.0));
                this.M[this.S] = 0;
                this.k[this.S] = 0;
                ++this.S;
            }
            this.p[2] = new IndexColorModel(7, 128, this.bH, this.M, this.k);
            final boolean be = false;
            this.j = (be ? 1 : 0);
            this.K = (be ? 1 : 0);
            this.bE = (be ? 1 : 0);
            this.S = 0;
            while (this.S < 128) {
                this.bH[this.S] = (byte)this.bE;
                this.M[this.S] = (byte)this.K;
                this.k[this.S] = (byte)this.j;
                this.bE += 4;
                this.j += 2;
                if (this.bE > 255) {
                    this.bE = 255;
                }
                if (this.K > 255) {
                    this.K = 255;
                }
                if (this.j > 255) {
                    this.j = 255;
                }
                ++this.S;
            }
            this.p[3] = new IndexColorModel(7, 128, this.bH, this.M, this.k);
            final boolean b = false;
            this.j = (b ? 1 : 0);
            this.bE = (b ? 1 : 0);
            this.K = 255;
            this.S = 127;
            while (this.S >= 0) {
                this.bH[this.S] = 0;
                this.M[this.S] = (byte)this.K;
                this.k[this.S] = 0;
                this.K -= 2;
                --this.S;
            }
            this.p[4] = new IndexColorModel(7, 128, this.bH, this.M, this.k);
            final int be2 = 255;
            this.j = be2;
            this.K = be2;
            this.bE = be2;
            this.S = 127;
            while (this.S >= 0) {
                this.bH[this.S] = (byte)this.bE;
                this.M[this.S] = (byte)this.K;
                this.k[this.S] = (byte)this.j;
                this.bE -= 2;
                this.K -= 8;
                this.j -= 4;
                if (this.bE < 0) {
                    this.bE = 0;
                }
                if (this.K < 0) {
                    this.K = 0;
                }
                if (this.j < 0) {
                    this.j = 0;
                }
                --this.S;
            }
            this.p[5] = new IndexColorModel(7, 128, this.bH, this.M, this.k);
        }
        this.o = null;
        this.o = this.p[this.L];
        this.bn = new float[14400];
        this.bo = new float[14400];
        this.bp = new float[14400];
        this.bq = new float[14400];
        this.br = new float[14400];
        this.bs = new float[14400];
        this.bt = new float[14400];
        this.bu = new float[14400];
        this.bv = new float[14400];
        this.bw = new float[14400];
        int n3 = 0;
        int n4 = this.N;
        this.S = 0;
        while (this.S < 10) {
            int n5 = n4;
            n4 += 7;
            for (int i = 1; i < 50; ++i) {
                this.bo[n3++] = 5 * i * (float)Math.cos(n5 * 2.0 * 3.141592653589793 / 73.0);
                this.bo[n3++] = 5 * i * (float)Math.sin(n5++ * 2.0 * 3.141592653589793 / 73.0);
                this.bo[n3++] = 20.0f;
            }
            ++this.S;
        }
        int n6 = 13;
        this.S = 0;
        while (this.S < 10) {
            int n7 = n6;
            n6 += 7;
            for (int j = 1; j < 50; ++j) {
                this.bo[n3++] = 5 * j * (float)Math.cos(n7 * 2.1 * 3.141592653589793 / 73.0);
                this.bo[n3++] = 5 * j * (float)Math.sin(n7++ * 2.0 * 3.141592653589793 / 73.0);
                this.bo[n3++] = 15.0f;
            }
            ++this.S;
        }
        int n8 = 22;
        this.S = 0;
        while (this.S < 10) {
            int n9 = n8;
            n8 += 7;
            for (int k = 1; k < 50; ++k) {
                this.bo[n3++] = 5 * k * (float)Math.cos(n9 * 2.0 * 3.141592653589793 / 73.0);
                this.bo[n3++] = 5 * k * (float)Math.sin(n9++ * 2.1 * 3.141592653589793 / 73.0);
                this.bo[n3++] = 10.0f;
            }
            ++this.S;
        }
        int n10 = 39;
        this.S = 0;
        while (this.S < 10) {
            int n11 = n10;
            n10 += 7;
            for (int l = 1; l < 50; ++l) {
                this.bo[n3++] = 5 * l * (float)Math.cos(n11 * 2.0 * 3.141592653589793 / 73.0);
                this.bo[n3++] = 5.1f * l * (float)Math.sin(n11++ * 2.0 * 3.141592653589793 / 73.0);
                this.bo[n3++] = 5.0f;
            }
            ++this.S;
        }
        int n12 = 57;
        this.S = 0;
        while (this.S < 10) {
            int n13 = n12;
            n12 += 7;
            for (int n14 = 1; n14 < 50; ++n14) {
                this.bo[n3++] = 5.1f * n14 * (float)Math.cos(n13 * 2.0 * 3.141592653589793 / 73.0);
                this.bo[n3++] = 5 * n14 * (float)Math.sin(n13++ * 2.0 * 3.141592653589793 / 73.0);
                this.bo[n3++] = 0.0f;
            }
            ++this.S;
        }
        int n15 = 64;
        this.S = 0;
        while (this.S < 10) {
            int n16 = n15;
            n15 += 7;
            for (int n17 = 1; n17 < 50; ++n17) {
                this.bo[n3++] = 5 * n17 * (float)Math.cos(n16 * 2.0 * 3.141592653589793 / 73.0);
                this.bo[n3++] = 5 * n17 * (float)Math.sin(n16++ * 2.0 * 3.141592653589793 / 73.0);
                this.bo[n3++] = -5.0f;
            }
            ++this.S;
        }
        int n18 = 77;
        this.S = 0;
        while (this.S < 10) {
            int n19 = n18;
            n18 += 7;
            for (int n20 = 1; n20 < 50; ++n20) {
                this.bo[n3++] = 5 * n20 * (float)Math.cos(n19 * 2.0 * 3.141592653589793 / 73.0);
                this.bo[n3++] = 5 * n20 * (float)Math.sin(n19++ * 2.0 * 3.141592653589793 / 73.0);
                this.bo[n3++] = -10.0f;
            }
            ++this.S;
        }
        int n21 = 86;
        this.S = 0;
        while (this.S < 10) {
            int n22 = n21;
            n21 += 7;
            for (int n23 = 1; n23 < 50; ++n23) {
                this.bo[n3++] = 5 * n23 * (float)Math.cos(n22 * 2.0 * 3.141592653589793 / 73.0);
                this.bo[n3++] = 5 * n23 * (float)Math.sin(n22++ * 2.0 * 3.141592653589793 / 73.0);
                this.bo[n3++] = -15.0f;
            }
            ++this.S;
        }
        this.S = 0;
        while (this.S < 880) {
            final double n24 = 800.0 + 1200.0 * Math.random();
            final double n25 = 6.283185307179586 * Math.random();
            final double n26 = 3.141592653589793 * Math.random() - 1.5707963267948966;
            this.bo[n3++] = (float)(n24 * Math.cos(n26) * Math.cos(n25));
            this.bo[n3++] = (float)(n24 * Math.cos(n26) * Math.sin(n25));
            this.bo[n3++] = (float)(n24 * Math.sin(n26));
            ++this.S;
        }
        int n27 = 0;
        this.S = 0;
        while (this.S < 2000) {
            final double n28 = 70.0 * Math.random();
            final double n29 = 6.283185307179586 * Math.random();
            final double n30 = 3.141592653589793 * Math.random() - 1.5707963267948966;
            this.bp[n27++] = (float)(n28 * Math.cos(n30) * Math.cos(n29));
            this.bp[n27++] = (float)(n28 * Math.cos(n30) * Math.sin(n29));
            this.bp[n27++] = (float)(n28 * Math.sin(n30));
            ++this.S;
        }
        this.S = 0;
        while (this.S < 2800) {
            final double n31 = 250.0 * Math.random() + 50.0;
            final double n32 = 6.283185307179586 * Math.random();
            final double n33 = 0.04 * (3.141592653589793 * Math.random() - 1.5707963267948966);
            this.bp[n27++] = (float)(n31 * Math.cos(n33) * Math.cos(n32));
            this.bp[n27++] = (float)(n31 * Math.cos(n33) * Math.sin(n32));
            this.bp[n27++] = (float)(n31 * Math.sin(n33));
            ++this.S;
        }
        int n34 = 0;
        this.S = 0;
        while (this.S < 4800) {
            final double n35 = 200.0 * Math.random();
            final double n36 = 6.283185307179586 * Math.random();
            final double n37 = 3.141592653589793 * Math.random() - 1.5707963267948966;
            this.bq[n34++] = (float)(n35 * Math.cos(n37) * Math.cos(n36));
            this.bq[n34++] = (float)(n35 * Math.cos(n37) * Math.sin(n36));
            this.bq[n34++] = (float)(n35 * Math.sin(n37));
            ++this.S;
        }
        int n38 = 0;
        this.S = 0;
        while (this.S < 4800) {
            final double n39 = 200.0;
            final double n40 = 400.0 + 100.0 * Math.random();
            final double n41 = 6.283185307179586 * Math.random();
            final double n42 = 3.141592653589793 * Math.random() - 1.5707963267948966;
            this.bs[n38] = (float)(n39 * Math.cos(n42) * Math.cos(n41));
            this.bt[n38++] = (float)(n40 * Math.cos(n41));
            this.bs[n38] = (float)(n39 * Math.cos(n42) * Math.sin(n41));
            this.bt[n38++] = (float)(n40 * Math.sin(n41));
            this.bs[n38] = (float)(n39 * Math.sin(n42));
            this.bt[n38++] = 0.0f;
            ++this.S;
        }
        int n43 = 0;
        this.S = 0;
        while (this.S < 4800) {
            final double n44 = 300.0 * Math.random();
            final double n45 = 6.283185307179586 * Math.random();
            this.bu[n43++] = (float)(n44 * Math.cos(n45));
            this.bu[n43++] = (float)(n44 * Math.sin(n45));
            this.bu[n43++] = (float)n44 - 150.0f;
            ++this.S;
        }
        int n46 = 0;
        this.S = 0;
        while (this.S < 1500) {
            double n47;
            if (Math.random() > 0.5) {
                n47 = 100.0 + 10.0 * Math.random();
            }
            else {
                n47 = -(220.0 + 10.0 * Math.random());
            }
            final double n48 = 170.0 + 10.0 * Math.random();
            final double n49 = 200.0 + 10.0 * Math.random();
            final double n50 = 6.283185307179586 * Math.random();
            this.bv[n46] = (float)(n47 * Math.cos(n50));
            this.bv[1500 + n46] = (float)(n48 * Math.cos(n50));
            this.bv[3000 + n46++] = (float)(n49 * Math.cos(n50));
            this.bv[n46] = (float)(n47 * Math.sin(n50));
            this.bv[1500 + n46] = (float)(n48 * Math.sin(n50));
            this.bv[3000 + n46++] = (float)(n49 * Math.sin(n50));
            this.bv[n46] = (float)(n47 * Math.sin(n50));
            this.bv[1500 + n46] = 0.0f;
            this.bv[3000 + n46++] = (float)(n49 * Math.cos(n50));
            ++this.S;
        }
        int n51 = 13500;
        this.S = 0;
        while (this.S < 150) {
            final double n52 = 50.0 + 10.0 * Math.random();
            final double n53 = 6.283185307179586 * Math.random();
            this.bv[n51++] = (float)(n52 * Math.cos(n53));
            this.bv[n51++] = (float)(n52 * Math.sin(n53));
            this.bv[n51++] = (float)(n52 * -Math.sin(n53));
            ++this.S;
        }
        this.S = 0;
        while (this.S < 150) {
            final double n54 = 70.0 + 10.0 * Math.random();
            final double n55 = 6.283185307179586 * Math.random();
            this.bv[n51++] = (float)(n54 * Math.cos(n55));
            this.bv[n51++] = (float)(n54 * Math.sin(n55));
            this.bv[n51++] = (float)(n54 * -Math.cos(n55));
            ++this.S;
        }
        int n56 = 0;
        this.S = 0;
        while (this.S < 4800) {
            final double random = Math.random();
            final double random2 = Math.random();
            this.bw[n56++] = (float)(400.0 * random - 200.0);
            this.bw[n56++] = (float)(400.0 * random2 - 200.0);
            this.bw[n56++] = -50.0f + 100.0f * (float)(Math.cos(6.283185307179586 * random) * Math.sin(6.283185307179586 * random2));
            ++this.S;
        }
        System.gc();
    }
    
    public void f() {
        if (this.bl != 2) {
            if (this.bm == 0) {
                try {
                    System.arraycopy(this.n, 0, this.bN, 0, this.cD);
                }
                catch (ArrayIndexOutOfBoundsException ex) {}
                catch (ArrayStoreException ex2) {}
            }
            else {
                final int cd = this.cD;
                final byte[] bn = this.bN;
                final byte bm = this.bm;
                for (int i = 0; i < cd; ++i) {
                    byte b = (byte)(bn[i] - bm);
                    if (b < 0) {
                        b = 0;
                    }
                    bn[i] = b;
                }
            }
        }
        if (this.bl == 2) {
            try {
                System.arraycopy(this.m, 0, this.bO, 0, this.cD);
            }
            catch (ArrayIndexOutOfBoundsException ex3) {
                this.stop();
            }
            catch (ArrayStoreException ex4) {
                this.stop();
            }
        }
        if (this.s != 0.0f) {
            this.t += this.s * this.u;
            if (this.t > this.v) {
                this.t = this.v;
                this.u = -1.0f;
            }
            if (this.t < this.w) {
                this.t = this.w;
                this.u = 1.0f;
            }
            this.bm = (byte)this.t;
        }
        this.l();
        this.c();
        this.d();
        this.k();
        this.j();
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
                            this.ce = new String(byteArray);
                            return;
                        }
                        catch (NoSuchMethodError noSuchMethodError) {
                            this.ce = new String(byteArray, 0);
                            return;
                        }
                    }
                    int n3 = 1;
                    for (int j = 0; j < n2; ++j) {
                        if (byteArray[j] == 10) {
                            ++n3;
                        }
                    }
                    this.W = new String[n3 - 1];
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
                                this.W[l] = new String(byteArray, array2[l], array3[l]);
                            }
                            catch (NoSuchMethodError noSuchMethodError2) {
                                this.W[l] = new String(byteArray, 0, array2[l], array3[l]);
                            }
                        }
                    }
                    catch (StringIndexOutOfBoundsException ex) {
                        this.W = null;
                    }
                }
                catch (IOException ex2) {}
            }
            catch (IOException ex3) {}
        }
        catch (MalformedURLException ex4) {}
    }
    
    public void a(final Graphics graphics) {
        graphics.setFont(this.H);
        if (this.c == 0) {
            this.cO = this.cP;
        }
        else {
            this.U += this.cF;
            this.cO = this.cP - (int)Math.abs(this.c * Math.sin(this.U / 90.0 * 3.141592653589793));
        }
        if (this.ck != 0) {
            for (int i = 0; i < this.cH; ++i) {
                final int n = this.cp[this.cj + i];
                graphics.copyArea(i, n, 1, this.cm, 0, this.bF - n);
            }
            if (this.cz) {
                graphics.setColor(this.ci);
                graphics.drawString(this.ce, this.cN + 1, this.bF + this.cf + 1);
            }
            graphics.setColor(this.cv);
            graphics.drawString(this.ce, this.cN, this.bF + this.cf);
            for (int j = 0; j < this.cH; ++j) {
                graphics.copyArea(j, this.bF, 1, this.cn, 0, this.cq[this.cj + j]);
            }
            this.cj -= this.co;
            if (this.cj < 0) {
                this.cj += 360;
            }
        }
        else {
            if (this.cz) {
                graphics.setColor(this.ci);
                graphics.drawString(this.ce, this.cN + 1, this.cO + 1);
            }
            graphics.setColor(this.cv);
            graphics.drawString(this.ce, this.cN, this.cO);
        }
        this.cN -= this.cG;
        if (this.cN < -this.cg) {
            this.cN = this.cH;
        }
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if (image == this.bi) {
            if (n == 16) {
                this.bh = true;
            }
            return true;
        }
        return true;
    }
    
    public void init() {
        this.setLayout(null);
        this.addNotify();
        this.cA = this.getToolkit();
        final String parameter = this.getParameter(c("wn>jW`o"));
        if (parameter != null) {
            if (!parameter.startsWith(c("Ul+b[`<9w\u001eUr?|[u<\u001doMwy{&IckuoPr"))) {
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
            s = c("ru7k");
        }
        String s2;
        try {
            s2 = this.getDocumentBase().getHost();
        }
        catch (SecurityException ex2) {
            s2 = "";
        }
        if (s.equalsIgnoreCase(c("ru7k")) || s2.length() == 0 || s2.equalsIgnoreCase(c("xs8oR|s(z")) || s2.equals(c("%.l \u000e:,u?"))) {
            this.bI = true;
        }
        else {
            if (s2.startsWith(c("ck, "))) {
                s2 = s2.substring(4);
            }
            final String parameter2 = this.getParameter(c("fy<mQpy"));
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
                        if (s5.startsWith(c("ck, "))) {
                            substring = s5.substring(4);
                        }
                        else {
                            substring = s5;
                        }
                        if (s2.equalsIgnoreCase(substring)) {
                            this.bI = true;
                        }
                    }
                }
            }
        }
        final int width = this.size().width;
        this.bG = width;
        this.cK = width;
        final int height = this.size().height;
        this.bF = height;
        this.N = height;
        this.cD = this.cK * this.N;
        final String parameter3 = this.getParameter(c("fy<bWzw"));
        if (parameter3 != null && !parameter3.equalsIgnoreCase("NO")) {
            try {
                this.X = new URL(this.getDocumentBase(), parameter3);
            }
            catch (MalformedURLException ex6) {
                this.X = null;
            }
        }
        if (this.getParameter(c("fy<`[cz)oSq")).equalsIgnoreCase(c("MY\b"))) {
            this.bd = true;
        }
        Container container;
        for (container = this.getParent(); !(container instanceof Frame); container = ((Frame)container).getParent()) {}
        (this.d = (Frame)container).setCursor(3);
        final String parameter4 = this.getParameter(c("{j>|Wy{"));
        if (parameter4 != null && !parameter4.equalsIgnoreCase("NO")) {
            this.bi = this.a(parameter4);
            if (this.bi != null) {
                String parameter5 = this.getParameter(c("{j>|Wy{\u0003"));
                if (parameter5 == null) {
                    parameter5 = "0";
                }
                this.bj = Integer.valueOf(parameter5);
                String parameter6 = this.getParameter(c("{j>|Wy{\u0002"));
                if (parameter6 == null) {
                    parameter6 = "0";
                }
                this.bk = Integer.valueOf(parameter6);
            }
        }
        final String parameter7 = this.getParameter(c("yy6j[x}\""));
        this.Y = ((parameter7 != null) ? Integer.valueOf(parameter7) : 10000);
        final String parameter8 = this.getParameter(c("dn2aL}h\""));
        this.bz = ((parameter8 != null) ? Integer.valueOf(parameter8) : 10);
        final String parameter9 = this.getParameter(c("sl:b[`h>"));
        this.L = ((parameter9 != null) ? Integer.valueOf(parameter9) : 0);
        final String parameter10 = this.getParameter(c("d})zWwp>}"));
        this.bl = ((parameter10 != null) ? Integer.valueOf(parameter10) : 6);
        final String parameter11 = this.getParameter(c("f}5jQy"));
        final int n14 = (parameter11 != null) ? Integer.valueOf(parameter11) : 0;
        final String parameter12 = this.getParameter(c("dy)}Wgh"));
        this.bm = (byte)((parameter12 != null) ? ((byte)(int)Integer.valueOf(parameter12)) : 0);
        final String parameter13 = this.getParameter(c("pu5oS}\u007f\u0004~[fo2}J"));
        this.s = ((parameter13 != null) ? Float.valueOf(parameter13) : 0.0f);
        final String parameter14 = this.getParameter(c("pu5oS}\u007f\u0004c_l"));
        this.v = ((parameter14 != null) ? Float.valueOf(parameter14) : 0.0f);
        final String parameter15 = this.getParameter(c("pu5oS}\u007f\u0004cWz"));
        this.w = ((parameter15 != null) ? Float.valueOf(parameter15) : 0.0f);
        final String parameter16 = this.getParameter(c("g\u007f)gN`"));
        int n15 = (parameter16 != null) ? Integer.valueOf(parameter16) : 10;
        final String parameter17 = this.getParameter(c("Yu5]gZ_"));
        this.Z = ((parameter17 != null) ? Integer.valueOf(parameter17) : 10);
        if (n14 == 1) {
            n15 = (int)(10.0 * Math.random());
        }
        switch (n15) {
            case 0: {
                this.bP = this.bQ;
                break;
            }
            case 1: {
                this.bP = this.bR;
                break;
            }
            case 2: {
                this.bP = this.bU;
                break;
            }
            case 3: {
                this.bP = this.bV;
                break;
            }
            case 4: {
                this.bP = this.bW;
                break;
            }
            case 5: {
                this.bP = this.bX;
                break;
            }
            case 6: {
                this.bP = this.bY;
                break;
            }
            case 7: {
                this.bP = this.bZ;
                break;
            }
            case 8: {
                this.bP = this.ca;
                break;
            }
            case 9: {
                this.bP = this.cb;
                break;
            }
            case 10: {
                this.bP = this.bS;
                break;
            }
            case 11: {
                this.bP = this.bT;
                break;
            }
            default: {
                this.bP = this.bQ;
                break;
            }
        }
        if (this.Y < 0) {
            this.Y = 0;
        }
        if (this.bz > 10) {
            this.bz = 10;
        }
        else if (this.bz < 1) {
            this.bz = 1;
        }
        this.bN = new byte[this.cD];
        if (this.bl == 2) {
            this.bO = new int[this.cD];
        }
        this.e();
        try {
            this.g();
        }
        catch (NoSuchMethodError noSuchMethodError) {
            this.g();
        }
        this.m();
        this.bg = this.createImage(this.bG, this.bF + this.cm);
        this.bf = this.bg.getGraphics();
        if (!this.bI) {
            (this.cL = new Lware(this.getAppletContext(), new Label(c("S}7oFm<:~Nxy/.\\m<\u001a`Zfy:.xuo8k\u001e%%b6\u0010")))).setTitle(c("S}7oFm<\u001a~Nxy/.\\m<\u001a`Zfy:.xuo8k"));
            this.cL.hide();
        }
    }
    
    void g() {
        this.bC = new MemoryImageSource(this.cK, this.N, this.o, this.bN, 0, this.cK);
        String s;
        try {
            s = System.getProperty(c("~}-o\u0010by)}W{r"));
        }
        catch (SecurityException ex) {
            s = c("ar0");
        }
        if (!s.startsWith(c("%2k"))) {
            try {
                this.bC.setAnimated(true);
                this.bC.setFullBufferUpdates(true);
                this.T = this.createImage(this.bC);
                this.bC.newPixels();
                this.V = true;
            }
            catch (NoSuchMethodError noSuchMethodError) {
                this.V = false;
            }
        }
        if (!this.V) {
            this.bC = null;
            this.bB = new anfy(this.cK, this.N, this.o, this.bN, 0, this.cK);
            this.T = this.createImage(this.bB);
            this.bD = new anfy[6];
            for (int i = 0; i < 6; ++i) {
                this.bD[i] = new anfy(this.cK, this.N, this.p[i], this.bN, 0, this.cK);
            }
        }
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        if (!this.bI) {
            this.cL.show();
            this.cL.toFront();
            this.cL.requestFocus();
        }
        else if (this.X != null) {
            if (this.bd) {
                this.getAppletContext().showDocument(this.X, this.getParameter(c("fy<hLuq>`_yy")));
            }
            else {
                this.getAppletContext().showDocument(this.X);
            }
        }
        return true;
    }
    
    public final boolean mouseEnter(final Event event, final int n, final int n2) {
        this.showStatus(this.cs);
        return true;
    }
    
    public final boolean mouseExit(final Event event, final int n, final int n2) {
        this.showStatus("");
        return true;
    }
    
    void a(final int n) {
        this.bC.newPixels(this.bN, this.p[n], 0, this.cK);
    }
    
    public final void paint(final Graphics graphics) {
        if (this.T != null) {
            this.bf.drawImage(this.T, 0, 0, this);
            if (this.bi != null) {
                this.h();
            }
            if (this.cy) {
                this.b(this.bf);
            }
            graphics.drawImage(this.bg, 0, 0, this);
        }
    }
    
    public synchronized void h() {
        if (this.e) {
            this.notifyAll();
            while (!this.bh) {
                Thread.yield();
                try {
                    Thread.sleep(8L);
                }
                catch (InterruptedException ex) {}
            }
            this.bh = false;
        }
        this.bf.drawImage(this.bi, this.bj, this.bk, this);
    }
    
    public final void i() {
        try {
            if (this.V) {
                this.bC.newPixels();
                return;
            }
            this.bB.startProduction(this.bB.getConsumer());
        }
        catch (NoSuchMethodError noSuchMethodError) {}
    }
    
    private final void j() {
        final int n = this.cK - 2;
        final int n2 = this.N - 2;
        final int n3 = this.cK * 2;
        final int ck = this.cK;
        final int n4 = this.cK + 1;
        final int n5 = this.cK - 1;
        final int cd = this.cD;
        final byte[] bn = this.bN;
        final int[] bo = this.bO;
        if (this.bl == 0) {
            int i = 0;
            while (i < 9600) {
                final int n6 = this.bA[i++];
                final int n7 = this.bA[i++];
                final int n8 = n6 + n7 * ck;
                if (n6 >= 2 && n6 < n && n7 >= 2 && n7 < n2 && bn[n8] > -20) {
                    final byte[] array = bn;
                    final int n9 = n8;
                    array[n9] += 51;
                    final byte[] array2 = bn;
                    final int n10 = n8 + 1;
                    array2[n10] += 22;
                    final byte[] array3 = bn;
                    final int n11 = n8 + ck;
                    array3[n11] += 22;
                    final byte[] array4 = bn;
                    final int n12 = n8 - ck;
                    array4[n12] += 22;
                    final byte[] array5 = bn;
                    final int n13 = n8 - 1;
                    array5[n13] += 22;
                    final byte[] array6 = bn;
                    final int n14 = n8 - n3;
                    array6[n14] += 16;
                    final byte[] array7 = bn;
                    final int n15 = n8 + n3;
                    array7[n15] += 16;
                    final byte[] array8 = bn;
                    final int n16 = n8 - 2;
                    array8[n16] += 16;
                    final byte[] array9 = bn;
                    final int n17 = n8 + 2;
                    array9[n17] += 16;
                    final byte[] array10 = bn;
                    final int n18 = n8 - n5;
                    array10[n18] += 16;
                    final byte[] array11 = bn;
                    final int n19 = n8 - n4;
                    array11[n19] += 16;
                    final byte[] array12 = bn;
                    final int n20 = n8 + n5;
                    array12[n20] += 16;
                    final byte[] array13 = bn;
                    final int n21 = n8 + n4;
                    array13[n21] += 16;
                }
            }
            return;
        }
        if (this.bl == 1) {
            int j = 0;
            while (j < 9600) {
                final int n22 = this.bA[j++];
                final int n23 = this.bA[j++];
                final int n24 = n22 + n23 * ck;
                if (n22 >= 2 && n22 < n && n23 >= 2 && n23 < n2) {
                    final byte[] array14 = bn;
                    final int n25 = n24;
                    array14[n25] += 16;
                    final byte[] array15 = bn;
                    final int n26 = n24 + 1;
                    array15[n26] += 3;
                    final byte[] array16 = bn;
                    final int n27 = n24 + ck;
                    array16[n27] += 3;
                    final byte[] array17 = bn;
                    final int n28 = n24 - ck;
                    array17[n28] += 3;
                    final byte[] array18 = bn;
                    final int n29 = n24 - 1;
                    array18[n29] += 3;
                }
            }
            return;
        }
        if (this.bl == 2) {
            int k = 0;
            while (k < 9600) {
                final int n30 = this.bA[k++];
                final int n31 = this.bA[k++];
                final int n32 = n30 + n31 * ck;
                if (n30 >= 2 && n30 < n && n31 >= 2 && n31 < n2) {
                    final int n33 = bo[n32];
                    if (n33 < 240) {
                        bo[n32] = n33 + 16;
                    }
                    final int n34 = bo[n32 + 1];
                    if (n34 < 248) {
                        bo[n32 + 1] = n34 + 8;
                    }
                    final int n35 = this.bO[n32 + ck];
                    if (n35 < 248) {
                        bo[n32 + ck] = n35 + 8;
                    }
                    final int n36 = this.bO[n32 - ck];
                    if (n36 < 248) {
                        bo[n32 - ck] = n36 + 8;
                    }
                    final int n37 = this.bO[n32 - 1];
                    if (n37 >= 248) {
                        continue;
                    }
                    bo[n32 - 1] = n37 + 8;
                }
            }
            for (int l = 0; l < cd; ++l) {
                bn[l] = (byte)bo[l];
            }
            return;
        }
        if (this.bl == 3) {
            int n38 = 0;
            while (n38 < 9600) {
                final int n39 = this.bA[n38++];
                final int n40 = this.bA[n38++];
                final int n41 = n39 + n40 * ck;
                if (n39 >= 2 && n39 < n && n40 >= 2 && n40 < n2) {
                    final byte b = bn[n41];
                    if (b < 120) {
                        bn[n41] = (byte)(b + 8);
                    }
                    final byte b2 = bn[n41 + 1];
                    if (b2 < 124) {
                        bn[n41 + 1] = (byte)(b2 + 4);
                    }
                    final byte b3 = bn[n41 + ck];
                    if (b3 < 124) {
                        bn[n41 + ck] = (byte)(b3 + 4);
                    }
                    final byte b4 = bn[n41 - ck];
                    if (b4 < 124) {
                        this.bN[n41 - ck] = (byte)(b4 + 4);
                    }
                    final byte b5 = this.bN[n41 - 1];
                    if (b5 >= 124) {
                        continue;
                    }
                    bn[n41 - 1] = (byte)(b5 + 4);
                }
            }
            return;
        }
        if (this.bl == 4) {
            int n42 = 0;
            while (n42 < 9600) {
                final int n43 = this.bA[n42++];
                final int n44 = this.bA[n42++];
                final int n45 = n43 + n44 * ck;
                if (n43 >= 2 && n43 < n && n44 >= 2 && n44 < n2) {
                    final byte b6 = bn[n45];
                    if (b6 < 96) {
                        bn[n45] = (byte)(b6 + 32);
                    }
                    final byte b7 = bn[n45 + 1];
                    if (b7 < 112) {
                        bn[n45 + 1] = (byte)(b7 + 16);
                    }
                    final byte b8 = bn[n45 + ck];
                    if (b8 < 112) {
                        bn[n45 + ck] = (byte)(b8 + 16);
                    }
                    final byte b9 = bn[n45 - ck];
                    if (b9 < 112) {
                        bn[n45 - ck] = (byte)(b9 + 16);
                    }
                    final byte b10 = bn[n45 - 1];
                    if (b10 < 112) {
                        bn[n45 - 1] = (byte)(b10 + 16);
                    }
                    final byte b11 = bn[n45 - n3];
                    if (b11 < 120) {
                        bn[n45 - n3] = (byte)(b11 + 8);
                    }
                    final byte b12 = bn[n45 + n3];
                    if (b12 < 120) {
                        bn[n45 + n3] = (byte)(b12 + 8);
                    }
                    final byte b13 = bn[n45 - 2];
                    if (b13 < 120) {
                        bn[n45 - 2] = (byte)(b13 + 8);
                    }
                    final byte b14 = bn[n45 + 2];
                    if (b14 < 120) {
                        bn[n45 + 2] = (byte)(b14 + 8);
                    }
                    final byte b15 = bn[n45 - n5];
                    if (b15 < 120) {
                        bn[n45 - n5] = (byte)(b15 + 8);
                    }
                    final byte b16 = bn[n45 - n4];
                    if (b16 < 120) {
                        bn[n45 - n4] = (byte)(b16 + 8);
                    }
                    final byte b17 = bn[n45 + n5];
                    if (b17 < 120) {
                        bn[n45 + n5] = (byte)(b17 + 8);
                    }
                    final byte b18 = bn[n45 + n4];
                    if (b18 >= 120) {
                        continue;
                    }
                    bn[n45 + n4] = (byte)(b18 + 8);
                }
            }
            return;
        }
        if (this.bl == 5) {
            int n46 = 0;
            while (n46 < 9600) {
                final int n47 = this.bA[n46++];
                final int n48 = this.bA[n46++];
                final int n49 = n47 + n48 * ck;
                if (n47 >= 2 && n47 < n && n48 >= 2 && n48 < n2) {
                    final byte b19 = bn[n49];
                    if (b19 < 120) {
                        bn[n49] = (byte)(b19 + 8);
                    }
                    final byte b20 = bn[n49 + 1];
                    if (b20 < 124) {
                        bn[n49 + 1] = (byte)(b20 + 4);
                    }
                    final byte b21 = bn[n49 + ck];
                    if (b21 < 124) {
                        bn[n49 + ck] = (byte)(b21 + 4);
                    }
                    final byte b22 = bn[n49 - ck];
                    if (b22 < 124) {
                        bn[n49 - ck] = (byte)(b22 + 4);
                    }
                    final byte b23 = bn[n49 - 1];
                    if (b23 < 124) {
                        bn[n49 - 1] = (byte)(b23 + 4);
                    }
                    final byte b24 = bn[n49 - n3];
                    if (b24 < 126) {
                        bn[n49 - n3] = (byte)(b24 + 2);
                    }
                    final byte b25 = bn[n49 + n3];
                    if (b25 < 126) {
                        bn[n49 + n3] = (byte)(b25 + 2);
                    }
                    final byte b26 = bn[n49 - 2];
                    if (b26 < 126) {
                        bn[n49 - 2] = (byte)(b26 + 2);
                    }
                    final byte b27 = bn[n49 + 2];
                    if (b27 < 126) {
                        bn[n49 + 2] = (byte)(b27 + 2);
                    }
                    final byte b28 = bn[n49 - n5];
                    if (b28 < 126) {
                        bn[n49 - n5] = (byte)(b28 + 2);
                    }
                    final byte b29 = bn[n49 - n4];
                    if (b29 < 126) {
                        bn[n49 - n4] = (byte)(b29 + 2);
                    }
                    final byte b30 = bn[n49 + n5];
                    if (b30 < 126) {
                        bn[n49 + n5] = (byte)(b30 + 2);
                    }
                    final byte b31 = bn[n49 + n4];
                    if (b31 >= 126) {
                        continue;
                    }
                    bn[n49 + n4] = (byte)(b31 + 2);
                }
            }
            return;
        }
        if (this.bl == 6) {
            int n50 = 0;
            while (n50 < 9600) {
                final int n51 = this.bA[n50++];
                final int n52 = this.bA[n50++];
                final int n53 = n51 + n52 * ck;
                if (n51 >= 2 && n51 < n && n52 >= 2 && n52 < n2) {
                    final byte b32 = bn[n53];
                    if (b32 < 115) {
                        bn[n53] = (byte)(b32 + 13);
                    }
                    final byte b33 = bn[n53 + 1];
                    if (b33 < 120) {
                        bn[n53 + 1] = (byte)(b33 + 8);
                    }
                    final byte b34 = bn[n53 + ck];
                    if (b34 < 120) {
                        bn[n53 + ck] = (byte)(b34 + 8);
                    }
                    final byte b35 = bn[n53 - ck];
                    if (b35 < 120) {
                        bn[n53 - ck] = (byte)(b35 + 8);
                    }
                    final byte b36 = bn[n53 - 1];
                    if (b36 < 120) {
                        bn[n53 - 1] = (byte)(b36 + 8);
                    }
                    final byte b37 = bn[n53 - n3];
                    if (b37 < 124) {
                        bn[n53 - n3] = (byte)(b37 + 4);
                    }
                    final byte b38 = bn[n53 + n3];
                    if (b38 < 124) {
                        bn[n53 + n3] = (byte)(b38 + 4);
                    }
                    final byte b39 = bn[n53 - 2];
                    if (b39 < 124) {
                        bn[n53 - 2] = (byte)(b39 + 4);
                    }
                    final byte b40 = bn[n53 + 2];
                    if (b40 < 124) {
                        bn[n53 + 2] = (byte)(b40 + 4);
                    }
                    final byte b41 = bn[n53 - n5];
                    if (b41 < 124) {
                        bn[n53 - n5] = (byte)(b41 + 4);
                    }
                    final byte b42 = bn[n53 - n4];
                    if (b42 >= 124) {
                        continue;
                    }
                    bn[n53 - n4] = (byte)(b42 + 4);
                }
            }
            return;
        }
        if (this.bl == 7) {
            int n54 = 0;
            while (n54 < 9600) {
                final int n55 = this.bA[n54++];
                final int n56 = this.bA[n54++];
                final int n57 = n55 + n56 * ck;
                if (n55 >= 2 && n55 < n && n56 >= 2 && n56 < n2) {
                    final byte b43 = bn[n57];
                    if (b43 < 96) {
                        bn[n57] = (byte)(b43 + 32);
                    }
                    final byte b44 = bn[n57 + 1];
                    if (b44 < 112) {
                        bn[n57 + 1] = (byte)(b44 + 16);
                    }
                    final byte b45 = bn[n57 + ck];
                    if (b45 < 112) {
                        bn[n57 + ck] = (byte)(b45 + 16);
                    }
                    final byte b46 = bn[n57 - ck];
                    if (b46 < 112) {
                        bn[n57 - ck] = (byte)(b46 + 16);
                    }
                    final byte b47 = bn[n57 - 1];
                    if (b47 >= 112) {
                        continue;
                    }
                    bn[n57 - 1] = (byte)(b47 + 16);
                }
            }
        }
    }
    
    private final void k() {
        int n = 4800;
        int n2 = 0;
        int n3 = 0;
        final float n4 = this.cE[this.g];
        final float n5 = this.cE[256 + this.g];
        final float n6 = this.cE[this.h];
        final float n7 = this.cE[256 + this.h];
        final float n8 = this.cE[this.i];
        final float n9 = this.cE[256 + this.i];
        final float n10 = n4 * n8;
        final float n11 = n4 * n9;
        this.y = n7 * n9 + n10 * n6;
        this.z = n11 * n6 - n8 * n7;
        this.A = n6 * n5;
        this.B = n8 * n5;
        this.C = n5 * n9;
        this.D = -n4;
        this.E = n10 * n7 - n6 * n9;
        this.F = n6 * n8 + n11 * n7;
        this.G = n5 * n7;
        final double n12 = this.cK / 320.0 * 256.0;
        final double n13 = this.N / 200.0 * 256.0;
        final float[] bn = this.bn;
        final int[] ba = this.bA;
        final int p = this.P;
        final int o = this.O;
        do {
            final float n14 = bn[n2++];
            final float n15 = bn[n2++];
            final float n16 = bn[n2++];
            final float n17 = n14 * this.y + n15 * this.z + n16 * this.A;
            final float n18 = n14 * this.B + n15 * this.C + n16 * this.D;
            final int n19 = (int)(n14 * this.E + n15 * this.F + n16 * this.G) + this.cR;
            ba[n3++] = (int)(n17 * (n12 / n19) + p);
            ba[n3++] = (int)(n18 * (n13 / n19) + o);
        } while (--n > 0);
    }
    
    public void run() {
        this.cB.setPriority(this.bz);
        this.showStatus("");
        System.gc();
        if (this.bi != null && !this.e) {
            this.e = this.b();
        }
        this.q = System.currentTimeMillis();
        final Graphics graphics = this.getGraphics();
        if (this.X != null) {
            this.d.setCursor(12);
        }
        else {
            this.d.setCursor(0);
        }
        while (this.cB != null) {
            this.f();
            if (++this.l == this.Y) {
                System.gc();
                this.l = 0;
            }
            try {
                this.i();
            }
            catch (NoSuchMethodError noSuchMethodError) {}
            this.bf.drawImage(this.T, 0, 0, this);
            if (this.bi != null) {
                this.h();
            }
            if (this.cy) {
                this.b(this.bf);
            }
            graphics.drawImage(this.bg, 0, 0, this);
            this.n();
        }
    }
    
    byte a(final int n, final int n2, final int n3, final int n4) {
        final int n5 = n - n2;
        if (n5 >= n3) {
            return (byte)n5;
        }
        return (byte)(n4 - (n3 - n5 - 1));
    }
    
    private final void l() {
        if (this.bM == 0) {
            this.cd = 0;
            while (this.cd == 0) {
                switch (this.bP[this.cc++]) {
                    case 6: {
                        if (this.V) {
                            try {
                                this.a(this.bP[this.cc]);
                            }
                            catch (NoSuchMethodError noSuchMethodError) {
                                this.a(this.bP[this.cc]);
                            }
                            ++this.cc;
                            System.gc();
                            continue;
                        }
                        this.T = null;
                        System.gc();
                        this.bB = this.bD[this.bP[this.cc++]];
                        this.T = this.createImage(this.bB);
                        continue;
                    }
                    default: {
                        continue;
                    }
                    case 0: {
                        switch (this.bP[this.cc++]) {
                            case 0: {
                                this.I = this.bo;
                                break;
                            }
                            case 1: {
                                this.I = this.bp;
                                break;
                            }
                            case 2: {
                                this.I = this.bq;
                                break;
                            }
                            case 3: {
                                this.I = this.br;
                                break;
                            }
                            case 4: {
                                this.I = this.bs;
                                break;
                            }
                            case 5: {
                                this.I = this.bt;
                                break;
                            }
                            case 6: {
                                this.I = this.bu;
                                break;
                            }
                            case 7: {
                                this.I = this.bv;
                                break;
                            }
                            case 8: {
                                this.I = this.bw;
                                break;
                            }
                        }
                        switch (this.bP[this.cc++]) {
                            case 0: {
                                this.cC = this.bo;
                                break;
                            }
                            case 1: {
                                this.cC = this.bp;
                                break;
                            }
                            case 2: {
                                this.cC = this.bq;
                                break;
                            }
                            case 3: {
                                this.cC = this.br;
                                break;
                            }
                            case 4: {
                                this.cC = this.bs;
                                break;
                            }
                            case 5: {
                                this.cC = this.bt;
                                break;
                            }
                            case 6: {
                                this.cC = this.bu;
                                break;
                            }
                            case 7: {
                                this.cC = this.bv;
                                break;
                            }
                            case 8: {
                                this.cC = this.bw;
                                break;
                            }
                        }
                        this.a = this.bP[this.cc++];
                        this.ct = this.bP[this.cc++];
                        continue;
                    }
                    case 1: {
                        this.bM = this.bP[this.cc++];
                        this.cd = 1;
                        continue;
                    }
                    case 2: {
                        this.cc = this.bP[this.cc++];
                        continue;
                    }
                    case 3: {
                        this.bJ = this.bP[this.cc++];
                        this.bK = this.bP[this.cc++];
                        this.bL = this.bP[this.cc++];
                        continue;
                    }
                    case 4: {
                        this.g = this.bP[this.cc++];
                        this.h = this.bP[this.cc++];
                        this.i = this.bP[this.cc++];
                        continue;
                    }
                    case 5: {
                        this.cV = this.bP[this.cc++];
                        this.cQ = this.bP[this.cc++];
                        this.b = this.bP[this.cc++];
                        this.cu = this.bP[this.cc++];
                        continue;
                    }
                }
            }
        }
        else {
            --this.bM;
        }
        this.g += this.bJ;
        this.h += this.bK;
        this.i += this.bL;
        this.g &= 0x3FF;
        this.h &= 0x3FF;
        this.i &= 0x3FF;
    }
    
    public void m() {
        this.cy = false;
        final String parameter = this.getParameter(c("`y#zMwn4bR"));
        if (parameter != null && !parameter.equalsIgnoreCase("NO")) {
            String s = this.getParameter(c("`y#zJml>"));
            if (s == null) {
                s = c("|s)gD{r/oR");
            }
            if (s.equals(c("|s)gD{r/oR"))) {
                this.ch = 0;
            }
            else if (s.equals(c("by)zWw}7"))) {
                this.ch = 1;
            }
            else if (s.equals(c("ns4cWz{"))) {
                this.ch = 2;
            }
            else if (s.equals(c("}r-tQ{q2`Y"))) {
                this.ch = 3;
            }
            if (this.ch == 0) {
                this.a(parameter, 0);
                if (this.ce != null) {
                    this.cy = true;
                }
            }
            else {
                this.a(parameter, 1);
                if (this.W != null) {
                    this.cy = true;
                }
            }
        }
        if (this.cy) {
            String parameter2 = this.getParameter(c("`y#zMdy>j"));
            if (parameter2 == null) {
                parameter2 = "0";
            }
            this.cG = Integer.valueOf(parameter2);
            String s2 = this.getParameter(c("`y#zX{r/"));
            if (s2 == null) {
                s2 = c("Un2oR");
            }
            int n = 0;
            if (this.getParameter(c("`y#z\\{p?")).equalsIgnoreCase(c("MY\b"))) {
                ++n;
            }
            String parameter3 = this.getParameter(c("`y#zW`}7g]"));
            if (parameter3 == null) {
                parameter3 = "NO";
            }
            if (parameter3.equalsIgnoreCase(c("MY\b"))) {
                n += 2;
            }
            String parameter4 = this.getParameter(c("`y#zM}f>"));
            if (parameter4 == null) {
                parameter4 = "12";
            }
            this.H = new Font(s2, n, Integer.valueOf(parameter4));
            if (this.getParameter(c("`y#zM|}?aI")).equalsIgnoreCase(c("MY\b"))) {
                this.cz = true;
            }
            else {
                this.cz = false;
            }
            this.cv = new Color(Integer.valueOf(this.getParameter(c("@y#z}{p\t"))), Integer.valueOf(this.getParameter(c("@y#z}{p\u001c"))), Integer.valueOf(this.getParameter(c("@y#z}{p\u0019"))));
            this.ci = new Color(Integer.valueOf(this.getParameter(c("@y#zmWs7\\"))), Integer.valueOf(this.getParameter(c("@y#zmWs7I"))), Integer.valueOf(this.getParameter(c("@y#zmWs7L"))));
            this.cH = this.size().width;
            this.cI = this.size().height;
            if (this.ch == 0) {
                String parameter5 = this.getParameter(c("`y#zQrz(kJ"));
                if (parameter5 == null) {
                    parameter5 = "0";
                }
                this.cP = Integer.valueOf(parameter5);
                if (this.cP < 0) {
                    this.cP = 0;
                }
                String parameter6 = this.getParameter(c("@y#ztaq+OSd"));
                if (parameter6 == null) {
                    parameter6 = "0";
                }
                this.c = Integer.valueOf(parameter6);
                String parameter7 = this.getParameter(c("@y#ztaq+]Np"));
                if (parameter7 == null) {
                    parameter7 = "0";
                }
                this.cF = Integer.valueOf(parameter7);
                String parameter8 = this.getParameter(c("@y#zm}r>OSd"));
                if (parameter8 == null) {
                    parameter8 = "0";
                }
                this.ck = Integer.valueOf(parameter8);
                String parameter9 = this.getParameter(c("@y#zm}r>]Np"));
                if (parameter9 == null) {
                    parameter9 = "0";
                }
                this.co = Integer.valueOf(parameter9);
                String parameter10 = this.getParameter(c("@y#zm}r>OPsp>"));
                if (parameter10 == null) {
                    parameter10 = "0";
                }
                this.cl = Integer.valueOf(parameter10);
                final FontMetrics fontMetrics = this.getGraphics().getFontMetrics(this.H);
                this.cg = fontMetrics.stringWidth(this.ce);
                this.cf = fontMetrics.getHeight();
                this.r = fontMetrics.getMaxDescent();
                this.cN = this.cH;
                if (this.cP < this.cf - this.r) {
                    this.cP = this.cf - this.r;
                }
                else if (this.cP > this.cI - this.r) {
                    this.cP = this.cI - this.r;
                }
                if (this.ck != 0) {
                    this.cp = new int[this.cH + 360];
                    this.cq = new int[this.cH + 360];
                    for (int i = 0; i < this.cH + 360; ++i) {
                        this.cp[i] = (int)(this.ck * Math.sin(this.cl * i * 3.141592653589793 / 180.0)) - this.cf - this.r + this.cP;
                        this.cq[i] = this.cp[i] - this.bF;
                    }
                    this.cj = 360;
                    this.cm = this.cf + this.r + 1;
                    this.cn = this.cm - 1;
                }
            }
            else {
                if (this.ch == 1) {
                    String parameter11 = this.getParameter(c("`y#zHgl:m["));
                    if (parameter11 == null) {
                        parameter11 = "10";
                    }
                    final int intValue = Integer.valueOf(parameter11);
                    final FontMetrics fontMetrics2 = this.getGraphics().getFontMetrics(this.H);
                    this.J = fontMetrics2.getHeight() + intValue;
                    this.cJ = new int[this.W.length];
                    this.S = 0;
                    while (this.S < this.W.length) {
                        this.cJ[this.S] = (this.cH - fontMetrics2.stringWidth(this.W[this.S])) / 2;
                        ++this.S;
                    }
                    this.bc = -this.J;
                    return;
                }
                if (this.ch >= 2) {
                    String parameter12 = this.getParameter(c("`y#zS}r=aP`"));
                    if (parameter12 == null) {
                        parameter12 = "2";
                    }
                    this.cx = Integer.valueOf(parameter12);
                    String parameter13 = this.getParameter(c("`y#zSud=aP`"));
                    if (parameter13 == null) {
                        parameter13 = "72";
                    }
                    this.cw = Integer.valueOf(parameter13);
                    this.be = this.cw - this.cx;
                    this.H = null;
                    this.cU = new Font[this.be];
                    int cx = this.cx;
                    this.S = 0;
                    while (this.S < this.be) {
                        this.cU[this.S] = new Font(s2, n, cx++);
                        ++this.S;
                    }
                    this.R = this.cH / 2.0f;
                    this.Q = this.cI / 2.0f;
                    if (this.ch == 3) {
                        this.cT = this.be - 1;
                        return;
                    }
                    this.cT = 0;
                }
            }
        }
    }
    
    public void b(final Graphics graphics) {
        switch (this.ch) {
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
        if (this.cB == null) {
            (this.cB = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.cB != null && this.cB.isAlive()) {
            this.cB.stop();
        }
        this.cB = null;
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void c(final Graphics graphics) {
        graphics.setFont(this.H);
        this.bc += this.cG;
        if (this.bc > this.cI + this.W.length * this.J) {
            this.bc = -this.J;
        }
        if (this.cz) {
            for (int i = 0; i < this.W.length; ++i) {
                final String s = this.W[i];
                final int n = this.cJ[i];
                final int n2 = this.cI - this.bc + i * this.J;
                graphics.setColor(this.ci);
                graphics.drawString(s, n + 1, n2 + 1);
                graphics.setColor(this.cv);
                graphics.drawString(s, n, n2);
            }
            return;
        }
        graphics.setColor(this.cv);
        for (int j = 0; j < this.W.length; ++j) {
            graphics.drawString(this.W[j], this.cJ[j], this.cI - this.bc + j * this.J);
        }
    }
    
    public synchronized void n() {
        Thread.yield();
        this.cA.sync();
        final long n = 10L - (System.currentTimeMillis() - this.q);
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
        this.q = System.currentTimeMillis();
        try {
            Thread.sleep(this.Z);
        }
        catch (InterruptedException ex3) {}
    }
    
    public void d(final Graphics graphics) {
        final String s = this.W[this.cS];
        graphics.setFont(this.cU[this.cT]);
        final FontMetrics fontMetrics = graphics.getFontMetrics(this.cU[this.cT]);
        final int n = (int)(this.R - fontMetrics.stringWidth(s) / 2.0f);
        final int n2 = (int)(this.Q + fontMetrics.getHeight() / 4.0f);
        if (this.cz) {
            graphics.setColor(this.ci);
            graphics.drawString(s, n + 1, n2 + 1);
        }
        graphics.setColor(this.cv);
        graphics.drawString(s, n, n2);
        if (this.ch == 3) {
            this.cT -= this.cG;
            if (this.cT <= 1) {
                this.cT = this.be - 1;
                ++this.cS;
                if (this.cS >= this.W.length) {
                    this.cS = 0;
                }
            }
        }
        else {
            this.cT += this.cG;
            if (this.cT >= this.be) {
                this.cT = 0;
                ++this.cS;
                if (this.cS >= this.W.length) {
                    this.cS = 0;
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
                char c = '\u0014';
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
                                c = '\u001c';
                                array = (array2 = charArray);
                                n3 = (n4 = n);
                                continue;
                            }
                            c = '[';
                            array = (array2 = charArray);
                            n3 = (n4 = n);
                            continue;
                        }
                        c = '\u000e';
                        array = (array2 = charArray);
                        n3 = (n4 = n);
                        continue;
                    }
                    c = '>';
                    array = (array2 = charArray);
                    n3 = (n4 = n);
                }
            }
            break;
        }
        return new String(charArray);
    }
}
