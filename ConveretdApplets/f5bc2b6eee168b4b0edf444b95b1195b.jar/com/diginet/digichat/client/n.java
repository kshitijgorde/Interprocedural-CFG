// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import com.diginet.digichat.awt.s;
import java.awt.Color;
import com.diginet.digichat.awt.r;
import java.awt.Insets;
import com.diginet.digichat.awt.m;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import com.diginet.digichat.common.v;
import com.diginet.digichat.awt.am;
import com.esial.util.LanguageSupport;
import com.diginet.digichat.util.q;
import java.awt.Event;
import java.awt.Component;
import java.awt.TextField;
import com.diginet.digichat.awt.o;
import java.awt.Panel;

public class n extends Panel implements Runnable
{
    private o a;
    public TextField b;
    public TextField c;
    public TextField d;
    public TextField e;
    Component f;
    Component g;
    private h h;
    private String i;
    private String j;
    private boolean k;
    private boolean l;
    private boolean m;
    private boolean n;
    private boolean o;
    private Component p;
    private Component q;
    private Component r;
    private Component s;
    
    public final void a() {
        final String name = this.h.getName();
        if (name != null) {
            this.b.setText(name);
            this.c.requestFocus();
        }
        else {
            this.b.setText("");
            this.b.requestFocus();
        }
        if (this.h.bz != null) {
            this.d.setText(this.h.bz);
        }
        else {
            this.d.setText("");
        }
        this.c.setText("");
        if (this.h.ce == -999) {
            this.e.setText("");
        }
        else {
            this.e.setText(String.valueOf(this.h.ce));
        }
    }
    
    public final void b() {
        new Thread(this).start();
    }
    
    public final boolean action(final Event event, final Object o) {
        if (event.target instanceof TextField) {
            this.a.e();
            return true;
        }
        if (event.target == this.a) {
            this.b();
            return true;
        }
        return super.action(event, o);
    }
    
    public final void c() {
        this.a.b();
        com.diginet.digichat.util.q.c(this.g, false);
    }
    
    public final void layout() {
        super.layout();
        this.a();
    }
    
    public final void run() {
        this.a.c();
        final String trim = this.b.getText().trim();
        final String text = this.d.getText();
        final String text2 = this.c.getText();
        if (trim.indexOf(44) != -1 || trim.indexOf(34) != -1) {
            this.b.requestFocus();
            this.b.selectAll();
            new am(this.h.bg, LanguageSupport.translate("Error"), LanguageSupport.translate("You can not use commas or quotes in your nickname.  Please re-enter this information."), this.h).setVisible(true);
            this.c();
            return;
        }
        int int1;
        try {
            int1 = Integer.parseInt(this.e.getText());
        }
        catch (NumberFormatException ex) {
            this.e.requestFocus();
            this.e.selectAll();
            new am(this.h.bg, LanguageSupport.translate("Error"), LanguageSupport.translate("The siteID you entered is invalid.  Please re-enter this information."), this.h).setVisible(true);
            this.c();
            return;
        }
        if (trim.length() == 0) {
            this.b.requestFocus();
            this.b.selectAll();
            new am(this.h.bg, LanguageSupport.translate("Note"), LanguageSupport.translate("You must enter a name.  Please re-enter this information."), this.h).setVisible(true);
            this.c();
            return;
        }
        if (trim.length() > 35) {
            this.b.setText(trim.substring(0, 34));
            this.b.requestFocus();
            this.b.selectAll();
            new am(this.h.bg, LanguageSupport.translate("Note"), LanguageSupport.translate("Your name must 35 characters or less.  Please re-enter this information."), this.h).setVisible(true);
            this.c();
            return;
        }
        if (this.l && text2.length() == 0) {
            this.c.requestFocus();
            this.c.selectAll();
            new am(this.h.bg, LanguageSupport.translate("Note"), LanguageSupport.translate("You must enter a password.  Please re-enter this information."), this.h).setVisible(true);
            this.c();
            return;
        }
        if (this.l && text2.length() < 3) {
            this.c.requestFocus();
            this.c.selectAll();
            new am(this.h.bg, LanguageSupport.translate("Note"), LanguageSupport.translate("Passwords must be at least three characters long.  Please re-enter this information."), this.h).setVisible(true);
            this.c();
            return;
        }
        if (this.o && !com.diginet.digichat.util.q.c(this.g)) {
            new am(this.h.bg, LanguageSupport.translate("Note"), this.i, this.h).setVisible(true);
            this.c();
            return;
        }
        v v;
        String s;
        if (this.j != null && !this.l) {
            v = new v(this.j);
            s = trim;
        }
        else if (text2.length() > 0) {
            v = new v(text2);
            s = trim;
        }
        else {
            v = null;
            s = "Guest";
        }
        this.h.bz = text;
        this.h.ce = int1;
        this.h.a(23, com.diginet.digichat.util.q.c(this.f));
        try {
            this.h.a(trim, s, v, text, int1);
        }
        catch (Exception ex2) {
            this.c();
        }
    }
    
