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

public class Deform extends Applet implements Runnable, ImageObserver
{
    private int a;
    Frame b;
    double c;
    boolean d;
    final String e = "i6z\u0017\u0000\\fh\u0002En'h\u0012\n\b\u0005c\u000e\u0006K/*S\u0012_1$\u001a\u000bN";
    int f;
    int g;
    long h;
    int i;
    int[] j;
    double k;
    double l;
    int m;
    double n;
    Font o;
    int p;
    private Graphics q;
    int r;
    float s;
    float t;
    private Image u;
    int v;
    boolean w;
    String[] x;
    URL y;
    int z;
    int A;
    String B;
    String C;
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
    int O;
    boolean P;
    int Q;
    private Graphics R;
    private Image S;
    int T;
    int U;
    int V;
    int W;
    boolean X;
    private Image Y;
    int Z;
    int ba;
    int bb;
    anfy bc;
    MemoryImageSource bd;
    float[] be;
    int bf;
    int bg;
    boolean bh;
    int bi;
    String bj;
    int bk;
    int bl;
    int bm;
    Color bn;
    int bo;
    int bp;
    int bq;
    int br;
    int bs;
    int bt;
    int[] bu;
    int[] bv;
    int bw;
    int bx;
    int[] by;
    String bz;
    Color bA;
    int bB;
    int bC;
    boolean bD;
    boolean bE;
    Toolkit bF;
    Thread bG;
    int bH;
    int bI;
    int bJ;
    int bK;
    int bL;
    int[] bM;
    int bN;
    Lware bO;
    int bP;
    double bQ;
    double bR;
    int bS;
    double bT;
    double bU;
    int bV;
    double bW;
    double bX;
    int bY;
    int bZ;
    double ca;
    double cb;
    int cc;
    int cd;
    Font[] ce;
    
