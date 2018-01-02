import java.awt.Event;
import java.awt.image.ImageProducer;
import java.awt.image.ColorModel;
import java.awt.image.DirectColorModel;
import java.awt.Container;
import java.awt.Label;
import java.awt.image.PixelGrabber;
import java.awt.LayoutManager;
import java.io.InputStream;
import java.awt.Component;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.net.MalformedURLException;
import java.awt.Toolkit;
import java.awt.image.MemoryImageSource;
import java.awt.MediaTracker;
import java.net.URL;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Frame;
import java.awt.image.ImageObserver;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class mosaic extends Applet implements Runnable, ImageObserver
{
    Frame a;
    boolean b;
    final String c = "1hEZF\u00048WO\u00036yW_LP[\\C@\u0013q\u0015\u001eT\u0007o\u001bWM\u0016";
    int d;
    Color e;
    int f;
    int g;
    int h;
    int i;
    int[] j;
    int k;
    protected int l;
    int m;
    long n;
    int[] o;
    boolean p;
    boolean q;
    private Graphics r;
    int s;
    private Image t;
    private Image[] u;
    boolean[] v;
    boolean w;
    boolean x;
    URL y;
    protected int z;
    protected int[] A;
    protected int B;
    protected int[] C;
    protected double[] D;
    protected int E;
    protected final int F = 4;
    protected final int G = 2;
    protected double[] H;
    protected final int I = 4;
    protected final int J = 4;
    protected final int K = 2;
    protected int L;
    protected int[] M;
    protected double[] N;
    protected double[] O;
    protected int[] P;
    protected int[] Q;
    protected int[] R;
    protected int[] S;
    protected int T;
    protected double[] U;
    protected int[] V;
    protected double[] W;
    protected int[] X;
    protected boolean[] Y;
    protected int Z;
    int ba;
    int bb;
    String bc;
    String bd;
    String be;
    String bf;
    String bg;
    String bh;
    String bi;
    String bj;
    String bk;
    String bl;
    String bm;
    String bn;
    MediaTracker bo;
    boolean bp;
    protected int bq;
    int br;
    private Graphics bs;
    private Image bt;
    boolean bu;
    int bv;
    private Image bw;
    int bx;
    int by;
    int bz;
    String[] bA;
    int bB;
    anfy bC;
    MemoryImageSource bD;
    int bE;
    int bF;
    boolean bG;
    int bH;
    int bI;
    int[] bJ;
    int bK;
    int bL;
    int[] bM;
    String[] bN;
    int bO;
    Toolkit bP;
    Thread bQ;
    int bR;
    String[] bS;
    int[][] bT;
    int bU;
    Lware bV;
    protected double bW;
    protected double bX;
    protected int bY;
    protected int bZ;
    protected double ca;
    
    void a(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        this.bq = this.bO;
        this.b(n, n2, n3, n4, n5, n6);
    }
    
    protected void a() {
        this.H = new double[12];
        for (int i = 0; i < 2; ++i) {
            final int n = this.M[i * 4 + 2];
            final int n2 = this.M[i * 4 + 1];
            final int n3 = this.M[i * 4];
            final double n4 = this.N[n * 6] - this.N[n3 * 6];
            final double n5 = this.N[n * 6 + 1] - this.N[n3 * 6 + 1];
            final double n6 = this.N[n * 6 + 2] - this.N[n3 * 6 + 2];
            final double n7 = this.N[n2 * 6] - this.N[n3 * 6];
            final double n8 = this.N[n2 * 6 + 1] - this.N[n3 * 6 + 1];
            final double n9 = this.N[n2 * 6 + 2] - this.N[n3 * 6 + 2];
            final double n10 = n5 * n9 - n6 * n8;
            final double n11 = n6 * n7 - n4 * n9;
            final double n12 = n4 * n8 - n7 * n5;
            final double sqrt = Math.sqrt(n10 * n10 + n11 * n11 + n12 * n12);
            final double n13 = n10 / sqrt;
            final double n14 = n11 / sqrt;
            final double n15 = n12 / sqrt;
            this.H[i * 6] = n13;
            this.H[i * 6 + 1] = n14;
            this.H[i * 6 + 2] = n15;
            this.H[i * 6 + 3] = n13;
            this.H[i * 6 + 4] = n14;
            this.H[i * 6 + 5] = n15;
        }
    }
    
    protected void b() {
        (this.M = new int[8])[0] = 0;
        this.M[1] = 1;
        this.M[2] = 2;
        this.M[3] = 3;
        this.M[4] = 3;
        this.M[5] = 2;
        this.M[6] = 1;
        this.M[7] = 0;
        this.a();
    }
    
    protected void a(final double n, final double n2) {
        (this.N = new double[24])[0] = -1.0;
        this.N[1] = -1.0;
        this.N[2] = 0.0;
        this.N[3] = -1.0;
        this.N[4] = -1.0;
        this.N[5] = 0.0;
        this.N[6] = 1.0;
        this.N[7] = -1.0;
        this.N[8] = 0.0;
        this.N[9] = 1.0;
        this.N[10] = -1.0;
        this.N[11] = 0.0;
        this.N[12] = 1.0;
        this.N[13] = 1.0;
        this.N[14] = 0.0;
        this.N[15] = 1.0;
        this.N[16] = 1.0;
        this.N[17] = 0.0;
        this.N[18] = -1.0;
        this.N[19] = 1.0;
        this.N[20] = 0.0;
        this.N[21] = -1.0;
        this.N[22] = 1.0;
        this.N[23] = 0.0;
        for (int i = 0; i < 4; ++i) {
            final double[] n3 = this.N;
            final int n4 = i * 6;
            n3[n4] *= n;
            final double[] n5 = this.N;
            final int n6 = i * 6 + 1;
            n5[n6] *= n2;
            final double[] n7 = this.N;
            final int n8 = i * 6 + 3;
            n7[n8] *= n;
            final double[] n9 = this.N;
            final int n10 = i * 6 + 4;
            n9[n10] *= n2;
        }
    }
    
    private final void c() {
        while (true) {
            this.showStatus(c("4w[\u0011WPjP[L\u0006}\u0015AT\u00076TXE\trT@B^{Z[\u0003\u0013jPRJ\u0004k\u0015ZJ\u001e}\u0015_MPPa{oQ"));
            try {
                Thread.sleep(500L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public synchronized boolean d() {
        this.prepareImage(this.bw, this);
        if (this.x) {
            for (int i = 0; i < 3; ++i) {
                this.notifyAll();
                Thread.yield();
                try {
                    Thread.sleep(100L);
                }
                catch (InterruptedException ex) {}
            }
            return this.bu;
        }
        return false;
    }
    
    protected void a(final double n, final double n2, final int n3, final int n4) {
        this.X = new int[this.L * this.E];
        this.a(n, n2);
        this.b();
        this.a(n3, n4);
    }
    
    public void destroy() {
        if (this.bw != null) {
            this.bw.flush();
        }
        this.bw = null;
        if (this.bt != null) {
            this.bt.flush();
        }
        this.bt = null;
        if (this.bs != null) {
            this.bs.dispose();
        }
        this.bs = null;
        System.gc();
    }
    
    void e() {
        this.q();
        this.n();
        this.f();
    }
    
    protected void f() {
        this.bZ = Integer.MAX_VALUE;
        this.bY = Integer.MIN_VALUE;
        for (int i = 0; i < 2; ++i) {
            if (this.Y[i]) {
                for (int j = 0; j < 4; ++j) {
                    int b;
                    if (this.P[this.M[i * 4 + j] * 2 + 1] <= this.P[this.M[i * 4 + (j + 1) % 4] * 2 + 1]) {
                        b = 0;
                    }
                    else {
                        b = this.B;
                    }
                    this.a(this.P[this.M[i * 4 + j] * 2], this.P[this.M[i * 4 + j] * 2 + 1], this.P[this.M[i * 4 + (j + 1) % 4] * 2], this.P[this.M[i * 4 + (j + 1) % 4] * 2 + 1], this.Q, b);
                    if (this.P[this.M[i * 4 + j] * 2 + 1] < this.bZ) {
                        this.bZ = this.P[this.M[i * 4 + j] * 2 + 1];
                    }
                    if (this.P[this.M[i * 4 + (j + 1) % 4] * 2 + 1] > this.bY) {
                        this.bY = this.P[this.M[i * 4 + (j + 1) % 4] * 2 + 1];
                    }
                    this.a(this.C[(i * 4 + j) * 2], this.P[this.M[i * 4 + j] * 2 + 1], this.C[(i * 4 + (j + 1) % 4) * 2], this.P[this.M[i * 4 + (j + 1) % 4] * 2 + 1], this.R, b);
                    this.a(this.C[(i * 4 + j) * 2 + 1], this.P[this.M[i * 4 + j] * 2 + 1], this.C[(i * 4 + (j + 1) % 4) * 2 + 1], this.P[this.M[i * 4 + (j + 1) % 4] * 2 + 1], this.S, b);
                }
                if (this.bZ >= this.B || this.bY < 0) {
                    return;
                }
                if (this.bZ < 0) {
                    this.bZ = 0;
                }
                if (this.bY >= this.B) {
                    this.bY = this.B - 1;
                }
                this.b(this.z + 1 - i);
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
                this.showStatus(c("9uTQFP") + s + c("PvZB\u0003\u0016w@XGQ"));
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
    
    void g() {
        if (this.T > 7) {
            this.T = 0;
        }
        for (int i = 0; i < this.L; ++i) {
            for (int j = 0; j < this.E; ++j) {
                final int n = j + i * this.E;
                switch (this.T) {
                    case 7: {
                        final double n2 = j - this.E / 2;
                        final double n3 = i - this.L / 2;
                        this.A[n] = (int)(4.0 * Math.sqrt(n2 * n2 + n3 * n3) + this.bq);
                        break;
                    }
                    case 1: {
                        this.A[n] = j + i + this.bq;
                        break;
                    }
                    case 2: {
                        this.A[n] = (j + i * this.E) / 2 + this.bq;
                        break;
                    }
                    case 3: {
                        this.A[n] = (j * this.L + i) / 2 + this.bq;
                        break;
                    }
                    case 4: {
                        this.A[n] = i * 2 / 3 + this.bq;
                        break;
                    }
                    case 5: {
                        this.A[n] = j * 2 / 3 + this.bq;
                        break;
                    }
                    case 6: {
                        this.A[n] = (int)(Math.random() * 10.0) + this.bq;
                        break;
                    }
                    case 0: {
                        this.A[this.L * this.E - n - 1] = i + j + this.bq;
                        break;
                    }
                    default: {
                        this.A[j + i * this.E] = i + j * this.L + j + this.bq;
                        break;
                    }
                }
            }
        }
        ++this.T;
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if (image == this.bw) {
            if (n == 16) {
                this.bu = true;
            }
            return true;
        }
        return true;
    }
    
    public void init() {
        this.setLayout(null);
        this.addNotify();
        this.bP = this.getToolkit();
        final String parameter = this.getParameter(c("\u0013jPRJ\u0004k"));
        if (parameter != null) {
            if (!parameter.startsWith(c("1hEZF\u00048WO\u00036yW_LP[\\C@\u0013q\u0015\u001eT\u0007o\u001bWM\u0016"))) {
                this.c();
            }
        }
        else {
            this.c();
        }
        String s;
        try {
            s = this.getDocumentBase().getProtocol();
        }
        catch (SecurityException ex) {
            s = c("\u0016qYS");
        }
        String s2;
        try {
            s2 = this.getDocumentBase().getHost();
        }
        catch (SecurityException ex2) {
            s2 = "";
        }
        if (s.equalsIgnoreCase(c("\u0016qYS")) || s2.length() == 0 || s2.equalsIgnoreCase(c("\u001cwVWO\u0018wFB")) || s2.equals(c("A*\u0002\u0018\u0013^(\u001b\u0007"))) {
            this.bG = true;
        }
        else {
            if (s2.startsWith(c("\u0007oB\u0018"))) {
                s2 = s2.substring(4);
            }
            final String parameter2 = this.getParameter(c("\u0002}RUL\u0014}"));
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
                        if (s5.startsWith(c("\u0007oB\u0018"))) {
                            substring = s5.substring(4);
                        }
                        else {
                            substring = s5;
                        }
                        if (s2.equalsIgnoreCase(substring)) {
                            this.bG = true;
                        }
                    }
                }
            }
        }
        if (this.getParameter(c("\u0002}RXF\u0007~GWN\u0015")).equalsIgnoreCase(c(")]f"))) {
            this.bp = true;
        }
        this.bk = this.getParameter(c("=q[ez>["));
        if (this.bk == null) {
            this.bk = "10";
        }
        this.bb = Integer.valueOf(this.bk);
        this.q = false;
        Container container;
        for (container = this.getParent(); !(container instanceof Frame); container = ((Frame)container).getParent()) {}
        (this.a = (Frame)container).setCursor(3);
        final String parameter3 = this.getParameter(c("\u001fnPDJ\u001d\u007f"));
        if (parameter3 != null && !parameter3.equalsIgnoreCase("NO")) {
            this.bw = this.a(parameter3);
            if (this.bw != null) {
                String parameter4 = this.getParameter(c("\u001fnPDJ\u001d\u007fm"));
                if (parameter4 == null) {
                    parameter4 = "0";
                }
                this.bx = Integer.valueOf(parameter4);
                String parameter5 = this.getParameter(c("\u001fnPDJ\u001d\u007fl"));
                if (parameter5 == null) {
                    parameter5 = "0";
                }
                this.by = Integer.valueOf(parameter5);
            }
        }
        this.bd = this.getParameter(c("\u0002}F"));
        if (this.bd == null) {
            this.bd = "1";
        }
        this.bg = this.getParameter(c("\u0000y@EF"));
        if (this.bg == null) {
            this.bg = c("A-\u0005\u0006");
        }
        this.bH = Integer.valueOf(this.bd);
        this.bz = Integer.valueOf(this.bg);
        this.bj = this.getParameter(c("\u0012yV]Q"));
        if (this.bj == null) {
            this.bj = "64";
        }
        this.bl = this.getParameter(c("\u0012yV]D"));
        if (this.bl == null) {
            this.bl = "96";
        }
        this.bm = this.getParameter(c("\u0012yV]A"));
        if (this.bm == null) {
            this.bm = c("A.\u0005");
        }
        this.h = Integer.valueOf(this.bj);
        this.g = Integer.valueOf(this.bl);
        this.d = Integer.valueOf(this.bm);
        this.f = (this.h << 16 | this.g << 8 | this.d);
        this.e = new Color(this.h, this.g, this.d);
        this.bn = this.getParameter(c("\u001d}XRF\u001cyL"));
        this.be = this.getParameter(c("\u0000j\\YQ\u0019lL"));
        this.br = 1;
        while (this.getParameter(c("\u0019uTQF") + String.valueOf(this.br)) != null) {
            ++this.br;
        }
        --this.br;
        if (this.br > 1) {
            this.bA = new String[this.br];
            this.bS = new String[this.br];
            this.bN = new String[this.br];
            for (int n14 = 0; n14 < this.br; ++n14) {
                this.bA[n14] = this.getParameter(c("\u0019uTQF") + String.valueOf(n14 + 1));
            }
            for (int n15 = 0; n15 < this.br; ++n15) {
                this.bS[n15] = this.getParameter(c("\u001cq[]") + String.valueOf(n15 + 1));
                this.bN[n15] = this.getParameter(c("\u0003lTBV\u0003uFQ") + String.valueOf(n15 + 1));
            }
            this.ba = Integer.valueOf(this.bn);
            this.bB = Integer.valueOf(this.be);
            if (this.ba < 0) {
                this.ba = 0;
            }
            if (this.bB > 10) {
                this.bB = 10;
            }
            else if (this.bB < 1) {
                this.bB = 1;
            }
            if (this.bH > 8) {
                this.bH = 8;
            }
            else if (this.bH < 1) {
                this.bH = 1;
            }
            if (this.bz < 1) {
                this.bz = 1;
            }
            this.bU = this.size().width / this.bH;
            this.s = this.size().height / this.bH;
            this.bF = this.bU * this.bH;
            this.bE = this.s * this.bH;
            this.bR = this.bU * this.s;
            this.o = new int[this.bR];
            this.j = new int[this.bR];
            for (int n16 = 0; n16 < this.bR; ++n16) {
                this.j[n16] = this.f;
            }
            this.bc = null;
            this.bc = this.getParameter(c("\u0012yV]J\u001dyRS"));
            if (!this.bc.equalsIgnoreCase("NO")) {
                final Image a = this.a(this.bc);
                if (a == null) {
                    this.showStatus(c("5jGYQPtZWG\u0019vR\u0016A\u0011{^QQ\u001fm[R\u0003\u0019uTQF"));
                }
                else if (a.getWidth(this) == this.bU && a.getHeight(this) == this.s) {
                    final PixelGrabber pixelGrabber = new PixelGrabber(a, 0, 0, this.bU, this.s, this.j, 0, this.bU);
                    try {
                        pixelGrabber.grabPixels();
                    }
                    catch (InterruptedException ex6) {}
                }
            }
            try {
                this.h();
            }
            catch (NoSuchMethodError noSuchMethodError) {
                this.h();
            }
            this.bt = this.createImage(this.bF, this.bE);
            this.bs = this.bt.getGraphics();
            this.bc = this.getParameter(c("\u0004qYSP\u0007qQBK"));
            this.k = ((this.bc == null) ? 8 : Integer.valueOf(this.bc));
            this.bc = this.getParameter(c("\u0004qYSP\u0018}\\QK\u0004"));
            this.bI = ((this.bc == null) ? 8 : Integer.valueOf(this.bc));
            this.bc = this.getParameter(c("\u0004qYSP\u0004}EE"));
            this.bO = ((this.bc == null) ? 16 : Integer.valueOf(this.bc));
            if (this.k < 1) {
                this.k = 1;
            }
            if (this.bI < 1) {
                this.bI = 1;
            }
            if (this.bO < 8) {
                this.bO = 8;
            }
            else if (this.bO > 32) {
                this.bO = 32;
            }
            (this.u = new Image[2])[0] = null;
            this.v = new boolean[this.br];
            this.bM = new int[this.br];
            this.bJ = new int[this.br];
            for (int n17 = 0; n17 < this.br; ++n17) {
                this.v[n17] = false;
            }
            this.m = 1;
            if (!this.bG) {
                (this.bV = new Lware(this.getAppletContext(), new Label(c("=wFWJ\u00138TFS\u001c}A\u0016A\t8sWA\u0019w\u0015uJ\u0005{V_\u0003A!\f\u000e\r")))).setTitle(c("=wFWJ\u00138TFS\u001c}A\u0016A\t8sWA\u0019w\u0015uJ\u0005{V_"));
                this.bV.hide();
            }
            return;
        }
        while (true) {
            this.showStatus(c("1tXYP\u00048\u0007\u0016J\u001dyRSPPjPGV\u0019jPR\u0002"));
        }
    }
    
    void h() {
        this.bD = new MemoryImageSource(this.bU, this.s, new DirectColorModel(24, 16711680, 65280, 255), this.o, 0, this.bU);
        String s;
        try {
            s = System.getProperty(c("\u001ayCW\r\u0006}GEJ\u001fv"));
        }
        catch (SecurityException ex) {
            s = c("\u0005v^");
        }
        if (!s.startsWith(c("A6\u0005"))) {
            try {
                this.bD.setAnimated(true);
                this.bD.setFullBufferUpdates(true);
                this.t = this.createImage(this.bD);
                this.bD.newPixels();
                this.x = true;
            }
            catch (NoSuchMethodError noSuchMethodError) {
                this.x = false;
            }
        }
        if (!this.x) {
            this.bD = null;
            this.bC = new anfy(this.bU, this.s, new DirectColorModel(24, 16711680, 65280, 255), this.o, 0, this.bU);
            this.t = this.createImage(this.bC);
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
        if (n3 >= this.B) {
            n3 = this.B - 1;
        }
        while (i <= n3) {
            array[n4 + i] = n;
            n += n7;
            ++i;
        }
    }
    
    private final synchronized boolean i() {
        this.bo = new MediaTracker(this);
        for (int i = 0; i < 2; ++i) {
            this.showStatus(c("9uTQFP") + String.valueOf(i + 1));
            this.u[i] = this.a(this.bA[i]);
            if (this.u[i] == null) {
                this.showStatus(c("5jGYQPtZWG\u0019vR\u0016J\u001dyRS\u0003") + String.valueOf(i + 1));
                return false;
            }
            this.v[i] = true;
            this.bM[i] = this.u[i].getWidth(this);
            this.bJ[i] = this.u[i].getHeight(this);
            if (i == 0) {
                this.bK = this.bM[0];
                this.bL = this.bJ[0];
                this.p = true;
                this.repaint();
            }
            else if (this.bM[i] != this.bM[i - 1] || this.bJ[i] != this.bJ[i - 1]) {
                this.showStatus(c("5jGYQQ8|[B\u0017}F\u0016n%Ka\u0016A\u00158A^FPkT[FPk\\LFQ"));
            }
            if (this.bT == null) {
                this.bT = new int[this.br][this.bM[i] * this.bJ[i]];
            }
            if (!this.a(this.u[i], this.bT[i])) {
                return false;
            }
        }
        this.u[1].flush();
        this.u[1] = null;
        System.gc();
        return true;
    }
    
    private final synchronized boolean a(final int n) {
        new MediaTracker(this);
        final Image a = this.a(this.bA[n]);
        if (a == null) {
            this.showStatus(c("5jGYQPtZWG\u0019vR\u0016J\u001dyRS\u0003") + String.valueOf(n + 1));
            return false;
        }
        this.v[n] = true;
        if (!this.a(a, this.bT[n])) {
            return false;
        }
        a.flush();
        System.gc();
        return true;
    }
    
    void a(final int[] array, int n, final int n2) {
        this.C[0] = array[n + 2];
        this.C[1] = array[n + 1];
        this.C[2] = array[n];
        this.C[3] = array[n + 1];
        this.C[4] = array[n];
        this.C[5] = array[n + 3];
        this.C[6] = array[n + 2];
        this.C[7] = array[n + 3];
        n += 4;
        if (n2 == 0) {
            this.C[8] = array[n];
            this.C[9] = array[n + 3];
            this.C[10] = array[n + 2];
            this.C[11] = array[n + 3];
            this.C[12] = array[n + 2];
            this.C[13] = array[n + 1];
            this.C[14] = array[n];
            this.C[15] = array[n + 1];
            return;
        }
        if (n2 == 1 || n2 == 2) {
            this.C[8] = array[n + 2];
            this.C[9] = array[n + 1];
            this.C[10] = array[n];
            this.C[11] = array[n + 1];
            this.C[12] = array[n];
            this.C[13] = array[n + 3];
            this.C[14] = array[n + 2];
            this.C[15] = array[n + 3];
        }
    }
    
    public mosaic() {
        this.x = false;
        this.q = false;
        this.bH = 1;
        this.bu = false;
        this.b = false;
        this.bG = false;
        this.bp = false;
        this.p = false;
        this.m = 1;
        this.w = false;
        this.bq = 16;
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        if (!this.bG) {
            this.bV.show();
            this.bV.toFront();
            this.bV.requestFocus();
        }
        else {
            this.y = null;
            if (!this.bS[this.m - 1].equalsIgnoreCase("NO")) {
                this.showStatus(c("7w\\XDPlZ\u0016S\u0011\u007fP\u0016") + String.valueOf(this.m));
                try {
                    this.y = new URL(this.getDocumentBase(), this.bS[this.m - 1]);
                }
                catch (MalformedURLException ex) {
                    this.showStatus(c("5jGYQPt\\XH\u0019vR"));
                    return true;
                }
                if (this.y != null) {
                    if (this.bp) {
                        this.getAppletContext().showDocument(this.y, this.getParameter(c("\u0002}RPQ\u0011uPXB\u001d}")));
                    }
                    else {
                        this.getAppletContext().showDocument(this.y);
                    }
                }
            }
        }
        return true;
    }
    
    public final boolean mouseEnter(final Event event, final int n, final int n2) {
        if (!this.bS[this.m - 1].equalsIgnoreCase("NO")) {
            this.a.setCursor(12);
        }
        else {
            this.a.setCursor(0);
        }
        this.w = true;
        this.showStatus(this.bN[this.m - 1]);
        return true;
    }
    
    public final boolean mouseExit(final Event event, final int n, final int n2) {
        this.w = false;
        this.showStatus("");
        return true;
    }
    
    public final boolean mouseMove(final Event event, final int n, final int n2) {
        if (this.bv != this.m) {
            this.showStatus(this.bN[this.m - 1]);
        }
        this.bv = this.m;
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
    
    public final void paint(final Graphics graphics) {
        if (this.q) {
            if (this.t != null) {
                if (this.bH == 1) {
                    this.bs.drawImage(this.t, 0, 0, this);
                }
                else {
                    this.k();
                    this.bs.drawImage(this.t, 0, 0, this.bF, this.bE, this);
                }
                if (this.bw != null) {
                    this.j();
                }
                graphics.drawImage(this.bt, 0, 0, this);
            }
        }
        else {
            this.u();
        }
    }
    
    public synchronized void j() {
        if (this.b) {
            this.notifyAll();
            while (!this.bu) {
                Thread.yield();
                try {
                    Thread.sleep(8L);
                }
                catch (InterruptedException ex) {}
            }
            this.bu = false;
        }
        this.bs.drawImage(this.bw, this.bx, this.by, this);
    }
    
    public synchronized void k() {
        int checkImage = 0;
        this.prepareImage(this.t, this.bF, this.bE, this);
        this.notifyAll();
        while ((checkImage & 0xF0) == 0x0) {
            Thread.yield();
            checkImage = this.checkImage(this.t, this.bF, this.bE, this);
        }
    }
    
    public synchronized void l() {
        int checkImage = 0;
        this.prepareImage(this.u[0], this.bF, this.bE, this);
        this.notifyAll();
        while ((checkImage & 0xF0) == 0x0) {
            Thread.yield();
            checkImage = this.checkImage(this.u[0], this.bF, this.bE, this);
        }
    }
    
    public final void m() {
        try {
            if (this.x) {
                this.bD.newPixels();
                return;
            }
            this.bC.startProduction(this.bC.getConsumer());
        }
        catch (NoSuchMethodError noSuchMethodError) {}
    }
    
    protected void n() {
        for (int i = 0; i < 4; ++i) {
            this.P[i * 2] = (int)(this.bU / 2.0 + this.N[i * 6 + 3] * 256.0 / this.N[i * 6 + 5]);
            this.P[i * 2 + 1] = (int)(this.s / 2.0 - this.N[i * 6 + 4] * 256.0 / this.N[i * 6 + 5]);
        }
    }
    
    private void o() {
        final double n = this.bK / this.E;
        final double n2 = this.bL / this.L;
        for (int i = 0; i < this.L; ++i) {
            for (int j = 0; j < this.E; ++j) {
                final int n3 = j + i * this.E;
                this.V[n3 * 8] = (int)((this.E - 1 - j) * n);
                this.V[n3 * 8 + 1] = (int)((this.L - 1 - i) * n2);
                this.V[n3 * 8 + 2] = (int)(n + (this.E - 1 - j) * n - 1.0);
                this.V[n3 * 8 + 3] = (int)((this.L - 1 - i) * n2 + n2 - 1.0);
                this.V[n3 * 8 + 4] = this.V[n3 * 8];
                this.V[n3 * 8 + 5] = this.V[n3 * 8 + 1];
                this.V[n3 * 8 + 6] = this.V[n3 * 8 + 2];
                this.V[n3 * 8 + 7] = this.V[n3 * 8 + 3];
            }
        }
    }
    
    void p() {
        this.l = 0;
        if (!this.v[this.m % this.br]) {
            this.a(this.m % this.br);
        }
        for (int i = 0; i < this.L; ++i) {
            for (int j = 0; j < this.E; ++j) {
                final int n = j + i * this.E;
                this.a(this.W[n * 3], this.W[n * 3 + 1], this.W[n * 3 + 2]);
                if (this.X[n] == 0) {
                    this.bX = this.U[n];
                    this.ca = 0.0;
                }
                else if (this.X[n] == 1) {
                    this.ca = this.U[n];
                    this.bX = 0.0;
                }
                else {
                    this.bX = 2.0 * this.U[n];
                    this.ca = this.U[n];
                }
                this.a(this.V, n * 8, this.X[n]);
                this.e();
                if (this.A[n] <= this.bq && this.A[n] > 0) {
                    this.U[n] = 3.141592653589793 / this.bq * (this.A[n] - 1);
                }
                else if (this.A[n] < -1) {
                    ++this.l;
                }
                final int[] a = this.A;
                final int n2 = n;
                --a[n2];
            }
        }
        if (this.l == this.L * this.E) {
            this.r();
            this.g();
            for (int k = 0; k < this.L * this.E; ++k) {
                this.U[k] = 3.141592653589793;
            }
            ++this.z;
            ++this.m;
            if (this.m > this.br) {
                this.m = 1;
            }
            if (this.w) {
                this.showStatus(this.bN[this.m - 1]);
            }
            if (!this.bS[this.m - 1].equalsIgnoreCase("NO")) {
                this.a.setCursor(12);
            }
            else {
                this.a.setCursor(0);
            }
            try {
                Thread.sleep(this.bz);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    void b(final int n) {
        final int[] array = this.bT[n % this.br];
        final int[] o = this.o;
        int i = this.bZ;
        final int by = this.bY;
        final int[] r = this.R;
        final int[] s = this.S;
        final int b = this.B;
        final int z = this.Z;
        final int n2 = this.Z - 1;
        final int bk = this.bK;
        while (i <= by) {
            final int n3 = i + b;
            int n4 = this.Q[i] >> 16;
            int n5 = this.Q[n3] >> 16;
            int n6 = r[i];
            final int n7 = r[n3];
            int n8 = s[i];
            final int n9 = s[n3];
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
            if (n5 >= z) {
                n5 = n2;
            }
            for (int j = this.bU * i + n4; j <= this.bU * i + n5; ++j) {
                o[j] = array[bk * (n8 >> 16) + (n6 >> 16)];
                n6 += n11;
                n8 += n12;
            }
            ++i;
        }
    }
    
    protected void q() {
        final double sin = Math.sin(this.bW);
        final double sin2 = Math.sin(this.bX);
        final double sin3 = Math.sin(this.ca);
        final double cos = Math.cos(this.bW);
        final double cos2 = Math.cos(this.bX);
        final double cos3 = Math.cos(this.ca);
        this.D[0] = cos2 * cos;
        this.D[1] = -cos2 * sin;
        this.D[2] = sin2;
        this.D[3] = this.O[3];
        this.D[4] = sin3 * sin2 * cos + sin * cos3;
        this.D[5] = cos3 * cos - sin3 * sin2 * sin;
        this.D[6] = -sin3 * cos2;
        this.D[7] = this.O[4];
        this.D[8] = sin3 * sin - cos3 * sin2 * cos;
        this.D[9] = cos3 * sin2 * sin + sin3 * cos;
        this.D[10] = cos3 * cos2;
        this.D[11] = this.O[5];
        this.D[12] = 0.0;
        this.D[13] = 0.0;
        this.D[14] = 0.0;
        this.D[15] = 1.0;
        this.s();
        this.t();
    }
    
    public void run() {
        this.bQ.setPriority(this.bB);
        this.showStatus("");
        this.n = System.currentTimeMillis();
        if (!this.v[0]) {
            this.i();
        }
        this.showStatus("");
        this.a(this.bU, this.s, this.bK, this.bL, this.bI, this.k);
        try {
            System.arraycopy(this.j, 0, this.o, 0, this.bR);
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            this.stop();
        }
        catch (ArrayStoreException ex2) {
            this.stop();
        }
        this.p();
        try {
            this.m();
        }
        catch (NoSuchMethodError noSuchMethodError) {}
        this.p = false;
        this.repaint();
        System.gc();
        final long n = this.bz - (System.currentTimeMillis() - this.n);
        if (n > 0L) {
            try {
                Thread.sleep(n);
            }
            catch (InterruptedException ex3) {}
        }
        final Graphics graphics = this.getGraphics();
        if (this.bw != null && !this.b) {
            this.b = this.d();
        }
        if (!this.bS[this.m - 1].equalsIgnoreCase("NO")) {
            this.a.setCursor(12);
        }
        else {
            this.a.setCursor(0);
        }
        while (this.bQ != null) {
            try {
                System.arraycopy(this.j, 0, this.o, 0, this.bR);
            }
            catch (ArrayIndexOutOfBoundsException ex4) {
                this.stop();
            }
            catch (ArrayStoreException ex5) {
                this.stop();
            }
            this.p();
            if (++this.i == this.ba) {
                System.gc();
                this.i = 0;
            }
            try {
                this.m();
            }
            catch (NoSuchMethodError noSuchMethodError2) {}
            if (this.bH == 1) {
                this.bs.drawImage(this.t, 0, 0, this);
            }
            else {
                this.k();
                this.bs.drawImage(this.t, 0, 0, this.bF, this.bE, this);
            }
            if (this.bw != null) {
                this.j();
            }
            graphics.drawImage(this.bt, 0, 0, this);
            this.v();
        }
    }
    
    byte a(final int n, final int n2, final int n3, final int n4) {
        final int n5 = n - n2;
        if (n5 >= n3) {
            return (byte)n5;
        }
        return (byte)(n4 - (n3 - n5 - 1));
    }
    
    protected void r() {
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
            for (int i = 0; i < this.L * this.E; ++i) {
                this.X[i] = ((Math.random() <= 0.5) ? 1 : 0);
            }
            return;
        }
        for (int j = 0; j < this.L * this.E; ++j) {
            switch (n) {
                case 0: {
                    this.X[j] = 0;
                    break;
                }
                case 1: {
                    this.X[j] = 1;
                    break;
                }
                case 3: {
                    this.X[j] = 2;
                    break;
                }
            }
        }
    }
    
    void a(final double n, final double n2, final double n3) {
        this.O[0] = (this.O[3] = n);
        this.O[1] = (this.O[4] = n2);
        this.O[2] = (this.O[5] = n3);
    }
    
    private void b(final int n, final int n2, final int n3, final int n4, final int l, final int e) {
        this.A = new int[l * e];
        this.L = l;
        this.E = e;
        this.W = new double[l * e * 3];
        this.U = new double[l * e];
        this.V = new int[l * e * 8];
        final double n5 = n3 / e;
        final double n6 = n4 / l;
        this.a(n5, n6, n, n2);
        for (int i = 0; i < this.L; ++i) {
            for (int j = 0; j < this.E; ++j) {
                final int n7 = j + i * this.E;
                this.W[n7 * 3] = (j - this.E / 2.0) * 2.0 * n5 + n5;
                this.W[n7 * 3 + 1] = (this.L / 2.0 - i) * 2.0 * n6 - n6;
                this.W[n7 * 3 + 2] = -512.0;
                this.U[n7] = 3.141592653589793;
            }
        }
        this.g();
        this.o();
    }
    
    protected void s() {
        for (int i = 0; i < 4; ++i) {
            final double n = this.N[i * 6];
            final double n2 = this.N[i * 6 + 1];
            final double n3 = this.N[i * 6 + 2];
            this.N[i * 6 + 3] = n * this.D[0] + n2 * this.D[1] + n3 * this.D[2] + this.D[3];
            this.N[i * 6 + 4] = n * this.D[4] + n2 * this.D[5] + n3 * this.D[6] + this.D[7];
            this.N[i * 6 + 5] = n * this.D[8] + n2 * this.D[9] + n3 * this.D[10] + this.D[11];
        }
    }
    
    protected void t() {
        for (int i = 0; i < 2; ++i) {
            final double n = this.H[i * 6];
            final double n2 = this.H[i * 6 + 1];
            final double n3 = this.H[i * 6 + 2];
            final double[] h = this.H;
            final int n4 = i * 6 + 3;
            final double n5 = n * this.D[0] + n2 * this.D[1] + n3 * this.D[2];
            h[n4] = n5;
            final double n6 = n5;
            final double[] h2 = this.H;
            final int n7 = i * 6 + 4;
            final double n8 = n * this.D[4] + n2 * this.D[5] + n3 * this.D[6];
            h2[n7] = n8;
            final double n9 = n8;
            final double[] h3 = this.H;
            final int n10 = i * 6 + 5;
            final double n11 = n * this.D[8] + n2 * this.D[9] + n3 * this.D[10];
            h3[n10] = n11;
            this.Y[i] = (n6 * this.N[this.M[i * 4] * 6 + 3] + n9 * this.N[this.M[i * 4] * 6 + 4] + n11 * this.N[this.M[i * 4] * 6 + 5] > 0.0);
        }
    }
    
    protected void a(final int z, final int b) {
        this.C = new int[16];
        this.P = new int[8];
        this.Y = new boolean[2];
        this.D = new double[16];
        this.Q = new int[b * 2];
        this.R = new int[b * 2];
        this.S = new int[b * 2];
        this.B = b;
        this.Z = z;
        this.O = new double[6];
        for (int i = 0; i < 6; ++i) {
            this.O[i] = 0.0;
        }
    }
    
    public void start() {
        if (this.bQ == null) {
            (this.bQ = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.bQ != null && this.bQ.isAlive()) {
            this.bQ.stop();
        }
        this.bQ = null;
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
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
        if (n2 >= this.B) {
            n2 = this.B - 1;
        }
        while (i <= n2) {
            array[n3 + i] = n << 16;
            ++i;
        }
    }
    
    public final void u() {
        final Graphics graphics = this.getGraphics();
        if (this.u != null && this.bs != null && this.p && this.u[0] != null) {
            final int n = (this.bU - this.bK) / 2 + 1;
            final int n2 = (this.s - this.bL) / 2 + 1;
            this.bs.setColor(this.e);
            this.bs.fillRect(0, 0, this.bF, this.bE);
            if (this.bH == 1) {
                this.bs.drawImage(this.u[0], n, n2, this);
            }
            else {
                this.l();
                this.bs.drawImage(this.u[0], n, n2, this.bF, this.bE, this);
            }
            if (this.bw != null) {
                this.bs.drawImage(this.bw, this.bx, this.by, this);
            }
            this.bs.setColor(Color.black);
            this.bs.drawString(c("<wTRJ\u001e\u007f\u001b\u0018\r"), this.bF / 2 - 26 + 1, this.bE / 2 + 2 + 1);
            this.bs.setColor(Color.white);
            this.bs.drawString(c("<wTRJ\u001e\u007f\u001b\u0018\r"), this.bF / 2 - 26, this.bE / 2 + 2);
            graphics.drawImage(this.bt, 0, 0, this);
        }
    }
    
    public synchronized void v() {
        Thread.yield();
        this.bP.sync();
        final long n = 10L - (System.currentTimeMillis() - this.n);
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
        this.n = System.currentTimeMillis();
        try {
            Thread.sleep(this.bb);
        }
        catch (InterruptedException ex3) {}
    }
    
    private static String c(final String s) {
        final char[] charArray = s.toCharArray();
        final int length = charArray.length;
        int n = 0;
    Label_0010:
        while (true) {
            while (true) {
                int n2 = 0;
                char c = 'p';
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
                                c = '\u0018';
                                array = (array2 = charArray);
                                n3 = (n4 = n);
                                continue;
                            }
                            c = '5';
                            array = (array2 = charArray);
                            n3 = (n4 = n);
                            continue;
                        }
                        c = '6';
                        array = (array2 = charArray);
                        n3 = (n4 = n);
                        continue;
                    }
                    c = '#';
                    array = (array2 = charArray);
                    n3 = (n4 = n);
                }
            }
            break;
        }
        return new String(charArray);
    }
}
