// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Color;
import java.awt.Component;
import java.awt.Choice;
import java.awt.TextField;

public class bX extends bF
{
    private TextField a;
    private Choice t;
    
    public cF a() {
        final bc bc = new bc(-999, "");
        bc.Q = "";
        return bc;
    }
    
    public void a(final cF cf) {
        final as as = (as)cf;
        this.a.setText(as.f());
        if (as.Q != null) {
            this.t.select(as.Q);
        }
        else {
            this.t.select(0);
        }
    }
    
    public boolean a(final cF cf) {
        final as as = (as)cf;
        final String text = this.a.getText();
        if (text.length() == 0) {
            this.a.requestFocus();
            new E(this.b(), ao.e("Note"), ao.e("You must enter a file name for this icon.  This file name must exactly match the name of a GIF or JPG file located in your \"userIcons\" directory."), super.i).setVisible(true);
            return false;
        }
        as.d(text);
        as.q = super.i.a("userIcons/" + text, true, 40);
        as.Q = this.t.getSelectedItem();
        return true;
    }
    
    public void a(final bk bk) {
        bk.a(ao.e("File Name:"), this.a);
        bk.a(ao.e("User:"), this.t);
        bk.a(new c(ao.e("Please enter the exact file name (including case) of an icon located in your \"userIcons\" directory.")), 2, 1.0f, 0.0f);
    }
    
    public void c() {
        if (super.e || ((cf)super.f.b(0)).k() || ((cf)super.f.b(1)).k()) {
            final cD cd = new cD(67331, this.j());
            cd.j = -1;
            cd.o = -1;
            int i = 0;
            int n = 0;
            while (i < this.d()) {
                final as as = (as)this.a(i);
                if (as.aw) {
                    if (as.d(62) && as.d(36)) {
                        as.h(36);
                    }
                    cd.a(n, as.d());
                    cd.a(n, 0, as.h());
                    if (!as.d(63)) {
                        cd.a(n, 0, as.f());
                        cd.a(n, 1, as.Q);
                    }
                    ++n;
                }
                ++i;
            }
            super.i.o(cd);
            super.e = false;
        }
    }
    
    public void d() {
        super.d();
        super.i.b.a(false);
        try {
            for (int i = 0; i < super.i.b.b(); ++i) {
                this.b((cF)new bc((as)super.i.b.a(i)));
            }
        }
        finally {
            super.i.b.a();
        }
    }
    
    public bX(final u u) {
        super(u, ao.e("Icons"), ao.e("Icon"));
        this.a = new TextField(30);
        (this.t = new Choice()).setForeground(Color.black);
        u.g.a(false);
        this.t.addItem("");
        try {
            for (int i = 0; i < u.g.b(); ++i) {
                this.t.addItem(((cF)u.g.a(i)).f());
            }
        }
        finally {
            u.g.a();
        }
        super.f.i(26);
        final j j = new j(doook.t.a, "image");
        j.b(26);
        this.a(super.b, 0);
        this.a(j, 2);
        j.c(0);
        final cf cf = new cf(ao.e("Restricted"), "restricted");
        cf.d(true);
        this.a(cf, 0);
        cf.b(90);
        cf.c(false);
    }
}
