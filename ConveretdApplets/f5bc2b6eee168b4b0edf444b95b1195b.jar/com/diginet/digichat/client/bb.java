// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import java.awt.Dimension;
import java.awt.Toolkit;
import com.diginet.digichat.common.User;
import com.diginet.digichat.network.t;
import java.awt.Frame;
import java.net.URL;
import com.diginet.digichat.awt.an;
import java.awt.Event;
import com.diginet.digichat.awt.am;
import com.diginet.digichat.util.StringSubst;
import com.esial.util.LanguageSupport;
import com.diginet.digichat.util.aj;
import com.diginet.digichat.util.p;

public class bb extends a7 implements p, aj
{
    private User2 a;
    private b9 b;
    private h c;
    private boolean d;
    
    public final void b(String s) {
        if (s == null || s.equals("")) {
            s = "ADFBV";
        }
        final String[] array = { "V", "B", "F", "D", "A" };
        final String[] array2 = { LanguageSupport.translate("View Profile"), LanguageSupport.translate("Block This User"), LanguageSupport.translate("Add To Favorites"), LanguageSupport.translate("Decline"), LanguageSupport.translate("Accept") };
        int n = 0;
        for (int i = 0; i < array2.length; ++i) {
            array2[i] = ((s.indexOf(array[i]) == -1) ? null : array2[i]);
            if (array2[i] != null) {
                ++n;
            }
        }
        final String[] array3 = new String[n];
        int n2 = 0;
        for (int j = 0; j < array2.length; ++j) {
            if (array2[j] != null) {
                array3[n2] = array2[j];
                ++n2;
            }
        }
        if (this == null) {
            throw null;
        }
        (this.b = new am(this, LanguageSupport.translate("Messaging Request"), array3, new String[] { StringSubst.Substitute(LanguageSupport.translate("%1 has requested to start a private message with you"), new String[] { this.a.getName() }) }, (aj)this, this.c) {
            bb a;
            
            public final boolean handleEvent(final Event event) {
                switch (event.id) {
                    case 1001: {
                        if (event.target instanceof an && event.arg instanceof URL) {
                            this.a.n();
                            return true;
                        }
                        break;
                    }
                    case 201: {
                        bb.this.a(this, null);
                        this.dispose();
                        return true;
                    }
                }
                return super.handleEvent(event);
            }
            
            {
                this.a = a;
            }
        }).setModal(false);
        this.b.a(true);
        this.b.setVisible(true);
    }
    
    public final void i() {
        if (this == null) {
            throw null;
        }
        (this.b = new am(this, LanguageSupport.translate("Messaging Request"), new String[] { LanguageSupport.translate("Cancel") }, new String[] { LanguageSupport.translate("Private message request sent, please wait for reply.") }, (aj)this, this.c) {
            bb a;
            
            public final boolean handleEvent(final Event event) {
                switch (event.id) {
                    case 1001: {
                        if (event.target instanceof an && event.arg instanceof URL) {
                            this.a.n();
                            return true;
                        }
                        break;
                    }
                    case 201: {
                        bb.this.a(this, null);
                        this.dispose();
                        return true;
                    }
                }
                return super.handleEvent(event);
            }
            
            {
                this.a = a;
            }
        }).setModal(false);
        this.b.a(true);
        this.b.setVisible(true);
        if (this.c.n != 0 && this.d) {
            this.c();
        }
    }
    
    public final void j() {
        if (this == null) {
            throw null;
        }
        (this.b = new am(this, LanguageSupport.translate("Messaging Request"), StringSubst.Substitute(LanguageSupport.translate("%1 is currently unavailable"), new String[] { this.a.getName() }), this.c) {
            bb a;
            
            public final boolean handleEvent(final Event event) {
                switch (event.id) {
                    case 1001: {
                        if (event.target instanceof an && event.arg instanceof URL) {
                            this.a.n();
                            return true;
                        }
                        break;
                    }
                    case 201: {
                        bb.this.a(this, null);
                        this.dispose();
                        return true;
                    }
                }
                return super.handleEvent(event);
            }
            
            {
                this.a = a;
            }
        }).setModal(false);
        this.b.setVisible(true);
    }
    
    public final void k() {
        this.d = false;
        this.d();
        this.b.dispose();
        this.setVisible(true);
    }
    
    public final void l() {
        if (this == null) {
            throw null;
        }
        final am am = new am(this, LanguageSupport.translate("Blocking"), new String[] { LanguageSupport.translate("Ok"), LanguageSupport.translate("Cancel") }, new String[] { StringSubst.Substitute(LanguageSupport.translate("Add %1 to your blocked users list ?"), new String[] { this.a.getName() }) }, (aj)null, this.c) {
            bb a;
            
            public final boolean handleEvent(final Event event) {
                switch (event.id) {
                    case 1001: {
                        if (event.target instanceof an && event.arg instanceof URL) {
                            this.a.n();
                            return true;
                        }
                        break;
                    }
                    case 201: {
                        bb.this.a(this, null);
                        this.dispose();
                        return true;
                    }
                }
                return super.handleEvent(event);
            }
            
            {
                this.a = a;
            }
        };
        am.setVisible(true);
        if (LanguageSupport.translate("Cancel").equals(am.b())) {
            return;
        }
        this.a.d = true;
        this.a.c = false;
        if (!this.c.a2.contains(this.a.getName())) {
            this.c.a2.addElement(this.a.getName());
        }
        if (this.c.b6 != null && this.c.b6.length() != 0) {
            final t t = new t(33621778, 1);
            t.a(0, 0, this.c.getName());
            t.a(0, 1, this.a.getName());
            t.a(0, 2, "block");
            this.c.ap(t);
        }
    }
    
