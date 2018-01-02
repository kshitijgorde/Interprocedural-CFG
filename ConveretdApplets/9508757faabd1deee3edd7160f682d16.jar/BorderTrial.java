import java.awt.Graphics;
import java.awt.Color;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class BorderTrial extends Canvas
{
    private int height;
    private int width;
    private int alignment;
    private boolean raised;
    
    public BorderTrial(final int width, final int height, final boolean raised) {
        this.setBackground(Color.lightGray);
        this.width = width;
        this.height = height;
        this.raised = raised;
        this.resize(width, height);
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(Color.lightGray);
        graphics.clearRect(0, 0, this.width, this.height);
        graphics.draw3DRect(1, 0, this.width - 3, this.height - 3, this.raised);
    }
}
