// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import java.awt.Color;
import com.diginet.digichat.util.c3;
import java.awt.event.ItemEvent;
import java.awt.Choice;
import java.awt.Event;
import com.diginet.digichat.util.StringSubst;
import com.esial.util.LanguageSupport;
import com.diginet.digichat.util.q;
import java.awt.Component;
import java.awt.event.ItemListener;
import com.diginet.digichat.awt.al;

public class cp extends al implements ItemListener
{
    private Component a;
    private Component b;
    private Component c;
    private Component d;
    private Component e;
    private g f;
    
    public final void a() {
        this.f.cj = (short)q.b(this.a);
        this.f.ck = (short)q.b(this.b);
        this.f.cl = (short)q.b(this.c);
        this.f.cm = (short)q.b(this.d);
        this.f.cn = (short)q.b(this.e);
    }
    
    public final void b() {
        q.a(this.a, (Object)this);
        q.a(this.b, (Object)this);
        q.a(this.c, (Object)this);
        q.a(this.d, (Object)this);
        q.a(this.e, (Object)this);
        q.a(this.a, this.f.cj);
        q.a(this.b, this.f.ck);
        q.a(this.c, this.f.cl);
        q.a(this.d, this.f.cm);
        q.a(this.e, this.f.cn);
        q.a(this.a, this);
        q.a(this.b, this);
        q.a(this.c, this);
        q.a(this.d, this);
        q.a(this.e, this);
    }
    
    public final String a(final Object o) {
        if (o == this.a) {
            return LanguageSupport.translate("Select a sound to play when a new user arrives.");
        }
        if (o == this.b) {
            return LanguageSupport.translate("Select a sound to play when a new message is received.");
        }
        if (o == this.c) {
            return LanguageSupport.translate("Select a sound to play when a new message is received from a flagged user.");
        }
        if (o == this.d) {
            return LanguageSupport.translate("Select a sound to play when a private message is received.");
        }
        if (o == this.e) {
            return StringSubst.Substitute(LanguageSupport.translate("Select a sound to play when a user leaves %1 or moves to another room."), new String[] { DigiChatAppletAbstract.OEM_DigiChat });
        }
        return null;
    }
    
    public final boolean action(final Event event, final Object o) {
        if (event.target instanceof Choice) {
            this.f.a(((Choice)event.target).getSelectedIndex());
            return true;
        }
        return super.action(event, o);
    }
    
    public final void itemStateChanged(final ItemEvent itemEvent) {
        if (itemEvent.getStateChange() == 1) {
            this.f.a(q.b((Component)itemEvent.getSource()));
        }
    }
    
    public cp(final g f) {
        super(LanguageSupport.translate("Sounds"), f);
        this.f = f;
        this.a = q.b();
        this.b = q.b();
        this.c = q.b();
        this.d = q.b();
        this.e = q.b();
        q.b(this.a, LanguageSupport.translate("None"));
        q.b(this.b, LanguageSupport.translate("None"));
        q.b(this.c, LanguageSupport.translate("None"));
        q.b(this.d, LanguageSupport.translate("None"));
        q.b(this.e, LanguageSupport.translate("None"));
        for (int i = 0; i < h.a.length; ++i) {
            q.b(this.a, h.a[i]);
            q.b(this.b, h.a[i]);
            q.b(this.c, h.a[i]);
            q.b(this.d, h.a[i]);
            q.b(this.e, h.a[i]);
        }
        this.a(LanguageSupport.translate("New User"), this.a);
        this.a(LanguageSupport.translate("New Message"), this.b);
        this.a(LanguageSupport.translate("New Flagged Message"), this.c);
        this.a(LanguageSupport.translate("Private Message"), this.d);
        this.a(LanguageSupport.translate("User Exit"), this.e);
        q.a(this.a, this);
        q.a(this.b, this);
        q.a(this.c, this);
        q.a(this.d, this);
        q.a(this.e, this);
        this.a.setBackground((c3.b != 2) ? f.df.tabsBackground : Color.white);
        this.b.setBackground((c3.b != 2) ? f.df.tabsBackground : Color.white);
        this.c.setBackground((c3.b != 2) ? f.df.tabsBackground : Color.white);
        this.d.setBackground((c3.b != 2) ? f.df.tabsBackground : Color.white);
        this.e.setBackground((c3.b != 2) ? f.df.tabsBackground : Color.white);
        this.a.setForeground(f.df.tabsText);
        this.b.setForeground(f.df.tabsText);
        this.c.setForeground(f.df.tabsText);
        this.d.setForeground(f.df.tabsText);
        this.e.setForeground(f.df.tabsText);
    }
}
