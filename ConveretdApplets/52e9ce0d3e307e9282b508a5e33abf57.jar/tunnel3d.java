import java.awt.FontMetrics;
import java.awt.Event;
import java.awt.image.ImageProducer;
import java.awt.image.ColorModel;
import java.awt.image.DirectColorModel;
import java.awt.Dimension;
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

public class tunnel3d extends Applet implements Runnable, ImageObserver
{
    private int a;
    Frame b;
    boolean c;
    int d;
    int e;
    int f;
    final String g = "_}\u000bP1j-\u0019EtXl\u0019U;>N\u0012I7}d[\u0014#izU]:x";
    boolean h;
    double i;
    double j;
    boolean k;
    double l;
    double m;
    double n;
    double o;
    int p;
    double q;
    double r;
    double s;
    long t;
    long u;
    int v;
    int[] w;
    int x;
    Font y;
    int z;
    private Graphics A;
    int B;
    int C;
    int D;
    int E;
    int F;
    int G;
    int H;
    int I;
    int J;
    float K;
    float L;
    private Image M;
    private Image N;
    boolean O;
    int P;
    boolean Q;
    float R;
    boolean S;
    int T;
    int U;
    String[] V;
    URL W;
    double X;
    double Y;
    int Z;
    int ba;
    String bb;
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
    int bz;
    int bA;
    boolean bB;
    boolean bC;
    double bD;
    int bE;
    private Graphics bF;
    private Image bG;
    boolean bH;
    int bI;
    int bJ;
    private Image bK;
    int bL;
    int bM;
    boolean bN;
    int bO;
    anfy bP;
    MemoryImageSource bQ;
    int bR;
    int bS;
    boolean bT;
    int bU;
    int bV;
    int bW;
    String bX;
    int bY;
    int bZ;
    int ca;
    boolean cb;
    Color cc;
    int cd;
    int ce;
    int cf;
    int cg;
    int ch;
    int ci;
    int[] cj;
    int[] ck;
    int[] cl;
    double cm;
    double cn;
    String co;
    double cp;
    double cq;
    double cr;
    Color cs;
    int ct;
    int cu;
    int cv;
    boolean cw;
    boolean cx;
    int cy;
    int cz;
    Toolkit cA;
    Thread cB;
    int cC;
    int[] cD;
    int cE;
    int cF;
    int cG;
    int cH;
    int cI;
    int cJ;
    int cK;
    int cL;
    double cM;
    double cN;
    double cO;
    double cP;
    double cQ;
    double cR;
    double cS;
    double cT;
    double cU;
    double cV;
    double cW;
    double cX;
    double cY;
    double cZ;
    double da;
    short[] db;
    short[] dc;
    short[] dd;
    int[] de;
    int df;
    Lware dg;
    double dh;
    double di;
    int dj;
    double dk;
    double dl;
    double dm;
    int dn;
    int do;
    double dp;
    double dq;
    int dr;
    int ds;
    Font[] dt;
    double du;
    
