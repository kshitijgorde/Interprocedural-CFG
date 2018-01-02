import java.awt.FontMetrics;
import java.awt.Event;
import java.awt.image.ImageProducer;
import java.awt.image.ColorModel;
import java.awt.image.DirectColorModel;
import java.awt.Container;
import java.awt.Label;
import java.awt.image.PixelGrabber;
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

public class huerot extends Applet implements Runnable, ImageObserver
{
    private int a;
    Frame b;
    boolean c;
    final String d = "oU]\u000b\u0012Z\u0005O\u001eWhDO\u000e\u0018\u000efD\u0012\u0014ML\rO\u0000YR\u0003\u0006\u0019H";
    int e;
    long f;
    int g;
    int[] h;
    Font i;
    int j;
    private Graphics k;
    int l;
    float m;
    float n;
    private Image o;
    int p;
    boolean q;
    String[] r;
    URL s;
    int t;
    int u;
    String v;
    String w;
    String x;
    String y;
    String z;
    String A;
    int B;
    boolean C;
    int D;
    private Graphics E;
    private Image F;
    boolean G;
    private Image H;
    int I;
    int J;
    int[] K;
    int L;
    anfy M;
    MemoryImageSource N;
    int O;
    int P;
    boolean Q;
    int R;
    String S;
    int T;
    int U;
    int V;
    Color W;
    int X;
    int Y;
    int Z;
    int ba;
    int bb;
    int bc;
    int[] bd;
    int[] be;
    int[] bf;
    float[] bg;
    float[] bh;
    float bi;
    float bj;
    String bk;
    Color bl;
    int bm;
    int bn;
    boolean bo;
    boolean bp;
    Toolkit bq;
    Thread br;
    int bs;
    int bt;
    int bu;
    int bv;
    int bw;
    int[] bx;
    int by;
    Lware bz;
    int bA;
    int bB;
    int bC;
    int bD;
    int bE;
    int bF;
    int bG;
    Font[] bH;
    