    public final void repaint(final int n, final int n2, final int n3, final int n4) {
        this.p.setForeground(this.getForeground());
        this.q.setForeground(this.getForeground());
        this.r.setForeground(this.getForeground());
        this.s.setForeground(this.getForeground());
        this.f.setForeground(this.getForeground());
        this.g.setForeground(this.getForeground());
        super.repaint(n, n2, n3, n4);
    }
    
    public n(final h h, final boolean b, final boolean b2, final boolean b3, final boolean b4, final String s, final String s2) {
        this(h, b, b2, b3, b4, s, s2, null);
    }
    
    public n(final h h, final boolean k, final boolean l, final boolean m, final boolean n, final String s, final String i, final String j) {
        this.a = new o(70, 20);
        this.b = new TextField(12);
        this.c = new TextField(12);
        this.d = new TextField(12);
        this.e = new TextField(10);
        this.f = com.diginet.digichat.util.q.b(LanguageSupport.translate("Invisible"));
        this.g = com.diginet.digichat.util.q.c();
        this.o = false;
        this.h = h;
        this.k = k;
        this.l = l;
        this.m = m;
        this.n = n;
        this.j = j;
        this.p = com.diginet.digichat.util.q.a(LanguageSupport.translate("Nickname"));
        this.q = com.diginet.digichat.util.q.a(LanguageSupport.translate("Password"));
        this.r = com.diginet.digichat.util.q.a(LanguageSupport.translate("Host"));
        this.s = com.diginet.digichat.util.q.a(LanguageSupport.translate("Site ID"));
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        this.p.setFont(com.diginet.digichat.awt.m.d);
        this.q.setFont(com.diginet.digichat.awt.m.d);
        this.c.setEchoCharacter('*');
        this.r.setFont(com.diginet.digichat.awt.m.d);
        this.s.setFont(com.diginet.digichat.awt.m.d);
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        gridBagConstraints.anchor = 17;
        if (k) {
            gridBagConstraints.gridwidth = -1;
            layout.setConstraints(this.p, gridBagConstraints);
            this.add(this.p);
            gridBagConstraints.gridwidth = 0;
            final r r = new r(this.b);
            layout.setConstraints(r, gridBagConstraints);
            this.add(r);
        }
        if (l) {
            gridBagConstraints.gridwidth = -1;
            layout.setConstraints(this.q, gridBagConstraints);
            this.add(this.q);
            gridBagConstraints.gridwidth = 0;
            final r r2 = new r(this.c);
            layout.setConstraints(r2, gridBagConstraints);
            this.add(r2);
        }
        if (m) {
            gridBagConstraints.gridwidth = -1;
            layout.setConstraints(this.r, gridBagConstraints);
            this.add(this.r);
            gridBagConstraints.gridwidth = 0;
            final r r3 = new r(this.d);
            layout.setConstraints(r3, gridBagConstraints);
            this.add(r3);
        }
        if (n) {
            gridBagConstraints.gridwidth = -1;
            layout.setConstraints(this.s, gridBagConstraints);
            this.add(this.s);
            gridBagConstraints.gridwidth = 0;
            final r r4 = new r(this.e);
            layout.setConstraints(r4, gridBagConstraints);
            this.add(r4);
        }
        if (l) {
            gridBagConstraints.gridwidth = 0;
            layout.setConstraints(this.f, gridBagConstraints);
            com.diginet.digichat.util.q.c(this.f, false);
            this.add(this.f);
        }
        if (s != null && i != null) {
            this.o = true;
            this.i = i;
            gridBagConstraints.gridwidth = 0;
            layout.setConstraints(this.g, gridBagConstraints);
            com.diginet.digichat.util.q.c(this.g, false);
            com.diginet.digichat.util.q.d(this.g, s);
            this.add(this.g);
        }
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = 0;
        this.a.a(LanguageSupport.translate("Connect"));
        this.a.f();
        this.a.setForeground(Color.black);
        final s s2 = new s(this.a);
        layout.setConstraints(s2, gridBagConstraints);
        this.add(s2);
        this.a();
        if (!k && !m && !l && !n) {
            this.b();
        }
    }
}
