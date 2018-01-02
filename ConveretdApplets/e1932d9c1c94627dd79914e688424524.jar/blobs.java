import java.io.DataInputStream;
import java.awt.FontMetrics;
import java.awt.Event;
import java.awt.image.ImageProducer;
import java.awt.image.DirectColorModel;
import java.awt.image.ColorModel;
import java.awt.Dimension;
import java.awt.Container;
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
import java.awt.image.IndexColorModel;
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

public class blobs extends Applet implements Runnable, ImageObserver
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
    double l;
    double m;
    Frame n;
    private Image o;
    int p;
    int q;
    int[] r;
    int s;
    int t;
    int u;
    boolean v;
    boolean w;
    String x;
    String y;
    String z;
    String A;
    String B;
    String C;
    String D;
    String E;
    String F;
    String G;
    String H;
    String I;
    byte[] J;
    byte[] K;
    byte[] L;
    byte[] M;
    private Image N;
    private Image O;
    private Graphics P;
    IndexColorModel Q;
    int R;
    boolean S;
    int[] T;
    int U;
    short[] V;
    int W;
    int X;
    int Y;
    static final int Z = 4;
    int ba;
    int bb;
    int bc;
    int bd;
    int be;
    static final int bf = 6;
    static final int bg = 6;
    int bh;
    int bi;
    blob[] bj;
    int[] bk;
    boolean bl;
    boolean bm;
    boolean bn;
    final String bo = "_\r:\u0015!j](\u0000dX\u001c(\u0010+>>#\f'}\u0014jQ3i\nd\u0018*x";
    boolean bp;
    URL bq;
    boolean br;
    int bs;
    String bt;
    int[] bu;
    int[] bv;
    int[] bw;
    int[] bx;
    double by;
    double bz;
    double bA;
    int bB;
    String bC;
    int bD;
    int bE;
    int bF;
    int bG;
    int bH;
    int bI;
    int bJ;
    String bK;
    int bL;
    boolean bM;
    boolean bN;
    Color bO;
    Color bP;
    Font bQ;
    int bR;
    int bS;
    int bT;
    int bU;
    int[] bV;
    int[] bW;
    String[] bX;
    int bY;
    int bZ;
    Font[] ca;
    int cb;
    int cc;
    private int cd;
    int ce;
    int cf;
    int cg;
    int ch;
    int ci;
    int[] cj;
    int ck;
    int cl;
    float cm;
    float cn;
    int co;
    int cp;
    public static boolean cq;
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if (image == this.o) {
            if (n == 16) {
                this.bl = true;
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
        if (this.o != null) {
            this.o.flush();
        }
        this.o = null;
        if (this.O != null) {
            this.O.flush();
        }
        this.O = null;
        if (this.P != null) {
            this.P.dispose();
        }
        this.P = null;
        System.gc();
    }
    
    public synchronized void prepaniframe() {
        if (this.bm) {
            this.notifyAll();
            while (!this.bl) {
                Thread.yield();
                try {
                    Thread.sleep(8L);
                }
                catch (InterruptedException ex) {}
            }
            this.bl = false;
        }
        this.P.drawImage(this.o, this.p, this.q, this);
    }
    
    public synchronized boolean CheckAniGIF() {
        this.prepareImage(this.o, this);
        if (this.c) {
            for (int i = 0; i < 3; ++i) {
                this.notifyAll();
                Thread.yield();
                try {
                    Thread.sleep(100L);
                }
                catch (InterruptedException ex) {}
            }
            return this.bl;
        }
        return false;
    }
    
    private final void a() {
        while (true) {
            this.showStatus(c("Z\u0012$^0>\u000f/\u0014+h\u0018j\u000e3iS+\u0017\"g\t/\u0018)0\u001e%\u0014d}\u000f/\u001d-j\u000ej\u0015-p\u0018j\u0010*>5\u001e4\b?"));
            try {
                Thread.sleep(500L);
            }
            catch (InterruptedException ex) {}
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
                this.showStatus(c("W\u0010+\u001e!>") + s + c(">\u0013%\rdx\u0012?\u0017 ?"));
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
    
    byte a(final int n, final int n2, final int n3, final int n4) {
        final int n5 = n - n2;
        if (n5 >= n3) {
            return (byte)n5;
        }
        return (byte)(n4 - (n3 - n5 - 1));
    }
    
    public void init() {
        final boolean cq = blobs.cq;
        this.setLayout(null);
        this.addNotify();
        this.bC = this.getParameter(c("m\t+\r1m\u00109\u001e"));
        this.d = this.getToolkit();
        final String parameter;
        final String s = parameter = this.getParameter(c("}\u000f/\u001d-j\u000e"));
        String c = null;
        Label_0130: {
            Label_0099: {
                Label_0095: {
                    if (!cq) {
                        if (parameter == null) {
                            break Label_0095;
                        }
                        final String s2;
                        final String protocol = s2 = s;
                        if (cq) {
                            break Label_0130;
                        }
                    }
                    if (parameter.startsWith(c("_\r:\u0015!j](\u0000dX\u001c(\u0010+>>#\f'}\u0014jQ3i\nd\u0018*x"))) {
                        break Label_0099;
                    }
                    this.a();
                    if (!cq) {
                        break Label_0099;
                    }
                    int a = blob.a;
                    blob.a = ++a;
                }
                this.a();
            }
            (this.h = new Lware(this, c("\\\u0011%\u001b7>\u001c:\t({\t"))).hide();
            try {
                final String protocol = this.getDocumentBase().getProtocol();
                c = protocol;
            }
            catch (SecurityException ex) {
                c = c("x\u0014&\u001c");
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
        Label_0657: {
            Label_0648: {
                int n3 = 0;
                int startsWith = 0;
                Label_0249: {
                    Label_0240: {
                        if (!cq) {
                            Label_0230: {
                                if (!c.equals(c("x\u0014&\u001c"))) {
                                    final int length = s3.length();
                                    int n = 0;
                                    Label_0222: {
                                        if (!cq) {
                                            if (length < 1) {
                                                break Label_0230;
                                            }
                                            final int n2;
                                            n = (n2 = (startsWith = (n3 = (s3.startsWith(c("r\u0012)\u0018(")) ? 1 : 0))));
                                            if (cq) {
                                                break Label_0222;
                                            }
                                        }
                                        if (length != 0) {
                                            break Label_0230;
                                        }
                                        startsWith = (n = (n3 = (s3.equals(c("/O}Wt0MdH")) ? 1 : 0)));
                                    }
                                    if (cq) {
                                        break Label_0249;
                                    }
                                    if (n == 0) {
                                        break Label_0240;
                                    }
                                }
                            }
                            this.bp = true;
                        }
                        if (!cq) {
                            break Label_0648;
                        }
                    }
                    n3 = (startsWith = (s3.startsWith(c("i\n=W")) ? 1 : 0));
                }
                if (!cq) {
                    if (startsWith != 0) {
                        s3 = s3.substring(4);
                    }
                    n3 = s3.length();
                }
                final int n5;
                final int n4 = n5 = n3;
                if (cq || n5 > 0) {
                    final char[] array = new char[n5];
                    s3.getChars(0, n4, array, 0);
                    int n6 = 0;
                    while (true) {
                        while (true) {
                            Label_0328: {
                                if (!cq) {
                                    break Label_0328;
                                }
                                final char[] array2 = array;
                                final int n7 = n6;
                                if (cq || array2[n7] == '0') {
                                    array2[n7] = '1';
                                }
                                n6 += 5;
                            }
                            if (n6 < n4) {
                                continue;
                            }
                            break;
                        }
                        if (cq) {
                            continue;
                        }
                        break;
                    }
                    s3 = new String(array);
                }
                final String s4 = parameter2 = this.getParameter(c("l\u0018-\u001a+z\u0018"));
                if (cq) {
                    break Label_0657;
                }
                if (parameter2 != null) {
                    final String s5 = s4;
                    if (cq) {
                        break Label_0657;
                    }
                    if (s5.length() > 5) {
                        s4.toLowerCase();
                        int n8 = 1;
                        try {
                            int n9 = 0;
                            while (true) {
                                while (true) {
                                    Label_0423: {
                                        if (!cq) {
                                            break Label_0423;
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
                                if (cq) {
                                    continue;
                                }
                                break;
                            }
                        }
                        catch (StringIndexOutOfBoundsException ex3) {}
                        final int[] array3 = new int[n8];
                        final int n10 = n8;
                        if (!cq && n10 == 1) {
                            array3[0] = s4.length();
                            if (cq) {
                                goto Label_0473;
                            }
                        }
                        else {
                            int n11 = n10;
                            try {
                                int n12 = 0;
                                while (true) {
                                    while (true) {
                                        Label_0509: {
                                            if (!cq) {
                                                break Label_0509;
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
                                    if (cq) {
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
                                Label_0589: {
                                    if (!cq) {
                                        break Label_0589;
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
                            if (cq) {
                                continue;
                            }
                            break;
                        }
                        while (true) {
                            Label_0641: {
                                if (!cq) {
                                    break Label_0641;
                                }
                                if (s3.equals(this.h.dr(array4[n15], 0, this.bp))) {
                                    this.bp = true;
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
            this.getParameter(c("l\u0018-\u0015-p\u0016"));
        }
        final String s7;
        final String s6 = s7 = parameter2;
        Label_0725: {
            if (!cq) {
                if (s7 != null) {
                    final String s8 = s6;
                    if (cq) {
                        break Label_0725;
                    }
                    if (!s8.equalsIgnoreCase(c("P2"))) {
                        try {
                            this.bq = new URL(this.getDocumentBase(), s6);
                        }
                        catch (MalformedURLException ex6) {
                            this.bq = null;
                        }
                    }
                }
                this.getParameter(c("l\u0018-\u0017!i\u001b8\u0018){"));
            }
        }
        final String s9 = s7;
        Container parent = null;
        Label_0802: {
            if (!cq) {
                if (s9.equalsIgnoreCase(c("G8\u0019"))) {
                    this.br = true;
                }
                this.bt = this.getParameter(c("S\u0014$*\u001dP>"));
                parent = this;
                if (cq) {
                    break Label_0802;
                }
                final String bt = this.bt;
            }
            if (s9 == null) {
                this.bt = c("/M");
            }
            this.e = Integer.valueOf(this.bt);
            parent = this.getParent();
        }
        Container container = parent;
        while (true) {
            while (true) {
                Label_0819: {
                    if (!cq) {
                        break Label_0819;
                    }
                    final Container parent2 = ((Frame)container).getParent();
                    container = parent2;
                }
                if (!(container instanceof Frame)) {
                    continue;
                }
                break;
            }
            (this.n = (Frame)container).setCursor(3);
            final Container parent2 = this;
            if (cq) {
                continue;
            }
            break;
        }
        final String parameter3;
        final String s10 = parameter3 = this.getParameter(c("q\u000b/\u000b-s\u001a"));
        final String s11;
        final String z;
        Label_1032: {
            if (!cq) {
                Label_1001: {
                    blobs blobs = null;
                    Label_0998: {
                        if (parameter3 != null) {
                            final String c2;
                            final String b;
                            s11 = (b = (c2 = s10));
                            if (cq) {
                                break Label_1001;
                            }
                            if (!s11.equalsIgnoreCase(c("P2"))) {
                                this.o = this.a(s10);
                                blobs = this;
                                if (cq) {
                                    break Label_0998;
                                }
                                if (this.o != null) {
                                    final String parameter4;
                                    String s12 = parameter4 = this.getParameter(c("q\u000b/\u000b-s\u001a\u0012"));
                                    if (!cq) {
                                        if (parameter4 == null) {
                                            s12 = "0";
                                        }
                                        this.p = Integer.valueOf(s12);
                                        this.getParameter(c("q\u000b/\u000b-s\u001a\u0013"));
                                    }
                                    final String s14;
                                    String s13 = s14 = parameter4;
                                    if (cq || s14 == null) {
                                        s13 = s14;
                                    }
                                    this.q = Integer.valueOf(s13);
                                }
                            }
                        }
                        this.z = this.getParameter(c("l\u00189"));
                        blobs = this;
                    }
                    z = blobs.z;
                }
                if (cq) {
                    break Label_1032;
                }
            }
            if (parameter3 == null) {
                this.z = "1";
            }
            this.A = this.getParameter(c("p\u001f&\u0016&m"));
            final String a2 = this.A;
        }
        if (!cq) {
            if (s11 == null) {
                this.A = "3";
            }
            this.B = this.getParameter(c("s\u0014$\n4{\u0018."));
            final String b = this.B;
        }
        Label_1128: {
            blobs blobs2 = null;
            Label_1124: {
                if (!cq) {
                    if (z == null) {
                        this.B = c("/O");
                    }
                    this.C = this.getParameter(c("x\u00142\u0017!j\u000e)\u00184{"));
                    blobs2 = this;
                    if (cq) {
                        break Label_1124;
                    }
                    final String c2 = this.C;
                }
                if (z.equalsIgnoreCase(c("G8\u0019"))) {
                    this.S = true;
                    if (!cq) {
                        break Label_1128;
                    }
                }
                blobs2 = this;
            }
            blobs2.S = false;
        }
        this.G = this.getParameter(c("p\u0018-\u00180w\u000b/"));
        blobs blobs3 = this;
        Label_1176: {
            if (!cq) {
                if (this.G.equalsIgnoreCase(c("G8\u0019"))) {
                    this.v = true;
                    if (!cq) {
                        break Label_1176;
                    }
                }
                blobs3 = this;
            }
            blobs3.v = false;
        }
        this.G = null;
        this.G = this.getParameter(c("m\t+\u000b7v\u001c:\u001c"));
        blobs blobs4 = this;
        Label_1229: {
            if (!cq) {
                if (this.G.equalsIgnoreCase(c("G8\u0019"))) {
                    this.w = true;
                    if (!cq) {
                        break Label_1229;
                    }
                }
                blobs4 = this;
            }
            blobs4.w = false;
        }
        this.I = this.getParameter(c("m\u0014$\u001c)q\u0019/"));
        blobs blobs5 = this;
        Label_1277: {
            if (!cq) {
                if (this.I.equalsIgnoreCase(c("G8\u0019"))) {
                    this.bn = true;
                    if (!cq) {
                        break Label_1277;
                    }
                }
                blobs5 = this;
            }
            blobs5.bn = false;
        }
        this.H = this.getParameter(c("p\b'\t%r"));
        String s16;
        String e;
        final String s15 = e = (s16 = this.H);
        if (!cq) {
            if (s15 == null) {
                this.H = "1";
            }
            this.bs = Integer.valueOf(this.H);
            this.D = this.getParameter(c("|\u000b+\u0015u"));
            final String s17;
            e = (s17 = (s16 = this.D));
        }
        if (!cq) {
            if (s15 == null) {
                this.D = c(",Lz");
            }
            this.E = this.getParameter(c("|\u000b+\u0015v"));
            s16 = (e = this.E);
        }
        blobs blobs6 = null;
        Label_1479: {
            if (!cq) {
                if (e == null) {
                    this.E = c("-H|It");
                }
                this.F = this.getParameter(c("|\u000b+\u0015w"));
                blobs6 = this;
                if (cq) {
                    break Label_1479;
                }
                s16 = this.F;
            }
            if (s16 == null) {
                this.F = c("-H|It");
            }
            this.x = this.getParameter(c("s\u0018'\u001d!r\u001c3"));
            this.y = this.getParameter(c("n\u000f#\u00166w\t3"));
            this.j = Integer.valueOf(this.x);
            this.k = Integer.valueOf(this.y);
            blobs6 = this;
        }
        int n17;
        int k;
        final int n16 = k = (n17 = blobs6.j);
        if (!cq) {
            if (n16 < 0) {
                this.j = 0;
            }
            final int n18;
            k = (n18 = (n17 = this.k));
        }
        int n20;
        final int n19 = n20 = 10;
        int n22 = 0;
        int w = 0;
        int n25 = 0;
        int n24 = 0;
        Label_1671: {
            Label_1665: {
                int n21 = 0;
                int n23 = 0;
                Label_1652: {
                    Label_1633: {
                        Label_1538: {
                            if (!cq) {
                                if (n16 > n19) {
                                    this.k = 10;
                                    if (!cq) {
                                        break Label_1538;
                                    }
                                }
                                n17 = (k = this.k);
                                final boolean b2;
                                n20 = ((b2 = true) ? 1 : 0);
                            }
                            if (cq) {
                                break Label_1633;
                            }
                            if (k < n19) {
                                this.k = 1;
                            }
                        }
                        this.by = Double.valueOf(this.D);
                        this.bz = Double.valueOf(this.E);
                        this.bA = Double.valueOf(this.F);
                        this.u = Integer.valueOf(this.z);
                        this.W = Integer.valueOf(this.A);
                        this.R = Integer.valueOf(this.B);
                        n21 = (n17 = (w = (n22 = this.u)));
                        n23 = (n20 = (n24 = (n25 = 8)));
                        if (cq) {
                            break Label_1652;
                        }
                    }
                    if (n17 > n20) {
                        this.u = 8;
                        if (!cq) {
                            break Label_1665;
                        }
                    }
                    w = (n21 = (n22 = this.u));
                    n24 = (n23 = (n25 = 1));
                }
                if (cq) {
                    break Label_1671;
                }
                if (n21 < n23) {
                    this.u = 1;
                }
            }
            n22 = (w = this.W);
            n25 = (n24 = 8);
        }
        blobs blobs7 = null;
        Label_1752: {
            Label_1751: {
                int r2 = 0;
                int n27 = 0;
                Label_1743: {
                    int r = 0;
                    int n26 = 0;
                    Label_1719: {
                        Label_1708: {
                            if (!cq) {
                                if (w > n24) {
                                    this.W = 8;
                                    if (!cq) {
                                        break Label_1708;
                                    }
                                }
                                r = (n22 = this.W);
                                n26 = (n25 = 2);
                                if (cq) {
                                    break Label_1719;
                                }
                            }
                            if (n22 < n25) {
                                this.W = 2;
                            }
                        }
                        r2 = (r = this.R);
                        n27 = (n26 = 15);
                        if (cq) {
                            break Label_1743;
                        }
                    }
                    if (r > n26) {
                        this.R = 15;
                        if (!cq) {
                            break Label_1751;
                        }
                    }
                    blobs7 = this;
                    if (cq) {
                        break Label_1752;
                    }
                    r2 = this.R;
                    n27 = 1;
                }
                if (r2 < n27) {
                    this.R = 1;
                }
            }
            blobs7 = this;
        }
        final Dimension size = blobs7.size();
        this.s = size.width / this.u;
        this.t = size.height / this.u;
        this.X = this.s * this.u;
        this.Y = this.t * this.u;
        this.bh = this.s - 6;
        this.bi = this.t - 6;
        this.J = new byte[128];
        this.K = new byte[128];
        this.L = new byte[128];
        int n29;
        int s18;
        final int n28 = s18 = (n29 = this.bs);
        if (!cq) {
            Label_1953: {
                switch (n28) {
                    case 1: {
                        this.dopalette1();
                        if (cq) {
                            break Label_1953;
                        }
                        break;
                    }
                    case 2: {
                        this.dopalette2();
                        if (cq) {
                            break Label_1953;
                        }
                        break;
                    }
                    case 3: {
                        this.dopalette3();
                        if (cq) {
                            break Label_1953;
                        }
                        break;
                    }
                    case 4: {
                        this.dopalette4();
                        if (cq) {
                            break Label_1953;
                        }
                        break;
                    }
                    case 5: {
                        this.dopalette5();
                        if (cq) {
                            break Label_1953;
                        }
                        break;
                    }
                    case 6: {
                        this.dopalette6();
                        break;
                    }
                }
            }
            final int n30;
            s18 = (n30 = (n29 = (this.v ? 1 : 0)));
        }
        if (!cq) {
            if (n28 != 0) {
                int n31 = 128;
                final byte[] array5 = new byte[128];
                final byte[] array6 = new byte[128];
                final byte[] array7 = new byte[128];
                int i = 0;
                while (true) {
                    while (true) {
                        Label_2048: {
                            if (!cq) {
                                break Label_2048;
                            }
                            --n31;
                            array5[i] = this.J[n31];
                            array6[i] = this.K[n31];
                            array7[i] = this.L[n31];
                            ++i;
                        }
                        if (i < 128) {
                            continue;
                        }
                        break;
                    }
                    i = 0;
                    if (cq) {
                        if (cq) {
                            continue;
                        }
                    }
                    break;
                }
                while (i < 128) {
                    this.J[i] = array5[i];
                    this.K[i] = array6[i];
                    this.L[i] = array7[i];
                    ++i;
                }
            }
            this.Q = new IndexColorModel(7, 128, this.J, this.K, this.L, 255);
            this.M = new byte[this.s * this.t];
            n29 = (s18 = (this.S ? 1 : 0));
        }
        int n32;
        while (true) {
            Label_2311: {
                if (cq) {
                    break Label_2311;
                }
                Label_2299: {
                    if (s18 != 1) {
                        break Label_2299;
                    }
                    this.U = this.s * this.t;
                    this.T = new int[this.U];
                    this.r = new int[128];
                    this.be = 0;
                    while (true) {
                        Label_2289: {
                            if (!cq) {
                                break Label_2289;
                            }
                            this.r[this.be] = (0xFF000000 | (this.J[this.be] & 0xFF) << 16 | (this.K[this.be] & 0xFF) << 8 | (this.L[this.be] & 0xFF));
                            ++this.be;
                        }
                        if (this.be < 128) {
                            continue;
                        }
                        break;
                    }
                }
                this.bj = new blob[this.W];
                n29 = 0;
            }
            n32 = n29;
            if (cq) {
                if (cq) {
                    continue;
                }
            }
            break;
        }
        while (true) {
            if (n32 >= this.W) {
                this.bk = new int[this.W];
                n32 = 0;
                if (!cq) {
                    break;
                }
            }
            else {
                this.bj[n32] = new blob();
            }
            ++n32;
        }
        Label_2545: {
            blobs blobs9;
            while (true) {
                while (true) {
                    Label_2486: {
                        if (!cq) {
                            break Label_2486;
                        }
                        this.bj[n32].x = (int)(Math.random() * 512.0) % (this.bh + 1) << 4;
                        this.bj[n32].y = (int)(Math.random() * 512.0) % (this.bi + 1) << 4;
                        this.bj[n32].sx = (int)(Math.random() * 512.0) % 4 * 2 + this.R;
                        final blobs blobs8 = this;
                        blobs8.bj[n32].sy = (int)(Math.random() * 512.0) % 4 * 2 + this.R;
                        ++n32;
                    }
                    if (n32 < this.W) {
                        continue;
                    }
                    break;
                }
                this.V = new short[this.s * 2 * (this.t * 2)];
                blobs9 = this;
                final blobs blobs8 = this;
                if (!cq) {
                    if (cq) {
                        continue;
                    }
                    if (this.w) {
                        this.d();
                        if (!cq) {
                            break Label_2545;
                        }
                    }
                    blobs9 = this;
                }
                break;
            }
            blobs9.c();
        }
        this.bu = new int[8];
        this.bv = new int[8];
        this.bw = new int[8];
        this.bx = new int[8];
        this.bu[0] = (int)(this.s / 2.2);
        this.bu[1] = (int)(this.s / 3.5);
        this.bu[2] = (int)(this.s / 2.1);
        this.bu[3] = (int)(this.s / 2.2);
        this.bu[4] = this.s / 2;
        this.bu[5] = (int)(this.s / 1.9);
        this.bu[6] = this.s / 2;
        this.bu[7] = (int)(this.s / 1.3);
        this.bv[0] = this.t / 3;
        this.bv[1] = (int)(this.t / 3.4);
        this.bv[2] = (int)(this.t / 2.5);
        this.bv[3] = (int)(this.t / 2.2);
        this.bv[4] = this.t / 3;
        this.bv[5] = (int)(this.t / 1.6);
        this.bv[6] = this.t / 2;
        this.bv[7] = (int)(this.t / 2.3);
        int n33 = 0;
    Label_2849_Outer:
        while (true) {
            while (true) {
                Label_2852: {
                    if (!cq) {
                        break Label_2852;
                    }
                    final int[] bu = this.bu;
                    final int n34 = n33;
                    bu[n34] *= 22;
                    final int[] bv = this.bv;
                    final int n35 = n33;
                    bv[n35] *= 22;
                    ++n33;
                }
                if (n33 >= 8) {
                    try {
                        this.b();
                        if (cq) {
                            continue;
                        }
                    }
                    catch (NoSuchMethodError noSuchMethodError) {
                        this.b();
                    }
                    this.scrollinitial();
                    this.O = this.createImage(this.X, this.Y + this.co);
                    this.P = this.O.getGraphics();
                    return;
                }
                break;
            }
            continue Label_2849_Outer;
        }
    }
    
    void b() {
        if (!this.S) {
            this.b = new MemoryImageSource(this.s, this.t, this.Q, this.M, 0, this.s);
        }
        else {
            this.b = new MemoryImageSource(this.s, this.t, new DirectColorModel(24, 16711680, 65280, 255), this.T, 0, this.s);
        }
        String s;
        try {
            s = System.getProperty(c("t\u001c<\u0018jh\u00188\n-q\u0013"));
        }
        catch (SecurityException ex) {
            s = c("k\u0013!");
        }
        if (!s.startsWith(c("/Sz"))) {
            try {
                this.b.setAnimated(true);
                this.b.setFullBufferUpdates(true);
                this.N = this.createImage(this.b);
                this.b.newPixels();
                this.c = true;
            }
            catch (NoSuchMethodError noSuchMethodError) {
                this.c = false;
            }
        }
        if (!this.c) {
            this.b = null;
            if (!this.S) {
                this.a = new anfy(this.s, this.t, this.Q, this.M, 0, this.s);
            }
            else {
                this.a = new anfy(this.s, this.t, new DirectColorModel(24, 16711680, 65280, 255), this.T, 0, this.s);
            }
            this.N = this.createImage(this.a);
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
        if (this.o != null && !this.bm) {
            this.bm = this.CheckAniGIF();
        }
        this.f = System.currentTimeMillis();
        final Graphics graphics = this.getGraphics();
        if (this.bq != null) {
            this.n.setCursor(12);
        }
        else {
            this.n.setCursor(0);
        }
        this.h.dr(c("\u007f\u0013,\u0000"), 1, this.bp);
        while (this.g != null) {
            this.doblobs();
            if (++this.i == this.j) {
                System.gc();
                this.i = 0;
            }
            try {
                this.producefixed();
            }
            catch (NoSuchMethodError noSuchMethodError) {}
            if (this.u == 1) {
                this.P.drawImage(this.N, 0, 0, this);
            }
            else {
                this.prepscaled();
                this.P.drawImage(this.N, 0, 0, this.X, this.Y, this);
            }
            if (this.o != null) {
                this.prepaniframe();
            }
            if (this.bM) {
                this.scrolltext(this.P);
            }
            graphics.drawImage(this.O, 0, 0, this);
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
        this.prepareImage(this.N, this.X, this.Y, this);
        this.notifyAll();
        while ((checkImage & 0xF0) == 0x0) {
            Thread.yield();
            checkImage = this.checkImage(this.N, this.X, this.Y, this);
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
        if (this.N != null) {
            if (this.u == 1) {
                this.P.drawImage(this.N, 0, 0, this);
            }
            else {
                this.prepscaled();
                this.P.drawImage(this.N, 0, 0, this.X, this.Y, this);
            }
            if (this.o != null) {
                this.prepaniframe();
            }
            if (this.bM) {
                this.scrolltext(this.P);
            }
            graphics.drawImage(this.O, 0, 0, this);
        }
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final void doblobs() {
        this.be = 0;
        while (this.be < this.W) {
            this.bk[this.be] = (this.bj[this.be].x >> 4) + this.s * 2 * (this.bj[this.be].y >> 4);
            ++this.be;
        }
        switch (this.W) {
            case 2: {
                this.blob2();
                break;
            }
            case 3: {
                this.blob3();
                break;
            }
            case 4: {
                this.blob4();
                break;
            }
            case 5: {
                this.blob5();
                break;
            }
            case 6: {
                this.blob6();
                break;
            }
            case 7: {
                this.blob7();
                break;
            }
            case 8: {
                this.blob8();
                break;
            }
        }
        if (this.S) {
            final int u = this.U;
            final int[] t = this.T;
            final int[] r = this.r;
            final byte[] m = this.M;
            this.be = 0;
            while (this.be < u) {
                t[this.be] = r[m[this.be]];
                ++this.be;
            }
        }
        if (this.bn) {
            this.sinemuovix();
            return;
        }
        this.rimbalza();
    }
    
    public final void rimbalza() {
        this.be = 0;
        while (this.be < this.W) {
            final blob blob = this.bj[this.be];
            blob.x += this.bj[this.be].sx;
            final blob blob2 = this.bj[this.be];
            blob2.y += this.bj[this.be].sy;
            if (this.bj[this.be].x < 96) {
                this.bj[this.be].x = 96;
                this.bj[this.be].sx = -this.bj[this.be].sx;
            }
            else if (this.bj[this.be].x > this.bh << 4) {
                this.bj[this.be].x = this.bh << 4;
                this.bj[this.be].sx = -this.bj[this.be].sx;
            }
            if (this.bj[this.be].y < 96) {
                this.bj[this.be].y = 96;
                this.bj[this.be].sy = -this.bj[this.be].sy;
            }
            else if (this.bj[this.be].y > this.bi << 4) {
                this.bj[this.be].y = this.bi << 4;
                this.bj[this.be].sy = -this.bj[this.be].sy;
            }
            ++this.be;
        }
    }
    
    public final void sinemuovix() {
        ++this.l;
        ++this.m;
        this.bw[0] = (int)(this.bu[0] + this.bu[1] / 3 * Math.sin(this.l * 0.01) * Math.cos(this.m * 0.1));
        this.bx[0] = (int)(this.bv[0] + this.bv[6] / 3 * Math.cos(this.l * 0.09));
        this.bw[1] = (int)(this.bu[1] + this.bu[2] / 3 * Math.sin(this.m * 0.09));
        this.bx[1] = (int)(this.bv[1] + this.bv[5] / 3 * Math.cos(this.l * 0.1));
        this.bw[2] = (int)(this.bu[2] + this.bu[3] / 4 * Math.sin(this.m * 0.09));
        this.bx[2] = (int)(this.bv[2] + this.bv[4] / 4 * Math.cos(this.l * 0.1) * Math.sin(this.l * 0.1));
        this.bw[3] = (int)(this.bu[3] + this.bu[4] / 4 * Math.sin(this.l * 0.1));
        this.bx[3] = (int)(this.bv[3] + this.bv[3] / 4 * Math.cos(this.m * 0.1));
        this.bw[4] = (int)(this.bu[4] + this.bu[5] / 4 * Math.cos(this.l * 0.09) * Math.sin(this.l * 0.09));
        this.bx[4] = (int)(this.bv[4] + this.bv[2] / 4 * Math.cos(this.l * 0.1) * Math.cos(this.l * 0.1));
        this.bw[5] = (int)(this.bu[5] + this.bu[6] / 4 * Math.sin(this.l * 0.1) * Math.sin(this.m * 0.1));
        this.bx[5] = (int)(this.bv[5] + this.bv[1] / 4 * Math.cos(this.l * 0.09));
        this.bw[6] = (int)(this.bu[6] + this.bu[7] / 4 * Math.sin(this.l * 0.09) * Math.cos(this.l * 0.09));
        this.bx[6] = (int)(this.bv[6] + this.bv[0] / 4 * Math.sin(this.l * 0.1) * Math.sin(this.l * 0.1));
        this.bw[7] = (int)(this.bu[7] + this.bu[0] / 4 * Math.cos(this.l * 0.1) * Math.cos(this.l * 0.1));
        this.bx[7] = (int)(this.bv[7] + this.bv[7] / 4 * Math.sin(this.l * 0.09));
        this.be = 0;
        while (this.be < this.W) {
            this.bj[this.be].x = this.bw[this.be];
            this.bj[this.be].y = this.bx[this.be];
            ++this.be;
        }
    }
    
    public final void blob2() {
        int n = 0;
        final int t = this.t;
        final int s = this.s;
        final byte[] m = this.M;
        final short[] v = this.V;
        final int[] bk = this.bk;
        final int w = this.W;
        for (int i = 0; i < t; ++i) {
            for (int j = 0; j < s; ++j) {
                m[n++] = (byte)(v[bk[0]++] + v[bk[1]++] >> 1);
            }
            for (int k = 0; k < w; ++k) {
                final int[] array = bk;
                final int n2 = k;
                array[n2] += s;
            }
        }
    }
    
    public final void blob3() {
        int n = 0;
        final int t = this.t;
        final int s = this.s;
        final byte[] m = this.M;
        final short[] v = this.V;
        final int[] bk = this.bk;
        final int w = this.W;
        for (int i = 0; i < t; ++i) {
            for (int j = 0; j < s; ++j) {
                m[n++] = (byte)(v[bk[0]++] + v[bk[1]++] + v[bk[2]++] >> 2);
            }
            for (int k = 0; k < w; ++k) {
                final int[] array = bk;
                final int n2 = k;
                array[n2] += s;
            }
        }
    }
    
    public final void blob4() {
        int n = 0;
        final int t = this.t;
        final int s = this.s;
        final byte[] m = this.M;
        final short[] v = this.V;
        final int[] bk = this.bk;
        final int w = this.W;
        for (int i = 0; i < t; ++i) {
            for (int j = 0; j < s; ++j) {
                m[n++] = (byte)(v[bk[0]++] + v[bk[1]++] + v[bk[2]++] + v[bk[3]++] >> 2);
            }
            for (int k = 0; k < w; ++k) {
                final int[] array = bk;
                final int n2 = k;
                array[n2] += s;
            }
        }
    }
    
    public final void blob5() {
        int n = 0;
        final int t = this.t;
        final int s = this.s;
        final byte[] m = this.M;
        final short[] v = this.V;
        final int[] bk = this.bk;
        final int w = this.W;
        for (int i = 0; i < t; ++i) {
            for (int j = 0; j < s; ++j) {
                m[n++] = (byte)((v[bk[0]++] + v[bk[1]++] + v[bk[2]++] + v[bk[3]++] + v[bk[4]++]) / 5);
            }
            for (int k = 0; k < w; ++k) {
                final int[] array = bk;
                final int n2 = k;
                array[n2] += s;
            }
        }
    }
    
    public final void blob6() {
        int n = 0;
        final int t = this.t;
        final int s = this.s;
        final byte[] m = this.M;
        final short[] v = this.V;
        final int[] bk = this.bk;
        final int w = this.W;
        for (int i = 0; i < t; ++i) {
            for (int j = 0; j < s; ++j) {
                m[n++] = (byte)((v[bk[0]++] + v[bk[1]++] + v[bk[2]++] + v[bk[3]++] + v[bk[4]++] + v[bk[5]++]) / 6);
            }
            for (int k = 0; k < w; ++k) {
                final int[] array = bk;
                final int n2 = k;
                array[n2] += s;
            }
        }
    }
    
    public final void blob7() {
        int n = 0;
        final int t = this.t;
        final int s = this.s;
        final byte[] m = this.M;
        final short[] v = this.V;
        final int[] bk = this.bk;
        final int w = this.W;
        for (int i = 0; i < t; ++i) {
            for (int j = 0; j < s; ++j) {
                m[n++] = (byte)((v[bk[0]++] + v[bk[1]++] + v[bk[2]++] + v[bk[3]++] + v[bk[4]++] + v[bk[5]++] + v[bk[6]++]) / 7);
            }
            for (int k = 0; k < w; ++k) {
                final int[] array = bk;
                final int n2 = k;
                array[n2] += s;
            }
        }
    }
    
    public final void blob8() {
        int n = 0;
        final int t = this.t;
        final int s = this.s;
        final byte[] m = this.M;
        final short[] v = this.V;
        final int[] bk = this.bk;
        final int w = this.W;
        for (int i = 0; i < t; ++i) {
            for (int j = 0; j < s; ++j) {
                m[n++] = (byte)(v[bk[0]++] + v[bk[1]++] + v[bk[2]++] + v[bk[3]++] + v[bk[4]++] + v[bk[5]++] + v[bk[6]++] + v[bk[7]++] >> 3);
            }
            for (int k = 0; k < w; ++k) {
                final int[] array = bk;
                final int n2 = k;
                array[n2] += s;
            }
        }
    }
    
    public final void dopalette1() {
        int n = 0;
        for (int i = 0; i < 16; ++i) {
            this.J[n] = 0;
            this.K[n] = 0;
            this.L[n++] = 0;
        }
        for (int j = 0; j < 16; ++j) {
            this.J[n] = 0;
            this.K[n] = 0;
            this.L[n++] = (byte)(j * 4);
        }
        for (int k = 0; k < 16; ++k) {
            this.J[n] = 0;
            this.K[n] = 0;
            this.L[n++] = 64;
        }
        for (int l = 0; l < 16; ++l) {
            this.J[n] = 0;
            this.K[n] = (byte)(l * 2);
            this.L[n++] = (byte)(64 + l * 4);
        }
        for (int n2 = 0; n2 < 16; ++n2) {
            this.J[n] = (byte)(n2 * 8);
            this.K[n] = (byte)(32 + n2 * 2);
            this.L[n++] = (byte)(128 - n2 * 4);
        }
        for (int n3 = 0; n3 < 16; ++n3) {
            this.J[n] = (byte)(128 + n3 * 8);
            this.K[n] = (byte)(64 + n3 * 8);
            this.L[n++] = (byte)(64 + n3 * 4);
        }
        for (int n4 = 0; n4 < 16; ++n4) {
            this.J[n] = -1;
            this.K[n] = (byte)(192 + n4 * 4);
            this.L[n++] = (byte)(128 + n4 * 8);
        }
        for (int n5 = 0; n5 < 16; ++n5) {
            this.J[n] = -1;
            this.K[n] = -1;
            this.L[n++] = -1;
        }
    }
    
    public final void dopalette2() {
        int n = 0;
        for (int i = 0; i < 128; ++i) {
            this.J[n] = (byte)(i * 2);
            this.K[n] = (byte)(i * 2);
            this.L[n++] = (byte)(i * 2);
        }
    }
    
    public final void dopalette3() {
        int n = 0;
        for (int i = 0; i < 128; ++i) {
            this.J[n] = (byte)(i / 2);
            this.K[n] = (byte)(i * 2);
            this.L[n++] = (byte)(i / 2);
        }
    }
    
    public final void dopalette4() {
        int n = 0;
        for (int i = 0; i < 128; ++i) {
            this.J[n] = (byte)(i * 2);
            this.K[n] = (byte)(i / 2);
            this.L[n++] = (byte)(i / 2);
        }
    }
    
    public final void dopalette5() {
        int n = 0;
        for (int i = 0; i < 128; ++i) {
            this.J[n] = (byte)(i / 2);
            this.K[n] = (byte)(i / 2);
            this.L[n++] = (byte)(i * 2);
        }
    }
    
    public final void dopalette6() {
        int n = 0;
        for (int i = 0; i < 128; ++i) {
            this.J[n] = (byte)(i * 2);
            this.K[n] = (byte)(i * 2);
            this.L[n++] = (byte)(i / 2);
        }
    }
    
    void c() {
        this.bB = 127;
        int n = 0;
        final double n2 = this.by / Math.log(this.bz);
        for (int i = -this.t; i < this.t; ++i) {
            final double n3 = i * i;
            for (int j = -this.s; j < this.s; ++j) {
                final double n4 = j * j + n3;
                int n5;
                if (n4 > 0.0) {
                    n5 = (int)(Math.log(this.bA / n4) * n2);
                }
                else {
                    n5 = this.bB;
                }
                if (n5 > this.bB) {
                    n5 = this.bB;
                }
                else if (n5 < 0) {
                    n5 = 0;
                }
                this.V[n++] = (short)n5;
            }
        }
    }
    
    void d() {
        this.bB = 127;
        int n = 0;
        final double n2 = this.by / Math.log(this.bz);
        for (int i = -this.t; i < this.t; ++i) {
            final double n3 = Math.abs(i);
            for (int j = -this.s; j < this.s; ++j) {
                final double n4 = Math.abs(j) + n3;
                final double n5 = n4 * n4;
                int n6;
                if (n5 > 0.0) {
                    n6 = (int)(Math.log(this.bA / n5) * n2);
                }
                else {
                    n6 = this.bB;
                }
                if (n6 > this.bB) {
                    n6 = this.bB;
                }
                else if (n6 < 0) {
                    n6 = 0;
                }
                this.V[n++] = (short)n6;
            }
        }
    }
    
    public final boolean mouseEnter(final Event event, final int n, final int n2) {
        this.showStatus(this.bC);
        return true;
    }
    
    public final boolean mouseExit(final Event event, final int n, final int n2) {
        this.showStatus("");
        return true;
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        final boolean cq = blobs.cq;
        blobs blobs = this;
        Label_0065: {
            if (cq) {
                break Label_0065;
            }
            if (!this.bp) {
                this.h.show();
                try {
                    this.h.move(100, 100);
                }
                catch (Exception ex) {}
                this.h.toFront();
                this.h.requestFocus();
                if (!cq) {
                    return true;
                }
            }
            try {
                blobs blobs2 = this;
                blobs = this;
                Label_0084: {
                    if (cq) {
                        break Label_0084;
                    }
                    if (blobs.bq == null) {
                        return true;
                    }
                    try {
                        this.h.dck();
                        blobs blobs3 = this;
                        blobs2 = this;
                        if (!cq) {
                            if (blobs2.br) {
                                this.getAppletContext().showDocument(this.bq, this.getParameter(c("l\u0018-\u001f6\u007f\u0010/\u0017%s\u0018")));
                                if (!cq) {
                                    return true;
                                }
                            }
                            blobs3 = this;
                        }
                        blobs3.getAppletContext().showDocument(this.bq);
                    }
                    catch (Exception ex2) {}
                }
            }
            catch (Exception ex3) {}
        }
        return true;
    }
    
    public void scrollinitial() {
        this.bM = false;
        final String parameter = this.getParameter(c("j\u00182\r7}\u000f%\u0015("));
        if (parameter != null && !parameter.equalsIgnoreCase(c("P2"))) {
            String s = this.getParameter(c("j\u00182\r0g\r/"));
            if (s == null) {
                s = c("v\u00128\u0010>q\u0013>\u0018(");
            }
            if (s.equals(c("v\u00128\u0010>q\u0013>\u0018("))) {
                this.bY = 0;
            }
            else if (s.equals(c("h\u00188\r-}\u001c&"))) {
                this.bY = 1;
            }
            else if (s.equals(c("d\u0012%\u0014-p\u001a"))) {
                this.bY = 2;
            }
            else if (s.equals(c("w\u0013<\u0003+q\u0010#\u0017#"))) {
                this.bY = 3;
            }
            if (this.bY == 0) {
                this.GetTheString(parameter, 0);
                if (this.bK != null) {
                    this.bM = true;
                }
            }
            else {
                this.GetTheString(parameter, 1);
                if (this.bX != null) {
                    this.bM = true;
                }
            }
        }
        if (this.bM) {
            String parameter2 = this.getParameter(c("j\u00182\r7n\u0018/\u001d"));
            if (parameter2 == null) {
                parameter2 = "0";
            }
            this.bL = Integer.valueOf(parameter2);
            String s2 = this.getParameter(c("j\u00182\r\"q\u0013>"));
            if (s2 == null) {
                s2 = c("_\u000f#\u0018(");
            }
            int n = 0;
            if (this.getParameter(c("j\u00182\r&q\u0011.")).equalsIgnoreCase(c("G8\u0019"))) {
                ++n;
            }
            String s3 = this.getParameter(c("j\u00182\r-j\u001c&\u0010'"));
            if (s3 == null) {
                s3 = c("P2");
            }
            if (s3.equalsIgnoreCase(c("G8\u0019"))) {
                n += 2;
            }
            String s4 = this.getParameter(c("j\u00182\r7w\u0007/"));
            if (s4 == null) {
                s4 = c("/O");
            }
            this.bQ = new Font(s2, n, Integer.valueOf(s4));
            if (this.getParameter(c("j\u00182\r7v\u001c.\u00163")).equalsIgnoreCase(c("G8\u0019"))) {
                this.bN = true;
            }
            else {
                this.bN = false;
            }
            this.bO = new Color(Integer.valueOf(this.getParameter(c("J\u00182\r\u0007q\u0011\u0018"))), Integer.valueOf(this.getParameter(c("J\u00182\r\u0007q\u0011\r"))), Integer.valueOf(this.getParameter(c("J\u00182\r\u0007q\u0011\b"))));
            this.bP = new Color(Integer.valueOf(this.getParameter(c("J\u00182\r\u0017]\u0012&+"))), Integer.valueOf(this.getParameter(c("J\u00182\r\u0017]\u0012&>"))), Integer.valueOf(this.getParameter(c("J\u00182\r\u0017]\u0012&;"))));
            this.bD = this.size().width;
            this.bE = this.size().height;
            if (this.bY == 0) {
                String parameter3 = this.getParameter(c("j\u00182\r+x\u001b9\u001c0"));
                if (parameter3 == null) {
                    parameter3 = "0";
                }
                this.bG = Integer.valueOf(parameter3);
                if (this.bG < 0) {
                    this.bG = 0;
                }
                String parameter4 = this.getParameter(c("J\u00182\r\u000ek\u0010:8)n"));
                if (parameter4 == null) {
                    parameter4 = "0";
                }
                this.cd = Integer.valueOf(parameter4);
                String parameter5 = this.getParameter(c("J\u00182\r\u000ek\u0010:*4z"));
                if (parameter5 == null) {
                    parameter5 = "0";
                }
                this.cg = Integer.valueOf(parameter5);
                String parameter6 = this.getParameter(c("J\u00182\r\u0017w\u0013/8)n"));
                if (parameter6 == null) {
                    parameter6 = "0";
                }
                this.bR = Integer.valueOf(parameter6);
                String parameter7 = this.getParameter(c("J\u00182\r\u0017w\u0013/*4z"));
                if (parameter7 == null) {
                    parameter7 = "0";
                }
                this.bS = Integer.valueOf(parameter7);
                String parameter8 = this.getParameter(c("J\u00182\r\u0017w\u0013/8*y\u0011/"));
                if (parameter8 == null) {
                    parameter8 = "0";
                }
                this.bT = Integer.valueOf(parameter8);
                final FontMetrics fontMetrics = this.getGraphics().getFontMetrics(this.bQ);
                this.bH = fontMetrics.stringWidth(this.bK);
                this.bI = fontMetrics.getHeight();
                this.bJ = fontMetrics.getMaxDescent();
                this.bF = this.bD;
                if (this.bG < this.bI - this.bJ) {
                    this.bG = this.bI - this.bJ;
                }
                else if (this.bG > this.bE - this.bJ) {
                    this.bG = this.bE - this.bJ;
                }
                if (this.bR != 0) {
                    this.bV = new int[this.bD + 360];
                    this.bW = new int[this.bD + 360];
                    for (int i = 0; i < this.bD + 360; ++i) {
                        this.bV[i] = (int)(this.bR * Math.sin(this.bT * i * 3.141592653589793 / 180.0)) - this.bI - this.bJ + this.bG;
                        this.bW[i] = this.bV[i] - this.Y;
                    }
                    this.bU = 360;
                    this.co = this.bI + this.bJ + 1;
                    this.cp = this.co - 1;
                }
            }
            else {
                if (this.bY == 1) {
                    String s5 = this.getParameter(c("j\u00182\r2m\r+\u001a!"));
                    if (s5 == null) {
                        s5 = c("/M");
                    }
                    final int intValue = Integer.valueOf(s5);
                    final FontMetrics fontMetrics2 = this.getGraphics().getFontMetrics(this.bQ);
                    this.ci = fontMetrics2.getHeight() + intValue;
                    this.cj = new int[this.bX.length];
                    this.be = 0;
                    while (this.be < this.bX.length) {
                        this.cj[this.be] = (this.bD - fontMetrics2.stringWidth(this.bX[this.be])) / 2;
                        ++this.be;
                    }
                    this.ch = -this.ci;
                    return;
                }
                if (this.bY >= 2) {
                    String parameter9 = this.getParameter(c("j\u00182\r)w\u0013,\u0016*j"));
                    if (parameter9 == null) {
                        parameter9 = "2";
                    }
                    this.cb = Integer.valueOf(parameter9);
                    String s6 = this.getParameter(c("j\u00182\r)\u007f\u0005,\u0016*j"));
                    if (s6 == null) {
                        s6 = c(")O");
                    }
                    this.cc = Integer.valueOf(s6);
                    this.bZ = this.cc - this.cb;
                    this.bQ = null;
                    this.ca = new Font[this.bZ];
                    int cb = this.cb;
                    this.be = 0;
                    while (this.be < this.bZ) {
                        this.ca[this.be] = new Font(s2, n, cb++);
                        ++this.be;
                    }
                    this.cm = this.bD / 2.0f;
                    this.cn = this.bE / 2.0f;
                    if (this.bY == 3) {
                        this.ck = this.bZ - 1;
                        return;
                    }
                    this.ck = 0;
                }
            }
        }
    }
    
    public void scrolltext(final Graphics graphics) {
        switch (this.bY) {
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
        graphics.setFont(this.bQ);
        this.ch += this.bL;
        if (this.ch > this.bE + this.bX.length * this.ci) {
            this.ch = -this.ci;
        }
        if (this.bN) {
            for (int i = 0; i < this.bX.length; ++i) {
                final String s = this.bX[i];
                final int n = this.cj[i];
                final int n2 = this.bE - this.ch + i * this.ci;
                graphics.setColor(this.bP);
                graphics.drawString(s, n + 1, n2 + 1);
                graphics.setColor(this.bO);
                graphics.drawString(s, n, n2);
            }
            return;
        }
        graphics.setColor(this.bO);
        for (int j = 0; j < this.bX.length; ++j) {
            graphics.drawString(this.bX[j], this.cj[j], this.bE - this.ch + j * this.ci);
        }
    }
    
    public void zoomscroll(final Graphics graphics) {
        final String s = this.bX[this.cl];
        graphics.setFont(this.ca[this.ck]);
        final FontMetrics fontMetrics = graphics.getFontMetrics(this.ca[this.ck]);
        final int n = (int)(this.cm - fontMetrics.stringWidth(s) / 2.0f);
        final int n2 = (int)(this.cn + fontMetrics.getHeight() / 4.0f);
        if (this.bN) {
            graphics.setColor(this.bP);
            graphics.drawString(s, n + 1, n2 + 1);
        }
        graphics.setColor(this.bO);
        graphics.drawString(s, n, n2);
        if (this.bY == 3) {
            this.ck -= this.bL;
            if (this.ck <= 1) {
                this.ck = this.bZ - 1;
                ++this.cl;
                if (this.cl >= this.bX.length) {
                    this.cl = 0;
                }
            }
        }
        else {
            this.ck += this.bL;
            if (this.ck >= this.bZ) {
                this.ck = 0;
                ++this.cl;
                if (this.cl >= this.bX.length) {
                    this.cl = 0;
                }
            }
        }
    }
    
    public void horizscroll(final Graphics graphics) {
        graphics.setFont(this.bQ);
        if (this.cd == 0) {
            this.ce = this.bG;
        }
        else {
            this.cf += this.cg;
            this.ce = this.bG - (int)Math.abs(this.cd * Math.sin(this.cf / 90.0 * 3.141592653589793));
        }
        if (this.bR != 0) {
            for (int i = 0; i < this.bD; ++i) {
                final int n = this.bV[this.bU + i];
                graphics.copyArea(i, n, 1, this.co, 0, this.Y - n);
            }
            if (this.bN) {
                graphics.setColor(this.bP);
                graphics.drawString(this.bK, this.bF + 1, this.Y + this.bI + 1);
            }
            graphics.setColor(this.bO);
            graphics.drawString(this.bK, this.bF, this.Y + this.bI);
            for (int j = 0; j < this.bD; ++j) {
                graphics.copyArea(j, this.Y, 1, this.cp, 0, this.bW[this.bU + j]);
            }
            this.bU -= this.bS;
            if (this.bU < 0) {
                this.bU += 360;
            }
        }
        else {
            if (this.bN) {
                graphics.setColor(this.bP);
                graphics.drawString(this.bK, this.bF + 1, this.ce + 1);
            }
            graphics.setColor(this.bO);
            graphics.drawString(this.bK, this.bF, this.ce);
        }
        this.bF -= this.bL;
        if (this.bF < -this.bH) {
            this.bF = this.bD;
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
                            this.bK = new String(byteArray);
                            return;
                        }
                        catch (NoSuchMethodError noSuchMethodError) {
                            this.bK = new String(byteArray, 0);
                            return;
                        }
                    }
                    int n3 = 1;
                    for (int j = 0; j < n2; ++j) {
                        if (byteArray[j] == 10) {
                            ++n3;
                        }
                    }
                    this.bX = new String[n3 - 1];
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
                                this.bX[l] = new String(byteArray, array2[l], array3[l]);
                            }
                            catch (NoSuchMethodError noSuchMethodError2) {
                                this.bX[l] = new String(byteArray, 0, array2[l], array3[l]);
                            }
                        }
                    }
                    catch (StringIndexOutOfBoundsException ex) {
                        this.bX = null;
                    }
                }
                catch (IOException ex2) {}
            }
            catch (IOException ex3) {}
        }
        catch (MalformedURLException ex4) {}
    }
    
    public blobs() {
        this.c = false;
        this.S = false;
        this.W = 3;
        this.bl = false;
        this.bm = false;
        this.bp = false;
        this.br = false;
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
                    c2 = '\u001e';
                    break;
                }
                case 1: {
                    c2 = '}';
                    break;
                }
                case 2: {
                    c2 = 'J';
                    break;
                }
                case 3: {
                    c2 = 'y';
                    break;
                }
                default: {
                    c2 = 'D';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
