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

public final class bL extends bs
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
    private j e;
    private j r;
    private I q;
    private I w;
    
    public final void q(final bw bw) {
        bw.q(eb.q("Name:"), this.q, this.q, 0);
        bw.q(eb.q("Topic:"), this.w, this.e, 0);
        bw.q(eb.q("Password (Optional):"), this.e, 0);
        bw.q(eb.q("Confirm Password:"), this.r, 0);
        bw.q(eb.q("Maximum users:"), this.q, 0);
        bw.q(eb.q("Background color:"), new Component[] { this.w, new Label(eb.q("Topic background color:")), this.r });
        final s s;
        (s = new s(2, 2, 2, 2)).add("North", this.q);
        s.add("Center", this.w);
        bw.q(s, 2, 0.0f, 0.0f);
        final cu cu = (cu)this.q.n.w(this.q.v);
        if (super.q.k && cu.q(61)) {
            bw.q(this.q, 0, 0.0f, 0.0f);
        }
        bw.q(this.w, 0, 0.0f, 0.0f);
        bw.q(this.e, 0, 0.0f, 0.0f);
        bw.q(this.r, 0, 0.0f, 0.0f);
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
    
    public final void q(final bZ bz) {
        final cq cq = (cq)bz;
        this.q.setText(super.q.q(cq.getName()));
        if (cq.q != null) {
            this.w.setText(cq.q);
        }
        else {
            this.w.setText("");
        }
        if (cq.q != null) {
            this.e.setText(cq.q.toString());
            this.r.setText(cq.q.toString());
        }
        else {
            this.e.setText("");
            this.r.setText("");
        }
        this.q.setState(cq.q(61));
        this.q.setBackground(new Color(cq.y()));
        this.w.setBackground(new Color(cq.t()));
        this.e.setBackground(new Color(cq.e()));
        this.r.setBackground(new Color(cq.i()));
        if (cq.e > 0) {
            this.q.select(String.valueOf(cq.e));
        }
        else {
            this.q.select(0);
        }
        this.w.setState(cq.q(57));
        this.e.setState(cq.q(56));
        this.r.setState(cq.q(55));
        this.q.q(new Color(cq.y()), Color.black);
        if (cq.t() != 0) {
            this.q.setBackground(new Color(cq.t()));
            this.w.setBackground(new Color(cq.i()));
        }
        else {
            this.q.setBackground(cf.w.a);
            this.w.setBackground(cf.w.a);
        }
        this.w.q(new Color(cq.e()), Color.black);
    }
    
    public final boolean q(final bZ bz) {
        final String trim = this.q.getText().trim();
        String text = this.w.getText();
        final String text2 = this.e.getText();
        final String text3 = this.r.getText();
        final cq cq = (cq)bz;
        super.q.q((aF)bz);
        if (trim.length() == 0) {
            this.q.requestFocus();
            new b(super.q, eb.q("Note"), eb.q("You must provide a name for the Chat Room.  Please re-enter this information."), super.q).setVisible(true);
            return false;
        }
        if (text2.length() > 0 && !text2.equals(text3)) {
            this.r.requestFocus();
            new b(super.q, eb.q("Note"), new String[] { eb.q("Your confirmation password does not match the new password.  Please re-enter this information."), eb.q("Room passwords are optional; you may leave the password fields blank if you do not want to password protect the room.") }, super.q).setVisible(true);
            return false;
        }
        if (text.length() == 0) {
            text = null;
        }
        cq.q = text;
        cq.a_(trim);
        if (text2.length() > 0) {
            cq.q = new ep(text2);
        }
        else {
            cq.q = null;
        }
        cq.q(61, this.q.getState());
        if (this.q.getSelectedIndex() == 0) {
            cq.e = 0;
        }
        else {
            cq.e = Integer.parseInt(this.q.getSelectedItem());
        }
        cq.q(57, this.w.getState());
        cq.q(56, this.e.getState());
        cq.q(55, this.r.getState());
        cq.y(this.q.q());
        cq.t(this.w.q());
        cq.w(this.e.q());
        cq.p(this.r.q());
        return true;
    }
    
    public final bZ q() {
        final cq cq;
        (cq = new cq(-999, "")).i(56);
        return cq;
    }
    
    public final void q() {
        Label_0066: {
            if (!super.q) {
                int i = 0;
                while (true) {
                    while (i < this.q()) {
                        final cr cr;
                        if ((cr = (cr)this.q(i)).q(62)) {
                            final boolean b = cr.q() != this.q;
                            if (b) {
                                break Label_0066;
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
        final Vector<cr> vector = new Vector<cr>();
        final Vector<cr> vector2 = new Vector<cr>();
        for (int j = 0; j < this.q(); ++j) {
            final cr cr2;
            final cr cr3;
            if ((cr2 = (cr)this.q(j)).q != -999 && (cr3 = (cr)this.w(cr2.q)) != null && cr3.q(63)) {
                cr2.i(63);
            }
            if (cr2.q(63)) {
                vector2.addElement(cr2);
            }
            else {
                final cr cr4;
                if ((cr4 = (cr)this.q.y.w(cr2.q())) == null || cr2.q(cr4) != 0) {
                    vector.addElement(cr2);
                }
            }
        }
        this.w(vector2);
        this.q(vector);
        super.q = false;
    }
    
    private void q(final Vector vector) {
        if (vector.size() == 0) {
            return;
        }
        final es es;
        (es = new es(17236481, vector.size())).w = -1;
        es.q = -1;
        es.e = super.q.q();
        for (int i = 0; i < vector.size(); ++i) {
            final cr cr = vector.elementAt(i);
            es.q(i, cr.q());
            es.q(i, 0, cr.q());
            if (!cr.q(63)) {
                es.q(i, 1, cr.q);
                es.q(i, 2, cr.w);
                es.q(i, 3, cr.e);
                es.q(i, 5, cr.y());
                es.q(i, 6, cr.t());
                es.q(i, 7, cr.e());
                es.q(i, 8, cr.i());
                es.q(i, 0, cr.getName());
                es.q(i, 1, cr.q);
                es.q(i, 2, cr.w);
                es.q(i, 0, cr.q);
                es.q(i, cr.q());
            }
        }
        super.q.q(es);
    }
    
    private void w(final Vector vector) {
        if (vector.size() == 0) {
            return;
        }
        final es es;
        (es = new es(17236482, vector.size())).w = -1;
        es.q = -1;
        for (int i = 0; i < vector.size(); ++i) {
            final cr cr = vector.elementAt(i);
            es.q(i, cr.q());
            es.q(i, 0, cr.q());
        }
        super.q.q(es);
    }
    
    public final void w() {
        super.w();
        final dW y = super.q.y;
        dW.q();
        try {
            for (int i = 0; i < super.q.y.q(); ++i) {
                final cr cr;
                if ((cr = (cr)super.q.y.q(i)).q(62)) {
                    this.q = cr.q();
                }
                this.e(new cq(cr));
            }
        }
        finally {
            final dW y2 = super.q.y;
            dW.w();
        }
    }
    
    public bL(final cV cv) {
        super(cv, eb.q("Rooms"), eb.q("Room"));
        this.e = new j();
        this.r = new j();
        this.q = new I(true);
        this.w = new I(true);
        this.q = new TextField(20);
        this.w = new TextField(30);
        this.e = new TextField(15);
        this.r = new TextField(15);
        (this.q = new Choice()).setForeground(Color.black);
        this.q = new Checkbox(eb.q("Moderated"));
        this.w = new Checkbox(eb.q("Suppress enter/exit messages"));
        this.e = new Checkbox(eb.q("Competition"));
        this.r = new Checkbox(eb.q("Special"));
        final y y;
        (y = new y(a.w, "lock")).w(16);
        this.q(super.q, 0);
        this.q(y, 2);
        y.e(0);
        this.q.addItem(eb.q("No Maximum"));
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
        this.q.q(eb.q("Sample Room Name"), null);
        this.w.q(eb.q("Sample Room Topic"), null);
        this.r.setVisible(true);
        this.r.e();
        this.t.setVisible(true);
        this.t.e();
    }
}
