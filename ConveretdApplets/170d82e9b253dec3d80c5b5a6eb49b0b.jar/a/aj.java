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

public final class aj extends ah
{
    private cV q;
    private g q;
    private g w;
    private g e;
    private g r;
    private g t;
    private P q;
    private String q;
    
    public aj(final Frame frame, final cV q, final String q2) {
        super(frame, ec.q(eb.q("View all users from same ip: %1"), q2), false);
        this.q = q;
        this.q = q2;
        final Panel panel;
        (panel = new Panel()).setBackground(cf.w.w);
        panel.setLayout(new GridBagLayout());
        this.setLayout(new GridBagLayout());
        final GridBagConstraints gridBagConstraints;
        (gridBagConstraints = new GridBagConstraints()).insets = new Insets(10, 10, 10, 10);
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.gridwidth = 0;
        this.q = new P(q, true, null, null, null, false);
        final s s;
        (s = new s()).setLayout(new GridBagLayout());
        s.setBackground(cf.w.i);
        gridBagConstraints.insets = new Insets(3, 2, 3, 2);
        s.add(this.q, gridBagConstraints);
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.0;
        (this.r = new g(eb.q("Profile"))).t();
        s.add(this.r, gridBagConstraints);
        (this.e = new g(eb.q("Ban IP"))).t();
        s.add(this.e, gridBagConstraints);
        (this.w = new g(eb.q("Kick All"))).t();
        s.add(this.w, gridBagConstraints);
        gridBagConstraints.gridwidth = 0;
        (this.q = new g(eb.q("Kick Users"))).t();
        s.add(this.q, gridBagConstraints);
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.fill = 2;
        final Label label;
        (label = new Label(ec.q(eb.q("             All users for ip: %1"), q2))).setFont(new Font(m.t.getFamily(), 1, 16));
        panel.add(label, gridBagConstraints);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = 1;
        panel.add(s, gridBagConstraints);
        gridBagConstraints.fill = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        gridBagConstraints.fill = 0;
        gridBagConstraints.anchor = 13;
        (this.t = new g(eb.q("Close"))).t();
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
    
    public final void q(final int[] array) {
        this.q.w();
        synchronized (this.q.e) {
            for (int i = 0; i < array.length; ++i) {
                final dj dj;
                if ((dj = (dj)this.q.e.w(array[i])) != null) {
                    this.q.q(dj, true);
                }
            }
        }
        this.setVisible(true);
    }
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 201: {
                this.q.w();
                this.dispose();
                return true;
            }
            case 1001: {
                if (event.target == this.q) {
                    final Vector q;
                    if ((q = this.q.q()) != null) {
                        final dj[] array = new dj[q.size()];
                        for (int i = 0; i < q.size(); ++i) {
                            array[i] = q.elementAt(i);
                        }
                        new af(this.q, array);
                    }
                    return true;
                }
                if (event.target == this.t) {
                    this.q.w();
                    this.dispose();
                    return true;
                }
                if (event.target == this.w) {
                    final dP dp = new dP(this.q);
                    final dj q2;
                    if ((q2 = this.q.q(0)) != null) {
                        dp.e(q2.getName());
                    }
                    return true;
                }
                final cz cz = (cz)this.q.q();
                final dP dp2 = new dP(this.q, cz.q());
                if (event.target == this.r) {
                    dp2.e(cz.q());
                    return true;
                }
                if (event.target == this.e) {
                    dp2.q(this.q, Integer.MAX_VALUE);
                    return true;
                }
                break;
            }
            case 701: {
                this.q((bZ)event.arg);
                return true;
            }
            case 702: {
                this.q((bZ)null);
                return true;
            }
        }
        return super.handleEvent(event);
    }
    
    private void q(final bZ bz) {
        if (bz == null) {
            this.q.e();
            this.r.e();
            return;
        }
        this.q.q();
        this.r.q();
    }
}
