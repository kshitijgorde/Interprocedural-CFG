// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Rectangle;
import java.awt.Component;
import java.awt.Frame;
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

public final class cO extends u
{
    private cT q;
    private aN q;
    
    public final Insets insets() {
        final Insets insets = super.insets();
        return new Insets(insets.top - 3, insets.left, insets.bottom, insets.right);
    }
    
    public final Container q() {
        return this.q;
    }
    
    public final aN q() {
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
    
    public final void q(final db db) {
        String s;
        final int lastIndex;
        if ((lastIndex = (s = this.q.q.getName()).lastIndexOf(".")) >= 0 && lastIndex + 4 == s.length()) {
            s = s.substring(0, lastIndex);
        }
        this.setTitle(a.e + ": " + db.getName() + ": " + s);
    }
    
    public cO(final cV cv, final aI ai) {
        super(true);
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(layout);
        this.q = new cT(cv, this);
        final Dimension dimension = new Dimension(700, 500);
        if (cf.w.w() > 700) {
            dimension.width = cf.w.w();
        }
        if (cf.w.e() > 500) {
            dimension.height = cf.w.e();
        }
        if (AppletAbstract.q().q > 500) {
            dimension.height = AppletAbstract.q().q;
        }
        if (AppletAbstract.q().w > 700) {
            dimension.width = AppletAbstract.q().w;
        }
        this.setSize(dimension.width, dimension.height);
        this.a_();
        final Rectangle bounds = this.getBounds();
        this.setBounds(bounds.x, bounds.y, bounds.width, bounds.height);
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 2;
        if (aG.q()) {
            this.setMenuBar(((bi)ai).q(this));
        }
        else {
            (this.q = (aN)ai).q(this);
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
            cx.q(this);
        }
    }
}
