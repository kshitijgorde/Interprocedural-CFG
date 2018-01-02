// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Event;

public final class dr extends bE implements bf
{
    private dH q;
    private bk q;
    
    public final String q(final Object o) {
        return null;
    }
    
    public final void q(final A a) {
        this.q.q(a);
    }
    
    public final void q(final byte[] array) {
        final boolean q;
        if ((q = this.q.q(59)) && array[3] == 0) {
            return;
        }
        if (!q && array[3] == 1) {
            return;
        }
        try {
            for (int i = this.q.w() - 1; i >= 0; --i) {
                if (this.q.q(i).q[0] == array[0] && this.q.q(i).q[1] == array[1]) {
                    if (array[2] == 2) {
                        this.q.q(i);
                    }
                    else {
                        this.q.q(i).o = (array[2] == 1);
                    }
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 1001: {
                if (!(event.arg instanceof A)) {
                    break;
                }
                final A a = (A)event.arg;
                final aO ao = (aO)this.q.a.w(a.y);
                if (a.y && a.y != this.q.s && !a.o) {
                    this.q.q(a, ao);
                    break;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public final void q() {
        this.q.w();
    }
    
    public final void dispose() {
        super.dispose();
    }
    
    public dr(final dH q) {
        super(false);
        this.q = q;
        this.q = new dl(q, false);
        this.setBackground(bC.w.q);
        if (q.q(59)) {
            this.setTitle(be.w("Guest Speaker Queue"));
        }
        else {
            this.setTitle(be.w("Moderated Queue"));
        }
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final dT dt = new dT();
        this.setLayout(gridBagLayout);
        dt.setLayout(gridBagLayout);
        this.q.setFont(bC.w.w());
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        final bZ bz = new bZ(this.q);
        gridBagLayout.setConstraints(bz, gridBagConstraints);
        dt.add(bz);
        dt.setBackground(bC.w.i);
        dt.setForeground(bC.w.u);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.gridheight = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagLayout.setConstraints(dt, gridBagConstraints);
        this.add(dt);
    }
}
