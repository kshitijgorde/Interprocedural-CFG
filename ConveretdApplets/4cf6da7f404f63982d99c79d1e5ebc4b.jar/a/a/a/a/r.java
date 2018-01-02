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
    private l if;
    private l int;
    private l goto;
    am K;
    t[] k;
    int long;
    bv char;
    protected ae A;
    protected ae i;
    protected t w;
    protected t e;
    protected an n;
    v G;
    Thread C;
    int width;
    int height;
    boolean o;
    boolean s;
    long L;
    public boolean t;
    public static final String q = "v1.10";
    String H;
    Component void;
    Component F;
    boolean a;
    boolean D;
    public int m;
    boolean j;
    public int new;
    b9 p;
    bx g;
    static boolean z;
    static boolean v;
    static boolean u;
    float for;
    boolean I;
    String B;
    boolean J;
    Object case;
    private boolean d;
    private String h;
    private aq else;
    private boolean b;
    private boolean byte;
    boolean f;
    boolean try;
    long r;
    public ap E;
    MouseEvent l;
    String c;
    int do;
    static /* synthetic */ Class class$0;
    static /* synthetic */ Class class$1;
    
    static {
        r.z = false;
        r.v = false;
        r.u = false;
    }
    
    public r() {
        this.if = null;
        this.int = null;
        this.goto = null;
        this.K = new am();
        this.k = null;
        this.long = 0;
        this.char = null;
        this.A = null;
        this.i = null;
        this.w = null;
        this.e = null;
        this.n = null;
        this.G = null;
        this.o = false;
        this.s = false;
        this.L = 0L;
        this.t = false;
        this.H = "ImmerVision PURE Player PRO (tm) for Java v1.10 Copyright (c) ImmerVision 2002-2007 all rights reserved.";
        this.void = this;
        this.F = null;
        this.a = false;
        this.D = false;
        this.m = 1;
        this.j = false;
        this.new = 0;
        this.p = null;
        this.g = null;
        this.for = 1.0f;
        this.I = false;
        this.B = "";
        this.J = false;
        this.case = new Object();
        this.d = false;
        this.h = null;
        this.else = null;
        this.b = false;
        this.byte = false;
        this.f = false;
        this.try = true;
        this.r = 0L;
        this.E = null;
        this.l = null;
        this.c = "";
        this.do = 0;
    }
    
    public String getAppletInfo() {
        return this.H;
    }
    
    public void init() {
        this.n = new an();
        this.n.case = this;
        System.out.println(this.H);
        try {
            final String property = System.getProperty("java.version");
            this.new = property.charAt(2) - '0';
            System.out.println("Java Version: " + property + " from " + System.getProperty("java.vendor"));
            System.out.println("Operating system: " + System.getProperty("os.name") + " version " + System.getProperty("os.version") + " on " + System.getProperty("os.arch"));
            if (System.getProperty("os.name").toLowerCase().startsWith("mac")) {
                a.a.a.a.r.z = true;
                if (this.new == 3) {
                    a.a.a.a.r.v = true;
                }
                if (this.new == 4) {
                    a.a.a.a.r.v = true;
                    a.a.a.a.r.u = true;
                }
            }
        }
        catch (Exception ex) {}
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        this.addKeyListener(this);
        this.void = this;
        this.width = this.getSize().width;
        this.height = this.getSize().height;
        this.n.L = this;
        this.n.I = this.getToolkit();
        this.n.W = new a3();
        this.n.aa = new a9(this.n);
        this.n.P = this;
        this.n.if = new MediaTracker(this);
        this.n.s = new a8();
        an.aj = this.new;
        this.new();
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
            this.D = true;
        }
        if (!this.D) {
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
        if (this.byte && !this.a && !this.D) {
            this.a = true;
            this.a(false);
            this.a = false;
        }
        this.a(true);
    }
    
    public void int() {
        if (this.g == null) {
            Container container;
            for (container = this.getParent(); !(container instanceof Frame); container = container.getParent()) {}
            final Frame frame = (Frame)container;
            if (this.w != null) {
                this.g = new bx(this, frame, "v1.10", this.A.e, this.A.new, this.A.s, this.w.e, this.w.new, this.w.s);
            }
            else {
                this.g = new bx(this, frame, "v1.10", this.A.e, this.A.new, this.A.s, "", "", "");
            }
        }
        else {
            this.g.toFront();
        }
    }
    
    public void for(final String s) {
        if (this.p == null) {
            Container container;
            for (container = this.getParent(); !(container instanceof Frame); container = container.getParent()) {}
            this.p = new b9((Frame)container, "v1.10", this, s);
        }
        else {
            this.p.toFront();
        }
    }
    
    public void start() {
        if (this.A != null) {
            return;
        }
        this.if.new();
        this.G = new v();
        this.char = new bv(this.getDocumentBase(), this.G, this.n);
        this.n.B = this.char;
        this.G.b = this.n;
        this.A = new ae(this.n, this.G, this);
        final String parameter = this.getParameter("optimizememory");
        if (parameter != null && parameter.toLowerCase().compareTo("true") == 0) {
            System.out.println("Memory optimization enabled.");
            this.n.T = true;
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
                this.n.ar = floatValue;
                System.out.println("Quality set to " + parameter2);
            }
            catch (Exception ex2) {
                this.n.ar = 100.0f;
                System.out.println("Error in Quality parameter. Quality is set to default.");
            }
        }
        final String parameter3 = this.getParameter("lockzenithnadir");
        if (parameter3 != null && parameter3.toLowerCase().compareTo("true") == 0) {
            System.out.println("Lock camera movements at zenith and nadir.");
            this.n.for = true;
        }
        final String parameter4 = this.getParameter("singlepanorama");
        if (parameter4 != null && parameter4.toLowerCase().compareTo("true") == 0) {
            System.out.println("Single panorama mode enabled.");
            this.n.K = true;
        }
        final String parameter5 = this.getParameter("usecache");
        if (parameter5 != null) {
            if (parameter5.toLowerCase().compareTo("true") == 0) {
                this.n.k = true;
            }
            else if (parameter5.toLowerCase().compareTo("smart") == 0 && an.aj < 3) {
                this.n.k = true;
            }
        }
        else if (an.aj < 3) {
            this.n.k = true;
        }
        System.out.println("Internal cache system " + (this.n.k ? "enabled" : "disabled") + ".");
        final String parameter6 = this.getParameter("antialiasing");
        if (parameter6 != null) {
            if (parameter6.toLowerCase().compareTo("none") == 0) {
                System.out.println("No antialiasing.");
                this.m = 0;
            }
            else if (parameter6.toLowerCase().compareTo("everytime") == 0) {
                System.out.println("Full antialiasing.");
                this.m = 2;
            }
            else {
                System.out.println("Antialiasing on stop.");
            }
        }
        else {
            System.out.println("Antialiasing on stop.");
        }
        if (this.t) {
            this.A.a(this.char.a(this.getParameter(new String(an.p)), null, false, false, false));
            this.try = false;
        }
        else {
            this.A.a(this.char.a(new String(an.Y), null, false, false, false));
        }
        while (!this.A.k || !this.A.new()) {
            try {
                Thread.sleep(100L);
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            this.A.a(System.currentTimeMillis());
        }
        if (!this.t) {
            final String parameter7 = this.getParameter("gui");
            this.G.f();
            if (parameter7 == null) {
                this.f = true;
            }
            else {
                (this.i = new ae(this.n, this.G, this)).a(this.char.a(parameter7, null, false, false, false));
            }
        }
        final String parameter8 = this.getParameter("transition");
        if (parameter8 != null) {
            this.B = parameter8;
        }
        this.G.d();
        this.A.if(System.currentTimeMillis());
        this.G.a((int)this.A.L);
        this.r = System.currentTimeMillis();
        this.A.L += this.r;
        this.L = System.currentTimeMillis();
        this.A.long = true;
        (this.C = new Thread(this)).start();
        this.J = true;
    }
    
    public void run() {
        while (!this.n.G) {
            Thread.yield();
            long n = System.currentTimeMillis();
            try {
                if (n - this.L < 40L) {
                    Thread.sleep(this.L + 40L - n);
                }
                n = System.currentTimeMillis();
                if (this.try) {
                    this.G.by += (int)(n - this.r);
                    this.r = n;
                }
                this.L = n;
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
            if (this.width != this.void.getSize().width || (this.height != this.void.getSize().height - (((this.a | this.D) & !a.a.a.a.r.v) ? 30 : 0) - (((this.a | this.D) & a.a.a.a.r.v) ? 22 : 0) | this.I)) {
                this.width = this.void.getSize().width;
                this.height = this.void.getSize().height - (((this.a | this.D) & !a.a.a.a.r.v) ? 30 : 0) - (((this.a | this.D) & a.a.a.a.r.v) ? 22 : 0);
                if (this.if != null) {
                    this.if.new();
                }
                if (this.A != null) {
                    this.A.a(("resize(" + this.width + "," + this.height + ");\u0000").toCharArray());
                    if (this.A.H != null) {
                        this.A.H.j();
                    }
                    this.A.long = true;
                }
                this.I = false;
                if (this.w != null) {
                    this.w.long = true;
                }
                b = true;
            }
            if (this.try && !this.f) {
                this.i.a(n);
                if (!this.f && this.i.k && System.currentTimeMillis() > this.A.L && this.A.L != 0L && this.i.new()) {
                    if (this.A != null) {
                        this.A.a();
                        this.G.b = this.n;
                        this.G.f();
                        this.A = null;
                    }
                    this.A = this.i;
                    this.i = null;
                    this.try = false;
                    this.G.for = false;
                    this.G.d();
                    this.A.long = true;
                    this.A.if(n);
                }
            }
            else if (this.try && this.f && System.currentTimeMillis() > this.A.L && this.A.L != 0L) {
                this.try = false;
                this.i = null;
                this.G.for = false;
                this.G.f();
            }
            if (this.A != null) {
                boolean b2 = b | this.A.a(n);
                for (int i = 0; i < this.long; ++i) {
                    this.k[i].do(n);
                    if (!this.k[i].S) {
                        if (!this.k[i].z) {
                            this.k[i].if(true);
                        }
                        if (this.k[i].z) {
                            this.k[i].S = true;
                            this.do(this.k[i].M);
                        }
                    }
                    else if (!this.k[i].z) {
                        this.k[i].if(true);
                    }
                }
                if (this.A.h && !this.try) {
                    if (this.w == null && this.e == null) {
                        final String parameter = this.getParameter("panorama");
                        if (parameter != null) {
                            this.e = new t(this.n, this, false);
                            this.e.M = parameter;
                            this.e.if(this.char.a(parameter, null, false, false, false));
                        }
                    }
                    else if (this.e != null && !this.e.h && this.e.if(false)) {
                        if (this.e.X) {
                            this.e = null;
                        }
                        else {
                            if (!this.e.P) {
                                this.A.H.a(this.e);
                            }
                            if (this.w != null) {
                                if (this.e.R) {
                                    this.e.W.cE = this.w.W.cE - this.w.T + this.e.T;
                                    this.e.W.cx = 0.0f;
                                    this.e.W.cy = this.w.W.cy;
                                }
                                if (this.B.length() > 0) {
                                    this.E = this.w.try();
                                }
                            }
                            else if (this.B.length() > 0 && this.E == null) {
                                this.E = this.A.H.g();
                            }
                            if (this.E != null) {
                                try {
                                    String s = null;
                                    if (this.n.e != null && this.B.toLowerCase().startsWith("trans")) {
                                        s = this.n.e.getProperty(this.B.toLowerCase());
                                    }
                                    if (s == null) {
                                        s = "com.immervision.pure.player." + this.B;
                                    }
                                    final ay ay = (ay)Class.forName(s).getConstructor((Class<?>[])new Class[0]).newInstance((Object[])null);
                                    if (ay != null) {
                                        this.e.W.a(ay);
                                    }
                                    else {
                                        System.out.println("Can't load transition: " + this.B);
                                    }
                                }
                                catch (Exception ex) {
                                    System.out.println("Can't load transition: " + this.B);
                                }
                                this.e.W.if(this.E);
                                this.E = null;
                            }
                            this.e.for(n);
                            if (this.w != null) {
                                this.w.if("enddisplay();\u0000".toCharArray());
                                this.A.a(("enddisplay(\"" + this.w.M + "\");\u0000").toCharArray());
                                this.w.a();
                                this.w = null;
                            }
                            this.w = this.e;
                            this.e = null;
                            b2 = true;
                            this.A.a(("startdisplay(\"" + this.w.M + "\");\u0000").toCharArray());
                        }
                    }
                    if (this.e != null) {
                        this.e.do(n);
                    }
                }
                if (b2 || this.d) {
                    this.A.for();
                    if (this.w != null && this.w.O) {
                        this.a(this.l, this.c, this.do);
                    }
                    this.d = false;
                    this.if.for();
                }
            }
        }
        // monitorexit(this.case)
    }
    
    public void repaint() {
        this.d = true;
    }
    
    public void update(final Graphics graphics) {
        this.d = true;
    }
    
    public void paint(final Graphics graphics) {
        this.d = true;
    }
    
    public String execpano(final String s) {
        synchronized (this.case) {
            if (this.w != null && this.w.z) {
                // monitorexit(this.case)
                return az.for(this.w.if((String.valueOf(s) + "\u0000").toCharArray()));
            }
        }
        // monitorexit(this.case)
        return "";
    }
    
    public String execgui(final String s) {
        synchronized (this.case) {
            if (this.A != null && this.A.new()) {
                // monitorexit(this.case)
                return az.for(this.A.a((String.valueOf(s) + "\u0000").toCharArray()));
            }
        }
        // monitorexit(this.case)
        return "";
    }
    
    public bi int(final String s) {
        synchronized (this.case) {
            if (this.w != null && this.w.z) {
                // monitorexit(this.case)
                return this.w.if((String.valueOf(s) + "\u0000").toCharArray());
            }
            // monitorexit(this.case)
            return new bi();
        }
    }
    
    public bi a(final String s) {
        synchronized (this.case) {
            if (this.A != null && this.A.new()) {
                // monitorexit(this.case)
                return this.A.a((String.valueOf(s) + "\u0000").toCharArray());
            }
            // monitorexit(this.case)
            return new bi();
        }
    }
    
    public void if(final String m, final aq aq) {
        for (int i = 0; i < this.long; ++i) {
            if (m.compareTo(this.k[i].M) == 0) {
                this.k[i].S = false;
                this.k[i].if(true);
                return;
            }
        }
        if (this.k == null || this.k.length == this.long) {
            final t[] k = new t[this.long + 10];
            for (int j = 0; j < this.long; ++j) {
                k[j] = this.k[j];
            }
            this.k = k;
        }
        if (this.w != null && m.compareTo(this.w.M) == 0) {
            this.k[this.long] = this.w;
            this.k[this.long].S = false;
            ++this.long;
            this.w.if(true);
            return;
        }
        this.k[this.long] = new t(this.n, this, false);
        this.k[this.long].M = m;
        this.k[this.long].if(this.char.a(m, aq, false, false, false));
        ++this.long;
    }
    
    public boolean if(final String s) {
        for (int i = 0; i < this.long; ++i) {
            if (this.k[i].M.compareTo(s) == 0) {
                for (int j = i; j < this.long - 1; ++j) {
                    this.k[j] = this.k[j + 1];
                }
                --this.long;
                return true;
            }
        }
        return false;
    }
    
    public void do(final String s) {
        this.A.a(("ispreloaded(\"" + s + "\")\u0000").toCharArray());
        if (this.w != null) {
            this.w.if(("ispreloaded(" + s + ")\u0000").toCharArray());
        }
    }
    
    public void byte(final String s) {
        this.A.a(("loadfinished(\"" + s + "\")\u0000").toCharArray());
        if (this.w != null) {
            this.w.if("loadfinished()\u0000".toCharArray());
        }
    }
    
    public boolean new(final String s) {
        int i = 0;
        while (i < this.long) {
            if (this.k[i].M.compareTo(s) == 0) {
                if (this.k[i].z) {
                    this.e = this.k[i];
                    this.if(s);
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
        for (int i = 0; i < this.long; ++i) {
            if (m.compareTo(this.k[i].M) == 0) {
                this.k[i].if(true);
                return;
            }
        }
        if (this.k == null || this.k.length == this.long) {
            final t[] k = new t[this.long + 10];
            for (int j = 0; j < this.long; ++j) {
                k[j] = this.k[j];
            }
            this.k = k;
        }
        this.k[this.long] = new t(this.n, this, false);
        this.k[this.long].M = m;
        this.k[this.long].if(this.char.a(m, aq, false, false, false));
        ++this.long;
    }
    
    public void try(final String s) {
        this.A.a(("ispreloaded(\"" + s + "\")\u0000").toCharArray());
        if (this.w != null) {
            this.w.if(("ispreloaded(" + s + ")\u0000").toCharArray());
        }
    }
    
    private void if() {
        if (this.h == null) {
            return;
        }
        if (this.e != null || (this.w != null && !this.w.Z)) {
            this.h = null;
            this.else = null;
            return;
        }
        if (!this.n.k) {
            this.char.do();
        }
        if (this.n.K && this.w != null) {
            if (this.B != null && this.B.length() > 0) {
                this.E = this.w.try();
            }
            this.w.if("enddisplay();\u0000".toCharArray());
            this.A.a(("enddisplay(\"" + this.w.M + "\");\u0000").toCharArray());
            this.w.a();
            this.w = null;
            this.A.H.h();
            System.gc();
        }
        (this.e = new t(this.n, this, this.b)).if(this.char.a(this.h, this.else, false, false, false));
        this.h = null;
        this.else = null;
    }
    
    public void a(final String h, final aq else1, final boolean b) {
        this.h = h;
        this.else = else1;
        this.b = b;
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
        if (s != null) {
            try {
                if (a.a.a.a.r.z) {
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
        if (this.w != null) {
            if1 = this.w.if(array);
        }
        if (if1 != null) {
            return if1;
        }
        System.out.println("Error in the command sent to panorama script environment.");
        return new bi();
    }
    
    public bi do(final char[] array) {
        bi a = null;
        if (this.A != null) {
            a = this.A.a(array);
        }
        if (a != null) {
            return a;
        }
        System.out.println("Error in the command sent to gui script environment.");
        return new bi();
    }
    
    private void do() {
        if (this.n.au != this.K.for) {
            this.n.au = this.K.for;
            switch (this.K.for) {
                case 3: {
                    if (this.A != null && this.A.D.bK[0] != null) {
                        this.void.setCursor(this.A.D.bK[0]);
                        break;
                    }
                    this.void.setCursor(Cursor.getPredefinedCursor(0));
                    break;
                }
                case 0: {
                    if (this.A != null && this.A.D.bK[1] != null) {
                        this.void.setCursor(this.A.D.bK[1]);
                        break;
                    }
                    this.void.setCursor(Cursor.getPredefinedCursor(12));
                    break;
                }
                case 1: {
                    if (this.A != null && this.A.D.bK[2] != null) {
                        this.void.setCursor(this.A.D.bK[2]);
                        break;
                    }
                    this.void.setCursor(Cursor.getPredefinedCursor(13));
                    break;
                }
                case 2: {
                    if (this.A != null && this.A.D.bK[3] != null) {
                        this.void.setCursor(this.A.D.bK[3]);
                        break;
                    }
                    this.void.setCursor(Cursor.getPredefinedCursor(9));
                    break;
                }
            }
        }
        for (int i = 0; i < this.K.byte; ++i) {
            if (this.K.c[i]) {
                this.w.if(this.K.b[i]);
            }
            else {
                this.A.a(this.K.b[i]);
            }
        }
    }
    
    public void destroy() {
        this.n.G = true;
        try {
            if (this.g != null) {
                this.g.dispose();
                this.g = null;
            }
            if (this.p != null) {
                this.p.dispose();
                this.p = null;
            }
            if (this.w != null) {
                this.w.a();
            }
            if (this.A != null) {
                this.A.a();
            }
            this.n.a();
            this.if.if();
            this.if = null;
            this.int.if();
            this.int = null;
            if (this.goto != null) {
                this.goto.if();
            }
            this.goto = null;
            if (this.F != null) {
                if (this.new >= 4) {
                    ((Dialog)this.F).dispose();
                }
                else {
                    ((Window)this.F).dispose();
                }
            }
            this.F = null;
            this.K = null;
            if (this.k != null) {
                for (int i = 0; i < this.k.length; ++i) {
                    if (this.k[i] != null) {
                        this.k[i].a();
                    }
                }
            }
            this.char = null;
            if (this.k != null) {
                for (int j = 0; j < this.k.length; ++j) {
                    if (this.k[j] != null) {
                        this.k[j].a();
                    }
                    this.k[j] = null;
                }
            }
            this.k = null;
            if (this.e != null) {
                this.e.a();
            }
            this.e = null;
            this.G.if();
            this.G = null;
            this.void = null;
            this.F = null;
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
    
    private void a(final MouseEvent l, final String s, final int if1) {
        if (l == null) {
            return;
        }
        this.l = l;
        switch (if1) {
            case 1:
            case 2:
            case 3: {
                this.do = 3;
                this.c = "mousemoved";
                break;
            }
            case 0:
            case 4: {
                this.do = 4;
                this.c = "mousedragged";
                break;
            }
        }
        if (!this.J) {
            return;
        }
        int n = 0;
        if ((l.getModifiers() & 0x4) != 0x0) {
            n |= 0x4;
        }
        else if ((l.getModifiers() & 0x10) != 0x0) {
            n |= 0x1;
        }
        else if ((l.getModifiers() & 0x8) != 0x0) {
            n |= 0x2;
        }
        if (this.K != null) {
            this.K.a();
            this.K.goto = l.getX();
            this.K.else = l.getY();
        }
        if (s.compareTo("mousemoved") == 0) {
            final char[] charArray = (String.valueOf(s) + "(" + l.getX() + "," + l.getY() + ")\u0000").toCharArray();
            if (this.A != null && this.A.h) {
                this.A.a(charArray);
                if (this.w != null && this.A.H != null && this.A.H.for) {
                    this.w.if((String.valueOf(s) + "(" + (l.getX() - this.A.H.bh) + "," + (l.getY() - this.A.H.bg) + ")\u0000").toCharArray());
                }
            }
        }
        else if (s.compareTo("mouseexited") == 0) {
            if (this.K != null) {
                this.K.goto = -65536;
                this.K.else = -65536;
            }
            final char[] charArray2 = "mouseexited()\u0000".toCharArray();
            if (this.A != null && this.A.h) {
                this.A.a(charArray2);
                if (this.w != null && this.A.H.for) {
                    this.w.if(charArray2);
                }
            }
        }
        else {
            final char[] charArray3 = (String.valueOf(s) + "(" + l.getX() + "," + l.getY() + "," + n + ")\u0000").toCharArray();
            if (this.A != null && this.A.h) {
                this.A.a(charArray3);
                if (this.w != null && this.A.H != null && this.A.H.for) {
                    this.w.if((String.valueOf(s) + "(" + (l.getX() - this.A.H.bh) + "," + (l.getY() - this.A.H.bg) + "," + n + ")\u0000").toCharArray());
                }
            }
        }
        if (this.K == null) {
            return;
        }
        if ((l.getModifiers() & 0x4) != 0x0) {
            this.K.g = 5;
        }
        else if ((l.getModifiers() & 0x10) != 0x0) {
            this.K.g = 6;
        }
        else if ((l.getModifiers() & 0x8) != 0x0) {
            this.K.g = 7;
        }
        if (this.j) {
            this.K.g = 5;
        }
        if (this.o) {
            return;
        }
        this.K.if = if1;
        if (this.A != null) {
            this.A.if(this.K);
        }
        this.do();
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        synchronized (this.case) {
            if (!this.J) {
                // monitorexit(this.case)
                return;
            }
            final char[] charArray = "mouseentered()\u0000".toCharArray();
            if (this.A != null && this.A.h) {
                this.A.a(charArray);
                if (this.w != null && this.A.H.for) {
                    this.w.if(charArray);
                }
            }
        }
        // monitorexit(this.case)
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        synchronized (this.case) {
            if (!this.J) {
                // monitorexit(this.case)
                return;
            }
            this.a(mouseEvent, "mouseExited", 3);
        }
        // monitorexit(this.case)
    }
    
    public void a(final int n) {
        synchronized (this.case) {
            if (!this.J) {
                // monitorexit(this.case)
                return;
            }
            if (this.w != null) {
                char[] array;
                if (n > 0) {
                    array = "mousewheel(1)\u0000".toCharArray();
                }
                else {
                    array = "mousewheel(-1)\u0000".toCharArray();
                }
                if (this.A != null && this.A.h) {
                    this.A.a(array);
                    if (this.w != null && this.A.H.for) {
                        this.w.if(array);
                    }
                }
                if (this.o) {
                    // monitorexit(this.case)
                    return;
                }
                if (n < 0) {
                    this.w.W.cy -= this.w.W.cy * 0.1f;
                }
                else {
                    this.w.W.cy += this.w.W.cy * 0.1f;
                }
                this.w.W.do = true;
            }
        }
        // monitorexit(this.case)
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
        synchronized (this.case) {
            if (!this.J) {
                // monitorexit(this.case)
                return;
            }
            final char[] charArray = ("keytyped(" + keyEvent.getKeyCode() + ")\u0000").toCharArray();
            if (this.A != null && this.A.h) {
                this.A.a(charArray);
                if (this.w != null && this.A.H.for) {
                    this.w.if(charArray);
                }
            }
        }
        // monitorexit(this.case)
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        synchronized (this.case) {
            if (!this.J) {
                // monitorexit(this.case)
                return;
            }
            final char[] charArray = ("keypressed(" + keyEvent.getKeyCode() + ")\u0000").toCharArray();
            if (this.A != null && this.A.h) {
                this.A.a(charArray);
                if (this.w != null && this.A.H.for) {
                    this.w.if(charArray);
                }
            }
            if (this.s) {
                // monitorexit(this.case)
                return;
            }
            if (keyEvent.getKeyCode() == 17 || keyEvent.getKeyCode() == 16) {
                this.j = true;
            }
            if (keyEvent.getKeyCode() == 112 || keyEvent.getKeyCode() == 156) {
                this.for("");
            }
            if (keyEvent.getKeyCode() == 113 || keyEvent.getKeyCode() == 36 || keyEvent.getKeyCode() == 154) {
                this.int();
            }
        }
        // monitorexit(this.case)
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
        synchronized (this.case) {
            if (!this.J) {
                // monitorexit(this.case)
                return;
            }
            final char[] charArray = ("keyreleased(" + keyEvent.getKeyCode() + ")\u0000").toCharArray();
            if (this.A != null && this.A.h) {
                this.A.a(charArray);
                if (this.w != null && this.A.H.for) {
                    this.w.if(charArray);
                }
            }
            if (this.s) {
                this.j = false;
                // monitorexit(this.case)
                return;
            }
            if (keyEvent.getKeyCode() == 17 || keyEvent.getKeyCode() == 16) {
                this.j = false;
            }
        }
        // monitorexit(this.case)
    }
    
    public void a(final boolean visible) {
        this.I = true;
        try {
            if (this.a || this.D) {
                if (this.F == null || this.goto.z != this.a || this.goto.y != this.D) {
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
                        this.F = new bk(frame, this);
                        ((bk)this.F).setUndecorated(true);
                    }
                    else {
                        this.F = new x(frame, this);
                    }
                    int width = this.F.getToolkit().getScreenSize().width;
                    final int height = this.F.getToolkit().getScreenSize().height;
                    if (this.D) {
                        width *= 2;
                    }
                    if (a.a.a.a.r.u) {
                        this.F.setLocation(0, 22);
                    }
                    else {
                        this.F.setLocation(0, 0);
                    }
                    this.F.setSize(width, height);
                    if (a.a.a.a.r.u) {
                        this.F.reshape(0, 22, width, height + (a.a.a.a.r.v ? 0 : 30));
                    }
                    else {
                        this.F.reshape(0, 0, width, height + (a.a.a.a.r.v ? 0 : 30));
                    }
                    this.width = this.F.getSize().width;
                    this.height = this.F.getSize().height - (((this.a | this.D) & !a.a.a.a.r.v) ? 30 : 0) - (((this.a | this.D) & a.a.a.a.r.v) ? 22 : 0);
                    this.F.addMouseMotionListener(this);
                    this.F.addMouseListener(this);
                    this.F.addKeyListener(this);
                    this.F.setVisible(true);
                    ((Window)this.F).toFront();
                    this.F.requestFocus();
                    if (this.new >= 4) {
                        try {
                            new a0(this, this.F);
                        }
                        catch (Throwable t) {}
                    }
                    if (this.goto == null || this.goto.z != this.a || this.goto.y != this.D) {
                        Label_0581: {
                            if (this.new >= 3) {
                                try {
                                    (this.goto = new b1()).a(this.F, true, this);
                                    break Label_0581;
                                }
                                catch (Exception ex2) {
                                    an.ah = false;
                                    this.goto = new bs();
                                    try {
                                        this.goto.a(this.F, true, this);
                                    }
                                    catch (Exception ex3) {}
                                    break Label_0581;
                                }
                            }
                            this.goto = new bs();
                            try {
                                this.goto.a(this.F, true, this);
                            }
                            catch (Exception ex4) {}
                        }
                        if (this.A != null && this.A.H != null) {
                            this.A.H.j();
                        }
                        if (this.A != null && this.w != null) {
                            this.w.W.a(this.A.H.aO);
                        }
                    }
                }
                this.F.setVisible(visible);
                this.void = this.F;
                this.if = this.goto;
                this.if.z = this.a;
                this.if.y = this.D;
                this.n.goto = this.if;
            }
            else {
                if ((this.a || this.D) && this.if != null) {
                    return;
                }
                if (this.F != null) {
                    final int n = this.if.s * this.if.r;
                    try {
                        for (int i = 0; i < n; ++i) {
                            this.if.x[i] = -1;
                        }
                    }
                    catch (Exception ex5) {}
                    this.F.setVisible(false);
                }
                if (this.int == null) {
                    this.for();
                }
                this.void = this;
                this.if = this.int;
            }
            if (this.w != null) {
                this.w.long = true;
            }
            if (this.A != null) {
                this.A.long = true;
            }
            this.width = this.void.getSize().width;
            this.height = this.void.getSize().height - (((this.a | this.D) & !a.a.a.a.r.v) ? 30 : 0) - (((this.a | this.D) & a.a.a.a.r.v) ? 22 : 0);
            this.if.new();
        }
        catch (OutOfMemoryError outOfMemoryError) {
            this.a = false;
            this.D = false;
            if (this.int == null) {
                this.for();
            }
            if (this.F != null) {
                if (this.new >= 4) {
                    ((Dialog)this.F).dispose();
                }
                else {
                    ((Window)this.F).dispose();
                }
            }
            this.F = null;
            this.goto = null;
            System.gc();
            if (this.int == null) {
                this.for();
            }
            this.void = this;
            this.if = this.int;
            System.out.println("Can't go fullscreen. Out of memory.");
        }
        this.width = this.void.getSize().width;
        this.height = this.void.getSize().height - (((this.a | this.D) & !a.a.a.a.r.v) ? 30 : 0) - (((this.a | this.D) & a.a.a.a.r.v) ? 22 : 0);
        this.n.goto = this.if;
    }
    
    private void for() {
        this.void = this;
        Label_0116: {
            if (this.int == null) {
                if (this.new >= 3) {
                    try {
                        (this.int = new b1()).a(this.void, false, this);
                        break Label_0116;
                    }
                    catch (Exception ex) {
                        an.ah = false;
                        this.int = null;
                        this.int = new bs();
                        try {
                            this.int.a(this.void, false, this);
                        }
                        catch (Exception ex2) {}
                        break Label_0116;
                    }
                }
                this.int = new bs();
                try {
                    this.int.a(this.void, false, this);
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
                    this.n.e = e;
                }
                catch (Exception ex) {}
                byteArrayInputStream.close();
            }
            catch (Exception ex2) {}
        }
    }
    
    public Image a() {
        if (this.if != null) {
            return this.if.C;
        }
        return null;
    }
}
