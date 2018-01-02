// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client.chatmaster;

import com.diginet.digichat.awt.u;
import com.diginet.digichat.util.ap;
import com.diginet.digichat.client.DigiChatAppletAbstract;
import com.diginet.digichat.awt.t;
import com.diginet.digichat.awt.as;
import java.awt.Component;
import com.diginet.digichat.awt.p;
import java.awt.Insets;
import java.awt.LayoutManager;
import com.diginet.digichat.common.bd;
import java.awt.Label;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import com.diginet.digichat.awt.au;
import com.diginet.digichat.awt.a7;
import com.esial.util.d;
import com.diginet.digichat.network.v;
import com.diginet.digichat.util.ch;
import java.awt.Event;
import com.diginet.digichat.client.i;
import com.diginet.digichat.common.j;
import java.awt.TextArea;
import java.awt.Checkbox;
import com.diginet.digichat.awt.r;
import com.diginet.digichat.awt.ShadedDialog;

public final class cb extends ShadedDialog
{
    private r a;
    private r b;
    private Checkbox c;
    private Checkbox d;
    private TextArea e;
    private j f;
    private i g;
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key == 10 || event.key == ch.a) {
                    this.a.e();
                    return true;
                }
                break;
            }
            case 1001: {
                if (event.target == this.a) {
                    final String text = this.e.getText();
                    final v v = new v(66816, 1);
                    v.k = this.f.q();
                    v.a(0, 0, text);
                    this.g.ad(v);
                    int n = 0;
                    if (this.c.getState()) {
                        ++n;
                    }
                    if (this.d.getState()) {
                        ++n;
                    }
                    if (n > 0) {
                        final v v2 = new v(67339, n);
                        if (this.c.getState()) {
                            v2.a(0, 0, -999);
                            v2.a(0, 0, this.f.e);
                        }
                        if (this.d.getState()) {
                            v2.a(n - 1, 0, -999);
                            v2.a(n - 1, 0, this.f.f);
                        }
                        v2.k = -1;
                        v2.j = -1;
                        this.g.ad(v2);
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
    
    public cb(final i g, final j f) {
        super(g.x.b(), com.esial.util.d.a("Kick User"), true);
        this.a = new r(80, 20);
        this.b = new r(80, 20);
        this.setBackground(g.ca.c);
        this.g = g;
        try {
            this.e = new TextArea("", 5, 35, 1);
        }
        catch (Throwable t2) {
            this.e = new TextArea(5, 35);
        }
        final a7 a7 = new a7();
        final au au = new au();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final Label label = new Label();
        final Label label2 = new Label(com.esial.util.d.a("Kick: "));
        this.f = f;
        final String r = this.f.r();
        final bd bd = (bd)g.z.d(this.f.a);
        this.a.a(com.esial.util.d.a("Kick"));
        this.a.f();
        this.setLayout(gridBagLayout);
        a7.setLayout(gridBagLayout);
        a7.setBackground(g.ca.j);
        a7.setForeground(g.ca.i);
        gridBagConstraints.insets = new Insets(1, 5, 1, 5);
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.anchor = 17;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        label2.setFont(p.a);
        gridBagLayout.setConstraints(label2, gridBagConstraints);
        a7.add(label2);
        if (bd != null) {
            gridBagConstraints.gridwidth = -1;
            final as as = new as();
            as.b(bd.a);
            gridBagLayout.setConstraints(as, gridBagConstraints);
            a7.add(as);
        }
        gridBagConstraints.gridwidth = 0;
        label.setFont(p.a);
        label.setText(r);
        gridBagLayout.setConstraints(label, gridBagConstraints);
        a7.add(label);
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagLayout.setConstraints(au, gridBagConstraints);
        a7.add(au);
        gridBagConstraints.fill = 1;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        final t t = new t(this.e);
        gridBagLayout.setConstraints(t, gridBagConstraints);
        a7.add(t);
        this.e.setText(ap.a(com.esial.util.d.a("You have been disconnected from %1 by the ChatMaster."), new String[] { DigiChatAppletAbstract.OEM_DigiChat }));
        if (g.i(49)) {
            this.c = new Checkbox(ap.a(com.esial.util.d.a("Ban this user's IP (%1)"), new String[] { f.e }));
            this.d = new Checkbox(ap.a(com.esial.util.d.a("Ban this user's host (%1)"), new String[] { f.f }));
            gridBagConstraints.fill = 0;
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.weighty = 0.0;
            gridBagLayout.setConstraints(this.c, gridBagConstraints);
            a7.add(this.c);
            gridBagLayout.setConstraints(this.d, gridBagConstraints);
            a7.add(this.d);
        }
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagLayout.setConstraints(a7, gridBagConstraints);
        this.add(a7);
        gridBagConstraints.insets = new Insets(2, 5, 2, 5);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weighty = 0.0;
        this.b.a(com.esial.util.d.a("Cancel"));
        this.b.f();
        gridBagLayout.setConstraints(this.b, gridBagConstraints);
        this.add(this.b);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridwidth = 0;
        final u u = new u(this.a);
        gridBagLayout.setConstraints(u, gridBagConstraints);
        this.add(u);
        this.pack();
        this.setVisible(true);
        this.e.requestFocus();
        this.e.selectAll();
    }
}
