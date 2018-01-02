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

public final class cH extends PopupMenu implements cC, ActionListener
{
    private Container q;
    private l q;
    private W q;
    private aO q;
    private S q;
    
    public cH(final Container q, final W q2, final aO q3) {
        super("ConferenceUserPane");
        this.q = q;
        this.q = q2;
        this.q = q3;
        q.add(this);
        final MenuItem menuItem;
        this.add(menuItem = new MenuItem(cz.f));
        menuItem.addActionListener(this);
        final MenuItem menuItem2;
        this.add(menuItem2 = new MenuItem(cz.j));
        menuItem2.addActionListener(this);
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand;
        if ((actionCommand = actionEvent.getActionCommand()).equals(cz.f)) {
            this.q.r(this.q.s);
            return;
        }
        if (actionCommand.equals(cz.j)) {
            this.q.q("" + this.q.s, this.q.q, 5);
        }
    }
    
    public final void q(final int n, final int n2, final Object o) {
        this.q = (l)o;
        this.q = new S(this.q, ((aJ)o).s);
        if ((cs.q() && !aS.w.y()) || (cs.w() && !aS.w.i())) {
            this.show(this.q, n, n2 + 20);
        }
    }
    
    public final void q() {
    }
}
