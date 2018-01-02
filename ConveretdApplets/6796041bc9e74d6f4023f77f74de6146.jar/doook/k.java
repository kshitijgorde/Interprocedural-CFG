// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Component;
import java.awt.Color;
import java.awt.Checkbox;
import java.awt.TextField;

public class k extends bF
{
    private TextField a;
    private TextField b;
    private Checkbox a;
    private Checkbox b;
    private Checkbox c;
    private aX a;
    
    public cF a() {
        return new m(-999, "");
    }
    
    public void a(final cF cf) {
        final bs bs = (bs)cf;
        if (bs.Y == null || bs.Y == "") {
            bs.Y = "0";
        }
        this.a.setText(bs.f());
        this.b.setText(bs.Y);
        this.a.a(new Color(Integer.parseInt(bs.Y, 16)));
        this.c.setState(bs.d(13));
        this.a.setState(bs.d(14));
        this.b.setState(bs.d(15));
    }
    
    public boolean a(final cF cf) {
        final bs bs = (bs)cf;
        final String text = this.a.getText();
        String y = this.b.getText();
        if (text.length() == 0) {
            this.a.requestFocus();
            new E(this.b(), ao.e("Note"), ao.e("You must enter a name for this color."), super.i).setVisible(true);
            return false;
        }
        if (y.length() == 0) {
            this.b.requestFocus();
            new E(this.b(), ao.e("Note"), ao.e("You must enter a code for this color in RGB format."), super.i).setVisible(true);
            return false;
        }
        if (y.startsWith("#")) {
            y = y.substring(1);
        }
        if (!this.a(y)) {
            this.b.requestFocus();
            new E(this.b(), ao.e("Note"), ao.e("You must enter a valid code for this color in RGB format.\n\nExample: FFCC00"), super.i).setVisible(true);
            return false;
        }
        bs.d(text);
        bs.Y = y;
        bs.a(13, this.c.getState());
        bs.a(14, this.a.getState());
        bs.a(15, this.b.getState());
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
    
    public void a(final bk bk) {
        bk.a(ao.e("Name:"), this.a);
        bk.a(ao.e("Code:"), this.b, this.a, 0);
        bk.a("", this.c);
        bk.a("", this.a);
        bk.a("", this.b);
    }
    
    public void c() {
        if (super.e) {
            final cD cd = new cD(67342, this.j());
            cd.j = -1;
            cd.o = -1;
            int i = 0;
            int n = 0;
            while (i < this.d()) {
                final bs bs = (bs)this.a(i);
                if (bs.aw) {
                    cd.a(n, bs.d());
                    cd.a(n, 0, bs.h());
                    if (!bs.d(63)) {
                        cd.a(n, 1, bs.f());
                        cd.a(n, 0, bs.Y);
                        cd.a(n, 13, bs.d(13));
                        cd.a(n, 14, bs.d(14));
                        cd.a(n, 15, bs.d(15));
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
        super.i.k.a(false);
        try {
            for (int i = 0; i < super.i.k.b(); ++i) {
                this.b(new m((bs)super.i.k.a(i)));
            }
        }
        finally {
            super.i.k.a();
        }
    }
    
    public void b(final cF cf) {
        super.b(cf);
        final bs bs = (bs)cf;
        if (bs.Y == null || bs.Y == "") {
            bs.Y = "0";
        }
        super.f.a(cf, new Color(Integer.parseInt(bs.Y, 16)), Color.white);
    }
    
    public void setForeground(final Color color) {
        super.setForeground(color);
        if (this.b != null) {
            this.b.setForeground(color);
            this.b.setText(bd.b(color));
        }
    }
    
    public k(final u u) {
        super(u, ao.e("Colors"), ao.e("Color"));
        this.a = new TextField(30);
        this.b = new TextField(30);
        this.a = new aX(u, this, "setForeground");
        this.c = new Checkbox(ao.e("User for message color"));
        this.a = new Checkbox(ao.e("User for guest nickname color"));
        this.b = new Checkbox(ao.e("Use for super nickname color"));
    }
}
