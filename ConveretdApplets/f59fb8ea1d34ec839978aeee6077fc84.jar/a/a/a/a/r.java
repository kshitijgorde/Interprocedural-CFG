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

public class r extends Applet implements KeyListener, MouseMotionListener, MouseListener, Runnable
{
    protected boolean else;
    private l if;
    private l int;
    private l void;
    protected am Q;
    protected t[] n;
    protected int b;
    protected bv char;
    protected ae F;
    protected ae k;
    protected t C;
    protected t g;
    protected an q;
    protected v M;
    protected Thread H;
    protected int width;
    protected int height;
    protected boolean r;
    protected boolean v;
    protected long R;
    private boolean w;
    protected static final String t = "1.14.1.1";
    protected String N;
    protected Component c;
    protected Component K;
    protected boolean a;
    protected boolean I;
    protected int p;
    protected boolean m;
    public int new;
    protected b9 s;
    protected bx i;
    protected static boolean D;
    protected static boolean B;
    protected static boolean A;
    protected float for;
    protected boolean O;
    protected String G;
    protected boolean P;
    protected Object case;
    private boolean f;
    private String j;
    private aq goto;
    private boolean d;
    private boolean byte;
    protected boolean h;
    protected boolean try;
    protected long u;
    protected ap J;
    private int l;
    private float z;
    private float E;
    private float long;
    private boolean L;
    MouseEvent o;
    String e;
    int do;
    static /* synthetic */ Class class$0;
    static /* synthetic */ Class class$1;
    
    static {
        r.D = false;
        r.B = false;
        r.A = false;
    }
    
    public r() {
        this.else = true;
        this.if = null;
        this.int = null;
        this.void = null;
        this.Q = new am();
        this.n = null;
        this.b = 0;
        this.char = null;
        this.F = null;
        this.k = null;
        this.C = null;
        this.g = null;
        this.q = null;
        this.M = null;
        this.r = false;
        this.v = false;
        this.R = 0L;
        this.w = false;
        this.N = "ImmerVision PURE Player PRO for Java 1.14.1.1";
        this.c = this;
        this.K = null;
        this.a = false;
        this.I = false;
        this.p = 1;
        this.m = false;
        this.new = 0;
        this.s = null;
        this.i = null;
        this.for = 1.0f;
        this.O = false;
        this.G = "";
        this.P = false;
        this.case = new Object();
        this.f = false;
        this.j = null;
        this.goto = null;
        this.d = false;
        this.byte = false;
        this.h = false;
        this.try = true;
        this.u = 0L;
        this.J = null;
        this.l = 30;
        this.z = 0.0f;
        this.E = 0.0f;
        this.long = 0.0f;
        this.L = false;
        this.o = null;
        this.e = "";
        this.do = 0;
    }
    
    public String getAppletInfo() {
        return this.N;
    }
    
