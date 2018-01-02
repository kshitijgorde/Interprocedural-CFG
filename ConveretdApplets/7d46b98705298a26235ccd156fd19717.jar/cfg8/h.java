// 
// Decompiled by Procyon v0.5.30
// 

package cfg8;

import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.AdjustmentEvent;
import java.awt.Component;
import java.awt.Color;
import java.awt.LayoutManager;
import java.util.Vector;
import java.awt.Scrollbar;
import java.awt.event.AdjustmentListener;
import java.awt.Panel;

class h extends Panel implements AdjustmentListener
{
    final g a;
    private Scrollbar b;
    private Vector c;
    private Vector d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private g j;
    private f k;
    
    h(final g a) {
        this.a = a;
        this.b = new Scrollbar();
        this.c = null;
        this.d = null;
        this.e = 0;
        this.f = -1;
        this.g = 0;
        this.h = 12;
        this.i = 12;
        this.j = null;
        this.k = new f();
        this.setLayout(null);
        this.setBackground(Color.white);
        this.b.setMinimum(0);
        this.b.addAdjustmentListener(this);
        this.add(this.b);
        this.k.setVisible(false);
        this.add(this.k);
        this.enableEvents(16L);
    }
    
    protected f a() {
        return this.k;
    }
    
    protected void a(final g j) {
        this.j = j;
    }
    
    protected void a(final int h, final int i) {
        this.h = h;
        this.i = i;
    }
    
    protected void b() {
        this.c = new Vector();
        this.d = new Vector();
        this.b.setValues(0, this.e, 0, 0);
        this.g = 0;
        this.f = -1;
    }
    
    protected void a(final n n, final int n2) {
        this.c.addElement(n);
        this.d.addElement(new Integer(n2));
    }
    
    protected void c() {
        this.b.setValues(0, this.e, 0, this.c.size());
        this.b.setValue(0);
    }
    
    protected void a(final Vector vector, final String s, final int n) {
        final int a = RotationImageFilter.a;
        int f = 0;
        int i = 0;
        while (i < vector.size()) {
            final n n2 = vector.elementAt(i);
            if (a == 0) {
                if (!n2.a(s)) {
                    h h = this;
                    Label_0071: {
                        if (a == 0) {
                            this.a(n2, i);
                            if (n != i) {
                                break Label_0071;
                            }
                            h = this;
                        }
                        h.f = f;
                    }
                    ++f;
                }
                ++i;
            }
            if (a != 0) {
                break;
            }
        }
    }
    
    protected int d() {
        return this.f;
    }
    
