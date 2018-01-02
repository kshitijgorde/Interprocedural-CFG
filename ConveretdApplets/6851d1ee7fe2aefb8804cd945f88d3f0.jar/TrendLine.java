import java.awt.Graphics;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class TrendLine extends UserDrawTool
{
    public TrendLine() {
        super(2, 1);
    }
    
    protected TrendLine a() {
        final TrendLine trendLine = new TrendLine();
        trendLine.eka = super.eka;
        for (int i = 0; i < super.Yja.length; ++i) {
            trendLine.Yja[i] = super.Yja[i];
        }
        for (int j = 0; j < super.Xja.length; ++j) {
            trendLine.Xja[j] = super.Xja[j];
        }
        trendLine.xa = new Color(super.xa.getRGB());
        return trendLine;
    }
    
    public boolean _(final int n, final int n2, final throws throws1, final throws throws2) {
        super.Wja[0].x = (int)throws1.b(super.Yja[0]);
        super.Wja[0].y = (int)throws2.b(super.Xja[0]);
        super.Wja[1].x = (int)throws1.b(super.Yja[1]);
        super.Wja[1].y = (int)throws2.b(super.Xja[1]);
        int n3;
        int n4;
        if (super.Wja[0].x <= super.Wja[1].x) {
            n3 = super.Wja[0].x;
            n4 = super.Wja[1].x;
        }
        else {
            n3 = super.Wja[1].x;
            n4 = super.Wja[0].x;
        }
        int n5;
        int n6;
        if (super.Wja[0].y <= super.Wja[1].y) {
            n5 = super.Wja[0].y;
            n6 = super.Wja[1].y;
        }
        else {
            n5 = super.Wja[1].y;
            n6 = super.Wja[0].y;
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
        final double n7 = super.Wja[0].x;
        final double n8 = super.Wja[0].y;
        final double n9 = super.Wja[1].x;
        final double n10 = super.Wja[1].y;
        final double n11 = (n8 - n10) / (n7 - n9);
        final double n12 = (n7 * n10 - n9 * n8) / (n7 - n9);
        final int n13 = (int)(n11 * n + n12);
        final int n14 = (int)((n2 - n12) / n11);
        return Math.abs(n13 - n2) < 6 || Math.abs(n14 - n) < 6;
    }
    
    protected void b(final Graphics graphics, final Color xorMode, final throws throws1, final throws throws2) {
        graphics.setXORMode(xorMode);
        graphics.setColor(super.xa);
        if (super.eka < 1) {
            graphics.drawLine(super.Wja[0].x, super.Wja[0].y, super.Wja[1].x, super.Wja[1].y);
        }
        else {
            UserDrawTool.b(graphics, super.Wja[0].x, super.Wja[0].y, super.Wja[1].x, super.Wja[1].y, super.eka);
        }
        graphics.setPaintMode();
    }
}
