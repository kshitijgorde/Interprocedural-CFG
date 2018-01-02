// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

import java.io.InputStream;
import java.util.Properties;
import java.io.ByteArrayInputStream;
import java.awt.Dialog;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import java.applet.AppletContext;
import java.net.URL;
import java.awt.Graphics;
import java.awt.Container;
import java.awt.Frame;
import java.awt.MediaTracker;
import java.awt.Image;
import java.awt.Component;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.KeyListener;
import java.applet.Applet;

public class l extends Applet implements KeyListener, MouseMotionListener, MouseListener, Runnable
{
    private i int;
    private i byte;
    private i goto;
    ab I;
    m[] e;
    int long;
    be else;
    protected v v;
    protected m r;
    protected m b;
    protected ac i;
    o C;
    Thread z;
    int width;
    int height;
    boolean j;
    boolean o;
    long K;
    long u;
    int G;
    public static final String m = "v0.42";
    String E;
    Component void;
    Component B;
    boolean if;
    boolean A;
    public int g;
    boolean d;
    public int case;
    bq l;
    bg c;
    boolean s;
    boolean q;
    boolean p;
    int t;
    int for;
    int J;
    int h;
    int f;
    int n;
    int k;
    int new;
    int do;
    boolean a;
    Image D;
    float try;
    boolean F;
    String w;
    boolean H;
    Object char;
    static /* synthetic */ Class class$0;
    static /* synthetic */ Class class$1;
    
    public l() {
        this.byte = null;
        this.goto = null;
        this.I = new ab();
        this.e = null;
        this.long = 0;
        this.else = null;
        this.v = null;
        this.r = null;
        this.b = null;
        this.i = null;
        this.C = null;
        this.j = false;
        this.o = false;
        this.K = 0L;
        this.u = 0L;
        this.G = 0;
        this.E = "ImmerVision PURE Java Player v0.42 Copyright (c) ImmerVision 2002-2005";
        this.void = this;
        this.B = null;
        this.if = false;
        this.A = false;
        this.g = 1;
        this.d = false;
        this.case = 0;
        this.l = null;
        this.c = null;
        this.s = false;
        this.q = false;
        this.p = false;
        this.t = -1;
        this.for = -6216402;
        this.J = -2236963;
        this.h = -1000;
        this.f = -1000;
        this.n = -1000;
        this.k = -1000;
        this.new = -1000;
        this.do = -1000;
        this.a = true;
        this.D = null;
        this.try = 1.0f;
        this.F = false;
        this.w = "";
        this.H = false;
        this.char = new Object();
    }
    
    public String getAppletInfo() {
        return this.E;
    }
    
