// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.util.NoSuchElementException;
import java.awt.Event;
import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Label;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Frame;
import java.awt.Point;
import java.util.Vector;
import java.awt.Checkbox;
import java.awt.Dialog;

public final class ac extends Dialog
{
    private l q;
    public static boolean q;
    private static Checkbox q;
    private cU q;
    private n q;
    private Vector q;
    private g q;
    private g w;
    private Point q;
    
    public ac(final l q, final Point q2) {
        super(new Frame(), eb.q("Select emotions"), false);
        ac.q = false;
        this.q = q;
        this.q = q2;
        this.q = (cU)this.q.q();
        this.q = this.q.q.q();
    }
    
    public final synchronized void hide() {
        ac.q = false;
        super.hide();
    }
    
    private void e() {
        this.q.q();
        for (int i = 0; i < this.q.size(); ++i) {
            final cj cj;
            if ((cj = this.q.elementAt(i)).q(36)) {
                this.q.q(cj);
            }
        }
    }
    
    public final void q() {
        this.setBackground(cf.w.q);
        final s s = new s();
        final q q = new q();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final Label label = new Label(eb.q("Select emotion"));
        this.setResizable(false);
        this.setLayout(gridBagLayout);
        s.setLayout(gridBagLayout);
        s.setBackground(cf.w.i);
        s.setForeground(cf.w.u);
        gridBagConstraints.insets = new Insets(1, 5, 1, 5);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.anchor = 17;
        label.setFont(m.q);
        gridBagLayout.setConstraints(label, gridBagConstraints);
        s.add(label);
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagLayout.setConstraints(q, gridBagConstraints);
        s.add(q);
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 0.0;
        (this.q = new n(this)).setSize(350, 200);
        this.e();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.anchor = 10;
        gridBagConstraints.fill = 1;
        gridBagConstraints.gridwidth = 0;
        gridBagLayout.setConstraints(this.q, gridBagConstraints);
        s.add(this.q);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagLayout.setConstraints(s, gridBagConstraints);
        this.add(s);
        if (ac.q == null) {
            (ac.q = new Checkbox(eb.q("Close after selection"))).setForeground(cf.w.u);
            ac.q.setState(true);
        }
        gridBagConstraints.insets = new Insets(2, 5, 2, 5);
        gridBagConstraints.gridwidth = -2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 0;
        gridBagLayout.setConstraints(ac.q, gridBagConstraints);
        this.add(ac.q);
        gridBagConstraints.insets = new Insets(2, 5, 2, 5);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 0;
        (this.q = new g(eb.q("Close"))).t();
        gridBagLayout.setConstraints(this.q, gridBagConstraints);
        this.add(this.q);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.weightx = 0.0;
        (this.w = new g(eb.q("Insert"))).t();
        gridBagLayout.setConstraints(this.w, gridBagConstraints);
        this.add(this.w);
        this.pack();
    }
    
    public final synchronized void w() {
        ac.q = true;
        this.setLocation(this.q);
        this.doLayout();
        this.show(true);
        this.setVisible(true);
    }
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key == 27) {
                    this.q.q();
                    return true;
                }
                break;
            }
            case 1001: {
                if (event.target == this.q) {
                    this.q.q();
                    return true;
                }
                if (event.target == this.w) {
                    a.q(" " + ((cj)this.q.q()).getName(), this.q.q());
                    this.requestFocus();
                    return true;
                }
                if (event.target instanceof o) {
                    try {
                        a.q(" " + ((cj)this.q.q()).getName(), this.q.q());
                    }
                    catch (NoSuchElementException ex) {}
                    if (ac.q.getState()) {
                        this.q.q();
                    }
                }
                return true;
            }
            case 201: {
                this.q.q();
                break;
            }
        }
        return super.handleEvent(event);
    }
}
