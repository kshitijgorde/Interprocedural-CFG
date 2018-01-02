// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.MenuItem;
import java.awt.Event;
import java.awt.Container;

public class ba extends ac
{
    private static int c;
    private bj a;
    private at i;
    
    public Container b() {
        return this.a;
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 401 && event.modifiers == 2 && event.key == 4 && as.a != null) {
            as.a(this.i);
            return true;
        }
        if (event.target instanceof MenuItem) {
            return this.a.handleEvent(event);
        }
        if (event.id == 201) {
            this.a.g.n();
        }
        if (event.id == 1004 && event.target == this.a) {
            this.a.d.requestFocus();
        }
        return super.handleEvent(event);
    }
    
    public void c(final bn bn) {
        this.setTitle(bi.Q + ": " + bn.d() + ": " + this.a.g.d());
    }
    
    public ba(final at i) {
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(layout);
        ++ba.c;
        this.i = i;
        this.a = new bj(this.i, this);
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        if (screenSize.width > 1024) {
            screenSize.width = 1024;
        }
        else if (screenSize.width > 800) {
            screenSize.width = 800;
        }
        if (screenSize.height > 768) {
            screenSize.height = 768;
        }
        else if (screenSize.height > 600) {
            screenSize.height = 600;
        }
        final Dimension dimension = screenSize;
        dimension.width -= 80;
        final Dimension dimension2 = screenSize;
        dimension2.height -= 80;
        if (bi.ar > 0) {
            screenSize.height = bi.ar;
        }
        if (bi.as > 0) {
            screenSize.width = bi.as;
        }
        this.resize(screenSize.width, screenSize.height);
        this.k();
        final Rectangle bounds = this.bounds();
        this.move(bounds.x + ba.c * 20, bounds.y + ba.c * 20);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.gridheight = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        layout.setConstraints(this.a, gridBagConstraints);
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        this.add(this.a);
        this.show();
        this.a.setVisible(true);
    }
}
