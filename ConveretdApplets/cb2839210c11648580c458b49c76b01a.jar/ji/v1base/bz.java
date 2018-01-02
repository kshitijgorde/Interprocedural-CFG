// 
// Decompiled by Procyon v0.5.30
// 

package ji.v1base;

import java.awt.event.MouseAdapter;
import ji.v1event.d8;
import ji.v1event.c9;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import ji.util.d;
import java.awt.Component;
import ji.util.e;
import ji.v1event.g9;
import ji.v1event.bp;
import java.awt.Image;
import ji.awt.c;
import ji.v1event.br;
import ji.awt.bb;
import java.awt.Insets;
import ji.graphic.b3;
import ji.v1event.as;
import java.awt.event.KeyListener;
import ji.v1event.ar;
import java.awt.Canvas;

public class bz extends Canvas implements ar, KeyListener, as
{
    private b3 a;
    private boolean b;
    private Insets c;
    private boolean d;
    private boolean e;
    private bb f;
    private bb g;
    private boolean h;
    private br i;
    private int j;
    private c k;
    private c l;
    private c m;
    private s9 n;
    private c o;
    private c p;
    private c q;
    private boolean r;
    private boolean s;
    private Image t;
    private int u;
    private int v;
    private String w;
    private String x;
    private int y;
    private static int z;
    private String aa;
    private boolean ab;
    private bp ac;
    private String ad;
    private boolean ae;
    private String af;
    private static Object ag;
    
    public bz(final String s) {
        this.a = new b3();
        this.b = false;
        this.c = null;
        this.d = false;
        this.e = false;
        this.f = null;
        this.g = null;
        this.h = false;
        this.i = null;
        this.j = 1;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = null;
        this.p = null;
        this.q = null;
        this.r = false;
        this.s = true;
        this.t = null;
        this.u = 0;
        this.v = 0;
        this.w = null;
        this.x = null;
        this.y = 0;
        this.aa = null;
        this.ab = false;
        this.ac = null;
        this.ad = null;
        this.ae = true;
        this.af = null;
        ++bz.z;
        this.aa = "".concat(String.valueOf(String.valueOf(bz.z)));
        this.d(s);
    }
    
    public void a(final boolean ae) {
        this.ae = ae;
    }
    
    public void a(final String ad) {
        this.ad = ad;
    }
    
    public String getFocusRingId() {
        return this.ad;
    }
    
    public void mouseWheelMoved(final g9 g9) {
        this.a(g9);
    }
    
    public void setId(final String aa) {
        this.aa = aa;
    }
    
    public String getId() {
        return this.aa;
    }
    
    public boolean a() {
        return false;
    }
    
    public void b(final boolean b) {
    }
    
    private void d(final String af) {
        try {
            this.af = af;
            this.b(true);
            ji.util.e.a(this);
            ji.util.e.a(af, this);
            ji.util.d.a(af, this);
            ji.util.d.c(this);
            this.addKeyListener(this);
            this.d(true);
        }
        catch (Exception ex) {}
    }
    
    private final void h() {
        if (this.i == null) {
            this.addMouseMotionListener(this.i = new br(this.af, this));
            this.addMouseListener(this.i);
        }
    }
    
    public void b() {
        this.removeKeyListener(this);
    }
    
    public void setEnabled(final boolean s) {
        if (this.s != s) {
            super.setEnabled(this.s = s);
            if (!s && ji.util.e.c(this.af, this)) {
                ji.util.e.n(this.af);
            }
        }
    }
    
    public void b(final String x) {
        this.x = x;
    }
    
    public String c(final String s) {
        if (this.x == null) {
            this.x = ji.util.d.e(this.w, s);
        }
        return this.x;
    }
    
    public Insets c() {
        if (this.c == null) {
            return this.a.a();
        }
        return this.c;
    }
    
    public void setBorderStyle(final int n) {
        try {
            if (n != this.a.b()) {
                this.a.c(this, this.getGraphics());
                this.a.a(n);
                this.repaint();
            }
        }
        catch (Exception ex) {}
    }
    
    public Color d() {
        return this.a.g();
    }
    
    public void a(final Color color) {
        this.a.c(color);
    }
    
    public Color e() {
        return this.a.h();
    }
    
    public void f() {
        try {
            if (this.ae) {
                this.a.a(this, this.getGraphics());
            }
        }
        catch (Exception ex) {}
    }
    
    public Rectangle g() {
        return this.a.b(this);
    }
    
