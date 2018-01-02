// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import com.diginet.digichat.awt.u;
import com.diginet.digichat.awt.t;
import java.awt.Component;
import com.diginet.digichat.awt.p;
import java.awt.Label;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import com.diginet.digichat.awt.ah;
import com.esial.util.d;
import com.diginet.digichat.util.ch;
import java.awt.Event;
import java.awt.Frame;
import java.awt.Checkbox;
import java.awt.TextField;
import com.diginet.digichat.awt.r;
import com.diginet.digichat.awt.ShadedDialog;

public final class cc extends ShadedDialog
{
    private r a;
    private r b;
    private TextField c;
    private TextField d;
    private TextField e;
    private TextField f;
    private Checkbox g;
    private Checkbox h;
    private i i;
    private Frame j;
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key == 10 || event.key == ch.a) {
                    this.a.e();
                    return true;
                }
                if (event.key == 27) {
                    this.b.e();
                    return true;
                }
                break;
            }
            case 1001: {
                if (event.target == this.a) {
                    final String trim = this.e.getText().trim();
                    String trim2 = this.f.getText().trim();
                    if (trim.length() == 0) {
                        new ah(this.j, com.esial.util.d.a("Note"), com.esial.util.d.a("You must provide a name for this room.  Please re-enter this information."), this.i).setVisible(true);
                        return true;
                    }
                    if (trim2.length() == 0) {
                        trim2 = null;
                    }
                    boolean b = false;
                    if (this.h.isEnabled() && this.h.getState()) {
                        b = true;
                    }
                    if (this.g.getState()) {
                        final String text = this.c.getText();
                        final String text2 = this.d.getText();
                        if (text.length() == 0) {
                            new ah(this.j, com.esial.util.d.a("Note"), com.esial.util.d.a("You must provide a password for this room.  Please re-enter this information."), this.i).setVisible(true);
                            return true;
                        }
                        if (!text.equals(text2)) {
                            new ah(this.j, com.esial.util.d.a("Note"), com.esial.util.d.a("The confirmation password does not match the room password.  Please re-enter this information."), this.i).setVisible(true);
                            return true;
                        }
                        this.i.a(trim, trim2, text, b);
                        this.dispose();
                    }
                    else {
                        this.i.a(trim, trim2, b);
                        this.dispose();
                    }
                }
                else if (event.target == this.b) {
                    this.dispose();
                }
                else if (event.target == this.g) {
                    this.h.setState(true);
                    this.h.enable();
                    if ((this.g.getState() && (!this.i.i(29) || !this.i.i(38))) || (!this.g.getState() && (!this.i.i(27) || !this.i.i(28)))) {
                        this.h.disable();
                    }
                    if ((this.g.getState() && this.i.i(38)) || (!this.g.getState() && this.i.i(28))) {
                        this.h.setState(false);
                    }
                    if (!this.g.getState()) {
                        this.c.disable();
                        this.d.disable();
                    }
                    else {
                        this.c.enable();
                        this.d.enable();
                    }
                }
                return true;
            }
        }
        return super.handleEvent(event);
    }
    
    public cc(final Frame frame, final i i) {
        super(frame, com.esial.util.d.a("Create New Room"), true);
        this.a = new r(80, 20);
        this.b = new r(80, 20);
        this.setBackground(i.ca.j);
        this.setForeground(i.ca.i);
        this.i = i;
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final Panel panel = new Panel();
        this.setResizable(false);
        this.setLayout(gridBagLayout);
        panel.setLayout(gridBagLayout);
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        gridBagConstraints.anchor = 13;
        this.e = new TextField(20);
        final Label label = new Label(com.esial.util.d.a("Name"));
        label.setFont(p.d);
        gridBagConstraints.gridwidth = -1;
        gridBagLayout.setConstraints(label, gridBagConstraints);
        panel.add(label);
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridwidth = 0;
        final t t = new t(this.e);
        gridBagLayout.setConstraints(t, gridBagConstraints);
        panel.add(t);
        this.f = new TextField(25);
        final Label label2 = new Label(com.esial.util.d.a("Topic"));
        label2.setFont(p.d);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        gridBagLayout.setConstraints(label2, gridBagConstraints);
        panel.add(label2);
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridwidth = 0;
        final t t2 = new t(this.f);
        gridBagLayout.setConstraints(t2, gridBagConstraints);
        panel.add(t2);
        this.c = new TextField(15);
        final Label label3 = new Label(com.esial.util.d.a("Password"));
        label3.setFont(p.d);
        this.c.setEchoCharacter('*');
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        gridBagLayout.setConstraints(label3, gridBagConstraints);
        panel.add(label3);
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridwidth = 0;
        final t t3 = new t(this.c);
        gridBagLayout.setConstraints(t3, gridBagConstraints);
        panel.add(t3);
        this.d = new TextField(15);
        final Label label4 = new Label(com.esial.util.d.a("Confirm"));
        this.d.setEchoCharacter('*');
        label4.setFont(p.d);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        gridBagLayout.setConstraints(label4, gridBagConstraints);
        panel.add(label4);
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridwidth = 0;
        final t t4 = new t(this.d);
        gridBagLayout.setConstraints(t4, gridBagConstraints);
        panel.add(t4);
        (this.g = new Checkbox(com.esial.util.d.a("Private Room"))).setState(true);
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridwidth = 0;
        gridBagLayout.setConstraints(this.g, gridBagConstraints);
        panel.add(this.g);
        (this.h = new Checkbox(com.esial.util.d.a("Permanent Room"))).setState(true);
        gridBagConstraints.gridwidth = 0;
        gridBagLayout.setConstraints(this.h, gridBagConstraints);
        panel.add(this.h);
        if (!i.i(29) && !i.i(38)) {
            this.g.setState(false);
            this.g.disable();
            this.c.disable();
            this.d.disable();
        }
        if (!i.i(27) && !i.i(28)) {
            this.g.disable();
        }
        if ((this.g.getState() && (!i.i(29) || !i.i(38))) || (!this.g.getState() && (!i.i(27) || !i.i(28)))) {
            this.h.disable();
        }
        if ((this.g.getState() && i.i(38)) || (!this.g.getState() && i.i(28))) {
            this.h.setState(false);
        }
        gridBagLayout.setConstraints(panel, gridBagConstraints);
        this.add(panel);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        this.b.a(com.esial.util.d.a("Cancel"));
        this.b.f();
        gridBagLayout.setConstraints(this.b, gridBagConstraints);
        this.add(this.b);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridwidth = 0;
        this.a.a(com.esial.util.d.a("OK"));
        this.a.f();
        final u u = new u(this.a);
        gridBagLayout.setConstraints(u, gridBagConstraints);
        this.add(u);
        this.pack();
        this.e.requestFocus();
    }
}
