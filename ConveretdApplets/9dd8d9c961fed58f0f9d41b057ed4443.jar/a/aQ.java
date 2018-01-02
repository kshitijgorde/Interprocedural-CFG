// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Component;
import java.awt.Label;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Frame;
import java.awt.Event;
import java.awt.TextField;

public final class aQ extends v
{
    private M q;
    private M w;
    private TextField q;
    private W q;
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key == 10 || event.key == bF.q) {
                    this.q.r();
                    return true;
                }
                if (event.key == 27) {
                    this.w.r();
                    return true;
                }
                break;
            }
            case 1001: {
                if (event.target == this.q) {
                    final String trim;
                    if ((trim = this.q.getText().trim()).length() == 0) {
                        new bT(null, ak.q("Note"), ak.q("You must provide a name for your Buddy.  Please re-enter this information."), this.q).setVisible(true);
                        return true;
                    }
                    if (trim.indexOf(44) != -1 || trim.indexOf(34) != -1) {
                        this.q.requestFocus();
                        this.q.selectAll();
                        new bT(this.q.q, ak.q("Error"), ak.q("Names can not have commas or quotes.  Please re-enter this information."), this.q).setVisible(true);
                        return true;
                    }
                    final String s = trim;
                    final ar[] array = new ar[this.q.e.q];
                    this.q.e.q(array);
                    while (true) {
                        for (int i = 0; i < array.length; ++i) {
                            if (array[i] != null && array[i].o.equals(s)) {
                                final cI ci;
                                (ci = new cI(array[i].s, array[i].o)).t = true;
                                ci.e = array[i].e;
                                ci.q = array[i].q;
                                ci.q = array[i].q;
                                ci.r = array[i].r;
                                ci.q(array[i].q());
                                final cI ci3;
                                final cI ci2 = ci3 = ci;
                                cI ci4 = ci3;
                                if (ci2 == null) {
                                    (ci4 = new cI(-999, trim)).t = false;
                                }
                                if (this.q.q != null) {
                                    this.q.q.q(ci4, true);
                                }
                                this.dispose();
                                return true;
                            }
                        }
                        cI ci3;
                        final cI ci2 = ci3 = null;
                        continue;
                    }
                }
                else if (event.target == this.w) {
                    this.dispose();
                }
                return true;
            }
        }
        return super.handleEvent(event);
    }
    
    public aQ(final Frame frame, final W q) {
        super(frame, ak.q("Add Buddy"), true);
        this.q = new M(80, 20);
        this.w = new M(80, 20);
        this.setBackground(aU.w.i);
        this.setForeground(aU.w.u);
        this.q = q;
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final Panel panel = new Panel();
        this.setResizable(false);
        this.setLayout(gridBagLayout);
        panel.setLayout(gridBagLayout);
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        gridBagConstraints.anchor = 13;
        this.q = new TextField(20);
        final Label label;
        (label = new Label(ak.q("Name"))).setFont(bf.t);
        gridBagConstraints.gridwidth = -1;
        gridBagLayout.setConstraints(label, gridBagConstraints);
        panel.add(label);
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridwidth = 0;
        final bd bd = new bd(this.q);
        gridBagLayout.setConstraints(bd, gridBagConstraints);
        panel.add(bd);
        gridBagLayout.setConstraints(panel, gridBagConstraints);
        this.add(panel);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        this.w.q(ak.q("Cancel"));
        this.w.t();
        gridBagLayout.setConstraints(this.w, gridBagConstraints);
        this.add(this.w);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridwidth = 0;
        this.q.q(ak.q("OK"));
        this.q.t();
        final Z z = new Z(this.q);
        gridBagLayout.setConstraints(z, gridBagConstraints);
        this.add(z);
        this.pack();
        this.q.requestFocus();
    }
}
