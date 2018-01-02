import java.awt.Shape;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.TextArea;

// 
// Decompiled by Procyon v0.5.30
// 

class TouchupTextArea extends TextArea
{
    public TouchupTextArea(final String s, final int n, final int n2, final int n3) {
        super(s, n, n2, n3);
    }
    
    public void paint(final Graphics graphics) {
        final Shape clip = graphics.getClip();
        final int width = this.getBounds().width;
        graphics.clipRect(3, 3, width - 6, this.getBounds().height - 6);
        graphics.setClip(clip);
        graphics.setColor(Color.cyan);
        graphics.drawLine(0, 0, width, 0);
        graphics.drawLine(0, 1, width, 1);
        graphics.fillRect(0, 0, 20, 20);
    }
    
    public void update(final Graphics graphics) {
    }
}
