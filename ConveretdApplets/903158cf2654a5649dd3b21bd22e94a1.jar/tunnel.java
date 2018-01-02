import java.awt.Event;
import java.io.DataInputStream;
import java.awt.FontMetrics;
import java.awt.image.ImageProducer;
import java.awt.image.ColorModel;
import java.awt.image.DirectColorModel;
import java.awt.Dimension;
import java.awt.Container;
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

public class tunnel extends Applet implements Runnable, ImageObserver
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
    int l;
    int m;
    int n;
    int o;
    private Image p;
    int q;
    int r;
    Frame s;
    final float t = 0.01745329f;
    final float u = 6.2831845f;
    float v;
    float w;
    float x;
    int[] y;
    int z;
    int A;
    float B;
    float C;
    int D;
    int E;
    int F;
    int[] G;
    int[] H;
    private Image I;
    private Graphics J;
    private Image K;
    String L;
    String M;
    String N;
    String O;
    String P;
    String Q;
    String R;
    String S;
    String T;
    String U;
    String V;
    String W;
    String X;
    String Y;
    private Image Z;
    private Graphics ba;
    boolean bb;
    boolean bc;
    boolean bd;
    int be;
    int bf;
    final String bg = ";\u001aW\u0003\u0006\u000eJE\u0016C<\u000bE\u0006\fZ)N\u001a\u0000\u0019\u0003\u0007G\u0014\r\u001d\t\u000e\r\u001c";
    boolean bh;
    URL bi;
    boolean bj;
    int bk;
    boolean bl;
    int bm;
    int bn;
    int bo;
    int bp;
    int bq;
    int br;
    int bs;
    int bt;
    int bu;
    String bv;
    int bw;
    boolean bx;
    boolean by;
    Color bz;
    Color bA;
    Font bB;
    int bC;
    int bD;
    int bE;
    int bF;
    int[] bG;
    int[] bH;
    String[] bI;
    int bJ;
    int bK;
    Font[] bL;
    int bM;
    int bN;
    private int bO;
    int bP;
    int bQ;
    int bR;
    int bS;
    int bT;
    int[] bU;
    int bV;
    int bW;
    float bX;
    float bY;
    int bZ;
    int ca;
    String cb;
    public static int cc;
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if (image == this.p) {
            if (n == 16) {
                this.bb = true;
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
        if (this.p != null) {
            this.p.flush();
        }
        this.p = null;
        if (this.Z != null) {
            this.Z.flush();
        }
        this.Z = null;
        if (this.ba != null) {
            this.ba.dispose();
        }
        this.ba = null;
        System.gc();
    }
    
    public synchronized void prepaniframe() {
        if (this.bc) {
            this.notifyAll();
            while (!this.bb) {
                Thread.yield();
                try {
                    Thread.sleep(8L);
                }
                catch (InterruptedException ex) {}
            }
            this.bb = false;
        }
        this.ba.drawImage(this.p, this.q, this.r, this);
    }
    
    public synchronized boolean CheckAniGIF() {
        this.prepareImage(this.p, this);
        if (this.c) {
            for (int i = 0; i < 3; ++i) {
                this.notifyAll();
                Thread.yield();
                try {
                    Thread.sleep(100L);
                }
                catch (InterruptedException ex) {}
            }
            return this.bb;
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
                this.showStatus(c("3\u0007F\b\u0006Z") + s + c("Z\u0004H\u001bC\u001c\u0005R\u0001\u0007["));
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
            this.showStatus(c(">\u0005IH\u0017Z\u0018B\u0002\f\f\u000f\u0007\u0018\u0014\rDF\u0001\u0005\u0003\u001eB\u000e\u000eT\tH\u0002C\u0019\u0018B\u000b\n\u000e\u0019\u0007\u0003\n\u0014\u000f\u0007\u0006\rZ\"s\"/["));
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
        final int cc = tunnel.cc;
        this.setLayout(null);
        this.addNotify();
        this.d = this.getToolkit();
        this.cb = this.getParameter(c("\t\u001eF\u001b\u0016\t\u0007T\b"));
        final String parameter = this.getParameter(c("\u0019\u0018B\u000b\n\u000e\u0019"));
        String protocol;
        final String s = protocol = parameter;
        String c = null;
        Label_0117: {
            Label_0086: {
                Label_0082: {
                    if (cc == 0) {
                        if (s == null) {
                            break Label_0082;
                        }
                        final String s2;
                        protocol = (s2 = parameter);
                    }
                    if (cc != 0) {
                        break Label_0117;
                    }
                    if (s.startsWith(c(";\u001aW\u0003\u0006\u000eJE\u0016C<\u000bE\u0006\fZ)N\u001a\u0000\u0019\u0003\u0007G\u0014\r\u001d\t\u000e\r\u001c"))) {
                        break Label_0086;
                    }
                    this.a();
                    if (cc == 0) {
                        break Label_0086;
                    }
                }
                this.a();
            }
            (this.h = new Lware(this, c(".\u001fI\u0001\u0006\u0016JF\u001f\u0013\u0016\u000fS"))).hide();
            try {
                protocol = this.getDocumentBase().getProtocol();
                c = protocol;
            }
            catch (SecurityException ex) {
                c = c("\u001c\u0003K\n");
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
                        if (cc == 0) {
                            Label_0217: {
                                if (!c.equals(c("\u001c\u0003K\n"))) {
                                    int length;
                                    final int n = length = s3.length();
                                    if (cc == 0) {
                                        if (n < 1) {
                                            break Label_0217;
                                        }
                                        final boolean startsWith;
                                        length = ((startsWith = s3.startsWith(c("\u0016\u0005D\u000e\u000f"))) ? 1 : 0);
                                    }
                                    if (cc == 0) {
                                        if (n != 0) {
                                            break Label_0217;
                                        }
                                        startsWith2 = (length = (n2 = (s3.equals(c("KX\u0010ASTZ\t^")) ? 1 : 0)));
                                        if (cc != 0) {
                                            break Label_0236;
                                        }
                                    }
                                    if (length == 0) {
                                        break Label_0227;
                                    }
                                }
                            }
                            this.bh = true;
                        }
                        if (cc == 0) {
                            break Label_0635;
                        }
                    }
                    n2 = (startsWith2 = (s3.startsWith(c("\r\u001dPA")) ? 1 : 0));
                }
                if (cc == 0) {
                    if (startsWith2 != 0) {
                        s3 = s3.substring(4);
                    }
                    n2 = s3.length();
                }
                final int n4;
                final int n3 = n4 = n2;
                if (cc != 0 || n4 > 0) {
                    final char[] array = new char[n4];
                    s3.getChars(0, n3, array, 0);
                    int n5 = 0;
                    while (true) {
                        while (true) {
                            Label_0315: {
                                if (cc == 0) {
                                    break Label_0315;
                                }
                                final char[] array2 = array;
                                final int n6 = n5;
                                if (cc != 0 || array2[n6] == '0') {
                                    array2[n6] = '1';
                                }
                                n5 += 5;
                            }
                            if (n5 < n3) {
                                continue;
                            }
                            break;
                        }
                        if (cc != 0) {
                            continue;
                        }
                        break;
                    }
                    s3 = new String(array);
                }
                final String s4 = parameter2 = this.getParameter(c("\b\u000f@\f\f\u001e\u000f"));
                if (cc != 0) {
                    break Label_0644;
                }
                if (parameter2 != null) {
                    final String s5 = s4;
                    if (cc != 0) {
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
                                        if (cc == 0) {
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
                                if (cc != 0) {
                                    continue;
                                }
                                break;
                            }
                        }
                        catch (StringIndexOutOfBoundsException ex3) {}
                        final int[] array3 = new int[n7];
                        final int n9 = n7;
                        if (cc == 0 && n9 == 1) {
                            array3[0] = s4.length();
                            if (cc != 0) {
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
                                            if (cc == 0) {
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
                                    if (cc != 0) {
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
                                    if (cc == 0) {
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
                            if (cc != 0) {
                                continue;
                            }
                            break;
                        }
                        while (true) {
                            Label_0628: {
                                if (cc == 0) {
                                    break Label_0628;
                                }
                                if (s3.equals(this.h.dr(array4[n14], 0, this.bh))) {
                                    this.bh = true;
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
            this.getParameter(c("\b\u000f@\u0003\n\u0014\u0001"));
        }
        final String s7;
        final String s6 = s7 = parameter2;
        Label_0712: {
            if (cc == 0) {
                if (s7 != null) {
                    final String s8 = s6;
                    if (cc != 0) {
                        break Label_0712;
                    }
                    if (!s8.equalsIgnoreCase(c("4%"))) {
                        try {
                            this.bi = new URL(this.getDocumentBase(), s6);
                        }
                        catch (MalformedURLException ex6) {
                            this.bi = null;
                        }
                    }
                }
                this.getParameter(c("\b\u000f@\u0001\u0006\r\fU\u000e\u000e\u001f"));
            }
        }
        if (s7.equalsIgnoreCase(c("#/t"))) {
            this.bj = true;
        }
        Container parent = this.getParent();
        while (true) {
            while (true) {
                Label_0753: {
                    if (cc == 0) {
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
            (this.s = (Frame)parent).setCursor(3);
            final Container parent2 = this;
            if (cc == 0) {
                final String parameter3 = this.getParameter(c("\u0015\u001cB\u001d\n\u0017\r"));
                String s15;
                String s14;
                String s13;
                String s12;
                String s11;
                final String s10;
                final String s9 = s10 = (s11 = (s12 = (s13 = (s14 = (s15 = parameter3)))));
                final String s16;
                final String s20;
                Label_0935: {
                    if (cc == 0) {
                        tunnel tunnel = null;
                        Label_0932: {
                            if (s9 != null) {
                                s16 = (s11 = (s12 = (s13 = (s14 = (s15 = parameter3)))));
                                if (cc != 0) {
                                    break Label_0935;
                                }
                                if (!s16.equalsIgnoreCase(c("4%"))) {
                                    this.p = this.a(parameter3);
                                    tunnel = this;
                                    if (cc != 0) {
                                        break Label_0932;
                                    }
                                    if (this.p != null) {
                                        final String parameter4;
                                        String s17 = parameter4 = this.getParameter(c("\u0015\u001cB\u001d\n\u0017\r\u007f"));
                                        if (cc == 0) {
                                            if (parameter4 == null) {
                                                s17 = "0";
                                            }
                                            this.q = Integer.valueOf(s17);
                                            this.getParameter(c("\u0015\u001cB\u001d\n\u0017\r~"));
                                        }
                                        final String s19;
                                        String s18 = s19 = parameter4;
                                        if (cc != 0 || s19 == null) {
                                            s18 = s19;
                                        }
                                        this.r = Integer.valueOf(s18);
                                    }
                                }
                            }
                            this.Y = this.getParameter(c("7\u0003I<:4)"));
                            tunnel = this;
                        }
                        s20 = (s13 = (s14 = (s15 = tunnel.Y)));
                    }
                }
                if (cc == 0) {
                    if (s9 == null) {
                        this.Y = c("KZ");
                    }
                    this.e = Integer.valueOf(this.Y);
                    this.bl = true;
                    this.M = this.getParameter(c("\b\u000fT"));
                    s13 = (s14 = (s15 = this.M));
                }
                if (cc == 0) {
                    if (s16 == null) {
                        this.M = "1";
                    }
                    this.N = this.getParameter(c("\t\u001eF\u001d\u0017\u0002"));
                    s13 = (s14 = (s15 = this.N));
                }
                if (cc == 0) {
                    if (s20 == null) {
                        this.N = c("KZ\u0017");
                    }
                    this.O = this.getParameter(c("\t\u001eF\u001d\u0017\u0003"));
                    s13 = (s12 = (s14 = (s15 = this.O)));
                }
                if (cc == 0) {
                    if (s20 == null) {
                        this.O = "1";
                    }
                    this.R = this.getParameter(c("\u0019\u0005I\n\u001b"));
                    s14 = (s13 = (s15 = this.R));
                }
                if (cc == 0) {
                    if (s13 == null) {
                        this.R = c("K_\u0017");
                    }
                    this.S = this.getParameter(c("\u0019\u0005I\n\u001a"));
                    s15 = (s14 = this.S);
                }
                String s21 = null;
                String u = null;
                Label_1186: {
                    if (cc == 0) {
                        if (s14 == null) {
                            this.S = c("H[\u0012");
                        }
                        this.T = this.getParameter(c("\u0019\u0005I\n\u0019"));
                        u = (s15 = (s21 = this.T));
                        if (cc != 0) {
                            break Label_1186;
                        }
                    }
                    if (s15 == null) {
                        this.T = c("KZ\u0017_");
                    }
                    this.U = this.getParameter(c("\u001f\fA\n\u0000\u000e"));
                    s21 = (u = this.U);
                }
                tunnel tunnel2 = null;
                Label_1286: {
                    if (cc == 0) {
                        if (u == null) {
                            this.U = "1";
                        }
                        this.V = this.getParameter(c("\u0017\u0005Q\n\u000e\u001f\u0004S"));
                        tunnel2 = this;
                        if (cc != 0) {
                            break Label_1286;
                        }
                        s21 = this.V;
                    }
                    if (s21 == null) {
                        this.V = "1";
                    }
                    this.W = this.getParameter(c("\u0017\u000fJ\u000b\u0006\u0016\u000b^"));
                    this.X = this.getParameter(c("\n\u0018N\u0000\u0011\u0013\u001e^"));
                    this.n = Integer.valueOf(this.W);
                    this.o = Integer.valueOf(this.X);
                    tunnel2 = this;
                }
                int n16;
                final int n15 = n16 = tunnel2.n;
                if (cc == 0) {
                    if (n15 < 0) {
                        this.n = 0;
                    }
                    final int o;
                    n16 = (o = this.o);
                }
                final int n17 = 10;
                int o2 = 0;
                int f = 0;
                int n20 = 0;
                int n18 = 0;
                Label_1463: {
                    Label_1345: {
                        if (cc == 0) {
                            if (n15 > n17) {
                                this.o = 10;
                                if (cc == 0) {
                                    break Label_1345;
                                }
                            }
                            f = (n16 = (o2 = this.o));
                            final int n19;
                            n18 = (n19 = (n20 = 1));
                            if (cc != 0) {
                                break Label_1463;
                            }
                        }
                        if (n16 < n17) {
                            this.o = 1;
                        }
                    }
                    this.E = Integer.valueOf(this.M);
                    this.F = Integer.valueOf(this.V);
                    this.B = Float.valueOf(this.N);
                    this.C = Float.valueOf(this.O);
                    this.v = Float.valueOf(this.R);
                    this.w = Float.valueOf(this.S);
                    this.x = Float.valueOf(this.T);
                    this.bk = Integer.valueOf(this.U);
                    o2 = (f = this.F);
                    n20 = (n18 = 8);
                }
                int f2 = 0;
                int e = 0;
                int n22 = 0;
                int n21 = 0;
                Label_1506: {
                    Label_1500: {
                        if (cc == 0) {
                            if (f > n18) {
                                this.F = 8;
                                if (cc == 0) {
                                    break Label_1500;
                                }
                            }
                            e = (o2 = (f2 = this.F));
                            n21 = (n20 = (n22 = 1));
                            if (cc != 0) {
                                break Label_1506;
                            }
                        }
                        if (o2 < n20) {
                            this.F = 1;
                        }
                    }
                    f2 = (e = this.E);
                    n22 = (n21 = 8);
                }
                int n23 = 0;
                int bk = 0;
                int n25 = 0;
                int n24 = 0;
                Label_1548: {
                    Label_1543: {
                        if (cc == 0) {
                            if (e > n21) {
                                this.E = 8;
                                if (cc == 0) {
                                    break Label_1543;
                                }
                            }
                            bk = (f2 = (n23 = this.E));
                            n24 = (n22 = (n25 = 1));
                            if (cc != 0) {
                                break Label_1548;
                            }
                        }
                        if (f2 < n22) {
                            this.E = 1;
                        }
                    }
                    n23 = (bk = this.bk);
                    n25 = (n24 = 2);
                }
                tunnel tunnel3 = null;
                Label_1585: {
                    Label_1584: {
                        if (cc == 0) {
                            if (bk > n24) {
                                this.bk = 2;
                                if (cc == 0) {
                                    break Label_1584;
                                }
                            }
                            tunnel3 = this;
                            if (cc != 0) {
                                break Label_1585;
                            }
                            n23 = this.bk;
                            n25 = 1;
                        }
                        if (n23 < n25) {
                            this.bk = 1;
                        }
                    }
                    tunnel3 = this;
                }
                final Dimension size = tunnel3.size();
                this.be = size.width / this.E;
                this.bf = size.height / this.E;
                this.L = this.getParameter(c("\u0013\u0007F\b\u0006"));
                this.K = this.a(this.L);
                this.z = this.K.getWidth(this);
                this.A = this.K.getHeight(this);
                this.D = this.z * this.A;
                this.bn = this.D;
                this.y = new int[5 * this.be * this.bf];
                this.G = new int[this.D];
                this.H = new int[this.be * this.bf];
                final PixelGrabber pixelGrabber = new PixelGrabber(this.K, 0, 0, this.z, this.A, this.G, 0, this.z);
                try {
                    pixelGrabber.grabPixels();
                }
                catch (InterruptedException ex7) {}
                this.l = this.be;
                this.m = this.bf;
                this.j = this.be * this.E;
                this.k = this.bf * this.E;
                this.bm = this.be * this.bf;
                try {
                    this.b();
                }
                catch (NoSuchMethodError noSuchMethodError) {
                    this.b();
                }
                this.scrollinitial();
                this.Z = this.createImage(this.j, this.k + this.bZ);
                this.ba = this.Z.getGraphics();
                return;
            }
            continue;
        }
    }
    
    void b() {
        this.b = new MemoryImageSource(this.l, this.m, new DirectColorModel(24, 16711680, 65280, 255), this.H, 0, this.l);
        String s;
        try {
            s = System.getProperty(c("\u0010\u000bQ\u000eM\f\u000fU\u001c\n\u0015\u0004"));
        }
        catch (SecurityException ex) {
            s = c("\u000f\u0004L");
        }
        if (!s.startsWith(c("KD\u0017"))) {
            try {
                this.b.setAnimated(true);
                this.b.setFullBufferUpdates(true);
                this.I = this.createImage(this.b);
                this.b.newPixels();
                this.c = true;
            }
            catch (NoSuchMethodError noSuchMethodError) {
                this.c = false;
            }
        }
        if (!this.c) {
            this.b = null;
            this.a = new anfy(this.l, this.m, new DirectColorModel(24, 16711680, 65280, 255), this.H, 0, this.l);
            this.I = this.createImage(this.a);
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
        this.g.setPriority(this.o);
        if (this.bl) {
            this.repaint();
            if (this.bk == 1) {
                this.d();
            }
            else if (this.bk == 2) {
                this.e();
            }
        }
        this.bl = false;
        this.showStatus("");
        this.repaint();
        System.gc();
        this.f = System.currentTimeMillis();
        final Graphics graphics = this.getGraphics();
        if (this.p != null && !this.bc) {
            this.bc = this.CheckAniGIF();
        }
        if (this.bi != null) {
            this.s.setCursor(12);
        }
        else {
            this.s.setCursor(0);
        }
        this.h.dr(c("\u001b\u0004A\u0016"), 1, this.bh);
        while (this.g != null) {
            this.c();
            if (++this.i == this.n) {
                System.gc();
                this.i = 0;
            }
            try {
                this.producefixed();
            }
            catch (NoSuchMethodError noSuchMethodError) {}
            if (this.E == 1) {
                this.ba.drawImage(this.I, 0, 0, this);
            }
            else {
                this.prepscaled();
                this.ba.drawImage(this.I, 0, 0, this.j, this.k, this);
            }
            if (this.p != null) {
                this.prepaniframe();
            }
            if (this.bx) {
                this.scrolltext(this.ba);
            }
            graphics.drawImage(this.Z, 0, 0, this);
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
        this.prepareImage(this.I, this.j, this.k, this);
        this.notifyAll();
        while ((checkImage & 0xF0) == 0x0) {
            Thread.yield();
            checkImage = this.checkImage(this.I, this.j, this.k, this);
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
        if (this.bl) {
            if (this.K == null) {
                graphics.setColor(Color.black);
                graphics.fillRect(0, 0, this.j, this.k);
            }
            else {
                graphics.drawImage(this.K, 0, 0, this.j, this.k, this);
            }
            graphics.setColor(Color.gray);
            graphics.drawString(c("-\u000bN\u001b"), this.j / 2 - 16 + 1, this.k / 2 - 4 - 6 + 1);
            graphics.drawString(c("6\u0005F\u000b\n\u0014\r"), this.j / 2 - 25 + 1, this.k / 2 + 2 + 1);
            graphics.setColor(Color.white);
            graphics.drawString(c("-\u000bN\u001b"), this.j / 2 - 16, this.k / 2 - 4 - 6);
            graphics.drawString(c("6\u0005F\u000b\n\u0014\r"), this.j / 2 - 25, this.k / 2 + 2);
            return;
        }
        if (this.I != null) {
            if (this.E == 1) {
                this.ba.drawImage(this.I, 0, 0, this);
            }
            else {
                this.prepscaled();
                this.ba.drawImage(this.I, 0, 0, this.j, this.k, this);
            }
            if (this.p != null) {
                this.prepaniframe();
            }
            if (this.bx) {
                this.scrolltext(this.ba);
            }
            graphics.drawImage(this.Z, 0, 0, this);
        }
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    final void c() {
        switch (this.F) {
            case 1: {
                this.bn = (this.bn + this.z) % this.D;
                break;
            }
            case 2: {
                this.bn = (this.bn - this.z) % this.D;
                break;
            }
            case 3: {
                this.bn = (this.bn + 1) % this.D;
                break;
            }
            case 4: {
                this.bn = (this.bn - 1) % this.D;
                break;
            }
            case 5: {
                this.bn = (this.bn + this.z + 1) % this.D;
                break;
            }
            case 6: {
                this.bn = (this.bn + this.z - 1) % this.D;
                break;
            }
            case 7: {
                this.bn = (this.bn - this.z + 1) % this.D;
                break;
            }
            case 8: {
                this.bn = (this.bn - this.z - 1) % this.D;
                break;
            }
        }
        if (this.bn < 0) {
            this.bn += this.D;
        }
        final int bn = this.bn;
        final int bm = this.bm;
        final int[] h = this.H;
        final int[] g = this.G;
        final int[] y = this.y;
        final int d = this.D;
        for (int i = 0; i < bm; ++i) {
            h[i] = g[(y[i] + bn) % d];
        }
    }
    
    final float a(final int n) {
        float n2 = 0.0f;
        if (n <= 50) {
            n2 = 4.0f;
        }
        if (n >= 50) {
            n2 = 3.0f;
        }
        if (n >= 70) {
            n2 = 2.5f;
        }
        if (n >= 100) {
            n2 = 2.4f;
        }
        if (n >= 120) {
            n2 = 2.2f;
        }
        if (n >= 150) {
            n2 = 2.1f;
        }
        if (n >= 170) {
            n2 = 2.0f;
        }
        if (n >= 190) {
            n2 = 1.8f;
        }
        if (n >= 200) {
            n2 = 1.5f;
        }
        if (n >= 220) {
            n2 = 1.2f;
        }
        if (n >= 230) {
            n2 = 1.0f;
        }
        if (n >= 240) {
            n2 = 0.7f;
        }
        if (n >= 250) {
            n2 = 0.5f;
        }
        if (n >= 280) {
            n2 = 0.2f;
        }
        if (n >= 300) {
            n2 = 0.1f;
        }
        if (n >= 310) {
            n2 = 0.08f;
        }
        if (n >= 320) {
            n2 = 0.07f;
        }
        if (n >= 330) {
            n2 = 0.06f;
        }
        if (n >= 350) {
            n2 = 0.05f;
        }
        if (n >= 370) {
            n2 = 0.04f;
        }
        return n2;
    }
    
    final void d() {
        float n = 0.0f;
        final float n2 = (int)(this.be / 2.0f);
        final float n3 = (int)(this.bf / 2.0f);
        final float n4 = (int)(this.bf / 1.3f);
        final float n5 = this.v * n2;
        final short[][] array = new short[this.be][this.bf];
        final float a = this.a(this.be);
        final float a2 = this.a(this.bf);
        for (float n6 = 0.0f; n6 < this.C; n6 += n) {
            float n10;
            for (float n7 = 0.0f; n7 < this.B; n7 += n10) {
                final float n8 = n7 / this.B;
                final float n9 = n6 / this.C;
                n10 = 0.065f + 0.4f * n9 * a;
                n = 0.065f + 0.4f * n9 * a2;
                final float n11 = n8;
                final float n12 = n9;
                final float n13 = n11 * 6.2831845f;
                final float n14 = this.x * n12;
                final float n15 = n14 + 150.0f;
                final int n16 = (int)(n5 * (float)Math.cos(n13) / n15 + n2);
                if (n16 >= 0 && n16 < this.be) {
                    final short[] array2 = array[n16];
                    final int n17 = (int)((this.w * (float)Math.sin(n13) * (float)Math.cos(n12) + n14 * (float)Math.sin(n12)) * n4 / n15 + n3);
                    if (n17 < this.bf && n17 >= 0 && array2[n17] == 0) {
                        this.y[n17 * this.be + n16] = ((int)(n9 * this.A) * this.z + (int)(n8 * this.z)) % this.D;
                        array2[n17] = 1;
                    }
                }
            }
        }
    }
    
    final void e() {
        float n = 0.0f;
        final float n2 = (int)(this.be / 2.0f);
        final float n3 = (int)(this.bf / 2.0f);
        final float n4 = (int)(this.bf / 1.3f);
        final float n5 = this.v * n2;
        final float a = this.a(this.be);
        final float a2 = this.a(this.bf);
        for (float n6 = 0.0f; n6 < this.C; n6 += n) {
            float n10;
            for (float n7 = 0.0f; n7 < this.B; n7 += n10) {
                final float n8 = n7 / this.B;
                final float n9 = n6 / this.C;
                n10 = 0.065f + 0.4f * n9 * a;
                n = 0.065f + 0.4f * n9 * a2;
                final float n11 = n8;
                final float n12 = n9;
                final float n13 = n11 * 6.2831845f;
                final float n14 = this.x * n12;
                final float n15 = n14 + 150.0f;
                final int n16 = (int)(n5 * (float)Math.cos(n13) / n15 + n2);
                if (n16 >= 0 && n16 < this.be) {
                    final int n17 = (int)((this.w * (float)Math.sin(n13) * (float)Math.cos(n12) + n14 * (float)Math.sin(n12)) * n4 / n15 + n3);
                    if (n17 < this.bf && n17 >= 0) {
                        this.y[n17 * this.be + n16] = ((int)(n9 * this.A) * this.z + (int)(n8 * this.z)) % this.D;
                    }
                }
            }
        }
    }
    
    public void scrollinitial() {
        this.bx = false;
        final String parameter = this.getParameter(c("\u000e\u000f_\u001b\u0010\u0019\u0018H\u0003\u000f"));
        if (parameter != null && !parameter.equalsIgnoreCase(c("4%"))) {
            String s = this.getParameter(c("\u000e\u000f_\u001b\u0017\u0003\u001aB"));
            if (s == null) {
                s = c("\u0012\u0005U\u0006\u0019\u0015\u0004S\u000e\u000f");
            }
            if (s.equals(c("\u0012\u0005U\u0006\u0019\u0015\u0004S\u000e\u000f"))) {
                this.bJ = 0;
            }
            else if (s.equals(c("\f\u000fU\u001b\n\u0019\u000bK"))) {
                this.bJ = 1;
            }
            else if (s.equals(c("\u0000\u0005H\u0002\n\u0014\r"))) {
                this.bJ = 2;
            }
            else if (s.equals(c("\u0013\u0004Q\u0015\f\u0015\u0007N\u0001\u0004"))) {
                this.bJ = 3;
            }
            if (this.bJ == 0) {
                this.GetTheString(parameter, 0);
                if (this.bv != null) {
                    this.bx = true;
                }
            }
            else {
                this.GetTheString(parameter, 1);
                if (this.bI != null) {
                    this.bx = true;
                }
            }
        }
        if (this.bx) {
            String parameter2 = this.getParameter(c("\u000e\u000f_\u001b\u0010\n\u000fB\u000b"));
            if (parameter2 == null) {
                parameter2 = "0";
            }
            this.bw = Integer.valueOf(parameter2);
            String s2 = this.getParameter(c("\u000e\u000f_\u001b\u0005\u0015\u0004S"));
            if (s2 == null) {
                s2 = c(";\u0018N\u000e\u000f");
            }
            int n = 0;
            if (this.getParameter(c("\u000e\u000f_\u001b\u0001\u0015\u0006C")).equalsIgnoreCase(c("#/t"))) {
                ++n;
            }
            String s3 = this.getParameter(c("\u000e\u000f_\u001b\n\u000e\u000bK\u0006\u0000"));
            if (s3 == null) {
                s3 = c("4%");
            }
            if (s3.equalsIgnoreCase(c("#/t"))) {
                n += 2;
            }
            String s4 = this.getParameter(c("\u000e\u000f_\u001b\u0010\u0013\u0010B"));
            if (s4 == null) {
                s4 = c("KX");
            }
            this.bB = new Font(s2, n, Integer.valueOf(s4));
            if (this.getParameter(c("\u000e\u000f_\u001b\u0010\u0012\u000bC\u0000\u0014")).equalsIgnoreCase(c("#/t"))) {
                this.by = true;
            }
            else {
                this.by = false;
            }
            this.bz = new Color(Integer.valueOf(this.getParameter(c(".\u000f_\u001b \u0015\u0006u"))), Integer.valueOf(this.getParameter(c(".\u000f_\u001b \u0015\u0006`"))), Integer.valueOf(this.getParameter(c(".\u000f_\u001b \u0015\u0006e"))));
            this.bA = new Color(Integer.valueOf(this.getParameter(c(".\u000f_\u001b09\u0005K="))), Integer.valueOf(this.getParameter(c(".\u000f_\u001b09\u0005K("))), Integer.valueOf(this.getParameter(c(".\u000f_\u001b09\u0005K-"))));
            this.bo = this.size().width;
            this.bp = this.size().height;
            if (this.bJ == 0) {
                String parameter3 = this.getParameter(c("\u000e\u000f_\u001b\f\u001c\fT\n\u0017"));
                if (parameter3 == null) {
                    parameter3 = "0";
                }
                this.br = Integer.valueOf(parameter3);
                if (this.br < 0) {
                    this.br = 0;
                }
                String parameter4 = this.getParameter(c(".\u000f_\u001b)\u000f\u0007W.\u000e\n"));
                if (parameter4 == null) {
                    parameter4 = "0";
                }
                this.bO = Integer.valueOf(parameter4);
                String parameter5 = this.getParameter(c(".\u000f_\u001b)\u000f\u0007W<\u0013\u001e"));
                if (parameter5 == null) {
                    parameter5 = "0";
                }
                this.bR = Integer.valueOf(parameter5);
                String parameter6 = this.getParameter(c(".\u000f_\u001b0\u0013\u0004B.\u000e\n"));
                if (parameter6 == null) {
                    parameter6 = "0";
                }
                this.bC = Integer.valueOf(parameter6);
                String parameter7 = this.getParameter(c(".\u000f_\u001b0\u0013\u0004B<\u0013\u001e"));
                if (parameter7 == null) {
                    parameter7 = "0";
                }
                this.bD = Integer.valueOf(parameter7);
                String parameter8 = this.getParameter(c(".\u000f_\u001b0\u0013\u0004B.\r\u001d\u0006B"));
                if (parameter8 == null) {
                    parameter8 = "0";
                }
                this.bE = Integer.valueOf(parameter8);
                final FontMetrics fontMetrics = this.getGraphics().getFontMetrics(this.bB);
                this.bs = fontMetrics.stringWidth(this.bv);
                this.bt = fontMetrics.getHeight();
                this.bu = fontMetrics.getMaxDescent();
                this.bq = this.bo;
                if (this.br < this.bt - this.bu) {
                    this.br = this.bt - this.bu;
                }
                else if (this.br > this.bp - this.bu) {
                    this.br = this.bp - this.bu;
                }
                if (this.bC != 0) {
                    this.bG = new int[this.bo + 360];
                    this.bH = new int[this.bo + 360];
                    for (int i = 0; i < this.bo + 360; ++i) {
                        this.bG[i] = (int)(this.bC * Math.sin(this.bE * i * 3.141592653589793 / 180.0)) - this.bt - this.bu + this.br;
                        this.bH[i] = this.bG[i] - this.k;
                    }
                    this.bF = 360;
                    this.bZ = this.bt + this.bu + 1;
                    this.ca = this.bZ - 1;
                }
            }
            else {
                if (this.bJ == 1) {
                    String s5 = this.getParameter(c("\u000e\u000f_\u001b\u0015\t\u001aF\f\u0006"));
                    if (s5 == null) {
                        s5 = c("KZ");
                    }
                    final int intValue = Integer.valueOf(s5);
                    final FontMetrics fontMetrics2 = this.getGraphics().getFontMetrics(this.bB);
                    this.bT = fontMetrics2.getHeight() + intValue;
                    this.bU = new int[this.bI.length];
                    for (int j = 0; j < this.bI.length; ++j) {
                        this.bU[j] = (this.bo - fontMetrics2.stringWidth(this.bI[j])) / 2;
                    }
                    this.bS = -this.bT;
                    return;
                }
                if (this.bJ >= 2) {
                    String parameter9 = this.getParameter(c("\u000e\u000f_\u001b\u000e\u0013\u0004A\u0000\r\u000e"));
                    if (parameter9 == null) {
                        parameter9 = "2";
                    }
                    this.bM = Integer.valueOf(parameter9);
                    String s6 = this.getParameter(c("\u000e\u000f_\u001b\u000e\u001b\u0012A\u0000\r\u000e"));
                    if (s6 == null) {
                        s6 = c("MX");
                    }
                    this.bN = Integer.valueOf(s6);
                    this.bK = this.bN - this.bM;
                    this.bB = null;
                    this.bL = new Font[this.bK];
                    int bm = this.bM;
                    for (int k = 0; k < this.bK; ++k) {
                        this.bL[k] = new Font(s2, n, bm++);
                    }
                    this.bX = this.bo / 2.0f;
                    this.bY = this.bp / 2.0f;
                    if (this.bJ == 3) {
                        this.bV = this.bK - 1;
                        return;
                    }
                    this.bV = 0;
                }
            }
        }
    }
    
    public void scrolltext(final Graphics graphics) {
        switch (this.bJ) {
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
        graphics.setFont(this.bB);
        this.bS += this.bw;
        if (this.bS > this.bp + this.bI.length * this.bT) {
            this.bS = -this.bT;
        }
        if (this.by) {
            for (int i = 0; i < this.bI.length; ++i) {
                final String s = this.bI[i];
                final int n = this.bU[i];
                final int n2 = this.bp - this.bS + i * this.bT;
                graphics.setColor(this.bA);
                graphics.drawString(s, n + 1, n2 + 1);
                graphics.setColor(this.bz);
                graphics.drawString(s, n, n2);
            }
            return;
        }
        graphics.setColor(this.bz);
        for (int j = 0; j < this.bI.length; ++j) {
            graphics.drawString(this.bI[j], this.bU[j], this.bp - this.bS + j * this.bT);
        }
    }
    
    public void zoomscroll(final Graphics graphics) {
        final String s = this.bI[this.bW];
        graphics.setFont(this.bL[this.bV]);
        final FontMetrics fontMetrics = graphics.getFontMetrics(this.bL[this.bV]);
        final int n = (int)(this.bX - fontMetrics.stringWidth(s) / 2.0f);
        final int n2 = (int)(this.bY + fontMetrics.getHeight() / 4.0f);
        if (this.by) {
            graphics.setColor(this.bA);
            graphics.drawString(s, n + 1, n2 + 1);
        }
        graphics.setColor(this.bz);
        graphics.drawString(s, n, n2);
        if (this.bJ == 3) {
            this.bV -= this.bw;
            if (this.bV <= 1) {
                this.bV = this.bK - 1;
                ++this.bW;
                if (this.bW >= this.bI.length) {
                    this.bW = 0;
                }
            }
        }
        else {
            this.bV += this.bw;
            if (this.bV >= this.bK) {
                this.bV = 0;
                ++this.bW;
                if (this.bW >= this.bI.length) {
                    this.bW = 0;
                }
            }
        }
    }
    
    public void horizscroll(final Graphics graphics) {
        graphics.setFont(this.bB);
        if (this.bO == 0) {
            this.bP = this.br;
        }
        else {
            this.bQ += this.bR;
            this.bP = this.br - (int)Math.abs(this.bO * Math.sin(this.bQ / 90.0 * 3.141592653589793));
        }
        if (this.bC != 0) {
            for (int i = 0; i < this.bo; ++i) {
                final int n = this.bG[this.bF + i];
                graphics.copyArea(i, n, 1, this.bZ, 0, this.k - n);
            }
            if (this.by) {
                graphics.setColor(this.bA);
                graphics.drawString(this.bv, this.bq + 1, this.k + this.bt + 1);
            }
            graphics.setColor(this.bz);
            graphics.drawString(this.bv, this.bq, this.k + this.bt);
            for (int j = 0; j < this.bo; ++j) {
                graphics.copyArea(j, this.k, 1, this.ca, 0, this.bH[this.bF + j]);
            }
            this.bF -= this.bD;
            if (this.bF < 0) {
                this.bF += 360;
            }
        }
        else {
            if (this.by) {
                graphics.setColor(this.bA);
                graphics.drawString(this.bv, this.bq + 1, this.bP + 1);
            }
            graphics.setColor(this.bz);
            graphics.drawString(this.bv, this.bq, this.bP);
        }
        this.bq -= this.bw;
        if (this.bq < -this.bs) {
            this.bq = this.bo;
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
                            this.bv = new String(byteArray);
                            return;
                        }
                        catch (NoSuchMethodError noSuchMethodError) {
                            this.bv = new String(byteArray, 0);
                            return;
                        }
                    }
                    int n3 = 1;
                    for (int j = 0; j < n2; ++j) {
                        if (byteArray[j] == 10) {
                            ++n3;
                        }
                    }
                    this.bI = new String[n3 - 1];
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
                                this.bI[l] = new String(byteArray, array2[l], array3[l]);
                            }
                            catch (NoSuchMethodError noSuchMethodError2) {
                                this.bI[l] = new String(byteArray, 0, array2[l], array3[l]);
                            }
                        }
                    }
                    catch (StringIndexOutOfBoundsException ex) {
                        this.bI = null;
                    }
                }
                catch (IOException ex2) {}
            }
            catch (IOException ex3) {}
        }
        catch (MalformedURLException ex4) {}
    }
    
    public final boolean mouseEnter(final Event event, final int n, final int n2) {
        this.showStatus(this.cb);
        return true;
    }
    
    public final boolean mouseExit(final Event event, final int n, final int n2) {
        this.showStatus("");
        return true;
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        final int cc = tunnel.cc;
        tunnel tunnel = this;
        Label_0065: {
            if (cc != 0) {
                break Label_0065;
            }
            if (!this.bh) {
                this.h.show();
                try {
                    this.h.move(100, 100);
                }
                catch (Exception ex) {}
                this.h.toFront();
                this.h.requestFocus();
                if (cc == 0) {
                    return true;
                }
            }
            try {
                tunnel tunnel2 = this;
                tunnel = this;
                Label_0084: {
                    if (cc != 0) {
                        break Label_0084;
                    }
                    if (tunnel.bi == null) {
                        return true;
                    }
                    try {
                        this.h.dck();
                        tunnel tunnel3 = this;
                        tunnel2 = this;
                        if (cc == 0) {
                            if (tunnel2.bj) {
                                this.getAppletContext().showDocument(this.bi, this.getParameter(c("\b\u000f@\t\u0011\u001b\u0007B\u0001\u0002\u0017\u000f")));
                                if (cc == 0) {
                                    return true;
                                }
                            }
                            tunnel3 = this;
                        }
                        tunnel3.getAppletContext().showDocument(this.bi);
                    }
                    catch (Exception ex2) {}
                }
            }
            catch (Exception ex3) {}
        }
        return true;
    }
    
    public tunnel() {
        this.c = false;
        this.E = 1;
        this.bb = false;
        this.bc = false;
        this.bh = false;
        this.bj = false;
        this.bl = true;
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
                    c2 = 'z';
                    break;
                }
                case 1: {
                    c2 = 'j';
                    break;
                }
                case 2: {
                    c2 = '\'';
                    break;
                }
                case 3: {
                    c2 = 'o';
                    break;
                }
                default: {
                    c2 = 'c';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
