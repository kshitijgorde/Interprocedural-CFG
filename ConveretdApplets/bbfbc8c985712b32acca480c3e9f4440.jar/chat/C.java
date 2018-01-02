// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Event;
import java.awt.Component;
import java.awt.Checkbox;
import java.awt.Choice;
import java.awt.TextField;

public final class C extends T
{
    private TextField a;
    private TextField b;
    private TextField c;
    private TextField d;
    private TextField e;
    private TextField f;
    private o a;
    private o b;
    private o c;
    private o d;
    private o e;
    private o f;
    private o g;
    private o h;
    private an a;
    private Choice a;
    private Choice b;
    private int a;
    private Checkbox a;
    private Checkbox b;
    private Checkbox c;
    private Checkbox d;
    private Checkbox e;
    private TextField g;
    private Choice c;
    private v a;
    private v b;
    private v c;
    private v d;
    private v e;
    private v f;
    private v g;
    private Checkbox f;
    private Checkbox g;
    private TextField h;
    private TextField i;
    private v h;
    private v i;
    private TextField j;
    private TextField k;
    
    public final U a() {
        return new bY(-999, "");
    }
    
    public final void a(final cg cg) {
        cg.a(aS.a(74), this.a, 0);
        cg.a(aS.a(190), this.g, 0);
        cg.a(aS.a(191), this.b, this.a, 0);
        cg.a(aS.a(192), this.c, this.b, 0);
        cg.a(aS.a(193), this.b, 0);
        cg.a(aS.a(194), this.d, this.c, 0);
        cg.a(aS.a(195), this.a, 0);
        cg.a(aS.a(196), this.e, this.d, 0);
        cg.a(aS.a(197), this.c, 0);
        cg.a(aS.a(198), this.f, this.e, 0);
        cg.a(aS.a(199), this.d, 0);
        cg.a(aS.a(200), this.e, 0);
        cg.a(aS.a(201), this.g, 0);
        cg.a(aS.a(202), this.f, 0);
        cg.a(aS.a(203), this.h, 0);
        cg.a(aS.a(204), this.a, 1);
        cg.a(aS.a(205), this.b, 1);
        cg.a(aS.a(206), this.a, 1);
        cg.a(aS.a(207), this.c, 1);
        cg.a(aS.a(208), this.a, 1);
        cg.a(aS.a(209), this.b, 1);
        cg.a(aS.a(210), this.c, 1);
        cg.a(aS.a(211), this.d, 1);
        cg.a(aS.a(212), this.e, 1);
        cg.a(aS.a(213), this.f, 1);
        cg.a(aS.a(214), this.g, 1);
        cg.a(aS.a(215), this.h, this.f, 1);
        cg.a(aS.a(216), this.i, this.g, 1);
        cg.a(aS.a("Text Color (rgb):"), this.j, this.h, 2);
        cg.a(aS.a("Background Color (rgb):"), this.k, this.i, 2);
    }
    
    public final boolean a(final cg cg, final Event event) {
        return false;
    }
    
    public final boolean a(final U u) {
        final bY by;
        (by = (bY)u).d = this.a.getText();
        by.a = chat.y.a(this.b.getText());
        by.b = chat.y.a(this.c.getText());
        by.d = chat.y.a(this.d.getText());
        by.f = chat.y.a(this.e.getText());
        by.h = chat.y.a(this.f.getText());
        by.o = chat.y.a(this.h.getText());
        by.p = chat.y.a(this.i.getText());
        by.q = chat.y.a(this.j.getText());
        by.r = chat.y.a(this.k.getText());
        by.c = this.b.a();
        by.e = this.a.a();
        by.g = this.c.a();
        by.i = this.d.a();
        by.k = this.e.a();
        by.m = this.f.a();
        by.l = this.g.a();
        by.n = this.h.a();
        by.a = new String(this.a.getSelectedItem());
        by.b = this.b.getSelectedIndex();
        by.a = Integer.parseInt(this.a.getSelectedItem());
        by.a(this.g.getText());
        by.a(this.a.getState());
        by.b(this.b.getState());
        by.c(this.c.getState());
        by.d(this.d.getState());
        by.e(this.e.getState());
        by.a(55, this.f.getState());
        by.a(54, this.g.getState());
        String selectedItem = this.c.getSelectedItem();
        if ("None".equals(selectedItem)) {
            selectedItem = "0";
        }
        by.c = Integer.parseInt(selectedItem);
        return true;
    }
    
    public final void a(final U u) {
        final bY by = (bY)u;
        this.a.setText(by.d);
        this.b.setText(chat.y.a(by.a));
        this.c.setText(chat.y.a(by.b));
        this.d.setText(chat.y.a(by.d));
        this.e.setText(chat.y.a(by.f));
        this.f.setText(chat.y.a(by.h));
        this.h.setText(chat.y.a(by.o));
        this.i.setText(chat.y.a(by.p));
        this.b.a(by.c);
        this.a.a(by.e);
        this.c.a(by.g);
        this.d.a(by.i);
        this.e.a(by.k);
        this.f.a(by.m);
        this.g.a(by.l);
        this.h.a(by.n);
        this.a.select(by.a);
        this.a.select(String.valueOf(by.a));
        this.b.select(by.b);
        this.g.setText(by.b());
        this.a.setState(by.a());
        this.b.setState(by.b());
        this.c.setState(by.c());
        this.d.setState(by.d());
        this.e.setState(by.e());
        this.f.setState(by.a(55));
        this.g.setState(by.a(54));
        if (by.c == 0) {
            this.c.select("None");
        }
        else {
            this.c.select(by.c + "");
        }
        this.a.a(by.a);
        this.b.a(by.b);
        this.c.a(by.d);
        this.d.a(by.f);
        this.e.a(by.h);
        this.f.a(by.o);
        this.g.a(by.p);
        this.h.a(by.q);
        this.i.a(by.r);
    }
    
