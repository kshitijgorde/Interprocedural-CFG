// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.util.Vector;
import java.awt.Color;
import java.awt.Event;
import java.awt.Label;
import java.awt.Component;
import java.awt.Checkbox;
import java.awt.Choice;
import java.awt.TextField;

public final class aQ extends G
{
    private TextField q;
    private TextField w;
    private TextField e;
    private TextField r;
    private int q;
    private Choice q;
    private Checkbox q;
    private Checkbox w;
    private Checkbox e;
    private Checkbox r;
    private dS e;
    private dS r;
    private E q;
    private E w;
    
    public final void q(final dK dk) {
        dk.q(be.w("Name:"), this.q, this.q, 0);
        dk.q(be.w("Topic:"), this.w, this.e, 0);
        dk.q(be.w("Password (Optional):"), this.e, 0);
        dk.q(be.w("Confirm Password:"), this.r, 0);
        dk.q(be.w("Maximum users:"), this.q, 0);
        dk.q(be.w("Background color:"), new Component[] { this.w, new Label(be.w("Topic background color:")), this.r });
        final dT dt;
        (dt = new dT(2, 2, 2, 2)).add("North", this.q);
        dt.add("Center", this.w);
        dk.q(dt, 2, 0.0f, 0.0f);
        final L l = (L)this.q.r.w(this.q.S);
        if (super.q.k && l.q(61)) {
            dk.q(this.q, 0, 0.0f, 0.0f);
        }
        dk.q(this.w, 0, 0.0f, 0.0f);
        dk.q(this.e, 0, 0.0f, 0.0f);
        dk.q(this.r, 0, 0.0f, 0.0f);
        this.e.setEchoCharacter('*');
        this.r.setEchoCharacter('*');
    }
    
    public final boolean q(final Event event) {
        switch (event.id) {
            case 1004: {
                if (event.target == this.q) {
                    this.q.q(this.q.getBackground(), Color.black);
                }
                if (event.target == this.w && this.w.q() != 0) {
                    this.q.setBackground(this.w.getBackground());
                }
                if (event.target == this.r && this.r.q() != 0) {
                    this.w.setBackground(this.r.getBackground());
                }
                if (event.target == this.e) {
                    this.w.q(this.e.getBackground(), Color.black);
                    break;
                }
                break;
            }
            case 1001: {
                if (event.target == this.q && this.q.getState()) {
                    this.e.setState(false);
                }
                if (event.target == this.e && this.e.getState()) {
                    this.q.setState(false);
                    break;
                }
                break;
            }
        }
        return super.q(event);
    }
    
    public final void q(final bp bp) {
        final co co = (co)bp;
        this.q.setText(super.q.q(co.a));
        if (co.q != null) {
            this.w.setText(co.q);
        }
        else {
            this.w.setText("");
        }
        if (co.q != null) {
            this.e.setText(co.q.toString());
            this.r.setText(co.q.toString());
        }
        else {
            this.e.setText("");
            this.r.setText("");
        }
        this.q.setState(co.q(61));
        this.q.setBackground(new Color(co.w()));
        this.w.setBackground(new Color(co.g));
        this.e.setBackground(new Color(co.t()));
        this.r.setBackground(new Color(co.y()));
        if (co.e > 0) {
            this.q.select(String.valueOf(co.e));
        }
        else {
            this.q.select(0);
        }
        this.w.setState(co.q(57));
        this.e.setState(co.q(56));
        this.r.setState(co.q(55));
        this.q.q(new Color(co.w()), Color.black);
        if (co.g != 0) {
            this.q.setBackground(new Color(co.g));
            this.w.setBackground(new Color(co.y()));
        }
        else {
            this.q.setBackground(bC.w.a);
            this.w.setBackground(bC.w.a);
        }
        this.w.q(new Color(co.t()), Color.black);
    }
    
    public final boolean q(final bp bp) {
        final String trim = this.q.getText().trim();
        String text = this.w.getText();
        final String text2 = this.e.getText();
        final String text3 = this.r.getText();
        final co co = (co)bp;
        super.q.q((bJ)bp);
        if (trim.length() == 0) {
            this.q.requestFocus();
            new dd(super.q, be.w("Note"), be.w("You must provide a name for the Chat Room.  Please re-enter this information."), super.q).setVisible(true);
            return false;
        }
        if (text2.length() > 0 && !text2.equals(text3)) {
            this.r.requestFocus();
            new dd(super.q, be.w("Note"), new String[] { be.w("Your confirmation password does not match the new password.  Please re-enter this information."), be.w("Room passwords are optional; you may leave the password fields blank if you do not want to password protect the room.") }, super.q).setVisible(true);
            return false;
        }
        if (text.length() == 0) {
            text = null;
        }
        co.q = text;
        co.a = trim;
        if (text2.length() > 0) {
            co.q = new dD(text2);
        }
        else {
            co.q = null;
        }
        co.q(61, this.q.getState());
        if (this.q.getSelectedIndex() == 0) {
            co.e = 0;
        }
        else {
            co.e = Integer.parseInt(this.q.getSelectedItem());
        }
        co.q(57, this.w.getState());
        co.q(56, this.e.getState());
        co.q(55, this.r.getState());
        co.p(this.q.q());
        co.o(this.w.q());
        co.w(this.e.q());
        co.e(this.r.q());
        return true;
    }
    
