// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

import java.awt.Image;
import java.io.InputStream;
import java.util.Properties;
import java.io.ByteArrayInputStream;
import java.awt.event.KeyEvent;
import java.awt.Window;
import java.awt.Dialog;
import java.awt.Cursor;
import java.applet.AppletContext;
import java.net.URL;
import java.awt.Graphics;
import java.awt.Container;
import java.awt.Frame;
import java.awt.MediaTracker;
import java.awt.event.MouseEvent;
import java.awt.Component;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.KeyListener;
import java.applet.Applet;

public class l extends Applet implements KeyListener, MouseMotionListener, MouseListener, Runnable
{
    private i if;
    private i int;
    private i long;
    ab P;
    m[] m;
    int void;
    protected be char;
    protected v E;
    protected v j;
    protected m B;
    protected m f;
    protected ac p;
    o L;
    Thread G;
    int width;
    int height;
    boolean q;
    boolean u;
    long Q;
    public boolean v;
    public static final String s = "v1.12";
    String M;
    Component b;
    Component J;
    boolean a;
    boolean H;
    public int o;
    boolean l;
    public int new;
    bq r;
    bg h;
    static boolean C;
    static boolean A;
    static boolean z;
    float for;
    boolean N;
    String F;
    protected boolean O;
    Object case;
    private boolean e;
    private String i;
    private ae else;
    private boolean c;
    private boolean byte;
    boolean g;
    boolean try;
    long t;
    public ad I;
    int k;
    float w;
    float D;
    float goto;
    boolean K;
    MouseEvent n;
    String d;
    int do;
    static /* synthetic */ Class class$0;
    static /* synthetic */ Class class$1;
    
    static {
        l.C = false;
        l.A = false;
        l.z = false;
    }
    
    public l() {
        this.if = null;
        this.int = null;
        this.long = null;
        this.P = new ab();
        this.m = null;
        this.void = 0;
        this.char = null;
        this.E = null;
        this.j = null;
        this.B = null;
        this.f = null;
        this.p = null;
        this.L = null;
        this.q = false;
        this.u = false;
        this.Q = 0L;
        this.v = false;
        this.M = "ImmerVision PURE Player PRO for Java v1.12";
        this.b = this;
        this.J = null;
        this.a = false;
        this.H = false;
        this.o = 1;
        this.l = false;
        this.new = 0;
        this.r = null;
        this.h = null;
        this.for = 1.0f;
        this.N = false;
        this.F = "";
        this.O = false;
        this.case = new Object();
        this.e = false;
        this.i = null;
        this.else = null;
        this.c = false;
        this.byte = false;
        this.g = false;
        this.try = true;
        this.t = 0L;
        this.I = null;
        this.k = 30;
        this.w = 0.0f;
        this.D = 0.0f;
        this.goto = 0.0f;
        this.K = false;
        this.n = null;
        this.d = "";
        this.do = 0;
    }
    
    public String getAppletInfo() {
        return this.M;
    }
    
    public void init() {
        this.p = new ac();
        this.p.case = this;
        System.out.println(this.M);
        try {
            final String property = System.getProperty("java.version");
            this.new = property.charAt(2) - '0';
            System.out.println("Java Version: " + property + " from " + System.getProperty("java.vendor"));
            System.out.println("Operating system: " + System.getProperty("os.name") + " version " + System.getProperty("os.version") + " on " + System.getProperty("os.arch"));
            if (property.compareTo("1.6.0_03") >= 0) {
                this.k = 17;
            }
            if (System.getProperty("os.name").toLowerCase().startsWith("mac")) {
                a.a.a.a.l.C = true;
                if (this.new == 3) {
                    a.a.a.a.l.A = true;
                }
                if (this.new == 4) {
                    a.a.a.a.l.A = true;
                    a.a.a.a.l.z = true;
                }
            }
        }
        catch (Exception ex) {}
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        this.addKeyListener(this);
        this.b = this;
        this.width = this.getSize().width;
        this.height = this.getSize().height;
        this.p.L = this;
        this.p.I = this.getToolkit();
        this.p.W = new ap();
        this.p.aa = new av(this.p);
        this.p.P = this;
        this.p.if = new MediaTracker(this);
        this.p.s = new au();
        ac.aj = this.new;
        this.new();
        final String parameter = this.getParameter("maximagesize");
        if (parameter != null) {
            ac.b = ac.a(parameter);
            if (ac.b < 0) {
                ac.b = 0;
            }
        }
        if (this.new >= 4) {
            try {
                new am(this, this);
                System.out.println("Mouse wheel enabled.");
            }
            catch (Throwable t) {
                System.out.println("Mouse wheel not supported.");
            }
        }
        else {
            System.out.println("Mouse wheel not supported.");
        }
        final String parameter2 = this.getParameter("mousespeed");
        if (parameter2 != null) {
            this.for = Float.valueOf(parameter2) / 100.0f;
            if (this.for < 0.01 || this.for > 100.0f) {
                this.for = 1.0f;
            }
        }
        final String parameter3 = this.getParameter("smoothstop");
        if (parameter3 != null) {
            ac.g = Float.valueOf(parameter3);
            if (ac.g > 0.0f) {
                ac.g = 1.0f - Float.valueOf(parameter3) / 1000.0f;
            }
            if (ac.g >= 1.0f || ac.g < 0.0f) {
                ac.g = 0.0f;
            }
        }
        final String parameter4 = this.getParameter("fullscreen2");
        if (parameter4 != null && parameter4.toLowerCase().compareTo("true") == 0) {
            this.H = true;
        }
        if (!this.H) {
            final String parameter5 = this.getParameter("fullscreen");
            if (parameter5 != null && parameter5.toLowerCase().compareTo("true") == 0) {
                this.a = true;
            }
        }
        final String parameter6 = this.getParameter("protectfullscreen");
        if (parameter6 != null && parameter6.toLowerCase().compareTo("true") == 0) {
            this.byte = true;
        }
        this.for();
        if (this.byte && !this.a && !this.H) {
            this.a = true;
            this.a(false);
            this.a = false;
        }
        this.a(true);
    }
    
