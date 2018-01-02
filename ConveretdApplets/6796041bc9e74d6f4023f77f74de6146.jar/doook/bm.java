// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Component;
import java.awt.Checkbox;
import java.awt.Choice;
import java.awt.TextField;

public class bm extends bF
{
    private TextField a;
    private TextField b;
    private TextField c;
    private TextField d;
    private TextField e;
    private TextField f;
    private bR f;
    private bR g;
    private bR h;
    private bR i;
    private bR j;
    private bR k;
    private bR l;
    private bR m;
    private bP c;
    private Choice n;
    private Choice o;
    private int af;
    private Checkbox t;
    private Checkbox u;
    private Checkbox v;
    private Checkbox w;
    private Checkbox x;
    private TextField u;
    private Choice p;
    private TextField v;
    private TextField w;
    private aX h;
    private aX i;
    private aX j;
    private aX k;
    private aX l;
    private aX m;
    private aX n;
    
    public cF a() {
        return new aV(-999, "");
    }
    
    public void a(final bk bk) {
        bk.a(ao.e("Name:"), this.a);
        bk.a(ao.e("Directory:"), this.u);
        bk.a(ao.e("Outer Background Color (rgb):"), this.b, this.h, 0);
        bk.a(ao.e("Inner Background Color (rgb):"), this.c, this.i, 0);
        bk.a(ao.e("Help Panel Text Color:"), this.g);
        bk.a(ao.e("Help Panel Background Color (rgb):"), this.d, this.j, 0);
        bk.a(ao.e("Input Panel Text Color:"), this.f);
        bk.a(ao.e("Input Panel Background Color (rgb):"), this.e, this.k, 0);
        bk.a(ao.e("Tabbed Panel Text Color:"), this.h);
        bk.a(ao.e("Tabbed Panel Background Color (rgb):"), this.f, this.l, 0);
        bk.a(ao.e("Normal Message Text Color:"), this.i);
        bk.a(ao.e("Flagged Message Text Panel Color:"), this.j);
        bk.a(ao.e("Normal/Flagged Message Background Color:"), this.l);
        bk.a(ao.e("Private Message Text Color:"), this.k);
        bk.a(ao.e("Private Message Background Color:"), this.m);
        bk.a(ao.e("Font:"), this.c, 1);
        bk.a(ao.e("Font Style:"), this.o, 1);
        bk.a(ao.e("Font Size:"), this.n, 1);
        bk.a(ao.e("Use rounded corners:"), this.p, 1);
        bk.a(ao.e("Use background image:"), this.t, 1);
        bk.a(ao.e("Use chat background image:"), this.u, 1);
        bk.a(ao.e("Scale chat background image:"), this.v, 1);
        bk.a(ao.e("Use image buttons:"), this.w, 1);
        bk.a(ao.e("Use image tabs:"), this.x, 1);
        bk.a(ao.e("Super User Message Text Color (rgb):"), this.v, this.m, 0);
        bk.a(ao.e("Super User Message Background Color (rgb):"), this.w, this.n, 0);
    }
    
