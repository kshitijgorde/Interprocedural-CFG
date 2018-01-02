import java.awt.Graphics;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class WindCanvas extends Canvas
{
    public void paint(final Graphics graphics) {
        final int penSize = ColoringBook.penSize;
        graphics.setColor(ColoringBook.col);
        graphics.fillOval(30 - penSize / 2, 30 - penSize / 2, penSize, penSize);
    }
}
