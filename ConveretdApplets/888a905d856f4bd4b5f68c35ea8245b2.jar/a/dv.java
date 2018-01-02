// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.util.Vector;
import java.awt.Event;
import java.awt.Component;
import java.awt.Checkbox;
import java.awt.Choice;
import java.awt.TextField;

public final class dv extends G
{
    private TextField q;
    private k q;
    private k w;
    private k e;
    private k r;
    private k t;
    private aB q;
    private aB w;
    private aB e;
    private aB r;
    private aB t;
    private aB y;
    private aB u;
    private aB i;
    private k y;
    private dS e;
    private k u;
    private dS r;
    private h q;
    private Choice q;
    private Choice w;
    private int q;
    private Checkbox q;
    private Checkbox w;
    private Checkbox e;
    private Checkbox r;
    private Checkbox t;
    private Checkbox y;
    private Checkbox u;
    private TextField w;
    private Choice e;
    private Checkbox i;
    private k i;
    private k o;
    private dS t;
    private dS y;
    private dS u;
    private dS i;
    private dS o;
    private dS p;
    private dS a;
    private k p;
    private dS s;
    private k a;
    private dS d;
    private k s;
    private dS f;
    private k d;
    private dS g;
    private k f;
    private dS h;
    private k g;
    private dS j;
    private Checkbox o;
    private Checkbox p;
    private Checkbox a;
    private Checkbox s;
    private Checkbox d;
    private Checkbox f;
    private Checkbox g;
    private Checkbox h;
    private Checkbox j;
    private k h;
    private dS k;
    private k j;
    private dS l;
    private h w;
    private Choice r;
    private Choice t;
    private k k;
    private dS z;
    private k l;
    private dS x;
    private Checkbox k;
    private Checkbox l;
    private Checkbox z;
    private TextField e;
    private TextField r;
    private Checkbox x;
    private Checkbox c;
    private TextField t;
    
    public final bp q() {
        return new Z(-999, "");
    }
    
    public final void q(final dK dk) {
        dk.q(be.w("Name:"), this.q, 0);
        dk.q(be.w("Directory:"), this.w, 0);
        dk.q(be.w("Outer Background Color (rgb):"), this.q, this.u, 0);
        dk.q(be.w("Inner Background Color (rgb):"), this.w, this.i, 0);
        dk.q(be.w("Help Panel Text Color:"), this.w, 0);
        dk.q(be.w("Help Panel Background Color (rgb):"), this.e, this.o, 0);
        dk.q(be.w("Input Panel Text Color:"), this.q, 0);
        dk.q(be.w("Input Panel Background Color (rgb):"), this.r, this.p, 0);
        dk.q(be.w("Tabbed Panel Text Color:"), this.e, 0);
        dk.q(be.w("Tabbed Panel Background Color (rgb):"), this.t, this.a, 0);
        dk.q(be.w("Normal Message Text Color:"), this.r, 0);
        dk.q(be.w("Flagged Message Text Panel Color:"), this.t, 0);
        dk.q(be.w("Normal/Flagged Message Background Color:"), this.u, 0);
        dk.q(be.w("Private Message Text Color:"), this.y, 0);
        dk.q(be.w("Private Message Background Color:"), this.i, 0);
        dk.q(be.w("Conference Message Background Color:"), this.y, this.e, 0);
        dk.q(be.w("Security Background Color:"), this.u, this.r, 0);
        dk.q(be.w("Font:"), this.q, 1);
        dk.q(be.w("Font Style:"), this.w, 1);
        dk.q(be.w("Font Size:"), this.q, 1);
        dk.q(be.w("Use rounded corners:"), this.e, 1);
        dk.q(be.w("Use background image:"), this.q, 1);
        dk.q(be.w("Use chat background image:"), this.w, 1);
        dk.q(be.w("Use chat logo image:"), this.e, 1);
        dk.q(be.w("Use chat help image:"), this.r, 1);
        dk.q(be.w("Scale chat background image:"), this.t, 1);
        dk.q(be.w("Use image buttons:"), this.y, 1);
        dk.q(be.w("Use image tabs:"), this.u, 1);
        dk.q(be.w("Use 3D buttons:"), this.i, 1);
        dk.q(be.w("3D Buttons Color (rgb):"), this.i, this.t, 1);
        dk.q(be.w("3D Buttons Text Color (rgb):"), this.o, this.y, 1);
        dk.q(be.w("Search Box Background Color:"), this.k, this.z, 1);
        dk.q(be.w("Select Pointer Color:"), this.l, this.x, 1);
        dk.q(be.w("Is one column menu:"), this.s, 2);
        if (!dN.i) {
            dk.q(be.w("Is clicked menu:"), this.d, 2);
            dk.q(be.w("Menu background color:"), this.p, this.s, 2);
            dk.q(be.w("Menu selected background color:"), this.a, this.d, 2);
            dk.q(be.w("Menu selected text color:"), this.s, this.f, 2);
            dk.q(be.w("Menu item background color:"), this.d, this.g, 2);
            dk.q(be.w("Menu item selected background color:"), this.f, this.h, 2);
            dk.q(be.w("Menu item selected text color:"), this.g, this.j, 2);
            dk.q(be.w("Is menu 3d:"), this.o, 2);
            dk.q(be.w("Is menu animated:"), this.p, 2);
        }
        dk.q(be.w("Scroll Font:"), this.w, 2);
        dk.q(be.w("Scroll Font Style:"), this.t, 2);
        dk.q(be.w("Scroll Font Size:"), this.r, 2);
        dk.q(be.w("Scroll background color:"), this.j, this.l, 2);
        dk.q(be.w("Scroll text color:"), this.h, this.k, 2);
        dk.q(be.w("Is context menu disabled:"), this.h, this.f, 3);
        dk.q(be.w("Is copy menu disabled:"), this.j, this.g, 3);
        dk.q(be.w("Hide conference:"), this.l, this.k, 3);
        dk.q(be.w("Chat window width:"), this.e, 3);
        dk.q(be.w("Chat window height:"), this.r, 3);
        dk.q(be.w("Tabs panel size:"), this.t, 3);
        dk.q(be.w("Use new BIC panel:"), this.z, 3);
        dk.q(be.w("Hide Games menu:"), this.x, 3);
        dk.q(be.w("Hide View Offline Messages menu:"), this.c, 3);
    }
    
