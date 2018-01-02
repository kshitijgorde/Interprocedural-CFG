import java.util.StringTokenizer;
import java.awt.Event;
import java.io.IOException;
import java.net.MalformedURLException;
import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.Toolkit;
import java.net.URL;
import java.awt.image.ImageObserver;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Container;
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

public class IpixViewer extends Applet implements Runnable
{
    private static final String a = "iPIX Java Viewer 4.0";
    private static final String b = "(C) 1986 - 2003 Internet Pictures Corporation.";
    private static final String c = "All Rights Reserved.";
    private static final String d = "Loading Image...";
    private Thread e;
    protected final Object f;
    protected final Object g;
    protected final Object h;
    protected k i;
    protected bc j;
    protected bb k;
    protected x l;
    protected Image m;
    Color n;
    protected Image o;
    protected static final int[] p;
    protected String q;
    protected p r;
    protected Image s;
    protected Graphics t;
    protected Frame u;
    protected Vector v;
    protected Graphics w;
    protected float x;
    protected boolean y;
    protected boolean z;
    protected boolean A;
    protected boolean B;
    private int C;
    private int D;
    boolean E;
    private Dimension F;
    protected static float G;
    protected static final int H = 1;
    protected static final int I = 2;
    protected static int J;
    protected static float K;
    
    public IpixViewer() {
        this.e = null;
        this.f = new Object();
        this.g = new Object();
        this.h = new Object();
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = Color.white;
        this.o = null;
        this.q = null;
        this.r = null;
        this.s = null;
        this.t = null;
        this.u = null;
        this.v = new Vector();
        this.w = null;
        this.x = 0.0f;
        this.y = true;
        this.z = false;
        this.A = false;
        this.B = true;
        this.C = 0;
        this.D = 0;
        this.E = false;
        if (System.getProperty("java.version").compareTo("1.1") >= 0) {
            IpixViewer.G = 1.1f;
        }
        final String property = System.getProperty("os.name");
        if (property.startsWith("Windows")) {
            IpixViewer.J = 1;
        }
        else if (property.startsWith("Mac")) {
            IpixViewer.J = 2;
        }
        if (!System.getProperty("java.vendor").startsWith("Apple")) {
            IpixViewer.K = 100.0f;
        }
        final String property2 = System.getProperty("java.vendor.url");
        if (property2.startsWith("http://devtools.apple.com/mrj")) {
            IpixViewer.K = 1.02f;
        }
        else if (property2.startsWith("http://www.applejava.apple.com/")) {
            IpixViewer.K = 2.0f;
        }
        else if (property2.startsWith("http://www.apple.com/macos/java/")) {
            IpixViewer.K = 2.1f;
        }
        else {
            IpixViewer.K = 3.0f;
        }
    }
    
