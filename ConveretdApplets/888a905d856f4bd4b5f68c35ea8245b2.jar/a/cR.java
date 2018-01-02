// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.MenuItem;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class cR implements ch, ActionListener
{
    private ap q;
    private p q;
    private aG q;
    
    public cR(final ap q, final p q2) {
        this.q = q;
        this.q = q2;
        this.q = new aG(q, q2.s);
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand;
        if ((actionCommand = actionEvent.getActionCommand()).equals(dX.E)) {
            this.q.y(this.q.s);
            return;
        }
        if (actionCommand.equals(dX.R)) {
            this.q.q.q(this.q);
            return;
        }
        if (actionCommand.equals(dX.T)) {
            this.q.q(this.q);
            return;
        }
        if (actionCommand.equals(dX.Y)) {
            new J(this.q, this.q);
        }
    }
    
    public final MenuBar q(final Frame frame) {
        final Menu menu = new Menu(dN.e);
        final MenuBar menuBar = new MenuBar();
        final MenuItem menuItem;
        menu.add(menuItem = new MenuItem(dX.E));
        menuItem.addActionListener(this);
        if (this.q.q(61) || !this.q.q(61)) {
            final MenuItem menuItem2;
            menu.add(menuItem2 = new MenuItem(dX.R));
            menuItem2.addActionListener(this);
        }
        if (dN.w() && (this.q.q(61) || !this.q.q(61))) {
            final MenuItem menuItem3;
            menu.add(menuItem3 = new MenuItem(dX.T));
            menuItem3.addActionListener(this);
        }
        else if (dN.q()) {
            final MenuItem menuItem4;
            menu.add(menuItem4 = new MenuItem(dX.Y));
            menuItem4.addActionListener(this);
        }
        menuBar.add(menu);
        return menuBar;
    }
    
    public final void q() {
    }
}
