// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import com.diginet.digichat.awt.u;
import java.awt.Color;
import com.diginet.digichat.awt.t;
import java.awt.Component;
import java.awt.Insets;
import com.diginet.digichat.awt.p;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Label;
import com.diginet.digichat.common.x;
import com.diginet.digichat.awt.ah;
import com.esial.util.d;
import java.awt.Event;
import java.awt.Checkbox;
import java.awt.TextField;
import com.diginet.digichat.awt.r;
import java.awt.Panel;

public class q extends Panel implements Runnable
{
    private r a;
    public TextField b;
    public TextField c;
    public TextField d;
    public TextField e;
    Checkbox f;
    Checkbox g;
    private i h;
    private String i;
    private String j;
    private boolean k;
    private boolean l;
    private boolean m;
    private boolean n;
    private boolean o;
    
    public void a() {
        final String r = this.h.r();
        if (r != null) {
            this.b.setText(r);
            this.c.requestFocus();
        }
        else {
            this.b.setText("");
            this.b.requestFocus();
        }
        if (this.h.bh != null) {
            this.d.setText(this.h.bh);
        }
        else {
            this.d.setText("");
        }
        this.c.setText("");
        if (this.h.bp == -999) {
            this.e.setText("");
        }
        else {
            this.e.setText(String.valueOf(this.h.bp));
        }
    }
    
    public void b() {
        new Thread(this).start();
    }
    
    public boolean action(final Event event, final Object o) {
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
    
    public void c() {
        this.a.b();
        this.g.setState(false);
    }
    
    public void layout() {
        super.layout();
        this.a();
    }
    
    public void run() {
        this.a.c();
        final String trim = this.b.getText().trim();
        final String text = this.d.getText();
        final String text2 = this.c.getText();
        if (trim.indexOf(44) != -1 || trim.indexOf(34) != -1) {
            this.b.requestFocus();
            this.b.selectAll();
            new ah(this.h.a1, com.esial.util.d.a("Error"), com.esial.util.d.a("You can not use commas or quotes in your nickname.  Please re-enter this information."), this.h).setVisible(true);
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
            new ah(this.h.a1, com.esial.util.d.a("Error"), com.esial.util.d.a("The siteID you entered is invalid.  Please re-enter this information."), this.h).setVisible(true);
            this.c();
            return;
        }
        if (trim.length() == 0) {
            this.b.requestFocus();
            this.b.selectAll();
            new ah(this.h.a1, com.esial.util.d.a("Note"), com.esial.util.d.a("You must enter a name.  Please re-enter this information."), this.h).setVisible(true);
            this.c();
            return;
        }
        if (trim.length() > 35) {
            this.b.setText(trim.substring(0, 34));
            this.b.requestFocus();
            this.b.selectAll();
            new ah(this.h.a1, com.esial.util.d.a("Note"), com.esial.util.d.a("Your name must 35 characters or less.  Please re-enter this information."), this.h).setVisible(true);
            this.c();
            return;
        }
        if (this.l && text2.length() == 0) {
            this.c.requestFocus();
            this.c.selectAll();
            new ah(this.h.a1, com.esial.util.d.a("Note"), com.esial.util.d.a("You must enter a password.  Please re-enter this information."), this.h).setVisible(true);
            this.c();
            return;
        }
        if (this.l && text2.length() < 3) {
            this.c.requestFocus();
            this.c.selectAll();
            new ah(this.h.a1, com.esial.util.d.a("Note"), com.esial.util.d.a("Passwords must be at least three characters long.  Please re-enter this information."), this.h).setVisible(true);
            this.c();
            return;
        }
        if (this.o && !this.g.getState()) {
            new ah(this.h.a1, com.esial.util.d.a("Note"), this.i, this.h).setVisible(true);
            this.c();
            return;
        }
        x x;
        String s;
        if (this.j != null && !this.l) {
            x = new x(this.j);
            s = trim;
        }
        else if (text2.length() > 0) {
            x = new x(text2);
            s = trim;
        }
        else {
            x = null;
            s = "Guest";
        }
        this.h.bh = text;
        this.h.bp = int1;
        this.h.a(23, this.f.getState());
        try {
            this.h.a(trim, s, x, text, int1);
        }
        catch (Exception ex2) {
            this.c();
        }
    }
    
    public q(final i i, final boolean b, final boolean b2, final boolean b3, final boolean b4, final String s, final String s2) {
        this(i, b, b2, b3, b4, s, s2, null);
    }
    
    public q(final i h, final boolean k, final boolean l, final boolean m, final boolean n, final String label, final String i, final String j) {
        this.a = new r(70, 20);
        this.b = new TextField(12);
        this.c = new TextField(12);
        this.d = new TextField(12);
        this.e = new TextField(10);
        this.f = new Checkbox(com.esial.util.d.a("Invisible"));
        this.g = new Checkbox();
        this.o = false;
        this.h = h;
        this.k = k;
        this.l = l;
        this.m = m;
        this.n = n;
        this.j = j;
        final Label label2 = new Label(com.esial.util.d.a("Nickname"));
        final Label label3 = new Label(com.esial.util.d.a("Password"));
        final Label label4 = new Label(com.esial.util.d.a("Host"));
        final Label label5 = new Label(com.esial.util.d.a("Site ID"));
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        label2.setFont(p.d);
        label3.setFont(p.d);
        this.c.setEchoCharacter('*');
        label4.setFont(p.d);
        label5.setFont(p.d);
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        gridBagConstraints.anchor = 17;
        if (k) {
            gridBagConstraints.gridwidth = -1;
            layout.setConstraints(label2, gridBagConstraints);
            this.add(label2);
            gridBagConstraints.gridwidth = 0;
            final t t = new t(this.b);
            layout.setConstraints(t, gridBagConstraints);
            this.add(t);
        }
        if (l) {
            gridBagConstraints.gridwidth = -1;
            layout.setConstraints(label3, gridBagConstraints);
            this.add(label3);
            gridBagConstraints.gridwidth = 0;
            final t t2 = new t(this.c);
            layout.setConstraints(t2, gridBagConstraints);
            this.add(t2);
        }
        if (m) {
            gridBagConstraints.gridwidth = -1;
            layout.setConstraints(label4, gridBagConstraints);
            this.add(label4);
            gridBagConstraints.gridwidth = 0;
            final t t3 = new t(this.d);
            layout.setConstraints(t3, gridBagConstraints);
            this.add(t3);
        }
        if (n) {
            gridBagConstraints.gridwidth = -1;
            layout.setConstraints(label5, gridBagConstraints);
            this.add(label5);
            gridBagConstraints.gridwidth = 0;
            final t t4 = new t(this.e);
            layout.setConstraints(t4, gridBagConstraints);
            this.add(t4);
        }
        if (l) {
            gridBagConstraints.gridwidth = 0;
            layout.setConstraints(this.f, gridBagConstraints);
            this.f.setState(false);
            this.add(this.f);
        }
        if (label != null && i != null) {
            this.o = true;
            this.i = i;
            gridBagConstraints.gridwidth = 0;
            layout.setConstraints(this.g, gridBagConstraints);
            this.g.setState(false);
            this.g.setLabel(label);
            this.add(this.g);
        }
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = 0;
        this.a.a(com.esial.util.d.a("Connect"));
        this.a.f();
        this.a.setForeground(Color.black);
        final u u = new u(this.a);
        layout.setConstraints(u, gridBagConstraints);
        this.add(u);
        this.a();
        if (!k && !m && !l && !n) {
            this.b();
        }
    }
}
