// 
// Decompiled by Procyon v0.5.30
// 

package ji.awt;

import java.awt.Image;
import java.awt.Color;
import java.awt.Graphics;
import ji.v1event.oj;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import ji.util.e;
import ji.v1event.cx;
import java.awt.Rectangle;
import ji.util.d;
import java.awt.Component;
import java.awt.LayoutManager;
import java.util.Vector;
import ji.document.ad;
import ji.v1event.aq;
import java.awt.event.ActionListener;
import ji.v1base.jiPanel;

public class dk extends jiPanel implements ActionListener, aq
{
    private dl a;
    private dn b;
    private dp c;
    private uu d;
    private String e;
    private ad f;
    private boolean g;
    private Vector h;
    
    public dk(final String e, final ad f) {
        super(e, null);
        this.g = true;
        this.h = new Vector();
        this.e = e;
        this.f = f;
        (this.a = new dl(e, f, f.cj())).a(this);
        (this.b = new dn(f, e)).setVisible(false);
        this.b.a(this);
        (this.c = new dp(e, f)).setVisible(false);
        (this.d = new uu(e, 1343, f)).setVisible(false);
        this.d.addActionListener(this);
        this.setBorderStyle(0);
        this.add(this.a);
        this.add(this.b);
        this.add(this.c);
        this.add(this.d);
    }
    
    public void releaseResources() {
        try {
            if (this.a != null) {
                this.remove(this.a);
                this.a.d();
                this.a = null;
            }
            if (this.b != null) {
                this.b.setVisible(false);
                this.remove(this.b);
                this.b.releaseResources();
                this.b = null;
            }
            if (this.c != null) {
                this.c.setVisible(false);
                this.remove(this.c);
                this.c.a();
                this.c = null;
            }
            if (this.d != null) {
                this.d.setVisible(false);
                this.remove(this.d);
                this.d.releaseResources();
                this.d = null;
            }
            if (this.h != null) {
                this.h.removeAllElements();
                this.h = null;
            }
            super.releaseResources();
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
        }
    }
    
    public void a(final String s, final boolean b) {
        if (this.a != null) {
            this.a.a(s);
            if (b) {
                this.a.b();
            }
        }
    }
    
    public void a() {
        if (this.a != null) {
            this.a.a();
        }
    }
    
    public void a(final boolean g) {
        this.g = g;
    }
    
    public void b(final boolean visible) {
        this.b.setVisible(visible);
    }
    
    public void c(final boolean b) {
        if (b && this.b.isVisible()) {
            this.b.setVisible(false);
        }
        this.c.setVisible(b);
        this.d.setVisible(b);
        final Rectangle bounds = this.getBounds();
        bounds.height = this.c();
        this.setBounds(bounds);
    }
    
    public void a(final cx cx) {
        this.c.a(cx);
    }
    
    public void b(final cx cx) {
        this.c.b(cx);
    }
    
    public void setBounds(int n, int y, final int n2, final int n3) {
        super.setBounds(n, y, n2, n3);
        n = 0;
        y = 0;
        int n4 = n3;
        if (this.b.isVisible()) {
            n4 -= 20;
        }
        if (this.c.isVisible()) {
            n4 -= this.c.getHeight();
        }
        this.a.setBounds(0, y, n2, n4);
        y += n4;
        if (this.b.isVisible()) {
            this.b.setBounds(0, y, n2, 20);
            y += 20;
        }
        if (this.c.isVisible()) {
            final Dimension preferredSize = this.c.getPreferredSize();
            if (preferredSize.height < this.c.getHeight()) {
                preferredSize.height = this.c.getHeight();
            }
            if (preferredSize.width < this.c.getWidth()) {
                preferredSize.width = this.c.getWidth();
            }
            ji.util.e.a(this.c, new Rectangle(n, y, preferredSize.width, preferredSize.height));
            final Rectangle rectangle = new Rectangle();
            rectangle.width = 18;
            rectangle.height = 18;
            rectangle.x = n2 - rectangle.width;
            rectangle.y = y;
            ji.util.e.a(this.d, rectangle);
        }
    }
    
    public Dimension getPreferredSize() {
        final Dimension preferredSize = this.a.getPreferredSize();
        preferredSize.height = this.c();
        return preferredSize;
    }
    
    private int c() {
        final Dimension preferredSize = this.a.getPreferredSize();
        if (!this.g) {
            preferredSize.height = 0;
        }
        if (this.b.isVisible()) {
            final Dimension dimension = preferredSize;
            dimension.height += 20;
        }
        if (this.c.isVisible()) {
            final Dimension dimension2 = preferredSize;
            dimension2.height += this.c.getHeight();
        }
        return preferredSize.height;
    }
    
    public void a(final int n) {
        if (n < 99 && n >= 0) {
            this.b.setVisible(true);
            this.b.a(n);
            this.c(false);
        }
        this.b.a();
    }
    
    public boolean b() {
        return this.b.isVisible();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.d) {
            this.b(new oj(this, 5));
        }
        else {
            this.b(new oj(this, 1));
        }
    }
    
    public void a(final aq aq) {
        if (this.h != null && !this.h.contains(aq)) {
            this.h.addElement(aq);
        }
    }
    
    public void a(final oj oj) {
        this.b(oj);
    }
    
    private void b(final oj oj) {
        if (oj != null && this.h != null) {
            for (int i = 0; i < this.h.size(); ++i) {
                ((aq)this.h.elementAt(i)).a(oj);
            }
        }
    }
    
    class uu extends dm
    {
        protected final void a(final Graphics graphics) {
            super.a(graphics);
        }
        
        public uu(final dk dk, final String s, final int n, final ad ad) {
            super("clearsearchbutton", s, n, ad);
        }
        
        private Image a(final Color color, final boolean b) {
            final Dimension l = this.l();
            final int width = l.width;
            final int height = l.height;
            final Image a = this.a(width, height);
            final Graphics graphics = a.getGraphics();
            d.a(graphics);
            try {
                final int n = 2;
                final int n2 = 2;
                final int n3 = width - n * 3;
                graphics.setColor(Color.red);
                graphics.fillRect(0, 0, width, height);
                graphics.setColor(Color.black);
                graphics.drawRect(0, 0, width - 1, height - 1);
                graphics.setColor(Color.white);
                graphics.drawLine(n, n2, n + n3, n2 + n3);
                graphics.drawLine(1 + n, n2, 1 + n + n3, n2 + n3);
                graphics.drawLine(n, n2 + n3, n + n3, n2);
                graphics.drawLine(1 + n, n2 + n3, 1 + n + n3, n2);
            }
            catch (Exception ex) {}
            return a;
        }
        
        protected Image m() {
            return this.a(this.a(d.h, false), d.h);
        }
    }
}
