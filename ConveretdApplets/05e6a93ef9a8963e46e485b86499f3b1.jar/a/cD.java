// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Component;
import java.awt.CheckboxGroup;
import java.util.NoSuchElementException;
import java.awt.Choice;
import java.awt.Checkbox;
import java.awt.TextField;

public final class cD extends bN
{
    private Y q;
    private TextField q;
    private TextField w;
    private TextField e;
    private TextField r;
    private TextField t;
    private TextField y;
    private TextField u;
    private Checkbox q;
    private Choice q;
    private W q;
    private cu q;
    private cu w;
    
    public final String q(final Object o) {
        if (o == this.q) {
            return al.q("Enter your name here.  This information is required, and may not be the same as another connected user.");
        }
        if (o == this.w) {
            return al.q("You may enter your real name here.");
        }
        if (o == this.e) {
            return al.q("You may enter your age here.");
        }
        if (o == this.t) {
            return al.q("You may enter your website URL here.");
        }
        if (o == this.r) {
            return al.q("You may enter your e-mail address here.");
        }
        if (o == this.y) {
            return al.q("You may enter a message to be displayed each time you leave chat or change rooms.");
        }
        if (o == this.u) {
            return al.q("You may enter any additional comments here.");
        }
        if (o == this.q) {
            return al.q("Select your gender.  This information is optional.");
        }
        if (o == this.q) {
            return al.q("Check this box to prevent other users (other than ChatMasters) from viewing your profile.");
        }
        if (o == this.q) {
            return al.q("Will change you nickname color.");
        }
        if (o == this.w) {
            return al.q("Will change you nickname background color.");
        }
        return null;
    }
    
    public final void w() {
        if (this.q.w == 1) {
            this.q.select(1);
        }
        else if (this.q.w == 2) {
            this.q.select(2);
        }
        if (this.q.q != -999) {
            this.e.setText(String.valueOf(this.q.q));
        }
        this.q.setText(this.q.o);
        this.w.setText(this.q.q);
        this.t.setText(this.q.w);
        this.r.setText(this.q.e);
        this.u.setText(this.q.r);
        this.y.setText(this.q.t);
        this.q.setState(this.q.q);
        this.q.q();
        try {
            synchronized (this.q.w) {
                for (int i = 0; i < this.q.w.q; ++i) {
                    final aw aw;
                    if ((aw = (aw)this.q.w.q(i)).a >= 0 && (aw.q <= 0 || aw.q == this.q.p) && (!aw.q(36) || this.q.q(36))) {
                        this.q.q(aw);
                    }
                }
            }
        }
        finally {}
        this.q.q(this.q.k);
        this.q.q(this.q.w());
        this.w.q(this.q.f);
    }
    
    public final void q() {
        final String trim;
        if ((trim = this.q.getText().trim()).length() == 0) {
            this.q.setText(this.q.o);
            throw new E(al.q("You must enter a name.  Please re-enter this information."));
        }
        if (this.q != null && trim.length() > this.q.Y) {
            this.q.setText(trim.substring(0, this.q.Y));
        }
        final String trim2;
        if ((trim2 = this.e.getText().trim()).length() == 0) {
            this.q.q = -999;
        }
        else {
            try {
                this.q.q = Integer.parseInt(trim2);
            }
            catch (NumberFormatException ex) {
                this.e.requestFocus();
                this.e.selectAll();
                throw new E(al.q("The age you entered is not valid.  Please re-enter this information, or leave the field blank."));
            }
        }
        switch (this.q.getSelectedIndex()) {
            case 1: {
                this.q.w = 1;
                break;
            }
            case 2: {
                this.q.w = 2;
                break;
            }
            default: {
                this.q.w = -999;
                break;
            }
        }
        if (this.w.getText() != null) {
            this.q.q = this.w.getText().trim();
        }
        if (this.t.getText() != null) {
            this.q.w = this.t.getText().trim();
        }
        if (this.r.getText() != null) {
            this.q.e = this.r.getText().trim();
        }
        if (this.u.getText() != null) {
            this.q.r = this.u.getText().trim();
        }
        if (this.y.getText() != null) {
            this.q.t = this.y.getText().trim();
        }
        this.q.q = this.q.getState();
        final String trim3 = this.q.getText().trim();
        int n = this.q.k;
        try {
            n = this.q.q().a;
        }
        catch (NoSuchElementException ex2) {}
        if (n != this.q.k || !trim3.equals(this.q.o) || this.q.q() != 0 || this.w.q() != 0) {
            final cp cp;
            (cp = new cp(67334, 1)).w = -1;
            cp.q = -1;
            cp.q(0, 0, this.q.a);
            cp.q(0, 1, n);
            cp.q(0, 0, trim3);
            if (this.q.q(3)) {
                cp.q(0, 3, this.q.q());
            }
            if (this.q.q(17)) {
                cp.q(0, 4, this.w.q());
            }
            this.q.r(cp);
        }
        final cp cp2;
        (cp2 = new cp(17237505, 1)).w = -1;
        cp2.q = -1;
        cp2.q(0, 0, this.q.q);
        cp2.q(0, 0, this.q.a);
        cp2.q(0, 1, this.q.q);
        cp2.q(0, 2, this.q.w);
        cp2.q(0, 0, trim3);
        cp2.q(0, 1, this.q.q);
        cp2.q(0, 2, this.q.w);
        cp2.q(0, 3, this.q.e);
        cp2.q(0, 4, this.q.r);
        cp2.q(0, 5, this.q.t);
        this.q.r(cp2);
    }
    
    public cD(final W q) {
        super(al.q("Profile"));
        this.q = new Y();
        this.q = new TextField(25);
        this.w = new TextField(25);
        this.e = new TextField(4);
        this.r = new TextField(25);
        this.t = new TextField(25);
        this.y = new TextField(25);
        this.u = new TextField(25);
        this.q = new Checkbox(al.q("Block profile from other users"), null, false);
        this.q = new cu(W.q(1));
        this.w = new cu(W.q(2));
        this.q = q;
        (this.q = new Choice()).addItem(" ");
        this.q.addItem(al.q("Male"));
        this.q.addItem(al.q("Female"));
        if (q.q(31) || !bE.w || bE.e >= 65800 || bE.w != 1) {
            if (q.q(3) && q.q(17)) {
                this.q(al.q("Nickname"), new Component[] { this.q, this.q, this.w });
            }
            else if (q.q(3)) {
                this.q(al.q("Nickname"), new Component[] { this.q, this.q });
            }
            else if (q.q(17)) {
                this.q(al.q("Nickname"), new Component[] { this.q, this.w });
            }
            else {
                this.q(al.q("Nickname"), this.q);
            }
        }
        this.q.enable(q.q(31));
        this.q.setEditable(q.q(31));
        if (q.q(37)) {
            this.q(al.q("Real Name"), this.w);
            this.q(al.q("Age"), this.e);
            this.q(al.q("Gender"), this.q);
            this.q(al.q("E-mail Address"), this.r);
            this.q(al.q("URL"), this.t);
            this.q(al.q("Exit Message"), this.y);
            this.q(al.q("Comments"), this.u);
            this.q(null, this.q);
        }
        if (q.q(30)) {
            this.q(this.q, 1, 1.0f, 1.0f);
        }
    }
}
