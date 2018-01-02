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

public final class ee extends G
{
    private TextField q;
    private TextField w;
    private TextArea q;
    private aq q;
    private Checkbox q;
    private Checkbox w;
    private Choice q;
    private Choice w;
    private bV w;
    private boolean e;
    private boolean r;
    private long q;
    private dS e;
    private Checkbox e;
    
    public final bp q() {
        final r r;
        (r = new r(-999, "")).r = "";
        return r;
    }
    
    public final void q(final bp bp) {
        final cz cz = (cz)bp;
        this.q.setText(cz.a);
        this.q.q(cz.q);
        if (this.q.q() < 0) {
            this.q.w(0);
        }
        this.q.setText(cz.r);
        this.e.setState(cz.w());
        if (cz.q(22)) {
            this.w.select(1);
        }
        else if (cz.q(23)) {
            this.w.select(2);
        }
        else {
            this.w.select(0);
        }
        this.q.setBackground(new Color(cz.w()));
        this.w.setBackground(new Color(cz.f));
        this.e.setBackground(new Color(cz.g));
    }
    
    public final boolean q(final bp bp) {
        final cz cz = (cz)bp;
        final String text = this.q.getText();
        final String text2 = this.q.getText();
        if (text.length() == 0) {
            this.q.requestFocus();
            new dd(super.q, be.w("Note"), be.w("You must provide a name for this ChatCast.  Please re-enter this information."), super.q).setVisible(true);
            return false;
        }
        if (text2.length() == 0) {
            this.q.requestFocus();
            new dd(super.q, be.w("Note"), be.w("You must provide a message for this ChatCast.  Please re-enter this information."), super.q).setVisible(true);
            return false;
        }
        cz.r = text2;
        cz.a = text;
        cz.q = this.q.q().s;
        cz.w(this.e.getState());
        cz.q(22, this.w.getSelectedIndex() == 1);
        cz.q(23, this.w.getSelectedIndex() == 2);
        cz.p(this.q.q());
        cz.i(this.w.q());
        cz.o(this.e.q());
        return true;
    }
    
    public final void q(final dK dk) {
        dk.q(be.w("Name:"), this.q, this.q, 0);
        dk.q(be.w("ChatCast:"), this.q, this.w, 0);
        dk.q(be.w("User Type:"), this.w, new Label(be.w("Back Color:")), this.e, 0);
        dk.q(be.w("Welcome"), this.e, 0);
        this.q.resize(200, 82);
        dk.q(this.q, 1, 1.0f, 1.0f);
    }
    
    public final void q() {
        final boolean state = this.w.getState();
        final boolean b = this.q.getSelectedIndex() == 1;
        long n;
        try {
            if (((n = Integer.parseInt(this.w.getText().trim())) < 10L && !b) || n < 1L) {
                this.w.selectAll();
                this.w.requestFocus();
                throw new Q(B.q(be.w("The %1 display time must be at least ten seconds long.  Please specify a larger value."), new String[] { "ChatCast" }));
            }
        }
        catch (NumberFormatException ex) {
            this.w.selectAll();
            this.w.requestFocus();
            throw new Q(B.q(be.w("The %1 display time you entered is invalid."), new String[] { "ChatCast" }));
        }
        if (super.w || this.e != state || this.r != b || this.q != n || ((bV)super.q.q(0)).q) {
            final Vector<cz> vector = new Vector<cz>();
            final Vector<cz> vector2 = new Vector<cz>();
            for (int i = 0; i < this.q(); ++i) {
                final cz cz;
                if ((cz = (cz)this.q(i)).q(63)) {
                    vector2.addElement(cz);
                }
                else {
                    final cz cz2;
                    if ((cz2 = (cz)this.q.h.w(cz.s)) == null || cz.q(cz2) != 0) {
                        vector.addElement(cz);
                    }
                }
            }
            this.q(vector2);
            this.q(vector, n, b, state);
            final dI di;
            (di = new dI(67332, 0)).w = -1;
            di.q(di.q = -1, n);
            di.q(-1, 62, state);
            di.q(-1, 32, b);
            super.q.o(di);
            super.w = false;
        }
    }
    
