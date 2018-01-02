// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client.plugin;

import com.diginet.digichat.util.k;
import com.diginet.digichat.client.ChatMessage;
import com.esial.util.LanguageSupport;
import java.util.Properties;
import com.diginet.digichat.common.User;
import java.util.Vector;
import com.diginet.digichat.client.User2;
import com.diginet.digichat.common.a6;
import java.util.Hashtable;
import com.diginet.digichat.network.t;
import com.diginet.digichat.common.Theme;
import com.diginet.digichat.awt.s;
import java.awt.Panel;
import java.awt.FlowLayout;
import java.awt.Component;
import com.diginet.digichat.awt.m;
import com.diginet.digichat.awt.an;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import com.diginet.digichat.client.g;
import java.awt.Frame;
import com.diginet.digichat.util.c3;
import java.awt.Event;
import com.diginet.digichat.awt.o;
import com.diginet.digichat.awt.ShadedDialog;

class b7 implements Runnable
{
    private final /* synthetic */ bc a = a;
    private final /* synthetic */ String b = b;
    private final /* synthetic */ String c = c;
    private final /* synthetic */ String d = d;
    private final /* synthetic */ String e = e;
    
    public final void run() {
        final boolean b = false;
        final bc a = this.a;
        if (a == null) {
            throw null;
        }
        if (new ShadedDialog(this.a.a, b, this.b, this.c, this.d) {
            private o a;
            private o b;
            private boolean c = c;
            
            public final boolean b() {
                this.setVisible(true);
                return this.c;
            }
            
            public final void setVisible(final boolean visible) {
                if (visible) {
                    this.pack();
                    this.a();
                }
                super.setVisible(visible);
            }
            
            public final boolean handleEvent(final Event event) {
                switch (event.id) {
                    case 201: {
                        this.dispose();
                        return true;
                    }
                    case 1001: {
                        if (event.target == this.a) {
                            this.c = true;
                        }
                        else if (event.target == this.b) {
                            this.c = false;
                        }
                        this.dispose();
                        return true;
                    }
                    case 401: {
                        if (event.key == 10 || event.key == c3.a) {
                            this.a.e();
                            return true;
                        }
                        break;
                    }
                }
                return super.handleEvent(event);
            }
            
            {
                final Theme df = g.df;
                this.setBackground(df.tabsBackground);
                this.setForeground(df.tabsText);
                final GridBagConstraints gridBagConstraints = new GridBagConstraints();
                final GridBagLayout layout = new GridBagLayout();
                this.setResizable(false);
                this.setLayout(layout);
                gridBagConstraints.insets = new Insets(5, 5, 5, 5);
                gridBagConstraints.anchor = 18;
                gridBagConstraints.gridwidth = 0;
                gridBagConstraints.weightx = 1.0;
                final an an = new an("You have received an invitation from " + s3 + " for " + s2 + ".  Do you want to accept?");
                an.setFont(m.c);
                an.resize(300, 20);
                layout.setConstraints(an, gridBagConstraints);
                this.add(an);
                if (s != null) {
                    final an an2 = new an(s);
                    an2.setFont(m.c);
                    an2.resize(300, 20);
                    layout.setConstraints(an2, gridBagConstraints);
                    this.add(an2);
                }
                this.getFontMetrics(m.b);
                gridBagConstraints.anchor = 13;
                gridBagConstraints.gridheight = 0;
                gridBagConstraints.gridwidth = 1;
                gridBagConstraints.weightx = 0.0;
                final Panel panel = new Panel(new FlowLayout(2, 5, 0));
                this.a = new o("Yes", 60, 20);
                this.b = new o("No", 60, 20);
                panel.add(new s(this.a));
                panel.add(this.b);
                layout.setConstraints(panel, gridBagConstraints);
                this.add(panel);
            }
        }.b()) {
            if (this.a.b.get(this.c) == null) {
                this.a.a(this.c, this.e, this.d, true);
            }
            else {
                this.a.b.get(this.c).receiveInvitation(this.d);
            }
        }
        else {
            final t t = new t(50400772, 1);
            t.a(0, 0, this.c);
            t.m = this.a.a(this.d).x();
            t.n = this.a.a.x();
            t.a(0, 50400774, true);
            this.a.a.ap(t);
        }
    }
    
    private final void a(final bc bc) {
    }
}
