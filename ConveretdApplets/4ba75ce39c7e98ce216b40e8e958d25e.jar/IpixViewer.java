import java.awt.Event;
import java.awt.FontMetrics;
import java.awt.Container;
import java.io.IOException;
import java.net.MalformedURLException;
import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.Toolkit;
import java.awt.Color;
import java.net.URL;
import java.awt.Rectangle;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.util.Vector;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class IpixViewer extends Applet implements Runnable, g
{
    private static final String a = "iPIX Virtual Tour Viewer 3.2";
    private static final String b = "(C) 1986 - 2000 Internet Pictures Corporation.";
    private static final String c = "All Rights Reserved.";
    private Thread d;
    protected r e;
    protected bo f;
    protected bn g;
    protected bh h;
    protected String i;
    protected Image j;
    protected int[] k;
    protected Image l;
    protected String m;
    y n;
    protected Image o;
    protected Graphics p;
    protected Frame q;
    protected Vector r;
    protected Graphics s;
    protected float t;
    protected boolean u;
    protected static float v;
    
    public synchronized void stop() {
        if (this.d != null) {
            this.d.stop();
            this.d = null;
        }
        if (this.f != null) {
            this.f.stop();
            this.f = null;
        }
        if (this.h != null) {
            this.h.a();
            this.h = null;
        }
        if (this.g != null) {
            this.g.stop();
            this.g = null;
        }
        this.p = null;
        this.o = null;
        this.e = null;
        this.m = null;
        System.gc();
        System.gc();
    }
    
    public void a(final f f) {
        if (f.b()) {
            this.f = null;
            this.m = "Failed to load image.";
            this.repaint();
            this.d.stop();
            this.d = null;
            return;
        }
        if (f.d()) {
            this.t = f.e();
            this.showStatus("Downloaded " + (int)Math.ceil(this.t * 100.0f) + "% of image...");
            if (this.e.b() == null) {
                this.update(this.s);
            }
            return;
        }
        Label_0188: {
            if (this.g == null) {
                synchronized (this.e) {
                    this.e.notify();
                    // monitorexit(this.e)
                    break Label_0188;
                }
            }
            if (this.g.d() == null || this.g.d() instanceof bg) {
                this.a(this.a(), true);
                this.f();
            }
        }
        if (f.f()) {
            this.f = null;
            synchronized (this.e) {
                this.e.notify();
            }
            // monitorexit(this.e)
            this.showStatus("iPIX Virtual Tour Viewer 3.2");
        }
    }
    
    public synchronized void paint(final Graphics graphics) {
        try {
            graphics.drawImage(this.o, 0, 0, null);
        }
        catch (NullPointerException ex) {}
        catch (IllegalArgumentException ex2) {}
    }
    
    protected void a(final Graphics graphics) {
        try {
            ((u)this.e.b()).a(graphics);
            try {
                if (this.h == null) {
                    graphics.drawImage(this.l, 5, this.size().height - this.l.getHeight(null) - 5, null);
                    return;
                }
                boolean b = this.h.a(graphics);
                if (!b && this.u && h() == 2.0f) {
                    int n = 500;
                    Thread.sleep(n);
                    while (!b) {
                        Thread.sleep(n);
                        b = this.h.a(graphics);
                        n += 100;
                        if (n <= 1000) {
                            continue;
                        }
                        break;
                    }
                    this.u = false;
                }
            }
            catch (NullPointerException ex) {}
            catch (IllegalArgumentException ex2) {}
            catch (InterruptedException ex3) {}
        }
        catch (NullPointerException ex4) {
            this.b(graphics);
        }
    }
    
    float[] a() {
        return bm.a((float[])this.e.a("Viewpoint"));
    }
    
    boolean a(final float[] array, final boolean b) {
        try {
            final float[] a = this.a();
            this.e.a("Viewpoint", array);
            return b || !bm.c(a, array);
        }
        catch (NullPointerException ex) {
            return false;
        }
    }
    
    r b() {
        return this.e;
    }
    
    public IpixViewer() {
        this.k = new int[] { -1, -2500135, -2500135, -6249818, -8816263, -16777216 };
        this.r = new Vector();
        this.t = 0.0f;
        this.u = true;
        if (!System.getProperty("java.vendor").startsWith("Apple")) {
            IpixViewer.v = 100.0f;
        }
        final String property = System.getProperty("java.vendor.url");
        if (property.startsWith("http://devtools.apple.com/mrj")) {
            IpixViewer.v = 1.02f;
            return;
        }
        if (property.startsWith("http://www.applejava.apple.com/")) {
            IpixViewer.v = 2.0f;
            return;
        }
        if (property.startsWith("http://www.apple.com/macos/java/")) {
            IpixViewer.v = 2.1f;
            return;
        }
        IpixViewer.v = 3.0f;
    }
    
    public void destroy() {
        this.n = null;
        this.i = null;
        this.j = null;
        this.l = null;
        this.m = null;
        System.gc();
        System.gc();
    }
    
    protected boolean c() {
        return !(this.e.e() instanceof x) && this.e.e().c().width <= 700 && !System.getProperty("java.vendor").startsWith("Netscape") && System.getProperty("java.version").compareTo("1.1") >= 0 && System.getProperty("os.name").startsWith("Windows");
    }
    
    Object d() {
        return this.f;
    }
    
    Dimension e() {
        final Dimension size = this.size();
        if (this.i.equals("large")) {
            final Dimension dimension = size;
            dimension.height -= 40;
        }
        return size;
    }
    
    boolean a(final Point point) {
        return (this.h == null || !this.h.a(point.x, point.y)) && this.inside(point.x, point.y);
    }
    
    public synchronized void update(final Graphics graphics) {
        if (this.o == null) {
            this.o = this.createImage(this.size().width, this.size().height);
            this.p = this.o.getGraphics();
        }
        this.a(this.p);
        this.paint(graphics);
    }
    
    public void stopPan() {
        try {
            this.g.a((ba)null);
        }
        catch (NullPointerException ex) {}
    }
    
    synchronized void f() {
        try {
            this.e.a();
            this.update(this.s);
        }
        catch (NullPointerException ex) {}
    }
    
    static {
        IpixViewer.v = 0.0f;
    }
    
    public synchronized void start() {
        this.update(this.s);
        if (this.d == null) {
            (this.d = new Thread(this)).start();
        }
    }
    
    void a(final i i) {
        this.r.removeElement(i);
    }
    
    protected void g() {
        if (this.i.equals("large")) {
            this.h = new bi(this.g, new Rectangle(0, this.size().height - 40, this.size().width, 40));
            return;
        }
        if (this.i.equals("small")) {
            this.h = new bj(this.g, new Rectangle(0, this.size().height - 21, 171, 21));
        }
    }
    
    void b(final i i) {
        this.r.addElement(i);
    }
    
    public void run() {
        try {
            this.e = new r();
            final URL url = new URL(this.getDocumentBase(), this.getParameter("URL"));
            w w;
            if (url.toString().endsWith(".ipx")) {
                w = new w();
                this.f = new bq(url);
                this.e.a(new n());
            }
            else {
                if (this.getParameter("Warp") != null && this.getParameter("Warp").equalsIgnoreCase("none")) {
                    w = new x(this);
                    this.e.a(new q());
                }
                else {
                    w = new w();
                    this.e.a(new p());
                }
                this.f = new br(url);
            }
            try {
                if (this.getParameter("Warp") != null) {
                    float floatValue = Float.valueOf(this.getParameter("Warp"));
                    if (floatValue < 1.0f) {
                        floatValue = 1.0f;
                    }
                    this.e.a("Warp", new Float(floatValue));
                }
            }
            catch (NumberFormatException ex3) {}
            System.out.println("Loading: " + url);
            this.f.a(url.openStream());
            this.f.a(this);
            this.f.a(w);
            synchronized (this.e) {
                this.e.wait();
            }
            // monitorexit(this.e)
            this.e.b(w);
            synchronized (this) {
                final Dimension e = this.e();
                if (this.e.e() instanceof x) {
                    this.e.a(new v(e, ((x)this.e.e()).e()));
                }
                else {
                    this.e.a(new u(e));
                }
                this.p.setColor(Color.black);
                this.p.fillRect(0, 0, this.size().width, this.size().height);
                this.g = new bn(this);
                this.g();
                this.a(bm.a((float[])this.e.e().a("DefViewpoint")), true);
            }
            this.requestFocus();
            this.g.start();
            if (this.getParameter("SpinSpeed") != null || this.getParameter("SpinStyle") != null) {
                this.g.a(new bg(this.g));
            }
            synchronized (this.e) {
                if (this.f != null) {
                    this.e.wait();
                }
            }
            // monitorexit(this.e)
            final w w2 = (w)this.e.e();
            final ImageProducer d = w2.d();
            if (!this.c()) {
                return;
            }
            this.d.setPriority(1);
            this.g.setPriority(4);
            Thread.sleep(0L);
            final w w3 = (w)w2.clone();
            final Image image = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(d, new b(2)));
            final Dimension dimension = new Dimension(w2.c().width * 2, w2.c().height * 2);
            final float[] array = { w2.b()[0] * 2.0f + 0.5f, w2.b()[1] * 2.0f + 0.5f };
            try {
                w3.a("Radius", new Float((float)w2.a("Radius") * 2.0f));
            }
            catch (NullPointerException ex4) {}
            w3.a(image, dimension, array);
            this.e.b(w3);
            this.g.setPriority(1);
            if (!this.g.c(null)) {
                this.f();
            }
        }
        catch (InterruptedException ex5) {}
        catch (MalformedURLException ex) {
            this.m = "File not found.";
            ex.printStackTrace();
        }
        catch (IOException ex2) {
            this.m = "File access error.";
            ex2.printStackTrace();
        }
        finally {
            this.d = null;
            if (this.m != null) {
                this.repaint();
            }
        }
    }
    
    public void init() {
        if (this.s == null) {
            this.s = this.getGraphics();
        }
        System.out.println("iPIX Virtual Tour Viewer 3.2");
        System.out.println("(C) 1986 - 2000 Internet Pictures Corporation.");
        System.out.println("All Rights Reserved.");
        for (Container container = this.getParent(); container != null; container = container.getParent()) {
            if (container instanceof Frame) {
                this.q = (Frame)container;
                break;
            }
        }
        this.n = new y(this.getCodeBase());
        this.j = this.n.b(51);
        this.update(this.s);
        this.l = this.n.b(2);
        this.i = this.getParameter("Toolbar");
        if (this.i == null) {
            this.i = "large";
        }
        this.i.toLowerCase();
        if (this.i.equals("none")) {
            return;
        }
        if (this.i.equals("small")) {
            return;
        }
        if (this.size().width < 320) {
            this.i = "small";
            return;
        }
        this.i = "large";
    }
    
    protected void b(final Graphics graphics) {
        try {
            graphics.setColor(Color.white);
            graphics.fillRect(0, 0, this.size().width, this.size().height);
            final Dimension dimension = new Dimension(this.j.getWidth(null), this.j.getHeight(null));
            final int n = (this.size().width / 2 < dimension.width || this.size().height / 2 < dimension.height) ? 2 : 1;
            final int n2 = this.size().height / n / 8;
            graphics.drawImage(this.j, (this.size().width - dimension.width / n) / 2, -n2 + (this.size().height - dimension.height / n) / 2, dimension.width / n, dimension.height / n, this);
            graphics.setColor(Color.black);
            final Graphics graphics2 = this.getGraphics();
            graphics.setFont(graphics2.getFont());
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            graphics2.dispose();
            if (this.m == null) {
                final int stringWidth = fontMetrics.stringWidth("iPIX Virtual Tour Viewer 3.2");
                graphics.drawString("iPIX Virtual Tour Viewer 3.2", (this.size().width - stringWidth) / 2, -n2 + this.size().height - fontMetrics.getDescent() - fontMetrics.getLeading());
                final int max = Math.max(this.size().width / 2, stringWidth);
                final int n3 = this.k.length / n;
                final int n4 = (this.size().width - max) / 2;
                final int n5 = -n2 + this.size().height - fontMetrics.getHeight() - 15 / n;
                graphics.setColor(new Color(-4144960));
                graphics.fillRect(n4 - 2, n5 - 1, max + 2, n3 + 2);
                graphics.setColor(Color.black);
                graphics.drawRect(n4 - 2, n5 - 1, max + 2, n3 + 2);
                for (int i = 0; i < this.k.length / n; ++i) {
                    graphics.setColor(new Color(this.k[i * n]));
                    graphics.drawLine(n4, n5 + 1 + i, n4 + (int)(this.t * (max - 1)), n5 + 1 + i);
                }
                return;
            }
            graphics.drawString(this.m, (this.size().width - fontMetrics.stringWidth(this.m)) / 2, -n2 + this.size().height - fontMetrics.getDescent() - fontMetrics.getLeading());
        }
        catch (NullPointerException ex) {}
        catch (IllegalArgumentException ex2) {}
    }
    
    public boolean handleEvent(final Event event) {
        final a a = new a(event);
        for (int i = 0; i < this.r.size(); ++i) {
            ((i)this.r.elementAt(i)).a(a);
        }
        return super.handleEvent(event);
    }
    
    static final float h() {
        return IpixViewer.v;
    }
    
    void a(final int cursor) {
        if (h() > 2.0f) {
            this.q.setCursor(cursor);
        }
    }
    
    void i() {
        try {
            String parameter = this.getParameter("HelpFrame");
            if (parameter == null) {
                parameter = "_blank";
            }
            String parameter2 = this.getParameter("HelpURL");
            if (parameter2 == null) {
                parameter2 = "index.html";
            }
            final URL url = new URL("http://www.ipix.com/" + parameter2);
            if (h() != 2.0f) {
                this.getAppletContext().showDocument(url, parameter);
                return;
            }
            this.showStatus("Please visit www.ipix.com/" + parameter2 + " for more info...");
        }
        catch (MalformedURLException ex) {}
    }
}
