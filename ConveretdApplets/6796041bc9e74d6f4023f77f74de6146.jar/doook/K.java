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

public class K extends o
{
    private bV a;
    private TextField b;
    private TextField c;
    private TextField d;
    private TextField e;
    private TextField f;
    private TextField g;
    private TextField h;
    private Checkbox l;
    private Choice h;
    private u f;
    private aX a;
    private aX b;
    
    public String a(final Object o) {
        if (o == this.b) {
            return ao.e("Enter your name here.  This information is required, and may not be the same as another connected user.");
        }
        if (o == this.c) {
            return ao.e("You may enter your real name here.");
        }
        if (o == this.d) {
            return ao.e("You may enter your age here.");
        }
        if (o == this.f) {
            return ao.e("You may enter your website URL here.");
        }
        if (o == this.e) {
            return ao.e("You may enter your e-mail address here.");
        }
        if (o == this.g) {
            return ao.e("You may enter a message to be displayed each time you leave chat or change rooms.");
        }
        if (o == this.h) {
            return ao.e("You may enter any additional comments here.");
        }
        if (o == this.h) {
            return ao.e("Select your gender.  This information is optional.");
        }
        if (o == this.l) {
            return ao.e("Check this box to prevent other users (other than ChatMasters) from viewing your profile.");
        }
        return null;
    }
    
    public void d() {
        if (this.f.z == 1) {
            this.h.select(1);
        }
        else if (this.f.z == 0) {
            this.h.select(2);
        }
        if (this.f.w != -999) {
            this.d.setText(String.valueOf(this.f.w));
        }
        this.b.setText(this.f.f());
        this.c.setText(this.f.r);
        this.f.setText(this.f.s);
        this.e.setText(this.f.t);
        this.h.setText(this.f.v);
        this.g.setText(this.f.u);
        this.l.setState(this.f.C);
        this.a.aB = 15;
        this.a.a(this.f.k, true, false);
        this.a.a(new Color(this.f.aN));
        this.b.aB = 15;
        this.b.a(this.f.k, true, false);
        this.b.a(new Color(this.f.d));
        this.a.a();
        this.f.b.a(false);
        try {
            synchronized (this.f.b) {
                for (int i = 0; i < this.f.b.b(); ++i) {
                    final as as = (as)this.f.b.a(i);
                    if (as.Q != null && as.Q.length() > 0) {
                        if (as.Q.equalsIgnoreCase(this.f.E)) {
                            this.a.a(as);
                        }
                    }
                    else if (as.d(36)) {
                        if (this.f.d(36)) {
                            this.a.a(as);
                        }
                    }
                    else {
                        this.a.a(as);
                    }
                }
            }
        }
        finally {
            this.f.b.a();
        }
        this.a.a(this.f.s);
    }
    
    public void c() {
        final String trim = this.b.getText().trim();
        if (trim.length() == 0) {
            this.b.setText(this.f.f());
            throw new aq(ao.e("You must enter a name.  Please re-enter this information."));
        }
        if (trim.length() > 99) {
            this.b.setText(trim.substring(0, 98));
            throw new aq(ao.e("Nickname must be 99 characters or less.  Please re-enter this information"));
        }
        final String trim2 = this.d.getText().trim();
        if (trim2.length() == 0) {
            this.f.w = -999;
        }
        else {
            try {
                this.f.w = Integer.parseInt(trim2);
            }
            catch (NumberFormatException ex) {
                this.d.requestFocus();
                this.d.selectAll();
                throw new aq(ao.e("The age you entered is not valid.  Please re-enter this information, or leave the field blank."));
            }
        }
        switch (this.h.getSelectedIndex()) {
            case 1: {
                this.f.z = 1;
                break;
            }
            case 2: {
                this.f.z = 0;
                break;
            }
            default: {
                this.f.z = -999;
                break;
            }
        }
        if (this.c.getText() != null) {
            this.f.r = this.c.getText().trim();
        }
        if (this.f.getText() != null) {
            this.f.s = this.f.getText().trim();
        }
        if (this.e.getText() != null) {
            this.f.t = this.e.getText().trim();
        }
        if (this.h.getText() != null) {
            this.f.v = this.h.getText().trim();
        }
        if (this.g.getText() != null) {
            this.f.u = this.g.getText().trim();
        }
        this.f.C = this.l.getState();
        final String trim3 = this.b.getText().trim();
        final int h = this.a.a().h();
        if (h != this.f.s || !trim3.equals(this.f.f()) || this.a.aA != this.f.aN || this.b.aA != this.f.d) {
            final cD cd = new cD(67334, 1);
            cd.a(0, 21, true);
            cd.j = -1;
            cd.o = -1;
            cd.a(0, 0, this.f.h());
            cd.a(0, 1, h);
            cd.a(0, 0, trim3);
            cd.a(0, 3, this.a.aA);
            cd.a(0, 6, this.b.aA);
            this.f.o(cd);
        }
    }
    
    public K(final u f) {
        super(ao.e("Profile"), f);
        this.a = new bV();
        this.b = new TextField(25);
        this.c = new TextField(25);
        this.d = new TextField(4);
        this.e = new TextField(25);
        this.f = new TextField(25);
        this.g = new TextField(25);
        this.h = new TextField(25);
        this.a = new aX(f, this.b, "setForeground");
        this.b = new aX(f, this.b, "setBackground");
        this.l = new Checkbox(ao.e("Block profile from other users"), null, false);
        this.f = f;
        (this.h = new Choice()).addItem(" ");
        this.h.addItem(ao.e("Male"));
        this.h.addItem(ao.e("Female"));
        if (f.d(31) || !doook.f.d || doook.f.i >= 65800 || doook.f.h != 1) {
            if (f.d(21)) {
                final Panel panel = new Panel(new GridBagLayout());
                panel.add(this.b);
                panel.add(this.a);
                if (f.d(51)) {
                    panel.add(this.b);
                }
                this.a(ao.e("Nickname"), panel);
            }
            else {
                this.a(ao.e("Nickname"), this.b);
            }
        }
        this.b.setEnabled(f.d(31));
        this.b.setEditable(f.d(31));
        if (f.d(37)) {
            this.a(ao.e("Real Name"), this.c);
            this.a(ao.e("Age"), this.d);
            this.a(ao.e("Gender"), this.h);
            this.a(ao.e("E-mail Address"), this.e);
            this.a(ao.e("URL"), this.f);
            this.a(ao.e("Exit Message"), this.g);
            this.a(ao.e("Comments"), this.h);
            this.a(null, this.l);
        }
        if (f.d(30)) {
            this.a(this.a, 1, 1.0f, 1.0f);
        }
    }
}
