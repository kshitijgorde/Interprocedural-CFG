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

public final class cn extends G
{
    private TextField q;
    private Choice q;
    private TextField w;
    private Choice w;
    private aq q;
    private Choice e;
    private ad t;
    private ad y;
    private ad u;
    private ad i;
    private ad o;
    private ad p;
    private bp q;
    
    public final bp q() {
        final cr cr;
        (cr = new cr(-999, "")).q(0L);
        return cr;
    }
    
    public final void q(final bp bp) {
        final cr cr = (cr)bp;
        this.q.setText(cr.a);
        this.w.setText(cr.i);
        if (cr.d != null) {
            this.q.select(cr.d);
        }
        this.q.q();
        int s = 0;
        for (int i = 0; i < this.q.p.q; ++i) {
            final aZ az;
            if ((az = (aZ)this.q.p.q(i)).s >= 0) {
                this.q.q(az);
                if (s == 0) {
                    s = az.s;
                }
            }
        }
        if (cr.e <= 0) {
            cr.e = s;
        }
        this.q.q(cr.e);
        this.e.removeAll();
        this.e.add(de.q);
        for (int j = 0; j < this.q.y.q; ++j) {
            final de de = (de)this.q.y.q(j);
            this.e.add(de.a);
            if (de.s == cr.k) {
                this.e.select(de.a);
            }
        }
        final Object w = this.q.f.w(cr.j);
        final String a = ((bp)this.q.f.w(super.q.r)).a;
        if (w != null) {
            final String a2 = ((bp)w).a;
            if (!"".equals(a2)) {
                cr.s = a2;
            }
            else {
                cr.s = a;
            }
        }
        else {
            cr.s = a;
        }
        this.w.select(cr.s);
        this.q.q(cr.w());
        this.w.q(cr.g);
    }
    
