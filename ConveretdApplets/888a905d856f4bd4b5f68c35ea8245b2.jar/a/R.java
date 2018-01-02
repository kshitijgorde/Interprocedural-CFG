// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Panel;
import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Label;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.TextField;
import java.awt.Frame;
import java.awt.Event;
import java.awt.Choice;
import java.awt.TextArea;

public final class R extends F
{
    private ad q;
    private ad w;
    private TextArea q;
    private Choice q;
    private Choice w;
    private ap q;
    private M q;
    private int q;
    private dS q;
    private dS w;
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key == 10 || event.key == cK.q) {
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
                    final int selectedIndex = this.q.getSelectedIndex();
                    final int selectedIndex2 = this.w.getSelectedIndex();
                    final dI di;
                    (di = new dI(66307, 1)).w = -1;
                    if (this.q == 1) {
                        di.q(0, 0, -999);
                        if (selectedIndex == 0) {
                            di.q = -1;
                        }
                        else {
                            di.q = ((bp)this.q.q(selectedIndex - 1)).s;
                        }
                    }
                    else {
                        di.q = -1;
                        if (selectedIndex == 0) {
                            di.q(0, 0, -1);
                        }
                        else {
                            di.q(0, 0, ((bp)this.q.q(selectedIndex - 1)).s);
                        }
                    }
                    di.q(0, 0, be.w("Chat Broadcast"));
                    di.q(0, 1, this.q.getText());
                    di.q(0, 1, selectedIndex2);
                    di.q(0, 2, this.q.q());
                    di.q(0, 3, this.w.q());
                    this.q.o(di);
                    this.dispose();
                    return true;
                }
                if (event.target == this.w) {
                    this.dispose();
                    return true;
                }
                break;
            }
            case 1005: {
                if (event.target == this.q || event.target == this.w) {
                    this.q();
                    break;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    private void q() {
        this.q.setForeground(this.q.getBackground());
        this.q.setBackground(this.w.getBackground());
    }
    
    public R(final Frame frame, final ap q, final int n, final int q2) {
        super(frame, be.w("Chat Broadcast"), true);
        this.q = new dS();
        this.w = new dS();
        this.q = new ad(80, 20);
        new ad(80, 20);
        this.w = new ad(80, 20);
        ((dS)(this.q = new TextArea("", 5, 35, 1))).q(bC.w.u);
        this.w.q(bC.w.i);
        new TextField(35);
        this.q = new dR();
        this.w = new dR();
        this.setBackground(bC.w.q);
        this.q = q2;
        final dT dt = new dT();
        final q q3 = new q();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final Label label = new Label(be.w("Send to: "));
        final Label label2 = new Label(be.w("User type: "));
        final Label label3 = new Label(be.w("Chat Broadcast"));
        this.q = q;
        if (q2 == 1) {
            this.q.addItem(be.w("ALL ROOMS"));
            this.q = (M)q.f.clone();
        }
        else {
            this.q.addItem(be.w("ALL SITES"));
            this.q = (M)((cT)q).r.clone();
        }
        for (int i = 0; i < this.q.q; ++i) {
            this.q.addItem(((bp)this.q.q(i)).a);
        }
        this.q.select(0);
        this.w.addItem(be.w(cz.q));
        this.w.addItem(be.w(cz.w));
        this.w.addItem(be.w(cz.e));
        this.setResizable(true);
        this.q.q(be.w("Send"));
        this.q.t();
        this.setLayout(gridBagLayout);
        dt.setLayout(gridBagLayout);
        dt.setBackground(bC.w.i);
        dt.setForeground(bC.w.u);
        gridBagConstraints.insets = new Insets(1, 5, 1, 5);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.anchor = 17;
        label3.setFont(cb.q);
        gridBagLayout.setConstraints(label3, gridBagConstraints);
        dt.add(label3);
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagLayout.setConstraints(q3, gridBagConstraints);
        dt.add(q3);
        gridBagConstraints.gridwidth = -1;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 0.0;
        label.setFont(cb.t);
        gridBagLayout.setConstraints(label, gridBagConstraints);
        dt.add(label);
        gridBagConstraints.gridwidth = 0;
        gridBagLayout.setConstraints(this.q, gridBagConstraints);
        dt.add(this.q);
        gridBagConstraints.gridwidth = 1;
        label2.setFont(cb.t);
        dt.add(label2, gridBagConstraints);
        gridBagConstraints.gridwidth = 0;
        dt.add(this.w, gridBagConstraints);
        final GridBagConstraints gridBagConstraints2;
        (gridBagConstraints2 = new GridBagConstraints()).gridwidth = 0;
        final Panel panel;
        (panel = new Panel()).setLayout(new GridBagLayout());
        panel.add(new Label(be.w("Text Color:")), gridBagConstraints2);
        panel.add(this.q, gridBagConstraints2);
        panel.add(new Label(be.w("Back Color:")), gridBagConstraints2);
        panel.add(this.w, gridBagConstraints2);
        final bZ bz = new bZ(this.q);
        this.q();
        final GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
        gridBagConstraints2.gridwidth = 1;
        gridBagConstraints2.insets = new Insets(0, 4, 0, 4);
        final Panel panel2;
        (panel2 = new Panel()).setLayout(new GridBagLayout());
        panel2.add(bz, gridBagConstraints3);
        gridBagConstraints2.gridwidth = 0;
        panel2.add(panel, gridBagConstraints3);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        dt.add(panel2, gridBagConstraints);
        gridBagLayout.setConstraints(dt, gridBagConstraints);
        this.add(dt);
        gridBagConstraints.insets = new Insets(2, 5, 2, 5);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 1.0;
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
        this.q.requestFocus();
    }
}
