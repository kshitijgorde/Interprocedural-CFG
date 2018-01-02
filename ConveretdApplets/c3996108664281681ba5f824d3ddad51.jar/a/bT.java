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

public final class bT extends D
{
    private l q;
    private TextField q;
    private TextField w;
    private TextField e;
    private TextField r;
    private TextField t;
    private TextField y;
    private TextField u;
    private Checkbox q;
    private Choice q;
    private bI q;
    private h q;
    private h w;
    private h e;
    private h r;
    
    public final String q(final Object o) {
        if (o == this.q) {
            return cv.q("Enter your name here.  This information is required, and may not be the same as another connected user.");
        }
        if (o == this.w) {
            return cv.q("You may enter your real name here.");
        }
        if (o == this.e) {
            return cv.q("You may enter your age here.");
        }
        if (o == this.t) {
            return cv.q("You may enter your website URL here.");
        }
        if (o == this.r) {
            return cv.q("You may enter your e-mail address here.");
        }
        if (o == this.y) {
            return cv.q("You may enter a message to be displayed each time you leave chat or change rooms.");
        }
        if (o == this.u) {
            return cv.q("You may enter any additional comments here.");
        }
        if (o == this.q) {
            return cv.q("Select your gender.  This information is optional.");
        }
        if (o == this.q) {
            return cv.q("Check this box to prevent other users (other than ChatMasters) from viewing your profile.");
        }
        if (o == this.q) {
            return cv.q("Will change you nickname color.");
        }
        if (o == this.w) {
            return cv.q("Will change you nickname background color.");
        }
        if (o == this.e) {
            return cv.q("Will change you public write background color.");
        }
        if (o == this.r) {
            return cv.q("Will change you private write background color.");
        }
        return null;
    }
    
    public final void w() {
        if (this.q.y == 1) {
            this.q.select(1);
        }
        else if (this.q.y == 2) {
            this.q.select(2);
        }
        if (this.q.t != -999) {
            this.e.setText(String.valueOf(this.q.t));
        }
        this.q.setText(this.q.getName());
        this.w.setText(this.q.q);
        this.t.setText(this.q.w);
        this.r.setText(this.q.r);
        this.u.setText(this.q.t);
        this.y.setText(this.q.y);
        this.q.setState(this.q.q);
        this.q.q();
        final cq w = this.q.w;
        cq.q();
        try {
            synchronized (this.q.w) {
                for (int i = 0; i < this.q.w.q(); ++i) {
                    final bj bj;
                    if ((bj = (bj)this.q.w.q(i)).q() >= 0 && (bj.y() <= 0 || bj.y() == this.q.i()) && (!bj.q(36) || this.q.q(36))) {
                        this.q.q(bj);
                    }
                }
            }
        }
        finally {
            final cq w2 = this.q.w;
            cq.w();
        }
        this.q.q(this.q.z);
        this.q.q(this.q.r());
        this.w.q(this.q.e());
        this.e.q(this.q.y());
        this.r.q(this.q.u());
    }
    
    public final void q() {
        final String trim;
        if ((trim = this.q.getText().trim()).length() == 0) {
            this.q.setText(this.q.getName());
            throw new bs(cv.q("You must enter a name.  Please re-enter this information."));
        }
        if (this.q != null && trim.length() > this.q.I) {
            this.q.setText(trim.substring(0, this.q.I));
        }
        final String trim2;
        if ((trim2 = this.e.getText().trim()).length() == 0) {
            this.q.t = -999;
        }
        else {
            try {
                this.q.t = Integer.parseInt(trim2);
            }
            catch (NumberFormatException ex) {
                this.e.requestFocus();
                this.e.selectAll();
                throw new bs(cv.q("The age you entered is not valid.  Please re-enter this information, or leave the field blank."));
            }
        }
        switch (this.q.getSelectedIndex()) {
            case 1: {
                this.q.y = 1;
                break;
            }
            case 2: {
                this.q.y = 2;
                break;
            }
            default: {
                this.q.y = -999;
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
        int n = this.q.z;
        try {
            n = this.q.q().q();
        }
        catch (NoSuchElementException ex2) {}
        if (n != this.q.z || !trim3.equals(this.q.getName()) || this.q.q() != 0 || this.w.q() != 0 || this.e.q() != 0 || this.r.q() != 0) {
            final cJ cj;
            (cj = new cJ(67334, 1)).w = -1;
            cj.q = -1;
            cj.q(0, 0, this.q.q());
            cj.q(0, 1, n);
            cj.q(0, 0, trim3);
            if (this.q.q(3)) {
                cj.q(0, 3, this.q.q());
            }
            if (this.q.q(17)) {
                cj.q(0, 4, this.w.q());
            }
            if (this.q.q(0)) {
                cj.q(0, 8, this.e.q());
                cj.q(0, 10, this.r.q());
            }
            this.q.q(cj);
        }
        final cJ cj2;
        (cj2 = new cJ(17237505, 1)).w = -1;
        cj2.q = -1;
        cj2.q(0, 0, this.q.q);
        cj2.q(0, 0, this.q.q());
        cj2.q(0, 1, this.q.t);
        cj2.q(0, 2, this.q.y);
        cj2.q(0, 0, trim3);
        cj2.q(0, 1, this.q.q);
        cj2.q(0, 2, this.q.w);
        cj2.q(0, 3, this.q.r);
        cj2.q(0, 4, this.q.t);
        cj2.q(0, 5, this.q.y);
        this.q.q(cj2);
    }
    
    public bT(final bI q) {
        super(cv.q("Profile"));
        this.q = new l();
        this.q = new TextField(25);
        this.w = new TextField(25);
        this.e = new TextField(4);
        this.r = new TextField(25);
        this.t = new TextField(25);
        this.y = new TextField(25);
        this.u = new TextField(25);
        this.q = new Checkbox(cv.q("Block profile from other users"), null, false);
        this.q = new h(bI.q(1));
        this.w = new h(bI.q(2));
        this.e = new h();
        this.r = new h();
        this.q = q;
        (this.q = new Choice()).addItem(" ");
        this.q.addItem(cv.q("Male"));
        this.q.addItem(cv.q("Female"));
        if (q.q(31) || !cx.w || cx.e >= 65800 || cx.w != 1) {
            if (q.q(3) && q.q(17)) {
                this.q(cv.q("Nickname"), new Component[] { this.q, this.q, this.w });
            }
            else if (q.q(3)) {
                this.q(cv.q("Nickname"), new Component[] { this.q, this.q });
            }
            else if (q.q(17)) {
                this.q(cv.q("Nickname"), new Component[] { this.q, this.w });
            }
            else {
                this.q(cv.q("Nickname"), this.q);
            }
        }
        this.q.enable(q.q(31));
        this.q.setEditable(q.q(31));
        if (q.q(0)) {
            this.q(cv.q("Real Name"), new Component[] { this.w, this.e, this.r });
        }
        else if (q.q(37)) {
            this.q(cv.q("Real Name"), this.w);
        }
        this.w.enable(q.q(37));
        this.w.setEditable(q.q(37));
        if (q.q(37)) {
            this.q(cv.q("Age"), this.e);
            this.q(cv.q("Gender"), this.q);
            this.q(cv.q("E-mail Address"), this.r);
            this.q(cv.q("URL"), this.t);
            this.q(cv.q("Exit Message"), this.y);
            this.q(cv.q("Comments"), this.u);
            this.q(null, this.q);
        }
        if (q.q(30)) {
            this.q(this.q, 1, 1.0f, 1.0f);
        }
    }
}
