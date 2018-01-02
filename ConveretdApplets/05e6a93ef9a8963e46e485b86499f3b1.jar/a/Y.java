// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Event;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.Panel;

public final class Y extends Panel
{
    B q;
    protected int q;
    protected int w;
    private bo q;
    protected K q;
    protected K w;
    protected Component q;
    
    public final void q() {
        this.w(0);
        this.q.q();
    }
    
    public final void q(final aK ak) {
        this.q.q(ak);
    }
    
    public final void q(final int n) {
        this.w(this.q.q(n));
    }
    
    public final void w(int q) {
        final int q2 = this.q;
        this.q = q;
        if ((q = this.q()) == 0) {
            return;
        }
        if ((q *= this.q / q) != this.w) {
            this.w = q;
            this.q.repaint();
            return;
        }
        final bo q3 = this.q;
        final int n = q2 - q;
        final bo bo = q3;
        final Graphics graphics;
        if ((graphics = q3.getGraphics()) != null) {
            final Rectangle q4;
            int x = (q4 = bo.q(n)).x;
            int y = q4.y;
            int height = q4.height;
            int width = q4.width;
            graphics.setColor(bo.getBackground());
            for (int i = 0; i <= 4; ++i) {
                graphics.drawRect(x, y, width, height);
                ++x;
                ++y;
                height -= 2;
                width -= 2;
            }
        }
        this.q.q(this.q - q);
    }
    
    public final aK q() {
        try {
            return (aK)this.q.q(this.q);
        }
        catch (Exception ex) {
            System.err.println("Icon with idx=" + this.q + " can't be found!");
            return new aw(0, "not exists");
        }
    }
    
    protected final Dimension q() {
        final Dimension size;
        int n = (size = this.q.size()).width / 43;
        int n2 = size.height / 41;
        if (n == 0) {
            n = 1;
        }
        if (n2 == 0) {
            n2 = 1;
        }
        final int q = this.q.q;
        if (n * n2 > q) {
            for (int i = (int)Math.ceil(Math.sqrt(q)); i < n; ++i) {
                final int n3;
                if ((n3 = (int)Math.ceil(q / i)) <= n2) {
                    n = i;
                    n2 = n3;
                    break;
                }
            }
        }
        return new Dimension(n, n2);
    }
    
    protected final int q() {
        final Dimension q = this.q();
        return q.height * q.width;
    }
    
    public final void resize(final int n, final int n2) {
        this.q.resize(n - 2 * this.q.size().width, n2);
    }
    
    public final boolean action(final Event event, final Object o) {
        final int q = this.q();
        final int q2 = this.q.q;
        if (event.target == this.w) {
            this.w += q;
        }
        else {
            this.w -= q;
        }
        if (this.w >= q2) {
            this.w = 0;
        }
        else if (this.w < 0) {
            this.w = q2 - 1 - (q2 - 1) % q;
        }
        final Graphics graphics = this.q.getGraphics();
        final Dimension size = this.q.size();
        graphics.setColor(this.q.getBackground());
        graphics.fillRect(0, 0, size.width, size.height);
        graphics.dispose();
        this.q.repaint();
        return true;
    }
    
    public Y(final Component q) {
        this();
        this.q = q;
    }
    
    public Y() {
        this.q = new B();
        this.q = -1;
        this.w = 0;
        this.q = new bo(this, this);
        this.q = new K(16, 30, 0);
        this.w = new K(16, 30, 1);
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(layout);
        gridBagConstraints.gridheight = 0;
        layout.setConstraints(this.q, gridBagConstraints);
        this.add(this.q);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = 1;
        layout.setConstraints(this.q, gridBagConstraints);
        this.add(this.q);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.fill = 0;
        layout.setConstraints(this.w, gridBagConstraints);
        this.add(this.w);
        this.q.q(al.q("Click here to view more icons."), null);
        this.w.q(al.q("Click here to view more icons."), null);
    }
}
