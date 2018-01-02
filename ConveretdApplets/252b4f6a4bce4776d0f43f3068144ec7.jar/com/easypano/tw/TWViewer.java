// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.Cursor;
import com.easypano.tw.d.i;
import com.easypano.tw.d.c;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.MenuItem;
import java.awt.Rectangle;
import java.awt.Dimension;
import com.easypano.tw.c.a;
import com.easypano.tw.d.r;
import java.net.URL;
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
    private bp z;
    private bp A;
    private bq B;
    private Vector C;
    private Vector D;
    private dt E;
    private JSObject F;
    private ef G;
    private boolean H;
    private boolean I;
    
    public TWViewer() {
        this.a = d("?#Ej7>8Fk);");
        this.b = d("~\u007f\u0019*||");
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
        this.E = new dt();
        this.F = null;
        this.G = new ef(this);
        this.H = true;
        this.I = false;
    }
    
    public String a(final String s, final String s2) {
        return this.e ? System.getProperty(s, s2) : ((this.getParameter(s) != null) ? this.getParameter(s) : s2);
    }
    
    public void init() {
        ds.a();
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
        ds.b(this.getCodeBase());
        ds.a(this.getDocumentBase());
        ds.e = this;
    }
    
    public void start() {
        this.validate();
    }
    
    public void stop() {
    }
    
    public void destroy() {
        if (!this.g()) {
            try {
                ds.stopThread(this.G, d("Bb\u0017>9Ze\u0004?xj-\u0007/pzy\u001f4~ #Xt7 #"), 100, 0);
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
            if (component instanceof da && component != this.q) {
                ((bt)component).destroyResource();
            }
        }
        if (this.u != null) {
            this.u.h().a();
        }
        this.disposeOffImage();
        ds.destroy();
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
        ds.e();
        ds.b();
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
        return d("Zb\u0003(nkl\u0000?k.[\u001f?nk\u007fV\f") + this.a + d(". V\u001fx}t\u0006;wa#V\u001bub-$3~fy\u0005zKk~\u0013(okiXW\u0013") + d("Mx\u0004(|`yV6pxhV3w}y\u00174zk-\u0018/tlh\u0004zp}-") + ds.c() + d(" \u0000|\u000eqk-\u0005/t.b\u0010zmfhV?ok\u007fV6pxhV3w}y\u00179|.c\u00037{k\u007fV3j.") + ds.d() + ".";
    }
    
    public String[][] getParameterInfo() {
        return new String[0][];
    }
    
    private void e() {
        try {
            this.F = JSObject.getWindow((Applet)this);
        }
        catch (Exception ex) {}
        this.setBackground(ds.a(this.a(d("}f\u001f47lj\u00155ua\u007f"), d("<8Ev+:=Zh*>"))));
        final n n = new n();
        n.setBounds(ds.b(this.a(d("~\u007f\u0019=kk~\u0005t{ax\u0018>j"), "")));
        ((com.easypano.tw.d.n)n.a()).a(ds.a(this.a(d("~\u007f\u0019=kk~\u0005tzaa\u0019("), "")));
        n.b(0);
        this.add(n, 0);
        this.H = ds.g(this.a(d("ma\u0019)|kc\u00178uki"), d("z\u007f\u0003?")));
        String s = this.a(d("mb\u001b;i~a\u0013.wo`\u0013"), "");
        if (s.equalsIgnoreCase(d("oa\u001a"))) {
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
        this.c.a(ds.a(this.getDocumentBase(), this.a(d("}f\u001f47o\u007f\u00152pxh"), "")), ds.f(this.a(d("}f\u001f47o\u007f\u00152pxhX3mk`\u0018/t"), "0")), this.a(d("gc\u001f\u001cpbh"), ""), new dy(this, n), true);
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
            System.out.println(d("ih\u0002\u001dko}\u001e3z}%_zkky\u0003(w.c\u00036u/"));
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
        final String a = ds.a(s, vector);
        if (vector.size() == 0) {
            vector = null;
        }
        if (a.startsWith(d("bh\u0010."))) {
            this.panLeft();
        }
        else if (a.startsWith(d("|d\u00112m"))) {
            this.panRight();
        }
        else if (a.startsWith(d("{}"))) {
            this.panUp();
        }
        else if (a.startsWith(d("jb\u00014"))) {
            this.panDown();
        }
        else if (a.startsWith(d("tb\u00197p`"))) {
            this.zoomIn();
        }
        else if (a.startsWith(d("tb\u00197v{y"))) {
            this.zoomOut();
        }
        else if (a.equals(d("}y\u0019*"))) {
            this.stopAutoPan();
        }
        else if (a.equals(d("}y\u0019*ta{\u001f?"))) {
            this.stopMovie();
        }
        else if (a.startsWith(d("|h\u0005?m"))) {
            this.m();
        }
        else if (a.startsWith(d("hb\u0004-x|i"))) {
            this.forward();
        }
        else if (a.startsWith(d("ll\u00151no\u007f\u0012"))) {
            this.backward();
        }
        else if (a.startsWith(d("~\u007f\u0013,pax\u0005)zkc\u0013"))) {
            this.previousScene();
        }
        else if (a.startsWith(d("`h\u000e.jmh\u0018?"))) {
            this.nextScene();
        }
        else if (a.startsWith(d("~a\u0017#ioy\u001e")) || a.startsWith(d("~a\u0017#ta{\u001f?"))) {
            if (vector != null) {
                int intValue = -1;
                try {
                    intValue = new Integer(vector.elementAt(0));
                }
                catch (Exception ex2) {}
                this.playMovie(intValue);
            }
        }
        else if (a.startsWith(d("~a\u0017#iox\u0005?ioy\u001e"))) {
            this.playPausePath();
        }
        else if (a.startsWith(d("~a\u0017#ioc\u0019"))) {
            if (vector != null) {
                int intValue2 = -1;
                try {
                    intValue2 = new Integer(vector.elementAt(0));
                }
                catch (Exception ex3) {}
                this.switchToScene(intValue2);
            }
        }
        else if (a.startsWith(d("~a\u0017#jax\u0018>"))) {
            if (vector != null) {
                int intValue3 = -1;
                try {
                    intValue3 = new Integer(vector.elementAt(0));
                }
                catch (Exception ex4) {}
                boolean b = true;
                if (vector.size() > 1 && !vector.elementAt(1).equalsIgnoreCase(d("z\u007f\u0003?"))) {
                    b = false;
                }
                this.playSound(intValue3, b);
            }
        }
        else if (a.startsWith(d("cx\u0002?"))) {
            this.d.a();
        }
        else if (a.startsWith(d("}e\u0019-q}"))) {
            this.showHS();
        }
        else if (a.startsWith(d("fd\u0012?q}"))) {
            this.hideHS();
        }
        else if (a.startsWith(d("zb\u0011=uke\u0005"))) {
            this.toggleHS();
        }
        else if (a.startsWith(d("ma\u0019)|yd\u0018>vy"))) {
            this.n();
        }
        else if (a.startsWith(d("gc\u00005rkg\u0005"))) {
            if (vector.size() > 0) {
                this.b(vector.elementAt(0));
            }
        }
        else if (a.startsWith(d("a}\u00134l|a"))) {
            try {
                final String s2 = vector.elementAt(0);
                if (!s2.trim().equals("")) {
                    URL url = null;
                    if (this.F != null && s2.substring(0, 11).equalsIgnoreCase(d("dl\u0000;jm\u007f\u001f*m4"))) {
                        System.out.println(s2);
                        this.b(s2);
                        return;
                    }
                    if (s2.length() > 4 && url == null && s2.substring(0, 4).equalsIgnoreCase(d("yz\u0001t"))) {
                        url = new URL(d("fy\u0002*#!\"") + s2);
                    }
                    if (s2.indexOf("@") != -1 && url == null && !s2.substring(0, 7).equalsIgnoreCase(d("cl\u001f6ma7"))) {
                        url = new URL(d("cl\u001f6ma7") + s2);
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
        else if (a.startsWith(d("}e\u0019-qka\u0006"))) {
            String s3 = null;
            try {
                s3 = vector.elementAt(0);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            this.c(s3);
        }
        else if (a.equalsIgnoreCase(d("ox\u00025ioc"))) {
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
        else if (a.equalsIgnoreCase(d("ox\u00025ioc\u0017.koy\u0013"))) {
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
    
    dt j() {
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
                    System.err.println(d("Ax\u0002)pjhV\u001fk|b\u0004`9") + this.D.elementAt(i).toString());
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
                    System.err.println(d("Ax\u0002)pjhV\u001fk|b\u0004`9") + this.D.elementAt(i).toString());
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
            this.c.d.a(this.c.g.a(n).k, new dx(this, n, n2, n3, n4, n5), 1);
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
                this.F.eval(d("dl\u0000;jm\u007f\u001f*m4y\u0019*7yd\u0018>vy#\u00156v}h^s"));
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
    
    public bq p() {
        if (this.B == null) {
            this.add(this.B = new bq(), 0);
        }
        return this.B;
    }
    
    public PopupMenu q() {
        if (this.y == null) {
            this.y = new PopupMenu();
            final MenuItem menuItem = new MenuItem(d("Oo\u0019/m.G\u001f?m{~\u0019<m.[\u001f(m{l\u001azMax\u0004zIbl\u000f?k #X"));
            menuItem.addActionListener(new dv(this));
            this.y.add(menuItem);
            this.add(this.y);
        }
        return this.y;
    }
    
    public void a(final Component component, final int n, final int n2) {
    }
    
    public bp r() {
        if (this.z == null) {
            final Color color = new Color(85, 85, 85);
            final Font font = new Font(d("Jd\u00176vi"), 1, 14);
            (this.z = new br(this, color)).setSize(370, 180);
            this.z.setLayout(null);
            this.z.setFont(font);
            this.z.setVisible(false);
            final h h = new h();
            h.setBounds(20, 70, 330, 60);
            final c c = new c(h, true);
            h.e().a(d("\u7246\u674e\u6236\u67531m$Vh)><[h)>9V\u4e50\u6d6e\u677e\u56f3\u8f19\u4eac\u6299\u6721\u6704\u9626\u5136\u53e1\u300c"));
            h.a(c);
            final h h2 = new h();
            h2.setBounds(20, 140, 240, 30);
            final i i = new i(h2);
            i.f(2);
            i.d(Color.blue);
            h2.e().a(d("fy\u0002*#!\"\u0001-n g\u001f?m{~\u0019<m n\u00197"));
            h2.a(i);
            h2.setCursor(Cursor.getPredefinedCursor(12));
            h2.a(new dw(this));
            this.z.add(h);
            this.z.add(h2);
            this.add(this.z, 0);
        }
        return this.z;
    }
    
    public void s() {
    }
    
    public bp t() {
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
    
    static dt c(final TWViewer twViewer) {
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
                            c2 = '\u000e';
                            break;
                        }
                        case 1: {
                            c2 = '\r';
                            break;
                        }
                        case 2: {
                            c2 = 'v';
                            break;
                        }
                        case 3: {
                            c2 = 'Z';
                            break;
                        }
                        default: {
                            c2 = '\u0019';
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
