import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.Toolkit;
import java.awt.Container;
import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.awt.Font;
import java.awt.Graphics;
import java.io.InputStream;
import java.awt.Color;
import java.awt.Event;
import java.awt.Frame;
import java.awt.Rectangle;
import java.applet.Applet;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class ipixa extends Panel implements Runnable
{
    protected static final int b = -1;
    protected static final int c = 0;
    protected static final int d = 1;
    protected static final String e = "Loading iPIX\u2122 Image ";
    protected static final String f = "Failed to load iPIX\u2122 Image.";
    protected static final String g = "Copyright © 1986-2000 iPIX\u2122 - v";
    protected ipixc h;
    protected boolean i;
    protected int j;
    protected String k;
    protected float l;
    protected Dimension m;
    protected Image n;
    protected Applet o;
    protected String p;
    protected int[] q;
    protected int r;
    protected int s;
    protected Image t;
    private int u;
    private int v;
    private int w;
    private static final int x = 12;
    private static final float y = 10000.0f;
    protected ipixb z;
    private Rectangle[] A;
    private Rectangle[] B;
    private int C;
    private int D;
    private Rectangle[] E;
    private Rectangle[] F;
    private Rectangle[] G;
    private float[] H;
    private int[] I;
    private Rectangle[] J;
    private static final boolean K = true;
    private static final boolean L = false;
    private boolean M;
    private int N;
    private int O;
    private int P;
    private int Q;
    private static final int R = 50;
    private static final int S = 500;
    private static final float T = 0.18f;
    private static final float U = 0.001f;
    static final int V = 0;
    static final int W = 1;
    static final int X = 2;
    static final int Y = 3;
    private boolean Z;
    private boolean ba;
    private float bb;
    private float bc;
    private boolean bd;
    private float be;
    int bf;
    protected long bg;
    protected static boolean bh;
    private Rectangle bi;
    protected Rectangle bj;
    protected Rectangle bk;
    private int bl;
    private int bm;
    private Frame bn;
    private int bo;
    private long bp;
    private long bq;
    private int br;
    private int bs;
    private int bt;
    private int bu;
    private int bv;
    private int bw;
    private int bx;
    private int by;
    static boolean a;
    
    private final boolean a() {
        final boolean a = ipixa.a;
        this.u = Math.min((int)Math.ceil(Math.log(this.m.width * this.m.height / 75.0f) / Math.log(2.0) / 2.0), 4);
        this.v = 1 << this.u;
        this.w = this.v << 1;
        final int b = this.b(this.m.width, this.v);
        final int b2 = this.b(this.m.height, this.v);
        this.C = b >> this.u;
        this.D = b2 >> this.u;
        if (this.m.width == 0 || this.m.height == 0) {
            return true;
        }
        this.A = new Rectangle[(this.C + 1) * (this.D + 1)];
        this.B = new Rectangle[this.A.length];
        int n = 0;
        int n2 = 0;
        int y;
        while (true) {
            while (true) {
                if (a) {
                    break Label_0187;
                }
                Label_0262: {
                    break Label_0262;
                    while (true) {
                        int n3 = 0;
                        while (true) {
                            if (y > b) {
                                n3 = n2 + this.v;
                                if (!a) {
                                    break;
                                }
                            }
                            else {
                                this.B[n] = new Rectangle(y, n2, 0, 0);
                                this.A[n] = new Rectangle();
                                final int n4 = y + this.v;
                            }
                            y = n3;
                            ++n;
                        }
                        n2 = n3;
                        break Label_0262;
                        y = 0;
                        if (a) {}
                        continue;
                    }
                }
                if (n2 <= b2) {
                    continue;
                }
                break;
            }
            this.J = new Rectangle[this.C * this.D];
            n = 0;
            n2 = 0;
            y = 0;
            if (a) {
                if (a) {
                    continue;
                }
            }
            break;
        }
        int n5 = 0;
    Label_0417_Outer:
        while (true) {
            if (y >= b2) {
                this.E = new Rectangle[Math.max(this.C, this.D) + 1];
                this.F = new Rectangle[this.E.length];
                this.G = new Rectangle[this.E.length];
                n5 = 0;
                if (!a) {
                    break;
                }
            }
            int x = n5;
            while (true) {
                while (true) {
                    Label_0420: {
                        if (!a) {
                            break Label_0420;
                        }
                        this.J[n2] = new Rectangle();
                        this.J[n2].x = x;
                        this.J[n2].y = y;
                        this.J[n2].width = Math.min(this.v, this.m.width - x);
                        this.J[n2].height = Math.min(this.v, this.m.height - y);
                        x += this.v;
                        ++n2;
                        ++n;
                    }
                    if (x < b) {
                        continue Label_0417_Outer;
                    }
                    break;
                }
                y += this.v;
                if (a) {
                    continue;
                }
                break;
            }
            ++n;
        }
        int n6 = n5;
        while (true) {
            while (true) {
                Label_0547: {
                    if (!a) {
                        break Label_0547;
                    }
                    this.E[n6] = new Rectangle();
                    this.F[n6] = new Rectangle();
                    this.G[n6] = new Rectangle();
                    ++n6;
                }
                if (n6 < this.E.length) {
                    continue;
                }
                break;
            }
            this.H = new float[this.b(Math.max(this.m.width, this.m.height), this.w) + this.w];
            this.I = new int[this.H.length];
            if (!a) {
                return true;
            }
            continue;
        }
    }
    
    public boolean mouseEnter(final Event event, final int x, final int y) {
        this.ba = true;
        this.a(x, y);
        if (this.Z) {
            this.bk.x = x;
            this.bk.y = y;
            this.a(1);
        }
        return true;
    }
    
    final synchronized void a(final float n) {
        this.k = "Loading iPIX\u2122 Image " + (int)(n * 100.0f) + "%";
        this.a(this.getGraphics());
        if (this.j != 1) {
            this.b(this.k);
        }
    }
    
    private void a(final Color foreground, final Color background) {
        this.setForeground(foreground);
        this.setBackground(background);
        this.repaint();
    }
    
    public boolean lostFocus(final Event event, final Object o) {
        if (this.i && (this.bf == 1 || this.bf == 2)) {
            this.a(0);
        }
        return true;
    }
    
    Image a(final byte[] array) {
        ipixc h = this.h;
        if (this.h == null) {
            h = new ipixc(this, this.o.getCodeBase(), null);
        }
        try {
            return h.b(array);
        }
        catch (NoSuchMethodError noSuchMethodError) {
            return h.c(array);
        }
    }
    
    final synchronized void b() {
        if (this.j != 0) {
            this.h();
            return;
        }
        if (this.h.h.height != this.h.h.width << 1 && this.h.h.height != this.h.h.width) {
            return;
        }
        this.n();
        this.f();
        this.j = 1;
        this.b(this.p);
        this.k();
        this.a(this.o.getParameter("Spin"));
        Thread.yield();
    }
    
    private final float c() {
        final long n = System.currentTimeMillis() - this.bg;
        return Math.min((n < 500L) ? 1.0f : (n * n / 250000.0f), 20.0f) * 3.1415927f / 180.0f * 2.0f * Math.min(2.0f, this.bp / 120000.0f);
    }
    
    protected boolean d() {
        final boolean a = ipixa.a;
        final float[] j = this.j();
        Label_0583: {
            switch (this.bf) {
                case 0: {
                    return false;
                }
                case 3: {
                    try {
                        if (this.h.b()) {
                            return false;
                        }
                    }
                    catch (NullPointerException ex) {
                        return false;
                    }
                    final float n = 0.02f * Math.min(1.0f, this.bp / 240000.0f);
                    if (!this.h.f() && !this.h.c() && Math.abs(j[0] + n * this.be) > 1.5707963267948966) {
                        this.be = -this.be;
                    }
                    final float[] array = j;
                    final int n2 = 0;
                    array[n2] += n * this.be;
                    if (this.bd) {
                        final float n3 = (float)Math.log(Math.abs(j[1]) + 1.0f) * n;
                        if (j[1] > 0.0f) {
                            final float[] array2 = j;
                            final int n4 = 1;
                            array2[n4] -= n3;
                            if (!a) {
                                break;
                            }
                        }
                        if (j[1] >= 0.0f) {
                            break;
                        }
                        final float[] array3 = j;
                        final int n5 = 1;
                        array3[n5] += n3;
                        if (!a) {
                            break;
                        }
                    }
                    if (Math.abs(j[1]) > this.bc) {
                        final float[] array4 = j;
                        final int n6 = 1;
                        array4[n6] += this.bb * n;
                        if (!a) {
                            break;
                        }
                    }
                    final double n7 = Math.asin(j[1] / this.bc) + this.bb * n;
                    if (Math.abs(n7) > 1.5707963267948966) {
                        this.bb *= -1.0f;
                    }
                    j[1] = (float)Math.sin(n7) * this.bc;
                    if (a) {
                        break Label_0583;
                    }
                    break;
                }
                case 1: {
                    if (this.bi.inside(this.bk.x, this.bk.y)) {
                        if (this.bj.y <= this.s) {
                            j[2] = (float)Math.exp(Math.log(j[2]) + this.c() / 2.0f);
                            if (!a) {
                                break;
                            }
                        }
                        j[2] = (float)Math.exp(Math.log(j[2]) - this.c() / 2.0f);
                        if (!a) {
                            break;
                        }
                    }
                    final float n8 = this.bj.x - this.r;
                    final float n9 = this.bj.y - this.s;
                    final float n10 = (float)Math.atan2(n9, n8);
                    final float n11 = n8 / this.r;
                    final float n12 = n9 / this.s;
                    float n13 = n11 * Math.abs(n11) * 0.16f;
                    float n14 = n12 * Math.abs(n12) * 0.16f;
                    final float n15 = (float)Math.cos(n10);
                    if (n15 < 0.001f && n15 > -0.001f) {
                        n13 = 0.0f;
                    }
                    final float n16 = (float)Math.sin(n10);
                    if (n16 < 0.001f && n16 > -0.001f) {
                        n14 = 0.0f;
                    }
                    final float n17 = j[2] * Math.max(1.0f, 240000.0f / this.bp);
                    final float[] array5 = j;
                    final int n18 = 0;
                    array5[n18] += n13 / n17;
                    final float[] array6 = j;
                    final int n19 = 1;
                    array6[n19] += -n14 / n17;
                    if (a) {
                        break Label_0583;
                    }
                    break;
                }
                case 2: {
                    Label_0993: {
                        switch (this.bm) {
                            case 52:
                            case 74:
                            case 106:
                            case 1006: {
                                final float[] array7 = j;
                                final int n20 = 0;
                                array7[n20] -= this.c();
                                if (a) {
                                    break Label_0993;
                                }
                                break Label_0583;
                            }
                            case 56:
                            case 77:
                            case 109:
                            case 1004: {
                                final float[] array8 = j;
                                final int n21 = 1;
                                array8[n21] += this.c();
                                if (a) {
                                    break Label_0993;
                                }
                                break Label_0583;
                            }
                            case 54:
                            case 75:
                            case 107:
                            case 1007: {
                                final float[] array9 = j;
                                final int n22 = 0;
                                array9[n22] += this.c();
                                if (a) {
                                    break Label_0993;
                                }
                                break Label_0583;
                            }
                            case 50:
                            case 73:
                            case 105:
                            case 1005: {
                                final float[] array10 = j;
                                final int n23 = 1;
                                array10[n23] -= this.c();
                                if (a) {
                                    break Label_0993;
                                }
                                break Label_0583;
                            }
                            case 55: {
                                final float[] array11 = j;
                                final int n24 = 0;
                                array11[n24] -= this.c();
                                final float[] array12 = j;
                                final int n25 = 1;
                                array12[n25] += this.c();
                                if (a) {
                                    break Label_0993;
                                }
                                break Label_0583;
                            }
                            case 49: {
                                final float[] array13 = j;
                                final int n26 = 0;
                                array13[n26] -= this.c();
                                final float[] array14 = j;
                                final int n27 = 1;
                                array14[n27] -= this.c();
                                if (a) {
                                    break Label_0993;
                                }
                                break Label_0583;
                            }
                            case 57: {
                                final float[] array15 = j;
                                final int n28 = 0;
                                array15[n28] += this.c();
                                final float[] array16 = j;
                                final int n29 = 1;
                                array16[n29] += this.c();
                                if (a) {
                                    break Label_0993;
                                }
                                break Label_0583;
                            }
                            case 51: {
                                final float[] array17 = j;
                                final int n30 = 0;
                                array17[n30] += this.c();
                                final float[] array18 = j;
                                final int n31 = 1;
                                array18[n31] -= this.c();
                                if (a) {
                                    break Label_0993;
                                }
                                break Label_0583;
                            }
                            case 43:
                            case 88:
                            case 120: {
                                j[2] = (float)Math.exp(Math.log(j[2]) + this.c() / 2.0);
                                if (a) {
                                    break Label_0993;
                                }
                                break Label_0583;
                            }
                            case 45:
                            case 90:
                            case 122: {
                                j[2] = (float)Math.exp(Math.log(j[2]) - this.c() / 2.0);
                                if (a) {
                                    break;
                                }
                                break Label_0583;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        this.a(j);
        return true;
    }
    
    protected final void a(final Graphics graphics) {
        final boolean a = ipixa.a;
        if (this.j == 1) {
            return;
        }
        final String s = (this.j == 0) ? this.k : "Failed to load iPIX\u2122 Image.";
        graphics.setFont(new Font("Arial", 2, 10));
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        final int stringWidth = fontMetrics.stringWidth((this.j == 0) ? "Loading iPIX\u2122 Image " : s);
        Label_0227: {
            if (this.t == null) {
                graphics.clearRect(0, this.m.height / 2 + fontMetrics.getDescent(), this.m.width, fontMetrics.getHeight());
                if (!a) {
                    break Label_0227;
                }
            }
            if (System.getProperty("java.vendor").startsWith("Netscape") && System.getProperty("java.version").compareTo("1.1") < 0) {
                graphics.drawImage(this.t, 0, 0, null);
                if (!a) {
                    break Label_0227;
                }
            }
            final Graphics create = graphics.create();
            create.clipRect((this.m.width - stringWidth) / 2 + stringWidth, this.m.height / 2 + fontMetrics.getDescent(), fontMetrics.stringWidth("100%"), fontMetrics.getHeight());
            create.drawImage(this.t, 0, 0, null);
        }
        graphics.setColor(Color.black);
        graphics.drawString(s, (this.m.width - stringWidth) / 2, this.m.height / 2 + fontMetrics.getHeight());
    }
    
    private final void e() {
        int n = 0;
        while (true) {
            Label_0028: {
                if (!ipixa.a) {
                    break Label_0028;
                }
                this.a(this.B[n], this.A[n]);
                ++n;
            }
            if (n >= this.A.length) {
                return;
            }
            continue;
        }
    }
    
    private final boolean f() {
        final int n = 4096;
        this.P = n;
        this.Q = n;
        final int n2 = this.h.h.width - 1 << 12;
        this.N = n2;
        this.O = n2;
        this.z.a(this.h.l);
        this.z.b(this.h.d());
        this.h.g[0] = -16777216;
        return true;
    }
    
    private final void a(final Rectangle rectangle, final Rectangle rectangle2, final Rectangle rectangle3, final Rectangle rectangle4, final int n, final int n2) {
        this.br = rectangle.x;
        this.bs = rectangle.y;
        this.bt = rectangle2.x - rectangle.x >> n;
        this.bu = rectangle2.y - rectangle.y >> n;
        this.bv = rectangle3.x - rectangle.x >> n2;
        this.bw = rectangle3.y - rectangle.y >> n2;
        this.bx = rectangle4.x - rectangle3.x - rectangle2.x + rectangle.x >> n + n2;
        this.by = rectangle4.y - rectangle3.y - rectangle2.y + rectangle.y >> n + n2;
    }
    
    protected static final float a(final float n, final float n2, final float n3) {
        return Math.min(Math.max(n, n2), n3);
    }
    
    protected static final int a(final int n, final int n2, final int n3) {
        return Math.min(Math.max(n, n2), n3);
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    final synchronized void g() {
        this.i = false;
        this.h = null;
        this.t = null;
        this.q = null;
        this.j = -1;
        this.getGraphics().clearRect(0, 0, this.m.width, this.m.height);
        this.repaint();
    }
    
    protected void h() {
        if (this.j != 1) {
            return;
        }
        this.e();
        this.l();
        this.i();
        this.o();
        this.t.flush();
        this.prepareImage(this.t, this);
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if (image == this.t && (n == 128 || n == 32)) {
            this.getGraphics().drawImage(image, 0, 0, null);
        }
        return true;
    }
    
    private final void a(final String s) {
        final boolean a = ipixa.a;
        Label_0049: {
            if (s != null) {
                if (s.equalsIgnoreCase("on")) {
                    this.a(3);
                    if (!a) {
                        break Label_0049;
                    }
                }
                if (s.equalsIgnoreCase("flat")) {
                    this.bd = true;
                    this.a(3);
                }
            }
        }
        this.bi.x = (int)(this.r * 0.82f);
        this.bi.y = (int)(this.s * 0.82f);
        this.bi.width = (int)(this.m.width * 0.18f);
        this.bi.height = (int)(this.m.height * 0.18f);
        final float[] j = this.j();
        this.bc = 1.5707964f - (float)Math.atan2(this.s, j[2] * this.z.e);
        Label_0210: {
            if (j[1] > this.bc) {
                this.bb = -Math.abs(this.bb);
                if (!a) {
                    break Label_0210;
                }
            }
            if (j[1] < -this.bc) {
                this.bb = Math.abs(this.bb);
            }
        }
        Container container = this.getParent();
        while (true) {
        Label_0255:
            while (true) {
                Label_0250: {
                    if (!a) {
                        break Label_0250;
                    }
                    final Container o = container;
                    if (o instanceof Frame) {
                        this.bn = (Frame)container;
                        if (!a) {
                            break Label_0255;
                        }
                    }
                    container = container.getParent();
                }
                if (container != null) {
                    continue;
                }
                break;
            }
            Container o;
            final Applet applet = (Applet)(o = this.o);
            if (!a) {
                final String parameter = applet.getParameter("AutoNav");
                this.Z = (parameter != null && parameter.equalsIgnoreCase("on"));
                if (this.Z && this.ba) {
                    this.a(1);
                }
                this.i = true;
                this.a(this.bj.x, this.bj.y);
                return;
            }
            continue;
        }
    }
    
    private final float a(final Rectangle rectangle, final Rectangle rectangle2) {
        final float[] array = { rectangle.x - this.r, rectangle.y - this.s };
        final float[] array2 = new float[2];
        final float b = this.z.b(array, array2);
        this.h.a(array2, false);
        rectangle2.x = (int)(array2[0] * this.P);
        rectangle2.y = (int)(array2[1] * this.Q);
        return b;
    }
    
    private final void b(String string) {
        final int index = string.indexOf(8482);
        if (index != -1) {
            string = string.substring(0, index) + "(TM)" + string.substring(index + 1);
        }
        this.o.showStatus(string);
    }
    
    public final void layout() {
        this.reshape(0, 0, this.m.width, this.m.height);
    }
    
    public final void move(final int n, final int n2) {
    }
    
    public final boolean keyDown(final Event event, final int n) {
        if (this.i) {
            this.bl = n;
            if (this.bf == 1 || this.bf == 2) {
                return true;
            }
            if (this.bl == 32) {
                this.k();
                return true;
            }
            this.bm = n;
            this.a(2);
        }
        return true;
    }
    
    final void a(final int bf) {
        this.bf = bf;
        this.bg = System.currentTimeMillis();
    }
    
    final synchronized void a(final ipixc h) {
        this.i = false;
        if (this.h != null) {
            this.h.a();
        }
        (this.h = h).e();
        this.j = 0;
        if (this.t == null) {
            this.getGraphics().clearRect(0, 0, this.m.width, this.m.height);
        }
        this.repaint();
    }
    
    private final void i() {
        final boolean a = ipixa.a;
        if (!this.M) {
            final int b = this.b(this.m.height, this.v);
            int n = 0;
            while (true) {
                Label_0163: {
                    if (!a) {
                        break Label_0163;
                    }
                    if (Math.abs(this.I[n]) != 10000.0f) {
                        this.a(new Rectangle(this.I[n], n, 0, 0), this.E[n >> this.u]);
                        this.a(new Rectangle(this.I[n] - this.w, n, 0, 0), this.F[n >> this.u]);
                        this.a(new Rectangle(this.I[n] + this.w, n, 0, 0), this.G[n >> this.u]);
                    }
                    n += this.v;
                }
                if (n > b) {
                    return;
                }
                continue;
            }
        }
        else {
            final int b2 = this.b(this.m.width, this.v);
            int n2 = 0;
            while (true) {
                Label_0321: {
                    if (!a) {
                        break Label_0321;
                    }
                    if (Math.abs(this.I[n2]) != 10000.0f) {
                        this.a(new Rectangle(n2, this.I[n2], 0, 0), this.E[n2 >> this.u]);
                        this.a(new Rectangle(n2, this.I[n2] - this.w, 0, 0), this.F[n2 >> this.u]);
                        this.a(new Rectangle(n2, this.I[n2] + this.w, 0, 0), this.G[n2 >> this.u]);
                    }
                    n2 += this.v;
                }
                if (n2 > b2) {
                    return;
                }
                continue;
            }
        }
    }
    
    protected static final float b(final float n, final float n2, final float n3) {
        return (n - n2) % (n3 - n2) + n2;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.ba = true;
        this.a(n, n2);
        if (this.Z && this.bf == 1) {
            this.a(0);
        }
        return true;
    }
    
    ipixa(final Applet o, final String s) {
        final boolean a = ipixa.a;
        this.h = null;
        this.i = false;
        this.j = 0;
        this.k = "Loading iPIX\u2122 Image ";
        this.l = 0.0f;
        this.m = new Dimension();
        this.q = null;
        this.t = null;
        this.z = new ipixb();
        this.H = null;
        this.I = null;
        this.M = true;
        this.N = 0;
        this.O = 0;
        this.P = 0;
        this.Q = 0;
        this.Z = false;
        this.ba = false;
        this.bb = 1.1f;
        this.bd = false;
        this.be = 1.0f;
        this.bf = 0;
        this.bi = new Rectangle();
        this.bj = new Rectangle();
        this.bk = new Rectangle();
        this.bl = 0;
        this.bm = 0;
        this.bn = null;
        this.bo = 0;
        this.bp = 33000L;
        this.bq = 0L;
        this.o = o;
        this.p = "Copyright © 1986-2000 iPIX\u2122 - v" + s;
        this.a(Color.black, Color.white);
        this.m.width = Integer.parseInt(this.o.getParameter("Width"));
        this.m.height = Integer.parseInt(this.o.getParameter("Height"));
        this.l = (float)Math.sqrt(this.m.width * this.m.width + this.m.height * this.m.height);
        if (a) {
            ipixc.a = !ipixc.a;
        }
    }
    
    public void paint(final Graphics graphics) {
        final boolean a = ipixa.a;
        try {
            Label_0256: {
                if (this.t != null) {
                    graphics.drawImage(this.t, 0, 0, null);
                    if (!a) {
                        break Label_0256;
                    }
                }
                String s = this.p;
                graphics.setFont(new Font("Arial", 2, 10));
                final FontMetrics fontMetrics = graphics.getFontMetrics();
                int n = graphics.getFontMetrics().stringWidth(s);
                if (n > this.m.width) {
                    s = s.substring(10);
                    n = graphics.getFontMetrics().stringWidth(s);
                }
                graphics.drawString(s, (this.m.width - n) / 2, this.m.height - fontMetrics.getDescent() - fontMetrics.getLeading());
                final int height = this.n.getHeight(null);
                final int width = this.n.getWidth(null);
                final float min = Math.min(Math.min(this.m.width / width, this.m.height / 2 / height), 1.0f);
                final int n2 = (int)(width * min);
                final int n3 = (int)(height * min);
                while (true) {
                    Label_0213: {
                        if (!a) {
                            break Label_0213;
                        }
                        Thread.sleep(10L);
                    }
                    if (!graphics.drawImage(this.n, (this.m.width - n2) / 2, this.m.height / 2 - n3, n2, n3, Color.black, this)) {
                        continue;
                    }
                    break;
                }
            }
            this.a(graphics);
        }
        catch (InterruptedException ex) {}
    }
    
    protected final float[] j() {
        final float[] array = new float[3];
        System.arraycopy(this.z.l, 0, array, 0, array.length);
        final float[] array2 = array;
        final int n = 2;
        array2[n] /= 0.64f * this.l / this.z.e;
        return array;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        this.a(n, n2);
        if (this.i && this.bf == 1) {
            this.a(0);
        }
        return true;
    }
    
    protected final void k() {
        try {
            this.a(this.h.g());
        }
        catch (NullPointerException ex) {}
    }
    
    final void a(final float[] array) {
        final boolean a = ipixa.a;
        try {
            if (array[0] > 10.0f || array[0] < -10.0f) {
                array[0] = 0.0f;
            }
            if (array[1] > 5.0f || array[1] < -5.0f) {
                array[1] = 0.0f;
            }
            Label_0110: {
                if (this.h.c() || this.h.f()) {
                    array[0] = b(array[0], -3.1415927f, 3.1415927f);
                    if (!a) {
                        break Label_0110;
                    }
                }
                array[0] = a(array[0], -1.5707964f, 1.5707964f);
            }
            if (this.h.n != 0.0f) {
                array[2] = Math.max(array[2], this.h.n);
            }
            if (this.h.o != 0.0f) {
                array[2] = Math.min(array[2], this.h.o);
            }
            final float n = 0.64f * this.l / this.z.e;
            final float n2 = 2.8f;
            final float n3 = this.l / (3.115f * this.z.e);
            Label_0245: {
                if (n3 < n2) {
                    array[2] = a(array[2] * n, n3, n2);
                    if (!a) {
                        break Label_0245;
                    }
                }
                array[2] = this.h.g()[2] * n;
            }
            Label_0410: {
                if (this.h.m > 0.0f && this.h.m < 180.0f) {
                    final float n4 = this.h.m * 0.017453292f / 2.0f;
                    final float n5 = this.m.height / 2.0f;
                    array[2] = Math.max(array[2], n5 / (this.z.e * (float)Math.tan(n4)));
                    final float n6 = (float)Math.atan2(n5, array[2] * this.z.e);
                    if (Math.abs(array[1]) + n6 <= n4) {
                        break Label_0410;
                    }
                    array[1] = ((array[1] < 0.0f) ? (-(n4 - n6)) : (n4 - n6));
                    if (!a) {
                        break Label_0410;
                    }
                }
                array[1] = a(array[1], -1.5707964f, 1.5707964f);
            }
            synchronized (this) {
                this.z.a(array);
                this.h();
            }
        }
        catch (NullPointerException ex) {}
    }
    
    private final void l() {
        final boolean a = ipixa.a;
        this.b(this.m.width, this.w);
        this.b(this.m.height, this.w);
        final float n = this.z.g[2];
        final float n2 = this.z.h[2];
        final float n3 = this.z.f[2];
        final float n4 = this.z.l[2] * this.z.e;
        float n5 = -n / n2;
        Label_0129: {
            if (n == 0.0f) {
                n5 = 0.0f;
                if (!a) {
                    break Label_0129;
                }
            }
            if (n2 == 0.0f) {
                n5 = ((n > 0.0f) ? -10000.0f : 10000.0f);
            }
        }
        this.M = (Math.abs(n5) <= 1.0f);
        while (true) {
            Label_0450: {
                while (true) {
                    int n7 = 0;
                    Label_0440: {
                        float n6;
                        if (Math.abs(n5) > 1000.0f) {
                            n6 = n4 * -n3 / n + this.r;
                            if (n == 0.0) {
                                n6 = ((n3 > 0.0) ? -10000.0f : 10000.0f);
                            }
                            n7 = 0;
                            while (true) {
                                Label_0220: {
                                    if (!a) {
                                        break Label_0220;
                                    }
                                    this.H[n7] = n6;
                                    ++n7;
                                }
                                if (n7 >= this.H.length) {
                                    break Label_0450;
                                }
                                continue;
                            }
                        }
                        else if (Math.abs(n5) < 0.001f) {
                            n6 = n4 * -n3 / n2 + this.s;
                            if (n2 == 0.0) {
                                n6 = ((n3 > 0.0) ? -10000.0f : 10000.0f);
                            }
                            n7 = 0;
                            while (true) {
                                Label_0305: {
                                    if (!a) {
                                        break Label_0305;
                                    }
                                    this.H[n7] = n6;
                                    ++n7;
                                }
                                if (n7 >= this.H.length) {
                                    break Label_0450;
                                }
                                continue;
                            }
                        }
                        else if (!this.M) {
                            n6 = n4 * -n3 / n - this.s / n5 + this.r;
                            n7 = 0;
                            while (true) {
                                Label_0376: {
                                    if (!a) {
                                        break Label_0376;
                                    }
                                    this.H[n7] = n7 / n5 + n6;
                                    ++n7;
                                }
                                if (n7 >= this.H.length) {
                                    break Label_0450;
                                }
                                continue;
                            }
                        }
                        else {
                            n6 = n4 * -n3 / n2 - n5 * this.r + this.s;
                            n7 = 0;
                            if (!a) {
                                break Label_0440;
                            }
                        }
                        this.H[n7] = n5 * n7 + n6;
                        ++n7;
                    }
                    if (n7 < this.H.length) {
                        continue;
                    }
                    break;
                }
            }
            if (a) {
                continue;
            }
            break;
        }
        int n8 = 0;
        while (true) {
            Label_0483: {
                if (!a) {
                    break Label_0483;
                }
                this.I[n8] = Math.round(this.H[n8]);
                ++n8;
            }
            if (n8 >= this.I.length) {
                return;
            }
            continue;
        }
    }
    
    private final void m() throws InterruptedException {
        final long currentTimeMillis = System.currentTimeMillis();
        final boolean d = this.d();
        final long currentTimeMillis2 = System.currentTimeMillis();
        if (d) {
            final long bq = Math.max(currentTimeMillis2 - currentTimeMillis, 10L) * 1000L;
            if (bq < this.bp * 2L || bq < 2L * this.bq) {
                this.bp = (bq + 49L * this.bp) / 50L;
            }
            this.bq = bq;
        }
        if (ipixa.bh || this.bf == 0) {
            Thread.sleep(Math.max(50L - (currentTimeMillis2 - currentTimeMillis), 1L));
        }
        Thread.yield();
    }
    
    private final boolean n() {
        try {
            this.q = new int[this.m.width * this.m.height];
            this.r = this.m.width / 2;
            this.s = this.m.height / 2;
            this.t = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(this.m.width, this.m.height, this.q, 0, this.m.width));
            return this.a();
        }
        catch (OutOfMemoryError outOfMemoryError) {
            return false;
        }
    }
    
    public final boolean keyUp(final Event event, final int bl) {
        if (this.i) {
            this.bl = bl;
            if (this.bf != 2) {
                return true;
            }
            if (this.bm == bl) {
                this.a(0);
            }
        }
        return true;
    }
    
    private final void a(final int n, final int n2) {
        final Rectangle bounds = this.bounds();
        this.bj.x = a(n, bounds.x, bounds.x + bounds.width);
        this.bj.y = a(n2, bounds.y, bounds.y + bounds.height);
        if (!this.i) {
            return;
        }
        final Rectangle rectangle = (this.bf == 1) ? this.bk : this.bj;
        int bo = 13;
        if (this.bi.inside(rectangle.x, rectangle.y)) {
            bo = ((n2 <= this.s) ? 7 : 5);
        }
        if (this.bo != bo) {
            this.bn.setCursor(this.bo = bo);
        }
    }
    
    static {
        final String property = System.getProperty("os.name");
        ipixa.bh = (property.equalsIgnoreCase("Mac OS") || property.equalsIgnoreCase("Windows 3.1"));
    }
    
    private final void o() {
        final boolean a = ipixa.a;
        final int n = this.M ? this.m.width : this.m.height;
        final int n2 = (this.M ? this.m.height : this.m.width) + this.v - 1 & ~(this.v - 1);
        final boolean b = (this.M ? this.z.h[2] : this.z.g[2]) >= 0.0f;
        int n3 = 0;
        int n4;
        int n5;
        while (true) {
            while (true) {
                Label_0199: {
                    if (!a) {
                        break Label_0199;
                    }
                    Label_0190: {
                        if (this.I[n3] > n2 && this.I[n3 + this.v] > n2) {
                            break Label_0190;
                        }
                        if (this.I[n3] >= 0 || this.I[n3 + this.v] >= 0) {
                            this.a(n3, Math.min(n3 + this.v, n), b);
                        }
                    }
                    n3 += this.v;
                }
                if (n3 < n) {
                    continue;
                }
                break;
            }
            n3 = 0;
            n4 = 0;
            n5 = 0;
            if (a) {
                continue;
            }
            break;
        }
    Label_0244_Outer:
        while (true) {
            Label_0490: {
                if (!a) {
                    break Label_0490;
                }
                int n6 = 0;
                while (true) {
                    while (true) {
                        Label_0461: {
                            if (!a) {
                                break Label_0461;
                            }
                            int n7 = 0;
                            Label_0246: {
                                if (!this.M) {
                                    n7 = n6;
                                    break Label_0246;
                                }
                                n7 = n5;
                            }
                            final int n8 = n7;
                            final int n9 = this.M ? n6 : n5;
                            if ((this.H[n9] < n8 || this.H[n9] > n8 + this.v) && (this.H[n9 + this.v] < n8 || this.H[n9 + this.v] > n8 + this.v)) {
                                this.a(this.A[n4], this.A[n4 + 1], this.A[n4 + this.C + 1], this.A[n4 + this.C + 2], this.u, this.u);
                                this.a(this.J[n3], this.H[n9 + this.v / 2] < n8 + this.v / 2 ^ b);
                            }
                            n6 += this.v;
                            ++n4;
                            ++n3;
                        }
                        if (n6 < this.m.width) {
                            continue Label_0244_Outer;
                        }
                        break;
                    }
                    n5 += this.v;
                    if (a) {
                        continue;
                    }
                    break;
                }
                ++n4;
            }
            if (n5 >= this.m.height) {
                return;
            }
            continue;
        }
    }
    
    private final void a(final Rectangle rectangle, final boolean b) {
        final boolean a = ipixa.a;
        int n = rectangle.y * this.m.width + rectangle.x;
        if (!this.h.c() && !this.h.f() && b) {
            int n2 = 0;
        Label_0086_Outer:
            while (true) {
                Label_0116: {
                    if (!a) {
                        break Label_0116;
                    }
                    int n3 = n;
                    int n4 = 0;
                    while (true) {
                        while (true) {
                            Label_0089: {
                                if (!a) {
                                    break Label_0089;
                                }
                                this.q[n3++] = this.h.g[0];
                                ++n4;
                            }
                            if (n4 < rectangle.width) {
                                continue Label_0086_Outer;
                            }
                            break;
                        }
                        n += this.m.width;
                        if (a) {
                            continue;
                        }
                        break;
                    }
                    ++n2;
                }
                if (n2 >= rectangle.height) {
                    return;
                }
                continue;
            }
        }
        else {
            int n5 = 0;
            if (b && this.h.c()) {
                n5 += this.h.h.width * this.h.h.height / 2;
            }
            int n6 = 0;
        Label_0230_Outer:
            while (true) {
                Label_0385: {
                    if (!a) {
                        break Label_0385;
                    }
                    int br = this.br;
                    int bs = this.bs;
                    int n7 = n;
                    int n8 = 0;
                    while (true) {
                        while (true) {
                            Label_0306: {
                                if (!a) {
                                    break Label_0306;
                                }
                                int n9 = 0;
                                Label_0265: {
                                    Label_0238: {
                                        if ((bs & Integer.MAX_VALUE) <= this.O && (br & Integer.MAX_VALUE) <= this.N) {
                                            break Label_0238;
                                        }
                                        n9 = 0;
                                        if (!a) {
                                            break Label_0265;
                                        }
                                    }
                                    n9 = n5 + (bs >>> 12) * this.h.h.width + (br >>> 12);
                                }
                                this.q[n7] = this.h.g[n9];
                                ++n7;
                                br += this.bt;
                                bs += this.bu;
                                ++n8;
                            }
                            if (n8 < rectangle.width) {
                                continue Label_0230_Outer;
                            }
                            break;
                        }
                        this.br += this.bv;
                        this.bs += this.bw;
                        this.bt += this.bx;
                        this.bw += this.by;
                        n += this.m.width;
                        if (a) {
                            continue;
                        }
                        break;
                    }
                    ++n6;
                }
                if (n6 >= rectangle.height) {
                    return;
                }
                continue;
            }
        }
    }
    
    protected final void p() {
        final boolean a = ipixa.a;
        try {
            final int[] array = { 1195984440, 962683904, 1677758720, 0, -3473408, -872415233, -13893632, 25600, 1677721602, -6516823, -882048349, 1890900619, -1445159705, 260459079, -1768847194, 587838702, 196584778, 1734184167, 1868035852, -1492448767, 513765171, 1276000070, 357697813, 849059687, -199584688, 823307351, -1428342086, -541970900, 1318112620, 473578840, -1209552841, 1431228363, 1323010302, 1606903780, 123254791, 822632888, 2027439048, 1751652338, 135866680, -1453848199, 155363689, 964766192, -1446417951, 1236961368, 899308218, 2055334410, 446380539, 1524333147, 1773839035, -887408026, 196872635, 542862393, -1404249573, 1205628156, 321760634, 2085645645, -1415135907, -2012348933, 2100165680, 1316937133, 332455582, -831087380, 1310654547, -1885339874, -1543512385, 2146410251, 2045247740, -1276648736, -1048511910, 744790127, 120352010, 321524840, -1314831988, -15129850, -900288350, -1014293429, 1927063633, -499551209, 999486584, -765179243, -495941564, -116234561, 386889658, 153480038, -863688847, -431744925, -1791515659, 2029679834, -1339501674, 961424541, -1972050868, -1391352546, -845540143, 1437007006, -1167316459, -1411796973, -972150454, 1949161371, 1438979596, -487707587, -1474636886, 1829414445, 1269464927, -838509418, 1851638070, -2055289481, 2020565954, -1952324240, -63410332, -854583885, -1254295191, 1724481299, 457073655, -222051669, 922997995, 2040585616, -1422686330, -868800534, -1783153258, -1909279569, -599933875, -1477129266, -1394035693, 1774974125, -465998121, 1619632107, 1025358186, -514106533, 131979703, 1995209149, 395716620, 359065673, 78036345, -908695306, 1869452263, -1932710468, 922351087, -1023425975, 855634279, 239600386, -762609657, 2056364159, 187044238, -2129718696, 537004622, 140609003, 1611161732, 372951073, 1293605909, 577379066, 1377965086, 570630790, -816802974, 1621921816, -477445168, 417499959, -956786424, 389982435, 690857491, -1534052338, 1482960425, 650757520, -902796925, -1816444871, -477982172, 585444150, 988996397, 1413140901, 861918848, -946576526, 421935207, 911304240, 1809735941, 592691233, 1251377597, 1239894637, 1987634434, 100765927, -1921963471, -1022951156, 6856717, -1732237303, 706521832, -2004010470, 1772470004, -1452801792, 59 };
            final byte[] array2 = new byte[array.length * 4];
            int n = 0;
            while (true) {
                while (true) {
                    Label_1401: {
                        if (!a) {
                            break Label_1401;
                        }
                        array2[n] = (byte)(array[n / 4] >>> 8 * (3 - n % 4));
                        ++n;
                    }
                    if (n < array2.length) {
                        continue;
                    }
                    break;
                }
                this.n = this.a(array2);
                if (!a) {
                    final MediaTracker mediaTracker = new MediaTracker(this);
                    mediaTracker.addImage(this.n, 1);
                    mediaTracker.waitForAll();
                    return;
                }
                continue;
            }
        }
        catch (InterruptedException ex) {}
    }
    
    public boolean mouseDown(final Event event, final int x, final int y) {
        this.a(x, y);
        if (this.i) {
            if (this.Z) {
                this.Z = false;
            }
            if (this.bf == 1 || this.bf == 2) {
                return true;
            }
            if ((event.modifiers & 0x4) == 0x4) {
                this.a(0);
                this.k();
                if (!ipixa.a) {
                    return true;
                }
            }
            this.bk.x = x;
            this.bk.y = y;
            this.a(1);
        }
        return true;
    }
    
    private final int b(final int n, final int n2) {
        return n + n2 - 1 & ~(n2 - 1);
    }
    
    public final void run() {
        final boolean a = ipixa.a;
        try {
            while (true) {
                Label_0054: {
                    switch (this.j) {
                        case 1: {
                            this.m();
                            if (a) {
                                break Label_0054;
                            }
                            continue;
                        }
                        case 0: {
                            Thread.sleep(1000L);
                            if (a) {
                                break Label_0054;
                            }
                            continue;
                        }
                        case -1: {
                            Thread.sleep(200L);
                            continue;
                        }
                    }
                }
            }
        }
        catch (InterruptedException ex) {}
    }
    
    private final void a(final int n, final int n2, final boolean b) {
        final boolean a = ipixa.a;
        int n3 = 0;
        final boolean b2 = false;
        if (this.h.c()) {
            n3 = this.h.h.width * this.h.h.height / 2;
        }
        int n4 = 0;
        int n5 = 0;
        if (!this.h.c() && !this.h.f()) {
            n4 = (b ? 1 : 0);
            n5 = (b ? 0 : 1);
        }
        final int n6 = b ? n3 : b2;
        final int n7 = b ? b2 : n3;
        final int n8 = this.M ? 1 : this.m.width;
        final int n9 = this.M ? this.m.width : 1;
        final int n10 = this.M ? this.m.height : this.m.width;
        final int n11 = n >> this.u;
        this.a(this.F[n11], this.E[n11], this.F[n11 + 1], this.E[n11 + 1], this.u + 1, this.u);
        int n12 = n;
        while (true) {
            while (true) {
                Label_0574: {
                    if (!a) {
                        break Label_0574;
                    }
                    final int max = Math.max(this.I[n12] - this.v - 1 & ~(this.v - 1), 0);
                    final int n13 = max;
                    final int min = Math.min(this.I[n12], n10 - 1);
                    int n14 = n12 * n8 + n13 * n9;
                    if (n4 == 1) {
                        int n15 = n13;
                        while (true) {
                            Label_0342: {
                                if (!a) {
                                    break Label_0342;
                                }
                                this.q[n14] = this.h.g[0];
                                n14 += n9;
                                ++n15;
                            }
                            if (n15 <= min) {
                                continue;
                            }
                            break;
                        }
                    }
                    else {
                        final int n16 = this.w - (this.I[n12] - n13);
                        int n17 = this.br + n16 * this.bt;
                        int n18 = this.bs + n16 * this.bu;
                        int n19 = n13;
                        while (true) {
                            Label_0512: {
                                if (!a) {
                                    break Label_0512;
                                }
                                int n20 = 0;
                                Label_0467: {
                                    if ((n18 & Integer.MAX_VALUE) > this.O || (n17 & Integer.MAX_VALUE) > this.N) {
                                        n20 = 0;
                                        if (!a) {
                                            break Label_0467;
                                        }
                                    }
                                    n20 = n6 + (n18 >>> 12) * this.h.h.width + (n17 >>> 12);
                                }
                                this.q[n14] = this.h.g[n20];
                                n14 += n9;
                                n17 += this.bt;
                                n18 += this.bu;
                                ++n19;
                            }
                            if (n19 <= min) {
                                continue;
                            }
                            break;
                        }
                    }
                    this.br += this.bv;
                    this.bs += this.bw;
                    this.bt += this.bx;
                    this.bu += this.by;
                    ++n12;
                }
                if (n12 < n2) {
                    continue;
                }
                break;
            }
            this.a(this.G[n11], this.E[n11], this.G[n11 + 1], this.E[n11 + 1], this.u + 1, this.u);
            final int max = n;
            if (a) {
                continue;
            }
            break;
        }
        int n21 = n;
        while (true) {
            Label_0970: {
                if (!a) {
                    break Label_0970;
                }
                final int n22 = Math.min(this.I[n21] + this.w & ~(this.v - 1), n10) - 1;
                final int max2 = Math.max(this.I[n21] + 1, 0);
                int n23 = n21 * n8 + n22 * n9;
                if (n5 == 1) {
                    int n24 = n22;
                    while (true) {
                        Label_0738: {
                            if (!a) {
                                break Label_0738;
                            }
                            this.q[n23] = this.h.g[0];
                            n23 -= n9;
                            --n24;
                        }
                        if (n24 >= max2) {
                            continue;
                        }
                        break;
                    }
                }
                else {
                    final int n25 = this.w + (this.I[n21] - n22);
                    int n26 = this.br + n25 * this.bt;
                    int n27 = this.bs + n25 * this.bu;
                    int n28 = n22;
                    while (true) {
                        Label_0908: {
                            if (!a) {
                                break Label_0908;
                            }
                            int n29 = 0;
                            Label_0863: {
                                if ((n27 & Integer.MAX_VALUE) > this.O || (n26 & Integer.MAX_VALUE) > this.N) {
                                    n29 = 0;
                                    if (!a) {
                                        break Label_0863;
                                    }
                                }
                                n29 = n7 + (n27 >>> 12) * this.h.h.width + (n26 >>> 12);
                            }
                            this.q[n23] = this.h.g[n29];
                            n23 -= n9;
                            n26 += this.bt;
                            n27 += this.bu;
                            --n28;
                        }
                        if (n28 >= max2) {
                            continue;
                        }
                        break;
                    }
                }
                this.br += this.bv;
                this.bs += this.bw;
                this.bt += this.bx;
                this.bu += this.by;
                ++n21;
            }
            if (n21 >= n2) {
                return;
            }
            continue;
        }
    }
    
    public boolean mouseDrag(final Event event, final int n, final int n2) {
        this.a(n, n2);
        return true;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        this.a(n, n2);
        return true;
    }
}
