import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Font;
import java.text.DecimalFormat;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class nq extends Wp
{
    private final DecimalFormat _b;
    protected double[] ab;
    Font bb;
    
    protected nq() {
        super(2, 0);
        this._b = new DecimalFormat("#,##0.##%");
        this.ab = new double[] { 0.0 };
        this.bb = new Font("SansSerif", 0, 10);
    }
    
    public boolean _(final double n, final double n2, final double n3, final double n4, final int n5, final int n6, final Np np, final Np np2) {
        final Point point = new Point((int)np.b(n), (int)np2.b(n2));
        final Point point2 = new Point((int)np.b(n3), (int)np2.b(n4));
        int n7;
        int n8;
        if (point.x <= point2.x) {
            n7 = point.x;
            n8 = point2.x;
        }
        else {
            n7 = point2.x;
            n8 = point.x;
        }
        int n9;
        int n10;
        if (point.y <= point2.y) {
            n9 = point.y;
            n10 = point2.y;
        }
        else {
            n9 = point2.y;
            n10 = point.y;
        }
        if (n5 < n7 - 6 || n5 > n8 + 6 || n6 < n9 - 6 || n6 > n10 + 6) {
            return false;
        }
        if (n7 == n8) {
            return true;
        }
        if (n9 == n10) {
            return true;
        }
        final double n11 = point.x;
        final double n12 = point.y;
        final double n13 = point2.x;
        final double n14 = point2.y;
        final double n15 = (n12 - n14) / (n11 - n13);
        final double n16 = (n11 * n14 - n13 * n12) / (n11 - n13);
        final int n17 = (int)(n15 * n5 + n16);
        final int n18 = (int)((n6 - n16) / n15);
        return Math.abs(n17 - n6) < 6 || Math.abs(n18 - n5) < 6;
    }
    
    protected void b(final Graphics graphics, final Color xorMode, final Np np, final Np np2) {
        graphics.setXORMode(xorMode);
        graphics.setColor(super.Ba);
        graphics.drawLine(super.cb[0].x, super.cb[0].y, super.cb[1].x, super.cb[1].y);
        graphics.setFont(this.bb);
        for (int i = 0; i < this.ab.length; ++i) {
            final int n = (int)np2.b(this._(this.ab[i]));
            graphics.drawLine(super.cb[0].x, n, super.cb[1].x, n);
            graphics.drawString(this._(this.ab[i]), Math.min(super.cb[0].x, super.cb[1].x), n - 1);
        }
        graphics.setPaintMode();
    }
    
    private String _(final double n) {
        return this._b.format(n);
    }
    
    private double _(final double n) {
        final double abs = Math.abs(super.db[1] - super.db[0]);
        double n2;
        if (super.eb[0] < super.eb[1]) {
            if (super.db[0] < super.db[1]) {
                n2 = super.db[1] - abs * n;
            }
            else {
                n2 = super.db[1] + abs * n;
            }
        }
        else if (super.db[0] < super.db[1]) {
            n2 = super.db[0] + abs * n;
        }
        else {
            n2 = super.db[0] - abs * n;
        }
        return n2;
    }
    
    public boolean _(final int n, final int n2, final Np np, final Np np2) {
        if (this._(super.eb[0], super.db[0], super.eb[1], super.db[1], n, n2, np, np2)) {
            return true;
        }
        for (int i = 0; i < this.ab.length; ++i) {
            final double _ = this._(this.ab[i]);
            if (this._(super.eb[0], _, super.eb[1], _, n, n2, np, np2)) {
                return true;
            }
        }
        return false;
    }
}
