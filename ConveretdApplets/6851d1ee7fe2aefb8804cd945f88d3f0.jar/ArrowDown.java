import java.awt.Color;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class ArrowDown extends UserDrawTool
{
    private static final int HEIGHT = 9;
    
    public ArrowDown() {
        super(1, 0);
    }
    
    public boolean _(final int n, final int n2, final throws throws1, final throws throws2) {
        super.Wja[0].x = (int)throws1.b(super.Yja[0]);
        super.Wja[0].y = (int)throws2.b(super.Xja[0]);
        return n > super.Wja[0].x - 4 && n < super.Wja[0].x + 4 && n2 >= super.Wja[0].y - 9 && n2 <= super.Wja[0].y + 1;
    }
    
    protected void b(final Graphics graphics, final Color xorMode, final throws throws1, final throws throws2) {
        graphics.setXORMode(xorMode);
        graphics.setColor(super.xa);
        b(graphics, super.Wja[0].x, super.Wja[0].y);
        graphics.setPaintMode();
    }
    
    public static void b(final Graphics graphics, final int n, final int n2) {
        graphics.drawLine(n, n2, n, n2);
        graphics.drawLine(n - 1, n2 - 1, n + 1, n2 - 1);
        graphics.drawLine(n - 2, n2 - 2, n + 2, n2 - 2);
        graphics.drawLine(n - 3, n2 - 3, n + 3, n2 - 3);
        graphics.drawLine(n - 4, n2 - 4, n + 4, n2 - 4);
        graphics.fillRect(n - 1, n2 - 9, 3, 5);
    }
}
