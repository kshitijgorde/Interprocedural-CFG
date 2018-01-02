// 
// Decompiled by Procyon v0.5.30
// 

package chat;

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

public final class aJ extends k
{
    af a;
    
    public final boolean handleEvent(final Event event) {
        if (event.target instanceof MenuItem) {
            return this.a.handleEvent(event);
        }
        if (event.id == 201) {
            this.a.a.c();
        }
        if (event.id == 1004 && event.target == this.a) {
            this.a.a.requestFocus();
        }
        return super.handleEvent(event);
    }
    
    public aJ(final bl bl) {
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(layout);
        this.a = new af(bl, this);
        final Dimension screenSize;
        if ((screenSize = Toolkit.getDefaultToolkit().getScreenSize()).width > 1024) {
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
        dimension2.height -= 80 - (bl.a(52) ? 30 : 10);
        if (aV.a > 0) {
            screenSize.height = aV.a;
        }
        if (aV.b > 0) {
            screenSize.width = aV.b;
        }
        this.resize(screenSize.width, screenSize.height);
        final Dimension screenSize2 = Toolkit.getDefaultToolkit().getScreenSize();
        final Dimension size = this.size();
        int n;
        if ((n = (screenSize2.height - size.height) / 2) > 100) {
            n = 100;
        }
        this.move((screenSize2.width - size.width) / 2, n);
        final Rectangle bounds = this.bounds();
        this.move(bounds.x, bounds.y);
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
