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

public final class aO extends w
{
    private N q;
    private N w;
    private TextField q;
    private W q;
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key == 10 || event.key == bE.q) {
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
                        new p(null, al.q("Note"), al.q("You must provide a name for your Buddy.  Please re-enter this information."), this.q).setVisible(true);
                        return true;
                    }
                    if (trim.indexOf(44) != -1 || trim.indexOf(34) != -1) {
                        this.q.requestFocus();
                        this.q.selectAll();
                        new p(this.q.q, al.q("Error"), al.q("Names can not have commas or quotes.  Please re-enter this information."), this.q).setVisible(true);
                        return true;
                    }
                    final String s = trim;
                    final as[] array = new as[this.q.e.q];
                    this.q.e.q(array);
                    while (true) {
                        for (int i = 0; i < array.length; ++i) {
                            if (array[i] != null && array[i].o.equals(s)) {
                                final cF cf;
                                (cf = new cF(array[i].a, array[i].o)).t = true;
                                cf.e = array[i].e;
                                cf.q = array[i].q;
                                cf.q = array[i].q;
                                cf.r = array[i].r;
                                cf.q(array[i].q());
                                final cF cf3;
                                final cF cf2 = cf3 = cf;
                                cF cf4 = cf3;
                                if (cf2 == null) {
                                    (cf4 = new cF(-999, trim)).t = false;
                                }
                                if (this.q.q != null) {
                                    this.q.q.q(cf4, true);
                                }
                                this.dispose();
                                return true;
                            }
                        }
                        cF cf3;
                        final cF cf2 = cf3 = null;
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
    
    public aO(final Frame frame, final W q) {
        super(frame, al.q("Add Buddy"), true);
        this.q = new N(80, 20);
        this.w = new N(80, 20);
        this.setBackground(aT.w.i);
        this.setForeground(aT.w.u);
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
        (label = new Label(al.q("Name"))).setFont(be.t);
        gridBagConstraints.gridwidth = -1;
        gridBagLayout.setConstraints(label, gridBagConstraints);
        panel.add(label);
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridwidth = 0;
        final bc bc = new bc(this.q);
        gridBagLayout.setConstraints(bc, gridBagConstraints);
        panel.add(bc);
        gridBagLayout.setConstraints(panel, gridBagConstraints);
        this.add(panel);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        this.w.q(al.q("Cancel"));
        this.w.t();
        gridBagLayout.setConstraints(this.w, gridBagConstraints);
        this.add(this.w);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridwidth = 0;
        this.q.q(al.q("OK"));
        this.q.t();
        final ab ab = new ab(this.q);
        gridBagLayout.setConstraints(ab, gridBagConstraints);
        this.add(ab);
        this.pack();
        this.q.requestFocus();
    }
}
