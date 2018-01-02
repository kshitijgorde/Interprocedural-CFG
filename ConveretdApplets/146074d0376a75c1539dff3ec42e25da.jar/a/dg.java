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

public final class dg extends G
{
    private n q;
    private TextField q;
    private TextField w;
    private TextField e;
    private TextField r;
    private TextField t;
    private TextField y;
    private TextField u;
    private Checkbox q;
    private Choice q;
    private cV q;
    private j q;
    private j w;
    private j e;
    private j r;
    
    public final String q(final Object o) {
        if (o == this.q) {
            return eb.q("Enter your name here.  This information is required, and may not be the same as another connected user.");
        }
        if (o == this.w) {
            return eb.q("You may enter your real name here.");
        }
        if (o == this.e) {
            return eb.q("You may enter your age here.");
        }
        if (o == this.t) {
            return eb.q("You may enter your website URL here.");
        }
        if (o == this.r) {
            return eb.q("You may enter your e-mail address here.");
        }
        if (o == this.y) {
            return eb.q("You may enter a message to be displayed each time you leave chat or change rooms.");
        }
        if (o == this.u) {
            return eb.q("You may enter any additional comments here.");
        }
        if (o == this.q) {
            return eb.q("Select your gender.  This information is optional.");
        }
        if (o == this.q) {
            return eb.q("Check this box to prevent other users (other than ChatMasters) from viewing your profile.");
        }
        if (o == this.q) {
            return eb.q("Will change you nickname color.");
        }
        if (o == this.w) {
            return eb.q("Will change you nickname background color.");
        }
        if (o == this.e) {
            return eb.q("Will change you public write background color.");
        }
        if (o == this.r) {
            return eb.q("Will change you private write background color.");
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
        this.q.setText(this.q.getName());
        this.w.setText(this.q.q);
        this.t.setText(this.q.e);
        this.r.setText(this.q.r);
        this.u.setText(this.q.y);
        this.y.setText(this.q.u);
        this.q.setState(this.q.w);
        this.q.q();
        final dW w = this.q.w;
        dW.q();
        try {
            synchronized (this.q.w) {
                for (int i = 0; i < this.q.w.q(); ++i) {
                    final cm cm;
                    if ((cm = (cm)this.q.w.q(i)).q() >= 0 && (cm.w() <= 0 || cm.w() == this.q.i()) && (!cm.q(36) || this.q.q(36))) {
                        this.q.q(cm);
                    }
                }
            }
        }
        finally {
            final dW w2 = this.q.w;
            dW.w();
        }
        this.q.q(this.q.x);
        this.q.q(this.q.y());
        this.w.q(this.q.t());
        this.e.q(this.q.w());
        this.r.q(this.q.e());
    }
    
    public final void q() {
        final String trim;
        if ((trim = this.q.getText().trim()).length() == 0) {
            this.q.setText(this.q.getName());
            throw new cF(eb.q("You must enter a name.  Please re-enter this information."));
        }
        if (this.q != null && trim.length() > this.q.O) {
            this.q.setText(trim.substring(0, this.q.O));
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
                throw new cF(eb.q("The age you entered is not valid.  Please re-enter this information, or leave the field blank."));
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
            this.q.y = this.u.getText().trim();
        }
        if (this.y.getText() != null) {
            this.q.u = this.y.getText().trim();
        }
        this.q.w = this.q.getState();
        final String trim3 = this.q.getText().trim();
        int n = this.q.x;
        try {
            n = this.q.q().q();
        }
        catch (NoSuchElementException ex2) {}
        if (n != this.q.x || !trim3.equals(this.q.getName()) || this.q.q() != 0 || this.w.q() != 0 || this.e.q() != 0 || this.r.q() != 0) {
            final es es;
            (es = new es(67334, 1)).w = -1;
            es.q = -1;
            es.q(0, 0, this.q.q());
            es.q(0, 1, n);
            es.q(0, 0, trim3);
            if (this.q.q(3)) {
                es.q(0, 3, this.q.q());
            }
            if (this.q.q(17)) {
                es.q(0, 4, this.w.q());
            }
            if (this.q.q(0)) {
                es.q(0, 8, this.e.q());
                es.q(0, 10, this.r.q());
            }
            this.q.q(es);
        }
        final es es2;
        (es2 = new es(17237505, 1)).w = -1;
        es2.q = -1;
        es2.q(0, 0, this.q.w);
        es2.q(0, 0, this.q.q());
        es2.q(0, 1, this.q.q);
        es2.q(0, 2, this.q.w);
        es2.q(0, 0, trim3);
        es2.q(0, 1, this.q.q);
        es2.q(0, 2, this.q.e);
        es2.q(0, 3, this.q.r);
        es2.q(0, 4, this.q.y);
        es2.q(0, 5, this.q.u);
        this.q.q(es2);
    }
    
    public dg(final cV q) {
        super(eb.q("Profile"));
        this.q = new n();
        this.q = new TextField(25);
        this.w = new TextField(25);
        this.e = new TextField(4);
        this.r = new TextField(25);
        this.t = new TextField(25);
        this.y = new TextField(25);
        this.u = new TextField(25);
        this.q = new Checkbox(eb.q("Block profile from other users"), null, false);
        this.q = new j(cV.q(1));
        this.w = new j(cV.q(2));
        this.e = new j();
        this.r = new j();
        this.q = q;
        (this.q = new Choice()).addItem(" ");
        this.q.addItem(eb.q("Male"));
        this.q.addItem(eb.q("Female"));
        if (q.q(31) || !ef.w || ef.e >= 65800 || ef.w != 1) {
            if (q.q(3) && q.q(17)) {
                this.q(eb.q("Nickname"), new Component[] { this.q, this.q, this.w });
            }
            else if (q.q(3)) {
                this.q(eb.q("Nickname"), new Component[] { this.q, this.q });
            }
            else if (q.q(17)) {
                this.q(eb.q("Nickname"), new Component[] { this.q, this.w });
            }
            else {
                this.q(eb.q("Nickname"), this.q);
            }
        }
        this.q.enable(q.q(31));
        this.q.setEditable(q.q(31));
        if (q.q(0)) {
            this.q(eb.q("Real Name"), new Component[] { this.w, this.e, this.r });
        }
        else if (q.q(37)) {
            this.q(eb.q("Real Name"), this.w);
        }
        this.w.enable(q.q(37));
        this.w.setEditable(q.q(37));
        if (q.q(37)) {
            this.q(eb.q("Age"), this.e);
            this.q(eb.q("Gender"), this.q);
            this.q(eb.q("E-mail Address"), this.r);
            this.q(eb.q("URL"), this.t);
            this.q(eb.q("Exit Message"), this.y);
            this.q(eb.q("Comments"), this.u);
            this.q(null, this.q);
        }
        if (q.q(30)) {
            this.q(this.q, 1, 1.0f, 1.0f);
        }
    }
}
