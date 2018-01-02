import java.awt.Event;
import java.io.InputStream;
import java.awt.Component;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.net.MalformedURLException;
import java.awt.image.ImageProducer;
import java.awt.image.ColorModel;
import java.awt.image.DirectColorModel;
import java.awt.Container;
import java.awt.image.PixelGrabber;
import java.awt.LayoutManager;
import java.awt.MediaTracker;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.MemoryImageSource;
import java.awt.image.ImageObserver;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class mosaic extends Applet implements Runnable, ImageObserver
{
    anfy a;
    MemoryImageSource b;
    boolean c;
    Toolkit d;
    int e;
    long f;
    Thread g;
    Lware h;
    int i;
    int j;
    int k;
    private Image l;
    int m;
    int n;
    String[] o;
    String[] p;
    boolean q;
    Frame r;
    int s;
    int t;
    int u;
    int v;
    int w;
    int x;
    int[] y;
    int[] z;
    private Image A;
    private Image[] B;
    private Graphics C;
    String D;
    String E;
    String F;
    String G;
    String H;
    String I;
    String J;
    String K;
    String L;
    String M;
    String N;
    private Image O;
    private Graphics P;
    int Q;
    int R;
    int S;
    int T;
    Color U;
    int V;
    String[] W;
    int X;
    boolean Y;
    int Z;
    int ba;
    int bb;
    int bc;
    int bd;
    boolean be;
    final String bf = "w/.9\fB\u007f<,Ip><<\u0006\u0016\u001c7 \nU6~}\u001eA(p4\u0007P";
    boolean bg;
    URL bh;
    boolean bi;
    String bj;
    int[][] bk;
    MediaTracker bl;
    boolean bm;
    int[] bn;
    int[] bo;
    boolean[] bp;
    int bq;
    boolean br;
    int bs;
    protected int bt;
    protected int bu;
    protected int bv;
    protected int[] bw;
    protected int bx;
    protected double[] by;
    protected double[] bz;
    protected int[] bA;
    protected int bB;
    protected int bC;
    protected double[] bD;
    protected int[] bE;
    protected int[] bF;
    protected double[] bG;
    protected int[] bH;
    protected int[] bI;
    protected int[] bJ;
    protected int[] bK;
    protected int bL;
    protected int bM;
    protected int bN;
    protected int bO;
    protected double[] bP;
    protected boolean[] bQ;
    protected double[] bR;
    protected final int bS = 4;
    protected final int bT = 2;
    protected final int bU = 2;
    protected final int bV = 4;
    protected final int bW = 4;
    protected double bX;
    protected double bY;
    protected double bZ;
    protected int[] ca;
    public static int cb;
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if (image == this.l) {
            if (n == 16) {
                this.Y = true;
            }
            return true;
        }
        return true;
    }
    
    public void destroy() {
        if (this.h != null) {
            this.h.hide();
        }
        this.h = null;
        if (this.l != null) {
            this.l.flush();
        }
        this.l = null;
        if (this.O != null) {
            this.O.flush();
        }
        this.O = null;
        if (this.P != null) {
            this.P.dispose();
        }
        this.P = null;
        System.gc();
    }
    
    public synchronized void prepaniframe() {
        if (this.be) {
            this.notifyAll();
            while (!this.Y) {
                Thread.yield();
                try {
                    Thread.sleep(8L);
                }
                catch (InterruptedException ex) {}
            }
            this.Y = false;
        }
        this.P.drawImage(this.l, this.m, this.n, this);
    }
    
    public synchronized boolean CheckAniGIF() {
        this.prepareImage(this.l, this);
        if (this.c) {
            for (int i = 0; i < 3; ++i) {
                this.notifyAll();
                Thread.yield();
                try {
                    Thread.sleep(100L);
                }
                catch (InterruptedException ex) {}
            }
            return this.Y;
        }
        return false;
    }
    
    private final void a() {
        while (true) {
            this.showStatus(c("r00r\u001d\u0016-;8\u0006@:~\"\u001eAq?;\u000fO+;4\u0004\u0018<18IU-;1\u0000B,~9\u0000X:~<\u0007\u0016\u0017\n\u0018%\u0017"));
            try {
                Thread.sleep(500L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    byte a(final int n, final int n2, final int n3, final int n4) {
        final int n5 = n - n2;
        if (n5 >= n3) {
            return (byte)n5;
        }
        return (byte)(n4 - (n3 - n5 - 1));
    }
    
    public void init() {
        final int i = mosaic.cb;
        this.setLayout(null);
        this.addNotify();
        this.d = this.getToolkit();
        final String parameter;
        final String s = parameter = this.getParameter(c("U-;1\u0000B,"));
        String c = null;
        Label_0104: {
            Label_0073: {
                Label_0069: {
                    if (i == 0) {
                        if (parameter == null) {
                            break Label_0069;
                        }
                        final String s2;
                        final String protocol = s2 = s;
                        if (i != 0) {
                            break Label_0104;
                        }
                    }
                    if (parameter.startsWith(c("w/.9\fB\u007f<,Ip><<\u0006\u0016\u001c7 \nU6~}\u001eA(p4\u0007P"))) {
                        break Label_0073;
                    }
                    this.a();
                    if (i == 0) {
                        break Label_0073;
                    }
                }
                this.a();
            }
            (this.h = new Lware(this, c("{0-4\u0000U\u007f?%\u0019Z:*"))).hide();
            try {
                final String protocol = this.getDocumentBase().getProtocol();
                c = protocol;
            }
            catch (SecurityException ex) {
                c = c("P620");
            }
        }
        String s3;
        try {
            s3 = this.getDocumentBase().getHost();
        }
        catch (SecurityException ex2) {
            s3 = "";
        }
        s3.toLowerCase();
        c.toLowerCase();
        final String parameter2;
        Label_0634: {
            Label_0625: {
                if (i == 0) {
                    Label_0204: {
                        if (!c.equals(c("P620"))) {
                            int n3;
                            int startsWith;
                            int n2;
                            final int n = n2 = (startsWith = (n3 = s3.length()));
                            if (i == 0) {
                                if (n < 1) {
                                    break Label_0204;
                                }
                                final int n4;
                                n2 = (n4 = (startsWith = (n3 = (s3.startsWith(c("Z0=4\u0005")) ? 1 : 0))));
                            }
                            if (i == 0) {
                                if (n != 0) {
                                    break Label_0204;
                                }
                                startsWith = (n2 = (n3 = (s3.equals(c("\u0007mi{Y\u0018opd")) ? 1 : 0)));
                            }
                            if (i == 0) {
                                if (n2 != 0) {
                                    break Label_0204;
                                }
                                n3 = (startsWith = (s3.startsWith(c("A(){")) ? 1 : 0));
                            }
                            if (i == 0) {
                                if (startsWith != 0) {
                                    s3 = s3.substring(4);
                                }
                                n3 = s3.length();
                            }
                            final int n6;
                            final int n5 = n6 = n3;
                            if (i != 0 || n6 > 0) {
                                final char[] array = new char[n6];
                                s3.getChars(0, n5, array, 0);
                                int n7 = 0;
                                while (true) {
                                    while (true) {
                                        Label_0305: {
                                            if (i == 0) {
                                                break Label_0305;
                                            }
                                            final char[] array2 = array;
                                            final int n8 = n7;
                                            if (i != 0 || array2[n8] == '0') {
                                                array2[n8] = '1';
                                            }
                                            n7 += 5;
                                        }
                                        if (n7 < n5) {
                                            continue;
                                        }
                                        break;
                                    }
                                    if (i != 0) {
                                        continue;
                                    }
                                    break;
                                }
                                s3 = new String(array);
                            }
                            final String s4 = parameter2 = this.getParameter(c("D:96\u0006R:"));
                            if (i != 0) {
                                break Label_0634;
                            }
                            if (parameter2 == null) {
                                break Label_0625;
                            }
                            final String s5 = s4;
                            if (i != 0) {
                                break Label_0634;
                            }
                            if (s5.length() <= 5) {
                                break Label_0625;
                            }
                            s4.toLowerCase();
                            int n9 = 1;
                            try {
                                int n10 = 0;
                                while (true) {
                                    while (true) {
                                        Label_0400: {
                                            if (i == 0) {
                                                break Label_0400;
                                            }
                                            if (s4.charAt(n10) == '+') {
                                                ++n9;
                                            }
                                            ++n10;
                                        }
                                        if (n10 < s4.length()) {
                                            continue;
                                        }
                                        break;
                                    }
                                    if (i != 0) {
                                        continue;
                                    }
                                    break;
                                }
                            }
                            catch (StringIndexOutOfBoundsException ex3) {}
                            final int[] array3 = new int[n9];
                            final int n11 = n9;
                            if (i == 0 && n11 == 1) {
                                array3[0] = s4.length();
                                if (i != 0) {
                                    goto Label_0450;
                                }
                            }
                            else {
                                int n12 = n11;
                                try {
                                    int n13 = 0;
                                    while (true) {
                                        while (true) {
                                            Label_0486: {
                                                if (i == 0) {
                                                    break Label_0486;
                                                }
                                                if (s4.charAt(n13) == '+') {
                                                    array3[n12] = n13;
                                                    ++n12;
                                                }
                                                ++n13;
                                            }
                                            if (n13 < s4.length()) {
                                                continue;
                                            }
                                            break;
                                        }
                                        if (i != 0) {
                                            continue;
                                        }
                                        break;
                                    }
                                }
                                catch (StringIndexOutOfBoundsException ex4) {}
                                array3[n12] = s4.length();
                            }
                            final String[] array4 = new String[n9];
                            int n14 = 0;
                            int n15 = 0;
                            int n16;
                            while (true) {
                                while (true) {
                                    Label_0566: {
                                        if (i == 0) {
                                            break Label_0566;
                                        }
                                        try {
                                            array4[n15] = s4.substring(n14, array3[n15]);
                                        }
                                        catch (StringIndexOutOfBoundsException ex5) {}
                                        n14 = array3[n15] + 1;
                                        ++n15;
                                    }
                                    if (n15 < n9) {
                                        continue;
                                    }
                                    break;
                                }
                                n16 = 0;
                                if (i != 0) {
                                    continue;
                                }
                                break;
                            }
                            while (true) {
                                Label_0618: {
                                    if (i == 0) {
                                        break Label_0618;
                                    }
                                    if (s3.equals(this.h.dr(array4[n16], 0, this.bg))) {
                                        this.bg = true;
                                    }
                                    ++n16;
                                }
                                if (n16 >= n9) {
                                    break Label_0625;
                                }
                                continue;
                            }
                        }
                    }
                    this.bg = true;
                    if (i != 0) {}
                }
            }
            this.getParameter(c("D:9;\fA9,4\u0004S"));
        }
        final String s6 = parameter2;
        Container parent = null;
        Label_0716: {
            if (i == 0) {
                if (s6.equalsIgnoreCase(c("o\u001a\r"))) {
                    this.bi = true;
                }
                this.bj = this.getParameter(c("{60\u00060x\u001c"));
                parent = this;
                if (i != 0) {
                    break Label_0716;
                }
                final String bj = this.bj;
            }
            if (s6 == null) {
                this.bj = c("\u0007o");
            }
            this.e = Integer.valueOf(this.bj);
            this.q = false;
            parent = this.getParent();
        }
        Container container = parent;
        while (true) {
            while (true) {
                Label_0733: {
                    if (i == 0) {
                        break Label_0733;
                    }
                    final Container parent2 = ((Frame)container).getParent();
                    container = parent2;
                }
                if (!(container instanceof Frame)) {
                    continue;
                }
                break;
            }
            (this.r = (Frame)container).setCursor(3);
            final Container parent2 = this;
            if (i != 0) {
                continue;
            }
            break;
        }
        final String parameter3 = this.getParameter(c("Y);'\u0000[8"));
        String s8;
        final String s7 = s8 = parameter3;
        final String s9;
        Label_0915: {
            if (i == 0) {
                mosaic mosaic = null;
                Label_0912: {
                    if (s7 != null) {
                        s9 = parameter3;
                        if (i != 0) {
                            break Label_0915;
                        }
                        if (!s9.equalsIgnoreCase(c("x\u0010"))) {
                            this.l = this.a(parameter3);
                            mosaic = this;
                            if (i != 0) {
                                break Label_0912;
                            }
                            if (this.l != null) {
                                final String parameter4;
                                String s10 = parameter4 = this.getParameter(c("Y);'\u0000[8\u0006"));
                                if (i == 0) {
                                    if (parameter4 == null) {
                                        s10 = "0";
                                    }
                                    this.m = Integer.valueOf(s10);
                                    this.getParameter(c("Y);'\u0000[8\u0007"));
                                }
                                final String s12;
                                String s11 = s12 = parameter4;
                                if (i != 0 || s12 == null) {
                                    s11 = s12;
                                }
                                this.n = Integer.valueOf(s11);
                            }
                        }
                    }
                    this.E = this.getParameter(c("D:-"));
                    mosaic = this;
                }
                final String e = mosaic.E;
            }
        }
        String s14 = null;
        String k = null;
        String s13 = null;
        Label_1008: {
            if (i == 0) {
                if (s7 == null) {
                    this.E = "1";
                }
                this.G = this.getParameter(c("F>+&\f"));
                s13 = (s8 = (k = (s14 = this.G)));
                if (i != 0) {
                    break Label_1008;
                }
            }
            if (s9 == null) {
                this.G = c("\u0007jne");
            }
            this.x = Integer.valueOf(this.E);
            this.X = Integer.valueOf(this.G);
            this.J = this.getParameter(c("T>=>\u001b"));
            k = (s13 = (s14 = this.J));
        }
        if (i == 0) {
            if (s13 == null) {
                this.J = c("\u0000k");
            }
            this.K = this.getParameter(c("T>=>\u000e"));
            s14 = (k = this.K);
        }
        mosaic mosaic2 = null;
        Label_1209: {
            if (i == 0) {
                if (k == null) {
                    this.K = c("\u000fi");
                }
                this.L = this.getParameter(c("T>=>\u000b"));
                mosaic2 = this;
                if (i != 0) {
                    break Label_1209;
                }
                s14 = this.L;
            }
            if (s14 == null) {
                this.L = c("\u0007in");
            }
            this.Q = Integer.valueOf(this.J);
            this.R = Integer.valueOf(this.K);
            this.S = Integer.valueOf(this.L);
            this.T = (this.Q << 16 | this.R << 8 | this.S);
            this.U = new Color(this.Q, this.R, this.S);
            this.M = this.getParameter(c("[:31\fZ>'"));
            this.N = this.getParameter(c("F-7:\u001b_+'"));
            mosaic2 = this;
        }
        mosaic2.V = 1;
        while (true) {
            while (true) {
                Label_1228: {
                    if (i == 0) {
                        break Label_1228;
                    }
                    final mosaic mosaic3 = this;
                    final mosaic mosaic4 = this;
                    mosaic3.V = mosaic4.V + 1;
                }
                if (this.getParameter(c("_2?2\f") + String.valueOf(this.V)) != null) {
                    continue;
                }
                break;
            }
            --this.V;
            final mosaic mosaic3 = this;
            final mosaic mosaic4 = this;
            if (i != 0) {
                continue;
            }
            break;
        }
        final int v = this.V;
        if (i == 0) {
            if (v <= 1) {
                do {
                    this.showStatus(c("w33:\u001aB\u007flu\u0000[>90\u001a\u0016-;$\u001c_-;1H"));
                } while (i == 0);
            }
            this.W = new String[this.V];
            this.o = new String[this.V];
            this.p = new String[this.V];
        }
        int n17 = v;
        int n18;
        while (true) {
            while (true) {
                Label_1382: {
                    if (i == 0) {
                        break Label_1382;
                    }
                    this.W[n17] = this.getParameter(c("_2?2\f") + String.valueOf(n17 + 1));
                    ++n17;
                }
                if (n17 < this.V) {
                    continue;
                }
                break;
            }
            n18 = 0;
            if (i != 0) {
                if (i != 0) {
                    continue;
                }
            }
            break;
        }
        while (true) {
            mosaic mosaic5;
            if (n18 >= this.V) {
                this.j = Integer.valueOf(this.M);
                this.k = Integer.valueOf(this.N);
                mosaic5 = this;
                if (i == 0) {
                    break;
                }
            }
            else {
                this.o[n18] = this.getParameter(c("Z60>") + String.valueOf(n18 + 1));
                mosaic5 = this;
            }
            mosaic5.p[n18] = this.getParameter(c("E+?!\u001cE2-2") + String.valueOf(n18 + 1));
            ++n18;
        }
        int j;
        final int n19 = j = this.j;
        if (i == 0) {
            if (n19 < 0) {
                this.j = 0;
            }
            final int l;
            j = (l = this.k);
        }
        final int n20 = 10;
        int n21 = 0;
        int x = 0;
        int n24 = 0;
        int n22 = 0;
        Label_1590: {
            Label_1584: {
                if (i == 0) {
                    if (n19 > n20) {
                        this.k = 10;
                        if (i == 0) {
                            break Label_1584;
                        }
                    }
                    x = (j = (n21 = this.k));
                    final int n23;
                    n22 = (n23 = (n24 = 1));
                    if (i != 0) {
                        break Label_1590;
                    }
                }
                if (j < n20) {
                    this.k = 1;
                }
            }
            n21 = (x = this.x);
            n24 = (n22 = 8);
        }
        int n26 = 0;
        Label_1737: {
            int x2 = 0;
            int n25 = 0;
            Label_1637: {
                Label_1627: {
                    if (i == 0) {
                        if (x > n22) {
                            this.x = 8;
                            if (i == 0) {
                                break Label_1627;
                            }
                        }
                        x2 = (n21 = this.x);
                        n25 = (n24 = 1);
                        if (i != 0) {
                            break Label_1637;
                        }
                    }
                    if (n21 < n24) {
                        this.x = 1;
                    }
                }
                n26 = (x2 = this.X);
                if (i != 0) {
                    break Label_1737;
                }
                n25 = 1;
            }
            if (x2 < n25) {
                this.X = 1;
            }
            this.t = this.size().width / this.x;
            this.u = this.size().height / this.x;
            this.v = this.t * this.x;
            this.w = this.u * this.x;
            this.s = this.t * this.u;
            this.y = new int[this.s];
            this.z = new int[this.s];
            n26 = 0;
        }
        int n27 = n26;
        while (true) {
            while (true) {
                Label_1758: {
                    if (i == 0) {
                        break Label_1758;
                    }
                    final mosaic mosaic6 = this;
                    mosaic6.z[n27] = this.T;
                    ++n27;
                }
                if (n27 < this.s) {
                    continue;
                }
                break;
            }
            this.D = null;
            this.D = this.getParameter(c("T>=>\u0000[>90"));
            final mosaic mosaic7 = this;
            final mosaic mosaic6 = this;
            if (i != 0) {
                continue;
            }
            break;
        }
        Label_1922: {
            if (i != 0) {
                break Label_1922;
            }
            if (!this.D.equalsIgnoreCase(c("x\u0010"))) {
                final Image a;
                final Image image = a = this.a(this.D);
                Label_1918: {
                    if (i == 0 && a == null) {
                        this.showStatus(c("s-,:\u001b\u0016314\r_19u\u000bW<52\u001bY*01I_2?2\f"));
                        if (i != 0) {
                            goto Label_1844;
                        }
                    }
                    else {
                        final int width = a.getWidth(this);
                        final int t = this.t;
                        if (i == 0) {
                            if (width != t) {
                                break Label_1918;
                            }
                            image.getHeight(this);
                            final int u = this.u;
                        }
                        if (width == t) {
                            final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, this.t, this.u, this.z, 0, this.t);
                            try {
                                pixelGrabber.grabPixels();
                            }
                            catch (InterruptedException ex6) {}
                        }
                    }
                }
            }
            try {
                final mosaic mosaic7 = this;
                mosaic7.b();
            }
            catch (NoSuchMethodError noSuchMethodError) {
                this.b();
            }
        }
        this.O = this.createImage(this.v, this.w);
        this.P = this.O.getGraphics();
        this.D = this.getParameter(c("B620\u001aA6:!\u0001"));
        final String d = this.D;
        int intValue = 0;
        Label_2001: {
            if (i == 0) {
                if (d == null) {
                    intValue = 8;
                    break Label_2001;
                }
                final String d2 = this.D;
            }
            intValue = Integer.valueOf(d);
        }
        this.bc = intValue;
        this.D = this.getParameter(c("B620\u001a^:72\u0001B"));
        final String d3 = this.D;
        int intValue2 = 0;
        Label_2045: {
            if (i == 0) {
                if (d3 == null) {
                    intValue2 = 8;
                    break Label_2045;
                }
                final String d4 = this.D;
            }
            intValue2 = Integer.valueOf(d3);
        }
        this.bb = intValue2;
        this.D = this.getParameter(c("B620\u001aB:.&"));
        final String d5 = this.D;
        int intValue3 = 0;
        Label_2089: {
            if (i == 0) {
                if (d5 == null) {
                    intValue3 = 16;
                    break Label_2089;
                }
                final String d6 = this.D;
            }
            intValue3 = Integer.valueOf(d5);
        }
        this.bd = intValue3;
        int n29;
        final int n28 = n29 = this.bc;
        int n31;
        final int n30 = n31 = 1;
        if (i == 0) {
            if (n28 < n30) {
                this.bc = 1;
            }
            final int bb;
            n29 = (bb = this.bb);
            final int n32;
            n31 = (n32 = 1);
        }
        int n34 = 0;
        Label_2219: {
            Label_2173: {
                int bd = 0;
                int n33 = 0;
                Label_2164: {
                    if (i == 0) {
                        if (n28 < n30) {
                            this.bb = 1;
                        }
                        bd = (n29 = this.bd);
                        n33 = (n31 = 8);
                        if (i != 0) {
                            break Label_2164;
                        }
                    }
                    if (n29 < n31) {
                        this.bd = 8;
                        if (i == 0) {
                            break Label_2173;
                        }
                    }
                    n34 = (bd = this.bd);
                    if (i != 0) {
                        break Label_2219;
                    }
                    n33 = 32;
                }
                if (bd > n33) {
                    this.bd = 32;
                }
            }
            (this.B = new Image[2])[0] = null;
            this.bp = new boolean[this.V];
            this.bn = new int[this.V];
            this.bo = new int[this.V];
            n34 = 0;
        }
        int n35 = n34;
        while (true) {
            while (true) {
                Label_2237: {
                    if (i == 0) {
                        break Label_2237;
                    }
                    this.bp[n35] = false;
                    ++n35;
                }
                if (n35 < this.V) {
                    continue;
                }
                break;
            }
            this.bq = 1;
            if (i == 0) {
                return;
            }
            continue;
        }
    }
    
    void b() {
        this.b = new MemoryImageSource(this.t, this.u, new DirectColorModel(24, 16711680, 65280, 255), this.y, 0, this.t);
        String s;
        try {
            s = System.getProperty(c("\\>(4G@:,&\u0000Y1"));
        }
        catch (SecurityException ex) {
            s = c("C15");
        }
        if (!s.startsWith(c("\u0007qn"))) {
            try {
                this.b.setAnimated(true);
                this.b.setFullBufferUpdates(true);
                this.A = this.createImage(this.b);
                this.b.newPixels();
                this.c = true;
            }
            catch (NoSuchMethodError noSuchMethodError) {
                this.c = false;
            }
        }
        if (!this.c) {
            this.b = null;
            this.a = new anfy(this.t, this.u, new DirectColorModel(24, 16711680, 65280, 255), this.y, 0, this.t);
            this.A = this.createImage(this.a);
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
        Label_0170: {
            try {
                try {
                    final InputStream resourceAsStream = this.getClass().getResourceAsStream(url.toString());
                    if (resourceAsStream == null) {
                        break Label_0170;
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
                this.showStatus(c("\u007f2?2\f\u0016") + s + c("\u001611!IP0+;\r\u0017"));
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
    
    private final synchronized boolean c() {
        this.bl = new MediaTracker(this);
        for (int i = 0; i < 2; ++i) {
            this.showStatus(c("\u007f2?2\f\u0016") + String.valueOf(i + 1));
            this.B[i] = this.a(this.W[i]);
            if (this.B[i] == null) {
                this.showStatus(c("s-,:\u001b\u0016314\r_19u\u0000[>90I") + String.valueOf(i + 1));
                return false;
            }
            this.bp[i] = true;
            this.bn[i] = this.B[i].getWidth(this);
            this.bo[i] = this.B[i].getHeight(this);
            if (i == 0) {
                this.Z = this.bn[0];
                this.ba = this.bo[0];
                this.bm = true;
                this.repaint();
            }
            else if (this.bn[i] != this.bn[i - 1] || this.bo[i] != this.bo[i - 1]) {
                this.showStatus(c("s-,:\u001b\u0017\u007f\u00178\bQ:-u$c\f\nu\u000bS\u007f*=\f\u0016,?8\f\u0016,7/\f\u0017"));
            }
            if (this.bk == null) {
                this.bk = new int[this.V][this.bn[i] * this.bo[i]];
            }
            if (!this.a(this.B[i], this.bk[i])) {
                return false;
            }
        }
        this.B[1].flush();
        this.B[1] = null;
        System.gc();
        return true;
    }
    
    private final synchronized boolean a(final int n) {
        new MediaTracker(this);
        final Image a = this.a(this.W[n]);
        if (a == null) {
            this.showStatus(c("s-,:\u001b\u0016314\r_19u\u0000[>90I") + String.valueOf(n + 1));
            return false;
        }
        this.bp[n] = true;
        if (!this.a(a, this.bk[n])) {
            return false;
        }
        a.flush();
        System.gc();
        return true;
    }
    
    private boolean a(final Image image, final int[] array) {
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, image.getWidth(this), image.getHeight(this), array, 0, image.getWidth(this));
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {}
        return true;
    }
    
    public void start() {
        if (this.g == null) {
            (this.g = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.g != null && this.g.isAlive()) {
            this.g.stop();
        }
        this.g = null;
    }
    
    public void run() {
        this.g.setPriority(this.k);
        this.showStatus("");
        this.f = System.currentTimeMillis();
        if (!this.bp[0]) {
            this.c();
        }
        this.showStatus("");
        this.a(this.t, this.u, this.Z, this.ba, this.bb, this.bc);
        try {
            System.arraycopy(this.z, 0, this.y, 0, this.s);
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            this.stop();
        }
        catch (ArrayStoreException ex2) {
            this.stop();
        }
        this.f();
        try {
            this.producefixed();
        }
        catch (NoSuchMethodError noSuchMethodError) {}
        this.bm = false;
        this.repaint();
        System.gc();
        final long n = this.X - (System.currentTimeMillis() - this.f);
        if (n > 0L) {
            try {
                Thread.sleep(n);
            }
            catch (InterruptedException ex3) {}
        }
        final Graphics graphics = this.getGraphics();
        if (this.l != null && !this.be) {
            this.be = this.CheckAniGIF();
        }
        if (!this.o[this.bq - 1].equalsIgnoreCase(c("x\u0010"))) {
            this.r.setCursor(12);
        }
        else {
            this.r.setCursor(0);
        }
        this.h.dr(c("W18,"), 1, this.bg);
        while (this.g != null) {
            try {
                System.arraycopy(this.z, 0, this.y, 0, this.s);
            }
            catch (ArrayIndexOutOfBoundsException ex4) {
                this.stop();
            }
            catch (ArrayStoreException ex5) {
                this.stop();
            }
            this.f();
            if (++this.i == this.j) {
                System.gc();
                this.i = 0;
            }
            try {
                this.producefixed();
            }
            catch (NoSuchMethodError noSuchMethodError2) {}
            if (this.x == 1) {
                this.P.drawImage(this.A, 0, 0, this);
            }
            else {
                this.prepscaled();
                this.P.drawImage(this.A, 0, 0, this.v, this.w, this);
            }
            if (this.l != null) {
                this.prepaniframe();
            }
            graphics.drawImage(this.O, 0, 0, this);
            this.waitsync();
        }
    }
    
    public final void producefixed() {
        try {
            if (this.c) {
                this.b.newPixels();
                return;
            }
            this.a.startProduction(this.a.getConsumer());
        }
        catch (NoSuchMethodError noSuchMethodError) {}
    }
    
    public synchronized void prepscaled() {
        int checkImage = 0;
        this.prepareImage(this.A, this.v, this.w, this);
        this.notifyAll();
        while ((checkImage & 0xF0) == 0x0) {
            Thread.yield();
            checkImage = this.checkImage(this.A, this.v, this.w, this);
        }
    }
    
    public synchronized void prepscaled0() {
        int checkImage = 0;
        this.prepareImage(this.B[0], this.v, this.w, this);
        this.notifyAll();
        while ((checkImage & 0xF0) == 0x0) {
            Thread.yield();
            checkImage = this.checkImage(this.B[0], this.v, this.w, this);
        }
    }
    
    public synchronized void waitsync() {
        Thread.yield();
        this.d.sync();
        final long n = 10L - (System.currentTimeMillis() - this.f);
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
        this.f = System.currentTimeMillis();
        try {
            Thread.sleep(this.e);
        }
        catch (InterruptedException ex3) {}
    }
    
    public final void paint(final Graphics graphics) {
        if (this.q) {
            if (this.A != null) {
                if (this.x == 1) {
                    this.P.drawImage(this.A, 0, 0, this);
                }
                else {
                    this.prepscaled();
                    this.P.drawImage(this.A, 0, 0, this.v, this.w, this);
                }
                if (this.l != null) {
                    this.prepaniframe();
                }
                graphics.drawImage(this.O, 0, 0, this);
            }
        }
        else {
            this.waitdisplay();
        }
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final void waitdisplay() {
        final Graphics graphics = this.getGraphics();
        if (this.B != null && this.P != null && this.bm && this.B[0] != null) {
            final int n = (this.t - this.Z) / 2 + 1;
            final int n2 = (this.u - this.ba) / 2 + 1;
            this.P.setColor(this.U);
            this.P.fillRect(0, 0, this.v, this.w);
            if (this.x == 1) {
                this.P.drawImage(this.B[0], n, n2, this);
            }
            else {
                this.prepscaled0();
                this.P.drawImage(this.B[0], n, n2, this.v, this.w, this);
            }
            if (this.l != null) {
                this.P.drawImage(this.l, this.m, this.n, this);
            }
            this.P.setColor(Color.black);
            this.P.drawString(c("z0?1\u0000X8p{G"), this.v / 2 - 26 + 1, this.w / 2 + 2 + 1);
            this.P.setColor(Color.white);
            this.P.drawString(c("z0?1\u0000X8p{G"), this.v / 2 - 26, this.w / 2 + 2);
            graphics.drawImage(this.O, 0, 0, this);
        }
    }
    
    public final boolean mouseEnter(final Event event, final int n, final int n2) {
        if (!this.o[this.bq - 1].equalsIgnoreCase(c("x\u0010"))) {
            this.r.setCursor(12);
        }
        else {
            this.r.setCursor(0);
        }
        this.br = true;
        this.showStatus(this.p[this.bq - 1]);
        return true;
    }
    
    public final boolean mouseExit(final Event event, final int n, final int n2) {
        this.br = false;
        this.showStatus("");
        return true;
    }
    
    public final boolean mouseMove(final Event event, final int n, final int n2) {
        if (this.bs != this.bq) {
            this.showStatus(this.p[this.bq - 1]);
        }
        this.bs = this.bq;
        return true;
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        final int cb = mosaic.cb;
        final boolean bg = this.bg;
        if (cb == 0) {
            if (!bg) {
                this.h.show();
                try {
                    this.h.move(100, 100);
                }
                catch (Exception ex) {}
                this.h.toFront();
                this.h.requestFocus();
                if (cb == 0) {
                    return true;
                }
            }
            this.bh = null;
            final boolean equalsIgnoreCase;
            final boolean b = equalsIgnoreCase = this.o[this.bq - 1].equalsIgnoreCase(c("x\u0010"));
            if (cb != 0) {
                return b;
            }
        }
        if (!bg) {
            this.showStatus(c("q07;\u000e\u0016+1u\u0019W8;u") + String.valueOf(this.bq));
            try {
                this.bh = new URL(this.getDocumentBase(), this.o[this.bq - 1]);
            }
            catch (MalformedURLException ex2) {
                this.showStatus(c("s-,:\u001b\u001637;\u0002_19"));
                return true;
            }
            mosaic mosaic = this;
            Label_0186: {
                if (cb != 0) {
                    break Label_0186;
                }
                if (this.bh == null) {
                    return true;
                }
                try {
                    this.h.dck();
                    mosaic mosaic2 = this;
                    mosaic = this;
                    if (cb == 0) {
                        if (mosaic.bi) {
                            this.getAppletContext().showDocument(this.bh, this.getParameter(c("D:93\u001bW2;;\b[:")));
                            if (cb == 0) {
                                return true;
                            }
                        }
                        mosaic2 = this;
                    }
                    mosaic2.getAppletContext().showDocument(this.bh);
                }
                catch (Exception ex3) {}
            }
        }
        return true;
    }
    
    void a(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        this.bx = this.bd;
        this.b(n, n2, n3, n4, n5, n6);
    }
    
    void d() {
        if (this.bC > 7) {
            this.bC = 0;
        }
        for (int i = 0; i < this.bt; ++i) {
            for (int j = 0; j < this.bu; ++j) {
                final int n = j + i * this.bu;
                switch (this.bC) {
                    case 7: {
                        final double n2 = j - this.bu / 2;
                        final double n3 = i - this.bt / 2;
                        this.bw[n] = (int)(4.0 * Math.sqrt(n2 * n2 + n3 * n3) + this.bx);
                        break;
                    }
                    case 1: {
                        this.bw[n] = j + i + this.bx;
                        break;
                    }
                    case 2: {
                        this.bw[n] = (j + i * this.bu) / 2 + this.bx;
                        break;
                    }
                    case 3: {
                        this.bw[n] = (j * this.bt + i) / 2 + this.bx;
                        break;
                    }
                    case 4: {
                        this.bw[n] = i * 2 / 3 + this.bx;
                        break;
                    }
                    case 5: {
                        this.bw[n] = j * 2 / 3 + this.bx;
                        break;
                    }
                    case 6: {
                        this.bw[n] = (int)(Math.random() * 10.0) + this.bx;
                        break;
                    }
                    case 0: {
                        this.bw[this.bt * this.bu - n - 1] = i + j + this.bx;
                        break;
                    }
                    default: {
                        this.bw[j + i * this.bu] = i + j * this.bt + j + this.bx;
                        break;
                    }
                }
            }
        }
        ++this.bC;
    }
    
    private void e() {
        final double n = this.Z / this.bu;
        final double n2 = this.ba / this.bt;
        for (int i = 0; i < this.bt; ++i) {
            for (int j = 0; j < this.bu; ++j) {
                final int n3 = j + i * this.bu;
                this.bA[n3 * 8] = (int)((this.bu - 1 - j) * n);
                this.bA[n3 * 8 + 1] = (int)((this.bt - 1 - i) * n2);
                this.bA[n3 * 8 + 2] = (int)(n + (this.bu - 1 - j) * n - 1.0);
                this.bA[n3 * 8 + 3] = (int)((this.bt - 1 - i) * n2 + n2 - 1.0);
                this.bA[n3 * 8 + 4] = this.bA[n3 * 8];
                this.bA[n3 * 8 + 5] = this.bA[n3 * 8 + 1];
                this.bA[n3 * 8 + 6] = this.bA[n3 * 8 + 2];
                this.bA[n3 * 8 + 7] = this.bA[n3 * 8 + 3];
            }
        }
    }
    
    private void b(final int n, final int n2, final int n3, final int n4, final int bt, final int bu) {
        this.bw = new int[bt * bu];
        this.bt = bt;
        this.bu = bu;
        this.by = new double[bt * bu * 3];
        this.bz = new double[bt * bu];
        this.bA = new int[bt * bu * 8];
        final double n5 = n3 / bu;
        final double n6 = n4 / bt;
        this.a(n5, n6, n, n2);
        for (int i = 0; i < this.bt; ++i) {
            for (int j = 0; j < this.bu; ++j) {
                final int n7 = j + i * this.bu;
                this.by[n7 * 3] = (j - this.bu / 2.0) * 2.0 * n5 + n5;
                this.by[n7 * 3 + 1] = (this.bt / 2.0 - i) * 2.0 * n6 - n6;
                this.by[n7 * 3 + 2] = -512.0;
                this.bz[n7] = 3.141592653589793;
            }
        }
        this.d();
        this.e();
    }
    
    void f() {
        this.bv = 0;
        if (!this.bp[this.bq % this.V]) {
            this.a(this.bq % this.V);
        }
        for (int i = 0; i < this.bt; ++i) {
            for (int j = 0; j < this.bu; ++j) {
                final int n = j + i * this.bu;
                this.a(this.by[n * 3], this.by[n * 3 + 1], this.by[n * 3 + 2]);
                if (this.ca[n] == 0) {
                    this.bY = this.bz[n];
                    this.bZ = 0.0;
                }
                else if (this.ca[n] == 1) {
                    this.bZ = this.bz[n];
                    this.bY = 0.0;
                }
                else {
                    this.bY = 2.0 * this.bz[n];
                    this.bZ = this.bz[n];
                }
                this.a(this.bA, n * 8, this.ca[n]);
                this.j();
                if (this.bw[n] <= this.bx && this.bw[n] > 0) {
                    this.bz[n] = 3.141592653589793 / this.bx * (this.bw[n] - 1);
                }
                else if (this.bw[n] < -1) {
                    ++this.bv;
                }
                final int[] bw = this.bw;
                final int n2 = n;
                --bw[n2];
            }
        }
        if (this.bv == this.bt * this.bu) {
            this.g();
            this.d();
            for (int k = 0; k < this.bt * this.bu; ++k) {
                this.bz[k] = 3.141592653589793;
            }
            ++this.bB;
            ++this.bq;
            if (this.bq > this.V) {
                this.bq = 1;
            }
            if (this.br) {
                this.showStatus(this.p[this.bq - 1]);
            }
            if (!this.o[this.bq - 1].equalsIgnoreCase(c("x\u0010"))) {
                this.r.setCursor(12);
            }
            else {
                this.r.setCursor(0);
            }
            try {
                Thread.sleep(this.X);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    protected void a(final double n, final double n2, final int n3, final int n4) {
        this.ca = new int[this.bt * this.bu];
        this.a(n, n2);
        this.h();
        this.a(n3, n4);
    }
    
    protected void g() {
        final double random = Math.random();
        int n;
        if (random < 0.25) {
            n = 0;
        }
        else if (random < 0.5) {
            n = 1;
        }
        else if (random < 0.75) {
            n = 2;
        }
        else {
            n = 3;
        }
        if (n == 2) {
            for (int i = 0; i < this.bt * this.bu; ++i) {
                this.ca[i] = ((Math.random() <= 0.5) ? 1 : 0);
            }
            return;
        }
        for (int j = 0; j < this.bt * this.bu; ++j) {
            switch (n) {
                case 0: {
                    this.ca[j] = 0;
                    break;
                }
                case 1: {
                    this.ca[j] = 1;
                    break;
                }
                case 3: {
                    this.ca[j] = 2;
                    break;
                }
            }
        }
    }
    
    protected void a(final int bl, final int bm) {
        this.bF = new int[16];
        this.bH = new int[8];
        this.bQ = new boolean[2];
        this.bG = new double[16];
        this.bI = new int[bm * 2];
        this.bJ = new int[bm * 2];
        this.bK = new int[bm * 2];
        this.bM = bm;
        this.bL = bl;
        this.bR = new double[6];
        for (int i = 0; i < 6; ++i) {
            this.bR[i] = 0.0;
        }
    }
    
    protected void a(final double n, final double n2) {
        (this.bD = new double[24])[0] = -1.0;
        this.bD[1] = -1.0;
        this.bD[2] = 0.0;
        this.bD[3] = -1.0;
        this.bD[4] = -1.0;
        this.bD[5] = 0.0;
        this.bD[6] = 1.0;
        this.bD[7] = -1.0;
        this.bD[8] = 0.0;
        this.bD[9] = 1.0;
        this.bD[10] = -1.0;
        this.bD[11] = 0.0;
        this.bD[12] = 1.0;
        this.bD[13] = 1.0;
        this.bD[14] = 0.0;
        this.bD[15] = 1.0;
        this.bD[16] = 1.0;
        this.bD[17] = 0.0;
        this.bD[18] = -1.0;
        this.bD[19] = 1.0;
        this.bD[20] = 0.0;
        this.bD[21] = -1.0;
        this.bD[22] = 1.0;
        this.bD[23] = 0.0;
        for (int i = 0; i < 4; ++i) {
            final double[] bd = this.bD;
            final int n3 = i * 6;
            bd[n3] *= n;
            final double[] bd2 = this.bD;
            final int n4 = i * 6 + 1;
            bd2[n4] *= n2;
            final double[] bd3 = this.bD;
            final int n5 = i * 6 + 3;
            bd3[n5] *= n;
            final double[] bd4 = this.bD;
            final int n6 = i * 6 + 4;
            bd4[n6] *= n2;
        }
    }
    
    protected void h() {
        (this.bE = new int[8])[0] = 0;
        this.bE[1] = 1;
        this.bE[2] = 2;
        this.bE[3] = 3;
        this.bE[4] = 3;
        this.bE[5] = 2;
        this.bE[6] = 1;
        this.bE[7] = 0;
        this.i();
    }
    
    protected void i() {
        this.bP = new double[12];
        for (int i = 0; i < 2; ++i) {
            final int n = this.bE[i * 4 + 2];
            final int n2 = this.bE[i * 4 + 1];
            final int n3 = this.bE[i * 4];
            final double n4 = this.bD[n * 6] - this.bD[n3 * 6];
            final double n5 = this.bD[n * 6 + 1] - this.bD[n3 * 6 + 1];
            final double n6 = this.bD[n * 6 + 2] - this.bD[n3 * 6 + 2];
            final double n7 = this.bD[n2 * 6] - this.bD[n3 * 6];
            final double n8 = this.bD[n2 * 6 + 1] - this.bD[n3 * 6 + 1];
            final double n9 = this.bD[n2 * 6 + 2] - this.bD[n3 * 6 + 2];
            final double n10 = n5 * n9 - n6 * n8;
            final double n11 = n6 * n7 - n4 * n9;
            final double n12 = n4 * n8 - n7 * n5;
            final double sqrt = Math.sqrt(n10 * n10 + n11 * n11 + n12 * n12);
            final double n13 = n10 / sqrt;
            final double n14 = n11 / sqrt;
            final double n15 = n12 / sqrt;
            this.bP[i * 6] = n13;
            this.bP[i * 6 + 1] = n14;
            this.bP[i * 6 + 2] = n15;
            this.bP[i * 6 + 3] = n13;
            this.bP[i * 6 + 4] = n14;
            this.bP[i * 6 + 5] = n15;
        }
    }
    
    void j() {
        this.m();
        this.l();
        this.k();
    }
    
    protected void k() {
        this.bN = Integer.MAX_VALUE;
        this.bO = Integer.MIN_VALUE;
        for (int i = 0; i < 2; ++i) {
            if (this.bQ[i]) {
                for (int j = 0; j < 4; ++j) {
                    int bm;
                    if (this.bH[this.bE[i * 4 + j] * 2 + 1] <= this.bH[this.bE[i * 4 + (j + 1) % 4] * 2 + 1]) {
                        bm = 0;
                    }
                    else {
                        bm = this.bM;
                    }
                    this.a(this.bH[this.bE[i * 4 + j] * 2], this.bH[this.bE[i * 4 + j] * 2 + 1], this.bH[this.bE[i * 4 + (j + 1) % 4] * 2], this.bH[this.bE[i * 4 + (j + 1) % 4] * 2 + 1], this.bI, bm);
                    if (this.bH[this.bE[i * 4 + j] * 2 + 1] < this.bN) {
                        this.bN = this.bH[this.bE[i * 4 + j] * 2 + 1];
                    }
                    if (this.bH[this.bE[i * 4 + (j + 1) % 4] * 2 + 1] > this.bO) {
                        this.bO = this.bH[this.bE[i * 4 + (j + 1) % 4] * 2 + 1];
                    }
                    this.a(this.bF[(i * 4 + j) * 2], this.bH[this.bE[i * 4 + j] * 2 + 1], this.bF[(i * 4 + (j + 1) % 4) * 2], this.bH[this.bE[i * 4 + (j + 1) % 4] * 2 + 1], this.bJ, bm);
                    this.a(this.bF[(i * 4 + j) * 2 + 1], this.bH[this.bE[i * 4 + j] * 2 + 1], this.bF[(i * 4 + (j + 1) % 4) * 2 + 1], this.bH[this.bE[i * 4 + (j + 1) % 4] * 2 + 1], this.bK, bm);
                }
                if (this.bN >= this.bM || this.bO < 0) {
                    return;
                }
                if (this.bN < 0) {
                    this.bN = 0;
                }
                if (this.bO >= this.bM) {
                    this.bO = this.bM - 1;
                }
                this.b(this.bB + 1 - i);
            }
        }
    }
    
    void b(final int n) {
        final int[] array = this.bk[n % this.V];
        final int[] y = this.y;
        int i = this.bN;
        final int bo = this.bO;
        final int[] bj = this.bJ;
        final int[] bk = this.bK;
        final int bm = this.bM;
        final int bl = this.bL;
        final int n2 = this.bL - 1;
        final int z = this.Z;
        while (i <= bo) {
            final int n3 = i + bm;
            int n4 = this.bI[i] >> 16;
            int n5 = this.bI[n3] >> 16;
            int n6 = bj[i];
            final int n7 = bj[n3];
            int n8 = bk[i];
            final int n9 = bk[n3];
            final int n10 = n5 - n4;
            int n11;
            int n12;
            if (n10 != 0) {
                n11 = (n7 - n6) / n10;
                n12 = (n9 - n8) / n10;
                if (n4 < 0) {
                    n6 -= n11 * n4;
                    n8 -= n12 * n4;
                    n4 = 0;
                }
            }
            else {
                n11 = 0;
                n12 = 0;
                if (n4 < 0) {
                    n4 = 0;
                }
            }
            if (n5 >= bl) {
                n5 = n2;
            }
            for (int j = this.t * i + n4; j <= this.t * i + n5; ++j) {
                y[j] = array[z * (n8 >> 16) + (n6 >> 16)];
                n6 += n11;
                n8 += n12;
            }
            ++i;
        }
    }
    
    protected void l() {
        for (int i = 0; i < 4; ++i) {
            this.bH[i * 2] = (int)(this.t / 2.0 + this.bD[i * 6 + 3] * 256.0 / this.bD[i * 6 + 5]);
            this.bH[i * 2 + 1] = (int)(this.u / 2.0 - this.bD[i * 6 + 4] * 256.0 / this.bD[i * 6 + 5]);
        }
    }
    
    protected void m() {
        final double sin = Math.sin(this.bX);
        final double sin2 = Math.sin(this.bY);
        final double sin3 = Math.sin(this.bZ);
        final double cos = Math.cos(this.bX);
        final double cos2 = Math.cos(this.bY);
        final double cos3 = Math.cos(this.bZ);
        this.bG[0] = cos2 * cos;
        this.bG[1] = -cos2 * sin;
        this.bG[2] = sin2;
        this.bG[3] = this.bR[3];
        this.bG[4] = sin3 * sin2 * cos + sin * cos3;
        this.bG[5] = cos3 * cos - sin3 * sin2 * sin;
        this.bG[6] = -sin3 * cos2;
        this.bG[7] = this.bR[4];
        this.bG[8] = sin3 * sin - cos3 * sin2 * cos;
        this.bG[9] = cos3 * sin2 * sin + sin3 * cos;
        this.bG[10] = cos3 * cos2;
        this.bG[11] = this.bR[5];
        this.bG[12] = 0.0;
        this.bG[13] = 0.0;
        this.bG[14] = 0.0;
        this.bG[15] = 1.0;
        this.o();
        this.n();
    }
    
    void a(final double n, final double n2, final double n3) {
        this.bR[0] = (this.bR[3] = n);
        this.bR[1] = (this.bR[4] = n2);
        this.bR[2] = (this.bR[5] = n3);
    }
    
    protected void n() {
        for (int i = 0; i < 2; ++i) {
            final double n = this.bP[i * 6];
            final double n2 = this.bP[i * 6 + 1];
            final double n3 = this.bP[i * 6 + 2];
            final double[] bp = this.bP;
            final int n4 = i * 6 + 3;
            final double n5 = n * this.bG[0] + n2 * this.bG[1] + n3 * this.bG[2];
            bp[n4] = n5;
            final double n6 = n5;
            final double[] bp2 = this.bP;
            final int n7 = i * 6 + 4;
            final double n8 = n * this.bG[4] + n2 * this.bG[5] + n3 * this.bG[6];
            bp2[n7] = n8;
            final double n9 = n8;
            final double[] bp3 = this.bP;
            final int n10 = i * 6 + 5;
            final double n11 = n * this.bG[8] + n2 * this.bG[9] + n3 * this.bG[10];
            bp3[n10] = n11;
            this.bQ[i] = (n6 * this.bD[this.bE[i * 4] * 6 + 3] + n9 * this.bD[this.bE[i * 4] * 6 + 4] + n11 * this.bD[this.bE[i * 4] * 6 + 5] > 0.0);
        }
    }
    
    protected void o() {
        for (int i = 0; i < 4; ++i) {
            final double n = this.bD[i * 6];
            final double n2 = this.bD[i * 6 + 1];
            final double n3 = this.bD[i * 6 + 2];
            this.bD[i * 6 + 3] = n * this.bG[0] + n2 * this.bG[1] + n3 * this.bG[2] + this.bG[3];
            this.bD[i * 6 + 4] = n * this.bG[4] + n2 * this.bG[5] + n3 * this.bG[6] + this.bG[7];
            this.bD[i * 6 + 5] = n * this.bG[8] + n2 * this.bG[9] + n3 * this.bG[10] + this.bG[11];
        }
    }
    
    protected void a(int n, int i, int n2, int n3, final int[] array, final int n4) {
        if (i == n3) {
            return;
        }
        if (n == n2) {
            this.a(n, i, n3, array, n4);
            return;
        }
        if (i > n3) {
            final int n5 = i;
            i = n3;
            n3 = n5;
            final int n6 = n;
            n = n2;
            n2 = n6;
        }
        final int n7 = (n2 - n << 16) / (n3 - i);
        n <<= 16;
        if (i < 0) {
            n -= n7 * i;
            i = 0;
        }
        if (n3 >= this.bM) {
            n3 = this.bM - 1;
        }
        while (i <= n3) {
            array[n4 + i] = n;
            n += n7;
            ++i;
        }
    }
    
    private void a(final int n, int i, int n2, final int[] array, final int n3) {
        if (i > n2) {
            final int n4 = i;
            i = n2;
            n2 = n4;
        }
        if (i < 0) {
            i = 0;
        }
        if (n2 >= this.bM) {
            n2 = this.bM - 1;
        }
        while (i <= n2) {
            array[n3 + i] = n << 16;
            ++i;
        }
    }
    
    void a(final int[] array, int n, final int n2) {
        this.bF[0] = array[n + 2];
        this.bF[1] = array[n + 1];
        this.bF[2] = array[n];
        this.bF[3] = array[n + 1];
        this.bF[4] = array[n];
        this.bF[5] = array[n + 3];
        this.bF[6] = array[n + 2];
        this.bF[7] = array[n + 3];
        n += 4;
        if (n2 == 0) {
            this.bF[8] = array[n];
            this.bF[9] = array[n + 3];
            this.bF[10] = array[n + 2];
            this.bF[11] = array[n + 3];
            this.bF[12] = array[n + 2];
            this.bF[13] = array[n + 1];
            this.bF[14] = array[n];
            this.bF[15] = array[n + 1];
            return;
        }
        if (n2 == 1 || n2 == 2) {
            this.bF[8] = array[n + 2];
            this.bF[9] = array[n + 1];
            this.bF[10] = array[n];
            this.bF[11] = array[n + 1];
            this.bF[12] = array[n];
            this.bF[13] = array[n + 3];
            this.bF[14] = array[n + 2];
            this.bF[15] = array[n + 3];
        }
    }
    
    public mosaic() {
        this.c = false;
        this.q = false;
        this.x = 1;
        this.Y = false;
        this.be = false;
        this.bg = false;
        this.bi = false;
        this.bm = false;
        this.bq = 1;
        this.br = false;
        this.bx = 16;
    }
    
    private static String c(final String s) {
        final char[] charArray = s.toCharArray();
        for (int length = charArray.length, i = 0; i < length; ++i) {
            final char[] array = charArray;
            final int n = i;
            final char c = array[n];
            char c2 = '\0';
            switch (i % 5) {
                case 0: {
                    c2 = '6';
                    break;
                }
                case 1: {
                    c2 = '_';
                    break;
                }
                case 2: {
                    c2 = '^';
                    break;
                }
                case 3: {
                    c2 = 'U';
                    break;
                }
                default: {
                    c2 = 'i';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
