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
import java.awt.Image;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.image.MemoryImageSource;
import java.awt.image.ImageObserver;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class huerot extends Applet implements Runnable, ImageObserver
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
    int l;
    int m;
    int n;
    int o;
    int p;
    float q;
    Frame r;
    private Image s;
    int t;
    int u;
    int[] v;
    float[] w;
    float[] x;
    int[] y;
    int[] z;
    int A;
    int B;
    int C;
    private Image D;
    private Graphics E;
    String F;
    String G;
    String H;
    String I;
    String J;
    String K;
    private Image L;
    private Graphics M;
    boolean N;
    boolean O;
    final String P = "wC\u0017\u001f#B\u0013\u0005\nfpR\u0005\u001a)\u0016p\u000e\u0006%UZG[1ADI\u0012(P";
    boolean Q;
    URL R;
    boolean S;
    float T;
    int U;
    int V;
    int W;
    int X;
    int Y;
    int Z;
    int ba;
    String bb;
    int bc;
    boolean bd;
    boolean be;
    Color bf;
    Color bg;
    Font bh;
    int bi;
    int bj;
    int bk;
    int bl;
    int[] bm;
    int[] bn;
    String[] bo;
    int bp;
    int bq;
    Font[] br;
    int bs;
    int bt;
    private int bu;
    int bv;
    int bw;
    int bx;
    int by;
    int bz;
    int[] bA;
    int bB;
    int bC;
    float bD;
    float bE;
    int bF;
    int bG;
    String bH;
    public static int bI;
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if (image == this.s) {
            if (n == 16) {
                this.N = true;
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
        if (this.s != null) {
            this.s.flush();
        }
        this.s = null;
        if (this.L != null) {
            this.L.flush();
        }
        this.L = null;
        if (this.M != null) {
            this.M.dispose();
        }
        this.M = null;
        System.gc();
    }
    
    public synchronized void prepaniframe() {
        if (this.O) {
            this.notifyAll();
            while (!this.N) {
                Thread.yield();
                try {
                    Thread.sleep(8L);
                }
                catch (InterruptedException ex) {}
            }
            this.N = false;
        }
        this.M.drawImage(this.s, this.t, this.u, this);
    }
    
    public synchronized boolean CheckAniGIF() {
        this.prepareImage(this.s, this);
        if (this.c) {
            for (int i = 0; i < 3; ++i) {
                this.notifyAll();
                Thread.yield();
                try {
                    Thread.sleep(100L);
                }
                catch (InterruptedException ex) {}
            }
            return this.N;
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
                this.showStatus(c("\u007f^\u0006\u0014#\u0016") + s + c("\u0016]\b\u0007fP\\\u0012\u001d\"\u0017"));
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
            this.showStatus(c("r\\\tT2\u0016A\u0002\u001e)@VG\u00041A\u001d\u0006\u001d OG\u0002\u0012+\u0018P\b\u001efUA\u0002\u0017/B@G\u001f/XVG\u001a(\u0016{3>\n\u0017"));
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
        final int bi = huerot.bI;
        this.setLayout(null);
        this.addNotify();
        this.d = this.getToolkit();
        this.bH = this.getParameter(c("EG\u0006\u00073E^\u0014\u0014"));
        final String parameter;
        final String s = parameter = this.getParameter(c("UA\u0002\u0017/B@"));
        String c = null;
        Label_0117: {
            Label_0086: {
                Label_0082: {
                    if (bi == 0) {
                        if (parameter == null) {
                            break Label_0082;
                        }
                        final String s2;
                        final String protocol = s2 = s;
                        if (bi != 0) {
                            break Label_0117;
                        }
                    }
                    if (parameter.startsWith(c("wC\u0017\u001f#B\u0013\u0005\nfpR\u0005\u001a)\u0016p\u000e\u0006%UZG[1ADI\u0012(P"))) {
                        break Label_0086;
                    }
                    this.a();
                    if (bi == 0) {
                        break Label_0086;
                    }
                }
                this.a();
            }
            (this.h = new Lware(this, c("~F\u0002!)BR\u0013\u001c4\u0016R\u0017\u0003*SG"))).hide();
            try {
                final String protocol = this.getDocumentBase().getProtocol();
                c = protocol;
            }
            catch (SecurityException ex) {
                c = c("PZ\u000b\u0016");
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
        Label_0644: {
            Label_0635: {
                int n3 = 0;
                int startsWith = 0;
                Label_0236: {
                    Label_0227: {
                        if (bi == 0) {
                            Label_0217: {
                                if (!c.equals(c("PZ\u000b\u0016"))) {
                                    final int length = s3.length();
                                    int n = 0;
                                    Label_0209: {
                                        if (bi == 0) {
                                            if (length < 1) {
                                                break Label_0217;
                                            }
                                            final int n2;
                                            n = (n2 = (startsWith = (n3 = (s3.startsWith(c("Z\\\u0004\u0012*")) ? 1 : 0))));
                                            if (bi != 0) {
                                                break Label_0209;
                                            }
                                        }
                                        if (length != 0) {
                                            break Label_0217;
                                        }
                                        startsWith = (n = (n3 = (s3.equals(c("\u0007\u0001P]v\u0018\u0003IB")) ? 1 : 0)));
                                    }
                                    if (bi != 0) {
                                        break Label_0236;
                                    }
                                    if (n == 0) {
                                        break Label_0227;
                                    }
                                }
                            }
                            this.Q = true;
                        }
                        if (bi == 0) {
                            break Label_0635;
                        }
                    }
                    n3 = (startsWith = (s3.startsWith(c("AD\u0010]")) ? 1 : 0));
                }
                if (bi == 0) {
                    if (startsWith != 0) {
                        s3 = s3.substring(4);
                    }
                    n3 = s3.length();
                }
                final int n5;
                final int n4 = n5 = n3;
                if (bi != 0 || n5 > 0) {
                    final char[] array = new char[n5];
                    s3.getChars(0, n4, array, 0);
                    int n6 = 0;
                    while (true) {
                        while (true) {
                            Label_0315: {
                                if (bi == 0) {
                                    break Label_0315;
                                }
                                final char[] array2 = array;
                                final int n7 = n6;
                                if (bi != 0 || array2[n7] == '0') {
                                    array2[n7] = '1';
                                }
                                n6 += 5;
                            }
                            if (n6 < n4) {
                                continue;
                            }
                            break;
                        }
                        if (bi != 0) {
                            continue;
                        }
                        break;
                    }
                    s3 = new String(array);
                }
                final String s4 = parameter2 = this.getParameter(c("DV\u0000\u0010)RV"));
                if (bi != 0) {
                    break Label_0644;
                }
                if (parameter2 != null) {
                    final String s5 = s4;
                    if (bi != 0) {
                        break Label_0644;
                    }
                    if (s5.length() > 5) {
                        s4.toLowerCase();
                        int n8 = 1;
                        try {
                            int n9 = 0;
                            while (true) {
                                while (true) {
                                    Label_0410: {
                                        if (bi == 0) {
                                            break Label_0410;
                                        }
                                        if (s4.charAt(n9) == '+') {
                                            ++n8;
                                        }
                                        ++n9;
                                    }
                                    if (n9 < s4.length()) {
                                        continue;
                                    }
                                    break;
                                }
                                if (bi != 0) {
                                    continue;
                                }
                                break;
                            }
                        }
                        catch (StringIndexOutOfBoundsException ex3) {}
                        final int[] array3 = new int[n8];
                        final int n10 = n8;
                        if (bi == 0 && n10 == 1) {
                            array3[0] = s4.length();
                            if (bi != 0) {
                                goto Label_0460;
                            }
                        }
                        else {
                            int n11 = n10;
                            try {
                                int n12 = 0;
                                while (true) {
                                    while (true) {
                                        Label_0496: {
                                            if (bi == 0) {
                                                break Label_0496;
                                            }
                                            if (s4.charAt(n12) == '+') {
                                                array3[n11] = n12;
                                                ++n11;
                                            }
                                            ++n12;
                                        }
                                        if (n12 < s4.length()) {
                                            continue;
                                        }
                                        break;
                                    }
                                    if (bi != 0) {
                                        continue;
                                    }
                                    break;
                                }
                            }
                            catch (StringIndexOutOfBoundsException ex4) {}
                            array3[n11] = s4.length();
                        }
                        final String[] array4 = new String[n8];
                        int n13 = 0;
                        int n14 = 0;
                        int n15;
                        while (true) {
                            while (true) {
                                Label_0576: {
                                    if (bi == 0) {
                                        break Label_0576;
                                    }
                                    try {
                                        array4[n14] = s4.substring(n13, array3[n14]);
                                    }
                                    catch (StringIndexOutOfBoundsException ex5) {}
                                    n13 = array3[n14] + 1;
                                    ++n14;
                                }
                                if (n14 < n8) {
                                    continue;
                                }
                                break;
                            }
                            n15 = 0;
                            if (bi != 0) {
                                continue;
                            }
                            break;
                        }
                        while (true) {
                            Label_0628: {
                                if (bi == 0) {
                                    break Label_0628;
                                }
                                if (s3.equals(this.h.dr(array4[n15], 0, this.Q))) {
                                    this.Q = true;
                                }
                                ++n15;
                            }
                            if (n15 < n8) {
                                continue;
                            }
                            break;
                        }
                    }
                }
            }
            this.getParameter(c("DV\u0000\u001f/XX"));
        }
        final String s7;
        final String s6 = s7 = parameter2;
        Label_0712: {
            if (bi == 0) {
                if (s7 != null) {
                    final String s8 = s6;
                    if (bi != 0) {
                        break Label_0712;
                    }
                    if (!s8.equalsIgnoreCase(c("x|"))) {
                        try {
                            this.R = new URL(this.getDocumentBase(), s6);
                        }
                        catch (MalformedURLException ex6) {
                            this.R = null;
                        }
                    }
                }
                this.getParameter(c("DV\u0000\u001d#AU\u0015\u0012+S"));
            }
        }
        if (s7.equalsIgnoreCase(c("ov4"))) {
            this.S = true;
        }
        Container parent = this.getParent();
        while (true) {
            while (true) {
                Label_0753: {
                    if (bi == 0) {
                        break Label_0753;
                    }
                    final Container parent2 = ((Frame)parent).getParent();
                    parent = parent2;
                }
                if (!(parent instanceof Frame)) {
                    continue;
                }
                break;
            }
            (this.r = (Frame)parent).setCursor(3);
            final Container parent2 = this;
            if (bi != 0) {
                continue;
            }
            break;
        }
        final String parameter3;
        final String s9 = parameter3 = this.getParameter(c("YE\u0002\u0001/[T"));
        String s15 = null;
        String g = null;
        Label_0996: {
            Label_0940: {
                if (bi == 0) {
                    huerot huerot = null;
                    Label_0932: {
                        if (parameter3 != null) {
                            final String s10 = s9;
                            if (bi != 0) {
                                break Label_0940;
                            }
                            if (!s10.equalsIgnoreCase(c("x|"))) {
                                this.s = this.a(s9);
                                huerot = this;
                                if (bi != 0) {
                                    break Label_0932;
                                }
                                if (this.s != null) {
                                    final String parameter4;
                                    String s11 = parameter4 = this.getParameter(c("YE\u0002\u0001/[T?"));
                                    if (bi == 0) {
                                        if (parameter4 == null) {
                                            s11 = "0";
                                        }
                                        this.t = Integer.valueOf(s11);
                                        this.getParameter(c("YE\u0002\u0001/[T>"));
                                    }
                                    final String s13;
                                    String s12 = s13 = parameter4;
                                    if (bi != 0 || s13 == null) {
                                        s12 = s13;
                                    }
                                    this.u = Integer.valueOf(s12);
                                }
                            }
                        }
                        this.K = this.getParameter(c("{Z\t \u001fxp"));
                        huerot = this;
                    }
                    final String s14;
                    g = (s14 = (s15 = huerot.K));
                    if (bi != 0) {
                        break Label_0996;
                    }
                }
            }
            if (parameter3 == null) {
                this.K = c("\u0007\u0003");
            }
            this.e = Integer.valueOf(this.K);
            this.F = this.getParameter(c("_^\u0006\u0014#"));
            this.G = this.getParameter(c("DV\u0014"));
            s15 = (g = this.G);
        }
        huerot huerot2 = null;
        Label_1116: {
            if (bi == 0) {
                if (g == null) {
                    this.G = "1";
                }
                this.H = this.getParameter(c("^F\u0002\u0000._U\u0013"));
                huerot2 = this;
                if (bi != 0) {
                    break Label_1116;
                }
                s15 = this.H;
            }
            if (s15 == null) {
                this.H = c("\u0007\u0003");
            }
            this.q = Float.valueOf(this.H) / 10000.0f;
            this.I = this.getParameter(c("[V\n\u0017#ZR\u001e"));
            this.J = this.getParameter(c("FA\u000e\u001c4_G\u001e"));
            this.j = Integer.valueOf(this.I);
            this.k = Integer.valueOf(this.J);
            huerot2 = this;
        }
        int n17;
        int k;
        final int n16 = k = (n17 = huerot2.j);
        if (bi == 0) {
            if (n16 < 0) {
                this.j = 0;
            }
            final int n18;
            k = (n18 = (n17 = this.k));
        }
        int n20;
        final int n19 = n20 = 10;
        int c2 = 0;
        Label_1328: {
            Label_1232: {
                int p = 0;
                int n22 = 0;
                Label_1224: {
                    Label_1200: {
                        Label_1175: {
                            if (bi == 0) {
                                if (n16 > n19) {
                                    this.k = 10;
                                    if (bi == 0) {
                                        break Label_1175;
                                    }
                                }
                                n17 = (k = this.k);
                                final int n21;
                                n20 = (n21 = 1);
                            }
                            if (bi != 0) {
                                break Label_1200;
                            }
                            if (k < n19) {
                                this.k = 1;
                            }
                        }
                        this.p = Integer.valueOf(this.G);
                        p = (n17 = this.p);
                        n22 = (n20 = 8);
                        if (bi != 0) {
                            break Label_1224;
                        }
                    }
                    if (n17 > n20) {
                        this.p = 8;
                        if (bi == 0) {
                            break Label_1232;
                        }
                    }
                    c2 = (p = this.p);
                    if (bi != 0) {
                        break Label_1328;
                    }
                    n22 = 1;
                }
                if (p < n22) {
                    this.p = 1;
                }
            }
            this.l = this.size().width / this.p;
            this.m = this.size().height / this.p;
            this.n = this.l * this.p;
            this.o = this.m * this.p;
            this.showStatus(c("z\\\u0006\u0017/XTG\u001a+WT\u0002]h\u0018"));
            this.D = this.a(this.F);
            this.C = this.l * this.m;
            c2 = this.C;
        }
        final int[] array5 = new int[c2];
        this.v = new int[this.C];
        final PixelGrabber pixelGrabber = new PixelGrabber(this.D, 0, 0, this.l, this.m, array5, 0, this.l);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex7) {}
        this.w = new float[this.C];
        this.x = new float[this.C];
        this.y = new int[this.C];
        this.z = new int[this.C];
        float[] rgBtoHSB = new float[3];
        int n23 = 0;
        while (true) {
            Label_1538: {
                if (bi == 0) {
                    break Label_1538;
                }
                final int n24 = array5[n23];
                rgBtoHSB = Color.RGBtoHSB((n24 & 0xFF0000) >> 16, (n24 & 0xFF00) >> 8, n24 & 0xFF, rgBtoHSB);
                this.w[n23] = rgBtoHSB[0];
                this.x[n23] = rgBtoHSB[1];
                this.y[n23] = (int)(rgBtoHSB[2] * 255.0f);
                this.z[n23] = (int)(this.y[n23] * (1.0 - this.x[n23]));
                ++n23;
            }
            if (n23 >= this.C) {
                try {
                    this.b();
                }
                catch (NoSuchMethodError noSuchMethodError) {
                    this.b();
                }
                this.scrollinitial();
                this.L = this.createImage(this.n, this.o + this.bF);
                this.M = this.L.getGraphics();
                return;
            }
            continue;
        }
    }
    
    void b() {
        this.b = new MemoryImageSource(this.l, this.m, new DirectColorModel(24, 16711680, 65280, 255), this.v, 0, this.l);
        String s;
        try {
            s = System.getProperty(c("\\R\u0011\u0012h@V\u0015\u0000/Y]"));
        }
        catch (SecurityException ex) {
            s = c("C]\f");
        }
        if (!s.startsWith(c("\u0007\u001dW"))) {
            try {
                this.b.setAnimated(true);
                this.b.setFullBufferUpdates(true);
                this.D = this.createImage(this.b);
                this.b.newPixels();
                this.c = true;
            }
            catch (NoSuchMethodError noSuchMethodError) {
                this.c = false;
            }
        }
        if (!this.c) {
            this.b = null;
            this.a = new anfy(this.l, this.m, new DirectColorModel(24, 16711680, 65280, 255), this.v, 0, this.l);
            this.D = this.createImage(this.a);
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
        if (this.s != null && !this.O) {
            this.O = this.CheckAniGIF();
        }
        if (this.R != null) {
            this.r.setCursor(12);
        }
        else {
            this.r.setCursor(0);
        }
        this.h.dr(c("W]\u0001\n"), 1, this.Q);
        while (this.g != null) {
            this.c();
            if (++this.i == this.j) {
                System.gc();
                this.i = 0;
            }
            try {
                this.producefixed();
            }
            catch (NoSuchMethodError noSuchMethodError) {}
            if (this.p == 1) {
                this.M.drawImage(this.D, 0, 0, this);
            }
            else {
                this.prepscaled();
                this.M.drawImage(this.D, 0, 0, this.n, this.o, this);
            }
            if (this.s != null) {
                this.prepaniframe();
            }
            if (this.bd) {
                this.scrolltext(this.M);
            }
            graphics.drawImage(this.L, 0, 0, this);
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
        this.prepareImage(this.D, this.n, this.o, this);
        this.notifyAll();
        while ((checkImage & 0xF0) == 0x0) {
            Thread.yield();
            checkImage = this.checkImage(this.D, this.n, this.o, this);
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
        if (this.D != null) {
            if (this.p == 1) {
                this.M.drawImage(this.D, 0, 0, this);
            }
            else {
                this.prepscaled();
                this.M.drawImage(this.D, 0, 0, this.n, this.o, this);
            }
            if (this.s != null) {
                this.prepaniframe();
            }
            if (this.bd) {
                this.scrolltext(this.M);
            }
            graphics.drawImage(this.L, 0, 0, this);
        }
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    void c() {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        this.T += this.q;
        final float t = this.T;
        final int c = this.C;
        final int[] y = this.y;
        final float[] x = this.x;
        final int[] z = this.z;
        final int[] v = this.v;
        for (int i = 0; i < c; ++i) {
            final int n4 = y[i];
            final float n5 = x[i];
            if (n5 == 0.0f) {
                n2 = (n = (n3 = n4));
            }
            else {
                final float n6 = this.w[i] + t;
                final float n7 = (n6 - (int)n6) * 6.0f;
                final float n8 = n7 - (int)n7;
                switch ((int)n7) {
                    case 0: {
                        n = n4;
                        n2 = (int)(n4 * (1.0f - n5 * (1.0f - n8)));
                        n3 = z[i];
                        break;
                    }
                    case 1: {
                        n = (int)(n4 * (1.0f - n5 * n8));
                        n2 = n4;
                        n3 = z[i];
                        break;
                    }
                    case 2: {
                        n = z[i];
                        n2 = n4;
                        n3 = (int)(n4 * (1.0f - n5 * (1.0f - n8)));
                        break;
                    }
                    case 3: {
                        n = z[i];
                        n2 = (int)(n4 * (1.0f - n5 * n8));
                        n3 = n4;
                        break;
                    }
                    case 4: {
                        n = (int)(n4 * (1.0f - n5 * (1.0f - n8)));
                        n2 = z[i];
                        n3 = n4;
                        break;
                    }
                    case 5: {
                        n = n4;
                        n2 = z[i];
                        n3 = (int)(n4 * (1.0f - n5 * n8));
                        break;
                    }
                }
            }
            v[i] = (n << 16 | n2 << 8 | n3);
        }
    }
    
    public void scrollinitial() {
        this.bd = false;
        final String parameter = this.getParameter(c("BV\u001f\u00075UA\b\u001f*"));
        if (parameter != null && !parameter.equalsIgnoreCase(c("x|"))) {
            String s = this.getParameter(c("BV\u001f\u00072OC\u0002"));
            if (s == null) {
                s = c("^\\\u0015\u001a<Y]\u0013\u0012*");
            }
            if (s.equals(c("^\\\u0015\u001a<Y]\u0013\u0012*"))) {
                this.bp = 0;
            }
            else if (s.equals(c("@V\u0015\u0007/UR\u000b"))) {
                this.bp = 1;
            }
            else if (s.equals(c("L\\\b\u001e/XT"))) {
                this.bp = 2;
            }
            else if (s.equals(c("_]\u0011\t)Y^\u000e\u001d!"))) {
                this.bp = 3;
            }
            if (this.bp == 0) {
                this.GetTheString(parameter, 0);
                if (this.bb != null) {
                    this.bd = true;
                }
            }
            else {
                this.GetTheString(parameter, 1);
                if (this.bo != null) {
                    this.bd = true;
                }
            }
        }
        if (this.bd) {
            String parameter2 = this.getParameter(c("BV\u001f\u00075FV\u0002\u0017"));
            if (parameter2 == null) {
                parameter2 = "0";
            }
            this.bc = Integer.valueOf(parameter2);
            String s2 = this.getParameter(c("BV\u001f\u0007 Y]\u0013"));
            if (s2 == null) {
                s2 = c("wA\u000e\u0012*");
            }
            int n = 0;
            if (this.getParameter(c("BV\u001f\u0007$Y_\u0003")).equalsIgnoreCase(c("ov4"))) {
                ++n;
            }
            String s3 = this.getParameter(c("BV\u001f\u0007/BR\u000b\u001a%"));
            if (s3 == null) {
                s3 = c("x|");
            }
            if (s3.equalsIgnoreCase(c("ov4"))) {
                n += 2;
            }
            String s4 = this.getParameter(c("BV\u001f\u00075_I\u0002"));
            if (s4 == null) {
                s4 = c("\u0007\u0001");
            }
            this.bh = new Font(s2, n, Integer.valueOf(s4));
            if (this.getParameter(c("BV\u001f\u00075^R\u0003\u001c1")).equalsIgnoreCase(c("ov4"))) {
                this.be = true;
            }
            else {
                this.be = false;
            }
            this.bf = new Color(Integer.valueOf(this.getParameter(c("bV\u001f\u0007\u0005Y_5"))), Integer.valueOf(this.getParameter(c("bV\u001f\u0007\u0005Y_ "))), Integer.valueOf(this.getParameter(c("bV\u001f\u0007\u0005Y_%"))));
            this.bg = new Color(Integer.valueOf(this.getParameter(c("bV\u001f\u0007\u0015u\\\u000b!"))), Integer.valueOf(this.getParameter(c("bV\u001f\u0007\u0015u\\\u000b4"))), Integer.valueOf(this.getParameter(c("bV\u001f\u0007\u0015u\\\u000b1"))));
            this.U = this.size().width;
            this.V = this.size().height;
            if (this.bp == 0) {
                String parameter3 = this.getParameter(c("BV\u001f\u0007)PU\u0014\u00162"));
                if (parameter3 == null) {
                    parameter3 = "0";
                }
                this.X = Integer.valueOf(parameter3);
                if (this.X < 0) {
                    this.X = 0;
                }
                String parameter4 = this.getParameter(c("bV\u001f\u0007\fC^\u00172+F"));
                if (parameter4 == null) {
                    parameter4 = "0";
                }
                this.bu = Integer.valueOf(parameter4);
                String parameter5 = this.getParameter(c("bV\u001f\u0007\fC^\u0017 6R"));
                if (parameter5 == null) {
                    parameter5 = "0";
                }
                this.bx = Integer.valueOf(parameter5);
                String parameter6 = this.getParameter(c("bV\u001f\u0007\u0015_]\u00022+F"));
                if (parameter6 == null) {
                    parameter6 = "0";
                }
                this.bi = Integer.valueOf(parameter6);
                String parameter7 = this.getParameter(c("bV\u001f\u0007\u0015_]\u0002 6R"));
                if (parameter7 == null) {
                    parameter7 = "0";
                }
                this.bj = Integer.valueOf(parameter7);
                String parameter8 = this.getParameter(c("bV\u001f\u0007\u0015_]\u00022(Q_\u0002"));
                if (parameter8 == null) {
                    parameter8 = "0";
                }
                this.bk = Integer.valueOf(parameter8);
                final FontMetrics fontMetrics = this.getGraphics().getFontMetrics(this.bh);
                this.Y = fontMetrics.stringWidth(this.bb);
                this.Z = fontMetrics.getHeight();
                this.ba = fontMetrics.getMaxDescent();
                this.W = this.U;
                if (this.X < this.Z - this.ba) {
                    this.X = this.Z - this.ba;
                }
                else if (this.X > this.V - this.ba) {
                    this.X = this.V - this.ba;
                }
                if (this.bi != 0) {
                    this.bm = new int[this.U + 360];
                    this.bn = new int[this.U + 360];
                    for (int i = 0; i < this.U + 360; ++i) {
                        this.bm[i] = (int)(this.bi * Math.sin(this.bk * i * 3.141592653589793 / 180.0)) - this.Z - this.ba + this.X;
                        this.bn[i] = this.bm[i] - this.o;
                    }
                    this.bl = 360;
                    this.bF = this.Z + this.ba + 1;
                    this.bG = this.bF - 1;
                }
            }
            else {
                if (this.bp == 1) {
                    String s5 = this.getParameter(c("BV\u001f\u00070EC\u0006\u0010#"));
                    if (s5 == null) {
                        s5 = c("\u0007\u0003");
                    }
                    final int intValue = Integer.valueOf(s5);
                    final FontMetrics fontMetrics2 = this.getGraphics().getFontMetrics(this.bh);
                    this.bz = fontMetrics2.getHeight() + intValue;
                    this.bA = new int[this.bo.length];
                    for (int j = 0; j < this.bo.length; ++j) {
                        this.bA[j] = (this.U - fontMetrics2.stringWidth(this.bo[j])) / 2;
                    }
                    this.by = -this.bz;
                    return;
                }
                if (this.bp >= 2) {
                    String parameter9 = this.getParameter(c("BV\u001f\u0007+_]\u0001\u001c(B"));
                    if (parameter9 == null) {
                        parameter9 = "2";
                    }
                    this.bs = Integer.valueOf(parameter9);
                    String s6 = this.getParameter(c("BV\u001f\u0007+WK\u0001\u001c(B"));
                    if (s6 == null) {
                        s6 = c("\u0001\u0001");
                    }
                    this.bt = Integer.valueOf(s6);
                    this.bq = this.bt - this.bs;
                    this.bh = null;
                    this.br = new Font[this.bq];
                    int bs = this.bs;
                    for (int k = 0; k < this.bq; ++k) {
                        this.br[k] = new Font(s2, n, bs++);
                    }
                    this.bD = this.U / 2.0f;
                    this.bE = this.V / 2.0f;
                    if (this.bp == 3) {
                        this.bB = this.bq - 1;
                        return;
                    }
                    this.bB = 0;
                }
            }
        }
    }
    
    public void scrolltext(final Graphics graphics) {
        switch (this.bp) {
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
        graphics.setFont(this.bh);
        this.by += this.bc;
        if (this.by > this.V + this.bo.length * this.bz) {
            this.by = -this.bz;
        }
        if (this.be) {
            for (int i = 0; i < this.bo.length; ++i) {
                final String s = this.bo[i];
                final int n = this.bA[i];
                final int n2 = this.V - this.by + i * this.bz;
                graphics.setColor(this.bg);
                graphics.drawString(s, n + 1, n2 + 1);
                graphics.setColor(this.bf);
                graphics.drawString(s, n, n2);
            }
            return;
        }
        graphics.setColor(this.bf);
        for (int j = 0; j < this.bo.length; ++j) {
            graphics.drawString(this.bo[j], this.bA[j], this.V - this.by + j * this.bz);
        }
    }
    
    public void zoomscroll(final Graphics graphics) {
        final String s = this.bo[this.bC];
        graphics.setFont(this.br[this.bB]);
        final FontMetrics fontMetrics = graphics.getFontMetrics(this.br[this.bB]);
        final int n = (int)(this.bD - fontMetrics.stringWidth(s) / 2.0f);
        final int n2 = (int)(this.bE + fontMetrics.getHeight() / 4.0f);
        if (this.be) {
            graphics.setColor(this.bg);
            graphics.drawString(s, n + 1, n2 + 1);
        }
        graphics.setColor(this.bf);
        graphics.drawString(s, n, n2);
        if (this.bp == 3) {
            this.bB -= this.bc;
            if (this.bB <= 1) {
                this.bB = this.bq - 1;
                ++this.bC;
                if (this.bC >= this.bo.length) {
                    this.bC = 0;
                }
            }
        }
        else {
            this.bB += this.bc;
            if (this.bB >= this.bq) {
                this.bB = 0;
                ++this.bC;
                if (this.bC >= this.bo.length) {
                    this.bC = 0;
                }
            }
        }
    }
    
    public void horizscroll(final Graphics graphics) {
        graphics.setFont(this.bh);
        if (this.bu == 0) {
            this.bv = this.X;
        }
        else {
            this.bw += this.bx;
            this.bv = this.X - (int)Math.abs(this.bu * Math.sin(this.bw / 90.0 * 3.141592653589793));
        }
        if (this.bi != 0) {
            for (int i = 0; i < this.U; ++i) {
                final int n = this.bm[this.bl + i];
                graphics.copyArea(i, n, 1, this.bF, 0, this.o - n);
            }
            if (this.be) {
                graphics.setColor(this.bg);
                graphics.drawString(this.bb, this.W + 1, this.o + this.Z + 1);
            }
            graphics.setColor(this.bf);
            graphics.drawString(this.bb, this.W, this.o + this.Z);
            for (int j = 0; j < this.U; ++j) {
                graphics.copyArea(j, this.o, 1, this.bG, 0, this.bn[this.bl + j]);
            }
            this.bl -= this.bj;
            if (this.bl < 0) {
                this.bl += 360;
            }
        }
        else {
            if (this.be) {
                graphics.setColor(this.bg);
                graphics.drawString(this.bb, this.W + 1, this.bv + 1);
            }
            graphics.setColor(this.bf);
            graphics.drawString(this.bb, this.W, this.bv);
        }
        this.W -= this.bc;
        if (this.W < -this.Y) {
            this.W = this.U;
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
                            this.bb = new String(byteArray);
                            return;
                        }
                        catch (NoSuchMethodError noSuchMethodError) {
                            this.bb = new String(byteArray, 0);
                            return;
                        }
                    }
                    int n3 = 1;
                    for (int j = 0; j < n2; ++j) {
                        if (byteArray[j] == 10) {
                            ++n3;
                        }
                    }
                    this.bo = new String[n3 - 1];
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
                                this.bo[l] = new String(byteArray, array2[l], array3[l]);
                            }
                            catch (NoSuchMethodError noSuchMethodError2) {
                                this.bo[l] = new String(byteArray, 0, array2[l], array3[l]);
                            }
                        }
                    }
                    catch (StringIndexOutOfBoundsException ex) {
                        this.bo = null;
                    }
                }
                catch (IOException ex2) {}
            }
            catch (IOException ex3) {}
        }
        catch (MalformedURLException ex4) {}
    }
    
    public final boolean mouseEnter(final Event event, final int n, final int n2) {
        this.showStatus(this.bH);
        return true;
    }
    
    public final boolean mouseExit(final Event event, final int n, final int n2) {
        this.showStatus("");
        return true;
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        final int bi = huerot.bI;
        huerot huerot = this;
        Label_0065: {
            if (bi != 0) {
                break Label_0065;
            }
            if (!this.Q) {
                this.h.show();
                try {
                    this.h.move(100, 100);
                }
                catch (Exception ex) {}
                this.h.toFront();
                this.h.requestFocus();
                if (bi == 0) {
                    return true;
                }
            }
            try {
                huerot huerot2 = this;
                huerot = this;
                Label_0084: {
                    if (bi != 0) {
                        break Label_0084;
                    }
                    if (huerot.R == null) {
                        return true;
                    }
                    try {
                        this.h.dck();
                        huerot huerot3 = this;
                        huerot2 = this;
                        if (bi == 0) {
                            if (huerot2.S) {
                                this.getAppletContext().showDocument(this.R, this.getParameter(c("DV\u0000\u00154W^\u0002\u001d'[V")));
                                if (bi == 0) {
                                    return true;
                                }
                            }
                            huerot3 = this;
                        }
                        huerot3.getAppletContext().showDocument(this.R);
                    }
                    catch (Exception ex2) {}
                }
            }
            catch (Exception ex3) {}
        }
        return true;
    }
    
    public huerot() {
        this.c = false;
        this.p = 1;
        this.N = false;
        this.O = false;
        this.Q = false;
        this.S = false;
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
                    c2 = '3';
                    break;
                }
                case 2: {
                    c2 = 'g';
                    break;
                }
                case 3: {
                    c2 = 's';
                    break;
                }
                default: {
                    c2 = 'F';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