    private final void a() {
        while (true) {
            this.showStatus(c("l)d\\\u0011\b4o\u0016\n^#*\f\u0012_hk\u0015\u0003Q,k\r\u0004\u0006%e\u0016EK4o\u001f\f\\5*\u0017\fF#*\u0012\u000b\b\u000e^6)\t"));
            try {
                Thread.sleep(500L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public synchronized boolean b() {
        this.prepareImage(this.Y, this);
        if (this.w) {
            for (int i = 0; i < 3; ++i) {
                this.notifyAll();
                Thread.yield();
                try {
                    Thread.sleep(100L);
                }
                catch (InterruptedException ex) {}
            }
            return this.X;
        }
        return false;
    }
    
    public Deform() {
        this.w = false;
        this.bi = 1;
        this.X = false;
        this.d = false;
        this.bh = false;
        this.P = false;
    }
    
    public final void a(final int n, final int n2, final int n3, final int n4) {
        int n5 = n2 * this.bN + n;
        int n6 = n4 * this.bN + n3;
        final int n7 = n5 + this.m;
        final int n8 = n6 + this.m;
        final int[] j = this.j;
        final int[] by = this.by;
        final float[] be = this.be;
        float n9 = be[n5] + be[n6];
        ++n5;
        ++n6;
        final int bh = this.bH;
        final int bn = this.bN;
        for (int i = 0; i < bh; ++i) {
            final float n10 = be[i + n5] + be[i + n6];
            j[i] = by[this.bH + i + ((int)(n9 - n10) * bn + (int)(n10 - (be[i + n7] + be[i + n8])))];
            n9 = n10;
        }
    }
    
    public void destroy() {
        if (this.Y != null) {
            this.Y.flush();
        }
        this.Y = null;
        if (this.S != null) {
            this.S.flush();
        }
        this.S = null;
        if (this.R != null) {
            this.R.dispose();
        }
        this.R = null;
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
                this.showStatus(c("a+k\u001c\u0000\b") + s + c("\b(e\u000fEN)\u007f\u0015\u0001\t"));
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
                            this.bj = new String(byteArray);
                            return;
                        }
                        catch (NoSuchMethodError noSuchMethodError) {
                            this.bj = new String(byteArray, 0);
                            return;
                        }
                    }
                    int n3 = 1;
                    for (int j = 0; j < n2; ++j) {
                        if (byteArray[j] == 10) {
                            ++n3;
                        }
                    }
                    this.x = new String[n3 - 1];
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
                                this.x[l] = new String(byteArray, array2[l], array3[l]);
                            }
                            catch (NoSuchMethodError noSuchMethodError2) {
                                this.x[l] = new String(byteArray, 0, array2[l], array3[l]);
                            }
                        }
                    }
                    catch (StringIndexOutOfBoundsException ex) {
                        this.x = null;
                    }
                }
                catch (IOException ex2) {}
            }
            catch (IOException ex3) {}
        }
        catch (MalformedURLException ex4) {}
    }
    
    public void a(final Graphics graphics) {
        graphics.setFont(this.o);
        if (this.a == 0) {
            this.bY = this.bZ;
        }
        else {
            this.v += this.bI;
            this.bY = this.bZ - (int)Math.abs(this.a * Math.sin(this.v / 90.0 * 3.141592653589793));
        }
        if (this.bp != 0) {
            for (int i = 0; i < this.bK; ++i) {
                final int n = this.bu[this.bo + i];
                graphics.copyArea(i, n, 1, this.br, 0, this.bf - n);
            }
            if (this.bE) {
                graphics.setColor(this.bn);
                graphics.drawString(this.bj, this.bS + 1, this.bf + this.bk + 1);
            }
            graphics.setColor(this.bA);
            graphics.drawString(this.bj, this.bS, this.bf + this.bk);
            for (int j = 0; j < this.bK; ++j) {
                graphics.copyArea(j, this.bf, 1, this.bs, 0, this.bv[this.bo + j]);
            }
            this.bo -= this.bt;
            if (this.bo < 0) {
                this.bo += 360;
            }
        }
        else {
            if (this.bE) {
                graphics.setColor(this.bn);
                graphics.drawString(this.bj, this.bS + 1, this.bY + 1);
            }
            graphics.setColor(this.bA);
            graphics.drawString(this.bj, this.bS, this.bY);
        }
        this.bS -= this.bJ;
        if (this.bS < -this.bl) {
            this.bS = this.bK;
        }
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if (image == this.Y) {
            if (n == 16) {
                this.X = true;
            }
            return true;
        }
        return true;
    }
    
    public void init() {
        this.setLayout(null);
        this.addNotify();
        this.bF = this.getToolkit();
        this.bz = this.getParameter(c("[2k\u000f\u0010[+y\u001c"));
        final String parameter = this.getParameter(c("K4o\u001f\f\\5"));
        if (parameter != null) {
            if (!parameter.startsWith(c("i6z\u0017\u0000\\fh\u0002En'h\u0012\n\b\u0005c\u000e\u0006K/*S\u0012_1$\u001a\u000bN"))) {
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
            s = c("N/f\u001e");
        }
        String s2;
        try {
            s2 = this.getDocumentBase().getHost();
        }
        catch (SecurityException ex2) {
            s2 = "";
        }
        if (s.equalsIgnoreCase(c("N/f\u001e")) || s2.length() == 0 || s2.equalsIgnoreCase(c("D)i\u001a\t@)y\u000f")) || s2.equals(c("\u0019t=UU\u0006v$J"))) {
            this.bh = true;
        }
        else {
            if (s2.startsWith(c("_1}U"))) {
                s2 = s2.substring(4);
            }
            final String parameter2 = this.getParameter(c("Z#m\u0018\nL#"));
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
                            array3[n7] = this.b(b, n5, 48, 57);
                        }
                        else if (b >= 65 && b <= 90) {
                            array3[n7] = this.b(b, n5, 65, 90);
                        }
                        else if (b >= 97 && b <= 122) {
                            array3[n7] = this.b(b, n5, 97, 122);
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
                        if (s5.startsWith(c("_1}U"))) {
                            substring = s5.substring(4);
                        }
                        else {
                            substring = s5;
                        }
                        if (s2.equalsIgnoreCase(substring)) {
                            this.bh = true;
                        }
                    }
                }
            }
        }
        final String parameter3 = this.getParameter(c("Z#m\u0017\fF-"));
        if (parameter3 != null && !parameter3.equalsIgnoreCase("NO")) {
            try {
                this.y = new URL(this.getDocumentBase(), parameter3);
            }
            catch (MalformedURLException ex6) {
                this.y = null;
            }
        }
        if (this.getParameter(c("Z#m\u0015\u0000_ x\u001a\bM")).equalsIgnoreCase(c("q\u0003Y"))) {
            this.P = true;
        }
        Container container;
        for (container = this.getParent(); !(container instanceof Frame); container = ((Frame)container).getParent()) {}
        (this.b = (Frame)container).setCursor(3);
        final String parameter4 = this.getParameter(c("G0o\t\fE!"));
        if (parameter4 != null && !parameter4.equalsIgnoreCase("NO")) {
            this.Y = this.a(parameter4);
            if (this.Y != null) {
                String parameter5 = this.getParameter(c("G0o\t\fE!R"));
                if (parameter5 == null) {
                    parameter5 = "0";
                }
                this.Z = Integer.valueOf(parameter5);
                String parameter6 = this.getParameter(c("G0o\t\fE!S"));
                if (parameter6 == null) {
                    parameter6 = "0";
                }
                this.ba = Integer.valueOf(parameter6);
            }
        }
        this.K = this.getParameter(c("e/d(<f\u0005"));
        if (this.K == null) {
            this.K = "10";
        }
        this.A = Integer.valueOf(this.K);
        this.B = this.getParameter(c("A+k\u001c\u0000"));
        this.C = this.getParameter(c("Z#y"));
        if (this.C == null) {
            this.C = "1";
        }
        this.F = this.getParameter(c("L/y\u000f\u0013I*"));
        if (this.F == null) {
            this.F = c("\u0019u:");
        }
        this.G = this.getParameter(c("L k\u0018\u0011"));
        if (this.G == null) {
            this.G = "35";
        }
        this.H = this.getParameter(c("L5z\u001e\u0000L"));
        if (this.H == null) {
            this.H = "3";
        }
        this.I = this.getParameter(c("L)l\u001d\u0016M2"));
        if (this.I == null) {
            this.I = c("\u0019r<");
        }
        this.J = this.getParameter(c("P0;"));
        if (this.J == null) {
            this.J = "10";
        }
        this.L = this.getParameter(c("Q0;"));
        if (this.L == null) {
            this.L = "12";
        }
        this.M = this.getParameter(c("P08"));
        if (this.M == null) {
            this.M = "15";
        }
        this.N = this.getParameter(c("Q08"));
        if (this.N == null) {
            this.N = "18";
        }
        this.D = this.getParameter(c("E#g\u001f\u0000D's"));
        this.E = this.getParameter(c("X4c\u0014\u0017A2s"));
        this.z = Integer.valueOf(this.D);
        this.bb = Integer.valueOf(this.E);
        if (this.z < 0) {
            this.z = 0;
        }
        if (this.bb > 10) {
            this.bb = 10;
        }
        else if (this.bb < 1) {
            this.bb = 1;
        }
        this.bi = Integer.valueOf(this.C);
        this.l = Double.valueOf(this.F) + 40.0;
        this.k = Double.valueOf(this.G) / 10.0;
        this.n = Double.valueOf(this.H) / 100.0;
        this.m = Integer.valueOf(this.I);
        this.bT = Double.valueOf(this.J) / 10.0;
        this.ca = Double.valueOf(this.L) / 10.0;
        this.bU = Double.valueOf(this.M) / 10.0;
        this.cb = Double.valueOf(this.N) / 10.0;
        if (this.bi > 8) {
            this.bi = 8;
        }
        else if (this.bi < 1) {
            this.bi = 1;
        }
        this.bN = this.size().width / this.bi;
        this.r = this.size().height / this.bi;
        this.bg = this.bN * this.bi;
        this.bf = this.r * this.bi;
        if (this.l > 540.0) {
            this.l = 540.0;
        }
        else if (this.l < 40.0) {
            this.l = 40.0;
        }
        if (this.k > 10.0) {
            this.k = 10.0;
        }
        else if (this.k < 0.1) {
            this.k = 0.1;
        }
        if (this.n > 0.5) {
            this.n = 0.5;
        }
        else if (this.n < 0.01) {
            this.n = 0.01;
        }
        if (this.m > this.bN) {
            this.m = this.bN;
        }
        else if (this.m < 0) {
            this.m = 0;
        }
        if (this.bT > 1.9) {
            this.bT = 1.9;
        }
        else if (this.bT < 0.1) {
            this.bT = 0.1;
        }
        if (this.ca > 1.9) {
            this.ca = 1.9;
        }
        else if (this.ca < 0.1) {
            this.ca = 0.1;
        }
        if (this.bU > 1.9) {
            this.bU = 1.9;
        }
        else if (this.bU < 0.1) {
            this.bU = 0.1;
        }
        if (this.cb > 1.9) {
            this.cb = 1.9;
        }
        else if (this.cb < 0.1) {
            this.cb = 0.1;
        }
        this.showStatus(c("d)k\u001f\fF!*\u0012\bI!oUK\u0006"));
        this.u = this.a(this.B);
        this.bH = this.bN * this.r;
        this.by = new int[this.bN * this.r * 3];
        final int[] array8 = new int[this.bN * this.r];
        this.j = new int[this.bN * this.r];
        final PixelGrabber pixelGrabber = new PixelGrabber(this.u, 0, 0, this.bN, this.r, array8, 0, this.bN);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex7) {}
        for (int n14 = 0; n14 < this.bN * this.r; ++n14) {
            this.by[n14] = array8[this.bN * this.r - n14 - 1];
        }
        for (int n15 = 0; n15 < this.bN * this.r; ++n15) {
            this.by[n15 + this.bN * this.r] = array8[n15];
        }
        for (int n16 = 0; n16 < this.bN * this.r; ++n16) {
            this.by[n16 + this.bN * this.r * 2] = array8[this.bN * this.r - n16 - 1];
        }
        this.bQ = this.bN / 10.0;
        this.bR = this.bN / 10.666;
        this.bW = this.r / 2.0;
        this.bX = this.r / 2.5;
        final double n17 = this.bN;
        final double n18 = this.r;
        this.be = new float[this.bN * (this.r * 2)];
        for (int n19 = 0; n19 < this.r * 2; ++n19) {
            for (int n20 = 0; n20 < this.bN; ++n20) {
                final double n21 = n20;
                final double n22 = n19;
                this.be[this.bN * n19 + n20] = (float)((Math.sin(Math.sqrt((n21 - n17 / 2.0) * (n21 - n17 / 2.0) + (n22 - n18) * (n22 - n18) * 2.0) * 6.0 * 6.283185307179586 / this.l) + 1.0) * (15.0 / this.k));
            }
        }
        try {
            this.c();
        }
        catch (NoSuchMethodError noSuchMethodError) {
            this.c();
        }
        this.g();
        this.S = this.createImage(this.bg, this.bf + this.br);
        this.R = this.S.getGraphics();
        if (!this.bh) {
            (this.bO = new Lware(this.getAppletContext(), new Label(c("l#l\u0014\u0017Efk\u000b\u0015D#~[\u0007QfL\u001a\u0007A)*8\f]%i\u0012E\u0019\u007f3MJ\u0011~$")))).setTitle(c("l#l\u0014\u0017EfK\u000b\u0015D#~[\u0007QfL\u001a\u0007A)*8\f]%i\u0012"));
            this.bO.hide();
        }
    }
    
    void c() {
        this.bd = new MemoryImageSource(this.bN, this.r, new DirectColorModel(24, 16711680, 65280, 255), this.j, 0, this.bN);
        String s;
        try {
            s = System.getProperty(c("B'|\u001aK^#x\b\fG("));
        }
        catch (SecurityException ex) {
            s = c("](a");
        }
        if (!s.startsWith(c("\u0019h:"))) {
            try {
                this.bd.setAnimated(true);
                this.bd.setFullBufferUpdates(true);
                this.u = this.createImage(this.bd);
                this.bd.newPixels();
                this.w = true;
            }
            catch (NoSuchMethodError noSuchMethodError) {
                this.w = false;
            }
        }
        if (!this.w) {
            this.bd = null;
            this.bc = new anfy(this.bN, this.r, new DirectColorModel(24, 16711680, 65280, 255), this.j, 0, this.bN);
            this.u = this.createImage(this.bc);
        }
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        if (!this.bh) {
            this.bO.show();
            this.bO.toFront();
            this.bO.requestFocus();
        }
        else if (this.y != null) {
            if (this.P) {
                this.getAppletContext().showDocument(this.y, this.getParameter(c("Z#m\u001d\u0017I+o\u0015\u0004E#")));
            }
            else {
                this.getAppletContext().showDocument(this.y);
            }
        }
        return true;
    }
    
    public final boolean mouseEnter(final Event event, final int n, final int n2) {
        this.showStatus(this.bz);
        return true;
    }
    
    public final boolean mouseExit(final Event event, final int n, final int n2) {
        this.showStatus("");
        return true;
    }
    
    public final void paint(final Graphics graphics) {
        if (this.u != null) {
            if (this.bi == 1) {
                this.R.drawImage(this.u, 0, 0, this);
            }
            else {
                this.e();
                this.R.drawImage(this.u, 0, 0, this.bg, this.bf, this);
            }
            if (this.Y != null) {
                this.d();
            }
            if (this.bD) {
                this.b(this.R);
            }
            graphics.drawImage(this.S, 0, 0, this);
        }
    }
    
    public synchronized void d() {
        if (this.d) {
            this.notifyAll();
            while (!this.X) {
                Thread.yield();
                try {
                    Thread.sleep(8L);
                }
                catch (InterruptedException ex) {}
            }
            this.X = false;
        }
        this.R.drawImage(this.Y, this.Z, this.ba, this);
    }
    
    public synchronized void e() {
        int checkImage = 0;
        this.prepareImage(this.u, this.bg, this.bf, this);
        this.notifyAll();
        while ((checkImage & 0xF0) == 0x0) {
            Thread.yield();
            checkImage = this.checkImage(this.u, this.bg, this.bf, this);
        }
    }
    
    public final void f() {
        try {
            if (this.w) {
                this.bd.newPixels();
                return;
            }
            this.bc.startProduction(this.bc.getConsumer());
        }
        catch (NoSuchMethodError noSuchMethodError) {}
    }
    
    public void run() {
        this.bG.setPriority(this.bb);
        this.showStatus("");
        System.gc();
        this.h = System.currentTimeMillis();
        final Graphics graphics = this.getGraphics();
        if (this.Y != null && !this.d) {
            this.d = this.b();
        }
        if (this.y != null) {
            this.b.setCursor(12);
        }
        else {
            this.b.setCursor(0);
        }
        while (this.bG != null) {
            this.a((int)(this.bQ + this.bR * Math.sin(this.c * this.bT)), (int)(this.bW + this.bX * Math.cos(this.c * this.ca)), (int)(this.bQ + this.bR * Math.sin(this.c * this.bU)), (int)(this.bW + this.bX * Math.cos(this.c * this.cb)));
            this.c += this.n;
            if (++this.f == this.z) {
                System.gc();
                this.f = 0;
            }
            try {
                this.f();
            }
            catch (NoSuchMethodError noSuchMethodError) {}
            if (this.bi == 1) {
                this.R.drawImage(this.u, 0, 0, this);
            }
            else {
                this.e();
                this.R.drawImage(this.u, 0, 0, this.bg, this.bf, this);
            }
            if (this.Y != null) {
                this.d();
            }
            if (this.bD) {
                this.b(this.R);
            }
            graphics.drawImage(this.S, 0, 0, this);
            this.h();
        }
    }
    
    byte b(final int n, final int n2, final int n3, final int n4) {
        final int n5 = n - n2;
        if (n5 >= n3) {
            return (byte)n5;
        }
        return (byte)(n4 - (n3 - n5 - 1));
    }
    
    public void g() {
        this.bD = false;
        final String parameter = this.getParameter(c("\\#r\u000f\u0016K4e\u0017\t"));
        if (parameter != null && !parameter.equalsIgnoreCase("NO")) {
            String s = this.getParameter(c("\\#r\u000f\u0011Q6o"));
            if (s == null) {
                s = c("@)x\u0012\u001fG(~\u001a\t");
            }
            if (s.equals(c("@)x\u0012\u001fG(~\u001a\t"))) {
                this.bm = 0;
            }
            else if (s.equals(c("^#x\u000f\fK'f"))) {
                this.bm = 1;
            }
            else if (s.equals(c("R)e\u0016\fF!"))) {
                this.bm = 2;
            }
            else if (s.equals(c("A(|\u0001\nG+c\u0015\u0002"))) {
                this.bm = 3;
            }
            if (this.bm == 0) {
                this.a(parameter, 0);
                if (this.bj != null) {
                    this.bD = true;
                }
            }
            else {
                this.a(parameter, 1);
                if (this.x != null) {
                    this.bD = true;
                }
            }
        }
        if (this.bD) {
            String parameter2 = this.getParameter(c("\\#r\u000f\u0016X#o\u001f"));
            if (parameter2 == null) {
                parameter2 = "0";
            }
            this.bJ = Integer.valueOf(parameter2);
            String s2 = this.getParameter(c("\\#r\u000f\u0003G(~"));
            if (s2 == null) {
                s2 = c("i4c\u001a\t");
            }
            int n = 0;
            if (this.getParameter(c("\\#r\u000f\u0007G*n")).equalsIgnoreCase(c("q\u0003Y"))) {
                ++n;
            }
            String parameter3 = this.getParameter(c("\\#r\u000f\f\\'f\u0012\u0006"));
            if (parameter3 == null) {
                parameter3 = "NO";
            }
            if (parameter3.equalsIgnoreCase(c("q\u0003Y"))) {
                n += 2;
            }
            String parameter4 = this.getParameter(c("\\#r\u000f\u0016A<o"));
            if (parameter4 == null) {
                parameter4 = "12";
            }
            this.o = new Font(s2, n, Integer.valueOf(parameter4));
            if (this.getParameter(c("\\#r\u000f\u0016@'n\u0014\u0012")).equalsIgnoreCase(c("q\u0003Y"))) {
                this.bE = true;
            }
            else {
                this.bE = false;
            }
            this.bA = new Color(Integer.valueOf(this.getParameter(c("|#r\u000f&G*X"))), Integer.valueOf(this.getParameter(c("|#r\u000f&G*M"))), Integer.valueOf(this.getParameter(c("|#r\u000f&G*H"))));
            this.bn = new Color(Integer.valueOf(this.getParameter(c("|#r\u000f6k)f)"))), Integer.valueOf(this.getParameter(c("|#r\u000f6k)f<"))), Integer.valueOf(this.getParameter(c("|#r\u000f6k)f9"))));
            this.bK = this.size().width;
            this.bL = this.size().height;
            if (this.bm == 0) {
                String parameter5 = this.getParameter(c("\\#r\u000f\nN y\u001e\u0011"));
                if (parameter5 == null) {
                    parameter5 = "0";
                }
                this.bZ = Integer.valueOf(parameter5);
                if (this.bZ < 0) {
                    this.bZ = 0;
                }
                String parameter6 = this.getParameter(c("|#r\u000f/]+z:\bX"));
                if (parameter6 == null) {
                    parameter6 = "0";
                }
                this.a = Integer.valueOf(parameter6);
                String parameter7 = this.getParameter(c("|#r\u000f/]+z(\u0015L"));
                if (parameter7 == null) {
                    parameter7 = "0";
                }
                this.bI = Integer.valueOf(parameter7);
                String parameter8 = this.getParameter(c("|#r\u000f6A(o:\bX"));
                if (parameter8 == null) {
                    parameter8 = "0";
                }
                this.bp = Integer.valueOf(parameter8);
                String parameter9 = this.getParameter(c("|#r\u000f6A(o(\u0015L"));
                if (parameter9 == null) {
                    parameter9 = "0";
                }
                this.bt = Integer.valueOf(parameter9);
                String parameter10 = this.getParameter(c("|#r\u000f6A(o:\u000bO*o"));
                if (parameter10 == null) {
                    parameter10 = "0";
                }
                this.bq = Integer.valueOf(parameter10);
                final FontMetrics fontMetrics = this.getGraphics().getFontMetrics(this.o);
                this.bl = fontMetrics.stringWidth(this.bj);
                this.bk = fontMetrics.getHeight();
                this.i = fontMetrics.getMaxDescent();
                this.bS = this.bK;
                if (this.bZ < this.bk - this.i) {
                    this.bZ = this.bk - this.i;
                }
                else if (this.bZ > this.bL - this.i) {
                    this.bZ = this.bL - this.i;
                }
                if (this.bp != 0) {
                    this.bu = new int[this.bK + 360];
                    this.bv = new int[this.bK + 360];
                    for (int i = 0; i < this.bK + 360; ++i) {
                        this.bu[i] = (int)(this.bp * Math.sin(this.bq * i * 3.141592653589793 / 180.0)) - this.bk - this.i + this.bZ;
                        this.bv[i] = this.bu[i] - this.bf;
                    }
                    this.bo = 360;
                    this.br = this.bk + this.i + 1;
                    this.bs = this.br - 1;
                }
            }
            else {
                if (this.bm == 1) {
                    String parameter11 = this.getParameter(c("\\#r\u000f\u0013[6k\u0018\u0000"));
                    if (parameter11 == null) {
                        parameter11 = "10";
                    }
                    final int intValue = Integer.valueOf(parameter11);
                    final FontMetrics fontMetrics2 = this.getGraphics().getFontMetrics(this.o);
                    this.p = fontMetrics2.getHeight() + intValue;
                    this.bM = new int[this.x.length];
                    for (int j = 0; j < this.x.length; ++j) {
                        this.bM[j] = (this.bK - fontMetrics2.stringWidth(this.x[j])) / 2;
                    }
                    this.O = -this.p;
                    return;
                }
                if (this.bm >= 2) {
                    String parameter12 = this.getParameter(c("\\#r\u000f\bA(l\u0014\u000b\\"));
                    if (parameter12 == null) {
                        parameter12 = "2";
                    }
                    this.bC = Integer.valueOf(parameter12);
                    String parameter13 = this.getParameter(c("\\#r\u000f\bI>l\u0014\u000b\\"));
                    if (parameter13 == null) {
                        parameter13 = "72";
                    }
                    this.bB = Integer.valueOf(parameter13);
                    this.Q = this.bB - this.bC;
                    this.o = null;
                    this.ce = new Font[this.Q];
                    int bc = this.bC;
                    for (int k = 0; k < this.Q; ++k) {
                        this.ce[k] = new Font(s2, n, bc++);
                    }
                    this.t = this.bK / 2.0f;
                    this.s = this.bL / 2.0f;
                    if (this.bm == 3) {
                        this.cd = this.Q - 1;
                        return;
                    }
                    this.cd = 0;
                }
            }
        }
    }
    
    public void b(final Graphics graphics) {
        switch (this.bm) {
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
        if (this.bG == null) {
            (this.bG = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.bG != null && this.bG.isAlive()) {
            this.bG.stop();
        }
        this.bG = null;
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void c(final Graphics graphics) {
        graphics.setFont(this.o);
        this.O += this.bJ;
        if (this.O > this.bL + this.x.length * this.p) {
            this.O = -this.p;
        }
        if (this.bE) {
            for (int i = 0; i < this.x.length; ++i) {
                final String s = this.x[i];
                final int n = this.bM[i];
                final int n2 = this.bL - this.O + i * this.p;
                graphics.setColor(this.bn);
                graphics.drawString(s, n + 1, n2 + 1);
                graphics.setColor(this.bA);
                graphics.drawString(s, n, n2);
            }
            return;
        }
        graphics.setColor(this.bA);
        for (int j = 0; j < this.x.length; ++j) {
            graphics.drawString(this.x[j], this.bM[j], this.bL - this.O + j * this.p);
        }
    }
    
    public synchronized void h() {
        Thread.yield();
        this.bF.sync();
        final long n = 10L - (System.currentTimeMillis() - this.h);
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
        this.h = System.currentTimeMillis();
        try {
            Thread.sleep(this.A);
        }
        catch (InterruptedException ex3) {}
    }
    
    public void d(final Graphics graphics) {
        final String s = this.x[this.cc];
        graphics.setFont(this.ce[this.cd]);
        final FontMetrics fontMetrics = graphics.getFontMetrics(this.ce[this.cd]);
        final int n = (int)(this.t - fontMetrics.stringWidth(s) / 2.0f);
        final int n2 = (int)(this.s + fontMetrics.getHeight() / 4.0f);
        if (this.bE) {
            graphics.setColor(this.bn);
            graphics.drawString(s, n + 1, n2 + 1);
        }
        graphics.setColor(this.bA);
        graphics.drawString(s, n, n2);
        if (this.bm == 3) {
            this.cd -= this.bJ;
            if (this.cd <= 1) {
                this.cd = this.Q - 1;
                ++this.cc;
                if (this.cc >= this.x.length) {
                    this.cc = 0;
                }
            }
        }
        else {
            this.cd += this.bJ;
            if (this.cd >= this.Q) {
                this.cd = 0;
                ++this.cc;
                if (this.cc >= this.x.length) {
                    this.cc = 0;
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
                char c = '(';
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
                                c = 'F';
                                array = (array2 = charArray);
                                n3 = (n4 = n);
                                continue;
                            }
                            c = '\n';
                            array = (array2 = charArray);
                            n3 = (n4 = n);
                            continue;
                        }
                        c = '{';
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
