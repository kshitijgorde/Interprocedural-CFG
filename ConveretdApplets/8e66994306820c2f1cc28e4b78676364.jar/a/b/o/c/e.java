// 
// Decompiled by Procyon v0.5.30
// 

package a.b.o.c;

import java.awt.event.MouseEvent;
import java.awt.Dimension;

public class e extends d implements j
{
    private a.b.o.a.b.e j;
    private a.b.o.a.b.e k;
    private boolean l;
    private boolean m;
    private boolean n;
    
    public e() {
        this.j = null;
        this.k = null;
        this.l = false;
        this.m = false;
        this.n = true;
    }
    
    public e(final a.b.o.a.b.e k, final a.b.o.a.b.e j, final boolean l, final boolean m) {
        super(k);
        this.j = null;
        this.k = null;
        this.l = false;
        this.m = false;
        this.n = true;
        this.k = k;
        this.j = j;
        this.l = l;
        this.m = m;
        this.n = false;
        this.c();
    }
    
    public void a() {
        if (this.n) {
            this.d();
        }
        else {
            this.b();
        }
    }
    
    public void b() {
        if (!this.n) {
            this.a(this.j, false);
            this.n = true;
            this.repaint();
        }
    }
    
    public void d() {
        if (this.n) {
            this.a(this.k, false);
            this.n = false;
            this.repaint();
        }
    }
    
    protected void c() {
        final Dimension b = this.b(this.j);
        final Dimension b2 = this.b(this.k);
        this.setSize(Math.max(b.width, b2.width), Math.max(b.height, b2.height));
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        if (this.l) {
            this.a();
        }
        super.mouseEntered(mouseEvent);
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        if (this.l) {
            this.a();
        }
        super.mouseExited(mouseEvent);
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (this.m) {
            this.a();
        }
        super.mouseReleased(mouseEvent);
    }
    
    public boolean equals(final Object o) {
        if (o == null) {
            return false;
        }
        try {
            final e e = (e)o;
            return this.b(this.j, e.j) && this.b(this.k, e.k);
        }
        catch (ClassCastException ex) {
            return false;
        }
    }
    
    private boolean b(final Object o, final Object o2) {
        if (o == null || o2 == null) {
            return o == o2;
        }
        return o.equals(o2);
    }
}
