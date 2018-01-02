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

public final class bQ extends bs
{
    private TextField q;
    private c q;
    private c w;
    private c e;
    private c r;
    private c t;
    private k q;
    private k w;
    private k e;
    private k r;
    private k t;
    private k y;
    private k u;
    private k i;
    private c y;
    private j e;
    private c u;
    private j r;
    private i q;
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
    private c i;
    private c o;
    private j t;
    private j y;
    private j u;
    private j i;
    private j o;
    private j p;
    private j a;
    private c p;
    private j s;
    private c a;
    private j d;
    private c s;
    private j f;
    private c d;
    private j g;
    private c f;
    private j h;
    private c g;
    private j j;
    private Checkbox o;
    private Checkbox p;
    private Checkbox a;
    private Checkbox s;
    private Checkbox d;
    private Checkbox f;
    private Checkbox g;
    private Checkbox h;
    private Checkbox j;
    private c h;
    private j k;
    private c j;
    private j l;
    private i w;
    private Choice r;
    private Choice t;
    private c k;
    private j z;
    private c l;
    private j x;
    private Checkbox k;
    private TextField e;
    private TextField r;
    private Checkbox l;
    private Checkbox z;
    private TextField t;
    
    public final bZ q() {
        return new cg(-999, "");
    }
    
