// 
// Decompiled by Procyon v0.5.30
// 

package b.a.e;

import java.awt.event.MouseEvent;
import java.awt.Graphics;
import javax.swing.border.Border;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Insets;
import javax.swing.JPanel;

public class m extends JPanel
{
    private Insets a;
    private static Insets b;
    
    public m() {
        this.a = null;
    }
    
    public m(final LayoutManager layout) {
        this.a = null;
        this.setLayout(layout);
    }
    
    public m(final int n, final int n2) {
        this.a = null;
        this.setPreferredSize(new Dimension(n, n2));
        this.setOpaque(false);
    }
    
    public Insets getInsets() {
        final Insets insets = new Insets(0, 0, 0, 0);
        final Border border = this.getBorder();
        if (border != null) {
            final Insets borderInsets = border.getBorderInsets(this);
            final Insets insets2 = insets;
            insets2.top += borderInsets.top;
            final Insets insets3 = insets;
            insets3.left += borderInsets.left;
            final Insets insets4 = insets;
            insets4.bottom += borderInsets.bottom;
            final Insets insets5 = insets;
            insets5.right += borderInsets.right;
        }
        if (this.a != null) {
            final Insets insets6 = insets;
            insets6.top += this.a.top;
            final Insets insets7 = insets;
            insets7.left += this.a.left;
            final Insets insets8 = insets;
            insets8.bottom += this.a.bottom;
            final Insets insets9 = insets;
            insets9.right += this.a.right;
        }
        return insets;
    }
    
    public void a(final int n, final int n2, final int n3, final int n4) {
        this.a = new Insets(n, n2, n3, n4);
    }
    
    public void a(final Insets insets) {
        this.a(insets.top, insets.left, insets.bottom, insets.right);
    }
    
    public Dimension a() {
        final Insets insets = this.getInsets();
        return new Dimension(this.getWidth() - insets.left - insets.right, this.getHeight() - insets.top - insets.bottom);
    }
    
    protected void paintComponent(Graphics create) {
        super.paintComponent(create);
        final Insets insets = this.getInsets();
        if (!insets.equals(m.b)) {
            create = create.create(insets.left, insets.top, this.getWidth() - insets.left - insets.right, this.getHeight() - insets.top - insets.bottom);
        }
        this.a(create);
    }
    
    protected void a(final Graphics graphics) {
    }
    
    public Dimension getMinimumSize() {
        final Dimension b = this.b();
        final Border border = this.getBorder();
        if (b == null) {
            return super.getMinimumSize();
        }
        if (border == null) {
            return b;
        }
        final Insets insets = this.getInsets();
        return new Dimension(b.width + insets.left + insets.right, b.height + insets.top + insets.bottom);
    }
    
    public Dimension b() {
        return null;
    }
    
    public Dimension getPreferredSize() {
        final Dimension c = this.c();
        final Border border = this.getBorder();
        if (c == null) {
            return super.getPreferredSize();
        }
        if (border == null) {
            return c;
        }
        final Insets insets = this.getInsets();
        return new Dimension(c.width + insets.left + insets.right, c.height + insets.top + insets.bottom);
    }
    
    public Dimension c() {
        return null;
    }
    
    public Dimension getMaximumSize() {
        final Dimension d = this.d();
        final Border border = this.getBorder();
        if (d == null) {
            return super.getMaximumSize();
        }
        if (border == null) {
            return d;
        }
        final Insets insets = this.getInsets();
        return new Dimension(d.width + insets.left + insets.right, d.height + insets.top + insets.bottom);
    }
    
    public Dimension d() {
        return null;
    }
    
    protected void a(final MouseEvent mouseEvent) {
        super.processMouseEvent(mouseEvent);
    }
    
    protected void processMouseEvent(final MouseEvent mouseEvent) {
        if (this.getBorder() != null) {
            final Insets insets = this.getInsets();
            mouseEvent.translatePoint(-insets.left, -insets.top);
        }
        this.b(mouseEvent);
    }
    
    protected void b(final MouseEvent mouseEvent) {
    }
    
    protected void processMouseMotionEvent(final MouseEvent mouseEvent) {
        if (this.getBorder() != null) {
            final Insets insets = this.getInsets();
            mouseEvent.translatePoint(-insets.left, -insets.top);
        }
        this.c(mouseEvent);
    }
    
    protected void c(final MouseEvent mouseEvent) {
    }
    
    static {
        m.b = new Insets(0, 0, 0, 0);
    }
}
