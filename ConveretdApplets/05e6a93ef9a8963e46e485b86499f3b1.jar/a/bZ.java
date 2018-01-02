// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.MenuItem;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.PopupMenu;

public final class bZ extends PopupMenu implements cB, ActionListener
{
    private Container q;
    private s q;
    private W q;
    
    public bZ(final Container q, final W q2) {
        super("MainPanel");
        this.q = q;
        this.q = q2;
        final MenuItem menuItem;
        this.add(menuItem = new MenuItem(cy.s));
        menuItem.addActionListener(this);
        final MenuItem menuItem2;
        this.add(menuItem2 = new MenuItem(cy.d));
        menuItem2.addActionListener(this);
        if (cs.q() && q2 != null && (q2.q(49) || q2.q(1))) {
            this.addSeparator();
            final MenuItem menuItem3;
            this.add(menuItem3 = new MenuItem(cy.Q));
            menuItem3.addActionListener(this);
        }
        q.add(this);
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand;
        if ((actionCommand = actionEvent.getActionCommand()).equals(cy.s)) {
            String string = "";
            if (this.q.q != null) {
                string = this.q.q.o + ", ";
            }
            bn.q(string + this.q.e + bn.q + this.q.q, this.q.q);
            return;
        }
        if (actionCommand.equals(cy.d)) {
            bn.q(this.q.q, this.q.q);
            return;
        }
        if (actionCommand.equals(cy.Q)) {
            if (this.q.q != null) {
                new p(this.q.q(), t.q(al.q("User: %1"), this.q.w), "IP: " + this.q.q.y + "\nHost: " + this.q.q.u, this.q).setVisible(true);
                return;
            }
            new p(this.q.q(), al.q("Info"), al.q("User message should be choosen"), this.q).setVisible(true);
        }
    }
    
    public final void q(final int n, final int n2, final Object o) {
        this.q = (s)o;
        if ((cs.q() && !aT.w.u()) || (cs.w() && !aT.w.o())) {
            this.show(this.q, n, n2);
        }
    }
    
    public final void q() {
    }
}
