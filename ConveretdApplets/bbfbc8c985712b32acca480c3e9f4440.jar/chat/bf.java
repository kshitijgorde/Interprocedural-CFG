// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Color;
import java.awt.Event;
import java.awt.Choice;
import java.awt.TextArea;
import java.awt.Checkbox;

public final class bf extends bC
{
    private cr a;
    private cr b;
    private Checkbox a;
    private Checkbox b;
    private Checkbox c;
    private TextArea a;
    private ai a;
    private cs a;
    private Choice a;
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key == 10 || event.key == ce.a) {
                    this.a.c();
                    return true;
                }
                break;
            }
            case 1001: {
                if (event.target == this.a) {
                    final String text = this.a.getText();
                    final r r;
                    (r = new r(66816, 1)).e = this.a.i;
                    r.a(0, 0, text);
                    if (this.c.getState() && this.a.a(11)) {
                        r.a(0, 7, true);
                    }
                    this.a.o(r);
                    if (this.a.a(49)) {
                        int n = 0;
                        if (this.a.getState()) {
                            ++n;
                        }
                        if (this.b.getState()) {
                            ++n;
                        }
                        if (n > 0) {
                            final r r2 = new r(67339, n);
                            if (this.a.getState()) {
                                r2.a(0, 0, -999);
                                r2.a(0, 0, this.a.a);
                            }
                            if (this.b.getState()) {
                                r2.a(n - 1, 0, -999);
                                r2.a(n - 1, 0, this.a.b);
                                r2.a(--n, 2, true);
                            }
                            r2.e = -1;
                            r2.d = -1;
                            this.a.o(r2);
                        }
                    }
                    this.dispose();
                    return true;
                }
                if (event.target == this.b) {
                    this.dispose();
                    return true;
                }
                if (event.target == this.a || event.target == this.b) {
                    this.a.setEnabled(this.a.getState() || this.b.getState());
                    return true;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public bf(final cs a, final ai a2) {
        super(a.a.a(), aS.a(171), true);
        this.a = new cr(80, 20);
        this.b = new cr(80, 20);
        this.setBackground(a.a.a);
        this.a = a;
        try {
            this.a = new TextArea("", 5, 35, 1);
        }
        catch (Throwable t) {
            this.a = new TextArea(5, 35);
        }
        (this.a = new Choice()).setBackground(Color.white);
        this.a.setForeground(Color.black);
        this.a.addItem(aS.a(632));
        this.a.addItem(aS.a(609));
        this.a.addItem(aS.a(610));
        this.a.addItem(aS.a(611));
        this.a.addItem(aS.a(612));
        this.a.addItem(aS.a(613));
        this.a.addItem(aS.a(614));
        this.a.addItem(aS.a(615));
        this.a.setEnabled(false);
        final bF bf = new bF();
        final k k = new k();
        this.a = new Checkbox();
        this.b = new Checkbox();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final bi bi = new bi();
        final bi bi2 = new bi(aS.a(172), (byte)0);
        this.a = a2;
        final String d = this.a.d;
        final b b = (b)a.b.b(this.a.a);
        this.a.a(aS.a(173));
        this.a.d();
        this.setLayout(gridBagLayout);
        bf.setLayout(gridBagLayout);
        bf.setBackground(a.a.h);
        bf.setForeground(a.a.g);
        gridBagConstraints.insets = new Insets(1, 5, 1, 5);
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.anchor = 17;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        bi2.setFont(bk.a);
        gridBagLayout.setConstraints(bi2, gridBagConstraints);
        bf.add(bi2);
        if (b != null) {
            gridBagConstraints.gridwidth = -1;
            final q q;
            (q = new q()).b(b.a);
            gridBagLayout.setConstraints(q, gridBagConstraints);
            bf.add(q);
        }
        gridBagConstraints.gridwidth = 0;
        bi.setFont(bk.a);
        bi.a(d);
        gridBagLayout.setConstraints(bi, gridBagConstraints);
        bf.add(bi);
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagLayout.setConstraints(k, gridBagConstraints);
        bf.add(k);
        gridBagConstraints.fill = 1;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        final i i = new i(this.a);
        gridBagLayout.setConstraints(i, gridBagConstraints);
        bf.add(i);
        this.a.setText(bm.a(aS.a(174), new String[] { this.a.b() }));
        if (a.a(49) || a.a(69)) {
            this.a = new Checkbox(bm.a(aS.a(175), new String[] { a2.a }));
            this.c = new Checkbox(aS.a(380));
            (this.b = new Checkbox(bm.a(aS.a(176), new String[] { (a2.b == null) ? "" : (a.a(69) ? ax.a(a2.b) : a2.b) }))).setEnabled(a2.b != null);
            gridBagConstraints.fill = 0;
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.weighty = 0.0;
            if (a.a(49)) {
                gridBagLayout.setConstraints(this.a, gridBagConstraints);
                bf.add(this.a);
            }
            if (a.a(11)) {
                gridBagLayout.setConstraints(this.c, gridBagConstraints);
                bf.add(this.c);
            }
            if (a.a(69)) {
                gridBagLayout.setConstraints(this.b, gridBagConstraints);
                bf.add(this.b);
            }
            final bi bi3;
            (bi3 = new bi(aS.a(631), (byte)0)).setFont(bk.a);
            gridBagConstraints.gridwidth = -1;
            gridBagConstraints.fill = 0;
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.weighty = 0.0;
            gridBagLayout.setConstraints(bi3, gridBagConstraints);
            bf.add(bi3);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(this.a, gridBagConstraints);
            bf.add(this.a);
        }
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagLayout.setConstraints(bf, gridBagConstraints);
        this.add(bf);
        gridBagConstraints.insets = new Insets(2, 5, 2, 5);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weighty = 0.0;
        this.b.a(aS.a(3));
        this.b.d();
        gridBagLayout.setConstraints(this.b, gridBagConstraints);
        this.add(this.b);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridwidth = 0;
        final g g = new g(this.a);
        gridBagLayout.setConstraints(g, gridBagConstraints);
        this.add(g);
        this.pack();
        this.setVisible(true);
        this.a.requestFocus();
        this.a.selectAll();
    }
}
