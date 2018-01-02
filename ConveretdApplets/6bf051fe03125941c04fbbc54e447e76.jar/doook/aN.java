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

public final class aN extends aA
{
    private ax a;
    private ax b;
    private TextField a;
    private TextField c;
    private TextField d;
    private TextField e;
    private Checkbox f;
    private Checkbox g;
    private as h;
    private Frame e;
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key == 10 || event.key == bs.c) {
                    this.a.l();
                    return true;
                }
                if (event.key == 27) {
                    this.b.l();
                    return true;
                }
                break;
            }
            case 1001: {
                if (event.target == this.a) {
                    final String trim = this.d.getText().trim();
                    String trim2 = this.e.getText().trim();
                    if (trim.length() == 0) {
                        new S(this.e, ar.b("Note"), ar.b("You must provide a name for this room.  Please re-enter this information."), this.h).setVisible(true);
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
                        final String text = this.a.getText();
                        final String text2 = this.c.getText();
                        if (text.length() == 0) {
                            new S(this.e, ar.b("Note"), ar.b("You must provide a password for this room.  Please re-enter this information."), this.h).setVisible(true);
                            return true;
                        }
                        if (!text.equals(text2)) {
                            new S(this.e, ar.b("Note"), ar.b("The confirmation password does not match the room password.  Please re-enter this information."), this.h).setVisible(true);
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
                else if (event.target == this.b) {
                    this.dispose();
                }
                else if (event.target == this.f) {
                    this.g.setState(true);
                    this.g.enable();
                    if ((this.f.getState() && (!this.h.a(29) || !this.h.a(38))) || (!this.f.getState() && (!this.h.a(27) || !this.h.a(28)))) {
                        this.g.disable();
                    }
                    if ((this.f.getState() && this.h.a(38)) || (!this.f.getState() && this.h.a(28))) {
                        this.g.setState(false);
                    }
                    if (!this.f.getState()) {
                        this.a.disable();
                        this.c.disable();
                    }
                    else {
                        this.a.enable();
                        this.c.enable();
                    }
                }
                return true;
            }
        }
        return super.handleEvent(event);
    }
    
    public aN(final Frame frame, final as h) {
        super(frame, ar.b("Create New Room"), true);
        this.a = new ax(80, 20);
        this.b = new ax(80, 20);
        this.setBackground(h.b.g);
        this.setForeground(h.b.f);
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
        final Label label = new Label(ar.b("Name"));
        label.setFont(ay.b);
        gridBagConstraints.gridwidth = -1;
        gridBagLayout.setConstraints(label, gridBagConstraints);
        panel.add(label);
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridwidth = 0;
        final aw aw = new aw(this.d);
        gridBagLayout.setConstraints(aw, gridBagConstraints);
        panel.add(aw);
        this.e = new TextField(25);
        final Label label2 = new Label(ar.b("Topic"));
        label2.setFont(ay.b);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        gridBagLayout.setConstraints(label2, gridBagConstraints);
        panel.add(label2);
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridwidth = 0;
        final aw aw2 = new aw(this.e);
        gridBagLayout.setConstraints(aw2, gridBagConstraints);
        panel.add(aw2);
        this.a = new TextField(15);
        final Label label3 = new Label(ar.b("Password"));
        label3.setFont(ay.b);
        this.a.setEchoCharacter('*');
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        gridBagLayout.setConstraints(label3, gridBagConstraints);
        panel.add(label3);
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridwidth = 0;
        final aw aw3 = new aw(this.a);
        gridBagLayout.setConstraints(aw3, gridBagConstraints);
        panel.add(aw3);
        this.c = new TextField(15);
        final Label label4 = new Label(ar.b("Confirm"));
        this.c.setEchoCharacter('*');
        label4.setFont(ay.b);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        gridBagLayout.setConstraints(label4, gridBagConstraints);
        panel.add(label4);
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridwidth = 0;
        final aw aw4 = new aw(this.c);
        gridBagLayout.setConstraints(aw4, gridBagConstraints);
        panel.add(aw4);
        (this.f = new Checkbox(ar.b("Private Room"))).setState(true);
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridwidth = 0;
        gridBagLayout.setConstraints(this.f, gridBagConstraints);
        panel.add(this.f);
        (this.g = new Checkbox(ar.b("Permanent Room"))).setState(true);
        gridBagConstraints.gridwidth = 0;
        gridBagLayout.setConstraints(this.g, gridBagConstraints);
        panel.add(this.g);
        if (!h.a(29) && !h.a(38)) {
            this.f.setState(false);
            this.f.disable();
            this.a.disable();
            this.c.disable();
        }
        if (!h.a(27) && !h.a(28)) {
            this.f.disable();
        }
        if ((this.f.getState() && (!h.a(29) || !h.a(38))) || (!this.f.getState() && (!h.a(27) || !h.a(28)))) {
            this.g.disable();
        }
        if ((this.f.getState() && h.a(38)) || (!this.f.getState() && h.a(28))) {
            this.g.setState(false);
        }
        gridBagLayout.setConstraints(panel, gridBagConstraints);
        this.add(panel);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        this.b.a(ar.b("Cancel"));
        this.b.p();
        gridBagLayout.setConstraints(this.b, gridBagConstraints);
        this.add(this.b);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridwidth = 0;
        this.a.a(ar.b("OK"));
        this.a.p();
        final au au = new au(this.a);
        gridBagLayout.setConstraints(au, gridBagConstraints);
        this.add(au);
        this.pack();
        this.d.requestFocus();
    }
}
