// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Label;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Event;
import java.awt.Choice;
import java.awt.TextArea;
import java.awt.Checkbox;

public final class ch extends p
{
    private aS a;
    private aS b;
    private Checkbox h;
    private Checkbox i;
    private TextArea e;
    private cG c;
    private t d;
    private Choice v;
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key == 10 || event.key == f.g) {
                    this.a.s();
                    return true;
                }
                break;
            }
            case 1001: {
                if (event.target == this.a) {
                    if (this.h != null && this.h.getState()) {
                        final cD cd = new cD(67339, 1);
                        cd.a(0, 0, -999);
                        cd.a(0, 0, this.c.W);
                        cd.a(0, 1, this.v.getSelectedIndex());
                        cd.j = this.c.h();
                        cd.o = -1;
                        this.d.o(cd);
                    }
                    if (this.i != null && this.i.getState()) {
                        final cD cd2 = new cD(67339, 1);
                        cd2.a(0, 0, -999);
                        cd2.a(0, 0, this.c.f.substring(0, 3));
                        cd2.a(0, 1, this.v.getSelectedIndex());
                        cd2.j = this.c.h();
                        cd2.o = -1;
                        this.d.o(cd2);
                    }
                    final String text = this.e.getText();
                    final cD cd3 = new cD(66816, 1);
                    cd3.j = this.c.h();
                    cd3.a(0, 0, text);
                    this.d.o(cd3);
                    this.dispose();
                    return true;
                }
                if (event.target == this.h) {
                    this.v.setEnabled(this.h.getState() || (this.i != null && this.i.getState()));
                    return true;
                }
                if (event.target == this.i) {
                    this.v.setEnabled(this.h.getState() || this.i.getState());
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
    
    public ch(final t d, final cG c) {
        super(d.a.a(), ao.e("Kick User"), true);
        this.a = new aS(80, 20);
        this.b = new aS(80, 20);
        this.setBackground(d.a.a);
        this.d = d;
        try {
            this.e = new TextArea("", 5, 35, 1);
        }
        catch (Throwable t) {
            this.e = new TextArea(5, 35);
        }
        final cA ca = new cA();
        final cH ch = new cH();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final Label label = new Label();
        final Label label2 = new Label(ao.e("Kick: "));
        this.c = c;
        final String f = this.c.f();
        final as as = (as)d.b.b(this.c.g);
        this.a.a(ao.e("Kick"));
        this.a.t();
        this.setLayout(gridBagLayout);
        ca.setLayout(gridBagLayout);
        ca.setBackground(d.a.g);
        ca.setForeground(d.a.f);
        gridBagConstraints.insets = new Insets(1, 5, 1, 5);
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.anchor = 17;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        label2.setFont(bL.e);
        gridBagLayout.setConstraints(label2, gridBagConstraints);
        ca.add(label2);
        if (as != null) {
            gridBagConstraints.gridwidth = -1;
            final B b = new B();
            b.b(as.q);
            gridBagLayout.setConstraints(b, gridBagConstraints);
            ca.add(b);
        }
        gridBagConstraints.gridwidth = 0;
        label.setFont(bL.e);
        label.setText(f);
        gridBagLayout.setConstraints(label, gridBagConstraints);
        ca.add(label);
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagLayout.setConstraints(ch, gridBagConstraints);
        ca.add(ch);
        gridBagConstraints.fill = 1;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        final aR ar = new aR(this.e);
        gridBagLayout.setConstraints(ar, gridBagConstraints);
        ca.add(ar);
        this.e.setText(am.a(ao.e("You have been disconnected from %1 by the ChatMaster."), new String[] { z.G }));
        if (d.d(32) || d.d(16)) {
            gridBagConstraints.fill = 0;
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.weighty = 0.0;
            if (d.d(16)) {
                gridBagLayout.setConstraints(this.h = new Checkbox(am.a(ao.e("Ban this user's IP (%1)"), new String[] { c.W })), gridBagConstraints);
                ca.add(this.h);
            }
            if (d.d(32) && c.f != null && c.f.length() > 0) {
                gridBagLayout.setConstraints(this.i = new Checkbox(am.a(ao.e("Ban this user's country (%1)"), new String[] { c.f })), gridBagConstraints);
                ca.add(this.i);
            }
            (this.v = new Choice()).setBackground(Color.white);
            this.v.setForeground(Color.black);
            this.v.addItem(ao.e("Forever"));
            this.v.addItem(ao.e("15 minutes"));
            this.v.addItem(ao.e("30 minutes"));
            this.v.addItem(ao.e("1 hour"));
            this.v.addItem(ao.e("3 hours"));
            this.v.addItem(ao.e("6 hours"));
            this.v.addItem(ao.e("12 hours"));
            this.v.addItem(ao.e("24 hours"));
            this.v.setEnabled(false);
            final Label label3 = new Label(ao.e("Band Time:"));
            label3.setFont(bL.g);
            gridBagConstraints.gridwidth = -1;
            gridBagConstraints.fill = 0;
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.weighty = 0.0;
            gridBagLayout.setConstraints(label3, gridBagConstraints);
            ca.add(label3);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(this.v, gridBagConstraints);
            ca.add(this.v);
        }
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagLayout.setConstraints(ca, gridBagConstraints);
        this.add(ca);
        gridBagConstraints.insets = new Insets(2, 5, 2, 5);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weighty = 0.0;
        this.b.a(ao.e("Cancel"));
        this.b.t();
        gridBagLayout.setConstraints(this.b, gridBagConstraints);
        this.add(this.b);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridwidth = 0;
        final aQ aq = new aQ(this.a);
        gridBagLayout.setConstraints(aq, gridBagConstraints);
        this.add(aq);
        this.pack();
        this.setVisible(true);
        this.e.requestFocus();
        this.e.selectAll();
    }
}
