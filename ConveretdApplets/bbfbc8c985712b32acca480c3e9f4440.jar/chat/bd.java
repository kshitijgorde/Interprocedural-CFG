// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.Color;
import java.awt.Event;
import java.awt.Panel;
import java.awt.TextField;

public final class bd extends T
{
    private TextField a;
    private TextField b;
    private I b;
    private Panel a;
    private v a;
    private v b;
    
    public final U a() {
        final W w;
        (w = new W(-999, "")).a = "";
        return w;
    }
    
    public final boolean a(final cg cg, final Event event) {
        return false;
    }
    
    public final void a(final U u) {
        final bn bn = (bn)u;
        this.a.setText(bn.d);
        this.b.setText(bn.a);
        this.a.a(new Color(bn.a));
        if (bn.b != 0) {
            this.b.a(new Color(bn.b));
            return;
        }
        this.b.b();
    }
    
    public final boolean a(final U u) {
        final bn bn = (bn)u;
        final String text = this.a.getText();
        final String text2 = this.b.getText();
        if (text.trim().length() == 0) {
            this.a.requestFocus();
            new bD(this.a(), aS.a(1), aS.a(434), super.a).setVisible(true);
            return false;
        }
        if (text2.trim().length() == 0) {
            this.b.requestFocus();
            new bD(this.a(), aS.a(1), aS.a(434), super.a).setVisible(true);
            return false;
        }
        final String s = text;
        final bn bn2 = (bn)u;
        final String s2 = s;
        int i = 0;
        while (true) {
            while (i < this.a()) {
                final U a = this.a(i);
                if (bn2 != a && a.d.equalsIgnoreCase(s2)) {
                    final boolean b = true;
                    if (b) {
                        new bD(this.a(), aS.a(1), bm.a(aS.a(225), new String[] { text }), super.a).setVisible(true);
                        return false;
                    }
                    final String s3 = text2;
                    final bn bn3 = (bn)u;
                    final String s4 = s3;
                    int j = 0;
                    while (true) {
                        while (j < this.a()) {
                            final U a2 = this.a(j);
                            if (bn3 != a2 && ((bn)a2).a.equalsIgnoreCase(s4)) {
                                final boolean b2 = true;
                                if (b2) {
                                    new bD(this.a(), aS.a(1), bm.a(aS.a(450), new String[] { text2 }), super.a).setVisible(true);
                                    return false;
                                }
                                bn.a = text2;
                                bn.d = text;
                                bn.a = this.a.a;
                                bn.b = this.b.a;
                                return true;
                            }
                            else {
                                ++j;
                            }
                        }
                        final boolean b2 = false;
                        continue;
                    }
                }
                else {
                    ++i;
                }
            }
            final boolean b = false;
            continue;
        }
    }
    
    public final void a(final cg cg) {
        cg.a(aS.a(74), this.a, 0);
        cg.a(aS.a(449) + ": ", this.b, 0);
        cg.a(aS.a(157), this.a, 0);
    }
    
    public final void b() {
        if (super.a) {
            int n = 0;
            final r r;
            (r = new r(67342, this.b())).e = -1;
            r.d = -1;
            for (int i = 0; i < this.a(); ++i) {
                final bn bn;
                if ((bn = (bn)this.a(i)).j != 0) {
                    r.a(n, bn.b);
                    r.a(n, 0, bn.i);
                    if (!bn.a(63)) {
                        r.a(n, 0, bn.d);
                        r.a(n, 1, bn.a);
                        r.a(n, 1, bn.a);
                        r.a(n, 2, bn.b);
                    }
                    ++n;
                }
            }
            super.a.o(r);
            super.a = false;
        }
    }
    
    public final void b(final U u) {
        super.b(u);
        super.a.a(u, new Color(((bn)u).a), Color.white, new Color((((bn)u).b == 0) ? 15658734 : ((bn)u).b));
    }
    
    public final void a() {
        super.a();
        try {
            for (int i = 0; i < super.a.p.a(); ++i) {
                this.b(new W((bn)super.a.p.a(i)));
            }
        }
        finally {
            throw loadexception(java.lang.Throwable.class);
        }
    }
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 701: {
                this.c((U)event.arg);
                return true;
            }
            case 702: {
                this.c(null);
                return true;
            }
            default: {
                return super.handleEvent(event);
            }
        }
    }
    
    public bd(final cx cx) {
        super(cx, aS.a(449), aS.a(449));
        this.a = new TextField(20);
        this.b = new TextField(30);
        final String string = aS.a(449) + ": ";
        this.a(this.b = new I(string.substring(0, string.length() - 1), "Shortcut"));
        this.b.b = false;
        this.a = new v(cx);
        this.b = new v(cx);
        this.a.b = true;
        this.a.a(0);
        this.b.b = true;
        this.b.a(0);
        final bi bi;
        (bi = new bi(aS.a(572), (byte)0)).setFont(bk.d);
        (this.a = new Panel()).setLayout(new FlowLayout(1, 0, 0));
        this.a.add(this.a);
        this.a.add(bi);
        this.a.add(this.b);
        super.a.a(135);
    }
}
