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

public class bs extends aZ
{
    private U a;
    private TextField h;
    private TextField i;
    private TextField c;
    private TextField d;
    private TextField a;
    private TextField e;
    private TextField f;
    private Checkbox m;
    private Choice f;
    private aW j;
    private aa b;
    private aa d;
    
    public String a(final Object o) {
        if (o == this.h) {
            return aG.a("Enter your name here.  This information is required, and may not be the same as another connected user.");
        }
        if (o == this.i) {
            return aG.a("You may enter your real name here.");
        }
        if (o == this.c) {
            return aG.a("You may enter your age here.");
        }
        if (o == this.a) {
            return aG.a("You may enter your website URL here.");
        }
        if (o == this.d) {
            return aG.a("You may enter your e-mail address here.");
        }
        if (o == this.e) {
            return aG.a("You may enter a message to be displayed each time you leave chat or change rooms.");
        }
        if (o == this.f) {
            return aG.a("You may enter any additional comments here.");
        }
        if (o == this.f) {
            return aG.a("Select your gender.  This information is optional.");
        }
        if (o == this.m) {
            return aG.a("Check this box to prevent other users (other than ChatMasters) from viewing your profile.");
        }
        return null;
    }
    
    public void c() {
        if (this.j.L == 1) {
            this.f.select(1);
        }
        else if (this.j.L == 0) {
            this.f.select(2);
        }
        if (this.j.K != -999) {
            this.c.setText(String.valueOf(this.j.K));
        }
        this.h.setText(this.j.g());
        this.i.setText(this.j.D);
        this.a.setText(this.j.E);
        this.d.setText(this.j.F);
        this.f.setText(this.j.H);
        this.e.setText(this.j.G);
        this.m.setState(this.j.L);
        this.b.c = 14;
        this.b.a(this.j.r, true, false);
        this.b.a(new Color(this.j.ag));
        this.d.c = 14;
        this.d.a(this.j.r, true, false);
        this.d.a(new Color(this.j.ah));
        this.a.c();
        this.j.c.a(false);
        try {
            synchronized (this.j.c) {
                for (int i = 0; i < this.j.c.a(); ++i) {
                    final bh bh = (bh)this.j.c.a(i);
                    if (bh.b == null || bh.b.length() == 0) {
                        if (bh.c(36)) {
                            if (this.j.c(36)) {
                                this.a.a(bh);
                            }
                        }
                        else {
                            this.a.a(bh);
                        }
                    }
                }
            }
        }
        finally {
            this.j.c.c();
        }
        this.a.b(this.j.G);
    }
    
    public void a() {
        final String trim = this.h.getText().trim();
        if (trim.length() == 0) {
            this.h.setText(this.j.g());
            throw new J(aG.a("You must enter a name.  Please re-enter this information."));
        }
        if (trim.length() > 99) {
            this.h.setText(trim.substring(0, 98));
            throw new J(aG.a("Nickname must be 99 characters or less.  Please re-enter this information"));
        }
        final String trim2 = this.c.getText().trim();
        if (trim2.length() == 0) {
            this.j.K = -999;
        }
        else {
            try {
                this.j.K = Integer.parseInt(trim2);
            }
            catch (NumberFormatException ex) {
                this.c.requestFocus();
                this.c.selectAll();
                throw new J(aG.a("The age you entered is not valid.  Please re-enter this information, or leave the field blank."));
            }
        }
        switch (this.f.getSelectedIndex()) {
            case 1: {
                this.j.L = 1;
                break;
            }
            case 2: {
                this.j.L = 0;
                break;
            }
            default: {
                this.j.L = -999;
                break;
            }
        }
        if (this.i.getText() != null) {
            this.j.D = this.i.getText().trim();
        }
        if (this.a.getText() != null) {
            this.j.E = this.a.getText().trim();
        }
        if (this.d.getText() != null) {
            this.j.F = this.d.getText().trim();
        }
        if (this.f.getText() != null) {
            this.j.H = this.f.getText().trim();
        }
        if (this.e.getText() != null) {
            this.j.G = this.e.getText().trim();
        }
        this.j.L = this.m.getState();
        final String trim3 = this.h.getText().trim();
        final int b = this.a.a().b();
        if (b != this.j.G || !trim3.equals(this.j.g()) || this.b.e != this.j.ag || this.d.e != this.j.ah) {
            final V v = new V(67334, 1);
            v.j = -1;
            v.u = -1;
            v.a(0, 21, true);
            v.a(0, 0, this.j.b());
            v.a(0, 1, b);
            v.a(0, 0, trim3);
            v.a(0, 3, this.b.e);
            v.a(0, 6, this.d.e);
            this.j.F(v);
        }
    }
    
    public bs(final aW j) {
        super(aG.a("Profile"), j);
        this.a = new U();
        (this.h = new TextField(25)).setBackground(Color.white);
        this.i = new TextField(25);
        this.c = new TextField(4);
        this.d = new TextField(25);
        this.a = new TextField(25);
        this.e = new TextField(25);
        this.f = new TextField(25);
        this.b = new aa(j, this.h, "setForeground");
        this.d = new aa(j, this.h, "setBackground");
        this.m = new Checkbox(aG.a("Block profile from other users"), null, false);
        this.j = j;
        (this.f = new Choice()).addItem(" ");
        this.f.addItem(aG.a("Male"));
        this.f.addItem(aG.a("Female"));
        if (j.c(31) || !F.b || F.a >= 65800 || F.f != 1) {
            if (j.c(21)) {
                final Panel panel = new Panel(new GridBagLayout());
                panel.add(this.h);
                panel.add(this.b);
                if (j.c(51)) {
                    panel.add(this.d);
                }
                this.a(aG.a("Nickname"), panel);
            }
            else {
                this.a(aG.a("Nickname"), this.h);
            }
        }
        this.h.setEnabled(j.c(31));
        this.h.setEditable(j.c(31));
        if (j.c(37)) {
            this.a(aG.a("Real Name"), this.i);
            this.a(aG.a("Age"), this.c);
            this.a(aG.a("Gender"), this.f);
            this.a(aG.a("E-mail Address"), this.d);
            this.a(aG.a("URL"), this.a);
            this.a(aG.a("Exit Message"), this.e);
            this.a(aG.a("Comments"), this.f);
            this.a(null, this.m);
        }
        if (j.c(30)) {
            this.a(this.a, 1, 1.0f, 1.0f);
        }
    }
}