    public final bp q() {
        final co co;
        (co = new co(-999, "")).s(56);
        return co;
    }
    
    public final void q() {
        Label_0069: {
            if (!super.w) {
                int i = 0;
                while (true) {
                    while (i < this.q()) {
                        final cB cb;
                        if ((cb = (cB)this.q(i)).q(62)) {
                            final boolean b = cb.s != this.q;
                            if (b) {
                                break Label_0069;
                            }
                            return;
                        }
                        else {
                            ++i;
                        }
                    }
                    final boolean b = true;
                    continue;
                }
            }
        }
        final Vector<cB> vector = new Vector<cB>();
        final Vector<cB> vector2 = new Vector<cB>();
        for (int j = 0; j < this.q(); ++j) {
            final cB cb2;
            final cB cb3;
            if ((cb2 = (cB)this.q(j)).q != -999 && (cb3 = (cB)this.w(cb2.q)) != null && cb3.q(63)) {
                cb2.s(63);
            }
            if (cb2.q(63)) {
                vector2.addElement(cb2);
            }
            else {
                final cB cb4;
                if ((cb4 = (cB)this.q.f.w(cb2.s)) == null || cb2.q(cb4) != 0) {
                    vector.addElement(cb2);
                }
            }
        }
        this.w(vector2);
        this.q(vector);
        super.w = false;
    }
    
    private void q(final Vector vector) {
        if (vector.size() == 0) {
            return;
        }
        final dI di;
        (di = new dI(17236481, vector.size())).w = -1;
        di.q = -1;
        di.e = super.q.s;
        for (int i = 0; i < vector.size(); ++i) {
            final cB cb = vector.elementAt(i);
            di.q(i, cb.w());
            di.q(i, 0, cb.s);
            if (!cb.q(63)) {
                di.q(i, 1, cb.q);
                di.q(i, 2, cb.w);
                di.q(i, 3, cb.e);
                di.q(i, 5, cb.w());
                di.q(i, 6, cb.g);
                di.q(i, 7, cb.t());
                di.q(i, 8, cb.y());
                di.q(i, 0, cb.a);
                di.q(i, 1, cb.q);
                di.q(i, 2, cb.w);
                di.q(i, 0, cb.q);
                di.q(i, cb.w());
            }
        }
        super.q.o(di);
    }
    
    private void w(final Vector vector) {
        if (vector.size() == 0) {
            return;
        }
        final dI di;
        (di = new dI(17236482, vector.size())).w = -1;
        di.q = -1;
        for (int i = 0; i < vector.size(); ++i) {
            final cB cb = vector.elementAt(i);
            di.q(i, cb.w());
            di.q(i, 0, cb.s);
        }
        super.q.o(di);
    }
    
    public final void w() {
        super.w();
        try {
            for (int i = 0; i < super.q.f.q; ++i) {
                final cB cb;
                if ((cb = (cB)super.q.f.q(i)).q(62)) {
                    this.q = cb.s;
                }
                this.e(new co(cb));
            }
        }
        finally {}
    }
    
    public aQ(final ap ap) {
        super(ap, be.w("Rooms"), be.w("Room"));
        this.e = new dS();
        this.r = new dS();
        this.q = new E(true);
        this.w = new E(true);
        this.q = new TextField(20);
        this.w = new TextField(30);
        this.e = new TextField(15);
        this.r = new TextField(15);
        (this.q = new Choice()).setForeground(Color.black);
        this.q = new Checkbox(be.w("Moderated"));
        this.w = new Checkbox(be.w("Suppress enter/exit messages"));
        this.e = new Checkbox(be.w("Competition"));
        this.r = new Checkbox(be.w("Special"));
        final aX ax;
        (ax = new aX(dN.w, "lock")).w(16);
        this.q(super.q, 0);
        this.q(ax, 2);
        ax.e(0);
        this.q.addItem(be.w("No Maximum"));
        this.q.addItem("2");
        this.q.addItem("3");
        this.q.addItem("4");
        this.q.addItem("5");
        this.q.addItem("10");
        this.q.addItem("15");
        this.q.addItem("20");
        this.q.addItem("25");
        this.q.addItem("30");
        this.q.addItem("35");
        this.q.addItem("40");
        this.q.addItem("45");
        this.q.addItem("50");
        this.q.addItem("75");
        this.q.addItem("100");
        this.q.q(be.w("Sample Room Name"), null);
        this.w.q(be.w("Sample Room Topic"), null);
        this.e.setVisible(true);
        this.e.e();
        this.r.setVisible(true);
        this.r.e();
    }
}
