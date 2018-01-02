// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

import java.awt.Image;
import java.io.InputStream;
import java.util.Properties;
import java.io.ByteArrayInputStream;
import java.awt.Dialog;
import java.awt.event.KeyEvent;
import java.awt.Window;
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
    protected boolean else;
    private i if;
    private i int;
    private i void;
    protected ab R;
    protected m[] o;
    protected int b;
    protected be char;
    protected v G;
    protected v l;
    protected m D;
    protected m g;
    protected ac r;
    protected o N;
    protected Thread I;
    protected int width;
    protected int height;
    protected boolean s;
    protected boolean w;
    protected long S;
    private boolean z;
    protected static final String u = "1.14.2.0";
    protected String O;
    protected Component c;
    protected Component L;
    protected boolean a;
    protected boolean J;
    protected int q;
    protected boolean n;
    public int new;
    protected bq t;
    protected bg i;
    protected static boolean E;
    protected static boolean C;
    protected static boolean B;
    protected float for;
    protected boolean P;
    protected String H;
    protected boolean Q;
    protected Object case;
    private boolean f;
    private String k;
    private ae goto;
    private boolean d;
    private boolean byte;
    protected boolean h;
    protected boolean try;
    protected long v;
    protected ad K;
    private int j;
    private int m;
    private float A;
    private float F;
    private float long;
    private boolean M;
    MouseEvent p;
    String e;
    int do;
    static /* synthetic */ Class class$0;
    static /* synthetic */ Class class$1;
    
    static {
        l.E = false;
        l.C = false;
        l.B = false;
    }
    
    public l() {
        this.else = true;
        this.if = null;
        this.int = null;
        this.void = null;
        this.R = new ab();
        this.o = null;
        this.b = 0;
        this.char = null;
        this.G = null;
        this.l = null;
        this.D = null;
        this.g = null;
        this.r = null;
        this.N = null;
        this.s = false;
        this.w = false;
        this.S = 0L;
        this.z = false;
        this.O = "ImmerVision PURE Player PRO for Java 1.14.2.0";
        this.c = this;
        this.L = null;
        this.a = false;
        this.J = false;
        this.q = 1;
        this.n = false;
        this.new = 0;
        this.t = null;
        this.i = null;
        this.for = 1.0f;
        this.P = false;
        this.H = "";
        this.Q = false;
        this.case = new Object();
        this.f = false;
        this.k = null;
        this.goto = null;
        this.d = false;
        this.byte = false;
        this.h = false;
        this.try = true;
        this.v = 0L;
        this.K = null;
        this.j = 22;
        this.m = 8;
        this.A = 0.0f;
        this.F = 0.0f;
        this.long = 0.0f;
        this.M = false;
        this.p = null;
        this.e = "";
        this.do = 0;
    }
    
    public String getAppletInfo() {
        return this.O;
    }
    
    public void init() {
        this.r = new ac();
        this.r.case = this;
        System.out.println(this.O);
        try {
            final String property = System.getProperty("java.version");
            this.new = property.charAt(2) - '0';
            System.out.println("Java Version: " + property + " from " + System.getProperty("java.vendor"));
            System.out.println("Operating system: " + System.getProperty("os.name") + " version " + System.getProperty("os.version") + " on " + System.getProperty("os.arch"));
            if (System.getProperty("os.name").toLowerCase().startsWith("mac")) {
                a.a.a.a.l.E = true;
                if (this.new == 3) {
                    a.a.a.a.l.C = true;
                }
                if (this.new >= 4) {
                    a.a.a.a.l.C = true;
                    a.a.a.a.l.B = true;
                }
            }
            else if (this.new == 6) {
                final int n = property.indexOf(95) + 1;
                if (n > 1) {
                    final int int1 = Integer.parseInt(property.substring(n));
                    if (int1 >= 3 && int1 < 10) {
                        this.m = 24;
                    }
                    else if (int1 >= 10) {
                        this.m = 0;
                    }
                }
            }
        }
        catch (Exception ex) {}
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        this.addKeyListener(this);
        this.c = this;
        this.width = this.getSize().width;
        this.height = this.getSize().height;
        this.r.L = this;
        this.r.I = this.getToolkit();
        this.r.W = new ap();
        this.r.aa = new av(this.r);
        this.r.P = this;
        this.r.if = new MediaTracker(this);
        this.r.s = new au();
        ac.aj = this.new;
        this.goto();
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
            this.J = true;
        }
        if (!this.J) {
            final String parameter5 = this.getParameter("fullscreen");
            if (parameter5 != null && parameter5.toLowerCase().compareTo("true") == 0) {
                this.a = true;
            }
        }
        final String parameter6 = this.getParameter("protectfullscreen");
        if (parameter6 != null && parameter6.toLowerCase().compareTo("true") == 0) {
            this.byte = true;
        }
        this.case();
        if (this.byte && !this.a && !this.J) {
            this.a = true;
            this.do(false);
            this.a = false;
        }
        this.do(true);
    }
    
    public void char() {
        if (this.i == null) {
            Container container;
            for (container = this.getParent(); !(container instanceof Frame); container = container.getParent()) {}
            final Frame frame = (Frame)container;
            if (this.D != null) {
                this.i = new bg(this, frame, "1.14.2.0", this.G.e, this.G.new, this.G.s, this.D.e, this.D.new, this.D.s);
            }
            else {
                this.i = new bg(this, frame, "1.14.2.0", this.G.e, this.G.new, this.G.s, "", "", "");
            }
        }
        else {
            this.i.toFront();
        }
    }
    
    public void int(final String s) {
        if (this.t == null) {
            Container container;
            for (container = this.getParent(); !(container instanceof Frame); container = container.getParent()) {}
            this.t = new bq((Frame)container, "1.14.2.0", this, s);
        }
        else {
            this.t.toFront();
        }
    }
    
    public void start() {
        if (this.G != null) {
            return;
        }
        this.if.new();
        this.N = new o();
        this.char = new be(this.getDocumentBase(), this.N, this.r);
        this.r.B = this.char;
        this.N.b = this.r;
        this.G = new v(this.r, this.N, this);
        final String parameter = this.getParameter("optimizememory");
        if (parameter != null && parameter.toLowerCase().compareTo("true") == 0) {
            System.out.println("Memory optimization enabled.");
            this.r.T = true;
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
                this.r.ar = floatValue;
                System.out.println("Quality set to " + parameter2);
            }
            catch (Exception ex2) {
                this.r.ar = 100.0f;
                System.out.println("Error in Quality parameter. Quality is set to default.");
            }
        }
        final String parameter3 = this.getParameter("lockzenithnadir");
        if (parameter3 != null && parameter3.toLowerCase().compareTo("true") == 0) {
            System.out.println("Lock camera movements at zenith and nadir.");
            this.r.for = true;
        }
        final String parameter4 = this.getParameter("singlepanorama");
        if (parameter4 != null && parameter4.toLowerCase().compareTo("true") == 0) {
            System.out.println("Single panorama mode enabled.");
            this.r.K = true;
        }
        final String parameter5 = this.getParameter("usecache");
        if (parameter5 != null) {
            if (parameter5.toLowerCase().compareTo("true") == 0) {
                this.r.k = true;
            }
            else if (parameter5.toLowerCase().compareTo("smart") == 0 && ac.aj < 3) {
                this.r.k = true;
            }
        }
        else if (ac.aj < 3) {
            this.r.k = true;
        }
        System.out.println("Internal cache system " + (this.r.k ? "enabled" : "disabled") + ".");
        final String parameter6 = this.getParameter("antialiasing");
        if (parameter6 != null) {
            if (parameter6.toLowerCase().compareTo("none") == 0) {
                System.out.println("No antialiasing.");
                this.q = 0;
            }
            else if (parameter6.toLowerCase().compareTo("everytime") == 0) {
                System.out.println("Full antialiasing.");
                this.q = 2;
            }
            else {
                System.out.println("Antialiasing on stop.");
            }
        }
        else {
            System.out.println("Antialiasing on stop.");
        }
        if (this.z) {
            this.G.a(this.char.a(this.getParameter(new String(ac.p)), null, false, false, false));
            this.try = false;
        }
        else {
            this.G.a(this.char.a(new String(ac.Y), null, false, false, false));
        }
        while (!this.G.k || !this.G.new()) {
            try {
                Thread.sleep(100L);
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            this.G.a(System.currentTimeMillis());
        }
        if (!this.z) {
            final String parameter7 = this.getParameter("gui");
            this.N.f();
            if (parameter7 == null) {
                this.h = true;
            }
            else {
                (this.l = new v(this.r, this.N, this)).a(this.char.a(parameter7, null, false, false, false));
            }
        }
        final String parameter8 = this.getParameter("transition");
        if (parameter8 != null) {
            this.H = parameter8;
        }
        this.N.d();
        this.G.if(System.currentTimeMillis());
        this.N.a((int)this.G.L);
        this.v = System.currentTimeMillis();
        this.G.L += this.v;
        this.S = System.currentTimeMillis();
        this.G.long = true;
        (this.I = new Thread(this)).start();
        this.Q = true;
    }
    
    public void run() {
        while (!this.r.G) {
            Thread.yield();
            long n = System.currentTimeMillis();
            try {
                if (n - this.S < 40L) {
                    Thread.sleep(this.S + 40L - n);
                }
                n = System.currentTimeMillis();
                if (this.try) {
                    this.N.by += (int)(n - this.v);
                    this.v = n;
                }
                this.S = n;
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            try {
                this.a(n);
            }
            catch (NullPointerException ex2) {}
            catch (OutOfMemoryError outOfMemoryError) {
                this.new("Out of memory.");
                this.width = this.c.getSize().width;
                this.height = this.c.getSize().height - (((this.a | this.J) & !a.a.a.a.l.C) ? this.m : 0) - (((this.a | this.J) & a.a.a.a.l.C) ? this.j : 0);
                this.r.goto = this.if;
            }
        }
    }
    
    private void a(final long n) {
        boolean b = false;
        if (this.r.L.getGraphics() == null) {
            return;
        }
        synchronized (this.case) {
            this.do();
            if (this.width != this.c.getSize().width || (this.height != this.c.getSize().height - (((this.a | this.J) & !a.a.a.a.l.C) ? this.m : 0) - (((this.a | this.J) & a.a.a.a.l.C) ? this.j : 0) | this.P)) {
                this.width = this.c.getSize().width;
                this.height = this.c.getSize().height - (((this.a | this.J) & !a.a.a.a.l.C) ? this.m : 0) - (((this.a | this.J) & a.a.a.a.l.C) ? this.j : 0);
                if (this.width <= 0 || this.height <= 0) {
                    // monitorexit(this.case)
                    return;
                }
                if (this.if != null) {
                    this.if.new();
                }
                if (this.G != null) {
                    this.G.a(("resize(" + this.width + "," + this.height + ");\u0000").toCharArray());
                    if (this.G.H != null) {
                        this.G.H.j();
                    }
                    this.G.long = true;
                }
                this.P = false;
                if (this.D != null) {
                    this.D.long = true;
                }
                b = true;
            }
            if (this.try && !this.h) {
                this.l.a(n);
                if (!this.h && this.l.k && System.currentTimeMillis() > this.G.L && this.G.L != 0L && this.l.new()) {
                    if (this.G != null) {
                        this.G.a();
                        this.N.b = this.r;
                        this.N.f();
                        this.G = null;
                    }
                    this.G = this.l;
                    this.l = null;
                    this.try = false;
                    this.N.for = false;
                    this.N.d();
                    this.G.long = true;
                    this.G.if(n);
                }
            }
            else if (this.try && this.h && System.currentTimeMillis() > this.G.L && this.G.L != 0L) {
                this.try = false;
                this.l = null;
                this.N.for = false;
                this.N.f();
            }
            if (this.G != null) {
                boolean b2 = b | this.G.a(n);
                for (int i = 0; i < this.b; ++i) {
                    this.o[i].do(n);
                    if (!this.o[i].S) {
                        if (!this.o[i].z) {
                            this.o[i].if(true);
                        }
                        if (this.o[i].z) {
                            this.o[i].S = true;
                            this.for(this.o[i].M);
                        }
                    }
                    else if (!this.o[i].z) {
                        this.o[i].if(true);
                    }
                }
                if (this.G.h && !this.try) {
                    if (this.D == null && this.g == null) {
                        final String parameter = this.getParameter("panorama");
                        if (parameter != null) {
                            this.g = new m(this.r, this, false);
                            this.g.M = parameter;
                            this.g.if(this.char.a(parameter, null, false, false, false));
                        }
                    }
                    else if (this.g != null && !this.g.h && this.g.if(false)) {
                        if (this.g.X) {
                            this.g = null;
                        }
                        else {
                            if (!this.g.P) {
                                this.G.H.a(this.g);
                            }
                            if (this.D != null) {
                                if (this.g.R && this.D.N) {
                                    this.g.W.cE = this.D.W.cE - this.D.T + this.g.T;
                                    this.g.W.cx = 0.0f;
                                    this.g.W.cy = this.D.W.cy;
                                }
                                if (this.H.length() > 0) {
                                    this.K = this.D.try();
                                }
                            }
                            else if (this.H.length() > 0 && this.K == null) {
                                if (this.g.R && this.M) {
                                    this.g.W.cE = this.F - this.A + this.g.T;
                                    this.g.W.cx = 0.0f;
                                    this.g.W.cy = this.long;
                                    this.M = false;
                                }
                                this.K = this.G.H.g();
                            }
                            else if (this.g.R && this.M) {
                                this.g.W.cE = this.F - this.A + this.g.T;
                                this.g.W.cx = 0.0f;
                                this.g.W.cy = this.long;
                                this.M = false;
                            }
                            if (this.K != null) {
                                try {
                                    String s = null;
                                    if (this.r.e != null && this.H.toLowerCase().startsWith("trans")) {
                                        s = this.r.e.getProperty(this.H.toLowerCase());
                                    }
                                    if (s == null) {
                                        s = "com.immervision.pure.player." + this.H;
                                    }
                                    final ak ak = (ak)Class.forName(s).getConstructor((Class<?>[])new Class[0]).newInstance((Object[])null);
                                    if (ak != null) {
                                        this.g.W.a(ak);
                                    }
                                    else {
                                        System.out.println("Can't load transition: " + this.H);
                                    }
                                }
                                catch (Exception ex) {
                                    System.out.println("Can't load transition: " + this.H);
                                }
                                this.g.W.if(this.K);
                                this.K = null;
                            }
                            this.g.for(n);
                            if (this.D != null) {
                                this.D.if("enddisplay();\u0000".toCharArray());
                                this.G.a(("enddisplay(\"" + this.D.M + "\");\u0000").toCharArray());
                                this.D.a();
                                this.D = null;
                            }
                            this.D = this.g;
                            this.g = null;
                            b2 = true;
                            this.G.a(("startdisplay(\"" + this.D.M + "\");\u0000").toCharArray());
                        }
                    }
                    if (this.g != null) {
                        this.g.do(n);
                    }
                }
                if (b2 || this.f) {
                    this.G.for();
                    if (this.D != null && this.D.O) {
                        this.a(this.p, this.e, this.do);
                    }
                    this.f = false;
                    this.if.for();
                }
            }
        }
        // monitorexit(this.case)
    }
    
    public void repaint() {
        this.f = true;
    }
    
    public void update(final Graphics graphics) {
        this.f = true;
    }
    
    public void paint(final Graphics graphics) {
        this.f = true;
    }
    
    public String execpano(final String s) {
        synchronized (this.case) {
            if (this.D != null && this.D.z) {
                // monitorexit(this.case)
                return al.for(this.D.if((String.valueOf(s) + "\u0000").toCharArray()));
            }
        }
        // monitorexit(this.case)
        return "";
    }
    
    public String execgui(final String s) {
        synchronized (this.case) {
            if (this.G != null && this.G.new()) {
                // monitorexit(this.case)
                return al.for(this.G.a((String.valueOf(s) + "\u0000").toCharArray()));
            }
        }
        // monitorexit(this.case)
        return "";
    }
    
    public a3 try(final String s) {
        synchronized (this.case) {
            if (this.D != null && this.D.z) {
                // monitorexit(this.case)
                return this.D.if((String.valueOf(s) + "\u0000").toCharArray());
            }
            // monitorexit(this.case)
            return new a3();
        }
    }
    
    public a3 if(final String s) {
        synchronized (this.case) {
            if (this.G != null && this.G.new()) {
                // monitorexit(this.case)
                return this.G.a((String.valueOf(s) + "\u0000").toCharArray());
            }
            // monitorexit(this.case)
            return new a3();
        }
    }
    
    public void if(final String m, final ae ae) {
        for (int i = 0; i < this.b; ++i) {
            if (m.compareTo(this.o[i].M) == 0) {
                this.o[i].S = false;
                this.o[i].if(true);
                return;
            }
        }
        if (this.o == null || this.o.length == this.b) {
            final m[] o = new m[this.b + 10];
            for (int j = 0; j < this.b; ++j) {
                o[j] = this.o[j];
            }
            this.o = o;
        }
        if (this.D != null && m.compareTo(this.D.M) == 0) {
            this.o[this.b] = this.D;
            this.o[this.b].S = false;
            ++this.b;
            this.D.if(true);
            return;
        }
        this.o[this.b] = new m(this.r, this, false);
        this.o[this.b].M = m;
        this.o[this.b].if(this.char.a(m, ae, false, false, false));
        ++this.b;
    }
    
    public boolean do(final String s) {
        for (int i = 0; i < this.b; ++i) {
            if (this.o[i].M.compareTo(s) == 0) {
                for (int j = i; j < this.b - 1; ++j) {
                    this.o[j] = this.o[j + 1];
                }
                --this.b;
                return true;
            }
        }
        return false;
    }
    
    public void for(final String s) {
        this.G.a(("ispreloaded(\"" + s + "\")\u0000").toCharArray());
        if (this.D != null) {
            this.D.if(("ispreloaded(" + s + ")\u0000").toCharArray());
        }
    }
    
    public void char(final String s) {
        this.G.a(("loadfinished(\"" + s + "\")\u0000").toCharArray());
        if (this.D != null) {
            this.D.if("loadfinished()\u0000".toCharArray());
        }
    }
    
    public boolean byte(final String s) {
        int i = 0;
        while (i < this.b) {
            if (this.o[i].M.compareTo(s) == 0) {
                if (this.o[i].z) {
                    this.g = this.o[i];
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
        for (int i = 0; i < this.b; ++i) {
            if (m.compareTo(this.o[i].M) == 0) {
                this.o[i].if(true);
                return;
            }
        }
        if (this.o == null || this.o.length == this.b) {
            final m[] o = new m[this.b + 10];
            for (int j = 0; j < this.b; ++j) {
                o[j] = this.o[j];
            }
            this.o = o;
        }
        this.o[this.b] = new m(this.r, this, false);
        this.o[this.b].M = m;
        this.o[this.b].if(this.char.a(m, ae, false, false, false));
        ++this.b;
    }
    
    public void case(final String s) {
        this.G.a(("ispreloaded(\"" + s + "\")\u0000").toCharArray());
        if (this.D != null) {
            this.D.if(("ispreloaded(" + s + ")\u0000").toCharArray());
        }
    }
    
    private void do() {
        if (this.k == null) {
            return;
        }
        if (this.g != null || (this.D != null && !this.D.Z)) {
            this.k = null;
            this.goto = null;
            return;
        }
        if (!this.r.k) {
            this.char.do();
        }
        if (this.r.K && this.D != null) {
            if (this.H != null && this.H.length() > 0) {
                this.K = this.D.try();
            }
            this.D.if("enddisplay();\u0000".toCharArray());
            this.G.a(("enddisplay(\"" + this.D.M + "\");\u0000").toCharArray());
            if (this.D.N) {
                this.A = this.D.T;
                this.F = this.D.W.cE;
                this.long = this.D.W.cy;
                this.M = true;
            }
            this.D.a();
            this.D = null;
            this.G.H.h();
            System.gc();
        }
        (this.g = new m(this.r, this, this.d)).if(this.char.a(this.k, this.goto, false, false, false));
        this.k = null;
        this.goto = null;
    }
    
    public void a(final String k, final ae goto1, final boolean d) {
        this.k = k;
        this.goto = goto1;
        this.d = d;
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
                if (a.a.a.a.l.E && this.new <= 3) {
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
        if (this.D != null) {
            if1 = this.D.if(array);
        }
        if (if1 != null) {
            return if1;
        }
        System.out.println("Error in the command sent to panorama script environment.");
        return new a3();
    }
    
    public a3 do(final char[] array) {
        a3 a = null;
        if (this.G != null) {
            a = this.G.a(array);
        }
        if (a != null) {
            return a;
        }
        System.out.println("Error in the command sent to gui script environment.");
        return new a3();
    }
    
    private void byte() {
        if (this.r.au != this.R.for) {
            this.r.au = this.R.for;
            switch (this.R.for) {
                case 3: {
                    if (this.G != null && this.G.D.bK[0] != null) {
                        this.c.setCursor(this.G.D.bK[0]);
                        break;
                    }
                    this.c.setCursor(Cursor.getPredefinedCursor(0));
                    break;
                }
                case 0: {
                    if (this.G != null && this.G.D.bK[1] != null) {
                        this.c.setCursor(this.G.D.bK[1]);
                        break;
                    }
                    this.c.setCursor(Cursor.getPredefinedCursor(12));
                    break;
                }
                case 1: {
                    if (this.G != null && this.G.D.bK[2] != null) {
                        this.c.setCursor(this.G.D.bK[2]);
                        break;
                    }
                    this.c.setCursor(Cursor.getPredefinedCursor(13));
                    break;
                }
                case 2: {
                    if (this.G != null && this.G.D.bK[3] != null) {
                        this.c.setCursor(this.G.D.bK[3]);
                        break;
                    }
                    this.c.setCursor(Cursor.getPredefinedCursor(9));
                    break;
                }
            }
        }
        for (int i = 0; i < this.R.byte; ++i) {
            if (this.R.c[i]) {
                this.D.if(this.R.b[i]);
            }
            else {
                this.G.a(this.R.b[i]);
            }
        }
    }
    
    public void destroy() {
        this.r.G = true;
        try {
            if (this.i != null) {
                this.i.dispose();
                this.i = null;
            }
            if (this.t != null) {
                this.t.dispose();
                this.t = null;
            }
            if (this.D != null) {
                this.D.a();
                this.D = null;
            }
            if (this.G != null) {
                this.G.a();
                this.G = null;
            }
            if (this.r != null) {
                this.r.a();
            }
            if (this.if != null) {
                this.if.if();
                this.if = null;
            }
            if (this.int != null) {
                this.int.if();
                this.int = null;
            }
            if (this.void != null) {
                this.void.if();
                this.void = null;
            }
            if (this.L != null) {
                ((Window)this.L).dispose();
                this.L = null;
            }
            this.R = null;
            if (this.o != null) {
                for (int i = 0; i < this.o.length; ++i) {
                    if (this.o[i] != null) {
                        this.o[i].a();
                    }
                }
            }
            this.char = null;
            if (this.o != null) {
                for (int j = 0; j < this.o.length; ++j) {
                    if (this.o[j] != null) {
                        this.o[j].a();
                    }
                    this.o[j] = null;
                }
            }
            this.o = null;
            if (this.g != null) {
                this.g.a();
                this.g = null;
            }
            if (this.N != null) {
                this.N.if();
                this.N = null;
            }
            this.c = null;
            this.L = null;
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
    
    private void a(final MouseEvent p3, final String s, final int if1) {
        if (p3 == null) {
            return;
        }
        this.p = p3;
        switch (if1) {
            case 1:
            case 2:
            case 3: {
                this.do = 3;
                this.e = "mousemoved";
                break;
            }
            case 0:
            case 4: {
                this.do = 4;
                this.e = "mousedragged";
                break;
            }
        }
        if (!this.Q) {
            return;
        }
        int n = 0;
        if ((p3.getModifiers() & 0x4) != 0x0) {
            n |= 0x4;
        }
        else if ((p3.getModifiers() & 0x10) != 0x0) {
            n |= 0x1;
        }
        else if ((p3.getModifiers() & 0x8) != 0x0) {
            n |= 0x2;
        }
        if (this.R != null) {
            this.R.a();
            this.R.goto = p3.getX();
            if (a.a.a.a.l.E && this.a) {
                this.R.else = p3.getY() - 22;
            }
            else {
                this.R.else = p3.getY();
            }
        }
        if (s.compareTo("mousemoved") == 0) {
            final char[] charArray = (String.valueOf(s) + "(" + p3.getX() + "," + p3.getY() + ")\u0000").toCharArray();
            if (this.G != null && this.G.h) {
                this.G.a(charArray);
                if (this.D != null && this.G.H != null && this.G.H.for) {
                    this.D.if((String.valueOf(s) + "(" + (p3.getX() - this.G.H.bh) + "," + (p3.getY() - this.G.H.bg) + ")\u0000").toCharArray());
                }
            }
        }
        else if (s.compareTo("mouseexited") == 0) {
            if (this.R != null) {
                this.R.goto = -65536;
                this.R.else = -65536;
            }
            final char[] charArray2 = "mouseexited()\u0000".toCharArray();
            if (this.G != null && this.G.h) {
                this.G.a(charArray2);
                if (this.D != null && this.G.H.for) {
                    this.D.if(charArray2);
                }
            }
        }
        else {
            final char[] charArray3 = (String.valueOf(s) + "(" + p3.getX() + "," + p3.getY() + "," + n + ")\u0000").toCharArray();
            if (this.G != null && this.G.h) {
                this.G.a(charArray3);
                if (this.D != null && this.G.H != null && this.G.H.for) {
                    this.D.if((String.valueOf(s) + "(" + (p3.getX() - this.G.H.bh) + "," + (p3.getY() - this.G.H.bg) + "," + n + ")\u0000").toCharArray());
                }
            }
        }
        if (this.R == null) {
            return;
        }
        if ((p3.getModifiers() & 0x4) != 0x0) {
            this.R.g = 5;
        }
        else if ((p3.getModifiers() & 0x10) != 0x0) {
            this.R.g = 6;
        }
        else if ((p3.getModifiers() & 0x8) != 0x0) {
            this.R.g = 7;
        }
        if (this.n) {
            this.R.g = 5;
        }
        if (this.s) {
            return;
        }
        this.R.if = if1;
        if (this.G != null) {
            this.G.if(this.R);
        }
        this.byte();
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        synchronized (this.case) {
            if (!this.Q) {
                // monitorexit(this.case)
                return;
            }
            final char[] charArray = "mouseentered()\u0000".toCharArray();
            if (this.G != null && this.G.h) {
                this.G.a(charArray);
                if (this.D != null && this.G.H.for) {
                    this.D.if(charArray);
                }
            }
        }
        // monitorexit(this.case)
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        synchronized (this.case) {
            if (!this.Q) {
                // monitorexit(this.case)
                return;
            }
            this.a(mouseEvent, "mouseExited", 3);
        }
        // monitorexit(this.case)
    }
    
    public void if(final int n) {
        synchronized (this.case) {
            if (!this.Q) {
                // monitorexit(this.case)
                return;
            }
            if (this.D != null) {
                char[] array;
                if (n > 0) {
                    array = "mousewheel(1)\u0000".toCharArray();
                }
                else {
                    array = "mousewheel(-1)\u0000".toCharArray();
                }
                if (this.G != null && this.G.h) {
                    this.G.a(array);
                    if (this.D != null && this.G.H.for) {
                        this.D.if(array);
                    }
                }
                if (this.s) {
                    // monitorexit(this.case)
                    return;
                }
                if (n < 0) {
                    this.D.W.cy -= this.D.W.cy * 0.1f;
                }
                else {
                    this.D.W.cy += this.D.W.cy * 0.1f;
                }
                this.D.W.do = true;
            }
        }
        // monitorexit(this.case)
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
        synchronized (this.case) {
            if (!this.Q) {
                // monitorexit(this.case)
                return;
            }
            final char[] charArray = ("keytyped(" + keyEvent.getKeyCode() + ")\u0000").toCharArray();
            if (this.G != null && this.G.h) {
                this.G.a(charArray);
                if (this.D != null && this.G.H.for) {
                    this.D.if(charArray);
                }
            }
        }
        // monitorexit(this.case)
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        synchronized (this.case) {
            if (!this.Q) {
                // monitorexit(this.case)
                return;
            }
            final char[] charArray = ("keypressed(" + keyEvent.getKeyCode() + ")\u0000").toCharArray();
            if (this.G != null && this.G.h) {
                this.G.a(charArray);
                if (this.D != null && this.G.H.for) {
                    this.D.if(charArray);
                }
            }
            if (this.w) {
                // monitorexit(this.case)
                return;
            }
            if (keyEvent.getKeyCode() == 17 || keyEvent.getKeyCode() == 16) {
                this.n = true;
            }
            if (keyEvent.getKeyCode() == 112 || keyEvent.getKeyCode() == 156) {
                this.int("");
            }
            if (keyEvent.getKeyCode() == 113 || keyEvent.getKeyCode() == 36 || keyEvent.getKeyCode() == 154) {
                this.char();
            }
        }
        // monitorexit(this.case)
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
        synchronized (this.case) {
            if (!this.Q) {
                // monitorexit(this.case)
                return;
            }
            final char[] charArray = ("keyreleased(" + keyEvent.getKeyCode() + ")\u0000").toCharArray();
            if (this.G != null && this.G.h) {
                this.G.a(charArray);
                if (this.D != null && this.G.H.for) {
                    this.D.if(charArray);
                }
            }
            if (this.w) {
                this.n = false;
                // monitorexit(this.case)
                return;
            }
            if (keyEvent.getKeyCode() == 17 || keyEvent.getKeyCode() == 16) {
                this.n = false;
            }
        }
        // monitorexit(this.case)
    }
    
    public void do(final boolean visible) {
        this.P = true;
        try {
            if (this.a || this.J) {
                if (this.L == null || this.void.z != this.a || this.void.y != this.J) {
                    Container parent = this;
                    do {
                        try {
                            parent = parent.getParent();
                        }
                        catch (NullPointerException ex) {
                            parent = new Frame();
                            parent.setBounds(0, 100, 0, 0);
                            parent.setVisible(true);
                            break;
                        }
                    } while (!(parent instanceof Frame));
                    final Frame frame = (Frame)parent;
                    if (this.new >= 4 && !a.a.a.a.l.E) {
                        this.L = new a4(frame, this);
                        ((a4)this.L).setUndecorated(true);
                    }
                    else {
                        this.L = new q(frame, this);
                    }
                    int width = this.L.getToolkit().getScreenSize().width;
                    final int height = this.L.getToolkit().getScreenSize().height;
                    if (this.J) {
                        width *= 2;
                    }
                    if (a.a.a.a.l.B) {
                        this.L.setLocation(0, this.j);
                    }
                    else {
                        this.L.setLocation(0, 0);
                    }
                    this.L.setSize(width, height);
                    if (a.a.a.a.l.E) {
                        this.L.setBounds(0, this.j, width, height - 22 + (a.a.a.a.l.C ? 0 : this.m));
                    }
                    else {
                        this.L.setBounds(0, 0, width, height + (a.a.a.a.l.C ? 0 : this.m));
                    }
                    this.width = this.L.getSize().width;
                    this.height = this.L.getSize().height - (((this.a | this.J) & !a.a.a.a.l.C) ? this.m : 0) - (((this.a | this.J) & a.a.a.a.l.C) ? this.j : 0);
                    this.L.addMouseMotionListener(this);
                    this.L.addMouseListener(this);
                    this.L.addKeyListener(this);
                    this.L.setVisible(true);
                    ((Window)this.L).toFront();
                    this.L.requestFocus();
                    if (this.new >= 4) {
                        try {
                            new am(this, this.L);
                        }
                        catch (Throwable t) {}
                    }
                    if (this.void == null || this.void.z != this.a || this.void.y != this.J) {
                        Label_0607: {
                            if (this.new >= 3) {
                                try {
                                    (this.void = new bj()).a(this.L, true, this);
                                    break Label_0607;
                                }
                                catch (Exception ex2) {
                                    ac.ah = false;
                                    this.void = new bb();
                                    try {
                                        this.void.a(this.L, true, this);
                                    }
                                    catch (Exception ex3) {}
                                    break Label_0607;
                                }
                            }
                            this.void = new bb();
                            try {
                                this.void.a(this.L, true, this);
                            }
                            catch (Exception ex4) {}
                        }
                        if (this.G != null && this.G.H != null) {
                            this.G.H.j();
                        }
                        if (this.G != null && this.D != null) {
                            this.D.W.a(this.G.H.aO);
                        }
                    }
                }
                this.L.setVisible(visible);
                this.c = this.L;
                this.if = this.void;
                this.if.z = this.a;
                this.if.y = this.J;
                this.r.goto = this.if;
            }
            else {
                if ((this.a || this.J) && this.if != null) {
                    return;
                }
                if (this.L != null) {
                    final int n = this.if.s * this.if.r;
                    try {
                        for (int i = 0; i < n; ++i) {
                            this.if.x[i] = -1;
                        }
                    }
                    catch (Exception ex5) {}
                    this.L.setVisible(false);
                }
                if (this.int == null) {
                    this.case();
                }
                this.c = this;
                this.if = this.int;
            }
            if (this.D != null) {
                this.D.long = true;
            }
            if (this.G != null) {
                this.G.long = true;
            }
            this.width = this.c.getSize().width;
            this.height = this.c.getSize().height - (((this.a | this.J) & !a.a.a.a.l.C) ? this.m : 0) - (((this.a | this.J) & a.a.a.a.l.C) ? this.j : 0);
            this.if.new();
        }
        catch (OutOfMemoryError outOfMemoryError) {
            this.new("Out of memory.");
        }
        this.width = this.c.getSize().width;
        this.height = this.c.getSize().height - (((this.a | this.J) & !a.a.a.a.l.C) ? this.m : 0) - (((this.a | this.J) & a.a.a.a.l.C) ? this.j : 0);
        this.r.goto = this.if;
    }
    
    private void new(final String s) {
        this.a = false;
        this.J = false;
        if (this.int == null) {
            this.case();
        }
        if (this.L != null) {
            if (this.new >= 4) {
                ((Dialog)this.L).dispose();
            }
            else {
                ((Window)this.L).dispose();
            }
        }
        this.L = null;
        this.void = null;
        System.gc();
        if (this.int == null) {
            this.case();
        }
        this.c = this;
        this.if = this.int;
        System.out.println("Can't go fullscreen. " + s);
    }
    
    private void case() {
        this.c = this;
        Label_0116: {
            if (this.int == null) {
                if (this.new >= 3) {
                    try {
                        (this.int = new bj()).a(this.c, false, this);
                        break Label_0116;
                    }
                    catch (Exception ex) {
                        ac.ah = false;
                        this.int = null;
                        this.int = new bb();
                        try {
                            this.int.a(this.c, false, this);
                        }
                        catch (Exception ex2) {}
                        break Label_0116;
                    }
                }
                this.int = new bb();
                try {
                    this.int.a(this.c, false, this);
                }
                catch (Exception ex3) {}
            }
        }
        this.if = this.int;
    }
    
    protected void goto() {
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
                    this.r.e = e;
                }
                catch (Exception ex) {}
                byteArrayInputStream.close();
            }
            catch (Exception ex2) {}
        }
    }
    
    public void a(final boolean z) {
        this.z = z;
    }
    
    protected void a(final String s) {
        this.r.B.a(s);
    }
    
    public Image a() {
        if (this.if != null) {
            return this.if.C;
        }
        return null;
    }
    
    public void if(final boolean else1) {
        this.else = else1;
    }
    
    public void a(final int q) {
        this.q = q;
    }
    
    public void new() {
        if (this.G != null && this.G.H != null) {
            this.G.H.b2 = false;
        }
    }
    
    public boolean for() {
        return this.G != null;
    }
    
    public boolean if() {
        return this.D != null;
    }
    
    public boolean else() {
        return this.D != null && this.D.Z;
    }
    
    public boolean try() {
        return this.Q;
    }
    
    public be int() {
        return this.char;
    }
}
