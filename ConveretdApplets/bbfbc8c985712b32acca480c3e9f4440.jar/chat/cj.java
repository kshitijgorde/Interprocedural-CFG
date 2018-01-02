// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.FontMetrics;
import java.awt.Component;
import java.awt.Event;
import java.awt.Color;
import java.awt.Choice;
import java.awt.TextField;

public final class cj extends T
{
    private TextField a;
    private ck a;
    private cr e;
    private cr f;
    private cr g;
    private cr h;
    private Choice a;
    private Choice b;
    private av b;
    private v a;
    
    public final U a() {
        final co co;
        (co = new co("")).a = "";
        co.b = "";
        return co;
    }
    
    public final void a(final U u) {
        final bv bv = (bv)u;
        this.a.setText(bv.d);
        this.a.a(bv.a);
        if (this.a.a() < 0) {
            this.a.b(0);
        }
        this.a.a(new Color(bv.c));
        if (bv.b == -1) {
            this.a.select(0);
        }
        else {
            final U u2;
            if ((u2 = (U)this.b.b(bv.b)) != null) {
                this.a.select(u2.d);
            }
        }
        this.b.select(bv.a);
    }
    
    public final boolean a(final cg cg, final Event event) {
        return false;
    }
    
    public final boolean a(final U u) {
        final bv bv = (bv)u;
        final String text;
        if ((text = this.a.getText()).length() == 0) {
            this.a.requestFocus();
            new bD(this.a(), aS.a(1), aS.a(223) + aS.a(10), super.a).setVisible(true);
            return false;
        }
        bv.d = text;
        bv.a = this.a.a().i;
        bv.c = this.a.a;
        final int selectedIndex;
        if ((selectedIndex = this.a.getSelectedIndex()) > 0) {
            final U u2 = (U)this.b.a(selectedIndex);
            bv.b = u2.i;
            bv.b = u2.d;
        }
        bv.a = this.b.getSelectedItem();
        bv.a = (b)super.a.b.b(bv.a);
        return true;
    }
    
    public final void a(final cg cg) {
        cg.a(aS.a(74), this.a, this.a, 0);
        cg.a(aS.a(84), this.a, 0);
        cg.a(aS.a(377), this.b, 0);
        this.a.resize(200, 82);
        cg.a(this.a, 1, 1.0f, 1.0f);
    }
    