    private final void a() {
        while (true) {
            this.showStatus(c("jJC@\u0003\u000eWH\n\u0018X@\r\u0010\u0000Y\u000bL\t\u0011WOL\u0011\u0016\u0000FB\nWMWH\u0003\u001eZV\r\u000b\u001e@@\r\u000e\u0019\u000emy*;\u000f"));
            try {
                Thread.sleep(500L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public synchronized boolean b() {
        this.prepareImage(this.H, this);
        if (this.q) {
            for (int i = 0; i < 3; ++i) {
                this.notifyAll();
                Thread.yield();
                try {
                    Thread.sleep(100L);
                }
                catch (InterruptedException ex) {}
            }
            return this.G;
        }
        return false;
    }
    
    public void destroy() {
        if (this.H != null) {
            this.H.flush();
        }
        this.H = null;
        if (this.F != null) {
            this.F.flush();
        }
        this.F = null;
        if (this.E != null) {
            this.E.dispose();
        }
        this.E = null;
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
                this.showStatus(c("gHL\u0000\u0012\u000e") + s + c("\u000eKB\u0013WHJX\t\u0013\u000f"));
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
                            this.S = new String(byteArray);
                            return;
                        }
                        catch (NoSuchMethodError noSuchMethodError) {
                            this.S = new String(byteArray, 0);
                            return;
                        }
                    }
                    int n3 = 1;
                    for (int j = 0; j < n2; ++j) {
                        if (byteArray[j] == 10) {
                            ++n3;
                        }
                    }
                    this.r = new String[n3 - 1];
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
                                this.r[l] = new String(byteArray, array2[l], array3[l]);
                            }
                            catch (NoSuchMethodError noSuchMethodError2) {
                                this.r[l] = new String(byteArray, 0, array2[l], array3[l]);
                            }
                        }
                    }
                    catch (StringIndexOutOfBoundsException ex) {
                        this.r = null;
                    }
                }
                catch (IOException ex2) {}
            }
            catch (IOException ex3) {}
        }
        catch (MalformedURLException ex4) {}
    }
    
    public void a(final Graphics graphics) {
        graphics.setFont(this.i);
        if (this.a == 0) {
            this.bD = this.bE;
        }
        else {
            this.p += this.bt;
            this.bD = this.bE - (int)Math.abs(this.a * Math.sin(this.p / 90.0 * 3.141592653589793));
        }
        if (this.Y != 0) {
            for (int i = 0; i < this.bv; ++i) {
                final int n = this.bd[this.X + i];
                graphics.copyArea(i, n, 1, this.ba, 0, this.O - n);
            }
            if (this.bp) {
                graphics.setColor(this.W);
                graphics.drawString(this.S, this.bB + 1, this.O + this.T + 1);
            }
            graphics.setColor(this.bl);
            graphics.drawString(this.S, this.bB, this.O + this.T);
            for (int j = 0; j < this.bv; ++j) {
                graphics.copyArea(j, this.O, 1, this.bb, 0, this.be[this.X + j]);
            }
            this.X -= this.bc;
            if (this.X < 0) {
                this.X += 360;
            }
        }
        else {
            if (this.bp) {
                graphics.setColor(this.W);
                graphics.drawString(this.S, this.bB + 1, this.bD + 1);
            }
            graphics.setColor(this.bl);
            graphics.drawString(this.S, this.bB, this.bD);
        }
        this.bB -= this.bu;
        if (this.bB < -this.U) {
            this.bB = this.bv;
        }
    }
    
    public huerot() {
        this.q = false;
        this.R = 1;
        this.G = false;
        this.c = false;
        this.Q = false;
        this.C = false;
    }
    
    void c() {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        this.bj += this.bi;
        final float bj = this.bj;
        final int bs = this.bs;
        final int[] bf = this.bf;
        final float[] bh = this.bh;
        final int[] k = this.K;
        final int[] h = this.h;
        for (int i = 0; i < bs; ++i) {
            final int n4 = bf[i];
            final float n5 = bh[i];
            if (n5 == 0.0f) {
                n2 = (n = (n3 = n4));
            }
            else {
                final float n6 = this.bg[i] + bj;
                final double n7 = (n6 - (int)n6) * 6.0;
                final double n8 = n7 - (int)n7;
                switch ((int)n7) {
                    case 0: {
                        n = n4;
                        n2 = (int)(n4 * (1.0 - n5 * (1.0 - n8)));
                        n3 = k[i];
                        break;
                    }
                    case 1: {
                        n = (int)(n4 * (1.0 - n5 * n8));
                        n2 = n4;
                        n3 = k[i];
                        break;
                    }
                    case 2: {
                        n = k[i];
                        n2 = n4;
                        n3 = (int)(n4 * (1.0 - n5 * (1.0 - n8)));
                        break;
                    }
                    case 3: {
                        n = k[i];
                        n2 = (int)(n4 * (1.0 - n5 * n8));
                        n3 = n4;
                        break;
                    }
                    case 4: {
                        n = (int)(n4 * (1.0 - n5 * (1.0 - n8)));
                        n2 = k[i];
                        n3 = n4;
                        break;
                    }
                    case 5: {
                        n = n4;
                        n2 = k[i];
                        n3 = (int)(n4 * (1.0 - n5 * n8));
                        break;
                    }
                }
            }
            h[i] = (n << 16 | n2 << 8 | n3);
        }
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if (image == this.H) {
            if (n == 16) {
                this.G = true;
            }
            return true;
        }
        return true;
    }
    
    public void init() {
        this.setLayout(null);
        this.addNotify();
        this.bq = this.getToolkit();
        this.bk = this.getParameter(c("]QL\u0013\u0002]H^\u0000"));
        final String parameter = this.getParameter(c("MWH\u0003\u001eZV"));
        if (parameter != null) {
            if (!parameter.startsWith(c("oU]\u000b\u0012Z\u0005O\u001eWhDO\u000e\u0018\u000efD\u0012\u0014ML\rO\u0000YR\u0003\u0006\u0019H"))) {
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
            s = c("HLA\u0002");
        }
        String s2;
        try {
            s2 = this.getDocumentBase().getHost();
        }
        catch (SecurityException ex2) {
            s2 = "";
        }
        if (s.equalsIgnoreCase(c("HLA\u0002")) || s2.length() == 0 || s2.equalsIgnoreCase(c("BJN\u0006\u001bFJ^\u0013")) || s2.equals(c("\u001f\u0017\u001aIG\u0000\u0015\u0003V"))) {
            this.Q = true;
        }
        else {
            if (s2.startsWith(c("YRZI"))) {
                s2 = s2.substring(4);
            }
            final String parameter2 = this.getParameter(c("\\@J\u0004\u0018J@"));
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
                        if (s5.startsWith(c("YRZI"))) {
                            substring = s5.substring(4);
                        }
                        else {
                            substring = s5;
                        }
                        if (s2.equalsIgnoreCase(substring)) {
                            this.Q = true;
                        }
                    }
                }
            }
        }
        final String parameter3 = this.getParameter(c("\\@J\u000b\u001e@N"));
        if (parameter3 != null && !parameter3.equalsIgnoreCase("NO")) {
            try {
                this.s = new URL(this.getDocumentBase(), parameter3);
            }
            catch (MalformedURLException ex6) {
                this.s = null;
            }
        }
        if (this.getParameter(c("\\@J\t\u0012YC_\u0006\u001aK")).equalsIgnoreCase(c("w`~"))) {
            this.C = true;
        }
        Container container;
        for (container = this.getParent(); !(container instanceof Frame); container = ((Frame)container).getParent()) {}
        (this.b = (Frame)container).setCursor(3);
        final String parameter4 = this.getParameter(c("ASH\u0015\u001eCB"));
        if (parameter4 != null && !parameter4.equalsIgnoreCase("NO")) {
            this.H = this.a(parameter4);
            if (this.H != null) {
                String parameter5 = this.getParameter(c("ASH\u0015\u001eCBu"));
                if (parameter5 == null) {
                    parameter5 = "0";
                }
                this.I = Integer.valueOf(parameter5);
                String parameter6 = this.getParameter(c("ASH\u0015\u001eCBt"));
                if (parameter6 == null) {
                    parameter6 = "0";
                }
                this.J = Integer.valueOf(parameter6);
            }
        }
        this.A = this.getParameter(c("cLC4.`f"));
        if (this.A == null) {
            this.A = "10";
        }
        this.u = Integer.valueOf(this.A);
        this.v = this.getParameter(c("GHL\u0000\u0012"));
        this.w = this.getParameter(c("\\@^"));
        if (this.w == null) {
            this.w = "1";
        }
        this.z = this.getParameter(c("FPH\u0014\u001fGCY"));
        if (this.z == null) {
            this.z = "10";
        }
        this.bi = Float.valueOf(this.z) / 10000.0f;
        this.x = this.getParameter(c("C@@\u0003\u0012BDT"));
        this.y = this.getParameter(c("^WD\b\u0005GQT"));
        this.t = Integer.valueOf(this.x);
        this.L = Integer.valueOf(this.y);
        if (this.t < 0) {
            this.t = 0;
        }
        if (this.L > 10) {
            this.L = 10;
        }
        else if (this.L < 1) {
            this.L = 1;
        }
        this.R = Integer.valueOf(this.w);
        if (this.R > 8) {
            this.R = 8;
        }
        else if (this.R < 1) {
            this.R = 1;
        }
        this.by = this.size().width / this.R;
        this.l = this.size().height / this.R;
        this.P = this.by * this.R;
        this.O = this.l * this.R;
        this.showStatus(c("bJL\u0003\u001e@B\r\u000e\u001aOBHIY\u0000"));
        this.o = this.a(this.v);
        this.bs = this.by * this.l;
        final int[] array8 = new int[this.bs];
        this.h = new int[this.bs];
        final PixelGrabber pixelGrabber = new PixelGrabber(this.o, 0, 0, this.by, this.l, array8, 0, this.by);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex7) {}
        this.bg = new float[this.bs];
        this.bh = new float[this.bs];
        this.bf = new int[this.bs];
        this.K = new int[this.bs];
        float[] rgBtoHSB = new float[3];
        for (int n14 = 0; n14 < this.bs; ++n14) {
            final int n15 = array8[n14];
            rgBtoHSB = Color.RGBtoHSB((n15 & 0xFF0000) >> 16, (n15 & 0xFF00) >> 8, n15 & 0xFF, rgBtoHSB);
            this.bg[n14] = rgBtoHSB[0];
            this.bh[n14] = rgBtoHSB[1];
            this.bf[n14] = (int)(rgBtoHSB[2] * 255.0f);
            this.K[n14] = (int)(this.bf[n14] * (1.0 - this.bh[n14]));
        }
        try {
            this.d();
        }
        catch (NoSuchMethodError noSuchMethodError) {
            this.d();
        }
        this.h();
        this.F = this.createImage(this.P, this.O + this.ba);
        this.E = this.F.getGraphics();
        if (!this.Q) {
            (this.bz = new Lware(this.getAppletContext(), new Label(c("fPHG%AQL\u0013\u0018\\\u0005L\u0017\u0007B@YG\u0015W\u0005k\u0006\u0015GJ\r$\u001e[FN\u000eW\u001f\u001c\u0014QX\u0017\u001d\u0003")))).setTitle(c("fPHG%AQL\u0013\u0018\\\u0005l\u0017\u0007B@YG\u0015W\u0005k\u0006\u0015GJ\r$\u001e[FN\u000e"));
            this.bz.hide();
        }
    }
    
    void d() {
        this.N = new MemoryImageSource(this.by, this.l, new DirectColorModel(24, 16711680, 65280, 255), this.h, 0, this.by);
        String s;
        try {
            s = System.getProperty(c("DD[\u0006YX@_\u0014\u001eAK"));
        }
        catch (SecurityException ex) {
            s = c("[KF");
        }
        if (!s.startsWith(c("\u001f\u000b\u001d"))) {
            try {
                this.N.setAnimated(true);
                this.N.setFullBufferUpdates(true);
                this.o = this.createImage(this.N);
                this.N.newPixels();
                this.q = true;
            }
            catch (NoSuchMethodError noSuchMethodError) {
                this.q = false;
            }
        }
        if (!this.q) {
            this.N = null;
            this.M = new anfy(this.by, this.l, new DirectColorModel(24, 16711680, 65280, 255), this.h, 0, this.by);
            this.o = this.createImage(this.M);
        }
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        if (!this.Q) {
            this.bz.show();
            this.bz.toFront();
            this.bz.requestFocus();
        }
        else if (this.s != null) {
            if (this.C) {
                this.getAppletContext().showDocument(this.s, this.getParameter(c("\\@J\u0001\u0005OHH\t\u0016C@")));
            }
            else {
                this.getAppletContext().showDocument(this.s);
            }
        }
        return true;
    }
    
    public final boolean mouseEnter(final Event event, final int n, final int n2) {
        this.showStatus(this.bk);
        return true;
    }
    
    public final boolean mouseExit(final Event event, final int n, final int n2) {
        this.showStatus("");
        return true;
    }
    
    public final void paint(final Graphics graphics) {
        if (this.o != null) {
            if (this.R == 1) {
                this.E.drawImage(this.o, 0, 0, this);
            }
            else {
                this.f();
                this.E.drawImage(this.o, 0, 0, this.P, this.O, this);
            }
            if (this.H != null) {
                this.e();
            }
            if (this.bo) {
                this.b(this.E);
            }
            graphics.drawImage(this.F, 0, 0, this);
        }
    }
    
    public synchronized void e() {
        if (this.c) {
            this.notifyAll();
            while (!this.G) {
                Thread.yield();
                try {
                    Thread.sleep(8L);
                }
                catch (InterruptedException ex) {}
            }
            this.G = false;
        }
        this.E.drawImage(this.H, this.I, this.J, this);
    }
    
    public synchronized void f() {
        int checkImage = 0;
        this.prepareImage(this.o, this.P, this.O, this);
        this.notifyAll();
        while ((checkImage & 0xF0) == 0x0) {
            Thread.yield();
            checkImage = this.checkImage(this.o, this.P, this.O, this);
        }
    }
    
    public final void g() {
        try {
            if (this.q) {
                this.N.newPixels();
                return;
            }
            this.M.startProduction(this.M.getConsumer());
        }
        catch (NoSuchMethodError noSuchMethodError) {}
    }
    
    public void run() {
        this.br.setPriority(this.L);
        this.showStatus("");
        System.gc();
        this.f = System.currentTimeMillis();
        final Graphics graphics = this.getGraphics();
        if (this.H != null && !this.c) {
            this.c = this.b();
        }
        if (this.s != null) {
            this.b.setCursor(12);
        }
        else {
            this.b.setCursor(0);
        }
        while (this.br != null) {
            this.c();
            if (++this.e == this.t) {
                System.gc();
                this.e = 0;
            }
            try {
                this.g();
            }
            catch (NoSuchMethodError noSuchMethodError) {}
            if (this.R == 1) {
                this.E.drawImage(this.o, 0, 0, this);
            }
            else {
                this.f();
                this.E.drawImage(this.o, 0, 0, this.P, this.O, this);
            }
            if (this.H != null) {
                this.e();
            }
            if (this.bo) {
                this.b(this.E);
            }
            graphics.drawImage(this.F, 0, 0, this);
            this.i();
        }
    }
    
    byte a(final int n, final int n2, final int n3, final int n4) {
        final int n5 = n - n2;
        if (n5 >= n3) {
            return (byte)n5;
        }
        return (byte)(n4 - (n3 - n5 - 1));
    }
    
    public void h() {
        this.bo = false;
        final String parameter = this.getParameter(c("Z@U\u0013\u0004MWB\u000b\u001b"));
        if (parameter != null && !parameter.equalsIgnoreCase("NO")) {
            String s = this.getParameter(c("Z@U\u0013\u0003WUH"));
            if (s == null) {
                s = c("FJ_\u000e\rAKY\u0006\u001b");
            }
            if (s.equals(c("FJ_\u000e\rAKY\u0006\u001b"))) {
                this.V = 0;
            }
            else if (s.equals(c("X@_\u0013\u001eMDA"))) {
                this.V = 1;
            }
            else if (s.equals(c("TJB\n\u001e@B"))) {
                this.V = 2;
            }
            else if (s.equals(c("GK[\u001d\u0018AHD\t\u0010"))) {
                this.V = 3;
            }
            if (this.V == 0) {
                this.a(parameter, 0);
                if (this.S != null) {
                    this.bo = true;
                }
            }
            else {
                this.a(parameter, 1);
                if (this.r != null) {
                    this.bo = true;
                }
            }
        }
        if (this.bo) {
            String parameter2 = this.getParameter(c("Z@U\u0013\u0004^@H\u0003"));
            if (parameter2 == null) {
                parameter2 = "0";
            }
            this.bu = Integer.valueOf(parameter2);
            String s2 = this.getParameter(c("Z@U\u0013\u0011AKY"));
            if (s2 == null) {
                s2 = c("oWD\u0006\u001b");
            }
            int n = 0;
            if (this.getParameter(c("Z@U\u0013\u0015AII")).equalsIgnoreCase(c("w`~"))) {
                ++n;
            }
            String parameter3 = this.getParameter(c("Z@U\u0013\u001eZDA\u000e\u0014"));
            if (parameter3 == null) {
                parameter3 = "NO";
            }
            if (parameter3.equalsIgnoreCase(c("w`~"))) {
                n += 2;
            }
            String parameter4 = this.getParameter(c("Z@U\u0013\u0004G_H"));
            if (parameter4 == null) {
                parameter4 = "12";
            }
            this.i = new Font(s2, n, Integer.valueOf(parameter4));
            if (this.getParameter(c("Z@U\u0013\u0004FDI\b\u0000")).equalsIgnoreCase(c("w`~"))) {
                this.bp = true;
            }
            else {
                this.bp = false;
            }
            this.bl = new Color(Integer.valueOf(this.getParameter(c("z@U\u00134AI\u007f"))), Integer.valueOf(this.getParameter(c("z@U\u00134AIj"))), Integer.valueOf(this.getParameter(c("z@U\u00134AIo"))));
            this.W = new Color(Integer.valueOf(this.getParameter(c("z@U\u0013$mJA5"))), Integer.valueOf(this.getParameter(c("z@U\u0013$mJA "))), Integer.valueOf(this.getParameter(c("z@U\u0013$mJA%"))));
            this.bv = this.size().width;
            this.bw = this.size().height;
            if (this.V == 0) {
                String parameter5 = this.getParameter(c("Z@U\u0013\u0018HC^\u0002\u0003"));
                if (parameter5 == null) {
                    parameter5 = "0";
                }
                this.bE = Integer.valueOf(parameter5);
                if (this.bE < 0) {
                    this.bE = 0;
                }
                String parameter6 = this.getParameter(c("z@U\u0013=[H]&\u001a^"));
                if (parameter6 == null) {
                    parameter6 = "0";
                }
                this.a = Integer.valueOf(parameter6);
                String parameter7 = this.getParameter(c("z@U\u0013=[H]4\u0007J"));
                if (parameter7 == null) {
                    parameter7 = "0";
                }
                this.bt = Integer.valueOf(parameter7);
                String parameter8 = this.getParameter(c("z@U\u0013$GKH&\u001a^"));
                if (parameter8 == null) {
                    parameter8 = "0";
                }
                this.Y = Integer.valueOf(parameter8);
                String parameter9 = this.getParameter(c("z@U\u0013$GKH4\u0007J"));
                if (parameter9 == null) {
                    parameter9 = "0";
                }
                this.bc = Integer.valueOf(parameter9);
                String parameter10 = this.getParameter(c("z@U\u0013$GKH&\u0019IIH"));
                if (parameter10 == null) {
                    parameter10 = "0";
                }
                this.Z = Integer.valueOf(parameter10);
                final FontMetrics fontMetrics = this.getGraphics().getFontMetrics(this.i);
                this.U = fontMetrics.stringWidth(this.S);
                this.T = fontMetrics.getHeight();
                this.g = fontMetrics.getMaxDescent();
                this.bB = this.bv;
                if (this.bE < this.T - this.g) {
                    this.bE = this.T - this.g;
                }
                else if (this.bE > this.bw - this.g) {
                    this.bE = this.bw - this.g;
                }
                if (this.Y != 0) {
                    this.bd = new int[this.bv + 360];
                    this.be = new int[this.bv + 360];
                    for (int i = 0; i < this.bv + 360; ++i) {
                        this.bd[i] = (int)(this.Y * Math.sin(this.Z * i * 3.141592653589793 / 180.0)) - this.T - this.g + this.bE;
                        this.be[i] = this.bd[i] - this.O;
                    }
                    this.X = 360;
                    this.ba = this.T + this.g + 1;
                    this.bb = this.ba - 1;
                }
            }
            else {
                if (this.V == 1) {
                    String parameter11 = this.getParameter(c("Z@U\u0013\u0001]UL\u0004\u0012"));
                    if (parameter11 == null) {
                        parameter11 = "10";
                    }
                    final int intValue = Integer.valueOf(parameter11);
                    final FontMetrics fontMetrics2 = this.getGraphics().getFontMetrics(this.i);
                    this.j = fontMetrics2.getHeight() + intValue;
                    this.bx = new int[this.r.length];
                    for (int j = 0; j < this.r.length; ++j) {
                        this.bx[j] = (this.bv - fontMetrics2.stringWidth(this.r[j])) / 2;
                    }
                    this.B = -this.j;
                    return;
                }
                if (this.V >= 2) {
                    String parameter12 = this.getParameter(c("Z@U\u0013\u001aGKK\b\u0019Z"));
                    if (parameter12 == null) {
                        parameter12 = "2";
                    }
                    this.bn = Integer.valueOf(parameter12);
                    String parameter13 = this.getParameter(c("Z@U\u0013\u001aO]K\b\u0019Z"));
                    if (parameter13 == null) {
                        parameter13 = "72";
                    }
                    this.bm = Integer.valueOf(parameter13);
                    this.D = this.bm - this.bn;
                    this.i = null;
                    this.bH = new Font[this.D];
                    int bn = this.bn;
                    for (int k = 0; k < this.D; ++k) {
                        this.bH[k] = new Font(s2, n, bn++);
                    }
                    this.n = this.bv / 2.0f;
                    this.m = this.bw / 2.0f;
                    if (this.V == 3) {
                        this.bG = this.D - 1;
                        return;
                    }
                    this.bG = 0;
                }
            }
        }
    }
    
    public void b(final Graphics graphics) {
        switch (this.V) {
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
        if (this.br == null) {
            (this.br = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.br != null && this.br.isAlive()) {
            this.br.stop();
        }
        this.br = null;
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void c(final Graphics graphics) {
        graphics.setFont(this.i);
        this.B += this.bu;
        if (this.B > this.bw + this.r.length * this.j) {
            this.B = -this.j;
        }
        if (this.bp) {
            for (int i = 0; i < this.r.length; ++i) {
                final String s = this.r[i];
                final int n = this.bx[i];
                final int n2 = this.bw - this.B + i * this.j;
                graphics.setColor(this.W);
                graphics.drawString(s, n + 1, n2 + 1);
                graphics.setColor(this.bl);
                graphics.drawString(s, n, n2);
            }
            return;
        }
        graphics.setColor(this.bl);
        for (int j = 0; j < this.r.length; ++j) {
            graphics.drawString(this.r[j], this.bx[j], this.bw - this.B + j * this.j);
        }
    }
    
    public synchronized void i() {
        Thread.yield();
        this.bq.sync();
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
            Thread.sleep(this.u);
        }
        catch (InterruptedException ex3) {}
    }
    
    public void d(final Graphics graphics) {
        final String s = this.r[this.bF];
        graphics.setFont(this.bH[this.bG]);
        final FontMetrics fontMetrics = graphics.getFontMetrics(this.bH[this.bG]);
        final int n = (int)(this.n - fontMetrics.stringWidth(s) / 2.0f);
        final int n2 = (int)(this.m + fontMetrics.getHeight() / 4.0f);
        if (this.bp) {
            graphics.setColor(this.W);
            graphics.drawString(s, n + 1, n2 + 1);
        }
        graphics.setColor(this.bl);
        graphics.drawString(s, n, n2);
        if (this.V == 3) {
            this.bG -= this.bu;
            if (this.bG <= 1) {
                this.bG = this.D - 1;
                ++this.bF;
                if (this.bF >= this.r.length) {
                    this.bF = 0;
                }
            }
        }
        else {
            this.bG += this.bu;
            if (this.bG >= this.D) {
                this.bG = 0;
                ++this.bF;
                if (this.bF >= this.r.length) {
                    this.bF = 0;
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
                char c = '.';
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
                                c = '%';
                                array = (array2 = charArray);
                                n3 = (n4 = n);
                                continue;
                            }
                            c = '-';
                            array = (array2 = charArray);
                            n3 = (n4 = n);
                            continue;
                        }
                        c = 'g';
                        array = (array2 = charArray);
                        n3 = (n4 = n);
                        continue;
                    }
                    c = 'w';
                    array = (array2 = charArray);
                    n3 = (n4 = n);
                }
            }
            break;
        }
        return new String(charArray);
    }
}
