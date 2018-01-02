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

public final class U extends W
{
    private e q;
    private e w;
    private Checkbox q;
    private Checkbox w;
    private Checkbox e;
    private Checkbox r;
    private Checkbox t;
    private TextArea q;
    private bp[] q;
    private bH q;
    private N q;
    
    public final boolean handleEvent(final Event event) {
        if (a.q == 0) {
            return true;
        }
        switch (event.id) {
            case 401: {
                if (event.key == 10 || event.key == cx.q) {
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
                    final cj cj = new cj(this.q, this.q[0].q());
                    final String text = this.q.getText();
                    if (this.r != null && this.r.getState()) {
                        cj.e(this.q[0].getName());
                    }
                    else {
                        for (int i = 0; i < this.q.length; ++i) {
                            cj.q = this.q[i].q();
                            cj.q(text, this.t.getState());
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
                            cj.q(this.q[0].u, this.q.q());
                        }
                        if (this.w.getState()) {
                            if (this.q[0].o == null || this.q[0].o.length() == 0 || this.q[0].o.equals("can't get")) {
                                return true;
                            }
                            cj.w(this.q[0].o, this.q.q());
                        }
                        if (this.e.getState()) {
                            cj.q("/MustBan");
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
    
    public U(final bH bh, final bp bp) {
        this(bh, new bp[] { bp });
    }
    
    private U(final bH q, final bp[] q2) {
        super(q.q.q(), cv.q("Kick User"), true);
        if (a.q == 1) {
            this.q = new e(80, 20);
            this.w = new e(80, 20);
            this.setBackground(be.w.q);
            this.q = q;
            try {
                this.q = new TextArea("", 5, 35, 1);
            }
            catch (Throwable t) {
                this.q = new TextArea(5, 35);
            }
            final q q3 = new q();
            final o o = new o();
            final GridBagConstraints gridBagConstraints = new GridBagConstraints();
            final GridBagLayout gridBagLayout = new GridBagLayout();
            final Label label = new Label();
            final Label label2 = new Label(cv.q("Kick: "));
            this.q = q2;
            String text = "";
            for (int i = 0; i < this.q.length; ++i) {
                text += this.q[i].getName();
                if (i < this.q.length - 1) {
                    text += ", ";
                }
            }
            final bj bj = (bj)q.w.w(this.q[0].u);
            this.q.q(cv.q("Kick"));
            this.q.t();
            this.setLayout(gridBagLayout);
            q3.setLayout(gridBagLayout);
            q3.setBackground(be.w.i);
            q3.setForeground(be.w.u);
            gridBagConstraints.insets = new Insets(1, 5, 1, 5);
            gridBagConstraints.gridwidth = 1;
            gridBagConstraints.anchor = 17;
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.weighty = 0.0;
            label2.setFont(k.q);
            gridBagLayout.setConstraints(label2, gridBagConstraints);
            q3.add(label2);
            if (bj != null) {
                gridBagConstraints.gridwidth = -1;
                final Component component;
                ((c)(component = new c())).w(bj.q);
                gridBagLayout.setConstraints(component, gridBagConstraints);
                q3.add(component);
            }
            gridBagConstraints.gridwidth = 0;
            label.setFont(k.q);
            label.setText(text);
            gridBagLayout.setConstraints(label, gridBagConstraints);
            q3.add(label);
            gridBagConstraints.fill = 2;
            gridBagConstraints.weightx = 1.0;
            gridBagLayout.setConstraints(o, gridBagConstraints);
            q3.add(o);
            gridBagConstraints.fill = 1;
            gridBagConstraints.weighty = 1.0;
            gridBagConstraints.insets = new Insets(5, 5, 5, 5);
            final r r = new r(this.q);
            gridBagLayout.setConstraints(r, gridBagConstraints);
            q3.add(r);
            this.q.setText(cv.q(cv.q("You have been disconnected from %1 by the ChatMaster."), new String[] { a.e }));
            gridBagLayout.setConstraints(this.t = new Checkbox(cv.q("Show that user has been kicked")), gridBagConstraints);
            q3.add(this.t);
            if (q.q(49) || q.q(1)) {
                this.q = new Checkbox(cv.q(cv.q("Ban this user's IP (%1)"), new String[] { this.q[0].u }));
                this.w = new Checkbox(cv.q(cv.q("Ban this user's country (%1)"), new String[] { bf.q(this.q[0].o) }));
                gridBagConstraints.fill = 0;
                gridBagConstraints.weightx = 0.0;
                gridBagConstraints.weighty = 0.0;
                gridBagLayout.setConstraints(this.q, gridBagConstraints);
                q3.add(this.q);
                gridBagLayout.setConstraints(this.w, gridBagConstraints);
                if (this.q.q(39)) {
                    q3.add(this.w);
                }
                if (this.q.q(11)) {
                    gridBagLayout.setConstraints(this.r = new Checkbox(cv.q("Kick all users for same IP")), gridBagConstraints);
                    q3.add(this.r);
                }
                final Label label3 = new Label(cv.q("Ban time:"));
                (this.q = new N(true, false)).setEnabled(false);
                gridBagConstraints.gridwidth = 1;
                q3.add(label3, gridBagConstraints);
                gridBagConstraints.gridwidth = 0;
                q3.add(this.q, gridBagConstraints);
            }
            if (q.q(85) || q.q(52)) {
                this.e = new Checkbox(cv.q("Ban this user forever"));
                gridBagConstraints.fill = 0;
                gridBagConstraints.weightx = 0.0;
                gridBagConstraints.weighty = 0.0;
                gridBagLayout.setConstraints(this.e, gridBagConstraints);
                q3.add(this.e);
            }
            gridBagConstraints.fill = 1;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.weighty = 1.0;
            gridBagLayout.setConstraints(q3, gridBagConstraints);
            this.add(q3);
            gridBagConstraints.insets = new Insets(2, 5, 2, 5);
            gridBagConstraints.anchor = 13;
            gridBagConstraints.gridwidth = -1;
            gridBagConstraints.fill = 0;
            gridBagConstraints.weighty = 0.0;
            this.w.q(cv.q("Cancel"));
            this.w.t();
            gridBagLayout.setConstraints(this.w, gridBagConstraints);
            this.add(this.w);
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.gridwidth = 0;
            final d d = new d(this.q);
            gridBagLayout.setConstraints(d, gridBagConstraints);
            this.add(d);
            this.pack();
            this.setVisible(true);
            this.q.requestFocus();
            this.q.selectAll();
        }
    }
}