    public boolean a(final cF cf) {
        final String text = this.a.getText();
        String s = this.v.getText();
        String s2 = this.w.getText();
        final String text2 = this.u.getText();
        if (text.length() == 0) {
            this.a.requestFocus();
            new E(this.b(), ao.e("Note"), ao.e("You must enter a name for this theme."), super.i).setVisible(true);
            return false;
        }
        if (text2.length() == 0) {
            this.u.requestFocus();
            new E(this.b(), ao.e("Note"), ao.e("You must enter a directory name for this theme."), super.i).setVisible(true);
            return false;
        }
        if (s.length() == 0) {
            this.v.requestFocus();
            new E(this.b(), ao.e("Note"), ao.e("You must enter a color code for super user message text in RGB format."), super.i).setVisible(true);
            return false;
        }
        if (s.startsWith("#")) {
            s = s.substring(1);
        }
        if (!this.a(s)) {
            this.v.requestFocus();
            new E(this.b(), ao.e("Note"), ao.e("You must enter a valid color code for super user message text in RGB format..\n\nExample: FFCC00"), super.i).setVisible(true);
            return false;
        }
        if (s2.length() == 0) {
            this.w.requestFocus();
            new E(this.b(), ao.e("Note"), ao.e("You must enter a color code for super user message background in RGB format."), super.i).setVisible(true);
            return false;
        }
        if (s2.startsWith("#")) {
            s2 = s2.substring(1);
        }
        if (!this.a(s2)) {
            this.w.requestFocus();
            new E(this.b(), ao.e("Note"), ao.e("You must enter a valid color code for super user message background in RGB format..\n\nExample: FFCC00"), super.i).setVisible(true);
            return false;
        }
        final aV av = (aV)cf;
        av.d(this.a.getText());
        av.a = bd.a(this.b.getText());
        av.b = bd.a(this.c.getText());
        av.k = bd.a(this.d.getText());
        av.e = bd.a(this.e.getText());
        av.g = bd.a(this.f.getText());
        av.q = bd.a(s);
        av.r = bd.a(s2);
        av.i = this.g.b();
        av.d = this.f.b();
        av.f = this.h.b();
        av.l = this.i.b();
        av.m = this.j.b();
        av.o = this.k.b();
        av.n = this.l.b();
        av.p = this.m.b();
        av.i = new String(this.c.getSelectedItem());
        av.af = this.o.getSelectedIndex();
        av.ae = Integer.parseInt(this.n.getSelectedItem());
        av.a(this.u.getText());
        av.a(this.t.getState());
        av.b(this.u.getState());
        av.c(this.v.getState());
        av.d(this.w.getState());
        av.e(this.x.getState());
        String selectedItem = this.p.getSelectedItem();
        if ("None".equals(selectedItem)) {
            selectedItem = "0";
        }
        av.aE = Integer.parseInt(selectedItem);
        return true;
    }
    
