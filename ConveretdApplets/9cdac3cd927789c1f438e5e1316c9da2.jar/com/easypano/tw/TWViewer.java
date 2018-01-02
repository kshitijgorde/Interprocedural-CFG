// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.Cursor;
import com.easypano.tw.c.i;
import com.easypano.tw.c.c;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.MenuItem;
import java.awt.Rectangle;
import java.awt.Dimension;
import com.easypano.tw.b.a;
import com.easypano.tw.c.r;
import java.net.URL;
import java.awt.image.ImageObserver;
import java.util.Enumeration;
import com.easypano.tw.c.n;
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
    private bz c;
    private bw d;
    private boolean e;
    private boolean f;
    private boolean g;
    private boolean h;
    private cc i;
    private int j;
    private int k;
    private Image l;
    private Graphics m;
    private Image n;
    private Image o;
    private g p;
    private bu q;
    private l r;
    private o s;
    private k t;
    private j u;
    private p v;
    private g w;
    private g x;
    private PopupMenu y;
    private bq z;
    private bq A;
    private br B;
    private Vector C;
    private Vector D;
    private du E;
    private JSObject F;
    private eg G;
    private boolean H;
    private boolean I;
    
    public TWViewer() {
        this.a = c("'d/d");
        this.b = c("f8r!\\d");
        this.c = new bz(this);
        this.d = new bw(this);
        this.e = false;
        this.f = false;
        this.g = false;
        this.h = false;
        this.i = null;
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = null;
        this.p = new g();
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
        this.G = new eg(this);
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
        this.addMouseMotionListener(new bn(this));
        this.setLayout(null);
    }
    
    private void b() throws Exception {
        dt.b(this.getCodeBase());
        dt.a(this.getDocumentBase());
        dt.e = this;
    }
    
    public void start() {
        this.validate();
    }
    
    public void stop() {
    }
    
    public void destroy() {
        if (!this.g()) {
            try {
                dt.stopThread(this.G, c("Z%|5\u0019B\"o4Xrjl$Pb>t?^8d3\u007f\u00178d"), 100, 0);
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
                ((bu)component).destroyResource();
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
        return c("B%h#Ns+k4K6\u001ct4Ns8=\u0007\b8x(q\u00146\u000f|\"@f+s>\u00176\u000bq=\u0019D#z9MejO4Js8k4]8G\u0017\u0012Ld8x?M6&t'\\6#s\"Mw$~4\u0019x?p3\\djt\"\u0019") + dt.c() + c("8G\u0017\u0005Qsjn$T6%{qM~/=4Os8==P`/=8We>|2\\6$h<[s8=8J6") + dt.d() + ".";
    }
    
    public String[][] getParameterInfo() {
        return new String[0][];
    }
    
    private void e() {
        try {
            this.F = JSObject.getWindow((Applet)this);
        }
        catch (Exception ex) {}
        this.setBackground(dt.a(this.a(c("e!t?\u0017t-~>Uy8"), c("$\u007f.}\u000b\"z1c\n&"))));
        final m m = new m();
        m.setBounds(dt.b(this.a(c("f8r6Ks9n\u007f[y?s5J"), "")));
        ((n)m.a()).a(dt.a(this.a(c("f8r6Ks9n\u007fZy&r#"), "")));
        m.b(0);
        this.add(m, 0);
        this.H = dt.g(this.a(c("u&r\"\\s$|3Us."), c("b8h4")));
        String s = this.a(c("u%p0If&x%Ww'x"), "");
        if (s.equalsIgnoreCase(c("w&q"))) {
            final Enumeration<Applet> applets = this.getAppletContext().getApplets();
            int n = 0;
            while (applets.hasMoreElements()) {
                final Applet nextElement = applets.nextElement();
                if (nextElement instanceof CommunicationAction) {
                    this.C.addElement(nextElement);
                    this.D.addElement(new Integer(n++));
                }
            }
            if (this.C.size() == 0) {
                this.C = null;
                this.D = null;
            }
        }
        else {
            final Vector vector = new Vector<String>();
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
        this.c.a(dt.a(this.getDocumentBase(), this.a(c("e!t?\u0017w8~9P`/"), "")), dt.f(this.a(c("e!t?\u0017w8~9P`/38Ms's$T"), "0")), this.a(c("\u007f$t\u0017Pz/"), ""), new dz(this, m), true);
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
            System.out.println(c("q/i\u0016Kw:u8Zeb4qKs>h#W6$h=U7"));
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.l == null) {
            this.j = this.getBounds().width;
            this.k = this.getBounds().height;
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
        if (a.startsWith(c("z/{%"))) {
            this.panLeft();
        }
        else if (a.startsWith(c("d#z9M"))) {
            this.panRight();
        }
        else if (a.startsWith(c("c:"))) {
            this.panUp();
        }
        else if (a.startsWith(c("r%j?"))) {
            this.panDown();
        }
        else if (a.startsWith(c("l%r<Px"))) {
            this.zoomIn();
        }
        else if (a.startsWith(c("l%r<Vc>"))) {
            this.zoomOut();
        }
        else if (a.equals(c("e>r!"))) {
            this.stopAutoPan();
        }
        else if (a.equals(c("e>r!Ty<t4"))) {
            this.stopMovie();
        }
        else if (a.startsWith(c("d/n4M"))) {
            this.m();
        }
        else if (a.startsWith(c("p%o&Xd."))) {
            this.forward();
        }
        else if (a.startsWith(c("t+~:Nw8y"))) {
            this.backward();
        }
        else if (a.startsWith(c("f8x'Py?n\"Zs$x"))) {
            this.o();
        }
        else if (a.startsWith(c("x/e%Ju/s4"))) {
            this.n();
        }
        else if (a.startsWith(c("f&|(Iw>u")) || a.startsWith(c("f&|(Ty<t4"))) {
            if (vector != null) {
                int intValue = -1;
                try {
                    intValue = new Integer(vector.elementAt(0));
                }
                catch (Exception ex3) {}
                this.playMovie(intValue);
            }
        }
        else if (a.startsWith(c("f&|(Iw?n4Iw>u"))) {
            this.playPausePath();
        }
        else if (a.startsWith(c("f&|(Iw$r"))) {
            if (vector != null) {
                int intValue2 = -1;
                try {
                    intValue2 = new Integer(vector.elementAt(0));
                }
                catch (Exception ex4) {}
                this.switchToScene(intValue2);
            }
        }
        else if (a.startsWith(c("f&|(Jy?s5"))) {
            if (vector != null) {
                int intValue3 = -1;
                try {
                    intValue3 = new Integer(vector.elementAt(0));
                }
                catch (Exception ex5) {}
                boolean b = true;
                if (vector.size() > 1 && !vector.elementAt(1).equalsIgnoreCase(c("b8h4"))) {
                    b = false;
                }
                this.playSound(intValue3, b);
            }
        }
        else if (a.startsWith(c("{?i4"))) {
            this.d.a();
        }
        else if (a.startsWith(c("e\"r&Qe"))) {
            this.showHS();
        }
        else if (a.startsWith(c("~#y4Qe"))) {
            this.hideHS();
        }
        else if (a.startsWith(c("b%z6Us\"n"))) {
            this.toggleHS();
        }
        else if (a.startsWith(c("u&r\"\\a#s5Va"))) {
            this.p();
        }
        else if (a.startsWith(c("y:x?Ld&"))) {
            try {
                final String s2 = vector.elementAt(0);
                if (!s2.trim().equals("")) {
                    URL url = null;
                    if (this.F != null && s2.substring(0, 11).equalsIgnoreCase(c("|+k0Ju8t!M,"))) {
                        try {
                            System.out.println(s2);
                            this.F.eval(s2);
                        }
                        catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        return;
                    }
                    if (s2.length() > 4 && url == null && s2.substring(0, 4).equalsIgnoreCase(c("a=j\u007f"))) {
                        url = new URL(c("~>i!\u00039e") + s2);
                    }
                    if (s2.indexOf("@") != -1 && url == null && !s2.substring(0, 7).equalsIgnoreCase(c("{+t=Myp"))) {
                        url = new URL(c("{+t=Myp") + s2);
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
            catch (Exception ex6) {}
        }
        else if (a.startsWith(c("e\"r&Qs&m"))) {
            String s3 = null;
            try {
                s3 = vector.elementAt(0);
            }
            catch (Exception ex2) {
                ex2.printStackTrace();
            }
            this.b(s3);
        }
        else if (a.equalsIgnoreCase(c("w?i>Iw$"))) {
            double doubleValue = 0.0;
            double doubleValue2 = 0.0;
            double doubleValue3 = 0.0;
            if (vector != null && vector.size() > 0) {
                try {
                    doubleValue = new Double(vector.elementAt(0));
                }
                catch (Exception ex7) {}
                if (vector.size() > 1) {
                    try {
                        doubleValue2 = new Double(vector.elementAt(1));
                    }
                    catch (Exception ex8) {}
                    if (vector.size() > 2) {
                        try {
                            doubleValue3 = new Double(vector.elementAt(2));
                        }
                        catch (Exception ex9) {}
                    }
                }
                this.autoPan(doubleValue, doubleValue2, doubleValue3);
            }
        }
        else if (a.equalsIgnoreCase(c("w?i>Iw$|%Kw>x"))) {
            int intValue4 = 0;
            int intValue5 = 0;
            int intValue6 = 0;
            if (vector != null && vector.size() > 0) {
                try {
                    intValue4 = new Integer(vector.elementAt(0));
                }
                catch (Exception ex10) {}
                if (vector.size() > 1) {
                    try {
                        intValue5 = new Integer(vector.elementAt(1));
                    }
                    catch (Exception ex11) {}
                    if (vector.size() > 2) {
                        try {
                            intValue6 = new Integer(vector.elementAt(2));
                        }
                        catch (Exception ex12) {}
                    }
                }
                this.autoPanAtRate(intValue4, intValue5, intValue6);
            }
        }
    }
    
    bz h() {
        return this.c;
    }
    
    bw i() {
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
                    System.err.println(c("Y?i\"Pr/=\u0014Kd%ok\u0019") + this.D.elementAt(i).toString());
                    ex.printStackTrace();
                }
            }
        }
    }
    
    public void actionOnPanoSwitching(final int n, final double n2, final double n3, final double n4) {
        if (!this.h) {
            this.i.a(new cb(n, n2, n3, n4));
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
                ((o)this.w.getParent().getParent()).validate();
                if (((o)this.w.getParent().getParent()).f()) {
                    this.w.setBounds(0, 0, this.w.getParent().getParent().getBounds().width - n.l.width, 0);
                    ((o)this.w.getParent().getParent()).validate();
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
                    System.err.println(c("Y?i\"Pr/=\u0014Kd%ok\u0019") + this.D.elementAt(i).toString());
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
            final cj a = this.c.g.a(this.q.d());
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
            final cj a = this.c.g.a(this.q.d());
            this.q.b(a.o, a.r, a.u);
        }
    }
    
    public void playMovie(final int n) {
        if (this.q != null) {
            this.q.c(n);
        }
    }
    
    public void playPath(final int n) {
        this.playMovie(n);
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
            final cj a = this.c.g.a(n);
            this.a(n, a.o, a.r, a.u, a.h);
        }
    }
    
    public void switchToScene(final int n, double n2, double n3, double n4) {
        if (this.q != null) {
            final cj a = this.c.g.a(n);
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
            this.c.d.a(this.c.g.a(n).k, new dy(this, n, n2, n3, n4, n5), 1);
        }
    }
    
    public void n() {
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
    
    public void o() {
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
            final cb c = this.i.c();
            if (c == null) {
                this.n();
            }
            else {
                this.h = true;
                final cj a = this.c.g.a(c.a);
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
                    this.i.b(new cb(this.q.d(), c.d(), c.f(), c.e()));
                }
            }
            final cb d = this.i.d();
            if (d == null) {
                this.o();
            }
            else {
                this.h = true;
                final cj a = this.c.g.a(d.a);
                if (a != null) {
                    this.a(d.a, d.b, d.c, d.d, a.h);
                }
            }
        }
    }
    
    public void p() {
        if (this.F != null && this.H) {
            try {
                this.F.eval(c("|+k0Ju8t!M,>r!\u0017a#s5Vad~=Ve/5x"));
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public void b(final String s) {
        if (s != null) {
            this.r().a((Image)this.c.d.a(s, 1));
            final Dimension preferredSize = this.r().getPreferredSize();
            final Rectangle bounds = this.getBounds();
            this.r().setBounds((bounds.width - preferredSize.width) / 2, (bounds.height - preferredSize.height) / 2, preferredSize.width, preferredSize.height);
            this.r().setVisible(true);
        }
    }
    
    public void playSound(final int n, final boolean b) {
        this.d.a(this.c.h.a(n), b, false);
    }
    
    public void stopSound() {
        this.d.stopAudioClip();
    }
    
    public j q() {
        return this.u;
    }
    
    public void b(final boolean b) {
        if (this.u != null) {
            this.u.d(b);
        }
    }
    
    public br r() {
        if (this.B == null) {
            this.add(this.B = new br(), 0);
        }
        return this.B;
    }
    
    public PopupMenu s() {
        if (this.y == null) {
            this.y = new PopupMenu();
            final MenuItem menuItem = new MenuItem(c("W(r$M6\u000f|\"@f+s>\u0019@#o%Lw&=\u0005Vc8=\u0001Uw3x#\u00178d"));
            menuItem.addActionListener(new dw(this));
            this.y.add(menuItem);
            this.add(this.y);
        }
        return this.y;
    }
    
    public void a(final Component component, final int n, final int n2) {
        if (component != null) {
            try {
                this.s().show(component, n, n2);
            }
            catch (IllegalArgumentException ex2) {}
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public bq t() {
        if (this.z == null) {
            final Color color = new Color(85, 85, 85);
            final Font font = new Font(c("E3n%\\{"), 1, 14);
            (this.z = new bs(this, color)).setSize(370, 180);
            this.z.setLayout(null);
            this.z.setFont(font);
            this.z.setVisible(false);
            final g g = new g();
            g.setBounds(20, 70, 330, 60);
            final c c = new c(g, true);
            g.e().a(c("U%m(K\u007f-u%\u0019>)4q\u000b&z,|\u000b&z)qiw$r#X{+=\u0005\\u\"s>Uy-t4J6\tr#Iy8|%Py$=\u001dMrd"));
            g.a(c);
            final g g2 = new g();
            g2.setBounds(20, 140, 240, 30);
            final i i = new i(g2);
            i.f(2);
            i.d(Color.blue);
            g2.e().a(c("~>i!\u00039ej&N8/|\"@f+s>\u0017u%p"));
            g2.a(i);
            g2.setCursor(Cursor.getPredefinedCursor(12));
            g2.a(new dx(this));
            this.z.add(g);
            this.z.add(g2);
            this.add(this.z, 0);
        }
        return this.z;
    }
    
    public void u() {
        final Rectangle bounds = this.getBounds();
        this.t().setLocation((bounds.width - 370) / 2, (bounds.height - 180) / 2);
        this.t().setVisible(true);
    }
    
    public bq v() {
        return null;
    }
    
    static g a(final TWViewer twViewer) {
        return twViewer.p;
    }
    
    static bz b(final TWViewer twViewer) {
        return twViewer.c;
    }
    
    static void a(final TWViewer twViewer, final cc i) {
        twViewer.i = i;
    }
    
    static du c(final TWViewer twViewer) {
        return twViewer.E;
    }
    
    static bw d(final TWViewer twViewer) {
        return twViewer.d;
    }
    
    static void a(final TWViewer twViewer, final bu q) {
        twViewer.q = q;
    }
    
    static void a(final TWViewer twViewer, final l r) {
        twViewer.r = r;
    }
    
    static void a(final TWViewer twViewer, final o s) {
        twViewer.s = s;
    }
    
    static void a(final TWViewer twViewer, final k t) {
        twViewer.t = t;
    }
    
    static void a(final TWViewer twViewer, final j u) {
        twViewer.u = u;
    }
    
    static void a(final TWViewer twViewer, final p v) {
        twViewer.v = v;
    }
    
    static void a(final TWViewer twViewer, final g w) {
        twViewer.w = w;
    }
    
    static void b(final TWViewer twViewer, final g x) {
        twViewer.x = x;
    }
    
    static j e(final TWViewer twViewer) {
        return twViewer.u;
    }
    
    static bu f(final TWViewer twViewer) {
        return twViewer.q;
    }
    
    static o g(final TWViewer twViewer) {
        return twViewer.s;
    }
    
    static void c(final TWViewer twViewer, final g p2) {
        twViewer.p = p2;
    }
    
    static void a(final TWViewer twViewer, final Image n) {
        twViewer.n = n;
    }
    
    static String h(final TWViewer twViewer) {
        return twViewer.b;
    }
    
    static void i(final TWViewer twViewer) {
        twViewer.b();
    }
    
    static void j(final TWViewer twViewer) {
        twViewer.e();
    }
    
    static void a(final TWViewer twViewer, final boolean h) {
        twViewer.h = h;
    }
    
    private static String c(final String s) {
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
                            c2 = '\u0016';
                            break;
                        }
                        case 1: {
                            c2 = 'J';
                            break;
                        }
                        case 2: {
                            c2 = '\u001d';
                            break;
                        }
                        case 3: {
                            c2 = 'Q';
                            break;
                        }
                        default: {
                            c2 = '9';
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
