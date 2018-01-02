import java.awt.Event;
import java.io.DataInputStream;
import java.awt.FontMetrics;
import java.awt.image.ImageProducer;
import java.awt.image.DirectColorModel;
import java.awt.image.ColorModel;
import java.awt.Container;
import java.awt.LayoutManager;
import java.io.InputStream;
import java.awt.Component;
import java.awt.MediaTracker;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.net.MalformedURLException;
import java.awt.Font;
import java.awt.Color;
import java.net.URL;
import java.awt.image.IndexColorModel;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.image.MemoryImageSource;
import java.awt.image.ImageObserver;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class fire extends Applet implements Runnable, ImageObserver
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
    Frame l;
    byte[] m;
    private Image n;
    int o;
    int p;
    int q;
    int r;
    int s;
    int t;
    int u;
    int v;
    int w;
    double x;
    double y;
    double z;
    byte A;
    int B;
    int C;
    int D;
    int E;
    int F;
    byte G;
    double H;
    int I;
    int J;
    int K;
    int L;
    boolean M;
    int[] N;
    int[] O;
    int P;
    int Q;
    int R;
    byte[] S;
    String T;
    String U;
    String V;
    String W;
    String X;
    String Y;
    String Z;
    String ba;
    String bb;
    String bc;
    String bd;
    String be;
    String bf;
    String bg;
    String bh;
    double bi;
    double bj;
    double bk;
    double bl;
    double bm;
    double bn;
    double bo;
    double bp;
    double bq;
    byte[] br;
    byte[] bs;
    byte[] bt;
    byte[] bu;
    byte[] bv;
    private Image bw;
    private Image bx;
    private Graphics by;
    IndexColorModel bz;
    boolean bA;
    boolean bB;
    final String bC = "aw>n\u0018T',{]ff,k\u0012\u0000D'w\u001eCnn*\nWp`c\u0013F";
    boolean bD;
    URL bE;
    boolean bF;
    String bG;
    int bH;
    int bI;
    int bJ;
    int bK;
    int bL;
    int bM;
    int bN;
    int bO;
    int bP;
    int bQ;
    int bR;
    int bS;
    String bT;
    int bU;
    boolean bV;
    boolean bW;
    Color bX;
    Color bY;
    Font bZ;
    int ca;
    int cb;
    int cc;
    int cd;
    int[] ce;
    int[] cf;
    String[] cg;
    int ch;
    int ci;
    Font[] cj;
    int ck;
    int cl;
    private int cm;
    int cn;
    int co;
    int cp;
    int cq;
    int cr;
    int[] cs;
    int ct;
    int cu;
    float cv;
    float cw;
    int cx;
    int cy;
    String cz;
    public static int cA;
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if (image == this.n) {
            if (n == 16) {
                this.bA = true;
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
        if (this.n != null) {
            this.n.flush();
        }
        this.n = null;
        if (this.bx != null) {
            this.bx.flush();
        }
        this.bx = null;
        if (this.by != null) {
            this.by.dispose();
        }
        this.by = null;
        System.gc();
    }
    
    public synchronized void prepaniframe() {
        if (this.bB) {
            this.notifyAll();
            while (!this.bA) {
                Thread.yield();
                try {
                    Thread.sleep(8L);
                }
                catch (InterruptedException ex) {}
            }
            this.bA = false;
        }
        this.by.drawImage(this.n, this.o, this.p, this);
    }
    
    public synchronized boolean CheckAniGIF() {
        this.prepareImage(this.n, this);
        if (this.c) {
            for (int i = 0; i < 3; ++i) {
                this.notifyAll();
                Thread.yield();
                try {
                    Thread.sleep(100L);
                }
                catch (InterruptedException ex) {}
            }
            return this.bA;
        }
        return false;
    }
    
    private final void a() {
        while (true) {
            this.showStatus(c("dh %\t\u0000u+o\u0012Vbnu\nW)/l\u001bYs+c\u0010\u000ed!o]Cu+f\u0014Ttnn\u0014Nbnk\u0013\u0000O\u001aO1\u0001"));
            try {
                Thread.sleep(500L);
            }
            catch (InterruptedException ex) {}
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
                this.showStatus(c("ij/e\u0018\u0000") + s + c("\u0000i!v]Fh;l\u0019\u0001"));
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
    
    byte a(final int n, final int n2, final int n3, final int n4) {
        final int n5 = n - n2;
        if (n5 >= n3) {
            return (byte)n5;
        }
        return (byte)(n4 - (n3 - n5 - 1));
    }
    
    public void init() {
        final int ca = fire.cA;
        this.setLayout(null);
        this.addNotify();
        this.d = this.getToolkit();
        this.cz = this.getParameter(c("Ss/v\bSj=e"));
        final String parameter;
        final String s = parameter = this.getParameter(c("Cu+f\u0014Tt"));
        String c = null;
        Label_0117: {
            Label_0086: {
                Label_0082: {
                    if (ca == 0) {
                        if (parameter == null) {
                            break Label_0082;
                        }
                        final String s2;
                        final String protocol = s2 = s;
                        if (ca != 0) {
                            break Label_0117;
                        }
                    }
                    if (parameter.startsWith(c("aw>n\u0018T',{]ff,k\u0012\u0000D'w\u001eCnn*\nWp`c\u0013F"))) {
                        break Label_0086;
                    }
                    this.a();
                    if (ca == 0) {
                        break Label_0086;
                    }
                }
                this.a();
            }
            (this.h = new Lware(this, c("fn<g]Aw>n\u0018T"))).hide();
            try {
                final String protocol = this.getDocumentBase().getProtocol();
                c = protocol;
            }
            catch (SecurityException ex) {
                c = c("Fn\"g");
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
        Label_0647: {
            Label_0638: {
                int length2 = 0;
                Label_0254: {
                    boolean b2 = false;
                    Label_0241: {
                        Label_0227: {
                            if (ca == 0) {
                                Label_0217: {
                                    if (!c.equals(c("Fn\"g"))) {
                                        final int length = s3.length();
                                        boolean equals = false;
                                        Label_0209: {
                                            if (ca == 0) {
                                                if (length < 1) {
                                                    break Label_0217;
                                                }
                                                final boolean b;
                                                equals = (b = (b2 = s3.startsWith(c("Lh-c\u0011"))));
                                                if (ca != 0) {
                                                    break Label_0209;
                                                }
                                            }
                                            if (length != 0) {
                                                break Label_0217;
                                            }
                                            b2 = (equals = s3.equals(c("\u00115y,M\u000e7`3")));
                                        }
                                        if (ca != 0) {
                                            break Label_0241;
                                        }
                                        if (!equals) {
                                            break Label_0227;
                                        }
                                    }
                                }
                                this.bD = true;
                            }
                            if (ca == 0) {
                                break Label_0638;
                            }
                        }
                        length2 = ((b2 = s3.startsWith(c("Wp9,"))) ? 1 : 0);
                        if (ca != 0) {
                            break Label_0254;
                        }
                    }
                    if (b2) {
                        s3 = s3.substring(4);
                    }
                    length2 = s3.length();
                }
                final int n2;
                final int n = n2 = length2;
                if (ca != 0 || n2 > 0) {
                    final char[] array = new char[n2];
                    s3.getChars(0, n, array, 0);
                    int n3 = 0;
                    while (true) {
                        while (true) {
                            Label_0315: {
                                if (ca == 0) {
                                    break Label_0315;
                                }
                                final char[] array2 = array;
                                final int n4 = n3;
                                if (ca != 0 || array2[n4] == '0') {
                                    array2[n4] = '1';
                                }
                                n3 += 5;
                            }
                            if (n3 < n) {
                                continue;
                            }
                            break;
                        }
                        if (ca != 0) {
                            continue;
                        }
                        break;
                    }
                    s3 = new String(array);
                }
                final String s4 = parameter2 = this.getParameter(c("Rb)a\u0012Db"));
                if (ca != 0) {
                    break Label_0647;
                }
                if (parameter2 != null) {
                    final String s5 = s4;
                    if (ca != 0) {
                        break Label_0647;
                    }
                    if (s5.length() > 5) {
                        s4.toLowerCase();
                        int n5 = 1;
                        try {
                            int n6 = 0;
                            while (true) {
                                while (true) {
                                    Label_0410: {
                                        if (ca == 0) {
                                            break Label_0410;
                                        }
                                        if (s4.charAt(n6) == '+') {
                                            ++n5;
                                        }
                                        ++n6;
                                    }
                                    if (n6 < s4.length()) {
                                        continue;
                                    }
                                    break;
                                }
                                if (ca != 0) {
                                    continue;
                                }
                                break;
                            }
                        }
                        catch (StringIndexOutOfBoundsException ex3) {}
                        final int[] array3 = new int[n5];
                        final int n7 = n5;
                        if (ca == 0 && n7 == 1) {
                            array3[0] = s4.length();
                            if (ca != 0) {
                                goto Label_0460;
                            }
                        }
                        else {
                            int n8 = n7;
                            try {
                                int n9 = 0;
                                while (true) {
                                    while (true) {
                                        Label_0496: {
                                            if (ca == 0) {
                                                break Label_0496;
                                            }
                                            if (s4.charAt(n9) == '+') {
                                                array3[n8] = n9;
                                                ++n8;
                                            }
                                            ++n9;
                                        }
                                        if (n9 < s4.length()) {
                                            continue;
                                        }
                                        break;
                                    }
                                    if (ca != 0) {
                                        continue;
                                    }
                                    break;
                                }
                            }
                            catch (StringIndexOutOfBoundsException ex4) {}
                            array3[n8] = s4.length();
                        }
                        final String[] array4 = new String[n5];
                        int n10 = 0;
                        int n11 = 0;
                        int i;
                        while (true) {
                            while (true) {
                                Label_0576: {
                                    if (ca == 0) {
                                        break Label_0576;
                                    }
                                    try {
                                        array4[n11] = s4.substring(n10, array3[n11]);
                                    }
                                    catch (StringIndexOutOfBoundsException ex5) {}
                                    n10 = array3[n11] + 1;
                                    ++n11;
                                }
                                if (n11 < n5) {
                                    continue;
                                }
                                break;
                            }
                            i = 0;
                            if (ca != 0) {
                                if (ca != 0) {
                                    continue;
                                }
                            }
                            break;
                        }
                        while (i < n5) {
                            if (s3.equals(this.h.dr(array4[i], 0, this.bD))) {
                                this.bD = true;
                            }
                            ++i;
                        }
                    }
                }
            }
            this.getParameter(c("Rb)n\u0014Nl"));
        }
        final String s7;
        final String s6 = s7 = parameter2;
        Label_0715: {
            if (ca == 0) {
                if (s7 != null) {
                    final String s8 = s6;
                    if (ca != 0) {
                        break Label_0715;
                    }
                    if (!s8.equalsIgnoreCase(c("nH"))) {
                        try {
                            this.bE = new URL(this.getDocumentBase(), s6);
                        }
                        catch (MalformedURLException ex6) {
                            this.bE = null;
                        }
                    }
                }
                this.getParameter(c("Rb)l\u0018Wa<c\u0010E"));
            }
        }
        if (s7.equalsIgnoreCase(c("yB\u001d"))) {
            this.bF = true;
        }
        Container parent = this.getParent();
    Label_2483_Outer:
        while (true) {
            while (true) {
                Label_0756: {
                    if (ca == 0) {
                        break Label_0756;
                    }
                    final Container parent2 = ((Frame)parent).getParent();
                    parent = parent2;
                }
                if (!(parent instanceof Frame)) {
                    continue;
                }
                break;
            }
            (this.l = (Frame)parent).setCursor(3);
            final Container parent2 = this;
            if (ca == 0) {
                final String parameter3;
                final String s9 = parameter3 = this.getParameter(c("Oq+p\u0014M`"));
                String s16 = null;
                String u = null;
                String s14 = null;
                Label_0986: {
                    Label_0943: {
                        if (ca == 0) {
                            fire fire = null;
                            Label_0935: {
                                if (parameter3 != null) {
                                    final String s10 = s9;
                                    if (ca != 0) {
                                        break Label_0943;
                                    }
                                    if (!s10.equalsIgnoreCase(c("nH"))) {
                                        this.n = this.a(s9);
                                        fire = this;
                                        if (ca != 0) {
                                            break Label_0935;
                                        }
                                        if (this.n != null) {
                                            final String parameter4;
                                            String s11 = parameter4 = this.getParameter(c("Oq+p\u0014M`\u0016"));
                                            if (ca == 0) {
                                                if (parameter4 == null) {
                                                    s11 = "0";
                                                }
                                                this.o = Integer.valueOf(s11);
                                                this.getParameter(c("Oq+p\u0014M`\u0017"));
                                            }
                                            final String s13;
                                            String s12 = s13 = parameter4;
                                            if (ca != 0 || s13 == null) {
                                                s12 = s13;
                                            }
                                            this.p = Integer.valueOf(s12);
                                        }
                                    }
                                }
                                this.bG = this.getParameter(c("mn Q$nD"));
                                fire = this;
                            }
                            final String s15;
                            s14 = (s15 = (u = (s16 = fire.bG)));
                            if (ca != 0) {
                                break Label_0986;
                            }
                        }
                    }
                    if (parameter3 == null) {
                        this.bG = c("\u00117");
                    }
                    this.e = Integer.valueOf(this.bG);
                    this.T = this.getParameter(c("Fp'f\tH"));
                    u = (s14 = (s16 = this.T));
                }
                if (ca == 0) {
                    if (s14 == null) {
                        this.T = c("\u00177");
                    }
                    this.U = this.getParameter(c("Fo+k\u001aTo"));
                    s16 = (u = this.U);
                }
                String v = null;
                String w = null;
                Label_1085: {
                    if (ca == 0) {
                        if (u == null) {
                            this.U = c("\u00163");
                        }
                        this.V = this.getParameter(c("Rb=z"));
                        w = (s16 = (v = this.V));
                        if (ca != 0) {
                            break Label_1085;
                        }
                    }
                    if (s16 == null) {
                        this.V = "2";
                    }
                    this.W = this.getParameter(c("Rb={"));
                    v = (w = this.W);
                }
                String x = null;
                String y = null;
                Label_1147: {
                    if (ca == 0) {
                        if (w == null) {
                            this.W = "4";
                        }
                        this.X = this.getParameter(c("Fd;v"));
                        y = (v = (x = this.X));
                        if (ca != 0) {
                            break Label_1147;
                        }
                    }
                    if (v == null) {
                        this.X = "7";
                    }
                    this.Y = this.getParameter(c("Ft;`"));
                    x = (y = this.Y);
                }
                String z = null;
                String ba = null;
                Label_1212: {
                    if (ca == 0) {
                        if (y == null) {
                            this.Y = "4";
                        }
                        this.Z = this.getParameter(c("Db(n\u0014Go:"));
                        ba = (x = (z = this.Z));
                        if (ca != 0) {
                            break Label_1212;
                        }
                    }
                    if (x == null) {
                        this.Z = c("\u00137");
                    }
                    this.ba = this.getParameter(c("Fj!f\u0018"));
                    z = (ba = this.ba);
                }
                String s17 = null;
                String bc = null;
                Label_1274: {
                    if (ca == 0) {
                        if (ba == null) {
                            this.ba = "2";
                        }
                        this.bb = this.getParameter(c("Ri*p\u0018P"));
                        bc = (z = (s17 = this.bb));
                        if (ca != 0) {
                            break Label_1274;
                        }
                    }
                    if (z == null) {
                        this.bb = "4";
                    }
                    this.bc = this.getParameter(c("Pf\"g\tTb\u001c"));
                    s17 = (bc = this.bc);
                }
                Label_1398: {
                    fire fire2 = null;
                    Label_1394: {
                        String bf = null;
                        Label_1372: {
                            String be = null;
                            Label_1341: {
                                if (ca == 0) {
                                    if (bc == null) {
                                        this.bc = "5";
                                    }
                                    this.bd = this.getParameter(c("Pf\"g\tTb\t"));
                                    be = (s17 = this.bd);
                                    if (ca != 0) {
                                        break Label_1341;
                                    }
                                }
                                if (s17 == null) {
                                    this.bd = "3";
                                }
                                this.be = this.getParameter(c("Pf\"g\tTb\f"));
                                bf = (be = this.be);
                                if (ca != 0) {
                                    break Label_1372;
                                }
                            }
                            if (be == null) {
                                this.be = "1";
                            }
                            this.bf = this.getParameter(c("Fn6l\u0018Tt-c\rE"));
                            fire2 = this;
                            if (ca != 0) {
                                break Label_1394;
                            }
                            bf = this.bf;
                        }
                        if (bf.equalsIgnoreCase(c("yB\u001d"))) {
                            this.M = true;
                            if (ca == 0) {
                                break Label_1398;
                            }
                        }
                        fire2 = this;
                    }
                    fire2.M = false;
                }
                this.bg = this.getParameter(c("Mb#f\u0018Lf7"));
                this.bh = this.getParameter(c("Pu'm\u000fIs7"));
                this.q = Integer.valueOf(this.T);
                this.r = Integer.valueOf(this.U);
                this.s = Integer.valueOf(this.V);
                this.t = Integer.valueOf(this.W);
                this.j = Integer.valueOf(this.bg);
                this.k = Integer.valueOf(this.bh);
                int n13;
                final int n12 = n13 = this.j;
                if (ca == 0) {
                    if (n12 < 0) {
                        this.j = 0;
                    }
                    final int k;
                    n13 = (k = this.k);
                }
                final int n14 = 10;
                int intValue = 0;
                Label_1621: {
                    Label_1568: {
                        if (ca == 0) {
                            if (n12 > n14) {
                                this.k = 10;
                                if (ca == 0) {
                                    break Label_1568;
                                }
                            }
                            intValue = (n13 = this.k);
                            if (ca != 0) {
                                break Label_1621;
                            }
                        }
                        if (n13 < n14) {
                            this.k = 1;
                        }
                    }
                    this.E = Integer.valueOf(this.X);
                    this.F = Integer.valueOf(this.Y);
                    this.G = (byte)(int)Integer.valueOf(this.Z);
                    intValue = Integer.valueOf(this.ba);
                }
                final int n15 = intValue;
                this.H = Double.valueOf(this.bb) / 10.0;
                this.J = Integer.valueOf(this.bc);
                this.K = Integer.valueOf(this.bd);
                this.L = Integer.valueOf(this.be);
                final double n16 = dcmpl(this.H, 0.9);
                int n18 = 0;
                int s18 = 0;
                Label_1738: {
                    Label_1734: {
                        if (ca == 0) {
                            if (n16 > 0) {
                                this.H = 0.9;
                                if (ca == 0) {
                                    break Label_1734;
                                }
                            }
                            final int n17;
                            s18 = (n17 = (n18 = dcmpg(this.H, 0.1)));
                            if (ca != 0) {
                                break Label_1738;
                            }
                        }
                        if (n16 < 0) {
                            this.H = 0.1;
                        }
                    }
                    n18 = (s18 = this.s);
                }
                final int n19 = 8;
                int n23 = 0;
                int q = 0;
                int n22 = 0;
                int n21 = 0;
                int n20 = 0;
                int n29 = 0;
                int n28 = 0;
                int n27 = 0;
                int n26 = 0;
                int n24 = 0;
                Label_1783: {
                    Label_1777: {
                        if (ca == 0) {
                            if (s18 > n19) {
                                this.s = 8;
                                if (ca == 0) {
                                    break Label_1777;
                                }
                            }
                            n20 = (n18 = (n21 = (n22 = (q = (n23 = this.s)))));
                            final int n25;
                            n24 = (n25 = (n26 = (n27 = (n28 = (n29 = 1)))));
                            if (ca != 0) {
                                break Label_1783;
                            }
                        }
                        if (n18 < n19) {
                            this.s = 1;
                        }
                    }
                    n21 = (n20 = (n22 = (q = (n23 = this.t))));
                    n26 = (n24 = (n27 = (n28 = (n29 = 8))));
                }
                Label_1827: {
                    Label_1820: {
                        if (ca == 0) {
                            if (n20 > n24) {
                                this.t = 8;
                                if (ca == 0) {
                                    break Label_1820;
                                }
                            }
                            n22 = (n21 = (q = (n23 = this.t)));
                            n27 = (n26 = (n28 = (n29 = 1)));
                        }
                        if (ca != 0) {
                            break Label_1827;
                        }
                        if (n21 < n26) {
                            this.t = 1;
                        }
                    }
                    q = (n22 = (n23 = this.q));
                    n28 = (n27 = (n29 = 1024));
                }
                Label_1977: {
                    fire fire3 = null;
                    Label_1973: {
                        int n30 = 0;
                        int n32 = 0;
                        Label_1946: {
                            Label_1910: {
                                int r = 0;
                                int n31 = 0;
                                Label_1897: {
                                    Label_1877: {
                                        Label_1865: {
                                            if (ca == 0) {
                                                if (n22 > n27) {
                                                    this.q = 1024;
                                                    if (ca == 0) {
                                                        break Label_1865;
                                                    }
                                                }
                                                n23 = (q = this.q);
                                                n29 = (n28 = 1);
                                            }
                                            if (ca != 0) {
                                                break Label_1877;
                                            }
                                            if (q < n28) {
                                                this.q = 1;
                                            }
                                        }
                                        r = (n23 = (n30 = this.r));
                                        n31 = (n29 = (n32 = 1024));
                                        if (ca != 0) {
                                            break Label_1897;
                                        }
                                    }
                                    if (n23 > n29) {
                                        this.r = 1024;
                                        if (ca == 0) {
                                            break Label_1910;
                                        }
                                    }
                                    n30 = (r = this.r);
                                    n32 = (n31 = 1);
                                }
                                if (ca != 0) {
                                    break Label_1946;
                                }
                                if (r < n31) {
                                    this.r = 1;
                                }
                            }
                            this.v = this.q * this.s;
                            this.w = this.r * this.t;
                            fire3 = this;
                            if (ca != 0) {
                                break Label_1973;
                            }
                            n30 = this.s;
                            n32 = 1;
                        }
                        if (n30 == n32) {
                            fire3 = this;
                            if (ca != 0) {
                                break Label_1973;
                            }
                            if (this.t == 1) {
                                this.u = 1;
                                if (ca == 0) {
                                    break Label_1977;
                                }
                            }
                        }
                        fire3 = this;
                    }
                    fire3.u = 2;
                }
                byte g;
                final byte b3 = g = this.G;
                int f2 = 0;
                Label_2105: {
                    Label_2103: {
                        int f = 0;
                        Label_2090: {
                            int e2 = 0;
                            Label_2070: {
                                Label_2061: {
                                    int e = 0;
                                    Label_2048: {
                                        Label_2028: {
                                            Label_2019: {
                                                if (ca == 0) {
                                                    if (b3 > 127) {
                                                        this.G = 127;
                                                        if (ca == 0) {
                                                            break Label_2019;
                                                        }
                                                    }
                                                    final byte g2;
                                                    g = (g2 = this.G);
                                                }
                                                if (ca != 0) {
                                                    break Label_2028;
                                                }
                                                if (b3 < 0) {
                                                    this.G = 0;
                                                }
                                            }
                                            e = (g = (byte)(e2 = this.E));
                                            if (ca != 0) {
                                                break Label_2048;
                                            }
                                        }
                                        if (g > 127) {
                                            this.E = 127;
                                            if (ca == 0) {
                                                break Label_2061;
                                            }
                                        }
                                        e2 = (e = this.E);
                                    }
                                    if (ca != 0) {
                                        break Label_2070;
                                    }
                                    if (e < 0) {
                                        this.E = 0;
                                    }
                                }
                                f = (e2 = (f2 = this.F));
                                if (ca != 0) {
                                    break Label_2090;
                                }
                            }
                            if (e2 > 127) {
                                this.F = 127;
                                if (ca == 0) {
                                    break Label_2103;
                                }
                            }
                            f2 = (f = this.F);
                        }
                        if (ca != 0) {
                            break Label_2105;
                        }
                        if (f < 0) {
                            this.F = 0;
                        }
                    }
                    f2 = n15;
                }
                Label_2142: {
                    if (f2 == 1) {
                        this.B = 0;
                        this.C = this.q;
                        if (ca == 0) {
                            break Label_2142;
                        }
                    }
                    this.B = 1;
                    this.C = this.q - 1;
                }
                this.D = this.B + this.B;
                int n37;
                int l;
                int n36;
                int n35;
                int n34;
                final int n33 = n34 = (n35 = (n36 = (l = (n37 = this.J))));
                Label_2201: {
                    Label_2197: {
                        if (ca == 0) {
                            if (n33 > 8) {
                                this.J = 8;
                                if (ca == 0) {
                                    break Label_2197;
                                }
                            }
                            final int n38;
                            n34 = (n38 = (n35 = (n36 = (l = (n37 = this.J)))));
                        }
                        if (ca != 0) {
                            break Label_2201;
                        }
                        if (n33 < 0) {
                            this.J = 0;
                        }
                    }
                    n35 = (n34 = (n36 = (l = (n37 = this.K))));
                }
                Label_2243: {
                    Label_2239: {
                        if (ca == 0) {
                            if (n34 > 8) {
                                this.K = 8;
                                if (ca == 0) {
                                    break Label_2239;
                                }
                            }
                            n36 = (n35 = (l = (n37 = this.K)));
                        }
                        if (ca != 0) {
                            break Label_2243;
                        }
                        if (n35 < 0) {
                            this.K = 0;
                        }
                    }
                    l = (n36 = (n37 = this.L));
                }
                int n40;
                while (true) {
                    int n39 = 0;
                    Label_2562: {
                        Label_2378: {
                            Label_2281: {
                                if (ca == 0) {
                                    if (n36 > 8) {
                                        this.L = 8;
                                        if (ca == 0) {
                                            break Label_2281;
                                        }
                                    }
                                    n37 = (l = this.L);
                                }
                                if (ca != 0) {
                                    break Label_2378;
                                }
                                if (l < 0) {
                                    this.L = 0;
                                }
                            }
                            this.br = new byte[128];
                            this.bs = new byte[128];
                            this.bt = new byte[128];
                            this.bu = new byte[128];
                            this.dopalette();
                            this.bz = new IndexColorModel(7, 128, this.br, this.bs, this.bt, 255);
                            this.bv = new byte[this.q * (this.r + 4)];
                            n39 = (n37 = (this.M ? 1 : 0));
                            if (ca != 0) {
                                break Label_2562;
                            }
                        }
                        Label_2503: {
                            if (n37 != 1) {
                                break Label_2503;
                            }
                            this.bH = this.q * this.r;
                            this.N = new int[this.bH];
                            this.O = new int[128];
                            this.bJ = 0;
                            while (true) {
                                Label_2493: {
                                    if (ca == 0) {
                                        break Label_2493;
                                    }
                                    this.O[this.bJ] = (0xFF000000 | (this.br[this.bJ] & 0xFF) << 16 | (this.bs[this.bJ] & 0xFF) << 8 | (this.bt[this.bJ] & 0xFF));
                                    ++this.bJ;
                                }
                                if (this.bJ < 128) {
                                    continue Label_2483_Outer;
                                }
                                break;
                            }
                        }
                        this.I = this.q * (this.r + 2);
                        this.S = new byte[this.q * 2 * 256];
                        this.Q = this.q * 2 * 256;
                        this.R = this.q + this.q;
                        n39 = 0;
                    }
                    n40 = n39;
                    this.bJ = 0;
                    if (ca != 0) {
                        if (ca != 0) {
                            continue;
                        }
                    }
                    break;
                }
                while (true) {
                    if (this.bJ >= 256) {
                        this.bL = this.r + 3;
                        this.m = new byte[128];
                        this.bJ = 0;
                        if (ca == 0) {
                            break;
                        }
                        if (ca == 0) {
                            break;
                        }
                    }
                    else {
                        this.A = 0;
                        this.bK = 0;
                        if (ca != 0) {}
                    }
                    while (true) {
                        int n41 = 0;
                        while (true) {
                            fire fire4;
                            if (this.bK >= this.q) {
                                n40 += this.R;
                                fire4 = this;
                                n41 = this.bJ + 1;
                                if (ca == 0) {
                                    break;
                                }
                            }
                            else {
                                final double n42 = dcmpg(Math.random(), this.H);
                                Label_2711: {
                                    fire fire5 = null;
                                    Label_2691: {
                                        if (ca == 0) {
                                            if (n42 < 0) {
                                                this.A = (byte)((int)(Math.random() * 512.0) & 0x7F);
                                            }
                                            this.S[n40 + this.bK] = this.A;
                                            fire5 = this;
                                            if (ca != 0) {
                                                break Label_2691;
                                            }
                                            final byte a = this.A;
                                        }
                                        if (n42 > this.G) {
                                            this.S[n40 + this.q + this.bK] = this.A;
                                            if (ca == 0) {
                                                break Label_2711;
                                            }
                                        }
                                        fire5 = this;
                                    }
                                    fire5.S[n40 + this.q + this.bK] = this.G;
                                }
                                fire4 = this;
                                final int n43 = this.bK + 1;
                            }
                            fire4.bK = n41;
                        }
                        this.bJ = n41;
                        continue Label_2483_Outer;
                        continue;
                    }
                }
                while (true) {
                    Label_2885: {
                        fire fire6 = null;
                        Label_2872: {
                            final int bj;
                            final int e3;
                            Label_2853: {
                                Label_2843: {
                                    if (this.bJ >= 128) {
                                        try {
                                            this.b();
                                            if (ca != 0) {
                                                break Label_2843;
                                            }
                                        }
                                        catch (NoSuchMethodError noSuchMethodError) {
                                            this.b();
                                        }
                                        break;
                                    }
                                    bj = this.bJ;
                                    e3 = this.E;
                                    if (ca != 0) {
                                        break Label_2853;
                                    }
                                    if (bj > e3) {
                                        this.m[this.bJ] = (byte)(this.bJ - this.F);
                                        if (ca == 0) {
                                            break Label_2885;
                                        }
                                    }
                                }
                                fire6 = this;
                                if (ca != 0) {
                                    break Label_2872;
                                }
                                final int bj2 = this.bJ;
                            }
                            if (bj > e3) {
                                this.m[this.bJ] = 0;
                                if (ca == 0) {
                                    break Label_2885;
                                }
                            }
                            fire6 = this;
                        }
                        fire6.m[this.bJ] = (byte)this.bJ;
                    }
                    ++this.bJ;
                }
                this.scrollinitial();
                this.bx = this.createImage(this.v, this.w + this.cx);
                this.by = this.bx.getGraphics();
                return;
            }
            continue;
        }
    }
    
    void b() {
        if (!this.M) {
            this.b = new MemoryImageSource(this.q, this.r, this.bz, this.bv, 0, this.q);
        }
        else {
            this.b = new MemoryImageSource(this.q, this.r, new DirectColorModel(24, 16711680, 65280, 255), this.N, 0, this.q);
        }
        String s;
        try {
            s = System.getProperty(c("Jf8cSVb<q\u0014Oi"));
        }
        catch (SecurityException ex) {
            s = c("Ui%");
        }
        if (!s.startsWith(c("\u0011)~"))) {
            try {
                this.b.setAnimated(true);
                this.b.setFullBufferUpdates(true);
                this.bw = this.createImage(this.b);
                this.b.newPixels();
                this.c = true;
            }
            catch (NoSuchMethodError noSuchMethodError) {
                this.c = false;
            }
        }
        if (!this.c) {
            this.b = null;
            if (!this.M) {
                this.a = new anfy(this.q, this.r, this.bz, this.bv, 0, this.q);
            }
            else {
                this.a = new anfy(this.q, this.r, new DirectColorModel(24, 16711680, 65280, 255), this.N, 0, this.q);
            }
            this.bw = this.createImage(this.a);
        }
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
        System.gc();
        this.f = System.currentTimeMillis();
        final Graphics graphics = this.getGraphics();
        if (this.n != null && !this.bB) {
            this.bB = this.CheckAniGIF();
        }
        if (this.bE != null) {
            this.l.setCursor(12);
        }
        else {
            this.l.setCursor(0);
        }
        this.h.dr(c("Ai({"), 1, this.bD);
        while (this.g != null) {
            this.drawFire();
            if (this.M) {
                final int bh = this.bH;
                final int[] n = this.N;
                final int[] o = this.O;
                final byte[] bv = this.bv;
                for (int i = 0; i < bh; ++i) {
                    n[i] = o[bv[i]];
                }
            }
            if (++this.i == this.j) {
                System.gc();
                this.i = 0;
            }
            try {
                this.producefixed();
            }
            catch (NoSuchMethodError noSuchMethodError) {}
            if (this.u == 1) {
                this.by.drawImage(this.bw, 0, 0, this);
            }
            else {
                this.prepscaled();
                this.by.drawImage(this.bw, 0, 0, this.v, this.w, this);
            }
            if (this.n != null) {
                this.prepaniframe();
            }
            if (this.bV) {
                this.scrolltext(this.by);
            }
            graphics.drawImage(this.bx, 0, 0, this);
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
        this.prepareImage(this.bw, this.v, this.w, this);
        this.notifyAll();
        while ((checkImage & 0xF0) == 0x0) {
            Thread.yield();
            checkImage = this.checkImage(this.bw, this.v, this.w, this);
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
        if (this.bw != null) {
            if (this.u == 1) {
                this.by.drawImage(this.bw, 0, 0, this);
            }
            else {
                this.prepscaled();
                this.by.drawImage(this.bw, 0, 0, this.v, this.w, this);
            }
            if (this.n != null) {
                this.prepaniframe();
            }
            if (this.bV) {
                this.scrolltext(this.by);
            }
            graphics.drawImage(this.bx, 0, 0, this);
        }
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final void drawFire() {
        int n = this.q + this.B;
        final int bl = this.bL;
        final int c = this.C;
        final byte[] bv = this.bv;
        final byte[] m = this.m;
        final int q = this.q;
        final int d = this.D;
        for (int i = 1; i < bl; ++i) {
            for (int j = this.B; j < c; ++j) {
                bv[n - q] = (byte)(bv[n] + bv[n - 1] + bv[n + 1] + bv[n + q] >> 2);
                bv[n] = m[bv[n]];
                ++n;
            }
            n += d;
        }
        final int r = this.R;
        final int k = this.I;
        final byte[] s = this.S;
        final int p = this.P;
        for (int l = 0; l < r; ++l) {
            bv[k + l] = s[p + l];
        }
        this.P += this.R;
        if (this.P >= this.Q) {
            this.P = 0;
        }
    }
    
    public final void dopalette() {
        switch (this.J) {
            case 0: {
                this.dopal0(1);
                break;
            }
            case 1: {
                this.dopal1(1);
                break;
            }
            case 2: {
                this.dopal2(1);
                break;
            }
            case 3: {
                this.dopal3(1);
                break;
            }
            case 4: {
                this.dopal4(1);
                break;
            }
            case 5: {
                this.dopal5(1);
                break;
            }
            case 6: {
                this.dopal6(1);
                break;
            }
            case 7: {
                this.dopal7(1);
                break;
            }
            case 8: {
                this.dopal8(1);
                break;
            }
        }
        switch (this.K) {
            case 0: {
                this.dopal0(2);
                break;
            }
            case 1: {
                this.dopal1(2);
                break;
            }
            case 2: {
                this.dopal2(2);
                break;
            }
            case 3: {
                this.dopal3(2);
                break;
            }
            case 4: {
                this.dopal4(2);
                break;
            }
            case 5: {
                this.dopal5(2);
                break;
            }
            case 6: {
                this.dopal6(2);
                break;
            }
            case 7: {
                this.dopal7(2);
                break;
            }
            case 8: {
                this.dopal8(2);
                break;
            }
        }
        switch (this.L) {
            case 0: {
                this.dopal0(3);
            }
            case 1: {
                this.dopal1(3);
            }
            case 2: {
                this.dopal2(3);
            }
            case 3: {
                this.dopal3(3);
            }
            case 4: {
                this.dopal4(3);
            }
            case 5: {
                this.dopal5(3);
            }
            case 6: {
                this.dopal6(3);
            }
            case 7: {
                this.dopal7(3);
            }
            case 8: {
                this.dopal8(3);
            }
            default: {}
        }
    }
    
    public final void placecmp(final int n) {
        switch (n) {
            case 1: {
                for (int i = 0; i < 128; ++i) {
                    this.br[i] = this.bu[i];
                }
            }
            case 2: {
                for (int j = 0; j < 128; ++j) {
                    this.bs[j] = this.bu[j];
                }
            }
            case 3: {
                for (int k = 0; k < 128; ++k) {
                    this.bt[k] = this.bu[k];
                }
            }
            default: {}
        }
    }
    
    public final void dopal8(final int n) {
        for (int i = 0; i < 128; ++i) {
            this.bu[i] = -1;
        }
        this.placecmp(n);
    }
    
    public final void dopal7(final int n) {
        for (int i = 0; i < 32; ++i) {
            this.bu[i] = (byte)(i * 8);
        }
        for (int j = 32; j < 128; ++j) {
            this.bu[j] = -1;
        }
        this.placecmp(n);
    }
    
    public final void dopal6(final int n) {
        for (int i = 0; i < 32; ++i) {
            this.bu[i] = (byte)(i * 4);
        }
        for (int j = 32; j < 64; ++j) {
            this.bu[j] = (byte)(j * 4);
        }
        for (int k = 64; k < 128; ++k) {
            this.bu[k] = -1;
        }
        this.placecmp(n);
    }
    
    public final void dopal5(final int n) {
        for (int i = 0; i < 32; ++i) {
            this.bu[i] = (byte)(i * 2.68);
        }
        for (int j = 32; j < 64; ++j) {
            this.bu[j] = (byte)(j * 2.68);
        }
        for (int k = 64; k < 96; ++k) {
            this.bu[k] = (byte)(k * 2.68);
        }
        for (int l = 96; l < 128; ++l) {
            this.bu[l] = -1;
        }
        this.placecmp(n);
    }
    
    public final void dopal4(final int n) {
        for (int i = 0; i < 32; ++i) {
            this.bu[i] = (byte)(i * 2);
        }
        for (int j = 32; j < 64; ++j) {
            this.bu[j] = (byte)(j * 2);
        }
        for (int k = 64; k < 96; ++k) {
            this.bu[k] = (byte)(k * 2);
        }
        for (int l = 96; l < 128; ++l) {
            this.bu[l] = (byte)(l * 2);
        }
        this.placecmp(n);
    }
    
    public final void dopal3(final int n) {
        for (int i = 0; i < 32; ++i) {
            this.bu[i] = 0;
        }
        for (int j = 32; j < 64; ++j) {
            this.bu[j] = (byte)((j - 32) * 2.68);
        }
        for (int k = 64; k < 96; ++k) {
            this.bu[k] = (byte)((k - 32) * 2.68);
        }
        for (int l = 96; l < 128; ++l) {
            this.bu[l] = (byte)((l - 32) * 2.68);
        }
        this.placecmp(n);
    }
    
    public final void dopal2(final int n) {
        for (int i = 0; i < 64; ++i) {
            this.bu[i] = 0;
        }
        for (int j = 64; j < 96; ++j) {
            this.bu[j] = (byte)((j - 64) * 4);
        }
        for (int k = 96; k < 128; ++k) {
            this.bu[k] = (byte)((k - 64) * 4);
        }
        this.placecmp(n);
    }
    
    public final void dopal1(final int n) {
        for (int i = 0; i < 96; ++i) {
            this.bu[i] = 0;
        }
        for (int j = 96; j < 128; ++j) {
            this.bu[j] = (byte)((j - 96) * 8);
        }
        this.placecmp(n);
    }
    
    public final void dopal0(final int n) {
        for (int i = 0; i < 128; ++i) {
            this.bu[i] = 0;
        }
        this.placecmp(n);
    }
    
    public void scrollinitial() {
        this.bV = false;
        final String parameter = this.getParameter(c("Tb6v\u000eCu!n\u0011"));
        if (parameter != null && !parameter.equalsIgnoreCase(c("nH"))) {
            String s = this.getParameter(c("Tb6v\tYw+"));
            if (s == null) {
                s = c("Hh<k\u0007Oi:c\u0011");
            }
            if (s.equals(c("Hh<k\u0007Oi:c\u0011"))) {
                this.ch = 0;
            }
            else if (s.equals(c("Vb<v\u0014Cf\""))) {
                this.ch = 1;
            }
            else if (s.equals(c("Zh!o\u0014N`"))) {
                this.ch = 2;
            }
            else if (s.equals(c("Ii8x\u0012Oj'l\u001a"))) {
                this.ch = 3;
            }
            if (this.ch == 0) {
                this.GetTheString(parameter, 0);
                if (this.bT != null) {
                    this.bV = true;
                }
            }
            else {
                this.GetTheString(parameter, 1);
                if (this.cg != null) {
                    this.bV = true;
                }
            }
        }
        if (this.bV) {
            String parameter2 = this.getParameter(c("Tb6v\u000ePb+f"));
            if (parameter2 == null) {
                parameter2 = "0";
            }
            this.bU = Integer.valueOf(parameter2);
            String s2 = this.getParameter(c("Tb6v\u001bOi:"));
            if (s2 == null) {
                s2 = c("au'c\u0011");
            }
            int n = 0;
            if (this.getParameter(c("Tb6v\u001fOk*")).equalsIgnoreCase(c("yB\u001d"))) {
                ++n;
            }
            String s3 = this.getParameter(c("Tb6v\u0014Tf\"k\u001e"));
            if (s3 == null) {
                s3 = c("nH");
            }
            if (s3.equalsIgnoreCase(c("yB\u001d"))) {
                n += 2;
            }
            String s4 = this.getParameter(c("Tb6v\u000eI}+"));
            if (s4 == null) {
                s4 = c("\u00115");
            }
            this.bZ = new Font(s2, n, Integer.valueOf(s4));
            if (this.getParameter(c("Tb6v\u000eHf*m\n")).equalsIgnoreCase(c("yB\u001d"))) {
                this.bW = true;
            }
            else {
                this.bW = false;
            }
            this.bX = new Color(Integer.valueOf(this.getParameter(c("tb6v>Ok\u001c"))), Integer.valueOf(this.getParameter(c("tb6v>Ok\t"))), Integer.valueOf(this.getParameter(c("tb6v>Ok\f"))));
            this.bY = new Color(Integer.valueOf(this.getParameter(c("tb6v.ch\"P"))), Integer.valueOf(this.getParameter(c("tb6v.ch\"E"))), Integer.valueOf(this.getParameter(c("tb6v.ch\"@"))));
            this.bM = this.size().width;
            this.bN = this.size().height;
            if (this.ch == 0) {
                String parameter3 = this.getParameter(c("Tb6v\u0012Fa=g\t"));
                if (parameter3 == null) {
                    parameter3 = "0";
                }
                this.bP = Integer.valueOf(parameter3);
                if (this.bP < 0) {
                    this.bP = 0;
                }
                String parameter4 = this.getParameter(c("tb6v7Uj>C\u0010P"));
                if (parameter4 == null) {
                    parameter4 = "0";
                }
                this.cm = Integer.valueOf(parameter4);
                String parameter5 = this.getParameter(c("tb6v7Uj>Q\rD"));
                if (parameter5 == null) {
                    parameter5 = "0";
                }
                this.cp = Integer.valueOf(parameter5);
                String parameter6 = this.getParameter(c("tb6v.Ii+C\u0010P"));
                if (parameter6 == null) {
                    parameter6 = "0";
                }
                this.ca = Integer.valueOf(parameter6);
                String parameter7 = this.getParameter(c("tb6v.Ii+Q\rD"));
                if (parameter7 == null) {
                    parameter7 = "0";
                }
                this.cb = Integer.valueOf(parameter7);
                String parameter8 = this.getParameter(c("tb6v.Ii+C\u0013Gk+"));
                if (parameter8 == null) {
                    parameter8 = "0";
                }
                this.cc = Integer.valueOf(parameter8);
                final FontMetrics fontMetrics = this.getGraphics().getFontMetrics(this.bZ);
                this.bQ = fontMetrics.stringWidth(this.bT);
                this.bR = fontMetrics.getHeight();
                this.bS = fontMetrics.getMaxDescent();
                this.bO = this.bM;
                if (this.bP < this.bR - this.bS) {
                    this.bP = this.bR - this.bS;
                }
                else if (this.bP > this.bN - this.bS) {
                    this.bP = this.bN - this.bS;
                }
                if (this.ca != 0) {
                    this.ce = new int[this.bM + 360];
                    this.cf = new int[this.bM + 360];
                    for (int i = 0; i < this.bM + 360; ++i) {
                        this.ce[i] = (int)(this.ca * Math.sin(this.cc * i * 3.141592653589793 / 180.0)) - this.bR - this.bS + this.bP;
                        this.cf[i] = this.ce[i] - this.w;
                    }
                    this.cd = 360;
                    this.cx = this.bR + this.bS + 1;
                    this.cy = this.cx - 1;
                }
            }
            else {
                if (this.ch == 1) {
                    String s5 = this.getParameter(c("Tb6v\u000bSw/a\u0018"));
                    if (s5 == null) {
                        s5 = c("\u00117");
                    }
                    final int intValue = Integer.valueOf(s5);
                    final FontMetrics fontMetrics2 = this.getGraphics().getFontMetrics(this.bZ);
                    this.cr = fontMetrics2.getHeight() + intValue;
                    this.cs = new int[this.cg.length];
                    this.bJ = 0;
                    while (this.bJ < this.cg.length) {
                        this.cs[this.bJ] = (this.bM - fontMetrics2.stringWidth(this.cg[this.bJ])) / 2;
                        ++this.bJ;
                    }
                    this.cq = -this.cr;
                    return;
                }
                if (this.ch >= 2) {
                    String parameter9 = this.getParameter(c("Tb6v\u0010Ii(m\u0013T"));
                    if (parameter9 == null) {
                        parameter9 = "2";
                    }
                    this.ck = Integer.valueOf(parameter9);
                    String s6 = this.getParameter(c("Tb6v\u0010A\u007f(m\u0013T"));
                    if (s6 == null) {
                        s6 = c("\u00175");
                    }
                    this.cl = Integer.valueOf(s6);
                    this.ci = this.cl - this.ck;
                    this.bZ = null;
                    this.cj = new Font[this.ci];
                    int ck = this.ck;
                    this.bJ = 0;
                    while (this.bJ < this.ci) {
                        this.cj[this.bJ] = new Font(s2, n, ck++);
                        ++this.bJ;
                    }
                    this.cv = this.bM / 2.0f;
                    this.cw = this.bN / 2.0f;
                    if (this.ch == 3) {
                        this.ct = this.ci - 1;
                        return;
                    }
                    this.ct = 0;
                }
            }
        }
    }
    
    public void scrolltext(final Graphics graphics) {
        switch (this.ch) {
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
        graphics.setFont(this.bZ);
        this.cq += this.bU;
        if (this.cq > this.bN + this.cg.length * this.cr) {
            this.cq = -this.cr;
        }
        if (this.bW) {
            for (int i = 0; i < this.cg.length; ++i) {
                final String s = this.cg[i];
                final int n = this.cs[i];
                final int n2 = this.bN - this.cq + i * this.cr;
                graphics.setColor(this.bY);
                graphics.drawString(s, n + 1, n2 + 1);
                graphics.setColor(this.bX);
                graphics.drawString(s, n, n2);
            }
            return;
        }
        graphics.setColor(this.bX);
        for (int j = 0; j < this.cg.length; ++j) {
            graphics.drawString(this.cg[j], this.cs[j], this.bN - this.cq + j * this.cr);
        }
    }
    
    public void zoomscroll(final Graphics graphics) {
        final String s = this.cg[this.cu];
        graphics.setFont(this.cj[this.ct]);
        final FontMetrics fontMetrics = graphics.getFontMetrics(this.cj[this.ct]);
        final int n = (int)(this.cv - fontMetrics.stringWidth(s) / 2.0f);
        final int n2 = (int)(this.cw + fontMetrics.getHeight() / 4.0f);
        if (this.bW) {
            graphics.setColor(this.bY);
            graphics.drawString(s, n + 1, n2 + 1);
        }
        graphics.setColor(this.bX);
        graphics.drawString(s, n, n2);
        if (this.ch == 3) {
            this.ct -= this.bU;
            if (this.ct <= 1) {
                this.ct = this.ci - 1;
                ++this.cu;
                if (this.cu >= this.cg.length) {
                    this.cu = 0;
                }
            }
        }
        else {
            this.ct += this.bU;
            if (this.ct >= this.ci) {
                this.ct = 0;
                ++this.cu;
                if (this.cu >= this.cg.length) {
                    this.cu = 0;
                }
            }
        }
    }
    
    public void horizscroll(final Graphics graphics) {
        graphics.setFont(this.bZ);
        if (this.cm == 0) {
            this.cn = this.bP;
        }
        else {
            this.co += this.cp;
            this.cn = this.bP - (int)Math.abs(this.cm * Math.sin(this.co / 90.0 * 3.141592653589793));
        }
        if (this.ca != 0) {
            for (int i = 0; i < this.bM; ++i) {
                final int n = this.ce[this.cd + i];
                graphics.copyArea(i, n, 1, this.cx, 0, this.w - n);
            }
            if (this.bW) {
                graphics.setColor(this.bY);
                graphics.drawString(this.bT, this.bO + 1, this.w + this.bR + 1);
            }
            graphics.setColor(this.bX);
            graphics.drawString(this.bT, this.bO, this.w + this.bR);
            for (int j = 0; j < this.bM; ++j) {
                graphics.copyArea(j, this.w, 1, this.cy, 0, this.cf[this.cd + j]);
            }
            this.cd -= this.cb;
            if (this.cd < 0) {
                this.cd += 360;
            }
        }
        else {
            if (this.bW) {
                graphics.setColor(this.bY);
                graphics.drawString(this.bT, this.bO + 1, this.cn + 1);
            }
            graphics.setColor(this.bX);
            graphics.drawString(this.bT, this.bO, this.cn);
        }
        this.bO -= this.bU;
        if (this.bO < -this.bQ) {
            this.bO = this.bM;
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
                            this.bT = new String(byteArray);
                            return;
                        }
                        catch (NoSuchMethodError noSuchMethodError) {
                            this.bT = new String(byteArray, 0);
                            return;
                        }
                    }
                    int n3 = 1;
                    for (int j = 0; j < n2; ++j) {
                        if (byteArray[j] == 10) {
                            ++n3;
                        }
                    }
                    this.cg = new String[n3 - 1];
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
                                this.cg[l] = new String(byteArray, array2[l], array3[l]);
                            }
                            catch (NoSuchMethodError noSuchMethodError2) {
                                this.cg[l] = new String(byteArray, 0, array2[l], array3[l]);
                            }
                        }
                    }
                    catch (StringIndexOutOfBoundsException ex) {
                        this.cg = null;
                    }
                }
                catch (IOException ex2) {}
            }
            catch (IOException ex3) {}
        }
        catch (MalformedURLException ex4) {}
    }
    
    public final boolean mouseEnter(final Event event, final int n, final int n2) {
        this.showStatus(this.cz);
        return true;
    }
    
    public final boolean mouseExit(final Event event, final int n, final int n2) {
        this.showStatus("");
        return true;
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        final int ca = fire.cA;
        fire fire = this;
        Label_0065: {
            if (ca != 0) {
                break Label_0065;
            }
            if (!this.bD) {
                this.h.show();
                try {
                    this.h.move(100, 100);
                }
                catch (Exception ex) {}
                this.h.toFront();
                this.h.requestFocus();
                if (ca == 0) {
                    return true;
                }
            }
            try {
                fire fire2 = this;
                fire = this;
                Label_0084: {
                    if (ca != 0) {
                        break Label_0084;
                    }
                    if (fire.bE == null) {
                        return true;
                    }
                    try {
                        this.h.dck();
                        fire fire3 = this;
                        fire2 = this;
                        if (ca == 0) {
                            if (fire2.bF) {
                                this.getAppletContext().showDocument(this.bE, this.getParameter(c("Rb)d\u000fAj+l\u001cMb")));
                                if (ca == 0) {
                                    return true;
                                }
                            }
                            fire3 = this;
                        }
                        fire3.getAppletContext().showDocument(this.bE);
                    }
                    catch (Exception ex2) {}
                }
            }
            catch (Exception ex3) {}
        }
        return true;
    }
    
    public fire() {
        this.c = false;
        this.q = 1;
        this.r = 1;
        this.s = 1;
        this.t = 1;
        this.x = 1.0;
        this.y = 1.0;
        this.z = 1.0;
        this.C = this.q;
        this.E = 7;
        this.F = 4;
        this.G = 70;
        this.H = 0.5;
        this.J = 1;
        this.K = 1;
        this.L = 1;
        this.M = false;
        this.bA = false;
        this.bB = false;
        this.bD = false;
        this.bF = false;
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
                    c2 = ' ';
                    break;
                }
                case 1: {
                    c2 = '\u0007';
                    break;
                }
                case 2: {
                    c2 = 'N';
                    break;
                }
                case 3: {
                    c2 = '\u0002';
                    break;
                }
                default: {
                    c2 = '}';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
