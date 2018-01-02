// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.MenuItem;
import java.awt.Container;

public final class dc extends dn
{
    private cT q;
    private bk q;
    
    public dc(final Container container, final cT q) {
        super(container, q);
        this.q = q;
        this.addSeparator();
        final MenuItem menuItem;
        this.add(menuItem = new MenuItem(dX.J));
        menuItem.addActionListener(this);
        final MenuItem menuItem2;
        this.add(menuItem2 = new MenuItem(dX.K));
        menuItem2.addActionListener(this);
        final MenuItem menuItem3;
        this.add(menuItem3 = new MenuItem(dX.L));
        menuItem3.addActionListener(this);
        final MenuItem menuItem4;
        this.add(menuItem4 = new MenuItem(dX.Z));
        menuItem4.addActionListener(this);
        this.addSeparator();
        final MenuItem menuItem5;
        this.add(menuItem5 = new MenuItem(dX.H));
        menuItem5.addActionListener(this);
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        this.q = ((z)this.q).q;
        final int q = this.q.q;
        final String e = this.q.e;
        if (actionCommand.equals(dX.J)) {
            aT.q(this.q, 4, this.q.f, this.q.w, e);
            this.q.q(q);
            this.q.q.w(this.q.f);
            return;
        }
        if (actionCommand.equals(dX.K)) {
            aT.q(this.q, 5, this.q.f, this.q.w, e);
            aT.q(this.q, this.q, this.q.w);
            return;
        }
        if (actionCommand.equals(dX.L)) {
            aT.q(this.q, 6, this.q.f, this.q.w, e);
            aT.w(this.q, this.q, this.q.e);
            return;
        }
        if (actionCommand.equals(dX.Z)) {
            aT.q(this.q, 7, this.q.f, this.q.w, e);
            aT.q(this.q, this.q);
            return;
        }
        if (actionCommand.equals(dX.H)) {
            aT.q(this.q, 8, this.q.f, this.q.w, e);
            aT.q(this.q, this.q, this.q.w);
            return;
        }
        super.actionPerformed(actionEvent);
    }
    
    public final void q(final int n, final int n2, final Object o) {
        super.q(n + 20, n2 + 50, o);
    }
}
