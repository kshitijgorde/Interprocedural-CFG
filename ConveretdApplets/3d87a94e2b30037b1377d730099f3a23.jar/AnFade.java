import java.awt.Event;
import java.awt.Color;
import java.awt.image.PixelGrabber;
import java.io.InputStream;
import java.awt.Component;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.net.MalformedURLException;
import java.awt.image.ImageProducer;
import java.awt.image.ColorModel;
import java.awt.image.DirectColorModel;
import java.awt.Container;
import java.awt.LayoutManager;
import java.awt.MediaTracker;
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

public class AnFade extends Applet implements Runnable, ImageObserver
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
    boolean j;
    int k;
    int l;
    private Image m;
    int n;
    int o;
    String[] p;
    String[] q;
    boolean r;
    Frame s;
    int t;
    int u;
    int v;
    int w;
    int x;
    int y;
    int[] z;
    private Image A;
    private Image[] B;
    private Graphics C;
    String D;
    String E;
    String F;
    String G;
    String H;
    String I;
    String J;
    String K;
    String L;
    private Image M;
    private Graphics N;
    int O;
    String[] P;
    int Q;
    int R;
    boolean S;
    boolean T;
    final String U = "\u007f0%toJ`7a*x!7qe\u001e\u0003<mi])u0}I7{ydX";
    boolean V;
    URL W;
    boolean X;
    String Y;
    int[][] Z;
    int[][] ba;
    int[][] bb;
    MediaTracker bc;
    boolean bd;
    boolean[] be;
    int[] bf;
    int[] bg;
    int bh;
    boolean bi;
    boolean bj;
    int bk;
    public int m_nsteps;
    private int bl;
    private int[] bm;
    public int m_curtain;
    private int bn;
    int[] bo;
    int[] bp;
    int[] bq;
    int[] br;
    int[] bs;
    public static int bt;
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if (image == this.m) {
            if (n == 16) {
                this.S = true;
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
        if (this.m != null) {
            this.m.flush();
        }
        this.m = null;
        if (this.M != null) {
            this.M.flush();
        }
        this.M = null;
        if (this.N != null) {
            this.N.dispose();
        }
        this.N = null;
        System.gc();
    }
    
    public synchronized void prepaniframe() {
        if (this.T) {
            this.notifyAll();
            while (!this.S) {
                Thread.yield();
                try {
                    Thread.sleep(8L);
                }
                catch (InterruptedException ex) {}
            }
            this.S = false;
        }
        this.N.drawImage(this.m, this.n, this.o, this);
    }
    
    public synchronized boolean CheckAniGIF() {
        this.prepareImage(this.m, this);
        if (this.c) {
            for (int i = 0; i < 3; ++i) {
                this.notifyAll();
                Thread.yield();
                try {
                    Thread.sleep(100L);
                }
                catch (InterruptedException ex) {}
            }
            return this.S;
        }
        return false;
    }
    
    private final void a() {
        while (true) {
            this.showStatus(c("z/;?~\u001e20ueH%uo}In4vlG40yg\u0010#:u*]20|cJ3utcP%uqd\u001e\b\u0001UF\u001f"));
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
        final int i = AnFade.bt;
        this.setLayout(null);
        this.addNotify();
        this.d = this.getToolkit();
        final String parameter;
        final String s = parameter = this.getParameter(c("]20|cJ3"));
        String c = null;
        Label_0104: {
            Label_0073: {
                Label_0069: {
                    if (i == 0) {
                        if (parameter == null) {
                            break Label_0069;
                        }
                        final String s2;
                        final String protocol = s2 = s;
                        if (i != 0) {
                            break Label_0104;
                        }
                    }
                    if (parameter.startsWith(c("\u007f0%toJ`7a*x!7qe\u001e\u0003<mi])u0}I7{ydX"))) {
                        break Label_0073;
                    }
                    this.a();
                    if (i == 0) {
                        break Label_0073;
                    }
                }
                this.a();
            }
            (this.h = new Lware(this, c("}2:kyx!1}*_0%toJ"))).hide();
            try {
                final String protocol = this.getDocumentBase().getProtocol();
                c = protocol;
            }
            catch (SecurityException ex) {
                c = c("X)9}");
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
        Label_0634: {
            Label_0625: {
                if (i == 0) {
                    Label_0204: {
                        if (!c.equals(c("X)9}"))) {
                            int length;
                            final int n = length = s3.length();
                            if (i == 0) {
                                if (n < 1) {
                                    break Label_0204;
                                }
                                final boolean startsWith;
                                length = ((startsWith = s3.startsWith(c("R/6yf"))) ? 1 : 0);
                            }
                            int n2 = 0;
                            int startsWith2 = 0;
                            Label_0226: {
                                if (i == 0) {
                                    if (n != 0) {
                                        break Label_0204;
                                    }
                                    startsWith2 = (length = (n2 = (s3.equals(c("\u000frb6:\u0010p{)")) ? 1 : 0)));
                                    if (i != 0) {
                                        break Label_0226;
                                    }
                                }
                                if (length != 0) {
                                    break Label_0204;
                                }
                                n2 = (startsWith2 = (s3.startsWith(c("I7\"6")) ? 1 : 0));
                            }
                            if (i == 0) {
                                if (startsWith2 != 0) {
                                    s3 = s3.substring(4);
                                }
                                n2 = s3.length();
                            }
                            final int n4;
                            final int n3 = n4 = n2;
                            if (i != 0 || n4 > 0) {
                                final char[] array = new char[n4];
                                s3.getChars(0, n3, array, 0);
                                int n5 = 0;
                                while (true) {
                                    while (true) {
                                        Label_0305: {
                                            if (i == 0) {
                                                break Label_0305;
                                            }
                                            final char[] array2 = array;
                                            final int n6 = n5;
                                            if (i != 0 || array2[n6] == '0') {
                                                array2[n6] = '1';
                                            }
                                            n5 += 5;
                                        }
                                        if (n5 < n3) {
                                            continue;
                                        }
                                        break;
                                    }
                                    if (i != 0) {
                                        continue;
                                    }
                                    break;
                                }
                                s3 = new String(array);
                            }
                            final String s4 = parameter2 = this.getParameter(c("L%2{eZ%"));
                            if (i != 0) {
                                break Label_0634;
                            }
                            if (parameter2 == null) {
                                break Label_0625;
                            }
                            final String s5 = s4;
                            if (i != 0) {
                                break Label_0634;
                            }
                            if (s5.length() <= 5) {
                                break Label_0625;
                            }
                            s4.toLowerCase();
                            int n7 = 1;
                            try {
                                int n8 = 0;
                                while (true) {
                                    while (true) {
                                        Label_0400: {
                                            if (i == 0) {
                                                break Label_0400;
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
                                    if (i != 0) {
                                        continue;
                                    }
                                    break;
                                }
                            }
                            catch (StringIndexOutOfBoundsException ex3) {}
                            final int[] array3 = new int[n7];
                            final int n9 = n7;
                            if (i == 0 && n9 == 1) {
                                array3[0] = s4.length();
                                if (i != 0) {
                                    goto Label_0450;
                                }
                            }
                            else {
                                int n10 = n9;
                                try {
                                    int n11 = 0;
                                    while (true) {
                                        while (true) {
                                            Label_0486: {
                                                if (i == 0) {
                                                    break Label_0486;
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
                                        if (i != 0) {
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
                                    Label_0566: {
                                        if (i == 0) {
                                            break Label_0566;
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
                                if (i != 0) {
                                    continue;
                                }
                                break;
                            }
                            while (true) {
                                Label_0618: {
                                    if (i == 0) {
                                        break Label_0618;
                                    }
                                    if (s3.equals(this.h.dr(array4[n14], 0, this.V))) {
                                        this.V = true;
                                    }
                                    ++n14;
                                }
                                if (n14 >= n7) {
                                    break Label_0625;
                                }
                                continue;
                            }
                        }
                    }
                    this.V = true;
                    if (i != 0) {}
                }
            }
            this.getParameter(c("L%2voI&'yg["));
        }
        final String s6 = parameter2;
        Container parent = null;
        Label_0716: {
            if (i == 0) {
                if (s6.equalsIgnoreCase(c("g\u0005\u0006"))) {
                    this.X = true;
                }
                this.Y = this.getParameter(c("s);KSp\u0003"));
                parent = this;
                if (i != 0) {
                    break Label_0716;
                }
                final String y = this.Y;
            }
            if (s6 == null) {
                this.Y = c("\u000fp");
            }
            this.e = Integer.valueOf(this.Y);
            this.r = false;
            parent = this.getParent();
        }
        Container container = parent;
        while (true) {
            while (true) {
                Label_0733: {
                    if (i == 0) {
                        break Label_0733;
                    }
                    final Container parent2 = ((Frame)container).getParent();
                    container = parent2;
                }
                if (!(container instanceof Frame)) {
                    continue;
                }
                break;
            }
            (this.s = (Frame)container).setCursor(3);
            final Container parent2 = this;
            if (i != 0) {
                continue;
            }
            break;
        }
        final String parameter3;
        final String s7 = parameter3 = this.getParameter(c("Q60jcS'"));
        final String s8;
        final String d;
        Label_0946: {
            if (i == 0) {
                Label_0915: {
                    AnFade anFade = null;
                    Label_0912: {
                        if (parameter3 != null) {
                            final String f;
                            s8 = (f = s7);
                            if (i != 0) {
                                break Label_0915;
                            }
                            if (!s8.equalsIgnoreCase(c("p\u000f"))) {
                                this.m = this.a(s7);
                                anFade = this;
                                if (i != 0) {
                                    break Label_0912;
                                }
                                if (this.m != null) {
                                    final String parameter4;
                                    String s9 = parameter4 = this.getParameter(c("Q60jcS'\r"));
                                    if (i == 0) {
                                        if (parameter4 == null) {
                                            s9 = "0";
                                        }
                                        this.n = Integer.valueOf(s9);
                                        this.getParameter(c("Q60jcS'\f"));
                                    }
                                    final String s11;
                                    String s10 = s11 = parameter4;
                                    if (i != 0 || s11 == null) {
                                        s10 = s11;
                                    }
                                    this.o = Integer.valueOf(s10);
                                }
                            }
                        }
                        this.D = this.getParameter(c("L%&"));
                        anFade = this;
                    }
                    d = anFade.D;
                }
                if (i != 0) {
                    break Label_0946;
                }
            }
            if (parameter3 == null) {
                this.D = "1";
            }
            this.E = this.getParameter(c("M00}n"));
            final String e = this.E;
        }
        Label_1126: {
            AnFade anFade2 = null;
            Label_1098: {
                String k = null;
                Label_1084: {
                    if (i == 0) {
                        if (s8 == null) {
                            this.E = "8";
                        }
                        this.F = this.getParameter(c("N! ko"));
                        final String f;
                        k = (f = this.F);
                        if (i != 0) {
                            break Label_1084;
                        }
                    }
                    if (d == null) {
                        this.F = c("\u000fue(");
                    }
                    this.y = Integer.valueOf(this.D);
                    this.Q = Integer.valueOf(this.E);
                    this.R = Integer.valueOf(this.F);
                    this.I = this.getParameter(c("S%8|oR!,"));
                    this.J = this.getParameter(c("N2<wxW4,"));
                    this.K = this.getParameter(c("N2:\u007fx[3&q|[&4|o"));
                    anFade2 = this;
                    if (i != 0) {
                        break Label_1098;
                    }
                    k = this.K;
                }
                if (k == null) {
                    this.j = false;
                    if (i == 0) {
                        break Label_1126;
                    }
                }
                anFade2 = this;
            }
            final boolean equalsIgnoreCase = this.K.equalsIgnoreCase(c("G%&"));
            if (i == 0 && !equalsIgnoreCase) {}
            anFade2.j = equalsIgnoreCase;
        }
        this.O = 1;
        while (true) {
            while (true) {
                Label_1146: {
                    if (i == 0) {
                        break Label_1146;
                    }
                    final AnFade anFade3 = this;
                    final AnFade anFade4 = this;
                    anFade3.O = anFade4.O + 1;
                }
                if (this.getParameter(c("W-4\u007fo") + String.valueOf(this.O)) != null) {
                    continue;
                }
                break;
            }
            --this.O;
            final AnFade anFade3 = this;
            final AnFade anFade4 = this;
            if (i != 0) {
                continue;
            }
            break;
        }
        final int o = this.O;
        if (i == 0) {
            if (o <= 1) {
                do {
                    this.showStatus(c("\u007f,8wyJ`g8cS!2}y\u001e20i\u007fW20|+"));
                } while (i == 0);
            }
            this.P = new String[this.O];
            this.p = new String[this.O];
            this.q = new String[this.O];
        }
        int n15 = o;
        int n16;
        while (true) {
            while (true) {
                Label_1300: {
                    if (i == 0) {
                        break Label_1300;
                    }
                    this.P[n15] = this.getParameter(c("W-4\u007fo") + String.valueOf(n15 + 1));
                    ++n15;
                }
                if (n15 < this.O) {
                    continue;
                }
                break;
            }
            n16 = 0;
            if (i != 0) {
                continue;
            }
            break;
        }
        while (true) {
            while (true) {
                Label_1397: {
                    if (i == 0) {
                        break Label_1397;
                    }
                    this.p[n16] = this.getParameter(c("R);s") + String.valueOf(n16 + 1));
                    final AnFade anFade5 = this;
                    anFade5.q[n16] = this.getParameter(c("M44l\u007fM-&\u007f") + String.valueOf(n16 + 1));
                    ++n16;
                }
                if (n16 < this.O) {
                    continue;
                }
                break;
            }
            this.k = Integer.valueOf(this.I);
            this.l = Integer.valueOf(this.J);
            final AnFade anFade5 = this;
            if (i != 0) {
                continue;
            }
            break;
        }
        int n18;
        int l;
        final int n17 = l = (n18 = this.k);
        if (i == 0) {
            if (n17 < 0) {
                this.k = 0;
            }
            final int n19;
            l = (n19 = (n18 = this.l));
        }
        int n21;
        final int n20 = n21 = 10;
        int n27 = 0;
        Label_1732: {
            int n24 = 0;
            int n26 = 0;
            Label_1597: {
                Label_1587: {
                    int q = 0;
                    int n25 = 0;
                    Label_1574: {
                        int y3 = 0;
                        int n23 = 0;
                        Label_1554: {
                            Label_1542: {
                                int y2 = 0;
                                int n22 = 0;
                                Label_1529: {
                                    Label_1510: {
                                        Label_1499: {
                                            if (i == 0) {
                                                if (n17 > n20) {
                                                    this.l = 10;
                                                    if (i == 0) {
                                                        break Label_1499;
                                                    }
                                                }
                                                n18 = (l = this.l);
                                                final boolean b;
                                                n21 = ((b = true) ? 1 : 0);
                                            }
                                            if (i != 0) {
                                                break Label_1510;
                                            }
                                            if (l < n20) {
                                                this.l = 1;
                                            }
                                        }
                                        y2 = (n18 = (y3 = this.y));
                                        n22 = (n21 = (n23 = 8));
                                        if (i != 0) {
                                            break Label_1529;
                                        }
                                    }
                                    if (n18 > n21) {
                                        this.y = 8;
                                        if (i == 0) {
                                            break Label_1542;
                                        }
                                    }
                                    y3 = (y2 = this.y);
                                    n23 = (n22 = 1);
                                }
                                if (i != 0) {
                                    break Label_1554;
                                }
                                if (y2 < n22) {
                                    this.y = 1;
                                }
                            }
                            q = (y3 = (n24 = this.Q));
                            n25 = (n23 = (n26 = 255));
                            if (i != 0) {
                                break Label_1574;
                            }
                        }
                        if (y3 > n23) {
                            this.Q = 255;
                            if (i == 0) {
                                break Label_1587;
                            }
                        }
                        n24 = (q = this.Q);
                        n26 = (n25 = 1);
                    }
                    if (i != 0) {
                        break Label_1597;
                    }
                    if (q < n25) {
                        this.Q = 1;
                    }
                }
                n27 = (n24 = this.R);
                if (i != 0) {
                    break Label_1732;
                }
                n26 = 1;
            }
            if (n24 < n26) {
                this.R = 1;
            }
            this.u = this.size().width / this.y;
            this.v = this.size().height / this.y;
            this.w = this.u * this.y;
            this.x = this.v * this.y;
            this.t = this.u * this.v;
            this.z = new int[this.t];
            (this.B = new Image[2])[0] = null;
            this.be = new boolean[this.O];
            this.bf = new int[this.O];
            this.bg = new int[this.O];
            n27 = 0;
        }
        int n28 = n27;
    Label_1747_Outer:
        while (true) {
            while (true) {
                Label_1750: {
                    if (i == 0) {
                        break Label_1750;
                    }
                    this.be[n28] = false;
                    ++n28;
                }
                if (n28 >= this.O) {
                    this.Z = new int[this.O][this.t];
                    this.ba = new int[this.O][this.t];
                    this.bb = new int[this.O][this.t];
                    this.bh = 1;
                    this.b(255 / this.Q);
                    try {
                        this.b();
                        if (i != 0) {
                            continue;
                        }
                    }
                    catch (NoSuchMethodError noSuchMethodError) {
                        this.b();
                    }
                    this.M = this.createImage(this.w, this.x);
                    this.N = this.M.getGraphics();
                    return;
                }
                break;
            }
            continue Label_1747_Outer;
        }
    }
    
    void b() {
        this.b = new MemoryImageSource(this.u, this.v, new DirectColorModel(24, 16711680, 65280, 255), this.z, 0, this.u);
        String s;
        try {
            s = System.getProperty(c("T!#y$H%'kcQ."));
        }
        catch (SecurityException ex) {
            s = c("K.>");
        }
        if (!s.startsWith(c("\u000fne"))) {
            try {
                this.b.setAnimated(true);
                this.b.setFullBufferUpdates(true);
                this.A = this.createImage(this.b);
                this.b.newPixels();
                this.c = true;
            }
            catch (NoSuchMethodError noSuchMethodError) {
                this.c = false;
            }
        }
        if (!this.c) {
            this.b = null;
            this.a = new anfy(this.u, this.v, new DirectColorModel(24, 16711680, 65280, 255), this.z, 0, this.u);
            this.A = this.createImage(this.a);
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
                this.showStatus(c("w-4\u007fo\u001e") + s + c("\u001e.:l*X/ vn\u001f"));
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
    
    private final synchronized boolean c() {
        this.bc = new MediaTracker(this);
        final int[] array = new int[this.t];
        for (int i = 0; i < 2; ++i) {
            this.showStatus(c("w-4\u007fo\u001e") + String.valueOf(i + 1));
            this.B[i] = this.a(this.P[i]);
            if (this.B[i] == null) {
                this.showStatus(c("{2'wx\u001e,:ynW.28cS!2}*") + String.valueOf(i + 1));
                return false;
            }
            this.be[i] = true;
            this.bf[i] = this.B[i].getWidth(this);
            this.bg[i] = this.B[i].getHeight(this);
            if (i == 0) {
                this.bd = true;
                this.repaint();
            }
            else if (this.bf[i] != this.bf[i - 1] || this.bg[i] != this.bg[i - 1]) {
                this.showStatus(c("{2'wx\u001f`\u001cukY%&8Gk\u0013\u00018h[`!po\u001e34uo\u001e3<bo\u001f"));
            }
            if (!this.a(this.B[i], array)) {
                return false;
            }
            if (i != 0) {
                this.B[i].flush();
                this.B[i] = null;
            }
            System.gc();
            for (int j = 0; j < this.t; ++j) {
                final int n = array[j];
                this.Z[i][j] = (n >> 16 & 0xFF) << 16;
                this.ba[i][j] = (n >> 8 & 0xFF) << 16;
                this.bb[i][j] = (n & 0xFF) << 16;
            }
        }
        return true;
    }
    
    private final synchronized boolean a(final int n) {
        new MediaTracker(this);
        final Image a = this.a(this.P[n]);
        if (a == null) {
            this.showStatus(c("{2'wx\u001e,:ynW.28cS!2}*") + String.valueOf(n + 1));
            return false;
        }
        this.be[n] = true;
        final int[] array = new int[this.t];
        if (!this.a(a, array)) {
            return false;
        }
        for (int i = 0; i < this.t; ++i) {
            final int n2 = array[i];
            this.Z[n][i] = (n2 >> 16 & 0xFF) << 16;
            this.ba[n][i] = (n2 >> 8 & 0xFF) << 16;
            this.bb[n][i] = (n2 & 0xFF) << 16;
        }
        a.flush();
        System.gc();
        return true;
    }
    
    private boolean a(final Image image, final int[] array) {
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, this.u, this.v, array, 0, this.u);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {}
        return true;
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
        this.f = System.currentTimeMillis();
        if (!this.be[0]) {
            this.c();
        }
        if (this.j) {
            this.d();
            this.a(0, 1);
        }
        this.b(this.bh - 1, this.bh);
        this.showStatus("");
        System.gc();
        this.cfade();
        try {
            this.producefixed();
        }
        catch (NoSuchMethodError noSuchMethodError) {}
        this.bd = false;
        this.repaint();
        final long n = this.R - (System.currentTimeMillis() - this.f);
        if (n > 0L) {
            try {
                Thread.sleep(n);
            }
            catch (InterruptedException ex) {}
        }
        final Graphics graphics = this.getGraphics();
        if (this.m != null && !this.T) {
            this.T = this.CheckAniGIF();
        }
        if (!this.p[this.bh - 1].equalsIgnoreCase(c("p\u000f"))) {
            this.s.setCursor(12);
        }
        else {
            this.s.setCursor(0);
        }
        this.h.dr(c("_.3a"), 1, this.V);
        while (this.g != null) {
            this.cfade();
            if (++this.i == this.k) {
                System.gc();
                this.i = 0;
            }
            try {
                this.producefixed();
            }
            catch (NoSuchMethodError noSuchMethodError2) {}
            if (this.y == 1) {
                this.N.drawImage(this.A, 0, 0, this);
            }
            else {
                this.prepscaled();
                this.N.drawImage(this.A, 0, 0, this.w, this.x, this);
            }
            if (this.m != null) {
                this.prepaniframe();
            }
            graphics.drawImage(this.M, 0, 0, this);
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
        this.prepareImage(this.A, this.w, this.x, this);
        this.notifyAll();
        while ((checkImage & 0xF0) == 0x0) {
            Thread.yield();
            checkImage = this.checkImage(this.A, this.w, this.x, this);
        }
    }
    
    public synchronized void prepscaled0() {
        int checkImage = 0;
        this.prepareImage(this.B[0], this.w, this.x, this);
        this.notifyAll();
        while ((checkImage & 0xF0) == 0x0) {
            Thread.yield();
            checkImage = this.checkImage(this.B[0], this.w, this.x, this);
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
        if (this.r) {
            if (this.A != null) {
                if (this.y == 1) {
                    this.N.drawImage(this.A, 0, 0, this);
                }
                else {
                    this.prepscaled();
                    this.N.drawImage(this.A, 0, 0, this.w, this.x, this);
                }
                if (this.m != null) {
                    this.prepaniframe();
                }
                graphics.drawImage(this.M, 0, 0, this);
            }
        }
        else {
            this.waitdisplay();
        }
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final void waitdisplay() {
        final Graphics graphics = this.getGraphics();
        if (this.B != null && this.N != null && this.bd && this.B[0] != null) {
            if (this.y == 1) {
                this.N.drawImage(this.B[0], 0, 0, this);
            }
            else {
                this.prepscaled0();
                this.N.drawImage(this.B[0], 0, 0, this.w, this.x, this);
            }
            if (this.m != null) {
                this.N.drawImage(this.m, this.n, this.o, this);
            }
            this.N.setColor(Color.black);
            this.N.drawString(c("r/4|cP'{6$"), this.w / 2 - 26 + 1, this.x / 2 + 2 + 1);
            this.N.setColor(Color.white);
            this.N.drawString(c("r/4|cP'{6$"), this.w / 2 - 26, this.x / 2 + 2);
            graphics.drawImage(this.M, 0, 0, this);
        }
    }
    
    public final void cfade() {
        if (this.bi) {
            ++this.bh;
            if (this.bh > this.O) {
                this.bh = 1;
            }
            if (!this.be[this.bh % this.O]) {
                this.a(this.bh % this.O);
            }
            if (this.bj) {
                this.showStatus(this.q[this.bh - 1]);
            }
            if (!this.p[this.bh - 1].equalsIgnoreCase(c("p\u000f"))) {
                this.s.setCursor(12);
            }
            else {
                this.s.setCursor(0);
            }
            try {
                Thread.sleep(this.R);
            }
            catch (InterruptedException ex) {}
            this.bi = false;
            if (this.j) {
                this.d();
                this.a(this.bh - 1, this.bh % this.O);
            }
            this.b(this.bh - 1, this.bh % this.O);
            this.bl = 0;
        }
        final int n = this.bh - 1;
        if (!this.j) {
            if (this.bh < this.O) {
                this.c(n);
                return;
            }
            this.c(n);
        }
        else {
            if (this.bh < this.O) {
                this.c(n, this.bh);
                return;
            }
            this.c(n, 0);
        }
    }
    
    public final boolean mouseEnter(final Event event, final int n, final int n2) {
        if (!this.p[this.bh - 1].equalsIgnoreCase(c("p\u000f"))) {
            this.s.setCursor(12);
        }
        else {
            this.s.setCursor(0);
        }
        this.bj = true;
        this.showStatus(this.q[this.bh - 1]);
        return true;
    }
    
    public final boolean mouseExit(final Event event, final int n, final int n2) {
        this.bj = false;
        this.showStatus("");
        return true;
    }
    
    public final boolean mouseMove(final Event event, final int n, final int n2) {
        if (this.bk != this.bh) {
            this.showStatus(this.q[this.bh - 1]);
        }
        this.bk = this.bh;
        return true;
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        final int bt = AnFade.bt;
        final boolean v = this.V;
        boolean equalsIgnoreCase = false;
        Label_0088: {
            if (bt != 0) {
                break Label_0088;
            }
            if (!v) {
                this.h.show();
                try {
                    this.h.move(100, 100);
                }
                catch (Exception ex) {}
                this.h.toFront();
                this.h.requestFocus();
                if (bt == 0) {
                    break Label_0088;
                }
            }
            try {
                this.W = null;
                equalsIgnoreCase = this.p[this.bh - 1].equalsIgnoreCase(c("p\u000f"));
                if (bt != 0) {
                    return equalsIgnoreCase;
                }
                if (!v) {
                    this.showStatus(c("y/<vm\u001e4:8z_'08") + String.valueOf(this.bh));
                    try {
                        this.W = new URL(this.getDocumentBase(), this.p[this.bh - 1]);
                    }
                    catch (MalformedURLException ex2) {
                        this.showStatus(c("{2'wx\u001e,<vaW.2"));
                        return true;
                    }
                    AnFade anFade = this;
                    AnFade anFade2 = null;
                    Label_0221: {
                        if (bt == 0) {
                            if (this.W == null) {
                                break Label_0088;
                            }
                            this.h.dck();
                            anFade2 = this;
                            anFade = this;
                            if (bt != 0) {
                                break Label_0221;
                            }
                        }
                        if (anFade.X) {
                            this.getAppletContext().showDocument(this.W, this.getParameter(c("L%2~x_-0vkS%")));
                            if (bt == 0) {
                                break Label_0088;
                            }
                        }
                        anFade2 = this;
                    }
                    anFade2.getAppletContext().showDocument(this.W);
                }
            }
            catch (Exception ex3) {}
        }
        return equalsIgnoreCase;
    }
    
    void b(final int nsteps) {
        this.bo = new int[this.t];
        this.bp = new int[this.t];
        this.bq = new int[this.t];
        this.m_nsteps = nsteps;
        if (this.j) {
            this.bm = new int[this.t];
            this.br = new int[this.t];
            this.bs = new int[this.t];
        }
    }
    
    void d() {
        for (int i = 0; i < this.v; ++i) {
            for (int j = 0; j < this.u; ++j) {
                final int n = j - this.u / 2;
                final int n2 = i - this.v / 2;
                switch (this.m_curtain) {
                    case 6: {
                        this.bm[i * this.u + j] = (short)(-(i + j) / 5);
                        break;
                    }
                    case 1: {
                        this.bm[i * this.u + j] = (short)(-Math.sqrt(n * n + n2 * n2) / 2.0);
                        break;
                    }
                    case 2: {
                        this.bm[i * this.u + j] = (short)(-Math.random() * 55.0);
                        break;
                    }
                    case 3: {
                        this.bm[i * this.u + j] = 0;
                        break;
                    }
                    case 4: {
                        this.bm[i * this.u + j] = (short)(-i / 3);
                        break;
                    }
                    case 9: {
                        this.bm[i * this.u + j] = (short)(-j / 3);
                        break;
                    }
                    case 5: {
                        this.bm[i * this.u + j] = (short)(-j / 10);
                        break;
                    }
                    case 0: {
                        this.bm[i * this.u + j] = (short)(-(((j < this.u / 2) ? j : (this.u - j)) / 2));
                        break;
                    }
                    case 7: {
                        this.bm[i * this.u + j] = (short)(-(((i < this.v / 2) ? i : (this.v - i)) / 2));
                        break;
                    }
                }
            }
        }
    }
    
    void a(final int n, final int n2) {
        final int[] array = this.Z[n];
        final int[] array2 = this.ba[n];
        final int[] array3 = this.bb[n];
        final int[] array4 = this.Z[n2];
        final int[] array5 = this.ba[n2];
        final int[] array6 = this.bb[n2];
        final int[] br = this.br;
        final int[] bs = this.bs;
        for (int t = this.t, i = 0; i < t; ++i) {
            bs[i] = (array4[i] | array5[i] >> 8 | array6[i] >> 16);
            br[i] = (array[i] | array2[i] >> 8 | array3[i] >> 16);
        }
    }
    
    void b(final int n, final int n2) {
        final int[] array = this.Z[n];
        final int[] array2 = this.ba[n];
        final int[] array3 = this.bb[n];
        final int[] array4 = this.Z[n2];
        final int[] array5 = this.ba[n2];
        final int[] array6 = this.bb[n2];
        final int[] bo = this.bo;
        final int[] bp = this.bp;
        final int[] bq = this.bq;
        for (int t = this.t, i = 0; i < t; ++i) {
            bo[i] = (array4[i] - array[i]) / this.m_nsteps;
            bp[i] = (array5[i] - array2[i]) / this.m_nsteps;
            bq[i] = (array6[i] - array3[i]) / this.m_nsteps;
        }
    }
    
    void c(final int n, final int n2) {
        int n3 = 0;
        final int[] array = this.Z[n];
        final int[] array2 = this.ba[n];
        final int[] array3 = this.bb[n];
        final int[] bo = this.bo;
        final int[] bp = this.bp;
        final int[] bq = this.bq;
        final int[] z = this.z;
        final int[] bm = this.bm;
        final int bl = this.bl;
        final int[] br = this.br;
        final int[] bs = this.bs;
        for (int i = 0; i < this.t; ++i) {
            final int n4 = bm[i] + bl;
            if (n4 >= 0 && n4 < this.m_nsteps) {
                ++n3;
                z[i] = ((array[i] + bo[i] * n4 & 0xFF0000) | array2[i] + bp[i] * n4 >> 16 << 8 | array3[i] + bq[i] * n4 >> 16);
            }
            else if (n4 >= this.m_nsteps) {
                z[i] = bs[i];
            }
            else {
                z[i] = br[i];
            }
        }
        ++this.bl;
        if (n3 == 0) {
            ++this.m_curtain;
            if (this.m_curtain > this.bn) {
                this.m_curtain %= this.bn + 1;
            }
            this.bi = true;
        }
    }
    
    void c(final int n) {
        final int[] array = this.Z[n];
        final int[] array2 = this.ba[n];
        final int[] array3 = this.bb[n];
        final int[] bo = this.bo;
        final int[] bp = this.bp;
        final int[] bq = this.bq;
        final int[] z = this.z;
        final int bl = this.bl;
        for (int t = this.t, i = 0; i < t; ++i) {
            z[i] = ((array[i] + bo[i] * bl & 0xFF0000) | array2[i] + bp[i] * bl >> 16 << 8 | array3[i] + bq[i] * bl >> 16);
        }
        ++this.bl;
        if (this.bl > this.m_nsteps) {
            this.bi = true;
        }
    }
    
    public AnFade() {
        this.c = false;
        this.j = false;
        this.r = false;
        this.y = 1;
        this.S = false;
        this.T = false;
        this.V = false;
        this.X = false;
        this.bd = false;
        this.bh = 1;
        this.bi = false;
        this.bj = false;
        this.m_nsteps = 32;
        this.bn = 10;
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
                    c2 = '>';
                    break;
                }
                case 1: {
                    c2 = '@';
                    break;
                }
                case 2: {
                    c2 = 'U';
                    break;
                }
                case 3: {
                    c2 = '\u0018';
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
