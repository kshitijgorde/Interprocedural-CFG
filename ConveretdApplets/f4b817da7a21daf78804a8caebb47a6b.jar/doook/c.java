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

public class c extends X
{
    private static int e;
    private O a;
    private aW a;
    
    public Container a() {
        return this.a;
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 401 && event.modifiers == 2 && event.key == 4 && be.a != null) {
            be.a(this.a);
            return true;
        }
        if (event.target instanceof MenuItem) {
            return this.a.handleEvent(event);
        }
        if (event.id == 201) {
            this.a.a.i();
        }
        if (event.id == 1004 && event.target == this.a) {
            this.a.b.requestFocus();
        }
        return super.handleEvent(event);
    }
    
    public void a(final ax ax) {
        this.setTitle(t.a + ": " + ax.g() + ": " + this.a.a.g());
    }
    
    public c(final aW a) {
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(layout);
        ++c.e;
        this.a = a;
        this.a = new O(this.a, this);
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
        if (t.b > 0) {
            screenSize.height = t.b;
        }
        if (t.c > 0) {
            screenSize.width = t.c;
        }
        this.resize(screenSize.width, screenSize.height);
        this.c();
        final Rectangle bounds = this.bounds();
        this.move(bounds.x + c.e * 20, bounds.y + c.e * 20);
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
