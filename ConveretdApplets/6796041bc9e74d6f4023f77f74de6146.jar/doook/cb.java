// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.util.Enumeration;
import java.awt.Component;
import java.awt.TextField;

public class cb extends bF
{
    private TextField a;
    private TextField b;
    
    public cF a() {
        final bh bh = new bh(-999, "");
        bh.d = "";
        return bh;
    }
    
    public void a(final cF cf) {
        final bh bh = (bh)cf;
        this.a.setText(bh.f());
        this.b.setText(bh.d);
    }
    
    public boolean a(final cF cf) {
        final bh bh = (bh)cf;
        final String trim = this.a.getText().trim();
        final String text = this.b.getText();
        if (trim.length() == 0) {
            this.a.requestFocus();
            new E(this.b(), ao.e("Note"), ao.e("You must provide a word for the emoticon."), super.i).setVisible(true);
            return false;
        }
        if (trim.indexOf(32) != -1) {
            this.a.requestFocus();
            new E(this.b(), ao.e("Note"), ao.e("You can not have spaces in emoticons."), super.i).setVisible(true);
            return false;
        }
        if (text.length() == 0) {
            this.b.requestFocus();
            new E(this.b(), ao.e("Note"), ao.e("You must enter a file name for this icon.  This file name must exactly match the name of a GIF or JPG file located in your \"userIcons\" directory."), super.i).setVisible(true);
            return false;
        }
        bh.d(trim);
        bh.d = text;
        bh.e = super.i.a("emoticons/" + text, true, 50);
        return true;
    }
    
    public void a(final bk bk) {
        bk.a(ao.e("Replace word:"), this.a);
        bk.a(ao.e("With Icon:"), this.b);
        bk.a(new c(ao.e("Please enter the exact file name (including case) of an icon located in your \"emoticons\" directory.")), 2, 1.0f, 0.0f);
    }
    
    public void c() {
        if (super.e || ((cf)super.f.b(0)).k()) {
            final cD cd = new cD(33621775, this.d());
            cd.j = -1;
            cd.o = -1;
            for (int i = 0; i < this.d(); ++i) {
                final bh bh = (bh)this.a(i);
                cd.a(i, 0, bh.h());
                cd.a(i, 0, bh.f());
                cd.a(i, 1, bh.d);
                cd.a(i, bh.d());
            }
            super.i.o(cd);
            super.e = false;
        }
    }
    
    public void d() {
        super.d();
        final Enumeration a = bh.a();
        while (a.hasMoreElements()) {
            this.b((cF)a.nextElement());
        }
    }
    
    public cb(final u u) {
        super(u, ao.e("Emoticons"), ao.e("Emoticon"));
        this.a = new TextField(20);
        this.b = new TextField(20);
        super.f.i(22);
        final j j = new j(t.a, "image");
        j.b(22);
        this.a(super.b, 0);
        super.b.c(0);
        this.a(j, 2);
        j.c(0);
        final cf cf = new cf(ao.e("Restricted"), "restricted");
        cf.d(true);
        this.a(cf, 0);
        cf.b(90);
        cf.c(false);
    }
}
