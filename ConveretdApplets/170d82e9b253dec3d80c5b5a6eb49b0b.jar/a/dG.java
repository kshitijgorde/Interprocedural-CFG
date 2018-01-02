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

public final class dG extends u implements dT
{
    private cU q;
    private cP q;
    
    public final String q(final Object o) {
        return null;
    }
    
    public final void q(final cS cs) {
        this.q.q(cs);
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
                if (!(event.arg instanceof cS)) {
                    break;
                }
                final cS cs = (cS)event.arg;
                final dj dj = (dj)this.q.e.w(cs.y);
                if (cs.y && cs.y != this.q.q() && !cs.o) {
                    this.q.q(cs, dj);
                    break;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public final void w() {
        this.q.w();
    }
    
    public final void dispose() {
        super.dispose();
    }
    
    public dG(final cU q) {
        super(false);
        this.q = q;
        this.q = new cI(q, false);
        this.setBackground(cf.w.q);
        if (q.q(59)) {
            this.setTitle(eb.q("Guest Speaker Queue"));
        }
        else {
            this.setTitle(eb.q("Moderated Queue"));
        }
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final s s = new s();
        this.setLayout(gridBagLayout);
        s.setLayout(gridBagLayout);
        this.q.setFont(cf.w.w());
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        final t t = new t(this.q);
        gridBagLayout.setConstraints(t, gridBagConstraints);
        s.add(t);
        s.setBackground(cf.w.i);
        s.setForeground(cf.w.u);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.gridheight = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagLayout.setConstraints(s, gridBagConstraints);
        this.add(s);
    }
}
