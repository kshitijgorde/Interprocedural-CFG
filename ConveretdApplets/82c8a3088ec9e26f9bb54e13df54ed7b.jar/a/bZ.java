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

public final class bZ extends PopupMenu implements cC, ActionListener
{
    private Container q;
    private r q;
    private W q;
    
    public bZ(final Container q, final W q2) {
        super("MainPanel");
        this.q = q;
        this.q = q2;
        final MenuItem menuItem;
        this.add(menuItem = new MenuItem(cz.s));
        menuItem.addActionListener(this);
        final MenuItem menuItem2;
        this.add(menuItem2 = new MenuItem(cz.d));
        menuItem2.addActionListener(this);
        if (cs.q() && q2 != null && (q2.q(49) || q2.q(1))) {
            this.addSeparator();
            final MenuItem menuItem3;
            this.add(menuItem3 = new MenuItem(cz.Q));
            menuItem3.addActionListener(this);
        }
        q.add(this);
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand;
        if ((actionCommand = actionEvent.getActionCommand()).equals(cz.s)) {
            String string = "";
            if (this.q.q != null) {
                string = this.q.q.o + ", ";
            }
            bm.q(string + this.q.e + bm.q + this.q.q, this.q.q);
            return;
        }
        if (actionCommand.equals(cz.d)) {
            bm.q(this.q.q, this.q.q);
            return;
        }
        if (actionCommand.equals(cz.Q)) {
            if (this.q.q != null) {
                new bR(this.q.q(), s.q(ak.q("User: %1"), this.q.w), "IP: " + this.q.q.y + "\nHost: " + this.q.q.u, this.q).setVisible(true);
                return;
            }
            new bR(this.q.q(), ak.q("Info"), ak.q("User message should be choosen"), this.q).setVisible(true);
        }
    }
    
    public final void q(final int n, final int n2, final Object o) {
        this.q = (r)o;
        if ((cs.q() && !aS.w.u()) || (cs.w() && !aS.w.o())) {
            this.show(this.q, n, n2);
        }
    }
    
    public final void q() {
    }
}
