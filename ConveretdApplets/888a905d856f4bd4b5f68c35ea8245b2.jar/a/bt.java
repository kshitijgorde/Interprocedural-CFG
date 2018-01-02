// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.util.Vector;
import java.awt.Event;
import java.awt.Font;
import java.awt.Label;
import java.awt.Component;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.Panel;
import java.awt.Frame;

public final class bt extends F
{
    public ap q;
    private ad q;
    private ad w;
    private ad e;
    private ad r;
    private ad t;
    public ck q;
    private String q;
    
    public bt(final Frame frame, final ap q, final String q2) {
        super(frame, B.q(be.w("View all users from same ip: %1"), q2), false);
        this.q = q;
        this.q = q2;
        final Panel panel;
        (panel = new Panel()).setBackground(bC.w.w);
        panel.setLayout(new GridBagLayout());
        this.setLayout(new GridBagLayout());
        final GridBagConstraints gridBagConstraints;
        (gridBagConstraints = new GridBagConstraints()).insets = new Insets(10, 10, 10, 10);
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.gridwidth = 0;
        this.q = new ck(q, true, null, null, null, false);
        final dT dt;
        (dt = new dT()).setLayout(new GridBagLayout());
        dt.setBackground(bC.w.i);
        gridBagConstraints.insets = new Insets(3, 2, 3, 2);
        dt.add(this.q, gridBagConstraints);
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.0;
        (this.r = new ad(be.w("Profile"))).t();
        dt.add(this.r, gridBagConstraints);
        (this.e = new ad(be.w("Ban IP"))).t();
        dt.add(this.e, gridBagConstraints);
        (this.w = new ad(be.w("Kick All"))).t();
        dt.add(this.w, gridBagConstraints);
        gridBagConstraints.gridwidth = 0;
        (this.q = new ad(be.w("Kick Users"))).t();
        dt.add(this.q, gridBagConstraints);
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.fill = 2;
        final Label label;
        (label = new Label(B.q(be.w("             All users for ip: %1"), q2))).setFont(new Font(cb.t.getFamily(), 1, 16));
        panel.add(label, gridBagConstraints);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = 1;
        panel.add(dt, gridBagConstraints);
        gridBagConstraints.fill = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        gridBagConstraints.fill = 0;
        gridBagConstraints.anchor = 13;
        (this.t = new ad(be.w("Close"))).t();
        panel.add(this.t, gridBagConstraints);
        this.setSize(350, 300);
        this.w();
        gridBagConstraints.fill = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 0;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        this.add(panel, gridBagConstraints);
        this.q.e();
        this.r.e();
    }
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 201: {
                this.q.e();
                this.dispose();
                return true;
            }
            case 1001: {
                if (event.target == this.q) {
                    final Vector q;
                    if ((q = this.q.q()) != null) {
                        final aO[] array = new aO[q.size()];
                        for (int i = 0; i < q.size(); ++i) {
                            array[i] = q.elementAt(i);
                        }
                        new J(this.q, array);
                    }
                    return true;
                }
                if (event.target == this.t) {
                    this.q.e();
                    this.dispose();
                    return true;
                }
                if (event.target == this.w) {
                    final aG ag = new aG(this.q);
                    final ck q2;
                    final aO ao;
                    if ((ao = (((q2 = this.q).q != null) ? ((aO)q2.q.q(0)) : null)) != null) {
                        ag.t(ao.a);
                    }
                    return true;
                }
                final p p = (p)this.q.q();
                final aG ag2 = new aG(this.q, p.s);
                if (event.target == this.r) {
                    ag2.y(p.s);
                    return true;
                }
                if (event.target == this.e) {
                    ag2.q(this.q, Integer.MAX_VALUE);
                    return true;
                }
                break;
            }
            case 701: {
                this.q((bp)event.arg);
                return true;
            }
            case 702: {
                this.q(null);
                return true;
            }
        }
        return super.handleEvent(event);
    }
    
    private void q(final bp bp) {
        if (bp == null) {
            this.q.e();
            this.r.e();
            return;
        }
        this.q.q();
        this.r.q();
    }
}
