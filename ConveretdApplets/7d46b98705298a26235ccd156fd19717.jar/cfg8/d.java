// 
// Decompiled by Procyon v0.5.30
// 

package cfg8;

import java.awt.Graphics;

class d extends c
{
    protected ItemApplet e;
    protected g f;
    
    public d(final ItemApplet e) {
        super(e);
        this.e = e;
    }
    
    protected void a(final g f) {
        this.f = f;
    }
    
    public void doLayout() {
        super.doLayout();
        this.f.a(this.getBounds());
        this.invalidate();
    }
    
    protected void a(final Graphics graphics) {
        d d = this;
        if (RotationImageFilter.a == 0) {
            if (this.f == null) {
                return;
            }
            d = this;
        }
        d.a(graphics);
    }
}
