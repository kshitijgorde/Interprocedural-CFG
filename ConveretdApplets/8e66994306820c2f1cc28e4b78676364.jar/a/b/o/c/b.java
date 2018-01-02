// 
// Decompiled by Procyon v0.5.30
// 

package a.b.o.c;

import java.awt.event.MouseEvent;
import java.awt.Dimension;
import a.b.o.a.b.c;

public class b extends a implements j
{
    private c h;
    private c i;
    private boolean j;
    private boolean k;
    private boolean l;
    
    public b(final c i, final c h, final boolean j, final boolean k) {
        super(i);
        this.i = i;
        this.h = h;
        this.j = j;
        this.k = k;
        this.l = false;
        this.a();
    }
    
    public void c() {
        if (this.l) {
            this.e();
        }
        else {
            this.d();
        }
    }
    
    public void d() {
        if (!this.l) {
            this.a(this.h, false);
            this.l = true;
            this.repaint();
        }
    }
    
    public void e() {
        if (this.l) {
            this.a(this.i, false);
            this.l = false;
            this.repaint();
        }
    }
    
    protected void a() {
        final Dimension b = this.b(this.h);
        final Dimension b2 = this.b(this.i);
        this.setSize(Math.max(b.width, b2.width), Math.max(b.height, b2.height));
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        if (this.j) {
            this.c();
        }
        super.mouseEntered(mouseEvent);
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        if (this.j) {
            this.c();
        }
        super.mouseExited(mouseEvent);
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (this.k) {
            this.c();
        }
        super.mouseReleased(mouseEvent);
    }
}
