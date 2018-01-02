// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.Dimension;
import com.easypano.tw.b.a;
import com.easypano.tw.d.r;
import java.net.URL;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.util.Enumeration;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import netscape.javascript.JSObject;
import java.util.Vector;
import java.awt.PopupMenu;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

public class TWViewer extends Applet implements ViewerAction, CommunicationAction
{
    private String a;
    private String b;
    private by c;
    private bv d;
    private boolean e;
    private boolean f;
    private boolean g;
    private boolean h;
    private cb i;
    private int j;
    private int k;
    private Image l;
    private Graphics m;
    private Image n;
    private Image o;
    private h p;
    private bt q;
    private m r;
    private p s;
    private l t;
    private k u;
    private q v;
    private h w;
    private h x;
    private PopupMenu y;
    private bq z;
    private bp A;
    private br B;
    private Vector C;
    private Vector D;
    private du E;
    private JSObject F;
    private ee G;
    private boolean H;
    private boolean I;
    
    public TWViewer() {
        this.a = d("\u00076\u0016j\u0000\u0006-\u0015l\u001e\u0000");
        this.b = d("FjJ*KD");
        this.c = new by(this);
        this.d = new bv(this);
        this.e = false;
        this.f = false;
        this.g = false;
        this.h = false;
        this.i = null;
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = null;
        this.p = new h();
        this.q = null;
        this.r = null;
        this.s = null;
        this.t = null;
        this.u = null;
        this.v = null;
        this.w = null;
        this.x = null;
        this.y = null;
        this.z = null;
        this.A = null;
        this.B = null;
        this.C = new Vector();
        this.D = new Vector();
        this.E = new du();
        this.F = null;
        this.G = new ee(this);
        this.H = true;
        this.I = false;
    }
    
    public String a(final String s, final String s2) {
        return this.e ? System.getProperty(s, s2) : ((this.getParameter(s) != null) ? this.getParameter(s) : s2);
    }
    
