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

public final class aV extends PopupMenu implements aQ, ActionListener
{
    private Container q;
    private bp q;
    private bI q;
    private J q;
    private cj q;
    
    public aV(final Container q, final bI q2, final J q3) {
        super("ConferenceUserPane");
        this.q = q;
        this.q = q2;
        this.q = q3;
        q.add(this);
        final MenuItem menuItem;
        this.add(menuItem = new MenuItem(au.f));
        menuItem.addActionListener(this);
        final MenuItem menuItem2;
        this.add(menuItem2 = new MenuItem(au.j));
        menuItem2.addActionListener(this);
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand;
        if ((actionCommand = actionEvent.getActionCommand()).equals(au.f)) {
            this.q.e(this.q.q());
            return;
        }
        if (actionCommand.equals(au.j)) {
            this.q.q("" + this.q.q(), this.q.q(), 5);
        }
    }
    
    public final void q(final int n, final int n2, final Object o) {
        this.q = (bp)o;
        this.q = new cj(this.q, ((ba)o).q());
        if ((a.q() && !be.w.y()) || (a.w() && !be.w.i())) {
            this.show(this.q, n, n2 + 20);
        }
    }
    
    public final void q() {
    }
}
