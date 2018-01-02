import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Font;
import java.text.DecimalFormat;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class Retracements extends UserDrawTool
{
    private final DecimalFormat Tja;
    protected double[] Uja;
    Font Vja;
    
    protected Retracements() {
        super(2, 0);
        this.Tja = new DecimalFormat("#,##0.##%");
        this.Uja = new double[] { 0.0 };
        this.Vja = new Font("SansSerif", 0, 11);
    }
    
    public boolean _(final double n, final double n2, final double n3, final double n4, final int n5, final int n6, final throws throws1, final throws throws2) {
        final Point point = new Point((int)throws1.b(n), (int)throws2.b(n2));
        final Point point2 = new Point((int)throws1.b(n3), (int)throws2.b(n4));
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
    
    protected void b(final Graphics graphics, final Color xorMode, final throws throws1, final throws throws2) {
        graphics.setXORMode(xorMode);
        graphics.setColor(super.xa);
        graphics.drawLine(super.Wja[0].x, super.Wja[0].y, super.Wja[1].x, super.Wja[1].y);
        graphics.setFont(this.Vja);
        for (int i = 0; i < this.Uja.length; ++i) {
            final int n = (int)throws2.b(this._(this.Uja[i]));
            graphics.drawLine(super.Wja[0].x, n, super.Wja[1].x, n);
            graphics.drawString(this.a(this.Uja[i]), Math.min(super.Wja[0].x, super.Wja[1].x), n - 1);
        }
        graphics.setPaintMode();
    }
    
    private String a(final double n) {
        return this.Tja.format(n);
    }
    
    private double _(final double n) {
        final double abs = Math.abs(super.Xja[1] - super.Xja[0]);
        double n2;
        if (super.Yja[0] < super.Yja[1]) {
            if (super.Xja[0] < super.Xja[1]) {
                n2 = super.Xja[1] - abs * n;
            }
            else {
                n2 = super.Xja[1] + abs * n;
            }
        }
        else if (super.Xja[0] < super.Xja[1]) {
            n2 = super.Xja[0] + abs * n;
        }
        else {
            n2 = super.Xja[0] - abs * n;
        }
        return n2;
    }
    
    public boolean _(final int n, final int n2, final throws throws1, final throws throws2) {
        if (this._(super.Yja[0], super.Xja[0], super.Yja[1], super.Xja[1], n, n2, throws1, throws2)) {
            return true;
        }
        for (int i = 0; i < this.Uja.length; ++i) {
            final double _ = this._(this.Uja[i]);
            if (this._(super.Yja[0], _, super.Yja[1], _, n, n2, throws1, throws2)) {
                return true;
            }
        }
        return false;
    }
}
