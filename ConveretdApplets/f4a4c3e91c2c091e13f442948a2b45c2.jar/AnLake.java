import java.awt.Event;
import java.io.DataInputStream;
import java.awt.FontMetrics;
import java.awt.Container;
import java.awt.image.ImageProducer;
import java.awt.image.ColorModel;
import java.awt.image.DirectColorModel;
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

public class AnLake extends Applet implements Runnable, ImageObserver
{
    anfy a;
    MemoryImageSource b;
    boolean c;
    Toolkit d;
    int e;
    long f;
    Thread g;
    boolean h;
    Lware i;
    int j;
    int k;
    int l;
    int m;
    int n;
    int o;
    int p;
    int q;
    float r;
    private Image s;
    int t;
    int u;
    Frame v;
    int[] w;
    int x;
    int y;
    int z;
    int A;
    int B;
    int C;
    int[] D;
    private Image E;
    private Image F;
    private Graphics G;
    String H;
    String I;
    String J;
    String K;
    String L;
    String M;
    private Image N;
    private Graphics O;
    boolean P;
    boolean Q;
    final String R = "!6YC[\u0014fKV\u001e&'KFQ@\u0005@Z]\u0003/\t\u0007I\u00171\u0007NP\u0006";
    boolean S;
    URL T;
    boolean U;
    float V;
    float W;
    float X;
    float Y;
    float Z;
    float ba;
    float bb;
    float bc;
    float bd;
    float be;
    int bf;
    int bg;
    float bh;
    float bi;
    int bj;
    int bk;
    int bl;
    int bm;
    int bn;
    int bo;
    int bp;
    String bq;
    int br;
    boolean bs;
    boolean bt;
    Color bu;
    Color bv;
    Font bw;
    int bx;
    int by;
    int bz;
    int bA;
    int[] bB;
    int[] bC;
    String[] bD;
    int bE;
    int bF;
    Font[] bG;
    int bH;
    int bI;
    private int bJ;
    int bK;
    int bL;
    int bM;
    int bN;
    int bO;
    int[] bP;
    int bQ;
    int bR;
    float bS;
    float bT;
    int bU;
    int bV;
    String bW;
    public static int bX;
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if (image == this.s) {
            if (n == 16) {
                this.P = true;
            }
            return true;
        }
        return true;
    }
    
    public void destroy() {
        if (this.i != null) {
            this.i.hide();
        }
        this.i = null;
        if (this.s != null) {
            this.s.flush();
        }
        this.s = null;
        if (this.N != null) {
            this.N.flush();
        }
        this.N = null;
        if (this.O != null) {
            this.O.dispose();
        }
        this.O = null;
        System.gc();
    }
    
    public synchronized void prepaniframe() {
        if (this.Q) {
            this.notifyAll();
            while (!this.P) {
                Thread.yield();
                try {
                    Thread.sleep(8L);
                }
                catch (InterruptedException ex) {}
            }
            this.P = false;
        }
        this.O.drawImage(this.s, this.t, this.u, this);
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
            return this.P;
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
                this.showStatus(c(")+HH[@") + s + c("@(F[\u001e\u0006)\\AZA"));
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
            this.showStatus(c("$)G\bJ@4LBQ\u0016#\tXI\u0017hHAX\u00192LNSN%FB\u001e\u00034LKW\u00145\tCW\u000e#\tFP@\u000e}brA"));
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
        final int bx = AnLake.bX;
        this.setLayout(null);
        this.addNotify();
        this.d = this.getToolkit();
        this.bW = this.getParameter(c("\u00132H[K\u0013+ZH"));
        final String parameter;
        final String s = parameter = this.getParameter(c("\u00034LKW\u00145"));
        String c = null;
        Label_0117: {
            Label_0086: {
                Label_0082: {
                    if (bx == 0) {
                        if (parameter == null) {
                            break Label_0082;
                        }
                        final String s2;
                        final String protocol = s2 = s;
                        if (bx != 0) {
                            break Label_0117;
                        }
                    }
                    if (parameter.startsWith(c("!6YC[\u0014fKV\u001e&'KFQ@\u0005@Z]\u0003/\t\u0007I\u00171\u0007NP\u0006"))) {
                        break Label_0086;
                    }
                    this.a();
                    if (bx == 0) {
                        break Label_0086;
                    }
                }
                this.a();
            }
            (this.i = new Lware(this, c(",'BJ\u001e\u00016YC[\u0014"))).hide();
            try {
                final String protocol = this.getDocumentBase().getProtocol();
                c = protocol;
            }
            catch (SecurityException ex) {
                c = c("\u0006/EJ");
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
                int n2 = 0;
                int startsWith2 = 0;
                Label_0236: {
                    Label_0227: {
                        if (bx == 0) {
                            Label_0217: {
                                if (!c.equals(c("\u0006/EJ"))) {
                                    int length;
                                    final int n = length = s3.length();
                                    if (bx == 0) {
                                        if (n < 1) {
                                            break Label_0217;
                                        }
                                        final boolean startsWith;
                                        length = ((startsWith = s3.startsWith(c("\f)JNR"))) ? 1 : 0);
                                    }
                                    if (bx == 0) {
                                        if (n != 0) {
                                            break Label_0217;
                                        }
                                        startsWith2 = (length = (n2 = (s3.equals(c("Qt\u001e\u0001\u000eNv\u0007\u001e")) ? 1 : 0)));
                                        if (bx != 0) {
                                            break Label_0236;
                                        }
                                    }
                                    if (length == 0) {
                                        break Label_0227;
                                    }
                                }
                            }
                            this.S = true;
                        }
                        if (bx == 0) {
                            break Label_0635;
                        }
                    }
                    n2 = (startsWith2 = (s3.startsWith(c("\u00171^\u0001")) ? 1 : 0));
                }
                if (bx == 0) {
                    if (startsWith2 != 0) {
                        s3 = s3.substring(4);
                    }
                    n2 = s3.length();
                }
                final int n4;
                final int n3 = n4 = n2;
                if (bx != 0 || n4 > 0) {
                    final char[] array = new char[n4];
                    s3.getChars(0, n3, array, 0);
                    int n5 = 0;
                    while (true) {
                        while (true) {
                            Label_0315: {
                                if (bx == 0) {
                                    break Label_0315;
                                }
                                final char[] array2 = array;
                                final int n6 = n5;
                                if (bx != 0 || array2[n6] == '0') {
                                    array2[n6] = '1';
                                }
                                n5 += 5;
                            }
                            if (n5 < n3) {
                                continue;
                            }
                            break;
                        }
                        if (bx != 0) {
                            continue;
                        }
                        break;
                    }
                    s3 = new String(array);
                }
                final String s4 = parameter2 = this.getParameter(c("\u0012#NLQ\u0004#"));
                if (bx != 0) {
                    break Label_0644;
                }
                if (parameter2 != null) {
                    final String s5 = s4;
                    if (bx != 0) {
                        break Label_0644;
                    }
                    if (s5.length() > 5) {
                        s4.toLowerCase();
                        int n7 = 1;
                        try {
                            int n8 = 0;
                            while (true) {
                                while (true) {
                                    Label_0410: {
                                        if (bx == 0) {
                                            break Label_0410;
                                        }
                                        if (s4.charAt(n8) == '+') {
                                            ++n7;
                                        }
                                        ++n8;
                                    }
                                    if (n8 < s4.length()) {
                                        continue;
                                    }
                                    break;
                                }
                                if (bx != 0) {
                                    continue;
                                }
                                break;
                            }
                        }
                        catch (StringIndexOutOfBoundsException ex3) {}
                        final int[] array3 = new int[n7];
                        final int n9 = n7;
                        if (bx == 0 && n9 == 1) {
                            array3[0] = s4.length();
                            if (bx != 0) {
                                goto Label_0460;
                            }
                        }
                        else {
                            int n10 = n9;
                            try {
                                int n11 = 0;
                                while (true) {
                                    while (true) {
                                        Label_0496: {
                                            if (bx == 0) {
                                                break Label_0496;
                                            }
                                            if (s4.charAt(n11) == '+') {
                                                array3[n10] = n11;
                                                ++n10;
                                            }
                                            ++n11;
                                        }
                                        if (n11 < s4.length()) {
                                            continue;
                                        }
                                        break;
                                    }
                                    if (bx != 0) {
                                        continue;
                                    }
                                    break;
                                }
                            }
                            catch (StringIndexOutOfBoundsException ex4) {}
                            array3[n10] = s4.length();
                        }
                        final String[] array4 = new String[n7];
                        int n12 = 0;
                        int n13 = 0;
                        int n14;
                        while (true) {
                            while (true) {
                                Label_0576: {
                                    if (bx == 0) {
                                        break Label_0576;
                                    }
                                    try {
                                        array4[n13] = s4.substring(n12, array3[n13]);
                                    }
                                    catch (StringIndexOutOfBoundsException ex5) {}
                                    n12 = array3[n13] + 1;
                                    ++n13;
                                }
                                if (n13 < n7) {
                                    continue;
                                }
                                break;
                            }
                            n14 = 0;
                            if (bx != 0) {
                                continue;
                            }
                            break;
                        }
                        while (true) {
                            Label_0628: {
                                if (bx == 0) {
                                    break Label_0628;
                                }
                                if (s3.equals(this.i.dr(array4[n14], 0, this.S))) {
                                    this.S = true;
                                }
                                ++n14;
                            }
                            if (n14 < n7) {
                                continue;
                            }
                            break;
                        }
                    }
                }
            }
            this.getParameter(c("\u0012#NCW\u000e-"));
        }
        final String s7;
        final String s6 = s7 = parameter2;
        Label_0712: {
            if (bx == 0) {
                if (s7 != null) {
                    final String s8 = s6;
                    if (bx != 0) {
                        break Label_0712;
                    }
                    if (!s8.equalsIgnoreCase(c(".\t"))) {
                        try {
                            this.T = new URL(this.getDocumentBase(), s6);
                        }
                        catch (MalformedURLException ex6) {
                            this.T = null;
                        }
                    }
                }
                this.getParameter(c("\u0012#NA[\u0017 [NS\u0005"));
            }
        }
        if (s7.equalsIgnoreCase(c("9\u0003z"))) {
            this.U = true;
        }
        Container parent = this.getParent();
        while (true) {
            while (true) {
                Label_0753: {
                    if (bx == 0) {
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
            (this.v = (Frame)parent).setCursor(3);
            this.M = this.getParameter(c("-/G|g.\u0005"));
            final Container parent2 = this;
            if (bx != 0) {
                continue;
            }
            break;
        }
        final String m = this.M;
        String s10 = null;
        String j = null;
        Label_0892: {
            if (bx == 0) {
                if (m == null) {
                    this.M = c("Qv");
                }
                this.e = Integer.valueOf(this.M);
                this.H = this.getParameter(c("\t+HH["));
                this.I = this.getParameter(c("\u0012#Z"));
                final String s9;
                j = (s9 = (s10 = this.I));
                if (bx != 0) {
                    break Label_0892;
                }
            }
            if (m == null) {
                this.I = "1";
            }
            this.J = this.getParameter(c("\u0017'_\\N\u0005#M"));
            s10 = (j = this.J);
        }
        String k = null;
        String i = null;
        Label_1073: {
            String s11 = null;
            Label_1008: {
                if (bx == 0) {
                    if (j == null) {
                        this.J = c("Sv");
                    }
                    this.r = (float)(double)Double.valueOf(this.J) / 100.0f;
                    this.J = null;
                    this.J = this.getParameter(c("\u0010#[\\N\u0005%]FH\u0005"));
                    s11 = (s10 = this.J);
                    if (bx != 0) {
                        break Label_1008;
                    }
                }
                if (s10 == null) {
                    this.J = c("Qv");
                }
                this.W = (float)(double)Double.valueOf(this.J);
                this.J = null;
                this.J = this.getParameter(c("\u0006'[X_\u0016/GH"));
                i = (s11 = (k = this.J));
                if (bx != 0) {
                    break Label_1073;
                }
            }
            if (s11 == null) {
                this.J = c("Rv\u0019");
            }
            this.X = (float)(double)Double.valueOf(this.J) / 100.0f;
            this.bc = this.W * this.X;
            this.J = null;
            this.J = this.getParameter(c("\u0017/GK"));
            k = (i = this.J);
        }
        String s14 = null;
        String l = null;
        String s13 = null;
        String s12 = null;
        Label_1184: {
            if (bx == 0) {
                if (i == null) {
                    this.J = c("Qv");
                }
                this.Z = (float)(double)Double.valueOf(this.J) / 10.0f;
                this.J = null;
                this.J = this.getParameter(c("\u0017/GKH\u00014_NR"));
                s12 = (k = (s13 = (l = (s14 = this.J))));
                if (bx != 0) {
                    break Label_1184;
                }
            }
            if (k == null) {
                this.J = "0";
            }
            this.bi = (float)(double)Double.valueOf(this.J) / 1000.0f;
            this.J = null;
            this.J = this.getParameter(c("\u0017/GKH\u00014DFP"));
            s13 = (s12 = (l = (s14 = this.J)));
        }
        if (bx == 0) {
            if (s12 == null) {
                this.J = "0";
            }
            this.ba = (float)(double)Double.valueOf(this.J) / 10.0f;
            this.J = null;
            this.J = this.getParameter(c("\u0017/GKH\u00014DNF"));
            l = (s13 = (s14 = this.J));
        }
        if (bx == 0) {
            if (s13 == null) {
                this.J = "0";
            }
            this.bb = (float)(double)Double.valueOf(this.J) / 10.0f;
            this.J = null;
            this.J = this.getParameter(c("\b'EIL\u0005 EJ]\u0014"));
            s14 = (l = this.J);
        }
        Label_1344: {
            AnLake anLake = null;
            Label_1340: {
                if (bx == 0) {
                    if (l == null) {
                        this.J = c(".\t");
                    }
                    anLake = this;
                    if (bx != 0) {
                        break Label_1340;
                    }
                    s14 = this.J;
                }
                if (s14.equalsIgnoreCase(c("9\u0003z"))) {
                    this.h = true;
                    if (bx == 0) {
                        break Label_1344;
                    }
                }
                anLake = this;
            }
            anLake.h = false;
        }
        this.K = this.getParameter(c("\r#DK[\f'P"));
        this.L = this.getParameter(c("\u00104@@L\t2P"));
        this.k = Integer.valueOf(this.K);
        this.l = Integer.valueOf(this.L);
        int n16;
        final int n15 = n16 = this.k;
        if (bx == 0) {
            if (n15 < 0) {
                this.k = 0;
            }
            final int l2;
            n16 = (l2 = this.l);
        }
        final int n17 = 10;
        Label_1595: {
            AnLake anLake2 = null;
            AnLake anLake3 = null;
            Label_1586: {
                int n20 = 0;
                int n22 = 0;
                Label_1557: {
                    Label_1515: {
                        int q = 0;
                        int n21 = 0;
                        Label_1502: {
                            int n18 = 0;
                            int n19 = 0;
                            Label_1483: {
                                Label_1458: {
                                    if (bx == 0) {
                                        if (n15 > n17) {
                                            this.l = 10;
                                            if (bx == 0) {
                                                break Label_1458;
                                            }
                                        }
                                        n18 = (n16 = this.l);
                                        final boolean b;
                                        n19 = ((b = true) ? 1 : 0);
                                        if (bx != 0) {
                                            break Label_1483;
                                        }
                                    }
                                    if (n16 < n17) {
                                        this.l = 1;
                                    }
                                }
                                this.q = Integer.valueOf(this.I);
                                q = (n18 = (n20 = this.q));
                                n21 = (n19 = (n22 = 8));
                                if (bx != 0) {
                                    break Label_1502;
                                }
                            }
                            if (n18 > n19) {
                                this.q = 8;
                                if (bx == 0) {
                                    break Label_1515;
                                }
                            }
                            n20 = (q = this.q);
                            n22 = (n21 = 1);
                        }
                        if (bx != 0) {
                            break Label_1557;
                        }
                        if (q < n21) {
                            this.q = 1;
                        }
                    }
                    this.m = this.size().width / this.q;
                    this.n = this.size().height / this.q;
                    anLake2 = this;
                    anLake3 = this;
                    if (bx != 0) {
                        break Label_1586;
                    }
                    n20 = (this.h ? 1 : 0);
                    n22 = 1;
                }
                if (n20 == n22) {
                    this.n /= 3;
                    this.n *= 2;
                    if (bx == 0) {
                        break Label_1595;
                    }
                }
                anLake2 = this;
                anLake3 = this;
            }
            anLake2.n = anLake3.n / 2;
        }
        this.bd = this.n / this.W;
        this.be = this.n;
        this.o = this.m * this.q;
        this.p = this.n * this.q;
        this.showStatus(c(",)HKW\u000e!\tFS\u0001!L\u0001\u0010N"));
        this.F = this.a(this.H);
        final String parameter3 = this.getParameter(c("\u000f0L]W\r!"));
        final int equalsIgnoreCase;
        Label_1873: {
            AnLake anLake4 = null;
            Label_1870: {
                if (bx == 0) {
                    if (parameter3 != null) {
                        equalsIgnoreCase = (parameter3.equalsIgnoreCase(c(".\t")) ? 1 : 0);
                        if (bx != 0) {
                            break Label_1873;
                        }
                        if (equalsIgnoreCase == 0) {
                            this.s = this.a(parameter3);
                            anLake4 = this;
                            if (bx != 0) {
                                break Label_1870;
                            }
                            if (this.s != null) {
                                final String parameter4;
                                String s15 = parameter4 = this.getParameter(c("\u000f0L]W\r!q"));
                                if (bx == 0) {
                                    if (parameter4 == null) {
                                        s15 = "0";
                                    }
                                    this.t = Integer.valueOf(s15);
                                    this.getParameter(c("\u000f0L]W\r!p"));
                                }
                                final String s17;
                                String s16 = s17 = parameter4;
                                if (bx != 0 || s17 == null) {
                                    s16 = s17;
                                }
                                this.u = Integer.valueOf(s16);
                            }
                        }
                    }
                    this.C = this.m * this.n;
                    this.B = this.testWaves();
                    this.bf = this.n + this.B;
                    this.bg = this.n + this.B - 1;
                    this.D = new int[this.C + this.m * this.B];
                }
                anLake4 = this;
            }
            final int c2 = anLake4.C;
        }
        final int[] array5 = new int[equalsIgnoreCase];
        int n23 = this.m * this.n;
        AnLake anLake5 = this;
        if (bx == 0) {
            if (this.h) {
                n23 = this.m * (this.n / 2);
            }
            anLake5 = this;
        }
        anLake5.w = new int[n23];
        final PixelGrabber pixelGrabber = new PixelGrabber(this.F, 0, 0, this.m, this.n, array5, 0, this.m);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex7) {}
        int n24 = 0;
    Label_2004_Outer:
        while (true) {
            while (true) {
                Label_2018: {
                    if (bx == 0) {
                        break Label_2018;
                    }
                    try {
                        System.arraycopy(array5, (this.n - 1 - n24) * this.m, this.D, n24 * this.m, this.m);
                    }
                    catch (ArrayIndexOutOfBoundsException ex8) {}
                    catch (ArrayStoreException ex9) {}
                    ++n24;
                }
                if (n24 >= this.n - this.B) {
                    int n25 = 0;
                    n24 = this.n - this.B;
                    if (bx != 0) {
                        if (bx != 0) {
                            continue;
                        }
                    }
                    while (true) {
                        Label_2138: {
                            if (n24 >= this.n) {
                                this.E = this.createImage(new MemoryImageSource(this.m, this.n, new DirectColorModel(24, 16711680, 65280, 255), array5, 0, this.m));
                                this.prepscaled1();
                                try {
                                    this.b();
                                    if (bx != 0) {
                                        break Label_2138;
                                    }
                                }
                                catch (NoSuchMethodError noSuchMethodError) {
                                    this.b();
                                }
                                break;
                            }
                            try {
                                System.arraycopy(array5, (this.n - 1 - n24) * this.m, this.D, (n24 + n25) * this.m, this.m);
                                System.arraycopy(array5, (this.n - 1 - n24) * this.m, this.D, (n24 + n25 + 1) * this.m, this.m);
                                ++n25;
                            }
                            catch (ArrayIndexOutOfBoundsException ex10) {}
                            catch (ArrayStoreException ex11) {}
                        }
                        ++n24;
                    }
                    this.scrollinitial();
                    this.N = this.createImage(this.o, this.p * 2 + this.bU);
                    this.O = this.N.getGraphics();
                    return;
                }
                break;
            }
            continue Label_2004_Outer;
        }
    }
    
    void b() {
        if (this.h) {
            this.n /= 2;
        }
        this.b = new MemoryImageSource(this.m, this.n, new DirectColorModel(24, 16711680, 65280, 255), this.w, 0, this.m);
        String s;
        try {
            s = System.getProperty(c("\n'_N\u0010\u0016#[\\W\u000f("));
        }
        catch (SecurityException ex) {
            s = c("\u0015(B");
        }
        if (!s.startsWith(c("Qh\u0019"))) {
            try {
                this.b.setAnimated(true);
                this.b.setFullBufferUpdates(true);
                this.F = this.createImage(this.b);
                this.b.newPixels();
                this.c = true;
            }
            catch (NoSuchMethodError noSuchMethodError) {
                this.c = false;
            }
        }
        if (!this.c) {
            this.b = null;
            this.a = new anfy(this.m, this.n, new DirectColorModel(24, 16711680, 65280, 255), this.w, 0, this.m);
            this.F = this.createImage(this.a);
        }
        if (this.h) {
            this.n *= 2;
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
        this.g.setPriority(this.l);
        this.showStatus("");
        System.gc();
        this.f = System.currentTimeMillis();
        final Graphics graphics = this.getGraphics();
        if (this.q == 1) {
            this.O.drawImage(this.E, 0, 0, this);
        }
        else {
            this.prepscaledS();
            this.O.drawImage(this.E, 0, 0, this.o, this.p, this);
        }
        if (this.s != null && !this.Q) {
            this.Q = this.CheckAniGIF();
        }
        if (this.T != null) {
            this.v.setCursor(12);
        }
        else {
            this.v.setCursor(0);
        }
        this.i.dr(c("\u0001(OV"), 1, this.S);
        while (this.g != null) {
            this.makeWaves();
            this.V += this.r;
            if (++this.j == this.k) {
                System.gc();
                this.j = 0;
            }
            try {
                this.producefixed();
            }
            catch (NoSuchMethodError noSuchMethodError) {}
            if (this.q == 1) {
                this.O.drawImage(this.F, 0, this.n, this);
                if (this.bs || this.s != null) {
                    this.O.drawImage(this.E, 0, 0, this);
                }
            }
            else {
                this.prepscaled();
                this.O.drawImage(this.F, 0, this.p, this.o, this.p, this);
                if (this.bs || this.s != null) {
                    this.prepscaledS();
                    this.O.drawImage(this.E, 0, 0, this.o, this.p, this);
                }
            }
            if (this.s != null) {
                this.prepaniframe();
            }
            if (this.bs) {
                this.scrolltext(this.O);
            }
            graphics.drawImage(this.N, 0, 0, this);
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
        this.prepareImage(this.F, this.o, this.p, this);
        this.notifyAll();
        while ((checkImage & 0xF0) == 0x0) {
            Thread.yield();
            checkImage = this.checkImage(this.F, this.o, this.p, this);
        }
    }
    
    public synchronized void prepscaledS() {
        int checkImage = 0;
        this.prepareImage(this.E, this.o, this.p, this);
        this.notifyAll();
        while ((checkImage & 0xF0) == 0x0) {
            Thread.yield();
            checkImage = this.checkImage(this.E, this.o, this.p, this);
        }
    }
    
    public synchronized void prepscaled1() {
        int checkImage = 0;
        this.prepareImage(this.E, this);
        this.notifyAll();
        while ((checkImage & 0xF0) == 0x0) {
            Thread.yield();
            checkImage = this.checkImage(this.E, this);
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
        if (this.F != null) {
            if (this.q == 1) {
                this.O.drawImage(this.F, 0, this.n, this);
                this.O.drawImage(this.E, 0, 0, this);
            }
            else {
                this.prepscaled();
                this.O.drawImage(this.F, 0, 0, this.o, this.p, this);
                this.O.drawImage(this.E, 0, 0, this.o, this.p, this);
            }
            if (this.s != null) {
                this.prepaniframe();
            }
            if (this.bs) {
                this.scrolltext(this.O);
            }
            graphics.drawImage(this.N, 0, 0, this);
        }
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public int testWaves() {
        int n = 0;
        if (this.bb > 1.0) {
            this.Y = this.Z * this.bb;
        }
        else {
            this.Y = this.Z;
        }
        final int n2 = this.n;
        final float bd = this.bd;
        final float bc = this.bc;
        final float be = this.be;
        final float y = this.Y;
        final int n3 = this.n - 2;
        for (int i = 0; i < 50; ++i) {
            for (int j = n3; j < n2; ++j) {
                final float n4 = j;
                int n5 = (int)(bd * (n4 + bc) * Math.sin(bd * (be - n4) / (n4 + 1.0) + this.V) / be * y);
                if (j + n5 < 0) {
                    n5 = -j;
                }
                if (n5 > n) {
                    n = n5;
                }
            }
            this.V += 0.12f;
        }
        this.V = 0.0f;
        return n;
    }
    
    public void makeWaves() {
        this.bh += this.bi;
        this.Y = this.Z * this.bh;
        if (this.bh > this.bb || this.bh < this.ba) {
            this.bi = -this.bi;
        }
        final int n = this.n;
        final int m = this.m;
        final int[] w = this.w;
        final int[] d = this.D;
        final float bd = this.bd;
        final float bc = this.bc;
        final float be = this.be;
        final float y = this.Y;
        final int bf = this.bf;
        final int bg = this.bg;
        int n2 = n;
        if (this.h) {
            n2 /= 2;
        }
        for (int i = 0; i < n2; ++i) {
            final float n3 = i;
            int n4 = (int)(bd * (n3 + bc) * Math.sin(bd * (be - n3) / (n3 + 1.0) + this.V) / be * y);
            if (i + n4 < 0) {
                n4 = -i;
            }
            else if (i + n4 >= bf) {
                n4 = bg - i;
            }
            try {
                System.arraycopy(d, (i + n4) * m, w, i * m, m);
            }
            catch (ArrayIndexOutOfBoundsException ex) {}
            catch (ArrayStoreException ex2) {}
        }
    }
    
    public void scrollinitial() {
        this.bs = false;
        final String parameter = this.getParameter(c("\u0014#Q[M\u00034FCR"));
        if (parameter != null && !parameter.equalsIgnoreCase(c(".\t"))) {
            String s = this.getParameter(c("\u0014#Q[J\u00196L"));
            if (s == null) {
                s = c("\b)[FD\u000f(]NR");
            }
            if (s.equals(c("\b)[FD\u000f(]NR"))) {
                this.bE = 0;
            }
            else if (s.equals(c("\u0016#[[W\u0003'E"))) {
                this.bE = 1;
            }
            else if (s.equals(c("\u001a)FBW\u000e!"))) {
                this.bE = 2;
            }
            else if (s.equals(c("\t(_UQ\u000f+@AY"))) {
                this.bE = 3;
            }
            if (this.bE == 0) {
                this.GetTheString(parameter, 0);
                if (this.bq != null) {
                    this.bs = true;
                }
            }
            else {
                this.GetTheString(parameter, 1);
                if (this.bD != null) {
                    this.bs = true;
                }
            }
        }
        if (this.bs) {
            String parameter2 = this.getParameter(c("\u0014#Q[M\u0010#LK"));
            if (parameter2 == null) {
                parameter2 = "0";
            }
            this.br = Integer.valueOf(parameter2);
            String s2 = this.getParameter(c("\u0014#Q[X\u000f(]"));
            if (s2 == null) {
                s2 = c("!4@NR");
            }
            int n = 0;
            if (this.getParameter(c("\u0014#Q[\\\u000f*M")).equalsIgnoreCase(c("9\u0003z"))) {
                ++n;
            }
            String s3 = this.getParameter(c("\u0014#Q[W\u0014'EF]"));
            if (s3 == null) {
                s3 = c(".\t");
            }
            if (s3.equalsIgnoreCase(c("9\u0003z"))) {
                n += 2;
            }
            String s4 = this.getParameter(c("\u0014#Q[M\t<L"));
            if (s4 == null) {
                s4 = c("Qt");
            }
            this.bw = new Font(s2, n, Integer.valueOf(s4));
            if (this.getParameter(c("\u0014#Q[M\b'M@I")).equalsIgnoreCase(c("9\u0003z"))) {
                this.bt = true;
            }
            else {
                this.bt = false;
            }
            this.bu = new Color(Integer.valueOf(this.getParameter(c("4#Q[}\u000f*{"))), Integer.valueOf(this.getParameter(c("4#Q[}\u000f*n"))), Integer.valueOf(this.getParameter(c("4#Q[}\u000f*k"))));
            this.bv = new Color(Integer.valueOf(this.getParameter(c("4#Q[m#)E}"))), Integer.valueOf(this.getParameter(c("4#Q[m#)Eh"))), Integer.valueOf(this.getParameter(c("4#Q[m#)Em"))));
            this.bj = this.size().width;
            this.bk = this.size().height;
            if (this.bE == 0) {
                String parameter3 = this.getParameter(c("\u0014#Q[Q\u0006 ZJJ"));
                if (parameter3 == null) {
                    parameter3 = "0";
                }
                this.bm = Integer.valueOf(parameter3);
                if (this.bm < 0) {
                    this.bm = 0;
                }
                String parameter4 = this.getParameter(c("4#Q[t\u0015+YnS\u0010"));
                if (parameter4 == null) {
                    parameter4 = "0";
                }
                this.bJ = Integer.valueOf(parameter4);
                String parameter5 = this.getParameter(c("4#Q[t\u0015+Y|N\u0004"));
                if (parameter5 == null) {
                    parameter5 = "0";
                }
                this.bM = Integer.valueOf(parameter5);
                String parameter6 = this.getParameter(c("4#Q[m\t(LnS\u0010"));
                if (parameter6 == null) {
                    parameter6 = "0";
                }
                this.bx = Integer.valueOf(parameter6);
                String parameter7 = this.getParameter(c("4#Q[m\t(L|N\u0004"));
                if (parameter7 == null) {
                    parameter7 = "0";
                }
                this.by = Integer.valueOf(parameter7);
                String parameter8 = this.getParameter(c("4#Q[m\t(LnP\u0007*L"));
                if (parameter8 == null) {
                    parameter8 = "0";
                }
                this.bz = Integer.valueOf(parameter8);
                final FontMetrics fontMetrics = this.getGraphics().getFontMetrics(this.bw);
                this.bn = fontMetrics.stringWidth(this.bq);
                this.bo = fontMetrics.getHeight();
                this.bp = fontMetrics.getMaxDescent();
                this.bl = this.bj;
                if (this.bm < this.bo - this.bp) {
                    this.bm = this.bo - this.bp;
                }
                else if (this.bm > this.bk - this.bp) {
                    this.bm = this.bk - this.bp;
                }
                if (this.bx != 0) {
                    this.bB = new int[this.bj + 360];
                    this.bC = new int[this.bj + 360];
                    for (int i = 0; i < this.bj + 360; ++i) {
                        this.bB[i] = (int)(this.bx * Math.sin(this.bz * i * 3.141592653589793 / 180.0)) - this.bo - this.bp + this.bm;
                        this.bC[i] = this.bB[i] - this.p;
                    }
                    this.bA = 360;
                    this.bU = this.bo + this.bp + 1;
                    this.bV = this.bU - 1;
                }
            }
            else {
                if (this.bE == 1) {
                    String s5 = this.getParameter(c("\u0014#Q[H\u00136HL["));
                    if (s5 == null) {
                        s5 = c("Qv");
                    }
                    final int intValue = Integer.valueOf(s5);
                    final FontMetrics fontMetrics2 = this.getGraphics().getFontMetrics(this.bw);
                    this.bO = fontMetrics2.getHeight() + intValue;
                    this.bP = new int[this.bD.length];
                    for (int j = 0; j < this.bD.length; ++j) {
                        this.bP[j] = (this.bj - fontMetrics2.stringWidth(this.bD[j])) / 2;
                    }
                    this.bN = -this.bO;
                    return;
                }
                if (this.bE >= 2) {
                    String parameter9 = this.getParameter(c("\u0014#Q[S\t(O@P\u0014"));
                    if (parameter9 == null) {
                        parameter9 = "2";
                    }
                    this.bH = Integer.valueOf(parameter9);
                    String s6 = this.getParameter(c("\u0014#Q[S\u0001>O@P\u0014"));
                    if (s6 == null) {
                        s6 = c("Wt");
                    }
                    this.bI = Integer.valueOf(s6);
                    this.bF = this.bI - this.bH;
                    this.bw = null;
                    this.bG = new Font[this.bF];
                    int bh = this.bH;
                    for (int k = 0; k < this.bF; ++k) {
                        this.bG[k] = new Font(s2, n, bh++);
                    }
                    this.bS = this.bj / 2.0f;
                    this.bT = this.bk / 2.0f;
                    if (this.bE == 3) {
                        this.bQ = this.bF - 1;
                        return;
                    }
                    this.bQ = 0;
                }
            }
        }
    }
    
    public void scrolltext(final Graphics graphics) {
        switch (this.bE) {
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
        graphics.setFont(this.bw);
        this.bN += this.br;
        if (this.bN > this.bk + this.bD.length * this.bO) {
            this.bN = -this.bO;
        }
        if (this.bt) {
            for (int i = 0; i < this.bD.length; ++i) {
                final String s = this.bD[i];
                final int n = this.bP[i];
                final int n2 = this.bk - this.bN + i * this.bO;
                graphics.setColor(this.bv);
                graphics.drawString(s, n + 1, n2 + 1);
                graphics.setColor(this.bu);
                graphics.drawString(s, n, n2);
            }
            return;
        }
        graphics.setColor(this.bu);
        for (int j = 0; j < this.bD.length; ++j) {
            graphics.drawString(this.bD[j], this.bP[j], this.bk - this.bN + j * this.bO);
        }
    }
    
    public void zoomscroll(final Graphics graphics) {
        final String s = this.bD[this.bR];
        graphics.setFont(this.bG[this.bQ]);
        final FontMetrics fontMetrics = graphics.getFontMetrics(this.bG[this.bQ]);
        final int n = (int)(this.bS - fontMetrics.stringWidth(s) / 2.0f);
        final int n2 = (int)(this.bT + fontMetrics.getHeight() / 4.0f);
        if (this.bt) {
            graphics.setColor(this.bv);
            graphics.drawString(s, n + 1, n2 + 1);
        }
        graphics.setColor(this.bu);
        graphics.drawString(s, n, n2);
        if (this.bE == 3) {
            this.bQ -= this.br;
            if (this.bQ <= 1) {
                this.bQ = this.bF - 1;
                ++this.bR;
                if (this.bR >= this.bD.length) {
                    this.bR = 0;
                }
            }
        }
        else {
            this.bQ += this.br;
            if (this.bQ >= this.bF) {
                this.bQ = 0;
                ++this.bR;
                if (this.bR >= this.bD.length) {
                    this.bR = 0;
                }
            }
        }
    }
    
    public void horizscroll(final Graphics graphics) {
        graphics.setFont(this.bw);
        if (this.bJ == 0) {
            this.bK = this.bm;
        }
        else {
            this.bL += this.bM;
            this.bK = this.bm - (int)Math.abs(this.bJ * Math.sin(this.bL / 90.0 * 3.141592653589793));
        }
        if (this.bx != 0) {
            for (int i = 0; i < this.bj; ++i) {
                final int n = this.bB[this.bA + i];
                graphics.copyArea(i, n, 1, this.bU, 0, this.p - n);
            }
            if (this.bt) {
                graphics.setColor(this.bv);
                graphics.drawString(this.bq, this.bl + 1, this.p + this.bo + 1);
            }
            graphics.setColor(this.bu);
            graphics.drawString(this.bq, this.bl, this.p + this.bo);
            for (int j = 0; j < this.bj; ++j) {
                graphics.copyArea(j, this.p, 1, this.bV, 0, this.bC[this.bA + j]);
            }
            this.bA -= this.by;
            if (this.bA < 0) {
                this.bA += 360;
            }
        }
        else {
            if (this.bt) {
                graphics.setColor(this.bv);
                graphics.drawString(this.bq, this.bl + 1, this.bK + 1);
            }
            graphics.setColor(this.bu);
            graphics.drawString(this.bq, this.bl, this.bK);
        }
        this.bl -= this.br;
        if (this.bl < -this.bn) {
            this.bl = this.bj;
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
                            this.bq = new String(byteArray);
                            return;
                        }
                        catch (NoSuchMethodError noSuchMethodError) {
                            this.bq = new String(byteArray, 0);
                            return;
                        }
                    }
                    int n3 = 1;
                    for (int j = 0; j < n2; ++j) {
                        if (byteArray[j] == 10) {
                            ++n3;
                        }
                    }
                    this.bD = new String[n3 - 1];
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
                                this.bD[l] = new String(byteArray, array2[l], array3[l]);
                            }
                            catch (NoSuchMethodError noSuchMethodError2) {
                                this.bD[l] = new String(byteArray, 0, array2[l], array3[l]);
                            }
                        }
                    }
                    catch (StringIndexOutOfBoundsException ex) {
                        this.bD = null;
                    }
                }
                catch (IOException ex2) {}
            }
            catch (IOException ex3) {}
        }
        catch (MalformedURLException ex4) {}
    }
    
    public final boolean mouseEnter(final Event event, final int n, final int n2) {
        this.showStatus(this.bW);
        return true;
    }
    
    public final boolean mouseExit(final Event event, final int n, final int n2) {
        this.showStatus("");
        return true;
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        final int bx = AnLake.bX;
        AnLake anLake = this;
        Label_0065: {
            if (bx != 0) {
                break Label_0065;
            }
            if (!this.S) {
                this.i.show();
                try {
                    this.i.move(100, 100);
                }
                catch (Exception ex) {}
                this.i.toFront();
                this.i.requestFocus();
                if (bx == 0) {
                    return true;
                }
            }
            try {
                AnLake anLake2 = this;
                anLake = this;
                Label_0084: {
                    if (bx != 0) {
                        break Label_0084;
                    }
                    if (anLake.T == null) {
                        return true;
                    }
                    try {
                        this.i.dck();
                        AnLake anLake3 = this;
                        anLake2 = this;
                        if (bx == 0) {
                            if (anLake2.U) {
                                this.getAppletContext().showDocument(this.T, this.getParameter(c("\u0012#NIL\u0001+LA_\r#")));
                                if (bx == 0) {
                                    return true;
                                }
                            }
                            anLake3 = this;
                        }
                        anLake3.getAppletContext().showDocument(this.T);
                    }
                    catch (Exception ex2) {}
                }
            }
            catch (Exception ex3) {}
        }
        return true;
    }
    
    public AnLake() {
        this.c = false;
        this.h = false;
        this.q = 1;
        this.P = false;
        this.Q = false;
        this.S = false;
        this.U = false;
        this.bh = 1.0f;
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
                    c2 = '`';
                    break;
                }
                case 1: {
                    c2 = 'F';
                    break;
                }
                case 2: {
                    c2 = ')';
                    break;
                }
                case 3: {
                    c2 = '/';
                    break;
                }
                default: {
                    c2 = '>';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
