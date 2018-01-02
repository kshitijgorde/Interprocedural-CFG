// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.Color;
import java.awt.Panel;
import java.awt.TextField;

public class br extends bF
{
    private TextField a;
    private TextField b;
    private aX a;
    private aX b;
    private Panel e;
    private j e;
    
    public cF a() {
        final aT at = new aT(-999, "");
        at.a = "";
        return at;
    }
    
    public void a(final cF cf) {
        final ax ax = (ax)cf;
        this.a.setText(ax.f());
        this.b.setText(ax.a);
        this.a.a(new Color(ax.aN));
        this.b.a(new Color(ax.d));
    }
    
    public boolean a(final cF cf) {
        final ax ax = (ax)cf;
        final String text = this.a.getText();
        final String text2 = this.b.getText();
        if (text.length() == 0) {
            this.a.requestFocus();
            new E(this.b(), ao.e("Note"), ao.e("You must provide a word to be censored."), super.i).setVisible(true);
            return false;
        }
        if (text2.length() == 0) {
            this.b.requestFocus();
            new E(this.b(), ao.e("Note"), ao.e("You must provide a replacement for the shortcut."), super.i).setVisible(true);
            return false;
        }
        ax.a = text2;
        ax.d(text);
        ax.aN = this.a.aA;
        ax.d = this.b.aA;
        return true;
    }
    
    public void a(final bk bk) {
        bk.a(ao.e("Replace:"), this.a);
        bk.a(ao.e("With:"), this.b, this.e, 0);
    }
    
    public void c() {
        if (super.e) {
            final cD cd = new cD(263936, this.j());
            cd.j = -1;
            cd.o = -1;
            int i = 0;
            int n = 0;
            while (i < this.d()) {
                final ax ax = (ax)this.a(i);
                if (ax.aw) {
                    cd.a(n, ax.d());
                    cd.a(n, 0, ax.h());
                    if (!ax.d(63)) {
                        cd.a(n, 0, ax.f());
                        cd.a(n, 1, ax.a);
                        cd.a(n, 1, ax.aN);
                        cd.a(n, 2, ax.d);
                    }
                    ++n;
                }
                ++i;
            }
            super.i.o(cd);
        }
    }
    
    public void b(final cF cf) {
        super.b(cf);
        super.f.a(cf, new Color(cf.aN), Color.white, cf.d);
    }
    
    public void d() {
        super.d();
        super.i.j.a(false);
        try {
            for (int i = 0; i < super.i.j.b(); ++i) {
                this.b(new aT((ax)super.i.j.a(i)));
            }
        }
        finally {
            super.i.j.a();
        }
    }
    
    public br(final u u, final boolean b) {
        super(u, ao.e("Nicknames Shortcuts"), ao.e("Nickname Shortcut"));
        this.a = new TextField(20);
        this.b = new TextField(20);
        (this.a = new aX(u, this.b, "setForeground")).a(u.k, false, true);
        (this.b = new aX(u, this.b, "setBackground")).a(u.k, false, true);
        (this.e = new Panel(new FlowLayout())).add(this.a);
        this.e.add(this.b);
        final String e = ao.e("Replace:");
        this.a(this.e = new j(e.substring(0, e.length() - 1), "replace"));
        this.e.c(false);
        super.g.b(135);
    }
}
