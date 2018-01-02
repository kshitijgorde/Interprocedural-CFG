// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Rectangle;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Toolkit;
import com.spilka.client.muc.AppletAbstract;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.MenuItem;
import java.awt.Event;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowEvent;
import java.awt.Container;
import java.awt.Insets;

public final class bB extends s
{
    private bG q;
    private ay q;
    
    public final Insets insets() {
        final Insets insets = super.insets();
        return new Insets(insets.top - 3, insets.left, insets.bottom, insets.right);
    }
    
    public final Container q() {
        return this.q;
    }
    
    public final ay q() {
        return this.q;
    }
    
    public final void windowClosing(final WindowEvent windowEvent) {
        super.windowClosing(windowEvent);
        this.q.q.w();
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
            this.q.q.w();
        }
        if (event.id == 1004 && event.target == this.q) {
            this.q.q().requestFocus();
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
    
    public final void q(final bO bo) {
        String s;
        final int lastIndex;
        if ((lastIndex = (s = this.q.q.getName()).lastIndexOf(".")) >= 0 && lastIndex + 4 == s.length()) {
            s = s.substring(0, lastIndex);
        }
        this.setTitle(a.e + ": " + bo.getName() + ": " + s);
    }
    
    public bB(final bI bi, final at at) {
        super(true);
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(layout);
        this.q = new bG(bi, this);
        final Dimension dimension = new Dimension(700, 500);
        if (be.w.y() > 700) {
            dimension.width = be.w.y();
        }
        if (be.w.u() > 500) {
            dimension.height = be.w.u();
        }
        if (AppletAbstract.q().q > 500) {
            dimension.height = AppletAbstract.q().q;
        }
        if (AppletAbstract.q().w > 700) {
            dimension.width = AppletAbstract.q().w;
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
        if (ar.q()) {
            this.setMenuBar(((aR)at).q(this));
        }
        else {
            (this.q = (ay)at).q(this);
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
        if (a.q()) {
            bn.q(this);
        }
    }
}
