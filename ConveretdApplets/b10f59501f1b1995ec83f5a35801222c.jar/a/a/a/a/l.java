// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

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
import java.awt.Image;
import java.awt.Component;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.KeyListener;
import java.applet.Applet;

public class l extends Applet implements KeyListener, MouseMotionListener, MouseListener, Runnable
{
    private i int;
    private i case;
    private i b;
    ab O;
    m[] l;
    int c;
    be long;
    protected v E;
    protected m B;
    protected m h;
    protected ac q;
    o J;
    Thread G;
    int width;
    int height;
    boolean r;
    boolean w;
    long P;
    public static final String u = "v1.01";
    String L;
    Component d;
    Component I;
    boolean if;
    boolean H;
    public int o;
    boolean k;
    public int char;
    bq t;
    bg i;
    static boolean C;
    static boolean A;
    static boolean z;
    int D;
    int for;
    int Q;
    int p;
    int n;
    int v;
    int s;
    int new;
    int do;
    boolean a;
    Image K;
    float byte;
    boolean M;
    String F;
    boolean N;
    Object goto;
    private boolean g;
    private String j;
    private ae void;
    private boolean e;
    private boolean else;
    MouseEvent m;
    String f;
    int try;
    static /* synthetic */ Class class$0;
    static /* synthetic */ Class class$1;
    
    static {
        l.C = false;
        l.A = false;
        l.z = false;
    }
    
    public l() {
        this.int = null;
        this.case = null;
        this.b = null;
        this.O = new ab();
        this.l = null;
        this.c = 0;
        this.long = null;
        this.E = null;
        this.B = null;
        this.h = null;
        this.q = null;
        this.J = null;
        this.r = false;
        this.w = false;
        this.P = 0L;
        this.L = "ImmerVision PURE Player PRO (tm) for Java v1.01 Copyright (c) ImmerVision 2002-2006 all rights reserved.";
        this.d = this;
        this.I = null;
        this.if = false;
        this.H = false;
        this.o = 1;
        this.k = false;
        this.char = 0;
        this.t = null;
        this.i = null;
        this.D = -1;
        this.for = -16777063;
        this.Q = -2236963;
        this.p = -1000;
        this.n = -1000;
        this.v = -1000;
        this.s = -1000;
        this.new = -1000;
        this.do = -1000;
        this.a = true;
        this.K = null;
        this.byte = 1.0f;
        this.M = false;
        this.F = "";
        this.N = false;
        this.goto = new Object();
        this.g = false;
        this.j = null;
        this.void = null;
        this.e = false;
        this.else = false;
        this.m = null;
        this.f = "";
        this.try = 0;
    }
    
    public String getAppletInfo() {
        return this.L;
    }
    
