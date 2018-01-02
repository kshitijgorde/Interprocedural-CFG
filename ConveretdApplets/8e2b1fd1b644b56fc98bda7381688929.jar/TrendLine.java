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
    
    protected TrendLine b() {
        final TrendLine trendLine = new TrendLine();
        trendLine.tra = super.tra;
        for (int i = 0; i < super._ra.length; ++i) {
            trendLine._ra[i] = super._ra[i];
        }
        for (int j = 0; j < super.Zqa.length; ++j) {
            trendLine.Zqa[j] = super.Zqa[j];
        }
        trendLine.T = new Color(super.T.getRGB());
        return trendLine;
    }
    
    public boolean a(final int n, final int n2, final o o, final o o2) {
        super.Yqa[0].x = (int)o.b(super._ra[0]);
        super.Yqa[0].y = (int)o2.b(super.Zqa[0]);
        super.Yqa[1].x = (int)o.b(super._ra[1]);
        super.Yqa[1].y = (int)o2.b(super.Zqa[1]);
        int n3;
        int n4;
        if (super.Yqa[0].x <= super.Yqa[1].x) {
            n3 = super.Yqa[0].x;
            n4 = super.Yqa[1].x;
        }
        else {
            n3 = super.Yqa[1].x;
            n4 = super.Yqa[0].x;
        }
        int n5;
        int n6;
        if (super.Yqa[0].y <= super.Yqa[1].y) {
            n5 = super.Yqa[0].y;
            n6 = super.Yqa[1].y;
        }
        else {
            n5 = super.Yqa[1].y;
            n6 = super.Yqa[0].y;
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
        final double n7 = super.Yqa[0].x;
        final double n8 = super.Yqa[0].y;
        final double n9 = super.Yqa[1].x;
        final double n10 = super.Yqa[1].y;
        final double n11 = (n8 - n10) / (n7 - n9);
        final double n12 = (n7 * n10 - n9 * n8) / (n7 - n9);
        final int n13 = (int)(n11 * n + n12);
        final int n14 = (int)((n2 - n12) / n11);
        return Math.abs(n13 - n2) < 6 || Math.abs(n14 - n) < 6;
    }
    
    protected void b(final Graphics graphics, final Color xorMode, final o o, final o o2) {
        graphics.setXORMode(xorMode);
        graphics.setColor(super.T);
        if (super.tra < 1) {
            graphics.drawLine(super.Yqa[0].x, super.Yqa[0].y, super.Yqa[1].x, super.Yqa[1].y);
        }
        else {
            UserDrawTool.a(graphics, super.Yqa[0].x, super.Yqa[0].y, super.Yqa[1].x, super.Yqa[1].y, super.tra);
        }
        graphics.setPaintMode();
    }
}