    public final void q(final bw bw) {
        bw.q(eb.q("Name:"), this.q);
        bw.q(eb.q("Directory:"), this.w);
        bw.q(eb.q("Outer Background Color (rgb):"), this.q, this.u, 0);
        bw.q(eb.q("Inner Background Color (rgb):"), this.w, this.i, 0);
        bw.q(eb.q("Help Panel Text Color:"), this.w);
        bw.q(eb.q("Help Panel Background Color (rgb):"), this.e, this.o, 0);
        bw.q(eb.q("Input Panel Text Color:"), this.q);
        bw.q(eb.q("Input Panel Background Color (rgb):"), this.r, this.p, 0);
        bw.q(eb.q("Tabbed Panel Text Color:"), this.e);
        bw.q(eb.q("Tabbed Panel Background Color (rgb):"), this.t, this.a, 0);
        bw.q(eb.q("Normal Message Text Color:"), this.r);
        bw.q(eb.q("Flagged Message Text Panel Color:"), this.t);
        bw.q(eb.q("Normal/Flagged Message Background Color:"), this.u);
        bw.q(eb.q("Private Message Text Color:"), this.y);
        bw.q(eb.q("Private Message Background Color:"), this.i);
        bw.q(eb.q("Conference Message Background Color:"), this.y, this.e, 0);
        bw.q(eb.q("Security Background Color:"), this.u, this.r, 0);
        bw.q(eb.q("Font:"), this.q, 1);
        bw.q(eb.q("Font Style:"), this.w, 1);
        bw.q(eb.q("Font Size:"), this.q, 1);
        bw.q(eb.q("Use rounded corners:"), this.e, 1);
        bw.q(eb.q("Use background image:"), this.q, 1);
        bw.q(eb.q("Use chat background image:"), this.w, 1);
        bw.q(eb.q("Use chat logo image:"), this.e, 1);
        bw.q(eb.q("Use chat help image:"), this.r, 1);
        bw.q(eb.q("Scale chat background image:"), this.t, 1);
        bw.q(eb.q("Use image buttons:"), this.y, 1);
        bw.q(eb.q("Use image tabs:"), this.u, 1);
        bw.q(eb.q("Use 3D buttons:"), this.i, 1);
        bw.q(eb.q("3D Buttons Color (rgb):"), this.i, this.t, 1);
        bw.q(eb.q("3D Buttons Text Color (rgb):"), this.o, this.y, 1);
        bw.q(eb.q("Search Box Background Color:"), this.k, this.z, 1);
        bw.q(eb.q("Select Pointer Color:"), this.l, this.x, 1);
        bw.q(eb.q("Is one column menu:"), this.s, 2);
        if (!a.a.y) {
            bw.q(eb.q("Is clicked menu:"), this.d, 2);
            bw.q(eb.q("Menu background color:"), this.p, this.s, 2);
            bw.q(eb.q("Menu selected background color:"), this.a, this.d, 2);
            bw.q(eb.q("Menu selected text color:"), this.s, this.f, 2);
            bw.q(eb.q("Menu item background color:"), this.d, this.g, 2);
            bw.q(eb.q("Menu item selected background color:"), this.f, this.h, 2);
            bw.q(eb.q("Menu item selected text color:"), this.g, this.j, 2);
            bw.q(eb.q("Is menu 3d:"), this.o, 2);
            bw.q(eb.q("Is menu animated:"), this.p, 2);
        }
        bw.q(eb.q("Scroll Font:"), this.w, 2);
        bw.q(eb.q("Scroll Font Style:"), this.t, 2);
        bw.q(eb.q("Scroll Font Size:"), this.r, 2);
        bw.q(eb.q("Scroll background color:"), this.j, this.l, 2);
        bw.q(eb.q("Scroll text color:"), this.h, this.k, 2);
        bw.q(eb.q("Is context menu disabled:"), this.h, this.f, 3);
        bw.q(eb.q("Is copy menu disabled:"), this.j, this.g, 3);
        bw.q(eb.q("Chat window width:"), this.e, 3);
        bw.q(eb.q("Chat window height:"), this.r, 3);
        bw.q(eb.q("Tabs panel size:"), this.t, 3);
        bw.q(eb.q("Use new BIC panel:"), this.k, 3);
        bw.q(eb.q("Hide Games menu:"), this.l, 3);
        bw.q(eb.q("Hide View Offline Messages menu:"), this.z, 3);
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
    
    public final boolean q(final bZ bz) {
        final cg cg;
        (cg = (cg)bz).a_(this.q.getText());
        cg.q = dV.q(this.q.getText());
        cg.w = dV.q(this.w.getText());
        cg.r = dV.q(this.e.getText());
        cg.y = dV.q(this.r.getText());
        cg.i = dV.q(this.t.getText());
        cg.h = dV.q(this.i.getText());
        cg.j = dV.q(this.o.getText());
        cg.e = this.w.q();
        cg.t = this.q.q();
        cg.u = this.e.q();
        cg.o = this.r.q();
        cg.p = this.t.q();
        cg.s = this.y.q();
        cg.a = this.u.q();
        cg.d = this.i.q();
        cg.f = dV.q(this.y.getText());
        cg.g = dV.q(this.u.getText());
        cg.q = new String(this.q.getSelectedItem());
        cg.w = this.w.getSelectedIndex();
        cg.q = Integer.parseInt(this.q.getSelectedItem());
        cg.w(this.w.getText());
        cg.q(this.q.getState());
        cg.w(this.w.getState());
        cg.e(this.e.getState());
        cg.r(this.r.getState());
        cg.t(this.t.getState());
        cg.y(this.y.getState());
        cg.u(this.u.getState());
        cg.i(this.i.getState());
        String selectedItem = this.e.getSelectedItem();
        if ("None".equals(selectedItem)) {
            selectedItem = "0";
        }
        cg.e = Integer.parseInt(selectedItem);
        cg.k = dV.q(this.p.getText());
        cg.l = dV.q(this.a.getText());
        cg.z = dV.q(this.s.getText());
        cg.x = dV.q(this.d.getText());
        cg.c = dV.q(this.f.getText());
        cg.v = dV.q(this.g.getText());
        cg.q(53, this.o.getState());
        cg.q(52, this.p.getState());
        cg.q(41, !this.a.getState());
        cg.q(51, this.s.getState());
        cg.q(50, this.d.getState());
        cg.q(49, this.h.getState());
        cg.q(48, this.j.getState());
        cg.q(47, this.f.getState());
        cg.q(46, this.g.getState());
        cg.w = this.w.getSelectedItem();
        cg.o = Integer.parseInt(this.r.getSelectedItem());
        cg.p = this.t.getSelectedIndex();
        cg.n = dV.q(this.j.getText());
        cg.b = dV.q(this.h.getText());
        cg.m = dV.q(this.k.getText());
        cg.Q = dV.q(this.l.getText());
        cg.a = Math.max(Integer.parseInt(this.t.getText()), 100);
        cg.q(40, this.k.getState());
        cg.q(39, this.l.getState());
        cg.q(38, this.z.getState());
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
            new b(super.q, eb.q("Note"), eb.q("The size for chat window width you entered is not valid.  Please re-enter this information."), super.q).setVisible(true);
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
            new b(super.q, eb.q("Note"), eb.q("The size for chat window height you entered is not valid.  Please re-enter this information."), super.q).setVisible(true);
            return false;
        }
        cg.q(int1);
        cg.w(int2);
        return true;
    }
    