    private void q(final Vector vector, final long n, final boolean b, final boolean b2) {
        if (vector.size() == 0) {
            return;
        }
        final dI di;
        (di = new dI(17236993, vector.size())).w = -1;
        di.q = -1;
        for (int i = 0; i < vector.size(); ++i) {
            final cz cz = vector.elementAt(i);
            di.q(i, cz.w());
            di.q(i, 0, cz.s);
            if (!cz.q(63)) {
                di.q(i, 1, cz.w);
                di.q(i, 2, cz.q);
                di.q(i, 0, cz.a);
                di.q(i, 1, cz.r);
            }
            di.q(i, 3, cz.w());
            di.q(i, 4, cz.f);
            di.q(i, 5, cz.g);
            di.q(i, 22, cz.q(22));
            di.q(i, 23, cz.q(23));
        }
        di.q(-1, n);
        di.q(-1, 62, b2);
        di.q(-1, 32, b);
        super.q.o(di);
    }
    
    private void q(final Vector vector) {
        if (vector.size() == 0) {
            return;
        }
        final dI di;
        (di = new dI(17236994, vector.size())).w = -1;
        di.q = -1;
        for (int i = 0; i < vector.size(); ++i) {
            final cz cz = vector.elementAt(i);
            di.q(i, cz.w());
            di.q(i, 0, cz.s);
        }
        super.q.o(di);
    }
    
    public final void w() {
        super.w();
        try {
            for (int i = 0; i < super.q.h.q; ++i) {
                this.e(new r((cz)super.q.h.q(i)));
            }
        }
        finally {}
        this.r = dI.q(super.q.o, 32);
        if (this.r) {
            this.q.select(1);
        }
        else {
            this.q.select(0);
        }
        this.e = dI.q(super.q.o, 62);
        if (this.e) {
            this.w.setState(true);
        }
        else {
            this.q.setState(true);
        }
        this.q = (super.q.o & 0xFFFFFFFFL);
        this.w.setText(String.valueOf(this.q));
    }
    
    public ee(final ap ap) {
        super(ap, be.w("DigiCast"), be.w("DigiCast"));
        this.e = new Checkbox();
        this.q = new TextField(30);
        this.w = new TextField(5);
        this.q = new TextArea("", 5, 30, 1);
        this.q = new aq();
        (this.q = new Choice()).setForeground(Color.black);
        (this.w = new Choice()).setForeground(Color.black);
        this.e = new dS();
        this.w.addItem(be.w(cz.q));
        this.w.addItem(be.w(cz.w));
        this.w.addItem(be.w(cz.e));
        new aX("Icon");
        final CheckboxGroup checkboxGroup = new CheckboxGroup();
        this.q = new Checkbox(be.w("Sequentially"), checkboxGroup, false);
        this.w = new Checkbox(be.w("In random order"), checkboxGroup, false);
        this.q.addItem(be.w("seconds"));
        this.q.addItem(be.w("minutes"));
        this.q(be.w("Display:"), this.q);
        this.q("", this.w);
        this.q(be.w("Display every: "), this.w, this.q);
        this.q.q();
        try {
            for (int i = 0; i < ap.p.q; ++i) {
                final aZ az;
                if ((az = (aZ)ap.p.q(i)).s >= 0) {
                    this.q.q(az);
                }
            }
        }
        finally {}
        super.q.w(50);
        super.w.w(240);
        (this.w = new bV(be.w("View"), "view")).r(true);
        this.q(this.w, 0);
        this.w.w(50);
        this.w.w(false);
        final bV bv = new bV(be.w(ds.q("$I@5")), ds.q("DI@5"));
        this.q(bv);
        bv.w(false);
        this.e.setVisible(true);
        this.e.e();
        this.r.setVisible(true);
        this.r.e();
    }
}