    public final void b() {
        Label_0062: {
            if (!super.a) {
                int i = 0;
                while (true) {
                    while (i < this.a()) {
                        final cz cz;
                        if ((cz = (cz)this.a(i)).a(62)) {
                            if (cz.i != this.a) {
                                break;
                            }
                            final boolean b = false;
                            if (b) {
                                break Label_0062;
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
        int n = 0;
        final r r;
        (r = new r(67341, this.b())).e = -1;
        r.d = -1;
        for (int j = 0; j < this.a(); ++j) {
            final cz cz2;
            if ((cz2 = (cz)this.a(j)).j != 0) {
                r.a(n, cz2.b);
                r.a(n, 0, cz2.i);
                if (!cz2.a(63)) {
                    r.a(n, 1, cz2.a.getRGB());
                    r.a(n, 2, cz2.b.getRGB());
                    r.a(n, 3, cz2.c.getRGB());
                    r.a(n, 4, cz2.d.getRGB());
                    r.a(n, 5, cz2.g.getRGB());
                    r.a(n, 6, cz2.h.getRGB());
                    r.a(n, 7, cz2.i.getRGB());
                    r.a(n, 8, cz2.k.getRGB());
                    r.a(n, 9, cz2.l.getRGB());
                    r.a(n, 10, cz2.m.getRGB());
                    r.a(n, 11, cz2.n.getRGB());
                    r.a(n, 12, cz2.b);
                    r.a(n, 13, cz2.a);
                    r.a(n, 14, cz2.c);
                    r.a(n, 15, cz2.e.getRGB());
                    r.a(n, 16, cz2.f.getRGB());
                    r.a(n, 17, cz2.o.getRGB());
                    r.a(n, 18, cz2.p.getRGB());
                    r.a(n, 19, cz2.q.getRGB());
                    r.a(n, 20, cz2.r.getRGB());
                    r.a(n, 0, cz2.d);
                    r.a(n, 1, cz2.a);
                    r.a(n, 2, cz2.b());
                    r.a(n, cz2.b);
                }
                ++n;
            }
        }
        super.a.o(r);
        super.a = false;
    }
    
    public final void a() {
        super.a();
        try {
            for (int i = 0; i < super.a.l.a(); ++i) {
                final bY by;
                if ((by = new bY((cz)super.a.l.a(i))).a(62)) {
                    this.a = by.i;
                }
                this.b(by);
            }
        }
        finally {
            throw loadexception(java.lang.Throwable.class);
        }
    }
    
    C(final cx cx) {
        super(cx, aS.a(112), aS.a(113));
        this.a = new TextField(20);
        this.b = new TextField(8);
        this.c = new TextField(8);
        this.d = new TextField(8);
        this.e = new TextField(8);
        this.f = new TextField(8);
        this.h = new TextField(8);
        this.i = new TextField(8);
        this.j = new TextField(8);
        this.k = new TextField(8);
        this.i = new TextField(8);
        this.a = new o();
        this.b = new o();
        this.c = new o();
        this.d = new o();
        this.e = new o();
        this.f = new o();
        this.g = new o();
        this.h = new o();
        this.a = new an();
        this.a = new Choice();
        this.b = new Choice();
        this.a = new Checkbox();
        this.b = new Checkbox();
        this.c = new Checkbox();
        this.d = new Checkbox();
        this.e = new Checkbox();
        this.g = new TextField(20);
        this.c = new Choice();
        this.a(super.a, 0);
        this.a.addItem("9");
        this.a.addItem("10");
        this.a.addItem("12");
        this.a.addItem("14");
        this.a.addItem("16");
        this.a.addItem("18");
        this.a.addItem("20");
        this.a.addItem("22");
        this.a.addItem("24");
        this.b.addItem(aS.a(217));
        this.b.addItem(aS.a(218));
        this.b.addItem(aS.a(219));
        this.b.addItem(aS.a(220));
        this.c.addItem("None");
        this.c.addItem("2");
        this.c.addItem("4");
        this.c.addItem("6");
        this.c.addItem("8");
        this.c.addItem("10");
        this.c.addItem("12");
        this.c.addItem("14");
        this.c.addItem("16");
        this.c.addItem("18");
        this.c.addItem("20");
        this.c.addItem("22");
        this.c.addItem("24");
        super.a = new String[] { aS.a(221), aS.a(222), aS.a("SMS Fonts/Colors") };
        this.a = new v(cx, this.b);
        this.b = new v(cx, this.c);
        this.c = new v(cx, this.d);
        this.d = new v(cx, this.e);
        this.e = new v(cx, this.f);
        this.f = new v(cx, this.h);
        this.g = new v(cx, this.i);
        this.h = new v(cx, this.j);
        this.i = new v(cx, this.k);
        this.f = new Checkbox();
        this.g = new Checkbox();
    }
}