    public final void q(final bZ bz) {
        final cg cg = (cg)bz;
        this.q.setText(cg.getName());
        this.q.setText(dV.q(cg.q));
        this.w.setText(dV.q(cg.w));
        this.e.setText(dV.q(cg.r));
        this.r.setText(dV.q(cg.y));
        this.t.setText(dV.q(cg.i));
        this.w.q(cg.e);
        this.q.q(cg.t);
        this.e.q(cg.u);
        this.r.q(cg.o);
        this.t.q(cg.p);
        this.y.q(cg.s);
        this.u.q(cg.a);
        this.i.q(cg.d);
        this.y.setText(dV.q(cg.f));
        this.u.setText(dV.q(cg.g));
        this.q.select(cg.q);
        this.q.select(String.valueOf(cg.q));
        this.w.select(cg.w);
        this.w.setText(cg.w());
        this.q.setState(cg.d());
        this.w.setState(cg.f());
        this.e.setState(cg.g());
        this.r.setState(cg.h());
        this.t.setState(cg.j());
        this.y.setState(cg.k());
        this.u.setState(cg.l());
        this.i.setState(cg.z());
        this.i.setText(dV.q(cg.h));
        this.o.setText(dV.q(cg.j));
        if (cg.e == 0) {
            this.e.select("None");
        }
        else {
            this.e.select(cg.e + "");
        }
        this.u.setBackground(dV.q(this.q.getText()));
        this.i.setBackground(dV.q(this.w.getText()));
        this.o.setBackground(dV.q(this.e.getText()));
        this.p.setBackground(dV.q(this.r.getText()));
        this.a.setBackground(dV.q(this.t.getText()));
        this.t.setBackground(dV.q(this.i.getText()));
        this.y.setBackground(dV.q(this.o.getText()));
        this.e.setBackground(dV.q(this.y.getText()));
        this.r.setBackground(dV.q(this.u.getText()));
        this.p.setText(dV.q(cg.k));
        this.s.q(cg.k);
        this.a.setText(dV.q(cg.l));
        this.d.q(cg.l);
        this.s.setText(dV.q(cg.z));
        this.f.q(cg.z);
        this.d.setText(dV.q(cg.x));
        this.g.q(cg.x);
        this.f.setText(dV.q(cg.c));
        this.h.q(cg.c);
        this.g.setText(dV.q(cg.v));
        this.j.q(cg.v);
        this.o.setState(cg.q());
        this.p.setState(cg.w());
        this.a.setState(cf.e());
        this.s.setState(cg.r());
        this.d.setState(cg.t());
        this.h.setState(cg.y());
        this.j.setState(cg.u());
        this.f.setState(cg.i());
        this.g.setState(cg.o());
        this.w.select((cg.w != null) ? cg.w : cg.q);
        this.r.select(String.valueOf(cg.o));
        this.t.select(cg.p);
        this.h.setText(dV.q(cg.b));
        this.k.q(cg.b);
        this.j.setText(dV.q(cg.n));
        this.l.q(cg.n);
        this.k.setText(dV.q(cg.m));
        this.z.q(cg.m);
        this.l.setText(dV.q(cg.Q));
        this.x.q(cg.Q);
        this.t.setText(cg.a + "");
        this.k.setState(cg.p());
        this.l.setState(cg.a());
        this.z.setState(cg.s());
        this.e.setText(cg.w() + "");
        this.r.setText(cg.e() + "");
        this.q(!this.a.getState());
    }
    
