// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.GridBagLayout;
import java.awt.CheckboxGroup;
import java.awt.Component;
import java.awt.Color;
import java.awt.Choice;
import java.awt.Checkbox;
import java.awt.TextField;

public class B extends W
{
    private an a;
    private TextField b;
    private TextField a;
    private TextField c;
    private TextField d;
    private TextField e;
    private TextField f;
    private TextField g;
    private Checkbox e;
    private Choice h;
    private at e;
    private bp a;
    private bp b;
    
    public String a(final Object o) {
        if (o == this.b) {
            return ar.b("Enter your name here.  This information is required, and may not be the same as another connected user.");
        }
        if (o == this.a) {
            return ar.b("You may enter your real name here.");
        }
        if (o == this.c) {
            return ar.b("You may enter your age here.");
        }
        if (o == this.e) {
            return ar.b("You may enter your website URL here.");
        }
        if (o == this.d) {
            return ar.b("You may enter your e-mail address here.");
        }
        if (o == this.f) {
            return ar.b("You may enter a message to be displayed each time you leave chat or change rooms.");
        }
        if (o == this.g) {
            return ar.b("You may enter any additional comments here.");
        }
        if (o == this.h) {
            return ar.b("Select your gender.  This information is optional.");
        }
        if (o == this.e) {
            return ar.b("Check this box to prevent other users (other than ChatMasters) from viewing your profile.");
        }
        return null;
    }
    
    public void c() {
        if (this.e.M == 1) {
            this.h.select(1);
        }
        else if (this.e.M == 0) {
            this.h.select(2);
        }
        if (this.e.L != -999) {
            this.c.setText(String.valueOf(this.e.L));
        }
        this.b.setText(this.e.d());
        this.a.setText(this.e.v);
        this.e.setText(this.e.w);
        this.d.setText(this.e.x);
        this.g.setText(this.e.z);
        this.f.setText(this.e.y);
        this.e.setState(this.e.F);
        this.a.ax = 14;
        this.a.a(this.e.s, true, false);
        this.a.b(new Color(this.e.u));
        this.b.ax = 14;
        this.b.a(this.e.s, true, false);
        this.b.b(new Color(this.e.d));
        this.a.f();
        this.e.d.a(false);
        try {
            synchronized (this.e.d) {
                for (int i = 0; i < this.e.d.b(); ++i) {
                    final af af = (af)this.e.d.a(i);
                    if (af.n == null || af.n.length() == 0) {
                        if (af.a(36)) {
                            if (this.e.a(36)) {
                                this.a.a(af);
                            }
                        }
                        else {
                            this.a.a(af);
                        }
                    }
                }
            }
        }
        finally {
            this.e.d.f();
        }
        this.a.a(this.e.I);
    }
    
    public void f() {
        final String trim = this.b.getText().trim();
        if (trim.length() == 0) {
            this.b.setText(this.e.d());
            throw new j(ar.b("You must enter a name.  Please re-enter this information."));
        }
        if (trim.length() > 99) {
            this.b.setText(trim.substring(0, 98));
            throw new j(ar.b("Nickname must be 99 characters or less.  Please re-enter this information"));
        }
        final String trim2 = this.c.getText().trim();
        if (trim2.length() == 0) {
            this.e.L = -999;
        }
        else {
            try {
                this.e.L = Integer.parseInt(trim2);
            }
            catch (NumberFormatException ex) {
                this.c.requestFocus();
                this.c.selectAll();
                throw new j(ar.b("The age you entered is not valid.  Please re-enter this information, or leave the field blank."));
            }
        }
        switch (this.h.getSelectedIndex()) {
            case 1: {
                this.e.M = 1;
                break;
            }
            case 2: {
                this.e.M = 0;
                break;
            }
            default: {
                this.e.M = -999;
                break;
            }
        }
        if (this.a.getText() != null) {
            this.e.v = this.a.getText().trim();
        }
        if (this.e.getText() != null) {
            this.e.w = this.e.getText().trim();
        }
        if (this.d.getText() != null) {
            this.e.x = this.d.getText().trim();
        }
        if (this.g.getText() != null) {
            this.e.z = this.g.getText().trim();
        }
        if (this.f.getText() != null) {
            this.e.y = this.f.getText().trim();
        }
        this.e.F = this.e.getState();
        final String trim3 = this.b.getText().trim();
        final int e = this.a.a().e();
        if (e != this.e.I || !trim3.equals(this.e.d()) || this.a.aw != this.e.u || this.b.aw != this.e.d) {
            final aJ aj = new aJ(67334, 1);
            aj.j = -1;
            aj.C = -1;
            aj.a(0, 21, true);
            aj.a(0, 0, this.e.e());
            aj.a(0, 1, e);
            aj.a(0, 0, trim3);
            aj.a(0, 3, this.a.aw);
            aj.a(0, 6, this.b.aw);
            this.e.q(aj);
        }
    }
    
    public B(final at e) {
        super(ar.b("Profile"), e);
        this.a = new an();
        (this.b = new TextField(25)).setBackground(Color.white);
        this.a = new TextField(25);
        this.c = new TextField(4);
        this.d = new TextField(25);
        this.e = new TextField(25);
        this.f = new TextField(25);
        this.g = new TextField(25);
        this.a = new bp(e, this.b, "setForeground");
        this.b = new bp(e, this.b, "setBackground");
        this.e = new Checkbox(ar.b("Block profile from other users"), null, false);
        this.e = e;
        (this.h = new Choice()).addItem(" ");
        this.h.addItem(ar.b("Male"));
        this.h.addItem(ar.b("Female"));
        if (e.a(31) || !bs.d || bs.g >= 65800 || bs.t != 1) {
            if (e.a(21)) {
                final Panel panel = new Panel(new GridBagLayout());
                panel.add(this.b);
                panel.add(this.a);
                if (e.a(51)) {
                    panel.add(this.b);
                }
                this.a(ar.b("Nickname"), panel);
            }
            else {
                this.a(ar.b("Nickname"), this.b);
            }
        }
        this.b.setEnabled(e.a(31));
        this.b.setEditable(e.a(31));
        if (e.a(37)) {
            this.a(ar.b("Real Name"), this.a);
            this.a(ar.b("Age"), this.c);
            this.a(ar.b("Gender"), this.h);
            this.a(ar.b("E-mail Address"), this.d);
            this.a(ar.b("URL"), this.e);
            this.a(ar.b("Exit Message"), this.f);
            this.a(ar.b("Comments"), this.g);
            this.a(null, this.e);
        }
        if (e.a(30)) {
            this.a(this.a, 1, 1.0f, 1.0f);
        }
    }
}
