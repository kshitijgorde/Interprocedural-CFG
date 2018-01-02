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

public final class bK implements bj, ActionListener
{
    private W q;
    private l q;
    private T q;
    
    public bK(final W q, final l q2) {
        this.q = q;
        this.q = q2;
        this.q = new T(q, q2.a);
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand;
        if ((actionCommand = actionEvent.getActionCommand()).equals(cy.f)) {
            this.q.r(this.q.a);
            return;
        }
        if (actionCommand.equals(cy.g)) {
            this.q.q.q(this.q);
            return;
        }
        if (actionCommand.equals(cy.h)) {
            this.q.q(this.q);
            return;
        }
        if (actionCommand.equals(cy.j)) {
            new z(this.q, this.q);
        }
    }
    
    public final MenuBar q(final Frame frame) {
        final Menu menu = new Menu(cs.e);
        final MenuBar menuBar = new MenuBar();
        final MenuItem menuItem;
        menu.add(menuItem = new MenuItem(cy.f));
        menuItem.addActionListener(this);
        if (this.q.q(61) || !this.q.q(61)) {
            final MenuItem menuItem2;
            menu.add(menuItem2 = new MenuItem(cy.g));
            menuItem2.addActionListener(this);
        }
        if (cs.w() && (this.q.q(61) || !this.q.q(61))) {
            final MenuItem menuItem3;
            menu.add(menuItem3 = new MenuItem(cy.h));
            menuItem3.addActionListener(this);
        }
        else if (cs.q()) {
            final MenuItem menuItem4;
            menu.add(menuItem4 = new MenuItem(cy.j));
            menuItem4.addActionListener(this);
        }
        menuBar.add(menu);
        return menuBar;
    }
    
    public final void q() {
    }
}
