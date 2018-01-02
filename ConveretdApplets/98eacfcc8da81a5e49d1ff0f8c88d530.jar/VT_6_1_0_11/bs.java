// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import javax.swing.event.ChangeListener;
import com.hw.client.util.c;
import java.awt.Rectangle;
import java.awt.Component;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import javax.swing.DefaultBoundedRangeModel;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.BoundedRangeModel;
import java.awt.Color;
import java.awt.Dimension;

public class bs extends aT
{
    private static Dimension a;
    private static Color b;
    private BoundedRangeModel c;
    private BoundedRangeModel d;
    private int e;
    private dC f;
    private int g;
    private Dimension h;
    private Color i;
    private ImageIcon j;
    private ImageIcon k;
    private boolean l;
    private boolean m;
    private Color n;
    private Color o;
    private Color p;
    private Insets q;
    
    public bs() {
        super("", 0);
        this.e = 0;
        this.g = 6;
        this.h = bs.a;
        this.i = bs.b;
        this.l = true;
        this.m = true;
        this.n = Color.darkGray;
        this.p = Color.black;
        this.q = new Insets(0, 0, 0, 0);
        this.addMouseListener(this.f = new dC(this, this));
        this.addMouseMotionListener(this.f);
        this.setOpaque(false);
    }
    
    public bs(final int n, final int maximum, final int n2) {
        this();
        this.b(new DefaultBoundedRangeModel());
        this.b().setMinimum(0);
        this.b().setMaximum(maximum);
        this.b(0);
    }
    
    public bs(final BoundedRangeModel boundedRangeModel, final int n) {
        this();
        this.b(boundedRangeModel);
        this.b(1);
    }
    
    public final void a(final boolean m) {
        if (!m) {
            this.c().setValueIsAdjusting(false);
        }
        this.m = m;
    }
    
