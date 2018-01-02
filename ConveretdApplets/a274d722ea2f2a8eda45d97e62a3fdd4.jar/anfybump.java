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
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.image.ImageObserver;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class anfybump extends Applet implements Runnable, ImageObserver
{
    double a;
    double b;
    private int c;
    Frame d;
    double e;
    double f;
    double g;
    double h;
    double i;
    double j;
    boolean k;
    int l;
    int m;
    int n;
    final String o = ".*[\u001b\u0000\u001bzI\u000eE);I\u001e\nO\u0019B\u0002\u0006\f3\u000b_\u0012\u0018-\u0005\u0016\u000b\t";
    int[] p;
    int q;
    int r;
    int s;
    int t;
    int u;
    int[] v;
    int[] w;
    int[] x;
    int[] y;
    double z;
    Dimension A;
    long B;
    long C;
    int D;
    int[] E;
    Dimension F;
    int G;
    Font H;
    int I;
    private Graphics J;
    int[] K;
    int L;
    int M;
    float N;
    float O;
    int P;
    private Image Q;
    private Image R;
    boolean S;
    int T;
    int U;
    boolean V;
    int W;
    int X;
    int Y;
    double Z;
    int ba;
    int[] bb;
    String[] bc;
    URL bd;
    int be;
    int bf;
    String bg;
    String bh;
    String bi;
    String bj;
    String bk;
    String bl;
    String bm;
    String bn;
    String bo;
    String bp;
    String bq;
    String br;
    String bs;
    String bt;
    String bu;
    String bv;
    String bw;
    String bx;
    String by;
    String bz;
    String bA;
    String bB;
    String bC;
    String bD;
    int bE;
    boolean bF;
    boolean bG;
    int bH;
    private Graphics bI;
    private Image bJ;
    boolean bK;
    int bL;
    int bM;
    int bN;
    int bO;
    private Image bP;
    int bQ;
    int bR;
    int bS;
    anfy bT;
    MemoryImageSource bU;
    int bV;
    int bW;
    int[] bX;
    boolean bY;
    int bZ;
    String ca;
    int cb;
    int cc;
    int cd;
    Color ce;
    int cf;
    int cg;
    int ch;
    int ci;
    int cj;
    int ck;
    int[] cl;
    int[] cm;
    int[] cn;
    String co;
    Color cp;
    int cq;
    int cr;
    boolean cs;
    boolean ct;
    Toolkit cu;
    Thread cv;
    int cw;
    int cx;
    int cy;
    int cz;
    int[] cA;
    int cB;
    Lware cC;
    int cD;
    int cE;
    int cF;
    int cG;
    int cH;
    Font[] cI;
    
    public anfybump() {
        this.w = new int[768];
        this.x = new int[768];
        this.y = new int[768];
        this.u = 1;
        this.ba = 256;
        this.M = 128;
        this.W = 65535;
        this.V = false;
        this.bZ = 1;
        this.bK = false;
        this.k = false;
        this.bY = false;
        this.bF = false;
    }
    
    public void a(final int[] array, final int[] array2) {
        for (int i = 0; i < this.F.height; ++i) {
            for (int j = 0; j < this.F.width; ++j) {
                int n = 1;
                final int n2 = i * this.F.width + j;
                int n3 = array[n2];
                if (j != 0) {
                    n3 += array[n2 - 1];
                    ++n;
                    if (i != 0) {
                        n3 += array[n2 - this.F.width - 1];
                        ++n;
                    }
                    if (i != this.F.height - 1) {
                        n3 += array[n2 + this.F.width - 1];
                        ++n;
                    }
                }
                if (j != this.F.width - 1) {
                    n3 += array[n2 + 1];
                    ++n;
                    if (i != 0) {
                        n3 += array[n2 - this.F.width + 1];
                        ++n;
                    }
                    if (i != this.F.height - 1) {
                        n3 += array[n2 + this.F.width + 1];
                        ++n;
                    }
                }
                if (i != 0) {
                    n3 += array[n2 - this.F.width];
                    ++n;
                }
                if (i != this.F.height - 1) {
                    n3 += array[n2 + this.F.width];
                    ++n;
                }
                array2[n2] = n3 / n;
            }
        }
    }
    
    private final void a() {
        while (true) {
            this.showStatus(c("+5EP\u0011O(N\u001a\n\u0019?\u000b\u0000\u0012\u0018tJ\u0019\u0003\u00160J\u0001\u0004A9D\u001aE\f(N\u0013\f\u001b)\u000b\u001b\f\u0001?\u000b\u001e\u000bO\u0012\u007f:)N"));
            try {
                Thread.sleep(500L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public synchronized boolean b() {
        this.prepareImage(this.bP, this);
        if (this.V) {
            for (int i = 0; i < 3; ++i) {
                this.notifyAll();
                Thread.yield();
                try {
                    Thread.sleep(100L);
                }
                catch (InterruptedException ex) {}
            }
            return this.bK;
        }
        return false;
    }
    
    public void destroy() {
        if (this.bP != null) {
            this.bP.flush();
        }
        this.bP = null;
        if (this.bJ != null) {
            this.bJ.flush();
        }
        this.bJ = null;
        if (this.bI != null) {
            this.bI.dispose();
        }
        this.bI = null;
        System.gc();
    }
    
    synchronized void c() {
        int n = 0;
        final int l = this.l;
        final int n2 = this.n;
        final int ba = this.ba;
        final int q = this.q;
        final int w = this.W;
        final int[] bb = this.bb;
        final int[] v = this.v;
        final int[] e = this.E;
        final int[] w2 = this.w;
        final int[] x = this.x;
        final int[] y = this.y;
        final int[] bx = this.bX;
        final int[] k = this.K;
        final int[] p = this.p;
        for (int i = 0; i < l; ++i) {
            final int n3 = i * ba + q;
            for (int j = 0; j < n2; ++j) {
                final int n4 = bb[v[n] + n3 + j & w];
                e[n] = (w2[bx[n] + n4] | x[k[n] + n4] | y[p[n] + n4]);
                ++n;
            }
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
                this.showStatus(c("&7J\u0010\u0000O") + s + c("O4D\u0003E\t5^\u0019\u0001N"));
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
                            this.ca = new String(byteArray);
                            return;
                        }
                        catch (NoSuchMethodError noSuchMethodError) {
                            this.ca = new String(byteArray, 0);
                            return;
                        }
                    }
                    int n3 = 1;
                    for (int j = 0; j < n2; ++j) {
                        if (byteArray[j] == 10) {
                            ++n3;
                        }
                    }
                    this.bc = new String[n3 - 1];
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
                                this.bc[l] = new String(byteArray, array2[l], array3[l]);
                            }
                            catch (NoSuchMethodError noSuchMethodError2) {
                                this.bc[l] = new String(byteArray, 0, array2[l], array3[l]);
                            }
                        }
                    }
                    catch (StringIndexOutOfBoundsException ex) {
                        this.bc = null;
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
            this.cE = this.cF;
        }
        else {
            this.U += this.cw;
            this.cE = this.cF - (int)Math.abs(this.c * Math.sin(this.U / 90.0 * 3.141592653589793));
        }
        if (this.cg != 0) {
            for (int i = 0; i < this.cy; ++i) {
                final int n = this.cl[this.cf + i];
                graphics.copyArea(i, n, 1, this.ci, 0, this.bV - n);
            }
            if (this.ct) {
                graphics.setColor(this.ce);
                graphics.drawString(this.ca, this.cD + 1, this.bV + this.cb + 1);
            }
            graphics.setColor(this.cp);
            graphics.drawString(this.ca, this.cD, this.bV + this.cb);
            for (int j = 0; j < this.cy; ++j) {
                graphics.copyArea(j, this.bV, 1, this.cj, 0, this.cm[this.cf + j]);
            }
            this.cf -= this.ck;
            if (this.cf < 0) {
                this.cf += 360;
            }
        }
        else {
            if (this.ct) {
                graphics.setColor(this.ce);
                graphics.drawString(this.ca, this.cD + 1, this.cE + 1);
            }
            graphics.setColor(this.cp);
            graphics.drawString(this.ca, this.cD, this.cE);
        }
        this.cD -= this.cx;
        if (this.cD < -this.cc) {
            this.cD = this.cy;
        }
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if (image == this.bP) {
            if (n == 16) {
                this.bK = true;
            }
            return true;
        }
        return true;
    }
    
    public void init() {
        this.setLayout(null);
        this.addNotify();
        this.cu = this.getToolkit();
        this.co = this.getParameter(c("\u001c.J\u0003\u0010\u001c7X\u0010"));
        final String parameter = this.getParameter(c("\f(N\u0013\f\u001b)"));
        if (parameter != null) {
            if (!parameter.startsWith(c(".*[\u001b\u0000\u001bzI\u000eE);I\u001e\nO\u0019B\u0002\u0006\f3\u000b_\u0012\u0018-\u0005\u0016\u000b\t"))) {
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
            s = c("\t3G\u0012");
        }
        String s2;
        try {
            s2 = this.getDocumentBase().getHost();
        }
        catch (SecurityException ex2) {
            s2 = "";
        }
        if (s.equalsIgnoreCase(c("\t3G\u0012")) || s2.length() == 0 || s2.equalsIgnoreCase(c("\u00035H\u0016\t\u00075X\u0003")) || s2.equals(c("^h\u001cYUAj\u0005F"))) {
            this.bY = true;
        }
        else {
            if (s2.startsWith(c("\u0018-\\Y"))) {
                s2 = s2.substring(4);
            }
            final String parameter2 = this.getParameter(c("\u001d?L\u0014\n\u000b?"));
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
                        if (s5.startsWith(c("\u0018-\\Y"))) {
                            substring = s5.substring(4);
                        }
                        else {
                            substring = s5;
                        }
                        if (s2.equalsIgnoreCase(substring)) {
                            this.bY = true;
                        }
                    }
                }
            }
        }
        final String parameter3 = this.getParameter(c("\u001d?L\u001b\f\u00011"));
        if (parameter3 != null && !parameter3.equalsIgnoreCase("NO")) {
            try {
                this.bd = new URL(this.getDocumentBase(), parameter3);
            }
            catch (MalformedURLException ex6) {
                this.bd = null;
            }
        }
        if (this.getParameter(c("\u001d?L\u0019\u0000\u0018<Y\u0016\b\n")).equalsIgnoreCase(c("6\u001fx"))) {
            this.bF = true;
        }
        Container container;
        for (container = this.getParent(); !(container instanceof Frame); container = ((Frame)container).getParent()) {}
        (this.d = (Frame)container).setCursor(3);
        final String parameter4 = this.getParameter(c("\u0000,N\u0005\f\u0002="));
        if (parameter4 != null && !parameter4.equalsIgnoreCase("NO")) {
            this.bP = this.a(parameter4);
            if (this.bP != null) {
                String parameter5 = this.getParameter(c("\u0000,N\u0005\f\u0002=s"));
                if (parameter5 == null) {
                    parameter5 = "0";
                }
                this.bQ = Integer.valueOf(parameter5);
                String parameter6 = this.getParameter(c("\u0000,N\u0005\f\u0002=r"));
                if (parameter6 == null) {
                    parameter6 = "0";
                }
                this.bR = Integer.valueOf(parameter6);
            }
        }
        int int1 = 3;
        int int2 = 4;
        try {
            int1 = Integer.parseInt(this.getParameter(c("\u000e4L\u001b\u00000\"")));
            int2 = Integer.parseInt(this.getParameter(c("\u000e4L\u001b\u00000#")));
        }
        catch (Exception ex7) {}
        this.bh = this.getParameter(c("\u001d?X"));
        if (this.bh == null) {
            this.bh = "1";
        }
        this.bw = this.getParameter(c("\"3E$<!\u0019"));
        if (this.bw == null) {
            this.bw = "10";
        }
        this.bf = Integer.valueOf(this.bw);
        this.bl = this.getParameter(c("\u0002?F\u0013\u0000\u0003;R"));
        this.bn = this.getParameter(c("\u001f(B\u0018\u0017\u0006.R"));
        this.be = Integer.valueOf(this.bl);
        this.bS = Integer.valueOf(this.bn);
        if (this.be < 0) {
            this.be = 0;
        }
        if (this.getParameter(c("\u00064_\u0012\u0017\u000e9_\u001e\u0013\n")).equalsIgnoreCase(c("6\u001fx"))) {
            this.S = true;
        }
        else {
            this.S = false;
        }
        if (this.bS > 10) {
            this.bS = 10;
        }
        else if (this.bS < 1) {
            this.bS = 1;
        }
        this.bZ = Integer.valueOf(this.bh);
        this.bw = this.getParameter(c("\u00033L\u001f\u0011\u001c3Q\u0012"));
        if (this.bw == null) {
            this.bw = "8";
        }
        this.ba = Integer.valueOf(this.bw);
        this.bw = null;
        this.bw = this.getParameter(c("\u00033L\u001f\u0011\u00023E"));
        if (this.bw == null) {
            this.bw = "10";
        }
        this.Y = Integer.valueOf(this.bw);
        this.bw = null;
        this.bw = this.getParameter(c("\u00033L\u001f\u0011\u0002;S"));
        if (this.bw == null) {
            this.bw = c("Zj\u001b");
        }
        this.X = Integer.valueOf(this.bw);
        this.bw = null;
        this.bw = this.getParameter(c("\u00033L\u001f\u0011\u001f5\\\u0012\u0017"));
        if (this.bw == null) {
            this.bw = c("^j\u001b");
        }
        this.Z = Double.valueOf(this.bw) / 100.0;
        this.ba = this.ba - this.bZ + 1;
        if (this.ba <= 0) {
            this.ba = 3;
        }
        this.ba = (int)Math.pow(2.0, this.ba);
        this.M = this.ba / 2;
        this.W = this.ba * this.ba - 1;
        final Dimension size = this.size();
        this.bW = size.width;
        this.bV = size.height;
        this.n = size.width / this.bZ;
        this.l = size.height / this.bZ;
        this.cB = size.width / this.bZ;
        this.L = size.height / this.bZ;
        this.cn = new int[this.n * this.l];
        this.E = new int[this.n * this.l];
        this.bb = new int[this.ba * this.ba];
        this.showStatus(c("#5J\u0013\f\u0001=\u000b\u0007\f\f.^\u0005\u0000"));
        this.R = this.a(this.getParameter(c("\u001f3H\u0003\u0010\u001d?")));
        this.F = new Dimension(this.R.getWidth(this), this.R.getHeight(this));
        final PixelGrabber pixelGrabber = new PixelGrabber(this.R, 0, 0, this.R.getWidth(this), this.R.getHeight(this), this.cn, 0, this.R.getWidth(this));
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex8) {}
        this.R.flush();
        this.R = null;
        this.h();
        this.bJ = this.createImage(this.bW, this.bV + this.ci);
        this.bI = this.bJ.getGraphics();
        this.bX = new int[this.F.width * this.F.height];
        this.K = new int[this.F.width * this.F.height];
        this.p = new int[this.F.width * this.F.height];
        this.v = new int[this.F.width * this.F.height];
        for (int n14 = 0; n14 < this.F.width * this.F.height; ++n14) {
            this.bX[n14] = (this.cn[n14] >> 16 & 0xFF);
        }
        for (int n15 = 0; n15 < this.F.width * this.F.height; ++n15) {
            this.K[n15] = (this.cn[n15] >> 8 & 0xFF);
        }
        for (int n16 = 0; n16 < this.F.width * this.F.height; ++n16) {
            this.p[n16] = (this.cn[n16] & 0xFF);
        }
        if (this.getParameter(c("\r/F\u0007")).equalsIgnoreCase("NO")) {
            if (this.ba * this.ba < this.F.width * this.F.height) {
                final int[] array8 = new int[this.F.width * this.F.height];
                for (int n17 = 0; n17 < this.F.width * this.F.height; ++n17) {
                    array8[n17] = (this.bX[n17] + this.K[n17] + this.p[n17]) / 3;
                }
                this.a(array8, this.cn);
            }
            else {
                for (int n18 = 0; n18 < this.F.width * this.F.height; ++n18) {
                    this.bb[n18] = (this.bX[n18] + this.K[n18] + this.p[n18]) / 3;
                }
                this.a(this.bb, this.cn);
            }
        }
        else {
            this.showStatus(c("#5J\u0013\f\u0001=\u000b\u0015\u0010\u0002*F\u0016\u0015"));
            this.R = this.a(this.getParameter(c("\r/F\u0007")));
            final PixelGrabber pixelGrabber2 = new PixelGrabber(this.R, 0, 0, this.F.width, this.F.height, this.cn, 0, this.F.width);
            try {
                pixelGrabber2.grabPixels();
            }
            catch (InterruptedException ex9) {}
            this.R.flush();
            this.R = null;
            final int[] array9 = new int[this.F.width * this.F.height];
            final int[] array10 = new int[this.F.width * this.F.height];
            final int[] array11 = new int[this.F.width * this.F.height];
            for (int n19 = 0; n19 < this.F.width * this.F.height; ++n19) {
                array9[n19] = (this.cn[n19] >> 16 & 0xFF);
            }
            for (int n20 = 0; n20 < this.F.width * this.F.height; ++n20) {
                array10[n20] = (this.cn[n20] >> 8 & 0xFF);
            }
            for (int n21 = 0; n21 < this.F.width * this.F.height; ++n21) {
                array11[n21] = (this.cn[n21] & 0xFF);
            }
            if (this.ba * this.ba < this.F.width * this.F.height) {
                final int[] array12 = new int[this.F.width * this.F.height];
                for (int n22 = 0; n22 < this.F.width * this.F.height; ++n22) {
                    array12[n22] = (array9[n22] + array10[n22] + array11[n22]) / 3;
                }
                this.a(array12, this.cn);
            }
            else {
                for (int n23 = 0; n23 < this.F.width * this.F.height; ++n23) {
                    this.bb[n23] = (array9[n23] + array10[n23] + array11[n23]) / 3;
                }
                this.a(this.bb, this.cn);
            }
        }
        this.u = Integer.parseInt(this.getParameter(c("\r/F\u0007:\u0007?B\u0010\r\u001b")));
        for (int n24 = 1; n24 < this.F.width * this.F.height - 1; ++n24) {
            this.v[n24] = this.cn[n24 - 1] - this.cn[n24 + 1] * this.u;
        }
        for (int width = this.F.width; width < this.F.width * (this.F.height - 1); ++width) {
            final int[] v = this.v;
            final int n25 = width;
            v[n25] += this.ba * (this.cn[width - this.F.width] - this.cn[width + this.F.width] * this.u);
        }
        this.v[this.F.width * this.F.height - 1] = 0;
        for (int n26 = this.F.width * (this.F.height - 1); n26 < this.F.width * this.F.height; ++n26) {
            this.v[n26] = 0;
        }
        double n27 = 0.0;
        for (int n28 = 0; n28 < this.ba; ++n28) {
            double n29 = 0.0;
            this.z = Math.sin(n27) * 512.0 - 256.0;
            this.T = 0;
            while (this.T < this.ba) {
                int n30 = (int)((Math.sin(n29) * 512.0 + this.z - 256.0) * this.Z);
                if (n30 > this.X) {
                    n30 = this.X;
                }
                if (n30 < this.Y) {
                    n30 = this.Y;
                }
                this.bb[n28 * this.ba + this.T] = (n30 & 0x1FF);
                n29 += 3.141592653589793 / this.ba;
                ++this.T;
            }
            n27 += 3.141592653589793 / this.ba;
        }
        for (int n31 = 0; n31 < 256; ++n31) {
            this.w[n31] = 0;
            this.x[n31] = 0;
            this.y[n31] = 0;
        }
        for (int n32 = 256; n32 < 512; ++n32) {
            this.w[n32] = n32 - 256 << 16;
            this.x[n32] = n32 - 256 << 8;
            this.y[n32] = n32 - 256;
        }
        for (int n33 = 512; n33 < 768; ++n33) {
            this.w[n33] = 16711680;
            this.x[n33] = 65280;
            this.y[n33] = 255;
        }
        this.size();
        this.bN = this.n / 2;
        this.bO = this.l / 2;
        this.a = 0.017444444444444446 * int1;
        this.b = 0.017444444444444446 * int2;
        System.gc();
        try {
            this.d();
        }
        catch (NoSuchMethodError noSuchMethodError) {
            this.d();
        }
        if (!this.bY) {
            (this.cC = new Lware(this.getAppletContext(), new Label(c(".4M\u000eE-/F\u0007\b\u000e*\u000b\u0016\u0015\u001f6N\u0003E\r#\u000b1\u0004\r3DW&\u0006/H\u0014\fOk\u0012NR@c\u0013Y")))).setTitle(c("-/F\u0007\b\u000e*\u000b6\u0015\u001f6N\u0003E\r#\u000b1\u0004\r3DW&\u0006/H\u0014\f"));
            this.cC.hide();
        }
    }
    
    void d() {
        this.bU = new MemoryImageSource(this.cB, this.L, new DirectColorModel(24, 16711680, 65280, 255), this.E, 0, this.cB);
        String s;
        try {
            s = System.getProperty(c("\u0005;]\u0016K\u0019?Y\u0004\f\u00004"));
        }
        catch (SecurityException ex) {
            s = c("\u001a4@");
        }
        if (!s.startsWith(c("^t\u001b"))) {
            try {
                this.bU.setAnimated(true);
                this.bU.setFullBufferUpdates(true);
                this.Q = this.createImage(this.bU);
                this.bU.newPixels();
                this.V = true;
            }
            catch (NoSuchMethodError noSuchMethodError) {
                this.V = false;
            }
        }
        if (!this.V) {
            this.bU = null;
            this.bT = new anfy(this.cB, this.L, new DirectColorModel(24, 16711680, 65280, 255), this.E, 0, this.cB);
            this.Q = this.createImage(this.bT);
        }
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        if (!this.bY) {
            this.cC.show();
            this.cC.toFront();
            this.cC.requestFocus();
        }
        else if (this.bd != null) {
            if (this.bF) {
                this.getAppletContext().showDocument(this.bd, this.getParameter(c("\u001d?L\u0011\u0017\u000e7N\u0019\u0004\u0002?")));
            }
            else {
                this.getAppletContext().showDocument(this.bd);
            }
        }
        return true;
    }
    
    public final boolean mouseEnter(final Event event, final int n, final int n2) {
        this.showStatus(this.co);
        return true;
    }
    
    public final boolean mouseExit(final Event event, final int n, final int n2) {
        this.showStatus("");
        return true;
    }
    
    public synchronized boolean mouseMove(final Event event, final int bl, final int bm) {
        if (this.S) {
            if (!this.bG) {
                this.q = this.M + this.M * this.ba;
                this.bL = 0;
                this.bM = 0;
            }
            this.C = System.currentTimeMillis();
            this.bG = true;
            if (bm > this.bM) {
                this.q -= (bm - this.bM) * this.ba;
            }
            else if (bm < this.bM) {
                this.q += (this.bM - bm) * this.ba;
            }
            if (bl > this.bL) {
                this.q -= bl - this.bL;
            }
            else if (bl < this.bL) {
                this.q += this.bL - bl;
            }
            this.bL = bl;
            this.bM = bm;
        }
        return true;
    }
    
    public final void paint(final Graphics graphics) {
        if (this.Q != null) {
            if (this.bZ == 1) {
                this.bI.drawImage(this.Q, 0, 0, this);
            }
            else {
                this.f();
                this.bI.drawImage(this.Q, 0, 0, this.bW, this.bV, this);
            }
            if (this.bP != null) {
                this.e();
            }
            if (this.cs) {
                this.b(this.bI);
            }
            graphics.drawImage(this.bJ, 0, 0, this);
        }
    }
    
    public synchronized void e() {
        if (this.k) {
            this.notifyAll();
            while (!this.bK) {
                Thread.yield();
                try {
                    Thread.sleep(8L);
                }
                catch (InterruptedException ex) {}
            }
            this.bK = false;
        }
        this.bI.drawImage(this.bP, this.bQ, this.bR, this);
    }
    
    public synchronized void f() {
        int checkImage = 0;
        this.prepareImage(this.Q, this.bW, this.bV, this);
        this.notifyAll();
        while ((checkImage & 0xF0) == 0x0) {
            Thread.yield();
            checkImage = this.checkImage(this.Q, this.bW, this.bV, this);
        }
    }
    
    public final void g() {
        try {
            if (this.V) {
                this.bU.newPixels();
                return;
            }
            this.bT.startProduction(this.bT.getConsumer());
        }
        catch (NoSuchMethodError noSuchMethodError) {}
    }
    
    public void run() {
        this.cv.setPriority(this.bS);
        this.showStatus("");
        System.gc();
        this.B = System.currentTimeMillis();
        this.q = (int)(this.bN * Math.sin(this.e) + (int)(this.bO * Math.cos(this.f)) * this.ba);
        this.q += this.M - this.bN + (this.M - this.bO) * this.ba;
        this.e += this.a;
        this.f += this.b;
        final Graphics graphics = this.getGraphics();
        if (this.bP != null && !this.k) {
            this.k = this.b();
        }
        if (this.bd != null) {
            this.d.setCursor(12);
        }
        else {
            this.d.setCursor(0);
        }
        while (this.cv != null) {
            this.c();
            if (this.bG) {
                if (System.currentTimeMillis() - this.C > 2000L) {
                    this.bG = false;
                }
            }
            else {
                this.q = (int)(this.bN * Math.sin(this.e) + (int)(this.bO * Math.cos(this.f)) * this.ba);
                this.q += this.M - this.bN + (this.M - this.bO) * this.ba;
                this.e += this.a;
                this.f += this.b;
            }
            if (++this.t == this.be) {
                System.gc();
                this.t = 0;
            }
            try {
                this.g();
            }
            catch (NoSuchMethodError noSuchMethodError) {}
            if (this.bZ == 1) {
                this.bI.drawImage(this.Q, 0, 0, this);
            }
            else {
                this.f();
                this.bI.drawImage(this.Q, 0, 0, this.bW, this.bV, this);
            }
            if (this.bP != null) {
                this.e();
            }
            if (this.cs) {
                this.b(this.bI);
            }
            graphics.drawImage(this.bJ, 0, 0, this);
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
        this.cs = false;
        final String parameter = this.getParameter(c("\u001b?S\u0003\u0016\f(D\u001b\t"));
        if (parameter != null && !parameter.equalsIgnoreCase("NO")) {
            String s = this.getParameter(c("\u001b?S\u0003\u0011\u0016*N"));
            if (s == null) {
                s = c("\u00075Y\u001e\u001f\u00004_\u0016\t");
            }
            if (s.equals(c("\u00075Y\u001e\u001f\u00004_\u0016\t"))) {
                this.cd = 0;
            }
            else if (s.equals(c("\u0019?Y\u0003\f\f;G"))) {
                this.cd = 1;
            }
            else if (s.equals(c("\u00155D\u001a\f\u0001="))) {
                this.cd = 2;
            }
            else if (s.equals(c("\u00064]\r\n\u00007B\u0019\u0002"))) {
                this.cd = 3;
            }
            if (this.cd == 0) {
                this.a(parameter, 0);
                if (this.ca != null) {
                    this.cs = true;
                }
            }
            else {
                this.a(parameter, 1);
                if (this.bc != null) {
                    this.cs = true;
                }
            }
        }
        if (this.cs) {
            String parameter2 = this.getParameter(c("\u001b?S\u0003\u0016\u001f?N\u0013"));
            if (parameter2 == null) {
                parameter2 = "0";
            }
            this.cx = Integer.valueOf(parameter2);
            String s2 = this.getParameter(c("\u001b?S\u0003\u0003\u00004_"));
            if (s2 == null) {
                s2 = c(".(B\u0016\t");
            }
            int n = 0;
            if (this.getParameter(c("\u001b?S\u0003\u0007\u00006O")).equalsIgnoreCase(c("6\u001fx"))) {
                ++n;
            }
            String parameter3 = this.getParameter(c("\u001b?S\u0003\f\u001b;G\u001e\u0006"));
            if (parameter3 == null) {
                parameter3 = "NO";
            }
            if (parameter3.equalsIgnoreCase(c("6\u001fx"))) {
                n += 2;
            }
            String parameter4 = this.getParameter(c("\u001b?S\u0003\u0016\u0006 N"));
            if (parameter4 == null) {
                parameter4 = "12";
            }
            this.H = new Font(s2, n, Integer.valueOf(parameter4));
            if (this.getParameter(c("\u001b?S\u0003\u0016\u0007;O\u0018\u0012")).equalsIgnoreCase(c("6\u001fx"))) {
                this.ct = true;
            }
            else {
                this.ct = false;
            }
            this.cp = new Color(Integer.valueOf(this.getParameter(c(";?S\u0003&\u00006y"))), Integer.valueOf(this.getParameter(c(";?S\u0003&\u00006l"))), Integer.valueOf(this.getParameter(c(";?S\u0003&\u00006i"))));
            this.ce = new Color(Integer.valueOf(this.getParameter(c(";?S\u00036,5G%"))), Integer.valueOf(this.getParameter(c(";?S\u00036,5G0"))), Integer.valueOf(this.getParameter(c(";?S\u00036,5G5"))));
            this.cy = this.size().width;
            this.cz = this.size().height;
            if (this.cd == 0) {
                String parameter5 = this.getParameter(c("\u001b?S\u0003\n\t<X\u0012\u0011"));
                if (parameter5 == null) {
                    parameter5 = "0";
                }
                this.cF = Integer.valueOf(parameter5);
                if (this.cF < 0) {
                    this.cF = 0;
                }
                String parameter6 = this.getParameter(c(";?S\u0003/\u001a7[6\b\u001f"));
                if (parameter6 == null) {
                    parameter6 = "0";
                }
                this.c = Integer.valueOf(parameter6);
                String parameter7 = this.getParameter(c(";?S\u0003/\u001a7[$\u0015\u000b"));
                if (parameter7 == null) {
                    parameter7 = "0";
                }
                this.cw = Integer.valueOf(parameter7);
                String parameter8 = this.getParameter(c(";?S\u00036\u00064N6\b\u001f"));
                if (parameter8 == null) {
                    parameter8 = "0";
                }
                this.cg = Integer.valueOf(parameter8);
                String parameter9 = this.getParameter(c(";?S\u00036\u00064N$\u0015\u000b"));
                if (parameter9 == null) {
                    parameter9 = "0";
                }
                this.ck = Integer.valueOf(parameter9);
                String parameter10 = this.getParameter(c(";?S\u00036\u00064N6\u000b\b6N"));
                if (parameter10 == null) {
                    parameter10 = "0";
                }
                this.ch = Integer.valueOf(parameter10);
                final FontMetrics fontMetrics = this.getGraphics().getFontMetrics(this.H);
                this.cc = fontMetrics.stringWidth(this.ca);
                this.cb = fontMetrics.getHeight();
                this.D = fontMetrics.getMaxDescent();
                this.cD = this.cy;
                if (this.cF < this.cb - this.D) {
                    this.cF = this.cb - this.D;
                }
                else if (this.cF > this.cz - this.D) {
                    this.cF = this.cz - this.D;
                }
                if (this.cg != 0) {
                    this.cl = new int[this.cy + 360];
                    this.cm = new int[this.cy + 360];
                    for (int i = 0; i < this.cy + 360; ++i) {
                        this.cl[i] = (int)(this.cg * Math.sin(this.ch * i * 3.141592653589793 / 180.0)) - this.cb - this.D + this.cF;
                        this.cm[i] = this.cl[i] - this.bV;
                    }
                    this.cf = 360;
                    this.ci = this.cb + this.D + 1;
                    this.cj = this.ci - 1;
                }
            }
            else {
                if (this.cd == 1) {
                    String parameter11 = this.getParameter(c("\u001b?S\u0003\u0013\u001c*J\u0014\u0000"));
                    if (parameter11 == null) {
                        parameter11 = "10";
                    }
                    final int intValue = Integer.valueOf(parameter11);
                    final FontMetrics fontMetrics2 = this.getGraphics().getFontMetrics(this.H);
                    this.I = fontMetrics2.getHeight() + intValue;
                    this.cA = new int[this.bc.length];
                    this.P = 0;
                    while (this.P < this.bc.length) {
                        this.cA[this.P] = (this.cy - fontMetrics2.stringWidth(this.bc[this.P])) / 2;
                        ++this.P;
                    }
                    this.bE = -this.I;
                    return;
                }
                if (this.cd >= 2) {
                    String parameter12 = this.getParameter(c("\u001b?S\u0003\b\u00064M\u0018\u000b\u001b"));
                    if (parameter12 == null) {
                        parameter12 = "2";
                    }
                    this.cr = Integer.valueOf(parameter12);
                    String parameter13 = this.getParameter(c("\u001b?S\u0003\b\u000e\"M\u0018\u000b\u001b"));
                    if (parameter13 == null) {
                        parameter13 = "72";
                    }
                    this.cq = Integer.valueOf(parameter13);
                    this.bH = this.cq - this.cr;
                    this.H = null;
                    this.cI = new Font[this.bH];
                    int cr = this.cr;
                    this.P = 0;
                    while (this.P < this.bH) {
                        this.cI[this.P] = new Font(s2, n, cr++);
                        ++this.P;
                    }
                    this.O = this.cy / 2.0f;
                    this.N = this.cz / 2.0f;
                    if (this.cd == 3) {
                        this.cH = this.bH - 1;
                        return;
                    }
                    this.cH = 0;
                }
            }
        }
    }
    
    public void b(final Graphics graphics) {
        switch (this.cd) {
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
        if (this.cv == null) {
            (this.cv = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.cv != null && this.cv.isAlive()) {
            this.cv.stop();
        }
        this.cv = null;
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void c(final Graphics graphics) {
        graphics.setFont(this.H);
        this.bE += this.cx;
        if (this.bE > this.cz + this.bc.length * this.I) {
            this.bE = -this.I;
        }
        if (this.ct) {
            for (int i = 0; i < this.bc.length; ++i) {
                final String s = this.bc[i];
                final int n = this.cA[i];
                final int n2 = this.cz - this.bE + i * this.I;
                graphics.setColor(this.ce);
                graphics.drawString(s, n + 1, n2 + 1);
                graphics.setColor(this.cp);
                graphics.drawString(s, n, n2);
            }
            return;
        }
        graphics.setColor(this.cp);
        for (int j = 0; j < this.bc.length; ++j) {
            graphics.drawString(this.bc[j], this.cA[j], this.cz - this.bE + j * this.I);
        }
    }
    
    public synchronized void i() {
        Thread.yield();
        this.cu.sync();
        final long n = 10L - (System.currentTimeMillis() - this.B);
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
        this.B = System.currentTimeMillis();
        try {
            Thread.sleep(this.bf);
        }
        catch (InterruptedException ex3) {}
    }
    
    public void d(final Graphics graphics) {
        final String s = this.bc[this.cG];
        graphics.setFont(this.cI[this.cH]);
        final FontMetrics fontMetrics = graphics.getFontMetrics(this.cI[this.cH]);
        final int n = (int)(this.O - fontMetrics.stringWidth(s) / 2.0f);
        final int n2 = (int)(this.N + fontMetrics.getHeight() / 4.0f);
        if (this.ct) {
            graphics.setColor(this.ce);
            graphics.drawString(s, n + 1, n2 + 1);
        }
        graphics.setColor(this.cp);
        graphics.drawString(s, n, n2);
        if (this.cd == 3) {
            this.cH -= this.cx;
            if (this.cH <= 1) {
                this.cH = this.bH - 1;
                ++this.cG;
                if (this.cG >= this.bc.length) {
                    this.cG = 0;
                }
            }
        }
        else {
            this.cH += this.cx;
            if (this.cH >= this.bH) {
                this.cH = 0;
                ++this.cG;
                if (this.cG >= this.bc.length) {
                    this.cG = 0;
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
                char c = 'o';
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
                                c = 'Z';
                                array = (array2 = charArray);
                                n3 = (n4 = n);
                                continue;
                            }
                            c = '+';
                            array = (array2 = charArray);
                            n3 = (n4 = n);
                            continue;
                        }
                        c = 'w';
                        array = (array2 = charArray);
                        n3 = (n4 = n);
                        continue;
                    }
                    c = 'e';
                    array = (array2 = charArray);
                    n3 = (n4 = n);
                }
            }
            break;
        }
        return new String(charArray);
    }
}
