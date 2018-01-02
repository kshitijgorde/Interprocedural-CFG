// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.Rectangle;
import java.awt.Dimension;
import com.easypano.tw.b.a;
import com.easypano.tw.a.r;
import java.net.URL;
import java.awt.image.ImageObserver;
import java.util.Enumeration;
import com.easypano.tw.a.n;
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
    private g p;
    private bt q;
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
    private dt E;
    private JSObject F;
    private ed G;
    private boolean H;
    private boolean I;
    
    public TWViewer() {
        this.a = c("{\f4V\u000fz\u0013");
        this.b = c(":Pi\u0013D8");
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
        this.E = new dt();
        this.F = null;
        this.G = new ed(this);
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
        this.addMouseMotionListener(new bn(this));
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
                ds.stopThread(this.G, c("\u0006Mg\u0007\u0001\u001eJt\u0006@.\u0002w\u0016H>Vo\rFd\f(M\u000fd\f"), 100, 0);
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
        return c("\u001eMs\u0011V/Cp\u0006Sjto\u0006V/P&5\u0010d\u00103C\fjgg\u0010X:Ch\f\u000fjcj\u000f\u0001\u0018Ka\u000bU9\u0002T\u0006R/Pp\u0006Ed/\f T8Pc\rUjNo\u0015DjKh\u0010U+Le\u0006\u0001$Wk\u0001D8\u0002o\u0010\u0001") + ds.c() + c("d/\f7I/\u0002u\u0016LjM`CU\"G&\u0006W/P&\u000fH<G&\nO9Vg\u0000DjLs\u000eC/P&\nRj") + ds.d() + ".";
    }
    
    public String[][] getParameterInfo() {
        return new String[0][];
    }
    
    private void e() {
        try {
            this.F = JSObject.getWindow((Applet)this);
        }
        catch (Exception ex) {}
        this.setBackground(ds.a(this.a(c("9Io\r\u000f(Ee\fM%P"), c("x\u00175O\u0013~\u0012*Q\u0012z"))));
        final m m = new m();
        m.setBounds(ds.b(this.a(c(":Pi\u0004S/QuMC%Wh\u0007R"), "")));
        ((n)m.a()).a(ds.a(this.a(c(":Pi\u0004S/QuMB%Ni\u0011"), "")));
        m.b(0);
        this.add(m, 0);
        this.H = ds.g(this.a(c(")Ni\u0010D/Lg\u0001M/F"), c(">Ps\u0006")));
        String s = this.a(c(")Mk\u0002Q:Nc\u0017O+Oc"), "");
        if (s.equalsIgnoreCase(c("+Nj"))) {
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
        this.c.a(ds.a(this.getDocumentBase(), this.a(c("9Io\r\u000f+Pe\u000bH<G"), "")), ds.f(this.a(c("9Io\r\u000f+Pe\u000bH<G(\nU/Oh\u0016L"), "0")), this.a(c("#Lo%H&G"), ""), new dw(this, m), false);
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
            System.out.println(c("-Gr$S+Rn\nB9\n/CS/Vs\u0011OjLs\u000fMk"));
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
        if (a.startsWith(c("&G`\u0017"))) {
            this.panLeft();
        }
        else if (a.startsWith(c("8Ka\u000bU"))) {
            this.panRight();
        }
        else if (a.startsWith(c("?R"))) {
            this.panUp();
        }
        else if (a.startsWith(c(".Mq\r"))) {
            this.panDown();
        }
        else if (a.startsWith(c("0Mi\u000eH$"))) {
            this.zoomIn();
        }
        else if (a.startsWith(c("0Mi\u000eN?V"))) {
            this.zoomOut();
        }
        else if (a.equals(c("9Vi\u0013"))) {
            this.stopAutoPan();
        }
        else if (a.equals(c("9Vi\u0013L%To\u0006"))) {
            this.stopMovie();
        }
        else if (a.startsWith(c("8Gu\u0006U"))) {
            this.m();
        }
        else if (a.startsWith(c(",Mt\u0014@8F"))) {
            this.forward();
        }
        else if (a.startsWith(c("(Ce\bV+Pb"))) {
            this.backward();
        }
        else if (a.startsWith(c(":Pc\u0015H%Wu\u0010B/Lc"))) {
            this.previousScene();
        }
        else if (a.startsWith(c("$G~\u0017R)Gh\u0006"))) {
            this.nextScene();
        }
        else if (a.startsWith(c(":Ng\u001aQ+Vn")) || a.startsWith(c(":Ng\u001aL%To\u0006"))) {
            if (vector != null) {
                int intValue = -1;
                try {
                    intValue = new Integer(vector.elementAt(0));
                }
                catch (Exception ex3) {}
                this.playMovie(intValue);
            }
        }
        else if (a.startsWith(c(":Ng\u001aQ+Wu\u0006Q+Vn"))) {
            this.playPausePath();
        }
        else if (a.startsWith(c(":Ng\u001aQ+Li"))) {
            if (vector != null) {
                int intValue2 = -1;
                try {
                    intValue2 = new Integer(vector.elementAt(0));
                }
                catch (Exception ex4) {}
                this.switchToScene(intValue2);
            }
        }
        else if (a.startsWith(c(":Ng\u001aR%Wh\u0007"))) {
            if (vector != null) {
                int intValue3 = -1;
                try {
                    intValue3 = new Integer(vector.elementAt(0));
                }
                catch (Exception ex5) {}
                boolean b = true;
                if (vector.size() > 1 && !vector.elementAt(1).equalsIgnoreCase(c(">Ps\u0006"))) {
                    b = false;
                }
                this.playSound(intValue3, b);
            }
        }
        else if (a.startsWith(c("'Wr\u0006"))) {
            this.d.a();
        }
        else if (a.startsWith(c("9Ji\u0014I9"))) {
            this.showHS();
        }
        else if (a.startsWith(c("\"Kb\u0006I9"))) {
            this.hideHS();
        }
        else if (a.startsWith(c(">Ma\u0004M/Ju"))) {
            this.toggleHS();
        }
        else if (a.startsWith(c(")Ni\u0010D=Kh\u0007N="))) {
            this.n();
        }
        else if (a.startsWith(c("%Rc\rT8N"))) {
            try {
                final String s2 = vector.elementAt(0);
                if (!s2.trim().equals("")) {
                    URL url = null;
                    if (this.F != null && s2.substring(0, 11).equalsIgnoreCase(c(" Cp\u0002R)Po\u0013Up"))) {
                        try {
                            System.out.println(s2);
                            this.F.eval(s2);
                        }
                        catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        return;
                    }
                    if (s2.length() > 4 && url == null && s2.substring(0, 4).equalsIgnoreCase(c("=UqM"))) {
                        url = new URL(c("\"Vr\u0013\u001be\r") + s2);
                    }
                    if (s2.indexOf("@") != -1 && url == null && !s2.substring(0, 7).equalsIgnoreCase(c("'Co\u000fU%\u0018"))) {
                        url = new URL(c("'Co\u000fU%\u0018") + s2);
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
        else if (a.startsWith(c("9Ji\u0014I/Nv"))) {
            String s3 = null;
            try {
                s3 = vector.elementAt(0);
            }
            catch (Exception ex2) {
                ex2.printStackTrace();
            }
            this.b(s3);
        }
        else if (a.equalsIgnoreCase(c("+Wr\fQ+L"))) {
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
        else if (a.equalsIgnoreCase(c("+Wr\fQ+Lg\u0017S+Vc"))) {
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
                    System.err.println(c("\u0005Wr\u0010H.G&&S8MtY\u0001") + this.D.elementAt(i).toString());
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
                    System.err.println(c("\u0005Wr\u0010H.G&&S8MtY\u0001") + this.D.elementAt(i).toString());
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
            this.q.b(a.o, a.r, a.u);
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
            this.a(n, a.o, a.r, a.u, a.h);
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
            this.c.d.a(this.c.g.a(n).k, new dv(this, n, n2, n3, n4, n5), 1);
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
                this.F.eval(c(" Cp\u0002R)Po\u0013UpVi\u0013\u000f=Kh\u0007N=\fe\u000fN9G.J"));
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public void b(final String s) {
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
    
    public j o() {
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
    
    public bq r() {
        return null;
    }
    
    static by a(final TWViewer twViewer) {
        return twViewer.c;
    }
    
    static g b(final TWViewer twViewer) {
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
    
    static bt f(final TWViewer twViewer) {
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
    
    static void h(final TWViewer twViewer) {
        twViewer.b();
    }
    
    static void i(final TWViewer twViewer) {
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
                            c2 = 'J';
                            break;
                        }
                        case 1: {
                            c2 = '\"';
                            break;
                        }
                        case 2: {
                            c2 = '\u0006';
                            break;
                        }
                        case 3: {
                            c2 = 'c';
                            break;
                        }
                        default: {
                            c2 = '!';
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
