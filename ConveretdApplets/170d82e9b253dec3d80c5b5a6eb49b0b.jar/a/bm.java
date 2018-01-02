// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.MenuItem;
import java.awt.Container;

public class bm extends bl
{
    public bm(final Container container, final cV cv) {
        super(container, cv);
        if (a.q() && cv != null) {
            boolean b = false;
            if (cv.q(49) || cv.q(1)) {
                this.addSeparator();
                b = true;
                final MenuItem menuItem;
                this.add(menuItem = new MenuItem(aJ.V));
                menuItem.addActionListener(this);
            }
            if (cv.q(72)) {
                if (!b) {
                    this.addSeparator();
                }
                final MenuItem menuItem2;
                this.add(menuItem2 = new MenuItem(aJ.E));
                menuItem2.addActionListener(this);
            }
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        super.actionPerformed(actionEvent);
        final String actionCommand;
        if (!(actionCommand = actionEvent.getActionCommand()).equals(aJ.V)) {
            if (actionCommand.equals(aJ.E)) {
                final Vector vector;
                (vector = new Vector()).addElement(new bV(this.q.p.w(), this.q.q));
                bx.q(vector, this.q);
            }
            return;
        }
        if (this.q.q != null) {
            new b(this.q.q(), ec.q(eb.q("User: %1"), this.q.w), "IP: " + this.q.q.i + "\nHost: " + this.q.q.o, this.q).setVisible(true);
            return;
        }
        new b(this.q.q(), eb.q("Info"), eb.q("User message should be choosen"), this.q).setVisible(true);
    }
}
