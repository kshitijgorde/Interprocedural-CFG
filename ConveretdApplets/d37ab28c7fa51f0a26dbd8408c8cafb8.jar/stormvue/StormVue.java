// 
// Decompiled by Procyon v0.5.30
// 

package stormvue;

import java.awt.event.MouseEvent;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.io.DataInputStream;
import java.util.StringTokenizer;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.Hashtable;
import java.awt.FontMetrics;
import java.awt.Toolkit;
import java.awt.image.PixelGrabber;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Color;
import java.net.URL;
import java.util.Vector;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.applet.Applet;

public class StormVue extends Applet implements Runnable, MouseListener, MouseMotionListener
{
    static final double aG = 57.29577951308232;
    static final double be = 0.017453292519943295;
    static final int bZ = -99999;
    static final int b = 10000;
    static final int Q = 0;
    static final int bI = 1;
    static final int g = 0;
    static final int by = 1;
    static final int bG = 2;
    static final String an = "1.54";
    int ai;
    int j;
    int t;
    int v;
    int aj;
    int U;
    int T;
    boolean a5;
    boolean ah;
    boolean O;
    boolean bW;
    boolean bw;
    int b0;
    int a0;
    String ar;
    boolean bv;
    String case;
    int m;
    int aB;
    double bX;
    double bA;
    double c;
    double a6;
    int bf;
    int au;
    int R;
    int null;
    Thread bd;
    MediaTracker aa;
    Image bp;
    Image ab;
    Image q;
    Image[] s;
    Image bF;
    Image aw;
    Image bg;
    Image bP;
    Image bc;
    Image aE;
    Image try;
    Image am;
    Image z;
    Image b2;
    Image N;
    int[] bb;
    int[] aW;
    Image bo;
    Image a3;
    Image a;
    Image[] aF;
    Image[] int;
    Image[] aJ;
    int ag;
    int r;
    int aP;
    int o;
    int width;
    int height;
    int new;
    int bt;
    Graphics bN;
    Graphics bl;
    Image ae;
    Image aQ;
    SymbolGenerator at;
    int aS;
    int aR;
    boolean h;
    boolean bu;
    Font M;
    String aO;
    String P;
    String bT;
    String f;
    String b1;
    boolean V;
    String bB;
    String aT;
    String K;
    String aV;
    int W;
    int aN;
    int bD;
    int H;
    String aZ;
    int aA;
    int bj;
    int bi;
    int aK;
    int d;
    int bJ;
    int bK;
    int i;
    int k;
    int Z;
    int a9;
    int ao;
    int as;
    int bO;
    int bU;
    double if;
    double do;
    double u;
    double w;
    double aY;
    int ad;
    int[] goto;
    int[] x;
    int[] y;
    int[] aX;
    int p;
    int bh;
    int bx;
    int J;
    int for;
    static final int af = 0;
    static final int al = 1;
    static final int B = 2;
    static final int G = 3;
    double ac;
    double bm;
    double ay;
    double ax;
    int else;
    int L;
    int byte;
    int bE;
    int aU;
    int bM;
    int[] E;
    int[] bH;
    int[] bR;
    int[] aq;
    int[] a4;
    int[] D;
    double char;
    int l;
    int bY;
    Vector n;
    URL ba;
    String e;
    double I;
    double bL;
    double bS;
    int a7;
    int ak;
    int F;
    boolean aM;
    boolean C;
    String[] S;
    Color[] a2;
    Color[] a8;
    Color ap;
    Color bQ;
    Color bn;
    Color Y;
    int a1;
    int A;
    int bs;
    int aD;
    int aC;
    int br;
    int bq;
    int bV;
    int av;
    int az;
    int void;
    int aI;
    int aH;
    int bC;
    int bz;
    int aL;
    boolean long;
    int X;
    Cursor bk;
    
    public void init() {
        super.init();
        this.bW = false;
        this.bw = false;
        this.case = "Loading StormVue...";
        System.out.println("StormVue-1 rev.1.54 (c)2004 Astrogenic Systems");
        System.out.println("More info available at http://www.astrogenic.com");
        this.ba = this.getDocumentBase();
        this.a2[0] = new Color(65365);
        this.a2[1] = new Color(16776960);
        this.a2[2] = new Color(16760072);
        this.a2[3] = new Color(16711680);
        this.a2[4] = new Color(10747904);
        this.a2[5] = new Color(6160384);
        this.a8[0] = new Color(16777215);
        this.a8[1] = new Color(16777215);
        this.a8[2] = new Color(16777215);
        this.a8[3] = new Color(16777215);
        this.do();
        this.aa = new MediaTracker(this);
        this.ab = this.getImage(this.ba, "stormvue/stormvue.gif");
        this.s[0] = this.getImage(this.ba, this.S[0]);
        this.s[1] = this.getImage(this.ba, this.S[1]);
        this.bF = this.getImage(this.ba, "stormvue/basemap.gif");
        this.q = this.getImage(this.ba, "stormvue/cmp.gif");
        this.bo = this.getImage(this.ba, "stormvue/charset7.gif");
        this.a3 = this.getImage(this.ba, "stormvue/digits2.gif");
        this.z = this.getImage(this.ba, "stormvue/psaccum.gif");
        this.b2 = this.getImage(this.ba, "stormvue/nsaccum.gif");
        this.aw = this.getImage(this.ba, "stormvue/strikes.gif");
        this.bg = this.getImage(this.ba, "stormvue/lact.gif");
        this.bP = this.getImage(this.ba, "stormvue/acctot.gif");
        this.bc = this.getImage(this.ba, "stormvue/syst.gif");
        this.am = this.getImage(this.ba, "stormvue/rates.gif");
        this.aE = this.getImage(this.ba, "stormvue/colkey.gif");
        this.try = this.getImage(this.ba, "stormvue/colkey2.gif");
        this.a = this.getImage(this.ba, "stormvue/cmbut.gif");
        this.N = this.getImage(this.ba, "stormvue/cmbar.gif");
        this.aa.addImage(this.ab, 0);
        this.aa.addImage(this.bF, 1);
        this.aa.addImage(this.q, 2);
        this.aa.addImage(this.bo, 3);
        this.aa.addImage(this.a3, 4);
        this.aa.addImage(this.z, 5);
        this.aa.addImage(this.b2, 6);
        this.aa.addImage(this.aw, 7);
        this.aa.addImage(this.bg, 8);
        this.aa.addImage(this.bP, 9);
        this.aa.addImage(this.bc, 10);
        this.aa.addImage(this.try, 11);
        this.aa.addImage(this.try, 12);
        this.aa.addImage(this.am, 13);
        this.aa.addImage(this.s[0], 14);
        this.aa.addImage(this.s[1], 15);
        this.aa.addImage(this.a, 16);
        this.aa.addImage(this.N, 17);
        this.a0 = 0;
        this.start();
    }
    
    public void a() {
        if (this.s[0].getHeight(this) != 500) {
            this.s[0] = this.bF;
        }
        if (this.s[1].getHeight(this) != 500) {
            this.s[1] = this.bF;
        }
        this.ai = 640;
        this.j = this.ab.getHeight(this) + 8 + this.s[0].getHeight(this) + 20;
        this.ae = this.createImage(this.ai, this.j);
        this.bN = this.ae.getGraphics();
        this.M = new Font("System", 0, 11);
        this.bN.setFont(this.M);
        this.width = this.bo.getWidth(this);
        this.height = this.bo.getHeight(this);
        this.bb = new int[this.width * this.height];
        this.aP = this.width / 79;
        final PixelGrabber pixelGrabber = new PixelGrabber(this.bo, 0, 0, this.width, this.height, this.bb, 0, this.width);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {}
        this.int();
        this.aF = new Image[256];
        int n = 33;
        for (int i = 0; i < this.new - this.aP; i += this.aP) {
            this.try();
            this.a(i, 0, this.aP, this.height);
            this.aF[n++] = this.case();
        }
        this.width = this.a3.getWidth(this);
        this.height = this.a3.getHeight(this);
        this.bb = new int[this.width * this.height];
        this.o = this.width / 10;
        final PixelGrabber pixelGrabber2 = new PixelGrabber(this.a3, 0, 0, this.width, this.height, this.bb, 0, this.width);
        try {
            pixelGrabber2.grabPixels();
        }
        catch (InterruptedException ex2) {}
        this.int();
        this.int = new Image[11];
        int n2 = 0;
        for (int j = 0; j < this.new - this.o; j += this.o) {
            this.try();
            this.a(j, 0, this.o, this.height);
            this.int[n2++] = this.case();
        }
        this.width = this.a.getWidth(this);
        this.height = this.a.getHeight(this);
        this.bb = new int[this.width * this.height];
        final int n3 = this.width / 12;
        final PixelGrabber pixelGrabber3 = new PixelGrabber(this.a, 0, 0, this.width, this.height, this.bb, 0, this.width);
        try {
            pixelGrabber3.grabPixels();
        }
        catch (InterruptedException ex3) {
            System.err.println("grabPixels fail");
        }
        this.int();
        this.aJ = new Image[12];
        int n4 = 0;
        for (int k = 0; k < this.new; k += n3) {
            this.try();
            this.a(k, 0, n3, this.height);
            this.aJ[n4++] = this.case();
        }
        this.O = false;
        this.aB = 1800;
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.new();
    }
    
    public void new() {
        this.au = 36;
        this.R = 0;
        this.aK = 0;
        this.d = 0;
        this.bJ = 0;
        this.bK = 0;
        this.i = 0;
        this.k = 0;
        this.Z = 0;
        this.a9 = 0;
        this.ao = 0;
        this.as = 0;
        this.bO = 0;
        this.bU = 0;
        this.V = false;
        this.a(this.ar, this.ba);
        this.bj = ((this.ak == 59) ? 3500 : 1700);
        this.bi = 50;
        this.aA = 0;
    }
    
