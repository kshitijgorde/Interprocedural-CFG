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

public abstract class dw extends ah
{
    protected g q;
    protected g w;
    protected TextField q;
    protected TextField w;
    protected TextField e;
    protected cV q;
    
    protected final void q() {
        new b(null, eb.q("Note"), eb.q("The confirmation password does not match the new password.  Please re-enter this information."), this.q).setVisible(true);
    }
    
    protected final void e() {
        new b(null, eb.q("Note"), eb.q("You must enter a new password.  Please re-enter this information."), this.q).setVisible(true);
    }
    
    protected final void r() {
        new b(null, eb.q("Note"), eb.q("Passwords must be at least three characters long.  Please re-enter this information."), this.q).setVisible(true);
    }
    
    public dw(final Frame frame, final String s, final cV q, final boolean b, final boolean b2) {
        super(frame, eb.q(s), true);
        this.q = new g(80, 20);
        this.w = new g(80, 20);
        this.q = new TextField(15);
        this.w = new TextField(15);
        this.e = new TextField(15);
        this.q = q;
        this.setBackground(cf.w.q);
        final Label label = new Label(eb.q("Current Password"));
        final Label label2 = new Label(eb.q("New Password"));
        final Label label3 = new Label(eb.q("Confirm"));
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final Panel panel = new Panel();
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        gridBagConstraints.anchor = 17;
        this.setResizable(false);
        this.setLayout(gridBagLayout);
        panel.setLayout(gridBagLayout);
        panel.setBackground(cf.w.i);
        panel.setForeground(cf.w.u);
        label.setFont(m.t);
        label2.setFont(m.t);
        label3.setFont(m.t);
        this.e.setEchoCharacter('*');
        this.q.setEchoCharacter('*');
        this.w.setEchoCharacter('*');
        if (b) {
            final H h;
            (h = new H(eb.q("Your current password has expired and must be changed.  Please enter a new password now."))).setFont(m.w);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(h, gridBagConstraints);
            panel.add(h);
        }
        gridBagConstraints.gridwidth = -1;
        gridBagLayout.setConstraints(label, gridBagConstraints);
        panel.add(label);
        gridBagConstraints.gridwidth = 0;
        final t t = new t(this.e);
        gridBagLayout.setConstraints(t, gridBagConstraints);
        panel.add(t);
        gridBagConstraints.gridwidth = -1;
        gridBagLayout.setConstraints(label2, gridBagConstraints);
        panel.add(label2);
        gridBagConstraints.gridwidth = 0;
        final t t2 = new t(this.q);
        gridBagLayout.setConstraints(t2, gridBagConstraints);
        panel.add(t2);
        gridBagConstraints.gridwidth = -1;
        gridBagLayout.setConstraints(label3, gridBagConstraints);
        panel.add(label3);
        gridBagConstraints.gridwidth = 0;
        final t t3 = new t(this.w);
        gridBagLayout.setConstraints(t3, gridBagConstraints);
        panel.add(t3);
        gridBagLayout.setConstraints(panel, gridBagConstraints);
        this.add(panel);
        gridBagConstraints.anchor = 13;
        if (!b) {
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.gridwidth = -1;
            this.w.q(eb.q("Cancel"));
            this.w.t();
            gridBagLayout.setConstraints(this.w, gridBagConstraints);
            this.add(this.w);
        }
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridwidth = 0;
        this.q.q(eb.q("OK"));
        this.q.t();
        final f f = new f(this.q);
        gridBagLayout.setConstraints(f, gridBagConstraints);
        this.add(f);
        this.pack();
        this.e.requestFocus();
    }
}
