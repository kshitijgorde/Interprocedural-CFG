// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.CheckboxGroup;
import java.awt.Component;
import java.awt.Event;
import java.awt.Color;
import java.awt.Choice;
import java.awt.Checkbox;
import java.awt.TextArea;
import java.awt.TextField;

public final class aQ extends T
{
    private TextField a;
    private TextField b;
    private TextArea a;
    private ck a;
    private Checkbox a;
    private Checkbox b;
    private Choice a;
    private boolean c;
    private boolean d;
    private long a;
    private v a;
    private v b;
    private v c;
    private Choice b;
    private cC b;
    
    public final U a() {
        final bQ bq;
        (bq = new bQ("")).a = "";
        return bq;
    }
    
    public final void a(final U u) {
        final ao ao = (ao)u;
        this.a.setText(ao.d);
        this.a.a(ao.a);
        if (this.a.a() < 0) {
            this.a.b(0);
        }
        this.a.setText(ao.a);
        this.a.a(new Color(ao.c));
        this.b.a(new Color(ao.d));
        if (ao.e != 0) {
            this.c.a(new Color(ao.e));
        }
        else {
            this.c.b();
        }
        int n = 0;
        if (ao.a(6) && !ao.a(7)) {
            n = 2;
        }
        else if (!ao.a(6) && ao.a(7)) {
            n = 1;
        }
        this.b.select(n);
    }
    
    public final boolean a(final cg cg, final Event event) {
        return false;
    }
    
    public final boolean a(final U u) {
        final ao ao = (ao)u;
        final String text = this.a.getText();
        final String text2 = this.a.getText();
        if (text.length() == 0) {
            this.a.requestFocus();
            new bD(this.a(), aS.a(1), aS.a(362) + aS.a(10), super.a).setVisible(true);
            return false;
        }
        if (text2.length() == 0) {
            this.a.requestFocus();
            new bD(this.a(), aS.a(1), aS.a(363) + aS.a(10), super.a).setVisible(true);
            return false;
        }
        ao.a = text2;
        ao.d = text;
        ao.a = this.a.a().i;
        ao.c = this.a.a;
        ao.d = this.b.a;
        ao.e = this.c.a;
        final int selectedIndex;
        if ((selectedIndex = this.b.getSelectedIndex()) == 0) {
            ao.a(6, false);
            ao.a(7, false);
        }
        else if (selectedIndex == 2) {
            ao.a(6, true);
            ao.a(7, false);
        }
        else {
            ao.a(6, false);
            ao.a(7, true);
        }
        return true;
    }
    
    public final void a(final cg cg) {
        cg.a(aS.a(74), this.a, this.a, 0);
        cg.a(aS.a(364), this.a, this.b, 0);
        cg.a(aS.a(556), new Component[] { this.b, new bi(aS.a(572), (byte)0), this.c });
        this.a.resize(200, 82);
        cg.a(this.a, 1, 1.0f, 1.0f);
    }
    
    public final void b() {
        final boolean state = this.b.getState();
        final boolean b = this.a.getSelectedIndex() == 1;
        int int1;
        try {
            if (((int1 = Integer.parseInt(this.b.getText().trim())) < 10 && !b) || int1 < 1) {
                this.b.selectAll();
                this.b.requestFocus();
                throw new ar(bm.a(aS.a(365), new String[] { aS.a(106) }));
            }
        }
        catch (NumberFormatException ex) {
            this.b.selectAll();
            this.b.requestFocus();
            throw new ar(bm.a(aS.a(366), new String[] { aS.a(106) }));
        }
        if (super.a || this.c != state || this.d != b || this.a != int1 || ((cC)super.a.a(0)).e) {
            final r r;
            (r = new r(67332, this.b())).e = -1;
            r.d = -1;
            int n = 0;
            for (int i = 0; i < this.a(); ++i) {
                final ao ao;
                if ((ao = (ao)this.a(i)).j != 0) {
                    r.a(n, ao.b);
                    r.a(n, 0, ao.i);
                    if (!ao.a(63)) {
                        r.a(n, 1, ao.b);
                        r.a(n, 2, ao.a);
                        r.a(n, 0, ao.d);
                        r.a(n, 1, ao.a);
                        r.a(n, 3, ao.c);
                        r.a(n, 4, ao.d);
                        r.a(n, 5, ao.e);
                    }
                    ++n;
                }
            }
            r.a(-1, (long)int1);
            r.a(-1, 62, state);
            r.a(-1, 32, b);
            super.a.o(r);
            super.a = false;
        }
    }
    
    public final void b(final U u) {
        super.b(u);
        super.a.a(u, new Color(((ao)u).c), Color.white, new Color((((ao)u).e == 0) ? 15658734 : ((ao)u).e));
    }
    
    public final void a() {
        super.a();
        try {
            for (int i = 0; i < super.a.f.a(); ++i) {
                this.b(new bQ((ao)super.a.f.a(i)));
            }
        }
        finally {
            throw loadexception(java.lang.Throwable.class);
        }
        this.d = r.a(super.a.g, 32);
        if (this.d) {
            this.a.select(1);
        }
        else {
            this.a.select(0);
        }
        this.c = r.a(super.a.g, 62);
        if (this.c) {
            this.b.setState(true);
        }
        else {
            this.a.setState(true);
        }
        this.a = (super.a.g & 0xFFFFFFFFL);
        this.b.setText(String.valueOf(this.a));
        this.b.e = false;
    }
    
    public aQ(final cx cx) {
        super(cx, aS.a(106), aS.a(106));
        this.a = new TextField(30);
        this.b = new TextField(5);
        this.a = new TextArea("", 5, 30, 1);
        this.a = new ck();
        this.a = new v(cx);
        this.b = new v(cx, this.a);
        this.c = new v(cx);
        this.a.b = true;
        this.a.a(0);
        this.b.b = true;
        this.b.a(0);
        this.c.b = true;
        this.c.a(0);
        this.a = new Choice();
        this.b = new Choice();
        (this.b = new cC(null, "restricted")).a(true);
        this.a(this.b, 0);
        this.b.b = false;
        final I i = new I("Type", "type");
        this.a(i);
        i.b = false;
        super.a.a(250);
        final CheckboxGroup checkboxGroup = new CheckboxGroup();
        this.a = new Checkbox(aS.a(356), checkboxGroup, false);
        this.b = new Checkbox(aS.a(357), checkboxGroup, false);
        this.a.addItem(aS.a(358));
        this.a.addItem(aS.a(359));
        this.b.addItem(aS.a(419));
        this.b.addItem(aS.a(420));
        this.b.addItem(aS.a(421));
        this.a(aS.a(360), this.a);
        this.a("", this.b);
        this.a(aS.a(361), this.b, this.a);
        this.a.a.a();
        try {
            for (int j = 0; j < cx.b.a(); ++j) {
                this.a.a((b)cx.b.a(j));
            }
        }
        finally {
            throw loadexception(java.lang.Throwable.class);
        }
    }
}
