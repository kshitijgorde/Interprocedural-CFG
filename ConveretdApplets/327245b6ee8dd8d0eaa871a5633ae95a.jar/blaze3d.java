import java.awt.event.ActionEvent;
import netscape.javascript.JSObject;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.io.ObjectInputStream;
import java.util.StringTokenizer;
import java.awt.image.ImageObserver;
import java.awt.Rectangle;
import java.awt.image.ImageProducer;
import java.awt.image.ColorModel;
import java.awt.Component;
import java.lang.reflect.Method;
import java.awt.MenuComponent;
import java.util.Enumeration;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.Cursor;
import java.util.Hashtable;
import java.net.URL;
import java.util.Vector;
import java.awt.Graphics;
import java.awt.image.MemoryImageSource;
import java.awt.Image;
import java.awt.image.DirectColorModel;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public final class blaze3d extends Applet implements Runnable, ActionListener, MouseListener, MouseMotionListener, KeyListener, FocusListener
{
    static final String a;
    static o b;
    static int c;
    static boolean d;
    static boolean e;
    static boolean f;
    long g;
    long h;
    long i;
    long j;
    Thread k;
    int l;
    int m;
    DirectColorModel n;
    Image p;
    MemoryImageSource q;
    Graphics r;
    l s;
    int t;
    int u;
    boolean v;
    a4 w;
    String x;
    String y;
    int z;
    a3 aa;
    int ab;
    int ac;
    int[] ad;
    private int ae;
    private boolean af;
    private long ag;
    int ah;
    boolean ai;
    boolean aj;
    aa ak;
    t al;
    ac am;
    y an;
    b[] ao;
    boolean ap;
    z aq;
    z ar;
    ab as;
    Vector at;
    Vector au;
    Vector av;
    Vector aw;
    Object ax;
    bl[] ay;
    aj az;
    long a0;
    URL a1;
    au[] a2;
    int a3;
    boolean a4;
    String a5;
    private int a6;
    int a7;
    int[] a8;
    int a9;
    boolean ba;
    static Hashtable bb;
    Cursor bc;
    boolean bd;
    private PopupMenu be;
    private MenuItem bf;
    private MenuItem bg;
    private MenuItem bh;
    private MenuItem bi;
    
    public blaze3d() {
        this.g = 0L;
        this.h = 0L;
        this.i = 0L;
        this.j = 0L;
        this.x = "";
        this.y = "";
        this.ad = new int[2];
        this.ah = 0;
        this.ai = false;
        this.aj = false;
        this.at = new Vector();
        this.au = new Vector();
        this.av = new Vector();
        this.aw = new Vector();
        this.ax = new Object();
        this.ay = new bl[100];
        this.a2 = new au[256];
        this.a3 = 0;
        this.a4 = false;
        this.a6 = 1;
        this.a7 = 0;
        this.a8 = null;
        this.a9 = -1;
        this.ba = false;
        this.bc = null;
        this.bd = false;
    }
    
    static blaze3d a() {
        return blaze3d.bb.get(Thread.currentThread());
    }
    
    public synchronized void init() {
        this.addKeyListener(this);
        this.addFocusListener(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.m();
        try {
            new URL(this.getDocumentBase(), "").openConnection().setDefaultUseCaches(true);
        }
        catch (Exception ex) {}
    }
    
    public synchronized void start() {
        this.ai = false;
        System.out.println("Holomatix Blaze Player V1.5.340 " + blaze3d.a + " (C) 2001-5");
        System.out.println("http://www.holomatix.com");
        if (this.k == null) {
            this.k = new Thread(this);
            blaze3d.bb.put(this.k, this);
            this.k.start();
        }
    }
    
    public synchronized void stop() {
        if (this.k != null && this.k.isAlive()) {
            this.k.stop();
        }
        try {
            if (this.az != null) {
                this.az.stop();
            }
            this.az = null;
            this.aa = null;
            this.w = null;
            if (this.ao != null) {
                if (this.ao[0] != null) {
                    this.ao[0].a();
                }
                this.ao[0] = null;
            }
            this.ao = null;
            if (this.an != null) {
                this.an.b();
            }
            this.an = null;
            this.s = null;
            t q;
            for (t al = this.al; al != null; al = q) {
                q = al.q;
                al.d();
            }
            this.al = null;
            l.i.removeAllElements();
            for (int i = 0; i < 256; ++i) {
                this.a2[i] = null;
            }
            for (int j = 0; j < 100; ++j) {
                this.ay[j] = null;
            }
            if (this.ak != null) {
                this.ak.a();
            }
            this.ak = null;
            this.as = null;
            this.q = null;
            this.p = null;
            this.r = null;
            final Enumeration<Thread> keys = (Enumeration<Thread>)blaze3d.bb.keys();
            while (keys.hasMoreElements()) {
                final Thread thread = keys.nextElement();
                if (blaze3d.bb.get(thread) == this) {
                    blaze3d.bb.remove(thread);
                }
            }
        }
        catch (Throwable t) {}
        this.k = null;
        System.gc();
    }
    
    public synchronized void destroy() {
        if (!System.getProperty("java.version").startsWith("1.1")) {
            try {
                final Method method = this.getClass().getMethod("fixSunBug", (Class<?>[])null);
                if (method != null) {
                    method.invoke(this, (Object[])null);
                }
            }
            catch (Exception ex) {}
        }
        this.removeKeyListener(this);
        this.removeFocusListener(this);
        this.removeMouseListener(this);
        this.removeMouseMotionListener(this);
        if (this.be != null) {
            this.remove(this.be);
        }
        super.destroy();
    }
    
    public void fixSunBug() {
        if (this.getInputContext() != null) {
            this.getInputContext().removeNotify(this);
        }
    }
    
    public void run() {
        this.showStatus("Holomatix Blaze 3D started");
        this.as = new ab(this);
        final Thread currentThread = Thread.currentThread();
        if (System.getProperty("os.name").startsWith("Mac OS")) {
            blaze3d.e = true;
        }
        blaze3d.d = System.getProperty("java.version").startsWith("1.1");
        blaze3d.f = (blaze3d.d || blaze3d.e);
        while ((this.size().width == 0 || this.size().height == 0) && currentThread == this.k) {
            try {
                Thread.sleep(50L);
            }
            catch (Exception ex) {}
        }
        this.am = new ac();
        this.an = new y(this, null, (b)null, 0, null, 0);
        this.n = new DirectColorModel(32, 16711680, 65280, 255);
        this.c();
        this.aj = true;
        this.a5 = this.getParameter("bgcolor");
        if (this.a5 != null) {
            int n;
            if (this.a5.startsWith("#")) {
                n = Integer.valueOf(this.a5.substring(1, this.a5.length()), 16);
            }
            else {
                n = Integer.valueOf(this.a5);
            }
            this.an.b(n | 0xFF000000);
        }
        String s = this.getParameter("src");
        if (s == null) {
            s = this.getParameter("movie");
        }
        try {
            this.a1 = new URL(this.getCodeBase(), s);
        }
        catch (Exception ex2) {
            try {
                this.a1 = new URL(this.getDocumentBase(), s);
            }
            catch (Exception ex3) {
                System.out.println("File not found: " + s);
                return;
            }
        }
        this.az = new aj();
        blaze3d.bb.put(this.az, this);
        this.az.setPriority(6);
        final String substring = s.substring(s.lastIndexOf(47) + 1, s.length());
        this.l();
        this.az.start();
        this.a(substring, true);
        this.a0 = System.currentTimeMillis();
        try {
            this.r = this.getGraphics();
            this.k();
            this.al.k.a();
            this.a(this.al.f, this.a6);
            currentThread.setPriority(3);
            this.ak = new aa(this);
            this.aa = new a3(this);
            this.w = new a4();
            this.ak.b();
            this.ao = new b[64];
            final b b = new b();
            b.a(this.al);
            b.a(null, "_level0", null, 0, 0);
            this.a(b);
            b.b.c = this.an;
            this.an.a(b.b);
            this.ao[0] = b;
            do {
                final long n2 = System.currentTimeMillis() + this.al.g;
                if (p.ab) {
                    this.g = this.h;
                    this.h = System.currentTimeMillis();
                    this.i += this.h - this.g;
                    ++this.j;
                    if (this.j < 0L || this.j > 100L) {
                        this.i /= 100L;
                        System.out.println(this.i);
                        this.i = 0L;
                        this.j = 0L;
                    }
                }
                if (this.aj) {
                    this.c();
                    this.a(this.al.f, this.a6);
                    this.an.f = new e(0, 0, this.l * 4, this.m * 4);
                    this.an.k();
                    this.aj = false;
                }
                this.ao[0].c();
                this.ao[0].e();
                this.g();
                this.h();
                this.b();
                int n3 = 0;
                do {
                    if (this.ab != 0) {
                        final int ab = this.ab;
                        this.ab = 0;
                        this.ao[0].f(ab);
                        this.aa.a(ab);
                    }
                    if (this.z != 0) {
                        final int z = this.z;
                        this.z = 0;
                        final String x = this.x;
                        this.x = "";
                        final String y = this.y;
                        this.y = "";
                        this.ao[0].d(z);
                        this.w.a(z);
                        this.as.a(z, x, y);
                    }
                    this.i();
                    this.j();
                    if (n3 == 0 || n2 - System.currentTimeMillis() > 5L) {
                        Thread.sleep(5L);
                        n3 = 1;
                    }
                } while (System.currentTimeMillis() + 9L < n2);
                ++blaze3d.c;
            } while (currentThread == this.k);
        }
        catch (Exception ex4) {}
    }
    
    void b() {
        this.ao[0].b.a(this.an.v, (ag)null);
        this.an.v = false;
        this.an.a(this.s);
    }
    
    void c() {
        final Rectangle bounds = this.getBounds();
        int width = bounds.width;
        int height = bounds.height;
        if (width < 1) {
            width = 1;
        }
        if (height < 1) {
            height = 1;
        }
        this.s = new l(new int[width * height], width, height);
        (this.q = new MemoryImageSource(width, height, this.n, this.s.b, 0, width)).setAnimated(true);
        this.q.setFullBufferUpdates(false);
        this.p = this.createImage(this.q);
        this.l = width;
        this.m = height;
    }
    
    void a(final int n, final int n2, final int n3, final int n4) {
        this.q.newPixels(n, n2, n3, n4);
        if (this.r == null) {
            return;
        }
        this.r.setClip(0, 0, this.l, this.m);
        this.r.clipRect(n, n2, n3, n4);
        this.r.drawImage(this.p, 0, 0, null);
        this.ai = true;
    }
    
    public void update(final Graphics graphics) {
        if (this.ai) {
            graphics.drawImage(this.p, 0, 0, this.l, this.m, null);
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.ai) {
            graphics.drawImage(this.p, 0, 0, this.l, this.m, null);
        }
    }
    
    public void setBounds(final int n, final int n2, final int n3, final int n4) {
        this.aj = true;
        super.setBounds(n, n2, n3, n4);
    }
    
    private void a(final f f, final int n) {
        this.am = ac.a(f, new f(0, 0, this.l << 2, this.m << 2), n);
    }
    
    void d() {
        while (this.ap) {
            this.ap = false;
            this.ao[0].a(false);
        }
    }
    
    t a(final String s, final boolean b) {
        t t = null;
        for (t t2 = this.al; t2 != null; t2 = t2.q) {
            if (t2.d.equals(s)) {
                t = t2;
            }
        }
        if (t == null) {
            t = new t(this, s);
            if (this.al == null) {
                this.al = t;
            }
            else {
                t t3;
                for (t3 = this.al; t3.q != null; t3 = t3.q) {}
                t3.q = t;
            }
        }
        if (b) {
            this.az.a(t);
        }
        else {
            t.b();
        }
        return t;
    }
    
    void a(final t t) {
        if (t == this.al) {
            this.al = t.q;
        }
        else {
            t t2;
            for (t2 = this.al; t2 != null && t2.q != t; t2 = t2.q) {}
            if (t2 != null) {
                t2.q = t.q;
            }
        }
    }
    
    private void g() {
        for (int i = 0; i < this.av.size(); ++i) {
            final b b = this.av.elementAt(i);
            if (!b.w) {
                if (b.b != null) {
                    b.g();
                    b.a((u)null);
                    b.i();
                    b.j();
                }
            }
        }
        this.av.removeAllElements();
    }
    
    private void h() {
        for (int i = 0; i < this.at.size(); ++i) {
            final b b = this.at.elementAt(i);
            if (!b.w) {
                if (b.b != null) {
                    final String s = this.au.elementAt(i);
                    if (!s.equals("")) {
                        b.a(this.a(s, true));
                        b.v = true;
                        b.h();
                        b.ag.a(b);
                    }
                }
            }
        }
        this.at.removeAllElements();
        this.au.removeAllElements();
    }
    
    private void i() {
        synchronized (this.ax) {
            for (int i = 0; i < this.aw.size(); ++i) {
                ((bl)this.aw.elementAt(i)).a();
                this.d();
            }
            this.aw.removeAllElements();
        }
    }
    
    private void j() {
        final long currentTimeMillis = System.currentTimeMillis();
        for (int i = 0; i < 100; ++i) {
            if (this.ay[i] != null && this.ay[i].a(currentTimeMillis)) {
                this.d();
            }
        }
    }
    
    private void k() {
        final String parameter = this.getParameter("scale");
        if (parameter != null) {
            final String lowerCase = parameter.toLowerCase();
            if (lowerCase.equals("noscale")) {
                this.a6 = 3;
            }
            else if (lowerCase.equals("exactfit")) {
                this.a6 = 0;
            }
            else if (lowerCase.equals("noborder")) {
                this.a6 = 2;
            }
            else if (lowerCase.equals("showall")) {
                this.a6 = 1;
            }
        }
        final String parameter2 = this.getParameter("salign");
        if (parameter2 != null) {
            final String lowerCase2 = parameter2.toLowerCase();
            if (lowerCase2.indexOf("l") != -1) {
                this.a6 |= 0x10;
            }
            else if (lowerCase2.indexOf("r") != -1) {
                this.a6 |= 0x20;
            }
            if (lowerCase2.indexOf("t") != -1) {
                this.a6 |= 0x40;
            }
            else if (lowerCase2.indexOf("b") != -1) {
                this.a6 |= 0x80;
            }
        }
        final String parameter3 = this.getParameter("quality");
        if (parameter3 != null) {
            if (parameter3.toLowerCase().equals("best")) {
                this.a7 = 1;
            }
            else {
                this.a7 = 0;
            }
        }
    }
    
    private void a(final b b) {
        final String parameter = this.getParameter("FlashVars");
        if (parameter != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(parameter, "&=");
            while (stringTokenizer.hasMoreTokens()) {
                b.c(stringTokenizer.nextToken(), new as(stringTokenizer.nextToken()));
            }
        }
    }
    
    public void SetVariable(final String s, String s2) {
        if (this.ao != null && this.ao[0] != null) {
            if (s2 == null) {
                s2 = "";
            }
            this.ao[0].c(s, new as(s2));
        }
    }
    
    public String GetVariable(final String s) {
        if (this.ao != null && this.ao[0] != null) {
            return this.ao[0].f(s).toString();
        }
        return null;
    }
    
    public boolean firstFrameReady() {
        return this.ai;
    }
    
    private void l() {
        if (blaze3d.b != null) {
            return;
        }
        try {
            blaze3d.b = (o)new ObjectInputStream(this.getClass().getResourceAsStream("c.classs")).readObject();
        }
        catch (Exception ex) {}
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.ab |= 0x2;
        if ((mouseEvent.getModifiers() & 0x4) != 0x0) {
            this.ac |= 0x2;
        }
        else {
            this.ac |= 0x1;
        }
        ++this.ae;
        if (this.ae > 2) {
            this.ae = 2;
        }
        if (this.ae == 2) {
            this.ac = 3;
        }
        this.ad[0] = this.ac;
        this.af = true;
        this.ag = System.currentTimeMillis();
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.ab |= 0x4;
        final int modifiers = mouseEvent.getModifiers();
        if ((modifiers & 0x4) != 0x0) {
            this.ac &= 0x1;
        }
        else {
            this.ac &= 0x2;
        }
        --this.ae;
        if (this.ae < 0) {
            this.ae = 0;
        }
        if (this.ae == 0) {
            this.ac = 0;
        }
        this.ad[1] = this.ac;
        if ((modifiers & 0x4) != 0x0 && this.af && System.currentTimeMillis() - this.ag < 700L) {
            final Point point = mouseEvent.getPoint();
            this.be.show(this, point.x, point.y);
            this.ae = 0;
            this.ac = 0;
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.showStatus("Holomatix Blaze 3D started");
        final Point point = mouseEvent.getPoint();
        this.t = point.x;
        this.u = point.y;
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        final Point point = mouseEvent.getPoint();
        this.t = point.x;
        this.u = point.y;
        this.ae = 0;
        this.ac = 0;
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        this.ab |= 0x1;
        final Point point = mouseEvent.getPoint();
        this.t = point.x;
        this.u = point.y;
        this.af = false;
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        this.ab |= 0x1;
        final Point point = mouseEvent.getPoint();
        this.t = point.x;
        this.u = point.y;
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
        final char keyChar = keyEvent.getKeyChar();
        final int keyCode = keyEvent.getKeyCode();
        this.x += keyChar;
        this.y += (char)keyCode;
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        this.z |= 0x2;
        final char keyChar = keyEvent.getKeyChar();
        final int keyCode = keyEvent.getKeyCode();
        if (keyCode == 35 || keyCode == 36 || keyCode == 37 || keyCode == 38 || keyCode == 39 || keyCode == 40 || keyCode == 8 || keyCode == 127) {
            this.x += keyChar;
            this.y += (char)keyCode;
        }
        this.w.a(keyChar, keyCode);
        if (keyChar == '?') {
            p.ab = !p.ab;
        }
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
        this.z |= 0x1;
        this.w.b(keyEvent.getKeyChar(), keyEvent.getKeyCode());
    }
    
    public void focusGained(final FocusEvent focusEvent) {
        this.v = true;
    }
    
    public void focusLost(final FocusEvent focusEvent) {
        this.v = false;
    }
    
    void a(final String s, final String s2) {
        if (s.startsWith("asfunction:", 0)) {
            final au au = new au();
            String string = "";
            for (int i = 11; i < s.length(); ++i) {
                final char char1 = s.charAt(i);
                if (char1 == ',') {
                    au.a(new as(string));
                    string = "";
                }
                else {
                    string += char1;
                }
            }
            au.a(new as(string));
            this.ao[0].b(au.e(), au, true);
        }
        else {
            if (s.startsWith("javascript:", 0)) {
                if (!blaze3d.e) {
                    final String substring = s.substring(11);
                    try {
                        JSObject.getWindow((Applet)this).eval(substring);
                    }
                    catch (Exception ex) {}
                    return;
                }
            }
            try {
                final URL url = new URL(this.getDocumentBase(), s);
                if (!s2.equals("")) {
                    this.getAppletContext().showDocument(url, s2);
                }
                else {
                    this.getAppletContext().showDocument(url);
                }
            }
            catch (Exception ex2) {}
        }
    }
    
    public Cursor createBlankCursor() {
        return this.getToolkit().createCustomCursor(this.getToolkit().createImage(new byte[0]), new Point(0, 0), "");
    }
    
    void a(final int ah) {
        if (this.ah != ah) {
            if (!this.bd) {
                this.setCursor(Cursor.getPredefinedCursor(ah));
            }
            this.ah = ah;
        }
    }
    
    private void m() {
        this.bf = this.a("Restart Movie");
        (this.bg = this.a("Mute")).setEnabled(false);
        (this.bh = this.a("Settings...")).setEnabled(false);
        this.bi = this.a("About Holomatix Blaze3D...");
        (this.be = new PopupMenu("Context")).add(this.bf);
        this.be.add(this.bg);
        this.be.add(this.a("-"));
        this.be.add(this.bh);
        this.be.add(this.a("-"));
        this.be.add(this.bi);
        this.add(this.be);
    }
    
    public synchronized void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        if (actionCommand.equals(this.bf.getLabel())) {
            this.stop();
            this.start();
        }
        else if (!actionCommand.equals(this.bg.getLabel())) {
            if (!actionCommand.equals(this.bh.getLabel())) {
                if (actionCommand.equals(this.bi.getLabel())) {
                    try {
                        this.getAppletContext().showDocument(new URL("http://www.holomatix.com/blaze3d/about"), "_blank");
                    }
                    catch (Exception ex) {}
                }
            }
        }
    }
    
    private MenuItem a(final String s) {
        final MenuItem menuItem = new MenuItem(s);
        menuItem.addActionListener(this);
        return menuItem;
    }
    
    static {
        a = new String("");
        blaze3d.c = 0;
        blaze3d.bb = new Hashtable();
    }
}
