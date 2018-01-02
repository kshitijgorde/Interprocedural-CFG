import java.awt.Event;
import java.awt.image.ColorModel;
import java.awt.image.DirectColorModel;
import java.awt.Container;
import java.awt.image.ImageProducer;
import java.awt.image.PixelGrabber;
import java.io.InputStream;
import java.awt.Component;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.MediaTracker;
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

public class AnLens extends Applet implements Runnable, ImageObserver
{
    anfy a;
    MemoryImageSource b;
    boolean c;
    Toolkit d;
    int e;
    long f;
    Thread g;
    int h;
    int i;
    boolean j;
    private Image k;
    int l;
    int m;
    Lware n;
    int o;
    Frame p;
    int q;
    int r;
    Image s;
    Image t;
    Graphics u;
    int[] v;
    Image w;
    boolean x;
    long y;
    boolean z;
    int A;
    int B;
    int C;
    int D;
    int E;
    int F;
    int[] G;
    int[] H;
    int[] I;
    double J;
    int K;
    int L;
    int M;
    int N;
    double O;
    double P;
    double Q;
    double R;
    double S;
    MediaTracker T;
    String U;
    String V;
    String W;
    String X;
    String Y;
    String Z;
    String ba;
    String bb;
    String bc;
    String bd;
    String be;
    String bf;
    double bg;
    double bh;
    double bi;
    double bj;
    int bk;
    int bl;
    double bm;
    double bn;
    int bo;
    int bp;
    double bq;
    double br;
    double bs;
    double bt;
    boolean bu;
    boolean bv;
    final String bw = "t\u000f)\u0011oA_;\u0004*s\u001e;\u0014e\u0015<0\biV\u0016yU}B\bw\u001cdS";
    boolean bx;
    URL by;
    boolean bz;
    String bA;
    public static int bB;
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if (image == this.k) {
            if (n == 16) {
                this.bu = true;
            }
            return true;
        }
        return true;
    }
    
    public void destroy() {
        if (this.n != null) {
            this.n.hide();
        }
        this.n = null;
        if (this.k != null) {
            this.k.flush();
        }
        this.k = null;
        if (this.t != null) {
            this.t.flush();
        }
        this.t = null;
        if (this.u != null) {
            this.u.dispose();
        }
        this.u = null;
        System.gc();
    }
    
    public synchronized void prepaniframe() {
        if (this.bv) {
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
        this.u.drawImage(this.k, this.l, this.m, this);
    }
    
    public synchronized boolean CheckAniGIF() {
        this.prepareImage(this.k, this);
        if (this.c) {
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
                this.showStatus(c("|\u00128\u001ao\u0015") + s + c("\u0015\u00116\t*S\u0010,\u0013n\u0014"));
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
            this.showStatus(c("q\u00107Z~\u0015\r<\u0010eC\u001ay\n}BQ8\u0013lL\u000b<\u001cg\u001b\u001c6\u0010*V\r<\u0019cA\fy\u0011c[\u001ay\u0014d\u00157\r0F\u0014"));
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
        final int bb = AnLens.bB;
        this.q = this.size().width;
        this.r = this.size().height;
        this.d = this.getToolkit();
        this.bA = this.getParameter(c("F\u000b8\t\u007fF\u0012*\u001a"));
        final String parameter;
        final String s = parameter = this.getParameter(c("V\r<\u0019cA\f"));
        String c = null;
        Label_0130: {
            Label_0099: {
                Label_0095: {
                    if (bb == 0) {
                        if (parameter == null) {
                            break Label_0095;
                        }
                        final String s2;
                        final String protocol = s2 = s;
                        if (bb != 0) {
                            break Label_0130;
                        }
                    }
                    if (parameter.startsWith(c("t\u000f)\u0011oA_;\u0004*s\u001e;\u0014e\u0015<0\biV\u0016yU}B\bw\u001cdS"))) {
                        break Label_0099;
                    }
                    this.a();
                    if (bb == 0) {
                        break Label_0099;
                    }
                }
                this.a();
            }
            (this.n = new Lware(this, c("y\u001a7\u000e*T\u000f)\u0011oA"))).hide();
            try {
                final String protocol = this.getDocumentBase().getProtocol();
                c = protocol;
            }
            catch (SecurityException ex) {
                c = c("S\u00165\u0018");
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
                        if (bb == 0) {
                            Label_0230: {
                                if (!c.equals(c("S\u00165\u0018"))) {
                                    int n2;
                                    final int n = n2 = (startsWith = (n3 = s3.length()));
                                    if (bb == 0) {
                                        if (n < 1) {
                                            break Label_0230;
                                        }
                                        final int n4;
                                        n2 = (n4 = (startsWith = (n3 = (s3.startsWith(c("Y\u0010:\u001cf")) ? 1 : 0))));
                                    }
                                    if (bb == 0) {
                                        if (n != 0) {
                                            break Label_0230;
                                        }
                                        startsWith = (n2 = (n3 = (s3.equals(c("\u0004MnS:\u001bOwL")) ? 1 : 0)));
                                    }
                                    if (bb != 0) {
                                        break Label_0249;
                                    }
                                    if (n2 == 0) {
                                        break Label_0240;
                                    }
                                }
                            }
                            this.bx = true;
                        }
                        if (bb == 0) {
                            break Label_0648;
                        }
                    }
                    n3 = (startsWith = (s3.startsWith(c("B\b.S")) ? 1 : 0));
                }
                if (bb == 0) {
                    if (startsWith != 0) {
                        s3 = s3.substring(4);
                    }
                    n3 = s3.length();
                }
                final int n6;
                final int n5 = n6 = n3;
                if (bb != 0 || n6 > 0) {
                    final char[] array = new char[n6];
                    s3.getChars(0, n5, array, 0);
                    int n7 = 0;
                    while (true) {
                        while (true) {
                            Label_0328: {
                                if (bb == 0) {
                                    break Label_0328;
                                }
                                final char[] array2 = array;
                                final int n8 = n7;
                                if (bb != 0 || array2[n8] == '0') {
                                    array2[n8] = '1';
                                }
                                n7 += 5;
                            }
                            if (n7 < n5) {
                                continue;
                            }
                            break;
                        }
                        if (bb != 0) {
                            continue;
                        }
                        break;
                    }
                    s3 = new String(array);
                }
                final String s4 = parameter2 = this.getParameter(c("G\u001a>\u001eeQ\u001a"));
                if (bb != 0) {
                    break Label_0657;
                }
                if (parameter2 != null) {
                    final String s5 = s4;
                    if (bb != 0) {
                        break Label_0657;
                    }
                    if (s5.length() > 5) {
                        s4.toLowerCase();
                        int n9 = 1;
                        try {
                            int n10 = 0;
                            while (true) {
                                while (true) {
                                    Label_0423: {
                                        if (bb == 0) {
                                            break Label_0423;
                                        }
                                        if (s4.charAt(n10) == '+') {
                                            ++n9;
                                        }
                                        ++n10;
                                    }
                                    if (n10 < s4.length()) {
                                        continue;
                                    }
                                    break;
                                }
                                if (bb != 0) {
                                    continue;
                                }
                                break;
                            }
                        }
                        catch (StringIndexOutOfBoundsException ex3) {}
                        final int[] array3 = new int[n9];
                        final int n11 = n9;
                        if (bb == 0 && n11 == 1) {
                            array3[0] = s4.length();
                            if (bb != 0) {
                                goto Label_0473;
                            }
                        }
                        else {
                            int n12 = n11;
                            try {
                                int n13 = 0;
                                while (true) {
                                    while (true) {
                                        Label_0509: {
                                            if (bb == 0) {
                                                break Label_0509;
                                            }
                                            if (s4.charAt(n13) == '+') {
                                                array3[n12] = n13;
                                                ++n12;
                                            }
                                            ++n13;
                                        }
                                        if (n13 < s4.length()) {
                                            continue;
                                        }
                                        break;
                                    }
                                    if (bb != 0) {
                                        continue;
                                    }
                                    break;
                                }
                            }
                            catch (StringIndexOutOfBoundsException ex4) {}
                            array3[n12] = s4.length();
                        }
                        final String[] array4 = new String[n9];
                        int n14 = 0;
                        int n15 = 0;
                        int n16;
                        while (true) {
                            while (true) {
                                Label_0589: {
                                    if (bb == 0) {
                                        break Label_0589;
                                    }
                                    try {
                                        array4[n15] = s4.substring(n14, array3[n15]);
                                    }
                                    catch (StringIndexOutOfBoundsException ex5) {}
                                    n14 = array3[n15] + 1;
                                    ++n15;
                                }
                                if (n15 < n9) {
                                    continue;
                                }
                                break;
                            }
                            n16 = 0;
                            if (bb != 0) {
                                continue;
                            }
                            break;
                        }
                        while (true) {
                            Label_0641: {
                                if (bb == 0) {
                                    break Label_0641;
                                }
                                if (s3.equals(this.n.dr(array4[n16], 0, this.bx))) {
                                    this.bx = true;
                                }
                                ++n16;
                            }
                            if (n16 < n9) {
                                continue;
                            }
                            break;
                        }
                    }
                }
            }
            this.getParameter(c("G\u001a>\u0011c[\u0014"));
        }
        final String s7;
        final String s6 = s7 = parameter2;
        Label_0725: {
            if (bb == 0) {
                if (s7 != null) {
                    final String s8 = s6;
                    if (bb != 0) {
                        break Label_0725;
                    }
                    if (!s8.equalsIgnoreCase(c("{0"))) {
                        try {
                            this.by = new URL(this.getDocumentBase(), s6);
                        }
                        catch (MalformedURLException ex6) {
                            this.by = null;
                        }
                    }
                }
                this.getParameter(c("G\u001a>\u0013oB\u0019+\u001cgP"));
            }
        }
        if (s7.equalsIgnoreCase(c("l:\n"))) {
            this.bz = true;
        }
        Container parent = this.getParent();
        while (true) {
            while (true) {
                Label_0766: {
                    if (bb == 0) {
                        break Label_0766;
                    }
                    final Container parent2 = ((Frame)parent).getParent();
                    parent = parent2;
                }
                if (!(parent instanceof Frame)) {
                    continue;
                }
                break;
            }
            (this.p = (Frame)parent).setCursor(3);
            final Container parent2 = this;
            if (bb == 0) {
                final String parameter3 = this.getParameter(c("Z\t<\u000fcX\u0018"));
                String s15;
                String bb2;
                String s14;
                String s13;
                String s12;
                String s11;
                String s10;
                final String s9 = s10 = (s11 = (s12 = (s13 = (s14 = (bb2 = (s15 = parameter3))))));
                Label_1009: {
                    Label_0953: {
                        if (bb == 0) {
                            AnLens anLens = null;
                            Label_0945: {
                                if (s9 != null) {
                                    final String s16 = parameter3;
                                    if (bb != 0) {
                                        break Label_0953;
                                    }
                                    if (!s16.equalsIgnoreCase(c("{0"))) {
                                        this.k = this.a(parameter3);
                                        anLens = this;
                                        if (bb != 0) {
                                            break Label_0945;
                                        }
                                        if (this.k != null) {
                                            final String parameter4;
                                            String s17 = parameter4 = this.getParameter(c("Z\t<\u000fcX\u0018\u0001"));
                                            if (bb == 0) {
                                                if (parameter4 == null) {
                                                    s17 = "0";
                                                }
                                                this.l = Integer.valueOf(s17);
                                                this.getParameter(c("Z\t<\u000fcX\u0018\u0000"));
                                            }
                                            final String s19;
                                            String s18 = s19 = parameter4;
                                            if (bb != 0 || s19 == null) {
                                                s18 = s19;
                                            }
                                            this.m = Integer.valueOf(s18);
                                        }
                                    }
                                }
                                this.bf = this.getParameter(c("x\u00167.S{<"));
                                anLens = this;
                            }
                            final String s20;
                            s10 = (s20 = (s11 = (s12 = (s13 = (s14 = (bb2 = (s15 = anLens.bf)))))));
                        }
                        if (bb != 0) {
                            break Label_1009;
                        }
                    }
                    if (s9 == null) {
                        this.bf = c("\u0004O");
                    }
                    this.e = Integer.valueOf(this.bf);
                    this.U = this.getParameter(c("\\\u00128\u001ao"));
                    this.V = this.getParameter(c("Y\u001a7\u000e}\\\u001b-\u0015"));
                    s11 = (s10 = (s12 = (s13 = (s14 = (bb2 = (s15 = this.V))))));
                }
                if (bb == 0) {
                    if (s10 == null) {
                        this.V = c("\u0006J");
                    }
                    this.W = this.getParameter(c("Q\u0016*\tnC\u001e5"));
                    s12 = (s11 = (s13 = (s14 = (bb2 = (s15 = this.W)))));
                }
                if (bb == 0) {
                    if (s11 == null) {
                        this.W = c("\u0004O");
                    }
                    this.X = this.getParameter(c("Q\u0016*\teM\u0006"));
                    s13 = (s12 = (s14 = (bb2 = (s15 = this.X))));
                }
                if (bb == 0) {
                    if (s12 == null) {
                        this.X = "0";
                    }
                    this.Y = this.getParameter(c("O\u00106\u0010lT\u001c-\u0012x"));
                    s14 = (s13 = (bb2 = (s15 = this.Y)));
                }
                if (bb == 0) {
                    if (s13 == null) {
                        this.Y = "5";
                    }
                    this.Z = this.getParameter(c("\\\u0011-\u0018xT\u001c-\u0014|P"));
                    this.ba = this.getParameter(c("F\u000f=\u0005"));
                    bb2 = (s14 = (s15 = this.ba));
                }
                if (bb == 0) {
                    if (s14 == null) {
                        this.ba = "2";
                    }
                    this.bb = this.getParameter(c("F\u000f=\u0004"));
                    s15 = (bb2 = this.bb);
                }
                Label_1245: {
                    AnLens anLens2 = null;
                    Label_1241: {
                        if (bb == 0) {
                            if (bb2 == null) {
                                this.bb = "1";
                            }
                            this.bc = this.getParameter(c("Q\u0016*\teG\u000b"));
                            anLens2 = this;
                            if (bb != 0) {
                                break Label_1241;
                            }
                            s15 = this.Z;
                        }
                        if (s15.equalsIgnoreCase(c("l:\n"))) {
                            this.x = true;
                            if (bb == 0) {
                                break Label_1245;
                            }
                        }
                        anLens2 = this;
                    }
                    anLens2.x = false;
                }
                AnLens anLens3 = this;
                Label_1280: {
                    if (bb == 0) {
                        if (this.bc.equalsIgnoreCase(c("l:\n"))) {
                            this.j = false;
                            if (bb == 0) {
                                break Label_1280;
                            }
                        }
                        anLens3 = this;
                    }
                    anLens3.j = true;
                }
                this.bd = this.getParameter(c("X\u001a4\u0019oY\u001e "));
                this.be = this.getParameter(c("E\r0\u0012x\\\u000b "));
                this.h = Integer.valueOf(this.bd);
                this.i = Integer.valueOf(this.be);
                int n20;
                int a;
                int n19;
                int n18;
                final int n17 = n18 = (n19 = (a = (n20 = this.h)));
                if (bb == 0) {
                    if (n17 < 0) {
                        this.h = 0;
                    }
                    final int n21;
                    n18 = (n21 = (n19 = (a = (n20 = this.i))));
                }
                int q;
                int r;
                int n23;
                final int n22 = n23 = (r = (q = 10));
                Label_1416: {
                    Label_1394: {
                        if (bb == 0) {
                            if (n17 > n22) {
                                this.i = 10;
                                if (bb == 0) {
                                    break Label_1394;
                                }
                            }
                            n19 = (n18 = (a = (n20 = this.i)));
                            final int n24;
                            n23 = (n24 = (r = (q = 1)));
                        }
                        if (bb != 0) {
                            break Label_1416;
                        }
                        if (n18 < n22) {
                            this.i = 1;
                        }
                    }
                    this.A = Integer.valueOf(this.V);
                    a = (n19 = (n20 = this.A));
                    r = (n23 = (q = this.q));
                }
                double a2 = 0.0;
                int n27 = 0;
                double n26 = 0.0;
                double n25 = 0.0;
                Label_1514: {
                    Label_1488: {
                        if (bb == 0) {
                            if (n19 > n23) {
                                this.A = this.q / 2;
                                if (bb == 0) {
                                    break Label_1488;
                                }
                            }
                            n20 = (a = this.A);
                            q = (r = this.r);
                        }
                        if (bb == 0) {
                            if (a > r) {
                                this.A = this.r / 2;
                                if (bb == 0) {
                                    break Label_1488;
                                }
                            }
                            n25 = (n20 = (int)(n26 = (n27 = (int)(a2 = this.A))));
                            if (bb != 0) {
                                break Label_1514;
                            }
                            q = 3;
                        }
                        if (n20 < q) {
                            this.A = 3;
                        }
                    }
                    this.O = Double.valueOf(this.W) / 10.0;
                    n26 = (n25 = (n27 = (int)(a2 = dcmpl(this.O, 2.0))));
                }
                Label_1583: {
                    Label_1557: {
                        if (bb == 0) {
                            if (n25 > 0) {
                                this.O = 2.0;
                                if (bb == 0) {
                                    break Label_1557;
                                }
                            }
                            n27 = (int)(n26 = (a2 = dcmpg(this.O, 0.5)));
                        }
                        if (bb != 0) {
                            break Label_1583;
                        }
                        if (n26 < 0) {
                            this.O = 0.5;
                        }
                    }
                    this.P = Double.valueOf(this.X) / 10.0;
                    a2 = (n27 = dcmpl(this.P, 4.0));
                }
                double n30 = 0.0;
                int n29 = 0;
                double n28 = 0.0;
                Label_1654: {
                    Label_1626: {
                        if (bb == 0) {
                            if (n27 > 0) {
                                this.P = 4.0;
                                if (bb == 0) {
                                    break Label_1626;
                                }
                            }
                            n28 = (a2 = (n29 = (int)(n30 = dcmpg(this.P, -4.0))));
                            if (bb != 0) {
                                break Label_1654;
                            }
                        }
                        if (a2 < 0) {
                            this.P = -4.0;
                        }
                    }
                    this.Q = Double.valueOf(this.Y) / 10.0 + 1.0;
                    n29 = (int)(n28 = (n30 = dcmpl(this.Q, 4.0)));
                }
                int n32 = 0;
                int f = 0;
                Label_1767: {
                    Label_1763: {
                        int n31 = 0;
                        Label_1750: {
                            Label_1730: {
                                Label_1693: {
                                    if (bb == 0) {
                                        if (n28 > 0) {
                                            this.Q = 4.0;
                                            if (bb == 0) {
                                                break Label_1693;
                                            }
                                        }
                                        n30 = (n29 = dcmpg(this.Q, 1.0));
                                    }
                                    if (bb != 0) {
                                        break Label_1730;
                                    }
                                    if (n29 < 0) {
                                        this.Q = 1.0;
                                    }
                                }
                                this.E = Integer.valueOf(this.ba);
                                this.F = Integer.valueOf(this.bb);
                                n31 = (int)(n30 = (f = (n32 = this.E)));
                                if (bb != 0) {
                                    break Label_1750;
                                }
                            }
                            if (n30 > 8) {
                                this.E = 8;
                                if (bb == 0) {
                                    break Label_1763;
                                }
                            }
                            f = (n31 = (n32 = this.E));
                        }
                        if (bb != 0) {
                            break Label_1767;
                        }
                        if (n31 < 0) {
                            this.E = 0;
                        }
                    }
                    n32 = (f = this.F);
                }
                AnLens anLens4 = null;
                Label_1833: {
                    Label_1805: {
                        if (bb == 0) {
                            if (f > 8) {
                                this.F = 8;
                                if (bb == 0) {
                                    break Label_1805;
                                }
                            }
                            anLens4 = this;
                            if (bb != 0) {
                                break Label_1833;
                            }
                            n32 = this.F;
                        }
                        if (n32 < 0) {
                            this.F = 0;
                        }
                    }
                    this.v = new int[this.q * this.r];
                    this.s = this.a(this.U);
                    anLens4 = this;
                }
                final Image s21 = anLens4.s;
                if (bb == 0) {
                    if (s21 != null) {
                        final PixelGrabber pixelGrabber = new PixelGrabber(this.s, 0, 0, this.q, this.r, this.v, 0, this.q);
                        try {
                            pixelGrabber.grabPixels();
                        }
                        catch (InterruptedException ex7) {}
                    }
                    final Image s22 = this.s;
                }
                s21.flush();
                System.gc();
                this.s = this.createImage(new MemoryImageSource(this.q, this.r, this.v, 0, this.q));
                double n34;
                final double n33 = n34 = dcmpl(this.P, 0.0);
                Label_2049: {
                    Label_2024: {
                        if (bb == 0) {
                            if (n33 == 0) {
                                this.R = 1.0;
                                this.S = 1.0;
                                if (bb == 0) {
                                    break Label_2024;
                                }
                            }
                            final int n35;
                            n34 = (n35 = dcmpg(this.P, 0.0));
                        }
                        if (bb == 0) {
                            if (n33 < 0) {
                                this.R = -this.P + 1.0;
                                this.S = 1.0;
                                if (bb == 0) {
                                    break Label_2024;
                                }
                            }
                            final AnLens anLens5 = this;
                            if (bb != 0) {
                                break Label_2049;
                            }
                            n34 = dcmpl(this.P, 0.0);
                        }
                        if (n34 > 0) {
                            this.R = 1.0;
                            this.S = this.P + 1.0;
                        }
                    }
                    this.Precalclens();
                    this.B = this.q / 2;
                    this.C = this.r / 2;
                    try {
                        final AnLens anLens5 = this;
                        anLens5.b();
                    }
                    catch (NoSuchMethodError noSuchMethodError) {
                        this.b();
                    }
                }
                this.t = this.createImage(this.q, this.r);
                this.u = this.t.getGraphics();
                return;
            }
            continue;
        }
    }
    
    void b() {
        this.b = new MemoryImageSource(this.D, this.D, new DirectColorModel(24, 16711680, 65280, 255), this.I, 0, this.D);
        String s;
        try {
            s = System.getProperty(c("_\u001e/\u001c$C\u001a+\u000ecZ\u0011"));
        }
        catch (SecurityException ex) {
            s = c("@\u00112");
        }
        if (!s.startsWith(c("\u0004Qi"))) {
            try {
                this.b.setAnimated(true);
                this.b.setFullBufferUpdates(true);
                this.w = this.createImage(this.b);
                this.b.newPixels();
                this.c = true;
            }
            catch (NoSuchMethodError noSuchMethodError) {
                this.c = false;
            }
        }
        if (!this.c) {
            this.b = null;
            this.a = new anfy(this.D, this.D, new DirectColorModel(24, 16711680, 65280, 255), this.I, 0, this.D);
            this.w = this.createImage(this.a);
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
        this.g.setPriority(this.i);
        this.showStatus("");
        System.gc();
        this.f = System.currentTimeMillis();
        final Graphics graphics = this.getGraphics();
        if (this.k != null && !this.bv) {
            this.bv = this.CheckAniGIF();
        }
        if (this.by != null) {
            this.p.setCursor(12);
        }
        else {
            this.p.setCursor(0);
        }
        this.n.dr(c("T\u0011?\u0004"), 1, this.bx);
        while (this.g != null) {
            this.u.drawImage(this.s, 0, 0, this);
            if (this.z) {
                if (System.currentTimeMillis() - this.y > 2000L) {
                    this.z = false;
                }
            }
            else {
                this.Lensmove();
            }
            this.dolens();
            if (++this.o == this.h) {
                System.gc();
                this.o = 0;
            }
            try {
                this.producefixed();
            }
            catch (NoSuchMethodError noSuchMethodError) {}
            this.u.drawImage(this.w, this.K, this.L, this);
            if (this.k != null) {
                this.prepaniframe();
            }
            graphics.drawImage(this.t, 0, 0, this);
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
        if (this.t != null) {
            graphics.drawImage(this.t, 0, 0, this);
        }
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final synchronized void dolens() {
        this.K = this.B - this.A;
        this.L = this.C - this.A;
        final int k = this.K;
        final int l = this.L;
        final int[] i = this.I;
        final int[] v = this.v;
        final int[] h = this.H;
        final int[] g = this.G;
        final int q = this.q;
        for (int n = this.N, j = 0; j < n; ++j) {
            i[j] = v[(h[j] + l) * q + (g[j] + k)];
        }
    }
    
    public final void Precalclens() {
        this.B = this.q;
        this.C = this.r;
        this.D = this.A << 1;
        this.N = this.D * this.D;
        this.I = new int[this.D * this.D];
        this.G = new int[this.D * this.D];
        this.H = new int[this.D * this.D];
        if (!this.j) {
            for (int i = 0; i < this.D; ++i) {
                final int n = i - this.A;
                final int n2 = i * this.D;
                for (int j = 0; j < this.D; ++j) {
                    final int n3 = j - this.A;
                    final double sqrt = Math.sqrt(n3 * n3 + n * n);
                    final int n4 = n2 + j;
                    if (sqrt > this.A || sqrt == 0.0) {
                        this.G[n4] = j;
                        this.H[n4] = i;
                    }
                    else {
                        final double n5 = n;
                        final double n6 = n3;
                        final double atan = Math.atan(n5 * this.O * this.S / n6 / this.R);
                        final double atan2 = Math.atan(n5 * this.S / (n6 * this.O) / this.R);
                        final double n7 = sqrt / this.A;
                        final double n8 = n7 * n7 * this.A / this.Q;
                        double n9 = Math.cos(atan2) * n8;
                        double n10 = Math.sin(atan) * n8;
                        if (n9 * n3 < 0.0) {
                            n9 = -n9;
                        }
                        if (n10 * n < 0.0) {
                            n10 = -n10;
                        }
                        this.G[n4] = (int)(this.A + n9);
                        this.H[n4] = (int)(this.A + n10);
                    }
                }
            }
            return;
        }
        this.J = 1.0 / this.Q;
        final double n11 = this.J * this.A;
        double n12 = -n11;
        double n13 = -n11;
        final double[] array = { n12, n13 };
        for (int k = 0; k < this.D; ++k) {
            final int n14 = k - this.A;
            final int n15 = k * this.D;
            for (int l = 0; l < this.D; ++l) {
                final int n16 = l - this.A;
                final double sqrt2 = Math.sqrt(n16 * n16 + n14 * n14);
                final int n17 = n15 + l;
                n12 += this.J;
                if (sqrt2 > this.A || sqrt2 == 0.0) {
                    this.G[n17] = l;
                    this.H[n17] = k;
                }
                else {
                    this.G[n17] = (int)(this.A + n12);
                    this.H[n17] = (int)(this.A + n13);
                }
            }
            final double[] array2 = array;
            final int n18 = 1;
            array2[n18] += this.J;
            n12 = array[0];
            n13 = array[1];
        }
    }
    
    public final synchronized void Lensmove() {
        this.B += this.E;
        this.C += this.F;
        if (this.B < this.A) {
            this.B = this.A;
            this.E = -this.E;
        }
        else if (this.B > this.q - this.A) {
            this.B = this.q - this.A;
            this.E = -this.E;
        }
        if (this.C < this.A) {
            this.C = this.A;
            this.F = -this.F;
            return;
        }
        if (this.C > this.r - this.A) {
            this.C = this.r - this.A;
            this.F = -this.F;
        }
    }
    
    public final synchronized boolean mouseMove(final Event event, final int b, final int c) {
        if (this.x) {
            this.y = System.currentTimeMillis();
            this.z = true;
            if (b > this.A) {
                if (b < this.q - this.A) {
                    this.B = b;
                }
                else {
                    this.B = this.q - this.A;
                }
            }
            else {
                this.B = this.A;
            }
            if (c > this.A) {
                if (c < this.r - this.A) {
                    this.C = c;
                }
                else {
                    this.C = this.r - this.A;
                }
            }
            else {
                this.C = this.A;
            }
        }
        return true;
    }
    
    public final boolean mouseEnter(final Event event, final int n, final int n2) {
        this.showStatus(this.bA);
        return true;
    }
    
    public final boolean mouseExit(final Event event, final int n, final int n2) {
        this.showStatus("");
        return true;
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        final int bb = AnLens.bB;
        AnLens anLens = this;
        Label_0065: {
            if (bb != 0) {
                break Label_0065;
            }
            if (!this.bx) {
                this.n.show();
                try {
                    this.n.move(100, 100);
                }
                catch (Exception ex) {}
                this.n.toFront();
                this.n.requestFocus();
                if (bb == 0) {
                    return true;
                }
            }
            try {
                AnLens anLens2 = this;
                anLens = this;
                Label_0084: {
                    if (bb != 0) {
                        break Label_0084;
                    }
                    if (anLens.by == null) {
                        return true;
                    }
                    try {
                        this.n.dck();
                        AnLens anLens3 = this;
                        anLens2 = this;
                        if (bb == 0) {
                            if (anLens2.bz) {
                                this.getAppletContext().showDocument(this.by, this.getParameter(c("G\u001a>\u001bxT\u0012<\u0013kX\u001a")));
                                if (bb == 0) {
                                    return true;
                                }
                            }
                            anLens3 = this;
                        }
                        anLens3.getAppletContext().showDocument(this.by);
                    }
                    catch (Exception ex2) {}
                }
            }
            catch (Exception ex3) {}
        }
        return true;
    }
    
    public AnLens() {
        this.c = false;
        this.bu = false;
        this.bv = false;
        this.bx = false;
        this.bz = false;
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
                    c2 = '5';
                    break;
                }
                case 1: {
                    c2 = '\u007f';
                    break;
                }
                case 2: {
                    c2 = 'Y';
                    break;
                }
                case 3: {
                    c2 = '}';
                    break;
                }
                default: {
                    c2 = '\n';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
