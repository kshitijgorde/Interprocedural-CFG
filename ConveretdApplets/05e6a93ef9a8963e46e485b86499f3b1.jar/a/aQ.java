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
import java.awt.Checkbox;
import java.awt.TextField;

public final class aQ extends w
{
    private N q;
    private N w;
    private TextField q;
    private TextField w;
    private TextField e;
    private TextField r;
    private Checkbox q;
    private Checkbox w;
    private bq q;
    
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
                    final String trim = this.e.getText().trim();
                    String trim2 = this.r.getText().trim();
                    if (trim.length() == 0) {
                        new p(null, al.q("Note"), al.q("You must provide a name for this room.  Please re-enter this information."), this.q).setVisible(true);
                        return true;
                    }
                    if (trim2.length() == 0) {
                        trim2 = null;
                    }
                    boolean b = false;
                    if (this.w.isEnabled() && this.w.getState()) {
                        b = true;
                    }
                    if (this.q.getState()) {
                        final String text = this.q.getText();
                        final String text2 = this.w.getText();
                        if (text.length() == 0) {
                            new p(null, al.q("Note"), al.q("You must provide a password for this room.  Please re-enter this information."), this.q).setVisible(true);
                            return true;
                        }
                        if (!text.equals(text2)) {
                            new p(null, al.q("Note"), al.q("The confirmation password does not match the room password.  Please re-enter this information."), this.q).setVisible(true);
                            return true;
                        }
                        this.q.q(trim, trim2, text, b);
                        this.dispose();
                    }
                    else {
                        this.q.q(trim, trim2, b);
                        this.dispose();
                    }
                }
                else if (event.target == this.w) {
                    this.dispose();
                }
                else if (event.target == this.q) {
                    this.w.setState(true);
                    this.w.enable();
                    if ((this.q.getState() && (!this.q.q(29) || !this.q.q(38))) || (!this.q.getState() && (!this.q.q(27) || !this.q.q(28)))) {
                        this.w.disable();
                    }
                    if ((this.q.getState() && this.q.q(38)) || (!this.q.getState() && this.q.q(28))) {
                        this.w.setState(false);
                    }
                    if (!this.q.getState()) {
                        this.q.disable();
                        this.w.disable();
                    }
                    else {
                        this.q.enable();
                        this.w.enable();
                    }
                }
                return true;
            }
        }
        return super.handleEvent(event);
    }
    
    public aQ(final Frame frame, final bq q) {
        super(frame, al.q("Create New Room"), true);
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
        this.e = new TextField(20);
        final Label label;
        (label = new Label(al.q("Name"))).setFont(be.t);
        gridBagConstraints.gridwidth = -1;
        gridBagLayout.setConstraints(label, gridBagConstraints);
        panel.add(label);
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridwidth = 0;
        final bc bc = new bc(this.e);
        gridBagLayout.setConstraints(bc, gridBagConstraints);
        panel.add(bc);
        this.r = new TextField(25);
        final Label label2;
        (label2 = new Label(al.q("Topic"))).setFont(be.t);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        gridBagLayout.setConstraints(label2, gridBagConstraints);
        panel.add(label2);
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridwidth = 0;
        final bc bc2 = new bc(this.r);
        gridBagLayout.setConstraints(bc2, gridBagConstraints);
        panel.add(bc2);
        this.q = new TextField(15);
        final Label label3;
        (label3 = new Label(al.q("Password"))).setFont(be.t);
        this.q.setEchoCharacter('*');
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        gridBagLayout.setConstraints(label3, gridBagConstraints);
        panel.add(label3);
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridwidth = 0;
        final bc bc3 = new bc(this.q);
        gridBagLayout.setConstraints(bc3, gridBagConstraints);
        panel.add(bc3);
        this.w = new TextField(15);
        final Label label4 = new Label(al.q("Confirm"));
        this.w.setEchoCharacter('*');
        label4.setFont(be.t);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        gridBagLayout.setConstraints(label4, gridBagConstraints);
        panel.add(label4);
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridwidth = 0;
        final bc bc4 = new bc(this.w);
        gridBagLayout.setConstraints(bc4, gridBagConstraints);
        panel.add(bc4);
        (this.q = new Checkbox(al.q("Private Room"))).setState(true);
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridwidth = 0;
        gridBagLayout.setConstraints(this.q, gridBagConstraints);
        panel.add(this.q);
        (this.w = new Checkbox(al.q("Permanent Room"))).setState(true);
        gridBagConstraints.gridwidth = 0;
        gridBagLayout.setConstraints(this.w, gridBagConstraints);
        panel.add(this.w);
        if (!q.q(29) && !q.q(38)) {
            this.q.setState(false);
            this.q.disable();
            this.q.disable();
            this.w.disable();
        }
        if (!q.q(27) && !q.q(28)) {
            this.q.disable();
        }
        if ((this.q.getState() && (!q.q(29) || !q.q(38))) || (!this.q.getState() && (!q.q(27) || !q.q(28)))) {
            this.w.disable();
        }
        if ((this.q.getState() && q.q(38)) || (!this.q.getState() && q.q(28))) {
            this.w.setState(false);
        }
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
        this.e.requestFocus();
    }
}