    public void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
        if (this.getText().trim().length() != 0) {
            return;
        }
        this.a(graphics);
        if (this.l) {
            final Rectangle a = this.a();
            if (this.j != null) {
                if (this.c().getValueIsAdjusting()) {
                    graphics.drawImage(((this.k != null) ? this.k : this.j).getImage(), a.x, a.y, this);
                    return;
                }
                this.j.paintIcon(this, graphics, a.x, a.y);
            }
            else {
                graphics.setColor(this.i);
                graphics.fillOval(a.x, a.y, a.width - 1, a.height - 1);
            }
        }
    }
    
    protected void a(final Graphics graphics) {
        final Rectangle rectangle = (this.e == 0) ? new Rectangle(this.q.left, (this.h.height - this.g) / 2, this.getWidth() - this.q.left - this.q.right, this.g) : new Rectangle((this.h.width - this.g) / 2, this.q.top, this.g, this.getHeight() - this.q.top - this.q.bottom);
        if (this.d == null) {
            this.a(new DefaultBoundedRangeModel());
        }
        final BoundedRangeModel d = this.d;
        if (this.e == 0) {
            final int n = rectangle.width * (this.a(d, d.getValue()) - d.getMinimum()) / (d.getMaximum() - d.getMinimum());
            if (this.o != null) {
                graphics.setColor(this.o);
            }
            else {
                graphics.setColor(this.n);
            }
            graphics.fillRect(rectangle.x, rectangle.y, n, rectangle.height);
            graphics.setColor(this.n);
            graphics.fillRect(rectangle.x + n, rectangle.y, rectangle.width - n, rectangle.height);
            if (this.p != null) {
                graphics.setColor(this.p);
            }
            graphics.drawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
            return;
        }
        final int n2 = rectangle.height * (this.a(d, d.getValue()) - d.getMinimum()) / (d.getMaximum() - d.getMinimum());
        if (this.o != null) {
            graphics.setColor(this.o);
        }
        else {
            graphics.setColor(this.n);
        }
        graphics.fillRect(rectangle.x, rectangle.y, rectangle.width, n2);
        graphics.setColor(this.n);
        graphics.fillRect(rectangle.x, rectangle.y + n2, rectangle.width, rectangle.height - n2);
        if (this.p != null) {
            graphics.setColor(this.p);
        }
        graphics.drawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }
    
    private Dimension d() {
        if (this.h == null) {
            this.h = bs.a;
        }
        return this.h;
    }
    
    private void a(final Dimension h) {
        if (h == null) {
            throw new IllegalArgumentException("knobSize should not be null");
        }
        if (h.equals(this.h)) {
            return;
        }
        this.h = h;
        this.e();
    }
    
    private void e() {
        if (this.e == 0) {
            this.setMinimumSize(new Dimension(this.d().width * 3, this.d().height));
            this.setPreferredSize(new Dimension(this.d().width * 3, this.d().height));
            this.setMaximumSize(new Dimension(1000, this.d().height));
            return;
        }
        this.setMinimumSize(new Dimension(this.d().width, this.d().height * 3));
        this.setPreferredSize(new Dimension(this.d().width, this.d().height * 3));
        this.setMaximumSize(new Dimension(this.d().width, 1000));
    }
    
    public final Rectangle a() {
        final BoundedRangeModel c = this.c();
        if (this.e == 0) {
            return new Rectangle((this.getWidth() - this.h.width) * (this.a(c, c.getValue()) - c.getMinimum()) / (c.getMaximum() - c.getMinimum()), 0, this.h.width, this.h.height);
        }
        return new Rectangle(0, (this.getHeight() - this.h.height) * (this.a(c, c.getValue()) - c.getMinimum()) / (c.getMaximum() - c.getMinimum()), this.h.width, this.h.height);
    }
    
    public final int a(final BoundedRangeModel boundedRangeModel, final int n) {
        if (this.e == 0) {
            return n;
        }
        return boundedRangeModel.getMinimum() + boundedRangeModel.getMaximum() - n;
    }
    
    private void b(final int e) {
        if (e == this.e) {
            return;
        }
        if (e != 0 && e != 1) {
            throw new IllegalArgumentException("orientation should be HORIZONTAL or VERTICAL");
        }
        this.e = e;
        this.e();
        com.hw.client.util.c.a(this);
    }
    
    private void a(BoundedRangeModel d) {
        if (d == null) {
            d = new DefaultBoundedRangeModel();
        }
        if (d == this.d) {
            return;
        }
        if (this.d != null) {
            this.d.removeChangeListener(this.f);
        }
        (this.d = d).addChangeListener(this.f);
    }
    
    private void b(BoundedRangeModel boundedRangeModel) {
        if (boundedRangeModel == null) {
            boundedRangeModel = new DefaultBoundedRangeModel();
        }
        this.c(boundedRangeModel);
        this.a(boundedRangeModel);
    }
    
    public final BoundedRangeModel b() {
        if (this.d == this.c) {
            if (this.c == null) {
                this.b((BoundedRangeModel)null);
            }
            return this.c;
        }
        throw new IllegalStateException("This method should not be used when this class is used with 2 models");
    }
    
    public final BoundedRangeModel c() {
        if (this.c == null) {
            this.c(new DefaultBoundedRangeModel());
        }
        return this.c;
    }
    
    private void c(BoundedRangeModel c) {
        if (c == null) {
            c = new DefaultBoundedRangeModel();
        }
        if (c == this.c) {
            return;
        }
        if (this.c != null) {
            this.c.removeChangeListener(this.f);
        }
        (this.c = c).addChangeListener(this.f);
    }
    
    public final void a(final int g) {
        this.g = g;
        if (this.j == null) {
            this.a(new Dimension(g, g));
        }
    }
    
    public final void b(final boolean l) {
        this.l = l;
    }
    
    public final void a(final ImageIcon j) {
        this.j = j;
        this.a(new Dimension(j.getIconWidth(), j.getIconHeight()));
    }
    
    public final void b(final ImageIcon k) {
        this.k = k;
    }
    
    public final void a(final Color n) {
        this.n = n;
    }
    
    public final void b(final Color o) {
        this.o = o;
    }
    
    public final void a(final Insets q) {
        this.q = q;
    }
    
    public final void c(final Color p) {
        this.p = p;
    }
    
    static boolean a(final bs bs) {
        return bs.m;
    }
    
    static int b(final bs bs) {
        return bs.e;
    }
    
    static Dimension c(final bs bs) {
        return bs.h;
    }
    
    static {
        bs.a = new Dimension(12, 12);
        bs.b = Color.black;
    }
}
