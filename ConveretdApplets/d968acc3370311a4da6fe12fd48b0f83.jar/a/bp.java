// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.MenuItem;
import java.awt.Container;

public final class bp extends bl
{
    private dz q;
    private cP q;
    
    public bp(final Container container, final dz q) {
        super(container, q);
        this.q = q;
        this.addSeparator();
        final MenuItem menuItem;
        this.add(menuItem = new MenuItem(aJ.L));
        menuItem.addActionListener(this);
        final MenuItem menuItem2;
        this.add(menuItem2 = new MenuItem(aJ.Z));
        menuItem2.addActionListener(this);
        final MenuItem menuItem3;
        this.add(menuItem3 = new MenuItem(aJ.X));
        menuItem3.addActionListener(this);
        final MenuItem menuItem4;
        this.add(menuItem4 = new MenuItem(aJ.C));
        menuItem4.addActionListener(this);
        this.addSeparator();
        final MenuItem menuItem5;
        this.add(menuItem5 = new MenuItem(aJ.K));
        menuItem5.addActionListener(this);
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        this.q = ((am)this.q).q;
        final int q = this.q.q;
        final String q2 = be.q(this.q);
        if (actionCommand.equals(aJ.L)) {
            be.q(this.q, 4, this.q.f, this.q.w, q2);
            this.q.q(q);
            this.q.c.w(this.q.f);
            return;
        }
        if (actionCommand.equals(aJ.Z)) {
            be.q(this.q, 5, this.q.f, this.q.w, q2);
            be.q(this.q, this.q, this.q.w);
            return;
        }
        if (actionCommand.equals(aJ.X)) {
            be.q(this.q, 6, this.q.f, this.q.w, q2);
            be.w(this.q, this.q, be.q(this.q));
            return;
        }
        if (actionCommand.equals(aJ.C)) {
            be.q(this.q, 7, this.q.f, this.q.w, q2);
            be.q(this.q, this.q);
            return;
        }
        if (actionCommand.equals(aJ.K)) {
            be.q(this.q, 8, this.q.f, this.q.w, q2);
            be.q(this.q, this.q, this.q.w);
            return;
        }
        super.actionPerformed(actionEvent);
    }
    
    public final void q(final int n, final int n2, final Object o) {
        super.q(n + 20, n2 + 50, o);
    }
}
