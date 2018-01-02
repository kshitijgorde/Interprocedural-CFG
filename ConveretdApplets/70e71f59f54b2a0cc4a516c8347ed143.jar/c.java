import java.awt.Graphics;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class c extends a
{
    public c(final int n, final int n2, final int n3, final int n4) {
        super(n, n2, n3, 0, n4);
    }
    
    protected void a(final int n, final int n2, final int n3, int n4) {
        super.a[0] = n;
        super.b[0] = n2;
        super.a[1] = n;
        super.b[1] = n2 - n3 / 5;
        super.a[2] = n;
        super.b[2] = n2 + n3;
        n4 = 0;
    }
    
    public void a(final Color color, final double n, final Graphics graphics) {
        this.a(n);
        graphics.setColor(color);
        graphics.drawLine(super.c[1], super.d[1], super.c[2], super.d[2]);
    }
}