    public final boolean q(final Event event) {
        switch (event.id) {
            case 1001: {
                if (event.target == this.a) {
                    this.q(!this.a.getState());
                    return true;
                }
                break;
            }
        }
        return false;
    }
    
    private void q(final boolean enabled) {
        this.d.setEnabled(enabled);
        this.p.setEnabled(enabled);
        this.s.setEnabled(enabled);
        this.a.setEnabled(enabled);
        this.d.setEnabled(enabled);
        this.s.setEnabled(enabled);
        this.f.setEnabled(enabled);
        this.d.setEnabled(enabled);
        this.g.setEnabled(enabled);
        this.f.setEnabled(enabled);
        this.h.setEnabled(enabled);
        this.g.setEnabled(enabled);
        this.j.setEnabled(enabled);
        this.o.setEnabled(enabled);
        this.p.setEnabled(enabled);
    }
    
    public final boolean q(final bp bp) {
        final Z z;
        (z = (Z)bp).a = this.q.getText();
        z.q = ds.q(this.q.getText());
        z.w = ds.q(this.w.getText());
        z.r = ds.q(this.e.getText());
        z.y = ds.q(this.r.getText());
        z.i = ds.q(this.t.getText());
        z.h = ds.q(this.i.getText());
        z.j = ds.q(this.o.getText());
        z.e = this.w.q();
        z.t = this.q.q();
        z.u = this.e.q();
        z.o = this.r.q();
        z.p = this.t.q();
        z.s = this.y.q();
        z.a = this.u.q();
        z.d = this.i.q();
        z.f = ds.q(this.y.getText());
        z.g = ds.q(this.u.getText());
        z.q = new String(this.q.getSelectedItem());
        z.w = this.w.getSelectedIndex();
        z.q = Integer.parseInt(this.q.getSelectedItem());
        z.q(this.w.getText());
        z.q(this.q.getState());
        z.w(this.w.getState());
        z.e(this.e.getState());
        z.r(this.r.getState());
        z.t(this.t.getState());
        z.y(this.y.getState());
        z.u(this.u.getState());
        z.i(this.i.getState());
        String selectedItem = this.e.getSelectedItem();
        if ("None".equals(selectedItem)) {
            selectedItem = "0";
        }
        z.e = Integer.parseInt(selectedItem);
        z.k = ds.q(this.p.getText());
        z.l = ds.q(this.a.getText());
        z.z = ds.q(this.s.getText());
        z.x = ds.q(this.d.getText());
        z.c = ds.q(this.f.getText());
        z.v = ds.q(this.g.getText());
        z.q(53, this.o.getState());
        z.q(52, this.p.getState());
        z.q(41, !this.a.getState());
        z.q(51, this.s.getState());
        z.q(50, this.d.getState());
        z.q(49, this.h.getState());
        z.q(48, this.j.getState());
        z.q(47, this.f.getState());
        z.q(46, this.g.getState());
        z.w = this.w.getSelectedItem();
        z.r = Integer.parseInt(this.r.getSelectedItem());
        z.t = this.t.getSelectedIndex();
        z.n = ds.q(this.j.getText());
        z.b = ds.q(this.h.getText());
        z.q(44, this.l.getState());
        z.q(45, this.k.getState());
        z.m = ds.q(this.k.getText());
        z.Q = ds.q(this.l.getText());
        z.y = Math.max(Integer.parseInt(this.t.getText()), 100);
        z.q(40, this.z.getState());
        z.q(39, this.x.getState());
        z.q(38, this.c.getState());
        final String text = this.e.getText();
        final String text2 = this.r.getText();
        int int1;
        try {
            if ((int1 = Integer.parseInt(text)) > 1024 || (int1 < 400 && int1 != 0)) {
                throw new NumberFormatException();
            }
        }
        catch (NumberFormatException ex) {
            this.e.requestFocus();
            this.e.selectAll();
            new dd(super.q, be.w("Note"), be.w("The size for chat window width you entered is not valid.  Please re-enter this information."), super.q).setVisible(true);
            return false;
        }
        int int2;
        try {
            if ((int2 = Integer.parseInt(text2)) > 768 || (int2 < 300 && int2 != 0)) {
                throw new NumberFormatException();
            }
        }
        catch (NumberFormatException ex2) {
            this.r.requestFocus();
            this.r.selectAll();
            new dd(super.q, be.w("Note"), be.w("The size for chat window height you entered is not valid.  Please re-enter this information."), super.q).setVisible(true);
            return false;
        }
        z.q(int1);
        z.w(int2);
        return true;
    }
    
