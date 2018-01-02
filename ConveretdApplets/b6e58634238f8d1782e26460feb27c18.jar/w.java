import java.awt.Point;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Window;
import java.awt.Graphics;
import java.awt.Canvas;
import java.awt.Frame;
import java.awt.Image;
import java.util.Vector;
import java.awt.Component;
import java.awt.Font;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class w
{
    public Color a;
    public Font b;
    public Color c;
    public Font d;
    public Component e;
    public d f;
    public Object g;
    public int h;
    public x i;
    public Component j;
    public Vector k;
    public Image l;
    public boolean m;
    
    public w(final d f, final Frame frame, final boolean b) {
        this.g = null;
        this.f = f;
        this.a = this.f.i().e;
        this.b = this.f.i().c();
        this.c = this.f.i().d;
        this.d = this.f.i().b();
        this.e = (Component)(b ? new Canvas() {
            public boolean a = true;
            
            {
                this.a = false;
            }
            
            public void paint(final Graphics graphics) {
                if (w.a(w.this) == null) {
                    super.paint(graphics);
                    return;
                }
                w.this.a(graphics);
            }
        } : new Window(frame) {
            public boolean a = true;
            
            {
                this.a = false;
            }
            
            public final void paint(final Graphics graphics) {
                if (w.a(w.this) == null) {
                    super.paint(graphics);
                    return;
                }
                w.this.a(graphics);
            }
        });
        this.g = this.e.getTreeLock();
        this.a();
    }
    
    public void a() {
        this.e.setVisible(false);
    }
    
    private boolean h() {
        return !this.e.isVisible();
    }
    
    public void a(final Component j, final Vector k) {
        synchronized (this.g) {
            if (this.m) {
                return;
            }
            if (this.j == null) {
                this.j = j;
                this.k = k;
                this.i();
                (this.i = new Thread() {
                    public long a = System.currentTimeMillis();
                    public boolean b;
                    
                    public void run() {
                        if (this.b) {
                            return;
                        }
                        final long m;
                        if ((m = w.a(w.this).i().m) > 0L) {
                            long a;
                            do {
                                try {
                                    Thread.currentThread();
                                    Thread.sleep(1L);
                                }
                                catch (InterruptedException ex) {}
                                if (this.b) {
                                    return;
                                }
                                synchronized (w.b(w.this)) {
                                    a = this.a;
                                }
                            } while (a + m >= System.currentTimeMillis());
                        }
                        else {
                            Thread.currentThread();
                            Thread.yield();
                        }
                        synchronized (w.b(w.this)) {
                            if (this.b || !w.c(w.this).isShowing() || !w.this.h()) {
                                return;
                            }
                            w.this.j();
                        }
                    }
                    
                    private void a() {
                        synchronized (w.b(w.this)) {
                            this.a = System.currentTimeMillis();
                        }
                    }
                    
                    private boolean b() {
                        final boolean b;
                        synchronized (w.b(w.this)) {
                            b = this.b;
                            this.b = true;
                        }
                        return b;
                    }
                }).start();
            }
        }
    }
    
    private void i() {
        if (this.k == null) {
            return;
        }
        for (int i = 0; i < this.k.size(); ++i) {
            final al al = this.k.elementAt(i);
            if (al.b) {
                al.setFont(this.b);
                al.setBackground(this.a);
                final al al2 = al;
                this.f.d();
                al2.setForeground(f.a(al.getBackground()));
            }
            else {
                al.setFont(this.d);
                al.setBackground(this.c);
                final al al3 = al;
                this.f.d();
                al3.setForeground(f.a(al.getBackground()));
            }
            if (al instanceof ak) {
                ((ak)al).a(this);
            }
        }
    }
    
    public final void b() {
        synchronized (this.g) {
            if (this.i != null) {
                this.i.a();
            }
        }
    }
    
    public void a(final Vector k) {
        this.k = k;
        this.i();
        if (this.h() || this.j == null || !this.j.isShowing()) {
            return;
        }
        this.j();
    }
    
    public void c() {
        synchronized (this.g) {
            boolean b = false;
            if (this.k != null && this.k.size() > 0) {
                for (int i = 0; i < this.k.size(); ++i) {
                    final al al = this.k.elementAt(i);
                    if (al instanceof ak && ((ak)al).b && ((ak)al).a() != null) {
                        ((ak)al).a((Image)null);
                        b = true;
                    }
                }
            }
            if (b) {
                this.e.repaint();
            }
        }
    }
    
    public void d() {
        if (!this.m) {
            synchronized (this.g) {
                if (this.j != null) {
                    this.a();
                    this.i.b();
                    this.i = null;
                    if ((this.h = (this.h + 1) % 12) == 0) {
                        this.f.d();
                        f.b();
                    }
                    this.j = null;
                    if (this.k != null) {
                        for (int i = 0; i < this.k.size(); ++i) {
                            final al al = this.k.elementAt(i);
                            if (al instanceof ak) {
                                ((ak)al).a((w)null);
                            }
                        }
                    }
                    this.k = null;
                }
            }
        }
    }
    
    public void a(final Graphics graphics) {
        synchronized (this.g) {
            final Dimension size = this.e.getSize();
            if ((this.l == null || this.l.getWidth(this.e) < size.width || this.l.getHeight(this.e) < size.height) && size.width > 0 && size.height > 0) {
                if (this.l != null) {
                    this.l.flush();
                    this.l = null;
                    this.f.d();
                    f.b();
                }
                this.l = this.e.createImage(size.width, size.height);
            }
            if (size.width > 0 && size.height > 0) {
                final Graphics graphics2 = this.l.getGraphics();
                final Rectangle clipBounds = graphics.getClipBounds();
                graphics2.setClip(clipBounds.x, clipBounds.y, clipBounds.width, clipBounds.height);
                this.b(graphics2);
                graphics2.dispose();
            }
            graphics.drawImage(this.l, 0, 0, this.e);
        }
    }
    
    public void b(final Graphics graphics) {
        if (this.k != null) {
            final Rectangle rectangle = new Rectangle(this.e.getSize());
            if (this.e instanceof az) {
                final Insets insets = ((az)this.e).getInsets();
                final Rectangle rectangle2 = rectangle;
                rectangle2.x += insets.left;
                final Rectangle rectangle3 = rectangle;
                rectangle3.width -= insets.left + insets.right;
                final Rectangle rectangle4 = rectangle;
                rectangle4.y += insets.top;
                final Rectangle rectangle5 = rectangle;
                rectangle5.height -= insets.top + insets.bottom;
            }
            graphics.setColor(this.c);
            graphics.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
            graphics.setColor(this.f.i().f);
            graphics.fillRect(rectangle.x, rectangle.y, rectangle.width, 1);
            graphics.fillRect(rectangle.x, rectangle.y, 1, rectangle.height);
            graphics.fillRect(rectangle.x + rectangle.width - 1, rectangle.y, 1, rectangle.height);
            graphics.fillRect(rectangle.x, rectangle.y + rectangle.height - 1, rectangle.width, 1);
            this.c(graphics);
        }
    }
    
    private void c(final Graphics graphics) {
        for (int i = 0; i < this.k.size(); ++i) {
            this.a(graphics, (al)this.k.elementAt(i));
        }
    }
    
    private void a(final Graphics graphics, final al al) {
        if (al.isVisible()) {
            final Rectangle clipBounds = graphics.getClipBounds();
            final Rectangle bounds = al.getBounds();
            if (bounds.width > 0 && bounds.height > 0 && bounds.intersects(clipBounds)) {
                final Graphics create = graphics.create();
                create.translate(bounds.x, bounds.y);
                create.setClip(0, 0, bounds.width, bounds.height);
                try {
                    al.paint(create);
                }
                catch (Throwable t) {}
                finally {
                    create.dispose();
                }
            }
        }
    }
    
    private void j() {
        if (this.j == null || !this.j.isShowing()) {
            return;
        }
        final Dimension k = this.k();
        Rectangle rectangle;
        if (this.e instanceof ay) {
            final Rectangle bounds;
            rectangle = (bounds = this.j.getBounds());
            bounds.x += this.e.getParent().getInsets().left;
            final Rectangle rectangle2 = rectangle;
            rectangle2.y += this.e.getParent().getInsets().top;
        }
        else {
            if (!(this.e instanceof az)) {
                return;
            }
            rectangle = new Rectangle(this.j.getLocationOnScreen(), this.j.getSize());
        }
        final f d = this.f.d();
        this.f.d();
        final Point a = d.a(f.c(), rectangle, k, 0);
        this.e.setBounds(a.x, a.y, k.width, k.height);
        this.e.setVisible(true);
    }
    
    private Dimension k() {
        final Rectangle rectangle = new Rectangle();
        if (this.e instanceof az) {
            final Insets insets = ((az)this.e).getInsets();
            final Rectangle rectangle2 = rectangle;
            rectangle2.x += insets.left;
            final Rectangle rectangle3 = rectangle;
            rectangle3.width += insets.left + insets.right;
            final Rectangle rectangle4 = rectangle;
            rectangle4.y += insets.top;
            final Rectangle rectangle5 = rectangle;
            rectangle5.height += insets.top + insets.bottom;
        }
        final Rectangle rectangle6 = rectangle;
        ++rectangle6.x;
        final Rectangle rectangle7 = rectangle;
        ++rectangle7.y;
        this.a(rectangle);
        final Rectangle rectangle8 = rectangle;
        ++rectangle8.width;
        final Rectangle rectangle9 = rectangle;
        ++rectangle9.height;
        return rectangle.getSize();
    }
    
    private void a(final Rectangle rectangle) {
        int max = 0;
        for (int i = 0; i < this.k.size(); ++i) {
            max = Math.max(max, ((al)this.k.elementAt(i)).c().width);
        }
        rectangle.width += max;
        int n = rectangle.y + 3;
        for (int j = 0; j < this.k.size(); ++j) {
            final al al = this.k.elementAt(j);
            if (al instanceof ak) {
                ((ak)al).b();
            }
            al.setVisible(true);
            final Dimension c = al.c();
            al.setBounds(rectangle.x, n, max, c.height);
            n += c.height;
        }
        rectangle.height += n;
    }
    
    public final void e() {
        this.m = true;
        if (this.e instanceof ay) {
            this.e.setVisible(false);
        }
        else if (this.e instanceof az) {
            ((az)this.e).dispose();
        }
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        if (this.i != null) {
            this.i.b();
            this.i = null;
        }
        this.j = null;
        this.k = null;
        this.l = null;
    }
    
    public void f() {
        this.j();
    }
    
    public Component g() {
        return this.e;
    }
    
    public static /* synthetic */ d a(final w w) {
        return w.f;
    }
    
    public static /* synthetic */ Object b(final w w) {
        return w.g;
    }
    
    public static /* synthetic */ Component c(final w w) {
        return w.j;
    }
}
