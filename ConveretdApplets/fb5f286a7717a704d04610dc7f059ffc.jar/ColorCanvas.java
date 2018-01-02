import java.awt.Graphics;
import java.awt.Color;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class ColorCanvas extends Canvas
{
    private Color color;
    private int number;
    
    public ColorCanvas(final Color color, final int number) {
        this.color = color;
        this.number = number;
        this.repaint();
    }
    
    public void paint(final Graphics graphics) {
        this.setBackground(this.color);
        if (this.number == ColoringBook.currentCanvas) {
            graphics.setColor(Color.black);
            graphics.fillOval(4, 4, 6, 6);
        }
    }
    
    public void changeColor(final Color color) {
        this.color = color;
        this.repaint();
    }
    
    public Color getColor() {
        return this.color;
    }
    
    public int getNumber() {
        return this.number;
    }
}