    public final void q(final bp bp) {
        final Z z = (Z)bp;
        this.q.setText(z.a);
        this.q.setText(ds.q(z.q));
        this.w.setText(ds.q(z.w));
        this.e.setText(ds.q(z.r));
        this.r.setText(ds.q(z.y));
        this.t.setText(ds.q(z.i));
        this.w.q(z.e);
        this.q.q(z.t);
        this.e.q(z.u);
        this.r.q(z.o);
        this.t.q(z.p);
        this.y.q(z.s);
        this.u.q(z.a);
        this.i.q(z.d);
        this.y.setText(ds.q(z.f));
        this.u.setText(ds.q(z.g));
        this.q.select(z.q);
        this.q.select(String.valueOf(z.q));
        this.w.select(z.w);
        this.w.setText(z.e());
        this.q.setState(z.g());
        this.w.setState(z.h());
        this.e.setState(z.j());
        this.r.setState(z.k());
        this.t.setState(z.l());
        this.y.setState(z.z());
        this.u.setState(z.x());
        this.i.setState(z.c());
        this.i.setText(ds.q(z.h));
        this.o.setText(ds.q(z.j));
        if (z.e == 0) {
            this.e.select("None");
        }
        else {
            this.e.select(z.e + "");
        }
        this.u.setBackground(ds.q(this.q.getText()));
        this.i.setBackground(ds.q(this.w.getText()));
        this.o.setBackground(ds.q(this.e.getText()));
        this.p.setBackground(ds.q(this.r.getText()));
        this.a.setBackground(ds.q(this.t.getText()));
        this.t.setBackground(ds.q(this.i.getText()));
        this.y.setBackground(ds.q(this.o.getText()));
        this.e.setBackground(ds.q(this.y.getText()));
        this.r.setBackground(ds.q(this.u.getText()));
        this.p.setText(ds.q(z.k));
        this.s.q(z.k);
        this.a.setText(ds.q(z.l));
        this.d.q(z.l);
        this.s.setText(ds.q(z.z));
        this.f.q(z.z);
        this.d.setText(ds.q(z.x));
        this.g.q(z.x);
        this.f.setText(ds.q(z.c));
        this.h.q(z.c);
        this.g.setText(ds.q(z.v));
        this.j.q(z.v);
        this.o.setState(z.q());
        this.p.setState(z.w());
        this.a.setState(bC.e());
        this.s.setState(z.r());
        this.d.setState(z.t());
        this.h.setState(z.y());
        this.j.setState(z.u());
        this.f.setState(z.i());
        this.g.setState(z.o());
        this.w.select((z.w != null) ? z.w : z.q);
        this.r.select(String.valueOf(z.r));
        this.t.select(z.t);
        this.h.setText(ds.q(z.b));
        this.k.q(z.b);
        this.j.setText(ds.q(z.n));
        this.l.q(z.n);
        this.k.setText(ds.q(z.m));
        this.z.q(z.m);
        this.l.setText(ds.q(z.Q));
        this.x.q(z.Q);
        this.l.setState(z.p());
        this.k.setState(z.a());
        this.t.setText(z.y + "");
        this.z.setState(z.s());
        this.x.setState(z.d());
        this.c.setState(z.f());
        this.e.setText(z.r() + "");
        this.r.setText(z.t() + "");
        this.q(!this.a.getState());
    }
    
