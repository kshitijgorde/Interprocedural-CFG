import java.awt.Graphics;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class b extends a
{
    public static boolean h;
    
    public b(final int n, final int n2, final int n3, final int n4, final int n5) {
        super(n, n2, n3, n4, n5);
    }
    
    protected void a(final int n, final int n2, final int n3, final int n4) {
        final boolean h = b.h;
        super.a[0] = n;
        super.b[0] = n2;
        super.a[1] = super.a[0] - n4 / 2;
        super.b[1] = super.b[0] + n4 / 2;
        super.a[2] = super.a[1];
        super.b[2] = super.b[0] + n3 - n4;
        super.a[3] = super.a[0];
        super.b[3] = super.b[0] + n3;
        super.a[4] = super.a[0] + n4 / 2;
        super.b[4] = super.b[2];
        super.a[5] = super.a[4];
        super.b[5] = super.b[1];
        if (h) {
            int g = a.g;
            a.g = ++g;
        }
    }
    
    public void draw(final Color color, final double n, final Graphics graphics) {
        this.a(n);
        graphics.setColor(color);
        graphics.fillPolygon(super.c, super.d, super.e);
    }
}
