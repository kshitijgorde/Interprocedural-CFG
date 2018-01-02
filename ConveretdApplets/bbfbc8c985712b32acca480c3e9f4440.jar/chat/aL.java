// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Frame;
import java.awt.Color;
import java.awt.Event;
import java.awt.Component;
import java.awt.Checkbox;
import java.awt.Choice;
import java.awt.TextField;

public final class aL extends T
{
    private TextField a;
    private TextField b;
    private TextField c;
    private TextField d;
    private int a;
    private Choice a;
    private Checkbox a;
    private Checkbox b;
    private Checkbox c;
    private ct a;
    private ct b;
    private v a;
    private v b;
    private v c;
    private v d;
    
    public final void a(final cg cg) {
        if (super.a.a(3)) {
            cg.a(aS.a(74), this.a, this.a, 0);
            cg.a(aS.a(75), this.b, this.b, 0);
        }
        else {
            cg.a(aS.a(74), this.a, 0);
            cg.a(aS.a(75), this.b, 0);
        }
        cg.a(aS.a(76), this.c, 0);
        cg.a(aS.a(77), this.d, 0);
        cg.a(aS.a(78), this.a, 0);
        if (super.a.a(3)) {
            cg.a(aS.a(573), new Component[] { this.c, new bi(aS.a(79), (byte)0), this.d });
        }
        final bF bf;
        (bf = new bF(2, 2, 2, 2, (byte)0)).add("North", this.a);
        bf.add("Center", this.b);
        cg.a(bf);
        if (this.c != null) {
            cg.a(this.c, 0, 0.0f, 0.0f);
        }
        final p p = (p)super.a.i.b(super.a.v);
        if (super.a.o && ((bH)super.a).e && p.a(61)) {
            cg.a(this.a, 0, 0.0f, 0.0f);
        }
        cg.a(this.b, 0, 0.0f, 0.0f);
        this.c.setEchoCharacter('*');
        this.d.setEchoCharacter('*');
    }
    
    public final boolean a(final cg cg, final Event event) {
        switch (event.id) {
            case 402: {
                if (event.target == this.a) {
                    this.a.a(this.a.getText());
                }
                if (event.target == this.b) {
                    this.b.a(this.b.getText());
                    break;
                }
                break;
            }
        }
        return false;
    }
    
    public final void a(final U u) {
        final bP bp = (bP)u;
        this.a.setText(super.a.a(bp.d));
        if (bp.a != null) {
            this.b.setText(bp.a);
        }
        else {
            this.b.setText("");
        }
        if (bp.a != null) {
            this.c.setText(bp.a.toString());
            this.d.setText(bp.a.toString());
        }
        else {
            this.c.setText("");
            this.d.setText("");
        }
        if (bp.i == -999) {
            this.a.a(super.a.a.i);
            this.b.a(super.a.a.i);
            this.c.a(super.a.a.l);
            this.d.a(o.c);
        }
        else {
            this.a.a(new Color(bp.e));
            this.b.a(new Color(bp.f));
            if (bp.g == 0) {
                this.c.a(super.a.a.l);
            }
            else {
                this.c.a(new Color(bp.g));
            }
            if (bp.h == 0) {
                this.d.a(o.c);
            }
            else {
                this.d.a(new Color(bp.h));
            }
        }
        this.a.a(this.a.getText());
        this.b.a(this.b.getText());
        this.a.setState(bp.a(61));
        if (this.c != null) {
            this.c.setState(bp.a(50));
        }
        if (bp.d > 0) {
            this.a.select(String.valueOf(bp.d));
        }
        else {
            this.a.select(0);
        }
        this.b.setState(bp.a(57));
    }
    
    public final boolean a(final U u) {
        final String trim = this.a.getText().trim();
        String text = this.b.getText();
        final String text2 = this.c.getText();
        final String text3 = this.d.getText();
        final bP bp = (bP)u;
        final int a = super.a.a((a)u);
        int n = 0;
        if (a != -1) {
            n = 1;
        }
        if (trim.length() == 0) {
            this.a.requestFocus();
            new bD(this.a(), aS.a(1), aS.a(80) + aS.a(10), super.a).setVisible(true);
            return false;
        }
        final int c = this.c();
        final int o;
        if ((o = ((bH)super.a).o) != 1023 && c - n >= o) {
            new bD(a(this), aS.a(55), new String[] { bm.a(aS.a(81), new String[] { String.valueOf(o) }) }, super.a).setVisible(true);
            return false;
        }
        if (text2.length() > 0 && !text2.equals(text3)) {
            this.d.requestFocus();
            new bD(this.a(), aS.a(1), new String[] { aS.a(82) + aS.a(10), aS.a(83) }, super.a).setVisible(true);
            return false;
        }
        if (text.length() == 0) {
            text = null;
        }
        bp.a = text;
        bp.d = trim;
        if (text2.length() > 0) {
            bp.a = new aV(text2);
        }
        else {
            bp.a = null;
        }
        bp.a(61, this.a.getState());
        if (this.a.getSelectedIndex() == 0) {
            bp.d = 0;
        }
        else {
            bp.d = Integer.parseInt(this.a.getSelectedItem());
        }
        bp.a(57, this.b.getState());
        if (this.c != null) {
            bp.a(50, this.c.getState());
        }
        bp.e = this.a.a;
        bp.f = this.b.getBackground().getRGB();
        bp.g = this.c.getBackground().getRGB();
        bp.h = this.d.getBackground().getRGB();
        return true;
    }
    
