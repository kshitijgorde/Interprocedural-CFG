// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.util.Vector;
import java.awt.Event;
import java.awt.Component;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.Frame;

public final class ea extends F
{
    private ap q;
    private ad q;
    M q;
    private ck q;
    private bz q;
    
    public ea(final Frame frame, final ap q, final bz q2) {
        super(frame, be.w(ds.q("t>F9D5K@1BD939@1>DC")), true);
        this.q = q2;
        (this.q = new M()).q(q);
        this.q = q;
        final dZ dz;
        (dz = new dZ(this)).setBackground(bC.w.w);
        dz.setLayout(new GridBagLayout());
        this.setLayout(new GridBagLayout());
        final GridBagConstraints gridBagConstraints;
        (gridBagConstraints = new GridBagConstraints()).insets = new Insets(10, 10, 10, 10);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        this.q = new ck(q, true, null, this.q, null);
        final dT dt;
        (dt = new dT()).setLayout(new GridBagLayout());
        dt.setBackground(bC.w.i);
        gridBagConstraints.insets = new Insets(3, 2, 3, 2);
        dt.add(this.q, gridBagConstraints);
        gridBagConstraints.insets = new Insets(50, 11, 5, 11);
        dz.add(dt, gridBagConstraints);
        this.q = new ad(be.w(ds.q("t>F9D5")));
        gridBagConstraints.fill = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.insets = new Insets(5, 50, 10, 50);
        dz.add(this.q, gridBagConstraints);
        this.setBounds(100, 100, 250, 445);
        gridBagConstraints.fill = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 0;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        this.add(dz, gridBagConstraints);
        this.setResizable(false);
    }
    
    public final void setVisible(final boolean visible) {
        this.q.e();
        synchronized (this.q.a) {
            for (int i = 0; i < this.q.a.q; ++i) {
                try {
                    final aO ao = (aO)this.q.a.q(i);
                    if (!this.q.q(ao.s) && ao.s != this.q.s) {
                        this.q.q(ao, true);
                    }
                }
                catch (ClassCastException ex) {}
            }
        }
        super.setVisible(visible);
    }
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 201: {
                this.q.e();
                this.setVisible(false);
                return true;
            }
            case 1001: {
                if (event.target == this.q) {
                    final Vector q;
                    if ((q = this.q.q()) != null) {
                        String s = "";
                        int n = 0;
                        for (int i = 0; i < q.size(); ++i) {
                            final aO ao = q.elementAt(i);
                            this.q.q(ao);
                            if (n != 0) {
                                s += String.valueOf(new char[] { ',' });
                            }
                            s = s + ao.s + "";
                            n = 1;
                        }
                        this.q.q(s, this.q.q, 3);
                    }
                    this.setVisible(false);
                    this.q.e();
                    return true;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
}
