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

public class Warp extends Applet implements Runnable, ImageObserver
{
    private int a;
    Frame b;
    float c;
    boolean d;
    final String e = "?m0\u0019\r\n=\"\fH8|\"\u001c\u0007^^)\u0000\u000b\u001dt`]\u001f\tjn\u0014\u0006\u0018";
    int f;
    float g;
    float h;
    float i;
    float j;
    long k;
    int l;
    int[] m;
    Font n;
    int o;
    private Graphics p;
    int q;
    float r;
    float s;
    double t;
    private Image u;
    private Image v;
    int w;
    int x;
    int y;
    boolean z;
    String[] A;
    URL B;
    int C;
    int D;
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
    String O;
    String P;
    String Q;
    String R;
    int S;
    boolean T;
    int U;
    int V;
    int W;
    private Graphics X;
    private Image Y;
    boolean Z;
    private Image ba;
    int bb;
    int bc;
    float bd;
    float be;
    double bf;
    int bg;
    int bh;
    anfy bi;
    MemoryImageSource bj;
    float bk;
    int bl;
    int bm;
    boolean bn;
    int bo;
    String bp;
    int bq;
    int br;
    int bs;
    Color bt;
    int bu;
    int bv;
    int bw;
    int bx;
    int by;
    int bz;
    int[] bA;
    int[] bB;
    int[] bC;
    String bD;
    Color bE;
    int bF;
    int bG;
    boolean bH;
    boolean bI;
    Toolkit bJ;
    Thread bK;
    int bL;
    int bM;
    int bN;
    int bO;
    int bP;
    int bQ;
    int[] bR;
    int bS;
    float bT;
    float bU;
    float bV;
    float bW;
    int bX;
    Lware bY;
    int bZ;
    int ca;
    float[] cb;
    float[] cc;
    int cd;
    int ce;
    int cf;
    int cg;
    int ch;
    Font[] ci;
    
