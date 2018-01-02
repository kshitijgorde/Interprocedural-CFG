// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import com.diginet.digichat.awt.s;
import com.diginet.digichat.awt.r;
import com.diginet.digichat.awt.m;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import com.diginet.digichat.util.q;
import com.diginet.digichat.awt.am;
import com.esial.util.LanguageSupport;
import com.diginet.digichat.util.c3;
import java.awt.Event;
import java.awt.Frame;
import java.awt.Component;
import java.awt.TextField;
import com.diginet.digichat.awt.o;
import com.diginet.digichat.awt.ShadedDialog;

public final class cw extends ShadedDialog
{
    private o a;
    private o b;
    private TextField c;
    private TextField d;
    private TextField e;
    private TextField f;
    private Component g;
    private Component h;
    private h i;
    private Frame j;
    private int k;
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key == 10 || event.key == c3.a) {
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
                        new am(this.j, LanguageSupport.translate("Note"), LanguageSupport.translate("You must provide a name for this room.  Please re-enter this information."), this.i).setVisible(true);
                        return true;
                    }
                    if (trim2.length() == 0) {
                        trim2 = null;
                    }
                    boolean b = false;
                    if (this.h.isEnabled() && q.c(this.h)) {
                        b = true;
                    }
                    if (q.c(this.g)) {
                        final String text = this.c.getText();
                        final String text2 = this.d.getText();
                        if (text.length() == 0) {
                            new am(this.j, LanguageSupport.translate("Note"), LanguageSupport.translate("You must provide a password for this room.  Please re-enter this information."), this.i).setVisible(true);
                            return true;
                        }
                        if (!text.equals(text2)) {
                            new am(this.j, LanguageSupport.translate("Note"), LanguageSupport.translate("The confirmation password does not match the room password.  Please re-enter this information."), this.i).setVisible(true);
                            return true;
                        }
                        this.i.a(trim, trim2, text, b, this.k);
                        this.dispose();
                    }
                    else {
                        this.i.a(trim, trim2, b, this.k);
                        this.dispose();
                    }
                }
                else if (event.target == this.b) {
                    this.dispose();
                }
                else if (event.target == this.g) {
                    q.c(this.h, true);
                    this.h.enable();
                    if ((q.c(this.g) && (!this.i.u(29) || !this.i.u(38))) || (!q.c(this.g) && (!this.i.u(27) || !this.i.u(28)))) {
                        this.h.disable();
                    }
                    if ((q.c(this.g) && this.i.u(38)) || (!q.c(this.g) && this.i.u(28))) {
                        q.c(this.h, false);
                    }
                    if (!q.c(this.g)) {
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
    
    public cw(final Frame frame, final h i, final int k) {
        super(frame, LanguageSupport.translate("Create New Room"), true);
        this.a = new o(80, 20);
        this.b = new o(80, 20);
        this.setBackground(i.df.tabsBackground);
        this.setForeground(i.df.tabsText);
        this.i = i;
        this.k = k;
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final Panel panel = new Panel();
        this.setResizable(false);
        this.setLayout(gridBagLayout);
        panel.setLayout(gridBagLayout);
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        gridBagConstraints.anchor = 13;
        this.e = new TextField(20);
        final Component a = q.a(LanguageSupport.translate("Name"));
        a.setFont(m.d);
        gridBagConstraints.gridwidth = -1;
        gridBagLayout.setConstraints(a, gridBagConstraints);
        panel.add(a);
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridwidth = 0;
        final r r = new r(this.e);
        gridBagLayout.setConstraints(r, gridBagConstraints);
        panel.add(r);
        this.f = new TextField(25);
        final Component a2 = q.a(LanguageSupport.translate("Topic"));
        a2.setFont(m.d);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        gridBagLayout.setConstraints(a2, gridBagConstraints);
        panel.add(a2);
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridwidth = 0;
        final r r2 = new r(this.f);
        gridBagLayout.setConstraints(r2, gridBagConstraints);
        panel.add(r2);
        this.c = new TextField(15);
        final Component a3 = q.a(LanguageSupport.translate("Password"));
        a3.setFont(m.d);
        this.c.setEchoCharacter('*');
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        gridBagLayout.setConstraints(a3, gridBagConstraints);
        panel.add(a3);
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridwidth = 0;
        final r r3 = new r(this.c);
        gridBagLayout.setConstraints(r3, gridBagConstraints);
        panel.add(r3);
        this.d = new TextField(15);
        final Component a4 = q.a(LanguageSupport.translate("Confirm"));
        this.d.setEchoCharacter('*');
        a4.setFont(m.d);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        gridBagLayout.setConstraints(a4, gridBagConstraints);
        panel.add(a4);
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridwidth = 0;
        final r r4 = new r(this.d);
        gridBagLayout.setConstraints(r4, gridBagConstraints);
        panel.add(r4);
        q.c(this.g = q.b(LanguageSupport.translate("Private Room")), true);
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridwidth = 0;
        gridBagLayout.setConstraints(this.g, gridBagConstraints);
        panel.add(this.g);
        q.c(this.h = q.b(LanguageSupport.translate("Permanent Room")), true);
        gridBagConstraints.gridwidth = 0;
        gridBagLayout.setConstraints(this.h, gridBagConstraints);
        panel.add(this.h);
        if (!i.u(29) && !i.u(38)) {
            q.c(this.g, false);
            this.g.disable();
            this.c.disable();
            this.d.disable();
        }
        if (!i.u(27) && !i.u(28)) {
            this.g.disable();
        }
        if ((q.c(this.g) && (!i.u(29) || !i.u(38))) || (!q.c(this.g) && (!i.u(27) || !i.u(28)))) {
            this.h.disable();
        }
        if ((q.c(this.g) && i.u(38)) || (!q.c(this.g) && i.u(28))) {
            q.c(this.h, false);
        }
        gridBagLayout.setConstraints(panel, gridBagConstraints);
        this.add(panel);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        this.b.a(LanguageSupport.translate("Cancel"));
        this.b.f();
        gridBagLayout.setConstraints(this.b, gridBagConstraints);
        this.add(this.b);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridwidth = 0;
        this.a.a(LanguageSupport.translate("OK"));
        this.a.f();
        final s s = new s(this.a);
        gridBagLayout.setConstraints(s, gridBagConstraints);
        this.add(s);
        this.pack();
        this.e.requestFocus();
    }
}
