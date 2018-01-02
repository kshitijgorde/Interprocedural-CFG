import java.awt.Color;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class cq extends Wp
{
    private int kb;
    private int lb;
    
    public cq() {
        super(1, 1);
        this.kb = 0;
        this.lb = 0;
    }
    
    public boolean _(final int n, final int n2, final Np np, final Np np2) {
        super.cb[0].x = (int)np.b(super.eb[0]);
        super.cb[0].y = (int)np2.b(super.db[0]);
        return Math.abs(n2 - super.cb[0].y) <= 6;
    }
    
    protected void b(final Graphics graphics, final Color xorMode, final Np np, final Np np2) {
        graphics.setXORMode(xorMode);
        graphics.setColor(super.Ba);
        if (super.c < 1) {
            graphics.drawLine(this.kb, super.cb[0].y, this.lb, super.cb[0].y);
        }
        else {
            Wp._(graphics, this.kb, super.cb[0].y, this.lb, super.cb[0].y, super.c);
        }
        graphics.setPaintMode();
    }
    
    public void a(final int kb, final int lb) {
        this.kb = kb;
        this.lb = lb;
    }
}
