import java.awt.Polygon;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import java.awt.event.KeyListener;
import java.util.Hashtable;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Font;
import java.util.Vector;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class transient extends Panel implements Runnable
{
    Image T;
    Graphics U;
    Dimension size;
    Thread Sa;
    Vector Ta;
    private Font Ua;
    Rectangle Va;
    Rectangle ya;
    Color Wa;
    Color Xa;
    Color Ya;
    Color Za;
    Color _b;
    boolean ab;
    boolean bb;
    boolean va;
    int cb;
    int db;
    int eb;
    String[] fb;
    AudioClip gb;
    e[] hb;
    super ib;
    e jb;
    private static String o = "\ue677\ue644\ue65f\ue657\ue65a";
    private static String p = "\ue650\ue65f\ue653\ue65a\ue652\ue675\ue659\ue65a\ue659\ue644";
    private static String q = "\ue654\ue657\ue655\ue65d\ue675\ue659\ue65a\ue659\ue644";
    private static String C = "\ue645\ue65e\ue657\ue646\ue653\ue675\ue659\ue65a\ue659\ue644";
    private static String D = "\ue642\ue653\ue64e\ue642\ue675\ue659\ue65a\ue659\ue644";
    private static String E = "\ue646\ue65f\ue653\ue655\ue653\ue675\ue659\ue65a\ue659\ue644";
    private static String F = "\ue654\ue659\ue644\ue652\ue653\ue644\ue675\ue659\ue65a\ue659\ue644";
    private static String ta = "\ue655\ue643\ue644\ue644\ue653\ue658\ue642\ue616\ue642\ue657\ue658\ue651\ue644\ue657\ue65b\ue616\ue65f\ue658\ue652\ue653\ue64e\ue60c\ue616";
    
    public transient(final Hashtable hashtable, final super ib, final String[] fb, final boolean ab, final AudioClip gb) {
        this.Ua = new Font(transient.o, 0, 14);
        this.db = -1;
        this.ab = ab;
        this.fb = fb;
        this.gb = gb;
        this.Wa = hashtable.get(transient.p);
        this.Za = hashtable.get(transient.q);
        this.Ya = hashtable.get(transient.C);
        this._b = hashtable.get(transient.D);
        final e[] a = this.a(hashtable.get(transient.E), hashtable.get(transient.F));
        this.Ta = new Vector();
        for (int i = 0; i < a.length; ++i) {
            this.Ta.addElement(a[i]);
        }
        this.cb = this.Ta.size();
        this.ib = ib;
        this.enableEvents(48L);
        this.addKeyListener(new f(this));
        this.requestFocus();
    }
    
    public Dimension getPreferredSize() {
        return this.getMinimumSize();
    }
    
    public synchronized Dimension getMinimumSize() {
        return new Dimension(659, 430);
    }
    
    private void a(final int n, int n2, final int n3) {
        this.U.setColor(this.Za);
        for (int i = 0; i < n3; i += 6) {
            this.U.drawLine(n, n2, n, n2 + 2);
            this.U.drawLine(n + 1, n2, n + 1, n2 + 2);
            n2 += 6;
        }
    }
    
    public void update(final Graphics graphics) {
        this.U.setColor(this.Wa);
        this.U.fillRect(0, 0, this.size.width, this.size.height);
        this.a(this.Va.width, 0, this.size.height);
        if (this.va) {
            this.h();
        }
        else if (this.hb != null && !this.ab) {
            this.U.setColor(this.Ya);
            for (int i = 0; i < this.hb.length; ++i) {
                this.U.fillPolygon(this.hb[i].b());
            }
        }
        for (int j = 0; j < this.cb; ++j) {
            ((e)this.Ta.elementAt(j)).a(this.U);
        }
        graphics.drawImage(this.T, 0, 0, this);
    }
    
    private void h() {
        this.U.setColor(this._b);
        this.U.setFont(this.Ua);
        for (int i = 0; i < this.fb.length; ++i) {
            this.U.drawString(this.fb[i], this.Va.x + 10, 20 + i * 20);
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.T == null) {
            this.size = this.getSize();
            this.Va = new Rectangle(0, 0, (int)(this.size.width * 0.75), this.size.height);
            this.ya = new Rectangle(this.Va.width, 0, this.size.width - this.Va.width, this.size.height);
            this.T = this.createImage(this.size.width, this.size.height);
            this.U = this.T.getGraphics();
            this.i();
            if (!this.ab) {
                this.d();
            }
        }
        this.update(graphics);
    }
    
    private void a(final KeyEvent keyEvent, final int n) {
        if (!keyEvent.isMetaDown() && n == 32) {
            for (int i = 0; i < this.cb; ++i) {
                final e e = this.Ta.elementAt(i);
                if (e.getID() == 3) {
                    e.a();
                    break;
                }
            }
            this.repaint();
            return;
        }
        if (n == 83 && this.ab) {
            this.j();
            for (int j = 0; j < this.cb; ++j) {
                System.out.println(((e)this.Ta.elementAt(j)).a());
            }
            System.out.println();
            this.repaint();
        }
    }
    
    private void i() {
        final e[] array = new e[this.cb];
        this.Ta.copyInto(array);
        Rectangle rectangle = null;
        for (int i = 0; i < this.cb; ++i) {
            if (rectangle == null) {
                rectangle = array[i].getBounds();
            }
            else {
                rectangle = rectangle.union(array[i].getBounds());
            }
        }
        final int n = this.ya.x + (this.ya.width - rectangle.width) / 2 - rectangle.x;
        final int n2 = this.ya.y + (this.ya.height - rectangle.height) / 2 - rectangle.y;
        for (int j = 0; j < array.length; ++j) {
            array[j].a(n, n2);
        }
    }
    
    private void j() {
        final e[] array = new e[this.cb];
        this.Ta.copyInto(array);
        this._(array);
    }
    
    private void k() {
        for (int i = 0; i < this.hb.length; ++i) {
            this.hb[i]._(true);
        }
        this._(this.hb);
    }
    
    private void _(final e[] array) {
        Rectangle rectangle = null;
        for (int i = 0; i < array.length; ++i) {
            if (array[i]._()) {
                if (rectangle == null) {
                    rectangle = array[i].getBounds();
                }
                else {
                    rectangle = rectangle.union(array[i].getBounds());
                }
            }
        }
        final int n = (this.Va.width - rectangle.width) / 2 - rectangle.x;
        final int n2 = (this.Va.height - rectangle.height) / 2 - rectangle.y;
        for (int j = 0; j < array.length; ++j) {
            if (array[j]._()) {
                array[j].move(n, n2);
            }
        }
    }
    
    public void processMouseEvent(final MouseEvent mouseEvent) {
        switch (mouseEvent.getID()) {
            case 501: {
                this._(mouseEvent, mouseEvent.getX(), mouseEvent.getY());
            }
            case 502: {
                if (this.Sa != null) {
                    this.Sa = null;
                    System.gc();
                    return;
                }
                break;
            }
            default: {
                mouseEvent.consume();
                break;
            }
        }
    }
    
    public void stop() {
        if (this.Sa != null && this.Sa.isAlive()) {
            this.Sa.stop();
        }
        this.Sa = null;
    }
    
    public void run() {
        final Thread currentThread = Thread.currentThread();
        int n = 0;
        while (currentThread == this.Sa && this.jb != null) {
            if (n == 0) {
                final Point b = this.jb.b();
                this.jb._(b.x, b.y);
                n = 1;
                this.repaint();
            }
            this.jb._();
            this.repaint();
            try {
                Thread.sleep(400L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    private void _(final MouseEvent mouseEvent, final int n, final int n2) {
        if (this.jb == null) {
            for (int i = 0; i < this.cb; ++i) {
                final e jb = this.Ta.elementAt(i);
                if (jb.inside(n, n2)) {
                    if (i < this.cb - 1) {
                        this.Ta.removeElementAt(i);
                        this.Ta.addElement(jb);
                    }
                    this.jb = jb;
                    final Point b = this.jb.b();
                    this.jb._(b.x, b.y);
                    this.requestFocus();
                    this.repaint();
                    break;
                }
            }
            if (this.jb != null && mouseEvent.isMetaDown()) {
                (this.Sa = new Thread(this)).start();
            }
        }
        else {
            if (mouseEvent.isMetaDown()) {
                (this.Sa = new Thread(this)).start();
            }
            else if (this.Va.contains(n, n2)) {
                if (!this.ab) {
                    this.l();
                }
                else {
                    this.m();
                }
                this.jb._(true);
                this.jb = null;
            }
            else {
                this.jb.b();
                this.jb = null;
            }
            this.repaint();
        }
    }
    
    public void processMouseMotionEvent(final MouseEvent mouseEvent) {
        switch (mouseEvent.getID()) {
            case 503:
            case 506: {
                this.g(mouseEvent.getX(), mouseEvent.getY());
            }
            default: {
                mouseEvent.consume();
            }
        }
    }
    
    private void g(final int n, final int n2) {
        if (this.jb != null) {
            this.jb.b(n, n2);
            this.repaint();
        }
    }
    
    public void c() {
        for (int i = 0; i < this.cb; ++i) {
            ((e)this.Ta.elementAt(i)).b();
        }
    }
    
    public void e() {
        --this.db;
        if (this.db < 0) {
            this.db = this.ib._(this.eb) - 1;
        }
        this.hb = this.ib._(this.db, this.eb);
        this.k();
        if (this.bb) {
            System.out.println(transient.ta + this.db);
        }
        this.c();
        this.repaint();
    }
    
    public void d() {
        ++this.db;
        if (this.db >= this.ib._(this.eb)) {
            this.db = 0;
        }
        this.hb = this.ib._(this.db, this.eb);
        this.k();
        if (this.bb) {
            System.out.println(transient.ta + this.db);
        }
        this.c();
        this.repaint();
    }
    
    public int b() {
        return this.db;
    }
    
    public void f() {
        ++this.eb;
        if (this.eb >= this.ib.m()) {
            this.eb = 0;
        }
        this.db = -1;
        this.d();
        this.c();
        this.repaint();
    }
    
    public e[] a(final Color color, final Color color2) {
        e.tb = 164;
        return new e[] { this.b(551, 115, 7, false, 0, color, color2), this.b(591, 155, 3, false, 0, color, color2), this.b(572, 212, 3, false, 1, color, color2), this.b(609, 231, 7, false, 2, color, color2), this.b(533, 289, 1, false, 2, color, color2), this.b(542, 252, 5, false, 3, color, color2), this.b(600, 280, 1, false, 4, color, color2) };
    }
    
    private e b(final int n, final int n2, final int n3, final boolean b, final int n4, final Color color, final Color color2) {
        return new e(color, color2, n4, n, n2, n3, b);
    }
    
    private void l() {
        if (this.hb != null) {
            final int n = 7;
            final Polygon b = this.jb.b();
            for (int i = 0; i < this.hb.length; ++i) {
                final Polygon b2 = this.hb[i].b();
                for (int j = 0; j < b2.npoints; ++j) {
                    for (int k = 0; k < b.npoints; ++k) {
                        final int n2 = b2.xpoints[j] - b.xpoints[k];
                        if (Math.abs(n2) <= n) {
                            final int n3 = b2.ypoints[j] - b.ypoints[k];
                            if (Math.abs(n3) <= n) {
                                final Point b3 = this.jb.b();
                                this.jb._(b3.x, b3.y);
                                this.jb.b(b3.x + n2, b3.y + n3);
                                switch.a(this.gb);
                                return;
                            }
                        }
                    }
                }
            }
        }
    }
    
    private void m() {
        final int n = 7;
        final Polygon b = this.jb.b();
        for (int i = 0; i < this.Ta.size(); ++i) {
            final e e = this.Ta.elementAt(i);
            if (e != this.jb) {
                final Polygon b2 = e.b();
                for (int j = 0; j < b2.npoints; ++j) {
                    for (int k = 0; k < b.npoints; ++k) {
                        final int n2 = b2.xpoints[j] - b.xpoints[k];
                        if (Math.abs(n2) <= n) {
                            final int n3 = b2.ypoints[j] - b.ypoints[k];
                            if (Math.abs(n3) <= n) {
                                final Point b3 = this.jb.b();
                                this.jb._(b3.x, b3.y);
                                this.jb.b(b3.x + n2, b3.y + n3);
                                switch.a(this.gb);
                                return;
                            }
                        }
                    }
                }
            }
        }
    }
    
    public void g() {
        final Vector<e> vector = new Vector<e>();
        for (int i = 0; i < this.hb.length; ++i) {
            vector.addElement(this.hb[i]);
        }
        for (int j = 0; j < this.Ta.size(); ++j) {
            final e e = this.Ta.elementAt(j);
            for (int k = 0; k < vector.size(); ++k) {
                final e e2 = vector.elementAt(k);
                if (e.getID() == e2.getID()) {
                    e.b(e2.b());
                    vector.removeElementAt(k);
                    --k;
                    break;
                }
            }
        }
    }
    
    public void n() {
        switch.b(this.Sa);
    }
    
    static void a(final transient transient1, final KeyEvent keyEvent, final int n) {
        transient1.a(keyEvent, n);
    }
    
    private static String _(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0xFFFEE636);
        }
        return new String(array);
    }
    
    static {
        transient.o = _(transient.o);
        transient.p = _(transient.p);
        transient.q = _(transient.q);
        transient.C = _(transient.C);
        transient.D = _(transient.D);
        transient.E = _(transient.E);
        transient.F = _(transient.F);
        transient.ta = _(transient.ta);
    }
}