    public final U a() {
        return new bP(-999, "");
    }
    
    public final void b() {
        Label_0062: {
            if (!super.a) {
                int i = 0;
                while (true) {
                    while (i < this.a()) {
                        final ak ak;
                        if ((ak = (ak)this.a(i)).a(62)) {
                            if (ak.i != this.a) {
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
        (r = new r(67330, this.b())).e = -1;
        r.d = -1;
        r.f = super.a.i;
        for (int j = 0; j < this.a(); ++j) {
            final ak ak2;
            if ((ak2 = (ak)this.a(j)).j != 0) {
                final ak ak3;
                if (ak2.b != -999 && (ak3 = (ak)this.b(ak2.b)) != null && ak3.a(63)) {
                    ak2.b(63);
                }
                r.a(n, ak2.b);
                r.a(n, 0, ak2.i);
                if (!ak2.a(63)) {
                    r.a(n, 1, ak2.b);
                    r.a(n, 2, ak2.c);
                    r.a(n, 3, ak2.d);
                    r.a(n, 5, ak2.e);
                    r.a(n, 6, ak2.f);
                    r.a(n, 7, ak2.g);
                    r.a(n, 8, ak2.h);
                    r.a(n, 0, ak2.d);
                    r.a(n, 1, ak2.a);
                    r.a(n, 2, ak2.b);
                    r.a(n, 0, ak2.a);
                    r.a(n, ak2.b);
                }
                ++n;
            }
        }
        super.a.o(r);
        super.a = false;
    }
    
    public final void b(final U u) {
        super.b(u);
        super.a.a(u, new Color(((bP)u).e), Color.white, new Color((((bP)u).h == 0) ? 15658734 : ((bP)u).h));
    }
    
    public final void a() {
        super.a();
        try {
            for (int i = 0; i < super.a.d.a(); ++i) {
                final ak ak;
                if ((ak = (ak)super.a.d.a(i)).a(62)) {
                    this.a = ak.i;
                }
                this.b(new bP(ak));
            }
        }
        finally {
            throw loadexception(java.lang.Throwable.class);
        }
    }
    
    private final int c() {
        int n = 0;
        for (int i = 0; i < this.a(); ++i) {
            if (!this.a(i).a(63)) {
                ++n;
            }
        }
        return n;
    }
    
    public final void c() {
        final int c = this.c();
        final int o;
        if ((o = ((bH)super.a).o) != 1023 && c > o) {
            new bD(a(this), aS.a(55), new String[] { bm.a(aS.a(81), new String[] { String.valueOf(o) }) }, super.a).setVisible(true);
            this.handleEvent(new Event(super.b, 1001, null));
        }
    }
    
    private static final Frame a(Component parent) {
        while (!(parent instanceof Frame)) {
            parent = parent.getParent();
        }
        return (Frame)parent;
    }
    
    public aL(final cx cx) {
        super(cx, aS.a(18), aS.a(84));
        this.a = new TextField(20);
        this.b = new TextField(30);
        this.c = new TextField(15);
        this.d = new TextField(15);
        this.a = new Choice();
        new Choice();
        this.a = new Checkbox(aS.a(85));
        this.b = new Checkbox(aS.a(86));
        if (cx.c().length() > 3 && cx.c().toLowerCase().startsWith("roro") && cx.a(52)) {
            this.c = new Checkbox(aS.a("Top Rooms"));
        }
        this.a = new ct();
        this.b = new ct();
        this.a = new v(cx, this.a);
        this.b = new v(cx, this.b);
        this.c = new v(cx, this.a, this.b);
        this.d = new v(cx);
        this.a.setSize(25, 25);
        if (cx.a(3)) {
            this.a.b = true;
            this.a.a(0);
            this.b.b = true;
            this.b.a(0);
            this.c.b = true;
            this.c.a(0);
            this.d.b = true;
            this.d.a(0);
        }
        final I i;
        (i = new I(null, "lock")).c(10);
        this.a(super.a, 0);
        i.b(0);
        final I j;
        (j = new I(null, "temporary")).c(10);
        j.b(-1);
        this.a(j, 2);
        this.a(i, 3);
        this.a.addItem(aS.a(87));
        this.a.addItem("2");
        this.a.addItem("3");
        this.a.addItem("4");
        this.a.addItem("5");
        this.a.addItem("10");
        this.a.addItem("15");
        this.a.addItem("20");
        this.a.addItem("25");
        this.a.addItem("30");
        this.a.addItem("35");
        this.a.addItem("40");
        this.a.addItem("45");
        this.a.addItem("50");
        this.a.addItem("75");
        this.a.addItem("100");
        this.d();
        if (!cx.a(56)) {
            super.a.b();
        }
    }
}
