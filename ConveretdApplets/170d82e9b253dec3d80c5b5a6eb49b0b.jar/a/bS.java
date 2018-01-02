// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Event;
import java.util.Vector;
import java.awt.Label;
import java.awt.Component;
import java.awt.Choice;
import java.awt.TextField;

public final class bS extends bs
{
    private TextField q;
    private Choice q;
    private TextField w;
    private Choice w;
    private n q;
    private Choice e;
    private g y;
    private g u;
    private g i;
    private g o;
    private g p;
    private g a;
    private bZ q;
    
    public final bZ q() {
        final cA ca;
        (ca = new cA(-999, "")).q(0L);
        return ca;
    }
    
    public final void q(final bZ bz) {
        final cA ca = (cA)bz;
        this.q.setText(ca.getName());
        this.w.setText(ca.o);
        if (ca.d != null) {
            this.q.select(ca.d);
        }
        this.q.q();
        int q = 0;
        for (int i = 0; i < this.q.w.q(); ++i) {
            final cm cm;
            if ((cm = (cm)this.q.w.q(i)).q() >= 0) {
                this.q.q(cm);
                if (q == 0) {
                    q = cm.q();
                }
            }
        }
        if (ca.e <= 0) {
            ca.e = q;
        }
        this.q.q(ca.e);
        this.e.removeAll();
        this.e.add(cl.q);
        for (int j = 0; j < this.q.x.q(); ++j) {
            final cl cl = (cl)this.q.x.q(j);
            this.e.add(cl.getName());
            if (cl.q() == ca.l) {
                this.e.select(cl.getName());
            }
        }
        final Object w = this.q.y.w(ca.k);
        final String name = ((bZ)this.q.y.w(super.q.o)).getName();
        if (w != null) {
            final String name2 = ((bZ)w).getName();
            if (!"".equals(name2)) {
                ca.s = name2;
            }
            else {
                ca.s = name;
            }
        }
        else {
            ca.s = name;
        }
        this.w.select(ca.s);
        this.q.q(ca.y());
        this.w.q(ca.t());
    }
    
    public final boolean q(bZ bz) {
        bz = bz;
        final String trim = this.q.getText().trim();
        final String trim2 = this.w.getText().trim();
        final String trim3 = this.q.getSelectedItem().trim();
        if (trim.length() == 0) {
            new b(super.q, eb.q("Note"), eb.q("You must provide a name. Please re-enter this information."), super.q).setVisible(true);
            return false;
        }
        final String s = trim;
        final bZ bz2 = bz;
        final String s2 = s;
        int i = 0;
        while (true) {
            while (i < this.q()) {
                final bZ q = this.q(i);
                if (bz2 != q && q.getName().equalsIgnoreCase(s2)) {
                    final boolean b = true;
                    if (b) {
                        new b(super.q, eb.q("Note"), ec.q(eb.q("There is already an virtual user named \"%1.\" Please choose another name."), new String[] { trim }), super.q).setVisible(true);
                        return false;
                    }
                    if (trim2.length() == 0) {
                        new b(super.q, eb.q("Note"), eb.q("You must provide a host. Please re-enter this information."), super.q).setVisible(true);
                        return false;
                    }
                    if (trim3.length() == 0) {
                        new b(super.q, eb.q("Note"), eb.q("You must provide a country. Please re-enter this information."), super.q).setVisible(true);
                        return false;
                    }
                    bz.a_(trim);
                    ((cA)bz).o = trim2;
                    ((cA)bz).d = trim3;
                    try {
                        ((cA)bz).e = this.q.q().q();
                        ((cA)bz).q = ((cm)super.q.w.w(((cA)bz).e)).q;
                    }
                    catch (Exception ex) {
                        ((cA)bz).e = 0;
                    }
                    ((cA)bz).a = this.e.getSelectedItem();
                    ((cA)bz).l = this.q.q(((cA)bz).a);
                    ((cA)bz).p = cl.q(this.q.x, ((cA)bz).l);
                    final cx cx;
                    if ((cx = (cx)this.q.j.w(((cA)bz).p)) != null) {
                        ((cA)bz).w = cx.q;
                    }
                    bz.y(this.q.q());
                    bz.t(this.w.q());
                    ((cA)bz).k = ((bZ)super.q.y.q(this.w.getSelectedIndex())).q();
                    ((cA)bz).s = ((bZ)super.q.y.q(this.w.getSelectedIndex())).getName();
                    return true;
                }
                else {
                    ++i;
                }
            }
            final boolean b = false;
            continue;
        }
    }
    
    public final void q(final bw bw) {
        bw.q(eb.q("Name:"), this.q);
        bw.q(eb.q("Host:"), this.w);
        bw.q(eb.q("Country:"), this.q);
        bw.q(eb.q("Room:"), this.w);
        bw.q(eb.q("Color:"), new Component[] { this.q, new Label(eb.q("Background Color:")), this.w });
        bw.q(this.q, 1, 1.0f, 1.0f);
        bw.q(eb.q("Group:"), this.e);
    }
    
    public final void w(final bZ q) {
        super.w(q);
        this.q = q;
        if (q == null) {
            this.i.e();
            this.o.e();
            this.p.e();
            this.a.e();
        }
        else {
            this.i.q();
            this.o.q();
            this.p.q();
            this.a.q();
        }
        if (this.q().size() != 0) {
            this.y.q();
            this.u.q();
            return;
        }
        this.y.e();
        this.u.e();
    }
    
    private Vector q() {
        final Vector<cA> vector = new Vector<cA>();
        for (int i = 0; i < this.q.q(); ++i) {
            final cA ca;
            if ((ca = (cA)this.q.q(i)).q(0)) {
                vector.addElement(ca);
            }
        }
        return vector;
    }
    
