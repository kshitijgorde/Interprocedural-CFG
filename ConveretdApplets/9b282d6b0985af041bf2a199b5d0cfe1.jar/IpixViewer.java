import java.awt.Event;
import java.awt.Point;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Rectangle;
import java.awt.FontMetrics;
import java.awt.Container;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.ImageObserver;
import java.awt.Dimension;
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
    private static final String a = "iPIX Virtual Tour Viewer 3.22";
    private static final String b = "(C) 1986 - 2001 Internet Pictures Corporation.";
    private static final String c = "All Rights Reserved.";
    private Thread d;
    protected final Object e;
    protected final Object f;
    protected final Object g;
    protected r h;
    protected bp i;
    protected bo j;
    protected bi k;
    protected String l;
    protected Image m;
    protected static final int[] n;
    protected Image o;
    protected String p;
    y q;
    protected Image r;
    protected Graphics s;
    protected Frame t;
    protected Vector u;
    protected Graphics v;
    protected float w;
    protected boolean x;
    protected boolean y;
    protected boolean z;
    private int A;
    private int B;
    private Dimension C;
    protected static float D;
    protected static final int E = 1;
    protected static final int F = 2;
    protected static int G;
    protected static float H;
    
    public void stop() {
        synchronized (this.f) {
            if (IpixViewer.D < 1.1f && IpixViewer.G == 1) {
                // monitorenter(this)
                try {
                    if (this.d != null) {
                        this.d.stop();
                    }
                    if (this.i != null) {
                        this.i.stop();
                    }
                    if (this.k != null) {
                        this.k.a();
                        this.k = null;
                    }
                    if (this.j != null) {
                        this.j.stop();
                    }
                    this.a();
                    // monitorexit(this)
                    // monitorexit(this.f)
                    return;
                }
                finally {}
            }
            if (IpixViewer.H == 2.0f) {
                // monitorenter(this)
                try {
                    if (this.A >= this.B) {
                        // monitorexit(this)
                        // monitorexit(this.f)
                        return;
                    }
                    ++this.A;
                }
                // monitorexit(this)
                finally {}
            }
            this.y = true;
            try {
                this.b(15000);
            }
            catch (InterruptedException ex) {}
            catch (IllegalThreadStateException ex2) {}
            this.a();
        }
        // monitorexit(this.f)
    }
    
    public void a(final f f) {
        if (f.b()) {
            this.i = null;
            this.p = "Failed to load image.";
            this.repaint();
            this.a(0);
            this.y = true;
            return;
        }
        if (f.g()) {
            synchronized (this.g) {
                this.g.notify();
            }
            // monitorexit(this.g)
            this.i = null;
            return;
        }
        if (f.d()) {
            this.w = f.e();
            this.showStatus("Downloaded " + (int)Math.ceil(this.w * 100.0f) + "% of image...");
            if (this.h.b() == null) {
                this.update(this.v);
            }
            return;
        }
        Label_0231: {
            if (this.j == null) {
                synchronized (this.g) {
                    this.g.notify();
                    // monitorexit(this.g)
                    break Label_0231;
                }
            }
            if (this.j.d() == null || this.j.d() instanceof bh) {
                this.a(this.h(), true);
                this.c();
            }
        }
        if (f.f()) {
            this.i = null;
            synchronized (this.g) {
                this.g.notify();
            }
            // monitorexit(this.g)
            this.a(13);
            this.showStatus("iPIX Virtual Tour Viewer 3.22");
        }
    }
    
    protected void a(final Graphics graphics) {
        try {
            ((u)this.h.b()).a(graphics);
            try {
                if (this.k == null) {
                    graphics.drawImage(this.o, 5, this.k().height - this.o.getHeight(null) - 5, null);
                    return;
                }
                boolean b = this.k.a(graphics);
                if (!b && this.x && g() == 2.0f) {
                    for (int n = 500; !b && n <= 1000; b = this.k.a(graphics), n += 100) {
                        Thread.sleep(n);
                    }
                    this.x = false;
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
    
    void a() {
        synchronized (this) {
            if (this.k != null) {
                this.k.a();
                this.k = null;
            }
            this.r = null;
            if (this.s != null) {
                this.s.dispose();
            }
            this.s = null;
            this.h = null;
            this.q = null;
            this.l = null;
            this.m = null;
            this.o = null;
            this.p = null;
            if (this.v != null) {
                this.v.dispose();
            }
            this.v = null;
            this.i = null;
            this.j = null;
            this.d = null;
            this.m();
        }
    }
    
    Object b() {
        return this.i;
    }
    
    public synchronized void update(final Graphics graphics) {
        if (this.r == null) {
            this.r = this.createImage(this.k().width, this.k().height);
            (this.s = this.r.getGraphics()).setFont(new Font("Arial", 0, 12));
        }
        this.a(this.s);
        this.paint(graphics);
    }
    
    public void stopPan() {
        try {
            this.j.b((bb)null);
        }
        catch (NullPointerException ex) {}
    }
    
    synchronized void c() {
        try {
            this.h.a();
            this.update(this.v);
        }
        catch (NullPointerException ex) {}
    }
    
    public void start() {
        synchronized (this.f) {
            // monitorenter(this)
            try {
                try {
                    if (IpixViewer.H == 2.0f) {
                        ++this.B;
                    }
                    for (Container container = this.getParent(); container != null; container = container.getParent()) {
                        if (container instanceof Frame) {
                            this.t = (Frame)container;
                            container.setBackground(Color.white);
                            break;
                        }
                    }
                    this.show();
                    if (this.v == null) {
                        this.v = this.getGraphics();
                    }
                    this.q = new y(this.getCodeBase());
                    this.m = this.q.b(51);
                    this.o = this.q.b(2);
                    this.l = this.getParameter("Toolbar");
                    if (this.l == null) {
                        this.l = "large";
                    }
                    this.l.toLowerCase();
                    if (!this.l.equals("none") && !this.l.equals("small")) {
                        if (this.k().width < 320) {
                            this.l = "small";
                        }
                        else {
                            this.l = "large";
                        }
                    }
                    this.update(this.v);
                    if (this.d == null) {
                        this.d = new Thread(this);
                        synchronized (this.e) {
                            this.y = false;
                            this.z = false;
                        }
                        // monitorexit(this.e)
                        this.d.start();
                    }
                    System.gc();
                }
                catch (OutOfMemoryError outOfMemoryError) {
                    this.showStatus(this.p = "Error, Out Of Memory...");
                    final Graphics graphics = this.getGraphics();
                    if (graphics == null) {
                        // monitorexit(this)
                        // monitorexit(this.f)
                        return;
                    }
                    graphics.setColor(Color.white);
                    graphics.fillRect(0, 0, this.k().width, this.k().height);
                    graphics.setColor(Color.black);
                    final FontMetrics fontMetrics = graphics.getFontMetrics();
                    graphics.drawString(this.p, (this.k().width - fontMetrics.stringWidth(this.p)) / 2, this.k().height / 2 - fontMetrics.getDescent() - fontMetrics.getLeading());
                }
            }
            // monitorexit(this)
            finally {}
        }
        // monitorexit(this.f)
    }
    
    protected void d() {
        if (this.l.equals("large")) {
            this.k = new bj(this.j, new Rectangle(0, this.k().height - 40, this.k().width, 40));
            return;
        }
        if (this.l.equals("small")) {
            this.k = new bk(this.j, new Rectangle(0, this.k().height - 21, 171, 21));
        }
    }
    
    void a(final i i) {
        this.u.addElement(i);
    }
    
    private long e() {
        return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    }
    
    void a(final int cursor) {
        if (g() > 2.0f) {
            this.t.setCursor(cursor);
        }
    }
    
    void f() {
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
            if (g() != 2.0f) {
                this.getAppletContext().showDocument(url, parameter);
                return;
            }
            this.showStatus("Please visit www.ipix.com/" + parameter2 + " for more info...");
        }
        catch (MalformedURLException ex) {}
    }
    
    static final float g() {
        return IpixViewer.H;
    }
    
    void b(final int n) throws InterruptedException, IllegalThreadStateException {
        if (Thread.currentThread() == this.d) {
            throw new IllegalThreadStateException();
        }
        synchronized (this.e) {
            if (!this.z) {
                this.y = true;
            }
            try {
                this.i.a(15000);
            }
            catch (InterruptedException ex) {}
            catch (IllegalThreadStateException ex2) {}
            catch (NullPointerException ex3) {}
            if (this.j != null) {
                try {
                    this.j.a(15000);
                }
                catch (InterruptedException ex4) {}
                catch (IllegalThreadStateException ex5) {}
            }
            if (this.z || this.d == null) {
                this.y = false;
                // monitorexit(this.e)
                return;
            }
        }
        // monitorexit(this.e)
        final long currentTimeMillis = System.currentTimeMillis();
        while (this.y && System.currentTimeMillis() - currentTimeMillis < n) {
            Thread.sleep(10L);
        }
    }
    
    public synchronized void paint(final Graphics graphics) {
        try {
            if (this.r != null) {
                graphics.drawImage(this.r, 0, 0, null);
            }
        }
        catch (NullPointerException ex) {}
        catch (IllegalArgumentException ex2) {}
    }
    
    float[] h() {
        return (float[])this.h.a("Viewpoint");
    }
    
    boolean a(final float[] array, final boolean b) {
        try {
            final float[] h = this.h();
            this.h.a("Viewpoint", array);
            return b || !bn.c(h, array);
        }
        catch (NullPointerException ex) {
            return false;
        }
    }
    
    r i() {
        return this.h;
    }
    
    public IpixViewer() {
        this.d = null;
        this.e = new Object();
        this.f = new Object();
        this.g = new Object();
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = null;
        this.o = null;
        this.p = null;
        this.q = null;
        this.r = null;
        this.s = null;
        this.t = null;
        this.u = new Vector();
        this.v = null;
        this.w = 0.0f;
        this.x = true;
        this.y = false;
        this.z = false;
        this.A = 0;
        this.B = 0;
        if (System.getProperty("java.version").compareTo("1.1") >= 0) {
            IpixViewer.D = 1.1f;
        }
        final String property = System.getProperty("os.name");
        if (property.startsWith("Windows")) {
            IpixViewer.G = 1;
        }
        else if (property.startsWith("Mac")) {
            IpixViewer.G = 2;
        }
        if (!System.getProperty("java.vendor").startsWith("Apple")) {
            IpixViewer.H = 100.0f;
        }
        final String property2 = System.getProperty("java.vendor.url");
        if (property2.startsWith("http://devtools.apple.com/mrj")) {
            IpixViewer.H = 1.02f;
            return;
        }
        if (property2.startsWith("http://www.applejava.apple.com/")) {
            IpixViewer.H = 2.0f;
            return;
        }
        if (property2.startsWith("http://www.apple.com/macos/java/")) {
            IpixViewer.H = 2.1f;
            return;
        }
        IpixViewer.H = 3.0f;
    }
    
    public void destroy() {
    }
    
    protected boolean j() {
        return !(this.h.e() instanceof x) && this.h.e().c().width <= 700 && !System.getProperty("java.vendor").startsWith("Netscape") && IpixViewer.D >= 1.1f && this.getParameter("upsample") == null && IpixViewer.G == 1;
    }
    
    protected Dimension k() {
        try {
            if (this.C == null || this.C.width <= 0 || this.C.height <= 0) {
                this.C = new Dimension(Integer.parseInt(this.getParameter("Width")), Integer.parseInt(this.getParameter("Height")));
            }
        }
        catch (NumberFormatException ex) {}
        return this.C;
    }
    
    Dimension l() {
        final Dimension dimension = new Dimension(this.k().width, this.k().height);
        if (this.l.equals("large")) {
            final Dimension dimension2 = dimension;
            dimension2.height -= 40;
        }
        return dimension;
    }
    
    private void m() {
        try {
            long e = this.e();
            while (System.currentTimeMillis() - System.currentTimeMillis() < 10000L) {
                System.gc();
                Thread.sleep(10L);
                if (IpixViewer.D >= 1.1f) {
                    System.runFinalization();
                }
                final long e2 = this.e();
                if (e <= e2) {
                    return;
                }
                e = e2;
            }
        }
        catch (InterruptedException ex) {}
    }
    
    boolean a(final Point point) {
        return (this.k == null || !this.k.a(point.x, point.y)) && this.inside(point.x, point.y);
    }
    
    static {
        n = new int[] { -1, -2500135, -2500135, -6249818, -8816263, -16777216 };
        IpixViewer.D = 0.0f;
        IpixViewer.G = 0;
        IpixViewer.H = 0.0f;
    }
    
    void b(final i i) {
        this.u.removeElement(i);
    }
    
    public void run() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        IpixViewer.y:Z
        //     4: iconst_1       
        //     5: if_icmpne       12
        //     8: jsr             973
        //    11: return         
        //    12: aload_0        
        //    13: iconst_3       
        //    14: invokevirtual   IpixViewer.a:(I)V
        //    17: aload_0        
        //    18: new             Lr;
        //    21: dup            
        //    22: invokespecial   r.<init>:()V
        //    25: putfield        IpixViewer.h:Lr;
        //    28: new             Ljava/net/URL;
        //    31: dup            
        //    32: aload_0        
        //    33: invokevirtual   java/applet/Applet.getDocumentBase:()Ljava/net/URL;
        //    36: aload_0        
        //    37: ldc_w           "URL"
        //    40: invokevirtual   java/applet/Applet.getParameter:(Ljava/lang/String;)Ljava/lang/String;
        //    43: invokespecial   java/net/URL.<init>:(Ljava/net/URL;Ljava/lang/String;)V
        //    46: astore_1       
        //    47: aconst_null    
        //    48: astore_2       
        //    49: aload_1        
        //    50: invokevirtual   java/net/URL.toString:()Ljava/lang/String;
        //    53: ldc_w           ".ipx"
        //    56: invokevirtual   java/lang/String.endsWith:(Ljava/lang/String;)Z
        //    59: ifeq            139
        //    62: new             Lw;
        //    65: dup            
        //    66: invokespecial   w.<init>:()V
        //    69: astore_2       
        //    70: ldc_w           -1.0
        //    73: fstore_3       
        //    74: aload_0        
        //    75: ldc_w           "initfov"
        //    78: invokevirtual   java/applet/Applet.getParameter:(Ljava/lang/String;)Ljava/lang/String;
        //    81: astore          4
        //    83: aload           4
        //    85: ifnull          99
        //    88: aload           4
        //    90: invokestatic    java/lang/Float.valueOf:(Ljava/lang/String;)Ljava/lang/Float;
        //    93: invokevirtual   java/lang/Float.intValue:()I
        //    96: goto            100
        //    99: iconst_m1      
        //   100: i2f            
        //   101: fstore_3       
        //   102: goto            109
        //   105: pop            
        //   106: ldc             100.0
        //   108: fstore_3       
        //   109: aload_0        
        //   110: new             Lbr;
        //   113: dup            
        //   114: aload_1        
        //   115: fload_3        
        //   116: invokespecial   br.<init>:(Ljava/net/URL;F)V
        //   119: putfield        IpixViewer.i:Lbp;
        //   122: aload_0        
        //   123: getfield        IpixViewer.h:Lr;
        //   126: new             Ln;
        //   129: dup            
        //   130: invokespecial   n.<init>:()V
        //   133: invokevirtual   r.a:(Lj;)V
        //   136: goto            226
        //   139: aload_0        
        //   140: ldc_w           "Warp"
        //   143: invokevirtual   java/applet/Applet.getParameter:(Ljava/lang/String;)Ljava/lang/String;
        //   146: ifnull          192
        //   149: aload_0        
        //   150: ldc_w           "Warp"
        //   153: invokevirtual   java/applet/Applet.getParameter:(Ljava/lang/String;)Ljava/lang/String;
        //   156: ldc_w           "none"
        //   159: invokevirtual   java/lang/String.equalsIgnoreCase:(Ljava/lang/String;)Z
        //   162: iconst_1       
        //   163: if_icmpne       192
        //   166: new             Lx;
        //   169: dup            
        //   170: aload_0        
        //   171: invokespecial   x.<init>:(LIpixViewer;)V
        //   174: astore_2       
        //   175: aload_0        
        //   176: getfield        IpixViewer.h:Lr;
        //   179: new             Lq;
        //   182: dup            
        //   183: invokespecial   q.<init>:()V
        //   186: invokevirtual   r.a:(Lj;)V
        //   189: goto            214
        //   192: new             Lw;
        //   195: dup            
        //   196: invokespecial   w.<init>:()V
        //   199: astore_2       
        //   200: aload_0        
        //   201: getfield        IpixViewer.h:Lr;
        //   204: new             Lp;
        //   207: dup            
        //   208: invokespecial   p.<init>:()V
        //   211: invokevirtual   r.a:(Lj;)V
        //   214: aload_0        
        //   215: new             Lbs;
        //   218: dup            
        //   219: aload_1        
        //   220: invokespecial   bs.<init>:(Ljava/net/URL;)V
        //   223: putfield        IpixViewer.i:Lbp;
        //   226: aload_0        
        //   227: ldc_w           "Warp"
        //   230: invokevirtual   java/applet/Applet.getParameter:(Ljava/lang/String;)Ljava/lang/String;
        //   233: ifnull          264
        //   236: aload_0        
        //   237: getfield        IpixViewer.h:Lr;
        //   240: ldc_w           "Warp"
        //   243: new             Ljava/lang/Float;
        //   246: dup            
        //   247: aload_0        
        //   248: ldc_w           "Warp"
        //   251: invokevirtual   java/applet/Applet.getParameter:(Ljava/lang/String;)Ljava/lang/String;
        //   254: invokespecial   java/lang/Float.<init>:(Ljava/lang/String;)V
        //   257: invokevirtual   r.a:(Ljava/lang/String;Ljava/lang/Object;)V
        //   260: goto            264
        //   263: pop            
        //   264: getstatic       java/lang/System.out:Ljava/io/PrintStream;
        //   267: new             Ljava/lang/StringBuffer;
        //   270: dup            
        //   271: invokespecial   java/lang/StringBuffer.<init>:()V
        //   274: ldc_w           "Loading: "
        //   277: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   280: aload_1        
        //   281: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/Object;)Ljava/lang/StringBuffer;
        //   284: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //   287: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
        //   290: aload_0        
        //   291: getfield        IpixViewer.i:Lbp;
        //   294: aload_1        
        //   295: invokevirtual   java/net/URL.openStream:()Ljava/io/InputStream;
        //   298: invokevirtual   bp.a:(Ljava/io/InputStream;)V
        //   301: aload_0        
        //   302: getfield        IpixViewer.g:Ljava/lang/Object;
        //   305: dup            
        //   306: astore          10
        //   308: monitorenter   
        //   309: aload_0        
        //   310: getfield        IpixViewer.i:Lbp;
        //   313: aload_0        
        //   314: invokevirtual   bp.a:(Lg;)V
        //   317: aload_0        
        //   318: getfield        IpixViewer.i:Lbp;
        //   321: aload_2        
        //   322: invokevirtual   bp.a:(Lw;)V
        //   325: aload_0        
        //   326: getfield        IpixViewer.y:Z
        //   329: iconst_1       
        //   330: if_icmpne       340
        //   333: jsr             357
        //   336: jsr             973
        //   339: return         
        //   340: aload_0        
        //   341: getfield        IpixViewer.g:Ljava/lang/Object;
        //   344: invokevirtual   java/lang/Object.wait:()V
        //   347: jsr             357
        //   350: goto            364
        //   353: aload           10
        //   355: monitorexit    
        //   356: athrow         
        //   357: astore          11
        //   359: aload           10
        //   361: monitorexit    
        //   362: ret             11
        //   364: aload_0        
        //   365: getfield        IpixViewer.h:Lr;
        //   368: aload_2        
        //   369: invokevirtual   r.b:(Lt;)V
        //   372: aload_0        
        //   373: dup            
        //   374: astore          10
        //   376: monitorenter   
        //   377: aload_0        
        //   378: getfield        IpixViewer.y:Z
        //   381: iconst_1       
        //   382: if_icmpne       392
        //   385: jsr             540
        //   388: jsr             973
        //   391: return         
        //   392: aload_0        
        //   393: invokevirtual   IpixViewer.l:()Ljava/awt/Dimension;
        //   396: astore_3       
        //   397: aload_0        
        //   398: getfield        IpixViewer.h:Lr;
        //   401: invokevirtual   r.e:()Lt;
        //   404: instanceof      Lx;
        //   407: ifeq            441
        //   410: aload_0        
        //   411: getfield        IpixViewer.h:Lr;
        //   414: new             Lv;
        //   417: dup            
        //   418: aload_3        
        //   419: aload_0        
        //   420: getfield        IpixViewer.h:Lr;
        //   423: invokevirtual   r.e:()Lt;
        //   426: checkcast       Lx;
        //   429: invokevirtual   x.e:()Ljava/awt/Image;
        //   432: invokespecial   v.<init>:(Ljava/awt/Dimension;Ljava/awt/Image;)V
        //   435: invokevirtual   r.a:(Lt;)V
        //   438: goto            456
        //   441: aload_0        
        //   442: getfield        IpixViewer.h:Lr;
        //   445: new             Lu;
        //   448: dup            
        //   449: aload_3        
        //   450: invokespecial   u.<init>:(Ljava/awt/Dimension;)V
        //   453: invokevirtual   r.a:(Lt;)V
        //   456: aload_0        
        //   457: getfield        IpixViewer.s:Ljava/awt/Graphics;
        //   460: getstatic       java/awt/Color.black:Ljava/awt/Color;
        //   463: invokevirtual   java/awt/Graphics.setColor:(Ljava/awt/Color;)V
        //   466: aload_0        
        //   467: getfield        IpixViewer.s:Ljava/awt/Graphics;
        //   470: iconst_0       
        //   471: iconst_0       
        //   472: aload_0        
        //   473: invokevirtual   IpixViewer.k:()Ljava/awt/Dimension;
        //   476: getfield        java/awt/Dimension.width:I
        //   479: aload_0        
        //   480: invokevirtual   IpixViewer.k:()Ljava/awt/Dimension;
        //   483: getfield        java/awt/Dimension.height:I
        //   486: invokevirtual   java/awt/Graphics.fillRect:(IIII)V
        //   489: aload_0        
        //   490: new             Lbo;
        //   493: dup            
        //   494: aload_0        
        //   495: invokespecial   bo.<init>:(LIpixViewer;)V
        //   498: putfield        IpixViewer.j:Lbo;
        //   501: aload_0        
        //   502: invokevirtual   IpixViewer.d:()V
        //   505: aload_0        
        //   506: aload_0        
        //   507: getfield        IpixViewer.h:Lr;
        //   510: invokevirtual   r.e:()Lt;
        //   513: ldc_w           "DefViewpoint"
        //   516: invokevirtual   t.a:(Ljava/lang/String;)Ljava/lang/Object;
        //   519: checkcast       [F
        //   522: invokestatic    bn.a:([F)[F
        //   525: iconst_1       
        //   526: invokevirtual   IpixViewer.a:([FZ)Z
        //   529: pop            
        //   530: jsr             540
        //   533: goto            547
        //   536: aload           10
        //   538: monitorexit    
        //   539: athrow         
        //   540: astore          11
        //   542: aload           10
        //   544: monitorexit    
        //   545: ret             11
        //   547: aload_0        
        //   548: invokevirtual   java/awt/Component.requestFocus:()V
        //   551: aload_0        
        //   552: getfield        IpixViewer.j:Lbo;
        //   555: invokevirtual   java/lang/Thread.start:()V
        //   558: aload_0        
        //   559: getfield        IpixViewer.y:Z
        //   562: iconst_1       
        //   563: if_icmpne       570
        //   566: jsr             973
        //   569: return         
        //   570: aload_0        
        //   571: ldc_w           "SpinSpeed"
        //   574: invokevirtual   java/applet/Applet.getParameter:(Ljava/lang/String;)Ljava/lang/String;
        //   577: ifnonnull       590
        //   580: aload_0        
        //   581: ldc_w           "SpinStyle"
        //   584: invokevirtual   java/applet/Applet.getParameter:(Ljava/lang/String;)Ljava/lang/String;
        //   587: ifnull          609
        //   590: aload_0        
        //   591: getfield        IpixViewer.j:Lbo;
        //   594: new             Lbh;
        //   597: dup            
        //   598: aload_0        
        //   599: getfield        IpixViewer.j:Lbo;
        //   602: invokespecial   bh.<init>:(Lbo;)V
        //   605: invokevirtual   bo.b:(Lbb;)Z
        //   608: pop            
        //   609: aload_0        
        //   610: getfield        IpixViewer.g:Ljava/lang/Object;
        //   613: dup            
        //   614: astore          10
        //   616: monitorenter   
        //   617: aload_0        
        //   618: getfield        IpixViewer.y:Z
        //   621: iconst_1       
        //   622: if_icmpne       632
        //   625: jsr             656
        //   628: jsr             973
        //   631: return         
        //   632: aload_0        
        //   633: getfield        IpixViewer.i:Lbp;
        //   636: ifnull          646
        //   639: aload_0        
        //   640: getfield        IpixViewer.g:Ljava/lang/Object;
        //   643: invokevirtual   java/lang/Object.wait:()V
        //   646: jsr             656
        //   649: goto            663
        //   652: aload           10
        //   654: monitorexit    
        //   655: athrow         
        //   656: astore          11
        //   658: aload           10
        //   660: monitorexit    
        //   661: ret             11
        //   663: aload_0        
        //   664: getfield        IpixViewer.y:Z
        //   667: iconst_1       
        //   668: if_icmpne       675
        //   671: jsr             973
        //   674: return         
        //   675: aload_0        
        //   676: invokevirtual   IpixViewer.n:()V
        //   679: aload_0        
        //   680: getfield        IpixViewer.h:Lr;
        //   683: invokevirtual   r.e:()Lt;
        //   686: checkcast       Lw;
        //   689: astore_3       
        //   690: aload_3        
        //   691: invokevirtual   w.d:()Ljava/awt/image/ImageProducer;
        //   694: astore          4
        //   696: aload_0        
        //   697: invokevirtual   IpixViewer.j:()Z
        //   700: ifne            707
        //   703: jsr             973
        //   706: return         
        //   707: aload_0        
        //   708: getfield        IpixViewer.d:Ljava/lang/Thread;
        //   711: iconst_1       
        //   712: invokevirtual   java/lang/Thread.setPriority:(I)V
        //   715: aload_0        
        //   716: getfield        IpixViewer.j:Lbo;
        //   719: iconst_4       
        //   720: invokevirtual   java/lang/Thread.setPriority:(I)V
        //   723: lconst_0       
        //   724: invokestatic    java/lang/Thread.sleep:(J)V
        //   727: aload_3        
        //   728: invokevirtual   w.clone:()Ljava/lang/Object;
        //   731: checkcast       Lw;
        //   734: astore          5
        //   736: new             Lb;
        //   739: dup            
        //   740: iconst_2       
        //   741: invokespecial   b.<init>:(I)V
        //   744: astore          6
        //   746: invokestatic    java/awt/Toolkit.getDefaultToolkit:()Ljava/awt/Toolkit;
        //   749: new             Ljava/awt/image/FilteredImageSource;
        //   752: dup            
        //   753: aload           4
        //   755: aload           6
        //   757: invokespecial   java/awt/image/FilteredImageSource.<init>:(Ljava/awt/image/ImageProducer;Ljava/awt/image/ImageFilter;)V
        //   760: invokevirtual   java/awt/Toolkit.createImage:(Ljava/awt/image/ImageProducer;)Ljava/awt/Image;
        //   763: astore          7
        //   765: new             Ljava/awt/Dimension;
        //   768: aload_3        
        //   769: invokevirtual   t.c:()Ljava/awt/Dimension;
        //   772: getfield        java/awt/Dimension.width:I
        //   775: iconst_2       
        //   776: imul           
        //   777: aload_3        
        //   778: invokevirtual   t.c:()Ljava/awt/Dimension;
        //   781: getfield        java/awt/Dimension.height:I
        //   784: iconst_2       
        //   785: imul           
        //   786: invokespecial   java/awt/Dimension.<init>:(II)V
        //   789: iconst_2       
        //   790: newarray        F
        //   792: dup            
        //   793: iconst_0       
        //   794: aload_3        
        //   795: invokevirtual   t.b:()[F
        //   798: iconst_0       
        //   799: faload         
        //   800: fconst_2       
        //   801: fmul           
        //   802: ldc_w           0.5
        //   805: fadd           
        //   806: fastore        
        //   807: dup            
        //   808: iconst_1       
        //   809: aload_3        
        //   810: invokevirtual   t.b:()[F
        //   813: iconst_1       
        //   814: faload         
        //   815: fconst_2       
        //   816: fmul           
        //   817: ldc_w           0.5
        //   820: fadd           
        //   821: fastore        
        //   822: astore          8
        //   824: aload           5
        //   826: ldc_w           "Radius"
        //   829: new             Ljava/lang/Float;
        //   832: dup            
        //   833: aload_3        
        //   834: ldc_w           "Radius"
        //   837: invokevirtual   t.a:(Ljava/lang/String;)Ljava/lang/Object;
        //   840: checkcast       Ljava/lang/Float;
        //   843: invokevirtual   java/lang/Float.floatValue:()F
        //   846: fconst_2       
        //   847: fmul           
        //   848: invokespecial   java/lang/Float.<init>:(F)V
        //   851: invokevirtual   t.a:(Ljava/lang/String;Ljava/lang/Object;)V
        //   854: goto            858
        //   857: pop            
        //   858: new             Lba;
        //   861: dup            
        //   862: aload           5
        //   864: aload           7
        //   866: invokespecial   ba.<init>:(Lw;Ljava/awt/Image;)V
        //   869: astore          9
        //   871: aload           9
        //   873: invokevirtual   ba.a:()Z
        //   876: pop            
        //   877: aload           5
        //   879: aload           8
        //   881: invokevirtual   t.a:([F)V
        //   884: aload_0        
        //   885: getfield        IpixViewer.h:Lr;
        //   888: aload           5
        //   890: invokevirtual   r.b:(Lt;)V
        //   893: aload_0        
        //   894: getfield        IpixViewer.j:Lbo;
        //   897: iconst_1       
        //   898: invokevirtual   java/lang/Thread.setPriority:(I)V
        //   901: aload_0        
        //   902: getfield        IpixViewer.j:Lbo;
        //   905: aconst_null    
        //   906: invokevirtual   bo.a:(Lbb;)Z
        //   909: ifne            961
        //   912: aload_0        
        //   913: invokevirtual   IpixViewer.c:()V
        //   916: goto            961
        //   919: astore_1       
        //   920: aload_0        
        //   921: ldc_w           "Interrupted."
        //   924: putfield        IpixViewer.p:Ljava/lang/String;
        //   927: aload_1        
        //   928: invokevirtual   java/lang/Throwable.printStackTrace:()V
        //   931: goto            961
        //   934: astore_1       
        //   935: aload_0        
        //   936: ldc_w           "File not found."
        //   939: putfield        IpixViewer.p:Ljava/lang/String;
        //   942: aload_1        
        //   943: invokevirtual   java/lang/Throwable.printStackTrace:()V
        //   946: goto            961
        //   949: astore_1       
        //   950: aload_0        
        //   951: ldc_w           "File access error."
        //   954: putfield        IpixViewer.p:Ljava/lang/String;
        //   957: aload_1        
        //   958: invokevirtual   java/lang/Throwable.printStackTrace:()V
        //   961: jsr             973
        //   964: return         
        //   965: astore          10
        //   967: jsr             973
        //   970: aload           10
        //   972: athrow         
        //   973: astore          11
        //   975: aload_0        
        //   976: getfield        IpixViewer.p:Ljava/lang/String;
        //   979: ifnull          999
        //   982: aload_0        
        //   983: getfield        IpixViewer.y:Z
        //   986: iconst_1       
        //   987: if_icmpeq       999
        //   990: aload_0        
        //   991: invokevirtual   java/awt/Component.repaint:()V
        //   994: aload_0        
        //   995: iconst_0       
        //   996: invokevirtual   IpixViewer.a:(I)V
        //   999: aload_0        
        //  1000: invokespecial   IpixViewer.m:()V
        //  1003: aload_0        
        //  1004: getfield        IpixViewer.e:Ljava/lang/Object;
        //  1007: dup            
        //  1008: astore          12
        //  1010: monitorenter   
        //  1011: aload_0        
        //  1012: iconst_1       
        //  1013: putfield        IpixViewer.z:Z
        //  1016: aload_0        
        //  1017: iconst_0       
        //  1018: putfield        IpixViewer.y:Z
        //  1021: jsr             1031
        //  1024: goto            1038
        //  1027: aload           12
        //  1029: monitorexit    
        //  1030: athrow         
        //  1031: astore          13
        //  1033: aload           12
        //  1035: monitorexit    
        //  1036: ret             13
        //  1038: ret             11
        //  1040: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  74     102    105    109    Ljava/lang/NumberFormatException;
        //  226    260    263    264    Ljava/lang/NumberFormatException;
        //  309    347    353    357    Any
        //  377    530    536    540    Any
        //  617    646    652    656    Any
        //  824    854    857    858    Ljava/lang/NullPointerException;
        //  0      916    919    934    Ljava/lang/InterruptedException;
        //  0      916    934    949    Ljava/net/MalformedURLException;
        //  0      916    949    961    Ljava/io/IOException;
        //  0      961    965    973    Any
        //  1011   1021   1027   1031   Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:657)
        //     at java.util.ArrayList.get(ArrayList.java:433)
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:3035)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2445)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public void init() {
        System.out.println("iPIX Virtual Tour Viewer 3.22");
        System.out.println("(C) 1986 - 2001 Internet Pictures Corporation.");
        System.out.println("All Rights Reserved.");
    }
    
    protected void n() {
        final String parameter = this.getParameter("perfurl");
        if (parameter == null) {
            return;
        }
        try {
            new URL(parameter).openConnection().getInputStream();
        }
        catch (Exception ex) {}
    }
    
    protected void b(final Graphics graphics) {
        try {
            final int width = this.k().width;
            final int height = this.k().height;
            graphics.setColor(Color.white);
            graphics.fillRect(0, 0, width, height);
            final Dimension dimension = new Dimension(this.m.getWidth(null), this.m.getHeight(null));
            final int n = (width / 2 < dimension.width || height / 2 < dimension.height) ? 2 : 1;
            final int n2 = height / n / 8;
            graphics.drawImage(this.m, (width - dimension.width / n) / 2, -n2 + (height - dimension.height / n) / 2, dimension.width / n, dimension.height / n, this);
            graphics.setColor(Color.black);
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            if (this.p == null) {
                final int stringWidth = fontMetrics.stringWidth("iPIX Virtual Tour Viewer 3.22");
                graphics.drawString("iPIX Virtual Tour Viewer 3.22", (width - stringWidth) / 2, -n2 + height - fontMetrics.getDescent() - fontMetrics.getLeading());
                final int max = Math.max(width / 2, stringWidth);
                final int n3 = IpixViewer.n.length / n;
                final int n4 = (width - max) / 2;
                final int n5 = -n2 + height - fontMetrics.getHeight() - 15 / n;
                graphics.setColor(new Color(-4144960));
                graphics.fillRect(n4 - 2, n5 - 1, max + 2, n3 + 2);
                graphics.setColor(Color.black);
                graphics.drawRect(n4 - 2, n5 - 1, max + 2, n3 + 2);
                for (int i = 0; i < IpixViewer.n.length / n; ++i) {
                    graphics.setColor(new Color(IpixViewer.n[i * n]));
                    graphics.drawLine(n4, n5 + 1 + i, n4 + (int)(this.w * (max - 1)), n5 + 1 + i);
                }
                return;
            }
            graphics.drawString(this.p, (width - fontMetrics.stringWidth(this.p)) / 2, -n2 + height - fontMetrics.getDescent() - fontMetrics.getLeading());
        }
        catch (NullPointerException ex) {}
        catch (IllegalArgumentException ex2) {}
    }
    
    public boolean handleEvent(final Event event) {
        final a a = new a(event);
        for (int i = 0; i < this.u.size(); ++i) {
            ((i)this.u.elementAt(i)).a(a);
        }
        return super.handleEvent(event);
    }
}