    public void start() {
        if (this.bd == null) {
            (this.bd = new Thread(this)).setPriority(1);
            this.bd.start();
        }
    }
    
    public void stop() {
        this.aM = false;
        this.bd = null;
    }
    
    public void run() {
        try {
            try {
                while (this.aa.statusAll(true) != 8) {
                    this.m += 2;
                    this.repaint();
                    Thread.sleep(100L);
                    if (this.aa.isErrorID(14)) {
                        this.aa.removeImage(this.s[0], 14);
                    }
                    if (this.aa.isErrorID(15)) {
                        this.aa.removeImage(this.s[1], 15);
                    }
                }
            }
            catch (InterruptedException ex) {}
            finally {
                this.case = "Loading complete, starting...";
                this.a0 = 10;
                this.repaint();
                try {
                    Thread.sleep(1500L);
                }
                catch (InterruptedException ex2) {}
                this.bW = true;
                this.a();
                long currentTimeMillis = System.currentTimeMillis();
                this.bd.setPriority(10);
                while (this.bd != null) {
                    try {
                        currentTimeMillis += this.au;
                        Thread.sleep(Math.max(0L, currentTimeMillis - System.currentTimeMillis()));
                    }
                    catch (InterruptedException ex3) {}
                    if (this.R++ > this.aB) {
                        this.R = 0;
                        this.new();
                        this.a5 = true;
                    }
                    this.repaint();
                }
            }
        }
        catch (NullPointerException ex4) {}
    }
    
    public void paint(final Graphics graphics) {
        if (this.bW) {
            try {
                if (this.a5 || this.aA >= (this.aM ? 5 : 15)) {
                    this.bN.setColor(Color.black);
                    this.bN.fillRect(0, 0, this.ai, this.j);
                    this.bN.drawImage(this.ab, 12, 4, this);
                    final int n = this.ab.getHeight(this) + 8;
                    this.bN.drawImage(this.s[this.F], 12, n, this);
                    final int n2 = n + (this.s[this.F].getHeight(this) + 20);
                    this.bN.drawImage(this.q, 12, 542, this);
                    if (this.a7 == 0) {
                        this.int(this.bN);
                    }
                    else {
                        this.do(this.bN);
                    }
                    this.a(this.bN);
                    if (this.n.size() != 0) {
                        if (!this.aM) {
                            this.a(this.bN, this.a(this.b1));
                        }
                        else {
                            this.new(this.bN);
                        }
                    }
                    this.if(this.bN);
                    this.for(this.bN);
                    this.a5 = false;
                }
                else {
                    this.a(this.s[this.F].getWidth(this) + 20, this.s[this.F].getWidth(this) + 95, 370);
                }
                graphics.drawImage(this.ae, 0, 0, this);
                if (this.aA++ > (this.aM ? 5 : 15)) {
                    this.aA = 0;
                }
                return;
            }
            catch (Exception ex) {
                return;
            }
        }
        final String s = "Astrogenic Systems";
        final Font font = new Font("System", 1, 14);
        graphics.setFont(font);
        final FontMetrics fontMetrics = graphics.getFontMetrics(font);
        final int n3 = this.getSize().height / 2;
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, this.getSize().width, this.getSize().height);
        graphics.setColor(Color.orange);
        graphics.drawString(s, this.getSize().width / 2 - fontMetrics.stringWidth(s) / 2, n3 - 120);
        graphics.setColor(Color.lightGray);
        final String s2 = "www.astrogenic.com";
        graphics.drawString(s2, this.getSize().width / 2 - fontMetrics.stringWidth(s2) / 2, n3 - 100);
        graphics.setColor(Color.green);
        final String s3 = "StormVue 1.54";
        graphics.drawString(s3, this.getSize().width / 2 - fontMetrics.stringWidth(s3) / 2, n3 - 60);
        final Font font2 = new Font("System", 0, 12);
        graphics.setFont(font2);
        final FontMetrics fontMetrics2 = graphics.getFontMetrics(font2);
        graphics.setColor(Color.lightGray);
        graphics.drawString(this.case, this.getSize().width / 2 - fontMetrics2.stringWidth(this.case) / 2, n3 - 20);
        graphics.setColor(Color.white);
        graphics.drawRoundRect(this.getSize().width / 2 - 75, n3 - 4, 155, 15, 5, 5);
        graphics.setColor(Color.green);
        this.b0 = this.getSize().width / 2 - 70;
        for (int i = 0; i < this.a0; ++i) {
            graphics.fillRect(this.b0, n3, 10, 8);
            this.b0 += 15;
        }
        if (this.a0 < 10) {
            ++this.a0;
        }
        if (this.bv) {
            final Font font3 = new Font("System", 1, 14);
            graphics.setFont(font3);
            final FontMetrics fontMetrics3 = graphics.getFontMetrics(font3);
            graphics.setColor(Color.yellow);
            final String s4 = "Not to be used for protection of life and property";
            graphics.drawString(s4, this.getSize().width / 2 - fontMetrics3.stringWidth(s4) / 2, n3 + 65);
        }
        Toolkit.getDefaultToolkit().sync();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void a(final Graphics graphics, final String s, final int n, final int n2) {
        int n3 = n;
        final int length = s.length();
        final char[] array = new char[length];
        s.getChars(0, length, array, 0);
        for (final char c : array) {
            if (c == ' ') {
                n3 += this.aP;
            }
            if (c > ' ') {
                graphics.drawImage(this.aF[c], n3, n2, this);
                n3 += this.aP;
            }
        }
    }
    
    public void a(final Graphics graphics, final String s, final int n, final int n2, final int n3) {
        int n4 = n;
        final int length = s.length();
        final char[] array = new char[length];
        s.getChars(0, length, array, 0);
        if (n3 == 1) {
            for (int i = length - 1; i >= 0; --i) {
                final char c = (char)(array[i] - '0');
                if (c >= '\0' && c < '\n') {
                    graphics.drawImage(this.int[c], n4, n2, this);
                    n4 -= this.o;
                }
            }
            return;
        }
        for (int j = 0; j < length; ++j) {
            final char c2 = (char)(array[j] - '0');
            if (c2 >= '\0' && c2 < '\n') {
                graphics.drawImage(this.int[c2], n4, n2, this);
                n4 += this.o;
            }
        }
    }
    
    public void a(final Graphics graphics) {
        int n = 210;
        n += 2;
        this.bN.drawImage(this.N, n - 9, 5, this);
        if (this.ak == 29) {
            this.bN.drawImage(this.aJ[1], n, 22, this);
            this.bN.drawImage(this.aJ[2], n + 34, 22, this);
        }
        else {
            this.bN.drawImage(this.aJ[0], n, 22, this);
            this.bN.drawImage(this.aJ[3], n + 34, 22, this);
        }
        n += 80;
        if (this.F == 0) {
            this.bN.drawImage(this.aJ[5], n, 22, this);
            this.bN.drawImage(this.aJ[6], n + 34, 22, this);
        }
        else {
            this.bN.drawImage(this.aJ[4], n, 22, this);
            this.bN.drawImage(this.aJ[7], n + 34, 22, this);
        }
        n += 80;
        if (!this.aM) {
            this.bN.drawImage(this.aJ[9], n, 22, this);
            this.bN.drawImage(this.aJ[10], n + 34, 22, this);
            return;
        }
        this.bN.drawImage(this.aJ[8], n, 22, this);
        this.bN.drawImage(this.aJ[11], n + 34, 22, this);
    }
    
