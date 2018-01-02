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

public final class z extends w
{
    private N q;
    private N w;
    private Checkbox q;
    private Checkbox w;
    private Checkbox e;
    private Checkbox r;
    private TextArea q;
    private l[] q;
    private co q;
    private cf q;
    
    public final boolean handleEvent(final Event event) {
        if (cs.q == 0) {
            return true;
        }
        switch (event.id) {
            case 401: {
                if (event.key == 10 || event.key == bE.q) {
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
                    final T t = new T(this.q, this.q[0].a);
                    final String text = this.q.getText();
                    if (this.e != null && this.e.getState()) {
                        t.e(this.q[0].o);
                    }
                    else {
                        for (int i = 0; i < this.q.length; ++i) {
                            t.q(this.q[i].a);
                            t.q(text, this.r.getState());
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
                            t.q(this.q[0].y, this.q.q());
                        }
                        if (this.w.getState()) {
                            if (this.q[0].i == null || this.q[0].i.length() == 0 || this.q[0].i.equals("can't get")) {
                                return true;
                            }
                            t.w(this.q[0].i, this.q.q());
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
    
    public z(final co co, final l l) {
        this(co, new l[] { l });
    }
    
    private z(final co q, final l[] q2) {
        super(q.q.q(), al.q("Kick User"), true);
        if (cs.q == 1) {
            this.q = new N(80, 20);
            this.w = new N(80, 20);
            this.setBackground(aT.w.q);
            this.q = q;
            try {
                this.q = new TextArea("", 5, 35, 1);
            }
            catch (Throwable t) {
                this.q = new TextArea(5, 35);
            }
            final cv cv = new cv();
            final m m = new m();
            final GridBagConstraints gridBagConstraints = new GridBagConstraints();
            final GridBagLayout gridBagLayout = new GridBagLayout();
            final Label label = new Label();
            final Label label2 = new Label(al.q("Kick: "));
            this.q = q2;
            String text = "";
            for (int i = 0; i < this.q.length; ++i) {
                text += this.q[i].o;
                if (i < this.q.length - 1) {
                    text += ", ";
                }
            }
            final aw aw = (aw)q.w.w(this.q[0].e);
            this.q.q(al.q("Kick"));
            this.q.t();
            this.setLayout(gridBagLayout);
            cv.setLayout(gridBagLayout);
            cv.setBackground(aT.w.i);
            cv.setForeground(aT.w.u);
            gridBagConstraints.insets = new Insets(1, 5, 1, 5);
            gridBagConstraints.gridwidth = 1;
            gridBagConstraints.anchor = 17;
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.weighty = 0.0;
            label2.setFont(be.q);
            gridBagLayout.setConstraints(label2, gridBagConstraints);
            cv.add(label2);
            if (aw != null) {
                gridBagConstraints.gridwidth = -1;
                final Component component;
                ((aH)(component = new aH())).w(aw.q);
                gridBagLayout.setConstraints(component, gridBagConstraints);
                cv.add(component);
            }
            gridBagConstraints.gridwidth = 0;
            label.setFont(be.q);
            label.setText(text);
            gridBagLayout.setConstraints(label, gridBagConstraints);
            cv.add(label);
            gridBagConstraints.fill = 2;
            gridBagConstraints.weightx = 1.0;
            gridBagLayout.setConstraints(m, gridBagConstraints);
            cv.add(m);
            gridBagConstraints.fill = 1;
            gridBagConstraints.weighty = 1.0;
            gridBagConstraints.insets = new Insets(5, 5, 5, 5);
            final bc bc = new bc(this.q);
            gridBagLayout.setConstraints(bc, gridBagConstraints);
            cv.add(bc);
            this.q.setText(t.q(al.q("You have been disconnected from %1 by the ChatMaster."), new String[] { cs.e }));
            gridBagLayout.setConstraints(this.r = new Checkbox(al.q("Show that user has been kicked")), gridBagConstraints);
            cv.add(this.r);
            if (q.q(49) || q.q(1)) {
                this.q = new Checkbox(t.q(al.q("Ban this user's IP (%1)"), new String[] { this.q[0].y }));
                this.w = new Checkbox(t.q(al.q("Ban this user's country (%1)"), new String[] { g.q(this.q[0].i) }));
                gridBagConstraints.fill = 0;
                gridBagConstraints.weightx = 0.0;
                gridBagConstraints.weighty = 0.0;
                gridBagLayout.setConstraints(this.q, gridBagConstraints);
                cv.add(this.q);
                gridBagLayout.setConstraints(this.w, gridBagConstraints);
                if (this.q.q(39)) {
                    cv.add(this.w);
                }
                if (this.q.q(11)) {
                    gridBagLayout.setConstraints(this.e = new Checkbox(al.q("Kick all users for same IP")), gridBagConstraints);
                    cv.add(this.e);
                }
                final Label label3 = new Label(al.q("Ban time:"));
                (this.q = new cf(true, true)).setEnabled(false);
                gridBagConstraints.gridwidth = 1;
                cv.add(label3, gridBagConstraints);
                gridBagConstraints.gridwidth = 0;
                cv.add(this.q, gridBagConstraints);
            }
            gridBagConstraints.fill = 1;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.weighty = 1.0;
            gridBagLayout.setConstraints(cv, gridBagConstraints);
            this.add(cv);
            gridBagConstraints.insets = new Insets(2, 5, 2, 5);
            gridBagConstraints.anchor = 13;
            gridBagConstraints.gridwidth = -1;
            gridBagConstraints.fill = 0;
            gridBagConstraints.weighty = 0.0;
            this.w.q(al.q("Cancel"));
            this.w.t();
            gridBagLayout.setConstraints(this.w, gridBagConstraints);
            this.add(this.w);
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.gridwidth = 0;
            final ab ab = new ab(this.q);
            gridBagLayout.setConstraints(ab, gridBagConstraints);
            this.add(ab);
            this.pack();
            this.setVisible(true);
            this.q.requestFocus();
            this.q.selectAll();
        }
    }
}
