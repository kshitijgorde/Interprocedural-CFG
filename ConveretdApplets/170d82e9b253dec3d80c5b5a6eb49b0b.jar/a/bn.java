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

public final class bn extends PopupMenu implements bh, ActionListener
{
    private Container q;
    private cz q;
    private cV q;
    private M q;
    private dP q;
    
    public bn(final Container q, final cV q2, final M q3) {
        super("ConferenceUserPane");
        this.q = q;
        this.q = q2;
        this.q = q3;
        q.add(this);
        final MenuItem menuItem;
        this.add(menuItem = new MenuItem(aJ.R));
        menuItem.addActionListener(this);
        final MenuItem menuItem2;
        this.add(menuItem2 = new MenuItem(aJ.U));
        menuItem2.addActionListener(this);
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand;
        if ((actionCommand = actionEvent.getActionCommand()).equals(aJ.R)) {
            this.q.e(this.q.q());
            return;
        }
        if (actionCommand.equals(aJ.U)) {
            this.q.q("" + this.q.q(), this.q.q(), 5);
        }
    }
    
    public final void q(final int n, final int n2, final Object o) {
        this.q = (cz)o;
        this.q = new dP(this.q, ((bZ)o).q());
        if ((a.q() && !cf.w.y()) || (a.w() && !cf.w.i())) {
            this.show(this.q, n, n2 + 20);
        }
    }
    
    public final void q() {
    }
}
