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

public class bl extends PopupMenu implements bh, ActionListener
{
    protected Container q;
    protected cS q;
    protected cV q;
    
    public bl(final Container q, final cV q2) {
        super("MainPanel");
        this.q = q;
        this.q = q2;
        final MenuItem menuItem;
        this.add(menuItem = new MenuItem(aJ.Q));
        menuItem.addActionListener(this);
        final MenuItem menuItem2;
        this.add(menuItem2 = new MenuItem(aJ.W));
        menuItem2.addActionListener(this);
        q.add(this);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand;
        if ((actionCommand = actionEvent.getActionCommand()).equals(aJ.Q)) {
            String string = "";
            if (this.q.q != null) {
                string = this.q.q.getName() + ", ";
            }
            ea.q(string + this.q.e + ea.q + this.q.q, this.q.q);
            return;
        }
        if (actionCommand.equals(aJ.W)) {
            ea.q(this.q.q, this.q.q);
        }
    }
    
    public void q(final int n, final int n2, final Object o) {
        this.q = (cS)o;
        if ((a.q() && !cf.w.u()) || (a.w() && !cf.w.o())) {
            this.show(this.q, n, n2);
        }
    }
    
    public final void q() {
    }
}
