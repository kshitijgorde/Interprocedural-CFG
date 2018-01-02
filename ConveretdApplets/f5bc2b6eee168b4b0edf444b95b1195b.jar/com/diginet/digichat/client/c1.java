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
import com.diginet.digichat.common.bg;
import java.awt.Frame;
import java.awt.Component;
import java.awt.TextField;
import com.diginet.digichat.awt.o;
import com.diginet.digichat.awt.ShadedDialog;

public final class c1 extends ShadedDialog
{
    private o a;
    private o b;
    private TextField c;
    private Component d;
    private h e;
    private Frame f;
    private bg g;
    
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
                if (event.target != this.a) {
                    if (event.target == this.b) {
                        this.dispose();
                    }
                    return true;
                }
                final String trim = this.c.getText().trim();
                if (trim.length() == 0) {
                    new am(this.f, LanguageSupport.translate("Note"), LanguageSupport.translate("You must provide a name for this category.  Please re-enter this information."), this.e).setVisible(true);
                    return true;
                }
                boolean b = false;
                if (this.d.isEnabled() && q.c(this.d)) {
                    b = true;
                }
                this.e.a(trim, this.g.x(), this.g.e + 1, b);
                this.dispose();
                return true;
            }
        }
        return super.handleEvent(event);
    }
    
    public c1(final Frame f, final h e, final bg g) {
        super(f, LanguageSupport.translate("Create New Category"), true);
        this.a = new o(80, 20);
        this.b = new o(80, 20);
        this.setBackground(e.df.tabsBackground);
        this.setForeground(e.df.tabsText);
        this.f = f;
        this.e = e;
        this.g = g;
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final Panel panel = new Panel();
        this.setResizable(false);
        this.setLayout(gridBagLayout);
        panel.setLayout(gridBagLayout);
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        gridBagConstraints.anchor = 13;
        this.c = new TextField(20);
        final Component a = q.a(LanguageSupport.translate("Name"));
        a.setFont(m.d);
        gridBagConstraints.gridwidth = -1;
        gridBagLayout.setConstraints(a, gridBagConstraints);
        panel.add(a);
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridwidth = 0;
        final r r = new r(this.c);
        gridBagLayout.setConstraints(r, gridBagConstraints);
        panel.add(r);
        q.c(this.d = q.b(LanguageSupport.translate("Permanent Category")), true);
        gridBagConstraints.gridwidth = 0;
        gridBagLayout.setConstraints(this.d, gridBagConstraints);
        panel.add(this.d);
        if (!e.u(18)) {
            q.c(this.d, false);
            this.d.disable();
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
        this.c.requestFocus();
    }
}
