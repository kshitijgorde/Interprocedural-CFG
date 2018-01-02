// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import java.awt.Color;
import com.diginet.digichat.util.c3;
import com.diginet.digichat.network.t;
import com.diginet.digichat.exceptions.c0;
import com.esial.util.LanguageSupport;
import com.diginet.digichat.util.q;
import java.awt.TextField;
import java.awt.Component;
import com.diginet.digichat.awt.al;

public class cm extends al
{
    private Component a;
    private Component b;
    private Component c;
    private Component d;
    private Component e;
    private Component f;
    private Component g;
    private Component h;
    private Component i;
    private Component j;
    private Component k;
    private Component l;
    private TextField m;
    private g n;
    
    public final void a() {
        this.n.cw = (q.c(this.b) || !this.b.isEnabled());
        this.n.cd = q.b(this.a);
        this.n.bp = q.c(this.c);
        this.n.bq = q.c(this.d);
        if (this.e != null) {
            this.n.t = q.c(this.e);
        }
        if (this.f != null) {
            this.n.c2 = q.c(this.f);
        }
        if (this.g != null) {
            this.n.c3 = q.c(this.g);
        }
        if (this.h != null) {
            this.n.c4 = q.c(this.h);
        }
        if (this.i != null) {
            this.n.c7 = q.c(this.i);
        }
        if (this.j != null) {
            this.n.c8 = q.c(this.j);
        }
        if (this.k != null) {
            this.n.c5 = q.c(this.k);
        }
        if (this.l != null) {
            this.n.c6 = q.c(this.l);
        }
        try {
            if (this.m != null) {
                final int int1 = Integer.parseInt(this.m.getText().trim());
                if (int1 <= 1) {
                    throw new NumberFormatException();
                }
                this.n.cf = int1;
            }
        }
        catch (NumberFormatException ex) {
            this.m.requestFocus();
            this.m.selectAll();
            throw new c0(LanguageSupport.translate("The scrollback you entered is not valid.  Please re-enter this information."));
        }
    }
    
    public final void b() {
        if (t.a(this.n.a6[0], 61) || t.a(this.n.a6[0], 62)) {
            this.b.disable();
            q.c(this.b, true);
        }
        else {
            q.c(this.b, this.n.cw);
        }
        q.c(this.c, this.n.bp);
        q.c(this.d, this.n.bq);
        q.a(this.a, this.n.cd);
        if (this.e != null) {
            q.c(this.e, this.n.t);
        }
        if (this.f != null) {
            q.c(this.f, this.n.c2);
        }
        if (this.g != null) {
            q.c(this.g, this.n.c3);
        }
        if (this.h != null) {
            q.c(this.h, this.n.c4);
        }
        if (this.i != null) {
            q.c(this.i, this.n.c7);
        }
        if (this.j != null) {
            q.c(this.j, this.n.c8);
        }
        if (this.k != null) {
            q.c(this.k, this.n.c5);
        }
        if (this.l != null) {
            q.c(this.l, this.n.c6);
        }
        if (this.m != null) {
            this.m.setText(new Integer((this.n.cf <= 1) ? 75 : this.n.cf).toString());
        }
    }
    
    public final String a(final Object o) {
        if (o == this.a) {
            return LanguageSupport.translate("Select how fast new chat messages will scroll.");
        }
        if (o == this.m) {
            return LanguageSupport.translate("Enter the number of messages for the scrollback buffer size.");
        }
        if (o != this.b) {
            return null;
        }
        if (this.b.isEnabled()) {
            return LanguageSupport.translate("Check this box to enable ChatWatch.  ChatWatch replaces vulgarities with less offensive words.");
        }
        return LanguageSupport.translate("This item is disabled because ChatWatch has been turned on for all users.");
    }
    
    public cm(final g n) {
        super(LanguageSupport.translate("Options"), n);
        this.a = q.b();
        this.b = q.b(LanguageSupport.translate("Enable ChatWatch"));
        this.c = q.b(LanguageSupport.translate("Enable Auto-Popup of Private Messages"));
        this.d = q.b(LanguageSupport.translate("Enable Carriage Return in Chat Input"));
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = n;
        q.b(this.a, LanguageSupport.translate("Very Slow"));
        q.b(this.a, LanguageSupport.translate("Slow"));
        q.b(this.a, LanguageSupport.translate("Normal"));
        q.b(this.a, LanguageSupport.translate("Fast"));
        q.b(this.a, LanguageSupport.translate("Very Fast"));
        this.a.setBackground((c3.b != 2) ? n.df.tabsBackground : Color.white);
        this.a.setForeground((c3.b != 2) ? n.df.tabsText : Color.black);
        this.a(LanguageSupport.translate("Message Scroll Speed"), this.a);
        this.a("", this.b);
        this.a("", this.c);
        this.a("", this.d);
        if (!n.bn) {
            this.e = q.b(LanguageSupport.translate("Enable Buddies Tab"));
            this.f = q.b(LanguageSupport.translate("Display Rooms by Category"));
            this.g = q.b(LanguageSupport.translate("Moderated User List Sort"));
            this.h = q.b(LanguageSupport.translate("Display Count Down Timer"));
            this.i = q.b(LanguageSupport.translate("Display Moderated Q/A Count"));
            this.j = q.b(LanguageSupport.translate("Display Q/A icons for moderated replies"));
            if (!n.e) {
                this.k = q.b(LanguageSupport.translate("Display Canned Responses"));
                this.l = q.b(LanguageSupport.translate("Spell Checker"));
                this.m = new TextField(5);
            }
            if (!n.e) {
                this.a("", this.e);
                this.a("", this.f);
                this.a("", this.g);
                this.a("", this.i);
                this.a("", this.j);
                this.a("", this.h);
                this.a("", this.k);
                if (this.l != null) {
                    this.a("", this.l);
                }
                if (this.m != null) {
                    this.a(LanguageSupport.translate("Message Scrollback size"), this.m);
                }
            }
        }
    }
}
