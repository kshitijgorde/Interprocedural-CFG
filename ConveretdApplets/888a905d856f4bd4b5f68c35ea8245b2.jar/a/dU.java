// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.util.Date;
import java.util.Vector;
import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Frame;
import java.awt.Event;
import java.text.SimpleDateFormat;

public final class dU extends F
{
    private ad q;
    private dl q;
    private cT q;
    private SimpleDateFormat q;
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key == 27) {
                    this.q.r();
                    return true;
                }
                break;
            }
            case 1001: {
                if (event.target == this.q) {
                    this.dispose();
                    return true;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public dU(final Frame frame, final cT q) {
        super(frame, B.q(be.w("View %1"), be.w("Notes")), false);
        this.q = new SimpleDateFormat("dd MMM yy HH:mm");
        this.q = q;
        super.q = new ad(80, 20);
        this.q = new ad(80, 20);
        (this.q = new dl(q, false)).setFont(bC.w.w());
        this.setBackground(bC.w.q);
        final dT dt = new dT();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout gridBagLayout = new GridBagLayout();
        this.setResizable(true);
        this.setLayout(gridBagLayout);
        dt.setLayout(gridBagLayout);
        dt.setBackground(bC.w.i);
        dt.setForeground(bC.w.u);
        gridBagConstraints.insets = new Insets(1, 5, 1, 5);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.anchor = 17;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        final bZ bz = new bZ(this.q);
        gridBagLayout.setConstraints(bz, gridBagConstraints);
        dt.add(bz);
        gridBagLayout.setConstraints(dt, gridBagConstraints);
        this.add(dt);
        gridBagConstraints.insets = new Insets(2, 5, 2, 5);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        this.q.q(be.w("Close"));
        this.q.t();
        gridBagLayout.setConstraints(this.q, gridBagConstraints);
        this.add(this.q);
        this.q.setSize(435, 300);
        this.pack();
        this.w();
    }
    
    public final void setVisible(final boolean visible) {
        super.setVisible(visible);
        if (visible) {
            final M w = this.q.w;
            final Vector vector = new Vector<dy>();
            Object o;
            for (int n = 0; n < w.q.length && (o = w.q[n]) != null; ++n) {
                vector.addElement((dy)o);
            }
            final dy[] array = new dy[vector.size()];
            vector.copyInto(array);
            final dy[] array2;
            bH.q(array2 = array);
            for (int i = 0; i < array2.length; ++i) {
                final D d = (D)array2[i];
                if (this.q.a == d.w || d.w == -1) {
                    aO ao = null;
                    for (int j = 0; j < this.q.a.q; ++j) {
                        final aO ao2;
                        if ((ao2 = (aO)this.q.a.q(j)).a.equals(d.r)) {
                            ao = ao2;
                            break;
                        }
                    }
                    if (ao == null) {
                        (ao = new aO(-999, d.r)).e = -1;
                        final cu cu;
                        if ((cu = (cu)this.q.e.w(aY.q(d.r, this.q.e))) != null) {
                            if (d.w > 0 && d.w != cu.s) {
                                continue;
                            }
                            ao.p(cu.w());
                            final de de;
                            if ((de = (de)this.q.y.w(cu.q)) != null) {
                                ao.t = de.q;
                            }
                            else {
                                ao.t = -1;
                            }
                        }
                        else {
                            ao.p(2125055);
                            ao.t = -1;
                        }
                    }
                    final StringBuffer sb = new StringBuffer();
                    final String format = this.q.format(new Date(d.q));
                    sb.append(d.a);
                    final A a;
                    (a = new A(sb.toString(), ao, false, false, (aZ)this.q.p.w(ao.e), null)).d = d.g;
                    a.q(format);
                    this.q.q(a);
                }
            }
            cW.w = null;
        }
    }
}
