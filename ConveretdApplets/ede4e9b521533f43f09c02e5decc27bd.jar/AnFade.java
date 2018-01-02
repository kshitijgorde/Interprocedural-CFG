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
    final String U = ":\u0011&7\u001d\u000fA4\"X=\u000042\u0017[\"?.\u001b\u0018\bvs\u000f\f\u0016x:\u0016\u001d";
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
            this.showStatus(c("?\u000e8|\f[\u001336\u0017\r\u0004v,\u000f\fO75\u001e\u0002\u00153:\u0015U\u000296X\u0018\u00133?\u0011\u000f\u0012v7\u0011\u0015\u0004v2\u0016[)\u0002\u00164Z"));
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
        final String parameter = this.getParameter(c("\u0018\u00133?\u0011\u000f\u0012"));
        String protocol;
        final String s = protocol = parameter;
        String c = null;
        Label_0104: {
            Label_0073: {
                Label_0069: {
                    if (i == 0) {
                        if (s == null) {
                            break Label_0069;
                        }
                        final String s2;
                        protocol = (s2 = parameter);
                    }
                    if (i != 0) {
                        break Label_0104;
                    }
                    if (s.startsWith(c(":\u0011&7\u001d\u000fA4\"X=\u000042\u0017[\"?.\u001b\u0018\bvs\u000f\f\u0016x:\u0016\u001d"))) {
                        break Label_0073;
                    }
                    this.a();
                    if (i == 0) {
                        break Label_0073;
                    }
                }
                this.a();
            }
            (this.h = new Lware(this, c("8\u00139(\u000b=\u00002>X\u001a\u0011&7\u001d\u000f"))).hide();
            try {
                protocol = this.getDocumentBase().getProtocol();
                c = protocol;
            }
            catch (SecurityException ex) {
                c = c("\u001d\b:>");
            }
        }
        String host;
        try {
            host = this.getDocumentBase().getHost();
        }
        catch (SecurityException ex2) {
            host = "";
        }
        host.toLowerCase();
        c.toLowerCase();
        Container parent = null;
        Label_0629: {
            String y = null;
            Label_0594: {
                final int length;
                Label_0564: {
                    final String parameter2;
                    Label_0556: {
                        Label_0538: {
                            Label_0237: {
                                final String s3;
                                Label_0236: {
                                    boolean b = false;
                                    Label_0228: {
                                        Label_0214: {
                                            if (i == 0) {
                                                Label_0204: {
                                                    if (!c.equals(c("\u001d\b:>"))) {
                                                        int equals;
                                                        final int n = equals = ((b = (host.length() != 0)) ? 1 : 0);
                                                        if (i == 0) {
                                                            if (n < 1) {
                                                                break Label_0204;
                                                            }
                                                            final boolean b2;
                                                            equals = ((b2 = (b = host.startsWith(c("\u0017\u000e5:\u0014")))) ? 1 : 0);
                                                        }
                                                        if (i == 0) {
                                                            if (n != 0) {
                                                                break Label_0204;
                                                            }
                                                            b = ((equals = (host.equals(c("JSauHUQxj")) ? 1 : 0)) != 0);
                                                        }
                                                        if (i != 0) {
                                                            break Label_0228;
                                                        }
                                                        if (equals == 0) {
                                                            break Label_0214;
                                                        }
                                                    }
                                                }
                                                this.V = true;
                                            }
                                            if (i == 0) {
                                                break Label_0538;
                                            }
                                        }
                                        s3 = host;
                                        if (i != 0) {
                                            break Label_0236;
                                        }
                                        b = s3.startsWith(c("\f\u0016!u"));
                                    }
                                    if (!b) {
                                        break Label_0237;
                                    }
                                    host.substring(4);
                                }
                                host = s3;
                            }
                            final String s4 = parameter2 = this.getParameter(c("\t\u000418\u0017\u001f\u0004"));
                            if (i != 0) {
                                break Label_0556;
                            }
                            if (parameter2 != null) {
                                length = s4.length();
                                if (i != 0) {
                                    break Label_0564;
                                }
                                if (length > 5) {
                                    s4.toLowerCase();
                                    int n2 = 1;
                                    try {
                                        int n3 = 0;
                                        while (true) {
                                            while (true) {
                                                Label_0310: {
                                                    if (i == 0) {
                                                        break Label_0310;
                                                    }
                                                    if (s4.charAt(n3) == '+') {
                                                        ++n2;
                                                    }
                                                    ++n3;
                                                }
                                                if (n3 < s4.length()) {
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
                                    final int[] array = new int[n2];
                                    final int n4 = n2;
                                    if (i == 0 && n4 == 1) {
                                        array[0] = s4.length();
                                        if (i != 0) {
                                            goto Label_0360;
                                        }
                                    }
                                    else {
                                        int n5 = n4;
                                        try {
                                            int n6 = 0;
                                            while (true) {
                                                while (true) {
                                                    Label_0396: {
                                                        if (i == 0) {
                                                            break Label_0396;
                                                        }
                                                        if (s4.charAt(n6) == '+') {
                                                            array[n5] = n6;
                                                            ++n5;
                                                        }
                                                        ++n6;
                                                    }
                                                    if (n6 < s4.length()) {
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
                                        array[n5] = s4.length();
                                    }
                                    final String[] array2 = new String[n2];
                                    int n7 = 0;
                                    int n8 = 0;
                                    int j;
                                    while (true) {
                                        while (true) {
                                            Label_0476: {
                                                if (i == 0) {
                                                    break Label_0476;
                                                }
                                                try {
                                                    array2[n8] = s4.substring(n7, array[n8]);
                                                }
                                                catch (StringIndexOutOfBoundsException ex5) {}
                                                n7 = array[n8] + 1;
                                                ++n8;
                                            }
                                            if (n8 < n2) {
                                                continue;
                                            }
                                            break;
                                        }
                                        j = 0;
                                        if (i != 0) {
                                            if (i != 0) {
                                                continue;
                                            }
                                        }
                                        break;
                                    }
                                    while (j < n2) {
                                        if (host.equals(this.h.dr(array2[j], 0, this.V))) {
                                            this.V = true;
                                        }
                                        ++j;
                                    }
                                }
                            }
                        }
                        final String parameter3;
                        y = (parameter3 = this.getParameter(c("\t\u000415\u001d\f\u0007$:\u0015\u001e")));
                        if (i != 0) {
                            break Label_0594;
                        }
                    }
                    parameter2.equalsIgnoreCase(c("\"$\u0005"));
                }
                if (length != 0) {
                    this.X = true;
                }
                this.Y = this.getParameter(c("6\b8\b!5\""));
                parent = this;
                if (i != 0) {
                    break Label_0629;
                }
                y = this.Y;
            }
            if (y == null) {
                this.Y = c("JQ");
            }
            this.e = Integer.valueOf(this.Y);
            this.r = false;
            parent = this.getParent();
        }
        Container container = parent;
        while (true) {
            while (true) {
                Label_0646: {
                    if (i == 0) {
                        break Label_0646;
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
        final String parameter4 = this.getParameter(c("\u0014\u00173)\u0011\u0016\u0006"));
        String s7;
        String f;
        String s6;
        final String s5 = s6 = (f = (s7 = parameter4));
        Label_0859: {
            Label_0833: {
                if (i == 0) {
                    AnFade anFade = null;
                    Label_0825: {
                        if (s5 != null) {
                            final String s8 = parameter4;
                            if (i != 0) {
                                break Label_0833;
                            }
                            if (!s8.equalsIgnoreCase(c("5."))) {
                                this.m = this.a(parameter4);
                                anFade = this;
                                if (i != 0) {
                                    break Label_0825;
                                }
                                if (this.m != null) {
                                    final String parameter5;
                                    String s9 = parameter5 = this.getParameter(c("\u0014\u00173)\u0011\u0016\u0006\u000e"));
                                    if (i == 0) {
                                        if (parameter5 == null) {
                                            s9 = "0";
                                        }
                                        this.n = Integer.valueOf(s9);
                                        this.getParameter(c("\u0014\u00173)\u0011\u0016\u0006\u000f"));
                                    }
                                    final String s11;
                                    String s10 = s11 = parameter5;
                                    if (i != 0 || s11 == null) {
                                        s10 = s11;
                                    }
                                    this.o = Integer.valueOf(s10);
                                }
                            }
                        }
                        this.D = this.getParameter(c("\t\u0004%"));
                        anFade = this;
                    }
                    final String s12;
                    s6 = (s12 = (f = (s7 = anFade.D)));
                }
                if (i != 0) {
                    break Label_0859;
                }
            }
            if (s5 == null) {
                this.D = "1";
            }
            this.E = this.getParameter(c("\b\u00113>\u001c"));
            f = (s6 = (s7 = this.E));
        }
        if (i == 0) {
            if (s6 == null) {
                this.E = "8";
            }
            this.F = this.getParameter(c("\u000b\u0000#(\u001d"));
            s7 = (f = this.F);
        }
        Label_1039: {
            AnFade anFade2 = null;
            Label_1011: {
                if (i == 0) {
                    if (f == null) {
                        this.F = c("JTfk");
                    }
                    this.y = Integer.valueOf(this.D);
                    this.Q = Integer.valueOf(this.E);
                    this.R = Integer.valueOf(this.F);
                    this.I = this.getParameter(c("\u0016\u0004;?\u001d\u0017\u0000/"));
                    this.J = this.getParameter(c("\u000b\u0013?4\n\u0012\u0015/"));
                    this.K = this.getParameter(c("\u000b\u00139<\n\u001e\u0012%2\u000e\u001e\u00077?\u001d"));
                    anFade2 = this;
                    if (i != 0) {
                        break Label_1011;
                    }
                    s7 = this.K;
                }
                if (s7 == null) {
                    this.j = false;
                    if (i == 0) {
                        break Label_1039;
                    }
                }
                anFade2 = this;
            }
            final boolean equalsIgnoreCase = this.K.equalsIgnoreCase(c("\u0002\u0004%"));
            if (i == 0 && !equalsIgnoreCase) {}
            anFade2.j = equalsIgnoreCase;
        }
        this.O = 1;
        while (true) {
            while (true) {
                Label_1059: {
                    if (i == 0) {
                        break Label_1059;
                    }
                    final AnFade anFade3 = this;
                    final AnFade anFade4 = this;
                    anFade3.O = anFade4.O + 1;
                }
                if (this.getParameter(c("\u0012\f7<\u001d") + String.valueOf(this.O)) != null) {
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
                    this.showStatus(c(":\r;4\u000b\u000fAd{\u0011\u0016\u00001>\u000b[\u00133*\r\u0012\u00133?Y"));
                } while (i == 0);
            }
            this.P = new String[this.O];
            this.p = new String[this.O];
            this.q = new String[this.O];
        }
        int n9 = o;
        int n10;
        while (true) {
            while (true) {
                Label_1213: {
                    if (i == 0) {
                        break Label_1213;
                    }
                    this.P[n9] = this.getParameter(c("\u0012\f7<\u001d") + String.valueOf(n9 + 1));
                    ++n9;
                }
                if (n9 < this.O) {
                    continue;
                }
                break;
            }
            n10 = 0;
            if (i != 0) {
                if (i != 0) {
                    continue;
                }
            }
            break;
        }
        while (true) {
            AnFade anFade5;
            if (n10 >= this.O) {
                this.k = Integer.valueOf(this.I);
                this.l = Integer.valueOf(this.J);
                anFade5 = this;
                if (i == 0) {
                    break;
                }
            }
            else {
                this.p[n10] = this.getParameter(c("\u0017\b80") + String.valueOf(n10 + 1));
                anFade5 = this;
            }
            anFade5.q[n10] = this.getParameter(c("\b\u00157/\r\b\f%<") + String.valueOf(n10 + 1));
            ++n10;
        }
        int k;
        final int n11 = k = this.k;
        if (i == 0) {
            if (n11 < 0) {
                this.k = 0;
            }
            final int l;
            k = (l = this.l);
        }
        final int n12 = 10;
        int n14 = 0;
        int y2 = 0;
        int n13 = 0;
        int n18 = 0;
        int n17 = 0;
        int n15 = 0;
        Label_1421: {
            Label_1415: {
                if (i == 0) {
                    if (n11 > n12) {
                        this.l = 10;
                        if (i == 0) {
                            break Label_1415;
                        }
                    }
                    n13 = (k = (y2 = (n14 = this.l)));
                    final int n16;
                    n15 = (n16 = (n17 = (n18 = 1)));
                    if (i != 0) {
                        break Label_1421;
                    }
                }
                if (k < n12) {
                    this.l = 1;
                }
            }
            y2 = (n13 = (n14 = this.y));
            n17 = (n15 = (n18 = 8));
        }
        int n22 = 0;
        Label_1648: {
            int n19 = 0;
            int n21 = 0;
            Label_1513: {
                Label_1503: {
                    int q = 0;
                    int n20 = 0;
                    Label_1490: {
                        Label_1470: {
                            Label_1458: {
                                if (i == 0) {
                                    if (n13 > n15) {
                                        this.y = 8;
                                        if (i == 0) {
                                            break Label_1458;
                                        }
                                    }
                                    n14 = (y2 = this.y);
                                    n18 = (n17 = 1);
                                }
                                if (i != 0) {
                                    break Label_1470;
                                }
                                if (y2 < n17) {
                                    this.y = 1;
                                }
                            }
                            q = (n14 = (n19 = this.Q));
                            n20 = (n18 = (n21 = 255));
                            if (i != 0) {
                                break Label_1490;
                            }
                        }
                        if (n14 > n18) {
                            this.Q = 255;
                            if (i == 0) {
                                break Label_1503;
                            }
                        }
                        n19 = (q = this.Q);
                        n21 = (n20 = 1);
                    }
                    if (i != 0) {
                        break Label_1513;
                    }
                    if (q < n20) {
                        this.Q = 1;
                    }
                }
                n22 = (n19 = this.R);
                if (i != 0) {
                    break Label_1648;
                }
                n21 = 1;
            }
            if (n19 < n21) {
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
            n22 = 0;
        }
        int n23 = n22;
    Label_1663_Outer:
        while (true) {
            while (true) {
                Label_1666: {
                    if (i == 0) {
                        break Label_1666;
                    }
                    this.be[n23] = false;
                    ++n23;
                }
                if (n23 >= this.O) {
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
            continue Label_1663_Outer;
        }
    }
    
    void b() {
        this.b = new MemoryImageSource(this.u, this.v, new DirectColorModel(24, 16711680, 65280, 255), this.z, 0, this.u);
        String s;
        try {
            s = System.getProperty(c("\u0011\u0000 :V\r\u0004$(\u0011\u0014\u000f"));
        }
        catch (SecurityException ex) {
            s = c("\u000e\u000f=");
        }
        if (!s.startsWith(c("JOf"))) {
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
                this.showStatus(c("2\f7<\u001d[") + s + c("[\u000f9/X\u001d\u000e#5\u001cZ"));
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
            this.showStatus(c("2\f7<\u001d[") + String.valueOf(i + 1));
            this.B[i] = this.a(this.P[i]);
            if (this.B[i] == null) {
                this.showStatus(c(">\u0013$4\n[\r9:\u001c\u0012\u000f1{\u0011\u0016\u00001>X") + String.valueOf(i + 1));
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
                this.showStatus(c(">\u0013$4\nZA\u001f6\u0019\u001c\u0004%{5.2\u0002{\u001a\u001eA\"3\u001d[\u001276\u001d[\u0012?!\u001dZ"));
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
            this.showStatus(c(">\u0013$4\n[\r9:\u001c\u0012\u000f1{\u0011\u0016\u00001>X") + String.valueOf(n + 1));
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
        if (!this.p[this.bh - 1].equalsIgnoreCase(c("5."))) {
            this.s.setCursor(12);
        }
        else {
            this.s.setCursor(0);
        }
        this.h.dr(c("\u001a\u000f0\""), 1, this.V);
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
            this.N.drawString(c("7\u000e7?\u0011\u0015\u0006xuV"), this.w / 2 - 26 + 1, this.x / 2 + 2 + 1);
            this.N.setColor(Color.white);
            this.N.drawString(c("7\u000e7?\u0011\u0015\u0006xuV"), this.w / 2 - 26, this.x / 2 + 2);
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
            if (!this.p[this.bh - 1].equalsIgnoreCase(c("5."))) {
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
        if (!this.p[this.bh - 1].equalsIgnoreCase(c("5."))) {
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
                equalsIgnoreCase = this.p[this.bh - 1].equalsIgnoreCase(c("5."));
                if (bt != 0) {
                    return equalsIgnoreCase;
                }
                if (!v) {
                    this.showStatus(c("<\u000e?5\u001f[\u00159{\b\u001a\u00063{") + String.valueOf(this.bh));
                    try {
                        this.W = new URL(this.getDocumentBase(), this.p[this.bh - 1]);
                    }
                    catch (MalformedURLException ex2) {
                        this.showStatus(c(">\u0013$4\n[\r?5\u0013\u0012\u000f1"));
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
                            this.getAppletContext().showDocument(this.W, this.getParameter(c("\t\u00041=\n\u001a\f35\u0019\u0016\u0004")));
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
                    c2 = '{';
                    break;
                }
                case 1: {
                    c2 = 'a';
                    break;
                }
                case 2: {
                    c2 = 'V';
                    break;
                }
                case 3: {
                    c2 = '[';
                    break;
                }
                default: {
                    c2 = 'x';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
