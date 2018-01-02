import java.awt.image.ImageObserver;
import au.com.rocketdog.project.awc.applet.images.ImageRes;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.PopupMenu;
import java.awt.Dimension;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class au extends av implements ac
{
    private boolean a;
    public final ad b;
    public z c;
    private Dimension d;
    
    public abstract PopupMenu b();
    
    public abstract void a(final Graphics p0, final Component p1, final int p2, final int p3);
    
    public abstract void a(final int p0, final int p1);
    
    public abstract int a();
    
    public void a(final z c) {
        this.c = c;
    }
    
    public void a(final av av) {
        if (this.b.a(av)) {
            av.a(this);
            this.a(this.a);
            this.c();
        }
    }
    
    public void b(final int n, final int n2) {
        super.b(n, n2);
        final av[] b = this.b.b();
        int n3 = n2 + this.a();
        for (int i = 0; i < b.length; ++i) {
            if (!b[i].n()) {
                b[i].b(0, n3);
                n3 += b[i].g().height;
            }
        }
    }
    
    public void c() {
        this.p();
    }
    
    public void b(final av av) {
        if (this.b.b(av) != null) {
            this.c();
        }
    }
    
    public av[] d() {
        return this.b.b();
    }
    
    public boolean e() {
        return this.a;
    }
    
    public void a(final boolean a) {
        this.a = a;
        if (this.a) {
            final av[] b = this.b.b();
            int a2 = this.a();
            for (int i = 0; i < b.length; ++i) {
                if (!b[i].n()) {
                    a2 += b[i].g().height;
                }
            }
            this.d.height = a2;
            this.a(this.d);
        }
        else {
            this.d.height = 15;
            this.a(this.d);
        }
    }
    
    public au(final z c, final String s, final boolean b) {
        this.a = false;
        this.b = new ad();
        this.d = new Dimension(179, 15);
        this.a(b);
        this.a(s);
        this.b(s);
        this.c = c;
        this.b(dj.b);
    }
    
    public PopupMenu a(final int n, final int n2, final Component component) {
        if (n > this.k() & n <= this.k() + this.g().width & (n2 > this.l() & n2 <= this.l() + 15)) {
            return this.b();
        }
        final av e = this.e(n, n2);
        if (e != null) {
            return e.a(n, n2, component);
        }
        return null;
    }
    
    public av[] f() {
        return this.b.a(this.c);
    }
    
    private av e(final int n, final int n2) {
        final av[] b = this.b.b();
        av av = null;
        for (int i = 0; i < b.length; ++i) {
            if (!b[i].n() && b[i].d(n, n2)) {
                av = b[i];
                break;
            }
        }
        return av;
    }
    
    public void c(final int n, final int n2) {
        if (n > this.k() + 165 & n <= this.k() + this.g().width & (n2 > this.l() & n2 <= this.l() + 15)) {
            if (!this.e()) {
                this.a(true);
            }
            else {
                this.a(false);
            }
            this.c();
        }
        else {
            final av e = this.e(n, n2);
            if (e != null) {
                e.c(n, n2);
            }
        }
        this.a(n, n2);
    }
    
    public Dimension g() {
        return this.d;
    }
    
    public void a(final Dimension d) {
        this.d = d;
    }
    
    public String b(final int n, final int n2, final Component component) {
        String b = null;
        if (!(n > this.k() & n <= this.k() + this.g().width & (n2 > this.l() & n2 <= this.l() + 15))) {
            final av e = this.e(n, n2);
            if (e != null) {
                b = e.b(n, n2, component);
            }
        }
        return b;
    }
    
    public void b(final Graphics graphics, final Component component, final int n, final int n2) {
        graphics.setColor(dj.g);
        graphics.fillRect(n, n2, 133, 15);
        graphics.setColor(dj.h);
        graphics.fillRect(n + 133, n2, 15, 15);
        graphics.setColor(dj.i);
        graphics.fillRect(n + 148, n2, 15, 15);
        graphics.setColor(dj.j);
        graphics.fillRect(n + 163, n2, 15, 15);
        graphics.setFont(dj.ak);
        graphics.setColor(dj.e);
        final int charsWidth = graphics.getFontMetrics().charsWidth(this.r().toCharArray(), 0, this.r().toCharArray().length);
        graphics.drawString(this.r(), n + 2, n2 + 11);
        graphics.setFont(dj.aj);
        graphics.drawString(" (" + this.b.d() + ")", n + 2 + charsWidth, n2 + 11);
        graphics.setColor(dj.f);
        graphics.drawRect(n, n2, this.d.width - 1, 14);
        int a = 15;
        if (this.a) {
            a = this.a();
            int n3 = 1;
            graphics.drawImage(ImageRes.e, n + 165, n2 + 2, component);
            final av[] f = this.f();
            synchronized (f) {
                for (int i = 0; i < f.length; ++i) {
                    if (!f[i].n()) {
                        if (n3 != 0) {
                            graphics.setColor(dj.c);
                            n3 = 0;
                        }
                        else {
                            graphics.setColor(dj.d);
                            n3 = 1;
                        }
                        f[i].b(graphics, component, n, n2 + a);
                        a += f[i].g().height;
                    }
                }
            }
        }
        else {
            graphics.drawImage(ImageRes.d, n + 165, n2 + 2, component);
        }
        this.d.height = a;
        graphics.setColor(dj.f);
        graphics.drawRect(n, n2, this.d.width - 1, this.d.height - 1);
        this.a(graphics, component, n, n2);
    }
}