    public final void q() {
        Label_0066: {
            if (!super.q) {
                int i = 0;
                while (true) {
                    while (i < this.q()) {
                        final cf cf;
                        if ((cf = (cf)this.q(i)).q(62)) {
                            final boolean b = cf.q() != this.q;
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
        final Vector<cf> vector = new Vector<cf>();
        final Vector<cf> vector2 = new Vector<cf>();
        for (int j = 0; j < this.q(); ++j) {
            final cf cf2;
            if ((cf2 = (cf)this.q(j)).q(63)) {
                vector2.addElement(cf2);
            }
            else {
                final cf cf3;
                if ((cf3 = (cf)this.q.a.w(cf2.q())) == null || cf2.q(cf3) != 0) {
                    vector.addElement(cf2);
                }
            }
        }
        this.w(vector2);
        this.q(vector);
        super.q = false;
        new bR(this).start();
    }
    
    private void q(final Vector vector) {
        if (vector.size() == 0) {
            return;
        }
        final es es;
        (es = new es(17239297, vector.size())).w = -1;
        es.q = -1;
        for (int i = 0; i < vector.size(); ++i) {
            final cf cf = vector.elementAt(i);
            es.q(i, cf.q());
            es.q(i, 0, cf.q());
            es.q(i, 1, cf.q.getRGB());
            es.q(i, 2, cf.w.getRGB());
            es.q(i, 3, cf.e.getRGB());
            es.q(i, 4, cf.r.getRGB());
            es.q(i, 5, cf.u.getRGB());
            es.q(i, 6, cf.i.getRGB());
            es.q(i, 7, cf.o.getRGB());
            es.q(i, 8, cf.p.getRGB());
            es.q(i, 9, cf.a.getRGB());
            es.q(i, 10, cf.s.getRGB());
            es.q(i, 11, cf.d.getRGB());
            es.q(i, 12, cf.w);
            es.q(i, 13, cf.q);
            es.q(i, 14, cf.e);
            es.q(i, 15, cf.t.getRGB());
            es.q(i, 16, cf.y.getRGB());
            es.q(i, 17, cf.h.getRGB());
            es.q(i, 18, cf.j.getRGB());
            es.q(i, 19, cf.k.getRGB());
            es.q(i, 20, cf.l.getRGB());
            es.q(i, 21, cf.z.getRGB());
            es.q(i, 22, cf.x.getRGB());
            es.q(i, 23, cf.c.getRGB());
            es.q(i, 24, cf.v.getRGB());
            es.q(i, 25, cf.b.getRGB());
            es.q(i, 26, cf.n.getRGB());
            es.q(i, 27, cf.o);
            es.q(i, 28, cf.p);
            es.q(i, 29, cf.m.getRGB());
            es.q(i, 30, cf.Q.getRGB());
            es.q(i, 31, cf.f.getRGB());
            es.q(i, 32, cf.a);
            es.q(i, 33, cf.g.getRGB());
            es.q(i, 34, cf.w());
            es.q(i, 35, cf.e());
            es.q(i, 0, cf.getName());
            es.q(i, 1, cf.q);
            es.q(i, 2, cf.w());
            es.q(i, 3, cf.w);
            es.q(i, cf.q());
        }
        super.q.q(es);
    }
    
    private void w(final Vector vector) {
        if (vector.size() == 0) {
            return;
        }
        final es es;
        (es = new es(17239298, vector.size())).w = -1;
        es.q = -1;
        for (int i = 0; i < vector.size(); ++i) {
            final cf cf = vector.elementAt(i);
            es.q(i, cf.q());
            es.q(i, 0, cf.q());
        }
        super.q.q(es);
    }
    
    public final void w() {
        super.w();
        final dW a = super.q.a;
        dW.q();
        try {
            for (int i = 0; i < super.q.a.q(); ++i) {
                final cg cg;
                if ((cg = new cg((cf)super.q.a.q(i))).q(62)) {
                    this.q = cg.q();
                }
                this.e(cg);
            }
        }
        finally {
            final dW a2 = super.q.a;
            dW.w();
        }
    }
    
    bQ(final cV cv) {
        super(cv, eb.q("Themes"), eb.q("Theme"));
        this.q = new TextField(20);
        this.q = new c(8);
        this.w = new c(8);
        this.e = new c(8);
        this.r = new c(8);
        this.t = new c(8);
        this.q = new k();
        this.w = new k();
        this.e = new k();
        this.r = new k();
        this.t = new k();
        this.y = new k();
        this.u = new k();
        this.i = new k();
        this.y = new c(8);
        this.u = new c(8);
        this.q = new i();
        this.q = new h();
        this.w = new h();
        this.q = new Checkbox();
        this.w = new Checkbox();
        this.e = new Checkbox();
        this.r = new Checkbox();
        this.t = new Checkbox();
        this.y = new Checkbox();
        this.u = new Checkbox();
        this.w = new TextField(20);
        this.e = new h();
        this.i = new Checkbox();
        this.i = new c(8);
        this.o = new c(8);
        this.p = new c(8);
        this.s = new j(this.p);
        this.a = new c(8);
        this.d = new j(this.a);
        this.s = new c(8);
        this.f = new j(this.s);
        this.d = new c(8);
        this.g = new j(this.d);
        this.f = new c(8);
        this.h = new j(this.f);
        this.g = new c(8);
        this.j = new j(this.g);
        this.o = new Checkbox();
        this.p = new Checkbox();
        this.a = new Checkbox();
        this.s = new Checkbox();
        this.d = new Checkbox();
        this.f = new Checkbox(eb.q("Guest"));
        this.g = new Checkbox(eb.q("Guest"));
        this.h = new Checkbox(eb.q("Master"));
        this.j = new Checkbox(eb.q("Master"));
        this.h = new c(8);
        this.k = new j(this.h);
        this.j = new c(8);
        this.l = new j(this.j);
        this.w = new i();
        this.r = new h();
        this.t = new h();
        this.k = new c(8);
        this.z = new j(this.k);
        this.l = new c(8);
        this.x = new j(this.l);
        this.k = new Checkbox();
        this.e = new TextField(5);
        this.r = new TextField(5);
        this.l = new Checkbox();
        this.z = new Checkbox();
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
        this.w.addItem(eb.q("Plain"));
        this.w.addItem(eb.q("Bold"));
        this.w.addItem(eb.q("Italic"));
        this.w.addItem(eb.q("Bold Italic"));
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
        super.q = new String[] { eb.q("Colors"), eb.q("Fonts/Images"), eb.q("Menu/Scroll"), eb.q("Components") };
        this.u = new j(this.q);
        this.i = new j(this.w);
        this.o = new j(this.e);
        this.p = new j(this.r);
        this.a = new j(this.t);
        this.e = new j(this.y);
        this.r = new j(this.u);
        this.q.q(this.u);
        this.w.q(this.i);
        this.e.q(this.o);
        this.r.q(this.p);
        this.t.q(this.a);
        this.y.q(this.e);
        this.u.q(this.r);
        this.t = new j(this.i);
        this.y = new j(this.o);
        this.i.q(this.t);
        this.o.q(this.y);
        this.p.q(this.s);
        this.a.q(this.d);
        this.s.q(this.f);
        this.d.q(this.g);
        this.f.q(this.h);
        this.g.q(this.j);
        this.r.addItem("9");
        this.r.addItem("10");
        this.r.addItem("12");
        this.r.addItem("14");
        this.r.addItem("16");
        this.r.addItem("18");
        this.r.addItem("20");
        this.r.addItem("22");
        this.r.addItem("24");
        this.t.addItem(eb.q("Plain"));
        this.t.addItem(eb.q("Bold"));
        this.t.addItem(eb.q("Italic"));
        this.t.addItem(eb.q("Bold Italic"));
        this.j.q(this.l);
        this.h.q(this.k);
        this.p.setState(true);
        this.k.q(this.z);
        this.l.q(this.x);
        this.t = new TextField(5);
    }
}
