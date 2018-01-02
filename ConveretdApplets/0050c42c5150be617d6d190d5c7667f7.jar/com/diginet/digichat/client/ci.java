// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import com.diginet.digichat.awt.an;
import java.awt.Color;
import com.diginet.digichat.util.c3;
import java.net.URL;
import java.awt.Event;
import com.diginet.digichat.network.t;
import com.diginet.digichat.exceptions.c0;
import com.diginet.digichat.common.a1;
import com.diginet.digichat.util.q;
import com.esial.util.LanguageSupport;
import java.awt.Component;
import java.awt.TextField;
import com.diginet.digichat.awt.cj;
import com.diginet.digichat.awt.al;

public class ci extends al
{
    private cj a;
    private TextField b;
    private TextField c;
    private TextField d;
    private TextField e;
    private TextField f;
    private TextField g;
    private TextField h;
    private TextField i;
    private Component j;
    private Component k;
    private g l;
    
    public final String a(final Object o) {
        if (o == this.b) {
            return LanguageSupport.translate("Enter your name here.  This information is required, and may not be the same as another connected user.");
        }
        if (o == this.c) {
            return LanguageSupport.translate("You may enter your real name here.");
        }
        if (o == this.d) {
            return LanguageSupport.translate("You may enter your age here.");
        }
        if (o == this.f) {
            return LanguageSupport.translate("You may enter your website URL here.");
        }
        if (o == this.f) {
            return LanguageSupport.translate("You may enter your the name that you want displayed for your website link here.");
        }
        if (o == this.e) {
            return LanguageSupport.translate("You may enter your e-mail address here.");
        }
        if (o == this.h) {
            return LanguageSupport.translate("You may enter a message to be displayed each time you leave chat or change rooms.");
        }
        if (o == this.i) {
            return LanguageSupport.translate("You may enter any additional comments here.");
        }
        if (o == this.k) {
            return LanguageSupport.translate("Select your gender.  This information is optional.");
        }
        if (o == this.j) {
            return LanguageSupport.translate("Check this box to prevent other users (other than ChatMasters) from viewing your profile.");
        }
        return null;
    }
    
    public final void b() {
        if (this.l.ch == 1) {
            q.a(this.k, 1);
        }
        else if (this.l.ch == 0) {
            q.a(this.k, 2);
        }
        if (this.l.cg != -999) {
            this.d.setText(String.valueOf(this.l.cg));
        }
        this.b.setText(this.l.getName());
        this.c.setText(this.l.bt);
        this.f.setText(this.l.bu);
        this.g.setText(this.l.bv);
        this.e.setText(this.l.bw);
        this.i.setText(this.l.by);
        this.h.setText(this.l.bx);
        q.c(this.j, this.l.cv);
        this.a.a();
        this.l.ai.a(false);
        try {
            synchronized (this.l.ai) {
                for (int i = 0; i < this.l.ai.b(); ++i) {
                    final a1 a1 = (a1)this.l.ai.c(i);
                    if (a1.u(36)) {
                        if (this.l.u(36)) {
                            this.a.a(a1);
                        }
                    }
                    else {
                        this.a.a(a1);
                    }
                }
            }
            // monitorexit(this.l.ai)
        }
        finally {
            this.l.ai.a();
        }
        this.a.a(this.l.cc);
    }
    
    public final void a() throws c0 {
        final String trim = this.b.getText().trim();
        if (trim.length() == 0) {
            this.b.setText(this.l.getName());
            throw new c0(LanguageSupport.translate("You must enter a name.  Please re-enter this information."));
        }
        if (trim.length() > 35) {
            this.b.setText(trim.substring(0, 34));
            throw new c0(LanguageSupport.translate("Nickname must be 35 characters or less.  Please re-enter this information"));
        }
        final String trim2 = this.d.getText().trim();
        if (trim2.length() == 0) {
            this.l.cg = -999;
        }
        else {
            try {
                this.l.cg = Integer.parseInt(trim2);
            }
            catch (NumberFormatException ex) {
                this.d.requestFocus();
                this.d.selectAll();
                throw new c0(LanguageSupport.translate("The age you entered is not valid.  Please re-enter this information, or leave the field blank."));
            }
        }
        switch (q.b(this.k)) {
            case 1: {
                this.l.ch = 1;
                break;
            }
            case 2: {
                this.l.ch = 0;
                break;
            }
            default: {
                this.l.ch = -999;
                break;
            }
        }
        if (this.c.getText() != null) {
            this.l.bt = this.c.getText().trim();
        }
        if (this.f.getText() != null) {
            this.l.bu = this.f.getText().trim();
        }
        if (this.g.getText() != null) {
            this.l.bv = this.g.getText().trim();
        }
        if (this.e.getText() != null) {
            this.l.bw = this.e.getText().trim();
        }
        if (this.i.getText() != null) {
            this.l.by = this.i.getText().trim();
        }
        if (this.h.getText() != null) {
            this.l.bx = this.h.getText().trim();
        }
        this.l.cv = q.c(this.j);
        final String trim3 = this.b.getText().trim();
        final int x = this.a.b().x();
        if (x != this.l.cc || !trim3.equals(this.l.getName())) {
            final t t = new t(67334, 1);
            t.m = -1;
            t.l = -1;
            t.a(0, 0, this.l.x());
            t.a(0, 1, x);
            t.a(0, 0, trim3);
            t.a(0, this.l.z());
            this.l.ap(t);
        }
    }
    
    public final boolean handleEvent(final Event event) {
        if (event.arg instanceof URL) {
            this.l.a((URL)event.arg, "_blank");
            return true;
        }
        return super.handleEvent(event);
    }
    
    public ci(final g l) {
        super(LanguageSupport.translate("Profile"), l);
        this.a = new cj();
        this.b = new TextField(25);
        this.c = new TextField(25);
        this.d = new TextField(4);
        this.e = new TextField(25);
        this.f = new TextField(25);
        this.g = new TextField(25);
        this.h = new TextField(25);
        this.i = new TextField(25);
        this.j = q.a(LanguageSupport.translate("Block profile from other users"), false);
        this.l = l;
        q.b(this.k = q.b(), " ");
        q.b(this.k, LanguageSupport.translate("Male"));
        q.b(this.k, LanguageSupport.translate("Female"));
        this.k.setBackground((c3.b != 2) ? l.df.tabsBackground : Color.white);
        this.k.setForeground((c3.b != 2) ? l.df.tabsText : Color.black);
        if (l.u(31) || !c3.e || c3.c >= 65800 || c3.b != 1) {
            this.a(LanguageSupport.translate("Nickname"), this.b);
        }
        this.b.enable(l.u(31));
        this.b.setEditable(l.u(31));
        if (l.u(37)) {
            this.a(LanguageSupport.translate("Real Name"), this.c);
            this.a(LanguageSupport.translate("Age"), this.d);
            this.a(LanguageSupport.translate("Gender"), this.k);
            this.a(LanguageSupport.translate("E-mail Address"), this.e);
            this.a(LanguageSupport.translate("URL"), this.f);
            this.a(LanguageSupport.translate("URL Display Name"), this.g);
            this.a(LanguageSupport.translate("Exit Message"), this.h);
            this.a(LanguageSupport.translate("Comments"), this.i);
            this.a(null, this.j);
        }
        else if (l.ca != null) {
            this.a(new an("Edit your Chat Profile", l.ca), 2);
        }
        if (l.u(30)) {
            this.a(this.a, 1, 1.0f, 1.0f);
        }
    }
}