    private final void a() {
        while (true) {
            this.showStatus(c(":r.R\u001c^o%\u0018\u0007\bx`\u0002\u001f\t3!\u001b\u000e\u0007w!\u0003\tP~/\u0018H\u001do%\u0011\u0001\nn`\u0019\u0001\u0010x`\u001c\u0006^U\u00148$_"));
            try {
                Thread.sleep(500L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public synchronized boolean b() {
        this.prepareImage(this.ba, this);
        if (this.z) {
            for (int i = 0; i < 3; ++i) {
                this.notifyAll();
                Thread.yield();
                try {
                    Thread.sleep(100L);
                }
                catch (InterruptedException ex) {}
            }
            return this.Z;
        }
        return false;
    }
    
    public void destroy() {
        if (this.ba != null) {
            this.ba.flush();
        }
        this.ba = null;
        if (this.Y != null) {
            this.Y.flush();
        }
        this.Y = null;
        if (this.X != null) {
            this.X.dispose();
        }
        this.X = null;
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
                this.showStatus(c("7p!\u0012\r^") + s + c("^s/\u0001H\u0018r5\u001b\f_"));
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
                            this.bp = new String(byteArray);
                            return;
                        }
                        catch (NoSuchMethodError noSuchMethodError) {
                            this.bp = new String(byteArray, 0);
                            return;
                        }
                    }
                    int n3 = 1;
                    for (int j = 0; j < n2; ++j) {
                        if (byteArray[j] == 10) {
                            ++n3;
                        }
                    }
                    this.A = new String[n3 - 1];
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
                                this.A[l] = new String(byteArray, array2[l], array3[l]);
                            }
                            catch (NoSuchMethodError noSuchMethodError2) {
                                this.A[l] = new String(byteArray, 0, array2[l], array3[l]);
                            }
                        }
                    }
                    catch (StringIndexOutOfBoundsException ex) {
                        this.A = null;
                    }
                }
                catch (IOException ex2) {}
            }
            catch (IOException ex3) {}
        }
        catch (MalformedURLException ex4) {}
    }
    
    public void a(final Graphics graphics) {
        graphics.setFont(this.n);
        if (this.a == 0) {
            this.ce = this.cf;
        }
        else {
            this.y += this.bN;
            this.ce = this.cf - (int)Math.abs(this.a * Math.sin(this.y / 90.0 * 3.141592653589793));
        }
        if (this.bv != 0) {
            for (int i = 0; i < this.bP; ++i) {
                final int n = this.bA[this.bu + i];
                graphics.copyArea(i, n, 1, this.bx, 0, this.bl - n);
            }
            if (this.bI) {
                graphics.setColor(this.bt);
                graphics.drawString(this.bp, this.ca + 1, this.bl + this.bq + 1);
            }
            graphics.setColor(this.bE);
            graphics.drawString(this.bp, this.ca, this.bl + this.bq);
            for (int j = 0; j < this.bP; ++j) {
                graphics.copyArea(j, this.bl, 1, this.by, 0, this.bB[this.bu + j]);
            }
            this.bu -= this.bz;
            if (this.bu < 0) {
                this.bu += 360;
            }
        }
        else {
            if (this.bI) {
                graphics.setColor(this.bt);
                graphics.drawString(this.bp, this.ca + 1, this.ce + 1);
            }
            graphics.setColor(this.bE);
            graphics.drawString(this.bp, this.ca, this.ce);
        }
        this.ca -= this.bO;
        if (this.ca < -this.br) {
            this.ca = this.bP;
        }
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if (image == this.ba) {
            if (n == 16) {
                this.Z = true;
            }
            return true;
        }
        return true;
    }
    
    public void init() {
        this.setLayout(null);
        this.addNotify();
        this.bJ = this.getToolkit();
        this.bD = this.getParameter(c("\ri!\u0001\u001d\rp3\u0012"));
        final String parameter = this.getParameter(c("\u001do%\u0011\u0001\nn"));
        if (parameter != null) {
            if (!parameter.startsWith(c("?m0\u0019\r\n=\"\fH8|\"\u001c\u0007^^)\u0000\u000b\u001dt`]\u001f\tjn\u0014\u0006\u0018"))) {
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
            s = c("\u0018t,\u0010");
        }
        String s2;
        try {
            s2 = this.getDocumentBase().getHost();
        }
        catch (SecurityException ex2) {
            s2 = "";
        }
        if (s.equalsIgnoreCase(c("\u0018t,\u0010")) || s2.length() == 0 || s2.equalsIgnoreCase(c("\u0012r#\u0014\u0004\u0016r3\u0001")) || s2.equals(c("O/w[XP-nD"))) {
            this.bn = true;
        }
        else {
            if (s2.startsWith(c("\tj7["))) {
                s2 = s2.substring(4);
            }
            final String parameter2 = this.getParameter(c("\fx'\u0016\u0007\u001ax"));
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
                        if (s5.startsWith(c("\tj7["))) {
                            substring = s5.substring(4);
                        }
                        else {
                            substring = s5;
                        }
                        if (s2.equalsIgnoreCase(substring)) {
                            this.bn = true;
                        }
                    }
                }
            }
        }
        final String parameter3 = this.getParameter(c("\fx'\u0019\u0001\u0010v"));
        if (parameter3 != null && !parameter3.equalsIgnoreCase("NO")) {
            try {
                this.B = new URL(this.getDocumentBase(), parameter3);
            }
            catch (MalformedURLException ex6) {
                this.B = null;
            }
        }
        if (this.getParameter(c("\fx'\u001b\r\t{2\u0014\u0005\u001b")).equalsIgnoreCase(c("'X\u0013"))) {
            this.T = true;
        }
        Container container;
        for (container = this.getParent(); !(container instanceof Frame); container = ((Frame)container).getParent()) {}
        (this.b = (Frame)container).setCursor(3);
        final String parameter4 = this.getParameter(c("\u0011k%\u0007\u0001\u0013z"));
        if (parameter4 != null && !parameter4.equalsIgnoreCase("NO")) {
            this.ba = this.a(parameter4);
            if (this.ba != null) {
                String parameter5 = this.getParameter(c("\u0011k%\u0007\u0001\u0013z\u0018"));
                if (parameter5 == null) {
                    parameter5 = "0";
                }
                this.bb = Integer.valueOf(parameter5);
                String parameter6 = this.getParameter(c("\u0011k%\u0007\u0001\u0013z\u0019"));
                if (parameter6 == null) {
                    parameter6 = "0";
                }
                this.bc = Integer.valueOf(parameter6);
            }
        }
        this.O = this.getParameter(c("3t.&10^"));
        if (this.O == null) {
            this.O = "10";
        }
        this.D = Integer.valueOf(this.O);
        this.F = this.getParameter(c("\fx3"));
        if (this.F == null) {
            this.F = "2";
        }
        this.J = this.getParameter(c("\t|$\u0011Y"));
        if (this.J == null) {
            this.J = "2";
        }
        this.K = this.getParameter(c("\t|$\u0011Z"));
        if (this.K == null) {
            this.K = "1";
        }
        this.L = this.getParameter(c("\t,-\u001c\u0006"));
        if (this.L == null) {
            this.L = c("S(pE");
        }
        this.M = this.getParameter(c("\t,-\u0014\u0010"));
        if (this.M == null) {
            this.M = c("K-p");
        }
        this.N = this.getParameter(c("\t/-\u001c\u0006"));
        if (this.N == null) {
            this.N = c("S,pE");
        }
        this.P = this.getParameter(c("\t/-\u0014\u0010"));
        if (this.P == null) {
            this.P = c("M(p");
        }
        this.Q = this.getParameter(c("\t,3\u0001\t\fi"));
        if (this.Q == null) {
            this.Q = c("S(pE");
        }
        this.R = this.getParameter(c("\t/3\u0001\t\fi"));
        if (this.R == null) {
            this.R = "5";
        }
        this.G = this.getParameter(c("\t|2\u0005\u0005\u0011y%"));
        if (this.G == null) {
            this.G = "1";
        }
        this.H = this.getParameter(c("\u0013x-\u0011\r\u0012|9"));
        this.I = this.getParameter(c("\u000eo)\u001a\u001a\u0017i9"));
        this.bo = Integer.valueOf(this.F);
        this.bX = Integer.valueOf(this.G);
        this.g = Float.valueOf(this.J) / 10.0f;
        this.h = Float.valueOf(this.K) / 10.0f;
        this.bU = Float.valueOf(this.L) / 10.0f;
        this.bT = Float.valueOf(this.M) / 10.0f;
        this.bW = Float.valueOf(this.N) / 10.0f;
        this.bV = Float.valueOf(this.P) / 10.0f;
        this.i = Float.valueOf(this.Q) / 10.0f + 0.4f;
        this.j = Float.valueOf(this.R) / 10.0f;
        this.C = Integer.valueOf(this.H);
        this.bh = Integer.valueOf(this.I);
        if (this.C < 0) {
            this.C = 0;
        }
        if (this.bh > 10) {
            this.bh = 10;
        }
        else if (this.bh < 1) {
            this.bh = 1;
        }
        if (this.g != 0.0f) {
            this.g += 0.01;
        }
        if (this.bo > 8) {
            this.bo = 8;
        }
        else if (this.bo < 1) {
            this.bo = 1;
        }
        this.bS = this.size().width / this.bo;
        this.q = this.size().height / this.bo;
        this.bm = this.bS * this.bo;
        this.bl = this.q * this.bo;
        this.bM = this.bS * this.q;
        if (this.h > 2.5) {
            this.h = 2.5f;
        }
        else if (this.h < 0.0f) {
            this.h = 0.0f;
        }
        if (this.i > this.bT) {
            this.i = this.bT;
        }
        else if (this.i < this.bU) {
            this.i = this.bU;
        }
        if (this.j > this.bV) {
            this.j = this.bV;
        }
        else if (this.j < this.bW) {
            this.j = this.bW;
        }
        this.E = this.getParameter(c("\u0017p!\u0012\r"));
        this.showStatus(c("2r!\u0011\u0001\u0010z`\u001c\u0005\u001fz%[FP"));
        this.m = new int[this.bM];
        this.v = this.a(this.E);
        this.x = this.v.getWidth(this);
        this.w = this.v.getHeight(this);
        this.bC = new int[this.x * this.w];
        final PixelGrabber pixelGrabber = new PixelGrabber(this.v, 0, 0, this.x, this.w, this.bC, 0, this.x);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex7) {}
        this.v.flush();
        this.v = null;
        this.cc = new float[this.bM];
        this.bg = 0;
        for (int n14 = 0; n14 < this.q; ++n14) {
            for (int n15 = 0; n15 < this.bS; ++n15) {
                this.bd = n15 - this.bS / 2;
                this.be = n14 - this.q / 2;
                this.cc[this.bg++] = (float)Math.sqrt(this.bd * this.bd + this.be * this.be);
            }
        }
        this.cb = new float[this.bM];
        this.bg = 0;
        for (int n16 = 0; n16 < this.q; ++n16) {
            for (int n17 = 0; n17 < this.bS; ++n17) {
                this.bd = n17 - this.bS / 2;
                this.be = n16 - this.q / 2;
                if (this.bd != 0.0f || this.be != 0.0f) {
                    this.cb[this.bg++] = (float)Math.atan2(this.be, this.bd);
                }
                else {
                    this.cb[this.bg++] = 1.0f;
                }
            }
        }
        try {
            this.c();
        }
        catch (NoSuchMethodError noSuchMethodError) {
            this.c();
        }
        this.g();
        this.Y = this.createImage(this.bm, this.bl + this.bx);
        this.X = this.Y.getGraphics();
        if (!this.bn) {
            (this.bY = new Lware(this.getAppletContext(), new Label(c("?s&\fH)|2\u0005H\u001fm0\u0019\r\n=\"\fH8|\"\u001c\u0007^^)\u0000\u000b\u001dt`DQG+oLPP")))).setTitle(c(")|2\u0005H?m0\u0019\r\n=\"\fH8|\"\u001c\u0007^^)\u0000\u000b\u001dt"));
            this.bY.hide();
        }
    }
    
    void c() {
        this.bj = new MemoryImageSource(this.bS, this.q, new DirectColorModel(24, 16711680, 65280, 255), this.m, 0, this.bS);
        String s;
        try {
            s = System.getProperty(c("\u0014|6\u0014F\bx2\u0006\u0001\u0011s"));
        }
        catch (SecurityException ex) {
            s = c("\u000bs+");
        }
        if (!s.startsWith(c("O3p"))) {
            try {
                this.bj.setAnimated(true);
                this.bj.setFullBufferUpdates(true);
                this.u = this.createImage(this.bj);
                this.bj.newPixels();
                this.z = true;
            }
            catch (NoSuchMethodError noSuchMethodError) {
                this.z = false;
            }
        }
        if (!this.z) {
            this.bj = null;
            this.bi = new anfy(this.bS, this.q, new DirectColorModel(24, 16711680, 65280, 255), this.m, 0, this.bS);
            this.u = this.createImage(this.bi);
        }
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        if (!this.bn) {
            this.bY.show();
            this.bY.toFront();
            this.bY.requestFocus();
        }
        else if (this.B != null) {
            if (this.T) {
                this.getAppletContext().showDocument(this.B, this.getParameter(c("\fx'\u0013\u001a\u001fp%\u001b\t\u0013x")));
            }
            else {
                this.getAppletContext().showDocument(this.B);
            }
        }
        return true;
    }
    
    public final boolean mouseEnter(final Event event, final int n, final int n2) {
        this.showStatus(this.bD);
        return true;
    }
    
    public final boolean mouseExit(final Event event, final int n, final int n2) {
        this.showStatus("");
        return true;
    }
    
    public final void paint(final Graphics graphics) {
        if (this.u != null) {
            if (this.bo == 1) {
                this.X.drawImage(this.u, 0, 0, this);
            }
            else {
                this.e();
                this.X.drawImage(this.u, 0, 0, this.bm, this.bl, this);
            }
            if (this.ba != null) {
                this.d();
            }
            if (this.bH) {
                this.b(this.X);
            }
            graphics.drawImage(this.Y, 0, 0, this);
        }
    }
    
    public synchronized void d() {
        if (this.d) {
            this.notifyAll();
            while (!this.Z) {
                Thread.yield();
                try {
                    Thread.sleep(8L);
                }
                catch (InterruptedException ex) {}
            }
            this.Z = false;
        }
        this.X.drawImage(this.ba, this.bb, this.bc, this);
    }
    
    public synchronized void e() {
        int checkImage = 0;
        this.prepareImage(this.u, this.bm, this.bl, this);
        this.notifyAll();
        while ((checkImage & 0xF0) == 0x0) {
            Thread.yield();
            checkImage = this.checkImage(this.u, this.bm, this.bl, this);
        }
    }
    
    public final void f() {
        try {
            if (this.z) {
                this.bj.newPixels();
                return;
            }
            this.bi.startProduction(this.bi.getConsumer());
        }
        catch (NoSuchMethodError noSuchMethodError) {}
    }
    
    public void run() {
        this.bK.setPriority(this.bh);
        this.showStatus("");
        System.gc();
        this.k = System.currentTimeMillis();
        final Graphics graphics = this.getGraphics();
        if (this.ba != null && !this.d) {
            this.d = this.b();
        }
        if (this.B != null) {
            this.b.setCursor(12);
        }
        else {
            this.b.setCursor(0);
        }
        while (this.bK != null) {
            this.i();
            if (++this.f == this.C) {
                System.gc();
                this.f = 0;
            }
            try {
                this.f();
            }
            catch (NoSuchMethodError noSuchMethodError) {}
            if (this.bo == 1) {
                this.X.drawImage(this.u, 0, 0, this);
            }
            else {
                this.e();
                this.X.drawImage(this.u, 0, 0, this.bm, this.bl, this);
            }
            if (this.ba != null) {
                this.d();
            }
            if (this.bH) {
                this.b(this.X);
            }
            graphics.drawImage(this.Y, 0, 0, this);
            this.h();
        }
    }
    
    byte a(final int n, final int n2, final int n3, final int n4) {
        final int n5 = n - n2;
        if (n5 >= n3) {
            return (byte)n5;
        }
        return (byte)(n4 - (n3 - n5 - 1));
    }
    
    public void g() {
        this.bH = false;
        final String parameter = this.getParameter(c("\nx8\u0001\u001b\u001do/\u0019\u0004"));
        if (parameter != null && !parameter.equalsIgnoreCase("NO")) {
            String s = this.getParameter(c("\nx8\u0001\u001c\u0007m%"));
            if (s == null) {
                s = c("\u0016r2\u001c\u0012\u0011s4\u0014\u0004");
            }
            if (s.equals(c("\u0016r2\u001c\u0012\u0011s4\u0014\u0004"))) {
                this.bs = 0;
            }
            else if (s.equals(c("\bx2\u0001\u0001\u001d|,"))) {
                this.bs = 1;
            }
            else if (s.equals(c("\u0004r/\u0018\u0001\u0010z"))) {
                this.bs = 2;
            }
            else if (s.equals(c("\u0017s6\u000f\u0007\u0011p)\u001b\u000f"))) {
                this.bs = 3;
            }
            if (this.bs == 0) {
                this.a(parameter, 0);
                if (this.bp != null) {
                    this.bH = true;
                }
            }
            else {
                this.a(parameter, 1);
                if (this.A != null) {
                    this.bH = true;
                }
            }
        }
        if (this.bH) {
            String parameter2 = this.getParameter(c("\nx8\u0001\u001b\u000ex%\u0011"));
            if (parameter2 == null) {
                parameter2 = "0";
            }
            this.bO = Integer.valueOf(parameter2);
            String s2 = this.getParameter(c("\nx8\u0001\u000e\u0011s4"));
            if (s2 == null) {
                s2 = c("?o)\u0014\u0004");
            }
            int n = 0;
            if (this.getParameter(c("\nx8\u0001\n\u0011q$")).equalsIgnoreCase(c("'X\u0013"))) {
                ++n;
            }
            String parameter3 = this.getParameter(c("\nx8\u0001\u0001\n|,\u001c\u000b"));
            if (parameter3 == null) {
                parameter3 = "NO";
            }
            if (parameter3.equalsIgnoreCase(c("'X\u0013"))) {
                n += 2;
            }
            String parameter4 = this.getParameter(c("\nx8\u0001\u001b\u0017g%"));
            if (parameter4 == null) {
                parameter4 = "12";
            }
            this.n = new Font(s2, n, Integer.valueOf(parameter4));
            if (this.getParameter(c("\nx8\u0001\u001b\u0016|$\u001a\u001f")).equalsIgnoreCase(c("'X\u0013"))) {
                this.bI = true;
            }
            else {
                this.bI = false;
            }
            this.bE = new Color(Integer.valueOf(this.getParameter(c("*x8\u0001+\u0011q\u0012"))), Integer.valueOf(this.getParameter(c("*x8\u0001+\u0011q\u0007"))), Integer.valueOf(this.getParameter(c("*x8\u0001+\u0011q\u0002"))));
            this.bt = new Color(Integer.valueOf(this.getParameter(c("*x8\u0001;=r,'"))), Integer.valueOf(this.getParameter(c("*x8\u0001;=r,2"))), Integer.valueOf(this.getParameter(c("*x8\u0001;=r,7"))));
            this.bP = this.size().width;
            this.bQ = this.size().height;
            if (this.bs == 0) {
                String parameter5 = this.getParameter(c("\nx8\u0001\u0007\u0018{3\u0010\u001c"));
                if (parameter5 == null) {
                    parameter5 = "0";
                }
                this.cf = Integer.valueOf(parameter5);
                if (this.cf < 0) {
                    this.cf = 0;
                }
                String parameter6 = this.getParameter(c("*x8\u0001\"\u000bp04\u0005\u000e"));
                if (parameter6 == null) {
                    parameter6 = "0";
                }
                this.a = Integer.valueOf(parameter6);
                String parameter7 = this.getParameter(c("*x8\u0001\"\u000bp0&\u0018\u001a"));
                if (parameter7 == null) {
                    parameter7 = "0";
                }
                this.bN = Integer.valueOf(parameter7);
                String parameter8 = this.getParameter(c("*x8\u0001;\u0017s%4\u0005\u000e"));
                if (parameter8 == null) {
                    parameter8 = "0";
                }
                this.bv = Integer.valueOf(parameter8);
                String parameter9 = this.getParameter(c("*x8\u0001;\u0017s%&\u0018\u001a"));
                if (parameter9 == null) {
                    parameter9 = "0";
                }
                this.bz = Integer.valueOf(parameter9);
                String parameter10 = this.getParameter(c("*x8\u0001;\u0017s%4\u0006\u0019q%"));
                if (parameter10 == null) {
                    parameter10 = "0";
                }
                this.bw = Integer.valueOf(parameter10);
                final FontMetrics fontMetrics = this.getGraphics().getFontMetrics(this.n);
                this.br = fontMetrics.stringWidth(this.bp);
                this.bq = fontMetrics.getHeight();
                this.l = fontMetrics.getMaxDescent();
                this.ca = this.bP;
                if (this.cf < this.bq - this.l) {
                    this.cf = this.bq - this.l;
                }
                else if (this.cf > this.bQ - this.l) {
                    this.cf = this.bQ - this.l;
                }
                if (this.bv != 0) {
                    this.bA = new int[this.bP + 360];
                    this.bB = new int[this.bP + 360];
                    for (int i = 0; i < this.bP + 360; ++i) {
                        this.bA[i] = (int)(this.bv * Math.sin(this.bw * i * 3.141592653589793 / 180.0)) - this.bq - this.l + this.cf;
                        this.bB[i] = this.bA[i] - this.bl;
                    }
                    this.bu = 360;
                    this.bx = this.bq + this.l + 1;
                    this.by = this.bx - 1;
                }
            }
            else {
                if (this.bs == 1) {
                    String parameter11 = this.getParameter(c("\nx8\u0001\u001e\rm!\u0016\r"));
                    if (parameter11 == null) {
                        parameter11 = "10";
                    }
                    final int intValue = Integer.valueOf(parameter11);
                    final FontMetrics fontMetrics2 = this.getGraphics().getFontMetrics(this.n);
                    this.o = fontMetrics2.getHeight() + intValue;
                    this.bR = new int[this.A.length];
                    for (int j = 0; j < this.A.length; ++j) {
                        this.bR[j] = (this.bP - fontMetrics2.stringWidth(this.A[j])) / 2;
                    }
                    this.S = -this.o;
                    return;
                }
                if (this.bs >= 2) {
                    String parameter12 = this.getParameter(c("\nx8\u0001\u0005\u0017s&\u001a\u0006\n"));
                    if (parameter12 == null) {
                        parameter12 = "2";
                    }
                    this.bG = Integer.valueOf(parameter12);
                    String parameter13 = this.getParameter(c("\nx8\u0001\u0005\u001fe&\u001a\u0006\n"));
                    if (parameter13 == null) {
                        parameter13 = "72";
                    }
                    this.bF = Integer.valueOf(parameter13);
                    this.U = this.bF - this.bG;
                    this.n = null;
                    this.ci = new Font[this.U];
                    int bg = this.bG;
                    for (int k = 0; k < this.U; ++k) {
                        this.ci[k] = new Font(s2, n, bg++);
                    }
                    this.s = this.bP / 2.0f;
                    this.r = this.bQ / 2.0f;
                    if (this.bs == 3) {
                        this.ch = this.U - 1;
                        return;
                    }
                    this.ch = 0;
                }
            }
        }
    }
    
    public void b(final Graphics graphics) {
        switch (this.bs) {
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
        if (this.bK == null) {
            (this.bK = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.bK != null && this.bK.isAlive()) {
            this.bK.stop();
        }
        this.bK = null;
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void c(final Graphics graphics) {
        graphics.setFont(this.n);
        this.S += this.bO;
        if (this.S > this.bQ + this.A.length * this.o) {
            this.S = -this.o;
        }
        if (this.bI) {
            for (int i = 0; i < this.A.length; ++i) {
                final String s = this.A[i];
                final int n = this.bR[i];
                final int n2 = this.bQ - this.S + i * this.o;
                graphics.setColor(this.bt);
                graphics.drawString(s, n + 1, n2 + 1);
                graphics.setColor(this.bE);
                graphics.drawString(s, n, n2);
            }
            return;
        }
        graphics.setColor(this.bE);
        for (int j = 0; j < this.A.length; ++j) {
            graphics.drawString(this.A[j], this.bR[j], this.bQ - this.S + j * this.o);
        }
    }
    
    public synchronized void h() {
        Thread.yield();
        this.bJ.sync();
        final long n = 10L - (System.currentTimeMillis() - this.k);
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
        this.k = System.currentTimeMillis();
        try {
            Thread.sleep(this.D);
        }
        catch (InterruptedException ex3) {}
    }
    
    public Warp() {
        this.z = false;
        this.bo = 1;
        this.Z = false;
        this.d = false;
        this.bn = false;
        this.T = false;
        this.bf = 3.141592653589793;
        this.t = 1.5707963267948966;
    }
    
    public final void i() {
        final float[] cc = this.cc;
        final float[] cb = this.cb;
        final int bm = this.bM;
        final int[] m = this.m;
        final int[] bc = this.bC;
        final int n = this.x / 2;
        final int n2 = this.w / 2;
        final int n3 = this.x - 1;
        final int n4 = this.w - 1;
        final float j = this.j;
        final float i = this.i;
        final double n5 = -this.bU;
        final double n6 = this.bT;
        double n7 = 0.1;
        double n8 = 0.1;
        if (n5 != 0.0) {
            n7 = this.t / n5;
        }
        if (n6 != 0.0) {
            n8 = this.t / n6;
        }
        double n9;
        if (this.i <= 0.0f) {
            n9 = Math.sin((this.i + n5) * n7 + this.bf) + 1.0;
        }
        else {
            n9 = Math.sin(this.i * n8 + this.bf + this.t) + 1.0;
        }
        if (this.bX == 1) {
            for (int k = 0; k < bm; ++k) {
                final float n10 = j + cc[k];
                final float n11 = cb[k] + n10 / i;
                m[k] = bc[(n + (int)(n10 * Math.cos(n11)) & n3) + (n2 + (int)(n10 * Math.sin(n11)) & n4) * this.x];
            }
        }
        if (this.bX == 2) {
            for (int l = 0; l < bm; ++l) {
                final float n12 = j + cc[l];
                final int n13 = (int)(n12 * Math.sin(cb[l] + n12 / i));
                m[l] = bc[(n + n13 & n3) + (n2 + n13 & n4) * this.x];
            }
        }
        if (this.bX == 3) {
            for (int n14 = 0; n14 < bm; ++n14) {
                final float n15 = cb[n14] + (j + cc[n14]) / i;
                m[n14] = bc[(n + (int)(n * Math.cos(n15)) & n3) + (n2 + (int)(n2 * Math.sin(n15)) & n4) * this.x];
            }
        }
        this.j += this.h;
        if (this.j < this.bW) {
            this.h = -this.h;
        }
        if (this.j > this.bV) {
            this.h = -this.h;
        }
        if (this.i <= this.bU) {
            this.g = -this.g;
            this.i = this.bU;
        }
        if (this.i >= this.bT) {
            this.g = -this.g;
            this.i = this.bT;
        }
        this.i += (float)(this.g * n9 * 20.0 + this.g * 0.01);
    }
    
    public void d(final Graphics graphics) {
        final String s = this.A[this.cg];
        graphics.setFont(this.ci[this.ch]);
        final FontMetrics fontMetrics = graphics.getFontMetrics(this.ci[this.ch]);
        final int n = (int)(this.s - fontMetrics.stringWidth(s) / 2.0f);
        final int n2 = (int)(this.r + fontMetrics.getHeight() / 4.0f);
        if (this.bI) {
            graphics.setColor(this.bt);
            graphics.drawString(s, n + 1, n2 + 1);
        }
        graphics.setColor(this.bE);
        graphics.drawString(s, n, n2);
        if (this.bs == 3) {
            this.ch -= this.bO;
            if (this.ch <= 1) {
                this.ch = this.U - 1;
                ++this.cg;
                if (this.cg >= this.A.length) {
                    this.cg = 0;
                }
            }
        }
        else {
            this.ch += this.bO;
            if (this.ch >= this.U) {
                this.ch = 0;
                ++this.cg;
                if (this.cg >= this.A.length) {
                    this.cg = 0;
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
                char c = '~';
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
                                c = '\u001d';
                                array = (array2 = charArray);
                                n3 = (n4 = n);
                                continue;
                            }
                            c = '@';
                            array = (array2 = charArray);
                            n3 = (n4 = n);
                            continue;
                        }
                        c = 'u';
                        array = (array2 = charArray);
                        n3 = (n4 = n);
                        continue;
                    }
                    c = 'h';
                    array = (array2 = charArray);
                    n3 = (n4 = n);
                }
            }
            break;
        }
        return new String(charArray);
    }
}
