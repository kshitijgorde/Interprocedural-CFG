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

public final class cG extends PopupMenu implements cB, ActionListener
{
    private Container q;
    private l q;
    private W q;
    private aP q;
    private T q;
    
    public cG(final Container q, final W q2, final aP q3) {
        super("ConferenceUserPane");
        this.q = q;
        this.q = q2;
        this.q = q3;
        q.add(this);
        final MenuItem menuItem;
        this.add(menuItem = new MenuItem(cy.f));
        menuItem.addActionListener(this);
        final MenuItem menuItem2;
        this.add(menuItem2 = new MenuItem(cy.j));
        menuItem2.addActionListener(this);
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand;
        if ((actionCommand = actionEvent.getActionCommand()).equals(cy.f)) {
            this.q.r(this.q.a);
            return;
        }
        if (actionCommand.equals(cy.j)) {
            this.q.q("" + this.q.a, this.q.q, 5);
        }
    }
    
    public final void q(final int n, final int n2, final Object o) {
        this.q = (l)o;
        this.q = new T(this.q, ((aK)o).a);
        if ((cs.q() && !aT.w.y()) || (cs.w() && !aT.w.i())) {
            this.show(this.q, n, n2 + 20);
        }
    }
    
    public final void q() {
    }
}
