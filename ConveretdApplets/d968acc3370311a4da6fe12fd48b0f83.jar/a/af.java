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

public final class af extends ah
{
    private g q;
    private g w;
    private Checkbox q;
    private Checkbox w;
    private Checkbox e;
    private Checkbox r;
    private Checkbox t;
    private TextArea q;
    private cz[] q;
    private cU q;
    private Q q;
    
    public final boolean handleEvent(final Event event) {
        if (a.q == 0) {
            return true;
        }
        switch (event.id) {
            case 401: {
                if (event.key == 10 || event.key == ef.q) {
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
                    final dP dp = new dP(this.q, this.q[0].q());
                    final String text = this.q.getText();
                    if (this.r != null && this.r.getState()) {
                        dp.e(this.q[0].getName());
                    }
                    else {
                        for (int i = 0; i < this.q.length; ++i) {
                            dp.q = this.q[i].q();
                            dp.q(text, this.t.getState());
                        }
                    }
                    int n = 0;
                    if (this.q != null && this.q.getState()) {
                        ++n;
                    }
                    if (this.w != null && this.w.getState()) {
                        ++n;
                    }
                    if (this.e != null && this.e.getState()) {
                        ++n;
                    }
                    if (n > 0) {
                        if (this.q.getState()) {
                            dp.q(this.q[0].i, this.q.q());
                        }
                        if (this.w.getState()) {
                            if (this.q[0].p == null || this.q[0].p.length() == 0 || this.q[0].p.equals("can't get")) {
                                return true;
                            }
                            dp.w(this.q[0].p, this.q.q());
                        }
                        if (this.e.getState()) {
                            dp.q("/MustBan");
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
    
    public af(final cU cu, final cz cz) {
        this(cu, new cz[] { cz });
    }
    
    public af(final cU q, final cz[] q2) {
        super(q.q.q(), eb.q("Kick User"), true);
        if (a.q == 1) {
            this.q = new g(80, 20);
            this.w = new g(80, 20);
            this.setBackground(cf.w.q);
            this.q = q;
            try {
                this.q = new TextArea("", 5, 35, 1);
            }
            catch (Throwable t2) {
                this.q = new TextArea(5, 35);
            }
            final s s = new s();
            final q q3 = new q();
            final GridBagConstraints gridBagConstraints = new GridBagConstraints();
            final GridBagLayout gridBagLayout = new GridBagLayout();
            final Label label = new Label();
            final Label label2 = new Label(eb.q("Kick: "));
            this.q = q2;
            String text = "";
            for (int i = 0; i < this.q.length; ++i) {
                text += this.q[i].getName();
                if (i < this.q.length - 1) {
                    text += ", ";
                }
            }
            final cm cm = (cm)q.w.w(this.q[0].e);
            this.q.q(eb.q("Kick"));
            this.q.t();
            this.setLayout(gridBagLayout);
            s.setLayout(gridBagLayout);
            s.setBackground(cf.w.i);
            s.setForeground(cf.w.u);
            gridBagConstraints.insets = new Insets(1, 5, 1, 5);
            gridBagConstraints.gridwidth = 1;
            gridBagConstraints.anchor = 17;
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.weighty = 0.0;
            label2.setFont(m.q);
            gridBagLayout.setConstraints(label2, gridBagConstraints);
            s.add(label2);
            if (cm != null) {
                gridBagConstraints.gridwidth = -1;
                final Component component;
                ((d)(component = new d())).w(cm.q);
                gridBagLayout.setConstraints(component, gridBagConstraints);
                s.add(component);
            }
            gridBagConstraints.gridwidth = 0;
            label.setFont(m.q);
            label.setText(text);
            gridBagLayout.setConstraints(label, gridBagConstraints);
            s.add(label);
            gridBagConstraints.fill = 2;
            gridBagConstraints.weightx = 1.0;
            gridBagLayout.setConstraints(q3, gridBagConstraints);
            s.add(q3);
            gridBagConstraints.fill = 1;
            gridBagConstraints.weighty = 1.0;
            gridBagConstraints.insets = new Insets(5, 5, 5, 5);
            final t t = new t(this.q);
            gridBagLayout.setConstraints(t, gridBagConstraints);
            s.add(t);
            this.q.setText(ec.q(eb.q("You have been disconnected from %1 by the ChatMaster."), new String[] { a.e }));
            gridBagLayout.setConstraints(this.t = new Checkbox(eb.q("Show that user has been kicked")), gridBagConstraints);
            s.add(this.t);
            if (q.q(49) || q.q(1)) {
                this.q = new Checkbox(ec.q(eb.q("Ban this user's IP (%1)"), new String[] { this.q[0].i }));
                this.w = new Checkbox(ec.q(eb.q("Ban this user's country (%1)"), new String[] { ci.w(this.q[0].p) }));
                gridBagConstraints.fill = 0;
                gridBagConstraints.weightx = 0.0;
                gridBagConstraints.weighty = 0.0;
                gridBagLayout.setConstraints(this.q, gridBagConstraints);
                s.add(this.q);
                gridBagLayout.setConstraints(this.w, gridBagConstraints);
                if (this.q.q(39)) {
                    s.add(this.w);
                }
                if (this.q.q(11)) {
                    gridBagLayout.setConstraints(this.r = new Checkbox(eb.q("Kick all users for same IP")), gridBagConstraints);
                    s.add(this.r);
                }
                final Label label3 = new Label(eb.q("Ban time:"));
                (this.q = new Q(true, false)).setEnabled(false);
                gridBagConstraints.gridwidth = 1;
                s.add(label3, gridBagConstraints);
                gridBagConstraints.gridwidth = 0;
                s.add(this.q, gridBagConstraints);
            }
            if (q.q(85) || q.q(52)) {
                this.e = new Checkbox(eb.q("Ban this user forever"));
                gridBagConstraints.fill = 0;
                gridBagConstraints.weightx = 0.0;
                gridBagConstraints.weighty = 0.0;
                gridBagLayout.setConstraints(this.e, gridBagConstraints);
                s.add(this.e);
            }
            gridBagConstraints.fill = 1;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.weighty = 1.0;
            gridBagLayout.setConstraints(s, gridBagConstraints);
            this.add(s);
            gridBagConstraints.insets = new Insets(2, 5, 2, 5);
            gridBagConstraints.anchor = 13;
            gridBagConstraints.gridwidth = -1;
            gridBagConstraints.fill = 0;
            gridBagConstraints.weighty = 0.0;
            this.w.q(eb.q("Cancel"));
            this.w.t();
            gridBagLayout.setConstraints(this.w, gridBagConstraints);
            this.add(this.w);
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.gridwidth = 0;
            final f f = new f(this.q);
            gridBagLayout.setConstraints(f, gridBagConstraints);
            this.add(f);
            this.pack();
            this.setVisible(true);
            this.q.requestFocus();
            this.q.selectAll();
        }
    }
}
