import java.awt.event.KeyEvent;
import java.awt.Window;
import java.awt.Dialog;
import java.awt.Cursor;
import java.applet.AppletContext;
import java.net.URL;
import java.awt.Graphics;
import java.awt.MediaTracker;
import java.awt.Container;
import java.awt.Frame;
import java.awt.event.MouseEvent;
import java.awt.Component;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.KeyListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ak extends Applet implements KeyListener, MouseMotionListener, MouseListener, Runnable
{
    private r if;
    private r new;
    private r goto;
    ah C;
    k byte;
    protected v n;
    protected v b;
    protected l h;
    af i;
    s d;
    i w;
    Thread s;
    int width;
    int height;
    long D;
    public boolean do;
    public static final String j = "v1.08";
    String z;
    Component long;
    Component u;
    boolean a;
    private boolean char;
    public int g;
    boolean e;
    public int try;
    boolean o;
    boolean m;
    boolean l;
    int p;
    float int;
    boolean A;
    boolean B;
    Object case;
    private boolean c;
    boolean t;
    MouseEvent f;
    String void;
    int for;
    e r;
    float k;
    float q;
    float else;
    boolean v;
    static /* synthetic */ Class class$0;
    static /* synthetic */ Class class$1;
    
    public ak() {
        this.if = null;
        this.new = null;
        this.goto = null;
        this.C = new ah();
        this.byte = null;
        this.n = null;
        this.b = null;
        this.h = null;
        this.i = null;
        this.d = null;
        this.w = null;
        this.D = 0L;
        this.do = false;
        this.z = "ImmerVision PURE Player for Java v1.08";
        this.long = this;
        this.u = null;
        this.a = false;
        this.char = false;
        this.g = 1;
        this.e = false;
        this.try = 0;
        this.o = false;
        this.m = false;
        this.l = false;
        this.p = -1;
        this.int = 1.0f;
        this.A = false;
        this.B = false;
        this.case = new Object();
        this.c = false;
        this.t = false;
        this.f = null;
        this.void = "";
        this.for = 0;
        this.k = 0.0f;
        this.q = 0.0f;
        this.else = 0.0f;
        this.v = false;
    }
    
    public String getAppletInfo() {
        return this.z;
    }
    
    public void if() {
        if (this.d == null) {
            Container container;
            for (container = this.getParent(); !(container instanceof Frame); container = container.getParent()) {}
            final Frame frame = (Frame)container;
            if (this.n != null) {
                this.d = new s(this, frame, "v1.08", this.n.if, this.n.new, this.n.m);
            }
            else {
                this.d = new s(this, frame, "v1.08", "", "", "");
            }
        }
        else {
            this.d.toFront();
        }
    }
    
    public void a() {
        if (this.i == null) {
            String s = this.getParameter("helpcontenttext");
            if (s != null) {
                for (int i = s.indexOf(92); i != -1; i = s.indexOf(92)) {
                    if (s.charAt(i + 1) == 'n') {
                        s = String.valueOf(s.substring(0, i)) + "\n" + s.substring(i + 2, s.length());
                    }
                    else if (s.charAt(i + 1) == 't') {
                        s = String.valueOf(s.substring(0, i)) + "\t" + s.substring(i + 2, s.length());
                    }
                    else {
                        s = String.valueOf(s.substring(0, i)) + s.substring(i + 2, s.length());
                    }
                }
            }
            Container container;
            for (container = this.getParent(); !(container instanceof Frame); container = container.getParent()) {}
            this.i = new af((Frame)container, "v1.08", this, s);
        }
        else {
            this.i.toFront();
        }
    }
    
    public void init() {
        this.h = new l();
        this.h.try = this;
        System.out.println(this.z);
        try {
            final String property = System.getProperty("java.version");
            this.try = property.charAt(2) - '0';
            System.out.println("Java Version: " + property + " from " + System.getProperty("java.vendor"));
            System.out.println("Operating system: " + System.getProperty("os.name") + " version " + System.getProperty("os.version") + " on " + System.getProperty("os.arch"));
            if (System.getProperty("os.name").toLowerCase().startsWith("mac")) {
                this.o = true;
                if (this.try == 3) {
                    this.m = true;
                }
                if (this.try == 4) {
                    this.m = true;
                    this.l = true;
                }
            }
        }
        catch (Exception ex) {}
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        this.addKeyListener(this);
        this.long = this;
        this.width = this.getSize().width;
        this.height = this.getSize().height;
        this.h.e = this;
        this.h.byte = this.getToolkit();
        this.h.k = new d();
        this.h.o = new y(this.h);
        this.h.c = this;
        this.h.b = new MediaTracker(this);
        l.int = this.try;
        if (this.try >= 4) {
            try {
                new h(this, this);
                System.out.println("Mouse wheel enabled.");
            }
            catch (Throwable t) {
                System.out.println("Mouse wheel not supported.");
            }
        }
        else {
            System.out.println("Mouse wheel not supported.");
        }
        final String parameter = this.getParameter("bgcolor");
        if (parameter != null) {
            this.p = (0xFF000000 | l.a(parameter));
        }
        final String parameter2 = this.getParameter("mousespeed");
        if (parameter2 != null) {
            this.int = Float.valueOf(parameter2) / 100.0f;
            if (this.int < 0.01 || this.int > 100.0f) {
                this.int = 1.0f;
            }
        }
        final String parameter3 = this.getParameter("smoothstop");
        if (parameter3 != null) {
            l.l = Float.valueOf(parameter3);
            if (l.l > 0.0f) {
                l.l = 1.0f - Float.valueOf(parameter3) / 1000.0f;
            }
            if (l.l >= 1.0f || l.l < 0.0f) {
                l.l = 0.0f;
            }
        }
        final String parameter4 = this.getParameter("fullscreen");
        if (parameter4 != null && parameter4.toLowerCase().compareTo("true") == 0) {
            this.a = true;
        }
        final String parameter5 = this.getParameter("hidegui");
        if (parameter5 != null && parameter5.toLowerCase().compareTo("true") == 0) {
            this.do = true;
        }
        final String parameter6 = this.getParameter("protectfullscreen");
        if (parameter6 != null && parameter6.toLowerCase().compareTo("true") == 0) {
            this.char = true;
        }
    }
    
    public void start() {
        this.for();
        if (!this.a && this.char) {
            this.a = true;
            this.a(false);
            this.a = false;
        }
        this.a(true);
        this.if.if();
        this.w = new i(this.h, this);
        this.byte = new k(this.getDocumentBase(), this.w, this.h);
        this.h.n = this.byte;
        this.w.byte = this.h;
        final String parameter = this.getParameter("quality");
        if (parameter != null) {
            try {
                float floatValue = Float.valueOf(parameter);
                if (floatValue < 100.0f) {
                    floatValue = 100.0f;
                }
                else if (floatValue > 1000.0f) {
                    floatValue = 1000.0f;
                }
                this.h.g = floatValue;
                System.out.println("Quality set to " + parameter);
            }
            catch (Exception ex) {
                this.h.g = 100.0f;
                System.out.println("Error in Quality parameter. Quality is set to default.");
            }
        }
        final String parameter2 = this.getParameter("lockzenithnadir");
        if (parameter2 != null && parameter2.toLowerCase().compareTo("true") == 0) {
            System.out.println("Lock camera movements at zenith and nadir.");
            this.h.p = true;
        }
        final String parameter3 = this.getParameter("singlepanorama");
        if (parameter3 != null && parameter3.toLowerCase().compareTo("true") == 0) {
            System.out.println("Single panorama mode enabled.");
            this.h.case = true;
        }
        final String parameter4 = this.getParameter("usecache");
        if (parameter4 != null) {
            if (parameter4.toLowerCase().compareTo("true") == 0) {
                this.h.j = true;
            }
            else if (parameter4.toLowerCase().compareTo("false") != 0 && l.int < 3) {
                this.h.j = true;
            }
        }
        else if (l.int < 3) {
            this.h.j = true;
        }
        System.out.println("Internal cache system " + (this.h.j ? "enabled" : "disabled") + ".");
        final String parameter5 = this.getParameter("antialiasing");
        if (parameter5 != null) {
            if (parameter5.toLowerCase().compareTo("none") == 0) {
                System.out.println("No antialiasing.");
                this.g = 0;
            }
            else if (parameter5.toLowerCase().compareTo("everytime") == 0) {
                System.out.println("Full antialiasing.");
                this.g = 2;
            }
            else {
                System.out.println("Antialiasing on stop.");
            }
        }
        else {
            System.out.println("Antialiasing on stop.");
        }
        (this.r = new e(this.h, this)).a(this.if);
        (this.s = new Thread(this)).start();
        this.B = true;
    }
    
    public void run() {
        while (!this.h.h) {
            Thread.yield();
            long d = System.currentTimeMillis();
            try {
                if (d - this.D < 40L) {
                    Thread.sleep(this.D + 40L - d);
                }
                d = System.currentTimeMillis();
                this.D = d;
            }
            catch (InterruptedException ex) {}
            try {
                this.a(d);
            }
            catch (NullPointerException ex2) {}
        }
    }
    
    private void a(final long n) {
        boolean b = false;
        synchronized (this.case) {
            if (this.width != this.long.getSize().width || (this.height != this.long.getSize().height - ((this.a & !this.m) ? 30 : 0) - ((this.a & this.m) ? 22 : 0) | this.A)) {
                this.width = this.long.getSize().width;
                this.height = this.long.getSize().height - ((this.a & !this.m) ? 30 : 0) - ((this.a & this.m) ? 22 : 0);
                if (this.width <= 0 || this.height <= 0) {
                    // monitorexit(this.case)
                    return;
                }
                if (this.if != null) {
                    this.if.if();
                }
                if (this.n != null) {
                    this.n.i.a(this.h.d);
                }
                this.r.a(this.if);
                this.A = false;
                if (this.n != null) {
                    this.n.do = true;
                }
                b = true;
            }
            boolean b2 = b | this.r.a(n);
            if (this.n == null && this.b == null) {
                final String parameter = this.getParameter("panorama");
                if (parameter != null) {
                    this.b = new v(this.h, this, false);
                    this.b.d = parameter;
                    this.b.a(this.byte.a(parameter, null, false, false, false));
                }
            }
            else if (this.b != null && !this.b.f && this.b.if(false)) {
                this.b.i.a(this.h.d);
                if (this.n != null) {
                    if (this.b.for && this.n.int) {
                        this.b.i.I = this.n.i.I - this.n.g + this.b.g;
                        this.b.i.B = 0.0f;
                        this.b.i.C = this.n.i.C;
                    }
                }
                else if (this.b.for && this.v) {
                    this.b.i.I = this.q - this.k + this.b.g;
                    this.b.i.B = 0.0f;
                    this.b.i.C = this.else;
                    this.v = false;
                }
                this.b.if(n);
                if (this.n != null) {
                    this.n.a();
                    this.n = null;
                }
                this.n = this.b;
                this.r.a("");
                this.r.for = false;
                this.b = null;
                b2 = true;
            }
            if (this.b != null) {
                this.b.a(n);
            }
            if (this.n != null) {
                b2 |= this.n.a(n);
            }
            if (b2) {
                if (this.n != null && this.n.case) {
                    this.n.for();
                }
                this.r.a();
            }
            if (b2 || this.c) {
                this.a(this.f, this.void, this.for);
                this.c = false;
                this.if.do();
            }
        }
        // monitorexit(this.case)
    }
    
    public void repaint() {
        this.c = true;
    }
    
    public void update(final Graphics graphics) {
        this.c = true;
    }
    
    public void paint(final Graphics graphics) {
        this.c = true;
    }
    
    public boolean a(final String s, final aa aa, final boolean b) {
        if (this.b != null || (this.n != null && !this.n.else)) {
            return false;
        }
        if (!this.h.j) {
            this.byte.if();
        }
        if (this.h.case && this.n != null) {
            if (this.n.int) {
                this.k = this.n.g;
                this.q = this.n.i.I;
                this.else = this.n.i.C;
                this.v = true;
            }
            this.n.a();
            this.n = null;
            System.gc();
        }
        (this.b = new v(this.h, this, b)).a(this.byte.a(s, aa, false, false, false));
        return true;
    }
    
    public void a(final String s, final String s2) {
        try {
            final URL url = new URL(this.getDocumentBase(), s);
            final AppletContext appletContext = this.getAppletContext();
            if (s2 != null) {
                appletContext.showDocument(url, s2);
            }
            else {
                appletContext.showDocument(url);
            }
        }
        catch (Exception ex) {
            System.out.println("Can't open: " + s);
        }
    }
    
    public void a(final String s) {
        if (s != null) {
            try {
                if (this.o) {
                    this.getAppletContext().showDocument(new URL("javascript:" + s), "_self");
                }
                else {
                    final Class<?> forName;
                    final Class<?> clazz = forName = Class.forName("netscape.javascript.JSObject");
                    final String s2 = "getWindow";
                    final Class[] array = { null };
                    final int n = 0;
                    Class class$0;
                    if ((class$0 = ak.class$0) == null) {
                        try {
                            class$0 = (ak.class$0 = Class.forName("java.applet.Applet"));
                        }
                        catch (ClassNotFoundException ex) {
                            throw new NoClassDefFoundError(ex.getMessage());
                        }
                    }
                    array[n] = class$0;
                    final Object invoke = forName.getMethod(s2, (Class[])array).invoke(clazz, this);
                    final Class<?> clazz2 = clazz;
                    final String s3 = "eval";
                    final Class[] array2 = { null };
                    final int n2 = 0;
                    Class class$2;
                    if ((class$2 = ak.class$1) == null) {
                        try {
                            class$2 = (ak.class$1 = Class.forName("java.lang.String"));
                        }
                        catch (ClassNotFoundException ex2) {
                            throw new NoClassDefFoundError(ex2.getMessage());
                        }
                    }
                    array2[n2] = class$2;
                    clazz2.getMethod(s3, (Class[])array2).invoke(invoke, s);
                }
            }
            catch (Exception ex3) {
                System.out.println("Can't execute: " + s);
            }
        }
    }
    
    private void do() {
        if (this.h.new != this.C.for) {
            this.h.new = this.C.for;
            switch (this.C.for) {
                case 3: {
                    this.long.setCursor(Cursor.getPredefinedCursor(0));
                    break;
                }
                case 0: {
                    this.long.setCursor(Cursor.getPredefinedCursor(12));
                    break;
                }
                case 1: {
                    this.long.setCursor(Cursor.getPredefinedCursor(13));
                    break;
                }
                case 2: {
                    this.long.setCursor(Cursor.getPredefinedCursor(9));
                    break;
                }
            }
        }
        for (int i = 0; i < this.C.byte; ++i) {
            this.if(this.C.b[i]);
        }
    }
    
    public void if(String s) {
        if (s.toLowerCase().startsWith("loadpano(")) {
            s = s.substring(s.indexOf("\"") + 1, s.length()).trim();
            final int index = s.indexOf("\"");
            final String substring = s.substring(0, index);
            s = s.substring(index + 1, s.length()).trim();
            if (s.substring(s.indexOf(",") + 1, s.indexOf(")")).trim().toLowerCase().compareTo("true") == 0) {
                this.a(substring, this.n.h, true);
            }
            else {
                this.a(substring, this.n.h, false);
            }
        }
        else if (s.toLowerCase().startsWith("javascript(")) {
            s = s.substring(s.indexOf("\"") + 1, s.lastIndexOf("\"")).trim();
            this.a(s);
        }
        else if (s.toLowerCase().startsWith("open(")) {
            final int index2 = s.indexOf("\"");
            if (index2 != -1) {
                s = s.substring(index2 + 1, s.length()).trim();
                final int index3 = s.indexOf("\"");
                if (index3 != -1) {
                    final String trim = s.substring(0, index3).trim();
                    final int index4 = s.indexOf("\"");
                    String trim2 = null;
                    if (index4 != -1) {
                        s = s.substring(index4 + 1, s.length()).trim();
                        final int index5 = s.indexOf("\"");
                        final int lastIndex = s.lastIndexOf("\"");
                        if (index5 != -1 && lastIndex != index5) {
                            trim2 = s.substring(index5 + 1, lastIndex).trim();
                        }
                    }
                    this.a(trim, trim2);
                }
            }
        }
    }
    
    public void destroy() {
        try {
            if (this.n != null) {
                this.n.a();
            }
            this.h.h = true;
            this.h.a();
            this.if.a();
            this.if = null;
            this.new.a();
            this.new = null;
            if (this.goto != null) {
                this.goto.a();
            }
            this.goto = null;
            if (this.u != null) {
                if (this.try >= 4) {
                    ((Dialog)this.u).dispose();
                }
                else {
                    ((Window)this.u).dispose();
                }
            }
            this.u = null;
            this.C = null;
            this.byte = null;
            if (this.b != null) {
                this.b.a();
            }
            this.b = null;
            this.w = null;
            this.long = null;
            this.u = null;
            System.gc();
            System.out.println("Finished.");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        synchronized (this.case) {
            this.a(mouseEvent, "mouseclicked", 2);
        }
        // monitorexit(this.case)
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        synchronized (this.case) {
            this.a(mouseEvent, "mousepressed", 0);
        }
        // monitorexit(this.case)
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        synchronized (this.case) {
            this.a(mouseEvent, "mousereleased", 1);
        }
        // monitorexit(this.case)
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        synchronized (this.case) {
            this.a(mouseEvent, "mousedragged", 4);
        }
        // monitorexit(this.case)
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        synchronized (this.case) {
            this.a(mouseEvent, "mousemoved", 3);
        }
        // monitorexit(this.case)
    }
    
    private void a(final MouseEvent f, final String s, final int if1) {
        if (f == null) {
            return;
        }
        this.f = f;
        switch (if1) {
            case 1:
            case 2:
            case 3: {
                this.for = 3;
                this.void = "mousemoved";
                break;
            }
            case 0:
            case 4: {
                this.for = 4;
                this.void = "mousedragged";
                break;
            }
        }
        if (!this.B) {
            return;
        }
        if (this.C != null) {
            this.C.a();
            if (s.compareTo("mouseexited") == 0) {
                this.C.goto = -65536;
                this.C.else = -65536;
            }
            else {
                this.C.goto = f.getX();
                this.C.else = f.getY();
            }
        }
        if ((f.getModifiers() & 0x4) == 0x0) {
            if ((f.getModifiers() & 0x10) == 0x0) {
                if ((f.getModifiers() & 0x8) != 0x0) {}
            }
        }
        if ((f.getModifiers() & 0x4) != 0x0) {
            this.C.f = 5;
        }
        else if ((f.getModifiers() & 0x10) != 0x0) {
            this.C.f = 6;
        }
        else if ((f.getModifiers() & 0x8) != 0x0) {
            this.C.f = 7;
        }
        if (this.e) {
            this.C.f = 5;
        }
        this.C.if = if1;
        this.r.a(this.C);
        if (this.n != null) {
            this.n.a(this.C);
        }
        this.do();
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        synchronized (this.case) {
            if (!this.B) {
                // monitorexit(this.case)
                return;
            }
        }
        // monitorexit(this.case)
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        synchronized (this.case) {
            if (!this.B) {
                // monitorexit(this.case)
                return;
            }
            this.a(mouseEvent, "mouseExited", 3);
        }
        // monitorexit(this.case)
    }
    
    public void a(final int n) {
        synchronized (this.case) {
            if (!this.B) {
                // monitorexit(this.case)
                return;
            }
            if (this.n != null) {
                if (n < 0) {
                    this.n.i.C -= this.n.i.C * 0.1f;
                }
                else {
                    this.n.i.C += this.n.i.C * 0.1f;
                }
                this.n.i.do = true;
            }
        }
        // monitorexit(this.case)
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
        final Object case1 = this.case;
    }
    // monitorenter(case1)
    // monitorexit(case1)
    
    public void keyPressed(final KeyEvent keyEvent) {
        synchronized (this.case) {
            if (keyEvent.getKeyCode() == 112 || keyEvent.getKeyCode() == 156) {
                this.a();
            }
            if (keyEvent.getKeyCode() == 113 || keyEvent.getKeyCode() == 36 || keyEvent.getKeyCode() == 154) {
                this.if();
            }
            if (this.n != null && !this.t) {
                if (keyEvent.getKeyCode() == 17) {
                    this.n.i.goto();
                    this.t = true;
                }
                else if (keyEvent.getKeyCode() == 16) {
                    this.n.i.try();
                    this.t = true;
                }
            }
            if (keyEvent.getKeyCode() == 27 && this.a) {
                this.a = false;
                this.a(true);
            }
            else if (keyEvent.getKeyCode() == 32 && this.n != null) {
                this.n.if();
            }
        }
        // monitorexit(this.case)
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
        synchronized (this.case) {
            if (this.n != null && this.t && (keyEvent.getKeyCode() == 17 || keyEvent.getKeyCode() == 16)) {
                this.n.i.char();
                this.t = false;
            }
        }
        // monitorexit(this.case)
    }
    
    public void a(final boolean visible) {
        this.A = true;
        try {
            if (this.a) {
                Label_0501: {
                    if (this.u == null) {
                        Container parent = this;
                        do {
                            try {
                                parent = parent.getParent();
                            }
                            catch (NullPointerException ex) {
                                parent = new Frame();
                                parent.setBounds(0, 0, 0, 0);
                                parent.setVisible(true);
                                break;
                            }
                        } while (!(parent instanceof Frame));
                        final Frame frame = (Frame)parent;
                        if (this.try >= 4) {
                            this.u = new ai(frame, this);
                            ((ai)this.u).setUndecorated(true);
                        }
                        else {
                            this.u = new ac(frame, this);
                        }
                        final int width = this.u.getToolkit().getScreenSize().width;
                        final int height = this.u.getToolkit().getScreenSize().height;
                        if (this.l) {
                            this.u.setLocation(0, 22);
                        }
                        else {
                            this.u.setLocation(0, 0);
                        }
                        this.u.setSize(width, height);
                        if (this.l) {
                            this.u.reshape(0, 22, width, height + (this.m ? 0 : 30));
                        }
                        else {
                            this.u.reshape(0, 0, width, height + (this.m ? 0 : 30));
                        }
                        this.width = this.u.getSize().width;
                        this.height = this.u.getSize().height - ((this.a & !this.m) ? 30 : 0) - ((this.a & this.m) ? 22 : 0);
                        this.u.addMouseMotionListener(this);
                        this.u.addMouseListener(this);
                        this.u.addKeyListener(this);
                        this.u.setVisible(true);
                        ((Window)this.u).toFront();
                        this.u.requestFocus();
                        if (this.try >= 4) {
                            try {
                                new h(this, this.u);
                            }
                            catch (Throwable t) {}
                        }
                        if (this.goto == null) {
                            if (this.try >= 3) {
                                try {
                                    (this.goto = new ag()).a(this.u, true, this);
                                    break Label_0501;
                                }
                                catch (Exception ex2) {
                                    l.a = false;
                                    this.goto = new b();
                                    try {
                                        this.goto.a(this.u, true, this);
                                    }
                                    catch (Exception ex3) {}
                                    break Label_0501;
                                }
                            }
                            this.goto = new b();
                            try {
                                this.goto.a(this.u, true, this);
                            }
                            catch (Exception ex4) {}
                        }
                    }
                }
                this.u.setVisible(visible);
                this.long = this.u;
                this.if = this.goto;
                this.h.d = this.if;
            }
            else {
                if (this.a && this.if != null) {
                    return;
                }
                if (this.u != null) {
                    final int n = this.if.long * this.if.e;
                    try {
                        for (int i = 0; i < n; ++i) {
                            this.if.c[i] = -1;
                        }
                    }
                    catch (Exception ex5) {}
                    this.u.setVisible(false);
                }
                if (this.new == null) {
                    this.for();
                }
                this.long = this;
                this.if = this.new;
            }
            if (this.n != null) {
                this.n.do = true;
            }
            this.width = this.long.getSize().width;
            this.height = this.long.getSize().height - ((this.a & !this.m) ? 30 : 0) - ((this.a & this.m) ? 22 : 0);
            this.if.if();
        }
        catch (OutOfMemoryError outOfMemoryError) {
            this.a = false;
            if (this.new == null) {
                this.for();
            }
            if (this.u != null) {
                if (this.try >= 4) {
                    ((Dialog)this.u).dispose();
                }
                else {
                    ((Window)this.u).dispose();
                }
            }
            this.u = null;
            this.goto = null;
            System.gc();
            if (this.new == null) {
                this.for();
            }
            this.long = this;
            this.if = this.new;
            System.out.println("Can't go fullscreen. Out of memory.");
        }
        this.width = this.long.getSize().width;
        this.height = this.long.getSize().height - ((this.a & !this.m) ? 30 : 0) - ((this.a & this.m) ? 22 : 0);
        this.h.d = this.if;
    }
    
    private void for() {
        this.long = this;
        Label_0116: {
            if (this.new == null) {
                if (this.try >= 3) {
                    try {
                        (this.new = new ag()).a(this.long, false, this);
                        break Label_0116;
                    }
                    catch (Exception ex) {
                        l.a = false;
                        this.new = null;
                        this.new = new b();
                        try {
                            this.new.a(this.long, false, this);
                        }
                        catch (Exception ex2) {}
                        break Label_0116;
                    }
                }
                this.new = new b();
                try {
                    this.new.a(this.long, false, this);
                }
                catch (Exception ex3) {}
            }
        }
        this.if = this.new;
    }
}
