// 
// Decompiled by Procyon v0.5.30
// 

package doook;

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

public final class ah extends N
{
    private al b;
    private al c;
    private TextField b;
    private TextField c;
    private TextField d;
    private TextField a;
    private Checkbox f;
    private Checkbox g;
    private be h;
    private Frame c;
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key == 10 || event.key == F.e) {
                    this.b.e();
                    return true;
                }
                if (event.key == 27) {
                    this.c.e();
                    return true;
                }
                break;
            }
            case 1001: {
                if (event.target == this.b) {
                    final String trim = this.d.getText().trim();
                    String trim2 = this.a.getText().trim();
                    if (trim.length() == 0) {
                        new E(this.c, aG.a("Note"), aG.a("You must provide a name for this room.  Please re-enter this information."), this.h).setVisible(true);
                        return true;
                    }
                    if (trim2.length() == 0) {
                        trim2 = null;
                    }
                    boolean b = false;
                    if (this.g.isEnabled() && this.g.getState()) {
                        b = true;
                    }
                    if (this.f.getState()) {
                        final String text = this.b.getText();
                        final String text2 = this.c.getText();
                        if (text.length() == 0) {
                            new E(this.c, aG.a("Note"), aG.a("You must provide a password for this room.  Please re-enter this information."), this.h).setVisible(true);
                            return true;
                        }
                        if (!text.equals(text2)) {
                            new E(this.c, aG.a("Note"), aG.a("The confirmation password does not match the room password.  Please re-enter this information."), this.h).setVisible(true);
                            return true;
                        }
                        this.h.a(trim, trim2, text, b);
                        this.dispose();
                    }
                    else {
                        this.h.a(trim, trim2, b);
                        this.dispose();
                    }
                }
                else if (event.target == this.c) {
                    this.dispose();
                }
                else if (event.target == this.f) {
                    this.g.setState(true);
                    this.g.enable();
                    if ((this.f.getState() && (!this.h.c(29) || !this.h.c(38))) || (!this.f.getState() && (!this.h.c(27) || !this.h.c(28)))) {
                        this.g.disable();
                    }
                    if ((this.f.getState() && this.h.c(38)) || (!this.f.getState() && this.h.c(28))) {
                        this.g.setState(false);
                    }
                    if (!this.f.getState()) {
                        this.b.disable();
                        this.c.disable();
                    }
                    else {
                        this.b.enable();
                        this.c.enable();
                    }
                }
                return true;
            }
        }
        return super.handleEvent(event);
    }
    
    public ah(final Frame frame, final be h) {
        super(frame, aG.a("Create New Room"), true);
        this.b = new al(80, 20);
        this.c = new al(80, 20);
        this.setBackground(h.c.k);
        this.setForeground(h.c.j);
        this.h = h;
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final Panel panel = new Panel();
        this.setResizable(false);
        this.setLayout(gridBagLayout);
        panel.setLayout(gridBagLayout);
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        gridBagConstraints.anchor = 13;
        this.d = new TextField(20);
        final Label label = new Label(aG.a("Name"));
        label.setFont(aK.g);
        gridBagConstraints.gridwidth = -1;
        gridBagLayout.setConstraints(label, gridBagConstraints);
        panel.add(label);
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridwidth = 0;
        final aX ax = new aX(this.d);
        gridBagLayout.setConstraints(ax, gridBagConstraints);
        panel.add(ax);
        this.a = new TextField(25);
        final Label label2 = new Label(aG.a("Topic"));
        label2.setFont(aK.g);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        gridBagLayout.setConstraints(label2, gridBagConstraints);
        panel.add(label2);
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridwidth = 0;
        final aX ax2 = new aX(this.a);
        gridBagLayout.setConstraints(ax2, gridBagConstraints);
        panel.add(ax2);
        this.b = new TextField(15);
        final Label label3 = new Label(aG.a("Password"));
        label3.setFont(aK.g);
        this.b.setEchoCharacter('*');
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        gridBagLayout.setConstraints(label3, gridBagConstraints);
        panel.add(label3);
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridwidth = 0;
        final aX ax3 = new aX(this.b);
        gridBagLayout.setConstraints(ax3, gridBagConstraints);
        panel.add(ax3);
        this.c = new TextField(15);
        final Label label4 = new Label(aG.a("Confirm"));
        this.c.setEchoCharacter('*');
        label4.setFont(aK.g);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        gridBagLayout.setConstraints(label4, gridBagConstraints);
        panel.add(label4);
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridwidth = 0;
        final aX ax4 = new aX(this.c);
        gridBagLayout.setConstraints(ax4, gridBagConstraints);
        panel.add(ax4);
        (this.f = new Checkbox(aG.a("Private Room"))).setState(true);
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridwidth = 0;
        gridBagLayout.setConstraints(this.f, gridBagConstraints);
        panel.add(this.f);
        (this.g = new Checkbox(aG.a("Permanent Room"))).setState(true);
        gridBagConstraints.gridwidth = 0;
        gridBagLayout.setConstraints(this.g, gridBagConstraints);
        panel.add(this.g);
        if (!h.c(29) && !h.c(38)) {
            this.f.setState(false);
            this.f.disable();
            this.b.disable();
            this.c.disable();
        }
        if (!h.c(27) && !h.c(28)) {
            this.f.disable();
        }
        if ((this.f.getState() && (!h.c(29) || !h.c(38))) || (!this.f.getState() && (!h.c(27) || !h.c(28)))) {
            this.g.disable();
        }
        if ((this.f.getState() && h.c(38)) || (!this.f.getState() && h.c(28))) {
            this.g.setState(false);
        }
        gridBagLayout.setConstraints(panel, gridBagConstraints);
        this.add(panel);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        this.c.a(aG.a("Cancel"));
        this.c.f();
        gridBagLayout.setConstraints(this.c, gridBagConstraints);
        this.add(this.c);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridwidth = 0;
        this.b.a(aG.a("OK"));
        this.b.f();
        final P p2 = new P(this.b);
        gridBagLayout.setConstraints(p2, gridBagConstraints);
        this.add(p2);
        this.pack();
        this.d.requestFocus();
    }
}
