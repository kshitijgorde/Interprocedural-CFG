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

public final class ed extends cV
{
    private aq q;
    private TextField q;
    private TextField w;
    private TextField e;
    private TextField r;
    private TextField t;
    private TextField y;
    private TextField u;
    private Checkbox q;
    private Choice q;
    private ap q;
    private dS q;
    private dS w;
    private dS e;
    private dS r;
    
    public final String q(final Object o) {
        if (o == this.q) {
            return be.w("Enter your name here.  This information is required, and may not be the same as another connected user.");
        }
        if (o == this.w) {
            return be.w("You may enter your real name here.");
        }
        if (o == this.e) {
            return be.w("You may enter your age here.");
        }
        if (o == this.t) {
            return be.w("You may enter your website URL here.");
        }
        if (o == this.r) {
            return be.w("You may enter your e-mail address here.");
        }
        if (o == this.y) {
            return be.w("You may enter a message to be displayed each time you leave chat or change rooms.");
        }
        if (o == this.u) {
            return be.w("You may enter any additional comments here.");
        }
        if (o == this.q) {
            return be.w("Select your gender.  This information is optional.");
        }
        if (o == this.q) {
            return be.w("Check this box to prevent other users (other than ChatMasters) from viewing your profile.");
        }
        if (o == this.q) {
            return be.w("Will change you nickname color.");
        }
        if (o == this.w) {
            return be.w("Will change you nickname background color.");
        }
        if (o == this.e) {
            return be.w("Will change you public write background color.");
        }
        if (o == this.r) {
            return be.w("Will change you private write background color.");
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
        this.q.setText(this.q.a);
        this.w.setText(this.q.q);
        this.t.setText(this.q.e);
        this.r.setText(this.q.r);
        this.u.setText(this.q.t);
        this.y.setText(this.q.y);
        this.q.setState(this.q.q);
        this.q.q();
        try {
            synchronized (this.q.p) {
                for (int i = 0; i < this.q.p.q; ++i) {
                    final aZ az;
                    if ((az = (aZ)this.q.p.q(i)).s >= 0 && (az.q <= 0 || az.q == this.q.a) && (!az.q(36) || this.q.q(36))) {
                        this.q.q(az);
                    }
                }
            }
        }
        finally {}
        this.q.q(this.q.P);
        this.q.q(this.q.w());
        this.w.q(this.q.g);
        this.e.q(this.q.i);
        this.r.q(this.q.o);
    }
    
    public final void q() {
        final String trim;
        if ((trim = this.q.getText().trim()).length() == 0) {
            this.q.setText(this.q.a);
            throw new Q(be.w("You must enter a name.  Please re-enter this information."));
        }
        if (this.q != null && trim.length() > this.q.B) {
            this.q.setText(trim.substring(0, this.q.B));
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
                throw new Q(be.w("The age you entered is not valid.  Please re-enter this information, or leave the field blank."));
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
            this.q.e = this.t.getText().trim();
        }
        if (this.r.getText() != null) {
            this.q.r = this.r.getText().trim();
        }
        if (this.u.getText() != null) {
            this.q.t = this.u.getText().trim();
        }
        if (this.y.getText() != null) {
            this.q.y = this.y.getText().trim();
        }
        this.q.q = this.q.getState();
        final String trim3 = this.q.getText().trim();
        int n = this.q.P;
        try {
            n = this.q.q().s;
        }
        catch (NoSuchElementException ex2) {}
        if (n != this.q.P || !trim3.equals(this.q.a) || this.q.q() != 0 || this.w.q() != 0 || this.e.q() != 0 || this.r.q() != 0) {
            final dI di;
            (di = new dI(67334, 1)).w = -1;
            di.q = -1;
            di.q(0, 0, this.q.s);
            di.q(0, 1, n);
            di.q(0, 0, trim3);
            if (this.q.q(3)) {
                di.q(0, 3, this.q.q());
            }
            if (this.q.q(17)) {
                di.q(0, 4, this.w.q());
            }
            if (this.q.q(0)) {
                di.q(0, 8, this.e.q());
                di.q(0, 12, this.r.q());
            }
            this.q.o(di);
        }
        final dI di2;
        (di2 = new dI(17237505, 1)).w = -1;
        di2.q = -1;
        di2.q(0, 0, this.q.q);
        di2.q(0, 0, this.q.s);
        di2.q(0, 1, this.q.q);
        di2.q(0, 2, this.q.w);
        di2.q(0, 0, trim3);
        di2.q(0, 1, this.q.q);
        di2.q(0, 2, this.q.e);
        di2.q(0, 3, this.q.r);
        di2.q(0, 4, this.q.t);
        di2.q(0, 5, this.q.y);
        this.q.o(di2);
    }
    
    public ed(final ap q) {
        super(be.w("Profile"));
        this.q = new aq();
        this.q = new TextField(25);
        this.w = new TextField(25);
        this.e = new TextField(4);
        this.r = new TextField(25);
        this.t = new TextField(25);
        this.y = new TextField(25);
        this.u = new TextField(25);
        this.q = new Checkbox(be.w("Block profile from other users"), null, false);
        this.q = new dS(ap.q(1));
        this.w = new dS(ap.q(2));
        this.e = new dS();
        this.r = new dS();
        this.q = q;
        (this.q = new Choice()).addItem(" ");
        this.q.addItem(be.w("Male"));
        this.q.addItem(be.w("Female"));
        if (q.q(31) || !cK.w || cK.e >= 65800 || cK.w != 1) {
            if (q.q(3) && q.q(17)) {
                this.q(be.w("Nickname"), new Component[] { this.q, this.q, this.w });
            }
            else if (q.q(3)) {
                this.q(be.w("Nickname"), new Component[] { this.q, this.q });
            }
            else if (q.q(17)) {
                this.q(be.w("Nickname"), new Component[] { this.q, this.w });
            }
            else {
                this.q(be.w("Nickname"), this.q);
            }
        }
        this.q.enable(q.q(31));
        this.q.setEditable(q.q(31));
        if (q.q(37)) {
            if (q.q(0)) {
                this.q(be.w("Real Name"), new Component[] { this.w, this.e, this.r });
            }
            else {
                this.q(be.w("Real Name"), this.w);
            }
            this.q(be.w("Age"), this.e);
            this.q(be.w("Gender"), this.q);
            this.q(be.w("E-mail Address"), this.r);
            this.q(be.w("URL"), this.t);
            this.q(be.w("Exit Message"), this.y);
            this.q(be.w("Comments"), this.u);
            this.q(null, this.q);
        }
        if (q.q(30)) {
            this.q(this.q, 1, 1.0f, 1.0f);
        }
    }
}
