// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client.chatmaster;

import com.diginet.digichat.common.bp;
import com.diginet.digichat.awt.u;
import com.diginet.digichat.client.DigiChatAppletAbstract;
import com.diginet.digichat.awt.t;
import com.diginet.digichat.awt.a9;
import com.diginet.digichat.client.h;
import java.awt.Component;
import com.diginet.digichat.awt.dw;
import java.awt.Label;
import com.diginet.digichat.util.a5;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import com.diginet.digichat.awt.bb;
import com.diginet.digichat.awt.bj;
import com.esial.util.c;
import com.diginet.digichat.network.v;
import com.diginet.digichat.util.dx;
import java.awt.Event;
import com.diginet.digichat.client.i;
import com.diginet.digichat.common.j;
import java.awt.TextArea;
import java.awt.Checkbox;
import com.diginet.digichat.awt.r;

public final class c0 extends BanBox
{
    private boolean fAll;
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
                if (event.key == 10 || event.key == dx.a) {
                    this.a.e();
                    return true;
                }
                break;
            }
            case 1001: {
                if (event.target == this.a) {
                    final String text = this.e.getText();
                    final v v = new v(66816, 1);
                    v.k = this.f.w();
                    v.a(0, 18014398509547520L);
                    v.a(0, 17, !this.fAll);
                    v.a(0, 18, this.fAll);
                    v.a(0, 0, text);
                    this.g.an(v);
                    int n = 0;
                    if (this.c.getState()) {
                        ++n;
                    }
                    if (this.d.getState()) {
                        ++n;
                    }
                    if (n > 0) {
                        final long limit;
                        if ((limit = this.getLimit()) < 0) {
                            return true;
                        }
                        final v v2 = new v(67339, n);
                        if (this.c.getState()) {
                            v2.a(0, 65536L);
                            v2.a(0, 0, -999);
                            v2.a(0, 0, this.f.e);
                            v2.a(0, 1, this.f.w());
                            v2.a(0, 2, limit);
                        }
                        if (this.d.getState()) {
                            v2.a(n - 1, 65536L);
                            v2.a(n - 1, 0, -999);
                            v2.a(n - 1, 0, this.f.f);
                            v2.a(n - 1, 1, this.f.w());
                            v2.a(n - 1, 2, limit);
                        }
                        v2.k = -1;
                        v2.j = -1;
                        this.g.an(v2);
                    }
                    this.dispose();
                    return true;
                }
                if (event.target == this.b) {
                    this.dispose();
                    return true;
                }
                if (event.target == this.c || event.target == this.d) {
                    if (this.c.getState() || this.d.getState()) {
                        super.txfLimit.enable();
                        super.chcLimit.enable();
                    }
                    else {
                        super.txfLimit.setText("");
                        super.txfLimit.disable();
                        super.chcLimit.disable();
                    }
                    return true;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public c0(final i g, final j f, final boolean fAll) {
        super(g, com.esial.util.c.a("Kick User"));
        this.a = new r(80, 20);
        this.b = new r(80, 20);
        this.setBackground(g.cc.c);
        this.g = g;
        try {
            this.e = new TextArea("", 5, 35, 1);
        }
        catch (Throwable t2) {
            this.e = new TextArea(5, 35);
        }
        final bj bj = new bj();
        final bb bb = new bb();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout gridBagLayout = new GridBagLayout();
        this.f = f;
        this.a.a(com.esial.util.c.a("Kick"));
        this.a.f();
        this.setLayout(gridBagLayout);
        bj.setLayout(gridBagLayout);
        bj.setBackground(g.cc.j);
        bj.setForeground(g.cc.i);
        gridBagConstraints.insets = new Insets(1, 5, 1, 5);
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.anchor = 17;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        this.fAll = fAll;
        String text;
        if (fAll) {
            text = a5.a(com.esial.util.c.a("Kick all users with IP %1"), new String[] { f.e });
        }
        else {
            text = this.f.x();
            final Label label = new Label(com.esial.util.c.a("Kick: "));
            label.setFont(dw.a);
            gridBagLayout.setConstraints(label, gridBagConstraints);
            bj.add(label);
            final bp icon = ((h)g).getIcon(this.f.a);
            if (icon != null) {
                gridBagConstraints.gridwidth = -1;
                final a9 a9 = new a9(false);
                a9.b(icon.a);
                gridBagLayout.setConstraints(a9, gridBagConstraints);
                bj.add(a9);
            }
        }
        gridBagConstraints.gridwidth = 0;
        final Label label2 = new Label();
        label2.setFont(dw.a);
        label2.setText(text);
        gridBagLayout.setConstraints(label2, gridBagConstraints);
        bj.add(label2);
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagLayout.setConstraints(bb, gridBagConstraints);
        bj.add(bb);
        gridBagConstraints.fill = 1;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        final t t = new t(this.e);
        gridBagLayout.setConstraints(t, gridBagConstraints);
        bj.add(t);
        this.e.setText(a5.a(com.esial.util.c.a("You have been disconnected from %1 by the %2."), new String[] { DigiChatAppletAbstract.OEM_DigiChat, g.x() }));
        if (g.i(49) && (fAll || (g.yyy() & 0xE000000000000000L) != 0x0 || !f.i(93))) {
            this.c = new Checkbox(a5.a(com.esial.util.c.a("Ban this user's IP (%1)"), new String[] { f.e }));
            this.d = new Checkbox(a5.a(com.esial.util.c.a("Ban this user's host (%1)"), new String[] { f.f }));
            final Component limit = this.createLimit();
            super.txfLimit.disable();
            super.chcLimit.disable();
            gridBagConstraints.fill = 0;
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.weighty = 0.0;
            gridBagLayout.setConstraints(this.c, gridBagConstraints);
            bj.add(this.c);
            gridBagLayout.setConstraints(this.d, gridBagConstraints);
            bj.add(this.d);
            gridBagLayout.setConstraints(limit, gridBagConstraints);
            bj.add(limit);
        }
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagLayout.setConstraints(bj, gridBagConstraints);
        this.add(bj);
        gridBagConstraints.insets = new Insets(2, 5, 2, 5);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weighty = 0.0;
        this.b.a(com.esial.util.c.a("Cancel"));
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
