// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client.chatmaster;

import com.diginet.digichat.awt.s;
import com.diginet.digichat.util.StringSubst;
import com.diginet.digichat.client.DigiChatAppletAbstract;
import com.diginet.digichat.awt.r;
import com.diginet.digichat.awt.aa;
import com.diginet.digichat.awt.m;
import java.awt.Insets;
import java.awt.LayoutManager;
import com.diginet.digichat.common.a1;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import com.diginet.digichat.awt.ay;
import com.diginet.digichat.awt.a9;
import com.esial.util.LanguageSupport;
import java.awt.Component;
import com.diginet.digichat.util.q;
import com.diginet.digichat.network.t;
import com.diginet.digichat.util.c3;
import java.awt.Event;
import com.diginet.digichat.client.h;
import com.diginet.digichat.common.User;
import java.awt.TextArea;
import com.diginet.ui.z;
import java.awt.Checkbox;
import com.diginet.digichat.awt.o;
import com.diginet.digichat.awt.ShadedDialog;

public final class cv extends ShadedDialog
{
    private o a;
    private o b;
    private Checkbox c;
    private Checkbox d;
    private z e;
    private z f;
    private TextArea g;
    private User h;
    private h i;
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key == 10 || event.key == c3.a) {
                    if (this.i.bq) {
                        this.g.appendText("\n");
                    }
                    else {
                        this.a.e();
                    }
                    return true;
                }
                break;
            }
            case 1001: {
                if (event.target == this.a) {
                    final String text = this.g.getText();
                    final t t = new t(66816, 1);
                    t.m = this.h.x();
                    t.a(0, 0, text);
                    this.i.ap(t);
                    int n = 0;
                    if (q.c(this.c)) {
                        ++n;
                    }
                    if (q.c(this.d)) {
                        ++n;
                    }
                    if (n > 0) {
                        final t t2 = new t(67339, n);
                        if (q.c(this.c)) {
                            t2.a(0, 0, -999);
                            t2.a(0, 0, this.h.e);
                        }
                        if (q.c(this.d)) {
                            t2.a(n - 1, 0, -999);
                            t2.a(n - 1, 0, this.h.f);
                        }
                        t2.m = -1;
                        t2.l = -1;
                        this.i.ap(t2);
                    }
                    this.dispose();
                    return true;
                }
                if (event.target == this.b) {
                    this.dispose();
                    return true;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public cv(final h i, final User h) {
        super(i.af.b(), LanguageSupport.translate("Kick User"), true);
        this.a = new o(80, 20);
        this.b = new o(80, 20);
        this.setBackground(i.df.outerBackground);
        this.i = i;
        try {
            this.g = new TextArea("", 5, 35, 1);
        }
        catch (Throwable t) {
            this.g = new TextArea(5, 35);
        }
        final a9 a9 = new a9();
        final ay ay = new ay();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final Component a10 = q.a();
        final Component a11 = q.a(LanguageSupport.translate("Kick: "));
        this.h = h;
        final String name = this.h.getName();
        final a1 a12 = (a1)i.ai.d(this.h.a);
        this.a.a(LanguageSupport.translate("Kick"));
        this.a.f();
        this.setLayout(gridBagLayout);
        a9.setLayout(gridBagLayout);
        a9.setBackground(i.df.tabsBackground);
        a9.setForeground(i.df.tabsText);
        gridBagConstraints.insets = new Insets(1, 5, 1, 5);
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.anchor = 17;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        a11.setFont(m.a);
        gridBagLayout.setConstraints(a11, gridBagConstraints);
        a9.add(a11);
        if (a12 != null) {
            gridBagConstraints.gridwidth = -1;
            final aa aa = new aa();
            aa.b(a12.a);
            gridBagLayout.setConstraints(aa, gridBagConstraints);
            a9.add(aa);
        }
        gridBagConstraints.gridwidth = 0;
        a10.setFont(m.a);
        q.a(a10, name);
        gridBagLayout.setConstraints(a10, gridBagConstraints);
        a9.add(a10);
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagLayout.setConstraints(ay, gridBagConstraints);
        a9.add(ay);
        gridBagConstraints.fill = 1;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        final r r = new r(this.g);
        gridBagLayout.setConstraints(r, gridBagConstraints);
        a9.add(r);
        this.g.setText(StringSubst.Substitute(LanguageSupport.translate("You have been disconnected from %1 by the ChatMaster."), new String[] { DigiChatAppletAbstract.OEM_DigiChat }));
        if (i.u(49)) {
            this.c = new Checkbox();
            this.e = new z(StringSubst.Substitute(LanguageSupport.translate("Ban this user's IP (%1)"), new String[] { h.e }));
            this.d = new Checkbox();
            this.f = new z(StringSubst.Substitute(LanguageSupport.translate("Ban this user's host (%1)"), new String[] { h.f }));
            gridBagConstraints.fill = 0;
            gridBagConstraints.weighty = 0.0;
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.insets = new Insets(0, 0, 0, 0);
            gridBagConstraints.gridx = -1;
            gridBagConstraints.anchor = 13;
            gridBagConstraints.gridwidth = 1;
            a9.add(this.c, gridBagConstraints);
            gridBagConstraints.anchor = 17;
            gridBagConstraints.gridwidth = 0;
            this.e.a(0);
            a9.add(this.e, gridBagConstraints);
            gridBagConstraints.anchor = 13;
            gridBagConstraints.gridwidth = 1;
            a9.add(this.d, gridBagConstraints);
            gridBagConstraints.anchor = 17;
            gridBagConstraints.gridwidth = 0;
            this.f.a(0);
            a9.add(this.f, gridBagConstraints);
        }
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagLayout.setConstraints(a9, gridBagConstraints);
        this.add(a9);
        gridBagConstraints.insets = new Insets(2, 5, 2, 5);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weighty = 0.0;
        this.b.a(LanguageSupport.translate("Cancel"));
        this.b.f();
        gridBagLayout.setConstraints(this.b, gridBagConstraints);
        this.add(this.b);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridwidth = 0;
        final s s = new s(this.a);
        gridBagLayout.setConstraints(s, gridBagConstraints);
        this.add(s);
        this.pack();
        this.setVisible(true);
        this.g.requestFocus();
        this.g.selectAll();
    }
}