    public final void b() {
        if (super.a) {
            final r r;
            (r = new r(67360, this.b())).e = -1;
            r.d = -1;
            int n = 0;
            for (int i = 0; i < this.a(); ++i) {
                final bv bv;
                if ((bv = (bv)this.a(i)).j != 0) {
                    r.a(n, bv.b);
                    r.a(n, 0, bv.i);
                    if (!bv.a(63)) {
                        r.a(n, 1, bv.b);
                        r.a(n, 2, bv.a);
                        r.a(n, 3, bv.c);
                        r.a(n, 0, bv.d);
                        r.a(n, 1, bv.a);
                        r.a(n, 2, bv.b);
                    }
                    ++n;
                }
            }
            super.a.o(r);
            super.a = false;
        }
    }
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 1001: {
                final U u = (U)super.a.a();
                if (event.target == this.e) {
                    final r r;
                    (r = new r(67346, 1)).d = -1;
                    r.e = -1;
                    r.a(0, 0, u.i);
                    r.a(0, 1, 1);
                    super.a.o(r);
                }
                if (event.target == this.g) {
                    final r r2;
                    (r2 = new r(67346, 1)).d = -1;
                    r2.e = -1;
                    r2.a(0, 0, -999);
                    r2.a(0, 1, 1);
                    super.a.o(r2);
                }
                if (event.target == this.f) {
                    final r r3;
                    (r3 = new r(67346, 1)).d = -1;
                    r3.e = -1;
                    r3.a(0, 0, u.i);
                    r3.a(0, 1, 2);
                    super.a.o(r3);
                }
                if (event.target == this.h) {
                    final r r4;
                    (r4 = new r(67346, 1)).d = -1;
                    r4.e = -1;
                    r4.a(0, 0, -999);
                    r4.a(0, 1, 2);
                    super.a.o(r4);
                }
                return super.handleEvent(event);
            }
            case 701: {
                this.d((U)event.arg);
                return true;
            }
            case 702: {
                this.d(null);
                return true;
            }
            default: {
                return super.handleEvent(event);
            }
        }
    }
    
    public final void d(final U u) {
        if (u == null) {
            this.f.b();
            this.e.b();
            this.g.b();
            this.h.b();
        }
        else {
            this.f.a();
            this.e.a();
            this.g.a();
            this.h.a();
            if (u.a(1)) {
                this.e.b();
                this.f.a();
            }
            else {
                this.e.a();
                this.f.b();
            }
        }
        super.c(u);
    }
    
    public final void b(final U u) {
        super.b(u);
        super.a.a(u, new Color(((bv)u).c), Color.white, new Color(15658734));
    }
    
    public final void a() {
        super.a();
        try {
            for (int i = 0; i < super.a.m.a(); ++i) {
                final bv bv = (bv)super.a.m.a(i);
                final co co2;
                final co co = co2 = new co(bv);
                co.a = (b)super.a.b.b(co2.a);
                final U u;
                if ((u = (U)this.b.b(bv.b)) != null) {
                    co2.b = u.d;
                }
                this.b(co2);
            }
        }
        finally {
            throw loadexception(java.lang.Throwable.class);
        }
    }
    
    public cj(final cx cx) {
        super(cx, aS.a(644), aS.a(644));
        this.a = new TextField(30);
        this.a = new ck();
        this.a = new v(cx);
        this.a = new Choice();
        this.b = new Choice();
        this.e = new cr(80, 20);
        this.f = new cr(80, 20);
        this.h = new cr(80, 20);
        this.g = new cr(80, 20);
        this.b = (av)cx.d.clone();
        for (int i = 0; i < this.b.a(); ++i) {
            this.a.addItem(((U)this.b.a(i)).d);
        }
        this.a.select(0);
        for (int j = 0; j < ax.b.length; ++j) {
            this.b.addItem(ax.b[j]);
        }
        super.a.b(26);
        final I k;
        (k = new I(cs.b, "image")).a(26);
        this.a(k, 1);
        k.b(0);
        final I l = new I(aS.a(84), "room");
        this.a(l);
        l.b = true;
        l.a(100);
        final I m = new I(aS.a(377), "country");
        this.a(m);
        m.b = true;
        super.a.a(160);
        this.a.b = true;
        this.a.a(0);
        this.e.a(aS.a(26));
        this.f.a(aS.a(12));
        this.g.a(aS.a(26) + " " + aS.a(419));
        this.h.a(aS.a(12) + " " + aS.a(419));
        final FontMetrics fontMetrics;
        int stringWidth = (fontMetrics = this.e.getFontMetrics(this.e.getFont())).stringWidth(this.e.a);
        final int stringWidth2 = fontMetrics.stringWidth(this.f.a);
        final int stringWidth3 = fontMetrics.stringWidth(this.h.a);
        if (stringWidth2 > stringWidth) {
            stringWidth = stringWidth2;
        }
        if (stringWidth3 > stringWidth) {
            stringWidth = stringWidth3;
        }
        final int stringWidth4;
        if ((stringWidth4 = fontMetrics.stringWidth(this.g.a)) > stringWidth) {
            stringWidth = stringWidth4;
        }
        stringWidth += 20;
        this.f.resize(stringWidth, 20);
        this.e.resize(stringWidth, 20);
        this.g.resize(stringWidth, 20);
        this.h.resize(stringWidth, 20);
        this.a("", new Component[] { this.e, this.f, this.g, this.h });
        this.d();
        this.f.b();
        this.e.b();
        this.g.b();
        this.h.b();
        this.a.a.a();
        try {
            for (int n = 0; n < cx.b.a(); ++n) {
                this.a.a((b)cx.b.a(n));
            }
        }
        finally {
            throw loadexception(java.lang.Throwable.class);
        }
    }
}
