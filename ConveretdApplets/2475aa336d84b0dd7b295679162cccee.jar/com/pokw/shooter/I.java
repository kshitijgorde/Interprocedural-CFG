// 
// Decompiled by Procyon v0.5.30
// 

package com.pokw.shooter;

import java.awt.image.ImageObserver;
import java.awt.Graphics2D;
import java.awt.Graphics;
import com.pokw.shooter.planes.c;
import java.awt.geom.Rectangle2D;
import com.pokw.shooter.weapon.m;
import com.pokw.shooter.planes.d;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.util.Random;
import com.pokw.shooter.planes.b;
import java.util.Vector;
import java.awt.Color;
import javax.swing.JComponent;

abstract class I extends JComponent implements Runnable
{
    private static final Color q;
    private final int d = 600;
    private final int b = 500;
    private final int j = 20;
    private final int p = 480;
    private final int z = 100;
    private final int k = 10;
    private final int e = 130;
    private final int h = 480;
    private final int s = 20;
    private final int t = 475;
    private Vector C;
    private Vector A;
    private Vector D;
    private Vector w;
    private b E;
    private Random r;
    private KeyEvent c;
    private boolean F;
    private boolean u;
    private boolean f;
    private boolean G;
    private boolean x;
    private com.pokw.shooter.levels.b a;
    private g i;
    private int v;
    private int B;
    private int l;
    private boolean m;
    private boolean y;
    private int g;
    private Dimension n;
    
    public I() {
        this.C = new Vector();
        this.A = new Vector();
        this.D = new Vector();
        this.w = new Vector();
        this.E = new b(300.0, 400.0);
        this.r = new Random();
        this.c = null;
        this.F = false;
        this.u = false;
        this.f = false;
        this.G = false;
        this.x = false;
        this.a = new com.pokw.shooter.levels.b();
        this.i = new g();
        this.v = 2;
        this.B = this.v;
        this.l = 0;
        this.m = true;
        this.y = true;
        this.g = 0;
        this.n = new Dimension(600, 500);
        this.addMouseListener(new MouseAdapter() {
            private final I a = a;
            
            public void mouseClicked(final MouseEvent mouseEvent) {
                if (!this.a.hasFocus()) {
                    this.a.requestFocus();
                }
            }
        });
        this.addKeyListener(new KeyListener() {
            private final I a = a;
            
            public void keyTyped(final KeyEvent keyEvent) {
            }
            
            public void keyPressed(final KeyEvent keyEvent) {
                switch (keyEvent.getKeyCode()) {
                    case 37: {
                        I.d(this.a, true);
                        break;
                    }
                    case 39: {
                        I.e(this.a, true);
                        break;
                    }
                    case 40: {
                        I.a(this.a, true);
                        break;
                    }
                    case 38: {
                        I.c(this.a, true);
                        break;
                    }
                    case 17: {
                        I.b(this.a, true);
                        break;
                    }
                    case 16: {
                        I.a(this.a).o();
                        break;
                    }
                    case 80: {
                        I.b(this.a);
                        break;
                    }
                }
            }
            
            public void keyReleased(final KeyEvent keyEvent) {
                switch (keyEvent.getKeyCode()) {
                    case 37: {
                        I.d(this.a, false);
                        break;
                    }
                    case 39: {
                        I.e(this.a, false);
                        break;
                    }
                    case 40: {
                        I.a(this.a, false);
                        break;
                    }
                    case 38: {
                        I.c(this.a, false);
                        break;
                    }
                    case 17: {
                        I.b(this.a, false);
                        break;
                    }
                }
            }
        });
        this.a = new com.pokw.shooter.levels.b();
        new Thread(this).start();
    }
    
    public void run() {
        while (this.m) {
            final long currentTimeMillis = System.currentTimeMillis();
            this.a.a(this.C);
            this.g();
            this.f();
            this.d();
            this.b();
            this.repaint();
            final long currentTimeMillis2 = System.currentTimeMillis();
            try {
                if (this.y) {
                    synchronized (this) {
                        this.wait();
                        continue;
                    }
                }
                Thread.sleep(Math.max(30L - (currentTimeMillis2 - currentTimeMillis), 1L));
            }
            catch (InterruptedException ex) {
                com.pokw.shooter.n.a(ex);
            }
        }
    }
    
    public Dimension getMinimumSize() {
        return this.n;
    }
    
    public Dimension getPreferredSize() {
        return this.n;
    }
    
    public Dimension getMaximumSize() {
        return this.n;
    }
    
    public synchronized void c() {
        if (!this.y) {
            this.y = true;
        }
    }
    
    public synchronized void e() {
        if (this.y) {
            this.y = false;
            this.notify();
        }
    }
    
    public void a() {
        this.c();
        this.B = this.v;
        this.l = 0;
        this.E = new b(300.0, 400.0);
        this.A.removeAllElements();
        this.C.removeAllElements();
        this.D.removeAllElements();
        this.F = false;
        this.u = false;
        this.f = false;
        this.G = false;
        this.x = false;
        this.a.a();
        this.repaint();
    }
    
