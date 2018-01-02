// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Component;
import java.awt.Checkbox;
import java.awt.TextField;

public class aP extends bF
{
    private TextField n;
    private TextField o;
    private TextField p;
    private TextField q;
    private Checkbox o;
    
    public cF a() {
        final J j = new J(-999, "");
        j.description = "";
        j.an = 0;
        j.b = "";
        return j;
    }
    
    public void a(final cF cf) {
        final cd cd = (cd)cf;
        this.n.setText(cd.f());
        this.o.setText(cd.description);
        if (cd.b != null) {
            this.p.setText(cd.b);
        }
        else {
            this.p.setText("");
        }
        this.q.setText(String.valueOf(cd.an));
        this.o.setState(!cd.d(5));
    }
    
    public boolean a(final cF cf) {
        final cd cd = (cd)cf;
        final String text = this.n.getText();
        final String text2 = this.o.getText();
        final String text3 = this.p.getText();
        if (text.length() == 0) {
            this.n.requestFocus();
            new E(this.b(), ao.e("Note"), ao.e("You must enter a name for this group."), super.i).setVisible(true);
            return false;
        }
        int int1;
        try {
            int1 = Integer.parseInt(this.q.getText());
            if (int1 < 1 || int1 > 1000) {
                this.q.requestFocus();
                new E(this.b(), ao.e("Note"), ao.e("Sorting order must be between 1 and 1000"), super.i).setVisible(true);
                return false;
            }
        }
        catch (NumberFormatException ex) {
            this.q.requestFocus();
            new E(this.b(), ao.e("Note"), ao.e("You must enter a valid number for the sorting order for this group."), super.i).setVisible(true);
            return false;
        }
        cd.d(text);
        cd.description = text2;
        cd.b = text3;
        if (text3.length() > 0 && !doook.y.b.contains(text3)) {
            doook.y.b.put(text3, super.i.a("stars/" + text3, true, 15));
        }
        cd.an = int1;
        cd.a(5, !this.o.getState());
        return true;
    }
    
    public void a(final bk bk) {
        bk.a(ao.e("Group Name:"), this.n);
        bk.a(ao.e("Group Description:"), this.o);
        bk.a(ao.e("Star Image:"), this.p);
        bk.a(new c(ao.e("Please enter the exact file name (including case) of a star image located in your \"stars\" directory.")), 2, 1.0f, 0.0f);
        bk.a(ao.e("Sorting Order:"), this.q);
        bk.a(ao.e("Restricted:"), this.o);
    }
    
    public void c() {
        if (super.e || ((cf)super.f.b(0)).k() || ((cf)super.f.b(1)).k()) {
            final cD cd = new cD(327936, this.j());
            cd.j = -1;
            cd.o = -1;
            int i = 0;
            int n = 0;
            while (i < this.d()) {
                final cd cd2 = (cd)this.a(i);
                if (cd2.aw) {
                    cd.a(n, cd2.d());
                    cd.a(n, 0, cd2.h());
                    if (!cd2.d(63)) {
                        cd.a(n, 0, cd2.f());
                        cd.a(n, 1, cd2.description);
                        cd.a(n, 2, cd2.b);
                        cd.a(n, 1, cd2.an);
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
        super.i.l.a(false);
        try {
            for (int i = 0; i < super.i.l.b(); ++i) {
                this.b((cF)new J((cd)super.i.l.a(i)));
            }
        }
        finally {
            super.i.l.a();
        }
    }
    
    public aP(final u u) {
        super(u, ao.e("Groups"), ao.e("Group"));
        this.n = new TextField(30);
        this.o = new TextField(30);
        this.p = new TextField(30);
        this.q = new TextField(10);
        this.o = new Checkbox("", true);
        super.f.i(26);
        final j j = new j(t.a, "image");
        j.b(26);
        this.a(super.b, 0);
        this.a(j, 2);
        j.c(0);
        final j i = new j(ao.e("Sort Order"), "sort");
        i.b(50);
        this.a(i, 3);
        i.c(true);
        final cf cf = new cf(ao.e("Restricted"), "restricted");
        cf.d(true);
        this.a(cf, 0);
        cf.b(90);
        cf.c(false);
        super.f.c(false);
        super.f.a(i);
    }
}
