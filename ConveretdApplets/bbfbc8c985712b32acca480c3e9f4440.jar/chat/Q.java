// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Event;
import java.awt.Component;
import java.awt.Color;
import java.awt.Choice;
import java.awt.TextField;

public final class Q extends T
{
    private TextField a;
    private TextField b;
    private I b;
    private Choice a;
    private boolean c;
    private int a;
    private cC b;
    private v a;
    
    public final U a() {
        final ch ch;
        (ch = new ch(-999, "")).a = "";
        return ch;
    }
    
    public final void a(final U u) {
        final ba ba = (ba)u;
        this.a.setText(ba.d);
        this.b.setText(ba.a);
        this.a.a(new Color(ba.a));
    }
    
    public final boolean a(final U u) {
        final ba ba = (ba)u;
        final String text = this.a.getText();
        final String text2 = this.b.getText();
        if (text.length() == 0) {
            this.a.requestFocus();
            new bD(this.a(), aS.a(1), aS.a(434), super.a).setVisible(true);
            return false;
        }
        ba.a = text2;
        ba.d = text;
        ba.a = this.a.a;
        return true;
    }
    
    public final void a(final cg cg) {
        cg.a(aS.a(433), this.a, 0);
        cg.a(aS.a(436), this.b, 0);
        cg.a(aS.a(157), this.a, 0);
        cg.a(new bi(aS.a(435), (byte)0));
    }
    
    public final boolean a(final cg cg, final Event event) {
        return false;
    }
    
    public final void b() {
        final int selectedIndex = this.a.getSelectedIndex();
        if (super.a || selectedIndex != this.a || ((cC)super.a.a(0)).e) {
            final r r;
            (r = new r(67333, this.b())).e = -1;
            r.d = -1;
            int n = 0;
            for (int i = 0; i < this.a(); ++i) {
                final ba ba;
                if ((ba = (ba)this.a(i)).j != 0) {
                    r.a(n, ba.b);
                    r.a(n, 0, ba.i);
                    if (!ba.a(63)) {
                        r.a(n, 0, ba.d);
                        r.a(n, 1, ba.a);
                        r.a(n, 1, ba.a);
                    }
                    ++n;
                }
            }
            r.a(-1, 60, selectedIndex == 1 && !this.c);
            r.a(-1, 61, selectedIndex == 0 && !this.c);
            r.a(-1, 62, selectedIndex == 0 && this.c);
            r.a(-1, 59, this.c);
            super.a.o(r);
            super.a = false;
        }
    }
    
    public final void b(final U u) {
        super.b(u);
        super.a.a(u, new Color(((ba)u).a), Color.white, new Color(15658734));
    }
    
    public final void a() {
        super.a();
        try {
            for (int i = 0; i < super.a.h.a(); ++i) {
                this.b(new ch((ba)super.a.h.a(i)));
            }
        }
        finally {
            throw loadexception(java.lang.Throwable.class);
        }
        if (this.c) {
            this.a.enable();
            if (r.a(super.a.e, 62)) {
                this.a.select(0);
            }
            else {
                this.a.select(1);
            }
        }
        else {
            final long n;
            if (r.a(n = (super.a.f | super.a.e), 62)) {
                this.a.select(0);
                this.a.disable();
            }
            else {
                this.a.enable();
                if (r.a(n, 61)) {
                    this.a.select(0);
                }
                else if (r.a(n, 60)) {
                    this.a.select(1);
                }
                else {
                    this.a.select(2);
                }
            }
        }
        this.a = this.a.getSelectedIndex();
        this.b.e = false;
    }
    
    public Q(final cx cx, final boolean c) {
        super(cx, aS.a(98), aS.a(98));
        this.a = new TextField(20);
        this.b = new TextField(30);
        this.a = new Choice();
        this.a = new v(cx);
        (this.b = new cC("View", "view")).a(true);
        this.a(this.b, 0);
        this.b.a(50);
        this.b.b = false;
        this.c = c;
        if (c) {
            this.a.addItem(aS.a(427));
            this.a.addItem(aS.a(428));
        }
        else {
            this.a.addItem(aS.a(429));
            this.a.addItem(aS.a(430));
            this.a.addItem(aS.a(431));
        }
        this.a(aS.a(432), this.a);
        this.a.b = true;
        this.a.a(0);
        final String a = aS.a(433);
        this.a(this.b = new I(a.substring(0, a.length() - 1), "replace"));
        this.b.b = false;
        super.a.a(135);
    }
}
