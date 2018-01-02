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

public final class bJ implements bi, ActionListener
{
    private W q;
    private l q;
    private S q;
    
    public bJ(final W q, final l q2) {
        this.q = q;
        this.q = q2;
        this.q = new S(q, q2.s);
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand;
        if ((actionCommand = actionEvent.getActionCommand()).equals(cz.f)) {
            this.q.r(this.q.s);
            return;
        }
        if (actionCommand.equals(cz.g)) {
            this.q.q.q(this.q);
            return;
        }
        if (actionCommand.equals(cz.h)) {
            this.q.q(this.q);
            return;
        }
        if (actionCommand.equals(cz.j)) {
            new y(this.q, this.q);
        }
    }
    
    public final MenuBar q(final Frame frame) {
        final Menu menu = new Menu(cs.e);
        final MenuBar menuBar = new MenuBar();
        final MenuItem menuItem;
        menu.add(menuItem = new MenuItem(cz.f));
        menuItem.addActionListener(this);
        if (this.q.q(61) || !this.q.q(61)) {
            final MenuItem menuItem2;
            menu.add(menuItem2 = new MenuItem(cz.g));
            menuItem2.addActionListener(this);
        }
        if (cs.w() && (this.q.q(61) || !this.q.q(61))) {
            final MenuItem menuItem3;
            menu.add(menuItem3 = new MenuItem(cz.h));
            menuItem3.addActionListener(this);
        }
        else if (cs.q()) {
            final MenuItem menuItem4;
            menu.add(menuItem4 = new MenuItem(cz.j));
            menuItem4.addActionListener(this);
        }
        menuBar.add(menu);
        return menuBar;
    }
    
    public final void q() {
    }
}
