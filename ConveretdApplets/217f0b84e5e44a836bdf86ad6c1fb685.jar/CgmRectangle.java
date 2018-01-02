import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

class CgmRectangle extends CgmPrimitive
{
    CgmRectangle(final double n, final double n2, final double n3, final double n4) {
        super.x = Math.min(n, n3);
        super.y = Math.min(n2, n4);
        super.Height = Math.abs(n4 - n2);
        super.Width = Math.abs(n3 - n);
    }
    
    final void draw(final Graphics graphics, final double n, final double n2, final boolean b) {
        final int n3 = (int)(super.x * n + 0.6);
        final int n4 = (int)(super.y * n2 + 0.6);
        final int n5 = (int)(super.Width * n + 0.6);
        final int n6 = (int)(super.Height * n2 + 0.6);
        if (b && super.IntStyle >= 1) {
            graphics.setColor(super.FillColor);
            graphics.fillRect(n3, n4, n5, n6);
        }
        if (super.LineColor != null) {
            final int n7 = (int)(super.LineWidth * n + 0.5);
            graphics.setColor(super.LineColor);
            if (n7 <= 1) {
                graphics.drawRect(n3, n4, n5, n6);
                return;
            }
            int n8 = n3 - (n7 >> 1);
            int n9 = n4 - (n7 >> 1);
            int n10 = n5 + n7;
            int n11 = n6 + n7;
            for (int i = 0; i < n7; ++i) {
                graphics.drawRect(n8++, n9++, n10, n11);
                n10 -= 2;
                n11 -= 2;
            }
        }
    }
    
    final boolean find(final double n, final double n2) {
        return super.visibility != 0 && super.IntStyle >= 1 && n >= super.x && n2 >= super.y && n <= super.x + super.Width && n2 <= super.y + super.Height;
    }
}