    public void if(final Graphics graphics) {
        final int n2;
        final int n = n2 = this.s[this.F].getWidth(this) + 24;
        final int n3 = n + 30;
        final int n4 = n + 59;
        final int n5 = n + 89;
        int bj = 0;
        int bk = 0;
        int i = 0;
        int k = 0;
        int ao = 0;
        int as = 0;
        int bo = 0;
        int bu = 0;
        this.if = 0.0;
        this.do = 0.0;
        this.u = 0.0;
        this.w = 0.0;
        this.aY = 0.0;
        if (this.aM) {
            bj = this.bJ;
            bk = this.bK;
            i = this.i;
            k = this.k;
            ao = this.ao;
            as = this.as;
            bo = this.bO;
            bu = this.bU;
            final boolean b = false;
            this.ao = (b ? 1 : 0);
            this.bJ = (b ? 1 : 0);
            final boolean b2 = false;
            this.as = (b2 ? 1 : 0);
            this.bK = (b2 ? 1 : 0);
            final boolean b3 = false;
            this.bO = (b3 ? 1 : 0);
            this.i = (b3 ? 1 : 0);
            final boolean b4 = false;
            this.bU = (b4 ? 1 : 0);
            this.k = (b4 ? 1 : 0);
            int n6 = 59 - this.bj / 59;
            if (n6 > 59) {
                n6 = 59;
            }
            for (int j = (this.ak == 29) ? 30 : 0; j <= n6; ++j) {
                this.bJ += this.bR[j];
                this.ao = this.bJ;
                this.bK += this.aq[j];
                this.as = this.bK;
                this.i += this.a4[j];
                this.bO = this.i;
                this.k += this.D[j];
                this.bU = this.k;
            }
        }
        if (this.ak == 59) {
            this.aY = this.bJ + this.bK + this.i + this.k;
        }
        else {
            this.aY = this.ao + this.as + this.bO + this.bU;
        }
        this.a(this.bN, "+CG  +IC  -CG  -IC", n - 5, 177);
        if (this.aY != 0.0) {
            if (this.ak == 59) {
                this.if = this.bJ / this.aY;
                this.do = this.bK / this.aY;
                this.u = this.i / this.aY;
                this.w = this.k / this.aY;
            }
            else {
                this.if = this.ao / this.aY;
                this.do = this.as / this.aY;
                this.u = this.bO / this.aY;
                this.w = this.bU / this.aY;
            }
        }
        if (this.aM) {
            this.bJ = bj;
            this.bK = bk;
            this.i = i;
            this.k = k;
            this.ao = ao;
            this.as = as;
            this.bO = bo;
            this.bU = bu;
        }
        this.bN.setColor(new Color(12124160));
        final int n7 = (int)(23.0 - this.if * 23.0);
        if (this.if >= 0.01 && this.aY != 0.0) {
            this.bN.fillRect(n2, 152 + n7, 15, 23 - n7);
        }
        final String string = Double.toString(this.if * 100.0);
        this.a(this.bN, String.valueOf(string.substring(0, string.indexOf("."))) + "%", n2 - 1, 152 + n7 - 12);
        this.bN.setColor(new Color(12124160));
        final int n8 = (int)(23.0 - this.u * 23.0);
        if (this.u >= 0.01 && this.aY != 0.0) {
            this.bN.fillRect(n3, 152 + n8, 15, 23 - n8);
        }
        final String string2 = Double.toString(this.u * 100.0);
        this.a(this.bN, String.valueOf(string2.substring(0, string2.indexOf("."))) + "%", n3 - 1, 152 + n8 - 12);
        this.bN.setColor(new Color(21156));
        final int n9 = (int)(23.0 - this.do * 23.0);
        if (this.do >= 0.01 && this.aY != 0.0) {
            this.bN.fillRect(n4, 152 + n9, 15, 23 - n9);
        }
        final String string3 = Double.toString(this.do * 100.0);
        this.a(this.bN, String.valueOf(string3.substring(0, string3.indexOf("."))) + "%", n4 - 1, 152 + n9 - 12);
        this.bN.setColor(new Color(21156));
        final int n10 = (int)(23.0 - this.w * 23.0);
        if (this.w >= 0.01 && this.aY != 0.0) {
            this.bN.fillRect(n5, 152 + n10, 15, 23 - n10);
        }
        final String string4 = Double.toString(this.w * 100.0);
        this.a(this.bN, String.valueOf(string4.substring(0, string4.indexOf("."))) + "%", n5 - 1, 152 + n10 - 12);
        final int n11 = n - 4;
        final int n12 = n11 + 107;
        final double n13 = (n12 - n11) / 60.0;
        int n14 = 0;
        int n15 = 0;
        this.bN.drawImage(this.am, n11 - 7, 194, this);
        this.bN.setColor(Color.gray);
        this.bN.drawLine(n11, 208, n11, 248);
        this.bN.drawLine(n11, 248, n12, 248);
        if (this.n.size() > 0) {
            final int n16 = (!this.aM || this.bj < 1) ? 60 : (60 - this.bj / 60);
            double n17 = 0.0;
            for (int l = (this.ak == 29) ? 29 : 0; l < n16; ++l) {
                if (n17 <= this.E[l]) {
                    n17 = this.E[l];
                    n14 = (int)n17;
                    n15 = l;
                }
            }
            final double n18 = 35.0 / n17;
            this.bN.setColor(new Color(52224));
            double n20;
            double n19 = n20 = n11 + 1;
            int n21 = 0;
            int n22 = 1;
            for (int n23 = (this.ak == 29) ? 30 : 0; n23 < n16 - 1; ++n23) {
                final int n24 = (int)(this.E[n23] * n18);
                if (n23 == n15) {
                    final int[] array = { 250, 255, 255 };
                    final int[] array2 = { (int)n19 + 1, (int)n19 + 4, (int)n19 - 2 };
                    this.bN.setColor(Color.yellow);
                    this.bN.fillPolygon(array2, array, 3);
                    this.bN.setColor(new Color(52224));
                }
                int n25 = 208 + (40 - n24);
                if (n25 == 248) {
                    --n25;
                }
                if (!this.aM) {
                    this.a(this.bH[n23], -1);
                }
                else {
                    this.a(this.bH[(n23 + 1 < 60) ? (n23 + 1) : n23], -1);
                }
                if (n23 > n16 - 6) {
                    this.if(-1);
                }
                if (n22 == 0 && n25 != 248) {
                    this.bN.drawLine((int)n20, n21, (int)n19, n25);
                }
                n22 = 0;
                n20 = n19;
                n21 = n25;
                n19 += ((this.ak == 29) ? (n13 * 2.0) : n13);
            }
            this.a(this.bN, "Current", n11, 258);
            if (!this.aM) {
                this.a(this.bN, String.valueOf(Integer.toString(this.bD)) + "/m", n11 + 60, 258);
            }
            else {
                int n26 = 59 - this.bj / 59 - 1;
                if (n26 > 58) {
                    n26 = 58;
                }
                if (n26 < 0) {
                    n26 = 0;
                }
                this.a(this.bN, String.valueOf(Integer.toString(this.E[n26])) + "/m", n11 + 60, 258);
            }
            this.a(this.bN, "Peak", n11, 269);
            this.a(this.bN, String.valueOf(Integer.toString(n14)) + "/m", n11 + 60, 269);
        }
    }
    
    public void a(final int n, final int n2) {
        int a = this.a(this.b1);
        if (this.aM) {
            a -= this.bj;
        }
        if (n >= a - 60) {
            if (n2 != -1) {
                switch (n2) {
                    case 2: {
                        this.a2[0] = this.a8[0];
                        break;
                    }
                    case 0: {
                        this.a2[0] = this.a8[1];
                        break;
                    }
                    case 3: {
                        this.a2[0] = this.a8[2];
                        break;
                    }
                    case 1: {
                        this.a2[0] = this.a8[3];
                        break;
                    }
                }
            }
            this.bN.setColor(this.a2[0]);
            return;
        }
        if (this.ak == 59) {
            if (n < a - 60 && n >= a - 420) {
                this.bN.setColor(this.a2[1]);
                return;
            }
            if (n < a - 420 && n >= a - 900) {
                this.bN.setColor(this.a2[2]);
                return;
            }
            if (n < a - 900 && n >= a - 1800) {
                this.bN.setColor(this.a2[3]);
                return;
            }
            if (n < a - 1800 && n >= a - 2700) {
                this.bN.setColor(this.a2[4]);
                return;
            }
            if (n < a - 2700 && n >= a - 3600) {
                this.bN.setColor(this.a2[5]);
                return;
            }
            this.bN.setColor(Color.black);
        }
        else {
            if (n < a - 60 && n >= a - 300) {
                this.bN.setColor(this.a2[1]);
                return;
            }
            if (n < a - 300 && n >= a - 600) {
                this.bN.setColor(this.a2[2]);
                return;
            }
            if (n < a - 600 && n >= a - 900) {
                this.bN.setColor(this.a2[3]);
                return;
            }
            if (n < a - 900 && n >= a - 1200) {
                this.bN.setColor(this.a2[4]);
                return;
            }
            if (n < a - 1200 && n >= a - 1800) {
                this.bN.setColor(this.a2[5]);
                return;
            }
            this.bN.setColor(Color.black);
        }
    }
    
    public void if(final int n) {
        switch (n) {
            case 2: {
                this.a2[0] = this.a8[0];
                break;
            }
            case 0: {
                this.a2[0] = this.a8[1];
                break;
            }
            case 3: {
                this.a2[0] = this.a8[2];
                break;
            }
            case 1: {
                this.a2[0] = this.a8[3];
                break;
            }
            default: {
                this.a2[0] = this.ap;
                break;
            }
        }
        this.bN.setColor(this.a2[0]);
    }
    
