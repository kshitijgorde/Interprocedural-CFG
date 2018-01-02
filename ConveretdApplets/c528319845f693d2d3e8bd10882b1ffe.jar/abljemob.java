import java.awt.Component;
import java.awt.Point;
import java.awt.Image;
import java.awt.image.ImageObserver;
import ABLwidgets.backgroundable;
import java.awt.Graphics;
import java.awt.Event;
import ABLjemsty.StyleEventArg;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class abljemob extends Canvas
{
    abljema a;
    boolean b;
    int c;
    int d;
    int e;
    int f;
    int g;
    int h;
    int i;
    String j;
    StyleEventArg k;
    boolean l;
    int m;
    static int n;
    
    abljemob(final abljema a) {
        this.b = false;
        this.l = false;
        this.m = 1;
        this.hide();
        this.a = a;
    }
    
    abljemob(final abljema a, final int e, final byte[] array, final int n) {
        this.b = false;
        this.l = false;
        this.m = 1;
        this.hide();
        this.a = a;
        this.e = e;
        if (e < 1) {
            return;
        }
        this.a(abljema.c(array, n + 1, 2), abljema.c(array, n + 3, 3), new String(array, 0, n + abljemob.n, e), null);
    }
    
    public abljemob(final abljema a, final int n, final int n2, final String s, final StyleEventArg styleEventArg) {
        this.b = false;
        this.l = false;
        this.m = 1;
        this.hide();
        this.a = a;
        this.a(n, n2, s, styleEventArg);
    }
    
    private void a(final int c, final int d, final String s, final StyleEventArg k) {
        this.c = c;
        this.d = d;
        this.j = new String("ENTAFT").concat(s);
        this.k = k;
        this.f = 0;
        this.g = 0;
        this.h = 0;
        this.i = 0;
    }
    
    public abljemob a(final abljema abljema, final int n, final int n2) {
        final abljemob abljemob = new abljemob(abljema);
        abljemob.c = this.c + n;
        abljemob.d = this.d + n2;
        abljemob.e = this.e;
        abljemob.f = this.f;
        abljemob.g = this.g;
        abljemob.h = this.h;
        abljemob.i = this.i;
        abljemob.j = this.j;
        abljemob.k = this.k;
        this.b = true;
        return abljemob;
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 504) {
            this.l = true;
            this.repaint();
        }
        if (event.id == 505) {
            this.l = false;
            this.repaint();
        }
        return super.handleEvent(event);
    }
    
    public void paint(final Graphics graphics) {
        try {
            final Image n = ((backgroundable)this.getParent()).n();
            if (n != null) {
                final Point location = this.getLocation();
                graphics.drawImage(n, -location.x, -location.y, this);
            }
        }
        catch (Throwable t) {}
        Image image = this.a.j.cr;
        if (this.a.eg != null) {
            image = this.a.eg;
        }
        if (this.l && this.a.eh != null) {
            image = this.a.eh;
        }
        if (this.h > 0 && image != null) {
            graphics.drawImage(image, 0, 0, this.h, this.i, this);
        }
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (event.clickCount > 1) {
            return true;
        }
        if (this.k != null) {
            event.arg = this.k;
            this.a.fb.b(event);
            return true;
        }
        if (this.a.fb.e0.h) {
            this.a.fb.e0.d(this.j);
        }
        this.a.fb.g(this.j);
        return true;
    }
    
    static int a(final byte[] array, final int n, final abljema abljema) {
        final int c = abljema.c(array, n + 6, 2);
        if (abljema.cr != null) {
            if (c < 1 || c > 99) {
                abljem.d("jem error 61, " + c);
            }
            else {
                final abljemob abljemob = new abljemob(abljema, c, array, n);
                if (abljemob == null) {
                    abljem.d("jem error 62");
                }
                else {
                    abljema.ex[abljema.ey++] = abljemob;
                    if (!abljema.bu || !abljema.he) {
                        abljema.fb.add(abljemob);
                    }
                }
            }
        }
        return abljemob.n + c;
    }
    
    static {
        abljemob.n = 8;
    }
}
