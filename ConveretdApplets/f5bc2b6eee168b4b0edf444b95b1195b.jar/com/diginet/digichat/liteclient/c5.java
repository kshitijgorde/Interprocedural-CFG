// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.liteclient;

import java.awt.Dimension;
import com.diginet.digichat.awt.s;
import com.diginet.digichat.util.StringSubst;
import com.diginet.digichat.awt.m;
import com.diginet.digichat.awt.r;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import com.esial.util.LanguageSupport;
import com.diginet.digichat.common.bg;
import java.awt.Frame;
import java.awt.Container;
import java.awt.Component;
import com.diginet.digichat.client.DigiChatAppletAbstract;
import com.diginet.digichat.client.bf;
import java.net.URL;
import com.diginet.digichat.common.User;
import com.diginet.digichat.client.User2;
import java.awt.MenuItem;
import com.diginet.digichat.util.c3;
import java.awt.Event;
import com.diginet.digichat.client.ChatMessage;
import com.diginet.digichat.client.a2;
import com.diginet.digichat.awt.bj;
import com.diginet.digichat.awt.o;
import java.awt.TextArea;
import com.diginet.digichat.client.h;
import com.diginet.digichat.client.x;
import com.diginet.digichat.awt.a9;

public class c5 extends a9 implements x
{
    private c7 a;
    private c8 b;
    public h c;
    public TextArea d;
    private o e;
    private o f;
    private bj g;
    private a2 h;
    private c9 i;
    
    public final void a(final ChatMessage chatMessage) {
        this.h.a(chatMessage);
    }
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 201: {
                this.f.e();
                return true;
            }
            case 401: {
                if (event.key == 27) {
                    this.d.appendText("\n");
                }
                else if (event.key == 10 || event.key == c3.a) {
                    if (this.c.bq) {
                        this.d.appendText("\n");
                    }
                    else {
                        this.e.e();
                    }
                    return true;
                }
                break;
            }
            case 1001: {
                if (event.target == this.e) {
                    String s = this.d.getText().trim();
                    this.d.setText("");
                    if (s.length() > 0) {
                        if (s.length() > 1024) {
                            s = s.substring(0, 1024);
                        }
                        if (System.currentTimeMillis() - this.c.o() < this.c.p()) {
                            try {
                                Thread.sleep(this.c.p() - (System.currentTimeMillis() - this.c.o()));
                            }
                            catch (InterruptedException ex) {}
                        }
                        this.c.f(s);
                        this.c.e(s);
                        this.h.e();
                    }
                    if (c3.d) {
                        this.d.requestFocus();
                    }
                    return true;
                }
                if (event.target == this.f) {
                    this.c.h();
                    return true;
                }
                if (event.target instanceof MenuItem) {
                    return this.c.a(event);
                }
                if (event.arg instanceof ChatMessage) {
                    final ChatMessage chatMessage = (ChatMessage)event.arg;
                    final User2 user2 = (User2)this.c.aj.d(chatMessage.f);
                    if (chatMessage.q && chatMessage.f != this.c.x()) {
                        this.c.a(chatMessage, user2);
                    }
                    else if (this.c.u(43) && user2 != null && chatMessage.p) {
                        this.c.a(chatMessage, (User)user2);
                        return true;
                    }
                    break;
                }
                if (event.arg instanceof URL) {
                    this.c.a((URL)event.arg, "_blank");
                    return true;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public final boolean a(final User2 user2) {
        return this.a.a(user2);
    }
    
    public final void a(final User2 user2, final boolean b) {
        this.a.a(user2, b);
    }
    
    public final void a(final boolean b) {
        this.g.a(0);
        this.a.a(b);
    }
    
    public final boolean a(final bf bf) {
        return this.b.a(bf);
    }
    
    public final void c(final bf bf) {
        this.b.b(bf);
    }
    
    public final void d() {
        this.h.d();
    }
    
    public final void a() {
        super.setVisible(false);
        DigiChatAppletAbstract.applet.remove(this);
        DigiChatAppletAbstract.login.setVisible(true);
        DigiChatAppletAbstract.login.validate();
    }
    
    public final void setVisible(final boolean visible) {
        super.setVisible(visible);
    }
    
    public final Container c() {
        return this;
    }
    
    public final Frame b() {
        return this.i;
    }
    
    public final void b(final bf bf) {
        if (this.i != null) {
            this.i.a(bf);
        }
    }
    
    public final void a(final bg bg) {
    }
    
    public final void e() {
    }
    
    public final void b(final boolean b) {
    }
    
    public final void a(final long n) {
    }
    
    public final void a(final String s) {
    }
    
    public final void a(final int n, final int n2) {
    }
    
    public final void c(final boolean b) {
    }
    
    public c5(final h c, final c9 i) {
        this.e = new o(70, 20);
        this.f = new o(LanguageSupport.translate("Logout"), 70, 20);
        this.i = i;
        this.c = c;
        this.setBackground(c.df.outerBackground);
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final a9 a9 = new a9();
        a9.setBackground(c.df.innerBackground);
        this.setLayout(gridBagLayout);
        a9.setLayout(gridBagLayout);
        try {
            this.d = new TextArea("", 2, 10, 3);
        }
        catch (Throwable t) {
            this.d = new TextArea(2, 10);
        }
        (this.h = new c6(c, true)).setFont(c.df.getFont());
        if (!c.q() && !c.r()) {
            gridBagConstraints.gridwidth = 0;
        }
        else {
            gridBagConstraints.gridwidth = 1;
        }
        gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        final r r = new r(this.h);
        gridBagLayout.setConstraints(r, gridBagConstraints);
        a9.add(r);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.weightx = 0.0;
        this.g = new bj(c);
        this.a = new c7(c);
        this.b = new c8(c);
        if (c.r()) {
            this.g.a(LanguageSupport.translate("Users"), this.a);
        }
        if (c.q()) {
            this.g.a(LanguageSupport.translate("Rooms"), this.b);
        }
        gridBagLayout.setConstraints(this.g, gridBagConstraints);
        if (c.q() || c.r()) {
            a9.add(this.g);
        }
        this.d.setFont(m.c);
        final r r2 = new r(this.d);
        gridBagConstraints.gridheight = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        gridBagLayout.setConstraints(r2, gridBagConstraints);
        a9.add(r2);
        gridBagConstraints.gridheight = -1;
        gridBagConstraints.weightx = 1.0E-6;
        this.f.a(StringSubst.Substitute(LanguageSupport.translate("Click here to logout and end your %1 session."), new String[] { DigiChatAppletAbstract.OEM_DigiChat }), null);
        gridBagConstraints.gridwidth = 0;
        gridBagLayout.setConstraints(this.f, gridBagConstraints);
        a9.add(this.f);
        gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridheight = 0;
        gridBagConstraints.gridwidth = 0;
        final s s = new s(this.e);
        gridBagLayout.setConstraints(s, gridBagConstraints);
        a9.add(s);
        this.e.a(LanguageSupport.translate("Send"));
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.gridheight = -1;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagLayout.setConstraints(a9, gridBagConstraints);
        this.add(a9);
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.fill = 2;
        final Dimension dimension = new Dimension();
        dimension.height = DigiChatAppletAbstract.initialWindowHeight;
        this.resize(dimension.width = DigiChatAppletAbstract.initialWindowWidth, dimension.height);
        if (i != null) {
            c.a(i);
        }
    }
}
