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

public final class aU extends PopupMenu implements aQ, ActionListener
{
    private Container q;
    private bF q;
    
    public aU(final Container q, final bI bi) {
        super("MainPanel");
        this.q = q;
        final MenuItem menuItem;
        this.add(menuItem = new MenuItem(au.s));
        menuItem.addActionListener(this);
        final MenuItem menuItem2;
        this.add(menuItem2 = new MenuItem(au.d));
        menuItem2.addActionListener(this);
        q.add(this);
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand;
        if ((actionCommand = actionEvent.getActionCommand()).equals(au.s)) {
            String string = "";
            if (this.q.q != null) {
                string = this.q.q.getName() + ", ";
            }
            cu.q(string + this.q.e + cu.q + this.q.q, this.q.q);
            return;
        }
        if (actionCommand.equals(au.d)) {
            cu.q(this.q.q, this.q.q);
        }
    }
    
    public final void q(final int n, final int n2, final Object o) {
        this.q = (bF)o;
        if ((a.q() && !be.w.u()) || (a.w() && !be.w.o())) {
            this.show(this.q, n, n2);
        }
    }
    
    public final void q() {
    }
}
