import java.awt.Event;
import java.awt.image.ImageProducer;
import java.awt.image.PixelGrabber;
import java.io.InputStream;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.image.ColorModel;
import java.awt.image.DirectColorModel;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.awt.FontMetrics;
import java.awt.Container;
import java.net.MalformedURLException;
import java.awt.LayoutManager;
import java.awt.image.MemoryImageSource;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Image;
import java.awt.Frame;
import java.net.URL;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class anpanorama extends Applet implements Runnable
{
    URL a;
    boolean b;
    boolean c;
    String d;
    String e;
    Frame f;
    Lware g;
    boolean h;
    private Image i;
    int j;
    int k;
    boolean l;
    boolean m;
    int n;
    long o;
    int p;
    int q;
    Toolkit r;
    int s;
    int t;
    int u;
    int v;
    int w;
    int x;
    int y;
    int z;
    int A;
    String B;
    int C;
    boolean D;
    boolean E;
    Color F;
    Color G;
    Font H;
    int I;
    int J;
    int K;
    int L;
    int[] M;
    int[] N;
    String[] O;
    int P;
    int Q;
    Font[] R;
    int S;
    int T;
    private int U;
    int V;
    int W;
    int X;
    int Y;
    int Z;
    int[] ba;
    int bb;
    int bc;
    float bd;
    float be;
    int bf;
    int bg;
    boolean bh;
    final String bi = "1c@\u0004\u0017\u00043R\u0011R1}V\u0011R$vQ\u0005RXdG\u001f\\\u0011}";
    boolean bj;
    boolean bk;
    boolean bl;
    int bm;
    int bn;
    static final boolean bo = false;
    static final int bp = 4096;
    static final int bq = 4096;
    static final int br = 12;
    static final double bs = 10.0;
    static final double bt = 170.0;
    static final double bu = -200.0;
    static final long bv = 40L;
    static final long bw = 100000000L;
    int bx;
    int by;
    boolean bz;
    long bA;
    long bB;
    boolean bC;
    Image bD;
    Image bE;
    Image bF;
    Graphics bG;
    int bH;
    int bI;
    MemoryImageSource bJ;
    anfy bK;
    public int vwidth;
    public int vheight;
    boolean bL;
    int bM;
    int bN;
    int bO;
    int bP;
    int[] bQ;
    int[][] bR;
    Color bS;
    boolean bT;
    boolean bU;
    boolean bV;
    boolean bW;
    boolean bX;
    public boolean dirty;
    boolean bY;
    int bZ;
    int ca;
    int cb;
    int cc;
    int[] cd;
    int[] ce;
    int cf;
    int cg;
    public double yaw;
    public double hfov;
    public double hfov_min;
    public double hfov_max;
    public double pitch;
    public double pitch_max;
    public double pitch_min;
    public double yaw_max;
    public double yaw_min;
    double ch;
    double ci;
    double cj;
    private double[][] ck;
    private int[][] cl;
    double cm;
    String cn;
    int co;
    int cp;
    int cq;
    int cr;
    long cs;
    long ct;
    long cu;
    Thread cv;
    int cw;
    String cx;
    boolean cy;
    String[] cz;
    boolean cA;
    public static int cB;
    
    void a() {
        this.cw = 0;
        this.yaw = 0.0;
        this.hfov = 70.0;
        this.hfov_min = 10.0;
        this.hfov_max = 170.0;
        this.pitch = 0.0;
        this.pitch_max = 90.0;
        this.pitch_min = -90.0;
        this.yaw_max = 180.0;
        this.yaw_min = -180.0;
        this.ch = 0.0;
        this.ci = 0.0;
        this.cj = 1.0;
        this.co = 0;
        this.cm = 0.0;
        this.bX = false;
        this.ct = 0L;
        this.dirty = false;
        this.bY = false;
        this.bC = false;
        this.bZ = this.size().width / 2;
        this.ca = this.size().height / 2;
    }
    
    public void init() {
        final int cb = anpanorama.cB;
        this.setLayout(null);
        this.addNotify();
        this.r = this.getToolkit();
        this.e = this.getParameter(d("\u0003gQ\u001c\u0007\u0003~C\u000f"));
        Container parent = this.getParent();
        Container parent2;
        String parameter;
        String protocol;
        String s;
        String s2;
        String d = null;
        String s3;
        int length;
        int n;
        boolean startsWith;
        int n2 = 0;
        int startsWith2 = 0;
        int n4;
        int n3;
        char[] array;
        int n5;
        char[] array2;
        int n6;
        String parameter2 = null;
        String s4;
        String s5;
        int n7;
        int n8;
        int[] array3;
        int n9;
        int n10;
        int n11;
        String[] array4;
        int n12;
        int n13;
        int n14;
        String s7;
        String s6;
        String s8;
        anpanorama anpanorama = null;
        String parameter3;
        String s9;
        String s11;
        String s10;
        String s13;
        String s12;
        String s14;
        String s15;
        String s16;
        String d2;
        String s17;
        String parameter4;
        int n16;
        int n15;
        int q;
        int n17;
        anpanorama anpanorama2 = null;
        Label_0369_Outer:Label_0465_Outer:Label_0551_Outer:Label_0601_Outer:Label_0618_Outer:Label_0654_Outer:
        while (true) {
            while (true) {
                Label_0053: {
                    if (cb == 0) {
                        break Label_0053;
                    }
                    parent2 = ((Frame)parent).getParent();
                    parent = parent2;
                }
                if (!(parent instanceof Frame)) {
                    continue;
                }
                break;
            }
            (this.f = (Frame)parent).setCursor(3);
            parent2 = this;
            if (cb == 0) {
                parameter = this.getParameter(d("\u0013aU\f\u001b\u0004`"));
                s = (protocol = parameter);
                Label_0163: {
                    Label_0132: {
                        Label_0128: {
                            if (cb == 0) {
                                if (s == null) {
                                    break Label_0128;
                                }
                                protocol = (s2 = parameter);
                            }
                            if (cb != 0) {
                                break Label_0163;
                            }
                            if (s.startsWith(d("1c@\u0004\u0017\u00043R\u0011R1}V\u0011R$vQ\u0005RXdG\u001f\\\u0011}"))) {
                                break Label_0132;
                            }
                            this.c();
                            if (cb == 0) {
                                break Label_0132;
                            }
                        }
                        this.c();
                    }
                    (this.g = new Lware(this, d(" r^\u0007\u0000\u0011~QH\u0013\u0000c\\\r\u0006"))).hide();
                    try {
                        protocol = this.getDocumentBase().getProtocol();
                        d = protocol;
                    }
                    catch (SecurityException ex) {
                        d = d("\u0016z\\\r");
                    }
                }
                try {
                    s3 = this.getDocumentBase().getHost();
                }
                catch (SecurityException ex2) {
                    s3 = "";
                }
                s3.toLowerCase();
                d.toLowerCase();
                Label_0718: {
                    Label_0694: {
                        Label_0289: {
                            Label_0279: {
                                if (cb == 0) {
                                    Label_0269: {
                                        if (!d.equals(d("\u0016z\\\r"))) {
                                            n = (length = s3.length());
                                            if (cb == 0) {
                                                if (n < 1) {
                                                    break Label_0269;
                                                }
                                                length = ((startsWith = s3.startsWith(d("\u001c|S\t\u001e"))) ? 1 : 0);
                                            }
                                            if (cb == 0) {
                                                if (n != 0) {
                                                    break Label_0269;
                                                }
                                                startsWith2 = (length = (n2 = (s3.equals(d("A!\u0007FB^#\u001eY")) ? 1 : 0)));
                                                if (cb != 0) {
                                                    break Label_0289;
                                                }
                                            }
                                            if (length == 0) {
                                                break Label_0279;
                                            }
                                        }
                                    }
                                    this.h = true;
                                }
                                if (cb == 0) {
                                    break Label_0694;
                                }
                            }
                            n2 = (startsWith2 = (s3.startsWith(d("\u0007dGF")) ? 1 : 0));
                        }
                        if (cb == 0) {
                            if (startsWith2 != 0) {
                                s3 = s3.substring(4);
                            }
                            n2 = s3.length();
                        }
                        n3 = (n4 = n2);
                        if (cb != 0 || n4 > 0) {
                            array = new char[n4];
                            s3.getChars(0, n3, array, 0);
                            n5 = 0;
                            while (true) {
                                while (true) {
                                    Label_0372: {
                                        if (cb == 0) {
                                            break Label_0372;
                                        }
                                        array2 = array;
                                        n6 = n5;
                                        if (cb != 0 || array2[n6] == '0') {
                                            array2[n6] = '1';
                                        }
                                        n5 += 5;
                                    }
                                    if (n5 < n3) {
                                        continue Label_0369_Outer;
                                    }
                                    break;
                                }
                                if (cb != 0) {
                                    continue;
                                }
                                break;
                            }
                            s3 = new String(array);
                        }
                        s4 = (parameter2 = this.getParameter(d("\u0002vW\u000b\u001d\u0014v")));
                        if (cb != 0) {
                            break Label_0718;
                        }
                        if (parameter2 != null) {
                            s5 = s4;
                            if (cb != 0) {
                                break Label_0718;
                            }
                            if (s5.length() > 5) {
                                s4.toLowerCase();
                                n7 = 1;
                                try {
                                    n8 = 0;
                                    while (true) {
                                        while (true) {
                                            Label_0468: {
                                                if (cb == 0) {
                                                    break Label_0468;
                                                }
                                                if (s4.charAt(n8) == '+') {
                                                    ++n7;
                                                }
                                                ++n8;
                                            }
                                            if (n8 < s4.length()) {
                                                continue Label_0465_Outer;
                                            }
                                            break;
                                        }
                                        if (cb != 0) {
                                            continue Label_0601_Outer;
                                        }
                                        break;
                                    }
                                }
                                catch (StringIndexOutOfBoundsException ex3) {}
                                array3 = new int[n7];
                                n9 = n7;
                                if (cb == 0 && n9 == 1) {
                                    array3[0] = s4.length();
                                    if (cb != 0) {
                                        goto Label_0518;
                                    }
                                }
                                else {
                                    n10 = n9;
                                    try {
                                        n11 = 0;
                                        while (true) {
                                            while (true) {
                                                Label_0554: {
                                                    if (cb == 0) {
                                                        break Label_0554;
                                                    }
                                                    if (s4.charAt(n11) == '+') {
                                                        array3[n10] = n11;
                                                        ++n10;
                                                    }
                                                    ++n11;
                                                }
                                                if (n11 < s4.length()) {
                                                    continue Label_0551_Outer;
                                                }
                                                break;
                                            }
                                            if (cb != 0) {
                                                continue Label_0601_Outer;
                                            }
                                            break;
                                        }
                                    }
                                    catch (StringIndexOutOfBoundsException ex4) {}
                                    array3[n10] = s4.length();
                                }
                                array4 = new String[n7];
                                n12 = 0;
                                n13 = 0;
                                while (true) {
                                    while (true) {
                                        Label_0634: {
                                            if (cb == 0) {
                                                break Label_0634;
                                            }
                                            try {
                                                array4[n13] = s4.substring(n12, array3[n13]);
                                            }
                                            catch (StringIndexOutOfBoundsException ex5) {}
                                            n12 = array3[n13] + 1;
                                            ++n13;
                                        }
                                        if (n13 < n7) {
                                            continue Label_0618_Outer;
                                        }
                                        break;
                                    }
                                    n14 = 0;
                                    if (cb != 0) {
                                        continue Label_0654_Outer;
                                    }
                                    break;
                                }
                                while (true) {
                                    Label_0687: {
                                        if (cb == 0) {
                                            break Label_0687;
                                        }
                                        if (s3.equals(this.g.dr(array4[n14], 0, this.h))) {
                                            this.h = true;
                                        }
                                        ++n14;
                                    }
                                    if (n14 < n7) {
                                        continue;
                                    }
                                    break;
                                }
                            }
                        }
                    }
                    this.bn = this.size().height;
                    this.scrollinitial();
                    this.getParameter(d("\u001feU\u001a\u001b\u001dt"));
                }
                s6 = (s7 = parameter2);
                Label_0853: {
                    if (cb == 0) {
                        Label_0845: {
                            if (s7 != null) {
                                s8 = s6;
                                if (cb != 0) {
                                    break Label_0853;
                                }
                                if (!s8.equalsIgnoreCase(d(">\\"))) {
                                    this.i = this.a(s6);
                                    anpanorama = this;
                                    if (cb != 0) {
                                        break Label_0845;
                                    }
                                    if (this.i != null) {
                                        s9 = (parameter3 = this.getParameter(d("\u001feU\u001a\u001b\u001dth")));
                                        if (cb == 0) {
                                            if (parameter3 == null) {
                                                s9 = "0";
                                            }
                                            this.j = Integer.valueOf(s9);
                                            this.getParameter(d("\u001feU\u001a\u001b\u001dti"));
                                        }
                                        s10 = (s11 = parameter3);
                                        if (cb != 0 || s11 == null) {
                                            s10 = s11;
                                        }
                                        this.k = Integer.valueOf(s10);
                                    }
                                }
                            }
                            anpanorama = this;
                        }
                        anpanorama.getParameter(d("\u0002vW\u0004\u001b\u001ex"));
                    }
                }
                s12 = (s13 = s7);
                Label_0921: {
                    if (cb == 0) {
                        if (s13 != null) {
                            s14 = s12;
                            if (cb != 0) {
                                break Label_0921;
                            }
                            if (!s14.equalsIgnoreCase(d(">\\"))) {
                                try {
                                    this.a = new URL(this.getDocumentBase(), s12);
                                }
                                catch (MalformedURLException ex6) {
                                    this.a = null;
                                }
                            }
                        }
                        this.getParameter(d("\u0002vW\u0006\u0017\u0007uB\t\u001f\u0015"));
                    }
                }
                s15 = s13;
                if (cb == 0) {
                    if (s15.equalsIgnoreCase(d(")Vc"))) {
                        this.b = true;
                    }
                    this.d = this.getParameter(d("\u0002vW\u000e\u0000\u0011~U\u0006\u0013\u001dv"));
                    this.getParameter(d("=z^;+>P"));
                }
                d2 = (s16 = s15);
                if (cb == 0) {
                    if (s16 == null) {
                        d2 = d("A#");
                    }
                    this.n = Integer.valueOf(d2);
                    this.getParameter(d("\u001dv]\f\u0017\u001crI"));
                }
                s17 = s16;
                parameter4 = this.getParameter(d("\u0000aY\u0007\u0000\u0019gI"));
                this.p = Integer.valueOf(s17);
                this.q = Integer.valueOf(parameter4);
                n15 = (n16 = this.p);
                if (cb == 0) {
                    if (n15 < 0) {
                        this.p = 0;
                    }
                    n16 = (q = this.q);
                }
                n17 = 10;
                Label_1268: {
                    Label_1105: {
                        if (cb == 0) {
                            if (n15 > n17) {
                                this.q = 10;
                                if (cb == 0) {
                                    break Label_1105;
                                }
                            }
                            anpanorama2 = this;
                            if (cb != 0) {
                                break Label_1268;
                            }
                            n16 = this.q;
                        }
                        if (n16 < n17) {
                            this.q = 1;
                        }
                    }
                    this.by = 1;
                    this.bz = false;
                    this.bC = false;
                    this.bL = false;
                    this.bT = false;
                    this.bU = false;
                    this.bW = false;
                    this.bX = false;
                    this.dirty = false;
                    this.bY = false;
                    this.hfov = 70.0;
                    this.hfov_min = 10.0;
                    this.hfov_max = 170.0;
                    this.pitch_max = 90.0;
                    this.pitch_min = -90.0;
                    this.yaw_max = 180.0;
                    this.yaw_min = -180.0;
                    this.cj = 1.0;
                    this.cq = -1;
                    this.cr = -1;
                    this.bz = true;
                    this.repaint();
                    this.bV = false;
                    this.ck = new double[3][3];
                    this.cl = new int[3][3];
                    this.cx = null;
                    this.cy = true;
                    this.a();
                    anpanorama2 = this;
                }
                anpanorama2.a(-1);
                return;
            }
            continue;
        }
    }
    
    public void start() {
        if (this.cv == null) {
            (this.cv = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.cv != null && this.cv.isAlive()) {
            this.cv.stop();
        }
        this.cv = null;
        this.g();
        this.bX = false;
        this.bT = false;
        this.bQ = null;
        this.bD = null;
        if (!this.bL) {
            this.vwidth = 0;
            this.vheight = 0;
        }
        this.bF = null;
    }
    
    void b() {
        this.bT = false;
        this.bU = false;
        this.bR = null;
        this.cn = null;
        this.cp = 0;
        System.gc();
    }
    
    public void destroy() {
        if (this.g != null) {
            this.g.hide();
        }
        this.g = null;
        this.b();
        this.ck = null;
        this.cl = null;
        this.bQ = null;
        this.bJ = null;
        this.bK = null;
        this.bD = null;
        this.bE = null;
        this.cd = null;
        this.ce = null;
        if (this.i != null) {
            this.i.flush();
        }
        this.i = null;
        System.gc();
    }
    
    public synchronized void waitsync() {
        Thread.yield();
        this.r.sync();
        final long n = 10L - (System.currentTimeMillis() - this.o);
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
        this.o = System.currentTimeMillis();
        try {
            Thread.sleep(this.n);
        }
        catch (InterruptedException ex3) {}
    }
    
    public void scrollinitial() {
        this.D = false;
        final String parameter = this.getParameter(d("\u0004vH\u001c\u0001\u0013a_\u0004\u001e"));
        if (parameter != null && !parameter.equalsIgnoreCase(d(">\\"))) {
            String s = this.getParameter(d("\u0004vH\u001c\u0006\tcU"));
            if (s == null) {
                s = d("\u0018|B\u0001\b\u001f}D\t\u001e");
            }
            if (s.equals(d("\u0018|B\u0001\b\u001f}D\t\u001e"))) {
                this.P = 0;
            }
            else if (s.equals(d("\u0006vB\u001c\u001b\u0013r\\"))) {
                this.P = 1;
            }
            else if (s.equals(d("\n|_\u0005\u001b\u001et"))) {
                this.P = 2;
            }
            else if (s.equals(d("\u0019}F\u0012\u001d\u001f~Y\u0006\u0015"))) {
                this.P = 3;
            }
            if (this.P == 0) {
                this.GetTheString(parameter, 0);
                if (this.B != null) {
                    this.D = true;
                }
            }
            else {
                this.GetTheString(parameter, 1);
                if (this.O != null) {
                    this.D = true;
                }
            }
        }
        if (this.D) {
            String parameter2 = this.getParameter(d("\u0004vH\u001c\u0001\u0000vU\f"));
            if (parameter2 == null) {
                parameter2 = "0";
            }
            this.C = Integer.valueOf(parameter2);
            String s2 = this.getParameter(d("\u0004vH\u001c\u0014\u001f}D"));
            if (s2 == null) {
                s2 = d("1aY\t\u001e");
            }
            int n = 0;
            if (this.getParameter(d("\u0004vH\u001c\u0010\u001f\u007fT")).equalsIgnoreCase(d(")Vc"))) {
                ++n;
            }
            String s3 = this.getParameter(d("\u0004vH\u001c\u001b\u0004r\\\u0001\u0011"));
            if (s3 == null) {
                s3 = d(">\\");
            }
            if (s3.equalsIgnoreCase(d(")Vc"))) {
                n += 2;
            }
            String s4 = this.getParameter(d("\u0004vH\u001c\u0001\u0019iU"));
            if (s4 == null) {
                s4 = d("A!");
            }
            this.H = new Font(s2, n, Integer.valueOf(s4));
            if (this.getParameter(d("\u0004vH\u001c\u0001\u0018rT\u0007\u0005")).equalsIgnoreCase(d(")Vc"))) {
                this.E = true;
            }
            else {
                this.E = false;
            }
            this.F = new Color(Integer.valueOf(this.getParameter(d("$vH\u001c1\u001f\u007fb"))), Integer.valueOf(this.getParameter(d("$vH\u001c1\u001f\u007fw"))), Integer.valueOf(this.getParameter(d("$vH\u001c1\u001f\u007fr"))));
            this.G = new Color(Integer.valueOf(this.getParameter(d("$vH\u001c!3|\\:"))), Integer.valueOf(this.getParameter(d("$vH\u001c!3|\\/"))), Integer.valueOf(this.getParameter(d("$vH\u001c!3|\\*"))));
            this.t = this.size().width;
            this.u = this.size().height;
            if (this.P == 0) {
                String parameter3 = this.getParameter(d("\u0004vH\u001c\u001d\u0016uC\r\u0006"));
                if (parameter3 == null) {
                    parameter3 = "0";
                }
                this.w = Integer.valueOf(parameter3);
                if (this.w < 0) {
                    this.w = 0;
                }
                String parameter4 = this.getParameter(d("$vH\u001c8\u0005~@)\u001f\u0000"));
                if (parameter4 == null) {
                    parameter4 = "0";
                }
                this.U = Integer.valueOf(parameter4);
                String parameter5 = this.getParameter(d("$vH\u001c8\u0005~@;\u0002\u0014"));
                if (parameter5 == null) {
                    parameter5 = "0";
                }
                this.X = Integer.valueOf(parameter5);
                String parameter6 = this.getParameter(d("$vH\u001c!\u0019}U)\u001f\u0000"));
                if (parameter6 == null) {
                    parameter6 = "0";
                }
                this.I = Integer.valueOf(parameter6);
                String parameter7 = this.getParameter(d("$vH\u001c!\u0019}U;\u0002\u0014"));
                if (parameter7 == null) {
                    parameter7 = "0";
                }
                this.J = Integer.valueOf(parameter7);
                String parameter8 = this.getParameter(d("$vH\u001c!\u0019}U)\u001c\u0017\u007fU"));
                if (parameter8 == null) {
                    parameter8 = "0";
                }
                this.K = Integer.valueOf(parameter8);
                final FontMetrics fontMetrics = this.getGraphics().getFontMetrics(this.H);
                this.x = fontMetrics.stringWidth(this.B);
                this.y = fontMetrics.getHeight();
                this.z = fontMetrics.getMaxDescent();
                this.v = this.t;
                if (this.w < this.y - this.z) {
                    this.w = this.y - this.z;
                }
                else if (this.w > this.u - this.z) {
                    this.w = this.u - this.z;
                }
                if (this.I != 0) {
                    this.M = new int[this.t + 360];
                    this.N = new int[this.t + 360];
                    for (int i = 0; i < this.t + 360; ++i) {
                        this.M[i] = (int)(this.I * Math.sin(this.K * i * 3.141592653589793 / 180.0)) - this.y - this.z + this.w;
                        this.N[i] = this.M[i] - this.bn;
                    }
                    this.L = 360;
                    this.bf = this.y + this.z + 1;
                    this.bg = this.bf - 1;
                }
            }
            else {
                if (this.P == 1) {
                    String s5 = this.getParameter(d("\u0004vH\u001c\u0004\u0003cQ\u000b\u0017"));
                    if (s5 == null) {
                        s5 = d("A#");
                    }
                    final int intValue = Integer.valueOf(s5);
                    final FontMetrics fontMetrics2 = this.getGraphics().getFontMetrics(this.H);
                    this.Z = fontMetrics2.getHeight() + intValue;
                    this.ba = new int[this.O.length];
                    this.A = 0;
                    while (this.A < this.O.length) {
                        this.ba[this.A] = (this.t - fontMetrics2.stringWidth(this.O[this.A])) / 2;
                        ++this.A;
                    }
                    this.Y = -this.Z;
                    return;
                }
                if (this.P >= 2) {
                    String parameter9 = this.getParameter(d("\u0004vH\u001c\u001f\u0019}V\u0007\u001c\u0004"));
                    if (parameter9 == null) {
                        parameter9 = "2";
                    }
                    this.S = Integer.valueOf(parameter9);
                    String s6 = this.getParameter(d("\u0004vH\u001c\u001f\u0011kV\u0007\u001c\u0004"));
                    if (s6 == null) {
                        s6 = d("G!");
                    }
                    this.T = Integer.valueOf(s6);
                    this.Q = this.T - this.S;
                    this.H = null;
                    this.R = new Font[this.Q];
                    int s7 = this.S;
                    this.A = 0;
                    while (this.A < this.Q) {
                        this.R[this.A] = new Font(s2, n, s7++);
                        ++this.A;
                    }
                    this.bd = this.t / 2.0f;
                    this.be = this.u / 2.0f;
                    if (this.P == 3) {
                        this.bb = this.Q - 1;
                        return;
                    }
                    this.bb = 0;
                }
            }
        }
    }
    
    public void scrolltext(final Graphics graphics) {
        switch (this.P) {
            case 0: {
                this.horizscroll(graphics);
            }
            case 1: {
                this.vertscroll(graphics);
            }
            default: {
                this.zoomscroll(graphics);
            }
        }
    }
    
    public void vertscroll(final Graphics graphics) {
        graphics.setFont(this.H);
        this.Y += this.C;
        if (this.Y > this.u + this.O.length * this.Z) {
            this.Y = -this.Z;
        }
        if (this.E) {
            for (int i = 0; i < this.O.length; ++i) {
                final String s = this.O[i];
                final int n = this.ba[i];
                final int n2 = this.u - this.Y + i * this.Z;
                graphics.setColor(this.G);
                graphics.drawString(s, n + 1, n2 + 1);
                graphics.setColor(this.F);
                graphics.drawString(s, n, n2);
            }
            return;
        }
        graphics.setColor(this.F);
        for (int j = 0; j < this.O.length; ++j) {
            graphics.drawString(this.O[j], this.ba[j], this.u - this.Y + j * this.Z);
        }
    }
    
    public void zoomscroll(final Graphics graphics) {
        final String s = this.O[this.bc];
        graphics.setFont(this.R[this.bb]);
        final FontMetrics fontMetrics = graphics.getFontMetrics(this.R[this.bb]);
        final int n = (int)(this.bd - fontMetrics.stringWidth(s) / 2.0f);
        final int n2 = (int)(this.be + fontMetrics.getHeight() / 4.0f);
        if (this.E) {
            graphics.setColor(this.G);
            graphics.drawString(s, n + 1, n2 + 1);
        }
        graphics.setColor(this.F);
        graphics.drawString(s, n, n2);
        if (this.P == 3) {
            this.bb -= this.C;
            if (this.bb <= 1) {
                this.bb = this.Q - 1;
                ++this.bc;
                if (this.bc >= this.O.length) {
                    this.bc = 0;
                }
            }
        }
        else {
            this.bb += this.C;
            if (this.bb >= this.Q) {
                this.bb = 0;
                ++this.bc;
                if (this.bc >= this.O.length) {
                    this.bc = 0;
                }
            }
        }
    }
    
    public void horizscroll(final Graphics graphics) {
        graphics.setFont(this.H);
        if (this.U == 0) {
            this.V = this.w;
        }
        else {
            this.W += this.X;
            this.V = this.w - (int)Math.abs(this.U * Math.sin(this.W / 90.0 * 3.141592653589793));
        }
        if (this.I != 0) {
            for (int i = 0; i < this.t; ++i) {
                final int n = this.M[this.L + i];
                graphics.copyArea(i, n, 1, this.bf, 0, this.bn - n);
            }
            if (this.E) {
                graphics.setColor(this.G);
                graphics.drawString(this.B, this.v + 1, this.bn + this.y + 1);
            }
            graphics.setColor(this.F);
            graphics.drawString(this.B, this.v, this.bn + this.y);
            for (int j = 0; j < this.t; ++j) {
                graphics.copyArea(j, this.bn, 1, this.bg, 0, this.N[this.L + j]);
            }
            this.L -= this.J;
            if (this.L < 0) {
                this.L += 360;
            }
        }
        else {
            if (this.E) {
                graphics.setColor(this.G);
                graphics.drawString(this.B, this.v + 1, this.V + 1);
            }
            graphics.setColor(this.F);
            graphics.drawString(this.B, this.v, this.V);
        }
        this.v -= this.C;
        if (this.v < -this.x) {
            this.v = this.t;
        }
    }
    
    public void GetTheString(final String s, final int n) {
        try {
            this.GetTheString1(s, n);
        }
        catch (NoSuchMethodError noSuchMethodError) {
            this.GetTheString1(s, n);
        }
    }
    
    public void GetTheString1(final String s, final int n) {
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
                            this.B = new String(byteArray);
                            return;
                        }
                        catch (NoSuchMethodError noSuchMethodError) {
                            this.B = new String(byteArray, 0);
                            return;
                        }
                    }
                    int n3 = 1;
                    for (int j = 0; j < n2; ++j) {
                        if (byteArray[j] == 10) {
                            ++n3;
                        }
                    }
                    this.O = new String[n3 - 1];
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
                                this.O[l] = new String(byteArray, array2[l], array3[l]);
                            }
                            catch (NoSuchMethodError noSuchMethodError2) {
                                this.O[l] = new String(byteArray, 0, array2[l], array3[l]);
                            }
                        }
                    }
                    catch (StringIndexOutOfBoundsException ex) {
                        this.O = null;
                    }
                }
                catch (IOException ex2) {}
            }
            catch (IOException ex3) {}
        }
        catch (MalformedURLException ex4) {}
    }
    
    public synchronized boolean CheckAniGIF() {
        this.prepareImage(this.i, this);
        if (this.m) {
            for (int i = 0; i < 3; ++i) {
                this.notifyAll();
                Thread.yield();
                try {
                    Thread.sleep(100L);
                }
                catch (InterruptedException ex) {}
            }
            return this.l;
        }
        return false;
    }
    
    public synchronized void prepaniframe() {
        if (this.bh) {
            this.notifyAll();
            while (!this.l) {
                Thread.yield();
                try {
                    Thread.sleep(8L);
                }
                catch (InterruptedException ex) {}
            }
            this.l = false;
        }
        this.bG.drawImage(this.i, this.j, this.k, this);
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if (image == this.i) {
            if (n == 16) {
                this.l = true;
            }
            return true;
        }
        return true;
    }
    
    public final void producefixed() {
        try {
            if (this.m) {
                this.bJ.newPixels();
                return;
            }
            this.bK.startProduction(this.bK.getConsumer());
        }
        catch (NoSuchMethodError noSuchMethodError) {}
    }
    
    void a(final int n, final int n2) {
        this.bJ = new MemoryImageSource(n, n2, this.bQ, 0, n);
        String s;
        try {
            s = System.getProperty(d("\u001arF\t\\\u0006vB\u001b\u001b\u001f}"));
        }
        catch (SecurityException ex) {
            s = d("\u0005}[");
        }
        if (!s.startsWith(d("A=\u0000"))) {
            try {
                this.bJ.setAnimated(true);
                this.bJ.setFullBufferUpdates(true);
                this.m = true;
            }
            catch (NoSuchMethodError noSuchMethodError) {
                this.m = false;
            }
        }
        if (!this.m) {
            this.bJ = null;
            this.bK = new anfy(n, n2, new DirectColorModel(24, 16711680, 65280, 255), this.bQ, 0, n);
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
                this.showStatus(d("9~Q\u000f\u0017P") + s + d("P}_\u001cR\u0016|E\u0006\u0016Q"));
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
    
    private final void c() {
        while (true) {
            this.showStatus(d("4|^O\u0006PaU\u0005\u001d\u0006v\u0010\u001f\u0005\u0007=Q\u0006\u0014\tgU\t\u001f^p_\u0005R\u0013aU\f\u001b\u0004`\u0010\u0004\u001b\u001ev\u0010\u0001\u001cP[d%>Q"));
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
    
    void d() {
        Image a = null;
        if (this.cn != null) {
            a = this.a(this.cn);
        }
        if (a == null) {
            this.bV = true;
            return;
        }
        this.bO = a.getWidth(null);
        this.bP = a.getHeight(null);
        try {
            this.bR = new int[this.bP][this.bO];
        }
        catch (Exception ex) {
            this.bR = null;
            this.bV = true;
            return;
        }
        final int bp = this.bP;
        final int[] array = new int[bp * this.bO];
        for (int i = 0; i < this.bP; i += bp) {
            final int n = (bp >= this.bP - i) ? (this.bP - i) : bp;
            final PixelGrabber pixelGrabber = new PixelGrabber(a, 0, i, this.bO, n, array, 0, this.bO);
            try {
                pixelGrabber.grabPixels();
            }
            catch (InterruptedException ex2) {
                this.bV = true;
                this.bR = null;
                return;
            }
            for (int j = 0; j < n; ++j) {
                for (int k = 0; k < this.bO; ++k) {
                    this.bR[j + i][k] = (array[j * this.bO + k] | 0xFF000000);
                }
            }
        }
        System.gc();
        this.bU = true;
    }
    
    public void run() {
        this.repaint();
        if (!this.bU) {
            this.d();
        }
        this.repaint();
        this.g.dr(d("\u0011}V\u0011"), 1, this.h);
        this.cv.setPriority(this.q);
        this.showStatus("");
        System.gc();
        this.o = System.currentTimeMillis();
        if (this.i != null && !this.bh) {
            this.bh = this.CheckAniGIF();
        }
        if (this.a != null) {
            this.f.setCursor(12);
        }
        else {
            this.f.setCursor(0);
        }
        if (this.bR == null) {
            this.bV = true;
            this.repaint();
            return;
        }
        if (this.bQ == null) {
            if (this.vwidth == 0) {
                this.vwidth = this.size().width;
            }
            if (this.vheight == 0) {
                this.vheight = this.size().height;
            }
            int vwidth = this.vwidth;
            if (vwidth % 16 != 0) {
                vwidth = (vwidth / 16 + 1) * 16;
            }
            this.bQ = new int[vwidth * this.vheight];
            try {
                this.a(vwidth, this.vheight);
            }
            catch (NoSuchMethodError noSuchMethodError) {
                this.a(vwidth, this.vheight);
            }
            this.producefixed();
        }
        if (this.bP != this.bO / 2 || this.yaw_max != 180.0 || this.yaw_min != -180.0) {
            final double pitch_max = this.bP / this.bO * (this.yaw_max - this.yaw_min) / 2.0;
            if (this.pitch_max > pitch_max) {
                this.pitch_max = pitch_max;
            }
            if (this.pitch_min < -pitch_max) {
                this.pitch_min = -pitch_max;
            }
        }
        this.cm = this.a(this.hfov);
        while (2.0 * this.cm > this.pitch_max - this.pitch_min) {
            this.hfov /= 1.03;
            this.cm = this.a(this.hfov);
        }
        if (this.pitch > this.pitch_max - this.cm && this.pitch_max != 90.0) {
            this.pitch = 0.0;
        }
        if (this.pitch < this.pitch_min + this.cm && this.pitch_min != -90.0) {
            this.pitch = 0.0;
        }
        this.e();
        if (this.ch != 0.0) {
            this.ct = this.cs + 100000000L;
        }
        this.f();
        if (this.bD == null) {
            try {
                if (this.m) {
                    this.bD = this.createImage(this.bJ);
                }
                else {
                    this.bD = this.createImage(this.bK);
                }
            }
            catch (NoSuchMethodError noSuchMethodError2) {}
        }
        this.bT = true;
        this.paint(this.getGraphics());
    }
    
    double a(final double n) {
        return 57.29577951308232 * Math.atan(this.vheight / this.vwidth * Math.tan(n / 2.0 * 3.141592653589793 / 180.0));
    }
    
    public boolean mouseDown(final Event event, final int cb, final int cc) {
        final int cb2 = anpanorama.cB;
        anpanorama anpanorama = this;
        anpanorama anpanorama2 = this;
        Label_0143: {
            Label_0060: {
                if (cb2 != 0) {
                    break Label_0060;
                }
                if (!this.h) {
                    this.g.show();
                    try {
                        this.g.move(100, 100);
                    }
                    catch (Exception ex) {}
                    this.g.toFront();
                    this.g.requestFocus();
                    if (cb2 == 0) {
                        break Label_0060;
                    }
                }
                try {
                    anpanorama = this;
                    anpanorama2 = this;
                    if (cb2 != 0) {
                        break Label_0143;
                    }
                    if (anpanorama2.a != null) {
                        try {
                            this.g.dck();
                            anpanorama anpanorama3 = this;
                            if (cb2 == 0) {
                                if (this.b) {
                                    this.getAppletContext().showDocument(this.a, this.getParameter(d("\u0002vW\u000e\u0000\u0011~U\u0006\u0013\u001dv")));
                                    if (cb2 == 0) {
                                        break Label_0060;
                                    }
                                }
                                anpanorama3 = this;
                            }
                            anpanorama3.getAppletContext().showDocument(this.a);
                        }
                        catch (Exception ex2) {}
                    }
                }
                catch (Exception ex3) {}
            }
            anpanorama = this;
        }
        final boolean bk = anpanorama.bk;
        if (cb2 == 0) {
            if (bk) {
                anpanorama anpanorama4 = this;
                Label_0183: {
                    if (cb2 == 0) {
                        if (this.bl) {
                            this.bl = false;
                            if (cb2 == 0) {
                                break Label_0183;
                            }
                        }
                        anpanorama4 = this;
                    }
                    anpanorama4.bl = true;
                }
                this.bk = false;
            }
            final boolean bw;
            final boolean b = bw = this.bW;
            if (cb2 != 0) {
                return b;
            }
        }
        if (bk) {
            this.bX = true;
            final boolean bj = this.bj;
            Label_0281: {
                anpanorama anpanorama5 = null;
                Label_0275: {
                    if (cb2 == 0) {
                        if (!bj) {
                            if (event.modifiers == 4) {
                                this.cj = 1.03;
                                if (cb2 == 0) {
                                    break Label_0281;
                                }
                            }
                            this.cj = 0.970873786407767;
                            if (cb2 == 0) {
                                break Label_0281;
                            }
                        }
                        anpanorama5 = this;
                        if (cb2 != 0) {
                            break Label_0275;
                        }
                        final boolean bl = this.bl;
                    }
                    if (bj) {
                        this.cj = 1.03;
                        if (cb2 == 0) {
                            break Label_0281;
                        }
                    }
                    anpanorama5 = this;
                }
                anpanorama5.cj = 0.970873786407767;
            }
            this.repaint();
        }
        this.cb = cb;
        this.cc = cc;
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int cb, final int cc) {
        this.cb = cb;
        this.cc = cc;
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        this.bk = true;
        this.cb = n;
        this.cc = n2;
        this.cj = 1.0;
        if (this.bT) {
            this.cq = n;
            this.cr = n2;
        }
        return true;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        if (this.a != null) {
            this.f.setCursor(12);
        }
        else {
            this.f.setCursor(0);
        }
        this.showStatus(this.e);
        this.bW = true;
        return this.bX = true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.showStatus("");
        this.bW = false;
        this.bX = false;
        this.cj = 1.0;
        return true;
    }
    
    public boolean keyDown(final Event event, final int n) {
        if (!this.bT) {
            return true;
        }
        switch (n) {
            case 1004: {
                this.d(5.0);
                break;
            }
            case 1005: {
                this.e(5.0);
                break;
            }
            case 1006: {
                this.f(5.0);
                break;
            }
            case 1007: {
                this.g(5.0);
                break;
            }
            case 43: {
                this.b(1.03);
                break;
            }
            case 45: {
                this.c(1.03);
                break;
            }
        }
        return true;
    }
    
    public boolean mouseMove(final Event event, final int cb, final int cc) {
        this.showStatus(this.e);
        this.cb = cb;
        this.cc = cc;
        this.repaint();
        return true;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (!this.bz) {
            return;
        }
        if (this.bV) {
            return;
        }
        if (this.bF == null) {
            if (!this.bL || this.bH == 0) {
                this.bH = this.size().width;
                this.bI = this.size().height;
            }
            this.bF = this.createImage(this.bH, this.bI);
            this.bG = this.bF.getGraphics();
        }
        if (!this.bT || System.currentTimeMillis() < this.cu) {
            if (this.bE != null) {
                if (this.bS != null && !this.bC) {
                    this.setBackground(this.bS);
                    this.bG.setColor(this.bS);
                    this.bG.fillRect(0, 0, this.bH, this.bI);
                }
                if (!this.bC) {
                    if (this.bA != 0L) {
                        this.cu = System.currentTimeMillis() + this.bA;
                    }
                    this.bC = true;
                }
                this.bG.drawImage(this.bE, (this.bH - this.bE.getWidth(null)) / 2, (this.bI - this.bE.getHeight(null)) / 2, this);
                if (this.i != null) {
                    this.prepaniframe();
                }
                graphics.drawImage(this.bF, 0, 0, this);
                if (this.bT) {
                    if (++this.s == this.p) {
                        System.gc();
                        this.s = 0;
                    }
                    this.a(this.getGraphics());
                }
            }
            else {
                if (this.bS != null) {
                    this.setBackground(this.bS);
                }
                graphics.setColor(this.bS);
                graphics.fillRect(0, 0, this.size().width, this.size().height);
            }
            return;
        }
        if (this.bX) {
            final double n = 5.0E-4 * this.hfov / 70.0 * 320.0 / this.vwidth;
            this.b(this.yaw + n * ((this.cb - this.bZ) * (this.cb - this.bZ)) * ((this.cb <= this.bZ) ? -1.0 : 1.0), this.pitch + n * ((this.ca - this.cc) * (this.ca - this.cc)) * ((this.ca <= this.cc) ? -1.0 : 1.0), this.hfov * this.cj);
        }
        if (this.ct > this.cs && !this.bW) {
            this.b(this.yaw + this.ch, this.pitch + this.ci, this.hfov * this.cj);
        }
        if (++this.s == this.p) {
            System.gc();
            this.s = 0;
        }
        if (this.dirty) {
            if (this.ct > this.cs) {
                System.currentTimeMillis();
            }
            this.bB = System.currentTimeMillis();
            this.f();
        }
        this.a(graphics);
    }
    
    final void a(final Graphics graphics) {
        this.producefixed();
        this.bG.drawImage(this.bD, this.bM, this.bN, this);
        if (this.D) {
            this.scrolltext(this.bG);
        }
        if (this.i != null) {
            this.prepaniframe();
        }
        graphics.drawImage(this.bF, 0, 0, this);
    }
    
    void e() {
        if (this.cd == null) {
            this.cd = new int[4097];
            this.ce = new int[4097];
            final double n = 2.44140625E-4;
            double n2 = 0.0;
            for (int i = 0; i < 4096; this.ce[i++] = (int)(Math.sqrt(1.0 + n2 * n2) * 4096.0), n2 += n) {}
            this.ce[4096] = (int)(Math.sqrt(2.0) * 4096.0);
        }
        final double n3 = 2.44140625E-4;
        double n4 = 0.0;
        final double n5 = this.bO / 6.283185307179586;
        this.cf = this.bO * 64;
        this.cg = 128 * this.bO;
        for (int j = 0; j < 4097; ++j, n4 += n3) {
            final double n6 = n5 * Math.atan(n4 / (1.0 - n4));
            if (j < 4096) {
                this.cd[j] = (int)(n6 * 256.0 + 0.5);
                final int n7 = (int)(n6 + 0.5);
            }
            else {
                this.cd[j] = (int)(n5 * 3.141592653589793 / 2.0 * 256.0 + 0.5);
                final int n8 = (int)(n5 * 3.141592653589793 / 2.0 + 0.5);
            }
        }
    }
    
    void f() {
        final double n = this.hfov * 2.0 * 3.141592653589793 / 360.0;
        final double n2 = this.vwidth / (2.0 * Math.tan(n / 2.0));
        this.a(this.pitch * 2.0 * 3.141592653589793 / 360.0, this.yaw * 2.0 * 3.141592653589793 / 360.0, this.ck, 1);
        final double[] array = this.ck[0];
        final int n3 = 0;
        array[n3] /= n2;
        final double[] array2 = this.ck[0];
        final int n4 = 1;
        array2[n4] /= n2;
        final double[] array3 = this.ck[0];
        final int n5 = 2;
        array3[n5] /= n2;
        final double[] array4 = this.ck[1];
        final int n6 = 0;
        array4[n6] /= n2;
        final double[] array5 = this.ck[1];
        final int n7 = 1;
        array5[n7] /= n2;
        final double[] array6 = this.ck[1];
        final int n8 = 2;
        array6[n8] /= n2;
        final double n9 = (n <= 0.3) ? 436906.6666666667 : (131072.0 / n);
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                this.cl[i][j] = (int)(n9 * this.ck[i][j] + 0.5);
            }
        }
        switch (this.by) {
            case 0: {
                this.a(this.cl, false);
                this.dirty = false;
                break;
            }
            case 1: {
                if (this.bX || this.ct > this.cs) {
                    this.a(this.cl, false);
                    break;
                }
                this.a(this.cl, true);
                System.gc();
                this.dirty = false;
                break;
            }
            case 2: {
                if (this.bX) {
                    this.a(this.cl, false);
                    break;
                }
                this.a(this.cl, true);
                System.gc();
                this.dirty = false;
                break;
            }
            case 3: {
                this.a(this.cl, true);
                this.dirty = false;
                break;
            }
        }
        ++this.cs;
    }
    
    void a(int n, int n2, final int[] array, final double n3, final double n4) {
        final double n5 = this.bO / 6.283185307179586;
        final double n6 = (int)(this.vwidth / (2.0 * Math.tan(this.hfov * 2.0 * 3.141592653589793 / 360.0 / 2.0)) + 0.5);
        this.a(n4 * 2.0 * 3.141592653589793 / 360.0, n3 * 2.0 * 3.141592653589793 / 360.0, this.ck, 1);
        n -= this.vwidth / 2;
        n2 -= this.vheight / 2;
        final double n7 = this.ck[0][0] * n + this.ck[1][0] * n2 + this.ck[2][0] * n6;
        final double n8 = this.ck[0][1] * n + this.ck[1][1] * n2 + this.ck[2][1] * n6;
        final double n9 = this.ck[0][2] * n + this.ck[1][2] * n2 + this.ck[2][2] * n6;
        double n10 = n5 * Math.atan2(n7, n9) + this.bO / 2.0;
        if (n10 < 0.0) {
            n10 = 0.0;
        }
        if (n10 >= this.bO) {
            n10 = this.bO - 1;
        }
        array[0] = (int)n10;
        double n11 = n5 * Math.atan2(n8, Math.sqrt(n9 * n9 + n7 * n7)) + this.bP / 2.0;
        if (n11 < 0.0) {
            n11 = 0.0;
        }
        if (n11 >= this.bP) {
            n11 = this.bP - 1;
        }
        array[1] = (int)n11;
    }
    
    void a(final int n, final int n2, final int[] array) {
        this.a(n, n2, array, this.yaw, this.pitch);
    }
    
    void a(final int[][] array, final boolean b) {
        final int n = this.bO - 1;
        final int n2 = this.bP - 1;
        final int n3 = (this.vwidth - 1) / 2;
        final int n4 = this.vheight / 2;
        final int n5 = this.bO / 2;
        final int n6 = this.bP / 2;
        final int n7 = -n3;
        final int n8 = this.vwidth - n3;
        final int n9 = -n4;
        final int n10 = this.vheight - n4;
        int n11 = 0;
        if (this.cn == null) {
            return;
        }
        int n12 = array[1][0] * n9 + array[2][0] + array[0][0] * n7;
        int n13 = array[1][1] * n9 + array[2][1];
        int n14 = array[1][2] * n9 + array[2][2] + array[0][2] * n7;
        final int n15 = array[0][0];
        final int n16 = array[0][2];
        final int n17 = array[1][0];
        final int n18 = array[1][1];
        final int n19 = array[1][2];
        System.gc();
        if (!b) {
            for (int i = n9; i < n10; ++i, n12 += n17, n13 += n18, n14 += n19) {
                int n20 = n12;
                int n21 = n14;
                int b2 = this.b(n20, n21);
                int b3 = this.b(n13, this.c(Math.abs(n21), Math.abs(n20)));
                for (int j = n7; j < n8; j += 16) {
                    n20 += n15 << 4;
                    n21 += n16 << 4;
                    int n22;
                    if (n21 > 0) {
                        if (n20 > 0) {
                            n22 = this.cd[(n20 << 12) / (n20 + n21)];
                        }
                        else {
                            n22 = -this.cd[(n20 << 12) / (n20 - n21)];
                        }
                    }
                    else if (n20 < 0) {
                        n22 = this.cd[(n20 << 12) / (n20 + n21)] - this.cg;
                    }
                    else {
                        n22 = -this.cd[(n20 << 12) / (n20 - n21)] + this.cg;
                    }
                    final int abs = Math.abs(n21);
                    final int abs2 = Math.abs(n20);
                    int n23;
                    if (abs > abs2) {
                        n23 = abs * this.ce[(abs2 << 12) / abs] >> 12;
                    }
                    else {
                        n23 = abs2 * this.ce[(abs << 12) / abs2] >> 12;
                    }
                    int n24;
                    if (n23 > 0) {
                        if (n13 > 0) {
                            n24 = this.cd[(n13 << 12) / (n13 + n23)];
                        }
                        else {
                            n24 = -this.cd[(n13 << 12) / (n13 - n23)];
                        }
                    }
                    else if (n13 < 0) {
                        n24 = this.cd[(n13 << 12) / (n13 + n23)] - this.cg;
                    }
                    else {
                        n24 = -this.cd[(n13 << 12) / (n13 - n23)] + this.cg;
                    }
                    final int n25 = n22 - b2 >> 4;
                    final int n26 = n24 - b3 >> 4;
                    if (n25 < 768 && n25 > -768) {
                        for (int k = 0; k < 16; ++k) {
                            int n27 = (b2 >> 8) + n5;
                            int n28 = (b3 >> 8) + n6;
                            b2 += n25;
                            b3 += n26;
                            if (n27 > n) {
                                n27 = n;
                            }
                            if (n28 < 0) {
                                n28 = 0;
                            }
                            if (n28 > n2) {
                                n28 = n2;
                            }
                            this.bQ[n11++] = this.bR[n28][n27];
                        }
                    }
                    else {
                        n20 -= n15 << 4;
                        n21 -= n16 << 4;
                        for (int l = 0; l < 16; ++l) {
                            int n29 = (this.b(n20, n21) >> 8) + n5;
                            final int n30 = (b3 >> 8) + n6;
                            b3 += n26;
                            n20 += n15;
                            n21 += n16;
                            if (n29 > n) {
                                n29 = n;
                            }
                            this.bQ[n11++] = this.bR[n30][n29];
                        }
                    }
                    b2 = n22;
                    b3 = n24;
                }
            }
            return;
        }
        for (int n31 = n9; n31 < n10; ++n31, n12 += n17, n13 += n18, n14 += n19) {
            int n32 = n12;
            int n33 = n14;
            int b4 = this.b(n32, n33);
            int b5 = this.b(n13, this.c(Math.abs(n33), Math.abs(n32)));
            for (int n34 = n7; n34 < n8; n34 += 16) {
                n32 += n15 << 4;
                n33 += n16 << 4;
                int n35;
                if (n33 > 0) {
                    if (n32 > 0) {
                        n35 = this.cd[(n32 << 12) / (n32 + n33)];
                    }
                    else {
                        n35 = -this.cd[(n32 << 12) / (n32 - n33)];
                    }
                }
                else if (n32 < 0) {
                    n35 = this.cd[(n32 << 12) / (n32 + n33)] - this.cg;
                }
                else {
                    n35 = -this.cd[(n32 << 12) / (n32 - n33)] + this.cg;
                }
                final int abs3 = Math.abs(n33);
                final int abs4 = Math.abs(n32);
                int n36;
                if (abs3 > abs4) {
                    n36 = abs3 * this.ce[(abs4 << 12) / abs3] >> 12;
                }
                else {
                    n36 = abs4 * this.ce[(abs3 << 12) / abs4] >> 12;
                }
                int n37;
                if (n36 > 0) {
                    if (n13 > 0) {
                        n37 = this.cd[(n13 << 12) / (n13 + n36)];
                    }
                    else {
                        n37 = -this.cd[(n13 << 12) / (n13 - n36)];
                    }
                }
                else if (n13 < 0) {
                    n37 = this.cd[(n13 << 12) / (n13 + n36)] - this.cg;
                }
                else {
                    n37 = -this.cd[(n13 << 12) / (n13 - n36)] + this.cg;
                }
                final int n38 = n35 - b4 >> 4;
                final int n39 = n37 - b5 >> 4;
                if (n38 < 768 && n38 > -768) {
                    for (int n40 = 0; n40 < 16; ++n40) {
                        int n41 = (b4 >> 8) + n5;
                        int n42 = (b5 >> 8) + n6;
                        if (n41 > n - 1) {
                            n41 = n - 1;
                        }
                        if (n42 < 0) {
                            n42 = 0;
                        }
                        if (n42 > n2 - 1) {
                            n42 = n2 - 1;
                        }
                        final int n43 = b4 & 0xFF;
                        final int n44 = b5 & 0xFF;
                        final int n45 = this.bR[n42][n41];
                        final int n46 = n45 & 0xFF00;
                        final int n47 = n45 & 0xFF00FF;
                        final int n48 = this.bR[n42 + 1][n41];
                        final int n49 = n47 + (n44 * ((n48 & 0xFF00FF) - n47) >>> 8) & 0xFF00FF;
                        final int n50 = n46 + (n44 * ((n48 & 0xFF00) - n46) >>> 8) & 0xFF00;
                        final int n51 = this.bR[n42][n41 + 1];
                        final int n52 = n51 & 0xFF00;
                        final int n53 = n51 & 0xFF00FF;
                        final int n54 = this.bR[n42 + 1][n41 + 1];
                        this.bQ[n11++] = ((n49 + (n43 * ((n53 + (n44 * ((n54 & 0xFF00FF) - n53) >> 8) & 0xFF00FF) - n49) >> 8) & 0xFF00FF) | (n50 + (n43 * ((n52 + (n44 * ((n54 & 0xFF00) - n52) >> 8) & 0xFF00) - n50) >> 8) & 0xFF00) | 0xFF000000);
                        b4 += n38;
                        b5 += n39;
                    }
                }
                else {
                    n32 -= n15 << 4;
                    n33 -= n16 << 4;
                    for (int n55 = 0; n55 < 16; ++n55) {
                        final int b6 = this.b(n32, n33);
                        int n56 = (b6 >> 8) + n5;
                        int n57 = (b5 >> 8) + n6;
                        n32 += n15;
                        n33 += n16;
                        if (n56 > n) {
                            n56 = n;
                        }
                        if (n56 < 0) {
                            n56 += n;
                        }
                        if (n57 < 0) {
                            n57 = 0;
                        }
                        if (n57 > n2 - 1) {
                            n57 = n2 - 1;
                        }
                        final int n58 = n56;
                        final int n59 = b6 & 0xFF;
                        final int n60 = b5 & 0xFF;
                        final int n61 = this.bR[n57][n56];
                        final int n62 = n61 & 0xFF00;
                        final int n63 = n61 & 0xFF00FF;
                        if (n56 >= n) {
                            n56 = -1;
                        }
                        final int n64 = this.bR[n57][n56 + 1];
                        final int n65 = this.bR[n57 + 1][n58];
                        final int n66 = n63 + (n60 * ((n65 & 0xFF00FF) - n63) >>> 8) & 0xFF00FF;
                        final int n67 = n62 + (n60 * ((n65 & 0xFF00) - n62) >>> 8) & 0xFF00;
                        final int n68 = n64 & 0xFF00;
                        final int n69 = n64 & 0xFF00FF;
                        final int n70 = this.bR[n57 + 1][n56 + 1];
                        this.bQ[n11++] = ((n66 + (n59 * ((n69 + (n60 * ((n70 & 0xFF00FF) - n69) >> 8) & 0xFF00FF) - n66) >> 8) & 0xFF00FF) | (n67 + (n59 * ((n68 + (n60 * ((n70 & 0xFF00) - n68) >> 8) & 0xFF00) - n67) >> 8) & 0xFF00) | 0xFF000000);
                        b5 += n39;
                    }
                }
                b4 = n35;
                b5 = n37;
            }
        }
    }
    
    int b(final int n, final int n2) {
        if (n2 > 0) {
            if (n > 0) {
                return this.cd[(n << 12) / (n2 + n)];
            }
            return -this.cd[(-n << 12) / (n2 - n)];
        }
        else if (n2 == 0) {
            if (n > 0) {
                return this.cf;
            }
            return -this.cf;
        }
        else {
            if (n < 0) {
                return this.cd[(n << 12) / (n2 + n)] - this.cg;
            }
            return -this.cd[(-n << 12) / (n2 - n)] + this.cg;
        }
    }
    
    final int c(final int n, final int n2) {
        if (n > n2) {
            return n * this.ce[(n2 << 12) / n] >> 12;
        }
        if (n2 == 0) {
            return 0;
        }
        return n2 * this.ce[(n << 12) / n2] >> 12;
    }
    
    void a(final double n, final double n2, final double[][] array, final int n3) {
        final double[][] array2 = new double[3][3];
        final double[][] array3 = new double[3][3];
        array2[0][0] = 1.0;
        array2[0][1] = 0.0;
        array2[0][2] = 0.0;
        array2[1][0] = 0.0;
        array2[1][1] = Math.cos(n);
        array2[1][2] = Math.sin(n);
        array2[2][0] = 0.0;
        array2[2][1] = -array2[1][2];
        array2[2][2] = array2[1][1];
        array3[0][0] = Math.cos(n2);
        array3[0][1] = 0.0;
        array3[0][2] = -Math.sin(n2);
        array3[1][0] = 0.0;
        array3[1][1] = 1.0;
        array3[1][2] = 0.0;
        array3[2][0] = -array3[0][2];
        array3[2][1] = 0.0;
        array3[2][2] = array3[0][0];
        if (n3 == 1) {
            this.a(array2, array3, array);
            return;
        }
        this.a(array3, array2, array);
    }
    
    void a(final double[][] array, final double[][] array2, final double[][] array3) {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                array3[i][j] = array[i][0] * array2[0][j] + array[i][1] * array2[1][j] + array[i][2] * array2[2][j];
            }
        }
    }
    
    private void b(final double n) {
        this.b(this.yaw, this.pitch, this.hfov / n);
    }
    
    private void c(final double n) {
        this.b(this.yaw, this.pitch, this.hfov * n);
    }
    
    private void d(final double n) {
        this.b(this.yaw, this.pitch + n, this.hfov);
    }
    
    private void e(final double n) {
        this.b(this.yaw, this.pitch - n, this.hfov);
    }
    
    private void f(final double n) {
        this.b(this.yaw - n, this.pitch, this.hfov);
    }
    
    private void g(final double n) {
        this.b(this.yaw + n, this.pitch, this.hfov);
    }
    
    private void a(final double n, final double n2, final double n3, final double n4, final double n5, final double n6, final int n7) {
        double n8 = 0.0;
        final double n9 = (n4 - n3) / n7;
        final double pow = Math.pow(n6 / n5, 1.0 / n7);
        if (Math.abs(n2 - n) < 180.0 || this.yaw_max != 180.0 || this.yaw_min != -180.0) {
            n8 = (n2 - n) / n7;
        }
        else if (n2 > n) {
            n8 = (n2 - n - 360.0) / n7;
        }
        else if (n2 < n) {
            n8 = (n2 - n + 360.0) / n7;
        }
        this.b(n, n3, n5);
        this.ct = this.cs + n7;
        this.a(n8, n9, pow);
    }
    
    private void a(final double n, final double n2, final double n3, final int n4) {
        this.a(this.yaw, n, this.pitch, n2, this.hfov, n3, n4);
    }
    
    private void a(final double ch, final double ci, final double cj) {
        this.ch = ch;
        this.ci = ci;
        this.cj = cj;
        if (this.ct < this.cs) {
            this.ct = this.cs + 100000000L;
        }
        this.repaint();
    }
    
    private void g() {
        this.ct = 0L;
        this.ch = 0.0;
        this.ci = 0.0;
        this.cj = 1.0;
    }
    
    private boolean h() {
        return this.ct > this.cs;
    }
    
    private void b(double yaw, double pitch, final double hfov) {
        if (yaw == this.yaw && pitch == this.pitch && hfov == this.hfov) {
            return;
        }
        while (yaw > 180.0) {
            yaw -= 360.0;
        }
        while (yaw < -180.0) {
            yaw += 360.0;
        }
        final double a = this.a(hfov);
        if (pitch > this.pitch_max - a && this.pitch_max != 90.0) {
            pitch = this.pitch_max - a;
        }
        else if (pitch > this.pitch_max) {
            pitch = this.pitch_max;
        }
        else if (pitch < this.pitch_min + a && this.pitch_min != -90.0) {
            pitch = this.pitch_min + a;
        }
        else if (pitch < this.pitch_min) {
            pitch = this.pitch_min;
        }
        if (this.yaw_max != 180.0 || this.yaw_min != -180.0) {
            final int[] array = new int[2];
            this.a(0, (this.pitch <= 0.0) ? (this.vheight - 1) : 0, array, yaw, pitch);
            final int n = array[0];
            this.a(this.vwidth - 1, (this.pitch <= 0.0) ? (this.vheight - 1) : 0, array, yaw, pitch);
            final int n2 = array[0];
            if (n2 - n >= (int)((this.yaw_max - this.yaw_min) / 360.0 * this.bO)) {
                return;
            }
            if (n < (int)((this.yaw_min + 180.0) / 360.0 * this.bO)) {
                if (this.ct > this.cs) {
                    this.ch *= -1.0;
                }
                yaw += this.yaw_min - (n / this.bO * 360.0 - 180.0);
            }
            if (n2 >= (int)((this.yaw_max + 180.0) / 360.0 * this.bO)) {
                if (this.ct > this.cs) {
                    this.ch *= -1.0;
                }
                yaw -= n2 / this.bO * 360.0 - 180.0 - this.yaw_max;
            }
        }
        if (2.0 * a <= this.pitch_max - this.pitch_min && hfov <= this.hfov_max && hfov >= this.hfov_min && hfov <= this.yaw_max - this.yaw_min && pitch <= this.pitch_max && pitch >= this.pitch_min && yaw <= this.yaw_max && yaw >= this.yaw_min && (yaw != this.yaw || pitch != this.pitch || hfov != this.hfov)) {
            this.yaw = yaw;
            this.pitch = pitch;
            this.hfov = hfov;
            this.cm = a;
            this.dirty = true;
        }
        this.repaint();
    }
    
    void a(final int n) {
        if (n == -1) {
            final String a = this.a(n, d("\u0012tS\u0007\u001e\u001fa"));
            if (a != null) {
                this.bS = new Color(Integer.parseInt(a, 16));
            }
            if (a != null) {
                this.bm = (0xFF000000 | Integer.parseInt(a, 16));
            }
        }
        final String a2 = this.a(n, d("\u0001fQ\u0004\u001b\u0004j"));
        if (a2 != null) {
            this.by = Integer.parseInt(a2);
            if (this.by < 0) {
                this.by = 0;
            }
            if (this.by > 3) {
                this.by = 3;
            }
        }
        final String a3 = this.a(n, d("\u0000r^\u0001\u001f\u0011tU"));
        if (a3 != null) {
            this.cn = a3;
        }
        final String a4 = this.a(n, d("\u0004z\\\u001c\u001f\u0019}"));
        if (a4 != null) {
            final double doubleValue = Double.valueOf(a4);
            if (doubleValue > -90.0 && doubleValue < 0.0) {
                this.pitch_min = doubleValue;
            }
        }
        final String a5 = this.a(n, d("\u0004z\\\u001c\u001f\u0011k"));
        if (a5 != null) {
            final double doubleValue2 = Double.valueOf(a5);
            if (doubleValue2 < 90.0 && doubleValue2 > 0.0) {
                this.pitch_max = doubleValue2;
            }
        }
        final String a6 = this.a(n, d("\u0004z\\\u001c"));
        if (a6 != null) {
            final double doubleValue3 = Double.valueOf(a6);
            if (doubleValue3 >= this.pitch_min && doubleValue3 <= this.pitch_max) {
                this.pitch = doubleValue3;
            }
        }
        final String a7 = this.a(n, d("\u0000r^\u0005\u0013\b"));
        if (a7 != null) {
            this.yaw_max = Double.valueOf(a7);
        }
        final String a8 = this.a(n, d("\u0000r^\u0005\u001b\u001e"));
        if (a8 != null) {
            this.yaw_min = Double.valueOf(a8);
        }
        final String a9 = this.a(n, d("\u0000r^"));
        if (a9 != null) {
            final double doubleValue4 = Double.valueOf(a9);
            if (doubleValue4 >= this.yaw_min && doubleValue4 <= this.yaw_max) {
                this.yaw = doubleValue4;
            }
        }
        final String a10 = this.a(n, d("\u0016|F\u0005\u0013\b"));
        if (a10 != null) {
            final double doubleValue5 = Double.valueOf(a10);
            if (doubleValue5 <= 170.0) {
                this.hfov_max = ((doubleValue5 <= this.yaw_max - this.yaw_min) ? doubleValue5 : (this.yaw_max - this.yaw_min));
            }
        }
        final String a11 = this.a(n, d("\u0016|F\u0005\u001b\u001e"));
        if (a11 != null) {
            final double doubleValue6 = Double.valueOf(a11);
            if (doubleValue6 <= 170.0 && doubleValue6 >= 10.0) {
                this.hfov_min = doubleValue6;
            }
        }
        final String a12 = this.a(n, d("\u0016|F"));
        if (a12 != null) {
            final double doubleValue7 = Double.valueOf(a12);
            if (doubleValue7 <= this.hfov_max && doubleValue7 >= this.hfov_min) {
                this.hfov = doubleValue7;
            }
        }
        if (this.getParameter(d("\u0016|F\u0005\u001d\u0014v")).equalsIgnoreCase(d("\u001f}U\n\u0007\u0004g_\u0006"))) {
            this.bj = true;
        }
        final String a13 = this.a(n, d("\u001c|Q\f\u001b\u001etY\u0005\u0015"));
        if (a13 != null && !a13.equalsIgnoreCase(d(">\\"))) {
            this.bE = null;
            this.bE = this.a(a13);
            this.update(this.getGraphics());
        }
        final String a14 = this.a(n, d("\u0011fD\u0007\u0002\u0011}"));
        if (a14 != null) {
            this.ch = Double.valueOf(a14);
        }
    }
    
    String a(final int n, final String s) {
        if (n == -1) {
            return this.getParameter(s);
        }
        return this.getParameter(s);
    }
    
    public String myGetParameter(final String s, final String s2) {
        int index = 0;
        int index2;
        while ((index2 = s.indexOf(123, index)) >= 0 && (index = s.indexOf(125, index2)) >= 0) {
            final String c = this.c(s.substring(index2 + 1, index));
            if (c.startsWith(String.valueOf(s2) + "=")) {
                return this.c(c.substring(c.indexOf(61) + 1));
            }
        }
        return null;
    }
    
    String c(final String s) {
        if (s == null) {
            return null;
        }
        int n = 0;
        final int length = s.length();
        int n2 = length - 1;
        while (n < length && (s.charAt(n) == ' ' || s.charAt(n) == '\r' || s.charAt(n) == '\n' || s.charAt(n) == '\t')) {
            ++n;
        }
        if (n == length) {
            return null;
        }
        while (n2 >= 0 && (s.charAt(n2) == ' ' || s.charAt(n2) == '\r' || s.charAt(n2) == '\n' || s.charAt(n2) == '\t')) {
            --n2;
        }
        if (n2 < 0 || n2 < n) {
            return null;
        }
        return s.substring(n, n2 + 1);
    }
    
    public anpanorama() {
        this.b = false;
        this.c = false;
        this.d = d("/q\\\t\u001c\u001b");
        this.h = false;
        this.l = false;
        this.m = false;
        this.bh = false;
        this.bj = false;
        this.bk = false;
        this.bl = false;
        this.cA = false;
    }
    
    private static String d(final String s) {
        final char[] charArray = s.toCharArray();
        for (int length = charArray.length, i = 0; i < length; ++i) {
            final char[] array = charArray;
            final int n = i;
            final char c = array[n];
            char c2 = '\0';
            switch (i % 5) {
                case 0: {
                    c2 = 'p';
                    break;
                }
                case 1: {
                    c2 = '\u0013';
                    break;
                }
                case 2: {
                    c2 = '0';
                    break;
                }
                case 3: {
                    c2 = 'h';
                    break;
                }
                default: {
                    c2 = 'r';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