    public boolean isVisible() {
        return super.isVisible();
    }
    
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        this.validate();
    }
    
    public void setLocation(final int n, final int n2) {
        try {
            final Point location = this.getLocation();
            if (location.x != n || location.y != n2) {
                super.setLocation(n, n2);
            }
        }
        catch (Exception ex) {}
    }
    
    public void paint(final Graphics graphics) {
        if (this.a()) {
            super.paint(graphics);
        }
        else {
            this.paintComponent(graphics);
        }
    }
    
    public void setToolTipText(final String s) {
    }
    
    public void paintComponent(final Graphics graphics) {
        if (!this.b) {
            try {
                this.a.d(this, graphics);
                if (this.t != null) {
                    final Insets c = this.c();
                    graphics.drawImage(this.t, c.left, c.top, this.u, this.v, null);
                }
            }
            finally {
                this.b = false;
            }
        }
    }
    
    public void update(final Graphics graphics) {
        try {
            if (this.a()) {
                super.update(graphics);
            }
            else {
                if (this.ae) {
                    this.a.a(this, graphics);
                }
                this.paintComponent(graphics);
            }
        }
        catch (Exception ex) {}
    }
    
    public void c(final boolean ab) {
        this.ab = ab;
    }
    
    public boolean ignoreTAB() {
        return this.ab;
    }
    
    public void d(final boolean d) {
        this.d = d;
        ji.util.e.a(this.af, this, d);
    }
    
    public boolean acceptFocus() {
        return this.d;
    }
    
    public void releaseResources() {
        if (!this.e) {
            this.e = true;
            this.w = null;
            try {
                if (this.t != null) {
                    this.t.flush();
                    this.t = null;
                }
            }
            catch (Exception ex) {
                ji.util.d.a(ex);
            }
            try {
                if (this.ac != null) {
                    this.ac.a(this);
                    this.ac = null;
                }
            }
            catch (Exception ex2) {
                ji.util.d.a(ex2);
            }
            try {
                this.e(false);
                this.removeKeyListener(this);
                ji.util.e.b(this.af, this);
                ji.util.d.b(this.af, this);
                ji.util.d.d(this);
                if (this.i != null) {
                    this.removeMouseListener(this.i);
                    this.removeMouseMotionListener(this.i);
                    this.i.a();
                    this.i = null;
                }
                if (this.p != null) {
                    this.p.c();
                }
                if (this.m != null) {
                    this.m.c();
                }
                if (this.o != null) {
                    this.o.c();
                }
                if (this.k != null) {
                    this.k.c();
                }
                if (this.l != null) {
                    this.l.c();
                }
                if (this.q != null) {
                    this.q.c();
                }
                ji.util.e.a(this.af, this, false);
            }
            catch (Exception ex3) {
                ji.util.d.a(ex3);
            }
        }
    }
    
    public void finalize() {
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    public void e(final boolean r) {
        if (ji.util.d.dq()) {
            this.i();
            this.r = r;
            if (r) {
                if (this.n == null) {
                    this.n = new s9();
                }
                super.addMouseListener(this.n);
                super.addMouseMotionListener(this.n);
            }
            else if (this.n != null) {
                super.removeMouseListener(this.n);
                super.removeMouseMotionListener(this.n);
                this.n = null;
            }
        }
    }
    
    protected final void a(final MouseEvent mouseEvent) {
        if (this.k != null) {
            for (int i = 0; i < this.k.b(); ++i) {
                ((MouseListener)this.k.b(i)).mouseReleased(mouseEvent);
            }
        }
    }
    
    public void addMouseListener(final MouseListener mouseListener) {
        super.addMouseListener(mouseListener);
        if (this.r) {
            if (this.k == null) {
                this.k = new c("jiCanvas1", 2);
            }
            if (!this.k.a(mouseListener)) {
                this.k.c(mouseListener);
            }
        }
    }
    
    public void removeMouseListener(final MouseListener mouseListener) {
        super.removeMouseListener(mouseListener);
        if (this.k != null && this.k.a(mouseListener)) {
            this.k.b(mouseListener);
        }
    }
    
    protected final void a(final g9 g9) {
        synchronized (bz.ag) {
            if (this.q != null) {
                for (int i = 0; i < this.q.b(); ++i) {
                    ((as)this.q.b(i)).mouseWheelMoved(g9);
                }
            }
        }
        // monitorexit(bz.ag)
    }
    
    public void a(final c9 c9) {
        this.h();
        if (this.p == null) {
            this.p = new c("jiCanvas2", 2);
        }
        if (!this.p.a(c9)) {
            this.p.c(c9);
        }
    }
    
    public void b(final c9 c9) {
        if (this.p != null && this.p.a(c9)) {
            this.p.b(c9);
        }
    }
    
    public void a(final d8 d8) {
        if (this.p != null) {
            for (int i = 0; i < this.p.b(); ++i) {
                if (!d8.f()) {
                    ((c9)this.p.b(i)).a(d8);
                }
            }
        }
    }
    
    private void b(final MouseEvent mouseEvent) {
        try {
            if (this.g == null) {
                this.h = false;
                (this.g = new bb(this.af, new zy(this, mouseEvent))).start();
            }
        }
        catch (Exception ex) {}
    }
    
    private void i() {
        this.h = true;
    }
    
    static {
        bz.z = 0;
        bz.ag = new Object();
    }
    
    class s9 extends MouseAdapter implements MouseMotionListener
    {
        public void mouseEntered(final MouseEvent mouseEvent) {
        }
        
        public void mouseClicked(final MouseEvent mouseEvent) {
        }
        
        public void mouseReleased(final MouseEvent mouseEvent) {
            bz.this.i();
        }
        
        public void mouseExited(final MouseEvent mouseEvent) {
            bz.this.i();
        }
        
        public void mousePressed(final MouseEvent mouseEvent) {
            bz.this.b(mouseEvent);
        }
        
        public void mouseDragged(final MouseEvent mouseEvent) {
            bz.this.i();
        }
        
        public void mouseMoved(final MouseEvent mouseEvent) {
        }
    }
    
    class zy implements Runnable
    {
        Component a;
        MouseEvent b;
        
        public zy(final Component a, final MouseEvent b) {
            this.a = null;
            this.b = null;
            this.a = a;
            this.b = b;
        }
        
        public final void run() {
            int n;
            int n2;
            for (n = 0, n2 = bz.this.j * 7; !bz.this.h && n < n2; ++n) {
                ji.util.d.b(100, 47, bz.this.af);
            }
            if (n >= n2 && !bz.this.h) {
                final Point point = this.b.getPoint();
                bz.this.a(new MouseEvent(this.a, 502, 0L, 8, point.x, point.y, 1, true));
            }
            bz.this.g = null;
            this.a = null;
            this.b = null;
        }
    }
}
