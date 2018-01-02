import java.awt.Toolkit;
import java.awt.Container;
import java.awt.image.ImageProducer;
import java.awt.image.ColorModel;
import java.awt.image.DirectColorModel;
import java.awt.LayoutManager;
import java.net.MalformedURLException;
import java.awt.Component;
import java.awt.image.PixelGrabber;
import java.awt.Event;
import java.awt.Frame;
import java.awt.MediaTracker;
import java.util.Vector;
import java.net.URL;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class snowFX extends Applet implements Runnable, ImageObserver
{
    private Thread b;
    private boolean c;
    private boolean d;
    private Image e;
    private Graphics f;
    private Producer g;
    private int h;
    private int i;
    private int[] j;
    private int[] k;
    private int[] l;
    private int[] m;
    private String n;
    private Image o;
    private long p;
    private final long q = 16L;
    private final long r = 1500L;
    private URL s;
    private String[] t;
    private String[] u;
    private String[] v;
    private int[] w;
    private int[] x;
    private int y;
    private Vector z;
    private MediaTracker A;
    private int B;
    private int C;
    private Frame D;
    private int[] E;
    private boolean F;
    private boolean G;
    private int H;
    private String I;
    final String J = ",\n]#.\u0019ZO6k$\u0018B!k9\u0015A 8\f\u0014Loc\u001a\rZa\"\u0017\u001b_a%\b\u000e\u00021\"\u000f\u0015Ca#\u0019\u0017Af";
    Image[] K;
    int L;
    int M;
    int[] N;
    int[] O;
    int[] P;
    int[] Q;
    final int R = 2048;
    final int S = 2047;
    int T;
    private final int[] U;
    static int a;
    
    public final boolean mouseMove(final Event event, final int n, final int n2) {
        if (n > this.h - 18 && n2 > this.i - 10) {
            this.D.setCursor(12);
        }
        else if (!this.t[this.B - 1].equalsIgnoreCase(b("#5"))) {
            this.D.setCursor(12);
        }
        else {
            this.D.setCursor(0);
        }
        this.d = true;
        if (this.C != this.B) {
            this.showStatus(this.u[this.B - 1]);
        }
        this.C = this.B;
        return true;
    }
    
    public void stop() {
        if (this.b != null) {
            this.b.stop();
            this.b = null;
        }
    }
    
    public final boolean mouseEnter(final Event event, final int n, final int n2) {
        if (!this.t[this.B - 1].equalsIgnoreCase(b("#5"))) {
            this.D.setCursor(12);
        }
        else {
            this.D.setCursor(0);
        }
        this.d = true;
        this.showStatus(this.u[this.B - 1]);
        return true;
    }
    
    void a() {
        for (int i = 0; i < this.L; ++i) {
            switch (this.O[i]) {
                case 0: {
                    this.a(i);
                    break;
                }
                case 1: {
                    this.a(i, -1);
                    break;
                }
                case 2: {
                    this.a(i, 1);
                    break;
                }
            }
        }
    }
    
    private boolean a(final Image image, final int[] array, final int n, final int n2) {
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, n, n2, array, 0, n);
        try {
            if (!pixelGrabber.grabPixels()) {
                this.showStatus(b("$\u0017Jo.\u001f\bB="));
                return false;
            }
        }
        catch (InterruptedException ex) {
            this.showStatus(b("$\u0017Jo.\u001f\bB="));
            return false;
        }
        if ((pixelGrabber.status() & 0x80) != 0x0) {
            this.showStatus(b("$\u0017Jo.\u001f\bB="));
            return false;
        }
        return true;
    }
    
    void a(final int n) {
        final int n2 = this.N[2 * n];
        final int n3 = this.N[2 * n + 1];
        final int n4 = this.Q[this.T++];
        this.T &= 0x7FF;
        if (n3 >= 0 && n2 >= 0 && n3 < this.i - 1 && n2 < this.h && 16777214 != this.j[n2 + this.E[n3]]) {
            this.j[n2 + this.E[n3]] = 0;
        }
        int n5 = n2 + n4;
        if (n5 < 0 || n5 >= this.h) {
            this.d(n);
            return;
        }
        if (n3 + this.P[n] >= this.i) {
            this.j[n5 + this.E[this.i - 1]] = 16777214;
            this.d(n);
            return;
        }
        int n6 = n3 + this.P[n];
        if (n6 >= 0) {
            if (this.j[n5 + this.E[n6]] == 16777214 || (this.j[n5 + this.E[n6]] != 0 && this.j[n5 + this.E[n6]] != 16777215)) {
                for (int n7 = 0; n7 < this.P[n] && this.j[n5 + this.E[n6]] != 0; ++n7) {
                    if (--n6 < 0) {
                        this.d(n);
                        return;
                    }
                }
                if (this.j[n5 + this.E[n6]] == 16777214 || this.j[n5 + this.E[n6]] != 0) {
                    n5 -= n4;
                }
                if (n5 <= 0 || n5 >= this.h - 1) {
                    this.d(n);
                    this.j[n5 + this.E[n6]] = 16777214;
                    return;
                }
                if (this.j[n5 + this.E[n6 + 1]] != 0 && this.j[n5 + 1 + this.E[n6 + 1]] == 0 && this.j[n5 - 1 + this.E[n6 + 1]] == 0) {
                    if (this.Q[this.T] == 1) {
                        this.O[n] = 1;
                    }
                    else {
                        this.O[n] = 2;
                    }
                    this.T = (this.T + 1 & 0x7FF);
                }
                else if (this.j[n5 + 1 + this.E[n6 + 1]] == 0) {
                    this.O[n] = 2;
                }
                else {
                    if (this.j[n5 - 1 + this.E[n6 + 1]] != 0) {
                        this.j[n5 + this.E[n6]] = 16777214;
                        this.d(n);
                        return;
                    }
                    this.O[n] = 1;
                }
            }
            if (n6 >= 0 && n5 >= 0 && n6 < this.i && n5 < this.h) {
                this.j[n5 + this.E[n6]] = 16777215;
            }
        }
        this.N[2 * n] = n5;
        this.N[2 * n + 1] = n6;
    }
    
    private final void b() {
        this.y = 1;
        while (this.getParameter(b("\u0004\u0017L(.") + String.valueOf(this.y)) != null) {
            ++this.y;
        }
        --this.y;
        this.v = new String[this.y];
        this.t = new String[this.y];
        this.u = new String[this.y];
        for (int i = 0; i < this.y; ++i) {
            this.v[i] = this.getParameter(b("\u0004\u0017L(.") + String.valueOf(i + 1));
            if (this.v[i] == null) {
                while (true) {
                    this.showStatus(b("8\u0014I*-\u0004\u0014H+k\u001d\u001b_.&\b\u000eH=qM\u0013@.,\b") + i);
                    try {
                        Thread.sleep(100L);
                    }
                    catch (InterruptedException ex) {}
                }
            }
            else {
                this.t[i] = this.getParameter(b("\u0018\bA") + String.valueOf(i + 1));
                if (this.t[i] == null) {
                    this.t[i] = new String(b("\u0003\u0015"));
                }
                this.u[i] = this.getParameter(b("\u0000\tJ") + String.valueOf(i + 1));
                if (this.u[i] == null) {
                    this.u[i] = new String();
                }
            }
        }
    }
    
    public snowFX() {
        this.n = "";
        this.p = 8L;
        this.B = 1;
        this.U = new int[] { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 0, 0, -1, 0, 0, 0, -1, -1, -1, 0, -1, -1, -1, 0, -1, -1, -1, 0, 0, 0, -1, -1, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        return true;
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void start() {
        if (this.b == null) {
            (this.b = new Thread(this)).start();
        }
    }
    
    void c() {
        System.arraycopy(this.k, 0, this.j, 0, this.h * this.i);
        final int[] array = this.z.elementAt(0);
        for (int i = 0; i < this.x[0]; ++i) {
            System.arraycopy(array, i * this.w[0], this.j, (this.h - this.w[0]) / 2 + this.E[(this.i - this.x[0]) / 2 + i], this.w[0]);
        }
        for (int j = 0; j < this.L; ++j) {
            this.d(j);
        }
    }
    
    public String getAppletInfo() {
        return b("#\u001b@*qM\tC <+\" E") + b(",\u000fY'$\u001f@\r&)\u0002\u0014");
    }
    
    private final boolean d() {
        this.z = new Vector();
        this.A = new MediaTracker(this);
        (this.K = new Image[this.y])[0] = null;
        this.w = new int[this.y];
        this.x = new int[this.y];
        for (int i = 0; i < this.y; ++i) {
            this.showStatus(b("$\u0017L(.M") + String.valueOf(i + 1));
            this.K[i] = this.a(this.v[i], i);
            if (this.K[i] == null) {
                this.showStatus(b("(\b_ 9M\u0016B./\u0004\u0014Jo\"\u0000\u001bJ*k") + String.valueOf(i + 1));
                return false;
            }
            this.w[i] = this.K[i].getWidth(this);
            this.x[i] = this.K[i].getHeight(this);
            final int[] array = new int[this.w[i] * this.x[i]];
            if (!this.a(this.K[i], array, this.w[i], this.x[i])) {
                return false;
            }
            this.z.addElement(array);
            System.gc();
            if (this.F) {
                this.b(i);
            }
        }
        for (int j = 0; j < this.y; ++j) {
            this.K[j].flush();
            this.K[j] = null;
        }
        this.K = null;
        return true;
    }
    
    public final synchronized void b(final int n) {
        final Graphics graphics = this.getGraphics();
        if (this.K[n] != null) {
            try {
                this.c(n);
                graphics.drawImage(this.K[n], 0, 0, this.h, this.i, this);
            }
            catch (Exception ex) {
                System.out.println(String.valueOf(ex));
            }
        }
    }
    
    private final void e() {
        while (true) {
            this.showStatus(b(")\u0015Ch?M\bH\"$\u001b\u001f\rm)\u0014Zd-$\u0003Zy '\u0002\tL!*OZN=.\t\u0013Y<k\u0001\u0013C*k\u0004\u0014\r\u0007\u001f 6\u0003"));
        }
    }
    
    public synchronized void c(final int n) {
        this.a(this.K[n]);
    }
    
    public synchronized void a(final Image image) {
        int checkImage = 0;
        this.prepareImage(image, this.h, this.i, this);
        this.notifyAll();
        while ((checkImage & 0xF0) == 0x0) {
            Thread.yield();
            checkImage = this.checkImage(image, this.h, this.i, this);
        }
    }
    
    public final boolean mouseExit(final Event event, final int n, final int n2) {
        this.d = false;
        this.showStatus("");
        return true;
    }
    
    public final void paint(final Graphics graphics) {
        if (this.o != null) {
            this.f.drawImage(this.o, 0, 0, this);
            graphics.drawImage(this.e, 0, 0, this);
        }
    }
    
    public void destroy() {
        if (this.e != null) {
            this.e.flush();
        }
        this.e = null;
        if (this.f != null) {
            this.f.dispose();
        }
        this.f = null;
        System.gc();
    }
    
    void a(final int n, final int n2) {
        final int n3 = this.N[2 * n];
        int n4 = this.N[2 * n + 1];
        if (n4 >= 0 && n3 >= 0 && n4 < this.i - 1 && n3 < this.h && 16777214 != this.j[n3 + this.E[n4]]) {
            this.j[n3 + this.E[n4]] = 0;
        }
        final int n5 = n3 + n2;
        if (n5 < 0 || n5 >= this.h) {
            this.d(n);
            final int n6 = n5 - n2;
            if (n4 >= 0) {
                this.j[n6 + this.E[n4]] = 16777214;
            }
            return;
        }
        if (++n4 >= this.i) {
            this.d(n);
            this.j[n5 + this.E[this.i - 1]] = 16777214;
            return;
        }
        if (n4 >= 0) {
            if (this.j[n5 + this.E[n4]] == 16777214 || (this.j[n5 + this.E[n4]] != 0 && this.j[n5 + this.E[n4]] != 16777215)) {
                final int n7 = n5 - n2;
                --n4;
                this.d(n);
                this.j[n7 + this.E[n4]] = 16777214;
                return;
            }
            if (n4 < this.i - 1 && (this.j[n5 + this.E[n4 + 1]] == 0 || this.j[n5 + this.E[n4 + 1]] == 16777215)) {
                this.O[n] = 0;
            }
            this.j[n5 + this.E[n4]] = 16777215;
        }
        this.N[2 * n] = n5;
        this.N[2 * n + 1] = n4;
    }
    
    void f() {
        this.Q = new int[2048];
        int n = 0;
        do {
            this.Q[n] = ((Math.random() < 0.5) ? 1 : -1);
        } while (++n < 2048);
        this.M = this.i;
        this.N = new int[this.L * 2];
        this.O = new int[this.L];
        this.P = new int[this.L];
        for (int i = 0; i < this.h; ++i) {
            this.j[i + this.E[this.i - 1]] = 16777214;
        }
        for (int j = 0; j < this.L; ++j) {
            this.O[j] = 0;
            this.N[j * 2] = (int)(Math.random() * (this.h - 20) + 10.0);
            this.N[j * 2 + 1] = (int)(-this.M * Math.random());
            this.P[j] = (int)(Math.random() * 3.0) + 1;
        }
    }
    
    void g() {
        this.a();
        if (this.G) {
            System.arraycopy(this.m, 0, this.l, 0, this.h * this.i);
            for (int i = 0; i < this.h * this.i; ++i) {
                if (this.j[i] != this.H) {
                    this.l[i] = this.j[i];
                }
            }
            this.h();
            return;
        }
        System.arraycopy(this.j, 0, this.l, 0, this.h * this.i);
        this.h();
    }
    
    void h() {
        int n = this.E[this.i - 10] + this.h - 18;
        int n2 = 0;
        do {
            int n3 = 0;
            do {
                if (this.U[n3 + n2 * 9] == 0) {
                    final int[] l = this.l;
                    final int n4 = n;
                    l[n4] ^= -1;
                    final int[] i = this.l;
                    final int n5 = n + 1;
                    i[n5] ^= -1;
                    final int[] j = this.l;
                    final int n6 = n + this.h;
                    j[n6] ^= -1;
                    final int[] k = this.l;
                    final int n7 = n + this.h + 1;
                    k[n7] ^= -1;
                }
                else {
                    this.l[n] = -1;
                    this.l[n + 1] = -1;
                    this.l[n + this.h] = -1;
                    this.l[n + this.h + 1] = -1;
                }
                n += 2;
            } while (++n3 < 9);
            n += (this.h << 1) - 18;
        } while (++n2 < 5);
    }
    
    public boolean keyUp(final Event event, final int n) {
        this.c();
        return true;
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        if (n > this.h - 18 && n2 > this.i - 10) {
            try {
                this.s = new URL(b("\u0005\u000eY?qBUZ8<C\u0013W.9C\u0014H;d\u0013\u0013O %"));
            }
            catch (MalformedURLException ex) {
                this.showStatus(b("(\b_ 9M\u0016D! \u0004\u0014J"));
                return true;
            }
            if (this.s != null) {
                this.getAppletContext().showDocument(this.s, b("2\u0018A.%\u0006"));
            }
            return true;
        }
        if (this.t[this.B - 1] == null) {
            return true;
        }
        this.s = null;
        if (!this.t[this.B - 1].equalsIgnoreCase(b("#5"))) {
            this.showStatus(b(".\u0015C!.\u000e\u000eD!,M\u000eBuk") + this.t[this.B - 1]);
            try {
                if (this.t[this.B - 1].startsWith(b("\u0005\u000eY?q"))) {
                    this.s = new URL(this.t[this.B - 1]);
                }
                else {
                    this.s = new URL(this.getDocumentBase(), this.t[this.B - 1]);
                }
            }
            catch (MalformedURLException ex2) {
                this.showStatus(b("(\b_ 9M\u0016D! \u0004\u0014J"));
                return true;
            }
            if (this.s != null) {
                this.getAppletContext().showDocument(this.s, this.I);
            }
        }
        return true;
    }
    
    public synchronized void i() {
        Thread.yield();
        this.getToolkit().sync();
        final long n = 10L - (System.currentTimeMillis() - this.p);
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
        this.p = System.currentTimeMillis();
        try {
            Thread.sleep(16L);
        }
        catch (InterruptedException ex3) {}
    }
    
    public void run() {
        this.d();
        final int[] array = this.z.elementAt(0);
        for (int i = 0; i < this.w[0] * this.x[0]; ++i) {
            final int[] array2 = array;
            final int n = i;
            array2[n] &= 0xFFFFFF;
        }
        this.f();
        this.c();
        this.p = System.currentTimeMillis();
        final long n2 = 1500L - (System.currentTimeMillis() - this.p);
        if (n2 > 0L) {
            try {
                Thread.sleep(n2);
            }
            catch (InterruptedException ex) {}
        }
        this.showStatus(b(",\n]#.\u0019Z_:%\u0003\u0013C(eCT"));
        System.gc();
        final Graphics graphics = this.getGraphics();
        if (!this.t[this.B - 1].equalsIgnoreCase(b("#5"))) {
            this.D.setCursor(12);
        }
        else {
            this.D.setCursor(0);
        }
        int n3 = 0;
        while (true) {
            this.g();
            if (n3++ > 1000) {
                n3 = 0;
                System.gc();
            }
            this.g.startProduction(this.g.a());
            this.f.drawImage(this.o, 0, 0, this);
            graphics.drawImage(this.e, 0, 0, this);
            this.i();
        }
    }
    
    public void init() {
        final int a = snowFX.a;
        this.setLayout(null);
        this.addNotify();
        final String parameter;
        String s = parameter = this.getParameter(b("\u000e\bH+\"\u0019\t"));
        String parameter2 = null;
        String i = null;
        Label_0096: {
            Label_0078: {
                Label_0074: {
                    if (a == 0) {
                        if (parameter == null) {
                            break Label_0074;
                        }
                        final String s2;
                        i = (s2 = (parameter2 = s));
                        if (a != 0) {
                            break Label_0096;
                        }
                    }
                    if (parameter.equals(b(",\n]#.\u0019ZO6k$\u0018B!k9\u0015A 8\f\u0014Loc\u001a\rZa\"\u0017\u001b_a%\b\u000e\u00021\"\u000f\u0015Ca#\u0019\u0017Af"))) {
                        break Label_0078;
                    }
                    this.e();
                    if (a == 0) {
                        break Label_0078;
                    }
                    Producer.a = !Producer.a;
                }
                this.e();
            }
            this.I = this.getParameter(b("\u000b\bL\"."));
            parameter2 = (i = this.I);
        }
        Label_0179: {
            snowFX snowFX = null;
            Label_0175: {
                if (a == 0) {
                    if (i == null) {
                        this.I = new String(b("2\u0018A.%\u0006"));
                    }
                    snowFX = this;
                    if (a != 0) {
                        break Label_0175;
                    }
                    s = (parameter2 = this.getParameter(b("\u001e\u0012B8\"\u0000\u001bJ*8")));
                }
                if (parameter2 != null) {
                    if (s.equalsIgnoreCase(b("\u0014\u001f^"))) {
                        this.F = true;
                        if (a == 0) {
                            break Label_0179;
                        }
                    }
                    this.F = false;
                    if (a == 0) {
                        break Label_0179;
                    }
                }
                snowFX = this;
            }
            snowFX.F = false;
        }
        final String parameter4;
        String parameter3 = parameter4 = this.getParameter(b("\u001a\u0013I;#"));
        Label_0245: {
            if (a == 0) {
                if (parameter4 != null) {
                    this.h = Integer.parseInt(parameter3);
                }
                parameter3 = this.getParameter(b("\u0005\u001fD(#\u0019"));
                if (a != 0) {
                    break Label_0245;
                }
            }
            if (parameter4 != null) {
                this.i = Integer.parseInt(parameter3);
            }
            this.E = new int[this.i];
        }
        int n = 0;
        while (true) {
            while (true) {
                Label_0267: {
                    if (a == 0) {
                        break Label_0267;
                    }
                    this.E[n] = n * this.h;
                    ++n;
                }
                if (n < this.i) {
                    continue;
                }
                break;
            }
            this.j = new int[this.h * this.i];
            this.k = new int[this.h * this.i];
            this.l = new int[this.h * this.i];
            this.b();
            this.g = new Producer(this.h, this.i, new DirectColorModel(32, 16711680, 65280, 255), this.l, 0, this.h);
            this.o = this.createImage(this.g);
            try {
                this.e = this.createImage(this.h, this.i);
                this.f = this.e.getGraphics();
                if (a != 0) {
                    continue;
                }
            }
            catch (Exception ex) {
                this.e = null;
                this.showStatus(b("(\b_ 9M\u0019_**\u0019\u0013C(k\u0002\u001cK&&\f\u001dHn"));
            }
            break;
        }
        this.resize(this.h, this.i);
        final String parameter5;
        String s3 = parameter5 = this.getParameter(b("\u000f\u001bN$\"\u0000\u001bJ*"));
        Label_0610: {
            if (a == 0) {
                if (parameter5 != null) {
                    boolean b2;
                    final boolean b = b2 = s3.equalsIgnoreCase(b("\u0003\u0015"));
                    if (a != 0) {
                        break Label_0610;
                    }
                    if (!b) {
                        final Image a2 = this.a(s3);
                        if (a != 0) {
                            break Label_0610;
                        }
                        if (a2 != null) {
                            final int width = a2.getWidth(this);
                            if (a != 0) {
                                break Label_0610;
                            }
                            if (width == this.h) {
                                final int height = a2.getHeight(this);
                                if (a != 0) {
                                    break Label_0610;
                                }
                                if (height == this.i) {
                                    this.m = new int[this.h * this.i];
                                    final boolean b3 = b2 = this.a(a2, this.l, this.h, this.i);
                                    if (a != 0) {
                                        break Label_0610;
                                    }
                                    if (b3) {
                                        System.arraycopy(this.l, 0, this.m, 0, this.h * this.i);
                                        this.G = true;
                                    }
                                }
                            }
                        }
                    }
                }
                this.getParameter(b("\u000f\u001bN$9\b\u001e"));
            }
            s3 = parameter5;
        }
        final String s4 = s3;
        int int1;
        if (a == 0 && s4 == null) {
            int1 = 255;
            if (a != 0) {
                goto Label_0630;
            }
        }
        else {
            int1 = Integer.parseInt(s4);
        }
        final String parameter6 = this.getParameter(b("\u000f\u001bN$,\u001f\u001fH!"));
        int int2;
        if (a == 0 && parameter6 == null) {
            int2 = 255;
            if (a != 0) {
                goto Label_0668;
            }
        }
        else {
            int2 = Integer.parseInt(parameter6);
        }
        final String parameter7 = this.getParameter(b("\u000f\u001bN$)\u0001\u000fH"));
        int int3;
        if (a == 0 && parameter7 == null) {
            int3 = 255;
            if (a != 0) {
                goto Label_0707;
            }
        }
        else {
            int3 = Integer.parseInt(parameter7);
        }
        this.H = (int1 << 16 | int2 << 8 | int3);
        int n2 = 0;
        while (true) {
            while (true) {
                Label_0752: {
                    if (a == 0) {
                        break Label_0752;
                    }
                    final snowFX snowFX2 = this;
                    snowFX2.k[n2] = this.H;
                    ++n2;
                }
                if (n2 < this.h * this.i) {
                    continue;
                }
                break;
            }
            final snowFX snowFX2 = this;
            if (a != 0) {
                continue;
            }
            break;
        }
        Container parent = this.getParent();
        while (true) {
            while (true) {
                Label_0789: {
                    if (a == 0) {
                        break Label_0789;
                    }
                    final Container parent2 = ((Frame)parent).getParent();
                    parent = parent2;
                }
                if (!(parent instanceof Frame)) {
                    continue;
                }
                break;
            }
            snowFX snowFX3 = this;
            final Container parent2 = this;
            if (a == 0) {
                Label_0841: {
                    if (a == 0) {
                        final String parameter8 = this.getParameter(b("\u001e\u0014B8-\f\u0019Y 9"));
                        if (parameter8 != null) {
                            this.L = Integer.parseInt(parameter8);
                            if (a == 0) {
                                break Label_0841;
                            }
                        }
                        snowFX3 = this;
                    }
                    snowFX3.L = 500;
                }
                snowFX snowFX4 = this;
                if (a == 0) {
                    if (this.L < 100) {
                        this.L = 200;
                    }
                    this.D = (Frame)parent;
                    snowFX4 = this;
                }
                snowFX4.D.setCursor(3);
                return;
            }
            continue;
        }
    }
    
    synchronized Image a(final String s, final int n) {
        URL url = null;
        Image image = null;
        try {
            url = new URL(this.getDocumentBase(), s);
        }
        catch (MalformedURLException ex) {}
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
                this.notifyAll();
                Thread.currentThread();
                Thread.yield();
                try {
                    image = this.getImage(url);
                    this.A.addImage(image, n);
                    this.A.waitForID(n);
                }
                catch (InterruptedException ex2) {
                    image = null;
                }
                if (this.A.isErrorID(n)) {
                    image = null;
                }
                else {
                    i = 6;
                }
            }
            catch (NullPointerException ex3) {
                System.gc();
            }
        }
        while (image == null) {
            this.showStatus(b("$\u0017L(.M") + s + b("M\u0014B;k\u000b\u0015X!/L"));
            try {
                Thread.sleep(500L);
            }
            catch (InterruptedException ex4) {}
        }
        while (image.getWidth(this) < 0) {
            this.notifyAll();
            Thread.currentThread();
            Thread.yield();
            try {
                Thread.currentThread();
                Thread.sleep(100L);
            }
            catch (InterruptedException ex5) {}
        }
        return image;
    }
    
    synchronized Image a(final String s) {
        URL url = null;
        Image image = null;
        try {
            url = new URL(this.getDocumentBase(), s);
        }
        catch (MalformedURLException ex) {}
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
                catch (InterruptedException ex2) {
                    image = null;
                }
                if (mediaTracker.isErrorID(0)) {
                    image = null;
                }
                else {
                    i = 6;
                }
            }
            catch (NullPointerException ex3) {
                System.gc();
            }
        }
        while (image == null) {
            this.showStatus(b("$\u0017L(.M") + s + b("M\u0014B;k\u000b\u0015X!/L"));
            try {
                Thread.sleep(500L);
            }
            catch (InterruptedException ex4) {}
        }
        while (image.getWidth(this) < 0) {
            this.notifyAll();
            Thread.currentThread();
            Thread.yield();
            try {
                Thread.currentThread();
                Thread.sleep(100L);
            }
            catch (InterruptedException ex5) {}
        }
        return image;
    }
    
    void d(final int n) {
        this.N[n * 2] = (int)(Math.random() * (this.h - 20) + 10.0);
        this.N[n * 2 + 1] = (int)(-this.M * Math.random());
        this.O[n] = 0;
        this.P[n] = (int)(Math.random() * 3.0) + 1;
    }
    
    private static String b(final String s) {
        final char[] charArray = s.toCharArray();
        final int length = charArray.length;
        int n = 0;
    Label_0010:
        while (true) {
            while (true) {
                int n2 = 0;
                char c = 'm';
                char[] array2;
                char[] array = array2 = charArray;
                int n4;
                int n3 = n4 = n;
                while (true) {
                    array[n3] = (char)(c ^ array2[n4]);
                    Label_0045: {
                        Label_0037: {
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
                                            break Label_0037;
                                        }
                                        case 4: {
                                            break Label_0045;
                                        }
                                        default: {
                                            continue;
                                        }
                                    }
                                }
                                c = 'z';
                                array = (array2 = charArray);
                                n3 = (n4 = n);
                                continue;
                            }
                            c = '-';
                            array = (array2 = charArray);
                            n3 = (n4 = n);
                            continue;
                        }
                        c = 'O';
                        array = (array2 = charArray);
                        n3 = (n4 = n);
                        continue;
                    }
                    c = 'K';
                    array = (array2 = charArray);
                    n3 = (n4 = n);
                }
            }
            break;
        }
        return new String(charArray);
    }
}
