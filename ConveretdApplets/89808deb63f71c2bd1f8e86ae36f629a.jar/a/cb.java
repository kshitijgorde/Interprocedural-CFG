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

public final class cb extends PopupMenu implements cE, ActionListener
{
    private Container q;
    private r q;
    private W q;
    
    public cb(final Container q, final W q2) {
        super("MainPanel");
        this.q = q;
        this.q = q2;
        final MenuItem menuItem;
        this.add(menuItem = new MenuItem(cB.s));
        menuItem.addActionListener(this);
        final MenuItem menuItem2;
        this.add(menuItem2 = new MenuItem(cB.d));
        menuItem2.addActionListener(this);
        if (cu.q() && q2 != null && (q2.q(49) || q2.q(1))) {
            this.addSeparator();
            final MenuItem menuItem3;
            this.add(menuItem3 = new MenuItem(cB.Q));
            menuItem3.addActionListener(this);
        }
        q.add(this);
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand;
        if ((actionCommand = actionEvent.getActionCommand()).equals(cB.s)) {
            String string = "";
            if (this.q.q != null) {
                string = this.q.q.o + ", ";
            }
            bo.q(string + this.q.e + bo.q + this.q.q, this.q.q);
            return;
        }
        if (actionCommand.equals(cB.d)) {
            bo.q(this.q.q, this.q.q);
            return;
        }
        if (actionCommand.equals(cB.Q)) {
            if (this.q.q != null) {
                new bT(this.q.q(), s.q(ak.q("User: %1"), this.q.w), "IP: " + this.q.q.y + "\nHost: " + this.q.q.u, this.q).setVisible(true);
                return;
            }
            new bT(this.q.q(), ak.q("Info"), ak.q("User message should be choosen"), this.q).setVisible(true);
        }
    }
    
    public final void q(final int n, final int n2, final Object o) {
        this.q = (r)o;
        if ((cu.q() && !aU.w.u()) || (cu.w() && !aU.w.o())) {
            this.show(this.q, n, n2);
        }
    }
    
    public final void q() {
    }
}
