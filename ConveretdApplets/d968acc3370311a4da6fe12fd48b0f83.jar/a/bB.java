// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.CheckboxGroup;
import java.util.Vector;
import java.awt.Label;
import java.awt.Component;
import java.awt.Color;
import java.awt.Choice;
import java.awt.Checkbox;
import java.awt.TextArea;
import java.awt.TextField;

public final class bB extends bs
{
    private TextField q;
    private TextField w;
    private TextArea q;
    private n q;
    private Checkbox q;
    private Checkbox w;
    private Choice q;
    private Choice w;
    private C w;
    private boolean e;
    private boolean r;
    private long q;
    private j e;
    private Checkbox e;
    
    public final bZ q() {
        final cc cc;
        (cc = new cc(-999, "")).r = "";
        return cc;
    }
    
    public final void q(final bZ bz) {
        final cb cb = (cb)bz;
        this.q.setText(cb.getName());
        this.q.q(cb.q);
        if (this.q.q() < 0) {
            this.q.w(0);
        }
        this.q.setText(cb.r);
        this.e.setState(cb.q());
        if (cb.q(22)) {
            this.w.select(1);
        }
        else if (cb.q(23)) {
            this.w.select(2);
        }
        else {
            this.w.select(0);
        }
        this.q.setBackground(new Color(cb.y()));
        this.w.setBackground(new Color(cb.r()));
        this.e.setBackground(new Color(cb.t()));
    }
    
    public final boolean q(final bZ bz) {
        final cb cb = (cb)bz;
        final String text = this.q.getText();
        final String text2 = this.q.getText();
        if (text.length() == 0) {
            this.q.requestFocus();
            new b(super.q, eb.q("Note"), eb.q("You must provide a name for this ChatCast.  Please re-enter this information."), super.q).setVisible(true);
            return false;
        }
        if (text2.length() == 0) {
            this.q.requestFocus();
            new b(super.q, eb.q("Note"), eb.q("You must provide a message for this ChatCast.  Please re-enter this information."), super.q).setVisible(true);
            return false;
        }
        cb.r = text2;
        cb.a_(text);
        cb.q = this.q.q().q();
        cb.q(this.e.getState());
        cb.q(22, this.w.getSelectedIndex() == 1);
        cb.q(23, this.w.getSelectedIndex() == 2);
        cb.y(this.q.q());
        cb.r(this.w.q());
        cb.t(this.e.q());
        return true;
    }
    
    public final void q(final bw bw) {
        bw.q(eb.q("Name:"), this.q, this.q, 0);
        bw.q(eb.q("ChatCast:"), this.q, this.w, 0);
        bw.q(eb.q("User Type:"), this.w, new Label(eb.q("Back Color:")), this.e, 0);
        bw.q(eb.q("Welcome"), this.e);
        this.q.resize(200, 82);
        bw.q(this.q, 1, 1.0f, 1.0f);
    }
    
    public final void q() {
        final boolean state = this.w.getState();
        final boolean b = this.q.getSelectedIndex() == 1;
        long n;
        try {
            if (((n = Integer.parseInt(this.w.getText().trim())) < 10L && !b) || n < 1L) {
                this.w.selectAll();
                this.w.requestFocus();
                throw new cF(ec.q(eb.q("The %1 display time must be at least ten seconds long.  Please specify a larger value."), new String[] { "ChatCast" }));
            }
        }
        catch (NumberFormatException ex) {
            this.w.selectAll();
            this.w.requestFocus();
            throw new cF(ec.q(eb.q("The %1 display time you entered is invalid."), new String[] { "ChatCast" }));
        }
        if (super.q || this.e != state || this.r != b || this.q != n || ((C)super.q.q(0)).q) {
            final Vector<cb> vector = new Vector<cb>();
            final Vector<cb> vector2 = new Vector<cb>();
            for (int i = 0; i < this.q(); ++i) {
                final cb cb;
                if ((cb = (cb)this.q(i)).q(63)) {
                    vector2.addElement(cb);
                }
                else {
                    final cb cb2;
                    if ((cb2 = (cb)this.q.i.w(cb.q())) == null || cb.q(cb2) != 0) {
                        vector.addElement(cb);
                    }
                }
            }
            this.q(vector2);
            this.q(vector, n, b, state);
            final es es;
            (es = new es(67332, 0)).w = -1;
            es.q(es.q = -1, n);
            es.q(-1, 62, state);
            es.q(-1, 32, b);
            super.q.q(es);
            super.q = false;
        }
    }
    
