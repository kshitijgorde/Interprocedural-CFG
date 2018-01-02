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

public final class aR extends v
{
    private M q;
    private M w;
    private TextField q;
    private TextField w;
    private TextField e;
    private TextField r;
    private Checkbox q;
    private Checkbox w;
    private br q;
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key == 10 || event.key == bF.q) {
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
                        new bT(null, ak.q("Note"), ak.q("You must provide a name for this room.  Please re-enter this information."), this.q).setVisible(true);
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
                            new bT(null, ak.q("Note"), ak.q("You must provide a password for this room.  Please re-enter this information."), this.q).setVisible(true);
                            return true;
                        }
                        if (!text.equals(text2)) {
                            new bT(null, ak.q("Note"), ak.q("The confirmation password does not match the room password.  Please re-enter this information."), this.q).setVisible(true);
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
    
    public aR(final Frame frame, final br q) {
        super(frame, ak.q("Create New Room"), true);
        this.q = new M(80, 20);
        this.w = new M(80, 20);
        this.setBackground(aU.w.i);
        this.setForeground(aU.w.u);
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
        (label = new Label(ak.q("Name"))).setFont(bf.t);
        gridBagConstraints.gridwidth = -1;
        gridBagLayout.setConstraints(label, gridBagConstraints);
        panel.add(label);
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridwidth = 0;
        final bd bd = new bd(this.e);
        gridBagLayout.setConstraints(bd, gridBagConstraints);
        panel.add(bd);
        this.r = new TextField(25);
        final Label label2;
        (label2 = new Label(ak.q("Topic"))).setFont(bf.t);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        gridBagLayout.setConstraints(label2, gridBagConstraints);
        panel.add(label2);
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridwidth = 0;
        final bd bd2 = new bd(this.r);
        gridBagLayout.setConstraints(bd2, gridBagConstraints);
        panel.add(bd2);
        this.q = new TextField(15);
        final Label label3;
        (label3 = new Label(ak.q("Password"))).setFont(bf.t);
        this.q.setEchoCharacter('*');
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        gridBagLayout.setConstraints(label3, gridBagConstraints);
        panel.add(label3);
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridwidth = 0;
        final bd bd3 = new bd(this.q);
        gridBagLayout.setConstraints(bd3, gridBagConstraints);
        panel.add(bd3);
        this.w = new TextField(15);
        final Label label4 = new Label(ak.q("Confirm"));
        this.w.setEchoCharacter('*');
        label4.setFont(bf.t);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        gridBagLayout.setConstraints(label4, gridBagConstraints);
        panel.add(label4);
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridwidth = 0;
        final bd bd4 = new bd(this.w);
        gridBagLayout.setConstraints(bd4, gridBagConstraints);
        panel.add(bd4);
        (this.q = new Checkbox(ak.q("Private Room"))).setState(true);
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridwidth = 0;
        gridBagLayout.setConstraints(this.q, gridBagConstraints);
        panel.add(this.q);
        (this.w = new Checkbox(ak.q("Permanent Room"))).setState(true);
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
        this.w.q(ak.q("Cancel"));
        this.w.t();
        gridBagLayout.setConstraints(this.w, gridBagConstraints);
        this.add(this.w);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridwidth = 0;
        this.q.q(ak.q("OK"));
        this.q.t();
        final Z z = new Z(this.q);
        gridBagLayout.setConstraints(z, gridBagConstraints);
        this.add(z);
        this.pack();
        this.e.requestFocus();
    }
}