    public void init() {
        dt.a();
        try {
            this.a();
            this.G.start();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void a() throws Exception {
        this.addMouseListener(new bg(this));
        this.addMouseMotionListener(new bm(this));
        this.setLayout(null);
    }
    
    private void b() throws Exception {
        dt.b(this.getCodeBase());
        dt.a(this.getDocumentBase());
        dt.e = this;
    }
    
    public void start() {
    }
    
    public void stop() {
    }
    
    public void destroy() {
        if (!this.g()) {
            try {
                dt.stopThread(this.G, d("zwD>\u000ebpW?OR8T/GBlL4I\u00186\u000bt\u0000\u00186"), 100, 0);
                this.G = null;
            }
            catch (NullPointerException ex2) {}
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        try {
            this.E.stopWatch();
            this.E = null;
        }
        catch (NullPointerException ex3) {}
        try {
            if (this.q != null) {
                this.q.destroyResource();
                this.q = null;
            }
        }
        catch (Exception ex4) {}
        this.d.destroyResource();
        this.d = null;
        try {
            this.c.destroyResource();
        }
        catch (Exception ex5) {}
        if (this.r != null) {
            this.r.destroyResource();
        }
        this.s = null;
        for (int componentCount = this.getComponentCount(), i = 0; i < componentCount; ++i) {
            final Component component = this.getComponent(i);
            if (component instanceof db && component != this.q) {
                ((bt)component).destroyResource();
            }
        }
        if (this.u != null) {
            this.u.h().a();
        }
        this.disposeOffImage();
        dt.destroy();
        this.c = null;
        this.l = null;
        this.m = null;
        this.n = null;
        this.p = null;
        this.r = null;
        this.t = null;
        this.u = null;
        this.v = null;
        this.w = null;
        this.x = null;
        this.F = null;
        this.removeAll();
        if (this.C != null) {
            this.C.removeAllElements();
            this.C = null;
            this.D.removeAllElements();
            this.D = null;
        }
        dt.e();
        dt.b();
    }
    
    private void c() {
    }
    
    public void d() {
        this.disposeOffImage();
        this.j = this.getBounds().width;
        this.k = this.getBounds().height;
        this.l = this.createImage(this.j, this.k);
        this.m = this.l.getGraphics();
    }
    
    public void disposeOffImage() {
        if (this.m != null) {
            this.l = null;
            this.m.dispose();
            this.m = null;
        }
    }
    
    public String getAppletInfo() {
        return d("bwP(YSyS?\\\u0016NL?YSj\u0005\f") + this.a + d("\u00165\u0005\u001fOEaU;@Y6\u0005\u001bBZ8w3I^lVz|Sk@(XS|\u000bW$") + d("umW(KXl\u00056G@}\u00053@ElD4MS8K/CT}WzGE8") + dt.c() + d("\u0018\u0015/\u000eFS8V/C\u0016wCzZ^}\u0005?XSj\u00056G@}\u00053@ElD9K\u0016vP7LSj\u00053]\u0016") + dt.d() + ".";
    }
    
    public String[][] getParameterInfo() {
        return new String[0][];
    }
    
    private void e() {
        try {
            this.F = JSObject.getWindow((Applet)this);
        }
        catch (Exception ex) {}
        catch (NoClassDefFoundError noClassDefFoundError) {}
        this.setBackground(dt.a(this.a(d("EsL4\u0000T\u007fF5BYj"), d("\u0004-\u0016v\u001c\u0002(\th\u001d\u0006"))));
        final n n = new n();
        n.setBounds(dt.b(this.a(d("FjJ=\\SkVtLYmK>]"), "")));
        ((com.easypano.tw.d.n)n.a()).a(dt.a(this.a(d("FjJ=\\SkVtMYtJ("), "")));
        n.b(0);
        this.add(n, 0);
        this.H = dt.g(this.a(d("UtJ)KSvD8BS|"), d("BjP?")));
        String s = this.a(d("UwH;^Ft@.@Wu@"), "");
        if (s.equalsIgnoreCase(d("WtI"))) {
            final Enumeration<Applet> applets = this.getAppletContext().getApplets();
            int n2 = 0;
            while (applets.hasMoreElements()) {
                final Applet nextElement = applets.nextElement();
                if (nextElement instanceof CommunicationAction) {
                    this.C.addElement(nextElement);
                    this.D.addElement(new Integer(n2++));
                }
            }
            if (this.C.size() == 0) {
                this.C = null;
                this.D = null;
            }
        }
        else {
            final Vector<String> vector = new Vector<String>();
            int index;
            while ((index = s.indexOf(",")) > 0) {
                vector.addElement(s.substring(0, index).trim());
                if (index + 1 < s.length()) {
                    s = s.substring(index + 1);
                }
                else {
                    s = "";
                }
            }
            if (!s.equals("")) {
                vector.addElement(s);
            }
            if (vector.size() > 0) {
                for (int i = 0; i < vector.size(); ++i) {
                    final Applet applet = this.getAppletContext().getApplet(vector.elementAt(i));
                    if (applet instanceof CommunicationAction) {
                        this.C.addElement(applet);
                        this.D.addElement(vector.elementAt(i));
                    }
                }
                if (this.C.size() == 0) {
                    this.C = null;
                    this.D = null;
                }
            }
            else {
                this.C = null;
                this.D = null;
            }
        }
        this.c.a(dt.a(this.getDocumentBase(), this.a(d("EsL4\u0000WjF2G@}"), "")), dt.f(this.a(d("EsL4\u0000WjF2G@}\u000b3ZSuK/C"), "0")), this.a(d("_vL\u001cGZ}"), ""), new dx(this, n), false);
    }
    
    public void f() {
        final Graphics graphics = this.getGraphics();
        if (graphics != null) {
            Graphics graphics2 = null;
            try {
                this.paint(graphics);
            }
            finally {
                graphics.dispose();
                graphics2 = null;
            }
            graphics2.dispose();
        }
        else {
            System.out.println(d("Q}Q\u001d\\WhM3ME0\fz\\SlP(@\u0016vP6B\u0017"));
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.l == null) {
            final Rectangle bounds = this.getBounds();
            this.j = bounds.width;
            this.k = bounds.height;
            this.l = this.createImage(this.j, this.k);
            this.m = this.l.getGraphics();
        }
        this.a(this.m);
        graphics.drawImage(this.l, 0, 0, this);
    }
    
    public void a(final Graphics graphics) {
        graphics.setColor(this.getBackground());
        graphics.fillRect(0, 0, this.j, this.k);
        if (this.n != null) {
            graphics.drawImage(this.n, (this.j - this.n.getWidth(this)) / 2, (this.k - this.n.getHeight(this)) / 2, this);
        }
        super.paint(graphics);
    }
    
    boolean g() {
        return this.f;
    }
    
    void a(final boolean f) {
        this.f = f;
    }
    
    void a(final String s) {
        Vector vector = new Vector<String>();
        final String a = dt.a(s, vector);
        if (vector.size() == 0) {
            vector = null;
        }
        if (a.startsWith(d("Z}C."))) {
            this.panLeft();
        }
        else if (a.startsWith(d("DqB2Z"))) {
            this.panRight();
        }
        else if (a.startsWith(d("Ch"))) {
            this.panUp();
        }
        else if (a.startsWith(d("RwR4"))) {
            this.panDown();
        }
        else if (a.startsWith(d("LwJ7GX"))) {
            this.zoomIn();
        }
        else if (a.startsWith(d("LwJ7ACl"))) {
            this.zoomOut();
        }
        else if (a.equals(d("ElJ*"))) {
            this.stopAutoPan();
        }
        else if (a.equals(d("ElJ*CYnL?"))) {
            this.stopMovie();
        }
        else if (a.startsWith(d("D}V?Z"))) {
            this.m();
        }
        else if (a.startsWith(d("PwW-OD|"))) {
            this.forward();
        }
        else if (a.startsWith(d("TyF1YWjA"))) {
            this.backward();
        }
        else if (a.startsWith(d("Fj@,GYmV)MSv@"))) {
            this.previousScene();
        }
        else if (a.startsWith(d("X}].]U}K?"))) {
            this.nextScene();
        }
        else if (a.startsWith(d("FtD#^WlM")) || a.startsWith(d("FtD#CYnL?"))) {
            if (vector != null) {
                int intValue = -1;
                try {
                    intValue = new Integer(vector.elementAt(0));
                }
                catch (Exception ex2) {}
                this.playMovie(intValue);
            }
        }
        else if (a.startsWith(d("FtD#^WmV?^WlM"))) {
            this.playPausePath();
        }
        else if (a.startsWith(d("FtD#^WvJ"))) {
            if (vector != null) {
                int intValue2 = -1;
                try {
                    intValue2 = new Integer(vector.elementAt(0));
                }
                catch (Exception ex3) {}
                this.switchToScene(intValue2);
            }
        }
        else if (a.startsWith(d("FtD#]YmK>"))) {
            if (vector != null) {
                int intValue3 = -1;
                try {
                    intValue3 = new Integer(vector.elementAt(0));
                }
                catch (Exception ex4) {}
                boolean b = true;
                if (vector.size() > 1 && !vector.elementAt(1).equalsIgnoreCase(d("BjP?"))) {
                    b = false;
                }
                this.playSound(intValue3, b);
            }
        }
        else if (a.startsWith(d("[mQ?"))) {
            this.d.a();
        }
        else if (a.startsWith(d("EpJ-FE"))) {
            this.showHS();
        }
        else if (a.startsWith(d("^qA?FE"))) {
            this.hideHS();
        }
        else if (a.startsWith(d("BwB=BSpV"))) {
            this.toggleHS();
        }
        else if (a.startsWith(d("UtJ)KAqK>AA"))) {
            this.n();
        }
        else if (a.startsWith(d("_vS5ESrV"))) {
            if (vector.size() > 0) {
                this.b(vector.elementAt(0));
            }
        }
        else if (a.startsWith(d("Yh@4[Dt"))) {
            try {
                final String s2 = vector.elementAt(0);
                if (!s2.trim().equals("")) {
                    URL url = null;
                    if (this.F != null && s2.substring(0, 11).equalsIgnoreCase(d("\\yS;]UjL*Z\f"))) {
                        System.out.println(s2);
                        this.b(s2);
                        return;
                    }
                    if (s2.length() > 4 && url == null && s2.substring(0, 4).equalsIgnoreCase(d("AoRt"))) {
                        url = new URL(d("^lQ*\u0014\u00197") + s2);
                    }
                    if (s2.indexOf("@") != -1 && url == null && !s2.substring(0, 7).equalsIgnoreCase(d("[yL6ZY\""))) {
                        url = new URL(d("[yL6ZY\"") + s2);
                    }
                    if (url == null) {
                        url = new URL(this.getDocumentBase(), s2);
                    }
                    if (vector.size() >= 2) {
                        this.getAppletContext().showDocument(url, vector.elementAt(1));
                    }
                    else {
                        this.getAppletContext().showDocument(url);
                    }
                }
            }
            catch (Exception ex5) {}
        }
        else if (a.startsWith(d("EpJ-FStU"))) {
            String s3 = null;
            try {
                s3 = vector.elementAt(0);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            this.c(s3);
        }
        else if (a.equalsIgnoreCase(d("WmQ5^Wv"))) {
            double doubleValue = 0.0;
            double doubleValue2 = 0.0;
            double doubleValue3 = 0.0;
            if (vector != null && vector.size() > 0) {
                try {
                    doubleValue = new Double(vector.elementAt(0));
                }
                catch (Exception ex6) {}
                if (vector.size() > 1) {
                    try {
                        doubleValue2 = new Double(vector.elementAt(1));
                    }
                    catch (Exception ex7) {}
                    if (vector.size() > 2) {
                        try {
                            doubleValue3 = new Double(vector.elementAt(2));
                        }
                        catch (Exception ex8) {}
                    }
                }
                this.autoPan(doubleValue, doubleValue2, doubleValue3);
            }
        }
        else if (a.equalsIgnoreCase(d("WmQ5^WvD.\\Wl@"))) {
            int intValue4 = 0;
            int intValue5 = 0;
            int intValue6 = 0;
            if (vector != null && vector.size() > 0) {
                try {
                    intValue4 = new Integer(vector.elementAt(0));
                }
                catch (Exception ex9) {}
                if (vector.size() > 1) {
                    try {
                        intValue5 = new Integer(vector.elementAt(1));
                    }
                    catch (Exception ex10) {}
                    if (vector.size() > 2) {
                        try {
                            intValue6 = new Integer(vector.elementAt(2));
                        }
                        catch (Exception ex11) {}
                    }
                }
                this.autoPanAtRate(intValue4, intValue5, intValue6);
            }
        }
    }
    
    by h() {
        return this.c;
    }
    
    bv i() {
        return this.d;
    }
    
    du j() {
        return this.E;
    }
    
    public String k() {
        return this.a;
    }
    
    public int l() {
        if (this.q != null) {
            return this.q.d();
        }
        return -1;
    }
    
    public void actionOnViewPortChanged(final double n, final double n2, final double n3) {
        if (this.r != null) {
            ((r)this.r.a()).a(n, n2, n3);
        }
        if (this.C != null) {
            for (int i = 0; i < this.C.size(); ++i) {
                try {
                    ((CommunicationAction)this.C.elementAt(i)).actionOnViewPortChanged(n, n2, n3);
                }
                catch (Exception ex) {
                    System.err.println(d("ymQ)GR}\u0005\u001f\\DwW`\u000e") + this.D.elementAt(i).toString());
                    ex.printStackTrace();
                }
            }
        }
    }
    
    public void actionOnPanoSwitching(final int n, final double n2, final double n3, final double n4) {
        if (!this.h) {
            this.i.a(new ca(n, n2, n3, n4));
        }
        else {
            this.h = false;
        }
    }
    
    public void actionOnPanoSwitched(final int n) {
        if (this.t != null) {
            this.t.e().e(n);
        }
        if (this.u != null) {
            this.u.b(n);
        }
        if (this.v != null) {
            this.v.d(n);
        }
        if (this.w != null) {
            this.w.e().a(this.c.g.a(n).i);
            if (this.w.getParent() != null && this.w.getParent().getParent() != null) {
                this.w.setVisible(false);
                this.w.setBounds(0, 0, this.w.getParent().getParent().getBounds().width, 0);
                ((p)this.w.getParent().getParent()).validate();
                if (((p)this.w.getParent().getParent()).f()) {
                    this.w.setBounds(0, 0, this.w.getParent().getParent().getBounds().width - com.easypano.tw.o.l.width, 0);
                    ((p)this.w.getParent().getParent()).validate();
                }
                this.w.setVisible(true);
            }
        }
        if (this.x != null) {
            this.x.e().a(this.c.g.a(n).e);
        }
        if (this.r != null) {
            ((r)this.r.a()).a(n);
            this.r.repaint();
        }
        if (this.C != null) {
            for (int i = 0; i < this.C.size(); ++i) {
                try {
                    ((CommunicationAction)this.C.elementAt(i)).actionOnPanoSwitched(n);
                }
                catch (Exception ex) {
                    System.err.println(d("ymQ)GR}\u0005\u001f\\DwW`\u000e") + this.D.elementAt(i).toString());
                    ex.printStackTrace();
                }
            }
        }
        this.d.a(this.c.g.a(n).f, true, false);
    }
    
    public void pathStateChanged(final int n, final int n2) {
        final Vector l = this.c.e.l();
        final Vector m = this.c.e.m();
        for (int i = 0; i < l.size(); ++i) {
            try {
                if (new Integer(m.elementAt(i)) == n) {
                    switch (n2) {
                        case 10: {
                            l.elementAt(i).e().a(true);
                            break;
                        }
                        case 11:
                        case 12: {
                            l.elementAt(i).e().a(false);
                            break;
                        }
                    }
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public void a(final int n) {
        if (this.q != null) {
            this.q.b(n);
        }
    }
    
    public void b(final String s) {
        if (this.F != null && s != null) {
            try {
                this.F.eval(s);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public void zoomIn() {
        if (this.q != null) {
            this.q.f();
        }
    }
    
    public void zoomOut() {
        if (this.q != null) {
            this.q.g();
        }
    }
    
    public void panLeft() {
        if (this.q != null) {
            this.q.h();
        }
    }
    
    public void panRight() {
        if (this.q != null) {
            this.q.i();
        }
    }
    
    public void panUp() {
        if (this.q != null) {
            this.q.j();
        }
    }
    
    public void panDown() {
        if (this.q != null) {
            this.q.k();
        }
    }
    
    public void autoPan(final double n, final double n2, final double n3) {
        if (this.q != null) {
            this.q.a(n, n2, n3);
        }
    }
    
    public void autoPanAtRate(final int n, final int n2, final int n3) {
        if (this.q != null) {
            this.q.a(n, n2, n3);
        }
    }
    
    public void stopAutoPan() {
        if (this.q != null) {
            this.q.stopAutoPan();
        }
    }
    
    public void gotoView(double n, double n2, double n3) {
        if (this.q != null) {
            final ci a = this.c.g.a(this.q.d());
            if (a != null) {
                switch (a.d) {
                    case 2:
                    case 3: {
                        n = n * 3.141592653589793 / 180.0 + 3.141592653589793;
                        n2 = n2 * 3.141592653589793 / 180.0;
                        n3 = n3 * 3.141592653589793 / 180.0;
                        break;
                    }
                }
                this.q.b(n, n2, n3);
            }
        }
    }
    
    public void m() {
        if (this.q != null) {
            this.q.stopAutoPan();
            final ci a = this.c.g.a(this.q.d());
            this.q.b(a.q, a.t, a.w);
        }
    }
    
    public void playPath(final int n) {
        this.playMovie(n);
    }
    
    public void playMovie(final int n) {
        if (this.q != null) {
            this.q.c(n);
        }
    }
    
    public void playPausePath() {
        if (this.q != null) {
            this.q.l();
        }
    }
    
    public void stopMovie() {
        if (this.q != null) {
            this.q.stopMovie();
        }
    }
    
    public void showHS() {
        if (this.q != null) {
            this.q.m();
        }
    }
    
    public void hideHS() {
        if (this.q != null) {
            this.q.n();
        }
    }
    
    public void toggleHS() {
        if (this.q != null) {
            this.q.o();
        }
    }
    
    public void switchToScene(final int n) {
        if (this.q != null) {
            final ci a = this.c.g.a(n);
            this.a(n, a.q, a.t, a.w, a.h);
        }
    }
    
    public void switchToScene(final int n, double n2, double n3, double n4) {
        if (this.q != null) {
            final ci a = this.c.g.a(n);
            if (a != null) {
                switch (a.d) {
                    case 2:
                    case 3: {
                        n2 = n2 * 3.141592653589793 / 180.0 + 3.141592653589793;
                        n3 = n3 * 3.141592653589793 / 180.0;
                        n4 = n4 * 3.141592653589793 / 180.0;
                        break;
                    }
                }
                this.a(n, n2, n3, n4, a.h);
            }
        }
    }
    
    public void a(final int n, final double n2, final double n3, final double n4, final int n5) {
        if (this.q != null) {
            this.c.d.a(this.c.g.a(n).k, new dw(this, n, n2, n3, n4, n5), 1);
        }
    }
    
    public void nextScene() {
        if (this.q != null) {
            int n = -1;
            if (this.c.g.a() > 0) {
                n = this.q.d() + 1;
                if (n >= this.c.g.a()) {
                    n = 0;
                }
            }
            this.switchToScene(n);
        }
    }
    
    public void previousScene() {
        if (this.q != null) {
            int n = -1;
            if (this.c.g.a() > 0) {
                n = this.q.d() - 1;
                if (n < 0) {
                    n = this.c.g.a() - 1;
                }
            }
            this.switchToScene(n);
        }
    }
    
    public void forward() {
        if (this.q != null) {
            final ca c = this.i.c();
            if (c == null) {
                this.nextScene();
            }
            else {
                this.h = true;
                final ci a = this.c.g.a(c.a);
                if (a != null) {
                    this.a(c.a, c.b, c.c, c.d, a.h);
                }
            }
        }
    }
    
    public void backward() {
        if (this.q != null) {
            if (this.i.a() == this.i.e()) {
                final a c = this.q.c();
                if (c != null) {
                    this.i.b(new ca(this.q.d(), c.d(), c.f(), c.e()));
                }
            }
            final ca d = this.i.d();
            if (d == null) {
                this.previousScene();
            }
            else {
                this.h = true;
                final ci a = this.c.g.a(d.a);
                if (a != null) {
                    this.a(d.a, d.b, d.c, d.d, a.h);
                }
            }
        }
    }
    
    public void n() {
        if (this.F != null && this.H) {
            try {
                this.F.eval(d("\\yS;]UjL*Z\flJ*\u0000AqK>AA6F6AE}\rs"));
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public void c(final String s) {
        if (s != null) {
            this.p().a((Image)this.c.d.a(s, 1));
            final Dimension preferredSize = this.p().getPreferredSize();
            final Rectangle bounds = this.getBounds();
            this.p().setBounds((bounds.width - preferredSize.width) / 2, (bounds.height - preferredSize.height) / 2, preferredSize.width, preferredSize.height);
            this.p().setVisible(true);
        }
    }
    
    public void playSound(final int n, final boolean b) {
        this.d.a(this.c.h.a(n), b, false);
    }
    
    public void stopSound() {
        this.d.stopAudioClip();
    }
    
    public k o() {
        return this.u;
    }
    
    public void b(final boolean b) {
        if (this.u != null) {
            this.u.d(b);
        }
    }
    
    public br p() {
        if (this.B == null) {
            this.add(this.B = new br(), 0);
        }
        return this.B;
    }
    
    public void a(final Component component, final int n, final int n2) {
    }
    
    public bq q() {
        return null;
    }
    
    public bp r() {
        return null;
    }
    
    static by a(final TWViewer twViewer) {
        return twViewer.c;
    }
    
    static h b(final TWViewer twViewer) {
        return twViewer.p;
    }
    
    static void a(final TWViewer twViewer, final cb i) {
        twViewer.i = i;
    }
    
    static du c(final TWViewer twViewer) {
        return twViewer.E;
    }
    
    static bv d(final TWViewer twViewer) {
        return twViewer.d;
    }
    
    static void a(final TWViewer twViewer, final bt q) {
        twViewer.q = q;
    }
    
    static void a(final TWViewer twViewer, final m r) {
        twViewer.r = r;
    }
    
    static void a(final TWViewer twViewer, final p s) {
        twViewer.s = s;
    }
    
    static void a(final TWViewer twViewer, final l t) {
        twViewer.t = t;
    }
    
    static void a(final TWViewer twViewer, final k u) {
        twViewer.u = u;
    }
    
    static void a(final TWViewer twViewer, final q v) {
        twViewer.v = v;
    }
    
    static void a(final TWViewer twViewer, final h w) {
        twViewer.w = w;
    }
    
    static void b(final TWViewer twViewer, final h x) {
        twViewer.x = x;
    }
    
    static k e(final TWViewer twViewer) {
        return twViewer.u;
    }
    
    static bt f(final TWViewer twViewer) {
        return twViewer.q;
    }
    
    static p g(final TWViewer twViewer) {
        return twViewer.s;
    }
    
    static void c(final TWViewer twViewer, final h p2) {
        twViewer.p = p2;
    }
    
    static void a(final TWViewer twViewer, final Image n) {
        twViewer.n = n;
    }
    
    static void h(final TWViewer twViewer) {
        twViewer.b();
    }
    
    static void i(final TWViewer twViewer) {
        twViewer.e();
    }
    
    static void a(final TWViewer twViewer, final boolean h) {
        twViewer.h = h;
    }
    
    private static String d(final String s) {
        final char[] charArray = s.toCharArray();
        final int length = charArray.length;
        int n = 0;
        while (true) {
            Label_0094: {
                if (length > 1) {
                    break Label_0094;
                }
                char[] array2;
                char[] array = array2 = charArray;
                int n3;
                int n2 = n3 = n;
                while (true) {
                    final char c = array2[n3];
                    char c2 = '\0';
                    switch (n % 5) {
                        case 0: {
                            c2 = '6';
                            break;
                        }
                        case 1: {
                            c2 = '\u0018';
                            break;
                        }
                        case 2: {
                            c2 = '%';
                            break;
                        }
                        case 3: {
                            c2 = 'Z';
                            break;
                        }
                        default: {
                            c2 = '.';
                            break;
                        }
                    }
                    array[n2] = (char)(c ^ c2);
                    ++n;
                    if (length != 0) {
                        break;
                    }
                    array = (array2 = charArray);
                    n2 = (n3 = length);
                }
            }
            if (n >= length) {
                return new String(charArray);
            }
            continue;
        }
    }
}