    public abstract void a(final int p0);
    
    private synchronized void h() {
        if (!(this.y = !this.y)) {
            this.notify();
        }
    }
    
    private void g() {
        double n = 0.0;
        double n2 = 0.0;
        if (this.F) {
            n -= 6.0;
        }
        if (this.u) {
            n += 6.0;
        }
        if (this.f) {
            n2 -= 6.0;
        }
        if (this.G) {
            n2 += 6.0;
        }
        this.E.d(n);
        this.E.c(n2);
        this.E.a(600, 500);
        if (this.x) {
            this.E.a(this.A);
        }
    }
    
    private void f() {
        for (int i = 0; i < this.C.size(); ++i) {
            final d d = this.C.get(i);
            d.a(this.D);
            if (d.i() || !this.a(d.a())) {
                this.C.remove(i);
                --i;
            }
        }
    }
    
    private void d() {
        for (int i = 0; i < this.A.size(); ++i) {
            final com.pokw.shooter.weapon.m m = this.A.elementAt(i);
            m.c();
            final Rectangle2D a = m.a();
            if (a.getMinX() < 0.0 || a.getMaxX() > 600.0 || a.getMinY() < 0.0 || a.getMaxY() > 500.0) {
                this.A.remove(i);
                --i;
            }
        }
        for (int j = 0; j < this.D.size(); ++j) {
            final com.pokw.shooter.weapon.m k = this.D.elementAt(j);
            k.c();
            final Rectangle2D a2 = k.a();
            if (a2.getMinX() < 0.0 || a2.getMaxX() > 600.0 || a2.getMinY() < 0.0 || a2.getMaxY() > 500.0) {
                this.D.remove(k);
                --j;
            }
        }
    }
    
    private void b() {
        for (int i = 0; i < this.A.size(); ++i) {
            final com.pokw.shooter.weapon.m m = this.A.elementAt(i);
            for (int j = 0; j < this.C.size(); ++j) {
                final c c = this.C.elementAt(j);
                if (!c.h() && m.a(c)) {
                    this.A.removeElement(m);
                    c.a(m.d());
                    if (c.h()) {
                        this.l += c.g();
                    }
                }
            }
        }
        for (int k = 0; k < this.D.size(); ++k) {
            final com.pokw.shooter.weapon.m l = this.D.elementAt(k);
            if (l.a(this.E)) {
                this.E.a(l.d());
                this.D.remove(l);
            }
        }
        for (int n = 0; n < this.C.size(); ++n) {
            final d d = this.C.elementAt(n);
            if (!d.h() && d.a(this.E)) {
                d.a(this.E);
            }
        }
        if (this.E.i()) {
            if (this.B > 0) {
                --this.B;
                this.E.f();
                this.E.q();
            }
            else {
                this.a(this.l);
                this.a();
            }
        }
    }
    
    private boolean a(final Rectangle2D rectangle2D) {
        return rectangle2D.getMinX() <= 600.0 && rectangle2D.getMinY() <= 500.0 && rectangle2D.getMaxX() >= 0.0 && rectangle2D.getMaxY() >= 0.0;
    }
    
    public void paintComponent(final Graphics graphics) {
        final Graphics2D graphics2D = (Graphics2D)graphics;
        graphics2D.setBackground(I.q);
        graphics2D.setColor(Color.red);
        this.i.a(graphics2D, 600, 500, this);
        this.E.a(graphics2D, this);
        for (int i = 0; i < this.A.size(); ++i) {
            ((com.pokw.shooter.weapon.m)this.A.elementAt(i)).a(graphics2D, this);
        }
        for (int j = 0; j < this.C.size(); ++j) {
            ((c)this.C.elementAt(j)).a(graphics2D, this);
        }
        for (int k = 0; k < this.D.size(); ++k) {
            ((com.pokw.shooter.weapon.m)this.D.elementAt(k)).a(graphics2D, this);
        }
        graphics2D.fillRect(20, 480, this.E.m() * 100 / this.E.g(), 10);
        graphics2D.setColor(Color.white);
        graphics2D.drawRect(20, 480, 100, 10);
        graphics2D.drawImage(com.pokw.shooter.y.r, 130, 480, this);
        graphics2D.drawString(this.E.p() + ") " + this.E.n().a(), 20, 475);
        graphics2D.drawImage(com.pokw.shooter.y.q, 550, 460, this);
        graphics2D.drawString(this.B + "", 560, 490);
        graphics2D.drawString(this.l + "", 25, 25);
    }
    
    static boolean d(final I i, final boolean f) {
        return i.F = f;
    }
    
    static boolean e(final I i, final boolean u) {
        return i.u = u;
    }
    
    static boolean a(final I i, final boolean g) {
        return i.G = g;
    }
    
    static boolean c(final I i, final boolean f) {
        return i.f = f;
    }
    
    static boolean b(final I i, final boolean x) {
        return i.x = x;
    }
    
    static b a(final I i) {
        return i.E;
    }
    
    static void b(final I i) {
        i.h();
    }
    
    static {
        q = new Color(0, 67, 171);
    }
}
