import java.util.StringTokenizer;
import java.awt.Event;
import java.security.AccessControlException;
import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.MalformedURLException;
import java.awt.image.ImageObserver;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Container;
import java.applet.AudioClip;
import java.net.URL;
import java.awt.Dimension;
import java.util.Vector;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class IpixViewer extends Applet implements Runnable, e
{
    private static final String a = "IPIX Java Viewer 5.0.1.2";
    private static final String b = "(C) 1998 IPIX Corporation.";
    private static final String c = "All Rights Reserved.";
    private static final String d = "Loading Image...";
    public String e;
    private Thread f;
    protected final Object g;
    protected final Object h;
    protected final Object i;
    protected Object j;
    protected m k;
    protected bl l;
    protected bk m;
    protected bg n;
    protected Image o;
    Color p;
    protected Image q;
    protected static final int[] r;
    protected String s;
    protected r t;
    protected Image u;
    protected Graphics v;
    protected Frame w;
    protected Vector x;
    protected Graphics y;
    protected float z;
    protected boolean A;
    protected boolean B;
    protected boolean C;
    public boolean D;
    protected boolean E;
    public boolean F;
    public boolean G;
    public boolean H;
    public boolean I;
    public boolean J;
    Color K;
    s[] L;
    private int M;
    private int N;
    boolean O;
    private int P;
    private Dimension Q;
    private URL R;
    private URL S;
    private URL T;
    final float U = 0.017453292f;
    final float V = 57.295776f;
    protected boolean W;
    protected boolean X;
    protected boolean Y;
    public static final boolean Z = true;
    public static final boolean ba = false;
    public static final boolean bb = false;
    public static final boolean bc = true;
    public static final boolean bd = false;
    public static final int be = 0;
    public static final int bf = 1;
    public static final int bg = 2;
    protected int bh;
    protected Image bi;
    protected bp bj;
    AudioClip bk;
    public int bl;
    public static int bm;
    public static int bn;
    public static int bo;
    public static int bp;
    public static int bq;
    protected static float br;
    protected static final int bs = 1;
    protected static final int bt = 2;
    protected static int bu;
    protected static float bv;
    private boolean bw;
    private float bx;
    private float by;
    private float bz;
    private float bA;
    private float bB;
    private float bC;
    private float bD;
    private int bE;
    
    public IpixViewer() {
        this.e = "init";
        this.f = null;
        this.g = new Object();
        this.h = new Object();
        this.i = new Object();
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = null;
        this.p = Color.white;
        this.q = null;
        this.s = null;
        this.t = null;
        this.u = null;
        this.v = null;
        this.w = null;
        this.x = new Vector();
        this.y = null;
        this.z = 0.0f;
        this.A = true;
        this.B = false;
        this.C = false;
        this.D = true;
        this.E = false;
        this.F = true;
        this.G = true;
        this.H = false;
        this.I = true;
        this.J = true;
        this.K = Color.green;
        this.L = null;
        this.M = 0;
        this.N = 0;
        this.O = false;
        this.P = 0;
        this.R = null;
        this.S = null;
        this.T = null;
        this.W = true;
        this.X = true;
        this.Y = true;
        this.bh = 0;
        this.bi = null;
        this.bj = null;
        this.bk = null;
        this.bl = 0;
        if (System.getProperty("java.version").compareTo("1.1") >= 0) {
            IpixViewer.br = 1.1f;
        }
        final String property = System.getProperty("os.name");
        if (property.startsWith("Windows")) {
            IpixViewer.bu = 1;
        }
        else if (property.startsWith("Mac")) {
            IpixViewer.bu = 2;
        }
        if (!System.getProperty("java.vendor").startsWith("Apple")) {
            IpixViewer.bv = 100.0f;
        }
        final String property2 = System.getProperty("java.vendor.url");
        if (property2.startsWith("http://devtools.apple.com/mrj")) {
            IpixViewer.bv = 1.02f;
        }
        else if (property2.startsWith("http://www.applejava.apple.com/")) {
            IpixViewer.bv = 2.0f;
        }
        else if (property2.startsWith("http://www.apple.com/macos/java/")) {
            IpixViewer.bv = 2.1f;
        }
        else {
            IpixViewer.bv = 3.0f;
        }
        this.bw = false;
    }
    
    protected Dimension a() {
        try {
            if (this.Q == null || this.Q.width <= 0 || this.Q.height <= 0) {
                this.Q = new Dimension(Integer.parseInt(this.getParameter("Width")), Integer.parseInt(this.getParameter("Height")));
            }
        }
        catch (NumberFormatException ex) {}
        return this.Q;
    }
    
    private long b() {
        return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    }
    
    private void c() {
        try {
            long b = this.b();
            while (System.currentTimeMillis() - System.currentTimeMillis() < 10000L) {
                System.gc();
                Thread.sleep(10L);
                if (IpixViewer.br >= 1.1f) {
                    System.runFinalization();
                }
                final long b2 = this.b();
                if (b <= b2) {
                    break;
                }
                b = b2;
            }
        }
        catch (InterruptedException ex) {}
    }
    
    public void init() {
        System.out.println("IPIX Java Viewer 5.0.1.2");
        System.out.println("(C) 1998 IPIX Corporation.");
        System.out.println("All Rights Reserved.");
        System.out.println(r());
        this.O = (this.getParameter("SpinSpeed") != null || this.getParameter("SpinStyle") != null);
        if (this.getParameter("SpinSpeed") != null) {
            try {
                this.P = Integer.valueOf(this.getParameter("SpinSpeed"));
            }
            catch (Exception ex) {
                ex.printStackTrace();
                this.P = 0;
            }
        }
    }
    
    public void start() {
        synchronized (this.h) {
            synchronized (this) {
                try {
                    if (IpixViewer.bv == 2.0f) {
                        ++this.N;
                    }
                    final URL documentBase = this.getDocumentBase();
                    final String path = documentBase.getPath();
                    this.T = new URL(documentBase, path.substring(0, path.lastIndexOf(47) + 1));
                    for (Container container = this.getParent(); container != null; container = container.getParent()) {
                        if (container instanceof Frame) {
                            this.w = (Frame)container;
                            container.setBackground(Color.white);
                            break;
                        }
                    }
                    this.show();
                    if (this.y == null) {
                        this.y = this.getGraphics();
                    }
                    this.e();
                    this.t = new r(this.getCodeBase());
                    if (this.W) {
                        final String parameter = this.getParameter("splash");
                        if (parameter != null) {
                            this.o = this.getImage(this.getCodeBase(), parameter);
                            this.q = this.t.a(1);
                            final String parameter2 = this.getParameter("SplashBG");
                            if (parameter2 != null) {
                                this.p = new Color(Integer.parseInt(parameter2, 16));
                            }
                        }
                        else {
                            this.o = this.t.a(0);
                        }
                    }
                    if (this.X) {
                        if (this.R == null) {
                            this.a(this.a(this.getParameter("URL"), false));
                        }
                        if (this.f == null) {
                            this.f = new Thread(this);
                            synchronized (this.g) {
                                this.B = false;
                                this.C = false;
                            }
                            this.f.start();
                        }
                    }
                    System.gc();
                }
                catch (OutOfMemoryError outOfMemoryError) {
                    outOfMemoryError.printStackTrace();
                    this.s = "Out Of Memory...";
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                    this.s = "Invalid Applet Parameter ERROR...";
                }
                finally {
                    if (this.s != null) {
                        this.showStatus(this.s);
                    }
                    final Graphics graphics = this.getGraphics();
                    if (graphics == null) {
                        return;
                    }
                    graphics.setColor(Color.white);
                    graphics.fillRect(0, 0, this.a().width, this.a().height);
                    graphics.setColor(Color.black);
                    final FontMetrics fontMetrics = graphics.getFontMetrics();
                    if (this.s != null) {
                        graphics.drawString(this.s, (this.a().width - fontMetrics.stringWidth(this.s)) / 2, this.a().height / 2 - fontMetrics.getDescent() - fontMetrics.getLeading());
                    }
                }
            }
        }
    }
    
    public void stop() {
        this.n();
        synchronized (this.h) {
            if (IpixViewer.br < 1.1f && IpixViewer.bu == 1) {
                synchronized (this) {
                    if (this.f != null) {
                        this.f.stop();
                    }
                    if (this.l != null) {
                        this.l.stop();
                    }
                    if (this.n != null) {
                        this.n.a();
                        this.n = null;
                    }
                    if (this.m != null) {
                        this.m.stop();
                    }
                    this.d();
                    return;
                }
            }
            if (IpixViewer.bv == 2.0f) {
                synchronized (this) {
                    if (this.M >= this.N) {
                        return;
                    }
                    ++this.M;
                }
            }
            this.B = true;
            try {
                this.a(15000);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            this.d();
        }
    }
    
    void d() {
        synchronized (this) {
            if (this.n != null) {
                this.n.a();
                this.n = null;
            }
            this.u = null;
            if (this.v != null) {
                this.v.dispose();
            }
            this.v = null;
            this.k = null;
            this.t = null;
            this.o = null;
            this.s = null;
            if (this.y != null) {
                this.y.dispose();
            }
            this.y = null;
            this.l = null;
            this.m = null;
            this.f = null;
            this.W = true;
            this.X = true;
            this.Y = true;
            this.c();
        }
    }
    
    void a(final int n) throws InterruptedException, IllegalThreadStateException {
        if (Thread.currentThread() == this.f) {
            throw new IllegalThreadStateException();
        }
        synchronized (this.g) {
            if (!this.C) {
                this.B = true;
            }
            try {
                if (this.l != null) {
                    this.l.a(15000);
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            if (this.m != null) {
                try {
                    this.m.a(15000);
                }
                catch (Exception ex2) {
                    ex2.printStackTrace();
                }
            }
            if (this.C || this.f == null) {
                this.B = false;
                return;
            }
        }
        System.currentTimeMillis();
    }
    
    public synchronized void update(final Graphics graphics) {
        if (this.Y) {
            this.a(this.e().getGraphics());
        }
        this.paint(graphics);
    }
    
    protected Image e() {
        if (this.u == null) {
            this.u = this.createImage(this.a().width, this.a().height);
            (this.v = this.u.getGraphics()).setFont(new Font("Arial", 0, 12));
        }
        return this.u;
    }
    
    protected void a(final Graphics graphics) {
        try {
            ((p)this.k.c()).a(graphics);
            if (this.L != null) {
                for (int i = 0; i < this.L.length; ++i) {
                    this.L[i].a(graphics, this.m);
                }
            }
            if (this.E) {
                final Vector b = this.m.b;
                for (int j = 0; j < b.size(); ++j) {
                    if (b.get(j) instanceof bb) {
                        b.get(j).a(graphics);
                    }
                }
            }
            try {
                if (this.n != null) {
                    boolean b2 = this.n.a(graphics);
                    if (!b2 && this.A && p() == 2.0f) {
                        for (int n = 500; !b2 && n <= 1000; b2 = this.n.a(graphics), n += 100) {
                            Thread.sleep(n);
                        }
                        this.A = false;
                    }
                }
            }
            catch (InterruptedException ex) {}
        }
        catch (NullPointerException ex2) {
            this.b(graphics);
        }
    }
    
    public synchronized void paint(final Graphics graphics) {
        super.paint(graphics);
        try {
            if (this.u != null) {
                graphics.drawImage(this.u, 0, 0, null);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    protected URL a(final String s, final boolean b) {
        try {
            final URL url = new URL(s);
            if (url.getProtocol() != "") {
                return url;
            }
        }
        catch (MalformedURLException ex) {}
        URL url2 = null;
        try {
            if (b) {
                url2 = new URL(this.S + s);
            }
            else {
                url2 = new URL(this.T + s);
            }
        }
        catch (MalformedURLException ex2) {}
        return url2;
    }
    
    protected void a(final URL r) {
        this.R = r;
        try {
            final String path = this.R.getPath();
            this.S = new URL(this.R, path.substring(0, path.lastIndexOf(47) + 1));
        }
        catch (MalformedURLException ex) {}
    }
    
    protected q a(final bl bl, final URL url, final e e) throws IOException, InterruptedException {
        final q q = new q();
        System.out.println("Loading IPIX: '" + url + "'...");
        bl.a(e);
        bl.a(url.openStream());
        synchronized (this.i) {
            bl.a(e);
            bl.a(q);
            if (this.B) {
                return null;
            }
            System.out.println("  waiting for IPIX to be loaded...");
            this.i.wait();
        }
        System.out.println("  waiting for IPIX to be loaded done.");
        return q;
    }
    
    protected bl b(final URL url) {
        float n;
        try {
            final String parameter = this.getParameter("InitFOV");
            n = ((parameter != null) ? ((float)(int)(Object)Float.valueOf(parameter)) : -1.0f);
        }
        catch (NumberFormatException ex) {
            n = 100.0f;
        }
        return new bm(url, n);
    }
    
    protected q f() throws IOException, InterruptedException {
        this.bl |= IpixViewer.bp;
        this.b(3);
        (this.k = new m()).a(new l());
        this.l = this.b(this.R);
        final q a = this.a(this.l, this.R, this);
        this.bl &= ~IpixViewer.bp;
        return a;
    }
    
    public void run() {
        try {
            if (this.B) {
                return;
            }
            this.b(3);
            (this.k = new m()).a(new l());
            final q f = this.f();
            if (f == null) {
                return;
            }
            this.k.a(f);
            synchronized (this) {
                if (this.B) {
                    return;
                }
                this.k.b(new p(this.a()));
                this.v.setColor(Color.black);
                this.v.fillRect(0, 0, this.a().width, this.a().height);
                this.m = new bk(this);
                this.a(bj.a((float[])this.k.b().getProperty("invp")), true);
            }
            final String parameter = this.getParameter("toolbar");
            if (parameter != null && parameter.equals("off")) {
                this.D = false;
            }
            this.n = new bg(this.m);
            this.requestFocus();
            this.m.start();
            if (this.B) {
                return;
            }
            if (this.O) {
                this.m.a(new bf(this.m, this.P));
            }
            synchronized (this.i) {
                if (this.B) {
                    return;
                }
                if (this.l != null) {
                    this.i.wait();
                }
            }
            if (this.B) {
                return;
            }
            final q q = (q)this.k.b();
            this.L = (s[])q.getProperty("spts");
            final ImageProducer d = q.d();
            if (!this.g()) {
                return;
            }
            this.f.setPriority(1);
            this.m.setPriority(4);
            Thread.sleep(0L);
            final q q2 = (q)q.e();
            final Image image = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(d, new b()));
            final Dimension dimension = new Dimension(q.b().width * 2, q.b().height * 2);
            final float[] array = { q.c()[0] * 2.0f + 0.5f, q.c()[1] * 2.0f + 0.5f };
            try {
                q2.a("irad", new Float((float)q.getProperty("irad") * 2.0f));
            }
            catch (NullPointerException ex3) {}
            new w(q2, image).a();
            q2.a(array);
            this.k.a(q2);
            this.m.setPriority(1);
            if (!this.m.c(null)) {
                this.k();
            }
        }
        catch (InterruptedException ex4) {}
        catch (IOException ex) {
            this.s = "File access error.";
            ex.printStackTrace();
        }
        catch (AccessControlException ex2) {
            this.s = "Image not available. For codebases using 'file' protocol, images must reside in same folder as Applet.";
            ex2.printStackTrace();
        }
        finally {
            if (this.s != null && !this.B) {
                this.repaint();
                this.b(0);
            }
            this.c();
            synchronized (this.g) {
                this.C = true;
                this.B = false;
            }
        }
    }
    
    protected boolean g() {
        return this.k.b().b().width <= 700 && !System.getProperty("java.vendor").startsWith("Netscape") && IpixViewer.br >= 1.1f && this.getParameter("UpSample") == null && IpixViewer.bu == 1;
    }
    
    public void a(final int n, final float z) {
        if (n == -1) {
            this.l = null;
            this.s = "Failed to load image.";
            this.repaint();
            this.b(0);
            this.B = true;
            return;
        }
        if (n == 4) {
            synchronized (this.i) {
                this.i.notify();
            }
            this.l = null;
            return;
        }
        if (n == 3) {
            this.z = z;
            this.showStatus("Downloaded " + (int)Math.ceil(this.z * 100.0f) + "% of image...");
            if (this.k.c() == null) {
                this.update(this.y);
            }
            return;
        }
        Label_0209: {
            if (this.m == null) {
                synchronized (this.i) {
                    this.i.notify();
                    break Label_0209;
                }
            }
            if (this.m.c() == null || this.m.c() instanceof bf) {
                this.a(this.j(), true);
                this.k();
            }
        }
        if (n == 2) {
            this.l = null;
            synchronized (this.i) {
                this.i.notify();
            }
            this.b(13);
            this.showStatus("IPIX Java Viewer 5.0.1.2");
        }
    }
    
    float[] h() {
        if (this.k != null && this.k.b() != null) {
            return (float[])this.k.b().getProperty("invp");
        }
        return new float[] { 0.0f, 0.0f, 0.0f, 0.0f };
    }
    
    float[] i() {
        if (this.k != null && this.k.b() != null) {
            return (float[])this.k.b().getProperty("invp");
        }
        return new float[] { 0.0f, 0.0f, 0.0f, 0.0f };
    }
    
    float[] j() {
        if (this.k != null) {
            return (float[])this.k.getProperty("ptrz");
        }
        return new float[] { 0.0f, 0.0f, 0.0f, 0.0f };
    }
    
    boolean a(final float[] array, final boolean b) {
        try {
            final float[] j = this.j();
            this.k.a("ptrz", array);
            if (!b && bj.c(j, array)) {
                return false;
            }
            if (this.L != null) {
                for (int i = 0; i < this.L.length; ++i) {
                    this.L[i].a(array, this.L[i].f(), this.m.b().getSize());
                }
            }
            return true;
        }
        catch (NullPointerException ex) {
            return false;
        }
    }
    
    synchronized void k() {
        try {
            this.k.a();
            this.update(this.y);
        }
        catch (NullPointerException ex) {}
    }
    
    protected void a(final Graphics graphics, final Image image) {
        final int width = this.a().width;
        final int height = this.a().height;
        graphics.setColor(this.p);
        graphics.fillRect(0, 0, width, height);
        final Dimension dimension = new Dimension(image.getWidth(null), image.getHeight(null));
        final int n = (int)dimension.getWidth();
        final int n2 = (int)dimension.getHeight();
        boolean b = false;
        double n3 = 0.0;
        if (n > width) {
            b = true;
            n3 = 100 * width / n;
        }
        boolean b2 = false;
        double n4 = 0.0;
        if (n2 > height) {
            b2 = true;
            n4 = 100 * height / n2;
        }
        int n5 = (width - n) / 2;
        if (n5 < 0) {
            n5 = 0;
        }
        int n6 = (height - n2) / 2;
        if (n6 < 0) {
            n6 = 0;
        }
        if (b && b2) {
            graphics.drawImage(image, n5, n6, n, n2, this);
        }
        else if (!b && !b2) {
            graphics.drawImage(image, n5, n6, this);
        }
        else if (b && !b2) {
            final int n7 = (int)Math.round(n2 * n3 / 100.0);
            graphics.drawImage(image, 0, (height - n7) / 2, width, n7, this);
        }
        else if (!b && b2) {
            final int n8 = (int)Math.round(n * n4 / 100.0);
            graphics.drawImage(image, (width - n8) / 2, 0, n8, height, this);
        }
        else {
            graphics.drawImage(image, 0, 0, width, height, this);
        }
    }
    
    protected void b(final Graphics graphics) {
        try {
            final int width = this.a().width;
            final int height = this.a().height;
            graphics.setColor(this.p);
            graphics.fillRect(0, 0, width, height);
            final Dimension dimension = new Dimension(this.o.getWidth(null), this.o.getHeight(null));
            final int n = height / 10;
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            String s = "Loading Image...";
            if (this.s != null) {
                s = this.s;
            }
            final int stringWidth = fontMetrics.stringWidth(s);
            final int n2 = (width - stringWidth) / 2;
            final int n3 = height - n - fontMetrics.getDescent() - fontMetrics.getLeading();
            final int n4 = (width <= 320) ? 2 : 1;
            final int max = Math.max(width / 2, stringWidth);
            final int n5 = IpixViewer.r.length / n4;
            final int n6 = (width - max) / 2;
            final int n7 = n3 - n5 - 15;
            Dimension dimension2;
            if (this.q != null) {
                dimension2 = new Dimension(this.q.getWidth(null), this.q.getHeight(null));
            }
            else {
                dimension2 = new Dimension(0, 0);
            }
            final int n8 = width;
            final int n9 = 6 * height / 10;
            final int n10 = n9 - Math.max(0, n + n9 + dimension2.height + 10 + (height - n7) - height);
            final float min = Math.min(Math.min(n8 / dimension.width, n10 / dimension.height), 1.0f);
            dimension.width *= (int)min;
            dimension.height *= (int)min;
            final int n11 = (width - dimension.width) / 2;
            final int n12 = n + (n10 - dimension.height) / 2;
            graphics.drawImage(this.o, n11, n12, dimension.width, dimension.height, this);
            if (this.q != null) {
                graphics.drawImage(this.q, (dimension2.width < dimension.width) ? (n11 + dimension.width - dimension2.width) : ((width - dimension2.width) / 2), n12 + dimension.height + 2, dimension2.width, dimension2.height, this);
            }
            graphics.setColor(Color.black);
            graphics.drawString(s, n2, n3);
            if (this.s == null) {
                graphics.setColor(new Color(-4144960));
                graphics.fillRect(n6 - 2, n7 - 1, max + 2, n5 + 2);
                graphics.setColor(Color.black);
                graphics.drawRect(n6 - 2, n7 - 1, max + 2, n5 + 2);
                for (int i = 0; i < n5; ++i) {
                    graphics.setColor(new Color(IpixViewer.r[i * n4]));
                    graphics.drawLine(n6, n7 + 1 + i, n6 + (int)(this.z * (max - 1)), n7 + 1 + i);
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    m l() {
        return this.k;
    }
    
    Object m() {
        return this.l;
    }
    
    public void b(final String s, final boolean b) {
        this.stop();
        this.a(this.a(s, b));
        this.start();
    }
    
    void c(final String s, final boolean b) {
        this.n();
        final URL a = this.a(s, b);
        if (a != null && p() != 2.0f) {
            this.bk = this.getAudioClip(a);
            this.bl |= IpixViewer.bn;
            this.bk.play();
            this.bl &= ~IpixViewer.bn;
        }
    }
    
    void n() {
        try {
            this.bk.stop();
        }
        catch (NullPointerException ex) {}
    }
    
    void a(final String s, String s2, final boolean b) {
        final URL a = this.a(s, b);
        if (s2 == null) {
            s2 = "_self";
        }
        if (a != null && p() != 2.0f) {
            this.getAppletContext().showDocument(a, s2);
        }
    }
    
    void b(final String s, String s2, final boolean b) {
        final URL a = this.a(s, b);
        if (s2 == null) {
            s2 = "_self";
        }
        if (a != null && p() != 2.0f) {
            this.getAppletContext().showDocument(a, s2);
        }
    }
    
    void o() {
        try {
            String parameter = this.getParameter("HelpFrame");
            if (parameter == null) {
                parameter = "_blank";
            }
            String parameter2 = this.getParameter("HelpURL");
            if (parameter2 == null) {
                parameter2 = "http://www.ipix.com/help/java5_0/index.html";
            }
            final URL url = new URL(parameter2);
            if (p() != 2.0f) {
                this.getAppletContext().showDocument(url, parameter);
            }
        }
        catch (MalformedURLException ex) {}
    }
    
    void b(final int cursor) {
        if (p() > 2.0f) {
            this.w.setCursor(cursor);
        }
    }
    
    void a(final y y) {
        this.x.addElement(y);
    }
    
    void a(final y y, int n) {
        if (n < 0) {
            n = 0;
        }
        if (n >= this.x.size()) {
            n = this.x.size() - 1;
        }
        final int index = this.x.indexOf(y);
        if (index == -1 || index == n) {
            return;
        }
        this.x.removeElement(y);
        this.x.insertElementAt(y, n);
    }
    
    void b(final y y) {
        this.x.removeElement(y);
    }
    
    public boolean handleEvent(final Event event) {
        final a a = new a(event);
        for (int i = 0; i < this.x.size(); ++i) {
            ((y)this.x.elementAt(i)).a(a);
        }
        return super.handleEvent(event);
    }
    
    static final float p() {
        return IpixViewer.bv;
    }
    
    public void a(final String s) {
        this.a(s, null);
    }
    
    public void a(final String s, final String s2) {
        try {
            if (s.equalsIgnoreCase("stop")) {
                this.m.a((y)null);
            }
            else if (s.equalsIgnoreCase("spin")) {
                this.m.a(new bf(this.m, this.P));
            }
            else if (s.equalsIgnoreCase("home")) {
                this.m.a(new bc(this.m));
            }
            else if (s.equalsIgnoreCase("go")) {
                final StringTokenizer stringTokenizer = new StringTokenizer(s2, ",");
                final float[] array = new float[stringTokenizer.countTokens()];
                int n = 0;
                while (stringTokenizer.hasMoreElements()) {
                    array[n] = Float.valueOf((String)stringTokenizer.nextElement()) * 0.017453292f;
                    ++n;
                }
                this.m.a(new bc(this.m, array));
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private static String a(final Color color) {
        final String hexString = Integer.toHexString(color.getRGB() & 0xFFFFFF);
        return "#" + "000000".substring(hexString.length()) + hexString;
    }
    
    public String getProperty(final String s) {
        if (s.equalsIgnoreCase("menu")) {
            return String.valueOf(this.F);
        }
        if (s.equalsIgnoreCase("contextualMenuOn")) {
            return String.valueOf(this.F);
        }
        if (s.equalsIgnoreCase("ehpt")) {
            return String.valueOf(this.G);
        }
        if (s.equalsIgnoreCase("hotspotsActive")) {
            return String.valueOf(this.G);
        }
        if (s.equalsIgnoreCase("oclr")) {
            return a(this.K);
        }
        if (s.equalsIgnoreCase("hotspotBoundsColor")) {
            return a(this.K);
        }
        if (s.equalsIgnoreCase("initialViewpointPan")) {
            return Float.toString(this.h()[0] * 57.295776f);
        }
        if (s.equalsIgnoreCase("initialViewpointTilt")) {
            return Float.toString(this.h()[1] * 57.295776f);
        }
        if (s.equalsIgnoreCase("initialViewpointRotate")) {
            return Float.toString(this.h()[2] * 57.295776f);
        }
        if (s.equalsIgnoreCase("initialViewpointZoom")) {
            return Float.toString(this.h()[3]);
        }
        if (s.equalsIgnoreCase("referenceViewpointPan")) {
            return Float.toString(this.i()[0] * 57.295776f);
        }
        if (s.equalsIgnoreCase("referenceViewpointTilt")) {
            return Float.toString(this.i()[1] * 57.295776f);
        }
        if (s.equalsIgnoreCase("referenceViewpointRotate")) {
            return Float.toString(this.i()[2] * 57.295776f);
        }
        if (s.equalsIgnoreCase("referenceViewpointZoom")) {
            return Float.toString(this.i()[3]);
        }
        if (s.equalsIgnoreCase("viewpointPan")) {
            return Float.toString(this.j()[0] * 57.295776f);
        }
        if (s.equalsIgnoreCase("viewpointTilt")) {
            return Float.toString(this.j()[1] * 57.295776f);
        }
        if (s.equalsIgnoreCase("viewpointRotate")) {
            return Float.toString(this.j()[2] * 57.295776f);
        }
        if (s.equalsIgnoreCase("viewpointZoom")) {
            return Float.toString(this.j()[3]);
        }
        if (s.equalsIgnoreCase("navi")) {
            return String.valueOf(this.J);
        }
        if (s.equalsIgnoreCase("navigationOn")) {
            return String.valueOf(this.J);
        }
        if (s.equalsIgnoreCase("shpt")) {
            return String.valueOf(this.I);
        }
        if (s.equalsIgnoreCase("popupTextOn")) {
            return String.valueOf(this.I);
        }
        if (s.equalsIgnoreCase("state")) {
            return String.valueOf(this.bl);
        }
        if (s.equalsIgnoreCase("shtg")) {
            return String.valueOf(this.H);
        }
        if (s.equalsIgnoreCase("targetsOn")) {
            return String.valueOf(this.H);
        }
        if (s.equalsIgnoreCase("tlbs") || s.equalsIgnoreCase("toolbarState")) {
            if (this.n != null) {
                return Integer.toString(this.n.d());
            }
            return "-1";
        }
        else {
            if (!s.equalsIgnoreCase("user")) {
                return null;
            }
            if ((this.bl & IpixViewer.bo) != 0x0) {
                return "1";
            }
            return "0";
        }
    }
    
    public String q() {
        return this.getProperty("tlbs");
    }
    
    public void b(final String s) {
        this.b("tlbs", s);
    }
    
    public void b(final String s, final String s2) {
        if (s.equalsIgnoreCase("menu")) {
            this.F = new Boolean(s2);
        }
        else if (s.equalsIgnoreCase("contextualMenuOn")) {
            this.F = new Boolean(s2);
        }
        else if (s.equalsIgnoreCase("ehpt")) {
            this.G = new Boolean(s2);
        }
        else if (s.equalsIgnoreCase("hotspotsActive")) {
            this.G = new Boolean(s2);
        }
        else if (s.equalsIgnoreCase("oclr")) {
            this.K = new Color(Integer.parseInt(s2.substring(1), 16));
        }
        else if (s.equalsIgnoreCase("hotspotBoundsColor")) {
            this.K = new Color(Integer.parseInt(s2.substring(1), 16));
        }
        else if (s.equalsIgnoreCase("navi")) {
            this.J = new Boolean(s2);
        }
        else if (s.equalsIgnoreCase("navigationOn")) {
            this.J = new Boolean(s2);
        }
        else if (s.equalsIgnoreCase("shpt")) {
            this.I = new Boolean(s2);
        }
        else if (s.equalsIgnoreCase("popupTextOn")) {
            this.I = new Boolean(s2);
        }
        else if (s.equalsIgnoreCase("shtg")) {
            this.H = new Boolean(s2);
        }
        else if (s.equalsIgnoreCase("targetsOn")) {
            this.H = new Boolean(s2);
        }
        else if (s.equalsIgnoreCase("tlbs")) {
            this.n.b(Integer.parseInt(s2));
        }
        else if (s.equalsIgnoreCase("toolbarState")) {
            this.n.b(Integer.parseInt(s2));
        }
        if (s.equalsIgnoreCase("user")) {
            if ("0".equals(s2)) {
                this.bl &= ~IpixViewer.bo;
            }
            else {
                this.bl |= IpixViewer.bo;
            }
        }
    }
    
    public int GetDataItemInt(final String s) {
        final Object d = this.d(s);
        if (d instanceof Integer) {
            return (int)d;
        }
        try {
            return new Integer(Integer.parseInt(d.toString()));
        }
        catch (NumberFormatException ex) {}
        catch (NullPointerException ex2) {}
        return 0;
    }
    
    public float GetDataItemFloat(final String s) {
        final Object d = this.d(s);
        if (d instanceof Float) {
            return (float)d;
        }
        try {
            return new Float(Float.parseFloat(d.toString()));
        }
        catch (NumberFormatException ex) {}
        catch (NullPointerException ex2) {}
        return Float.NaN;
    }
    
    public boolean c(final String s) {
        final Object d = this.d(s);
        try {
            return new Boolean((String)d);
        }
        catch (ClassCastException ex) {}
        catch (NullPointerException ex2) {}
        return false;
    }
    
    public String GetDataItemString(final String s) {
        return this.e(s);
    }
    
    public Object d(final String s) {
        Object o = null;
        if (this.k != null) {
            if (this.k.b() != null) {
                o = this.k.b().getProperty(s);
            }
            if (o == null || "".equals(o.toString())) {
                o = this.k.getProperty(s);
            }
        }
        if (o == null || "".equals(o.toString())) {
            o = this.getProperty(s);
        }
        return o;
    }
    
    public String e(final String s) {
        Object o = this.d(s);
        if (o == null) {
            return "";
        }
        if (!((s[])o).getClass().isArray()) {
            return o.toString();
        }
        try {
            o = this.g(s);
        }
        catch (ClassCastException ex) {}
        if (o == null) {
            try {
                o = this.f(s);
            }
            catch (ClassCastException ex2) {}
        }
        if (o == null) {
            return "";
        }
        final StringBuffer sb = new StringBuffer();
        sb.append("Array[");
        final s[] array = (s[])o;
        for (int i = 0; i < array.length; ++i) {
            sb.append(array[i]);
            if (i != array.length - 1) {
                sb.append(" | ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
    
    public s[] f(final String s) {
        final Object d = this.d(s);
        if (d == null) {
            return null;
        }
        if (((s[])d).getClass().isArray()) {
            return (s[])d;
        }
        return null;
    }
    
    public Float[] g(final String s) {
        final Object d = this.d(s);
        if (d == null) {
            return null;
        }
        if (((float[])d).getClass().isArray()) {
            final float[] array = (float[])d;
            final Float[] array2 = new Float[array.length];
            for (int i = 0; i < array.length; ++i) {
                array2[i] = new Float(array[i]);
            }
            return array2;
        }
        return null;
    }
    
    public void SetDataItemInt(final String s, final int n) {
        this.a(s, new Integer(n));
    }
    
    public void SetDataItemInt(final String s, final String s2) {
        this.a(s, new Integer(s2));
    }
    
    public void SetDataItemFloat(final String s, final double n) {
        this.a(s, new Float(n));
    }
    
    public void SetDataItemFloat(final String s, final float n) {
        this.a(s, new Float(n));
    }
    
    public void SetDataItemFloat(final String s, final String s2) {
        this.a(s, new Float(s2));
    }
    
    public void d(final String s, final boolean b) {
        this.a(s, new Boolean(b));
    }
    
    public void c(final String s, final String s2) {
        this.a(s, new Boolean(s2));
    }
    
    public void SetDataItemString(final String s, final String s2) {
        this.a(s, (Object)s2);
    }
    
    protected void a(final String s, final Object o) {
        if (s == null || "".equals(s) || o == null) {
            return;
        }
        if (this.k != null) {
            if (this.k.b() != null) {
                this.k.b().a(s, o);
            }
            else {
                this.k.a(s, o);
            }
        }
        else {
            this.b(s, o.toString());
        }
        this.repaint();
    }
    
    public void LaunchMedia(final String s, final boolean b) {
        this.b(s, null, b);
    }
    
    public void h(final String s) {
        this.c(s, false);
    }
    
    public void d(final String s, final String s2) {
        this.LoadImage(s, new Boolean(s2));
    }
    
    protected void LoadImage(final String s, final boolean b) {
        this.a(this.a(s, false), b);
    }
    
    public void e(final String s, final String s2) {
        this.e(s, new Boolean(s2));
    }
    
    public void e(final String s, final boolean b) {
        this.a(this.a(s, false), b);
    }
    
    protected void a(final URL url, final boolean b) {
        synchronized (this.i) {
            if (this.B) {
                return;
            }
            if (this.j != null) {
                try {
                    this.i.wait();
                }
                catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
        if (!b) {
            this.bh = 0;
            this.b(3);
        }
        else {
            this.bh = 2;
        }
        this.bi = this.getImage(url);
        final bn bn = new bn(this, this.bi, b);
        bn.start();
        if (!b) {
            try {
                bn.join();
            }
            catch (InterruptedException ex2) {
                ex2.printStackTrace();
            }
            final bo bo = new bo(this, this.bi);
            bo.start();
            try {
                bo.join();
            }
            catch (InterruptedException ex3) {
                ex3.printStackTrace();
            }
        }
    }
    
    public void LoadIpix(final String s, final String s2) {
        this.LoadIpix(s, new Boolean(s2));
    }
    
    public void LoadIpix(final String s, final boolean b) {
        synchronized (this.i) {
            if (this.B) {
                return;
            }
            if (this.j != null) {
                try {
                    this.i.wait();
                }
                catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
        this.bh = 0;
        this.b(s, false);
    }
    
    public void MoveToForeground() {
    }
    
    public void Pause() {
        if (!this.s()) {
            return;
        }
        this.m.a((y)null);
    }
    
    public void Resume() {
        if (!this.s()) {
            return;
        }
        if (this.bw) {
            this.SpinTo(this.bx, this.by, this.bz, this.bA, this.bB, this.bC, this.bD, this.bE);
        }
        else {
            this.m.a(new bf(this.m, this.P));
        }
    }
    
    public void Spin(int p) {
        if (!this.s()) {
            return;
        }
        if (p > 9) {
            p = 9;
        }
        else if (p < -9) {
            p = -9;
        }
        this.P = p;
        this.bw = false;
        this.m.a(new bf(this.m, this.P));
    }
    
    public void SetReferenceViewpoint(final String s, final String s2, final String s3, final String s4) {
        this.SetReferenceViewpoint(Float.parseFloat(s), Float.parseFloat(s2), Float.parseFloat(s3), Float.parseFloat(s4));
    }
    
    public void SetReferenceViewpoint(final float n, final float n2, final float n3, final float n4) {
        if (!this.s()) {
            return;
        }
        this.k.b().a("invp", new float[] { 0.017453292f * n, 0.017453292f * n2, 0.017453292f * n3, n4 });
    }
    
    public void SetViewpoint(final String s, final String s2, final String s3, final String s4) {
        this.SetViewpoint(Float.parseFloat(s), Float.parseFloat(s2), Float.parseFloat(s3), Float.parseFloat(s4));
    }
    
    public void SetViewpoint(final float n, final float n2, final float n3, final float n4) {
        if (!this.s()) {
            return;
        }
        this.a(new float[] { 0.017453292f * n, 0.017453292f * n2, 0.017453292f * n3, n4 }, true);
        this.Resume();
    }
    
    public void SpinTo(final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7, final String s8) {
        this.SpinTo(Float.parseFloat(s), Float.parseFloat(s2), Float.parseFloat(s3), Float.parseFloat(s4), Float.parseFloat(s5), Float.parseFloat(s6), Float.parseFloat(s7), Integer.parseInt(s8));
    }
    
    public void SpinTo(final float bx, final float by, final float bz, final float ba, final float bb, final float bc, final float bd, final int be) {
        if (!this.s()) {
            return;
        }
        final float[] array = { 0.017453292f * bx, 0.017453292f * by, 0.017453292f * bz, ba };
        this.bw = true;
        this.bx = bx;
        this.by = by;
        this.bz = bz;
        this.bA = ba;
        this.bB = bb;
        this.bC = bc;
        this.bD = bd;
        this.bE = be;
        if (bb == 0.0f && bc == 0.0f && bd == 0.0f) {
            this.m.a(new bc(this.m, array, be));
        }
        else {
            this.m.a(new ba(this.m, array, bb, bc, bd, be));
        }
    }
    
    public static String r() {
        final StringBuffer sb = new StringBuffer();
        final Runtime runtime = Runtime.getRuntime();
        sb.append("The amount of free memory in the Java Virtual Machine");
        sb.append(": " + runtime.freeMemory() + " bytes\n");
        sb.append("The maximum amount of memory");
        sb.append(": " + runtime.totalMemory() + " bytes\n");
        try {
            sb.append("Java version");
            sb.append(": " + System.getProperty("java.version") + "\n");
        }
        catch (Exception ex) {}
        try {
            sb.append("JVM name");
            sb.append(": " + System.getProperty("java.vm.name") + "\n");
        }
        catch (Exception ex2) {}
        try {
            sb.append("JVM version");
            sb.append(": " + System.getProperty("java.vm.version") + "\n");
        }
        catch (Exception ex3) {}
        try {
            sb.append("JVM vendor");
            sb.append(": " + System.getProperty("java.vendor") + "\n");
        }
        catch (Exception ex4) {}
        try {
            sb.append("Java specification name");
            sb.append(": " + System.getProperty("java.specification.name") + "\n");
        }
        catch (Exception ex5) {}
        try {
            sb.append("Java specification vendor");
            sb.append(": " + System.getProperty("java.specification.vendor") + "\n");
        }
        catch (Exception ex6) {}
        try {
            sb.append("Java specification version");
            sb.append(": " + System.getProperty("java.specification.version") + "\n");
        }
        catch (Exception ex7) {}
        try {
            sb.append("OS architecture");
            sb.append(": " + System.getProperty("os.arch") + "\n");
        }
        catch (Exception ex8) {}
        try {
            sb.append("OS name");
            sb.append(": " + System.getProperty("os.name") + "\n");
        }
        catch (Exception ex9) {}
        try {
            sb.append("OS version");
            sb.append(": " + System.getProperty("os.version") + "\n");
        }
        catch (Exception ex10) {}
        try {
            sb.append("Browser");
            sb.append(": " + System.getProperty("http.agent") + "\n");
        }
        catch (Exception ex11) {}
        return sb.toString();
    }
    
    public boolean s() {
        return this.k != null && this.k.b() != null && this.j == null;
    }
    
    static {
        r = new int[] { -1, -2500135, -2500135, -6249818, -8816263, -16777216 };
        IpixViewer.bm = 1;
        IpixViewer.bn = 2;
        IpixViewer.bo = 4;
        IpixViewer.bp = 8;
        IpixViewer.bq = 16;
        IpixViewer.br = 0.0f;
        IpixViewer.bu = 0;
        IpixViewer.bv = 0.0f;
    }
}
