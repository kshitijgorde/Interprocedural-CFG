// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import com.diginet.digichat.awt.s;
import com.diginet.digichat.awt.r;
import java.awt.Label;
import java.awt.Component;
import com.diginet.digichat.awt.m;
import java.awt.Insets;
import java.awt.LayoutManager;
import com.diginet.digichat.awt.an;
import com.diginet.digichat.util.StringSubst;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import com.diginet.digichat.awt.ay;
import com.diginet.digichat.awt.am;
import com.esial.util.LanguageSupport;
import com.diginet.digichat.common.v;
import com.diginet.digichat.util.c3;
import java.awt.Event;
import java.awt.Frame;
import java.awt.TextField;
import com.diginet.digichat.awt.o;
import com.diginet.digichat.awt.ShadedDialog;

public final class ax extends ShadedDialog
{
    private o a;
    private o b;
    private TextField c;
    private TextField d;
    private bf e;
    private h f;
    private Frame g;
    private boolean h;
    private boolean i;
    
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
                    if (!this.i) {
                        if (new v(this.c.getText()).equals(this.e.k)) {
                            this.h = true;
                            this.dispose();
                            this.e.c = false;
                        }
                        else {
                            new am(this.g, LanguageSupport.translate("Note"), LanguageSupport.translate("You have not entered the correct password for this room.  Please try again."), this.f).setVisible(true);
                        }
                    }
                    else {
                        this.f.b5 = this.c.getText();
                        this.f.b4 = this.d.getText();
                        this.h = true;
                        this.dispose();
                    }
                }
                else if (event.target == this.b) {
                    this.h = true;
                    this.dispose();
                    if (this.f.b == -999 || !this.f.g) {
                        this.f.h();
                    }
                }
                return true;
            }
            case 201: {
                return this.h && super.handleEvent(event);
            }
        }
        return super.handleEvent(event);
    }
    
    public ax(final Frame g, final h f, final bf e) {
        super(g, LanguageSupport.translate("Enter Password"), true);
        this.a = new o(80, 20);
        this.b = new o(80, 20);
        this.c = new TextField(15);
        this.d = new TextField(15);
        this.h = false;
        this.i = false;
        this.setBackground(f.df.tabsBackground);
        this.setForeground(f.df.tabsText);
        this.setResizable(false);
        this.f = f;
        this.g = g;
        final ay ay = new ay();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout layout = new GridBagLayout();
        this.i = e.u(48);
        final an an = new an(StringSubst.Substitute(LanguageSupport.translate(this.i ? "You must enter the correct login to enter %1." : "You must enter the correct password to enter %1."), new String[] { e.getName() }));
        this.e = e;
        this.a.a(LanguageSupport.translate("OK"));
        this.a.f();
        this.setLayout(layout);
        gridBagConstraints.insets = new Insets(1, 5, 1, 5);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 2;
        an.setFont(m.a);
        layout.setConstraints(an, gridBagConstraints);
        this.add(an);
        gridBagConstraints.weightx = 1.0;
        layout.setConstraints(ay, gridBagConstraints);
        this.add(ay);
        if (this.i) {
            gridBagConstraints.insets = new Insets(2, 2, 2, 2);
            gridBagConstraints.anchor = 17;
            final Label label = new Label(LanguageSupport.translate("Username"));
            gridBagConstraints.gridwidth = -1;
            layout.setConstraints(label, gridBagConstraints);
            this.add(label);
            gridBagConstraints.gridwidth = 0;
            final r r = new r(this.d);
            layout.setConstraints(r, gridBagConstraints);
            this.add(r);
            final Label label2 = new Label(LanguageSupport.translate("Password"));
            gridBagConstraints.gridwidth = -1;
            layout.setConstraints(label2, gridBagConstraints);
            this.add(label2);
            gridBagConstraints.gridwidth = 0;
        }
        else {
            gridBagConstraints.insets = new Insets(6, 6, 6, 6);
            gridBagConstraints.anchor = 10;
            gridBagConstraints.fill = 0;
        }
        this.c.setEchoCharacter('*');
        final r r2 = new r(this.c);
        layout.setConstraints(r2, gridBagConstraints);
        this.add(r2);
        gridBagConstraints.insets = new Insets(2, 5, 4, 5);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        this.b.a(LanguageSupport.translate("Cancel"));
        this.b.f();
        layout.setConstraints(this.b, gridBagConstraints);
        this.add(this.b);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridwidth = 0;
        final s s = new s(this.a);
        layout.setConstraints(s, gridBagConstraints);
        this.add(s);
        this.pack();
        if (!this.i) {
            this.c.requestFocus();
        }
    }
}