    public void init() {
        this.q = new ac();
        this.q.case = this;
        System.out.println(this.L);
        try {
            final String property = System.getProperty("java.version");
            this.char = property.charAt(2) - '0';
            System.out.println("Java Version: " + property + " from " + System.getProperty("java.vendor"));
            System.out.println("Operating system: " + System.getProperty("os.name") + " version " + System.getProperty("os.version") + " on " + System.getProperty("os.arch"));
            if (System.getProperty("os.name").toLowerCase().startsWith("mac")) {
                a.a.a.a.l.C = true;
                if (this.char == 3) {
                    a.a.a.a.l.A = true;
                }
                if (this.char == 4) {
                    a.a.a.a.l.A = true;
                    a.a.a.a.l.z = true;
                }
            }
        }
        catch (Exception ex) {}
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        this.addKeyListener(this);
        this.d = this;
        this.width = this.getSize().width;
        this.height = this.getSize().height;
        ac.K = this;
        this.q.H = this.getToolkit();
        this.q.V = new ap();
        this.q.Y = new av(this.q);
        this.q.O = this;
        this.q.if = new MediaTracker(this);
        this.q.r = new au();
        ac.ah = this.char;
        final String parameter = this.getParameter("barbgcolor");
        if (parameter != null) {
            this.Q = (0xFF000000 | ac.a(parameter));
        }
        final String parameter2 = this.getParameter("barfgcolor");
        if (parameter2 != null) {
            this.for = (0xFF000000 | ac.a(parameter2));
        }
        final String parameter3 = this.getParameter("maximagesize");
        if (parameter3 != null) {
            ac.b = ac.a(parameter3);
            if (ac.b < 0) {
                ac.b = 0;
            }
        }
        if (this.char >= 4) {
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
        final String parameter4 = this.getParameter("bgcolor");
        if (parameter4 != null) {
            this.D = (0xFF000000 | ac.a(parameter4));
        }
        final String parameter5 = this.getParameter("mousespeed");
        if (parameter5 != null) {
            this.byte = Float.valueOf(parameter5) / 100.0f;
            if (this.byte < 0.01 || this.byte > 100.0f) {
                this.byte = 1.0f;
            }
        }
        final String parameter6 = this.getParameter("smoothstop");
        if (parameter6 != null) {
            ac.g = Float.valueOf(parameter6);
            if (ac.g > 0.0f) {
                ac.g = 1.0f - Float.valueOf(parameter6) / 1000.0f;
            }
            if (ac.g >= 1.0f || ac.g < 0.0f) {
                ac.g = 0.0f;
            }
        }
        final String parameter7 = this.getParameter("fullscreen2");
        if (parameter7 != null && parameter7.toLowerCase().compareTo("true") == 0) {
            this.H = true;
        }
        if (!this.H) {
            final String parameter8 = this.getParameter("fullscreen");
            if (parameter8 != null && parameter8.toLowerCase().compareTo("true") == 0) {
                this.if = true;
            }
        }
        final String parameter9 = this.getParameter("protectfullscreen");
        if (parameter9 != null && parameter9.toLowerCase().compareTo("true") == 0) {
            this.else = true;
        }
        this.for();
        if (this.else && !this.if && !this.H) {
            this.if = true;
            this.a(false);
            this.if = false;
        }
        this.a(true);
    }
    
    public void int() {
        if (this.i == null) {
            Container container;
            for (container = this.getParent(); !(container instanceof Frame); container = container.getParent()) {}
            final Frame frame = (Frame)container;
            if (this.B != null) {
                this.i = new bg(this, frame, "v1.01", this.E.e, this.E.new, this.E.s, this.B.e, this.B.new, this.B.s);
            }
            else {
                this.i = new bg(this, frame, "v1.01", this.E.e, this.E.new, this.E.s, "", "", "");
            }
        }
        else {
            this.i.toFront();
        }
    }
    
    public void for(final String s) {
        if (this.t == null) {
            Container container;
            for (container = this.getParent(); !(container instanceof Frame); container = container.getParent()) {}
            this.t = new bq((Frame)container, "v1.01", this, s);
        }
        else {
            this.t.toFront();
        }
    }
    
    public void start() {
        if (this.E != null) {
            return;
        }
        this.int.try();
        this.int.new();
        if (this.p != -1000 && this.n != -1000 && this.v != -1000 && this.s != -1000) {
            this.J = new o(this.Q, this.for, this.p, this.n, this.v, this.s);
        }
        else {
            this.J = new o(this.Q, this.for, 0, this.height - 5, this.width, 5);
        }
        this.long = new be(this.getDocumentBase(), this.J, this.q);
        this.q.A = this.long;
        this.J.b = this.q;
        this.E = new v(this.q, this.J, this);
        final String parameter = this.getParameter("optimizememory");
        if (parameter != null && parameter.toLowerCase().compareTo("true") == 0) {
            System.out.println("Memory optimization enabled.");
            this.q.S = true;
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
                this.q.ap = floatValue;
                System.out.println("Quality set to " + parameter2);
            }
            catch (Exception ex) {
                this.q.ap = 100.0f;
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
            this.q.J = true;
        }
        final String parameter5 = this.getParameter("usecache");
        if (parameter5 != null) {
            if (parameter5.toLowerCase().compareTo("true") == 0) {
                this.q.k = true;
            }
            else if (parameter5.toLowerCase().compareTo("smart") == 0 && ac.ah < 3) {
                this.q.k = true;
            }
        }
        else if (ac.ah < 3) {
            this.q.k = true;
        }
        System.out.println("Internal cache system " + (this.q.k ? "enabled" : "disabled") + ".");
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
        final String parameter7 = this.getParameter("gui");
        if (parameter7 == null) {
            this.E.a(this.long.a("immergui.gif", null, false, false, false));
        }
        else {
            this.E.a(this.long.a(parameter7, null, false, false, false));
        }
        final String parameter8 = this.getParameter("transition");
        if (parameter8 != null) {
            this.F = parameter8;
        }
        (this.G = new Thread(this)).start();
        this.N = true;
    }
    
    public void run() {
        while (!this.q.F) {
            Thread.yield();
            long p = System.currentTimeMillis();
            try {
                if (p - this.P < 40L) {
                    Thread.sleep(this.P + 40L - p);
                }
                p = System.currentTimeMillis();
                this.P = p;
            }
            catch (InterruptedException ex) {}
            try {
                this.a(p);
            }
            catch (NullPointerException ex2) {}
        }
    }
    
    private void a(final long n) {
        boolean b = false;
        synchronized (this.goto) {
            this.if();
            if (this.width != this.d.getSize().width || (this.height != this.d.getSize().height - (((this.if | this.H) & !a.a.a.a.l.A) ? 30 : 0) - (((this.if | this.H) & a.a.a.a.l.A) ? 22 : 0) | this.M)) {
                this.width = this.d.getSize().width;
                this.height = this.d.getSize().height - (((this.if | this.H) & !a.a.a.a.l.A) ? 30 : 0) - (((this.if | this.H) & a.a.a.a.l.A) ? 22 : 0);
                if (this.int != null) {
                    this.int.try();
                }
                if (this.E != null) {
                    this.E.a(("resize(" + this.width + "," + this.height + ");\u0000").toCharArray());
                    if (this.E.J != null) {
                        this.E.J.l();
                    }
                    this.E.long = true;
                }
                this.M = false;
                if (this.B != null) {
                    this.B.long = true;
                }
                b = true;
            }
            if (this.E != null) {
                boolean b2 = b | this.E.a(n);
                for (int i = 0; i < this.c; ++i) {
                    this.l[i].do(n);
                    if (!this.l[i].Q) {
                        if (!this.l[i].z) {
                            this.l[i].if(true);
                        }
                        if (this.l[i].z) {
                            this.l[i].Q = true;
                            this.do(this.l[i].K);
                        }
                    }
                    else if (!this.l[i].z) {
                        this.l[i].if(true);
                    }
                }
                if (this.E.h) {
                    if (this.B == null && this.h == null) {
                        final String parameter = this.getParameter("panorama");
                        if (parameter != null) {
                            this.h = new m(this.q, this, false);
                            this.h.K = parameter;
                            this.h.if(this.long.a(parameter, null, false, false, false));
                        }
                    }
                    else if (this.h != null && !this.h.h && this.h.if(false)) {
                        if (this.h.V) {
                            this.h = null;
                        }
                        else {
                            ad ad = null;
                            if (!this.h.N) {
                                this.E.J.a(this.h);
                            }
                            if (this.B != null) {
                                if (this.h.P) {
                                    this.h.U.cD = this.B.U.cD - this.B.R + this.h.R;
                                    this.h.U.cw = 0.0f;
                                    this.h.U.cx = this.B.U.cx;
                                }
                                if (this.F.length() > 0) {
                                    ad = this.B.new();
                                }
                            }
                            else if (this.F.length() > 0) {
                                ad = this.E.J.i();
                            }
                            if (ad != null) {
                                try {
                                    String s = null;
                                    if (this.q.e != null && this.F.toLowerCase().startsWith("trans")) {
                                        s = this.q.e.getProperty(this.F.toLowerCase());
                                    }
                                    if (s == null) {
                                        s = "com.immervision.pure.player." + this.F;
                                    }
                                    final ak ak = (ak)Class.forName(s).getConstructor((Class<?>[])new Class[0]).newInstance((Object[])null);
                                    if (ak != null) {
                                        this.h.U.a(ak);
                                    }
                                    else {
                                        System.out.println("Can't load transition: " + this.F);
                                    }
                                }
                                catch (Exception ex) {
                                    System.out.println("Can't load transition: " + this.F);
                                }
                            }
                            this.h.U.if(ad);
                            this.h.for(n);
                            if (this.B != null) {
                                this.B.if("enddisplay();\u0000".toCharArray());
                                this.E.a(("enddisplay(\"" + this.B.K + "\");\u0000").toCharArray());
                                this.B.a();
                                this.B = null;
                            }
                            this.B = this.h;
                            this.h = null;
                            b2 = true;
                            this.E.a(("startdisplay(\"" + this.B.K + "\");\u0000").toCharArray());
                        }
                    }
                    if (this.h != null) {
                        this.h.do(n);
                    }
                    if (b2) {
                        this.E.for();
                    }
                }
                else if (this.E.k) {
                    if (this.E.int()) {
                        this.J.h();
                        this.E.if(n);
                        this.K = null;
                    }
                    else {
                        b2 = this.J.a(n);
                        this.J.f();
                    }
                }
                else {
                    b2 = this.J.a(n);
                    this.J.f();
                }
                if (b2 || this.g) {
                    if (this.B != null && this.B.M) {
                        this.a(this.m, this.f, this.try);
                    }
                    this.g = false;
                    this.int.for();
                }
            }
        }
        // monitorexit(this.goto)
    }
    
    public void repaint() {
        this.g = true;
    }
    
    public void update(final Graphics graphics) {
        this.g = true;
    }
    
    public void paint(final Graphics graphics) {
        this.g = true;
    }
    
    public String execpano(final String s) {
        if (this.B != null && this.B.z) {
            return al.for(this.B.if((String.valueOf(s) + "\u0000").toCharArray()));
        }
        return "";
    }
    
    public String execgui(final String s) {
        if (this.E != null && this.E.int()) {
            return al.for(this.E.a((String.valueOf(s) + "\u0000").toCharArray()));
        }
        return "";
    }
    
    public a3 int(final String s) {
        synchronized (this.goto) {
            if (this.B != null && this.B.z) {
                // monitorexit(this.goto)
                return this.B.if((String.valueOf(s) + "\u0000").toCharArray());
            }
            // monitorexit(this.goto)
            return new a3();
        }
    }
    
    public a3 a(final String s) {
        synchronized (this.goto) {
            if (this.E != null && this.E.int()) {
                // monitorexit(this.goto)
                return this.E.a((String.valueOf(s) + "\u0000").toCharArray());
            }
            // monitorexit(this.goto)
            return new a3();
        }
    }
    
    public void if(final String k, final ae ae) {
        for (int i = 0; i < this.c; ++i) {
            if (k.compareTo(this.l[i].K) == 0) {
                this.l[i].Q = false;
                this.l[i].if(true);
                return;
            }
        }
        if (this.l == null || this.l.length == this.c) {
            final m[] l = new m[this.c + 10];
            for (int j = 0; j < this.c; ++j) {
                l[j] = this.l[j];
            }
            this.l = l;
        }
        if (this.B != null && k.compareTo(this.B.K) == 0) {
            this.l[this.c] = this.B;
            this.l[this.c].Q = false;
            ++this.c;
            this.B.if(true);
            return;
        }
        this.l[this.c] = new m(this.q, this, false);
        this.l[this.c].K = k;
        this.l[this.c].if(this.long.a(k, ae, false, false, false));
        ++this.c;
    }
    
    public boolean if(final String s) {
        for (int i = 0; i < this.c; ++i) {
            if (this.l[i].K.compareTo(s) == 0) {
                for (int j = i; j < this.c - 1; ++j) {
                    this.l[j] = this.l[j + 1];
                }
                --this.c;
                return true;
            }
        }
        return false;
    }
    
    public void do(final String s) {
        this.E.a(("ispreloaded(\"" + s + "\")\u0000").toCharArray());
        if (this.B != null) {
            this.B.if(("ispreloaded(" + s + ")\u0000").toCharArray());
        }
    }
    
    public void byte(final String s) {
        this.E.a(("loadfinished(\"" + s + "\")\u0000").toCharArray());
        if (this.B != null) {
            this.B.if("loadfinished()\u0000".toCharArray());
        }
    }
    
    public boolean new(final String s) {
        int i = 0;
        while (i < this.c) {
            if (this.l[i].K.compareTo(s) == 0) {
                if (this.l[i].z) {
                    this.h = this.l[i];
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
    
    public void a(final String k, final ae ae) {
        for (int i = 0; i < this.c; ++i) {
            if (k.compareTo(this.l[i].K) == 0) {
                this.l[i].if(true);
                return;
            }
        }
        if (this.l == null || this.l.length == this.c) {
            final m[] l = new m[this.c + 10];
            for (int j = 0; j < this.c; ++j) {
                l[j] = this.l[j];
            }
            this.l = l;
        }
        this.l[this.c] = new m(this.q, this, false);
        this.l[this.c].K = k;
        this.l[this.c].if(this.long.a(k, ae, false, false, false));
        ++this.c;
    }
    
    public void try(final String s) {
        this.E.a(("ispreloaded(\"" + s + "\")\u0000").toCharArray());
        if (this.B != null) {
            this.B.if(("ispreloaded(" + s + ")\u0000").toCharArray());
        }
    }
    
    private void if() {
        if (this.j == null) {
            return;
        }
        if (this.h != null || (this.B != null && !this.B.X)) {
            this.j = null;
            this.void = null;
            return;
        }
        if (!this.q.k) {
            this.long.do();
        }
        if (this.q.J && this.B != null) {
            this.B.if("enddisplay();\u0000".toCharArray());
            this.E.a(("enddisplay(\"" + this.B.K + "\");\u0000").toCharArray());
            this.B.a();
            this.B = null;
            this.E.J.j();
            System.gc();
        }
        (this.h = new m(this.q, this, this.e)).if(this.long.a(this.j, this.void, false, false, false));
        this.j = null;
        this.void = null;
    }
    
    public void a(final String j, final ae void1, final boolean e) {
        this.j = j;
        this.void = void1;
        this.e = e;
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
        if (this.q.as != this.O.for) {
            this.q.as = this.O.for;
            switch (this.O.for) {
                case 3: {
                    if (this.E != null && this.E.H.bJ[0] != null) {
                        this.d.setCursor(this.E.H.bJ[0]);
                        break;
                    }
                    this.d.setCursor(Cursor.getPredefinedCursor(0));
                    break;
                }
                case 0: {
                    if (this.E != null && this.E.H.bJ[1] != null) {
                        this.d.setCursor(this.E.H.bJ[1]);
                        break;
                    }
                    this.d.setCursor(Cursor.getPredefinedCursor(12));
                    break;
                }
                case 1: {
                    if (this.E != null && this.E.H.bJ[2] != null) {
                        this.d.setCursor(this.E.H.bJ[2]);
                        break;
                    }
                    this.d.setCursor(Cursor.getPredefinedCursor(13));
                    break;
                }
                case 2: {
                    if (this.E != null && this.E.H.bJ[3] != null) {
                        this.d.setCursor(this.E.H.bJ[3]);
                        break;
                    }
                    this.d.setCursor(Cursor.getPredefinedCursor(9));
                    break;
                }
            }
        }
        for (int i = 0; i < this.O.byte; ++i) {
            if (this.O.c[i]) {
                this.B.if(this.O.b[i]);
            }
            else {
                this.E.a(this.O.b[i]);
            }
        }
    }
    
    public void destroy() {
        this.q.F = true;
        try {
            if (this.i != null) {
                this.i.dispose();
                this.i = null;
            }
            if (this.t != null) {
                this.t.dispose();
                this.t = null;
            }
            if (this.B != null) {
                this.B.a();
            }
            if (this.E != null) {
                this.E.a();
            }
            this.q.a();
            this.int.if();
            this.int = null;
            this.case.if();
            this.case = null;
            if (this.b != null) {
                this.b.if();
            }
            this.b = null;
            if (this.I != null) {
                if (this.char >= 4) {
                    ((Dialog)this.I).dispose();
                }
                else {
                    ((Window)this.I).dispose();
                }
            }
            this.I = null;
            this.O = null;
            if (this.l != null) {
                for (int i = 0; i < this.l.length; ++i) {
                    if (this.l[i] != null) {
                        this.l[i].a();
                    }
                }
            }
            this.long = null;
            if (this.l != null) {
                for (int j = 0; j < this.l.length; ++j) {
                    if (this.l[j] != null) {
                        this.l[j].a();
                    }
                    this.l[j] = null;
                }
            }
            this.l = null;
            if (this.h != null) {
                this.h.a();
            }
            this.h = null;
            this.J.if();
            this.J = null;
            this.d = null;
            this.I = null;
            this.K = null;
            System.gc();
            System.out.println("Finished.");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        synchronized (this.goto) {
            this.a(mouseEvent, "mouseclicked", 2);
        }
        // monitorexit(this.goto)
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        synchronized (this.goto) {
            this.a(mouseEvent, "mousepressed", 0);
        }
        // monitorexit(this.goto)
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        synchronized (this.goto) {
            this.a(mouseEvent, "mousereleased", 1);
        }
        // monitorexit(this.goto)
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        synchronized (this.goto) {
            this.a(mouseEvent, "mousedragged", 4);
        }
        // monitorexit(this.goto)
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        synchronized (this.goto) {
            this.a(mouseEvent, "mousemoved", 3);
        }
        // monitorexit(this.goto)
    }
    
    private void a(final MouseEvent m, final String s, final int if1) {
        if (m == null) {
            return;
        }
        this.m = m;
        switch (if1) {
            case 1:
            case 2:
            case 3: {
                this.try = 3;
                this.f = "mousemoved";
                break;
            }
            case 0:
            case 4: {
                this.try = 4;
                this.f = "mousedragged";
                break;
            }
        }
        if (!this.N) {
            return;
        }
        int n = 0;
        if ((m.getModifiers() & 0x4) != 0x0) {
            n |= 0x4;
        }
        else if ((m.getModifiers() & 0x10) != 0x0) {
            n |= 0x1;
        }
        else if ((m.getModifiers() & 0x8) != 0x0) {
            n |= 0x2;
        }
        if (this.O != null) {
            this.O.a();
            this.O.goto = m.getX();
            this.O.else = m.getY();
        }
        if (s.compareTo("mousemoved") == 0) {
            final char[] charArray = (String.valueOf(s) + "(" + m.getX() + "," + m.getY() + ")\u0000").toCharArray();
            if (this.E != null && this.E.h) {
                this.E.a(charArray);
                if (this.B != null && this.E.J != null && this.E.J.for) {
                    this.B.if((String.valueOf(s) + "(" + (m.getX() - this.E.J.bg) + "," + (m.getY() - this.E.J.bf) + ")\u0000").toCharArray());
                }
            }
        }
        else if (s.compareTo("mouseexited") == 0) {
            if (this.O != null) {
                this.O.goto = -65536;
                this.O.else = -65536;
            }
            final char[] charArray2 = "mouseexited()\u0000".toCharArray();
            if (this.E != null && this.E.h) {
                this.E.a(charArray2);
                if (this.B != null && this.E.J.for) {
                    this.B.if(charArray2);
                }
            }
        }
        else {
            final char[] charArray3 = (String.valueOf(s) + "(" + m.getX() + "," + m.getY() + "," + n + ")\u0000").toCharArray();
            if (this.E != null && this.E.h) {
                this.E.a(charArray3);
                if (this.B != null && this.E.J != null && this.E.J.for) {
                    this.B.if((String.valueOf(s) + "(" + (m.getX() - this.E.J.bg) + "," + (m.getY() - this.E.J.bf) + "," + n + ")\u0000").toCharArray());
                }
            }
        }
        if (this.O == null) {
            return;
        }
        if ((m.getModifiers() & 0x4) != 0x0) {
            this.O.g = 5;
        }
        else if ((m.getModifiers() & 0x10) != 0x0) {
            this.O.g = 6;
        }
        else if ((m.getModifiers() & 0x8) != 0x0) {
            this.O.g = 7;
        }
        if (this.k) {
            this.O.g = 5;
        }
        if (this.r) {
            return;
        }
        this.O.if = if1;
        if (this.E != null) {
            this.E.if(this.O);
        }
        this.do();
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        synchronized (this.goto) {
            if (!this.N) {
                // monitorexit(this.goto)
                return;
            }
            final char[] charArray = "mouseentered()\u0000".toCharArray();
            if (this.E != null && this.E.h) {
                this.E.a(charArray);
                if (this.B != null && this.E.J.for) {
                    this.B.if(charArray);
                }
            }
        }
        // monitorexit(this.goto)
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        synchronized (this.goto) {
            if (!this.N) {
                // monitorexit(this.goto)
                return;
            }
            this.a(mouseEvent, "mouseExited", 3);
        }
        // monitorexit(this.goto)
    }
    
    public void a(final int n) {
        synchronized (this.goto) {
            if (!this.N) {
                // monitorexit(this.goto)
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
                    if (this.B != null && this.E.J.for) {
                        this.B.if(array);
                    }
                }
                if (this.r) {
                    // monitorexit(this.goto)
                    return;
                }
                if (n < 0) {
                    this.B.U.cx -= this.B.U.cx * 0.1f;
                }
                else {
                    this.B.U.cx += this.B.U.cx * 0.1f;
                }
                this.B.U.do = true;
            }
        }
        // monitorexit(this.goto)
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
        synchronized (this.goto) {
            if (!this.N) {
                // monitorexit(this.goto)
                return;
            }
            final char[] charArray = ("keytyped(" + keyEvent.getKeyCode() + ")\u0000").toCharArray();
            if (this.E != null && this.E.h) {
                this.E.a(charArray);
                if (this.B != null && this.E.J.for) {
                    this.B.if(charArray);
                }
            }
        }
        // monitorexit(this.goto)
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        synchronized (this.goto) {
            if (!this.N) {
                // monitorexit(this.goto)
                return;
            }
            final char[] charArray = ("keypressed(" + keyEvent.getKeyCode() + ")\u0000").toCharArray();
            if (this.E != null && this.E.h) {
                this.E.a(charArray);
                if (this.B != null && this.E.J.for) {
                    this.B.if(charArray);
                }
            }
            if (this.w) {
                // monitorexit(this.goto)
                return;
            }
            if (keyEvent.getKeyCode() == 17 || keyEvent.getKeyCode() == 16) {
                this.k = true;
            }
            if (keyEvent.getKeyCode() == 112 || keyEvent.getKeyCode() == 156) {
                this.for("");
            }
            if (keyEvent.getKeyCode() == 113 || keyEvent.getKeyCode() == 36 || keyEvent.getKeyCode() == 154) {
                this.int();
            }
        }
        // monitorexit(this.goto)
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
        synchronized (this.goto) {
            if (!this.N) {
                // monitorexit(this.goto)
                return;
            }
            final char[] charArray = ("keyreleased(" + keyEvent.getKeyCode() + ")\u0000").toCharArray();
            if (this.E != null && this.E.h) {
                this.E.a(charArray);
                if (this.B != null && this.E.J.for) {
                    this.B.if(charArray);
                }
            }
            if (this.w) {
                this.k = false;
                // monitorexit(this.goto)
                return;
            }
            if (keyEvent.getKeyCode() == 17 || keyEvent.getKeyCode() == 16) {
                this.k = false;
            }
        }
        // monitorexit(this.goto)
    }
    
    public void a(final boolean visible) {
        this.M = true;
        try {
            if (this.if || this.H) {
                if (this.I == null || this.b.y != this.if || this.b.x != this.H) {
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
                    if (this.char >= 4) {
                        this.I = new a4(frame, this);
                        ((a4)this.I).setUndecorated(true);
                    }
                    else {
                        this.I = new q(frame, this);
                    }
                    int width = this.I.getToolkit().getScreenSize().width;
                    final int height = this.I.getToolkit().getScreenSize().height;
                    if (this.H) {
                        width *= 2;
                    }
                    if (a.a.a.a.l.z) {
                        this.I.setLocation(0, 22);
                    }
                    else {
                        this.I.setLocation(0, 0);
                    }
                    this.I.setSize(width, height);
                    if (a.a.a.a.l.z) {
                        this.I.reshape(0, 22, width, height + (a.a.a.a.l.A ? 0 : 30));
                    }
                    else {
                        this.I.reshape(0, 0, width, height + (a.a.a.a.l.A ? 0 : 30));
                    }
                    this.width = this.I.getSize().width;
                    this.height = this.I.getSize().height - (((this.if | this.H) & !a.a.a.a.l.A) ? 30 : 0) - (((this.if | this.H) & a.a.a.a.l.A) ? 22 : 0);
                    this.I.addMouseMotionListener(this);
                    this.I.addMouseListener(this);
                    this.I.addKeyListener(this);
                    this.I.setVisible(true);
                    ((Window)this.I).toFront();
                    this.I.requestFocus();
                    if (this.char >= 4) {
                        try {
                            new am(this, this.I);
                        }
                        catch (Throwable t) {}
                    }
                    if (this.b == null || this.b.y != this.if || this.b.x != this.H) {
                        Label_0581: {
                            if (this.char >= 3) {
                                try {
                                    (this.b = new bj()).a(this.I, true, this);
                                    break Label_0581;
                                }
                                catch (Exception ex2) {
                                    ac.af = false;
                                    this.b = new bb();
                                    try {
                                        this.b.a(this.I, true, this);
                                    }
                                    catch (Exception ex3) {}
                                    break Label_0581;
                                }
                            }
                            this.b = new bb();
                            try {
                                this.b.a(this.I, true, this);
                            }
                            catch (Exception ex4) {}
                        }
                        if (this.E != null && this.E.J != null) {
                            this.E.J.l();
                        }
                        if (this.E != null && this.B != null) {
                            this.B.U.a(this.E.J.aN);
                        }
                    }
                }
                this.I.setVisible(visible);
                this.d = this.I;
                this.int = this.b;
                this.int.y = this.if;
                this.int.x = this.H;
                this.q.goto = this.int;
            }
            else {
                if ((this.if || this.H) && this.int != null) {
                    return;
                }
                if (this.I != null) {
                    final int n = this.int.j * this.int.u;
                    try {
                        for (int i = 0; i < n; ++i) {
                            this.int.q[i] = -1;
                        }
                    }
                    catch (Exception ex5) {}
                    this.I.setVisible(false);
                }
                if (this.case == null) {
                    this.for();
                }
                this.d = this;
                this.int = this.case;
            }
            if (this.B != null) {
                this.B.long = true;
            }
            if (this.E != null) {
                this.E.long = true;
            }
            this.width = this.d.getSize().width;
            this.height = this.d.getSize().height - (((this.if | this.H) & !a.a.a.a.l.A) ? 30 : 0) - (((this.if | this.H) & a.a.a.a.l.A) ? 22 : 0);
            this.int.try();
        }
        catch (OutOfMemoryError outOfMemoryError) {
            this.if = false;
            this.H = false;
            if (this.case == null) {
                this.for();
            }
            if (this.I != null) {
                if (this.char >= 4) {
                    ((Dialog)this.I).dispose();
                }
                else {
                    ((Window)this.I).dispose();
                }
            }
            this.I = null;
            this.b = null;
            System.gc();
            if (this.case == null) {
                this.for();
            }
            this.d = this;
            this.int = this.case;
            System.out.println("Can't go fullscreen. Out of memory.");
        }
        this.width = this.d.getSize().width;
        this.height = this.d.getSize().height - (((this.if | this.H) & !a.a.a.a.l.A) ? 30 : 0) - (((this.if | this.H) & a.a.a.a.l.A) ? 22 : 0);
        this.q.goto = this.int;
    }
    
    private void for() {
        this.d = this;
        Label_0116: {
            if (this.case == null) {
                if (this.char >= 3) {
                    try {
                        (this.case = new bj()).a(this.d, false, this);
                        break Label_0116;
                    }
                    catch (Exception ex) {
                        ac.af = false;
                        this.case = null;
                        this.case = new bb();
                        try {
                            this.case.a(this.d, false, this);
                        }
                        catch (Exception ex2) {}
                        break Label_0116;
                    }
                }
                this.case = new bb();
                try {
                    this.case.a(this.d, false, this);
                }
                catch (Exception ex3) {}
            }
        }
        this.int = this.case;
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
                    this.q.e = e;
                    final String property = e.getProperty("bgc");
                    if (property != null) {
                        this.D = (0xFF000000 | ac.a(property));
                    }
                    final String property2 = e.getProperty("bbgc");
                    if (property2 != null) {
                        this.Q = (0xFF000000 | ac.a(property2));
                    }
                    final String property3 = e.getProperty("bfgc");
                    if (property3 != null) {
                        this.for = (0xFF000000 | ac.a(property3));
                    }
                    final String property4 = e.getProperty("bpx");
                    if (property4 != null) {
                        this.p = ac.a(property4);
                    }
                    final String property5 = e.getProperty("bpy");
                    if (property5 != null) {
                        this.n = ac.a(property5);
                    }
                    final String property6 = e.getProperty("bw");
                    if (property6 != null) {
                        this.v = ac.a(property6);
                    }
                    final String property7 = e.getProperty("bh");
                    if (property7 != null) {
                        this.s = ac.a(property7);
                    }
                    final String property8 = e.getProperty("l");
                    if (property8 != null && property8.compareTo("yes") == 0) {
                        this.a = false;
                    }
                    final String property9 = e.getProperty("lx");
                    if (property9 != null) {
                        this.new = ac.a(property9);
                    }
                    final String property10 = e.getProperty("ly");
                    if (property10 != null) {
                        this.do = ac.a(property10);
                    }
                }
                catch (Exception ex2) {}
                byteArrayInputStream.close();
            }
            catch (Exception ex3) {}
        }
        final InputStream resourceAsStream3 = this.getClass().getResourceAsStream("b.gif");
        if (resourceAsStream3 != null) {
            try {
                int n2 = 0;
                for (int k = resourceAsStream3.read(); k != -1; k = resourceAsStream3.read()) {
                    ++n2;
                }
                resourceAsStream3.close();
                final InputStream resourceAsStream4 = this.getClass().getResourceAsStream("b.gif");
                final byte[] array3 = new byte[n2];
                for (int l = 0; l < n2; ++l) {
                    array3[l] = (byte)((byte)resourceAsStream4.read() ^ array[l & 0xF]);
                }
                resourceAsStream4.close();
                this.K = this.getToolkit().createImage(array3);
                this.q.if.addImage(this.K, 0);
                this.q.if.waitForAll();
            }
            catch (Exception ex) {
                ex.printStackTrace();
                this.K = null;
            }
        }
    }
    
    public Image a() {
        if (this.int != null) {
            return this.int.B;
        }
        return null;
    }
}