    public final void q() {
        if (super.q) {
            final Vector<cA> vector = new Vector<cA>();
            final Vector<cA> vector2 = new Vector<cA>();
            for (int i = 0; i < this.q(); ++i) {
                final cA ca;
                if ((ca = (cA)this.q(i)).q(63)) {
                    vector2.addElement(ca);
                }
                else {
                    final cA ca2;
                    if ((ca2 = (cA)this.q.t.w(ca.q())) == null || ca.q(ca2) != 0) {
                        vector.addElement(ca);
                    }
                }
            }
            this.w(vector2);
            this.q(vector);
            super.q = false;
        }
    }
    
    private void q(final Vector vector) {
        if (vector.size() == 0) {
            return;
        }
        final es es;
        (es = new es(1074819073, vector.size())).w = -1;
        es.q = -1;
        for (int i = 0; i < vector.size(); ++i) {
            final cA ca = vector.elementAt(i);
            es.q(i, ca.q());
            es.q(i, 0, ca.q());
            es.q(i, 0, ca.getName());
            if (!ca.q(63)) {
                es.q(i, 1, ca.e);
                es.q(i, 2, ca.k);
                es.q(i, 3, ca.p);
                es.q(i, 4, ca.y());
                es.q(i, 5, ca.t());
                es.q(i, 1, ca.o);
                es.q(i, 2, ca.d);
            }
        }
        super.q.q(es);
    }
    
    private void w(final Vector vector) {
        if (vector.size() == 0) {
            return;
        }
        final es es;
        (es = new es(1074819074, vector.size())).w = -1;
        es.q = -1;
        for (int i = 0; i < vector.size(); ++i) {
            final cA ca = vector.elementAt(i);
            es.q(i, ca.q());
            es.q(i, 0, ca.q());
        }
        super.q.q(es);
    }
    
    public final void w() {
        super.w();
        final dW t = super.q.t;
        dW.q();
        try {
            for (int i = 0; i < super.q.t.q(); ++i) {
                final cA ca;
                final int q = (ca = (cA)super.q.t.q(i)).q();
                ca.q = (q >= 1000);
                this.e((bZ)ca.clone());
                super.q.q(ca, q > 1 || (super.q.q(62) && q > 0));
                ca.a = this.q.q(ca.l);
            }
        }
        finally {
            final dW t2 = super.q.t;
            dW.w();
        }
    }
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 1001: {
                if (event.target == this.y) {
                    this.q.q(this.q(false, true));
                }
                if (event.target == this.u) {
                    this.q.q(this.q(false, false));
                }
                if (event.target == this.i) {
                    this.q.q(this.q(true, true));
                }
                if (event.target == this.o) {
                    this.q.q(this.q(true, false));
                }
                if (event.target == this.p) {
                    this.q.q(this.q(true));
                }
                if (event.target == this.a) {
                    this.q.q(this.q(false));
                    break;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    private es q(final boolean b, final boolean b2) {
        final Vector q = this.q();
        int n;
        if (b) {
            n = this.q.q();
        }
        else {
            n = q.size();
        }
        final es es;
        (es = new es(4198514, n)).q(-1, 0, !b2);
        for (int i = 0; i < n; ++i) {
            bZ bz;
            if (b) {
                bz = (bZ)this.q.q(i);
            }
            else {
                bz = q.elementAt(i);
            }
            es.q(i, 0, bz.q());
        }
        return es;
    }
    
    private es q(final boolean b) {
        final es es;
        (es = new es(4198514, 1)).q(-1, 0, !b);
        es.q(0, 0, this.q.q());
        return es;
    }
    
    public bS(final cV cv) {
        super(cv, eb.q("Virtual Users"), eb.q("Virtual User"));
        this.q = new TextField(30);
        this.w = new TextField(30);
        this.q = new h();
        for (int i = 0; i < ci.q.length; ++i) {
            this.q.add(ci.q[i]);
        }
        this.e = new h();
        super.w = true;
        this.w = new h();
        for (int j = 0; j < this.q.y.q(); ++j) {
            String s;
            if ((s = ((bZ)this.q.y.q(j)).getName()).length() > 35) {
                s = s.substring(0, 35);
            }
            this.w.addItem(s);
        }
        (this.q = new n()).q();
        this.y = new g(80, 20);
        this.u = new g(80, 20);
        this.i = new g(80, 20);
        this.o = new g(80, 20);
        this.p = new g(80, 20);
        this.a = new g(80, 20);
        this.y.q(eb.q("Login selected"));
        this.y.t();
        this.u.q(eb.q("Logout selected"));
        this.u.t();
        this.i.q(eb.q("Login all"));
        this.i.t();
        this.o.q(eb.q("Logout all"));
        this.o.t();
        this.p.q(eb.q("Login"));
        this.p.t();
        this.a.q(eb.q("Logout"));
        this.a.t();
        this.q("", new Component[] { this.p, this.a, this.y, this.u, this.i, this.o });
        super.w.w(100);
        final C c = new C(eb.q("Room"), "room");
        this.q(c);
        c.w(false);
        c.w(100);
        final C c2 = new C(eb.q("Country"), "country");
        this.q(c2);
        c2.w(false);
        c2.w(100);
        this.q(new C(eb.q("Group"), "group"));
        final y y;
        (y = new y(a.a.w, "iconImg")).w(22);
        this.q(super.q, 0);
        super.q.e(0);
        this.q(y, 2);
        y.e(0);
        final y y2;
        (y2 = new y(null, "starImg")).w(22);
        this.q(y2, 3);
        y2.e(0);
        final C c3;
        (c3 = new C("connect")).r(true);
        c3.w(40);
        this.q(c3, 0);
        this.r.setVisible(true);
        this.r.e();
        this.t.setVisible(true);
        this.t.e();
    }
}