    private final void a() {
        while (true) {
            this.showStatus(c("Zb\u0015\u001b >\u007f\u001eQ;hh[K#i#\u001aR2gg\u001aJ50n\u0014Qt}\u007f\u001eX=j~[P=ph[U:>E/q\u0018?"));
            try {
                Thread.sleep(500L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public synchronized boolean b() {
        this.prepareImage(this.bK, this);
        if (this.Q) {
            for (int i = 0; i < 3; ++i) {
                this.notifyAll();
                Thread.yield();
                try {
                    Thread.sleep(100L);
                }
                catch (InterruptedException ex) {}
            }
            return this.bH;
        }
        return false;
    }
    
    public void destroy() {
        if (this.bK != null) {
            this.bK.flush();
        }
        this.bK = null;
        if (this.bG != null) {
            this.bG.flush();
        }
        this.bG = null;
        if (this.bF != null) {
            this.bF.dispose();
        }
        this.bF = null;
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
                this.showStatus(c("W`\u001a[1>") + s + c(">c\u0014Htxb\u000eR0?"));
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
                            this.bX = new String(byteArray);
                            return;
                        }
                        catch (NoSuchMethodError noSuchMethodError) {
                            this.bX = new String(byteArray, 0);
                            return;
                        }
                    }
                    int n3 = 1;
                    for (int j = 0; j < n2; ++j) {
                        if (byteArray[j] == 10) {
                            ++n3;
                        }
                    }
                    this.V = new String[n3 - 1];
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
                                this.V[l] = new String(byteArray, array2[l], array3[l]);
                            }
                            catch (NoSuchMethodError noSuchMethodError2) {
                                this.V[l] = new String(byteArray, 0, array2[l], array3[l]);
                            }
                        }
                    }
                    catch (StringIndexOutOfBoundsException ex) {
                        this.V = null;
                    }
                }
                catch (IOException ex2) {}
            }
            catch (IOException ex3) {}
        }
        catch (MalformedURLException ex4) {}
    }
    
    public void a(final Graphics graphics) {
        graphics.setFont(this.y);
        if (this.a == 0) {
            this.dn = this.do;
        }
        else {
            this.P += this.cG;
            this.dn = this.do - (int)Math.abs(this.a * Math.sin(this.P / 90.0 * 3.141592653589793));
        }
        if (this.ce != 0) {
            for (int i = 0; i < this.cI; ++i) {
                final int n = this.cj[this.cd + i];
                graphics.copyArea(i, n, 1, this.cg, 0, this.bR - n);
            }
            if (this.cx) {
                graphics.setColor(this.cc);
                graphics.drawString(this.bX, this.dj + 1, this.bR + this.bY + 1);
            }
            graphics.setColor(this.cs);
            graphics.drawString(this.bX, this.dj, this.bR + this.bY);
            for (int j = 0; j < this.cI; ++j) {
                graphics.copyArea(j, this.bR, 1, this.ch, 0, this.ck[this.cd + j]);
            }
            this.cd -= this.ci;
            if (this.cd < 0) {
                this.cd += 360;
            }
        }
        else {
            if (this.cx) {
                graphics.setColor(this.cc);
                graphics.drawString(this.bX, this.dj + 1, this.dn + 1);
            }
            graphics.setColor(this.cs);
            graphics.drawString(this.bX, this.dj, this.dn);
        }
        this.dj -= this.cH;
        if (this.dj < -this.bZ) {
            this.dj = this.cI;
        }
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if (image == this.bK) {
            if (n == 16) {
                this.bH = true;
            }
            return true;
        }
        return true;
    }
    
    public void init() {
        this.setLayout(null);
        this.addNotify();
        this.cA = this.getToolkit();
        this.co = this.getParameter(c("my\u001aH!m`\b["));
        final String parameter = this.getParameter(c("}\u007f\u001eX=j~"));
        if (parameter != null) {
            if (!parameter.startsWith(c("_}\u000bP1j-\u0019EtXl\u0019U;>N\u0012I7}d[\u0014#izU]:x"))) {
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
            s = c("xd\u0017Y");
        }
        String s2;
        try {
            s2 = this.getDocumentBase().getHost();
        }
        catch (SecurityException ex2) {
            s2 = "";
        }
        if (s.equalsIgnoreCase(c("xd\u0017Y")) || s2.length() == 0 || s2.equalsIgnoreCase(c("rb\u0018]8vb\bH")) || s2.equals(c("/?L\u0012d0=U\r"))) {
            this.bT = true;
        }
        else {
            if (s2.startsWith(c("iz\f\u0012"))) {
                s2 = s2.substring(4);
            }
            final String parameter2 = this.getParameter(c("lh\u001c_;zh"));
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
                        if (s5.startsWith(c("iz\f\u0012"))) {
                            substring = s5.substring(4);
                        }
                        else {
                            substring = s5;
                        }
                        if (s2.equalsIgnoreCase(substring)) {
                            this.bT = true;
                        }
                    }
                }
            }
        }
        final String parameter3 = this.getParameter(c("lh\u001cP=pf"));
        if (parameter3 != null && !parameter3.equalsIgnoreCase("NO")) {
            try {
                this.W = new URL(this.getDocumentBase(), parameter3);
            }
            catch (MalformedURLException ex6) {
                this.W = null;
            }
        }
        if (this.getParameter(c("lh\u001cR1ik\t]9{")).equalsIgnoreCase(c("GH("))) {
            this.bB = true;
        }
        Container container;
        for (container = this.getParent(); !(container instanceof Frame); container = ((Frame)container).getParent()) {}
        (this.b = (Frame)container).setCursor(3);
        final String parameter4 = this.getParameter(c("q{\u001eN=sj"));
        if (parameter4 != null && !parameter4.equalsIgnoreCase("NO")) {
            this.bK = this.a(parameter4);
            if (this.bK != null) {
                String parameter5 = this.getParameter(c("q{\u001eN=sj#"));
                if (parameter5 == null) {
                    parameter5 = "0";
                }
                this.bL = Integer.valueOf(parameter5);
                String parameter6 = this.getParameter(c("q{\u001eN=sj\""));
                if (parameter6 == null) {
                    parameter6 = "0";
                }
                this.bM = Integer.valueOf(parameter6);
            }
        }
        this.bc = this.getParameter(c("lh\b"));
        if (this.bc == null) {
            this.bc = "1";
        }
        this.bk = this.getParameter(c("me\u001aX1"));
        if (this.bk.equalsIgnoreCase(c("GH("))) {
            this.cb = true;
        }
        else {
            this.cb = false;
        }
        this.bk = null;
        this.bk = this.getParameter(c("rd\u001cT wc\r"));
        if (this.bk.equalsIgnoreCase(c("GH("))) {
            this.S = true;
        }
        else {
            this.S = false;
        }
        this.bl = this.getParameter(c("m|\u000e]&{i"));
        if (this.bl.equalsIgnoreCase(c("GH("))) {
            this.bN = true;
        }
        else {
            this.bN = false;
        }
        this.bm = this.getParameter(c("|h\u0015X'"));
        if (this.bm.equalsIgnoreCase(c("GH("))) {
            this.k = true;
        }
        else {
            this.k = false;
        }
        this.bb = this.getParameter(c("wc\u000fY&\u007fn\u000fU\"{"));
        if (this.bb.equalsIgnoreCase(c("GH("))) {
            this.O = true;
        }
        else {
            this.O = false;
        }
        this.bn = this.getParameter(c("rd\u001cT sd\u0015"));
        if (this.bn == null) {
            this.bn = "1";
        }
        this.bo = this.getParameter(c("rd\u001cT sl\u0003"));
        if (this.bo == null) {
            this.bo = c("/?L");
        }
        this.bp = this.getParameter(c("jx\u0015R1r\u007f\u001aX=k~"));
        if (this.bp == null) {
            this.bp = "20";
        }
        this.bq = this.getParameter(c("jx\u0015N;j"));
        if (this.bq == null) {
            this.bq = "1";
        }
        this.bt = this.getParameter(c("jx\u0015]0hl\u0015_1"));
        if (this.bt == null) {
            this.bt = "2";
        }
        this.bv = this.getParameter(c("f\u007f\u0014H"));
        if (this.bv == null) {
            this.bv = "10";
        }
        this.bx = this.getParameter(c("g\u007f\u0014H"));
        if (this.bx == null) {
            this.bx = "20";
        }
        this.bd = this.getParameter(c("d\u007f\u0014H"));
        if (this.bd == null) {
            this.bd = "30";
        }
        this.cF = Integer.valueOf(this.bq) * 65536;
        this.cC = Integer.valueOf(this.bt) * 65536;
        this.dk = Double.valueOf(this.bv) / 5000.0;
        this.dp = Double.valueOf(this.bx) / 5000.0;
        this.du = Double.valueOf(this.bd) / 5000.0;
        this.br = this.getParameter(c("Sd\u0015o\rPN"));
        if (this.br == null) {
            this.br = "10";
        }
        this.ba = Integer.valueOf(this.br);
        this.bs = this.getParameter(c("px\u0016^1pi\b"));
        if (this.bs == null) {
            this.bs = "3";
        }
        this.bu = this.getParameter(c("|h\u0015X;xk\bY "));
        if (this.bu == null) {
            this.bu = "20";
        }
        this.bw = this.getParameter(c("|h\u0015X'ww\u001eQ=p"));
        if (this.bw == null) {
            this.bw = "0";
        }
        this.by = this.getParameter(c("|h\u0015X'ww\u001eQ5f"));
        if (this.by == null) {
            this.by = "50";
        }
        this.be = this.getParameter(c("|h\u0015X;xk\bY wc\u0018"));
        if (this.be == null) {
            this.be = "2";
        }
        this.bf = this.getParameter(c("|h\u0015X'ww\u001eU:}"));
        if (this.bf == null) {
            this.bf = "2";
        }
        this.bD = Double.valueOf(this.bs);
        this.i = Double.valueOf(this.bu) / 100.0;
        this.o = Double.valueOf(this.bw) / 100.0;
        this.n = Double.valueOf(this.by) / 100.0;
        this.l = this.o;
        this.j = Double.valueOf(this.be) / 100.0;
        this.m = Double.valueOf(this.bf) / 100.0;
        this.U = Integer.valueOf(this.bn);
        this.T = Integer.valueOf(this.bo);
        this.cE = Integer.valueOf(this.bp);
        if (this.U > 127) {
            this.U = 127;
        }
        else if (this.U < 1) {
            this.U = 1;
        }
        if (this.T > 127) {
            this.T = 127;
        }
        else if (this.T < 1) {
            this.T = 1;
        }
        this.T <<= 16;
        this.U <<= 16;
        this.bg = this.getParameter(c("sh\u0016X1rl\u0002"));
        this.bi = this.getParameter(c("n\u007f\u0012S&wy\u0002"));
        this.Z = Integer.valueOf(this.bg);
        this.bO = Integer.valueOf(this.bi);
        if (this.Z < 0) {
            this.Z = 0;
        }
        if (this.bO > 10) {
            this.bO = 10;
        }
        else if (this.bO < 1) {
            this.bO = 1;
        }
        this.bh = this.getParameter(c("rd\u001cT zh\u0018"));
        if (this.bh == null) {
            this.bh = "18";
        }
        this.R = Float.valueOf(this.bh) / 10.0f;
        this.bU = Integer.valueOf(this.bc);
        this.cz = 256;
        this.ct = 256;
        this.cy = 65536;
        if (this.bU > 8) {
            this.bU = 8;
        }
        else if (this.bU < 1) {
            this.bU = 1;
        }
        final Dimension size = this.size();
        this.bS = size.width;
        this.bR = size.height;
        this.f = size.width / this.bU;
        this.d = size.height / this.bU;
        this.df = size.width / this.bU;
        this.J = size.height / this.bU;
        int n14 = this.df - this.df % 8;
        int n15 = this.J - this.J % 8;
        if (n14 > this.df) {
            n14 -= 8;
        }
        if (n15 > this.J) {
            n15 -= 8;
        }
        final int bv = n14;
        this.df = bv;
        this.f = bv;
        this.bV = bv;
        final int bw = n15;
        this.J = bw;
        this.d = bw;
        this.bW = bw;
        this.X = 3.141592653589793;
        this.Y = 6.283185307179586;
        this.H = 1 << this.D;
        this.I = 1 << this.F;
        this.B = this.bV / this.H + 1;
        this.C = this.bW / this.I + 1;
        this.di = 45.0 * (this.X / 360.0);
        this.dm = 30.0 * (this.X / 360.0);
        this.cN = -Math.tan(this.di);
        this.cP = Math.tan(this.dm);
        this.cS = Math.tan(this.di);
        this.cU = Math.tan(this.dm);
        this.cX = -Math.tan(this.di);
        this.cZ = -Math.tan(this.dm);
        this.E = this.D + 8;
        this.G = this.F + 8;
        this.e = this.f * this.d;
        this.bb = this.getParameter(c("w`\u001a[1"));
        this.showStatus(c("Rb\u001aX=pj[U9\u007fj\u001e\u0012z0"));
        this.cl = new int[65536];
        this.w = new int[this.f * this.d];
        this.N = this.a(this.bb);
        final PixelGrabber pixelGrabber = new PixelGrabber(this.N, 0, 0, this.cz, this.ct, this.cl, 0, this.cz);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex7) {}
        this.N.flush();
        this.N = null;
        try {
            this.c();
        }
        catch (NoSuchMethodError noSuchMethodError) {
            this.c();
        }
        if (this.cb) {
            this.cD = new int[this.B * this.C * 3];
        }
        else {
            this.cD = new int[this.B * this.C * 2];
        }
        if (this.cb) {
            this.dd = new short[this.cy];
            this.dc = new short[this.cy];
            this.db = new short[this.cy];
            for (int n16 = 0; n16 < this.cy; ++n16) {
                final int n17 = this.cl[n16];
                this.dd[n16] = (short)(n17 >> 16 & 0xFF);
                this.dc[n16] = (short)(n17 >> 8 & 0xFF);
                this.db[n16] = (short)(n17 & 0xFF);
            }
        }
        this.g();
        this.bG = this.createImage(this.bS, this.bR + this.cg);
        this.bF = this.bG.getGraphics();
        if (!this.bT) {
            (this.dg = new Lware(this.getAppletContext(), new Label(c("Jx\u0015R1r>\u001f\u001c5n}\u0017Y >o\u0002\u001c\u0012\u007fo\u0012St]d\u000e_7w-J\u0005m(\"B\u0004z")))).setTitle(c("Jx\u0015R1r>\u001f\u001c\u0015n}\u0017Y >o\u0002\u001c\u0012\u007fo\u0012St]d\u000e_7w"));
            this.dg.hide();
        }
    }
    
    void c() {
        this.bQ = new MemoryImageSource(this.df, this.J, new DirectColorModel(24, 16711680, 65280, 255), this.w, 0, this.df);
        String s;
        try {
            s = System.getProperty(c("tl\r]zhh\tO=qc"));
        }
        catch (SecurityException ex) {
            s = c("kc\u0010");
        }
        if (!s.startsWith(c("/#K"))) {
            try {
                this.bQ.setAnimated(true);
                this.bQ.setFullBufferUpdates(true);
                this.M = this.createImage(this.bQ);
                this.bQ.newPixels();
                this.Q = true;
            }
            catch (NoSuchMethodError noSuchMethodError) {
                this.Q = false;
            }
        }
        if (!this.Q) {
            this.bQ = null;
            this.bP = new anfy(this.df, this.J, new DirectColorModel(24, 16711680, 65280, 255), this.w, 0, this.df);
            this.M = this.createImage(this.bP);
        }
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        if (!this.bT) {
            this.dg.show();
            this.dg.toFront();
            this.dg.requestFocus();
        }
        else if (this.W != null) {
            if (this.bB) {
                this.getAppletContext().showDocument(this.W, this.getParameter(c("lh\u001cZ&\u007f`\u001eR5sh")));
            }
            else {
                this.getAppletContext().showDocument(this.W);
            }
        }
        return true;
    }
    
    public final boolean mouseEnter(final Event event, final int bi, final int bj) {
        this.bI = bi;
        this.bJ = bj;
        this.showStatus(this.co);
        return true;
    }
    
    public final boolean mouseExit(final Event event, final int n, final int n2) {
        this.showStatus("");
        return true;
    }
    
    public synchronized boolean mouseMove(final Event event, final int bi, final int bj) {
        if (this.O) {
            this.u = System.currentTimeMillis();
            this.bC = true;
            if (bj > this.bJ) {
                this.dh -= (bj - this.bJ) / 400.0;
            }
            else if (bj < this.bJ) {
                this.dh += (this.bJ - bj) / 400.0;
            }
            if (bi > this.bI) {
                this.dl += (bi - this.bI) / 400.0;
            }
            else if (bi < this.bI) {
                this.dl -= (this.bI - bi) / 400.0;
            }
            this.bI = bi;
            this.bJ = bj;
        }
        return true;
    }
    
    public final void paint(final Graphics graphics) {
        if (this.M != null) {
            if (this.bU == 1) {
                this.bF.drawImage(this.M, 0, 0, this);
            }
            else {
                this.e();
                this.bF.drawImage(this.M, 0, 0, this.bS, this.bR, this);
            }
            if (this.bK != null) {
                this.d();
            }
            if (this.cw) {
                this.b(this.bF);
            }
            graphics.drawImage(this.bG, 0, 0, this);
        }
    }
    
    public synchronized void d() {
        if (this.c) {
            this.notifyAll();
            while (!this.bH) {
                Thread.yield();
                try {
                    Thread.sleep(8L);
                }
                catch (InterruptedException ex) {}
            }
            this.bH = false;
        }
        this.bF.drawImage(this.bK, this.bL, this.bM, this);
    }
    
    public synchronized void e() {
        int checkImage = 0;
        this.prepareImage(this.M, this.bS, this.bR, this);
        this.notifyAll();
        while ((checkImage & 0xF0) == 0x0) {
            Thread.yield();
            checkImage = this.checkImage(this.M, this.bS, this.bR, this);
        }
    }
    
    public final void f() {
        try {
            if (this.Q) {
                this.bQ.newPixels();
                return;
            }
            this.bP.startProduction(this.bP.getConsumer());
        }
        catch (NoSuchMethodError noSuchMethodError) {}
    }
    
    public final void a(final double n, final double n2, final double n3) {
        this.cp = Math.sin(n);
        this.cq = Math.sin(n2);
        this.cr = Math.sin(n3);
        this.q = Math.cos(n);
        this.r = Math.cos(n2);
        this.s = Math.cos(n3);
        final double co = this.cO * this.q - this.cQ * this.cp;
        this.cQ = this.cO * this.cp + this.cQ * this.q;
        this.cO = co;
        final double cm = this.cM * this.r - this.cQ * this.cq;
        this.cQ = this.cM * this.cq + this.cQ * this.r;
        this.cM = cm;
        final double cm2 = this.cM * this.s - this.cO * this.cr;
        this.cO = this.cM * this.cr + this.cO * this.s;
        this.cM = cm2;
        final double ct = this.cT * this.q - this.cV * this.cp;
        this.cV = this.cT * this.cp + this.cV * this.q;
        this.cT = ct;
        final double cr = this.cR * this.r - this.cV * this.cq;
        this.cV = this.cR * this.cq + this.cV * this.r;
        this.cR = cr;
        final double cr2 = this.cR * this.s - this.cT * this.cr;
        this.cT = this.cR * this.cr + this.cT * this.s;
        this.cR = cr2;
        final double cy = this.cY * this.q - this.da * this.cp;
        this.da = this.cY * this.cp + this.da * this.q;
        this.cY = cy;
        final double cw = this.cW * this.r - this.da * this.cq;
        this.da = this.cW * this.cq + this.da * this.r;
        this.cW = cw;
        final double cw2 = this.cW * this.s - this.cY * this.cr;
        this.cY = this.cW * this.cr + this.cY * this.s;
        this.cW = cw2;
    }
    
    public void run() {
        this.cB.setPriority(this.bO);
        this.showStatus("");
        System.gc();
        this.t = System.currentTimeMillis();
        final Graphics graphics = this.getGraphics();
        if (this.bK != null && !this.c) {
            this.c = this.b();
        }
        if (this.W != null) {
            this.b.setCursor(12);
        }
        else {
            this.b.setCursor(0);
        }
        while (this.cB != null) {
            if (this.cb) {
                this.l();
            }
            else {
                this.h();
            }
            if (this.bC) {
                if (System.currentTimeMillis() - this.u > 2000L) {
                    this.bC = false;
                }
            }
            else {
                if (this.dl > 0.5) {
                    this.dl = 0.5;
                    this.dp = -this.dp;
                }
                else if (this.dl < -0.5) {
                    this.dl = -0.5;
                    this.dp = -this.dp;
                }
                if (this.dh > 0.4) {
                    this.dh = 0.4;
                    this.dk = -this.dk;
                }
                else if (this.dh < -0.4) {
                    this.dh = -0.4;
                    this.dk = -this.dk;
                }
                this.dh += this.dk;
                this.dl += this.dp;
                this.dq += this.du;
            }
            this.i += this.j;
            if (!this.h) {
                this.l += this.m;
            }
            else {
                this.l -= this.m;
            }
            if (this.l >= this.n) {
                this.h = true;
            }
            else if (this.l <= this.o) {
                this.h = false;
            }
            this.cK += this.cF;
            this.cL += this.cC;
            if (++this.p == this.Z) {
                System.gc();
                this.p = 0;
            }
            try {
                this.f();
            }
            catch (NoSuchMethodError noSuchMethodError) {}
            if (this.bU == 1) {
                this.bF.drawImage(this.M, 0, 0, this);
            }
            else {
                this.e();
                this.bF.drawImage(this.M, 0, 0, this.bS, this.bR, this);
            }
            if (this.bK != null) {
                this.d();
            }
            if (this.cw) {
                this.b(this.bF);
            }
            graphics.drawImage(this.bG, 0, 0, this);
            this.m();
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
        this.cw = false;
        final String parameter = this.getParameter(c("jh\u0003H'}\u007f\u0014P8"));
        if (parameter != null && !parameter.equalsIgnoreCase("NO")) {
            String s = this.getParameter(c("jh\u0003H g}\u001e"));
            if (s == null) {
                s = c("vb\tU.qc\u000f]8");
            }
            if (s.equals(c("vb\tU.qc\u000f]8"))) {
                this.ca = 0;
            }
            else if (s.equals(c("hh\tH=}l\u0017"))) {
                this.ca = 1;
            }
            else if (s.equals(c("db\u0014Q=pj"))) {
                this.ca = 2;
            }
            else if (s.equals(c("wc\rF;q`\u0012R3"))) {
                this.ca = 3;
            }
            if (this.ca == 0) {
                this.a(parameter, 0);
                if (this.bX != null) {
                    this.cw = true;
                }
            }
            else {
                this.a(parameter, 1);
                if (this.V != null) {
                    this.cw = true;
                }
            }
        }
        if (this.cw) {
            String parameter2 = this.getParameter(c("jh\u0003H'nh\u001eX"));
            if (parameter2 == null) {
                parameter2 = "0";
            }
            this.cH = Integer.valueOf(parameter2);
            String s2 = this.getParameter(c("jh\u0003H2qc\u000f"));
            if (s2 == null) {
                s2 = c("_\u007f\u0012]8");
            }
            int n = 0;
            if (this.getParameter(c("jh\u0003H6qa\u001f")).equalsIgnoreCase(c("GH("))) {
                ++n;
            }
            String parameter3 = this.getParameter(c("jh\u0003H=jl\u0017U7"));
            if (parameter3 == null) {
                parameter3 = "NO";
            }
            if (parameter3.equalsIgnoreCase(c("GH("))) {
                n += 2;
            }
            String parameter4 = this.getParameter(c("jh\u0003H'ww\u001e"));
            if (parameter4 == null) {
                parameter4 = "12";
            }
            this.y = new Font(s2, n, Integer.valueOf(parameter4));
            if (this.getParameter(c("jh\u0003H'vl\u001fS#")).equalsIgnoreCase(c("GH("))) {
                this.cx = true;
            }
            else {
                this.cx = false;
            }
            this.cs = new Color(Integer.valueOf(this.getParameter(c("Jh\u0003H\u0017qa)"))), Integer.valueOf(this.getParameter(c("Jh\u0003H\u0017qa<"))), Integer.valueOf(this.getParameter(c("Jh\u0003H\u0017qa9"))));
            this.cc = new Color(Integer.valueOf(this.getParameter(c("Jh\u0003H\u0007]b\u0017n"))), Integer.valueOf(this.getParameter(c("Jh\u0003H\u0007]b\u0017{"))), Integer.valueOf(this.getParameter(c("Jh\u0003H\u0007]b\u0017~"))));
            this.cI = this.size().width;
            this.cJ = this.size().height;
            if (this.ca == 0) {
                String parameter5 = this.getParameter(c("jh\u0003H;xk\bY "));
                if (parameter5 == null) {
                    parameter5 = "0";
                }
                this.do = Integer.valueOf(parameter5);
                if (this.do < 0) {
                    this.do = 0;
                }
                String parameter6 = this.getParameter(c("Jh\u0003H\u001ek`\u000b}9n"));
                if (parameter6 == null) {
                    parameter6 = "0";
                }
                this.a = Integer.valueOf(parameter6);
                String parameter7 = this.getParameter(c("Jh\u0003H\u001ek`\u000bo$z"));
                if (parameter7 == null) {
                    parameter7 = "0";
                }
                this.cG = Integer.valueOf(parameter7);
                String parameter8 = this.getParameter(c("Jh\u0003H\u0007wc\u001e}9n"));
                if (parameter8 == null) {
                    parameter8 = "0";
                }
                this.ce = Integer.valueOf(parameter8);
                String parameter9 = this.getParameter(c("Jh\u0003H\u0007wc\u001eo$z"));
                if (parameter9 == null) {
                    parameter9 = "0";
                }
                this.ci = Integer.valueOf(parameter9);
                String parameter10 = this.getParameter(c("Jh\u0003H\u0007wc\u001e}:ya\u001e"));
                if (parameter10 == null) {
                    parameter10 = "0";
                }
                this.cf = Integer.valueOf(parameter10);
                final FontMetrics fontMetrics = this.getGraphics().getFontMetrics(this.y);
                this.bZ = fontMetrics.stringWidth(this.bX);
                this.bY = fontMetrics.getHeight();
                this.v = fontMetrics.getMaxDescent();
                this.dj = this.cI;
                if (this.do < this.bY - this.v) {
                    this.do = this.bY - this.v;
                }
                else if (this.do > this.cJ - this.v) {
                    this.do = this.cJ - this.v;
                }
                if (this.ce != 0) {
                    this.cj = new int[this.cI + 360];
                    this.ck = new int[this.cI + 360];
                    for (int i = 0; i < this.cI + 360; ++i) {
                        this.cj[i] = (int)(this.ce * Math.sin(this.cf * i * 3.141592653589793 / 180.0)) - this.bY - this.v + this.do;
                        this.ck[i] = this.cj[i] - this.bR;
                    }
                    this.cd = 360;
                    this.cg = this.bY + this.v + 1;
                    this.ch = this.cg - 1;
                }
            }
            else {
                if (this.ca == 1) {
                    String parameter11 = this.getParameter(c("jh\u0003H\"m}\u001a_1"));
                    if (parameter11 == null) {
                        parameter11 = "10";
                    }
                    final int intValue = Integer.valueOf(parameter11);
                    final FontMetrics fontMetrics2 = this.getGraphics().getFontMetrics(this.y);
                    this.z = fontMetrics2.getHeight() + intValue;
                    this.de = new int[this.V.length];
                    for (int j = 0; j < this.V.length; ++j) {
                        this.de[j] = (this.cI - fontMetrics2.stringWidth(this.V[j])) / 2;
                    }
                    this.bA = -this.z;
                    return;
                }
                if (this.ca >= 2) {
                    String parameter12 = this.getParameter(c("jh\u0003H9wc\u001dS:j"));
                    if (parameter12 == null) {
                        parameter12 = "2";
                    }
                    this.cv = Integer.valueOf(parameter12);
                    String parameter13 = this.getParameter(c("jh\u0003H9\u007fu\u001dS:j"));
                    if (parameter13 == null) {
                        parameter13 = "72";
                    }
                    this.cu = Integer.valueOf(parameter13);
                    this.bE = this.cu - this.cv;
                    this.y = null;
                    this.dt = new Font[this.bE];
                    int cv = this.cv;
                    for (int k = 0; k < this.bE; ++k) {
                        this.dt[k] = new Font(s2, n, cv++);
                    }
                    this.L = this.cI / 2.0f;
                    this.K = this.cJ / 2.0f;
                    if (this.ca == 3) {
                        this.ds = this.bE - 1;
                        return;
                    }
                    this.ds = 0;
                }
            }
        }
    }
    
    public void b(final Graphics graphics) {
        switch (this.ca) {
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
        if (this.cB == null) {
            (this.cB = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.cB != null && this.cB.isAlive()) {
            this.cB.stop();
        }
        this.cB = null;
    }
    
    void h() {
        this.a(this.dh, this.dl, this.dq, this.cK, this.cL);
        this.i();
    }
    
    void i() {
        final int n = this.C - 1;
        final int n2 = this.B - 1;
        final int n3 = n2 << 1;
        final int n4 = n3 + 1;
        final int n5 = n3 + 2;
        final int n6 = n3 + 3;
        final int n7 = this.bV * this.I;
        final int n8 = this.bV - this.H;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n2; ++j) {
                int n9 = i * n7 + j * this.H;
                int n10 = i * this.B + j << 1;
                int n11 = this.cD[n10++];
                int n12 = this.cD[n10++];
                int n13 = this.cD[n10];
                int n14 = this.cD[n10 + 1];
                final int n15 = this.cD[n10 + n3] - n11 >> this.F;
                final int n16 = this.cD[n10 + n4] - n12 >> this.F;
                final int n17 = this.cD[n10 + n5] - n13 >> this.F;
                final int n18 = this.cD[n10 + n6] - n14 >> this.F;
                for (int k = 0; k < this.I; ++k) {
                    int n19 = n11 >> 8;
                    int n20 = n12 >> 8;
                    final int n21 = n13 - n11 >> this.E;
                    final int n22 = n14 - n12 >> this.E;
                    for (int l = 0; l < this.H; ++l) {
                        this.w[n9++] = this.cl[((n20 & 0xFF0000) >> 8) + ((n19 & 0xFF0000) >> 16)];
                        n19 += n21;
                        n20 += n22;
                    }
                    n11 += n15;
                    n12 += n16;
                    n13 += n17;
                    n14 += n18;
                    n9 += n8;
                }
            }
        }
    }
    
    void j() {
        final int n = this.C - 1;
        final int n2 = this.B - 1;
        final int n3 = n2 * 3;
        final int n4 = n3 + 1;
        final int n5 = n3 + 2;
        final int n6 = n3 + 3;
        final int n7 = n3 + 4;
        final int n8 = n3 + 5;
        final int n9 = this.bV * this.I;
        final int n10 = this.bV - this.H;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n2; ++j) {
                int n11 = i * n9 + j * this.H;
                int n12 = (i * this.B + j) * 3;
                int n13 = this.cD[n12++];
                int n14 = this.cD[n12++];
                int n15 = this.cD[n12++];
                int n16 = this.cD[n12];
                int n17 = this.cD[n12 + 1];
                int n18 = this.cD[n12 + 2];
                final int n19 = this.cD[n12 + n3] - n13 >> this.F;
                final int n20 = this.cD[n12 + n4] - n14 >> this.F;
                final int n21 = this.cD[n12 + n5] - n15 >> this.F;
                final int n22 = this.cD[n12 + n6] - n16 >> this.F;
                final int n23 = this.cD[n12 + n7] - n17 >> this.F;
                final int n24 = this.cD[n12 + n8] - n18 >> this.F;
                for (int k = 0; k < this.I; ++k) {
                    int n25 = n13 >> 8;
                    int n26 = n14 >> 8;
                    int n27 = n15 >> 8;
                    final int n28 = n16 - n13 >> this.E;
                    final int n29 = n17 - n14 >> this.E;
                    final int n30 = n18 - n15 >> this.E;
                    for (int l = 0; l < this.H; ++l) {
                        final int n31 = ((n26 & 0xFF0000) >> 8) + ((n25 & 0xFF0000) >> 16);
                        final int n32 = (n27 & 0xFF0000) >> 15;
                        this.w[n11++] = (this.dd[n31] * n32 >> 8 << 16 | (this.dc[n31] * n32 & 0xFF00) | this.db[n31] * n32 >> 8);
                        n25 += n28;
                        n26 += n29;
                        n27 += n30;
                    }
                    n13 += n19;
                    n14 += n20;
                    n15 += n21;
                    n16 += n22;
                    n17 += n23;
                    n18 += n24;
                    n11 += n10;
                }
            }
        }
    }
    
    void k() {
        final int n = this.C - 1;
        final int n2 = this.B - 1;
        final int n3 = n2 * 3;
        final int n4 = n3 + 1;
        final int n5 = n3 + 2;
        final int n6 = n3 + 3;
        final int n7 = n3 + 4;
        final int n8 = n3 + 5;
        final int n9 = this.bV * this.I;
        final int n10 = this.bV - this.H;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n2; ++j) {
                int n11 = i * n9 + j * this.H;
                int n12 = (i * this.B + j) * 3;
                int n13 = this.cD[n12++];
                int n14 = this.cD[n12++];
                int n15 = this.cD[n12++];
                int n16 = this.cD[n12];
                int n17 = this.cD[n12 + 1];
                int n18 = this.cD[n12 + 2];
                final int n19 = this.cD[n12 + n3] - n13 >> this.F;
                final int n20 = this.cD[n12 + n4] - n14 >> this.F;
                final int n21 = this.cD[n12 + n5] - n15 >> this.F;
                final int n22 = this.cD[n12 + n6] - n16 >> this.F;
                final int n23 = this.cD[n12 + n7] - n17 >> this.F;
                final int n24 = this.cD[n12 + n8] - n18 >> this.F;
                for (int k = 0; k < this.I; ++k) {
                    int n25 = n13 >> 8;
                    int n26 = n14 >> 8;
                    int n27 = n15 >> 8;
                    final int n28 = n16 - n13 >> this.E;
                    final int n29 = n17 - n14 >> this.E;
                    final int n30 = n18 - n15 >> this.E;
                    for (int l = 0; l < this.H; ++l) {
                        final int n31 = ((n26 & 0xFF0000) >> 8) + ((n25 & 0xFF0000) >> 16);
                        final int n32 = 255 - ((n27 & 0xFF0000) >> 15);
                        int n33 = this.dd[n31] + n32;
                        int n34 = this.dc[n31] + n32;
                        int n35 = this.dc[n31] + n32;
                        if (n33 > 255) {
                            n33 = 255;
                        }
                        if (n34 > 255) {
                            n34 = 255;
                        }
                        if (n35 > 255) {
                            n35 = 255;
                        }
                        this.w[n11++] = (n33 << 16 | n34 << 8 | n35);
                        n25 += n28;
                        n26 += n29;
                        n27 += n30;
                    }
                    n13 += n19;
                    n14 += n20;
                    n15 += n21;
                    n16 += n22;
                    n17 += n23;
                    n18 += n24;
                    n11 += n10;
                }
            }
        }
    }
    
    void a(final double n, final double n2, final double n3, final int n4, final int n5) {
        int n6 = 0;
        this.cM = this.cN;
        this.cO = this.cP;
        this.cQ = 1.0;
        this.cR = this.cS;
        this.cT = this.cU;
        this.cV = 1.0;
        this.cW = this.cX;
        this.cY = this.cZ;
        this.da = 1.0;
        this.a(n, n2, n3);
        final double n7 = (this.cR - this.cM) / this.B;
        final double n8 = (this.cT - this.cO) / this.B;
        final double n9 = (this.cV - this.cQ) / this.B;
        final double n10 = (this.cW - this.cM) / this.C;
        final double n11 = (this.cY - this.cO) / this.C;
        final double n12 = (this.da - this.cQ) / this.C;
        if (this.bN) {
            final double sqrt = Math.sqrt(2.0);
            for (int i = 0; i < this.C; ++i) {
                double cm = this.cM;
                double co = this.cO;
                double cq = this.cQ;
                for (int j = 0; j < this.B; ++j) {
                    this.cD[n6++] = (int)(Math.atan2(co, cm) / this.Y * 1.6777216E7) + n4 << 8;
                    this.cD[n6++] = (int)(cq * (this.cE / (cm * sqrt) * 65536.0)) + n5 << 8;
                    cm += n7;
                    co += n8;
                    cq += n9;
                }
                this.cM += n10;
                this.cO += n11;
                this.cQ += n12;
            }
            return;
        }
        if (this.k) {
            for (int k = 0; k < this.C; ++k) {
                double cm2 = this.cM;
                double co2 = this.cO;
                double cq2 = this.cQ;
                for (int l = 0; l < this.B; ++l) {
                    final double atan2 = Math.atan2(co2, cm2);
                    this.cD[n6++] = (int)(atan2 / this.Y * 1.6777216E7) + n5 << 8;
                    this.cD[n6++] = (int)(cq2 * this.cE / Math.sqrt(cm2 * cm2 + co2 * co2) * (1.0 + Math.sin(atan2 * this.bD + this.i) * this.l) * 65536.0) + n5 << 8;
                    cm2 += n7;
                    co2 += n8;
                    cq2 += n9;
                }
                this.cM += n10;
                this.cO += n11;
                this.cQ += n12;
            }
            return;
        }
        for (int n13 = 0; n13 < this.C; ++n13) {
            double cm3 = this.cM;
            double co3 = this.cO;
            double cq3 = this.cQ;
            for (int n14 = 0; n14 < this.B; ++n14) {
                this.cD[n6++] = (int)(Math.atan2(co3, cm3) / this.Y * 1.6777216E7) + n4 << 8;
                this.cD[n6++] = (int)(cq3 * (this.cE / Math.sqrt(cm3 * cm3 + co3 * co3)) * 65536.0) + n5 << 8;
                cm3 += n7;
                co3 += n8;
                cq3 += n9;
            }
            this.cM += n10;
            this.cO += n11;
            this.cQ += n12;
        }
    }
    
    void b(final double n, final double n2, final double n3, final int n4, final int n5) {
        int n6 = 0;
        this.cM = this.cN;
        this.cO = this.cP;
        this.cQ = 1.0;
        this.cR = this.cS;
        this.cT = this.cU;
        this.cV = 1.0;
        this.cW = this.cX;
        this.cY = this.cZ;
        this.da = 1.0;
        this.a(n, n2, n3);
        final double n7 = (this.cR - this.cM) / this.B;
        final double n8 = (this.cT - this.cO) / this.B;
        final double n9 = (this.cV - this.cQ) / this.B;
        final double n10 = (this.cW - this.cM) / this.C;
        final double n11 = (this.cY - this.cO) / this.C;
        final double n12 = (this.da - this.cQ) / this.C;
        if (this.bN) {
            final double sqrt = Math.sqrt(2.0);
            for (int i = 0; i < this.C; ++i) {
                double cm = this.cM;
                double co = this.cO;
                double cq = this.cQ;
                for (int j = 0; j < this.B; ++j) {
                    final int n13 = (int)(cq * (this.cE / (cm * sqrt) * 65536.0));
                    this.cD[n6++] = (int)(Math.atan2(co, cm) / this.Y * 1.6777216E7) + n4 << 8;
                    this.cD[n6++] = n13 + n5 << 8;
                    int u = this.T - Math.abs((int)(n13 / this.R));
                    if (u < this.U) {
                        u = this.U;
                    }
                    this.cD[n6++] = u << 8;
                    cm += n7;
                    co += n8;
                    cq += n9;
                }
                this.cM += n10;
                this.cO += n11;
                this.cQ += n12;
            }
            return;
        }
        if (this.k) {
            for (int k = 0; k < this.C; ++k) {
                double cm2 = this.cM;
                double co2 = this.cO;
                double cq2 = this.cQ;
                for (int l = 0; l < this.B; ++l) {
                    final double atan2 = Math.atan2(co2, cm2);
                    final int n14 = (int)(cq2 * this.cE / Math.sqrt(cm2 * cm2 + co2 * co2) * (1.0 + Math.sin(atan2 * this.bD + this.i) * this.l) * 65536.0);
                    this.cD[n6++] = (int)(atan2 / this.Y * 1.6777216E7) + n5 << 8;
                    this.cD[n6++] = n14 + n5 << 8;
                    int u2 = this.T - Math.abs((int)(n14 / this.R));
                    if (u2 < this.U) {
                        u2 = this.U;
                    }
                    this.cD[n6++] = u2 << 8;
                    cm2 += n7;
                    co2 += n8;
                    cq2 += n9;
                }
                this.cM += n10;
                this.cO += n11;
                this.cQ += n12;
            }
            return;
        }
        for (int n15 = 0; n15 < this.C; ++n15) {
            double cm3 = this.cM;
            double co3 = this.cO;
            double cq3 = this.cQ;
            for (int n16 = 0; n16 < this.B; ++n16) {
                final int n17 = (int)(cq3 * this.cE / Math.sqrt(cm3 * cm3 + co3 * co3) * 65536.0);
                this.cD[n6++] = (int)(Math.atan2(co3, cm3) / this.Y * 1.6777216E7) + n4 << 8;
                this.cD[n6++] = n17 + n5 << 8;
                int u3 = this.T - Math.abs((int)(n17 / this.R));
                if (u3 < this.U) {
                    u3 = this.U;
                }
                this.cD[n6++] = u3 << 8;
                cm3 += n7;
                co3 += n8;
                cq3 += n9;
            }
            this.cM += n10;
            this.cO += n11;
            this.cQ += n12;
        }
    }
    
    void l() {
        this.b(this.dh, this.dl, this.dq, this.cK, this.cL);
        if (this.S) {
            this.k();
            return;
        }
        this.j();
    }
    
    public tunnel3d() {
        this.Q = false;
        this.h = false;
        this.D = 3;
        this.F = 3;
        this.bU = 1;
        this.bH = false;
        this.c = false;
        this.bT = false;
        this.bB = false;
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void c(final Graphics graphics) {
        graphics.setFont(this.y);
        this.bA += this.cH;
        if (this.bA > this.cJ + this.V.length * this.z) {
            this.bA = -this.z;
        }
        if (this.cx) {
            for (int i = 0; i < this.V.length; ++i) {
                final String s = this.V[i];
                final int n = this.de[i];
                final int n2 = this.cJ - this.bA + i * this.z;
                graphics.setColor(this.cc);
                graphics.drawString(s, n + 1, n2 + 1);
                graphics.setColor(this.cs);
                graphics.drawString(s, n, n2);
            }
            return;
        }
        graphics.setColor(this.cs);
        for (int j = 0; j < this.V.length; ++j) {
            graphics.drawString(this.V[j], this.de[j], this.cJ - this.bA + j * this.z);
        }
    }
    
    public synchronized void m() {
        Thread.yield();
        this.cA.sync();
        final long n = 10L - (System.currentTimeMillis() - this.t);
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
        this.t = System.currentTimeMillis();
        try {
            Thread.sleep(this.ba);
        }
        catch (InterruptedException ex3) {}
    }
    
    public void d(final Graphics graphics) {
        final String s = this.V[this.dr];
        graphics.setFont(this.dt[this.ds]);
        final FontMetrics fontMetrics = graphics.getFontMetrics(this.dt[this.ds]);
        final int n = (int)(this.L - fontMetrics.stringWidth(s) / 2.0f);
        final int n2 = (int)(this.K + fontMetrics.getHeight() / 4.0f);
        if (this.cx) {
            graphics.setColor(this.cc);
            graphics.drawString(s, n + 1, n2 + 1);
        }
        graphics.setColor(this.cs);
        graphics.drawString(s, n, n2);
        if (this.ca == 3) {
            this.ds -= this.cH;
            if (this.ds <= 1) {
                this.ds = this.bE - 1;
                ++this.dr;
                if (this.dr >= this.V.length) {
                    this.dr = 0;
                }
            }
        }
        else {
            this.ds += this.cH;
            if (this.ds >= this.bE) {
                this.ds = 0;
                ++this.dr;
                if (this.dr >= this.V.length) {
                    this.dr = 0;
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
                char c = '\u001e';
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
                                c = '\r';
                                array = (array2 = charArray);
                                n3 = (n4 = n);
                                continue;
                            }
                            c = '{';
                            array = (array2 = charArray);
                            n3 = (n4 = n);
                            continue;
                        }
                        c = '<';
                        array = (array2 = charArray);
                        n3 = (n4 = n);
                        continue;
                    }
                    c = 'T';
                    array = (array2 = charArray);
                    n3 = (n4 = n);
                }
            }
            break;
        }
        return new String(charArray);
    }
}
