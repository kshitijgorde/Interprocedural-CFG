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

public final class ca extends ah
{
    private g q;
    private g w;
    private TextArea q;
    private Choice q;
    private Choice w;
    private cV q;
    private dW q;
    private int q;
    private j q;
    private j w;
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key == 10 || event.key == ef.q) {
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
                    final es es;
                    (es = new es(66307, 1)).w = -1;
                    if (this.q == 1) {
                        es.q(0, 0, -999);
                        if (selectedIndex == 0) {
                            es.q = -1;
                        }
                        else {
                            es.q = ((bZ)this.q.q(selectedIndex - 1)).q();
                        }
                    }
                    else {
                        es.q = -1;
                        if (selectedIndex == 0) {
                            es.q(0, 0, -1);
                        }
                        else {
                            es.q(0, 0, ((bZ)this.q.q(selectedIndex - 1)).q());
                        }
                    }
                    es.q(0, 0, eb.q("Chat Broadcast"));
                    es.q(0, 1, this.q.getText());
                    es.q(0, 1, selectedIndex2);
                    es.q(0, 2, this.q.q());
                    es.q(0, 3, this.w.q());
                    this.q.q(es);
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
        this.q.setForeground(this.q.q());
        this.q.setBackground(this.w.q());
    }
    
    public ca(final Frame frame, final cV q, final int n, final int q2) {
        super(frame, eb.q("Chat Broadcast"), true);
        this.q = new j();
        this.w = new j();
        this.q = new g(80, 20);
        new g(80, 20);
        this.w = new g(80, 20);
        ((j)(this.q = new TextArea("", 5, 35, 1))).q(cf.w.u);
        this.w.q(cf.w.i);
        new TextField(35);
        this.q = new h();
        this.w = new h();
        this.setBackground(cf.w.q);
        this.q = q2;
        final s s = new s();
        final q q3 = new q();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final Label label = new Label(eb.q("Send to: "));
        final Label label2 = new Label(eb.q("User type: "));
        final Label label3 = new Label(eb.q("Chat Broadcast"));
        this.q = q;
        if (q2 == 1) {
            this.q.addItem(eb.q("ALL ROOMS"));
            this.q = (dW)q.y.clone();
        }
        else {
            this.q.addItem(eb.q("ALL SITES"));
            this.q = (dW)((dz)q).n.clone();
        }
        for (int i = 0; i < this.q.q(); ++i) {
            this.q.addItem(((bZ)this.q.q(i)).getName());
        }
        this.q.select(0);
        this.w.addItem(eb.q(cb.q));
        this.w.addItem(eb.q(cb.w));
        this.w.addItem(eb.q(cb.e));
        this.setResizable(true);
        this.q.q(eb.q("Send"));
        this.q.t();
        this.setLayout(gridBagLayout);
        s.setLayout(gridBagLayout);
        s.setBackground(cf.w.i);
        s.setForeground(cf.w.u);
        gridBagConstraints.insets = new Insets(1, 5, 1, 5);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.anchor = 17;
        label3.setFont(m.q);
        gridBagLayout.setConstraints(label3, gridBagConstraints);
        s.add(label3);
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagLayout.setConstraints(q3, gridBagConstraints);
        s.add(q3);
        gridBagConstraints.gridwidth = -1;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 0.0;
        label.setFont(m.t);
        gridBagLayout.setConstraints(label, gridBagConstraints);
        s.add(label);
        gridBagConstraints.gridwidth = 0;
        gridBagLayout.setConstraints(this.q, gridBagConstraints);
        s.add(this.q);
        gridBagConstraints.gridwidth = 1;
        label2.setFont(m.t);
        s.add(label2, gridBagConstraints);
        gridBagConstraints.gridwidth = 0;
        s.add(this.w, gridBagConstraints);
        final GridBagConstraints gridBagConstraints2;
        (gridBagConstraints2 = new GridBagConstraints()).gridwidth = 0;
        final Panel panel;
        (panel = new Panel()).setLayout(new GridBagLayout());
        panel.add(new Label(eb.q("Text Color:")), gridBagConstraints2);
        panel.add(this.q, gridBagConstraints2);
        panel.add(new Label(eb.q("Back Color:")), gridBagConstraints2);
        panel.add(this.w, gridBagConstraints2);
        final t t = new t(this.q);
        this.q();
        final GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
        gridBagConstraints2.gridwidth = 1;
        gridBagConstraints2.insets = new Insets(0, 4, 0, 4);
        final Panel panel2;
        (panel2 = new Panel()).setLayout(new GridBagLayout());
        panel2.add(t, gridBagConstraints3);
        gridBagConstraints2.gridwidth = 0;
        panel2.add(panel, gridBagConstraints3);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        s.add(panel2, gridBagConstraints);
        gridBagLayout.setConstraints(s, gridBagConstraints);
        this.add(s);
        gridBagConstraints.insets = new Insets(2, 5, 2, 5);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 1.0;
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
        this.q.requestFocus();
    }
}
