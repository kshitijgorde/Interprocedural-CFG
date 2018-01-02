// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.util.Vector;
import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Container;
import java.awt.Frame;
import java.awt.Event;
import java.awt.Label;

public final class z extends F
{
    private ad q;
    public dl q;
    private cT q;
    private Label q;
    
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
    
    public z(final Frame frame, cT q) {
        super(frame, dX.m, false);
        this.q = q;
        super.q = new ad(80, 20);
        this.q = new ad(80, 20);
        (this.q = new dl(q, false)).setFont(bC.w.w());
        final dl q2 = this.q;
        q = this.q;
        eb eb;
        if (cU.q()) {
            eb = new dc(this, q);
        }
        else {
            eb = new aT(this, q);
        }
        q2.q(eb);
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
        dt.add(this.q = new Label(""), gridBagConstraints);
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
        this.q.setSize(431, 265);
        this.pack();
        this.w();
    }
    
    public final void setVisible(final boolean visible) {
        super.setVisible(visible);
        if (visible) {
            final Vector vector = new Vector<dV>();
            int n = 0;
            int n2 = 0;
            for (int i = 0; i < this.q.q.q; ++i) {
                final dV dv = (dV)this.q.q.q(i);
                n += dv.a.length();
                aO ao = null;
                for (int j = 0; j < this.q.a.q; ++j) {
                    final aO ao2;
                    if ((ao2 = (aO)this.q.a.q(j)).a.equals(dv.q)) {
                        ao = ao2;
                        break;
                    }
                }
                if (ao == null) {
                    (ao = new aO(-999, dv.q)).e = -1;
                    final cu cu;
                    if ((cu = (cu)this.q.e.w(aY.q(dv.q, this.q.e))) != null) {
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
                else {
                    ao.s = dv.s;
                }
                final StringBuffer sb = new StringBuffer();
                final String format = d.q.format(dv.q);
                sb.append(dv.a);
                final A a;
                (a = new A(sb.toString(), ao, false, false, (aZ)this.q.p.w(ao.e), this.q)).p = dv.w();
                a.q(format);
                if (dv.q) {
                    a.d = 14869218;
                }
                else {
                    a.d = 13684944;
                    ++n2;
                }
                this.q.q(a);
                if (!dv.q) {
                    vector.addElement(dv);
                    dv.q = true;
                }
            }
            cW.r = null;
            if (vector.size() > 0) {
                final dI di;
                (di = new dI(4202528, vector.size())).q = -1;
                di.w = -1;
                for (int k = 0; k < vector.size(); ++k) {
                    final dV dv2 = vector.elementAt(k);
                    di.q(k, 0, 3);
                    di.q(k, 1, dv2.s);
                }
                this.q.o(di);
            }
            this.q.setText("(" + n2 + " " + ds.q(">5G") + ", " + n * 100 / 5120 + ds.q("PEC54KC@135") + ")");
        }
    }
}
