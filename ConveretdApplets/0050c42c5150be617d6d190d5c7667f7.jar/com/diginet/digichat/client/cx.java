// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import com.diginet.digichat.awt.s;
import java.awt.Component;
import com.diginet.digichat.awt.r;
import com.diginet.digichat.awt.m;
import com.diginet.digichat.util.q;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import com.diginet.digichat.awt.am;
import com.esial.util.LanguageSupport;
import com.diginet.digichat.util.c3;
import java.awt.Event;
import java.awt.Frame;
import java.awt.TextField;
import com.diginet.digichat.awt.o;
import com.diginet.digichat.awt.ShadedDialog;

public final class cx extends ShadedDialog
{
    private o a;
    private o b;
    private TextField c;
    private g d;
    private Frame e;
    
    private final boolean a(final String s) {
        final User2[] array = new User2[this.d.aj.b()];
        this.d.aj.a(array);
        for (int i = 0; i < array.length; ++i) {
            if (array[i] != null && array[i].getName().equals(s)) {
                this.d.a(array[i], true, false);
                return true;
            }
        }
        return false;
    }
    
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
                    final String trim = this.c.getText().trim();
                    if (trim.length() == 0) {
                        new am(this.e, LanguageSupport.translate("Note"), LanguageSupport.translate("You must provide a name for your Buddy.  Please re-enter this information."), this.d).setVisible(true);
                        return true;
                    }
                    if (trim.indexOf(44) != -1 || trim.indexOf(34) != -1) {
                        this.c.requestFocus();
                        this.c.selectAll();
                        new am(this.d.bg, LanguageSupport.translate("Error"), LanguageSupport.translate("Names can not have commas or quotes.  Please re-enter this information."), this.d).setVisible(true);
                        return true;
                    }
                    if (this.a(trim)) {
                        this.dispose();
                        return true;
                    }
                    final az az = new az(-999, trim);
                    az.a = false;
                    if (this.d.af != null) {
                        ((av)this.d.af).a(az, true, false);
                    }
                    this.dispose();
                }
                else if (event.target == this.b) {
                    this.dispose();
                }
                return true;
            }
        }
        return super.handleEvent(event);
    }
    
    public cx(final Frame frame, final g d) {
        super(frame, LanguageSupport.translate("Add Buddy"), true);
        this.a = new o(80, 20);
        this.b = new o(80, 20);
        this.setBackground(d.df.tabsBackground);
        this.setForeground(d.df.tabsText);
        this.d = d;
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
