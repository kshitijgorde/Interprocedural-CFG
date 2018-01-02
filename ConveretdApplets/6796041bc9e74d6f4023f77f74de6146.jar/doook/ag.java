// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Color;
import java.awt.Component;
import java.awt.Label;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Event;
import java.awt.Frame;
import java.awt.Checkbox;
import java.awt.TextField;

public final class ag extends p
{
    private aS a;
    private aS b;
    private TextField c;
    private TextField d;
    private TextField e;
    private TextField f;
    private Checkbox e;
    private Checkbox n;
    private t f;
    private Frame g;
    private aX e;
    private aX b;
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key == 10 || event.key == doook.f.g) {
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
                    final String trim = this.e.getText().trim();
                    String trim2 = this.f.getText().trim();
                    if (trim.length() == 0) {
                        new E(this.g, ao.e("Note"), ao.e("You must provide a name for this room.  Please re-enter this information."), this.f).setVisible(true);
                        return true;
                    }
                    if (trim2.length() == 0) {
                        trim2 = null;
                    }
                    boolean b = false;
                    if (this.n.isEnabled() && this.n.getState()) {
                        b = true;
                    }
                    if (this.e.getState()) {
                        final String text = this.c.getText();
                        final String text2 = this.d.getText();
                        if (text.length() == 0) {
                            new E(this.g, ao.e("Note"), ao.e("You must provide a password for this room.  Please re-enter this information."), this.f).setVisible(true);
                            return true;
                        }
                        if (!text.equals(text2)) {
                            new E(this.g, ao.e("Note"), ao.e("The confirmation password does not match the room password.  Please re-enter this information."), this.f).setVisible(true);
                            return true;
                        }
                        this.f.a(trim, trim2, text, b, this.e.aA, this.b.aA);
                        this.dispose();
                    }
                    else {
                        this.f.a(trim, trim2, b, this.e.aA, this.b.aA);
                        this.dispose();
                    }
                }
                else if (event.target == this.b) {
                    this.dispose();
                }
                else if (event.target == this.e) {
                    this.n.setState(true);
                    this.n.setEnabled(true);
                    if ((this.e.getState() && (!this.f.d(29) || !this.f.d(38))) || (!this.e.getState() && (!this.f.d(27) || !this.f.d(28)))) {
                        this.n.setEnabled(false);
                    }
                    if ((this.e.getState() && this.f.d(38)) || (!this.e.getState() && this.f.d(28))) {
                        this.n.setState(false);
                    }
                    if (!this.e.getState()) {
                        this.c.setEnabled(false);
                        this.d.setEnabled(false);
                    }
                    else {
                        this.c.setEnabled(true);
                        this.d.setEnabled(true);
                    }
                }
                return true;
            }
        }
        return super.handleEvent(event);
    }
    
    public ag(final Frame frame, final t f) {
        super(frame, ao.e("Create New Room"), true);
        this.a = new aS(80, 20);
        this.b = new aS(80, 20);
        this.setBackground(f.a.g);
        this.setForeground(f.a.f);
        this.f = f;
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final Panel panel = new Panel();
        this.setResizable(false);
        this.setLayout(gridBagLayout);
        panel.setLayout(gridBagLayout);
        gridBagConstraints.fill = 1;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        gridBagConstraints.anchor = 13;
        this.e = new TextField(20);
        final Label label = new Label(ao.e("Name"));
        label.setFont(bL.g);
        gridBagConstraints.gridwidth = -1;
        gridBagLayout.setConstraints(label, gridBagConstraints);
        panel.add(label);
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridwidth = 0;
        final aR ar = new aR(this.e);
        gridBagLayout.setConstraints(ar, gridBagConstraints);
        panel.add(ar);
        this.f = new TextField(25);
        final Label label2 = new Label(ao.e("Topic"));
        label2.setFont(bL.g);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        gridBagLayout.setConstraints(label2, gridBagConstraints);
        panel.add(label2);
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridwidth = 0;
        final aR ar2 = new aR(this.f);
        gridBagLayout.setConstraints(ar2, gridBagConstraints);
        panel.add(ar2);
        this.c = new TextField(15);
        final Label label3 = new Label(ao.e("Password"));
        label3.setFont(bL.g);
        this.c.setEchoChar('*');
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        gridBagLayout.setConstraints(label3, gridBagConstraints);
        panel.add(label3);
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridwidth = 0;
        final aR ar3 = new aR(this.c);
        gridBagLayout.setConstraints(ar3, gridBagConstraints);
        panel.add(ar3);
        this.d = new TextField(15);
        final Label label4 = new Label(ao.e("Confirm"));
        this.d.setEchoChar('*');
        label4.setFont(bL.g);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        gridBagLayout.setConstraints(label4, gridBagConstraints);
        panel.add(label4);
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridwidth = 0;
        final aR ar4 = new aR(this.d);
        gridBagLayout.setConstraints(ar4, gridBagConstraints);
        panel.add(ar4);
        (this.e = new aX(f)).a(this.f.k, false, true);
        (this.b = new aX(f)).a(this.f.k, false, true);
        this.e.a(new Color(0));
        this.b.a(new Color(15658734));
        if (f.d(0)) {
            final Label label5 = new Label(ao.e("Room Color:"));
            label5.setFont(bL.g);
            gridBagConstraints.anchor = 13;
            gridBagConstraints.gridwidth = -1;
            gridBagLayout.setConstraints(label5, gridBagConstraints);
            panel.add(label5);
            gridBagConstraints.anchor = 17;
            gridBagConstraints.gridwidth = 0;
            gridBagConstraints.fill = 0;
            gridBagLayout.setConstraints(this.e, gridBagConstraints);
            panel.add(this.e);
            final Label label6 = new Label(ao.e("Back Color:"));
            label5.setFont(bL.g);
            gridBagConstraints.anchor = 13;
            gridBagConstraints.gridwidth = -1;
            gridBagLayout.setConstraints(label5, gridBagConstraints);
            panel.add(label6);
            gridBagConstraints.anchor = 17;
            gridBagConstraints.gridwidth = 0;
            gridBagConstraints.fill = 0;
            gridBagLayout.setConstraints(this.b, gridBagConstraints);
            panel.add(this.b);
        }
        (this.e = new Checkbox(ao.e("Private Room"))).setState(true);
        gridBagConstraints.fill = 0;
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridwidth = 0;
        gridBagLayout.setConstraints(this.e, gridBagConstraints);
        panel.add(this.e);
        (this.n = new Checkbox(ao.e("Permanent Room"))).setState(true);
        gridBagConstraints.gridwidth = 0;
        gridBagLayout.setConstraints(this.n, gridBagConstraints);
        panel.add(this.n);
        if (!f.d(29) && !f.d(38)) {
            this.e.setState(false);
            this.e.setEnabled(false);
            this.c.setEnabled(false);
            this.d.setEnabled(false);
        }
        if (!f.d(27) && !f.d(28)) {
            this.e.setEnabled(false);
        }
        if ((this.e.getState() && (!f.d(29) || !f.d(38))) || (!this.e.getState() && (!f.d(27) || !f.d(28)))) {
            this.n.setEnabled(false);
        }
        if ((this.e.getState() && f.d(38)) || (!this.e.getState() && f.d(28))) {
            this.n.setState(false);
        }
        gridBagLayout.setConstraints(panel, gridBagConstraints);
        this.add(panel);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.anchor = 13;
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