    public void init() {
        this.i = new ac();
        this.i.byte = this;
        System.out.println(this.E);
        try {
            final String property = System.getProperty("java.version");
            this.case = property.charAt(2) - '0';
            System.out.println("Java Version: " + property + " from " + System.getProperty("java.vendor"));
            System.out.println("Operating system: " + System.getProperty("os.name") + " version " + System.getProperty("os.version") + " on " + System.getProperty("os.arch"));
            if (System.getProperty("os.name").toLowerCase().startsWith("mac")) {
                this.s = true;
                if (this.case == 3) {
                    this.q = true;
                }
                if (this.case == 4) {
                    this.q = true;
                    this.p = true;
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
        ac.F = this;
        this.i.C = this.getToolkit();
        this.i.P = new ap();
        this.i.S = new av(this.i);
        this.i.J = this;
        this.i.if = new MediaTracker(this);
        this.i.n = new au();
        ac.ab = this.case;
        this.int();
        final String parameter = this.getParameter("barbgcolor");
        if (parameter != null) {
            this.J = (0xFF000000 | ac.a(parameter));
        }
        final String parameter2 = this.getParameter("barfgcolor");
        if (parameter2 != null) {
            this.for = (0xFF000000 | ac.a(parameter2));
        }
        if (this.case >= 4) {
            try {
                new am(this);
                System.out.println("Mouse wheel enabled.");
            }
            catch (Throwable t) {
                System.out.println("Mouse wheel not supported.");
            }
        }
        else {
            System.out.println("Mouse wheel not supported.");
        }
        final String parameter3 = this.getParameter("bgcolor");
        if (parameter3 != null) {
            this.t = (0xFF000000 | ac.a(parameter3));
        }
        final String parameter4 = this.getParameter("mousespeed");
        if (parameter4 != null) {
            this.try = Float.valueOf(parameter4) / 100.0f;
            if (this.try < 0.01 || this.try > 100.0f) {
                this.try = 1.0f;
            }
        }
        final String parameter5 = this.getParameter("smoothstop");
        if (parameter5 != null) {
            ac.e = Float.valueOf(parameter5);
            if (ac.e > 0.0f) {
                ac.e = 1.0f - Float.valueOf(parameter5) / 1000.0f;
            }
            if (ac.e >= 1.0f || ac.e < 0.0f) {
                ac.e = 0.0f;
            }
        }
        final String parameter6 = this.getParameter("fullscreen2");
        if (parameter6 != null && parameter6.toLowerCase().compareTo("true") == 0) {
            this.A = true;
        }
        if (!this.A) {
            final String parameter7 = this.getParameter("fullscreen");
            if (parameter7 != null && parameter7.toLowerCase().compareTo("true") == 0) {
                this.if = true;
            }
        }
    }
    
    public void for() {
        if (this.c == null) {
            Container container;
            for (container = this.getParent(); !(container instanceof Frame); container = container.getParent()) {}
            final Frame frame = (Frame)container;
            if (this.r != null) {
                this.c = new bg(this, frame, "v0.42", this.v.if, this.v.int, this.v.s, this.r.if, this.r.int, this.r.s);
            }
            else {
                this.c = new bg(this, frame, "v0.42", this.v.if, this.v.int, this.v.s, "", "", "");
            }
        }
        else {
            this.c.toFront();
        }
    }
    
    public void do(final String s) {
        if (this.l == null) {
            Container container;
            for (container = this.getParent(); !(container instanceof Frame); container = container.getParent()) {}
            this.l = new bq((Frame)container, "v0.42", this, s);
        }
        else {
            this.l.toFront();
        }
    }
    
    public void start() {
        this.do();
        this.if();
        this.int.try();
        this.int.new();
        if (this.h != -1000 && this.f != -1000 && this.n != -1000 && this.k != -1000) {
            this.C = new o(this.J, this.for, this.h, this.f, this.n, this.k);
        }
        else {
            this.C = new o(this.J, this.for, 0, this.height - 5, this.width, 5);
        }
        this.else = new be(this.getDocumentBase(), this.C, this.i);
        this.i.w = this.else;
        this.C.void = this.i;
        this.v = new v(this.i, this.C, this);
        final String parameter = this.getParameter("optimizememory");
        if (parameter != null && parameter.toLowerCase().compareTo("true") == 0) {
            System.out.println("Memory optimization enabled.");
            this.i.M = true;
        }
        final String parameter2 = this.getParameter("quality");
        if (parameter2 != null) {
            try {
                float float1 = Float.parseFloat(parameter2);
                if (float1 < 100.0f) {
                    float1 = 100.0f;
                }
                else if (float1 > 1000.0f) {
                    float1 = 1000.0f;
                }
                this.i.ai = float1;
                System.out.println("Quality set to " + parameter2);
            }
            catch (Exception ex) {
                this.i.ai = 100.0f;
                System.out.println("Error in Quality parameter. Quality is set to default.");
            }
        }
        final String parameter3 = this.getParameter("lockzenithnadir");
        if (parameter3 != null && parameter3.toLowerCase().compareTo("true") == 0) {
            System.out.println("Lock camera movements at zenith and nadir.");
            this.i.for = true;
        }
        final String parameter4 = this.getParameter("singlepanorama");
        if (parameter4 != null && parameter4.toLowerCase().compareTo("true") == 0) {
            System.out.println("Single panorama mode enabled.");
            this.i.E = true;
        }
        final String parameter5 = this.getParameter("usecache");
        if (parameter5 != null && parameter5.toLowerCase().compareTo("false") == 0) {
            System.out.println("Internal cache system disabled.");
            this.i.h = false;
        }
        final String parameter6 = this.getParameter("antialiasing");
        if (parameter6 != null) {
            if (parameter6.toLowerCase().compareTo("none") == 0) {
                System.out.println("No antialiasing.");
                this.g = 0;
            }
            else if (parameter6.toLowerCase().compareTo("everytime") == 0) {
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
        final String parameter7 = this.getParameter("gui");
        if (parameter7 == null) {
            this.v.a(this.else.a("immergui.xml", null, false, false, false));
        }
        else {
            this.v.a(this.else.a(parameter7, null, false, false, false));
        }
        final String parameter8 = this.getParameter("transition");
        if (parameter8 != null) {
            this.w = parameter8;
        }
        (this.z = new Thread(this)).start();
        this.H = true;
    }
    
    public void run() {
        while (true) {
            Thread.yield();
            long k = System.currentTimeMillis();
            try {
                if (k - this.K < 40L) {
                    Thread.sleep(this.K + 40L - k);
                }
                k = System.currentTimeMillis();
                this.K = k;
            }
            catch (InterruptedException ex) {}
            this.a(k);
        }
    }
    
    private void a(final long n) {
        boolean b = false;
        synchronized (this.char) {
            if (this.width != this.void.getSize().width || (this.height != this.void.getSize().height - (((this.if | this.A) & !this.q) ? 30 : 0) - (((this.if | this.A) & this.q) ? 22 : 0) | this.F)) {
                this.width = this.void.getSize().width;
                this.height = this.void.getSize().height - (((this.if | this.A) & !this.q) ? 30 : 0) - (((this.if | this.A) & this.q) ? 22 : 0);
                if (this.int != null) {
                    this.int.try();
                }
                if (this.v != null) {
                    this.v.a(("resize(" + this.width + "," + this.height + ");\u0000").toCharArray());
                    if (this.v.C != null) {
                        this.v.C.k();
                    }
                    this.v.do = true;
                }
                this.F = false;
                if (this.r != null) {
                    this.r.do = true;
                }
                b = true;
            }
            if (this.v != null) {
                boolean b2 = b | this.v.a(n);
                for (int i = 0; i < this.long; ++i) {
                    this.e[i].do(n);
                    if (!this.e[i].goto) {
                        this.e[i].if(true);
                        if (this.e[i].goto) {
                            this.if(this.e[i].D);
                        }
                    }
                }
                if (this.v.l) {
                    if (this.r == null && this.b == null) {
                        final String parameter = this.getParameter("panorama");
                        if (parameter != null) {
                            this.b = new m(this.i, this, false);
                            this.b.D = parameter;
                            this.b.if(this.else.a(parameter, null, false, false, false));
                        }
                    }
                    else if (this.b != null && !this.b.l && this.b.if(false)) {
                        ad ad = null;
                        if (!this.b.F) {
                            this.v.C.a(this.b);
                        }
                        if (this.r != null) {
                            if (this.b.H) {
                                this.b.L.cn = this.r.L.cn - this.r.I + this.b.I;
                                this.b.L.cg = 0.0f;
                            }
                            if (this.w.length() > 0) {
                                ad = this.r.new();
                            }
                        }
                        else if (this.w.length() > 0) {
                            ad = this.v.C.h();
                        }
                        if (ad != null) {
                            try {
                                final ak ak = (ak)Class.forName("com.immervision.pure.player." + this.w).getConstructor((Class<?>[])new Class[0]).newInstance((Object[])null);
                                if (ak != null) {
                                    this.b.L.a(ak);
                                }
                            }
                            catch (Exception ex) {
                                System.out.println("Can't load transition:" + this.w);
                            }
                        }
                        this.b.L.if(ad);
                        this.b.for(n);
                        if (this.r != null) {
                            this.r.if("enddisplay();\u0000".toCharArray());
                            this.v.a(("enddisplay(\"" + this.r.D + "\");\u0000").toCharArray());
                            this.r.a();
                            this.r = null;
                        }
                        this.r = this.b;
                        this.b = null;
                        b2 = true;
                        this.v.a(("startdisplay(\"" + this.r.D + "\");\u0000").toCharArray());
                    }
                    if (this.b != null) {
                        this.b.do(n);
                    }
                    if (b2) {
                        this.v.for();
                    }
                }
                else if (this.v.m) {
                    if (this.v.int()) {
                        this.C.g();
                        this.v.if(n);
                        this.D = null;
                    }
                    else {
                        b2 = this.C.a(n);
                        this.C.e();
                    }
                }
                else {
                    b2 = this.C.a(n);
                    this.C.e();
                }
                if (b2) {
                    this.int.for();
                }
            }
        }
        // monitorexit(this.char)
    }
    
    public void repaint() {
    }
    
    public void update(final Graphics graphics) {
    }
    
    public void paint(final Graphics graphics) {
        if (this.int != null) {
            this.int.for();
        }
    }
    
    public String execpano(final String s) {
        if (this.r != null && this.r.goto) {
            return al.for(this.r.if((String.valueOf(s) + "\u0000").toCharArray()));
        }
        return "";
    }
    
    public String execgui(final String s) {
        if (this.v != null && this.v.int()) {
            return al.for(this.v.a((String.valueOf(s) + "\u0000").toCharArray()));
        }
        return "";
    }
    
    public a3 int(final String s) {
        synchronized (this.char) {
            if (this.r != null && this.r.goto) {
                // monitorexit(this.char)
                return this.r.if((String.valueOf(s) + "\u0000").toCharArray());
            }
            // monitorexit(this.char)
            return new a3();
        }
    }
    
    public a3 a(final String s) {
        synchronized (this.char) {
            if (this.v != null && this.v.int()) {
                // monitorexit(this.char)
                return this.v.a((String.valueOf(s) + "\u0000").toCharArray());
            }
            // monitorexit(this.char)
            return new a3();
        }
    }
    
    public void if(final String d, final ae ae) {
        for (int i = 0; i < this.long; ++i) {
            if (d.compareTo(this.e[i].D) == 0) {
                this.e[i].if(true);
                return;
            }
        }
        if (this.e == null || this.e.length == this.long) {
            final m[] e = new m[this.long + 10];
            for (int j = 0; j < this.long; ++j) {
                e[j] = this.e[j];
            }
            this.e = e;
        }
        if (this.r != null && d.compareTo(this.r.D) == 0) {
            this.e[this.long] = this.r;
            ++this.long;
            this.r.if(true);
            return;
        }
        this.e[this.long] = new m(this.i, this, false);
        this.e[this.long].D = d;
        this.e[this.long].if(this.else.a(d, ae, false, false, false));
        ++this.long;
    }
    
    public void for(final String s) {
        for (int i = 0; i < this.long; ++i) {
            if (this.e[i].D.compareTo(s) == 0) {
                for (int j = i; j < this.long - 1; ++j) {
                    this.e[j] = this.e[j + 1];
                }
                --this.long;
                return;
            }
        }
    }
    
    public void if(final String s) {
        this.v.a(("ispreloaded(\"" + s + "\")\u0000").toCharArray());
        if (this.r != null) {
            this.r.if(("ispreloaded(" + s + ")\u0000").toCharArray());
        }
    }
    
    public void case(final String s) {
        this.v.a(("loadfinished(\"" + s + "\")\u0000").toCharArray());
        if (this.r != null) {
            this.r.if("loadfinished()\u0000".toCharArray());
        }
    }
    
    public boolean new(final String s) {
        int i = 0;
        while (i < this.long) {
            if (this.e[i].D.compareTo(s) == 0) {
                if (this.e[i].goto) {
                    this.b = this.e[i];
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
    
    public void a(final String d, final ae ae) {
        for (int i = 0; i < this.long; ++i) {
            if (d.compareTo(this.e[i].D) == 0) {
                this.e[i].if(true);
                return;
            }
        }
        if (this.e == null || this.e.length == this.long) {
            final m[] e = new m[this.long + 10];
            for (int j = 0; j < this.long; ++j) {
                e[j] = this.e[j];
            }
            this.e = e;
        }
        this.e[this.long] = new m(this.i, this, false);
        this.e[this.long].D = d;
        this.e[this.long].if(this.else.a(d, ae, false, false, false));
        ++this.long;
    }
    
    public void try(final String s) {
        for (int i = 0; i < this.long; ++i) {
            if (this.e[i].D.compareTo(s) == 0) {
                for (int j = i; j < this.long - 1; ++j) {
                    this.e[j] = this.e[j + 1];
                }
                --this.long;
                return;
            }
        }
    }
    
    public void byte(final String s) {
        this.v.a(("ispreloaded(\"" + s + "\")\u0000").toCharArray());
        if (this.r != null) {
            this.r.if(("ispreloaded(" + s + ")\u0000").toCharArray());
        }
    }
    
    public void a(final String s, final ae ae, final boolean b) {
        if (this.b != null) {
            return;
        }
        if (!this.i.h) {
            this.else.if();
        }
        if (this.i.E && this.r != null) {
            this.r.a();
            this.r = null;
            this.v.C.i();
            System.gc();
        }
        (this.b = new m(this.i, this, b)).if(this.else.a(s, ae, false, false, false));
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
                if (this.s) {
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
        if (this.r != null) {
            if1 = this.r.if(array);
        }
        if (if1 != null) {
            return if1;
        }
        System.out.println("Error in the command sent to panorama script environment.");
        return new a3();
    }
    
    public a3 do(final char[] array) {
        a3 a = null;
        if (this.v != null) {
            a = this.v.a(array);
        }
        if (a != null) {
            return a;
        }
        System.out.println("Error in the command sent to gui script environment.");
        return new a3();
    }
    
    private void a() {
        if (this.i.al != this.I.for) {
            this.i.al = this.I.for;
            switch (this.I.for) {
                case 3: {
                    if (this.v != null && this.v.A.by[0] != null) {
                        this.void.setCursor(this.v.A.by[0]);
                        break;
                    }
                    this.void.setCursor(Cursor.getPredefinedCursor(0));
                    break;
                }
                case 0: {
                    if (this.v != null && this.v.A.by[1] != null) {
                        this.void.setCursor(this.v.A.by[1]);
                        break;
                    }
                    this.void.setCursor(Cursor.getPredefinedCursor(12));
                    break;
                }
                case 1: {
                    if (this.v != null && this.v.A.by[2] != null) {
                        this.void.setCursor(this.v.A.by[2]);
                        break;
                    }
                    this.void.setCursor(Cursor.getPredefinedCursor(13));
                    break;
                }
                case 2: {
                    if (this.v != null && this.v.A.by[3] != null) {
                        this.void.setCursor(this.v.A.by[3]);
                        break;
                    }
                    this.void.setCursor(Cursor.getPredefinedCursor(9));
                    break;
                }
            }
        }
        for (int i = 0; i < this.I.byte; ++i) {
            if (this.I.c[i]) {
                this.r.if(this.I.b[i]);
            }
            else {
                this.v.a(this.I.b[i]);
            }
        }
    }
    
    public void stop() {
        synchronized (this.char) {
            try {
                if (this.c != null) {
                    this.c.dispose();
                    this.c = null;
                }
                if (this.l != null) {
                    this.l.dispose();
                    this.l = null;
                }
                if (this.r != null) {
                    this.r.a();
                }
                if (this.v != null) {
                    this.v.a();
                }
                this.z.interrupt();
            }
            catch (Exception ex) {}
        }
        // monitorexit(this.char)
    }
    
    public void destroy() {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        this.a(mouseEvent, "mouseclicked", 2);
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.a(mouseEvent, "mousepressed", 0);
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.a(mouseEvent, "mousereleased", 1);
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        this.a(mouseEvent, "mousedragged", 4);
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        this.a(mouseEvent, "mousemoved", 3);
    }
    
    private void a(final MouseEvent mouseEvent, final String s, final int if1) {
        synchronized (this.char) {
            if (!this.H) {
                // monitorexit(this.char)
                return;
            }
            int n = 0;
            if ((mouseEvent.getModifiers() & 0x4) != 0x0) {
                n |= 0x4;
            }
            else if ((mouseEvent.getModifiers() & 0x10) != 0x0) {
                n |= 0x1;
            }
            else if ((mouseEvent.getModifiers() & 0x8) != 0x0) {
                n |= 0x2;
            }
            if (s.compareTo("mousemoved") == 0) {
                final char[] charArray = (String.valueOf(s) + "(" + mouseEvent.getX() + "," + mouseEvent.getY() + ")\u0000").toCharArray();
                if (this.v != null && this.v.l) {
                    this.v.a(charArray);
                    if (this.r != null && this.v.C.do) {
                        this.r.if((String.valueOf(s) + "(" + (mouseEvent.getX() - this.v.C.a7) + "," + (mouseEvent.getY() - this.v.C.a6) + ")\u0000").toCharArray());
                    }
                }
            }
            else {
                final char[] charArray2 = (String.valueOf(s) + "(" + mouseEvent.getX() + "," + mouseEvent.getY() + "," + n + ")\u0000").toCharArray();
                if (this.v != null && this.v.l) {
                    this.v.a(charArray2);
                    if (this.r != null && this.v.C.do) {
                        this.r.if((String.valueOf(s) + "(" + (mouseEvent.getX() - this.v.C.a7) + "," + (mouseEvent.getY() - this.v.C.a6) + "," + n + ")\u0000").toCharArray());
                    }
                }
            }
            this.I.a();
            if ((mouseEvent.getModifiers() & 0x4) != 0x0) {
                this.I.g = 5;
            }
            else if ((mouseEvent.getModifiers() & 0x10) != 0x0) {
                this.I.g = 6;
            }
            else if ((mouseEvent.getModifiers() & 0x8) != 0x0) {
                this.I.g = 7;
            }
            if (this.d) {
                this.I.g = 5;
            }
            if (this.j) {
                // monitorexit(this.char)
                return;
            }
            this.I.if = if1;
            this.I.goto = mouseEvent.getX();
            this.I.else = mouseEvent.getY();
            if (this.v != null) {
                this.v.if(this.I);
            }
            this.a();
        }
        // monitorexit(this.char)
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        synchronized (this.char) {
            if (!this.H) {
                // monitorexit(this.char)
                return;
            }
            final char[] charArray = "mouseentered()\u0000".toCharArray();
            if (this.v != null && this.v.l) {
                this.v.a(charArray);
                if (this.r != null && this.v.C.do) {
                    this.r.if(charArray);
                }
            }
        }
        // monitorexit(this.char)
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        synchronized (this.char) {
            if (!this.H) {
                // monitorexit(this.char)
                return;
            }
            final char[] charArray = "mouseexited()\u0000".toCharArray();
            if (this.v != null && this.v.l) {
                this.v.a(charArray);
                if (this.r != null && this.v.C.do) {
                    this.r.if(charArray);
                }
            }
            this.I.a();
            if (this.j) {
                // monitorexit(this.char)
                return;
            }
            this.I.if = 3;
            this.I.goto = -65536;
            this.I.else = -65536;
            if (this.v != null) {
                this.v.if(this.I);
                this.a();
            }
        }
        // monitorexit(this.char)
    }
    
    public void a(final int n) {
        synchronized (this.char) {
            if (!this.H) {
                // monitorexit(this.char)
                return;
            }
            if (this.r != null) {
                char[] array;
                if (n > 0) {
                    array = "mousewheel(1)\u0000".toCharArray();
                }
                else {
                    array = "mousewheel(-1)\u0000".toCharArray();
                }
                if (this.v != null && this.v.l) {
                    this.v.a(array);
                    if (this.r != null && this.v.C.do) {
                        this.r.if(array);
                    }
                }
                if (this.j) {
                    // monitorexit(this.char)
                    return;
                }
                if (n < 0) {
                    this.r.L.ch -= this.r.L.ch * 0.1f;
                }
                else {
                    this.r.L.ch += this.r.L.ch * 0.1f;
                }
                this.r.L.if = true;
            }
        }
        // monitorexit(this.char)
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
        synchronized (this.char) {
            if (!this.H) {
                // monitorexit(this.char)
                return;
            }
            final char[] charArray = ("keytyped(" + keyEvent.getKeyCode() + ")\u0000").toCharArray();
            if (this.v != null && this.v.l) {
                this.v.a(charArray);
                if (this.r != null && this.v.C.do) {
                    this.r.if(charArray);
                }
            }
        }
        // monitorexit(this.char)
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        synchronized (this.char) {
            if (!this.H) {
                // monitorexit(this.char)
                return;
            }
            final char[] charArray = ("keypressed(" + keyEvent.getKeyCode() + ")\u0000").toCharArray();
            if (this.v != null && this.v.l) {
                this.v.a(charArray);
                if (this.r != null && this.v.C.do) {
                    this.r.if(charArray);
                }
            }
            if (this.o) {
                // monitorexit(this.char)
                return;
            }
            if (keyEvent.getKeyCode() == 17 || keyEvent.getKeyCode() == 16) {
                this.d = true;
            }
            if (keyEvent.getKeyCode() == 112 || keyEvent.getKeyCode() == 156) {
                this.do("");
            }
            if (keyEvent.getKeyCode() == 113 || keyEvent.getKeyCode() == 36 || keyEvent.getKeyCode() == 154) {
                this.for();
            }
        }
        // monitorexit(this.char)
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
        synchronized (this.char) {
            if (!this.H) {
                // monitorexit(this.char)
                return;
            }
            final char[] charArray = ("keyreleased(" + keyEvent.getKeyCode() + ")\u0000").toCharArray();
            if (this.v != null && this.v.l) {
                this.v.a(charArray);
                if (this.r != null && this.v.C.do) {
                    this.r.if(charArray);
                }
            }
            if (this.o) {
                this.d = false;
                // monitorexit(this.char)
                return;
            }
            if (keyEvent.getKeyCode() == 17 || keyEvent.getKeyCode() == 16) {
                this.d = false;
            }
        }
        // monitorexit(this.char)
    }
    
    public void if() {
        this.F = true;
        try {
            if (this.if || this.A) {
                if (this.B == null) {
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
                    if (this.case >= 4) {
                        this.B = new a4(frame, this);
                        ((a4)this.B).setUndecorated(true);
                    }
                    else {
                        this.B = new q(frame, this);
                    }
                    int width = this.B.getToolkit().getScreenSize().width;
                    final int height = this.B.getToolkit().getScreenSize().height;
                    if (this.A) {
                        width *= 2;
                    }
                    if (this.p) {
                        this.B.setLocation(0, 22);
                    }
                    else {
                        this.B.setLocation(0, 0);
                    }
                    this.B.setSize(width, height);
                    if (this.p) {
                        this.B.reshape(0, 22, width, height + (this.q ? 0 : 30));
                    }
                    else {
                        this.B.reshape(0, 0, width, height + (this.q ? 0 : 30));
                    }
                    this.B.setVisible(true);
                    ((Window)this.B).toFront();
                    this.B.requestFocus();
                    this.width = this.B.getSize().width;
                    this.height = this.B.getSize().height - (((this.if | this.A) & !this.q) ? 30 : 0) - (((this.if | this.A) & this.q) ? 22 : 0);
                    this.B.addMouseMotionListener(this);
                    this.B.addMouseListener(this);
                    this.B.addKeyListener(this);
                    if (this.case >= 4) {
                        try {
                            new am(this);
                            if (this.v == null) {
                                System.out.println("Mouse wheel enabled.");
                            }
                        }
                        catch (Throwable t) {
                            if (this.v == null) {
                                System.out.println("Mouse wheel not supported.");
                            }
                        }
                    }
                    else if (this.v == null) {
                        System.out.println("Mouse wheel not supported.");
                    }
                    Label_0561: {
                        if (this.case >= 3) {
                            try {
                                (this.goto = new bj()).a(this.B, true, this);
                                break Label_0561;
                            }
                            catch (Exception ex2) {
                                this.goto = new bb();
                                try {
                                    this.goto.a(this.B, true, this);
                                }
                                catch (Exception ex3) {}
                                break Label_0561;
                            }
                        }
                        this.goto = new bb();
                        try {
                            this.goto.a(this.B, true, this);
                        }
                        catch (Exception ex4) {}
                    }
                    if (this.v != null && this.v.C != null) {
                        this.v.C.k();
                    }
                    if (this.v != null && this.r != null) {
                        this.r.L.a(this.v.C.aE);
                    }
                }
                else {
                    this.B.setVisible(true);
                }
                this.void = this.B;
                (this.int = this.goto).try();
                this.i.else = this.int;
            }
            else {
                if ((this.if || this.A) && this.int != null) {
                    return;
                }
                if (this.B != null) {
                    this.B.setVisible(false);
                }
                if (this.byte == null) {
                    this.do();
                }
                this.void = this;
                this.int = this.byte;
            }
            if (this.r != null) {
                this.r.do = true;
            }
            if (this.v != null) {
                this.v.do = true;
            }
        }
        catch (OutOfMemoryError outOfMemoryError) {
            this.if = false;
            this.A = false;
            if (this.byte == null) {
                this.do();
            }
            if (this.B != null) {
                if (this.case >= 4) {
                    ((Dialog)this.B).dispose();
                }
                else {
                    ((Window)this.B).dispose();
                }
            }
            this.B = null;
            this.goto = null;
            System.gc();
            if (this.byte == null) {
                this.do();
            }
            this.void = this;
            this.int = this.byte;
            final char[] charArray = "cantfullscreen()".toCharArray();
            if (this.v != null) {
                this.v.a(charArray);
            }
            if (this.r != null) {
                this.r.if(charArray);
            }
        }
        this.width = this.void.getSize().width;
        this.height = this.void.getSize().height - (((this.if | this.A) & !this.q) ? 30 : 0) - (((this.if | this.A) & this.q) ? 22 : 0);
        this.i.else = this.int;
    }
    
    private void do() {
        this.void = this;
        Label_0112: {
            if (this.byte == null) {
                if (this.case >= 3) {
                    try {
                        (this.byte = new bj()).a(this.void, false, this);
                        break Label_0112;
                    }
                    catch (Exception ex) {
                        this.byte = null;
                        this.byte = new bb();
                        try {
                            this.byte.a(this.void, false, this);
                        }
                        catch (Exception ex2) {}
                        break Label_0112;
                    }
                }
                this.byte = new bb();
                try {
                    this.byte.a(this.void, false, this);
                }
                catch (Exception ex3) {}
            }
        }
        this.int = this.byte;
    }
    
    protected void int() {
        final byte[] array = { 73, 109, 77, 101, 82, 118, 73, 115, 73, 111, 78, 32, 80, 117, 82, 101 };
        final InputStream resourceAsStream = this.getClass().getResourceAsStream("a.class");
        if (resourceAsStream != null) {
            try {
                int n = 0;
                for (int i = resourceAsStream.read(); i != -1; i = resourceAsStream.read()) {
                    ++n;
                }
                resourceAsStream.close();
                final InputStream resourceAsStream2 = this.getClass().getResourceAsStream("a.class");
                final byte[] array2 = new byte[n];
                for (int j = 0; j < n; ++j) {
                    array2[j] = (byte)resourceAsStream2.read();
                    array2[j] ^= array[j & 0xF];
                }
                resourceAsStream2.close();
                final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(array2);
                try {
                    final Properties c = new Properties();
                    c.load(byteArrayInputStream);
                    this.i.c = c;
                    final String property = c.getProperty("bgc");
                    if (property != null) {
                        this.t = (0xFF000000 | ac.a(property));
                    }
                    final String property2 = c.getProperty("bbgc");
                    if (property2 != null) {
                        this.J = (0xFF000000 | ac.a(property2));
                    }
                    final String property3 = c.getProperty("bfgc");
                    if (property3 != null) {
                        this.for = (0xFF000000 | ac.a(property3));
                    }
                    final String property4 = c.getProperty("bpx");
                    if (property4 != null) {
                        this.h = ac.a(property4);
                    }
                    final String property5 = c.getProperty("bpy");
                    if (property5 != null) {
                        this.f = ac.a(property5);
                    }
                    final String property6 = c.getProperty("bw");
                    if (property6 != null) {
                        this.n = ac.a(property6);
                    }
                    final String property7 = c.getProperty("bh");
                    if (property7 != null) {
                        this.k = ac.a(property7);
                    }
                    final String property8 = c.getProperty("l");
                    if (property8 != null && property8.compareTo("yes") == 0) {
                        this.a = false;
                    }
                    final String property9 = c.getProperty("lx");
                    if (property9 != null) {
                        this.new = ac.a(property9);
                    }
                    final String property10 = c.getProperty("ly");
                    if (property10 != null) {
                        this.do = ac.a(property10);
                    }
                }
                catch (Exception ex2) {}
                byteArrayInputStream.close();
            }
            catch (Exception ex3) {}
        }
        final InputStream resourceAsStream3 = this.getClass().getResourceAsStream("b.class");
        if (resourceAsStream3 != null) {
            try {
                int n2 = 0;
                for (int k = resourceAsStream3.read(); k != -1; k = resourceAsStream3.read()) {
                    ++n2;
                }
                resourceAsStream3.close();
                final InputStream resourceAsStream4 = this.getClass().getResourceAsStream("b.class");
                final byte[] array3 = new byte[n2];
                for (int l = 0; l < n2; ++l) {
                    array3[l] = (byte)((byte)resourceAsStream4.read() ^ array[l & 0xF]);
                }
                resourceAsStream4.close();
                this.D = this.getToolkit().createImage(array3);
                this.i.if.addImage(this.D, 0);
                this.i.if.waitForAll();
            }
            catch (Exception ex) {
                ex.printStackTrace();
                this.D = null;
            }
        }
    }
}
