// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.Frame;
import java.awt.TextField;

public abstract class bK extends p
{
    protected aS a;
    protected aS b;
    protected TextField c;
    protected TextField d;
    protected TextField e;
    protected Frame c;
    protected u a;
    
    protected void a() {
        new E(this.c, ao.e("Note"), ao.e("The confirmation password does not match the new password.  Please re-enter this information."), this.a).setVisible(true);
    }
    
    protected void c() {
        new E(this.c, ao.e("Note"), ao.e("You must enter a new password.  Please re-enter this information."), this.a).setVisible(true);
    }
    
    protected void b() {
        new E(this.c, ao.e("Note"), ao.e("Passwords must be at least three characters long.  Please re-enter this information."), this.a).setVisible(true);
    }
    
    public bK(final Frame frame, final u a, final boolean b) {
        super(frame, ao.e("Change Password"), true);
        this.a = new aS(80, 20);
        this.b = new aS(80, 20);
        this.c = new TextField(15);
        this.d = new TextField(15);
        this.e = new TextField(15);
        this.a = a;
        this.setBackground(a.a.a);
        final Label label = new Label(ao.e("Current Password"));
        final Label label2 = new Label(ao.e("New Password"));
        final Label label3 = new Label(ao.e("Confirm"));
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final Panel panel = new Panel();
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        gridBagConstraints.anchor = 17;
        this.setResizable(false);
        this.setLayout(gridBagLayout);
        panel.setLayout(gridBagLayout);
        panel.setBackground(a.a.g);
        panel.setForeground(a.a.f);
        label.setFont(bL.g);
        label2.setFont(bL.g);
        label3.setFont(bL.g);
        this.e.setEchoCharacter('*');
        this.c.setEchoCharacter('*');
        this.d.setEchoCharacter('*');
        if (b) {
            final c c = new c(ao.e("Your current password has expired and must be changed.  Please enter a new password now."));
            c.setFont(bL.a);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(c, gridBagConstraints);
            panel.add(c);
        }
        gridBagConstraints.gridwidth = -1;
        gridBagLayout.setConstraints(label, gridBagConstraints);
        panel.add(label);
        gridBagConstraints.gridwidth = 0;
        final aR ar = new aR(this.e);
        gridBagLayout.setConstraints(ar, gridBagConstraints);
        panel.add(ar);
        gridBagConstraints.gridwidth = -1;
        gridBagLayout.setConstraints(label2, gridBagConstraints);
        panel.add(label2);
        gridBagConstraints.gridwidth = 0;
        final aR ar2 = new aR(this.c);
        gridBagLayout.setConstraints(ar2, gridBagConstraints);
        panel.add(ar2);
        gridBagConstraints.gridwidth = -1;
        gridBagLayout.setConstraints(label3, gridBagConstraints);
        panel.add(label3);
        gridBagConstraints.gridwidth = 0;
        final aR ar3 = new aR(this.d);
        gridBagLayout.setConstraints(ar3, gridBagConstraints);
        panel.add(ar3);
        gridBagLayout.setConstraints(panel, gridBagConstraints);
        this.add(panel);
        gridBagConstraints.anchor = 13;
        if (!b) {
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.gridwidth = -1;
            this.b.a(ao.e("Cancel"));
            this.b.t();
            gridBagLayout.setConstraints(this.b, gridBagConstraints);
            this.add(this.b);
        }
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridwidth = 0;
        this.a.a(ao.e("OK"));
        this.a.t();
        final aQ aq = new aQ(this.a);
        gridBagLayout.setConstraints(aq, gridBagConstraints);
        this.add(aq);
        this.pack();
        this.e.requestFocus();
    }
}
