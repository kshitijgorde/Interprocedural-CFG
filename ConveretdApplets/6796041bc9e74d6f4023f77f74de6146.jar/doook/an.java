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

public class an extends aO
{
    private static int g;
    private S b;
    private u i;
    
    public Container b() {
        return this.b;
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 401 && event.modifiers == 2 && t.a(event.key, this.i)) {
            return true;
        }
        if (event.target instanceof MenuItem) {
            return this.b.handleEvent(event);
        }
        if (event.id == 201) {
            this.b.a.i();
        }
        if (event.id == 1004 && event.target == this.b) {
            this.b.a.requestFocus();
        }
        return super.handleEvent(event);
    }
    
    public void c(final T t) {
        this.setTitle(z.G + ": " + t.f() + ": " + this.b.a.f());
    }
    
    public an(final u i) {
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(layout);
        ++an.g;
        this.i = i;
        this.b = new S(i, this);
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
        if (z.V > 0) {
            screenSize.height = z.V;
        }
        if (z.W > 0) {
            screenSize.width = z.W;
        }
        this.resize(screenSize.width, screenSize.height);
        this.c();
        final Rectangle bounds = this.bounds();
        this.move(bounds.x + an.g * 20, bounds.y + an.g * 20);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.gridheight = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        layout.setConstraints(this.b, gridBagConstraints);
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        this.add(this.b);
        this.show();
        this.b.setVisible(true);
    }
}