    public final void q() {
        Label_0069: {
            if (!super.w) {
                int i = 0;
                while (true) {
                    while (i < this.q()) {
                        final bC bc;
                        if ((bc = (bC)this.q(i)).q(62)) {
                            final boolean b = bc.s != this.q;
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
        final Vector<bC> vector = new Vector<bC>();
        final Vector<bC> vector2 = new Vector<bC>();
        for (int j = 0; j < this.q(); ++j) {
            final bC bc2;
            if ((bc2 = (bC)this.q(j)).q(63)) {
                vector2.addElement(bc2);
            }
            else {
                final bC bc3;
                if ((bc3 = (bC)this.q.k.w(bc2.s)) == null || bc2.q(bc3) != 0) {
                    vector.addElement(bc2);
                }
            }
        }
        this.w(vector2);
        this.q(vector);
        super.w = false;
        new cp(this).start();
    }
    
    private void q(final Vector vector) {
        if (vector.size() == 0) {
            return;
        }
        final dI di;
        (di = new dI(17239297, vector.size())).w = -1;
        di.q = -1;
        for (int i = 0; i < vector.size(); ++i) {
            final bC bc = vector.elementAt(i);
            di.q(i, bc.w());
            di.q(i, 0, bc.s);
            di.q(i, 1, bc.q.getRGB());
            di.q(i, 2, bc.w.getRGB());
            di.q(i, 3, bc.e.getRGB());
            di.q(i, 4, bc.r.getRGB());
            di.q(i, 5, bc.u.getRGB());
            di.q(i, 6, bc.i.getRGB());
            di.q(i, 7, bc.o.getRGB());
            di.q(i, 8, bc.p.getRGB());
            di.q(i, 9, bc.a.getRGB());
            di.q(i, 10, bc.s.getRGB());
            di.q(i, 11, bc.d.getRGB());
            di.q(i, 12, bc.w);
            di.q(i, 13, bc.q);
            di.q(i, 14, bc.e);
            di.q(i, 15, bc.t.getRGB());
            di.q(i, 16, bc.y.getRGB());
            di.q(i, 17, bc.h.getRGB());
            di.q(i, 18, bc.j.getRGB());
            di.q(i, 19, bc.k.getRGB());
            di.q(i, 20, bc.l.getRGB());
            di.q(i, 21, bc.z.getRGB());
            di.q(i, 22, bc.x.getRGB());
            di.q(i, 23, bc.c.getRGB());
            di.q(i, 24, bc.v.getRGB());
            di.q(i, 25, bc.b.getRGB());
            di.q(i, 26, bc.n.getRGB());
            di.q(i, 27, bc.r);
            di.q(i, 28, bc.t);
            di.q(i, 29, bc.m.getRGB());
            di.q(i, 30, bc.Q.getRGB());
            di.q(i, 31, bc.f.getRGB());
            di.q(i, 32, bc.y);
            di.q(i, 33, bc.g.getRGB());
            di.q(i, 34, bc.r());
            di.q(i, 35, bc.t());
            di.q(i, 0, bc.a);
            di.q(i, 1, bc.q);
            di.q(i, 2, bc.e());
            di.q(i, 3, bc.w);
            di.q(i, bc.w());
        }
        super.q.o(di);
    }
    
    private void w(final Vector vector) {
        if (vector.size() == 0) {
            return;
        }
        final dI di;
        (di = new dI(17239298, vector.size())).w = -1;
        di.q = -1;
        for (int i = 0; i < vector.size(); ++i) {
            final bC bc = vector.elementAt(i);
            di.q(i, bc.w());
            di.q(i, 0, bc.s);
        }
        super.q.o(di);
    }
    
    public final void w() {
        super.w();
        try {
            for (int i = 0; i < super.q.k.q; ++i) {
                final Z z;
                if ((z = new Z((bC)super.q.k.q(i))).q(62)) {
                    this.q = z.s;
                }
                this.e(z);
            }
        }
        finally {}
    }
    
    dv(final ap ap) {
        super(ap, be.w("Themes"), be.w("Theme"));
        this.q = new TextField(20);
        this.q = new k(8);
        this.w = new k(8);
        this.e = new k(8);
        this.r = new k(8);
        this.t = new k(8);
        this.q = new aB();
        this.w = new aB();
        this.e = new aB();
        this.r = new aB();
        this.t = new aB();
        this.y = new aB();
        this.u = new aB();
        this.i = new aB();
        this.y = new k(8);
        this.u = new k(8);
        this.q = new h();
        this.q = new dR();
        this.w = new dR();
        this.q = new Checkbox();
        this.w = new Checkbox();
        this.e = new Checkbox();
        this.r = new Checkbox();
        this.t = new Checkbox();
        this.y = new Checkbox();
        this.u = new Checkbox();
        this.w = new TextField(20);
        this.e = new dR();
        this.i = new Checkbox();
        this.i = new k(8);
        this.o = new k(8);
        this.p = new k(8);
        this.s = new dS(this.p);
        this.a = new k(8);
        this.d = new dS(this.a);
        this.s = new k(8);
        this.f = new dS(this.s);
        this.d = new k(8);
        this.g = new dS(this.d);
        this.f = new k(8);
        this.h = new dS(this.f);
        this.g = new k(8);
        this.j = new dS(this.g);
        this.o = new Checkbox();
        this.p = new Checkbox();
        this.a = new Checkbox();
        this.s = new Checkbox();
        this.d = new Checkbox();
        this.f = new Checkbox(be.w("Guest"));
        this.g = new Checkbox(be.w("Guest"));
        this.h = new Checkbox(be.w("Master"));
        this.j = new Checkbox(be.w("Master"));
        this.h = new k(8);
        this.k = new dS(this.h);
        this.j = new k(8);
        this.l = new dS(this.j);
        this.w = new h();
        this.r = new dR();
        this.t = new dR();
        this.k = new k(8);
        this.z = new dS(this.k);
        this.l = new k(8);
        this.x = new dS(this.l);
        this.k = new Checkbox(be.w("Guest"));
        this.l = new Checkbox(be.w("Master"));
        this.z = new Checkbox();
        this.e = new TextField(5);
        this.r = new TextField(5);
        this.x = new Checkbox();
        this.c = new Checkbox();
        this.q(super.q, 0);
        this.q.addItem("9");
        this.q.addItem("10");
        this.q.addItem("12");
        this.q.addItem("14");
        this.q.addItem("16");
        this.q.addItem("18");
        this.q.addItem("20");
        this.q.addItem("22");
        this.q.addItem("24");
        this.w.addItem(be.w("Plain"));
        this.w.addItem(be.w("Bold"));
        this.w.addItem(be.w("Italic"));
        this.w.addItem(be.w("Bold Italic"));
        this.e.addItem("None");
        this.e.addItem("2");
        this.e.addItem("4");
        this.e.addItem("6");
        this.e.addItem("8");
        this.e.addItem("10");
        this.e.addItem("12");
        this.e.addItem("14");
        this.e.addItem("16");
        this.e.addItem("18");
        this.e.addItem("20");
        this.e.addItem("22");
        this.e.addItem("24");
        super.q = new String[] { be.w("Colors"), be.w("Fonts/Images"), be.w("Menu/Scroll"), be.w("Components") };
        this.u = new dS(this.q);
        this.i = new dS(this.w);
        this.o = new dS(this.e);
        this.p = new dS(this.r);
        this.a = new dS(this.t);
        this.e = new dS(this.y);
        this.r = new dS(this.u);
        this.q.q = this.u;
        this.w.q = this.i;
        this.e.q = this.o;
        this.r.q = this.p;
        this.t.q = this.a;
        this.y.q = this.e;
        this.u.q = this.r;
        this.t = new dS(this.i);
        this.y = new dS(this.o);
        this.i.q = this.t;
        this.o.q = this.y;
        this.p.q = this.s;
        this.a.q = this.d;
        this.s.q = this.f;
        this.d.q = this.g;
        this.f.q = this.h;
        this.g.q = this.j;
        this.r.addItem("9");
        this.r.addItem("10");
        this.r.addItem("12");
        this.r.addItem("14");
        this.r.addItem("16");
        this.r.addItem("18");
        this.r.addItem("20");
        this.r.addItem("22");
        this.r.addItem("24");
        this.t.addItem(be.w("Plain"));
        this.t.addItem(be.w("Bold"));
        this.t.addItem(be.w("Italic"));
        this.t.addItem(be.w("Bold Italic"));
        this.j.q = this.l;
        this.h.q = this.k;
        this.p.setState(true);
        this.k.q = this.z;
        this.l.q = this.x;
        this.t = new TextField(5);
    }
}