    public void init() {
        this.q = new an();
        this.q.case = this;
        System.out.println(this.N);
        try {
            final String property = System.getProperty("java.version");
            this.new = property.charAt(2) - '0';
            System.out.println("Java Version: " + property + " from " + System.getProperty("java.vendor"));
            System.out.println("Operating system: " + System.getProperty("os.name") + " version " + System.getProperty("os.version") + " on " + System.getProperty("os.arch"));
            if (property.compareTo("1.6.0_03") >= 0) {
                this.l = 17;
            }
            if (System.getProperty("os.name").toLowerCase().startsWith("mac")) {
                a.a.a.a.r.D = true;
                if (this.new == 3) {
                    a.a.a.a.r.B = true;
                }
                if (this.new == 4) {
                    a.a.a.a.r.B = true;
                    a.a.a.a.r.A = true;
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
        this.q.L = this;
        this.q.I = this.getToolkit();
        this.q.W = new a3();
        this.q.aa = new a9(this.q);
        this.q.P = this;
        this.q.if = new MediaTracker(this);
        this.q.s = new a8();
        an.aj = this.new;
        this.goto();
        final String parameter = this.getParameter("maximagesize");
        if (parameter != null) {
            an.b = an.a(parameter);
            if (an.b < 0) {
                an.b = 0;
            }
        }
        if (this.new >= 4) {
            try {
                new a0(this, this);
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
            an.g = Float.valueOf(parameter3);
            if (an.g > 0.0f) {
                an.g = 1.0f - Float.valueOf(parameter3) / 1000.0f;
            }
            if (an.g >= 1.0f || an.g < 0.0f) {
                an.g = 0.0f;
            }
        }
        final String parameter4 = this.getParameter("fullscreen2");
        if (parameter4 != null && parameter4.toLowerCase().compareTo("true") == 0) {
            this.I = true;
        }
        if (!this.I) {
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
        if (this.byte && !this.a && !this.I) {
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
            if (this.C != null) {
                this.i = new bx(this, frame, "1.14.1.1", this.F.e, this.F.new, this.F.s, this.C.e, this.C.new, this.C.s);
            }
            else {
                this.i = new bx(this, frame, "1.14.1.1", this.F.e, this.F.new, this.F.s, "", "", "");
            }
        }
        else {
            this.i.toFront();
        }
    }
    
    public void int(final String s) {
        if (this.s == null) {
            Container container;
            for (container = this.getParent(); !(container instanceof Frame); container = container.getParent()) {}
            this.s = new b9((Frame)container, "1.14.1.1", this, s);
        }
        else {
            this.s.toFront();
        }
    }
    
    public void start() {
        if (this.F != null) {
            return;
        }
        this.if.new();
        this.M = new v();
        this.char = new bv(this.getDocumentBase(), this.M, this.q);
        this.q.B = this.char;
        this.M.b = this.q;
        this.F = new ae(this.q, this.M, this);
        final String parameter = this.getParameter("optimizememory");
        if (parameter != null && parameter.toLowerCase().compareTo("true") == 0) {
            System.out.println("Memory optimization enabled.");
            this.q.T = true;
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
                this.q.ar = floatValue;
                System.out.println("Quality set to " + parameter2);
            }
            catch (Exception ex2) {
                this.q.ar = 100.0f;
                System.out.println("Error in Quality parameter. Quality is set to default.");
            }
        }
        final String parameter3 = this.getParameter("lockzenithnadir");
        if (parameter3 != null && parameter3.toLowerCase().compareTo("true") == 0) {
            System.out.println("Lock camera movements at zenith and nadir.");
            this.q.for = true;
        }
        final String parameter4 = this.getParameter("singlepanorama");
        if (parameter4 != null && parameter4.toLowerCase().compareTo("true") == 0) {
            System.out.println("Single panorama mode enabled.");
            this.q.K = true;
        }
        final String parameter5 = this.getParameter("usecache");
        if (parameter5 != null) {
            if (parameter5.toLowerCase().compareTo("true") == 0) {
                this.q.k = true;
            }
            else if (parameter5.toLowerCase().compareTo("smart") == 0 && an.aj < 3) {
                this.q.k = true;
            }
        }
        else if (an.aj < 3) {
            this.q.k = true;
        }
        System.out.println("Internal cache system " + (this.q.k ? "enabled" : "disabled") + ".");
        final String parameter6 = this.getParameter("antialiasing");
        if (parameter6 != null) {
            if (parameter6.toLowerCase().compareTo("none") == 0) {
                System.out.println("No antialiasing.");
                this.p = 0;
            }
            else if (parameter6.toLowerCase().compareTo("everytime") == 0) {
                System.out.println("Full antialiasing.");
                this.p = 2;
            }
            else {
                System.out.println("Antialiasing on stop.");
            }
        }
        else {
            System.out.println("Antialiasing on stop.");
        }
        if (this.w) {
            this.F.a(this.char.a(this.getParameter(new String(an.p)), null, false, false, false));
            this.try = false;
        }
        else {
            this.F.a(this.char.a(new String(an.Y), null, false, false, false));
        }
        while (!this.F.k || !this.F.new()) {
            try {
                Thread.sleep(100L);
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            this.F.a(System.currentTimeMillis());
        }
        if (!this.w) {
            final String parameter7 = this.getParameter("gui");
            this.M.f();
            if (parameter7 == null) {
                this.h = true;
            }
            else {
                (this.k = new ae(this.q, this.M, this)).a(this.char.a(parameter7, null, false, false, false));
            }
        }
        final String parameter8 = this.getParameter("transition");
        if (parameter8 != null) {
            this.G = parameter8;
        }
        this.M.d();
        this.F.if(System.currentTimeMillis());
        this.M.a((int)this.F.L);
        this.u = System.currentTimeMillis();
        this.F.L += this.u;
        this.R = System.currentTimeMillis();
        this.F.long = true;
        (this.H = new Thread(this)).start();
        this.P = true;
    }
    
    public void run() {
        while (!this.q.G) {
            Thread.yield();
            long n = System.currentTimeMillis();
            try {
                if (n - this.R < 40L) {
                    Thread.sleep(this.R + 40L - n);
                }
                n = System.currentTimeMillis();
                if (this.try) {
                    this.M.by += (int)(n - this.u);
                    this.u = n;
                }
                this.R = n;
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
            this.do();
            if (this.width != this.c.getSize().width || (this.height != this.c.getSize().height - (((this.a | this.I) & !a.a.a.a.r.B) ? this.l : 0) - (((this.a | this.I) & a.a.a.a.r.B) ? 22 : 0) | this.O)) {
                this.width = this.c.getSize().width;
                this.height = this.c.getSize().height - (((this.a | this.I) & !a.a.a.a.r.B) ? this.l : 0) - (((this.a | this.I) & a.a.a.a.r.B) ? 22 : 0);
                if (this.width <= 0 || this.height <= 0) {
                    // monitorexit(this.case)
                    return;
                }
                if (this.if != null) {
                    this.if.new();
                }
                if (this.F != null) {
                    this.F.a(("resize(" + this.width + "," + this.height + ");\u0000").toCharArray());
                    if (this.F.H != null) {
                        this.F.H.j();
                    }
                    this.F.long = true;
                }
                this.O = false;
                if (this.C != null) {
                    this.C.long = true;
                }
                b = true;
            }
            if (this.try && !this.h) {
                this.k.a(n);
                if (!this.h && this.k.k && System.currentTimeMillis() > this.F.L && this.F.L != 0L && this.k.new()) {
                    if (this.F != null) {
                        this.F.a();
                        this.M.b = this.q;
                        this.M.f();
                        this.F = null;
                    }
                    this.F = this.k;
                    this.k = null;
                    this.try = false;
                    this.M.for = false;
                    this.M.d();
                    this.F.long = true;
                    this.F.if(n);
                }
            }
            else if (this.try && this.h && System.currentTimeMillis() > this.F.L && this.F.L != 0L) {
                this.try = false;
                this.k = null;
                this.M.for = false;
                this.M.f();
            }
            if (this.F != null) {
                boolean b2 = b | this.F.a(n);
                for (int i = 0; i < this.b; ++i) {
                    this.n[i].do(n);
                    if (!this.n[i].S) {
                        if (!this.n[i].z) {
                            this.n[i].if(true);
                        }
                        if (this.n[i].z) {
                            this.n[i].S = true;
                            this.for(this.n[i].M);
                        }
                    }
                    else if (!this.n[i].z) {
                        this.n[i].if(true);
                    }
                }
                if (this.F.h && !this.try) {
                    if (this.C == null && this.g == null) {
                        final String parameter = this.getParameter("panorama");
                        if (parameter != null) {
                            this.g = new t(this.q, this, false);
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
                                this.F.H.a(this.g);
                            }
                            if (this.C != null) {
                                if (this.g.R && this.C.N) {
                                    this.g.W.cE = this.C.W.cE - this.C.T + this.g.T;
                                    this.g.W.cx = 0.0f;
                                    this.g.W.cy = this.C.W.cy;
                                }
                                if (this.G.length() > 0) {
                                    this.J = this.C.try();
                                }
                            }
                            else if (this.G.length() > 0 && this.J == null) {
                                if (this.g.R && this.L) {
                                    this.g.W.cE = this.E - this.z + this.g.T;
                                    this.g.W.cx = 0.0f;
                                    this.g.W.cy = this.long;
                                    this.L = false;
                                }
                                this.J = this.F.H.g();
                            }
                            else if (this.g.R && this.L) {
                                this.g.W.cE = this.E - this.z + this.g.T;
                                this.g.W.cx = 0.0f;
                                this.g.W.cy = this.long;
                                this.L = false;
                            }
                            if (this.J != null) {
                                try {
                                    String s = null;
                                    if (this.q.e != null && this.G.toLowerCase().startsWith("trans")) {
                                        s = this.q.e.getProperty(this.G.toLowerCase());
                                    }
                                    if (s == null) {
                                        s = "com.immervision.pure.player." + this.G;
                                    }
                                    final ay ay = (ay)Class.forName(s).getConstructor((Class<?>[])new Class[0]).newInstance((Object[])null);
                                    if (ay != null) {
                                        this.g.W.a(ay);
                                    }
                                    else {
                                        System.out.println("Can't load transition: " + this.G);
                                    }
                                }
                                catch (Exception ex) {
                                    System.out.println("Can't load transition: " + this.G);
                                }
                                this.g.W.if(this.J);
                                this.J = null;
                            }
                            this.g.for(n);
                            if (this.C != null) {
                                this.C.if("enddisplay();\u0000".toCharArray());
                                this.F.a(("enddisplay(\"" + this.C.M + "\");\u0000").toCharArray());
                                this.C.a();
                                this.C = null;
                            }
                            this.C = this.g;
                            this.g = null;
                            b2 = true;
                            this.F.a(("startdisplay(\"" + this.C.M + "\");\u0000").toCharArray());
                        }
                    }
                    if (this.g != null) {
                        this.g.do(n);
                    }
                }
                if (b2 || this.f) {
                    this.F.for();
                    if (this.C != null && this.C.O) {
                        this.a(this.o, this.e, this.do);
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
            if (this.C != null && this.C.z) {
                // monitorexit(this.case)
                return az.for(this.C.if((String.valueOf(s) + "\u0000").toCharArray()));
            }
        }
        // monitorexit(this.case)
        return "";
    }
    
    public String execgui(final String s) {
        synchronized (this.case) {
            if (this.F != null && this.F.new()) {
                // monitorexit(this.case)
                return az.for(this.F.a((String.valueOf(s) + "\u0000").toCharArray()));
            }
        }
        // monitorexit(this.case)
        return "";
    }
    
    public bi new(final String s) {
        synchronized (this.case) {
            if (this.C != null && this.C.z) {
                // monitorexit(this.case)
                return this.C.if((String.valueOf(s) + "\u0000").toCharArray());
            }
            // monitorexit(this.case)
            return new bi();
        }
    }
    
    public bi if(final String s) {
        synchronized (this.case) {
            if (this.F != null && this.F.new()) {
                // monitorexit(this.case)
                return this.F.a((String.valueOf(s) + "\u0000").toCharArray());
            }
            // monitorexit(this.case)
            return new bi();
        }
    }
    
    public void if(final String m, final aq aq) {
        for (int i = 0; i < this.b; ++i) {
            if (m.compareTo(this.n[i].M) == 0) {
                this.n[i].S = false;
                this.n[i].if(true);
                return;
            }
        }
        if (this.n == null || this.n.length == this.b) {
            final t[] n = new t[this.b + 10];
            for (int j = 0; j < this.b; ++j) {
                n[j] = this.n[j];
            }
            this.n = n;
        }
        if (this.C != null && m.compareTo(this.C.M) == 0) {
            this.n[this.b] = this.C;
            this.n[this.b].S = false;
            ++this.b;
            this.C.if(true);
            return;
        }
        this.n[this.b] = new t(this.q, this, false);
        this.n[this.b].M = m;
        this.n[this.b].if(this.char.a(m, aq, false, false, false));
        ++this.b;
    }
    
    public boolean do(final String s) {
        for (int i = 0; i < this.b; ++i) {
            if (this.n[i].M.compareTo(s) == 0) {
                for (int j = i; j < this.b - 1; ++j) {
                    this.n[j] = this.n[j + 1];
                }
                --this.b;
                return true;
            }
        }
        return false;
    }
    
    public void for(final String s) {
        this.F.a(("ispreloaded(\"" + s + "\")\u0000").toCharArray());
        if (this.C != null) {
            this.C.if(("ispreloaded(" + s + ")\u0000").toCharArray());
        }
    }
    
    public void case(final String s) {
        this.F.a(("loadfinished(\"" + s + "\")\u0000").toCharArray());
        if (this.C != null) {
            this.C.if("loadfinished()\u0000".toCharArray());
        }
    }
    
    public boolean try(final String s) {
        int i = 0;
        while (i < this.b) {
            if (this.n[i].M.compareTo(s) == 0) {
                if (this.n[i].z) {
                    this.g = this.n[i];
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
    
    public void a(final String m, final aq aq) {
        for (int i = 0; i < this.b; ++i) {
            if (m.compareTo(this.n[i].M) == 0) {
                this.n[i].if(true);
                return;
            }
        }
        if (this.n == null || this.n.length == this.b) {
            final t[] n = new t[this.b + 10];
            for (int j = 0; j < this.b; ++j) {
                n[j] = this.n[j];
            }
            this.n = n;
        }
        this.n[this.b] = new t(this.q, this, false);
        this.n[this.b].M = m;
        this.n[this.b].if(this.char.a(m, aq, false, false, false));
        ++this.b;
    }
    
    public void byte(final String s) {
        this.F.a(("ispreloaded(\"" + s + "\")\u0000").toCharArray());
        if (this.C != null) {
            this.C.if(("ispreloaded(" + s + ")\u0000").toCharArray());
        }
    }
    
    private void do() {
        if (this.j == null) {
            return;
        }
        if (this.g != null || (this.C != null && !this.C.Z)) {
            this.j = null;
            this.goto = null;
            return;
        }
        if (!this.q.k) {
            this.char.do();
        }
        if (this.q.K && this.C != null) {
            if (this.G != null && this.G.length() > 0) {
                this.J = this.C.try();
            }
            this.C.if("enddisplay();\u0000".toCharArray());
            this.F.a(("enddisplay(\"" + this.C.M + "\");\u0000").toCharArray());
            if (this.C.N) {
                this.z = this.C.T;
                this.E = this.C.W.cE;
                this.long = this.C.W.cy;
                this.L = true;
            }
            this.C.a();
            this.C = null;
            this.F.H.h();
            System.gc();
        }
        (this.g = new t(this.q, this, this.d)).if(this.char.a(this.j, this.goto, false, false, false));
        this.j = null;
        this.goto = null;
    }
    
    public void a(final String j, final aq goto1, final boolean d) {
        this.j = j;
        this.goto = goto1;
        this.d = d;
    }
    
    public void a(final char[] array, final char[] array2) {
        final String s = new String(array, 0, a.a.a.a.i.a(array));
        try {
            final URL url = new URL(this.getDocumentBase(), s);
            final AppletContext appletContext = this.getAppletContext();
            if (array2 != null) {
                appletContext.showDocument(url, new String(array2, 0, a.a.a.a.i.a(array2)));
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
        final String s = new String(array, 0, a.a.a.a.i.a(array));
        System.out.println(array);
        if (s != null) {
            try {
                if (a.a.a.a.r.D) {
                    this.getAppletContext().showDocument(new URL("javascript:" + s), "_self");
                }
                else {
                    final Class<?> forName;
                    final Class<?> clazz = forName = Class.forName("netscape.javascript.JSObject");
                    final String s2 = "getWindow";
                    final Class[] array2 = { null };
                    final int n = 0;
                    Class class$0;
                    if ((class$0 = a.a.a.a.r.class$0) == null) {
                        try {
                            class$0 = (a.a.a.a.r.class$0 = Class.forName("java.applet.Applet"));
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
                    if ((class$2 = a.a.a.a.r.class$1) == null) {
                        try {
                            class$2 = (a.a.a.a.r.class$1 = Class.forName("java.lang.String"));
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
    
    public bi if(final char[] array) {
        bi if1 = null;
        if (this.C != null) {
            if1 = this.C.if(array);
        }
        if (if1 != null) {
            return if1;
        }
        System.out.println("Error in the command sent to panorama script environment.");
        return new bi();
    }
    
    public bi do(final char[] array) {
        bi a = null;
        if (this.F != null) {
            a = this.F.a(array);
        }
        if (a != null) {
            return a;
        }
        System.out.println("Error in the command sent to gui script environment.");
        return new bi();
    }
    
    private void byte() {
        if (this.q.au != this.Q.for) {
            this.q.au = this.Q.for;
            switch (this.Q.for) {
                case 3: {
                    if (this.F != null && this.F.D.bK[0] != null) {
                        this.c.setCursor(this.F.D.bK[0]);
                        break;
                    }
                    this.c.setCursor(Cursor.getPredefinedCursor(0));
                    break;
                }
                case 0: {
                    if (this.F != null && this.F.D.bK[1] != null) {
                        this.c.setCursor(this.F.D.bK[1]);
                        break;
                    }
                    this.c.setCursor(Cursor.getPredefinedCursor(12));
                    break;
                }
                case 1: {
                    if (this.F != null && this.F.D.bK[2] != null) {
                        this.c.setCursor(this.F.D.bK[2]);
                        break;
                    }
                    this.c.setCursor(Cursor.getPredefinedCursor(13));
                    break;
                }
                case 2: {
                    if (this.F != null && this.F.D.bK[3] != null) {
                        this.c.setCursor(this.F.D.bK[3]);
                        break;
                    }
                    this.c.setCursor(Cursor.getPredefinedCursor(9));
                    break;
                }
            }
        }
        for (int i = 0; i < this.Q.byte; ++i) {
            if (this.Q.c[i]) {
                this.C.if(this.Q.b[i]);
            }
            else {
                this.F.a(this.Q.b[i]);
            }
        }
    }
    
    public void destroy() {
        this.q.G = true;
        try {
            if (this.i != null) {
                this.i.dispose();
                this.i = null;
            }
            if (this.s != null) {
                this.s.dispose();
                this.s = null;
            }
            if (this.C != null) {
                this.C.a();
            }
            if (this.F != null) {
                this.F.a();
            }
            this.q.a();
            this.if.if();
            this.if = null;
            this.int.if();
            this.int = null;
            if (this.void != null) {
                this.void.if();
            }
            this.void = null;
            if (this.K != null) {
                if (this.new >= 4) {
                    ((Dialog)this.K).dispose();
                }
                else {
                    ((Window)this.K).dispose();
                }
            }
            this.K = null;
            this.Q = null;
            if (this.n != null) {
                for (int i = 0; i < this.n.length; ++i) {
                    if (this.n[i] != null) {
                        this.n[i].a();
                    }
                }
            }
            this.char = null;
            if (this.n != null) {
                for (int j = 0; j < this.n.length; ++j) {
                    if (this.n[j] != null) {
                        this.n[j].a();
                    }
                    this.n[j] = null;
                }
            }
            this.n = null;
            if (this.g != null) {
                this.g.a();
            }
            this.g = null;
            this.M.if();
            this.M = null;
            this.c = null;
            this.K = null;
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
    
    private void a(final MouseEvent o, final String s, final int if1) {
        if (o == null) {
            return;
        }
        this.o = o;
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
        if (!this.P) {
            return;
        }
        int n = 0;
        if ((o.getModifiers() & 0x4) != 0x0) {
            n |= 0x4;
        }
        else if ((o.getModifiers() & 0x10) != 0x0) {
            n |= 0x1;
        }
        else if ((o.getModifiers() & 0x8) != 0x0) {
            n |= 0x2;
        }
        if (this.Q != null) {
            this.Q.a();
            this.Q.goto = o.getX();
            this.Q.else = o.getY();
        }
        if (s.compareTo("mousemoved") == 0) {
            final char[] charArray = (String.valueOf(s) + "(" + o.getX() + "," + o.getY() + ")\u0000").toCharArray();
            if (this.F != null && this.F.h) {
                this.F.a(charArray);
                if (this.C != null && this.F.H != null && this.F.H.for) {
                    this.C.if((String.valueOf(s) + "(" + (o.getX() - this.F.H.bh) + "," + (o.getY() - this.F.H.bg) + ")\u0000").toCharArray());
                }
            }
        }
        else if (s.compareTo("mouseexited") == 0) {
            if (this.Q != null) {
                this.Q.goto = -65536;
                this.Q.else = -65536;
            }
            final char[] charArray2 = "mouseexited()\u0000".toCharArray();
            if (this.F != null && this.F.h) {
                this.F.a(charArray2);
                if (this.C != null && this.F.H.for) {
                    this.C.if(charArray2);
                }
            }
        }
        else {
            final char[] charArray3 = (String.valueOf(s) + "(" + o.getX() + "," + o.getY() + "," + n + ")\u0000").toCharArray();
            if (this.F != null && this.F.h) {
                this.F.a(charArray3);
                if (this.C != null && this.F.H != null && this.F.H.for) {
                    this.C.if((String.valueOf(s) + "(" + (o.getX() - this.F.H.bh) + "," + (o.getY() - this.F.H.bg) + "," + n + ")\u0000").toCharArray());
                }
            }
        }
        if (this.Q == null) {
            return;
        }
        if ((o.getModifiers() & 0x4) != 0x0) {
            this.Q.g = 5;
        }
        else if ((o.getModifiers() & 0x10) != 0x0) {
            this.Q.g = 6;
        }
        else if ((o.getModifiers() & 0x8) != 0x0) {
            this.Q.g = 7;
        }
        if (this.m) {
            this.Q.g = 5;
        }
        if (this.r) {
            return;
        }
        this.Q.if = if1;
        if (this.F != null) {
            this.F.if(this.Q);
        }
        this.byte();
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        synchronized (this.case) {
            if (!this.P) {
                // monitorexit(this.case)
                return;
            }
            final char[] charArray = "mouseentered()\u0000".toCharArray();
            if (this.F != null && this.F.h) {
                this.F.a(charArray);
                if (this.C != null && this.F.H.for) {
                    this.C.if(charArray);
                }
            }
        }
        // monitorexit(this.case)
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        synchronized (this.case) {
            if (!this.P) {
                // monitorexit(this.case)
                return;
            }
            this.a(mouseEvent, "mouseExited", 3);
        }
        // monitorexit(this.case)
    }
    
    public void if(final int n) {
        synchronized (this.case) {
            if (!this.P) {
                // monitorexit(this.case)
                return;
            }
            if (this.C != null) {
                char[] array;
                if (n > 0) {
                    array = "mousewheel(1)\u0000".toCharArray();
                }
                else {
                    array = "mousewheel(-1)\u0000".toCharArray();
                }
                if (this.F != null && this.F.h) {
                    this.F.a(array);
                    if (this.C != null && this.F.H.for) {
                        this.C.if(array);
                    }
                }
                if (this.r) {
                    // monitorexit(this.case)
                    return;
                }
                if (n < 0) {
                    this.C.W.cy -= this.C.W.cy * 0.1f;
                }
                else {
                    this.C.W.cy += this.C.W.cy * 0.1f;
                }
                this.C.W.do = true;
            }
        }
        // monitorexit(this.case)
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
        synchronized (this.case) {
            if (!this.P) {
                // monitorexit(this.case)
                return;
            }
            final char[] charArray = ("keytyped(" + keyEvent.getKeyCode() + ")\u0000").toCharArray();
            if (this.F != null && this.F.h) {
                this.F.a(charArray);
                if (this.C != null && this.F.H.for) {
                    this.C.if(charArray);
                }
            }
        }
        // monitorexit(this.case)
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        synchronized (this.case) {
            if (!this.P) {
                // monitorexit(this.case)
                return;
            }
            final char[] charArray = ("keypressed(" + keyEvent.getKeyCode() + ")\u0000").toCharArray();
            if (this.F != null && this.F.h) {
                this.F.a(charArray);
                if (this.C != null && this.F.H.for) {
                    this.C.if(charArray);
                }
            }
            if (this.v) {
                // monitorexit(this.case)
                return;
            }
            if (keyEvent.getKeyCode() == 17 || keyEvent.getKeyCode() == 16) {
                this.m = true;
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
            if (!this.P) {
                // monitorexit(this.case)
                return;
            }
            final char[] charArray = ("keyreleased(" + keyEvent.getKeyCode() + ")\u0000").toCharArray();
            if (this.F != null && this.F.h) {
                this.F.a(charArray);
                if (this.C != null && this.F.H.for) {
                    this.C.if(charArray);
                }
            }
            if (this.v) {
                this.m = false;
                // monitorexit(this.case)
                return;
            }
            if (keyEvent.getKeyCode() == 17 || keyEvent.getKeyCode() == 16) {
                this.m = false;
            }
        }
        // monitorexit(this.case)
    }
    
    public void do(final boolean visible) {
        this.O = true;
        try {
            if (this.a || this.I) {
                if (this.K == null || this.void.z != this.a || this.void.y != this.I) {
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
                        this.K = new bk(frame, this);
                        ((bk)this.K).setUndecorated(true);
                    }
                    else {
                        this.K = new x(frame, this);
                    }
                    int width = this.K.getToolkit().getScreenSize().width;
                    final int height = this.K.getToolkit().getScreenSize().height;
                    if (this.I) {
                        width *= 2;
                    }
                    if (a.a.a.a.r.A) {
                        this.K.setLocation(0, 22);
                    }
                    else {
                        this.K.setLocation(0, 0);
                    }
                    this.K.setSize(width, height);
                    if (a.a.a.a.r.A) {
                        this.K.reshape(0, 22, width, height + (a.a.a.a.r.B ? 0 : this.l));
                    }
                    else {
                        this.K.reshape(0, 0, width, height + (a.a.a.a.r.B ? 0 : this.l));
                    }
                    this.width = this.K.getSize().width;
                    this.height = this.K.getSize().height - (((this.a | this.I) & !a.a.a.a.r.B) ? this.l : 0) - (((this.a | this.I) & a.a.a.a.r.B) ? 22 : 0);
                    this.K.addMouseMotionListener(this);
                    this.K.addMouseListener(this);
                    this.K.addKeyListener(this);
                    this.K.setVisible(true);
                    ((Window)this.K).toFront();
                    this.K.requestFocus();
                    if (this.new >= 4) {
                        try {
                            new a0(this, this.K);
                        }
                        catch (Throwable t) {}
                    }
                    if (this.void == null || this.void.z != this.a || this.void.y != this.I) {
                        Label_0587: {
                            if (this.new >= 3) {
                                try {
                                    (this.void = new b1()).a(this.K, true, this);
                                    break Label_0587;
                                }
                                catch (Exception ex2) {
                                    an.ah = false;
                                    this.void = new bs();
                                    try {
                                        this.void.a(this.K, true, this);
                                    }
                                    catch (Exception ex3) {}
                                    break Label_0587;
                                }
                            }
                            this.void = new bs();
                            try {
                                this.void.a(this.K, true, this);
                            }
                            catch (Exception ex4) {}
                        }
                        if (this.F != null && this.F.H != null) {
                            this.F.H.j();
                        }
                        if (this.F != null && this.C != null) {
                            this.C.W.a(this.F.H.aO);
                        }
                    }
                }
                this.K.setVisible(visible);
                this.c = this.K;
                this.if = this.void;
                this.if.z = this.a;
                this.if.y = this.I;
                this.q.goto = this.if;
            }
            else {
                if ((this.a || this.I) && this.if != null) {
                    return;
                }
                if (this.K != null) {
                    final int n = this.if.s * this.if.r;
                    try {
                        for (int i = 0; i < n; ++i) {
                            this.if.x[i] = -1;
                        }
                    }
                    catch (Exception ex5) {}
                    this.K.setVisible(false);
                }
                if (this.int == null) {
                    this.case();
                }
                this.c = this;
                this.if = this.int;
            }
            if (this.C != null) {
                this.C.long = true;
            }
            if (this.F != null) {
                this.F.long = true;
            }
            this.width = this.c.getSize().width;
            this.height = this.c.getSize().height - (((this.a | this.I) & !a.a.a.a.r.B) ? this.l : 0) - (((this.a | this.I) & a.a.a.a.r.B) ? 22 : 0);
            this.if.new();
        }
        catch (OutOfMemoryError outOfMemoryError) {
            this.a = false;
            this.I = false;
            if (this.int == null) {
                this.case();
            }
            if (this.K != null) {
                if (this.new >= 4) {
                    ((Dialog)this.K).dispose();
                }
                else {
                    ((Window)this.K).dispose();
                }
            }
            this.K = null;
            this.void = null;
            System.gc();
            if (this.int == null) {
                this.case();
            }
            this.c = this;
            this.if = this.int;
            System.out.println("Can't go fullscreen. Out of memory.");
        }
        this.width = this.c.getSize().width;
        this.height = this.c.getSize().height - (((this.a | this.I) & !a.a.a.a.r.B) ? this.l : 0) - (((this.a | this.I) & a.a.a.a.r.B) ? 22 : 0);
        this.q.goto = this.if;
    }
    
    private void case() {
        this.c = this;
        Label_0116: {
            if (this.int == null) {
                if (this.new >= 3) {
                    try {
                        (this.int = new b1()).a(this.c, false, this);
                        break Label_0116;
                    }
                    catch (Exception ex) {
                        an.ah = false;
                        this.int = null;
                        this.int = new bs();
                        try {
                            this.int.a(this.c, false, this);
                        }
                        catch (Exception ex2) {}
                        break Label_0116;
                    }
                }
                this.int = new bs();
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
                    this.q.e = e;
                }
                catch (Exception ex) {}
                byteArrayInputStream.close();
            }
            catch (Exception ex2) {}
        }
    }
    
    public void a(final boolean w) {
        this.w = w;
    }
    
    protected void a(final String s) {
        this.q.B.a(s);
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
    
    public void a(final int p) {
        this.p = p;
    }
    
    public void new() {
        if (this.F != null && this.F.H != null) {
            this.F.H.b2 = false;
        }
    }
    
    public boolean for() {
        return this.F != null;
    }
    
    public boolean if() {
        return this.C != null;
    }
    
    public boolean else() {
        return this.C != null && this.C.Z;
    }
    
    public boolean try() {
        return this.P;
    }
    
    public bv int() {
        return this.char;
    }
}
