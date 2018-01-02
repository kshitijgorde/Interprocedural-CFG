// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Label;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Event;
import java.awt.TextArea;
import java.awt.Checkbox;

public final class J extends F
{
    private ad q;
    private ad w;
    private Checkbox q;
    private Checkbox w;
    private Checkbox e;
    private Checkbox r;
    private TextArea q;
    private p[] q;
    private dH q;
    private du q;
    
    public final boolean handleEvent(final Event event) {
        if (dN.q == 0) {
            return true;
        }
        switch (event.id) {
            case 401: {
                if (event.key == 10 || event.key == cK.q) {
                    this.q.r();
                    return true;
                }
                break;
            }
            case 1001: {
                if (event.target == this.q) {
                    if (this.q != null && this.q.isEnabled() && this.q.q() < 0) {
                        return true;
                    }
                    final aG ag = new aG(this.q, this.q[0].s);
                    final String text = this.q.getText();
                    if (this.e != null && this.e.getState()) {
                        ag.t(this.q[0].a);
                    }
                    else {
                        for (int i = 0; i < this.q.length; ++i) {
                            ag.q(this.q[i].s);
                            ag.q(text, this.r.getState());
                        }
                    }
                    int n = 0;
                    if (this.q != null && this.q.getState()) {
                        ++n;
                    }
                    if (this.w != null && this.w.getState()) {
                        ++n;
                    }
                    if (n > 0) {
                        if (this.q.getState()) {
                            ag.q(this.q[0].u, this.q.q());
                        }
                        if (this.w.getState()) {
                            if (this.q[0].o == null || this.q[0].o.length() == 0 || this.q[0].o.equals("can't get")) {
                                return true;
                            }
                            ag.w(this.q[0].o, this.q.q());
                        }
                    }
                    this.dispose();
                    return true;
                }
                else {
                    if (event.target == this.w) {
                        this.dispose();
                        return true;
                    }
                    if (event.target == this.q || event.target == this.w) {
                        this.q.setEnabled(this.q.getState() || this.w.getState());
                        return true;
                    }
                    break;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public J(final dH dh, final p p2) {
        this(dh, new p[] { p2 });
    }
    
    public J(final dH q, final p[] q2) {
        super(q.q.q(), be.w("Kick User"), true);
        if (dN.q == 1) {
            this.q = new ad(80, 20);
            this.w = new ad(80, 20);
            this.setBackground(bC.w.q);
            this.q = q;
            try {
                this.q = new TextArea("", 5, 35, 1);
            }
            catch (Throwable t) {
                this.q = new TextArea(5, 35);
            }
            final dT dt = new dT();
            final q q3 = new q();
            final GridBagConstraints gridBagConstraints = new GridBagConstraints();
            final GridBagLayout gridBagLayout = new GridBagLayout();
            final Label label = new Label();
            final Label label2 = new Label(be.w("Kick: "));
            this.q = q2;
            String text = "";
            for (int i = 0; i < this.q.length; ++i) {
                text += this.q[i].a;
                if (i < this.q.length - 1) {
                    text += ", ";
                }
            }
            final aZ az = (aZ)q.p.w(this.q[0].e);
            this.q.q(be.w("Kick"));
            this.q.t();
            this.setLayout(gridBagLayout);
            dt.setLayout(gridBagLayout);
            dt.setBackground(bC.w.i);
            dt.setForeground(bC.w.u);
            gridBagConstraints.insets = new Insets(1, 5, 1, 5);
            gridBagConstraints.gridwidth = 1;
            gridBagConstraints.anchor = 17;
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.weighty = 0.0;
            label2.setFont(cb.q);
            gridBagLayout.setConstraints(label2, gridBagConstraints);
            dt.add(label2);
            if (az != null) {
                gridBagConstraints.gridwidth = -1;
                final Component component;
                ((bl)(component = new bl())).w(az.q);
                gridBagLayout.setConstraints(component, gridBagConstraints);
                dt.add(component);
            }
            gridBagConstraints.gridwidth = 0;
            label.setFont(cb.q);
            label.setText(text);
            gridBagLayout.setConstraints(label, gridBagConstraints);
            dt.add(label);
            gridBagConstraints.fill = 2;
            gridBagConstraints.weightx = 1.0;
            gridBagLayout.setConstraints(q3, gridBagConstraints);
            dt.add(q3);
            gridBagConstraints.fill = 1;
            gridBagConstraints.weighty = 1.0;
            gridBagConstraints.insets = new Insets(5, 5, 5, 5);
            final bZ bz = new bZ(this.q);
            gridBagLayout.setConstraints(bz, gridBagConstraints);
            dt.add(bz);
            this.q.setText(B.q(be.w("You have been disconnected from %1 by the ChatMaster."), new String[] { dN.e }));
            gridBagLayout.setConstraints(this.r = new Checkbox(be.w("Show that user has been kicked")), gridBagConstraints);
            dt.add(this.r);
            if (q.q(49) || q.q(1)) {
                this.q = new Checkbox(B.q(be.w("Ban this user's IP (%1)"), new String[] { this.q[0].u }));
                this.w = new Checkbox(B.q(be.w("Ban this user's country (%1)"), new String[] { l.w(this.q[0].o) }));
                gridBagConstraints.fill = 0;
                gridBagConstraints.weightx = 0.0;
                gridBagConstraints.weighty = 0.0;
                gridBagLayout.setConstraints(this.q, gridBagConstraints);
                dt.add(this.q);
                gridBagLayout.setConstraints(this.w, gridBagConstraints);
                if (this.q.q(39)) {
                    dt.add(this.w);
                }
                if (this.q.q(11)) {
                    gridBagLayout.setConstraints(this.e = new Checkbox(be.w("Kick all users for same IP")), gridBagConstraints);
                    dt.add(this.e);
                }
                final Label label3 = new Label(be.w("Ban time:"));
                (this.q = new du(true, true)).setEnabled(false);
                gridBagConstraints.gridwidth = 1;
                dt.add(label3, gridBagConstraints);
                gridBagConstraints.gridwidth = 0;
                dt.add(this.q, gridBagConstraints);
            }
            gridBagConstraints.fill = 1;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.weighty = 1.0;
            gridBagLayout.setConstraints(dt, gridBagConstraints);
            this.add(dt);
            gridBagConstraints.insets = new Insets(2, 5, 2, 5);
            gridBagConstraints.anchor = 13;
            gridBagConstraints.gridwidth = -1;
            gridBagConstraints.fill = 0;
            gridBagConstraints.weighty = 0.0;
            this.w.q(be.w("Cancel"));
            this.w.t();
            gridBagLayout.setConstraints(this.w, gridBagConstraints);
            this.add(this.w);
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.gridwidth = 0;
            final as as = new as(this.q);
            gridBagLayout.setConstraints(as, gridBagConstraints);
            this.add(as);
            this.pack();
            this.setVisible(true);
            this.q.requestFocus();
            this.q.selectAll();
        }
    }
}