    public final boolean q(bp bp) {
        bp = bp;
        final String trim = this.q.getText().trim();
        final String trim2 = this.w.getText().trim();
        final String trim3 = this.q.getSelectedItem().trim();
        if (trim.length() == 0) {
            new dd(super.q, be.w("Note"), be.w("You must provide a name. Please re-enter this information."), super.q).setVisible(true);
            return false;
        }
        final String s = trim;
        final bp bp2 = bp;
        final String s2 = s;
        int i = 0;
        while (true) {
            while (i < this.q()) {
                final bp q = this.q(i);
                if (bp2 != q && q.a.equalsIgnoreCase(s2)) {
                    final boolean b = true;
                    if (b) {
                        new dd(super.q, be.w("Note"), B.q(be.w("There is already an virtual user named \"%1.\" Please choose another name."), new String[] { trim }), super.q).setVisible(true);
                        return false;
                    }
                    if (trim2.length() == 0) {
                        new dd(super.q, be.w("Note"), be.w("You must provide a host. Please re-enter this information."), super.q).setVisible(true);
                        return false;
                    }
                    if (trim3.length() == 0) {
                        new dd(super.q, be.w("Note"), be.w("You must provide a country. Please re-enter this information."), super.q).setVisible(true);
                        return false;
                    }
                    bp.a = trim;
                    ((cr)bp).i = trim2;
                    ((cr)bp).d = trim3;
                    try {
                        ((cr)bp).e = this.q.q().s;
                        ((cr)bp).e = ((aZ)super.q.p.w(((cr)bp).e)).q;
                    }
                    catch (Exception ex) {
                        ((cr)bp).e = 0;
                    }
                    ((cr)bp).p = this.e.getSelectedItem();
                    ((cr)bp).k = this.q.q(((cr)bp).p);
                    ((cr)bp).t = de.q(this.q.y, ((cr)bp).k);
                    final cx cx;
                    if ((cx = (cx)this.q.b.w(((cr)bp).t)) != null) {
                        ((cr)bp).r = cx.q;
                    }
                    bp.p(this.q.q());
                    bp.o(this.w.q());
                    ((cr)bp).j = ((bp)super.q.f.q(this.w.getSelectedIndex())).s;
                    ((cr)bp).s = ((bp)super.q.f.q(this.w.getSelectedIndex())).a;
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
    
    public final void q(final dK dk) {
        dk.q(be.w("Name:"), this.q, 0);
        dk.q(be.w("Host:"), this.w, 0);
        dk.q(be.w("Country:"), this.q, 0);
        dk.q(be.w("Room:"), this.w, 0);
        dk.q(be.w("Color:"), new Component[] { this.q, new Label(be.w("Background Color:")), this.w });
        dk.q(this.q, 1, 1.0f, 1.0f);
        dk.q(be.w("Group:"), this.e, 0);
    }
    
    public final void w(final bp q) {
        super.w(q);
        this.q = q;
        if (q == null) {
            this.u.e();
            this.i.e();
            this.o.e();
            this.p.e();
        }
        else {
            this.u.q();
            this.i.q();
            this.o.q();
            this.p.q();
        }
        if (this.q().size() != 0) {
            this.t.q();
            this.y.q();
            return;
        }
        this.t.e();
        this.y.e();
    }
    
    private Vector q() {
        final Vector<cr> vector = new Vector<cr>();
        for (int i = 0; i < this.q.q; ++i) {
            final cr cr;
            if ((cr = (cr)this.q.q(i)).q(0)) {
                vector.addElement(cr);
            }
        }
        return vector;
    }
    
    public final void q() {
        if (super.w) {
            final Vector<cr> vector = new Vector<cr>();
            final Vector<cr> vector2 = new Vector<cr>();
            for (int i = 0; i < this.q(); ++i) {
                final cr cr;
                if ((cr = (cr)this.q(i)).q(63)) {
                    vector2.addElement(cr);
                }
                else {
                    final cr cr2;
                    if ((cr2 = (cr)this.q.d.w(cr.s)) == null || cr.q(cr2) != 0) {
                        vector.addElement(cr);
                    }
                }
            }
            this.w(vector2);
            this.q(vector);
            super.w = false;
        }
    }
    
    private void q(final Vector vector) {
        if (vector.size() == 0) {
            return;
        }
        final dI di;
        (di = new dI(1074819073, vector.size())).w = -1;
        di.q = -1;
        for (int i = 0; i < vector.size(); ++i) {
            final cr cr = vector.elementAt(i);
            di.q(i, cr.w());
            di.q(i, 0, cr.s);
            di.q(i, 0, cr.a);
            if (!cr.q(63)) {
                di.q(i, 1, cr.e);
                di.q(i, 2, cr.j);
                di.q(i, 3, cr.t);
                di.q(i, 4, cr.w());
                di.q(i, 5, cr.g);
                di.q(i, 1, cr.i);
                di.q(i, 2, cr.d);
            }
        }
        super.q.o(di);
    }
    
    private void w(final Vector vector) {
        if (vector.size() == 0) {
            return;
        }
        final dI di;
        (di = new dI(1074819074, vector.size())).w = -1;
        di.q = -1;
        for (int i = 0; i < vector.size(); ++i) {
            final cr cr = vector.elementAt(i);
            di.q(i, cr.w());
            di.q(i, 0, cr.s);
        }
        super.q.o(di);
    }
    
    public final void w() {
        super.w();
        try {
            for (int i = 0; i < super.q.d.q; ++i) {
                final cr cr;
                final int s = (cr = (cr)super.q.d.q(i)).s;
                cr.t = (s >= 1000);
                this.e((bp)cr.clone());
                super.q.q(cr, s > 1 || (super.q.q(62) && s > 0));
                cr.p = this.q.q(cr.k);
            }
        }
        finally {}
    }
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 1001: {
                if (event.target == this.t) {
                    this.q.o(this.q(false, true));
                }
                if (event.target == this.y) {
                    this.q.o(this.q(false, false));
                }
                if (event.target == this.u) {
                    this.q.o(this.q(true, true));
                }
                if (event.target == this.i) {
                    this.q.o(this.q(true, false));
                }
                if (event.target == this.o) {
                    this.q.o(this.q(true));
                }
                if (event.target == this.p) {
                    this.q.o(this.q(false));
                    break;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    private dI q(final boolean b, final boolean b2) {
        final Vector q = this.q();
        int n;
        if (b) {
            n = this.q.q;
        }
        else {
            n = q.size();
        }
        final dI di;
        (di = new dI(4198514, n)).q(-1, 0, !b2);
        for (int i = 0; i < n; ++i) {
            bp bp;
            if (b) {
                bp = (bp)this.q.q(i);
            }
            else {
                bp = q.elementAt(i);
            }
            di.q(i, 0, bp.s);
        }
        return di;
    }
    
    private dI q(final boolean b) {
        final dI di;
        (di = new dI(4198514, 1)).q(-1, 0, !b);
        di.q(0, 0, this.q.s);
        return di;
    }
    
    public cn(final ap ap) {
        super(ap, be.w("Virtual Users"), be.w("Virtual User"));
        this.q = new TextField(30);
        this.w = new TextField(30);
        this.q = new dR();
        for (int i = 0; i < l.q.length; ++i) {
            this.q.add(l.q[i]);
        }
        this.e = new dR();
        super.q = true;
        this.w = new dR();
        for (int j = 0; j < this.q.f.q; ++j) {
            String s;
            if ((s = ((bp)this.q.f.q(j)).a).length() > 35) {
                s = s.substring(0, 35);
            }
            this.w.addItem(s);
        }
        (this.q = new aq()).q();
        this.t = new ad(80, 20);
        this.y = new ad(80, 20);
        this.u = new ad(80, 20);
        this.i = new ad(80, 20);
        this.o = new ad(80, 20);
        this.p = new ad(80, 20);
        this.t.q(be.w("Login selected"));
        this.t.t();
        this.y.q(be.w("Logout selected"));
        this.y.t();
        this.u.q(be.w("Login all"));
        this.u.t();
        this.i.q(be.w("Logout all"));
        this.i.t();
        this.o.q(be.w("Login"));
        this.o.t();
        this.p.q(be.w("Logout"));
        this.p.t();
        this.q("", new Component[] { this.o, this.p, this.t, this.y, this.u, this.i });
        super.w.w(100);
        final bV bv = new bV(be.w("Room"), "room");
        this.q(bv);
        bv.w(false);
        bv.w(100);
        final bV bv2 = new bV(be.w("Country"), "country");
        this.q(bv2);
        bv2.w(false);
        bv2.w(100);
        this.q(new bV(be.w("Group"), "group"));
        final aX ax;
        (ax = new aX(dN.w, "iconImg")).w(22);
        this.q(super.q, 0);
        super.q.e(0);
        this.q(ax, 2);
        ax.e(0);
        final aX ax2;
        (ax2 = new aX(null, "starImg")).w(22);
        this.q(ax2, 3);
        ax2.e(0);
        final bV bv3;
        (bv3 = new bV("connect")).r(true);
        bv3.w(40);
        this.q(bv3, 0);
        this.e.setVisible(true);
        this.e.e();
        this.r.setVisible(true);
        this.r.e();
    }
}
