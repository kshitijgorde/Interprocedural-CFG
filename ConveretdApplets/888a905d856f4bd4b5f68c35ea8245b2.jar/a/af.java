// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.Frame;
import java.awt.TextField;

public abstract class af extends F
{
    protected ad q;
    protected ad w;
    protected TextField q;
    protected TextField w;
    protected TextField e;
    protected ap q;
    
    protected final void q() {
        new dd(null, be.w("Note"), be.w("The confirmation password does not match the new password.  Please re-enter this information."), this.q).setVisible(true);
    }
    
    protected final void e() {
        new dd(null, be.w("Note"), be.w("You must enter a new password.  Please re-enter this information."), this.q).setVisible(true);
    }
    
    protected final void r() {
        new dd(null, be.w("Note"), be.w("Passwords must be at least three characters long.  Please re-enter this information."), this.q).setVisible(true);
    }
    
    public af(final Frame frame, final String s, final ap q, final boolean b, final boolean b2) {
        super(frame, be.w(s), true);
        this.q = new ad(80, 20);
        this.w = new ad(80, 20);
        this.q = new TextField(15);
        this.w = new TextField(15);
        this.e = new TextField(15);
        this.q = q;
        this.setBackground(bC.w.q);
        final Label label = new Label(be.w("Current Password"));
        final Label label2 = new Label(be.w("New Password"));
        final Label label3 = new Label(be.w("Confirm"));
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final Panel panel = new Panel();
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        gridBagConstraints.anchor = 17;
        this.setResizable(false);
        this.setLayout(gridBagLayout);
        panel.setLayout(gridBagLayout);
        panel.setBackground(bC.w.i);
        panel.setForeground(bC.w.u);
        label.setFont(cb.t);
        label2.setFont(cb.t);
        label3.setFont(cb.t);
        this.e.setEchoCharacter('*');
        this.q.setEchoCharacter('*');
        this.w.setEchoCharacter('*');
        if (b) {
            final u u;
            (u = new u(be.w("Your current password has expired and must be changed.  Please enter a new password now."))).setFont(cb.w);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(u, gridBagConstraints);
            panel.add(u);
        }
        gridBagConstraints.gridwidth = -1;
        gridBagLayout.setConstraints(label, gridBagConstraints);
        panel.add(label);
        gridBagConstraints.gridwidth = 0;
        final bZ bz = new bZ(this.e);
        gridBagLayout.setConstraints(bz, gridBagConstraints);
        panel.add(bz);
        gridBagConstraints.gridwidth = -1;
        gridBagLayout.setConstraints(label2, gridBagConstraints);
        panel.add(label2);
        gridBagConstraints.gridwidth = 0;
        final bZ bz2 = new bZ(this.q);
        gridBagLayout.setConstraints(bz2, gridBagConstraints);
        panel.add(bz2);
        gridBagConstraints.gridwidth = -1;
        gridBagLayout.setConstraints(label3, gridBagConstraints);
        panel.add(label3);
        gridBagConstraints.gridwidth = 0;
        final bZ bz3 = new bZ(this.w);
        gridBagLayout.setConstraints(bz3, gridBagConstraints);
        panel.add(bz3);
        gridBagLayout.setConstraints(panel, gridBagConstraints);
        this.add(panel);
        gridBagConstraints.anchor = 13;
        if (!b) {
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.gridwidth = -1;
            this.w.q(be.w("Cancel"));
            this.w.t();
            gridBagLayout.setConstraints(this.w, gridBagConstraints);
            this.add(this.w);
        }
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridwidth = 0;
        this.q.q(be.w("OK"));
        this.q.t();
        final as as = new as(this.q);
        gridBagLayout.setConstraints(as, gridBagConstraints);
        this.add(as);
        this.pack();
        this.e.requestFocus();
    }
}