    private void q(final Vector vector, final long n, final boolean b, final boolean b2) {
        if (vector.size() == 0) {
            return;
        }
        final es es;
        (es = new es(17236993, vector.size())).w = -1;
        es.q = -1;
        for (int i = 0; i < vector.size(); ++i) {
            final cb cb = vector.elementAt(i);
            es.q(i, cb.q());
            es.q(i, 0, cb.q());
            if (!cb.q(63)) {
                es.q(i, 1, cb.w);
                es.q(i, 2, cb.q);
                es.q(i, 0, cb.getName());
                es.q(i, 1, cb.r);
            }
            es.q(i, 3, cb.y());
            es.q(i, 4, cb.r());
            es.q(i, 5, cb.t());
            es.q(i, 22, cb.q(22));
            es.q(i, 23, cb.q(23));
        }
        es.q(-1, n);
        es.q(-1, 62, b2);
        es.q(-1, 32, b);
        super.q.q(es);
    }
    
    private void q(final Vector vector) {
        if (vector.size() == 0) {
            return;
        }
        final es es;
        (es = new es(17236994, vector.size())).w = -1;
        es.q = -1;
        for (int i = 0; i < vector.size(); ++i) {
            final cb cb = vector.elementAt(i);
            es.q(i, cb.q());
            es.q(i, 0, cb.q());
        }
        super.q.q(es);
    }
    
    public final void w() {
        super.w();
        final dW i = super.q.i;
        dW.q();
        try {
            for (int j = 0; j < super.q.i.q(); ++j) {
                this.e(new cc((cb)super.q.i.q(j)));
            }
        }
        finally {
            final dW k = super.q.i;
            dW.w();
        }
        this.r = es.q(super.q.e, 32);
        if (this.r) {
            this.q.select(1);
        }
        else {
            this.q.select(0);
        }
        this.e = es.q(super.q.e, 62);
        if (this.e) {
            this.w.setState(true);
        }
        else {
            this.q.setState(true);
        }
        this.q = (super.q.e & 0xFFFFFFFFL);
        this.w.setText(String.valueOf(this.q));
    }
    
    public bB(final cV cv) {
        super(cv, eb.q("DigiCast"), eb.q("DigiCast"));
        this.e = new Checkbox();
        this.q = new TextField(30);
        this.w = new TextField(5);
        this.q = new TextArea("", 5, 30, 1);
        this.q = new n();
        (this.q = new Choice()).setForeground(Color.black);
        (this.w = new Choice()).setForeground(Color.black);
        this.e = new j();
        this.w.addItem(eb.q(cb.q));
        this.w.addItem(eb.q(cb.w));
        this.w.addItem(eb.q(cb.e));
        new y("Icon");
        final CheckboxGroup checkboxGroup = new CheckboxGroup();
        this.q = new Checkbox(eb.q("Sequentially"), checkboxGroup, false);
        this.w = new Checkbox(eb.q("In random order"), checkboxGroup, false);
        this.q.addItem(eb.q("seconds"));
        this.q.addItem(eb.q("minutes"));
        this.q(eb.q("Display:"), this.q);
        this.q("", this.w);
        this.q(eb.q("Display every: "), this.w, this.q);
        this.q.q();
        final dW w = cv.w;
        dW.q();
        try {
            for (int i = 0; i < cv.w.q(); ++i) {
                final cm cm;
                if ((cm = (cm)cv.w.q(i)).q() >= 0) {
                    this.q.q(cm);
                }
            }
        }
        finally {
            final dW w2 = cv.w;
            dW.w();
        }
        super.q.w(50);
        super.w.w(240);
        (this.w = new C(eb.q("View"), "view")).r(true);
        this.q(this.w, 0);
        this.w.w(50);
        this.w.w(false);
        final C c = new C(eb.q(dV.q("$I@5")), dV.q("DI@5"));
        this.q(c);
        c.w(false);
        this.r.setVisible(true);
        this.r.e();
        this.t.setVisible(true);
        this.t.e();
    }
}