    protected String e() {
        return this.c.elementAt(this.f).b();
    }
    
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        final int a = RotationImageFilter.a;
        int n2;
        final int n = n2 = adjustmentEvent.getID();
        int n4;
        final int n3 = n4 = 601;
        if (a == 0) {
            if (n != n3) {
                return;
            }
            final int g;
            n2 = (g = this.g);
            final int value;
            n4 = (value = adjustmentEvent.getValue());
        }
        if (a == 0) {
            if (n == n3) {
                return;
            }
            n2 = adjustmentEvent.getValue();
            n4 = this.c.size() - this.e;
        }
        Label_0080: {
            if (n2 < n4) {
                this.g = adjustmentEvent.getValue();
                if (a == 0) {
                    break Label_0080;
                }
            }
            this.g = this.c.size() - this.e;
        }
        this.repaint(0L);
    }
    
    public void doLayout() {
        final int a = RotationImageFilter.a;
        super.doLayout();
        final Rectangle bounds = this.getParent().getBounds();
        final Rectangle bounds2;
        final Rectangle rectangle = bounds2 = this.getBounds();
        if (a == 0) {
            if (bounds2.height > bounds.height - 2) {
                rectangle.height = bounds.height - 2;
            }
            this.a.N.b(new Rectangle(0, 0, rectangle.width, rectangle.height));
            this.a.N.a();
        }
        final Rectangle rectangle2 = bounds2;
        int a2 = 0;
        final int a3 = this.k.a() ? 1 : 0;
        final int n = 1;
        Label_0189: {
            h h = null;
            Label_0185: {
                if (a == 0) {
                    if (a3 == n) {
                        a2 = this.k.a(rectangle2.width);
                        final Rectangle rectangle3 = rectangle2;
                        rectangle3.height -= a2 + 2;
                    }
                    this.e = (rectangle2.height - 2) / this.i;
                    h = this;
                    if (a != 0) {
                        break Label_0185;
                    }
                    final int f = this.f;
                    final int e = this.e;
                }
                if (a3 >= n) {
                    this.g = 1 + (this.f - this.e);
                    if (a == 0) {
                        break Label_0189;
                    }
                }
                h = this;
            }
            h.g = 0;
        }
        final Vector c = this.c;
        Label_0334: {
            Label_0292: {
                h h2 = null;
                Label_0285: {
                    if (a == 0) {
                        if (c == null) {
                            break Label_0334;
                        }
                        h2 = this;
                        if (a != 0) {
                            break Label_0285;
                        }
                        final Vector c2 = this.c;
                    }
                    if (c.size() <= this.e) {
                        this.e = this.c.size();
                        this.b.setEnabled(false);
                        this.b.setVisible(false);
                        if (a == 0) {
                            break Label_0292;
                        }
                    }
                    this.b.setValues(this.g, this.e, 0, this.c.size());
                    this.b.setEnabled(true);
                    h2 = this;
                }
                h2.b.setVisible(true);
            }
            rectangle2.height = this.i * this.e;
            rectangle.height = rectangle2.height + 2 + a2 + this.a.N.e();
            this.setBounds(rectangle);
        }
        final int n2 = rectangle.y + rectangle.height;
        final int n3 = bounds.y + bounds.height;
        final f k;
        Label_0534: {
            if (a == 0) {
                if (n2 > n3) {
                    rectangle.y = bounds.height - rectangle.height - 1;
                    this.setBounds(rectangle);
                }
                this.b.setBounds(rectangle2.x + rectangle2.width - 16, rectangle2.y, 16, rectangle2.height);
                this.b.doLayout();
                k = this.k;
                if (a != 0) {
                    break Label_0534;
                }
                k.c();
            }
            if (n2 == n3) {
                final f i = this.k;
                Label_0510: {
                    if (a == 0) {
                        if (i.a()) {
                            this.k.setBounds(rectangle2.x, rectangle2.y + rectangle2.height + 4, rectangle2.width, a2);
                            if (a == 0) {
                                break Label_0510;
                            }
                        }
                        final f j = this.k;
                    }
                    i.setBounds(rectangle2.x + rectangle2.width - 17, rectangle2.y + 1, 17, 17);
                }
                this.k.doLayout();
                this.k.setVisible(true);
                if (a == 0) {
                    return;
                }
            }
            final f l = this.k;
        }
        k.setVisible(false);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        final int a = RotationImageFilter.a;
        super.paint(graphics);
        final Rectangle a2 = this.a.N.a();
        final Rectangle bounds = this.getBounds();
        graphics.clearRect(a2.x, a2.y, a2.width, a2.height);
        this.a.N.a(graphics, this, 0, 0, bounds.width, bounds.height);
        graphics.setColor(Color.black);
        final Rectangle rectangle = new Rectangle(a2.x, a2.y, a2.width, this.i);
        int n = 0;
        int g = this.g;
        while (g < this.c.size() && n < this.e) {
            final n n2 = this.c.elementAt(g);
            final Rectangle rectangle2 = rectangle;
            final int h = this.h;
            final int n3 = this.i - 2;
            int f;
            final int n4 = f = this.f;
            if (a == 0) {
                if (n4 == g) {
                    f = 1;
                }
                else {
                    f = 0;
                }
            }
            n2.a(graphics, rectangle2, h, n3, f != 0, this);
            final Rectangle rectangle3 = rectangle;
            rectangle3.y += this.i;
            ++n;
            ++g;
            if (a != 0) {
                break;
            }
        }
    }
    
    protected void processMouseEvent(final MouseEvent mouseEvent) {
        final int a = RotationImageFilter.a;
        int n2;
        final int n = n2 = mouseEvent.getID();
        h h = null;
        Label_0293: {
            Label_0292: {
                Label_0108: {
                    if (a == 0) {
                        switch (n) {
                            case 501: {
                                final int clickCount;
                                n2 = (clickCount = mouseEvent.getClickCount());
                                break;
                            }
                            case 502: {
                                break Label_0108;
                            }
                        }
                    }
                    Label_0103: {
                        if (a == 0) {
                            if (n <= 0) {
                                break Label_0103;
                            }
                            n2 = mouseEvent.getY();
                        }
                        int n3 = n2;
                        if (a == 0) {
                            if (n3 > 0) {
                                n3 = (n3 - this.a.N.c()) / this.i;
                            }
                            this.f = this.g + n3;
                        }
                        this.repaint(0L);
                    }
                    if (a == 0) {
                        break Label_0292;
                    }
                }
                final int clickCount2 = mouseEvent.getClickCount();
                final int n4 = 0;
                if (a == 0) {
                    if (clickCount2 <= n4) {
                        break Label_0292;
                    }
                    h = this;
                    if (a != 0) {
                        break Label_0293;
                    }
                    final int f = this.f;
                }
                if (clickCount2 > n4) {
                    h h2 = this;
                    if (a == 0) {
                        if (this.j != null) {
                            final o f2 = this.j.f();
                            if (f2 != null) {
                                final int f3 = this.f;
                                Label_0267: {
                                    Label_0245: {
                                        if (a == 0) {
                                            if (f3 >= this.d.size()) {
                                                break Label_0245;
                                            }
                                            this.d.elementAt(this.f);
                                        }
                                        final int n5 = f3;
                                        Label_0240: {
                                            Label_0218: {
                                                if (a == 0) {
                                                    if (n5 == -1) {
                                                        break Label_0218;
                                                    }
                                                    f2.b(n5, true);
                                                }
                                                if (a == 0) {
                                                    break Label_0240;
                                                }
                                            }
                                            f2.a(this.c.elementAt(this.f).b(), true);
                                        }
                                        if (a == 0) {
                                            break Label_0267;
                                        }
                                    }
                                    f2.a(this.c.elementAt(this.f).b(), true);
                                }
                                this.j.e();
                                this.j.c(true);
                            }
                            if (a == 0) {
                                break Label_0292;
                            }
                        }
                        h2 = this;
                    }
                    h2.setVisible(false);
                }
            }
            h = this;
        }
        h.processMouseEvent(mouseEvent);
    }
}
