// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Label;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Event;
import java.awt.TextArea;
import java.awt.Checkbox;

public final class aG extends aA
{
    private ax a;
    private ax b;
    private Checkbox c;
    private Checkbox d;
    private TextArea c;
    private F c;
    private as f;
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key == 10 || event.key == bs.c) {
                    this.a.l();
                    return true;
                }
                break;
            }
            case 1001: {
                if (event.target == this.a) {
                    final String text = this.c.getText();
                    final aJ aj = new aJ(66816, 1);
                    aj.j = this.c.e();
                    aj.a(0, 0, text);
                    this.f.q(aj);
                    int n = 0;
                    if (this.c.getState()) {
                        ++n;
                    }
                    if (this.d.getState()) {
                        ++n;
                    }
                    if (n > 0) {
                        final aJ aj2 = new aJ(67339, n);
                        if (this.c.getState()) {
                            aj2.a(0, 0, -999);
                            aj2.a(0, 0, this.c.l);
                        }
                        if (this.d.getState()) {
                            aj2.a(n - 1, 0, -999);
                            aj2.a(n - 1, 0, this.c.d);
                        }
                        aj2.j = -1;
                        aj2.C = -1;
                        this.f.q(aj2);
                    }
                    this.dispose();
                    return true;
                }
                if (event.target == this.b) {
                    this.dispose();
                    return true;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public aG(final as f, final F c) {
        super(f.a.a(), ar.b("Kick User"), true);
        this.a = new ax(80, 20);
        this.b = new ax(80, 20);
        this.setBackground(f.b.a);
        this.f = f;
        try {
            this.c = new TextArea("", 5, 35, 1);
        }
        catch (Throwable t) {
            this.c = new TextArea(5, 35);
        }
        final aC ac = new aC();
        final K k = new K();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final Label label = new Label();
        final Label label2 = new Label(ar.b("Kick: "));
        this.c = c;
        final String d = this.c.d();
        final af af = (af)f.d.b(this.c.c);
        this.a.a(ar.b("Kick"));
        this.a.p();
        this.setLayout(gridBagLayout);
        ac.setLayout(gridBagLayout);
        ac.setBackground(f.b.g);
        ac.setForeground(f.b.f);
        gridBagConstraints.insets = new Insets(1, 5, 1, 5);
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.anchor = 17;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        label2.setFont(ay.d);
        gridBagLayout.setConstraints(label2, gridBagConstraints);
        ac.add(label2);
        if (af != null) {
            gridBagConstraints.gridwidth = -1;
            final M m = new M();
            m.b(af.a);
            gridBagLayout.setConstraints(m, gridBagConstraints);
            ac.add(m);
        }
        gridBagConstraints.gridwidth = 0;
        label.setFont(ay.d);
        label.setText(d);
        gridBagLayout.setConstraints(label, gridBagConstraints);
        ac.add(label);
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagLayout.setConstraints(k, gridBagConstraints);
        ac.add(k);
        gridBagConstraints.fill = 1;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        final aw aw = new aw(this.c);
        gridBagLayout.setConstraints(aw, gridBagConstraints);
        ac.add(aw);
        this.c.setText(H.a(ar.b("You have been disconnected from %1 by the ChatMaster."), new String[] { bi.Q }));
        if (f.a(49)) {
            this.c = new Checkbox(H.a(ar.b("Ban this user's IP (%1)"), new String[] { c.l }));
            this.d = new Checkbox(H.a(ar.b("Ban this user's host (%1)"), new String[] { c.d }));
            gridBagConstraints.fill = 0;
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.weighty = 0.0;
            gridBagLayout.setConstraints(this.c, gridBagConstraints);
            ac.add(this.c);
            gridBagLayout.setConstraints(this.d, gridBagConstraints);
            ac.add(this.d);
        }
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagLayout.setConstraints(ac, gridBagConstraints);
        this.add(ac);
        gridBagConstraints.insets = new Insets(2, 5, 2, 5);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weighty = 0.0;
        this.b.a(ar.b("Cancel"));
        this.b.p();
        gridBagLayout.setConstraints(this.b, gridBagConstraints);
        this.add(this.b);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridwidth = 0;
        final au au = new au(this.a);
        gridBagLayout.setConstraints(au, gridBagConstraints);
        this.add(au);
        this.pack();
        this.setVisible(true);
        this.c.requestFocus();
        this.c.selectAll();
    }
}
