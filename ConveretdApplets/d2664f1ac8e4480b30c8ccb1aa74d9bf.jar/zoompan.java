import java.awt.Event;
import java.io.DataInputStream;
import java.awt.FontMetrics;
import java.awt.image.ImageProducer;
import java.awt.image.ColorModel;
import java.awt.image.DirectColorModel;
import java.awt.Container;
import java.awt.image.PixelGrabber;
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

public class zoompan extends Applet implements Runnable, ImageObserver
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
    boolean l;
    int m;
    int n;
    int o;
    int p;
    int q;
    int r;
    int[] s;
    int[] t;
    private boolean u;
    private Image v;
    int w;
    int x;
    int y;
    int z;
    int A;
    int B;
    boolean C;
    int D;
    int E;
    Frame F;
    int G;
    boolean H;
    int I;
    int J;
    private Image K;
    private Graphics L;
    String M;
    String N;
    String O;
    String P;
    String Q;
    String R;
    String S;
    String T;
    String U;
    String V;
    String W;
    String X;
    String Y;
    String Z;
    private Image ba;
    private Graphics bb;
    boolean bc;
    boolean bd;
    final String be = "]$cp`htqe%]:ue%H1rq%4#dk+}:ueqy5~2fs9:";
    boolean bf;
    URL bg;
    boolean bh;
    String bi;
    double bj;
    double bk;
    double bl;
    int bm;
    int bn;
    int bo;
    long bp;
    int bq;
    int br;
    int bs;
    int bt;
    int bu;
    float bv;
    int bw;
    int bx;
    int by;
    int bz;
    int bA;
    int bB;
    int bC;
    String bD;
    int bE;
    boolean bF;
    boolean bG;
    Color bH;
    Color bI;
    Font bJ;
    int bK;
    int bL;
    int bM;
    int bN;
    int[] bO;
    int[] bP;
    String[] bQ;
    int bR;
    int bS;
    Font[] bT;
    int bU;
    int bV;
    private int bW;
    int bX;
    int bY;
    int bZ;
    int ca;
    int cb;
    int[] cc;
    int cd;
    int ce;
    float cf;
    float cg;
    int ch;
    int ci;
    String cj;
    int ck;
    int cl;
    int cm;
    int cn;
    int co;
    int cp;
    int cq;
    int cr;
    int cs;
    boolean ct;
    int cu;
    int cv;
    public static int cw;
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if (image == this.v) {
            if (n == 16) {
                this.bc = true;
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
        if (this.v != null) {
            this.v.flush();
        }
        this.v = null;
        if (this.ba != null) {
            this.ba.flush();
        }
        this.ba = null;
        if (this.bb != null) {
            this.bb.dispose();
        }
        this.bb = null;
        System.gc();
    }
    
    public synchronized void prepaniframe() {
        if (this.bd) {
            this.notifyAll();
            while (!this.bc) {
                Thread.yield();
                try {
                    Thread.sleep(8L);
                }
                catch (InterruptedException ex) {}
            }
            this.bc = false;
        }
        this.bb.drawImage(this.v, this.w, this.x, this);
    }
    
    public synchronized boolean CheckAniGIF() {
        this.prepareImage(this.v, this);
        if (this.c) {
            for (int i = 0; i < 3; ++i) {
                this.notifyAll();
                Thread.yield();
                try {
                    Thread.sleep(100L);
                }
                catch (InterruptedException ex) {}
            }
            return this.bc;
        }
        return false;
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
                this.showStatus(c("U9r{`<") + s + c("<:|h%z;fra="));
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
    
    private final void a() {
        while (true) {
            this.showStatus(c("X;};q<&vqjj13krkzrrce v}h27|q%\u007f&vxlh'3plr13uk<\u001cGQI="));
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
        final int cw = zoompan.cw;
        this.setLayout(null);
        this.addNotify();
        this.d = this.getToolkit();
        this.cj = this.getParameter(c("o rhpo9`{"));
        final String parameter;
        final String s = parameter = this.getParameter(c("\u007f&vxlh'"));
        String c = null;
        Label_0117: {
            Label_0086: {
                Label_0082: {
                    if (cw == 0) {
                        if (parameter == null) {
                            break Label_0082;
                        }
                        final String s2;
                        final String protocol = s2 = s;
                        if (cw != 0) {
                            break Label_0117;
                        }
                    }
                    if (parameter.startsWith(c("]$cp`htqe%]:ue%H1rq%4#dk+}:ueqy5~2fs9:"))) {
                        break Label_0086;
                    }
                    this.a();
                    if (cw == 0) {
                        break Label_0086;
                    }
                }
                this.a();
            }
            (this.h = new Lware(this, c("F;|qU}:3}ul8vh"))).hide();
            try {
                final String protocol = this.getDocumentBase().getProtocol();
                c = protocol;
            }
            catch (SecurityException ex) {
                c = c("z=\u007fy");
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
                int n3 = 0;
                int startsWith = 0;
                Label_0236: {
                    Label_0227: {
                        if (cw == 0) {
                            Label_0217: {
                                if (!c.equals(c("z=\u007fy"))) {
                                    int n2;
                                    final int n = n2 = (startsWith = (n3 = s3.length()));
                                    if (cw == 0) {
                                        if (n < 1) {
                                            break Label_0217;
                                        }
                                        final int n4;
                                        n2 = (n4 = (startsWith = (n3 = (s3.startsWith(c("p;p}i")) ? 1 : 0))));
                                    }
                                    if (cw == 0) {
                                        if (n != 0) {
                                            break Label_0217;
                                        }
                                        startsWith = (n2 = (n3 = (s3.equals(c("-f$252d=-")) ? 1 : 0)));
                                    }
                                    if (cw != 0) {
                                        break Label_0236;
                                    }
                                    if (n2 == 0) {
                                        break Label_0227;
                                    }
                                }
                            }
                            this.bf = true;
                        }
                        if (cw == 0) {
                            break Label_0638;
                        }
                    }
                    n3 = (startsWith = (s3.startsWith(c("k#d2")) ? 1 : 0));
                }
                if (cw == 0) {
                    if (startsWith != 0) {
                        s3 = s3.substring(4);
                    }
                    n3 = s3.length();
                }
                final int n6;
                final int n5 = n6 = n3;
                if (cw != 0 || n6 > 0) {
                    final char[] array = new char[n6];
                    s3.getChars(0, n5, array, 0);
                    int n7 = 0;
                    while (true) {
                        while (true) {
                            Label_0315: {
                                if (cw == 0) {
                                    break Label_0315;
                                }
                                final char[] array2 = array;
                                final int n8 = n7;
                                if (cw != 0 || array2[n8] == '0') {
                                    array2[n8] = '1';
                                }
                                n7 += 5;
                            }
                            if (n7 < n5) {
                                continue;
                            }
                            break;
                        }
                        if (cw != 0) {
                            continue;
                        }
                        break;
                    }
                    s3 = new String(array);
                }
                final String s4 = parameter2 = this.getParameter(c("n1t\u007fjx1"));
                if (cw != 0) {
                    break Label_0647;
                }
                if (parameter2 != null) {
                    final String s5 = s4;
                    if (cw != 0) {
                        break Label_0647;
                    }
                    if (s5.length() > 5) {
                        s4.toLowerCase();
                        int n9 = 1;
                        try {
                            int n10 = 0;
                            while (true) {
                                while (true) {
                                    Label_0410: {
                                        if (cw == 0) {
                                            break Label_0410;
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
                                if (cw != 0) {
                                    continue;
                                }
                                break;
                            }
                        }
                        catch (StringIndexOutOfBoundsException ex3) {}
                        final int[] array3 = new int[n9];
                        final int n11 = n9;
                        if (cw == 0 && n11 == 1) {
                            array3[0] = s4.length();
                            if (cw != 0) {
                                goto Label_0460;
                            }
                        }
                        else {
                            int n12 = n11;
                            try {
                                int n13 = 0;
                                while (true) {
                                    while (true) {
                                        Label_0496: {
                                            if (cw == 0) {
                                                break Label_0496;
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
                                    if (cw != 0) {
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
                        int i;
                        while (true) {
                            while (true) {
                                Label_0576: {
                                    if (cw == 0) {
                                        break Label_0576;
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
                            i = 0;
                            if (cw != 0) {
                                if (cw != 0) {
                                    continue;
                                }
                            }
                            break;
                        }
                        while (i < n9) {
                            if (s3.equals(this.h.dr(array4[i], 0, this.bf))) {
                                this.bf = true;
                            }
                            ++i;
                        }
                    }
                }
            }
            this.getParameter(c("n1tplr?"));
        }
        final String s7;
        final String s6 = s7 = parameter2;
        Label_0715: {
            if (cw == 0) {
                if (s7 != null) {
                    final String s8 = s6;
                    if (cw != 0) {
                        break Label_0715;
                    }
                    if (!s8.equalsIgnoreCase(c("R\u001b"))) {
                        try {
                            this.bg = new URL(this.getDocumentBase(), s6);
                        }
                        catch (MalformedURLException ex6) {
                            this.bg = null;
                        }
                    }
                }
                this.getParameter(c("n1tr`k2a}hy"));
            }
        }
        if (s7.equalsIgnoreCase(c("E\u0011@"))) {
            this.bh = true;
        }
        Container parent = this.getParent();
        while (true) {
            while (true) {
                Label_0756: {
                    if (cw == 0) {
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
            (this.F = (Frame)parent).setCursor(3);
            final Container parent2 = this;
            if (cw == 0) {
                final String parameter3 = this.getParameter(c("s\"vnlq3"));
                String p;
                final String s9 = p = parameter3;
                final String s10;
                Label_0938: {
                    if (cw == 0) {
                        zoompan zoompan = null;
                        Label_0935: {
                            if (s9 != null) {
                                s10 = parameter3;
                                if (cw != 0) {
                                    break Label_0938;
                                }
                                if (!s10.equalsIgnoreCase(c("R\u001b"))) {
                                    this.v = this.a(parameter3);
                                    zoompan = this;
                                    if (cw != 0) {
                                        break Label_0935;
                                    }
                                    if (this.v != null) {
                                        final String parameter4;
                                        String s11 = parameter4 = this.getParameter(c("s\"vnlq3K"));
                                        if (cw == 0) {
                                            if (parameter4 == null) {
                                                s11 = "0";
                                            }
                                            this.w = Integer.valueOf(s11);
                                            this.getParameter(c("s\"vnlq3J"));
                                        }
                                        final String s13;
                                        String s12 = s13 = parameter4;
                                        if (cw != 0 || s13 == null) {
                                            s12 = s13;
                                        }
                                        this.x = Integer.valueOf(s12);
                                    }
                                }
                            }
                            this.bi = this.getParameter(c("Q=}O\\R\u0017"));
                            zoompan = this;
                        }
                        final String bi = zoompan.bi;
                    }
                }
                Label_1052: {
                    zoompan zoompan2 = null;
                    Label_1048: {
                        if (cw == 0) {
                            if (s9 == null) {
                                this.bi = c("-d");
                            }
                            this.e = Integer.valueOf(this.bi);
                            this.M = this.getParameter(c("u9r{`"));
                            this.m = this.size().width;
                            this.n = this.size().height;
                            this.P = this.getParameter(c("}!gsay'z{k"));
                            zoompan2 = this;
                            if (cw != 0) {
                                break Label_1048;
                            }
                            p = this.P;
                        }
                        if (s10.equalsIgnoreCase(c("E\u0011@"))) {
                            this.H = true;
                            if (cw == 0) {
                                break Label_1052;
                            }
                        }
                        zoompan2 = this;
                    }
                    zoompan2.H = false;
                }
                this.Y = this.getParameter(c("q1~x`p5j"));
                this.Z = this.getParameter(c("l&zswu j"));
                this.j = Integer.valueOf(this.Y);
                this.k = Integer.valueOf(this.Z);
                int n17;
                final int n16 = n17 = this.j;
                if (cw == 0) {
                    if (n16 < 0) {
                        this.j = 0;
                    }
                    final int k;
                    n17 = (k = this.k);
                }
                final int n18 = 10;
                zoompan zoompan3 = null;
                Label_1208: {
                    Label_1166: {
                        if (cw == 0) {
                            if (n16 > n18) {
                                this.k = 10;
                                if (cw == 0) {
                                    break Label_1166;
                                }
                            }
                            zoompan3 = this;
                            if (cw != 0) {
                                break Label_1208;
                            }
                            n17 = this.k;
                        }
                        if (n17 < n18) {
                            this.k = 1;
                        }
                    }
                    this.o = this.m;
                    this.p = this.n;
                    this.K = this.a(this.M);
                    this.J = this.m * this.n;
                    zoompan3 = this;
                }
                this.bp = Integer.valueOf(zoompan3.getParameter(c("f;|qvl1vx")));
                this.bp *= 20000L;
                this.A = Integer.valueOf(this.getParameter(c("d9|j`o$vya")));
                this.B = Integer.valueOf(this.getParameter(c("e9|j`o$vya")));
                this.y = Integer.valueOf(this.getParameter(c("d6|nay&")));
                this.z = Integer.valueOf(this.getParameter(c("e6|nay&")));
                this.br = Integer.valueOf(this.getParameter(c("q5kfjs9")));
                this.bt = Integer.valueOf(this.getParameter(c("q;ey}")));
                this.bu = Integer.valueOf(this.getParameter(c("q;ey|")));
                this.C = this.getParameter(c("n=ttq\u007f8z\u007fn")).equalsIgnoreCase(c("e1`"));
                this.D = Integer.valueOf(this.getParameter(c("d5p\u007f`p1a}qy")));
                this.E = Integer.valueOf(this.getParameter(c("e5p\u007f`p1a}qy")));
                this.u = this.getParameter(c("o ayq\u007f<")).equalsIgnoreCase(c("E\u0011@"));
                this.q = this.K.getWidth(this);
                this.r = this.K.getHeight(this);
                int n19;
                final boolean b = (n19 = (this.u ? 1 : 0)) != 0;
                Label_1723: {
                    if (cw == 0) {
                        if (b) {
                            this.cm = 0;
                            this.cn = 0;
                            final int n20 = this.q - 1 << 16;
                            this.cp = n20;
                            this.cr = n20;
                            final int n21 = this.r - 1 << 16;
                            this.cq = n21;
                            this.cs = n21;
                            if (cw == 0) {
                                break Label_1723;
                            }
                        }
                        n19 = this.q;
                    }
                    final float n22 = n19 / this.r;
                    final float n23 = this.m / this.n;
                    this.cm = 0;
                    this.cn = 0;
                    if (cw == 0) {
                        if (n23 >= n22) {
                            final int n24 = this.q - 1 << 16;
                            this.cp = n24;
                            this.cr = n24;
                            final int n25 = (int)((this.q - 1) / n23) << 16;
                            this.cq = n25;
                            this.cs = n25;
                            if (cw == 0) {
                                break Label_1723;
                            }
                        }
                        final int n26 = this.r - 1 << 16;
                        this.cq = n26;
                        this.cs = n26;
                    }
                    final int n27 = (int)((this.r - 1) * n23) << 16;
                    this.cp = n27;
                    this.cr = n27;
                }
                this.s = new int[this.q * this.r];
                this.t = new int[this.m * this.n];
                final PixelGrabber pixelGrabber = new PixelGrabber(this.K, 0, 0, this.q, this.r, this.s, 0, this.q);
                try {
                    pixelGrabber.grabPixels();
                }
                catch (InterruptedException ex7) {}
                try {
                    this.b();
                }
                catch (NoSuchMethodError noSuchMethodError) {
                    this.b();
                }
                this.scrollinitial();
                this.ba = this.createImage(this.o, this.p + this.ch);
                this.bb = this.ba.getGraphics();
                return;
            }
            continue;
        }
    }
    
    void b() {
        this.b = new MemoryImageSource(this.m, this.n, new DirectColorModel(24, 16711680, 65280, 255), this.t, 0, this.m);
        String s;
        try {
            s = System.getProperty(c("v5e}+j1aols:"));
        }
        catch (SecurityException ex) {
            s = c("i:x");
        }
        if (!s.startsWith(c("-z#"))) {
            try {
                this.b.setAnimated(true);
                this.b.setFullBufferUpdates(true);
                this.K = this.createImage(this.b);
                this.b.newPixels();
                this.c = true;
            }
            catch (NoSuchMethodError noSuchMethodError) {
                this.c = false;
            }
        }
        if (!this.c) {
            this.b = null;
            this.a = new anfy(this.m, this.n, new DirectColorModel(24, 16711680, 65280, 255), this.t, 0, this.m);
            this.K = this.createImage(this.a);
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
        if (this.v != null && !this.bd) {
            this.bd = this.CheckAniGIF();
        }
        if (this.bg != null) {
            this.F.setCursor(12);
        }
        else {
            this.F.setCursor(0);
        }
        double n = System.currentTimeMillis();
        this.bj = n / 1000.0;
        this.bm = 0;
        this.bn = 10;
        final double bj = this.bj;
        this.bk = bj;
        this.bl = bj;
        long n2 = this.bp << 16;
        this.h.dr(c("}:ue"), 1, this.bf);
        while (this.g != null) {
            ++this.bm;
            final long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - n > 1000.0) {
                this.bn = (int)(this.bm / ((currentTimeMillis - n) / 1000.0));
                n = currentTimeMillis;
                this.bm = 0;
            }
            this.bl = this.bk;
            double n3 = this.bn;
            if (n3 > 60.0) {
                n3 = 60.0;
            }
            this.bk += 1.0 / n3;
            final double n4 = currentTimeMillis / 1000.0;
            if (this.bk > n4) {
                this.bk -= (this.bk - n4) / 5.0;
            }
            if (this.bk < n4) {
                this.bk += (n4 - this.bk) / 5.0;
            }
            long n5 = (long)(this.bp * (this.bk - this.bl)) << 16;
            if (n5 < 0L) {
                n5 = n2;
            }
            else {
                n2 = n5;
            }
            final float n6 = 1.0f / (this.cq >> 16);
            final float n7 = 1.0f / (this.cp >> 16);
            final int cp = this.cp;
            final int cq = this.cq;
            if (this.ct && this.co == 1) {
                if (this.cp > (this.q << 16) / this.br) {
                    final int n8 = (int)(n5 * n6);
                    final int n9 = (int)(n5 * n7);
                    final float n10 = (this.cp >> 16) * (this.cq >> 16) / (4.0f * this.q * this.r);
                    int n11 = (int)(n8 * n10);
                    int n12 = (int)(n9 * n10);
                    this.cp -= n11;
                    this.cq -= n12;
                    if (this.cp <= (this.q << 16) / this.br) {
                        final int n13 = (this.q << 16) / this.br - this.cp;
                        this.cp -= n13;
                        this.cq -= n13;
                        n11 = cp - this.cp;
                        n12 = cq - this.cq;
                    }
                    this.cm += n11 / 2;
                    this.cn += n12 / 2;
                }
            }
            else if (this.ct && this.co == 2 && this.cp < this.cr) {
                final int n14 = (int)(n5 * n6);
                final int n15 = (int)(n5 * n7);
                final float n16 = (this.cp >> 16) * (this.cq >> 16) / (4.0f * this.q * this.r);
                int n17 = (int)(n14 * n16);
                int n18 = (int)(n15 * n16);
                this.cp += n17;
                this.cq += n18;
                if (this.cp >= this.cr) {
                    this.cp = this.cr;
                    this.cq = this.cs;
                    n17 = this.cp - cp;
                    n18 = this.cq - cq;
                }
                this.cm -= n17 / 2;
                this.cn -= n18 / 2;
            }
            if (this.H) {
                this.ct = true;
                final int n19 = 2 * Math.max(this.m, this.n);
                final int n20 = this.A * this.cp / n19;
                final int n21 = this.B * this.cq / n19;
                this.cu = (int)(n20 * Math.sin(this.bv * this.bt / 70.0));
                this.cv = (int)(n21 * Math.cos(this.bv * this.bu / 110.0));
                ++this.bv;
                if (this.co == 1) {
                    if (this.cp <= (this.q << 16) / this.br) {
                        this.co = 2;
                    }
                }
                else if (this.cp >= this.cr) {
                    this.co = 1;
                }
            }
            final int n22 = this.q - 1 << 16;
            final int n23 = this.r - 1 << 16;
            final int cp2 = this.cp;
            final int cq2 = this.cq;
            if (this.cu > 0 && this.cm >= 0 && n22 - this.cm > cp2) {
                this.cm += this.cu;
            }
            if (this.cu < 0 && this.cm > 0) {
                this.cm += this.cu;
            }
            if (this.cv > 0 && this.cn >= 0 && n23 - this.cn > cq2) {
                this.cn += this.cv;
            }
            if (this.cv < 0 && this.cn > 0) {
                this.cn += this.cv;
            }
            if (this.cp < 0) {
                this.cp = 0;
            }
            if (this.cq < 0) {
                this.cq = 0;
            }
            if (this.cm + this.cp > n22) {
                this.cm -= this.cm + this.cp - n22;
            }
            if (this.cn + this.cq > n23) {
                this.cn -= this.cn + this.cq - n23;
            }
            if (this.cm < 0) {
                this.cm = 0;
            }
            if (this.cn < 0) {
                this.cn = 0;
            }
            this.b(this.cm, this.cm + this.cp, this.cn, this.cn + this.cq);
            if (++this.i == this.j) {
                System.gc();
                this.i = 0;
            }
            try {
                this.producefixed();
            }
            catch (NoSuchMethodError noSuchMethodError) {}
            this.paint(graphics);
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
        this.prepareImage(this.K, this.o, this.p, this);
        this.notifyAll();
        while ((checkImage & 0xF0) == 0x0) {
            Thread.yield();
            checkImage = this.checkImage(this.K, this.o, this.p, this);
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
        if (this.K != null) {
            this.bb.drawImage(this.K, 0, 0, this);
            if (this.v != null) {
                this.prepaniframe();
            }
            if (this.bF) {
                this.scrolltext(this.bb);
            }
            graphics.drawImage(this.ba, 0, 0, this);
        }
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void scrollinitial() {
        this.bF = false;
        final String parameter = this.getParameter(c("h1khv\u007f&|pi"));
        if (parameter != null && !parameter.equalsIgnoreCase(c("R\u001b"))) {
            String s = this.getParameter(c("h1khqe$v"));
            if (s == null) {
                s = c("t;au\u007fs:g}i");
            }
            if (s.equals(c("t;au\u007fs:g}i"))) {
                this.bR = 0;
            }
            else if (s.equals(c("j1ahl\u007f5\u007f"))) {
                this.bR = 1;
            }
            else if (s.equals(c("f;|qlr3"))) {
                this.bR = 2;
            }
            else if (s.equals(c("u:efjs9zrb"))) {
                this.bR = 3;
            }
            if (this.bR == 0) {
                this.GetTheString(parameter, 0);
                if (this.bD != null) {
                    this.bF = true;
                }
            }
            else {
                this.GetTheString(parameter, 1);
                if (this.bQ != null) {
                    this.bF = true;
                }
            }
        }
        if (this.bF) {
            String parameter2 = this.getParameter(c("h1khvl1vx"));
            if (parameter2 == null) {
                parameter2 = "0";
            }
            this.bE = Integer.valueOf(parameter2);
            String s2 = this.getParameter(c("h1khcs:g"));
            if (s2 == null) {
                s2 = c("]&z}i");
            }
            int n = 0;
            if (this.getParameter(c("h1khgs8w")).equalsIgnoreCase(c("E\u0011@"))) {
                ++n;
            }
            String s3 = this.getParameter(c("h1khlh5\u007fuf"));
            if (s3 == null) {
                s3 = c("R\u001b");
            }
            if (s3.equalsIgnoreCase(c("E\u0011@"))) {
                n += 2;
            }
            String s4 = this.getParameter(c("h1khvu.v"));
            if (s4 == null) {
                s4 = c("-f");
            }
            this.bJ = new Font(s2, n, Integer.valueOf(s4));
            if (this.getParameter(c("h1khvt5wsr")).equalsIgnoreCase(c("E\u0011@"))) {
                this.bG = true;
            }
            else {
                this.bG = false;
            }
            this.bH = new Color(Integer.valueOf(this.getParameter(c("H1khFs8A"))), Integer.valueOf(this.getParameter(c("H1khFs8T"))), Integer.valueOf(this.getParameter(c("H1khFs8Q"))));
            this.bI = new Color(Integer.valueOf(this.getParameter(c("H1khV_;\u007fN"))), Integer.valueOf(this.getParameter(c("H1khV_;\u007f["))), Integer.valueOf(this.getParameter(c("H1khV_;\u007f^"))));
            this.bw = this.size().width;
            this.bx = this.size().height;
            if (this.bR == 0) {
                String parameter3 = this.getParameter(c("h1khjz2`yq"));
                if (parameter3 == null) {
                    parameter3 = "0";
                }
                this.bz = Integer.valueOf(parameter3);
                if (this.bz < 0) {
                    this.bz = 0;
                }
                String parameter4 = this.getParameter(c("H1khOi9c]hl"));
                if (parameter4 == null) {
                    parameter4 = "0";
                }
                this.bW = Integer.valueOf(parameter4);
                String parameter5 = this.getParameter(c("H1khOi9cOux"));
                if (parameter5 == null) {
                    parameter5 = "0";
                }
                this.bZ = Integer.valueOf(parameter5);
                String parameter6 = this.getParameter(c("H1khVu:v]hl"));
                if (parameter6 == null) {
                    parameter6 = "0";
                }
                this.bK = Integer.valueOf(parameter6);
                String parameter7 = this.getParameter(c("H1khVu:vOux"));
                if (parameter7 == null) {
                    parameter7 = "0";
                }
                this.bL = Integer.valueOf(parameter7);
                String parameter8 = this.getParameter(c("H1khVu:v]k{8v"));
                if (parameter8 == null) {
                    parameter8 = "0";
                }
                this.bM = Integer.valueOf(parameter8);
                final FontMetrics fontMetrics = this.getGraphics().getFontMetrics(this.bJ);
                this.bA = fontMetrics.stringWidth(this.bD);
                this.bB = fontMetrics.getHeight();
                this.bC = fontMetrics.getMaxDescent();
                this.by = this.bw;
                if (this.bz < this.bB - this.bC) {
                    this.bz = this.bB - this.bC;
                }
                else if (this.bz > this.bx - this.bC) {
                    this.bz = this.bx - this.bC;
                }
                if (this.bK != 0) {
                    this.bO = new int[this.bw + 360];
                    this.bP = new int[this.bw + 360];
                    for (int i = 0; i < this.bw + 360; ++i) {
                        this.bO[i] = (int)(this.bK * Math.sin(this.bM * i * 3.141592653589793 / 180.0)) - this.bB - this.bC + this.bz;
                        this.bP[i] = this.bO[i] - this.p;
                    }
                    this.bN = 360;
                    this.ch = this.bB + this.bC + 1;
                    this.ci = this.ch - 1;
                }
            }
            else {
                if (this.bR == 1) {
                    String s5 = this.getParameter(c("h1khso$r\u007f`"));
                    if (s5 == null) {
                        s5 = c("-d");
                    }
                    final int intValue = Integer.valueOf(s5);
                    final FontMetrics fontMetrics2 = this.getGraphics().getFontMetrics(this.bJ);
                    this.cb = fontMetrics2.getHeight() + intValue;
                    this.cc = new int[this.bQ.length];
                    this.G = 0;
                    while (this.G < this.bQ.length) {
                        this.cc[this.G] = (this.bw - fontMetrics2.stringWidth(this.bQ[this.G])) / 2;
                        ++this.G;
                    }
                    this.ca = -this.cb;
                    return;
                }
                if (this.bR >= 2) {
                    String parameter9 = this.getParameter(c("h1khhu:uskh"));
                    if (parameter9 == null) {
                        parameter9 = "2";
                    }
                    this.bU = Integer.valueOf(parameter9);
                    String s6 = this.getParameter(c("h1khh},uskh"));
                    if (s6 == null) {
                        s6 = c("+f");
                    }
                    this.bV = Integer.valueOf(s6);
                    this.bS = this.bV - this.bU;
                    this.bJ = null;
                    this.bT = new Font[this.bS];
                    int bu = this.bU;
                    this.G = 0;
                    while (this.G < this.bS) {
                        this.bT[this.G] = new Font(s2, n, bu++);
                        ++this.G;
                    }
                    this.cf = this.bw / 2.0f;
                    this.cg = this.bx / 2.0f;
                    if (this.bR == 3) {
                        this.cd = this.bS - 1;
                        return;
                    }
                    this.cd = 0;
                }
            }
        }
    }
    
    public void scrolltext(final Graphics graphics) {
        switch (this.bR) {
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
        graphics.setFont(this.bJ);
        this.ca += this.bE;
        if (this.ca > this.bx + this.bQ.length * this.cb) {
            this.ca = -this.cb;
        }
        if (this.bG) {
            for (int i = 0; i < this.bQ.length; ++i) {
                final String s = this.bQ[i];
                final int n = this.cc[i];
                final int n2 = this.bx - this.ca + i * this.cb;
                graphics.setColor(this.bI);
                graphics.drawString(s, n + 1, n2 + 1);
                graphics.setColor(this.bH);
                graphics.drawString(s, n, n2);
            }
            return;
        }
        graphics.setColor(this.bH);
        for (int j = 0; j < this.bQ.length; ++j) {
            graphics.drawString(this.bQ[j], this.cc[j], this.bx - this.ca + j * this.cb);
        }
    }
    
    public void zoomscroll(final Graphics graphics) {
        final String s = this.bQ[this.ce];
        graphics.setFont(this.bT[this.cd]);
        final FontMetrics fontMetrics = graphics.getFontMetrics(this.bT[this.cd]);
        final int n = (int)(this.cf - fontMetrics.stringWidth(s) / 2.0f);
        final int n2 = (int)(this.cg + fontMetrics.getHeight() / 4.0f);
        if (this.bG) {
            graphics.setColor(this.bI);
            graphics.drawString(s, n + 1, n2 + 1);
        }
        graphics.setColor(this.bH);
        graphics.drawString(s, n, n2);
        if (this.bR == 3) {
            this.cd -= this.bE;
            if (this.cd <= 1) {
                this.cd = this.bS - 1;
                ++this.ce;
                if (this.ce >= this.bQ.length) {
                    this.ce = 0;
                }
            }
        }
        else {
            this.cd += this.bE;
            if (this.cd >= this.bS) {
                this.cd = 0;
                ++this.ce;
                if (this.ce >= this.bQ.length) {
                    this.ce = 0;
                }
            }
        }
    }
    
    public void horizscroll(final Graphics graphics) {
        graphics.setFont(this.bJ);
        if (this.bW == 0) {
            this.bX = this.bz;
        }
        else {
            this.bY += this.bZ;
            this.bX = this.bz - (int)Math.abs(this.bW * Math.sin(this.bY / 90.0 * 3.141592653589793));
        }
        if (this.bK != 0) {
            for (int i = 0; i < this.bw; ++i) {
                final int n = this.bO[this.bN + i];
                graphics.copyArea(i, n, 1, this.ch, 0, this.p - n);
            }
            if (this.bG) {
                graphics.setColor(this.bI);
                graphics.drawString(this.bD, this.by + 1, this.p + this.bB + 1);
            }
            graphics.setColor(this.bH);
            graphics.drawString(this.bD, this.by, this.p + this.bB);
            for (int j = 0; j < this.bw; ++j) {
                graphics.copyArea(j, this.p, 1, this.ci, 0, this.bP[this.bN + j]);
            }
            this.bN -= this.bL;
            if (this.bN < 0) {
                this.bN += 360;
            }
        }
        else {
            if (this.bG) {
                graphics.setColor(this.bI);
                graphics.drawString(this.bD, this.by + 1, this.bX + 1);
            }
            graphics.setColor(this.bH);
            graphics.drawString(this.bD, this.by, this.bX);
        }
        this.by -= this.bE;
        if (this.by < -this.bA) {
            this.by = this.bw;
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
                            this.bD = new String(byteArray);
                            return;
                        }
                        catch (NoSuchMethodError noSuchMethodError) {
                            this.bD = new String(byteArray, 0);
                            return;
                        }
                    }
                    int n3 = 1;
                    for (int j = 0; j < n2; ++j) {
                        if (byteArray[j] == 10) {
                            ++n3;
                        }
                    }
                    this.bQ = new String[n3 - 1];
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
                                this.bQ[l] = new String(byteArray, array2[l], array3[l]);
                            }
                            catch (NoSuchMethodError noSuchMethodError2) {
                                this.bQ[l] = new String(byteArray, 0, array2[l], array3[l]);
                            }
                        }
                    }
                    catch (StringIndexOutOfBoundsException ex) {
                        this.bQ = null;
                    }
                }
                catch (IOException ex2) {}
            }
            catch (IOException ex3) {}
        }
        catch (MalformedURLException ex4) {}
    }
    
    public final boolean mouseEnter(final Event event, final int n, final int n2) {
        this.showStatus(this.cj);
        return true;
    }
    
    public final boolean mouseExit(final Event event, final int n, final int n2) {
        this.showStatus("");
        return true;
    }
    
    public final synchronized boolean mouseDown(final Event event, final int n, final int n2) {
        final int cw = zoompan.cw;
        this.ct = true;
        int n3;
        final boolean b = (n3 = (this.C ? 1 : 0)) != 0;
        Label_0073: {
            if (cw == 0) {
                if (b) {
                    if (event.modifiers == 4) {
                        this.co = 2;
                        if (cw == 0) {
                            break Label_0073;
                        }
                    }
                    this.co = 1;
                    if (cw == 0) {
                        break Label_0073;
                    }
                }
                n3 = event.clickCount;
            }
            if (n3 == 1) {
                this.co = 1;
                if (cw == 0) {
                    break Label_0073;
                }
            }
            this.co = 2;
        }
        zoompan zoompan = this;
        zoompan zoompan2 = this;
        zoompan zoompan3 = this;
        Label_0128: {
            if (cw != 0) {
                break Label_0128;
            }
            if (!this.bf) {
                this.h.show();
                try {
                    this.h.move(100, 100);
                }
                catch (Exception ex) {}
                this.h.toFront();
                this.h.requestFocus();
                if (cw == 0) {
                    return true;
                }
            }
            try {
                zoompan = this;
                zoompan2 = this;
                zoompan3 = this;
                Label_0147: {
                    if (cw != 0) {
                        break Label_0147;
                    }
                    if (zoompan3.bg == null) {
                        return true;
                    }
                    try {
                        this.h.dck();
                        zoompan = this;
                        zoompan2 = this;
                        if (cw == 0) {
                            if (zoompan2.bh) {
                                this.getAppletContext().showDocument(this.bg, this.getParameter(c("n1tzw}9vrdq1")));
                                if (cw == 0) {
                                    return true;
                                }
                            }
                            zoompan = this;
                        }
                        zoompan.getAppletContext().showDocument(this.bg);
                    }
                    catch (Exception ex2) {}
                }
            }
            catch (Exception ex3) {}
        }
        return true;
    }
    
    public synchronized boolean mouseDrag(final Event event, final int n, final int n2) {
        return this.mouseMove(event, n, n2);
    }
    
    public synchronized boolean mouseMove(final Event event, final int n, final int n2) {
        final boolean b = false;
        this.cv = (b ? 1 : 0);
        this.cu = (b ? 1 : 0);
        final int n3 = 2 * Math.max(this.m, this.n);
        final int n4 = this.A * this.cp / n3;
        final int n5 = this.B * this.cq / n3;
        if (n2 < this.z) {
            this.cv = -n5 + -n5 * (this.z - n2) * this.E / 100;
        }
        if (n2 > this.n - this.z) {
            this.cv = n5 + n5 * (n2 - this.n + this.z) * this.E / 100;
        }
        if (n < this.y) {
            this.cu = -n4 + -n4 * (this.y - n) * this.D / 100;
        }
        if (n > this.m - this.y) {
            this.cu = n4 + n4 * (n - this.m + this.y) * this.D / 100;
        }
        if (n < this.y || n > this.m - this.y || n2 < this.z || n2 > this.n - this.z) {
            this.F.setCursor(13);
        }
        else {
            this.F.setCursor(0);
        }
        return true;
    }
    
    public final synchronized boolean mouseUp(final Event event, final int n, final int n2) {
        this.ct = false;
        return true;
    }
    
    private final void b(final int n, int n2, final int n3, int n4) {
        final int[] t = this.t;
        final int[] s = this.s;
        if (n2 >> 16 >= this.q) {
            n2 = this.q - 1 << 16;
        }
        if (n4 >> 16 >= this.r) {
            n4 = this.r - 1 << 16;
        }
        final int q = this.q;
        int m = this.m;
        int n5 = this.n;
        final int n6 = (n2 - n) / m;
        final int n7 = (n4 - n3) / n5;
        int n8 = 0;
        int n9 = n3;
        final int n10 = q;
        final int n11 = n10 + 1;
        while (--n5 >= 0) {
            int n12 = n;
            final int n13 = (n9 >> 16) * q;
            int n14 = n8;
            n8 += m;
            while (--m >= 0) {
                final int n15 = (n12 >> 16) + n13;
                final int n16 = n12 >> 8 & 0xFF;
                final int n17 = n9 >> 8 & 0xFF;
                final int n18 = s[n15];
                final int n19 = n18 & 0xFF00;
                final int n20 = n18 & 0xFF00FF;
                final int n21 = s[n15 + n10];
                final int n22 = n20 + (n17 * ((n21 & 0xFF00FF) - n20) >>> 8) & 0xFF00FF;
                final int n23 = n19 + (n17 * ((n21 & 0xFF00) - n19) >>> 8) & 0xFF00;
                final int n24 = s[n15 + 1];
                final int n25 = n24 & 0xFF00;
                final int n26 = n24 & 0xFF00FF;
                final int n27 = s[n15 + n11];
                t[n14++] = ((n22 + (n16 * ((n26 + (n17 * ((n27 & 0xFF00FF) - n26) >> 8) & 0xFF00FF) - n22) >> 8) & 0xFF00FF) | (n23 + (n16 * ((n25 + (n17 * ((n27 & 0xFF00) - n25) >> 8) & 0xFF00) - n23) >> 8) & 0xFF00));
                n12 += n6;
            }
            n9 += n7;
        }
    }
    
    public zoompan() {
        this.c = false;
        this.u = false;
        this.y = 50;
        this.z = 50;
        this.A = 1;
        this.B = 1;
        this.C = false;
        this.H = false;
        this.bc = false;
        this.bd = false;
        this.bf = false;
        this.bh = false;
        this.bp = 400000L;
        this.br = 100;
        this.bs = 1;
        this.bt = 1;
        this.bu = 1;
        this.ct = false;
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
                    c2 = '\u001c';
                    break;
                }
                case 1: {
                    c2 = 'T';
                    break;
                }
                case 2: {
                    c2 = '\u0013';
                    break;
                }
                case 3: {
                    c2 = '\u001c';
                    break;
                }
                default: {
                    c2 = '\u0005';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
