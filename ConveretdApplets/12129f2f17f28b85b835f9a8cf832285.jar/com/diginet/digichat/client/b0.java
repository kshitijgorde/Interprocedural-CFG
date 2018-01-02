// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import java.awt.Component;
import com.diginet.digichat.util.ch;
import java.awt.CheckboxGroup;
import com.diginet.digichat.network.v;
import com.diginet.digichat.exceptions.cf;
import com.diginet.digichat.common.bd;
import com.esial.util.d;
import java.awt.Choice;
import java.awt.Checkbox;
import java.awt.TextField;
import com.diginet.digichat.awt.b1;
import com.diginet.digichat.awt.ag;

public class b0 extends ag
{
    private b1 a;
    private TextField b;
    private TextField c;
    private TextField d;
    private TextField e;
    private TextField f;
    private TextField g;
    private TextField h;
    private Checkbox i;
    private Choice j;
    private h k;
    
    public String a(final Object o) {
        if (o == this.b) {
            return com.esial.util.d.a("Enter your name here.  This information is required, and may not be the same as another connected user.");
        }
        if (o == this.c) {
            return com.esial.util.d.a("You may enter your real name here.");
        }
        if (o == this.d) {
            return com.esial.util.d.a("You may enter your age here.");
        }
        if (o == this.f) {
            return com.esial.util.d.a("You may enter your website URL here.");
        }
        if (o == this.e) {
            return com.esial.util.d.a("You may enter your e-mail address here.");
        }
        if (o == this.g) {
            return com.esial.util.d.a("You may enter a message to be displayed each time you leave chat or change rooms.");
        }
        if (o == this.h) {
            return com.esial.util.d.a("You may enter any additional comments here.");
        }
        if (o == this.j) {
            return com.esial.util.d.a("Select your gender.  This information is optional.");
        }
        if (o == this.i) {
            return com.esial.util.d.a("Check this box to prevent other users (other than ChatMasters) from viewing your profile.");
        }
        return null;
    }
    
    public void b() {
        if (this.k.br == 1) {
            this.j.select(1);
        }
        else if (this.k.br == 0) {
            this.j.select(2);
        }
        if (this.k.bq != -999) {
            this.d.setText(String.valueOf(this.k.bq));
        }
        this.b.setText(this.k.r());
        this.c.setText(this.k.bc);
        this.f.setText(this.k.bd);
        this.e.setText(this.k.be);
        this.h.setText(this.k.bg);
        this.g.setText(this.k.bf);
        this.i.setState(this.k.b4);
        this.a.a();
        this.k.z.a(false);
        try {
            synchronized (this.k.z) {
                for (int i = 0; i < this.k.z.b(); ++i) {
                    final bd bd = (bd)this.k.z.c(i);
                    if (bd.i(36)) {
                        if (this.k.i(36)) {
                            this.a.a(bd);
                        }
                    }
                    else {
                        this.a.a(bd);
                    }
                }
            }
            // monitorexit(this.k.z)
        }
        finally {
            this.k.z.a();
        }
        this.a.a(this.k.bn);
    }
    
    public void a() throws cf {
        final String trim = this.b.getText().trim();
        if (trim.length() == 0) {
            this.b.setText(this.k.r());
            throw new cf(com.esial.util.d.a("You must enter a name.  Please re-enter this information."));
        }
        if (trim.length() > 35) {
            this.b.setText(trim.substring(0, 34));
            throw new cf(com.esial.util.d.a("Nickname must be 35 characters or less.  Please re-enter this information"));
        }
        final String trim2 = this.d.getText().trim();
        if (trim2.length() == 0) {
            this.k.bq = -999;
        }
        else {
            try {
                this.k.bq = Integer.parseInt(trim2);
            }
            catch (NumberFormatException ex) {
                this.d.requestFocus();
                this.d.selectAll();
                throw new cf(com.esial.util.d.a("The age you entered is not valid.  Please re-enter this information, or leave the field blank."));
            }
        }
        switch (this.j.getSelectedIndex()) {
            case 1: {
                this.k.br = 1;
                break;
            }
            case 2: {
                this.k.br = 0;
                break;
            }
            default: {
                this.k.br = -999;
                break;
            }
        }
        if (this.c.getText() != null) {
            this.k.bc = this.c.getText().trim();
        }
        if (this.f.getText() != null) {
            this.k.bd = this.f.getText().trim();
        }
        if (this.e.getText() != null) {
            this.k.be = this.e.getText().trim();
        }
        if (this.h.getText() != null) {
            this.k.bg = this.h.getText().trim();
        }
        if (this.g.getText() != null) {
            this.k.bf = this.g.getText().trim();
        }
        this.k.b4 = this.i.getState();
        final String trim3 = this.b.getText().trim();
        final int q = this.a.b().q();
        if (q != this.k.bn || !trim3.equals(this.k.r())) {
            final v v = new v(67334, 1);
            v.k = -1;
            v.j = -1;
            v.a(0, 0, this.k.q());
            v.a(0, 1, q);
            v.a(0, 0, trim3);
            this.k.ad(v);
        }
    }
    
    public b0(final h k) {
        super(com.esial.util.d.a("Profile"), k);
        this.a = new b1();
        this.b = new TextField(25);
        this.c = new TextField(25);
        this.d = new TextField(4);
        this.e = new TextField(25);
        this.f = new TextField(25);
        this.g = new TextField(25);
        this.h = new TextField(25);
        this.i = new Checkbox(com.esial.util.d.a("Block profile from other users"), null, false);
        this.k = k;
        (this.j = new Choice()).addItem(" ");
        this.j.addItem(com.esial.util.d.a("Male"));
        this.j.addItem(com.esial.util.d.a("Female"));
        if (k.i(31) || !ch.e || ch.c >= 65800 || ch.b != 1) {
            this.a(com.esial.util.d.a("Nickname"), this.b);
        }
        this.b.enable(k.i(31));
        this.b.setEditable(k.i(31));
        if (k.i(37)) {
            this.a(com.esial.util.d.a("Real Name"), this.c);
            this.a(com.esial.util.d.a("Age"), this.d);
            this.a(com.esial.util.d.a("Gender"), this.j);
            this.a(com.esial.util.d.a("E-mail Address"), this.e);
            this.a(com.esial.util.d.a("URL"), this.f);
            this.a(com.esial.util.d.a("Exit Message"), this.g);
            this.a(com.esial.util.d.a("Comments"), this.h);
            this.a(null, this.i);
        }
        if (k.i(30)) {
            this.a(this.a, 1, 1.0f, 1.0f);
        }
    }
}
