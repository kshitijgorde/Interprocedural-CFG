// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.FontMetrics;
import java.awt.Event;
import java.awt.Component;
import java.awt.TextField;

public class bY extends bF
{
    private TextField a;
    private TextField b;
    private TextField c;
    aS d;
    aS r;
    
    public cF a() {
        return new be(-999, "");
    }
    
    public void a(final cF cf) {
        final be be = (be)cf;
        this.a.setText(be.f());
        if (be.a != null) {
            this.b.setText(be.a);
        }
        else {
            this.b.setText("");
        }
        if (be.R != null) {
            this.c.setText(be.R);
        }
        else {
            this.c.setText("");
        }
    }
    
    public boolean a(final cF cf) {
        final String text = this.a.getText();
        final String text2 = this.b.getText();
        String text3 = this.c.getText();
        final be be = (be)cf;
        if (text.length() == 0) {
            this.a.requestFocus();
            new E(this.b(), ao.e("Note"), ao.e("You must provide a site name for this banner.  Please re-enter this information."), super.i).setVisible(true);
            return false;
        }
        if (text2.length() == 0) {
            this.b.requestFocus();
            new E(this.b(), ao.e("Note"), new String[] { ao.e("You must provide a file name for this banner image.  Please re-enter this information."), ao.e("Banner images may be either GIF or JPEG files.") }, super.i).setVisible(true);
            return false;
        }
        if (text3.length() == 0) {
            text3 = null;
        }
        be.a = text2;
        be.d(text);
        be.R = text3;
        return true;
    }
    
    public void a(final bk bk) {
        bk.a(ao.e("Site Name:"), this.a);
        bk.a(ao.e("Web Address:"), this.c);
        bk.a(ao.e("File Name:"), this.b);
    }
    
    public void c() {
        if (super.e) {
            final cD cd = new cD(67329, this.d());
            cd.j = -1;
            cd.o = -1;
            for (int i = 0; i < this.d(); ++i) {
                final at at = (at)this.a(i);
                cd.a(i, at.d());
                cd.a(i, 0, at.h());
                if (!at.d(63)) {
                    cd.a(i, 1, at.a);
                    cd.a(i, 2, at.b);
                    cd.a(i, 0, at.f());
                    cd.a(i, 1, at.R);
                    cd.a(i, 2, at.a);
                    cd.a(i, 3, at.d);
                }
            }
            super.i.o(cd);
            super.e = false;
        }
    }
    
    public void d() {
        super.d();
        super.i.e.a(false);
        try {
            for (int i = 0; i < super.i.e.b(); ++i) {
                this.b((cF)new be((at)super.i.e.a(i)));
            }
        }
        finally {
            super.i.e.a();
        }
    }
    
    public void c(final cF cf) {
        if (cf == null) {
            this.r.d();
            this.d.d();
        }
        else if (cf == super.x.a(0)) {
            this.r.c();
            this.d.d();
        }
        else if (cf == super.x.b(super.x.g() - 1)) {
            this.d.c();
            this.r.d();
        }
        else {
            this.r.c();
            this.d.c();
        }
        final cF cf2 = (cF)super.f.a();
        super.c(cf);
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 1001: {
                final cF cf = (cF)super.f.a();
                if (event.target == this.r) {
                    this.g(cf);
                }
                if (event.target == this.d) {
                    this.i(cf);
                }
                return super.handleEvent(event);
            }
            case 701: {
                this.c((cF)event.arg);
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
    
    public void g(final cF cf) {
        int b = super.x.b((Object)cf);
        while (super.x.b(++b) == null && super.x.g() > b) {}
        final be be = (be)super.x.b(b);
        final int h = be.h();
        be.l(cf.h());
        cf.l(h);
        super.x.a(be, be.h());
        super.x.a(cf, b);
        super.f.c();
        super.e = true;
        this.c(cf);
    }
    
    public void i(final cF cf) {
        int b = super.x.b((Object)cf);
        while (super.x.b(--b) == null && b >= 0) {}
        final be be = (be)super.x.b(b);
        final int h = be.h();
        be.l(cf.h());
        cf.l(h);
        super.x.a(be, be.h());
        super.x.a(cf, b);
        super.f.c();
        super.e = true;
        this.c(cf);
    }
    
    public bY(final u u) {
        super(u, ao.e("Banners"), ao.e("Banner"));
        this.a = new TextField(20);
        this.b = new TextField(20);
        this.c = new TextField(20);
        this.d = new aS(80, 20);
        this.r = new aS(80, 20);
        this.d.a(ao.e("Move Up"));
        this.r.a(ao.e("Move Down"));
        final FontMetrics fontMetrics = this.d.getFontMetrics(this.d.getFont());
        int stringWidth = fontMetrics.stringWidth(ao.e("Move Up"));
        final int stringWidth2 = fontMetrics.stringWidth(ao.e("Move Down"));
        if (stringWidth2 > stringWidth) {
            stringWidth = stringWidth2;
        }
        stringWidth += 20;
        this.r.resize(stringWidth, 20);
        this.d.resize(stringWidth, 20);
        this.a("", this.d, this.r);
        this.r.d();
        this.d.d();
    }
}
