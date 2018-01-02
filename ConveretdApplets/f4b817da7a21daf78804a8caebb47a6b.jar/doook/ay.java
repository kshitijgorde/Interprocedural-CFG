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

public final class ay extends N
{
    private al b;
    private al c;
    private Checkbox h;
    private Checkbox d;
    private TextArea c;
    private aI b;
    private be d;
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key == 10 || event.key == F.e) {
                    this.b.e();
                    return true;
                }
                break;
            }
            case 1001: {
                if (event.target == this.b) {
                    final String text = this.c.getText();
                    final V v = new V(66816, 1);
                    v.j = this.b.b();
                    v.a(0, 0, text);
                    this.d.F(v);
                    int n = 0;
                    if (this.h.getState()) {
                        ++n;
                    }
                    if (this.d.getState()) {
                        ++n;
                    }
                    if (n > 0) {
                        final V v2 = new V(67339, n);
                        if (this.h.getState()) {
                            v2.a(0, 0, -999);
                            v2.a(0, 0, this.b.w);
                        }
                        if (this.d.getState()) {
                            v2.a(n - 1, 0, -999);
                            v2.a(n - 1, 0, this.b.x);
                        }
                        v2.j = -1;
                        v2.u = -1;
                        this.d.F(v2);
                    }
                    this.dispose();
                    return true;
                }
                if (event.target == this.c) {
                    this.dispose();
                    return true;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public ay(final be d, final aI b) {
        super(d.a.a(), aG.a("Kick User"), true);
        this.b = new al(80, 20);
        this.c = new al(80, 20);
        this.setBackground(d.c.c);
        this.d = d;
        try {
            this.c = new TextArea("", 5, 35, 1);
        }
        catch (Throwable t) {
            this.c = new TextArea(5, 35);
        }
        final n n = new n();
        final ad ad = new ad();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final Label label = new Label();
        final Label label2 = new Label(aG.a("Kick: "));
        this.b = b;
        final String g = this.b.g();
        final bh bh = (bh)d.c.b(this.b.v);
        this.b.a(aG.a("Kick"));
        this.b.f();
        this.setLayout(gridBagLayout);
        n.setLayout(gridBagLayout);
        n.setBackground(d.c.k);
        n.setForeground(d.c.j);
        gridBagConstraints.insets = new Insets(1, 5, 1, 5);
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.anchor = 17;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        label2.setFont(aK.e);
        gridBagLayout.setConstraints(label2, gridBagConstraints);
        n.add(label2);
        if (bh != null) {
            gridBagConstraints.gridwidth = -1;
            final bp bp = new bp();
            bp.b(bh.b);
            gridBagLayout.setConstraints(bp, gridBagConstraints);
            n.add(bp);
        }
        gridBagConstraints.gridwidth = 0;
        label.setFont(aK.e);
        label.setText(g);
        gridBagLayout.setConstraints(label, gridBagConstraints);
        n.add(label);
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagLayout.setConstraints(ad, gridBagConstraints);
        n.add(ad);
        gridBagConstraints.fill = 1;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        final aX ax = new aX(this.c);
        gridBagLayout.setConstraints(ax, gridBagConstraints);
        n.add(ax);
        this.c.setText(aC.a(aG.a("You have been disconnected from %1 by the ChatMaster."), new String[] { t.a }));
        if (d.c(49)) {
            this.h = new Checkbox(aC.a(aG.a("Ban this user's IP (%1)"), new String[] { b.w }));
            this.d = new Checkbox(aC.a(aG.a("Ban this user's host (%1)"), new String[] { b.x }));
            gridBagConstraints.fill = 0;
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.weighty = 0.0;
            gridBagLayout.setConstraints(this.h, gridBagConstraints);
            n.add(this.h);
            gridBagLayout.setConstraints(this.d, gridBagConstraints);
            n.add(this.d);
        }
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagLayout.setConstraints(n, gridBagConstraints);
        this.add(n);
        gridBagConstraints.insets = new Insets(2, 5, 2, 5);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weighty = 0.0;
        this.c.a(aG.a("Cancel"));
        this.c.f();
        gridBagLayout.setConstraints(this.c, gridBagConstraints);
        this.add(this.c);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridwidth = 0;
        final P p2 = new P(this.b);
        gridBagLayout.setConstraints(p2, gridBagConstraints);
        this.add(p2);
        this.pack();
        this.setVisible(true);
        this.c.requestFocus();
        this.c.selectAll();
    }
}
