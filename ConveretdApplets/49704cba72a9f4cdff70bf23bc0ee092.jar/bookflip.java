import java.awt.Color;
import java.awt.image.PixelGrabber;
import java.awt.Event;
import java.awt.image.ImageProducer;
import java.awt.image.ColorModel;
import java.awt.image.DirectColorModel;
import java.awt.Container;
import java.awt.Label;
import java.awt.LayoutManager;
import java.io.InputStream;
import java.awt.Component;
import java.awt.MediaTracker;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.net.MalformedURLException;
import java.awt.Toolkit;
import java.awt.image.MemoryImageSource;
import java.net.URL;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Frame;
import java.awt.image.ImageObserver;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class bookflip extends Applet implements Runnable, ImageObserver
{
    Frame a;
    boolean b;
    int c;
    final String d = "\u0019%\u0016vy,u\u0004c<\u001e4\u0004ssx\u0016\u000fo\u007f;<F2k/\"H{r>";
    int e;
    int[] f;
    int g;
    int[] h;
    int i;
    long j;
    int[] k;
    int l;
    int m;
    boolean n;
    private Graphics o;
    int p;
    private Image q;
    Image[] r;
    int s;
    boolean[] t;
    int u;
    int v;
    boolean w;
    boolean x;
    int y;
    URL z;
    int A;
    int B;
    String C;
    String D;
    String E;
    String F;
    String G;
    String H;
    String I;
    String J;
    boolean K;
    int L;
    private Graphics M;
    private Image N;
    boolean O;
    int P;
    private Image Q;
    int R;
    int S;
    int T;
    String[] U;
    int V;
    int W;
    int X;
    anfy Y;
    MemoryImageSource Z;
    int ba;
    int bb;
    boolean bc;
    int bd;
    int[] be;
    int[] bf;
    int bg;
    int bh;
    float[] bi;
    int[] bj;
    int[] bk;
    int[] bl;
    int[] bm;
    int bn;
    int bo;
    int bp;
    int[][] bq;
    int br;
    String[] bs;
    Toolkit bt;
    Thread bu;
    int bv;
    String[] bw;
    int bx;
    boolean by;
    Lware bz;
    int bA;
    int bB;
    
    public bookflip() {
        this.x = false;
        this.n = false;
        this.bd = 1;
        this.bg = 1;
        this.bh = 50;
        this.W = -1;
        this.X = 9;
        this.O = false;
        this.b = false;
        this.bc = false;
        this.K = false;
        this.by = false;
        this.i = 1;
        this.w = false;
    }
    
    private final void a() {
        while (true) {
            this.showStatus(c("\u001c:\b=hx'\u0003ws.0Fmk/{\u0007tz!?\u0007l}v6\tw<;'\u0003~u,&Fvu60Fsrx\u001d2WPy"));
            try {
                Thread.sleep(500L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public synchronized boolean b() {
        this.prepareImage(this.Q, this);
        if (this.x) {
            for (int i = 0; i < 3; ++i) {
                this.notifyAll();
                Thread.yield();
                try {
                    Thread.sleep(100L);
                }
                catch (InterruptedException ex) {}
            }
            return this.O;
        }
        return false;
    }
    
    public void a(final int n) {
        final int n2 = (this.bx - this.v) / 2 + (this.p - this.s) / 2 * this.bx;
        final int[] array = this.bq[n];
        final int[] h = this.h;
        final int u = this.u;
        final int n3 = n2;
        try {
            System.arraycopy(array, 0, h, n3, u);
        }
        catch (ArrayIndexOutOfBoundsException ex) {}
        catch (ArrayStoreException ex2) {}
        this.W = -1;
        this.by = true;
    }
    
    public void c() {
        this.m = this.bh / 2;
        this.bj = new int[this.bh];
        this.bk = new int[this.bh];
        final double n = 3.141592653589793 / (this.bh * 2);
        for (int i = 0; i < this.bh; ++i) {
            this.bj[i] = (int)(Math.cos(i * n + 4.71238898038469) * this.m);
            this.bk[i] = (int)(this.m + Math.sin(i * n + 4.71238898038469) * this.m);
        }
        this.bi = new float[this.bh];
        final int n2 = this.bh / 2;
        final float n3 = this.bo / n2;
        for (int j = 0; j < n2; ++j) {
            this.bi[j] = (255.0f - j * n3) / 255.0f;
        }
        for (int k = n2 - 1; k > -1; --k) {
            this.bi[n2 + k] = this.bi[n2 - k - 1];
        }
        final float n4 = this.bp / n2;
        for (int l = 0; l < n2; ++l) {
            this.bi[l] = (255.0f - l * n4) / 255.0f;
        }
    }
    
    public void destroy() {
        if (this.Q != null) {
            this.Q.flush();
        }
        this.Q = null;
        if (this.N != null) {
            this.N.flush();
        }
        this.N = null;
        if (this.M != null) {
            this.M.dispose();
        }
        this.M = null;
        System.gc();
    }
    
    public void d() {
        if (this.W == -1) {
            if (!this.t[this.bf[this.bg]]) {
                this.b(this.bf[this.bg]);
            }
            switch (this.be[this.bg]) {
                case 0: {
                    this.W = 21;
                    this.X = 1;
                    this.y = this.bf[this.bg];
                    break;
                }
                case 1: {
                    this.W = 21;
                    this.X = 2;
                    this.y = this.bf[this.bg];
                    break;
                }
                case 2: {
                    this.W = 21;
                    this.X = 3;
                    this.y = this.bf[this.bg];
                    break;
                }
                case 3: {
                    this.W = 21;
                    this.X = 4;
                    this.y = this.bf[this.bg];
                    break;
                }
                case 4: {
                    this.W = 11;
                    this.X = 1;
                    this.y = this.bf[this.bg];
                    break;
                }
                case 5: {
                    this.W = 11;
                    this.X = 2;
                    this.y = this.bf[this.bg];
                    break;
                }
                case 6: {
                    this.W = 11;
                    this.X = 3;
                    this.y = this.bf[this.bg];
                    break;
                }
                case 7: {
                    this.W = 11;
                    this.X = 4;
                    this.y = this.bf[this.bg];
                    break;
                }
                case 8: {
                    this.a(this.bf[this.bg]);
                    break;
                }
            }
            ++this.bg;
            if (this.bg >= this.c) {
                this.bg = 0;
            }
            this.i = this.bg / 2 + 1;
            if (this.w) {
                this.showStatus(this.bs[this.i - 1]);
            }
            if (!this.bw[this.i - 1].equalsIgnoreCase("NO")) {
                this.a.setCursor(12);
            }
            else {
                this.a.setCursor(0);
            }
            this.f = this.bq[this.y];
        }
        if (this.W == 11) {
            this.bA = this.v;
            this.W = 1;
        }
        if (this.W == 1) {
            this.bA -= this.br;
            if (this.bA < -2 * this.s) {
                this.W = -1;
            }
        }
        if (this.W == 21) {
            this.bA = -2 * this.s;
            this.W = 2;
        }
        if (this.W == 2) {
            this.bA += this.br;
            if (this.bA > this.bx) {
                this.W = -1;
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
                this.showStatus(c("\u00118\u0007}yx") + s + c("x;\tn<>:\u0013txy"));
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
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if (image == this.Q) {
            if (n == 16) {
                this.O = true;
            }
            return true;
        }
        return true;
    }
    
    public void init() {
        this.setLayout(null);
        this.addNotify();
        this.bt = this.getToolkit();
        final String parameter = this.getParameter(c(";'\u0003~u,&"));
        if (parameter != null) {
            if (!parameter.startsWith(c("\u0019%\u0016vy,u\u0004c<\u001e4\u0004ssx\u0016\u000fo\u007f;<F2k/\"H{r>"))) {
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
            s = c("><\n\u007f");
        }
        String s2;
        try {
            s2 = this.getDocumentBase().getHost();
        }
        catch (SecurityException ex2) {
            s2 = "";
        }
        if (s.equalsIgnoreCase(c("><\n\u007f")) || s2.length() == 0 || s2.equalsIgnoreCase(c("4:\u0005{p0:\u0015n")) || s2.equals(c("igQ4,veH+"))) {
            this.bc = true;
        }
        else {
            if (s2.startsWith(c("/\"\u00114"))) {
                s2 = s2.substring(4);
            }
            final String parameter2 = this.getParameter(c("*0\u0001ys<0"));
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
                        if (s5.startsWith(c("/\"\u00114"))) {
                            substring = s5.substring(4);
                        }
                        else {
                            substring = s5;
                        }
                        if (s2.equalsIgnoreCase(substring)) {
                            this.bc = true;
                        }
                    }
                }
            }
        }
        if (this.getParameter(c("*0\u0001ty/3\u0014{q=")).equalsIgnoreCase(c("\u0001\u00105"))) {
            this.K = true;
        }
        this.H = this.getParameter(c("\u0015<\bIE\u0016\u0016"));
        if (this.H == null) {
            this.H = "10";
        }
        this.B = Integer.valueOf(this.H);
        this.n = false;
        Container container;
        for (container = this.getParent(); !(container instanceof Frame); container = ((Frame)container).getParent()) {}
        (this.a = (Frame)container).setCursor(3);
        final String parameter3 = this.getParameter(c("7#\u0003hu52"));
        if (parameter3 != null && !parameter3.equalsIgnoreCase("NO")) {
            this.Q = this.a(parameter3);
            if (this.Q != null) {
                String parameter4 = this.getParameter(c("7#\u0003hu52>"));
                if (parameter4 == null) {
                    parameter4 = "0";
                }
                this.R = Integer.valueOf(parameter4);
                String parameter5 = this.getParameter(c("7#\u0003hu52?"));
                if (parameter5 == null) {
                    parameter5 = "0";
                }
                this.S = Integer.valueOf(parameter5);
            }
        }
        this.C = this.getParameter(c("*0\u0015"));
        if (this.C == null) {
            this.C = "1";
        }
        this.D = this.getParameter(c("+%\u0003\u007fx"));
        if (this.D == null) {
            this.D = "8";
        }
        this.E = this.getParameter(c("(4\u0013iy"));
        if (this.E == null) {
            this.E = c("i`V*");
        }
        this.bd = Integer.valueOf(this.C);
        this.br = Integer.valueOf(this.D);
        this.T = Integer.valueOf(this.E);
        this.I = this.getParameter(c("50\u000b~y44\u001f"));
        this.J = this.getParameter(c("('\u000fun1!\u001f"));
        this.A = Integer.valueOf(this.I);
        this.V = Integer.valueOf(this.J);
        if (this.A < 0) {
            this.A = 0;
        }
        if (this.V > 10) {
            this.V = 10;
        }
        else if (this.V < 1) {
            this.V = 1;
        }
        this.L = 1;
        while (this.getParameter(c("18\u0007}y") + String.valueOf(this.L)) != null) {
            ++this.L;
        }
        --this.L;
        if (this.L > 1) {
            this.U = new String[this.L];
            this.bw = new String[this.L];
            this.bs = new String[this.L];
            final int[] array8 = new int[this.L + 1];
            for (int n14 = 0; n14 < this.L; ++n14) {
                this.U[n14] = this.getParameter(c("18\u0007}y") + String.valueOf(n14 + 1));
            }
            for (int n15 = 0; n15 < this.L; ++n15) {
                this.bw[n15] = this.getParameter(c("4<\bq") + String.valueOf(n15 + 1));
                this.bs[n15] = this.getParameter(c("+!\u0007ni+8\u0015}") + String.valueOf(n15 + 1));
                array8[n15] = Integer.valueOf(this.getParameter(c(">9\u000fj") + String.valueOf(n15 + 1)));
            }
            array8[this.L] = array8[0];
            this.I = this.getParameter(c("=-\u0012h}0"));
            if (this.I == null) {
                this.I = "0";
            }
            this.l = Integer.valueOf(this.I);
            this.I = this.getParameter(c(">9\u000fj\u007f-'\u0010\u007f"));
            if (this.I == null) {
                this.I = "0";
            }
            this.bh *= Integer.valueOf(this.I);
            if (this.bh > 500) {
                this.bh = 500;
            }
            else if (this.bh < 50) {
                this.bh = 50;
            }
            this.I = this.getParameter(c("+=\u0007~u62"));
            if (this.I == null) {
                this.I = "4";
            }
            this.bo = Integer.valueOf(this.I) * 64 - 1;
            if (this.bo < 0) {
                this.bo = 0;
            }
            this.bp = (int)(this.bo / 1.5f);
            this.I = this.getParameter(c(":4\u0005qn"));
            if (this.I == null) {
                this.I = "64";
            }
            final int intValue = Integer.valueOf(this.I);
            this.I = this.getParameter(c(":4\u0005q{"));
            if (this.I == null) {
                this.I = "96";
            }
            final int intValue2 = Integer.valueOf(this.I);
            this.I = this.getParameter(c(":4\u0005q~"));
            if (this.I == null) {
                this.I = c("icV");
            }
            this.e = (intValue << 16 | intValue2 << 8 | Integer.valueOf(this.I));
            this.I = this.getParameter(c("=-\u0012h}0"));
            if (this.I == null) {
                this.I = "0";
            }
            this.l = Integer.valueOf(this.I);
            this.bx = this.size().width / this.bd;
            this.p = this.size().height / this.bd;
            this.bb = this.bx * this.bd;
            this.ba = this.p * this.bd;
            this.v = this.bx;
            this.s = this.p - this.l;
            this.bv = this.bx * this.p;
            this.u = this.v * this.s;
            this.bB = (this.p - this.s) / 2;
            this.h = new int[this.bv];
            for (int n16 = 0; n16 < this.bv; ++n16) {
                this.h[n16] = this.e;
            }
            this.k = new int[this.bv];
            try {
                System.arraycopy(this.h, 0, this.k, 0, this.bv);
            }
            catch (ArrayIndexOutOfBoundsException ex6) {}
            catch (ArrayStoreException ex7) {}
            try {
                this.e();
            }
            catch (NoSuchMethodError noSuchMethodError) {
                this.e();
            }
            int n17 = 0;
            this.c = this.L * 2;
            this.be = new int[this.c];
            this.bf = new int[this.c];
            for (int n18 = 0; n18 < this.L; ++n18) {
                final int n19 = array8[n18];
                final int n20 = array8[n18 + 1];
                if ((this.be[n17] = n19) < 4) {
                    this.bf[n17++] = n18;
                }
                else if (n18 > 0) {
                    this.bf[n17++] = n18 - 1;
                }
                else {
                    this.bf[n17++] = this.L - 1;
                }
                this.be[n17] = 8;
                if (n20 < 4) {
                    this.bf[n17++] = n18;
                }
                else if (n18 < this.L - 1) {
                    this.bf[n17++] = n18 + 1;
                }
                else {
                    this.bf[n17++] = 0;
                }
            }
            this.t = new boolean[this.L];
            this.bm = new int[this.L];
            this.bl = new int[this.L];
            for (int n21 = 0; n21 < this.L; ++n21) {
                this.t[n21] = false;
            }
            this.i = 1;
            this.c();
            this.r = new Image[2];
            this.bq = new int[this.L][this.u];
            this.N = this.createImage(this.bb, this.ba);
            this.M = this.N.getGraphics();
            if (!this.bc) {
                (this.bz = new Lware(this.getAppletContext(), new Label(c("\u001a:\tqZ4<\u0016:}(%\n\u007fhx7\u001f:Z97\u000fu<\u001b<\u0013y\u007f1uW#%`{")))).setTitle(c("\u001a:\tqZ4<\u0016:](%\n\u007fhx7\u001f:Z97\u000fu<\u001b<\u0013y\u007f1"));
                this.bz.hide();
            }
            return;
        }
        while (true) {
            this.showStatus(c("\u00199\u000buo,uT:u54\u0001\u007fox'\u0003ki1'\u0003~="));
        }
    }
    
    void e() {
        this.Z = new MemoryImageSource(this.bx, this.p, new DirectColorModel(24, 16711680, 65280, 255), this.k, 0, this.bx);
        String s;
        try {
            s = System.getProperty(c("24\u0010{2.0\u0014iu7;"));
        }
        catch (SecurityException ex) {
            s = c("-;\r");
        }
        if (!s.startsWith(c("i{V"))) {
            try {
                this.Z.setAnimated(true);
                this.Z.setFullBufferUpdates(true);
                this.q = this.createImage(this.Z);
                this.Z.newPixels();
                this.x = true;
            }
            catch (NoSuchMethodError noSuchMethodError) {
                this.x = false;
            }
        }
        if (!this.x) {
            this.Z = null;
            this.Y = new anfy(this.bx, this.p, new DirectColorModel(24, 16711680, 65280, 255), this.k, 0, this.bx);
            this.q = this.createImage(this.Y);
        }
    }
    
    private final synchronized boolean f() {
        new MediaTracker(this);
        for (int i = 0; i < 2; ++i) {
            this.showStatus(c("\u00118\u0007}yx") + String.valueOf(i + 1));
            this.r[i] = this.a(this.U[i]);
            if (this.r[i] == null) {
                this.showStatus(c("\u001d'\u0014unx9\t{x1;\u0001:u54\u0001\u007f<") + String.valueOf(i + 1));
                return false;
            }
            this.t[i] = true;
            this.bm[i] = this.r[i].getWidth(this);
            this.bl[i] = this.r[i].getHeight(this);
            if (i != 0 && (this.bm[i] != this.bm[i - 1] || this.bl[i] != this.bl[i - 1])) {
                this.showStatus(c("\u001d'\u0014unyu/w}?0\u0015:Q\r\u00062:~=u\u0012ryx&\u0007wyx&\u000f`yy"));
            }
            if (!this.a(this.r[i], this.bq[i])) {
                return false;
            }
            if (i == 0) {
                this.a(0);
                try {
                    System.arraycopy(this.h, 0, this.k, 0, this.bv);
                }
                catch (ArrayIndexOutOfBoundsException ex) {}
                catch (ArrayStoreException ex2) {}
                try {
                    this.n();
                }
                catch (NoSuchMethodError noSuchMethodError) {}
                this.repaint();
            }
        }
        this.r[1].flush();
        this.r[1] = null;
        System.gc();
        return true;
    }
    
    private final synchronized boolean b(final int n) {
        new MediaTracker(this);
        final Image a = this.a(this.U[n]);
        if (a == null) {
            this.showStatus(c("\u001d'\u0014unx9\t{x1;\u0001:u54\u0001\u007f<") + String.valueOf(n + 1));
            return false;
        }
        this.t[n] = true;
        if (!this.a(a, this.bq[n])) {
            return false;
        }
        a.flush();
        System.gc();
        return true;
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        if (!this.bc) {
            this.bz.show();
            this.bz.toFront();
            this.bz.requestFocus();
        }
        else {
            this.z = null;
            if (!this.bw[this.i - 1].equalsIgnoreCase("NO")) {
                this.showStatus(c("\u001f:\u000ft{x!\t:l92\u0003:") + String.valueOf(this.i));
                try {
                    this.z = new URL(this.getDocumentBase(), this.bw[this.i - 1]);
                }
                catch (MalformedURLException ex) {
                    this.showStatus(c("\u001d'\u0014unx9\u000ftw1;\u0001"));
                    return true;
                }
                if (this.z != null) {
                    if (this.K) {
                        this.getAppletContext().showDocument(this.z, this.getParameter(c("*0\u0001|n98\u0003t}50")));
                    }
                    else {
                        this.getAppletContext().showDocument(this.z);
                    }
                }
            }
        }
        return true;
    }
    
    public final boolean mouseEnter(final Event event, final int n, final int n2) {
        if (!this.bw[this.i - 1].equalsIgnoreCase("NO")) {
            this.a.setCursor(12);
        }
        else {
            this.a.setCursor(0);
        }
        this.w = true;
        this.showStatus(this.bs[this.i - 1]);
        return true;
    }
    
    public final boolean mouseExit(final Event event, final int n, final int n2) {
        this.w = false;
        this.showStatus("");
        return true;
    }
    
    public final boolean mouseMove(final Event event, final int n, final int n2) {
        if (this.P != this.i) {
            this.showStatus(this.bs[this.i - 1]);
        }
        this.P = this.i;
        return true;
    }
    
    private boolean a(final Image image, final int[] array) {
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, this.v, this.s, array, 0, this.v);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {
            return false;
        }
        return true;
    }
    
    public void g() {
        final int n = this.bA + this.bh;
        final int n2 = this.bA + this.m;
        final int n3 = this.m - this.bA - this.bh + this.bB;
        final int[] k = this.k;
        final int[] f = this.f;
        final int[] bj = this.bj;
        final int[] bk = this.bk;
        for (int i = this.p - 1; i > -1; --i) {
            for (int j = 0; j < this.bx; ++j) {
                if (j - i > this.bA) {
                    if (j - i > n) {
                        final int n4 = i + n2;
                        final int n5 = j + n3;
                        if (n4 < this.v && n4 > -1 && n5 < this.p && n5 > -1 && j > -1 && j < this.v && i > -1 && i < this.s) {
                            k[n4 + n5 * this.bx] = f[j + i * this.v];
                        }
                    }
                    else {
                        final int n6 = j - i - this.bA;
                        if (n6 <= this.bh - 1) {
                            final int n7 = i + this.bA + bj[n6];
                            final int n8 = i + bk[n6] + this.bB;
                            if (n7 < this.v && n7 > -1 && n8 < this.p && n8 > -1 && j > -1 && j < this.v && i > -1 && i < this.s) {
                                k[n7 + n8 * this.bx] = f[j + i * this.v];
                            }
                        }
                    }
                }
                else {
                    final int n9 = j;
                    final int n10 = i + this.bB;
                    if (n9 < this.v && n9 > -1 && n10 < this.p && n10 > -1 && j > -1 && j < this.v && i > -1 && i < this.s) {
                        k[n9 + n10 * this.bx] = f[j + i * this.v];
                    }
                }
            }
        }
    }
    
    public void h() {
        final int n = this.bA + this.bh;
        final int n2 = this.bA + this.m;
        final int n3 = this.m - this.bA - this.bh + this.bB;
        final int[] k = this.k;
        final int[] f = this.f;
        final int[] bj = this.bj;
        final int[] bk = this.bk;
        for (int i = this.p - 1; i > -1; --i) {
            for (int j = 0; j < this.bx; ++j) {
                if (j - i > this.bA) {
                    if (j - i > n) {
                        final int n4 = i + n2;
                        final int n5 = j + n3;
                        if (n4 < this.v && n4 > -1 && n5 < this.p && n5 > -1 && j > -1 && j < this.v && i > -1 && i < this.s) {
                            k[n4 + (this.p - n5 - 1) * this.bx] = f[j + (this.s - 1 - i) * this.v];
                        }
                    }
                    else {
                        final int n6 = j - i - this.bA;
                        if (n6 <= this.bh - 1) {
                            final int n7 = i + this.bA + bj[n6];
                            final int n8 = i + bk[n6] + this.bB;
                            if (n7 < this.v && n7 > -1 && n8 < this.p && n8 > -1 && j > -1 && j < this.v && i > -1 && i < this.s) {
                                k[n7 + (this.p - 1 - n8) * this.bx] = f[j + (this.s - 1 - i) * this.v];
                            }
                        }
                    }
                }
                else {
                    final int n9 = j;
                    final int n10 = i + this.bB;
                    if (n9 < this.v && n9 > -1 && n10 < this.p && n10 > -1 && j > -1 && j < this.v && i > -1 && i < this.s) {
                        k[n9 + (this.p - n10 - 1) * this.bx] = f[j + (this.s - 1 - i) * this.v];
                    }
                }
            }
        }
    }
    
    public void i() {
        final int n = this.bA + this.bh;
        final int n2 = this.bA + this.m;
        final int n3 = this.m - this.bA - this.bh + this.bB;
        final int n4 = this.bx - 1;
        final int n5 = this.v - 1;
        final int[] k = this.k;
        final int[] f = this.f;
        final int[] bj = this.bj;
        final int[] bk = this.bk;
        for (int i = this.p - 1; i > -1; --i) {
            for (int j = 0; j < this.bx; ++j) {
                if (j - i > this.bA) {
                    if (j - i > n) {
                        final int n6 = i + n2;
                        final int n7 = j + n3;
                        if (n6 < this.v && n6 > -1 && n7 < this.p && n7 > -1 && j > -1 && j < this.v && i > -1 && i < this.s) {
                            k[n4 - n6 + (this.p - 1 - n7) * this.bx] = f[n5 - j + (this.s - 1 - i) * this.v];
                        }
                    }
                    else {
                        final int n8 = j - i - this.bA;
                        if (n8 <= this.bh - 1) {
                            final int n9 = i + this.bA + bj[n8];
                            final int n10 = i + bk[n8] + this.bB;
                            if (n9 < this.v && n9 > -1 && n10 < this.p && n10 > -1 && j > -1 && j < this.v && i > -1 && i < this.s) {
                                k[n4 - n9 + (this.p - 1 - n10) * this.bx] = f[n5 - j + (this.s - 1 - i) * this.v];
                            }
                        }
                    }
                }
                else {
                    final int n11 = j;
                    final int n12 = i + this.bB;
                    if (n11 < this.v && n11 > -1 && n12 < this.p && n12 > -1 && j > -1 && j < this.v && i > -1 && i < this.s) {
                        k[n4 - n11 + (this.p - n12 - 1) * this.bx] = f[n5 - j + (this.s - 1 - i) * this.v];
                    }
                }
            }
        }
    }
    
    public void j() {
        final int n = this.bA + this.bh;
        final int n2 = this.bA + this.m;
        final int n3 = this.m - this.bA - this.bh + this.bB;
        final int n4 = this.bx - 1;
        final int n5 = this.v - 1;
        final int[] k = this.k;
        final int[] f = this.f;
        final int[] bj = this.bj;
        final int[] bk = this.bk;
        for (int i = this.p - 1; i > -1; --i) {
            for (int j = 0; j < this.bx; ++j) {
                if (j - i > this.bA) {
                    if (j - i > n) {
                        final int n6 = i + n2;
                        final int n7 = j + n3;
                        if (n6 < this.v && n6 > -1 && n7 < this.p && n7 > -1 && j > -1 && j < this.v && i > -1 && i < this.s) {
                            k[n4 - n6 + n7 * this.bx] = f[n5 - j + i * this.v];
                        }
                    }
                    else {
                        final int n8 = j - i - this.bA;
                        if (n8 <= this.bh - 1) {
                            final int n9 = i + this.bA + bj[n8];
                            final int n10 = i + bk[n8] + this.bB;
                            if (n9 < this.v && n9 > -1 && n10 < this.p && n10 > -1 && j > -1 && j < this.v && i > -1 && i < this.s) {
                                k[n4 - n9 + n10 * this.bx] = f[n5 - j + i * this.v];
                            }
                        }
                    }
                }
                else {
                    final int n11 = j;
                    final int n12 = i + this.bB;
                    if (n11 < this.v && n11 > -1 && n12 < this.p && n12 > -1 && j > -1 && j < this.v && i > -1 && i < this.s) {
                        k[n4 - n11 + n12 * this.bx] = f[n5 - j + i * this.v];
                    }
                }
            }
        }
    }
    
    public final void paint(final Graphics graphics) {
        if (this.q != null) {
            if (this.bd == 1) {
                this.M.drawImage(this.q, 0, 0, this);
            }
            else {
                this.l();
                this.M.drawImage(this.q, 0, 0, this.bb, this.ba, this);
            }
            if (this.Q != null) {
                this.k();
            }
            if (!this.n) {
                this.M.setColor(Color.black);
                this.M.drawString(c("\u0014:\u0007~u62H42"), this.bb / 2 - 26 + 1, this.ba / 2 + 2 + 1);
                this.M.setColor(Color.white);
                this.M.drawString(c("\u0014:\u0007~u62H42"), this.bb / 2 - 26, this.ba / 2 + 2);
            }
            graphics.drawImage(this.N, 0, 0, this);
        }
    }
    
    public synchronized void k() {
        if (this.b) {
            this.notifyAll();
            while (!this.O) {
                Thread.yield();
                try {
                    Thread.sleep(8L);
                }
                catch (InterruptedException ex) {}
            }
            this.O = false;
        }
        this.M.drawImage(this.Q, this.R, this.S, this);
    }
    
    public synchronized void l() {
        int checkImage = 0;
        this.prepareImage(this.q, this.bb, this.ba, this);
        this.notifyAll();
        while ((checkImage & 0xF0) == 0x0) {
            Thread.yield();
            checkImage = this.checkImage(this.q, this.bb, this.ba, this);
        }
    }
    
    public synchronized void m() {
        int checkImage = 0;
        this.prepareImage(this.r[0], this.bb, this.ba, this);
        this.notifyAll();
        while ((checkImage & 0xF0) == 0x0) {
            Thread.yield();
            checkImage = this.checkImage(this.r[0], this.bb, this.ba, this);
        }
    }
    
    public final void n() {
        try {
            if (this.x) {
                this.Z.newPixels();
                return;
            }
            this.Y.startProduction(this.Y.getConsumer());
        }
        catch (NoSuchMethodError noSuchMethodError) {}
    }
    
    public void run() {
        this.bu.setPriority(this.V);
        this.showStatus("");
        this.j = System.currentTimeMillis();
        if (!this.t[0]) {
            this.f();
        }
        this.showStatus("");
        this.bA = this.v;
        System.gc();
        this.repaint();
        final long n = this.T - (System.currentTimeMillis() - this.j);
        if (n > 0L) {
            try {
                Thread.sleep(n);
            }
            catch (InterruptedException ex) {}
        }
        final Graphics graphics = this.getGraphics();
        if (this.Q != null && !this.b) {
            this.b = this.b();
        }
        if (!this.bw[this.i - 1].equalsIgnoreCase("NO")) {
            this.a.setCursor(12);
        }
        else {
            this.a.setCursor(0);
        }
        while (this.bu != null) {
            if (this.by && this.bn > 0) {
                try {
                    Thread.sleep(this.T);
                }
                catch (InterruptedException ex2) {}
                this.by = false;
            }
            ++this.bn;
            try {
                System.arraycopy(this.h, 0, this.k, 0, this.bv);
            }
            catch (ArrayIndexOutOfBoundsException ex3) {}
            catch (ArrayStoreException ex4) {}
            this.d();
            if (this.bo > 0) {
                switch (this.X) {
                    case 1: {
                        this.o();
                        break;
                    }
                    case 2: {
                        this.p();
                        break;
                    }
                    case 3: {
                        this.q();
                        break;
                    }
                    case 4: {
                        this.r();
                        break;
                    }
                }
            }
            else {
                switch (this.X) {
                    case 1: {
                        this.g();
                        break;
                    }
                    case 2: {
                        this.h();
                        break;
                    }
                    case 3: {
                        this.i();
                        break;
                    }
                    case 4: {
                        this.j();
                        break;
                    }
                }
            }
            if (++this.g == this.A) {
                System.gc();
                this.g = 0;
            }
            try {
                this.n();
            }
            catch (NoSuchMethodError noSuchMethodError) {}
            if (this.bd == 1) {
                this.M.drawImage(this.q, 0, 0, this);
            }
            else {
                this.l();
                this.M.drawImage(this.q, 0, 0, this.bb, this.ba, this);
            }
            if (this.Q != null) {
                this.k();
            }
            graphics.drawImage(this.N, 0, 0, this);
            this.s();
        }
    }
    
    byte a(final int n, final int n2, final int n3, final int n4) {
        final int n5 = n - n2;
        if (n5 >= n3) {
            return (byte)n5;
        }
        return (byte)(n4 - (n3 - n5 - 1));
    }
    
    public void o() {
        final int n = this.bA + this.bh;
        final int n2 = this.bA + this.m;
        final int n3 = this.m - this.bA - this.bh + this.bB;
        final float[] bi = this.bi;
        final int[] k = this.k;
        final int[] f = this.f;
        final int[] bj = this.bj;
        final int[] bk = this.bk;
        for (int i = this.p - 1; i > -1; --i) {
            for (int j = 0; j < this.bx; ++j) {
                if (j - i > this.bA) {
                    if (j - i > n) {
                        final int n4 = i + n2;
                        final int n5 = j + n3;
                        if (n4 < this.v && n4 > -1 && n5 < this.p && n5 > -1 && j > -1 && j < this.v && i > -1 && i < this.s) {
                            k[n4 + n5 * this.bx] = f[j + i * this.v];
                        }
                    }
                    else {
                        final int n6 = j - i - this.bA;
                        if (n6 <= this.bh - 1) {
                            final int n7 = i + this.bA + bj[n6];
                            final int n8 = i + bk[n6] + this.bB;
                            if (n7 < this.v && n7 > -1 && n8 < this.p && n8 > -1 && j > -1 && j < this.v && i > -1 && i < this.s) {
                                final int n9 = f[j + i * this.v];
                                k[n7 + n8 * this.bx] = (((int)((n9 & 0xFF0000) * bi[n6]) & 0xFF0000) | ((int)((n9 & 0xFF00) * bi[n6]) & 0xFF00) | ((int)((n9 & 0xFF) * bi[n6]) & 0xFF));
                            }
                        }
                    }
                }
                else {
                    final int n10 = j;
                    final int n11 = i + this.bB;
                    if (n10 < this.v && n10 > -1 && n11 < this.p && n11 > -1 && j > -1 && j < this.v && i > -1 && i < this.s) {
                        k[n10 + n11 * this.bx] = f[j + i * this.v];
                    }
                }
            }
        }
    }
    
    public void p() {
        final int n = this.bA + this.bh;
        final int n2 = this.bA + this.m;
        final int n3 = this.m - this.bA - this.bh + this.bB;
        final float[] bi = this.bi;
        final int[] k = this.k;
        final int[] f = this.f;
        final int[] bj = this.bj;
        final int[] bk = this.bk;
        for (int i = this.p - 1; i > -1; --i) {
            for (int j = 0; j < this.bx; ++j) {
                if (j - i > this.bA) {
                    if (j - i > n) {
                        final int n4 = i + n2;
                        final int n5 = j + n3;
                        if (n4 < this.v && n4 > -1 && n5 < this.p && n5 > -1 && j > -1 && j < this.v && i > -1 && i < this.s) {
                            k[n4 + (this.p - n5 - 1) * this.bx] = f[j + (this.s - 1 - i) * this.v];
                        }
                    }
                    else {
                        final int n6 = j - i - this.bA;
                        if (n6 <= this.bh - 1) {
                            final int n7 = i + this.bA + bj[n6];
                            final int n8 = i + bk[n6] + this.bB;
                            if (n7 < this.v && n7 > -1 && n8 < this.p && n8 > -1 && j > -1 && j < this.v && i > -1 && i < this.s) {
                                final int n9 = f[j + (this.s - 1 - i) * this.v];
                                k[n7 + (this.p - 1 - n8) * this.bx] = (((int)((n9 & 0xFF0000) * bi[n6]) & 0xFF0000) | ((int)((n9 & 0xFF00) * bi[n6]) & 0xFF00) | ((int)((n9 & 0xFF) * bi[n6]) & 0xFF));
                            }
                        }
                    }
                }
                else {
                    final int n10 = j;
                    final int n11 = i + this.bB;
                    if (n10 < this.v && n10 > -1 && n11 < this.p && n11 > -1 && j > -1 && j < this.v && i > -1 && i < this.s) {
                        k[n10 + (this.p - n11 - 1) * this.bx] = f[j + (this.s - 1 - i) * this.v];
                    }
                }
            }
        }
    }
    
    public void q() {
        final int n = this.bA + this.bh;
        final int n2 = this.bA + this.m;
        final int n3 = this.m - this.bA - this.bh + this.bB;
        final int n4 = this.bx - 1;
        final int n5 = this.v - 1;
        final float[] bi = this.bi;
        final int[] k = this.k;
        final int[] f = this.f;
        final int[] bj = this.bj;
        final int[] bk = this.bk;
        for (int i = this.p - 1; i > -1; --i) {
            for (int j = 0; j < this.bx; ++j) {
                if (j - i > this.bA) {
                    if (j - i > n) {
                        final int n6 = i + n2;
                        final int n7 = j + n3;
                        if (n6 < this.v && n6 > -1 && n7 < this.p && n7 > -1 && j > -1 && j < this.v && i > -1 && i < this.s) {
                            k[n4 - n6 + (this.p - 1 - n7) * this.bx] = f[n5 - j + (this.s - 1 - i) * this.v];
                        }
                    }
                    else {
                        final int n8 = j - i - this.bA;
                        if (n8 <= this.bh - 1) {
                            final int n9 = i + this.bA + bj[n8];
                            final int n10 = i + bk[n8] + this.bB;
                            if (n9 < this.v && n9 > -1 && n10 < this.p && n10 > -1 && j > -1 && j < this.v && i > -1 && i < this.s) {
                                final int n11 = f[n5 - j + (this.s - 1 - i) * this.v];
                                k[n4 - n9 + (this.p - 1 - n10) * this.bx] = (((int)((n11 & 0xFF0000) * bi[n8]) & 0xFF0000) | ((int)((n11 & 0xFF00) * bi[n8]) & 0xFF00) | ((int)((n11 & 0xFF) * bi[n8]) & 0xFF));
                            }
                        }
                    }
                }
                else {
                    final int n12 = j;
                    final int n13 = i + this.bB;
                    if (n12 < this.v && n12 > -1 && n13 < this.p && n13 > -1 && j > -1 && j < this.v && i > -1 && i < this.s) {
                        k[n4 - n12 + (this.p - n13 - 1) * this.bx] = f[n5 - j + (this.s - 1 - i) * this.v];
                    }
                }
            }
        }
    }
    
    public void r() {
        final int n = this.bA + this.bh;
        final int n2 = this.bA + this.m;
        final int n3 = this.m - this.bA - this.bh + this.bB;
        final int n4 = this.bx - 1;
        final int n5 = this.v - 1;
        final float[] bi = this.bi;
        final int[] k = this.k;
        final int[] f = this.f;
        final int[] bj = this.bj;
        final int[] bk = this.bk;
        for (int i = this.p - 1; i > -1; --i) {
            for (int j = 0; j < this.bx; ++j) {
                if (j - i > this.bA) {
                    if (j - i > n) {
                        final int n6 = i + n2;
                        final int n7 = j + n3;
                        if (n6 < this.v && n6 > -1 && n7 < this.p && n7 > -1 && j > -1 && j < this.v && i > -1 && i < this.s) {
                            k[n4 - n6 + n7 * this.bx] = f[n5 - j + i * this.v];
                        }
                    }
                    else {
                        final int n8 = j - i - this.bA;
                        if (n8 <= this.bh - 1) {
                            final int n9 = i + this.bA + bj[n8];
                            final int n10 = i + bk[n8] + this.bB;
                            if (n9 < this.v && n9 > -1 && n10 < this.p && n10 > -1 && j > -1 && j < this.v && i > -1 && i < this.s) {
                                final int n11 = f[n5 - j + i * this.v];
                                k[n4 - n9 + n10 * this.bx] = (((int)((n11 & 0xFF0000) * bi[n8]) & 0xFF0000) | ((int)((n11 & 0xFF00) * bi[n8]) & 0xFF00) | ((int)((n11 & 0xFF) * bi[n8]) & 0xFF));
                            }
                        }
                    }
                }
                else {
                    final int n12 = j;
                    final int n13 = i + this.bB;
                    if (n12 < this.v && n12 > -1 && n13 < this.p && n13 > -1 && j > -1 && j < this.v && i > -1 && i < this.s) {
                        k[n4 - n12 + n13 * this.bx] = f[n5 - j + i * this.v];
                    }
                }
            }
        }
    }
    
    public void start() {
        if (this.bu == null) {
            (this.bu = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.bu != null && this.bu.isAlive()) {
            this.bu.stop();
        }
        this.bu = null;
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public synchronized void s() {
        Thread.yield();
        this.bt.sync();
        final long n = 10L - (System.currentTimeMillis() - this.j);
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
        this.j = System.currentTimeMillis();
        try {
            Thread.sleep(this.B);
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
                char c = 'X';
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
                                c = 'U';
                                array = (array2 = charArray);
                                n3 = (n4 = n);
                                continue;
                            }
                            c = 'f';
                            array = (array2 = charArray);
                            n3 = (n4 = n);
                            continue;
                        }
                        c = '\u001a';
                        array = (array2 = charArray);
                        n3 = (n4 = n);
                        continue;
                    }
                    c = '\u001c';
                    array = (array2 = charArray);
                    n3 = (n4 = n);
                }
            }
            break;
        }
        return new String(charArray);
    }
}
