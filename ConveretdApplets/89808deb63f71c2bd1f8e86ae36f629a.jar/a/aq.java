// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Rectangle;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.MenuItem;
import java.awt.Event;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowEvent;
import java.awt.Insets;

public final class aq extends aV
{
    bb q;
    bj q;
    
    public final Insets insets() {
        final Insets insets = super.insets();
        return new Insets(insets.top - 3, insets.left, insets.bottom, insets.right);
    }
    
    public final void windowClosing(final WindowEvent windowEvent) {
        super.windowClosing(windowEvent);
        this.q.q.y();
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
            this.q.q.y();
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
    
    public aq(final W w, final ag ag) {
        super(true);
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(layout);
        this.q = new bb(w, this);
        final Dimension dimension = new Dimension(800, 600);
        if (aU.w.r() > 800) {
            dimension.width = aU.w.r();
        }
        if (aU.w.t() > 600) {
            dimension.height = aU.w.t();
        }
        if (h.q().q > 600) {
            dimension.height = h.q().q;
        }
        if (h.q().w > 800) {
            dimension.width = h.q().w;
        }
        this.setSize(dimension.width, dimension.height);
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final Dimension size = this.size();
        int n;
        if ((n = (screenSize.height - size.height) / 2) > 100) {
            n = 100;
        }
        this.move((screenSize.width - size.width) / 2, n);
        final Rectangle bounds = this.getBounds();
        this.setBounds(bounds.x, bounds.y, bounds.width, bounds.height);
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 2;
        if (bN.q()) {
            this.setMenuBar(((bk)ag).q(this));
        }
        else {
            (this.q = (bj)ag).q(this);
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
        if (cu.q()) {
            bv.q(this);
        }
    }
}
