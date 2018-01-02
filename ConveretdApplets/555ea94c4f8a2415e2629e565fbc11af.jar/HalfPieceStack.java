import java.awt.Graphics;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class HalfPieceStack extends ChipStack
{
    final int HalfPieceWidth = 26;
    final int HalfPieceHeight = 4;
    
    public HalfPieceStack() {
    }
    
    public HalfPieceStack(final Color color, final Color color2, final int n, final String s, final int n2, final int n3) {
        super(color, color2, n, s, n2, n3);
        super.chipHeight = 4;
    }
    
    protected void drawChip2(final Graphics graphics, final int n, final int n2, final int n3) {
        graphics.setColor(super.chipColor);
        graphics.fillRect(n2, n, 26, 3);
        graphics.setColor(Color.black);
        graphics.drawRect(n2, n, 26, 3);
    }
    
    protected void drawBlank(final Graphics graphics) {
    }
}
