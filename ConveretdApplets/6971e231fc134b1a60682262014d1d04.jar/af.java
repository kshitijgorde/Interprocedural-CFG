import java.awt.Graphics;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.Label;

// 
// Decompiled by Procyon v0.5.30
// 

class af extends a8
{
    MainChartApplet parent;
    Label p;
    Label n;
    Label k;
    Label j;
    Label i;
    Label g;
    Label q;
    Label s;
    Label l;
    Label t;
    Label r;
    Label u;
    Label m;
    Label f;
    Label e;
    Label h;
    Label o;
    Label z;
    Label A;
    Label v;
    int w;
    
    public af(final MainChartApplet parent) {
        super(false, false, false, false);
        this.p = new Label();
        this.n = new Label();
        this.k = new Label();
        this.j = new Label();
        this.i = new Label();
        this.g = new Label();
        this.q = new Label("\ub0a0\uc9dc", 1);
        this.s = new Label("\uc2dc\uac00", 1);
        this.l = new Label("\uc800\uac00", 1);
        this.t = new Label("\uace0\uac00", 1);
        this.r = new Label("\uc885\uac00", 1);
        this.u = new Label("\uac70\ub798\ub7c9", 1);
        this.m = new Label(parent._fldif, 1);
        this.f = new Label("", 1);
        this.e = new Label("", 1);
        this.h = new Label("", 1);
        this.o = new Label("", 1);
        this.z = new Label("", 1);
        this.A = new Label("");
        this.v = new Label("");
        this.parent = parent;
        this.w = 85;
        this.setBackground(new Color(255, 255, 255));
        this.setLayout(null);
        this.p.setBounds(61, 1, this.w - 1, 2);
        this.p.setBackground(new Color(223, 223, 223));
        this.add(this.p);
        this.q.setBounds(61, 3, this.w - 1, 15);
        this.q.setBackground(new Color(223, 223, 223));
        this.add(this.q);
        this.m.setBounds(61, 22, this.w - 1, 13);
        this.m.setBackground(Color.white);
        this.add(this.m);
        this.n.setBounds(61 + this.w * 1, 1, this.w - 1, 2);
        this.n.setBackground(new Color(223, 223, 223));
        this.add(this.n);
        this.s.setBounds(61 + this.w * 1, 3, this.w - 1, 15);
        this.s.setBackground(new Color(223, 223, 223));
        this.add(this.s);
        this.f.setBounds(61 + this.w * 1, 22, this.w - 1, 13);
        this.f.setBackground(Color.white);
        this.add(this.f);
        this.k.setBounds(61 + this.w * 2, 1, this.w - 1, 2);
        this.k.setBackground(new Color(223, 223, 223));
        this.add(this.k);
        this.l.setBounds(61 + this.w * 2, 3, this.w - 1, 15);
        this.l.setBackground(new Color(223, 223, 223));
        this.add(this.l);
        this.e.setBounds(61 + this.w * 2, 22, this.w - 1, 13);
        this.e.setBackground(Color.white);
        this.add(this.e);
        this.j.setBounds(61 + this.w * 3, 1, this.w - 1, 2);
        this.j.setBackground(new Color(223, 223, 223));
        this.add(this.j);
        this.t.setBounds(61 + this.w * 3, 3, this.w - 1, 15);
        this.t.setBackground(new Color(223, 223, 223));
        this.add(this.t);
        this.h.setBounds(61 + this.w * 3, 22, this.w - 1, 13);
        this.h.setBackground(Color.white);
        this.add(this.h);
        this.i.setBounds(61 + this.w * 4, 1, this.w - 1, 2);
        this.i.setBackground(new Color(223, 223, 223));
        this.add(this.i);
        this.r.setBounds(61 + this.w * 4, 3, this.w - 1, 15);
        this.r.setBackground(new Color(223, 223, 223));
        this.add(this.r);
        this.o.setBounds(61 + this.w * 4, 22, this.w - 1, 13);
        this.o.setBackground(Color.white);
        this.add(this.o);
        this.g.setBounds(61 + this.w * 5, 1, this.w - 1, 2);
        this.g.setBackground(new Color(223, 223, 223));
        this.add(this.g);
        this.u.setBounds(61 + this.w * 5, 3, this.w - 1, 15);
        this.u.setBackground(new Color(223, 223, 223));
        this.add(this.u);
        this.z.setBounds(61 + this.w * 5, 22, this.w - 1, 13);
        this.z.setBackground(Color.white);
        this.add(this.z);
    }
    
    public void _mthnew() {
    }
    
    public void paint(final Graphics graphics) {
        this.w = 85;
        graphics.setColor(new Color(183, 183, 183));
        graphics.drawLine(61, 0, 570, 0);
        graphics.drawLine(61, 18, 570, 18);
        graphics.drawLine(61, 35, 570, 35);
        graphics.drawLine(60 + this.w * 0, 0, 60 + this.w * 0, 35);
        graphics.drawLine(60 + this.w * 1, 0, 60 + this.w * 1, 35);
        graphics.drawLine(60 + this.w * 2, 0, 60 + this.w * 2, 35);
        graphics.drawLine(60 + this.w * 3, 0, 60 + this.w * 3, 35);
        graphics.drawLine(60 + this.w * 4, 0, 60 + this.w * 4, 35);
        graphics.drawLine(60 + this.w * 5, 0, 60 + this.w * 5, 35);
        graphics.drawLine(570, 0, 570, 35);
        this.paintComponents(graphics);
    }
}
