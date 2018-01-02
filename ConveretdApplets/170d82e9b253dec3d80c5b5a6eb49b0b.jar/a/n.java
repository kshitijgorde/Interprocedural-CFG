// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.Panel;

public final class n extends Panel
{
    private dW q;
    protected int q;
    protected int w;
    private o q;
    protected V q;
    protected V w;
    protected Component q;
    
    public final void q() {
        this.w(0);
        this.q.e();
    }
    
    public final void q(final bZ bz) {
        this.q.q(bz);
    }
    
    public final void q(final int n) {
        this.w(this.q.q(n));
    }
    
    public final void w(int w) {
        final int q = this.q;
        this.q = w;
        if ((w = this.w()) == 0) {
            return;
        }
        if ((w *= this.q / w) != this.w) {
            this.w = w;
            this.q.repaint();
            return;
        }
        this.q.q(q - w);
        this.q.w(this.q - w);
    }
    
    public final bZ q() {
        try {
            return (bZ)this.q.q(this.q);
        }
        catch (Exception ex) {
            System.err.println("Icon with idx=" + this.q + " can't be found!");
            return new cm(0, "not exists");
        }
    }
    
    public final int q() {
        return this.q;
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
        final int q = this.q.q();
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
    
    protected final int w() {
        final Dimension q = this.q();
        return q.height * q.width;
    }
    
    public final void resize(final int n, final int n2) {
        this.q.resize(n - 2 * this.q.size().width, n2);
    }
    
    public final boolean action(final Event event, final Object o) {
        final int w = this.w();
        final int q = this.q.q();
        if (event.target == this.w) {
            this.w += w;
        }
        else {
            this.w -= w;
        }
        if (this.w >= q) {
            this.w = 0;
        }
        else if (this.w < 0) {
            this.w = q - 1 - (q - 1) % w;
        }
        final Graphics graphics = this.q.getGraphics();
        final Dimension size = this.q.size();
        graphics.setColor(this.q.getBackground());
        graphics.fillRect(0, 0, size.width, size.height);
        graphics.dispose();
        this.q.repaint();
        return true;
    }
    
    static dW q(final n n) {
        return n.q;
    }
    
    public n(final Component q) {
        this();
        this.q = q;
    }
    
    public n() {
        this.q = new dW();
        this.q = -1;
        this.w = 0;
        this.q = new o(this, this);
        this.q = new V(16, 30, 0);
        this.w = new V(16, 30, 1);
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
        this.q.q(eb.q("Click here to view more icons."), null);
        this.w.q(eb.q("Click here to view more icons."), null);
    }
}
