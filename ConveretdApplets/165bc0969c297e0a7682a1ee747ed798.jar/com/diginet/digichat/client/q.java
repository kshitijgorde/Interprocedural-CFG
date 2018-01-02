// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import com.diginet.digichat.awt.u;
import java.awt.GridLayout;
import com.diginet.digichat.awt.t;
import java.awt.Component;
import java.awt.Insets;
import com.diginet.digichat.awt.dw;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Label;
import java.awt.CheckboxGroup;
import com.diginet.digichat.common.e;
import com.diginet.digichat.awt.a6;
import com.esial.util.c;
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
    Checkbox chkUsual;
    Checkbox chkInvis;
    Checkbox chkGhost;
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
        final String x = this.h.x();
        if (x != null) {
            this.b.setText(x);
            this.c.requestFocus();
        }
        else {
            this.b.setText("");
            this.b.requestFocus();
        }
        if (this.h.bj != null) {
            this.d.setText(this.h.bj);
        }
        else {
            this.d.setText("");
        }
        this.c.setText("");
        if (this.h.br == -999) {
            this.e.setText("");
        }
        else {
            this.e.setText(String.valueOf(this.h.br));
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
    
    private TextField update(final TextField textField, TextField textField2) {
        if (textField.getParent() != null) {
            textField.setText(textField.getText());
            if (textField2 == null) {
                textField2 = textField;
            }
        }
        return textField2;
    }
    
    public void layout() {
        super.layout();
        final TextField update;
        if ((update = this.update(this.e, this.update(this.d, this.update(this.c, this.update(this.b, null))))) != null) {
            update.requestFocus();
        }
    }
    
    public void run() {
        this.a.c();
        final String trim = this.b.getText().trim();
        final String text = this.d.getText();
        final String text2 = this.c.getText();
        if (trim.indexOf(44) != -1 || trim.indexOf(34) != -1) {
            this.b.requestFocus();
            this.b.selectAll();
            new a6(this.h.a3, com.esial.util.c.a("Error"), com.esial.util.c.a("You can not use commas or quotes in your nickname.  Please re-enter this information."), this.h).setVisible(true);
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
            new a6(this.h.a3, com.esial.util.c.a("Error"), com.esial.util.c.a("The siteID you entered is invalid.  Please re-enter this information."), this.h).setVisible(true);
            this.c();
            return;
        }
        if (trim.length() == 0) {
            this.b.requestFocus();
            this.b.selectAll();
            new a6(this.h.a3, com.esial.util.c.a("Note"), com.esial.util.c.a("You must enter a name.  Please re-enter this information."), this.h).setVisible(true);
            this.c();
            return;
        }
        if (this.l && text2.length() == 0) {
            this.c.requestFocus();
            this.c.selectAll();
            new a6(this.h.a3, com.esial.util.c.a("Note"), com.esial.util.c.a("You must enter a password.  Please re-enter this information."), this.h).setVisible(true);
            this.c();
            return;
        }
        if (this.l && text2.length() < 3) {
            this.c.requestFocus();
            this.c.selectAll();
            new a6(this.h.a3, com.esial.util.c.a("Note"), com.esial.util.c.a("Passwords must be at least three characters long.  Please re-enter this information."), this.h).setVisible(true);
            this.c();
            return;
        }
        if (this.o && !this.g.getState()) {
            new a6(this.h.a3, com.esial.util.c.a("Note"), this.i, this.h).setVisible(true);
            this.c();
            return;
        }
        e e;
        String s;
        if (this.j != null && !this.l) {
            e = new e(this.j);
            s = trim;
        }
        else if (text2.length() > 0) {
            e = new e(text2);
            s = trim;
        }
        else {
            e = null;
            s = "Guest";
        }
        this.h.bj = text;
        this.h.br = int1;
        this.h.a(23, this.chkInvis.getState());
        this.h.a(79, this.chkGhost.getState());
        try {
            this.h.a(trim, s, e, text, int1);
        }
        catch (Exception ex2) {
            this.c();
        }
    }
    
    public q(final i i, final boolean b, final boolean b2, final boolean b3, final boolean b4) {
        this(i, b, b2, b3, b4, null, null, null);
    }
    
    public q(final i h, final boolean k, final boolean l, final boolean m, final boolean n, final String label, final String i, final String j) {
        this.a = new r(70, 20);
        this.b = new TextField(12);
        this.c = new TextField(12);
        this.d = new TextField(12);
        this.e = new TextField(10);
        final CheckboxGroup checkboxGroup = new CheckboxGroup();
        this.chkUsual = new Checkbox(com.esial.util.c.a("Usual"), checkboxGroup, true);
        this.chkInvis = new Checkbox(com.esial.util.c.a("Invisible"), checkboxGroup, false);
        this.chkGhost = new Checkbox(com.esial.util.c.a("Ghost"), checkboxGroup, false);
        this.g = new Checkbox();
        this.o = false;
        this.h = h;
        this.k = k;
        this.l = l;
        this.m = m;
        this.n = n;
        this.j = j;
        final Label label2 = new Label(com.esial.util.c.a("Nickname"));
        final Label label3 = new Label(com.esial.util.c.a("Password"));
        final Label label4 = new Label(com.esial.util.c.a("Host"));
        final Label label5 = new Label(com.esial.util.c.a("Site ID"));
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        label2.setFont(dw.d);
        label3.setFont(dw.d);
        this.c.setEchoCharacter('*');
        label4.setFont(dw.d);
        label5.setFont(dw.d);
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
            final Panel panel = new Panel(new GridLayout(1, 0));
            panel.add(this.chkUsual);
            panel.add(this.chkInvis);
            panel.add(this.chkGhost);
            layout.setConstraints(panel, gridBagConstraints);
            this.add(panel);
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
        gridBagConstraints.anchor = 10;
        gridBagConstraints.gridwidth = 0;
        this.a.a(com.esial.util.c.a("Connect"));
        this.a.f();
        final u u = new u(this.a);
        layout.setConstraints(u, gridBagConstraints);
        this.add(u);
        this.a();
        if (!k && !m && !l && !n) {
            this.b();
        }
    }
}
