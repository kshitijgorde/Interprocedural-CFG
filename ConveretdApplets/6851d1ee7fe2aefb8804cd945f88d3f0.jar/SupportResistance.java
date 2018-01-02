import java.awt.Color;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class SupportResistance extends UserDrawTool
{
    private int hka;
    private int ika;
    
    public SupportResistance() {
        super(1, 1);
        this.hka = 0;
        this.ika = 0;
    }
    
    public boolean _(final int n, final int n2, final throws throws1, final throws throws2) {
        super.Wja[0].x = (int)throws1.b(super.Yja[0]);
        super.Wja[0].y = (int)throws2.b(super.Xja[0]);
        return Math.abs(n2 - super.Wja[0].y) <= 6;
    }
    
    protected void b(final Graphics graphics, final Color xorMode, final throws throws1, final throws throws2) {
        graphics.setXORMode(xorMode);
        graphics.setColor(super.xa);
        if (super.eka < 1) {
            graphics.drawLine(this.hka, super.Wja[0].y, this.ika, super.Wja[0].y);
        }
        else {
            UserDrawTool.b(graphics, this.hka, super.Wja[0].y, this.ika, super.Wja[0].y, super.eka);
        }
        graphics.setPaintMode();
    }
    
    public void b(final int hka, final int ika) {
        this.hka = hka;
        this.ika = ika;
    }
}