    public void int() {
        if (this.h == null) {
            Container container;
            for (container = this.getParent(); !(container instanceof Frame); container = container.getParent()) {}
            final Frame frame = (Frame)container;
            if (this.B != null) {
                this.h = new bg(this, frame, "v1.12", this.E.e, this.E.new, this.E.s, this.B.e, this.B.new, this.B.s);
            }
            else {
                this.h = new bg(this, frame, "v1.12", this.E.e, this.E.new, this.E.s, "", "", "");
            }
        }
        else {
            this.h.toFront();
        }
    }
    
    public void int(final String s) {
        if (this.r == null) {
            Container container;
            for (container = this.getParent(); !(container instanceof Frame); container = container.getParent()) {}
            this.r = new bq((Frame)container, "v1.12", this, s);
        }
        else {
            this.r.toFront();
        }
    }
    
    public void start() {
        if (this.E != null) {
            return;
        }
        this.if.new();
        this.L = new o();
        this.char = new be(this.getDocumentBase(), this.L, this.p);
        this.p.B = this.char;
        this.L.b = this.p;
        this.E = new v(this.p, this.L, this);
        final String parameter = this.getParameter("optimizememory");
        if (parameter != null && parameter.toLowerCase().compareTo("true") == 0) {
            System.out.println("Memory optimization enabled.");
            this.p.T = true;
        }
        final String parameter2 = this.getParameter("quality");
        if (parameter2 != null) {
            try {
                float floatValue = Float.valueOf(parameter2);
                if (floatValue < 100.0f) {
                    floatValue = 100.0f;
                }
                else if (floatValue > 1000.0f) {
                    floatValue = 1000.0f;
                }
                this.p.ar = floatValue;
                System.out.println("Quality set to " + parameter2);
            }
            catch (Exception ex2) {
                this.p.ar = 100.0f;
                System.out.println("Error in Quality parameter. Quality is set to default.");
            }
        }
        final String parameter3 = this.getParameter("lockzenithnadir");
        if (parameter3 != null && parameter3.toLowerCase().compareTo("true") == 0) {
            System.out.println("Lock camera movements at zenith and nadir.");
            this.p.for = true;
        }
        final String parameter4 = this.getParameter("singlepanorama");
        if (parameter4 != null && parameter4.toLowerCase().compareTo("true") == 0) {
            System.out.println("Single panorama mode enabled.");
            this.p.K = true;
        }
        final String parameter5 = this.getParameter("usecache");
        if (parameter5 != null) {
            if (parameter5.toLowerCase().compareTo("true") == 0) {
                this.p.k = true;
            }
            else if (parameter5.toLowerCase().compareTo("smart") == 0 && ac.aj < 3) {
                this.p.k = true;
            }
        }
        else if (ac.aj < 3) {
            this.p.k = true;
        }
        System.out.println("Internal cache system " + (this.p.k ? "enabled" : "disabled") + ".");
        final String parameter6 = this.getParameter("antialiasing");
        if (parameter6 != null) {
            if (parameter6.toLowerCase().compareTo("none") == 0) {
                System.out.println("No antialiasing.");
                this.o = 0;
            }
            else if (parameter6.toLowerCase().compareTo("everytime") == 0) {
                System.out.println("Full antialiasing.");
                this.o = 2;
            }
            else {
                System.out.println("Antialiasing on stop.");
            }
        }
        else {
            System.out.println("Antialiasing on stop.");
        }
        if (this.v) {
            this.E.a(this.char.a(this.getParameter(new String(ac.p)), null, false, false, false));
            this.try = false;
        }
        else {
            this.E.a(this.char.a(new String(ac.Y), null, false, false, false));
        }
        while (!this.E.k || !this.E.new()) {
            try {
                Thread.sleep(100L);
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            this.E.a(System.currentTimeMillis());
        }
        if (!this.v) {
            final String parameter7 = this.getParameter("gui");
            this.L.f();
            if (parameter7 == null) {
                this.g = true;
            }
            else {
                (this.j = new v(this.p, this.L, this)).a(this.char.a(parameter7, null, false, false, false));
            }
        }
        final String parameter8 = this.getParameter("transition");
        if (parameter8 != null) {
            this.F = parameter8;
        }
        this.L.d();
        this.E.if(System.currentTimeMillis());
        this.L.a((int)this.E.L);
        this.t = System.currentTimeMillis();
        this.E.L += this.t;
        this.Q = System.currentTimeMillis();
        this.E.long = true;
        (this.G = new Thread(this)).start();
        this.O = true;
    }
    
    public void run() {
        while (!this.p.G) {
            Thread.yield();
            long n = System.currentTimeMillis();
            try {
                if (n - this.Q < 40L) {
                    Thread.sleep(this.Q + 40L - n);
                }
                n = System.currentTimeMillis();
                if (this.try) {
                    this.L.by += (int)(n - this.t);
                    this.t = n;
                }
                this.Q = n;
            }
            catch (InterruptedException ex) {}
            try {
                this.a(n);
            }
            catch (NullPointerException ex2) {}
        }
    }
    
    private void a(final long n) {
        boolean b = false;
        synchronized (this.case) {
            this.if();
            if (this.width != this.b.getSize().width || (this.height != this.b.getSize().height - (((this.a | this.H) & !a.a.a.a.l.A) ? this.k : 0) - (((this.a | this.H) & a.a.a.a.l.A) ? 22 : 0) | this.N)) {
                this.width = this.b.getSize().width;
                this.height = this.b.getSize().height - (((this.a | this.H) & !a.a.a.a.l.A) ? this.k : 0) - (((this.a | this.H) & a.a.a.a.l.A) ? 22 : 0);
                if (this.width <= 0 || this.height <= 0) {
                    // monitorexit(this.case)
                    return;
                }
                if (this.if != null) {
                    this.if.new();
                }
                if (this.E != null) {
                    this.E.a(("resize(" + this.width + "," + this.height + ");\u0000").toCharArray());
                    if (this.E.H != null) {
                        this.E.H.j();
                    }
                    this.E.long = true;
                }
                this.N = false;
                if (this.B != null) {
                    this.B.long = true;
                }
                b = true;
            }
            if (this.try && !this.g) {
                this.j.a(n);
                if (!this.g && this.j.k && System.currentTimeMillis() > this.E.L && this.E.L != 0L && this.j.new()) {
                    if (this.E != null) {
                        this.E.a();
                        this.L.b = this.p;
                        this.L.f();
                        this.E = null;
                    }
                    this.E = this.j;
                    this.j = null;
                    this.try = false;
                    this.L.for = false;
                    this.L.d();
                    this.E.long = true;
                    this.E.if(n);
                }
            }
            else if (this.try && this.g && System.currentTimeMillis() > this.E.L && this.E.L != 0L) {
                this.try = false;
                this.j = null;
                this.L.for = false;
                this.L.f();
            }
            if (this.E != null) {
                boolean b2 = b | this.E.a(n);
                for (int i = 0; i < this.void; ++i) {
                    this.m[i].do(n);
                    if (!this.m[i].S) {
                        if (!this.m[i].z) {
                            this.m[i].if(true);
                        }
                        if (this.m[i].z) {
                            this.m[i].S = true;
                            this.for(this.m[i].M);
                        }
                    }
                    else if (!this.m[i].z) {
                        this.m[i].if(true);
                    }
                }
                if (this.E.h && !this.try) {
                    if (this.B == null && this.f == null) {
                        final String parameter = this.getParameter("panorama");
                        if (parameter != null) {
                            this.f = new m(this.p, this, false);
                            this.f.M = parameter;
                            this.f.if(this.char.a(parameter, null, false, false, false));
                        }
                    }
                    else if (this.f != null && !this.f.h && this.f.if(false)) {
                        if (this.f.X) {
                            this.f = null;
                        }
                        else {
                            if (!this.f.P) {
                                this.E.H.a(this.f);
                            }
                            if (this.B != null) {
                                if (this.f.R && this.B.N) {
                                    this.f.W.cE = this.B.W.cE - this.B.T + this.f.T;
                                    this.f.W.cx = 0.0f;
                                    this.f.W.cy = this.B.W.cy;
                                }
                                if (this.F.length() > 0) {
                                    this.I = this.B.try();
                                }
                            }
                            else if (this.F.length() > 0 && this.I == null) {
                                if (this.f.R && this.K) {
                                    this.f.W.cE = this.D - this.w + this.f.T;
                                    this.f.W.cx = 0.0f;
                                    this.f.W.cy = this.goto;
                                    this.K = false;
                                }
                                this.I = this.E.H.g();
                            }
                            else if (this.f.R && this.K) {
                                this.f.W.cE = this.D - this.w + this.f.T;
                                this.f.W.cx = 0.0f;
                                this.f.W.cy = this.goto;
                                this.K = false;
                            }
                            if (this.I != null) {
                                try {
                                    String s = null;
                                    if (this.p.e != null && this.F.toLowerCase().startsWith("trans")) {
                                        s = this.p.e.getProperty(this.F.toLowerCase());
                                    }
                                    if (s == null) {
                                        s = "com.immervision.pure.player." + this.F;
                                    }
                                    final ak ak = (ak)Class.forName(s).getConstructor((Class<?>[])new Class[0]).newInstance((Object[])null);
                                    if (ak != null) {
                                        this.f.W.a(ak);
                                    }
                                    else {
                                        System.out.println("Can't load transition: " + this.F);
                                    }
                                }
                                catch (Exception ex) {
                                    System.out.println("Can't load transition: " + this.F);
                                }
                                this.f.W.if(this.I);
                                this.I = null;
                            }
                            this.f.for(n);
                            if (this.B != null) {
                                this.B.if("enddisplay();\u0000".toCharArray());
                                this.E.a(("enddisplay(\"" + this.B.M + "\");\u0000").toCharArray());
                                this.B.a();
                                this.B = null;
                            }
                            this.B = this.f;
                            this.f = null;
                            b2 = true;
                            this.E.a(("startdisplay(\"" + this.B.M + "\");\u0000").toCharArray());
                        }
                    }
                    if (this.f != null) {
                        this.f.do(n);
                    }
                }
                if (b2 || this.e) {
                    this.E.for();
                    if (this.B != null && this.B.O) {
                        this.a(this.n, this.d, this.do);
                    }
                    this.e = false;
                    this.if.for();
                }
            }
        }
        // monitorexit(this.case)
    }
    
    public void repaint() {
        this.e = true;
    }
    
    public void update(final Graphics graphics) {
        this.e = true;
    }
    
    public void paint(final Graphics graphics) {
        this.e = true;
    }
    
    public String execpano(final String s) {
        synchronized (this.case) {
            if (this.B != null && this.B.z) {
                // monitorexit(this.case)
                return al.for(this.B.if((String.valueOf(s) + "\u0000").toCharArray()));
            }
        }
        // monitorexit(this.case)
        return "";
    }
    
    public String execgui(final String s) {
        synchronized (this.case) {
            if (this.E != null && this.E.new()) {
                // monitorexit(this.case)
                return al.for(this.E.a((String.valueOf(s) + "\u0000").toCharArray()));
            }
        }
        // monitorexit(this.case)
        return "";
    }
    
    public a3 new(final String s) {
        synchronized (this.case) {
            if (this.B != null && this.B.z) {
                // monitorexit(this.case)
                return this.B.if((String.valueOf(s) + "\u0000").toCharArray());
            }
            // monitorexit(this.case)
            return new a3();
        }
    }
    
    public a3 if(final String s) {
        synchronized (this.case) {
            if (this.E != null && this.E.new()) {
                // monitorexit(this.case)
                return this.E.a((String.valueOf(s) + "\u0000").toCharArray());
            }
            // monitorexit(this.case)
            return new a3();
        }
    }
    
    public void if(final String m, final ae ae) {
        for (int i = 0; i < this.void; ++i) {
            if (m.compareTo(this.m[i].M) == 0) {
                this.m[i].S = false;
                this.m[i].if(true);
                return;
            }
        }
        if (this.m == null || this.m.length == this.void) {
            final m[] j = new m[this.void + 10];
            for (int k = 0; k < this.void; ++k) {
                j[k] = this.m[k];
            }
            this.m = j;
        }
        if (this.B != null && m.compareTo(this.B.M) == 0) {
            this.m[this.void] = this.B;
            this.m[this.void].S = false;
            ++this.void;
            this.B.if(true);
            return;
        }
        this.m[this.void] = new m(this.p, this, false);
        this.m[this.void].M = m;
        this.m[this.void].if(this.char.a(m, ae, false, false, false));
        ++this.void;
    }
    
    public boolean do(final String s) {
        for (int i = 0; i < this.void; ++i) {
            if (this.m[i].M.compareTo(s) == 0) {
                for (int j = i; j < this.void - 1; ++j) {
                    this.m[j] = this.m[j + 1];
                }
                --this.void;
                return true;
            }
        }
        return false;
    }
    
    public void for(final String s) {
        this.E.a(("ispreloaded(\"" + s + "\")\u0000").toCharArray());
        if (this.B != null) {
            this.B.if(("ispreloaded(" + s + ")\u0000").toCharArray());
        }
    }
    
    public void case(final String s) {
        this.E.a(("loadfinished(\"" + s + "\")\u0000").toCharArray());
        if (this.B != null) {
            this.B.if("loadfinished()\u0000".toCharArray());
        }
    }
    
    public boolean try(final String s) {
        int i = 0;
        while (i < this.void) {
            if (this.m[i].M.compareTo(s) == 0) {
                if (this.m[i].z) {
                    this.f = this.m[i];
                    this.do(s);
                    return true;
                }
                return false;
            }
            else {
                ++i;
            }
        }
        return false;
    }
    
    public void a(final String m, final ae ae) {
        for (int i = 0; i < this.void; ++i) {
            if (m.compareTo(this.m[i].M) == 0) {
                this.m[i].if(true);
                return;
            }
        }
        if (this.m == null || this.m.length == this.void) {
            final m[] j = new m[this.void + 10];
            for (int k = 0; k < this.void; ++k) {
                j[k] = this.m[k];
            }
            this.m = j;
        }
        this.m[this.void] = new m(this.p, this, false);
        this.m[this.void].M = m;
        this.m[this.void].if(this.char.a(m, ae, false, false, false));
        ++this.void;
    }
    
    public void byte(final String s) {
        this.E.a(("ispreloaded(\"" + s + "\")\u0000").toCharArray());
        if (this.B != null) {
            this.B.if(("ispreloaded(" + s + ")\u0000").toCharArray());
        }
    }
    
    private void if() {
        if (this.i == null) {
            return;
        }
        if (this.f != null || (this.B != null && !this.B.Z)) {
            this.i = null;
            this.else = null;
            return;
        }
        if (!this.p.k) {
            this.char.do();
        }
        if (this.p.K && this.B != null) {
            if (this.F != null && this.F.length() > 0) {
                this.I = this.B.try();
            }
            this.B.if("enddisplay();\u0000".toCharArray());
            this.E.a(("enddisplay(\"" + this.B.M + "\");\u0000").toCharArray());
            if (this.B.N) {
                this.w = this.B.T;
                this.D = this.B.W.cE;
                this.goto = this.B.W.cy;
                this.K = true;
            }
            this.B.a();
            this.B = null;
            this.E.H.h();
            System.gc();
        }
        (this.f = new m(this.p, this, this.c)).if(this.char.a(this.i, this.else, false, false, false));
        this.i = null;
        this.else = null;
    }
    
    public void a(final String i, final ae else1, final boolean c) {
        this.i = i;
        this.else = else1;
        this.c = c;
    }
    
    public void a(final char[] array, final char[] array2) {
        final String s = new String(array, 0, a.a.a.a.g.a(array));
        try {
            final URL url = new URL(this.getDocumentBase(), s);
            final AppletContext appletContext = this.getAppletContext();
            if (array2 != null) {
                appletContext.showDocument(url, new String(array2, 0, a.a.a.a.g.a(array2)));
            }
            else {
                appletContext.showDocument(url);
            }
        }
        catch (Exception ex) {
            System.out.println("Can't open: " + s);
        }
    }
    
    public void a(final char[] array) {
        final String s = new String(array, 0, a.a.a.a.g.a(array));
        System.out.println(array);
        if (s != null) {
            try {
                if (a.a.a.a.l.C) {
                    this.getAppletContext().showDocument(new URL("javascript:" + s), "_self");
                }
                else {
                    final Class<?> forName;
                    final Class<?> clazz = forName = Class.forName("netscape.javascript.JSObject");
                    final String s2 = "getWindow";
                    final Class[] array2 = { null };
                    final int n = 0;
                    Class class$0;
                    if ((class$0 = a.a.a.a.l.class$0) == null) {
                        try {
                            class$0 = (a.a.a.a.l.class$0 = Class.forName("java.applet.Applet"));
                        }
                        catch (ClassNotFoundException ex) {
                            throw new NoClassDefFoundError(ex.getMessage());
                        }
                    }
                    array2[n] = class$0;
                    final Object invoke = forName.getMethod(s2, (Class[])array2).invoke(clazz, this);
                    final Class<?> clazz2 = clazz;
                    final String s3 = "eval";
                    final Class[] array3 = { null };
                    final int n2 = 0;
                    Class class$2;
                    if ((class$2 = a.a.a.a.l.class$1) == null) {
                        try {
                            class$2 = (a.a.a.a.l.class$1 = Class.forName("java.lang.String"));
                        }
                        catch (ClassNotFoundException ex2) {
                            throw new NoClassDefFoundError(ex2.getMessage());
                        }
                    }
                    array3[n2] = class$2;
                    clazz2.getMethod(s3, (Class[])array3).invoke(invoke, s);
                }
            }
            catch (Exception ex3) {
                System.out.println("Can't execute: " + s);
            }
        }
    }
    
    public a3 if(final char[] array) {
        a3 if1 = null;
        if (this.B != null) {
            if1 = this.B.if(array);
        }
        if (if1 != null) {
            return if1;
        }
        System.out.println("Error in the command sent to panorama script environment.");
        return new a3();
    }
    
    public a3 do(final char[] array) {
        a3 a = null;
        if (this.E != null) {
            a = this.E.a(array);
        }
        if (a != null) {
            return a;
        }
        System.out.println("Error in the command sent to gui script environment.");
        return new a3();
    }
    
    private void do() {
        if (this.p.au != this.P.for) {
            this.p.au = this.P.for;
            switch (this.P.for) {
                case 3: {
                    if (this.E != null && this.E.D.bK[0] != null) {
                        this.b.setCursor(this.E.D.bK[0]);
                        break;
                    }
                    this.b.setCursor(Cursor.getPredefinedCursor(0));
                    break;
                }
                case 0: {
                    if (this.E != null && this.E.D.bK[1] != null) {
                        this.b.setCursor(this.E.D.bK[1]);
                        break;
                    }
                    this.b.setCursor(Cursor.getPredefinedCursor(12));
                    break;
                }
                case 1: {
                    if (this.E != null && this.E.D.bK[2] != null) {
                        this.b.setCursor(this.E.D.bK[2]);
                        break;
                    }
                    this.b.setCursor(Cursor.getPredefinedCursor(13));
                    break;
                }
                case 2: {
                    if (this.E != null && this.E.D.bK[3] != null) {
                        this.b.setCursor(this.E.D.bK[3]);
                        break;
                    }
                    this.b.setCursor(Cursor.getPredefinedCursor(9));
                    break;
                }
            }
        }
        for (int i = 0; i < this.P.byte; ++i) {
            if (this.P.c[i]) {
                this.B.if(this.P.b[i]);
            }
            else {
                this.E.a(this.P.b[i]);
            }
        }
    }
    
    public void destroy() {
        this.p.G = true;
        try {
            if (this.h != null) {
                this.h.dispose();
                this.h = null;
            }
            if (this.r != null) {
                this.r.dispose();
                this.r = null;
            }
            if (this.B != null) {
                this.B.a();
            }
            if (this.E != null) {
                this.E.a();
            }
            this.p.a();
            this.if.if();
            this.if = null;
            this.int.if();
            this.int = null;
            if (this.long != null) {
                this.long.if();
            }
            this.long = null;
            if (this.J != null) {
                if (this.new >= 4) {
                    ((Dialog)this.J).dispose();
                }
                else {
                    ((Window)this.J).dispose();
                }
            }
            this.J = null;
            this.P = null;
            if (this.m != null) {
                for (int i = 0; i < this.m.length; ++i) {
                    if (this.m[i] != null) {
                        this.m[i].a();
                    }
                }
            }
            this.char = null;
            if (this.m != null) {
                for (int j = 0; j < this.m.length; ++j) {
                    if (this.m[j] != null) {
                        this.m[j].a();
                    }
                    this.m[j] = null;
                }
            }
            this.m = null;
            if (this.f != null) {
                this.f.a();
            }
            this.f = null;
            this.L.if();
            this.L = null;
            this.b = null;
            this.J = null;
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
    
    private void a(final MouseEvent n, final String s, final int if1) {
        if (n == null) {
            return;
        }
        this.n = n;
        switch (if1) {
            case 1:
            case 2:
            case 3: {
                this.do = 3;
                this.d = "mousemoved";
                break;
            }
            case 0:
            case 4: {
                this.do = 4;
                this.d = "mousedragged";
                break;
            }
        }
        if (!this.O) {
            return;
        }
        int n2 = 0;
        if ((n.getModifiers() & 0x4) != 0x0) {
            n2 |= 0x4;
        }
        else if ((n.getModifiers() & 0x10) != 0x0) {
            n2 |= 0x1;
        }
        else if ((n.getModifiers() & 0x8) != 0x0) {
            n2 |= 0x2;
        }
        if (this.P != null) {
            this.P.a();
            this.P.goto = n.getX();
            this.P.else = n.getY();
        }
        if (s.compareTo("mousemoved") == 0) {
            final char[] charArray = (String.valueOf(s) + "(" + n.getX() + "," + n.getY() + ")\u0000").toCharArray();
            if (this.E != null && this.E.h) {
                this.E.a(charArray);
                if (this.B != null && this.E.H != null && this.E.H.for) {
                    this.B.if((String.valueOf(s) + "(" + (n.getX() - this.E.H.bh) + "," + (n.getY() - this.E.H.bg) + ")\u0000").toCharArray());
                }
            }
        }
        else if (s.compareTo("mouseexited") == 0) {
            if (this.P != null) {
                this.P.goto = -65536;
                this.P.else = -65536;
            }
            final char[] charArray2 = "mouseexited()\u0000".toCharArray();
            if (this.E != null && this.E.h) {
                this.E.a(charArray2);
                if (this.B != null && this.E.H.for) {
                    this.B.if(charArray2);
                }
            }
        }
        else {
            final char[] charArray3 = (String.valueOf(s) + "(" + n.getX() + "," + n.getY() + "," + n2 + ")\u0000").toCharArray();
            if (this.E != null && this.E.h) {
                this.E.a(charArray3);
                if (this.B != null && this.E.H != null && this.E.H.for) {
                    this.B.if((String.valueOf(s) + "(" + (n.getX() - this.E.H.bh) + "," + (n.getY() - this.E.H.bg) + "," + n2 + ")\u0000").toCharArray());
                }
            }
        }
        if (this.P == null) {
            return;
        }
        if ((n.getModifiers() & 0x4) != 0x0) {
            this.P.g = 5;
        }
        else if ((n.getModifiers() & 0x10) != 0x0) {
            this.P.g = 6;
        }
        else if ((n.getModifiers() & 0x8) != 0x0) {
            this.P.g = 7;
        }
        if (this.l) {
            this.P.g = 5;
        }
        if (this.q) {
            return;
        }
        this.P.if = if1;
        if (this.E != null) {
            this.E.if(this.P);
        }
        this.do();
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        synchronized (this.case) {
            if (!this.O) {
                // monitorexit(this.case)
                return;
            }
            final char[] charArray = "mouseentered()\u0000".toCharArray();
            if (this.E != null && this.E.h) {
                this.E.a(charArray);
                if (this.B != null && this.E.H.for) {
                    this.B.if(charArray);
                }
            }
        }
        // monitorexit(this.case)
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        synchronized (this.case) {
            if (!this.O) {
                // monitorexit(this.case)
                return;
            }
            this.a(mouseEvent, "mouseExited", 3);
        }
        // monitorexit(this.case)
    }
    
    public void a(final int n) {
        synchronized (this.case) {
            if (!this.O) {
                // monitorexit(this.case)
                return;
            }
            if (this.B != null) {
                char[] array;
                if (n > 0) {
                    array = "mousewheel(1)\u0000".toCharArray();
                }
                else {
                    array = "mousewheel(-1)\u0000".toCharArray();
                }
                if (this.E != null && this.E.h) {
                    this.E.a(array);
                    if (this.B != null && this.E.H.for) {
                        this.B.if(array);
                    }
                }
                if (this.q) {
                    // monitorexit(this.case)
                    return;
                }
                if (n < 0) {
                    this.B.W.cy -= this.B.W.cy * 0.1f;
                }
                else {
                    this.B.W.cy += this.B.W.cy * 0.1f;
                }
                this.B.W.do = true;
            }
        }
        // monitorexit(this.case)
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
        synchronized (this.case) {
            if (!this.O) {
                // monitorexit(this.case)
                return;
            }
            final char[] charArray = ("keytyped(" + keyEvent.getKeyCode() + ")\u0000").toCharArray();
            if (this.E != null && this.E.h) {
                this.E.a(charArray);
                if (this.B != null && this.E.H.for) {
                    this.B.if(charArray);
                }
            }
        }
        // monitorexit(this.case)
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        synchronized (this.case) {
            if (!this.O) {
                // monitorexit(this.case)
                return;
            }
            final char[] charArray = ("keypressed(" + keyEvent.getKeyCode() + ")\u0000").toCharArray();
            if (this.E != null && this.E.h) {
                this.E.a(charArray);
                if (this.B != null && this.E.H.for) {
                    this.B.if(charArray);
                }
            }
            if (this.u) {
                // monitorexit(this.case)
                return;
            }
            if (keyEvent.getKeyCode() == 17 || keyEvent.getKeyCode() == 16) {
                this.l = true;
            }
            if (keyEvent.getKeyCode() == 112 || keyEvent.getKeyCode() == 156) {
                this.int("");
            }
            if (keyEvent.getKeyCode() == 113 || keyEvent.getKeyCode() == 36 || keyEvent.getKeyCode() == 154) {
                this.int();
            }
        }
        // monitorexit(this.case)
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
        synchronized (this.case) {
            if (!this.O) {
                // monitorexit(this.case)
                return;
            }
            final char[] charArray = ("keyreleased(" + keyEvent.getKeyCode() + ")\u0000").toCharArray();
            if (this.E != null && this.E.h) {
                this.E.a(charArray);
                if (this.B != null && this.E.H.for) {
                    this.B.if(charArray);
                }
            }
            if (this.u) {
                this.l = false;
                // monitorexit(this.case)
                return;
            }
            if (keyEvent.getKeyCode() == 17 || keyEvent.getKeyCode() == 16) {
                this.l = false;
            }
        }
        // monitorexit(this.case)
    }
    
    public void a(final boolean visible) {
        this.N = true;
        try {
            if (this.a || this.H) {
                if (this.J == null || this.long.z != this.a || this.long.y != this.H) {
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
                    if (this.new >= 4) {
                        this.J = new a4(frame, this);
                        ((a4)this.J).setUndecorated(true);
                    }
                    else {
                        this.J = new q(frame, this);
                    }
                    int width = this.J.getToolkit().getScreenSize().width;
                    final int height = this.J.getToolkit().getScreenSize().height;
                    if (this.H) {
                        width *= 2;
                    }
                    if (a.a.a.a.l.z) {
                        this.J.setLocation(0, 22);
                    }
                    else {
                        this.J.setLocation(0, 0);
                    }
                    this.J.setSize(width, height);
                    if (a.a.a.a.l.z) {
                        this.J.reshape(0, 22, width, height + (a.a.a.a.l.A ? 0 : this.k));
                    }
                    else {
                        this.J.reshape(0, 0, width, height + (a.a.a.a.l.A ? 0 : this.k));
                    }
                    this.width = this.J.getSize().width;
                    this.height = this.J.getSize().height - (((this.a | this.H) & !a.a.a.a.l.A) ? this.k : 0) - (((this.a | this.H) & a.a.a.a.l.A) ? 22 : 0);
                    this.J.addMouseMotionListener(this);
                    this.J.addMouseListener(this);
                    this.J.addKeyListener(this);
                    this.J.setVisible(true);
                    ((Window)this.J).toFront();
                    this.J.requestFocus();
                    if (this.new >= 4) {
                        try {
                            new am(this, this.J);
                        }
                        catch (Throwable t) {}
                    }
                    if (this.long == null || this.long.z != this.a || this.long.y != this.H) {
                        Label_0587: {
                            if (this.new >= 3) {
                                try {
                                    (this.long = new bj()).a(this.J, true, this);
                                    break Label_0587;
                                }
                                catch (Exception ex2) {
                                    ac.ah = false;
                                    this.long = new bb();
                                    try {
                                        this.long.a(this.J, true, this);
                                    }
                                    catch (Exception ex3) {}
                                    break Label_0587;
                                }
                            }
                            this.long = new bb();
                            try {
                                this.long.a(this.J, true, this);
                            }
                            catch (Exception ex4) {}
                        }
                        if (this.E != null && this.E.H != null) {
                            this.E.H.j();
                        }
                        if (this.E != null && this.B != null) {
                            this.B.W.a(this.E.H.aO);
                        }
                    }
                }
                this.J.setVisible(visible);
                this.b = this.J;
                this.if = this.long;
                this.if.z = this.a;
                this.if.y = this.H;
                this.p.goto = this.if;
            }
            else {
                if ((this.a || this.H) && this.if != null) {
                    return;
                }
                if (this.J != null) {
                    final int n = this.if.s * this.if.r;
                    try {
                        for (int i = 0; i < n; ++i) {
                            this.if.x[i] = -1;
                        }
                    }
                    catch (Exception ex5) {}
                    this.J.setVisible(false);
                }
                if (this.int == null) {
                    this.for();
                }
                this.b = this;
                this.if = this.int;
            }
            if (this.B != null) {
                this.B.long = true;
            }
            if (this.E != null) {
                this.E.long = true;
            }
            this.width = this.b.getSize().width;
            this.height = this.b.getSize().height - (((this.a | this.H) & !a.a.a.a.l.A) ? this.k : 0) - (((this.a | this.H) & a.a.a.a.l.A) ? 22 : 0);
            this.if.new();
        }
        catch (OutOfMemoryError outOfMemoryError) {
            this.a = false;
            this.H = false;
            if (this.int == null) {
                this.for();
            }
            if (this.J != null) {
                if (this.new >= 4) {
                    ((Dialog)this.J).dispose();
                }
                else {
                    ((Window)this.J).dispose();
                }
            }
            this.J = null;
            this.long = null;
            System.gc();
            if (this.int == null) {
                this.for();
            }
            this.b = this;
            this.if = this.int;
            System.out.println("Can't go fullscreen. Out of memory.");
        }
        this.width = this.b.getSize().width;
        this.height = this.b.getSize().height - (((this.a | this.H) & !a.a.a.a.l.A) ? this.k : 0) - (((this.a | this.H) & a.a.a.a.l.A) ? 22 : 0);
        this.p.goto = this.if;
    }
    
    private void for() {
        this.b = this;
        Label_0116: {
            if (this.int == null) {
                if (this.new >= 3) {
                    try {
                        (this.int = new bj()).a(this.b, false, this);
                        break Label_0116;
                    }
                    catch (Exception ex) {
                        ac.ah = false;
                        this.int = null;
                        this.int = new bb();
                        try {
                            this.int.a(this.b, false, this);
                        }
                        catch (Exception ex2) {}
                        break Label_0116;
                    }
                }
                this.int = new bb();
                try {
                    this.int.a(this.b, false, this);
                }
                catch (Exception ex3) {}
            }
        }
        this.if = this.int;
    }
    
    protected void new() {
        final byte[] array = { 73, 109, 77, 101, 82, 118, 73, 115, 73, 111, 78, 32, 80, 117, 82, 101 };
        final InputStream resourceAsStream = this.getClass().getResourceAsStream("a.gif");
        if (resourceAsStream != null) {
            try {
                int n = 0;
                for (int i = resourceAsStream.read(); i != -1; i = resourceAsStream.read()) {
                    ++n;
                }
                resourceAsStream.close();
                final InputStream resourceAsStream2 = this.getClass().getResourceAsStream("a.gif");
                final byte[] array2 = new byte[n];
                for (int j = 0; j < n; ++j) {
                    array2[j] = (byte)resourceAsStream2.read();
                    array2[j] ^= array[j & 0xF];
                }
                resourceAsStream2.close();
                final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(array2);
                try {
                    final Properties e = new Properties();
                    e.load(byteArrayInputStream);
                    this.p.e = e;
                }
                catch (Exception ex) {}
                byteArrayInputStream.close();
            }
            catch (Exception ex2) {}
        }
    }
    
    protected void a(final String s) {
        this.p.B.a(s);
    }
    
    public Image a() {
        if (this.if != null) {
            return this.if.C;
        }
        return null;
    }
}
