import java.awt.Rectangle;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.Event;
import java.util.Enumeration;
import java.awt.Color;
import java.applet.Applet;
import java.awt.Cursor;
import java.awt.Label;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class C44 extends Thread
{
    boolean s;
    boolean t;
    long u;
    C28 v;
    Vector w;
    Label x;
    C28 y;
    boolean z;
    Cursor A;
    Applet B;
    C39 C;
    Cursor D;
    C49 E;
    Cursor F;
    boolean G;
    boolean H;
    Cursor I;
    Vector J;
    boolean K;
    Vector L;
    
    public void d() {
        if (this.C != null) {
            this.C.c();
        }
    }
    
    public C44(final Applet b, final Label x, final Vector w, final C49 e) {
        super("URLThread size=" + w.size());
        this.H = true;
        this.G = false;
        this.s = false;
        this.t = false;
        this.z = false;
        this.C = null;
        this.F = null;
        this.v = null;
        this.D = new Cursor(12);
        this.I = new Cursor(13);
        this.A = new Cursor(0);
        this.K = false;
        this.B = b;
        this.x = x;
        this.J = new Vector();
        this.L = new Vector();
        this.w = w;
        this.E = e;
        this.start();
    }
    
    public synchronized void g() {
        this.G = true;
        this.notify();
    }
    
    private void h(final C15 c15, final C35 c16) {
        if (((C28)c16).e() != null) {
            return;
        }
        c15.D(Color.red);
        final boolean d = c16.d();
        c16.e(true);
        this.E.J(c15, c16);
        c16.e(d);
    }
    
    public synchronized void i() {
        this.H = true;
    }
    
    public void k() {
        if (this.C != null) {
            this.C.a();
        }
    }
    
    public Vector l() {
        return this.w;
    }
    
    public void m(String s) {
        int n = 0;
        int n2 = 0;
        s = s.trim();
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == ' ') {
                ++n2;
            }
        }
        final String[] array = new String[++n2];
        if (s.indexOf(", ") < 0) {
            final Enumeration<C28> elements = this.w.elements();
            while (elements.hasMoreElements()) {
                final C28 c28 = elements.nextElement();
                final C25 e = c28.e();
                if (e != null && e.h() != null && s.trim().equals(e.h().trim())) {
                    this.E.E(c28.p());
                    c28.e().o = true;
                    this.E.q();
                }
            }
        }
        else {
            final boolean b = true;
            while (b) {
                if (s.indexOf(", ") <= -1) {
                    array[n] = s;
                    break;
                }
                array[n] = s.substring(0, s.indexOf(", "));
                s = s.substring(s.indexOf(", ") + 1);
                ++n;
            }
            final Enumeration<C28> elements2 = this.w.elements();
            final C28[] array2 = new C28[n2];
            int n3 = 0;
            while (elements2.hasMoreElements()) {
                final C28 c29 = elements2.nextElement();
                final C25 e2 = c29.e();
                if (e2 != null && e2.h() != null) {
                    for (int j = 0; j < n2; ++j) {
                        if (array[j].trim().equals(e2.h().trim())) {
                            array2[n3] = c29;
                            ++n3;
                            c29.e().o = true;
                        }
                    }
                }
            }
            final int n4 = n3;
            int p = array2[0].P;
            for (int k = 1; k < n4; ++k) {
                if (array2[k].P < p) {
                    p = array2[k].P - array2[k].H / 2;
                }
            }
            int b2 = array2[0].B;
            for (int l = 1; l < n4; ++l) {
                if (array2[l].B > b2) {
                    b2 = array2[l].B + array2[l].J + array2[l].J / 2;
                }
            }
            int p2 = array2[0].P;
            for (int n5 = 1; n5 < n4; ++n5) {
                if (array2[n5].P > p2) {
                    p2 = array2[n5].P + array2[n5].H + array2[n5].H / 2;
                }
            }
            int b3 = array2[0].B;
            for (int n6 = 1; n6 < n4; ++n6) {
                if (array2[n6].B < b3) {
                    b3 = array2[n6].B - array2[n6].J / 2;
                }
            }
            if (p == array2[0].P) {
                p = array2[0].P - array2[0].H / 2;
            }
            if (b2 == array2[0].B) {
                b2 = array2[0].B + array2[0].J + array2[0].J / 2;
            }
            if (p2 == array2[0].P) {
                p2 = array2[0].P + array2[0].H + array2[0].H / 2;
            }
            if (b3 == array2[0].B) {
                b3 = array2[0].B - array2[0].J / 2;
            }
            this.E.g(new C17(p, b2, p2, b3, ""));
        }
    }
    
    public void run() {
        this.i();
        while (!this.G) {
            try {
                Thread.sleep(600L);
            }
            catch (InterruptedException ex) {}
            if (this.H && !this.s) {
                synchronized (this) {
                    try {
                        this.wait();
                    }
                    catch (InterruptedException ex2) {}
                }
            }
            if (this.G) {
                continue;
            }
            int n = 0;
            for (int i = 0; i < this.L.size(); ++i) {
                if (((C28)this.L.elementAt(i)).k()) {
                    n = 1;
                    break;
                }
            }
            if (n == 0) {
                for (int j = 0; j < this.w.size(); ++j) {
                    if (((C28)this.w.elementAt(j)).k()) {
                        n = 1;
                        break;
                    }
                }
            }
            if (n != 0 || this.G) {
                continue;
            }
            System.out.println("There are no URLs in the layers currently selected.");
            this.H = true;
        }
        System.gc();
    }
    
    public void n(final C06 c06) {
        this.J.addElement(c06);
    }
    
    public boolean o(final Event event) {
        this.E.setCursor(this.I);
        if (this.E.cl == 0 || this.E.V.a()) {
            this.E.setCursor(this.A);
        }
        C28 c28 = this.q(this.w.elements(), event);
        if (c28 == null) {
            c28 = this.q(this.L.elements(), event);
            if (c28 == null) {
                if (this.x != null) {
                    this.x.setText("");
                }
                if (this.B != null) {
                    this.B.showStatus("");
                }
            }
        }
        if (this.y != null && c28 != this.y) {
            final Graphics graphics = this.E.getGraphics();
            if (graphics != null) {
                this.h(new C15(this.E, graphics, this.E.size().width, this.E.size().height), this.y);
            }
            this.y = null;
        }
        if (event.id == 502 && (event.modifiers & 0x4) != 0x0 && this.E.V.a()) {
            this.E.V.b();
        }
        return false;
    }
    
    private C28 q(final Enumeration enumeration, final Event event) {
        final Point point = new Point(event.x - 5, event.y + 5);
        final Point point2 = new Point(event.x + 5, event.y - 5);
        final Point i = this.E.I(point);
        final Point j = this.E.I(point2);
        final Rectangle rectangle = new Rectangle(i.x, i.y, j.x - i.x, Math.abs(j.y - i.y));
        while (enumeration.hasMoreElements()) {
            final C35 c35 = enumeration.nextElement();
            if (c35 instanceof C28) {
                final C28 y = (C28)c35;
                final C25 e = y.e();
                if (y.P + y.H > rectangle.x && y.B + y.J > rectangle.y && y.P < rectangle.x + rectangle.width && y.B < rectangle.y + rectangle.height) {
                    if (this.H && this.y == null) {
                        this.y = y;
                        final Graphics graphics = this.E.getGraphics();
                        if (graphics != null) {
                            this.h(new C15(this.E, graphics, this.E.size().width, this.E.size().height), y);
                        }
                    }
                    if (((C28)c35).e() != null) {
                        if (this.E.by == -1 && this.E.cl == 1) {
                            if (!this.t) {
                                this.E.setCursor(this.D);
                            }
                            else if (y.e().g().toString().compareTo(C25.p.toString()) != 0) {
                                this.E.setCursor(this.D);
                            }
                        }
                        if (!this.t) {
                            this.E.setCursor(this.I);
                            if (this.z) {
                                this.E.setCursor(this.D);
                                if (event.id == 501 && this.E.cl == 1 && !this.E.V.a() && this.E.by == -1) {
                                    this.E.bu = e.h().trim();
                                    this.E.cf.P.ZoomBooth(this.E.bu);
                                }
                            }
                        }
                        if (this.B != null && this.E.by == -1 && this.E.cl == 1 && this.t) {
                            if (y.e().g().toString().compareTo(C25.p.toString()) != 0) {
                                this.B.showStatus(y.e().g() + "");
                            }
                            else {
                                this.B.showStatus("");
                            }
                        }
                        if (this.x != null) {
                            this.x.setText(y.e().g() + "");
                        }
                        if (event.id == 501 && System.currentTimeMillis() - this.u < 350L) {
                            this.u = 0L;
                        }
                        if (event.id == 501) {
                            this.u = System.currentTimeMillis();
                            if (this.t && y.e().g().toString().compareTo(C25.p.toString()) != 0 && this.B != null) {
                                this.E.cf.P.OpenURL(y.e().g().toString());
                            }
                        }
                    }
                    return y;
                }
                continue;
            }
        }
        return null;
    }
}
