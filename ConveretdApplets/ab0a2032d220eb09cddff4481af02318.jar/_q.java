import java.awt.Color;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class _q extends Wp
{
    public _q() {
        super(2, 1);
    }
    
    public boolean _(final int n, final int n2, final Np np, final Np np2) {
        super.cb[0].x = (int)np.b(super.eb[0]);
        super.cb[0].y = (int)np2.b(super.db[0]);
        super.cb[1].x = (int)np.b(super.eb[1]);
        super.cb[1].y = (int)np2.b(super.db[1]);
        int n3;
        int n4;
        if (super.cb[0].x <= super.cb[1].x) {
            n3 = super.cb[0].x;
            n4 = super.cb[1].x;
        }
        else {
            n3 = super.cb[1].x;
            n4 = super.cb[0].x;
        }
        int n5;
        int n6;
        if (super.cb[0].y <= super.cb[1].y) {
            n5 = super.cb[0].y;
            n6 = super.cb[1].y;
        }
        else {
            n5 = super.cb[1].y;
            n6 = super.cb[0].y;
        }
        if (n < n3 - 6 || n > n4 + 6 || n2 < n5 - 6 || n2 > n6 + 6) {
            return false;
        }
        if (n3 == n4) {
            return true;
        }
        if (n5 == n6) {
            return true;
        }
        final double n7 = super.cb[0].x;
        final double n8 = super.cb[0].y;
        final double n9 = super.cb[1].x;
        final double n10 = super.cb[1].y;
        final double n11 = (n8 - n10) / (n7 - n9);
        final double n12 = (n7 * n10 - n9 * n8) / (n7 - n9);
        final int n13 = (int)(n11 * n + n12);
        final int n14 = (int)((n2 - n12) / n11);
        return Math.abs(n13 - n2) < 6 || Math.abs(n14 - n) < 6;
    }
    
    protected void b(final Graphics graphics, final Color xorMode, final Np np, final Np np2) {
        graphics.setXORMode(xorMode);
        graphics.setColor(super.Ba);
        if (super.c < 1) {
            graphics.drawLine(super.cb[0].x, super.cb[0].y, super.cb[1].x, super.cb[1].y);
        }
        else {
            Wp._(graphics, super.cb[0].x, super.cb[0].y, super.cb[1].x, super.cb[1].y, super.c);
        }
        graphics.setPaintMode();
    }
}
