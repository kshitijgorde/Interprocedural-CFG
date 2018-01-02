import java.awt.image.PixelGrabber;
import java.awt.FontMetrics;
import java.awt.Font;
import java.io.InputStream;
import java.util.zip.Adler32;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.awt.Event;
import java.awt.image.ImageProducer;
import java.awt.image.ImageObserver;
import java.util.Observable;
import java.awt.Graphics;
import java.awt.Container;
import java.awt.Color;
import java.awt.Frame;
import java.util.Random;
import java.awt.Image;
import java.util.Observer;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class xatshowApplet extends Applet implements Observer
{
    static final int a = 4096;
    static final boolean[] b;
    static final int c = 0;
    static final int d = 1;
    static final int e = 2;
    static final int f = 3;
    static final int g = 0;
    static final int h = 9;
    static final int i = 12;
    static final int j = 13;
    static final int k = 4;
    static final int l = 6;
    static final int m = 5;
    static final int n = 2;
    static final int o = 14;
    static final int p = 3;
    static final int q = 7;
    static final int r = 8;
    static final int s = 15;
    static final int t = 15;
    static final int u = 62460;
    static final int v = 0;
    static final int w = 1;
    static final int x = 2;
    int y;
    int z;
    int A;
    int B;
    String C;
    int D;
    int E;
    String[] F;
    int[] G;
    int[] H;
    int[] I;
    boolean J;
    int K;
    int L;
    Image M;
    Image N;
    int[] O;
    int[] P;
    Image Q;
    b R;
    int S;
    int T;
    int U;
    long V;
    int W;
    int X;
    int Y;
    private Random Z;
    private Frame ba;
    static /* synthetic */ Class bb;
    
    public xatshowApplet() {
        this.J = true;
        this.K = 3;
        this.L = 0;
        this.M = null;
        this.N = null;
        this.O = null;
        this.P = null;
        this.Q = null;
        this.R = null;
        this.S = 0;
        this.T = 0;
        this.U = 0;
        this.V = 0L;
        this.W = 0;
        this.X = 0;
        this.Y = 0;
    }
    
    public void start() {
        super.start();
        this.J = true;
        this.repaint();
    }
    
    public void stop() {
        super.stop();
        this.J = false;
    }
    
    public void init() {
        final int c = a.c;
        Container container = super.getParent();
        int n2 = 0;
        int e = 0;
        int n = 0;
        Label_0041: {
            while (true) {
                Label_0018: {
                    if (c == 0) {
                        break Label_0018;
                    }
                    container = container.getParent();
                }
                if (container != null) {
                    n = (e = (n2 = ((container instanceof Frame) ? 1 : 0)));
                    if (c != 0 || c != 0) {
                        break Label_0041;
                    }
                    if (n == 0) {
                        continue;
                    }
                }
                break;
            }
            final boolean b;
            e = ((b = ((n2 = ((container instanceof Frame) ? 1 : 0)) != 0)) ? 1 : 0);
        }
        if (c == 0) {
            if (n != 0) {
                this.ba = (Frame)container;
            }
            this.Z = new Random();
            this.a();
            n2 = (e = this.E);
        }
        Label_0123: {
            int u;
            while (true) {
                while (true) {
                    Label_0105: {
                        if (c != 0) {
                            break Label_0105;
                        }
                        if (e == 0) {
                            this.U = 0;
                            if (c == 0) {
                                break Label_0123;
                            }
                        }
                        final xatshowApplet xatshowApplet = this;
                        n2 = (xatshowApplet.Z.nextInt() >>> 1) % this.L;
                    }
                    u = n2;
                    if (u == this.U) {
                        continue;
                    }
                    break;
                }
                final xatshowApplet xatshowApplet = this;
                if (c != 0) {
                    continue;
                }
                break;
            }
            this.U = u;
        }
        int n4;
        final int n3 = n4 = this.L;
        Label_0230: {
            if (c == 0) {
                if (n3 != 0) {
                    this.N = this.a(this.F[this.U]);
                    if (c == 0) {
                        break Label_0230;
                    }
                }
                this.K = 0;
                final int s;
                n4 = (s = this.S);
            }
            xatshowApplet xatshowApplet2 = null;
            Label_0205: {
                if (c == 0) {
                    if (n3 == 0) {
                        this.S = this.size().width;
                    }
                    xatshowApplet2 = this;
                    if (c != 0) {
                        break Label_0205;
                    }
                    n4 = this.T;
                }
                if (n4 == 0) {
                    this.T = this.size().height;
                }
                xatshowApplet2 = this;
            }
            xatshowApplet2.N = this.a(d("A\t&?dV\u001f\u0013<|U\r&lIK\u001a=>6"), d("m\u00007>i\u0019\t ),W\u0007r%aX\u000f7?,M\u0007r(eJ\u0018>-u"), this.S, this.T);
        }
        this.P = this.a(this.N, this.S, this.T);
        this.M = this.createImage(this.S, this.T);
        final Graphics graphics = this.M.getGraphics();
        graphics.setColor(new Color(this.D));
        graphics.fillRect(0, 0, this.S, this.T);
        this.O = this.a(this.M, this.S, this.T);
        this.Q = this.M;
        this.repaint();
        this.validate();
        this.V = System.currentTimeMillis();
        this.a(this.U, this.O, this.P);
    }
    
    public void update(final Observable observable, final Object o) {
        final int c = a.c;
        int n2;
        int e;
        final int n = e = (n2 = (this.J ? 1 : 0));
        if (c == 0) {
            if (n == 0) {
                return;
            }
            final int n3;
            e = (n3 = (n2 = this.L));
        }
        if (c == 0) {
            if (n == 0) {
                return;
            }
            n2 = (e = this.E);
        }
        final int n4 = 1;
        xatshowApplet xatshowApplet = null;
        Label_0170: {
            Label_0136: {
                if (c == 0) {
                    if (e == n4) {
                    Block_7:
                        while (true) {
                            final int i = (this.Z.nextInt() >>> 1) % this.L;
                            while (i != this.U) {
                                this.U = i;
                                if (c == 0) {
                                    break Block_7;
                                }
                            }
                        }
                        if (c == 0) {
                            break Label_0136;
                        }
                    }
                    ++this.U;
                    xatshowApplet = this;
                    if (c != 0) {
                        break Label_0170;
                    }
                    n2 = this.U;
                    final int l = this.L;
                }
                if (n2 >= n4) {
                    this.U = 0;
                    xatshowApplet = this;
                    if (c != 0) {
                        break Label_0170;
                    }
                    if (this.y != 0) {
                        this.G[this.U] = Integer.MAX_VALUE;
                    }
                }
            }
            this.M = this.N;
            this.O = this.P;
            this.N = this.a(this.F[this.U]);
            xatshowApplet = this;
        }
        xatshowApplet.P = this.a(this.N, this.S, this.T);
        final long n5 = System.currentTimeMillis() - this.V;
        if (c == 0) {
            if (n5 < this.G[this.U]) {
                System.gc();
                try {
                    Thread.currentThread();
                    Thread.sleep(this.G[this.U] - n5);
                }
                catch (InterruptedException ex) {
                    return;
                }
            }
            this.V = System.currentTimeMillis();
            this.Y = this.U;
            this.a(this.U, this.O, this.P);
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.Q != null) {
            graphics.drawImage(this.Q, 0, 0, this);
        }
    }
    
    private void a(final int n, final int[] array, final int[] array2) {
        final int c = a.c;
        this.X = ((this.X | ~this.H[n]) & 0xF3FC);
        final int x = this.X;
        final int n2 = 62460;
        if (c == 0) {
            if (x == n2) {
                this.X = (~this.H[n] & 0xF3FC);
            }
            this.W = 0;
            final int n3 = this.Z.nextInt() >>> 1;
        }
        int n5;
        final int n4 = n5 = x % n2;
        int n8 = 0;
    Label_0129_Outer:
        while (true) {
            while (true) {
                Label_0149: {
                    if ((1 << n5 & (this.X | 0xFFFF0C03)) == 0x0) {
                        this.X |= 1 << n5;
                        this.W = 1 << n5;
                        if (c == 0) {
                            break Label_0149;
                        }
                    }
                    n5 = (n5 + 1) % 16;
                    final int n6;
                    int w = n6 = n5;
                    final int n7 = n4;
                    if (c == 0) {
                        if (n8 == n7) {
                            break Label_0149;
                        }
                        w = n5;
                    }
                    if (w == n7) {
                        continue Label_0129_Outer;
                    }
                }
                int w;
                n8 = (w = this.W);
                if (c == 0) {
                    break;
                }
                continue;
            }
        }
        if (c == 0) {
            Label_0780: {
                switch (n8) {
                    case 0: {
                        this.R = new i(this.O, this.P, this.S, this.T, this.D, this.I[n]);
                        if (c != 0) {
                            break Label_0780;
                        }
                        break;
                    }
                    case 4: {
                        this.R = new d(this.O, this.P, this.S, this.T, this.D, this.I[n]);
                        if (c != 0) {
                            break Label_0780;
                        }
                        break;
                    }
                    case 8: {
                        this.R = new h(this.O, this.P, this.S, this.T, this.D, this.I[n]);
                        if (c != 0) {
                            break Label_0780;
                        }
                        break;
                    }
                    case 16: {
                        this.R = new k(this.O, this.P, this.S, this.T, this.D, this.I[n]);
                        if (c != 0) {
                            break Label_0780;
                        }
                        break;
                    }
                    case 32: {
                        this.R = new c(this.O, this.P, this.S, this.T, this.D, this.I[n]);
                        if (c != 0) {
                            break Label_0780;
                        }
                        break;
                    }
                    case 64: {
                        this.R = new m(this.O, this.P, this.S, this.T, this.D, this.I[n]);
                        if (c != 0) {
                            break Label_0780;
                        }
                        break;
                    }
                    case 128: {
                        this.R = new n(this.O, this.P, this.S, this.T, this.D, this.I[n]);
                        if (c != 0) {
                            break Label_0780;
                        }
                        break;
                    }
                    case 256: {
                        this.R = new o(this.O, this.P, this.S, this.T, this.D, this.I[n]);
                        if (c != 0) {
                            break Label_0780;
                        }
                        break;
                    }
                    case 512: {
                        this.R = new e(this.O, this.P, this.S, this.T, this.D, this.I[n]);
                        if (c != 0) {
                            break Label_0780;
                        }
                        break;
                    }
                    case 4096: {
                        this.R = new g(this.O, this.P, this.S, this.T, this.D, this.I[n]);
                        if (c != 0) {
                            break Label_0780;
                        }
                        break;
                    }
                    case 8192: {
                        this.R = new j(this.O, this.P, this.S, this.T, this.D, this.I[n]);
                        if (c != 0) {
                            break Label_0780;
                        }
                        break;
                    }
                    case 16384: {
                        this.R = new f(this.O, this.P, this.S, this.T, this.D, this.I[n]);
                        if (c != 0) {
                            break Label_0780;
                        }
                        break;
                    }
                    case 32768: {
                        this.R = new l(this.O, this.P, this.S, this.T, this.D, this.I[n]);
                        break;
                    }
                }
            }
            this.R.addObserver(this);
            this.prepareImage(this.Q = this.createImage(this.R), this);
        }
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        final int c = a.c;
        String s = new String();
        final String c2 = this.C;
        Label_0298: {
            if (c == 0) {
                if (c2 != null) {
                    final int i;
                    int k;
                    final int n3 = k = (i = this.K);
                    Label_0279: {
                        final int compareTo;
                        Label_0097: {
                            final int j;
                            final int n4;
                            Label_0084: {
                                Label_0078: {
                                    Label_0074: {
                                        if (c == 0) {
                                            if (n3 != 2) {
                                                j = this.K;
                                                n4 = 3;
                                                if (c != 0) {
                                                    break Label_0084;
                                                }
                                                if (j != n4) {
                                                    break Label_0074;
                                                }
                                            }
                                            compareTo = this.C.compareTo(d("W\u0007<)"));
                                        }
                                        if (c != 0) {
                                            break Label_0078;
                                        }
                                        if (n3 == 0) {
                                            return true;
                                        }
                                    }
                                    k = this.K;
                                }
                                if (c != 0) {
                                    break Label_0097;
                                }
                            }
                            if (j != n4) {
                                break Label_0279;
                            }
                            this.C.indexOf(42, 0);
                        }
                        final int n5 = compareTo;
                        if (c == 0) {
                            if (n5 != -1) {
                                String s2 = new String();
                                final char[] charArray = this.C.toCharArray();
                                int n6 = 0;
                                int n7;
                                while (true) {
                                    while (true) {
                                        Label_0164: {
                                            if (c == 0) {
                                                break Label_0164;
                                            }
                                            s2 += charArray[n6];
                                            ++n6;
                                        }
                                        if (n6 < n5) {
                                            continue;
                                        }
                                        break;
                                    }
                                    s2 += String.valueOf(this.Y);
                                    n7 = n5 + 1;
                                    if (c != 0) {
                                        continue;
                                    }
                                    break;
                                }
                                while (true) {
                                    while (true) {
                                        Label_0242: {
                                            if (c == 0) {
                                                break Label_0242;
                                            }
                                            s2 += charArray[n7];
                                            ++n7;
                                        }
                                        if (n7 < this.C.length()) {
                                            continue;
                                        }
                                        break;
                                    }
                                    s = s2;
                                    if (c != 0) {
                                        continue;
                                    }
                                    break;
                                }
                                if (c == 0) {
                                    break Label_0298;
                                }
                            }
                            s = this.C;
                        }
                        if (c == 0) {
                            break Label_0298;
                        }
                    }
                    s = d("Q\u001c&<6\u0016G%;{\u0017\u001038\"Z\u0007?ctX\u001c!$cNGm;i[");
                    if (c == 0) {
                        break Label_0298;
                    }
                }
                d("Q\u001c&<6\u0016G%;{\u0017\u001038\"Z\u0007?ctX\u001c!$cNGm;i[");
            }
            s = c2;
            try {
                this.getAppletContext().showDocument(new URL(this.getDocumentBase(), s));
            }
            catch (Exception ex) {
                return true;
            }
        }
        return true;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        final int c = a.c;
        xatshowApplet xatshowApplet = this;
        if (c == 0) {
            if (this.C != null) {
                int n4;
                final int n3 = n4 = this.K;
                xatshowApplet xatshowApplet2 = null;
                Label_0288: {
                    final int k;
                    final int n5;
                    Label_0074: {
                        Label_0065: {
                            if (c == 0) {
                                if (n3 != 2) {
                                    k = this.K;
                                    n5 = 3;
                                    if (c != 0) {
                                        break Label_0074;
                                    }
                                    if (k != n5) {
                                        break Label_0065;
                                    }
                                }
                                this.C.compareTo(d("W\u0007<)"));
                            }
                            if (c != 0) {
                                break Label_0074;
                            }
                            if (n3 == 0) {
                                return true;
                            }
                        }
                        xatshowApplet2 = this;
                        if (c != 0) {
                            break Label_0288;
                        }
                        n4 = this.K;
                    }
                    if (k == n5) {
                        final int index = this.C.indexOf(42, 0);
                        Label_0264: {
                            if (index != -1) {
                                String s = new String();
                                final char[] charArray = this.C.toCharArray();
                                int n6 = 0;
                                int n7;
                                while (true) {
                                    while (true) {
                                        Label_0150: {
                                            if (c == 0) {
                                                break Label_0150;
                                            }
                                            s += charArray[n6];
                                            ++n6;
                                        }
                                        if (n6 < index) {
                                            continue;
                                        }
                                        break;
                                    }
                                    s += String.valueOf(this.Y);
                                    n7 = index + 1;
                                    if (c != 0) {
                                        continue;
                                    }
                                    break;
                                }
                                while (true) {
                                    while (true) {
                                        Label_0228: {
                                            if (c == 0) {
                                                break Label_0228;
                                            }
                                            s += charArray[n7];
                                            ++n7;
                                        }
                                        if (n7 < this.C.length()) {
                                            continue;
                                        }
                                        break;
                                    }
                                    this.showStatus(s);
                                    if (c != 0) {
                                        continue;
                                    }
                                    break;
                                }
                                if (c == 0) {
                                    break Label_0264;
                                }
                            }
                            this.showStatus(this.C);
                        }
                        this.ba.setCursor(12);
                        if (c == 0) {
                            return true;
                        }
                    }
                    this.showStatus(d("Q\u001c&<6\u0016G%;{\u0017\u001038\"Z\u0007?ctX\u001c!$cNG"));
                    xatshowApplet2 = this;
                }
                xatshowApplet2.ba.setCursor(12);
                if (c == 0) {
                    return true;
                }
            }
            this.showStatus(d("Q\u001c&<6\u0016G%;{\u0017\u001038\"Z\u0007?ctX\u001c!$cNG"));
            xatshowApplet = this;
        }
        xatshowApplet.ba.setCursor(12);
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        final int c = a.c;
        xatshowApplet xatshowApplet = this;
        if (c == 0) {
            if (this.K == 3) {
                xatshowApplet xatshowApplet2 = this;
                if (c == 0) {
                    if (this.C == null) {
                        return true;
                    }
                    this.showStatus("");
                    xatshowApplet2 = this;
                }
                xatshowApplet2.ba.setCursor(0);
                if (c == 0) {
                    return true;
                }
            }
            this.showStatus("");
            xatshowApplet = this;
        }
        xatshowApplet.ba.setCursor(0);
        return true;
    }
    
    private void a() {
        final int c = a.c;
        final String parameter = this.getParameter(d("K\u001d<#bZ\r"));
        Label_0048: {
            xatshowApplet xatshowApplet = null;
            Label_0044: {
                Label_0043: {
                    if (c == 0) {
                        if (parameter != null) {
                            xatshowApplet = this;
                            if (c != 0) {
                                break Label_0044;
                            }
                            if (this.K == 3) {
                                break Label_0043;
                            }
                        }
                        this.y = 0;
                    }
                    if (c == 0) {
                        break Label_0048;
                    }
                }
                xatshowApplet = this;
            }
            xatshowApplet.y = 1;
        }
        xatshowApplet xatshowApplet2 = this;
        final String d = d("M\u0001?)");
        Label_0085: {
            if (c == 0) {
                if (this.getParameter(d) == null) {
                    this.z = 3000;
                    if (c == 0) {
                        break Label_0085;
                    }
                }
                xatshowApplet2 = this;
            }
            xatshowApplet2.z = Integer.parseInt(d);
        }
        xatshowApplet xatshowApplet3 = this;
        final String d2 = d("M\u001a3\"\u007fP\u001c;#b");
        Label_0121: {
            if (c == 0) {
                if (this.getParameter(d2) == null) {
                    this.A = 62460;
                    if (c == 0) {
                        break Label_0121;
                    }
                }
                xatshowApplet3 = this;
            }
            xatshowApplet3.A = Integer.parseInt(d2);
        }
        xatshowApplet xatshowApplet4 = this;
        final String d3 = d("M\u001a3\"\u007fP\u001c;#bM\u0001?)");
        Label_0158: {
            if (c == 0) {
                if (this.getParameter(d3) == null) {
                    this.B = 2000;
                    if (c == 0) {
                        break Label_0158;
                    }
                }
                xatshowApplet4 = this;
            }
            xatshowApplet4.B = Integer.parseInt(d3);
        }
        xatshowApplet xatshowApplet5 = this;
        final String d4 = d("Z\u0007>#~");
        Label_0193: {
            if (c == 0) {
                if (this.getParameter(d4) == null) {
                    this.D = 0;
                    if (c == 0) {
                        break Label_0193;
                    }
                }
                xatshowApplet5 = this;
            }
            xatshowApplet5.D = Integer.parseInt(d4);
        }
        String s = this.getParameter(d("V\u001a6)~"));
        xatshowApplet xatshowApplet7 = null;
        Label_0270: {
            Label_0256: {
                if (c == 0) {
                    if (s == null) {
                        this.E = 0;
                        if (c == 0) {
                            break Label_0256;
                        }
                    }
                    this.E = Integer.parseInt(s);
                }
                xatshowApplet xatshowApplet6 = this;
                if (c == 0) {
                    if (this.E >= 0) {
                        xatshowApplet7 = this;
                        if (c != 0) {
                            break Label_0270;
                        }
                        if (this.E <= 2) {
                            break Label_0256;
                        }
                    }
                    xatshowApplet6 = this;
                }
                xatshowApplet6.E = 0;
            }
            this.C = this.getParameter(d("U\u0001<'"));
            xatshowApplet7 = this;
        }
        xatshowApplet7.L = 0;
        while (this.getParameter("i" + this.L) != null) {
            ++this.L;
            if (this.L >= 4096) {
                break;
            }
        }
        this.F = new String[this.L];
        this.G = new int[this.L];
        this.H = new int[this.L];
        this.I = new int[this.L];
        int l = 0;
        while (true) {
        Label_0614:
            while (true) {
                Label_0606: {
                    if (c == 0) {
                        break Label_0606;
                    }
                    this.F[l] = this.getParameter("i" + l);
                    final String s2 = this.F[l];
                    Label_0491: {
                        xatshowApplet xatshowApplet8 = null;
                        Label_0475: {
                            if (c == 0) {
                                if (s2 == null) {
                                    this.L = l;
                                    if (c == 0) {
                                        break Label_0614;
                                    }
                                }
                                xatshowApplet8 = this;
                                if (c != 0) {
                                    break Label_0475;
                                }
                                final String parameter2;
                                s = (parameter2 = this.getParameter("f" + l));
                            }
                            if (s2 == null) {
                                this.G[(l + 1) % this.L] = this.z;
                                if (c == 0) {
                                    break Label_0491;
                                }
                            }
                            xatshowApplet8 = this;
                        }
                        xatshowApplet8.G[(l + 1) % this.L] = Integer.parseInt(s);
                    }
                    xatshowApplet xatshowApplet9 = this;
                    Label_0547: {
                        if (c == 0) {
                            s = this.getParameter("t" + l);
                            if (s == null) {
                                this.H[l] = this.A;
                                if (c == 0) {
                                    break Label_0547;
                                }
                            }
                            xatshowApplet9 = this;
                        }
                        xatshowApplet9.H[l] = Integer.parseInt(s);
                    }
                    xatshowApplet xatshowApplet10 = this;
                    Label_0603: {
                        if (c == 0) {
                            s = this.getParameter("d" + l);
                            if (s == null) {
                                this.I[l] = this.B;
                                if (c == 0) {
                                    break Label_0603;
                                }
                            }
                            xatshowApplet10 = this;
                        }
                        xatshowApplet10.I[l] = Integer.parseInt(s);
                    }
                    ++l;
                }
                if (l < this.L) {
                    continue;
                }
                break;
            }
            if (c == 0) {
                return;
            }
            continue;
        }
    }
    
    private Image a(final String s) {
        final int c = a.c;
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Image image = null;
        Label_0236: {
            try {
                final Class bb = xatshowApplet.bb;
                if (c == 0) {
                    if (bb == null) {
                        xatshowApplet.bb = c(d("A\t&?dV\u001f\u0013<|U\r&"));
                    }
                    else {
                        final Class bb2 = xatshowApplet.bb;
                    }
                }
                final InputStream resourceAsStream = bb.getResourceAsStream(s);
                if (c == 0 && resourceAsStream == null) {
                    return this.b(s);
                }
                final int read;
                final ByteArrayOutputStream byteArrayOutputStream2;
                if ((read = resourceAsStream.read()) < 0) {
                    byteArrayOutputStream2 = byteArrayOutputStream;
                    if (c == 0) {
                        final byte[] byteArray = byteArrayOutputStream2.toByteArray();
                        if (byteArray == null) {
                            throw new Exception(d("z\t<\"cMH1>iX\u001c7ln@\u001c7lmK\u001a35"));
                        }
                        image = Toolkit.getDefaultToolkit().createImage(byteArray);
                        if (image == null) {
                            throw new Exception(d("z\t<\"cMH1>iX\u001c7leT\t5)"));
                        }
                        break Label_0236;
                    }
                }
                byteArrayOutputStream2.write(read);
                goto Label_0073;
            }
            catch (Exception ex) {
                final int s2 = this.S;
                Label_0192: {
                    xatshowApplet xatshowApplet = null;
                    Label_0182: {
                        if (c == 0) {
                            if (s2 == 0) {
                                this.S = this.size().width;
                            }
                            xatshowApplet = this;
                            if (c != 0) {
                                break Label_0182;
                            }
                            final int t = this.T;
                        }
                        if (s2 != 0) {
                            break Label_0192;
                        }
                        xatshowApplet = this;
                    }
                    xatshowApplet.T = this.size().height;
                }
                final String message;
                String s3 = message = ex.getMessage();
                if (c == 0) {
                    if (message != null) {
                        return this.a(d("A\t&?dV\u001f\u0013<|U\r&lIK\u001a=>6"), s3, this.S, this.T);
                    }
                    d("z\t<\"cMH6#{W\u0004=-h\u0019\u0001?-k\\");
                }
                s3 = message;
                return this.a(d("A\t&?dV\u001f\u0013<|U\r&lIK\u001a=>6"), s3, this.S, this.T);
            }
            try {
                final MediaTracker mediaTracker = new MediaTracker(this);
                mediaTracker.addImage(image, 0);
                mediaTracker.waitForID(0);
            }
            catch (InterruptedException ex2) {
                final int s4 = this.S;
                Label_0312: {
                    xatshowApplet xatshowApplet2 = null;
                    Label_0302: {
                        if (c == 0) {
                            if (s4 == 0) {
                                this.S = this.size().width;
                            }
                            xatshowApplet2 = this;
                            if (c != 0) {
                                break Label_0302;
                            }
                            final int t2 = this.T;
                        }
                        if (s4 != 0) {
                            break Label_0312;
                        }
                        xatshowApplet2 = this;
                    }
                    xatshowApplet2.T = this.size().height;
                }
                final String message2;
                String s5 = message2 = ex2.getMessage();
                if (c == 0) {
                    if (message2 != null) {
                        return this.a(d("A\t&?dV\u001f\u0013<|U\r&lIK\u001a=>6"), s5, this.S, this.T);
                    }
                    d("p\u00053+i\u0019\u000e3%`\\\fr(yK\u0001<+,]\u0007%\"`V\t6");
                }
                s5 = message2;
                return this.a(d("A\t&?dV\u001f\u0013<|U\r&lIK\u001a=>6"), s5, this.S, this.T);
            }
        }
        final byte[] byteArray2 = byteArrayOutputStream.toByteArray();
        final long n = (byteArray2[10] & 0xFF) * 16777216L + ((byteArray2[7] & 0xFF) + ((byteArray2[8] & 0xFF) << 8) + ((byteArray2[9] & 0xFF) << 16));
        final byte[] array = byteArray2;
        final int n2 = 7;
        final byte[] array2 = byteArray2;
        final int n3 = 8;
        final byte[] array3 = byteArray2;
        final int n4 = 9;
        final byte[] array4 = byteArray2;
        final int n5 = 10;
        final byte b = 32;
        array3[n4] = (array4[n5] = b);
        array[n2] = (array2[n3] = b);
        final Adler32 adler32 = new Adler32();
        adler32.reset();
        adler32.update(byteArray2);
        final long value = adler32.getValue();
        long n8;
        long n7;
        final int n6 = (int)(n7 = (n8 = (this.prepareImage(image, this) ? 1 : 0)));
        if (c == 0) {
            if (n6 == 0) {
                final int s6 = this.S;
                if (c == 0) {
                    if (s6 == 0) {
                        this.S = this.size().width;
                    }
                    final xatshowApplet xatshowApplet3 = this;
                    if (c != 0) {
                        return xatshowApplet3.a(d("A\t&?dV\u001f\u0013<|U\r&lIK\u001a=>6"), d("p\u00053+i\u0019\u000e=>aX\u001cr%\u007f\u0019\u0001<:mU\u00016"), this.S, this.T);
                    }
                    final int t3 = this.T;
                }
                if (s6 == 0) {
                    this.T = this.size().height;
                }
                final xatshowApplet xatshowApplet3 = this;
                return xatshowApplet3.a(d("A\t&?dV\u001f\u0013<|U\r&lIK\u001a=>6"), d("p\u00053+i\u0019\u000e=>aX\u001cr%\u007f\u0019\u0001<:mU\u00016"), this.S, this.T);
            }
            final int n9;
            n7 = (n9 = (int)(n8 = this.S));
        }
        Label_0713: {
            if (c == 0) {
                Label_0690: {
                    if (n6 > 0) {
                        final int n10 = (int)(n7 = (n8 = this.T));
                        if (c != 0) {
                            break Label_0713;
                        }
                        if (n10 > 0) {
                            final int width = image.getWidth(this);
                            final int s7 = this.S;
                            Label_0619: {
                                if (c == 0) {
                                    if (width != s7) {
                                        break Label_0619;
                                    }
                                    final int n11;
                                    n7 = (n11 = (int)(n8 = image.getHeight(this)));
                                    if (c != 0) {
                                        break Label_0713;
                                    }
                                    final int t4 = this.T;
                                }
                                if (width == s7) {
                                    break Label_0690;
                                }
                            }
                            final int s8 = this.S;
                            if (c == 0) {
                                if (s8 == 0) {
                                    this.S = this.size().width;
                                }
                                final xatshowApplet xatshowApplet4 = this;
                                if (c != 0) {
                                    return xatshowApplet4.a(d("A\t&?dV\u001f\u0013<|U\r&lIK\u001a=>6"), d("p\u00053+i\u0019\u00003?,M\u00007l{K\u0007<+,]\u0001?)bJ\u0001=\"\u007f"), this.S, this.T);
                                }
                                final int t5 = this.T;
                            }
                            if (s8 == 0) {
                                this.T = this.size().height;
                            }
                            final xatshowApplet xatshowApplet4 = this;
                            return xatshowApplet4.a(d("A\t&?dV\u001f\u0013<|U\r&lIK\u001a=>6"), d("p\u00053+i\u0019\u00003?,M\u00007l{K\u0007<+,]\u0001?)bJ\u0001=\"\u007f"), this.S, this.T);
                        }
                    }
                }
                this.S = image.getWidth(this);
                this.T = image.getHeight(this);
                n8 = (n7 = lcmp(value, n));
            }
        }
        if (c == 0) {
            if (n7 != 0) {
                this.K = 0;
                final int s9 = this.S;
                if (c == 0) {
                    if (s9 == 0) {
                        this.S = this.size().width;
                    }
                    final xatshowApplet xatshowApplet5 = this;
                    if (c != 0) {
                        return xatshowApplet5.a(d("A\t&?dV\u001f\u0013<|U\r&lIK\u001a=>6"), d("p\u00053+i\u0019\u001f3?,W\u0007&loK\r38i]H05,A\t&?dV\u001f"), this.S, this.T);
                    }
                    final int t6 = this.T;
                }
                if (s9 == 0) {
                    this.T = this.size().height;
                }
                final xatshowApplet xatshowApplet5 = this;
                return xatshowApplet5.a(d("A\t&?dV\u001f\u0013<|U\r&lIK\u001a=>6"), d("p\u00053+i\u0019\u001f3?,W\u0007&loK\r38i]H05,A\t&?dV\u001f"), this.S, this.T);
            }
            n8 = (byteArray2[6] & 0xFF) - 48;
        }
        final long k = n8;
        xatshowApplet xatshowApplet6 = this;
        final int i;
        final int n12;
        Label_0852: {
            Label_0846: {
                if (c == 0) {
                    if (this.K >= 0) {
                        i = this.K;
                        n12 = 3;
                        if (c != 0) {
                            break Label_0852;
                        }
                        if (i <= n12) {
                            break Label_0846;
                        }
                    }
                    xatshowApplet6 = this;
                }
                xatshowApplet6.K = 0;
                if (c == 0) {
                    return image;
                }
            }
            final int j = this.K;
        }
        if (i < n12) {
            this.K = (int)k;
        }
        return image;
    }
    
    private Image b(final String s) {
        final int c = a.c;
        final Image image = this.getImage(this.getDocumentBase(), s);
        try {
            final MediaTracker mediaTracker = new MediaTracker(this);
            mediaTracker.addImage(image, 0);
            mediaTracker.waitForID(0);
        }
        catch (InterruptedException ex) {
            final int s2 = this.S;
            Label_0087: {
                xatshowApplet xatshowApplet = null;
                Label_0077: {
                    if (c == 0) {
                        if (s2 == 0) {
                            this.S = this.size().width;
                        }
                        xatshowApplet = this;
                        if (c != 0) {
                            break Label_0077;
                        }
                        final int t = this.T;
                    }
                    if (s2 != 0) {
                        break Label_0087;
                    }
                    xatshowApplet = this;
                }
                xatshowApplet.T = this.size().height;
            }
            final String message;
            String s3 = message = ex.getMessage();
            if (c == 0) {
                if (message != null) {
                    return this.a(d("A\t&?dV\u001f\u0013<|U\r&lIK\u001a=>6"), s3, this.S, this.T);
                }
                d("p\u00053+i\u0019\u000e3%`\\\fr(yK\u0001<+,]\u0007%\"`V\t6");
            }
            s3 = message;
            return this.a(d("A\t&?dV\u001f\u0013<|U\r&lIK\u001a=>6"), s3, this.S, this.T);
        }
        final int prepareImage = this.prepareImage(image, this) ? 1 : 0;
        xatshowApplet xatshowApplet3 = null;
        final Image image2;
        Label_0354: {
            if (c == 0) {
                if (prepareImage == 0) {
                    final int s4 = this.S;
                    if (c == 0) {
                        if (s4 == 0) {
                            this.S = this.size().width;
                        }
                        final xatshowApplet xatshowApplet2 = this;
                        if (c != 0) {
                            return xatshowApplet2.a(d("A\t&?dV\u001f\u0013<|U\r&lIK\u001a=>6"), d("p\u00053+i\u0019\u0006=8,_\u0007'\"h\u0017"), this.S, this.T);
                        }
                        final int t2 = this.T;
                    }
                    if (s4 == 0) {
                        this.T = this.size().height;
                    }
                    final xatshowApplet xatshowApplet2 = this;
                    return xatshowApplet2.a(d("A\t&?dV\u001f\u0013<|U\r&lIK\u001a=>6"), d("p\u00053+i\u0019\u0006=8,_\u0007'\"h\u0017"), this.S, this.T);
                }
                xatshowApplet3 = this;
                if (c != 0) {
                    break Label_0354;
                }
                final int s5 = this.S;
            }
            Label_0344: {
                if (prepareImage > 0) {
                    xatshowApplet3 = this;
                    if (c != 0) {
                        break Label_0354;
                    }
                    if (this.T > 0) {
                        final int width = image.getWidth(this);
                        final int s6 = this.S;
                        Label_0273: {
                            if (c == 0) {
                                if (width != s6) {
                                    break Label_0273;
                                }
                                image2 = image;
                                if (c != 0) {
                                    return image2;
                                }
                                image2.getHeight(this);
                                final int t3 = this.T;
                            }
                            if (width == s6) {
                                break Label_0344;
                            }
                        }
                        final int s7 = this.S;
                        if (c == 0) {
                            if (s7 == 0) {
                                this.S = this.size().width;
                            }
                            final xatshowApplet xatshowApplet4 = this;
                            if (c != 0) {
                                return xatshowApplet4.a(d("A\t&?dV\u001f\u0013<|U\r&lIK\u001a=>6"), d("p\u00053+i\u0019\u00003?,M\u00007l{K\u0007<+,]\u0001?)bJ\u0001=\"\u007f"), this.S, this.T);
                            }
                            final int t4 = this.T;
                        }
                        if (s7 == 0) {
                            this.T = this.size().height;
                        }
                        final xatshowApplet xatshowApplet4 = this;
                        return xatshowApplet4.a(d("A\t&?dV\u001f\u0013<|U\r&lIK\u001a=>6"), d("p\u00053+i\u0019\u00003?,M\u00007l{K\u0007<+,]\u0001?)bJ\u0001=\"\u007f"), this.S, this.T);
                    }
                }
            }
            this.S = image.getWidth(this);
            xatshowApplet3 = this;
        }
        xatshowApplet3.T = image.getHeight(this);
        return image2;
    }
    
    private Image a(final String s, final String s2, final int n, final int n2) {
        final Image image = this.createImage(n, n2);
        final Graphics graphics = image.getGraphics();
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, n, n2);
        graphics.setFont(new Font(d("z\u0007'>e\\\u001a"), 0, 12));
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        graphics.setColor(Color.white);
        graphics.drawString(s, (n - fontMetrics.stringWidth(s)) / 2, 3 * n2 / 8);
        graphics.drawString(s2, (n - fontMetrics.stringWidth(s2)) / 2, 5 * n2 / 8);
        this.prepareImage(image, this);
        return image;
    }
    
    private int[] a(final Image image, final int n, final int n2) {
        final int[] array = new int[n * n2];
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, n, n2, array, 0, n);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {
            return null;
        }
        return array;
    }
    
    static /* synthetic */ Class c(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        b = new boolean[] { false, false, false, false, true, false, true, false, false, true, false, false, true, true, false, false };
    }
    
    private static String d(final String s) {
        final char[] charArray = s.toCharArray();
        for (int length = charArray.length, i = 0; i < length; ++i) {
            final char[] array = charArray;
            final int n = i;
            final char c = array[n];
            char c2 = '\0';
            switch (i % 5) {
                case 0: {
                    c2 = '9';
                    break;
                }
                case 1: {
                    c2 = 'h';
                    break;
                }
                case 2: {
                    c2 = 'R';
                    break;
                }
                case 3: {
                    c2 = 'L';
                    break;
                }
                default: {
                    c2 = '\f';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
