import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.Component;
import java.awt.LayoutManager;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class aw implements LayoutManager
{
    public d a;
    public q b;
    public ag c;
    
    public aw(final d a, final q b) {
        this.a = a;
        this.b = b;
    }
    
    public void a() {
        this.c = new ag(this.a, this.b);
        this.b.add(this.c);
    }
    
    public void b() {
        if (this.b.f().c().ab) {
            this.c.setVisible(true);
            if (!this.c.a()) {
                this.c.setBounds(this.b.f().c().y - this.c.getBounds().width / 2, this.c.getBounds().y, this.c.getBounds().width, this.c.getBounds().height);
            }
            this.c.setSize(this.c.getSize().width, this.b.getSize().height);
        }
        else {
            this.c.setVisible(false);
        }
    }
    
    public void c() {
        final FontMetrics fontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(this.b.f().c().a());
        int[] widths = fontMetrics.getWidths();
        if (widths.length == 0) {
            widths = new int[] { fontMetrics.stringWidth("M") };
        }
        int n = 0;
        for (int i = 0; i < widths.length; ++i) {
            n += widths[i];
        }
        final int n2 = n / widths.length;
        final int n3 = this.b.f().c().x * n2;
        this.a(this.b);
        int max = 0;
        synchronized (this.b.getTreeLock()) {
            for (int j = 0; j < this.b.getComponentCount(); ++j) {
                final Component component = this.b.getComponent(j);
                if (component instanceof ax) {
                    final ax ax = (ax)component;
                    if (ax.f > 0) {
                        max = Math.max(max, Math.max(fontMetrics.stringWidth(ax.c(true)), fontMetrics.stringWidth(ax.b(true))));
                    }
                }
            }
        }
        this.b.f().c().y = Math.min(n3, max + 6 * n2) + 2 * this.b.f().c().l;
    }
    
    public void addLayoutComponent(final String s, final Component component) {
        if (component instanceof ax) {
            this.c.a(component);
        }
    }
    
    public void removeLayoutComponent(final Component component) {
        if (component instanceof ax) {
            this.c.b(component);
        }
        else if (component instanceof ae) {
            ((ae)component).e();
        }
    }
    
    public Dimension preferredLayoutSize(final Container container) {
        return this.a.s.GetSize();
    }
    
    public Dimension minimumLayoutSize(final Container container) {
        this.a(container);
        final int l = this.b.f().c().l;
        final s f = this.b.f();
        final k c = f.c();
        return new Dimension((2 * l + 1) * ((c.ab ? 1 : 0) + c.a(f.q())), 10);
    }
    
    public int d() {
        int n = this.b.f().c().y;
        if (n < this.c.c()) {
            n = this.c.c();
            this.b.f().c().y = n;
        }
        else if (n > this.c.d()) {
            n = this.c.d();
            this.b.f().c().y = n;
        }
        return n;
    }
    
    public void a(final Container container) {
        ae ae = null;
        af af = null;
        ax ax = null;
        synchronized (container.getTreeLock()) {
            for (int i = 0; i < container.getComponentCount(); ++i) {
                Component component = container.getComponent(i);
                if (component instanceof af) {
                    af = (af)component;
                    af.a(this.b.f().c().ac);
                    component = container.getComponent(++i);
                }
                if (component instanceof ax) {
                    ax = (ax)component;
                    ax.a(this.b.f().c().ac);
                    component = container.getComponent(++i);
                }
                if (component instanceof ae) {
                    final ax ax2 = (ax)component;
                    if (ae != null && ae.a(ax2)) {
                        af.a(true);
                        ax.a(true);
                    }
                    ae = ax2;
                }
            }
        }
    }
    
    public int b(final Container container) {
        int n = 0;
        synchronized (container.getTreeLock()) {
            for (int i = 0; i < container.getComponentCount(); ++i) {
                final Component component = container.getComponent(i);
                if (component instanceof af) {
                    n += ((af)component).c;
                }
            }
            return n;
        }
    }
}
