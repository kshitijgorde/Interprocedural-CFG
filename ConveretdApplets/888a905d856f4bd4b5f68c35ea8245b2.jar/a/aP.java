// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Rectangle;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.MenuItem;
import java.awt.Event;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowEvent;
import java.awt.Insets;

public final class aP extends bE
{
    public bT q;
    public cg q;
    
    public final Insets insets() {
        final Insets insets = super.insets();
        return new Insets(insets.top - 3, insets.left, insets.bottom, insets.right);
    }
    
    public final void windowClosing(final WindowEvent windowEvent) {
        super.windowClosing(windowEvent);
        this.q.q.u();
    }
    
    public final void componentResized(final ComponentEvent componentEvent) {
        super.componentResized(componentEvent);
        if (this.q != null) {
            this.q.q();
        }
    }
    
    public final boolean handleEvent(final Event event) {
        if (event.target instanceof MenuItem) {
            return this.q.handleEvent(event);
        }
        if (event.id == 201) {
            this.q.q.u();
        }
        if (event.id == 1004 && event.target == this.q) {
            this.q.q.q.requestFocus();
        }
        if (event.id == 7688) {
            if (this.q != null) {
                this.q.q();
            }
            this.validate();
            this.repaint();
        }
        return super.handleEvent(event);
    }
    
    public aP(final ap ap, final az az) {
        super(true);
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(layout);
        this.q = new bT(ap, this);
        final Dimension dimension = new Dimension(800, 600);
        if (bC.w.r() > 800) {
            dimension.width = bC.w.r();
        }
        if (bC.w.t() > 600) {
            dimension.height = bC.w.t();
        }
        if (m.q().q > 600) {
            dimension.height = m.q().q;
        }
        if (m.q().w > 800) {
            dimension.width = m.q().w;
        }
        this.setSize(dimension.width, dimension.height);
        this.e();
        final Rectangle bounds = this.getBounds();
        this.setBounds(bounds.x, bounds.y, bounds.width, bounds.height);
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 2;
        if (cU.q()) {
            this.setMenuBar(((ch)az).q(this));
        }
        else {
            (this.q = (cg)az).q(this);
            this.q.q(this.getSize().width, this.getSize().height);
            this.add(this.q.q(), gridBagConstraints);
        }
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.gridheight = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        layout.setConstraints(this.q, gridBagConstraints);
        this.add(this.q);
        this.show();
        this.q.setVisible(true);
        if (dN.q()) {
            cx.q(this);
        }
    }
}