    public void for(final Graphics graphics) {
        final int n = this.s[this.F].getWidth(this) + 20;
        final int n2 = n + 75;
        int bj = 0;
        int bk = 0;
        int i = 0;
        int k = 0;
        int ao = 0;
        int as = 0;
        int bo = 0;
        int bu = 0;
        if (this.aM) {
            bj = this.bJ;
            bk = this.bK;
            i = this.i;
            k = this.k;
            ao = this.ao;
            as = this.as;
            bo = this.bO;
            bu = this.bU;
            final boolean b = false;
            this.ao = (b ? 1 : 0);
            this.bJ = (b ? 1 : 0);
            final boolean b2 = false;
            this.as = (b2 ? 1 : 0);
            this.bK = (b2 ? 1 : 0);
            final boolean b3 = false;
            this.bO = (b3 ? 1 : 0);
            this.i = (b3 ? 1 : 0);
            final boolean b4 = false;
            this.bU = (b4 ? 1 : 0);
            this.k = (b4 ? 1 : 0);
            int n3 = 59 - this.bj / 59;
            if (n3 > 59) {
                n3 = 59;
            }
            for (int j = (this.ak == 29) ? 30 : 0; j <= n3; ++j) {
                this.bJ += this.bR[j];
                this.ao = this.bJ;
                this.bK += this.aq[j];
                this.as = this.bK;
                this.i += this.a4[j];
                this.bO = this.i;
                this.k += this.D[j];
                this.bU = this.k;
            }
        }
        this.bN.drawImage(this.aw, n - 7, 38, this);
        if (!this.aM) {
            this.a(this.bN, Integer.toString((this.ak == 29) ? this.aN : this.W), n + this.aw.getWidth(this) - 35, 52, 1);
        }
        else {
            int n4 = this.bJ + this.bK + this.i + this.k;
            if (n4 < 0) {
                n4 = 0;
            }
            this.a(this.bN, Integer.toString(n4), n + this.aw.getWidth(this) - 35, 52, 1);
        }
        this.bN.drawImage(this.z, n - 7, 73, this);
        this.bN.drawImage(this.b2, n - 7, 105, this);
        this.bN.setFont(new Font("SansSerif", 1, 14));
        this.bN.setColor(this.bQ);
        if (this.ak == 59) {
            this.bN.drawString(Integer.toString(this.bJ), n + 3, 98);
            this.bN.drawString(Integer.toString(this.i), n2 + 6, 98);
            this.bN.drawString(Integer.toString(this.bK), n + 3, 130);
            this.bN.drawString(Integer.toString(this.k), n2 + 6, 130);
        }
        else {
            this.bN.drawString(Integer.toString(this.ao), n + 3, 98);
            this.bN.drawString(Integer.toString(this.bO), n2 + 6, 98);
            this.bN.drawString(Integer.toString(this.as), n + 3, 130);
            this.bN.drawString(Integer.toString(this.bU), n2 + 6, 130);
        }
        if (this.aM) {
            this.bJ = bj;
            this.bK = bk;
            this.i = i;
            this.k = k;
            this.ao = ao;
            this.as = as;
            this.bO = bo;
            this.bU = bu;
        }
        this.bN.drawImage(this.bg, n - 7, 285, this);
        final int index = this.P.indexOf(" ");
        final String substring = this.P.substring(0, index);
        final String substring2 = this.P.substring(index + 1, this.P.length());
        this.a(this.bN, "Date " + substring, n, 300);
        this.a(this.bN, "Time " + substring2, n, 311);
        this.K = Integer.toString(this.ak + 1);
        this.a(this.bN, "Timezone UTC" + this.aV, n, 322);
        int n5 = 350;
        this.bN.drawImage(this.bP, n - 7, n5 - 12, this);
        this.a(this.bN, "Strikes", n, n5 + 2);
        this.a(this.bN, this.bB, n2 - 15, n5 + 2);
        this.a(this.bN, "Noises", n, n5 + 13);
        this.a(this.bN, this.aT, n2 - 15, n5 + 13);
        this.bN.drawImage(this.bc, n - 7, n5 + 28, this);
        n5 += 20;
        this.a(this.bN, "Squelch", n, n5 + 22);
        this.a(this.bN, Integer.toString(this.bx), n2 - 15, n5 + 22);
        String s = (this.F == 0) ? "600 km" : "300 km";
        if (this.a7 == 1) {
            s = ((this.F == 0) ? "375 mi" : "188 mi");
        }
        this.a(this.bN, "Range", n, n5 + 33);
        this.a(this.bN, s, n2 - 15, n5 + 33);
        String s2 = "";
        int n6 = 0;
        if (this.J > 180 && this.J < 360) {
            s2 = "-";
        }
        if (this.J > 0 && this.J <= 180) {
            s2 = "+";
        }
        if (this.J != 0) {
            if (s2.compareTo("-") == 0) {
                n6 = Math.abs(360 - this.J);
            }
            else {
                n6 = this.J;
            }
        }
        this.a(this.bN, "Az.offset ", n, n5 + 44);
        this.a(this.bN, String.valueOf(s2) + Integer.toString(n6) + "$", n2 - 15, n5 + 44);
        this.a(n, n2, n5);
        int n7 = n + 2;
        int n8 = 455;
        this.bN.drawImage(this.aE, n - 7, n8 - 14, this);
        this.bN.drawImage(this.try, n - 7, n8 + 24, this);
        this.a(this.bN, "+CG  +IC  -CG  -IC", n - 1, n8 + 9);
        this.at = new SymbolGenerator(0, 0, 3);
        this.if(2);
        this.at.if(this.bN, n + 10, n8 + 1, this.bV);
        this.if(3);
        this.at.if(this.bN, n + 40, n8 + 1, this.az);
        this.if(0);
        this.at.if(this.bN, n + 70, n8 + 1, this.av);
        this.if(1);
        this.at.if(this.bN, n + 100, n8 + 1, this.void);
        n8 += 35;
        for (int l = 1; l < 6; ++l) {
            this.bN.setColor(this.a2[l]);
            this.bN.fillRect(n7, n8, 16, 8);
            n8 += 10;
        }
        n7 += 25;
        int n9 = 455;
        n9 += 25;
        if (this.ak == 59) {
            final Graphics bn = this.bN;
            final String s3 = "1-7 min";
            final int n10 = n7;
            n9 += 10;
            this.a(bn, s3, n10, n9);
            final Graphics bn2 = this.bN;
            final String s4 = "8-15 min";
            final int n11 = n7;
            n9 += 10;
            this.a(bn2, s4, n11, n9);
            final Graphics bn3 = this.bN;
            final String s5 = "16-30 min";
            final int n12 = n7;
            n9 += 10;
            this.a(bn3, s5, n12, n9);
            final Graphics bn4 = this.bN;
            final String s6 = "31-45 min";
            final int n13 = n7;
            n9 += 10;
            this.a(bn4, s6, n13, n9);
            final Graphics bn5 = this.bN;
            final String s7 = "45-60 min";
            final int n14 = n7;
            n9 += 10;
            this.a(bn5, s7, n14, n9);
        }
        else {
            final Graphics bn6 = this.bN;
            final String s8 = "1-5 min";
            final int n15 = n7;
            n9 += 10;
            this.a(bn6, s8, n15, n9);
            final Graphics bn7 = this.bN;
            final String s9 = "6-11 min";
            final int n16 = n7;
            n9 += 10;
            this.a(bn7, s9, n16, n9);
            final Graphics bn8 = this.bN;
            final String s10 = "12-17 min";
            final int n17 = n7;
            n9 += 10;
            this.a(bn8, s10, n17, n9);
            final Graphics bn9 = this.bN;
            final String s11 = "18-23 min";
            final int n18 = n7;
            n9 += 10;
            this.a(bn9, s11, n18, n9);
            final Graphics bn10 = this.bN;
            final String s12 = "24-30 min";
            final int n19 = n7;
            n9 += 10;
            this.a(bn10, s12, n19, n9);
        }
        this.else = 120;
        this.L = 22;
        this.aU = 34;
        this.bM = 72;
        this.byte = 380;
        this.bE = 500;
    }
    
    public void a(final int n, final int n2, final int n3) {
        if (!this.O) {
            this.a(this.bN, "Reload", n, n3 + 55);
        }
        else {
            this.a(this.bN, "Retrying", n, n3 + 55);
        }
        final int[] array = { n3 + 56, n3 + 59, n3 + 62 };
        final int[] array2 = { n2 - 20, n2 - 16, n2 - 20 };
        ++this.H;
        if (this.H < 12) {
            if ((this.aB - this.R) / 30 > 10) {
                this.bN.setColor(Color.green);
            }
            else {
                this.bN.setColor(Color.red);
            }
            if (this.O) {
                this.bN.setColor(Color.orange);
            }
            this.bN.fillPolygon(array2, array, 3);
        }
        else if (this.H >= 12 && this.H < 24) {
            this.bN.setColor(Color.black);
            this.bN.fillPolygon(array2, array, 3);
        }
        else {
            this.H = 0;
        }
        this.a(this.bN, Integer.toString((this.aB - this.R) / 30), n2 - 15, n3 + 55);
    }
    
    public void new(final Graphics graphics) {
        final int a = this.a(this.b1);
        final int n = (a - this.bj < 0) ? 0 : (a - this.bj);
        this.a(graphics, String.valueOf(this.a(n, false, this.a7 != 0)) + ((this.a7 == 0) ? " LT" : ""), 453, 22);
        this.a(graphics, n);
        this.l = 60 - (int)Math.round(((this.ak == 29) ? (this.bj * 2) : this.bj) / 60.0);
        if (this.l > 59) {
            this.l = 59;
        }
        if (this.l < 0) {
            this.l = 0;
        }
        if (this.bi == 50) {
            this.bj -= 10;
        }
        if (this.bj < 0 && this.bi-- < 1) {
            this.bj = ((this.ak == 59) ? 3500 : 1700);
            this.bi = 50;
        }
    }
    
    public void a(final Graphics graphics, final int n) {
        this.at = new SymbolGenerator(12, this.ab.getHeight(this) + 7, this.aS);
        int n2 = 0;
        final Enumeration<Hashtable<K, Integer>> elements = (Enumeration<Hashtable<K, Integer>>)this.n.elements();
        while (elements.hasMoreElements()) {
            ++n2;
            final Hashtable<K, Integer> hashtable = elements.nextElement();
            final int intValue = hashtable.get("time");
            if ((!this.aM || intValue <= n + 300) && (this.aM || this.ak != 29 || intValue >= n - 1800)) {
                double ay = hashtable.get("x");
                double ax = hashtable.get("y");
                if (this.F == 1) {
                    this.do(ay, ax);
                    this.bm *= 2.0;
                    this.if(this.ac, this.bm);
                    ay = this.ay;
                    ax = this.ax;
                }
                final int intValue2 = hashtable.get("charge");
                if (ax < 2.0 || ax > this.s[this.F].getHeight(this) - 5 || ay < 2.0 || ay > this.s[this.F].getWidth(this) - 5) {
                    continue;
                }
                this.a(intValue, intValue2);
                if (this.bN.getColor() == this.a2[0]) {
                    switch (intValue2) {
                        case 2: {
                            this.br = this.bV;
                            break;
                        }
                        case 0: {
                            this.br = this.av;
                            break;
                        }
                        case 3: {
                            this.br = this.az;
                            break;
                        }
                        case 1: {
                            this.br = this.void;
                            break;
                        }
                    }
                    this.at.a(this.aS);
                    this.at.if(this.bN, (int)Math.round(ay), (int)Math.round(ax), this.br);
                }
                else {
                    if (this.bN.getColor() == Color.black) {
                        continue;
                    }
                    this.at.a(this.aR);
                    this.at.if(this.bN, (int)Math.round(ay), (int)Math.round(ax), this.bq);
                }
            }
        }
        if (!this.aM) {
            this.a(graphics, "--:-- --", 453, 22);
        }
    }
    
    public void char() {
        this.bN.setFont(new Font("SansSerif", 1, 22));
        this.bN.setColor(new Color(16776960));
        this.bN.drawString("NO DATA AVAILABLE", 160, 285);
    }
    
