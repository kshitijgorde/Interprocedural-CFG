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
import java.awt.Event;
import java.awt.Frame;
import java.awt.TextField;

public final class bv extends p
{
    protected aS a;
    protected aS b;
    protected TextField c;
    protected TextField d;
    protected TextField e;
    protected Frame c;
    protected u a;
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key == 10 || event.key == f.g) {
                    this.a.s();
                    return true;
                }
                if (event.key == 27) {
                    this.b.s();
                    return true;
                }
                break;
            }
            case 1001: {
                if (event.target == this.a) {
                    final String text = this.c.getText();
                    final String text2 = this.d.getText();
                    if (this.e.getText().length() == 0) {
                        this.e.requestFocus();
                        this.v();
                    }
                    else if (text.length() == 0) {
                        this.c.requestFocus();
                        this.c();
                    }
                    else if (text.length() < 6) {
                        this.c.requestFocus();
                        this.b();
                    }
                    else if (text.equals(text2)) {
                        final a a = new a(this.e.getText().trim());
                        final cD cd = new cD(262912, 1);
                        cd.a(0, 0, new a(text));
                        cd.a(0, 1, a);
                        cd.a(0, 0, -999);
                        cd.a(0, 1, -999);
                        this.a.o(cd);
                        this.dispose();
                    }
                    else {
                        this.a();
                    }
                }
                else if (event.target == this.b) {
                    this.dispose();
                }
                return true;
            }
        }
        return super.handleEvent(event);
    }
    
    protected void a() {
        new E(this.c, ao.e("Note"), ao.e("The confirmation password does not match the new password.  Please re-enter this information."), this.a).setVisible(true);
    }
    
    protected void v() {
        new E(this.c, ao.e("Note"), ao.e("You must enter your old password.  Please re-enter this information."), this.a).setVisible(true);
    }
    
    protected void c() {
        new E(this.c, ao.e("Note"), ao.e("You must enter a new password.  Please re-enter this information."), this.a).setVisible(true);
    }
    
    protected void b() {
        new E(this.c, ao.e("Note"), ao.e("Passwords must be at least six characters long.  Please re-enter this information."), this.a).setVisible(true);
    }
    
    public bv(final Frame frame, final u a) {
        super(frame, ao.e("Change FTP Password"), true);
        this.a = new aS(80, 20);
        this.b = new aS(80, 20);
        this.c = new TextField(15);
        this.d = new TextField(15);
        this.e = new TextField(15);
        this.a = a;
        this.setBackground(a.a.a);
        final Label label = new Label(ao.e("Current FTP Password"));
        final Label label2 = new Label(ao.e("New FTP Password"));
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
        this.e.setEchoChar('*');
        this.c.setEchoChar('*');
        this.d.setEchoChar('*');
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
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.gridwidth = -1;
        this.b.a(ao.e("Cancel"));
        this.b.t();
        gridBagLayout.setConstraints(this.b, gridBagConstraints);
        this.add(this.b);
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