    protected Dimension a() {
        try {
            if (this.F == null || this.F.width <= 0 || this.F.height <= 0) {
                this.F = new Dimension(Integer.parseInt(this.getParameter("Width")), Integer.parseInt(this.getParameter("Height")));
            }
        }
        catch (NumberFormatException ex) {}
        return this.F;
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
                if (IpixViewer.G >= 1.1f) {
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
        System.out.println("iPIX Java Viewer 4.0");
        System.out.println("(C) 1986 - 2003 Internet Pictures Corporation.");
        System.out.println("All Rights Reserved.");
        this.E = (this.getParameter("SpinSpeed") != null || this.getParameter("SpinStyle") != null);
    }
    
    public void start() {
        synchronized (this.g) {
            synchronized (this) {
                try {
                    if (IpixViewer.K == 2.0f) {
                        ++this.D;
                    }
                    for (Container container = this.getParent(); container != null; container = container.getParent()) {
                        if (container instanceof Frame) {
                            this.u = (Frame)container;
                            container.setBackground(Color.white);
                            break;
                        }
                    }
                    this.show();
                    if (this.w == null) {
                        this.w = this.getGraphics();
                    }
                    this.r = new p(this.getCodeBase());
                    final String parameter = this.getParameter("splash");
                    if (parameter != null) {
                        this.m = this.getImage(this.getDocumentBase(), parameter);
                        this.o = this.r.a(1);
                        final String parameter2 = this.getParameter("SplashBG");
                        if (parameter2 != null) {
                            this.n = new Color(Integer.parseInt(parameter2, 16));
                        }
                    }
                    else {
                        this.m = this.r.a(0);
                    }
                    this.update(this.w);
                    if (this.e == null) {
                        this.e = new Thread(this);
                        synchronized (this.f) {
                            this.z = false;
                            this.A = false;
                        }
                        this.e.start();
                    }
                    System.gc();
                }
                catch (OutOfMemoryError outOfMemoryError) {
                    this.q = "Out Of Memory...";
                }
                catch (Exception ex) {
                    this.q = "Invalid Applet Parameter Error...";
                }
                finally {
                    this.showStatus(this.q);
                    final Graphics graphics = this.getGraphics();
                    if (graphics == null) {
                        return;
                    }
                    graphics.setColor(Color.white);
                    graphics.fillRect(0, 0, this.a().width, this.a().height);
                    graphics.setColor(Color.black);
                    final FontMetrics fontMetrics = graphics.getFontMetrics();
                    graphics.drawString(this.q, (this.a().width - fontMetrics.stringWidth(this.q)) / 2, this.a().height / 2 - fontMetrics.getDescent() - fontMetrics.getLeading());
                }
            }
        }
    }
    
    public void stop() {
        synchronized (this.g) {
            if (IpixViewer.G < 1.1f && IpixViewer.J == 1) {
                synchronized (this) {
                    if (this.e != null) {
                        this.e.stop();
                    }
                    if (this.j != null) {
                        this.j.stop();
                    }
                    if (this.l != null) {
                        this.l.a();
                        this.l = null;
                    }
                    if (this.k != null) {
                        this.k.stop();
                    }
                    this.d();
                    return;
                }
            }
            if (IpixViewer.K == 2.0f) {
                synchronized (this) {
                    if (this.C >= this.D) {
                        return;
                    }
                    ++this.C;
                }
            }
            this.z = true;
            try {
                this.a(15000);
            }
            catch (Exception ex) {}
            this.d();
        }
    }
    
    void d() {
        synchronized (this) {
            if (this.l != null) {
                this.l.a();
                this.l = null;
            }
            this.s = null;
            if (this.t != null) {
                this.t.dispose();
            }
            this.t = null;
            this.i = null;
            this.r = null;
            this.m = null;
            this.q = null;
            if (this.w != null) {
                this.w.dispose();
            }
            this.w = null;
            this.j = null;
            this.k = null;
            this.e = null;
            this.c();
        }
    }
    
    void a(final int n) throws InterruptedException, IllegalThreadStateException {
        if (Thread.currentThread() == this.e) {
            throw new IllegalThreadStateException();
        }
        synchronized (this.f) {
            if (!this.A) {
                this.z = true;
            }
            try {
                this.j.a(15000);
            }
            catch (Exception ex) {}
            if (this.k != null) {
                try {
                    this.k.a(15000);
                }
                catch (Exception ex2) {}
            }
            if (this.A || this.e == null) {
                this.z = false;
                return;
            }
        }
        final long currentTimeMillis = System.currentTimeMillis();
        while (this.z && System.currentTimeMillis() - currentTimeMillis < n) {
            Thread.sleep(10L);
        }
    }
    
    public synchronized void update(final Graphics graphics) {
        if (this.s == null) {
            this.s = this.createImage(this.a().width, this.a().height);
            (this.t = this.s.getGraphics()).setFont(new Font("Arial", 0, 12));
        }
        this.a(this.t);
        this.paint(graphics);
    }
    
    protected void a(final Graphics graphics) {
        try {
            ((n)this.i.c()).a(graphics);
            try {
                if (this.l != null) {
                    boolean b = this.l.a(graphics);
                    if (!b && this.y && k() == 2.0f) {
                        for (int n = 500; !b && n <= 1000; b = this.l.a(graphics), n += 100) {
                            Thread.sleep(n);
                        }
                        this.y = false;
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
        try {
            if (this.s != null) {
                graphics.drawImage(this.s, 0, 0, null);
            }
        }
        catch (Exception ex) {}
    }
    
    public void run() {
        try {
            if (this.z) {
                return;
            }
            this.b(3);
            this.i = new k();
            final URL url = new URL(this.getDocumentBase(), this.getParameter("URL"));
            final o o = new o();
            float n;
            try {
                final String parameter = this.getParameter("InitFOV");
                n = ((parameter != null) ? ((float)(int)(Object)Float.valueOf(parameter)) : -1.0f);
            }
            catch (NumberFormatException ex3) {
                n = 100.0f;
            }
            this.j = new bd(url, n);
            this.i.a(new j());
            System.out.println("Loading: " + url);
            this.j.a(this);
            this.j.a(url.openStream());
            synchronized (this.h) {
                this.j.a(this);
                this.j.a(o);
                if (this.z) {
                    return;
                }
                this.h.wait();
            }
            this.i.a(o);
            synchronized (this) {
                if (this.z) {
                    return;
                }
                this.i.b(new n(this.a()));
                this.t.setColor(Color.black);
                this.t.fillRect(0, 0, this.a().width, this.a().height);
                this.k = new bb(this);
                this.a(ba.a((float[])this.i.b().a("DefViewpoint")), true);
            }
            final String parameter2 = this.getParameter("toolbar");
            if (parameter2 != null && parameter2.equals("off")) {
                this.B = false;
            }
            this.l = new x(this.k);
            this.requestFocus();
            this.k.start();
            if (this.z) {
                return;
            }
            if (this.E) {
                this.k.a(new w(this.k));
            }
            synchronized (this.h) {
                if (this.z) {
                    return;
                }
                if (this.j != null) {
                    this.h.wait();
                }
            }
            if (this.z) {
                return;
            }
            final o o2 = (o)this.i.b();
            final ImageProducer d = o2.d();
            if (!this.e()) {
                return;
            }
            this.e.setPriority(1);
            this.k.setPriority(4);
            Thread.sleep(0L);
            final o o3 = (o)o2.e();
            final Image image = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(d, new b()));
            final Dimension dimension = new Dimension(o2.b().width * 2, o2.b().height * 2);
            final float[] array = { o2.c()[0] * 2.0f + 0.5f, o2.c()[1] * 2.0f + 0.5f };
            try {
                o3.a("Radius", new Float((float)o2.a("Radius") * 2.0f));
            }
            catch (NullPointerException ex4) {}
            new q(o3, image).a();
            o3.a(array);
            this.i.a(o3);
            this.k.setPriority(1);
            if (!this.k.c(null)) {
                this.g();
            }
        }
        catch (InterruptedException ex5) {}
        catch (MalformedURLException ex) {
            this.q = "File not found.";
            ex.printStackTrace();
        }
        catch (IOException ex2) {
            this.q = "File access error.";
            ex2.printStackTrace();
        }
        finally {
            if (this.q != null && !this.z) {
                this.repaint();
                this.b(0);
            }
            this.c();
            synchronized (this.f) {
                this.A = true;
                this.z = false;
            }
        }
    }
    
    protected boolean e() {
        return this.i.b().b().width <= 700 && !System.getProperty("java.vendor").startsWith("Netscape") && IpixViewer.G >= 1.1f && this.getParameter("UpSample") == null && IpixViewer.J == 1;
    }
    
    void a(final int n, final float x) {
        if (n == -1) {
            this.j = null;
            this.q = "Failed to load image.";
            this.repaint();
            this.b(0);
            this.z = true;
            return;
        }
        if (n == 4) {
            synchronized (this.h) {
                this.h.notify();
            }
            this.j = null;
            return;
        }
        if (n == 3) {
            this.x = x;
            this.showStatus("Downloaded " + (int)Math.ceil(this.x * 100.0f) + "% of image...");
            if (this.i.c() == null) {
                this.update(this.w);
            }
            return;
        }
        Label_0206: {
            if (this.k == null) {
                synchronized (this.h) {
                    this.h.notify();
                    break Label_0206;
                }
            }
            if (this.k.c() == null || this.k.c() instanceof w) {
                this.a(this.f(), true);
                this.g();
            }
        }
        if (n == 2) {
            this.j = null;
            synchronized (this.h) {
                this.h.notify();
            }
            this.b(13);
            this.showStatus("iPIX Java Viewer 4.0");
        }
    }
    
    float[] f() {
        return (float[])this.i.a("Viewpoint");
    }
    
    boolean a(final float[] array, final boolean b) {
        try {
            final float[] f = this.f();
            this.i.a("Viewpoint", array);
            return b || !ba.c(f, array);
        }
        catch (NullPointerException ex) {
            return false;
        }
    }
    
    synchronized void g() {
        try {
            this.i.a();
            this.update(this.w);
        }
        catch (NullPointerException ex) {}
    }
    
    protected void b(final Graphics graphics) {
        try {
            final int width = this.a().width;
            final int height = this.a().height;
            graphics.setColor(this.n);
            graphics.fillRect(0, 0, width, height);
            final Dimension dimension = new Dimension(this.m.getWidth(null), this.m.getHeight(null));
            final int n = height / 10;
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            String q = "Loading Image...";
            if (this.q != null) {
                q = this.q;
            }
            final int stringWidth = fontMetrics.stringWidth(q);
            final int n2 = (width - stringWidth) / 2;
            final int n3 = height - n - fontMetrics.getDescent() - fontMetrics.getLeading();
            final int n4 = (width <= 320) ? 2 : 1;
            final int max = Math.max(width / 2, stringWidth);
            final int n5 = IpixViewer.p.length / n4;
            final int n6 = (width - max) / 2;
            final int n7 = n3 - n5 - 15;
            final Dimension dimension2 = new Dimension(this.o.getWidth(null), this.o.getHeight(null));
            final int n8 = width;
            final int n9 = 6 * height / 10;
            final int n10 = n9 - Math.max(0, n + n9 + dimension2.height + 10 + (height - n7) - height);
            final float min = Math.min(Math.min(n8 / dimension.width, n10 / dimension.height), 1.0f);
            dimension.width *= (int)min;
            dimension.height *= (int)min;
            final int n11 = (width - dimension.width) / 2;
            final int n12 = n + (n10 - dimension.height) / 2;
            graphics.drawImage(this.m, n11, n12, dimension.width, dimension.height, this);
            if (this.o != null) {
                graphics.drawImage(this.o, (dimension2.width < dimension.width) ? (n11 + dimension.width - dimension2.width) : ((width - dimension2.width) / 2), n12 + dimension.height + 2, dimension2.width, dimension2.height, this);
            }
            graphics.setColor(Color.black);
            graphics.drawString(q, n2, n3);
            if (this.q == null) {
                graphics.setColor(new Color(-4144960));
                graphics.fillRect(n6 - 2, n7 - 1, max + 2, n5 + 2);
                graphics.setColor(Color.black);
                graphics.drawRect(n6 - 2, n7 - 1, max + 2, n5 + 2);
                for (int i = 0; i < n5; ++i) {
                    graphics.setColor(new Color(IpixViewer.p[i * n4]));
                    graphics.drawLine(n6, n7 + 1 + i, n6 + (int)(this.x * (max - 1)), n7 + 1 + i);
                }
            }
        }
        catch (Exception ex) {}
    }
    
    k h() {
        return this.i;
    }
    
    Object i() {
        return this.j;
    }
    
    void j() {
        try {
            String parameter = this.getParameter("HelpFrame");
            if (parameter == null) {
                parameter = "_blank";
            }
            String parameter2 = this.getParameter("HelpURL");
            if (parameter2 == null) {
                parameter2 = "http://www.ipix.com/help/java3_2/index.html";
            }
            final URL url = new URL(parameter2);
            if (k() != 2.0f) {
                this.getAppletContext().showDocument(url, parameter);
            }
        }
        catch (MalformedURLException ex) {}
    }
    
    void b(final int cursor) {
        if (k() > 2.0f) {
            this.u.setCursor(cursor);
        }
    }
    
    void a(final r r) {
        this.v.addElement(r);
    }
    
    void a(final r r, int n) {
        if (n < 0) {
            n = 0;
        }
        if (n >= this.v.size()) {
            n = this.v.size() - 1;
        }
        final int index = this.v.indexOf(r);
        if (index == -1 || index == n) {
            return;
        }
        this.v.removeElement(r);
        this.v.insertElementAt(r, n);
    }
    
    void b(final r r) {
        this.v.removeElement(r);
    }
    
    public boolean handleEvent(final Event event) {
        final a a = new a(event);
        for (int i = 0; i < this.v.size(); ++i) {
            ((r)this.v.elementAt(i)).a(a);
        }
        return super.handleEvent(event);
    }
    
    static final float k() {
        return IpixViewer.K;
    }
    
    public void control(final String s, final String s2) {
        try {
            if (s.equalsIgnoreCase("stop")) {
                this.k.a((r)null);
            }
            else if (s.equalsIgnoreCase("spin")) {
                this.k.a(new w(this.k));
            }
            else if (s.equalsIgnoreCase("home")) {
                this.k.a(new t(this.k));
            }
            else if (s.equalsIgnoreCase("go")) {
                final StringTokenizer stringTokenizer = new StringTokenizer(s2, ",");
                final float[] array = new float[stringTokenizer.countTokens()];
                int n = 0;
                while (stringTokenizer.hasMoreElements()) {
                    array[n] = Float.valueOf((String)stringTokenizer.nextElement()) * 0.017453292f;
                    ++n;
                }
                this.k.a(new t(this.k, array));
            }
        }
        catch (Exception ex) {}
    }
    
    static {
        p = new int[] { -1, -2500135, -2500135, -6249818, -8816263, -16777216 };
        IpixViewer.G = 0.0f;
        IpixViewer.J = 0;
        IpixViewer.K = 0.0f;
    }
}