    public void int(final Graphics graphics) {
        final int height = this.s[this.F].getHeight(this);
        final int width = this.s[this.F].getWidth(this);
        final int n = width / 2 + 12;
        final int n2 = this.ab.getHeight(this) + 7 + height / 2;
        final int n3 = this.ab.getHeight(this) + 7;
        this.bN.setColor(new Color(12632256));
        this.bN.drawRect(12, n3, width, height);
        if (this.C) {
            this.bN.setColor(new Color(10526880));
            this.bN.drawLine(12, n3, 12 + width, n3 + height);
            this.bN.drawLine(12 + width, n3, 12, n3 + height);
            this.bN.drawLine(n, n2, n, n3 + height);
            this.bN.drawLine(12, n2, 12 + width, n2);
        }
        this.bN.setFont(new Font("SansSerif", 1, 9));
        int n4 = 92;
        int n5 = 355;
        if (this.F == 1) {
            ++n4;
            --n5;
        }
        int n6 = 16;
        final int n7 = (this.F == 0) ? 6 : 3;
        final int[] array = new int[n7];
        int n8 = width - 8;
        for (int i = 0; i < n7; ++i) {
            this.bN.setColor(new Color(10526880));
            if (i == 0) {
                --n4;
                n5 += 2;
            }
            if (i == 2) {
                if (this.F == 0) {
                    ++n4;
                    n5 -= 2;
                }
                else {
                    n4 += 3;
                    n5 -= 7;
                }
            }
            if (i == 3) {
                ++n4;
                n5 -= 2;
            }
            if (i == 4) {
                n4 += 3;
                n5 -= 6;
            }
            if (i == 5) {
                n4 += 5;
                n5 -= 11;
            }
            this.bN.drawArc(n6, n6 + 26, n8, n8, n4, n5);
            if (i == 99 && this.F == 0) {
                n6 += 39;
                n8 -= 78;
            }
            else {
                n6 += ((this.F == 0) ? 41 : 82);
                n8 -= ((this.F == 0) ? 82 : 164);
            }
            if (i == 0) {
                ++n4;
                n5 -= 2;
            }
        }
        int n9 = 46;
        final int n10 = (this.F == 0) ? 700 : 400;
        this.bN.setColor(new Color(13421670));
        for (int j = 0; j < n7; ++j) {
            final String string = Integer.toString(n10 - (j + 1) * 100);
            if (string.length() < 3) {
                this.bN.drawString(string, n - 4, n9);
            }
            else {
                this.bN.drawString(string, n - 6, n9);
            }
            array[j] = n9;
            n9 += ((this.F == 0) ? 41 : 82);
        }
        if (this.C) {
            this.bN.setColor(new Color(10526880));
            if (this.F == 0) {
                this.bN.drawLine(n, 48, n, 78);
                this.bN.drawLine(n, 89, n, 118);
                this.bN.drawLine(n, 130, n, 159);
                this.bN.drawLine(n, 171, n, 200);
                this.bN.drawLine(n, 211, n, 241);
                this.bN.drawLine(n, 253, n, 282);
            }
            else {
                this.bN.drawLine(n, 48, n, 119);
                this.bN.drawLine(n, 131, n, 200);
                this.bN.drawLine(n, 212, n, 282);
            }
        }
        this.bN.setColor(Color.green);
        this.bN.drawLine(n - 4, n2, n + 4, n2);
        this.bN.drawLine(n, n2 - 4, n, n2 + 4);
        if (this.O) {
            this.char();
        }
    }
    
    public void do(final Graphics graphics) {
        final int height = this.s[this.F].getHeight(this);
        final int width = this.s[this.F].getWidth(this);
        final int n = width / 2 + 12;
        final int n2 = this.ab.getHeight(this) + 7 + height / 2;
        final int n3 = this.ab.getHeight(this) + 7;
        this.bN.setColor(new Color(12632256));
        this.bN.drawRect(12, n3, width, height);
        if (this.C) {
            this.bN.setColor(new Color(10526880));
            this.bN.drawLine(12, n3, 12 + width, n3 + height);
            this.bN.drawLine(12 + width, n3, 12, n3 + height);
            this.bN.drawLine(n, n2, n, n3 + height);
            this.bN.drawLine(12, n2, 12 + width, n2);
        }
        this.bN.setFont(new Font("SansSerif", 1, 9));
        int n4 = 92;
        int n5 = 355;
        int n6 = (this.F == 0) ? 15 : 20;
        final int n7 = (this.F == 0) ? 5 : 3;
        final int n8 = (this.F == 0) ? 6 : 18;
        final int[] array = new int[n7];
        int n9 = width - n8;
        for (int i = 0; i < n7; ++i) {
            this.bN.setColor(new Color(10526880));
            if (i == 0) {
                n4 += 0;
                ++n5;
            }
            if (i == 1) {
                --n4;
                ++n5;
            }
            if (i == 2) {
                ++n4;
                n5 -= 2;
            }
            if (i == 3) {
                n4 += 2;
                n5 -= 3;
            }
            if (i == 4) {
                n4 += 2;
                n5 -= 6;
            }
            this.bN.drawArc(n6, n6 + 26, n9, n9, n4, n5);
            n6 += ((this.F == 0) ? 49 : 80);
            n9 -= ((this.F == 0) ? 98 : 160);
            if (i == 0) {
                ++n4;
                n5 -= 2;
            }
        }
        int n10 = (this.F == 0) ? 46 : 50;
        final int n11 = (this.F == 0) ? 375 : 180;
        final int n12 = (this.F == 0) ? 75 : 60;
        this.bN.setColor(new Color(13421670));
        for (int j = 0, n13 = n11; j < n7; ++j, n13 -= n12) {
            final String string = Integer.toString(n13);
            if (string.length() < 3) {
                this.bN.drawString(string, n - 4, n10);
            }
            else {
                this.bN.drawString(string, n - 6, n10);
            }
            array[j] = n10;
            n10 += ((this.F == 0) ? 49 : 80);
        }
        if (this.C) {
            this.bN.setColor(new Color(10526880));
            if (this.F == 0) {
                this.bN.setColor(new Color(10526880));
                this.bN.drawLine(n, 48, n, 85);
                this.bN.drawLine(n, 97, n, 134);
                this.bN.drawLine(n, 147, n, 182);
                this.bN.drawLine(n, 195, n, 232);
                this.bN.drawLine(n, 244, n, 288);
            }
            else {
                this.bN.drawLine(n, 39, n, 41);
                this.bN.drawLine(n, 52, n, 119);
                this.bN.drawLine(n, 132, n, 200);
                this.bN.drawLine(n, 212, n, 288);
            }
        }
        this.bN.setColor(Color.green);
        this.bN.drawLine(n - 4, n2, n + 4, n2);
        this.bN.drawLine(n, n2 - 4, n, n2 + 4);
        if (this.O) {
            this.char();
        }
    }
    
    public String a(final int n, final boolean b, final boolean b2) {
        String s = "AM";
        final int n2 = n / 3600;
        final int n3 = (n - n2 * 3600) / 60;
        final int n4 = n - n2 * 3600 - n3 * 60;
        String s2 = Integer.toString(n2);
        if (n2 < 10) {
            s2 = "0" + s2;
        }
        String s3 = Integer.toString(n3);
        if (n3 < 10) {
            s3 = "0" + s3;
        }
        String s4 = Integer.toString(n4);
        if (n4 < 10) {
            s4 = "0" + s4;
        }
        String string;
        if (b2 && n2 >= 12) {
            string = ((n2 > 12) ? (String.valueOf(Integer.toString(n2 - 12)) + ":") : (String.valueOf(Integer.toString(n2)) + ":"));
            s = "PM";
        }
        else {
            string = String.valueOf(s2) + ":";
        }
        String s5 = String.valueOf(string) + s3;
        if (b) {
            s5 = String.valueOf(s5) + ":" + s4;
        }
        if (b2) {
            s5 = String.valueOf(s5) + " " + s;
        }
        return s5;
    }
    
