import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class c extends Canvas
{
    private final int a = 3;
    private int b;
    
    public c() {
        this(0);
    }
    
    public c(final int b) {
        this.b = b;
        this.setForeground(Color.gray);
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(this.b, 3);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(this.getForeground());
        graphics.fill3DRect(0, 0, this.getSize().width - 1, 2, false);
    }
}
