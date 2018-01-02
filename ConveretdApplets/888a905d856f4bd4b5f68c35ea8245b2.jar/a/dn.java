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

public class dn extends PopupMenu implements eb, ActionListener
{
    protected Container q;
    protected A q;
    protected ap q;
    
    public dn(final Container q, final ap q2) {
        super("MainPanel");
        this.q = q;
        this.q = q2;
        final MenuItem menuItem;
        this.add(menuItem = new MenuItem(dX.Q));
        menuItem.addActionListener(this);
        final MenuItem menuItem2;
        this.add(menuItem2 = new MenuItem(dX.W));
        menuItem2.addActionListener(this);
        if (dN.q() && q2 != null && (q2.q(49) || q2.q(1))) {
            this.addSeparator();
            final MenuItem menuItem3;
            this.add(menuItem3 = new MenuItem(dX.X));
            menuItem3.addActionListener(this);
        }
        q.add(this);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand;
        if ((actionCommand = actionEvent.getActionCommand()).equals(dX.Q)) {
            String string = "";
            if (this.q.q != null) {
                string = this.q.q.a + ", ";
            }
            cm.q(string + this.q.e + cm.q + this.q.q, this.q.q);
            return;
        }
        if (actionCommand.equals(dX.W)) {
            cm.q(this.q.q, this.q.q);
            return;
        }
        if (actionCommand.equals(dX.X)) {
            if (this.q.q != null) {
                new dd(this.q.q(), B.q(be.w("User: %1"), this.q.w), "IP: " + this.q.q.u + "\nHost: " + this.q.q.i, this.q).setVisible(true);
                return;
            }
            new dd(this.q.q(), be.w("Info"), be.w("User message should be choosen"), this.q).setVisible(true);
        }
    }
    
    public void q(final int n, final int n2, final Object o) {
        this.q = (A)o;
        if ((dN.q() && !bC.w.u()) || (dN.w() && !bC.w.o())) {
            this.show(this.q, n, n2);
        }
    }
    
    public final void q() {
    }
}