    public String if() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }
    
    public int a(final String s) {
        int n = 0;
        try {
            final StringTokenizer stringTokenizer = new StringTokenizer(s.substring(s.indexOf(" ") + 1, s.length()), " :");
            final int[] array = { 3600, 60, 0 };
            int n2 = 0;
            while (stringTokenizer.hasMoreTokens()) {
                if (n2 >= 10) {
                    break;
                }
                n += Integer.parseInt(stringTokenizer.nextToken()) * array[n2];
                ++n2;
            }
        }
        catch (NumberFormatException ex) {
            return -1;
        }
        return n;
    }
    
    public int a(final int n) {
        final int[] array = { n & 0xFF, n >> 8 & 0xFF, n >> 16 & 0xFF, n >> 24 & 0xFF };
        return array[0] << 24 | array[1] << 16 | array[2] << 8 | array[3];
    }
    
    public int a(final DataInputStream dataInputStream, final int n) {
        boolean b = true;
        final byte[] array = new byte[n];
        try {
            dataInputStream.readFully(array);
        }
        catch (Exception ex) {
            b = false;
        }
        if (b) {
            return array[1] << 8 | (array[0] & 0xFF);
        }
        return -99999;
    }
    
    public void do() {
        try {
            if (this.getParameter("UNITS").toString().equalsIgnoreCase("us")) {
                this.a7 = 1;
            }
            else if (this.getParameter("UNITS").toString().equalsIgnoreCase("uk")) {
                this.a7 = 2;
            }
        }
        catch (Exception ex) {}
        try {
            this.ak = Integer.parseInt(this.getParameter("HISTORY")) - 1;
            if (this.ak != 29 && this.ak != 59) {
                this.ak = 59;
            }
        }
        catch (Exception ex2) {
            this.ak = 59;
        }
        try {
            if (this.getParameter("AZLINES").toString().equalsIgnoreCase("false")) {
                this.C = false;
            }
        }
        catch (Exception ex3) {}
        try {
            this.ar = this.getParameter("USEARC").toString();
        }
        catch (Exception ex4) {
            this.ar = "nexstorm_arc.dat";
        }
        try {
            if (this.getParameter("SHOW_DISCLAIMER").toString().equalsIgnoreCase("true")) {
                this.bv = true;
            }
            else {
                this.bv = false;
            }
        }
        catch (Exception ex5) {
            this.bv = true;
        }
        try {
            this.aS = Integer.parseInt(this.getParameter("NEWSTRIKE_SIZE"));
        }
        catch (Exception ex6) {
            this.aS = 5;
        }
        try {
            this.aR = Integer.parseInt(this.getParameter("OLDSTRIKE_SIZE"));
        }
        catch (Exception ex7) {
            this.aR = 1;
        }
        try {
            this.S[0] = this.getParameter("MAP_LR").toString();
            this.S[1] = this.getParameter("MAP_SR").toString();
        }
        catch (Exception ex8) {}
        try {
            this.aV = this.getParameter("TIMEZONE").toString();
            if (this.aV.length() > 3) {
                this.aV = "";
            }
        }
        catch (Exception ex9) {
            this.aV = "";
        }
        try {
            if (this.getParameter("RANGE").equalsIgnoreCase("FULL")) {
                this.F = 0;
            }
            else {
                this.F = 1;
            }
        }
        catch (Exception ex10) {
            this.F = 0;
        }
        try {
            this.aM = this.getParameter("STATE").equalsIgnoreCase("LOOP");
        }
        catch (Exception ex11) {
            this.aM = false;
        }
        try {
            this.a2[0] = new Color(Integer.decode(this.getParameter("COLOR1").toString()));
            this.ap = this.a2[0];
            this.a2[1] = new Color(Integer.decode(this.getParameter("COLOR2").toString()));
            this.a2[2] = new Color(Integer.decode(this.getParameter("COLOR3").toString()));
            this.a2[3] = new Color(Integer.decode(this.getParameter("COLOR4").toString()));
            this.a2[4] = new Color(Integer.decode(this.getParameter("COLOR5").toString()));
            this.a2[5] = new Color(Integer.decode(this.getParameter("COLOR6").toString()));
            this.a8[0] = new Color(Integer.decode(this.getParameter("CGP_COLOR").toString()));
            this.a8[1] = new Color(Integer.decode(this.getParameter("CGN_COLOR").toString()));
            this.a8[2] = new Color(Integer.decode(this.getParameter("ICP_COLOR").toString()));
            this.a8[3] = new Color(Integer.decode(this.getParameter("ICN_COLOR").toString()));
        }
        catch (Exception ex12) {}
        try {
            final String upperCase = this.getParameter("NEWSTRIKESYMBOL").toUpperCase();
            if (upperCase.compareTo("POINT") == 0) {
                this.br = 1;
            }
            else if (upperCase.compareTo("PIXEL") == 0) {
                this.br = 2;
            }
            else if (upperCase.compareTo("BLOB") == 0) {
                this.br = 3;
            }
            else if (upperCase.compareTo("CROSS") == 0) {
                this.br = 4;
            }
            else if (upperCase.compareTo("DCROSS") == 0) {
                this.br = 5;
            }
            else if (upperCase.compareTo("DASH") == 0) {
                this.br = 6;
            }
            else if (upperCase.compareTo("CIRCLE") == 0) {
                this.br = 7;
            }
            else if (upperCase.compareTo("DIAMOND") == 0) {
                this.br = 8;
            }
            else if (upperCase.compareTo("FDIAMOND") == 0) {
                this.br = 9;
            }
            else {
                this.br = 5;
            }
        }
        catch (Exception ex13) {
            this.br = 5;
        }
        try {
            final String upperCase2 = this.getParameter("CGP_SYMBOL").toUpperCase();
            if (upperCase2.compareTo("POINT") == 0) {
                this.bV = 1;
            }
            else if (upperCase2.compareTo("PIXEL") == 0) {
                this.bV = 2;
            }
            else if (upperCase2.compareTo("BLOB") == 0) {
                this.bV = 3;
            }
            else if (upperCase2.compareTo("CROSS") == 0) {
                this.bV = 4;
            }
            else if (upperCase2.compareTo("DCROSS") == 0) {
                this.bV = 5;
            }
            else if (upperCase2.compareTo("DASH") == 0) {
                this.bV = 6;
            }
            else if (upperCase2.compareTo("CIRCLE") == 0) {
                this.bV = 7;
            }
            else if (upperCase2.compareTo("DIAMOND") == 0) {
                this.bV = 8;
            }
            else if (upperCase2.compareTo("FDIAMOND") == 0) {
                this.bV = 9;
            }
            else if (upperCase2.compareTo("UTRIANGLE") == 0) {
                this.bV = 10;
            }
            else if (upperCase2.compareTo("FUTRIANGLE") == 0) {
                this.bV = 11;
            }
            else if (upperCase2.compareTo("DTRIANGLE") == 0) {
                this.bV = 12;
            }
            else if (upperCase2.compareTo("FDTRIANGLE") == 0) {
                this.bV = 13;
            }
            else if (upperCase2.compareTo("SQUARE") == 0) {
                this.bV = 14;
            }
            else if (upperCase2.compareTo("FSQUARE") == 0) {
                this.bV = 15;
            }
            else {
                this.bV = 5;
            }
        }
        catch (Exception ex14) {
            this.bV = 5;
        }
        try {
            final String upperCase3 = this.getParameter("CGN_SYMBOL").toUpperCase();
            if (upperCase3.compareTo("POINT") == 0) {
                this.av = 1;
            }
            else if (upperCase3.compareTo("PIXEL") == 0) {
                this.av = 2;
            }
            else if (upperCase3.compareTo("BLOB") == 0) {
                this.av = 3;
            }
            else if (upperCase3.compareTo("CROSS") == 0) {
                this.av = 4;
            }
            else if (upperCase3.compareTo("DCROSS") == 0) {
                this.av = 5;
            }
            else if (upperCase3.compareTo("DASH") == 0) {
                this.av = 6;
            }
            else if (upperCase3.compareTo("CIRCLE") == 0) {
                this.av = 7;
            }
            else if (upperCase3.compareTo("DIAMOND") == 0) {
                this.av = 8;
            }
            else if (upperCase3.compareTo("FDIAMOND") == 0) {
                this.av = 9;
            }
            else if (upperCase3.compareTo("UTRIANGLE") == 0) {
                this.av = 10;
            }
            else if (upperCase3.compareTo("FUTRIANGLE") == 0) {
                this.av = 11;
            }
            else if (upperCase3.compareTo("DTRIANGLE") == 0) {
                this.av = 12;
            }
            else if (upperCase3.compareTo("FDTRIANGLE") == 0) {
                this.av = 13;
            }
            else if (upperCase3.compareTo("SQUARE") == 0) {
                this.av = 14;
            }
            else if (upperCase3.compareTo("FSQUARE") == 0) {
                this.av = 15;
            }
            else {
                this.av = 5;
            }
        }
        catch (Exception ex15) {
            this.av = 5;
        }
        try {
            final String upperCase4 = this.getParameter("ICP_SYMBOL").toUpperCase();
            if (upperCase4.compareTo("POINT") == 0) {
                this.az = 1;
            }
            else if (upperCase4.compareTo("PIXEL") == 0) {
                this.az = 2;
            }
            else if (upperCase4.compareTo("BLOB") == 0) {
                this.az = 3;
            }
            else if (upperCase4.compareTo("CROSS") == 0) {
                this.az = 4;
            }
            else if (upperCase4.compareTo("DCROSS") == 0) {
                this.az = 5;
            }
            else if (upperCase4.compareTo("DASH") == 0) {
                this.az = 6;
            }
            else if (upperCase4.compareTo("CIRCLE") == 0) {
                this.az = 7;
            }
            else if (upperCase4.compareTo("DIAMOND") == 0) {
                this.az = 8;
            }
            else if (upperCase4.compareTo("FDIAMOND") == 0) {
                this.az = 9;
            }
            else if (upperCase4.compareTo("UTRIANGLE") == 0) {
                this.az = 10;
            }
            else if (upperCase4.compareTo("FUTRIANGLE") == 0) {
                this.az = 11;
            }
            else if (upperCase4.compareTo("DTRIANGLE") == 0) {
                this.az = 12;
            }
            else if (upperCase4.compareTo("FDTRIANGLE") == 0) {
                this.az = 13;
            }
            else if (upperCase4.compareTo("SQUARE") == 0) {
                this.az = 14;
            }
            else if (upperCase4.compareTo("FSQUARE") == 0) {
                this.az = 15;
            }
            else {
                this.az = 5;
            }
        }
        catch (Exception ex16) {
            this.az = 5;
        }
        try {
            final String upperCase5 = this.getParameter("ICN_SYMBOL").toUpperCase();
            if (upperCase5.compareTo("POINT") == 0) {
                this.void = 1;
            }
            else if (upperCase5.compareTo("PIXEL") == 0) {
                this.void = 2;
            }
            else if (upperCase5.compareTo("BLOB") == 0) {
                this.void = 3;
            }
            else if (upperCase5.compareTo("CROSS") == 0) {
                this.void = 4;
            }
            else if (upperCase5.compareTo("DCROSS") == 0) {
                this.void = 5;
            }
            else if (upperCase5.compareTo("DASH") == 0) {
                this.void = 6;
            }
            else if (upperCase5.compareTo("CIRCLE") == 0) {
                this.void = 7;
            }
            else if (upperCase5.compareTo("DIAMOND") == 0) {
                this.void = 8;
            }
            else if (upperCase5.compareTo("FDIAMOND") == 0) {
                this.void = 9;
            }
            else if (upperCase5.compareTo("UTRIANGLE") == 0) {
                this.void = 10;
            }
            else if (upperCase5.compareTo("FUTRIANGLE") == 0) {
                this.void = 11;
            }
            else if (upperCase5.compareTo("DTRIANGLE") == 0) {
                this.void = 12;
            }
            else if (upperCase5.compareTo("FDTRIANGLE") == 0) {
                this.void = 13;
            }
            else if (upperCase5.compareTo("SQUARE") == 0) {
                this.void = 14;
            }
            else if (upperCase5.compareTo("FSQUARE") == 0) {
                this.void = 15;
            }
            else {
                this.void = 5;
            }
        }
        catch (Exception ex17) {
            this.void = 5;
        }
        try {
            final String upperCase6 = this.getParameter("OLDSTRIKESYMBOL").toUpperCase();
            if (upperCase6.compareTo("POINT") == 0) {
                this.bq = 1;
                return;
            }
            if (upperCase6.compareTo("PIXEL") == 0) {
                this.bq = 2;
                return;
            }
            if (upperCase6.compareTo("BLOB") == 0) {
                this.bq = 3;
                return;
            }
            if (upperCase6.compareTo("CROSS") == 0) {
                this.bq = 4;
                return;
            }
            if (upperCase6.compareTo("DCROSS") == 0) {
                this.bq = 5;
                return;
            }
            if (upperCase6.compareTo("DASH") == 0) {
                this.bq = 6;
                return;
            }
            if (upperCase6.compareTo("CIRCLE") == 0) {
                this.bq = 7;
                return;
            }
            if (upperCase6.compareTo("DIAMOND") == 0) {
                this.bq = 8;
                return;
            }
            if (upperCase6.compareTo("FDIAMOND") == 0) {
                this.bq = 9;
                return;
            }
            if (upperCase6.compareTo("UTRIANGLE") == 0) {
                this.bq = 10;
                return;
            }
            if (upperCase6.compareTo("FUTRIANGLE") == 0) {
                this.bq = 11;
                return;
            }
            if (upperCase6.compareTo("DTRIANGLE") == 0) {
                this.bq = 12;
                return;
            }
            if (upperCase6.compareTo("FDTRIANGLE") == 0) {
                this.bq = 13;
                return;
            }
            if (upperCase6.compareTo("SQUARE") == 0) {
                this.bq = 14;
                return;
            }
            if (upperCase6.compareTo("FSQUARE") == 0) {
                this.bq = 15;
                return;
            }
            this.bq = 2;
        }
        catch (Exception ex18) {
            this.bq = 2;
        }
    }
    
    public Vector a(double n, double n2) {
        final Vector<Double> vector = new Vector<Double>();
        if (this.J == 0 || (n == 0.0 && n2 == 0.0)) {
            n = n / this.I - this.bS;
            n2 = n2 / this.I - this.bS;
            vector.addElement(new Double(n));
            vector.addElement(new Double(n2));
            return vector;
        }
        this.do(n, n2);
        this.ac += this.J;
        if (this.ac > 360.0) {
            this.ac -= 360.0;
        }
        if (this.ac < 0.0) {
            this.ac += 360.0;
        }
        this.if(this.ac, this.bm);
        n = this.ay / this.I - this.bS;
        n2 = this.ax / this.I - this.bS;
        vector.addElement(new Double(n));
        vector.addElement(new Double(n2));
        return vector;
    }
    
    public void if(final double n, final double n2) {
        final double n3 = this.I * 1000.0 / 2.0;
        this.ay = n3 + n2 * Math.sin(n * 0.017453292519943295);
        this.ax = n3 - n2 * Math.cos(n * 0.017453292519943295);
    }
    
    public void do(double n, double n2) {
        final double n3 = this.I * 1000.0 / 2.0;
        if (n < n3) {
            n += -n3;
        }
        else {
            n -= n3;
        }
        if (n2 < n3) {
            n2 += -n3;
        }
        else {
            n2 -= n3;
        }
        this.bm = Math.sqrt(n * n + n2 * n2);
        final double abs = Math.abs(n);
        final double abs2 = Math.abs(n2);
        double ac;
        if (abs2 != 0.0) {
            ac = Math.atan(abs / abs2) * 57.29577951308232;
        }
        else {
            ac = 90.0;
        }
        if (n != 0.0 && n2 != 0.0) {
            if (n > 0.0 && n2 > 0.0) {
                this.ac = 180.0 - ac;
            }
            if (n > 0.0 && n2 < 0.0) {
                this.ac = ac;
            }
            if (n < 0.0 && n2 < 0.0) {
                this.ac = 360.0 - ac;
            }
            if (n < 0.0 && n2 > 0.0) {
                this.ac = 180.0 + ac;
            }
        }
        else {
            if (n < 0.0 && n2 == 0.0) {
                this.ac = 270.0;
            }
            if (n > 0.0 && n2 == 0.0) {
                this.ac = 90.0;
            }
            if (n == 0.0 && n2 < 0.0) {
                this.ac = 360.0;
            }
            if (n == 0.0 && n2 > 0.0) {
                this.ac = 180.0;
            }
        }
    }
    
    public void a(final int n, final int n2, final int width, final int height) {
        final int[] bb = this.bb;
        this.bb = new int[width * height];
        for (int i = n2; i < n2 + height; ++i) {
            System.arraycopy(bb, n + i * this.width, this.bb, i * width, width);
        }
        this.width = width;
        this.height = height;
    }
    
    public Image case() {
        final Image image = this.createImage(new MemoryImageSource(this.width, this.height, this.bb, 0, this.width));
        System.arraycopy(this.bb, 0, this.bb = new int[this.width * this.height], 0, this.width * this.height);
        return image;
    }
    
    void int() {
        this.new = this.width;
        this.bt = this.height;
        this.aW = new int[this.width * this.height];
        System.arraycopy(this.bb, 0, this.aW, 0, this.width * this.height);
    }
    
    void try() {
        if (this.aW != null) {
            this.width = this.new;
            this.height = this.bt;
            this.bb = new int[this.width * this.height];
            System.arraycopy(this.aW, 0, this.bb, 0, this.width * this.height);
        }
    }
    
    public void a(final String s, final URL url) {
        final int n = 0;
        int a = -1;
        this.n = new Vector();
        this.byte();
        try {
            final DataInputStream dataInputStream = new DataInputStream(new URL(url, s).openStream());
            this.P = Integer.toString(this.a(dataInputStream, 4));
            final int a2 = this.a(dataInputStream, 4);
            if (a2 < 10) {
                this.P = String.valueOf(this.P) + "-0";
            }
            else {
                this.P = String.valueOf(this.P) + "-";
            }
            this.P = String.valueOf(this.P) + Integer.toString(a2);
            final int a3 = this.a(dataInputStream, 4);
            if (a3 < 10) {
                this.P = String.valueOf(this.P) + "-0";
            }
            else {
                this.P = String.valueOf(this.P) + "-";
            }
            this.P = String.valueOf(this.P) + Integer.toString(a3);
            this.V = false;
            if (this.if().equals(this.P)) {
                this.V = true;
            }
            this.P = String.valueOf(this.P) + " ";
            final int a4 = this.a(dataInputStream, 4);
            if (a4 < 10) {
                this.P = String.valueOf(this.P) + "0";
            }
            this.P = String.valueOf(this.P) + Integer.toString(a4) + ":";
            final int a5 = this.a(dataInputStream, 4);
            if (a5 < 10) {
                this.P = String.valueOf(this.P) + "0";
            }
            this.P = String.valueOf(this.P) + Integer.toString(a5) + ":";
            final int a6 = this.a(dataInputStream, 4);
            if (a6 < 10) {
                this.P = String.valueOf(this.P) + "0";
            }
            this.P = String.valueOf(this.P) + Integer.toString(a6);
            this.b1 = this.P;
            if (this.a7 == 1 || this.a7 == 2) {
                final int[] array = new int[6];
                int n2 = 0;
                final StringTokenizer stringTokenizer = new StringTokenizer(this.P, " -:");
                while (stringTokenizer.hasMoreTokens()) {
                    array[n2] = Integer.parseInt(stringTokenizer.nextToken());
                    ++n2;
                }
                String s2 = "AM";
                if (array[3] >= 12) {
                    s2 = "PM";
                    if (array[3] > 12) {
                        final int[] array2 = array;
                        final int n3 = 3;
                        array2[n3] -= 12;
                    }
                }
                if (this.a7 == 1) {
                    this.P = String.valueOf(Integer.toString(array[1])) + "/";
                    this.P = String.valueOf(this.P) + Integer.toString(array[2]) + "/";
                    this.P = String.valueOf(this.P) + Integer.toString(array[0]) + " ";
                }
                else {
                    this.P = String.valueOf(Integer.toString(array[2])) + "/";
                    this.P = String.valueOf(this.P) + Integer.toString(array[1]) + "/";
                    this.P = String.valueOf(this.P) + Integer.toString(array[0]) + " ";
                }
                if (this.a7 == 1) {
                    this.P = String.valueOf(this.P) + Integer.toString(array[3]) + ":";
                    if (array[4] < 10) {
                        this.P = String.valueOf(this.P) + "0" + Integer.toString(array[4]) + ":";
                    }
                    else {
                        this.P = String.valueOf(this.P) + Integer.toString(array[4]) + ":";
                    }
                    if (array[5] < 10) {
                        this.P = String.valueOf(this.P) + "0" + Integer.toString(array[5]) + " ";
                    }
                    else {
                        this.P = String.valueOf(this.P) + Integer.toString(array[5]) + " ";
                    }
                    this.P = String.valueOf(this.P) + s2;
                }
                else {
                    this.P = String.valueOf(this.P) + this.b1.substring(this.b1.indexOf(" ") + 1, this.b1.length());
                }
            }
            int a7 = this.a(dataInputStream, 4);
            if (a7 < 0) {
                a7 += 65535;
            }
            this.bB = Integer.toString(a7);
            int a8 = this.a(dataInputStream, 4);
            if (a8 < 0) {
                a8 += 65535;
            }
            this.aT = Integer.toString(a8);
            this.K = Integer.toString(this.a(dataInputStream, 4) * 60);
            this.p = this.a(dataInputStream, 4);
            this.bh = this.a(dataInputStream, 4);
            this.bx = this.a(dataInputStream, 4);
            this.J = this.a(dataInputStream, 4);
            if (this.J > 359) {
                this.J = 0;
            }
            this.f = "";
            for (int i = 0; i < 32; ++i) {
                final char c = (char)dataInputStream.readByte();
                if (!Character.isISOControl(c)) {
                    this.f = String.valueOf(this.f) + (char)new Character(c);
                }
            }
            a = this.a(this.b1);
            if (a >= 0) {
                this.char = a;
                for (int j = 0; j < 60; ++j) {
                    this.E[j] = 0;
                    this.bH[j] = -1;
                }
                final boolean b = false;
                this.aN = (b ? 1 : 0);
                this.W = (b ? 1 : 0);
                this.bD = 0;
                int k = 0;
                int n4 = -1;
                int int1 = 0;
                while (k != -99999) {
                    try {
                        int1 = dataInputStream.readInt();
                    }
                    catch (Exception ex) {
                        k = -99999;
                    }
                    if (k == -99999) {
                        break;
                    }
                    k = this.a(int1);
                    if (k < 0) {
                        k = 65535 - k;
                    }
                    if (n4 < 0) {
                        n4 = k;
                    }
                    if (n4 <= k - 60) {
                        n4 = k;
                    }
                    int n5 = 60 - ((int)this.char - k) / 60;
                    if (n5 > 59) {
                        n5 = 59;
                    }
                    if (n5 < 0) {
                        n5 = 0;
                    }
                    final double n6 = this.a(dataInputStream, 4) * this.I;
                    final double n7 = this.a(dataInputStream, 4) * this.I;
                    final int a9 = this.a(dataInputStream, 4);
                    if (k >= a - 3600) {
                        if (a9 == 0 || a9 == 2) {
                            if (a9 == 0) {
                                final int[] aq = this.aq;
                                final int n8 = n5;
                                ++aq[n8];
                            }
                            else {
                                final int[] br = this.bR;
                                final int n9 = n5;
                                ++br[n9];
                            }
                        }
                        else if (a9 == 1 || a9 == 3) {
                            if (a9 == 1) {
                                final int[] d = this.D;
                                final int n10 = n5;
                                ++d[n10];
                            }
                            else {
                                final int[] a10 = this.a4;
                                final int n11 = n5;
                                ++a10[n11];
                            }
                        }
                    }
                    final Vector a11 = this.a(n6, n7);
                    final Hashtable<String, Double> hashtable = new Hashtable<String, Double>();
                    hashtable.put("time", (Double)new Integer(k));
                    hashtable.put("x", a11.firstElement());
                    hashtable.put("y", a11.lastElement());
                    hashtable.put("charge", (Double)new Integer(a9));
                    this.n.addElement(hashtable);
                }
            }
        }
        catch (Exception ex2) {}
        for (int l = 0; l < 60; ++l) {
            this.W += this.bR[l] + this.aq[l] + this.a4[l] + this.D[l];
            this.d += this.a4[l] + this.D[l];
            this.aK += this.bR[l] + this.aq[l];
            this.bJ += this.bR[l];
            this.bK += this.aq[l];
            this.i += this.a4[l];
            this.k += this.D[l];
            if (l > 29) {
                this.aN += this.bR[l] + this.aq[l] + this.a4[l] + this.D[l];
                this.a9 += this.a4[l] + this.D[l];
                this.Z += this.bR[l] + this.aq[l];
                this.ao += this.bR[l];
                this.as += this.aq[l];
                this.bO += this.a4[l];
                this.bU += this.D[l];
            }
        }
        boolean b2 = false;
        if (a >= 0) {
            try {
                this.for = n - 1;
                if (this.for < 0) {
                    this.for = 0;
                }
                this.bH[59] = a;
                this.bH[0] = a - 3540;
                if (this.bH[0] < 0) {
                    this.bH[0] = 0;
                }
                for (int n12 = 0; n12 < 60; ++n12) {
                    this.E[n12] = 0;
                }
                this.bD = 0;
                for (int n13 = 0; n13 < 59; ++n13) {
                    this.bH[n13 + 1] = this.bH[n13] + 60;
                }
                this.n.size();
                for (int n14 = 0; n14 < 60; ++n14) {
                    final Enumeration<Hashtable<K, Integer>> elements = (Enumeration<Hashtable<K, Integer>>)this.n.elements();
                    while (elements.hasMoreElements()) {
                        final int intValue = elements.nextElement().get("time");
                        if (n14 < 59 && intValue >= this.bH[n14] && intValue < this.bH[n14 + 1]) {
                            final int[] e = this.E;
                            final int n15 = n14;
                            ++e[n15];
                        }
                        else {
                            if (n14 != 59 || intValue < this.bH[59]) {
                                continue;
                            }
                            final int[] e2 = this.E;
                            final int n16 = 59;
                            ++e2[n16];
                        }
                    }
                }
                this.bD = this.E[58];
                this.aB = 1800;
                this.O = false;
            }
            catch (Exception ex3) {
                b2 = true;
            }
        }
        if (a < 0 || b2) {
            this.aB = 320;
            this.O = true;
            this.for = 0;
            this.P = "2000-01-01 00:00:00";
            this.b1 = this.P;
            this.bB = "0";
            this.aT = "0";
            this.K = "0";
            final boolean b3 = false;
            this.Z = (b3 ? 1 : 0);
            this.aK = (b3 ? 1 : 0);
            final boolean b4 = false;
            this.a9 = (b4 ? 1 : 0);
            this.d = (b4 ? 1 : 0);
            final boolean b5 = false;
            this.ao = (b5 ? 1 : 0);
            this.bJ = (b5 ? 1 : 0);
            final boolean b6 = false;
            this.as = (b6 ? 1 : 0);
            this.bK = (b6 ? 1 : 0);
            final boolean b7 = false;
            this.bO = (b7 ? 1 : 0);
            this.i = (b7 ? 1 : 0);
            final boolean b8 = false;
            this.bU = (b8 ? 1 : 0);
            this.k = (b8 ? 1 : 0);
            this.byte();
            this.bx = 0;
            this.J = 0;
        }
        this.for();
    }
    
    public void byte() {
        for (int i = 0; i < 60; ++i) {
            this.bR[i] = 0;
            this.aq[i] = 0;
            this.a4[i] = 0;
            this.D[i] = 0;
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        if (y >= 22 && y <= 30) {
            if (x >= 212 && x <= 241) {
                this.ak = 29;
                this.bj = 1700;
                this.bi = 50;
            }
            else if (x >= 247 && x <= 276) {
                this.ak = 59;
                this.bj = 3500;
                this.bi = 50;
            }
            else if (x >= 293 && x <= 322) {
                this.F = 0;
            }
            else if (x >= 326 && x <= 356) {
                this.F = 1;
            }
            else if (x >= 375 && x <= 402) {
                this.aM = false;
                this.aA = 1;
            }
            else if (x >= 406 && x <= 436) {
                this.aA = 0;
                this.bj = ((this.ak == 59) ? 3500 : 1700);
                this.bi = 50;
                this.aM = true;
            }
        }
        this.a5 = true;
        this.repaint();
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        if (y >= 22 && y <= 30) {
            if ((x >= 212 && x <= 241) || (x >= 247 && x <= 276) || (x >= 293 && x <= 322) || (x >= 326 && x <= 356) || (x >= 375 && x <= 402) || (x >= 406 && x <= 436)) {
                this.setCursor(new Cursor(12));
                return;
            }
            if (this.getCursor() != this.bk) {
                this.setCursor(this.bk);
            }
        }
        else if (this.getCursor() != this.bk) {
            this.setCursor(this.bk);
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    private void for() {
        while (this.n.size() > 2000) {
            boolean b = false;
            final Vector vector = new Vector(this.n.size());
            final Vector vector2 = (Vector)this.n.clone();
            this.n.removeAllElements();
            final Enumeration<Object> elements = vector2.elements();
            while (elements.hasMoreElements()) {
                if (b) {
                    elements.nextElement();
                }
                else {
                    this.n.addElement(elements.nextElement());
                }
                b = !b;
            }
            this.n.trimToSize();
        }
    }
    
    public StormVue() {
        this.aj = 1;
        this.T = -120;
        this.a5 = true;
        this.ar = "";
        this.case = "";
        this.bf = 7680;
        this.null = 15000;
        this.s = new Image[2];
        this.h = true;
        this.bu = true;
        this.aO = "";
        this.P = "2000-01-01 00:00:00";
        this.b1 = this.P;
        this.bB = "0";
        this.aT = "0";
        this.K = "0";
        this.aV = "";
        this.aZ = this.b1;
        this.E = new int[60];
        this.bH = new int[60];
        this.bR = new int[61];
        this.aq = new int[61];
        this.a4 = new int[61];
        this.D = new int[61];
        this.e = "nexstorm_arc.dat";
        this.I = 0.5;
        this.bL = 500.0;
        this.bS = this.bL / 2.0;
        this.a7 = 0;
        this.ak = 59;
        this.aM = false;
        this.C = true;
        this.S = new String[2];
        this.a2 = new Color[6];
        this.a8 = new Color[4];
        this.bQ = new Color(12632256);
        this.bn = new Color(9560063);
        this.Y = new Color(16764785);
        this.long = false;
        this.bk = new Cursor(0);
    }
}