    private boolean a(final String s) {
        try {
            bd.a(s);
            return true;
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    public void a(final cF cf) {
        final aV av = (aV)cf;
        this.a.setText(av.f());
        this.b.setText(bd.b(av.a));
        this.c.setText(bd.b(av.b));
        this.d.setText(bd.b(av.k));
        this.e.setText(bd.b(av.e));
        this.f.setText(bd.b(av.g));
        this.v.setText(bd.b(av.q));
        this.w.setText(bd.b(av.r));
        this.g.b(av.i);
        this.f.b(av.d);
        this.h.b(av.f);
        this.i.b(av.l);
        this.j.b(av.m);
        this.k.b(av.o);
        this.l.b(av.n);
        this.m.b(av.p);
        this.c.select(av.i);
        this.n.select(String.valueOf(av.ae));
        this.o.select(av.af);
        this.u.setText(av.b());
        this.t.setState(av.n());
        this.u.setState(av.o());
        this.v.setState(av.h());
        this.w.setState(av.p());
        this.x.setState(av.q());
        if (av.aE == 0) {
            this.p.select("None");
        }
        else {
            this.p.select(av.aE + "");
        }
        this.h.a(av.a);
        this.i.a(av.b);
        this.j.a(av.k);
        this.k.a(av.e);
        this.l.a(av.g);
        this.m.a(av.q);
        this.n.a(av.r);
    }
    
    public boolean k() {
        for (int i = 0; i < this.d(); ++i) {
            final bA ba = (bA)this.a(i);
            if (ba.d(62)) {
                return ba.h() != this.af;
            }
        }
        return true;
    }
    
    public void c() {
        if (super.e || this.k()) {
            final cD cd = new cD(67341, this.d());
            cd.j = -1;
            cd.o = -1;
            for (int i = 0; i < this.d(); ++i) {
                final bA ba = (bA)this.a(i);
                cd.a(i, ba.d());
                cd.a(i, 0, ba.h());
                if (!ba.d(63)) {
                    cd.a(i, 1, ba.a.getRGB());
                    cd.a(i, 2, ba.b.getRGB());
                    cd.a(i, 3, ba.i.getRGB());
                    cd.a(i, 4, ba.k.getRGB());
                    cd.a(i, 5, ba.f.getRGB());
                    cd.a(i, 6, ba.g.getRGB());
                    cd.a(i, 7, ba.l.getRGB());
                    cd.a(i, 8, ba.m.getRGB());
                    cd.a(i, 9, ba.n.getRGB());
                    cd.a(i, 10, ba.o.getRGB());
                    cd.a(i, 11, ba.p.getRGB());
                    cd.a(i, 12, ba.af);
                    cd.a(i, 13, ba.ae);
                    cd.a(i, 14, ba.aE);
                    cd.a(i, 15, ba.d.getRGB());
                    cd.a(i, 16, ba.e.getRGB());
                    cd.a(i, 17, ba.q.getRGB());
                    cd.a(i, 18, ba.r.getRGB());
                    cd.a(i, 0, ba.f());
                    cd.a(i, 1, ba.i);
                    cd.a(i, 2, ba.b());
                    cd.a(i, ba.d());
                }
            }
            super.i.o(cd);
            super.e = false;
        }
    }
    
    public void d() {
        super.d();
        super.i.q.a(false);
        try {
            for (int i = 0; i < super.i.q.b(); ++i) {
                final aV av = new aV((bA)super.i.q.a(i));
                if (av.d(62)) {
                    this.af = av.h();
                }
                this.b((cF)av);
            }
        }
        finally {
            super.i.q.a();
        }
    }
    
    bm(final u u) {
        super(u, ao.e("Themes"), ao.e("Theme"));
        this.a = new TextField(20);
        this.b = new TextField(8);
        this.c = new TextField(8);
        this.d = new TextField(8);
        this.e = new TextField(8);
        this.f = new TextField(8);
        this.v = new TextField(8);
        this.w = new TextField(8);
        this.f = new bR();
        this.g = new bR();
        this.h = new bR();
        this.i = new bR();
        this.j = new bR();
        this.k = new bR();
        this.l = new bR();
        this.m = new bR();
        this.c = new bP();
        this.n = new Choice();
        this.o = new Choice();
        this.t = new Checkbox();
        this.u = new Checkbox();
        this.v = new Checkbox();
        this.w = new Checkbox();
        this.x = new Checkbox();
        this.u = new TextField(20);
        this.p = new Choice();
        this.a(super.b, 0);
        this.n.addItem("9");
        this.n.addItem("10");
        this.n.addItem("12");
        this.n.addItem("14");
        this.n.addItem("16");
        this.n.addItem("18");
        this.n.addItem("20");
        this.n.addItem("22");
        this.n.addItem("24");
        this.o.addItem(ao.e("Plain"));
        this.o.addItem(ao.e("Bold"));
        this.o.addItem(ao.e("Italic"));
        this.o.addItem(ao.e("Bold Italic"));
        this.p.addItem("None");
        this.p.addItem("2");
        this.p.addItem("4");
        this.p.addItem("6");
        this.p.addItem("8");
        this.p.addItem("10");
        this.p.addItem("12");
        this.p.addItem("14");
        this.p.addItem("16");
        this.p.addItem("18");
        this.p.addItem("20");
        this.p.addItem("22");
        this.p.addItem("24");
        super.e = new String[] { ao.e("Colors"), ao.e("Fonts/Images") };
        (this.h = new aX(u, this.b)).a(u.k, false, true);
        (this.i = new aX(u, this.c)).a(u.k, false, true);
        (this.j = new aX(u, this.d)).a(u.k, false, true);
        (this.k = new aX(u, this.e)).a(u.k, false, true);
        (this.l = new aX(u, this.f)).a(u.k, false, true);
        (this.m = new aX(u, this.v)).a(u.k, false, true);
        (this.n = new aX(u, this.w)).a(u.k, false, true);
    }
}
