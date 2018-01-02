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

public final class bk implements bi, ActionListener
{
    private cV q;
    private cz q;
    private dP q;
    
    public bk(final cV q, final cz q2) {
        this.q = q;
        this.q = q2;
        this.q = new dP(q, q2.q());
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand;
        if ((actionCommand = actionEvent.getActionCommand()).equals(aJ.R)) {
            this.q.e(this.q.q());
            return;
        }
        if (actionCommand.equals(aJ.T)) {
            this.q.q.q(this.q);
            return;
        }
        if (actionCommand.equals(aJ.Y)) {
            this.q.q(this.q);
            return;
        }
        if (actionCommand.equals(aJ.U)) {
            new af(this.q, this.q);
        }
    }
    
    public final MenuBar q(final Frame frame) {
        final Menu menu = new Menu(a.e);
        final MenuBar menuBar = new MenuBar();
        final MenuItem menuItem;
        menu.add(menuItem = new MenuItem(aJ.R));
        menuItem.addActionListener(this);
        if (this.q.a_() || !this.q.a_()) {
            final MenuItem menuItem2;
            menu.add(menuItem2 = new MenuItem(aJ.T));
            menuItem2.addActionListener(this);
        }
        if (a.w() && (this.q.a_() || !this.q.a_())) {
            final MenuItem menuItem3;
            menu.add(menuItem3 = new MenuItem(aJ.Y));
            menuItem3.addActionListener(this);
        }
        else if (a.q()) {
            final MenuItem menuItem4;
            menu.add(menuItem4 = new MenuItem(aJ.U));
            menuItem4.addActionListener(this);
        }
        menuBar.add(menu);
        return menuBar;
    }
    
    public final void q() {
    }
}
