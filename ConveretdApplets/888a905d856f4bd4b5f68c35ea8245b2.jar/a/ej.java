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

public final class ej extends PopupMenu implements eb, ActionListener
{
    private Container q;
    private p q;
    private ap q;
    private bz q;
    private aG q;
    
    public ej(final Container q, final ap q2, final bz q3) {
        super("ConferenceUserPane");
        this.q = q;
        this.q = q2;
        this.q = q3;
        q.add(this);
        final MenuItem menuItem;
        this.add(menuItem = new MenuItem(dX.E));
        menuItem.addActionListener(this);
        final MenuItem menuItem2;
        this.add(menuItem2 = new MenuItem(dX.Y));
        menuItem2.addActionListener(this);
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand;
        if ((actionCommand = actionEvent.getActionCommand()).equals(dX.E)) {
            this.q.y(this.q.s);
            return;
        }
        if (actionCommand.equals(dX.Y)) {
            this.q.q("" + this.q.s, this.q.q, 5);
        }
    }
    
    public final void q(final int n, final int n2, final Object o) {
        this.q = (p)o;
        this.q = new aG(this.q, ((bp)o).s);
        if ((dN.q() && !bC.w.y()) || (dN.w() && !bC.w.i())) {
            this.show(this.q, n, n2 + 20);
        }
    }
    
    public final void q() {
    }
}
