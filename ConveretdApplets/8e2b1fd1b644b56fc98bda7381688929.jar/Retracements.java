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
    private final DecimalFormat Vqa;
    protected double[] Wqa;
    Font Xqa;
    
    protected Retracements() {
        super(2, 0);
        this.Vqa = new DecimalFormat("#,##0.##%");
        this.Wqa = new double[] { 0.0 };
        this.Xqa = new Font("SansSerif", 0, 10);
    }
    
    public boolean b(final double n, final double n2, final double n3, final double n4, final int n5, final int n6, final o o, final o o2) {
        final Point point = new Point((int)o.b(n), (int)o2.b(n2));
        final Point point2 = new Point((int)o.b(n3), (int)o2.b(n4));
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
    
    protected void b(final Graphics graphics, final Color xorMode, final o o, final o o2) {
        graphics.setXORMode(xorMode);
        graphics.setColor(super.T);
        graphics.drawLine(super.Yqa[0].x, super.Yqa[0].y, super.Yqa[1].x, super.Yqa[1].y);
        graphics.setFont(this.Xqa);
        for (int i = 0; i < this.Wqa.length; ++i) {
            final int n = (int)o2.b(this.a(this.Wqa[i]));
            graphics.drawLine(super.Yqa[0].x, n, super.Yqa[1].x, n);
            graphics.drawString(this._(this.Wqa[i]), Math.min(super.Yqa[0].x, super.Yqa[1].x), n - 1);
        }
        graphics.setPaintMode();
    }
    
    private String _(final double n) {
        return this.Vqa.format(n);
    }
    
    private double a(final double n) {
        final double abs = Math.abs(super.Zqa[1] - super.Zqa[0]);
        double n2;
        if (super._ra[0] < super._ra[1]) {
            if (super.Zqa[0] < super.Zqa[1]) {
                n2 = super.Zqa[1] - abs * n;
            }
            else {
                n2 = super.Zqa[1] + abs * n;
            }
        }
        else if (super.Zqa[0] < super.Zqa[1]) {
            n2 = super.Zqa[0] + abs * n;
        }
        else {
            n2 = super.Zqa[0] - abs * n;
        }
        return n2;
    }
    
    public boolean a(final int n, final int n2, final o o, final o o2) {
        if (this.b(super._ra[0], super.Zqa[0], super._ra[1], super.Zqa[1], n, n2, o, o2)) {
            return true;
        }
        for (int i = 0; i < this.Wqa.length; ++i) {
            final double a = this.a(this.Wqa[i]);
            if (this.b(super._ra[0], a, super._ra[1], a, n, n2, o, o2)) {
                return true;
            }
        }
        return false;
    }
}
