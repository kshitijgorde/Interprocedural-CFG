import java.awt.Color;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class SupportResistance extends UserDrawTool
{
    private int gra;
    private int fra;
    
    public SupportResistance() {
        super(1, 1);
        this.gra = 0;
        this.fra = 0;
    }
    
    public boolean a(final int n, final int n2, final o o, final o o2) {
        super.Yqa[0].x = (int)o.b(super._ra[0]);
        super.Yqa[0].y = (int)o2.b(super.Zqa[0]);
        return Math.abs(n2 - super.Yqa[0].y) <= 6;
    }
    
    protected void b(final Graphics graphics, final Color xorMode, final o o, final o o2) {
        graphics.setXORMode(xorMode);
        graphics.setColor(super.T);
        if (super.tra < 1) {
            graphics.drawLine(this.gra, super.Yqa[0].y, this.fra, super.Yqa[0].y);
        }
        else {
            UserDrawTool.a(graphics, this.gra, super.Yqa[0].y, this.fra, super.Yqa[0].y, super.tra);
        }
        graphics.setPaintMode();
    }
    
    public void m(final int gra, final int fra) {
        this.gra = gra;
        this.fra = fra;
    }
}
