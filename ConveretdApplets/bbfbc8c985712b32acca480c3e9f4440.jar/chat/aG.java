// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Component;
import java.awt.Color;
import java.awt.Event;
import java.awt.Choice;
import java.awt.TextArea;
import java.awt.Checkbox;

public final class aG extends T
{
    private Checkbox a;
    private TextArea a;
    private Choice a;
    private Choice b;
    private Choice c;
    private Choice d;
    private Choice e;
    private v a;
    private v b;
    private cC b;
    private an a;
    private int a;
    
    public final U a() {
        return new d("");
    }
    
    public final boolean a(final cg cg, final Event event) {
        if (event.target == this.a) {
            if (this.a.getState()) {
                this.a.disable();
                this.d.disable();
                this.c.disable();
            }
            else {
                this.a.enable();
                this.d.enable();
                this.c.enable();
            }
            return true;
        }
        return false;
    }
    
    public final boolean a(final U u) {
        final String text;
        if ((text = this.a.getText()).length() == 0) {
            super.a.requestFocus();
            new bD(this.a(), aS.a(1), aS.a(648) + aS.a(10), super.a).setVisible(true);
            return false;
        }
        final bu bu;
        (bu = (bu)u).d = text;
        bu.a(2, this.e.getSelectedIndex() != 2);
        bu.a(3, this.e.getSelectedIndex() != 1);
        bu.a(4, this.a.d);
        bu.a(5, this.b.d);
        if (this.a.getState()) {
            bu.a = null;
            bu.a = 0;
            bu.b = 0;
        }
        else {
            bu.a = new String(this.a.getSelectedItem());
            bu.a = this.c.getSelectedIndex();
            bu.b = Integer.parseInt(this.d.getSelectedItem());
        }
        bu.a = (this.a.d ? null : this.a.getBackground());
        bu.b = (this.b.d ? null : this.b.getBackground());
        return true;
    }
    
    public final void a(final U u) {
        final bu bu = (bu)u;
        this.a.setText(bu.d);
        if (bu.i == -999) {
            bu.b = 60L;
        }
        this.e.select(bu.a(2) ? (bu.a(3) ? false : true) : 2);
        if (bu.a == null) {
            this.a.select(super.a.a.a);
            this.d.select(String.valueOf(super.a.a.a));
            this.c.select(String.valueOf(super.a.a.b));
            this.a.disable();
            this.d.disable();
            this.c.disable();
            this.a.setState(true);
        }
        else {
            this.a.select(bu.a);
            this.d.select(String.valueOf(bu.b));
            this.c.select(String.valueOf(bu.a));
            this.a.enable();
            this.d.enable();
            this.c.enable();
            this.a.setState(false);
        }
        if (bu.i == -999 || bu.a(4)) {
            this.a.a((Color)null);
        }
        else {
            this.a.a(bu.a);
        }
        if (bu.i == -999 || bu.a(5)) {
            this.b.a((Color)null);
            return;
        }
        this.b.a(bu.b);
    }
    
    public final void a(final cg cg) {
        cg.a(aS.a(649), this.a, 0);
        cg.a(aS.a(556), this.e, 0);
        cg.a(aS.a(204), new Component[] { this.a, this.a, new bi(aS.a(647), (byte)0) });
        cg.a(aS.a(205), this.c, 0);
        cg.a(aS.a(206), this.d, 0);
        cg.a(aS.a(602), this.a, 0);
        cg.a(aS.a(572), this.b, 0);
    }
    
    public final void b() {
        final int selectedIndex = this.a.getSelectedIndex();
        if (super.a || ((cC)super.a.a(0)).e || this.a != selectedIndex || this.b.getSelectedIndex() != super.a.P) {
            final r r = new r(67351, this.b());
            int n = 0;
            for (int i = 0; i < this.a(); ++i) {
                final bu bu;
                if ((bu = (bu)this.a(i)).j != 0) {
                    r.a(n, bu.b);
                    r.a(n, 0, bu.i);
                    if (!bu.a(63)) {
                        r.a(n, 1, bu.a);
                        r.a(n, 2, bu.b);
                        r.a(n, 3, (bu.a != null) ? (bu.a.getRGB() & 0xFFFFFF) : 0);
                        r.a(n, 4, (bu.b != null) ? (bu.b.getRGB() & 0xFFFFFF) : 0);
                        r.a(n, 0, bu.d);
                        r.a(n, 1, bu.a);
                    }
                    ++n;
                }
            }
            r.a(-1, (long)selectedIndex);
            r.a(-1, 4, this.b.getSelectedIndex() == 1);
            r.e = -1;
            r.d = -1;
            super.a.o(r);
            super.a = false;
        }
    }
    
    public final void a() {
        super.a();
        try {
            for (int i = 0; i < super.a.s.a(); ++i) {
                this.b(new d((bu)super.a.s.a(i)));
            }
        }
        finally {
            throw loadexception(java.lang.Throwable.class);
        }
        this.b.select(super.a.P);
        this.a.select(super.a.Q);
        this.a = super.a.Q;
    }
    
    public aG(final cx cx) {
        super(cx, aS.a(650), "SMS");
        this.a = new TextArea("", 5, 30, 1);
        this.a = new an();
        (this.e = new Choice()).addItem(aS.a(419));
        this.e.addItem(aS.a(420));
        this.e.addItem(aS.a(421));
        (this.c = new Choice()).addItem(aS.a(217));
        this.c.addItem(aS.a(218));
        this.c.addItem(aS.a(219));
        this.c.addItem(aS.a(220));
        (this.d = new Choice()).addItem("9");
        this.d.addItem("10");
        this.d.addItem("12");
        this.d.addItem("14");
        this.d.addItem("16");
        this.d.addItem("18");
        this.d.addItem("20");
        this.d.addItem("22");
        this.d.addItem("24");
        this.a = new v(cx, null);
        this.b = new v(cx, null);
        this.a.c = true;
        this.b.c = true;
        this.a = new Checkbox();
        (this.b = new cC(aS.a(651), "view")).a(40);
        this.b.b = false;
        this.b.a(true);
        this.a(this.b, 0);
        this.a(new k(), 2, 1.0f, 0.0f);
        (this.a = new Choice()).setBackground(Color.white);
        this.a.setForeground(Color.black);
        this.a.addItem(aS.a(403));
        this.a.addItem(aS.a(404));
        this.a.addItem(aS.a(405));
        this.a.addItem(aS.a(406));
        this.a.addItem(aS.a(407));
        (this.b = new Choice()).setBackground(Color.white);
        this.b.setForeground(Color.black);
        this.b.addItem(aS.a(608));
        this.b.addItem(aS.a(607));
        this.a(aS.a(605), this.b);
        this.a(aS.a(606), this.a);
    }
}