    public final void m() {
        if (this == null) {
            throw null;
        }
        final am am = new am(this, LanguageSupport.translate("Favorites"), new String[] { LanguageSupport.translate("Ok"), LanguageSupport.translate("Cancel") }, new String[] { StringSubst.Substitute(LanguageSupport.translate("Add %1 to your Favorites ?"), new String[] { this.a.getName() }) }, (aj)null, this.c) {
            bb a;
            
            public final boolean handleEvent(final Event event) {
                switch (event.id) {
                    case 1001: {
                        if (event.target instanceof an && event.arg instanceof URL) {
                            this.a.n();
                            return true;
                        }
                        break;
                    }
                    case 201: {
                        bb.this.a(this, null);
                        this.dispose();
                        return true;
                    }
                }
                return super.handleEvent(event);
            }
            
            {
                this.a = a;
            }
        };
        am.setVisible(true);
        if (LanguageSupport.translate("Cancel").equals(am.b())) {
            return;
        }
        this.a.d = false;
        this.a.c = true;
        if (!this.c.a3.contains(this.a.getName())) {
            this.c.a3.addElement(this.a.getName());
        }
        if (this.c.b6 != null && this.c.b6.length() != 0) {
            final t t = new t(33621778, 1);
            t.a(0, 0, this.c.getName());
            t.a(0, 1, this.a.getName());
            t.a(0, 2, "addFavorite");
            this.c.ap(t);
        }
    }
    
    public final void n() {
        if (this.c != null) {
            this.c.b(this.a);
        }
    }
    
    public final void setVisible(final boolean visible) {
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int n = screenSize.width / 2 - 20;
        int n2 = screenSize.height / 2 - 20;
        int n3 = 0;
        int n4 = 0;
        switch (this.c.ag.b() % 4) {
            case 0: {
                n3 = 0;
                n4 = 0;
                break;
            }
            case 1: {
                n3 = 0;
                n4 = n + 10;
                break;
            }
            case 2: {
                n3 = n2 + 10;
                n4 = 0;
                break;
            }
            default: {
                n3 = n2 + 10;
                n4 = n + 10;
                break;
            }
        }
        if (n > 375) {
            n = 375;
        }
        if (n2 > 375) {
            n2 = 375;
        }
        this.reshape(2 + n4, 10 + n3, n, n2);
        super.setVisible(visible);
    }
    
    public final void dispose() {
        this.d();
        if (this.b != null) {
            this.b.dispose();
        }
        super.dispose();
        this.d = true;
    }
    
    protected final void e() {
        if (super.q) {
            return;
        }
        this.b.dispose();
        this.c.b(this.a.x(), "The session with %1 has timed out");
        if (this == null) {
            throw null;
        }
        final am am = new am(this, LanguageSupport.translate("Request Timed Out"), StringSubst.Substitute(LanguageSupport.translate("%1 did not respond to the invitation"), new String[] { this.a.getName() }), this.c) {
            bb a = a;
            
            public final boolean handleEvent(final Event event) {
                switch (event.id) {
                    case 1001: {
                        if (event.target instanceof an && event.arg instanceof URL) {
                            this.a.n();
                            return true;
                        }
                        break;
                    }
                    case 201: {
                        bb.this.a(this, null);
                        this.dispose();
                        return true;
                    }
                }
                return super.handleEvent(event);
            }
        };
        am.setModal(false);
        am.setVisible(true);
        super.q = false;
    }
    
    public final void a(final Object o, final Object o2) {
        if (o2 == null) {
            this.c.b(this.a.x(), "The session with %1 has been cancelled");
            this.b.dispose();
            this.dispose();
            return;
        }
        final String s = (String)o2;
        if (o2.equals(LanguageSupport.translate("Accept"))) {
            this.b.dispose();
            this.c.s(this.a.x());
            this.setVisible(true);
            this.d = false;
        }
        else if (o2.equals(LanguageSupport.translate("Decline"))) {
            this.b.dispose();
            this.c.b(this.a.x(), "The session with %1 has been declined");
            this.setVisible(false);
            this.dispose();
        }
        else if (o2.equals(LanguageSupport.translate("Block This User"))) {
            this.l();
        }
        else if (o2.equals(LanguageSupport.translate("Add To Favorites"))) {
            this.m();
        }
        else if (o2.equals(LanguageSupport.translate("View Profile"))) {
            this.n();
        }
        else if (o2 == LanguageSupport.translate("Cancel")) {
            this.c.b(this.a.x(), "The session with %1 has been cancelled");
            this.b.dispose();
            this.dispose();
        }
    }
    
    public final String a(final Object o) {
        if (o == super.a) {
            return StringSubst.Substitute(LanguageSupport.translate("Type your message here, then hit ENTER or click \"Send\" to send it to %1"), new String[] { this.a.getName() });
        }
        return null;
    }
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 201: {
                if (!this.d) {
                    this.c.b(this.a.x(), "%1 has stopped this chat session");
                }
                this.dispose();
                return true;
            }
            default: {
                return super.handleEvent(event);
            }
        }
    }
    
    public bb(final h c, final int n) {
        super(new Frame(), c, n);
        this.d = true;
        this.c = c;
        synchronized (c.aj) {
            this.a = (User2)c.aj.d(n);
        }
        // monitorexit(c.aj)
    }
}
